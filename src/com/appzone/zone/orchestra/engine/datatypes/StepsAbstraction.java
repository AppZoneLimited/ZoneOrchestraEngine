package com.appzone.zone.orchestra.engine.datatypes;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.appzone.zone.orchestra.engine.interfaces.StepResultCallback;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public class StepsAbstraction {

	private HashMap<String, Step> idToStep = new HashMap<>();
	int sizeOfSteps;
	private String initialStepId;
	private Step initialStep, nextStep, currentStep;
	private ArrayList<Fields> sfields;
	private Step aardvark;

	public StepsAbstraction(JSONObject jo, String initialStepId,
			ArrayList<Fields> sfields) throws JSONException {
		// TODO Auto-generated constructor stub
		JSONArray stepsIdArray = jo.names();
		this.sizeOfSteps = stepsIdArray.length();
		this.initialStepId = initialStepId;
		this.setFields(sfields);

		for (int i = 0; i < sizeOfSteps; i++) {
			String id = (String) stepsIdArray.getString(i);
			Step s = new Step(id, jo.getJSONObject(id), getFields());
			idToStep.put(id, s);
		}
		aardvark = null;
		initialStep = (Step) idToStep.get(this.initialStepId);
	}

	public StepsAbstraction setFields(ArrayList<Fields> sfields) {
		this.sfields = sfields;
		return this;
	}



	public ArrayList<Fields> getFields() {
		return this.sfields;
	}

	private Step getInitialStep() {
		return initialStep;
	}

	private Step getCurrentStep() {
		return this.currentStep;
	}

	public int noOfSteps() {
		return this.sizeOfSteps;
	}

	public void setCurrentStepResult(JSONObject result){
		Step current = getCurrentStep();
		if(current != null){
			getCurrentStep().setStepResult(result);
		}
	}
	
	public void setStepResultCallBack(StepResultCallback resultCallBack){
		Step s = getNextStep();
		while(s != null){
			//s.setStepResult();
			resultCallBack.onStepResult(s, s.getStepResult());
			s = getNextStep();
		}
	}

	public Step getNextStep() {

		if (aardvark == null) {
			aardvark = getInitialStep();
			this.currentStep = aardvark;
			return aardvark;
		} else {
			String nextStepId = initialStep.getNextStepId();
			if (nextStepId != null) {
				nextStep = (Step) idToStep.get(nextStepId);
				initialStep = nextStep;
				this.currentStep = nextStep;
				return nextStep;
			} else {
				return null;
			}
		}
	}

}
