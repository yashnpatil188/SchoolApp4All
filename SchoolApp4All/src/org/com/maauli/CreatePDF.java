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

import com.itextpdf.text.PageSize;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
//import com.lowagie.text.pdf.draw.LineSeparator;

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


public class CreatePDF {
	DBValidate findStudentDB = new DBValidate();
	Common commonLc = new Common();
	static String leavingDate 		= "";
	static String issueDate 		= "";
	static String reason 			= ""; 
	static String remark 			= "";
	static String conduct 			= "";
	static String fileName 			= "";
	static  boolean fileOpenFlag	= false;
	List<String> selectedLcList 	= new ArrayList();
	static String fileAddress		= "";
	static String path				= "";
	static String std 				= "";
    static String div 				= "";
    static String section			= "";
    static String secName			= "";
    static String user_name 		= "";
	static String user_role 		= "";
    ResourceBundle bundle    		= ResourceBundle.getBundle("org.com.accesser.school"); 
    static Logger logger 			= Logger.getLogger(CreatePDF.class.getName());
	
	public void LeavingCertificate(SessionData sessionData, String view_save, String conductTxt, String leavingDt, String issueDt, String reasonT, String remarkT, 
			List<String> passGrList,String sec, String retUserName, String retUserRole, String academic) throws DocumentException{
		
		logger.info("Inside LeavingCertificate pdf constructor..............");
		System.gc();
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
    	
		List<String> recLCList = new ArrayList();
		
		String separator ="----------------------------------------------------------------------------------------------------------------------------------";
		leavingDate 	= leavingDt;
		issueDate 		= issueDt;
		if(!issueDt.equalsIgnoreCase("")){
			issueDt 		= commonLc.formatdd_MM_yyyy(issueDt);
		}
		reason 			= reasonT; 
		remark 			= remarkT;
		conduct         = conductTxt;
		logger.info("view_save :: "+view_save);
		logger.info("leavingDate :: "+leavingDate);
		logger.info("issueDate :: "+issueDate);
		logger.info("reason :: "+reason);
		logger.info("remark :: "+remark);
		logger.info("conduct :: "+conduct);
		
		path = commonLc.createTodayFolder(commonLc.getDriveName() + bundle.getString("LC_PDF_PATH_"+sessionData.getDBName()),true)+"/";
		
		if(leavingDate.contains("/")){
			leavingDate = commonLc.MM_dd_mm_yyyy(leavingDate);
		}
		
		logger.info("leavingDate after format: "+leavingDate);
		logger.info("issueDate :: "+issueDate);
		logger.info("reason :: "+reason);
		logger.info("remark :: "+remark);
		logger.info("conduct :: "+conduct);
		int lcCountYear = 0;
		int dupLcCountYear = 0;
		
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("dd/MM/yyyy");
		String todayDate = today.format(date);
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		String currentYear = year.format(date);
		
		try {
			recLCList = findStudentDB.findStudentLC(sessionData, passGrList,section,academic);
		} catch (Exception e) {
			logger.info("recLCList Exception == "+e);
		}
		try {
			
			fileName  = "LeavingCertificatePrint"+commonLc.timeInMillis()+".pdf";
			fileAddress = path+fileName;
			File file = new File(fileAddress);
			FileOutputStream fileout = new FileOutputStream(file);
			Document document = new Document(com.lowagie.text.PageSize.A4);
			PdfWriter.getInstance(document, fileout);
			document.addHeader("ABC", "XYZ");
//			document.addAuthor("MAAULI EDUCATION SOCIETY");
			document.addTitle("Leaving Certificate");
			document.open();
			
			try {
				lcCountYear = findStudentDB.getPatternCount(sessionData, "HS_GENERAL_REGISTER", "ORIGINAL_LC", commonLc.getAcademicYear(commonLc.getCurrentDate()));
				logger.info("lcCountYear => "+lcCountYear);
				dupLcCountYear = findStudentDB.getPatternCount(sessionData, "HS_GENERAL_REGISTER", "DUPLICATE_LC", commonLc.getAcademicYear(commonLc.getCurrentDate()));
				logger.info("dupLcCountYear => "+dupLcCountYear);
			} catch (Exception e) {
				logger.info("CountYear Exception == "+e);
			}
			
		    for(int i=0;i<recLCList.size(); i++){
		    	logger.info(recLCList.get(i));
		    	StringTokenizer st1 = new StringTokenizer(recLCList.get(i), "|");
		    	List<String> LCDetaillist = new ArrayList();
		    	while(st1.hasMoreTokens()){
		    		LCDetaillist.add(st1.nextToken());
		    	}
		    	String grDb = LCDetaillist.get(0);
				String origLcDb = LCDetaillist.get(1);
				String nameDb = LCDetaillist.get(2);
				String motherNameDb = LCDetaillist.get(3);
				String nationalityDb = LCDetaillist.get(4);
				String religionDb = LCDetaillist.get(5);
				String castDb = LCDetaillist.get(6);
				String dobDb = LCDetaillist.get(7);
				String dobWordsDb = LCDetaillist.get(8);
				String lastSchoolDb = LCDetaillist.get(9);
				String dateAdmittedDb = LCDetaillist.get(10);
				String progressDb = LCDetaillist.get(11);
				String conductDb = LCDetaillist.get(12);
				String dateLeavingDb = LCDetaillist.get(13);
				String leavingStdDb = LCDetaillist.get(14);
				String reasonDb = LCDetaillist.get(15);
				reasonDb = commonLc.retrieveComma(reasonDb);
				String remarkDb = LCDetaillist.get(16);
				remarkDb = commonLc.retrieveComma(remarkDb);
				String duplicateLcDb = LCDetaillist.get(17);
				String originalLcDateDb = LCDetaillist.get(18);
				logger.info("originalLcDateDb before:::"+origLcDb);
				if(originalLcDateDb.equalsIgnoreCase("00-00-0000") || originalLcDateDb.equalsIgnoreCase("-")){
					originalLcDateDb = "";
				}
//				else if(!originalLcDateDb.equalsIgnoreCase("") && !originalLcDateDb.equalsIgnoreCase("-")){
//					originalLcDateDb = commonLc.MMM_ddmmyyy(originalLcDateDb);
//				}
				logger.info("originalLcDateDb:::"+originalLcDateDb);
				String duplicateLcDateDb = LCDetaillist.get(19);
				String studyingSinceDb = LCDetaillist.get(20);
				String currentStdDb = LCDetaillist.get(21);
				String birthPlaceDb = LCDetaillist.get(22);
				String remark1Db = LCDetaillist.get(23);
				
				logger.info("originalLcDateDb=="+originalLcDateDb);
				if(leavingStdDb.equalsIgnoreCase("") || leavingStdDb.equalsIgnoreCase("-")){
					leavingStdDb = currentStdDb;
				}
				String duplicateLcNo ="";
				String duplicateLcDate ="";
				String sinceDate = "";
				
				logger.info("admitted year = "+dateAdmittedDb.substring(7));
				logger.info("current year = "+(Integer.parseInt(currentYear)-1));
				logger.info("admitted month = "+dateAdmittedDb.substring(3, 6));
				String twoDigitYear = ""+(Integer.parseInt(currentYear)-1);
				twoDigitYear = twoDigitYear.substring(2);
				logger.info("check == "+dateAdmittedDb.substring(7).equalsIgnoreCase(twoDigitYear));
				
				if(dateAdmittedDb.substring(7).equalsIgnoreCase(twoDigitYear)) {
					sinceDate = dateAdmittedDb.substring(3, 6) +" "+ (Integer.parseInt(currentYear)-1);
				}
				else {
					sinceDate = "JUNE "+(Integer.parseInt(currentYear)-1);
				}
	    	    
				Chunk chunkHeader = new Chunk("P.R. High School");
				Font font = new Font(Font.TIMES_ROMAN);
				font.setStyle(Font.BOLD);
				font.setSize(24);
				font.setColor(Color.RED);
				chunkHeader.setFont(font);
				Paragraph paragraph = new Paragraph();
				paragraph.add(chunkHeader);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph);
				
				Chunk chunkSchoolName = new Chunk("MARATHI MEDIUM PRE-PRIMARY & PRIMARY SCHOOL, BHIWANDI,(DIST. THANE)");
				Font fontSchoolName = new Font(Font.TIMES_ROMAN);
				fontSchoolName.setStyle(Font.BOLD);
				fontSchoolName.setSize(12);
				fontSchoolName.setColor(Color.BLACK);
				chunkSchoolName.setFont(fontSchoolName);
				Paragraph paraSchoolName = new Paragraph();
				paraSchoolName.setSpacingBefore(0);
				paraSchoolName.add(chunkSchoolName);
				paraSchoolName.setAlignment(Element.ALIGN_CENTER);
				document.add(paraSchoolName);
				
				Chunk chunkSchoolAdd = new Chunk("KASAR ALLEY, BHIWANDI, BHIWANDI 421302");
				Font fontSchoolAdd = new Font(Font.TIMES_ROMAN);
				fontSchoolAdd.setStyle(Font.NORMAL);
				fontSchoolAdd.setSize(12);
				fontSchoolAdd.setColor(Color.BLACK);
				chunkSchoolAdd.setFont(fontSchoolAdd);
				Paragraph paraSchoolAdd = new Paragraph();
				paraSchoolAdd.setSpacingBefore(0);
				paraSchoolAdd.add(chunkSchoolAdd);
				paraSchoolAdd.setAlignment(Element.ALIGN_CENTER);
				document.add(paraSchoolAdd);
				
				Chunk chunkAddress = new Chunk("Recognition Letter No. 1111-1947-22 Dated 19-09-1947");
				Font fontAddress = new Font(Font.TIMES_ROMAN);
				//fontAddress.setStyle(Font.BOLD);
				fontAddress.setSize(12);
				fontAddress.setColor(Color.BLACK);
				chunkAddress.setFont(fontAddress);
				Paragraph paragraphAddress = new Paragraph();
				paragraphAddress.add(chunkAddress);
				paragraphAddress.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraphAddress);
				
				Chunk chunkSubject = new Chunk("SCHOOL LEAVING CERTIFICATE");
				Font fontSubject = new Font(Font.TIMES_ROMAN);
				fontSubject.setStyle(Font.BOLD);
				fontSubject.setSize(18);
				fontSubject.setColor(Color.BLACK);
				chunkSubject.setFont(fontSubject);
				Paragraph paragraphSubject = new Paragraph();
				paragraphSubject.setSpacingBefore(5);
				paragraphSubject.add(chunkSubject);
				paragraphSubject.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraphSubject);
				
