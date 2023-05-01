package org.com.maauli;

import java.awt.Frame;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.com.accesser.SessionData;
import org.com.security.EncryptDecryptStr;

public class BackupExcel {
    
	String driver = bundle.getString("DBDRIVER");

	boolean validateUserFlag = false;

	boolean admitFormFlag = false;

	Common cm = new Common();

	CreateExcel ce = new CreateExcel();
	
	EncryptDecryptStr encdec = new EncryptDecryptStr();

	SessionData sessionData = new SessionData();
	
    static Logger logger = Logger.getLogger(BackupExcel.class.getName());
    
    static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	Connection connection = null;

	Statement statement = null;

	ResultSet resultSet = null;
	
	static String path	= "";
	
	static String headerClass = "";
	
	Common commonObj = new Common();
	
//	DBValidate dbValidate = new DBValidate();
	
	static String user = bundle.getString("DBUSER");

	static String pwd = bundle.getString("DBPASSWD");
	
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

    public void BackupExcelMethod(SessionData sessionData, String groupTitle, String categoryType, String query, 
    		List<String> printList, boolean fieldSentInList, String headers, String fields, double percentProgress, Frame f) throws SQLException {
    	System.gc();
    	int index = 0;
    	String excel_readonly = "";
    	headerClass = headers;
    	PreparedStatement psmnt = null;
		Statement st = null;
		ResultSet rs = null;
		
//		f.setTitle("Backup completed " + percentProgress + " %");
		
		try {
			connectDatabase(sessionData);
			path = commonObj.createTodayFolder(commonObj.getDriveName() + bundle.getString("BACKUP_PATH_"+sessionData.getDBName()),true);
			excel_readonly = bundle.getString("EXCEL_READONLY");
			
			if(printList.size() <= 0){
				st = connection.createStatement();
				rs = st.executeQuery(query);
			}
			
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet(categoryType);
			//sheet.protectSheet(excel_readonly);
			XSSFRow rowhead = sheet.createRow((short) index);
			Font font= wb.createFont();
			Font font2= wb.createFont();
			CellStyle cellStyle = wb.createCellStyle();
			CellStyle cellStyle2 = wb.createCellStyle();
			
			cellStyle.setLocked(true); //true or false based on the cell.
			cellStyle2.setLocked(true); //true or false based on the cell.
			
			StringTokenizer st1 = new StringTokenizer(fields, "|");
			int noOfFields = st1.countTokens();
			String rowHeadFields = "";
			XSSFRow row = sheet.createRow((short) index);
			
			if(printList.size() <= 0){
				
				
				StringTokenizer st2 = new StringTokenizer(fields, "|");
				int n = 0;
				if(!fieldSentInList){
					while (st2.hasMoreTokens()) {
						rowHeadFields = st2.nextToken().toString();
						if(rowHeadFields.contains("*")){
							rowHeadFields = rowHeadFields.substring(0,rowHeadFields.lastIndexOf("*"));
						}
		//				rowhead.createCell((short) i).setCellValue(rowHeadFields);
						createCell(wb, row, (short) n, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rowHeadFields, index, font, font2, cellStyle, cellStyle2);
						n++;
					}
					index++;
				}
				
				String cellValue = "";
				while (rs.next()) {
					row = sheet.createRow((short) index);
					f.setTitle("Backup completed " + percentProgress + " % ("+index+")");
//					logger.info("index = "+index);
					for(int j=0; j<noOfFields; j++){
						try{
							cellValue = rs.getString(j+1);
						} catch(Exception cell){
							cellValue = "";
						}
						
						if(j == 0){
							createCell(wb, row, (short) j, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, cellValue, index, font, font2, cellStyle, cellStyle2);
						}
						else{
							createCell(wb, row, (short) j, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, cellValue, index, font, font2, cellStyle, cellStyle2);
						}
					}
					index++;
				}
			}
			else{
//				index = index + 1;
				int m = 0;
				int k = 0;
				String[] dataArray = new String[printList.size()];
		        dataArray = printList.toArray(dataArray);
		        logger.info("printList size === " + printList.size());
		        logger.info("dataArray === " + dataArray.length);
		        
		        if(headers.equalsIgnoreCase("")){
		        	m = 1;
		        }
		        
		        for (k = m ; k < printList.size(); k++) {
		        	row = sheet.createRow((short) index);
		        	int tokenSize = 0;
		        	int l = 0;
	                StringTokenizer st2 = new StringTokenizer(dataArray[k],"|");  
	                tokenSize = st2.countTokens();
	                String[] columnArray = new String[tokenSize];
	                while (st2.hasMoreTokens()) {  
	                    columnArray[l] = st2.nextToken();
	                    if(columnArray[l].equalsIgnoreCase("") || columnArray[l].equalsIgnoreCase(null) || columnArray[l].equalsIgnoreCase("null")){
	                    	columnArray[l] = " ";
	                    }
	                    if(l == 0){
							createCell(wb, row, (short) l, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, columnArray[l], index, font, font2, cellStyle, cellStyle2);
						}
						else{
							createCell(wb, row, (short) l, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, columnArray[l], index, font, font2, cellStyle, cellStyle2);
						}
	                    l++;
	                } 
	                index++;
		        }
			}
			
			//to set column width
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

			String filePath = path+"/"+categoryType+"_"+headers.replace("/", "_")+"_"+commonObj.timeInMillis()+".xlsx";
			FileOutputStream fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);
//			commonObj.setPasswordToExcel(filePath, fileOut);
//			commonObj.CreatePasswordProtectedZip(path,"");
//			fileOut.close();
			logger.info("Data is saved in excel file.");
		} catch (Exception e) {
			logger.error("Exception :: " + e);
		}finally {
			if(rs != null){
				rs.close();
			}
			if (connection != null) {
				connection.close();
			}
			closeDatabase(sessionData);
        }
    }
    
    // This method create a cell align the text
    private static void createCell(Workbook workbook, Row row, short column, short halign, short valign, String cellValue, int rowNum, Font font, Font font2, CellStyle cellStyle, CellStyle cellStyle2) {
    	Cell cell = row.createCell(column);
        cell.setCellValue(cellValue);
//      CellStyle cellStyle = workbook.createCellStyle();
//      Font font= workbook.createFont();
        if(!headerClass.equalsIgnoreCase("") && rowNum == 0){
            cellStyle.setAlignment(halign);
            cellStyle.setVerticalAlignment(valign);
            
            font.setFontHeightInPoints((short)10);
            font.setFontName("Arial");
            font.setColor(IndexedColors.BLACK.getIndex());
            font.setBoldweight(Font.BOLDWEIGHT_NORMAL);//Make font normal
            font.setItalic(false);
            
            cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
            cellStyle.setFont(font);
        	cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        	cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        	font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
        	cell.setCellStyle(cellStyle);
        }
        else {
        	cellStyle2.setAlignment(halign);
        	cellStyle2.setVerticalAlignment(valign);
            
            font2.setFontHeightInPoints((short)10);
            font2.setFontName("Arial");
            font2.setColor(IndexedColors.BLACK.getIndex());
            font2.setBoldweight(Font.BOLDWEIGHT_NORMAL);//Make font normal
            font2.setItalic(false);
            
            cellStyle2.setBorderBottom(cellStyle2.BORDER_MEDIUM);
            cellStyle2.setBorderTop(cellStyle2.BORDER_MEDIUM);
            cellStyle2.setBorderRight(cellStyle2.BORDER_MEDIUM);
            cellStyle2.setBorderLeft(cellStyle2.BORDER_MEDIUM);
            cellStyle2.setFont(font2);
//        	cellStyle2.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
//        	cellStyle2.setFillPattern(cellStyle2.SOLID_FOREGROUND);
//        	font2.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
        	cell.setCellStyle(cellStyle2);
        }
        
        
        
    }
    
    /**
         * This method will return Style of Header Cell
         * @return
     */

    private static void getHeaderStyle(Workbook workbook, Row row, short column, short halign, short valign, String cellValue, Font font, CellStyle cellStyle)
    {
	    	Cell cell = row.createCell(column);
	        cell.setCellValue(cellValue);
//            CellStyle cellStyle = workbook.createCellStyle();
            
//            Font font= workbook.createFont();
            font.setFontHeightInPoints((short)10);
            font.setFontName("Arial");
            font.setColor(IndexedColors.BLACK.getIndex());
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
            font.setItalic(false);
            cellStyle.setFont(font);
            
            cellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
            cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
            cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            cellStyle.setBorderRight(CellStyle.BORDER_THIN);
            cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
            cellStyle.setBorderTop(CellStyle.BORDER_THIN);
            cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
            cellStyle.setAlignment(halign);
            cellStyle.setVerticalAlignment(valign);
            cell.setCellStyle(cellStyle);

    }

    public boolean connectDatabase(SessionData sessionData1) {
		boolean dbConnection = false;
		sessionData 	= sessionData1;
		String dbUser 	= "";
		String dbPass 	= "";
		
		try {
			String url = bundle.getString("DBURL_"+sessionData.getDBName());
			dbUser = sessionData.getDBUser();
			dbPass = sessionData.getDBPass();
			if(dbUser.equalsIgnoreCase(null) || dbUser.equalsIgnoreCase("")){
				sessionData.setDBUser(encdec.decryptString(user));
				sessionData.setDBPass(encdec.decryptString(pwd));
				dbUser = sessionData.getDBUser();
				dbPass = sessionData.getDBPass();
			}
			
			Class.forName(driver);
			connection = DriverManager.getConnection(url, dbUser, dbPass);
			dbConnection = true;
		} catch (Exception e) {
			logger.error("Database connectivity issue...");
			JOptionPane.showMessageDialog(null, "Database connectivity issue...");
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
	}
}
