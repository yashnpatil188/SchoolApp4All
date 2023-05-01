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

public class ExamRegister_IX_PDF {

	static String fileName = "";
	static String fileAddress = "";
	static String path = "";
	static boolean fileOpenFlag = false;
	Common commonObj = new Common();
	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	static Logger logger = Logger.getLogger(ExamRegister_IX_PDF.class.getName());

	public ExamRegister_IX_PDF(SessionData sessionData, String exam, String subject, String std, String div,
			String academic, LinkedHashMap<String, LinkedHashMap<String, String>> marksSemDataMap,
			LinkedHashMap<String, LinkedHashMap<String, String>> studentOptSubAllotMap,
			LinkedHashMap<String, LinkedHashMap<String, String>> maxSubMarks, 
			LinkedHashMap<String, LinkedHashMap<String, String>> resultMap, 
			LinkedHashMap<String,String> leftDataMap, LinkedHashMap<String, LinkedHashMap<String, String>> marksSubjectcDataMap,
			LinkedHashMap<String,LinkedHashMap<String, String>> subjectMap, String reportType) throws DocumentException {

		System.gc();
		fileOpenFlag = true;
		LinkedHashMap<String,String> examTypeMap = new LinkedHashMap<String,String>();
		LinkedHashMap examTypeMap2 = new LinkedHashMap();
		LinkedHashMap<String,Integer> subjectCountMap = new LinkedHashMap<String,Integer>();
		boolean isMg = false, isDoubleLine = false, lastPage = true;
		int displayRows= 4, stdInt = 0, rowSpanInt = 14;
		String examHeader = "", sem = "", subMarks = "", subjectMarksDisp = "", obtainedStr = "", subject1FromMap = "",
				subject2FromMap = "", prevSubjectTitle = "";
		double total = 0, totalObtained = 0, subjectMarks = 0, subjectTotal = 0, percent = 0;
		int subjectHeadCount = subjectMap.size(), addRow = 0;
		String subTitle = "", subName = "", grNo = "", subGrade = "", totalGrade = "", outOfMarks = "", finalSubMarks = "0";
		String[] optionList;
		String bonafide_header = bundle.getString("BONAFIDE_HEADER_" + sessionData.getAppType());
		String bonafide_header_0 = bundle.getString("BONAFIDE_HEADER_0_" + sessionData.getAppType());
		if(!bonafide_header_0.trim().equalsIgnoreCase("")){
			bonafide_header_0 = bonafide_header_0 + " \n";
			displayRows = 4;
		}

		try {
			stdInt = commonObj.RomanToInteger(std);
//			if(stdInt > 10){
//				rowSpanInt = 12;
//			}
			
			Set setSubjectCount = subjectMap.entrySet();
			Iterator r = setSubjectCount.iterator();
			while (r.hasNext()) {
				Map.Entry me = (Map.Entry) r.next();
				subTitle = subjectMap.get(me.getKey().toString()).get("subject_title");
				
				if(subjectCountMap.get(subTitle) == null){
					subjectCountMap.put(subTitle, 1);
				}
				else{
					subjectCountMap.put(subTitle, 1+subjectCountMap.get(subTitle));
				}
			}
			
			examTypeMap = commonObj.getExamType(stdInt, std, academic);
			examTypeMap.put("TOTAL", "TOTAL");
			examTypeMap.put("FINAL", "AVERAGE");
			examTypeMap2 = commonObj.getExamTypeMapping();
			rowSpanInt = examTypeMap.size();
			displayRows = 60 / rowSpanInt;
			
			
			sem = "final";
			examHeader = "FINAL EXAM";
			
			path = commonObj.createTodayFolder(
					commonObj.getDriveName() + bundle.getString("REPORT_PDF_PATH_" + sessionData.getDBName()), true)
					+ "/";
			fileName = "Exam_Register_Sheet_" + std + "_" + div + "_" + commonObj.timeInMillis() + ".pdf";
			fileAddress = path + fileName;
			File file = new File(fileAddress);
			FileOutputStream fileout = new FileOutputStream(file);
			Document document = new Document(PageSize.LEGAL);
			PdfWriter.getInstance(document, fileout);
			document.addHeader("ABC", "XYZ");
			document.addTitle("Exam Register");
			document.open();// PDF document opened........

			int pageStart = -100;
			Chunk chunkHeader1 = new Chunk(bonafide_header_0 + bonafide_header + " \n");
			Font font1 = FontFactory.getFont("TIMES_ROMAN");
			font1.setStyle(Font.NORMAL);
			font1.setSize(12);
			chunkHeader1.setFont(font1);
			Paragraph paragraph1 = new Paragraph();
			paragraph1.add(chunkHeader1);
			paragraph1.setAlignment(Element.ALIGN_CENTER);
			paragraph1.setSpacingBefore(pageStart+20);

			Chunk chunk3 = new Chunk("EXAM REGISTER SHEET "+academic+"       Std : "+std+"  Div : "+div);
			Font font3 = FontFactory.getFont("TIMES_ROMAN");
			font3.setStyle(Font.NORMAL);
			font3.setSize(12);
			chunk3.setFont(font3);
			Paragraph paragraph3 = new Paragraph();
			paragraph3.add(chunk3);
			paragraph3.setAlignment(Element.ALIGN_CENTER);
			paragraph3.setSpacingBefore(0);

			PdfPTable tableHeader;
			PdfPTable table1;
			
			tableHeader = new PdfPTable(8+(subjectHeadCount));
			tableHeader.setWidthPercentage(100);
			tableHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tableHeader.setSpacingBefore(5.0f);
			
			table1 = new PdfPTable(8+(subjectHeadCount));
			table1.setWidthPercentage(100);
			table1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.setSpacingBefore(5.0f);

			PdfPCell cell301 = new PdfPCell(
					new Paragraph("ROLL NO.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell301.setColspan(1);
			cell301.setRowspan(2);
			cell301.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell301.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell301.setRotation(90);
			tableHeader.addCell(cell301);

			PdfPCell cell301a = new PdfPCell(
					new Paragraph("G.R. NO.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell301a.setColspan(1);
			cell301a.setRowspan(2);
			cell301a.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell301a.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell301a.setRotation(90);
			tableHeader.addCell(cell301a);

			PdfPCell cell302 = new PdfPCell(
					new Paragraph("DOB", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell302.setColspan(1);
			cell302.setRowspan(2);
			cell302.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell302.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell302.setRotation(90);
			tableHeader.addCell(cell302);

			PdfPCell cell303 = new PdfPCell(
					new Paragraph("NAME", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell303.setColspan(1);
			cell303.setRowspan(2);
			cell303.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell303.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell303.setRotation(90);
			tableHeader.addCell(cell303);

			PdfPCell cell304 = new PdfPCell(
					new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cell304.setColspan(1);
			cell304.setRowspan(2);
			cell304.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell304.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell304.setRotation(90);
			tableHeader.addCell(cell304);

			int addToFloat = 5;
			int subjectHeaderCount = 0;
			float []columnWidths=new float[8+(subjectHeadCount)];
			columnWidths[0]=  0.8f;
			columnWidths[1]=  0.8f;
			columnWidths[2]=  0.8f;
			columnWidths[3]=  1.2f;
			columnWidths[4]=  3.0f;
			
			for(int i = 0; i < 2; i++){
				Set setSubjectHeader = subjectMap.entrySet();
				Iterator n = setSubjectHeader.iterator();
				while (n.hasNext()) {
					Map.Entry me = (Map.Entry) n.next();
					subName = me.getKey().toString();
					subjectHeaderCount++;
					if(i == 0 && subjectCountMap.get(subjectMap.get(subName).get("subject_title")) == 1){
						columnWidths[addToFloat]=  1.2f;
						addToFloat = addToFloat+1;
					}
					else if(i == 0 && subjectCountMap.get(subjectMap.get(subName).get("subject_title")) > 1){
						columnWidths[addToFloat]=  1.7f;
						addToFloat = addToFloat+1;
					}
					
					PdfPCell cell305;
					
					if(!prevSubjectTitle.equalsIgnoreCase(subjectMap.get(subName).get("subject_title")) && 
							subjectCountMap.get(subjectMap.get(subName).get("subject_title")) > 1 && i == 0){
						cell305 = new PdfPCell(
								new Paragraph(subjectMap.get(subName).get("subject_title").replace("_", "\n"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
						cell305.setColspan(2);
						cell305.setRowspan(1);
						cell305.setNoWrap(false);
						cell305.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell305.setVerticalAlignment(Element.ALIGN_MIDDLE);
//						cell305.setRotation(90);
						tableHeader.addCell(cell305);
					}
					else if(subjectCountMap.get(subjectMap.get(subName).get("subject_title")) > 1 && i == 1){
						cell305 = new PdfPCell(
								new Paragraph(subName.replace("_", "\n"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell305.setColspan(1);
						cell305.setRowspan(1);
						cell305.setNoWrap(false);
						cell305.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell305.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell305.setRotation(90);
						tableHeader.addCell(cell305);
					}
					else if(subjectCountMap.get(subjectMap.get(subName).get("subject_title")) < 2 && i == 0){
						cell305 = new PdfPCell(
								new Paragraph(subName.replace("_", "\n"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell305.setColspan(1);
						cell305.setRowspan(2);
						cell305.setNoWrap(false);
						cell305.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell305.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell305.setRotation(90);
						tableHeader.addCell(cell305);
					}
					
					if(subjectHeaderCount == subjectHeadCount && i == 0){
						PdfPCell cell314 = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell314.setColspan(1);
						cell314.setRowspan(2);
						cell314.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell314.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell314.setRotation(90);
						tableHeader.addCell(cell314);

						PdfPCell cell315 = new PdfPCell(new Paragraph("PER", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell315.setColspan(1);
						cell315.setRowspan(2);
						cell315.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell315.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell315.setRotation(90);
						tableHeader.addCell(cell315);

						PdfPCell cell316 = new PdfPCell(new Paragraph("REMARK", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell316.setColspan(1);
						cell316.setRowspan(2);
						cell316.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell316.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell316.setRotation(90);
						tableHeader.addCell(cell316);
					}
					prevSubjectTitle = subjectMap.get(subName).get("subject_title");
				}
			}
			
			columnWidths[addToFloat]=  0.8f;
			columnWidths[addToFloat+1]=  0.8f;
			columnWidths[addToFloat+2]=  0.8f;

			Chunk blankChunk = new Chunk("");
			Paragraph blankParagraph = new Paragraph();
			blankParagraph.add(blankChunk);
			blankParagraph.setSpacingBefore(-5);

			PdfPCell cellBlankEnd = new PdfPCell(
					new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
			cellBlankEnd.setColspan(columnWidths.length);
//			cellBlankEnd.setRowspan(1);
			cellBlankEnd.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellBlankEnd.setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			double count = marksSemDataMap.size();
			boolean isOpted = true;
			String optionalSubject = "", subMarksFromMap = "";
//			String attendance = "", working = "", attended = "";
			String result = "", subtotalStr = "";
			Double subTotal = 0.0;
			int rowCount= 0, totalRows = 0;
			int pageCount= 1;
			Set setMarksSemDataMap = marksSemDataMap.entrySet();
			Iterator m = setMarksSemDataMap.iterator();
			while (m.hasNext()) {
				LinkedHashMap<String, String> grDetail = new LinkedHashMap<String, String>();
				LinkedHashMap<String, String> grResultMap = new LinkedHashMap<String, String>();
				LinkedHashMap<String, String> marksSubGrMap = new LinkedHashMap<String, String>();
				lastPage = true;
				Map.Entry me = (Map.Entry) m.next();
				grNo = me.getKey().toString();
				grDetail = (LinkedHashMap<String, String>) me.getValue();
				grResultMap = (LinkedHashMap<String, String>) resultMap.get(grNo);
				marksSubGrMap = (LinkedHashMap<String, String>) marksSubjectcDataMap.get(grNo);
				optionalSubject = studentOptSubAllotMap.get(grNo) == null ? "" :studentOptSubAllotMap.get(grNo).get("optionalSubject");
				optionList = optionalSubject.split("\\|");
				if(grResultMap != null){
					
					String passFail = grResultMap.get("semResult").toString();
					if(grResultMap.get("semPercent").equalsIgnoreCase("NA")) {
						continue;
					}
					totalGrade = commonObj.getResultRemark(passFail, Double.parseDouble(grResultMap.get("semPercent")));
					if(reportType.equalsIgnoreCase("Exam Register-Fail") && !totalGrade.equalsIgnoreCase("Poor")) {
						continue;
					}
					
					PdfPCell cell342 = new PdfPCell(
							new Paragraph(grDetail.get("rollNo"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell342.setColspan(1);
					cell342.setRowspan(rowSpanInt);
					cell342.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell342.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell342);

					PdfPCell cell342a = new PdfPCell(new Paragraph(grNo, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell342a.setColspan(1);
					cell342a.setRowspan(rowSpanInt);
					cell342a.setRotation(90);
					cell342a.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell342a.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell342a);

					PdfPCell cell344 = new PdfPCell(new Paragraph(grResultMap.get("birthDate"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell344.setColspan(1);
					cell344.setRowspan(rowSpanInt);
					cell344.setRotation(90);
					cell344.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell344.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell344);
					
					String name = grDetail.get("name");
					if(name.length() > 40){
						isDoubleLine = true;
						addRow = addRow + 1;
					}
					name = commonObj.FirstWordCap(name);
					int wordCount = commonObj.WordCountInString(name);
					if(wordCount >= 4) {
						name = commonObj.replaceSpaceWithNewLine(name, wordCount);
					}
					
					PdfPCell cell343 = new PdfPCell(
							new Paragraph(name, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell343.setColspan(1);
					cell343.setRowspan(rowSpanInt);
					cell343.setRotation(90);
					cell343.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell343.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell343);
					
					PdfPCell cellBlank = new PdfPCell(
							new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cellBlank.setColspan(1);
					cellBlank.setHorizontalAlignment(Element.ALIGN_LEFT);
					cellBlank.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
					int i = 0;
					Set setSubjectMaxMarks = examTypeMap.entrySet();
					Iterator q = setSubjectMaxMarks.iterator();
					while (q.hasNext()) {
						Map.Entry meType = (Map.Entry) q.next();
						
						PdfPCell cellMarks = new PdfPCell(new Paragraph(meType.getValue().toString(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cellMarks.setColspan(1);
						cellMarks.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellMarks.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table1.addCell(cellMarks);
						
						double subjectCount = 0.0;
						double maxSubType = 0.0;
						Set setSubjectMarks = subjectMap.entrySet();
						Iterator p = setSubjectMarks.iterator();
						while (p.hasNext()) {
							isMg = false;
							isOpted = true;
							Map.Entry meSubject = (Map.Entry) p.next();
							subName = meSubject.getKey().toString();
							subTitle = subjectMap.get(subName).get("subject_title");
							
							if(!meType.getValue().toString().equalsIgnoreCase("AVERAGE") && !meType.getValue().toString().equalsIgnoreCase("TOTAL")){
								maxSubType = Double.parseDouble(maxSubMarks.get(subTitle).get("sem1_"+examTypeMap2.get(meType.getKey().toString().substring(1))+"_ct")) + 
										Double.parseDouble(maxSubMarks.get(subTitle).get("sem2_"+examTypeMap2.get(meType.getKey().toString().substring(1))+"_ct"));	
							}
							if(maxSubType > 0 || meType.getValue().toString().equalsIgnoreCase("AVERAGE") || 
									meType.getValue().toString().equalsIgnoreCase("TOTAL")){
								subMarksFromMap = marksSubGrMap.get(subName+"_"+meType.getKey()) == null ? "NA" : marksSubGrMap.get(subName+"_"+meType.getKey());
								if(subMarksFromMap.contains("(")){
									subMarksFromMap = subMarksFromMap.substring(0,  subMarksFromMap.indexOf("("));
								}
								if(subMarksFromMap.contains(".") && subMarksFromMap.substring(subMarksFromMap.indexOf(".")+1).equalsIgnoreCase("0")){
									subMarksFromMap = subMarksFromMap.substring(0, subMarksFromMap.indexOf("."));
								}
								for(int k = 0; k < optionList.length; k++){
									if(optionList[k].toString().equalsIgnoreCase(subTitle+"_NO")){
										isOpted = false;
									}
								}
								if(!isOpted){
									subMarksFromMap = "-";
								}
							}
							else{
								subMarksFromMap = "-";
							}
							
							PdfPCell cell346 = new PdfPCell(new Paragraph(subMarksFromMap,
									FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
//							cell346.setColspan(1);
							if(!meType.getValue().toString().equalsIgnoreCase("AVERAGE") && !meType.getValue().toString().equalsIgnoreCase("TOTAL")){
								cell346.setColspan(1);
								cell346.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell346.setVerticalAlignment(Element.ALIGN_MIDDLE);
								table1.addCell(cell346);
							}
							else if(!prevSubjectTitle.equalsIgnoreCase(subjectMap.get(subName).get("subject_title"))){
									cell346.setColspan(subjectCountMap.get(subjectMap.get(subName).get("subject_title")));
									cell346.setHorizontalAlignment(Element.ALIGN_CENTER);
									cell346.setVerticalAlignment(Element.ALIGN_MIDDLE);
									table1.addCell(cell346);
									prevSubjectTitle = subjectMap.get(subName).get("subject_title");
							}
							
							subject1FromMap = marksSubGrMap.get(subName+"_SEM1");
							subject2FromMap = marksSubGrMap.get(subName+"_SEM2");
							if(i == 0 && !subject1FromMap.equalsIgnoreCase("NA")){
								if(subject1FromMap.contains("+")){
									subject1FromMap = subject1FromMap.substring(subject1FromMap.indexOf("+")+1, subject1FromMap.indexOf("("));
									subTotal = Double.parseDouble(subject1FromMap);
								}
								else if(!subject1FromMap.equalsIgnoreCase("-") && !subject1FromMap.equalsIgnoreCase("NA")){
									if(!subject1FromMap.substring(0, subject1FromMap.indexOf("(")).equalsIgnoreCase("NA")){
										subTotal = Double.parseDouble(subject1FromMap.substring(0, subject1FromMap.indexOf("(")));
									}
								}
								
								marksSubGrMap.put(subName+"_TOTAL",subTotal+"");
							}
							if(i == 0 && !subject2FromMap.equalsIgnoreCase("NA")){
								if(subject2FromMap.contains("+")){
									subject2FromMap = subject2FromMap.substring(subject2FromMap.indexOf("+")+1, subject2FromMap.indexOf("("));
									subTotal = subTotal + Double.parseDouble(subject2FromMap);
								}
								else if(!subject2FromMap.equalsIgnoreCase("-") && !subject2FromMap.equalsIgnoreCase("NA")){
									if(!subject2FromMap.substring(0, subject2FromMap.indexOf("(")).equalsIgnoreCase("NA")){
										subTotal = subTotal + Double.parseDouble(subject2FromMap.substring(0, subject2FromMap.indexOf("(")));
									}
								}
								
								subtotalStr = subTotal+"";
								if(subtotalStr.substring(subtotalStr.indexOf(".")+1).equalsIgnoreCase("0")){
									subtotalStr = subtotalStr.substring(0, subtotalStr.indexOf("."));
								}
								marksSubGrMap.put(subName+"_TOTAL",subtotalStr+"");
							}
							
							subTotal = 0.0;
						}
						
						if(i == 0){
//							table1.addCell(cellBlankEnd);
//							table1.addCell(cellBlankEnd);
//							table1.addCell(cellBlankEnd);
							
							PdfPCell cellObtained = new PdfPCell(
									new Paragraph(grResultMap.get("semMarksObtained"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
							cellObtained.setColspan(1);
							cellObtained.setRowspan(rowSpanInt);
							cellObtained.setRotation(90);
							cellObtained.setHorizontalAlignment(Element.ALIGN_CENTER);
							cellObtained.setVerticalAlignment(Element.ALIGN_MIDDLE);
							
							PdfPCell cellPercent = new PdfPCell(
									new Paragraph(grResultMap.get("semPercent"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
							cellPercent.setColspan(1);
							cellPercent.setRowspan(rowSpanInt);
							cellPercent.setRotation(90);
							cellPercent.setHorizontalAlignment(Element.ALIGN_CENTER);
							cellPercent.setVerticalAlignment(Element.ALIGN_MIDDLE);
							
							PdfPCell cellProgress = new PdfPCell(
									new Paragraph(totalGrade, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
							cellProgress.setColspan(1);
							cellProgress.setRowspan(rowSpanInt);
							cellProgress.setRotation(90);
							cellProgress.setHorizontalAlignment(Element.ALIGN_CENTER);
							cellProgress.setVerticalAlignment(Element.ALIGN_MIDDLE);
							
							table1.addCell(cellObtained);
							table1.addCell(cellPercent);
							table1.addCell(cellProgress);
						}
						i++;
					}
					rowCount++;
					totalRows++;
					if(rowCount != 0 && rowCount != displayRows && rowSpanInt != 12){
						table1.addCell(cellBlankEnd);
					}
				}

				if(rowCount == displayRows || rowCount == count){
					document.add(paragraph1);
					document.add(paragraph3);
					
					tableHeader.setWidths(columnWidths);
					table1.setWidths(columnWidths);
					
					document.add(tableHeader);
					document.add(blankParagraph);
					document.add(table1);
					table1.flushContent();
					pageCount++;
					addRow = 0;
					document.newPage();
					rowCount = 0;
					lastPage = false;
				}
				totalObtained = 0;
				total = 0;
				isDoubleLine = false;
			}

			if(lastPage){
				document.add(paragraph1);
				document.add(paragraph3);
				
				tableHeader.setWidths(columnWidths);
				table1.setWidths(columnWidths);
				
				document.add(tableHeader);
				document.add(blankParagraph);
				document.add(table1);
			}
			
			document.close();
			
			if(totalRows == 0) {
				fileOpenFlag = false;
				JOptionPane.showMessageDialog(null, "Please update data to generate report");
			}
			else {
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