				Chunk chunkSpecimen = new Chunk("SPECIMEN NO. 17");
				Font fontSpecimen = new Font(Font.TIMES_ROMAN);
				fontSpecimen.setStyle(Font.NORMAL);
				fontSpecimen.setSize(12);
				fontSpecimen.setColor(Color.BLACK);
				chunkSpecimen.setFont(fontSpecimen);
				Paragraph paragraphSpecimen = new Paragraph();
				paragraphSpecimen.setSpacingBefore(0);
				paragraphSpecimen.add(chunkSpecimen);
				paragraphSpecimen.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraphSpecimen);
				
				Chunk chunkRule = new Chunk("(VIDE RULE NO. 17 OF GRANT-IN-AID CODE)");
				Font fontRule = new Font(Font.TIMES_ROMAN);
				fontRule.setStyle(Font.NORMAL);
				fontRule.setSize(12);
				fontRule.setColor(Color.BLACK);
				chunkRule.setFont(fontRule);
				Paragraph paraRule = new Paragraph();
				paraRule.setSpacingBefore(0);
				paraRule.setSpacingAfter(-2);
				paraRule.add(chunkRule);
				paraRule.setAlignment(Element.ALIGN_CENTER);
				document.add(paraRule);
				
	//			LineSeparator sep = new LineSeparator();
	//			sep.setOffset(5);
	//			Paragraph paraSep = new Paragraph();
	//			paraSep.setSpacingAfter(7);
	//			paraSep.add(sep);
	//			document.add(paraSep);
				
				Chunk sep = new Chunk(separator);
				Paragraph paraSep = new Paragraph();
				paraSep.setSpacingBefore(0);
				paraSep.add(sep);
				paraSep.setAlignment(Element.ALIGN_LEFT);
				document.add(paraSep);
				
				Chunk chunkGrNo = new Chunk("    G.R. No.:  "+grDb);
				Font fontGrNo = new Font(Font.TIMES_ROMAN);
				fontGrNo.setStyle(Font.BOLD);
				fontGrNo.setSize(16);
				fontGrNo.setColor(Color.BLACK);
				chunkGrNo.setFont(fontGrNo);
				
				Paragraph paraGrNo = new Paragraph();
				paraGrNo.setSpacingBefore(-1);
//				paraGrNo.setSpacingAfter(0);
				paraGrNo.add(chunkGrNo);
				paraGrNo.setAlignment(Element.ALIGN_LEFT);
				document.add(paraGrNo);
				
				if(!origLcDb.equalsIgnoreCase("") && !origLcDb.equalsIgnoreCase("-") && !view_save.equalsIgnoreCase("view"))
				{
					Chunk chunkDUPLICATE = new Chunk("                                                    DUPLICATE ");
					Font fontDUPLICATE = new Font(Font.TIMES_ROMAN);
					fontDUPLICATE.setStyle(Font.BOLD);
					fontDUPLICATE.setSize(16);
					fontDUPLICATE.setColor(Color.BLACK);
					chunkDUPLICATE.setFont(fontDUPLICATE);
					Paragraph paraDUPLICATE = new Paragraph();
					paraDUPLICATE.setSpacingBefore(-16);
//					paraDUPLICATE.setSpacingAfter(-16);
					paraDUPLICATE.add(chunkDUPLICATE);
					paraDUPLICATE.setAlignment(Element.ALIGN_LEFT);
					document.add(paraDUPLICATE);
				}
				else if(!duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("-"))
				{
					Chunk chunkDUPLICATE = new Chunk("                                                    DUPLICATE ");
					Font fontDUPLICATE = new Font(Font.TIMES_ROMAN);
					fontDUPLICATE.setStyle(Font.BOLD);
					fontDUPLICATE.setSize(16);
					fontDUPLICATE.setColor(Color.BLACK);
					chunkDUPLICATE.setFont(fontDUPLICATE);
					Paragraph paraDUPLICATE = new Paragraph();
					paraDUPLICATE.setSpacingBefore(-16);
//					paraDUPLICATE.setSpacingAfter(-16);
					paraDUPLICATE.add(chunkDUPLICATE);
					paraDUPLICATE.setAlignment(Element.ALIGN_LEFT);
					document.add(paraDUPLICATE);
				}
				
				Chunk chunkLcNoTitle = new Chunk("                                                                           " +
												 "           L.C. No. :");
				Font fontLcNoTitle = new Font(Font.TIMES_ROMAN);
				fontLcNoTitle.setStyle(Font.BOLD);
				fontLcNoTitle.setSize(16);
				fontLcNoTitle.setColor(Color.BLACK);
				chunkLcNoTitle.setFont(fontLcNoTitle);
				Paragraph paraLcNoTitle = new Paragraph();
				paraLcNoTitle.setSpacingBefore(-16);
				paraLcNoTitle.add(chunkLcNoTitle);
				paraLcNoTitle.setAlignment(Element.ALIGN_LEFT);
				document.add(paraLcNoTitle);
				
				String LcCount =  "";
				String newLcNo = "";
				String origLcNo = "";
				String originalLcDate = "";
				
				if(view_save.equalsIgnoreCase("view") && !origLcDb.equalsIgnoreCase("") && !origLcDb.equalsIgnoreCase("-")){
					origLcNo = origLcDb;
					originalLcDate = originalLcDateDb;
					newLcNo = origLcDb;
					issueDt = originalLcDateDb;
							
					if(!duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("-")){
						newLcNo = duplicateLcDb;
						issueDt = duplicateLcDateDb;
					}
				}
				else if(!origLcDb.equalsIgnoreCase("") && !origLcDb.equalsIgnoreCase("-")){
					origLcNo = origLcDb;
					originalLcDate = originalLcDateDb;
					dupLcCountYear = dupLcCountYear +1;
					String dupLcCount =  String.format("%03d", dupLcCountYear);
//					newLcNo = (dupLcCount)+"/"+(Integer.parseInt(currentYear)-1)+"-"+currentYear.substring(2);
					newLcNo = (dupLcCount)+"/"+commonLc.getAcademicYear(todayDate);
					logger.info("duplicate LC No.: "+newLcNo);
					duplicateLcNo = newLcNo;
					duplicateLcDate = issueDate;
				}
				else{
					lcCountYear = lcCountYear +1;
					LcCount =  String.format("%03d", lcCountYear);
//					newLcNo = (LcCount)+"/"+(Integer.parseInt(currentYear)-1)+"-"+currentYear.substring(2);
					newLcNo = (LcCount)+"/"+commonLc.getAcademicYear(todayDate);
					logger.info("New LC No.: "+newLcNo);
					origLcNo = newLcNo;
					originalLcDate = issueDate;
				}
				logger.info("origLcNo - - - "+origLcNo);
				logger.info("originalLcDateDb - - - "+originalLcDateDb);
				logger.info("duplicateLcNo - - - "+duplicateLcNo);
				logger.info("duplicateLcDate - - - "+duplicateLcDate);
				
				Chunk chunkLcNo = new Chunk("                                                                           " +
				 "                             "+newLcNo);
	//			Chunk chunkLcNo = new Chunk(newLcNo);
				Font fontLcNo = new Font(Font.TIMES_ROMAN);
				fontLcNo.setStyle(Font.BOLD);
				fontLcNo.setSize(16);
				fontLcNo.setColor(Color.BLACK);
				chunkLcNo.setFont(fontLcNo);
				Paragraph paraLcNo = new Paragraph();	
				paraLcNo.setSpacingBefore(-16);
				paraLcNo.add(chunkLcNo);
				paraLcNo.setAlignment(Element.ALIGN_LEFT);
				document.add(paraLcNo);
				
	//			LineSeparator sep1 = new LineSeparator();
	//			sep1.setOffset(5);
	//			Paragraph paraSep1 = new Paragraph();
	//			paraSep1.setSpacingBefore(-3);
	//			paraSep1.add(sep1);
	//			document.add(paraSep1);
				
				Chunk sep1 = new Chunk(separator);
				Paragraph paraSep1 = new Paragraph();
				paraSep1.setSpacingBefore(-5);
				paraSep1.add(sep1);
				paraSep1.setAlignment(Element.ALIGN_LEFT);
				document.add(paraSep1);
				
				Chunk chunkTitleName = new Chunk("      01.  Name Of The Pupil In Full");
				Font fontTitleName = new Font(Font.TIMES_ROMAN);
				fontTitleName.setStyle(Font.BOLD);
				fontTitleName.setStyle(Font.ITALIC);
				fontTitleName.setSize(12);
				fontTitleName.setColor(Color.BLACK);
				chunkTitleName.setFont(fontTitleName);
				
				Paragraph paraTitleName = new Paragraph();	
				paraTitleName.setSpacingBefore(5);
				paraTitleName.add(chunkTitleName);
				paraTitleName.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleName);
				
				Chunk chunkName = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(nameDb));
				Font fontName = new Font(Font.TIMES_ROMAN);
				fontName.setStyle(Font.BOLD);
				fontName.setStyle(Font.ITALIC);
				fontName.setSize(12);
				fontName.setColor(Color.BLACK);
				chunkName.setFont(fontName);
				
				Paragraph paraName = new Paragraph();	
				paraName.setSpacingBefore(-16);
				paraName.add(chunkName);
				paraName.setAlignment(Element.ALIGN_LEFT);
				document.add(paraName);
				
				Chunk chunkTitleMother = new Chunk("      02.   Mother Name");
				Font fontTitleMother = new Font(Font.TIMES_ROMAN);
				fontTitleMother.setStyle(Font.BOLD);
				fontTitleMother.setStyle(Font.ITALIC);
				fontTitleMother.setSize(12);
				fontTitleMother.setColor(Color.BLACK);
				chunkTitleMother.setFont(fontTitleMother);
				
				Paragraph paraTitleMother = new Paragraph();	
				paraTitleMother.setSpacingBefore(8);
				paraTitleMother.add(chunkTitleMother);
				paraTitleMother.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleMother);
				
				Chunk chunkMother = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(motherNameDb));
				Font fontMother = new Font(Font.TIMES_ROMAN);
				fontMother.setStyle(Font.BOLD);
				fontMother.setStyle(Font.ITALIC);
				fontMother.setSize(12);
				fontMother.setColor(Color.BLACK);
				chunkMother.setFont(fontMother);
				
				Paragraph paraMother = new Paragraph();	
				paraMother.setSpacingBefore(-16);
				paraMother.add(chunkMother);
				paraMother.setAlignment(Element.ALIGN_LEFT);
				document.add(paraMother);
				
				Chunk chunkTitleReligion = new Chunk("      03.   Religion");
				Font fontTitleReligion = new Font(Font.TIMES_ROMAN);
				fontTitleReligion.setStyle(Font.BOLD);
				fontTitleReligion.setStyle(Font.ITALIC);
				fontTitleReligion.setSize(12);
				fontTitleReligion.setColor(Color.BLACK);
				chunkTitleReligion.setFont(fontTitleReligion);
				
				Paragraph paraTitleReligion = new Paragraph();	
				paraTitleReligion.setSpacingBefore(8);
				paraTitleReligion.add(chunkTitleReligion);
				paraTitleReligion.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleReligion);
				
				Chunk chunkReligion = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(religionDb));
				Font fontReligion = new Font(Font.TIMES_ROMAN);
				fontReligion.setStyle(Font.BOLD);
				fontReligion.setStyle(Font.ITALIC);
				fontReligion.setSize(12);
				fontReligion.setColor(Color.BLACK);
				chunkReligion.setFont(fontReligion);
				
				Paragraph paraReligion = new Paragraph();	
				paraReligion.setSpacingBefore(-16);
				paraReligion.add(chunkReligion);
				paraReligion.setAlignment(Element.ALIGN_LEFT);
				document.add(paraReligion);
				
				Chunk chunkTitleCaste = new Chunk("      04.   Caste With Sub-Caste");
				Font fontTitleCaste = new Font(Font.TIMES_ROMAN);
				fontTitleCaste.setStyle(Font.BOLD);
				fontTitleCaste.setStyle(Font.ITALIC);
				fontTitleCaste.setSize(12);
				fontTitleCaste.setColor(Color.BLACK);
				chunkTitleCaste.setFont(fontTitleCaste);
			
				Paragraph paraTitleCaste = new Paragraph();	
				paraTitleCaste.setSpacingBefore(8);
				paraTitleCaste.add(chunkTitleCaste);
				paraTitleCaste.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleCaste);
				
				Chunk chunkCaste = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(castDb));
				Font fontCaste = new Font(Font.TIMES_ROMAN);
				fontCaste.setStyle(Font.BOLD);
				fontCaste.setStyle(Font.ITALIC);
				fontCaste.setSize(12);
				fontCaste.setColor(Color.BLACK);
				chunkCaste.setFont(fontCaste);
				
				Paragraph paraCaste = new Paragraph();	
				paraCaste.setSpacingBefore(-16);
				paraCaste.add(chunkCaste);
				paraCaste.setAlignment(Element.ALIGN_LEFT);
				document.add(paraCaste);
				
