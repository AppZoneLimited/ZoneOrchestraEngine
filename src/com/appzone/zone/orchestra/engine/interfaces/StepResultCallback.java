
package com.appzone.zone.orchestra.engine.interfaces;

import org.json.JSONObject;

import com.appzone.zone.orchestra.engine.datatypes.Step;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public interface StepResultCallback {
	public void onStepResult(Step step, JSONObject result);
}

