package org.com.maauli;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.DocumentException;
import com.itextpdf.text.PageSize;

public class CC_Book_PDF {

	static String fileName = "";
	static String fileAddress = "";
	static String path = "", remarkImagePath = "";
	static boolean fileOpenFlag = false;
	Common commonObj = new Common();
	TextToImage textToImage = new TextToImage();
	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	static Logger logger = Logger.getLogger(BonafidePDF.class.getName());

	public CC_Book_PDF(SessionData sessionData, String exam, String subject, String std, String div, String academic,
			LinkedHashMap<String, LinkedHashMap<String, String>> marksSemDataMap,
			LinkedHashMap<String, LinkedHashMap<String, String>> studentOptSubAllotMap,
			LinkedHashMap<String, LinkedHashMap<String, String>> maxSubMarks) throws DocumentException {

		System.gc();
		JFrame f = null;
		fileOpenFlag = true;
		String examHeader = "", sem = "", semInitial = "", subMarks = "", name = "", rollNo = "", row1Str = "";
		double totalA = 0, totalB = 0, total = 0, maxTotalA = 0, maxTotalB = 0, maxTotal = 0;
		int numCount = 1, studentCount = marksSemDataMap.size();
		String dob = "0", obt = "0", ora = "0", ass = "0", wri = "0", pra = "0", pre = "0", mca = "0", act = "0",
				pro = "0", oth = "0", ora1 = "0", pra1 = "0", wri1 = "0";
		String mdob = "", mobt = "", mora = "", mass = "", mwri = "", mpra = "", mpre = "", mmca = "", mact = "",
				mpro = "", moth = "", mora1 = "", mpra1 = "", mwri1 = "", optionalSubject = "";
		String[] optSubjectArr;
		LinkedHashMap<String, String> subMaxMarks = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> optSubMap = new LinkedHashMap<String, String>();
		LinkedHashMap subjectTitleMap = new LinkedHashMap<>();
		LinkedHashMap<String,LinkedHashMap<String, String>> studentRemarkMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
		DBValidate dbValidate = new DBValidate();
		List columnnList = new ArrayList();
		String filterFields = bundle.getString("FILTER_FIELDS_"+sessionData.getAppType().toUpperCase());
		String[] filterFieldsList = filterFields.split(",");
		LinkedHashMap<String, String> filterFieldsMap = new LinkedHashMap<String, String>(); 
		
		try {
			f = new JFrame("CC Book downlaod in progress. Don't Close");
			f.setBounds(sessionData.getScreenWidth()/2 - 150, sessionData.getScreenHeight()/2, 90, 25);
		    f.setSize(500, 0);
		    f.setResizable(false);
		    f.setVisible(true);
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
			int filterTableCount = 0;
			for(int j = 0; j < filterFieldsList.length; j++){
				filterFieldsMap.put(filterFieldsList[j], "");
				if(filterFieldsList[j].equalsIgnoreCase("grNo") || filterFieldsList[j].equalsIgnoreCase("birthDate")){
					filterTableCount = filterTableCount + 2;
				}
			}
			
			String footer = bundle.getString("FOOTER_"+sessionData.getAppType());
			
			if (exam.equalsIgnoreCase("Semester 1")) {
				sem = "sem1";
				semInitial = "F";
				examHeader = "First Term  " + academic;
			} else if (exam.equalsIgnoreCase("Semester 2")) {
				sem = "sem2";
				semInitial = "S";
				examHeader = "Second Term  "+academic;
			}
			path = commonObj.createTodayFolder(commonObj.getDriveName() + 
					bundle.getString("REPORT_PDF_PATH_" + sessionData.getDBName()), true)+ "/";
			remarkImagePath = commonObj.createFolder(commonObj.getDriveName()+"/"+sessionData.getDBName()+"_app/Remark_Images/");
			fileName = "CC_Book_" + std + "_" + div +"_" + academic + "_" + commonObj.timeInMillis() + ".pdf";
			fileAddress = path + fileName;
			Image img1 = null, img2 = null, img3 = null;
			Image img1Col2 = null, img2Col2 = null, img3Col2 = null;
			boolean isMarathiFont = false;
			File file = new File(fileAddress);
			FileOutputStream fileout = new FileOutputStream(file);
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, fileout);
			document.addHeader("ABC", "XYZ");
			document.addTitle("Marksheet Subjectwise");
			document.open();// PDF document opened........
			
			// for column width size
			float[] columnWidths = new float[] { 2.4f, 9.6f, 7f, 2.8f, 2.8f, 2.8f, 2.8f, 2.8f, 2.8f, 2.8f, 2.8f, 3.5f, 3f, 3f, 3f, 3.5f, 4f,
					4f };
			float[] columnWidthsRecord = new float[] {3.5f, 7f, 7f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3.5f, 3f, 3f, 3f, 3.5f, 4f, 4f};

			//font for unicode to marathi
		    final String FONT = "resources/fonts/MANGAL.TTF";//FreeSans
//		    BaseFont kruti_Dev = BaseFont.createFont("resources/fonts/MANGAL.TTF",BaseFont.CP1252,BaseFont.EMBEDDED);
			Font marathiFont = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			
			Set set = marksSemDataMap.entrySet();
			Iterator m = set.iterator();
			while (m.hasNext()) {
				
				maxTotalA = 0;
				maxTotalB = 0;
				maxTotal = 0;
				optionalSubject = "";
				f.setTitle(numCount + " / "+studentCount+" : CC Book downlaod in progress. Don't Close");
				numCount++;
				Map.Entry me = (Map.Entry) m.next();
				LinkedHashMap grMap = new LinkedHashMap();
				grMap = (LinkedHashMap) me.getValue();
				name = grMap.get("name").toString();
				rollNo = grMap.get("rollNo").toString();
				
				if(studentOptSubAllotMap.get(me.getKey()) != null){
					optionalSubject = studentOptSubAllotMap.get(me.getKey()).get("optionalSubject");
				}
				optSubjectArr = optionalSubject.split("\\|");
				optSubMap.clear();
				for(int i = 0; i < optSubjectArr.length; i++){
					optSubMap.put(optSubjectArr[i],"");
				}
				
				Chunk chunkHeader = new Chunk("FORMATIVE & SUMMATIVE EVALUATION RECORD CHART \n");
				Font font = FontFactory.getFont("TIMES_ROMAN");
				font.setStyle(Font.BOLD);
				font.setSize(13);
				chunkHeader.setFont(font);
//				chunkHeader.setFont(marathiFont);
				Paragraph paragraph = new Paragraph();
				paragraph.add(chunkHeader);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				paragraph.setSpacingBefore(5);

				Chunk chunkHeader1 = new Chunk("Name of the Pupil : \n");
				Font font1 = FontFactory.getFont("TIMES_ROMAN");
				font1.setStyle(Font.BOLD);
				font1.setSize(12);
				chunkHeader1.setFont(font1);
				Paragraph paragraph1 = new Paragraph();
				paragraph1.add(chunkHeader1);
				paragraph1.setAlignment(Element.ALIGN_LEFT);
				paragraph1.setSpacingBefore(5);

				Chunk chunkHeader2 = new Chunk("																																"
						+ "	"+commonObj.FirstWordCap(name));
				Font fonta = FontFactory.getFont("TIMES_ROMAN");
				fonta.setStyle(Font.NORMAL);
				fonta.setSize(12);
				chunkHeader2.setFont(fonta);
				Paragraph paragrapha = new Paragraph();
				paragrapha.add(chunkHeader2);
				paragrapha.setAlignment(Element.ALIGN_LEFT);
				paragrapha.setSpacingBefore(-16);

				Chunk chunk2 = new Chunk("																																																																																																																									"
						+ " 		Std. : "+std);
				Font font2 = FontFactory.getFont("TIMES_ROMAN");
				font2.setStyle(Font.BOLD);
				font2.setSize(10);
				chunk2.setFont(font2);
				Paragraph paragraph2 = new Paragraph();
				paragraph2.add(chunk2);
				paragraph2.setAlignment(Element.ALIGN_LEFT);
				paragraph2.setSpacingBefore(-16);

				Chunk chunkc = new Chunk(
						"																																																																																																																																																				Div. : "+div);
				Font fontc = FontFactory.getFont("TIMES_ROMAN");
				fontc.setStyle(Font.BOLD);
				fontc.setSize(10);
				chunkc.setFont(fontc);
				Paragraph paragraphc = new Paragraph();
				paragraphc.add(chunkc);
				paragraphc.setAlignment(Element.ALIGN_LEFT);
				paragraphc.setSpacingBefore(-16);

				Chunk chunks = new Chunk(
						"																																																																																																																																																																								Roll No. :");
				Font fonts = FontFactory.getFont("TIMES_ROMAN");
				fonts.setStyle(Font.BOLD);
				fonts.setSize(10);
				chunks.setFont(fonts);
				Paragraph paragraphs = new Paragraph();
				paragraphs.add(chunks);
				paragraphs.setAlignment(Element.ALIGN_LEFT);
				paragraphs.setSpacingBefore(-16);

				Chunk chunkt = new Chunk("																																																																																																																																																																																				"
						+ "		"+rollNo);
				Font fontt = FontFactory.getFont("TIMES_ROMAN");
				fontt.setStyle(Font.NORMAL);
				fontt.setSize(10);
				chunkt.setFont(fontt);
				Paragraph paragrapht = new Paragraph();
				paragrapht.add(chunkt);
				paragrapht.setAlignment(Element.ALIGN_LEFT);
				paragrapht.setSpacingBefore(-16);

				Chunk chunk3 = new Chunk(examHeader);
				Font font3 = FontFactory.getFont("TIMES_ROMAN");
				font3.setStyle(Font.BOLD);
				font3.setSize(11);
				chunk3.setFont(font3);
				Paragraph paragraph3 = new Paragraph();
				paragraph3.add(chunk3);
				paragraph3.setAlignment(Element.ALIGN_CENTER);
				paragraph3.setSpacingBefore(0);

				PdfPTable table2 = new PdfPTable(18);
				table2.setWidths(columnWidths);

				table2.setWidthPercentage(100);
				table2.setSpacingBefore(10.0f);

				PdfPCell cell301 = new PdfPCell(new Paragraph("Sr. No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));
				cell301.setColspan(1);
				cell301.setRowspan(3);
				cell301.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell301.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell301.setBackgroundColor (new BaseColor (110, 110, 110));
				table2.addCell(cell301);

				PdfPCell cell302 = new PdfPCell(
						new Paragraph("Particular", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
				cell302.setColspan(2);
				cell302.setRowspan(2);
				cell302.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell302.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell302.setBackgroundColor (new BaseColor (110, 110, 110));
				table2.addCell(cell302);

				PdfPCell cell306 = new PdfPCell(
						new Paragraph("(A) Formative Evaluation", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell306.setColspan(9);
				// cell306.setRowspan (2);
				cell306.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell306.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell306.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell306);

				PdfPCell cell307 = new PdfPCell(
						new Paragraph("(B) Summative Evaluation", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell307.setColspan(4);
				// cell307.setRowspan (2);
				cell307.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell307.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell307.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell307);

				PdfPCell cell308 = new PdfPCell(
						new Paragraph("Total (A+B)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell308.setColspan(1);
				cell308.setRowspan(3);
				cell308.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell308.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell308.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell308);

				PdfPCell cell309 = new PdfPCell(new Paragraph("Grade", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell309.setColspan(1);
				cell309.setRowspan(3);
				cell309.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell309.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell309.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell309);

				PdfPCell cell312 = new PdfPCell(new Paragraph("DOB", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell312.setColspan(1);
				// cell312.setRowspan (2);
				cell312.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell312.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell312.setRotation(90);
				// cell312.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell312);

				PdfPCell cell313 = new PdfPCell(new Paragraph("Oral", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell313.setColspan(1);
				// cell313.setRowspan (2);
				cell313.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell313.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell313.setRotation(90);
				// cell313.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell313);

				PdfPCell cell314 = new PdfPCell(
						new Paragraph("Practical", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell314.setColspan(1);
				// cell314.setRowspan (2);
				cell314.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell314.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell314.setRotation(90);
				// cell314.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell314);

				PdfPCell cell315 = new PdfPCell(
						new Paragraph("Activity", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell315.setColspan(1);
				// cell315.setRowspan (2);
				cell315.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell315.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell315.setRotation(90);
				// cell315.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell315);

				PdfPCell cell316 = new PdfPCell(new Paragraph("Project", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell316.setColspan(1);
				// cell316.setRowspan (2);
				cell316.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell316.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell316.setRotation(90);
				// cell316.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell316);

				PdfPCell cell317 = new PdfPCell(new Paragraph("Test", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell317.setColspan(1);
				// cell317.setRowspan (2);
				cell317.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell317.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell317.setRotation(90);
				// cell317.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell317);

				PdfPCell cell318 = new PdfPCell(new Paragraph("Assign", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell318.setColspan(1);
				// cell318.setRowspan (2);
				cell318.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell318.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell318.setRotation(90);
				// cell318.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell318);

				PdfPCell cell319 = new PdfPCell(new Paragraph("Others", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell319.setColspan(1);
				// cell319.setRowspan (2);
				cell319.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell319.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell319.setRotation(90);
				// cell319.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell319);

				PdfPCell cell320 = new PdfPCell(new Paragraph("Total", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell320.setColspan(1);
				// cell320.setRowspan (2);
				cell320.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell320.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell320.setRotation(90);
				// cell320.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell320);

				PdfPCell cell321 = new PdfPCell(new Paragraph("Oral", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell321.setColspan(1);
				// cell321.setRowspan (2);
				cell321.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell321.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell321.setRotation(90);
				// cell321.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell321);

				PdfPCell cell322 = new PdfPCell(
						new Paragraph("Practical", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell322.setColspan(1);
				// cell322.setRowspan (2);
				cell322.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell322.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell322.setRotation(90);
				// cell322.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell322);

				PdfPCell cell323 = new PdfPCell(new Paragraph("Written", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell323.setColspan(1);
				// cell323.setRowspan (2);
				cell323.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell323.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell323.setRotation(90);
				// cell323.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell323);

				PdfPCell cell324 = new PdfPCell(new Paragraph("Total", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell324.setColspan(1);
				// cell324.setRowspan (2);
				cell324.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell324.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell324.setRotation(90);
				// cell324.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell324);

				PdfPCell cell303 = new PdfPCell(new Paragraph("Subject", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
				cell303.setColspan(2);
				cell303.setRowspan(1);
				cell303.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell303.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell303.setBackgroundColor (new BaseColor (110, 110, 110));
				table2.addCell(cell303);

				PdfPCell cell304 = new PdfPCell(new Paragraph("1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell304.setColspan(1);
				// cell304.setRowspan (2);
				cell304.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell304.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell304.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell304);

				PdfPCell cell305 = new PdfPCell(new Paragraph("2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell305.setColspan(1);
				// cell305.setRowspan (2);
				cell305.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell305.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell305.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell305);

				PdfPCell cell310 = new PdfPCell(new Paragraph("3", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell310.setColspan(1);
				// cell310.setRowspan (2);
				cell310.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell310.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell310.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell310);

				PdfPCell cell311 = new PdfPCell(new Paragraph("4", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell311.setColspan(1);
				// cell311.setRowspan (2);
				cell311.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell311.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell311.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell311);

				PdfPCell cell325 = new PdfPCell(new Paragraph("5", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell325.setColspan(1);
				// cell325.setRowspan (2);
				cell325.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell325.setVerticalAlignment(Element.ALIGN_MIDDLE);
				// cell325.setBackgroundColor (new BaseColor (99, 99, 99));
				table2.addCell(cell325);

				PdfPCell cell326 = new PdfPCell(new Paragraph("6", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell326.setColspan(1);
				cell326.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell326);

				PdfPCell cell326a = new PdfPCell(new Paragraph("7", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell326a.setColspan(1);
				cell326a.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326a.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell326a);

				PdfPCell cell326b = new PdfPCell(new Paragraph("8", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell326b.setColspan(1);
				cell326b.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326b.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell326b);

				PdfPCell cell326c = new PdfPCell(new Paragraph("(A)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell326c.setColspan(1);
				cell326c.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326c.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell326c);

				PdfPCell cell326d = new PdfPCell(new Paragraph("9", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell326d.setColspan(1);
				cell326d.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326d.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell326d);

				PdfPCell cell326e = new PdfPCell(new Paragraph("10", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell326e.setColspan(1);
				cell326e.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326e.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell326e);

				PdfPCell cell326f = new PdfPCell(new Paragraph("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell326f.setColspan(1);
				cell326f.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326f.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell326f);

				PdfPCell cell326g = new PdfPCell(new Paragraph("(B)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
				cell326g.setColspan(1);
				cell326g.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell326g.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table2.addCell(cell326g);

				int rowCount = 1;
				int doubleLineSubject = 0;
				Set setMax = maxSubMarks.entrySet();
				Iterator n = setMax.iterator();
				while (n.hasNext()) {
					Map.Entry meSubject = (Map.Entry) n.next();
					subject = meSubject.getKey().toString();
					subMaxMarks = maxSubMarks.get(subject);
					
					if(optSubMap.containsKey((subject+"_NO"))){
						continue;
					}
					columnnList.add(subject);
					subjectTitleMap.put(subject, subject);
					
					PdfPCell cell327 = new PdfPCell(new Paragraph(rowCount+"", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell327.setColspan(1);
					cell327.setRowspan(2);
					cell327.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell327.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell327);
					
					if(subject.contains("_")) {
						doubleLineSubject = doubleLineSubject + 1;
					}
					PdfPCell cell328 = new PdfPCell(new Paragraph(subject.replace("_", " "), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell328.setColspan(1);
					cell328.setRowspan(2);
					cell328.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell328.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell328);

					PdfPCell cell329 = new PdfPCell(
							new Paragraph("Marks out of", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell329.setColspan(1);
					cell329.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell329.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell329);

					if(Double.parseDouble(subMaxMarks.get(sem+"_dobs")) == 0){
						mdob = "-";
					}
					else{
						mdob = subMaxMarks.get(sem+"_dobs");
						maxTotalA = Double.parseDouble(mdob);
					}
					PdfPCell cell330 = new PdfPCell(new Paragraph(mdob, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell330.setColspan(1);
					cell330.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell330.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell330);

					if(Double.parseDouble(subMaxMarks.get(sem+"_oral")) == 0){
						mora = "-";
					}
					else{
						mora = subMaxMarks.get(sem+"_oral");
						maxTotalA = maxTotalA + Double.parseDouble(mora);
					}
					PdfPCell cell331 = new PdfPCell(new Paragraph(mora, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell331.setColspan(1);
					cell331.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell331.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell331);

					if(Double.parseDouble(subMaxMarks.get(sem+"_pract")) == 0){
						mpra = "-";
					}
					else{
						mpra = subMaxMarks.get(sem+"_pract");
						maxTotalA = maxTotalA + Double.parseDouble(mpra);
					}
					PdfPCell cell332 = new PdfPCell(new Paragraph(mpra, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell332.setColspan(1);
					cell332.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell332.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell332);

					if(Double.parseDouble(subMaxMarks.get(sem+"_activity")) == 0){
						mact = "-";
					}
					else{
						mact = subMaxMarks.get(sem+"_activity");
						maxTotalA = maxTotalA + Double.parseDouble(mact);
					}
					PdfPCell cell333 = new PdfPCell(new Paragraph(mact, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell333.setColspan(1);
					cell333.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell333.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell333);

					if(Double.parseDouble(subMaxMarks.get(sem+"_project")) == 0){
						mpro = "-";
					}
					else{
						mpro = subMaxMarks.get(sem+"_project");
						maxTotalA = maxTotalA + Double.parseDouble(mpro);
					}
					PdfPCell cell334 = new PdfPCell(new Paragraph(mpro, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell334.setColspan(1);
					cell334.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell334.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell334);

					if(Double.parseDouble(subMaxMarks.get(sem+"_obt")) == 0){
						mobt = "-";
					}
					else{
						mobt = subMaxMarks.get(sem+"_obt");
						maxTotalA = maxTotalA + Double.parseDouble(mobt);
					}
					PdfPCell cell335 = new PdfPCell(new Paragraph(mobt, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell335.setColspan(1);
					cell335.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell335.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell335);

					if(Double.parseDouble(subMaxMarks.get(sem+"_assign")) == 0){
						mass = "-";
					}
					else{
						mass = subMaxMarks.get(sem+"_assign");
						maxTotalA = maxTotalA + Double.parseDouble(mass);
					}
					PdfPCell cell336 = new PdfPCell(new Paragraph(mass, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell336.setColspan(1);
					cell336.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell336.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell336);

					if(Double.parseDouble(subMaxMarks.get(sem+"_other")) == 0){
						moth = "-";
					}
					else{
						moth = subMaxMarks.get(sem+"_other");
						maxTotalA = maxTotalA + Double.parseDouble(moth);
					}
					PdfPCell cell337 = new PdfPCell(new Paragraph(moth, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell337.setColspan(1);
					cell337.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell337.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell337);

					PdfPCell cell338 = new PdfPCell(new Paragraph(String.format("%.0f", maxTotalA), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell338.setColspan(1);
					cell338.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell338.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell338);

					if(Double.parseDouble(subMaxMarks.get(sem+"_oral1")) == 0){
						mora1 = "-";
					}
					else{
						mora1 = subMaxMarks.get(sem+"_oral1");
						maxTotalB = Double.parseDouble(mora1);
					}
					PdfPCell cell339 = new PdfPCell(new Paragraph(mora1, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell339.setColspan(1);
					cell339.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell339.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell339);

					if(Double.parseDouble(subMaxMarks.get(sem+"_pract1")) == 0){
						mpra1 = "-";
					}
					else{
						mpra1 = subMaxMarks.get(sem+"_pract1");
						maxTotalB = maxTotalB + Double.parseDouble(mpra1);
					}
					PdfPCell cell340 = new PdfPCell(new Paragraph(mpra1, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell340.setColspan(1);
					cell340.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell340.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell340);

					if(Double.parseDouble(subMaxMarks.get(sem+"_write1")) == 0){
						mwri1 = "-";
					}
					else{
						mwri1 = subMaxMarks.get(sem+"_write1");
						maxTotalB = maxTotalB + Double.parseDouble(mwri1);
					}
					PdfPCell cell341 = new PdfPCell(new Paragraph(mwri1, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell341.setColspan(1);
					cell341.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell341.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell341);

					PdfPCell cell342 = new PdfPCell(new Paragraph(String.format("%.0f", maxTotalB), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell342.setColspan(1);
					cell342.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell342.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell342);

					PdfPCell cell343 = new PdfPCell(new Paragraph(String.format("%.0f", (maxTotalA+maxTotalB)), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell343.setColspan(1);
					cell343.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell343.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell343);

					PdfPCell cell344 = new PdfPCell(new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell344.setColspan(1);
					cell344.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell344.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell344);

					PdfPCell cell347 = new PdfPCell(
							new Paragraph("Marks obtained", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					cell347.setColspan(1);
					cell347.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell347.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell347);
					
					if(mdob.equalsIgnoreCase("-")){
						dob = "-";
					} else{
						dob = grMap.get(subject+"_"+semInitial+"DOB").toString();
						if(!dob.equalsIgnoreCase("AB") && !dob.equalsIgnoreCase("MG") && !dob.equalsIgnoreCase("") && !dob.equalsIgnoreCase("-"))
						{
							totalA = Double.parseDouble(dob);
						}
					}

					PdfPCell cell348 = new PdfPCell(new Paragraph(dob, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell348.setColspan(1);
					cell348.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell348.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell348);

					if(mora.equalsIgnoreCase("-")){
						ora = "-";
					} else{
						ora = grMap.get(subject+"_"+semInitial+"ORA").toString();
						if(!ora.equalsIgnoreCase("AB") && !ora.equalsIgnoreCase("MG") && !ora.equalsIgnoreCase("") && !ora.equalsIgnoreCase("-"))
							totalA = totalA + Double.parseDouble(ora);
					}
					PdfPCell cell349 = new PdfPCell(new Paragraph(ora, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell349.setColspan(1);
					cell349.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell349.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell349);

					if(mpra.equalsIgnoreCase("-")){
						pra = "-";
					} else{
						pra = grMap.get(subject+"_"+semInitial+"PRA").toString();
						if(!pra.equalsIgnoreCase("AB") && !pra.equalsIgnoreCase("MG") && !pra.equalsIgnoreCase("") && !pra.equalsIgnoreCase("-"))
						totalA = totalA + Double.parseDouble(pra);
					}
					PdfPCell cell350 = new PdfPCell(new Paragraph(pra, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell350.setColspan(1);
					cell350.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell350.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell350);

					if(mact.equalsIgnoreCase("-")){
						act = "-";
					} else{
						act = grMap.get(subject+"_"+semInitial+"ACT").toString();
						if(!act.equalsIgnoreCase("AB") && !act.equalsIgnoreCase("MG") && !act.equalsIgnoreCase("") && !act.equalsIgnoreCase("-"))
						totalA = totalA + Double.parseDouble(act);
					}
					PdfPCell cell351 = new PdfPCell(new Paragraph(act, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell351.setColspan(1);
					cell351.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell351.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell351);

					if(mpro.equalsIgnoreCase("-")){
						pro = "-";
					} else{
						pro = grMap.get(subject+"_"+semInitial+"PRO").toString();
						if(!pro.equalsIgnoreCase("AB") && !pro.equalsIgnoreCase("MG") && !pro.equalsIgnoreCase("") && !pro.equalsIgnoreCase("-"))
						totalA = totalA + Double.parseDouble(pro);
					}
					PdfPCell cell352 = new PdfPCell(new Paragraph(pro, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell352.setColspan(1);
					cell352.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell352.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell352);

					if(mobt.equalsIgnoreCase("-")){
						obt = "-";
					}
					else{
						obt = grMap.get(subject+"_"+semInitial+"OBT").toString();
						if(!obt.equalsIgnoreCase("AB") && !obt.equalsIgnoreCase("MG") && !obt.equalsIgnoreCase("") && !obt.equalsIgnoreCase("-"))
						totalA = totalA + Double.parseDouble(obt);
					}
					PdfPCell cell353 = new PdfPCell(new Paragraph(obt, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell353.setColspan(1);
					cell353.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell353.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell353);

					if(mass.equalsIgnoreCase("-")){
						ass = "-";
					}
					else{
						ass = grMap.get(subject+"_"+semInitial+"ASS").toString();
						if(!ass.equalsIgnoreCase("AB") && !ass.equalsIgnoreCase("MG") && !ass.equalsIgnoreCase("") && !ass.equalsIgnoreCase("-"))
						totalA = totalA + Double.parseDouble(ass);
					}
					PdfPCell cell354 = new PdfPCell(new Paragraph(ass, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell354.setColspan(1);
					cell354.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell354.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell354);

					if(moth.equalsIgnoreCase("-")){
						oth = "-";
					}
					else{
						oth = grMap.get(subject+"_"+semInitial+"OTH").toString();
						if(!oth.equalsIgnoreCase("AB") && !oth.equalsIgnoreCase("MG") && !oth.equalsIgnoreCase("") && !oth.equalsIgnoreCase("-"))
						totalA = totalA + Double.parseDouble(oth);
					}
					PdfPCell cell355 = new PdfPCell(new Paragraph(oth, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell355.setColspan(1);
					cell355.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell355.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell355);

					PdfPCell cell356 = new PdfPCell(new Paragraph(String.format("%.0f", totalA), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell356.setColspan(1);
					cell356.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell356.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell356);

					if(mora1.equalsIgnoreCase("-")){
						ora1 = "-";
					}
					else{
						ora1 = grMap.get(subject+"_"+semInitial+"ORA1").toString();
						if(!ora1.equalsIgnoreCase("AB") && !ora1.equalsIgnoreCase("MG") && !ora1.equalsIgnoreCase("") && !ora1.equalsIgnoreCase("-"))
						totalB = Double.parseDouble(ora1);
					}
					PdfPCell cell357 = new PdfPCell(new Paragraph(ora1, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell357.setColspan(1);
					cell357.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell357.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell357);

					if(mpra1.equalsIgnoreCase("-")){
						pra1 = "-";
					}
					else{
						pra1 = grMap.get(subject+"_"+semInitial+"PRA1").toString();
						if(!pra1.equalsIgnoreCase("AB") && !pra1.equalsIgnoreCase("MG") && !pra1.equalsIgnoreCase("") && !pra1.equalsIgnoreCase("-"))
						totalB =  totalB + Double.parseDouble(pra1);
					}
					PdfPCell cell358 = new PdfPCell(new Paragraph(pra1, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell358.setColspan(1);
					cell358.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell358.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell358);

					if(mwri1.equalsIgnoreCase("-")){
						wri1 = "-";
					}
					else{
						wri1 = grMap.get(subject+"_"+semInitial+"WRI1").toString();
						if(!wri1.equalsIgnoreCase("AB") && !wri1.equalsIgnoreCase("MG") && !wri1.equalsIgnoreCase("") && !wri1.equalsIgnoreCase("-"))
						totalB =  totalB + Double.parseDouble(wri1);
					}
					PdfPCell cell359 = new PdfPCell(new Paragraph(wri1, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell359.setColspan(1);
					cell359.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell359.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell359);

					PdfPCell cell360 = new PdfPCell(new Paragraph(String.format("%.0f", totalB), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell360.setColspan(1);
					cell360.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell360.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell360);

					PdfPCell cell361 = new PdfPCell(new Paragraph(String.format("%.0f", (totalA+totalB)), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell361.setColspan(1);
					cell361.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell361.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell361);

					PdfPCell cell362 = new PdfPCell(new Paragraph(commonObj.getGradeFromMarks((maxTotalA+maxTotalB), "", (totalA+totalB), std), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					cell362.setColspan(1);
					cell362.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell362.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table2.addCell(cell362);

					totalA = 0; totalB = 0; maxTotalA = 0; maxTotalB = 0;
					rowCount++;
				}

				table2.setSpacingAfter(80.0f);
				
				Chunk chunk6 = new Chunk("DESCRIPTIVE RECORD OF FORMATIVE EVALUATION");
				Font font6 = FontFactory.getFont("TIMES_ROMAN");
				font6.setStyle(Font.BOLD);
				font6.setSize(15);
				chunk6.setFont(font6);
				Paragraph paragraph6 = new Paragraph();
				paragraph6.add(chunk6);
				paragraph6.setAlignment(Element.ALIGN_CENTER);
				paragraph6.setSpacingAfter(5);
				paragraph6.setSpacingBefore(-70);

				PdfPTable table3 = new PdfPTable(18);
				table3.setWidths(columnWidthsRecord);

				table3.setWidthPercentage(100);
				table3.setHorizontalAlignment(Element.ALIGN_CENTER);

				List columnn1List = new ArrayList();
				List columnn2List = new ArrayList();
				columnnList.add("Special Progress");
				columnnList.add("Interest/Hobby");
				columnnList.add("Improvement\nNeeded");
				if(!filterFieldsMap.containsKey("Personal\nCharacteristic")){
					columnnList.add("Personal\nCharacteristic");
				}
				if(columnnList.size() % 2 != 0){
					columnnList.add("");
				}
				if(dbValidate.connectDatabase(sessionData)) {
					studentRemarkMap = dbValidate.getRemarkResultMap(sessionData, academic, std, div, exam, subjectTitleMap);
				}
				int size = maxSubMarks.size()+4;
				int count = 0;
//				Set setSubject = maxSubMarks.entrySet();
//				Iterator p = setMax.iterator();
				
				for(int i=0; i < columnnList.size();i++) {
					if(count < size/2){
						columnn1List.add(commonObj.FirstWordCap(columnnList.get(i).toString()));
					}
					else{
						columnn2List.add(commonObj.FirstWordCap(columnnList.get(i).toString()));
					}
					count++;
				}

				String col1Subject = "", col2Subject = "";
				for(int k = 0; k < columnn1List.size(); k++){
					col1Subject = columnn1List.get(k).toString();
					col2Subject = columnn2List.get(k).toString();
					if(studentRemarkMap.get(me.getKey()) == null) {
						continue;
					}
					if(!columnn1List.get(k).toString().equalsIgnoreCase("Special Progress") && !columnn1List.get(k).toString().equalsIgnoreCase("Interest/Hobby") && 
							!columnn1List.get(k).toString().equalsIgnoreCase("Improvement\nNeeded")) {
						col1Subject = studentRemarkMap.get(me.getKey().toString()).get(columnn1List.get(k).toString().toUpperCase()+"_REM"+sem.toUpperCase())+"";
					}
					else {
						col1Subject = "";
					}
					
					if(!columnn2List.get(k).toString().equalsIgnoreCase("Special Progress") && !columnn2List.get(k).toString().equalsIgnoreCase("Interest/Hobby") && 
							!columnn2List.get(k).toString().equalsIgnoreCase("Improvement\nNeeded")) {
						col2Subject = studentRemarkMap.get(me.getKey().toString()).get(columnn2List.get(k).toString().toUpperCase()+"_REM"+sem.toUpperCase())+"";
					}
					
					if(columnn2List.get(k).toString().equalsIgnoreCase("Special Progress")) {
						col2Subject = studentRemarkMap.get(me.getKey().toString()).get(columnn2List.get(k).toString().toUpperCase()+"_"+sem.toUpperCase())+"";
						if(!col2Subject.equalsIgnoreCase("poor")) {
							col2Subject = col2Subject + " \nAcquired ability level";
						}
					}
					
					if(columnn2List.get(k).toString().equalsIgnoreCase("Interest/Hobby")) {
						col2Subject = studentRemarkMap.get(me.getKey().toString()).get(columnn2List.get(k).toString())+"";
					}
					
					if(columnn2List.get(k).toString().equalsIgnoreCase("Improvement\nNeeded")) {
						col2Subject = studentRemarkMap.get(me.getKey().toString()).get(columnn2List.get(k).toString().replace("\n", "-").toUpperCase()+"_"+sem.toUpperCase())+"";
					}
					
					if(col1Subject.equalsIgnoreCase("-") || col1Subject.equalsIgnoreCase("null")) {
						col1Subject = "";
					}
					if(col2Subject.equalsIgnoreCase("-") || col2Subject.equalsIgnoreCase("null")) {
						col2Subject = "";
					}
					
					PdfPCell cell686, cell687 = null, cell688, cell689;
					float paddingValue = 0.0f, mainPaddingValue=16.0f;
					if(doubleLineSubject > 2) {
						paddingValue = (doubleLineSubject - 2)*2.0f;
					}
					if(maxSubMarks.size()>9) {
						mainPaddingValue = 14.0f;
					}
					cell686 = new PdfPCell (new Paragraph (columnn1List.get(k).toString().replace("_", " "), FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));
					cell686.setColspan (2);
					cell686.setHorizontalAlignment (Element.ALIGN_LEFT);
					cell686.setVerticalAlignment (Element.ALIGN_MIDDLE);
					cell686.setPadding (mainPaddingValue-paddingValue);
					cell686.setPaddingLeft(3.0f);
					table3.addCell(cell686);
				    
					col1Subject = commonObj.revertCommaApostrophy(col1Subject);
					Chunk chunkMarathi = new Chunk(col1Subject);
					chunkMarathi.setFont(marathiFont);
					if(commonObj.validateMarathi(col1Subject) || col1Subject.equalsIgnoreCase("")) {
						cell687 = new PdfPCell (new Paragraph (col1Subject, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
						cell687.setPaddingTop(2.0f);
						cell687.setColspan (6);
						cell687.setHorizontalAlignment (Element.ALIGN_LEFT );
						cell687.setVerticalAlignment (Element.ALIGN_TOP);
						table3.addCell(cell687);
					}
					else {
						isMarathiFont = true;
						String line1 = "", line2 = "", line3 = "";
						cell687 = new PdfPCell ();
						
						if(col1Subject.length() > 30) {
							List remarkList = commonObj.breakSentence(col1Subject, 30);
							if(remarkList != null){
								line1 = remarkList.get(0).toString().trim();
								if (!commonObj.fileExists(remarkImagePath+commonObj.replaceCommaApostrophy(line1)+".jpg")) {
									textToImage.Text2Image(line1,commonObj.replaceCommaApostrophy(line1),remarkImagePath);
								}
								img1 = Image.getInstance(remarkImagePath+commonObj.replaceCommaApostrophy(line1)+".jpg");
								img1.scalePercent(43f);
								cell687.setPaddingTop(5.0f);
								cell687.addElement(img1);
								if(remarkList.size() < 2){
									line2 = remarkList.get(1).toString().trim();
									if (!line2.trim().equalsIgnoreCase("")) {
										if (!commonObj.fileExists(remarkImagePath+commonObj.replaceCommaApostrophy(line2)+".jpg")) {
											textToImage.Text2Image(line2,line2,remarkImagePath);
										}
										img2 = Image.getInstance(remarkImagePath+commonObj.replaceCommaApostrophy(line2)+".jpg");
										img2.scalePercent(43f);
										cell687.setPaddingTop(5.0f);
										cell687.addElement(img2);
									}
								}
								else if(remarkList.size() > 2){
									line2 = remarkList.get(1).toString().trim();
									if (!line2.trim().equalsIgnoreCase("")) {
										if (!commonObj.fileExists(remarkImagePath+commonObj.replaceCommaApostrophy(line2)+".jpg")) {
											textToImage.Text2Image(line2,line2,remarkImagePath);
										}
										img2 = Image.getInstance(remarkImagePath+commonObj.replaceCommaApostrophy(line2)+".jpg");
										img2.scalePercent(43f);
										cell687.setPaddingTop(5.0f);
										cell687.addElement(img2);
									}
									
									line3 = remarkList.get(2).toString().trim();
									if (!line3.trim().equalsIgnoreCase("")) {
										if (!commonObj.fileExists(remarkImagePath+commonObj.replaceCommaApostrophy(line3)+".jpg")) {
											textToImage.Text2Image(line3,line3,remarkImagePath);
										}
										img3 = Image.getInstance(remarkImagePath+commonObj.replaceCommaApostrophy(line3)+".jpg");
										img3.scalePercent(43f);
										cell687.setPaddingTop(5.0f);
										cell687.addElement(img3);
									}
								}
							}
						}
						else if(!col1Subject.equalsIgnoreCase("")){
							line1 = col1Subject;
							if (!commonObj.fileExists(remarkImagePath+commonObj.replaceCommaApostrophy(line1)+".jpg")) {
								textToImage.Text2Image(line1,commonObj.replaceCommaApostrophy(line1),remarkImagePath);
							}
							img1 = Image.getInstance(remarkImagePath+commonObj.replaceCommaApostrophy(line1)+".jpg");
							img1.scalePercent(43f);
							cell687.addElement(img1);
						}
						cell687.setColspan (6);
						cell687.setHorizontalAlignment (Element.ALIGN_LEFT );
						cell687.setVerticalAlignment (Element.ALIGN_TOP);
						table3.addCell(cell687);
					}
				      
					cell688 = new PdfPCell (new Paragraph (columnn2List.get(k).toString().replace("_", " "), FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));
					cell688.setColspan (4);
					cell688.setHorizontalAlignment (Element.ALIGN_LEFT);
					cell688.setPaddingLeft(3.0f);
					cell688.setVerticalAlignment (Element.ALIGN_MIDDLE);
					table3.addCell(cell688);
			     
					col2Subject = commonObj.revertCommaApostrophy(col2Subject);
					if(commonObj.validateMarathi(col2Subject) || col2Subject.equalsIgnoreCase("") || col2Subject.contains("\n")) {
						cell689 = new PdfPCell (new Paragraph (col2Subject, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
						cell689.setColspan (6);
						cell689.setHorizontalAlignment (Element.ALIGN_LEFT);
						cell689.setVerticalAlignment (Element.ALIGN_TOP);				               
						table3.addCell(cell689);
					}
					else {
						isMarathiFont = true;
						String line1col2 = "", line2col2 = "", line3col2 = "";
						cell689 = new PdfPCell ();
						if(col2Subject.length() > 30) {
							List remarkList = commonObj.breakSentence(col2Subject, 30);
							if(remarkList != null){
								line1col2 = remarkList.get(0).toString().trim();
								if (!commonObj.fileExists(remarkImagePath+commonObj.replaceCommaApostrophy(line1col2)+".jpg")) {
									textToImage.Text2Image(line1col2,commonObj.replaceCommaApostrophy(line1col2),remarkImagePath);
								}
								img1Col2 = Image.getInstance(remarkImagePath+commonObj.replaceCommaApostrophy(line1col2)+".jpg");
								img1Col2.scalePercent(43f);
								cell689.addElement(img1Col2);
								
								if(remarkList.size() > 1){
									line2col2 = remarkList.get(1).toString().trim();
									if(!line2col2.trim().equalsIgnoreCase("")) {
										if (!commonObj.fileExists(remarkImagePath+commonObj.replaceCommaApostrophy(line2col2)+".jpg")) {
											textToImage.Text2Image(line2col2,line2col2,remarkImagePath);
										}
										img2Col2 = Image.getInstance(remarkImagePath+commonObj.replaceCommaApostrophy(line2col2)+".jpg");
										img2Col2.scalePercent(43f);
										cell689.addElement(img2Col2);
									}
								}
								else if(remarkList.size() > 2){
									line2col2 = remarkList.get(1).toString().trim();
									if(!line2col2.trim().equalsIgnoreCase("")) {
										if (!commonObj.fileExists(remarkImagePath+commonObj.replaceCommaApostrophy(line2col2)+".jpg")) {
											textToImage.Text2Image(line2col2,line2col2,remarkImagePath);
										}
										img2Col2 = Image.getInstance(remarkImagePath+commonObj.replaceCommaApostrophy(line2col2)+".jpg");
										img2Col2.scalePercent(43f);
										cell689.addElement(img2Col2);
									}
									
									line3col2 = remarkList.get(2).toString().trim();
									if(!line3col2.trim().equalsIgnoreCase("")) {
										if (!commonObj.fileExists(remarkImagePath+commonObj.replaceCommaApostrophy(line3col2)+".jpg")) {
											textToImage.Text2Image(line3col2,line3col2,remarkImagePath);
										}
										img3Col2 = Image.getInstance(remarkImagePath+commonObj.replaceCommaApostrophy(line3col2)+".jpg");
										img3Col2.scalePercent(43f);
										cell689.addElement(img3Col2);
									}
								}
							}
						}
						else if(!col2Subject.equalsIgnoreCase("")){
							line1col2 = col2Subject;
							if (!commonObj.fileExists(remarkImagePath+commonObj.replaceCommaApostrophy(line1col2)+".jpg")) {
								textToImage.Text2Image(line1col2,commonObj.replaceCommaApostrophy(line1col2),remarkImagePath);
							}
							img1Col2 = Image.getInstance(remarkImagePath+commonObj.replaceCommaApostrophy(line1col2)+".jpg");
							img1Col2.scalePercent(43f);
							cell689.addElement(img1Col2);
						}
						cell689.setColspan (6);
						cell689.setHorizontalAlignment (Element.ALIGN_LEFT);
						cell689.setVerticalAlignment (Element.ALIGN_TOP);				               
						table3.addCell(cell689);
					}
				}
				
				table3.setSpacingAfter(20.0f);
				
				Chunk chunk4 = new Chunk("													Class Teacher                                                                                                                          "+footer);
				Font font4 = FontFactory.getFont("TIMES_ROMAN");
				font4.setStyle(Font.BOLD);
				font4.setSize(9);
				chunk4.setFont(font4);
				Paragraph paragraph4 = new Paragraph();
				paragraph4.add(chunk4);
				paragraph4.setAlignment(Element.ALIGN_LEFT);
				paragraph4.setSpacingAfter(5);
				paragraph4.setSpacingBefore(10);

				/*Chunk chunk5 = new Chunk("																																																																																																																																																																																																																			"
						+ "	"+footer);
				Font font5 = FontFactory.getFont("TIMES_ROMAN");
				font5.setStyle(Font.BOLD);
				font5.setSize(7);
				chunk5.setFont(font5);
				Paragraph paragraph5 = new Paragraph();
				paragraph5.add(chunk5);
				paragraph5.setAlignment(Element.ALIGN_LEFT);
//				paragraph5.setSpacingAfter(15);
				paragraph5.setSpacingBefore(-26);*/

				document.add(paragraph);
				document.add(paragraph1);
				document.add(paragrapha);
				document.add(paragraph2);
//				document.add(paragraphb);
				document.add(paragraphc);
//				document.add(paragraphr);
				document.add(paragraphs);
				document.add(paragrapht);
				document.add(paragraph3);
				document.add(table2);
				document.add(paragraph6);
				document.add(table3);
				document.add(paragraph4);
//				if(isMarathiFont) {
//					String line1 = "", line2 = "", line3 = "";
//					String line1col2 = "", line2col2 = "", line3col2 = "";
//					int col1Height = 365,col2Height = 365, col1Width = 125, col2Width = 400;
//					if(maxSubMarks.size()>9) {
//						col1Height = 350;
//						col2Height = 350;
//					}
//					for(int k = 0; k < columnn1List.size(); k++){
//						col1Subject = columnn1List.get(k).toString();
//						col2Subject = columnn2List.get(k).toString();
//						
//						if(studentRemarkMap.get(me.getKey()) == null) {
//							continue;
//						}
//						if(!col1Subject.equalsIgnoreCase("Special Progress") && !col1Subject.equalsIgnoreCase("Interest/Hobby") && 
//								!col1Subject.equalsIgnoreCase("Improvement\nNeeded")) {
//							col1Subject = studentRemarkMap.get(me.getKey().toString()).get(columnn1List.get(k).toString().toUpperCase()+"_REM"+sem.toUpperCase())+"";
//						}
//						else {
//							col1Subject = "";
//						}
//						if(!col2Subject.equalsIgnoreCase("Special Progress") && !col2Subject.equalsIgnoreCase("Interest/Hobby") && 
//								!col2Subject.equalsIgnoreCase("Improvement\nNeeded")) {
//							col2Subject = studentRemarkMap.get(me.getKey().toString()).get(columnn2List.get(k).toString().toUpperCase()+"_REM"+sem.toUpperCase())+"";
//						}
//						else {
//							col2Subject = "";
//						}
//						if(col1Subject.equalsIgnoreCase("-") || col1Subject.equalsIgnoreCase("null")) {
//							col1Subject = "";
//						}
//						if(col2Subject.equalsIgnoreCase("-") || col2Subject.equalsIgnoreCase("null")) {
//							col2Subject = "";
//						}
//						
//						if(!commonObj.validateMarathi(col1Subject) && !col1Subject.equalsIgnoreCase("")) {
//							
//							if(col1Subject.length() > 25) {
//								List remarkList = commonObj.breakSentence(col1Subject, 25);
//								if(remarkList != null){
//									line1 = remarkList.get(0).toString().trim();
//									if (!commonObj.fileExists(remarkImagePath+commonObj.replaceCommaApostrophy(line1)+".jpg")) {
//										textToImage.Text2Image(line1,commonObj.replaceCommaApostrophy(line1),remarkImagePath);
//									}
//									img1 = Image.getInstance(remarkImagePath+commonObj.replaceCommaApostrophy(line1)+".jpg");
//									img1.scalePercent(43f);
//									img1.setAbsolutePosition(col1Width, col1Height);//1: 345
//									document.add(img1);
//									
//									if(remarkList.size() > 1){
//										line2 = remarkList.get(1).toString().trim();
//										if (!commonObj.fileExists(remarkImagePath+line2+".jpg")) {
//											textToImage.Text2Image(line2,line2,remarkImagePath);
//										}
//										img2 = Image.getInstance(remarkImagePath+line2+".jpg");
//										img2.scalePercent(43f);
//										img2.setAbsolutePosition(col1Width, col1Height-10);//2: 335
//										document.add(img2);
//									}
//									else if(remarkList.size() > 2){
//										line2 = remarkList.get(1).toString().trim();
//										if (!commonObj.fileExists(remarkImagePath+line2+".jpg")) {
//											textToImage.Text2Image(line2,line2,remarkImagePath);
//										}
//										img2 = Image.getInstance(remarkImagePath+line2+".jpg");
//										img2.scalePercent(43f);
//										img2.setAbsolutePosition(col1Width, col1Height-10);//2: 335
//										document.add(img2);
//										
//										line3 = remarkList.get(2).toString().trim();
//										if (!commonObj.fileExists(remarkImagePath+line3+".jpg")) {
//											textToImage.Text2Image(line3,line3,remarkImagePath);
//										}
//										img3 = Image.getInstance(remarkImagePath+line3+".jpg");
//										img3.scalePercent(43f);
//										img3.setAbsolutePosition(col1Width, col1Height-20);//3: 325
//										document.add(img3);
//									}
//								}
//							}
//							else if(!col1Subject.equalsIgnoreCase("")){
//								line1 = col1Subject;
//								if (!commonObj.fileExists(remarkImagePath+commonObj.replaceCommaApostrophy(line1)+".jpg")) {
//									textToImage.Text2Image(line1,commonObj.replaceCommaApostrophy(line1),remarkImagePath);
//								}
//								img1 = Image.getInstance(remarkImagePath+commonObj.replaceCommaApostrophy(line1)+".jpg");
//								img1.scalePercent(43f);
//								img1.setAbsolutePosition(col1Width, col1Height);//1: 345
//								document.add(img1);document.add(img1);
//							}
//						}
//						
//						if(!commonObj.validateMarathi(col2Subject) && !col2Subject.equalsIgnoreCase("")) {
//							
//							if(col2Subject.length() > 25) {
//								List remarkList = commonObj.breakSentence(col2Subject, 25);
//								if(remarkList != null){
//									line1col2 = remarkList.get(0).toString().trim();
//									if (!commonObj.fileExists(remarkImagePath+commonObj.replaceCommaApostrophy(line1col2)+".jpg")) {
//										textToImage.Text2Image(line1col2,commonObj.replaceCommaApostrophy(line1col2),remarkImagePath);
//									}
//									img1Col2 = Image.getInstance(remarkImagePath+commonObj.replaceCommaApostrophy(line1col2)+".jpg");
//									img1Col2.scalePercent(43f);
//									img1Col2.setAbsolutePosition(col2Width, col2Height);//1: 345
//									document.add(img1Col2);
//									
//									if(remarkList.size() > 1){
//										line2col2 = remarkList.get(1).toString().trim();
//										if (!commonObj.fileExists(remarkImagePath+line2col2+".jpg")) {
//											textToImage.Text2Image(line2col2,line2col2,remarkImagePath);
//										}
//										img2Col2 = Image.getInstance(remarkImagePath+line2col2+".jpg");
//										img2Col2.scalePercent(43f);
//										img2Col2.setAbsolutePosition(col2Width, col2Height-10);//2: 335
//										document.add(img2Col2);
//									}
//									else if(remarkList.size() > 2){
//										line2col2 = remarkList.get(1).toString().trim();
//										if (!commonObj.fileExists(remarkImagePath+line2col2+".jpg")) {
//											textToImage.Text2Image(line2col2,line2col2,remarkImagePath);
//										}
//										img2Col2 = Image.getInstance(remarkImagePath+line2col2+".jpg");
//										img2Col2.scalePercent(43f);
//										img2Col2.setAbsolutePosition(col2Width, col2Height-10);//2: 335
//										document.add(img2Col2);
//										
//										line3col2 = remarkList.get(2).toString().trim();
//										if (!commonObj.fileExists(remarkImagePath+line3col2+".jpg")) {
//											textToImage.Text2Image(line3col2,line3col2,remarkImagePath);
//										}
//										img3Col2 = Image.getInstance(remarkImagePath+line3col2+".jpg");
//										img3Col2.scalePercent(43f);
//										img3Col2.setAbsolutePosition(col2Width, col2Height-20);//3: 325
//										document.add(img3Col2);
//									}
//								}
//							}
//							else if(!col2Subject.equalsIgnoreCase("")){
//								line1col2 = col2Subject;
//								if (!commonObj.fileExists(remarkImagePath+commonObj.replaceCommaApostrophy(line1col2)+".jpg")) {
//									textToImage.Text2Image(line1col2,commonObj.replaceCommaApostrophy(line1col2),remarkImagePath);
//								}
//								img1Col2 = Image.getInstance(remarkImagePath+commonObj.replaceCommaApostrophy(line1col2)+".jpg");
//								img1Col2.scalePercent(43f);
//								img1Col2.setAbsolutePosition(col2Width, col2Height);//1: 345
//								document.add(img1Col2);document.add(img1Col2);
//							}
//						}
//						
//						col1Height = col1Height - 35;
//						col2Height = col2Height - 35;
//						
////						document.add(img1);
////						document.add(img2);
////						document.add(img3);
//					}
//				}
				isMarathiFont = false;
				document.newPage();
			}

			document.close();
			f.setVisible(false);
			JOptionPane.showMessageDialog(null, "Report generated successfully.");
		} catch (Exception e) {
			f.setVisible(false);
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