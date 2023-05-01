import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HeaderFooter;

public class WriteExcelDemo
{
    public static void main(String[] args)
    {
        //Blank workbook
    	HSSFWorkbook workbook = new HSSFWorkbook();
         
        //Create a blank sheet
    	HSSFSheet sheet = workbook.createSheet("Employee Data");
          
    	HSSFHeader header = sheet.getHeader();
//    	sheet.setDisplayRowColHeadings(true);
        header.setCenter("Center Header");
        header.setLeft("Left Header");
        header.setRight(HSSFHeader.font("Stencil-Normal", "Italic") +
                        HSSFHeader.fontSize((short) 16) + "Right w/ Stencil-Normal Italic font and size 16");
    	
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("10", new Object[] {"ID", "NAME", "LASTNAME"});
        data.put("11", new Object[] {1, "Amit", "Shukla"});
        data.put("12", new Object[] {2, "Lokesh", "Gupta"});
        data.put("13", new Object[] {3, "John", "Adwards"});
        data.put("14", new Object[] {4, "Brian", "Schultz"});
          
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 10;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("D://yash//howtodoinjava_demo.xls"));
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}