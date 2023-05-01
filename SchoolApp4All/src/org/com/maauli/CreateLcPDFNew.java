package org.com.maauli;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import com.itextpdf.text.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;
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
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.log.Log;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
//import com.itextpdf.text.pdf.draw.LineSeparator;

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
import java.util.TreeMap;

import org.apache.log4j.Logger;


public class CreateLcPDFNew {
	DBValidate findStudentDB = new DBValidate();
	Common commonLc = new Common();
	static String leavingDate 		= "";
	static String issueDate 		= "";
	static String reason 			= ""; 
	static String remark 			= "";
	static String remark2 			= "";
	static String progress 			= "";
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
	static String lcTypeClass		= "";
	static String admittedToken		= "";
	static String stdSinceToken		= "";
	static boolean lcDiscrepancyFlag		= false;
    ResourceBundle bundle    		= ResourceBundle.getBundle("org.com.accesser.school"); 
    static Logger logger 			= Logger.getLogger(CreateLcPDFNew.class.getName());
    TreeMap<String, String> progressMap = new TreeMap<String, String>();
    
	public void LeavingCertificate(SessionData sessionData, String view_save, String conductTxt, String leavingDt, String issueDt, String reasonT, 
			String remarkT, List<String> passGrList,String sec, String lcType, String retUserName, String retUserRole, String progressT, String remark2T, 
			String academic, String feeStatus, String retStd, String retDiv, String medium) throws DocumentException{
		
		logger.info("Inside LeavingCertificate pdf constructor..............");
		fileOpenFlag	= false;
		admittedToken = "";
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
    	lcTypeClass = lcType;
    	String headMaster = bundle.getString(sessionData.getDBName().toUpperCase()+"_PDF_FOOTER");
    	String pdf_header_img_flag = bundle.getString("PDF_HEADER_IMAGE_FLAG");
    	String img_path = bundle.getString("IMAGE_PATH");
    	String pdf_header_img_path = bundle.getString("IMAGE_PDF_HEADER_"+sessionData.getAppType());
		int image_pdf_pos_x = Integer.parseInt(bundle.getString("IMAGE_PDF_POS_X"));
		int image_pdf_pos_y = Integer.parseInt(bundle.getString("IMAGE_PDF_POS_Y"));
		float image_pdf_scalepercent = Float.parseFloat(bundle.getString("IMAGE_PDF_SCALEPERCENT"));
		boolean lc_count_db = Boolean.parseBoolean(bundle.getString("LC_COUNT_DB"));
    	
    	////////////////try block added for chabildas ls issue showing 2016-17///////
    	try{
			lcDiscrepancyFlag = Boolean.valueOf(bundle.getString("LC_DISCREPANCY_"+sessionData.getAppType()));
		}
		catch(MissingResourceException m){
			logger.warn("LC_DISCREPANCY flag missing");
		}
    	
		List<String> recLCList = new ArrayList();
		
		String separator ="----------------------------------------------------------------------------------------------------------------------------------";
		leavingDate 	= leavingDt;
		if(leavingDate.contains("/")){
			leavingDate = commonLc.formatdd_MM_yyyy(leavingDate);
		}
		issueDate 		= issueDt;
		reason 			= reasonT; 
		reason 			= reason.replace("*", "'");
		remark 			= remarkT;
		remark2 		= remark2T;
		conduct         = conductTxt;
		progress        = progressT;
		logger.info("view_save :: "+view_save);
		logger.info("leavingDate :: "+leavingDate);
		logger.info("remark :: "+remark);
		logger.info("conduct :: "+conduct);
		logger.info("issueDate :: "+issueDate);
		logger.info("reason :: "+reason);
		logger.info("remark2 :: "+remark2);
		logger.info("progress :: "+progress);
		path = commonLc.createTodayFolder(commonLc.getDriveName() + bundle.getString("LC_PDF_PATH_"+sessionData.getDBName()),true)+"/";
		
		int lcCountYear = 0;
		int dupLcCountYear = 0;
		int tripLcCountYear = 0;
		
		
		try {
				if(findStudentDB.connectDatabase(sessionData)){
					findStudentDB.alterStudyingSince(sessionData);
					recLCList = findStudentDB.findStudentLC(sessionData, passGrList,section,academic);
					if(progress.equalsIgnoreCase("Based on Result")){
						progressMap = findStudentDB.getProgressBasedOnResult(sessionData, retStd, retDiv, academic, passGrList);
					}
					if(view_save.equalsIgnoreCase("VALIDATE") && !lcType.equalsIgnoreCase("Update") && !lc_count_db){
						lcCountYear = findStudentDB.getLastCount(sessionData,"HS_GENERAL_REGISTER", "ORIGINAL_LC");
						dupLcCountYear = findStudentDB.getLastCount(sessionData,"HS_GENERAL_REGISTER", "DUPLICATE_LC");
						tripLcCountYear = findStudentDB.getLastCount(sessionData,"HS_GENERAL_REGISTER", "TRIPLICATE_LC");
					}
					else if(view_save.equalsIgnoreCase("VALIDATE") && !lcType.equalsIgnoreCase("Update") && lc_count_db){
						lcCountYear = findStudentDB.updateCountData(sessionData, academic, sessionData.getSectionName(), "ORIGINAL_LC","VALIDATE")-1;
						dupLcCountYear = findStudentDB.updateCountData(sessionData, academic, sessionData.getSectionName(), "DUPLICATE_LC","VALIDATE")-1;
						tripLcCountYear = findStudentDB.updateCountData(sessionData, academic, sessionData.getSectionName(), "TRIPLICATE_LC","VALIDATE")-1;
					}
		
				try {
		
				///////start of pdf creation/////////////////////
					fileName  = "LeavingCertificatePrint"+commonLc.timeInMillis()+".pdf";
					fileAddress = path+fileName;
					File file = new File(fileAddress);
					FileOutputStream fileout = new FileOutputStream(file);
					Document document = new Document(com.itextpdf.text.PageSize.A4);
//					PdfWriter.getInstance(document, fileout);
					PdfWriter writer = PdfWriter.getInstance(document, fileout);
					document.addHeader("ABC", "XYZ");
//					document.addAuthor("MAAULI SOFTWARE SOLUTIONS");
					document.addTitle("Leaving Certificate");
					document.open();
					
					//font for unicode to marathi
				    final String FONT = "resources/fonts/FreeSans.ttf";
					Font f = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			        
					Font fontClass1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
					fontClass1.setColor(BaseColor.BLACK);
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
					 
				    for(int i=0;i<recLCList.size(); i++){
				    	logger.info(recLCList.get(i));
				    	admittedToken = bundle.getString("ADMITTED_"+sessionData.getDBName()+"_"+sec);
				    	stdSinceToken = bundle.getString("STD_SINCE_"+sessionData.getDBName()+"_"+sec);
				    	
				    	StringTokenizer st1 = new StringTokenizer(recLCList.get(i), "|");
				    	List<String> LCDetaillist = new ArrayList();
				    	while(st1.hasMoreTokens()){
				    		LCDetaillist.add(st1.nextToken());
				    	}
				    	String grDb = LCDetaillist.get(0);
						String origLcDb = LCDetaillist.get(1);
						String nameDb = LCDetaillist.get(2);
						String motherNameDb = LCDetaillist.get(3);
						if(motherNameDb.equalsIgnoreCase("-")){
							motherNameDb = "-----";
						}
						String nationalityDb = LCDetaillist.get(4);
						if(nationalityDb.equalsIgnoreCase("-")){
							nationalityDb = "-----";
						}
						String religionDb = LCDetaillist.get(5);
						if(religionDb.equalsIgnoreCase("-")){
							religionDb = "-----";
						}
						String castDb = LCDetaillist.get(6);
						if(castDb.equalsIgnoreCase("-")){
							castDb = "-----";
						}
						String dobDb = LCDetaillist.get(7);
						String dobWordsDb = LCDetaillist.get(8);
						if(dobWordsDb.equalsIgnoreCase("-")){
							dobWordsDb = "-----";
						}
						String lastSchoolDb = LCDetaillist.get(9);
						lastSchoolDb = commonLc.revertCommaApostrophy(lastSchoolDb);
						if(lastSchoolDb.equalsIgnoreCase("-")){
							lastSchoolDb = "-----";
						}
						String dateAdmittedDb = LCDetaillist.get(10);
						String progressDb = LCDetaillist.get(11);
						String conductDb = LCDetaillist.get(12);
						String dateLeavingDb = LCDetaillist.get(13);
						String leavingStdDb = LCDetaillist.get(14);
						String reasonDb = LCDetaillist.get(15);
						String remarkDb = LCDetaillist.get(16);
						String duplicateLcDb = LCDetaillist.get(17);
						String originalLcDateDb = LCDetaillist.get(18);
						String duplicateLcDateDb = LCDetaillist.get(19);
						String studyingSinceDb = LCDetaillist.get(20);
						String studyingSinceTmp = studyingSinceDb;
						String currentStdDb = LCDetaillist.get(21);
						String birthPlaceDb = LCDetaillist.get(22);
						birthPlaceDb = commonLc.revertCommaApostrophy(birthPlaceDb);
						String feeStatusDb = LCDetaillist.get(23);
						String triplicateLcDb = LCDetaillist.get(24);
						String triplicateLcDateDb = LCDetaillist.get(25);
						String lcIssueCount1Db = LCDetaillist.get(26);
						String lcadmittedStdDb = LCDetaillist.get(27);
						String suidDb = LCDetaillist.get(28);
						if(suidDb.equalsIgnoreCase("-")){
							suidDb = "-----";
						}
						String subCasteDb = LCDetaillist.get(29);
						if(subCasteDb.equalsIgnoreCase("-")){
							subCasteDb = "-----";
						}
						String talukaDb = LCDetaillist.get(30);
						String districtDb = LCDetaillist.get(31);
						String stateDb = LCDetaillist.get(32);
						String countryDb = LCDetaillist.get(33);
						String adhaarCardDb = LCDetaillist.get(34);
						if(adhaarCardDb.equalsIgnoreCase("-")){
							adhaarCardDb = "-----";
						}
						String mother_tongue = LCDetaillist.get(35);
						String mediumDb = LCDetaillist.get(36);
						String admittedStdBranch = LCDetaillist.get(37);
						String duplicateLcNo ="";
						String duplicateLcDate ="";
						String sinceDate = "";
						String LcCount =  "";
						String newLcNo = "";
						String showOrigLcNo = "";
						String showDupLcNo = "";
						String showNewLcNo = "";
						String origLcNo = "";
						String originalLcDate = "";
						String todayDate = "";
						String AdmittedStd2 = "";
						
//						if(studyingSinceDb.equalsIgnoreCase("") || studyingSinceDb.equalsIgnoreCase("NA") || studyingSinceDb.equalsIgnoreCase("-") || 
//								studyingSinceDb.equalsIgnoreCase("null") || !currentStdDb.equalsIgnoreCase(leavingStdDb) || 
//								!studyingSinceTmp.contains(studyingSinceDb)){
							String studyingSinceAcad = findStudentDB.getStudyingSinceAcad(sessionData, currentStdDb, grDb, academic);
							studyingSinceDb		= commonLc.getStudyingSince(dateAdmittedDb, studyingSinceAcad);
//						}
						
						progressDb = commonLc.retrieveComma(progressDb);
						conductDb = commonLc.retrieveComma(conductDb);
						reasonDb = commonLc.retrieveComma(reasonDb);
						reasonDb = reasonDb.replace("*", "'");
						remarkDb = commonLc.retrieveComma(remarkDb);
						feeStatusDb = commonLc.retrieveComma(feeStatusDb);
						logger.info("originalLcDateDb before:::"+origLcDb);
						if(originalLcDateDb.equalsIgnoreCase("00-00-0000") || originalLcDateDb.equalsIgnoreCase("NA") || originalLcDateDb.equalsIgnoreCase("-") 
								|| originalLcDateDb.equalsIgnoreCase("null") || originalLcDateDb == null){
							originalLcDateDb = "";
						}
		//				else if(!originalLcDateDb.equalsIgnoreCase("") && !originalLcDateDb.equalsIgnoreCase("NA")){
		//					originalLcDateDb = commonLc.MMM_ddmmyyy(originalLcDateDb);
		//				}
						logger.info("originalLcDateDb:::"+originalLcDateDb);
						logger.info("originalLcDateDb=="+originalLcDateDb);
						
						if(leavingStdDb.equalsIgnoreCase("") || leavingStdDb.equalsIgnoreCase("NA") || leavingStdDb.equalsIgnoreCase("-") 
								|| leavingStdDb.equalsIgnoreCase("null") || leavingStdDb == null){
							leavingStdDb = currentStdDb;
						}
		
						Date date = new Date();
						SimpleDateFormat today = new SimpleDateFormat("dd/MM/yyyy");
						todayDate = today.format(date);
						
						if(view_save.equalsIgnoreCase("SAVE")){
							logger.info("==save==");
							if(!origLcDb.equalsIgnoreCase("") && !origLcDb.equalsIgnoreCase("NA")  && !origLcDb.equalsIgnoreCase("-")
									&& !origLcDb.equalsIgnoreCase("null") && origLcDb != null){
								lcType  = "ORIGINAL";
								origLcNo = origLcDb;
								originalLcDate = originalLcDateDb;
								newLcNo = origLcDb;
								leavingDate = dateLeavingDb;
								showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(leavingDate);
								
								logger.info("original showNewLcNo=="+showNewLcNo);
										
								if(!duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("NA") && !duplicateLcDb.equalsIgnoreCase("-")
										&& !duplicateLcDb.equalsIgnoreCase("null") && duplicateLcDb != null){
									lcType  = "DUPLICATE";
									newLcNo = duplicateLcDb;
									leavingDate = duplicateLcDateDb;
									showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(leavingDate);
									showOrigLcNo = origLcNo + "/" + commonLc.getAcademicYear(dateLeavingDb);
									logger.info("duplicate showNewLcNo =="+showNewLcNo);
									
									if(!triplicateLcDb.equalsIgnoreCase("") && !triplicateLcDb.equalsIgnoreCase("NA") && !triplicateLcDb.equalsIgnoreCase("-")
											&& !triplicateLcDb.equalsIgnoreCase("null") && triplicateLcDb != null){
										lcType  = "TRIPLICATE";
										newLcNo = triplicateLcDb;
										leavingDate = triplicateLcDateDb;
										
										showNewLcNo =  triplicateLcDb + "/" + commonLc.getAcademicYear(triplicateLcDateDb);
										showOrigLcNo = origLcDb + "/" + commonLc.getAcademicYear(dateLeavingDb);
										showDupLcNo = duplicateLcDb + "/" + commonLc.getAcademicYear(duplicateLcDateDb);
										logger.info("triplicate showNewLcNo =="+showNewLcNo);
									}
								}
							}
						}
						else if(view_save.equalsIgnoreCase("VALIDATE")){
							logger.info("==VALIDATE LC==");
							if(lcTypeClass.equalsIgnoreCase("Update")){
								if(!triplicateLcDb.equalsIgnoreCase("") && !triplicateLcDb.equalsIgnoreCase("NA") && !triplicateLcDb.equalsIgnoreCase("-")
										&& !triplicateLcDb.equalsIgnoreCase("null") && triplicateLcDb != null){
									lcType  = "TRIPLICATE";
									newLcNo = triplicateLcDb;
									showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(triplicateLcDateDb);
									showOrigLcNo = origLcDb + "/" + commonLc.getAcademicYear(dateLeavingDb);
									showDupLcNo = duplicateLcDb + "/" + commonLc.getAcademicYear(duplicateLcDateDb);
								}
								else if(!duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("NA") && !duplicateLcDb.equalsIgnoreCase("-")
										&& !duplicateLcDb.equalsIgnoreCase("null") && duplicateLcDb != null){
									lcType  = "DUPLICATE";
									newLcNo = duplicateLcDb;
									showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(duplicateLcDateDb);
									showOrigLcNo = origLcDb + "/" + commonLc.getAcademicYear(dateLeavingDb);
								}
								else if(!origLcDb.equalsIgnoreCase("") && !origLcDb.equalsIgnoreCase("NA") && !origLcDb.equalsIgnoreCase("-")
										&& !origLcDb.equalsIgnoreCase("null") && origLcDb != null){
									lcType  = "ORIGINAL";
									newLcNo = origLcDb;
									showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(dateLeavingDb);
								}
							}
							else if(origLcDb.equalsIgnoreCase("") || origLcDb.equalsIgnoreCase("NA") || origLcDb.equalsIgnoreCase("-")
									|| origLcDb.equalsIgnoreCase("null") || origLcDb == null){
								lcType  = "ORIGINAL";
								lcCountYear = lcCountYear +1;
								newLcNo =  String.format("%04d", lcCountYear);
								showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(leavingDate);
								logger.info("New LC No.: "+newLcNo);
								origLcNo = newLcNo;
								originalLcDate = leavingDate;
//								showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(leavingDate);
								logger.info("original showNewLcNo in VALIDATE =="+showNewLcNo);
							}
							else if((duplicateLcDb.equalsIgnoreCase("") || duplicateLcDb.equalsIgnoreCase("NA") || duplicateLcDb.equalsIgnoreCase("-")
									|| duplicateLcDb.equalsIgnoreCase("null") || duplicateLcDb == null) && !lcTypeClass.equalsIgnoreCase("Update")){
								dupLcCountYear = dupLcCountYear +1;
								lcType  = "DUPLICATE";
								newLcNo = String.format("%04d", dupLcCountYear);
								showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(leavingDate);
								logger.info("original showNewLcNo=="+showNewLcNo);
								showOrigLcNo = origLcDb + "/" + commonLc.getAcademicYear(dateLeavingDb);
								logger.info("duplicate showOrigLcNo=="+showOrigLcNo);
							}
							else if(triplicateLcDb.equalsIgnoreCase("") || triplicateLcDb.equalsIgnoreCase("NA") || triplicateLcDb.equalsIgnoreCase("-")
									|| triplicateLcDb.equalsIgnoreCase("null") || triplicateLcDb == null){
								tripLcCountYear = tripLcCountYear +1;
								lcType  = "TRIPLICATE";
								newLcNo = String.format("%04d", tripLcCountYear);
								showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(leavingDate);
								logger.info("original showNewLcNo=="+showNewLcNo);
								showOrigLcNo = origLcDb + "/" + commonLc.getAcademicYear(dateLeavingDb);
								logger.info("showOrigLcNo=="+showOrigLcNo);
								showDupLcNo = duplicateLcDb + "/" + commonLc.getAcademicYear(duplicateLcDateDb);
								logger.info("showdupLcNo=="+showDupLcNo);
							}
							
						}
						else if(view_save.equalsIgnoreCase("VIEW")){
							logger.info("==View==");
							if(!origLcDb.equalsIgnoreCase("") && !origLcDb.equalsIgnoreCase("NA") && !origLcDb.equalsIgnoreCase("-") 
									&& !origLcDb.equalsIgnoreCase("null") && origLcDb != null){
								lcType  = "ORIGINAL";
								leavingDate = dateLeavingDb;
								showNewLcNo =  origLcDb + "/" + commonLc.getAcademicYear(leavingDate);
								
								////////////////
								if(lcDiscrepancyFlag && commonLc.getAcademicYear(leavingDate).equalsIgnoreCase("2016-17")){
									///this condition is added for chabildas lc issue showing 2016-17
									showNewLcNo =  origLcDb + "/2017-18";
								}
								////////////////////
								logger.info("original showNewLcNo=="+showNewLcNo);
										
								if(!duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("NA") && !duplicateLcDb.equalsIgnoreCase("-")
										&& !duplicateLcDb.equalsIgnoreCase("null") && duplicateLcDb != null){
									lcType  = "DUPLICATE";
									leavingDate = duplicateLcDateDb;
									showNewLcNo =  duplicateLcDb + "/" + commonLc.getAcademicYear(duplicateLcDateDb);
									showOrigLcNo = origLcDb + "/" + commonLc.getAcademicYear(dateLeavingDb);
									logger.info("duplicate showNewLcNo=="+showNewLcNo);
									
									if(!triplicateLcDb.equalsIgnoreCase("") && !triplicateLcDb.equalsIgnoreCase("NA") && !triplicateLcDb.equalsIgnoreCase("-")
											&& !triplicateLcDb.equalsIgnoreCase("null") && triplicateLcDb != null){
										lcType  = "TRIPLICATE";
										leavingDate = triplicateLcDateDb;
										showNewLcNo =  triplicateLcDb + "/" + commonLc.getAcademicYear(triplicateLcDateDb);
										showOrigLcNo = origLcDb + "/" + commonLc.getAcademicYear(dateLeavingDb);
										showDupLcNo = duplicateLcDb + "/" + commonLc.getAcademicYear(duplicateLcDateDb);
										logger.info("duplicate showNewLcNo=="+showNewLcNo);
									}
								}
							}
						}
		//				if(!view_save.equalsIgnoreCase("SAVE") && !origLcDb.equalsIgnoreCase("") && !origLcDb.equalsIgnoreCase("NA") && 
		//						!origLcDb.equalsIgnoreCase(null)){
		//					origLcNo = origLcDb;
		//					originalLcDate = originalLcDateDb;
		//					newLcNo = origLcDb;
		//					leavingDate = originalLcDateDb;
		//					showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(leavingDate);
		//					logger.info("original showNewLcNo=="+showNewLcNo);
		//					
		//					if(!duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("NA")){
		//						newLcNo = duplicateLcDb;
		//						leavingDate = duplicateLcDateDb;
		//						showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(leavingDate);
		//						logger.info("duplicate showNewLcNo=="+showNewLcNo);
		//					}
		//				}
		//				else if(!origLcDb.equalsIgnoreCase("") && !origLcDb.equalsIgnoreCase("NA")){
		//					origLcNo = origLcDb;
		//					originalLcDate = originalLcDateDb;
		//					dupLcCountYear = dupLcCountYear +1;
		//					String dupLcCount =  String.format("%03d", dupLcCountYear);
		//					newLcNo = (dupLcCount);
		//					duplicateLcNo = newLcNo;
		//					duplicateLcDate = leavingDate;
		//					showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(leavingDate);
		//				}
						
						logger.info("origLcNo - - - "+origLcNo);
						logger.info("originalLcDateDb - - - "+originalLcDateDb);
						logger.info("duplicateLcNo - - - "+duplicateLcNo);
						logger.info("duplicateLcDate - - - "+duplicateLcDate);
						
						String dobWords1 = "";
						String dobWords2 = "";
						List dobWordsList = commonLc.breakSentence(dobWordsDb, 45);
						if(dobWordsList != null){
							dobWords1 = dobWordsList.get(0).toString().trim();
							if(dobWordsList.size() > 1){
								dobWords2 = dobWordsList.get(1).toString().trim();
							}
						}
						/*StringTokenizer dobWordsToken = new StringTokenizer(dobWordsDb);
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
						}*/
						
						String lastSchool1 = "";
						String lastSchool2 = "";
						List lastSchoolList = commonLc.breakSentence(lastSchoolDb, 40);
						if(lastSchoolList != null){
							lastSchool1 = lastSchoolList.get(0).toString().trim();
							if(lastSchoolList.size() > 1){
								lastSchool2 = lastSchoolList.get(1).toString().trim();
							}
						}
						/*StringTokenizer lastSchoolToken = new StringTokenizer(lastSchoolDb);
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
						}*/
						
						if(!conduct.equalsIgnoreCase("") && (conductDb.equalsIgnoreCase("") || conductDb.equalsIgnoreCase("NA") || conductDb.equalsIgnoreCase("-") || conductDb.equalsIgnoreCase(null) || conductDb.equalsIgnoreCase("null") || lcTypeClass.equalsIgnoreCase("Update"))){
							conductDb = conduct;
						}
						logger.info("conductDb == "+conductDb);
						
						if(studyingSinceDb.equalsIgnoreCase("") || studyingSinceDb.equalsIgnoreCase("NA") || studyingSinceDb.equalsIgnoreCase("-") || studyingSinceDb.equalsIgnoreCase(null) || studyingSinceDb.equalsIgnoreCase("null")){
							studyingSinceDb = sinceDate;
						}
		//				Chunk chunkSince = new Chunk("                           :"+  studyingSinceDb);
						String stdWords = "";
						String stdAdmittedWords = "";
						if(!currentStdDb.equalsIgnoreCase(leavingStdDb)) {
							leavingStdDb = currentStdDb;
						}
						if(!leavingStdDb.equalsIgnoreCase("JR.KG") && !leavingStdDb.equalsIgnoreCase("SR.KG") && !leavingStdDb.equalsIgnoreCase("")){
							stdWords = " ("+commonLc.RomanToWord(leavingStdDb)+")";
							if(!lcadmittedStdDb.equalsIgnoreCase("-") && !lcadmittedStdDb.equalsIgnoreCase("") && 
									!lcadmittedStdDb.equalsIgnoreCase("null")){
								stdAdmittedWords = " ("+commonLc.FirstWordCap(commonLc.RomanToWord(lcadmittedStdDb))+")";
							}
						}
						
						if(!reason.equalsIgnoreCase("") && (reasonDb.equalsIgnoreCase("") || reasonDb.equalsIgnoreCase("NA") || reasonDb.equalsIgnoreCase("-") || reasonDb.equalsIgnoreCase(null) || reasonDb.equalsIgnoreCase("null") || lcTypeClass.equalsIgnoreCase("Update"))){
							reasonDb = reason;
						}
						
						if(!remark.equalsIgnoreCase("") && (remarkDb.equalsIgnoreCase("") || remarkDb.equalsIgnoreCase("NA") || remarkDb.equalsIgnoreCase("-") || remarkDb.equalsIgnoreCase(null) || remarkDb.equalsIgnoreCase("null") || lcTypeClass.equalsIgnoreCase("Update"))){
							remarkDb = remark;
						}
						
						/*if(!remark2.equalsIgnoreCase("") && (feeStatusDb.equalsIgnoreCase("") || feeStatusDb.equalsIgnoreCase("NA") || feeStatusDb.equalsIgnoreCase("-") || feeStatusDb.equalsIgnoreCase(null) || lcTypeClass.equalsIgnoreCase("Update"))){
							feeStatusDb = remark2;
						}*/
						
						if(progressT.equalsIgnoreCase("Based on Result")){
							progressDb = progressMap.get(grDb);
						}
						else if(!progress.equalsIgnoreCase("") && (progressDb.equalsIgnoreCase("") || progressDb.equalsIgnoreCase("NA") || progressDb.equalsIgnoreCase("-") || progressDb.equalsIgnoreCase(null) || progressDb.equalsIgnoreCase("null") || lcTypeClass.equalsIgnoreCase("Update"))){
							progressDb = progress;
						}
						
						if(progress == null || progressDb == null){
							progressDb = "-----";
						}
						
						logger.info("remarkDb===="+remarkDb);
						if(!lcTypeClass.equalsIgnoreCase("Original") && !lcTypeClass.equalsIgnoreCase("Update")){
							feeStatus = feeStatusDb;
						}
						else if(feeStatus.equalsIgnoreCase("null") || feeStatus.equalsIgnoreCase("Select") || feeStatus.equalsIgnoreCase("None") || feeStatus.equalsIgnoreCase("-")){
							feeStatus = "-";
							feeStatusDb = feeStatus;
						}
						else if(feeStatus.equalsIgnoreCase("From Fee Status")){
							feeStatus = feeStatusDb;
						}
						if(!feeStatus.equalsIgnoreCase("-") && !feeStatus.trim().equalsIgnoreCase("") && 
								!feeStatusDb.trim().equalsIgnoreCase(feeStatus)){
							feeStatusDb = feeStatus;
							boolean updateSuccess = false;
							if(view_save.equalsIgnoreCase("SAVE")){
								//Update fee status table
								List<String> foundStudentList = new ArrayList();
								foundStudentList.add("-|"+grDb+"|-|"+feeStatus);
								try{
									updateSuccess = findStudentDB.insertStudentFeeStatus(sessionData, foundStudentList, academic, 
										std, div, true, section, "LC");
									if(!updateSuccess){
										findStudentDB.insertStudentFeeStatus(sessionData, foundStudentList, academic, 
												std, div, false, section, "LC");
									}
								} catch(Exception e){
									logger.error("fee status update/insert failed while creating LC");
								}
							}
						}
						logger.info("feeStatusDb===="+feeStatusDb);
						String remarkStatus = "";
						if(remarkDb.trim().equalsIgnoreCase("-") || remarkDb.trim().equalsIgnoreCase("") || remarkDb.trim().equalsIgnoreCase("NA") || remarkDb.trim().equalsIgnoreCase("null")){
							remarkStatus = feeStatusDb.trim() +".";
						} else if(feeStatusDb.equalsIgnoreCase("-") || feeStatusDb.equalsIgnoreCase("None")){
							remarkStatus = remarkDb.trim() + ".";
						} else {
							remarkStatus = remarkDb.trim() +", "+ feeStatusDb.trim() + ".";
						}
						
						/*StringTokenizer remarkToken = new StringTokenizer(remarkStatus);
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
						}*/
						String payremark = "";
						String remarkLc = "";
						List remarkStatusList = commonLc.breakSentence(remarkStatus, 45);
						if(remarkStatusList != null){
							payremark = remarkStatusList.get(0).toString().trim();
							if(remarkStatusList.size() > 1){
								remarkLc = remarkStatusList.get(1).toString().trim();
							}
						}
						
						int preparedByHeight = 40;
		//				if(!dobWords2.equalsIgnoreCase("")){
		//					preparedByHeight = preparedByHeight - 4;
		//				}
						logger.info("dobWords2:"+dobWords2);
						logger.info("duplicateLcDb:"+duplicateLcDb);
						logger.info("lastSchool2:"+lastSchool2);
						
						if(!dobWords2.equalsIgnoreCase("") && !dobWords2.equalsIgnoreCase(" ") && !dobWords2.equalsIgnoreCase("NA") && !dobWords2.equalsIgnoreCase("-") 
								&& !dobWords2.equalsIgnoreCase(null) && !dobWords2.equalsIgnoreCase("null")){
							preparedByHeight = preparedByHeight - 6;
						}
						
						if(!duplicateLcDb.equalsIgnoreCase(" ") && !duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("NA") && !duplicateLcDb.equalsIgnoreCase("-")
								&& !duplicateLcDb.equalsIgnoreCase(null) && !duplicateLcDb.equalsIgnoreCase("null")){
							preparedByHeight = preparedByHeight - 4;
						}
						
						if(!lastSchool2.equalsIgnoreCase("") && !lastSchool2.equalsIgnoreCase(" ") && !lastSchool2.equalsIgnoreCase("NA") 
								&& !lastSchool2.equalsIgnoreCase("-") && !lastSchool2.equalsIgnoreCase(null) && !lastSchool2.equalsIgnoreCase("null")){
							preparedByHeight = preparedByHeight - 6;
						}
						
		//				JOptionPane.showMessageDialog(null, "preparedByHeight :: "+preparedByHeight);
		//				//single dob with duplicate
		//				if(dobWords2.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("NA") && !duplicateLcDb.equalsIgnoreCase(null)){
		//					preparedByHeight = preparedByHeight - 18;
		//				}
		//				//single dob with original
		//				else if(dobWords2.equalsIgnoreCase("") && (duplicateLcDb.equalsIgnoreCase("") || duplicateLcDb.equalsIgnoreCase("NA") || duplicateLcDb.equalsIgnoreCase(null))){
		//					preparedByHeight = preparedByHeight - 13;
		//				}
		//				//double dob with duplicate
		//				else if(!dobWords2.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("NA") && !duplicateLcDb.equalsIgnoreCase(null)){
		//					preparedByHeight = preparedByHeight - 35;
		//				}
		//				//double dob with original
		//				else if(!dobWords2.equalsIgnoreCase("") && (duplicateLcDb.equalsIgnoreCase("") || duplicateLcDb.equalsIgnoreCase("NA") || duplicateLcDb.equalsIgnoreCase(null))){
		//					preparedByHeight = preparedByHeight - 20;
		//				}
						
						/*Chunk chunkHeader = new Chunk("P.R. HIGH SCHOOL");
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
						document.add(paraRule);*/
						
						/*Chunk blank = new Chunk("A");
						Paragraph parablank = new Paragraph();
						parablank.setSpacingAfter(-100);
						parablank.add(blank);
						document.add(parablank);*/
						
						Chunk sep = new Chunk(" ");
						Paragraph paraSep = new Paragraph();
//						paraSep.getPaddingTop(105);
						paraSep.setSpacingAfter(90);
						paraSep.add(sep);
						paraSep.setAlignment(Element.ALIGN_LEFT);
						
						if(pdf_header_img_flag.equalsIgnoreCase("true")){
							////just to add image in pdf as header
							Image img = Image.getInstance(img_path+pdf_header_img_path);
							img.scalePercent(image_pdf_scalepercent);
//							img.scaleToFit(550f, 150f);
							img.setAbsolutePosition(image_pdf_pos_x, image_pdf_pos_y);
//							img.scaleAbsolute(550, 150);
						    document.add(img);
//						    paraSep.setSpacingBefore(05);
						}
						document.add(paraSep);
						
						Chunk sep01 = new Chunk(separator);
						Paragraph paraSep01 = new Paragraph();
//						paraSep01.getPaddingTop(105);
//						paraSep01.setSpacingBefore(250);
						paraSep01.setSpacingAfter(0);
						paraSep01.add(sep01);
						paraSep01.setAlignment(Element.ALIGN_LEFT);
						document.add(paraSep01);
						
						Chunk chunkSuid = new Chunk("    Student Udise ID :  "+suidDb);
						chunkSuid.setFont(fontClass1);
						
						Paragraph paraSuid = new Paragraph();
						paraSuid.setSpacingBefore(-8);
						paraSuid.add(chunkSuid);
						paraSuid.setAlignment(Element.ALIGN_LEFT);
						document.add(paraSuid);
						
						Chunk chunkAdhaarCard = new Chunk("    UID No.(Aadhar  No.) :  "+adhaarCardDb);
						chunkAdhaarCard.setFont(fontClass1);
						
						Paragraph paraAdhaarCard = new Paragraph();
						paraAdhaarCard.setSpacingBefore(-4);
						paraAdhaarCard.add(chunkAdhaarCard);
						paraAdhaarCard.setAlignment(Element.ALIGN_LEFT);
						document.add(paraAdhaarCard);

						if(lcType.equalsIgnoreCase("DUPLICATE"))
						{
							Chunk chunkDUPLICATE = new Chunk("                                                                                DUPLICATE ");
							/*Font fontDUPLICATE = new Font(Font.TIMES_ROMAN);
							fontDUPLICATE.setStyle(Font.BOLD);
							fontDUPLICATE.setSize(16);
							fontDUPLICATE.setColor(Color.BLACK);*/
							chunkDUPLICATE.setFont(fontClass1);
							Paragraph paraDUPLICATE = new Paragraph();
							paraDUPLICATE.setSpacingBefore(-24);
		//					paraDUPLICATE.setSpacingAfter(-16);
							paraDUPLICATE.add(chunkDUPLICATE);
							paraDUPLICATE.setAlignment(Element.ALIGN_LEFT);
							document.add(paraDUPLICATE);
						} 
						else if(lcType.equalsIgnoreCase("TRIPLICATE"))
						{
							Chunk chunkTRIPLICATE = new Chunk("                                                                                TRIPLICATE ");
							/*Font fontTRIPLICATE = new Font(Font.TIMES_ROMAN);
							fontTRIPLICATE.setStyle(Font.BOLD);
							fontTRIPLICATE.setSize(16);
							fontTRIPLICATE.setColor(Color.BLACK);*/
							chunkTRIPLICATE.setFont(fontClass1);
							Paragraph paraTRIPLICATE = new Paragraph();
							paraTRIPLICATE.setSpacingBefore(-24);
		//					paraTRIPLICATE.setSpacingAfter(-16);
							paraTRIPLICATE.add(chunkTRIPLICATE);
							paraTRIPLICATE.setAlignment(Element.ALIGN_LEFT);
							document.add(paraTRIPLICATE);
						}
						
						Chunk chunkGrNo = new Chunk("                                                                                                                 " +
								 "           G.R. No.:  "+grDb);
						chunkGrNo.setFont(fontClass1);
						
						Paragraph paraGrNo = new Paragraph();
						if(lcType.equalsIgnoreCase("original")){
							paraGrNo.setSpacingBefore(-27);
						}else{
							paraGrNo.setSpacingBefore(-20);
						}
						
						paraGrNo.add(chunkGrNo);
						paraGrNo.setAlignment(Element.ALIGN_LEFT);
						document.add(paraGrNo);

						Chunk chunkLcNoTitle = new Chunk("                                                                                                                 " +
														 "           L.C. No. :");
						/*Font fontLcNoTitle = new Font(Font.TIMES_ROMAN);
						fontLcNoTitle.setStyle(Font.BOLD);
						fontLcNoTitle.setSize(16);
						fontLcNoTitle.setColor(Color.BLACK);*/
						chunkLcNoTitle.setFont(fontClass1);
						Paragraph paraLcNoTitle = new Paragraph();
						paraLcNoTitle.setSpacingBefore(-4);
						paraLcNoTitle.add(chunkLcNoTitle);
						paraLcNoTitle.setAlignment(Element.ALIGN_LEFT);
						document.add(paraLcNoTitle);
						
						Chunk chunkLcNo = new Chunk("                                                                                                                 " +
						 "                             "+showNewLcNo);
						/*Font fontLcNo = new Font(Font.TIMES_ROMAN);
						fontLcNo.setStyle(Font.BOLD);
						fontLcNo.setSize(16);
						fontLcNo.setColor(Color.BLACK);*/
						chunkLcNo.setFont(fontClass1);
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
						paraSep1.setSpacingBefore(-10);
						paraSep1.add(sep1);
						paraSep1.setAlignment(Element.ALIGN_LEFT);
						document.add(paraSep1);
						
						Chunk chunkTitleName = new Chunk(" 01.   Name Of The Pupil In Full");
						/*Font fontTitleName = new Font(Font.TIMES_ROMAN);
						fontTitleName.setStyle(Font.BOLD);
						fontTitleName.setStyle(Font.ITALIC);
						fontTitleName.setSize(13);
						fontTitleName.setColor(Color.BLACK);*/
						chunkTitleName.setFont(fontClass2);
						
						Paragraph paraTitleName = new Paragraph();	
						paraTitleName.setSpacingBefore(5);
						paraTitleName.add(chunkTitleName);
						paraTitleName.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleName);
						
						Chunk chunkName = new Chunk("                                                                :   "+  commonLc.FirstWordCap(nameDb));
						/*Font fontName = new Font(Font.TIMES_ROMAN);
						fontName.setStyle(Font.BOLD);
						fontName.setStyle(Font.ITALIC);
						fontName.setSize(13);
						fontName.setColor(Color.BLACK);*/
						chunkName.setFont(fontClass2);
						
						Paragraph paraName = new Paragraph();	
						paraName.setSpacingBefore(-16);
						paraName.add(chunkName);
						paraName.setAlignment(Element.ALIGN_LEFT);
						document.add(paraName);
						
						Chunk chunkTitleMother = new Chunk(" 02.   Mother Name");
						chunkTitleMother.setFont(fontClass2);
						
						Paragraph paraTitleMother = new Paragraph();	
						paraTitleMother.setSpacingBefore(8);
						paraTitleMother.add(chunkTitleMother);
						paraTitleMother.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleMother);
						
						Chunk chunkMother = new Chunk("                                                                :   "+  commonLc.FirstWordCap(motherNameDb));
						chunkMother.setFont(fontClass2);
						
						Paragraph paraMother = new Paragraph();	
						paraMother.setSpacingBefore(-16);
						paraMother.add(chunkMother);
						paraMother.setAlignment(Element.ALIGN_LEFT);
						document.add(paraMother);
						
						if(!medium.equalsIgnoreCase("") && (mediumDb.equalsIgnoreCase("") || mediumDb.equalsIgnoreCase("NA") || 
							mediumDb.equalsIgnoreCase("-") || mediumDb.equalsIgnoreCase(null) || mediumDb.equalsIgnoreCase("null") || lcTypeClass.equalsIgnoreCase("Update"))){
							mediumDb = medium;
						}
						Chunk chunkTitleMedium = new Chunk("                                                                                                                      Medium");
						chunkTitleMedium.setFont(fontClass2);
						
						Paragraph paraTitleMedium = new Paragraph();	
						paraTitleMedium.setSpacingBefore(-15);
						paraTitleMedium.add(chunkTitleMedium);
						paraTitleMedium.setAlignment(Element.ALIGN_LEFT);
						
						Chunk chunkMedium = new Chunk("                                                                                                                                    :  "+  commonLc.FirstWordCap(mediumDb));
						chunkMedium.setFont(fontClass2);
						
						Paragraph paraMedium = new Paragraph();	
						paraMedium.setSpacingBefore(-16);
						paraMedium.add(chunkMedium);
						paraMedium.setAlignment(Element.ALIGN_LEFT);
						if(!mediumDb.equalsIgnoreCase("-") && !mediumDb.equalsIgnoreCase("")){
							document.add(paraTitleMedium);
							document.add(paraMedium);
						}
						
						Chunk chunkTitleNationality = new Chunk(" 03.   Nationality");
						chunkTitleNationality.setFont(fontClass2);
						
						Paragraph paraTitleNationality = new Paragraph();	
						paraTitleNationality.setSpacingBefore(4);
						paraTitleNationality.add(chunkTitleNationality);
						paraTitleNationality.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleNationality);
						
						Chunk chunkNationality = new Chunk("                                                                :   "+  commonLc.FirstWordCap(nationalityDb));
						chunkNationality.setFont(fontClass2);
						
						Paragraph paraNationality = new Paragraph();	
						paraNationality.setSpacingBefore(-16);
						paraNationality.add(chunkNationality);
						paraNationality.setAlignment(Element.ALIGN_LEFT);
						document.add(paraNationality);
						
						if(mother_tongue.equalsIgnoreCase("-") || mother_tongue.equalsIgnoreCase("") || mother_tongue.equalsIgnoreCase("null")){
							mother_tongue = "-----";
						}
						Chunk chunkTitleMotherTongue = new Chunk("                                                                                                          Mother Tongue");
						chunkTitleMotherTongue.setFont(fontClass2);
						
						Paragraph paraTitleMotherTongue = new Paragraph();	
						paraTitleMotherTongue.setSpacingBefore(-15);
						paraTitleMotherTongue.add(chunkTitleMotherTongue);
						paraTitleMotherTongue.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleMotherTongue);
						
						Chunk chunkMotherTongue = new Chunk("                                                                                                                                    :  "+  commonLc.FirstWordCap(mother_tongue));
						chunkMotherTongue.setFont(fontClass2);
						
						Paragraph paraMotherTongue = new Paragraph();	
						paraMotherTongue.setSpacingBefore(-16);
						paraMotherTongue.add(chunkMotherTongue);
						paraMotherTongue.setAlignment(Element.ALIGN_LEFT);
						document.add(paraMotherTongue);
						
						Chunk chunkTitleReligion = new Chunk(" 04.   Religion");
						chunkTitleReligion.setFont(fontClass2);
						
						Paragraph paraTitleReligion = new Paragraph();	
						paraTitleReligion.setSpacingBefore(4);
						paraTitleReligion.add(chunkTitleReligion);
						paraTitleReligion.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleReligion);
						
