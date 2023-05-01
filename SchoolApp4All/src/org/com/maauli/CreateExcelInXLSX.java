package org.com.maauli;

import java.io.FileOutputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;
import org.com.security.EncryptDecryptStr;

public class CreateExcelInXLSX {
    
    static Logger logger = Logger.getLogger(CreateExcelInXLSX.class.getName());
    
    static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

    String driver = bundle.getString("DBDRIVER");

	static String user = bundle.getString("DBUSER");

	static String pwd = bundle.getString("DBPASSWD");

	Connection connection = null;

	Statement statement = null;

	ResultSet resultSet = null;
	
	static String path	= "";
	
	static int noOfRowshighlight = 1;
	
	static String headerClass = "";
	
	Common ce = new Common();
	
	static DBValidate dbValidate = new DBValidate();
	
	SessionData sessionData = new SessionData();
	
	EncryptDecryptStr encdec = new EncryptDecryptStr();
	
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

    public void generateExcel(SessionData sessionData, String groupTitle, String categoryType, String query, 
    		List<String> printList, boolean fieldSentInList, String headers, int highlightRowNum) {
    	System.gc();
    	String fields = "";
    	int index = 0;
    	noOfRowshighlight = highlightRowNum;
    	headerClass = headers;
		try {
			PreparedStatement psmnt = null;
			Statement st = null;
			ResultSet rs = null;
			connectDatabase(sessionData);
			path = ce.createTodayFolder(ce.getDriveName() + bundle.getString("TEMPLATE_PATH_"+sessionData.getDBName()),true)+"/";
			
			if(printList.size() <= 0){
				st = connection.createStatement();
				rs = st.executeQuery(query);
			}
			
			//getField list from excelData table
			if(!fieldSentInList){
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
			} else{
				fields = printList.get(0);
			}
			
			//For xls
//			HSSFWorkbook wb = new HSSFWorkbook();
//			HSSFSheet sheet = wb.createSheet(categoryType);
//			HSSFRow rowhead = sheet.createRow((short) index);
			
			//For xlsx
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet(categoryType);
//			sheet.protectSheet(excel_readonly);
			XSSFRow rowhead = sheet.createRow((short) index);
			
			Font font= wb.createFont();
			Font font2= wb.createFont();
			Font font3= wb.createFont();
			CellStyle cellStyle = wb.createCellStyle();
			CellStyle cellStyle2 = wb.createCellStyle();
			CellStyle cellStyle3 = wb.createCellStyle();
			
			StringTokenizer st1 = new StringTokenizer(fields, "|");
			int i = 0;
			int noOfFields = st1.countTokens();
			String rowHeadFields = "";
			if(headers.equalsIgnoreCase("")){
				while (st1.hasMoreTokens()) {
					rowHeadFields = st1.nextToken().toString();
					if(rowHeadFields.contains("*")){
						rowHeadFields = rowHeadFields.substring(0,rowHeadFields.lastIndexOf("*"));
					}
	//				rowhead.createCell((short) i).setCellValue(rowHeadFields);
					createCell(wb, rowhead, (short) i, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rowHeadFields, index, font, font2, font3, cellStyle, cellStyle2, cellStyle3);
					i++;
				}
			}else{
				getHeaderStyle(wb, rowhead, (short) i, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, headers, font, cellStyle);
				sheet.addMergedRegion(new CellRangeAddress(
                        0, //first row (0-based)
                        0, //last row  (0-based)
                        0, //first column (0-based)
                        noOfFields-1  //last column  (0-based)
                ));
			}
			
			index = index + 1;
//			HSSFRow row = sheet.createRow((short) index); // for xls
			XSSFRow row = sheet.createRow((short) index); // for xlsx
			
			if(printList.size() <= 0){
				
				
				StringTokenizer st2 = new StringTokenizer(fields, "|");
				int n = 0;
				if(!fieldSentInList){
					while (st2.hasMoreTokens()) {
						rowHeadFields = st2.nextToken().toString();
						if(rowHeadFields.contains("*")){
							rowHeadFields = rowHeadFields.substring(0,rowHeadFields.lastIndexOf("*"));
						}
						createCell(wb, row, (short) n, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rowHeadFields, index, font, font2, font3, cellStyle, cellStyle2, cellStyle3);
						n++;
					}
					index++;
				}
				
				while (rs.next()) {
					row = sheet.createRow((short) index);
					for(int j=0; j<noOfFields; j++){
						if(j == 0){
							createCell(wb, row, (short) j, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rs.getString(j+1), index, font, font2, font3, cellStyle, cellStyle2,cellStyle3);
						}
						else{
							createCell(wb, row, (short) j, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rs.getString(j+1), index, font, font2, font3, cellStyle, cellStyle2, cellStyle3);
						}
					}
					index++;
				}
			}
			else{
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
	                    if(columnArray[l].contains("*")){
	                    	columnArray[l] = columnArray[l].substring(0,columnArray[l].lastIndexOf("*"));
	                    }
	                    else if(columnArray[l].equalsIgnoreCase("") || columnArray[l].equalsIgnoreCase(null) || columnArray[l].equalsIgnoreCase("null")){
	                    	columnArray[l] = " ";
	                    }
	                    if(l == 0){
							createCell(wb, row, (short) l, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, columnArray[l], index, font, font2, font3, cellStyle, cellStyle2, cellStyle3);
						}
						else{
							createCell(wb, row, (short) l, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, columnArray[l], index, font, font2, font3, cellStyle, cellStyle2, cellStyle3);
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
				if(fieldToken.contains("*") && !fieldToken.contains("*auto")){
					cellWidth = Integer.parseInt(fieldToken.substring(fieldToken.indexOf("*")+1));
					sheet.setColumnWidth(k, cellWidth*1400);//1400 = 1 centimeter
				}
				else if(fieldToken.contains("*auto")){
					fieldToken = fieldToken.substring(fieldToken.indexOf("*")+1);
					sheet.autoSizeColumn(k, true);
				}
				else if(noOfFields < 8 && 
						(categoryType.equalsIgnoreCase("Age Wise"))){
					cellWidth = 2;
					sheet.setColumnWidth(k, cellWidth*2000);//1400 = 1 centimeter
				}
				else if(noOfFields < 8 && !categoryType.equalsIgnoreCase("CATEGORY_WISE") && !categoryType.equalsIgnoreCase("RELIGION_WISE")
						  && !categoryType.contains("Attendance")){
					cellWidth = 2;
					sheet.setColumnWidth(k, cellWidth*2000);//1400 = 1 centimeter
				}
				else {
					sheet.autoSizeColumn(k, true);
				}
				k++;
			}

			String filePath = path+categoryType+ce.timeInMillis()+".xlsx";
			FileOutputStream fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);
			fileOut.close();
			logger.info("Data is saved in excel file.");
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
			logger.error(e);
		}finally {
			dbValidate.closeDatabase(sessionData);
        }
    }
    
