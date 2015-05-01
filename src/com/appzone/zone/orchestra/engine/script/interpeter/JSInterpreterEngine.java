
package com.appzone.zone.orchestra.engine.script.interpeter;

import java.util.ArrayList;

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
	
	public String generateFunction(String functionName, String execCode, String argsString){
		String initString = "function *myFunction*(*values*){ *execCode* };";
		return initString.replace("*myFunction*", functionName).replace("*execCode*", execCode).replace("*values*", argsString);
	}
	
	public Object[] generateArgs(int length, ArrayList<Object> data){
		Object[] s = new Object[length];
		
		for(int i = 0; i < length; i++){
			s[i] = data.get(i);
		}
		
		return s;
	}
	
	public static ArrayList<Object> generateArrayList(String args){
		ArrayList<Object> aryObjs = new ArrayList<>();
		String[] values = args.split("\\,");
		
		for(String s:values){
			aryObjs.add((Object)s);
		}
		
		return aryObjs;
	}
	
}

