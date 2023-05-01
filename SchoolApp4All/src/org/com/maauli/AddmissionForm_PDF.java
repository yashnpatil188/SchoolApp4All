package org.com.maauli;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.PageSize;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class AddmissionForm_PDF {

	private static AddmissionForm_PDF addmissionForm_PDF = new AddmissionForm_PDF();
	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	static Logger logger = Logger.getLogger(AddmissionForm_PDF.class.getName());

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private AddmissionForm_PDF() {
	}

	/* Static 'instance' method */
	public static AddmissionForm_PDF getInstance() {
		return addmissionForm_PDF;
	}

	/* Other methods protected by singleton-ness */
	protected static void getAddmissionForm_PDF(SessionData sessionData, LinkedHashMap<String, String> selectedGr,
			String std, String div) {
		String permanentAdd = "", residentAdd = "", birthPlace = "", lastSchool = "", hobbies = "";
		LinkedHashMap<String, LinkedHashMap<String, String>> studentDetailDb = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		DBValidate dbValidate = new DBValidate();
		Image img = null;
		try {
			Common commonObj = new Common();
			int image_pdf_student_pos_x = Integer.parseInt(bundle.getString("IMAGE_PDF_STUDENT_POS_X"));
			int image_pdf_student_pos_y = Integer.parseInt(bundle.getString("IMAGE_PDF_STUDENT_POS_Y"));
			float image_pdf_student_scalepercent = Float.parseFloat(bundle.getString("IMAGE_PDF_STUDENT_SCALEPERCENT"));
			
			String fileName = "Admission_Form_"+std +"_"+ div +"_"+ commonObj.timeInMillis() + ".pdf";
			String path = commonObj.createTodayFolder(commonObj.getDriveName() + "/" + sessionData.getSchoolName()
					+ "_app/ADMISSIONPDF/" + sessionData.getAppType() + "/", true) + "/";
			String studentImgPath = commonObj.createFolder(commonObj.getDriveName() + "/" + sessionData.getSchoolName()
			+ "_app/STUDENTIMAGE/" + sessionData.getAppType() + "/");
			String fileAddress = path + fileName;

			String bonafide_header_0 = bundle.getString("BONAFIDE_HEADER_0_" + sessionData.getAppType());
			String bonafide_header = bundle.getString("BONAFIDE_HEADER_" + sessionData.getAppType());
			OutputStream file = new FileOutputStream(new File(fileAddress));
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, file);
			boolean fileOpenFlag = false;
			
			document.open();// PDF document opened........

			studentDetailDb = dbValidate.viewStudentInfoMap(sessionData, selectedGr);
			Set set = studentDetailDb.entrySet();
			Iterator i = set.iterator();
			while (i.hasNext()) {
				byte b[] = null;
				
				Map.Entry me = (Map.Entry) i.next();
				LinkedHashMap<String, String> studentDetail = new LinkedHashMap<String, String>();
				studentDetail = (LinkedHashMap<String, String>) me.getValue();
				
				permanentAdd = commonObj.revertCommaApostrophy(studentDetail.get("permanentAdd"));
				residentAdd = commonObj.revertCommaApostrophy(studentDetail.get("residentAdd"));
				birthPlace = commonObj.revertCommaApostrophy(studentDetail.get("birthPlace"));
				lastSchool = commonObj.revertCommaApostrophy(studentDetail.get("lastSchool"));
				hobbies = commonObj.revertCommaApostrophy(studentDetail.get("hobbies"));

				Chunk chunk47 = new Chunk("G.R. NO.: ");
				Font font47 = FontFactory.getFont("TIMES_ROMAN");
				font47.setStyle(Font.NORMAL);
				font47.setSize(10);
				chunk47.setFont(font47);
				Paragraph paragraph47 = new Paragraph();
				paragraph47.add(chunk47);
				paragraph47.setAlignment(Element.ALIGN_LEFT);
				paragraph47.setSpacingBefore(0);

				Chunk chunk48 = new Chunk("																" + studentDetail.get("grNo"));
				Font font48 = FontFactory.getFont("TIMES_ROMAN");
				font48.setStyle(Font.NORMAL);
				font48.setSize(10);
				chunk48.setFont(font48);
				Paragraph paragraph48 = new Paragraph();
				paragraph48.add(chunk48);
				paragraph48.setAlignment(Element.ALIGN_LEFT);
				paragraph48.setSpacingBefore(-16);

				Chunk chunk49 = new Chunk(
						"																																										"
								+ "		STD: ");
				Font font49 = FontFactory.getFont("TIMES_ROMAN");
				font49.setStyle(Font.NORMAL);
				font49.setSize(10);
				chunk49.setFont(font49);
				Paragraph paragraph49 = new Paragraph();
				paragraph49.add(chunk49);
				paragraph49.setAlignment(Element.ALIGN_LEFT);
				paragraph49.setSpacingBefore(-16);

				Chunk chunk50 = new Chunk(
						"																																																			"
								+ "		" + studentDetail.get("presentStd")+"   DIV: "+studentDetail.get("presentDiv"));
				Font font50 = FontFactory.getFont("TIMES_ROMAN");
				font50.setStyle(Font.NORMAL);
				font50.setSize(10);
				chunk50.setFont(font50);
				Paragraph paragraph50 = new Paragraph();
				paragraph50.add(chunk50);
				paragraph50.setAlignment(Element.ALIGN_LEFT);
				paragraph50.setSpacingBefore(-16);

//				Chunk chunk51 = new Chunk(
//						"																																																																				"
//								+ "		DIV: ");
//				Font font51 = FontFactory.getFont("TIMES_ROMAN");
//				font51.setStyle(Font.NORMAL);
//				font51.setSize(10);
//				chunk51.setFont(font51);
//				Paragraph paragraph51 = new Paragraph();
//				paragraph51.add(chunk51);
//				paragraph51.setAlignment(Element.ALIGN_LEFT);
//				paragraph51.setSpacingBefore(-16);

//				Chunk chunk52 = new Chunk(
//						"      				  																																																																"
//								+ "			" + studentDetail.get("presentDiv"));
//				Font font52 = FontFactory.getFont("TIMES_ROMAN");
//				font52.setStyle(Font.NORMAL);
//				font52.setSize(10);
//				chunk52.setFont(font52);
//				Paragraph paragraph52 = new Paragraph();
//				paragraph52.add(chunk52);
//				paragraph52.setAlignment(Element.ALIGN_LEFT);
//				paragraph52.setSpacingBefore(-16);

				Chunk chunk53 = new Chunk(
						"														 																																																																																																											"
								+ "	DATE OF ADMISSION: ");
				Font font53 = FontFactory.getFont("TIMES_ROMAN");
				font53.setStyle(Font.NORMAL);
				font53.setSize(10);
				chunk53.setFont(font53);
				Paragraph paragraph53 = new Paragraph();
				paragraph53.add(chunk53);
				paragraph53.setAlignment(Element.ALIGN_LEFT);
				paragraph53.setSpacingBefore(-16);

				Chunk chunk54 = new Chunk(
						"														 																																																																																																																																																	"
								+ "		" + commonObj.formatyyyymmddtoddmmyyyy(studentDetail.get("doa")));
				Font font54 = FontFactory.getFont("TIMES_ROMAN");
				font54.setStyle(Font.NORMAL);
				font54.setSize(10);
				chunk54.setFont(font54);
				Paragraph paragraph54 = new Paragraph();
				paragraph54.add(chunk54);
				paragraph54.setAlignment(Element.ALIGN_LEFT);
				paragraph54.setSpacingBefore(-16);

				Chunk chunk55 = new Chunk("STUDENT UDISE: ");
				Font font55 = FontFactory.getFont("TIMES_ROMAN");
				font55.setStyle(Font.NORMAL);
				font55.setSize(10);
				chunk55.setFont(font55);
				Paragraph paragraph55 = new Paragraph();
				paragraph55.add(chunk55);
				paragraph55.setAlignment(Element.ALIGN_LEFT);
				paragraph55.setSpacingBefore(0);

				Chunk chunk56 = new Chunk(
						"																											"
								+ "				" + studentDetail.get("suid"));
				Font font56 = FontFactory.getFont("TIMES_ROMAN");
				font56.setStyle(Font.NORMAL);
				font56.setSize(10);
				chunk56.setFont(font56);
				Paragraph paragraph56 = new Paragraph();
				paragraph56.add(chunk56);
				paragraph56.setAlignment(Element.ALIGN_LEFT);
				paragraph56.setSpacingBefore(-16);

				Chunk chunk57 = new Chunk(
						"														 																																																																																																										"
								+ "		Adhaar Card:");
				Font font57 = FontFactory.getFont("TIMES_ROMAN");
				font57.setStyle(Font.NORMAL);
				font57.setSize(10);
				chunk57.setFont(font57);
				Paragraph paragraph57 = new Paragraph();
				paragraph57.add(chunk57);
				paragraph57.setAlignment(Element.ALIGN_LEFT);
				paragraph57.setSpacingBefore(-16);

				Chunk chunk58 = new Chunk(
						"      												 																				 																																																																																																					"
								+ "				" + studentDetail.get("adhaar"));
				Font font58 = FontFactory.getFont("TIMES_ROMAN");
				font58.setStyle(Font.NORMAL);
				font58.setSize(10);
				chunk58.setFont(font58);
				Paragraph paragraph58 = new Paragraph();
				paragraph58.add(chunk58);
				paragraph58.setAlignment(Element.ALIGN_LEFT);
				paragraph58.setSpacingBefore(-16);

				Chunk chunkHeader = new Chunk(bonafide_header_0);
				Font font = FontFactory.getFont("TIMES_ROMAN");
				font.setStyle(Font.NORMAL);
				font.setSize(12);
				chunkHeader.setFont(font);
				Paragraph paragraph = new Paragraph();
				paragraph.add(chunkHeader);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				paragraph.setSpacingBefore(16);

				Chunk chunkHeaderA = new Chunk(bonafide_header);
				Font fontA = FontFactory.getFont("TIMES_ROMAN");
				fontA.setStyle(Font.BOLD);
				fontA.setSize(12);
				chunkHeaderA.setFont(font);
				Paragraph paragraphA = new Paragraph();
				paragraphA.add(chunkHeaderA);
				paragraphA.setAlignment(Element.ALIGN_CENTER);
				paragraphA.setSpacingBefore(00);

				// Chunk chunkHeaderB = new Chunk("PHONE: 233242/25061");
				// Font fontB = FontFactory.getFont("TIMES_ROMAN");
				// fontB.setStyle(Font.BOLD);
				// fontB.setSize(12);
				// chunkHeaderB.setFont(font);
				// Paragraph paragraphB = new Paragraph();
				// paragraphB.add(chunkHeaderB);
				// paragraphB.setAlignment(Element.ALIGN_CENTER);
				// paragraphB.setSpacingBefore(0);

				Chunk chunkHeaderC = new Chunk("ADMISSION FORM");
				Font fontC = FontFactory.getFont("TIMES_ROMAN");
				fontC.setStyle(Font.BOLD);
				fontC.setSize(12);
				chunkHeaderC.setFont(fontC);
				Paragraph paragraphC = new Paragraph();
				paragraphC.add(chunkHeaderC);
				paragraphC.setAlignment(Element.ALIGN_CENTER);
				paragraphC.setSpacingBefore(15);

				String sectionHead = sessionData.getSectionName();
				if (sessionData.getAppName().contains("College")) {
					sectionHead = "JRC";
				}
				sectionHead = bundle.getString(sectionHead + "_SEC");
				Chunk chunkHeaderD = new Chunk(sectionHead.toUpperCase());
				Font fontD = FontFactory.getFont("TIMES_ROMAN");
				fontD.setStyle(Font.BOLD);
				fontD.setSize(12);
				chunkHeaderD.setFont(fontD);
				Paragraph paragraphD = new Paragraph();
				paragraphD.add(chunkHeaderD);
				paragraphD.setAlignment(Element.ALIGN_CENTER);
				paragraphD.setSpacingBefore(5);

				Chunk chunkHeaderE = new Chunk("ACADEMIC YEAR " + studentDetail.get("academicYear"));
				Font fontE = FontFactory.getFont("TIMES_ROMAN");
				fontE.setStyle(Font.BOLD);
				fontE.setSize(12);
				chunkHeaderE.setFont(fontE);
				Paragraph paragraphE = new Paragraph();
				paragraphE.add(chunkHeaderE);
				paragraphE.setAlignment(Element.ALIGN_CENTER);
				paragraphE.setSpacingBefore(5);
				paragraphE.setSpacingAfter(-60);

				document.newPage();

				PdfPTable table = new PdfPTable(12);

				table.setWidthPercentage(15);
				table.setHorizontalAlignment(Element.ALIGN_RIGHT);

				///////////////////
				String studentImagePath = commonObj.createFolder(commonObj.getDriveName() + "/" + sessionData.getSchoolName()
					+ "_app/STUDENTIMAGE/" + sessionData.getAppType() + "/");
				String studentImageName = studentDetail.get("grNo")+"_"+sessionData.getSectionName()+".jpg";
				try {
					b = commonObj.imageToByteArray(studentImagePath + studentImageName);
				} catch (IOException e2) {
					commonObj.logException(e2);
				}
				
//				try {
//					if (dbValidate.connectDatabase(sessionData)) {
//						b = dbValidate.getImage(sessionData, studentDetail.get("grNo"));
//					}
//				} catch (Exception e1) {
//					commonObj.logException(e1);
//				}
				
				if(b != null){
					commonObj.writeBytesToFileNio(b, studentImageName);
					
					img = Image.getInstance(studentImagePath + studentImageName);
					img.scalePercent(image_pdf_student_scalepercent);
					img.scaleToFit(85f, 140f);
					img.setAbsolutePosition(image_pdf_student_pos_x, image_pdf_student_pos_y);
//					img.setSpacingAfter(40);
//					img.scaleAbsolute(550, 150);
//				    document.add(img);
					
				}
				else {
					PdfPCell cell001 = new PdfPCell(
							new Paragraph("\n" + "\n" + "\n" + "PHOTO" + "\n" + "\n" + "\n" + "\n" + "\n",
									FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell001.setColspan(12);
					cell001.setRowspan(3);
					cell001.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell001.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell001);
				}
				///////////////////////////////

				// table.setSpacingBefore(300.0f); // Space Before table starts, like margin-top
				// in CSS
				table.setSpacingAfter(8.0f);

				document.newPage();

				PdfPTable table1 = new PdfPTable(12);

				table1.setWidthPercentage(100);
				table1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.setSpacingBefore(10.0f);

				PdfPCell cell002 = new PdfPCell(
						new Paragraph("SURNAME", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell002.setColspan(3);
				cell002.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell002.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell002);

				PdfPCell cell003 = new PdfPCell(
						new Paragraph("STUDENT NAME", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell003.setColspan(3);
				cell003.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell003.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell003);

				PdfPCell cell004 = new PdfPCell(
						new Paragraph("FATHER NAME", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell004.setColspan(3);
				cell004.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell004.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell004);

				PdfPCell cell005 = new PdfPCell(
						new Paragraph("MOTHER NAME", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell005.setColspan(3);
				cell005.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell005.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell005);

				PdfPCell cell006 = new PdfPCell(
						new Paragraph(studentDetail.get("lastName").toUpperCase(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell006.setColspan(3);
				cell006.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell006.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell006);

				PdfPCell cell007 = new PdfPCell(
						new Paragraph(studentDetail.get("firstName").toUpperCase(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell007.setColspan(3);
				cell007.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell007.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell007);

				PdfPCell cell008 = new PdfPCell(
						new Paragraph(studentDetail.get("fatherName").toUpperCase(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell008.setColspan(3);
				cell008.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell008.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell008);

				PdfPCell cell009 = new PdfPCell(
						new Paragraph(studentDetail.get("motherName").toUpperCase(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell009.setColspan(3);
				cell009.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell009.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table1.addCell(cell009);

				table1.setSpacingAfter(16.0f);

				document.newPage();

				Chunk chunk1 = new Chunk("RESIDENTIAL ADDRESS:");
				Font font1 = FontFactory.getFont("TIMES_ROMAN");
				font1.setStyle(Font.NORMAL);
				font1.setSize(10);
				chunk1.setFont(font1);
				Paragraph paragraph1 = new Paragraph();
				paragraph1.add(chunk1);
				paragraph1.setAlignment(Element.ALIGN_LEFT);
				paragraph1.setSpacingBefore(-16);

				Chunk chunk2 = new Chunk(
						"																																												"
								+ "	" + residentAdd);
				Font font2 = FontFactory.getFont("TIMES_ROMAN");
				font2.setStyle(Font.NORMAL);
				font2.setSize(10);
				chunk2.setFont(font2);
				Paragraph paragraph2 = new Paragraph();
				paragraph2.add(chunk2);
				paragraph2.setAlignment(Element.ALIGN_LEFT);
				paragraph2.setSpacingBefore(-16);

				Chunk chunk3 = new Chunk("PERMANENT ADDRESS:");
				Font font3 = FontFactory.getFont("TIMES_ROMAN");
				font3.setStyle(Font.NORMAL);
				font3.setSize(10);
				chunk3.setFont(font3);
				Paragraph paragraph3 = new Paragraph();
				paragraph3.add(chunk3);
				paragraph3.setAlignment(Element.ALIGN_LEFT);
				paragraph3.setSpacingBefore(0);

				Chunk chunk4 = new Chunk(
						"																																									"
								+ "			" + permanentAdd);
				Font font4 = FontFactory.getFont("TIMES_ROMAN");
				font4.setStyle(Font.NORMAL);
				font4.setSize(10);
				chunk4.setFont(font4);
				Paragraph paragraph4 = new Paragraph();
				paragraph4.add(chunk4);
				paragraph4.setAlignment(Element.ALIGN_LEFT);
				paragraph4.setSpacingBefore(-16);

				Chunk chunk5 = new Chunk("CONTACT NO.:");
				Font font5 = FontFactory.getFont("TIMES_ROMAN");
				font5.setStyle(Font.NORMAL);
				font5.setSize(10);
				chunk5.setFont(font5);
				Paragraph paragraph5 = new Paragraph();
				paragraph5.add(chunk5);
				paragraph5.setAlignment(Element.ALIGN_LEFT);
				paragraph5.setSpacingBefore(0);

				Chunk chunk6 = new Chunk(
						"																									"
								+ "			" + studentDetail.get("contact1") + " / " + studentDetail.get("contact2") + "");
				Font font6 = FontFactory.getFont("TIMES_ROMAN");
				font6.setStyle(Font.NORMAL);
				font6.setSize(10);
				chunk6.setFont(font6);
				Paragraph paragraph6 = new Paragraph();
				paragraph6.add(chunk6);
				paragraph6.setAlignment(Element.ALIGN_LEFT);
				paragraph6.setSpacingBefore(-16);

				Chunk chunk7 = new Chunk("DATE OF BIRTH:");
				Font font7 = FontFactory.getFont("TIMES_ROMAN");
				font7.setStyle(Font.NORMAL);
				font7.setSize(10);
				chunk7.setFont(font7);
				Paragraph paragraph7 = new Paragraph();
				paragraph7.add(chunk7);
				paragraph7.setAlignment(Element.ALIGN_LEFT);
				paragraph7.setSpacingBefore(0);

				Chunk chunk8 = new Chunk(
						"																								"
				+ "						" + commonObj.formatyyyymmddtoddmmyyyy(studentDetail.get("dob")) + " , " 
				+ studentDetail.get("dobWords"));
				
				Font font8 = FontFactory.getFont("TIMES_ROMAN");
				font8.setStyle(Font.NORMAL);
				font8.setSize(10);
				chunk8.setFont(font8);
				Paragraph paragraph8 = new Paragraph();
				paragraph8.add(chunk8);
				paragraph8.setAlignment(Element.ALIGN_LEFT);
				paragraph8.setSpacingBefore(-16);

				Chunk chunk9 = new Chunk("PLACE OF BIRTH:");
				Font font9 = FontFactory.getFont("TIMES_ROMAN");
				font9.setStyle(Font.NORMAL);
				font9.setSize(10);
				chunk9.setFont(font9);
				Paragraph paragraph9 = new Paragraph();
				paragraph9.add(chunk9);
				paragraph9.setAlignment(Element.ALIGN_LEFT);
				paragraph9.setSpacingBefore(0);

				Chunk chunk10 = new Chunk(
						"																														"
								+ "			" + commonObj.FirstWordCap(studentDetail.get("birthPlace")));
				Font font10 = FontFactory.getFont("TIMES_ROMAN");
				font10.setStyle(Font.NORMAL);
				font10.setSize(10);
				chunk10.setFont(font10);
				Paragraph paragraph10 = new Paragraph();
				paragraph10.add(chunk10);
				paragraph10.setAlignment(Element.ALIGN_LEFT);
				paragraph10.setSpacingBefore(-16);

				Chunk chunk11 = new Chunk(
						"																																																																							"
								+ "					TALUKA:");
				Font font11 = FontFactory.getFont("TIMES_ROMAN");
				font11.setStyle(Font.NORMAL);
				font11.setSize(10);
				chunk11.setFont(font11);
				Paragraph paragraph11 = new Paragraph();
				paragraph11.add(chunk11);
				paragraph11.setAlignment(Element.ALIGN_LEFT);
				paragraph11.setSpacingBefore(-16);

				Chunk chunk12 = new Chunk(
						"																							 																																																																		"
								+ "		" + commonObj.FirstWordCap(studentDetail.get("taluka")));
				Font font12 = FontFactory.getFont("TIMES_ROMAN");
				font12.setStyle(Font.NORMAL);
				font12.setSize(10);
				chunk12.setFont(font12);
				Paragraph paragraph12 = new Paragraph();
				paragraph12.add(chunk12);
				paragraph12.setAlignment(Element.ALIGN_LEFT);
				paragraph12.setSpacingBefore(-16);

				Chunk chunk13 = new Chunk("DISTRICT: ");
				Font font13 = FontFactory.getFont("TIMES_ROMAN");
				font13.setStyle(Font.NORMAL);
				font13.setSize(10);
				chunk13.setFont(font13);
				Paragraph paragraph13 = new Paragraph();
				paragraph13.add(chunk13);
				paragraph13.setAlignment(Element.ALIGN_LEFT);
				paragraph13.setSpacingBefore(0);

				Chunk chunk14 = new Chunk("																			"
						+ commonObj.FirstWordCap(studentDetail.get("district")));
				Font font14 = FontFactory.getFont("TIMES_ROMAN");
				font14.setStyle(Font.NORMAL);
				font14.setSize(10);
				chunk14.setFont(font14);
				Paragraph paragraph14 = new Paragraph();
				paragraph14.add(chunk14);
				paragraph14.setAlignment(Element.ALIGN_LEFT);
				paragraph14.setSpacingBefore(-16);

				Chunk chunk15 = new Chunk(
						"																																																																									"
								+ "					STATE:");
				Font font15 = FontFactory.getFont("TIMES_ROMAN");
				font15.setStyle(Font.NORMAL);
				font15.setSize(10);
				chunk15.setFont(font15);
				Paragraph paragraph15 = new Paragraph();
				paragraph15.add(chunk15);
				paragraph15.setAlignment(Element.ALIGN_LEFT);
				paragraph15.setSpacingBefore(-16);
				paragraph15.setSpacingAfter(0);

				Chunk chunk16 = new Chunk(
						"																							 																																																															"
								+ "					" + commonObj.FirstWordCap(studentDetail.get("state")));
				Font font16 = FontFactory.getFont("TIMES_ROMAN");
				font16.setStyle(Font.NORMAL);
				font16.setSize(10);
				chunk16.setFont(font16);
				Paragraph paragraph16 = new Paragraph();
				paragraph16.add(chunk16);
				paragraph16.setAlignment(Element.ALIGN_LEFT);
				paragraph16.setSpacingBefore(-16);
				paragraph16.setSpacingAfter(0);

				Chunk chunk17 = new Chunk(
						"																																																																																																																																																				"
								+ "				COUNTRY:");
				Font font17 = FontFactory.getFont("TIMES_ROMAN");
				font17.setStyle(Font.NORMAL);
				font17.setSize(10);
				chunk17.setFont(font17);
				Paragraph paragraph17 = new Paragraph();
				paragraph17.add(chunk17);
				paragraph17.setAlignment(Element.ALIGN_LEFT);
				paragraph17.setSpacingBefore(-16);

				Chunk chunk18 = new Chunk(
						"																																																																																																																																																																								"
								+ "				" + commonObj.FirstWordCap(studentDetail.get("country")));
				Font font18 = FontFactory.getFont("TIMES_ROMAN");
				font18.setStyle(Font.NORMAL);
				font18.setSize(10);
				chunk18.setFont(font18);
				Paragraph paragraph18 = new Paragraph();
				paragraph18.add(chunk18);
				paragraph18.setAlignment(Element.ALIGN_LEFT);
				paragraph18.setSpacingBefore(-16);

				Chunk chunk19 = new Chunk("NATIONALITY:");
				Font font19 = FontFactory.getFont("TIMES_ROMAN");
				font19.setStyle(Font.NORMAL);
				font19.setSize(10);
				chunk19.setFont(font19);
				Paragraph paragraph19 = new Paragraph();
				paragraph19.add(chunk19);
				paragraph19.setAlignment(Element.ALIGN_LEFT);
				paragraph19.setSpacingBefore(0);

				Chunk chunk20 = new Chunk(
						"																						"
								+ "					" + commonObj.FirstWordCap(studentDetail.get("nationality")));
				Font font20 = FontFactory.getFont("TIMES_ROMAN");
				font20.setStyle(Font.NORMAL);
				font20.setSize(10);
				chunk20.setFont(font20);
				Paragraph paragraph20 = new Paragraph();
				paragraph20.add(chunk20);
				paragraph20.setAlignment(Element.ALIGN_LEFT);
				paragraph20.setSpacingBefore(-16);

				Chunk chunk21 = new Chunk(
						"																																																																										"
								+ "				RELIGION:");
				Font font21 = FontFactory.getFont("TIMES_ROMAN");
				font21.setStyle(Font.NORMAL);
				font21.setSize(10);
				chunk21.setFont(font21);
				Paragraph paragraph21 = new Paragraph();
				paragraph21.add(chunk21);
				paragraph21.setAlignment(Element.ALIGN_LEFT);
				paragraph21.setSpacingBefore(-16);

				Chunk chunk22 = new Chunk(
						"																																																																																											"
								+ "						" + commonObj.FirstWordCap(studentDetail.get("religion")));
				Font font22 = FontFactory.getFont("TIMES_ROMAN");
				font22.setStyle(Font.NORMAL);
				font22.setSize(10);
				chunk22.setFont(font22);
				Paragraph paragraph22 = new Paragraph();
				paragraph22.add(chunk22);
				paragraph22.setAlignment(Element.ALIGN_LEFT);
				paragraph22.setSpacingBefore(-16);

				Chunk chunk23 = new Chunk("CATEGORY: ");
				Font font23 = FontFactory.getFont("TIMES_ROMAN");
				font23.setStyle(Font.NORMAL);
				font23.setSize(10);
				chunk23.setFont(font23);
				Paragraph paragraph23 = new Paragraph();
				paragraph23.add(chunk23);
				paragraph23.setAlignment(Element.ALIGN_LEFT);
				paragraph23.setSpacingBefore(0);

				Chunk chunk24 = new Chunk("																		"
						+ "				" + commonObj.FirstWordCap(studentDetail.get("category")));
				Font font24 = FontFactory.getFont("TIMES_ROMAN");
				font24.setStyle(Font.NORMAL);
				font24.setSize(10);
				chunk24.setFont(font24);
				Paragraph paragraph24 = new Paragraph();
				paragraph24.add(chunk24);
				paragraph24.setAlignment(Element.ALIGN_LEFT);
				paragraph24.setSpacingBefore(-16);

				Chunk chunk25 = new Chunk(
						"																																																																						"
								+ "								CASTE: ");
				Font font25 = FontFactory.getFont("TIMES_ROMAN");
				font25.setStyle(Font.NORMAL);
				font25.setSize(10);
				chunk25.setFont(font25);
				Paragraph paragraph25 = new Paragraph();
				paragraph25.add(chunk25);
				paragraph25.setAlignment(Element.ALIGN_LEFT);
				paragraph25.setSpacingBefore(-16);

				Chunk chunk26 = new Chunk(
						"																																																																																							"
								+ "						" + commonObj.FirstWordCap(studentDetail.get("cast")));
				Font font26 = FontFactory.getFont("TIMES_ROMAN");
				font26.setStyle(Font.NORMAL);
				font26.setSize(10);
				chunk26.setFont(font26);
				Paragraph paragraph26 = new Paragraph();
				paragraph26.add(chunk26);
				paragraph26.setAlignment(Element.ALIGN_LEFT);
				paragraph26.setSpacingBefore(-16);

				Chunk chunk27 = new Chunk(
						"																																																																																																																																	"
								+ "			MOTHER TONGUE: ");
				Font font27 = FontFactory.getFont("TIMES_ROMAN");
				font27.setStyle(Font.NORMAL);
				font27.setSize(10);
				chunk27.setFont(font27);
				Paragraph paragraph27 = new Paragraph();
				paragraph27.add(chunk27);
				paragraph27.setAlignment(Element.ALIGN_LEFT);
				paragraph27.setSpacingBefore(-16);

				Chunk chunk28 = new Chunk(
						"																																																																																																																																																																"
								+ "						" + commonObj.FirstWordCap(studentDetail.get("motherTongue")));
				Font font28 = FontFactory.getFont("TIMES_ROMAN");
				font28.setStyle(Font.NORMAL);
				font28.setSize(10);
				chunk28.setFont(font28);
				Paragraph paragraph28 = new Paragraph();
				paragraph28.add(chunk28);
				paragraph28.setAlignment(Element.ALIGN_LEFT);
				paragraph28.setSpacingBefore(-16);

				Chunk chunk29 = new Chunk("PARENT'S DETAILS: ");
				Font font29 = FontFactory.getFont("TIMES_ROMAN");
				font29.setStyle(Font.BOLD);
				font29.setSize(10);
				chunk29.setFont(font29);
				Paragraph paragraph29 = new Paragraph();
				paragraph29.add(chunk29);
				paragraph29.setAlignment(Element.ALIGN_LEFT);
				paragraph29.setSpacingBefore(5);

				Chunk chunk30 = new Chunk("NAME OF FATHER:");
				Font font30 = FontFactory.getFont("TIMES_ROMAN");
				font30.setStyle(Font.NORMAL);
				font30.setSize(10);
				chunk30.setFont(font30);
				Paragraph paragraph30 = new Paragraph();
				paragraph30.add(chunk30);
				paragraph30.setAlignment(Element.ALIGN_LEFT);
				paragraph30.setSpacingBefore(0);

				// Chunk chunk31 = new Chunk(" "
				// + " ");
				// Font font31 = FontFactory.getFont("TIMES_ROMAN");
				// font31.setStyle(Font.NORMAL);
				// font31.setSize(10);
				// chunk31.setFont(font31);
				// Paragraph paragraph31 = new Paragraph();
				// paragraph31.add(chunk31);
				// paragraph31.setAlignment(Element.ALIGN_LEFT);
				// paragraph31.setSpacingBefore(-16);

				Chunk chunk32 = new Chunk("PROFESSION:");
				Font font32 = FontFactory.getFont("TIMES_ROMAN");
				font32.setStyle(Font.NORMAL);
				font32.setSize(10);
				chunk32.setFont(font32);
				Paragraph paragraph32 = new Paragraph();
				paragraph32.add(chunk32);
				paragraph32.setAlignment(Element.ALIGN_LEFT);
				paragraph32.setSpacingBefore(0);

				// Chunk chunk33 = new Chunk(" "
				// + " ");
				// Font font33 = FontFactory.getFont("TIMES_ROMAN");
				// font33.setStyle(Font.NORMAL);
				// font33.setSize(10);
				// chunk33.setFont(font33);
				// Paragraph paragraph33 = new Paragraph();
				// paragraph33.add(chunk33);
				// paragraph33.setAlignment(Element.ALIGN_LEFT);
				// paragraph33.setSpacingBefore(-16);

				Chunk chunk34 = new Chunk(
						"																																																				"
								+ "					                                        CONTACT:");
				Font font34 = FontFactory.getFont("TIMES_ROMAN");
				font34.setStyle(Font.NORMAL);
				font34.setSize(10);
				chunk34.setFont(font34);
				Paragraph paragraph34 = new Paragraph();
				paragraph34.add(chunk34);
				paragraph34.setAlignment(Element.ALIGN_LEFT);
				paragraph34.setSpacingBefore(-16);

				// Chunk chunk35 = new Chunk(
				// " 9665166962");
				// Font font35 = FontFactory.getFont("TIMES_ROMAN");
				// font35.setStyle(Font.NORMAL);
				// font35.setSize(10);
				// chunk35.setFont(font35);
				// Paragraph paragraph35 = new Paragraph();
				// paragraph35.add(chunk35);
				// paragraph35.setAlignment(Element.ALIGN_LEFT);
				// paragraph35.setSpacingBefore(-16);

				Chunk chunk36 = new Chunk("NAME OF MOTHER:");
				Font font36 = FontFactory.getFont("TIMES_ROMAN");
				font36.setStyle(Font.NORMAL);
				font36.setSize(10);
				chunk36.setFont(font36);
				Paragraph paragraph36 = new Paragraph();
				paragraph36.add(chunk36);
				paragraph36.setAlignment(Element.ALIGN_LEFT);
				paragraph36.setSpacingBefore(0);

				// Chunk chunk37 = new Chunk(
				// " JAIVANTI JAGANNATH PATIL");
				// Font font37 = FontFactory.getFont("TIMES_ROMAN");
				// font37.setStyle(Font.NORMAL);
				// font37.setSize(10);
				// chunk37.setFont(font37);
				// Paragraph paragraph37 = new Paragraph();
				// paragraph37.add(chunk37);
				// paragraph37.setAlignment(Element.ALIGN_LEFT);
				// paragraph37.setSpacingBefore(-16);

				Chunk chunk38 = new Chunk("PROFESSION:");
				Font font38 = FontFactory.getFont("TIMES_ROMAN");
				font38.setStyle(Font.NORMAL);
				font38.setSize(10);
				chunk38.setFont(font38);
				Paragraph paragraph38 = new Paragraph();
				paragraph38.add(chunk38);
				paragraph38.setAlignment(Element.ALIGN_LEFT);
				paragraph38.setSpacingBefore(0);

				Chunk chunk39 = new Chunk(
						"																					"
								+ "				");
				Font font39 = FontFactory.getFont("TIMES_ROMAN");
				font39.setStyle(Font.NORMAL);
				font39.setSize(10);
				chunk39.setFont(font39);
				Paragraph paragraph39 = new Paragraph();
				paragraph39.add(chunk39);
				paragraph39.setAlignment(Element.ALIGN_LEFT);
				paragraph39.setSpacingBefore(-16);

				Chunk chunk40 = new Chunk(
						"																																																"
								+ "									                                        CONTACT:");
				Font font40 = FontFactory.getFont("TIMES_ROMAN");
				font40.setStyle(Font.NORMAL);
				font40.setSize(10);
				chunk40.setFont(font40);
				Paragraph paragraph40 = new Paragraph();
				paragraph40.add(chunk40);
				paragraph40.setAlignment(Element.ALIGN_LEFT);
				paragraph40.setSpacingBefore(-16);

				// Chunk chunk41 = new Chunk(" "
				// + " ");
				// Font font41 = FontFactory.getFont("TIMES_ROMAN");
				// font41.setStyle(Font.NORMAL);
				// font41.setSize(10);
				// chunk41.setFont(font41);
				// Paragraph paragraph41 = new Paragraph();
				// paragraph41.add(chunk41);
				// paragraph41.setAlignment(Element.ALIGN_LEFT);
				// paragraph41.setSpacingBefore(-16);

				Chunk chunk42 = new Chunk("STUDENT PREVIOUS ACADEMIC RECORD:");
				Font font42 = FontFactory.getFont("TIMES_ROMAN");
				font42.setStyle(Font.BOLD);
				font42.setSize(10);
				chunk42.setFont(font42);
				Paragraph paragraph42 = new Paragraph();
				paragraph42.add(chunk42);
				paragraph42.setAlignment(Element.ALIGN_LEFT);
				paragraph42.setSpacingBefore(5);

				Chunk chunk43 = new Chunk("NAME OF THE LAST SCHOOL:");
				Font font43 = FontFactory.getFont("TIMES_ROMAN");
				font43.setStyle(Font.NORMAL);
				font43.setSize(10);
				chunk43.setFont(font43);
				Paragraph paragraph43 = new Paragraph();
				paragraph43.add(chunk43);
				paragraph43.setAlignment(Element.ALIGN_LEFT);
				paragraph43.setSpacingBefore(0);

				Chunk chunk44 = new Chunk(
						"																																														"
								+ "							" + studentDetail.get("lastSchool"));
				Font font44 = FontFactory.getFont("TIMES_ROMAN");
				font44.setStyle(Font.NORMAL);
				font44.setSize(10);
				chunk44.setFont(font44);
				Paragraph paragraph44 = new Paragraph();
				paragraph44.add(chunk44);
				paragraph44.setAlignment(Element.ALIGN_LEFT);
				paragraph44.setSpacingBefore(-16);

				Chunk chunk45 = new Chunk("UDISE NO. OF LAST SCHOOL: ");
				Font font45 = FontFactory.getFont("TIMES_ROMAN");
				font45.setStyle(Font.NORMAL);
				font45.setSize(10);
				chunk45.setFont(font45);
				Paragraph paragraph45 = new Paragraph();
				paragraph45.add(chunk45);
				paragraph45.setAlignment(Element.ALIGN_LEFT);
				paragraph45.setSpacingBefore(0);

				// Chunk chunk46 = new Chunk(
				// " 27210201406");
				// Font font46 = FontFactory.getFont("TIMES_ROMAN");
				// font46.setStyle(Font.NORMAL);
				// font46.setSize(10);
				// chunk46.setFont(font46);
				// Paragraph paragraph46 = new Paragraph();
				// paragraph46.add(chunk46);
				// paragraph46.setAlignment(Element.ALIGN_LEFT);
				// paragraph46.setSpacingBefore(-16);

				PdfPTable table2 = new PdfPTable(12);

				table2.setWidthPercentage(100);
				table2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.setSpacingBefore(20.0f);

				PdfPCell cell051 = new PdfPCell(
						new Paragraph("BOARD", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell051.setColspan(2);
				cell051.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell051.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell051);

				PdfPCell cell052 = new PdfPCell(new Paragraph("STD", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell052.setColspan(2);
				cell052.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell052.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell052);

				PdfPCell cell053 = new PdfPCell(
						new Paragraph("EXAM MONTH", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell053.setColspan(2);
				cell053.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell053.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell053);

				PdfPCell cell052A = new PdfPCell(
						new Paragraph("MARKS OBTAINED", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell052A.setColspan(2);
				cell052A.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell052A.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell052A);

				PdfPCell cell053A = new PdfPCell(
						new Paragraph("PERCENTAGE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell053A.setColspan(2);
				cell053A.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell053A.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell053A);

				PdfPCell cell052B = new PdfPCell(
						new Paragraph("GRADE/CLASS", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell052B.setColspan(2);
				cell052B.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell052B.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell052B);

				PdfPCell cell053B = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell053B.setColspan(2);
				cell053B.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell053B.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell053B);

				PdfPCell cell053C = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell053C.setColspan(2);
				cell053C.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell053C.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell053C);

				PdfPCell cell054 = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell054.setColspan(2);
				cell054.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell054.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell054);

				PdfPCell cell055 = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell055.setColspan(2);
				cell055.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell055.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell055);

				PdfPCell cell056 = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell056.setColspan(2);
				cell056.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell056.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell056);

				PdfPCell cell057 = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell057.setColspan(2);
				cell057.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell057.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell057);

				/*
				 * PdfPCell cell058 = new PdfPCell (new Paragraph ("9876543210",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))); cell058.setColspan (1);
				 * cell058.setHorizontalAlignment (Element.ALIGN_CENTER);
				 * cell058.setVerticalAlignment (Element.ALIGN_MIDDLE); table2.addCell(cell058);
				 * 
				 * PdfPCell cell057A = new PdfPCell (new Paragraph ("FATHER EMAIL ID",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))); cell057A.setColspan (1);
				 * cell057A.setHorizontalAlignment (Element.ALIGN_CENTER);
				 * cell057A.setVerticalAlignment (Element.ALIGN_MIDDLE);
				 * table2.addCell(cell057A);
				 * 
				 * PdfPCell cell058A = new PdfPCell (new Paragraph (" ",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))); cell058A.setColspan (1);
				 * cell058A.setHorizontalAlignment (Element.ALIGN_CENTER);
				 * cell058A.setVerticalAlignment (Element.ALIGN_MIDDLE);
				 * table2.addCell(cell058A);
				 * 
				 * PdfPCell cell059 = new PdfPCell (new Paragraph ("MOTHER FIRST NAME",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))); cell059.setColspan (1);
				 * cell059.setHorizontalAlignment (Element.ALIGN_CENTER);
				 * cell059.setVerticalAlignment (Element.ALIGN_MIDDLE); table2.addCell(cell059);
				 * 
				 * PdfPCell cell060 = new PdfPCell (new Paragraph ("JAIVANTI",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))); cell060.setColspan (3);
				 * cell060.setHorizontalAlignment (Element.ALIGN_CENTER);
				 * cell060.setVerticalAlignment (Element.ALIGN_MIDDLE); table2.addCell(cell060);
				 * 
				 * PdfPCell cell059A = new PdfPCell (new Paragraph ("MOTHER MIDDLE NAME",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))); cell059A.setColspan (1);
				 * cell059A.setHorizontalAlignment (Element.ALIGN_CENTER);
				 * cell059A.setVerticalAlignment (Element.ALIGN_MIDDLE);
				 * table2.addCell(cell059A);
				 * 
				 * PdfPCell cell060A = new PdfPCell (new Paragraph ("JAGANNATH",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))); cell060A.setColspan (3);
				 * cell060A.setHorizontalAlignment (Element.ALIGN_CENTER);
				 * cell060A.setVerticalAlignment (Element.ALIGN_MIDDLE);
				 * table2.addCell(cell060A);
				 * 
				 * 
				 * 
				 */

				table2.setSpacingAfter(8.0f);

//				document.newPage();

//				document.open();// PDF document opened........

				// document.add(image);

//				document.add(Chunk.NEWLINE); // Something like in HTML :-)

				// document.add(new Paragraph("Dear Java4s.com"));
				// document.add(new Paragraph("Document Generated On - "+new
				// Date().toString()));

				float[] columnWidths = new float[] { 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f };
				table.setWidths(columnWidths);

				document.add(paragraph47);
				document.add(paragraph48);
				document.add(paragraph49);
				document.add(paragraph50);
//				document.add(paragraph51);
//				document.add(paragraph52);
				document.add(paragraph53);
				document.add(paragraph54);
				document.add(paragraph55);
				document.add(paragraph56);
				document.add(paragraph57);
				document.add(paragraph58);
				document.add(paragraph);
				document.add(paragraphA);
				// document.add(paragraphB);
				document.add(paragraphC);
				document.add(paragraphD);
				document.add(paragraphE);
				if(b != null) {
					table1.setSpacingBefore(100.0f);
					document.add(img);
				}
				else {
					document.add(table);
				}
				document.add(table1);
				document.add(paragraph1);
				document.add(paragraph2);
				document.add(paragraph3);
				document.add(paragraph4);
				document.add(paragraph5);
				document.add(paragraph6);
				document.add(paragraph7);
				document.add(paragraph8);
				document.add(paragraph9);
				document.add(paragraph10);
				document.add(paragraph11);
				document.add(paragraph12);
				document.add(paragraph13);
				document.add(paragraph14);
				document.add(paragraph15);
				document.add(paragraph16);
				document.add(paragraph17);
				document.add(paragraph18);
				document.add(paragraph19);
				document.add(paragraph20);
				document.add(paragraph21);
				document.add(paragraph22);
				document.add(paragraph23);
				document.add(paragraph24);
				document.add(paragraph25);
				document.add(paragraph26);
				document.add(paragraph27);
				document.add(paragraph28);
				document.add(paragraph29);
				document.add(paragraph30);
				// document.add(paragraph31);
				document.add(paragraph32);
				// document.add(paragraph33);
				document.add(paragraph34);
				// document.add(paragraph35);
				document.add(paragraph36);
				// document.add(paragraph37);
				document.add(paragraph38);
				document.add(paragraph39);
				document.add(paragraph40);
				// document.add(paragraph41);
				document.add(paragraph42);
				document.add(paragraph43);
				document.add(paragraph44);
				document.add(paragraph45);
				// document.add(paragraph46);
				document.add(table2);

				document.newPage();
				
//				document.add(Chunk.NEWLINE); // Something like in HTML :-)
				fileOpenFlag = true;
			}
			
			document.close();
			file.close();
			
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
					logger.info("Done");
				} catch (Exception e) {
					commonObj.logException(e);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