//				Chunk chunkTitleCaste2 = new Chunk("      BELONGING TO BACKWARD CLASSES AND");
//				Font fontTitleCaste2 = new Font(Font.TIMES_ROMAN);
//				fontTitleCaste2.setStyle(Font.BOLD);
//				fontTitleCaste2.setSize(12);
//				fontTitleCaste2.setColor(Color.BLACK);
//				chunkTitleCaste2.setFont(fontTitleCaste2);
//			
//				Paragraph paraTitleCaste2 = new Paragraph();	
//				paraTitleCaste2.setSpacingBefore(0);
//				paraTitleCaste2.add(chunkTitleCaste2);
//				paraTitleCaste2.setAlignment(Element.ALIGN_LEFT);
//				document.add(paraTitleCaste2);
//				
//				Chunk chunkTitleCaste3 = new Chunk("      CATEGORY AMONG BACKWARD CLASSES");
//				Font fontTitleCaste3 = new Font(Font.TIMES_ROMAN);
//				fontTitleCaste3.setStyle(Font.BOLD);
//				fontTitleCaste3.setSize(12);
//				fontTitleCaste3.setColor(Color.BLACK);
//				chunkTitleCaste3.setFont(fontTitleCaste3);
//			
//				Paragraph paraTitleCaste3 = new Paragraph();	
//				paraTitleCaste3.setSpacingBefore(0);
//				paraTitleCaste3.add(chunkTitleCaste3);
//				paraTitleCaste3.setAlignment(Element.ALIGN_LEFT);
//				document.add(paraTitleCaste3);
				
				Chunk chunkTitleNationality = new Chunk("      05.   Nationality");
				Font fontTitleNationality = new Font(Font.TIMES_ROMAN);
				fontTitleNationality.setStyle(Font.BOLD);
				fontTitleNationality.setStyle(Font.ITALIC);
				fontTitleNationality.setSize(12);
				fontTitleNationality.setColor(Color.BLACK);
				chunkTitleNationality.setFont(fontTitleNationality);
				
				Paragraph paraTitleNationality = new Paragraph();	
				paraTitleNationality.setSpacingBefore(8);
				paraTitleNationality.add(chunkTitleNationality);
				paraTitleNationality.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleNationality);
				
				Chunk chunkNationality = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(nationalityDb));
				Font fontNationality = new Font(Font.TIMES_ROMAN);
				fontNationality.setStyle(Font.BOLD);
				fontNationality.setStyle(Font.ITALIC);
				fontNationality.setSize(12);
				fontNationality.setColor(Color.BLACK);
				chunkNationality.setFont(fontNationality);
				
				Paragraph paraNationality = new Paragraph();	
				paraNationality.setSpacingBefore(-16);
				paraNationality.add(chunkNationality);
				paraNationality.setAlignment(Element.ALIGN_LEFT);
				document.add(paraNationality);
				
				Chunk chunkTitlePOB = new Chunk("      06.   Place Of Birth");
				Font fontTitlePOB = new Font(Font.TIMES_ROMAN);
				fontTitlePOB.setStyle(Font.BOLD);
				fontTitlePOB.setStyle(Font.ITALIC);
				fontTitlePOB.setSize(12);
				fontTitlePOB.setColor(Color.BLACK);
				chunkTitlePOB.setFont(fontTitlePOB);
				
				Paragraph paraTitlePOB = new Paragraph();	
				paraTitlePOB.setSpacingBefore(8);
				paraTitlePOB.add(chunkTitlePOB);
				paraTitlePOB.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitlePOB);
				
				Chunk chunkPOB = new Chunk("                                                                     :       "+commonLc.FirstWordCap(birthPlaceDb));
				Font fontPOB = new Font(Font.TIMES_ROMAN);
				fontPOB.setStyle(Font.BOLD);
				fontPOB.setStyle(Font.ITALIC);
				fontPOB.setSize(12);
				fontPOB.setColor(Color.BLACK);
				chunkPOB.setFont(fontPOB);
				
				Paragraph paraPOB = new Paragraph();	
				paraPOB.setSpacingBefore(-16);
				paraPOB.add(chunkPOB);
				paraPOB.setAlignment(Element.ALIGN_LEFT);
				document.add(paraPOB);
				
				Chunk chunkTitleDOB1 = new Chunk("      07.   Date Of Birth (In Figure)");
				Font fontTitleDOB1 = new Font(Font.TIMES_ROMAN);
				fontTitleDOB1.setStyle(Font.BOLD);
				fontTitleDOB1.setStyle(Font.ITALIC);
				fontTitleDOB1.setSize(12);
				fontTitleDOB1.setColor(Color.BLACK);
				chunkTitleDOB1.setFont(fontTitleDOB1);
				
				Paragraph paraTitleDOB1 = new Paragraph();	
				paraTitleDOB1.setSpacingBefore(8);
				paraTitleDOB1.add(chunkTitleDOB1);
				paraTitleDOB1.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleDOB1);
				
