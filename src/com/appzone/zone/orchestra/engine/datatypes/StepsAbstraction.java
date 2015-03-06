
package com.appzone.zone.orchestra.engine.datatypes;


import java.util.HashMap;

import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public class StepsAbstraction {
	String[] stepIds;
	HashMap<String, Step> idToStep = new HashMap<>();
	
	public StepsAbstraction(JSONObject jo) {
		// TODO Auto-generated constructor stub
		int sizeOfSteps = jo.length();
		if (sizeOfSteps <= 0){
			//Handle no steps
		}
	}
	
}

