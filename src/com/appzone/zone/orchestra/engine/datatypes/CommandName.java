package com.appzone.zone.orchestra.engine.datatypes;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * @contact fdamilola@gmail.com
 * 
 */

public class CommandName {

	private String commandName, commandNameString, description, implementation, id, versionNumber,
			institutionCode, successMessage, failureMessage;
	private boolean isToSave, isNew;

	private JSONObject cObject;
	private ArrayList<Sections> sections = new ArrayList<Sections>();
	private ArrayList<Buttons> buttons = new ArrayList<Buttons>();
	private Category category;
	private ArrayList<SingleField> allFields = new ArrayList<SingleField>();

	public CommandName(String cObjectString) throws JSONException {
		setCommandNameString(cObjectString);
		
		cObject = null;
		try {
			cObject = new JSONObject(cObjectString);
		} catch (Exception s) {
			//s.printStackTrace();
			//setCommandNameString(cObjectString);
		}
		
		if (cObject != null) {
			// TODO Auto-generated constructor stub
			setCommandName(cObject.optString("Name"));
			setDescription(cObject.optString("Description"));
			setIsToSave(cObject.optBoolean("IsToSave"));
			setNew(cObject.optBoolean("IsNew"));
			setId(cObject.optString("ID"));
			setImplementation(cObject.optString("Implementation"));
			setVersionNumber(cObject.optString("VersionNumber"));
			setInstitutionCode(cObject.optString("InstitutionCode"));
			setSuccessMessage(cObject.optString("SuccessMessage"));
			setFailureMessage(cObject.optString("FailureMessage"));
			setCategory(new Category(cObject.optJSONObject("Category")));
			JSONArray sectionsArray = cObject.optJSONArray("Sections");
			if (sectionsArray != null && sectionsArray.length() > 0) {
				for (int i = 0; i < sectionsArray.length(); i++) {
					JSONObject section = sectionsArray.getJSONObject(i);
					this.sections.add(new Sections(section));
				}
			} else {
				this.setSections(sections);
			}
			JSONArray buttonsArray = cObject.optJSONArray("Buttons");
			if (buttonsArray != null && buttonsArray.length() > 0) {
				for (int i = 0; i < buttonsArray.length(); i++) {
					JSONObject button = buttonsArray.getJSONObject(i);
					this.buttons.add(new Buttons(button));
				}
			} else {
				this.setButtons(buttons);
			}
			JSONArray fieldsArray = cObject.optJSONArray("AllFields");
			if (fieldsArray != null && fieldsArray.length() > 0) {
				for (int i = 0; i < fieldsArray.length(); i++) {
					JSONObject field = fieldsArray.getJSONObject(i);
					this.allFields.add(new SingleField(field));
				}
			} else {
				this.setAllFields(allFields);
				;
			}
		}

	}

	public JSONObject getCommandNameJSONObject(){
		return this.cObject;
	}
	
	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setButtons(ArrayList<Buttons> buttons) {
		this.buttons = buttons;
	}

	public ArrayList<Buttons> getButtons() {
		return this.buttons;
	}

	public ArrayList<Sections> getSections() {
		return sections;
	}

	public void setSections(ArrayList<Sections> sections) {
		this.sections = sections;
	}

	public String getCommandNameString() {
		return commandNameString;
	}

	public void setCommandNameString(String commandNameString) {
		this.commandNameString = commandNameString;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImplementation() {
		return implementation;
	}

	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsToSave() {
		return isToSave;
	}

	public void setIsToSave(boolean isToSave) {
		this.isToSave = isToSave;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public ArrayList<SingleField> getAllFields() {
		return allFields;
	}

	public void setAllFields(ArrayList<SingleField> allFields) {
		this.allFields = allFields;
	}

}
