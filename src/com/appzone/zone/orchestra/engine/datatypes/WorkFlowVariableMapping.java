
package com.appzone.zone.orchestra.engine.datatypes;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *	"WorkflowVariablesMapping": [
            {
              "Field": "Corporate",
              "FieldType": "Entity",
              "SourceType": "Event",
              "ValueSource": "Corporate",
              "SubMappings": {}
            }
          ]
 * 
 */

public class WorkFlowVariableMapping {
	
	private ArrayList<CommandMapping> cmaps;
	
	public WorkFlowVariableMapping(JSONArray workflowmappings) throws JSONException{
		// TODO Auto-generated constructor stub
		this.cmaps = new ArrayList<>();
		for(int i = 0; i < workflowmappings.length(); i++){
			JSONObject cmap =  workflowmappings.getJSONObject(i);
			this.cmaps.add(new CommandMapping(cmap));
		}
	}
	
	public ArrayList<CommandMapping> getWorkFlowMappings() {
		return this.cmaps;
	}
}

