package com.niekai.life.fragement;

import java.util.List;

import org.apache.http.HttpEntity;

import com.niekai.life.R;
import com.niekai.life.adpater.JokeAdapter;
import com.niekai.life.data.ConstantValue;
import com.niekai.life.data.DataUtil;
import com.niekai.life.domain.Joke;
import com.niekai.life.http.HttpUtil;
import com.niekai.life.http.Util;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;

import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

public class JokeFragment extends Fragment{
	private ListView tv;
	private List<Joke> vs;
	private int index;
	Handler h = new Handler() {
		@Override
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 5:
				vs = (List<Joke>) msg.obj;
				tv.setAdapter(new JokeAdapter(getActivity(), vs));
				break;
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		new Thread(new Mythread()).start();
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.joke, null);
		tv = (ListView) v.findViewById(R.id.joke_listview);
		registerForContextMenu(tv);
		return v;
	}

	private class Mythread implements Runnable {

		@Override
		public void run() {
			HttpEntity str = HttpUtil.getJsonData(ConstantValue.JOKES);
			List<Joke> list = DataUtil.jsonParseForJoke(HttpUtil
					.getJsonStr(str));
			Message m = h.obtainMessage(5, list);
			h.sendMessage(m);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		String[] arr = getResources().getStringArray(R.array.social_share);
		menu.setHeaderTitle("选择要操作的方式");
		for (int i = 0; i < arr.length; i++) {
			menu.add(0, i, 0, arr[i]);
		}
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		if (item.getGroupId() == 0) {
			AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
					.getMenuInfo();
			index = menuInfo.position;
			switch (item.getItemId()) {
			case 0:
				new ImgTask().execute(vs.get(index).getPicUrl());
				break;

			default:
				break;
			}
		}
		return true;
	}

	private class ImgTask extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			return Util.getbitmap(params[0]);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			byte[] b1 = Util.bmpToByteArray(result, false);
			byte[] b2 = Util.bmpToByteArray(
					Bitmap.createScaledBitmap(result, 50, 50, false), false);

		}
	}

}