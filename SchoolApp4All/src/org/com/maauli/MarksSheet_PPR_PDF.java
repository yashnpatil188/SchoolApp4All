package org.com.maauli;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.com.accesser.SessionData;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.DocumentException;
import com.itextpdf.text.PageSize;

public class MarksSheet_PPR_PDF {

	static String fileName = "";
	static String fileAddress = "";
	static String path = "";
	static boolean fileOpenFlag = false;
	Common commonObj = new Common();
	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	static Logger logger = Logger.getLogger(MarksSheet_PPR_PDF.class.getName());

	public MarksSheet_PPR_PDF(SessionData sessionData, String exam, String subject, String std, String div,
			String academic, LinkedHashMap<String, LinkedHashMap<String, String>> marksSemDataMap,
			LinkedHashMap<String, LinkedHashMap<String, String>> studentOptSubAllotMap,
			LinkedHashMap<String, LinkedHashMap<String, String>> maxSubMarks, 
			LinkedHashMap<String, LinkedHashMap<String, String>> resultMap, 
			LinkedHashMap<String,String> leftDataMap) throws DocumentException {

		System.gc();
		fileOpenFlag = true;
		boolean isMg = false, isDoubleLine = false, lastPage = true;
		int displayRows= 26;
		String examHeader = "", sem = "", subMarks = "", subjectMarksDisp = "", obtainedStr = "";
		double total = 0, totalObtained = 0, subjectMarks = 0, subjectTotal = 0, percent = 0;
		int subjectHeadCount = maxSubMarks.size(), addRow = 0;
		String subTitle = "", grNo = "", subGrade = "", totalGrade = "", outOfMarks = "", gradeMarks = "", finalSubMarks = "0";
		String bonafide_header = bundle.getString("BONAFIDE_HEADER_" + sessionData.getAppType());
		String bonafide_header_0 = bundle.getString("BONAFIDE_HEADER_0_" + sessionData.getAppType());
		if(!bonafide_header_0.trim().equalsIgnoreCase("")){
			bonafide_header_0 = bonafide_header_0 + " \n";
			displayRows = 25;
		}

		try {
			if (exam.equalsIgnoreCase("Semester 1")) {
				sem = "sem1";
				examHeader = "FIRST SEMESTER";
			} else if (exam.equalsIgnoreCase("Semester 2")) {
				sem = "sem2";
				examHeader = "SECOND SEMESTER";
			} else if (exam.equalsIgnoreCase("Final")) {
				sem = "final";
				examHeader = "FINAL EXAM";
			}
			path = commonObj.createTodayFolder(
					commonObj.getDriveName() + bundle.getString("REPORT_PDF_PATH_" + sessionData.getDBName()), true)
					+ "/";
			fileName = "Mark_Grade_Sheet_" + std + "_" + div + "_" + commonObj.timeInMillis() + ".pdf";
			fileAddress = path + fileName;
			File file = new File(fileAddress);
			FileOutputStream fileout = new FileOutputStream(file);
			Document document = new Document(PageSize.LEGAL.rotate());
			PdfWriter.getInstance(document, fileout);
			document.addHeader("ABC", "XYZ");
			document.addTitle("Marksheet Subjectwise");
			document.open();// PDF document opened........

			int pageStart = -60;
			Chunk chunkHeader1 = new Chunk(bonafide_header_0 + bonafide_header + " \n");
			Font font1 = FontFactory.getFont("TIMES_ROMAN");
			font1.setStyle(Font.NORMAL);
			font1.setSize(12);
			chunkHeader1.setFont(font1);
			Paragraph paragraph1 = new Paragraph();
			paragraph1.add(chunkHeader1);
			paragraph1.setAlignment(Element.ALIGN_CENTER);
			paragraph1.setSpacingBefore(pageStart+20);

			Chunk chunkHeader2 = new Chunk(examHeader + " " + academic + " \n");
			Font font2 = FontFactory.getFont("TIMES_ROMAN");
			font2.setStyle(Font.NORMAL);
			font2.setSize(12);
			chunkHeader2.setFont(font2);
			Paragraph paragraph2 = new Paragraph();
			paragraph2.add(chunkHeader2);
			paragraph2.setAlignment(Element.ALIGN_CENTER);
			paragraph2.setSpacingBefore(2);

			Chunk chunk3 = new Chunk("MARKS SHEET");
			Font font3 = FontFactory.getFont("TIMES_ROMAN");
			font3.setStyle(Font.NORMAL);
			font3.setSize(12);
			chunk3.setFont(font3);
			Paragraph paragraph3 = new Paragraph();
			paragraph3.add(chunk3);
			paragraph3.setAlignment(Element.ALIGN_CENTER);
			paragraph3.setSpacingBefore(0);

			Chunk chunk4 = new Chunk("Std :");
			Font font4 = FontFactory.getFont("TIMES_ROMAN");
			font4.setStyle(Font.BOLD);
			font4.setSize(12);
			chunk4.setFont(font4);
			Paragraph paragraph4 = new Paragraph();
			paragraph4.add(chunk4);
			paragraph4.setAlignment(Element.ALIGN_LEFT);
			paragraph4.setSpacingBefore(0);

			Chunk chunk5 = new Chunk("									" + std);
			Font font5 = FontFactory.getFont("TIMES_ROMAN");
			font5.setStyle(Font.NORMAL);
			font5.setSize(12);
			chunk5.setFont(font5);
			Paragraph paragraph5 = new Paragraph();
			paragraph5.add(chunk5);
			paragraph5.setAlignment(Element.ALIGN_LEFT);
			paragraph5.setSpacingBefore(-16);

			Chunk chunk6 = new Chunk("																										Div :");
			Font font6 = FontFactory.getFont("TIMES_ROMAN");
			font6.setStyle(Font.BOLD);
			font6.setSize(12);
			chunk6.setFont(font6);
			Paragraph paragraph6 = new Paragraph();
			paragraph6.add(chunk6);
			paragraph6.setAlignment(Element.ALIGN_LEFT);
			paragraph6.setSpacingBefore(-16);

			Chunk chunk7 = new Chunk(
					"																																			"
							+ div);
			Font font7 = FontFactory.getFont("TIMES_ROMAN");
			font7.setStyle(Font.NORMAL);
			font7.setSize(12);
			chunk7.setFont(font7);
			Paragraph paragraph7 = new Paragraph();
			paragraph7.add(chunk7);
			paragraph7.setAlignment(Element.ALIGN_LEFT);
			paragraph7.setSpacingBefore(-16);

			PdfPTable tableHeader;
			PdfPTable table1;
			
			tableHeader = new PdfPTable(9+(subjectHeadCount));
			tableHeader.setWidthPercentage(100);
			tableHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tableHeader.setSpacingBefore(5.0f);
			
			table1 = new PdfPTable(9+(subjectHeadCount));
			table1.setWidthPercentage(100);
			table1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.setSpacingBefore(5.0f);

			PdfPCell cell301 = new PdfPCell(
					new Paragraph("ROLL NO.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell301.setColspan(1);
			cell301.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell301.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell301.setRotation(90);
			tableHeader.addCell(cell301);

			PdfPCell cell301a = new PdfPCell(
					new Paragraph("G.R. NO.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell301a.setColspan(1);
			cell301a.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell301a.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell301a.setRotation(90);
			tableHeader.addCell(cell301a);

			PdfPCell cell302 = new PdfPCell(
					new Paragraph("STUDENT NAME", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell302.setColspan(1);
			cell302.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell302.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell302.setRotation(90);
			tableHeader.addCell(cell302);

			PdfPCell cell303 = new PdfPCell(
					new Paragraph("WORKING DAYS", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell303.setColspan(1);
			cell303.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell303.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell303.setRotation(90);
			tableHeader.addCell(cell303);

			PdfPCell cell304 = new PdfPCell(
					new Paragraph("PRESENT DAYS", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell304.setColspan(1);
			cell304.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell304.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell304.setRotation(90);
			tableHeader.addCell(cell304);

			int addToFloat = 5;
			float []columnWidths=new float[9+(subjectHeadCount)];
			columnWidths[0]=  0.8f;
			columnWidths[1]=  1.7f;
			columnWidths[2]=  7.8f;
			columnWidths[3]=  0.8f;
			columnWidths[4]=  0.8f;
			
			Set setSubjectHeader = maxSubMarks.entrySet();
			Iterator n = setSubjectHeader.iterator();
			while (n.hasNext()) {
				Map.Entry me = (Map.Entry) n.next();
				subTitle = me.getKey().toString();
				String[] spaceCount = subTitle.split("\\_");
				if(!subTitle.contains("_")){
					columnWidths[addToFloat]=  0.8f;
				}
				else if(subTitle.length() == 1){
					columnWidths[addToFloat]=  1.2f;
				}else{
					columnWidths[addToFloat]=  1.6f;
				}
				
				subTitle = subTitle.replaceFirst("_", "\n");
				subTitle = subTitle.replace("_", " ");
				PdfPCell cell305 = new PdfPCell(
						new Paragraph(subTitle, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell305.setColspan(1);
				cell305.setNoWrap(false);
				cell305.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell305.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell305.setRotation(90);
				tableHeader.addCell(cell305);
				addToFloat = addToFloat+1;
			}
			columnWidths[addToFloat]=  0.8f;
			columnWidths[addToFloat+1]=  1.2f;
			columnWidths[addToFloat+2]=  2.5f;
			columnWidths[addToFloat+3]=  8.6f;
			
			PdfPCell cell314 = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell314.setColspan(1);
			cell314.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell314.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell314.setRotation(90);
			tableHeader.addCell(cell314);

			PdfPCell cell315 = new PdfPCell(new Paragraph("PERCENTAGE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell315.setColspan(1);
			cell315.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell315.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell315.setRotation(90);
			tableHeader.addCell(cell315);

			PdfPCell cell316 = new PdfPCell(new Paragraph("REMARK", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell316.setColspan(1);
			cell316.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell316.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell316.setRotation(90);
			tableHeader.addCell(cell316);

			PdfPCell cell316a = new PdfPCell(new Paragraph("RESULT", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell316a.setColspan(1);
			cell316a.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell316a.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell316a.setRotation(90);
			tableHeader.addCell(cell316a);

			PdfPCell cell317 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell317.setColspan(1);
			cell317.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell317.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell317);

			PdfPCell cell317a = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell317a.setColspan(1);
			cell317a.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell317a.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell317a);

			PdfPCell cell318 = new PdfPCell(new Paragraph("OUT OF", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell318.setColspan(1);
			cell318.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell318.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell318);

			PdfPCell cell319 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell319.setColspan(1);
			cell319.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell319.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell319);

			PdfPCell cell320 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell320.setColspan(1);
			cell320.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell320.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell320);

			Set setSubjectMaxMarks = maxSubMarks.entrySet();
			Iterator q = setSubjectMaxMarks.iterator();
			while (q.hasNext()) {
				Map.Entry me = (Map.Entry) q.next();
				LinkedHashMap<String, String> subjectMax = new LinkedHashMap<String, String>();
				subTitle = me.getKey().toString();
				subjectMax = (LinkedHashMap<String, String>) me.getValue();
				outOfMarks = subjectMax.get(sem+"_"+subTitle+"_total");
				gradeMarks = subjectMax.get("marks_grade"); 
				if(gradeMarks.equalsIgnoreCase("MARKS") && outOfMarks.contains(".") && Double.parseDouble(outOfMarks) > 0){
					outOfMarks = outOfMarks.substring(0, outOfMarks.indexOf("."));
				} else if(gradeMarks.equalsIgnoreCase("MARKS") && Double.parseDouble(outOfMarks) == 0){
					outOfMarks = "-";
				} else {
					outOfMarks = " ";
				}
				
				PdfPCell cellMarks = new PdfPCell(new Paragraph(outOfMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cellMarks.setColspan(1);
				cellMarks.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellMarks.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableHeader.addCell(cellMarks);
			}
			
			PdfPCell cell339 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell339.setColspan(1);
			cell339.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell339.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell339);

			PdfPCell cell340 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell340.setColspan(1);
			cell340.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell340.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell340);

			PdfPCell cell341 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell341.setColspan(1);
			cell341.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell341.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell341);

			PdfPCell cell341a = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell341a.setColspan(1);
			cell341a.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell341a.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell341a);
			
			Chunk blankChunk = new Chunk("");
			Paragraph blankParagraph = new Paragraph();
			blankParagraph.add(blankChunk);
			blankParagraph.setSpacingBefore(-5);

			double count = marksSemDataMap.size();
			String optionalSubject = "";
			String attendance = "", working = "", attended = "";
			String result = "";
			int rowCount= 0;
			int pageCount= 1;
			Set setMarksSemDataMap = marksSemDataMap.entrySet();
			Iterator m = setMarksSemDataMap.iterator();
			while (m.hasNext()) {
				rowCount++;
				lastPage = true;
				LinkedHashMap<String, String> grDetail = new LinkedHashMap<String, String>();
				LinkedHashMap<String, String> grResultMap = new LinkedHashMap<String, String>();
				Map.Entry me = (Map.Entry) m.next();
				grNo = me.getKey().toString();
				grDetail = (LinkedHashMap<String, String>) me.getValue();
				grResultMap = (LinkedHashMap<String, String>) resultMap.get(grNo);
				optionalSubject = studentOptSubAllotMap.get(grNo) == null ? "" :studentOptSubAllotMap.get(grNo).get("optionalSubject");
				if(grResultMap != null){
					attendance = grResultMap.get("attendance");
					if(attendance.equalsIgnoreCase("0/0")) {
						attendance = grResultMap.get("ATT_"+sem.toUpperCase());
					}
					if(attendance != null && !attendance.equalsIgnoreCase("NA") && !attendance.equalsIgnoreCase("")) {
						attended = attendance.substring(0,attendance.indexOf("/")).equalsIgnoreCase("null") ? "" : attendance.substring(0,attendance.indexOf("/"));
						working = attendance.substring(attendance.indexOf("/")+1);
					}
					else {
						attended = "-";
						working = "-";
					}
					
				}
				else{
					attended = "-";
				}
					
				if(grResultMap != null){
					PdfPCell cell342 = new PdfPCell(
							new Paragraph(grDetail.get("rollNo"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell342.setColspan(1);
					cell342.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell342.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell342);

					PdfPCell cell342a = new PdfPCell(new Paragraph(grNo, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell342a.setColspan(1);
					cell342a.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell342a.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell342a);

					if(!isDoubleLine && grDetail.get("name").length() > 40){
						isDoubleLine = true;
						addRow = addRow + 1;
					}
					PdfPCell cell343 = new PdfPCell(
							new Paragraph(commonObj.FirstWordCap(grDetail.get("name")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell343.setColspan(1);
					cell343.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell343.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell343);

					PdfPCell cell344 = new PdfPCell(new Paragraph(working, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell344.setColspan(1);
					cell344.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell344.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell344);

					PdfPCell cell345 = new PdfPCell(new Paragraph(attended, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell345.setColspan(1);
					cell345.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell345.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell345);

					double subjectCount = 0.0;
//					int wordCount = 0;
					Set setSubjectMarks = maxSubMarks.entrySet();
					Iterator p = setSubjectMarks.iterator();
					while (p.hasNext()) {
						isMg = false;
						LinkedHashMap<String, String> subjectMax = new LinkedHashMap<String, String>();
						Map.Entry meSubject = (Map.Entry) p.next();
						subTitle = me.getKey().toString();
						subjectMax = (LinkedHashMap<String, String>) meSubject.getValue();
						subTitle = meSubject.getKey().toString();
						gradeMarks = subjectMax.get("marks_grade"); 
						
						if(!optionalSubject.contains(subTitle+"_YES") || optionalSubject.equalsIgnoreCase("")){
							subjectCount = subjectCount + 1.0;
						}
						
						if(!sem.equalsIgnoreCase("final")){
							if(gradeMarks.equalsIgnoreCase("MARKS")) {
								subjectMarks = Double.parseDouble(grDetail.get(subTitle + "_MARKS") == null ? "0" : grDetail.get(subTitle + "_MARKS"));
							}
							else {
								subjectMarksDisp = grResultMap.get(subTitle+"_SEM1");
								if(!subjectMarksDisp.equalsIgnoreCase("NA")){
									subjectMarksDisp = subjectMarksDisp.substring(0, subjectMarksDisp.indexOf("("));
									subjectMarksDisp = subjectMarksDisp.substring(0, subjectMarksDisp.indexOf("+"));
								}
							}
						} else{
							finalSubMarks = grResultMap.get(subTitle + "_FINAL") == null ? "0" : grResultMap.get(subTitle + "_FINAL");
							if(finalSubMarks.contains("MG")){
								isMg = true;
								if(!isDoubleLine){
									addRow = addRow + 1;
								}
							}
							if(!finalSubMarks.equalsIgnoreCase("NA")){
								finalSubMarks = finalSubMarks.substring(0, finalSubMarks.indexOf("("));
							}
							if(commonObj.validateNumber(finalSubMarks)){
								subjectMarks = Double.parseDouble(finalSubMarks);
							}
						}
						subjectTotal = Double.parseDouble(grDetail.get(subTitle + "_TOTAL") == null ? "0" : grDetail.get(subTitle + "_TOTAL"));
						total = total + subjectTotal;
						totalObtained = totalObtained + subjectMarks;
						subGrade = commonObj.getGradeFromMarks(subjectTotal, subTitle, subjectMarks, std);

						if(grDetail.get(subTitle + "_MARKS") == null && finalSubMarks.equalsIgnoreCase("0")){
							subjectMarksDisp = "-";
							subGrade = "-";
						}
						else{
							if(subjectMarksDisp.equalsIgnoreCase("") && commonObj.validateNumber(subjectMarks+"") && commonObj.validateNumber(finalSubMarks)){
								subjectMarksDisp = String.format("%.0f", subjectMarks);
							} else if(subjectMarksDisp.equalsIgnoreCase("")){
								subjectMarksDisp = finalSubMarks;
							}
						}
						if(subjectMarksDisp.contains("+")){
//							subjectMarksDisp = subjectMarksDisp.substring(0,  subjectMarksDisp.indexOf("+"));
							if(!isDoubleLine && !subTitle.contains("_")){
								addRow = addRow + 1;
								isDoubleLine = true;
							}
						}
						if(subjectMarksDisp.equalsIgnoreCase("NA")){
							subjectMarksDisp = "-";
						}
						
						PdfPCell cell346;
						if(isMg){
							subjectMarksDisp = subjectMarksDisp+" MG";
						}
						cell346 = new PdfPCell(new Paragraph(subjectMarksDisp,
								FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell346.setColspan(1);
						cell346.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell346.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table1.addCell(cell346);

						subjectMarks = 0;
						finalSubMarks = "0";
						subjectMarksDisp = "";
					}

					if(sem.equalsIgnoreCase("final")){
						obtainedStr = grResultMap.get("semMarksObtained");
						if(!obtainedStr.contains("+") && !obtainedStr.contains("-")){
							totalObtained = Double.parseDouble(obtainedStr);
							obtainedStr = String.format("%.0f", totalObtained);
						}
						else{
							if(!isDoubleLine && !subTitle.contains("_")){
								addRow = addRow + 1;
								isDoubleLine = true;
							}
						}
						if(commonObj.validateOnlyNumber(grResultMap.get("semPercent"))) {
							percent = Double.parseDouble(grResultMap.get("semPercent"));
						}
					}
					else{
						percent = Double.parseDouble(grResultMap.get("semPercent"));
						obtainedStr = grResultMap.get("semMarksObtained");
//						percent = totalObtained/subjectCount;
//						obtainedStr = String.format("%.0f", totalObtained);
					}
					PdfPCell cell364 = new PdfPCell(
							new Paragraph(obtainedStr, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell364.setColspan(1);
					cell364.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell364.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell364);

					if(!isDoubleLine && obtainedStr.length() > 3){
						addRow = addRow + 1;
						isDoubleLine = true;
					}
					
					PdfPCell cell365 = new PdfPCell(new Paragraph(String.format("%.2f", percent),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell365.setColspan(1);
					cell365.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell365.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell365);

					if(grResultMap != null){
						result = grResultMap.get("semResult");
						if(result.contains("|")){
							result = result.substring(result.indexOf("|")+1);
						}
					}
					else{
						result = "-";
					}
					
					if(result.contains("Detained") || result.contains("detained")){
						totalGrade = "Poor";
					}
					else{
						totalGrade = commonObj.getProgressFromPercentage(percent);
					}
					
					String passFail = grResultMap.get("semResult").toString();
					totalGrade = commonObj.getResultRemark(passFail, percent);
							
					PdfPCell cell366 = new PdfPCell(
							new Paragraph(totalGrade, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell366.setColspan(1);
					cell366.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell366.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell366);

					PdfPCell cell367;
					if(!isDoubleLine && result.length() > 40){
						addRow = addRow + 1;
						isDoubleLine = true;
					}
					cell367 = new PdfPCell(
							new Paragraph(result, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell367.setColspan(1);
					cell367.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell367.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell367);
				}
				

				if((rowCount+addRow)%displayRows==0 || (rowCount+addRow) > displayRows){
					document.add(paragraph1);
					document.add(paragraph2);
					document.add(paragraph3);
					document.add(paragraph4);
					document.add(paragraph5);
					document.add(paragraph6);
					document.add(paragraph7);
					
					tableHeader.setWidths(columnWidths);
					table1.setWidths(columnWidths);
					
					document.add(tableHeader);
					document.add(blankParagraph);
					document.add(table1);
					table1.flushContent();
					pageCount++;
					rowCount = 0;
					addRow = 0;
					document.newPage();
					lastPage = false;
				}
				totalObtained = 0;
				total = 0;
				isDoubleLine = false;
			}

			if(lastPage){
				document.add(paragraph1);
				document.add(paragraph2);
				document.add(paragraph3);
				document.add(paragraph4);
				document.add(paragraph5);
				document.add(paragraph6);
				document.add(paragraph7);
				
				tableHeader.setWidths(columnWidths);
				table1.setWidths(columnWidths);
				
				document.add(tableHeader);
				document.add(blankParagraph);
				document.add(table1);
			}
			document.close();
			JOptionPane.showMessageDialog(null, "Report generated successfully.");
		} catch (Exception e) {
			fileOpenFlag = false;
			commonObj.logException(e);
		}

		// open pdf
		if (fileOpenFlag) {
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
				logger.error(":: -----Exception---- ::\n" + e);
				commonObj.logException(e);
			}
		}
	}
}