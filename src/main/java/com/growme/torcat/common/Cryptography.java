package com.growme.torcat.common;

import java.security.MessageDigest;

public class Cryptography {

	private static final char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String MD5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			return bytesToHexString(md.digest());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private static String bytesToHexString(byte[] b) {
		String hex = "";
		int msb;
		int lsb;
		int i;

		// MSB maps to idx 0
		for (i = 0; i < b.length; i++) {
			msb = (b[i] & 0x000000FF) / 16;
			lsb = (b[i] & 0x000000FF) % 16;
			hex = hex + hexChars[msb] + hexChars[lsb];
		}
		return (hex);
	}
}