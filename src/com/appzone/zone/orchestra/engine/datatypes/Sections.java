package com.appzone.zone.orchestra.engine.datatypes;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * @contact fdamilola@gmail.com
 * 
 * @see { "Name":"Account Info", "NumberOfColumn":2, "Parent":null,
 *      "RowNumber":1, "IsColumn":false, "Buttons":[
 * 
 *      ], "Fields":[
 * 
 *      ], "ID":1098, "IsToSave":false, "VersionNumber":null,
 *      "InstitutionCode":null, "SuccessMessage":null, "FailureMessage":null }
 * 
 */

public class Sections {

	private String sectionName, noOfColumns, versionNumber, insitutionCode,
			successMessage, failureMessage, rowNumber, id;
	private boolean isColumn, isToSave;
	private Sections parent;
	private ArrayList<Buttons> button = new ArrayList<Buttons>();
	private ArrayList<SingleField> field = new ArrayList<SingleField>();

	public Sections(JSONObject o) throws JSONException {
		if (o != null) {
			// TODO Auto-generated constructor stub
			setSectionName(o.optString("Name"));
			setNoOfColumns(o.optString("NumberOfColumn"));

			JSONObject parentJSON = o.optJSONObject("Parent");
			if (parentJSON != null) {
				setParent(new Sections(parentJSON));
			} else {
				setParent(null);
			}
			setRowNumber(o.optString("RowNumber"));
			setColumn(o.optBoolean("IsColumn"));
			setId(o.optString("ID"));
			setToSave(o.optBoolean("IsToSave"));
			setVersionNumber(o.optString("VersionNumber"));
			setInsitutionCode(o.optString("InstitutionCode"));
			setSuccessMessage(o.optString("SuccessMessage"));
			setFailureMessage(o.optString("FailureMessage"));

			JSONArray buttonsArray = o.getJSONArray("Buttons");
			if (buttonsArray.length() > 0) {
				for (int i = 0; i < buttonsArray.length(); i++) {
					Buttons b = new Buttons(
							(JSONObject) buttonsArray.getJSONObject(i));
					this.button.add(b);
				}
			} else {
				setButton(this.button);
			}

			JSONArray fieldsArray = o.getJSONArray("Fields");

			if (fieldsArray.length() > 0) {
				for (int i = 0; i < fieldsArray.length(); i++) {
					SingleField b = new SingleField(
							(JSONObject) fieldsArray.getJSONObject(i));
					this.field.add(b);
				}
			} else {
				setField(this.field);
			}

		}
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getNoOfColumns() {
		return noOfColumns;
	}

	public void setNoOfColumns(String noOfColumns) {
		this.noOfColumns = noOfColumns;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getInsitutionCode() {
		return insitutionCode;
	}

	public void setInsitutionCode(String insitutionCode) {
		this.insitutionCode = insitutionCode;
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

	public boolean isColumn() {
		return isColumn;
	}

	public void setColumn(boolean isColumn) {
		this.isColumn = isColumn;
	}

	public boolean isToSave() {
		return isToSave;
	}

	public void setToSave(boolean isToSave) {
		this.isToSave = isToSave;
	}

	public String getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Sections getParent() {
		return parent;
	}

	public void setParent(Sections parent) {
		this.parent = parent;
	}

	public ArrayList<Buttons> getButton() {
		return button;
	}

	public void setButton(ArrayList<Buttons> button) {
		this.button = button;
	}

	public ArrayList<SingleField> getField() {
		return field;
	}

	public void setField(ArrayList<SingleField> field) {
		this.field = field;
	}
}
