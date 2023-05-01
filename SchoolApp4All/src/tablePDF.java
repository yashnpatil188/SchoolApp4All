import java.awt.Color;
import java.io.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
public class tablePDF{
 public static void main(String arg[])throws Exception{
 Document document=new Document();
 PdfWriter.getInstance(document,new FileOutputStream("D:\\yash\\tablePDF.pdf"));
 document.open();
// PdfPTable table=new PdfPTable(5);
// 
 	Chunk chunkTitleNationality = new Chunk("Nationality");
	Font fontTitleNationality = new Font(Font.TIMES_ROMAN);
	fontTitleNationality.setStyle(Font.BOLD);
	fontTitleNationality.setSize(12);
	fontTitleNationality.setColor(Color.BLACK);
	chunkTitleNationality.setFont(fontTitleNationality);
	
	Paragraph paraTitleNationality = new Paragraph();	
	paraTitleNationality.setSpacingBefore(5);
	paraTitleNationality.add(chunkTitleNationality);
	paraTitleNationality.setAlignment(Element.ALIGN_LEFT);
//	
// table.addCell(paraTitleNationality);
// table.addCell("Place");
// table.setTotalWidth(800);
// table.addCell("RoseIndia");
// table.addCell("Delhi");
// table.addCell("RoseIndia123");
// table.addCell("Madras");
// document.add(table);
// 
// ///////colspan////////////////
 PdfPTable table1 = new PdfPTable(3);
 table1.setTotalWidth(500);
 table1.setLockedWidth(true);
 table1.setWidths(new float[]{2, 1, 1});
 PdfPCell cell;
 cell = new PdfPCell(paraTitleNationality);
 cell.setColspan(3);
 table1.addCell(cell);
 cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
 cell.setColspan(2);
 table1.addCell(cell);
 table1.addCell("row 1; cell 1");
 table1.addCell("row 1; cell 2");
 table1.addCell("row 2; cell 1");
 table1.addCell("row 2; cell 2");
 document.add(table1);
 
 ///////rowspan////////////////
// PdfPTable table = new PdfPTable(3);
// table.setTotalWidth(288);
// table.setLockedWidth(true);
// table.setWidths(new float[]{2, 1, 1});
// PdfPCell cell;
// cell = new PdfPCell(new Phrase("Table 2"));
// cell.setColspan(3);
// table.addCell(cell);
// cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
// cell.setColspan(2);
// table.addCell(cell);
// table.addCell("row 1; cell 1");
// table.addCell("row 1; cell 2");
// table.addCell("row 2; cell 1");
// table.addCell("row 2; cell 2");
 
 document.close();
   }
}
