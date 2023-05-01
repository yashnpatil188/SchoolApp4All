package org.com.maauli;

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


public class form16new {
	
	 public static void main(String[] args) {

	        try {

	              OutputStream file = new FileOutputStream(new File("D:\\yash\\form16new.pdf"));
//		          Document document = new Document(PageSize.A4.rotate());
	              Document document = new Document(PageSize.A4);
//	              Document document = new Document();
		          PdfWriter.getInstance(document, file);
		          
		          PdfPTable table=new PdfPTable(12);
				     table.setWidthPercentage(100);

		                     PdfPCell cell = new PdfPCell (new Paragraph ("FORM16", FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
					      cell.setColspan (12);
					      cell.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell.setPadding (10.0f);
					      cell.setBackgroundColor (new BaseColor (110, 110, 110));	
					      table.addCell(cell);
					      
					      PdfPCell cell1 = new PdfPCell (new Paragraph ("( See rule 31(1) (a) )", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell1.setColspan (12);
					      cell1.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell1.setPadding (10.0f);
//					      cell1.setBackgroundColor (new BaseColor (99, 99, 99));					               
					      table.addCell(cell1);
					      
					      
					      PdfPCell cell2 = new PdfPCell (new Paragraph ("PART A", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell2.setColspan (12);
					      cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell2.setPadding (10.0f);
//					      cell2.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell2);
					      
					      
					      PdfPCell cell3 = new PdfPCell (new Paragraph ("Certificate under section 203 of the Income-tax Act, 1961 for tax deducted at source on salary", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell3.setColspan (12);
					      cell3.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell3.setPadding (10.0f);
//					      cell3.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell3);
					      
					      
					      PdfPCell cell4 = new PdfPCell (new Paragraph ("Certificate No. ROVZXHF", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell4.setColspan (6);
					      cell4.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell4.setPadding (10.0f);
//					      cell4.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell4);
					      
					      
					      PdfPCell cell5 = new PdfPCell (new Paragraph ("Last updated on 15-May-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell5.setColspan (6);
					      cell5.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell5.setPadding (10.0f);
//					      cell5.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell5);
					      
					      
					      PdfPCell cell6 = new PdfPCell (new Paragraph ("Name and address of the Employer", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell6.setColspan (6);
					      cell6.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell6.setPadding (10.0f);
//					      cell6.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell6);
					      
					      
					      PdfPCell cell7 = new PdfPCell (new Paragraph ("Name and address of the Employee", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell7.setColspan (6);
					      cell7.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell7.setPadding (10.0f);
//					      cell7.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell7);
					      
					      
					      PdfPCell cell8 = new PdfPCell (new Paragraph ("RASHMI PARIKH \n" +
					      		"MEECON ENGINEER \n" +
					      		"MADHU INDUSTRIAL ESTATE \n" +
					      		"WORLI \n" +
					      		"MUMBAI 400013\n" +
					      		"Maharashtra \n" +
					      		"022-22456547 \n" +
					      		"meecon@gmail.com", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell8.setColspan (6);
					      cell8.setRowspan (7);
					      cell8.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell8.setPadding (10.0f);
//					      cell8.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell8);
					      
					      
					      PdfPCell cell9 = new PdfPCell (new Paragraph ("SURENDRA PATIL \n " +
					      		"Aai Maauli \n" +
					      		"At Post. Dunge \n" +
					      		"Tal.BHIWANDI. \n" +
					      		"Dist.Thane 421302 \n" +
					      		"Maharashtra", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell9.setColspan (6);
					      cell9.setRowspan (7);
					      cell9.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell9.setPadding (10.0f);
//					      cell9.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell9);
					      
					      PdfPCell cell10 = new PdfPCell (new Paragraph ("PAN of the Deductor", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell10.setColspan (3);
					      cell10.setRowspan (3);
					      cell10.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell10.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell10.setPadding (10.0f);
//					      cell10.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell10);
					      
					      PdfPCell cell11 = new PdfPCell (new Paragraph ("TAN of the Deductor", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell11.setColspan (3);
					      cell11.setRowspan (3);
					      cell11.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell11.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell11.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell11);
					      
					      PdfPCell cell12 = new PdfPCell (new Paragraph ("PAN of the Employee", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));

					      cell12.setColspan (3);
					      cell12.setRowspan (3);
					      cell12.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell12.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell10.setPadding (10.0f);
//					      cell12.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell12);
					      
					      PdfPCell cell13 = new PdfPCell (new Paragraph ("Employee Reference No.\n" +
					      		"provided by the Employer \n" +
					      		"(if available)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell13.setColspan (3);
					      cell13.setRowspan (3);
					      cell13.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell13.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell13.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell13);
					      
					     
					      
					      PdfPCell cell14 = new PdfPCell (new Paragraph ("AACPP8466D", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell14.setColspan (3);
					      cell14.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell14.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell14.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell14);
					      
					      
					      PdfPCell cell15 = new PdfPCell (new Paragraph ("MUMR12372D", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell15.setColspan (3);
					      cell15.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell15.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell15.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell15);
					      
					      
					      
					      PdfPCell cell16 = new PdfPCell (new Paragraph ("CAFPP6093M", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell16.setColspan (3);
					      cell16.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell16.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell16.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell16);
					      
					      PdfPCell cell17 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell17.setColspan (3);
					      cell17.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell17.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell17.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell17);
					      
					      PdfPCell cell18 = new PdfPCell (new Paragraph ("CIT (TDS)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell18.setColspan (5);
					      cell18.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell18.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell18.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell18);
					      
					      PdfPCell cell19 = new PdfPCell (new Paragraph ("Assessment Year", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell19.setColspan (3);
					      cell19.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell19.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell19.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell19);
					      
					      PdfPCell cell20 = new PdfPCell (new Paragraph ("Period with the Employer", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell20.setColspan (4);
					      cell20.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell20.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell20.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell20);
					      
					      
					      PdfPCell cell21 = new PdfPCell (new Paragraph ("The Commissioner of Income Tax (TDS) \n" +
					      		"Room No. 900A, 9th Floor, K.G.Mittal Ayurvedic Hospital \n" +
					      		"Building, Charni Road, Mumbai-400002", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9)));
					      cell21.setColspan (5);
					      cell21.setRowspan (5);
					      cell21.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell21.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell21.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell21);
					      
					      PdfPCell cell22 = new PdfPCell (new Paragraph ("2014-15", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell22.setColspan (3);
					      cell22.setRowspan (5);
					      cell22.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell22.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell22.setPadding (10.0f);
//					      cell22.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell22);
					      
					      PdfPCell cell23 = new PdfPCell (new Paragraph ("From \n" +
					      		"\n" +
					      		"01-Apr-2013", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell23.setColspan (2);
					      cell23.setRowspan (5);
					      cell23.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell23.setPadding (10.0f);
//					      cell23.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell23);
					      
					      PdfPCell cell24 = new PdfPCell (new Paragraph ("To \n" +
					      		"\n" +
					      		"31-Mar-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell24.setColspan (2);
					      cell24.setRowspan (5);
					      cell24.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell24.setPadding (10.0f);
//					      cell24.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell24);
					      
					      PdfPCell cell25 = new PdfPCell (new Paragraph ("Summary of amount paid/credited and tax deducted at source thereon in respect of the employee", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell25.setColspan (12);
					      cell25.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell25.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell25.setPadding (10.0f);
//					      cell25.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell25);
					      
//					      PdfPCell cell26 = new PdfPCell (new Paragraph ("TO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell26.setColspan (2);
//					      cell26.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell26.setPadding (10.0f);
////					      cell26.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell26);
//					      
//					      PdfPCell cell27 = new PdfPCell (new Paragraph ("Year", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell27.setColspan (2);
//					      cell27.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell27.setPadding (10.0f);
////					      cell27.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell27);
//					      
//					      PdfPCell cell28 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell28.setColspan (3);
//					      cell28.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell28.setPadding (10.0f);
////					      cell28.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell28);
//					      
//					      PdfPCell cell29 = new PdfPCell (new Paragraph ("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell29.setColspan (3);
//					      cell29.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell29.setPadding (10.0f);
////					      cell29.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell29);
//					      
//					      PdfPCell cell30 = new PdfPCell (new Paragraph ("01/04/2013", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell30.setColspan (2);
//					      cell30.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell30.setPadding (10.0f);
////					      cell30.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell30);
//					      
//					      PdfPCell cell31 = new PdfPCell (new Paragraph ("31/03/2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell31.setColspan (2);
//					      cell31.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell31.setPadding (10.0f);
////					      cell31.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell31);
//					      
//					      PdfPCell cell32 = new PdfPCell (new Paragraph ("2014-15", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell32.setColspan (2);
//					      cell32.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell32.setPadding (10.0f);
////					      cell32.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell32);
//					   
//					      PdfPCell cell33 = new PdfPCell (new Paragraph ("Summary of tax deducted at source", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell33.setColspan (12);
//					      cell33.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell33.setPadding (10.0f);
////					      cell33.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell33);
					      
					      PdfPCell cell34 = new PdfPCell (new Paragraph ("Quarter(s)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell34.setColspan (2);
					      cell34.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell34.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell34.setPadding (10.0f);
//					      cell34.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell34);
					      
					      PdfPCell cell35 = new PdfPCell (new Paragraph ("Receipt Numbers of original \n" +
					      		"quarterly statements of TDS \n " +
					      		"under sub section(3) \n" +
					      		"of section 200", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell35.setColspan (3);
					      cell35.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell35.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell35.setPadding (10.0f);
//					      cell35.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell35);
					      
					      PdfPCell cell36 = new PdfPCell (new Paragraph ("Amount paid/credited", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell36.setColspan (2);
					      cell36.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell36.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell36.setPadding (10.0f);
//					      cell36.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell36);
					      
					      PdfPCell cell37 = new PdfPCell (new Paragraph ("Amount of tax deducted \n" +
					      		"(Rs.)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell37.setColspan (2);
					      cell37.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell37.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell37.setPadding (10.0f);
//					      cell37.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell37);
					      
					      PdfPCell cell38 = new PdfPCell (new Paragraph ("Amount of tax deposited / remitted \n" +
					      		"(Rs.)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell38.setColspan (3);
					      cell38.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell38.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell38.setPadding (10.0f);
//					      cell38.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell38);
					      
					      PdfPCell cell39 = new PdfPCell (new Paragraph ("Q1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell39.setColspan (2);
					      cell39.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell39.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell39.setPadding (10.0f);
//					      cell39.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell39);
					      
					      PdfPCell cell40 = new PdfPCell (new Paragraph ("ABCDEFGH", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell40.setColspan (3);
					      cell40.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell40.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell40.setPadding (10.0f);
//					      cell40.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell40);
					      
					      PdfPCell cell41 = new PdfPCell (new Paragraph ("00000.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell41.setColspan (2);
					      cell41.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell41.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell41.setPadding (10.0f);
//					      cell41.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell41);
					      
					      PdfPCell cell42 = new PdfPCell (new Paragraph ("000.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell42.setColspan (2);
					      cell42.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell42.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell42.setPadding (10.0f);
//					      cell42.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell42);
					      
					      PdfPCell cell43 = new PdfPCell (new Paragraph ("000.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell43.setColspan (3);
					      cell43.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell43.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell43.setPadding (10.0f);
//					      cell43.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell43);
					      
					      PdfPCell cell44 = new PdfPCell (new Paragraph ("Q2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell44.setColspan (2);
					      cell44.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell44.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell44.setPadding (10.0f);
//					      cell44.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell44);
					      
					      PdfPCell cell45 = new PdfPCell (new Paragraph ("FFMXFTAG", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell45.setColspan (3);
					      cell45.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell45.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell45.setPadding (10.0f);
//					      cell45.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell45);
					      
					      PdfPCell cell46 = new PdfPCell (new Paragraph ("40723.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell46.setColspan (2);
					      cell46.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell46.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell46.setPadding (10.0f);
//					      cell46.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell46);
					      
					      PdfPCell cell47 = new PdfPCell (new Paragraph ("368.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell47.setColspan (2);
					      cell47.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell47.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell47.setPadding (10.0f);
//					      cell47.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell47);
					      
					      PdfPCell cell48 = new PdfPCell (new Paragraph ("368.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell48.setColspan (3);
					      cell48.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell48.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell48.setPadding (10.0f);
//					      cell48.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell48);
					      
					      PdfPCell cell49 = new PdfPCell (new Paragraph ("Q3", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell49.setColspan (2);
					      cell49.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell49.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell49.setPadding (10.0f);
//					      cell49.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell49);
					      
					      PdfPCell cell50 = new PdfPCell (new Paragraph ("QARBLRCB", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell50.setColspan (3);
					      cell50.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell50.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell50.setPadding (10.0f);
//					      cell50.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell50);
					      
					      PdfPCell cell51 = new PdfPCell (new Paragraph ("62028.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell51.setColspan (2);
					      cell51.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell51.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell51.setPadding (10.0f);
//					      cell51.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell51);
					      
					      PdfPCell cell52 = new PdfPCell (new Paragraph ("784.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell52.setColspan (2);
					      cell52.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell52.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell52.setPadding (10.0f);
//					      cell52.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell52);
					      
					      PdfPCell cell53 = new PdfPCell (new Paragraph ("784.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell53.setColspan (3);
					      cell53.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell53.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell53.setPadding (10.0f);
//					      cell53.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell53);
					      
					      PdfPCell cell54 = new PdfPCell (new Paragraph ("Q4", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell54.setColspan (2);
					      cell54.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell54.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell54.setPadding (10.0f);
//					      cell54.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell54);
					      
					      PdfPCell cell55 = new PdfPCell (new Paragraph ("QQOHRRIE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell55.setColspan (3);
					      cell55.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell55.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell55.setPadding (10.0f);
//					      cell55.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell55);
					      
					      PdfPCell cell56 = new PdfPCell (new Paragraph ("91011.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell56.setColspan (2);
					      cell56.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell56.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell56.setPadding (10.0f);
//					      cell56.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell56);
					      
					      PdfPCell cell57 = new PdfPCell (new Paragraph ("1812.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell57.setColspan (2);
					      cell57.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell57.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell57.setPadding (10.0f);
//					      cell57.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell57);
					      
					      PdfPCell cell58 = new PdfPCell (new Paragraph ("1812.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell58.setColspan (3);
					      cell58.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell58.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell58.setPadding (10.0f);
//					      cell58.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell58);
					      
					      PdfPCell cell59 = new PdfPCell (new Paragraph ("Total (Rs.)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell59.setColspan (2);
					      cell59.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell59.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell59.setPadding (10.0f);
//					      cell59.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell59);
					      
					      PdfPCell cell60 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell60.setColspan (3);
					      cell60.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell60.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell60.setPadding (10.0f);
					      cell60.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell60);
					      
					      PdfPCell cell61 = new PdfPCell (new Paragraph ("193762.00", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell61.setColspan (2);
					      cell61.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell61.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell61.setPadding (10.0f);
//					      cell61.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell61);
					      
					      PdfPCell cell62 = new PdfPCell (new Paragraph ("2964.00", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell62.setColspan (2);
					      cell62.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell62.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell62.setPadding (10.0f);
//					      cell62.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell62);
					      
					      PdfPCell cell63 = new PdfPCell (new Paragraph ("2964.00", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell63.setColspan (3);
					      cell63.setHorizontalAlignment (Element.ALIGN_RIGHT);
					      cell63.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell63.setPadding (10.0f);
//					      cell63.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell63);
					      
					     
					      
					      PdfPCell cell64 = new PdfPCell (new Paragraph ("I. DETAILS OF TAX DEDUCTED AND DEPOSITED IN THE CENTRAL GOVERNMENT ACCOUNT THROUGH BOOK ADJUSTMENT \n" +
						      		"(The deductor to provide payment wise details of tax deducted and deposited with respect to the deductee)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						  cell64.setColspan (12);
						  cell64.setHorizontalAlignment (Element.ALIGN_CENTER);
						  cell64.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						  cell64.setPadding (10.0f);
//						  cell64.setBackgroundColor (new BaseColor (140, 221, 8));					               
						  table.addCell(cell64);
					      
					      PdfPCell cell65 = new PdfPCell (new Paragraph ("Sl.No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell65.setColspan (1);
					      cell65.setRowspan (2);
					      cell65.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell65.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell65.setPadding (10.0f);
//					      cell65.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell65);
					      
					      PdfPCell cell66 = new PdfPCell (new Paragraph ("Tax Deposited in respect of the \n" +
					      		"deductee \n" +
					      		"(Rs.)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell66.setColspan (3);
					      cell66.setRowspan (2);
					      cell66.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell66.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell66.setPadding (10.0f);
//					      cell66.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell66);
					      
					      PdfPCell cell67 = new PdfPCell (new Paragraph ("Book Identification Number (BIN)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell67.setColspan (8);
//					      cell67.setRowspan (15);
					      cell67.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell67.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell67.setPadding (10.0f);
//					      cell67.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell67);
					      
					      PdfPCell cell68 = new PdfPCell (new Paragraph ("Receipt Number of Form \n" +
					      		"No.24G", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell68.setColspan (2);
//					      cell68.setRowspan (15);
					      cell68.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell68.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell68.setPadding (10.0f);
//					      cell68.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell68);
					      
					      PdfPCell cell69 = new PdfPCell (new Paragraph ("DDO serial number in Form \n" +
					      		"No.24G", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell69.setColspan (2);
//					      cell69.setRowspan (15);
					      cell69.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell69.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell69.setPadding (10.0f);
//					      cell69.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell69);
					      
					      PdfPCell cell70 = new PdfPCell (new Paragraph ("Date of transfer voucher \n" +
					      		"(dd/mm/yyyy)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell70.setColspan (2);
//					      cell70.setRowspan (15);
					      cell70.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell70.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell70.setPadding (10.0f);
//					      cell70.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell70);
					      
					      PdfPCell cell71 = new PdfPCell (new Paragraph ("Status of matching \n" +
					      		"with Form No. 24G", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell71.setColspan (2);
//					      cell71.setRowspan (15);
					      cell71.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell71.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell71.setPadding (10.0f);
//					      cell71.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell71);
					      
					      PdfPCell cell72 = new PdfPCell (new Paragraph ("Total(Rs.)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell72.setColspan (1);
//					      cell72.setRowspan (15);
					      cell72.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell72.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell72.setPadding (10.0f);
//					      cell72.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell72);
					      
					      PdfPCell cell73 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell73.setColspan (3);
//					      cell73.setRowspan (3);
					      cell73.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell73.setPadding (10.0f);
//					      cell73.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell73);
					      
					      PdfPCell cell74 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell74.setColspan (8);
//					      cell74.setRowspan (3);
					      cell74.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell74.setPadding (10.0f);
					      cell74.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell74);
					      
					      PdfPCell cell75 = new PdfPCell (new Paragraph ("II. DETAILS OF TAX DEDUCTED AND DEPOSITED IN THE CENTRAL GOVERNMENT ACCOUNT THROUGH CHALLAN \n" +
						      		"(The deductor to provide payment wise details of tax deducted and deposited with respect to the deductee)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell75.setColspan (12);
//					      cell75.setRowspan (2);
					      cell75.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell75.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell75.setPadding (10.0f);
//					      cell75.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell75);
					      
					      PdfPCell cell76 = new PdfPCell (new Paragraph ("Sl.No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell76.setColspan (1);
					      cell76.setRowspan (2);
					      cell76.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell76.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell76.setPadding (10.0f);
//					      cell76.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell76);
					      
					      PdfPCell cell77 = new PdfPCell (new Paragraph ("Tax Deposited in respect of the \n" +
					      		"deductee \n" +
					      		"(Rs.)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell77.setColspan (3);
					      cell77.setRowspan (2);
					      cell77.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell77.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell77.setPadding (10.0f);
//					      cell77.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell77);
					      
					      PdfPCell cell78 = new PdfPCell (new Paragraph ("Challan Identification Number (CIN)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell78.setColspan (8);
//					      cell78.setRowspan (2);
					      cell78.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell78.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell75.setPadding (10.0f);
//					      cell78cell78.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell78);
					      
					      PdfPCell cell79 = new PdfPCell (new Paragraph ("BSR Code of the Bank \n" +
					      		"Branch", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell79.setColspan (2);
//					      cell79.setRowspan (2);
					      cell79.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell79.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell79.setPadding (10.0f);
//					      cell79.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell79);
					      
					      PdfPCell cell80 = new PdfPCell (new Paragraph ("Date on which Tax deposited \n" +
					      		"(dd/mm/yyyy)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell80.setColspan (2);
//					      cell80.setRowspan (2);
					      cell80.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell80.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell80.setPadding (10.0f);
//					      cell80.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell80);
					      
					      PdfPCell cell81 = new PdfPCell (new Paragraph ("Challan Serial Number", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell81.setColspan (2);
//					      cell81.setRowspan (2);
					      cell81.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell81.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell81.setPadding (10.0f);
//					      cell81.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell81);
					      
					      PdfPCell cell82 = new PdfPCell (new Paragraph ("Status of matching with \n" +
					      		"OLTAS*", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell82.setColspan (2);
//					      cell82.setRowspan (2);
					      cell82.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell82.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell82.setPadding (10.0f);
//					      cell82.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell82);
					      
					      PdfPCell cell83 = new PdfPCell (new Paragraph ("1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell83.setColspan (1);
//					      cell83.setRowspan (2);
					      cell83.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell83.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell83.setPadding (10.0f);
//					      cell83.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell83);
					      
					      PdfPCell cell84 = new PdfPCell (new Paragraph ("161.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell84.setColspan (3);
//					      cell84.setRowspan (2);
					      cell84.setHorizontalAlignment (Element.ALIGN_RIGHT);
//					      cell84.setPadding (10.0f);
//					      cell84.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell84);
					      
					      PdfPCell cell85 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell85.setColspan (2);
//					      cell85.setRowspan (2);
					      cell85.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell85.setPadding (10.0f);
//					      cell85.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell85);
					      
					      PdfPCell cell86 = new PdfPCell (new Paragraph ("21-08-2013", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell86.setColspan (2);
//					      cell86.setRowspan (2);
					      cell86.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell86.setPadding (10.0f);
//					      cell86.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell86);
					      
					      PdfPCell cell87 = new PdfPCell (new Paragraph ("51970", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell87.setColspan (2);
//					      cell87.setRowspan (2);
					      cell87.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell87.setPadding (10.0f);
//					      cell87.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell87);
					      
					      PdfPCell cell88 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell88.setColspan (2);
//					      cell88.setRowspan (9);
					      cell88.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell88.setPadding (10.0f);
//					      cell88.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell88);
					      
					      PdfPCell cell89 = new PdfPCell (new Paragraph ("2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell89.setColspan (1);
//					      cell89.setRowspan (9);
					      cell89.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell89.setPadding (10.0f);
//					      cell89.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell89);
					      
					      PdfPCell cell90 = new PdfPCell (new Paragraph ("207.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell90.setColspan (3);
//					      cell90.setRowspan (9);
					      cell90.setHorizontalAlignment (Element.ALIGN_RIGHT);
//					      cell90.setPadding (10.0f);
//					      cell90.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell90);
					      
					      PdfPCell cell91 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell91.setColspan (2);
//					      cell91.setRowspan (9);
					      cell91.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell91.setPadding (10.0f);
//					      cell91.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell91);
					      
					      PdfPCell cell92 = new PdfPCell (new Paragraph ("17-09-2013", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell92.setColspan (2);
//					      cell92.setRowspan (9);
					      cell92.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell92.setPadding (10.0f);
//					      cell92.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell92);
					      
					      PdfPCell cell93 = new PdfPCell (new Paragraph ("52876", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell93.setColspan (2);
//					      cell93.setRowspan (4);
					      cell93.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell93.setPadding (10.0f);
//					      cell93.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell93);
					      
					      PdfPCell cell94 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell94.setColspan (2);
//					      cell94.setRowspan (9);
					      cell94.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell94.setPadding (10.0f);
//					      cell94.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell94);
					      
					      PdfPCell cell95 = new PdfPCell (new Paragraph ("3", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell95.setColspan (1);
//					      cell95.setRowspan (9);
					      cell95.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell95.setPadding (10.0f);
//					      cell95.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell95);
					      
					      PdfPCell cell96 = new PdfPCell (new Paragraph ("254.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell96.setColspan (3);
//					      cell96.setRowspan (9);
					      cell96.setHorizontalAlignment (Element.ALIGN_RIGHT);
//					      cell96.setPadding (10.0f);
//					      cell96.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell96);
					      
					      PdfPCell cell97 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell97.setColspan (2);
//					      cell97.setRowspan (9);
					      cell97.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell97.setPadding (10.0f);
//					      cell97.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell97);
					      
					      PdfPCell cell98 = new PdfPCell (new Paragraph ("22-10-2013", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell98.setColspan (2);
//					      cell98.setRowspan (9);
					      cell98.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell98.setPadding (10.0f);
//					      cell98.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell98);
					      
					      PdfPCell cell99 = new PdfPCell (new Paragraph ("51443", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell99.setColspan (2);
//					      cell99.setRowspan (9);
					      cell99.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell99.setPadding (10.0f);
//					      cell99.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell99);
					      
					      PdfPCell cell100 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell100.setColspan (2);
//					      cell100.setRowspan (9);
					      cell100.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell100.setPadding (10.0f);
//					      cell100.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell100);
					      
					      PdfPCell cell101 = new PdfPCell (new Paragraph ("4", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell101.setColspan (1);
//					      cell101.setRowspan (9);
					      cell101.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell101.setPadding (10.0f);
//					      cell101.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell101);
					      
					      PdfPCell cell102 = new PdfPCell (new Paragraph ("293.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell102.setColspan (3);
//					      cell102.setRowspan (9);
					      cell102.setHorizontalAlignment (Element.ALIGN_RIGHT);
//					      cell102.setPadding (10.0f);
//					      cell102.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell102);
					      
					      PdfPCell cell103 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell103.setColspan (2);
//					      cell103.setRowspan (9);
					      cell103.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell103.setPadding (10.0f);
//					      cell103.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell103);
					      
					      PdfPCell cell104 = new PdfPCell (new Paragraph ("15-11-2013", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell104.setColspan (2);
//					      cell104.setRowspan (2);
					      cell104.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell104.setPadding (10.0f);
//					      cell104.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell104);
					      
					      PdfPCell cell105 = new PdfPCell (new Paragraph ("42393", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell105.setColspan (2);
//					      cell105.setRowspan (9);
					      cell105.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell105.setPadding (10.0f);
//					      cell105.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell105);
					      
					      PdfPCell cell106 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell106.setColspan (2);
//					      cell106.setRowspan (9);
					      cell106.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell106.setPadding (10.0f);
//					      cell106.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell106);
					      
					      PdfPCell cell107 = new PdfPCell (new Paragraph ("5", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell107.setColspan (1);
//					      cell107.setRowspan (9);
					      cell107.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell107.setPadding (10.0f);
//					      cell107.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell107);
					      
					      PdfPCell cell108 = new PdfPCell (new Paragraph ("237.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell108.setColspan (3);
//					      cell108.setRowspan (9);
					      cell108.setHorizontalAlignment (Element.ALIGN_RIGHT);
//					      cell108.setPadding (10.0f);
//					      cell108.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell108);
					      
					      PdfPCell cell109 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell109.setColspan (2);
//					      cell109.setRowspan (9);
					      cell109.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell109.setPadding (10.0f);
//					      cell109.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell109);
					      
					      PdfPCell cell110 = new PdfPCell (new Paragraph ("16-12-2013", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell110.setColspan (2);
//					      cell110.setRowspan (9);
					      cell110.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell110.setPadding (10.0f);
//					      cell110.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell110);
					      
					      PdfPCell cell111 = new PdfPCell (new Paragraph ("44527", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell111.setColspan (2);
//					      cell111.setRowspan (9);
					      cell111.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell111.setPadding (10.0f);
//					      cell111.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell111);
					      
					      PdfPCell cell112 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell112.setColspan (2);
//					      cell112.setRowspan (9);
					      cell112.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell112.setPadding (10.0f);
//					      cell112.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell112);
					      
					     
					      
					      
					      
//					      
//					      document.newPage();
//					      table.setSpacingBefore(20.0f);       // Space Before table starts, like margin-top in CSS
//					      table.setSpacingAfter(150.0f);  
//					      
					      Paragraph addTableSpace = new Paragraph();
					      addTableSpace.setSpacingAfter(30);
//					      
//					      PdfPTable table1=new PdfPTable(12);
//					      
//					      table1.setWidthPercentage(100);
//					      
					      
//					      PdfPCell cell201 = new PdfPCell (new Paragraph ("Sl.No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell201.setColspan (1);
//					      cell201.setRowspan (2);
//					      cell201.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell201.setVerticalAlignment (Element.ALIGN_MIDDLE);
////					      cell201.setPadding (10.0f);
////					      cell201.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell201);
//					      
//					      PdfPCell cell202 = new PdfPCell (new Paragraph ("Tax Deposited in respect of the \n" +
//					      		"deductee \n" +
//					      		"(Rs.)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell202.setColspan (3);
//					      cell202.setRowspan (2);
//					      cell202.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell202.setVerticalAlignment (Element.ALIGN_MIDDLE);
////					      cell202.setPadding (10.0f);
////					      cell202.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell202);
//					      
//					      PdfPCell cell203 = new PdfPCell (new Paragraph ("Challan Identification Number (CIN)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell203.setColspan (8);
////					      cell203.setRowspan (2);
//					      cell203.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell203.setVerticalAlignment (Element.ALIGN_MIDDLE);
////					      cell203.setPadding (10.0f);
////					      cell203.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell203);
//					      
//					      PdfPCell cell204 = new PdfPCell (new Paragraph ("BSR Code of the Bank \n" +
//					      		"Branch", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell204.setColspan (2);
////					      cell204.setRowspan (2);
//					      cell204.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell204.setVerticalAlignment (Element.ALIGN_MIDDLE);
////					      cell204.setPadding (10.0f);
////					      cell204.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell204);
//					      
//					      PdfPCell cell205 = new PdfPCell (new Paragraph ("Date on which Tax deposited \n" +
//					      		"(dd/mm/yyyy)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell205.setColspan (2);
////					      cell205.setRowspan (2);
//					      cell205.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell205.setVerticalAlignment (Element.ALIGN_MIDDLE);
////					      cell205.setPadding (10.0f);
////					      cell205.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell205);
//					      
//					      PdfPCell cell206 = new PdfPCell (new Paragraph ("Challan Serial Number", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell206.setColspan (2);
////					      cell206.setRowspan (2);
//					      cell206.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell206.setVerticalAlignment (Element.ALIGN_MIDDLE);
////					      cell206.setPadding (10.0f);
////					      cell206.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell206);
//					      
//					      PdfPCell cell207 = new PdfPCell (new Paragraph ("Status of matching with \n" +
//					      		"OLTAS*", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell207.setColspan (2);
////					      cell207.setRowspan (2);
//					      cell207.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell207.setVerticalAlignment (Element.ALIGN_MIDDLE);
////					      cell82.setPadding (10.0f);
////					      cell207.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell207);
					      
					      PdfPCell cell208 = new PdfPCell (new Paragraph ("6", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell208.setColspan (1);
//					      cell208.setRowspan (2);
					      cell208.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell208.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell208.setPadding (10.0f);
//					      cell208.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell208);
					      
					      PdfPCell cell209 = new PdfPCell (new Paragraph ("211.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell209.setColspan (3);
//					      cell209.setRowspan (2);
					      cell209.setHorizontalAlignment (Element.ALIGN_RIGHT);
//					      cell209.setPadding (10.0f);
//					      cell209.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell209);
					      
					      PdfPCell cell210 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell210.setColspan (2);
//					      cell210.setRowspan (2);
					      cell210.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell210.setPadding (10.0f);
//					      cell210.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell210);
					      
					      PdfPCell cell211 = new PdfPCell (new Paragraph ("20-01-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell211.setColspan (2);
//					      cell211.setRowspan (2);
					      cell211.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell211.setPadding (10.0f);
//					      cell211.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell211);
					      
					      PdfPCell cell212 = new PdfPCell (new Paragraph ("42082", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell212.setColspan (2);
//					      cell212.setRowspan (2);
					      cell212.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell212.setPadding (10.0f);
//					      cell212.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell212);
					      
					      PdfPCell cell213 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell213.setColspan (2);
//					      cell213.setRowspan (9);
					      cell213.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell213.setPadding (10.0f);
//					      cell213.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell213);
					      
					      PdfPCell cell214 = new PdfPCell (new Paragraph ("7", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell214.setColspan (1);
//					      cell214.setRowspan (9);
					      cell214.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell214.setPadding (10.0f);
//					      cell214.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell214);
					      
					      PdfPCell cell215 = new PdfPCell (new Paragraph ("317.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell215.setColspan (3);
//					      cell215.setRowspan (9);
					      cell215.setHorizontalAlignment (Element.ALIGN_RIGHT);
//					      cell215.setPadding (10.0f);
//					      cell215.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell215);
					      
					      PdfPCell cell216 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell216.setColspan (2);
//					      cell216.setRowspan (9);
					      cell216.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell216.setPadding (10.0f);
//					      cell216.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell216);
					      
					      PdfPCell cell217 = new PdfPCell (new Paragraph ("18-02-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell217.setColspan (2);
//					      cell217.setRowspan (9);
					      cell217.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell217.setPadding (10.0f);
//					      cell217.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell217);
					      
					      PdfPCell cell218 = new PdfPCell (new Paragraph ("43109", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell218.setColspan (2);
//					      cell218.setRowspan (4);
					      cell218.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell218.setPadding (10.0f);
//					      cell218.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell218);
					      
					      PdfPCell cell219 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell219.setColspan (2);
//					      cell219.setRowspan (9);
					      cell219.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell219.setPadding (10.0f);
//					      cell219.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell219);
					      
					      PdfPCell cell220 = new PdfPCell (new Paragraph ("8", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell220.setColspan (1);
//					      cell220.setRowspan (9);
					      cell220.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell220.setPadding (10.0f);
//					      cell220.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell220);
					      
					      PdfPCell cell221 = new PdfPCell (new Paragraph ("181.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell221.setColspan (3);
//					      cell221.setRowspan (9);
					      cell221.setHorizontalAlignment (Element.ALIGN_RIGHT);
//					      cell221.setPadding (10.0f);
//					      cell221.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell221);
					      
					      PdfPCell cell222 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell222.setColspan (2);
//					      cell222.setRowspan (9);
					      cell222.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell222.setPadding (10.0f);
//					      cell222.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell222);
					      
					      PdfPCell cell223 = new PdfPCell (new Paragraph ("18-03-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell223.setColspan (2);
//					      cell223.setRowspan (9);
					      cell223.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell223.setPadding (10.0f);
//					      cell223.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell223);
					      
					      PdfPCell cell224 = new PdfPCell (new Paragraph ("48246", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell224.setColspan (2);
//					      cell224.setRowspan (9);
					      cell224.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell224.setPadding (10.0f);
//					      cell224.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell224);
					      
					      PdfPCell cell225 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell225.setColspan (2);
//					      cell225.setRowspan (9);
					      cell225.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell225.setPadding (10.0f);
//					      cell225.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell225);
					      
					      PdfPCell cell226 = new PdfPCell (new Paragraph ("9", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell226.setColspan (1);
//					      cell226.setRowspan (9);
					      cell226.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell226.setPadding (10.0f);
//					      cell226.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell226);
					      
					      PdfPCell cell227 = new PdfPCell (new Paragraph ("1013.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell227.setColspan (3);
//					      cell227.setRowspan (9);
					      cell227.setHorizontalAlignment (Element.ALIGN_RIGHT);
//					      cell227.setPadding (10.0f);
//					      cell227.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell227);
					      
					      PdfPCell cell228 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell228.setColspan (2);
//					      cell228.setRowspan (9);
					      cell228.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell228.setPadding (10.0f);
//					      cell228.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell228);
					      
					      PdfPCell cell229 = new PdfPCell (new Paragraph ("15-04-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell229.setColspan (2);
//					      cell229.setRowspan (2);
					      cell229.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell229.setPadding (10.0f);
//					      cell229.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell229);
					      
					      PdfPCell cell230 = new PdfPCell (new Paragraph ("42525", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell230.setColspan (2);
//					      cell230.setRowspan (9);
					      cell230.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell230.setPadding (10.0f);
//					      cell230.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell230);
					      
					      PdfPCell cell231 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell231.setColspan (2);
//					      cell231.setRowspan (9);
					      cell231.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell231.setPadding (10.0f);
//					      cell231.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell231);
					      
					      PdfPCell cell232 = new PdfPCell (new Paragraph ("10", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell232.setColspan (1);
//					      cell232.setRowspan (9);
					      cell232.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell232.setPadding (10.0f);
//					      cell232.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell232);
					      
					      PdfPCell cell233 = new PdfPCell (new Paragraph ("90.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell233.setColspan (3);
//					      cell233.setRowspan (9);
					      cell233.setHorizontalAlignment (Element.ALIGN_RIGHT);
//					      cell233.setPadding (10.0f);
//					      cell233.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell233);
					      
					      PdfPCell cell234 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell234.setColspan (2);
//					      cell234.setRowspan (9);
					      cell234.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell234.setPadding (10.0f);
//					      cell234.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell234);
					      
					      PdfPCell cell235 = new PdfPCell (new Paragraph ("02-05-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell235.setColspan (2);
//					      cell235.setRowspan (9);
					      cell235.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell235.setPadding (10.0f);
//					      cell235.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell235);
					      
					      PdfPCell cell236 = new PdfPCell (new Paragraph ("43861", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell236.setColspan (2);
//					      cell236.setRowspan (9);
					      cell236.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell236.setPadding (10.0f);
//					      cell236.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell236);
					      
					      PdfPCell cell237 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell237.setColspan (2);
//					      cell237.setRowspan (9);
					      cell237.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell237.setPadding (10.0f);
//					      cell237.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell237);
					      
					      PdfPCell cell232a = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell232a.setColspan (1);
//					      cell232a.setRowspan (9);
					      cell232a.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell232a.setPadding (10.0f);
//					      cell232a.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell232a);
					      
					      PdfPCell cell233a = new PdfPCell (new Paragraph ("90.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell233a.setColspan (3);
//					      cell233a.setRowspan (9);
					      cell233a.setHorizontalAlignment (Element.ALIGN_RIGHT);
//					      cell233a.setPadding (10.0f);
//					      cell233a.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell233a);
					      
					      PdfPCell cell234a = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell234a.setColspan (2);
//					      cell234a.setRowspan (9);
					      cell234a.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell234a.setPadding (10.0f);
//					      cell234a.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell234a);
					      
					      PdfPCell cell235a = new PdfPCell (new Paragraph ("02-05-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell235a.setColspan (2);
//					      cell235a.setRowspan (9);
					      cell235a.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell235a.setPadding (10.0f);
//					      cell235a.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell235a);
					      
					      PdfPCell cell236a = new PdfPCell (new Paragraph ("43861", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell236a.setColspan (2);
//					      cell236a.setRowspan (9);
					      cell236a.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell236a.setPadding (10.0f);
//					      cell236a.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell236a);
					      
					      PdfPCell cell237a = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell237a.setColspan (2);
//					      cell237a.setRowspan (9);
					      cell237a.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell237a.setPadding (10.0f);
//					      cell237a.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell237a);
					      
					      PdfPCell cell232b = new PdfPCell (new Paragraph ("12", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell232b.setColspan (1);
//					      cell232b.setRowspan (9);
					      cell232b.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell232b.setPadding (10.0f);
//					      cell232b.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell232b);
					      
					      PdfPCell cell233b = new PdfPCell (new Paragraph ("90.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell233b.setColspan (3);
//					      cell233b.setRowspan (9);
					      cell233b.setHorizontalAlignment (Element.ALIGN_RIGHT);
//					      cell233b.setPadding (10.0f);
//					      cell233b.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell233b);
					      
					      PdfPCell cell234b = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell234b.setColspan (2);
//					      cell234b.setRowspan (9);
					      cell234b.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell234b.setPadding (10.0f);
//					      cell234b.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell234b);
					      
					      PdfPCell cell235b = new PdfPCell (new Paragraph ("02-05-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell235b.setColspan (2);
//					      cell235b.setRowspan (9);
					      cell235b.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell235b.setPadding (10.0f);
//					      cell235b.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell235b);
					      
					      PdfPCell cell236b = new PdfPCell (new Paragraph ("43861", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell236b.setColspan (2);
//					      cell236b.setRowspan (9);
					      cell236b.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell236b.setPadding (10.0f);
//					      cell236b.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell236b);
					      
					      PdfPCell cell237b = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell237b.setColspan (2);
//					      cell237b.setRowspan (9);
					      cell237b.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell237b.setPadding (10.0f);
//					      cell237b.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell237b);
					      
					      PdfPCell cell238 = new PdfPCell (new Paragraph ("Total (Rs.)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell238.setColspan (1);
//					      cell238.setRowspan (9);
					      cell238.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell238.setPadding (10.0f);
//					      cell238.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell238);
					      
					      PdfPCell cell239 = new PdfPCell (new Paragraph ("2964.00", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell239.setColspan (3);
//					      cell239.setRowspan (9);
					      cell239.setHorizontalAlignment (Element.ALIGN_RIGHT);
//					      cell239.setPadding (10.0f);
//					      cell239.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell239);
					      
					      PdfPCell cell240 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell240.setColspan (8);
//					      cell240.setRowspan (9);
					      cell240.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell240.setPadding (10.0f);
					      cell240.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell240);
					      
					      PdfPCell cell241 = new PdfPCell (new Paragraph ("Verification", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell241.setColspan (12);
//					      cell241.setRowspan (9);
					      cell241.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell241.setVerticalAlignment (Element.ALIGN_MIDDLE);
					      cell241.setPadding (5.0f);
//					      cell241.setBackgroundColor (new BaseColor (100, 100, 100));					               
					      table.addCell(cell241);
					      
					      
					      PdfPCell cell242 = new PdfPCell (new Paragraph ("1. RASHMI DHIRAJLAL PARIKH, son / daughter of DHIRAJLAL PARIKH working in the capacity of PROPRIETOR (designation) do hereby certify that a sum of " +
					      		"Rs.2964.00 [Rs. Two Thousand Nine Hundred and Sixty Four Only (in words)] has been deducted and a sum of Rs.2964.00 [Rs. Two Thousand Nine Hundred and " +
					      		"Sixty Four Only] has been deposited to the credit of the Central Government. I further certify that the information given above is true, complete and correct and is " +
					      		"based on the books of account, documents, TDS statements, TDS deposited and other available records.", FontFactory.getFont(FontFactory.TIMES_BOLD, 7)));
					      cell242.setColspan (12);
//					      cell242.setRowspan (9);
					      cell242.setHorizontalAlignment (Element.ALIGN_UNDEFINED);
					      cell242.setVerticalAlignment (Element.ALIGN_MIDDLE);
					      cell242.setPadding (5.0f);
//					      cell242.setBackgroundColor (new BaseColor (100, 100, 100));					               
					      table.addCell(cell242);
					      
					      
					      PdfPCell cell243 = new PdfPCell (new Paragraph ("Place", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell243.setColspan (2);
//					      cell243.setRowspan (9);
					      cell243.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell243.setVerticalAlignment (Element.ALIGN_MIDDLE);
					      cell243.setPadding (3.0f);
//					      cell243.setBackgroundColor (new BaseColor (100, 100, 100));					               
					      table.addCell(cell243);
					      
					      PdfPCell cell244 = new PdfPCell (new Paragraph ("MUMBAI", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell244.setColspan (4);
//					      cell244.setRowspan (9);
					      cell244.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell244.setVerticalAlignment (Element.ALIGN_MIDDLE);
					      cell244.setPadding (3.0f);
//					      cell244.setBackgroundColor (new BaseColor (100, 100, 100));					               
					      table.addCell(cell244);
					      
					      PdfPCell cell245 = new PdfPCell (new Paragraph ("(Signature of person responsible for deduction of Tax)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell245.setColspan (6);
					      cell245.setRowspan (2);
					      cell245.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell245.setVerticalAlignment (Element.ALIGN_BOTTOM);
					      cell245.setPadding (3.0f);
//					      cell245.setBackgroundColor (new BaseColor (100, 100, 100));					               
					      table.addCell(cell245);
					      
					      PdfPCell cell246 = new PdfPCell (new Paragraph ("Date", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell246.setColspan (2);
//					      cell246.setRowspan (9);
					      cell246.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell246.setVerticalAlignment (Element.ALIGN_MIDDLE);
					      cell246.setPadding (3.0f);
//					      cell246.setBackgroundColor (new BaseColor (100, 100, 100));					               
					      table.addCell(cell246);
					      
					      PdfPCell cell247 = new PdfPCell (new Paragraph ("15-May-2014", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell247.setColspan (4);
//					      cell247.setRowspan (9);
					      cell247.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell247.setVerticalAlignment (Element.ALIGN_MIDDLE);
					      cell247.setPadding (3.0f);
//					      cell247.setBackgroundColor (new BaseColor (100, 100, 100));					               
					      table.addCell(cell247);
					      
					      PdfPCell cell248 = new PdfPCell (new Paragraph ("Designation: PROPRIETOR", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell248.setColspan (6);
//					      cell248.setRowspan (9);
					      cell248.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell248.setVerticalAlignment (Element.ALIGN_MIDDLE);
					      cell248.setPadding (3.0f);
//					      cell248.setBackgroundColor (new BaseColor (100, 100, 100));					               
					      table.addCell(cell248);
					      
					      PdfPCell cell249 = new PdfPCell (new Paragraph ("Full Name: RASHMI DHIRAJLAL PARIKH", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
					      cell249.setColspan (6);
//					      cell249.setRowspan (9);
					      cell249.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell249.setVerticalAlignment (Element.ALIGN_MIDDLE);
					      cell249.setPadding (3.0f);
//					      cell249.setBackgroundColor (new BaseColor (100, 100, 100));					               
					      table.addCell(cell249);
					      
					      
					      Chunk chunkHeader = new Chunk("Notes: \n");
							Font font = FontFactory.getFont("TIMES_ROMAN");
							font.setStyle(Font.BOLD);
							font.setSize(10);
//							font.setColor(BaseColor.RED);
							chunkHeader.setFont(font);
							Paragraph paragraph = new Paragraph();
							paragraph.add(chunkHeader);
							paragraph.setAlignment(Element.ALIGN_LEFT);
							paragraph.setSpacingBefore(20);
							
							Chunk chunk2 = new Chunk("1. Part B (Annexure) of the certificate in Form No.16 shall be issued by employer.");
							Font font2 = FontFactory.getFont("TIMES_ROMAN");
							font2.setStyle(Font.NORMAL);
							font2.setSize(7);
//							font2.setColor(BaseColor.RED);
							chunk2.setFont(font2);
							Paragraph paragraph2 = new Paragraph();
							paragraph2.add(chunk2);
							paragraph2.setAlignment(Element.ALIGN_JUSTIFIED);
							paragraph2.setSpacingBefore(-8);
							
							Chunk chunk3 = new Chunk("2. If an assessee is employed under one employer during the year, Part 'A' of the certificate in Form No.16 issued for the quarter ending on 31st March of the financial");
							Font font3 = FontFactory.getFont("TIMES_ROMAN");
							font3.setStyle(Font.NORMAL);
							font3.setSize(7);
//							font3.setColor(BaseColor.RED);
							chunk3.setFont(font3);
							Paragraph paragraph3 = new Paragraph();
							paragraph3.add(chunk3);
							paragraph3.setAlignment(Element.ALIGN_JUSTIFIED);
							paragraph3.setSpacingBefore(-8);
							
							Chunk chunk31 = new Chunk("    year shall contain the details of the tax deducted and deposited for all the quarters of the financial year.");
							Font font31 = FontFactory.getFont("TIMES_ROMAN");
							font31.setStyle(Font.NORMAL);
							font31.setSize(7);
//							font31.setColor(BaseColor.RED);
							chunk31.setFont(font31);
							Paragraph paragraph31 = new Paragraph();
							paragraph31.add(chunk31);
							paragraph31.setAlignment(Element.ALIGN_JUSTIFIED);
							paragraph31.setSpacingBefore(-10);
							
							Chunk chunk4 = new Chunk("3. If an assessee is employed under more than one employer during the year, each of the employers shall issue  Part A  of the certificate in Form  No.16  pertaining  to");
							Font font4 = FontFactory.getFont("TIMES_ROMAN");
							font4.setStyle(Font.NORMAL);
							font4.setSize(7);
//							font4.setColor(BaseColor.RED);
							chunk4.setFont(font4);
							Paragraph paragraph4 = new Paragraph();
							paragraph4.setAlignment(Element.ALIGN_JUSTIFIED);
							paragraph4.add(chunk4);
							paragraph4.setSpacingBefore(-8);
							

							Chunk chunk41 = new Chunk("    the period for  which such  assessee was employed with each of the employers. Part B (Annexure) of the certificate in Form No.16 may be issued by each of the");
							Font font41 = FontFactory.getFont("TIMES_ROMAN");
							font41.setStyle(Font.NORMAL);
							font41.setSize(7);
//							font41.setColor(BaseColor.RED);
							chunk41.setFont(font41);
							Paragraph paragraph41 = new Paragraph();
							paragraph41.add(chunk41);
							paragraph41.setAlignment(Element.ALIGN_JUSTIFIED);
							paragraph41.setSpacingBefore(-10);
							
							Chunk chunk42 = new Chunk("    employers or the last employer at the option of the assessee. ");
							Font font42 = FontFactory.getFont("TIMES_ROMAN");
							font42.setStyle(Font.NORMAL);
							font42.setSize(7);
//							font42.setColor(BaseColor.RED);
							chunk42.setFont(font42);
							Paragraph paragraph42 = new Paragraph();
							paragraph42.add(chunk42);
							paragraph42.setAlignment(Element.ALIGN_JUSTIFIED);
							paragraph42.setSpacingBefore(-10);
							
							
							
							Chunk chunk5 = new Chunk("4. To update PAN details in Income Tax Department database, apply for 'PAN change request' through NSDL or UTITSL. ");
							Font font5 = FontFactory.getFont("TIMES_ROMAN");
							font5.setStyle(Font.NORMAL);
							font5.setSize(7);
//							font5.setColor(BaseColor.RED);
							chunk5.setFont(font5);
							Paragraph paragraph5 = new Paragraph();
							paragraph5.add(chunk5);
							paragraph5.setAlignment(Element.ALIGN_JUSTIFIED);
							paragraph5.setSpacingBefore(-8);
							paragraph5.setSpacingAfter(5);
							
							Chunk chunk6 = new Chunk("Legend used in Form 16");
							chunk6.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
							Font font6 = FontFactory.getFont("TIMES_ROMAN");
//							font6.setStyle(Font.UNDERLINE);
							font6.setStyle(Font.BOLD);
							font6.setSize(7);
//							font6.setColor(BaseColor.RED);
							chunk6.setFont(font6);
							Paragraph paragraph6 = new Paragraph();
							paragraph6.add(chunk6);
							paragraph6.setAlignment(Element.ALIGN_LEFT);
							paragraph6.setSpacingBefore(10);
							paragraph6.setSpacingAfter(5);
							
							Chunk chunk7 = new Chunk(" * Status of matching with OLTAS");
							Font font7 = FontFactory.getFont("TIMES_ROMAN");
							font7.setStyle(Font.BOLD);
							font7.setSize(7);
//							font6.setColor(BaseColor.RED);
							chunk7.setFont(font7);
							Paragraph paragraph7 = new Paragraph();
							paragraph7.add(chunk7);
							paragraph7.setAlignment(Element.ALIGN_LEFT);
							paragraph7.setSpacingBefore(5);
							paragraph7.setSpacingAfter(5);
					      
					      
							
							
							

						      document.newPage();
						      table.setSpacingBefore(20.0f);       // Space Before table starts, like margin-top in CSS
						      table.setSpacingAfter(300.0f);  
						      
						      PdfPTable table2=new PdfPTable(12);
						      
						      table2.setWidthPercentage(100);
						      
						      
						      PdfPCell cell301 = new PdfPCell (new Paragraph ("Legend", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell301.setColspan (1);
//						      cell301.setRowspan (2);
						      cell301.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell301.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell301.setPadding (10.0f);
						      cell301.setBackgroundColor (new BaseColor (110, 110, 110));					               
						      table2.addCell(cell301);
						      
						      PdfPCell cell302 = new PdfPCell (new Paragraph ("Description", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell302.setColspan (2);
//						      cell302.setRowspan (2);
						      cell302.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell302.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell302.setPadding (10.0f);
						      cell302.setBackgroundColor (new BaseColor (110, 110, 110));					               
						      table2.addCell(cell302);
						      
						      PdfPCell cell303 = new PdfPCell (new Paragraph ("Definition", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell303.setColspan (9);
//						      cell303.setRowspan (2);
						      cell303.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell303.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell303.setPadding (10.0f);
						      cell303.setBackgroundColor (new BaseColor (110, 110, 110));					               
						      table2.addCell(cell303);
					     
						      PdfPCell cell304 = new PdfPCell (new Paragraph ("U", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell304.setColspan (1);
//						      cell304.setRowspan (2);
						      cell304.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell304.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell304.setPadding (10.0f);
//						      cell304.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table2.addCell(cell304);
						      
						      PdfPCell cell305 = new PdfPCell (new Paragraph ("Unmatched", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
						      cell305.setColspan (2);
//						      cell305.setRowspan (2);
						      cell305.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell305.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell305.setPadding (10.0f);
//						      cell305.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table2.addCell(cell305);
						      
						      PdfPCell cell306 = new PdfPCell (new Paragraph ("Deductors have not deposited taxes or have furnished incorrect particulars of tax payment, Final credited will be reflected only when payment details in bank match with details of deposit in TDS/TCS statement", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
						      cell306.setColspan (9);
//						      cell306.setRowspan (2);
						      cell306.setHorizontalAlignment (Element.ALIGN_JUSTIFIED);
						      cell306.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell306.setPadding (10.0f);
//						      cell306.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table2.addCell(cell306);
						      
						      PdfPCell cell307 = new PdfPCell (new Paragraph ("P", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell307.setColspan (1);
//						      cell307.setRowspan (2);
						      cell307.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell307.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell307.setPadding (10.0f);
//						      cell307.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table2.addCell(cell307);
						      
						      PdfPCell cell308 = new PdfPCell (new Paragraph ("Provisional", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
						      cell308.setColspan (2);
//						      cell308.setRowspan (2);
						      cell308.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell308.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell308.setPadding (10.0f);
//						      cell308.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table2.addCell(cell308);
						      
						      PdfPCell cell309 = new PdfPCell (new Paragraph ("Provisional tax credit is effected only for TDS/TCS Statement filed by Government deductors. 'P' status will be changed to Final (F) on verification of payment details submitted by Pay and Accounts Officer (PAO)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
						      cell309.setColspan (9);
//						      cell309.setRowspan (2);
						      cell309.setHorizontalAlignment (Element.ALIGN_JUSTIFIED);
						      cell309.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell309.setPadding (10.0f);
//						      cell309.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table2.addCell(cell309);
						      
						      PdfPCell cell310 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell310.setColspan (1);
//						      cell310.setRowspan (2);
						      cell310.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell310.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell310.setPadding (10.0f);
//						      cell310.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table2.addCell(cell310);
						      
						      PdfPCell cell311 = new PdfPCell (new Paragraph ("Final", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
						      cell311.setColspan (2);
//						      cell311.setRowspan (2);
						      cell311.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell311.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell311.setPadding (10.0f);
//						      cell311.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table2.addCell(cell311);
						      
						      PdfPCell cell312 = new PdfPCell (new Paragraph ("In case of non-gorvenment deductors, payment details of TDS/TCS deposited in bank by deductor have matched with the payment details mentioned in the TDS/TCS statement filed by the deductors. Incase of government deductors, details of TDS/TCS booked in government account have been  verified by Pay & Account Officer (PAO). ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
						      cell312.setColspan (9);
//						      cell312.setRowspan (2);
						      cell312.setHorizontalAlignment (Element.ALIGN_JUSTIFIED);
						      cell312.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell312.setPadding (10.0f);
//						      cell312.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table2.addCell(cell312);
						      
						      PdfPCell cell313 = new PdfPCell (new Paragraph ("O", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell313.setColspan (1);
//						      cell313.setRowspan (2);
						      cell313.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell313.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell313.setPadding (10.0f);
//						      cell313.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table2.addCell(cell313);
						      
						      PdfPCell cell314 = new PdfPCell (new Paragraph ("Overbooked", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
						      cell314.setColspan (2);
//						      cell314.setRowspan (2);
						      cell314.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell314.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell314.setPadding (10.0f);
//						      cell314.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table2.addCell(cell314);
						      
						      PdfPCell cell315 = new PdfPCell (new Paragraph ("Payment details of TDS/TCS deposited in bank by deductor have matched with details mentioned in the TDS/TCS statement but the amount is over claimed in the statement. Final (F) credit will be reflected only when deductor reduces claimed amount in the statement or makes new payment for excess amount claimed in the statement.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
						      cell315.setColspan (9);
//						      cell315.setRowspan (2);
						      cell315.setHorizontalAlignment (Element.ALIGN_JUSTIFIED);
						      cell315.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell315.setPadding (10.0f);
//						      cell315.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table2.addCell(cell315);
						      
						      
						      
						      
						      
						      
						      document.newPage();
						      table.setSpacingBefore(300.0f);       // Space Before table starts, like margin-top in CSS
						      table.setSpacingAfter(150.0f);  
						      
						      PdfPTable table3=new PdfPTable(12);
						      
						      table3.setWidthPercentage(100);
						      
						      
						      PdfPCell cell401 = new PdfPCell (new Paragraph ("PART B (Annexure)", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
						      cell401.setColspan (12);
//						      cell401.setRowspan (2);
						      cell401.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell401.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell401.setPadding (10.0f);
//						      cell401.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell401);
						      
						      PdfPCell cell402 = new PdfPCell (new Paragraph ("Details of Salary paid and any other income and tax deducted", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell402.setColspan (12);
//						      cell402.setRowspan (2);
						      cell402.setHorizontalAlignment (Element.ALIGN_LEFT);
						      cell402.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell402.setPadding (10.0f);
//						      cell402.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell402);
						      
						      PdfPCell cell403 = new PdfPCell (new Paragraph ("1. Gross Salary \n" +
						      		"   (a) Salary as per provisions contained in section 17(1) \n" +
						      		"   (b) Value of perquisites under section 17(2) (as per Form No. 12BA, wherever\n" +
						      		"       applicable) \n" +
						      		"   (c) Profits in lieu of salary under section 17(3) (as per Form No. 12BA,\n" +
						      		"       wherever applicable) \n" +
						      		"   (d) Total \n" +
						      		"2. Less Allowance to the extent exempt under section 10 \n" +
						      		"   (a) CONVEYANCE ALLOWANCE \n" +
						      		"3. Balance (1-2) \n" +
						      		"4. Deductions: \n" +
						      		"   (a) Entertainment allowance \n" +
						      		"   (b) Tax on employment \n" +
						      		"5. Aggregate of 4(a) and (b) \n" +
						      		"6. Income chargeble under the head 'salaries' (3-5) \n" +
						      		"7. Add: Any other income reported by the employee \n" +
						      		"8. Gross total income (6 + 7) \n" +
						      		"9. Deductions under Chapter VIA \n" +
						      		"   (A) Sections 80C, 80CCC and 80CCD \n" +
						      		"       (a) Section 80C \n" +
						      		"       (b) Section 80CCC \n" +
						      		"       (c) Section 80CCD \n" +
						      		"   (B) Other Sections under Chapter VIA                              Gross Amount \n" +
						      		"       (a) \n" +
						      		"10. Aggregate of deductible amount under Chapter VIA \n" +
						      		"11. Total income (8-10) \n" +
						      		"12. Tax on total income \n" +
						      		"13. Education cess @ 3% (on tax at S.No.12) \n" +
						      		"14. Tax payable (12+13) \n" +
						      		"15. Less : Relief under section 89 (attach details) \n" +
						      		"16. Tax payable (14-15)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
						      cell403.setColspan (6);
//						      cell403.setRowspan (2);
						      cell403.setHorizontalAlignment (Element.ALIGN_LEFT);
//						      cell403.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell403.setPadding (10.0f);
//						      cell403.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell403);
						      
						      PdfPCell cell404 = new PdfPCell (new Paragraph ("\n" +
						      		"260,871.00\n" +
						      		"Nil\n" +
						      		"\n" +
						      		"Nil\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"Nil\n" +
						      		"2,500.00\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						       		"\n" +
						      		"Gross Amount\n" +
						      		"Nil\n" +
						      		"Nil\n" +
						      		"Nil\n" +
						      		"Qualifying Amount", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
						      cell404.setColspan (2);
//						      cell404.setRowspan (2);
						      cell404.setHorizontalAlignment (Element.ALIGN_RIGHT);
//						      cell404.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell404.setPadding (10.0f);
//						      cell404.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell404);
						      
						      PdfPCell cell405 = new PdfPCell (new Paragraph ("\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						       		"260,871.00\n" +
						      		"\n" +
						      		"9,600.00\n" +
						      		"251,271.00\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"2,500.00\n" +
						      		"\n" +
						      		"Nil\n" +
						      		"\n" +
						      		"\n" +
						      		"Deductible Amount\n" +
						      		"Nil\n" +
						      		"Nil\n" +
						      		"Nil\n" +
						      		"Deductible Amount", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
						      cell405.setColspan (2);
//						      cell405.setRowspan (2);
						      cell405.setHorizontalAlignment (Element.ALIGN_RIGHT);
//						      cell405.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell405.setPadding (10.0f);
//						      cell405.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell405);
						      
						      PdfPCell cell406 = new PdfPCell (new Paragraph ("\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"248,771.00\n" +
						      		"\n" +
						      		"248,771.00\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"\n" +
						      		"Nil\n" +
						      		"248,770.00\n" +
						      		"2,877.00\n" +
						      		"87.00\n" +
						      		"2,964.00\n" +
						      		"Nil\n" +
						      		"2,964.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
						      cell406.setColspan (2);
//						      cell406.setRowspan (2);
						      cell406.setHorizontalAlignment (Element.ALIGN_RIGHT);
//						      cell406.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell406.setPadding (10.0f);
//						      cell406.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell406);
						      
						      PdfPCell cell407 = new PdfPCell (new Paragraph ("Verification", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell407.setColspan (12);
//						      cell407.setRowspan (2);
						      cell407.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell407.setVerticalAlignment (Element.ALIGN_MIDDLE);
						      cell407.setPadding (5.0f);
//						      cell407.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell407);
						      
						      PdfPCell cell408 = new PdfPCell (new Paragraph ("1. RASHMI DHIRAJLAL PARIKH, son/daughter of DHIRAJLAL PARIKH working in the capacity of PROPRIETOR (designation) do hereby certify that the information given above is true, complete and correct and is based on the book of accounts, documents, TDS statements, and other available records.", FontFactory.getFont(FontFactory.TIMES_BOLD, 7)));
						      cell408.setColspan (12);
//						      cell408.setRowspan (2);
						      cell408.setHorizontalAlignment (Element.ALIGN_JUSTIFIED);
//						      cell408.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell408.setPadding (5.0f);
//						      cell408.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell408);
						      
						      PdfPCell cell409 = new PdfPCell (new Paragraph ("Place", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell409.setColspan (1);
//						      cell409.setRowspan (2);
						      cell409.setHorizontalAlignment (Element.ALIGN_LEFT);
						      cell409.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell409cell409.setPadding (10.0f);
//						      cell409.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell409);
						      
						      PdfPCell cell410 = new PdfPCell (new Paragraph ("MUMBAI", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell410.setColspan (5);
//						      cell410.setRowspan (2);
						      cell410.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell410.setVerticalAlignment (Element.ALIGN_MIDDLE);
						      cell410.setPadding (4.0f);
//						      cell410.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell410);
						      
						      PdfPCell cell411 = new PdfPCell (new Paragraph ("(Signature of person responsible for deduction of tax)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell411.setColspan (6);
						      cell411.setRowspan (2);
						      cell411.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell411.setVerticalAlignment (Element.ALIGN_BOTTOM);
//						      cell411.setPadding (4.0f);
//						      cell411.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell411);
						      
						      PdfPCell cell412 = new PdfPCell (new Paragraph ("Date", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell412.setColspan (1);
//						      cell412.setRowspan (2);
						      cell412.setHorizontalAlignment (Element.ALIGN_LEFT);
						      cell412.setVerticalAlignment (Element.ALIGN_MIDDLE);
						      cell412.setPadding (4.0f);
//						      cell412.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell412);
						      
						      PdfPCell cell413 = new PdfPCell (new Paragraph ("15-May-2014", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell413.setColspan (5);
//						      cell413.setRowspan (2);
						      cell413.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell413.setVerticalAlignment (Element.ALIGN_MIDDLE);
						      cell413.setPadding (4.0f);
//						      cell413.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell413);
						      
						      PdfPCell cell414 = new PdfPCell (new Paragraph ("Designation: PROPRIETOR", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell414.setColspan (6);
//						      cell414.setRowspan (2);
						      cell414.setHorizontalAlignment (Element.ALIGN_LEFT);
						      cell414.setVerticalAlignment (Element.ALIGN_MIDDLE);
						      cell414.setPadding (4.0f);
//						      cell414.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell414);
						      
						      PdfPCell cell415 = new PdfPCell (new Paragraph ("Full Name: RASHMI DHIRAJLAL PARIKH", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
						      cell415.setColspan (6);
//						      cell415.setRowspan (2);
						      cell415.setHorizontalAlignment (Element.ALIGN_LEFT);
						      cell415.setVerticalAlignment (Element.ALIGN_MIDDLE);
						      cell415.setPadding (4.0f);
//						      cell415.setBackgroundColor (new BaseColor (99, 99, 99));					               
						      table3.addCell(cell415);
						      
//
//		                     PdfPCell cell301 = new PdfPCell (new Paragraph ("School Teacher"));
//
//		                     cell301.setColspan (12);
//		                     cell301.setHorizontalAlignment (Element.ALIGN_CENTER);
//		                     cell301.setPadding (10.0f);
//		                     cell301.setBackgroundColor (new BaseColor (140, 221, 8));					               
//
//					      table1.addCell(cell301);						               
//
//					      table1.addCell("Name");
//					      table1.addCell("Address");
//					      table1.addCell("Country");
//					      table1.addCell("Java4s");
//					      table1.addCell("NC");
//					      table1.addCell("United States");
//					    
					      
					      
//					      
//					      PdfPTable table1=new PdfPTable(12);
//						     table1.setWidthPercentage(100);
//					      
//					    PdfPTable table1=new PdfPTable(12);
////					      
//					      PdfPCell cell113 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell113.setColspan (12);
////					      cell113.setRowspan (4);
//					      cell113.setHorizontalAlignment (Element.ALIGN_LEFT);
////					      cell113.setPadding (10.0f);
////					      cell113.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table1.addCell(cell113);
////					      
//					      PdfPCell cell114 = new PdfPCell (new Paragraph ("Aggregate of 4 (a to c)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell114.setColspan (3);
////					      cell114.setRowspan (9);
//					      cell114.setHorizontalAlignment (Element.ALIGN_LEFT);
////					      cell114.setPadding (10.0f);
////					      cell114.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table1.addCell(cell114);
//					      
//					      PdfPCell cell115 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell115.setColspan (3);
////					      cell115.setRowspan (9);
//					      cell115.setHorizontalAlignment (Element.ALIGN_LEFT);
////					      cell115.setPadding (10.0f);
////					      cell115.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table1.addCell(cell115);
//					      
//					      PdfPCell cell116 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell116.setColspan (2);
////					      cell116.setRowspan (9);
//					      cell116.setHorizontalAlignment (Element.ALIGN_LEFT);
////					      cell116.setPadding (10.0f);
////					      cell116.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table1.addCell(cell116);
//					      
//					      PdfPCell cell117 = new PdfPCell (new Paragraph ("Rs.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell117.setColspan (12);
////					      cell117.setRowspan (9);
//					      cell117.setHorizontalAlignment (Element.ALIGN_LEFT);
////					      cell117.setPadding (10.0f);
////					      cell117.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table1.addCell(cell117);
//					      
//					      PdfPCell cell118 = new PdfPCell (new Paragraph ("2500", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell118.setColspan (12);
////					      cell118.setRowspan (9);
//					      cell118.setHorizontalAlignment (Element.ALIGN_LEFT);
////					      cell118.setPadding (10.0f);
////					      cell118.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table1.addCell(cell118);
//					      
//					      PdfPCell cell119 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell119.setColspan (1);
////					      cell119.setRowspan (9);
//					      cell119.setHorizontalAlignment (Element.ALIGN_LEFT);
////					      cell119.setPadding (10.0f);
////					      cell119.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table1.addCell(cell119);
//					      
//					      PdfPCell cell120 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell120.setColspan (3);
////					      cell120.setRowspan (9);
//					      cell120.setHorizontalAlignment (Element.ALIGN_LEFT);
////					      cell120.setPadding (10.0f);
////					      cell120.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table1.addCell(cell120);
//					      
//					      PdfPCell cell121 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell121.setColspan (2);
////					      cell121.setRowspan (9);
//					      cell121.setHorizontalAlignment (Element.ALIGN_LEFT);
////					      cell121.setPadding (10.0f);
////					      cell121.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table1.addCell(cell121);
//					      
//					      PdfPCell cell122 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell122.setColspan (1);
////					      cell122.setRowspan (9);
//					      cell122.setHorizontalAlignment (Element.ALIGN_LEFT);
////					      cell122.setPadding (10.0f);
////					      cell122.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table1.addCell(cell122);
//					      
//					      PdfPCell cell123 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell123.setColspan (1);
////					      cell123.setRowspan (9);
//					      cell123.setHorizontalAlignment (Element.ALIGN_LEFT);
////					      cell123.setPadding (10.0f);
////					      cell123.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table1.addCell(cell123);
//					      
//					      PdfPCell cell124 = new PdfPCell (new Paragraph ("6", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell124.setColspan (1);
////					      cell124.setRowspan (9);
//					      cell124.setHorizontalAlignment (Element.ALIGN_LEFT);
////					      cell124.setPadding (10.0f);
////					      cell124.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table1.addCell(cell124);
//					      
//					      PdfPCell cell125 = new PdfPCell (new Paragraph ("Income Chargeable under", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
//					      cell125.setColspan (7);
////					      cell125.setRowspan (9);
//					      cell125.setHorizontalAlignment (Element.ALIGN_LEFT);
////					      cell125.setPadding (10.0f);
////					      cell125.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table1.addCell(cell125);
//					    
					    
					      
					      


					      						               
					      
//					      table.addCell(new Phrase("Name of Oppointment", FontFactory.getFont(FontFactory.HELVETICA, 8)));
//					      table.addCell("Whether substantive or officiating and whether permanent or temporary");
//					      table.addCell("Nature of Original Vacancy");
//	                      table.addCell("Pay in substantive appointment");
//					      table.addCell("Additional Pay for officiating");
//					      table.addCell("Other Emolumnets falling under the term pay");
//					      table.addCell("Date of Oppointment");
//					      table.addCell("Singature of Employee");
//					      table.addCell("Signature and designation of the Principal or Chief Executive Officer or Other attesting Officer in attestation of column 1 to 8");
//					      table.addCell("Date of Termination of Appointment");
//					      table.addCell("Reason of termination (such as promotion, transfer, dismissal etc.");
//					      table.addCell("Signature of the Head or Chief Executive Officer or Other attesting Officer ");
//					      table.addCell("Nature & duration of leave taken");
//					      table.addCell("Signature of the Head or Chief Executive Officer or Other attesting Officer ");
//					      table.addCell("Referemce to any recorded punishment or censure or reward or praise of the employee");
					      
					      
					      
//					      table.setSpacingBefore(10.0f);       // Space Before table starts, like margin-top in CSS
//					      table.setSpacingAfter(10.0f);        // Space After table starts, like margin-Bottom in CSS								          

				 //Inserting List in PDF
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

						document.add(Chunk.NEWLINE);   //Something like in HTML :-)

//	                   document.add(new Paragraph("Dear Java4s.com"));
//		                document.add(new Paragraph("Document Generated On - "+new Date().toString()));	
		              
		                
		                //for column width size 
		                float[] columnWidths = new float[] {10f, 12f, 10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f};
		                table.setWidths(columnWidths);
//		                table1.setWidths(columnWidths);
		                table2.setWidths(columnWidths);
		                table3.setWidths(columnWidths);
		                
		                document.add(table);
		                document.add(addTableSpace);
//		                document.add(table1);
		                document.add(table3);
		                document.add(paragraph);
		                document.add(paragraph2);
		                document.add(paragraph3);
		                document.add(paragraph31);
		                document.add(paragraph4);
		                document.add(paragraph41);
		                document.add(paragraph42);
		                document.add(paragraph5);
		                document.add(paragraph6);
		                document.add(paragraph7);
		                document.add(table2);
//		                document.add(addTableSpace);
//		                document.add(table3);

//						document.add(chunk);
//						document.add(chunk1);
//						document.add(chunk2);

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
