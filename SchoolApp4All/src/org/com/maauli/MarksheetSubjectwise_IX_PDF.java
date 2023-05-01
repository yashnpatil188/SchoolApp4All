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

public class MarksheetSubjectwise_IX_PDF {

	static String fileName = "";
	static String fileAddress = "";
	static String path = "";
	static boolean fileOpenFlag = false;
	Common commonObj = new Common();
	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	static Logger logger = Logger.getLogger(BonafidePDF.class.getName());

	public MarksheetSubjectwise_IX_PDF(SessionData sessionData, String exam, String subject, String std, String div,
			String academic, LinkedHashMap<String, LinkedHashMap<String, String>> marksSemDataMap,
			LinkedHashMap<String, LinkedHashMap<String, String>> studentOptSubAllotMap,
			LinkedHashMap<String, LinkedHashMap<String, String>> maxSubMarks,
			LinkedHashMap<String,String> leftDataMap) throws DocumentException {

		System.gc();
		fileOpenFlag = true;
		int displayRows= 45, fontSize = 10, colMerge = 7;
		boolean isDoubleLine = false, lastPage = true;
		String examHeader = "", sem = "", semInitial = "", subMarks = "", ctStr = "", average = "0", marks_grade = "",
				grade = "", sem1Total = "", sem2Total = "", noOfRecords = "45", divisorStr = "", name = "", optional = "";
		String dispTotalA = "", dispTotalB = "", dispTotalAB = "";
		int stdInt = commonObj.RomanToInteger(std), column = 19, addRow = 0, pageCount = 0, divisorHeader = 0, academicStart = 0;
		double totalA = 0, totalB = 0, totalAB = 0, total = 0, subTotalA = 0, subTotalB = 0, subTotal_AB = 0, subTotal = 0,
				pageNumber = 0, divisor = 0;
		LinkedHashMap<String, String> subMaxMarks = new LinkedHashMap<String, String>();

		try {
			String bonafide_header = bundle.getString("BONAFIDE_HEADER_" + sessionData.getAppType());
			String bonafide_header_0 = bundle.getString("BONAFIDE_HEADER_0_" + sessionData.getAppType());
			if(!academic.equalsIgnoreCase("")) {
				academicStart = Integer.parseInt(academic.substring(0, 4));
			}
			if(!bonafide_header_0.trim().equalsIgnoreCase("")){
				bonafide_header_0 = bonafide_header_0 + " \n";
				displayRows = 45;
				noOfRecords = "45";
			}
			
			marks_grade = maxSubMarks.get(subject).get("marks_grade");
			optional = maxSubMarks.get(subject).get("optional");
			subMaxMarks = maxSubMarks.get(subject);
			if (stdInt >= 9) {
				ctStr = "_ct";
			}
			if (exam.equalsIgnoreCase("Semester 1")) {
				sem = "sem1";
				semInitial = "F";
				examHeader = "FIRST SEMESTER";
				divisor = 1;
				if (commonObj.is9thEvaluation(std, academic)) {
					column = 11;
				}
				else {
					column = 10;
				}
			} else if (exam.equalsIgnoreCase("Semester 2")) {
				sem = "sem2";
				semInitial = "S";
				examHeader = "SECOND SEMESTER";
				divisor = 1;
				if (commonObj.is9thEvaluation(std, academic)) {
					column = 11;
				}
				else {
					column = 10;
				}
			} else if (exam.equalsIgnoreCase("Final")) {
				sem = "final";
				examHeader = "FINAL";
				divisor = 2;
				if (marks_grade.equalsIgnoreCase("Grade")) {
					noOfRecords = displayRows+"";
					column = 22;
				} else 
					if(commonObj.is9thEvaluation(std, academic)){
					column = 21;
				} else {
					column = 19;
				}
			}
			
			// for column width size
			float[] columnWidths = null;
			if (commonObj.is9thEvaluation(std, academic) && (sem.equalsIgnoreCase("sem1") || sem.equalsIgnoreCase("sem2"))) {
				columnWidths = new float[] { 1.2f, 2.4f, 9f, 1f, 1f, 1f, 1.2f, 1f, 1f, 1f, 1.2f };
				colMerge = 8;
			} 
			else if (stdInt < 11 && (sem.equalsIgnoreCase("sem1") || sem.equalsIgnoreCase("sem2"))) {
				columnWidths = new float[] { 1.2f, 2.4f, 10f, 1f, 1f, 1f, 1.2f, 1f, 1f, 1.2f };
			} 
			else if (stdInt > 10 && (sem.equalsIgnoreCase("sem1") || sem.equalsIgnoreCase("sem2"))) {
				column = 9;
				columnWidths = new float[] { 1.2f, 2.4f, 10f, 1.2f, 1.2f, 1.2f, 1.2f, 1.2f, 1.2f };
			} else if (stdInt < 11) {
				if (marks_grade.equalsIgnoreCase("Grade")) {
					columnWidths = new float[] { 1.2f, 2.4f, 8.8f, 1.2f, 1.2f, 1.2f, 1.2f, 1.2f, 1.2f, 1.2f, 1.2f, 1.2f, 1.2f, 1.2f, 1.2f,
							1.2f, 1.2f, 1.2f, 1.2f, 1.2f, 1.2f, 1.2f };
					colMerge = 8;
				} else 
					if(commonObj.is9thEvaluation(std, academic)){
					columnWidths = new float[] { 1.2f, 2.4f, 10f, 1f, 1f, 1f, 1f, 1.2f, 1f, 1f, 1.2f, 1f, 1f, 1f, 1.2f, 1f,
							1f, 1f, 1.2f, 1.2f, 1.2f };
					colMerge = 8;
				} else {
					columnWidths = new float[] { 1.2f, 2.4f, 10f, 1f, 1f, 1f, 1.2f, 1f, 1f, 1.2f, 1f, 1f, 1f, 1.2f, 1f,
							1f, 1.2f, 1.2f, 1.2f };
				}
			} else if (stdInt > 10) {
				colMerge = 6;
				if (marks_grade.equalsIgnoreCase("Grade")) {
					column = 18;
					columnWidths = new float[] { 1.2f, 2.4f, 10.8f, 1f, 1f, 1f, 1.2f, 1f, 1.2f, 1f, 1f, 1f, 1.2f,
							1f, 1.2f, 1.2f, 1.2f, 1.2f };
				} else {
					column = 17;
					columnWidths = new float[] { 1.2f, 2.4f, 12f, 1f, 1f, 1f, 1.2f, 1f, 1.2f, 1f, 1f, 1f, 1.2f,
							1f, 1.2f, 1.2f, 1.2f };
				}
			}
						
			path = commonObj.createTodayFolder(
					commonObj.getDriveName() + bundle.getString("REPORT_PDF_PATH_" + sessionData.getDBName()), true)
					+ "/";
			fileName = "Marksheet_Subjectwise_" + subject + "_" + std + "_" + div + "_" + commonObj.timeInMillis()
					+ ".pdf";
			fileAddress = path + fileName;
			File file = new File(fileAddress);
			FileOutputStream fileout = new FileOutputStream(file);
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, fileout);
			document.addHeader("ABC", "XYZ");
			document.addTitle("Marksheet Subjectwise");
			document.open();// PDF document opened........

			int pageStart = -60;
			Chunk chunkHeader = new Chunk("");
			Font font = FontFactory.getFont("TIMES_ROMAN");
			font.setStyle(Font.NORMAL);
			font.setSize(12);
			chunkHeader.setFont(font);
			Paragraph paragraph = new Paragraph();
			paragraph.add(chunkHeader);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setSpacingBefore(pageStart);

			Chunk chunkHeader1 = new Chunk(bonafide_header_0 + bonafide_header);
			Font font1 = FontFactory.getFont("TIMES_ROMAN");
			font1.setStyle(Font.NORMAL);
			font1.setSize(12);
			chunkHeader1.setFont(font1);
			Paragraph paragraph1 = new Paragraph();
			paragraph1.add(chunkHeader1);
			paragraph1.setAlignment(Element.ALIGN_CENTER);
			paragraph1.setSpacingBefore(pageStart+20);
			paragraph1.setSpacingAfter(5.0f);
			
			PdfPCell cell298 = new PdfPCell(new Paragraph(examHeader + " EXAMINATION " + academic,
					FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			cell298.setColspan(column);
			cell298.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell298.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell299 = new PdfPCell(
					new Paragraph("MARKSHEET", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			cell299.setColspan(column);
			cell299.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell299.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell300 = new PdfPCell(
					new Paragraph("SUBJECT: " + subject.replace("_", " ") + "			STD.: " + std + "-" + div,
							FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			cell300.setColspan(column);
			cell300.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell300.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell301 = new PdfPCell(new Paragraph("ROLL NO.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			cell301.setColspan(1);
			cell301.setRowspan(2);
			cell301.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell301.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell301.setRotation(90);

			PdfPCell cell302 = new PdfPCell(new Paragraph("GR NO.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			cell302.setColspan(1);
			cell302.setRowspan(2);
			cell302.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell302.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell302.setRotation(90);

			PdfPCell cell302a = new PdfPCell(new Paragraph("NAME", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			cell302a.setColspan(1);
			cell302a.setRowspan(2);
			cell302a.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell302a.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell302a.setRotation(90);

			PdfPCell cell306 = new PdfPCell(
					new Paragraph("FIRST SEMESTER (A)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell306.setColspan(colMerge);
			cell306.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell306.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell307 = new PdfPCell(
					new Paragraph("SECOND SEMESTER (B)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell307.setColspan(colMerge);
			cell307.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell307.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell308 = new PdfPCell(
					new Paragraph("TOTAL (A+B)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell308.setColspan(1);
			cell308.setRowspan(2);//changed from 3 to add out of marks
			cell308.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell308.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell308.setRotation(90);

			PdfPCell cell309 = new PdfPCell(new Paragraph("AVERAGE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell309.setColspan(1);
			cell309.setRowspan(2);//changed from 3 to add out of marks
			cell309.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell309.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell309.setRotation(90);

			PdfPCell cellGradeHeader = null;
			if (marks_grade.equalsIgnoreCase("Grade")) {
				cellGradeHeader = new PdfPCell(
						new Paragraph("GRADE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cellGradeHeader.setColspan(1);
				cellGradeHeader.setRowspan(3);
				cellGradeHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellGradeHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cellGradeHeader.setRotation(90);
			}

			PdfPCell cell312 = new PdfPCell(new Paragraph("FUT", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell312.setColspan(1);
			cell312.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell312.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell312.setRotation(90);

			PdfPCell cell313 = null;
			PdfPCell cell314 = null;
			
			if(commonObj.is9thEvaluation(std, academic)){
				cell313 = new PdfPCell(new Paragraph("LISTEN", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell314 = new PdfPCell(new Paragraph("SPEAK", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			}
			else if(stdInt < 11){
				cell313 = new PdfPCell(new Paragraph("PRES", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell314 = new PdfPCell(new Paragraph("MCAP", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			}
			else{
				cell313 = new PdfPCell(new Paragraph("ORAL", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell314 = new PdfPCell(new Paragraph("ASSIGN", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			}
			
			cell313.setColspan(1);
			cell313.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell313.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell313.setRotation(90);
			
			cell314.setColspan(1);
			cell314.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell314.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell314.setRotation(90);

			PdfPCell cell315 = new PdfPCell(new Paragraph("WRITE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell315.setColspan(1);
			cell315.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell315.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell315.setRotation(90);

			PdfPCell cell316 = new PdfPCell(new Paragraph("PRACT", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell316.setColspan(1);
			cell316.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell316.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell316.setRotation(90);

			PdfPCell cell317 = null;
			if(commonObj.is9thEvaluation(std, academic)){
				cell317 = new PdfPCell(new Paragraph("ASSIGN", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell317.setColspan(1);
				cell317.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell317.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell317.setRotation(90);
			}
			else if(stdInt < 11){
				cell317 = new PdfPCell(new Paragraph("ACT", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell317.setColspan(1);
				cell317.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell317.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell317.setRotation(90);
			}

			PdfPCell cell345 = null;
			if(commonObj.is9thEvaluation(std, academic)){
				cell345 = new PdfPCell(new Paragraph("PROJECT", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell345.setColspan(1);
				cell345.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell345.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell345.setRotation(90);
			}
			
			PdfPCell cell318 = new PdfPCell(
					new Paragraph("TOTAL (A)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell318.setColspan(1);
			cell318.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell318.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell318.setRotation(90);

			PdfPCell cell319 = new PdfPCell(new Paragraph("SUT", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell319.setColspan(1);
			cell319.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell319.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell319.setRotation(90);

			PdfPCell cell320 = null;
			PdfPCell cell321 = null;
			
			if(commonObj.is9thEvaluation(std, academic)){
				cell320 = new PdfPCell(new Paragraph("LISTEN", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell321 = new PdfPCell(new Paragraph("SPEAK", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			}
			else if(stdInt < 11){
				cell320 = new PdfPCell(new Paragraph("PRES", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell321 = new PdfPCell(new Paragraph("MCAP", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			}
			else{
				cell320 = new PdfPCell(new Paragraph("ORAL", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell321 = new PdfPCell(new Paragraph("ASSIGN", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			}
			cell320.setColspan(1);
			cell320.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell320.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell320.setRotation(90);

			cell321.setColspan(1);
			cell321.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell321.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell321.setRotation(90);

			PdfPCell cell322 = new PdfPCell(new Paragraph("WRITE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell322.setColspan(1);
			cell322.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell322.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell322.setRotation(90);

			PdfPCell cell323 = new PdfPCell(new Paragraph("PRACT", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell323.setColspan(1);
			cell323.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell323.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell323.setRotation(90);

			PdfPCell cell324 = null;
			if(commonObj.is9thEvaluation(std, academic)){
				cell324 = new PdfPCell(new Paragraph("ASSIGN", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell324.setColspan(1);
				cell324.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell324.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell324.setRotation(90);
			}
			else if(stdInt < 11){
				cell324 = new PdfPCell(new Paragraph("ACT", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell324.setColspan(1);
				cell324.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell324.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell324.setRotation(90);
			}
			
			PdfPCell cell349 = null;
			if(commonObj.is9thEvaluation(std, academic)){
				cell349 = new PdfPCell(new Paragraph("PROJECT", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell349.setColspan(1);
				cell349.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell349.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell349.setRotation(90);
			}

			PdfPCell cell303 = new PdfPCell(
					new Paragraph("TOTAL (B)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell303.setColspan(1);
			cell303.setRowspan(1);
			cell303.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell303.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell303.setRotation(90);

			PdfPCell cell303a = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell303a.setColspan(1);
			cell303a.setRowspan(1);
			cell303a.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell303a.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell303b = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell303b.setColspan(1);
			cell303b.setRowspan(1);
			cell303b.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell303b.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell303C = new PdfPCell(new Paragraph("OUT OF", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell303C.setColspan(1);
			cell303C.setRowspan(1);
			cell303C.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell303C.setVerticalAlignment(Element.ALIGN_MIDDLE);

			String maxMarks = "0";
			if (total == 0)
				totalA = Double.parseDouble(subMaxMarks.get("sem1_obt" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem1_obt" + ctStr)))) + "";
			if (maxMarks.equalsIgnoreCase("0")) {
				maxMarks = "-";
			}
			PdfPCell cell304 = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell304.setColspan(1);
			cell304.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell304.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell305 = null;
			PdfPCell cell310 = null;
			
			if(commonObj.is9thEvaluation(std, academic)){
				if (total == 0)
					totalA = totalA + Double.parseDouble(subMaxMarks.get("sem1_listen" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem1_listen" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				
				cell305 = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell305.setColspan(1);
				cell305.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell305.setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				if (total == 0)
					totalA = totalA + Double.parseDouble(subMaxMarks.get("sem1_speak" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem1_speak" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				
				cell310 = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell310.setColspan(1);
				cell310.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell310.setVerticalAlignment(Element.ALIGN_MIDDLE);
			}
			else if(stdInt < 11){
				if (total == 0)
					totalA = totalA + Double.parseDouble(subMaxMarks.get("sem1_pres" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem1_pres" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				
				cell305 = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell305.setColspan(1);
				cell305.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell305.setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				if (total == 0)
					totalA = totalA + Double.parseDouble(subMaxMarks.get("sem1_mcap" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem1_mcap" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				
				cell310 = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell310.setColspan(1);
				cell310.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell310.setVerticalAlignment(Element.ALIGN_MIDDLE);
			}
			else{
				if (total == 0)
					totalA = totalA + Double.parseDouble(subMaxMarks.get("sem1_oral" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem1_oral" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				
				cell305 = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell305.setColspan(1);
				cell305.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell305.setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				if (total == 0)
					totalA = totalA + Double.parseDouble(subMaxMarks.get("sem1_assign" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem1_assign" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				
				cell310 = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell310.setColspan(1);
				cell310.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell310.setVerticalAlignment(Element.ALIGN_MIDDLE);
			}
			
			PdfPCell cell347 = null;
			if(commonObj.is9thEvaluation(std, academic)){
				if (total == 0)
					totalA = totalA + Double.parseDouble(subMaxMarks.get("sem1_project" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem1_project" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				cell347 = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell347.setColspan(1);
				cell347.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell347.setVerticalAlignment(Element.ALIGN_MIDDLE);
			}
			
			if (total == 0)
				totalA = totalA + Double.parseDouble(subMaxMarks.get("sem1_write" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem1_write" + ctStr)))) + "";
			if (maxMarks.equalsIgnoreCase("0")) {
				maxMarks = "-";
			}
			PdfPCell cell311 = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell311.setColspan(1);
			cell311.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell311.setVerticalAlignment(Element.ALIGN_MIDDLE);

			if (total == 0)
				totalA = totalA + Double.parseDouble(subMaxMarks.get("sem1_pract" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem1_pract" + ctStr)))) + "";
			if (maxMarks.equalsIgnoreCase("0")) {
				maxMarks = "-";
			}
			PdfPCell cell325 = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell325.setColspan(1);
			cell325.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell325.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell326 = null;
			if(commonObj.is9thEvaluation(std, academic)){
				if (total == 0)
					totalA = totalA + Double.parseDouble(subMaxMarks.get("sem1_assign" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem1_assign" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				cell326 = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell326.setColspan(1);
				cell326.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326.setVerticalAlignment(Element.ALIGN_MIDDLE);
			}
			else if(stdInt < 11){
				if (total == 0)
					totalA = totalA + Double.parseDouble(subMaxMarks.get("sem1_activity" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem1_activity" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				cell326 = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell326.setColspan(1);
				cell326.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326.setVerticalAlignment(Element.ALIGN_MIDDLE);
			}

			PdfPCell cell326a = new PdfPCell(
					new Paragraph((Math.round(totalA)) + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell326a.setColspan(1);
			cell326a.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell326a.setVerticalAlignment(Element.ALIGN_MIDDLE);

			if (total == 0)
				totalB = Double.parseDouble(subMaxMarks.get("sem2_" + "obt" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem2_" + "obt" + ctStr)))) + "";
			if (maxMarks.equalsIgnoreCase("0")) {
				maxMarks = "-";
			}
			PdfPCell cell326b = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell326b.setColspan(1);
			cell326b.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell326b.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell326c = null;
			PdfPCell cell326d = null;
			if(commonObj.is9thEvaluation(std, academic)){
				if (total == 0)
					totalB = totalB + Double.parseDouble(subMaxMarks.get("sem2_" + "listen" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem2_" + "listen" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}

				cell326c = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell326c.setColspan(1);
				cell326c.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326c.setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				if (total == 0)
					totalB = totalB + Double.parseDouble(subMaxMarks.get("sem2_" + "speak" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem2_" + "speak" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				
				cell326d = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell326d.setColspan(1);
				cell326d.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326d.setVerticalAlignment(Element.ALIGN_MIDDLE);
			}
			else if(stdInt < 11){
				if (total == 0)
					totalB = totalB + Double.parseDouble(subMaxMarks.get("sem2_" + "pres" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem2_" + "pres" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}

				cell326c = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell326c.setColspan(1);
				cell326c.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326c.setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				if (total == 0)
					totalB = totalB + Double.parseDouble(subMaxMarks.get("sem2_" + "mcap" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem2_" + "mcap" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				
				cell326d = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell326d.setColspan(1);
				cell326d.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326d.setVerticalAlignment(Element.ALIGN_MIDDLE);
			}
			else{
				if (total == 0)
					totalB = totalB + Double.parseDouble(subMaxMarks.get("sem2_" + "oral" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem2_" + "oral" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}

				cell326c = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell326c.setColspan(1);
				cell326c.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326c.setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				if (total == 0)
					totalB = totalB + Double.parseDouble(subMaxMarks.get("sem2_" + "assign" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem2_" + "assign" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				
				cell326d = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell326d.setColspan(1);
				cell326d.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326d.setVerticalAlignment(Element.ALIGN_MIDDLE);
			}
			
			if (total == 0)
				totalB = totalB + Double.parseDouble(subMaxMarks.get("sem2_" + "write" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem2_" + "write" + ctStr)))) + "";
			if (maxMarks.equalsIgnoreCase("0")) {
				maxMarks = "-";
			}
			PdfPCell cell326e = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell326e.setColspan(1);
			cell326e.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell326e.setVerticalAlignment(Element.ALIGN_MIDDLE);

			if (total == 0)
				totalB = totalB + Double.parseDouble(subMaxMarks.get("sem2_" + "pract" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem2_" + "pract" + ctStr)))) + "";
			if (maxMarks.equalsIgnoreCase("0")) {
				maxMarks = "-";
			}
			PdfPCell cell326f = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell326f.setColspan(1);
			cell326f.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell326f.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell326g = null;
			if(commonObj.is9thEvaluation(std, academic)){
				if (total == 0)
					totalB = totalB + Double.parseDouble(subMaxMarks.get("sem2_" + "assign" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem2_" + "assign" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				cell326g = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell326g.setColspan(1);
				cell326g.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326g.setVerticalAlignment(Element.ALIGN_MIDDLE);
			}
			else if(stdInt < 11){
				if (total == 0)
					totalB = totalB + Double.parseDouble(subMaxMarks.get("sem2_" + "activity" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem2_" + "activity" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				cell326g = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell326g.setColspan(1);
				cell326g.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326g.setVerticalAlignment(Element.ALIGN_MIDDLE);
			}

			PdfPCell cell351 = null;
			if(commonObj.is9thEvaluation(std, academic)) {
				if (total == 0)
					totalB = totalB + Double.parseDouble(subMaxMarks.get("sem2_project" + ctStr));
				maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get("sem2_project" + ctStr)))) + "";
				if (maxMarks.equalsIgnoreCase("0")) {
					maxMarks = "-";
				}
				cell351 = new PdfPCell(new Paragraph(maxMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell351.setColspan(1);
				cell351.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell351.setVerticalAlignment(Element.ALIGN_MIDDLE);	
			}
			
			PdfPCell cell326h = new PdfPCell(
					new Paragraph((Math.round(totalB)) + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell326h.setColspan(1);
			cell326h.setRowspan(1);
			cell326h.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell326h.setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			PdfPCell cellOutOfTotal = new PdfPCell(
					new Paragraph((Math.round(totalA)+Math.round(totalB)) + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cellOutOfTotal.setColspan(1);
			cellOutOfTotal.setRowspan(1);
			cellOutOfTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellOutOfTotal.setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			totalAB = Math.round(totalA)+Math.round(totalB);
			if(totalA > 0) {
				divisorHeader = 1;
			}
			if(totalB > 0) {
				divisorHeader = divisorHeader + 1;
			}
			if(totalAB > 200) {
				divisorHeader = (int) (Math.round(totalAB)/100);
			}
			PdfPCell cellOutOfAvg = new PdfPCell(
					new Paragraph((Math.round(totalAB)/divisorHeader) + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cellOutOfAvg.setColspan(1);
			cellOutOfAvg.setRowspan(1);
			cellOutOfAvg.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellOutOfAvg.setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			PdfPCell cellGradeOutOf = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cellGradeOutOf.setColspan(1);
			cellGradeOutOf.setRowspan(1);
			cellGradeOutOf.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellGradeOutOf.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPTable tableHeader = new PdfPTable(column);
			PdfPTable table2 = new PdfPTable(column);
			tableHeader.setWidthPercentage(100);

			tableHeader.addCell(cell298);
			tableHeader.addCell(cell299);
			tableHeader.addCell(cell300);
			tableHeader.addCell(cell301);
			tableHeader.addCell(cell302);
			tableHeader.addCell(cell302a);
			if (!sem.equalsIgnoreCase("sem2"))
				tableHeader.addCell(cell306);
			if (!sem.equalsIgnoreCase("sem1"))
				tableHeader.addCell(cell307);

			if (sem.equalsIgnoreCase("Final")) {
				tableHeader.addCell(cell308);
				tableHeader.addCell(cell309);
				if (marks_grade.equalsIgnoreCase("Grade")) {
					tableHeader.addCell(cellGradeHeader);
				}
			}
			
			if (!sem.equalsIgnoreCase("sem2")) {
				if(commonObj.is9thEvaluation(std, academic)) {
					tableHeader.addCell(cell312);
					tableHeader.addCell(cell316);
					tableHeader.addCell(cell313);
					tableHeader.addCell(cell314);
					tableHeader.addCell(cell317);
					tableHeader.addCell(cell345);
					tableHeader.addCell(cell315);
					tableHeader.addCell(cell318);
				}
				else {
					tableHeader.addCell(cell312);
					tableHeader.addCell(cell313);
					tableHeader.addCell(cell314);
					tableHeader.addCell(cell315);
					tableHeader.addCell(cell316);
					if(stdInt < 11){
						tableHeader.addCell(cell317);
					}
					tableHeader.addCell(cell318);
				}
			}
			
			if (!sem.equalsIgnoreCase("sem1")) {
				if(commonObj.is9thEvaluation(std, academic)) {
					tableHeader.addCell(cell319);
					tableHeader.addCell(cell323);
					tableHeader.addCell(cell320);
					tableHeader.addCell(cell321);
					tableHeader.addCell(cell324);
					tableHeader.addCell(cell349);
					tableHeader.addCell(cell322);
					tableHeader.addCell(cell303);
				}
				else {
					tableHeader.addCell(cell319);
					tableHeader.addCell(cell320);
					tableHeader.addCell(cell321);
					tableHeader.addCell(cell322);
					tableHeader.addCell(cell323);
					if(stdInt < 11){
						tableHeader.addCell(cell324);
					}
					tableHeader.addCell(cell303);
				}
			}
			table2.addCell(cell303a);
			table2.addCell(cell303b);
			table2.addCell(cell303C);
			if (!sem.equalsIgnoreCase("sem2")) {
				if(commonObj.is9thEvaluation(std, academic)) {
					table2.addCell(cell304);
					table2.addCell(cell325);
					table2.addCell(cell305);
					table2.addCell(cell310);
					table2.addCell(cell326);
					table2.addCell(cell347);
					table2.addCell(cell311);
					table2.addCell(cell326a);
				}
				else {
					table2.addCell(cell304);
					table2.addCell(cell305);
					table2.addCell(cell310);
					table2.addCell(cell311);
					table2.addCell(cell325);
					if(stdInt < 11){
						table2.addCell(cell326);
					}
					table2.addCell(cell326a);
				}
			}
			if (!sem.equalsIgnoreCase("sem1")) {
				if(commonObj.is9thEvaluation(std, academic)) {
					table2.addCell(cell326b);
					table2.addCell(cell326f);
					table2.addCell(cell326c);
					table2.addCell(cell326d);
					table2.addCell(cell326g);
					table2.addCell(cell351);
					table2.addCell(cell326e);
					table2.addCell(cell326h);
				}
				else {
					table2.addCell(cell326b);
					table2.addCell(cell326c);
					table2.addCell(cell326d);
					table2.addCell(cell326e);
					table2.addCell(cell326f);
					if(stdInt < 11){
						table2.addCell(cell326g);
					}
					table2.addCell(cell326h);
				}
			}
			if(sem.equalsIgnoreCase("Final")) {
				table2.addCell(cellOutOfTotal);
				table2.addCell(cellOutOfAvg);
				if(marks_grade.equalsIgnoreCase("Grade")) {
					table2.addCell(cellGradeOutOf);
				}
			}
			tableHeader.setWidths(columnWidths);
			
			table2.setWidthPercentage(100);
			table2.setWidths(columnWidths);
			
			int count = 0, rowCount = 0;
			String optionalSubject = "", grNo = "";
			String[] optionList;
			boolean isOptional = false;
			Set set = marksSemDataMap.entrySet();
			Iterator m = set.iterator();
			while (m.hasNext()) {
				subTotalA = 0;
				subTotalB = 0;
				subTotal = 0;
				subTotal_AB = 0;
				isOptional = false;

				Map.Entry me = (Map.Entry) m.next();
				LinkedHashMap grMap = new LinkedHashMap();
				grMap = (LinkedHashMap) me.getValue();
				grNo = grMap.get("grNo").toString();
				optionalSubject = studentOptSubAllotMap.get(grNo) == null ? "" :studentOptSubAllotMap.get(grNo).get("optionalSubject");
				optionList = optionalSubject.split("\\|");
				if(optional.equalsIgnoreCase("YES") && optionalSubject.trim().length() > 0){
					for(int i = 0; i < optionList.length; i++){
						if(optionList[i].equalsIgnoreCase(subject+"_YES")){
							isOptional = true;
						}
					}
					if(!isOptional){
						continue;
					}
				}
				rowCount++;
				lastPage = true;
				PdfPCell cell327 = new PdfPCell(
						new Paragraph(grMap.get("rollNo").toString(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell327.setColspan(1);
				cell327.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell327.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell327);

				PdfPCell cell328 = new PdfPCell(
						new Paragraph(grNo, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell328.setColspan(1);
				cell328.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell328.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell328);

				name = grMap.get("name").toString();
				if(name.length() > 33){
					addRow = addRow + 1;
					isDoubleLine = true;
				}
				PdfPCell cell329 = new PdfPCell(
						new Paragraph(name, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell329.setColspan(1);
				cell329.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell329.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell329);

				if (!sem.equalsIgnoreCase("sem2") && !subMaxMarks.get("sem1_obt" + ctStr).equalsIgnoreCase("0")
						&& !subMaxMarks.get("sem1_obt" + ctStr).equalsIgnoreCase("0.0")) {
					if (commonObj.validateNumber(grMap.get(subject + "_FOBT").toString())) {
						subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_FOBT").toString())) + "";
						subTotalA = Math.round(Double.parseDouble(subMarks));
						fontSize = 10;
					} else {
						subMarks = grMap.get(subject + "_FOBT").toString();
						fontSize = 9;
					}
				} else {
					subMarks = "-";
				}
				PdfPCell cell330 = new PdfPCell(
						new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell330.setColspan(1);
				cell330.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell330.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell cell331 = null;
				PdfPCell cell332 = null;
				if(commonObj.is9thEvaluation(std, academic)){
					if (!sem.equalsIgnoreCase("sem2") && !subMaxMarks.get("sem1_listen" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem1_listen" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_FLIS").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_FLIS").toString())) + "";
							subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_FLIS").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}

					cell331 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell331.setColspan(1);
					cell331.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell331.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
					if (!sem.equalsIgnoreCase("sem2") && !subMaxMarks.get("sem1_speak" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem1_speak" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_FSPE").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_FSPE").toString())) + "";
							subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_FSPE").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}
					
					cell332 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell332.setColspan(1);
					cell332.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell332.setVerticalAlignment(Element.ALIGN_MIDDLE);
				}
				else if(stdInt < 11){
					if (!sem.equalsIgnoreCase("sem2") && !subMaxMarks.get("sem1_pres" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem1_pres" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_FPRE").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_FPRE").toString())) + "";
							subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_FPRE").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}

					cell331 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell331.setColspan(1);
					cell331.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell331.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
					if (!sem.equalsIgnoreCase("sem2") && !subMaxMarks.get("sem1_mcap" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem1_mcap" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_FMCA").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_FMCA").toString())) + "";
							subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_FMCA").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}
					
					cell332 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell332.setColspan(1);
					cell332.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell332.setVerticalAlignment(Element.ALIGN_MIDDLE);
				}
				else {
					if (!sem.equalsIgnoreCase("sem2") && !subMaxMarks.get("sem1_oral" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem1_oral" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_FORA").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_FORA").toString())) + "";
							subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_FORA").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}

					cell331 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell331.setColspan(1);
					cell331.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell331.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
					if (!sem.equalsIgnoreCase("sem2") && !subMaxMarks.get("sem1_assign" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem1_assign" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_FASS").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_FASS").toString())) + "";
							subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_FASS").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}
					
					cell332 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell332.setColspan(1);
					cell332.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell332.setVerticalAlignment(Element.ALIGN_MIDDLE);
				}
				
				if (!sem.equalsIgnoreCase("sem2") && !subMaxMarks.get("sem1_write" + ctStr).equalsIgnoreCase("0")
						&& !subMaxMarks.get("sem1_write" + ctStr).equalsIgnoreCase("0.0")) {
					if (commonObj.validateNumber(grMap.get(subject + "_FWRI").toString())) {
						subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_FWRI").toString())) + "";
						subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
						fontSize = 10;
					} else {
						subMarks = grMap.get(subject + "_FWRI").toString();
						fontSize = 9;
					}
				} else {
					subMarks = "-";
				}
				PdfPCell cell333 = new PdfPCell(
						new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell333.setColspan(1);
				cell333.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell333.setVerticalAlignment(Element.ALIGN_MIDDLE);

				if (!sem.equalsIgnoreCase("sem2") && !subMaxMarks.get("sem1_pract" + ctStr).equalsIgnoreCase("0")
						&& !subMaxMarks.get("sem1_pract" + ctStr).equalsIgnoreCase("0.0")) {
					if (commonObj.validateNumber(grMap.get(subject + "_FPRA").toString())) {
						subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_FPRA").toString())) + "";
						subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
						fontSize = 10;
					} else {
						subMarks = grMap.get(subject + "_FPRA").toString();
						fontSize = 9;
					}
				} else {
					subMarks = "-";
				}
				PdfPCell cell334 = new PdfPCell(
						new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell334.setColspan(1);
				cell334.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell334.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell cell335 = null;
				if(commonObj.is9thEvaluation(std, academic)){
					if (!sem.equalsIgnoreCase("sem2") && !subMaxMarks.get("sem1_assign" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem1_assign" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_FASS").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_FASS").toString())) + "";
							subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_FASS").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}
					cell335 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell335.setColspan(1);
					cell335.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell335.setVerticalAlignment(Element.ALIGN_MIDDLE);
				}
				else if(stdInt < 11){
					if (!sem.equalsIgnoreCase("sem2") && !subMaxMarks.get("sem1_activity" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem1_activity" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_FACT").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_FACT").toString())) + "";
							subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_FACT").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}
					cell335 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell335.setColspan(1);
					cell335.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell335.setVerticalAlignment(Element.ALIGN_MIDDLE);
				}

				PdfPCell cell348 = null;
				if(commonObj.is9thEvaluation(std, academic)){
					if (!sem.equalsIgnoreCase("sem2") && !subMaxMarks.get("sem1_project" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem1_project" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_FPRO").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_FPRO").toString())) + "";
							subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_FPRO").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}
					cell348 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell348.setColspan(1);
					cell348.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell348.setVerticalAlignment(Element.ALIGN_MIDDLE);
				}
				
				if(leftDataMap != null && leftDataMap.get(grNo) != null){
					dispTotalA = "-";
				}
				else{
					dispTotalA = Math.round(subTotalA)+"";
				}
				PdfPCell cell336 = new PdfPCell(
						new Paragraph(dispTotalA, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell336.setColspan(1);
				cell336.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell336.setVerticalAlignment(Element.ALIGN_MIDDLE);

				if (!sem.equalsIgnoreCase("sem1") && !subMaxMarks.get("sem2_obt" + ctStr).equalsIgnoreCase("0")
						&& !subMaxMarks.get("sem2_obt" + ctStr).equalsIgnoreCase("0.0")) {
					if (commonObj.validateNumber(grMap.get(subject + "_SOBT").toString())) {
						subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_SOBT").toString())) + "";
						subTotalB = Math.round(Double.parseDouble(subMarks));
						fontSize = 10;
					} else {
						subMarks = grMap.get(subject + "_SOBT").toString();
						fontSize = 9;
					}
				} else {
					subMarks = "-";
				}
				PdfPCell cell337 = new PdfPCell(
						new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell337.setColspan(1);
				cell337.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell337.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell cell338 = null;
				PdfPCell cell339 = null;
				if(commonObj.is9thEvaluation(std, academic)){
					if (!sem.equalsIgnoreCase("sem1") && !subMaxMarks.get("sem2_listen" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem2_listen" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_SLIS").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_SLIS").toString())) + "";
							subTotalB = subTotalB + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_SLIS").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}
					
					cell338 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell338.setColspan(1);
					cell338.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell338.setVerticalAlignment(Element.ALIGN_MIDDLE);

					if (!sem.equalsIgnoreCase("sem1") && !subMaxMarks.get("sem2_speak" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem2_speak" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_SSPE").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_SSPE").toString())) + "";
							subTotalB = subTotalB + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_SSPE").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}
					
					cell339 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell339.setColspan(1);
					cell339.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell339.setVerticalAlignment(Element.ALIGN_MIDDLE);
				}
				else if(stdInt < 11){
					if (!sem.equalsIgnoreCase("sem1") && !subMaxMarks.get("sem2_pres" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem2_pres" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_SPRE").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_SPRE").toString())) + "";
							subTotalB = subTotalB + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_SPRE").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}
					
					cell338 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell338.setColspan(1);
					cell338.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell338.setVerticalAlignment(Element.ALIGN_MIDDLE);

					if (!sem.equalsIgnoreCase("sem1") && !subMaxMarks.get("sem2_mcap" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem2_mcap" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_SMCA").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_SMCA").toString())) + "";
							subTotalB = subTotalB + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_SMCA").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}
					
					cell339 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell339.setColspan(1);
					cell339.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell339.setVerticalAlignment(Element.ALIGN_MIDDLE);
				}
				else{
					if (!sem.equalsIgnoreCase("sem1") && !subMaxMarks.get("sem2_oral" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem2_oral" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_SORA").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_SORA").toString())) + "";
							subTotalB = subTotalB + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_SORA").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}

					cell338 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell338.setColspan(1);
					cell338.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell338.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
					if (!sem.equalsIgnoreCase("sem1") && !subMaxMarks.get("sem2_assign" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem2_assign" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_SASS").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_SASS").toString())) + "";
							subTotalB = subTotalB + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_SASS").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}
					
					cell339 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell339.setColspan(1);
					cell339.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell339.setVerticalAlignment(Element.ALIGN_MIDDLE);
				}
				
				if (!sem.equalsIgnoreCase("sem1") && !subMaxMarks.get("sem2_write" + ctStr).equalsIgnoreCase("0")
						&& !subMaxMarks.get("sem2_write" + ctStr).equalsIgnoreCase("0.0")) {
					if (commonObj.validateNumber(grMap.get(subject + "_SWRI").toString())) {
						subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_SWRI").toString())) + "";
						subTotalB = subTotalB + Math.round(Double.parseDouble(subMarks));
						fontSize = 10;
					} else {
						subMarks = grMap.get(subject + "_SWRI").toString();
						fontSize = 9;
					}
				} else {
					subMarks = "-";
				}
				
				PdfPCell cell340 = new PdfPCell(
						new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell340.setColspan(1);
				cell340.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell340.setVerticalAlignment(Element.ALIGN_MIDDLE);

				if (!sem.equalsIgnoreCase("sem1") && !subMaxMarks.get("sem2_pract" + ctStr).equalsIgnoreCase("0")
						&& !subMaxMarks.get("sem2_pract" + ctStr).equalsIgnoreCase("0.0")) {
					if (commonObj.validateNumber(grMap.get(subject + "_SPRA").toString())) {
						subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_SPRA").toString())) + "";
						subTotalB = subTotalB + Math.round(Double.parseDouble(subMarks));
						fontSize = 10;
					} else {
						subMarks = grMap.get(subject + "_SPRA").toString();
						fontSize = 9;
					}
				} else {
					subMarks = "-";
				}
				PdfPCell cell341 = new PdfPCell(
						new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell341.setColspan(1);
				cell341.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell341.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell cell342 = null;
				if(commonObj.is9thEvaluation(std, academic)){
					if (!sem.equalsIgnoreCase("sem1") && !subMaxMarks.get("sem2_assign" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem2_assign" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_SASS").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_SASS").toString())) + "";
							subTotalB = subTotalB + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_SASS").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}
					cell342 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell342.setColspan(1);
					cell342.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell342.setVerticalAlignment(Element.ALIGN_MIDDLE);
				}
				else if(stdInt < 11){
					if (!sem.equalsIgnoreCase("sem1") && !subMaxMarks.get("sem2_activity" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem2_activity" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_SACT").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_SACT").toString())) + "";
							subTotalB = subTotalB + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_SACT").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}
					cell342 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell342.setColspan(1);
					cell342.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell342.setVerticalAlignment(Element.ALIGN_MIDDLE);
				}

				PdfPCell cell350 = null;
				if(commonObj.is9thEvaluation(std, academic)){
					if (!sem.equalsIgnoreCase("sem1") && !subMaxMarks.get("sem2_project" + ctStr).equalsIgnoreCase("0")
							&& !subMaxMarks.get("sem2_project" + ctStr).equalsIgnoreCase("0.0")) {
						if (commonObj.validateNumber(grMap.get(subject + "_SPRO").toString())) {
							subMarks = Math.round(Double.parseDouble(grMap.get(subject + "_SPRO").toString())) + "";
							subTotalB = subTotalB + Math.round(Double.parseDouble(subMarks));
							fontSize = 10;
						} else {
							subMarks = grMap.get(subject + "_SPRO").toString();
							fontSize = 9;
						}
					} else {
						subMarks = "-";
					}
					cell350 = new PdfPCell(
							new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell350.setColspan(1);
					cell350.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell350.setVerticalAlignment(Element.ALIGN_MIDDLE);
				}
				
				if(leftDataMap != null && leftDataMap.get(grNo) != null){
					dispTotalB = "-";
				}
				else{
					dispTotalB = Math.round(subTotalB)+"";
				}
				PdfPCell cell343 = new PdfPCell(
						new Paragraph(dispTotalB, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell343.setColspan(1);
				cell343.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell343.setVerticalAlignment(Element.ALIGN_MIDDLE);

				if (!sem.equalsIgnoreCase("sem2")) {
					if(commonObj.is9thEvaluation(std, academic)) {
						table2.addCell(cell330);
						table2.addCell(cell334);
						table2.addCell(cell331);
						table2.addCell(cell332);
						table2.addCell(cell335);
						table2.addCell(cell348);
						table2.addCell(cell333);
						table2.addCell(cell336);
						subTotal_AB = subTotalA;
					}
					else {
						table2.addCell(cell330);
						table2.addCell(cell331);
						table2.addCell(cell332);
						table2.addCell(cell333);
						table2.addCell(cell334);
						if(stdInt < 11){
							table2.addCell(cell335);
						}
						table2.addCell(cell336);
						subTotal_AB = subTotalA;
					}
				}
				if (!sem.equalsIgnoreCase("sem1")) {
					if(commonObj.is9thEvaluation(std, academic)) {
						table2.addCell(cell337);
						table2.addCell(cell341);
						table2.addCell(cell338);
						table2.addCell(cell339);
						table2.addCell(cell342);
						table2.addCell(cell350);
						table2.addCell(cell340);
						table2.addCell(cell343);
						subTotal_AB = subTotalB;
					}
					else {
						table2.addCell(cell337);
						table2.addCell(cell338);
						table2.addCell(cell339);
						table2.addCell(cell340);
						table2.addCell(cell341);
						if(stdInt < 11){
							table2.addCell(cell342);
						}
						table2.addCell(cell343);
						subTotal_AB = subTotalB;
					}
				}
				if (sem.equalsIgnoreCase("final")) {
					subTotal_AB = subTotalA + subTotalB;
				}

				if(leftDataMap != null && leftDataMap.get(grNo) != null){
					dispTotalAB = "-";
				}
				else{
					dispTotalAB = Math.round(subTotal_AB)+"";
				}
				PdfPCell cell344 = new PdfPCell(
						new Paragraph(dispTotalAB, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell344.setColspan(1);
				cell344.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell344.setVerticalAlignment(Element.ALIGN_MIDDLE);

				if (std.equalsIgnoreCase("IX") || std.equalsIgnoreCase("X") || std.equalsIgnoreCase("XI")
						|| std.equalsIgnoreCase("XII") || exam.equalsIgnoreCase("Final")) {
					divisor = (totalA + totalB) / 100;

//					sem1Total = grMap.get(subject + "_SEM1").toString();
					sem1Total = grMap.get(subject + "_SEM1").toString().equalsIgnoreCase("-") ? "0" : grMap.get(subject + "_SEM1").toString();
					if(marks_grade.equalsIgnoreCase("Marks")){
						if(!sem1Total.contains("(")){
							sem1Total = "-";
						}
					}
					if (!sem1Total.equalsIgnoreCase("") && !sem1Total.equalsIgnoreCase("-") && sem1Total.contains("+")) {
						sem1Total = sem1Total.substring(sem1Total.indexOf("+") + 1, sem1Total.indexOf("("));
						average = grMap.get(subject + "_SEM1").toString();
						average = average.substring(0, average.indexOf("("));
					}
//					sem2Total = grMap.get(subject + "_SEM2").toString();
					sem2Total = grMap.get(subject + "_SEM2").toString().equalsIgnoreCase("-") ? "0" : grMap.get(subject + "_SEM2").toString();
					if(marks_grade.equalsIgnoreCase("Marks")){
						if(!sem2Total.contains("(")){
							sem2Total = "-";
						}
					}
					if (!sem2Total.equalsIgnoreCase("") && !sem2Total.equalsIgnoreCase("-") && sem2Total.contains("+")) {
						sem2Total = sem2Total.substring(sem2Total.indexOf("+") + 1, sem2Total.indexOf("("));
						average = grMap.get(subject + "_SEM2").toString();
						average = average.substring(0, average.indexOf("("));
					}
					
					if(exam.equalsIgnoreCase("Final")){
						average = grMap.get(subject + "_FINAL").toString();
						
						if(!average.equalsIgnoreCase("-") && !average.equalsIgnoreCase("NA")){
							average = average.substring(0, average.indexOf("("));
							if(grMap.get(subject + "_FINAL").toString().contains("MG")){
								average = average + " MG";
								if(!isDoubleLine){
									addRow = addRow + 1;
								}
							}
						}
//						else {
//							average = Math.round(subTotal_AB/divisor)+"";
//						}
					}

					grade = grMap.get(subject + "_FINAL").toString();
					
					if(exam.equalsIgnoreCase("Final") && marks_grade.equalsIgnoreCase("GRADE") && !grade.equalsIgnoreCase("-")){
						if((subTotalA == 0 && subTotalB != 0) || (subTotalA != 0 && subTotalB == 0)) {
							average = String.format("%.0f", ((Double.parseDouble(sem1Total) + Double.parseDouble(sem2Total))));
						}
						else {
							average = String.format("%.0f", ((Double.parseDouble(sem1Total) + Double.parseDouble(sem2Total)) / 
									Double.parseDouble(grade.substring(grade.indexOf("~")+1, grade.indexOf("#")))));
						}
					}
					
					if (grade.contains("(")) {
						grade = grade.substring(0, grade.indexOf("("));
					}
				}

				if(leftDataMap != null && leftDataMap.get(grNo) != null){
					average = "-";
				}
				PdfPCell cell346;
				if (!average.contains("+")) {
					cell346 = new PdfPCell(new Paragraph(average, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				} else {
					cell346 = new PdfPCell(new Paragraph(average, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					if(!isDoubleLine){
						addRow = addRow + 1;
					}
				}
				cell346.setColspan(1);
				cell346.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell346.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell cellGrade;
				if (!grade.contains("+")) {
					cellGrade = new PdfPCell(new Paragraph(grade, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				} else {
					cellGrade = new PdfPCell(new Paragraph(grade, FontFactory.getFont(FontFactory.TIMES_ROMAN, 9)));
				}
				cellGrade.setColspan(1);
				cellGrade.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellGrade.setVerticalAlignment(Element.ALIGN_MIDDLE);

				if (sem.equalsIgnoreCase("Final")) {
					table2.addCell(cell344);
					table2.addCell(cell346);
					if (marks_grade.equalsIgnoreCase("Grade")) {
						table2.addCell(cellGrade);
					}
				}
				
				average = "0";
				count++;
				isDoubleLine = false;
				
				if ((count+addRow)%displayRows == 0) {
					document.add(paragraph);
					document.add(paragraph1);
					document.add(tableHeader);
					document.add(table2);
					pageCount++;
					document.newPage();
					addRow = 0;
					table2.flushContent();
					table2.setWidthPercentage(100);
					table2.setWidths(columnWidths);
					count = 0;
					lastPage = false;
				}
				else if(pageCount != 1 && count != displayRows && (count+addRow)%displayRows==0){
					document.add(paragraph);
					document.add(paragraph1);
					document.add(tableHeader);
					document.add(table2);
					pageCount++;
					document.newPage();
					addRow = 0;
					table2.flushContent();
					table2.setWidthPercentage(100);
					table2.setWidths(columnWidths);
					lastPage = false;
				}
				else if(marksSemDataMap.size() == rowCount){
					document.add(paragraph);
					document.add(paragraph1);
					document.add(tableHeader);
					document.add(table2);
					addRow = 0;
					lastPage = false;
				}
			}

			if(lastPage){
				document.add(paragraph);
				document.add(paragraph1);
				document.add(tableHeader);
				document.add(table2);
			}
			
			document.close();
			
			if(rowCount == 0){
				JOptionPane.showMessageDialog(null, "No students found for subject "+subject);
				fileOpenFlag = false;
			}
			else{
				JOptionPane.showMessageDialog(null, "Report generated successfully.");
			}
			
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