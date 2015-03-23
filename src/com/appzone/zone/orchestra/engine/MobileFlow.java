package com.appzone.zone.orchestra.engine;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.appzone.zone.orchestra.engine.datatypes.Fields;
import com.appzone.zone.orchestra.engine.datatypes.StepsAbstraction;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ] This is the flow data
 *         structure
 * 
 */

public class MobileFlow {

	private JSONObject mobileFlowJsonObject;
	private StepsAbstraction stepAbstraction;
	private ArrayList<Fields> fields;
	private String flowName;
	private ArrayList<String> variables;
	

	public JSONObject getMobileFlowJson() {
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

			this.setVariables(varsArray);

			String initialStepId = mobileFlowJsonObject
					.getString("InitialStepID");
			JSONObject stepsObject = mobileFlowJsonObject
					.getJSONObject("Steps");
			
			JSONArray fieldsArray = mobileFlowJsonObject.getJSONArray("InitialFields");
			ArrayList<Fields> sfields = new ArrayList<>();
			
			for(int i = 0; i < fieldsArray.length(); i++){
				JSONObject jField = fieldsArray.getJSONObject(i);
				sfields.add(new Fields(jField));
			}
			
			setFields(sfields);
			
			setstepAbstractionion(new StepsAbstraction(stepsObject,
					initialStepId, getFields()));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Fields> getFields() {
		return fields;
	}

	public void setFields(ArrayList<Fields> fields) {
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


	public StepsAbstraction getstepAbstractionion() {
		return stepAbstraction;
	}

	public void setstepAbstractionion(StepsAbstraction stepAbstraction) {
		this.stepAbstraction = stepAbstraction;
	}

}
