
package com.appzone.zone.orchestra.engine.datatypes;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public class Events {
	
	JSONArray eventKeys;
	private ArrayList<AttachedCommand> atCommand;
	
	public Events(JSONObject events) throws JSONException {
		// TODO Auto-generated constructor stub
		eventKeys = events.names();
		//Log.e("eventKeys", eventKeys.toString());
		atCommand = new ArrayList<>();
		if (eventKeys != null){
			for (int i = 0; i < eventKeys.length() ; i ++){
				String eventKey = (String)eventKeys.getString(i);
				JSONObject eventObject = events.getJSONObject(eventKey);
				Log.e("eventObject", eventObject.names().toString());
				AttachedCommand atC = new AttachedCommand(eventObject.getJSONObject("AttachedCommands"));
				this.atCommand.add(atC);
			}
		}
		
	}

	public ArrayList<AttachedCommand> getAttachedCommands() {
		return atCommand;
	}

}

