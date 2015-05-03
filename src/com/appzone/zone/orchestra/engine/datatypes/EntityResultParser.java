package com.appzone.zone.orchestra.engine.datatypes;

import org.json.JSONObject;

import android.util.Log;

public class EntityResultParser {
	private String eventName;
	private EntityEventData eeD;
	
	private String TAG = this.getClass().getSimpleName();

	public EntityResultParser(JSONObject resultJson) {
		
		Log.e(TAG+"EntityResultParserJSON", resultJson.toString());
		
		setEventName(resultJson.optString("EventName", null));

		try {
			setEeD(new EntityEventData(resultJson.optJSONObject("EventData")));
			//Log.e(TAG+"eeD", getEeD().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public EntityEventData getEeD() {
		return eeD;
	}

	public void setEeD(EntityEventData eeD) {
		this.eeD = eeD;
	}
}