						Chunk chunkReligion = new Chunk("                                                                :   "+  commonLc.FirstWordCap(religionDb));
						chunkReligion.setFont(fontClass2);
						
						Paragraph paraReligion = new Paragraph();	
						paraReligion.setSpacingBefore(-16);
						paraReligion.add(chunkReligion);
						paraReligion.setAlignment(Element.ALIGN_LEFT);
						document.add(paraReligion);
						
						if(subCasteDb.equalsIgnoreCase("-") || subCasteDb.equalsIgnoreCase("") || subCasteDb.equalsIgnoreCase("null")){
							subCasteDb = "-----";
						}
						
						Chunk chunkTitleCaste = new Chunk(" 05.   Caste");
						chunkTitleCaste.setFont(fontClass2);
						
						Paragraph paraTitleCaste = new Paragraph();	
						paraTitleCaste.setSpacingBefore(4);
						paraTitleCaste.add(chunkTitleCaste);
						paraTitleCaste.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleCaste);
						
						Chunk chunkCaste = new Chunk("                                                                :   "+  commonLc.FirstWordCap(castDb) + "    Sub-Caste:" + commonLc.FirstWordCap(subCasteDb));
						chunkCaste.setFont(fontClass2);
						
						Paragraph paraCaste = new Paragraph();	
						paraCaste.setSpacingBefore(-16);
						paraCaste.add(chunkCaste);
						paraCaste.setAlignment(Element.ALIGN_LEFT);
						document.add(paraCaste);
						
						/*Chunk chunkTitleSubCaste = new Chunk("                                                                                                             Sub-Caste");
						chunkTitleSubCaste.setFont(fontClass2);
						
						Paragraph paraTitleSubCaste = new Paragraph();	
						paraTitleSubCaste.setSpacingBefore(-15);
						paraTitleSubCaste.add(chunkTitleSubCaste);
						paraTitleSubCaste.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleSubCaste);*/
						
						/*Chunk chunkSubCaste = new Chunk("                                                                                                                              :  "+  commonLc.FirstWordCap(subCasteDb));
						chunkSubCaste.setFont(fontClass2);
						
						Paragraph paraSubCaste = new Paragraph();	
						paraSubCaste.setSpacingBefore(-16);
						paraSubCaste.add(chunkSubCaste);
						paraSubCaste.setAlignment(Element.ALIGN_LEFT);
						document.add(paraSubCaste);*/
						
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
						fullplaceOfBirth = fullplaceOfBirth + " State:"+commonLc.FirstWordCap(stateDb);
						
						if(countryDb.trim().equalsIgnoreCase("-") || countryDb.trim().trim().equalsIgnoreCase("") || countryDb.trim().equalsIgnoreCase("null")){
							countryDb = "-----";
						}
						fullplaceOfBirth = fullplaceOfBirth + " Country:"+commonLc.FirstWordCap(countryDb);
						List placeOfBirthList = commonLc.breakSentence(fullplaceOfBirth, 45);
						if(placeOfBirthList != null){
							placeOfBirth1 = placeOfBirthList.get(0).toString().trim();
							if(placeOfBirthList.size() > 2){
								placeOfBirth2 = placeOfBirthList.get(1).toString().trim();
								placeOfBirth3 = placeOfBirthList.get(2).toString().trim();
							}
							else if(placeOfBirthList.size() > 1){
								placeOfBirth2 = placeOfBirthList.get(1).toString().trim();
							}
						}
						placeOfBirth1 = placeOfBirth1.replace("*","");
						placeOfBirth2 = placeOfBirth2.replace("*","");
						placeOfBirth3 = placeOfBirth3.replace("*","");
						Chunk chunkTitlePOB = new Chunk(" 06.   Place Of Birth (Village/City)");
						chunkTitlePOB.setFont(fontClass2);
						
						Paragraph paraTitlePOB = new Paragraph();	
						paraTitlePOB.setSpacingBefore(8);
						paraTitlePOB.add(chunkTitlePOB);
						paraTitlePOB.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitlePOB);
						
						Chunk chunkPOB = new Chunk("                                                                :   "+placeOfBirth1);
						chunkPOB.setFont(fontClass2);
						
						Paragraph paraPOB = new Paragraph();	
						paraPOB.setSpacingBefore(-16);
						paraPOB.add(chunkPOB);
						paraPOB.setAlignment(Element.ALIGN_LEFT);
						document.add(paraPOB);
						
						if(!placeOfBirth2.equalsIgnoreCase("-") && !placeOfBirth2.equalsIgnoreCase("") && !placeOfBirth2.equalsIgnoreCase("null")){
							Chunk chunkTitleState = new Chunk(" ");
							chunkTitleState.setFont(fontClass2);
							
							Paragraph paraTitleState = new Paragraph();	
							paraTitleState.setSpacingBefore(0);
							paraTitleState.add(chunkTitleState);
							paraTitleState.setAlignment(Element.ALIGN_LEFT);
							document.add(paraTitleState);
							
							Chunk chunkState = new Chunk("                                                                    "+  placeOfBirth2);
							chunkState.setFont(fontClass2);
							
							Paragraph paraState = new Paragraph();	
							paraState.setSpacingBefore(-16);
							paraState.add(chunkState);
							paraState.setAlignment(Element.ALIGN_LEFT);
							document.add(paraState);
						}
						
						if(!placeOfBirth3.equalsIgnoreCase("-") && !placeOfBirth3.equalsIgnoreCase("") && !placeOfBirth3.equalsIgnoreCase("null")){
							Chunk chunkTitleState = new Chunk(" ");
							chunkTitleState.setFont(fontClass2);
							
							Paragraph paraTitleState = new Paragraph();	
							paraTitleState.setSpacingBefore(0);
							paraTitleState.add(chunkTitleState);
							paraTitleState.setAlignment(Element.ALIGN_LEFT);
							document.add(paraTitleState);
							
							Chunk chunkState = new Chunk("                                                                    "+  placeOfBirth3);
							chunkState.setFont(fontClass2);
							
							Paragraph paraState = new Paragraph();	
							paraState.setSpacingBefore(-16);
							paraState.add(chunkState);
							paraState.setAlignment(Element.ALIGN_LEFT);
							document.add(paraState);
						}
						
						Chunk chunkTitleDOB1 = new Chunk(" 07.   Date Of Birth (In Figure)");
						chunkTitleDOB1.setFont(fontClass2);
						
						Paragraph paraTitleDOB1 = new Paragraph();	
						paraTitleDOB1.setSpacingBefore(8);
						paraTitleDOB1.add(chunkTitleDOB1);
						paraTitleDOB1.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleDOB1);
						
		//				dobDb = commonLc.MMM_ddmmyyy(dobDb);
						Chunk chunkDOB = new Chunk("                                                                :   "+  commonLc.FirstWordCap(dobDb));
						chunkDOB.setFont(fontClass2);
						
						Paragraph paraTitleDOB = new Paragraph();	
						paraTitleDOB.setSpacingBefore(-16);
						paraTitleDOB.add(chunkDOB);
						paraTitleDOB.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleDOB);
						
						Chunk chunkTitleDOB2 = new Chunk(" 08.   Date Of Birth (In Words)");
						chunkTitleDOB2.setFont(fontClass2);
						
						Paragraph paraTitleDOB2 = new Paragraph();	
						paraTitleDOB2.setSpacingBefore(8);
						paraTitleDOB2.add(chunkTitleDOB2);
						paraTitleDOB2.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleDOB2);
						
						Chunk chunkDOB_Word = new Chunk("                                                                :   "+  commonLc.FirstWordCap(dobWords1));
						chunkDOB_Word.setFont(fontClass2);
						
						Paragraph paraTitleDOB_Word = new Paragraph();	
						paraTitleDOB_Word.setSpacingBefore(-16);
						paraTitleDOB_Word.add(chunkDOB_Word);
						paraTitleDOB_Word.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleDOB_Word);
						
						if(!dobWords2.equalsIgnoreCase("") && !dobWords2.equalsIgnoreCase("null")){
							Chunk chunkDOB1_Word = new Chunk  ("                                                                    "+commonLc.FirstWordCap(dobWords2));
							chunkDOB1_Word.setFont(fontClass2);
							
							Paragraph paraTitleDOB1_Word = new Paragraph();	
							paraTitleDOB1_Word.setSpacingBefore(0);
			//				paraTitleDOB1_Word.add(chunkTitleLastSchool1);
							paraTitleDOB1_Word.add(chunkDOB1_Word);
							paraTitleDOB1_Word.setAlignment(Element.ALIGN_LEFT);
							document.add(paraTitleDOB1_Word);
						}
						
						String lastSchool = "Last School";
						String lastSchoolSpace = "";
						if(sessionData.getAppType().toString().equalsIgnoreCase("COLLEGE")){
							lastSchool = "Last School/Jr.College";
//							lastSchoolSpace = "    ";
						}
						Chunk chunkTitleLastSchool = new Chunk(" 09.   "+lastSchool+" Attended");
						chunkTitleLastSchool.setFont(fontClass2);
						
						Paragraph paraTitleLastSchool = new Paragraph();	
						paraTitleLastSchool.setSpacingBefore(8);
						paraTitleLastSchool.add(chunkTitleLastSchool);
						paraTitleLastSchool.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleLastSchool);
						
						Chunk chunkLastSchool = new Chunk  (lastSchoolSpace+"                                                                :   "+commonLc.FirstWordCap(lastSchool1));
						chunkLastSchool.setFont(fontClass2);
						
						Paragraph paraLastSchool = new Paragraph();	
						paraLastSchool.setSpacingBefore(-16);
						paraLastSchool.add(chunkLastSchool);
						paraLastSchool.setAlignment(Element.ALIGN_LEFT);
						document.add(paraLastSchool);
						
						if(!lastSchool2.equalsIgnoreCase("") && !lastSchool2.equalsIgnoreCase("null")){
							Chunk chunkLastSchool2 = new Chunk  ("                                                                    "+commonLc.FirstWordCap(lastSchool2));
							chunkLastSchool2.setFont(fontClass2);
							
							Paragraph paraTitleLastSchool2 = new Paragraph();	
							paraTitleLastSchool2.setSpacingBefore(0);
			//				paraTitleLastSchool2.add(chunkTitleLastSchool1);
							paraTitleLastSchool2.add(chunkLastSchool2);
							paraTitleLastSchool2.setAlignment(Element.ALIGN_LEFT);
							document.add(paraTitleLastSchool2);
						}
						
						if(admittedStdBranch.equalsIgnoreCase("") || admittedStdBranch.equalsIgnoreCase("NA") || admittedStdBranch.equalsIgnoreCase("-") || admittedStdBranch.equalsIgnoreCase(null) 
								|| admittedStdBranch.equalsIgnoreCase("null") || !currentStdDb.equalsIgnoreCase(leavingStdDb) || !admittedStdBranch.contains(commonLc.RomanToWord(lcadmittedStdDb))){
							admittedToken = lcadmittedStdDb.toUpperCase() + " " +admittedToken;
							if(admittedToken.contains("WORDSVALUE")){
								admittedToken = admittedToken.replace("WORDSVALUE", stdAdmittedWords);
							}
						}
						else{
							admittedToken = admittedStdBranch;
						}
						
						Chunk chunkTitleAdmittedStd = new Chunk(" 10.   Admitted to Std.");
						chunkTitleAdmittedStd.setFont(fontClass2);
						
						Paragraph paraTitleAdmittedStd = new Paragraph();	
						paraTitleAdmittedStd.setSpacingBefore(8);
						paraTitleAdmittedStd.add(chunkTitleAdmittedStd);
						paraTitleAdmittedStd.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleAdmittedStd);
						
						Chunk chunkAdmittedStd = new Chunk  ("                                                                :   "+admittedToken);
						chunkAdmittedStd.setFont(fontClass2);
						
						Paragraph paraAdmittedStd = new Paragraph();	
						paraAdmittedStd.setSpacingBefore(-16);
						paraAdmittedStd.add(chunkAdmittedStd);
						paraAdmittedStd.setAlignment(Element.ALIGN_LEFT);
						document.add(paraAdmittedStd);
						
						if(!AdmittedStd2.equalsIgnoreCase("") && !AdmittedStd2.equalsIgnoreCase("null")){
							Chunk chunkAdmittedStd2 = new Chunk  ("                                                                             "+commonLc.FirstWordCap(AdmittedStd2));
							chunkAdmittedStd2.setFont(fontClass2);
							
							Paragraph paraTitleAdmittedStd2 = new Paragraph();	
							paraTitleAdmittedStd2.setSpacingBefore(0);
			//				paraTitleAdmittedStd2.add(chunkTitleAdmittedStd1);
							paraTitleAdmittedStd2.add(chunkAdmittedStd2);
							paraTitleAdmittedStd2.setAlignment(Element.ALIGN_LEFT);
							document.add(paraTitleAdmittedStd2);
						}
						
						Chunk chunkTitleDOA = new Chunk(" 11.   Date Of Admission");
						/*Font fontTitleDOA = new Font(Font.TIMES_ROMAN);
						fontTitleDOA.setStyle(Font.BOLD);
						fontTitleDOA.setStyle(Font.ITALIC);
						fontTitleDOA.setSize(13);
						fontTitleDOA.setColor(Color.BLACK);*/
						chunkTitleDOA.setFont(fontClass2);
						
						Paragraph paraTitleDOA = new Paragraph();	
						paraTitleDOA.setSpacingBefore(8);
						paraTitleDOA.add(chunkTitleDOA);
						paraTitleDOA.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleDOA);
						
						if(dateAdmittedDb.equalsIgnoreCase("00-00-0000") || dateAdmittedDb.equalsIgnoreCase("null")){
							dateAdmittedDb = "-";
						}
						Chunk chunkDOA = new Chunk("                                                                :   "+  commonLc.FirstWordCap(dateAdmittedDb));
						/*Font fontDOA = new Font(Font.TIMES_ROMAN);
						fontDOA.setStyle(Font.BOLD);
						fontDOA.setStyle(Font.ITALIC);
						fontDOA.setSize(13);
						fontDOA.setColor(Color.BLACK);*/
						chunkDOA.setFont(fontClass2);
						
						Paragraph paraDOA = new Paragraph();	
						paraDOA.setSpacingBefore(-16);
						paraDOA.add(chunkDOA);
						paraDOA.setAlignment(Element.ALIGN_LEFT);
						document.add(paraDOA);
						
						Chunk chunkTitleProgress = new Chunk(" 12.   Progress");
						/*Font fontTitleProgress = new Font(Font.TIMES_ROMAN);
						fontTitleProgress.setStyle(Font.BOLD);
						fontTitleProgress.setStyle(Font.ITALIC);
						fontTitleProgress.setSize(13);
						fontTitleProgress.setColor(Color.BLACK);*/
						chunkTitleProgress.setFont(fontClass2);
						
						Paragraph paraTitleProgress = new Paragraph();	
						paraTitleProgress.setSpacingBefore(8);
						paraTitleProgress.add(chunkTitleProgress);
						paraTitleProgress.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleProgress);
						
						Chunk chunkProgress = new Chunk("                                                                :   "+  commonLc.FirstWordCap(progressDb));
						/*Font fontProgress = new Font(Font.TIMES_ROMAN);
						fontProgress.setStyle(Font.BOLD);
						fontProgress.setStyle(Font.ITALIC);
						fontProgress.setSize(13);
						fontProgress.setColor(Color.BLACK);*/
						chunkProgress.setFont(fontClass2);
						
						Paragraph paraProgress = new Paragraph();	
						paraProgress.setSpacingBefore(-16);
						paraProgress.add(chunkProgress);
						paraProgress.setAlignment(Element.ALIGN_LEFT);
						document.add(paraProgress);
						
						/*
						Chunk chunkTitleProgress = new Chunk("Progress : ");
						Font fontTitleProgress = new Font(Font.TIMES_ROMAN);
						fontTitleProgress.setStyle(Font.BOLD);
						fontTitleProgress.setSize(13);
						fontTitleProgress.setColor(Color.BLACK);
						chunkTitleProgress.setFont(fontTitleProgress);
						
						Chunk chunkProgress = new Chunk(progressDb+"                                                                          ");
						Font fontProgress = new Font(Font.TIMES_ROMAN);
						fontProgress.setStyle(Font.NORMAL);
						fontProgress.setSize(13);
						fontProgress.setColor(Color.BLACK);
						chunkProgress.setFont(fontLcNo);
						*/
						
						Chunk chunkTitleConduct = new Chunk(" 13.   Conduct");
						/*Font fontTitleConduct = new Font(Font.TIMES_ROMAN);
						fontTitleConduct.setStyle(Font.BOLD);
						fontTitleConduct.setStyle(Font.ITALIC);
						fontTitleConduct.setSize(13);
						fontTitleConduct.setColor(Color.BLACK);*/
						chunkTitleConduct.setFont(fontClass2);
						
						Paragraph paraTitleConduct = new Paragraph();	
						paraTitleConduct.setSpacingBefore(8);
						paraTitleConduct.add(chunkTitleConduct);
						paraTitleConduct.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleConduct);
						
						Chunk chunkConduct = new Chunk("                                                                :   "+  commonLc.FirstWordCap(conductDb));
						/*Font fontConduct = new Font(Font.TIMES_ROMAN);
						fontConduct.setStyle(Font.BOLD);
						fontConduct.setStyle(Font.ITALIC);
						fontConduct.setSize(13);
						fontConduct.setColor(Color.BLACK);*/
						chunkConduct.setFont(fontClass2);
						
						Paragraph paraConduct = new Paragraph();	
						paraConduct.setSpacingBefore(-16);
						paraConduct.add(chunkConduct);
						paraConduct.setAlignment(Element.ALIGN_LEFT);
						document.add(paraConduct);
			//			
			//			Chunk chunkTitleConduct = new Chunk("Conduct : ");
			//			Font fontTitleConduct = new Font(Font.TIMES_ROMAN);
			//			fontTitleConduct.setStyle(Font.BOLD);
			//			fontTitleConduct.setSize(13);
			//			fontTitleConduct.setColor(Color.BLACK);
			//			chunkTitleConduct.setFont(fontTitleConduct);
			//			
			//			if(conductDb.equalsIgnoreCase("") || conductDb.equalsIgnoreCase("NA")){
			//				conductDb = conduct;
			//			}
			//			Chunk chunkConduct = new Chunk(conductDb);
			//			Font fontConduct = new Font(Font.TIMES_ROMAN);
			//			fontConduct.setStyle(Font.NORMAL);
			//			fontConduct.setSize(14);
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
						
						Chunk chunkTitleDOL = new Chunk(" 14.   Date Of Leaving");
						/*Font fontTitleDOL = new Font(Font.TIMES_ROMAN);
						fontTitleDOL.setStyle(Font.BOLD);
						fontTitleDOL.setStyle(Font.ITALIC);
						fontTitleDOL.setSize(13);
						fontTitleDOL.setColor(Color.BLACK);*/
						chunkTitleDOL.setFont(fontClass2);
						
						Paragraph paraTitleDOL = new Paragraph();	
						paraTitleDOL.setSpacingBefore(8);
						paraTitleDOL.add(chunkTitleDOL);
						paraTitleDOL.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleDOL);
						
					if(dateLeavingDb.equalsIgnoreCase(null) || dateLeavingDb.equalsIgnoreCase("00-00-0000") || dateLeavingDb.equalsIgnoreCase("") || dateLeavingDb.equalsIgnoreCase("NA") || dateLeavingDb.equalsIgnoreCase("-") || dateLeavingDb.equalsIgnoreCase("null")){
							dateLeavingDb = leavingDate;
						}
		//				dateLeavingDb = commonLc.MMM_ddmmyyy(dateLeavingDb);
						Chunk chunkDOL = new Chunk("                                                                :   "+  dateLeavingDb);
						/*Font fontDOL = new Font(Font.TIMES_ROMAN);
						fontDOL.setStyle(Font.BOLD);
						fontDOL.setStyle(Font.ITALIC);
						fontDOL.setSize(13);
						fontDOL.setColor(Color.BLACK);*/
						chunkDOL.setFont(fontClass2);
						
						Paragraph paraDOL = new Paragraph();	
						paraDOL.setSpacingBefore(-16);
						paraDOL.add(chunkDOL);
						paraDOL.setAlignment(Element.ALIGN_LEFT);
						document.add(paraDOL);
						
						Chunk chunkTitleStandard = new Chunk(" 15.   Std. In Which Studying & \n"
								+ "          Since when");
						/*Font fontTitleStandard = new Font(Font.TIMES_ROMAN);
						fontTitleStandard.setStyle(Font.BOLD);
						fontTitleStandard.setStyle(Font.ITALIC);
						fontTitleStandard.setSize(13);
						fontTitleStandard.setColor(Color.BLACK);*/
						chunkTitleStandard.setFont(fontClass2);
						
						Paragraph paraTitleStandard = new Paragraph();	
						paraTitleStandard.setSpacingBefore(8);
						paraTitleStandard.add(chunkTitleStandard);
						paraTitleStandard.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleStandard);
						
						if(stdSinceToken.contains("ABBREVIATION") && leavingStdDb.equalsIgnoreCase("XI")){
							stdSinceToken = stdSinceToken.replace("ABBREVIATION", "(F.Y.J.C.)") ;
						}
						else if(stdSinceToken.contains("ABBREVIATION") && leavingStdDb.equalsIgnoreCase("XII")){
							stdSinceToken = stdSinceToken.replace("ABBREVIATION", "(S.Y.J.C.)") ;
						}
						else if(stdSinceToken.contains("WORDSVALUE")){
							stdSinceToken = stdSinceToken.replace("WORDSVALUE", stdWords);
						}
						
						String studyingSince = "";
						if(studyingSinceTmp.equalsIgnoreCase("") || studyingSinceTmp.equalsIgnoreCase("NA") || studyingSinceTmp.equalsIgnoreCase("-") || studyingSinceTmp.equalsIgnoreCase(null) 
								|| studyingSinceTmp.equalsIgnoreCase("null") || !currentStdDb.equalsIgnoreCase(leavingStdDb) 
								|| !studyingSinceTmp.contains(stdSinceToken) || !studyingSinceTmp.contains(studyingSinceDb)){
							studyingSince = leavingStdDb + commonLc.FirstWordCap(stdSinceToken+" Since "+studyingSinceDb+".");
							if(view_save.equalsIgnoreCase("SAVE")){
								try {
										findStudentDB.updateAdmitted_Since(sessionData, grDb, admittedToken, studyingSince);
	//									logger.info("LC No."+origLcNo+" created for Gr No.: "+grDb);
								} catch (Exception e) {
									logger.info("updateLC Exception == "+e);
								}
							}
						}
						else{
							studyingSince = studyingSinceTmp;
						}
						List stdSinceList = commonLc.breakSentence(studyingSince, 45);
						String stdSince1 = "";
						String stdSince2 = "";
						if(stdSinceList != null){
							stdSince1 = stdSinceList.get(0).toString().trim() + ".";
							if(stdSinceList.size() > 1){
								stdSince1 = stdSince1.substring(0, stdSince1.length()-1);
								stdSince2 = stdSinceList.get(1).toString().trim();
							}
						}
						Chunk chunkStandard = new Chunk("                                                                :   "+ stdSince1);
						/*Font fontStandard = new Font(Font.TIMES_ROMAN);
						fontStandard.setStyle(Font.BOLD);
						fontStandard.setStyle(Font.ITALIC);
						fontStandard.setSize(13);
						fontStandard.setColor(Color.BLACK);*/
						chunkStandard.setFont(fontClass2);
						
						Paragraph paraStandard = new Paragraph();	
						paraStandard.setSpacingBefore(-33);
						paraStandard.add(chunkStandard);
						paraStandard.setAlignment(Element.ALIGN_LEFT);
						document.add(paraStandard);
						
						int reasonSpacing = 18;
						if(!stdSince2.equalsIgnoreCase("") && !stdSince2.equalsIgnoreCase("null")){
							Chunk chunkstdSince2 = new Chunk  ("                                                                    "+commonLc.FirstWordCap(stdSince2));
							chunkstdSince2.setFont(fontClass2);
							
							Paragraph paraTitlestdSince2 = new Paragraph();	
							paraTitlestdSince2.setSpacingBefore(0);
			//				paraTitlestdSince2.add(chunkTitleLastSchool1);
							paraTitlestdSince2.add(chunkstdSince2);
							paraTitlestdSince2.setAlignment(Element.ALIGN_LEFT);
							document.add(paraTitlestdSince2);
							reasonSpacing = 7;
						}
						
						Chunk chunkTitleReason = new Chunk(" 16.   Reason Of Leaving");
						/*Font fontTitleReason = new Font(Font.TIMES_ROMAN);
						fontTitleReason.setStyle(Font.BOLD);
						fontTitleReason.setStyle(Font.ITALIC);
						fontTitleReason.setSize(13);
						fontTitleReason.setColor(Color.BLACK);*/
						chunkTitleReason.setFont(fontClass2);
						
						Paragraph paraTitleReason = new Paragraph();	
						paraTitleReason.setSpacingBefore(reasonSpacing);
						paraTitleReason.add(chunkTitleReason);
						paraTitleReason.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleReason);
						
						Chunk chunkReason = new Chunk("                                                                :   "+  reasonDb);
						/*Font fontReason = new Font(Font.TIMES_ROMAN);
						fontReason.setStyle(Font.BOLD);
						fontReason.setStyle(Font.ITALIC);
						fontReason.setSize(13);
						fontReason.setColor(Color.BLACK);*/
						chunkReason.setFont(fontClass2);
						
						Paragraph paraReason = new Paragraph();	
						paraReason.setSpacingBefore(-16);
						paraReason.add(chunkReason);
						paraReason.setAlignment(Element.ALIGN_LEFT);
						document.add(paraReason);
						
						Chunk chunkTitleRemark = new Chunk(" 17.  Remark");
						/*Font fontTitleRemark = new Font(Font.TIMES_ROMAN);
						fontTitleRemark.setStyle(Font.BOLD);
						fontTitleRemark.setStyle(Font.ITALIC);
						fontTitleRemark.setSize(13);
						fontTitleRemark.setColor(Color.BLACK);*/
						chunkTitleRemark.setFont(fontClass2);
						
						Paragraph paraTitleRemark = new Paragraph();	
						paraTitleRemark.setSpacingBefore(8);
		//				paraTitleRemark.setSpacingAfter(9);
						paraTitleRemark.add(chunkTitleRemark);
						paraTitleRemark.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleRemark);
						
						String stdStar = "";
						String romanStd = "";
						int stdPromoted = 0;
						if(payremark.contains("promoted") || payremark.contains("PROMOTED") || remarkLc.contains("promoted") || remarkLc.contains("PROMOTED")){
							stdPromoted = commonLc.RomanToInteger(leavingStdDb)+1;
							romanStd = commonLc.IntegerToRoman("a"+stdPromoted);
						}
						if(leavingStdDb.equalsIgnoreCase("I") || romanStd.equalsIgnoreCase("I")){
							stdStar = "Ist";
							if(payremark.contains("promoted") || payremark.contains("PROMOTED") || remarkLc.contains("promoted") || remarkLc.contains("PROMOTED")){
								stdStar = romanStd+"nd";
							}
						} else if(leavingStdDb.equalsIgnoreCase("II") || romanStd.equalsIgnoreCase("II")){
							stdStar = "IInd";
							if(payremark.contains("promoted") || payremark.contains("PROMOTED") || remarkLc.contains("promoted") || remarkLc.contains("PROMOTED")){
								stdStar = romanStd+"rd";
							}
						} else if(leavingStdDb.equalsIgnoreCase("III") || romanStd.equalsIgnoreCase("III")){
							stdStar = "IIIrd";
							if(payremark.contains("promoted") || payremark.contains("PROMOTED") || remarkLc.contains("promoted") || remarkLc.contains("PROMOTED")){
								stdStar = romanStd+"th";
							}
						} else if(!leavingStdDb.equalsIgnoreCase("JR.KG") && !leavingStdDb.equalsIgnoreCase("SR.KG")){
							stdStar = leavingStdDb+"th";
							if(payremark.contains("promoted") || payremark.contains("PROMOTED") || remarkLc.contains("promoted") || remarkLc.contains("PROMOTED")){
								stdStar = romanStd+"th";
							}
						}
						payremark = payremark;
						payremark = payremark.replace("****", stdStar.substring(0,stdStar.length()-2));
						Chunk chunkRemark = new Chunk("                                                                :   "+  payremark);
						/*Font fontRemark = new Font(Font.TIMES_ROMAN);
						fontRemark.setStyle(Font.BOLD);
						fontRemark.setStyle(Font.ITALIC);
						fontRemark.setSize(13);
						fontRemark.setColor(Color.BLACK);*/
						chunkRemark.setFont(fontClass2);
						
						Paragraph paraRemark = new Paragraph();	
						paraRemark.setSpacingBefore(-16);
						paraRemark.add(chunkRemark);
						paraRemark.setAlignment(Element.ALIGN_LEFT);
						document.add(paraRemark);
						
						logger.info("remarkDb===="+remarkDb);
						remarkLc = remarkLc;
						remarkLc = remarkLc.replace("****", stdStar);
						Chunk chunkRemark1 = new Chunk("                                                                    "+  remarkLc);
						/*Font fontRemark1 = new Font(Font.TIMES_ROMAN);
						fontRemark1.setStyle(Font.BOLD);
						fontRemark1.setStyle(Font.ITALIC);
						fontRemark1.setSize(13);
						fontRemark1.setColor(Color.BLACK);*/
						chunkRemark1.setFont(fontClass2);
						
						Paragraph paraRemark1 = new Paragraph();	
		//				paraRemark1.setSpacingBefore(-16);
						paraRemark1.add(chunkRemark1);
						paraRemark1.setAlignment(Element.ALIGN_LEFT);
						document.add(paraRemark1);
						
						//////////////////////////////////////////
						Chunk sep2 = new Chunk(separator);
						Paragraph paraSep2 = new Paragraph();
						paraSep2.setSpacingBefore(1);
						paraSep2.add(sep2);
						paraSep2.setAlignment(Element.ALIGN_LEFT);
