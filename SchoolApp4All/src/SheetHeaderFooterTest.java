import java.io.File;
import java.io.IOException;

import jxl.CellView;
import jxl.HeaderFooter;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

public class SheetHeaderFooterTest {

  /**
   * @param args
   * @throws IOException 
   * @throws IOException 
   * @throws WriteException 
   * @throws BiffException 
   */
  public static void main(String[] args) throws IOException, WriteException {
    //Creates a writable workbook with the given file name
    WritableWorkbook workbook = Workbook.createWorkbook(new File("D:/HeaderFooter.xls"));
    
    //Create sheet and add a label
    WritableSheet sheet = workbook.createSheet("My Sheet", 0);
    
    //Add Header and Footer
      HeaderFooter header = new HeaderFooter();
      header.getLeft().appendWorkbookName();
      header.getCentre().append("BE THE CODER");
      header.getRight().appendWorkSheetName();
      sheet.getSettings().setHeader(header);

      HeaderFooter footer = new HeaderFooter();
      footer.getLeft().appendDate();
      
      footer.getCentre().append("Page ");
      footer.getCentre().appendPageNumber();
      footer.getCentre().append("/");
      footer.getCentre().appendTotalPages();
      
      footer.getRight().appendTime();
      sheet.getSettings().setFooter(footer);
    
// normal cells    
//    sheet.addCell(new Label(1, 2, "ABCD"));
//    sheet.addCell(new Label(1, 200, "ABCD"));
//    sheet.addCell(new Label(1, 3, "1111"));
//    sheet.addCell(new Label(0, 4, "2222"));
    
   // Create cell font and format
      WritableFont cellFont = new WritableFont(WritableFont.TIMES, 16);
      cellFont.setColour(Colour.BLUE);
      
      WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
      cellFormat.setBackground(Colour.ORANGE);
      cellFormat.setAlignment(Alignment.LEFT);
      cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
      cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
      
      
    /*
    //Merge col[0-3] and row[1]
    sheet.mergeCells(0, 1, 3, 1);
    Label lable = new Label(0, 1, "BE THE CODER", cellFormat);
    sheet.addCell(lable);
    
    
    //Merge col[0-5] and row[3-5]
    sheet.mergeCells(0, 3, 5, 5);
    lable = new Label(0, 3, "BE THE CODER", cellFormat);
    sheet.addCell(lable);
    */
      
    //Set cell width in CHARS
      int col = 2;
      int widthInChars = 3;
      sheet.setColumnView(col, widthInChars);
      sheet.addCell(new Label(col, 1, "A", cellFormat));    
      
      col = 3;
      widthInChars = 4;
      sheet.setColumnView(col, widthInChars);
      sheet.addCell(new Label(col, 1, "BB", cellFormat));  
      
      col = 4;
      widthInChars = 16;
      sheet.setColumnView(col, widthInChars);
      sheet.addCell(new Label(col, 1, "CCCCC", cellFormat));  
      
      col = 5;
      sheet.addCell(new Label(col, 1, "VVVVVVVVVVVVVVVVVVVVVVVVVV", cellFormat));
      CellView cell = sheet.getColumnView(col);
      cell.setAutosize(true);
      sheet.setColumnView(col, cell);
      
    //Writes out the data held in this workbook in Excel format
    workbook.write(); 
    
    //Close and free allocated memory 
    workbook.close(); 
    
    System.out.println("Header Footer write completed in excel.");
  }

}