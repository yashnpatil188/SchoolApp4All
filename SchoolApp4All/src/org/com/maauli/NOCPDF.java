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
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;


public class NOCPDF {
	DBValidate findStudentDB = new DBValidate();
	Common commonLc = new Common();
	static String fileName 				= "";
	static  boolean fileOpenFlag		= false;
	List<String> selectedNOCList 	= new ArrayList();
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
    static String noc_header			= "";
    static String noc_header_0			= "";
    static String footer				= "";
    static String nocGr					= "";
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
    static boolean isFooterRequired = false;
    
    ResourceBundle bundle    		= ResourceBundle.getBundle("org.com.accesser.school"); 
    static Logger logger 			= Logger.getLogger(NOCPDF.class.getName());
    static SessionData sessionData = new SessionData();
	
	public void NOCCertificate(SessionData sessionData1, String view_save, List<String> passGrList,String sec, String certType, String retUserName, String retUserRole) throws DocumentException{
		
		logger.info("Inside NOCCertificate pdf constructor..............");
		System.gc();
		fileAddress = "";
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
    	
        noc_header = bundle.getString("NOC_HEADER_"+sessionData.getAppType());
        noc_header_0 = bundle.getString("NOC_HEADER_0_"+sessionData.getAppType());
        footer = bundle.getString("FOOTER_"+sessionData1.getAppType());
        
        String img_path = bundle.getString("IMAGE_PATH");
        String pdf_header_img_path = bundle.getString("IMAGE_PDF_BONAFIDE_HEADER_"+sessionData.getAppType());
		String pdf_header_bonafide_image_flag = bundle.getString("PDF_HEADER_NOC_IMAGE_FLAG");
		float image_pdf_scalepercent = Float.parseFloat(bundle.getString("IMAGE_PDF_BONAFIDE_SCALEPERCENT")) - 9.0f;
		int image_pdf_pos_x = Integer.parseInt(bundle.getString("IMAGE_PDF_BONAFIDE_POS_X"));
		int image_pdf_pos_y = Integer.parseInt(bundle.getString("IMAGE_PDF_BONAFIDE_POS_Y"))+45;
		int image_pdf_office_pos_y = Integer.parseInt(bundle.getString("IMAGE_PDF_BONAFIDE_OFFICE_POS_Y"))+45;
		isFooterRequired = Boolean.parseBoolean(bundle.getString("FOOTER_REQUIRED"));
    	
		List<String> recLCList = new ArrayList();
		
		String separator ="----------------------------------------------------------------------------------------------------------------------------------";
		logger.info("view_save :: "+view_save);
		path = commonLc.createTodayFolder(commonLc.getDriveName() + bundle.getString("NOC_PDF_PATH_"+sessionData.getDBName()),true)+"/";
		
		int lcCountYear = 0;
		int dupLcCountYear = 0;
		int triLcCountYear = 0;
		List<String> nocWithoutLC = new ArrayList();
		int reply = 0;
		try {
				if(findStudentDB.connectDatabase(sessionData)){
					recLCList = findStudentDB.findStudentBonafide(sessionData, passGrList,section,"NOC");
//					nocWithoutLC = passGrList;
//					nocWithoutLC.removeAll(recLCList);
					if(recLCList.size() < 1){
						JOptionPane.showMessageDialog(null, "LC data not found for this student."); 
						passGrList.clear();
					}
					else if((passGrList.size() - recLCList.size()) > 0){
						reply = JOptionPane.showConfirmDialog(null, "LC data not found for "+(passGrList.size() - recLCList.size())+" students. Would You Like to create NOC for others?", "Confirm validate", JOptionPane.YES_NO_OPTION);
					}
					if(recLCList.size() > 0 && (reply == JOptionPane.YES_OPTION || reply == 0)){
						if(view_save.equalsIgnoreCase("VALIDATE")){
							lcCountYear = findStudentDB.getLastCount(sessionData,"HS_GENERAL_REGISTER", "ORIGINAL_LC");
							dupLcCountYear = findStudentDB.getLastCount(sessionData,"HS_GENERAL_REGISTER", "DUPLICATE_LC");
				//			triLcCountYear = findStudentDB.getNextCount("HS_GENERAL_REGISTER", "TRIPLICATE_LC");
							logger.info("lcCountYear => "+lcCountYear);
							logger.info("dupLcCountYear => "+dupLcCountYear);
				//			logger.info("triLcCountYear => "+triLcCountYear);
						}
			
					try {
			
					///////start of pdf creation/////////////////////
						fileName  = "NOCCertificatePrint"+commonLc.timeInMillis()+".pdf";
						fileAddress = path+fileName;
						File file = new File(fileAddress);
						FileOutputStream fileout = new FileOutputStream(file);
						Document document = new Document(com.itextpdf.text.PageSize.A4);
						PdfWriter.getInstance(document, fileout);
						document.addHeader("ABC", "XYZ");
//						document.addAuthor("MAAULI EDUCATION SOCIETY");
						document.addTitle("NO OBJECTION CERTIFICATE");
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
							String grDb = LCDetaillist.get(10);
							nocGr = nocGr + "," + grDb;
							String lcDb = LCDetaillist.get(11);
							String lcDateDb = LCDetaillist.get(12);
							if(lcDateDb.equalsIgnoreCase("null") || lcDateDb == null){
								lcDateDb = "";
							}
							//lcDateDb = commonLc.formatdd_MM_yyyy(lcDateDb);
							
							String todayDate = "";
							
							Date date = new Date();
							SimpleDateFormat today = new SimpleDateFormat("dd/MM/yyyy");
							todayDate = today.format(date);
							
							String currentAcademicYear = commonLc.getAcademicYear(todayDate.toString());
							if(currentAcademicYear.equalsIgnoreCase(academicYear)){
								is_was = "is";
							}
							
							Chunk chunkHeader1 = new Chunk("");
							chunkHeader1.setFont(fontClassBold1);
							Paragraph paragraph1 = new Paragraph();
							paragraph1.add(chunkHeader1);
							paragraph1.setAlignment(Element.ALIGN_CENTER);
							
							Chunk chunkHeader = new Chunk(noc_header_0);
							chunkHeader.setFont(fontClassBold1);
							Paragraph paragraph = new Paragraph();
							paragraph.setSpacingBefore(-20);
							paragraph.add(chunkHeader);
							paragraph.setAlignment(Element.ALIGN_CENTER);
							
							Chunk chunkSchoolName = new Chunk(noc_header);
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
							
							Chunk chunkSubject = new Chunk("NO OBJECTION CERTIFICATE");
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
							
							Chunk chunkMatter3 = new Chunk(", was studying in Std. ");
							chunkMatter3.setFont(fontClass1);
	//						Paragraph paraMatter = new Paragraph();
	//						paraMatter.setSpacingBefore(43);
	//						paraMatter.add(chunkMatter3);
	//						paraMatter.setAlignment(Element.ALIGN_LEFT);
	//						document.add(chunkMatter3);
							
							Chunk chunkStd = new Chunk(currentStdDb);
							chunkStd.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
							chunkStd.setFont(fontClassBold1);
	//						Paragraph paraMatter = new Paragraph();
	//						paraMatter.setSpacingBefore(43);
	//						paraMatter.add(chunkStd);
	//						paraMatter.setAlignment(Element.ALIGN_LEFT);
	//						document.add(chunkStd);
							
							Chunk chunkMatter4 = new Chunk(" Div. ");
							chunkMatter4.setFont(fontClass1);
	//						Paragraph paraMatter = new Paragraph();
	//						paraMatter.setSpacingBefore(43);
	//						paraMatter.add(chunkMatter4);
	//						paraMatter.setAlignment(Element.ALIGN_LEFT);
	//						document.add(chunkMatter4);
							
							Chunk chunkDiv = new Chunk(currentDivDb);
							chunkDiv.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
							chunkDiv.setFont(fontClassBold1);
	//						Paragraph paraMatter = new Paragraph();
	//						paraMatter.setSpacingBefore(43);
	//						paraMatter.add(chunkDiv);
	//						paraMatter.setAlignment(Element.ALIGN_LEFT);
	//						document.add(chunkDiv);
							
							Chunk chunkMatter5 = new Chunk(" in the academic year ");
							chunkMatter5.setFont(fontClass1);
	//						Paragraph paraMatter = new Paragraph();
	//						paraMatter.setSpacingBefore(43);
	//						paraMatter.add(chunkMatter5);
	//						paraMatter.setAlignment(Element.ALIGN_LEFT);
	//						document.add(chunkMatter5);
							
							Chunk chunkAcademic = new Chunk(academicYear);
							chunkAcademic.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
							chunkAcademic.setFont(fontClassBold1);
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
							chunkMatter6.setFont(fontClass1);
	//						Paragraph paraMatter = new Paragraph();
	//						paraMatter.setSpacingBefore(43);
	//						paraMatter.add(chunkMatter6);
	//						paraMatter.setAlignment(Element.ALIGN_LEFT);
	//						document.add(chunkMatter6);
							
							dobDb = dobDb.replace("-", "/");
							Chunk chunkDob = new Chunk(dobDb+ " ("+dobWordsDb+").");
							chunkDob.setUnderline(0.8f, -2f); //0.8 thick, -2 y-location
							chunkDob.setFont(fontClassBold1);
							
							Chunk chunkMatter1 = new Chunk(" "+he_she+" left the "+school_college+" on "+lcDateDb+
									". We have no objection to take admission in other institute for further studies.");
							chunkMatter1.setFont(fontClass1);
	
							Phrase phrase = new Phrase();
							phrase.add(chunkMatter);
							phrase.add(chunkName);
							phrase.add(chunkMatter3);
							phrase.add(chunkStd);
							phrase.add(chunkMatter4);
							phrase.add(chunkDiv);
							phrase.add(chunkMatter5);
							phrase.add(chunkAcademic);
							phrase.add(chunkMatter6);
							phrase.add(chunkDob);
							phrase.add(chunkMatter1);
							
							Paragraph paraMatter1 = new Paragraph();
							paraMatter1.setSpacingBefore(5);
							paraMatter1.add(phrase);
							paraMatter1.setAlignment(Element.ALIGN_JUSTIFIED);
							document.add(paraMatter1);
							
							/*Chunk chunkMatter11 = new Chunk("(In words) "+dobWordsDb+".He / She is / was regular in attendance.");
							Font fontMatter11 = new Font(Font.TIMES_ROMAN);
							fontMatter11.setStyle(Font.NORMAL);
							fontMatter11.setSize(12);
							fontMatter11.setColor(Color.BLACK);
							chunkMatter11.setFont(fontMatter11);
							Paragraph paraMatter11 = new Paragraph();
							paraMatter11.setSpacingBefore(5);
							paraMatter11.add(chunkMatter11);
							paraMatter11.setAlignment(Element.ALIGN_LEFT);
							document.add(paraMatter11);*/
							
							Chunk chunkMatter2 = new Chunk("          To the best of my knowledge "+he_she.toLowerCase()+" bears good moral character.");
							chunkMatter2.setFont(fontClass1);
							Paragraph paraMatter2 = new Paragraph();
							paraMatter2.setSpacingBefore(10);
							paraMatter2.add(chunkMatter2);
							paraMatter2.setAlignment(Element.ALIGN_LEFT);
							document.add(paraMatter2);
							
							Chunk chunkRC = new Chunk("Religion & Cast : "+religionDb + " " + castDb);
							chunkRC.setFont(fontClass1);
							Paragraph paraRC = new Paragraph();			
							paraRC.setSpacingBefore(15);
							paraRC.add(chunkRC);
							paraRC.setAlignment(Element.ALIGN_LEFT);
							document.add(paraRC);
							
							Chunk chunkBirthplace = new Chunk("Place of Birth : "+commonLc.FirstWordCap(birthPlaceDb));
							chunkBirthplace.setFont(fontClass1);
							Paragraph paraBirthPlace = new Paragraph();
							paraBirthPlace.setSpacingBefore(10);
							paraBirthPlace.add(chunkBirthplace);
							paraBirthPlace.setAlignment(Element.ALIGN_LEFT);
							document.add(paraBirthPlace);
							
							Chunk chunkGrNo = new Chunk("G. R. Number : "+grDb);
							chunkGrNo.setFont(fontClass1);
							Paragraph paraGrNo = new Paragraph();
							paraGrNo.setSpacingBefore(10);
							paraGrNo.add(chunkGrNo);
							paraGrNo.setAlignment(Element.ALIGN_LEFT);
							document.add(paraGrNo);
							
							if(isFooterRequired) {
								Chunk chunkPrincipal = new Chunk("             "+footer+"                  ");
								chunkPrincipal.setFont(fontClass1);
								Paragraph paraName = new Paragraph();
								paraName.setSpacingBefore(10);
								paraName.setSpacingAfter(20);
								paraName.add(chunkPrincipal);
								paraName.setAlignment(Element.ALIGN_RIGHT);
								document.add(paraName);
							}
							
							//===========================Office copy=========================//
							int spacingBeforeOffice = 70;
							if(pdf_header_bonafide_image_flag.equalsIgnoreCase("true")){
								spacingBeforeOffice = 30;
							}
							
							Chunk chunkHeader2 = new Chunk("-----------------------------------------------------------------------------------------------------------------");
							chunkHeader2.setFont(fontClassBold1);
							Paragraph paragraph2 = new Paragraph();
							paragraph2.setSpacingAfter(10);
							paragraph2.setSpacingBefore(spacingBeforeOffice);
							paragraph2.add(chunkHeader2);
							paragraph2.setAlignment(Element.ALIGN_CENTER);
							document.add(paragraph2);
							
							Chunk chunkHeaderOffice = new Chunk(noc_header_0);
							chunkHeaderOffice.setFont(fontClassBold1);
							Paragraph paragraphOffice = new Paragraph();
							paragraphOffice.setSpacingBefore(0);
							paragraphOffice.add(chunkHeaderOffice);
							paragraphOffice.setAlignment(Element.ALIGN_CENTER);
							
							Chunk chunkSchoolNameOffice = new Chunk(noc_header);
							chunkSchoolNameOffice.setFont(fontClassBold18);
							Paragraph paraSchoolNameOffice = new Paragraph();
							paraSchoolNameOffice.setSpacingBefore(10);
							paraSchoolNameOffice.add(chunkSchoolNameOffice);
							paraSchoolNameOffice.setAlignment(Element.ALIGN_CENTER);
							
							int spaceBeforeDateOffice = 15;
							int y_adj = 460;
//							if(showAdhaar) {
//								y_adj = y_adj + 15;
//							}
//							if(showSUID) {
//								y_adj = y_adj + 15;
//							}
							
							if(!isFooterRequired) {
								y_adj = y_adj - 40;
							}
							
							if(pdf_header_bonafide_image_flag.equalsIgnoreCase("true")){
								////just to add image in pdf as header
								spaceBeforeDateOffice = -25;
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
							}
							
							Chunk chunkDateOffice = new Chunk("Date :- "+todayDate);
							chunkDateOffice.setFont(fontClassBold1);
							Paragraph paragraphDateOffice = new Paragraph();
							paragraphDateOffice.setSpacingBefore(spaceBeforeDateOffice);
							paragraphDateOffice.add(chunkDateOffice);
							paragraphDateOffice.setAlignment(Element.ALIGN_RIGHT);
							document.add(paragraphDateOffice);
							
							Chunk chunkSubjectOffice = new Chunk("NO OBJECTION CERTIFICATE");
							chunkSubjectOffice.setFont(fontClassBold18);
							Paragraph paragraphSubjectOffice = new Paragraph();
							paragraphSubjectOffice.setSpacingBefore(15);
							paragraphSubjectOffice.setSpacingAfter(15);
							paragraphSubjectOffice.add(chunkSubjectOffice);
							paragraphSubjectOffice.setAlignment(Element.ALIGN_CENTER);
							document.add(paragraphSubjectOffice);
							
							Paragraph paraMatter1Office = new Paragraph();
							paraMatter1Office.setSpacingBefore(5);
							paraMatter1Office.add(phrase);
							paraMatter1Office.setAlignment(Element.ALIGN_JUSTIFIED);
							document.add(paraMatter1Office);
							
							/*Chunk chunkMatter11 = new Chunk("(In words) "+dobWordsDb+".He / She is / was regular in attendance.");
							Font fontMatter11 = new Font(Font.TIMES_ROMAN);
							fontMatter11.setStyle(Font.NORMAL);
							fontMatter11.setSize(12);
							fontMatter11.setColor(Color.BLACK);
							chunkMatter11.setFont(fontMatter11);
							Paragraph paraMatter11 = new Paragraph();
							paraMatter11.setSpacingBefore(5);
							paraMatter11.add(chunkMatter11);
							paraMatter11.setAlignment(Element.ALIGN_LEFT);
							document.add(paraMatter11);*/
							
							Chunk chunkMatter2Office = new Chunk("          To the best of my knowledge "+he_she.toLowerCase()+" bears good moral character.");
							chunkMatter2Office.setFont(fontClass1);
							Paragraph paraMatter2Office = new Paragraph();
							paraMatter2Office.setSpacingBefore(10);
							paraMatter2Office.add(chunkMatter2Office);
							paraMatter2Office.setAlignment(Element.ALIGN_LEFT);
							document.add(paraMatter2Office);
							
							Chunk chunkRCOffice = new Chunk("Religion & Cast : "+religionDb + " " + castDb);
							chunkRCOffice.setFont(fontClass1);
							Paragraph paraRCOffice = new Paragraph();			
							paraRCOffice.setSpacingBefore(15);
							paraRCOffice.add(chunkRCOffice);
							paraRCOffice.setAlignment(Element.ALIGN_LEFT);
							document.add(paraRCOffice);
							
							Chunk chunkBirthplaceOffice = new Chunk("Place of Birth : "+birthPlaceDb);
							chunkBirthplaceOffice.setFont(fontClass1);
							Paragraph paraBirthPlaceOffice = new Paragraph();
							paraBirthPlaceOffice.setSpacingBefore(10);
							paraBirthPlaceOffice.add(chunkBirthplaceOffice);
							paraBirthPlaceOffice.setAlignment(Element.ALIGN_LEFT);
							document.add(paraBirthPlaceOffice);
							
							Chunk chunkGrNoOffice = new Chunk("G. R. Number : "+grDb);
							chunkGrNoOffice.setFont(fontClass1);
							Paragraph paraGrNoOffice = new Paragraph();
							paraGrNoOffice.setSpacingBefore(10);
							paraGrNoOffice.add(chunkGrNo);
							paraGrNoOffice.setAlignment(Element.ALIGN_LEFT);
							document.add(paraGrNoOffice);
							
							if(isFooterRequired) {
								Chunk chunkPrincipalOffice = new Chunk("             "+footer+"                  ");
								chunkPrincipalOffice.setFont(fontClass1);
								Paragraph paraNameOffice = new Paragraph();
								paraNameOffice.setSpacingBefore(10);
								paraNameOffice.add(chunkPrincipalOffice);
								paraNameOffice.setAlignment(Element.ALIGN_RIGHT);
								document.add(paraNameOffice);
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
								String fileNameView 	= "NOCCertificate"+view_save+commonLc.timeInMillis()+".pdf";
								String fileAddressView = path+fileNameView;
								
								reader 		= new PdfReader(fileAddress);
								stamper 	= new PdfStamper(reader, new FileOutputStream(fileAddressView));
								
								if(view_save.equalsIgnoreCase("validate"))
									stamper.setEncryption(null, null, PdfWriter.AllowPrinting | PdfWriter.AllowCopy, PdfWriter.STRENGTH40BITS);
								stamper.close();
								
								logger.info("file "+commonLc.deleteFile(fileAddress)+" deleted");
								fileName		=  fileNameView;
								fileAddress		=  fileAddressView;
							} catch (IOException e) {
								fileOpenFlag = true;
								logger.info("view file Exception == "+e);
								JOptionPane.showMessageDialog(null, "Please close "+fileName+" and try again"); 
							}
						}
						
					} catch (FileNotFoundException e) {
						fileOpenFlag = true;
						logger.info("FileNotFound Exception == "+e);
						JOptionPane.showMessageDialog(null, "Please close "+fileName+" and try again"); 
					} catch (DocumentException e) {
						logger.info("last Exception == "+e);
					}
				}
				else if(reply == JOptionPane.NO_OPTION){
					JOptionPane.showMessageDialog(null, "Please validate for ignored students");
				}
				else{
					JOptionPane.showMessageDialog(null, "NOC not created as no student found with LC."); 
				}
			}
		} catch (Exception e) {
			logger.info("fetch data Exception == "+e);
		}finally {
			findStudentDB.closeDatabase(sessionData);
		}
		
		 // open pdf 
		if(!fileOpenFlag && recLCList.size() > 0){
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
				logger.info(":: -----Exception---- ::\n"+e);
			}
		}
	}
}