//						document.add(paraSep2);

						if(lcType.equalsIgnoreCase("DUPLICATE") || lcType.equalsIgnoreCase("TRIPLICATE"))
						{
							Chunk chunkTitleOriginalLC = new Chunk("  Original L.C.No. : ");
							/*Font fontTitleOriginalLC = new Font(Font.TIMES_ROMAN);
							fontTitleOriginalLC.setStyle(Font.BOLD);
							fontTitleOriginalLC.setSize(12);
							fontTitleOriginalLC.setColor(Color.BLACK);*/
							chunkTitleOriginalLC.setFont(fontClass4);
							
							Chunk chunkOriginalLC = new Chunk(showOrigLcNo+"         ");
							/*Font fontOriginalLC = new Font(Font.TIMES_ROMAN);
							fontOriginalLC.setStyle(Font.BOLD);
							fontOriginalLC.setSize(12);
							fontOriginalLC.setColor(Color.BLACK);*/
							chunkOriginalLC.setFont(fontClass4);
							
							Paragraph paraTitleOriginalLC = new Paragraph();	
							paraTitleOriginalLC.setSpacingBefore(-1);
							paraTitleOriginalLC.add(chunkTitleOriginalLC);
							paraTitleOriginalLC.add(chunkOriginalLC);
							paraTitleOriginalLC.setAlignment(Element.ALIGN_LEFT);
//							document.add(paraTitleOriginalLC);
							
							Chunk chunkTitleDated = new Chunk("                                                               (Dt : ");
							/*Font fontTitleDated = new Font(Font.TIMES_ROMAN);
							fontTitleDated.setStyle(Font.BOLD);
							fontTitleDated.setSize(11);
							fontTitleDated.setColor(Color.BLACK);*/
							chunkTitleDated.setFont(fontClass3);
							
		//					originalLcDateDb = commonLc.MMM_ddmmyyy(originalLcDateDb);
							Chunk chunkDated = new Chunk(originalLcDateDb+")");
							/*Font fontDated = new Font(Font.TIMES_ROMAN);
							fontDated.setStyle(Font.BOLD);
							fontDated.setSize(11);
							fontDated.setColor(Color.BLACK);*/
							chunkDated.setFont(fontClass3);
							
							Paragraph paraTitleDated = new Paragraph();	
							paraTitleDated.setSpacingBefore(-16);
							if(!issueDate.equalsIgnoreCase("") && !issueDate.equalsIgnoreCase("00-00-0000")) {
								paraTitleDated.add(chunkTitleDated);
							}
							paraTitleDated.add(chunkDated);
							paraTitleDated.setAlignment(Element.ALIGN_LEFT);
//							document.add(paraTitleDated);
							
							if(lcType.equalsIgnoreCase("TRIPLICATE")){
								Chunk chunkTitleDuplicateLC = new Chunk("                                                                                     / Duplicate L.C.No. : ");
								/*Font fontTitleDuplicateLC = new Font(Font.TIMES_ROMAN);
								fontTitleDuplicateLC.setStyle(Font.BOLD);
								fontTitleDuplicateLC.setSize(12);
								fontTitleDuplicateLC.setColor(Color.BLACK);*/
								chunkTitleDuplicateLC.setFont(fontClass4);
								
								Chunk chunkDuplicateLC = new Chunk(showDupLcNo+"         ");
								/*Font fontDuplicateLC = new Font(Font.TIMES_ROMAN);
								fontDuplicateLC.setStyle(Font.BOLD);
								fontDuplicateLC.setSize(12);
								fontDuplicateLC.setColor(Color.BLACK);*/
								chunkDuplicateLC.setFont(fontClass4);
								
								Paragraph paraTitleDuplicateLC = new Paragraph();	
								paraTitleDuplicateLC.setSpacingBefore(-16);
								paraTitleDuplicateLC.add(chunkTitleDuplicateLC);
								paraTitleDuplicateLC.add(chunkDuplicateLC);
								paraTitleDuplicateLC.setAlignment(Element.ALIGN_LEFT);
//								document.add(paraTitleDuplicateLC);
								
								Chunk chunkTitleDupDated = new Chunk("                                                                            "
										+ "                                                                                  (Dt : ");
/*								Font fontTitleDupDated = new Font(Font.TIMES_ROMAN);
								fontTitleDupDated.setStyle(Font.BOLD);
								fontTitleDupDated.setSize(11);
								fontTitleDupDated.setColor(Color.BLACK);*/
								chunkTitleDupDated.setFont(fontClass3);
								
			//					originalLcDateDb = commonLc.MMM_ddmmyyy(originalLcDateDb);
								Chunk chunkDupDated = new Chunk(duplicateLcDateDb+")");
								/*Font fontDupDated = new Font(Font.TIMES_ROMAN);
								fontDupDated.setStyle(Font.BOLD);
								fontDupDated.setSize(11);
								fontDupDated.setColor(Color.BLACK);*/
								chunkDupDated.setFont(fontClass3);
								
								Paragraph paraTitleDupDated = new Paragraph();	
								paraTitleDupDated.setSpacingBefore(-16);
								paraTitleDupDated.add(chunkTitleDupDated);
								if(!issueDate.equalsIgnoreCase("") && !issueDate.equalsIgnoreCase("00-00-0000")) {
									paraTitleDupDated.add(chunkTitleDated);
								}
								paraTitleDupDated.add(chunkDupDated);
								paraTitleDupDated.setAlignment(Element.ALIGN_LEFT);
//								document.add(paraTitleDupDated);
							}
							
							/*Chunk chunkTitleDUPLICATE1 = new Chunk("(DUPLICATE)       ");
							Font fontTitleDUPLICATE1 = new Font(Font.TIMES_ROMAN);
							fontTitleDUPLICATE1.setStyle(Font.BOLD);
							fontTitleDUPLICATE1.setSize(12);
							fontTitleDUPLICATE1.setColor(Color.BLACK);
							chunkTitleDUPLICATE1.setFont(fontTitleDUPLICATE1);
							
							Paragraph paraTitleDUPLICATE1 = new Paragraph();	
							paraTitleDUPLICATE1.setSpacingBefore(-16);
							paraTitleDUPLICATE1.add(chunkTitleDUPLICATE1);
							paraTitleDUPLICATE1.setAlignment(Element.ALIGN_RIGHT);
							document.add(paraTitleDUPLICATE1);*/
							
							Chunk sep3 = new Chunk(separator);
							Paragraph paraSep3 = new Paragraph();
							paraSep3.setSpacingBefore(-5);
							paraSep3.add(sep3);
							paraSep3.setAlignment(Element.ALIGN_LEFT);
//							document.add(paraSep3);
						}
						
						String school_college = "School";
						if(sessionData.getAppType().toString().equalsIgnoreCase("COLLEGE")){
							school_college = "Jr.College";
						}
						Chunk chunkCertified = new Chunk(" Certified that the above information is in accordance with the "+school_college+" Register.");
						/*Font fontCertified = new Font(Font.TIMES_ROMAN);
						fontCertified.setStyle(Font.NORMAL);
						fontCertified.setSize(12);
						fontCertified.setColor(Color.BLACK);*/
						chunkCertified.setFont(fontClass4);
						Paragraph paraCertified = new Paragraph();
						paraCertified.setSpacingBefore(-2);
		//				if(!duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("NA") && !duplicateLcDb.equalsIgnoreCase(" ")){
		//					paraCertified.setSpacingAfter(20);
		//				}else{
		//					paraCertified.setSpacingAfter(43);
		//				}
						paraCertified.add(chunkCertified);
						paraCertified.setAlignment(Element.ALIGN_LEFT);
