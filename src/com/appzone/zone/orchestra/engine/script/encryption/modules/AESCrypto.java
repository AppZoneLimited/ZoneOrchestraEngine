package com.appzone.zone.orchestra.engine.script.encryption.modules;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public class AESCrypto {

	private SecretKeySpec secretKey;
	private byte[] key;

	private String decryptedString;
	private String encryptedString;

	public AESCrypto(String key) {
		// TODO Auto-generated constructor stub
		setKey(key);
	}

	private void setKey(String myKey) {

		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); // use only first 128 bit

			secretKey = new SecretKeySpec(key, "AES");

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getDecryptedString() {
		return this.decryptedString;
	}

	public void setDecryptedString(String decryptedString) {
		this.decryptedString = decryptedString;
	}

	public String getEncryptedString() {
		return this.encryptedString;
	}

	public void setEncryptedString(String encryptedString) {
		this.encryptedString = encryptedString;
	}

	public String encrypt(String strToEncrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			//Base64.encodeBase64String()
			byte[] input = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));	
			String result = android.util.Base64.encodeToString(input, android.util.Base64.DEFAULT);
			setEncryptedString(result);
			return getEncryptedString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String decrypt(String strToDecrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);

			byte[] input = cipher.doFinal(android.util.Base64.decode(strToDecrypt, android.util.Base64.DEFAULT));
			String result = new String(input);
			setDecryptedString(result);

			return getDecryptedString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
