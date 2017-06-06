package com.niekai.life.adpater;

import java.util.List;

import com.niekai.life.R;
import com.niekai.life.domain.Information;

import android.content.Context;

public class InfoAdapter extends CommonAdapter {

	public InfoAdapter(Context context, List<Information> list) {
		super(context, list, R.layout.info_item, R.id.info_item_tv1,
				R.id.info_item_tv2, R.id.info_item_img);
	}

}