//				dobDb = commonLc.MMM_ddmmyyy(dobDb);
				Chunk chunkDOB = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(dobDb));
				Font fontDOB = new Font(Font.TIMES_ROMAN);
				fontDOB.setStyle(Font.BOLD);
				fontDOB.setStyle(Font.ITALIC);
				fontDOB.setSize(12);
				fontDOB.setColor(Color.BLACK);
				chunkDOB.setFont(fontDOB);
				
				Paragraph paraTitleDOB = new Paragraph();	
				paraTitleDOB.setSpacingBefore(-16);
				paraTitleDOB.add(chunkDOB);
				paraTitleDOB.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleDOB);
				
				Chunk chunkTitleDOB2 = new Chunk("      08.   Date Of Birth (In Word)");
				Font fontTitleDOB2 = new Font(Font.TIMES_ROMAN);
				fontTitleDOB2.setStyle(Font.BOLD);
				fontTitleDOB2.setStyle(Font.ITALIC);
				fontTitleDOB2.setSize(12);
				fontTitleDOB2.setColor(Color.BLACK);
				chunkTitleDOB2.setFont(fontTitleDOB2);
				
				Paragraph paraTitleDOB2 = new Paragraph();	
				paraTitleDOB2.setSpacingBefore(8);
				paraTitleDOB2.add(chunkTitleDOB2);
				paraTitleDOB2.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleDOB2);
				
				StringTokenizer dobWordsToken = new StringTokenizer(dobWordsDb);
				int j = 1;
				String dobWords1 = "";
				String dobWords2 = "";
				while (dobWordsToken.hasMoreElements()) {
					if(j <= 6){
						dobWords1 = dobWords1+" "+dobWordsToken.nextElement();
					}
					else{
						dobWords2 = dobWords2+" "+dobWordsToken.nextElement();
					}
					j++;
				}
				
				Chunk chunkDOB_Word = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(dobWords1));
				Font fontDOB_Word = new Font(Font.TIMES_ROMAN);
				fontDOB_Word.setStyle(Font.BOLD);
				fontDOB_Word.setStyle(Font.ITALIC);
				fontDOB_Word.setSize(12);
				fontDOB_Word.setColor(Color.BLACK);
				chunkDOB_Word.setFont(fontDOB_Word);
				
				Paragraph paraTitleDOB_Word = new Paragraph();	
				paraTitleDOB_Word.setSpacingBefore(-16);
				paraTitleDOB_Word.add(chunkDOB_Word);
				paraTitleDOB_Word.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleDOB_Word);
				
				if(!dobWords2.equalsIgnoreCase("")){
					Chunk chunkDOB1_Word = new Chunk  ("                                                                             "+commonLc.FirstWordCap(dobWords2));
					Font fontDOB1_Word = new Font(Font.TIMES_ROMAN);
					fontDOB1_Word.setStyle(Font.BOLD);
					fontDOB1_Word.setStyle(Font.ITALIC);
					fontDOB1_Word.setSize(12);
					fontDOB1_Word.setColor(Color.BLACK);
					chunkDOB1_Word.setFont(fontDOB1_Word);
					
					Paragraph paraTitleDOB1_Word = new Paragraph();	
					paraTitleDOB1_Word.setSpacingBefore(0);
	//				paraTitleDOB1_Word.add(chunkTitleLastSchool1);
					paraTitleDOB1_Word.add(chunkDOB1_Word);
					paraTitleDOB1_Word.setAlignment(Element.ALIGN_LEFT);
					document.add(paraTitleDOB1_Word);
				}
				
//				Chunk chunkTitleDOB3 = new Chunk("      BOTH IN WORDS AND FIGURES");
//				Font fontTitleDOB3 = new Font(Font.TIMES_ROMAN);
//				fontTitleDOB3.setStyle(Font.BOLD);
//				fontTitleDOB3.setSize(12);
//				fontTitleDOB3.setColor(Color.BLACK);
//				chunkTitleDOB3.setFont(fontTitleDOB3);
//				
//				Paragraph paraTitleDOB3 = new Paragraph();	
//				paraTitleDOB3.setSpacingBefore(0);
//				paraTitleDOB3.add(chunkTitleDOB3);
//				paraTitleDOB3.setAlignment(Element.ALIGN_LEFT);
//				document.add(paraTitleDOB3);
				
				
				
				Chunk chunkTitleLastSchool = new Chunk("      09.   Last School Attended");
				Font fontTitleLastSchool = new Font(Font.TIMES_ROMAN);
				fontTitleLastSchool.setStyle(Font.BOLD);
				fontTitleLastSchool.setStyle(Font.ITALIC);
				fontTitleLastSchool.setSize(12);
				fontTitleLastSchool.setColor(Color.BLACK);
				chunkTitleLastSchool.setFont(fontTitleLastSchool);
				
				Paragraph paraTitleLastSchool = new Paragraph();	
				paraTitleLastSchool.setSpacingBefore(8);
				paraTitleLastSchool.add(chunkTitleLastSchool);
				paraTitleLastSchool.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleLastSchool);
				
				StringTokenizer lastSchoolToken = new StringTokenizer(lastSchoolDb);
				int k = 1;
				String lastSchool1 = "";
				String lastSchool2 = "";
				while (lastSchoolToken.hasMoreElements()) {
					if(k <= 6){
						lastSchool1 = lastSchool1+" "+lastSchoolToken.nextElement();
					}
					else{
						lastSchool2 = lastSchool2+" "+lastSchoolToken.nextElement();
					}
					k++;
				}
				
//				Chunk chunkLastSchool = new Chunk  ("                               : "+  lastSchoolDb);
				Chunk chunkLastSchool = new Chunk  ("                                                                     :       "+commonLc.FirstWordCap(lastSchool1));
				Font fontLastSchool = new Font(Font.TIMES_ROMAN);
				fontLastSchool.setStyle(Font.BOLD);
				fontLastSchool.setStyle(Font.ITALIC);
				fontLastSchool.setSize(12);
				fontLastSchool.setColor(Color.BLACK);
				chunkLastSchool.setFont(fontLastSchool);
				
				Paragraph paraLastSchool = new Paragraph();	
				paraLastSchool.setSpacingBefore(-16);
				paraLastSchool.add(chunkLastSchool);
				paraLastSchool.setAlignment(Element.ALIGN_LEFT);
				document.add(paraLastSchool);
				
				if(!lastSchool2.equalsIgnoreCase("")){
					Chunk chunkLastSchool1 = new Chunk  ("                                                                             "+commonLc.FirstWordCap(lastSchool2));
					Font fontLastSchool1 = new Font(Font.TIMES_ROMAN);
					fontLastSchool1.setStyle(Font.BOLD);
					fontLastSchool1.setStyle(Font.ITALIC);
					fontLastSchool1.setSize(12);
					fontLastSchool1.setColor(Color.BLACK);
					chunkLastSchool1.setFont(fontLastSchool1);
					
					Paragraph paraTitleLastSchool1 = new Paragraph();	
					paraTitleLastSchool1.setSpacingBefore(0);
	//				paraTitleLastSchool1.add(chunkTitleLastSchool1);
					paraTitleLastSchool1.add(chunkLastSchool1);
					paraTitleLastSchool1.setAlignment(Element.ALIGN_LEFT);
					document.add(paraTitleLastSchool1);
				}
				
				Chunk chunkTitleDOA = new Chunk("      10.   Date Of Admission");
				Font fontTitleDOA = new Font(Font.TIMES_ROMAN);
				fontTitleDOA.setStyle(Font.BOLD);
				fontTitleDOA.setStyle(Font.ITALIC);
				fontTitleDOA.setSize(12);
				fontTitleDOA.setColor(Color.BLACK);
				chunkTitleDOA.setFont(fontTitleDOA);
				
				Paragraph paraTitleDOA = new Paragraph();	
				paraTitleDOA.setSpacingBefore(8);
				paraTitleDOA.add(chunkTitleDOA);
				paraTitleDOA.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleDOA);
				
