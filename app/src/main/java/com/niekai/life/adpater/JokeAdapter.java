package com.niekai.life.adpater;

import java.util.List;

import com.niekai.life.R;
import com.niekai.life.domain.Joke;

import android.content.Context;

public class JokeAdapter extends CommonAdapter {

	public JokeAdapter(Context context, List<Joke> list) {
		super(context, list, R.layout.joke_item, R.id.joke_item_tv1,
				R.id.joke_item_tv2, R.id.joke_item_img);
	}

}
