
package com.appzone.zone.orchestra.engine.datatypes;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public class Contacts {
	private String contactName, contactNumber;
	public static final String NAME_KEY = "name";
	public static final String NUMBER_KEY = "number";
	
	public Contacts(String contactName, String contactNumber){
		setContactName(contactName);
		setContactNumber(contactNumber);
	}
	
	public Contacts(JSONObject contactJson) throws JSONException{
		setContactName(contactJson.getString(NAME_KEY));
		setContactNumber(contactJson.getString(NUMBER_KEY));
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public JSONObject makeJSON() throws JSONException{
		JSONObject jo = new JSONObject();
		jo.put(NAME_KEY, getContactName());
		jo.put(NUMBER_KEY, getContactNumber());
		return jo;
	}
}

