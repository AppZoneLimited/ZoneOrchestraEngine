package com.appzone.zone.orchestra.engine.datatypes;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * { "EventName":"Save Clicked", "EventData":{ "Name":"emma", "code":"2",
 * "address":"Aba"} }
 */
public class ResultParser {
	private String eventName;
	private EventData eventData;
	
	public ResultParser(JSONObject resultJson) {
		// TODO Auto-generated constructor stub
		setEventName(resultJson.optString("EventName", null));
		
		try {
			setEventData(new EventData(resultJson.optJSONObject("EventData")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public EventData getEventData() {
		return eventData;
	}

	public void setEventData(EventData eventData) {
		this.eventData = eventData;
	}
}