//				dateAdmittedDb = commonLc.MMM_ddmmyyy(dateAdmittedDb);
				Chunk chunkDOA = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(dateAdmittedDb));
				Font fontDOA = new Font(Font.TIMES_ROMAN);
				fontDOA.setStyle(Font.BOLD);
				fontDOA.setStyle(Font.ITALIC);
				fontDOA.setSize(12);
				fontDOA.setColor(Color.BLACK);
				chunkDOA.setFont(fontDOA);
				
				Paragraph paraDOA = new Paragraph();	
				paraDOA.setSpacingBefore(-16);
				paraDOA.add(chunkDOA);
				paraDOA.setAlignment(Element.ALIGN_LEFT);
				document.add(paraDOA);
				
				Chunk chunkTitleProgress = new Chunk("      11.   Progress");
				Font fontTitleProgress = new Font(Font.TIMES_ROMAN);
				fontTitleProgress.setStyle(Font.BOLD);
				fontTitleProgress.setStyle(Font.ITALIC);
				fontTitleProgress.setSize(12);
				fontTitleProgress.setColor(Color.BLACK);
				chunkTitleProgress.setFont(fontTitleProgress);
				
				Paragraph paraTitleProgress = new Paragraph();	
				paraTitleProgress.setSpacingBefore(8);
				paraTitleProgress.add(chunkTitleProgress);
				paraTitleProgress.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleProgress);
				
				Chunk chunkProgress = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(progressDb));
				Font fontProgress = new Font(Font.TIMES_ROMAN);
				fontProgress.setStyle(Font.BOLD);
				fontProgress.setStyle(Font.ITALIC);
				fontProgress.setSize(12);
				fontProgress.setColor(Color.BLACK);
				chunkProgress.setFont(fontProgress);
				
				Paragraph paraProgress = new Paragraph();	
				paraProgress.setSpacingBefore(-16);
				paraProgress.add(chunkProgress);
				paraProgress.setAlignment(Element.ALIGN_LEFT);
				document.add(paraProgress);
				
				/*
				Chunk chunkTitleProgress = new Chunk("Progress : ");
				Font fontTitleProgress = new Font(Font.TIMES_ROMAN);
				fontTitleProgress.setStyle(Font.BOLD);
				fontTitleProgress.setSize(12);
				fontTitleProgress.setColor(Color.BLACK);
				chunkTitleProgress.setFont(fontTitleProgress);
				
				Chunk chunkProgress = new Chunk(progressDb+"                                                                          ");
				Font fontProgress = new Font(Font.TIMES_ROMAN);
				fontProgress.setStyle(Font.NORMAL);
				fontProgress.setSize(12);
				fontProgress.setColor(Color.BLACK);
				chunkProgress.setFont(fontLcNo);
				*/
				
				Chunk chunkTitleConduct = new Chunk("      12.   Conduct");
				Font fontTitleConduct = new Font(Font.TIMES_ROMAN);
				fontTitleConduct.setStyle(Font.BOLD);
				fontTitleConduct.setStyle(Font.ITALIC);
				fontTitleConduct.setSize(12);
				fontTitleConduct.setColor(Color.BLACK);
				chunkTitleConduct.setFont(fontTitleConduct);
				
				Paragraph paraTitleConduct = new Paragraph();	
				paraTitleConduct.setSpacingBefore(8);
				paraTitleConduct.add(chunkTitleConduct);
				paraTitleConduct.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleConduct);
				
				if(conductDb.equalsIgnoreCase("") || conductDb.equalsIgnoreCase("-")){
					conductDb = conduct;
				}
				logger.info("conductDb == "+conductDb);
				Chunk chunkConduct = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(conductDb));
				Font fontConduct = new Font(Font.TIMES_ROMAN);
				fontConduct.setStyle(Font.BOLD);
				fontConduct.setStyle(Font.ITALIC);
				fontConduct.setSize(12);
				fontConduct.setColor(Color.BLACK);
				chunkConduct.setFont(fontConduct);
				
				Paragraph paraConduct = new Paragraph();	
				paraConduct.setSpacingBefore(-16);
				paraConduct.add(chunkConduct);
				paraConduct.setAlignment(Element.ALIGN_LEFT);
				document.add(paraConduct);
	//			
	//			Chunk chunkTitleConduct = new Chunk("Conduct : ");
	//			Font fontTitleConduct = new Font(Font.TIMES_ROMAN);
	//			fontTitleConduct.setStyle(Font.BOLD);
	//			fontTitleConduct.setSize(12);
	//			fontTitleConduct.setColor(Color.BLACK);
	//			chunkTitleConduct.setFont(fontTitleConduct);
	//			
	//			if(conductDb.equalsIgnoreCase("") || conductDb.equalsIgnoreCase("-")){
	//				conductDb = conduct;
	//			}
	//			Chunk chunkConduct = new Chunk(conductDb);
	//			Font fontConduct = new Font(Font.TIMES_ROMAN);
	//			fontConduct.setStyle(Font.NORMAL);
	//			fontConduct.setSize(12);
	//			fontConduct.setColor(Color.BLACK);
	//			chunkConduct.setFont(fontLcNo);
	//			
	//			Paragraph paraTitleProgress = new Paragraph();	
	//			paraTitleProgress.setSpacingBefore(5);
	//			paraTitleProgress.add(chunkTitleProgress);
	//			paraTitleProgress.add(chunkProgress);
	//			paraTitleProgress.add(chunkTitleConduct);
	//			paraTitleProgress.add(chunkConduct);
	//			paraTitleProgress.setAlignment(Element.ALIGN_LEFT);
	//			document.add(paraTitleProgress);
				
				Chunk chunkTitleDOL = new Chunk("      13.   Date Of Leaving");
				Font fontTitleDOL = new Font(Font.TIMES_ROMAN);
				fontTitleDOL.setStyle(Font.BOLD);
				fontTitleDOL.setStyle(Font.ITALIC);
				fontTitleDOL.setSize(12);
				fontTitleDOL.setColor(Color.BLACK);
				chunkTitleDOL.setFont(fontTitleDOL);
				
				Paragraph paraTitleDOL = new Paragraph();	
				paraTitleDOL.setSpacingBefore(8);
				paraTitleDOL.add(chunkTitleDOL);
				paraTitleDOL.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleDOL);
				
				if(dateLeavingDb.equalsIgnoreCase("") || dateLeavingDb.equalsIgnoreCase("-")){
					dateLeavingDb = leavingDate.toUpperCase();
				}
//				dateLeavingDb = commonLc.MMM_ddmmyyy(dateLeavingDb);
				Chunk chunkDOL = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(dateLeavingDb));
				Font fontDOL = new Font(Font.TIMES_ROMAN);
				fontDOL.setStyle(Font.BOLD);
				fontDOL.setStyle(Font.ITALIC);
				fontDOL.setSize(12);
				fontDOL.setColor(Color.BLACK);
				chunkDOL.setFont(fontDOL);
				
				Paragraph paraDOL = new Paragraph();	
				paraDOL.setSpacingBefore(-16);
				paraDOL.add(chunkDOL);
				paraDOL.setAlignment(Element.ALIGN_LEFT);
				document.add(paraDOL);
				
				Chunk chunkTitleStandard = new Chunk("      14.   STD. In Which Studying");
				Font fontTitleStandard = new Font(Font.TIMES_ROMAN);
				fontTitleStandard.setStyle(Font.BOLD);
				fontTitleStandard.setStyle(Font.ITALIC);
				fontTitleStandard.setSize(12);
				fontTitleStandard.setColor(Color.BLACK);
				chunkTitleStandard.setFont(fontTitleStandard);
				
				Paragraph paraTitleStandard = new Paragraph();	
				paraTitleStandard.setSpacingBefore(8);
				paraTitleStandard.add(chunkTitleStandard);
				paraTitleStandard.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleStandard);
				
				if(studyingSinceDb.equalsIgnoreCase("") || studyingSinceDb.equalsIgnoreCase("-")){
					studyingSinceDb = sinceDate;
				}
//				Chunk chunkSince = new Chunk("                           :"+  studyingSinceDb);
				String stdWords = "";
				if(!leavingStdDb.equalsIgnoreCase("JR.KG") && !leavingStdDb.equalsIgnoreCase("SR.KG") && !leavingStdDb.equalsIgnoreCase("")){
					stdWords = " ("+commonLc.RomanToWord(leavingStdDb)+")";
				}
				Chunk chunkStandard = new Chunk("                                                                     :       "+ leavingStdDb + commonLc.FirstWordCap(stdWords+" Since "+studyingSinceDb));
				
				Font fontStandard = new Font(Font.TIMES_ROMAN);
				fontStandard.setStyle(Font.BOLD);
				fontStandard.setStyle(Font.ITALIC);
				fontStandard.setSize(12);
				fontStandard.setColor(Color.BLACK);
				chunkStandard.setFont(fontStandard);
				
				Paragraph paraStandard = new Paragraph();	
				paraStandard.setSpacingBefore(-16);
				paraStandard.add(chunkStandard);
				paraStandard.setAlignment(Element.ALIGN_LEFT);
				document.add(paraStandard);
				
	//			Chunk chunkTitleStandard = new Chunk("Standard In Which Studying : ");
	//			Font fontTitleStandard = new Font(Font.TIMES_ROMAN);
	//			fontTitleStandard.setStyle(Font.BOLD);
	//			fontTitleStandard.setSize(12);
	//			fontTitleStandard.setColor(Color.BLACK);
	//			chunkTitleStandard.setFont(fontTitleStandard);
	//			
	//			Chunk chunkStandard = new Chunk(leavingStdDb+"                                             ");
	//			Font fontStandard = new Font(Font.TIMES_ROMAN);
	//			fontStandard.setStyle(Font.NORMAL);
	//			fontStandard.setSize(12);
	//			fontStandard.setColor(Color.BLACK);
	//			chunkStandard.setFont(fontLcNo);
				
				/*
				Chunk chunkTitleSince = new Chunk("                                                                           " +
				 "                        SINCE");
				Font fontTitleSince = new Font(Font.TIMES_ROMAN);
				fontTitleSince.setStyle(Font.BOLD);
				fontTitleSince.setSize(12);
				fontTitleSince.setColor(Color.BLACK);
				chunkTitleSince.setFont(fontTitleSince);
				*/ 
				
