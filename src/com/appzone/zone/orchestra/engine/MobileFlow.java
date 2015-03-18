package com.appzone.zone.orchestra.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.appzone.zone.orchestra.engine.R;
import com.appzone.zone.orchestra.engine.datatypes.Fields;
import com.appzone.zone.orchestra.engine.datatypes.StepsAbstraction;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ] This is the flow data
 *         structure
 * 
 */

public class MobileFlow {

	JSONObject mobileFlowJsonObject;
	private StepsAbstraction stepAbstraction;
	private Fields fields;
	private String flowName;
	private ArrayList<String> variables;

	public MobileFlow(Context ctx) {
		// TODO Auto-generated constructor stub
		try {
			mobileFlowJsonObject = new JSONObject(loadJson(ctx));

			setFlowName(mobileFlowJsonObject.getString("Name"));

			JSONArray vars = mobileFlowJsonObject.getJSONArray("Variables");
			ArrayList<String> varsArray = new ArrayList<>();

			for (int i = 0; i < vars.length(); i++) {
				String var = (String) vars.get(i);
				varsArray.add(var);
			}

			setVariables(varsArray);

			String initialStepId = mobileFlowJsonObject
					.getString("InitialStepID");
			JSONObject stepsObject = mobileFlowJsonObject
					.getJSONObject("Steps");
			setstepAbstractionion(new StepsAbstraction(stepsObject,
					initialStepId));

			setFields(new Fields(
					mobileFlowJsonObject.getJSONObject("InitialFields")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JSONObject returnJSON() {
		return this.mobileFlowJsonObject;
	}

	public MobileFlow(String stringFlow) {
		// TODO Auto-generated constructor stub
		try {
			mobileFlowJsonObject = new JSONObject(stringFlow);

			setFlowName(mobileFlowJsonObject.getString("Name"));

			JSONArray vars = mobileFlowJsonObject.getJSONArray("Variables");
			ArrayList<String> varsArray = new ArrayList<>();

			for (int i = 0; i < vars.length(); i++) {
				String var = (String) vars.get(i);
				varsArray.add(var);
			}

			setVariables(varsArray);

			String initialStepId = mobileFlowJsonObject
					.getString("InitialStepID");
			JSONObject stepsObject = mobileFlowJsonObject
					.getJSONObject("Steps");
			setstepAbstractionion(new StepsAbstraction(stepsObject,
					initialStepId));

			setFields(new Fields(
					mobileFlowJsonObject.getJSONObject("InitialFields")));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Fields getFields() {
		return fields;
	}

	public void setFields(Fields fields) {
		this.fields = fields;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public ArrayList<String> getVariables() {
		return variables;
	}

	public void setVariables(ArrayList<String> variables) {
		this.variables = variables;
	}

	/*
	 * This is a method for loading string from text file
	 */
	private String loadJson(Context ctx) {
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

	public StepsAbstraction getstepAbstractionion() {
		return stepAbstraction;
	}

	public void setstepAbstractionion(StepsAbstraction stepAbstraction) {
		this.stepAbstraction = stepAbstraction;
	}

}
