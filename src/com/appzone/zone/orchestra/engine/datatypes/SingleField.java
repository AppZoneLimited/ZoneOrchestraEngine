package com.appzone.zone.orchestra.engine.datatypes;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * @contact fdamilola@gmail.com
 * 
 */

public class SingleField {
	
	private String name, id, versionNumber,
	institutionCode, successMessage, failureMessage, sourceContent, type, dataSourceType, sectionColumn, customSpec, entitySource;
	private boolean isToSave, isCustom;
	private int rowNumber, theMatchMode;
	private Spec spec;
	
	public SingleField(JSONObject af) throws JSONException{
		
		setName(af.optString("Name"));
		setType(af.optString("Type"));
		setDataSourceType(af.optString("DataSourceType"));
		setSectionColumn(af.optString("SectionColumn"));
		setSpec(new Spec(af.optJSONObject("Spec")));
		setCustom(af.optBoolean("IsCustom"));
		setSourceContent(af.optString("SourceContent"));
		setRowNumber(af.optInt("RowNumber"));
		setCustomSpec(af.optString("CustomSpec"));
		setEntitySource(af.optString("EntitySource"));
		setTheMatchMode(af.optInt("TheMatchMode"));
		setId(af.optString("ID"));
		setToSave(af.optBoolean("IsToSave"));
		setVersionNumber(af.optString("VersionNumber"));
		setInstitutionCode(af.optString("InstitutionCode"));
		setSuccessMessage(af.optString("SuccessMessage"));
		setFailureMessage(af.optString("FailureMessage"));
	}
	
	public String getName() {
		return name;
	}
	public Spec getSpec() {
		return spec;
	}

	public void setSpec(Spec spec) {
		this.spec = spec;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getSourceContent() {
		return sourceContent;
	}
	public void setSourceContent(String sourceContent) {
		this.sourceContent = sourceContent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	public String getSectionColumn() {
		return sectionColumn;
	}
	public void setSectionColumn(String sectionColumn) {
		this.sectionColumn = sectionColumn;
	}
	public String getCustomSpec() {
		return customSpec;
	}
	public void setCustomSpec(String customSpec) {
		this.customSpec = customSpec;
	}
	public String getEntitySource() {
		return entitySource;
	}
	public void setEntitySource(String entitySource) {
		this.entitySource = entitySource;
	}
	public boolean isToSave() {
		return isToSave;
	}
	public void setToSave(boolean isToSave) {
		this.isToSave = isToSave;
	}
	public boolean isCustom() {
		return isCustom;
	}
	public void setCustom(boolean isCustom) {
		this.isCustom = isCustom;
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public int getTheMatchMode() {
		return theMatchMode;
	}
	public void setTheMatchMode(int theMatchCode) {
		this.theMatchMode = theMatchCode;
	}
}
