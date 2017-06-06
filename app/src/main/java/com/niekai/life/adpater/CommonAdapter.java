package com.niekai.life.adpater;

import java.util.List;

import com.niekai.life.domain.Information;
import com.niekai.life.domain.Joke;
import com.niekai.life.domain.Video;
import com.niekai.life.http.HttpUtil;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CommonAdapter extends BaseAdapter {
	private Context context;
	@SuppressWarnings("rawtypes")
	private List list;
	private int resourceId;
	private int tv_id1;
	private int tv_id2;
	private int img_id;

	public <T> CommonAdapter(Context context, List<T> list, int resourceId,
			int tv_id1, int tv_id2, int img_id) {
		this.context = context;
		this.list = list;
		this.resourceId = resourceId;
		this.tv_id1 = tv_id1;
		this.tv_id2 = tv_id2;
		this.img_id = img_id;
	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public Object getItem(int position) {

		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater la = LayoutInflater.from(context);
		MyView vh = null;
		if (convertView == null) {
			vh = new MyView();
			convertView = la.inflate(resourceId, null);
			vh.tv1 = (TextView) convertView.findViewById(tv_id1);
			vh.tv2 = (TextView) convertView.findViewById(tv_id2);
			vh.image = (ImageView) convertView.findViewById(img_id);
			convertView.setTag(vh);
		} else {
			vh = (MyView) convertView.getTag();
		}
		Object obj = list.get(position);
		if (obj instanceof Video) {
			Video v = (Video) obj;
			vh.tv1.setText(v.getTitle());
			vh.tv2.setText(v.getDescription());
			asyncImageLoad(vh.image, v.getCover());
		}
		if (obj instanceof Information) {
			Information v = (Information) obj;
			vh.tv1.setText(v.getTitle());
			vh.tv2.setText(v.getDigest());
			asyncImageLoad(vh.image, v.getImgSrc());
		}
		if (obj instanceof Joke) {
			Joke v = (Joke) obj;
			vh.tv1.setText(v.getAuthor());
			vh.tv2.setText(v.getContent());
			asyncImageLoad(vh.image, v.getPicUrl());
		}

		return convertView;
	}

	private class MyView {
		TextView tv1;
		TextView tv2;
		ImageView image;
	}

	private void asyncImageLoad(ImageView imageView, String path) {
		AsyncImageTask asyncImageTask = new AsyncImageTask(imageView);
		asyncImageTask.execute(path);

	}

	private class AsyncImageTask extends AsyncTask<String, Integer, Uri> {

		private ImageView img;

		public AsyncImageTask(ImageView img) {
			this.img = img;
		}

		@Override
		protected Uri doInBackground(String... params) {
			try {
				return HttpUtil.getImage(context.getExternalCacheDir(),
						params[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Uri result) {
			if (img != null)
				img.setImageURI(result);
		}
	}

}
