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

import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
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
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;


public class StudyCertificatePDF {
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
	
    ResourceBundle bundle    		= ResourceBundle.getBundle("org.com.accesser.school"); 
    static Logger logger 			= Logger.getLogger(StudyCertificatePDF.class.getName());
    static SessionData sessionData  = new SessionData();
	
	public void studyCertificate(SessionData sessionData1, String view_save, List<String> passGrList,String sec, 
			String certType, String retUserName, String retUserRole, boolean isSingleCopy) throws DocumentException{
		System.gc();
		logger.info("Inside studyCertificate pdf constructor..............");
		isSingleCopy = true;//default single
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
        footer = bundle.getString("FOOTER_"+sessionData1.getAppType());
        String img_path = bundle.getString("IMAGE_PATH");
        String pdf_header_img_path = bundle.getString("IMAGE_PDF_BONAFIDE_HEADER_"+sessionData.getAppType());
		String pdf_header_bonafide_image_flag = bundle.getString("PDF_HEADER_STUDY_CERT_IMAGE_FLAG");
		float image_pdf_scalepercent = Float.parseFloat(bundle.getString("IMAGE_PDF_BONAFIDE_SCALEPERCENT")) - 9.0f;
		int image_pdf_pos_x = Integer.parseInt(bundle.getString("IMAGE_PDF_BONAFIDE_POS_X"));
		int image_pdf_pos_y = Integer.parseInt(bundle.getString("IMAGE_PDF_BONAFIDE_POS_Y"))+45;
    	
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
					fileName  = "StudyCertificatePrint"+commonLc.timeInMillis()+".pdf";
					fileAddress = path+fileName;
					File file = new File(fileAddress);
					FileOutputStream fileout = new FileOutputStream(file);
					
					Document document = new Document(com.itextpdf.text.PageSize.A4);
					PdfWriter.getInstance(document, fileout);
					document.addHeader("ABC", "XYZ");
//					document.addAuthor("MAAULI EDUCATION SOCIETY");
					document.addTitle("Study Certificate");
					document.open();
					
					Font fontClass1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
					fontClass1.setColor(BaseColor.BLACK);
					Font fontClassBold1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
					fontClassBold1.setColor(BaseColor.BLACK);
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
				    		Mst_Miss = "Kumar";
				    		he_she = "He";
				    		his_her = "his";
				    	}
				    	else {
				    		Mst_Miss = "Kumari";
				    		he_she = "She";
				    		his_her = "her";
				    	}
						String nameDb = LCDetaillist.get(1);
						String currentStdDb = LCDetaillist.get(2);
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
						String admStdDb = LCDetaillist.get(17);
						String admDivDb = LCDetaillist.get(18);
						String doaDb = LCDetaillist.get(19);
						
						String grDb = LCDetaillist.get(10);
						String originalLC = LCDetaillist.get(11);
						String originalLCDate = LCDetaillist.get(12);
						String todayDate = "";
						Date date = new Date();
						SimpleDateFormat today = new SimpleDateFormat("dd/MM/yyyy");
						todayDate = today.format(date);
						
						String currentAcademicYear = commonLc.getAcademicYear(todayDate.toString());
						
						String is_was = "is";
						if(originalLC != null && !originalLC.equalsIgnoreCase("null") && !originalLC.equalsIgnoreCase("-")){
							is_was = "was";
						}
						
						Chunk chunkHeader1 = new Chunk("");
						chunkHeader1.setFont(fontClassBold1);
						Paragraph paragraph1 = new Paragraph();
						paragraph1.add(chunkHeader1);
						paragraph1.setAlignment(Element.ALIGN_CENTER);
						
						Chunk chunkHeader = new Chunk(bonafide_header_0);
						chunkHeader.setFont(fontClassBold1);
						Paragraph paragraph = new Paragraph();
						paragraph.setSpacingBefore(-20);
						paragraph.add(chunkHeader);
						paragraph.setAlignment(Element.ALIGN_CENTER);
						
						Chunk chunkSchoolName = new Chunk(bonafide_header);
						chunkSchoolName.setFont(fontClassBold18);
						Paragraph paraSchoolName = new Paragraph();
						paraSchoolName.setSpacingBefore(10);
						paraSchoolName.add(chunkSchoolName);
						paraSchoolName.setAlignment(Element.ALIGN_CENTER);
						
