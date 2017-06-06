package com.niekai.life.fragement;

import java.util.List;

import org.apache.http.HttpEntity;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.niekai.life.R;
import com.niekai.life.activity.VideoActivity;
import com.niekai.life.adpater.VideoAdapter;
import com.niekai.life.data.ConstantValue;
import com.niekai.life.data.DataUtil;
import com.niekai.life.domain.Video;
import com.niekai.life.http.HttpUtil;
import com.niekai.life.http.Util;


public class VideoFragment extends Fragment implements OnItemClickListener {
	private ListView tv;
	private List<Video> vs;
	private int index;
	Handler h = new Handler() {
		@Override
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			vs = (List<Video>) msg.obj;
			tv.setAdapter(new VideoAdapter(getActivity(), vs));
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		new Thread(new Mythread()).start();
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.video, null);
		tv = (ListView) v.findViewById(R.id.video_listview);
		registerForContextMenu(tv);
		tv.setOnItemClickListener(this);
		return v;
	}

	private class Mythread implements Runnable {

		@Override
		public void run() {

			HttpEntity str = HttpUtil.getJsonData(ConstantValue.VIDEOES);
			List<Video> videos = DataUtil.jsonParseForVideo(HttpUtil
					.getJsonStr(str));
			Message m = h.obtainMessage(20, videos);
			h.sendMessage(m);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent it = new Intent();
		it.setClass(getActivity().getBaseContext(), VideoActivity.class);
		it.putExtra("videoUrl", vs.get(position).getVideoUrl());
		startActivity(it);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		String[] arr = getResources().getStringArray(R.array.social_share);
		menu.setHeaderTitle("选择要操作的方式");
		for (int i = 0; i < arr.length; i++) {
			menu.add(2, i, 0, arr[i]);
		}
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getGroupId() == 2) {
			AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
					.getMenuInfo();
			index = menuInfo.position;
			switch (item.getItemId()) {
			case 0:
				new ImgTask().execute(vs.get(index).getCover());
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
			byte[] b2 = Util.bmpToByteArray(
					Bitmap.createScaledBitmap(result, 50, 50, false), false);

		}
	}
}
