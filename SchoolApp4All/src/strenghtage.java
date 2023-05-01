import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

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
import com.itextpdf.text.PageSize;
//import com.lowagie.text.Font;


public class strenghtage{
	
	 public static void main(String[] args) {

	        try {

	              OutputStream file = new FileOutputStream(new File("D:\\yash\\strengthage.pdf"));
//		          Document document = new Document(PageSize.A4.rotate());
	              Document document = new Document();
		          PdfWriter.getInstance(document, file);
		          
		          PdfPTable table=new PdfPTable(9);
				     table.setWidthPercentage(100);

		                     PdfPCell cell = new PdfPCell (new Paragraph ("F. Enrolment in current academic session (by age) - All children", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell.setColspan (9);
					      cell.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell.setPadding (10.0f);
					      cell.setBackgroundColor (new BaseColor (150, 150, 150));	
					      table.addCell(cell);
					      
					      PdfPCell cell1 = new PdfPCell (new Paragraph ("Note: Total students (class wise) should match with class wise in block E of DCF", FontFactory.getFont(FontFactory.TIMES_ROMAN, 6)));
					      cell1.setColspan (9);
					      cell1.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell1.setPadding (10.0f);
					      cell1.setBackgroundColor (new BaseColor (150, 150, 150));					               
					      table.addCell(cell1);
					      
					      
					      PdfPCell cell2 = new PdfPCell (new Paragraph ("Class"));
					      cell2.setColspan (1);
					      cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell2.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell2.setPadding (10.0f);
//					      cell2.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell2);
					      
					      
					      PdfPCell cell3 = new PdfPCell (new Paragraph ("I", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell3.setColspan (2);
					      cell3.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell3.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell3.setPadding (10.0f);
//					      cell3.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell3);
					      
					      
					      PdfPCell cell4 = new PdfPCell (new Paragraph ("II", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell4.setColspan (2);
					      cell4.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell4.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell4.setPadding (10.0f);
//					      cell4.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell4);
					      
					      
					      PdfPCell cell5 = new PdfPCell (new Paragraph ("III", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell5.setColspan (2);
					      cell5.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell5.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell5.setPadding (10.0f);
//					      cell5.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell5);
					      
					      
					      PdfPCell cell6 = new PdfPCell (new Paragraph ("IV", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell6.setColspan (2);
					      cell6.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell6.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell6.setPadding (10.0f);
//					      cell6.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell6);
					      
					      
					      PdfPCell cell7 = new PdfPCell (new Paragraph ("Age", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell7.setColspan (1);
					      cell7.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell7.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell7.setPadding (10.0f);
//					      cell7.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell7);
					      
					      
					      PdfPCell cell8 = new PdfPCell (new Paragraph ("Boys", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell8.setColspan (1);
//					      cell8.setRowspan (7);
					      cell8.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell8.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell8.setPadding (10.0f);
//					      cell8.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell8);
					      
					      
					      PdfPCell cell9 = new PdfPCell (new Paragraph ("Girls", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell9.setColspan (1);
//					      cell9.setRowspan (7);
					      cell9.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell9.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell9.setPadding (10.0f);
//					      cell9.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell9);
					      
					      PdfPCell cell10 = new PdfPCell (new Paragraph ("Boys", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell10.setColspan (1);
//					      cell10.setRowspan (3);
					      cell10.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell10.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell10.setPadding (10.0f);
//					      cell10.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell10);
					      
					      PdfPCell cell11 = new PdfPCell (new Paragraph ("Girls", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell11.setColspan (1);
//					      cell11.setRowspan (3);
					      cell11.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell11.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell11.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell11);
					      
					      PdfPCell cell12 = new PdfPCell (new Paragraph ("Boys", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));

					      cell12.setColspan (1);
//					      cell12.setRowspan (3);
					      cell12.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell12.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell10.setPadding (10.0f);
//					      cell12.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell12);
					      
					      PdfPCell cell13 = new PdfPCell (new Paragraph ("Girls", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell13.setColspan (1);
//					      cell13.setRowspan (3);
					      cell13.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell13.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell13.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell13);
					      
					     
					      
					      PdfPCell cell14 = new PdfPCell (new Paragraph ("Boys", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell14.setColspan (1);
					      cell14.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell14.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell14.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell14);
					      
					      
					      PdfPCell cell15 = new PdfPCell (new Paragraph ("Girls", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell15.setColspan (1);
					      cell15.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell15.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell15.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell15);
					      
					      
					      
					      PdfPCell cell16 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 3)));
					      cell16.setColspan (9);
					      cell16.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell16.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell16.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell16);
					      
					      PdfPCell cell17 = new PdfPCell (new Paragraph ("<5", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell17.setColspan (1);
					      cell17.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell17.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell17.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell17);
					      
					      PdfPCell cell18 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell18.setColspan (1);
					      cell18.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell18.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell18.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell18);
					      
					      PdfPCell cell19 = new PdfPCell (new Paragraph ("20", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell19.setColspan (1);
					      cell19.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell19.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell19.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell19);
					      
					      PdfPCell cell20 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell20.setColspan (1);
					      cell20.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell20.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
					      cell20.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell20);
					      
					      
					      PdfPCell cell21 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell21.setColspan (1);
//					      cell21.setRowspan (5);
					      cell21.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell21.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
					      cell21.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell21);
					      
					      PdfPCell cell22 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell22.setColspan (1);
//					      cell22.setRowspan (5);
					      cell22.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell22.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell22.setPadding (10.0f);
					      cell22.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell22);
					      
					      PdfPCell cell23 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell23.setColspan (1);
//					      cell23.setRowspan (5);
					      cell23.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell23.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell23.setPadding (10.0f);
					      cell23.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell23);
					      
					      PdfPCell cell24 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell24.setColspan (1);
//					      cell24.setRowspan (5);
					      cell24.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell24.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell24.setPadding (10.0f);
					      cell24.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell24);
					      
					      PdfPCell cell25 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell25.setColspan (1);
					      cell25.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell25.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell25.setPadding (10.0f);
					      cell25.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell25);
					      
					      PdfPCell cell26 = new PdfPCell (new Paragraph ("5", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell26.setColspan (1);
					      cell26.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell26.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell26.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell26);
					      
					      PdfPCell cell27 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell27.setColspan (1);
					      cell27.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell27.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell27.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell27);
					      
					      PdfPCell cell28 = new PdfPCell (new Paragraph ("20", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell28.setColspan (1);
					      cell28.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell28.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell28.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell28);
					      
					      PdfPCell cell29 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell29.setColspan (1);
					      cell29.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell29.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell29.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell29);
					      
					      
					      PdfPCell cell30 = new PdfPCell (new Paragraph ("25", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell30.setColspan (1);
//					      cell30.setRowspan (5);
					      cell30.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell30.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell30.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell30);
					      
					      PdfPCell cell31 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell31.setColspan (1);
//					      cell31.setRowspan (5);
					      cell31.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell31.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell31.setPadding (10.0f);
					      cell31.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell31);
					      
					      PdfPCell cell32 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell32.setColspan (1);
//					      cell32.setRowspan (5);
					      cell32.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell32.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell32.setPadding (10.0f);
					      cell32.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell32);
					      
					      PdfPCell cell33 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell33.setColspan (1);
//					      cell33.setRowspan (5);
					      cell33.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell33.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell33.setPadding (10.0f);
					      cell33.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell33);
					      
					      PdfPCell cell34 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell34.setColspan (1);
					      cell34.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell34.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell34.setPadding (10.0f);
					      cell34.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell34);
			      
					      
					      PdfPCell cell35 = new PdfPCell (new Paragraph ("6", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell35.setColspan (1);
					      cell35.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell35.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell35.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell35);
					      
					      PdfPCell cell36 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell36.setColspan (1);
					      cell36.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell36.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell36.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell36);
					      
					      PdfPCell cell37 = new PdfPCell (new Paragraph ("20", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell37.setColspan (1);
					      cell37.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell37.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell37.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell37);
					      
					      PdfPCell cell38 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell38.setColspan (1);
					      cell38.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell38.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell38.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell38);
					      
					      
					      PdfPCell cell39 = new PdfPCell (new Paragraph ("25", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell39.setColspan (1);
//					      cell39.setRowspan (5);
					      cell39.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell39.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell39.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell39);
					      
					      PdfPCell cell40 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell40.setColspan (1);
//					      cell40.setRowspan (5);
					      cell40.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell40.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell40.setPadding (10.0f);
					      cell40.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell40);
					      
					      PdfPCell cell41 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell41.setColspan (1);
//					      cell41.setRowspan (5);
					      cell41.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell41.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell41.setPadding (10.0f);
					      cell41.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell41);
					      
					      PdfPCell cell42 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell42.setColspan (1);
//					      cell42.setRowspan (5);
					      cell42.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell42.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell42.setPadding (10.0f);
					      cell42.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell42);
					      
					      PdfPCell cell43 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell43.setColspan (1);
					      cell43.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell43.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell43.setPadding (10.0f);
					      cell43.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell43);
					      
					      PdfPCell cell44 = new PdfPCell (new Paragraph ("7", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell44.setColspan (1);
					      cell44.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell44.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell44.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell44);
					      
					      PdfPCell cell45 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell45.setColspan (1);
					      cell45.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell45.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell45.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell45);
					      
					      PdfPCell cell46 = new PdfPCell (new Paragraph ("20", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell46.setColspan (1);
					      cell46.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell46.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell46.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell46);
					      
					      PdfPCell cell47 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell47.setColspan (1);
					      cell47.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell47.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell47.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell47);
					      
					      
					      PdfPCell cell48 = new PdfPCell (new Paragraph ("25", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell48.setColspan (1);
//					      cell48.setRowspan (5);
					      cell48.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell48.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell48.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell48);
					      
					      PdfPCell cell49 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell49.setColspan (1);
//					      cell49.setRowspan (5);
					      cell49.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell49.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell49.setPadding (10.0f);
//					      cell49.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell49);
					      
					      PdfPCell cell50 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell50.setColspan (1);
//					      cell50.setRowspan (5);
					      cell50.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell50.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell50.setPadding (10.0f);
//					      cell50.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell50);
					      
					      PdfPCell cell51 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell51.setColspan (1);
//					      cell51.setRowspan (5);
					      cell51.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell51.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell51.setPadding (10.0f);
					      cell51.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell51);
					      
					      PdfPCell cell52 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell52.setColspan (1);
					      cell52.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell52.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell52.setPadding (10.0f);
					      cell52.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell52);
					      
					      PdfPCell cell53 = new PdfPCell (new Paragraph ("8", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell53.setColspan (1);
					      cell53.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell53.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell53.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell53);
					      
					      PdfPCell cell54 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell54.setColspan (1);
					      cell54.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell54.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell54.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell54);
					      
					      PdfPCell cell55 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell55.setColspan (1);
					      cell55.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell55.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell55.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell55);
					      
					      PdfPCell cell56 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell56.setColspan (1);
					      cell56.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell56.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell56.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell56);
					      
					      
					      PdfPCell cell57 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell57.setColspan (1);
//					      cell57.setRowspan (5);
					      cell57.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell57.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell47.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell57);
					      
					      PdfPCell cell58 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell58.setColspan (1);
//					      cell58.setRowspan (5);
					      cell58.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell58.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell58.setPadding (10.0f);
//					      cell58.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell58);
					      
					      PdfPCell cell59 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell59.setColspan (1);
//					      cell59.setRowspan (5);
					      cell59.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell59.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell59.setPadding (10.0f);
//					      cell59.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell59);
					      
					      PdfPCell cell60 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell60.setColspan (1);
//					      cell60.setRowspan (5);
					      cell60.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell60.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell60.setPadding (10.0f);
//					      cell60.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell60);
					      
					      PdfPCell cell61 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell61.setColspan (1);
					      cell61.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell61.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell61.setPadding (10.0f);
//					      cell61.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell61);
					      
					      PdfPCell cell62 = new PdfPCell (new Paragraph ("9", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell62.setColspan (1);
					      cell62.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell62.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell62.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell62);
					      
					      PdfPCell cell63 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell63.setColspan (1);
					      cell63.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell63.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell63.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell63);
					      
					      PdfPCell cell64 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell64.setColspan (1);
					      cell64.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell64.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell64.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell64);
					      
					      PdfPCell cell65 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell65.setColspan (1);
					      cell65.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell65.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell65.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell65);
					      
					      
					      PdfPCell cell66 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell66.setColspan (1);
//					      cell66.setRowspan (5);
					      cell66.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell66.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell47.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell66);
					      
					      PdfPCell cell67 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell67.setColspan (1);
//					      cell67.setRowspan (5);
					      cell67.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell67.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell67.setPadding (10.0f);
//					      cell67.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell67);
					      
					      PdfPCell cell68 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell68.setColspan (1);
//					      cell68.setRowspan (5);
					      cell68.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell68.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell68.setPadding (10.0f);
//					      cell68.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell68);
					      
					      PdfPCell cell69 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell69.setColspan (1);
//					      cell69.setRowspan (5);
					      cell69.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell69.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell69.setPadding (10.0f);
//					      cell69.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell69);
					      
					      PdfPCell cell70 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell70.setColspan (1);
					      cell70.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell70.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell70.setPadding (10.0f);
//					      cell70.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell70);
					      
					      PdfPCell cell71 = new PdfPCell (new Paragraph ("10", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell71.setColspan (1);
					      cell71.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell71.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell71.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell71);
					      
					      PdfPCell cell72 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell72.setColspan (1);
					      cell72.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell72.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell72.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell72);
					      
					      PdfPCell cell73 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell73.setColspan (1);
					      cell73.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell73.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell73.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell73);
					      
					      PdfPCell cell74 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell74.setColspan (1);
					      cell74.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell74.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell74.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell74);
					      
					      
					      PdfPCell cell75 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell75.setColspan (1);
//					      cell75.setRowspan (5);
					      cell75.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell75.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell47.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell75);
					      
					      PdfPCell cell76 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell76.setColspan (1);