						int spaceBeforeDate = 15;
						if(pdf_header_bonafide_image_flag.equalsIgnoreCase("true")){
							////just to add image in pdf as header
							spaceBeforeDate = 55;
							Image img = Image.getInstance(img_path+pdf_header_img_path);
							img.scalePercent(image_pdf_scalepercent);
							img.setAbsolutePosition(image_pdf_pos_x, image_pdf_pos_y);
						    document.add(img);
						}
						else {
							document.add(paragraph1);
							document.add(paragraph);
							document.add(paraSchoolName);
						}
						
						Chunk chunkDate = new Chunk("Date :- "+todayDate);
						chunkDate.setFont(fontClassBold1);
						Paragraph paragraphDate = new Paragraph();
						paragraphDate.setSpacingBefore(spaceBeforeDate);
						paragraphDate.add(chunkDate);
						paragraphDate.setAlignment(Element.ALIGN_RIGHT);
						document.add(paragraphDate);
						
						Chunk chunkSubject = new Chunk("Study Certificate");
						chunkSubject.setFont(fontClassBold18);
						Paragraph paragraphSubject = new Paragraph();
						paragraphSubject.setSpacingBefore(15);
						paragraphSubject.setSpacingAfter(15);
						paragraphSubject.add(chunkSubject);
						paragraphSubject.setAlignment(Element.ALIGN_CENTER);
						document.add(paragraphSubject);
						
						Chunk chunkMatter = new Chunk("          This is to certify that "+Mst_Miss+" ");
						chunkMatter.setFont(fontClass1);
//						Paragraph paraMatter = new Paragraph();
//						paraMatter.setSpacingBefore(43);
//						paraMatter.add(chunkMatter);
//						paraMatter.setAlignment(Element.ALIGN_LEFT);
//						document.add(chunkMatter);
						
						Chunk chunkName = new Chunk(nameDb);
						chunkName.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
						chunkName.setFont(fontClassBold1);
//						Paragraph paraMatter = new Paragraph();
//						paraMatter.setSpacingBefore(43);
//						paraMatter.add(chunkName);
//						paraMatter.setAlignment(Element.ALIGN_LEFT);
//						document.add(chunkName);
						
						Chunk chunkMatter3 = new Chunk(", "+is_was+" bonafide student of the school admitted in Std. ");
						chunkMatter3.setFont(fontClass1);
//						Paragraph paraMatter = new Paragraph();
//						paraMatter.setSpacingBefore(43);
//						paraMatter.add(chunkMatter3);
//						paraMatter.setAlignment(Element.ALIGN_LEFT);
//						document.add(chunkMatter3);
						
						Chunk chunkAdmittedStd = new Chunk(admStdDb);
						chunkAdmittedStd.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
						chunkAdmittedStd.setFont(fontClassBold1);
						
						Chunk chunkAdmittedDiv = new Chunk(" Div. ");
						chunkAdmittedDiv.setFont(fontClass1);
						
						Chunk chunkAdmDiv = new Chunk(admDivDb);
						chunkAdmDiv.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
						chunkAdmDiv.setFont(fontClassBold1);
						
						Chunk chunkMatter5 = new Chunk(" in the academic year ");
						chunkMatter5.setFont(fontClass1);
						
						Chunk chunkAdmAcademic = new Chunk(commonLc.getAcademicYear(doaDb));
						chunkAdmAcademic.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
						chunkAdmAcademic.setFont(fontClassBold1);

						String school_college = "school";
						if(sessionData1.getAppName().contains("College")){
							school_college = "college";
						}
						Chunk chunkMatter6 = new Chunk(". "+he_she+" is studying in Std. ");
						chunkMatter6.setFont(fontClass1);
						
						Chunk chunkStd = new Chunk(currentStdDb);
						chunkStd.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
						chunkStd.setFont(fontClassBold1);
						
						Chunk chunkMatter4 = new Chunk(" Div. ");
						chunkMatter4.setFont(fontClass1);
						
						Chunk chunkDiv = new Chunk(currentDivDb);
						chunkDiv.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
						chunkDiv.setFont(fontClassBold1);
						
						Chunk chunkMatter7 = new Chunk(" in the present academic year ");
						chunkMatter7.setFont(fontClass1);
						
