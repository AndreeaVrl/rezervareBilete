package it.rezervare.beans.utils;

import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

import it.rezervare.beans.constants.ApplicationConstants;


public class PDFHelper {

	public static Date getDateWithoutTime(final Date date) {
	    final Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    return cal.getTime();
	}

	public static Date getTomorrowDate(final Date date) {
	    final Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.DATE, 1);
	    return cal.getTime();
	}

	public static Font getTimesNewRomanFont() {
		BaseFont bf = null;
		Font font = null;
		try {
			bf = BaseFont.createFont(ApplicationConstants.PATH_TO_FONT_LOCATION, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);// Cp1250
			font = new Font(bf, 12);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return font;
	}
	
	public static Font getTimesNewRomanFontBold() {
		BaseFont bf = null;
		Font font = null;
		try {
			bf = BaseFont.createFont(ApplicationConstants.PATH_TO_FONT_LOCATION, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);// Cp1250
			font = new Font(bf, 12, Font.BOLD);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return font;
	}
	
	public static Font getTimesNewRomanFontForTitle() {
		BaseFont bf = null;
		Font font = null;
		try {
			bf = BaseFont.createFont(ApplicationConstants.PATH_TO_FONT_LOCATION, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);// Cp1250
			font = new Font(bf, 26, Font.NORMAL, BaseColor.BLACK);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return font;
	}
	
	public static Font getTimesNewRomanFontFourteen() {
		BaseFont bf = null;
		Font font = null;
		try {
			bf = BaseFont.createFont(ApplicationConstants.PATH_TO_FONT_LOCATION, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);// Cp1250
			font = new Font(bf, 14);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return font;
	}

}