//					      cell76.setRowspan (5);
					      cell76.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell76.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell76.setPadding (10.0f);
//					      cell76.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell76);
					      
					      PdfPCell cell77 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell77.setColspan (1);
//					      cell77.setRowspan (5);
					      cell77.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell77.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell77.setPadding (10.0f);
//					      cell77.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell77);
					      
					      PdfPCell cell78 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell78.setColspan (1);
//					      cell78.setRowspan (5);
					      cell78.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell78.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell78.setPadding (10.0f);
//					      cell78.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell78);
					      
					      PdfPCell cell79 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell79.setColspan (1);
					      cell79.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell79.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell79.setPadding (10.0f);
//					      cell79.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell79);
					      
					      PdfPCell cell80 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell80.setColspan (1);
					      cell80.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell80.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell80.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell80);
					      
					      PdfPCell cell81 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell81.setColspan (1);
					      cell81.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell81.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell81.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell81);
					      
					      PdfPCell cell82 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell82.setColspan (1);
					      cell82.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell82.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell82.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell82);
					      
					      PdfPCell cell83 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell83.setColspan (1);
					      cell83.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell83.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell83.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell83);
					      
					      
					      PdfPCell cell84 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell84.setColspan (1);
