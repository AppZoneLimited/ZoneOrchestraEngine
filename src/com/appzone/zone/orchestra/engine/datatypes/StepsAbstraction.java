
package com.appzone.zone.orchestra.engine.datatypes;


import java.util.HashMap;

import org.json.JSONObject;

import android.util.Log;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public class StepsAbstraction {
	String[] stepIds;
	HashMap<String, Step> idToStep = new HashMap<>();
	int sizeOfSteps;
	
	public StepsAbstraction(JSONObject jo){
		// TODO Auto-generated constructor stub
		sizeOfSteps = jo.length();
		Log.e("stepsName", jo.names().toString());
		
	}
	
	
	
	
}

