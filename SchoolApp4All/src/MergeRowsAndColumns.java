import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
public class MergeRowsAndColumns {  
        public static void main(String[] args) throws Exception{
                /* Create Workbook and Worksheet */
                HSSFWorkbook my_workbook = new HSSFWorkbook();
                HSSFSheet my_sheet = my_workbook.createSheet("Merge Cells");            
                Row row = my_sheet.createRow((short) 1);
                Cell cell = row.createCell((short) 1);
                cell.setCellValue("Merge Across Rows and Columns - Example");
                //We want the Cell Data to be distributed across B2 to D5 range
                // We use static method valueOf in CellRangeAddress, to specify range
                my_sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
                my_sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D5"));
                
               /* my_sheet.addMergedRegion(new CellRangeAddress(
                        1, //first row (0-based)
                        2, //last row  (0-based)
                        1, //first column (0-based)
                        4  //last column  (0-based)
                ));*/
                
                my_sheet.autoSizeColumn(1, true);
                my_sheet.autoSizeColumn(2, true);
                my_sheet.autoSizeColumn(3, true);
                my_sheet.autoSizeColumn(4, true);
                /* Write changes to the workbook */
                FileOutputStream out = new FileOutputStream(new File("D:\\school_app\\Merge_Rows_Columns.xls"));
                my_workbook.write(out);
                out.close();
        }
}