//				if(studyingSinceDb.equalsIgnoreCase("") || studyingSinceDb.equalsIgnoreCase("-")){
//					studyingSinceDb = sinceDate;
//				}
//				Chunk chunkSince = new Chunk("                           :"+  studyingSinceDb);
//				Font fontSince = new Font(Font.TIMES_ROMAN);
//				fontSince.setStyle(Font.NORMAL);
//				fontSince.setSize(12);
//				fontSince.setColor(Color.BLACK);
//				chunkSince.setFont(fontLcNo);
//				
//				Paragraph paraTitleSince = new Paragraph();	
//				paraTitleSince.setSpacingBefore(-16);
////				paraTitleSince.add(chunkTitleSince);
//				paraTitleSince.add(chunkSince);
//				paraTitleSince.setAlignment(Element.ALIGN_LEFT);
//				document.add(paraTitleSince);
			
				
	//			Chunk chunkTitleSince = new Chunk("Since : ");
	//			Font fontTitleSince = new Font(Font.TIMES_ROMAN);
	//			fontTitleSince.setStyle(Font.BOLD);
	//			fontTitleSince.setSize(12);
	//			fontTitleSince.setColor(Color.BLACK);
	//			chunkTitleSince.setFont(fontTitleSince);
	//			
	//			
	//			if(studyingSinceDb.equalsIgnoreCase("") || studyingSinceDb.equalsIgnoreCase("-")){
	//				studyingSinceDb = sinceDate;
	//			}
	//			Chunk chunkSince = new Chunk(studyingSinceDb);
	//			Font fontSince = new Font(Font.TIMES_ROMAN);
	//			fontSince.setStyle(Font.NORMAL);
	//			fontSince.setSize(12);
	//			fontSince.setColor(Color.BLACK);
	//			chunkSince.setFont(fontLcNo);
	//			
	//			Paragraph paraTitleStandard = new Paragraph();	
	//			paraTitleStandard.setSpacingBefore(5);
	//			paraTitleStandard.add(chunkTitleStandard);
	//			paraTitleStandard.add(chunkStandard);
	//			paraTitleStandard.add(chunkTitleSince);
	//			paraTitleStandard.add(chunkSince);
	//			paraTitleStandard.setAlignment(Element.ALIGN_LEFT);
	//			document.add(paraTitleStandard);
				
				Chunk chunkTitleReason = new Chunk("      15.   Reason Of Leaving");
				Font fontTitleReason = new Font(Font.TIMES_ROMAN);
				fontTitleReason.setStyle(Font.BOLD);
				fontTitleReason.setStyle(Font.ITALIC);
				fontTitleReason.setSize(12);
				fontTitleReason.setColor(Color.BLACK);
				chunkTitleReason.setFont(fontTitleReason);
				
				Paragraph paraTitleReason = new Paragraph();	
				paraTitleReason.setSpacingBefore(8);
				paraTitleReason.add(chunkTitleReason);
				paraTitleReason.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleReason);
				
				if(reasonDb.equalsIgnoreCase("") || reasonDb.equalsIgnoreCase("-")){
					reasonDb = reason;
				}
				logger.info("reasonDb===="+reasonDb);
				Chunk chunkReason = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(reasonDb));
				Font fontReason = new Font(Font.TIMES_ROMAN);
				fontReason.setStyle(Font.BOLD);
				fontReason.setStyle(Font.ITALIC);
				fontReason.setSize(12);
				fontReason.setColor(Color.BLACK);
				chunkReason.setFont(fontReason);
				
				Paragraph paraReason = new Paragraph();	
				paraReason.setSpacingBefore(-16);
				paraReason.add(chunkReason);
				paraReason.setAlignment(Element.ALIGN_LEFT);
				document.add(paraReason);
				
				Chunk chunkTitleRemark = new Chunk("      16.  Remark");
				Font fontTitleRemark = new Font(Font.TIMES_ROMAN);
				fontTitleRemark.setStyle(Font.BOLD);
				fontTitleRemark.setStyle(Font.ITALIC);
				fontTitleRemark.setSize(12);
				fontTitleRemark.setColor(Color.BLACK);
				chunkTitleRemark.setFont(fontTitleRemark);
				
				Paragraph paraTitleRemark = new Paragraph();	
				paraTitleRemark.setSpacingBefore(8);
//				paraTitleRemark.setSpacingAfter(9);
				paraTitleRemark.add(chunkTitleRemark);
				paraTitleRemark.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleRemark);
				
				if(remarkDb.equalsIgnoreCase("") || remarkDb.equalsIgnoreCase("-")){
					remarkDb = remark;
				}
				logger.info("remarkDb===="+remarkDb);
				logger.info("remark1Db===="+remark1Db);
				String remarkStatus = remark1Db +", "+ remarkDb;
				
				StringTokenizer remarkToken = new StringTokenizer(remarkStatus);
				int l = 1;
				String payremark = "";
				String remarkLc = "";
				while (remarkToken.hasMoreElements()) {
					if(l <= 8){
						payremark = payremark+" "+remarkToken.nextElement();
					}
					else{
						remarkLc = remarkLc+" "+remarkToken.nextElement();
					}
					l++;
				}
				
				Chunk chunkRemark = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(payremark));
				Font fontRemark = new Font(Font.TIMES_ROMAN);
				fontRemark.setStyle(Font.BOLD);
				fontRemark.setStyle(Font.ITALIC);
				fontRemark.setSize(12);
				fontRemark.setColor(Color.BLACK);
				chunkRemark.setFont(fontRemark);
				
				Paragraph paraRemark = new Paragraph();	
				paraRemark.setSpacingBefore(-16);
				paraRemark.add(chunkRemark);
				paraRemark.setAlignment(Element.ALIGN_LEFT);
				document.add(paraRemark);
				
				logger.info("remarkDb===="+remarkDb);
				Chunk chunkRemark1 = new Chunk("                                                                             "+  commonLc.FirstWordCap(remarkLc));
				Font fontRemark1 = new Font(Font.TIMES_ROMAN);
				fontRemark1.setStyle(Font.BOLD);
				fontRemark1.setStyle(Font.ITALIC);
				fontRemark1.setSize(12);
				fontRemark1.setColor(Color.BLACK);
				chunkRemark1.setFont(fontRemark1);
				
				Paragraph paraRemark1 = new Paragraph();	
