
package com.appzone.zone.orchestra.engine.datatypes;

import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * 
 * 
 */

public class Step {
	private String stepId, nextStepId;
	
	public Step(String stepId, JSONObject stepProcedure){
		this.setStepId(stepId);
		
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getNextStepId() {
		return nextStepId;
	}

	public void setNextStepId(String nextStepId) {
		this.nextStepId = nextStepId;
	}
	
	
	
	
}

