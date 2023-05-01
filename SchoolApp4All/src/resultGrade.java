

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


public class resultGrade {
	
	 public static void main(String[] args) {

	        try {

	              OutputStream file = new FileOutputStream(new File("D:\\yash\\result2.pdf"));
//		          Document document = new Document(PageSize.A4.rotate());
	              Document document = new Document(PageSize.A4);
//	              Document document = new Document();
		          PdfWriter.getInstance(document, file);
		          
		          Chunk chunk01 = new Chunk("General Education Institute, Dadar, Mumbai's \n");
					Font font01 = FontFactory.getFont("TIMES_ROMAN");
					font01.setStyle(Font.BOLD);
					font01.setSize(12);
//					font01.setColor(BaseColor.RED);
					chunk01.setFont(font01);
					Paragraph paragraph01 = new Paragraph();
					paragraph01.add(chunk01);
					paragraph01.setAlignment(Element.ALIGN_CENTER);
					paragraph01.setSpacingBefore(20);
					
					Chunk chunk02 = new Chunk("P.R. HIGH SCHOOL, BHIWANDI.");
					Font font02 = FontFactory.getFont("TIMES_ROMAN");
					font02.setStyle(Font.BOLD);
					font02.setSize(20);
					font02.setColor(BaseColor.RED);
					chunk02.setFont(font02);
					Paragraph paragraph02 = new Paragraph();
					paragraph02.add(chunk02);
					paragraph02.setAlignment(Element.ALIGN_CENTER);
					paragraph02.setSpacingBefore(4);
					
					Chunk chunk03 = new Chunk("97, Parshuram Vidyabhuvan, Brahmin Alley, Bhiwandi-421302");
					Font font03 = FontFactory.getFont("TIMES_ROMAN");
					font03.setStyle(Font.NORMAL);
					font03.setSize(10);
//					font03.setColor(BaseColor.RED);
					chunk03.setFont(font03);
					Paragraph paragraph03 = new Paragraph();
					paragraph03.add(chunk03);
					paragraph03.setAlignment(Element.ALIGN_CENTER);
					paragraph03.setSpacingBefore(0);
					
					Chunk chunk04 = new Chunk("S.S.C. Index No. : S-16.19.033");
					Font font04 = FontFactory.getFont("TIMES_ROMAN");
					font04.setStyle(Font.NORMAL);
					font04.setSize(10);
//					font04.setColor(BaseColor.RED);
					chunk04.setFont(font04);
					Paragraph paragraph04 = new Paragraph();
					paragraph04.add(chunk04);
					paragraph04.setAlignment(Element.ALIGN_CENTER);
//					paragraph04.setSpacingBefore(0);
//					paragraph04.setSpacingAfter(-85);
					
					Chunk chunk05 = new Chunk("U-DISE CODE - 27210201407");
					Font font05 = FontFactory.getFont("TIMES_ROMAN");
					font05.setStyle(Font.NORMAL);
					font05.setSize(10);
//					font05.setColor(BaseColor.RED);
					chunk05.setFont(font05);
					Paragraph paragraph05 = new Paragraph();
					paragraph05.add(chunk05);
					paragraph05.setAlignment(Element.ALIGN_CENTER);
//					paragraph05.setSpacingBefore(0);
//					paragraph05.setSpacingAfter(-85);
					
					Chunk chunk06 = new Chunk("FINAL RESULT 2015-2016");
					Font font06 = FontFactory.getFont("TIMES_ROMAN");
					font06.setStyle(Font.BOLD);
					font06.setSize(16);
//					font06.setColor(BaseColor.RED);
					chunk06.setFont(font06);
					Paragraph paragraph06 = new Paragraph();
					paragraph06.add(chunk06);
					paragraph06.setAlignment(Element.ALIGN_CENTER);
//					paragraph06.setSpacingBefore(0);
//					paragraph06.setSpacingAfter(-85);
					
					Chunk chunk07 = new Chunk("Name of the Student :");
					Font font07 = FontFactory.getFont("TIMES_ROMAN");
					font07.setStyle(Font.BOLD);
					font07.setSize(10);
//					font07.setColor(BaseColor.RED);
					chunk07.setFont(font07);
					Paragraph paragraph07 = new Paragraph();
					paragraph07.add(chunk07);
					paragraph07.setAlignment(Element.ALIGN_LEFT);
					paragraph07.setSpacingBefore(20);
//					paragraph07.setSpacingAfter(-85);
					
					Chunk chunk07b = new Chunk("                                                                                                                                                      ");
					Font font07b = FontFactory.getFont("TIMES_ROMAN");
					font07b.setStyle(Font.BOLD);
					font07b.setStyle(Font.UNDERLINE);
					font07b.setSize(10);
//					font07a.setColor(BaseColor.RED);
					chunk07b.setFont(font07b);
					Paragraph paragraph07b = new Paragraph();
					paragraph07b.add(chunk07b);
					paragraph07b.setAlignment(Element.ALIGN_LEFT);
					paragraph07b.setIndentationLeft(105);
					paragraph07b.setSpacingBefore(-16);
//					paragraph07b.setSpacingAfter(-85);
//					
					Chunk chunk07a = new Chunk("RASHMI PARIKH                                                                                                                         ");
					Font font07a = FontFactory.getFont("TIMES_ROMAN");
					font07a.setStyle(Font.BOLD);
//					font07a.setStyle(Font.UNDERLINE);
					font07a.setSize(10);
//					font07a.setColor(BaseColor.RED);
					chunk07a.setFont(font07a);
					Paragraph paragraph07a = new Paragraph();
					paragraph07a.add(chunk07a);
					paragraph07a.setAlignment(Element.ALIGN_LEFT);
					paragraph07a.setIndentationLeft(105);
					paragraph07a.setSpacingBefore(-15);
					paragraph07a.setSpacingAfter(-85);
					
					
					
					Chunk chunk08 = new Chunk("Progress :- ");
					Font font08 = FontFactory.getFont("TIMES_ROMAN");
					font08.setStyle(Font.BOLD);
//					font08.setStyle(Font.UNDERLINE);
					font08.setSize(10);
//					font08(BaseColor.RED);
					chunk08.setFont(font08);
//					Paragraph paragraph08 = new Paragraph();
//					paragraph08.add(chunk08);
//					paragraph08.setAlignment(Element.ALIGN_LEFT);
//					paragraph08.setIndentationLeft(105);
//					paragraph08.setSpacingBefore(-15);
//					paragraph08.setSpacingAfter(-85);
					
					Chunk chunk09 = new Chunk("Excellent");
					Font font09 = FontFactory.getFont("TIMES_ROMAN");
					font09.setStyle(Font.ITALIC);
//					font09.setStyle(Font.UNDERLINE);
					font09.setSize(10);
//					font09(BaseColor.RED);
					chunk09.setFont(font09);
//					Paragraph paragraph09 = new Paragraph();
//					paragraph09.add(chunk09);
//					paragraph09.setAlignment(Element.ALIGN_LEFT);
//					paragraph09.setIndentationLeft(105);
//					paragraph09.setSpacingBefore(-15);
//					paragraph09.setSpacingAfter(-85);
					
					Paragraph paragraph10 = new Paragraph();
					paragraph10.add(chunk08);
					paragraph10.add(chunk09);
					
					Chunk chunk11 = new Chunk("Hobbies / Participation In Activities:- ");
					Font font11 = FontFactory.getFont("TIMES_ROMAN");
					font11.setStyle(Font.BOLD);
//					font11.setStyle(Font.UNDERLINE);
					font11.setSize(10);
//					font11(BaseColor.RED);
					chunk11.setFont(font11);
					
					Chunk chunk12 = new Chunk("Playing Cricket");
					Font font12 = FontFactory.getFont("TIMES_ROMAN");
					font12.setStyle(Font.ITALIC);
//					font12.setStyle(Font.UNDERLINE);
					font12.setSize(10);
//					font12(BaseColor.RED);
					chunk12.setFont(font12);
					
					Paragraph paragraph12 = new Paragraph();
					paragraph12.add(chunk11);
					paragraph12.add(chunk12);
					
					Chunk chunk13 = new Chunk("Improvement required in:- ");
					Font font13 = FontFactory.getFont("TIMES_ROMAN");
					font13.setStyle(Font.BOLD);
//					font13.setStyle(Font.UNDERLINE);
					font13.setSize(10);
//					font13(BaseColor.RED);
					chunk13.setFont(font13);
					
					Chunk chunk14 = new Chunk("Should Study More");
					Font font14 = FontFactory.getFont("TIMES_ROMAN");
					font14.setStyle(Font.ITALIC);
//					font14.setStyle(Font.UNDERLINE);
					font14.setSize(10);
//					font14(BaseColor.RED);
					chunk14.setFont(font14);
					
					Paragraph paragraph14 = new Paragraph();
					paragraph14.add(chunk13);
					paragraph14.add(chunk14);
					
				
					
//					Chunk chunk31 = new Chunk("    year shall contain the details of the tax deducted and deposited for all the quarters of the financial year.");
//					Font font31 = FontFactory.getFont("TIMES_ROMAN");
//					font31.setStyle(Font.NORMAL);
//					font31.setSize(7);
////					font31.setColor(BaseColor.RED);
//					chunk31.setFont(font31);
//					Paragraph paragraph31 = new Paragraph();
//					paragraph31.add(chunk31);
//					paragraph31.setAlignment(Element.ALIGN_JUSTIFIED);
//					paragraph31.setSpacingBefore(-10);
		          
		          PdfPTable table=new PdfPTable(12);
				     table.setWidthPercentage(100);

		                     PdfPCell cell = new PdfPCell (new Paragraph ("STD.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell.setColspan (2);
					      cell.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell.setColspan (5);
//					      cell.setPadding (10.0f);
//					      cell.setBackgroundColor (new BaseColor (99, 99, 99));	
					      table.addCell(cell);
					      
					      PdfPCell cell1 = new PdfPCell (new Paragraph ("DIV.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell1.setColspan (1);
					      cell1.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell1.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell1.setPadding (10.0f);
//					      cell1.setBackgroundColor (new BaseColor (99, 99, 99));					               
					      table.addCell(cell1);
					      
					      PdfPCell cell1a = new PdfPCell (new Paragraph ("ROLL NO.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell1a.setColspan (1);
					      cell1a.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell1a.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell1a.setPadding (10.0f);
//					      cell1a.setBackgroundColor (new BaseColor (99, 99, 99));					               
					      table.addCell(cell1a);
					      
					      
					      PdfPCell cell2 = new PdfPCell (new Paragraph ("GR. NO.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell2.setColspan (2);
					      cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell2.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell2.setPadding (10.0f);
//					      cell2.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell2);
					      
					      
					      PdfPCell cell3 = new PdfPCell (new Paragraph ("BIRTH DATE", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell3.setColspan (3);
					      cell3.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell3.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell3.setPadding (10.0f);
//					      cell3.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell3);
					      
					      
					      PdfPCell cell4 = new PdfPCell (new Paragraph ("ATTENDANCE", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell4.setColspan (3);
					      cell4.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell4.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell4.setPadding (10.0f);
//					      cell4.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell4);
					      
					      
					      PdfPCell cell5 = new PdfPCell (new Paragraph ("V", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell5.setColspan (2);
					      cell5.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell5.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell5.setPadding (10.0f);
//					      cell5.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell5);
					      
					      
					      PdfPCell cell6 = new PdfPCell (new Paragraph ("A", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell6.setColspan (1);
					      cell6.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell6.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell6.setPadding (10.0f);
//					      cell6.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell6);
					      
					      
					      PdfPCell cell7 = new PdfPCell (new Paragraph ("01", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell7.setColspan (1);
					      cell7.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell7.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell7.setPadding (10.0f);
//					      cell7.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell7);
					      
					      
					      PdfPCell cell8 = new PdfPCell (new Paragraph ("0000001", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell8.setColspan (2);
//					      cell8.setRowspan (7);
					      cell8.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell8.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell8.setPadding (10.0f);
//					      cell8.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell8);
					      
					      
					      PdfPCell cell9 = new PdfPCell (new Paragraph ("01/01/2003", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell9.setColspan (3);
//					      cell9.setRowspan (7);
					      cell9.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell9.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell9.setPadding (10.0f);
//					      cell9.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell9);
					      
					      PdfPCell cell10 = new PdfPCell (new Paragraph ("40/50", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell10.setColspan (3);
//					      cell10.setRowspan (3);
					      cell10.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell10.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell10.setPadding (10.0f);
//					      cell10.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell10);
					      
					      PdfPCell cell11 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell11.setColspan (12);
//					      cell11.setRowspan (3);
					      cell11.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell11.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell11.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell11);
					      
					      PdfPCell cell12 = new PdfPCell (new Paragraph ("SR. \n"
					      		+ "NO.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));

					      cell12.setColspan (1);
//					      cell12.setRowspan (3);
					      cell12.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell12.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell10.setPadding (10.0f);
//					      cell12.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell12);
					      
					      PdfPCell cell13 = new PdfPCell (new Paragraph ("SUBJECTS", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell13.setColspan (2);
//					      cell13.setRowspan (3);
					      cell13.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell13.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell13.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell13);
					      
					     
					      
					      PdfPCell cell14 = new PdfPCell (new Paragraph ("FIRST TERM \n"
					      		+ "GRADE", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell14.setColspan (2);
					      cell14.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell14.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell14.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell14);
					      
					      
					      PdfPCell cell15 = new PdfPCell (new Paragraph ("SECOND TERM \n"
					      		+ "GRADE", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell15.setColspan (2);
					      cell15.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell15.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell15.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell15);
					      
					      
					      
					      PdfPCell cell16 = new PdfPCell (new Paragraph ("REMARKS", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell16.setColspan (5);
					      cell16.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell16.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell16.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell16);
					      
					      PdfPCell cell17 = new PdfPCell (new Paragraph ("1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell17.setColspan (1);
					      cell17.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell17.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell17.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell17);
					      
					      PdfPCell cell18 = new PdfPCell (new Paragraph ("ENGLISH", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell18.setColspan (2);
					      cell18.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell18.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (50.0f);
//					      cell18.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell18);
					      
					      PdfPCell cell19 = new PdfPCell (new Paragraph ("A-1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell19.setColspan (2);
					      cell19.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell19.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell19.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell19);
					      
					      PdfPCell cell20 = new PdfPCell (new Paragraph ("A-2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell20.setColspan (2);
					      cell20.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell20.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell20.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell20);
//					      
//					      
					      PdfPCell cell21 = new PdfPCell (paragraph10);
					      cell21.setColspan (5);
					      cell21.setRowspan (3);
					      cell21.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell21.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell11.setPadding (10.0f);
//					      cell21.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell21);
//					      
					      PdfPCell cell22 = new PdfPCell (new Paragraph ("2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell22.setColspan (1);
//					      cell22.setRowspan (5);
					      cell22.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell22.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell22.setPadding (10.0f);
//					      cell22.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell22);
					      
					      PdfPCell cell23 = new PdfPCell (new Paragraph ("HINDI/HINDI-SANS", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell23.setColspan (2);
//					      cell23.setRowspan (5);
					      cell23.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell23.setVerticalAlignment (Element.ALIGN_MIDDLE);					      
//					      cell23.setPadding (10.0f);
//					      cell23.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell23);
					      
					      PdfPCell cell24 = new PdfPCell (new Paragraph ("B-1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell24.setColspan (2);
//					      cell24.setRowspan (5);
					      cell24.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell24.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell24.setPadding (10.0f);
//					      cell24.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell24);
//					      
					      PdfPCell cell25 = new PdfPCell (new Paragraph ("B-2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell25.setColspan (2);
					      cell25.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell25.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell25.setPadding (10.0f);
//					      cell25.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell25);
//					      
					      PdfPCell cell26 = new PdfPCell (new Paragraph ("3", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell26.setColspan (1);
					      cell26.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell26.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell26.setPadding (10.0f);
//					      cell26.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell26);
//					      
					      PdfPCell cell27 = new PdfPCell (new Paragraph ("MARATHI", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell27.setColspan (2);
					      cell27.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell27.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell27.setPadding (10.0f);
//					      cell27.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell27);
					      
					      PdfPCell cell28 = new PdfPCell (new Paragraph ("A-1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell28.setColspan (2);
					      cell28.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell28.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell28.setPadding (10.0f);
//					      cell28.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell28);
					      
					      PdfPCell cell29 = new PdfPCell (new Paragraph ("A-2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell29.setColspan (2);
					      cell29.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell29.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell29.setPadding (10.0f);
//					      cell29.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell29);
//					      
					      PdfPCell cell30 = new PdfPCell (new Paragraph ("4", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell30.setColspan (1);
					      cell30.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell30.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell30.setPadding (10.0f);
//					      cell30.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell30);
					      
					      PdfPCell cell31 = new PdfPCell (new Paragraph ("MATHEMATICS", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell31.setColspan (2);
					      cell31.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell31.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell31.setPadding (10.0f);
//					      cell31.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell31);
					      
					      PdfPCell cell32 = new PdfPCell (new Paragraph ("B-1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell32.setColspan (2);
					      cell32.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell32.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell32.setPadding (10.0f);
//					      cell32.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell32);
//					   
					      PdfPCell cell33 = new PdfPCell (new Paragraph ("B-2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell33.setColspan (2);
					      cell33.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell33.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell33.setPadding (10.0f);
//					      cell33.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell33);
					      
					      PdfPCell cell34 = new PdfPCell (paragraph12);
					      cell34.setColspan (5);
					      cell34.setRowspan (3);
					      cell34.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell34.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell34.setPadding (10.0f);
//					      cell34.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell34);
//					      
					      PdfPCell cell35 = new PdfPCell (new Paragraph ("5", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell35.setColspan (1);
					      cell35.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell35.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell35.setPadding (10.0f);
//					      cell35.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell35);
					      
					      PdfPCell cell36 = new PdfPCell (new Paragraph ("SCIENCE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell36.setColspan (2);
					      cell36.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell36.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell36.setPadding (10.0f);
//					      cell36.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell36);
					      
					      PdfPCell cell37 = new PdfPCell (new Paragraph ("A-1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell37.setColspan (2);
					      cell37.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell37.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell37.setPadding (10.0f);
//					      cell37.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell37);
					      
					      PdfPCell cell38 = new PdfPCell (new Paragraph ("A-2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell38.setColspan (2);
					      cell38.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell38.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell38.setPadding (10.0f);
//					      cell38.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell38);
//					      
					      PdfPCell cell39 = new PdfPCell (new Paragraph ("6", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell39.setColspan (1);
					      cell39.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell39.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell39.setPadding (10.0f);
//					      cell39.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell39);
					      
					      PdfPCell cell40 = new PdfPCell (new Paragraph ("SOCIAL STUDIES", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell40.setColspan (2);
					      cell40.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell40.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell40.setPadding (10.0f);
//					      cell40.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell40);
					      
					      PdfPCell cell41 = new PdfPCell (new Paragraph ("B-1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell41.setColspan (2);
					      cell41.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell41.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell41.setPadding (10.0f);
//					      cell41.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell41);
					      
					      PdfPCell cell42 = new PdfPCell (new Paragraph ("B-2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell42.setColspan (2);
					      cell42.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell42.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell42.setPadding (10.0f);
//					      cell42.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell42);
					      
					      PdfPCell cell43 = new PdfPCell (new Paragraph ("7", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell43.setColspan (1);
					      cell43.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell43.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell43.setPadding (10.0f);
//					      cell43.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell43);
					      
					      PdfPCell cell44 = new PdfPCell (new Paragraph ("DRAWING", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell44.setColspan (2);
					      cell44.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell44.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell44.setPadding (10.0f);
//					      cell44.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell44);
					      
					      PdfPCell cell45 = new PdfPCell (new Paragraph ("A-1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell45.setColspan (2);
					      cell45.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell45.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell45.setPadding (10.0f);
//					      cell45.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell45);
					      
					      PdfPCell cell46 = new PdfPCell (new Paragraph ("A-2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell46.setColspan (2);
					      cell46.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell46.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell46.setPadding (10.0f);
//					      cell46.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell46);
//					      
					      PdfPCell cell47 = new PdfPCell (paragraph14);
					      cell47.setColspan (5);
					      cell47.setRowspan (3);
					      cell47.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell47.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell47.setPadding (10.0f);
//					      cell47.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell47);
//					      
					      PdfPCell cell48 = new PdfPCell (new Paragraph ("8", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell48.setColspan (1);
					      cell48.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell48.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell48.setPadding (10.0f);
//					      cell48.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell48);
					      
					      PdfPCell cell49 = new PdfPCell (new Paragraph ("COMPUTER WORK EXPERIENCE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell49.setColspan (2);
					      cell49.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell49.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell49.setPadding (10.0f);
//					      cell49.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell49);
					      
					      PdfPCell cell50 = new PdfPCell (new Paragraph ("A-1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell50.setColspan (2);
					      cell50.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell50.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell50.setPadding (10.0f);
//					      cell50.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell50);
					      
					      PdfPCell cell51 = new PdfPCell (new Paragraph ("A-2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell51.setColspan (2);
					      cell51.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell51.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell51.setPadding (10.0f);
//					      cell51.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell51);
//					      
					      PdfPCell cell52 = new PdfPCell (new Paragraph ("9", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell52.setColspan (1);
					      cell52.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell52.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell52.setPadding (10.0f);
//					      cell52.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell52);
					      
					      PdfPCell cell53 = new PdfPCell (new Paragraph ("PHYSICAL EDUCATION", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell53.setColspan (2);
					      cell53.setHorizontalAlignment (Element.ALIGN_LEFT);
					      cell53.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell53.setPadding (10.0f);
//					      cell53.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell53);
					      
					      PdfPCell cell54 = new PdfPCell (new Paragraph ("B-1", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell54.setColspan (2);
					      cell54.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell54.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell54.setPadding (10.0f);
//					      cell54.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell54);
					      
					      PdfPCell cell55 = new PdfPCell (new Paragraph ("B-2", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					      cell55.setColspan (2);
					      cell55.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell55.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell55.setPadding (10.0f);
//					      cell55.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell55);
//					      
					      PdfPCell cell56 = new PdfPCell (new Paragraph ("PERCENTAGE", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell56.setColspan (3);
					      cell56.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell56.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell56.setPadding (10.0f);
//					      cell56.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell56);
					      
					      PdfPCell cell57 = new PdfPCell (new Paragraph ("91% to 100%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell57.setColspan (1);
					      cell57.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell57.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell57.setPadding (10.0f);
//					      cell57.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell57);
					      
					      PdfPCell cell58 = new PdfPCell (new Paragraph ("81% to 90%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell58.setColspan (1);
					      cell58.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell58.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell58.setPadding (10.0f);
//					      cell58.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell58);
					      
					      PdfPCell cell59 = new PdfPCell (new Paragraph ("71% to 80%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell59.setColspan (1);
					      cell59.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell59.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell59.setPadding (10.0f);
//					      cell59.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell59);
					      
					      PdfPCell cell60 = new PdfPCell (new Paragraph ("61% to 70%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell60.setColspan (1);
					      cell60.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell60.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell60.setPadding (10.0f);
//					      cell60.setBackgroundColor (new BaseColor (110, 110, 110));					               
					      table.addCell(cell60);
					      
					      PdfPCell cell61 = new PdfPCell (new Paragraph ("51% to 60%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell61.setColspan (1);
					      cell61.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell61.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell61.setPadding (10.0f);
//					      cell61.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell61);
					      
					      PdfPCell cell62 = new PdfPCell (new Paragraph ("41% to 50%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell62.setColspan (1);
					      cell62.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell62.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell62.setPadding (10.0f);
//					      cell62.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell62);
					      
					      PdfPCell cell63 = new PdfPCell (new Paragraph ("33% to 40%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell63.setColspan (1);
					      cell63.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell63.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell63.setPadding (10.0f);
//					      cell63.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell63);
					      
					      PdfPCell cell64 = new PdfPCell (new Paragraph ("21% to 32%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell64.setColspan (1);
					      cell64.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell64.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell64.setPadding (10.0f);
//					      cell64.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell64);
					      
					      PdfPCell cell65 = new PdfPCell (new Paragraph ("20% or Less", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
					      cell65.setColspan (1);
					      cell65.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell65.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell65.setPadding (10.0f);
//					      cell65.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell65);
					      
					      PdfPCell cell66 = new PdfPCell (new Paragraph ("GRADE", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell66.setColspan (3);
					      cell66.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell66.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell66.setPadding (10.0f);
//					      cell66.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell66);
					      
					      PdfPCell cell67 = new PdfPCell (new Paragraph ("A-1", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell67.setColspan (1);
					      cell67.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell67.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell67.setPadding (10.0f);
//					      cell67.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell67);
					      
					      PdfPCell cell68 = new PdfPCell (new Paragraph ("A-2", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell68.setColspan (1);
					      cell68.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell68.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell68.setPadding (10.0f);
//					      cell68.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell68);
					      
					      PdfPCell cell69 = new PdfPCell (new Paragraph ("B-1", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell69.setColspan (1);
					      cell69.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell69.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell69.setPadding (10.0f);
//					      cell69.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell69);
					      
					      PdfPCell cell70 = new PdfPCell (new Paragraph ("B-2", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell70.setColspan (1);
					      cell70.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell70.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell70.setPadding (10.0f);
//					      cell70.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell70);
					      
					      PdfPCell cell71 = new PdfPCell (new Paragraph ("C-1", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell71.setColspan (1);
					      cell71.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell71.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell71.setPadding (10.0f);
//					      cell71.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell71);
					      
					      PdfPCell cell72 = new PdfPCell (new Paragraph ("C-2", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell72.setColspan (1);
					      cell72.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell72.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell72.setPadding (10.0f);
//					      cell72.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell72);
					      
					      PdfPCell cell73 = new PdfPCell (new Paragraph ("D", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell73.setColspan (1);
					      cell73.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell73.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell73.setPadding (10.0f);
//					      cell73.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell73);
					      
					      PdfPCell cell74 = new PdfPCell (new Paragraph ("E-1", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell74.setColspan (1);
					      cell74.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell74.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell74.setPadding (10.0f);
//					      cell74.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell74);
					      
					      PdfPCell cell75 = new PdfPCell (new Paragraph ("E-2", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
					      cell75.setColspan (1);
					      cell75.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell75.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell75.setPadding (10.0f);
//					      cell75.setBackgroundColor (new BaseColor (140, 221, 8));					               
					      table.addCell(cell75);
					      
//					      PdfPCell cell76 = new PdfPCell (new Paragraph ("B-1", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
//					      cell76.setColspan (1);
//					      cell76.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell76.setVerticalAlignment (Element.ALIGN_MIDDLE);
////					      cell76.setPadding (10.0f);
////					      cell76.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell76);
//					      
//					      PdfPCell cell77 = new PdfPCell (new Paragraph ("B-1", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
//					      cell77.setColspan (1);
//					      cell77.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell77.setVerticalAlignment (Element.ALIGN_MIDDLE);
////					      cell77.setPadding (10.0f);
////					      cell77.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell77);
					      
					      
					      Chunk chunk101 = new Chunk("Passed & Promoted to Std. :");
//					      chunk101.setUnderline(0.1f, -2f);
							Font font101 = FontFactory.getFont("TIMES_ROMAN");
//							font101.setStyle(Font.UNDERLINE);
							font101.setStyle(Font.BOLD);
							font101.setSize(10);
//							font101.setColor(BaseColor.RED);
							chunk101.setFont(font01);
							Paragraph paragraph101 = new Paragraph();
							paragraph101.add(chunk101);
							paragraph101.setAlignment(Element.ALIGN_LEFT);
							paragraph101.setSpacingBefore(20);
							
							Chunk chunk101a = new Chunk("VI-A");
							Font font101a = FontFactory.getFont("TIMES_ROMAN");
							font101a.setStyle(Font.BOLD);
							font101a.setSize(10);
//							font101a.setColor(BaseColor.RED);
							chunk101a.setFont(font01);
							Paragraph paragraph101a = new Paragraph();
							paragraph101a.add(chunk101a);
							paragraph101a.setIndentationLeft(165);
							paragraph101a.setAlignment(Element.ALIGN_LEFT);
							paragraph101a.setSpacingBefore(-16);
							
//							Chunk chunk102 = new Chunk("Conduct : Good                                                                                                                  ");
//							Font font102 = FontFactory.getFont("TIMES_ROMAN");
//							font102.setStyle(Font.BOLD);
////							font102.setStyle(Font.UNDERLINE);
//							font102.setSize(10);
////							font102.setColor(BaseColor.RED);
//							chunk102.setFont(font102);
//							Paragraph paragraph102 = new Paragraph();
//							paragraph102.add(chunk102);
//							paragraph102.setAlignment(Element.ALIGN_LEFT);
//							paragraph102.setSpacingBefore(4);
//							
//							Chunk chunk102a = new Chunk("                                                                                                                               Remark : No Due");
//							Font font102a = FontFactory.getFont("TIMES_ROMAN");
//							font102a.setStyle(Font.BOLD);
//							font102a.setSize(10);
////							font102a.setColor(BaseColor.RED);
//							chunk102a.setFont(font102a);
//							Paragraph paragraph102a = new Paragraph();
//							paragraph102a.add(chunk102a);
//							paragraph102a.setAlignment(Element.ALIGN_LEFT);
//							paragraph102a.setSpacingBefore(-15);
//							
//							Chunk chunk102b = new Chunk("Result : Pass");
//							Font font102b = FontFactory.getFont("TIMES_ROMAN");
//							font102b.setStyle(Font.BOLD);
//							font102b.setSize(10);
////							font102b.setColor(BaseColor.RED);
//							chunk102b.setFont(font102b);
//							Paragraph paragraph102b = new Paragraph();
//							paragraph102b.add(chunk102b);
//							paragraph102b.setAlignment(Element.ALIGN_LEFT);
//							paragraph102b.setSpacingBefore(4);
//							
//							Chunk chunk103 = new Chunk(" ");
//							Font font103 = FontFactory.getFont("TIMES_ROMAN");
//							font103.setStyle(Font.NORMAL);
//							font103.setSize(10);
////							font103.setColor(BaseColor.RED);
//							chunk103.setFont(font103);
//							Paragraph paragraph103 = new Paragraph();
//							paragraph103.add(chunk103);
//							paragraph103.setAlignment(Element.ALIGN_LEFT);
//							paragraph103.setSpacingBefore(0);
//							
							Chunk chunk104 = new Chunk("CLASS TEACHER                                                                                             PRINCIPAL");
							Font font104 = FontFactory.getFont("TIMES_ROMAN");
							font104.setStyle(Font.BOLD);
							font104.setSize(12);
							font104.setColor(BaseColor.RED);
							chunk104.setFont(font104);
							Paragraph paragraph104 = new Paragraph();
							paragraph104.add(chunk104);
							paragraph104.setAlignment(Element.ALIGN_LEFT);
							paragraph104.setSpacingBefore(60);
							paragraph104.setSpacingAfter(-85);
					      
//					      
//					      document.newPage();
//					      table.setSpacingBefore(20.0f);       // Space Before table starts, like margin-top in CSS
//					      table.setSpacingAfter(150.0f);  
////					      
//					      Paragraph addTableSpace = new Paragraph();
//					      addTableSpace.setSpacingAfter(10);
////					      
//					      PdfPTable table1=new PdfPTable(12);
//					      
//					      table1.setWidthPercentage(100);
////					      
//					      
//					      PdfPCell cell201 = new PdfPCell (new Paragraph ("Sl.No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell201.setColspan (6);
////					      cell201.setRowspan (2);
//					      cell201.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell201.setVerticalAlignment (Element.ALIGN_MIDDLE);
////					      cell201.setPadding (10.0f);
////					      cell201.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell201);
////					      
//					      PdfPCell cell202 = new PdfPCell (new Paragraph ("Tax Deposited in )", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell202.setColspan (3);
////					      cell202.setRowspan (2);
//					      cell202.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell202.setVerticalAlignment (Element.ALIGN_MIDDLE);
////					      cell202.setPadding (10.0f);
////					      cell202.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell202);
////					      
//					      PdfPCell cell203 = new PdfPCell (new Paragraph ("Challan Identification ", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell203.setColspan (3);
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
					      
//					      PdfPCell cell208 = new PdfPCell (new Paragraph ("6", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell208.setColspan (1);
////					      cell208.setRowspan (2);
//					      cell208.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell208.setVerticalAlignment (Element.ALIGN_MIDDLE);
////					      cell208.setPadding (10.0f);
////					      cell208.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell208);
//					      
//					      PdfPCell cell209 = new PdfPCell (new Paragraph ("211.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell209.setColspan (3);
////					      cell209.setRowspan (2);
//					      cell209.setHorizontalAlignment (Element.ALIGN_RIGHT);
////					      cell209.setPadding (10.0f);
////					      cell209.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell209);
//					      
//					      PdfPCell cell210 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell210.setColspan (2);
////					      cell210.setRowspan (2);
//					      cell210.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell210.setPadding (10.0f);
////					      cell210.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell210);
//					      
//					      PdfPCell cell211 = new PdfPCell (new Paragraph ("20-01-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell211.setColspan (2);
////					      cell211.setRowspan (2);
//					      cell211.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell211.setPadding (10.0f);
////					      cell211.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell211);
//					      
//					      PdfPCell cell212 = new PdfPCell (new Paragraph ("42082", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell212.setColspan (2);
////					      cell212.setRowspan (2);
//					      cell212.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell212.setPadding (10.0f);
////					      cell212.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell212);
//					      
//					      PdfPCell cell213 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell213.setColspan (2);
////					      cell213.setRowspan (9);
//					      cell213.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell213.setPadding (10.0f);
////					      cell213.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell213);
//					      
//					      PdfPCell cell214 = new PdfPCell (new Paragraph ("7", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell214.setColspan (1);
////					      cell214.setRowspan (9);
//					      cell214.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell214.setPadding (10.0f);
////					      cell214.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell214);
//					      
//					      PdfPCell cell215 = new PdfPCell (new Paragraph ("317.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell215.setColspan (3);
////					      cell215.setRowspan (9);
//					      cell215.setHorizontalAlignment (Element.ALIGN_RIGHT);
////					      cell215.setPadding (10.0f);
////					      cell215.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell215);
//					      
//					      PdfPCell cell216 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell216.setColspan (2);
////					      cell216.setRowspan (9);
//					      cell216.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell216.setPadding (10.0f);
////					      cell216.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell216);
//					      
//					      PdfPCell cell217 = new PdfPCell (new Paragraph ("18-02-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell217.setColspan (2);
////					      cell217.setRowspan (9);
//					      cell217.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell217.setPadding (10.0f);
////					      cell217.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell217);
//					      
//					      PdfPCell cell218 = new PdfPCell (new Paragraph ("43109", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell218.setColspan (2);
////					      cell218.setRowspan (4);
//					      cell218.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell218.setPadding (10.0f);
////					      cell218.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell218);
//					      
//					      PdfPCell cell219 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell219.setColspan (2);
////					      cell219.setRowspan (9);
//					      cell219.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell219.setPadding (10.0f);
////					      cell219.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell219);
//					      
//					      PdfPCell cell220 = new PdfPCell (new Paragraph ("8", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell220.setColspan (1);
////					      cell220.setRowspan (9);
//					      cell220.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell220.setPadding (10.0f);
////					      cell220.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell220);
//					      
//					      PdfPCell cell221 = new PdfPCell (new Paragraph ("181.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell221.setColspan (3);
////					      cell221.setRowspan (9);
//					      cell221.setHorizontalAlignment (Element.ALIGN_RIGHT);
////					      cell221.setPadding (10.0f);
////					      cell221.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell221);
//					      
//					      PdfPCell cell222 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell222.setColspan (2);
////					      cell222.setRowspan (9);
//					      cell222.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell222.setPadding (10.0f);
////					      cell222.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell222);
//					      
//					      PdfPCell cell223 = new PdfPCell (new Paragraph ("18-03-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell223.setColspan (2);
////					      cell223.setRowspan (9);
//					      cell223.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell223.setPadding (10.0f);
////					      cell223.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell223);
//					      
//					      PdfPCell cell224 = new PdfPCell (new Paragraph ("48246", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell224.setColspan (2);
////					      cell224.setRowspan (9);
//					      cell224.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell224.setPadding (10.0f);
////					      cell224.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell224);
//					      
//					      PdfPCell cell225 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell225.setColspan (2);
////					      cell225.setRowspan (9);
//					      cell225.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell225.setPadding (10.0f);
////					      cell225.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell225);
//					      
//					      PdfPCell cell226 = new PdfPCell (new Paragraph ("9", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell226.setColspan (1);
////					      cell226.setRowspan (9);
//					      cell226.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell226.setPadding (10.0f);
////					      cell226.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell226);
//					      
//					      PdfPCell cell227 = new PdfPCell (new Paragraph ("1013.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell227.setColspan (3);
////					      cell227.setRowspan (9);
//					      cell227.setHorizontalAlignment (Element.ALIGN_RIGHT);
////					      cell227.setPadding (10.0f);
////					      cell227.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell227);
//					      
//					      PdfPCell cell228 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell228.setColspan (2);
////					      cell228.setRowspan (9);
//					      cell228.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell228.setPadding (10.0f);
////					      cell228.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell228);
//					      
//					      PdfPCell cell229 = new PdfPCell (new Paragraph ("15-04-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell229.setColspan (2);
////					      cell229.setRowspan (2);
//					      cell229.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell229.setPadding (10.0f);
////					      cell229.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell229);
//					      
//					      PdfPCell cell230 = new PdfPCell (new Paragraph ("42525", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell230.setColspan (2);
////					      cell230.setRowspan (9);
//					      cell230.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell230.setPadding (10.0f);
////					      cell230.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell230);
//					      
//					      PdfPCell cell231 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell231.setColspan (2);
////					      cell231.setRowspan (9);
//					      cell231.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell231.setPadding (10.0f);
////					      cell231.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell231);
//					      
//					      PdfPCell cell232 = new PdfPCell (new Paragraph ("10", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell232.setColspan (1);
////					      cell232.setRowspan (9);
//					      cell232.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell232.setPadding (10.0f);
////					      cell232.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell232);
//					      
//					      PdfPCell cell233 = new PdfPCell (new Paragraph ("90.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell233.setColspan (3);
////					      cell233.setRowspan (9);
//					      cell233.setHorizontalAlignment (Element.ALIGN_RIGHT);
////					      cell233.setPadding (10.0f);
////					      cell233.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell233);
//					      
//					      PdfPCell cell234 = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell234.setColspan (2);
////					      cell234.setRowspan (9);
//					      cell234.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell234.setPadding (10.0f);
////					      cell234.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell234);
//					      
//					      PdfPCell cell235 = new PdfPCell (new Paragraph ("02-05-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell235.setColspan (2);
////					      cell235.setRowspan (9);
//					      cell235.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell235.setPadding (10.0f);
////					      cell235.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell235);
//					      
//					      PdfPCell cell236 = new PdfPCell (new Paragraph ("43861", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell236.setColspan (2);
////					      cell236.setRowspan (9);
//					      cell236.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell236.setPadding (10.0f);
////					      cell236.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell236);
//					      
//					      PdfPCell cell237 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell237.setColspan (2);
////					      cell237.setRowspan (9);
//					      cell237.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell237.setPadding (10.0f);
////					      cell237.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell237);
//					      
//					      PdfPCell cell232a = new PdfPCell (new Paragraph ("11", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell232a.setColspan (1);
////					      cell232a.setRowspan (9);
//					      cell232a.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell232a.setPadding (10.0f);
////					      cell232a.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell232a);
//					      
//					      PdfPCell cell233a = new PdfPCell (new Paragraph ("90.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell233a.setColspan (3);
////					      cell233a.setRowspan (9);
//					      cell233a.setHorizontalAlignment (Element.ALIGN_RIGHT);
////					      cell233a.setPadding (10.0f);
////					      cell233a.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell233a);
//					      
//					      PdfPCell cell234a = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell234a.setColspan (2);
////					      cell234a.setRowspan (9);
//					      cell234a.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell234a.setPadding (10.0f);
////					      cell234a.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell234a);
//					      
//					      PdfPCell cell235a = new PdfPCell (new Paragraph ("02-05-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell235a.setColspan (2);
////					      cell235a.setRowspan (9);
//					      cell235a.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell235a.setPadding (10.0f);
////					      cell235a.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell235a);
//					      
//					      PdfPCell cell236a = new PdfPCell (new Paragraph ("43861", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell236a.setColspan (2);
////					      cell236a.setRowspan (9);
//					      cell236a.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell236a.setPadding (10.0f);
////					      cell236a.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell236a);
//					      
//					      PdfPCell cell237a = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell237a.setColspan (2);
////					      cell237a.setRowspan (9);
//					      cell237a.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell237a.setPadding (10.0f);
////					      cell237a.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell237a);
//					      
//					      PdfPCell cell232b = new PdfPCell (new Paragraph ("12", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell232b.setColspan (1);
////					      cell232b.setRowspan (9);
//					      cell232b.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell232b.setPadding (10.0f);
////					      cell232b.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell232b);
//					      
//					      PdfPCell cell233b = new PdfPCell (new Paragraph ("90.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell233b.setColspan (3);
////					      cell233b.setRowspan (9);
//					      cell233b.setHorizontalAlignment (Element.ALIGN_RIGHT);
////					      cell233b.setPadding (10.0f);
////					      cell233b.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell233b);
//					      
//					      PdfPCell cell234b = new PdfPCell (new Paragraph ("0510308", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell234b.setColspan (2);
////					      cell234b.setRowspan (9);
//					      cell234b.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell234b.setPadding (10.0f);
////					      cell234b.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell234b);
//					      
//					      PdfPCell cell235b = new PdfPCell (new Paragraph ("02-05-2014", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell235b.setColspan (2);
////					      cell235b.setRowspan (9);
//					      cell235b.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell235b.setPadding (10.0f);
////					      cell235b.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell235b);
//					      
//					      PdfPCell cell236b = new PdfPCell (new Paragraph ("43861", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell236b.setColspan (2);
////					      cell236b.setRowspan (9);
//					      cell236b.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell236b.setPadding (10.0f);
////					      cell236b.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell236b);
//					      
//					      PdfPCell cell237b = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//					      cell237b.setColspan (2);
////					      cell237b.setRowspan (9);
//					      cell237b.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell237b.setPadding (10.0f);
////					      cell237b.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell237b);
//					      
//					      PdfPCell cell238 = new PdfPCell (new Paragraph ("Total (Rs.)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell238.setColspan (1);
////					      cell238.setRowspan (9);
//					      cell238.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell238.setPadding (10.0f);
////					      cell238.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell238);
//					      
//					      PdfPCell cell239 = new PdfPCell (new Paragraph ("2964.00", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell239.setColspan (3);
////					      cell239.setRowspan (9);
//					      cell239.setHorizontalAlignment (Element.ALIGN_RIGHT);
////					      cell239.setPadding (10.0f);
////					      cell239.setBackgroundColor (new BaseColor (140, 221, 8));					               
//					      table.addCell(cell239);
//					      
//					      PdfPCell cell240 = new PdfPCell (new Paragraph (" ", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell240.setColspan (8);
////					      cell240.setRowspan (9);
//					      cell240.setHorizontalAlignment (Element.ALIGN_CENTER);
////					      cell240.setPadding (10.0f);
//					      cell240.setBackgroundColor (new BaseColor (110, 110, 110));					               
//					      table.addCell(cell240);
//					      
//					      PdfPCell cell241 = new PdfPCell (new Paragraph ("Verification", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
//					      cell241.setColspan (12);
////					      cell241.setRowspan (9);
//					      cell241.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell241.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell241.setPadding (5.0f);
////					      cell241.setBackgroundColor (new BaseColor (100, 100, 100));					               
//					      table.addCell(cell241);
//					      
//					      
//					      PdfPCell cell242 = new PdfPCell (new Paragraph ("1. RASHMI DHIRAJLAL PARIKH, son / daughter of DHIRAJLAL PARIKH working in the capacity of PROPRIETOR (designation) do hereby certify that a sum of " +
//					      		"Rs.2964.00 [Rs. Two Thousand Nine Hundred and Sixty Four Only (in words)] has been deducted and a sum of Rs.2964.00 [Rs. Two Thousand Nine Hundred and " +
//					      		"Sixty Four Only] has been deposited to the credit of the Central Government. I further certify that the information given above is true, complete and correct and is " +
//					      		"based on the books of account, documents, TDS statements, TDS deposited and other available records.", FontFactory.getFont(FontFactory.TIMES_BOLD, 7)));
//					      cell242.setColspan (12);
////					      cell242.setRowspan (9);
//					      cell242.setHorizontalAlignment (Element.ALIGN_UNDEFINED);
//					      cell242.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell242.setPadding (5.0f);
////					      cell242.setBackgroundColor (new BaseColor (100, 100, 100));					               
//					      table.addCell(cell242);
//					      
//					      
//					      PdfPCell cell243 = new PdfPCell (new Paragraph ("Place", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell243.setColspan (2);
////					      cell243.setRowspan (9);
//					      cell243.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell243.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell243.setPadding (3.0f);
////					      cell243.setBackgroundColor (new BaseColor (100, 100, 100));					               
//					      table.addCell(cell243);
//					      
//					      PdfPCell cell244 = new PdfPCell (new Paragraph ("MUMBAI", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell244.setColspan (4);
////					      cell244.setRowspan (9);
//					      cell244.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell244.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell244.setPadding (3.0f);
////					      cell244.setBackgroundColor (new BaseColor (100, 100, 100));					               
//					      table.addCell(cell244);
//					      
//					      PdfPCell cell245 = new PdfPCell (new Paragraph ("(Signature of person responsible for deduction of Tax)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell245.setColspan (6);
//					      cell245.setRowspan (2);
//					      cell245.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell245.setVerticalAlignment (Element.ALIGN_BOTTOM);
//					      cell245.setPadding (3.0f);
////					      cell245.setBackgroundColor (new BaseColor (100, 100, 100));					               
//					      table.addCell(cell245);
//					      
//					      PdfPCell cell246 = new PdfPCell (new Paragraph ("Date", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell246.setColspan (2);
////					      cell246.setRowspan (9);
//					      cell246.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell246.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell246.setPadding (3.0f);
////					      cell246.setBackgroundColor (new BaseColor (100, 100, 100));					               
//					      table.addCell(cell246);
//					      
//					      PdfPCell cell247 = new PdfPCell (new Paragraph ("15-May-2014", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell247.setColspan (4);
////					      cell247.setRowspan (9);
//					      cell247.setHorizontalAlignment (Element.ALIGN_CENTER);
//					      cell247.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell247.setPadding (3.0f);
////					      cell247.setBackgroundColor (new BaseColor (100, 100, 100));					               
//					      table.addCell(cell247);
//					      
//					      PdfPCell cell248 = new PdfPCell (new Paragraph ("Designation: PROPRIETOR", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell248.setColspan (6);
////					      cell248.setRowspan (9);
//					      cell248.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell248.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell248.setPadding (3.0f);
////					      cell248.setBackgroundColor (new BaseColor (100, 100, 100));					               
//					      table.addCell(cell248);
//					      
//					      PdfPCell cell249 = new PdfPCell (new Paragraph ("Full Name: RASHMI DHIRAJLAL PARIKH", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//					      cell249.setColspan (6);
////					      cell249.setRowspan (9);
//					      cell249.setHorizontalAlignment (Element.ALIGN_LEFT);
//					      cell249.setVerticalAlignment (Element.ALIGN_MIDDLE);
//					      cell249.setPadding (3.0f);
////					      cell249.setBackgroundColor (new BaseColor (100, 100, 100));					               
//					      table.addCell(cell249);
					      
					      
//					      Chunk chunkHeader = new Chunk("Notes: \n");
//							Font font = FontFactory.getFont("TIMES_ROMAN");
//							font.setStyle(Font.BOLD);
//							font.setSize(10);
////							font.setColor(BaseColor.RED);
//							chunkHeader.setFont(font);
//							Paragraph paragraph = new Paragraph();
//							paragraph.add(chunkHeader);
//							paragraph.setAlignment(Element.ALIGN_LEFT);
//							paragraph.setSpacingBefore(20);
//							
//							Chunk chunk2 = new Chunk("1. Part B (Annexure) of the certificate in Form No.16 shall be issued by employer.");
//							Font font2 = FontFactory.getFont("TIMES_ROMAN");
//							font2.setStyle(Font.NORMAL);
//							font2.setSize(7);
////							font2.setColor(BaseColor.RED);
//							chunk2.setFont(font2);
//							Paragraph paragraph2 = new Paragraph();
//							paragraph2.add(chunk2);
//							paragraph2.setAlignment(Element.ALIGN_JUSTIFIED);
//							paragraph2.setSpacingBefore(-8);
//							
//							Chunk chunk3 = new Chunk("2. If an assessee is employed under one employer during the year, Part 'A' of the certificate in Form No.16 issued for the quarter ending on 31st March of the financial");
//							Font font3 = FontFactory.getFont("TIMES_ROMAN");
//							font3.setStyle(Font.NORMAL);
//							font3.setSize(7);
////							font3.setColor(BaseColor.RED);
//							chunk3.setFont(font3);
//							Paragraph paragraph3 = new Paragraph();
//							paragraph3.add(chunk3);
//							paragraph3.setAlignment(Element.ALIGN_JUSTIFIED);
//							paragraph3.setSpacingBefore(-8);
//							
//							Chunk chunk31 = new Chunk("    year shall contain the details of the tax deducted and deposited for all the quarters of the financial year.");
//							Font font31 = FontFactory.getFont("TIMES_ROMAN");
//							font31.setStyle(Font.NORMAL);
//							font31.setSize(7);
////							font31.setColor(BaseColor.RED);
//							chunk31.setFont(font31);
//							Paragraph paragraph31 = new Paragraph();
//							paragraph31.add(chunk31);
//							paragraph31.setAlignment(Element.ALIGN_JUSTIFIED);
//							paragraph31.setSpacingBefore(-10);
//							
//							Chunk chunk4 = new Chunk("3. If an assessee is employed under more than one employer during the year, each of the employers shall issue  Part A  of the certificate in Form  No.16  pertaining  to");
//							Font font4 = FontFactory.getFont("TIMES_ROMAN");
//							font4.setStyle(Font.NORMAL);
//							font4.setSize(7);
////							font4.setColor(BaseColor.RED);
//							chunk4.setFont(font4);
//							Paragraph paragraph4 = new Paragraph();
//							paragraph4.setAlignment(Element.ALIGN_JUSTIFIED);
//							paragraph4.add(chunk4);
//							paragraph4.setSpacingBefore(-8);
//							
//
//							Chunk chunk41 = new Chunk("    the period for  which such  assessee was employed with each of the employers. Part B (Annexure) of the certificate in Form No.16 may be issued by each of the");
//							Font font41 = FontFactory.getFont("TIMES_ROMAN");
//							font41.setStyle(Font.NORMAL);
//							font41.setSize(7);
////							font41.setColor(BaseColor.RED);
//							chunk41.setFont(font41);
//							Paragraph paragraph41 = new Paragraph();
//							paragraph41.add(chunk41);
//							paragraph41.setAlignment(Element.ALIGN_JUSTIFIED);
//							paragraph41.setSpacingBefore(-10);
//							
//							Chunk chunk42 = new Chunk("    employers or the last employer at the option of the assessee. ");
//							Font font42 = FontFactory.getFont("TIMES_ROMAN");
//							font42.setStyle(Font.NORMAL);
//							font42.setSize(7);
////							font42.setColor(BaseColor.RED);
//							chunk42.setFont(font42);
//							Paragraph paragraph42 = new Paragraph();
//							paragraph42.add(chunk42);
//							paragraph42.setAlignment(Element.ALIGN_JUSTIFIED);
//							paragraph42.setSpacingBefore(-10);
//							
//							
//							
//							Chunk chunk5 = new Chunk("4. To update PAN details in Income Tax Department database, apply for 'PAN change request' through NSDL or UTITSL. ");
//							Font font5 = FontFactory.getFont("TIMES_ROMAN");
//							font5.setStyle(Font.NORMAL);
//							font5.setSize(7);
////							font5.setColor(BaseColor.RED);
//							chunk5.setFont(font5);
//							Paragraph paragraph5 = new Paragraph();
//							paragraph5.add(chunk5);
//							paragraph5.setAlignment(Element.ALIGN_JUSTIFIED);
//							paragraph5.setSpacingBefore(-8);
//							paragraph5.setSpacingAfter(5);
//							
//							Chunk chunk6 = new Chunk("Legend used in Form 16");
//							chunk6.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
//							Font font6 = FontFactory.getFont("TIMES_ROMAN");
////							font6.setStyle(Font.UNDERLINE);
//							font6.setStyle(Font.BOLD);
//							font6.setSize(7);
////							font6.setColor(BaseColor.RED);
//							chunk6.setFont(font6);
//							Paragraph paragraph6 = new Paragraph();
//							paragraph6.add(chunk6);
//							paragraph6.setAlignment(Element.ALIGN_LEFT);
//							paragraph6.setSpacingBefore(10);
//							paragraph6.setSpacingAfter(5);
//							
//							Chunk chunk7 = new Chunk(" * Status of matching with OLTAS");
//							Font font7 = FontFactory.getFont("TIMES_ROMAN");
//							font7.setStyle(Font.BOLD);
//							font7.setSize(7);
////							font6.setColor(BaseColor.RED);
//							chunk7.setFont(font7);
//							Paragraph paragraph7 = new Paragraph();
//							paragraph7.add(chunk7);
//							paragraph7.setAlignment(Element.ALIGN_LEFT);
//							paragraph7.setSpacingBefore(5);
//							paragraph7.setSpacingAfter(5);
					      
					      
							
							
							

						      document.newPage();
						      table.setSpacingBefore(100.0f);       // Space Before table starts, like margin-top in CSS
						      table.setSpacingAfter(1.0f);  
						      
						     
						      
//						      PdfPTable table2=new PdfPTable(12);
//						      
//						      table2.setWidthPercentage(100);
//						      
////						      document.newPage();
//						      table2.setSpacingBefore(150.0f);       // Space Before table starts, like margin-top in CSS
//						      table2.setSpacingAfter(1.0f); 
//						      
//						      PdfPCell cell301 = new PdfPCell (new Paragraph ("CLASS", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell301.setColspan (6);
////						      cell301.setRowspan (2);
//						      cell301.setHorizontalAlignment (Element.ALIGN_CENTER);
//						      cell301.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell301.setPadding (10.0f);
//						      cell301.setBackgroundColor (new BaseColor (110, 110, 110));					               
//						      table2.addCell(cell301);
//						      
//						      PdfPCell cell302 = new PdfPCell (new Paragraph ("GRADES", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell302.setColspan (3);
////						      cell302.setRowspan (2);
//						      cell302.setHorizontalAlignment (Element.ALIGN_CENTER);
//						      cell302.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell302.setPadding (10.0f);
//						      cell302.setBackgroundColor (new BaseColor (110, 110, 110));					               
//						      table2.addCell(cell302);
//						      
//						      PdfPCell cell303 = new PdfPCell (new Paragraph ("REMARKS", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell303.setColspan (3);
////						      cell303.setRowspan (2);
//						      cell303.setHorizontalAlignment (Element.ALIGN_CENTER);
//						      cell303.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell303.setPadding (10.0f);
//						      cell303.setBackgroundColor (new BaseColor (110, 110, 110));					               
//						      table2.addCell(cell303);
//					     
//						      PdfPCell cell304 = new PdfPCell (new Paragraph ("DISTINCTION              - 75 & ABOVE \n"
//						      		+ "FIRST CLASS               - 60% - 74% \n"
//						      		+ "SECOND CLASS          - 50% - 59% \n"
//						      		+ "PASS CLASS                - 35% - 49% \n"
//						      		+ "FAIL                              - BELOW 35%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//						      cell304.setColspan (6);
////						      cell304.setRowspan (2);
//						      cell304.setHorizontalAlignment (Element.ALIGN_LEFT);
////						      cell304.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell304.setPadding (10.0f);
////						      cell304.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table2.addCell(cell304);
//						      
//						      PdfPCell cell305 = new PdfPCell (new Paragraph ("A   -   60% - ABOVE \n"
//						      		+ "B   -   50% - 59% \n"
//						      		+ "C   -   35% - 49% \n"
//						      		+ "D   -   BELOW 35%", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//						      cell305.setColspan (3);
////						      cell305.setRowspan (2);
//						      cell305.setHorizontalAlignment (Element.ALIGN_LEFT);
////						      cell305.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell305.setPadding (10.0f);
////						      cell305.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table2.addCell(cell305);
//						      
//						      PdfPCell cell306 = new PdfPCell (new Paragraph ("MG   -   MEDICAL GROUNDS \n"
//						      		+ "CC   -   COPY CASE \n"
//						      		+ "AB   -   ABSENT", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//						      cell306.setColspan (3);
////						      cell306.setRowspan (2);
//						      cell306.setHorizontalAlignment (Element.ALIGN_LEFT);
////						      cell306.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell306.setPadding (10.0f);
////						      cell306.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table2.addCell(cell306);
						      
//						      PdfPCell cell307 = new PdfPCell (new Paragraph ("P", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell307.setColspan (6);
////						      cell307.setRowspan (2);
//						      cell307.setHorizontalAlignment (Element.ALIGN_CENTER);
//						      cell307.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell307.setPadding (10.0f);
////						      cell307.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table2.addCell(cell307);
//						      
//						      PdfPCell cell308 = new PdfPCell (new Paragraph ("Provisional", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//						      cell308.setColspan (3);
////						      cell308.setRowspan (2);
//						      cell308.setHorizontalAlignment (Element.ALIGN_CENTER);
//						      cell308.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell308.setPadding (10.0f);
////						      cell308.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table2.addCell(cell308);
//						      
//						      PdfPCell cell309 = new PdfPCell (new Paragraph ("Provisional tax credit is effected only for TDS/TCS Statement filed by Government deductors. 'P' status will be changed to Final (F) on verification of payment details submitted by Pay and Accounts Officer (PAO)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//						      cell309.setColspan (3);
////						      cell309.setRowspan (2);
//						      cell309.setHorizontalAlignment (Element.ALIGN_JUSTIFIED);
//						      cell309.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell309.setPadding (10.0f);
////						      cell309.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table2.addCell(cell309);
//						      
//						      PdfPCell cell310 = new PdfPCell (new Paragraph ("F", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell310.setColspan (6);
////						      cell310.setRowspan (2);
//						      cell310.setHorizontalAlignment (Element.ALIGN_CENTER);
//						      cell310.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell310.setPadding (10.0f);
////						      cell310.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table2.addCell(cell310);
//						      
//						      PdfPCell cell311 = new PdfPCell (new Paragraph ("Final", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//						      cell311.setColspan (3);
////						      cell311.setRowspan (2);
//						      cell311.setHorizontalAlignment (Element.ALIGN_CENTER);
//						      cell311.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell311.setPadding (10.0f);
////						      cell311.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table2.addCell(cell311);
//						      
//						      PdfPCell cell312 = new PdfPCell (new Paragraph ("In case of non-gorvenment deductors, payment details of TDS/TCS deposited in bank by deductor have matched with the payment details mentioned in the TDS/TCS statement filed by the deductors. Incase of government deductors, details of TDS/TCS booked in government account have been  verified by Pay & Account Officer (PAO). ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//						      cell312.setColspan (3);
////						      cell312.setRowspan (2);
//						      cell312.setHorizontalAlignment (Element.ALIGN_JUSTIFIED);
//						      cell312.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell312.setPadding (10.0f);
////						      cell312.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table2.addCell(cell312);
//						      
//						      PdfPCell cell313 = new PdfPCell (new Paragraph ("O", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell313.setColspan (6);
////						      cell313.setRowspan (2);
//						      cell313.setHorizontalAlignment (Element.ALIGN_CENTER);
//						      cell313.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell313.setPadding (10.0f);
////						      cell313.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table2.addCell(cell313);
//						      
//						      PdfPCell cell314 = new PdfPCell (new Paragraph ("Overbooked", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//						      cell314.setColspan (3);
////						      cell314.setRowspan (2);
//						      cell314.setHorizontalAlignment (Element.ALIGN_CENTER);
//						      cell314.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell314.setPadding (10.0f);
////						      cell314.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table2.addCell(cell314);
//						      
//						      PdfPCell cell315 = new PdfPCell (new Paragraph ("Payment details of TDS/TCS deposited in bank by deductor have matched with details mentioned in the TDS/TCS statement but the amount is over claimed in the statement. Final (F) credit will be reflected only when deductor reduces claimed amount in the statement or makes new payment for excess amount claimed in the statement.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//						      cell315.setColspan (3);
////						      cell315.setRowspan (2);
//						      cell315.setHorizontalAlignment (Element.ALIGN_JUSTIFIED);
//						      cell315.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell315.setPadding (10.0f);
////						      cell315.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table2.addCell(cell315);
						      
						      
						      
						      
						      
						      
						      document.newPage();
//						      table.setSpacingBefore(300.0f);       // Space Before table starts, like margin-top in CSS
//						      table.setSpacingAfter(150.0f);  
						      
//						      PdfPTable table3=new PdfPTable(12);
//						      
//						      table3.setWidthPercentage(100);
//						      
//						      
//						      PdfPCell cell401 = new PdfPCell (new Paragraph ("PART B (Annexure)", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
//						      cell401.setColspan (12);
////						      cell401.setRowspan (2);
//						      cell401.setHorizontalAlignment (Element.ALIGN_CENTER);
//						      cell401.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell401.setPadding (10.0f);
////						      cell401.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell401);
//						      
//						      PdfPCell cell402 = new PdfPCell (new Paragraph ("Details of Salary paid and any other income and tax deducted", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell402.setColspan (12);
////						      cell402.setRowspan (2);
//						      cell402.setHorizontalAlignment (Element.ALIGN_LEFT);
//						      cell402.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell402.setPadding (10.0f);
////						      cell402.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell402);
//						      
//						      PdfPCell cell403 = new PdfPCell (new Paragraph ("1. Gross Salary \n" +
//						      		"   (a) Salary as per provisions contained in section 17(1) \n" +
//						      		"   (b) Value of perquisites under section 17(2) (as per Form No. 12BA, wherever\n" +
//						      		"       applicable) \n" +
//						      		"   (c) Profits in lieu of salary under section 17(3) (as per Form No. 12BA,\n" +
//						      		"       wherever applicable) \n" +
//						      		"   (d) Total \n" +
//						      		"2. Less Allowance to the extent exempt under section 10 \n" +
//						      		"   (a) CONVEYANCE ALLOWANCE \n" +
//						      		"3. Balance (1-2) \n" +
//						      		"4. Deductions: \n" +
//						      		"   (a) Entertainment allowance \n" +
//						      		"   (b) Tax on employment \n" +
//						      		"5. Aggregate of 4(a) and (b) \n" +
//						      		"6. Income chargeble under the head 'salaries' (3-5) \n" +
//						      		"7. Add: Any other income reported by the employee \n" +
//						      		"8. Gross total income (6 + 7) \n" +
//						      		"9. Deductions under Chapter VIA \n" +
//						      		"   (A) Sections 80C, 80CCC and 80CCD \n" +
//						      		"       (a) Section 80C \n" +
//						      		"       (b) Section 80CCC \n" +
//						      		"       (c) Section 80CCD \n" +
//						      		"   (B) Other Sections under Chapter VIA                              Gross Amount \n" +
//						      		"       (a) \n" +
//						      		"10. Aggregate of deductible amount under Chapter VIA \n" +
//						      		"11. Total income (8-10) \n" +
//						      		"12. Tax on total income \n" +
//						      		"13. Education cess @ 3% (on tax at S.No.12) \n" +
//						      		"14. Tax payable (12+13) \n" +
//						      		"15. Less : Relief under section 89 (attach details) \n" +
//						      		"16. Tax payable (14-15)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//						      cell403.setColspan (6);
////						      cell403.setRowspan (2);
//						      cell403.setHorizontalAlignment (Element.ALIGN_LEFT);
////						      cell403.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell403.setPadding (10.0f);
////						      cell403.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell403);
//						      
//						      PdfPCell cell404 = new PdfPCell (new Paragraph ("\n" +
//						      		"260,871.00\n" +
//						      		"Nil\n" +
//						      		"\n" +
//						      		"Nil\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"Nil\n" +
//						      		"2,500.00\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						       		"\n" +
//						      		"Gross Amount\n" +
//						      		"Nil\n" +
//						      		"Nil\n" +
//						      		"Nil\n" +
//						      		"Qualifying Amount", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//						      cell404.setColspan (2);
////						      cell404.setRowspan (2);
//						      cell404.setHorizontalAlignment (Element.ALIGN_RIGHT);
////						      cell404.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell404.setPadding (10.0f);
////						      cell404.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell404);
//						      
//						      PdfPCell cell405 = new PdfPCell (new Paragraph ("\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						       		"260,871.00\n" +
//						      		"\n" +
//						      		"9,600.00\n" +
//						      		"251,271.00\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"2,500.00\n" +
//						      		"\n" +
//						      		"Nil\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"Deductible Amount\n" +
//						      		"Nil\n" +
//						      		"Nil\n" +
//						      		"Nil\n" +
//						      		"Deductible Amount", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//						      cell405.setColspan (2);
////						      cell405.setRowspan (2);
//						      cell405.setHorizontalAlignment (Element.ALIGN_RIGHT);
////						      cell405.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell405.setPadding (10.0f);
////						      cell405.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell405);
//						      
//						      PdfPCell cell406 = new PdfPCell (new Paragraph ("\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"248,771.00\n" +
//						      		"\n" +
//						      		"248,771.00\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"\n" +
//						      		"Nil\n" +
//						      		"248,770.00\n" +
//						      		"2,877.00\n" +
//						      		"87.00\n" +
//						      		"2,964.00\n" +
//						      		"Nil\n" +
//						      		"2,964.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
//						      cell406.setColspan (2);
////						      cell406.setRowspan (2);
//						      cell406.setHorizontalAlignment (Element.ALIGN_RIGHT);
////						      cell406.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell406.setPadding (10.0f);
////						      cell406.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell406);
//						      
//						      PdfPCell cell407 = new PdfPCell (new Paragraph ("Verification", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell407.setColspan (12);
////						      cell407.setRowspan (2);
//						      cell407.setHorizontalAlignment (Element.ALIGN_CENTER);
//						      cell407.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell407.setPadding (5.0f);
////						      cell407.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell407);
//						      
//						      PdfPCell cell408 = new PdfPCell (new Paragraph ("1. RASHMI DHIRAJLAL PARIKH, son/daughter of DHIRAJLAL PARIKH working in the capacity of PROPRIETOR (designation) do hereby certify that the information given above is true, complete and correct and is based on the book of accounts, documents, TDS statements, and other available records.", FontFactory.getFont(FontFactory.TIMES_BOLD, 7)));
//						      cell408.setColspan (12);
////						      cell408.setRowspan (2);
//						      cell408.setHorizontalAlignment (Element.ALIGN_JUSTIFIED);
////						      cell408.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell408.setPadding (5.0f);
////						      cell408.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell408);
//						      
//						      PdfPCell cell409 = new PdfPCell (new Paragraph ("Place", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell409.setColspan (1);
////						      cell409.setRowspan (2);
//						      cell409.setHorizontalAlignment (Element.ALIGN_LEFT);
//						      cell409.setVerticalAlignment (Element.ALIGN_MIDDLE);
////						      cell409cell409.setPadding (10.0f);
////						      cell409.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell409);
//						      
//						      PdfPCell cell410 = new PdfPCell (new Paragraph ("MUMBAI", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell410.setColspan (5);
////						      cell410.setRowspan (2);
//						      cell410.setHorizontalAlignment (Element.ALIGN_CENTER);
//						      cell410.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell410.setPadding (4.0f);
////						      cell410.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell410);
//						      
//						      PdfPCell cell411 = new PdfPCell (new Paragraph ("(Signature of person responsible for deduction of tax)", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell411.setColspan (6);
//						      cell411.setRowspan (2);
//						      cell411.setHorizontalAlignment (Element.ALIGN_CENTER);
//						      cell411.setVerticalAlignment (Element.ALIGN_BOTTOM);
////						      cell411.setPadding (4.0f);
////						      cell411.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell411);
//						      
//						      PdfPCell cell412 = new PdfPCell (new Paragraph ("Date", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell412.setColspan (1);
////						      cell412.setRowspan (2);
//						      cell412.setHorizontalAlignment (Element.ALIGN_LEFT);
//						      cell412.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell412.setPadding (4.0f);
////						      cell412.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell412);
//						      
//						      PdfPCell cell413 = new PdfPCell (new Paragraph ("15-May-2014", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell413.setColspan (5);
////						      cell413.setRowspan (2);
//						      cell413.setHorizontalAlignment (Element.ALIGN_CENTER);
//						      cell413.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell413.setPadding (4.0f);
////						      cell413.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell413);
//						      
//						      PdfPCell cell414 = new PdfPCell (new Paragraph ("Designation: PROPRIETOR", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell414.setColspan (6);
////						      cell414.setRowspan (2);
//						      cell414.setHorizontalAlignment (Element.ALIGN_LEFT);
//						      cell414.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell414.setPadding (4.0f);
////						      cell414.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell414);
//						      
//						      PdfPCell cell415 = new PdfPCell (new Paragraph ("Full Name: RASHMI DHIRAJLAL PARIKH", FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
//						      cell415.setColspan (6);
////						      cell415.setRowspan (2);
//						      cell415.setHorizontalAlignment (Element.ALIGN_LEFT);
//						      cell415.setVerticalAlignment (Element.ALIGN_MIDDLE);
//						      cell415.setPadding (4.0f);
////						      cell415.setBackgroundColor (new BaseColor (99, 99, 99));					               
//						      table3.addCell(cell415);
						      
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
		                float[] columnWidths = new float[] {5f, 10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f};
		                table.setWidths(columnWidths);
//		                table1.setWidths(columnWidths);
//		                table2.setWidths(columnWidths);
//		                table3.setWidths(columnWidths);
		                
		                document.add(paragraph01);
		                document.add(paragraph02);
		                document.add(paragraph03);
		                document.add(paragraph04);
		                document.add(paragraph05);
		                document.add(paragraph06);
		                document.add(paragraph07);
		                document.add(paragraph07b);
		                document.add(paragraph07a);
		                
//		                document.add(paragraph10);
//		                document.add(paragraph12);
//		                document.add(paragraph14);
		                document.add(table);
		                document.add(paragraph101);
		                document.add(paragraph101a);
//		                document.add(paragraph102);
//		                document.add(paragraph102a);
//		                document.add(paragraph102b);
//		                document.add(paragraph103);
		                document.add(paragraph104);
//		                document.add(addTableSpace);
//		                document.add(table2);
		                
		                /*document.add(table3);
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
		                document.add(table2);*/
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
