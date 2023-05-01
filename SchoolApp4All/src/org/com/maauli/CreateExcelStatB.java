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
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;
import org.com.security.EncryptDecryptStr;

public class CreateExcelStatB {
    
//    static DBValidate dbValidate	= new DBValidate();

    static Logger logger = Logger.getLogger(CreateExcelStatB.class.getName());
    
    static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

    String driver = bundle.getString("DBDRIVER");

	static String user = bundle.getString("DBUSER");

	static String pwd = bundle.getString("DBPASSWD");

	Connection connection = null;

	Statement statement = null;

	ResultSet resultSet = null;
	
	static String path	= "";
	
	static String headerClass = "";
	
	static int noOfFieldsClass = 0;
	
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

    public void generateExcel(SessionData sessionData, String groupTitle, String categoryType, String query, List<String> printList, boolean fieldSentInList, String headers) {
    	System.gc();
    	String fields = "";
    	int index = 0;
    	noOfFieldsClass = 0;
    	headerClass = headers;
		try {
			PreparedStatement psmnt = null;
			Statement st = null;
			ResultSet rs = null;
			connectDatabase(sessionData);
			path = ce.createTodayFolder(ce.getDriveName() + bundle.getString("EXCEL_PATH_"+sessionData.getDBName()),true)+"/";
			
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
				fields = printList.get(2);
			}
			
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(categoryType);
			HSSFRow rowhead = sheet.createRow((short) index);
			
			Font font= wb.createFont();
			Font font2= wb.createFont();
			Font font3= wb.createFont();
			CellStyle cellStyle = wb.createCellStyle();
			CellStyle cellStyle2 = wb.createCellStyle();
			CellStyle cellStyle3 = wb.createCellStyle();
			
			StringTokenizer st1 = new StringTokenizer(fields, "|");
			int i = 0;
			int noOfFields = st1.countTokens();
			noOfFieldsClass = noOfFields;
			String rowHeadFields = "";
			if(headers.equalsIgnoreCase("")){
				while (st1.hasMoreTokens()) {
					rowHeadFields = st1.nextToken().toString();
					if(rowHeadFields.contains("*")){
						rowHeadFields = rowHeadFields.substring(0,rowHeadFields.lastIndexOf("*"));
					}
	//				rowhead.createCell((short) i).setCellValue(rowHeadFields);
					createCell(wb, rowhead, (short) i, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rowHeadFields, index, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet);
					i++;
				}
			}else{
//				rowhead.createCell((short) i).setCellValue(rowHeadFields);
				getHeaderStyle(wb, rowhead, (short) i, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, headers, font, cellStyle, sheet);
			}
			
//			rowhead.createCell((short) 0).setCellValue("USER_ID");
//			rowhead.createCell((short) 1).setCellValue("FIRST_NAME");
//			rowhead.createCell((short) 2).setCellValue("LAST_NAME");
//			rowhead.createCell((short) 3).setCellValue("USERNAME");
//			rowhead.createCell((short) 4).setCellValue("PASSWRD");

//			int index = 1;
			index = index + 1;//starts with 2nd row if +1
			HSSFRow row = sheet.createRow((short) index);
			
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
						createCell(wb, row, (short) n, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rowHeadFields, index, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet);
						n++;
					}
					index++;
				}
				
				while (rs.next()) {
					row = sheet.createRow((short) index);
					for(int j=0; j<noOfFields; j++){
						if(j == 0){
							createCell(wb, row, (short) j, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rs.getString(j+1), index, font, font2, font3, cellStyle, cellStyle2,cellStyle3, sheet);
						}
						else{
							createCell(wb, row, (short) j, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rs.getString(j+1), index, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet);
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
	                
	                if(fields.contains("*")){
						while (st2.hasMoreTokens()) {
							rowHeadFields = st2.nextToken().toString();
							if(rowHeadFields.contains("*")){
								rowHeadFields = rowHeadFields.substring(0,rowHeadFields.lastIndexOf("*"));
							}
							createCell(wb, row, (short) l, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rowHeadFields, 
									index, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet);
							if(l == 6 && index == 3){//leave 2 blank spaces(10-8)
								l = 7;
							}
							if(l == 8 && index == 3){//leave 2 blank spaces(10-8)
								l = 10;
							}
							else if(l == 11 && index == 3){//leave 2 blank spaces(13-11)
								l = 13;
							}
							l++;
							
						}
					}
	                else 
	                {
	                	while (st2.hasMoreTokens()) {  
		                    columnArray[l] = st2.nextToken();
		                    if(columnArray[l].equalsIgnoreCase("") || columnArray[l].equalsIgnoreCase(null) || columnArray[l].equalsIgnoreCase("null")){
		                    	columnArray[l] = " ";
		                    }
		                    if(l == 0){
								createCell(wb, row, (short) l, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, columnArray[l], 
										index, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet);
							}
							else{
								createCell(wb, row, (short) l, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, columnArray[l], 
										index, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet);
							}
		                    l++;
	                	} 
	                }
	                index++;
	                /*if(index == 1){/////to start actual data from 4th row
	                	createCell(wb, row, (short) l, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, null, 
								index, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet);
//	                	index = index + 1;
	                }*/
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
				else if(noOfFields < 5 && 
						(categoryType.equalsIgnoreCase("Age Wise") || categoryType.equalsIgnoreCase("CATEGORY_WISE"))){
					cellWidth = 2;
					sheet.setColumnWidth(k, cellWidth*2000);//1400 = 1 centimeter
				}
				else {
					sheet.autoSizeColumn(k, true);
				}
				
				//to adjust merged columns
				if(k == 6){
					k = k + 1;
				}
				else if(k == 8){
					k = k + 2;
				}
				else if(k == 11){
					k = k + 2;
				}
				/*if(k == 6 && index == 3){
					k = 16;
				}*/
				k++;
			}

			String filePath = path+categoryType+ce.timeInMillis()+".xls";
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
    private static void createCell(Workbook workbook, Row row, short column, short halign, short valign, String cellValue, 
    		int rowNum, Font font, Font font2, Font font3, CellStyle cellStyle, CellStyle cellStyle2, CellStyle cellStyle3, 
    		HSSFSheet sheet) {
        Cell cell = row.createCell(column);
        cell.setCellValue(cellValue);
//        CellStyle cellStyle = workbook.createCellStyle();
//        Font font= workbook.createFont();
//        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:B3"));
        if(headerClass.equalsIgnoreCase("") && rowNum == 0){
        	cellStyle.setAlignment(halign);
            cellStyle.setVerticalAlignment(valign);
            cellStyle.setWrapText(true);
            font.setFontHeightInPoints((short)10);
            font.setFontName("Dotum");
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
        else if(rowNum == 1 || rowNum == 2){
        	sheet.addMergedRegion(new CellRangeAddress(
        			rowNum, //first row (0-based)
        			rowNum, //last row  (0-based)
                    0, //first column (0-based)
                    noOfFieldsClass+4  //last column  (0-based)
            ));
        	cellStyle3.setAlignment(halign);
        	cellStyle3.setVerticalAlignment(valign);
        	font3.setFontHeightInPoints((short)10);
        	font3.setFontName("Dotum");
        	font3.setItalic(false);
        	cellStyle3.setBorderLeft(CellStyle.BORDER_MEDIUM);
        	cellStyle3.setBorderTop(CellStyle.BORDER_MEDIUM);
        	cellStyle3.setBorderRight(CellStyle.BORDER_MEDIUM);
        	cellStyle3.setBorderBottom(CellStyle.BORDER_MEDIUM);
        	cellStyle3.setFont(font3);
            cell.setCellStyle(cellStyle3);
        }
        else if(!headerClass.equalsIgnoreCase("") && rowNum == 3){
        	row.setHeight((short)600);
        	for(int fc = 0; fc < (noOfFieldsClass+5); fc++){////to merge 4th and 5th row
        		if(fc == 6){
        			fc = 8;
        		}
        		if(fc == 8){
        			fc = 11;
        		}
        		if(fc == 11){
        			fc = 14;
        		}
	        		sheet.addMergedRegion(new CellRangeAddress(
	                        3, //first row (0-based)
	                        4, //last row  (0-based)
	                        fc, //first column (0-based)
	                        fc  //last column  (0-based)
	                ));
        	}
        	sheet.addMergedRegion(new CellRangeAddress(
                    3, //first row (0-based)
                    3, //last row  (0-based)
                    6, //first column (0-based)
                    7  //last column  (0-based)
            ));
        	sheet.addMergedRegion(new CellRangeAddress(
                    3, //first row (0-based)
                    3, //last row  (0-based)
                    8, //first column (0-based)
                    10  //last column  (0-based)
            ));
        	sheet.addMergedRegion(new CellRangeAddress(
                    3, //first row (0-based)
                    3, //last row  (0-based)
                    11, //first column (0-based)
                    13  //last column  (0-based)
            ));
        	cellStyle2.setAlignment(halign);
        	cellStyle2.setVerticalAlignment(valign);
        	cellStyle2.setWrapText(true);
            font2.setFontHeightInPoints((short)10);
            font2.setFontName("Dotum");
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
        else if(!headerClass.equalsIgnoreCase("") && rowNum == 4){
//        	row = sheet.createRow((short) rowNum);
        	row.setHeight((short)1400);
        	cellStyle2.setAlignment(halign);
        	cellStyle2.setVerticalAlignment(valign);
        	cellStyle2.setWrapText(true);
            font2.setFontHeightInPoints((short)10);
            font2.setFontName("Dotum");
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

    private static void getHeaderStyle(Workbook workbook, Row row, short column, short halign, short valign, 
    		String cellValue, Font font, CellStyle cellStyle, HSSFSheet sheet)
    {
    	Cell cell = row.createCell(column);
    	cell.setCellValue(cellValue);
    	sheet.addMergedRegion(new CellRangeAddress(
    			0, //first row (0-based)
    			0, //last row  (0-based)
                0, //first column (0-based)
                noOfFieldsClass+4  //last column  (0-based)
        ));
    	cellStyle.setAlignment(halign);
    	cellStyle.setVerticalAlignment(valign);
    	font.setFontHeightInPoints((short)10);
    	font.setFontName("Dotum");
    	font.setColor(IndexedColors.BLACK.getIndex());
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
    	font.setItalic(false);
    	cellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//    	cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
//    	cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
    	cellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
//    	cellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
    	cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);

    }

}
