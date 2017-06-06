package com.niekai.life.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.niekai.life.http.HttpUtil;
import com.niekai.life.http.MD5;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class VideoService extends Service {

	private File dir;
	private String url;
	private File file;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		dir = this.getExternalCacheDir();
		url = intent.getStringExtra("url");
		file = new File(dir, MD5.getMD5(url)
				+ url.substring(url.lastIndexOf(".")));
		new Mythread().start();
		return START_STICKY;
	}

	private class Mythread extends Thread {
		@Override
		public void run() {
			InputStream result = HttpUtil.getInputStream(HttpUtil
					.getJsonData(url));
			try {
				OutputStream out = new FileOutputStream(file);
				byte[] data = new byte[1024];
				int len = 0;
				while ((len = result.read(data)) != -1) {
					out.write(data, 0, len);
				}
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
