package com.appzone.zone.orchestra.engine.datatypes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventData{
	
	private JSONArray eventDataKeys;
	private JSONObject eventData;
	
	public EventData(JSONObject eventData) throws JSONException{
		if (eventData != null) {
			this.eventData = eventData;
			this.eventDataKeys = eventData.names();
		}
	}
	
	public String getEventDataValueByKey(String eventDataKey){
		String eventDataValue = eventData.optString(eventDataKey, null);
		return eventDataValue;
	}
	
	public JSONArray getEventDataKeys(){
		return this.eventDataKeys;
	}
}