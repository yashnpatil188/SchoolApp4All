package org.com.maauli;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;
import org.com.security.AESCrypt;
import org.com.security.EncryptDecryptStr;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Font.FontFamily;
//import com.mysql.jdbc.PreparedStatement;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;

public class Common {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	Toolkit toolkit = Toolkit.getDefaultToolkit();

	Dimension dim = toolkit.getScreenSize();

	static DBValidate dbValidate = new DBValidate();

	static CreateExcel ce = new CreateExcel();

	static Logger logger = Logger.getLogger(Common.class.getName());

	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	
	private static final int IMG_WIDTH = 110;
	
	private static final int IMG_HEIGHT = 140;

	private enum RomanStd {
		I, II, III, IV, V, VI, VII, VIII, IX, X, XI, XII, XIII, XIV, XV, NURSERY, LOWER_KG, UPPER_KG, JR_KG, SR_KG
	}

	private enum WordStd {
		am3, am2, am1, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a31
	}

	private enum Alphabet {
		A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, AA, ET, A1, A2, A3, A4, BLUE, GREEN, RED, YELLOW, Total, Grand_Total
	}

	private enum MonthList {
		JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
	}
	
	private enum MonthShortList {
		JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
	}
	
	public int screeWidth() {

		int width;
		width = dim.width;
		return width;
	}

	public int screeHeight() {

		int height;
		height = dim.height;
		return height;
	}

	public static boolean validateNumber(String num) {

		boolean isNumber = false;
		try {
			String numToken = "[0-9.]++";
			if (num.matches(numToken)) {
				isNumber = true;
			} else {
				isNumber = false;
			}
		} catch (Exception numFormat) {
			logger.error(numFormat);
			return isNumber;
		}
		return isNumber;
	}

	public boolean validateOnlyNumber(String num) {

		boolean isNumber = false;
		try {
			String numToken = "[0-9.]++";
			if (num.matches(numToken)) {
				isNumber = true;
			} else {
				isNumber = false;
			}
		} catch (Exception numFormat) {
			logException(numFormat);
			return isNumber;
		}
		return isNumber;
	}

