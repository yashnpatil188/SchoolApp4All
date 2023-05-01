package org.com.maauli;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;


public class BonafidePDF {
	DBValidate findStudentDB = new DBValidate();
	Common commonLc = new Common();
	static String fileName 				= "";
	static  boolean fileOpenFlag		= false;
	List<String> selectedBonafideList 	= new ArrayList();
	static String fileAddress			= "";
	static String path					= "";
	static String std 					= "";
    static String div 					= "";
    static String section				= "";
    static String secName				= "";
    static String user_name 			= "";
	static String user_role 			= "";
	static String Mst_Miss	 			= "";
	static String he_she	 			= "";
	static String his_her	 			= "";
	static String is_was	 			= "was";
	static String app_header 			= "";
    static String app_header_0 			= "";
    static String bonafide_header		= "";
    static String bonafide_header_0		= "";
    static String footer				= "";
	static String app_header_0_fontName = "";
    static int app_header_0_fontSize = 0;
    static int app_header_0_widthSpace = 0;
    static int app_header_0_heightSpace = 0;
    static String app_header_fontName = "";
    static int app_header_fontSize = 0;
    static int app_header_widthSpace = 0;
    static int app_header_heightSpace = 0;
    static String app_header_2 = "";
    static String app_header_2_fontName = "";
    static int app_header_2_fontSize = 0;
    static int app_header_2_widthSpace = 0;
    static int app_header_2_heightSpace = 0;
    static String kumar = "";
    static String kumari = "";
    static boolean isMotherName;
    static boolean isFooterRequired;
    static String pdf_logo_img_path = "", pdf_header_img_path = "";
	
    ResourceBundle bundle    		= ResourceBundle.getBundle("org.com.accesser.school"); 
    static Logger logger 			= Logger.getLogger(BonafidePDF.class.getName());
    static SessionData sessionData  = new SessionData();
	
