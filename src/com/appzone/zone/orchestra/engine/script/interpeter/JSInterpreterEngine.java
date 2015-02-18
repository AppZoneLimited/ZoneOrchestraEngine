
package com.appzone.zone.orchestra.engine.script.interpeter;

import com.evgenii.jsevaluator.JsEvaluator;
import android.content.Context;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public class JSInterpreterEngine {

	Context ctx;
	
	public JSInterpreterEngine(Context ctx){
		this.ctx = ctx;
	}
	
	public JsEvaluator evaluateScript(){
		JsEvaluator jsEvaluator = new JsEvaluator(this.ctx);
		return jsEvaluator;
	}
	
}

