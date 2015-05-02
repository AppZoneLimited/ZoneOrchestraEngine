package com.appzone.zone.orchestra.engine.datatypes;

import org.json.JSONObject;

public class EntityResultParser {
	private String eventName;
	private EntityEventData eeD;

	public EntityResultParser(JSONObject resultJson) {
		setEventName(resultJson.optString("EventName", null));

		try {
			setEeD(new EntityEventData(resultJson.optJSONObject("EventData")));
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
