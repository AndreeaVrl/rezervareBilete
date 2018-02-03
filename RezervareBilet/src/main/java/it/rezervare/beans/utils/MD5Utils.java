package it.rezervare.beans.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import beans.exception.ApplicationException;
import beans.exception.ExceptionsMessages;

public class MD5Utils {

	public static String convertStringToMD5(final String value) throws ApplicationException {
		String encodedValue = null;
		try {
			final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(value.getBytes(Charset.forName("UTF8")));
			final byte[] resultByte = messageDigest.digest();
			encodedValue = Hex.encodeHexString(resultByte);
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new ApplicationException(ExceptionsMessages.GENERIC_ERROR);
		}
		return encodedValue;
	}
	
	/*public static String convertMD5ToString(final String value) throws ApplicationException {
		String password = "";
		try {
		    final MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(value.getBytes());
		    final byte byteData[] = md.digest();
		    final StringBuilder stringBuilder = new StringBuilder();
		    for (int i = 0; i < byteData.length; i++) {
		    	stringBuilder.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		    }
		    password = stringBuilder.toString();
		} catch (final Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ExceptionsMessages.GENERIC_ERROR);
		}
		return password;
	}*/

}
