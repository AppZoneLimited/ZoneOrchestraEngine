
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
	
	private JSONArray eventKeys;
	private ArrayList<AttachedCommand> atCommand;
	private String name;
	private WorkFlowVariableMapping workflowMap;
	
	public Events(JSONObject events) throws JSONException {
		// TODO Auto-generated constructor stub
		eventKeys = events.names();
		atCommand = new ArrayList<>();
		
		if (eventKeys != null){
			for (int i = 0; i < eventKeys.length() ; i ++){
				String eventKey = (String)eventKeys.getString(i);
				JSONObject eventObject = events.getJSONObject(eventKey);
				Log.d("eventObject", eventObject.toString(4));
				AttachedCommand atC = new AttachedCommand(eventObject.getJSONObject("AttachedCommands"));
				this.atCommand.add(atC);
				JSONArray workflowmappings = eventObject.getJSONArray("WorkflowVariablesMapping");
				this.setWorkflowMap(new WorkFlowVariableMapping(workflowmappings));
			}
		}

	}

	public ArrayList<AttachedCommand> getAttachedCommands() {
		return atCommand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorkFlowVariableMapping getWorkflowMap() {
		return workflowMap;
	}

	public void setWorkflowMap(WorkFlowVariableMapping workflowMap) {
		this.workflowMap = workflowMap;
	}

}

