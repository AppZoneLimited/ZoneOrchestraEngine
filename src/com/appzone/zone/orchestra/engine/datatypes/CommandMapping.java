
package com.appzone.zone.orchestra.engine.datatypes;

import org.json.JSONException;
import org.json.JSONObject;

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
	
	public CommandMapping(JSONObject commandmapping) throws JSONException {
		// TODO Auto-generated constructor stub
		setJsonObject(commandmapping);
		setField(commandmapping.getString("Field"));
		setFieldType(commandmapping.getString("FieldType"));
		setSourceType(commandmapping.getString("SourceType"));
		setValueSource(commandmapping.getString("ValueSource"));
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
}

