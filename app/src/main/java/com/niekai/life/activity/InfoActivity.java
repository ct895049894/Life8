package com.niekai.life.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.niekai.life.R;

public class InfoActivity extends Activity {

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_info);
		super.onCreate(savedInstanceState);
		init();
		String url = getIntent().getStringExtra("url");
		webView.loadUrl(url);
	}

	private void init() {
		webView = (WebView) findViewById(R.id.info_webView);
		WebSettings st = webView.getSettings();
		st.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		st.setJavaScriptCanOpenWindowsAutomatically(true);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
	}
}
