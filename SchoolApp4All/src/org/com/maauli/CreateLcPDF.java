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
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;


public class CreateLcPDF {
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
    ResourceBundle bundle    		= ResourceBundle.getBundle("org.com.accesser.school"); 
    static Logger logger 			= Logger.getLogger(CreateLcPDF.class.getName());
	
	public void LeavingCertificate(SessionData sessionData, String view_save, String conductTxt, String leavingDt, String issueDt, String reasonT, String remarkT, 
			List<String> passGrList,String sec, String lcType, String retUserName, String retUserRole, String progressT, String remark2T, String academic, String feeStatus) throws DocumentException{
		
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
    	lcTypeClass = lcType;
    	
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
					recLCList = findStudentDB.findStudentLC(sessionData, passGrList,section,academic);
					if(view_save.equalsIgnoreCase("VALIDATE") && !lcType.equalsIgnoreCase("Update")){
						lcCountYear = findStudentDB.getLastCount(sessionData,"HS_GENERAL_REGISTER", "ORIGINAL_LC");
						dupLcCountYear = findStudentDB.getLastCount(sessionData,"HS_GENERAL_REGISTER", "DUPLICATE_LC");
						tripLcCountYear = findStudentDB.getLastCount(sessionData,"HS_GENERAL_REGISTER", "TRIPLICATE_LC");
//						logger.info("lcCountYear => "+lcCountYear);
//						logger.info("dupLcCountYear => "+dupLcCountYear);
			//			logger.info("tripLcCountYear => "+tripLcCountYear);
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
						
					Font fontClass1 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
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
						String remarkDb = LCDetaillist.get(16);
						String duplicateLcDb = LCDetaillist.get(17);
						String originalLcDateDb = LCDetaillist.get(18);
						String duplicateLcDateDb = LCDetaillist.get(19);
						String studyingSinceDb = LCDetaillist.get(20);
						String currentStdDb = LCDetaillist.get(21);
						String birthPlaceDb = LCDetaillist.get(22);
						String feeStatusDb = LCDetaillist.get(23);
						String triplicateLcDb = LCDetaillist.get(24);
						String triplicateLcDateDb = LCDetaillist.get(25);
						String lcIssueCount1Db = LCDetaillist.get(26);
						String lcadmittedStdDb = LCDetaillist.get(27);
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
						
						if(studyingSinceDb.equalsIgnoreCase("") || studyingSinceDb.equalsIgnoreCase("NA") || studyingSinceDb.equalsIgnoreCase("-") || studyingSinceDb.equalsIgnoreCase("null")){
							studyingSinceDb		= commonLc.getStudyingSince(dateAdmittedDb, academic);
//							studyingSinceDb		= studyingSinceDb.substring(0, 4); 
						}
						
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
								leavingDate = originalLcDateDb;
								showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(leavingDate);
								
								logger.info("original showNewLcNo=="+showNewLcNo);
										
								if(!duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("NA") && !duplicateLcDb.equalsIgnoreCase("-")
										&& !duplicateLcDb.equalsIgnoreCase("null") && duplicateLcDb != null){
									lcType  = "DUPLICATE";
									newLcNo = duplicateLcDb;
									leavingDate = duplicateLcDateDb;
									showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(leavingDate);
									showOrigLcNo = origLcNo + "/" + commonLc.getAcademicYear(originalLcDateDb);
									logger.info("duplicate showNewLcNo =="+showNewLcNo);
									
									if(!triplicateLcDb.equalsIgnoreCase("") && !triplicateLcDb.equalsIgnoreCase("NA") && !triplicateLcDb.equalsIgnoreCase("-")
											&& !triplicateLcDb.equalsIgnoreCase("null") && triplicateLcDb != null){
										lcType  = "TRIPLICATE";
										newLcNo = triplicateLcDb;
										leavingDate = triplicateLcDateDb;
										
										showNewLcNo =  triplicateLcDb + "/" + commonLc.getAcademicYear(triplicateLcDateDb);
										showOrigLcNo = origLcDb + "/" + commonLc.getAcademicYear(originalLcDateDb);
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
									showOrigLcNo = origLcDb + "/" + commonLc.getAcademicYear(originalLcDateDb);
									showDupLcNo = duplicateLcDb + "/" + commonLc.getAcademicYear(duplicateLcDateDb);
								}
								else if(!duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("NA") && !duplicateLcDb.equalsIgnoreCase("-")
										&& !duplicateLcDb.equalsIgnoreCase("null") && duplicateLcDb != null){
									lcType  = "DUPLICATE";
									newLcNo = duplicateLcDb;
									showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(duplicateLcDateDb);
									showOrigLcNo = origLcDb + "/" + commonLc.getAcademicYear(originalLcDateDb);
								}
								else if(!origLcDb.equalsIgnoreCase("") && !origLcDb.equalsIgnoreCase("NA") && !origLcDb.equalsIgnoreCase("-")
										&& !origLcDb.equalsIgnoreCase("null") && origLcDb != null){
									lcType  = "ORIGINAL";
									newLcNo = origLcDb;
									showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(originalLcDateDb);
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
								showOrigLcNo = origLcDb + "/" + commonLc.getAcademicYear(originalLcDateDb);
								logger.info("duplicate showOrigLcNo=="+showOrigLcNo);
							}
							else if(triplicateLcDb.equalsIgnoreCase("") || triplicateLcDb.equalsIgnoreCase("NA") || triplicateLcDb.equalsIgnoreCase("-")
									|| triplicateLcDb.equalsIgnoreCase("null") || triplicateLcDb == null){
								tripLcCountYear = tripLcCountYear +1;
								lcType  = "TRIPLICATE";
								newLcNo = String.format("%04d", tripLcCountYear);
								showNewLcNo =  newLcNo + "/" + commonLc.getAcademicYear(leavingDate);
								logger.info("original showNewLcNo=="+showNewLcNo);
								showOrigLcNo = origLcDb + "/" + commonLc.getAcademicYear(originalLcDateDb);
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
		//						origLcNo = origLcDb;
		//						originalLcDate = originalLcDateDb;
		//						newLcNo = origLcDb;
								leavingDate = originalLcDateDb;
								showNewLcNo =  origLcDb + "/" + commonLc.getAcademicYear(originalLcDateDb);
								
								logger.info("original showNewLcNo=="+showNewLcNo);
										
								if(!duplicateLcDb.equalsIgnoreCase("") && !duplicateLcDb.equalsIgnoreCase("NA") && !duplicateLcDb.equalsIgnoreCase("-")
										&& !duplicateLcDb.equalsIgnoreCase("null") && duplicateLcDb != null){
									lcType  = "DUPLICATE";
		//							newLcNo = duplicateLcDb;
									leavingDate = duplicateLcDateDb;
									showNewLcNo =  duplicateLcDb + "/" + commonLc.getAcademicYear(duplicateLcDateDb);
									showOrigLcNo = origLcDb + "/" + commonLc.getAcademicYear(originalLcDateDb);
									logger.info("duplicate showNewLcNo=="+showNewLcNo);
									
									if(!triplicateLcDb.equalsIgnoreCase("") && !triplicateLcDb.equalsIgnoreCase("NA") && !triplicateLcDb.equalsIgnoreCase("-")
											&& !triplicateLcDb.equalsIgnoreCase("null") && triplicateLcDb != null){
										lcType  = "TRIPLICATE";
			//							newLcNo = duplicateLcDb;
										leavingDate = triplicateLcDateDb;
										showNewLcNo =  triplicateLcDb + "/" + commonLc.getAcademicYear(triplicateLcDateDb);
										showOrigLcNo = origLcDb + "/" + commonLc.getAcademicYear(originalLcDateDb);
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
						List dobWordsList = commonLc.breakSentence(dobWordsDb, 40);
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
						if(!leavingStdDb.equalsIgnoreCase("JR.KG") && !leavingStdDb.equalsIgnoreCase("SR.KG") && !leavingStdDb.equalsIgnoreCase("") && !leavingStdDb.equalsIgnoreCase("null")){
							stdWords = " ("+commonLc.RomanToWord(leavingStdDb)+")";
						}
						
						if(!reason.equalsIgnoreCase("") && (reasonDb.equalsIgnoreCase("") || reasonDb.equalsIgnoreCase("NA") || reasonDb.equalsIgnoreCase("-") || reasonDb.equalsIgnoreCase(null) || reasonDb.equalsIgnoreCase("null") || lcTypeClass.equalsIgnoreCase("Update"))){
							reasonDb = reason;
						}
						logger.info("reasonDb===="+reasonDb);
						
						if(!remark.equalsIgnoreCase("") && (remarkDb.equalsIgnoreCase("") || remarkDb.equalsIgnoreCase("NA") || remarkDb.equalsIgnoreCase("-") || remarkDb.equalsIgnoreCase(null) || remarkDb.equalsIgnoreCase("null") || lcTypeClass.equalsIgnoreCase("Update"))){
							remarkDb = remark;
						}
						
						/*if(!remark2.equalsIgnoreCase("") && (feeStatusDb.equalsIgnoreCase("") || feeStatusDb.equalsIgnoreCase("NA") || feeStatusDb.equalsIgnoreCase("-") || feeStatusDb.equalsIgnoreCase(null) || lcTypeClass.equalsIgnoreCase("Update"))){
							feeStatusDb = remark2;
						}*/
						
						if(!progress.equalsIgnoreCase("") && (progressDb.equalsIgnoreCase("") || progressDb.equalsIgnoreCase("NA") || progressDb.equalsIgnoreCase("-") || progressDb.equalsIgnoreCase(null) || progressDb.equalsIgnoreCase("null") || lcTypeClass.equalsIgnoreCase("Update"))){
							progressDb = progress;
						}
						logger.info("remarkDb===="+remarkDb);
						
						if(feeStatus.equalsIgnoreCase("null") || feeStatus.equalsIgnoreCase("Select") || feeStatus.equalsIgnoreCase("None") || feeStatus.equalsIgnoreCase("-")){
//							feeStatus = "-";
							feeStatus = feeStatusDb;
						}
						else if(feeStatus.equalsIgnoreCase("From Fee Status")){
							feeStatus = feeStatusDb;
						}
						if(!feeStatusDb.equalsIgnoreCase(feeStatus) && !feeStatus.trim().equalsIgnoreCase("") && 
								!feeStatus.trim().equalsIgnoreCase("-")){
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
							remarkStatus = feeStatusDb.trim();
						} else if(feeStatusDb.equalsIgnoreCase("-")){
							remarkStatus = remarkDb.trim();
						} else {
							remarkStatus = remarkDb.trim() +", "+ feeStatusDb.trim();
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
						List remarkStatusList = commonLc.breakSentence(remarkStatus, 40);
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
						
			//			LineSeparator sep = new LineSeparator();
			//			sep.setOffset(5);
			//			Paragraph paraSep = new Paragraph();
			//			paraSep.setSpacingAfter(7);
			//			paraSep.add(sep);
			//			document.add(paraSep);
						
						Chunk sep = new Chunk(" ");
						Paragraph paraSep = new Paragraph();
//						paraSep.getPaddingTop(105);
//						paraSep.setSpacingBefore(250);
						paraSep.setSpacingAfter(90);
						paraSep.add(sep);
						paraSep.setAlignment(Element.ALIGN_LEFT);
						document.add(paraSep);
						
						Chunk sep01 = new Chunk(separator);
						Paragraph paraSep01 = new Paragraph();
//						paraSep01.getPaddingTop(105);
//						paraSep01.setSpacingBefore(250);
//						paraSep01.setSpacingAfter(150);
						paraSep01.add(sep01);
						paraSep01.setAlignment(Element.ALIGN_LEFT);
						document.add(paraSep01);
						
						Chunk chunkGrNo = new Chunk("    G.R. No.:  "+grDb);
						/*Font fontGrNo = new Font(Font.TIMES_ROMAN);
						fontGrNo.setStyle(Font.BOLD);
						fontGrNo.setSize(16);
						fontGrNo.setColor(Color.BLACK);*/
						chunkGrNo.setFont(fontClass1);
						
						Paragraph paraGrNo = new Paragraph();
						paraGrNo.setSpacingBefore(-1);
		//				paraGrNo.setSpacingAfter(0);
						paraGrNo.add(chunkGrNo);
						paraGrNo.setAlignment(Element.ALIGN_LEFT);
						document.add(paraGrNo);
						
		//				if(!origLcDb.equalsIgnoreCase("") && !origLcDb.equalsIgnoreCase("NA")  && !origLcDb.equalsIgnoreCase(null) && 
		//						!view_save.equalsIgnoreCase("view") && !view_save.equalsIgnoreCase("validate"))
		//				{
		//					Chunk chunkDUPLICATE = new Chunk("                                                    DUPLICATE ");
		//					Font fontDUPLICATE = new Font(Font.TIMES_ROMAN);
		//					fontDUPLICATE.setStyle(Font.BOLD);
		//					fontDUPLICATE.setSize(16);
		//					fontDUPLICATE.setColor(Color.BLACK);
		//					chunkDUPLICATE.setFont(fontDUPLICATE);
		//					Paragraph paraDUPLICATE = new Paragraph();
		//					paraDUPLICATE.setSpacingBefore(-16);
		////					paraDUPLICATE.setSpacingAfter(-16);
		//					paraDUPLICATE.add(chunkDUPLICATE);
		//					paraDUPLICATE.setAlignment(Element.ALIGN_LEFT);
		//					document.add(paraDUPLICATE);
		//				}
		//				else 
						if(lcType.equalsIgnoreCase("DUPLICATE"))
						{
							Chunk chunkDUPLICATE = new Chunk("                                                    DUPLICATE ");
							/*Font fontDUPLICATE = new Font(Font.TIMES_ROMAN);
							fontDUPLICATE.setStyle(Font.BOLD);
							fontDUPLICATE.setSize(16);
							fontDUPLICATE.setColor(Color.BLACK);*/
							chunkDUPLICATE.setFont(fontClass1);
							Paragraph paraDUPLICATE = new Paragraph();
							paraDUPLICATE.setSpacingBefore(-16);
		//					paraDUPLICATE.setSpacingAfter(-16);
							paraDUPLICATE.add(chunkDUPLICATE);
							paraDUPLICATE.setAlignment(Element.ALIGN_LEFT);
							document.add(paraDUPLICATE);
						} 
						else if(lcType.equalsIgnoreCase("TRIPLICATE"))
						{
							Chunk chunkTRIPLICATE = new Chunk("                                                    TRIPLICATE ");
							/*Font fontTRIPLICATE = new Font(Font.TIMES_ROMAN);
							fontTRIPLICATE.setStyle(Font.BOLD);
							fontTRIPLICATE.setSize(16);
							fontTRIPLICATE.setColor(Color.BLACK);*/
							chunkTRIPLICATE.setFont(fontClass1);
							Paragraph paraTRIPLICATE = new Paragraph();
							paraTRIPLICATE.setSpacingBefore(-16);
		//					paraTRIPLICATE.setSpacingAfter(-16);
							paraTRIPLICATE.add(chunkTRIPLICATE);
							paraTRIPLICATE.setAlignment(Element.ALIGN_LEFT);
							document.add(paraTRIPLICATE);
						}
						
						Chunk chunkLcNoTitle = new Chunk("                                                                           " +
														 "           L.C. No. :");
						/*Font fontLcNoTitle = new Font(Font.TIMES_ROMAN);
						fontLcNoTitle.setStyle(Font.BOLD);
						fontLcNoTitle.setSize(16);
						fontLcNoTitle.setColor(Color.BLACK);*/
						chunkLcNoTitle.setFont(fontClass1);
						Paragraph paraLcNoTitle = new Paragraph();
						paraLcNoTitle.setSpacingBefore(-16);
						paraLcNoTitle.add(chunkLcNoTitle);
						paraLcNoTitle.setAlignment(Element.ALIGN_LEFT);
						document.add(paraLcNoTitle);
						
						Chunk chunkLcNo = new Chunk("                                                                           " +
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
						paraSep1.setSpacingBefore(-5);
						paraSep1.add(sep1);
						paraSep1.setAlignment(Element.ALIGN_LEFT);
						document.add(paraSep1);
						
						Chunk chunkTitleName = new Chunk("      01.   Name Of The Pupil In Full");
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
						
						Chunk chunkName = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(nameDb));
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
						
						Chunk chunkTitleMother = new Chunk("      02.   Mother Name");
						/*Font fontTitleMother = new Font(Font.TIMES_ROMAN);
						fontTitleMother.setStyle(Font.BOLD);
						fontTitleMother.setStyle(Font.ITALIC);
						fontTitleMother.setSize(13);
						fontTitleMother.setColor(Color.BLACK);*/
						chunkTitleMother.setFont(fontClass2);
						
						Paragraph paraTitleMother = new Paragraph();	
						paraTitleMother.setSpacingBefore(8);
						paraTitleMother.add(chunkTitleMother);
						paraTitleMother.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleMother);
						
						Chunk chunkMother = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(motherNameDb));
						/*Font fontMother = new Font(Font.TIMES_ROMAN);
						fontMother.setStyle(Font.BOLD);
						fontMother.setStyle(Font.ITALIC);
						fontMother.setSize(13);
						fontMother.setColor(Color.BLACK);*/
						chunkMother.setFont(fontClass2);
						
						Paragraph paraMother = new Paragraph();	
						paraMother.setSpacingBefore(-16);
						paraMother.add(chunkMother);
						paraMother.setAlignment(Element.ALIGN_LEFT);
						document.add(paraMother);
						
						Chunk chunkTitleReligion = new Chunk("      03.   Caste and sub-Cast");
						/*Font fontTitleReligion = new Font(Font.TIMES_ROMAN);
						fontTitleReligion.setStyle(Font.BOLD);
						fontTitleReligion.setStyle(Font.ITALIC);
						fontTitleReligion.setSize(13);
						fontTitleReligion.setColor(Color.BLACK);*/
						chunkTitleReligion.setFont(fontClass2);
						
						Paragraph paraTitleReligion = new Paragraph();	
						paraTitleReligion.setSpacingBefore(8);
						paraTitleReligion.add(chunkTitleReligion);
						paraTitleReligion.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleReligion);
						
						if(religionDb.equalsIgnoreCase("-")){
							religionDb = "";
						}
						else{
							religionDb = religionDb + " ";
						}
						if(castDb.equalsIgnoreCase("-")){
							castDb = "";
						}
						Chunk chunkReligion = new Chunk("                                                                     :       "+  
															commonLc.FirstWordCap(religionDb) + commonLc.FirstWordCap(castDb));
						/*Font fontReligion = new Font(Font.TIMES_ROMAN);
						fontReligion.setStyle(Font.BOLD);
						fontReligion.setStyle(Font.ITALIC);
						fontReligion.setSize(13);
						fontReligion.setColor(Color.BLACK);*/
						chunkReligion.setFont(fontClass2);
						
						Paragraph paraReligion = new Paragraph();	
						paraReligion.setSpacingBefore(-16);
						paraReligion.add(chunkReligion);
						paraReligion.setAlignment(Element.ALIGN_LEFT);
						document.add(paraReligion);
						
						Chunk chunkCondition1 = new Chunk("                    (Only in the case of pupils belonging to ");
						/*Font fontCondition1 = new Font(Font.TIMES_ROMAN);
						fontCondition1.setStyle(Font.BOLD);
						fontCondition1.setStyle(Font.ITALIC);
						fontCondition1.setSize(9);
						fontCondition1.setColor(Color.BLACK);*/
						chunkCondition1.setFont(fontClass5);
					
						Paragraph paraCondition1 = new Paragraph();	
						paraCondition1.setSpacingBefore(-8);
						paraCondition1.add(chunkCondition1);
						paraCondition1.setAlignment(Element.ALIGN_LEFT);
						document.add(paraCondition1);
						
						Chunk chunkCondition2 = new Chunk("                    Backward Classes and category among ");
						/*Font fontCondition2 = new Font(Font.TIMES_ROMAN);
						fontCondition2.setStyle(Font.BOLD);
						fontCondition2.setStyle(Font.ITALIC);
						fontCondition2.setSize(9);
						fontCondition2.setColor(Color.BLACK);*/
						chunkCondition2.setFont(fontClass5);
					
						Paragraph paraCondition2 = new Paragraph();	
						paraCondition2.setSpacingBefore(-8);
						paraCondition2.add(chunkCondition2);
						paraCondition2.setAlignment(Element.ALIGN_LEFT);
						document.add(paraCondition2);
						
						Chunk chunkCondition2a = new Chunk("                    Backward Classes (e.g. S.C./S.T. etc))");
						/*Font fontCondition2a = new Font(Font.TIMES_ROMAN);
						fontCondition2a.setStyle(Font.BOLD);
						fontCondition2a.setStyle(Font.ITALIC);
						fontCondition2a.setSize(9);
						fontCondition2a.setColor(Color.BLACK);*/
						chunkCondition2a.setFont(fontClass5);
					
						Paragraph paraCondition2a = new Paragraph();	
						paraCondition2a.setSpacingBefore(-8);
						paraCondition2a.add(chunkCondition2a);
						paraCondition2a.setAlignment(Element.ALIGN_LEFT);
						document.add(paraCondition2a);
						
						/*Chunk chunkTitleReligion = new Chunk("      03.   Religion");
						Font fontTitleReligion = new Font(Font.TIMES_ROMAN);
						fontTitleReligion.setStyle(Font.BOLD);
						fontTitleReligion.setStyle(Font.ITALIC);
						fontTitleReligion.setSize(13);
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
						fontReligion.setSize(13);
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
						fontTitleCaste.setSize(13);
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
						fontCaste.setSize(13);
						fontCaste.setColor(Color.BLACK);
						chunkCaste.setFont(fontCaste);
						
						Paragraph paraCaste = new Paragraph();	
						paraCaste.setSpacingBefore(-16);
						paraCaste.add(chunkCaste);
						paraCaste.setAlignment(Element.ALIGN_LEFT);
						document.add(paraCaste);*/
						
		//				Chunk chunkTitleCaste2 = new Chunk("      BELONGING TO BACKWARD CLASSES AND");
		//				Font fontTitleCaste2 = new Font(Font.TIMES_ROMAN);
		//				fontTitleCaste2.setStyle(Font.BOLD);
		//				fontTitleCaste2.setSize(13);
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
		//				fontTitleCaste3.setSize(13);
		//				fontTitleCaste3.setColor(Color.BLACK);
		//				chunkTitleCaste3.setFont(fontTitleCaste3);
		//			
		//				Paragraph paraTitleCaste3 = new Paragraph();	
		//				paraTitleCaste3.setSpacingBefore(0);
		//				paraTitleCaste3.add(chunkTitleCaste3);
		//				paraTitleCaste3.setAlignment(Element.ALIGN_LEFT);
		//				document.add(paraTitleCaste3);
						
						Chunk chunkTitleNationality = new Chunk("      04.   Nationality");
						/*Font fontTitleNationality = new Font(Font.TIMES_ROMAN);
						fontTitleNationality.setStyle(Font.BOLD);
						fontTitleNationality.setStyle(Font.ITALIC);
						fontTitleNationality.setSize(13);
						fontTitleNationality.setColor(Color.BLACK);*/
						chunkTitleNationality.setFont(fontClass2);
						
						Paragraph paraTitleNationality = new Paragraph();	
						paraTitleNationality.setSpacingBefore(4);
						paraTitleNationality.add(chunkTitleNationality);
						paraTitleNationality.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleNationality);
						
						Chunk chunkNationality = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(nationalityDb));
						/*Font fontNationality = new Font(Font.TIMES_ROMAN);
						fontNationality.setStyle(Font.BOLD);
						fontNationality.setStyle(Font.ITALIC);
						fontNationality.setSize(13);
						fontNationality.setColor(Color.BLACK);*/
						chunkNationality.setFont(fontClass2);
						
						Paragraph paraNationality = new Paragraph();	
						paraNationality.setSpacingBefore(-16);
						paraNationality.add(chunkNationality);
						paraNationality.setAlignment(Element.ALIGN_LEFT);
						document.add(paraNationality);
						
						Chunk chunkTitlePOB = new Chunk("      05.   Place Of Birth");
						/*Font fontTitlePOB = new Font(Font.TIMES_ROMAN);
						fontTitlePOB.setStyle(Font.BOLD);
						fontTitlePOB.setStyle(Font.ITALIC);
						fontTitlePOB.setSize(13);
						fontTitlePOB.setColor(Color.BLACK);*/
						chunkTitlePOB.setFont(fontClass2);
						
						Paragraph paraTitlePOB = new Paragraph();	
						paraTitlePOB.setSpacingBefore(8);
						paraTitlePOB.add(chunkTitlePOB);
						paraTitlePOB.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitlePOB);
						
						Chunk chunkPOB = new Chunk("                                                                     :       "+commonLc.FirstWordCap(birthPlaceDb));
						/*Font fontPOB = new Font(Font.TIMES_ROMAN);
						fontPOB.setStyle(Font.BOLD);
						fontPOB.setStyle(Font.ITALIC);
						fontPOB.setSize(13);
						fontPOB.setColor(Color.BLACK);*/
						chunkPOB.setFont(fontClass2);
						
						Paragraph paraPOB = new Paragraph();	
						paraPOB.setSpacingBefore(-16);
						paraPOB.add(chunkPOB);
						paraPOB.setAlignment(Element.ALIGN_LEFT);
						document.add(paraPOB);
						
						Chunk chunkTitleDOB1 = new Chunk("      06.   Date Of Birth (In Figure)");
						/*Font fontTitleDOB1 = new Font(Font.TIMES_ROMAN);
						fontTitleDOB1.setStyle(Font.BOLD);
						fontTitleDOB1.setStyle(Font.ITALIC);
						fontTitleDOB1.setSize(13);
						fontTitleDOB1.setColor(Color.BLACK);*/
						chunkTitleDOB1.setFont(fontClass2);
						
						Paragraph paraTitleDOB1 = new Paragraph();	
						paraTitleDOB1.setSpacingBefore(8);
						paraTitleDOB1.add(chunkTitleDOB1);
						paraTitleDOB1.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleDOB1);
						
