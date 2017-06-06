package com.niekai.life.activity;

import java.io.File;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.niekai.life.R;
import com.niekai.life.http.MD5;

public class VideoActivity extends Activity {
	private VideoView video;
	private static ProgressDialog pd;
	private String videoUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_video);
		super.onCreate(savedInstanceState);
		videoUrl = getIntent().getStringExtra("videoUrl");
		initView();
		showProgressDialog();

	}

	@Override
	protected void onResume() {
		super.onResume();
		video.setVideoURI(getVideoUri(videoUrl));
		video.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				pd.dismiss();
			}
		});
		video.start();
	}

	private void initView() {
		video = (VideoView) findViewById(R.id.video);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		video.setLayoutParams(layoutParams);
		MediaController mc = new MediaController(this);
		video.setMediaController(mc);
		mc.setMediaPlayer(video);
	}

	private void showProgressDialog() {
		pd = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
		pd.setMessage("正在拼命加载中......");
		pd.show();
	}

	private Uri getVideoUri(String videoUrl) {
		File f = new File(this.getExternalCacheDir(), MD5.getMD5(videoUrl)
				+ videoUrl.substring(videoUrl.lastIndexOf(".")));
		if (f.exists() && f.canExecute()) {
			return Uri.fromFile(f);
		} else {
			startCache(videoUrl);
		}
		return Uri.parse(videoUrl);
	}

	private void startCache(String url) {
		Intent it = new Intent();
		it.setClass(this, VideoService.class);
		it.putExtra("url", url);
		startService(it);
	}

}
