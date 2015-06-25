package com.appzone.zone.orchestra.engine.datatypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.appzone.zone.orchestra.engine.datatypes.Fields.SubMappings;
import com.appzone.zone.orchestra.engine.enums.StepTypeEnum;
import com.appzone.zone.orchestra.engine.interfaces.StepResultCallback;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ] Data pulled from steps
 * 
 */

public class Step {
	private String stepId, nextStepId, serviceName;
	private JSONObject stepResult;
	private JSONObject prevStepResult;
	private Step prevStep;
	private Events events;
	private ServiceType serviceType;
	private ArrayList<Fields> sfields;
	private StepsAbstraction stepAbstract;
	private CommandName commandName;
	private String commandNameString;
	private JSONObject commandNameJson;
	private JSONObject eventJSON;
	private boolean isUI;
	private boolean canRollBack;
	private boolean isGoTo;
	private String stepGotoId;
	private StepTypeEnum stepTypeEnum;

	final String SERVICE_UI = "DejaVu.UI";
	final String SERVICE_ENTITY = "DejaVu.Entity";
	final String SERVICE_GOTO = "DejaVu.Goto";
	final String SERVICE_SCRIPT = "DejaVu.Script";
	
	final String VERSION = "2.2";

	public boolean isUI() {
		return isUI;
	}

	public void setUI(boolean isUI) {
		this.isUI = isUI;
	}

	public boolean canRollBack() {
		return canRollBack;
	}

	public void setCanRollBack(boolean canRollBack) {
		this.canRollBack = canRollBack;
	}
	
	public Step getRollBackStep(){
		if(this.canRollBack == true){
			return getPreviousStep();
		}
		return null;
	}