//				paraRemark1.setSpacingBefore(-16);
				paraRemark1.add(chunkRemark1);
				paraRemark1.setAlignment(Element.ALIGN_LEFT);
				document.add(paraRemark1);
				
				Chunk sep2 = new Chunk(separator);
				Paragraph paraSep2 = new Paragraph();
				paraSep2.setSpacingBefore(1);
				paraSep2.add(sep2);
				paraSep2.setAlignment(Element.ALIGN_LEFT);
				document.add(paraSep2);
				
				if(!origLcDb.equalsIgnoreCase("") && !origLcDb.equalsIgnoreCase("-") && !view_save.equalsIgnoreCase("view"))
				{
	//				LineSeparator sep2 = new LineSeparator();
	//				sep2.setOffset(5);
	//				Paragraph parasep2 = new Paragraph();
	//				parasep2.setSpacingBefore(-12);
	//				parasep2.setSpacingAfter(6);
	//				parasep2.add(sep2);
	//				document.add(parasep2);
					
					Chunk chunkTitleOriginalLC = new Chunk("    Original L.C.No. : ");
					Font fontTitleOriginalLC = new Font(Font.TIMES_ROMAN);
					fontTitleOriginalLC.setStyle(Font.BOLD);
					fontTitleOriginalLC.setSize(16);
					fontTitleOriginalLC.setColor(Color.BLACK);
					chunkTitleOriginalLC.setFont(fontTitleOriginalLC);
					
					Chunk chunkOriginalLC = new Chunk(origLcNo+"         ");
					Font fontOriginalLC = new Font(Font.TIMES_ROMAN);
					fontOriginalLC.setStyle(Font.BOLD);
					fontOriginalLC.setSize(16);
					fontOriginalLC.setColor(Color.BLACK);
					chunkOriginalLC.setFont(fontOriginalLC);
					
					Paragraph paraTitleOriginalLC = new Paragraph();	
					paraTitleOriginalLC.setSpacingBefore(-1);
					paraTitleOriginalLC.add(chunkTitleOriginalLC);
					paraTitleOriginalLC.add(chunkOriginalLC);
					paraTitleOriginalLC.setAlignment(Element.ALIGN_LEFT);
					document.add(paraTitleOriginalLC);
					
	//			Chunk chunkTitleOriginalLC = new Chunk("Original L.C.No. : ");
	//			Font fontTitleOriginalLC = new Font(Font.TIMES_ROMAN);
	//			fontTitleOriginalLC.setStyle(Font.BOLD);
	//			fontTitleOriginalLC.setSize(12);
	//			fontTitleOriginalLC.setColor(Color.BLACK);
	//			chunkTitleOriginalLC.setFont(fontTitleOriginalLC);
	//			
	//			Chunk chunkOriginalLC = new Chunk(origLcNo+"                                                       ");
	//			Font fontOriginalLC = new Font(Font.TIMES_ROMAN);
	//			fontOriginalLC.setStyle(Font.NORMAL);
	//			fontOriginalLC.setSize(12);
	//			fontOriginalLC.setColor(Color.BLACK);
	//			chunkOriginalLC.setFont(fontLcNo);
					
					Chunk chunkTitleDated = new Chunk("                                                      " +
					 "     Dated : ");
					Font fontTitleDated = new Font(Font.TIMES_ROMAN);
					fontTitleDated.setStyle(Font.BOLD);
					fontTitleDated.setSize(16);
					fontTitleDated.setColor(Color.BLACK);
					chunkTitleDated.setFont(fontTitleDated);
					
//					originalLcDateDb = commonLc.MMM_ddmmyyy(originalLcDateDb);
					Chunk chunkDated = new Chunk(originalLcDateDb);
					Font fontDated = new Font(Font.TIMES_ROMAN);
					fontDated.setStyle(Font.BOLD);
					fontDated.setSize(16);
					fontDated.setColor(Color.BLACK);
					chunkDated.setFont(fontDated);
					
					Paragraph paraTitleDated = new Paragraph();	
					paraTitleDated.setSpacingBefore(-16);
					paraTitleDated.add(chunkTitleDated);
					paraTitleDated.add(chunkDated);
					paraTitleDated.setAlignment(Element.ALIGN_LEFT);
					document.add(paraTitleDated);
					
	//			Chunk chunkTitleDated = new Chunk("Dated : ");
	//			Font fontTitleDated = new Font(Font.TIMES_ROMAN);
	//			fontTitleDated.setStyle(Font.BOLD);
	//			fontTitleDated.setSize(12);
	//			fontTitleDated.setColor(Color.BLACK);
	//			chunkTitleDated.setFont(fontTitleDated);
	//			
	//			Chunk chunkDated = new Chunk(originalLcDateDb);
	//			Font fontDated = new Font(Font.TIMES_ROMAN);
	//			fontDated.setStyle(Font.NORMAL);
	//			fontDated.setSize(12);
	//			fontDated.setColor(Color.BLACK);
	//			chunkDated.setFont(fontLcNo);
	//			
	//			Paragraph paraTitleOriginalLC = new Paragraph();	
	//			paraTitleOriginalLC.setSpacingBefore(5);
	//			paraTitleOriginalLC.add(chunkTitleOriginalLC);
	//			paraTitleOriginalLC.add(chunkOriginalLC);
	//			paraTitleOriginalLC.add(chunkTitleDated);
	//			paraTitleOriginalLC.add(chunkDated);
	//			paraTitleOriginalLC.setAlignment(Element.ALIGN_LEFT);
	//			document.add(paraTitleOriginalLC);
					Chunk chunkTitleDUPLICATE1 = new Chunk("                              (DUPLICATE) "+"           ");
					Font fontTitleDUPLICATE1 = new Font(Font.TIMES_ROMAN);
					fontTitleDUPLICATE1.setStyle(Font.BOLD);
					fontTitleDUPLICATE1.setSize(16);
					fontTitleDUPLICATE1.setColor(Color.BLACK);
					chunkTitleDUPLICATE1.setFont(fontTitleDUPLICATE1);
					
					Paragraph paraTitleDUPLICATE1 = new Paragraph();	
					paraTitleDUPLICATE1.setSpacingBefore(-16);
					paraTitleDUPLICATE1.add(chunkTitleDUPLICATE1);
					paraTitleDUPLICATE1.setAlignment(Element.ALIGN_RIGHT);
					document.add(paraTitleDUPLICATE1);
					
	//				LineSeparator sep3 = new LineSeparator();
	//				sep3.setOffset(5);
	//				Paragraph parasep3 = new Paragraph();
	//				parasep3.setSpacingBefore(-3);
	//				parasep3.setSpacingAfter(6);
	//				parasep3.add(sep3);
	//				document.add(parasep3);
					
					Chunk sep3 = new Chunk(separator);
					Paragraph paraSep3 = new Paragraph();
					paraSep3.setSpacingBefore(-5);
					paraSep3.add(sep3);
					paraSep3.setAlignment(Element.ALIGN_LEFT);
					document.add(paraSep3);
				}
				else if(!duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("-"))
				{
	//				LineSeparator sep2 = new LineSeparator();
	//				sep2.setOffset(5);
	//				Paragraph parasep2 = new Paragraph();
	//				parasep2.setSpacingBefore(-12);
	//				parasep2.setSpacingAfter(6);
	//				parasep2.add(sep2);
	//				document.add(parasep2);
					
					Chunk chunkTitleOriginalLC = new Chunk("    Original L.C.No. : ");
					Font fontTitleOriginalLC = new Font(Font.TIMES_ROMAN);
					fontTitleOriginalLC.setStyle(Font.BOLD);
					fontTitleOriginalLC.setSize(16);
					fontTitleOriginalLC.setColor(Color.BLACK);
					chunkTitleOriginalLC.setFont(fontTitleOriginalLC);
					
					Chunk chunkOriginalLC = new Chunk(origLcNo+"         ");
					Font fontOriginalLC = new Font(Font.TIMES_ROMAN);
					fontOriginalLC.setStyle(Font.BOLD);
					fontOriginalLC.setSize(16);
					fontOriginalLC.setColor(Color.BLACK);
					chunkOriginalLC.setFont(fontOriginalLC);
					
					Paragraph paraTitleOriginalLC = new Paragraph();	
					paraTitleOriginalLC.setSpacingBefore(-1);
					paraTitleOriginalLC.add(chunkTitleOriginalLC);
					paraTitleOriginalLC.add(chunkOriginalLC);
					paraTitleOriginalLC.setAlignment(Element.ALIGN_LEFT);
					document.add(paraTitleOriginalLC);
					
	//			Chunk chunkTitleOriginalLC = new Chunk("Original L.C.No. : ");
	//			Font fontTitleOriginalLC = new Font(Font.TIMES_ROMAN);
	//			fontTitleOriginalLC.setStyle(Font.BOLD);
	//			fontTitleOriginalLC.setSize(12);
	//			fontTitleOriginalLC.setColor(Color.BLACK);
	//			chunkTitleOriginalLC.setFont(fontTitleOriginalLC);
	//			
	//			Chunk chunkOriginalLC = new Chunk(origLcNo+"                                                       ");
	//			Font fontOriginalLC = new Font(Font.TIMES_ROMAN);
	//			fontOriginalLC.setStyle(Font.NORMAL);
	//			fontOriginalLC.setSize(12);
	//			fontOriginalLC.setColor(Color.BLACK);
	//			chunkOriginalLC.setFont(fontLcNo);
					
					Chunk chunkTitleDated = new Chunk("                                                      " +
					 "     Dated : ");
					Font fontTitleDated = new Font(Font.TIMES_ROMAN);
					fontTitleDated.setStyle(Font.BOLD);
					fontTitleDated.setSize(16);
					fontTitleDated.setColor(Color.BLACK);
					chunkTitleDated.setFont(fontTitleDated);
					
//					originalLcDateDb = commonLc.MMM_ddmmyyy(originalLcDateDb);
					Chunk chunkDated = new Chunk(originalLcDateDb);
					Font fontDated = new Font(Font.TIMES_ROMAN);
					fontDated.setStyle(Font.BOLD);
					fontDated.setSize(16);
					fontDated.setColor(Color.BLACK);
					chunkDated.setFont(fontDated);
					
					Paragraph paraTitleDated = new Paragraph();	
					paraTitleDated.setSpacingBefore(-16);
					paraTitleDated.add(chunkTitleDated);
					paraTitleDated.add(chunkDated);
					paraTitleDated.setAlignment(Element.ALIGN_LEFT);
					document.add(paraTitleDated);
					
	//			Chunk chunkTitleDated = new Chunk("Dated : ");
	//			Font fontTitleDated = new Font(Font.TIMES_ROMAN);
	//			fontTitleDated.setStyle(Font.BOLD);
	//			fontTitleDated.setSize(12);
	//			fontTitleDated.setColor(Color.BLACK);
	//			chunkTitleDated.setFont(fontTitleDated);
	//			
	//			Chunk chunkDated = new Chunk(originalLcDateDb);
	//			Font fontDated = new Font(Font.TIMES_ROMAN);
	//			fontDated.setStyle(Font.NORMAL);
	//			fontDated.setSize(12);
	//			fontDated.setColor(Color.BLACK);
	//			chunkDated.setFont(fontLcNo);
	//			
	//			Paragraph paraTitleOriginalLC = new Paragraph();	
	//			paraTitleOriginalLC.setSpacingBefore(5);
	//			paraTitleOriginalLC.add(chunkTitleOriginalLC);
	//			paraTitleOriginalLC.add(chunkOriginalLC);
	//			paraTitleOriginalLC.add(chunkTitleDated);
	//			paraTitleOriginalLC.add(chunkDated);
	//			paraTitleOriginalLC.setAlignment(Element.ALIGN_LEFT);
	//			document.add(paraTitleOriginalLC);
					Chunk chunkTitleDUPLICATE1 = new Chunk("                              (DUPLICATE) "+"           ");
					Font fontTitleDUPLICATE1 = new Font(Font.TIMES_ROMAN);
					fontTitleDUPLICATE1.setStyle(Font.BOLD);
					fontTitleDUPLICATE1.setSize(16);
					fontTitleDUPLICATE1.setColor(Color.BLACK);
					chunkTitleDUPLICATE1.setFont(fontTitleDUPLICATE1);
					
					Paragraph paraTitleDUPLICATE1 = new Paragraph();	
					paraTitleDUPLICATE1.setSpacingBefore(-16);
					paraTitleDUPLICATE1.add(chunkTitleDUPLICATE1);
					paraTitleDUPLICATE1.setAlignment(Element.ALIGN_RIGHT);
					document.add(paraTitleDUPLICATE1);
					
	//				LineSeparator sep3 = new LineSeparator();
	//				sep3.setOffset(5);
	//				Paragraph parasep3 = new Paragraph();
	//				parasep3.setSpacingBefore(-3);
	//				parasep3.setSpacingAfter(6);
	//				parasep3.add(sep3);
	//				document.add(parasep3);
					
					Chunk sep3 = new Chunk(separator);
					Paragraph paraSep3 = new Paragraph();
					paraSep3.setSpacingBefore(-5);
					paraSep3.add(sep3);
					paraSep3.setAlignment(Element.ALIGN_LEFT);
					document.add(paraSep3);
				}
					
				Chunk chunkCertified = new Chunk("     Certified that the above information is in accordance with the School Register.");
				Font fontCertified = new Font(Font.TIMES_ROMAN);
				fontCertified.setStyle(Font.NORMAL);
				fontCertified.setSize(12);
				fontCertified.setColor(Color.BLACK);
				chunkCertified.setFont(fontCertified);
				Paragraph paraCertified = new Paragraph();
				paraCertified.setSpacingBefore(-2);