	public void bonafideCertificate(SessionData sessionData1, String view_save, List<String> passGrList,String sec, 
			String certType, String retUserName, String retUserRole, boolean isSingleCopy) throws DocumentException{
		System.gc();
		logger.info("Inside bonafideCertificate pdf constructor..............");
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		section = sec;
    	logger.info("section :: "+section);
    	std     = bundle.getString(section.toUpperCase()+"_STD");
    	logger.info("std :: "+std);
    	div     = bundle.getString(section.toUpperCase()+"_DIV");
    	logger.info("div :: "+div);
    	secName = bundle.getString(section.toUpperCase()+"_SEC");
    	logger.info("secName :: "+secName);
    	app_header = bundle.getString("APP_HEADER_"+sessionData.getAppType());
        app_header_0 = bundle.getString("APP_HEADER_0_"+sessionData.getAppType());
        app_header_0_fontName = bundle.getString("APP_HEADER_0_FONTNAME_"+sessionData.getAppType());
        app_header_0_fontSize = Integer.parseInt(bundle.getString("APP_HEADER_0_FONTSIZE_"+sessionData.getAppType()));
    	app_header_0_widthSpace = Integer.parseInt(bundle.getString("APP_HEADER_0_WIDTHSPACE_"+sessionData.getAppType()));
    	app_header_0_heightSpace = Integer.parseInt(bundle.getString("APP_HEADER_0_HEIGHTSPACE_"+sessionData.getAppType()));
    	app_header_fontName = bundle.getString("APP_HEADER_FONTNAME_"+sessionData.getAppType());
        app_header_fontSize = Integer.parseInt(bundle.getString("APP_HEADER_FONTSIZE_"+sessionData.getAppType()));
    	app_header_widthSpace = Integer.parseInt(bundle.getString("APP_HEADER_WIDTHSPACE_"+sessionData.getAppType()));
    	app_header_heightSpace = Integer.parseInt(bundle.getString("APP_HEADER_HEIGHTSPACE_"+sessionData.getAppType()));
    	app_header_2 = bundle.getString("APP_HEADER_2_"+sessionData.getAppType());
        app_header_2_fontName = bundle.getString("APP_HEADER_2_FONTNAME_"+sessionData.getAppType());
        app_header_2_fontSize = Integer.parseInt(bundle.getString("APP_HEADER_2_FONTSIZE_"+sessionData.getAppType()));
    	app_header_2_widthSpace = Integer.parseInt(bundle.getString("APP_HEADER_2_WIDTHSPACE_"+sessionData.getAppType()));
    	app_header_2_heightSpace = Integer.parseInt(bundle.getString("APP_HEADER_2_HEIGHTSPACE_"+sessionData.getAppType()));
        bonafide_header = bundle.getString("BONAFIDE_HEADER_"+sessionData.getAppType());
        bonafide_header_0 = bundle.getString("BONAFIDE_HEADER_0_"+sessionData.getAppType());
        kumar = bundle.getString("KUMAR");
        kumari = bundle.getString("KUMARI");
        isMotherName = Boolean.parseBoolean(bundle.getString("MOTHER_NAME"));
        isFooterRequired = Boolean.parseBoolean(bundle.getString("FOOTER_REQUIRED"));
        footer = bundle.getString("FOOTER_"+sessionData1.getAppType());
        Boolean showAdhaar = Boolean.parseBoolean(bundle.getString("SHOW_ADHAAR_BONAFIDE_"+sessionData1.getAppType()));
        Boolean showSUID = Boolean.parseBoolean(bundle.getString("SHOW_SUID_BONAFIDE_"+sessionData1.getAppType()));
        String img_path = bundle.getString("IMAGE_PATH");
		int image_pdf_office_pos_y = Integer.parseInt(bundle.getString("IMAGE_PDF_BONAFIDE_OFFICE_POS_Y"))+45;
		
		boolean pdf_header_bonafide_logo_flag = Boolean.parseBoolean(bundle.getString("PDF_HEADER_BONAFIDE_LOGO_FLAG"));
		if(pdf_header_bonafide_logo_flag) {
			pdf_logo_img_path = bundle.getString("IMAGE_PDF_LOGO_"+sessionData.getAppType());
		}
		
		String pdf_header_bonafide_image_flag = bundle.getString("PDF_HEADER_BONAFIDE_IMAGE_FLAG");
		if(pdf_header_bonafide_image_flag.equalsIgnoreCase("true")) {
			pdf_header_img_path = bundle.getString("IMAGE_PDF_BONAFIDE_HEADER_"+sessionData.getAppType());
		}
		float image_pdf_scalepercent = Float.parseFloat(bundle.getString("IMAGE_PDF_BONAFIDE_SCALEPERCENT")) - 9.0f;
		int image_pdf_pos_x = Integer.parseInt(bundle.getString("IMAGE_PDF_BONAFIDE_POS_X"));
		int image_pdf_pos_y = Integer.parseInt(bundle.getString("IMAGE_PDF_BONAFIDE_POS_Y"))+45;
		
		int image_pdf_logo_pos_x = Integer.parseInt(bundle.getString("IMAGE_PDF_LOGO_POS_X"));
		int image_pdf_logo_pos_y = Integer.parseInt(bundle.getString("IMAGE_PDF_LOGO_POS_Y"))+45;
		float image_pdf_logo_scalepercent = Float.parseFloat(bundle.getString("IMAGE_PDF_LOGO_SCALEPERCENT")) - 9.0f;
    	
		List<String> recLCList = new ArrayList();
		
		String separator ="----------------------------------------------------------------------------------------------------------------------------------";
		logger.info("view_save :: "+view_save);
		path = commonLc.createTodayFolder(commonLc.getDriveName() + bundle.getString("BONAFIDE_PDF_PATH_"+sessionData.getDBName()),true)+"/";
		
		int lcCountYear = 0;
		int dupLcCountYear = 0;
		int triLcCountYear = 0;
		
		
		try {
				if(findStudentDB.connectDatabase(sessionData)){
					recLCList = findStudentDB.findStudentBonafide(sessionData, passGrList,section,"BONAFIDE");
					if(view_save.equalsIgnoreCase("VALIDATE")){
						lcCountYear = findStudentDB.getLastCount(sessionData,"HS_GENERAL_REGISTER", "ORIGINAL_LC");
						dupLcCountYear = findStudentDB.getLastCount(sessionData,"HS_GENERAL_REGISTER", "DUPLICATE_LC");
					}
		
				try {
		
					///////start of pdf creation/////////////////////
					fileName  = "BonafideCertificatePrint"+commonLc.timeInMillis()+".pdf";
					fileAddress = path+fileName;
					File file = new File(fileAddress);
					FileOutputStream fileout = new FileOutputStream(file);
//					Document document = new Document(com.lowagie.text.PageSize.A4);
					Document document = new Document(com.itextpdf.text.PageSize.A4);
					PdfWriter pdfWriter = PdfWriter.getInstance(document, fileout);
					document.addHeader("ABC", "XYZ");
//					document.addAuthor("MAAULI EDUCATION SOCIETY");
					document.addTitle("Bonafide Certificate");
					document.open();
					
					//font for unicode to marathi
				    final String FONT = "resources/fonts/FreeSans.ttf";
					Font f = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			        
					Font fontClass1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
					fontClass1.setColor(BaseColor.BLACK);
					Font fontClassBold1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
					fontClassBold1.setColor(BaseColor.BLACK);
					Font fontClassBoldLine1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
					fontClassBoldLine1.setColor(BaseColor.BLACK);
//					fontClassBoldLine1.setStyle(Font.UNDERLINE);
					Font fontClass2 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLDITALIC);
					fontClass2.setColor(BaseColor.BLACK);
					Font fontClass3 = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
					fontClass3.setColor(BaseColor.BLACK);
					Font fontClass4 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
					fontClass4.setColor(BaseColor.BLACK);
					Font fontClass5 = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLDITALIC);
					fontClass5.setColor(BaseColor.BLACK);
					Font fontClass6 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD);
					fontClass6.setColor(BaseColor.BLACK);
					Font fontClass7 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL);
					fontClass7.setColor(BaseColor.BLACK);
					Font fontClass18 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.NORMAL);
					fontClass18.setColor(BaseColor.BLACK);
					Font fontClassBold18 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
					fontClassBold18.setColor(BaseColor.BLACK);
						
				    for(int i=0;i<recLCList.size(); i++){
				    	logger.info(recLCList.get(i));
				    	StringTokenizer st1 = new StringTokenizer(recLCList.get(i), "|");
				    	List<String> LCDetaillist = new ArrayList();
				    	while(st1.hasMoreTokens()){
				    		LCDetaillist.add(st1.nextToken());
				    	}
				    	String genderDb = LCDetaillist.get(0);
				    	
				    	if(genderDb.equalsIgnoreCase("MALE")){
				    		Mst_Miss = kumar;
				    		he_she = "He";
				    		his_her = "his";
				    	}
				    	else {
				    		Mst_Miss = kumari;
				    		he_she = "She";
				    		his_her = "her";
				    	}
						String nameDb = LCDetaillist.get(1);
						String currentStdDb = LCDetaillist.get(2);
						String bonafide_header_2		= "";
					    String bonafide_header_3		= "";
						try {
							bonafide_header_2 = bundle.getString("BONAFIDE_HEADER_2_"+sessionData.getAppType()+"_"+currentStdDb+"_"+sessionData.getSectionName());
					        bonafide_header_3 = bundle.getString("BONAFIDE_HEADER_3_"+sessionData.getAppType()+"_"+currentStdDb+"_"+sessionData.getSectionName());
						}
						catch(MissingResourceException me) {
							logger.warn(me);
						}
						String currentDivDb = LCDetaillist.get(3);
						String academicYear = LCDetaillist.get(4);
						String dobDb = LCDetaillist.get(5);
						String dobWordsDb = LCDetaillist.get(6);
						String religionDb = LCDetaillist.get(7);
						if(religionDb.equalsIgnoreCase("-")){
							religionDb = "";
						}
						String castDb = LCDetaillist.get(8);
						if(castDb.equalsIgnoreCase("-")){
							castDb = "";
						}
						String birthPlaceDb = LCDetaillist.get(9);
						birthPlaceDb = commonLc.revertCommaApostrophy(birthPlaceDb);
						String talukaDb = LCDetaillist.get(13);
						talukaDb = commonLc.revertCommaApostrophy(talukaDb);
						String districtDb = LCDetaillist.get(14);
						districtDb = commonLc.revertCommaApostrophy(districtDb);
						String stateDb = LCDetaillist.get(15);
						stateDb = commonLc.revertCommaApostrophy(stateDb);
						String countryDb = LCDetaillist.get(16);
						countryDb = commonLc.revertCommaApostrophy(countryDb);
						
						String grDb = LCDetaillist.get(10);
						String adhaarDb = LCDetaillist.get(20);
						String motherNameDb = LCDetaillist.get(21);
						if(!isMotherName) {
							nameDb = nameDb +", "+motherNameDb;
						}
						String suid_db = LCDetaillist.get(22);
						String todayDate = "";
						
						Date date = new Date();
						SimpleDateFormat today = new SimpleDateFormat("dd/MM/yyyy");
						todayDate = today.format(date);
						
						String currentAcademicYear = commonLc.getAcademicYear(todayDate.toString());
						if(currentAcademicYear.equalsIgnoreCase(academicYear)){
							is_was = "is";
						}
						
//						if(isMotherName){
//							////just to add image in pdf as header
//							Image img = Image.getInstance(img_path+pdf_header_img_path);
//							img.scalePercent(image_pdf_scalepercent);
////							img.scaleToFit(550f, 150f);
//							img.setAbsolutePosition(image_pdf_pos_x, image_pdf_pos_y);
////							img.scaleAbsolute(550, 150);
//						    document.add(img);
////						    paraSep.setSpacingBefore(05);
//						}
						
						Chunk chunkHeader1 = new Chunk("");
//						Font font1 = new Font(Font.TIMES_ROMAN);
//						font1.setStyle(Font.BOLD);
//						font1.setSize(12);
//						font1.setColor(Color.BLACK);
						chunkHeader1.setFont(fontClass1);
						Paragraph paragraph1 = new Paragraph();
						paragraph1.add(chunkHeader1);
						paragraph1.setAlignment(Element.ALIGN_CENTER);
						document.add(paragraph1);
						
						Chunk chunkHeader = new Chunk(bonafide_header_0);
//						Font font = new Font(Font.TIMES_ROMAN);
//						font.setStyle(Font.BOLD);
//						font.setSize(12);
//						font.setColor(Color.BLACK);
						chunkHeader.setFont(fontClassBold1);
						Paragraph paragraph = new Paragraph();
						paragraph.setSpacingBefore(-20);
						paragraph.add(chunkHeader);
						paragraph.setAlignment(Element.ALIGN_CENTER);
						
						Chunk chunkSchoolName = new Chunk(bonafide_header);
//						Font fontSchoolName = new Font(Font.TIMES_ROMAN);
//						fontSchoolName.setStyle(Font.BOLD);
//						fontSchoolName.setSize(18);
//						fontSchoolName.setColor(Color.BLACK);
						chunkSchoolName.setFont(fontClassBold18);
						Paragraph paraSchoolName = new Paragraph();
						paraSchoolName.setSpacingBefore(2);
						paraSchoolName.add(chunkSchoolName);
						paraSchoolName.setAlignment(Element.ALIGN_CENTER);
						
						Chunk chunkBonHeader2 = new Chunk(bonafide_header_2);
						chunkBonHeader2.setFont(fontClass1);
						Paragraph paragraphBon2 = new Paragraph();
						paragraphBon2.setSpacingBefore(-3);
						paragraphBon2.add(chunkBonHeader2);
						paragraphBon2.setAlignment(Element.ALIGN_CENTER);
						
						Chunk chunkBonHeader3 = new Chunk(bonafide_header_3);
						chunkBonHeader3.setFont(fontClass1);
						Paragraph paragraphBona3 = new Paragraph();
						paragraphBona3.setSpacingBefore(-3);
						paragraphBona3.add(chunkBonHeader3);
						paragraphBona3.setAlignment(Element.ALIGN_CENTER);
						
						if(pdf_header_bonafide_logo_flag){
							////just to add image in pdf as header
							Image img = Image.getInstance(img_path+pdf_logo_img_path);
							img.scalePercent(image_pdf_logo_scalepercent);
//							img.scaleToFit(550f, 150f);
							img.setAbsolutePosition(image_pdf_logo_pos_x, image_pdf_logo_pos_y);
//							img.scaleAbsolute(550, 150);
						    document.add(img);
//						    paraSep.setSpacingBefore(05);
						}
						
						if(pdf_header_bonafide_image_flag.equalsIgnoreCase("true")){
							////just to add image in pdf as header
							Image img = Image.getInstance(img_path+pdf_header_img_path);
							img.scalePercent(image_pdf_scalepercent);
//							img.scaleToFit(550f, 150f);
							img.setAbsolutePosition(image_pdf_pos_x, image_pdf_pos_y);
//							img.scaleAbsolute(550, 150);
						    document.add(img);
//						    paraSep.setSpacingBefore(05);
						}
						else {
							document.add(paragraph);
							document.add(paraSchoolName);
							document.add(paragraphBon2);
							document.add(paragraphBona3);
						}
						
							
						Chunk chunkDate = new Chunk("Date :- "+todayDate);
//						Font fontDate = new Font(Font.TIMES_ROMAN);
//						fontDate.setStyle(Font.BOLD);
//						fontDate.setSize(12);
//						fontDate.setColor(Color.BLACK);
						chunkDate.setFont(fontClassBold1);
						Paragraph paragraphDate = new Paragraph();
						
						if(pdf_header_bonafide_image_flag.equalsIgnoreCase("true")){
							paragraphDate.setSpacingBefore(50);
						}
						else if(bonafide_header_3.equalsIgnoreCase("")) {
							paragraphDate.setSpacingBefore(15);
						}
						else {
							paragraphDate.setSpacingBefore(0);
						}
						
						paragraphDate.add(chunkDate);
						paragraphDate.setAlignment(Element.ALIGN_RIGHT);
						document.add(paragraphDate);
						
						Chunk chunkSubject = new Chunk("Bonafide Certificate");
//						Font fontSubject = new Font(Font.TIMES_ROMAN);
//						fontSubject.setStyle(Font.BOLD);
//						fontSubject.setSize(18);
//						fontSubject.setColor(Color.BLACK);
						chunkSubject.setFont(fontClassBold18);
						Paragraph paragraphSubject = new Paragraph();
						if(bonafide_header_3.equalsIgnoreCase("")) {
							paragraphSubject.setSpacingBefore(15);
							paragraphSubject.setSpacingAfter(15);
						}
						else {
							paragraphSubject.setSpacingBefore(10);
							paragraphSubject.setSpacingAfter(10);
						}
						paragraphSubject.add(chunkSubject);
						paragraphSubject.setAlignment(Element.ALIGN_CENTER);
						document.add(paragraphSubject);
						
						Chunk chunkMatter = new Chunk("          This is to certify that "+Mst_Miss+" ");
//						Font fontMatter = new Font(Font.TIMES_ROMAN);
//						fontMatter.setStyle(Font.NORMAL);
//						fontMatter.setSize(12);
//						fontMatter.setColor(Color.BLACK);
						chunkMatter.setFont(fontClass1);
//						Paragraph paraMatter = new Paragraph();
//						paraMatter.setSpacingBefore(43);
//						paraMatter.add(chunkMatter);
//						paraMatter.setAlignment(Element.ALIGN_LEFT);
//						document.add(chunkMatter);
						
						Chunk chunkName = new Chunk(nameDb);
//						Font fontName = new Font(Font.TIMES_ROMAN);
//						fontName.setStyle(Font.BOLD);
//						fontName.setStyle(Font.UNDERLINE);
//						fontName.setSize(12);
//						fontName.setColor(Color.BLACK);
						chunkName.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
						chunkName.setFont(fontClassBoldLine1);
//						Paragraph paraMatter = new Paragraph();
//						paraMatter.setSpacingBefore(43);
//						paraMatter.add(chunkName);
//						paraMatter.setAlignment(Element.ALIGN_LEFT);
//						document.add(chunkName);
						
						Chunk chunkMatterMotherName = new Chunk(" Mother's Name ");
						chunkMatterMotherName.setFont(fontClass1);
						
						Chunk chunkMotherName = new Chunk(motherNameDb);
						chunkMotherName.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
						chunkMotherName.setFont(fontClassBoldLine1);
						
						Chunk chunkMatter3 = new Chunk(", studying in Std. ");
//						Font fontMatter3 = new Font(Font.TIMES_ROMAN);
//						fontMatter3.setStyle(Font.NORMAL);
//						fontMatter3.setSize(12);
//						fontMatter3.setColor(Color.BLACK);
						chunkMatter3.setFont(fontClass1);
//						Paragraph paraMatter = new Paragraph();
//						paraMatter.setSpacingBefore(43);
//						paraMatter.add(chunkMatter3);
//						paraMatter.setAlignment(Element.ALIGN_LEFT);
//						document.add(chunkMatter3);
						
						Chunk chunkStd = new Chunk(currentStdDb);
//						Font fontStd = new Font(Font.TIMES_ROMAN);
//						fontStd.setStyle(Font.BOLD);
//						fontStd.setStyle(Font.UNDERLINE);
//						fontStd.setSize(12);
//						fontStd.setColor(Color.BLACK);
//						chunkStd.setFont(fontClass1);
						chunkStd.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
						chunkStd.setFont(fontClassBoldLine1);
//						Paragraph paraMatter = new Paragraph();
//						paraMatter.setSpacingBefore(43);
//						paraMatter.add(chunkStd);
//						paraMatter.setAlignment(Element.ALIGN_LEFT);
//						document.add(chunkStd);
						
						Chunk chunkMatter4 = new Chunk(" Div. ");
//						Font fontMatter4 = new Font(Font.TIMES_ROMAN);
//						fontMatter4.setStyle(Font.NORMAL);
//						fontMatter4.setSize(12);
//						fontMatter4.setColor(Color.BLACK);
						chunkMatter4.setFont(fontClass1);
//						Paragraph paraMatter = new Paragraph();
//						paraMatter.setSpacingBefore(43);
//						paraMatter.add(chunkMatter4);
//						paraMatter.setAlignment(Element.ALIGN_LEFT);
//						document.add(chunkMatter4);
						
						Chunk chunkDiv = new Chunk(currentDivDb);
//						Font fontDiv = new Font(Font.TIMES_ROMAN);
//						fontDiv.setStyle(Font.BOLD);
//						fontDiv.setStyle(Font.UNDERLINE);
//						fontDiv.setSize(12);
//						fontDiv.setColor(Color.BLACK);
//						chunkDiv.setFont(fontClass1);
						chunkDiv.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
						chunkDiv.setFont(fontClassBoldLine1);
//						Paragraph paraMatter = new Paragraph();
//						paraMatter.setSpacingBefore(43);
//						paraMatter.add(chunkDiv);
//						paraMatter.setAlignment(Element.ALIGN_LEFT);
//						document.add(chunkDiv);
						
						Chunk chunkMatter5 = new Chunk(" in the academic year ");
//						Font fontMatter5 = new Font(Font.TIMES_ROMAN);
//						fontMatter5.setStyle(Font.NORMAL);
//						fontMatter5.setSize(12);
//						fontMatter5.setColor(Color.BLACK);
						chunkMatter5.setFont(fontClass1);
//						Paragraph paraMatter = new Paragraph();
//						paraMatter.setSpacingBefore(43);
//						paraMatter.add(chunkMatter5);
//						paraMatter.setAlignment(Element.ALIGN_LEFT);
//						document.add(chunkMatter5);
						
						Chunk chunkAcademic = new Chunk(academicYear);
//						Font fontAcademic = new Font(Font.TIMES_ROMAN);
//						fontAcademic.setStyle(Font.BOLD);
//						fontAcademic.setStyle(Font.UNDERLINE);
//						fontAcademic.setSize(12);
//						fontAcademic.setColor(Color.BLACK);
//						chunkAcademic.setFont(fontClass1);
						chunkAcademic.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
						chunkAcademic.setFont(fontClassBoldLine1);
//						Paragraph paraMatter = new Paragraph();
//						paraMatter.setSpacingBefore(43);
//						paraMatter.add(chunkAcademic);
//						paraMatter.setAlignment(Element.ALIGN_LEFT);
//						document.add(chunkAcademic);
						String school_college = "school";
						if(sessionData1.getAppName().contains("College")){
							school_college = "college";
						}
						Chunk chunkMatter6 = new Chunk(". As per our "+school_college+" General Register "+his_her
								+ " Date of Birth is recorded as ");
//						Font fontMatter6 = new Font(Font.TIMES_ROMAN);
//						fontMatter6.setStyle(Font.NORMAL);
//						fontMatter6.setSize(12);
//						fontMatter6.setColor(Color.BLACK);
						chunkMatter6.setFont(fontClass1);
//						Paragraph paraMatter = new Paragraph();
//						paraMatter.setSpacingBefore(43);
//						paraMatter.add(chunkMatter6);
//						paraMatter.setAlignment(Element.ALIGN_LEFT);
//						document.add(chunkMatter6);
						
						dobDb = dobDb.replace("-", "/");
						Chunk chunkDob = new Chunk(dobDb+ " ("+commonLc.FirstWordCap(dobWordsDb)+").");
//						Font fontDob = new Font(Font.TIMES_ROMAN);
//						fontDob.setStyle(Font.BOLD);
//						fontDob.setStyle(Font.UNDERLINE);
//						fontDob.setSize(12);
//						fontDob.setColor(Color.BLACK);
//						chunkDob.setFont(fontClass1);
						chunkDob.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
						chunkDob.setFont(fontClassBoldLine1);
						
						Chunk chunkMatter1 = new Chunk(" "+he_she+" "+is_was+" regular in attendance.");
//						Font fontMatter1 = new Font(Font.TIMES_ROMAN);
//						fontMatter1.setStyle(Font.NORMAL);
//						fontMatter1.setSize(12);
//						fontMatter1.setColor(Color.BLACK);
						chunkMatter1.setFont(fontClass1);
//						Paragraph paraMatter1 = new Paragraph();
//						paraMatter1.setSpacingBefore(5);
//						paraMatter1.add(chunkMatter1);
//						paraMatter1.setAlignment(Element.ALIGN_LEFT);
//						document.add(chunkMatter1);

						Phrase phrase = new Phrase();
						phrase.add(chunkMatter);
						phrase.add(chunkName);
						if(isMotherName) {
							phrase.add(chunkMatterMotherName);
							phrase.add(chunkMotherName);
						}
						phrase.add(chunkMatter3);
						phrase.add(chunkStd);
						phrase.add(chunkMatter4);
						phrase.add(chunkDiv);
						phrase.add(chunkMatter5);
						phrase.add(chunkAcademic);
						phrase.add(chunkMatter6);
						phrase.add(chunkDob);
//						if(sessionData1.getSchoolName().equalsIgnoreCase("PR")){
							phrase.add(chunkMatter1);
//						}
							
						Paragraph paraMatter1 = new Paragraph();
						paraMatter1.setSpacingBefore(5);
						paraMatter1.add(phrase);
						paraMatter1.setAlignment(Element.ALIGN_JUSTIFIED);
						document.add(paraMatter1);
						
						ColumnText ct = new ColumnText(null);
						ct.addElement(paraMatter1);
						float phraseWidth = ct.getWidth(phrase);
						boolean isPhraseWidthGreater = false;
						if(phraseWidth > 1515) {
							isPhraseWidthGreater = true;
						}
						
						Chunk chunkMatter2 = new Chunk("          To the best of my knowledge "+he_she.toLowerCase()+" bears a good moral character.");
//						Font fontMatter2 = new Font(Font.TIMES_ROMAN);
//						fontMatter2.setStyle(Font.NORMAL);
//						fontMatter2.setSize(12);
//						fontMatter2.setColor(Color.BLACK);
						chunkMatter2.setFont(fontClass1);
						Paragraph paraMatter2 = new Paragraph();
						paraMatter2.setSpacingBefore(10);
						paraMatter2.add(chunkMatter2);
						paraMatter2.setAlignment(Element.ALIGN_LEFT);
						document.add(paraMatter2);
						
						Chunk chunkRC = new Chunk("Religion & Caste : "+commonLc.FirstWordCap(religionDb) + " " + commonLc.FirstWordCap(castDb));
//						Font fontRC = new Font(Font.TIMES_ROMAN);
//						fontRC.setStyle(Font.NORMAL);
//						fontRC.setSize(12);
//						fontRC.setColor(Color.BLACK);
						chunkRC.setFont(fontClass1);
						Paragraph paraRC = new Paragraph();			
						paraRC.setSpacingBefore(15);
						paraRC.add(chunkRC);
						paraRC.setAlignment(Element.ALIGN_LEFT);
						document.add(paraRC);
						
						String fullplaceOfBirth = "";
						String placeOfBirth1 = "";
						String placeOfBirth2 = "";
						String placeOfBirth3 = "";
						fullplaceOfBirth = commonLc.FirstWordCap(birthPlaceDb) + ",";
						if(fullplaceOfBirth.equalsIgnoreCase("-,") || fullplaceOfBirth.equalsIgnoreCase("") || fullplaceOfBirth.equalsIgnoreCase("null")){
							fullplaceOfBirth = "-----,";
						}
						if(talukaDb.equalsIgnoreCase("-") || talukaDb.equalsIgnoreCase("") || talukaDb.equalsIgnoreCase("null")){
							talukaDb = "-----,";
						}
						else{
							talukaDb = "*" + talukaDb +",";
						}
						fullplaceOfBirth = fullplaceOfBirth + " Taluka:"+commonLc.FirstWordCap(talukaDb);
						
						if(districtDb.equalsIgnoreCase("-") || districtDb.equalsIgnoreCase("") || districtDb.equalsIgnoreCase("null")){
							districtDb = "-----,";
						}
						else{
							districtDb = "*" + districtDb + ",";
						}
						fullplaceOfBirth = fullplaceOfBirth + " Dist:"+commonLc.FirstWordCap(districtDb);
						
						if(stateDb.equalsIgnoreCase("-") || stateDb.equalsIgnoreCase("") || stateDb.equalsIgnoreCase("null")){
							stateDb = "-----,";
						}
						else{
							stateDb = "*" + stateDb + ",";
						}
						
						if(countryDb.trim().equalsIgnoreCase("-") || countryDb.trim().trim().equalsIgnoreCase("") || countryDb.trim().equalsIgnoreCase("null")){
							countryDb = "-----";
						}
						
						placeOfBirth1 = fullplaceOfBirth;
						placeOfBirth2 = "State:"+commonLc.FirstWordCap(stateDb)+" Country:"+commonLc.FirstWordCap(countryDb);
						placeOfBirth1 = placeOfBirth1.replace("*","");
						placeOfBirth2 = placeOfBirth2.replace("*","");
						placeOfBirth3 = placeOfBirth3.replace("*","");
						
						Chunk chunkBirthplace = new Chunk("Place of Birth : "+commonLc.FirstWordCap(placeOfBirth1));
//						Font fontBirthPlace = new Font(Font.TIMES_ROMAN);
//						fontBirthPlace.setStyle(Font.NORMAL);
//						fontBirthPlace.setSize(12);
//						fontBirthPlace.setColor(Color.BLACK);
						chunkBirthplace.setFont(fontClass1);
						Paragraph paraBirthPlace = new Paragraph();
						paraBirthPlace.setSpacingBefore(10);
						paraBirthPlace.add(chunkBirthplace);
						paraBirthPlace.setAlignment(Element.ALIGN_LEFT);
						document.add(paraBirthPlace);
						
						if(!placeOfBirth2.equalsIgnoreCase("-") && !placeOfBirth2.equalsIgnoreCase("") && !placeOfBirth2.equalsIgnoreCase("null")){
							Chunk chunkTitleState = new Chunk(" ");
							chunkTitleState.setFont(fontClass1);
							
							Paragraph paraTitleState = new Paragraph();	
							paraTitleState.setSpacingBefore(0);
							paraTitleState.add(chunkTitleState);
							paraTitleState.setAlignment(Element.ALIGN_LEFT);
							document.add(paraTitleState);
							
							Chunk chunkState = new Chunk("                         "+  placeOfBirth2);
							chunkState.setFont(fontClass1);
							
							Paragraph paraState = new Paragraph();	
							paraState.setSpacingBefore(-16);
							paraState.add(chunkState);
							paraState.setAlignment(Element.ALIGN_LEFT);
							document.add(paraState);
						}
						
						if(!placeOfBirth3.equalsIgnoreCase("-") && !placeOfBirth3.equalsIgnoreCase("") && !placeOfBirth3.equalsIgnoreCase("null")){
							Chunk chunkTitleState = new Chunk(" ");
							chunkTitleState.setFont(fontClass1);
							
							Paragraph paraTitleState = new Paragraph();	
							paraTitleState.setSpacingBefore(0);
							paraTitleState.add(chunkTitleState);
							paraTitleState.setAlignment(Element.ALIGN_LEFT);
							document.add(paraTitleState);
							
							Chunk chunkState = new Chunk("                                                                    "+  placeOfBirth3);
							chunkState.setFont(fontClass1);
							
							Paragraph paraState = new Paragraph();	
							paraState.setSpacingBefore(-16);
							paraState.add(chunkState);
							paraState.setAlignment(Element.ALIGN_LEFT);
							document.add(paraState);
						}
						
						Chunk chunkGrNo = new Chunk("G. R. Number : "+grDb);
//						Font fontGrNo = new Font(Font.TIMES_ROMAN);
//						fontGrNo.setStyle(Font.NORMAL);
//						fontGrNo.setSize(12);
//						fontGrNo.setColor(Color.BLACK);
						chunkGrNo.setFont(fontClass1);
						Paragraph paraGrNo = new Paragraph();
						paraGrNo.setSpacingBefore(10);
						paraGrNo.add(chunkGrNo);
						paraGrNo.setAlignment(Element.ALIGN_LEFT);
						document.add(paraGrNo);
						
						Chunk chunkAdhaarNo = new Chunk("Adhaar Card : "+adhaarDb);
						if(showAdhaar) {
							chunkAdhaarNo.setFont(fontClass1);
							Paragraph paraAdhaarNo = new Paragraph();
							paraAdhaarNo.setSpacingBefore(0);
							paraAdhaarNo.add(chunkAdhaarNo);
							paraAdhaarNo.setAlignment(Element.ALIGN_LEFT);
							document.add(paraAdhaarNo);
						}
						
						Chunk chunkSuid = new Chunk("Student Udise ID : "+suid_db);
						if(showSUID) {
							chunkSuid.setFont(fontClass1);
							Paragraph paraSuid = new Paragraph();
							paraSuid.setSpacingBefore(0);
							paraSuid.add(chunkSuid);
							paraSuid.setAlignment(Element.ALIGN_LEFT);
							document.add(paraSuid);
						}
						
						if(isFooterRequired) {
							Chunk chunkPrincipal = new Chunk("             "+footer+"                  ");
							chunkPrincipal.setFont(fontClass1);
							Paragraph paraName = new Paragraph();
							if(showAdhaar && isSingleCopy){
								paraName.setSpacingBefore(40);
							}
							else if(showAdhaar){
								paraName.setSpacingBefore(25);
							}
							else if(isSingleCopy){
								paraName.setSpacingBefore(40);
							}
							else{
								paraName.setSpacingBefore(10);
							}
							paraName.setSpacingAfter(20);
							paraName.add(chunkPrincipal);
							paraName.setAlignment(Element.ALIGN_RIGHT);
							document.add(paraName);
						}
						
						//===========================Office copy=========================//
						
						if(!isSingleCopy){
							String officeSeparator = "-----------------------------------------------------------------------------------------------------------------";
							Chunk chunkHeader2 = new Chunk(officeSeparator);
							chunkHeader2.setFont(fontClass1);
							Paragraph paragraph2 = new Paragraph();
							paragraph2.setSpacingAfter(10);
							paragraph2.setSpacingBefore(30);
							
							int spaceBeforeSeparator = 10;
							if(isPhraseWidthGreater) {
								spaceBeforeSeparator = spaceBeforeSeparator + 10;
							}
							if(pdf_header_bonafide_image_flag.equalsIgnoreCase("true")){
								paragraph2.setSpacingBefore(spaceBeforeSeparator);
							}
							paragraph2.add(chunkHeader2);
							paragraph2.setAlignment(Element.ALIGN_CENTER);
//							FixText(officeSeparator, 75, 430, pdfWriter, 12);
							document.add(paragraph2);

							Chunk chunkHeaderOffice = new Chunk(bonafide_header_0);
							chunkHeaderOffice.setFont(fontClassBold1);
							Paragraph paragraphOffice = new Paragraph();
							paragraphOffice.setSpacingBefore(0);
							paragraphOffice.add(chunkHeaderOffice);
							paragraphOffice.setAlignment(Element.ALIGN_CENTER);
							
							Chunk chunkSchoolNameOffice = new Chunk(bonafide_header);
							chunkSchoolNameOffice.setFont(fontClassBold18);
							Paragraph paraSchoolNameOffice = new Paragraph();
							paraSchoolNameOffice.setSpacingBefore(10);
							paraSchoolNameOffice.add(chunkSchoolNameOffice);
							paraSchoolNameOffice.setAlignment(Element.ALIGN_CENTER);
							
							Chunk chunkBonHeader2Office = new Chunk(bonafide_header_2);
							chunkBonHeader2Office.setFont(fontClass1);
							Paragraph paragraphBon2Office = new Paragraph();
							paragraphBon2Office.setSpacingBefore(-3);
							paragraphBon2Office.add(chunkBonHeader2Office);
							paragraphBon2Office.setAlignment(Element.ALIGN_CENTER);
							
							Chunk chunkBonHeader3Office = new Chunk(bonafide_header_3);
							chunkBonHeader3Office.setFont(fontClass1);
							Paragraph paragraphBona3Office = new Paragraph();
							paragraphBona3Office.setSpacingBefore(-3);
							paragraphBona3Office.add(chunkBonHeader3Office);
							paragraphBona3Office.setAlignment(Element.ALIGN_CENTER);
							
							int y_adj = 390;
							if(showAdhaar) {
								y_adj = y_adj + 15;
							}
							if(showSUID) {
								y_adj = y_adj + 15;
							}
							
							if(isPhraseWidthGreater) {
								y_adj = y_adj + 20;
							}
							
							if(pdf_header_bonafide_logo_flag){
								////just to add image in pdf as logo
								Image img3 = Image.getInstance(img_path+pdf_logo_img_path);
								img3.scalePercent(image_pdf_logo_scalepercent);
//								img3.setAbsolutePosition(image_pdf_logo_pos_x, image_pdf_logo_pos_y - y_adj - 15);
							    document.add(img3);
							}
							
							if(pdf_header_bonafide_image_flag.equalsIgnoreCase("true")){
								////just to add image in pdf as header
								Image img2 = Image.getInstance(img_path+pdf_header_img_path);
								img2.scalePercent(image_pdf_scalepercent);
								
								Paragraph imgPara = new Paragraph();
								imgPara.add(img2);
								imgPara.setSpacingBefore(-10);
//								img2.setAbsolutePosition(image_pdf_pos_x, image_pdf_office_pos_y - y_adj);
							    document.add(imgPara);
							}
							else {
								document.add(paragraphOffice);
								document.add(paraSchoolNameOffice);
								document.add(paragraphBon2Office);
								document.add(paragraphBona3Office);
							}
							
							Chunk chunkDateOffice = new Chunk("Date :- "+todayDate);
							chunkDateOffice.setFont(fontClassBold1);
							Paragraph paragraphDateOffice = new Paragraph();
							if(pdf_header_bonafide_image_flag.equalsIgnoreCase("true")){
								paragraphDateOffice.setSpacingBefore(-25);
							}
							else if(bonafide_header_3.equalsIgnoreCase("")) {
//								paragraphDateOffice.setSpacingBefore(15);
							}
							else {
//								paragraphDateOffice.setSpacingBefore(0);
							}
							paragraphDateOffice.add(chunkDateOffice);
							paragraphDateOffice.setAlignment(Element.ALIGN_RIGHT);
							document.add(paragraphDateOffice);
							
							Chunk chunkSubjectOffice = new Chunk("Bonafide Certificate");
//							Font fontSubjectOffice = new Font(Font.TIMES_ROMAN);
//							fontSubjectOffice.setStyle(Font.BOLD);
//							fontSubjectOffice.setSize(18);
//							fontSubjectOffice.setColor(Color.BLACK);
							chunkSubjectOffice.setFont(fontClassBold18);
							Paragraph paragraphSubjectOffice = new Paragraph();
							if(bonafide_header_3.equalsIgnoreCase("")) {
								paragraphSubjectOffice.setSpacingBefore(15);
								paragraphSubjectOffice.setSpacingAfter(15);
							}
							else {
								paragraphSubjectOffice.setSpacingBefore(10);
								paragraphSubjectOffice.setSpacingAfter(10);
							}
							paragraphSubjectOffice.add(chunkSubjectOffice);
							paragraphSubjectOffice.setAlignment(Element.ALIGN_CENTER);
							document.add(paragraphSubjectOffice);
							
							Paragraph paraMatter1Office = new Paragraph();
							paraMatter1Office.setSpacingBefore(5);
							paraMatter1Office.add(phrase);
							paraMatter1Office.setAlignment(Element.ALIGN_JUSTIFIED);
							document.add(paraMatter1Office);
							
							Chunk chunkMatter2Office = new Chunk("          To the best of my knowledge "+he_she.toLowerCase()+" bears a good moral character.");
//							Font fontMatter2Office = new Font(Font.TIMES_ROMAN);
//							fontMatter2Office.setStyle(Font.NORMAL);
//							fontMatter2Office.setSize(12);
//							fontMatter2Office.setColor(Color.BLACK);
							chunkMatter2Office.setFont(fontClass1);
							Paragraph paraMatter2Office = new Paragraph();
							paraMatter2Office.setSpacingBefore(10);
							paraMatter2Office.add(chunkMatter2Office);
							paraMatter2Office.setAlignment(Element.ALIGN_LEFT);
							document.add(paraMatter2Office);
							
							Chunk chunkRCOffice = new Chunk("Religion & Caste : "+commonLc.FirstWordCap(religionDb) + " " + commonLc.FirstWordCap(castDb));
//							Font fontRCOffice = new Font(Font.TIMES_ROMAN);
//							fontRCOffice.setStyle(Font.NORMAL);
//							fontRCOffice.setSize(12);
//							fontRCOffice.setColor(Color.BLACK);
							chunkRCOffice.setFont(fontClass1);
							Paragraph paraRCOffice = new Paragraph();			
							paraRCOffice.setSpacingBefore(15);
							paraRCOffice.add(chunkRCOffice);
							paraRCOffice.setAlignment(Element.ALIGN_LEFT);
							document.add(paraRCOffice);
							
							Chunk chunkBirthplaceOffice = new Chunk("Place of Birth : "+placeOfBirth1);
//							Font fontBirthPlaceOffice = new Font(Font.TIMES_ROMAN);
//							fontBirthPlaceOffice.setStyle(Font.NORMAL);
//							fontBirthPlaceOffice.setSize(12);
//							fontBirthPlaceOffice.setColor(Color.BLACK);
							chunkBirthplaceOffice.setFont(fontClass1);
							Paragraph paraBirthPlaceOffice = new Paragraph();
							paraBirthPlaceOffice.setSpacingBefore(10);
							paraBirthPlaceOffice.add(chunkBirthplaceOffice);
							paraBirthPlaceOffice.setAlignment(Element.ALIGN_LEFT);
							document.add(paraBirthPlaceOffice);
							
							if(!placeOfBirth2.equalsIgnoreCase("-") && !placeOfBirth2.equalsIgnoreCase("") && !placeOfBirth2.equalsIgnoreCase("null")){
								Chunk chunkTitleStateOffice = new Chunk(" ");
								chunkTitleStateOffice.setFont(fontClass1);
								
								Paragraph paraTitleStateOffice = new Paragraph();	
								paraTitleStateOffice.setSpacingBefore(0);
								paraTitleStateOffice.add(chunkTitleStateOffice);
								paraTitleStateOffice.setAlignment(Element.ALIGN_LEFT);
								document.add(paraTitleStateOffice);
								
								Chunk chunkStateOffice = new Chunk("                         "+  placeOfBirth2);
								chunkStateOffice.setFont(fontClass1);
								
								Paragraph paraStateOffice = new Paragraph();	
								paraStateOffice.setSpacingBefore(-16);
								paraStateOffice.add(chunkStateOffice);
								paraStateOffice.setAlignment(Element.ALIGN_LEFT);
								document.add(paraStateOffice);
							}
							
							if(!placeOfBirth3.equalsIgnoreCase("-") && !placeOfBirth3.equalsIgnoreCase("") && !placeOfBirth3.equalsIgnoreCase("null")){
								Chunk chunkTitleStateOffice = new Chunk(" ");
								chunkTitleStateOffice.setFont(fontClass1);
								
								Paragraph paraTitleStateOffice = new Paragraph();	
								paraTitleStateOffice.setSpacingBefore(0);
								paraTitleStateOffice.add(chunkTitleStateOffice);
								paraTitleStateOffice.setAlignment(Element.ALIGN_LEFT);
								document.add(paraTitleStateOffice);
								
								Chunk chunkStateOffice = new Chunk("                                                                    "+  placeOfBirth3);
								chunkStateOffice.setFont(fontClass1);
								
								Paragraph paraStateOffice = new Paragraph();	
								paraStateOffice.setSpacingBefore(-16);
								paraStateOffice.add(chunkStateOffice);
								paraStateOffice.setAlignment(Element.ALIGN_LEFT);
								document.add(paraStateOffice);
							}
							
							Chunk chunkGrNoOffice = new Chunk("G. R. Number : "+grDb);
//							Font fontGrNoOffice = new Font(Font.TIMES_ROMAN);
//							fontGrNoOffice.setStyle(Font.NORMAL);
//							fontGrNoOffice.setSize(12);
//							fontGrNoOffice.setColor(Color.BLACK);
							chunkGrNoOffice.setFont(fontClass1);
							Paragraph paraGrNoOffice = new Paragraph();
							paraGrNoOffice.setSpacingBefore(10);
							paraGrNoOffice.add(chunkGrNo);
							paraGrNoOffice.setAlignment(Element.ALIGN_LEFT);
							document.add(paraGrNoOffice);
							
							if(showAdhaar) {
								Paragraph paraAdhaarOffice = new Paragraph();
								paraAdhaarOffice.setSpacingBefore(0);
								paraAdhaarOffice.add(chunkAdhaarNo);
								paraAdhaarOffice.setAlignment(Element.ALIGN_LEFT);
								document.add(paraAdhaarOffice);
							}
							
							if(showSUID) {
								Paragraph paraSuidOffice = new Paragraph();
								paraSuidOffice.setSpacingBefore(0);
								paraSuidOffice.add(chunkSuid);
								paraSuidOffice.setAlignment(Element.ALIGN_LEFT);
								document.add(paraSuidOffice);
							}
							
							if(isFooterRequired) {
								Chunk chunkPrincipalOffice = new Chunk("             "+footer+"                  ");
								chunkPrincipalOffice.setFont(fontClass1);
								Paragraph paraNameOffice = new Paragraph();
								paraNameOffice.setSpacingBefore(10);
								paraNameOffice.add(chunkPrincipalOffice);
								paraNameOffice.setAlignment(Element.ALIGN_RIGHT);
								document.add(paraNameOffice);
							}
						}
						
						
						// start second page
						//if(recLCList.size()-i==1)
						document.newPage();
			    			
				    }
				    document.close();
					
				    logger.info("view_save => "+view_save);
				    ///////////////////
					if(view_save.equalsIgnoreCase("validate") || view_save.equalsIgnoreCase("view")){
						try {
							PdfReader reader;
							PdfStamper stamper;
							String fileNameView 	= "BonafideCertificate"+view_save+commonLc.timeInMillis()+".pdf";
							String fileAddressView = path+fileNameView;
							
							reader 		= new PdfReader(fileAddress);
							stamper 	= new PdfStamper(reader, new FileOutputStream(fileAddressView));
							
							stamper.close();
							
							logger.info("file "+commonLc.deleteFile(fileAddress)+" deleted");
							fileName		=  fileNameView;
							fileAddress		=  fileAddressView;
							
						} catch (IOException e) {
							fileOpenFlag = true;
							logger.error("view file Exception == "+e);
							commonLc.logException(e);
							JOptionPane.showMessageDialog(null, "Please close "+fileName+" and try again"); 
						}
					}
					
				} catch (FileNotFoundException e) {
					fileOpenFlag = true;
					logger.error("FileNotFound Exception == "+e);
					commonLc.logException(e);
					JOptionPane.showMessageDialog(null, "Please close "+fileName+" and try again"); 
				} catch (DocumentException e) {
					logger.error("last Exception == "+e);
					commonLc.logException(e);
				}
			}
		} catch (Exception e) {
			logger.error("fetch data Exception == "+e);
			commonLc.logException(e);
		}finally {
			findStudentDB.closeDatabase(sessionData);
		}
		
		// open pdf 
		if(!fileOpenFlag){
			try {
				if ((new File(fileAddress)).exists()) {
					if((System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
						Runtime.getRuntime().exec(new String[]{"/usr/bin/open", fileAddress});
					}
					else {
						Process process = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+fileAddress);
						process.waitFor();
					}
				} else {
					logger.info("File not found");
				}
				logger.info("Done");
			} catch (Exception e) {
				logger.error(":: -----Exception---- ::\n"+e);
				commonLc.logException(e);
			}
		}
	}
	
	private static void FixText(String text, int x, int y,PdfWriter writer,int size) {
	    try {
	        PdfContentByte cb = writer.getDirectContent();
	        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	        cb.saveState();
	        cb.beginText();
	        cb.moveText(x, y);
	        cb.setFontAndSize(bf, size);
	        cb.showText(text);
	        cb.endText();
	        cb.restoreState();
	    } catch (DocumentException | IOException e) {
	        e.printStackTrace();
	    }
	}
}
