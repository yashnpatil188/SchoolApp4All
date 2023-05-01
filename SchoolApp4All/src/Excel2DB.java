import java.io.*;
import java.sql.*;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

public class Excel2DB {
	public static void main(String[] args) {
		try {

			Statement statement = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/SCHOOL", "root", "maauli123");
            PreparedStatement sql_statement = null;
            String jdbc_insert_sql = "INSERT INTO XLS_POI"
                            + "(GR_NO, PRESENT_STD, PRESENT_DIV, ACADEMIC_YEAR, CREATED_DATE, CREATED_BY, SECTION_NM) VALUES"
                            + "(?,?,?,?,?,?,?)";
            
            sql_statement = (PreparedStatement) connection.prepareStatement(jdbc_insert_sql);
            FileInputStream input = new FileInputStream("D:\\optional_allotment.xls");
            POIFSFileSystem fs = new POIFSFileSystem (input);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            Iterator rows = sheet.rowIterator(); 
            for(int i=1; i<=sheet.getLastRowNum(); i++){
                HSSFRow row = sheet.getRow(i);
//                HSSFRow row=null;
//                int id = (int) row.getCell(0).getNumericCellValue();
                String GR_NO = row.getCell(0).getStringCellValue();
                String PRESENT_STD = row.getCell(1).getStringCellValue();
                String PRESENT_DIV = row.getCell(2).getStringCellValue();
                String ACADEMIC_YEAR = row.getCell(3).getStringCellValue();
                String CREATED_DATE = row.getCell(4).getDateCellValue().toString();
                String CREATED_BY = row.getCell(5).getStringCellValue();
                String SECTION_NM = row.getCell(6).getStringCellValue();
                
                String sql = "INSERT INTO OPTIONAL_ALLOTMENT (GR_NO, PRESENT_STD, PRESENT_DIV, ACADEMIC_YEAR, CREATED_DATE, CREATED_BY, SECTION_NM) VALUES('"+GR_NO+"','"+PRESENT_STD+"','"+PRESENT_DIV+"','"+ACADEMIC_YEAR+"',SYSDATE(),'"+CREATED_BY+"','"+SECTION_NM+"')";
                statement = connection.createStatement();
    			statement.executeUpdate(sql);
//                PreparedStatement pstm = (PreparedStatement)connection.prepareStatement(sql);
                System.out.println("Import rows "+i);
            }
            /*while(rows.hasNext()) {        
                HSSFRow row = (HSSFRow) rows.next(); 
                Iterator cells = row.cellIterator();
                        while(cells.hasNext()) {
                                HSSFCell cell =  (HSSFCell) cells.next();
                                switch(cell.getCellType()) { 
                                case Cell.CELL_TYPE_STRING: //handle string columns
                                        sql_statement.setString(1,  
                                                   cell.getStringCellValue());                                                                                     
                                        break;
                                case Cell.CELL_TYPE_NUMERIC: //handle double data
                                            sql_statement.setDouble(2,cell.getNumericCellValue() );
                                        break;
                                }
                        }
            }
            sql_statement.executeUpdate(); //we can execute the statement before  
            */
            connection.commit();
            connection.close();
            input.close();
            System.out.println("Success import excel to mysql table");
		} catch (Exception e) {
			System.out.println("Exception :: " + e);
		}
	}
}