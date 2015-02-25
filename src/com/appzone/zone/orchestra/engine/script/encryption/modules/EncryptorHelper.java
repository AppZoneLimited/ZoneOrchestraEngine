package com.appzone.zone.orchestra.engine.script.encryption.modules;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public class EncryptorHelper {

	/**
	 * 
	 * @param s : String to be encrypted
	 * @param e : Encryption type, an enum
	 * @param salt : Salt to be used for SHA-1 encryption
	 * @param key : Key for AES encryption
	 * @return String encrypted, returns null if encryption fails
	 */
	public static String encryptString(String s, EncryptionAlgorithms e, String salt, String key){
		try{
		switch (e) {
		case MD5:
			return MD5Crypto.md5Hash(s);
		case AES:
			return new AESCrypto(key).encrypt(s);
		case SHA_256:
			return new SHACrypto(EncryptionAlgorithms.SHA_256).encrptyString(s);
		default:
			return null;
		}
		}catch(Exception c){
			c.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param s String to decrypt
	 * @param e Encryption type
	 * @param salt needed for SHA-1 just supply null
	 * @param key : Key for AES decryption
	 * @return returns null if it fails and decrypted String if successful.
	 */
	public static String decryptString(String s, EncryptionAlgorithms e, String salt, String key){
		try{
		switch (e) {
		case AES:
			AESCrypto aes = new AESCrypto(key);
			return aes.decrypt(s);
		default:
			return null;
		}
		}catch(Exception c){
			c.printStackTrace();
		}
		return null;
	}
	
	

}
