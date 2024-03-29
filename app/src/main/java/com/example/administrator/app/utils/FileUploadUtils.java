
package com.example.administrator.app.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class FileUploadUtils {
	public static String uploadFile(String actionUrl,Map<String, String> params,
			Map<String, File> files) throws IOException {
		return uploadFile(actionUrl,params, files, null);
	}
	
	public static String uploadFile(String actionUrl,  Map<String, String> params,
			Map<String, File> files, Map<String, String> headers) throws IOException {

		String BOUNDARY = java.util.UUID.randomUUID().toString();
		String PREFIX = "--", LINEND = "\r\n";
		String MULTIPART_FROM_DATA = "multipart/form-data";
		String CHARSET = "UTF-8";

		URL uri = new URL(actionUrl);
		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		conn.setConnectTimeout(60 * 1000);
		conn.setReadTimeout(60 * 1000);
		conn.setDoInput(true);// 允许输入
		conn.setDoOutput(true);// 允许输出
		conn.setUseCaches(false);
		conn.setRequestMethod("POST"); // Post方式
		conn.setRequestProperty("connection", "keep-alive");
		conn.setRequestProperty("Charsert", "UTF-8");

		if (headers != null && !headers.isEmpty()) {
			for (String key : headers.keySet()) {
				conn.setRequestProperty(key, headers.get(key));
			}
		}
		
		conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
		
		DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
		
		// 首先组拼文本类型的参数
		if (params != null && !params.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(PREFIX);
				sb.append(BOUNDARY);
				sb.append(LINEND);
				sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
				sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
				// sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
				sb.append(LINEND);
				sb.append(entry.getValue());
				sb.append(LINEND);
			}
			outStream.write(sb.toString().getBytes());
		}
		
		// 发送文件数据
		int fileIndex = 0;
		if (files != null)
			for (Map.Entry<String, File> file : files.entrySet()) {
				StringBuilder sb1 = new StringBuilder();
				sb1.append(PREFIX);
				sb1.append(BOUNDARY);
				sb1.append(LINEND);
				sb1.append("Content-Disposition: form-data; name=\"" + file.getKey() + "\"; " +
						"filename=\"" + file.getValue().getName() + "\""+ LINEND);
				sb1.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
				sb1.append(LINEND);
				outStream.write(sb1.toString().getBytes());

				InputStream is = new FileInputStream(file.getValue());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					outStream.write(buffer, 0, len);
				}

				is.close();
				outStream.write(LINEND.getBytes());
				fileIndex++;
			}

		// 请求结束标志
		byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
		outStream.write(end_data);
		outStream.flush();
		// 得到响应码
		int res = conn.getResponseCode();
		InputStream in = conn.getInputStream();
		InputStreamReader isReader = new InputStreamReader(in);

		BufferedReader bufReader = new BufferedReader(isReader);

		String line = null;
		StringBuffer data = new StringBuffer();
		if (res == 200) {
			while ((line = bufReader.readLine()) != null)
				data.append(line);
		}
		outStream.close();
		conn.disconnect();
		bufReader.close();
		return data.toString();
	}

}

