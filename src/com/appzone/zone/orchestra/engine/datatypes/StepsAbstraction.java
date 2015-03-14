
package com.appzone.zone.orchestra.engine.datatypes;


import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public class StepsAbstraction {

	HashMap<String, Step> idToStep = new HashMap<>();
	int sizeOfSteps;
	String initialStepId;
	Step initialStep, nextStep;

	public StepsAbstraction(JSONObject jo, String initialStepId) throws JSONException{
		// TODO Auto-generated constructor stub
		JSONArray stepsIdArray = jo.names();
		sizeOfSteps = stepsIdArray.length();
		this.initialStepId = initialStepId;

		for(int i = 0; i < sizeOfSteps; i++){
			String id = (String)stepsIdArray.getString(i);
			Step s = new Step(id, jo.getJSONObject(id));
			idToStep.put(id, s);
		}
		
		initialStep = (Step)idToStep.get(this.initialStepId);
	}
	
	public Step getInitialStep(){
		return initialStep;
	}

	public Step getNextStep(){
		String nextStepId = initialStep.getNextStepId();
		if(nextStepId != null){
			nextStep = (Step)idToStep.get(nextStepId);
			initialStep = nextStep;
			return nextStep;
		}else{
			return null;
		}
	}



}

