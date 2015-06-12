package com.appzone.zone.publicaccess.misc;

import java.util.Iterator;

import org.json.JSONObject;

public class Miscellaneous {
	public Miscellaneous() {
		// TODO Auto-generated constructor stub
	}
	
	public String getObjectStringFromJsonObject(JSONObject js){
		Iterator<String> keys = js.keys();
		String result = null;
		while(keys.hasNext()){
			result = js.optString(keys.next());
		}
		return result;
	}
}
