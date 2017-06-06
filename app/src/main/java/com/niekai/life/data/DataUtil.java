package com.niekai.life.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.niekai.life.domain.Information;
import com.niekai.life.domain.Joke;
import com.niekai.life.domain.Video;

public class DataUtil {

	public static List<Video> jsonParseForVideo(String jsonStr) {

		List<Video> list = new ArrayList<Video>();
		try {
			JSONObject obj = new JSONObject(jsonStr);
			JSONArray arr = obj.getJSONArray("V9LG4CHOR");
			for (int i = 0; i < arr.length(); i++) {
				JSONObject json = arr.getJSONObject(i);
				Video v = new Video(json.getString("title"),
						json.getString("description"), json.getString("cover"),
						json.getString("mp4Hd_url"));
				list.add(v);
			}
		} catch (Exception e) {
		}
		return list;
	}

	public static List<Information> jsonParseForInfo(String jsonStr) {

		List<Information> list = new ArrayList<Information>();
		try {
			JSONObject obj = new JSONObject(jsonStr);
			JSONArray arr = obj.getJSONArray("T1348648037603");
			for (int i = 1; i < arr.length(); i++) {
				JSONObject json = arr.getJSONObject(i);
				Information v = new Information(json.getString("title"),
						json.getString("digest"), json.getString("imgsrc"),
						json.getString("url"));
				list.add(v);
			}
		} catch (Exception e) {
		}
		return list;
	}

	public static List<Joke> jsonParseForJoke(String jsonStr) {

		List<Joke> list = new ArrayList<Joke>();
		try {
			JSONArray arr = new JSONArray(jsonStr);
			for (int i = 0; i < arr.length(); i++) {
				JSONObject json = arr.getJSONObject(i);
				Joke j = new Joke(json.getString("author"),
						json.getString("content"), json.getString("picUrl"));
				list.add(j);
			}
		} catch (Exception e) {
		}
		return list;
	}

}
