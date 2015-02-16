
package com.appzone.zone.orchestra.engine.phonefunctions.options;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;


import com.appzone.zone.orchestra.engine.datatypes.Contacts;
import com.appzone.zone.orchestra.engine.phonefunctions.PhoneOptions;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *requires <uses-permission android:name="android.permission.READ_CONTACTS" />
 * 
 */

public class PhoneContacts implements PhoneOptions {

	private Context ctx;
	private ArrayList<Contacts> contactsArray;
	private JSONArray jContactsArray;

	public PhoneContacts(Context ctx){
		this.ctx = ctx;
		this.contactsArray = new ArrayList<Contacts>();
	}

	public ArrayList<Contacts> getContactsArray(){
		Cursor phones = this.ctx.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
		while (phones.moveToNext())
		{
			String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			this.contactsArray.add(new Contacts(name, phoneNumber));
		}
		phones.close();
		Collections.sort(this.contactsArray, new ComparatorContacts());
		return this.contactsArray;
	}

	public JSONArray getContactsJSONArray() throws JSONException{
		this.jContactsArray = new JSONArray();
		this.contactsArray = getContactsArray();
		for(Contacts c:this.contactsArray){
			this.jContactsArray.put(c.makeJSON());
		}
		return this.jContactsArray;
	}

	private class ComparatorContacts implements Comparator<Contacts>{
		
		@Override
		public int compare(Contacts lhs, Contacts rhs) {
			// TODO Auto-generated method stub
			String lhsString = lhs.getContactName().toUpperCase();
			String rhsString = rhs.getContactName().toUpperCase();

			if (lhsString == rhsString){
				return 0;
			}

			if(lhsString == null){
				return -1;
			}

			if(rhsString == null){
				return 1;
			}
			return lhsString.compareTo(rhsString);
		}

	}
}

