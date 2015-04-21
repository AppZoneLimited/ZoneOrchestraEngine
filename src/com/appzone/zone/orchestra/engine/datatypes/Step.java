package com.appzone.zone.orchestra.engine.datatypes;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.appzone.zone.orchestra.engine.interfaces.StepResultCallback;




/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * Data pulled from steps
 * 
 */

public class Step {
	private String stepId, nextStepId, serviceName;
	private JSONObject stepResult;
	private JSONObject prevStepResult;
	private Events events;
	private ServiceType serviceType;
	private ArrayList<Fields> sfields;
	private StepsAbstraction stepAbstract;
	private CommandName commandName;
	private JSONObject commandNameJson;
	private JSONObject eventJSON;

	public Step(String stepId, JSONObject stepProcedure, ArrayList<Fields> sfields, StepsAbstraction stepsAbstraction) throws JSONException {
		this.setStepId(stepId);
		this.setStepAbstract(stepsAbstraction);
		this.setFields(sfields);
		
		String commandNameJson = stepProcedure.optString("CommandName");
		this.setCommandName(new CommandName(commandNameJson));
		this.setServiceName(stepProcedure.optString("ServiceName"));
		
		if(stepProcedure.optJSONObject("Events") == null){
			this.setNextStepId(null);
			this.setEvents(null);
			this.setEventJSON(null);
		}else{
			this.setEvents(new Events(stepProcedure.optJSONObject("Events")));
			this.setEventJSON(stepProcedure.optJSONObject("Events"));
			if(this.getEvents().getAttachedCommands().size() > 0){
				this.setNextStepId(this.getEvents().getAttachedCommands().get(0)
						.getNextStepId());
			}
		}
		this.setPrevStepResult(null);
	}

	public Step setFields(ArrayList<Fields> sfields){
		this.sfields = sfields;
		return this;
	}

	public CommandName getCommandName() {
		return commandName;
	}

	public void setCommandName(CommandName commandName) {
		this.commandName = commandName;
	}

	public ArrayList<Fields> getFields(){
		return this.sfields;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

	public Events getEvents() {
		return events;
	}

	public void setEvents(Events events) {
		this.events = events;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * 
	 * @return JSONObject of Commands. Single command objects can be parsed using the CommandMapping class
	 * @throws JSONException
	 */
	public JSONObject getCommands() throws JSONException {
		JSONObject data = new JSONObject();
		ArrayList<AttachedCommand> attachedCommands = this.getEvents().getAttachedCommands();

		if (attachedCommands.size() > 0){
			for(int i = 0; i < attachedCommands.size(); i++){
				AttachedCommand attCom = attachedCommands.get(i);
				ArrayList<CommandMapping> commandMappings = attCom.getCommandMappingsList();
				for(CommandMapping cmap:commandMappings){
					data.put(cmap.getField(), cmap.getJson());
				}
			}
			return data;
		}else{
			return data;
		}
	}

	public JSONObject getData() throws JSONException {
		JSONObject data = new JSONObject();
		ArrayList<AttachedCommand> attachedCommands = this.getEvents().getAttachedCommands();

		if (attachedCommands.size() > 0){
			for(int i = 0; i < attachedCommands.size(); i++){
				AttachedCommand attCom = attachedCommands.get(i);
				ArrayList<CommandMapping> commandMappings = attCom.getCommandMappingsList();
				for(CommandMapping cmap:commandMappings){
					data.put(cmap.getField(), cmap.getValueSource());
				}
			}
			return data;
		}else{
			return data;
		}
	}

	public JSONObject getStepResult() {
		return stepResult;
	}

	public JSONObject getPrevStepResult() {
		return prevStepResult;
	}

	public void setPrevStepResult(JSONObject prevStepResult) {
		this.prevStepResult = prevStepResult;
	}

	public void setStepResult(JSONObject stepResult) {
		this.stepResult = stepResult;
	}
	
	

	public void setStepResultCallBack(JSONObject stepResult, StepsAbstraction sa, StepResultCallback stepResultCallback) {
		this.setStepResult(stepResult);
		this.setStepAbstract(sa);
		stepResultCallback.onStepResult(sa, this, this.stepResult);
		Step nextStep = sa.getNextStep();
		if(nextStep != null){
			nextStep.setPrevStepResult(stepResult);
			stepResultCallback.onGetNextStep(nextStep);
		}else{
			return;
		}
	}

	public StepsAbstraction getStepAbstract() {
		return stepAbstract;
	}

	public void setStepAbstract(StepsAbstraction stepAbstract) {
		this.stepAbstract = stepAbstract;
	}

	public JSONObject getCommandNameJson() {
		return commandNameJson;
	}

	public void setCommandNameJson(JSONObject commandNameJson) {
		this.commandNameJson = commandNameJson;
	}

	public JSONObject getEventJSON() {
		return eventJSON;
	}

	public void setEventJSON(JSONObject eventJSON) {
		this.eventJSON = eventJSON;
	}


}
