package org.com.maauli;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.com.accesser.SessionData;
import com.itextpdf.text.BaseColor;
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
import java.util.Map;

public class ProgressReport_PDF {
	static String fileName = "";
	static String fileAddress = "";
	static String path = "";
	static boolean fileOpenFlag = false;
	Common commonObj = new Common();
	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	static Logger logger = Logger.getLogger(ProgressReport_PDF.class.getName());

	public ProgressReport_PDF(SessionData sessionData, String exam, String subject, String std, String div,
			String academic, LinkedHashMap<String, LinkedHashMap<String, String>> marksSemDataMap,
			LinkedHashMap<String, LinkedHashMap<String, String>> studentOptSubAllotMap,
			LinkedHashMap<String, LinkedHashMap<String, String>> maxSubMarks,
			LinkedHashMap<String, LinkedHashMap<String, String>> resultMap, 
			LinkedHashMap<String, String> leftDataMap, LinkedHashMap<String, LinkedHashMap<String, String>> attendanceMap)
					throws DocumentException {

		try {

			System.gc();
			fileOpenFlag = true;
			int spaceBefore = -40;
			String subTitle = "", grNo = "", configValue="", sem = "", footer = "", footer1 = "", footer2 = "";
			String[] optionalList;
			String bonafide_header = bundle.getString("BONAFIDE_HEADER_" + sessionData.getAppType());
			String bonafide_header_0 = bundle.getString("BONAFIDE_HEADER_0_" + sessionData.getAppType());
			if(sessionData.getConfigMap().get("FOOTER") != null) {
				footer = sessionData.getConfigMap().get("FOOTER");
			}
			else {
				footer = bundle.getString("FOOTER_"+sessionData.getAppType());
			}
			
			if(sessionData.getConfigMap().get("FOOTER1") != null) {
				footer1 = sessionData.getConfigMap().get("FOOTER1");
			}
			else {
				footer1 = bundle.getString("FOOTER1_"+sessionData.getAppType());
			}
			
			if(sessionData.getConfigMap().get("FOOTER2") != null) {
				footer2 = sessionData.getConfigMap().get("FOOTER2");
			}
			else {
				footer2 = bundle.getString("FOOTER2_"+sessionData.getAppType());
			}
			
			int startMonth = Integer.parseInt(bundle.getString("ACADEMIC_START_MONTH"));
			int semStartMonth = startMonth;
			if(exam.equalsIgnoreCase("Semester 2")) {
				sem = "SEM2";
			}
			else {
				sem = "SEM1";
			}
			
			if(sessionData.getConfigMap().get("ATT_"+sem+"_"+academic) != null) {
				configValue = sessionData.getConfigMap().get("ATT_"+sem+"_"+academic);
				semStartMonth = Integer.parseInt(configValue.substring(0, configValue.indexOf("_")));
			}
			
			path = commonObj.createTodayFolder(
					commonObj.getDriveName() + bundle.getString("REPORT_PDF_PATH_" + sessionData.getDBName()), true)
					+ "/";
			fileName = "Progress_Report_"+academic+"_" + std + "_" + div + "_" + commonObj.timeInMillis() + ".pdf";
			fileAddress = path + fileName;
			File file = new File(fileAddress);
			FileOutputStream fileout = new FileOutputStream(file);
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, fileout);
			document.addHeader("ABC", "XYZ");
			document.addTitle("Progress Report");
			document.open();// PDF document opened........
			float[] columnWidths = new float[] { 1.3f, 0.9f, 0.9f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 0.9f, 0.9f, 0.9f,
					0.9f, 0.9f, 0.9f, 0.9f, 0.9f, 0.9f, 0.9f, 1.2f, 1.2f, 1.2f, 1.2f, 1f, 1f, 1.2f };
			float spaceBeforeTable = -312.0f;
			/*if(std.equalsIgnoreCase("VIII")){
				spaceBeforeTable = -272.0f;
			}*/
			
			double count = marksSemDataMap.size();
			String optionalSubject = "", progress = "", progressSem1 = "", progressSem2 = "", hobbies = "",
					improve = "", improveSem1 = "", improveSem2 = "";
			int rowCount= 0;
			int srNo = 1, noOfSubjects = 0;
			float noOfSubjectsFloat = 0f;
			int pageCount= 1;
			boolean skipSubject = false;
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
				if(grResultMap == null){
					continue;
				}
				optionalSubject = studentOptSubAllotMap.get(grNo) == null ? "" :studentOptSubAllotMap.get(grNo).get("optionalSubject");
				optionalList = optionalSubject.split("\\|");
				
				Chunk chunkHeader = new Chunk(bonafide_header_0);
				Font font = FontFactory.getFont("TIMES_ROMAN");
				font.setStyle(Font.NORMAL);
				font.setSize(12);
				chunkHeader.setFont(font);
				Paragraph paragraph = new Paragraph();
				paragraph.add(chunkHeader);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				paragraph.setSpacingBefore(spaceBefore);

				Chunk chunkHeader1 = new Chunk(bonafide_header);
				Font font1 = FontFactory.getFont("TIMES_ROMAN");
				font1.setStyle(Font.NORMAL);
				font1.setSize(14);
				chunkHeader1.setFont(font1);
				Paragraph paragraph1 = new Paragraph();
				paragraph1.add(chunkHeader1);
				paragraph1.setAlignment(Element.ALIGN_CENTER);
				paragraph1.setSpacingBefore(0);

				Chunk chunkHeader2 = new Chunk("PROGRESS REPORT : "+academic);
				Font font2 = FontFactory.getFont("TIMES_ROMAN");
				font2.setStyle(Font.BOLD);
				font2.setSize(12);
				chunkHeader2.setFont(font2);
				Paragraph paragraph2 = new Paragraph();
				paragraph2.add(chunkHeader2);
				paragraph2.setAlignment(Element.ALIGN_CENTER);
				paragraph2.setSpacingBefore(3);

				Chunk chunk3 = new Chunk("Name of the Student : "+commonObj.FirstWordCap(grDetail.get("name")));
				Font font3 = FontFactory.getFont("TIMES_ROMAN");
				font3.setStyle(Font.NORMAL);
				font3.setSize(14);
				chunk3.setFont(font3);
				Paragraph paragraph3 = new Paragraph();
				paragraph3.add(chunk3);
				paragraph3.setAlignment(Element.ALIGN_LEFT);
				paragraph3.setSpacingBefore(12);

				PdfPTable table = new PdfPTable(28);

				table.setWidthPercentage(60);
				table.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.setSpacingBefore(10.0f);

				PdfPCell cell001 = new PdfPCell(new Paragraph("STD", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell001.setColspan(5);
				cell001.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell001.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell001);

				PdfPCell cell002 = new PdfPCell(new Paragraph("DIV", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell002.setColspan(5);
				cell002.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell002.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell002);

				PdfPCell cell003 = new PdfPCell(new Paragraph("ROLL NO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell003.setColspan(5);
				cell003.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell003.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell003);

				PdfPCell cell004 = new PdfPCell(new Paragraph("GR NO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell004.setColspan(5);
				cell004.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell004.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell004);

				PdfPCell cell005 = new PdfPCell(
						new Paragraph("BIRTH DATE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell005.setColspan(8);
				cell005.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell005.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell005);

				PdfPCell cell006 = new PdfPCell(new Paragraph(std, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell006.setColspan(5);
				cell006.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell006.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell006);

				PdfPCell cell007 = new PdfPCell(new Paragraph(div, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell007.setColspan(5);
				cell007.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell007.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell007);

				PdfPCell cell008 = new PdfPCell(new Paragraph(grDetail.get("rollNo"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell008.setColspan(5);
				cell008.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell008.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell008);

				PdfPCell cell009 = new PdfPCell(new Paragraph(grDetail.get("grNo"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell009.setColspan(5);
				cell009.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell009.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell009);

				PdfPCell cell010 = new PdfPCell(
						new Paragraph(grResultMap.get("birthDate"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell010.setColspan(8);
				cell010.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell010.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell010);

				// document.newPage();
				// table.setSpacingBefore(300.0f); // Space Before table starts,
				// like margin-top in CSS
				table.setSpacingAfter(8.0f);

				PdfPTable tablea = new PdfPTable(28);

				tablea.setWidthPercentage(60);
				tablea.setHorizontalAlignment(Element.ALIGN_LEFT);
				tablea.setSpacingBefore(0.0f);

				PdfPCell cell011 = new PdfPCell(new Paragraph("AGE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell011.setColspan(5);
				cell011.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell011.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tablea.addCell(cell011);

				PdfPCell cell012 = new PdfPCell(new Paragraph("SEX", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell012.setColspan(5);
				cell012.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell012.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tablea.addCell(cell012);

				PdfPCell cell013 = new PdfPCell(new Paragraph("WEIGHT", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell013.setColspan(5);
				cell013.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell013.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tablea.addCell(cell013);

				PdfPCell cell014 = new PdfPCell(new Paragraph("HEIGHT", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell014.setColspan(5);
				cell014.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell014.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tablea.addCell(cell014);

				PdfPCell cell015 = new PdfPCell(
						new Paragraph("BLOOD GROUP", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell015.setColspan(8);
				cell015.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell015.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tablea.addCell(cell015);

				PdfPCell cell016 = new PdfPCell(new Paragraph(grResultMap.get("age"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell016.setColspan(5);
				cell016.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell016.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tablea.addCell(cell016);

				PdfPCell cell017 = new PdfPCell(new Paragraph(grResultMap.get("gender"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell017.setColspan(5);
				cell017.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell017.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tablea.addCell(cell017);

				PdfPCell cell018 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell018.setColspan(5);
				cell018.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell018.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tablea.addCell(cell018);

				PdfPCell cell019 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell019.setColspan(5);
				cell019.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell019.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tablea.addCell(cell019);

				PdfPCell cell020 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell020.setColspan(8);
				cell020.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell020.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tablea.addCell(cell020);

				// document.newPage();
				// tablea.setSpacingBefore(300.0f); // Space Before table starts,
				// like margin-top in CSS
				tablea.setSpacingAfter(8.0f);

				PdfPTable tableb = new PdfPTable(28);

				tableb.setWidthPercentage(100);
				tableb.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableb.setSpacingBefore(0.0f);

				PdfPCell cell101 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell101.setColspan(3);
				cell101.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell101.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell101);

				PdfPCell cell102 = new PdfPCell(
						new Paragraph("NAME OF PARENTS/GUARDIAN", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell102.setColspan(10);
				cell102.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell102.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell102);

				PdfPCell cell103 = new PdfPCell(
						new Paragraph("QUALIFICATION", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell103.setColspan(5);
				cell103.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell103.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell103);

				PdfPCell cell104 = new PdfPCell(
						new Paragraph("OCCUPATION", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell104.setColspan(6);
				cell104.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell104.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell104);

				PdfPCell cell105 = new PdfPCell(
						new Paragraph("CONTACT NO.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell105.setColspan(4);
				cell105.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell105.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell105);

				PdfPCell cell106 = new PdfPCell(new Paragraph("FATHER", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell106.setColspan(3);
				cell106.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell106.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell106);

				PdfPCell cell107 = new PdfPCell(
						new Paragraph(grResultMap.get("fatherName") + " " + grResultMap.get("lastName"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell107.setColspan(10);
				cell107.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell107.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell107);

				PdfPCell cell108 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell108.setColspan(5);
				cell108.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell108.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell108);

				PdfPCell cell109 = new PdfPCell(
						new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell109.setColspan(6);
				cell109.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell109.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell109);

				PdfPCell cell110 = new PdfPCell(
						new Paragraph(grResultMap.get("contact1"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell110.setColspan(4);
				cell110.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell110.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell110);

				PdfPCell cell111 = new PdfPCell(new Paragraph("MOTHER", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell111.setColspan(3);
				cell111.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell111.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell111);

				PdfPCell cell112 = new PdfPCell(
						new Paragraph(grResultMap.get("motherName") + " " + grResultMap.get("lastName"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell112.setColspan(10);
				cell112.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell112.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell112);

				PdfPCell cell113 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell113.setColspan(5);
				cell113.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell113.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell113);

				PdfPCell cell114 = new PdfPCell(
						new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell114.setColspan(6);
				cell114.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell114.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell114);

				PdfPCell cell115 = new PdfPCell(
						new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell115.setColspan(4);
				cell115.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell115.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell115);

				PdfPCell cell116 = new PdfPCell(
						new Paragraph("GUARDIAN", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell116.setColspan(3);
				cell116.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell116.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell116);

				PdfPCell cell117 = new PdfPCell(
						new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell117.setColspan(10);
				cell117.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell117.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell117);

				PdfPCell cell118 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell118.setColspan(5);
				cell118.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell118.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell118);

				PdfPCell cell119 = new PdfPCell(
						new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell119.setColspan(6);
				cell119.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell119.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell119);

				PdfPCell cell120 = new PdfPCell(
						new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell120.setColspan(4);
				cell120.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell120.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableb.addCell(cell120);

				// document.newPage();
				// tableb.setSpacingBefore(300.0f); // Space Before table starts,
				// like margin-top in CSS
				tableb.setSpacingAfter(0.0f);

				PdfPTable table1 = new PdfPTable(28);
				PdfPTable table2 = new PdfPTable(28);

				table1.setWidthPercentage(49);
				table1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.setWidthPercentage(49);
				table2.setHorizontalAlignment(Element.ALIGN_RIGHT);
				
				table1.setSpacingBefore(5.0f);

				PdfPCell cell301 = new PdfPCell(
						new Paragraph("FIRST TERM", FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
				cell301.setColspan(28);
				// cell301.setRowspan (2);
				cell301.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell301.setVerticalAlignment(Element.ALIGN_TOP);
				// cell301.setPadding (10.0f);
//				cell301.setBackgroundColor(new BaseColor(110, 110, 110));
				table1.addCell(cell301);

				PdfPCell cell302 = new PdfPCell(new Paragraph("Sr. No.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell302.setColspan(2);
				// cell302.setRowspan (2);
				cell302.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell302.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell302.setPadding (10.0f);
//				 cell302.setBackgroundColor (new BaseColor (110, 110, 110));
				table1.addCell(cell302);

				PdfPCell cell303 = new PdfPCell(
						new Paragraph("SUBJECTS", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell303.setColspan(12);
				cell303.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell303.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell303);

				PdfPCell cell304 = new PdfPCell(new Paragraph("GRADE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell304.setColspan(5);
				cell304.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell304.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell304);

				PdfPCell cell305 = new PdfPCell(new Paragraph("REMARKS", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell305.setColspan(9);
				cell305.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell305.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell305);
				
				PdfPCell cell401 = new PdfPCell(
						new Paragraph("SECOND TERM", FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
				cell401.setColspan(28);
				cell401.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell401.setVerticalAlignment(Element.ALIGN_MIDDLE);
//				cell401.setBackgroundColor(new BaseColor(110, 110, 110));
				table2.addCell(cell401);

				PdfPCell cell402 = new PdfPCell(new Paragraph("Sr. No.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell402.setColspan(2);
				cell402.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell402.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell402);

				PdfPCell cell403 = new PdfPCell(
						new Paragraph("SUBJECTS", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell403.setColspan(12);
				cell403.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell403.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell403);

				PdfPCell cell404 = new PdfPCell(new Paragraph("GRADE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell404.setColspan(5);
				cell404.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell404.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell404);

				PdfPCell cell405 = new PdfPCell(new Paragraph("REMARKS", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell405.setColspan(9);
				cell405.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell405.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell405);

				Set setSubjectHeader = maxSubMarks.entrySet();
				noOfSubjects = maxSubMarks.size();
				noOfSubjectsFloat = (float) noOfSubjects;
				Iterator n = setSubjectHeader.iterator();
				while (n.hasNext()) {
					Map.Entry meSubject = (Map.Entry) n.next();
					subTitle = meSubject.getKey().toString();
					skipSubject = false;
					
					for(int i=0; i < optionalList.length; i++){
						if(optionalList[i].equalsIgnoreCase(subTitle+"_NO")){
							noOfSubjects--;
							noOfSubjectsFloat--;
							skipSubject = true;
						}
					}
					
					if(skipSubject){
						continue;
					}
					
					PdfPCell cell306 = new PdfPCell(new Paragraph(srNo+"", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell306.setColspan(2);
					cell306.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell306.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell306.setPadding(10.0f);
					cell306.setNoWrap(true);
					table1.addCell(cell306);

					PdfPCell cell307 = new PdfPCell(new Paragraph(subTitle.replace("_", " "), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell307.setColspan(12);
					cell307.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell307.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell307);

					PdfPCell cell308 = new PdfPCell(new Paragraph(grResultMap.get(subTitle+"_SEM1"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell308.setColspan(5);
					cell308.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell308.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table1.addCell(cell308);

					table2.addCell(cell306);
					table2.addCell(cell307);

					PdfPCell cell408 = new PdfPCell(new Paragraph(grResultMap.get(subTitle+"_SEM2") == "NA" ? "" : grResultMap.get(subTitle+"_SEM2"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell408.setColspan(5);
					cell408.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell408.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell408);

					progress = commonObj.revertCommaApostrophy(commonObj.FirstWordCap(resultMap.get(grNo).get("semProgress")));
					progressSem1 = commonObj.revertCommaApostrophy(commonObj.FirstWordCap(resultMap.get(grNo).get("sem1Progress")));
					if(!progressSem1.equalsIgnoreCase("poor")) {
						progressSem1 = progressSem1 + " \n Acquired ability level";
					}
					progressSem2 = commonObj.FirstWordCap(resultMap.get(grNo).get("sem2Progress"));
					if(!progressSem2.equalsIgnoreCase("poor")) {
						progressSem2 = progressSem2 + " \n Acquired ability level";
					}
					
					hobbies = commonObj.revertCommaApostrophy(commonObj.FirstWordCap(resultMap.get(grNo).get("hobbies")));
					improve = commonObj.revertCommaApostrophy(commonObj.FirstWordCap(resultMap.get(grNo).get("semImprove").replace("*", ", ").replace("_", " ")));
					improveSem1 = commonObj.revertCommaApostrophy(commonObj.FirstWordCap(resultMap.get(grNo).get("sem1Improve").replace("*", ", ").replace("_", " ")));
					if(exam.equalsIgnoreCase("Semester 2")) {
						improveSem2 = commonObj.revertCommaApostrophy(commonObj.FirstWordCap(resultMap.get(grNo).get("sem2Improve").replace("*", ", ").replace("_", " ")));
					}
					
					if(srNo == 1){
						PdfPCell cell309 = new PdfPCell(new Paragraph("PROGRESS : \n\n "+progressSem1, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell309.setColspan(9);
						cell309.setRowspan(3);
						cell309.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell309.setVerticalAlignment(Element.ALIGN_TOP);
						table1.addCell(cell309);
						if(exam.equalsIgnoreCase("Semester 1")) {
							progressSem2 = "";
						}
						PdfPCell cell309Sem2 = new PdfPCell(new Paragraph("PROGRESS : \n\n "+progressSem2, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell309Sem2.setColspan(9);
						cell309Sem2.setRowspan(3);
						cell309Sem2.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell309Sem2.setVerticalAlignment(Element.ALIGN_TOP);
						table2.addCell(cell309Sem2);
					}
					else if(srNo == 4){
						PdfPCell cell319 = new PdfPCell(
								new Paragraph("HOBBIES / INTEREST : \n\n "+hobbies, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell319.setColspan(9);
						cell319.setRowspan(3);
						cell319.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell319.setVerticalAlignment(Element.ALIGN_TOP);
						table1.addCell(cell319);
						
						if(exam.equalsIgnoreCase("Semester 1")) {
							hobbies = "";
						}
						PdfPCell cell319Sem2 = new PdfPCell(
								new Paragraph("HOBBIES / INTEREST : \n\n "+hobbies, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell319Sem2.setColspan(9);
						cell319Sem2.setRowspan(3);
						cell319Sem2.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell319Sem2.setVerticalAlignment(Element.ALIGN_TOP);
						table2.addCell(cell319Sem2);
					}
					else if(srNo == 7){
						PdfPCell cell329 = new PdfPCell(
								new Paragraph("IMPROVEMENT REQUIRED IN : \n\n "+improveSem1, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell329.setColspan(9);
						cell329.setRowspan(noOfSubjects-6);
						cell329.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell329.setVerticalAlignment(Element.ALIGN_TOP);
						table1.addCell(cell329);
						if(exam.equalsIgnoreCase("Semester 1")) {
							improveSem2 = "";
						}
						PdfPCell cell329Sem2 = new PdfPCell(
								new Paragraph("IMPROVEMENT REQUIRED IN : \n\n "+improveSem2, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell329Sem2.setColspan(9);
						cell329Sem2.setRowspan(noOfSubjects-6);
						cell329Sem2.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell329Sem2.setVerticalAlignment(Element.ALIGN_TOP);
						table2.addCell(cell329Sem2);
					}
					
					srNo++;
				}
				
				if(srNo > 10 ) {
					table2.setSpacingBefore(10.0f);
				}
				else {
					table2.setSpacingBefore(26.0f);
				}
				
//				if(srNo > 9){
//					spaceBeforeTable = spaceBeforeTable - (noOfSubjectsFloat-9)*40.0f;
//				}
				
				spaceBeforeTable = -312.0f;
				if(srNo < 9){
					for(int i = 0; i < 9-srNo; srNo++){
						table2.addCell("");
						table2.addCell("");
					}
				}
				else {
//					spaceBeforeTable = -326.0f - ((srNo-9)*15.0f);//pri 15
					spaceBeforeTable = -326.0f - ((srNo-9)*13.0f);//pri 15
				}
				
				table1.setSpacingAfter(spaceBeforeTable);

				PdfPTable table3 = new PdfPTable(28);

				table3.setWidthPercentage(100);
				table3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.setSpacingBefore(5.0f);

				PdfPCell cell501 = new PdfPCell(
						new Paragraph("RECORD OF ATTENDANCE", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				cell501.setColspan(28);
				cell501.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell501.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell501);

				PdfPCell cell502 = new PdfPCell(new Paragraph("Months", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell502.setColspan(2);
				cell502.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell502.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell502);

				PdfPCell cell503 = new PdfPCell(new Paragraph(commonObj.intgerToMonth(startMonth+""), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell503.setColspan(2);
				cell503.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell503.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell503);

				PdfPCell cell504 = new PdfPCell(new Paragraph(commonObj.intgerToMonth((startMonth+1)+""), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell504.setColspan(2);
				cell504.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell504.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell504);

				PdfPCell cell505 = new PdfPCell(new Paragraph(commonObj.intgerToMonth((startMonth+2)+""), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell505.setColspan(2);
				cell505.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell505.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell505);

				PdfPCell cell506 = new PdfPCell(new Paragraph(commonObj.intgerToMonth((startMonth+3)+""), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell506.setColspan(2);
				cell506.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell506.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell506);

				PdfPCell cell507 = new PdfPCell(new Paragraph(commonObj.intgerToMonth((startMonth+4)+""), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell507.setColspan(2);
				cell507.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell507.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell507);

				PdfPCell cell508 = new PdfPCell(new Paragraph(commonObj.intgerToMonth((startMonth+5)+""), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell508.setColspan(2);
				cell508.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell508.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell508);

				PdfPCell cell509 = new PdfPCell(new Paragraph(commonObj.intgerToMonth((startMonth+6)+""), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell509.setColspan(2);
				cell509.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell509.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell509);

				PdfPCell cell510 = new PdfPCell(new Paragraph(commonObj.intgerToMonth((startMonth+7)+""), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell510.setColspan(2);
				cell510.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell510.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell510);

				PdfPCell cell511 = new PdfPCell(new Paragraph(commonObj.intgerToMonth((startMonth+8)+""), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell511.setColspan(2);
				cell511.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell511.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell511);

				PdfPCell cell512 = new PdfPCell(new Paragraph(commonObj.intgerToMonth((startMonth+9)+""), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell512.setColspan(2);
				cell512.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell512.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell512);

				PdfPCell cell513 = new PdfPCell(new Paragraph(commonObj.intgerToMonth((startMonth+10)+""), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell513.setColspan(2);
				cell513.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell513.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell513);

				PdfPCell cell514 = new PdfPCell(new Paragraph(commonObj.intgerToMonth((startMonth+11)+""), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell514.setColspan(2);
				cell514.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell514.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell514);

				PdfPCell cell515 = new PdfPCell(new Paragraph("Total", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell515.setColspan(2);
				cell515.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell515.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell515);

				PdfPCell cell516 = new PdfPCell(
						new Paragraph("Working Days", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell516.setColspan(2);
				cell516.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell516.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell516);
				
				String presentTotal = "", workingTotal = "";
				PdfPCell cell517 = null, cell518 = null, cell519 = null, cell520 = null, cell521 = null, cell522 = null, cell523 = null,
						 cell524 = null, cell525 = null, cell526 = null, cell527 = null, cell528 = null, cell529 = null,
						 cell531 = null, cell532 = null, cell533 = null, cell534 = null, cell535 = null, cell536 = null, cell537 = null,
						 cell538 = null, cell539 = null, cell540 = null, cell541 = null, cell542 = null, cell543 = null;
				if(sem.equalsIgnoreCase("SEM1") && attendanceMap != null && !attendanceMap.isEmpty()) {
					cell517 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell518 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+1)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell519 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+2)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell520 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+3)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell521 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+4)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell522 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+5)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));

					cell523 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell524 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell525 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell526 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell527 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell528 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					
					//sem2
					if(attendanceMap.get(grNo) != null) {
						cell531 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell532 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+1)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell533 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+2)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell534 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+3)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell535 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+4)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell536 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+5)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					}
					else {
						cell531 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell532 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell533 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell534 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell535 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell536 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					}
					
					cell537 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell538 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell539 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell540 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell541 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell542 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				}
				else if(attendanceMap != null && !attendanceMap.isEmpty()){
					cell517 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell518 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+1)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell519 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+2)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell520 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+3)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell521 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+4)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell522 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+5)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					
					cell523 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+6)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell524 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+7)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell525 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+8)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell526 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+9)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell527 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+10)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell528 = new PdfPCell(new Paragraph(attendanceMap.get("total").get(commonObj.intgerToMonth((startMonth+11)+"")+"_TOT"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					
					//sem2
					if(attendanceMap.get(grNo) != null) {
						cell531 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell532 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+1)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell533 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+2)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell534 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+3)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell535 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+4)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell536 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+5)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						
						cell537 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+6)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell538 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+7)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell539 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+8)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell540 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+9)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell541 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+10)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell542 = new PdfPCell(new Paragraph(attendanceMap.get(grNo).get(commonObj.intgerToMonth((startMonth+11)+"")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					}
					else {
						cell531 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell532 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell533 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell534 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell535 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell536 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						
						cell537 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell538 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell539 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell540 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell541 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
						cell542 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					}
					
				}
				else {
					cell517 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell518 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell519 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell520 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell521 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell522 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell523 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell524 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell525 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell526 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell527 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell528 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					
					cell531 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell532 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell533 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell534 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell535 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell536 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					
					cell537 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell538 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell539 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell540 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell541 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell542 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				}
				
				if(attendanceMap.get(grNo) != null) {
					presentTotal = attendanceMap.get(grNo).get("attended");
					workingTotal = attendanceMap.get("total").get("total");
					if(workingTotal.equalsIgnoreCase("0")) {
						workingTotal = "";
					}
					if(presentTotal.equalsIgnoreCase("0")) {
						presentTotal = "";
					}
					cell529 = new PdfPCell(new Paragraph(workingTotal, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell543 = new PdfPCell(new Paragraph(presentTotal, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				}
				else {
					cell529 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell543 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				}
				
				cell517.setColspan(2);
				cell517.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell517.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell517);

				cell518.setColspan(2);
				cell518.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell518.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell518);

				cell519.setColspan(2);
				cell519.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell519.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell519);

				cell520.setColspan(2);
				cell520.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell520.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell520);

				cell521.setColspan(2);
				cell521.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell521.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell521);

				cell522.setColspan(2);
				cell522.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell522.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell522);

				cell523.setColspan(2);
				cell523.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell523.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell523);

				cell524.setColspan(2);
				cell524.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell524.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell524);

				cell525.setColspan(2);
				cell525.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell525.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell525);

				cell526.setColspan(2);
				cell526.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell526.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell526);

				cell527.setColspan(2);
				cell527.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell527.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell527);

				cell528.setColspan(2);
				cell528.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell528.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell528);

				cell529.setColspan(2);
				cell529.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell529.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell529);

				PdfPCell cell530 = new PdfPCell(
						new Paragraph("Present Days", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell530.setColspan(2);
				cell530.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell530.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell530);

				cell531.setColspan(2);
				cell531.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell531.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell531);

				cell532.setColspan(2);
				cell532.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell532.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell532);

				cell533.setColspan(2);
				cell533.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell533.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell533);

				cell534.setColspan(2);
				cell534.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell534.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell534);

				cell535.setColspan(2);
				cell535.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell535.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell535);

				cell536.setColspan(2);
				cell536.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell536.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell536);

				cell537.setColspan(2);
				cell537.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell537.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell537);

				cell538.setColspan(2);
				cell538.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell538.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell538);

				cell539.setColspan(2);
				cell539.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell539.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell539);

				cell540.setColspan(2);
				cell540.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell540.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell540);

				cell541.setColspan(2);
				cell541.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell541.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell541);

				cell542.setColspan(2);
				cell542.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell542.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell542);

				cell543.setColspan(2);
				cell543.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell543.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell543);

				PdfPCell cell544 = new PdfPCell(
						new Paragraph("C. T. Sign", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell544.setColspan(2);
				cell544.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell544.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell544);

				PdfPCell cell545 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell545.setColspan(2);
				cell545.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell545.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell545);

				PdfPCell cell546 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell546.setColspan(2);
				cell546.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell546.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell546);

				PdfPCell cell547 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell547.setColspan(2);
				cell547.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell547.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell547);

				PdfPCell cell548 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell548.setColspan(2);
				cell548.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell548.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell548);

				PdfPCell cell549 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell549.setColspan(2);
				cell549.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell549.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell549);

				PdfPCell cell550 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell550.setColspan(2);
				cell550.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell550.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell550);

				PdfPCell cell551 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell551.setColspan(2);
				cell551.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell551.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell551);

				PdfPCell cell552 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell552.setColspan(2);
				cell552.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell552.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell552);

				PdfPCell cell553 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell553.setColspan(2);
				cell553.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell553.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell553);

				PdfPCell cell554 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell554.setColspan(2);
				cell554.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell554.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell554);

				PdfPCell cell555 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell555.setColspan(2);
				cell555.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell555.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell555);

				PdfPCell cell556 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell556.setColspan(2);
				cell556.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell556.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell556);

				PdfPCell cell557 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell557.setColspan(2);
				cell557.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell557.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell557);

				PdfPCell cell558 = new PdfPCell(
						new Paragraph(commonObj.FirstWordCap(footer1)+" Sign", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell558.setColspan(2);
				cell558.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell558.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell558);

				PdfPCell cell559 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell559.setColspan(2);
				cell559.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell559.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell559);

				PdfPCell cell560 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell560.setColspan(2);
				cell560.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell560.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell560);

				PdfPCell cell561 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell561.setColspan(2);
				cell561.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell561.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell561);

				PdfPCell cell562 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell562.setColspan(2);
				cell562.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell562.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell562);

				PdfPCell cell563 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell563.setColspan(2);
				cell563.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell563.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell563);

				PdfPCell cell564 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell564.setColspan(2);
				cell564.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell564.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell564);

				PdfPCell cell565 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell565.setColspan(2);
				cell565.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell565.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell565);

				PdfPCell cell566 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell566.setColspan(2);
				cell566.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell566.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell566);

				PdfPCell cell567 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell567.setColspan(2);
				cell567.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell567.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell567);

				PdfPCell cell568 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell568.setColspan(2);
				cell568.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell568.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell568);

				PdfPCell cell569 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell569.setColspan(2);
				cell569.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell569.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell569.setPadding (10.0f);
				table3.addCell(cell569);

				PdfPCell cell570 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell570.setColspan(2);
				cell570.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell570.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell570);

				PdfPCell cell571 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell571.setColspan(2);
				cell571.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell571.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table3.addCell(cell571);

				// document.newPage();
				// table.setSpacingBefore(300.0f); // Space Before table starts,
				// like margin-top in CSS
				table3.setSpacingAfter(0.0f);

				PdfPTable table4 = new PdfPTable(28);

				table4.setWidthPercentage(100);
				table4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table4.setSpacingBefore(5.0f);

				PdfPCell cell601 = new PdfPCell(
						new Paragraph("GRADES RECKONER", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				cell601.setColspan(28);
				cell601.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell601.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell601);

				PdfPCell cell602 = new PdfPCell(
						new Paragraph("Percentage", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell602.setColspan(3);
				cell602.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell602.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell602);

				PdfPCell cell603 = new PdfPCell(
						new Paragraph("91%-100%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell603.setColspan(3);
				cell603.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell603.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell603);

				PdfPCell cell604 = new PdfPCell(new Paragraph("81%-90%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell604.setColspan(3);
				cell604.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell604.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell604);

				PdfPCell cell605 = new PdfPCell(new Paragraph("71%-80%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell605.setColspan(3);
				cell605.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell605.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell605);

				PdfPCell cell606 = new PdfPCell(new Paragraph("61%-70%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell606.setColspan(3);
				cell606.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell606.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell606);

				PdfPCell cell607 = new PdfPCell(new Paragraph("51%-60%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell607.setColspan(3);
				cell607.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell607.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell607);

				PdfPCell cell608 = new PdfPCell(new Paragraph("41%-50%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell608.setColspan(3);
				cell608.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell608.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell608);

				PdfPCell cell609 = new PdfPCell(new Paragraph("33%-40%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell609.setColspan(2);
				cell609.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell609.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell609);

				PdfPCell cell610 = new PdfPCell(new Paragraph("21%-32%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell610.setColspan(2);
				cell610.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell610.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell610);

				PdfPCell cell611 = new PdfPCell(
						new Paragraph("20% or Less", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell611.setColspan(3);
				cell611.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell611.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell611);

				PdfPCell cell612 = new PdfPCell(new Paragraph("Grade", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell612.setColspan(3);
				cell612.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell612.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell612);

				PdfPCell cell613 = new PdfPCell(new Paragraph("A-1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell613.setColspan(3);
				cell613.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell613.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell613);

				PdfPCell cell614 = new PdfPCell(new Paragraph("A-2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell614.setColspan(3);
				cell614.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell614.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell614);

				PdfPCell cell615 = new PdfPCell(new Paragraph("B-1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell615.setColspan(3);
				cell615.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell615.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell615);

				PdfPCell cell616 = new PdfPCell(new Paragraph("B-2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell616.setColspan(3);
				cell616.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell616.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell616);

				PdfPCell cell617 = new PdfPCell(new Paragraph("C-1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell617.setColspan(3);
				cell617.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell617.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell617);

				PdfPCell cell618 = new PdfPCell(new Paragraph("C-2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell618.setColspan(3);
				cell618.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell618.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell618);

				PdfPCell cell619 = new PdfPCell(new Paragraph("D", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell619.setColspan(2);
				cell619.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell619.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell619);

				PdfPCell cell620 = new PdfPCell(new Paragraph("E-1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell620.setColspan(2);
				cell620.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell620.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell620);

				PdfPCell cell621 = new PdfPCell(new Paragraph("E-2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell621.setColspan(3);
				cell621.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell621.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table4.addCell(cell621);

				table4.setSpacingAfter(0.0f);

				Chunk chunk28 = new Chunk(footer1+"																																									"
						+ "	"+footer2+" 																																					"
						+ "	"+footer);
				Font font28 = FontFactory.getFont("TIMES_ROMAN");
				font28.setStyle(Font.BOLD);
				font28.setSize(12);
				chunk28.setFont(font28);
				Paragraph paragraph28 = new Paragraph();
				paragraph28.add(chunk28);
				paragraph28.setAlignment(Element.ALIGN_LEFT);
				// paragraph28.setSpacingAfter(5);
				paragraph28.setSpacingBefore(30);

				document.add(Chunk.NEWLINE); // Something like in HTML :-)

				table.setWidths(columnWidths);
				tablea.setWidths(columnWidths);
				tableb.setWidths(columnWidths);
				table1.setWidths(columnWidths);
				table2.setWidths(columnWidths);
				table3.setWidths(columnWidths);
				table4.setWidths(columnWidths);

				document.add(paragraph);
				document.add(paragraph1);
				document.add(paragraph2);
				document.add(paragraph3);
//				document.add(paragraph4);

				document.add(table);
				document.add(tablea);
				document.add(tableb);
				document.add(table1);
				document.add(table2);
				document.add(table3);
				document.add(table4);

				document.add(paragraph28);
				document.newPage();
				spaceBefore = -56;
				srNo = 1;
			}
			
			document.close();
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
