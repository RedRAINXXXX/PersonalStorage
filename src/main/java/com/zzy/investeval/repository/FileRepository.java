package com.zzy.investeval.repository;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 存储文件的DAO接口<br>
 * 文件以一个字符串型key值唯一标识。<br>
 * 保存文件时需指定key值、文件名，并以byte数组或{@code InputStream}的形式提供文件内容，若已有相同key值则覆盖。<br>
 * 读取文件时需指定key值，可查询文件名，以byte数组或{@code OutputStream}的形式获取文件内容。<br>
 * 删除文件时需指定key值，若不存在key值对应的文件则不执行任何操作。
 *
 * @author 赵正阳
 */
public interface FileRepository {

	/**
	 * 存储文件，若已有相同key值的文件则覆盖
	 *
	 * @param key      文件key值
	 * @param filename 文件名
	 * @param content  文件内容
	 */
	void save(String key, String filename, byte[] content);

	/**
	 * 存储文件，若已有相同key值的文件则覆盖
	 *
	 * @param key         文件key值
	 * @param filename    文件名
	 * @param inputStream 用于获取文件内容的输入流
	 */
	void save(String key, String filename, InputStream inputStream);

	/** 查询key值对应的文件是否存在 */
	boolean exists(String key);

	/** 查询所有存在的键值 */
	List<String> findAllKeys();

	/** 查询key值对应的文件名，若不存在则返回 {@code null} */
	String findFilename(String key);

	/** 读取文件内容，返回byte数组，若不存在则返回 {@code null} */
	byte[] readContent(String key);

	/**
	 * 读取文件内容到指定的输出流
	 *
	 * @param key          文件key值
	 * @param outputStream 将读取出的文件内容写入的输出流
	 * @return 若文件存在并成功读取则返回 {@code true} ，否则返回 {@code false}
	 */
	boolean readContent(String key, OutputStream outputStream);

	/**
	 * 删除文件
	 *
	 * @param key 文件key值
	 * @return 若文件存在并成功删除则返回 {@code true} ，否则返回 {@code false}
	 */
	boolean delete(String key);

}