    // This method create a cell align the text
    private static void createCell(Workbook workbook, Row row, short column, short halign, short valign, String cellValue, int rowNum, Font font, Font font2, Font font3, CellStyle cellStyle, CellStyle cellStyle2, CellStyle cellStyle3) {
        Cell cell = row.createCell(column);
        cell.setCellValue(cellValue);
//        CellStyle cellStyle = workbook.createCellStyle();
//        Font font= workbook.createFont();

        if(headerClass.equalsIgnoreCase("") && (rowNum == 0 || rowNum < noOfRowshighlight)){
        	cellStyle.setAlignment(halign);
            cellStyle.setVerticalAlignment(valign);
            font.setFontHeightInPoints((short)10);
            font.setFontName("Arial");
            font.setColor(IndexedColors.BLACK.getIndex());
            font.setBoldweight(Font.BOLDWEIGHT_NORMAL);//Make font normal
            font.setItalic(false);
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
            
        	cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        	cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        	cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
            cellStyle.setFont(font);
            cell.setCellStyle(cellStyle);
        }
        else if(!headerClass.equalsIgnoreCase("") && (rowNum == 1 || rowNum < noOfRowshighlight)){
        	cellStyle2.setAlignment(halign);
        	cellStyle2.setVerticalAlignment(valign);
            font2.setFontHeightInPoints((short)10);
            font2.setFontName("Arial");
            font2.setColor(IndexedColors.BLACK.getIndex());
            font2.setBoldweight(Font.BOLDWEIGHT_NORMAL);//Make font normal
            font2.setItalic(false);
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
            
            cellStyle2.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
            cellStyle2.setBorderBottom(CellStyle.BORDER_MEDIUM);
            cellStyle2.setBorderTop(CellStyle.BORDER_MEDIUM);
            cellStyle2.setBorderRight(CellStyle.BORDER_MEDIUM);
            cellStyle2.setBorderLeft(CellStyle.BORDER_MEDIUM);
            cellStyle2.setFont(font2);
            cell.setCellStyle(cellStyle2);
        }
        else {
        	cellStyle3.setAlignment(halign);
        	cellStyle3.setVerticalAlignment(valign);
        	font3.setFontHeightInPoints((short)10);
        	font3.setFontName("Arial");
        	font3.setColor(IndexedColors.BLACK.getIndex());
        	font3.setBoldweight(Font.BOLDWEIGHT_NORMAL);//Make font normal
        	font3.setItalic(false);
//        	font3.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
            
//        	cellStyle3.setFillForegroundColor(IndexedColors.AQUA.getIndex());
//        	cellStyle3.setFillPattern(CellStyle.SOLID_FOREGROUND);
        	cellStyle3.setBorderBottom(CellStyle.BORDER_MEDIUM);
        	cellStyle3.setBorderTop(CellStyle.BORDER_MEDIUM);
        	cellStyle3.setBorderRight(CellStyle.BORDER_MEDIUM);
        	cellStyle3.setBorderLeft(CellStyle.BORDER_MEDIUM);
        	cellStyle3.setFont(font3);
            cell.setCellStyle(cellStyle3);
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

}
