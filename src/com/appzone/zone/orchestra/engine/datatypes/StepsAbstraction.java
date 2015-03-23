
package com.appzone.zone.orchestra.engine.datatypes;


import java.util.ArrayList;
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

	private HashMap<String, Step> idToStep = new HashMap<>();
	int sizeOfSteps;
	private String initialStepId;
	private Step initialStep, nextStep;
	private ArrayList<Fields> sfields;

	public StepsAbstraction(JSONObject jo, String initialStepId, ArrayList<Fields> sfields) throws JSONException{
		// TODO Auto-generated constructor stub
		JSONArray stepsIdArray = jo.names();
		this.sizeOfSteps = stepsIdArray.length();
		this.initialStepId = initialStepId;
        this.setFields(sfields);
		
        for(int i = 0; i < sizeOfSteps; i++){
			String id = (String)stepsIdArray.getString(i);
			Step s = new Step(id, jo.getJSONObject(id), getFields());
			idToStep.put(id, s);
		}
		
		initialStep = (Step)idToStep.get(this.initialStepId);
	}
	
	public StepsAbstraction setFields(ArrayList<Fields> sfields){
		this.sfields = sfields;
		return this;
	}
	
	public ArrayList<Fields> getFields(){
		return this.sfields;
	}
	
	public Step getInitialStep(){
		return initialStep;
	}
	
	public int noOfSteps(){
		return this.sizeOfSteps;
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

