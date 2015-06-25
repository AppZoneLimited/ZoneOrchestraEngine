
package com.appzone.zone.orchestra.engine.datatypes;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 * @contact fdamilola@gmail.com
 * 
 */

public class Format{
	
	private String name, id, versionNumber,
	institutionCode, successMessage, failureMessage;
	private String regexFormat;
	private Validation validation;
	private ValidationField vf;
	
	private boolean isToSave;
	
	public Format(JSONObject f) throws JSONException{
		if (f != null) {
			// TODO Auto-generated constructor stub
			setName(f.optString("Name"));
			setRegexFormat(f.optString("RegexFormat"));
			setValidation(new Validation(f.optJSONObject("Validation")));
			setVf(new ValidationField(f.optJSONObject("ValidationFields")));
			setId(f.optString("ID"));
			setToSave(f.optBoolean("IsToSave"));
			setVersionNumber(f.optString("VersionNumber"));
			setInstitutionCode(f.optString("InstitutionCode"));
			setSuccessMessage(f.optString("SuccessMessage"));
			setFailureMessage(f.optString("FailureMessage"));
		}
	}
	
	public String getRegexFormat() {
		return regexFormat;
	}

	public void setRegexFormat(String regexFormat) {
		this.regexFormat = regexFormat;
	}

	public Validation getValidation() {
		return validation;
	}

	public void setValidation(Validation validation) {
		this.validation = validation;
	}

	public ValidationField getVf() {
		return vf;
	}

	public void setVf(ValidationField vf) {
		this.vf = vf;
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

	public boolean isToSave() {
		return isToSave;
	}

	public void setToSave(boolean isToSave) {
		this.isToSave = isToSave;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
