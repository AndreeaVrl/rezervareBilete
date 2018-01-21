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

}
