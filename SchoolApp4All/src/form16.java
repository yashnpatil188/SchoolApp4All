import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.PageSize;


public class form16 {
	
	 public static void main(String[] args) {

	        try {

	              OutputStream file = new FileOutputStream(new File("D:\\yash\\form16.pdf"));
//		          Document document = new Document(PageSize.A4.rotate());
	              Document document = new Document();
		          PdfWriter.getInstance(document, file);
		          
		          PdfPTable table=new PdfPTable(12);
				     table.setWidthPercentage(100);

		                     PdfPCell cell = new PdfPCell (new Paragraph ("FORM16"));
					      cell.setColspan (12);
					      cell.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell.setPadding (10.0f);
					      cell.setBackgroundColor (new BaseColor (140, 221, 8));	
					      table.addCell(cell);
					      
					      PdfPCell cell1 = new PdfPCell (new Paragraph ("( See rule 31(1) (a) )"));
					      cell1.setColspan (12);
					      cell1.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell1.setPadding (10.0f);
					      cell1.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell1);
					      
					      
					      PdfPCell cell2 = new PdfPCell (new Paragraph ("Certificate under section 203 of the Income-tax Act, 1961 for tax deducted"));
					      cell2.setColspan (12);
					      cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell2.setPadding (10.0f);
					      cell2.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell2);
					      
					      
					      PdfPCell cell3 = new PdfPCell (new Paragraph ("Name and Address of the Employer", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell3.setColspan (6);
					      cell3.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell3.setPadding (10.0f);
//					      cell3.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell3);
					      
					      
					      PdfPCell cell4 = new PdfPCell (new Paragraph ("Name and Designation of the Employee", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell4.setColspan (6);
					      cell4.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell4.setPadding (10.0f);
//					      cell4.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell4);
					      
					      
					      PdfPCell cell5 = new PdfPCell (new Paragraph ("Navbharat Education Society's ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell5.setColspan (6);
					      cell5.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell5.setPadding (10.0f);
//					      cell5.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell5);
					      
					      
					      PdfPCell cell6 = new PdfPCell (new Paragraph ("Rapol Someshwar Satrynarayan", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell6.setColspan (6);
					      cell6.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell6.setPadding (10.0f);
//					      cell6.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell6);
					      
					      
					      PdfPCell cell7 = new PdfPCell (new Paragraph ("English Medium High School & Jr.College", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell7.setColspan (6);
					      cell7.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell7.setPadding (10.0f);
//					      cell7.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell7);
					      
					      
					      PdfPCell cell8 = new PdfPCell (new Paragraph ("Asstt.  Teacher", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell8.setColspan (6);
					      cell8.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell8.setPadding (10.0f);
//					      cell8.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell8);
					      
					      
					      PdfPCell cell9 = new PdfPCell (new Paragraph ("BHIWANDI.  Dist. - Thane", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell9.setColspan (6);
					      cell9.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell9.setPadding (10.0f);
//					      cell9.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell9);
					      
					      PdfPCell cell10 = new PdfPCell (new Paragraph ("SEX", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell10.setColspan (1);
					      cell10.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell10.setPadding (10.0f);
//					      cell10.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell10);
					      
					      PdfPCell cell11 = new PdfPCell (new Paragraph ("MALE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell11.setColspan (5);
					      cell11.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell11.setPadding (10.0f);
//					      cell11.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell11);
					      
					      PdfPCell cell12 = new PdfPCell (new Paragraph ("State : Maharashtra", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));

					      cell12.setColspan (6);
					      cell12.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell10.setPadding (10.0f);
//					      cell12.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell12);
					      
					      PdfPCell cell13 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell13.setColspan (6);
					      cell13.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell11.setPadding (10.0f);
//					      cell13.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell13);
					      
					     
					      
					      PdfPCell cell14 = new PdfPCell (new Paragraph ("PAN / GIR NO.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell14.setColspan (3);
					      cell14.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell11.setPadding (10.0f);
//					      cell14.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell14);
					      
					      
					      PdfPCell cell15 = new PdfPCell (new Paragraph ("TAN", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell15.setColspan (3);
					      cell15.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell11.setPadding (10.0f);
//					      cell15.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell15);
					      
					      
					      
					      PdfPCell cell16 = new PdfPCell (new Paragraph ("PAN / GIR NO.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell16.setColspan (6);
					      cell16.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell11.setPadding (10.0f);
//					      cell16.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell16);
					      
					      PdfPCell cell17 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell17.setColspan (3);
					      cell17.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell11.setPadding (10.0f);
//					      cell17.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell17);
					      
					      PdfPCell cell18 = new PdfPCell (new Paragraph ("PNENO4994D", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell18.setColspan (3);
					      cell18.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell11.setPadding (10.0f);
//					      cell18.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell18);
					      
					      PdfPCell cell19 = new PdfPCell (new Paragraph ("AMHPR1040C", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell19.setColspan (6);
					      cell19.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell11.setPadding (10.0f);
//					      cell19.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell19);
					      
					      PdfPCell cell20 = new PdfPCell (new Paragraph ("TDS Circle where Annual Return/ Statement under", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell20.setColspan (6);
					      cell20.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell11.setPadding (10.0f);
//					      cell20.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell20);
					      
					      
					      PdfPCell cell21 = new PdfPCell (new Paragraph ("PERIOD", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell21.setColspan (4);
					      cell21.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell11.setPadding (10.0f);
//					      cell21.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell21);
					      
					      PdfPCell cell22 = new PdfPCell (new Paragraph ("Assessment", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell22.setColspan (2);
					      cell22.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell22.setPadding (10.0f);
//					      cell22.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell22);
					      
					      PdfPCell cell23 = new PdfPCell (new Paragraph ("Section 206 is to be filed", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell23.setColspan (3);
					      cell23.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell23.setPadding (10.0f);
//					      cell23.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell23);
					      
					      PdfPCell cell24 = new PdfPCell (new Paragraph ("Kalyan Range", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell24.setColspan (3);
					      cell24.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell24.setPadding (10.0f);
//					      cell24.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell24);
					      
					      PdfPCell cell25 = new PdfPCell (new Paragraph ("FROM", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell25.setColspan (2);
					      cell25.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell25.setPadding (10.0f);
//					      cell25.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell25);
					      
					      PdfPCell cell26 = new PdfPCell (new Paragraph ("TO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell26.setColspan (2);
					      cell26.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell26.setPadding (10.0f);
//					      cell26.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell26);
					      
					      PdfPCell cell27 = new PdfPCell (new Paragraph ("Year", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell27.setColspan (2);
					      cell27.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell27.setPadding (10.0f);
//					      cell27.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell27);
					      
					      PdfPCell cell28 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell28.setColspan (3);
					      cell28.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell28.setPadding (10.0f);
//					      cell28.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell28);
					      
					      PdfPCell cell29 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell29.setColspan (3);
					      cell29.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell29.setPadding (10.0f);
//					      cell29.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell29);
					      
					      PdfPCell cell30 = new PdfPCell (new Paragraph ("01/04/2013", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell30.setColspan (2);
					      cell30.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell30.setPadding (10.0f);
//					      cell30.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell30);
					      
					      PdfPCell cell31 = new PdfPCell (new Paragraph ("31/03/2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell31.setColspan (2);
					      cell31.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell31.setPadding (10.0f);
//					      cell31.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell31);
					      
					      PdfPCell cell32 = new PdfPCell (new Paragraph ("2014-15", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell32.setColspan (2);
					      cell32.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell32.setPadding (10.0f);
//					      cell32.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell32);
					   
					      PdfPCell cell33 = new PdfPCell (new Paragraph ("Summary of tax deducted at source", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell33.setColspan (12);
					      cell33.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell33.setPadding (10.0f);
//					      cell33.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell33);
					      
					      PdfPCell cell34 = new PdfPCell (new Paragraph ("Quarter", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell34.setColspan (2);
					      cell34.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell34.setPadding (10.0f);
//					      cell34.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell34);
					      
					      PdfPCell cell35 = new PdfPCell (new Paragraph ("under sub section(3) of section 200", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell35.setColspan (4);
					      cell35.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell35.setPadding (10.0f);
//					      cell35.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell35);
					      
					      PdfPCell cell36 = new PdfPCell (new Paragraph ("In respect of", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell36.setColspan (3);
					      cell36.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell36.setPadding (10.0f);
//					      cell36.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell36);
					      
					      PdfPCell cell37 = new PdfPCell (new Paragraph ("respect of employee", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell37.setColspan (3);
					      cell37.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell37.setPadding (10.0f);
//					      cell37.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell37);
					      
					      PdfPCell cell38 = new PdfPCell (new Paragraph ("Quarter 1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell38.setColspan (2);
					      cell38.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell38.setPadding (10.0f);
//					      cell38.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell38);
					      
					      PdfPCell cell39 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell39.setColspan (4);
					      cell39.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell39.setPadding (10.0f);
//					      cell39.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell39);
					      
					      PdfPCell cell40 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell40.setColspan (3);
					      cell40.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell40.setPadding (10.0f);
//					      cell40.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell40);
					      
					      PdfPCell cell41 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell41.setColspan (3);
					      cell41.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell41.setPadding (10.0f);
//					      cell41.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell41);
					      
					      PdfPCell cell42 = new PdfPCell (new Paragraph ("Quarter 2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell42.setColspan (2);
					      cell42.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell42.setPadding (10.0f);
//					      cell42.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell42);
					      
					      PdfPCell cell43 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell43.setColspan (4);
					      cell43.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell43.setPadding (10.0f);
//					      cell43.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell43);
					      
					      PdfPCell cell44 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell44.setColspan (3);
					      cell44.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell44.setPadding (10.0f);
//					      cell44.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell44);
					      
					      PdfPCell cell45 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell45.setColspan (3);
					      cell45.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell45.setPadding (10.0f);
//					      cell45.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell45);
					      
					      PdfPCell cell46 = new PdfPCell (new Paragraph ("Quarter 3", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell46.setColspan (2);
					      cell46.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell46.setPadding (10.0f);
//					      cell46.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell46);
					      
					      PdfPCell cell47 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell47.setColspan (4);
					      cell47.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell47.setPadding (10.0f);
//					      cell47.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell47);
					      
					      PdfPCell cell48 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell48.setColspan (3);
					      cell48.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell48.setPadding (10.0f);
//					      cell48.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell48);
					      
					      PdfPCell cell49 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell49.setColspan (3);
					      cell49.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell49.setPadding (10.0f);
//					      cell49.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell49);
					      
					      PdfPCell cell50 = new PdfPCell (new Paragraph ("Quarter 4", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell50.setColspan (2);
					      cell50.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell50.setPadding (10.0f);
//					      cell50.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell50);
					      
					      PdfPCell cell51 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell51.setColspan (4);
					      cell51.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell51.setPadding (10.0f);
//					      cell51.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell51);
					      
					      PdfPCell cell52 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell52.setColspan (3);
					      cell52.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell52.setPadding (10.0f);
//					      cell52.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell52);
					      
					      PdfPCell cell53 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell53.setColspan (3);
					      cell53.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell53.setPadding (10.0f);
//					      cell53.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell53);
					      
					      PdfPCell cell54 = new PdfPCell (new Paragraph ("Total", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell54.setColspan (2);
					      cell54.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell54.setPadding (10.0f);
//					      cell54.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell54);
					      
					      PdfPCell cell55 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell55.setColspan (4);
					      cell55.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell55.setPadding (10.0f);
//					      cell55.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell55);
					      
					      PdfPCell cell56 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell56.setColspan (3);
					      cell56.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell56.setPadding (10.0f);
//					      cell56.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell56);
					      
					      PdfPCell cell57 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell57.setColspan (3);
					      cell57.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell57.setPadding (10.0f);
//					      cell57.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell57);
					      
					      PdfPCell cell58 = new PdfPCell (new Paragraph ("PART B  (Refer Note 1)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell58.setColspan (12);
					      cell58.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell58.setPadding (10.0f);
//					      cell58.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell58);
					      
					      PdfPCell cell59 = new PdfPCell (new Paragraph ("DETAILS OF SALARY PAID AND ANY OTHER INCOME AND TAX DEDUCTED", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell59.setColspan (12);
					      cell59.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell59.setPadding (10.0f);
//					      cell59.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell59);
					      
					      PdfPCell cell60 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell60.setColspan (1);
					      cell60.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell60.setPadding (10.0f);
//					      cell60.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell60);
					      
					      PdfPCell cell61 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell61.setColspan (11);
					      cell61.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell61.setPadding (10.0f);
//					      cell61.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell61);
					      
					      PdfPCell cell62 = new PdfPCell (new Paragraph ("1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell62.setColspan (1);
					      cell62.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell62.setPadding (10.0f);
//					      cell62.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell62);
					      
					      PdfPCell cell63 = new PdfPCell (new Paragraph ("Gross  Salary*", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell63.setColspan (2);
					      cell63.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell63.setPadding (10.0f);
//					      cell63.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell63);
					      
					      PdfPCell cell64 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell64.setColspan (6);
					      cell64.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell64.setPadding (10.0f);
//					      cell64.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell64);
					      
					      PdfPCell cell65 = new PdfPCell (new Paragraph ("480576", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell65.setColspan (1);
					      cell65.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell65.setPadding (10.0f);
//					      cell65.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell65);
					      
					      PdfPCell cell66 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell66.setColspan (1);
					      cell66.setRowspan (15);
					      cell66.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell66.setPadding (10.0f);
//					      cell66.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell66);
					      
					      PdfPCell cell67 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell67.setColspan (1);
					      cell67.setRowspan (15);
					      cell67.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell67.setPadding (10.0f);
//					      cell67.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell67);
					      
					      PdfPCell cell68 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell68.setColspan (1);
//					      cell68.setRowspan (15);
					      cell68.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell68.setPadding (10.0f);
//					      cell68.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell68);
					      
					      PdfPCell cell69 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell69.setColspan (8);
//					      cell69.setRowspan (15);
					      cell69.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell69.setPadding (10.0f);
//					      cell69.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell69);
					      
					      PdfPCell cell70 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell70.setColspan (1);
//					      cell70.setRowspan (15);
					      cell70.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell70.setPadding (10.0f);
//					      cell70.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell70);
					      
					      PdfPCell cell71 = new PdfPCell (new Paragraph ("2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell71.setColspan (1);
//					      cell71.setRowspan (15);
					      cell71.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell71.setPadding (10.0f);
//					      cell71.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell71);
					      
					      PdfPCell cell72 = new PdfPCell (new Paragraph ("Less : Allowance to the extent exempt under ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell72.setColspan (7);
//					      cell72.setRowspan (15);
					      cell72.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell72.setPadding (10.0f);
//					      cell72.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell72);
					      
					      PdfPCell cell73 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell73.setColspan (1);
					      cell73.setRowspan (3);
					      cell73.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell73.setPadding (10.0f);
//					      cell73.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell73);
					      
					      PdfPCell cell74 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell74.setColspan (1);
					      cell74.setRowspan (3);
					      cell74.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell74.setPadding (10.0f);
//					      cell74.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell74);
					      
					      PdfPCell cell75 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell75.setColspan (1);
					      cell75.setRowspan (2);
					      cell75.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell75.setPadding (10.0f);
//					      cell75.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell75);
					      
					      PdfPCell cell76 = new PdfPCell (new Paragraph ("Section 10", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell76.setColspan (1);
//					      cell76.setRowspan (2);
					      cell76.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell76.setPadding (10.0f);
//					      cell76.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell76);
					      
					      PdfPCell cell77 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell77.setColspan (4);
//					      cell77.setRowspan (2);
					      cell77.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell77.setPadding (10.0f);
//					      cell77.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell77);
					      
					      PdfPCell cell78 = new PdfPCell (new Paragraph ("Rs.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell78.setColspan (1);
//					      cell78.setRowspan (2);
					      cell78.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell75.setPadding (10.0f);
//					      cell78cell78.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell78);
					      
					      PdfPCell cell79 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell79.setColspan (1);
//					      cell79.setRowspan (2);
					      cell79.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell79.setPadding (10.0f);
//					      cell79.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell79);
					      
					      PdfPCell cell80 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell80.setColspan (7);
//					      cell80.setRowspan (2);
					      cell80.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell80.setPadding (10.0f);
//					      cell80.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell80);
					      
					      PdfPCell cell81 = new PdfPCell (new Paragraph ("3", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell81.setColspan (1);
//					      cell81.setRowspan (2);
					      cell81.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell81.setPadding (10.0f);
//					      cell81.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell81);
					      
					      PdfPCell cell82 = new PdfPCell (new Paragraph ("Balance ( 1-2 )", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell82.setColspan (2);
//					      cell82.setRowspan (2);
					      cell82.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell82.setPadding (10.0f);
//					      cell82.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell82);
					      
					      PdfPCell cell83 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell83.setColspan (5);
//					      cell83.setRowspan (2);
					      cell83.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell83.setPadding (10.0f);
//					      cell83.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell83);
					      
					      PdfPCell cell84 = new PdfPCell (new Paragraph ("Rs.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell84.setColspan (1);
//					      cell84.setRowspan (2);
					      cell84.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell84.setPadding (10.0f);
//					      cell84.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell84);
					      
					      PdfPCell cell85 = new PdfPCell (new Paragraph ("480576", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell85.setColspan (1);
//					      cell85.setRowspan (2);
					      cell85.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell85.setPadding (10.0f);
//					      cell85.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell85);
					      
					      PdfPCell cell86 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell86.setColspan (1);
//					      cell86.setRowspan (2);
					      cell86.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell86.setPadding (10.0f);
//					      cell86.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell86);
					      
					      PdfPCell cell87 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell87.setColspan (7);
//					      cell87.setRowspan (2);
					      cell83.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell87.setPadding (10.0f);
//					      cell87.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell87);
					      
					      PdfPCell cell88 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell88.setColspan (1);
					      cell88.setRowspan (9);
					      cell88.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell88.setPadding (10.0f);
//					      cell88.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell88);
					      
					      PdfPCell cell89 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell89.setColspan (1);
					      cell89.setRowspan (9);
					      cell89.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell89.setPadding (10.0f);
//					      cell89.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell89);
					      
					      PdfPCell cell90 = new PdfPCell (new Paragraph ("4", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell90.setColspan (1);
//					      cell90.setRowspan (9);
					      cell90.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell90.setPadding (10.0f);
//					      cell90.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell90);
					      
					      PdfPCell cell91 = new PdfPCell (new Paragraph ("Deductions :", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell91.setColspan (2);
//					      cell91.setRowspan (9);
					      cell91.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell91.setPadding (10.0f);
//					      cell91.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell91);
					      
					      PdfPCell cell92 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell92.setColspan (5);
//					      cell92.setRowspan (9);
					      cell92.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell92.setPadding (10.0f);
//					      cell92.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell92);
					      
					      PdfPCell cell93 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell93.setColspan (1);
					      cell93.setRowspan (4);
					      cell93.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell93.setPadding (10.0f);
//					      cell93.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell93);
					      
					      PdfPCell cell94 = new PdfPCell (new Paragraph ("(a) Entertainment allowance", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell94.setColspan (3);
//					      cell94.setRowspan (9);
					      cell94.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell94.setPadding (10.0f);
//					      cell94.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell94);
					      
					      PdfPCell cell95 = new PdfPCell (new Paragraph ("Rs.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell95.setColspan (1);
//					      cell95.setRowspan (9);
					      cell95.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell95.setPadding (10.0f);
//					      cell95.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell95);
					      
					      PdfPCell cell96 = new PdfPCell (new Paragraph ("-", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell96.setColspan (1);
//					      cell96.setRowspan (9);
					      cell96.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell96.setPadding (10.0f);
//					      cell96.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell96);
					      
					      PdfPCell cell97 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell97.setColspan (1);
//					      cell97.setRowspan (9);
					      cell97.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell97.setPadding (10.0f);
//					      cell97.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell97);
					      
					      PdfPCell cell98 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell98.setColspan (1);
//					      cell98.setRowspan (9);
					      cell98.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell98.setPadding (10.0f);
//					      cell98.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell98);
					      
					      PdfPCell cell99 = new PdfPCell (new Paragraph ("(b)Tax on Employment", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell99.setColspan (3);
//					      cell99.setRowspan (9);
					      cell99.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell99.setPadding (10.0f);
//					      cell99.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell99);
					      
					      PdfPCell cell100 = new PdfPCell (new Paragraph ("Rs.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell100.setColspan (1);
//					      cell100.setRowspan (9);
					      cell100.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell100.setPadding (10.0f);
//					      cell100.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell100);
					      
					      PdfPCell cell101 = new PdfPCell (new Paragraph ("2500", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell101.setColspan (1);
//					      cell101.setRowspan (9);
					      cell101.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell101.setPadding (10.0f);
//					      cell101.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell101);
					      
					      PdfPCell cell102 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell102.setColspan (1);
//					      cell102.setRowspan (9);
					      cell102.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell102.setPadding (10.0f);
//					      cell102.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell102);
					      
					      PdfPCell cell103 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell103.setColspan (1);
//					      cell103.setRowspan (9);
					      cell103.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell103.setPadding (10.0f);
//					      cell103.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell103);
					      
					      PdfPCell cell104 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell104.setColspan (3);
					      cell104.setRowspan (2);
					      cell104.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell104.setPadding (10.0f);
//					      cell104.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell104);
					      
					      PdfPCell cell105 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell105.setColspan (1);
//					      cell105.setRowspan (9);
					      cell105.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell105.setPadding (10.0f);
//					      cell105.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell105);
					      
					      PdfPCell cell106 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell106.setColspan (1);
//					      cell106.setRowspan (9);
					      cell106.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell106.setPadding (10.0f);
//					      cell106.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell106);
					      
					      PdfPCell cell107 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell107.setColspan (1);
//					      cell107.setRowspan (9);
					      cell107.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell107.setPadding (10.0f);
//					      cell107.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell107);
					      
					      PdfPCell cell108 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell108.setColspan (1);
//					      cell108.setRowspan (9);
					      cell108.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell108.setPadding (10.0f);
//					      cell108.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell108);
					      
					      PdfPCell cell109 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell109.setColspan (1);
//					      cell109.setRowspan (9);
					      cell109.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell109.setPadding (10.0f);
//					      cell109.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell109);
					      
					      PdfPCell cell110 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell110.setColspan (1);
//					      cell110.setRowspan (9);
					      cell110.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell110.setPadding (10.0f);
//					      cell110.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell110);
					      
					      PdfPCell cell111 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell111.setColspan (1);
//					      cell111.setRowspan (9);
					      cell111.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell111.setPadding (10.0f);
//					      cell111.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell111);
					      
					      PdfPCell cell112 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell112.setColspan (1);
//					      cell112.setRowspan (9);
					      cell112.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell112.setPadding (10.0f);
//					      cell112.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell112);
					      
					      PdfPCell cell113 = new PdfPCell (new Paragraph ("5", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell113.setColspan (1);
//					      cell113.setRowspan (4);
					      cell113.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell113.setPadding (10.0f);
//					      cell113.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell113);
					      
					      PdfPCell cell114 = new PdfPCell (new Paragraph ("Aggregate of 4 (a to c)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell114.setColspan (3);
//					      cell114.setRowspan (9);
					      cell114.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell114.setPadding (10.0f);
//					      cell114.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell114);
					      
					      PdfPCell cell115 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell115.setColspan (1);
//					      cell115.setRowspan (9);
					      cell115.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell115.setPadding (10.0f);
//					      cell115.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell115);
					      
					      PdfPCell cell116 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell116.setColspan (1);
//					      cell116.setRowspan (9);
					      cell116.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell116.setPadding (10.0f);
//					      cell116.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell116);
					      
					      PdfPCell cell117 = new PdfPCell (new Paragraph ("Rs.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell117.setColspan (1);
//					      cell117.setRowspan (9);
					      cell117.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell117.setPadding (10.0f);
//					      cell117.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell117);
					      
					      PdfPCell cell118 = new PdfPCell (new Paragraph ("2500", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell118.setColspan (1);
//					      cell118.setRowspan (9);
					      cell118.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell118.setPadding (10.0f);
//					      cell118.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell118);
					      
					      PdfPCell cell119 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell119.setColspan (1);
//					      cell119.setRowspan (9);
					      cell119.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell119.setPadding (10.0f);
//					      cell119.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell119);
					      
					      PdfPCell cell120 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell120.setColspan (3);
//					      cell120.setRowspan (9);
					      cell120.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell120.setPadding (10.0f);
//					      cell120.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell120);
					      
					      PdfPCell cell121 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell121.setColspan (2);
//					      cell121.setRowspan (9);
					      cell121.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell121.setPadding (10.0f);
//					      cell121.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell121);
					      
					      PdfPCell cell122 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell122.setColspan (1);
//					      cell122.setRowspan (9);
					      cell122.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell122.setPadding (10.0f);
//					      cell122.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell122);
					      
					      PdfPCell cell123 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell123.setColspan (1);
//					      cell123.setRowspan (9);
					      cell123.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell123.setPadding (10.0f);
//					      cell123.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell123);
					      
					      PdfPCell cell124 = new PdfPCell (new Paragraph ("6", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell124.setColspan (1);
//					      cell124.setRowspan (9);
					      cell124.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell124.setPadding (10.0f);
//					      cell124.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell124);
					      
					      PdfPCell cell125 = new PdfPCell (new Paragraph ("Income Chargeable under", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
					      cell125.setColspan (7);
//					      cell125.setRowspan (9);
					      cell125.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell125.setPadding (10.0f);
//					      cell125.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell125);
					      
//					      PdfPCell cell124 = new PdfPCell (new Paragraph ("6", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell124.setColspan (1);
////					      cell124.setRowspan (9);
//					      cell124.setHorizontalAlignment (Element.ALIGN_LEFT);
////					      cell124.setPadding (10.0f);
////					      cell124.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell124);
					    
					    
					      
					      


					      						               
					      
					      table.addCell(new Phrase("Name of Oppointment", FontFactory.getFont(FontFactory.HELVETICA, 8)));
					      table.addCell("Whether substantive or officiating and whether permanent or temporary");
					      table.addCell("Nature of Original Vacancy");
	                      table.addCell("Pay in substantive appointment");
					      table.addCell("Additional Pay for officiating");
					      table.addCell("Other Emolumnets falling under the term pay");
					      table.addCell("Date of Oppointment");
					      table.addCell("Singature of Employee");
					      table.addCell("Signature and designation of the Principal or Chief Executive Officer or Other attesting Officer in attestation of column 1 to 8");
					      table.addCell("Date of Termination of Appointment");
					      table.addCell("Reason of termination (such as promotion, transfer, dismissal etc.");
					      table.addCell("Signature of the Head or Chief Executive Officer or Other attesting Officer ");
//					      table.addCell("Nature & duration of leave taken");
//					      table.addCell("Signature of the Head or Chief Executive Officer or Other attesting Officer ");
//					      table.addCell("Referemce to any recorded punishment or censure or reward or praise of the employee");
					      
					      
					      
//					      table.setSpacingBefore(10.0f);       // Space Before table starts, like margin-top in CSS
//					      table.setSpacingAfter(10.0f);        // Space After table starts, like margin-Bottom in CSS								          

				 //Inserting List in PDF
					      List list=new List(true,30);
				          list.add(new ListItem("Java4s"));
					      list.add(new ListItem("Php4s"));
					      list.add(new ListItem("Some Thing..."));		

				 //Text formating in PDF
		                Chunk chunk=new Chunk("Welecome To Java4s Programming Blog...");
						chunk.setUnderline(+1f,-2f);//1st co-ordinate is for line width,2nd is space between
						Chunk chunk1=new Chunk("Php4s.com");
						chunk1.setUnderline(+4f,-8f);
						chunk1.setBackground(new BaseColor (17, 46, 193));      

				 //Now Insert Every Thing Into PDF Document
			         document.open();//PDF document opened........			       

//						document.add(image);

						document.add(Chunk.NEWLINE);   //Something like in HTML :-)

	                   document.add(new Paragraph("Dear Java4s.com"));
		                document.add(new Paragraph("Document Generated On - "+new Date().toString()));	
		              
		                
		                //for column width size 
		                float[] columnWidths = new float[] {4f, 10f, 10f, 10f, 4f, 10f, 4f, 10f, 4f, 10f, 4f, 10f};
		                table.setWidths(columnWidths);
		                
						document.add(table);

						document.add(chunk);
						document.add(chunk1);

						document.add(Chunk.NEWLINE);   //Something like in HTML :-)							    

	       				document.newPage();            //Opened new page
						document.add(list);            //In the new page we are going to add list

			         document.close();

				             file.close();

	            System.out.println("Pdf created successfully..");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }


}
