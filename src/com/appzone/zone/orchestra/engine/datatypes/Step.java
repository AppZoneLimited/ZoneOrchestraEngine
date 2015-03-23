package com.appzone.zone.orchestra.engine.datatypes;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * Data pulled from steps
 * 
 */

public class Step {
	private String stepId, nextStepId, commandName, serviceName;
	private Events events;
	private ServiceType serviceType;
	private ArrayList<Fields> sfields;

	public Step(String stepId, JSONObject stepProcedure, ArrayList<Fields> sfields) throws JSONException {
		this.setStepId(stepId);
		this.setCommandName(stepProcedure.getString("CommandName"));
		this.setServiceName(stepProcedure.getString("ServiceName"));
		this.setEvents(new Events(stepProcedure.getJSONObject("Events")));
		this.setFields(sfields);
		if(this.getEvents().getAttachedCommands().size() > 0){
			this.setNextStepId(this.getEvents().getAttachedCommands().get(0)
					.getNextStepId());
		}
	}

	public Step setFields(ArrayList<Fields> sfields){
		this.sfields = sfields;
		return this;
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

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
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
	 * @return JSONObject of Commands. SIngle command objects can be parsed using the CommandMapping class
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

}