	public Step(String stepId, JSONObject stepProcedure,
			ArrayList<Fields> sfields, StepsAbstraction stepsAbstraction)
					throws JSONException {
		this.setStepId(stepId);
		this.setStepAbstract(stepsAbstraction);
		this.setFields(sfields);

		this.setUI(stepProcedure.optBoolean("IsUI", false));
		this.setCanRollBack(stepProcedure.optBoolean("CanRollback", false));

		String commandNameJson = stepProcedure.optString("CommandName");
		this.setCommandNameString(commandNameJson);
		this.setServiceName(stepProcedure.optString("ServiceName"));

		if(this.getServiceName().equalsIgnoreCase(SERVICE_GOTO)){
			this.setStepGotoId(commandNameJson);
			this.setGoTo(true);
			this.setStepTypeEnum(StepTypeEnum.SERVICE_GOTO_ENUM);
		}else{
			this.setGoTo(false);
			this.setStepGotoId(null);
			if(this.getServiceName().equalsIgnoreCase(SERVICE_ENTITY)){
				this.setStepTypeEnum(StepTypeEnum.SERVICE_ENTITY_ENUM);
			}else if(this.getServiceName().equalsIgnoreCase(SERVICE_UI)){
				this.setStepTypeEnum(StepTypeEnum.SERVICE_UI_ENUM);
			}else if(this.getServiceName().equalsIgnoreCase(SERVICE_SCRIPT)){
				this.setStepTypeEnum(StepTypeEnum.SERVICE_SCRIPT_ENUM);
			}
		}

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
		this.setCommandName(new CommandName(getCommandNameString()));
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
		
		Log.e("Ver :", VERSION);
		
		JSONObject data = new JSONObject();
		JSONObject newdata = new JSONObject();

		ArrayList<AttachedCommand> attachedCommands = this.getEvents()
				.getAttachedCommands();

		if (getStepResult() != null) {
			ResultParser result = new ResultParser(prevStepResult);
			try {
				if(this.getEvents().getEventKeysArrayList().contains(result.getEventName())){
					if (attachedCommands.size() > 0) {
						for (int i = 0; i < attachedCommands.size(); i++) {
							AttachedCommand attCom = attachedCommands.get(i);
							
							ArrayList<CommandMapping> commandMappings = attCom
									.getCommandMappingsList();
							for (CommandMapping cmap : commandMappings) {
								if(Integer.parseInt(cmap.getSourceType()) == 3){
									newdata.put(cmap.getField(), cmap.getValueSource());
								}
								data.put(cmap.getField(), result.getEventData().getEventDataValueByKey(cmap.getValueSource()));
								
								
							}
						}
						
						
					}else{
						//Log.e("Error", "No Attached Command");
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		String finalstring = data.toString().substring(0, data.toString().length() - 1)+","+newdata.toString().substring(1);
//	
//		try {
//			return new JSONObject(finalstring);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
		return data;
	}

	public JSONObject getStepEntityData(JSONObject prevResult){
		Log.e("Ver :", VERSION);
		JSONObject data = new JSONObject();
		JSONObject baseData = new JSONObject();
		JSONObject newdata = new JSONObject();
		
		boolean hasSubMappings = true;
		ArrayList<AttachedCommand> attachedCommands = this.getEvents()
				.getAttachedCommands();
		
		if (getStepResult() != null) {
			EntityResultParser result = new EntityResultParser(prevResult);
			try {
				//Log.e("EventKeysArrayList", this.getEvents().getEventKeysArrayList().toString());
				if(this.getEvents().getEventKeysArrayList().contains(result.getEventName())){
					//Log.e("EventFound!", "True");
					if (attachedCommands.size() > 0) {
						//Log.e("Attached Command Size!", attachedCommands.size()+"");
						for (int i = 0; i < attachedCommands.size(); i++) {
							AttachedCommand attCom = attachedCommands.get(i);
							//Log.e("AttCommand", attCom.getJsonObject().toString());
							ArrayList<CommandMapping> commandMappings = attCom
									.getCommandMappingsList();
							for (CommandMapping cmap : commandMappings) {
								
								if(Integer.parseInt(cmap.getSourceType()) == 3){
									newdata.put(cmap.getField(), cmap.getValueSource());
								}
								
								if(cmap.hasSubmapping()){
									hasSubMappings = true;
									HashMap<String, SubMappings> smaps = cmap.getFieldSubmappings();
									Set<String> smapKeys = smaps.keySet();
									for(String keySetString:smapKeys){
										SubMappings s = smaps.get(keySetString);
										data.put(s.getField(), result.getEeD().getEntityEventDataValueByKey(s.getValueSource()));
									}
								}else{
									hasSubMappings = false;
									data.put(cmap.getField(), result.getEeD().getEntityEventDataValueByKey(cmap.getValueSource()));
									baseData.put(cmap.getField(), result.getEeD().getEntityEventDataValueByKey(cmap.getValueSource()));
								}
								
							}
						}
					}else{
						//Log.e("Error", "No Attached Command");
					}
				}
				if(hasSubMappings == true){
					baseData.put(result.getEeD().getEntityName(), data);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
		}
		
		String finalstring = baseData.toString().substring(0, baseData.toString().length() - 1)+","+newdata.toString().substring(1);
	
		try {
			return new JSONObject(finalstring);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return baseData;
	}

	public void setPreviousStep(Step step){
		this.prevStep = step;
	}
	
	public Step getPreviousStep(){
		return this.prevStep;
	}


	public void setStepResultCallBack(JSONObject stepResult,
			StepsAbstraction sa, StepResultCallback stepResultCallback) {
		this.setStepResult(stepResult);
		this.setStepAbstract(sa);
		stepResultCallback.onStepResult(sa, this, this.stepResult);
		Step nextStep = sa.getNextStep();
		if (nextStep != null) {
			nextStep.setPreviousStep(this);
			nextStep.setPrevStepResult(stepResult);
			stepResultCallback.onGetNextStep(nextStep, getStepData(nextStep.getPrevStepResult()), nextStep.getStepTypeEnum(), nextStep.canRollBack());
		} else {
			return;
		}
	}

	public void setStepEntityResultCallBack(JSONObject stepResult,
			StepsAbstraction sa, StepResultCallback stepResultCallback){
		this.setStepResult(stepResult);
		this.setStepAbstract(sa);
		stepResultCallback.onStepResult(sa, this, this.stepResult);
		Step nextStep = sa.getNextStep();
		if (nextStep != null) {
			//Log.e("StepData", this.getNextStepId());
			nextStep.setPreviousStep(this);
			nextStep.setPrevStepResult(stepResult);
			stepResultCallback.onGetNextStep(nextStep, getStepEntityData(nextStep.getPrevStepResult()), nextStep.getStepTypeEnum(), nextStep.canRollBack());
		} else {
			return;
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

	public StepsAbstraction getStepAbstract() {
		return stepAbstract;
	}

	public Step setFields(ArrayList<Fields> sfields) {
		this.sfields = sfields;
		return this;
	}

	public CommandName getCommandName() {
		return commandName;
	}

	public StepTypeEnum getStepTypeEnum() {
		return stepTypeEnum;
	}

	public void setStepTypeEnum(StepTypeEnum stepTypeEnum) {
		this.stepTypeEnum = stepTypeEnum;
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

	public boolean isGoTo() {
		return isGoTo;
	}

	public void setGoTo(boolean isGoTo) {
		this.isGoTo = isGoTo;
	}

	public String getStepGotoId() {
		return stepGotoId;
	}

	public void setStepGotoId(String stepGotoId) {
		this.stepGotoId = stepGotoId;
	}

	public String getCommandNameString() {
		return commandNameString;
	}

	public void setCommandNameString(String commandNameString) {
		this.commandNameString = commandNameString;
	}
	
	public static JSONObject getEntityResultFromJSON(JSONObject object){
		JSONArray keys = object.names();
		JSONObject js = null;
		for(int i = 0; i < keys.length(); i++){
			try {
				js = object.getJSONObject((String)keys.get(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return js;
	}
	

}