//						document.add(paraCertified);
						
						Chunk chunkTitleDate = new Chunk(" Date : ");
						/*Font fontTitleDate = new Font(Font.TIMES_ROMAN);
						fontTitleDate.setStyle(Font.BOLD);
						fontTitleDate.setSize(13);
						fontTitleDate.setColor(Color.BLACK);*/
						chunkTitleDate.setFont(fontClass6);
						
						if(!triplicateLcDateDb.equalsIgnoreCase("") && !triplicateLcDateDb.equalsIgnoreCase("null") && 
								triplicateLcDateDb != null && view_save.equalsIgnoreCase("VIEW")){
							issueDate = triplicateLcDateDb;
						}
						else if(!duplicateLcDateDb.equalsIgnoreCase("") && !duplicateLcDateDb.equalsIgnoreCase("null") && 
								duplicateLcDateDb != null && view_save.equalsIgnoreCase("VIEW")){
							issueDate = duplicateLcDateDb;
						}
						else if(!originalLcDateDb.equalsIgnoreCase("") && view_save.equalsIgnoreCase("VIEW")){
							issueDate = originalLcDateDb;
						}
						else if(issueDate.equalsIgnoreCase("null")){
							String currentDate = commonLc.getCurrentDate();
							issueDate = commonLc.MM_dd_mm_yyyy(currentDate);
						}
						else if(issueDate.contains("/")){
							issueDate = commonLc.MM_dd_mm_yyyy(issueDate);
						}
						Chunk chunkDate = new Chunk(issueDate);
						/*Font fontDate = new Font(Font.TIMES_ROMAN);
						fontDate.setStyle(Font.NORMAL);
						fontDate.setSize(13);
						fontDate.setColor(Color.BLACK);*/
						chunkDate.setFont(fontClass7);
						
						Paragraph paraTitleDate = new Paragraph();	
						paraTitleDate.setSpacingBefore(3);
						paraTitleDate.add(chunkTitleDate);
						paraTitleDate.add(chunkDate);
						paraTitleDate.setAlignment(Element.ALIGN_LEFT);
