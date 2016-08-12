package org.wowtools.common.utils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

/**
 * 简单的http请求工具类
 * 
 * @author liuyu
 * @date 2016年8月12日
 */
public class SimpleHttpUtil {
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url) {
		InputStream ins = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			ins = connection.getInputStream();
			int b = ins.read();
			LinkedList<Byte> bList = new LinkedList<>();
			while (b > 0) {
				bList.add((byte) b);
				b = ins.read();
			}
			byte[] bs = new byte[bList.size()];
			int i = 0;
			for (byte bt : bList) {
				bs[i] = bt;
				i++;
			}
			return new String(bs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (ins != null) {
					ins.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
