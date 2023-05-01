package org.com.maauli;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedHashMap;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class ClassPDF {
	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	static Logger logger = Logger.getLogger(ClassPDF.class.getName());
	DBValidate findStudentDB = new DBValidate();
	Common commonLc = new Common();
	static String fileName = "";
	static String fileAddress = "";
	static String path = "";
	static String user_name = "";
	static String user_role = "";
	static  boolean fileOpenFlag	= true;
	static String app_header = "";
	static String records_per_page = "";
	static String app_header_0_fontName = "";
    static int app_header_0_fontSize = 0;
    static int app_header_0_widthSpace = 0;
    static int app_header_0_heightSpace = 0;
    static String app_header_fontName = "";
    static int app_header_fontSize = 0;
    static int app_header_widthSpace = 0;
    static int app_header_heightSpace = 0;
    static String app_header_2 = "";
    static String app_header_2_fontName = "";
    static int app_header_2_fontSize = 0;
    static int app_header_2_widthSpace = 0;
    static int app_header_2_heightSpace = 0;

	public void ClassPDFGen(SessionData sessionData, LinkedHashMap studentMap, String std, String div,
			String academic, String retUserName, String retUserRole) {
		try {
			System.gc();
			fileOpenFlag	= true;
			user_name = retUserName;
			user_role = retUserRole;
			app_header = bundle.getString("CLASS_ALLOT_HEADER_"+sessionData.getAppType());
			records_per_page = bundle.getString("RECORDS_PER_PAGE_"+sessionData.getAppType());
			int classSize = 0;
			List<String> classDataList = new ArrayList();
			classDataList = findStudentDB.findClassData(sessionData, studentMap, std, div, academic);
			classSize = classDataList.size();
			logger.info("classDataList Size === "+classSize);
			String[] studentArray = new String[classSize];
		    studentArray = classDataList.toArray(studentArray);
		    logger.info("classDataList Size === "+classSize);
		    logger.info("studentArray === "+studentArray.length);
			
//			fileName = "Class" + std + div + academic + commonLc.timeInMillis()+ ".pdf";
		    fileName = "Class" + std + div + academic + commonLc.timeInMillis()+".pdf";
		    path = commonLc.createTodayFolder(commonLc.getDriveName() + bundle.getString("CLASS_PDF_PATH_"+sessionData.getDBName()),true)+"/";
//			path = commonLc.createTodayFolder("D:\\yash\\classPDF\\",false) + "\\";
//		    path = "D:\\yash\\classPDF\\";
			fileAddress = path + fileName;

			OutputStream file = new FileOutputStream(new File(fileAddress));
//			Document document = new Document(PageSize.A4.rotate());
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			
			// Now Insert Every Thing Into PDF Document
			document.open();// PDF document opened........

			// Inserting Table in PDF
			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100);
			
			PdfPCell topCell = new PdfPCell (new Paragraph (app_header, FontFactory.getFont(FontFactory.TIMES_ROMAN, 22)));
			topCell.setColspan(5);
			topCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			topCell.setPadding(18.0f);
			topCell.setBackgroundColor(new BaseColor(140, 221, 8));
			
			PdfPCell cell = new PdfPCell(new Paragraph("STD : " + std + "   DIV : " + div + "  (" + academic + ")"));
			cell.setColspan(5);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPadding(16.0f);
//			cell.setBackgroundColor(new BaseColor(140, 221, 8));

			///pdf headers//////////////////
			
			PdfPCell Rollcell = new PdfPCell(new Paragraph("Roll No"));
			Rollcell.setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPCell GRcell = new PdfPCell(new Paragraph("G.R.No"));
			GRcell.setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPCell NMcell = new PdfPCell(new Paragraph("Name of the Students"));
			NMcell.setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPCell Gendercell = new PdfPCell(new Paragraph("Gender"));
			Gendercell.setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPCell BDcell = new PdfPCell(new Paragraph("Birth Date"));
			BDcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			

			////////pdf data////////////////////////////
			for(int i=0;i<studentArray.length;i++){
		    	String name 		= studentArray[i].substring(0, studentArray[i].indexOf("|")); 
		    	String gr 			= studentArray[i].substring(studentArray[i].indexOf("|")+1, studentArray[i].indexOf("||")); 
			    String rollNo		= studentArray[i].substring(studentArray[i].indexOf("||")+2, studentArray[i].indexOf("|||")); 
	    		String gender 		= studentArray[i].substring(studentArray[i].indexOf("|||")+3, studentArray[i].indexOf("||||")); 
	    		String dob 			= studentArray[i].substring(studentArray[i].lastIndexOf("||||")+4); 
//	    		String gender 		= studentArray[i].substring(studentArray[i].indexOf("|||||")+5, studentArray[i].indexOf("||||||")); 
//	    		String dob 			= studentArray[i].substring(studentArray[i].lastIndexOf("||||||")+6); 
	    		
	    		if(i%Integer.parseInt(records_per_page)==0){
	    			table.addCell(topCell);
	    			table.addCell(cell);
	    			table.addCell(Rollcell);
	    			table.addCell(GRcell);
	    			table.addCell(NMcell);
	    			table.addCell(Gendercell);
	    			table.addCell(BDcell);
	    		}
				PdfPCell RN1cell = new PdfPCell(new Paragraph(rollNo));
				RN1cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(RN1cell);
	
				PdfPCell GR1cell = new PdfPCell(new Paragraph(gr));
				GR1cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(GR1cell);
	
				PdfPCell NM1cell = new PdfPCell(new Paragraph(name));
				NM1cell.setPaddingLeft(10f);
				NM1cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(NM1cell);
	
				PdfPCell G1cell = new PdfPCell(new Paragraph(gender));
				G1cell.setPaddingLeft(10f);
				G1cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(G1cell);
	
				PdfPCell BD1cell = new PdfPCell(new Paragraph(dob));
	
				BD1cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(BD1cell);
			}
			
			///////////////////////////
			table.setSpacingBefore(30.0f); // Space Before table starts, like
											// margin-top in CSS
			table.setSpacingAfter(30.0f); // Space After table starts, like
											// margin-Bottom in CSS
//			float[] columnWidths = new float[] { 10f, 10f, 30f, 15f, 15f };
			float[] columnWidths = new float[] { 7f, 10f, 43f, 10f, 13f };
			table.setWidths(columnWidths);
			document.add(table);
			
			document.close();

			file.close();

			logger.info("ClassPdf created successfully for std : " + std
					+ "  div : " + div);

		} catch (Exception e) {
			fileOpenFlag = false;
			logger.info("ClassPDF Exception e == " + e);
			JOptionPane.showMessageDialog(null, "Please close "+fileName+" and try again"); 
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
				logger.info("Done");
			} catch (Exception e) {
				logger.info(":: -----Exception---- ::\n"+e);
			}
		}
	}
}
