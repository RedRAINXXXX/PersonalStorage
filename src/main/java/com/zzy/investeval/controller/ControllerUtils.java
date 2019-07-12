package com.zzy.investeval.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * {@code Controller}辅助类，包含一些公用操作
 *
 * @author 赵正阳
 */
class ControllerUtils {

	private ControllerUtils() {}

	/** 将文件名和文件内容封装为{@link ResponseEntity}并返回 */
	static ResponseEntity<byte[]> downloadFile(String filename, byte[] content) {
		if (filename == null)
			return ResponseEntity.notFound().build();
		else {
			try {
				filename = URLEncoder.encode(filename, "UTF-8").replace("+", "%20");
			}
			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
					.body(content);
		}
	}

}