//						document.add(paraTitleDate);
						
						Chunk chunkPrepared = new Chunk("     Prepared By");
						/*Font fontPrepared = new Font(Font.TIMES_ROMAN);
						fontPrepared.setStyle(Font.NORMAL);
//						fontPrepared.setSpacingBefore(10);
						fontPrepared.setSize(13);
						fontPrepared.setColor(Color.BLACK);*/
						chunkPrepared.setFont(fontClass7);
						Paragraph paraPrepared = new Paragraph();
						paraPrepared.setSpacingBefore(preparedByHeight);
						paraPrepared.add(chunkPrepared);
						paraPrepared.setAlignment(Element.ALIGN_LEFT);
//						document.add(paraPrepared);
						
						Chunk chunkChecked = new Chunk("Checked By");
						/*Font fontChecked = new Font(Font.TIMES_ROMAN);
						fontChecked.setStyle(Font.NORMAL);
						fontChecked.setSize(13);
						fontChecked.setColor(Color.BLACK);*/
						chunkChecked.setFont(fontClass7);
						Paragraph paraChecked = new Paragraph();
						paraChecked.setSpacingBefore(-16);
						paraChecked.add(chunkChecked);
						paraChecked.setAlignment(Element.ALIGN_CENTER);
//						document.add(paraChecked);
						
						Chunk chunkPrincipal = new Chunk("Head Master");
						/*Font fontPrincipal = new Font(Font.TIMES_ROMAN);
						fontPrincipal.setStyle(Font.NORMAL);
						fontPrincipal.setSize(13);
						fontPrincipal.setColor(Color.BLACK);*/
						chunkPrincipal.setFont(fontClass7);
						Paragraph paraPrincipal = new Paragraph();	
						paraPrincipal.setSpacingBefore(-16);
						paraPrincipal.add(chunkPrincipal);
						paraPrincipal.setAlignment(Element.ALIGN_RIGHT);
