
package com.appzone.zone.orchestra.engine.script.encryption.modules;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public class MD5Crypto {
	/**
	 * 
	 * @param s is the string to be encrypted
	 * @return encrypted string
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5Hash(String s) throws NoSuchAlgorithmException {
		
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(s.getBytes(), 0, s.length());
		return new BigInteger(1, m.digest()).toString(16);
	}
}