						Chunk chunkMatter11 = new Chunk(commonLc.getAcademicYear(todayDate)+".");
						chunkMatter11.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
						chunkMatter11.setFont(fontClassBold1);
						
						Chunk chunkMatter9 = new Chunk(". "+he_she+"  left the school from Std. ");
						chunkMatter9.setFont(fontClass1);
						
						Chunk chunkMatter10 = new Chunk(" in the academic year "+commonLc.getAcademicYear(originalLCDate)+".");
						chunkMatter10.setFont(fontClass1);
						
						Phrase phrase = new Phrase();
						phrase.add(chunkMatter);
						phrase.add(chunkName);
						phrase.add(chunkMatter3);
						phrase.add(chunkAdmittedStd);
						phrase.add(chunkAdmittedDiv);
						phrase.add(chunkAdmDiv);
						phrase.add(chunkMatter5);
						phrase.add(chunkAdmAcademic);
						if(is_was.equalsIgnoreCase("is")){
							phrase.add(chunkMatter6);
							phrase.add(chunkStd);
							phrase.add(chunkMatter4);
							phrase.add(chunkDiv);
							phrase.add(chunkMatter7);
							phrase.add(chunkMatter11);
						}else{
							phrase.add(chunkMatter9);
							phrase.add(chunkStd);
							phrase.add(chunkMatter4);
							phrase.add(chunkDiv);
							phrase.add(chunkMatter10);
						}
//						phrase.add(chunkStd);
//						phrase.add(chunkMatter4);
//						phrase.add(chunkDiv);
//						phrase.add(chunkMatter7);
						
						Paragraph paraMatter1 = new Paragraph();
						paraMatter1.setSpacingBefore(5);
						paraMatter1.add(phrase);
						paraMatter1.setAlignment(Element.ALIGN_JUSTIFIED);
						document.add(paraMatter1);
						
						Chunk chunkMatter8 = new Chunk("As per our school General Register "+his_her+" Date of Birth is recorded as ");
						chunkMatter8.setFont(fontClass1);
						
						dobDb = dobDb.replace("-", "/");
						Chunk chunkDob = new Chunk(dobDb+ " ("+commonLc.FirstWordCap(dobWordsDb)+").");
						chunkDob.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
						chunkDob.setFont(fontClassBold1);
						
						Phrase phraseDob = new Phrase();
						phraseDob.add(chunkMatter8);
						phraseDob.add(chunkDob);
						Paragraph paraMatterDob = new Paragraph();
						paraMatterDob.setSpacingBefore(5);
						paraMatterDob.add(phraseDob);
						paraMatterDob.setAlignment(Element.ALIGN_JUSTIFIED);
						document.add(paraMatterDob);
						
						Chunk chunkMatter2 = new Chunk("          To the best of my knowledge "+he_she.toLowerCase()+" bears a good moral character.");
						chunkMatter2.setFont(fontClass1);
						Paragraph paraMatter2 = new Paragraph();
						paraMatter2.setSpacingBefore(10);
						paraMatter2.add(chunkMatter2);
						paraMatter2.setAlignment(Element.ALIGN_LEFT);
						document.add(paraMatter2);
						
						Chunk chunkRC = new Chunk("Religion & Caste : "+commonLc.FirstWordCap(religionDb) + " " + commonLc.FirstWordCap(castDb));
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
						chunkGrNo.setFont(fontClass1);
						Paragraph paraGrNo = new Paragraph();
						paraGrNo.setSpacingBefore(10);
						paraGrNo.add(chunkGrNo);
						paraGrNo.setAlignment(Element.ALIGN_LEFT);
						document.add(paraGrNo);
						
						Chunk chunkPrincipal = new Chunk("             "+footer+"                  ");
						chunkPrincipal.setFont(fontClass1);
						Paragraph paraName = new Paragraph();
						if(isSingleCopy){
							paraName.setSpacingBefore(40);
						}
						else{
							paraName.setSpacingBefore(10);
						}
						paraName.setSpacingAfter(20);
						paraName.add(chunkPrincipal);
						paraName.setAlignment(Element.ALIGN_RIGHT);
						document.add(paraName);
						
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
							String fileNameView 	= "StudyCertificate"+view_save+commonLc.timeInMillis()+".pdf";
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
			} catch (Exception e) {
				logger.error(":: -----Exception---- ::\n"+e);
				commonLc.logException(e);
			}
		}
	}
}