	public static boolean validateDate(String inDate) {

		boolean isDate = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
			isDate = true;
		} catch (ParseException pe) {
			return false;
		}
		return isDate;
	}

	public boolean validateEmail(String email) {

		boolean isEmail = false;
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		Boolean b = email.matches(EMAIL_REGEX);
		if (b)
			isEmail = true;
		else
			isEmail = false;
		return isEmail;
	}

	public boolean validateAlphaNum(String textData) {

		String AlphaNum_REGEX = "[A-Za-z0-9 ]+";
		// String AlphaNum_REGEX = "^[a-zA-Z0-9][a-zA-Z0-9\s]*$";
		return textData.matches(AlphaNum_REGEX);
	}
	
	public boolean validateMarathi(String textData) {

		String AlphaNum_REGEX = "[A-Za-z0-9 .&,/:'-_]+";
		// String AlphaNum_REGEX = "^[a-zA-Z0-9][a-zA-Z0-9\s]*$";
		return textData.matches(AlphaNum_REGEX);
	}

	public boolean validateAlphabets(String textData) {

		boolean isAlphabet = false;
		String Alphabet_REGEX = "[A-Za-z]+";
		isAlphabet = textData.matches(Alphabet_REGEX);
		return isAlphabet;
	}

	public static String appendZero(String appendString) {

		int strLength = appendString.length();
		if (strLength < 7) {
			int zeroes = 7 - strLength;
			for (int j = 0; j < zeroes; j++) {
				appendString = "0" + appendString;
			}
		}
		return appendString;
	}
	
	public String appendZeroByLength(String appendString, int len) {

		if (appendString.length() < len) {
			int zeroes = len - appendString.length();
			for (int j = 0; j < zeroes; j++) {
				appendString = "0" + appendString;
			}
		}
		return appendString;
	}

	// ////////////////////////////////////datetowords//////////////////////////
	String string;

	String st0[] = { "", "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", };

	String st1[] = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", };

	String st2[] = { "hundred", "thousand" };

	String st3[] = { "tenth", "eleventh", "twelfth", "thirteenth", "fourteenth", "fifteenth", "sixteenth",
			"seventeenth", "eighteenth", "nineteenth", };

	String st4[] = { "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };

	String st5[] = { "twentieth", "thirtieth", "fortieth", "fiftieth", "sixtieth", "seventieth", "eightieth",
			"ninetieth" };

	String st6[] = { "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen", };

	public String dateToWords(int day, int month, int year) {

		String date2word = convert(day, "day") + " " + getMonth(month) + " " + convert(year, "year");
		return date2word;
	}

	///// getMonth in words from integer
	public String getMonth(int month) {

		return new DateFormatSymbols().getMonths()[month - 1];
	}

	public String convert(int number, String dateType) {

		int n = 1;
		int word;
		string = "";
		while (number != 0) {
			switch (n) {
			case 1:
				word = number % 100;
				pass(word, dateType);
				if (number > 100 && number % 100 != 0 && !dateType.equalsIgnoreCase("year")) {
					show("and ");
				}
				number /= 100;
				break;

			case 2:
				word = number % 10;
				if (word != 0) {
					if (dateType.equalsIgnoreCase("year") && number > 19) {
						show(" ");
						show(st2[0]);
					}
					if (n == 2 && number < 20) {
						show(" ");
						pass(number, dateType);
					} else {
						show(" ");
						pass(word, dateType);
						number /= 10;
					}
				}
				break;

			case 3:
				word = number % 100;
				if (word != 0 && (dateType.equalsIgnoreCase("year") && n == 3 && number > 19)) {
					show(" ");
					show(st2[1]);
					show(" ");
					word = word / 10;
					pass(word, dateType);
				}
				number /= 100;
				break;

			case 4:
				word = number % 100;
				if (word != 0) {
					show(" ");
					show(st2[2]);
					show(" ");
					pass(word, dateType);
				}
				number /= 100;
				break;

			case 5:
				word = number % 100;
				if (word != 0) {
					show(" ");
					show(st2[3]);
					show(" ");
					pass(word, dateType);
				}
				number /= 100;
				break;

			}
			n++;
		}
		return string;
	}

	public void pass(int number, String dateType) {

		int word, q;
		if (number < 10) {
			if (dateType.equalsIgnoreCase("day")) {
				show(st0[number]);
			} else {
				show(st1[number]);
			}

		}
		if (number > 9 && number < 20) {
			if (dateType.equalsIgnoreCase("year")) {
				show(st6[number - 10]);
			} else {
				show(st3[number - 10]);
			}
		}
		if (number > 19) {
			word = number % 10;
			if (word == 0) {
				q = number / 10;
				if (dateType.equalsIgnoreCase("day")) {
					show(st5[q - 2]);
				} else {
					show(st4[q - 2]);
				}

			} else {
				q = number / 10;
				if (dateType.equalsIgnoreCase("day")) {
					show(st0[word]);
				} else {
					show(st1[word]);
				}
				// show(st1[word]);
				show(" ");
				show(st4[q - 2]);
			}
		}
	}

	public void show(String s) {

		String st;
		st = string;
		string = s;
		string += st;
	}

	public static String charExceeded(String fieldName, String fieldData, int limit) {

		String retString = "";
		if (fieldData.length() > limit) {
			retString = "Field " + fieldName + " cannot be greater than " + limit + " chracters";
		}
		return retString;
	}

	public static boolean checkComma(String validateString) {
		/// ;',-:|
		boolean isComma = false;
		if (validateString.contains(";") || validateString.contains("'") || validateString.contains(",")
				|| validateString.contains("-") || validateString.contains(":") || validateString.contains("|")) {
			isComma = true;
		}
		return isComma;
	}
	
	public static boolean checkCommaWithoutHiphen(String validateString) {
		/// ;',:|
		boolean isComma = false;
		if (validateString.contains(";") || validateString.contains("'") || validateString.contains(",")
				|| validateString.contains(":") || validateString.contains("|")) {
			isComma = true;
		}
		return isComma;
	}

	// ///file reader/////////////
	public String fileReader() {

		String retUser = "";
		try {
			InputStream is = this.getClass().getResourceAsStream("org/com/accesser/yash.txt");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br1 = new BufferedReader(isr);
			String line;
			while ((line = br1.readLine()) != null) {
				retUser = line;
			}
			br1.close();
			isr.close();
			is.close();
		} catch (Exception io) {
			logException(io);
		} finally {
			try {
				// fr.close();
			} catch (Exception e) {
				logException(e);
			}
		}
		return retUser;
	}

	// ///file writer/////////////
	public boolean fileWriter(String user) {

		boolean currentUser = false;
		File f = null;
		FileWriter fw = null;
		try {
			f = new File("D:\\yash.txt");
			fw = new FileWriter(f, false);
			if (f.exists()) {
				fw.write(user);
				currentUser = true;
			}
		} catch (IOException io) {
			logException(io);
		} finally {
			try {
				fw.flush();
				fw.close();
			} catch (IOException e) {
				logException(e);
			}
		}
		return currentUser;
	}

	public String getCurrentTimeStamp() {
	    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	}
	
	////////
	public TreeMap<String, Double> updateTreeMap(TreeMap<String, Double> map, String keyStr, Double value){
		DecimalFormat df = new DecimalFormat("####0.00");
		if(map.get(keyStr) != null) {
			
			map.put(keyStr, Double.parseDouble(df.format((map.get(keyStr) + value))));
		} else {
			map.put(keyStr, value);
		}
		return map;
	}
	// ////////////////////////////////////////////////////////////////
	public String formatdd_MM_yyyy(String dateReceived) {

		String retDateddMMMyyy = "";
		try {
			Date dt = new SimpleDateFormat("dd/MM/yyyy").parse(dateReceived);
			retDateddMMMyyy = new SimpleDateFormat("dd-MM-yyyy").format(dt);
		} catch (ParseException e) {
			logException(e);
		}
		return retDateddMMMyyy;
	}

	// ////////////////////////////////////////////////////////////////
	public String formatyyyymmddtoddmmyyyy(String dateReceived) {

		String retDateddMMMyyy = "";
		if (dateReceived.contains("/")) {
			dateReceived = dateReceived.replace("/", "-");
		}
		try {
			Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(dateReceived);
			retDateddMMMyyy = new SimpleDateFormat("dd/MM/yyyy").format(dt);
		} catch (ParseException e) {
			logException(e);
		}
		return retDateddMMMyyy;
	}
	// ////////////////////////////////////////////////////////////////

	public String formatddMMMyyy(String dateReceived) {

		String retDateddMMMyyy = "";
		try {
			Date dt = new SimpleDateFormat("dd/MM/yyyy").parse(dateReceived);
			retDateddMMMyyy = new SimpleDateFormat("dd-MMM-yyyy").format(dt);
		} catch (ParseException e) {
			logException(e);
		}
		return retDateddMMMyyy;
	}

	// ////////////////////////////////////////////////////////////////
	public String MMM_ddmmyyy(String dateReceived) {

		String retDateddmmyyy = "";
		if (!dateReceived.equalsIgnoreCase("") && !dateReceived.equalsIgnoreCase("-")) {
			try {
				Date dt = new SimpleDateFormat("dd-MMM-yyyy").parse(dateReceived);
				retDateddmmyyy = new SimpleDateFormat("dd-MM-yyyy").format(dt);
			} catch (ParseException e) {
				logException(e);
			}
		}
		return retDateddmmyyy;
	}

	// ////////////////////////////////////////////////////////////////
	public static String MM_ddlmmlyyyy(String dateReceived) {

		String retDateddmmyyy = "";
		if (!dateReceived.equalsIgnoreCase("") && !dateReceived.equalsIgnoreCase("-")) {
			try {
				Date dt = new SimpleDateFormat("dd-mm-yyyy").parse(dateReceived);
				retDateddmmyyy = new SimpleDateFormat("dd/mm/yyyy").format(dt);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		return retDateddmmyyy;
	}

	/////////////////////////////
	public String dateFormat_yyyymmdd(String dateReceived) {

		String retDateyyyymmdd = "";
		if (!dateReceived.equalsIgnoreCase("") && !dateReceived.equalsIgnoreCase("-")) {
			try {
				Date dt = new SimpleDateFormat("dd/mm/yyyy").parse(dateReceived);
				retDateyyyymmdd = new SimpleDateFormat("yyyy/mm/dd").format(dt);
			} catch (ParseException e) {
				logException(e);
			}
		}
		return retDateyyyymmdd;
	}

	/////////////////////////////
	public String dateFormatFromyyyymmddToddmmyyyy(String dateReceived) {

		String retDateyyyymmdd = "";
		Date dt = null;
		if (!dateReceived.equalsIgnoreCase("") && !dateReceived.equalsIgnoreCase("-")) {
			try {
				if(dateReceived.contains("-")){
					dt = new SimpleDateFormat("yyyy-mm-dd").parse(dateReceived);
				} else if(dateReceived.contains("/")){
					dt = new SimpleDateFormat("yyyy/mm/dd").parse(dateReceived);
				}
				retDateyyyymmdd = new SimpleDateFormat("dd/mm/yyyy").format(dt);
			} catch (ParseException e) {
				logException(e);
			}
		}
		return retDateyyyymmdd;
	}
	
	/////////////////////////////
	public String dateFormatFromyyyymmddhhmmToddmmyyyyhhmm(String dateReceived) {
	
		String retDateyyyymmdd = "";
		if (!dateReceived.equalsIgnoreCase("") && !dateReceived.equalsIgnoreCase("-")) {
			try {
				Date dt = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateReceived);
				retDateyyyymmdd = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(dt);
			} catch (ParseException e) {
				logException(e);
			}
		}
		return retDateyyyymmdd;
	}

	/// current date in yyyy-mm-dd HH:min
	public String dateFormat_yyyymmddhhmin() {

		String retDateyyyymmdd = "";
		String retCurrentDate = "";
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			retCurrentDate = sdf.format(cal.getTime());
			return retCurrentDate;
		} catch (Exception e) {
			logException(e);
			return retCurrentDate;
		}
	}

	// /////////Date to
	// dd-mm-yyyy///////////////////////////////////////////////////////
	public String MM_dd_mm_yyyy(String dateReceived) {

		String retDateddmmyyy = "";
		if (!dateReceived.equalsIgnoreCase("") && !dateReceived.equalsIgnoreCase("-")) {
			try {
				Date dt = new SimpleDateFormat("dd/mm/yyyy").parse(dateReceived);
				retDateddmmyyy = new SimpleDateFormat("dd-mm-yyyy").format(dt);
			} catch (ParseException e) {
				logException(e);
			}
		}
		return retDateddmmyyy;
	}

	// ////////////////////////////////////////////////////////////////
	public String MM_ddmmyyy(String dateReceived) {

		String retDateddmmyyy = "";
		if (!dateReceived.equalsIgnoreCase("") && !dateReceived.equalsIgnoreCase("-")) {
			try {
				Date dt = new SimpleDateFormat("dd/MM/yyyy").parse(dateReceived);
				retDateddmmyyy = new SimpleDateFormat("dd-mm-yyyy").format(dt);
			} catch (ParseException e) {
				logException(e);
			}
		}
		return retDateddmmyyy;
	}

	/////////// Date to dd/mm/yyyy
	/////////// formt///////////////////////////////////////////////////////
	public String dateToDDMMYYYY(Date dateReceived) {

		String retDateddmmyyy = "";
		if (dateReceived != null) {
			try {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				retDateddmmyyy = df.format(dateReceived);
			} catch (Exception e) {
				logException(e);
			}
		}
		return retDateddmmyyy;
	}

	/////////// format to ddmmyyyy///////////////////////////////////////////////////////
	public String dateToDDMMYYYY_withoutSlash(Date dateReceived) {
	
		String retDateddmmyyy = "";
		if (dateReceived != null) {
			try {
				DateFormat df = new SimpleDateFormat("ddMMyyyy");
				retDateddmmyyy = df.format(dateReceived);
			} catch (Exception e) {
				logException(e);
			}
		}
		return retDateddmmyyy;
	}
	/////////// Date to yyyy/mm/dd
	/////////// formt///////////////////////////////////////////////////////
	public String dateToYYYYMMDD(Date dateReceived) {

		String retDateyyyymmdd = "";
		if (dateReceived != null) {
			try {
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				retDateyyyymmdd = df.format(dateReceived);
			} catch (Exception e) {
				logException(e);
			}
		}
		return retDateyyyymmdd;
	}

	public Date stringToDate(String dateStr) {  
	    Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
		} catch (ParseException e) {
			logException(e);
		}  
	    return date1;
	}  
	
	// //////////////////////////
	public boolean validateYearFormat(String dateReceived) {

		boolean isYearValid = false;
		try {
			String year = dateReceived.substring(dateReceived.lastIndexOf("/") + 1);
			if (year.length() == 4) {
				isYearValid = true;
			}
			return isYearValid;
		} catch (Exception e) {
			logException(e);
			return isYearValid;
		}
	}

	/////////////////////////////////////
	public String RomanToWord(String RomanWord) {

		String RomanInWord = "";
		if(RomanWord.contains(" ")) {
			RomanWord = RomanWord.replace(" ", "_");
		}

		if(RomanWord != null && !RomanWord.equalsIgnoreCase("") && !RomanWord.equalsIgnoreCase("-")) {
			RomanStd word = RomanStd.valueOf(RomanWord.trim()); // surround with
			switch (word) {
				case I:
					RomanInWord = "First";
					break;
				case II:
					RomanInWord = "Second";
					break;
				case III:
					RomanInWord = "Third";
					break;
				case IV:
					RomanInWord = "Fourth";
					break;
				case V:
					RomanInWord = "Fifth";
					break;
				case VI:
					RomanInWord = "Sixth";
					break;
				case VII:
					RomanInWord = "Seventh";
					break;
				case VIII:
					RomanInWord = "eighth";
					break;
				case IX:
					RomanInWord = "Ninth";
					break;
				case X:
					RomanInWord = "Tenth";
					break;
				case XI:
					RomanInWord = "Eleventh";
					break;
				case XII:
					RomanInWord = "Twelfth";
					break;
				case XIII:
					RomanInWord = "Thirteenth";
					break;
				case XIV:
					RomanInWord = "Fourteenth";
					break;
				case XV:
					RomanInWord = "Fifteenth";
					break;
				case NURSERY:
					RomanInWord = "Nursery";
					break;
				case LOWER_KG:
					RomanInWord = "Lower KG";
					break;
				case UPPER_KG:
					RomanInWord = "Upper KG";
					break;
				case JR_KG:
					RomanInWord = "JR KG";
					break;
				case SR_KG:
					RomanInWord = "SR KG";
					break;
				default:
				logger.info("Invalid Std.");
			}
		}
		return RomanInWord;
	}

	// ///////////////////////////////////
	public int RomanToInteger(String RomanWord) {

		int RomanToInteger = 0;
		RomanWord = RomanWord.toUpperCase().replace(" ", "_");
		RomanWord = RomanWord.toUpperCase().replace(".", "_");

		RomanStd word = RomanStd.valueOf(RomanWord.trim()); // surround with

		switch (word) {
		case NURSERY:
			RomanToInteger = -3;
			break;
		case JR_KG:
			RomanToInteger = -2;
			break;
		case SR_KG:
			RomanToInteger = -1;
			break;
		case I:
			RomanToInteger = 1;
			break;
		case II:
			RomanToInteger = 2;
			break;
		case III:
			RomanToInteger = 3;
			break;
		case IV:
			RomanToInteger = 4;
			break;
		case V:
			RomanToInteger = 5;
			break;
		case VI:
			RomanToInteger = 6;
			break;
		case VII:
			RomanToInteger = 7;
			break;
		case VIII:
			RomanToInteger = 8;
			break;
		case IX:
			RomanToInteger = 9;
			break;
		case X:
			RomanToInteger = 10;
			break;
		case XI:
			RomanToInteger = 11;
			break;
		case XII:
			RomanToInteger = 12;
			break;
		case XIII:
			RomanToInteger = 13;
			break;
		case XIV:
			RomanToInteger = 14;
			break;
		case XV:
			RomanToInteger = 15;
			break;
//		case NURSERY:
//			RomanToInteger = 16;
//			break;
		case LOWER_KG:
			RomanToInteger = 17;
			break;
		case UPPER_KG:
			RomanToInteger = 18;
			break;
		default:
			RomanToInteger = 31;
			break;
		}
		return RomanToInteger;
	}

	// ////////IntegerToRoman also append a before int///////////////////////////
	public String IntegerToRoman(String stdWord) {

		String intToRoman = "";

		stdWord = stdWord.replace("-", "m");
		WordStd word = WordStd.valueOf(stdWord.trim()); // surround with
														// try/catch
		// one, two, three, four, five, six, seven, eight, nine, ten, eleven,
		// twelve
		switch (word) {
		case am3:
			intToRoman = "NURSERY";
			break;
		case am2:
			intToRoman = "JR KG";
			break;
		case am1:
			intToRoman = "SR KG";
			break;
		case a0:
			intToRoman = "I";
			break;
		case a1:
			intToRoman = "I";
			break;
		case a2:
			intToRoman = "II";
			break;
		case a3:
			intToRoman = "III";
			break;
		case a4:
			intToRoman = "IV";
			break;
		case a5:
			intToRoman = "V";
			break;
		case a6:
			intToRoman = "VI";
			break;
		case a7:
			intToRoman = "VII";
			break;
		case a8:
			intToRoman = "VIII";
			break;
		case a9:
			intToRoman = "IX";
			break;
		case a10:
			intToRoman = "X";
			break;
		case a11:
			intToRoman = "XI";
			break;
		case a12:
			intToRoman = "XII";
			break;
		case a13:
			intToRoman = "XIII";
			break;
		case a14:
			intToRoman = "XIV";
			break;
		case a15:
			intToRoman = "XV";
			break;
		case a16:
			intToRoman = "NURSERY";
			break;
		case a17:
			intToRoman = "LOWER KG";
			break;
		case a18:
			intToRoman = "UPPER KG";
			break;
		default:
			intToRoman = "XVI";
			break;
		}

		return intToRoman;
	}

	// ///////////////////////////////////
	public int AlphabetToInteger(String AlphaWord) {

		int alphaToInteger = 0;

		Alphabet alphaInt = Alphabet.valueOf(AlphaWord.trim());

		switch (alphaInt) {
		case A:
			alphaToInteger = 11;
			break;
		case B:
			alphaToInteger = 12;
			break;
		case C:
			alphaToInteger = 13;
			break;
		case D:
			alphaToInteger = 14;
			break;
		case E:
			alphaToInteger = 15;
			break;
		case F:
			alphaToInteger = 16;
			break;
		case G:
			alphaToInteger = 17;
			break;
		case H:
			alphaToInteger = 18;
			break;
		case I:
			alphaToInteger = 19;
			break;
		case J:
			alphaToInteger = 20;
			break;
		case K:
			alphaToInteger = 21;
			break;
		case L:
			alphaToInteger = 22;
			break;
		case M:
			alphaToInteger = 23;
			break;
		case N:
			alphaToInteger = 24;
			break;
		case O:
			alphaToInteger = 25;
			break;
		case P:
			alphaToInteger = 26;
			break;
		case Q:
			alphaToInteger = 27;
			break;
		case R:
			alphaToInteger = 28;
			break;
		case S:
			alphaToInteger = 29;
			break;
		case T:
			alphaToInteger = 30;
			break;
		case U:
			alphaToInteger = 31;
			break;
		case V:
			alphaToInteger = 32;
			break;
		case W:
			alphaToInteger = 33;
			break;
		case X:
			alphaToInteger = 34;
			break;
		case Y:
			alphaToInteger = 35;
			break;
		case Z:
			alphaToInteger = 36;
			break;
		case AA:
			alphaToInteger = 37;
			break;
		case ET:
			alphaToInteger = 38;
			break;
		case A1:
			alphaToInteger = 39;
			break;
		case A2:
			alphaToInteger = 40;
			break;
		case A3:
			alphaToInteger = 41;
			break;
		case A4:
			alphaToInteger = 42;
			break;
		case BLUE:
			alphaToInteger = 43;
			break;
		case GREEN:
			alphaToInteger = 44;
			break;
		case RED:
			alphaToInteger = 45;
			break;
		case YELLOW:
			alphaToInteger = 46;
			break;
		case Total:
			alphaToInteger = 47;
			break;
		case Grand_Total:
			alphaToInteger = 999;
			break;
		default:
			alphaToInteger = 48;
			break;
		}

		return alphaToInteger;
	}
		
	// ///////////////////////////////////
	public LinkedHashMap<String, String> IntegerToAlphabet() {
		LinkedHashMap<String, String> integerToAlphabetMap = new LinkedHashMap<String, String>();

		integerToAlphabetMap.put("11", "A");
		integerToAlphabetMap.put("12", "B");
		integerToAlphabetMap.put("13", "C");
		integerToAlphabetMap.put("14", "D");
		integerToAlphabetMap.put("15", "E");
		integerToAlphabetMap.put("16", "F");
		integerToAlphabetMap.put("17", "G");
		integerToAlphabetMap.put("18", "H");
		integerToAlphabetMap.put("19", "I");
		integerToAlphabetMap.put("20", "J");
		integerToAlphabetMap.put("21", "K");
		integerToAlphabetMap.put("22", "L");
		integerToAlphabetMap.put("23", "M");
		integerToAlphabetMap.put("24", "N");
		integerToAlphabetMap.put("25", "O");
		integerToAlphabetMap.put("26", "P");
		integerToAlphabetMap.put("27", "Q");
		integerToAlphabetMap.put("28", "R");
		integerToAlphabetMap.put("29", "S");
		integerToAlphabetMap.put("30", "T");
		integerToAlphabetMap.put("31", "U");
		integerToAlphabetMap.put("32", "V");
		integerToAlphabetMap.put("33", "W");
		integerToAlphabetMap.put("34", "X");
		integerToAlphabetMap.put("35", "Y");
		integerToAlphabetMap.put("36", "Z");
		integerToAlphabetMap.put("37", "AA");
		integerToAlphabetMap.put("38", "ET");
		integerToAlphabetMap.put("39", "A1");
		integerToAlphabetMap.put("40", "A2");
		integerToAlphabetMap.put("41", "A3");
		integerToAlphabetMap.put("42", "A4");
		integerToAlphabetMap.put("43", "Total");
		integerToAlphabetMap.put("999", "Grand_Total");
		
		return integerToAlphabetMap;
	}

	
	public int MonthToIntger(String month) {

		int monthToInteger = 0;

		MonthShortList monthInt = MonthShortList.valueOf(month.trim());
		
		switch (monthInt) {
		case JUN:
			return 6;
		case JUL:
			return 7;
		case AUG:
			return 8;
		case SEP:
			return 9;
		case OCT:
			return 10;
		case NOV:
			return 11;
		case DEC:
			return 12;
		case JAN:
			return 13;
		case FEB:
			return 14;
		case MAR:
			return 15;
		case APR:
			return 16;
		case MAY:
			return 17;
		}
		return 0;
	}
	
	public int QuarterToIntger(String quarter) {

		switch (quarter) {
		case "Q 1":
			return 0;
		case "Q 2":
			return 3;
		case "Q 3":
			return 6;
		case "Q 4":
			return 9;
		}
		return 0;
	}
	
	public int HalfYearlyToIntger(String halfYearly) {

		switch (halfYearly) {
		case "Term 1":
			return 0;
		case "Term 2":
			return 6;
		}
		return 0;
	}
	
	// ///////////////////////////////////
	public String intgerToMonth(String monthInt) {

		switch (monthInt) {
		case "1":
			return "JAN";
		case "2":
			return "FEB";
		case "3":
			return "MAR";
		case "4":
			return "APR";
		case "5":
			return "MAY";
		case "6":
			return "JUN";
		case "7":
			return "JUL";
		case "8":
			return "AUG";
		case "9":
			return "SEP";
		case "10":
			return "OCT";
		case "11":
			return "NOV";
		case "12":
			return "DEC";
		case "13":
			return "JAN";
		case "14":
			return "FEB";
		case "15":
			return "MAR";
		case "16":
			return "APR";
		case "17":
			return "MAY";
		case "18":
			return "JUN";
		case "19":
			return "JUL";
		case "20":
			return "AUG";
		case "21":
			return "SEP";
		case "22":
			return "OCT";
		case "23":
			return "NOV";
		case "24":
			return "DEC";
		}
		return "";
	}
	
	public int frequencyToInteger(String frequency) {

		switch (frequency) {
		case "Yearly":
			return 1;
		case "Half Yearly":
			return 2;
		case "Quarterly":
			return 4;
		case "Monthly":
			return 12;
		case "Occasionally":
			return 1;
		}
		return 0;
	}
	
	// //////////FirstWordCap////////////////
	public String FirstWordCap(String str) {

		try {
			StringBuffer stringbf = new StringBuffer();
			Matcher m = Pattern.compile("([a-z])([a-z,']*)", Pattern.CASE_INSENSITIVE).matcher(str);
			while (m.find()) {
				m.appendReplacement(stringbf, m.group(1).toUpperCase() + m.group(2).toLowerCase());
			}
			str = m.appendTail(stringbf).toString();
		} catch (Exception e) {
			logException(e);
		}
		return str;
	}

	// //////////validate password////////////////
	public boolean validatePassword(String password) {
		boolean passValidateFlag = false;
		try {
			Pattern pswNamePtrn = Pattern.compile("((?=.*\\d)(?=.*[a-z]).{6,15})");
			Matcher mtch = pswNamePtrn.matcher(password);
			if (mtch.matches()) {
				passValidateFlag = true;
			} else {
				passValidateFlag = false;
			}
		} catch (Exception e) {
			logException(e);
		}
		return passValidateFlag;
	}

	// ////////WordCountInString//////////////////
	public int WordCountInString(String str) {

		try {
			int wordCount = str.trim().split("\\s+").length;
			return wordCount;
		} catch (Exception e) {
			logException(e);
			return 0;
		}
	}

	// ////////SplitString//////////////////
	public String[] SplitString(String str) {

		try {
			int wordCount = WordCountInString(str);
			String[] wordsSplit = new String[wordCount];
			for (int i = 0; i < wordCount; i++) {
				wordsSplit[i] = "";
			}
			return wordsSplit;
		} catch (Exception e) {
			logException(e);
			return st1;
		}
	}
	
	///replace space with new line char
	public String replaceSpaceWithNewLine(String str, int wordCount) {
		try {
			for(int i = 0; i < wordCount; i++) {
				if(i < 2) {
					str = str.replaceFirst("\\s+", "*");
				}
				else {
					str = str.replaceFirst("\\s+", "\n");
					break;
				}
			}
			str = str.replace("*", " ");
			return str;
		}
		catch (Exception e) {
			logException(e);
			return str;
		}
	}

	// ////////replace comma,apostophy//////////////////
	public String replaceComma(String str) {

		try {
			String retStr = str;
			if (str.contains(","))
				retStr = str.replace(",", "*"); // comma to *
			if (str.contains("'"))
				retStr = str.replace("'", "#"); // apostrophe to #
			return retStr;
		} catch (Exception e) {
			logException(e);
			return "";
		}
	}

	// ////////retrieve comma,apostophy//////////////////
	public String retrieveComma(String str) {

		try {
			String retStr = str;
			if (str.contains(","))
				retStr = str.replace("*", ","); // * to comma
			if (str.contains("'"))
				retStr = str.replace("#", "'"); // # to apostrophe
			return retStr;
		} catch (Exception e) {
			logException(e);
			return "";
		}
	}

	// ////////timeInMillis//////////////////
	public String timeInMillis() {

		try {
			String retStr = "";
			long lDateTime = new Date().getTime();
			retStr = "" + lDateTime;
			return retStr;
		} catch (Exception e) {
			logException(e);
			return "";
		}
	}

	public String createFolder(String path) {

		try {

			File file = new File(path);

			if (!file.exists()) {
				if (file.mkdirs()) {
					logger.info("Directory " + file + " is created!");
				} else {
					logger.info("Failed to create directory!");
				}
			} else {
				logger.info("Failed to create directory as folder already exists");
			}
			return path;
		} catch (Exception e) {
			logException(e);
			return "";
		}
	}
	
	public boolean fileExists(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return false;
		}
		return true;
	}
	// ////////todays date folder//////////////////
	///// *****sent flag todayFolder as false if folder already
	// exists*****////////////
	public String createTodayFolder(String path, boolean todayFolder) {

		try {
			String retStr = "";
			String dateNow = "";

			if (todayFolder) {
				Date dt = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd_MMM_yyyy"); // format
																					// it
																					// as
																					// per
																					// your
																					// requirement
				dateNow = formatter.format(dt.getTime());
			}

			File file = new File(path + dateNow);
			retStr = path + dateNow;

			if (!file.exists()) {
				if (file.mkdirs()) {
					logger.info("Directory " + file + " is created!");
				} else {
					logger.info("Failed to create directory!");
				}
			} else {
//				logger.info("Failed to create directory as folder already exists");
			}
			return retStr;
		} catch (Exception e) {
			logException(e);
			return "";
		}
	}

	//// create file
	public void createFile(String filepath) {
		// This does not actually create a file
		File file = new File(filepath);

		try {
			// create a new file if it doesn't exist already
			file.createNewFile();

		} catch (IOException e) {
			e.printStackTrace();
			StackTraceElement[] stackTrace = e.getStackTrace();
		}
	}

	// ////////delete file//////////////////
	public boolean deleteFile(String path) {

		boolean retFlag = false;
		try {

			File file = new File(path);

			if (file.delete()) {
//				logger.info(file.getName() + " is deleted!");
				retFlag = true;
			} else {
				logger.warn("Delete operation is failed.");
				retFlag = false;
			}
			return retFlag;

		} catch (Exception e) {
			logException(e);
			return retFlag;

		}
	}

	/////file copy or copy file
	public void copyFileUsingStream(String source, String dest) throws IOException {
		
		File from = new File(source); 
		File to = new File(dest);
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(from);
	        os = new FileOutputStream(to);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } 
	    catch(Exception e) {
			logException(e);
	    }
	    finally {
	        is.close();
	        os.close();
	    }
	}
	
	////convert image to byte array
	public byte[] imageToByteArray(String filePath) throws IOException {
		BufferedImage bImage = ImageIO.read(new File(filePath));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, "jpg", bos );
		byte [] data = bos.toByteArray();
		
		return data;
	}
	
	// ////////Academic Year(24/12/2014)/////////////////
	public static String getAcademicYear(SessionData sessionData, String date) {

		String retAcademic = "", month = "", year = "";
		int monthStart = Integer.parseInt(sessionData.getConfigMap().get("ACADEMIC_START_MONTH"));
		
		try {
			if (date.contains("-")) {
				date = MM_ddlmmlyyyy(date);
			}
			month = date.substring(date.indexOf("/") + 1, date.lastIndexOf("/"));
			year = date.substring(date.lastIndexOf("/") + 1);

			
			for(int i = monthStart; i < (monthStart+5); i++) {
				retAcademic = year + "-" + String.format("%02d", (Integer.parseInt(year.substring(2)) + 1));
			}
			
			if (Integer.parseInt(month) >= monthStart) {
				retAcademic = year + "-" + String.format("%02d", (Integer.parseInt(year.substring(2)) + 1));
			} else {
				retAcademic = (Integer.parseInt(year) - 1) + "-"
						+ String.format("%02d", (Integer.parseInt(year.substring(2))));
			}
			
//			if (month.contains("06") || month.contains("07") || month.contains("08") || month.contains("09")
//					|| month.contains("10") || month.contains("11") || month.contains("12")) {
//				retAcademic = year + "-" + String.format("%02d", (Integer.parseInt(year.substring(2)) + 1));
//			} else {
//				retAcademic = (Integer.parseInt(year) - 1) + "-"
//						+ String.format("%02d", (Integer.parseInt(year.substring(2))));
//			}
			return retAcademic;
		} catch (Exception e) {
			logger.error(e);
			return retAcademic;
		}
	}

	// ////////Get previous year/////////////////
	public static String getPreviousYear(SessionData sessionData, String currAcademicYear) {

		String retPreviousYear = "";
		try {

			String previousYearDate = "";
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, -1);

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			previousYearDate = sdf.format(cal.getTime());
			retPreviousYear = getAcademicYear(sessionData,previousYearDate);
			return retPreviousYear;
		} catch (Exception e) {
			logger.error(e);
			return retPreviousYear;
		}
	}
	
	// ////////Get previous year/////////////////
	public static String getPreviousYearFromSelected(SessionData sessionData, String selAcademicYear) {

		String retPreviousYear = "";
		try {

			String previousYear = (Integer.parseInt(selAcademicYear.substring(0, selAcademicYear.indexOf("-")))-1) + "";
			retPreviousYear = previousYear + "-" + selAcademicYear.substring(2, 4);

			return retPreviousYear;
		} catch (Exception e) {
			logger.error(e);
			return retPreviousYear;
		}
	}

	/////////////// validate academic year/////////////
	public boolean validateAcademicYear(String academic_year) {
		boolean validateAcademic = true;
		String year4 = academic_year.substring(0, 4);
		String year2 = academic_year.substring(6, 7);
		if (!validateNumber(year4) || !validateNumber(year2)) {
			validateAcademic = false;
		}

		return validateAcademic;
	}

	// ////////get year from academic/////////////////
	public int yearFromAcademic(int month, String academic) {

		int retYear = 0;
		try {
			if (month == 6 || month == 7 || month == 8 || month == 9 || month == 10 || month == 11 || month == 12) {
				retYear = Integer.parseInt(academic.substring(0, 4));
				// retAcademic = year + "-" + String.format("%02d",
				// (Integer.parseInt(year.substring(2)) + 1));
			} else {
				retYear = Integer.parseInt(academic.substring(0, 2) + academic.substring(5, 7));
				// retAcademic = (Integer.parseInt(year) - 1) + "-" +
				// String.format("%02d", (Integer.parseInt(year.substring(2))));
			}
			return retYear;
		} catch (Exception e) {
			logException(e);
			return retYear;
		}
	}

	// ////////Get current date/////////////////
	public static String getCurrentDate() {

		String retCurrentDate = "";
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			retCurrentDate = sdf.format(cal.getTime());
			return retCurrentDate;
		} catch (Exception e) {
			logger.error(e);
			return retCurrentDate;
		}
	}
	
	// ////////Get current date in dd/MM/yyyy HH:mm format/////////////////
		public String getCurrentDateInHHmm() {

			String retCurrentDate = "";
			try {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				retCurrentDate = sdf.format(cal.getTime());
				return retCurrentDate;
			} catch (Exception e) {
				logException(e);
				return retCurrentDate;
			}
		}

	// ////////Get current year/////////////////
	public String getCurrentYear() {

		String retCurrentYear = "";
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			retCurrentYear = sdf.format(cal.getTime());
			return retCurrentYear;
		} catch (Exception e) {
			logException(e);
			return retCurrentYear;
		}
	}

	// ////////Get promote year/////////////////
	public String getPromoteYear(SessionData sessionData, String currAcademicYear) {

		String retPromoteYear = "";
		try {

			/////
			String previousYearDate = "";
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, -1);

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			previousYearDate = sdf.format(cal.getTime());
			String previousAcademicYr = getAcademicYear(sessionData,previousYearDate);

			retPromoteYear = previousAcademicYr + " to " + currAcademicYear;
			return retPromoteYear;
		} catch (Exception e) {
			logException(e);
			return retPromoteYear;
		}
	}

	// ////////Get Next year/////////////////
	public String getNextYear(SessionData sessionData, String currAcademicYear) {

		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 1);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return getAcademicYear(sessionData,sdf.format(cal.getTime()));
		} catch (Exception e) {
			logException(e);
			return "";
		}
	}
		
	// ////////Get current date in dd_MMM_yyyy/////////////////
	public String getCurrentDatein_dd_MMM_yyyy() {

		String retCurrentDate = "";
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy");
			retCurrentDate = sdf.format(cal.getTime());
			return retCurrentDate;
		} catch (Exception e) {
			logException(e);
			return retCurrentDate;
		}
	}

	// ////////validateSpecial/////////////////
	// true if special character present
	public static boolean validateSpecial(String valStr) {

		boolean retValidateSpecial = false;
		try {
			Pattern p = Pattern.compile("[^a-z0-9 ~#$*.]", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(valStr);
			retValidateSpecial = m.find();
			return retValidateSpecial;
		} catch (Exception e) {
			logger.error(e);
			return retValidateSpecial;
		}
	}

	// /////////////////////////////////
	public List<String> sortedHashMap(List<String> subjectList) throws Exception {

		List<String> retSubjectList = null;
		HashMap<Integer, String> hmap = new HashMap<Integer, String>();

		hmap.put(1, "A");
		hmap.put(10, "C");
		hmap.put(4, "Z");
		hmap.put(77, "Y");
		hmap.put(9, "P");
		hmap.put(66, "Q");
		hmap.put(0, "R");

		Map<Integer, String> map = new TreeMap<Integer, String>(hmap);
		Set set2 = map.entrySet();
		Iterator iterator2 = set2.iterator();
		while (iterator2.hasNext()) {
			Map.Entry me2 = (Map.Entry) iterator2.next();
		}
		return retSubjectList;
	}

	public String ifNullThenDash(String nullCheckStr) {
		String retResult = "";
		if (nullCheckStr != null) {
			nullCheckStr = nullCheckStr.trim();
		}

		if (nullCheckStr == null || nullCheckStr.equalsIgnoreCase(null)) {
			retResult = "-";
		} else if (nullCheckStr.equalsIgnoreCase("null") || nullCheckStr.equalsIgnoreCase("")) {
			retResult = "-";
		} else {
			retResult = nullCheckStr;
		}
		return retResult;
	}

	public String ifNullThenBlank(String nullCheck) {

		String retResult = "";
		if (nullCheck != null) {
			nullCheck = nullCheck.trim();
		}

		if (nullCheck == null || nullCheck.equalsIgnoreCase(null)) {
			retResult = "";
		} else if (nullCheck.equalsIgnoreCase("null") || nullCheck.equalsIgnoreCase("")) {
			retResult = "";
		} else {
			retResult = nullCheck;
		}
		return retResult;
	}

	public String ifNullThenSpace(String nullCheck) {

		String retResult = " ";
		if (nullCheck != null) {
			nullCheck = nullCheck.trim();
		}
		if (nullCheck.equalsIgnoreCase(null) || nullCheck.equalsIgnoreCase("")) {
			retResult = " ";
		} else {
			retResult = nullCheck;
		}
		return retResult;
	}

	public String getGradeFromMarks(double maxMarks, String subjectName, double marksObtained, String std) {
		String retGrade = "";
		double percentageObtained = (marksObtained * 100 / maxMarks);

		if (!std.equalsIgnoreCase("IX") && !std.equalsIgnoreCase("X") && !std.equalsIgnoreCase("XI")
				&& !std.equalsIgnoreCase("XII")) {
			if (percentageObtained > 100) {
				retGrade = "% greater than 100";
			} else if (percentageObtained >= 91) {
				retGrade = "A-1";
			} else if (percentageObtained >= 81 && percentageObtained < 91) {
				retGrade = "A-2";
			} else if (percentageObtained >= 71 && percentageObtained < 81) {
				retGrade = "B-1";
			} else if (percentageObtained >= 61 && percentageObtained < 71) {
				retGrade = "B-2";
			} else if (percentageObtained >= 51 && percentageObtained < 61) {
				retGrade = "C-1";
			} else if (percentageObtained >= 41 && percentageObtained < 51) {
				retGrade = "C-2";
			} else if (percentageObtained >= 33 && percentageObtained < 41) {
				retGrade = "D";
			} else if (percentageObtained >= 21 && percentageObtained < 33) {
				retGrade = "E-1";
			} else if (percentageObtained < 21) {
				retGrade = "E-2";
			}
		} else {
			if (percentageObtained >= 60) {
				retGrade = "A";
			} else if (percentageObtained >= 50 && percentageObtained < 60) {
				retGrade = "B";
			} else if (percentageObtained >= 35 && percentageObtained < 50) {
				retGrade = "C";
			} else if (percentageObtained < 35) {
				retGrade = "D";
			}
		}
		return retGrade;
	}
	
	public String getGradeFromPercent(double percentageObtained, String std) {

		if (std.equalsIgnoreCase("JR KG") || std.equalsIgnoreCase("SR KG")) {
			if (percentageObtained >= 91) {
				return "A-1";
			} else if (percentageObtained >= 81 && percentageObtained < 91) {
				return "A-2";
			} else if (percentageObtained >= 71 && percentageObtained < 81) {
				return "B-1";
			} else if (percentageObtained >= 61 && percentageObtained < 71) {
				return "B-2";
			} else if (percentageObtained >= 51 && percentageObtained < 61) {
				return "C-1";
			} else if (percentageObtained >= 41 && percentageObtained < 51) {
				return "C-2";
			} else if (percentageObtained >= 33 && percentageObtained < 41) {
				return "D";
			} else if (percentageObtained < 33) {
				return "E";
			}
		}
		else if (!std.equalsIgnoreCase("IX") && !std.equalsIgnoreCase("X") && !std.equalsIgnoreCase("XI")
				&& !std.equalsIgnoreCase("XII")) {
			if (percentageObtained >= 91) {
				return "A-1";
			} else if (percentageObtained >= 81 && percentageObtained < 91) {
				return "A-2";
			} else if (percentageObtained >= 71 && percentageObtained < 81) {
				return "B-1";
			} else if (percentageObtained >= 61 && percentageObtained < 71) {
				return "B-2";
			} else if (percentageObtained >= 51 && percentageObtained < 61) {
				return "C-1";
			} else if (percentageObtained >= 41 && percentageObtained < 51) {
				return "C-2";
			} else if (percentageObtained >= 33 && percentageObtained < 41) {
				return "D";
			} else if (percentageObtained >= 21 && percentageObtained < 33) {
				return "E-1";
			} else if (percentageObtained < 21) {
				return "E-2";
			}
		} 
		else {
			if (percentageObtained >= 60) {
				return "A";
			} else if (percentageObtained >= 50 && percentageObtained < 60) {
				return "B";
			} else if (percentageObtained >= 35 && percentageObtained < 50) {
				return "C";
			} else if (percentageObtained < 35) {
				return "D";
			}
		}
		return "-";
	}

	public String getPercentRangeFromPercent(double percentObtained, String std) {

			if (percentObtained >= 91) {
				return "91-100";
			} else if (percentObtained >= 81 && percentObtained < 91) {
				return "81-90";
			} else if (percentObtained >= 71 && percentObtained < 81) {
				return "71-80";
			} else if (percentObtained >= 61 && percentObtained < 71) {
				return "61-70";
			} else if (percentObtained >= 51 && percentObtained < 61) {
				return "51-60";
			} else if (percentObtained >= 41 && percentObtained < 51) {
				return "41-50";
			} else if (percentObtained >= 33 && percentObtained < 41) {
				return "33-40";
			} else if (percentObtained >= 21 && percentObtained < 33) {
				return "21-32";
			} else if (percentObtained < 21) {
				return "UP TO 20";
			}
		return "";
	}
	
	public double getPercentFromMarks(double maxMarks, String subjectName, double marksObtained) {
		double percentageObtained = (marksObtained * 100 / getConvertMarksForDivisor(maxMarks, 0, false));
		return percentageObtained;
	}

	public String getProgressFromPercentage(double percentageObtained) {

		String retProgress = "";

		if (percentageObtained >= 75) {
			retProgress = "Excellent";
		} else if (percentageObtained >= 60 && percentageObtained < 75) {
			retProgress = "Very Good";
		} else if (percentageObtained >= 50 && percentageObtained < 60) {
			retProgress = "Good";
		} else if (percentageObtained >= 35 && percentageObtained < 50) {
			retProgress = "Satisfactory";
		} else {
			retProgress = "Poor";
		}

		return retProgress;
	}

	/*********
	 * 9th, 10th final result 1-8th result
	 ***************************/
	public String getResult(Map subjectMarks, String std, Map maxMarksMap) {

		String retResult = "PASS";
		int failedInSubjects = 0;
		String subjectNameFromMap = "";

		Set subjectMarksSet = subjectMarks.entrySet();
		Iterator i = subjectMarksSet.iterator();
		// int subjectMapSize = studentMarks.size();

		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			String gradeFromMap = me.getValue().toString();
			String subjectFromMap = me.getKey().toString();
			String maxMarkValue = maxMarksMap.get(subjectFromMap).toString();
			int maxMarksForSubject = Double.valueOf(maxMarkValue).intValue();

			if (validateNumber(gradeFromMap)) {
				double subPercent = getPercentFromMarks(maxMarksForSubject, subjectFromMap,
						Integer.parseInt(gradeFromMap));
				if (subPercent < 35) {
					retResult = "F";
					failedInSubjects = failedInSubjects + 1;

					if (failedInSubjects > 1) {
						subjectNameFromMap = subjectNameFromMap + "*" + me.getKey().toString();
					} else {
						subjectNameFromMap = me.getKey().toString();
					}
				}
			} else if (gradeFromMap.contains("E") && !gradeFromMap.contains("RTE")) {
				retResult = "F";

				failedInSubjects = failedInSubjects + 1;

				if (failedInSubjects > 1 || !subjectNameFromMap.equalsIgnoreCase("")) {
					subjectNameFromMap = subjectNameFromMap + "*" + me.getKey().toString();
				} else {
					subjectNameFromMap = me.getKey().toString();
				}
			} else if ((gradeFromMap.contains("D") || gradeFromMap.contains("C-2")) && !gradeFromMap.contains("RTE")) {

				if (!subjectNameFromMap.equalsIgnoreCase("")) {
					subjectNameFromMap = subjectNameFromMap + "*" + me.getKey().toString();
				} else {
					subjectNameFromMap = me.getKey().toString();
				}
			}
		}

		if (failedInSubjects >= 1) {
			retResult = "F" + failedInSubjects + "|" + subjectNameFromMap.trim();
		} else {
			retResult = "PASS|" + subjectNameFromMap.trim();
		}
		return retResult;
	}

	/*********
	 * 9th & 10th semester result
	 * 
	 ***********************************/
	public String getResultSemester(Map subjectMarks, String std, TreeMap maxMarksMap, List<String> optionalSubject,
			TreeMap subjectMap, TreeMap marksBeforeGrace) {

		String retResult = "PASS";
		int failedInSubjects = 0;
		String subjectNameFromMap = "";

		try {
			Set subjectMarksSet = subjectMarks.entrySet();
			Iterator i = subjectMarksSet.iterator();
			// int subjectMapSize = studentMarks.size();

			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				String gradeFromMap = me.getValue().toString();
				if (!validateNumber(gradeFromMap)) {
					continue;
				}
				String subjectFromMap = me.getKey().toString();
				String isOptional = subjectMap.get(subjectFromMap).toString();
				double maxMarksForSubject = Double.parseDouble(maxMarksMap.get(subjectFromMap).toString());
				String passStatus = "";

				if (marksBeforeGrace.get(subjectFromMap) != null) {
					passStatus = marksBeforeGrace.get(subjectFromMap).toString();
					passStatus = passStatus.substring(passStatus.lastIndexOf("|") + 1);
				}

				if ((isOptional.equalsIgnoreCase("NO")
						|| (isOptional.equalsIgnoreCase("YES") && optionalSubject.contains(subjectFromMap + "_YES")))
						&& !passStatus.equalsIgnoreCase("P")) {
					if (validateNumber(gradeFromMap)) {
						double subPercent;
						subPercent = getPercentFromMarks(maxMarksForSubject, subjectFromMap,
								Double.parseDouble(gradeFromMap));
						if (subPercent < 35) {
							retResult = "F";
							failedInSubjects = failedInSubjects + 1;

							if (failedInSubjects > 1) {
								subjectNameFromMap = subjectNameFromMap + "*" + me.getKey().toString();
							} else {
								subjectNameFromMap = me.getKey().toString();
							}
						}
					} else if (gradeFromMap.contains("E")) {
						retResult = "F";

						failedInSubjects = failedInSubjects + 1;

						if (failedInSubjects > 1 || !subjectNameFromMap.equalsIgnoreCase("")) {
							subjectNameFromMap = subjectNameFromMap + "*" + me.getKey().toString();
							// retResult = "F";
							// retResult = "F" + failedInSubjects + "|" +
							// subjectNameFromMap;
						} else {
							subjectNameFromMap = me.getKey().toString();
							// retResult = retResult + "|" + subjectNameFromMap;
						}
					} else if (gradeFromMap.contains("D")) {

						if (!subjectNameFromMap.equalsIgnoreCase("")) {
							subjectNameFromMap = subjectNameFromMap + "*" + me.getKey().toString();
						} else {
							subjectNameFromMap = me.getKey().toString();
						}
					}
				}
			}

			if (failedInSubjects >= 1) {
				retResult = "F" + failedInSubjects + "|" + subjectNameFromMap;
			}
			return retResult;
		} catch (Exception e) {
			logException(e);
		}
		return retResult;
	}

	public void showMessageDialog(String displayMessage) {
		JOptionPane.showMessageDialog(null, displayMessage);
	}

	public boolean validateLogin(SessionData sessionData, String username, String password, String role) {
		boolean isValidate = false;
		if (!username.equalsIgnoreCase("") && !password.equalsIgnoreCase("") && !role.equalsIgnoreCase("")) {
			try {
				if (dbValidate.connectDatabase(sessionData)) {
					dbValidate.addPersonalDetailsColumns(sessionData);
					
					boolean validateFlag = dbValidate.validateUser(sessionData, username, password, role);
					if(sessionData.getUserStatus().equalsIgnoreCase("INACTIVE")){
						validateFlag = false;
						JOptionPane.showMessageDialog(null, "User "+sessionData.getUserName()+" is inactive. Please contact administrator.");
					}
					else if (validateFlag) {
						isValidate = true;
					} else {
						showMessageDialog("Please enter valid Username / Password / Role");
					}
				}
			} catch (Exception e1) {
				logException(e1);
			} finally {
				dbValidate.closeDatabase(sessionData);
			}
		} else {
			showMessageDialog("Please enter Username, Password & Role");
		}
		return isValidate;
	}

	///// daysBetween two dates///////
	/***
	 * if d1>d2 output is negative if d1<d2 output is positive
	 ****/
	public int daysBetween(Date d1, Date d2) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		int numberOfDays = 0;
		try {

			Date a1 = simpleDateFormat.parse(simpleDateFormat.format(d1));
			Date a2 = simpleDateFormat.parse(simpleDateFormat.format(d2));
			numberOfDays = (int) ((a2.getTime() - a1.getTime()) / (60 * 60 * 24 * 1000));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StackTraceElement[] stackTrace = e.getStackTrace();
		}
		return numberOfDays;
	}
	
