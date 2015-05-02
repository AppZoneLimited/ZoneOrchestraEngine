package com.appzone.zone.orchestra.engine;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.appzone.zone.orchestra.engine.datatypes.Fields;
import com.appzone.zone.orchestra.engine.datatypes.StepsAbstraction;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ] Data Structure for a Mobile
 *         Flow
 * 
 */

public class MobileFlow {

	private JSONObject mobileFlowJsonObject;
	private StepsAbstraction stepAbstraction;
	private ArrayList<Fields> fields;
	private String flowName;
	private ArrayList<JSONObject> variables;
	private String flowId;

	/**
	 * @param stringFlow
	 *            Creates MobileFlow object from a flowString
	 */
	public MobileFlow(String stringFlow) {
		// TODO Auto-generated constructor stub
		try {
			mobileFlowJsonObject = new JSONObject(stringFlow);
			setFlowId(mobileFlowJsonObject.optString("Id"));
			setFlowName(mobileFlowJsonObject.getString("Name"));
			JSONArray vars = mobileFlowJsonObject.getJSONArray("Variables");

			ArrayList<JSONObject> varsArray = new ArrayList<>();
			try {
				if (vars.length() > 0) {
					for (int i = 0; i < vars.length(); i++) {
						JSONObject var = (JSONObject) vars.get(i);
						if (var != null) {
							varsArray.add(var);
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

			this.setVariables(varsArray);
			String initialStepId = mobileFlowJsonObject
					.getString("InitialStepID");
			JSONObject stepsObject = mobileFlowJsonObject
					.getJSONObject("Steps");

			JSONArray fieldsArray = mobileFlowJsonObject
					.getJSONArray("InitialFields");
			ArrayList<Fields> sfields = new ArrayList<>();

			for (int i = 0; i < fieldsArray.length(); i++) {
				JSONObject jField = fieldsArray.getJSONObject(i);
				sfields.add(new Fields(jField));
			}
			setFields(sfields);
			setstepAbstractionion(new StepsAbstraction(stepsObject,
					initialStepId, getFields()));

		} catch (Exception e) {
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

	public ArrayList<JSONObject> getVariables() {
		return variables;
	}

	public void setVariables(ArrayList<JSONObject> variables) {
		this.variables = variables;
	}

	/**
	 * @return StepAbstraction object
	 */
	public StepsAbstraction getstepAbstractionion() {
		return stepAbstraction;
	}

	public void setstepAbstractionion(StepsAbstraction stepAbstraction) {
		this.stepAbstraction = stepAbstraction;
	}

	/**
	 * @return JSONOObject : MobileFlow representation as a JSONObject
	 */
	public JSONObject getMobileFlowJson() {
		return this.mobileFlowJsonObject;
	}

	/**
	 * @return String : FlowId
	 */
	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

}
