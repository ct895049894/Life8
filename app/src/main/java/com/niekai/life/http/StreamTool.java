package com.niekai.life.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTool {

	public static byte[] getByteArray(InputStream in){
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		try {
			while((len=in.read(data))!=-1){
				bo.write(data, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bo.toByteArray();
		
	}
}