//				if(!duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("-") && !duplicateLcDb.equalsIgnoreCase(" ")){
//					paraCertified.setSpacingAfter(20);
//				}else{
//					paraCertified.setSpacingAfter(43);
//				}
				paraCertified.add(chunkCertified);
				paraCertified.setAlignment(Element.ALIGN_LEFT);
				document.add(paraCertified);
				
				Chunk chunkTitleDate = new Chunk("     Date : ");
				Font fontTitleDate = new Font(Font.TIMES_ROMAN);
				fontTitleDate.setStyle(Font.BOLD);
				fontTitleDate.setSize(12);
				fontTitleDate.setColor(Color.BLACK);
				chunkTitleDate.setFont(fontTitleDate);
				
//				issueDt = commonLc.MMM_ddmmyyy(issueDt);
				Chunk chunkDate = new Chunk(issueDt);
				Font fontDate = new Font(Font.TIMES_ROMAN);
				fontDate.setStyle(Font.NORMAL);
				fontDate.setSize(12);
				fontDate.setColor(Color.BLACK);
				chunkDate.setFont(fontDate);
				
				Paragraph paraTitleDate = new Paragraph();	
				paraTitleDate.setSpacingBefore(3);
				paraTitleDate.add(chunkTitleDate);
				paraTitleDate.add(chunkDate);
				paraTitleDate.setAlignment(Element.ALIGN_LEFT);
				document.add(paraTitleDate);
				
				int preparedByHeight = 70;
//				if(!dobWords2.equalsIgnoreCase("")){
//					preparedByHeight = preparedByHeight - 4;
//				}
				logger.info("dobWords2:"+dobWords2);
				logger.info("duplicateLcDb:"+duplicateLcDb);
				
				String test = "test";
				//single dob with duplicate
				if(dobWords2.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("-") && !duplicateLcDb.equalsIgnoreCase(" ")){
					preparedByHeight = preparedByHeight - 18;
					test = test+"1";
					logger.info("test:"+test+preparedByHeight);
				}
				//single dob with original
				else if(dobWords2.equalsIgnoreCase("") && (duplicateLcDb.equalsIgnoreCase("") || duplicateLcDb.equalsIgnoreCase("-") || duplicateLcDb.equalsIgnoreCase(" "))){
					preparedByHeight = preparedByHeight - 13;
					test = test+"2";
					logger.info("test:"+test+preparedByHeight);
				}
				//double dob with duplicate
				else if(!dobWords2.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("-") && !duplicateLcDb.equalsIgnoreCase(" ")){
					preparedByHeight = preparedByHeight - 35;
					test = test+"3";
					logger.info("test:"+test+preparedByHeight);
				}
				//double dob with original
				else if(!dobWords2.equalsIgnoreCase("") && (duplicateLcDb.equalsIgnoreCase("") || duplicateLcDb.equalsIgnoreCase("-") || duplicateLcDb.equalsIgnoreCase(" "))){
					preparedByHeight = preparedByHeight - 20;
					test = test+"4";
					logger.info("test:"+test+preparedByHeight);
				}
					
				Chunk chunkPrepared = new Chunk("     Prepared By");
				Font fontPrepared = new Font(Font.TIMES_ROMAN);
				fontPrepared.setStyle(Font.NORMAL);
				fontPrepared.setSize(12);
				fontPrepared.setColor(Color.BLACK);
				chunkPrepared.setFont(fontPrepared);
				Paragraph paraPrepared = new Paragraph();
				paraPrepared.setSpacingBefore(preparedByHeight);
				paraPrepared.add(chunkPrepared);
				paraPrepared.setAlignment(Element.ALIGN_LEFT);
				document.add(paraPrepared);
				
				Chunk chunkChecked = new Chunk("Checked By");
				Font fontChecked = new Font(Font.TIMES_ROMAN);
				fontChecked.setStyle(Font.NORMAL);
				fontChecked.setSize(12);
				fontChecked.setColor(Color.BLACK);
				chunkChecked.setFont(fontChecked);
				Paragraph paraChecked = new Paragraph();
				paraChecked.setSpacingBefore(-16);
				paraChecked.add(chunkChecked);
				paraChecked.setAlignment(Element.ALIGN_CENTER);
				document.add(paraChecked);
				
				Chunk chunkPrincipal = new Chunk("Principal");
				Font fontPrincipal = new Font(Font.TIMES_ROMAN);
				fontPrincipal.setStyle(Font.NORMAL);
				fontPrincipal.setSize(12);
				fontPrincipal.setColor(Color.BLACK);
				chunkPrincipal.setFont(fontPrincipal);
				Paragraph paraPrincipal = new Paragraph();	
				paraPrincipal.setSpacingBefore(-16);
				paraPrincipal.add(chunkPrincipal);
				paraPrincipal.setAlignment(Element.ALIGN_RIGHT);
				document.add(paraPrincipal);
				
				conductDb = commonLc.replaceComma(conductDb);
				reasonDb  = commonLc.replaceComma(reasonDb);
				remarkDb  = commonLc.replaceComma(remarkDb);
				
				logger.info("selectedLcList ==>  "+grDb+"|*"+origLcNo+"||*"+conductDb+"|||*"+dateLeavingDb+"||||*"+reasonDb+"|||||*"+remarkDb+"||||||*"+duplicateLcNo+"|||||||*"+duplicateLcDate+"||||||||*"+originalLcDate+"|||||||||*"+studyingSinceDb+"||||||||||*"+leavingStdDb);
				selectedLcList.add(grDb+"|*"+origLcNo+"||*"+conductDb+"|||*"+dateLeavingDb+"||||*"+reasonDb+"|||||*"+remarkDb+"||||||*"+duplicateLcNo+"|||||||*"+duplicateLcDate+"||||||||*"+originalLcDate+"|||||||||*"+studyingSinceDb+"||||||||||*"+leavingStdDb);
	    		
				// start second page
				//if(recLCList.size()-i==1)
				document.newPage();
	    			
		    }
		    document.close();
			
		    logger.info("view_save => "+view_save);
		//////insert into database//////////////
						if(view_save.equalsIgnoreCase("save")){
							boolean updateLc = false;
							try {
//								updateLc = findStudentDB.updateLC(grDb, origLcNo, conductDb, dateLeavingDb, reasonDb, remarkDb, duplicateLcNo, duplicateLcDate, originalLcDate, studyingSinceDb, leavingStdDb);
//								updateLc = findStudentDB.updateLC(selectedLcList,section);
//								logger.info("LC No."+origLcNo+" created for Gr No.: "+grDb);
							} catch (Exception e) {
								logger.info("updateLC Exception == "+e);
							}
							if(!updateLc){
//								JOptionPane.showMessageDialog(null, "LC not created for Gr No.: "+grDb); 
								JOptionPane.showMessageDialog(null, "Updation of LC failed..."); 
							}
						}
						///////////////////
			if(view_save.equalsIgnoreCase("validate") || view_save.equalsIgnoreCase("view")){
				try {
					PdfReader reader;
					PdfStamper stamper;
					String fileNameView 	= "LeavingCertificate"+view_save+commonLc.timeInMillis()+".pdf";
					String fileAddressView = path+fileNameView;
					
					reader 		= new PdfReader(fileAddress);
					stamper 	= new PdfStamper(reader, new FileOutputStream(fileAddressView));
					
					if(view_save.equalsIgnoreCase("validate"))
						stamper.setEncryption(null, null, PdfWriter.HideToolbar, PdfWriter.STRENGTH40BITS);
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
//			else{
//				fileName 	= "LeavingCertificatePrint.pdf";
//				fileAddress = "D:\\yash\\LeavingCertificatePrint.pdf";
//			}
			
		} catch (FileNotFoundException e) {
			fileOpenFlag = true;
			logger.info("FileNotFound Exception == "+e);
			JOptionPane.showMessageDialog(null, "Please close "+fileName+" and try again"); 
		} catch (DocumentException e) {
			logger.info("last Exception == "+e);
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
				logger.info(":: -----Exception---- ::\n"+e);
			}
		}
	}
}