//						document.add(paraPrincipal);
						
						//////Footer setting//////////////////
						
						/////////////////////////////////////
						conductDb = commonLc.replaceComma(conductDb);
						reasonDb  = commonLc.replaceComma(reasonDb);
						remarkDb  = commonLc.replaceComma(remarkDb);
						
						logger.info("selectedLcList ==>  "+grDb+"|*"+origLcNo+"||*"+conductDb+"|||*"+dateLeavingDb+"||||*"+reasonDb+"|||||*"+remarkDb+"||||||*"+duplicateLcNo+"|||||||*"+duplicateLcDate+"||||||||*"+originalLcDate+"|||||||||*"+studyingSinceDb+"||||||||||*"+leavingStdDb);
						selectedLcList.add(grDb+"|*"+origLcNo+"||*"+conductDb+"|||*"+dateLeavingDb+"||||*"+reasonDb+"|||||*"+remarkDb+"||||||*"+duplicateLcNo+"|||||||*"+duplicateLcDate+"||||||||*"+originalLcDate+"|||||||||*"+studyingSinceDb+"||||||||||*"+leavingStdDb);
			    		
						// start second page
						//if(recLCList.size()-i==1)
						
						if(lcType.equalsIgnoreCase("DUPLICATE") || lcType.equalsIgnoreCase("TRIPLICATE"))
						{
							String slash = "/ ";
							onEndPage(writer, document, "", separator, null, fontClass4, 145, 290);
							String showOriginal = "Original L.C.No. : "+showOrigLcNo;
							onEndPage(writer, document, "", showOriginal, null, fontClass4, 135, 120);
							String originalDt = "(Dt : "+originalLcDateDb+")";
							if(issueDate.equalsIgnoreCase("") || issueDate.equalsIgnoreCase("00-00-0000")) {
								originalDt = "";
								slash = "";
							}
							onEndPage(writer, document, "", originalDt, null, fontClass3, 135, 245);
							
							if(lcType.equalsIgnoreCase("TRIPLICATE")){
								String duplicateLc = slash+"Duplicate L.C.No. : "+showDupLcNo;
								onEndPage(writer, document, "", duplicateLc, null, fontClass4, 135, 376);
								String duplicateDt = "(Dt : "+duplicateLcDateDb+")";
								if(issueDate.equalsIgnoreCase("") || issueDate.equalsIgnoreCase("00-00-0000")) {
									duplicateDt = "";
								}
								onEndPage(writer, document, "", duplicateDt, null, fontClass3, 135, 508);
							}
						}
						
						onEndPage(writer, document, "", separator, null, fontClass4, 127, 290);
						String certified = " Certified that the above information is in accordance with the "+school_college+" Register.";
						onEndPage(writer, document, "", certified, null, fontClass7, 110, 250);
						String dateStr = " Date : "+issueDate;
						int dateStrWidth = 95;
						if(issueDate.equalsIgnoreCase("") || issueDate.equalsIgnoreCase("00-00-0000")) {
							dateStrWidth = 63;
						}
						onEndPage(writer, document, "", dateStr, null, fontClass7, 90, dateStrWidth);
						String preparedBy = "Prepared By";
						onEndPage(writer, document, "", preparedBy, null, fontClass7, 40, 95);
						String checkedBy = "Checked By";
						onEndPage(writer, document, "", checkedBy, null, fontClass7, 40, 300);
