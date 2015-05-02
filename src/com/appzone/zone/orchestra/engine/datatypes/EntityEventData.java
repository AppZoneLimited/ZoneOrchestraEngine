package com.appzone.zone.orchestra.engine.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;

public class EntityEventData{
	private String entityName;
	private JSONArray entityEventKeys;
	private JSONArray entityEventDataKeys;
	private JSONObject entityEventData;
	private JSONObject entityData;
	
	public EntityEventData(JSONObject entityEventData) throws Exception {
		// TODO Auto-generated constructor stub
		if(entityEventData != null){
			this.setEntityEventData(entityEventData);
			this.entityEventKeys = entityEventData.names();
			this.setEntityName(this.entityEventKeys.getString(0));
			this.setEntityData(entityEventData.getJSONObject(getEntityName()));
		}
	}
	
	public String getEntityEventDataValueByKey(String entityEventDataKey){
		String entityEventDataValue = getEntityData().optString(entityEventDataKey, null);
		return entityEventDataValue;
	}
	
	public JSONArray getEntityEventKeys(){
		return this.entityEventKeys; 
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public JSONArray getEntityEventDataKeys() {
		return entityEventDataKeys;
	}

	public void setEntityEventDataKeys(JSONArray entityEventDataKeys) {
		this.entityEventDataKeys = entityEventDataKeys;
	}

	public JSONObject getEntityEventData() {
		return entityEventData;
	}

	public void setEntityEventData(JSONObject entityEventData) {
		this.entityEventData = entityEventData;
	}



	public JSONObject getEntityData() {
		return entityData;
	}



	public void setEntityData(JSONObject entityData) {
		this.entityData = entityData;
	}
	
}