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

public class ResultUnitTestPDF {
	DBValidate findStudentDB = new DBValidate();
	Common cm = new Common();
	ResourceBundle bundle    		= ResourceBundle.getBundle("org.com.accesser.school"); 
    static Logger logger 			= Logger.getLogger(ResultUnitTestPDF.class.getName());
    static String secName 			= "";
    static String path				= "";
    static String fileName			= "";
    static String fileAddress		= "";
    
	public ResultUnitTestPDF(SessionData sessionData, String sec, String academic, LinkedHashMap subjectTitleMap, 
			LinkedHashMap subjectMap, String exam,	String std, String div, LinkedHashMap studentMap, LinkedHashMap optionalAllotmentMap, LinkedHashMap optionalMap) {
		logger.info("Inside Result Unit Test pdf constructor..............");
		System.gc();
		
		String semester = "", displaySection = "";
		boolean fileOpenFlag = false;
    	secName = bundle.getString(sec.toUpperCase()+"_SEC");
    	logger.info("secName :: "+secName);
    	String headMaster = bundle.getString(sessionData.getDBName().toUpperCase()+"_PDF_FOOTER");
    	String result_header_1 = bundle.getString("RESULT_HEADER_1");
    	String result_header_2 = bundle.getString("RESULT_HEADER_2");
    	String result_header_3 = bundle.getString("RESULT_HEADER_3");
    	String result_header_4 = bundle.getString("RESULT_HEADER_4");
    	String result_header_5 = bundle.getString("RESULT_HEADER_5");
    	String result_header_6 = bundle.getString("CLASS_ALLOT_HEADER_SCHOOL");
    	path = cm.createTodayFolder(cm.getDriveName() + bundle.getString("RESULT_PDF_PATH_"+sessionData.getDBName()),true)+"/";
    	String img_path = bundle.getString("IMAGE_PATH");
    	String pdf_header_img_path = bundle.getString("IMAGE_PDF_RESULT_HEADER_"+sessionData.getAppType());
		int image_pdf_pos_x = Integer.parseInt(bundle.getString("IMAGE_PDF_RESULT_POS_X"));
		int image_pdf_pos_y = Integer.parseInt(bundle.getString("IMAGE_PDF_RESULT_POS_Y"));
		float image_pdf_scalepercent = Float.parseFloat(bundle.getString("IMAGE_PDF_RESULT_SCALEPERCENT"));
		String pdf_header_img_flag = bundle.getString("PDF_HEADER_RESULT_IMAGE_FLAG");
		
		if(sessionData.getAppType().equalsIgnoreCase("College")){
			displaySection = sec;
		}
		
    	/*if (exam.equalsIgnoreCase("Semester 1")) {
			semester = "SEM1";
		} else if (exam.equalsIgnoreCase("Semester 2")) {
			semester = "SEM2";
		} else {
			semester = "FINAL";
		}*/
    	int noOfSubjects = subjectTitleMap.size();
    	int studentSubjects = noOfSubjects;
    	
    	for(int i=0; i < (10-noOfSubjects); i++){
    		subjectTitleMap.put(" ","");
    	}
    	noOfSubjects = subjectTitleMap.size();
    	
		try {
			///////start of pdf creation/////////////////////
			fileName  = "ResultPrint"+cm.timeInMillis()+".pdf";
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

			// Get a set of the entries
			Set set = studentMap.entrySet();
			// Get an iterator
			Iterator i = set.iterator();
		      
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
		        String grNo = me.getKey().toString();
//		        if(grNo.equalsIgnoreCase("0008049")){
		        	logger.info("grNo----"+grNo+"-----");
//		        }
		        LinkedHashMap studentData = new LinkedHashMap();
		        studentData = (LinkedHashMap) me.getValue();
		         
				Chunk chunk01 = new Chunk(result_header_1);
				Font font01 = FontFactory.getFont("TIMES_ROMAN");
				font01.setStyle(Font.BOLD);
				font01.setSize(12);
				// font01.setColor(BaseColor.RED);
				chunk01.setFont(font01);
				Paragraph paragraph01 = new Paragraph();
				paragraph01.add(chunk01);
				paragraph01.setAlignment(Element.ALIGN_CENTER);
				paragraph01.setSpacingBefore(20);
	
				Chunk chunk02 = new Chunk(result_header_2);
				Font font02 = FontFactory.getFont("TIMES_ROMAN");
				font02.setStyle(Font.BOLD);
				font02.setSize(20);
				font02.setColor(BaseColor.RED);
				chunk02.setFont(font02);
				Paragraph paragraph02 = new Paragraph();
				paragraph02.add(chunk02);
				paragraph02.setAlignment(Element.ALIGN_CENTER);
				paragraph02.setSpacingBefore(4);
	
				Chunk chunk03 = new Chunk(result_header_3);
				Font font03 = FontFactory.getFont("TIMES_ROMAN");
				font03.setStyle(Font.NORMAL);
				font03.setSize(10);
				// font03.setColor(BaseColor.RED);
				chunk03.setFont(font03);
				Paragraph paragraph03 = new Paragraph();
				paragraph03.add(chunk03);
				paragraph03.setAlignment(Element.ALIGN_CENTER);
				paragraph03.setSpacingBefore(0);
	
				Chunk chunk04 = new Chunk(result_header_4);
				Font font04 = FontFactory.getFont("TIMES_ROMAN");
				font04.setStyle(Font.NORMAL);
				font04.setSize(10);
				// font04.setColor(BaseColor.RED);
				chunk04.setFont(font04);
				Paragraph paragraph04 = new Paragraph();
				paragraph04.add(chunk04);
				paragraph04.setAlignment(Element.ALIGN_CENTER);
				// paragraph04.setSpacingBefore(0);
				// paragraph04.setSpacingAfter(-85);
	
				Chunk chunk05 = new Chunk(result_header_5);
				Font font05 = FontFactory.getFont("TIMES_ROMAN");
				font05.setStyle(Font.NORMAL);
				font05.setSize(10);
				// font05.setColor(BaseColor.RED);
				chunk05.setFont(font05);
				Paragraph paragraph05 = new Paragraph();
				paragraph05.add(chunk05);
				paragraph05.setAlignment(Element.ALIGN_CENTER);
				// paragraph05.setSpacingBefore(0);
				// paragraph05.setSpacingAfter(-85);
				
				Chunk chunkHeader = new Chunk(result_header_6);
				Font fontHeader = FontFactory.getFont("TIMES_ROMAN");
				fontHeader.setStyle(Font.BOLD);
				fontHeader.setSize(16);
				chunkHeader.setFont(fontHeader);
				Paragraph paragraphHeader = new Paragraph();
				paragraphHeader.add(chunkHeader);
				paragraphHeader.setAlignment(Element.ALIGN_CENTER);
				paragraphHeader.setSpacingAfter(10);
				
				Chunk sep = new Chunk(" ");
				Paragraph paraSep = new Paragraph();
//				paraSep.getPaddingTop(105);
//				paraSep.setSpacingBefore(250);
//				paraSep.setSpacingAfter(90);
				paraSep.add(sep);
				paraSep.setAlignment(Element.ALIGN_LEFT);
	
				String resultName = exam;
				if(resultName.equalsIgnoreCase("FUT")){
					resultName = "FIRST UNIT TEST";
				}
				else if(resultName.equalsIgnoreCase("SUT")){
					resultName = "SECOND UNIT TEST";
				}
				/*if(!std.equalsIgnoreCase("IX") && !std.equalsIgnoreCase("X") && 
						!std.equalsIgnoreCase("XI") && !std.equalsIgnoreCase("XII") && 
						exam.equalsIgnoreCase("Semester 2")){
					resultName = "Final";
				}*/
				Chunk chunk06 = new Chunk(resultName.toUpperCase()+" RESULT "+academic);
				Font font06 = FontFactory.getFont("TIMES_ROMAN");
				font06.setStyle(Font.BOLD);
				font06.setSize(16);
				// font06.setColor(BaseColor.RED);
				chunk06.setFont(font06);
				Paragraph paragraph06 = new Paragraph();
				paragraph06.add(chunk06);
				paragraph06.setAlignment(Element.ALIGN_CENTER);
				// paragraph06.setSpacingBefore(0);
				// paragraph06.setSpacingAfter(-85);
	
				Chunk chunk07 = new Chunk("Name of the Student :");
				Font font07 = FontFactory.getFont("TIMES_ROMAN");
				font07.setStyle(Font.BOLD);
				font07.setSize(10);
				// font07.setColor(BaseColor.RED);
				chunk07.setFont(font07);
				Paragraph paragraph07 = new Paragraph();
				paragraph07.add(chunk07);
				paragraph07.setAlignment(Element.ALIGN_LEFT);
				paragraph07.setSpacingBefore(20);
				// paragraph07.setSpacingAfter(-85);
	
				Chunk chunk07b = new Chunk(
						"                                                                                                                                                      ");
				Font font07b = FontFactory.getFont("TIMES_ROMAN");
				font07b.setStyle(Font.BOLD);
				font07b.setStyle(Font.UNDERLINE);
				font07b.setSize(10);
				// font07a.setColor(BaseColor.RED);
				chunk07b.setFont(font07b);
				Paragraph paragraph07b = new Paragraph();
				paragraph07b.add(chunk07b);
				paragraph07b.setAlignment(Element.ALIGN_LEFT);
				paragraph07b.setIndentationLeft(105);
				paragraph07b.setSpacingBefore(-16);
				// paragraph07b.setSpacingAfter(-85);
				//
				Chunk chunk07a = new Chunk(" "+
						studentData.get("last_name")+" "+studentData.get("first_name")+" "+studentData.get("father_name")+"                          ");
				Font font07a = FontFactory.getFont("TIMES_ROMAN");
				font07a.setStyle(Font.BOLD);
				// font07a.setStyle(Font.UNDERLINE);
				font07a.setSize(12);
				// font07a.setColor(BaseColor.RED);
				chunk07a.setFont(font07a);
				Paragraph paragraph07a = new Paragraph();
				paragraph07a.add(chunk07a);
				paragraph07a.setAlignment(Element.ALIGN_LEFT);
				paragraph07a.setIndentationLeft(105);
				paragraph07a.setSpacingBefore(-15);
				paragraph07a.setSpacingAfter(-85);
	
				PdfPTable table = new PdfPTable(12);
				table.setWidthPercentage(100);
	
				PdfPCell cell = new PdfPCell(new Paragraph("STD.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
				cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell.setColspan (5);
				 cell.setPaddingBottom(5.0f);
				// cell.setBackgroundColor (new BaseColor (99, 99, 99));
				table.addCell(cell);
	
				PdfPCell cell1 = new PdfPCell(new Paragraph("DIV.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
				cell1.setColspan(1);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.setPaddingBottom(5.0f);
				// cell1.setBackgroundColor (new BaseColor (99, 99, 99));
				table.addCell(cell1);
	
				PdfPCell cell1a = new PdfPCell(new Paragraph("ROLL NO.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
				cell1a.setColspan(1);
				cell1a.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1a.setVerticalAlignment(Element.ALIGN_MIDDLE);
				 cell1a.setPaddingBottom(5.0f);
				// cell1a.setBackgroundColor (new BaseColor (99, 99, 99));
				table.addCell(cell1a);
	
				PdfPCell cell2 = new PdfPCell(new Paragraph("GR. NO.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
				cell2.setColspan(2);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				 cell2.setPaddingBottom(5.0f);
				// cell2.setBackgroundColor (new BaseColor (140, 221, 8));
				table.addCell(cell2);
	
				PdfPCell cell3 = new PdfPCell(new Paragraph("BIRTH DATE", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
				cell3.setColspan(6);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				 cell3.setPaddingBottom(5.0f);
				// cell3.setBackgroundColor (new BaseColor (140, 221, 8));
				table.addCell(cell3);
	
//				PdfPCell cell4 = new PdfPCell(new Paragraph("ATTENDANCE", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
//				cell4.setColspan(3);
//				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
//				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
//				 cell4.setPaddingBottom(5.0f);
//				// cell4.setBackgroundColor (new BaseColor (140, 221, 8));
//				table.addCell(cell4);
				
				PdfPCell cell5 = new PdfPCell(new Paragraph(std +" "+ displaySection, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell5.setColspan(2);
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell5.setPaddingBottom(5.0f);
				// cell5.setBackgroundColor (new BaseColor (140, 221, 8));
				table.addCell(cell5);
	
				PdfPCell cell6 = new PdfPCell(new Paragraph(div, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell6.setColspan(1);
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell6.setPaddingBottom(5.0f);
				// cell6.setBackgroundColor (new BaseColor (140, 221, 8));
				table.addCell(cell6);
	
				PdfPCell cell7 = new PdfPCell(new Paragraph(studentData.get("roll_no").toString(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//				PdfPCell cell7 = new PdfPCell(new Paragraph("roll", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell7.setColspan(1);
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
				 cell7.setPaddingBottom(5.0f);
				// cell7.setBackgroundColor (new BaseColor (140, 221, 8));
				table.addCell(cell7);
	
				PdfPCell cell8 = new PdfPCell(new Paragraph(grNo, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell8.setColspan(2);
				// cell8.setRowspan (7);
				cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
				 cell8.setPaddingBottom(5.0f);
				// cell8.setBackgroundColor (new BaseColor (140, 221, 8));
				table.addCell(cell8);
	
//				PdfPCell cell9 = new PdfPCell(new Paragraph(studentData.get("birthDate").toString(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				PdfPCell cell9 = new PdfPCell(new Paragraph(studentData.get("birth_date").toString(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell9.setColspan(6);
				// cell9.setRowspan (7);
				cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
				 cell9.setPaddingBottom(5.0f);
				// cell9.setBackgroundColor (new BaseColor (140, 221, 8));
				table.addCell(cell9);
	
				/*String attendance = "";
				if(studentData.get("attendance") != null){
					attendance = studentData.get("attendance").toString();
				}
				if(attendance.equalsIgnoreCase("NA") || attendance.trim().equalsIgnoreCase("")){
					attendance = "-";
				}
				PdfPCell cell10 = new PdfPCell(new Paragraph(attendance, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell10.setColspan(3);
				// cell10.setRowspan (3);
				cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
				 cell10.setPaddingBottom(5.0f);
				// cell10.setBackgroundColor (new BaseColor (140, 221, 8));
				table.addCell(cell10);*/
	
				PdfPCell cell11 = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
				cell11.setColspan(12);
				// cell11.setRowspan (3);
				cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell11.setPadding (10.0f);
				// cell11.setBackgroundColor (new BaseColor (140, 221, 8));
				table.addCell(cell11);
	
				PdfPCell cell12 = new PdfPCell(
						new Paragraph("SR. \n" + "NO.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
	
				cell12.setColspan(1);
				// cell12.setRowspan (3);
				cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell10.setPadding (10.0f);
				// cell12.setBackgroundColor (new BaseColor (140, 221, 8));
				table.addCell(cell12);
	
				PdfPCell cell13 = new PdfPCell(new Paragraph("SUBJECTS", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
				cell13.setColspan(5);
				// cell13.setRowspan (3);
				cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell11.setPadding (10.0f);
				// cell13.setBackgroundColor (new BaseColor (140, 221, 8));
				table.addCell(cell13);
	
				PdfPCell cell14 = new PdfPCell(
						new Paragraph("MAXIMUM MARKS", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
				cell14.setColspan(3);
				cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell14.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell11.setPadding (10.0f);
				// cell14.setBackgroundColor (new BaseColor (140, 221, 8));
				table.addCell(cell14);
	
				PdfPCell cell15 = new PdfPCell(
						new Paragraph("MARKS OBTAINED", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
				cell15.setColspan(3);
				cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell15.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell11.setPadding (10.0f);
				// cell15.setBackgroundColor (new BaseColor (140, 221, 8));
				table.addCell(cell15);
	
				/*PdfPCell cell16 = new PdfPCell(new Paragraph("REMARKS", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
				cell16.setColspan(4);
				cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell16.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell11.setPadding (10.0f);
				// cell16.setBackgroundColor (new BaseColor (140, 221, 8));
				table.addCell(cell16);*/
	
				/*Chunk chunk08 = new Chunk("Progress :- ");
				Font font08 = FontFactory.getFont("TIMES_ROMAN");
				font08.setStyle(Font.BOLD);
				// font08.setStyle(Font.UNDERLINE);
				font08.setSize(10);
				// font08(BaseColor.RED);
				chunk08.setFont(font08);
	
//				String semProgress = studentData.get("semProgress").toString();
				String semProgress = "progress";
				if(semProgress.equalsIgnoreCase("NA")){
					semProgress = "-";
				}
				Chunk chunk09 = new Chunk(" "+semProgress);
				Font font09 = FontFactory.getFont("TIMES_ROMAN");
				font09.setStyle(Font.ITALIC);
				// font09.setStyle(Font.UNDERLINE);
				font09.setSize(10);
				// font09(BaseColor.RED);
				chunk09.setFont(font09);
	
				Paragraph paragraph10 = new Paragraph();
				paragraph10.add(chunk08);
				paragraph10.add(chunk09);
	
				Chunk chunk11 = new Chunk("Hobbies / Participation In Activities:- ");
				Font font11 = FontFactory.getFont("TIMES_ROMAN");
				font11.setStyle(Font.BOLD);
				// font11.setStyle(Font.UNDERLINE);
				font11.setSize(10);
				// font11(BaseColor.RED);
				chunk11.setFont(font11);
	
//				String hobbies = studentData.get("hobbies").toString();
				String hobbies = "hobbies";
				if(hobbies.equalsIgnoreCase("NA")){
					hobbies = "-";
				}
				Chunk chunk12 = new Chunk("\n"+cm.FirstWordCap(hobbies));
				Font font12 = FontFactory.getFont("TIMES_ROMAN");
				font12.setStyle(Font.ITALIC);
				// font12.setStyle(Font.UNDERLINE);
				font12.setSize(10);
				// font12(BaseColor.RED);
				chunk12.setFont(font12);
	
				Paragraph paragraph12 = new Paragraph();
				paragraph12.add(chunk11);
				paragraph12.add(chunk12);
	
				Chunk chunk13 = new Chunk("Improvement required in:- ");
				Font font13 = FontFactory.getFont("TIMES_ROMAN");
				font13.setStyle(Font.BOLD);
				// font13.setStyle(Font.UNDERLINE);
				font13.setSize(10);
				// font13(BaseColor.RED);
				chunk13.setFont(font13);
	
//				String semImprove = studentData.get("semImprove").toString();
				String semImprove = "improve";
				if(semImprove.equalsIgnoreCase("NA")){
					semImprove = "-";
				}
				Chunk chunk14 = new Chunk("\n"+cm.FirstWordCap(semImprove.replace("*", ", ")));
				Font font14 = FontFactory.getFont("TIMES_ROMAN");
				font14.setStyle(Font.ITALIC);
				// font14.setStyle(Font.UNDERLINE);
				font14.setSize(10);
				// font14(BaseColor.RED);
				chunk14.setFont(font14);*/
	
				Paragraph paragraph14 = new Paragraph();
//				paragraph14.add(chunk13);
//				paragraph14.add(chunk14);
				paragraph14.setLeading(3);
				
				PdfPCell cellsubject;
				Font fontTimes10  = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
				int j = 0;
				int maxTotal = 0;
				String srNo = "";
				String maxMarks = "";
				String marksObtained = "";
				double totalObtained = 0.0;
				LinkedHashMap subjectTitleMatched = new LinkedHashMap();
				Set set1 = subjectMap.entrySet();
				Iterator iterator = set1.iterator();
				while(iterator.hasNext()) {
					Map.Entry me1 = (Map.Entry)iterator.next();
					String subName = me1.getKey().toString();
					String subTitleName = me1.getValue().toString();
					if(optionalMap.get(subName).toString().equalsIgnoreCase("YES")){
						if(!optionalAllotmentMap.get(grNo).toString().contains(subTitleName+"_YES")){
							continue;
						}
					}
					String testMarks = studentData.get(subName).toString().equalsIgnoreCase("NA") ? "" : studentData.get(subName).toString();
					if(cm.validateNumber(testMarks)){
						if(Integer.parseInt(testMarks.substring(testMarks.indexOf(".")+1))<=0){
							testMarks = (int)Double.parseDouble(testMarks)+"";//converting to integer
						}
						
						if(subjectTitleMatched.get(subTitleName) != null){
							int addSubMarks = (Integer.parseInt(subjectTitleMatched.get(subTitleName).toString()) + Integer.parseInt(testMarks));
							subjectTitleMatched.put(subTitleName, Integer.toString(addSubMarks));
						}
						else{
							subjectTitleMatched.put(subTitleName, testMarks);
						}
						totalObtained = totalObtained + Double.parseDouble(testMarks);
					}
					else{
						//for MG & AB logic
					}
				}
				int subjectMatchSize = subjectTitleMatched.size();
				if((totalObtained-(int)totalObtained) == 0) {
					subjectTitleMatched.put("GRAND TOTAL",String.format("%.0f", totalObtained));
				}
				else {
					subjectTitleMatched.put("GRAND TOTAL",totalObtained);
				}
				
				
				Set set2 = subjectTitleMatched.entrySet();
				Iterator iterator2 = set2.iterator();
				while(iterator2.hasNext()) {
					Map.Entry me2 = (Map.Entry)iterator2.next();
					String subName = me2.getKey().toString();
					String subMarks = me2.getValue().toString();
					srNo = "";
					maxMarks = "";
					marksObtained = "";
					
					if(!subMarks.trim().equalsIgnoreCase("") && subMarks != null && subjectTitleMap.get(subName)  != null){
						maxMarks = subjectTitleMap.get(subName).toString();
						marksObtained = subMarks;
						if(maxMarks.equalsIgnoreCase("-") || marksObtained.equalsIgnoreCase("-")){
							studentSubjects = noOfSubjects - 1;
							continue;
						}
						maxTotal = maxTotal + Integer.parseInt(maxMarks);
						srNo = (j+1)+"";
						
						if(subName.equalsIgnoreCase("GRAND TOTAL")){
							maxMarks = Integer.toString(maxTotal);
							srNo = "";
						}
					}
					else{
						continue;
					}
					
					cellsubject = new PdfPCell(new Phrase(srNo, fontTimes10));
					cellsubject.setColspan(1);
					cellsubject.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellsubject.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cellsubject.setPaddingBottom(5.0f);
					table.addCell(cellsubject);
		
					subName = subName.replace("_", " ");
					if(!subName.equalsIgnoreCase("GRAND TOTAL")){
						cellsubject = new PdfPCell(new Phrase(subName, fontTimes10));
					}
					else{
						cellsubject = new PdfPCell(new Phrase(subName, FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					}
					cellsubject.setColspan(5);
					cellsubject.setHorizontalAlignment(Element.ALIGN_LEFT);
					cellsubject.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cellsubject.setPaddingBottom(5.0f);
					table.addCell(cellsubject);
					
					if(!subName.equalsIgnoreCase("GRAND TOTAL")){
						cellsubject = new PdfPCell(new Phrase(maxMarks, fontTimes10));
					}
					else{
						cellsubject = new PdfPCell(new Phrase(maxMarks, FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					}
					cellsubject.setColspan(3);
					cellsubject.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellsubject.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cellsubject.setPaddingBottom(5.0f);
					table.addCell(cellsubject);
					
					if(!subName.equalsIgnoreCase("GRAND TOTAL")){
						cellsubject = new PdfPCell(new Phrase(marksObtained, fontTimes10));
					}
					else{
						cellsubject = new PdfPCell(new Phrase(marksObtained, FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					}
					cellsubject.setColspan(3);
					cellsubject.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellsubject.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cellsubject.setPaddingBottom(5.0f);
					table.addCell(cellsubject);
					j++;
				}
	
//				String passFail = studentData.get("semResult").toString();
				String passFail = "pass/fail";
				String romanStd = std;
				int stdPromoted = 0;
				if(passFail.contains("pass") || passFail.contains("Pass") || passFail.contains("PASS")){
					stdPromoted = cm.RomanToInteger(std)+1;
					romanStd = cm.IntegerToRoman("a"+stdPromoted);
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
				
				Chunk chunk101 = new Chunk(promotedStr);
				// chunk101.setUnderline(0.1f, -2f);
				Font font101 = FontFactory.getFont("TIMES_ROMAN");
				// font101.setStyle(Font.UNDERLINE);
				font101.setStyle(Font.BOLD);
				font101.setSize(10);
				// font101.setColor(BaseColor.RED);
				chunk101.setFont(font01);
				Paragraph paragraph101 = new Paragraph();
				paragraph101.add(chunk101);
				paragraph101.setAlignment(Element.ALIGN_LEFT);
				paragraph101.setSpacingBefore(20);
	
				/*Chunk chunk101a = new Chunk(romanStd);
				Font font101a = FontFactory.getFont("TIMES_ROMAN");
				font101a.setStyle(Font.BOLD);
				font101a.setSize(10);
				// font101a.setColor(BaseColor.RED);
				chunk101a.setFont(font01);
				Paragraph paragraph101a = new Paragraph();
				paragraph101a.add(chunk101a);
				paragraph101a.setIndentationLeft(165);
				paragraph101a.setAlignment(Element.ALIGN_LEFT);
				paragraph101a.setSpacingBefore(-16);*/
				
				String headMasterSpace = "                                      ";
				if(headMaster.contains("/")){
					headMasterSpace = "";
				}
				Chunk chunk104 = new Chunk(
						"CLASS TEACHER"+headMasterSpace+"                                                       "+headMaster.toUpperCase());
				Font font104 = FontFactory.getFont("TIMES_ROMAN");
				font104.setStyle(Font.BOLD);
				font104.setSize(12);
				font104.setColor(BaseColor.RED);
				chunk104.setFont(font104);
				Paragraph paragraph104 = new Paragraph();
				paragraph104.add(chunk104);
				paragraph104.setAlignment(Element.ALIGN_LEFT);
				paragraph104.setSpacingBefore(20);
//				paragraph104.setSpacingAfter(-85);
				
				/*Chunk chunkNote = new Chunk("Note : "+note);
				Font fontNote = FontFactory.getFont("TIMES_ROMAN");
				fontNote.setStyle(Font.BOLD);
				fontNote.setSize(12);
				fontNote.setColor(BaseColor.BLACK);
				chunkNote.setFont(fontNote);
				Paragraph paragraphNote = new Paragraph();
				paragraphNote.add(chunkNote);
				paragraphNote.setAlignment(Element.ALIGN_LEFT);
				paragraphNote.setSpacingBefore(60);
//				paragraphNote.setSpacingAfter(-85);
*/	
				document.newPage();
				table.setSpacingBefore(100.0f); // Space Before table starts, like margin-top in CSS
				table.setSpacingAfter(1.0f);
				
				/*tablePercentageGrade.setSpacingBefore(5.0f); // Space Before table starts, like margin-top in CSS
				tablePercentageGrade.setSpacingAfter(1.0f);*/
	
				document.newPage();
				// Now Insert Every Thing Into PDF Document
				document.open();// PDF document opened........
	
				document.add(Chunk.NEWLINE); // Something like in HTML :-)
	
				// for column width size
				float[] columnWidths = new float[] { 5f, 10f, 10f, 10f, 6f, 6f, 7f, 7f, 7f, 7f, 9f, 9f };
				table.setWidths(columnWidths);
	
				/*float[] columnWidthsPercentageGrade = new float[] { 5f, 10f, 10f, 15f, 15f, 15f, 15f, 15f, 15f, 15f, 15f, 15f };
				tablePercentageGrade.setWidths(columnWidthsPercentageGrade);*/
				
				/*document.add(paragraph01);
				document.add(paragraph02);
				document.add(paragraph03);
				document.add(paragraph04);
				document.add(paragraph05);*/
				if(pdf_header_img_flag.equalsIgnoreCase("true")){
					////just to add image in pdf as header
					Image img = Image.getInstance(img_path+pdf_header_img_path);
					img.scalePercent(image_pdf_scalepercent);
	//				img.scaleToFit(550f, 150f);
					img.setAbsolutePosition(image_pdf_pos_x, image_pdf_pos_y);
	//				img.scaleAbsolute(550, 150);
				    document.add(img);
				    paraSep.setSpacingBefore(05);
				    paraSep.setSpacingAfter(90);
				}
				else {
					document.add(paragraphHeader);
				}
				document.add(paraSep);
				document.add(paragraph06);
				document.add(paragraph07);
				document.add(paragraph07b);
				document.add(paragraph07a);
	
				document.add(table);
//				document.add(tablePercentageGrade);
				document.add(paragraph101);
//				document.add(paragraph101a);
				document.add(paragraph104);
				/*if(!note.equalsIgnoreCase("")){
					document.add(paragraphNote);
				}*/
	
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
					logger.error(":: -----Exception1---- ::\n"+e);
				}
			}

		} catch (Exception e) {
			logger.error(": --Exception2-- ::"+e);
		}
	}

}
