package com.appzone.zone.orchestra.engine.datatypes;

import org.json.JSONObject;

public class EntityResultParser {
	private String eventName;
	private EntityEventData eeD;


	public EntityResultParser(JSONObject resultJson) {
		
		
		setEventName(resultJson.optString("EventName", null));

		try {
			Object s = resultJson.get("EventData");
			JSONObject sm = null;
			if(s instanceof String){
				sm = new JSONObject(s.toString());
			}else{
				sm = resultJson.optJSONObject("EventData");
			}
			
			setEeD(new EntityEventData(sm));
			//Log.e(TAG+"eeD", resultJson.optJSONObject("EventData").toString());
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