//					      cell84.setRowspan (5);
					      cell84.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell84.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell47.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell84);
					      
					      PdfPCell cell85 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell85.setColspan (1);
//					      cell85.setRowspan (5);
					      cell85.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell85.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell85.setPadding (10.0f);
//					      cell85.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell85);
					      
					      PdfPCell cell86 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell86.setColspan (1);
//					      cell86.setRowspan (5);
					      cell86.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell86.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell86.setPadding (10.0f);
//					      cell86.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell86);
					      
					      PdfPCell cell87 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell87.setColspan (1);
//					      cell87.setRowspan (5);
					      cell87.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell87.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell87.setPadding (10.0f);
//					      cell87.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell87);
					      
					      PdfPCell cell88 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell88.setColspan (1);
					      cell88.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell88.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell88.setPadding (10.0f);
//					      cell88.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell88);
					      
					      PdfPCell cell89 = new PdfPCell (new Paragraph ("12", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell89.setColspan (1);
					      cell89.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell89.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell89.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell89);
					      
					      PdfPCell cell90 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell90.setColspan (1);
					      cell90.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell90.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell90.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell90);
					      
					      PdfPCell cell91 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell91.setColspan (1);
					      cell91.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell91.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell91.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell91);
					      
					      PdfPCell cell92 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell92.setColspan (1);
					      cell92.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell92.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell92.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell92);
					      
					      
					      PdfPCell cell93 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell93.setColspan (1);
