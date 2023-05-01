package org.com.maauli;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.List;
import java.util.Map;

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
//import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.PageSize;

public class AdmissionRegisterPDF {
	private static AdmissionRegisterPDF admissionRegisterPDF = new AdmissionRegisterPDF();
	
	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private AdmissionRegisterPDF() {
	}

	/* Static 'instance' method */
	public static AdmissionRegisterPDF getInstance() {
		return admissionRegisterPDF;
	}
	
	/* Other methods protected by singleton-ness */
	protected static void getAdmissionRegisterPDF(SessionData sessionData, List<String> passGrList) {

		String fileName = "", fileAddress = "", schoolName = "", school1 = "", school2 = "", schoolTaluka = "", schoolDistrict = "";
		Common cm = new Common();
		LinkedHashMap<String, LinkedHashMap<String, String>> studentDetailsMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		LinkedHashMap<String, String> grDetailMap = new LinkedHashMap<String, String>();
		boolean fileOpenFlag = true;
		Logger logger = Logger.getLogger(AdmissionRegisterPDF.class.getName());
		DBValidate dbValidate = new DBValidate();
		int s = 0;
		
		try {
			ResourceBundle bundle    		= ResourceBundle.getBundle("org.com.accesser.school"); 
			String path = cm.createTodayFolder(cm.getDriveName() + bundle.getString("ADMISSION_REGISTER_PDF_PATH_"+sessionData.getDBName()),true)+"/";
			schoolName = bundle.getString("BONAFIDE_HEADER_"+sessionData.getAppType());
			schoolTaluka = bundle.getString(sessionData.getAppType()+"_TALUKA");
			schoolDistrict = bundle.getString(sessionData.getAppType()+"_DISTRICT");
			
			List schoolList = cm.breakSentence(schoolName, 35);
			if(schoolList != null){
				school1 = schoolList.get(0).toString().trim();
				if(schoolList.size() > 1){
					school2 = schoolList.get(1).toString().trim();
					school1 += " \n                          "+ school2;
				}
			}
			
			if(dbValidate.connectDatabase(sessionData)){
			studentDetailsMap = dbValidate.findStudentDetails(sessionData, passGrList);
			}
			
			fileName  = "extract_of_admission_register_"+cm.timeInMillis()+".pdf";
			fileAddress = path+fileName;
			File file = new File(fileAddress);
			FileOutputStream fileout = new FileOutputStream(file);
			
			Document document = new Document(PageSize.A4.rotate());
			PdfWriter.getInstance(document, fileout);

			Set setStudent = studentDetailsMap.entrySet();
			Iterator iStudent = setStudent.iterator();
			while(iStudent.hasNext()) {
				Map.Entry meGr = (Map.Entry)iStudent.next();
				grDetailMap = (LinkedHashMap<String, String>) meGr.getValue();
				
				if(grDetailMap.get("dateOfLeaving").equalsIgnoreCase("") || grDetailMap.get("dateOfLeaving").equalsIgnoreCase("-")){
					continue;
				}
				
				Chunk chunkHeader = new Chunk("EXTRACT OF GENERAL REGISTER (PRAVESH NIRGAM UTARA) \n");
				Font font = FontFactory.getFont("TIMES_ROMAN");
				font.setStyle(Font.BOLD);
				font.setSize(18);
				chunkHeader.setFont(font);
				Paragraph paragraph = new Paragraph();
				paragraph.add(chunkHeader);
				paragraph.setAlignment(Element.ALIGN_CENTER);
//				paragraph.setSpacingBefore(5);
				
				if(s==0){
					paragraph.setSpacingBefore(5);
				}
				else{
					paragraph.setSpacingBefore(-10);
				}
				s += 1;

				Chunk chunkHeader1 = new Chunk("School Name : \n");
				Font font1 = FontFactory.getFont("TIMES_ROMAN");
				font1.setStyle(Font.BOLD);
				font1.setSize(14);
				chunkHeader1.setFont(font1);
				Paragraph paragraph1 = new Paragraph();
				paragraph1.add(chunkHeader1);
				paragraph1.setAlignment(Element.ALIGN_LEFT);
				paragraph1.setSpacingBefore(5);

				Chunk chunkHeader2 = new Chunk("																									"
						+ "	"+school1+" \n");
				Font fonta = FontFactory.getFont("TIMES_ROMAN");
				fonta.setStyle(Font.NORMAL);
				fonta.setSize(14);
				chunkHeader2.setFont(fonta);
				Paragraph paragrapha = new Paragraph();
				paragrapha.add(chunkHeader2);
				paragrapha.setAlignment(Element.ALIGN_LEFT);
				paragrapha.setSpacingBefore(-16);

				Chunk chunk2 = new Chunk("																																																																																					"
						+ "	Taluka :");
				Font font2 = FontFactory.getFont("TIMES_ROMAN");
				font2.setStyle(Font.BOLD);
				font2.setSize(14);
				chunk2.setFont(font2);
				Paragraph paragraph2 = new Paragraph();
				paragraph2.add(chunk2);
				paragraph2.setAlignment(Element.ALIGN_LEFT);
				if(school2.equalsIgnoreCase("")){
					paragraph2.setSpacingBefore(-16);
				}
				else{
					paragraph2.setSpacingBefore(-33);
				}

				Chunk chunkb = new Chunk("																																																																																																			"
						+ "	"+schoolTaluka);
				Font fontb = FontFactory.getFont("TIMES_ROMAN");
				fontb.setStyle(Font.NORMAL);
				fontb.setSize(14);
				chunkb.setFont(fontb);
				Paragraph paragraphb = new Paragraph();
				paragraphb.add(chunkb);
				paragraphb.setAlignment(Element.ALIGN_LEFT);
				paragraphb.setSpacingBefore(-16);

				Chunk chunk3 = new Chunk("																																																																																																																												"
						+ "	District : ");
				Font font3 = FontFactory.getFont("TIMES_ROMAN");
				font3.setStyle(Font.BOLD);
				font3.setSize(14);
				chunk3.setFont(font3);
				Paragraph paragraph3 = new Paragraph();
				paragraph3.add(chunk3);
				paragraph3.setAlignment(Element.ALIGN_LEFT);
				paragraph3.setSpacingBefore(-16);

				Chunk chunkc = new Chunk("																																																																																																																																											"
						+ "	"+schoolDistrict);
				Font fontc = FontFactory.getFont("TIMES_ROMAN");
				fontc.setStyle(Font.NORMAL);
				fontc.setSize(14);
				chunkc.setFont(fontc);
				Paragraph paragraphc = new Paragraph();
				paragraphc.add(chunkc);
				paragraphc.setAlignment(Element.ALIGN_LEFT);
				paragraphc.setSpacingBefore(-16);

				Chunk chunk4 = new Chunk("																																																																																																																																																																		"
						+ "	Date :");
				Font font4 = FontFactory.getFont("TIMES_ROMAN");
				font4.setStyle(Font.BOLD);
				font4.setSize(14);
				chunk4.setFont(font4);
				Paragraph paragraph4 = new Paragraph();
				paragraph4.add(chunk4);
				paragraph4.setAlignment(Element.ALIGN_LEFT);
				paragraph4.setSpacingBefore(-16);

				Chunk chunkd = new Chunk("																																																																																																																																																																												"
						+ "	 "+cm.getCurrentDate());
				Font fontd = FontFactory.getFont("TIMES_ROMAN");
				fontd.setStyle(Font.NORMAL);
				fontd.setSize(14);
				chunkd.setFont(fontd);
				Paragraph paragraphd = new Paragraph();
				paragraphd.add(chunkd);
				paragraphd.setAlignment(Element.ALIGN_LEFT);
				paragraphd.setSpacingBefore(-16);

				document.newPage();
				// table.setSpacingBefore(20.0f); // Space Before table starts, like
				// margin-top in CSS
				// table.setSpacingAfter(300.0f);

				PdfPTable table1 = new PdfPTable(11);

//				table1.setWidthPercentage(100);
				table1.setTotalWidth(780);
				table1.setLockedWidth(true);
				
				// table1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table1.setSpacingBefore(22.0f);

				PdfPCell cell301 = new PdfPCell(new Paragraph("Gr. No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				cell301.setColspan(1);
				// cell301.setRowspan (2);
				cell301.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell301.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell301.setPadding (10.0f);
				// cell301.setBackgroundColor (new BaseColor (110, 110, 110));
				table1.addCell(cell301);

				PdfPCell cell302 = new PdfPCell(
						new Paragraph("Student Name", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				cell302.setColspan(1);
				// cell302.setRowspan (2);
				cell302.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell302.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell302.setPadding (10.0f);
				// cell302.setBackgroundColor (new BaseColor (110, 110, 110));
				table1.addCell(cell302);

				PdfPCell cell303 = new PdfPCell(
						new Paragraph("Religion and Caste", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				cell303.setColspan(1);
				// cell303.setRowspan (2);
				cell303.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell303.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell303.setPadding (10.0f);
				// cell303.setBackgroundColor (new BaseColor (110, 110, 110));
				table1.addCell(cell303);

				PdfPCell cell304 = new PdfPCell(
						new Paragraph("Birth Place / Taluka / District ", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				cell304.setColspan(1);
				// cell304.setRowspan (2);
				cell304.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell304.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell304.setPadding (10.0f);
				// cell304.setBackgroundColor (new BaseColor (99, 99, 99));
				table1.addCell(cell304);

				PdfPCell cell305 = new PdfPCell(
						new Paragraph("Birth Date	(Figure & Words)", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				cell305.setColspan(1);
				// cell305.setRowspan (2);
				cell305.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell305.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell305.setPadding (10.0f);
				// cell305.setBackgroundColor (new BaseColor (99, 99, 99));
				table1.addCell(cell305);

				PdfPCell cell306 = new PdfPCell(
						new Paragraph("Last School", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				cell306.setColspan(1);
				// cell306.setRowspan (2);
				cell306.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell306.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell306.setPadding (10.0f);
				// cell306.setBackgroundColor (new BaseColor (99, 99, 99));
				table1.addCell(cell306);

				PdfPCell cell307 = new PdfPCell(
						new Paragraph("Date of Admission", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				cell307.setColspan(1);
				// cell307.setRowspan (2);
				cell307.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell307.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell307.setPadding (10.0f);
				// cell307.setBackgroundColor (new BaseColor (99, 99, 99));
				table1.addCell(cell307);

				PdfPCell cell308 = new PdfPCell(
						new Paragraph("Admitted Std.", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				cell308.setColspan(1);
				// cell308.setRowspan (2);
				cell308.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell308.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell308.setPadding (10.0f);
				// cell308.setBackgroundColor (new BaseColor (99, 99, 99));
				table1.addCell(cell308);

				PdfPCell cell309 = new PdfPCell(
						new Paragraph("Leaving Std.", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				cell309.setColspan(1);
				// cell309.setRowspan (2);
				cell309.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell309.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell309.setPadding (10.0f);
				// cell309.setBackgroundColor (new BaseColor (99, 99, 99));
				table1.addCell(cell309);

				PdfPCell cell310 = new PdfPCell(
						new Paragraph("Date of Leaving ", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				cell310.setColspan(1);
				// cell310.setRowspan (2);
				cell310.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell310.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell310.setPadding (10.0f);
				// cell310.setBackgroundColor (new BaseColor (99, 99, 99));
				table1.addCell(cell310);

				PdfPCell cell311 = new PdfPCell(new Paragraph("Remark ", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
				cell311.setColspan(1);
				cell311.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell311.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell311);

				int rowHeight = 15;
				table1.getRowHeight(rowHeight);

				PdfPCell cell312 = new PdfPCell(new Paragraph(".                                                       "+grDetailMap.get("grNo")+"                                             .", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//				cell312.setFixedHeight(300);
				cell312.setColspan(1);
				cell312.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell312.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell312.setRotation(90);
				table1.addCell(cell312);

				PdfPCell cell313 = new PdfPCell(
						new Paragraph(cm.FirstWordCap(grDetailMap.get("name")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell313.setColspan(1);
				cell313.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell313.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell313.setRotation(90);
				table1.addCell(cell313);

				PdfPCell cell314 = new PdfPCell(
						new Paragraph(cm.FirstWordCap(grDetailMap.get("religion"))+" "+ cm.FirstWordCap(grDetailMap.get("caste")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell314.setColspan(1);
				cell314.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell314.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell314.setRotation(90);
				table1.addCell(cell314);
				
				PdfPCell cell315 = new PdfPCell(new Paragraph(cm.FirstWordCap(grDetailMap.get("birthplace"))+", "
						+ "TALUKA: "+cm.FirstWordCap(grDetailMap.get("taluka"))+", "
						+ "\n DIST.: "+cm.FirstWordCap(grDetailMap.get("district"))+", STATE: "+cm.FirstWordCap(grDetailMap.get("state")),
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//				cell315.setBorder(PdfPCell.LEFT | PdfPCell.TOP | PdfPCell.BOTTOM);
				cell315.setColspan(1);
				cell315.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell315.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell315.setRotation(90);
				table1.addCell(cell315);

				PdfPCell cell316 = new PdfPCell(new Paragraph(grDetailMap.get("birthDate")+" & "+cm.FirstWordCap(grDetailMap.get("birthDateWords")),
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell316.setColspan(1);
				cell316.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell316.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell316.setRotation(90);
				table1.addCell(cell316);

				PdfPCell cell317 = new PdfPCell(
						new Paragraph(cm.FirstWordCap(grDetailMap.get("lastSchool")), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell317.setColspan(1);
				cell317.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell317.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell317.setRotation(90);
				table1.addCell(cell317);

				PdfPCell cell318 = new PdfPCell(
						new Paragraph(grDetailMap.get("admmissionDate"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell318.setColspan(1);
				cell318.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell318.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell318.setRotation(90);
				table1.addCell(cell318);

				PdfPCell cell319 = new PdfPCell(new Paragraph(grDetailMap.get("admittedStd"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell319.setColspan(1);
				cell319.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell319.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell319.setRotation(90);
				table1.addCell(cell319);

				PdfPCell cell320 = new PdfPCell(new Paragraph(grDetailMap.get("leavingStd"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell320.setColspan(1);
				cell320.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell320.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell320.setRotation(90);
				table1.addCell(cell320);

				PdfPCell cell321 = new PdfPCell(
						new Paragraph(grDetailMap.get("dateOfLeaving"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell321.setColspan(1);
				cell321.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell321.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell321.setRotation(90);
				table1.addCell(cell321);

				PdfPCell cell322 = new PdfPCell(
						new Paragraph("  "+grDetailMap.get("remark")+"  ",
								FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
				cell322.setColspan(1);
				cell322.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell322.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell322.setRotation(90);
				// cell322.setPadding (10.0f);
				table1.addCell(cell322);

				// document.newPage();
				// table.setSpacingBefore(300.0f); // Space Before table starts,
				// like margin-top in CSS
				table1.setSpacingAfter(0.0f);

				Chunk chunk5 = new Chunk("Progress : ");
				Font font5 = FontFactory.getFont("TIMES_ROMAN");
				font5.setStyle(Font.BOLD);
				font5.setSize(12);
				chunk5.setFont(font5);
				Paragraph paragraph5 = new Paragraph();
				paragraph5.add(chunk5);
				paragraph5.setAlignment(Element.ALIGN_LEFT);
				// paragraph5.setSpacingAfter(15);
				paragraph5.setSpacingBefore(0);

				Chunk chunk6 = new Chunk("																		"
						+ "	"+cm.FirstWordCap(grDetailMap.get("progress")));
				Font font6 = FontFactory.getFont("TIMES_ROMAN");
				font6.setStyle(Font.NORMAL);
				font6.setSize(12);
				chunk6.setFont(font6);
				Paragraph paragraph6 = new Paragraph();
				paragraph6.add(chunk6);
				paragraph6.setAlignment(Element.ALIGN_LEFT);
				paragraph6.setSpacingBefore(-16);

				Chunk chunk7 = new Chunk("																																																																																																	"
						+ "	Conduct :");
				Font font7 = FontFactory.getFont("TIMES_ROMAN");
				font7.setStyle(Font.BOLD);
				font7.setSize(12);
				chunk7.setFont(font7);
				Paragraph paragraph7 = new Paragraph();
				paragraph7.add(chunk7);
				paragraph7.setAlignment(Element.ALIGN_LEFT);
				paragraph7.setSpacingBefore(-16);

				Chunk chunk8 = new Chunk("																																																																																																																			"
						+ "	"+cm.FirstWordCap(grDetailMap.get("conduct")));
				Font font8 = FontFactory.getFont("TIMES_ROMAN");
				font8.setStyle(Font.NORMAL);
				font8.setSize(12);
				chunk8.setFont(font8);
				Paragraph paragraph8 = new Paragraph();
				paragraph8.add(chunk8);
				paragraph8.setAlignment(Element.ALIGN_LEFT);
				paragraph8.setSpacingBefore(-16);

				Chunk chunk9 = new Chunk("Head Master");
				Font font9 = FontFactory.getFont("TIMES_ROMAN");
				font9.setStyle(Font.BOLD);
				font9.setSize(12);
				chunk9.setFont(font9);
				Paragraph paragraph9 = new Paragraph();
				paragraph9.add(chunk9);
				paragraph9.setAlignment(Element.ALIGN_RIGHT);
				// paragraph9.setSpacingAfter(15);
				paragraph9.setSpacingBefore(15);

				document.open();// PDF document opened........

				document.add(Chunk.NEWLINE); // Something like in HTML :-)

				float[] columnWidths = new float[] { 3f, 5f, 6f, 8f, 8.5f, 5f, 7f, 6f, 6f, 6f, 5f };
				// table.setWidths(columnWidths);
				table1.setWidths(columnWidths);

				document.add(paragraph);
				document.add(paragraph1);
				document.add(paragrapha);
				document.add(paragraph2);
				document.add(paragraphb);
				document.add(paragraph3);
				document.add(paragraphc);
				document.add(paragraph4);
				document.add(paragraphd);

				document.add(table1);

				document.add(paragraph5);
				document.add(paragraph6);
				document.add(paragraph7);
				document.add(paragraph8);
				document.add(paragraph9);
				// document.add(addTableSpace);
//				document.add(Chunk.NEWLINE); // Something like in HTML :-)

				 document.newPage(); //Opened new page
				// document.add(list); //In the new page we are going to add list
			}

			document.close();

			fileout.close();

		} catch (Exception e) {
			cm.logException(e);
			fileOpenFlag = false;
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
		
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
			} catch (Exception e) {
				cm.logException(e);
			}
		}
	}
}
