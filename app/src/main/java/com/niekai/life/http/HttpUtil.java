package com.niekai.life.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

public class HttpUtil {

	public static HttpEntity getJsonData(String url) {

		HttpEntity entity = null;
		HttpClient ht = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		try {
			HttpResponse response = ht.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				entity = response.getEntity();
			}
		} catch (Exception e) {

		}
		return entity;
	}

	public static String getJsonStr(HttpEntity entity) {
		try {
			System.out.println(entity.getContentEncoding());
			return EntityUtils.toString(entity, HTTP.UTF_8);
		} catch (Exception e) {
		}
		return null;
	}

	public static InputStream getInputStream(HttpEntity entity) {
		try {
			return entity.getContent();
		} catch (Exception e) {
		}
		return null;
	}

	public static Bitmap getBitmap(InputStream in) {
		return BitmapFactory.decodeStream(in);
	}

	public static Uri getImage(File dir, String picUrl) {
		File localFile = new File(dir, MD5.getMD5(picUrl)
				+ picUrl.substring(picUrl.lastIndexOf(".")));
		if (localFile.exists()) {
			return Uri.fromFile(localFile);
		} else {
			try {
				OutputStream out = new FileOutputStream(localFile);
				InputStream in = HttpUtil.getInputStream(HttpUtil
						.getJsonData(picUrl));
				byte[] data = new byte[1024];
				int len = 0;
				while ((len = in.read(data)) != -1) {
					out.write(data, 0, len);
				}
				in.close();
				out.close();
				return Uri.fromFile(localFile);
			} catch (Exception e) {
			}
		}
		return null;
	}
}
