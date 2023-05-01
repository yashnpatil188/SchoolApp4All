

import java.io.FileOutputStream;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import java.io.*;
import java.sql.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;
import org.com.maauli.Common;

public class CreateExcel {
    
//    static DBValidate dbValidate	= new DBValidate();

    static Logger logger = Logger.getLogger(CreateExcel.class.getName());
    
    static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	/*String driver = bundle.getString("DBDRIVER");

	String url = bundle.getString("DBURL");

	String user = bundle.getString("DBUSER");

	String pwd = bundle.getString("DBPASSWD");*/

	Connection connection = null;

	Statement statement = null;

	ResultSet resultSet = null;
	
	static String path	= "";
	
	Common ce = new Common();
	
	static DBValidate dbValidate = new DBValidate();
	
	/*public boolean connectDatabase() {

		boolean dbConnection = false;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, pwd);
			dbConnection = true;
		} catch (Exception e) {
			logger.error("Database connectivity issue...");
			JOptionPane.showMessageDialog(null,
					"Database connectivity issue...");
			return dbConnection;
		}
		return dbConnection;
	}

	public boolean closeDatabase(SessionData sessionData) {

		boolean dbClose = false;
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (connection != null) {
				connection.close();
			}
			dbClose = true;
		} catch (Exception e) {
			logger.error("Database Closing issue...");
			JOptionPane.showMessageDialog(null, "Database Closing issue...");
			return dbClose;
		}
		return dbClose;
	}*/

    public void generateExcel(SessionData sessionData, String groupTitle, String categoryType, String query, List<String> printList) {
    	String fields = "";
		try {
			PreparedStatement psmnt = null;
			Statement st = null;
			ResultSet rs = null;
			dbValidate.connectDatabase(sessionData);
			path = ce.createTodayFolder(bundle.getString("EXCEL_PATH_"+sessionData.getDBName()),true)+"/";
			
			if(printList.size() <= 0){
				st = connection.createStatement();
				rs = st.executeQuery(query);
			}
			//getField list from excelData table
			try {
				String fieldquery =  "";
				
				fieldquery = "SELECT * FROM EXCEL_DATA WHERE "
						+ "GROUP_TITLE=('"+groupTitle+"') AND CATEGORY_TYPE='" + categoryType + "'";
				logger.info("generateStrengthExcel fieldquery==>" + fieldquery);
				statement = connection.createStatement();
				resultSet = statement.executeQuery(fieldquery);

				while (resultSet.next()) {
					fields = resultSet.getString("FIELDS");
				}
			} catch (Exception e) {
				logger.info("getField list from excelData table Exception=" + e);
			} 
			
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(categoryType);
			HSSFRow rowhead = sheet.createRow((short) 0);
			
			StringTokenizer st1 = new StringTokenizer(fields, "|");
			int i = 0;
			int noOfFields = st1.countTokens();
			String rowHeadFields = "";
			while (st1.hasMoreTokens()) {
				rowHeadFields = st1.nextToken().toString();
				if(rowHeadFields.contains("*")){
					rowHeadFields = rowHeadFields.substring(0,rowHeadFields.lastIndexOf("*"));
				}
//				rowhead.createCell((short) i).setCellValue(rowHeadFields);
				createCell(wb, rowhead, (short) i, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rowHeadFields);
				i++;
			}
			
//			rowhead.createCell((short) 0).setCellValue("USER_ID");
//			rowhead.createCell((short) 1).setCellValue("FIRST_NAME");
//			rowhead.createCell((short) 2).setCellValue("LAST_NAME");
//			rowhead.createCell((short) 3).setCellValue("USERNAME");
//			rowhead.createCell((short) 4).setCellValue("PASSWRD");

//			int index = 1;
//			HSSFRow row = sheet.createRow((short) index);
			
			if(printList.size() <= 0){
				int index = 1;
				while (rs.next()) {
					HSSFRow row = sheet.createRow((short) index);
					for(int j=0; j<noOfFields; j++){
						if(j == 0){
							createCell(wb, row, (short) j, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rs.getString(j+1));
						}
						else{
							createCell(wb, row, (short) j, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rs.getString(j+1));
						}
					}
					index++;
				}
			}
			else{
				int index = 1;
				String[] dataArray = new String[printList.size()];
		        dataArray = printList.toArray(dataArray);
		        logger.info("printList size === " + printList.size());
		        logger.info("dataArray === " + dataArray.length);
		        
		        for (int k = 1; k < printList.size(); k++) {
		        	HSSFRow row = sheet.createRow((short) index);
		        	int tokenSize = 0;
		        	int l = 0;
		        	
	                StringTokenizer st2 = new StringTokenizer(dataArray[k],"|");  
	                tokenSize = st2.countTokens();
	                String[] columnArray = new String[tokenSize];
	                while (st2.hasMoreTokens()) {  
	                    columnArray[l] = st2.nextToken();
	                    if(l == 0){
							createCell(wb, row, (short) l, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, columnArray[l]);
						}
						else{
							createCell(wb, row, (short) l, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, columnArray[l]);
						}
	                    l++;
	                } 
	                index++;
		        }
			}
			StringTokenizer st3 = new StringTokenizer(fields, "|");
			String fieldToken = "";
			int cellWidth = 0;
			int k = 0;
			while (st3.hasMoreTokens()) {
				fieldToken = st3.nextToken().toString();
				if(fieldToken.contains("*")){
					cellWidth = Integer.parseInt(fieldToken.substring(fieldToken.indexOf("*")+1));
					sheet.setColumnWidth(k, cellWidth*1400);//1400 = 1 centimeter
				}
				else {
					sheet.autoSizeColumn(k, true);
				}
				k++;
			}

			String filePath = path+categoryType+".xls";
			FileOutputStream fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);
			fileOut.close();
			System.out.println("Data is saved in excel file.");
			if(rs != null){
				rs.close();
			}
			if (connection != null) {
				connection.close();
			}
			
			////open excel
			try {
				if ((new File(filePath)).exists()) {
					if((System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
						Runtime.getRuntime().exec(new String[]{"/usr/bin/open", filePath});
					}
					else {
						Process process = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+filePath);
						process.waitFor();
					}
				} else {
					logger.info("File not found");
				}
				logger.info("Done");
			} catch (Exception e) {
				logger.info(":: -----Exception---- ::\n"+e);
			}
		} catch (Exception e) {
			System.out.println("Exception :: " + e);
		}finally {
			dbValidate.closeDatabase(sessionData);
        }
    }
    
    // This method create a cell align the text
    private static void createCell(Workbook workbook, Row row, short column, short halign, short valign, String cellValue) {
        Cell cell = row.createCell(column);
        cell.setCellValue(cellValue);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cell.setCellStyle(cellStyle);
    }
}