//						String headMaster = "Head Master";
						onEndPage(writer, document, "", headMaster, null, fontClass7, 40, 500);
						document.newPage();
			    			
				    }
				    document.close();
					
				    logger.info("view_save => "+view_save);
					///////////////////
					if(view_save.equalsIgnoreCase("validate") || view_save.equalsIgnoreCase("view")){
						try {
							String fileNameView 	= "LeavingCertificate"+view_save+commonLc.timeInMillis()+".pdf";
							String fileAddressView = path+fileNameView;
							
							PdfReader pdfReader = new PdfReader(fileAddress);
						    //Modify file using PdfReader
						    PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(fileAddressView));
						    if(view_save.equalsIgnoreCase("validate")){
						    	pdfStamper.setEncryption(null, null, PdfWriter.HideToolbar, PdfWriter.STANDARD_ENCRYPTION_40);
						    }
						    pdfStamper.close();
							
							
							logger.info("file = "+commonLc.deleteFile(fileAddress)+" deleted");
							fileName		=  fileNameView;
							fileAddress		=  fileAddressView;
							
						} catch (IOException e) {
							fileOpenFlag = true;
							logger.error("view file Exception == "+e);
							commonLc.logException(e);
							JOptionPane.showMessageDialog(null, "Please close "+fileName+" and try again"); 
						}
					}
		//		 	else{
		//				fileName 	= "LeavingCertificatePrint.pdf";
		//				fileAddress = "D:\\yash\\LeavingCertificatePrint.pdf";
		//			}
					
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
				logger.error(":: -----Exception----- ::\n"+e);
				commonLc.logException(e);
			}
		}
	}
	
	public void onEndPage(PdfWriter writer, Document document, String headerData, String footerData, Font fontClassHeader, Font fontClassFooter, int footerHeight, int spaceFromLeft) {
        PdfContentByte cb = writer.getDirectContent();
//        Phrase header = new Phrase(headerData, fontClass);
//      ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header,(document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, 0);
        Phrase footer = new Phrase(footerData, fontClassFooter);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,spaceFromLeft, document.bottom() + footerHeight, 0);
    }
}
