package com.appzone.zone.orchestra.engine.datatypes;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.appzone.zone.orchestra.engine.interfaces.StepResultCallback;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ] Data pulled from steps
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

	public Step(String stepId, JSONObject stepProcedure,
			ArrayList<Fields> sfields, StepsAbstraction stepsAbstraction)
			throws JSONException {
		this.setStepId(stepId);
		this.setStepAbstract(stepsAbstraction);
		this.setFields(sfields);

		String commandNameJson = stepProcedure.optString("CommandName");
		this.setCommandName(new CommandName(commandNameJson));
		this.setServiceName(stepProcedure.optString("ServiceName"));

		if (stepProcedure.optJSONObject("Events") == null) {
			this.setNextStepId(null);
			this.setEvents(null);
			this.setEventJSON(null);
		} else {
			this.setEvents(new Events(stepProcedure.optJSONObject("Events")));
			this.setEventJSON(stepProcedure.optJSONObject("Events"));
			if (this.getEvents().getAttachedCommands().size() > 0) {
				this.setNextStepId(this.getEvents().getAttachedCommands()
						.get(0).getNextStepId());
			}
		}
		this.setPrevStepResult(null);
	}

	public Step setFields(ArrayList<Fields> sfields) {
		this.sfields = sfields;
		return this;
	}

	public CommandName getCommandName() {
		return commandName;
	}

	public void setCommandName(CommandName commandName) {
		this.commandName = commandName;
	}

	public ArrayList<Fields> getFields() {
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
	 * @return JSONObject of Commands. Single command objects can be parsed
	 *         using the CommandMapping class
	 * @throws JSONException
	 */
	public JSONObject getCommands() throws JSONException {
		JSONObject data = new JSONObject();
		ArrayList<AttachedCommand> attachedCommands = this.getEvents()
				.getAttachedCommands();

		if (attachedCommands.size() > 0) {
			for (int i = 0; i < attachedCommands.size(); i++) {
				AttachedCommand attCom = attachedCommands.get(i);
				ArrayList<CommandMapping> commandMappings = attCom
						.getCommandMappingsList();
				for (CommandMapping cmap : commandMappings) {
					data.put(cmap.getField(), cmap.getJson());
				}
			}
			return data;
		} else {
			return data;
		}
	}

	/*
	 * but there is a commandMapping when setting the result for the steps, you
	 * are expecting a json object with this format {
	 * "EventName":"Save Clicked", "EventData":{ "Name":"emma", "code":"2",
	 * "address":"Aba"} } In the commandMapping there is a Field and ValueSource
	 * key what you are to do is check if event(from step) and EventName(from
	 * resultjson) then the field value is mapped to the valueSource on the
	 * resultjson Assuming the step has event:"Save Clicked" and "Field": "X",
	 * ValueSource"Name" the data should be: {"X":"emma"} and this data is
	 * available to the next step ie It is for next step
	 */

	public JSONObject getStepData(JSONObject prevStepResult){
		JSONObject data = new JSONObject();
		
		ArrayList<AttachedCommand> attachedCommands = this.getEvents()
				.getAttachedCommands();
		
		if (getStepResult() != null) {
			ResultParser result = new ResultParser(prevStepResult);
			try {
				Log.e("EventKeys", this.getEvents().getEventKeysArrayList().toString());
				Log.e("EventKeyName", result.getEventName());
				if(this.getEvents().getEventKeysArrayList().contains(result.getEventName())){
					Log.e("EventFound", true+"");
					if (attachedCommands.size() > 0) {
						Log.e("AttachedCommandLength", attachedCommands.size()+"");
						for (int i = 0; i < attachedCommands.size(); i++) {
							AttachedCommand attCom = attachedCommands.get(i);
							ArrayList<CommandMapping> commandMappings = attCom
									.getCommandMappingsList();
							Log.e("CommandMappingsLength", commandMappings.size()+"");
							for (CommandMapping cmap : commandMappings) {
								Log.e(cmap.getField()+"", cmap.getValueSource()+ " : "+result.getEventData().getEventDataValueByKey(cmap.getValueSource()));
								data.put(cmap.getField(), result.getEventData().getEventDataValueByKey(cmap.getValueSource()));
							}
						}
				}else{
					Log.e("Error", "Not Attached Command");
				}
}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return data;
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

	public void setStepResultCallBack(JSONObject stepResult,
			StepsAbstraction sa, StepResultCallback stepResultCallback) {
		this.setStepResult(stepResult);
		this.setStepAbstract(sa);
		stepResultCallback.onStepResult(sa, this, this.stepResult);
		Step nextStep = sa.getNextStep();
		if (nextStep != null) {
			nextStep.setPrevStepResult(stepResult);
			Log.e("CurrentStepResult", stepResult.toString());
			stepResultCallback.onGetNextStep(nextStep, getStepData(nextStep.getPrevStepResult()));
		} else {
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

	/*
	 * { "EventName":"Save Clicked", "EventData":{ "Name":"emma", "code":"2",
	 * "address":"Aba"} }
	 */
	public static class ResultParser {
		private String eventName;
		private EventData eventData;
		
		public ResultParser(JSONObject resultJson) {
			// TODO Auto-generated constructor stub
			setEventName(resultJson.optString("EventName", null));
			
			try {
				setEventData(new EventData(resultJson.optJSONObject("EventData")));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public String getEventName() {
			return eventName;
		}

		public void setEventName(String eventName) {
			this.eventName = eventName;
		}

		public EventData getEventData() {
			return eventData;
		}

		public void setEventData(EventData eventData) {
			this.eventData = eventData;
		}

		public static class EventData{
			
			private JSONArray eventDataKeys;
			private JSONObject eventData;
			
			public EventData(JSONObject eventData) throws JSONException{
				if (eventData != null) {
					this.eventData = eventData;
					this.eventDataKeys = eventData.names();
				}
			}
			
			public String getEventDataValueByKey(String eventDataKey){
				String eventDataValue = eventData.optString(eventDataKey, null);
				return eventDataValue;
			}
			
			public JSONArray getEventDataKeys(){
				return this.eventDataKeys;
			}
		}
	}

}
