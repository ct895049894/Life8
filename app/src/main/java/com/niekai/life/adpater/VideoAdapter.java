package com.niekai.life.adpater;

import java.util.List;

import com.niekai.life.R;
import com.niekai.life.domain.Video;

import android.content.Context;

public class VideoAdapter extends CommonAdapter{

	public VideoAdapter(Context context, List<Video> videos) {
		super(context, videos, R.layout.video_item,
				R.id.video_item_tv1, R.id.video_item_tv2,
				R.id.video_item_img);
	}
}