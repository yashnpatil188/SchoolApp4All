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

public class MarksheetSubjectwise_PDF {

	static String fileName = "";
	static String fileAddress = "";
	static String path = "";
	static boolean fileOpenFlag = false;
	Common commonObj = new Common();
	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	static Logger logger = Logger.getLogger(MarksheetSubjectwise_PDF.class.getName());

	public MarksheetSubjectwise_PDF(SessionData sessionData, String exam, String subject, String std, String div,
			String academic, LinkedHashMap<String, LinkedHashMap<String, String>> marksSemDataMap,
			LinkedHashMap<String, LinkedHashMap<String, String>> studentOptSubAllotMap,
			LinkedHashMap<String, LinkedHashMap<String, String>> maxSubMarks,
			LinkedHashMap<String,String> leftDataMap) throws DocumentException {

		System.gc();
		fileOpenFlag = true;
		int displayRows= 45, fontSize = 9, fontSizeRTE = 7;
		String examHeader = "", sem = "", semInitial = "", subMarks = "", noOfRecords = "45", divisorStr = "", name = "";
		String dispTotalA = "", dispTotalB = "", dispTotalAB = "", ctStr = "", gradeMarks = "";
		double totalA = 0, totalB = 0, total = 0,subTotalA = 0, subTotalB = 0, subTotal = 0, pageNumber = 0, addRow = 0, 
				pageCount = 0, academicStart = 0, stdInt = 0;
		boolean isDoubleLine = false;
		LinkedHashMap<String, String> subMaxMarks = new LinkedHashMap<String, String>();

		try {
			String bonafide_header = bundle.getString("BONAFIDE_HEADER_" + sessionData.getAppType());
			String bonafide_header_0 = bundle.getString("BONAFIDE_HEADER_0_" + sessionData.getAppType());
			if(!bonafide_header_0.trim().equalsIgnoreCase("")){
				bonafide_header_0 = bonafide_header_0 + " \n";
				displayRows = 45;
				noOfRecords = "45";
			}
			
			stdInt = commonObj.RomanToInteger(std);
			if(!academic.equalsIgnoreCase("")) {
				academicStart = Integer.parseInt(academic.substring(0, 4));
			}
			if (academicStart >= 2019) {//changed since 9th evaluation was implemented
				ctStr = "_ct";
			}
			subMaxMarks = maxSubMarks.get(subject);

			if (exam.equalsIgnoreCase("Semester 1")) {
				sem = "sem1";
				semInitial = "F";
				examHeader = "FIRST SEMESTER";
			} else if (exam.equalsIgnoreCase("Semester 2")) {
				sem = "sem2";
				semInitial = "S";
				examHeader = "SECOND SEMESTER";
			}
			
			// for column width size
			float[] columnWidths = new float[] { 1.2f, 2.5f, 12.0f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.3f, 1.1f, 1.1f, 1.1f,
				1.3f, 1.3f, 1.2f };
						
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
			
			PdfPTable tableHeader = new PdfPTable(18);
			tableHeader.setWidthPercentage(100);
			
			PdfPCell cell298 = new PdfPCell(new Paragraph(examHeader + " EXAMINATION " + academic,
					FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			cell298.setColspan(18);
			cell298.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell298.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell298);

			PdfPCell cell299 = new PdfPCell(
					new Paragraph("MARKSHEET", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			cell299.setColspan(18);
			cell299.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell299.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell299);

			PdfPCell cell300 = new PdfPCell(new Paragraph("SUBJECT: " + subject.replace("_", " ") + "			STD.: " + std + "-" + div,
					FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			cell300.setColspan(18);
			cell300.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell300.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell300);

			PdfPCell cell301 = new PdfPCell(new Paragraph("Roll No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			cell301.setColspan(1);
			cell301.setRowspan(2);
			cell301.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell301.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell301.setRotation(90);
			tableHeader.addCell(cell301);

			PdfPCell cell302 = new PdfPCell(new Paragraph("GR No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			cell302.setColspan(1);
			cell302.setRowspan(2);
			cell302.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell302.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell302.setRotation(90);
			tableHeader.addCell(cell302);

			PdfPCell cell302a = new PdfPCell(new Paragraph("Name", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			cell302a.setColspan(1);
			cell302a.setRowspan(2);
			cell302a.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell302a.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell302a.setRotation(90);
			tableHeader.addCell(cell302a);

			PdfPCell cell306 = new PdfPCell(
					new Paragraph("(A) Formative Evaluation", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell306.setColspan(9);
			cell306.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell306.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell306);

			PdfPCell cell307 = new PdfPCell(
					new Paragraph("(B) Summative Evaluation", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell307.setColspan(4);
			cell307.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell307.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell307);

			PdfPCell cell308 = new PdfPCell(new Paragraph("Total (A+B)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell308.setColspan(1);
			cell308.setRowspan(2);
			cell308.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell308.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell308.setRotation(90);
			tableHeader.addCell(cell308);

			PdfPCell cell309 = new PdfPCell(new Paragraph("Grade", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell309.setColspan(1);
			cell309.setRowspan(3);
			cell309.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell309.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell309.setRotation(90);
			tableHeader.addCell(cell309);

			PdfPCell cell312 = new PdfPCell(new Paragraph("DOB", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell312.setColspan(1);
			cell312.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell312.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell312.setRotation(90);
			tableHeader.addCell(cell312);

			PdfPCell cell313 = new PdfPCell(new Paragraph("Oral", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell313.setColspan(1);
			cell313.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell313.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell313.setRotation(90);
			tableHeader.addCell(cell313);

			PdfPCell cell314 = new PdfPCell(
					new Paragraph("Practical", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell314.setColspan(1);
			cell314.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell314.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell314.setRotation(90);
			tableHeader.addCell(cell314);

			PdfPCell cell315 = new PdfPCell(
					new Paragraph("Activity", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell315.setColspan(1);
			cell315.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell315.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell315.setRotation(90);
			tableHeader.addCell(cell315);

			PdfPCell cell316 = new PdfPCell(new Paragraph("Project", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell316.setColspan(1);
			cell316.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell316.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell316.setRotation(90);
			tableHeader.addCell(cell316);

			PdfPCell cell317 = new PdfPCell(new Paragraph("Test", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell317.setColspan(1);
			cell317.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell317.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell317.setRotation(90);
			tableHeader.addCell(cell317);

			PdfPCell cell318 = new PdfPCell(new Paragraph("Assign", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell318.setColspan(1);
			cell318.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell318.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell318.setRotation(90);
			tableHeader.addCell(cell318);

			PdfPCell cell319 = new PdfPCell(new Paragraph("Others", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell319.setColspan(1);
			cell319.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell319.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell319.setRotation(90);
			tableHeader.addCell(cell319);

			PdfPCell cell320 = new PdfPCell(new Paragraph("Total (A) ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell320.setColspan(1);
			cell320.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell320.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell320.setRotation(90);
			tableHeader.addCell(cell320);

			PdfPCell cell321 = new PdfPCell(new Paragraph("Oral", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell321.setColspan(1);
			cell321.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell321.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell321.setRotation(90);
			tableHeader.addCell(cell321);

			PdfPCell cell322 = new PdfPCell(
					new Paragraph("Practical", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell322.setColspan(1);
			cell322.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell322.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell322.setRotation(90);
			tableHeader.addCell(cell322);

			PdfPCell cell323 = new PdfPCell(new Paragraph("Written", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell323.setColspan(1);
			cell323.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell323.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell323.setRotation(90);
			tableHeader.addCell(cell323);

			PdfPCell cell324 = new PdfPCell(
					new Paragraph("Total (B)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell324.setColspan(1);
			cell324.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell324.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell324.setRotation(90);
			tableHeader.addCell(cell324);

			PdfPCell cell303 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			cell303.setColspan(1);
			cell303.setRowspan(1);
			cell303.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell303.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell303);

			PdfPCell cell303a = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			cell303a.setColspan(1);
			cell303a.setRowspan(1);
			cell303a.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell303a.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell303a);

			PdfPCell cell303b = new PdfPCell(new Paragraph("OUT OF", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell303b.setColspan(1);
			cell303b.setRowspan(1);
			cell303b.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell303b.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell303b);

			String maxMarks = "0";
			if (total == 0)
				totalA = Double.parseDouble(subMaxMarks.get(sem + "_" + "dobs" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get(sem + "_" + "dobs" + ctStr))))+"";
			if(maxMarks.equalsIgnoreCase("0")){
				maxMarks = "-";
			}
			PdfPCell cell304 = new PdfPCell(new Paragraph(maxMarks,
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell304.setColspan(1);
			cell304.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell304.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell304);

			if (total == 0)
				totalA = totalA + Double.parseDouble(subMaxMarks.get(sem + "_" + "oral" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get(sem + "_" + "oral" + ctStr))))+"";
			if(maxMarks.equalsIgnoreCase("0")){
				maxMarks = "-";
			}
			PdfPCell cell305 = new PdfPCell(new Paragraph(maxMarks,
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell305.setColspan(1);
			cell305.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell305.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell305);
			if (total == 0)
				totalA = totalA + Double.parseDouble(subMaxMarks.get(sem + "_" + "pract" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get(sem + "_" + "pract" + ctStr))))+"";
			if(maxMarks.equalsIgnoreCase("0")){
				maxMarks = "-";
			}
			PdfPCell cell310 = new PdfPCell(new Paragraph(maxMarks,
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell310.setColspan(1);
			cell310.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell310.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell310);

			if (total == 0)
				totalA = totalA + Double.parseDouble(subMaxMarks.get(sem + "_" + "activity" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get(sem + "_" + "activity" + ctStr))))+"";
			if(maxMarks.equalsIgnoreCase("0")){
				maxMarks = "-";
			}
			PdfPCell cell311 = new PdfPCell(new Paragraph(maxMarks,
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell311.setColspan(1);
			cell311.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell311.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell311);

			if (total == 0)
				totalA = totalA + Double.parseDouble(subMaxMarks.get(sem + "_" + "project" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get(sem + "_" + "project" + ctStr))))+"";
			if(maxMarks.equalsIgnoreCase("0")){
				maxMarks = "-";
			}
			PdfPCell cell325 = new PdfPCell(new Paragraph(maxMarks,
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell325.setColspan(1);
			cell325.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell325.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell325);
			if (total == 0)
				totalA = totalA + Double.parseDouble(subMaxMarks.get(sem + "_" + "obt" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get(sem + "_" + "obt" + ctStr))))+"";
			if(maxMarks.equalsIgnoreCase("0")){
				maxMarks = "-";
			}
			PdfPCell cell326 = new PdfPCell(new Paragraph(maxMarks,
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell326.setColspan(1);
			cell326.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell326.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell326);

			if (total == 0)
				totalA = totalA + Double.parseDouble(subMaxMarks.get(sem + "_" + "assign" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get(sem + "_" + "assign" + ctStr))))+"";
			if(maxMarks.equalsIgnoreCase("0")){
				maxMarks = "-";
			}
			PdfPCell cell326a = new PdfPCell(new Paragraph(maxMarks,
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell326a.setColspan(1);
			cell326a.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell326a.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell326a);

			if (total == 0)
				totalA = totalA + Double.parseDouble(subMaxMarks.get(sem + "_" + "other" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get(sem + "_" + "other" + ctStr))))+"";
			if(maxMarks.equalsIgnoreCase("0")){
				maxMarks = "-";
			}
			PdfPCell cell326b = new PdfPCell(new Paragraph(maxMarks,
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell326b.setColspan(1);
			cell326b.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell326b.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell326b);

			PdfPCell cell326c = new PdfPCell(
					new Paragraph(Math.round(totalA) + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell326c.setColspan(1);
			cell326c.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell326c.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell326c);

			if (total == 0)
				totalB = Double.parseDouble(subMaxMarks.get(sem + "_" + "oral1" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get(sem + "_" + "oral1" + ctStr))))+"";
			if(maxMarks.equalsIgnoreCase("0")){
				maxMarks = "-";
			}
			PdfPCell cell326d = new PdfPCell(new Paragraph(maxMarks,
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell326d.setColspan(1);
			cell326d.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell326d.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell326d);

			if (total == 0)
				totalB = totalB + Double.parseDouble(subMaxMarks.get(sem + "_" + "pract1" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get(sem + "_" + "pract1" + ctStr))))+"";
			if(maxMarks.equalsIgnoreCase("0")){
				maxMarks = "-";
			}
			PdfPCell cell326e = new PdfPCell(new Paragraph(maxMarks,
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell326e.setColspan(1);
			cell326e.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell326e.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell326e);

			if (total == 0)
				totalB = totalB + Double.parseDouble(subMaxMarks.get(sem + "_" + "write1" + ctStr));
			maxMarks = (Math.round(Double.parseDouble(subMaxMarks.get(sem + "_" + "write1" + ctStr))))+"";
			if(maxMarks.equalsIgnoreCase("0")){
				maxMarks = "-";
			}
			PdfPCell cell326f = new PdfPCell(new Paragraph(maxMarks,
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell326f.setColspan(1);
			cell326f.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell326f.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell326f);

			PdfPCell cell326g = new PdfPCell(
					new Paragraph(Math.round(totalB) + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
			cell326g.setColspan(1);
			cell326g.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell326g.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell326g);

			if (total == 0)
				total = totalA + totalB;
			PdfPCell cell326h = new PdfPCell(
					new Paragraph(Math.round(total) + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
			cell326h.setColspan(1);
			cell326h.setRowspan(1);
			cell326h.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell326h.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableHeader.addCell(cell326h);
			tableHeader.setWidths(columnWidths);
			
			PdfPTable table2 = new PdfPTable(18);
			table2.setWidths(columnWidths);
			table2.setWidthPercentage(100);
//			table2.setSpacingBefore(-10.0f);
			
			int count = 0, rowCount = 0;
			Set set = marksSemDataMap.entrySet();
			Iterator m = set.iterator();
			while (m.hasNext()) {
				rowCount++;
				subTotalA = 0;
				subTotalB = 0;
				subTotal = 0;
				
				Map.Entry me = (Map.Entry) m.next();
				LinkedHashMap grMap = new LinkedHashMap();
				grMap = (LinkedHashMap) me.getValue();
				
				PdfPCell cell327 = new PdfPCell(new Paragraph(grMap.get("rollNo").toString(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cell327.setColspan(1);
				cell327.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell327.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell327);

				PdfPCell cell328 = new PdfPCell(
						new Paragraph(grMap.get("grNo").toString(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
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

				if(!subMaxMarks.get(sem + "_" + "dobs" + ctStr).equalsIgnoreCase("0") && !subMaxMarks.get(sem + "_" + "dobs" + ctStr).equalsIgnoreCase("0.0")){
					if(commonObj.validateNumber(grMap.get(subject+"_"+semInitial+"DOB").toString())){
						subMarks = Math.round(Double.parseDouble(grMap.get(subject+"_"+semInitial+"DOB").toString()))+"";
						subTotalA = Math.round(Double.parseDouble(subMarks));
					}
					else{
						subMarks = grMap.get(subject+"_"+semInitial+"DOB").toString();
					}
				}
				else{
					subMarks = "-";
				}
				
				if(subMarks.contains("RTE")) {
					fontSize = fontSizeRTE;
				}
				PdfPCell cell330 = new PdfPCell(new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell330.setColspan(1);
				cell330.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell330.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell330);

				if(!subMaxMarks.get(sem + "_" + "oral" + ctStr).equalsIgnoreCase("0") && !subMaxMarks.get(sem + "_" + "oral" + ctStr).equalsIgnoreCase("0.0")){
					if(commonObj.validateNumber(grMap.get(subject+"_"+semInitial+"ORA").toString())){
						subMarks = Math.round(Double.parseDouble(grMap.get(subject+"_"+semInitial+"ORA").toString()))+"";
						subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
					}
					else{
						subMarks = grMap.get(subject+"_"+semInitial+"ORA").toString();
					}
				}
				else{
					subMarks = "-";
				}
				
				if(subMarks.contains("RTE")) {
					fontSize = fontSizeRTE;
				}
				PdfPCell cell331 = new PdfPCell(new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell331.setColspan(1);
				cell331.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell331.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell331);

				if(!subMaxMarks.get(sem + "_" + "pract" + ctStr).equalsIgnoreCase("0") && !subMaxMarks.get(sem + "_" + "pract" + ctStr).equalsIgnoreCase("0.0")){
					if(commonObj.validateNumber(grMap.get(subject+"_"+semInitial+"PRA").toString())){
						subMarks = Math.round(Double.parseDouble(grMap.get(subject+"_"+semInitial+"PRA").toString()))+"";
						subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
					}
					else{
						subMarks = grMap.get(subject+"_"+semInitial+"PRA").toString();
					}
				}
				else{
					subMarks = "-";
				}
				
				if(subMarks.contains("RTE")) {
					fontSize = fontSizeRTE;
				}
				PdfPCell cell332 = new PdfPCell(new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell332.setColspan(1);
				cell332.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell332.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell332);

				if(!subMaxMarks.get(sem + "_" + "activity" + ctStr).equalsIgnoreCase("0") && !subMaxMarks.get(sem + "_" + "activity" + ctStr).equalsIgnoreCase("0.0")){
					if(commonObj.validateNumber(grMap.get(subject+"_"+semInitial+"ACT").toString())){
						subMarks = Math.round(Double.parseDouble(grMap.get(subject+"_"+semInitial+"ACT").toString()))+"";
						subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
					}
					else{
						subMarks = grMap.get(subject+"_"+semInitial+"ACT").toString();
					}
				}
				else{
					subMarks = "-";
				}
				
				if(subMarks.contains("RTE")) {
					fontSize = fontSizeRTE;
				}
				PdfPCell cell333 = new PdfPCell(new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell333.setColspan(1);
				cell333.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell333.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell333);

				if(!subMaxMarks.get(sem + "_" + "project" + ctStr).equalsIgnoreCase("0") && !subMaxMarks.get(sem + "_" + "project" + ctStr).equalsIgnoreCase("0.0")){
					if(commonObj.validateNumber(grMap.get(subject+"_"+semInitial+"PRO").toString())){
						subMarks = Math.round(Double.parseDouble(grMap.get(subject+"_"+semInitial+"PRO").toString()))+"";
						subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
					}
					else{
						subMarks = grMap.get(subject+"_"+semInitial+"PRO").toString();
					}
				}
				else{
					subMarks = "-";
				}
				
				if(subMarks.contains("RTE")) {
					fontSize = fontSizeRTE;
				}
				PdfPCell cell334 = new PdfPCell(new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell334.setColspan(1);
				cell334.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell334.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell334);

				if(!subMaxMarks.get(sem + "_" + "obt" + ctStr).equalsIgnoreCase("0") && !subMaxMarks.get(sem + "_" + "obt" + ctStr).equalsIgnoreCase("0.0")){
					if(commonObj.validateNumber(grMap.get(subject+"_"+semInitial+"OBT").toString())){
						subMarks = Math.round(Double.parseDouble(grMap.get(subject+"_"+semInitial+"OBT").toString()))+"";
						subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
					}
					else{
						subMarks = grMap.get(subject+"_"+semInitial+"OBT").toString();
					}
				}
				else{
					subMarks = "-";
				}
				
				if(subMarks.contains("RTE")) {
					fontSize = fontSizeRTE;
				}
				PdfPCell cell335 = new PdfPCell(new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell335.setColspan(1);
				cell335.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell335.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell335);

				if(!subMaxMarks.get(sem + "_" + "assign" + ctStr).equalsIgnoreCase("0") && !subMaxMarks.get(sem + "_" + "assign" + ctStr).equalsIgnoreCase("0.0")){
					if(commonObj.validateNumber(grMap.get(subject+"_"+semInitial+"ASS").toString())){
						subMarks = Math.round(Double.parseDouble(grMap.get(subject+"_"+semInitial+"ASS").toString()))+"";
						subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
					}
					else{
						subMarks = grMap.get(subject+"_"+semInitial+"ASS").toString();
					}
				}
				else{
					subMarks = "-";
				}
				
				if(subMarks.contains("RTE")) {
					fontSize = fontSizeRTE;
				}
				PdfPCell cell336 = new PdfPCell(new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell336.setColspan(1);
				cell336.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell336.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell336);

				if(!subMaxMarks.get(sem + "_" + "other" + ctStr).equalsIgnoreCase("0") && !subMaxMarks.get(sem + "_" + "other" + ctStr).equalsIgnoreCase("0.0")){
					if(commonObj.validateNumber(grMap.get(subject+"_"+semInitial+"OTH").toString())){
						subMarks = Math.round(Double.parseDouble(grMap.get(subject+"_"+semInitial+"OTH").toString()))+"";
						subTotalA = subTotalA + Math.round(Double.parseDouble(subMarks));
					}
					else{
						subMarks = grMap.get(subject+"_"+semInitial+"OTH").toString();
					}
				}
				else{
					subMarks = "-";
				}
				
				if(subMarks.contains("RTE")) {
					fontSize = fontSizeRTE;
				}
				PdfPCell cell337 = new PdfPCell(new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell337.setColspan(1);
				cell337.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell337.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell337);

//				if(leftDataMap != null && leftDataMap.get(grMap.get("grNo").toString()) == null){
					dispTotalA = Math.round(subTotalA)+"";
//				}
//				else{
//					dispTotalA = "-";
//				}
				PdfPCell cell338 = new PdfPCell(new Paragraph(dispTotalA, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell338.setColspan(1);
				cell338.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell338.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell338);

				if(!subMaxMarks.get(sem + "_" + "oral1" + ctStr).equalsIgnoreCase("0") && !subMaxMarks.get(sem + "_" + "oral1" + ctStr).equalsIgnoreCase("0.0")){
					if(commonObj.validateNumber(grMap.get(subject+"_"+semInitial+"ORA1").toString())){
						subMarks = Math.round(Double.parseDouble(grMap.get(subject+"_"+semInitial+"ORA1").toString()))+"";
						subTotalB = Math.round(Double.parseDouble(subMarks));
					}
					else{
						subMarks = grMap.get(subject+"_"+semInitial+"ORA1").toString();
					}
				}
				else{
					subMarks = "-";
				}
				
				if(subMarks.contains("RTE")) {
					fontSize = fontSizeRTE;
				}
				PdfPCell cell339 = new PdfPCell(new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell339.setColspan(1);
				cell339.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell339.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell339);

				if(!subMaxMarks.get(sem + "_" + "pract1" + ctStr).equalsIgnoreCase("0") && !subMaxMarks.get(sem + "_" + "pract1" + ctStr).equalsIgnoreCase("0.0")){
					if(commonObj.validateNumber(grMap.get(subject+"_"+semInitial+"PRA1").toString())){
						subMarks = Math.round(Double.parseDouble(grMap.get(subject+"_"+semInitial+"PRA1").toString()))+"";
						subTotalB = subTotalB + Math.round(Double.parseDouble(subMarks));
					}
					else{
						subMarks = grMap.get(subject+"_"+semInitial+"PRA1").toString();
					}
				}
				else{
					subMarks = "-";
				}
				
				if(subMarks.contains("RTE")) {
					fontSize = fontSizeRTE;
				}
				PdfPCell cell340 = new PdfPCell(new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell340.setColspan(1);
				cell340.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell340.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell340);

				if(!subMaxMarks.get(sem + "_" + "write1" + ctStr).equalsIgnoreCase("0") && !subMaxMarks.get(sem + "_" + "write1" + ctStr).equalsIgnoreCase("0.0")){
					if(commonObj.validateNumber(grMap.get(subject+"_"+semInitial+"WRI1").toString())){
						subMarks = Math.round(Double.parseDouble(grMap.get(subject+"_"+semInitial+"WRI1").toString()))+"";
						subTotalB = subTotalB + Math.round(Double.parseDouble(subMarks));
					}
					else{
						subMarks = grMap.get(subject+"_"+semInitial+"WRI1").toString();
					}
				}
				else{
					subMarks = "-";
				}
				
				if(subMarks.contains("RTE")) {
					fontSize = fontSizeRTE;
				}
				PdfPCell cell341 = new PdfPCell(new Paragraph(subMarks, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell341.setColspan(1);
				cell341.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell341.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell341);

//				if(leftDataMap != null && leftDataMap.get(grMap.get("grNo").toString()) == null){
					dispTotalB = Math.round(subTotalB)+"";
//				}
//				else{
//					dispTotalB = "-";
//				}
				PdfPCell cell342 = new PdfPCell(new Paragraph(dispTotalB, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell342.setColspan(1);
				cell342.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell342.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell342);

				subTotal = subTotalA + subTotalB;
//				if(leftDataMap != null && leftDataMap.get(grMap.get("grNo").toString()) == null){
					dispTotalAB = Math.round(subTotal)+"";
//				}
//				else{
//					dispTotalAB = "-";
//				}
				PdfPCell cell343 = new PdfPCell(new Paragraph(dispTotalAB, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell343.setColspan(1);
				cell343.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell343.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell343);

				String grade = commonObj.getGradeFromMarks(total, subject, subTotal, std);
				if(leftDataMap != null && leftDataMap.get(grMap.get("grNo").toString()) != null){
					grade = "-";
				}
				gradeMarks = subMaxMarks.get("marks_grade");
				if(gradeMarks.equalsIgnoreCase("MARKS") && stdInt < 0) {
					grade = "-";
				}
				PdfPCell cell344 = new PdfPCell(new Paragraph(grade, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell344.setColspan(1);
				cell344.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell344.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell344);

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
				}
				else if(marksSemDataMap.size() == rowCount){
					document.add(paragraph);
					document.add(paragraph1);
					document.add(tableHeader);
					document.add(table2);
					addRow = 0;
				}
			}

//			table2.setWidths(columnWidths);
//			document.add(paragraph);
//			document.add(table2);
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