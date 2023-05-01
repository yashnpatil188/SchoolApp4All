package org.com.maauli;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.com.accesser.SessionData;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.DocumentException;
import com.itextpdf.text.PageSize;
//import com.lowagie.text.Font;

public class CategorywiseReport_PDF {
	static String fileName = "";
	static String fileAddress = "";
	static String path = "";
	static boolean fileOpenFlag = false;
	Common commonObj = new Common();
	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	static Logger logger = Logger.getLogger(MarksSheet_IX_PDF.class.getName());

	public CategorywiseReport_PDF(SessionData sessionData, String exam, String subject, String std, String div,
			String academic, LinkedHashMap<String, Integer> categoryMap, ArrayList<String> categoryList, String reportType) throws DocumentException {

		System.gc();
		fileOpenFlag = true;
		int fontSize = 10;
		int stdInt = 0;
		String sem = "",examHeader = "";
		String bonafide_header = bundle.getString("BONAFIDE_HEADER_" + sessionData.getAppType());
		String bonafide_header_0 = bundle.getString("BONAFIDE_HEADER_0_" + sessionData.getAppType());
		LinkedHashMap<String, String> percentRangeMap = new LinkedHashMap<String, String>();
		
		percentRangeMap.put("91-100", "A1");
		percentRangeMap.put("81-90", "A2");
		percentRangeMap.put("71-80", "B1");
		percentRangeMap.put("61-70", "B2");
		percentRangeMap.put("51-60", "C1");
		percentRangeMap.put("41-50", "C2");
		percentRangeMap.put("33-40", "D");
		percentRangeMap.put("21-32", "E1");
		percentRangeMap.put("UP TO 20", "E2");
		
		if(!reportType.equalsIgnoreCase("Subjectwise")){
			categoryList.add("TOTAL");
			percentRangeMap.put("LEFT", "");
		}
		float []columnWidths=new float[3+(categoryList.size()*2)];
		int addToFloat = 3;
		columnWidths[0]=  1f;
		columnWidths[1]=  1.7f;
		columnWidths[2]=  1.7f;
		
		try {
			stdInt = commonObj.RomanToInteger(std);
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
			fileName = reportType+"_Sheet_" + std + "_" + div + "_" + commonObj.timeInMillis() + ".pdf";
			fileAddress = path + fileName;
			File file = new File(fileAddress);
			FileOutputStream fileout = new FileOutputStream(file);
			Document document = new Document(PageSize.LEGAL.rotate());
			PdfWriter.getInstance(document, fileout);
			document.addHeader("ABC", "XYZ");
			document.addTitle(reportType+" Report");
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

			Chunk chunk3 = new Chunk(reportType+" SHEET");
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

			PdfPTable table1 = new PdfPTable(3+(categoryList.size()*2));

			table1.setWidthPercentage(100);
			table1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.setSpacingBefore(5.0f);

			PdfPCell cell301 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
			cell301.setColspan(1);
			cell301.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell301.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table1.addCell(cell301);

			PdfPCell cell302 = new PdfPCell(
					new Paragraph("CATEGORY", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
			cell302.setColspan(2);
			cell302.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell302.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table1.addCell(cell302);

			for(int i = 0; i < categoryList.size(); i++){
				PdfPCell cellCategoryName;
				if(!reportType.equalsIgnoreCase("Subjectwise")){
					cellCategoryName = new PdfPCell(new Paragraph(categoryList.get(i).replace("_", " "), FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));	
				}
				else{
					cellCategoryName = new PdfPCell(new Paragraph(categoryList.get(i).replace("_", " "), FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize-1)));
				}
				
				cellCategoryName.setColspan(2);
				cellCategoryName.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellCategoryName.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cellCategoryName);
				
				columnWidths[addToFloat]=  1f;
				columnWidths[addToFloat+1]=  1f;
				addToFloat = addToFloat + 2;
			}

			PdfPCell cell313 = new PdfPCell(new Paragraph("SR", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
			cell313.setColspan(1);
			cell313.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell313.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table1.addCell(cell313);

			if(stdInt < 9){
				PdfPCell cell314 = new PdfPCell(new Paragraph("PER", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell314.setColspan(1);
				cell314.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell314.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell314);

				PdfPCell cell315 = new PdfPCell(new Paragraph("GRADE", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell315.setColspan(1);
				cell315.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell315.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell315);
			}
			else{
				PdfPCell cell314 = new PdfPCell(new Paragraph("PER", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cell314.setColspan(2);
				cell314.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell314.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell314);
			}

			for(int i = 0; i < categoryList.size(); i++){
				PdfPCell cellMale = new PdfPCell(new Paragraph("M", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cellMale.setColspan(1);
				cellMale.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellMale.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cellMale);

				PdfPCell cellFemale = new PdfPCell(new Paragraph("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cellFemale.setColspan(1);
				cellFemale.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellFemale.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cellFemale);
			}
			
			int srNo = 1;
			String percentRange = "";
			String grade = "", male = "", female="";
			Set setPercentRangeMap = percentRangeMap.entrySet();
			Iterator n = setPercentRangeMap.iterator();
			while (n.hasNext()) {
				Map.Entry me = (Map.Entry) n.next();
				percentRange = me.getKey().toString();
				grade = me.getValue().toString();
				
				if(percentRange.equalsIgnoreCase("LEFT") && categoryMap.get("LEFT_COUNT") == null){
					continue;
				}
				PdfPCell cellSr = new PdfPCell(new Paragraph(srNo+"", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cellSr.setColspan(1);
				cellSr.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellSr.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cellSr);

				if(stdInt < 9){
					PdfPCell cellRange = new PdfPCell(new Paragraph(percentRange, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
					cellRange.setColspan(1);
					cellRange.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellRange.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cellRange);

					PdfPCell cellGrade = new PdfPCell(new Paragraph(grade, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
					cellGrade.setColspan(1);
					cellGrade.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellGrade.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cellGrade);
				}
				else{
					PdfPCell cellRange = new PdfPCell(new Paragraph(percentRange, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
					cellRange.setColspan(2);
					cellRange.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellRange.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cellRange);
				}
				
				for(int i = 0; i < categoryList.size(); i++){
					male = categoryMap.get(percentRange+"_"+categoryList.get(i)+"_MALE") == null ? "-" : categoryMap.get(percentRange+"_"+categoryList.get(i)+"_MALE")+"";
					PdfPCell cell339 = new PdfPCell(new Paragraph(male, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
					cell339.setColspan(1);
					cell339.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell339.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell339);

					female = categoryMap.get(percentRange+"_"+categoryList.get(i)+"_FEMALE") == null ? "-" : categoryMap.get(percentRange+"_"+categoryList.get(i)+"_FEMALE")+"";
					PdfPCell cell340 = new PdfPCell(new Paragraph(female, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
					cell340.setColspan(1);
					cell340.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell340.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell340);
				}
				srNo++;
			}
			
			if(reportType.equalsIgnoreCase("Subjectwise")){
				PdfPCell cellSr = new PdfPCell(new Paragraph((srNo+1)+"", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cellSr.setColspan(1);
				cellSr.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellSr.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cellSr);

				if(stdInt < 9){
					PdfPCell cellRange = new PdfPCell(new Paragraph("LEFT", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
					cellRange.setColspan(1);
					cellRange.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellRange.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cellRange);

					PdfPCell cellGrade = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
					cellGrade.setColspan(1);
					cellGrade.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellGrade.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cellGrade);
				}
				else{
					PdfPCell cellRange = new PdfPCell(new Paragraph("LEFT", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
					cellRange.setColspan(2);
					cellRange.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellRange.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cellRange);
				}
				
				for(int i = 0; i < categoryList.size(); i++){
					male = categoryMap.get("LEFT_COUNT_MALE") == null ? "-" : categoryMap.get("LEFT_COUNT_MALE")+"";
					if(!male.equalsIgnoreCase("-")){
						categoryMap.put(categoryList.get(i)+"_TOTAL_MALE", ((categoryMap.get(categoryList.get(i)+"_TOTAL_MALE") == null ? 0 : categoryMap.get(categoryList.get(i)+"_TOTAL_MALE"))+categoryMap.get("LEFT_COUNT_MALE")));
					}
					PdfPCell cell339 = new PdfPCell(new Paragraph(male, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
					cell339.setColspan(1);
					cell339.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell339.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell339);

					female = categoryMap.get("LEFT_COUNT_FEMALE") == null ? "-" : categoryMap.get("LEFT_COUNT_FEMALE")+"";
					if(!female.equalsIgnoreCase("-")){
						categoryMap.put(categoryList.get(i)+"_TOTAL_FEMALE", ((categoryMap.get(categoryList.get(i)+"_TOTAL_FEMALE") == null ? 0 : categoryMap.get(categoryList.get(i)+"_TOTAL_FEMALE"))+categoryMap.get("LEFT_COUNT_FEMALE")));
					}
					PdfPCell cell340 = new PdfPCell(new Paragraph(female, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
					cell340.setColspan(1);
					cell340.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell340.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell340);
				}
			}
			
			PdfPCell cellSr = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
			cellSr.setColspan(1);
			cellSr.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellSr.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table1.addCell(cellSr);

			if(stdInt < 9){
				PdfPCell cellRange = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cellRange.setColspan(1);
				cellRange.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellRange.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cellRange);

				PdfPCell cellGrade = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cellGrade.setColspan(1);
				cellGrade.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellGrade.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cellGrade);
			}
			else{
				PdfPCell cellRange = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cellRange.setColspan(2);
				cellRange.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellRange.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cellRange);
			}
			
			for(int i = 0; i < categoryList.size(); i++){
				male = categoryMap.get(categoryList.get(i)+"_TOTAL_MALE") == null ? "0" : categoryMap.get(categoryList.get(i)+"_TOTAL_MALE")+"";
				PdfPCell cellTotalMale = new PdfPCell(new Paragraph(male, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cellTotalMale.setColspan(1);
				cellTotalMale.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellTotalMale.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cellTotalMale);

				female = categoryMap.get(categoryList.get(i)+"_TOTAL_FEMALE") == null ? "0" : categoryMap.get(categoryList.get(i)+"_TOTAL_FEMALE")+"";
				PdfPCell cellTotalFemale = new PdfPCell(new Paragraph(female, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize)));
				cellTotalFemale.setColspan(1);
				cellTotalFemale.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellTotalFemale.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cellTotalFemale);
			}

			// document.newPage();
			// table.setSpacingBefore(300.0f); // Space Before table starts,
			// like margin-top in CSS
			table1.setSpacingAfter(0.0f);

			document.open();// PDF document opened........

			document.add(Chunk.NEWLINE); // Something like in HTML :-)

			table1.setWidths(columnWidths);

			document.add(paragraph1);
			document.add(paragraph2);
			document.add(paragraph3);
			document.add(paragraph4);
			document.add(paragraph5);
			document.add(paragraph6);
			document.add(paragraph7);

			document.add(table1);

			document.add(Chunk.NEWLINE); // Something like in HTML :-)


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
