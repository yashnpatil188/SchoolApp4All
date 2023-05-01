package org.com.maauli;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;
import org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ResultPPRMarksPDF {
	ResourceBundle bundle    		= ResourceBundle.getBundle("org.com.accesser.school"); 
    static Logger logger 			= Logger.getLogger(ResultGradePDF.class.getName());
    static String secName 			= "";
    static String path				= "";
    static String fileName			= "";
    static String fileAddress		= "";
    static Common commonObj = new Common();
    
	public ResultPPRMarksPDF(SessionData sessionData, String sec, String academic, LinkedHashMap grStudentMap, 
			List<String> subjectTitleList, String exam,	String std, String div, String note, 
			Map<String,String> maxMarksMapOrder, Map<String,String> gradeMarksMapOrder) {

		System.gc();
		String spaceAfterSubjects = "";
		String resultHeaderSpace = "4", resultAcademicHeaderSpace = "120", resultStampSpace = "-85";
		String semester = "";
		int stdHeaderColSpan = 1;
		int nameHeaderColSpan = 6;
		boolean fileOpenFlag = false;
    	secName = bundle.getString(sec.toUpperCase()+"_SEC");
    	String headMaster = bundle.getString(sessionData.getDBName().toUpperCase()+"_PDF_FOOTER");
    	String result_header_1 = bundle.getString("RESULT_HEADER_1");
    	String result_header_2 = bundle.getString("RESULT_HEADER_2");
    	String result_header_3 = bundle.getString("RESULT_HEADER_3");
    	String result_header_4 = bundle.getString("RESULT_HEADER_4");
    	String result_header_5 = bundle.getString("RESULT_HEADER_5");
    	String result_header_6 = bundle.getString("CLASS_ALLOT_HEADER_SCHOOL");
    	path = commonObj.createTodayFolder(commonObj.getDriveName() + bundle.getString("RESULT_PDF_PATH_"+sessionData.getDBName()),true)+"/";
    	String img_path = bundle.getString("IMAGE_PATH");
    	String pdf_header_img_path = bundle.getString("IMAGE_PDF_RESULT_HEADER_"+sessionData.getAppType());
		int image_pdf_pos_x = Integer.parseInt(bundle.getString("IMAGE_PDF_RESULT_POS_X"));
		int image_pdf_pos_y = Integer.parseInt(bundle.getString("IMAGE_PDF_RESULT_POS_Y"));
		float image_pdf_scalepercent = Float.parseFloat(bundle.getString("IMAGE_PDF_RESULT_SCALEPERCENT"));
		String pdf_header_img_flag = bundle.getString("PDF_HEADER_RESULT_IMAGE_FLAG");
		String pdf_header_img_flag_final = bundle.getString("PDF_HEADER_RESULT_IMAGE_FLAG_FINAL");
		String pdf_header_txt_flag = bundle.getString("PDF_HEADER_RESULT_TEXT_FLAG");
		String filterFields = bundle.getString("FILTER_FIELDS_"+sessionData.getAppType().toUpperCase());
		String no_fail_count_result = bundle.getString("NO_FAIL_COUNT_RESULT");
		int stdInt = commonObj.RomanToInteger(std);
		
    	if (exam.equalsIgnoreCase("Semester 1")) {
			semester = "SEM1";
		} else if (exam.equalsIgnoreCase("Semester 2")) {
			semester = "SEM2";
		} else {
			if(pdf_header_img_flag_final.equalsIgnoreCase("false")){
				pdf_header_img_flag = "false";
				pdf_header_txt_flag = "false";
			}
			semester = "FINAL";
		}
    	
    	int noOfSubjects = subjectTitleList.size();
    	int studentSubjects = noOfSubjects;
    	
    	for(int i=0; i < (4-noOfSubjects); i++){
    		subjectTitleList.add(" ");
    	}
    	noOfSubjects = subjectTitleList.size();
    	
		try {
			///////start of pdf creation/////////////////////
			if(sessionData.getConfigMap() != null && sessionData.getConfigMap().get("RESULT_PDF_HEADER_SPACE")!= null) {
				resultHeaderSpace = sessionData.getConfigMap().get("RESULT_PDF_HEADER_SPACE");
			}
			
			if(sessionData.getConfigMap() != null && sessionData.getConfigMap().get("RESULT_PDF_ANNUAL_YEAR_HEADER_SPACE")!= null) {
				resultAcademicHeaderSpace = sessionData.getConfigMap().get("RESULT_PDF_ANNUAL_YEAR_HEADER_SPACE");
			}
			
			if(sessionData.getConfigMap() != null && sessionData.getConfigMap().get("RESULT_PDF_STAMP_SPACE")!= null) {
				resultStampSpace = sessionData.getConfigMap().get("RESULT_PDF_STAMP_SPACE");
			}
			
			String[] filterFieldsList = filterFields.split(",");
			LinkedHashMap<String, String> filterFieldsMap = new LinkedHashMap<String, String>(); 
			int filterTableCount = 0;
			for(int j = 0; j < filterFieldsList.length; j++){
				filterFieldsMap.put(filterFieldsList[j], "");
				if(filterFieldsList[j].equalsIgnoreCase("grNo") || filterFieldsList[j].equalsIgnoreCase("birthDate")){
					filterTableCount = filterTableCount + 2;
				}
			}
			
			fileName  = "ResultPrint"+commonObj.timeInMillis()+".pdf";
			fileAddress = path+fileName;
			File file = new File(fileAddress);
			FileOutputStream fileout = new FileOutputStream(file);
							
			Document document = new Document(com.itextpdf.text.PageSize.A4);
//			PdfWriter.getInstance(document, fileout);
			PdfWriter writer = PdfWriter.getInstance(document, fileout);
			document.addHeader("ABC", "XYZ");
//			document.addAuthor("MAAULI SOFTWARE SOLUTIONS");
			document.addTitle("Result");
			document.open();
			
			//for column width size 
			float[] columnWidths = new float[] {12f, 12f, 8f, 10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f};;
            
			// Get a set of the entries
			Set set = grStudentMap.entrySet();
			// Get an iterator
			Iterator i = set.iterator();
		      
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
		        String grNo = me.getKey().toString();
		        LinkedHashMap studentData = new LinkedHashMap();
		        studentData = (LinkedHashMap) me.getValue();
		         
				Chunk chunk01 = new Chunk(result_header_1);
				Font font01 = FontFactory.getFont("TIMES_ROMAN");
				font01.setStyle(Font.BOLD);
				font01.setSize(12);
				chunk01.setFont(font01);
				Paragraph paragraph01 = new Paragraph();
				paragraph01.add(chunk01);
				paragraph01.setAlignment(Element.ALIGN_CENTER);
				paragraph01.setSpacingBefore(20);
	
				Chunk chunk02 = new Chunk(result_header_2);
				Font font02 = FontFactory.getFont("TIMES_ROMAN");
				font02.setStyle(Font.BOLD);
				font02.setSize(20);
				font02.setColor(BaseColor.BLACK);
				chunk02.setFont(font02);
				Paragraph paragraph02 = new Paragraph();
				paragraph02.add(chunk02);
				paragraph02.setAlignment(Element.ALIGN_CENTER);
				paragraph02.setSpacingBefore(Integer.parseInt(resultHeaderSpace));
				paragraph02.setSpacingAfter(-64);
	
				Chunk chunk03 = new Chunk(result_header_3);
				Font font03 = FontFactory.getFont("TIMES_ROMAN");
				font03.setStyle(Font.NORMAL);
				font03.setSize(10);
				chunk03.setFont(font03);
				Paragraph paragraph03 = new Paragraph();
				paragraph03.add(chunk03);
				paragraph03.setAlignment(Element.ALIGN_CENTER);
				paragraph03.setSpacingBefore(0);
	
				Chunk chunk04 = new Chunk(result_header_4);
				Font font04 = FontFactory.getFont("TIMES_ROMAN");
				font04.setStyle(Font.NORMAL);
				font04.setSize(10);
				chunk04.setFont(font04);
				Paragraph paragraph04 = new Paragraph();
				paragraph04.add(chunk04);
				paragraph04.setAlignment(Element.ALIGN_CENTER);
	
				Chunk chunk05 = new Chunk(result_header_5);
				Font font05 = FontFactory.getFont("TIMES_ROMAN");
				font05.setStyle(Font.NORMAL);
				font05.setSize(10);
				chunk05.setFont(font05);
				Paragraph paragraph05 = new Paragraph();
				paragraph05.add(chunk05);
				paragraph05.setAlignment(Element.ALIGN_CENTER);
				
				Chunk chunkHeader = new Chunk(result_header_6);
				Font fontHeader = FontFactory.getFont("TIMES_ROMAN");
				fontHeader.setStyle(Font.BOLD);
				fontHeader.setSize(16);
				chunkHeader.setFont(fontHeader);
				Paragraph paragraphHeader = new Paragraph();
				paragraphHeader.add(chunkHeader);
				paragraphHeader.setAlignment(Element.ALIGN_CENTER);
				paragraphHeader.setSpacingAfter(-80);
				
				Chunk sep = new Chunk(" ");
				Paragraph paraSep = new Paragraph();
				paraSep.setSpacingAfter(-25);
				paraSep.add(sep);
				paraSep.setAlignment(Element.ALIGN_LEFT);
	
				String resultName = exam;
				if(resultName.equalsIgnoreCase("Semester 1")){
					resultName = "First Semester";
				}
				else if(resultName.equalsIgnoreCase("Semester 2")){
					resultName = "Second Semester";
				}
				else if(resultName.equalsIgnoreCase("Final")){
					resultName = "Annual";
				}
				if(!std.equalsIgnoreCase("IX") && !std.equalsIgnoreCase("X") && !std.equalsIgnoreCase("XI") 
						&& !std.equalsIgnoreCase("XII") && !std.equalsIgnoreCase("JR KG") && !std.equalsIgnoreCase("SR KG") && exam.equalsIgnoreCase("Semester 2")){
					resultName = "Annual";
				}
				Chunk chunk06 = new Chunk(resultName.toUpperCase()+" RESULT "+academic);
				Font font06 = FontFactory.getFont("TIMES_ROMAN");
				font06.setStyle(Font.BOLD);
				font06.setSize(16);
				chunk06.setFont(font06);
				Paragraph paragraph06 = new Paragraph();
				paragraph06.add(chunk06);
				paragraph06.setAlignment(Element.ALIGN_CENTER);
				paragraph06.setSpacingBefore(Integer.parseInt(resultAcademicHeaderSpace));
	
				PdfPTable table=new PdfPTable(12);
				table.setWidthPercentage(100);

				if(stdInt < 0) {
					stdHeaderColSpan = 2;
					nameHeaderColSpan = 5;
				}
                 PdfPCell cell = new PdfPCell (new Paragraph ("Std.Div.", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
			      cell.setColspan (stdHeaderColSpan);
			      cell.setHorizontalAlignment (Element.ALIGN_CENTER);
			      cell.setPaddingBottom(5.0f);
			      table.addCell(cell);
			      
			      PdfPCell cell1 = new PdfPCell (new Paragraph ("Roll No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
			      cell1.setColspan (1);
			      cell1.setHorizontalAlignment (Element.ALIGN_CENTER);
			      table.addCell(cell1);
			      
			      
			      PdfPCell cell2;
			      if(!filterFieldsMap.containsKey("grNo")){
			    	  cell2 = new PdfPCell (new Paragraph ("GR.No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				      cell2.setColspan (2);
				      cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
				      table.addCell(cell2);
			      }
			      
			      PdfPCell cell3 = new PdfPCell (new Paragraph ("Name", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
			      cell3.setColspan (nameHeaderColSpan + filterTableCount);
			      cell3.setHorizontalAlignment (Element.ALIGN_CENTER);
			      table.addCell(cell3);
			      
			      PdfPCell cell4;
			      if(!filterFieldsMap.containsKey("birthDate")){
			    	  cell4 = new PdfPCell (new Paragraph ("Date of Birth", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				      cell4.setColspan (2);
				      cell4.setHorizontalAlignment (Element.ALIGN_CENTER);
				      table.addCell(cell4);
			      }
			      
			      PdfPCell cell5 = new PdfPCell (new Paragraph (std+"-"+div, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
			      cell5.setColspan (stdHeaderColSpan);
			      cell5.setHorizontalAlignment (Element.ALIGN_CENTER);
			      cell5.setPaddingBottom(5.0f);
			      table.addCell(cell5);
			      
			      
			      PdfPCell cell6 = new PdfPCell (new Paragraph (studentData.get("rollNo").toString(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
			      cell6.setColspan (1);
			      cell6.setHorizontalAlignment (Element.ALIGN_CENTER);
			      cell6.setPaddingBottom(5.0f);
			      table.addCell(cell6);
			      
			      PdfPCell cell7;
			      if(!filterFieldsMap.containsKey("grNo")){
			    	  cell7 = new PdfPCell (new Paragraph (grNo, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				      cell7.setColspan (2);
				      cell7.setHorizontalAlignment (Element.ALIGN_CENTER);
				      cell7.setPaddingBottom(5.0f);
				      table.addCell(cell7);
			      }
			      
			      PdfPCell cell8 = new PdfPCell (new Paragraph (" "+
							studentData.get("lastName")+" "+studentData.get("firstName")+" "+studentData.get("fatherName")+"                          ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
			      cell8.setColspan (nameHeaderColSpan + filterTableCount);
			      cell8.setHorizontalAlignment (Element.ALIGN_CENTER);
			      cell8.setPaddingBottom(5.0f);
			      table.addCell(cell8);
			      
			      PdfPCell cell9;
			      if(!filterFieldsMap.containsKey("birthDate")){
			    	  cell9 = new PdfPCell (new Paragraph (studentData.get("birthDate").toString(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				      cell9.setColspan (2);
				      cell9.setHorizontalAlignment (Element.ALIGN_CENTER);
				      cell9.setPaddingBottom(5.0f);
				      table.addCell(cell9);
			      }
			      
			      PdfPCell cell10 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			      cell10.setColspan (12);
			      cell10.setHorizontalAlignment (Element.ALIGN_CENTER);
			      table.addCell(cell10);
			      
			      PdfPCell cell11 = new PdfPCell (new Paragraph ("SUBJECT", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
			      cell11.setColspan (6);
			      cell11.setHorizontalAlignment (Element.ALIGN_CENTER);
			      cell11.setPaddingBottom(5.0f);
			      table.addCell(cell11);
			      
			      PdfPCell cell12 = new PdfPCell (new Paragraph ("MAXIMUM", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));

			      cell12.setColspan (2);
			      cell12.setHorizontalAlignment (Element.ALIGN_CENTER);
			      table.addCell(cell12);
			      
			      PdfPCell cell13 = new PdfPCell (new Paragraph ("SEM I", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
			      cell13.setColspan (2);
			      cell13.setHorizontalAlignment (Element.ALIGN_CENTER);
			      table.addCell(cell13);
			      
			     
			      
			      PdfPCell cell14 = new PdfPCell (new Paragraph ("SEM II", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
			      cell14.setColspan (2);
			      cell14.setHorizontalAlignment (Element.ALIGN_CENTER);
			      table.addCell(cell14);
			      
			      String subjectMarksObtainedSem1 = "";
			      String subjectMarksObtainedSem2 = "";
			      String subjectName = "";
			      String subjectList = "\n";
			      String maxMarksList = "\n";
			      String minMarksList = "";
			      String marksList = "";
			      String subjectGradeList = "";
			      String maxMarksGradeList = "";
			      String minMarksGradeList = "";
			      String marksGradeList = "";
			      String optionalHeader = "";
			      optionalHeader = "Optional Subjects";
			      maxMarksGradeList = maxMarksGradeList + "\n \n";
			      minMarksGradeList = minMarksGradeList + "\n \n \n \n";
			      marksGradeList = marksGradeList + "\n \n \n";
			      String dispMarksSem1 = "";
			      String dispMarksSem2 = "";
			      String dispPassStatus = "";
			      String dispReason = "";
			      String dispAbsentMarks = "";
			      List<String> subjectTitleMatched = new ArrayList<String>();
			      List<String> subjectTitleMatchedGrade = new ArrayList<String>();
			      double maxTotalMarks = 0;
			      double maxIndividualMarks = 0;
			      double minIndividualMarks = 0;
			      double minTotalMarks = 0;
			      double obtainedSem1TotalMarks = 0;
			      double obtainedSem2TotalMarks = 0;
			      int noOfGradeSubjects = 0;
			      
			      Paragraph marksSubjectPara = new Paragraph ();
			      Paragraph maxMarksPara = new Paragraph ();
			      Paragraph minMarksPara = new Paragraph ();
			      Paragraph marksPara = new Paragraph ();
			      Paragraph optionalHeaderPara = new Paragraph ();
			      Paragraph optionalSubPara = new Paragraph ();
			      Paragraph optionalMarksPara = new Paragraph ();
			      Paragraph spaceAfterSubjectPara = new Paragraph ();
			      float gradeMarksSpace = -3.0f;
			      double avgDivisor = 0.0;
					for (String item : subjectTitleList) {
						if(!item.trim().equalsIgnoreCase("")){
							subjectMarksObtainedSem1 = studentData.get(item+"_SEM1").toString().equalsIgnoreCase("NA") ? "" : studentData.get(item+"_SEM1").toString();
							if(subjectMarksObtainedSem1.startsWith("NA") || subjectMarksObtainedSem1.equalsIgnoreCase("NA") || subjectMarksObtainedSem1.trim().equalsIgnoreCase("")){
								continue;
							}
							else{
								if(subjectMarksObtainedSem1.contains("~")){
									avgDivisor = Double.parseDouble(subjectMarksObtainedSem1.substring(subjectMarksObtainedSem1.indexOf("~")+1, subjectMarksObtainedSem1.indexOf("#")));
								}
								if(!subjectMarksObtainedSem1.equalsIgnoreCase("NA") && !commonObj.validateNumber(subjectMarksObtainedSem1) && 
										subjectMarksObtainedSem1.contains("(")){
									dispMarksSem1 = subjectMarksObtainedSem1.substring(0, subjectMarksObtainedSem1.indexOf("("));
									if(dispMarksSem1.contains("+") && !exam.equalsIgnoreCase("Final")){
										dispMarksSem1 = dispMarksSem1.substring(0, dispMarksSem1.indexOf("+"));
									}
									dispPassStatus = subjectMarksObtainedSem1.substring(subjectMarksObtainedSem1.indexOf("(")+1, subjectMarksObtainedSem1.indexOf("#"));
									dispReason = subjectMarksObtainedSem1.substring(subjectMarksObtainedSem1.indexOf("#")+1, subjectMarksObtainedSem1.indexOf("@"));
									dispAbsentMarks = subjectMarksObtainedSem1.substring(subjectMarksObtainedSem1.indexOf("@")+1, subjectMarksObtainedSem1.indexOf(")"));
									if(dispReason.equalsIgnoreCase("MG")){
										dispMarksSem1 = dispMarksSem1 + " (MG)";
									}
									else if(dispPassStatus.equalsIgnoreCase("F")){
										dispMarksSem1 = dispMarksSem1 + " (F)";
									}
								}
								else{
									dispMarksSem1 = subjectMarksObtainedSem1;
								}
								
								if(!dispMarksSem1.contains("+") && !dispMarksSem1.contains("#") && !dispMarksSem1.contains("(") && !dispMarksSem1.contains("-")) {
									obtainedSem1TotalMarks = obtainedSem1TotalMarks + Double.parseDouble(dispMarksSem1);
								}
								else if(dispPassStatus.equalsIgnoreCase("F") && gradeMarksMapOrder.get(item).toString().equalsIgnoreCase("Marks")){
									obtainedSem1TotalMarks = obtainedSem1TotalMarks + Double.parseDouble(dispMarksSem1.substring(0, dispMarksSem1.indexOf("(")));
								}
								else if(dispMarksSem1.contains("+") ){
									dispMarksSem1 = dispMarksSem1.substring(0, dispMarksSem1.indexOf("+"));
								}
							}
							
							if(!exam.equalsIgnoreCase("Semester 1")) {
								subjectMarksObtainedSem2 = studentData.get(item+"_SEM2").toString().equalsIgnoreCase("NA") ? "" : studentData.get(item+"_SEM2").toString();
								if(subjectMarksObtainedSem2.startsWith("NA") || subjectMarksObtainedSem2.equalsIgnoreCase("NA") || subjectMarksObtainedSem2.trim().equalsIgnoreCase("")){
									continue;
								}
								else{
									if(subjectMarksObtainedSem2.contains("~")){
										avgDivisor = Double.parseDouble(subjectMarksObtainedSem2.substring(subjectMarksObtainedSem2.indexOf("~")+1, subjectMarksObtainedSem2.indexOf("#")));
									}
									if(!subjectMarksObtainedSem2.equalsIgnoreCase("NA") && !commonObj.validateNumber(subjectMarksObtainedSem2) && 
											subjectMarksObtainedSem2.contains("(")){
										dispMarksSem2 = subjectMarksObtainedSem2.substring(0, subjectMarksObtainedSem2.indexOf("("));
										if(dispMarksSem2.contains("+") && !exam.equalsIgnoreCase("Final")){
											dispMarksSem2 = dispMarksSem2.substring(0, dispMarksSem2.indexOf("+"));
										}
										dispPassStatus = subjectMarksObtainedSem2.substring(subjectMarksObtainedSem2.indexOf("(")+1, subjectMarksObtainedSem2.indexOf("#"));
										dispReason = subjectMarksObtainedSem2.substring(subjectMarksObtainedSem2.indexOf("#")+1, subjectMarksObtainedSem2.indexOf("@"));
										dispAbsentMarks = subjectMarksObtainedSem2.substring(subjectMarksObtainedSem2.indexOf("@")+1, subjectMarksObtainedSem2.indexOf(")"));
										if(dispReason.equalsIgnoreCase("MG")){
											dispMarksSem2 = dispMarksSem2 + " (MG)";
										}
										else if(dispPassStatus.equalsIgnoreCase("F")){
											dispMarksSem2 = dispMarksSem2 + " (F)";
										}
									}
									else {
										dispMarksSem2 = subjectMarksObtainedSem2;
									}
									
									if(!dispMarksSem2.contains("+") && !dispMarksSem2.contains("#") && !dispMarksSem2.contains("(") && !dispMarksSem2.contains("-")) {
										obtainedSem2TotalMarks = obtainedSem2TotalMarks + Double.parseDouble(dispMarksSem2);
									}
									else if(dispPassStatus.equalsIgnoreCase("F") && gradeMarksMapOrder.get(item).toString().equalsIgnoreCase("Marks")){
										obtainedSem2TotalMarks = obtainedSem2TotalMarks + Double.parseDouble(dispMarksSem2.substring(0, dispMarksSem2.indexOf("(")));
									}
									else if(dispMarksSem2.contains("+") ){
										dispMarksSem2 = dispMarksSem2.substring(0, dispMarksSem2.indexOf("+"));
									}
								}
							}
							
							if(gradeMarksMapOrder.get(item).toString().equalsIgnoreCase("Marks")){
								maxIndividualMarks = Integer.parseInt(maxMarksMapOrder.get(item));
								if(avgDivisor == 1.0 && exam.equalsIgnoreCase("Final")){
									maxIndividualMarks = maxIndividualMarks * 2;
								}
								maxTotalMarks = maxTotalMarks + maxIndividualMarks;
								subjectName = item.toString().equalsIgnoreCase("NA") ? "" : item.toString();
								maxIndividualMarks = Math.round(maxIndividualMarks);
								if(maxIndividualMarks == 0.0) {
									continue;
								}
//								subjectList = subjectList + "\n          "+commonObj.FirstWordCap(subjectName.replace("_", " "))+"\n";
								subjectList = subjectList + "\n          "+subjectName.replace("_", " ")+"\n";
								maxMarksList = maxMarksList + "\n       "+(int) maxIndividualMarks+"\n";
								minIndividualMarks = commonObj.roundEven(maxIndividualMarks*0.35);
								minTotalMarks = minTotalMarks + minIndividualMarks;
								minMarksList = minMarksList + "\n       "+dispMarksSem1+"\n";
								marksList = marksList + "\n    "+dispMarksSem2+"\n";
								subjectTitleMatched.add(item);
							}
							else{
								subjectName = item.toString().equalsIgnoreCase("NA") ? "" : item.toString();
								maxIndividualMarks = Integer.parseInt(maxMarksMapOrder.get(subjectName));
								if(maxIndividualMarks == 0.0) {
									continue;
								}
//								subjectGradeList = subjectGradeList + "          "+commonObj.FirstWordCap(subjectName).replace("_", " ")+"\n";
								subjectGradeList = subjectGradeList + "          "+subjectName.replace("_", " ")+"\n";
								maxMarksGradeList = maxMarksGradeList + "\n \n";
								minMarksGradeList = minMarksGradeList + "\n       "+dispMarksSem1+"\n";
								marksGradeList = marksGradeList + "\n    "+dispMarksSem2+"\n";
								gradeMarksSpace = gradeMarksSpace + 10.0f;
								subjectTitleMatchedGrade.add(item);
								noOfGradeSubjects = noOfGradeSubjects + 1;
							}
						}
					}
					if(noOfGradeSubjects < 3){
						marksGradeList = "\n"+marksGradeList;
					}
					if(!subjectList.equalsIgnoreCase("")){
						subjectList = subjectList.substring(0,subjectList.lastIndexOf("\n"));
					}
					if(!subjectGradeList.equalsIgnoreCase("")){
						subjectGradeList = subjectGradeList.substring(0,subjectGradeList.lastIndexOf("\n"));
					}
					maxMarksList = maxMarksList + maxMarksGradeList;
					minMarksList = minMarksList + minMarksGradeList;
					
					int subjectMatchSize = subjectTitleMatched.size();
					for(int k = 0; k < (subjectTitleList.size() - subjectMatchSize)+1; k++){
						subjectList = subjectList + "\n";
						maxMarksList = maxMarksList + "\n";
						minMarksList = minMarksList + "\n";
						if(k < (subjectTitleList.size() - subjectMatchSize)-3){
							marksList = marksList + "\n";
						}
					}
					
				  subjectList = subjectList + "\n \n";
				  Chunk chunk1 = new Chunk();
				  chunk1.append(subjectList);
				  Font font1 = FontFactory.getFont("TIMES_ROMAN");
				  font1.setStyle(Font.UNDEFINED);
				  font1.setSize(10);////changed font size to adjust large subject name
				  chunk1.setFont(font1);
				  marksSubjectPara.add(chunk1);
				  marksSubjectPara.setSpacingAfter(-5);
				  
				  Chunk chunkSpace = new Chunk("        ");
				  Chunk chunk2 = new Chunk();
				  Chunk chunk3 = new Chunk();
				  Chunk chunkGradeList = new Chunk();
				  optionalSubPara.setSpacingBefore(10.0f);
				  optionalMarksPara.setSpacingBefore(gradeMarksSpace);//17 for 2 subjects
				  
				  if(!subjectGradeList.equalsIgnoreCase("") && !filterFieldsMap.containsKey("optionalSubject")){
					  chunk2.append(optionalHeader);
					  chunk2.setUnderline(0.1f, -2f);
					  Font font2 = FontFactory.getFont("TIMES_ROMAN");
					  font2.setStyle(Font.BOLD);
					  font2.setSize(12);
					  chunk2.setFont(font2);
					  optionalHeaderPara.add(chunkSpace);
					  optionalHeaderPara.add(chunk2);
					  optionalHeaderPara.setSpacingAfter(-10);
				  }
				  else if(filterFieldsMap.containsKey("optionalSubject") && stdInt > 10){
					  optionalSubPara.setSpacingBefore(-25.0f);
					  optionalMarksPara.setSpacingBefore(-25.0f);
				  }
				  else if(filterFieldsMap.containsKey("optionalSubject")){
					  optionalSubPara.setSpacingBefore(-35.0f);
					  optionalMarksPara.setSpacingBefore(-25.0f);
				  }
				  
				  chunk3.append(subjectGradeList);
				  Font font3 = FontFactory.getFont("TIMES_ROMAN");
				  font3.setStyle(Font.UNDEFINED);
				  font3.setSize(10);////changed font size to adjust large subject name
				  chunk3.setFont(font3);
				  optionalSubPara.setLeading(20);
				  optionalSubPara.add(chunk3);
				  
				  for(int j=0; j < (10 - noOfSubjects); j++){
					  spaceAfterSubjects = spaceAfterSubjects + "\n";
				  }
				  Chunk spaceAfterSubjectsChunk = new Chunk();
				  spaceAfterSubjectsChunk.append(spaceAfterSubjects);
				  spaceAfterSubjectPara.add(spaceAfterSubjectsChunk);
				  spaceAfterSubjectPara.setSpacingAfter(-5);
				  
			      PdfPCell cell15 = new PdfPCell ();
			      marksSubjectPara.setLeading(10);
			      cell15.addElement(marksSubjectPara);
			      cell15.addElement(optionalHeaderPara);
			      cell15.addElement(optionalSubPara);
			      cell15.addElement(spaceAfterSubjectPara);
			      spaceAfterSubjects = "";
			      
			      cell15.setColspan (6);
			      cell15.setHorizontalAlignment (Element.ALIGN_LEFT);
			      cell15.setPadding (-8.0f);
			      table.addCell(cell15);
			      
			      Chunk maxMarksChunk = new Chunk();
			      maxMarksChunk.append(maxMarksList);
				  Font maxMarksFont = FontFactory.getFont("TIMES_ROMAN");
				  maxMarksFont.setStyle(Font.UNDEFINED);
				  maxMarksFont.setSize(12);
				  maxMarksChunk.setFont(maxMarksFont);
				  maxMarksPara.add(maxMarksChunk);
				  maxMarksPara.setSpacingAfter(-5);
				  maxMarksPara.setLeading(0, 0.83f);
				  
			      PdfPCell cell16 = new PdfPCell ();
			      cell16.addElement(maxMarksPara);
			      cell16.setColspan (2);
			      cell16.setHorizontalAlignment (Element.ALIGN_CENTER);
			      cell16.setPadding (-8.0f);
			      cell16.setPaddingLeft(10f);
			      table.addCell(cell16);
			      
			      Chunk minMarksChunk = new Chunk();
			      minMarksChunk.append(minMarksList);
				  Font minMarksFont = FontFactory.getFont("TIMES_ROMAN");
				  minMarksFont.setStyle(Font.UNDEFINED);
				  minMarksFont.setSize(12);
				  minMarksChunk.setFont(minMarksFont);
				  minMarksPara.add(minMarksChunk);
				  minMarksPara.setSpacingAfter(-5);
				  minMarksPara.setLeading(0, 0.83f);
				  
			      PdfPCell cell17 = new PdfPCell ();
			      cell17.addElement(minMarksPara);
			      cell17.setColspan (2);
			      cell17.setHorizontalAlignment (Element.ALIGN_CENTER);
			      cell17.setPaddingLeft(10f);
			      table.addCell(cell17);
			      
			      Chunk marksChunk = new Chunk();
			      marksChunk.append(marksList);
				  Font marksFont = FontFactory.getFont("TIMES_ROMAN");
				  marksFont.setStyle(Font.UNDEFINED);
				  marksFont.setSize(12);
				  marksChunk.setFont(marksFont);
				  marksPara.add(marksChunk);
				  marksPara.setSpacingAfter(-5);
				  marksPara.setLeading(0, 0.83f);
				  
				  chunkGradeList.append(marksGradeList);
				  Font fontGradeList = FontFactory.getFont("TIMES_ROMAN");
				  fontGradeList.setStyle(Font.UNDEFINED);
				  fontGradeList.setSize(12);
				  chunkGradeList.setFont(fontGradeList);
				  optionalMarksPara.setLeading(10);
				  optionalMarksPara.add(chunkGradeList);
				  
			      PdfPCell cell18 = new PdfPCell ();
			      cell18.addElement(marksPara);
			      cell18.addElement(optionalMarksPara);
			      cell18.setColspan (2);
			      cell18.setHorizontalAlignment (Element.ALIGN_CENTER);
			      cell18.setPaddingLeft(10f);
			      table.addCell(cell18);
			      
			      PdfPCell cell59 = new PdfPCell (new Paragraph ("          Total", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
			      cell59.setColspan (6);
			      cell59.setHorizontalAlignment (Element.ALIGN_LEFT);
			      cell59.setPaddingBottom(5.0f);
			      table.addCell(cell59);
			      
			      PdfPCell cell60 = new PdfPCell (new Paragraph (studentData.get("sem1Total").toString(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
			      cell60.setColspan (2);
			      cell60.setHorizontalAlignment (Element.ALIGN_CENTER);
			      cell60.setPaddingBottom(5.0f);
			      table.addCell(cell60);
			      
			      PdfPCell cell61 = new PdfPCell (new Paragraph ((int)obtainedSem1TotalMarks+"", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
			      cell61.setColspan (2);
			      cell61.setHorizontalAlignment (Element.ALIGN_CENTER);
			      cell61.setPaddingBottom(5.0f);
			      table.addCell(cell61);
			      
			      String obtainedTotalMarks = studentData.get("semMarksObtained").toString();
			      String obtainedSem2TotalMarksStr = (int)obtainedSem2TotalMarks + "";
			      if(!exam.equalsIgnoreCase("Semester 2") && obtainedSem2TotalMarksStr.equalsIgnoreCase("0")) {
			    	  obtainedSem2TotalMarksStr = "";
			      }
			      PdfPCell cell62 = new PdfPCell (new Paragraph ("       "+obtainedSem2TotalMarksStr, FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
			      cell62.setColspan (2);
			      cell62.setVerticalAlignment (Element.ALIGN_MIDDLE);
			      cell62.setPaddingBottom(5.0f);
			      table.addCell(cell62);
			      
			      String attendance = studentData.get("attendance").toString();
			      if(attendance.equalsIgnoreCase("NA") || attendance.equalsIgnoreCase("0/0") || attendance.equalsIgnoreCase("")){
			    	  attendance = "";
			      }

			      Chunk chunk101;
			      Paragraph paragraph101 = new Paragraph();
			      Paragraph paragraph101a = new Paragraph();
			      Paragraph paragraph102 = new Paragraph();
			      
			      if(!filterFieldsMap.containsKey("attendance")){
			    	  chunk101 = new Chunk("Attendance : "+attendance);
						Font font101 = FontFactory.getFont("TIMES_ROMAN");
						font101.setStyle(Font.BOLD);
						font101.setSize(10);
						chunk101.setFont(font01);
						paragraph101.add(chunk101);
						paragraph101.setAlignment(Element.ALIGN_LEFT);
						paragraph101.setSpacingBefore(10);
						paragraph101a.setSpacingBefore(-16);
						paragraph102.setSpacingBefore(4);
			      }
			      else{
			    	  if(!filterFieldsMap.containsKey("optionalSubject")){
			    		  paragraph101a.setSpacingBefore(10);
			    	  }
			    	  else{
			    		  paragraph101a.setSpacingBefore(20);
			    	  }
			    	  paragraph102.setSpacingBefore(-16);
			      }
					
					double percentage = 0.0;
					if(!studentData.get("sem1Percent").toString().equalsIgnoreCase("NA") && exam.equalsIgnoreCase("Semester 1")) {
						percentage = Double.parseDouble(studentData.get("sem1Percent").toString());
					}
					else if(!studentData.get("sem2Percent").toString().equalsIgnoreCase("NA") 
							&& (exam.equalsIgnoreCase("Semester 2") || exam.equalsIgnoreCase("Final"))) {
						percentage = Double.parseDouble(studentData.get("sem2Percent").toString());
					}
					Chunk chunk101a;
					if(!filterFieldsMap.containsKey("percentage")){
						String gradeFromPercent = commonObj.getGradeFromPercent(percentage, std);
						chunk101a = new Chunk("                                                                                                       "
								+ "   Grade :   "+gradeFromPercent);
						Font font101a = FontFactory.getFont("TIMES_ROMAN");
						font101a.setStyle(Font.BOLD);
						font101a.setSize(10);
						chunk101a.setFont(font01);
						paragraph101a.add(chunk101a);
						paragraph101a.setAlignment(Element.ALIGN_LEFT);
//						paragraph101a.setSpacingBefore(3f);
					}
					
					String conduct = studentData.get("conduct").toString().equalsIgnoreCase("NA") ? "-" : studentData.get("conduct").toString();
					Chunk chunk102 = new Chunk("Conduct : Good                                                                                                                  ");
					Font font102 = FontFactory.getFont("TIMES_ROMAN");
					font102.setStyle(Font.BOLD);
					font102.setSize(12);
					chunk102.setFont(font102);
					paragraph102.add(chunk102);
					paragraph102.setAlignment(Element.ALIGN_LEFT);
					
					String passFail = studentData.get("semResult").toString();
					if(no_fail_count_result.equalsIgnoreCase("true") && passFail.toLowerCase().contains("promoted") 
							&& passFail.startsWith("F")) {
						passFail = passFail.substring(passFail.indexOf("& ")+2);
					}
					String remark_0 = commonObj.getResultRemark(passFail, percentage);
					
					Chunk chunk102a;
					Paragraph paragraph102a = new Paragraph();
					if(!filterFieldsMap.containsKey("remark")){
						chunk102a = new Chunk("                                                                                                          Remark : "+remark_0);
						Font font102a = FontFactory.getFont("TIMES_ROMAN");
						font102a.setStyle(Font.BOLD);
						font102a.setSize(12);
						chunk102a.setFont(font102a);
						paragraph102a.add(chunk102a);
						paragraph102a.setAlignment(Element.ALIGN_LEFT);
						if(!filterFieldsMap.containsKey("attendance")){
							paragraph102a.setSpacingBefore(-15);
						}
						else {
							paragraph102a.setSpacingBefore(-0f);
						}
					}
					
					String romanStd = std;
					int stdPromoted = 0;
					if(passFail.contains("pass") || passFail.contains("Pass") || passFail.contains("PASS")){
						stdPromoted = commonObj.RomanToInteger(std)+1;
						romanStd = commonObj.IntegerToRoman("a"+stdPromoted);
						romanStd = romanStd+"-"+div;
					}
					String promotedStr = "Passed & Promoted to Std. : "+romanStd;
					
					if(!passFail.contains("pass") || !passFail.contains("Pass") || !passFail.contains("PASS")){
						promotedStr = "Detained in Std. : "+romanStd;
					}
					
					if(!exam.equalsIgnoreCase("Final") && !exam.equalsIgnoreCase("Semester 2")){
						romanStd = "";
						promotedStr = "";
					}
					if(passFail.contains("|")){
						passFail = passFail.substring(passFail.indexOf("|")+1);
					}
					
					Chunk chunk102b;
					Paragraph paragraph102b = new Paragraph();
					if(filterFieldsMap.containsKey("removeDiv") && passFail.contains("-")){
						passFail = passFail.substring(0, passFail.lastIndexOf("-"));
					}
					if(filterFieldsMap.containsKey("showEligible") && passFail.toUpperCase().contains("DETAINED")){
						passFail = "Eligible for Re-exam";
					}
					if(sessionData.getAppType().equalsIgnoreCase("College")) {
						passFail = passFail +" "+ secName.replace(" Section", "");
					}
					
					if(sessionData.getSchoolName().toUpperCase().startsWith("NES") && stdInt == 9 
							&& (passFail.toUpperCase().contains("DETAINED") || passFail.contains("Eligible for Re-exam"))){
						passFail = "Detained but permitted to appear for Re-exam";
					}
					chunk102b = new Chunk("Result : "+passFail);
					Font font102b = FontFactory.getFont("TIMES_ROMAN");
					font102b.setStyle(Font.BOLD);
					font102b.setSize(12);
//					font102b.setColor(BaseColor.RED);
					chunk102b.setFont(font102b);
					paragraph102b.add(chunk102b);
					paragraph102b.setAlignment(Element.ALIGN_LEFT);
					if(!filterFieldsMap.containsKey("attendance")){
						paragraph102b.setSpacingBefore(4);
					}
					else if(filterFieldsMap.containsKey("percentage")){
						paragraph102b.setSpacingBefore(0f);
					}
					else {
						paragraph102b.setSpacingBefore(-15f);
					}
					
					Chunk chunk103 = new Chunk(" ");
					Font font103 = FontFactory.getFont("TIMES_ROMAN");
					font103.setStyle(Font.NORMAL);
					font103.setSize(10);
//					font103.setColor(BaseColor.RED);
					chunk103.setFont(font103);
					Paragraph paragraph103 = new Paragraph();
					paragraph103.add(chunk103);
					paragraph103.setAlignment(Element.ALIGN_LEFT);
					paragraph103.setSpacingBefore(0);
					
					String headMasterSpace = "                                      ";
					String classTeacher = "";
					if(headMaster.contains("/")){
						headMasterSpace = "";
					}
					classTeacher = "Class Teacher"+headMasterSpace+"                                                       "+headMaster;
					if(filterFieldsMap.containsKey("addCheckby")){
						classTeacher = "Class Teacher                           Checked By                           "+headMaster;
					}
					if(headMaster.contains("Head Master/Head Mistress")){
						classTeacher = "Class Teacher                               Checked By                                "+headMaster;
					}
					else if(headMaster.contains("Principal")){
						classTeacher = "Class Teacher                                               Checked By                                               "+headMaster;
					}
					else if(!headMaster.contains("/")){
						classTeacher = "Class Teacher                                           Checked By                                           "+headMaster;
					}
					Chunk chunk104 = new Chunk(classTeacher);
					Font font104 = FontFactory.getFont("TIMES_ROMAN");
					font104.setStyle(Font.BOLD);
					font104.setSize(12);
					font104.setColor(BaseColor.RED);
					chunk104.setFont(font104);
					Paragraph paragraph104 = new Paragraph();
					paragraph104.add(chunk104);
					paragraph104.setAlignment(Element.ALIGN_LEFT);
					paragraph104.setSpacingBefore(30);
					paragraph104.setSpacingAfter(Integer.parseInt(resultStampSpace));
			      
				      document.newPage();
				      table.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
				      table.setSpacingAfter(1.0f);  
				      
				     
				      
				      PdfPTable table2=new PdfPTable(12);
				      
				      table2.setWidthPercentage(100);
				      
//				      document.newPage();
				      table2.setSpacingBefore(100.0f);       // Space Before table starts, like margin-top in CSS
				      table2.setSpacingAfter(-5.0f); 
				      
				      PdfPCell cell301 = new PdfPCell (new Paragraph ("GRADES", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
				      cell301.setColspan (8);
//				      cell301.setRowspan (2);
				      cell301.setHorizontalAlignment (Element.ALIGN_CENTER);
				      cell301.setVerticalAlignment (Element.ALIGN_MIDDLE);
//				      cell301.setPadding (10.0f);
				      cell301.setBackgroundColor (new BaseColor (110, 110, 110));					               
				      table2.addCell(cell301);
				      
//				      PdfPCell cell302 = new PdfPCell (new Paragraph ("GRADES", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//				      cell302.setColspan (3);
////				      cell302.setRowspan (2);
//				      cell302.setHorizontalAlignment (Element.ALIGN_CENTER);
//				      cell302.setVerticalAlignment (Element.ALIGN_MIDDLE);
////				      cell302.setPadding (10.0f);
//				      cell302.setBackgroundColor (new BaseColor (110, 110, 110));					               
//				      table2.addCell(cell302);
				      
				      PdfPCell cell303 = new PdfPCell (new Paragraph ("REMARKS", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
				      cell303.setColspan (4);
//				      cell303.setRowspan (2);
				      cell303.setHorizontalAlignment (Element.ALIGN_CENTER);
				      cell303.setVerticalAlignment (Element.ALIGN_MIDDLE);
//				      cell303.setPadding (10.0f);
				      cell303.setBackgroundColor (new BaseColor (110, 110, 110));					               
				      table2.addCell(cell303);
			     
				      PdfPCell cell304 = new PdfPCell (new Paragraph ("A-1   -   91% to 100% \n"
														      		+ "A-2   -   81% to 90% \n"
														      		+ "B-1   -   71% to 80% \n"
														      		+ "B-2   -   61% to 70%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				      cell304.setColspan (4);
//				      cell304.setRowspan (2);
				      cell304.setHorizontalAlignment (Element.ALIGN_LEFT);
//				      cell304.setVerticalAlignment (Element.ALIGN_MIDDLE);
//				      cell304.setPadding (10.0f);
//				      cell304.setBackgroundColor (new BaseColor (99, 99, 99));					               
				      table2.addCell(cell304);
				      
				      PdfPCell cell305 = new PdfPCell (new Paragraph ("C-1   -   51% to 60% \n"
														      		+ "C-2   -   41% to 50% \n"
														      		+ "D     -   33% to 40% \n"
														      		+ "E     -   BELOW 33%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				      cell305.setColspan (4);
//				      cell305.setRowspan (2);
				      cell305.setHorizontalAlignment (Element.ALIGN_LEFT);
//				      cell305.setVerticalAlignment (Element.ALIGN_MIDDLE);
//				      cell305.setPadding (10.0f);
//				      cell305.setBackgroundColor (new BaseColor (99, 99, 99));					               
				      table2.addCell(cell305);
				      
				      PdfPCell cell306 = new PdfPCell (new Paragraph ("MG   -   MEDICAL GROUNDS \n"
				      		+ "CC   -   COPY CASE \n"
				      		+ "AB   -   ABSENT \n"
				      		+ "GP   -   GROUP PASSING", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				      cell306.setColspan (4);
//				      cell306.setRowspan (2);
				      cell306.setHorizontalAlignment (Element.ALIGN_LEFT);
//				      cell306.setVerticalAlignment (Element.ALIGN_MIDDLE);
//				      cell306.setPadding (10.0f);
//				      cell306.setBackgroundColor (new BaseColor (99, 99, 99));					               
				      table2.addCell(cell306);
				      
//				      document.newPage();
//				      table.setSpacingBefore(300.0f);       // Space Before table starts, like margin-top in CSS
//				      table.setSpacingAfter(150.0f);  
				      
				      PdfPTable table3=new PdfPTable(12);
				      
				      table3.setWidthPercentage(100);
				      
				      //////////////table for JR & SR KG/////////
				      //////////////////////////////////////////

				      Chunk chunkNote = new Chunk("Note : "+note);
						Font fontNote = FontFactory.getFont("TIMES_ROMAN");
						fontNote.setStyle(Font.BOLD);
						fontNote.setSize(12);
						fontNote.setColor(BaseColor.BLACK);
						chunkNote.setFont(fontNote);
						Paragraph paragraphNote = new Paragraph();
						paragraphNote.add(chunkNote);
						paragraphNote.setAlignment(Element.ALIGN_LEFT);
						paragraphNote.setSpacingBefore(5);
						paragraphNote.setSpacingAfter(-5);
						
				//Now Insert Every Thing Into PDF Document
				document.newPage();
				table.setSpacingBefore(15.0f); // Space Before table starts, like
												// margin-top in CSS
//				table.setSpacingAfter(1.0f);
	
//				document.newPage();
				// Now Insert Every Thing Into PDF Document
				document.open();// PDF document opened........
	
//				document.add(Chunk.NEWLINE); // Something like in HTML :-)
              
                table.setWidths(columnWidths);
                table2.setWidths(columnWidths);
                
                if(pdf_header_txt_flag.equalsIgnoreCase("true") && semester.equalsIgnoreCase("Final")){
                	document.add(paragraph01);
                    document.add(paragraph02);
//                    document.add(paragraph03);
//                    document.add(paragraph04);
//                    document.add(paragraph05);
                }
                if(pdf_header_img_flag.equalsIgnoreCase("true")){
					////just to add image in pdf as header
					Image img = Image.getInstance(img_path+pdf_header_img_path);
					img.scalePercent(image_pdf_scalepercent);
	//				img.scaleToFit(550f, 150f);
					img.setAbsolutePosition(image_pdf_pos_x, image_pdf_pos_y);
	//				img.scaleAbsolute(550, 150);
				    document.add(img);
				    paraSep.setSpacingBefore(05);
				}
				else if(!resultName.equalsIgnoreCase("Final") && !resultName.equalsIgnoreCase("Annual")){
					document.add(paragraphHeader);
				}
                document.add(paraSep);
                document.add(paragraph06);
                document.add(table);
                document.add(paragraph101);
                document.add(paragraph101a);
                document.add(paragraph102);
                document.add(paragraph102a);
                document.add(paragraph102b);
                document.add(paragraph103);
                document.add(paragraph104);
//                document.add(addTableSpace);
                document.add(table2);
                if(!note.equalsIgnoreCase("")){
					document.add(paragraphNote);
				}
	
				document.add(Chunk.NEWLINE); // Something like in HTML :-)
				document.newPage(); // Opened new page
			}

			document.close();

			fileout.close();

			logger.info("Pdf created successfully..");
			fileOpenFlag = true;
			
			// open pdf 
			if(fileOpenFlag){
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
					commonObj.logException(e);
				}
			}

		} catch (Exception e) {
			commonObj.logException(e);
		}
	}

}
