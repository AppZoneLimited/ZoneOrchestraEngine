
package com.appzone.zone.orchestra.engine.interfaces;

import org.json.JSONObject;

import com.appzone.zone.orchestra.engine.datatypes.Step;
import com.appzone.zone.orchestra.engine.datatypes.StepsAbstraction;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public abstract interface StepResultCallback {
	
	public void onStepResult(StepsAbstraction stepAbstraction, Step s, JSONObject result);
	public void onGetNextStep(Step nextStep);
}

