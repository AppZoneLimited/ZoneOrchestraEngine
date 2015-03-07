
package com.appzone.zone.orchestra.engine.datatypes;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * Retrieve the 'AttachedCommand' JSONObject and pass into the Constructor
 * 
 */

public class AttachedCommand {
	private ArrayList<CommandMapping> commandMappingsList;
	private String nextStepId;
	
	public AttachedCommand(JSONObject aCommand) throws JSONException {
		// TODO Auto-generated constructor stub
		this.setNextStepId(aCommand.getString("StepID"));
		this.commandMappingsList = new ArrayList<>();
		
		JSONArray commandMappings = aCommand.getJSONArray("CommandMappings");
		for(int i = 0; i < commandMappings.length(); i++){
			JSONObject cMapping = (JSONObject)commandMappings.get(i);
			this.commandMappingsList.add(new CommandMapping(cMapping));
		}
		
	}

	public ArrayList<CommandMapping> getCommandMappingsList() {
		return commandMappingsList;
	}

	public void setCommandMappingsList(ArrayList<CommandMapping> commandMappingsList) {
		this.commandMappingsList = commandMappingsList;
	}

	public String getNextStepId() {
		return nextStepId;
	}

	public void setNextStepId(String nextStepId) {
		this.nextStepId = nextStepId;
	}
}

