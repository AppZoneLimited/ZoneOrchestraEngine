
package com.appzone.zone.orchestra.engine.phonefunctions;

import android.content.Context;

import com.appzone.zone.orchestra.engine.phonefunctions.options.PhoneContacts;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public class PhoneOptionsFactory {
	
	private Context ctx;
	
	public PhoneOptionsFactory(Context ctx){
		this.ctx = ctx;
	}
	
	public PhoneOptions createPhoneOptionObject(PhoneOptionsEnum option){
		switch (option) {
		case PHONE_CONTACTS:
			return new PhoneContacts(this.ctx);
		case LOCATION_API:
			return null;
		case CAMERA_API:
			return null;
		default:
			return null;
		}	
	}
}

