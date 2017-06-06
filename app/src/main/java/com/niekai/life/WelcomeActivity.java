package com.niekai.life;

import com.niekai.life.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_welcome);
		super.onCreate(savedInstanceState);
		Intent it = new Intent();
		it.setClass(this, MainActivity.class);
		startActivity(it);
		finish();
	}
}
