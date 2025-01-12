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

public class MarksGradeSheet_PDF {

	static String fileName = "";
	static String fileAddress = "";
	static String path = "";
	static boolean fileOpenFlag = false;
	Common commonObj = new Common();
	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	static Logger logger = Logger.getLogger(BonafidePDF.class.getName());

	public MarksGradeSheet_PDF(SessionData sessionData, String exam, String subject, String std, String div,
			String academic, LinkedHashMap<String, LinkedHashMap<String, String>> marksSemDataMap,
			LinkedHashMap<String, LinkedHashMap<String, String>> studentOptSubAllotMap,
			LinkedHashMap<String, LinkedHashMap<String, String>> maxSubMarks,
			LinkedHashMap<String, LinkedHashMap<String, String>> resultMap, 
			LinkedHashMap<String,String> leftDataMap) throws DocumentException {

		System.gc();
		fileOpenFlag = true;
		int displayRows= 26;
		String examHeader = "", sem = "", semInitial = "", subMarks = "", subjectMarksDisp = "";
		double total = 0, totalObtained = 0, subjectMarks = 0, subjectTotal = 0;
		String dispTotalA = "", dispTotalB = "", dispTotalAB = "";
		int subjectHeadCount = maxSubMarks.size(), addRow = 0, fontSize = 10;
		String subTitle = "", grNo = "", subGrade = "", totalGrade = "";
		String bonafide_header = sessionData.getConfigMap().get("BONAFIDE_HEADER_" + sessionData.getAppType());
		String bonafide_header_0 = sessionData.getConfigMap().get("BONAFIDE_HEADER_0_" + sessionData.getAppType());
		LinkedHashMap<String, String> subMarksDetails;

		try {
			if(!bonafide_header_0.trim().equalsIgnoreCase("")){
				bonafide_header_0 = bonafide_header_0 + " \n";
				displayRows = 25;
			}
			
			if (exam.equalsIgnoreCase("Semester 1")) {
				sem = "sem1";
				semInitial = "F";
				examHeader = "FIRST SEMESTER";
			} else if (exam.equalsIgnoreCase("Semester 2")) {
				sem = "sem2";
				semInitial = "S";
				examHeader = "SECOND SEMESTER";
			}
			sem = sem.toUpperCase(); //changed for SEM1 report was failing on 28112014
			
			path = commonObj.createTodayFolder(
					commonObj.getDriveName() + sessionData.getConfigMap().get("REPORT_PDF_PATH_" + sessionData.getDBName()), true)
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
//			Chunk chunkHeader = new Chunk(bonafide_header_0 + " \n");
//			Font font = FontFactory.getFont("TIMES_ROMAN");
//			font.setStyle(Font.NORMAL);
//			font.setSize(12);
//			chunkHeader.setFont(font);
//			Paragraph paragraph = new Paragraph();
//			paragraph.add(chunkHeader);
//			paragraph.setAlignment(Element.ALIGN_CENTER);
//			paragraph.setSpacingBefore(pageStart);

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

			Chunk chunk3 = new Chunk("MARKS AND GRADE SHEET");
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

			Chunk chunk6 = new Chunk("																Div :");
			Font font6 = FontFactory.getFont("TIMES_ROMAN");
			font6.setStyle(Font.BOLD);
			font6.setSize(12);
			chunk6.setFont(font6);
			Paragraph paragraph6 = new Paragraph();
			paragraph6.add(chunk6);
			paragraph6.setAlignment(Element.ALIGN_LEFT);
			paragraph6.setSpacingBefore(-16);

			Chunk chunk7 = new Chunk(
					"																									"
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
			
			tableHeader = new PdfPTable(9+(subjectHeadCount*2));
			tableHeader.setWidthPercentage(100);
			tableHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tableHeader.setSpacingBefore(5.0f);
			
			table1 = new PdfPTable(9+(subjectHeadCount*2));
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
			float []columnWidths=new float[9+(subjectHeadCount*2)];
			columnWidths[0]=  0.8f;
			columnWidths[1]=  1.5f;
			columnWidths[2]=  8f;
			columnWidths[3]=  0.8f;
			columnWidths[4]=  0.8f;
//			float[] columnWidths = new float[] { 0.8f, 1.4f, 8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f,
//					0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 1f, 0.8f, 4.8f };
			
			Set setSubjectHeader = maxSubMarks.entrySet();
			Iterator n = setSubjectHeader.iterator();
			while (n.hasNext()) {
				Map.Entry me = (Map.Entry) n.next();
				subTitle = me.getKey().toString();
				columnWidths[addToFloat]=  0.8f;
				columnWidths[addToFloat+1]=  0.8f;
				
				PdfPCell cell305 = new PdfPCell(
						new Paragraph(subTitle.replace("_", "\n"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell305.setColspan(2);
				cell305.setNoWrap(false);
				cell305.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell305.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell305.setRotation(90);
				tableHeader.addCell(cell305);
				addToFloat = addToFloat+2;
			}
			columnWidths[addToFloat]=  0.8f;
			columnWidths[addToFloat+1]=  1f;
			columnWidths[addToFloat+2]=  0.8f;
			columnWidths[addToFloat+3]=  3.5f;
			
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

			PdfPCell cell316 = new PdfPCell(new Paragraph("GRADE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell316.setColspan(1);
			cell316.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell316.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell316.setRotation(90);
			tableHeader.addCell(cell316);

			PdfPCell cell316a = new PdfPCell(new Paragraph("REMARK", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
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

			PdfPCell cell318 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
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

			for(int i = 0; i < subjectHeadCount; i++){
				PdfPCell cellMarks = new PdfPCell(new Paragraph("Marks", FontFactory.getFont(FontFactory.TIMES_ROMAN, 7)));
				cellMarks.setColspan(1);
				cellMarks.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellMarks.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableHeader.addCell(cellMarks);
				
				PdfPCell cellGrade = new PdfPCell(new Paragraph("Grade", FontFactory.getFont(FontFactory.TIMES_ROMAN, 7)));
				cellGrade.setColspan(1);
				cellGrade.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellGrade.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableHeader.addCell(cellGrade);
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
			String remark = "";
			int rowCount= 0;
			int pageCount= 1;
			Set setMarksSemDataMap = marksSemDataMap.entrySet();
			Iterator m = setMarksSemDataMap.iterator();
			while (m.hasNext()) {
				rowCount++;
				LinkedHashMap<String, String> grDetail = new LinkedHashMap<String, String>();
				LinkedHashMap<String, String> grResultMap = new LinkedHashMap<String, String>();
				Map.Entry me = (Map.Entry) m.next();
				grNo = me.getKey().toString();
				grDetail = (LinkedHashMap<String, String>) me.getValue();
				grResultMap = (LinkedHashMap<String, String>) resultMap.get(grNo);
				optionalSubject = studentOptSubAllotMap.get(grNo) == null ? "" :studentOptSubAllotMap.get(grNo).get("optionalSubject");
				
				if(grResultMap != null){
					attendance = grResultMap.get("attendance");
					attended = attendance.substring(0,attendance.indexOf("/")).equalsIgnoreCase("null") ? "" : attendance.substring(0,attendance.indexOf("/"));
					working = attendance.substring(attendance.indexOf("/")+1);
				}
				else{
					if(sem.equalsIgnoreCase("final") && rowCount != count){
						continue;
					}
					attended = "-";
				}
						
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

				double subjectCount = 0.0, semSubjectTotal = 0.0;
				Set setSubjectMarks = maxSubMarks.entrySet();
				Iterator p = setSubjectMarks.iterator();
				while (p.hasNext()) {
					fontSize = 10;
					Map.Entry meSubject = (Map.Entry) p.next();
					subTitle = meSubject.getKey().toString();
					subMarksDetails = (LinkedHashMap<String, String>) meSubject.getValue();
					semSubjectTotal = Double.parseDouble(subMarksDetails.get(sem+"_"+subTitle+"_total"));
					
					if(semSubjectTotal > 0 && (!optionalSubject.contains(subTitle+"_YES") || optionalSubject.equalsIgnoreCase(""))){
						subjectCount = subjectCount + 1.0;
					}
					subjectMarks = Double.parseDouble(grDetail.get(subTitle + "_MARKS") == null ? "0" : grDetail.get(subTitle + "_MARKS"));
					subjectTotal = Double.parseDouble(grDetail.get(subTitle + "_TOTAL") == null ? "0" : grDetail.get(subTitle + "_TOTAL"));
					total = total + subjectTotal;
					totalObtained = totalObtained + subjectMarks;
					subGrade = commonObj.getGradeFromMarks(subjectTotal, subTitle, subjectMarks, std);
					if(grResultMap.get(subTitle+"_"+sem.toUpperCase().toString()).contains("RTE")) {
						subGrade = "RTE";
					}

					if(leftDataMap != null && leftDataMap.get(grNo) == null){
						if(grDetail.get(subTitle + "_MARKS") == null){
							subjectMarksDisp = "-";
							subGrade = "-";
						}
						else{
							subjectMarksDisp = String.format("%.0f", subjectMarks);
						}
					}
					else if(grDetail.get(subTitle + "_MARKS") != null){
						subjectMarksDisp = String.format("%.0f", subjectMarks);
					}
					else{
						subjectMarksDisp = "-";
						subGrade = "-";
					}
					
					PdfPCell cell346 = new PdfPCell(new Paragraph(subjectMarksDisp,
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell346.setColspan(1);
					cell346.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell346.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell346);

					if(subGrade.contains("RTE")) {
						fontSize = 9;
					}
					PdfPCell cell347 = new PdfPCell(
							new Paragraph(subGrade, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
					cell347.setColspan(1);
					cell347.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell347.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell347);
				}

				dispTotalA = String.format("%.0f", totalObtained);
				if(leftDataMap != null && leftDataMap.get(grNo) != null){
					dispTotalA = "-";
				}
				PdfPCell cell364 = new PdfPCell(
						new Paragraph(dispTotalA, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell364.setColspan(1);
				cell364.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell364.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell364);

				dispTotalB = String.format("%.1f", (totalObtained/subjectCount));
				if(leftDataMap != null && leftDataMap.get(grNo) != null){
					dispTotalB = "-";
				}
				PdfPCell cell365 = new PdfPCell(new Paragraph(dispTotalB,
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell365.setColspan(1);
				cell365.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell365.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell365);

				totalGrade = commonObj.getGradeFromMarks(total, "", totalObtained, std);
				if(leftDataMap != null && (totalGrade.equalsIgnoreCase("") || leftDataMap.get(grNo) != null)){
					totalGrade = "-";
				}
				PdfPCell cell366 = new PdfPCell(
						new Paragraph(totalGrade, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell366.setColspan(1);
				cell366.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell366.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell366);

				if(!totalGrade.contains("D") && !totalGrade.contains("E") && !totalGrade.equalsIgnoreCase("-")){
					remark = "Acquired ability level";
				}
				else{
					remark = "-";
				}
				PdfPCell cell367 = new PdfPCell(
						new Paragraph(remark, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell367.setColspan(1);
				cell367.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell367.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell367);

//				if(rowCount != 0 && pageCount == 1 && rowCount%Integer.parseInt("27")==0){
				if(rowCount != 0 && pageCount == 1 && (rowCount+addRow)%displayRows==0){
//					document.add(paragraph);
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
					addRow = 0;
					document.newPage();
				}
//				else if(pageCount != 1 && rowCount != 27 && rowCount%Integer.parseInt("27")==0){
				else if(pageCount != 1 && (rowCount-1) != displayRows && ((rowCount)+addRow)%displayRows==0){
//					document.add(paragraph);
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
					pageCount++;
					addRow = 0;
					table1.flushContent();
					document.newPage();
				}
//				else if((rowCount+1) == count){
				else if((rowCount+addRow) == count){
//					document.add(paragraph);
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
					pageCount++;
					addRow = 0;
					table1.flushContent();
					document.newPage();
				}
				
				totalObtained = 0;
				total = 0;
			}

			// table1.setSpacingAfter(0.0f);

//			document.newPage();
//			document.open();// PDF document opened........

//			document.add(Chunk.NEWLINE); // Something like in HTML :-)

			/*float[] columnWidths = new float[] { 0.8f, 1.4f, 8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f,
					0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 1f, 0.8f, 4.8f };
			tableHeader.setWidths(columnWidths);
			table1.setWidths(columnWidths);*/

			/*document.add(paragraph);
			document.add(paragraph1);
			document.add(paragraph2);
			document.add(paragraph3);
			document.add(paragraph4);
			document.add(paragraph5);
			document.add(paragraph6);
			document.add(paragraph7);*/
//			document.add(tableHeader);
//			document.add(blankParagraph);
//			document.add(table1);

//			document.add(Chunk.NEWLINE); // Something like in HTML :-)

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