		//				dobDb = commonLc.MMM_ddmmyyy(dobDb);
						Chunk chunkDOB = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(dobDb));
						/*Font fontDOB = new Font(Font.TIMES_ROMAN);
						fontDOB.setStyle(Font.BOLD);
						fontDOB.setStyle(Font.ITALIC);
						fontDOB.setSize(13);
						fontDOB.setColor(Color.BLACK);*/
						chunkDOB.setFont(fontClass2);
						
						Paragraph paraTitleDOB = new Paragraph();	
						paraTitleDOB.setSpacingBefore(-16);
						paraTitleDOB.add(chunkDOB);
						paraTitleDOB.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleDOB);
						
						Chunk chunkTitleDOB2 = new Chunk("      07.   Date Of Birth (In Word)");
						/*Font fontTitleDOB2 = new Font(Font.TIMES_ROMAN);
						fontTitleDOB2.setStyle(Font.BOLD);
						fontTitleDOB2.setStyle(Font.ITALIC);
						fontTitleDOB2.setSize(13);
						fontTitleDOB2.setColor(Color.BLACK);*/
						chunkTitleDOB2.setFont(fontClass2);
						
						Paragraph paraTitleDOB2 = new Paragraph();	
						paraTitleDOB2.setSpacingBefore(8);
						paraTitleDOB2.add(chunkTitleDOB2);
						paraTitleDOB2.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleDOB2);
						
						Chunk chunkDOB_Word = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(dobWords1));
						/*Font fontDOB_Word = new Font(Font.TIMES_ROMAN);
						fontDOB_Word.setStyle(Font.BOLD);
						fontDOB_Word.setStyle(Font.ITALIC);
						fontDOB_Word.setSize(13);
						fontDOB_Word.setColor(Color.BLACK);*/
						chunkDOB_Word.setFont(fontClass2);
						
						Paragraph paraTitleDOB_Word = new Paragraph();	
						paraTitleDOB_Word.setSpacingBefore(-16);
						paraTitleDOB_Word.add(chunkDOB_Word);
						paraTitleDOB_Word.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleDOB_Word);
						
						if(!dobWords2.equalsIgnoreCase("") && !dobWords2.equalsIgnoreCase("null")){
							Chunk chunkDOB1_Word = new Chunk  ("                                                                             "+commonLc.FirstWordCap(dobWords2));
							/*Font fontDOB1_Word = new Font(Font.TIMES_ROMAN);
							fontDOB1_Word.setStyle(Font.BOLD);
							fontDOB1_Word.setStyle(Font.ITALIC);
							fontDOB1_Word.setSize(13);
							fontDOB1_Word.setColor(Color.BLACK);*/
							chunkDOB1_Word.setFont(fontClass2);
							
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
		//				fontTitleDOB3.setSize(13);
		//				fontTitleDOB3.setColor(Color.BLACK);
		//				chunkTitleDOB3.setFont(fontTitleDOB3);
		//				
		//				Paragraph paraTitleDOB3 = new Paragraph();	
		//				paraTitleDOB3.setSpacingBefore(0);
		//				paraTitleDOB3.add(chunkTitleDOB3);
		//				paraTitleDOB3.setAlignment(Element.ALIGN_LEFT);
		//				document.add(paraTitleDOB3);
						
						
						
						Chunk chunkTitleLastSchool = new Chunk("      08.   Last School Attended");
						/*Font fontTitleLastSchool = new Font(Font.TIMES_ROMAN);
						fontTitleLastSchool.setStyle(Font.BOLD);
						fontTitleLastSchool.setStyle(Font.ITALIC);
						fontTitleLastSchool.setSize(13);
						fontTitleLastSchool.setColor(Color.BLACK);*/
						chunkTitleLastSchool.setFont(fontClass2);
						
						Paragraph paraTitleLastSchool = new Paragraph();	
						paraTitleLastSchool.setSpacingBefore(8);
						paraTitleLastSchool.add(chunkTitleLastSchool);
						paraTitleLastSchool.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleLastSchool);
						
						Chunk chunkLastSchool = new Chunk  ("                                                                     :       "+commonLc.FirstWordCap(lastSchool1));
						/*Font fontLastSchool = new Font(Font.TIMES_ROMAN);
						fontLastSchool.setStyle(Font.BOLD);
						fontLastSchool.setStyle(Font.ITALIC);
						fontLastSchool.setSize(13);
						fontLastSchool.setColor(Color.BLACK);*/
						chunkLastSchool.setFont(fontClass2);
						
						Paragraph paraLastSchool = new Paragraph();	
						paraLastSchool.setSpacingBefore(-16);
						paraLastSchool.add(chunkLastSchool);
						paraLastSchool.setAlignment(Element.ALIGN_LEFT);
						document.add(paraLastSchool);
						
						if(!lastSchool2.equalsIgnoreCase("") && !lastSchool2.equalsIgnoreCase("null")){
							Chunk chunkLastSchool2 = new Chunk  ("                                                                             "+commonLc.FirstWordCap(lastSchool2));
							/*Font fontLastSchool2 = new Font(Font.TIMES_ROMAN);
							fontLastSchool2.setStyle(Font.BOLD);
							fontLastSchool2.setStyle(Font.ITALIC);
							fontLastSchool2.setSize(13);
							fontLastSchool2.setColor(Color.BLACK);*/
							chunkLastSchool2.setFont(fontClass2);
							
							Paragraph paraTitleLastSchool2 = new Paragraph();	
							paraTitleLastSchool2.setSpacingBefore(0);
			//				paraTitleLastSchool2.add(chunkTitleLastSchool1);
							paraTitleLastSchool2.add(chunkLastSchool2);
							paraTitleLastSchool2.setAlignment(Element.ALIGN_LEFT);
							document.add(paraTitleLastSchool2);
						}
						
						Chunk chunkTitleAdmittedStd = new Chunk("      09.   Admitted to Std.");
						/*Font fontTitleAdmittedStd = new Font(Font.TIMES_ROMAN);
						fontTitleAdmittedStd.setStyle(Font.BOLD);
						fontTitleAdmittedStd.setStyle(Font.ITALIC);
						fontTitleAdmittedStd.setSize(13);
						fontTitleAdmittedStd.setColor(Color.BLACK);*/
						chunkTitleAdmittedStd.setFont(fontClass2);
						
						Paragraph paraTitleAdmittedStd = new Paragraph();	
						paraTitleAdmittedStd.setSpacingBefore(8);
						paraTitleAdmittedStd.add(chunkTitleAdmittedStd);
						paraTitleAdmittedStd.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleAdmittedStd);
						
						Chunk chunkAdmittedStd = new Chunk  ("                                                                     :       "+lcadmittedStdDb.toUpperCase());
						/*Font fontAdmittedStd = new Font(Font.TIMES_ROMAN);
						fontAdmittedStd.setStyle(Font.BOLD);
						fontAdmittedStd.setStyle(Font.ITALIC);
						fontAdmittedStd.setSize(13);
						fontAdmittedStd.setColor(Color.BLACK);*/
						chunkAdmittedStd.setFont(fontClass2);
						
						Paragraph paraAdmittedStd = new Paragraph();	
						paraAdmittedStd.setSpacingBefore(-16);
						paraAdmittedStd.add(chunkAdmittedStd);
						paraAdmittedStd.setAlignment(Element.ALIGN_LEFT);
						document.add(paraAdmittedStd);
						
						if(!AdmittedStd2.equalsIgnoreCase("") && !AdmittedStd2.equalsIgnoreCase("null")){
							Chunk chunkAdmittedStd2 = new Chunk  ("                                                                             "+commonLc.FirstWordCap(AdmittedStd2));
							/*Font fontAdmittedStd2 = new Font(Font.TIMES_ROMAN);
							fontAdmittedStd2.setStyle(Font.BOLD);
							fontAdmittedStd2.setStyle(Font.ITALIC);
							fontAdmittedStd2.setSize(13);
							fontAdmittedStd2.setColor(Color.BLACK);*/
							chunkAdmittedStd2.setFont(fontClass2);
							
							Paragraph paraTitleAdmittedStd2 = new Paragraph();	
							paraTitleAdmittedStd2.setSpacingBefore(0);
			//				paraTitleAdmittedStd2.add(chunkTitleAdmittedStd1);
							paraTitleAdmittedStd2.add(chunkAdmittedStd2);
							paraTitleAdmittedStd2.setAlignment(Element.ALIGN_LEFT);
							document.add(paraTitleAdmittedStd2);
						}
						
						Chunk chunkTitleDOA = new Chunk("      10.   Date Of Admission");
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
						
						Chunk chunkDOA = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(dateAdmittedDb));
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
						
						Chunk chunkTitleProgress = new Chunk("      11.   Progress");
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
						
						Chunk chunkProgress = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(progressDb));
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
						
						Chunk chunkTitleConduct = new Chunk("      12.   Conduct");
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
						
						Chunk chunkConduct = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(conductDb));
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
						
						Chunk chunkTitleDOL = new Chunk("      13.   Date Of Leaving");
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
						
					if(dateLeavingDb.equalsIgnoreCase("00-00-0000") || dateLeavingDb.equalsIgnoreCase("") || dateLeavingDb.equalsIgnoreCase("NA") || dateLeavingDb.equalsIgnoreCase("-") || dateLeavingDb.equalsIgnoreCase(null) || dateLeavingDb.equalsIgnoreCase("null")){
							dateLeavingDb = leavingDate;
						}
		//				dateLeavingDb = commonLc.MMM_ddmmyyy(dateLeavingDb);
						Chunk chunkDOL = new Chunk("                                                                     :       "+  dateLeavingDb);
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
						
						Chunk chunkTitleStandard = new Chunk("      14.   Std. In Which Studying & \n"
								+ "              Since when");
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
						
						Chunk chunkStandard = new Chunk("                                                                     :       "+ leavingStdDb + commonLc.FirstWordCap(stdWords+" Since "+studyingSinceDb));
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
						
			//			Chunk chunkTitleStandard = new Chunk("Standard In Which Studying : ");
			//			Font fontTitleStandard = new Font(Font.TIMES_ROMAN);
			//			fontTitleStandard.setStyle(Font.BOLD);
			//			fontTitleStandard.setSize(13);
			//			fontTitleStandard.setColor(Color.BLACK);
			//			chunkTitleStandard.setFont(fontTitleStandard);
			//			
			//			Chunk chunkStandard = new Chunk(leavingStdDb+"                                             ");
			//			Font fontStandard = new Font(Font.TIMES_ROMAN);
			//			fontStandard.setStyle(Font.NORMAL);
			//			fontStandard.setSize(13);
			//			fontStandard.setColor(Color.BLACK);
			//			chunkStandard.setFont(fontLcNo);
						
						/*
						Chunk chunkTitleSince = new Chunk("                                                                           " +
						 "                        SINCE");
						Font fontTitleSince = new Font(Font.TIMES_ROMAN);
						fontTitleSince.setStyle(Font.BOLD);
						fontTitleSince.setSize(13);
						fontTitleSince.setColor(Color.BLACK);
						chunkTitleSince.setFont(fontTitleSince);
						*/ 
						
		//				if(studyingSinceDb.equalsIgnoreCase("") || studyingSinceDb.equalsIgnoreCase("NA")){
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
			//			if(studyingSinceDb.equalsIgnoreCase("") || studyingSinceDb.equalsIgnoreCase("NA")){
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
						/*Font fontTitleReason = new Font(Font.TIMES_ROMAN);
						fontTitleReason.setStyle(Font.BOLD);
						fontTitleReason.setStyle(Font.ITALIC);
						fontTitleReason.setSize(13);
						fontTitleReason.setColor(Color.BLACK);*/
						chunkTitleReason.setFont(fontClass2);
						
						Paragraph paraTitleReason = new Paragraph();	
						paraTitleReason.setSpacingBefore(18);
						paraTitleReason.add(chunkTitleReason);
						paraTitleReason.setAlignment(Element.ALIGN_LEFT);
						document.add(paraTitleReason);
						
						Chunk chunkReason = new Chunk("                                                                     :       "+  commonLc.FirstWordCap(reasonDb));
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
						
						Chunk chunkTitleRemark = new Chunk("      16.  Remark");
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
						} else if(leavingStdDb.equalsIgnoreCase("II") || romanStd.equalsIgnoreCase("II")){
							stdStar = "IInd";
						} else if(leavingStdDb.equalsIgnoreCase("III") || romanStd.equalsIgnoreCase("III")){
							stdStar = "IIIrd";
						} else if(!leavingStdDb.equalsIgnoreCase("JR.KG") && !leavingStdDb.equalsIgnoreCase("SR.KG")){
							stdStar = leavingStdDb+"th";
							if(payremark.contains("promoted") || payremark.contains("PROMOTED") || remarkLc.contains("promoted") || remarkLc.contains("PROMOTED")){
								stdStar = romanStd+"th";
							}
						}
						payremark = commonLc.FirstWordCap(payremark);
						payremark = payremark.replace("****", stdStar);
						Chunk chunkRemark = new Chunk("                                                                     :       "+  payremark);
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
						remarkLc = commonLc.FirstWordCap(remarkLc);
						remarkLc = remarkLc.replace("****", stdStar);
						Chunk chunkRemark1 = new Chunk("                                                                             "+  remarkLc);
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
							paraTitleDated.add(chunkTitleDated);
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
							
						Chunk chunkCertified = new Chunk("     Certified that the above information is in accordance with the School Register.");
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
						
						Chunk chunkTitleDate = new Chunk("     Date : ");
						/*Font fontTitleDate = new Font(Font.TIMES_ROMAN);
						fontTitleDate.setStyle(Font.BOLD);
						fontTitleDate.setSize(13);
						fontTitleDate.setColor(Color.BLACK);*/
						chunkTitleDate.setFont(fontClass6);
						
						if(issueDate.equalsIgnoreCase("") || issueDate.equalsIgnoreCase("null")){
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
							onEndPage(writer, document, "", separator, null, fontClass4, 150, 290);
							String showOriginal = "Original L.C.No. : "+showOrigLcNo;
							onEndPage(writer, document, "", showOriginal, null, fontClass4, 140, 120);
							String originalDt = "(Dt : "+originalLcDateDb+")";
							onEndPage(writer, document, "", originalDt, null, fontClass3, 140, 245);
							
							if(lcType.equalsIgnoreCase("TRIPLICATE")){
								String duplicateLc = "/ Duplicate L.C.No. : "+showDupLcNo;
								onEndPage(writer, document, "", duplicateLc, null, fontClass4, 140, 376);
								String duplicateDt = "(Dt : "+duplicateLcDateDb+")";
								onEndPage(writer, document, "", duplicateDt, null, fontClass3, 140, 508);
							}
						}
						
						onEndPage(writer, document, "", separator, null, fontClass4, 132, 290);
						String certified = "     Certified that the above information is in accordance with the School Register.";
						onEndPage(writer, document, "", certified, null, fontClass7, 115, 250);
						String dateStr = "     Date : "+issueDate;
						onEndPage(writer, document, "", dateStr, null, fontClass7, 95, 95);
						String preparedBy = "Prepared By";
						onEndPage(writer, document, "", preparedBy, null, fontClass7, 35, 95);
						String checkedBy = "Checked By";
						onEndPage(writer, document, "", checkedBy, null, fontClass7, 35, 300);
						String headMaster = "Head Master";
						onEndPage(writer, document, "", headMaster, null, fontClass7, 35, 500);
						document.newPage();
			    			
				    }
				    document.close();
					
				    logger.info("view_save => "+view_save);
				//////insert into database//////////////
		//			if(view_save.equalsIgnoreCase("save")){
		//				boolean updateLc = false;
		//				try {
		////								updateLc = findStudentDB.updateLC(grDb, origLcNo, conductDb, dateLeavingDb, reasonDb, remarkDb, duplicateLcNo, duplicateLcDate, originalLcDate, studyingSinceDb, leavingStdDb);
		//					updateLc = findStudentDB.updateLC(selectedLcList,section);
		////								logger.info("LC No."+origLcNo+" created for Gr No.: "+grDb);
		//				} catch (Exception e) {
		//					logger.info("updateLC Exception == "+e);
		//				}
		//				if(!updateLc){
		////								JOptionPane.showMessageDialog(null, "LC not created for Gr No.: "+grDb); 
		//					JOptionPane.showMessageDialog(null, "Updation of LC failed..."); 
		//				}
		//			}
								///////////////////
					if(view_save.equalsIgnoreCase("validate") || view_save.equalsIgnoreCase("view")){
						try {
							String fileNameView 	= "LeavingCertificate"+view_save+commonLc.timeInMillis()+".pdf";
							String fileAddressView = path+fileNameView;
							
//							PdfReader reader;
							/*PdfReader reader = new PdfReader(fileAddress);
//							PdfStamper stamper;
							PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileAddressView));
							
//							reader 		= new PdfReader(fileAddress);
//							stamper 	= new PdfStamper(reader, new FileOutputStream(fileAddressView));
							
							if(view_save.equalsIgnoreCase("validate")){
//								stamper.setEncryption(null, null, PdfWriter.HideToolbar, PdfWriter.STANDARD_ENCRYPTION_40);
								stamper.setEncryption("my-owner-password".getBytes(), "my-user-password".getBytes(),
									    PdfWriter.AllowPrinting | PdfWriter.AllowCopy, PdfWriter.STRENGTH128BITS);
								stamper.close();
							}*/
							
							PdfReader pdfReader = new PdfReader(fileAddress);
						    //Modify file using PdfReader
						    PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(fileAddressView));
						    if(view_save.equalsIgnoreCase("validate")){
						    	pdfStamper.setEncryption(null, null, PdfWriter.HideToolbar, PdfWriter.STANDARD_ENCRYPTION_40);
						    }
						    pdfStamper.close();
							
							
							logger.info("file "+commonLc.deleteFile(fileAddress)+" deleted");
							fileName		=  fileNameView;
							fileAddress		=  fileAddressView;
							
						} catch (IOException e) {
							fileOpenFlag = true;
							logger.info("view file Exception == "+e);
							JOptionPane.showMessageDialog(null, "Please close "+fileName+" and try again"); 
						}
					}
		//		 	else{
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
			}
		} catch (Exception e) {
			logger.info("fetch data Exception == "+e);
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
				logger.info(":: -----Exception---- ::\n"+e);
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
