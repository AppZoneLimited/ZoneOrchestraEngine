package com.appzone.zone.orchestra.engine.script.encryption.modules;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.text.TextUtils;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public class SHACrypto {

	EncryptionAlgorithms e;

	public SHACrypto(EncryptionAlgorithms e) {
		// TODO Auto-generated constructor stub
		this.e = e;
	}

	public String encrptyString(String s) throws Exception{
		switch (this.e) {
		case SHA_256:
			return encrypt256(s);
		default:
			return null;
		}
	}

	private String encrypt256(String str) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(str.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		// convert the byte to hex format method 2
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		
		System.out.println("Hex format : " + sb.toString());
		System.out.println("Hex format : " + hexString.toString());
		
		if(!TextUtils.isEmpty(sb.toString())){
			return sb.toString();
		}
		
		if(!TextUtils.isEmpty(hexString.toString())){
			return hexString.toString();
		}
		
		return null;
	}
}
