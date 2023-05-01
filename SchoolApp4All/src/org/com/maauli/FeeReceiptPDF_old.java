package org.com.maauli;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.List;
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
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.PageSize;

public class FeeReceiptPDF_old {

	private static FeeReceiptPDF_old feeReceiptPDF = new FeeReceiptPDF_old();
	private static Common commonObj = new Common();
	private static String path = "";
	private static String fee_header_0 = "";
	private static String fee_header_1 = "";
	private static String fee_header_2 = "";
	private static int fee_row_count = 0;
	private static String fileName,fileAddress = "";
	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school"); 
    static Logger logger = Logger.getLogger(FeeReceiptPDF_old.class.getName());
    
	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private FeeReceiptPDF_old() {
	}

	/* Static 'instance' method */
	public static FeeReceiptPDF_old getInstance() {
		return feeReceiptPDF;
	}

	/* Other methods protected by singleton-ness */
	protected static void getFeeReceiptPDF(SessionData sessionData,
			LinkedHashMap<String, LinkedHashMap<String, String>> feesPaymentMap, String penaltyAmount, String bank,
			String chequeDDNo, String paymentMode, String grNo, String name, String std, String div, String academic,
			String feeStatus, double totalAmount, String chequeDD_date, LinkedHashMap concessionMap,
			double concessionAmount, String category, int count, String rollNo, String feesForMonths, 
			LinkedHashMap<String, LinkedHashMap<String, String>> selectedStudentMap, boolean headerRadio,
			LinkedHashMap<String, LinkedHashMap<String, String>> studentOptMap, String shortName, 
			LinkedHashMap<String, LinkedHashMap<String, String>> feesHeadMap, String frequency, String displayDate, 
			String remark, String status) {
		
		LinkedHashMap<String, String> receiptHeaderDetails = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> displayFeesMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, LinkedHashMap<String, String>> receiptHeaderMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		boolean fileOpenFlag = false;
		int spacingBefore = 5;
		float tableSpacingBefore = -205.0f;
		String concession = "", penalty = "", total = "", remark2 = "";
		try {
			
			if(feesHeadMap != null) {
				spacingBefore = 25;
				tableSpacingBefore = -205.0f;
			}
			totalAmount = Double.parseDouble(String.format("%.2f", totalAmount));
			
			Date currentDate = new Date();
			path = commonObj.createTodayFolder(commonObj.getDriveName() + bundle.getString("FEERECEIPT_PDF_PATH_"+sessionData.getDBName()),true)+"/";
			fileName  = "FeeReceipt_"+grNo+"_"+commonObj.getCurrentDatein_dd_MMM_yyyy()+"_"+commonObj.timeInMillis()+".pdf";
			logger.info("Pdf will be created for file.."+fileName);
			fileAddress = path+fileName;
			fee_header_0 = bundle.getString("FEES_HEADER_0_"+sessionData.getAppType());
	        fee_header_1 = bundle.getString("FEES_HEADER_1_"+sessionData.getAppType());
	        fee_header_2 = bundle.getString("FEES_HEADER_2_"+sessionData.getAppType());
	        fee_row_count = Integer.parseInt(bundle.getString("FEE_ROW_COUNT"));
	        
	        if(displayDate.equalsIgnoreCase("")) {
				displayDate = commonObj.dateToDDMMYYYY(currentDate);
			}
	        if(sessionData.getDBName().equalsIgnoreCase("sanskaranjurschool") && headerRadio){
	        	fee_header_1 = "\u0020 \u0020 \u0020 \u0020 \u0020 Dnyanjyot Samaj Prabhodan Mandal";
		        fee_header_2 = "\u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 Anjur. Tal. Bhiwandi - 421302";
	        }
			
	        try {
	        	int space = 0;
	        	DBValidate dbValidate = new DBValidate();
				if(dbValidate.connectDatabase(sessionData)){
					receiptHeaderMap = dbValidate.fetchHeaderMap(sessionData);
					if(receiptHeaderMap.get(shortName) != null) {
						receiptHeaderDetails = (LinkedHashMap<String, String>) receiptHeaderMap.get(shortName);
					}
					if(receiptHeaderDetails.get("mainHead") != null && !receiptHeaderDetails.get("mainHead").equalsIgnoreCase("")) {
						fee_header_1 = commonObj.revertCommaApostrophy(receiptHeaderDetails.get("mainHead"));
						if(fee_header_1.length() < 40) {
							space = (40 - fee_header_1.length());
						}
						for(int i = 0; i < space; i++) {
							fee_header_1 = " " + fee_header_1;
						}
						space = 0;
						fee_header_2 = commonObj.revertCommaApostrophy(receiptHeaderDetails.get("addHead"));
						if(fee_header_2.length() < 40) {
							space = (40 - fee_header_2.length());
						}
						for(int i = 0; i < space; i++) {
							fee_header_2 = " " + fee_header_2;
						}
						space = 0;
						fee_header_0 = commonObj.revertCommaApostrophy(receiptHeaderDetails.get("institute"));
						if(fee_header_0.length() < 50) {
							space = (50 - fee_header_0.length());
						}
						for(int i = 0; i < space; i++) {
							fee_header_0 = " " + fee_header_0;
						}
					}
				}
			} catch (Exception e) {
				commonObj.logException(e);
	        }
	        
			OutputStream file = new FileOutputStream(new File(fileAddress));
			Document document = new Document(PageSize.A4.rotate());
			// Document document = new Document(PageSize.A4);
			// Document document = new Document();
//			PdfWriter.getInstance(document, file);
			PdfWriter writer = PdfWriter.getInstance(document, file);
			document.open();// PDF document opened........
			
			int s = 0;
			int before = -16;
			String[] optionalList;
			
			Set setStudent = selectedStudentMap.entrySet();
			Iterator iStudent = setStudent.iterator();
			while(iStudent.hasNext()) {
				// Now Insert Every Thing Into PDF Document
				document.add(Chunk.NEWLINE); // Something like in HTML :-)
				
				Map.Entry meGr = (Map.Entry)iStudent.next();
				LinkedHashMap<String, String> grDetailMap = new LinkedHashMap<String, String>();
				grDetailMap = (LinkedHashMap<String, String>) meGr.getValue();
				optionalList = new String[1];
				if(studentOptMap.get(meGr.getKey()) != null) {
					optionalList = ((LinkedHashMap<?, ?>) studentOptMap.get(meGr.getKey())).get("optional_fee").toString().split("\\|");
				}
				
				Chunk chunkHeader = new Chunk(fee_header_0);
				Font font = FontFactory.getFont("TIMES_ROMAN");
				font.setStyle(Font.NORMAL);
				font.setSize(8);
				chunkHeader.setFont(font);
				Paragraph paragraph = new Paragraph();
				paragraph.add(chunkHeader);
				paragraph.setAlignment(Element.ALIGN_LEFT);
				if(s==0){
					paragraph.setSpacingBefore(-16);
				}
				else{
					paragraph.setSpacingBefore(-33);
				}

				Chunk chunkHeader1 = new Chunk("																																																																	                                          														"
						+ "	"+fee_header_0);
				Font font1 = FontFactory.getFont("TIMES_ROMAN");
				font1.setStyle(Font.NORMAL);
				font1.setSize(8);
				chunkHeader1.setFont(font1);
				Paragraph paragraph1 = new Paragraph();
				paragraph1.add(chunkHeader1);
				paragraph1.setAlignment(Element.ALIGN_LEFT);
				paragraph1.setSpacingBefore(-16);

				Chunk chunkHeader2 = new Chunk("	                                                                                  																																																																																																																																																															"
						+ "	"+fee_header_0);
				Font fonta = FontFactory.getFont("TIMES_ROMAN");
				fonta.setStyle(Font.NORMAL);
				fonta.setSize(8);
				chunkHeader2.setFont(fonta);
				Paragraph paragrapha = new Paragraph();
				paragrapha.add(chunkHeader2);
				paragrapha.setAlignment(Element.ALIGN_LEFT);
				paragrapha.setSpacingBefore(-16);

				Chunk chunk2 = new Chunk(fee_header_1);
				Font font2 = FontFactory.getFont("TIMES_ROMAN");
				font2.setStyle(Font.NORMAL);
				font2.setSize(10);
				chunk2.setFont(font2);
				Paragraph paragraph2 = new Paragraph();
				paragraph2.add(chunk2);
				paragraph2.setAlignment(Element.ALIGN_LEFT);
				if(s==0){
					if(!fee_header_0.trim().equalsIgnoreCase(""))
						paragraph2.setSpacingBefore(-16+16);
					else
						paragraph2.setSpacingBefore(-16);
				}
				else{
					if(!fee_header_0.trim().equalsIgnoreCase(""))
						paragraph2.setSpacingBefore(-33+33);
					else
						paragraph2.setSpacingBefore(-33);
				}

				Chunk chunkb = new Chunk("                                                                                             "
						+ "   "+fee_header_1);
				Font fontb = FontFactory.getFont("TIMES_ROMAN");
				fontb.setStyle(Font.NORMAL);
				fontb.setSize(10);
				chunkb.setFont(fontb);
				Paragraph paragraphb = new Paragraph();
				paragraphb.add(chunkb);
				paragraphb.setAlignment(Element.ALIGN_LEFT);
				paragraphb.setSpacingBefore(-16);

				Chunk chunkc = new Chunk("	                                                                                              																																																																																															"
						+ "	"+fee_header_1);
				Font fontc = FontFactory.getFont("TIMES_ROMAN");
				fontc.setStyle(Font.NORMAL);
				fontc.setSize(10);
				chunkc.setFont(fontc);
				Paragraph paragraphc = new Paragraph();
				paragraphc.add(chunkc);
				paragraphc.setAlignment(Element.ALIGN_LEFT);
				paragraphc.setSpacingBefore(-16);

				Chunk chunkheader2 = new Chunk(fee_header_2);
				Font fontheader2 = FontFactory.getFont("TIMES_ROMAN");
				fontheader2.setStyle(Font.NORMAL);
				fontheader2.setSize(10);
				chunkheader2.setFont(fontheader2);
				Paragraph paragraphHeader2 = new Paragraph();
				paragraphHeader2.add(chunkheader2);
				paragraphHeader2.setAlignment(Element.ALIGN_LEFT);

				Chunk chunkOfficeheader2 = new Chunk("                                                                                                   "+fee_header_2);
				Font fontOheader2 = FontFactory.getFont("TIMES_ROMAN");
				fontOheader2.setStyle(Font.NORMAL);
				fontOheader2.setSize(10);
				chunkOfficeheader2.setFont(fontOheader2);
				Paragraph paraOfficeHeader2 = new Paragraph();
				paraOfficeHeader2.add(chunkOfficeheader2);
				paraOfficeHeader2.setAlignment(Element.ALIGN_LEFT);
				paraOfficeHeader2.setSpacingBefore(-16);

				Chunk chunkTeachheader2 = new Chunk("		                                                                                               																																																																																																"+fee_header_2);
				Font fontTheader2 = FontFactory.getFont("TIMES_ROMAN");
				fontTheader2.setStyle(Font.NORMAL);
				fontTheader2.setSize(10);
				chunkTeachheader2.setFont(fontTheader2);
				Paragraph paraTechHeader2 = new Paragraph();
				paraTechHeader2.add(chunkTeachheader2);
				paraTechHeader2.setAlignment(Element.ALIGN_LEFT);
				paraTechHeader2.setSpacingBefore(-16);
				
				Chunk chunkr = new Chunk("																															"
						+ "	Office Copy");
				Font fontr = FontFactory.getFont("TIMES_ROMAN");
				fontr.setStyle(Font.BOLD);
				fontr.setSize(10);
				chunkr.setFont(fontr);
				Paragraph paragraphr = new Paragraph();
				paragraphr.add(chunkr);
				paragraphr.setAlignment(Element.ALIGN_LEFT);

				Chunk chunks = new Chunk("Teacher Copy");
				Font fonts = FontFactory.getFont("TIMES_ROMAN");
				fonts.setStyle(Font.BOLD);
				fonts.setSize(10);
				chunks.setFont(fonts);
				Paragraph paragraphs = new Paragraph();
				paragraphs.add(chunks);
				paragraphs.setAlignment(Element.ALIGN_CENTER);
				paragraphs.setSpacingBefore(-16);

				Chunk chunkt = new Chunk("																																																																																																	"
						+ "	Student Copy");
				Font fontt = FontFactory.getFont("TIMES_ROMAN");
				fontt.setStyle(Font.BOLD);
				fontt.setSize(10);
				chunkt.setFont(fontt);
				Paragraph paragrapht = new Paragraph();
				paragrapht.add(chunkt);
				paragrapht.setAlignment(Element.ALIGN_CENTER);
				paragrapht.setSpacingBefore(-16);

				Chunk chunkAcademic = new Chunk("                       Acedamic Year : "+academic
						+ "                                                         Acedamic Year : "+academic 
						+ "                                                         Acedamic Year : "+academic);
				Font fontAcademic = FontFactory.getFont("TIMES_ROMAN");
				fontAcademic.setStyle(Font.NORMAL);
				fontAcademic.setSize(10);
				chunkAcademic.setFont(fontAcademic);
				Paragraph paragraphAcademic = new Paragraph();
				paragraphAcademic.add(chunkAcademic);
				paragraphAcademic.setAlignment(Element.ALIGN_LEFT);
//				paragraphAcademic.setSpacingBefore(-16);
				paragraphAcademic.setSpacingAfter(10);
				
				Chunk chunk3 = new Chunk("																																																																																										"
						+ "	Date: "+displayDate);
				Font font3 = FontFactory.getFont("TIMES_ROMAN");
				font3.setStyle(Font.NORMAL);
				font3.setSize(7);
				chunk3.setFont(font3);
				Paragraph paragraph3 = new Paragraph();
				paragraph3.add(chunk3);
				paragraph3.setAlignment(Element.ALIGN_LEFT);
				paragraph3.setSpacingBefore(-8);

				Chunk chunk3a = new Chunk("Fee Receipt No. :  "+grDetailMap.get("receiptNo"));
				Font font3a = FontFactory.getFont("TIMES_ROMAN");
				font3a.setStyle(Font.NORMAL);
				font3a.setSize(7);
				// font3a.setColor(BaseColor.RED);
				chunk3a.setFont(font3a);
				Paragraph paragraph3a = new Paragraph();
				paragraph3a.add(chunk3a);
				paragraph3a.setAlignment(Element.ALIGN_LEFT);
				paragraph3a.setSpacingBefore(-16);
				paragraph3a.setSpacingAfter(0);

				Chunk chunkd = new Chunk("																																																																																																																																																																																																																																		"
						+ "	Date: "+displayDate);
				Font fontd = FontFactory.getFont("TIMES_ROMAN");
				fontd.setStyle(Font.NORMAL);
				fontd.setSize(7);
				chunkd.setFont(fontd);
				Paragraph paragraphd = new Paragraph();
				paragraphd.add(chunkd);
				paragraphd.setAlignment(Element.ALIGN_LEFT);
				paragraphd.setSpacingBefore(-16);

				Chunk chunke = new Chunk("																																																																																																																																									"
						+ "	Fee Receipt No. :  "+grDetailMap.get("receiptNo"));
				Font fonte = FontFactory.getFont("TIMES_ROMAN");
				fonte.setStyle(Font.NORMAL);
				fonte.setSize(7);
				chunke.setFont(fonte);
				Paragraph paragraphe = new Paragraph();
				paragraphe.add(chunke);
				paragraphe.setAlignment(Element.ALIGN_LEFT);
				paragraphe.setSpacingBefore(-16);
				paragraphe.setSpacingAfter(0);

				Chunk chunkf = new Chunk("																																																																																																																																																																																																																																																																																																																																																																														"
						+ "	Date: "+displayDate);
				Font fontf = FontFactory.getFont("TIMES_ROMAN");
				fontf.setStyle(Font.NORMAL);
				fontf.setSize(7);
				chunkf.setFont(fontf);
				Paragraph paragraphf = new Paragraph();
				paragraphf.add(chunkf);
				paragraphf.setAlignment(Element.ALIGN_LEFT);
				paragraphf.setSpacingBefore(-16);

				Chunk chunkg = new Chunk("																																																																																																																																																																																																																																																																																				"
						+ "	Fee Receipt No. :  "+grDetailMap.get("receiptNo"));
				Font fontg = FontFactory.getFont("TIMES_ROMAN");
				fontg.setStyle(Font.NORMAL);
				fontg.setSize(7);
				chunkg.setFont(fontg);
				Paragraph paragraphg = new Paragraph();
				paragraphg.add(chunkg);
				paragraphg.setAlignment(Element.ALIGN_LEFT);
				paragraphg.setSpacingBefore(-16);
				paragraphg.setSpacingAfter(0);

				Chunk chunk32 = new Chunk("Fees for : "+feesForMonths);
				Font font32 = FontFactory.getFont("TIMES_ROMAN");
				font32.setStyle(Font.NORMAL);
				font32.setSize(7);
				chunk32.setFont(font32);
				Paragraph paragraph32 = new Paragraph();
				paragraph32.add(chunk32);
				paragraph32.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraph32.setSpacingBefore(0);

				Chunk chunkh = new Chunk("																																																																																																																																									"
						+ "	Fees for : "+feesForMonths);
				Font fonth = FontFactory.getFont("TIMES_ROMAN");
				fonth.setStyle(Font.NORMAL);
				fonth.setSize(7);
				chunkh.setFont(fonth);
				Paragraph paragraphh = new Paragraph();
				paragraphh.add(chunkh);
				paragraphh.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraphh.setSpacingBefore(-16);

				Chunk chunki = new Chunk("																																																																																																																																																																																																																																																																																				"
						+ "	Fees for : "+feesForMonths);
				Font fonti = FontFactory.getFont("TIMES_ROMAN");
				fonti.setStyle(Font.NORMAL);
				fonti.setSize(7);
				chunki.setFont(fonti);
				Paragraph paragraphi = new Paragraph();
				paragraphi.add(chunki);
				paragraphi.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraphi.setSpacingBefore(-16);

				Chunk chunk4 = new Chunk("Name : "+commonObj.FirstWordCap(grDetailMap.get("name")));
				Font font4 = FontFactory.getFont("TIMES_ROMAN");
				font4.setStyle(Font.NORMAL);
				font4.setSize(7);
				chunk4.setFont(font4);
				Paragraph paragraph4 = new Paragraph();
				paragraph4.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraph4.add(chunk4);
				paragraph4.setSpacingBefore(0);

				Chunk chunkj = new Chunk("																																																																																																																																								"
						+ "		Name : "+commonObj.FirstWordCap(commonObj.FirstWordCap(grDetailMap.get("name"))));
				Font fontj = FontFactory.getFont("TIMES_ROMAN");
				fontj.setStyle(Font.NORMAL);
				fontj.setSize(7);
				chunkj.setFont(fontj);
				Paragraph paragraphj = new Paragraph();
				paragraphj.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraphj.add(chunkj);
				paragraphj.setSpacingBefore(-16);

				Chunk chunkk = new Chunk("																																																																																																																																																																																																																																																																																		"
						+ "			Name : "+commonObj.FirstWordCap(commonObj.FirstWordCap(grDetailMap.get("name"))));
				Font fontk = FontFactory.getFont("TIMES_ROMAN");
				fontk.setStyle(Font.NORMAL);
				fontk.setSize(7);
				chunkk.setFont(fontk);
				Paragraph paragraphk = new Paragraph();
				paragraphk.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraphk.add(chunkk);
				paragraphk.setSpacingBefore(-16);

				Chunk chunk41 = new Chunk("Std.: "+grDetailMap.get("std"));
				Font font41 = FontFactory.getFont("TIMES_ROMAN");
				font41.setStyle(Font.NORMAL);
				font41.setSize(7);
				chunk41.setFont(font41);
				Paragraph paragraph41 = new Paragraph();
				paragraph41.add(chunk41);
				paragraph41.setAlignment(Element.ALIGN_LEFT);
				paragraph41.setSpacingBefore(0);
				paragraph41.setSpacingAfter(0);

				Chunk chunkl = new Chunk("																																																																																																																																									"
						+ "	Std.: "+grDetailMap.get("std"));
				Font fontl = FontFactory.getFont("TIMES_ROMAN");
				fontl.setStyle(Font.NORMAL);
				fontl.setSize(7);
				chunkl.setFont(fontl);
				Paragraph paragraphl = new Paragraph();
				paragraphl.add(chunkl);
				paragraphl.setAlignment(Element.ALIGN_LEFT);
				paragraphl.setSpacingBefore(-16);
				paragraphl.setSpacingAfter(0);

				Chunk chunkm = new Chunk(
						"																																																																																																																																																																																																																																																																																					Std.: "+grDetailMap.get("std"));
				Font fontm = FontFactory.getFont("TIMES_ROMAN");
				fontm.setStyle(Font.NORMAL);
				fontm.setSize(7);
				chunkm.setFont(fontm);
				Paragraph paragraphm = new Paragraph();
				paragraphm.add(chunkm);
				paragraphm.setAlignment(Element.ALIGN_LEFT);
				paragraphm.setSpacingBefore(-16);
				paragraphm.setSpacingAfter(0);

				Chunk chunk44 = new Chunk(
						"																																																						Div. : "+grDetailMap.get("div"));
				Font font44 = FontFactory.getFont("TIMES_ROMAN");
				font44.setStyle(Font.NORMAL);
				font44.setSize(7);
				chunk44.setFont(font44);
				Paragraph paragraph44 = new Paragraph();
				paragraph44.add(chunk44);
				paragraph44.setAlignment(Element.ALIGN_LEFT);
				paragraph44.setSpacingBefore(-16);
				paragraph44.setSpacingAfter(0);

				Chunk chunkn = new Chunk("Div. : "+grDetailMap.get("div"));
				Font fontn = FontFactory.getFont("TIMES_ROMAN");
				fontn.setStyle(Font.NORMAL);
				fontn.setSize(7);
				chunkn.setFont(fontn);
				Paragraph paragraphn = new Paragraph();
				paragraphn.add(chunkn);
				paragraphn.setAlignment(Element.ALIGN_CENTER);
				paragraphn.setSpacingBefore(-16);
				paragraphn.setSpacingAfter(0);

				Chunk chunko = new Chunk("																																																																																																																																																																																																																																																																																																																																										"
						+ "	Div. : "+grDetailMap.get("div"));
				Font fonto = FontFactory.getFont("TIMES_ROMAN");
				fonto.setStyle(Font.NORMAL);
				fonto.setSize(7);
				chunko.setFont(fonto);
				Paragraph paragrapho = new Paragraph();
				paragrapho.add(chunko);
				paragrapho.setAlignment(Element.ALIGN_LEFT);
				paragrapho.setSpacingBefore(-16);
				paragrapho.setSpacingAfter(0);

				Chunk chunk45 = new Chunk("																																																																																												"
						+ "	Roll No. : "+grDetailMap.get("rollNo"));
				Font font45 = FontFactory.getFont("TIMES_ROMAN");
				font45.setStyle(Font.NORMAL);
				font45.setSize(7);
				chunk45.setFont(font45);
				Paragraph paragraph45 = new Paragraph();
				paragraph45.add(chunk45);
				paragraph45.setAlignment(Element.ALIGN_LEFT);
				paragraph45.setSpacingBefore(-16);
				paragraph45.setSpacingAfter(0);

				Chunk chunkp = new Chunk("																																																																																																																																																																																																																																					"
						+ "	Roll No. : "+grDetailMap.get("rollNo"));
				Font fontp = FontFactory.getFont("TIMES_ROMAN");
				fontp.setStyle(Font.NORMAL);
				fontp.setSize(7);
				chunkp.setFont(fontp);
				Paragraph paragraphp = new Paragraph();
				paragraphp.add(chunkp);
				paragraphp.setAlignment(Element.ALIGN_LEFT);
				paragraphp.setSpacingBefore(-16);
				paragraphp.setSpacingAfter(0);

				Chunk chunkq = new Chunk("		                                                                                                                                          																																																																																																																																																																																																																																			"
						+ "Roll No. : "+grDetailMap.get("rollNo"));
				Font fontq = FontFactory.getFont("TIMES_ROMAN");
				fontq.setStyle(Font.NORMAL);
				fontq.setSize(7);
				chunkq.setFont(fontq);
				Paragraph paragraphq = new Paragraph();
				paragraphq.add(chunkq);
				paragraphq.setAlignment(Element.ALIGN_LEFT);
				paragraphq.setSpacingBefore(-16);
				paragraphq.setSpacingAfter(10);

//				document.newPage();
				// table.setSpacingBefore(20.0f); // Space Before table starts, like
				// margin-top in CSS
				// table.setSpacingAfter(300.0f);

				PdfPTable tableStudent = new PdfPTable(4);

				tableStudent.setWidthPercentage(30);
				tableStudent.setHorizontalAlignment(Element.ALIGN_LEFT);

				PdfPCell cell301 = new PdfPCell(new Paragraph("Sr. No.", FontFactory.getFont(FontFactory.TIMES, 8)));
				cell301.setColspan(1);
				cell301.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell301.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell301.setBackgroundColor(new BaseColor(160, 160, 160));
				tableStudent.addCell(cell301);

				PdfPCell cell302 = new PdfPCell(
						new Paragraph("Fees Description", FontFactory.getFont(FontFactory.TIMES, 8)));
				cell302.setColspan(2);
				cell302.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell302.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell302.setBackgroundColor(new BaseColor(160, 160, 160));
				tableStudent.addCell(cell302);

				PdfPCell cell303 = new PdfPCell(new Paragraph("Amount", FontFactory.getFont(FontFactory.TIMES, 8)));
				cell303.setColspan(9);
				cell303.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell303.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell303.setBackgroundColor(new BaseColor(160, 160, 160));
				tableStudent.addCell(cell303);

				PdfPCell[] cellStudent = new PdfPCell[feesPaymentMap.size()];
				int j = 0;
				int k = 0;
				int l = 0;
				String feeType = "", subFee = "", optional;
				double feeTotal = 0;
				boolean isOptional = false;
				Set<?> set = feesPaymentMap.entrySet();
				Iterator<?> i = set.iterator();
				while(i.hasNext()) {
					Map.Entry me = (Map.Entry)i.next();
					feeType = commonObj.revertCommaApostrophy(me.getKey().toString().replace("$$", "."));
					isOptional = false;
					
					cellStudent[j] = new PdfPCell(new Paragraph((j+1)+"", FontFactory.getFont(FontFactory.TIMES, 8)));
					cellStudent[j].setColspan(1);
					cellStudent[j].setHorizontalAlignment(Element.ALIGN_CENTER);
					cellStudent[j].setVerticalAlignment(Element.ALIGN_MIDDLE);
					
					for(int n = 0; n < optionalList.length; n++) {
    	        		if(optionalList[n] != null && optionalList[n].contains(feeType+"^") && subFee.equalsIgnoreCase("")) {
    	        			isOptional = true;
    	        			subFee = optionalList[n].substring(optionalList[n].indexOf("^")+1);
    	        			if(!subFee.equalsIgnoreCase("")) {
    	        				subFee = " ("+ subFee +")";
    	        			}
    	        			feeType = feeType + subFee;
    	        			break;
    	        		}
    	        	}
					if(feesHeadMap != null) {
						optional = ((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("optional").toString();
						if(optional.equalsIgnoreCase("No") || (optional.equalsIgnoreCase("Yes") && isOptional)) {
							feeType = feeType.replace("_", " ");
							tableStudent.addCell(cellStudent[j]);
	    				}
	    				else if(!optional.equalsIgnoreCase("")){
	    					feeType = "";
	    					continue;
	    				}
					}
					else {
						tableStudent.addCell(cellStudent[j]);
					}
					
					cellStudent[k] = new PdfPCell(new Paragraph(commonObj.revertCommaApostrophy(feeType.replace("_", " ")), FontFactory.getFont(FontFactory.TIMES, 8)));
					cellStudent[k].setColspan(2);
					cellStudent[k].setHorizontalAlignment(Element.ALIGN_LEFT);
					cellStudent[k].setVerticalAlignment(Element.ALIGN_MIDDLE);
					tableStudent.addCell(cellStudent[k]);
					
					LinkedHashMap feesTypeMonthMap = new LinkedHashMap();
					feesTypeMonthMap = feesPaymentMap.get(me.getKey());
					
					Set<?> setMonth = feesTypeMonthMap.entrySet();
					Iterator<?> m = setMonth.iterator();
					double feeTypeTotal = 0;
					while(m.hasNext()) {
						Map.Entry monthData = (Map.Entry)m.next();
						
						if(feesHeadMap != null) {
							optional = ((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("optional").toString();
							if(optional.equalsIgnoreCase("No") || (optional.equalsIgnoreCase("Yes") && isOptional)) {
								feeTypeTotal = feeTypeTotal + (Double.parseDouble(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey().toString())).get("amount").toString())/commonObj.frequencyToInteger(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey().toString())).get("frequency").toString()));
		    				}
						}
						else {
							feeTypeTotal = feeTypeTotal + (Double.parseDouble(((LinkedHashMap<?, ?>) feesTypeMonthMap.get(monthData.getKey())).get("amount").toString()));
						}
					}
					
					if(feesHeadMap != null) {
						optional = ((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("optional").toString();
						if(optional.equalsIgnoreCase("No") || (optional.equalsIgnoreCase("Yes") && isOptional)) {
							displayFeesMap.put(feeType, feeTypeTotal+"");
							feeTotal = feeTotal + feeTypeTotal;
	    				}
						else {
							continue;
						}
					}
					else {
						displayFeesMap.put(feeType, feeTypeTotal+"");
						feeTotal = feeTotal + feeTypeTotal;
					}
					
					cellStudent[l] = new PdfPCell(new Paragraph(String.format("%.2f", feeTypeTotal)+"", FontFactory.getFont(FontFactory.TIMES, 8)));
					cellStudent[l].setColspan(9);
					cellStudent[l].setHorizontalAlignment(Element.ALIGN_CENTER);
					cellStudent[l].setVerticalAlignment(Element.ALIGN_MIDDLE);
					tableStudent.addCell(cellStudent[l]);
					
					j++;
					k++;
					l++;
				}
				
				if(feesPaymentMap.size() < fee_row_count){
					PdfPCell[] cellEmpty = new PdfPCell[fee_row_count - feesPaymentMap.size()];
					int p = 0; 
					int q = 0;
					for(int n = 0; n < (fee_row_count - feesPaymentMap.size()); n++){
						
						if(n > 8 && feesPaymentMap.size() > 8) {
							tableSpacingBefore = tableSpacingBefore - 10f;
						}
						
						cellEmpty[n] = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES, 8)));
						cellEmpty[n].setColspan(1);
						cellEmpty[n].setHorizontalAlignment(Element.ALIGN_CENTER);
						cellEmpty[n].setVerticalAlignment(Element.ALIGN_MIDDLE);
						tableStudent.addCell(cellEmpty[n]);
						
						cellEmpty[p] = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES, 8)));
						cellEmpty[p].setColspan(2);
						cellEmpty[p].setHorizontalAlignment(Element.ALIGN_LEFT);
						cellEmpty[p].setVerticalAlignment(Element.ALIGN_MIDDLE);
						tableStudent.addCell(cellEmpty[p]);
						
						cellEmpty[q] = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES, 8)));
						cellEmpty[q].setColspan(9);
						cellEmpty[q].setHorizontalAlignment(Element.ALIGN_CENTER);
						cellEmpty[q].setVerticalAlignment(Element.ALIGN_MIDDLE);
						tableStudent.addCell(cellEmpty[q]);
						
						p++;
						q++;
					}
				}

				PdfPCell cellConcessionSr = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES, 8)));
				cellConcessionSr.setColspan(1);
				cellConcessionSr.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellConcessionSr.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableStudent.addCell(cellConcessionSr);

				PdfPCell cellConcession = new PdfPCell(new Paragraph("CONCESSION", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cellConcession.setColspan(2);
				cellConcession.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cellConcession.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableStudent.addCell(cellConcession);

				concession = "  "+String.format("%.2f", concessionAmount);
				PdfPCell cellConcessionAmt = new PdfPCell(new Paragraph(concession, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cellConcessionAmt.setColspan(9);
				cellConcessionAmt.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellConcessionAmt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableStudent.addCell(cellConcessionAmt);
				
				PdfPCell cellPenaltySr = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES, 8)));
				cellPenaltySr.setColspan(1);
				cellPenaltySr.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellPenaltySr.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableStudent.addCell(cellPenaltySr);

				PdfPCell cellPenalty = new PdfPCell(new Paragraph("PENALTY", FontFactory.getFont(FontFactory.TIMES, 8)));
				cellPenalty.setColspan(2);
				cellPenalty.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cellPenalty.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableStudent.addCell(cellPenalty);

				penalty = "+ "+String.format("%.2f", Double.parseDouble(penaltyAmount));
				PdfPCell cellPenaltyAmt = new PdfPCell(new Paragraph(penalty, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cellPenaltyAmt.setColspan(9);
				cellPenaltyAmt.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellPenaltyAmt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableStudent.addCell(cellPenaltyAmt);
				
				PdfPCell cell328 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES, 8)));
				cell328.setColspan(1);
				cell328.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell328.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableStudent.addCell(cell328);

				PdfPCell cell329 = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont(FontFactory.TIMES, 8)));
				cell329.setColspan(2);
				cell329.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell329.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableStudent.addCell(cell329);

				feeTotal = feeTotal + Double.parseDouble(penaltyAmount) - concessionAmount;
				total = String.format("%.2f", feeTotal)+"";
				PdfPCell cell330 = new PdfPCell(new Paragraph(total, FontFactory.getFont(FontFactory.TIMES, 8)));
				cell330.setColspan(9);
				cell330.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell330.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableStudent.addCell(cell330);

				// table.setSpacingBefore(300.0f); // Space Before table starts,
				// like margin-top in CSS
				tableStudent.setSpacingAfter(tableSpacingBefore);

				PdfPTable tableOffice = new PdfPTable(4);

				tableOffice.setWidthPercentage(30);
				tableOffice.setHorizontalAlignment(Element.ALIGN_CENTER);

				PdfPCell cell331 = new PdfPCell(new Paragraph("Sr. No.", FontFactory.getFont(FontFactory.TIMES, 8)));
				cell331.setColspan(1);
				cell331.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell331.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell331.setBackgroundColor(new BaseColor(160, 160, 160));
				tableOffice.addCell(cell331);

				PdfPCell cell332 = new PdfPCell(
						new Paragraph("Fees Description", FontFactory.getFont(FontFactory.TIMES, 8)));
				cell332.setColspan(2);
				cell332.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell332.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell332.setBackgroundColor(new BaseColor(160, 160, 160));
				tableOffice.addCell(cell332);

				PdfPCell cell333 = new PdfPCell(new Paragraph("Amount", FontFactory.getFont(FontFactory.TIMES, 8)));
				cell333.setColspan(9);
				cell333.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell333.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell333.setBackgroundColor(new BaseColor(160, 160, 160));
				tableOffice.addCell(cell333);

				PdfPCell[] cellOffice = new PdfPCell[feesPaymentMap.size()];
				int jOffice = 0;
				int kOffice = 0;
				int lOffice = 0;
				String feeTypeOffice = "", subFeeOffice = "";
				double feeTotalOffice = 0;
				Set<?> setOffice = displayFeesMap.entrySet();
				Iterator<?> iOffice = setOffice.iterator();
				while(iOffice.hasNext()) {
					Map.Entry me = (Map.Entry)iOffice.next();
					/*feeTypeOffice = me.getKey().toString().replace("$$", ".");
					for(int n = 0; n < optionalList.length; n++) {
    	        		if(optionalList[n].contains(feeTypeOffice+"^") && subFeeOffice.equalsIgnoreCase("")) {
    	        			subFeeOffice = optionalList[n].substring(optionalList[n].indexOf("^")+1);
    	        			if(!subFeeOffice.equalsIgnoreCase("")) {
    	        				subFeeOffice = " ("+ subFeeOffice +")";
    	        			}
    	        			
    	        			feeTypeOffice = feeTypeOffice + subFeeOffice;
    	        			break;
    	        		}
    	        	}
					feeTypeOffice = feeTypeOffice.replace("_", " ");*/
					
					cellOffice[jOffice] = new PdfPCell(new Paragraph((jOffice+1)+"", FontFactory.getFont(FontFactory.TIMES, 8)));
					cellOffice[jOffice].setColspan(1);
					cellOffice[jOffice].setHorizontalAlignment(Element.ALIGN_CENTER);
					cellOffice[jOffice].setVerticalAlignment(Element.ALIGN_MIDDLE);
					tableOffice.addCell(cellOffice[jOffice]);
					
					cellOffice[kOffice] = new PdfPCell(new Paragraph(commonObj.revertCommaApostrophy(me.getKey().toString().replace("_", " ")), FontFactory.getFont(FontFactory.TIMES, 8)));
					cellOffice[kOffice].setColspan(2);
					cellOffice[kOffice].setHorizontalAlignment(Element.ALIGN_LEFT);
					cellOffice[kOffice].setVerticalAlignment(Element.ALIGN_MIDDLE);
					tableOffice.addCell(cellOffice[kOffice]);
					
					cellOffice[lOffice] = new PdfPCell(new Paragraph(String.format("%.2f", Double.parseDouble(me.getValue().toString()))+"", FontFactory.getFont(FontFactory.TIMES, 8)));
					cellOffice[lOffice].setColspan(9);
					cellOffice[lOffice].setHorizontalAlignment(Element.ALIGN_CENTER);
					cellOffice[lOffice].setVerticalAlignment(Element.ALIGN_MIDDLE);
					tableOffice.addCell(cellOffice[lOffice]);
					
					jOffice++;
					kOffice++;
					lOffice++;
				}
				
				if(feesPaymentMap.size() < fee_row_count){
					PdfPCell[] cellEmpty = new PdfPCell[fee_row_count - feesPaymentMap.size()];
					int p = 0; 
					int q = 0;
					for(int n = 0; n < (fee_row_count - feesPaymentMap.size()); n++){
						cellEmpty[n] = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES, 8)));
						cellEmpty[n].setColspan(1);
						cellEmpty[n].setHorizontalAlignment(Element.ALIGN_CENTER);
						cellEmpty[n].setVerticalAlignment(Element.ALIGN_MIDDLE);
						tableOffice.addCell(cellEmpty[n]);
						
						cellEmpty[p] = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES, 8)));
						cellEmpty[p].setColspan(2);
						cellEmpty[p].setHorizontalAlignment(Element.ALIGN_LEFT);
						cellEmpty[p].setVerticalAlignment(Element.ALIGN_MIDDLE);
						tableOffice.addCell(cellEmpty[p]);
						
						cellEmpty[q] = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES, 8)));
						cellEmpty[q].setColspan(9);
						cellEmpty[q].setHorizontalAlignment(Element.ALIGN_CENTER);
						cellEmpty[q].setVerticalAlignment(Element.ALIGN_MIDDLE);
						tableOffice.addCell(cellEmpty[q]);
						
						p++;
						q++;
					}
				}
				
				PdfPCell cellConcessionSrOffice = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES, 8)));
				cellConcessionSrOffice.setColspan(1);
				cellConcessionSrOffice.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellConcessionSrOffice.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableOffice.addCell(cellConcessionSrOffice);
				
				PdfPCell cellConcessionOffice = new PdfPCell(new Paragraph("CONCESSION", FontFactory.getFont(FontFactory.TIMES, 8)));
				cellConcessionOffice.setColspan(2);
				cellConcessionOffice.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cellConcessionOffice.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableOffice.addCell(cellConcessionOffice);
				
				PdfPCell cellConcessionAmtOffice = new PdfPCell(new Paragraph(concession, FontFactory.getFont(FontFactory.TIMES, 8)));
				cellConcessionAmtOffice.setColspan(9);
				cellConcessionAmtOffice.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellConcessionAmtOffice.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableOffice.addCell(cellConcessionAmtOffice);
				
				PdfPCell cellPenaltySrOffice = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES, 8)));
				cellPenaltySrOffice.setColspan(1);
				cellPenaltySrOffice.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellPenaltySrOffice.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableOffice.addCell(cellPenaltySrOffice);
				
				PdfPCell cellPenaltyOffice = new PdfPCell(new Paragraph("PENALTY", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cellPenaltyOffice.setColspan(2);
				cellPenaltyOffice.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cellPenaltyOffice.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableOffice.addCell(cellPenaltyOffice);
				
				PdfPCell cellPenaltyAmtOffice = new PdfPCell(new Paragraph(penalty, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cellPenaltyAmtOffice.setColspan(9);
				cellPenaltyAmtOffice.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellPenaltyAmtOffice.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableOffice.addCell(cellPenaltyAmtOffice);
				
				PdfPCell cellOfficeSr = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES, 8)));
				cellOfficeSr.setColspan(1);
				cellOfficeSr.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellOfficeSr.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableOffice.addCell(cellOfficeSr);
				
				PdfPCell cellOfficeTotal = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cellOfficeTotal.setColspan(2);
				cellOfficeTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cellOfficeTotal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableOffice.addCell(cellOfficeTotal);
				
				PdfPCell cellOfficeGrand = new PdfPCell(new Paragraph(feeTotal+"", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cellOfficeGrand.setColspan(9);
				cellOfficeGrand.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellOfficeGrand.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableOffice.addCell(cellOfficeGrand);

				tableOffice.setSpacingAfter(tableSpacingBefore);

				PdfPTable tableTeach = new PdfPTable(4);

				tableTeach.setWidthPercentage(30);
				tableTeach.setHorizontalAlignment(Element.ALIGN_RIGHT);

				PdfPCell cell361 = new PdfPCell(new Paragraph("Sr. No.", FontFactory.getFont(FontFactory.TIMES, 8)));
				cell361.setColspan(1);
				cell361.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell361.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell361.setBackgroundColor(new BaseColor(160, 160, 160));
				tableTeach.addCell(cell361);

				PdfPCell cell362 = new PdfPCell(
						new Paragraph("Fees Description", FontFactory.getFont(FontFactory.TIMES, 8)));
				cell362.setColspan(2);
				cell362.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell362.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell362.setBackgroundColor(new BaseColor(160, 160, 160));
				tableTeach.addCell(cell362);

				PdfPCell cell363 = new PdfPCell(new Paragraph("Amount", FontFactory.getFont(FontFactory.TIMES, 8)));
				cell363.setColspan(9);
				cell363.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell363.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell363.setBackgroundColor(new BaseColor(160, 160, 160));
				tableTeach.addCell(cell363);

				PdfPCell[] cellTeach = new PdfPCell[feesPaymentMap.size()];
				int jTeach = 0;
				int kTeach = 0;
				int lTeach = 0;
				String feeTypeTeach = "", subFeeTeach = "";
				double feeTotalTeach = 0;
				Set<?> setTeach = displayFeesMap.entrySet();
				Iterator<?> iTeach = setTeach.iterator();
				while(iTeach.hasNext()) {
					Map.Entry me = (Map.Entry)iTeach.next();
					/*feeTypeTeach = me.getKey().toString().replace("$$", ".");
					for(int n = 0; n < optionalList.length; n++) {
    	        		if(optionalList[n].contains(feeTypeTeach+"^") && subFeeTeach.equalsIgnoreCase("")) {
    	        			subFeeTeach = optionalList[n].substring(optionalList[n].indexOf("^")+1);
    	        			if(!subFeeTeach.equalsIgnoreCase("")) {
    	        				subFeeTeach = " ("+ subFeeTeach +")";
    	        			}
    	        			feeTypeTeach = feeTypeTeach + subFeeTeach;
    	        			break;
    	        		}
    	        	}
					feeTypeTeach = feeTypeTeach.replace("_", " ");*/
					
					cellTeach[jTeach] = new PdfPCell(new Paragraph((jTeach+1)+"", FontFactory.getFont(FontFactory.TIMES, 8)));
					cellTeach[jTeach].setColspan(1);
					cellTeach[jTeach].setHorizontalAlignment(Element.ALIGN_CENTER);
					cellTeach[jTeach].setVerticalAlignment(Element.ALIGN_MIDDLE);
					tableTeach.addCell(cellTeach[jTeach]);
					
					cellTeach[kTeach] = new PdfPCell(new Paragraph(commonObj.revertCommaApostrophy(me.getKey().toString().replace("_", " ")), FontFactory.getFont(FontFactory.TIMES, 8)));
					cellTeach[kTeach].setColspan(2);
					cellTeach[kTeach].setHorizontalAlignment(Element.ALIGN_LEFT);
					cellTeach[kTeach].setVerticalAlignment(Element.ALIGN_MIDDLE);
					tableTeach.addCell(cellTeach[kTeach]);
					
					/*LinkedHashMap feesTypeMonthMap = new LinkedHashMap();
					feesTypeMonthMap = feesPaymentMap.get(me.getKey());
					
					Set<?> setMonth = feesTypeMonthMap.entrySet();
					Iterator<?> m = setMonth.iterator();
					double feeTypeTotal = 0;
					while(m.hasNext()) {
						Map.Entry monthData = (Map.Entry)m.next();
						feeTypeTotal = feeTypeTotal + (Double.parseDouble(((LinkedHashMap<?, ?>) feesTypeMonthMap.get(monthData.getKey())).get("amount").toString()));
					}
					feeTotalTeach = feeTotalTeach + feeTypeTotal;*/
					
					cellTeach[lTeach] = new PdfPCell(new Paragraph(String.format("%.2f", Double.parseDouble(me.getValue().toString()))+"", FontFactory.getFont(FontFactory.TIMES, 8)));
					cellTeach[lTeach].setColspan(9);
					cellTeach[lTeach].setHorizontalAlignment(Element.ALIGN_CENTER);
					cellTeach[lTeach].setVerticalAlignment(Element.ALIGN_MIDDLE);
					tableTeach.addCell(cellTeach[lTeach]);
					
					jTeach++;
					kTeach++;
					lTeach++;
				}
				
				if(feesPaymentMap.size() < fee_row_count){
					PdfPCell[] cellEmpty = new PdfPCell[fee_row_count - feesPaymentMap.size()];
					int p = 0; 
					int q = 0;
					for(int n = 0; n < (fee_row_count - feesPaymentMap.size()); n++){
						cellEmpty[n] = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES, 8)));
						cellEmpty[n].setColspan(1);
						cellEmpty[n].setHorizontalAlignment(Element.ALIGN_CENTER);
						cellEmpty[n].setVerticalAlignment(Element.ALIGN_MIDDLE);
						tableTeach.addCell(cellEmpty[n]);
						
						cellEmpty[p] = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES, 8)));
						cellEmpty[p].setColspan(2);
						cellEmpty[p].setHorizontalAlignment(Element.ALIGN_LEFT);
						cellEmpty[p].setVerticalAlignment(Element.ALIGN_MIDDLE);
						tableTeach.addCell(cellEmpty[p]);
						
						cellEmpty[q] = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES, 8)));
						cellEmpty[q].setColspan(9);
						cellEmpty[q].setHorizontalAlignment(Element.ALIGN_CENTER);
						cellEmpty[q].setVerticalAlignment(Element.ALIGN_MIDDLE);
						tableTeach.addCell(cellEmpty[q]);
						
						p++;
						q++;
					}
				}
				
				PdfPCell cellConcessionSrTeach = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES, 8)));
				cellConcessionSrTeach.setColspan(1);
				cellConcessionSrTeach.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellConcessionSrTeach.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableTeach.addCell(cellConcessionSrTeach);
				
				PdfPCell cellConcessionTeach = new PdfPCell(new Paragraph("CONCESSION", FontFactory.getFont(FontFactory.TIMES, 8)));
				cellConcessionTeach.setColspan(2);
				cellConcessionTeach.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cellConcessionTeach.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableTeach.addCell(cellConcessionOffice);
				
				PdfPCell cellConcessionAmtTeach = new PdfPCell(new Paragraph(concession, FontFactory.getFont(FontFactory.TIMES, 8)));
				cellConcessionAmtOffice.setColspan(9);
				cellConcessionAmtOffice.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellConcessionAmtOffice.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableTeach.addCell(cellConcessionAmtOffice);
				
				PdfPCell cellPenaltySrTeach = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES, 8)));
				cellPenaltySrTeach.setColspan(1);
				cellPenaltySrTeach.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellPenaltySrTeach.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableTeach.addCell(cellPenaltySrTeach);
				
				PdfPCell cellPenaltyTeach = new PdfPCell(new Paragraph("PENALTY", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cellPenaltyTeach.setColspan(2);
				cellPenaltyTeach.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cellPenaltyTeach.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableTeach.addCell(cellPenaltyTeach);
				
				PdfPCell cellPenaltyAmtTeach = new PdfPCell(new Paragraph(penalty, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cellPenaltyAmtTeach.setColspan(9);
				cellPenaltyAmtTeach.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellPenaltyAmtTeach.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableTeach.addCell(cellPenaltyAmtTeach);
				
				PdfPCell cellTeachSr = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES, 8)));
				cellTeachSr.setColspan(1);
				cellTeachSr.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellTeachSr.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableTeach.addCell(cellTeachSr);
				
				PdfPCell cellTeachTotal = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cellTeachTotal.setColspan(2);
				cellTeachTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cellTeachTotal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableTeach.addCell(cellTeachTotal);
				
				PdfPCell cellTeachGrand = new PdfPCell(new Paragraph(feeTotal+"", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
				cellTeachGrand.setColspan(9);
				cellTeachGrand.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellTeachGrand.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tableTeach.addCell(cellTeachGrand);

				String wordBeforeDecimal = commonObj.convert(Integer.parseInt((totalAmount+"").substring(0, (totalAmount+"").indexOf("."))));
				String wordAfterDecimal = commonObj.convert(Integer.parseInt((totalAmount+"").substring((totalAmount+"").indexOf(".")+1)));
				String amountInWords = wordBeforeDecimal;
				if(!wordAfterDecimal.equalsIgnoreCase("zero") && !wordAfterDecimal.equalsIgnoreCase("")){
					amountInWords = amountInWords + " and " + wordAfterDecimal + " paise";
				}
				
				List amountWordsList = commonObj.breakSentence(amountInWords, 45);
				String words1 = "", words2 = "";
				if(amountWordsList != null){
					words1 = amountWordsList.get(0).toString().trim();
					if(amountWordsList.size() > 1 && !amountWordsList.get(1).toString().trim().equalsIgnoreCase("")){
						words2 = amountWordsList.get(1).toString().trim() + " Only.";
					}
					else{
						words1 = words1 + " Only.";
					}
				}
				
				Chunk chunk42 = new Chunk("Amount in Words: Rs. "+words1);
				Font font42 = FontFactory.getFont("TIMES_ROMAN");
				font42.setStyle(Font.NORMAL);
				font42.setSize(7);
				chunk42.setFont(font42);
				Paragraph paragraph42 = new Paragraph();
				paragraph42.add(chunk42);
				paragraph42.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraph42.setSpacingBefore(spacingBefore);
				
				Chunk chunkWords2 = new Chunk("                             "+words2);
				font42.setStyle(Font.NORMAL);
				font42.setSize(7);
				chunkWords2.setFont(font42);
				Paragraph paragraphWords2 = new Paragraph();
				paragraphWords2.add(chunkWords2);
				paragraphWords2.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraphWords2.setSpacingBefore(-5);

				int wordSpacingBefore = -16;
				if(!words2.equalsIgnoreCase("")){
					wordSpacingBefore = -27;
				}
//				if(feesHeadMap != null) {
//					spacingBefore = wordSpacingBefore + spacingBefore;
//				}
				Chunk chunk43 = new Chunk("																																																																																																																																									"
						+ "	Amount in Words: Rs. "+words1);
				Font font43 = FontFactory.getFont("TIMES_ROMAN");
				font43.setStyle(Font.NORMAL);
				font43.setSize(7);
				chunk43.setFont(font43);
				Paragraph paragraph43 = new Paragraph();
				paragraph43.add(chunk43);
				paragraph43.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraph43.setSpacingBefore(wordSpacingBefore);//-16

				Chunk chunkOfficeWords2 = new Chunk("			         																																																																																																																																							             "
						+ "        "+words2);
				chunkOfficeWords2.setFont(font43);
				Paragraph paragraphOfficeWords2 = new Paragraph();
				paragraphOfficeWords2.add(chunkOfficeWords2);
				paragraphOfficeWords2.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraphOfficeWords2.setSpacingBefore(-5);
				
				Chunk chunk46 = new Chunk("																																																																																																																																																																																																																																																																																			"
						+ "		Amount in Words: Rs. "+words1);
				Font font46 = FontFactory.getFont("TIMES_ROMAN");
				font46.setStyle(Font.NORMAL);
				font46.setSize(7);
				chunk46.setFont(font46);
				Paragraph paragraph46 = new Paragraph();
				paragraph46.add(chunk46);
				paragraph46.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraph46.setSpacingBefore(wordSpacingBefore);

				Chunk chunkTeachWords2 = new Chunk("																																																																																																																																																																																																																																																																																					                       "
						+ "      "+words2);
				chunkTeachWords2.setFont(font43);
				Paragraph paragraphTeachWords2 = new Paragraph();
				paragraphTeachWords2.add(chunkTeachWords2);
				paragraphTeachWords2.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraphTeachWords2.setSpacingBefore(-5);
				
				String chequeNo = "";
				String bankName = " ";
				paymentMode = grDetailMap.get("paymentMode");
				
				if(paymentMode.trim().equalsIgnoreCase("Cash")){
					chequeNo = "Payment Mode : Cash";
				}
				else if(paymentMode.trim().equalsIgnoreCase("Cheque")){
					bankName = "Bank Name : "+grDetailMap.get("bank");
					chequeNo = "Cheque No. : "+grDetailMap.get("chequeDDNo")+"					Dated : "+grDetailMap.get("chequeDDDate");
				}
				else if(paymentMode.trim().equalsIgnoreCase("DD")){
					bankName = "Bank Name : "+grDetailMap.get("bank");
					chequeNo = "D.D. No. : "+grDetailMap.get("chequeDDNo")+"					Dated : "+grDetailMap.get("chequeDDDate");
				}
				
				Chunk chunk50 = new Chunk(chequeNo);
				Font font50 = FontFactory.getFont("TIMES_ROMAN");
				font50.setStyle(Font.NORMAL);
				font50.setSize(7);
				chunk50.setFont(font50);
				Paragraph paragraph50 = new Paragraph();
				paragraph50.add(chunk50);
				paragraph50.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraph50.setSpacingBefore(5);

				Chunk chunk51 = new Chunk("																																																																																																																																									"
						+ "	"+chequeNo);
				Font font51 = FontFactory.getFont("TIMES_ROMAN");
				font51.setStyle(Font.NORMAL);
				font51.setSize(7);
				chunk51.setFont(font51);
				Paragraph paragraph51 = new Paragraph();
				paragraph51.add(chunk51);
				paragraph51.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraph51.setSpacingBefore(-16);

				Chunk chunk52 = new Chunk("																																																																																																																																																																																																																																																																																				"
						+ "	"+chequeNo);
				Font font52 = FontFactory.getFont("TIMES_ROMAN");
				font52.setStyle(Font.NORMAL);
				font52.setSize(7);
				chunk52.setFont(font52);
				Paragraph paragraph52 = new Paragraph();
				paragraph52.add(chunk52);
				paragraph52.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraph52.setSpacingBefore(-16);

				Chunk chunk53 = new Chunk(bankName);
				Font font53 = FontFactory.getFont("TIMES_ROMAN");
				font53.setStyle(Font.NORMAL);
				font53.setSize(7);
				chunk53.setFont(font53);
				Paragraph paragraph53 = new Paragraph();
				paragraph53.add(chunk53);
				paragraph53.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraph53.setSpacingBefore(5);

				Chunk chunk54 = new Chunk("																																																																																																																																									"
						+ "	"+bankName);
				Font font54 = FontFactory.getFont("TIMES_ROMAN");
				font54.setStyle(Font.NORMAL);
				font54.setSize(7);
				chunk54.setFont(font54);
				Paragraph paragraph54 = new Paragraph();
				paragraph54.add(chunk54);
				paragraph54.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraph54.setSpacingBefore(-16);

				Chunk chunk55 = new Chunk("																																																																																																																																																																																																																																																																																			"
						+ "		"+bankName);
				Font font55 = FontFactory.getFont("TIMES_ROMAN");
				font55.setStyle(Font.NORMAL);
				font55.setSize(7);
				chunk55.setFont(font55);
				Paragraph paragraph55 = new Paragraph();
				paragraph55.add(chunk55);
				paragraph55.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraph55.setSpacingBefore(-16);

				if(remark.contains("|")) {
					displayDate = remark.substring(remark.indexOf("|")+1);
					remark = remark.substring(0, remark.lastIndexOf("|"));
				}
				List remarkList = commonObj.breakSentence(remark, 45);
				if(remarkList != null){
					remark = remarkList.get(0).toString().trim();
					if(remarkList.size() > 1){
						remark2 = remarkList.get(1).toString().trim();
					}
				}
				
				int remarkSpacingBefore = -16;
				if(!remark2.equalsIgnoreCase("")){
					remarkSpacingBefore = -27;
				}
				
				Chunk chunkRemark = new Chunk("Remark: "+remark);
				chunkRemark.setFont(font43);
				Paragraph paragraphRemark = new Paragraph();
				paragraphRemark.add(chunkRemark);
				paragraphRemark.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraphRemark.setSpacingBefore(spacingBefore);
				
				Chunk chunkRemark2 = new Chunk("               "+remark2);
				chunkRemark2.setFont(font43);
				Paragraph paragraphRemark2 = new Paragraph();
				paragraphRemark2.add(chunkRemark2);
				paragraphRemark2.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraphRemark2.setSpacingBefore(-5);
				
				Chunk chunkOfficeRemark = new Chunk("																																																																																																																																									"
						+ "	Remark: "+remark);
				chunkOfficeRemark.setFont(font43);
				Paragraph paragraphOfficeRemark = new Paragraph();
				paragraphOfficeRemark.add(chunkOfficeRemark);
				paragraphOfficeRemark.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraphOfficeRemark.setSpacingBefore(remarkSpacingBefore);
				
				Chunk chunkOfficeRemark2 = new Chunk("			         																																																																																																																																				 "
						+ "        "+remark2);
				chunkOfficeRemark2.setFont(font43);
				Paragraph paragraphOfficeRemark2 = new Paragraph();
				paragraphOfficeRemark2.add(chunkOfficeRemark2);
				paragraphOfficeRemark2.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraphOfficeRemark2.setSpacingBefore(-5);
				
				Chunk chunkTeachRemark = new Chunk("																																																																																																																																																																																																																																																																																			"
						+ "		Remark: "+remark);
				chunkTeachRemark.setFont(font43);
				Paragraph paragraphTeachRemark = new Paragraph();
				paragraphTeachRemark.add(chunkTeachRemark);
				paragraphTeachRemark.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraphTeachRemark.setSpacingBefore(remarkSpacingBefore);
				
				Chunk chunkTeachRemark2 = new Chunk("																																																																																																																																																																																																																																																																															               "
						+ "      "+remark2);
				chunkTeachRemark2.setFont(font43);
				Paragraph paragraphTeachRemark2 = new Paragraph();
				paragraphTeachRemark2.add(chunkTeachRemark2);
				paragraphTeachRemark2.setAlignment(Element.ALIGN_JUSTIFIED);
				paragraphTeachRemark2.setSpacingBefore(-5);
				
				Chunk chunk47 = new Chunk("																																																																																									"
						+ "		Signature");
				Font font47 = FontFactory.getFont("TIMES_ROMAN");
				font47.setStyle(Font.NORMAL);
				font47.setSize(7);
				chunk47.setFont(font47);
				Paragraph paragraph47 = new Paragraph();
				paragraph47.add(chunk47);
				paragraph47.setAlignment(Element.ALIGN_LEFT);
				paragraph47.setSpacingBefore(45);

				Chunk chunk48 = new Chunk("																																																																																																																																																																																																																																		"
						+ "	Signature");
				Font font48 = FontFactory.getFont("TIMES_ROMAN");
				font48.setStyle(Font.NORMAL);
				font48.setSize(7);
				chunk48.setFont(font48);
				Paragraph paragraph48 = new Paragraph();
				paragraph48.add(chunk48);
				paragraph48.setAlignment(Element.ALIGN_LEFT);
				paragraph48.setSpacingBefore(-16);

				Chunk chunk49 = new Chunk("Signature");
				Font font49 = FontFactory.getFont("TIMES_ROMAN");
				font49.setStyle(Font.NORMAL);
				font49.setSize(7);
				chunk49.setFont(font49);
				Paragraph paragraph49 = new Paragraph();
				paragraph49.add(chunk49);
				paragraph49.setAlignment(Element.ALIGN_RIGHT);
				paragraph49.setSpacingBefore(-16);
				
				if(status.equalsIgnoreCase("C")) {
					// add watermark
			        writer.setPageEvent(new WatermarkPageEvent(writer, document, "Cancel", 170.5f, 330, 45, 90));
			        writer.setPageEvent(new WatermarkPageEvent(writer, document, "Cancel", 440.5f, 330, 45, 90));
			        writer.setPageEvent(new WatermarkPageEvent(writer, document, "Cancel", 710.5f, 330, 45, 90));
				}

				// Now Insert Every Thing Into PDF Document
//				document.open();// PDF document opened........

				// document.add(image);

//				document.add(Chunk.NEWLINE); // Something like in HTML :-)

				// for column width size
				float[] columnWidths = new float[] { 4f, 10f, 3f, 4f };
				// table.setWidths(columnWidths);
				// table1.setWidths(columnWidths);
				tableStudent.setWidths(columnWidths);
				tableOffice.setWidths(columnWidths);
				tableTeach.setWidths(columnWidths);

				if(!fee_header_0.trim().equalsIgnoreCase("")){
					document.add(paragraph);
					document.add(paragraph1);
					document.add(paragrapha);
				}
				if(!fee_header_1.trim().equalsIgnoreCase("")){
					document.add(paragraph2);
					document.add(paragraphb);
					document.add(paragraphc);
				}
				if(!fee_header_2.trim().equalsIgnoreCase("")){
					document.add(paragraphHeader2);
					document.add(paraOfficeHeader2);
					document.add(paraTechHeader2);
				}
				document.add(paragraphr);
				document.add(paragraphs);
				document.add(paragrapht);
				document.add(paragraphAcademic);
				document.add(paragraph3);
				document.add(paragraph3a);
				document.add(paragraphd);
				document.add(paragraphe);
				document.add(paragraphf);
				document.add(paragraphg);
				
//				if(optional.equalsIgnoreCase("No")){
					document.add(paragraph32);
					document.add(paragraphh);
					document.add(paragraphi);
//				}
				
				document.add(paragraph4);
				document.add(paragraphj);
				document.add(paragraphk);
				document.add(paragraph41);
				document.add(paragraphl);
				document.add(paragraphm);
				document.add(paragraph44);
				document.add(paragraphn);
				document.add(paragrapho);
				document.add(paragraph45);
				document.add(paragraphp);
				document.add(paragraphq);
				document.add(tableStudent);
				document.add(tableOffice);
				document.add(tableTeach);
				document.add(paragraph42);
				if(!words2.equalsIgnoreCase("")){
					document.add(paragraphWords2);
				}
				document.add(paragraph43);
				if(!words2.equalsIgnoreCase("")){
					document.add(paragraphOfficeWords2);
				}
				document.add(paragraph46);
				if(!words2.equalsIgnoreCase("")){
					document.add(paragraphTeachWords2);
				}
				
				if(!remark.trim().equalsIgnoreCase("")) {
					document.add(paragraphRemark);
					if(!remark2.equalsIgnoreCase("")){
						document.add(paragraphRemark2);
					}
					
					document.add(paragraphOfficeRemark);
					if(!remark2.equalsIgnoreCase("")){
						document.add(paragraphOfficeRemark2);
					}
					
					document.add(paragraphTeachRemark);
					if(!remark2.equalsIgnoreCase("")){
						document.add(paragraphTeachRemark2);
					}
				}
				document.add(paragraph50);
				document.add(paragraph51);
				document.add(paragraph52);
				document.add(paragraph53);
				document.add(paragraph54);
				document.add(paragraph55);
				document.add(paragraph47);
				document.add(paragraph48);
				document.add(paragraph49);
				// document.add(addTableSpace);

				// document.add(chunk);
				// document.add(chunk1);
				// document.add(chunk2);

//				document.add(Chunk.NEWLINE); // Something like in HTML :-)

//				document.newPage(); //Opened new page
				// document.add(list); //In the new page we are going to add list
				s = s+1;
				
				document.newPage();
			}
			
			

			document.close();

			file.close();

			logger.info("Pdf created successfully for .."+grNo);
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
					logger.error(":: -----Exception---- ::\n"+e);
					commonObj.logException(e);
				}
			}
		} catch (Exception e) {
			commonObj.logException(e);
		}
	}
}
