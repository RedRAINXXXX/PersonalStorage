package com.zzy.investeval.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用MongoDB存储文件的DAO接口实现类<br>
 * 文件以一个字符串型key值唯一标识。<br>
 * 保存文件时需指定key值、文件名，并以byte数组或{@code InputStream}的形式提供文件内容，若已有相同key值则覆盖。<br>
 * 读取文件时需指定key值，可查询文件名，以byte数组或{@code OutputStream}的形式获取文件内容。<br>
 * 删除文件时需指定key值，若不存在key值对应的文件则不执行任何操作。
 *
 * @author 赵正阳
 */
@Component
public class MongoDBFileRepository implements FileRepository {
	private final MongoClient client;

	private String database;

	private GridFSBucket gridFSBucket;

	@Autowired
	public MongoDBFileRepository(MongoClient client) {
		this.client = client;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	/** 初始化，创建{@link GridFSBucket}对象 */
	public void init() {
		gridFSBucket = GridFSBuckets.create(client.getDatabase(database));
	}

	@Override
	public void save(String key, String filename, byte[] content) {
		save(key, filename, new ByteArrayInputStream(content));
	}

	@Override
	public void save(String key, String filename, InputStream inputStream) {
		GridFSUploadOptions options = new GridFSUploadOptions().metadata(new Document("key", key));
		gridFSBucket.uploadFromStream(filename, inputStream, options);
	}

	@Override
	public boolean exists(String key) {
		return gridFSBucket.find(new Document("metadata.key", key)).iterator().hasNext();
	}

	@Override
	public List<String> findAllKeys() {
		MongoCursor<GridFSFile> cursor = gridFSBucket.find().iterator();
		List<String> keyList = new ArrayList<>();
		while (cursor.hasNext())
			keyList.add(cursor.next().getMetadata().getString("key"));
		return keyList;
	}

	@Override
	public String findFilename(String key) {
		MongoCursor<GridFSFile> cursor = gridFSBucket.find(new Document("metadata.key", key)).iterator();
		return cursor.hasNext() ? cursor.next().getFilename() : null;
	}

	@Override
	public byte[] readContent(String key) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		if (readContent(key, outputStream))
			return outputStream.toByteArray();
		else
			return null;
	}

	@Override
	public boolean readContent(String key, OutputStream outputStream) {
		MongoCursor<GridFSFile> cursor = gridFSBucket.find(new Document("metadata.key", key)).iterator();
		if (cursor.hasNext()) {
			gridFSBucket.downloadToStream(cursor.next().getObjectId(), outputStream);
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean delete(String key) {
		MongoCursor<GridFSFile> cursor = gridFSBucket.find(new Document("metadata.key", key)).iterator();
		if (cursor.hasNext()) {
			gridFSBucket.delete(cursor.next().getObjectId());
			return true;
		}
		else
			return false;
	}

}
