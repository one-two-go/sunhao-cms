package com.sunhao.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 
 * @author zhuzg  
 *
 */
public class StreamUtils {
	
	/**
	 *  关闭所有的流
	 * @param n
	 * @param streams
	 * @throws IOException
	 */
	public static void closeStream(Closeable ... streams) throws IOException {
		
		for (int i = 0; i < streams.length; i++) {
			streams[i].close();
		}
		
	}

}
