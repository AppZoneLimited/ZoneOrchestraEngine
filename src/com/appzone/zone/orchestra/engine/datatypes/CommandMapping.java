
package com.appzone.zone.orchestra.engine.datatypes;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.appzone.zone.orchestra.engine.datatypes.Fields.SubMappings;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * {
                  "Field": "Name",
                  "FieldType": "Default",
                  "SourceType": "Event",
                  "ValueSource": null,
                  "SubMappings": {}
   }
 * 
 */

public class CommandMapping {
	
	private JSONObject jsonVariable;
	private String field, fieldType, sourceType, valueSource;
	private int type, id;
	private HashMap<String, SubMappings> fieldSubmappings;
	private boolean hasSubmapping;
	
	public CommandMapping(JSONObject commandmapping) throws JSONException {
		// TODO Auto-generated constructor stub
		setJsonObject(commandmapping);
		setField(commandmapping.optString("Field"));
		setFieldType(commandmapping.optString("FieldType"));
		setSourceType(commandmapping.optString("SourceType"));
		setValueSource(commandmapping.optString("ValueSource"));
		setType(commandmapping.optInt("type"));
		setId(commandmapping.optInt("id"));
		
		HashMap<String, SubMappings> initSubs = new HashMap<String, Fields.SubMappings>();
		try{
			JSONObject subMappings = commandmapping.getJSONObject("SubMappings");
			JSONArray mappingsIds = subMappings.names();
			for(int i = 0; i < mappingsIds.length(); i++){
				String id = (String)mappingsIds.get(i);
				JSONObject subMapping = subMappings.getJSONObject(id);
				initSubs.put(id, new SubMappings(subMapping, id));
			}
			
			this.setFieldSubmappings(initSubs);
		}catch(Exception m){
			//m.printStackTrace();
		}
		
		if(initSubs.size() > 0){
			setHasSubmapping(true);
		}else{
			setHasSubmapping(false);
		}
		
	}

	public boolean hasSubmapping() {
		return hasSubmapping;
	}

	public void setHasSubmapping(boolean hasSubmapping) {
		this.hasSubmapping = hasSubmapping;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JSONObject getJson() {
		return jsonVariable;
	}

	public void setJsonObject(JSONObject jsonVariable) {
		this.jsonVariable = jsonVariable;
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
}