//					      cell93.setRowspan (5);
					      cell93.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell93.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell47.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell93);
					      
					      PdfPCell cell94 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell94.setColspan (1);
//					      cell94.setRowspan (5);
					      cell94.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell94.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell94.setPadding (10.0f);
//					      cell94.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell94);
					      
					      PdfPCell cell95 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell95.setColspan (1);
//					      cell95.setRowspan (5);
					      cell95.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell95.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell95.setPadding (10.0f);
//					      cell95.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell95);
					      
					      PdfPCell cell96 = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell96.setColspan (1);
//					      cell96.setRowspan (5);
					      cell96.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell96.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell96.setPadding (10.0f);
//					      cell96.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell96);
					      
					      PdfPCell cell97 = new PdfPCell (new Paragraph ("22", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell97.setColspan (1);
					      cell97.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell97.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell97.setPadding (10.0f);
//					      cell97.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell97);
					   
				
					      						          

//				 //Inserting List in PDF
//					      List list=new List(true,30);
//				          list.add(new ListItem("Java4s"));
//					      list.add(new ListItem("Php4s"));
//					      list.add(new ListItem("Some Thing..."));		
//
//				 //Text formating in PDF
//		                Chunk chunk=new Chunk("Welecome To Java4s Programming Blog...");
//						chunk.setUnderline(+1f,-2f);//1st co-ordinate is for line width,2nd is space between
//						Chunk chunk1=new Chunk("Php4s.com");
//						chunk1.setUnderline(+4f,-8f);
//						chunk1.setBackground(new BaseColor (17, 46, 193));      

				 //Now Insert Every Thing Into PDF Document
			         document.open();//PDF document opened........			       

//						document.add(image);
//
//						document.add(Chunk.NEWLINE);   //Something like in HTML :-)
//
//	                   document.add(new Paragraph("Dear Java4s.com"));
//		                document.add(new Paragraph("Document Generated On - "+new Date().toString()));	
//		              
		                
		                //for column width size 
		                float[] columnWidths = new float[] {10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f};
		                table.setWidths(columnWidths);
//		                table1.setWidths(columnWidths);
		                
		                document.add(table);
//		                document.add(table1);
//		                document.add(paragraph);

//						document.add(chunk);
//						document.add(chunk1);

						document.add(Chunk.NEWLINE);   //Something like in HTML :-)							    

	       				document.newPage();            //Opened new page
//						document.add(list);            //In the new page we are going to add list

			         document.close();

				             file.close();

	            System.out.println("Pdf created successfully..");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }


}
