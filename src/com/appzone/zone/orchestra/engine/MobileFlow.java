
package com.appzone.zone.orchestra.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.appzone.zone.orchestra.engine.R;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * This is the flow data structure
 * 
 */

public class MobileFlow {

	JSONObject jObi;
	
	public MobileFlow(Context ctx) {
		// TODO Auto-generated constructor stub
		try {
			jObi = new JSONObject(loadJson(ctx));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public JSONObject returnJSON(){
		return this.jObi;
	}
	
	public MobileFlow(String stringFlow) {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 *This is a method for loading string from text file
	 */
	private String loadJson(Context ctx){
		String json = null;
		InputStream is = ctx.getResources().openRawResource(R.raw.dejavujson);
		Writer writer = new StringWriter();
		char[] buffer = new char[1024];
		Reader reader = null;
		try {
		    
			try {
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    int n;
		    try {
				while ((n = reader.read(buffer)) != -1) {
				    writer.write(buffer, 0, n);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
		    try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		json = writer.toString();
		return json;
	}
	
	
}

