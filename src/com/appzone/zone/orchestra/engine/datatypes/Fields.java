
package com.appzone.zone.orchestra.engine.datatypes;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * Data pulled from 'InitialFields'
 * 
 */

public class Fields {
	
	private String field, fieldType, sourceType, valueSource;

	private HashMap<String, SubMappings> fieldSubmappings;
	
	public Fields(JSONObject object) throws JSONException {
		// TODO Auto-generated constructor stub
		setField(object.getString("Field"));
		setFieldType(object.getString("FieldType"));
		setSourceType(object.getString("SourceType"));
		setValueSource(object.getString("ValueSource"));
		
		HashMap<String, SubMappings> initSubs = new HashMap<String, Fields.SubMappings>();
		JSONObject subMappings = object.getJSONObject("SubMappings");
		JSONArray mappingsIds = subMappings.names();
		
		for(int i = 0; i < mappingsIds.length(); i++){
			String id = (String)mappingsIds.get(i);
			JSONObject subMapping = subMappings.getJSONObject(id);
			initSubs.put(id, new SubMappings(subMapping, id));
		}
		setFieldSubmappings(initSubs);
	}
	
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getValueSource() {
		return valueSource;
	}

	public void setValueSource(String valueSource) {
		this.valueSource = valueSource;
	}

	public HashMap<String, SubMappings> getFieldSubmappings() {
		return fieldSubmappings;
	}

	public void setFieldSubmappings(HashMap<String, SubMappings> fieldSubmappings) {
		this.fieldSubmappings = fieldSubmappings;
	}
	
	public class SubMappings{
		/*
		 * "Field": "ID",
          "FieldType": "Default",
          "SourceType": "Event",
          "ValueSource": null,
          "SubMappings": {}
		 */
		private String field, fieldType, sourceType, valueSource;
		private String keyId;
		
		public SubMappings(JSONObject object, String keyId) throws JSONException {
			// TODO Auto-generated constructor stub
			setKeyId(keyId);
			setField(object.getString("Field"));
			setFieldType(object.getString("FieldType"));
			setSourceType(object.getString("SourceType"));
			setValueSource(object.getString("ValueSource"));
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getFieldType() {
			return fieldType;
		}

		public void setFieldType(String fieldType) {
			this.fieldType = fieldType;
		}

		public String getSourceType() {
			return sourceType;
		}

		public void setSourceType(String sourceType) {
			this.sourceType = sourceType;
		}

		public String getValueSource() {
			return valueSource;
		}

		public void setValueSource(String valueSource) {
			this.valueSource = valueSource;
		}

		public String getKeyId() {
			return keyId;
		}

		public void setKeyId(String keyId) {
			this.keyId = keyId;
		}
		
	}
}

