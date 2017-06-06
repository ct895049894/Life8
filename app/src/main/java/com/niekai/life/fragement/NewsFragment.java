package com.niekai.life.fragement;

import java.util.List;

import org.apache.http.HttpEntity;

import com.niekai.life.R;
import com.niekai.life.activity.InfoActivity;
import com.niekai.life.adpater.InfoAdapter;
import com.niekai.life.data.ConstantValue;
import com.niekai.life.data.DataUtil;
import com.niekai.life.domain.Information;
import com.niekai.life.http.HttpUtil;
import com.niekai.life.http.Util;


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
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public class NewsFragment extends Fragment implements OnItemClickListener {
	private ListView tv;
	private List<Information> vs;
	private int index;
	Handler h = new Handler() {
		@Override
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			vs = (List<Information>) msg.obj;
			tv.setAdapter(new InfoAdapter(getActivity(), vs));
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
		View v = inflater.inflate(R.layout.info, null);
		tv = (ListView) v.findViewById(R.id.info_listview);
		registerForContextMenu(tv);
		tv.setOnItemClickListener(this);
		return v;
	}

	private class Mythread implements Runnable {

		@Override
		public void run() {

			HttpEntity str = HttpUtil.getJsonData(ConstantValue.INFORMATIONS);
			List<Information> list = DataUtil.jsonParseForInfo(HttpUtil
					.getJsonStr(str));
			Message m = h.obtainMessage(10, list);
			h.sendMessage(m);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent it = new Intent();
		it.setClass(getActivity().getBaseContext(), InfoActivity.class);
		it.putExtra("url", vs.get(position).getUrl());
		startActivity(it);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		String[] arr = getResources().getStringArray(R.array.social_share);
		menu.setHeaderTitle("选择要操作的方式");
		for (int i = 0; i < arr.length; i++) {
			menu.add(1, i, 0, arr[i]);
		}
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		if (item.getGroupId() == 1) {
			AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
					.getMenuInfo();
			index = menuInfo.position;
			switch (item.getItemId()) {
			case 0:
				new ImgTask().execute(vs.get(index).getImgSrc());
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
