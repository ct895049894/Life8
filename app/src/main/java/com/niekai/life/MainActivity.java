package com.niekai.life;

import java.util.ArrayList;
import java.util.List;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.niekai.life.R;
import com.niekai.life.fragement.JokeFragment;
import com.niekai.life.fragement.NewsFragment;
import com.niekai.life.fragement.VideoFragment;

public class MainActivity extends FragmentActivity implements
		ViewPager.OnPageChangeListener, OnClickListener, OnItemClickListener {
	private ViewPager viewPager;
	private FragmentPagerAdapter adapter;
	private List<Fragment> mTabs = new ArrayList<Fragment>();
	private TextView tv_news;
	private TextView tv_jokes;
	//private TextView tv_weather;
	private ImageView tv_newsImg;
	private ImageView tv_jokesImg;
	private ImageView tv_weatherImg;
	private ActionBar bar;
	private ListView menuListView;
	private Button loginBtn;
	private View v;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initData();
		initView();
		viewPager.setOffscreenPageLimit(2);
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);
	}

	private void initData() {
		NewsFragment nf = new NewsFragment();
		VideoFragment vf = new VideoFragment();
		//JokeFragment jf = new JokeFragment();
		mTabs.add(nf);
		mTabs.add(vf);
		//mTabs.add(jf);
	}

	private void initView() {

		bar = getActionBar();
		bar.setHomeButtonEnabled(true);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setOnPageChangeListener(this);
		tv_news = (TextView) findViewById(R.id.tab_news);
		tv_jokes = (TextView) findViewById(R.id.tab_jokes);
		//tv_weather = (TextView) findViewById(R.id.tab_weather);

		tv_news.setOnClickListener(this);
		tv_jokes.setOnClickListener(this);
		//tv_weather.setOnClickListener(this);
		adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				return mTabs.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return mTabs.get(arg0);
			}
		};

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		resetColor();
		switch (arg0) {
		case 0:
			tv_news.setTextColor(getResources().getColor(R.color.LimeGreen));

			break;

		case 1:
			tv_jokes.setTextColor(getResources().getColor(R.color.LimeGreen));

			break;

//		case 2:
//			tv_weather.setTextColor(getResources().getColor(R.color.LimeGreen));
//
//			break;
		}
	}

	private void resetColor() {
		tv_news.setTextColor(getResources().getColor(R.color.gray));
		tv_jokes.setTextColor(getResources().getColor(R.color.gray));
		//tv_weather.setTextColor(getResources().getColor(R.color.gray));

	}

	@Override
	public void onClick(View arg0) {
		resetColor();
		switch (arg0.getId()) {
		case R.id.tab_news:
			tv_news.setTextColor(getResources().getColor(R.color.LimeGreen));

			viewPager.setCurrentItem(0);
			break;

		case R.id.tab_jokes:
			tv_jokes.setTextColor(getResources().getColor(R.color.LimeGreen));

			viewPager.setCurrentItem(1);
			break;

//		case R.id.tab_weather:
//			tv_weather.setTextColor(getResources().getColor(R.color.LimeGreen));
//
//			viewPager.setCurrentItem(2);
//			break;

		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0:
			break;
		}
	}

	private long exitTimeMillis = System.currentTimeMillis();

	@Override
	public void onBackPressed() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - exitTimeMillis == 0
				|| currentTime - exitTimeMillis > 1500) {
			exitTimeMillis = System.currentTimeMillis();
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
		} else {
			finish();
		}
	}
}