///// date difference in minutes///////
	/***
	 * if d1>d2 output is negative if d1<d2 output is positive
	 ****/
	public boolean dateDifferenceInMinutes(Date d1, Date d2) {
			long diff = d2.getTime() - d1.getTime();
			long diffSeconds = diff / 1000 % 60;  
			long diffMinutes = diff / (60 * 1000) % 60;   
			long diffHours = diff / (60 * 60 * 1000);   
			long diffDays = diff / (60 * 60 * 24 * 1000);
			
		 if (diffDays > 0) {
            return false;
        } else if (diffHours > 0) {
            return false;
        } else if (diffMinutes > 0) {
            return false;
        }
		return true;
	}

	///// last day of month//////
	public String lastDayOfMonth(int month, int year) {
		Date date = null;
		DateFormat DATE_FORMAT = null;
		try {

			Calendar calendar = Calendar.getInstance();
			// passing month because 0-->jan, 1-->feb... 11-->dec
			calendar.set(year, month, 1);
			calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
			date = calendar.getTime();
			DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
			return DATE_FORMAT.format(date);
		} catch (Exception e) {
			logException(e);
		}
		return DATE_FORMAT.format(date);
	}

	///// month to integer//////
	public int monthInInteger(String month) {
		int monthInInteger = 0;
		monthInInteger = MonthList.valueOf(month).ordinal();
		return monthInInteger;
	}

	// validate adhaar card
	public static boolean validateAadharNumber(String aadharNumber) {
		Pattern aadharPattern = Pattern.compile("\\d{12}");
		boolean isValidAadhar = aadharPattern.matcher(aadharNumber).matches();
		/*
		 * if(isValidAadhar){ isValidAadhar =
		 * VerhoeffAlgorithm.validateVerhoeff(aadharNumber); }
		 */
		return isValidAadhar;
	}

	//// MotherBoardSerialNumber//////
	public String getMotherboardSN() {
		String result = "";
		try {
			File file = File.createTempFile("realhowto", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);

			String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
					+ "Set colItems = objWMIService.ExecQuery _ \n" + "   (\"Select * from Win32_BaseBoard\") \n"
					+ "For Each objItem in colItems \n" + "    Wscript.Echo objItem.SerialNumber \n"
					+ "    exit for  ' do the first cpu only! \n" + "Next \n";

			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				result += line;
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			StackTraceElement[] stackTrace = e.getStackTrace();
		}
		return result.trim();
	}

	//// HardDiskSerialNumbercfor drive//////
	public String getHardDiskSN(String drive) {
		String result = "";
		try {
			File file = File.createTempFile("realhowto", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);

			String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
					+ "Set colDrives = objFSO.Drives\n" + "Set objDrive = colDrives.item(\"" + drive + "\")\n"
					+ "Wscript.Echo objDrive.SerialNumber"; // see note
			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				result += line;
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			StackTraceElement[] stackTrace = e.getStackTrace();
		}
		return result.trim();
	}

	// /////////////////////////////////
	public List<String> breakSentence(String originalSentence, int charCount) throws Exception {

		List retbrokenSentenceList = new ArrayList();
		Pattern regex = Pattern.compile("(.{1," + charCount + "}(?:\\s|$))|(.{0," + charCount + "})", Pattern.DOTALL);
		Matcher regexMatcher = regex.matcher(originalSentence);
		while (regexMatcher.find()) {
			retbrokenSentenceList.add(regexMatcher.group());
		}

		return retbrokenSentenceList;
	}

	//// add \n to break/wrap single sentence
	public String wrapSentence(String originalSentence, int charCount) throws Exception {

		String retWrapSentence = "";
		Pattern regex = Pattern.compile("(.{1," + charCount + "}(?:\\s|$))|(.{0," + charCount + "})", Pattern.DOTALL);
		Matcher regexMatcher = regex.matcher(originalSentence);
		while (regexMatcher.find()) {
			retWrapSentence = retWrapSentence + regexMatcher.group() + "\n";
		}

		return retWrapSentence;
	}

	////////// yearSince/////////////////
	public String yearSince(SessionData sessionData, String date) {

		String retyearSince = "";
		String month = "";
		String year = "";
		boolean retFlag = false;
		int monthStart = Integer.parseInt(sessionData.getConfigMap().get("ACADEMIC_START_MONTH"));
		
		try {
			if (date.contains("-")) {
				date = MM_ddlmmlyyyy(date);
			}
			month = date.substring(date.indexOf("/") + 1, date.lastIndexOf("/"));
			year = date.substring(date.lastIndexOf("/") + 1);

			if (Integer.parseInt(month) >= monthStart) {
				retyearSince = year;
			} else {
				retyearSince = (Integer.parseInt(year) + 1) + "";
			}
			
//			if (month.contains("06") || month.contains("07") || month.contains("08") || month.contains("09")
//					|| month.contains("10") || month.contains("11") || month.contains("12")) {
//				retyearSince = year;
//			} else {
//				retyearSince = (Integer.parseInt(year) + 1) + "";
//			}
			return retyearSince;
		} catch (Exception e) {
			logException(e);
			return retyearSince;
		}
	}

	/////////// getStudyingSince//////////////////////////////
	public String getStudyingSince(SessionData sessionData, String dateAdmitted, String academic) {

		String studyingSince = "";
		String admittedAcademicYear = "";
		int monthAdmitted = 0;
		try {
			admittedAcademicYear = getAcademicYear(sessionData,dateAdmitted);
			monthAdmitted = Integer
					.parseInt(dateAdmitted.substring(dateAdmitted.indexOf("-") + 1, dateAdmitted.lastIndexOf("-")));
			if (admittedAcademicYear.equalsIgnoreCase(academic) && monthAdmitted >= 6) {
				studyingSince = getMonth(monthAdmitted) + " " + academic.substring(0, 4);
			} else if (admittedAcademicYear.equalsIgnoreCase(academic)) {
				studyingSince = getMonth(monthAdmitted) + " " + (Integer.parseInt(academic.substring(0, 4)) + 1);
			} else {
				studyingSince = "June " + academic.substring(0, 4);
			}
		} catch (Exception e) {
			logException(e);
		}
		return studyingSince;
	}

	public void setPasswordToExcel(SessionData sessionData, String fname, FileOutputStream fileOut) {

		FileInputStream input = null;
		BufferedInputStream binput = null;
		POIFSFileSystem poifs = null;
		String excelPass = sessionData.getConfigMap().get("EXCEL_SECURITY");

		try {
			input = new FileInputStream(fname);
			binput = new BufferedInputStream(input);
			poifs = new POIFSFileSystem(binput);

			Biff8EncryptionKey.setCurrentUserPassword(excelPass);

			HSSFWorkbook workbook = new HSSFWorkbook(poifs);

			fileOut = new FileOutputStream(fname);
			workbook.writeProtectWorkbook(Biff8EncryptionKey.getCurrentUserPassword(), "");
			workbook.write(fileOut);

		} catch (FileNotFoundException e) {
			logException(e);
		} catch (IOException e) {
			logException(e);
		} catch (Exception e) {
			logException(e);
		} finally {
			try {
				input.close();
				fileOut.close();
				binput.close();
				System.gc();
			} catch (IOException e) {
				logException(e);
			}
		}
	}

	public void progressBar(JFrame f, int percentage) {
		// JFrame f = new JFrame("Backup in progress");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = f.getContentPane();
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(percentage);
		progressBar.setStringPainted(true);
		Border border = BorderFactory.createTitledBorder("Reading...");
		progressBar.setBorder(border);
		content.add(progressBar, BorderLayout.NORTH);
		f.setBounds(300, 500, 90, 25);
		f.setSize(300, 100);
		// f.setVisible(true);
	}

	public void CreatePasswordProtectedZip(SessionData sessionData, String srcPath, String destPath) {
		try {
			String zipPass = sessionData.getConfigMap().get("ZIP_SECURITY");
			// This is name and path of zip file to be created
			ZipFile zipFile = new ZipFile(srcPath + ".zip");

			// Add files to be archived into zip file
			ArrayList<File> filesToAdd = new ArrayList<File>();
			File dir = new File(srcPath);
			String[] files = dir.list();
			if (files.length == 0) {
//				logger.info("The directory is empty");
			} else {
				for (String aFile : files) {
//					logger.info(aFile);
					filesToAdd.add(new File(srcPath + "\\" + aFile));
				}
			}

			// Initiate Zip Parameters which define various properties
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to deflate compression

			// DEFLATE_LEVEL_FASTEST - Lowest compression level but higher speed
			// of compression
			// DEFLATE_LEVEL_FAST - Low compression level but higher speed of
			// compression
			// DEFLATE_LEVEL_NORMAL - Optimal balance between compression
			// level/speed
			// DEFLATE_LEVEL_MAXIMUM - High compression level with a compromise
			// of speed
			// DEFLATE_LEVEL_ULTRA - Highest compression level but low speed
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

			// Set the encryption flag to true
			parameters.setEncryptFiles(true);

			// Set the encryption method to AES Zip Encryption
			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);

			// AES_STRENGTH_128 - For both encryption and decryption
			// AES_STRENGTH_192 - For decryption only
			// AES_STRENGTH_256 - For both encryption and decryption
			// Key strength 192 cannot be used for encryption. But if a zip file
			// already has a
			// file encrypted with key strength of 192, then Zip4j can decrypt
			// this file
			parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);

			// Set password
			parameters.setPassword(zipPass.toString());

			// Now add files to the zip file
			zipFile.addFiles(filesToAdd, parameters);
		} catch (ZipException e) {
			logException(e);
		}
	}

	public void deleteFolder(String SRC_FOLDER) {
		File directory = new File(SRC_FOLDER);

		// make sure directory exists
		if (!directory.exists()) {
			return;
		} else {
			try {
				delete(directory);
			} catch (IOException e) {
//				logger.warn("Warning for deleting folder "+SRC_FOLDER);
			}
		}
	}

	/// replace characters which cause issue in SQL query
	public static String replaceCommaApostrophy(String text) {
		String retText = text;

		if (retText.contains(".")) {
			retText = retText.replace(".", "$$");
		}
		
		if (retText.contains("&")) {
			retText = retText.replace("&", "$38$");
		}
		
		if (retText.contains("\n")) {
			retText = retText.replace("\n", "$39$");
		}
		
		if (retText.contains("xxxx")) {
			retText = retText.replace("xxxx", "$40$");
		}
		
		if (retText.contains("'")) {
			retText = retText.replace("'", "~");
		}

		if (retText.contains(",")) {
			retText = retText.replace(",", "*");
		}
		
		if (retText.contains(", ")) {
			retText = retText.replace(", ", "*");
		}

		if (retText.contains("-")) {
			retText = retText.replace("-", "#");
		}

		if (retText.contains("\"")) {
			retText = retText.replace("\"", "$");
		}
		
		if (retText.contains("/")) {
			retText = retText.replace("/", "$47$");
		}
		
		if (retText.equalsIgnoreCase("FUNCTION")) {
			retText = retText+"_999";
		}
		return retText;
	}

	public String revertCommaApostrophy(String text) {
		String retText = text;
		if (text == null) {
			return "";
		}
		
		if (retText.contains("$$")) {
			retText = retText.replace("$$", ".");
		}
		
		if (retText.contains("$38$")) {
			retText = retText.replace("$38$", "&");
		}
		
		if (retText.contains("$39$")) {
			retText = retText.replace("$39$", "\n");
		}
		
		if (retText.contains("$40$")) {
			retText = retText.replace("$40$", "xxxx");
		}
		
		if (retText.contains("$47$")) {
			retText = retText.replace("$47$", "/");
		}
		
		if (retText.contains("~")) {
			retText = retText.replace("~", "'");
		}

		if (retText.contains("*")) {
			retText = retText.replace("*", ",");
		}

		if (retText.contains("#")) {
			retText = retText.replace("#", "-");
		}

		if (retText.contains("$")) {
			retText = retText.replace("$", "\"");
		}
		
		if (retText.equalsIgnoreCase("FUNCTION_999")) {
			retText = retText.replace("_999", "");
		}
		
		return retText;
	}

	public static void delete(File file) throws IOException {

		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();
				logger.info("Directory is deleted : " + file.getAbsolutePath());

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					logger.info("Directory is deleted : " + file.getAbsolutePath());
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			logger.info("File is deleted : " + file.getAbsolutePath());
		}
	}

	public double convertMarks(String std, String subjectTypeMarks, String subjectName, String maxMarksforSubjectType,
			String examType, LinkedHashMap subjectConvertMap, String semester) {
		double convertedMarks = 0.0;
		double convertedMarksDouble = 0.0;
		double convertMarksTo = 0.0;
		double convertRatio = 1.0;
		LinkedHashMap subjectConvertMarks = new LinkedHashMap();
		int stdInt = RomanToInteger(std);

		try {
			subjectConvertMarks = (LinkedHashMap) subjectConvertMap.get(subjectName);
			
		if (subjectTypeMarks != null && !subjectTypeMarks.equalsIgnoreCase("null") && !subjectTypeMarks.trim().equalsIgnoreCase("") && 
				!subjectTypeMarks.equalsIgnoreCase("AB") && !subjectTypeMarks.equalsIgnoreCase("MG")) {
				if (Double.parseDouble(maxMarksforSubjectType) > 0) {
					convertMarksTo = Double.parseDouble((String) subjectConvertMarks
							.get(semester.toLowerCase() + "_" + examType.toLowerCase() + "_ct"));
					if (convertMarksTo >= 0.0 && Double.parseDouble(maxMarksforSubjectType) > 0) {// removed - && stdInt > 8
						convertRatio = convertMarksTo / Double.parseDouble(maxMarksforSubjectType);
						convertedMarksDouble = Double.parseDouble(subjectTypeMarks) * convertRatio;
						convertedMarks = convertedMarksDouble;
					} else {
						convertedMarks = Double.parseDouble(subjectTypeMarks);
					}
				}
			}
		} catch (Exception e) {
			convertedMarks = Double.parseDouble(subjectTypeMarks);
		}

//		return roundUp(convertedMarks); removed as roundup is adding one extra marks in result
		return convertedMarks;
	}

	public String getDriveSerialNumber() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuffer buffer = new StringBuffer();
		String cdrive = "";
		try {

			Process pb = new ProcessBuilder("cmd", "/c", "vol").start();
			InputStream in = pb.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null) {
				buffer.append(line + NEWLINE);
				if (line.contains("Serial")) {
					cdrive = line.substring(line.indexOf("is ") + 3);
				}
			}
		} catch (Exception e) {
			javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null, "Exception : " + e, null,
					javax.swing.JOptionPane.DEFAULT_OPTION);
		}
		return cdrive;
	}

	public static void writeToText(String filepath, String fileName, String content) {
		BufferedWriter bw = null;
		try {
			String mycontent = content + "\r\n";
			// Specify the file name and path here
			File file = new File(filepath + "\\" + fileName);

			/*
			 * This logic will make sure that the file gets created if it is not
			 * present at the specified location
			 */
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(mycontent);
			logger.info("File written Successfully");

		} catch (IOException ioe) {
			logger.error(ioe);
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}

	public String uploadMarksTemplate(SessionData sessionData, String fileLocation, String stdSel, String divSel,
			String academicSel, String examSel) {
		String displayMessage = "Marks upload Completed";
		try {
			List<String> subjectList = new ArrayList();
			List<String> studentMarksList = new ArrayList();
			HashMap<String, String> hmap = new HashMap<String, String>();
			String subject = "";
			String subjectName = "";
			String subExamType = "";
			String examType = "";
			String lvType = "";
			String maxSubMarks = "";
			String actualLvType = "";
			String lvTypeFromExcel = "";
			String exam = "";
			String academic_year = "";
			String gr_no = "";
			String roll_no = "";
			String name = "";
			String std = "";
			String div = "";
			String examPassed = "";
			int noOfRows = 0;
			int stdInt = RomanToInteger(stdSel);
			TreeMap maxMarksforSubject = new TreeMap();
			TreeMap maxMarksDetails = new TreeMap();
			PreparedStatement pstm = null;
			FileInputStream input = new FileInputStream(fileLocation);
			POIFSFileSystem fs = new POIFSFileSystem(input);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			Row row;
			JFrame f = null;
			dbValidate.connectDatabase(sessionData);

			screenWidth = screeWidth();
			screenHeight = screeHeight();
			mainCentre = (screenWidth - 150) / 2;

			if (examSel.equalsIgnoreCase("Semester 1")) {
				examPassed = "SEM1";
			} else if (examSel.equalsIgnoreCase("Semester 2")) {
				examPassed = "SEM2";
			}
			noOfRows = sheet.getLastRowNum();

			outerloop: for (int i = 0; i <= noOfRows; i++) {
				f = new JFrame("Uploaded " + i + " student out of " + noOfRows);
				f.setBounds(screenWidth / 2 - 150, screenHeight / 2, 90, 25);
				f.setSize(500, 0);
				f.setResizable(false);
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				try {

					row = sheet.getRow(i);
					int noOfColumns = sheet.getRow(0).getLastCellNum();// total
																		// number
																		// of
																		// cells

					if (i == 0) {
						for (int j = 0; j < noOfColumns; j++) {
							subjectList.add(row.getCell(j).toString());
							if (j > 6) {
								subjectName = row.getCell(j).toString();
								if(subjectName.contains("_SUM_")){
									subjectName = subjectName.substring(0, subjectName.indexOf("_SUM_"));
								}
								else{
									subjectName = subjectName.substring(0, subjectName.lastIndexOf("_"));
								}
								if (maxMarksforSubject.get(subjectName) == null) {
									maxMarksforSubject.put(subjectName, dbValidate.getMaxMarksForSubject(sessionData,
											subjectName, stdSel, academicSel, examPassed));
								}
							}
						}
					} else if(i != 1){//to skip max marks row
						exam = row.getCell(0).getStringCellValue().toString().trim();
						if (!exam.equalsIgnoreCase(examSel)) {
							displayMessage = "Selected exam does not match with exam specified in excel";
							showMessageDialog(displayMessage);
							displayMessage = "Marks upload failed.";
							f.setVisible(false);
							break outerloop;
						} else if (exam.equalsIgnoreCase("Semester 1")) {
							examType = "F";
						} else if (exam.equalsIgnoreCase("Semester 2")) {
							examType = "S";
						}
						academic_year = row.getCell(1).getStringCellValue().toString().trim();
						if (!academic_year.equalsIgnoreCase(academicSel)) {
							displayMessage = "Selected div academic year not match with academic year specified in excel";
							showMessageDialog(displayMessage);
							displayMessage = "Marks upload failed.";
							f.setVisible(false);
							break outerloop;
						}
						gr_no = row.getCell(2).getStringCellValue();
						roll_no = row.getCell(3).getStringCellValue().toString().trim();
						name = row.getCell(4).getStringCellValue().toString().trim();
						std = row.getCell(5).getStringCellValue().toString().trim();
						if (!std.equalsIgnoreCase(stdSel)) {
							displayMessage = "Selected std does not match with std specified in excel";
							showMessageDialog(displayMessage);
							displayMessage = "Marks upload failed.";
							f.setVisible(false);
							break outerloop;
						}
						div = row.getCell(6).getStringCellValue().toString().trim();
						if (!div.equalsIgnoreCase(divSel)) {
							displayMessage = "Selected div does not match with div specified in excel";
							showMessageDialog(displayMessage);
							displayMessage = "Marks upload failed.";
							f.setVisible(false);
							break outerloop;
						}

						for (int k = 7; k < noOfColumns; k++) {
							try {
								studentMarksList.clear();
								String marksObtained = row.getCell(k).toString();
								if (!validateNumber(marksObtained) && !marksObtained.equalsIgnoreCase("NA")
										&& !marksObtained.trim().equalsIgnoreCase("") && !(marksObtained.equalsIgnoreCase("MG")
												|| marksObtained.equalsIgnoreCase("AB"))) {
									showMessageDialog(
											"Please validate all marks. It should contain intger value or MG or AB only");
									// showMessageDialog(displayMessage);
									displayMessage = "Marks upload failed.";
									f.setVisible(false);
									// showMessageDialog(displayMessage);
									break outerloop;
								} else if (!marksObtained.trim().equalsIgnoreCase("")) {
									if (marksObtained.trim().equalsIgnoreCase("NA")) {
										marksObtained = "0";
										studentMarksList.add(gr_no + "|" + roll_no + "||" + name + "|||" + marksObtained);
									} else {
										studentMarksList.add(gr_no + "|" + roll_no + "||" + name + "|||" + marksObtained);
									}
									subject = subjectList.get(k).substring(0, subjectList.get(k).lastIndexOf("_"));
									if(subject.contains("_SUM")){
										subject = subject.substring(0, subject.lastIndexOf("_"));
									}
									subExamType = subjectList.get(k);
									if(!subExamType.contains("_SUM_")){
										lvTypeFromExcel = subExamType.substring(subExamType.lastIndexOf("_") + 1);
									}
									else{
										lvTypeFromExcel = subExamType.substring(subExamType.lastIndexOf("_SUM") + 1);
									}
									
									if (lvTypeFromExcel.equalsIgnoreCase("FUT")
											|| lvTypeFromExcel.equalsIgnoreCase("SUT")
											|| lvTypeFromExcel.equalsIgnoreCase("TEST")) {
										actualLvType = "OBT";
									} else if (stdInt > 8 && (lvTypeFromExcel.equalsIgnoreCase("ACTIVITY") || lvTypeFromExcel.equalsIgnoreCase("PROJ"))) {
										actualLvType = "PRACT";
									} else if (lvTypeFromExcel.equalsIgnoreCase("ACT")) {
										actualLvType = "ACTIVITY";
									} else if (lvTypeFromExcel.contains("SUM_")) {
										actualLvType = lvTypeFromExcel.substring(lvTypeFromExcel.indexOf("_")+1)+"1";
									} else {
										actualLvType = lvTypeFromExcel;
									}
									
									if(lvTypeFromExcel.contains("SUM_")){
										lvType = actualLvType.substring(0, 3)+"1";
									}
									else{
										lvType = actualLvType.substring(0, 3);
									}
									
									maxSubMarks = (((TreeMap<?, ?>) maxMarksforSubject.get(subject)).get(actualLvType)) + "";

									if (marksObtained.equalsIgnoreCase("AB") || marksObtained.equalsIgnoreCase("MG") || marksObtained.equalsIgnoreCase("RTE")) {
										dbValidate.updateSubMarks(sessionData, studentMarksList, academic_year, std,
												div, subject, subExamType, examType, lvType, maxSubMarks, actualLvType);
									} else if (!validateNumber(marksObtained)) {
										displayMessage = "GR No. " + gr_no
												+ " => Marks obtained is not numeric/AB/MG/RTE for Subject " + subject
												+ " type " + lvTypeFromExcel;
										f.setVisible(false);
										showMessageDialog(displayMessage);
										displayMessage = "Marks upload failed..";
										break outerloop;
									} else if (Double.parseDouble(marksObtained) > Double.parseDouble(maxSubMarks)) {
										displayMessage = "GR No. " + gr_no + " => Marks obtained (" + marksObtained
												+ ") is greater than max marks (" + maxSubMarks + ") " + "for Subject "
												+ subject + " type " + lvTypeFromExcel;
										f.setVisible(false);
										showMessageDialog(displayMessage);
										displayMessage = "Marks upload failed..";
										break outerloop;
									} else
										if (!maxSubMarks.equalsIgnoreCase(null)) {
										dbValidate.updateSubMarks(sessionData, studentMarksList, academic_year, std,
												div, subject, subExamType, examType, lvType, maxSubMarks, actualLvType);
									}
								}
							} catch (Exception ex) {
								double marksObtained = (double) row.getCell(k).getNumericCellValue();
								studentMarksList.add(gr_no + "|" + roll_no + "||" + name + "|||" + marksObtained);
								subject = subjectList.get(k).substring(0, subjectList.get(k).lastIndexOf("_"));
								subExamType = subjectList.get(k);
								lvTypeFromExcel = subjectList.get(k).substring(subjectList.get(k).lastIndexOf("_") + 1);
								if (lvTypeFromExcel.equalsIgnoreCase("FUT") || lvTypeFromExcel.equalsIgnoreCase("SUT")
										|| lvTypeFromExcel.equalsIgnoreCase("TEST")) {
									actualLvType = "OBT";
								} else if (lvTypeFromExcel.equalsIgnoreCase("ACTIVITY") || lvTypeFromExcel.equalsIgnoreCase("PROJ")) {
									actualLvType = "PRACT";
								} else {
									actualLvType = lvTypeFromExcel;
								}
								lvType = actualLvType.substring(0, 3);
								maxSubMarks = (((TreeMap<?, ?>) maxMarksforSubject.get(subject)).get(actualLvType)) + "";
								if (marksObtained > Integer.parseInt(maxSubMarks)) {
									displayMessage = "GR No. " + gr_no + " => Marks obtained (" + marksObtained
											+ ") is greater than max marks (" + maxSubMarks + ") " + "for Subject "
											+ subject + " type " + lvTypeFromExcel;
									f.setVisible(false);
									showMessageDialog(displayMessage);
									displayMessage = "Marks upload failed..";
									showMessageDialog(displayMessage);
									break outerloop;
								} else if (!maxSubMarks.equalsIgnoreCase(null)) {
									dbValidate.updateSubMarks(sessionData, studentMarksList, academic_year, std, div,
											subject, subExamType, examType, lvType, maxSubMarks, actualLvType);
								}
							}
						}
					}
				} catch (Exception e) {
					f.setVisible(false);
					logException(e);
					StackTraceElement[] stackTrace = e.getStackTrace();
				}
				f.setVisible(false);
			}
			f.setVisible(false);
			input.close();
		} catch (Exception e) {
			logException(e);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
		return displayMessage;
	}
	
	public String uploadAllMarksTemplate(SessionData sessionData, String fileLocation, String stdSel, String divSel,
			String academicSel, String examSel, String type) {
		String displayMessage = "Marks upload Completed";
		try {
			List<String> subjectList = new ArrayList();
			List<String> studentMarksList = new ArrayList();
			HashMap<String, String> hmap = new HashMap<String, String>();
			String subject = "";
			String subjectName = "";
			String subExamType = "";
			String examType = "";
			String lvType = "";
			String maxSubMarks = "";
			String actualLvType = "";
			String lvTypeFromExcel = "";
			String exam = "";
//			String academic_year = "";
			String gr_no = "";
			String roll_no = "";
			String name = "";
			String std = "";
			String div = "";
			String examPassed = "";
			int noOfRows = 0;
			int noOfColumns = 0;
			boolean validFlag = true;
			int stdInt = RomanToInteger(stdSel);
			TreeMap maxMarksforSubject = new TreeMap();
			TreeMap maxMarksDetails = new TreeMap();
			PreparedStatement pstm = null;
			FileInputStream input = new FileInputStream(fileLocation);
			POIFSFileSystem fs = new POIFSFileSystem(input);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			Row row;
			JFrame f = null;
			dbValidate.connectDatabase(sessionData);

			screenWidth = screeWidth();
			screenHeight = screeHeight();
			mainCentre = (screenWidth - 150) / 2;

			if (examSel.equalsIgnoreCase("Semester 1")) {
				examPassed = "SEM1";
			} else if (examSel.equalsIgnoreCase("Semester 2")) {
				examPassed = "SEM2";
			}
			noOfRows = sheet.getLastRowNum() + 1;

			outerloop: for (int i = 0; i < noOfRows; i++) {
				f = new JFrame("Uploaded " + i + " student out of " + noOfRows);
				f.setBounds(screenWidth / 2 - 150, screenHeight / 2, 90, 25);
				f.setSize(500, 0);
				f.setResizable(false);
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				try {

					row = sheet.getRow(i);

					if (i == 1) {
						noOfColumns = sheet.getRow(1).getLastCellNum();// total
						// number
						// of
						// cells
						for (int j = 0; j < noOfColumns; j++) {
							subjectList.add(row.getCell(j).toString());
							if (j > 5) {
								subjectName = row.getCell(j).toString();
								if(subjectName.contains("_SUM_")){
									subjectName = subjectName.substring(0, subjectName.indexOf("_SUM_"));
								}
								else{
									subjectName = subjectName.substring(0, subjectName.lastIndexOf("_"));
								}
								if (maxMarksforSubject.get(subjectName) == null) {
									maxMarksforSubject.put(subjectName, dbValidate.getMaxMarksForSubject(sessionData,
											subjectName, stdSel, academicSel, examPassed));
								}
							}
						}
					} else if(i > 1){//to skip max marks row
						exam = row.getCell(5).getStringCellValue().toString().trim();
						if(exam.equalsIgnoreCase("Max Marks")) {
							f.setVisible(false);
							continue;
						}
						if (!exam.equalsIgnoreCase(examSel)) {
							displayMessage = "Selected exam does not match with exam specified in excel";
							showMessageDialog(displayMessage);
							displayMessage = "Marks upload failed.";
							f.setVisible(false);
							break outerloop;
						} else if (exam.equalsIgnoreCase("Semester 1")) {
							examType = "F";
						} else if (exam.equalsIgnoreCase("Semester 2")) {
							examType = "S";
						}
//						academic_year = row.getCell(1).getStringCellValue().toString().trim();
						if (!academicSel.equalsIgnoreCase(academicSel)) {
							displayMessage = "Selected div academic year not match with academic year specified in excel";
							showMessageDialog(displayMessage);
							displayMessage = "Marks upload failed.";
							f.setVisible(false);
							break outerloop;
						}
						gr_no = row.getCell(1).getStringCellValue();
						roll_no = row.getCell(0).getStringCellValue().toString().trim();
						name = row.getCell(2).getStringCellValue().toString().trim();
						std = row.getCell(3).getStringCellValue().toString().trim();
						if (!std.equalsIgnoreCase(stdSel)) {
							displayMessage = "Selected std does not match with std specified in excel";
							showMessageDialog(displayMessage);
							displayMessage = "Marks upload failed.";
							f.setVisible(false);
							break outerloop;
						}
						div = row.getCell(4).getStringCellValue().toString().trim();
						if (!div.equalsIgnoreCase(divSel)) {
							displayMessage = "Selected div does not match with div specified in excel";
							showMessageDialog(displayMessage);
							displayMessage = "Marks upload failed.";
							f.setVisible(false);
							break outerloop;
						}

						for (int k = 6; k < noOfColumns; k++) {
							try {
								studentMarksList.clear();
								String marksObtained = row.getCell(k).toString();
								if(marksObtained.equalsIgnoreCase("-")) {
									continue;
								}
								if (!validateNumber(marksObtained) && !marksObtained.equalsIgnoreCase("NA")
										&& !marksObtained.trim().equalsIgnoreCase("") && !(marksObtained.equalsIgnoreCase("MG")
												|| marksObtained.equalsIgnoreCase("AB") || marksObtained.equalsIgnoreCase("RTE")) && !type.equalsIgnoreCase("Remark")) {
									showMessageDialog(
											"Please validate all marks. It should contain intger value or MG or AB or RTE only");
									// showMessageDialog(displayMessage);
									displayMessage = "Marks upload failed.";
									f.setVisible(false);
									// showMessageDialog(displayMessage);
									break outerloop;
								} else if (!marksObtained.trim().equalsIgnoreCase("") && !type.equalsIgnoreCase("Remark")) {
									if (marksObtained.trim().equalsIgnoreCase("NA")) {
										marksObtained = "0";
										studentMarksList.add(gr_no + "|" + roll_no + "||" + name + "|||" + marksObtained);
									} else {
										studentMarksList.add(gr_no + "|" + roll_no + "||" + name + "|||" + marksObtained);
									}
									subject = subjectList.get(k).substring(0, subjectList.get(k).lastIndexOf("_"));
									if(subject.contains("_SUM")){
										subject = subject.substring(0, subject.lastIndexOf("_"));
									}
									subExamType = subjectList.get(k);
									if(!subExamType.contains("_SUM_")){
										lvTypeFromExcel = subExamType.substring(subExamType.lastIndexOf("_") + 1);
									}
									else{
										lvTypeFromExcel = subExamType.substring(subExamType.lastIndexOf("_SUM") + 1);
									}

									actualLvType = getActualLvType(lvTypeFromExcel, stdInt);
									/*if (lvTypeFromExcel.equalsIgnoreCase("FUT")
											|| lvTypeFromExcel.equalsIgnoreCase("SUT")
											|| lvTypeFromExcel.equalsIgnoreCase("TEST")) {
										actualLvType = "OBT";
									} else if (stdInt > 8 && (lvTypeFromExcel.equalsIgnoreCase("ACTIVITY") || lvTypeFromExcel.equalsIgnoreCase("PROJ"))) {
										actualLvType = "PRACT";
									} else if (lvTypeFromExcel.equalsIgnoreCase("ACT")) {
										actualLvType = "ACTIVITY";
									} else if (lvTypeFromExcel.contains("SUM_")) {
										actualLvType = lvTypeFromExcel.substring(lvTypeFromExcel.indexOf("_")+1)+"1";
									} else {
										actualLvType = lvTypeFromExcel;
									}*/
									
									if(lvTypeFromExcel.contains("SUM_")){
										lvType = actualLvType.substring(0, 3)+"1";
									}
									else{
										lvType = actualLvType.substring(0, 3);
									}
									
									maxSubMarks = (((TreeMap<?, ?>) maxMarksforSubject.get(subject)).get(actualLvType)) + "";

									if (marksObtained.equalsIgnoreCase("AB") || marksObtained.equalsIgnoreCase("MG") || marksObtained.equalsIgnoreCase("RTE")) {
										dbValidate.updateSubMarks(sessionData, studentMarksList, academicSel, std,
												div, subject, subExamType, examType, lvType, maxSubMarks, actualLvType);
									} else if (!validateNumber(marksObtained)) {
										displayMessage = "GR No. " + gr_no
												+ " => Marks obtained is not numeric/AB/MG/RTE for Subject " + subject
												+ " type " + lvTypeFromExcel;
										f.setVisible(false);
										showMessageDialog(displayMessage);
										displayMessage = "Marks upload failed..";
										break outerloop;
									} else if (Double.parseDouble(marksObtained) > Double.parseDouble(maxSubMarks)) {
										displayMessage = "GR No. " + gr_no + " => Marks obtained (" + marksObtained
												+ ") is greater than max marks (" + maxSubMarks + ") " + "for Subject "
												+ subject + " type " + lvTypeFromExcel;
										f.setVisible(false);
										showMessageDialog(displayMessage);
										displayMessage = "Marks upload failed..";
										break outerloop;
									} else
										if (!maxSubMarks.equalsIgnoreCase(null)) {
										dbValidate.updateSubMarks(sessionData, studentMarksList, academicSel, std,
												div, subject, subExamType, examType, lvType, maxSubMarks, actualLvType);
									}
								}
								else if(type.equalsIgnoreCase("Remark")) {
									subExamType = subjectList.get(k);
									if(!subExamType.contains("_SUM_")){
										lvTypeFromExcel = subExamType.substring(subExamType.lastIndexOf("_") + 1);
									}
									else{
										lvTypeFromExcel = subExamType.substring(subExamType.lastIndexOf("_SUM") + 1);
									}
									
									actualLvType = lvTypeFromExcel;
									if(lvTypeFromExcel.contains("SUM_")){
										lvType = actualLvType.substring(0, 3)+"1";
									}
									else{
										lvType = actualLvType.substring(0, 3);
									}
									
									studentMarksList.add(gr_no + "|" + roll_no + "||" + name + "|||" + marksObtained);
									subject = subjectList.get(k).substring(0, subjectList.get(k).lastIndexOf("_"));
									validFlag = dbValidate.updateSubMarks(sessionData, studentMarksList, academicSel, std,
											div, subject, subExamType, examType, lvType, maxSubMarks, actualLvType);
									if(!validFlag) {
										break outerloop;
									}
								}
							} catch (Exception ex) {
								double marksObtained = (double) row.getCell(k).getNumericCellValue();
								studentMarksList.add(gr_no + "|" + roll_no + "||" + name + "|||" + marksObtained);
								subject = subjectList.get(k).substring(0, subjectList.get(k).lastIndexOf("_"));
								subExamType = subjectList.get(k);
								lvTypeFromExcel = subjectList.get(k).substring(subjectList.get(k).lastIndexOf("_") + 1);
								if (lvTypeFromExcel.equalsIgnoreCase("FUT") || lvTypeFromExcel.equalsIgnoreCase("SUT")
										|| lvTypeFromExcel.equalsIgnoreCase("TEST")) {
									actualLvType = "OBT";
								} else if (lvTypeFromExcel.equalsIgnoreCase("ACTIVITY") || lvTypeFromExcel.equalsIgnoreCase("PROJ")) {
									actualLvType = "PRACT";
								} else {
									actualLvType = lvTypeFromExcel;
								}
								lvType = actualLvType.substring(0, 3);
								maxSubMarks = (((TreeMap<?, ?>) maxMarksforSubject.get(subject)).get(actualLvType)) + "";
								if (marksObtained > Integer.parseInt(maxSubMarks)) {
									displayMessage = "GR No. " + gr_no + " => Marks obtained (" + marksObtained
											+ ") is greater than max marks (" + maxSubMarks + ") " + "for Subject "
											+ subject + " type " + lvTypeFromExcel;
									f.setVisible(false);
									showMessageDialog(displayMessage);
									displayMessage = "Marks upload failed..";
									showMessageDialog(displayMessage);
									break outerloop;
								} else if (!maxSubMarks.equalsIgnoreCase(null)) {
									dbValidate.updateSubMarks(sessionData, studentMarksList, academicSel, std, div,
											subject, subExamType, examType, lvType, maxSubMarks, actualLvType);
								}
							}
						}
					}
				} catch (Exception e) {
					f.setVisible(false);
					logException(e);
					StackTraceElement[] stackTrace = e.getStackTrace();
					displayMessage = "Marks upload failed.";
				}
				f.setVisible(false);
			}
			f.setVisible(false);
			input.close();
		} catch (Exception e) {
			logException(e);
			if(e.getMessage().contains("2007")) {
				displayMessage = "Upload file with .xls extension";
			}
			else {
				displayMessage = "Marks upload failed.";
			}
			
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
		return displayMessage;
	}
	
	public String uploadAllRemarksTemplate(SessionData sessionData, String fileLocation, String stdSel, String divSel,
			String academicSel, String examSel, String type) {
		String displayMessage = "Remarks upload Completed";
		try {
			List<String> subjectList = new ArrayList();
			List<String> studentMarksList = new ArrayList();
			HashMap<String, String> hmap = new HashMap<String, String>();
			String subject = "";
			String subjectName = "";
			String subExamType = "";
			String examType = "";
			String lvType = "";
			String maxSubMarks = "";
			String actualLvType = "";
			String lvTypeFromExcel = "";
			String exam = "";
			String gr_no = "";
			String roll_no = "";
			String name = "";
			String std = "";
			String div = "";
			String examPassed = "";
			int noOfRows = 0;
			int noOfColumns = 0;
			boolean validFlag = true;
			int stdInt = RomanToInteger(stdSel);
			TreeMap maxMarksforSubject = new TreeMap();
			TreeMap maxMarksDetails = new TreeMap();
			LinkedHashMap subjectTitleMap = new LinkedHashMap<>();
			LinkedHashMap<String,LinkedHashMap<String, String>> studentRemarkMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
			PreparedStatement pstm = null;
			FileInputStream input = new FileInputStream(fileLocation);
			POIFSFileSystem fs = new POIFSFileSystem(input);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			Row row;
			JFrame f = null;
			dbValidate.connectDatabase(sessionData);

			screenWidth = screeWidth();
			screenHeight = screeHeight();
			mainCentre = (screenWidth - 150) / 2;

			if (examSel.equalsIgnoreCase("Semester 1")) {
				examPassed = "SEM1";
			} else if (examSel.equalsIgnoreCase("Semester 2")) {
				examPassed = "SEM2";
			}
			noOfRows = sheet.getLastRowNum();

			outerloop: for (int i = 0; i <= noOfRows; i++) {
				f = new JFrame("Uploaded " + i + " student out of " + noOfRows);
				f.setBounds(screenWidth / 2 - 150, screenHeight / 2, 90, 25);
				f.setSize(500, 0);
				f.setResizable(false);
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				try {

					row = sheet.getRow(i);

					if (i == 1) {
						noOfColumns = sheet.getRow(1).getLastCellNum();// total
						// number
						// of
						// cells
						for (int j = 0; j < noOfColumns; j++) {
							subjectList.add(row.getCell(j).toString());
							if (j > 5) {
								subjectName = row.getCell(j).toString();
								subjectName = subjectName.substring(0, subjectName.lastIndexOf("_"));
								subjectTitleMap.put(subjectName, subjectName);
							}
						}
					} else if(i > 1){//to skip max marks row
						exam = row.getCell(5).getStringCellValue().toString().trim();
						if (!exam.equalsIgnoreCase(examSel)) {
							displayMessage = "Selected exam does not match with exam specified in excel";
							showMessageDialog(displayMessage);
							displayMessage = "Remarks upload failed.";
							f.setVisible(false);
							break outerloop;
						} else if (exam.equalsIgnoreCase("Semester 1")) {
							examType = "SEM1";
						} else if (exam.equalsIgnoreCase("Semester 2")) {
							examType = "SEM2";
						} else if (exam.equalsIgnoreCase("Final")) {
							examType = "FINAL";
						}
//						academic_year = row.getCell(1).getStringCellValue().toString().trim();
						if (!academicSel.equalsIgnoreCase(academicSel)) {
							displayMessage = "Selected div academic year not match with academic year specified in excel";
							showMessageDialog(displayMessage);
							displayMessage = "Marks upload failed.";
							f.setVisible(false);
							break outerloop;
						}
						LinkedHashMap<String, String> grDetail = new LinkedHashMap<String, String>();
						gr_no = row.getCell(1).getStringCellValue();
						roll_no = row.getCell(0).getStringCellValue().toString().trim();
						name = row.getCell(2).getStringCellValue().toString().trim();
						std = row.getCell(3).getStringCellValue().toString().trim();
						grDetail.put("name", name);
						grDetail.put("rollNo", roll_no);
						
						if (!std.equalsIgnoreCase(stdSel)) {
							displayMessage = "Selected std does not match with std specified in excel";
							showMessageDialog(displayMessage);
							displayMessage = "Marks upload failed.";
							f.setVisible(false);
							break outerloop;
						}
						div = row.getCell(4).getStringCellValue().toString().trim();
						if (!div.equalsIgnoreCase(divSel)) {
							displayMessage = "Selected div does not match with div specified in excel";
							showMessageDialog(displayMessage);
							displayMessage = "Marks upload failed.";
							f.setVisible(false);
							break outerloop;
						}

						for (int k = 6; k < noOfColumns; k++) {
							try {
								studentMarksList.clear();
								String remarksObtained = row.getCell(k).toString();
								if(type.equalsIgnoreCase("Remark") && !remarksObtained.trim().equalsIgnoreCase("")) {
									subExamType = subjectList.get(k);
									lvTypeFromExcel = subExamType.substring(subExamType.lastIndexOf("_") + 1);
									
									actualLvType = lvTypeFromExcel;
									lvType = actualLvType.substring(0, 3);
									
									studentMarksList.add(gr_no + "|" + roll_no + "||" + name + "|||" + remarksObtained);
									subject = subjectList.get(k).substring(0, subjectList.get(k).lastIndexOf("_"));
									grDetail.put(subject+"_REM"+examType, remarksObtained);
									studentRemarkMap.put(gr_no, grDetail);
									if(!validFlag) {
										break outerloop;
									}
								}
							} catch (Exception ex) {
								logException(ex);
							}
						}
					}
				} catch (Exception e) {
					f.setVisible(false);
					logException(e);
					StackTraceElement[] stackTrace = e.getStackTrace();
				}
				f.setVisible(false);
			}
			if(studentRemarkMap.size()>0) {
				dbValidate.updateRemarkResultMap(sessionData, academicSel, std, div, examType, subjectTitleMap, studentRemarkMap);
			}
			f.setVisible(false);
			input.close();
		} catch (Exception e) {
			logException(e);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
		return displayMessage;
	}

	public LinkedHashMap<String, LinkedHashMap<String, String>> cloneLinkedHashMap(LinkedHashMap originalMap) {
		LinkedHashMap cloneMap = new LinkedHashMap();
		Set subjectMapSet = originalMap.entrySet();
		Iterator subjectMapItr = subjectMapSet.iterator();
		while (subjectMapItr.hasNext()) {
			Map.Entry me = (Map.Entry) subjectMapItr.next();
			cloneMap.put(me.getKey(), (LinkedHashMap) me.getValue());
		}
		return cloneMap;
	}

	public String getDriveName() {
		String driveTmp = "";
		String driveName = "C:";
		boolean canWrite = false;
		try {
			File[] drives = File.listRoots();
			if (drives != null && drives.length > 0) {
				for (File aDrive : drives) {
					canWrite = aDrive.canWrite();
					driveTmp = aDrive.toString();
					if (canWrite && !driveTmp.contains("C:")) {
						driveName = driveTmp.substring(0, 2);
						break;
					}
				}
			}
			if((System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
				driveName = "/Users/yashpal/Documents/Maauli_School_App";
			}
		} catch (Exception e) {
			logException(e);
		}
		return driveName;
	}

	private Chunk returnCorrectColor(char letter) {
		Font RED_NORMAL = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED);
		Font BLUE_BOLD = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
		Font GREEN_ITALIC = new Font(FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.GREEN);
		Chunk B = new Chunk("b", BLUE_BOLD);
		Chunk G = new Chunk("g", GREEN_ITALIC);

		if (letter == 'b') {
			return B;
		} else if (letter == 'g') {
			return G;
		}
		return new Chunk(String.valueOf(letter), RED_NORMAL);
	}

	public String resultAverageSheet(SessionData sessionData, String sec, String academic, LinkedHashMap grStudentMap,
			List<String> subjectTitleList, String exam, String std, String div, String note,
			Map<String, String> maxMarksMapOrder, Map<String, String> gradeMarksMapOrder) {
		String status = "";
		String semester = "";
		String subjectMarksObtained = "";
		String dispMarks = "";
		String dispPassStatus = "";
		String dispReason = "";
		String dispAbsentMarks = "";
		List<String> printList = new ArrayList<String>();
		String studentDetails = "";
		String sheetFirstRowHeader = "";
		String sheetName = "";

		try {
			if (exam.equalsIgnoreCase("Semester 1")) {
				semester = "SEM1";
				sheetName = semester + " SHEET";
			} else if (exam.equalsIgnoreCase("Semester 2")) {
				semester = "SEM2";
				sheetName = semester + " SHEET";
			} else {
				semester = "FINAL";
				sheetName = "AVERAGE SHEET";
			}

			sheetFirstRowHeader = "DOB|G.No.|R.No.|Name";
			for (String item : subjectTitleList) {
				sheetFirstRowHeader = sheetFirstRowHeader + "|" + item;
			}
			sheetFirstRowHeader = sheetFirstRowHeader + "|Total|Percentage|Attendance|Remark|Result";
			printList.add(sheetFirstRowHeader);
			// Get a set of the entries
			Set set = grStudentMap.entrySet();
			// Get an iterator
			Iterator i = set.iterator();

			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				String grNo = me.getKey().toString();
				LinkedHashMap studentData = new LinkedHashMap();
				studentData = (LinkedHashMap) me.getValue();

				studentDetails = studentData.get("birthDate") + "|" + grNo + "|" + studentData.get("rollNo") + "|"
						+ studentData.get("lastName") + " " + studentData.get("firstName") + " "
						+ studentData.get("fatherName");

				for (String item : subjectTitleList) {
					if (!item.trim().equalsIgnoreCase("")) {
						subjectMarksObtained = studentData.get(item + "_" + semester).toString().equalsIgnoreCase("NA")
								? "" : studentData.get(item + "_" + semester).toString();
						if (subjectMarksObtained.equalsIgnoreCase("NA") || subjectMarksObtained.equalsIgnoreCase("")) {
							studentDetails = studentDetails + "|NA";
							continue;
						} else {
							if (!subjectMarksObtained.equalsIgnoreCase("NA") && !validateNumber(subjectMarksObtained)
									&& subjectMarksObtained.contains("(")) {
								dispMarks = subjectMarksObtained.substring(0, subjectMarksObtained.indexOf("("));
								if (dispMarks.contains("+") && !exam.equalsIgnoreCase("Final")) {
									dispMarks = dispMarks.substring(0, dispMarks.indexOf("+"));
								}
								dispPassStatus = subjectMarksObtained.substring(subjectMarksObtained.indexOf("(") + 1,
										subjectMarksObtained.indexOf("#"));
								dispReason = subjectMarksObtained.substring(subjectMarksObtained.indexOf("#") + 1,
										subjectMarksObtained.indexOf("@"));
								dispAbsentMarks = subjectMarksObtained.substring(subjectMarksObtained.indexOf("@") + 1,
										subjectMarksObtained.indexOf(")"));
								if (dispReason.equalsIgnoreCase("MG")) {
									dispMarks = dispMarks + " (MG)";
								} else if (dispPassStatus.equalsIgnoreCase("F")) {
									dispMarks = dispMarks + " (F)";
								}
							} else {
								dispMarks = subjectMarksObtained;
							}
						}
					}
					if (dispMarks.contains("NA")) {
						dispMarks = "NA";
					}
					studentDetails = studentDetails + "|" + dispMarks;
				}

				String passFail = studentData.get("semResult").toString();
				double percentage = Double.parseDouble(studentData.get("semPercent").toString());
				String remark_0 = "";
				if (passFail.contains("condonation")) {
					remark_0 = "Fair";
				} else if (passFail.contains("Detain")) {
					remark_0 = "Poor";
				} else if (percentage >= 75) {
					remark_0 = "Excellent";
				} else if (percentage >= 60) {
					remark_0 = "Very Good";
				} else if (percentage >= 50) {
					remark_0 = "Good";
				} else if (percentage >= 35) {
					remark_0 = "Satisfactory";
				}

				if (remark_0.equalsIgnoreCase("")) {
					remark_0 = "NA";
				}
				String romanStd = std;
				int stdPromoted = 0;
				if (passFail.contains("pass") || passFail.contains("Pass") || passFail.contains("PASS")) {
					stdPromoted = RomanToInteger(std) + 1;
					romanStd = IntegerToRoman("a" + stdPromoted);
					romanStd = romanStd + "-" + div;
				}
				String promotedStr = "Passed & Promoted to Std. : " + romanStd;
				if (!passFail.contains("pass") || !passFail.contains("Pass") || !passFail.contains("PASS")) {
					promotedStr = "Detained in Std. : " + romanStd;
				}
				if (!exam.equalsIgnoreCase("Final") && !exam.equalsIgnoreCase("Semester 2")) {
					romanStd = "";
					promotedStr = "";
				}
				if (passFail.contains("|")) {
					passFail = passFail.substring(passFail.indexOf("|") + 1);
				}

				String attendance = studentData.get("attendance").toString();
				if (attendance.equalsIgnoreCase("NA")) {
					attendance = "-";
				}

				studentDetails = studentDetails + "|" + studentData.get("semMarksObtained") + "|"
						+ studentData.get("semPercent") + "|" + attendance + "|" + remark_0 + "|" + passFail;
				printList.add(studentDetails);
				studentDetails = "";
			}
			ce.generateExcel(sessionData, "MARKS_ENTRY", "OBTAINED MARKS", "", printList, true,
					sec + " " + sheetName + " " + academic + " for Std." + std + " & Div." + div, 1);
		} catch (Exception e) {
			logException(e);
		}
		return status;
	}

	public String resultSemesterSheet(SessionData sessionData, String sec, String academic, LinkedHashMap grStudentMap,
			List<String> subjectTitleList, String exam, String std, String div, String note,
			Map<String, String> maxMarksMapOrder, Map<String, String> gradeMarksMapOrder) {
		String status = "";
		String semester = "";
		String subjectMarksObtained = "";
		String dispMarks = "";
		String dispPassStatus = "";
		String dispReason = "";
		String dispAbsentMarks = "";
		List<String> printList = new ArrayList<String>();
		String studentDetails = "";
		String sheetFirstRowHeader = "";
		String sheetSecondRowHeader = "";
		String sheetName = "";

		try {
			if (exam.equalsIgnoreCase("Semester 1")) {
				semester = "SEM1";
				sheetName = semester + " SHEET";
			} else if (exam.equalsIgnoreCase("Semester 2")) {
				semester = "SEM2";
				sheetName = semester + " SHEET";
			} else {
				semester = "FINAL";
				sheetName = "AVERAGE SHEET";
			}

			sheetFirstRowHeader = " | | |Subject";
			sheetSecondRowHeader = "DOB|G.No.|R.No.|Name";
			for (String item : subjectTitleList) {
				String maxMarks = maxMarksMapOrder.get(item);
				if (!maxMarks.equalsIgnoreCase("0") && !maxMarks.equalsIgnoreCase("NA")) {
					sheetFirstRowHeader = sheetFirstRowHeader + "|" + item;
					sheetSecondRowHeader = sheetSecondRowHeader + "|" + maxMarksMapOrder.get(item);
				}
			}
			sheetFirstRowHeader = sheetFirstRowHeader + "|Total";
			sheetSecondRowHeader = sheetSecondRowHeader + "|Total";

			printList.add(sheetFirstRowHeader);
			printList.add(sheetSecondRowHeader);

			// Get a set of the entries
			Set set = grStudentMap.entrySet();
			// Get an iterator
			Iterator i = set.iterator();

			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				String grNo = me.getKey().toString();
				LinkedHashMap studentData = new LinkedHashMap();
				studentData = (LinkedHashMap) me.getValue();

				studentDetails = studentData.get("birthDate") + "|" + grNo + "|" + studentData.get("rollNo") + "|"
						+ studentData.get("lastName") + " " + studentData.get("firstName") + " "
						+ studentData.get("fatherName");

				for (String item : subjectTitleList) {
					String maxMarks = maxMarksMapOrder.get(item);
					if (maxMarks.equalsIgnoreCase("0") || maxMarks.equalsIgnoreCase("NA")) {
						continue;
					}
					if (!item.trim().equalsIgnoreCase("")) {
						subjectMarksObtained = studentData.get(item + "_" + semester).toString().equalsIgnoreCase("NA")
								? "" : studentData.get(item + "_" + semester).toString();
						if (subjectMarksObtained.equalsIgnoreCase("NA") || subjectMarksObtained.equalsIgnoreCase("")) {
							studentDetails = studentDetails + "|NA";
							continue;
						} else {
							if (!subjectMarksObtained.equalsIgnoreCase("NA") && !validateNumber(subjectMarksObtained)
									&& subjectMarksObtained.contains("(")) {
								dispMarks = subjectMarksObtained.substring(0, subjectMarksObtained.indexOf("("));
								if (dispMarks.contains("+") && !exam.equalsIgnoreCase("Final")) {
									dispMarks = dispMarks.substring(0, dispMarks.indexOf("+"));
								}
								dispPassStatus = subjectMarksObtained.substring(subjectMarksObtained.indexOf("(") + 1,
										subjectMarksObtained.indexOf("#"));
								dispReason = subjectMarksObtained.substring(subjectMarksObtained.indexOf("#") + 1,
										subjectMarksObtained.indexOf("@"));
								dispAbsentMarks = subjectMarksObtained.substring(subjectMarksObtained.indexOf("@") + 1,
										subjectMarksObtained.indexOf(")"));
								if (dispReason.equalsIgnoreCase("MG")) {
									dispMarks = dispMarks + " (MG)";
								} else if (dispPassStatus.equalsIgnoreCase("F")) {
									dispMarks = dispMarks + " (F)";
								}
							} else {
								dispMarks = subjectMarksObtained;
							}
						}
					}
					if (dispMarks.contains("NA")) {
						dispMarks = "NA";
					}
					studentDetails = studentDetails + "|" + dispMarks;
				}

				studentDetails = studentDetails + "|" + studentData.get("semMarksObtained");
				printList.add(studentDetails);
				studentDetails = "";
			}
			ce.generateExcel(sessionData, "MARKS_ENTRY", "OBTAINED MARKS", "", printList, true,
					sec + " " + sheetName + " " + academic + " for Std." + std + " & Div." + div, 3);
		} catch (Exception e) {
			logException(e);
		}
		return status;
	}

	/// send bhash SMS
	public String sendBhashSms(SessionData sessionData, List<String> passGrList, LinkedHashMap foundStudent,
			String smsText, String section, String smsType, String academic, String std, String div, 
			String dateTime, String moduleName) {
		String sResult = null;
		try {
			// Construct data
			LinkedHashMap studenthmap = new LinkedHashMap();
			String sms_url = sessionData.getConfigMap().get("SMS_URL");
			String sms_user = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_USER");
			String sms_pass = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_PASS");
			String sms_sender = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_SENDER");
			String apiKey = sessionData.getConfigMap().get(sessionData.getAppType()+"_APIKEY");
			
			String phonenumbers = "";
			String phonePassed = "";
			String priority = "ndnd";
			String type = "normal";
			//// loop for multiple phoneNumbers
			for (String grNo : passGrList) {
				phonePassed = (((LinkedHashMap<?, ?>) foundStudent.get(grNo)).get("contact1")).toString();
				phonenumbers = phonenumbers + "," + phonePassed;
				studenthmap.put(grNo, phonePassed);
			}
			phonenumbers = phonenumbers.substring(1);

			/// for bhash sms
			String data = "user=" + URLEncoder.encode(sms_user, "UTF-8");
			data += "&pass=" + URLEncoder.encode(sms_pass, "UTF-8");
			data += "&sender=" + URLEncoder.encode(sms_sender, "UTF-8");
			data += "&phone=" + URLEncoder.encode(phonenumbers, "UTF-8");
			data += "&text=" + URLEncoder.encode(smsText, "UTF-8");
			data += "&priority=" + URLEncoder.encode(priority, "UTF-8");
			data += "&stype=" + URLEncoder.encode("normal", "UTF-8");

			if (smsType.equalsIgnoreCase("Schedule SMS") && sms_url.contains("bhashsms")) {
				data += "&time=" + URLEncoder.encode(dateTime, "UTF-8");
				sms_url = sessionData.getConfigMap().get("SMS_SCHEDULE");
			} else if (smsType.equalsIgnoreCase("Schedule SMS") && sms_url.contains("hspsms")) {
				dateTime = dateTime.replace("-", "");
				dateTime = dateTime.replace(" ", "");
				dateTime = dateTime.replace(":", "");
				data += "&scheduled=" + URLEncoder.encode(dateTime, "UTF-8");
				sms_url = sessionData.getConfigMap().get("SMS_SCHEDULE");
			}

			URL urlTest = new URL(sms_url);
			String internetStatus = checkInternet(urlTest).toString();
			if (!internetStatus.equalsIgnoreCase("Success")) {
				return internetStatus;
			}

			URL url = new URL(sms_url + data);
			URLConnection conn = url.openConnection();

			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();
			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			String sResult1 = "";
			smsText = smsText.replace("\n", "newLine");
			try {
				if (dbValidate.connectDatabase(sessionData)) {
					while ((line = rd.readLine()) != null) {
						// Process line...
						sResult1 = sResult1 + line + " ";
					}

					String[] resultString = null;
					resultString = sResult1.split("\\s");// splits the string
															// based on
															// whitespace
					if (smsType.equalsIgnoreCase("Schedule SMS")) {
						sResult1 = sResult1.substring(sResult1.indexOf(">") + 1, sResult1.indexOf("</"));
					}

					String response = "";
					String status = "";
					String msgId = "";
					int noOfResultCount = 0;
					Set setInt = studenthmap.entrySet();
					Iterator j = setInt.iterator();
					while (j.hasNext()) {
						Map.Entry me = (Map.Entry) j.next();
						if (smsType.equalsIgnoreCase("Schedule SMS")) {
							response = sResult1;
							status = "Scheduled";
							msgId = "Scheduled";
						} else {
							response = resultString[noOfResultCount];
							status = response.substring(0, 1);
							msgId = response.substring(response.indexOf(".") + 1);
						}
						String name = (((LinkedHashMap<?, ?>) foundStudent.get(me.getKey().toString())).get("name"))
								.toString();
						String rollNo = (((LinkedHashMap<?, ?>) foundStudent.get(me.getKey().toString()))
								.get("roll_no")).toString();
						dbValidate.insertSmsData(sessionData, me.getKey().toString(), std, div, academic,
								me.getValue().toString(), smsText, sms_sender, priority, type, status, response, msgId,
								section, "", "", smsType, name, rollNo, moduleName, "");
						noOfResultCount++;
					}
				}
			} catch (Exception e1) {
				logException(e1);
			} finally {
				dbValidate.closeDatabase(sessionData);
			}
			wr.close();
			rd.close();
			if (smsType.equalsIgnoreCase("Schedule SMS")) {
				JOptionPane.showMessageDialog(null, sResult1);
			}
			return sResult1;
		} catch (Exception e) {
			logException(e);
			return "Error " + e;
		}
	}

	/// send hsp SMS
	public String sendHspSms(SessionData sessionData, List<String> passGrList, LinkedHashMap foundStudent,
			String smsText, String smsTemplateId, String section, String smsType, String academic, String std, String div, 
			String dateTime, String moduleName) {
		String sResult = null;
		try {
			// Construct data
			LinkedHashMap studenthmap = new LinkedHashMap();
			String sms_url = sessionData.getConfigMap().get("SMS_URL");
			String sms_user = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_USER");
			String sms_pass = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_PASS");
			String sms_sender = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_SENDER");
			String apiKey = sessionData.getConfigMap().get(sessionData.getAppType()+"_APIKEY");
			String sms_pe_id = sessionData.getConfigMap().get("SMS_PE_ID");
			String sms_maauli_flag = sessionData.getConfigMap().get("SMS_MAAULI_FLAG");
			
			if(sessionData.getUserName().equalsIgnoreCase("prp") && sms_maauli_flag.equalsIgnoreCase("true")){
				sms_user = sessionData.getConfigMap().get("SMS_USER");
				sms_pass = sessionData.getConfigMap().get("SMS_PASS");
				sms_sender = sessionData.getConfigMap().get("SMS_SENDER");
				apiKey = sessionData.getConfigMap().get("SMS_APIKEY");
			}
			String phonenumbers = "";
			String phonePassed = "";
			String type = "TRANS";
			String scheduleTime = dateTime;
			
			if(!moduleName.equalsIgnoreCase("SMS_FEE_ADMIN")){
				//// loop for multiple phoneNumbers
				for (String grNo : passGrList) {
					phonePassed = (((LinkedHashMap<?, ?>) foundStudent.get(grNo)).get("contact1")).toString();
					if(phonePassed.length() != 10){
						continue;
					}
					
					phonenumbers = phonenumbers + "," + phonePassed;
					studenthmap.put(grNo, phonePassed);
				}
				phonenumbers = phonenumbers.substring(1);
			}
			else{
				phonenumbers = sessionData.getAdminContact();
			}
			
			String data = "username=" + sms_user;
			data += "&" + "apikey=" + apiKey;
			data += "&" + "smstype=" + type;
			data += "&" + "sendername=" + sms_sender;
			data += "&" + "numbers=" + phonenumbers;
			data += "&" + "peid=" + sms_pe_id;
			data += "&" + "templateid=" + smsTemplateId;
			data += "&" + "message=" + URLEncoder.encode(smsText, "UTF-8");

			if (smsType.equalsIgnoreCase("Schedule SMS") && sms_url.contains("hspsms")) {
				dateTime = dateTime.replace("-", "");
				dateTime = dateTime.replace(" ", "");
				dateTime = dateTime.replace(":", "");
				data += "&scheduled=" + URLEncoder.encode(dateTime, "UTF-8");
				sms_url = sessionData.getConfigMap().get("SMS_SCHEDULE");
			}

			URL urlTest = new URL(sms_url);
			String internetStatus = checkInternet(urlTest).toString();
			if (!internetStatus.equalsIgnoreCase("Success")) {
				return internetStatus;
			}

			URL url = new URL(sms_url + data);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

			String line;
			String sResult1 = "";
			smsText = smsText.replace("\n", "newLine");
			try {
				if (dbValidate.connectDatabase(sessionData)) {
					while ((line = br.readLine()) != null) {
						sResult1 += line;
					}

					String response = "";
					String status = "";
					String msgId = "";
					int noOfResultCount = 0;
					
					if(!moduleName.equalsIgnoreCase("SMS_FEE_ADMIN")){
						Set setInt = studenthmap.entrySet();
						Iterator j = setInt.iterator();
						while (j.hasNext()) {
							Map.Entry me = (Map.Entry) j.next();
							if (smsType.equalsIgnoreCase("Schedule SMS")) {
								status = "Scheduled";
							}
							else {
								status = response;
							}
							
							if(!sResult1.contains("Sender Name Invalid")) {
								response = sResult1.substring(sResult1.indexOf("responseCode") + 15,
										sResult1.indexOf(",") - 2);
								if (response.contains("Submitted") || response.contains("SUBMITTED")) {
									response = "SUBMITTED";
								}
								msgId = sResult1.substring(sResult1.indexOf("msgid") + 8, sResult1.lastIndexOf("}") - 1);
							}
							else {
								response = "Sender Name Invalid";
								status = "Failed";
							}
							
							
							String name = (((LinkedHashMap<?, ?>) foundStudent.get(me.getKey().toString())).get("name"))
									.toString();
							String rollNo = (((LinkedHashMap<?, ?>) foundStudent.get(me.getKey().toString()))
									.get("roll_no")).toString();
							dbValidate.insertSmsData(sessionData, me.getKey().toString(), std, div, academic,
									me.getValue().toString(), smsText, sms_sender, "", type, status, response, msgId,
									section, scheduleTime, apiKey, smsType, name, rollNo, moduleName, smsTemplateId);
							noOfResultCount++;
						}	
					}
					else{
						if (smsType.equalsIgnoreCase("Schedule SMS")) {
							status = "Scheduled";
						}
						else {
							status = response;
						}
						
						
						if(!sResult1.contains("Sender Name Invalid")) {
							response = sResult1.substring(sResult1.indexOf("responseCode") + 15,
									sResult1.indexOf(",") - 2);
							if (response.contains("Submitted") || response.contains("SUBMITTED")) {
								response = "SUBMITTED";
							}
							msgId = sResult1.substring(sResult1.indexOf("msgid") + 8, sResult1.lastIndexOf("}") - 1);
						}
						else {
							response = "Sender Name Invalid";
							status = "Failed";
						}
						
						dbValidate.insertSmsData(sessionData, "", "", "", academic,
								phonenumbers, smsText, sms_sender, "", type, status, response, msgId,
								section, scheduleTime, apiKey, smsType, "admin", "", moduleName, smsTemplateId);
					}
				}
			} catch (Exception e1) {
				logException(e1);
			} finally {
				dbValidate.closeDatabase(sessionData);
			}
			if (smsType.equalsIgnoreCase("Schedule SMS")) {
				JOptionPane.showMessageDialog(null, sResult1);
			}
			return sResult1;
		} catch (Exception e) {
			logException(e);
			return "Error " + e;
		}
	}
	
	/// send hsp Staff Fee SMS
	public String sendHspStaffFeeSms(SessionData sessionData, List<String> passGrList, LinkedHashMap foundStudent,
			String smsText, String smsTemplateId, String section, String smsType, String academic, String std, String div, 
			String dateTime, String moduleName) {
		String sResult = "SMS sent successfully..";
		try {
			// Construct data
			LinkedHashMap studenthmap = new LinkedHashMap();
			String sms_url = sessionData.getConfigMap().get("SMS_URL");
			String sms_user = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_USER");
			String sms_pass = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_PASS");
			String sms_sender = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_SENDER");
			String apiKey = sessionData.getConfigMap().get(sessionData.getAppType()+"_APIKEY");
			String sms_pe_id = sessionData.getConfigMap().get("SMS_PE_ID");
			String sms_maauli_flag = sessionData.getConfigMap().get("SMS_MAAULI_FLAG");
			
			if(sessionData.getUserName().equalsIgnoreCase("prp") && sms_maauli_flag.equalsIgnoreCase("true")){
				sms_user = sessionData.getConfigMap().get("SMS_USER");
				sms_pass = sessionData.getConfigMap().get("SMS_PASS");
				sms_sender = sessionData.getConfigMap().get("SMS_SENDER");
				apiKey = sessionData.getConfigMap().get("SMS_APIKEY");
			}
			String phonenumbers = "", staffName = "";
			String phonePassed = "";
			String type = "TRANS";
			String scheduleTime = dateTime;
			
			if(moduleName.equalsIgnoreCase("SMS_FEE_STAFF") && dbValidate.connectDatabase(sessionData)){
				
				LinkedHashMap<String,String> feeStaffMap = new LinkedHashMap<String,String>();
				feeStaffMap = dbValidate.getFeeStaffMap(sessionData, academic, std, div);
				
				Set set = feeStaffMap.entrySet();
				Iterator i = set.iterator();
				while(i.hasNext()) {
					Map.Entry me = (Map.Entry)i.next();
					staffName = (String) me.getKey();
					phonenumbers = (String) me.getValue();
					
					if(phonenumbers.length() != 10){
						continue;
					}
					
					String data = "username=" + sms_user;
					data += "&" + "apikey=" + apiKey;
					data += "&" + "smstype=" + type;
					data += "&" + "sendername=" + sms_sender;
					data += "&" + "numbers=" + phonenumbers;
					data += "&" + "peid=" + sms_pe_id;
					data += "&" + "templateid=" + smsTemplateId;
					data += "&" + "message=" + URLEncoder.encode(smsText, "UTF-8");
		
					if (smsType.equalsIgnoreCase("Schedule SMS") && sms_url.contains("hspsms")) {
						dateTime = dateTime.replace("-", "");
						dateTime = dateTime.replace(" ", "");
						dateTime = dateTime.replace(":", "");
						data += "&scheduled=" + URLEncoder.encode(dateTime, "UTF-8");
						sms_url = sessionData.getConfigMap().get("SMS_SCHEDULE");
					}

					URL urlTest = new URL(sms_url);
					String internetStatus = checkInternet(urlTest).toString();
					if (!internetStatus.equalsIgnoreCase("Success")) {
						return internetStatus;
					}
		
					URL url = new URL(sms_url + data);
					BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		
					String line;
					String sResult1 = "";
					smsText = smsText.replace("\n", "newLine");
					
					try {
						if (dbValidate.connectDatabase(sessionData)) {
							while ((line = br.readLine()) != null) {
								sResult1 += line;
							}
		
							String response = "";
							String status = "";
							String msgId = "";
							int noOfResultCount = 0;
							
							response = sResult1.substring(sResult1.indexOf("responseCode") + 15,
									sResult1.indexOf(",") - 2);
							if (response.contains("Submitted") || response.contains("SUBMITTED")) {
								response = "SUBMITTED";
							}
							status = response;
							msgId = sResult1.substring(sResult1.indexOf("msgid") + 8, sResult1.lastIndexOf("}") - 1);

							dbValidate.insertSmsData(sessionData, "", "", "", academic,
								phonenumbers, smsText, sms_sender, "", type, status, response, msgId,
								section, scheduleTime, apiKey, smsType, staffName, "", moduleName, smsTemplateId);
						}
					} catch (Exception e1) {
						sResult = "SMS sent failed...";
						logException(e1);
					} finally {
						dbValidate.closeDatabase(sessionData);
					}
				}
			}
			/////end sending SMS to staff
			return sResult;
		} catch (Exception e) {
			logException(e);
			return "Error " + e;
		}
	}

	/// Check SMS Balance
	public String checkSmsBalance(SessionData sessionData) {
		String sResult = null;
		try {
			// Construct data
			LinkedHashMap studenthmap = new LinkedHashMap();
			String sms_url = sessionData.getConfigMap().get("SMS_BALANCE");
			String sms_user = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_USER");
			String sms_pass = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_PASS");

			String data = "user=" + URLEncoder.encode(sms_user, "UTF-8");
			data += "&pass=" + URLEncoder.encode(sms_pass, "UTF-8");

			URL url = new URL(sms_url + data);
			/*
			 * String internetStatus = checkInternet(url).toString();
			 * if(!internetStatus.equalsIgnoreCase("Success")){ return
			 * internetStatus; }
			 */
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();
			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			String sResult1 = "";
			try {
				while ((line = rd.readLine()) != null) {
					sResult1 = sResult1 + line + " ";
				}
			} catch (Exception e1) {
				logException(e1);
			} finally {
				dbValidate.closeDatabase(sessionData);
			}
			wr.close();
			rd.close();
			return sResult1;
		} catch (Exception e) {
			return "Exception occured.";
		}
	}

	/// Check HSP SMS Balance
	public String checkHspSmsBalance(SessionData sessionData) {
		String sResult = null;
		try {
			// Construct data
			LinkedHashMap studenthmap = new LinkedHashMap();
			String sms_url = sessionData.getConfigMap().get("SMS_BALANCE");
			String sms_user = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_USER");
			String sms_pass = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_PASS");
			String apiKey = sessionData.getConfigMap().get(sessionData.getAppType()+"_APIKEY");
			String sms_maauli_flag = sessionData.getConfigMap().get("SMS_MAAULI_FLAG");
			
			if(sessionData.getUserName().equalsIgnoreCase("prp") && sms_maauli_flag.equalsIgnoreCase("true")){
				sms_user = sessionData.getConfigMap().get("SMS_USER");
				sms_pass = sessionData.getConfigMap().get("SMS_PASS");
				apiKey = sessionData.getConfigMap().get("SMS_APIKEY");
			}

			String data = "username=" + sms_user;
			data += "&" + "apikey=" + apiKey;

			URL urlTest = new URL(sms_url);
			String internetStatus = checkInternet(urlTest).toString();
			if (!internetStatus.equalsIgnoreCase("Success")) {
				return internetStatus;
			}

			URL url = new URL(sms_url + data);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			String sResult1 = "";
			try {
				while ((line = rd.readLine()) != null) {
					sResult1 = sResult1 + line + " ";
				}
				sResult1 = sResult1.substring(sResult1.indexOf("[") + 2, sResult1.indexOf(","));
			} catch (Exception e1) {
				logException(e1);
				sResult1 = "Check balance error";
			} finally {
				dbValidate.closeDatabase(sessionData);
			}
			rd.close();
			return sResult1;
		} catch (Exception e) {
			logException(e);
			return "Exception occured.";
		}
	}

	/// Check delivery status
	public String checkDeliveryStatus(SessionData sessionData, String originalStatus, String msgId, String phone,
			String msgType, String gr, String section) {
		String sResult = "";
		try {
			// Construct data
			LinkedHashMap studenthmap = new LinkedHashMap();
			String sms_url = sessionData.getConfigMap().get("DELIVERY_STATUS");
			String sms_user = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_USER");
			String sms_pass = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_PASS");

			String data = "user=" + URLEncoder.encode(sms_user, "UTF-8");
			data += "&msgid=" + URLEncoder.encode(msgId, "UTF-8");
			data += "&phone=" + URLEncoder.encode(phone, "UTF-8");
			data += "&msgtype=" + URLEncoder.encode(msgType, "UTF-8");

			URL url = new URL(sms_url + data);
			/*
			 * String internetStatus = checkInternet(url).toString();
			 * if(!internetStatus.equalsIgnoreCase("Success")){ return
			 * internetStatus; }
			 */
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			try {
				while ((line = rd.readLine()) != null) {
					sResult = sResult + line + " ".trim();
				}

				if (sResult.equalsIgnoreCase("DELIVRD")) {
					sResult = "DELIVERED";
				} else if (sResult.equalsIgnoreCase("UNDELIV")) {
					sResult = "UNDELIVERED";
				}
				if (!originalStatus.equalsIgnoreCase(sResult) && dbValidate.connectDatabase(sessionData)) {
					dbValidate.updateSmsDeliveryStatus(sessionData, gr, sResult, msgId, section, sResult);
				}
			} catch (Exception e1) {
				logException(e1);
			} finally {
				dbValidate.closeDatabase(sessionData);
			}
			rd.close();
			return sResult;
		} catch (Exception e) {
			logException(e);
			return "Exception occured.";
		}
	}

	/// Check HSP SMS delivery status
	public String checkHSPDeliveryStatus(SessionData sessionData, String originalStatus, String msgId, String phone,
			String msgType, String gr, String section) {
		String sResult = "";
		String dlrStatus = "";
		try {
			// Construct data
			LinkedHashMap studenthmap = new LinkedHashMap();
			String sms_url = sessionData.getConfigMap().get("DELIVERY_STATUS");
			String sms_user = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_USER");
			String sms_pass = sessionData.getConfigMap().get("SMS_"+sessionData.getAppType()+"_PASS");
			String apiKey = sessionData.getConfigMap().get(sessionData.getAppType()+"_APIKEY");
			String sms_maauli_flag = sessionData.getConfigMap().get("SMS_MAAULI_FLAG");
			
			if(sessionData.getUserName().equalsIgnoreCase("prp") && sms_maauli_flag.equalsIgnoreCase("true")){
				sms_user = sessionData.getConfigMap().get("SMS_USER");
				sms_pass = sessionData.getConfigMap().get("SMS_PASS");
				apiKey = sessionData.getConfigMap().get("SMS_APIKEY");
			}

			String data = "username=" + sms_user;
			data += "&" + "apikey=" + apiKey;
			data += "&" + "msgid=" + msgId;

			/*
			 * URL urlTest = new URL(sms_url); String internetStatus =
			 * checkInternet(urlTest).toString();
			 * if(!internetStatus.equalsIgnoreCase("Success")){ return
			 * internetStatus; }
			 */

			URL url = new URL(sms_url + data);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			try {
				while ((line = rd.readLine()) != null) {
					sResult = sResult + line + " ".trim();
				}
				
				if (sResult.contains(phone)) {
					dlrStatus = sResult.substring(sResult.indexOf("dlr_status") + 13, sResult.lastIndexOf("]") - 2);
					sResult = dlrStatus.substring(dlrStatus.indexOf(phone));
					sResult = sResult.substring(0, sResult.indexOf("}"));
					dlrStatus = sResult.substring(sResult.indexOf(phone) + 25);
					if(dlrStatus.contains("dlrstatus")){
						dlrStatus = dlrStatus.substring(dlrStatus.indexOf(":")+2);
					}
					dlrStatus = dlrStatus.substring(0, dlrStatus.indexOf("senderid") - 3);
				} else if (sResult.contains("responseCode")) {
					dlrStatus = sResult.substring(sResult.indexOf("responseCode") + 15, sResult.lastIndexOf("}") - 1);
				} else if (sResult.contains("dlr_status")) {
					dlrStatus = sResult.substring(sResult.indexOf("dlr_status") + 13, sResult.lastIndexOf("}") - 1);
				} else {
					logger.error("response data is not as expected:: "+sResult);
				}

				if (dlrStatus.equalsIgnoreCase("DELIVRD")) {
					dlrStatus = "DELIVERED";
				} else if (dlrStatus.equalsIgnoreCase("UNDELIV")) {
					dlrStatus = "UNDELIVERED";
				} else if (dlrStatus.equalsIgnoreCase("SUBMITED")) {
					dlrStatus = "SUBMITTED";
				}
				
				if (!originalStatus.equalsIgnoreCase(dlrStatus) && dbValidate.connectDatabase(sessionData)) {
					dbValidate.updateSmsDeliveryStatus(sessionData, gr, dlrStatus, msgId, section, sResult);
				}
			} catch (Exception e1) {
				logException(e1);
			} finally {
				dbValidate.closeDatabase(sessionData);
			}
			rd.close();
			return dlrStatus;
		} catch (UnknownHostException e) {
			logException(e);
			return "Connection error.";
		} catch (Exception e) {
			return "Exception occured.";
		}
	}

	public String checkInternet(URL url) {
		try {
			URLConnection connection = url.openConnection();
			if (connection.getContentLength() == -1) {
				return "Error while connecting to SMS server. \n Please check internet connectivity.";
			}
			return "success";
		} catch (Exception e) {
			logException(e);
			return "Exception occured while connecting to SMS server.";
		}
	}
	
	public static long roundEven(double d) {
		long retDouble = 0;
		if(d % 1 == 0){
			retDouble = (long) d;
		}
		else{
			retDouble = Math.round(d / 2) * 2;
		}
	    return retDouble;
	}
	
	public static Double roundUp(double d) {
		long retDouble = 0;
		if(d > 0 && d <= 1){
			return 1.0;
		}
		else if(d % 1 == 0){
			retDouble = (long) d;
		}
		else{
			retDouble = Math.round(d / 2) * 2;
		}
	    return (double) retDouble;
	}
	
	public static String roundUpString(String strNumber) {
		
		if(!validateNumber(strNumber)) {
			return strNumber;
		}
		
		double d = Double.parseDouble(strNumber);
		long retDouble = 0;
		if(d > 0 && d <= 1){
			return "1.0";
		}
		else if(d % 1 == 0){
			retDouble = (long) d;
		}
		else{
			retDouble = Math.round(d / 2) * 2;
		}
	    return ""+retDouble;
	}
	
	public double getConvertMarksForDivisor(Double maxMarksForSubject, int semCount, boolean isSemCount){
		double convertMarksForDivisor = 0.0;
		if(!isSemCount){
			if(maxMarksForSubject < 200.00){//200 as considered for both sem 1 + 2
				convertMarksForDivisor = maxMarksForSubject/(maxMarksForSubject/(maxMarksForSubject/2));
			}
			else{
				convertMarksForDivisor = maxMarksForSubject/(maxMarksForSubject/100.0);
			}
		}
		else{
			if(maxMarksForSubject < 200.00){//200 as considered for both sem 1 + 2
				convertMarksForDivisor = maxMarksForSubject/(maxMarksForSubject/(maxMarksForSubject/semCount));
			}
			else{
				convertMarksForDivisor = maxMarksForSubject/(maxMarksForSubject/100.0);
			}
		}
		
		return convertMarksForDivisor;
	}
	
	////number to word convert start////
	public static final String[] units = { "", "One", "Two", "Three", "Four",
			"Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
			"Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
			"Eighteen", "Nineteen" };

	public static final String[] tens = { 
			"", 		// 0
			"",		// 1
			"Twenty", 	// 2
			"Thirty", 	// 3
			"Forty", 	// 4
			"Fifty", 	// 5
			"Sixty", 	// 6
			"Seventy",	// 7
			"Eighty", 	// 8
			"Ninety" 	// 9
	};

	public static String convert(final int n) {
		if (n < 0) {
			return "Minus " + convert(-n);
		}

		if (n < 20) {
			return units[n];
		}

		if (n < 100) {
			return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
		}

		if (n < 1000) {
			return units[n / 100] + " Hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
		}

		if (n < 100000) {
			return convert(n / 1000) + " Thousand" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
		}

		if (n < 10000000) {
			return convert(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
		}

		return convert(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
	}
	///number to word convert end//////////
	
	public void logException(Exception e){
		logger.error(e.getMessage().replace("sms.hspsms.com", "xxx.xxx"));
		StackTraceElement[] stack = e.getStackTrace();
	    String exception = "";
	    for (StackTraceElement s : stack) {
	        exception = exception + s.toString() + "\n\t\t";
	    }
	    logger.error(exception.toString().replace("sms.hspsms.com", "xxx.xxx"));
	}
	
	public boolean isDateBetween(String fromDate, String toDate, String checkDate){
		try {
			if(checkDate.equalsIgnoreCase("00/00/0000")) {
				return false;
			}
			
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);

            Date d1 = sdf.parse(fromDate);
            Date d2 = sdf.parse(checkDate);
            Date d3 = sdf.parse(toDate);

            if(d2.compareTo(d1) >= 0 && d2.compareTo(d3) <= 0){
              	  return true;
            }
            else {
            	return false;
            }
		} catch (ParseException pe) {
            pe.printStackTrace();
		}
		return false;
	}
	
	public LinkedHashMap getExamTypeMapping(){
		LinkedHashMap examTypeMap = new LinkedHashMap();
		examTypeMap.put("DOB", "dobs");
		examTypeMap.put("OBT", "obt");
		examTypeMap.put("ORA", "oral");
		examTypeMap.put("ASS", "assign");
		examTypeMap.put("WRI", "write");
		examTypeMap.put("PRA", "pract");
		examTypeMap.put("PRE", "pres");
		examTypeMap.put("MCA", "mcap");
		examTypeMap.put("ACT", "activity");
		examTypeMap.put("PRO", "project");
		examTypeMap.put("OTH", "other");
		examTypeMap.put("ORA1", "oral1");
		examTypeMap.put("PRA1", "pract1");
		examTypeMap.put("WRI1", "write1");
		examTypeMap.put("LIS", "listen");
		examTypeMap.put("SPE", "speak");
		examTypeMap.put("ASS1", "assign1");
		
		return examTypeMap;
	}
	
	public LinkedHashMap<String,String> getExamType(int stdInt, String std, String academicYear){
		LinkedHashMap<String,String> examTypeMap = new LinkedHashMap<String,String>();
		if(is9thEvaluation(std, academicYear)) {
			examTypeMap.put("FOBT", "FUT");
			examTypeMap.put("FPRA", "PRACT");
			examTypeMap.put("FLIS", "LISTEN");
			examTypeMap.put("FSPE", "SPEAK");
			examTypeMap.put("FASS", "ASSIGN");
			examTypeMap.put("FPRO", "PROJECT");
			examTypeMap.put("FWRI", "WRITE");
			examTypeMap.put("SOBT", "SUT");
			examTypeMap.put("SPRA", "PRACT");
			examTypeMap.put("SLIS", "LISTEN");
			examTypeMap.put("SSPE", "SPEAK");
			examTypeMap.put("SASS", "ASSIGN");
			examTypeMap.put("SPRO", "PROJECT");
			examTypeMap.put("SWRI", "WRITE");
			
		}
		else if(stdInt < 11){
			examTypeMap.put("FOBT", "FUT");
			examTypeMap.put("FPRE", "PRES");
			examTypeMap.put("FMCA", "MCAP");
			examTypeMap.put("FWRI", "WRITE");
			examTypeMap.put("FPRA", "PRACT");
			examTypeMap.put("FACT", "ACT");
			examTypeMap.put("SOBT", "SUT");
			examTypeMap.put("SPRE", "PRES");
			examTypeMap.put("SMCA", "MCAP");
			examTypeMap.put("SWRI", "WRITE");
			examTypeMap.put("SPRA", "PRACT");
			examTypeMap.put("SACT", "ACT");
		}
		else{
			examTypeMap.put("FOBT", "FUT");
			examTypeMap.put("FORA", "ORAL");
			examTypeMap.put("FASS", "ASSIGN");
			examTypeMap.put("FWRI", "WRITE");
			examTypeMap.put("FPRA", "PRACT");
			examTypeMap.put("SOBT", "SUT");
			examTypeMap.put("SORA", "ORAL");
			examTypeMap.put("SASS", "ASSIGN");
			examTypeMap.put("SWRI", "WRITE");
			examTypeMap.put("SPRA", "PRACT");
		}
		
        return examTypeMap;
	}
	
	public String marksCalculation(String marksFromMap, String marksFromDb, String maxSubMarks, 
			LinkedHashMap<String,String> leftDataMap, String grNo){
		String retMarks = "";
		
//			if((marksFromDb.equalsIgnoreCase("0") || marksFromDb.equalsIgnoreCase("AB") || 
//					marksFromDb.equalsIgnoreCase("MG")) && leftDataMap.get(grNo) != null){
//				return "-";
//			}
		if(marksFromMap == null || marksFromMap.equalsIgnoreCase("null")) {
//			return "-";
		}
		else if(!marksFromMap.equalsIgnoreCase("0") && (marksFromDb.equalsIgnoreCase("0") || marksFromDb.equalsIgnoreCase("AB") || 
				marksFromDb.equalsIgnoreCase("MG"))){
//			return "-"; //added after result flag
		} else if((marksFromDb.equalsIgnoreCase("0") || marksFromDb.equalsIgnoreCase("AB") || 
				marksFromDb.equalsIgnoreCase("MG"))){
			return "-";
		}
		
		if(marksFromMap != null){
			if(validateNumber(marksFromDb) && validateNumber(marksFromMap)){
				retMarks =  (Double.parseDouble(marksFromDb)+Double.parseDouble(marksFromMap))+"";
			}
			else if(!validateNumber(marksFromDb) && validateNumber(marksFromMap)){
				retMarks =  Double.parseDouble(marksFromMap)+"";
			}
			else if(validateNumber(marksFromDb) && !validateNumber(marksFromMap)){
				retMarks =  Double.parseDouble(marksFromDb)+"";
			}
			else{
				retMarks = marksFromDb;
			}
		} else {
			if(validateNumber(marksFromDb)){
				retMarks =  (Double.parseDouble(marksFromDb)+"");
			}
			else{
				retMarks = marksFromDb;
			}
		}
		
		if(retMarks.contains(".") && Double.parseDouble(retMarks.substring(retMarks.indexOf(".")+1)) == 0){
			retMarks = String.format("%.0f", Double.parseDouble(retMarks.substring(0,retMarks.indexOf("."))));
		}
		
		return retMarks;
	}
	
	/****
	 * sunday = 0, monday = 1,.....saturday=6
	 * @param firstDay
	 * @param lastDay
	 * @return
	 */
	public LinkedHashMap<String,String> getDateForADayInWeek(SessionData sessionData){
		LinkedHashMap<String,String> dataMap = new LinkedHashMap<String,String>();
		int firstDay = Integer.parseInt(sessionData.getConfigMap().get("SMS_FIRST_DAY")+"");
		int lastDay = Integer.parseInt(sessionData.getConfigMap().get("SMS_LAST_DAY")+"");
//		int startMonth = Integer.parseInt(sessionData.getConfigMap().get("ACADEMIC_START_MONTH"));
//		int endMonth = startMonth-1;
		
//      DateFormat df = new SimpleDateFormat("EEE dd/MM/yyyy"); //if you want to get day on that date
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      
		// Get calendar set to current date and time
        Calendar c = Calendar.getInstance();
        // Set the calendar to monday of the current week
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        
        c.add(Calendar.DATE, firstDay);
        dataMap.put("firstDay", df.format(c.getTime()));
        
        c.add(Calendar.DATE, lastDay);
        dataMap.put("lastDay", df.format(c.getTime()));
        
        // Get calendar set to current date and time
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.DAY_OF_MONTH, 1);
        dataMap.put("firstDayOfMonth", df.format(c1.getTime()));
        
        c1.set(Calendar.DATE, c1.getActualMaximum(Calendar.DATE));
        dataMap.put("lastDayOfMonth", df.format(c1.getTime()));
        
        return dataMap;
	}
	
	public String getResultRemark(String passFail, double percent){
		
		if(passFail.contains("condonation")){
			return "Fair";
		}
		else if(passFail.contains("Detain")){
			return "Poor";
		}
		else if(percent >= 75){
			return "Excellent";
		}
		else if(percent >= 60){
			return "Very Good";
		}
		else if(percent >= 50){
			return "Good";
		}
		else if(percent >= 35){
			return "Satisfactory";
		}
		else if(percent < 35){
			return "Poor";
		}
		return "-";
	}
	
	public String[] toAddInReport(String[] dataSplit) {
		String[] data = null;
		for(int k = 0; k < dataSplit.length; k++) {
			data = dataSplit[k].split("\\^");
			if(data.length > 9) {
				if(data[9].equalsIgnoreCase("A")) {
					return data;
				}
			}
		}
		return null;
	}
	
	//Since JDK 7, NIO
    public static void writeBytesToFileNio(byte[] bFile, String fileDest) {

        try {
            Path path = Paths.get(fileDest);
            Files.write(path, bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	
	public byte[] convertImage(String imgPath, String tmpPath) {
		byte b[] = null;
		try {

			logger.info("convertImage imgPath :: "+imgPath);
			if((System.getProperty("os.name").toLowerCase().indexOf("mac") < 0)) {
				imgPath = imgPath.replace("/","\\\\");
			}
			logger.info("convertImage imgPath 2 :: "+imgPath);
			BufferedImage originalImage = ImageIO.read(new File(imgPath));
//			BufferedImage originalImage = ImageIO.read(this.getClass().getResource(imgPath));
			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

			BufferedImage resizeImageJpg = resizeImage(originalImage, type);
			ImageIO.write(resizeImageJpg, "jpg", new File(tmpPath));
			
//			BufferedImage resizeImagePng = resizeImage(originalImage, type);
//			ImageIO.write(resizeImagePng, "png", new File("d:\\image\\mkyong_png.jpg"));
//
//			BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);
//			ImageIO.write(resizeImageHintJpg, "jpg", new File("d:\\image\\mkyong_hint_jpg.jpg"));
//
//			BufferedImage resizeImageHintPng = resizeImageWithHint(originalImage, type);
//			ImageIO.write(resizeImageHintPng, "png", new File("d:\\image\\mkyong_hint_png.jpg"));
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( resizeImageJpg, "jpg", baos );
			baos.flush();
			b = baos.toByteArray();
			baos.close();

		} catch (Exception e) {
			logException(e);
		}
		return b;
	}
	
	public static BufferedImage resizeImage(BufferedImage originalImage, int type) {
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();

		return resizedImage;
	}

	private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type) {

		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}
	
	public void sortMap() {
		// a Map with string keys and integer values
	    Map<String, Integer> budget = new HashMap<>();
	    budget.put("clothes", 120);
	    budget.put("grocery", 150);
	    budget.put("transportation", 100);
	    budget.put("utility", 130);
	    budget.put("rent", 1150);
	    budget.put("miscellneous", 90);

	    // let's sort this map by values first
	    Map<String, Integer> sorted = budget
	        .entrySet()
	        .stream()
	        .sorted(comparingByValue())
	        .collect(
	            toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
	                LinkedHashMap::new));
	}
	
	public static void importExcel(SessionData sessionData, String filePath, String currentDirectory, 
			String fieldName) throws IOException, InterruptedException, ClassNotFoundException{
		
		String fileName = "", GR_NO = "";
		
		JFrame f = new JFrame("Data update in progress. Don't Close");
		f.setBounds(sessionData.getScreenWidth()/2 - 150, sessionData.getScreenHeight()/2, 90, 25);
	    f.setSize(400, 0);
	    f.setResizable(false);
	    f.setVisible(true);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		try {
			String dateToday = getCurrentDate();
			String errors = "";
			String academicYear = getAcademicYear(sessionData, dateToday);
			String previousYear = getPreviousYear(sessionData, academicYear);
			dbValidate.connectDatabase(sessionData);
			long lDateTime = new Date().getTime();
			fileName  = "errors_in_import_"+lDateTime+".txt";
			
			PreparedStatement pstm = null;
			FileInputStream input = new FileInputStream(filePath);
			POIFSFileSystem fs = new POIFSFileSystem(input);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			String field_name_From_Excel = fieldName, field_name = "", field_value = "", section_nm = "";
			boolean validateField = true;
			Row row;
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				try{
					row = sheet.getRow(i);
					validateField = true;
					try{
						GR_NO = row.getCell(0).getStringCellValue().toString().trim().toUpperCase();
					} catch (Exception e) {
						GR_NO = ((int) row.getCell(0).getNumericCellValue())+"";
					}
					
					if(GR_NO.length() < 7){
						GR_NO = appendZero(GR_NO);
					}
					if(GR_NO.trim().equalsIgnoreCase("0000000")){
						return;
					}
					
					section_nm = row.getCell(1).getStringCellValue().toString().trim().toUpperCase();
					field_value = row.getCell(2).toString().trim();
					
					if(section_nm.equalsIgnoreCase("")){
						errors = " with some errors in file";
						writeToText(currentDirectory, fileName, "validate section name for GR_NO "+GR_NO);
						validateField = false;
						continue;
					}
					
					switch(field_name_From_Excel) {
			         case "Hobbies" :
		        	 	field_name = "EXTRA_1";
						field_value = replaceCommaApostrophy(field_value);
						if (checkComma(field_value)) {
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, "hobbies cannot contain |:; for GR_NO "+GR_NO);
							validateField = false;
							continue;
						} 
						field_value = "'"+field_value+"'";
			            break;
			         case "Student_Udise" :
			        	field_name = "SUID";
			        	field_value = replaceCommaApostrophy(field_value);
						
						if (checkComma(field_value)) {
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, "Student Udise id cannot contain |-:';, for GR_NO "+GR_NO);
							validateField = false;
							continue;
						}
						else if (field_value.length() > 50) {
							validateField = false;
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, charExceeded("Student Udise id", field_value, 50) + " for GR_NO "+GR_NO);
							continue;
						}
						field_value = "'"+field_value+"'";
			            break;
			         case "Adhaar_card" :
			        	field_name = "ADHAAR_CARD";
			        	field_value = replaceCommaApostrophy(field_value);
						
						if(!validateAadharNumber(field_value) && !(field_value).equalsIgnoreCase("") && (field_value).length() != 12){
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, "Please enter valid Adhaar Card for GR_NO "+GR_NO);
							validateField = false;
							continue;
						}
						field_value = "'"+field_value+"'";
						break;
			         case "Date_of_Admission" :
			        	field_name = "DATE_ADMITTED";
						if ((!validateDate(field_value) || field_value.equalsIgnoreCase(""))) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid Date of Admission for Gr No. "+GR_NO);
							continue;
						} else if (!validateDate(field_value)) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid Year of Admission for Gr No. "+GR_NO);
							continue;
						} else {
							field_value = "STR_TO_DATE('" + field_value.trim().toUpperCase() + "', '%d/%m/%Y')";
						}
			            break;
			         case "Date_of_Birth" :
			        	field_name = "DOB";
						if ((!validateDate(field_value) || field_value.equalsIgnoreCase(""))) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid Date of Birth for Gr No. "+GR_NO);
							continue;
						} else if (!validateDate(field_value)) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid Year of Birth for Gr No. "+GR_NO);
							continue;
						} else {
							field_value = "STR_TO_DATE('" + field_value.trim().toUpperCase() + "', '%d/%m/%Y')";
						}
			            break;
			         case "Date_of_Leaving" :
			        	field_name = "DATE_LEAVING";
						if ((!validateDate(field_value) || field_value.equalsIgnoreCase(""))) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid Date of Leaving for Gr No. "+GR_NO);
							continue;
						} else if (!validateDate(field_value)) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid Year of Leaving for Gr No. "+GR_NO);
							continue;
						} else {
							field_value = "STR_TO_DATE('" + field_value.trim().toUpperCase() + "', '%d/%m/%Y')";
						}
			            break;
			         case "Last_school" :
			        	field_name = "LAST_SCHOOL";
			        	field_value = replaceCommaApostrophy(field_value);
						if ((field_value.length() > 200)) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Last school cannot be grater than 200 characters for Gr No. "+GR_NO);
							continue;
						}
						field_value = "'"+field_value+"'";
			            break;
			         case "Birth_Place" :
			        	field_name = "BIRTH_PLACE";
			        	field_value = replaceCommaApostrophy(field_value);
			        	if (field_value.length() > 50) {
			        		validateField = false;
							JOptionPane.showMessageDialog(null, charExceeded("Birth Place", field_value, 50));
							writeToText(currentDirectory, fileName, charExceeded("Birth Place", field_value, 50)+" for Gr No. "+GR_NO);
							continue;
						} else if (checkComma(field_value)) {
							validateField = false;
							JOptionPane.showMessageDialog(null, "Please enter valid characters in Birth Place without |:;");
							writeToText(currentDirectory, fileName, "Please enter valid characters in Birth Place without |:; for Gr No. "+GR_NO);
							continue;
						}
						field_value = "'"+field_value+"'";
			            break;
			         case "Taluka" :
			        	field_name = "TALUKA";
			        	if (checkComma(field_value)) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid Taluka without |-:';, for Gr No. "+GR_NO);
							continue;
						} else if (field_value.length() > 30) {
							validateField = false;
							writeToText(currentDirectory, fileName, charExceeded("Taluka", field_value, 30)+" for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "District" :
			        	field_name = "DISTRICT";
			        	if (checkComma(field_value)) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid District without |-:';, for Gr No. "+GR_NO);
							continue;
						} else if (field_value.length() > 30) {
							validateField = false;
							writeToText(currentDirectory, fileName, charExceeded("District", field_value, 30)+" for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "State" :
			        	field_name = "STATE";
			        	if (checkComma(field_value)) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid State without |-:';, for Gr No. "+GR_NO);
							continue;
						} else if (field_value.length() > 30) {
							validateField = false;
							writeToText(currentDirectory, fileName, charExceeded("State", field_value, 30)+" for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "Country" :
			        	field_name = "COUNTRY";
			        	if (checkComma(field_value)) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid Country without |-:';, for Gr No. "+GR_NO);
							continue;
						} else if (field_value.length() > 30) {
							validateField = false;
							writeToText(currentDirectory, fileName, charExceeded("Country", field_value, 30)+" for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "Nationality" :
			        	field_name = "NATIONALITY";
			        	if (field_value.length() > 20) {
							validateField = false;
							writeToText(currentDirectory, fileName, charExceeded("Nationality", field_value, 20)+" for Gr No. "+GR_NO);
							continue;
						} else if (checkComma(field_value) || validateSpecial(field_value)) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid Nationality without |-:';, for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "Mother_Tongue" :
			        	field_name = "MOTHER_TONGUE";
			        	if (field_value.length() > 20) {
							validateField = false;
							writeToText(currentDirectory, fileName, charExceeded("Mother Tongue", field_value, 20)+" for Gr No. "+GR_NO);
							continue;
						} else if (checkComma(field_value) || validateSpecial(field_value)) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid MotherTongue without |-:';, for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "Contact_1" :
			        	field_name = "CONTACT_1";
			        	if(field_value.contains(".")) {
			        		field_value = field_value.substring(0,field_value.indexOf("."));
			        	}
			        	if (!field_value.equalsIgnoreCase("") && (!validateNumber(field_value) || field_value.length() != 10)) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid Mobile No. for Gr No. "+GR_NO);
							continue;
						} else if (field_value.length() > 20) {
							validateField = false;
							writeToText(currentDirectory, fileName, charExceeded("Contact", field_value, 20)+" for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "Contact_2" :
			        	field_name = "CONTACT_2";
			        	if(field_value.contains(".")) {
			        		field_value = field_value.substring(0,field_value.indexOf("."));
			        	}
			        	if (!field_value.equalsIgnoreCase("") && (!validateNumber(field_value) || field_value.length() != 10)) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid Mobile No. for Gr No. "+GR_NO);
							continue;
						} else if (field_value.length() > 20) {
							validateField = false;
							writeToText(currentDirectory, fileName, charExceeded("Contact", field_value, 20)+" for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "Academic_Year" :
			        	field_name = "ACADEMIC_YEAR";
			        	field_value = "'"+previousYear+"'";
			            break;
			         case "Admitted_Std" :
				        	field_name = "ADMITTED_STD";
				        	field_value = "'"+field_value+"'";
				            break;
			         case "Admitted_Div" :
				        	field_name = "ADMITTED_DIV";
				        	field_value = "'"+field_value+"'";
				            break;
			         case "Bank_Name" :
			        	field_name = "BANK";
			        	if (field_value.length() > 40) {
							validateField = false;
							writeToText(currentDirectory, fileName, charExceeded("Bank Name", field_value, 40)+" for Gr No. "+GR_NO);
							continue;
						} else if (checkComma(field_value) || validateSpecial(field_value)) {
							validateField = false;
							writeToText(currentDirectory, fileName, "Please enter valid Bank Name without |-:';, for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "Bank_Branch" :
				        	field_name = "BANK_BRANCH";
				        	if (field_value.length() > 90) {
								validateField = false;
								writeToText(currentDirectory, fileName, charExceeded("Bank Branch", field_value, 90)+" for Gr No. "+GR_NO);
								continue;
							} else if (checkComma(field_value) || validateSpecial(field_value)) {
								validateField = false;
								writeToText(currentDirectory, fileName, "Please enter valid Bank Branch without |-:';, for Gr No. "+GR_NO);
								continue;
							}
				        	field_value = "'"+field_value+"'";
				            break;
			         case "Bank_Account" :
				        	field_name = "BANK_ACCOUNT";
				        	if (field_value.length() > 20) {
								validateField = false;
								writeToText(currentDirectory, fileName, charExceeded("Bank Account", field_value, 20)+" for Gr No. "+GR_NO);
								continue;
							} else if (checkComma(field_value) || validateSpecial(field_value)) {
								validateField = false;
								writeToText(currentDirectory, fileName, "Please enter valid Bank Account without |-:';, for Gr No. "+GR_NO);
								continue;
							}
				        	field_value = "'"+field_value+"'";
				            break;
			         case "Bank_IFSC" :
				        	field_name = "BANK_IFSC";
				        	if (field_value.length() > 20) {
								validateField = false;
								writeToText(currentDirectory, fileName, charExceeded("Bank IFSC", field_value, 20)+" for Gr No. "+GR_NO);
								continue;
							} else if (checkComma(field_value) || validateSpecial(field_value)) {
								validateField = false;
								writeToText(currentDirectory, fileName, "Please enter valid Bank IFSC without |-:';, for Gr No. "+GR_NO);
								continue;
							}
				        	field_value = "'"+field_value+"'";
				            break;
			         case "Replace_Gr_No" :
			        	 field_name = "GR_NO";
				        	if (GR_NO.length() != 7) {
								validateField = false;
								writeToText(currentDirectory, fileName, charExceeded("GR No", field_value, 7)+" for Gr No. "+GR_NO);
								continue;
							} else if (checkComma(field_value) || validateSpecial(field_value)) {
								validateField = false;
								writeToText(currentDirectory, fileName, "Please enter valid GR No without |-:';, for Gr No. "+GR_NO);
								continue;
							}
				        	field_value = "'"+field_value+"'";
				            break;
			         case "First_Name" :
			        	 field_name = "FIRST_NAME";
				        	if (field_name.length() > 50) {
								validateField = false;
								writeToText(currentDirectory, fileName, charExceeded("FirstName", field_value, 50)+" for Gr No. "+GR_NO);
								continue;
							} else if (checkComma(field_value) || validateSpecial(field_value)) {
								validateField = false;
								writeToText(currentDirectory, fileName, "Please enter valid FirstName, for Gr No. "+GR_NO);
								continue;
							}
				        	field_value = "'"+field_value+"'";
				            break;
			         case "Last_Name" :
			        	 field_name = "LAST_NAME";
				        	if (field_name.length() > 50) {
								validateField = false;
								writeToText(currentDirectory, fileName, charExceeded("LastName", field_value, 50)+" for Gr No. "+GR_NO);
								continue;
							} else if (checkComma(field_value) || validateSpecial(field_value)) {
								validateField = false;
								writeToText(currentDirectory, fileName, "Please enter valid LastName, for Gr No. "+GR_NO);
								continue;
							}
				        	field_value = "'"+field_value+"'";
				            break;
			         case "Father_Name" :
			        	 field_name = "FATHER_NAME";
				        	if (field_name.length() > 50) {
								validateField = false;
								writeToText(currentDirectory, fileName, charExceeded("FatherName", field_value, 50)+" for Gr No. "+GR_NO);
								continue;
							} else if (checkComma(field_value) || validateSpecial(field_value)) {
								validateField = false;
								writeToText(currentDirectory, fileName, "Please enter valid FatherName, for Gr No. "+GR_NO);
								continue;
							}
				        	field_value = "'"+field_value+"'";
				            break;
			         case "Mother_Name" :
			        	 field_name = "MOTHER_NAME";
				        	if (field_name.length() > 50) {
								validateField = false;
								writeToText(currentDirectory, fileName, charExceeded("MotherName", field_value, 50)+" for Gr No. "+GR_NO);
								continue;
							} else if (checkComma(field_value) || validateSpecial(field_value)) {
								validateField = false;
								writeToText(currentDirectory, fileName, "Please enter valid MotherName, for Gr No. "+GR_NO);
								continue;
							}
				        	field_value = "'"+field_value+"'";
				            break;
			         default :
			            logger.error("Invalid value");
			      }
					
					/*if(field_name_From_Excel.equalsIgnoreCase("Hobbies")){
						field_name = "EXTRA_1";
						field_value = "'"+replaceCommaApostrophy(field_value)+"'";
						
						if (checkComma(field_value)) {
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, "hobbies cannot contain |:; for GR_NO "+GR_NO);
							validateField = false;
							continue;
						}
					}
					else if(field_name_From_Excel.equalsIgnoreCase("Student_Udise")){
						field_name = "SUID";
						field_value = "'"+replaceCommaApostrophy(field_value)+"'";
						
						if (checkComma(field_value)) {
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, "Student Udise id cannot contain |-:';, for GR_NO "+GR_NO);
							validateField = false;
							continue;
						}
						else if (field_value.length() > 50) {
							validateField = false;
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, charExceeded("Student Udise id", field_value, 50) + " for GR_NO "+GR_NO);
							continue;
						}
					}
					else if(field_name_From_Excel.equalsIgnoreCase("Adhaar_card")){
						field_name = "ADHAAR_CARD";
						field_value = "'"+replaceCommaApostrophy(field_value)+"'";
						
						if(!validateAadharNumber(field_value) && !(field_value).equalsIgnoreCase("") && (field_value).length() != 12){
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, "Please enter valid Adhaar Card for GR_NO "+GR_NO);
							validateField = false;
							continue;
						} 
					}
					else if(field_name_From_Excel.equalsIgnoreCase("Date_of_Admission")){
						field_name = "DATE_ADMITTED";
						if ((!validateDate(field_value) || field_value.equalsIgnoreCase(""))) {
							validateField = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Date of Admission for Gr No. "+GR_NO);
						} else if (!validateDate(field_value)) {
							validateField = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Year of Admission for Gr No. "+GR_NO);
						} else {
							field_value = "STR_TO_DATE('" + field_value.trim().toUpperCase() + "', '%d/%m/%Y')";
						}
					}
					else if(field_name_From_Excel.equalsIgnoreCase("Date_of_Birth")){
						field_name = "DOB";
						if ((!validateDate(field_value) || field_value.equalsIgnoreCase(""))) {
							validateField = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Date of Birth for Gr No. "+GR_NO);
						} else if (!validateDate(field_value)) {
							validateField = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Year of Birth for Gr No. "+GR_NO);
						} else {
							field_value = "STR_TO_DATE('" + field_value.trim().toUpperCase() + "', '%d/%m/%Y')";
						}
					}
					else if(field_name_From_Excel.equalsIgnoreCase("Last_school")){
						field_name = "LAST_SCHOOL";
						field_value = "'"+replaceCommaApostrophy(field_value)+"'";
						if ((field_value.length() > 200)) {
							validateField = false;
							JOptionPane.showMessageDialog(null, "Last school cannot be grater than 200 characters for Gr No. "+GR_NO);
						}
					}*/
					
					if(field_name.equalsIgnoreCase("LAST_NAME") || field_name.equalsIgnoreCase("FIRST_NAME") || field_name.equalsIgnoreCase("FATHER_NAME") || field_name.equalsIgnoreCase("MOTHER_NAME")) {
						field_value = field_value.toUpperCase();
					}
						
					String updateAcademicToPrevious = "";
					if(validateField && !field_name.equalsIgnoreCase("GR_NO")){
						//====hs_general_register=========//
						String sql = "UPDATE hs_general_register SET "+field_name+" = "+field_value+" "
								+ "where GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
		
						pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(sql);
						pstm.execute();
					}
					
					if(validateField && (field_name.equalsIgnoreCase("LAST_NAME") || field_name.equalsIgnoreCase("FIRST_NAME") || field_name.equalsIgnoreCase("FATHER_NAME"))){
						try{
							
							updateAcademicToPrevious = "update class_allotment SET "+field_name+" = "+field_value+" "
									+ "where GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
							
							updateAcademicToPrevious = "update marks_entry SET "+field_name+" = "+field_value+" "
									+ "where GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
							
							updateAcademicToPrevious = "update result_data SET "+field_name+" = "+field_value+" "
									+ "where GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
							
							updateAcademicToPrevious = "update fees_data_mandatory SET "+field_name+" = "+field_value+" "
									+ "where GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
							
							updateAcademicToPrevious = "update fee_status SET "+field_name+" = "+field_value+" "
									+ "where GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
							
							updateAcademicToPrevious = "update student_subject SET "+field_name+" = "+field_value+" "
									+ "where GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							logger.error("Exception "+e);
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
					}
					
					if(field_name.equalsIgnoreCase("Academic_Year")){
						try{
							updateAcademicToPrevious = "update attendance set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							logger.error("Exception "+e);
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update class_allotment set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							logger.error("Exception "+e);
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update fee_status set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							logger.error("Exception "+e);
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update fees_data_mandatory set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							logger.error("Exception "+e);
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update fees_data_optional set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							logger.error("Exception "+e);
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update marks_entry set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							logger.error("Exception "+e);
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update optional_allotment set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							logger.error("Exception "+e);
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update optional_fee_allotment set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							logger.error("Exception "+e);
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update result_data set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							logger.error("Exception "+e);
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update sms_data set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							logger.error("Exception "+e);
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update statement_data set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							logger.error("Exception "+e);
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update student_subject set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							logger.error("Exception "+e);
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
					}
					else if(field_name.equalsIgnoreCase("GR_NO")){
						String updateField = "";
						ResultSet resultSet = null;
						String table_names[] = {"hs_general_register","attendance","attendance_period","class_allotment","fee_status",
								"fees_data_mandatory","fees_data_optional","marks_entry","optional_allotment","optional_fee_allotment",
								"result_data","sms_data","statement_data","student_imgs","student_subject"};
						try{
							if(field_value.contains("'")) {
								field_value = field_value.substring(1, field_value.length()-1);
							}
							if(field_value.length() < 7){
								field_value = appendZero(field_value);
							}
							if(field_value.trim().equalsIgnoreCase("0000000")){
								return;
							}
							field_value = "'"+field_value+"'";
							
							String findQuery = "SELECT DISTINCT "+field_name+" FROM "+sessionData.getDBName()+"."+"hs_general_register "
									+ "where "+field_name+"="+field_value+" AND SECTION_NM='"+section_nm+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(findQuery);
							resultSet = pstm.executeQuery();
							if (!resultSet.last()) {
								for(int k = 0; k < table_names.length; k++) {
									try{
										updateField = "update "+sessionData.getDBName()+"."+table_names[k]+" set "+field_name+"="+field_value+" "
												+ "where GR_NO='"+GR_NO+"' AND SECTION_NM='"+section_nm+"'";
										pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateField);
										pstm.execute();
									}
									catch (Exception e) {
									}
								}
							}
							else {
								writeToText(currentDirectory, fileName, GR_NO + " cannot be replaced by "+field_value+" as "+field_value
										+" already exists in section "+section_nm);
							}
						}
						catch (Exception e) {
							logger.error("Exception "+e);
							errors = " with some errors in file";
							writeToText(currentDirectory, fileName, GR_NO + " => "+field_name+" : " +e);
						}
					}
					
//					sessionData.getConnection().commit();
				} catch (Exception e) {
					logger.error("Exception "+e);
					errors = " with some errors in file";
					writeToText(currentDirectory, fileName, GR_NO + " => " +e);
				}
			}
			pstm.close();
			sessionData.getConnection().close();
			input.close();
			JOptionPane.showMessageDialog(null, "Excel inserted successfully.."+errors);
			try {
				String fileAddress = currentDirectory+"\\"+fileName;
				if ((new File(fileAddress)).exists()) {
					if((System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
						Runtime.getRuntime().exec(new String[]{"/usr/bin/open", fileAddress});
					}
					else {
						Process process = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+fileAddress);
						process.waitFor();
					}
				}
//				else {
//					JOptionPane.showMessageDialog(null, "File not found"+errors);
//				}
			} catch (Exception e) {
				logger.error(":: -----Exception---- ::\n"+e);
			}
		} catch (FileNotFoundException e) {
			logger.error("Exception "+e);
			writeToText(currentDirectory, fileName, GR_NO + " => " +e);
			JOptionPane.showMessageDialog(null, "D drive DATA.xls (The system cannot find the file specified)");
		} catch (SQLException e) {
			logger.error("Exception "+e);
			writeToText(currentDirectory, fileName, GR_NO + " => " +e);
			JOptionPane.showMessageDialog(null, "Error in import :: "+e);
		} catch (Exception e) {
			logger.error("Exception "+e);
			writeToText(currentDirectory, fileName, GR_NO + " => " +e);
			JOptionPane.showMessageDialog(null, "Error in import :: "+e);
		}
		finally {
			f.setVisible(false);
		}
	}
	
	public static void importBackupDataFromExcel(SessionData sessionData, String filePath, String currentDirectory, 
			String fieldName) throws IOException, InterruptedException, ClassNotFoundException{
		
		String fileName = "", GR_NO = "";
		
		JFrame f = new JFrame("Data update in progress. Don't Close");
		f.setBounds(sessionData.getScreenWidth()/2 - 150, sessionData.getScreenHeight()/2, 90, 25);
	    f.setSize(400, 0);
	    f.setResizable(false);
	    f.setVisible(true);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		try {
			String dateToday = getCurrentDate();
			String errors = "";
			String academicYear = getAcademicYear(sessionData, dateToday);
			String previousYear = getPreviousYear(sessionData,academicYear);
			dbValidate.connectDatabase(sessionData);
			long lDateTime = new Date().getTime();
			fileName  = "errors_in_import_"+lDateTime+".txt";
			
			PreparedStatement pstm = null;
			FileInputStream input = new FileInputStream(filePath);
			POIFSFileSystem fs = new POIFSFileSystem(input);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			String field_name_From_Excel = fieldName, field_name = "", field_value = "", section_nm = "", prevGr = "", cellValue = "";
			boolean validateField = true;
			Row row;
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				try{
					row = sheet.getRow(i);
					validateField = true;
					try{
						GR_NO = row.getCell(0).getStringCellValue().toString().trim().toUpperCase();
					} catch (Exception e) {
						GR_NO = ((int) row.getCell(0).getNumericCellValue())+"";
					}
					
					if(GR_NO.length() < 7){
						GR_NO = appendZero(GR_NO);
					}
					if(GR_NO.trim().equalsIgnoreCase("0000000")){
						return;
					}
					
					section_nm = row.getCell(1).getStringCellValue().toString().trim().toUpperCase();
					field_value = row.getCell(2).toString().trim();
					
					if(section_nm.equalsIgnoreCase("")){
						errors = " with some errors in file";
						writeToText(currentDirectory, fileName, "validate section name for GR_NO "+GR_NO);
						validateField = false;
						continue;
					}
					
					if(prevGr.equalsIgnoreCase(GR_NO) || prevGr.equalsIgnoreCase("")) {
						cellValue = cellValue + field_value;
					}
					else if(!prevGr.equalsIgnoreCase("")){
						cellValue = cellValue + field_value;
						if(validateField){
							//====hs_general_register=========//
//							cellValue = replaceCommaApostrophy(cellValue);
							String sql = cellValue;
			
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(sql.trim());
							pstm.execute();
						}
					}
					prevGr = GR_NO;
				} catch (Exception e) {
					logger.error("Exception "+e);
					errors = " with some errors in file";
					writeToText(currentDirectory, fileName, GR_NO + " => " +e);
				}
			}
			pstm.close();
			sessionData.getConnection().close();
			input.close();
			JOptionPane.showMessageDialog(null, "Excel inserted successfully.."+errors);
			try {
				String fileAddress = currentDirectory+"\\"+fileName;
				if ((new File(fileAddress)).exists()) {
					if((System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
						Runtime.getRuntime().exec(new String[]{"/usr/bin/open", fileAddress});
					}
					else {
						Process process = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+fileAddress);
						process.waitFor();
					}
				}
//				else {
//					JOptionPane.showMessageDialog(null, "File not found"+errors);
//				}
			} catch (Exception e) {
				logger.error(":: -----Exception---- ::\n"+e);
			}
		} catch (FileNotFoundException e) {
			logger.error("Exception "+e);
			writeToText(currentDirectory, fileName, GR_NO + " => " +e);
			JOptionPane.showMessageDialog(null, "D drive DATA.xls (The system cannot find the file specified)");
		} catch (SQLException e) {
			logger.error("Exception "+e);
			writeToText(currentDirectory, fileName, GR_NO + " => " +e);
			JOptionPane.showMessageDialog(null, "Error in import :: "+e);
		} catch (Exception e) {
			logger.error("Exception "+e);
			writeToText(currentDirectory, fileName, GR_NO + " => " +e);
			JOptionPane.showMessageDialog(null, "Error in import :: "+e);
		}
		finally {
			f.setVisible(false);
		}
	}
	
	public void readLineFromText() {
		// We need to provide file path as the parameter:
		// double backquote is to avoid compiler interpret words
		// like \test as \t (ie. as a escape sequence)
		File file = new File("D:\\NESSCHOOL_app\\BACKUP\\SCHOOL\\09_Apr_2019\\backup.sql");

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			int i = 1;
			while ((st = br.readLine()) != null)
			{
				if (st.startsWith("INSERT")) {
					i++;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getStdFieldFromTable(String tableName) {
		if(tableName.equalsIgnoreCase("class_allotment") || tableName.equalsIgnoreCase("hs_general_register") || tableName.equalsIgnoreCase("optional_allotment") || 
				tableName.equalsIgnoreCase("sms_data") || tableName.equalsIgnoreCase("statement_data")) {
			return "PRESENT_STD";
		}
		else if(tableName.equalsIgnoreCase("fees_data_mandatory") || tableName.equalsIgnoreCase("fees_data_optional") || tableName.equalsIgnoreCase("fees_head") || 
				tableName.equalsIgnoreCase("fees_report_mandatory") || tableName.equalsIgnoreCase("fees_report_optional") || tableName.equalsIgnoreCase("hs_general_register") || 
				tableName.equalsIgnoreCase("multiple_head") || tableName.equalsIgnoreCase("result_data") || tableName.equalsIgnoreCase("student_subject") || 
				tableName.equalsIgnoreCase("subject") || tableName.equalsIgnoreCase("subject_allotment")) {
			return "STD_1";
		}
		else {
			return "";
		}	
	}
	
	public String getFirstLetterFromString(String str) {
		String initials = "";
		for (String s : str.split(" ")) {
			if(!s.equalsIgnoreCase("KG")) {
				initials+=s.charAt(0);
			}
			else {
				initials+=s;
			}
		}
		return initials;
	}
	
	public void startProgressBar(JFrame f) {
		screenWidth = screeWidth();
		screenHeight = screeHeight();
		mainCentre = (screenWidth - 150) / 2;
		f.setBounds(screenWidth/2 - 250, screenHeight/2, 90, 25);
	    f.setSize(600, 0);
	    f.setResizable(false);
	    f.setVisible(true);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void closeProgressBar(JFrame f) {
		f.setVisible(false);
	}
	
	public String isConcession(LinkedHashMap concessionMap) {
		int i = 0;
		String flag = "false";
		Set setsc = concessionMap.entrySet();
		Iterator isc = setsc.iterator();
		while(isc.hasNext()) {
			Map.Entry me = (Map.Entry)isc.next();
			if(Double.parseDouble(me.getValue().toString()) > 0) {
				flag = "true";
				i++;
			}
		}
		return flag+"|"+i;
	}
	
	public String renewLogic(SessionData session, TreeMap tm) {
		int reply = 0, renewCode = 0, renewMonth = 0;
		String lastRenewedDetails = "0|month|code", userKey = "", codeMonth = "", matchCode = "", matchKey = "",
				userKeyDate = "", renewDate = "", userCode = "", userMonth = "";
		
		try {
			String renewStr = session.getConfigMap().get("RENEW_CODE");
			Date currentDate = new Date();
			
			if(tm.get("renew_code") != null && !tm.get("renew_code").toString().equalsIgnoreCase("")) {
				lastRenewedDetails = tm.get("renew_code").toString();
			}
			
			renewCode = (Integer.parseInt(lastRenewedDetails.substring(0,lastRenewedDetails.indexOf("|")))+1);
			
			reply = JOptionPane.showConfirmDialog(null, "Would You Like to renew. Please call Maauli Software Solutions and share below code ? "
					+ "\n Code : "+renewCode, "Confirm Renew", JOptionPane.YES_NO_OPTION);
			
			if (reply == JOptionPane.YES_OPTION) {
				userKey = JOptionPane.showInputDialog("Please Enter a key to renew shared by Maauli Software Solutions.");
				if(userKey.length() > 14) {
					userKeyDate = userKey.substring(6, 14);
				}
				codeMonth = userKey.substring(userKey.length()-4);
				userCode = codeMonth.substring(0, 2);
				userMonth = codeMonth.substring(2, 4);
				
				if (!renewStr.contains("|"+codeMonth+":")){
					JOptionPane.showMessageDialog(null, "Please enter valid key to renew.");
				}
				else if (!userKeyDate.equalsIgnoreCase(dateToDDMMYYYY_withoutSlash(currentDate))){
					JOptionPane.showMessageDialog(null, "Key has expired. Please contact Maauli Software Solutions");
				}
				else if (renewStr.contains("|"+codeMonth+":")) {
					String expiryFromDB = tm.get("expiry_date").toString();
					matchCode = renewStr.substring(renewStr.indexOf("|"+codeMonth+":"));
					matchCode = matchCode.substring(6, 12);
					matchKey = matchCode + dateToDDMMYYYY_withoutSlash(currentDate) + codeMonth;
					
					if(userKey.equalsIgnoreCase(matchKey) && Integer.parseInt(userCode) == renewCode) {
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						Date todayDate = formatter.parse(getCurrentDate());
				        Date expiryDate = formatter.parse(MM_ddlmmlyyyy(expiryFromDB));
				        int daysLeft = daysBetween(expiryDate, todayDate);
				        renewMonth = Integer.parseInt(codeMonth.substring(2));
				        
				        if(daysLeft > 0) {//expiry in past
							renewDate = addMonthToDate(renewMonth, 0, 0, 0);
				        }
				        else {
				        	int year = Integer.parseInt(expiryFromDB.substring(6));
				        	int month = Integer.parseInt(expiryFromDB.substring(3,5));
				        	int day = Integer.parseInt(expiryFromDB.substring(0,2));
							renewDate = addMonthToDate(renewMonth, year, month, day);
				        }

				        if(dbValidate.connectDatabase(session)){
				        	dbValidate.updateExpiryByCode(session, renewDate, userCode + "|" + userMonth + "|" +matchCode);
				        }
						JOptionPane.showMessageDialog(null, "Software renewed successfully");
					}
					else {
						JOptionPane.showMessageDialog(null, "Please enter valid key to renew.");
					}
				}
			}
		}
		catch(Exception e) {
			logException(e);
		}
		return userKey;
	}
	
	public String addMonthToDate(int renewMonth, int year, int month, int date) {
		try {
			Calendar cal = Calendar.getInstance();
			if(year != 0) {
				cal.set(year, month-1, date);
			}
			cal.add(Calendar.MONTH, renewMonth);
			return dateToDDMMYYYY(cal.getTime());
		} catch (Exception e) {
			logException(e);
			return "";
		}
	}
	
	public String getExamType(String retStd, String retExam, String academicYear) {
		String examTypeList = "EXAM_TYPE_5";
		if((retStd.equalsIgnoreCase("XI") || 
				retStd.equalsIgnoreCase("XII")) && retExam.equalsIgnoreCase("Semester 1")){
			examTypeList = "EXAM_TYPE_1";
		}
		else if((retStd.equalsIgnoreCase("XI") || 
				retStd.equalsIgnoreCase("XII")) && retExam.equalsIgnoreCase("Semester 2")){
			examTypeList = "EXAM_TYPE_2";
		}
		else if(is9thEvaluation(retStd, academicYear) && retExam.equalsIgnoreCase("Semester 1")){
			examTypeList = "EXAM_TYPE_6";
		}
		else if(is9thEvaluation(retStd, academicYear) && retExam.equalsIgnoreCase("Semester 2")){
			examTypeList = "EXAM_TYPE_7";
		}
		else if((retStd.equalsIgnoreCase("X") || retStd.equalsIgnoreCase("IX")) && retExam.equalsIgnoreCase("Semester 1")){
			examTypeList = "EXAM_TYPE_3";
		}
		else if((retStd.equalsIgnoreCase("X") || retStd.equalsIgnoreCase("IX")) && retExam.equalsIgnoreCase("Semester 2")){
			examTypeList = "EXAM_TYPE_4";
		}
		
		return examTypeList;
	}
	
	public String getActualLvType(String lvTypeFromExcel, int stdInt) {
		if (lvTypeFromExcel.equalsIgnoreCase("FUT")
				|| lvTypeFromExcel.equalsIgnoreCase("SUT")
				|| lvTypeFromExcel.equalsIgnoreCase("TEST")) {
			return "OBT";
		} else if (lvTypeFromExcel.equalsIgnoreCase("DOB")) {
			return "DOBS";
		} else if (stdInt > 8 && (lvTypeFromExcel.equalsIgnoreCase("ACTIVITY") || lvTypeFromExcel.equalsIgnoreCase("PROJ"))) {
			return "PRACT";
		} else if (lvTypeFromExcel.equalsIgnoreCase("ACT")) {
			return "ACTIVITY";
		} else if (lvTypeFromExcel.contains("SUM_")) {
			return lvTypeFromExcel.substring(lvTypeFromExcel.indexOf("_")+1)+"1";
		} else {
			return lvTypeFromExcel;
		}
	}
	
	public boolean is9thEvaluation(String std, String academicYear) {
		int academicStart = 0;
		if(!academicYear.equalsIgnoreCase("")) {
			academicStart = Integer.parseInt(academicYear.substring(0, 4));
			
			if((std.equalsIgnoreCase("IX") || std.equalsIgnoreCase("X")) && academicStart >= 2019){
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	} 
	
	public double checkBalanceAmount(double totalAmount, String totalAmountPaid, double totalBalanceFromDB) {
		return ((totalAmount + totalBalanceFromDB) - Double.parseDouble(totalAmountPaid));
	}
	
	public String balanceAmountMessage(double balanceAmount) {
		if(balanceAmount > 0) {
			return "Balance amount of Rs."+balanceAmount+ " is due to be paid next time";
		}
		else if(balanceAmount < 0) {
			return "Extra amount of Rs."+Math.abs(balanceAmount)+ " will be adjusted in next receipt";
		}
		return "Required amount is paid";
	}
	
	public String getStringArrayElement(String data, String separator, int elementIndex, String receiptNo) {
		String[] columnSplit = null, feeSplit =	 null;
		
		if(data != null && !data.equalsIgnoreCase("")) {
			feeSplit = data.toString().split("\\!");
			for(int i = 0; i < feeSplit.length; i++) {
				if(receiptNo.equalsIgnoreCase("") && i == (feeSplit.length - 1)) {
					columnSplit = feeSplit[i].toString().split("\\"+separator);
					return  columnSplit[elementIndex];
				}
				else if(receiptNo.equalsIgnoreCase(feeSplit[i])){
					columnSplit = feeSplit[i].toString().split("\\"+separator);
					return  columnSplit[elementIndex];
				}
			}
		}
		return "";
	}
	
	public String typeMarks(String type, String dob, String obt, String ora, String ass, String wri, String pra, String pre,
			String mca, String act, String pro, String oth, String ora1, String pra1, String wri1, String lis, String spe, 
			String ass1) {

		String typeMarks = "";

		switch (type.toUpperCase()) {
		case "DOBS":
			typeMarks = dob;
			break;
		case "OBT":
			typeMarks = obt;
			break;
		case "TEST":
			typeMarks = obt;
			break;
		case "FUT":
			typeMarks = obt;
			break;
		case "SUT":
			typeMarks = obt;
			break;
		case "ORAL":
			typeMarks = ora;
			break;
		case "ASSIGN":
			typeMarks = ass;
			break;
		case "WRITE":
			typeMarks = wri;
			break;
		case "PRACT":
			typeMarks = pra;
			break;
		case "PRES":
			typeMarks = pre;
			break;
		case "MCA":
			typeMarks = mca;
			break;
		case "ACTIVITY":
			typeMarks = act;
			break;
		case "PROJECT":
			typeMarks = pro;
			break;
		case "OTHER":
			typeMarks = oth;
			break;
		case "ORAL1":
			typeMarks = ora1;
			break;
		case "PRACT1":
			typeMarks = pra1;
			break;
		case "WRITE1":
			typeMarks = wri1;
			break;
		case "LISTEN":
			typeMarks = lis;
			break;
		case "SPEAK":
			typeMarks = spe;
			break;
		case "ASSIGN1":
			typeMarks = ass1;
			break;
		default:
			typeMarks = "";
			break;
		}
		return typeMarks;
	}
	
	public String getConcatString(String subjectMarksDB, String subjectMarksListDB, String separator, String type, List<String> typeArrayList) {
		if(subjectMarksDB.equalsIgnoreCase("")) {
			subjectMarksDB = "-";
		}
		if(typeArrayList.contains(type)) {
			subjectMarksListDB = subjectMarksListDB + separator + subjectMarksDB;
		}
		return subjectMarksListDB;
	}
	
	public String ifHyphenThenZero(String text) {
		if(text.trim().equalsIgnoreCase("-") || text.trim().equalsIgnoreCase("")) {
			return "0";
		}
		return text;
	}
}
