package com.appzone.zone.orchestra.engine.datatypes;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * Data pulled from steps
 * 
 */

public class Step {
	private String stepId, nextStepId, commandName;
	private Events events;
	private ServiceType serviceType;

	public Step(String stepId, JSONObject stepProcedure) throws JSONException {
		this.setStepId(stepId);
		this.setCommandName(stepProcedure.getString("CommandName"));
		this.setEvents(new Events(stepProcedure.getJSONObject("Events")));
		this.setNextStepId(this.getEvents().getAttachedCommands().get(0)
				.getNextStepId());
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

}
