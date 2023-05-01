package org.com.maauli;

import java.io.FileOutputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
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

public class CreateMarksGradeExcel {
    
    static Logger logger = Logger.getLogger(CreateMarksGradeExcel.class.getName());
    
    static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

    String driver = bundle.getString("DBDRIVER");

	static String user = bundle.getString("DBUSER");

	static String pwd = bundle.getString("DBPASSWD");

	Connection connection = null;

	Statement statement = null;

	ResultSet resultSet = null;
	
	static String path	= "";
	
	static int noOfFieldsClass = 0;
	
	static int noOfRowshighlight = 1;
	
	static int noOfColumnsMerge = 1;
	
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
    		LinkedHashMap resultMap, LinkedHashMap subjectTitleMap, boolean fieldSentInList, String headers, int highlightRowNum, String sem) {
    	System.gc();
    	LinkedHashMap resultSubjectMap;
    	String fields = resultMap.get("fields").toString();
    	noOfFieldsClass = 0;
    	int rowNum = 0;
    	headerClass = headers;
		try {
			PreparedStatement psmnt = null;
			Statement st = null;
			ResultSet rs = null;
			connectDatabase(sessionData);
			path = ce.createTodayFolder(ce.getDriveName() + bundle.getString("EXCEL_PATH_"+sessionData.getDBName()),true)+"/";
			
			if(resultMap.size() <= 0){
				st = connection.createStatement();
				rs = st.executeQuery(query);
			}
			
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet(groupTitle);
			XSSFRow rowhead = sheet.createRow((short) rowNum);
			
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
			String fieldValue = "";
			if(headers.equalsIgnoreCase("")){
				while (st1.hasMoreTokens()) {
					rowHeadFields = st1.nextToken().toString();
					if(rowHeadFields.contains("*")){
						rowHeadFields = rowHeadFields.substring(0,rowHeadFields.lastIndexOf("*"));
					}
	//				rowhead.createCell((short) i).setCellValue(rowHeadFields);
					createCell(wb, rowhead, (short) i, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rowHeadFields, rowNum, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet, noOfColumnsMerge);
					i++;
				}
			}else{
//				rowhead.createCell((short) i).setCellValue(rowHeadFields);
				getHeaderStyle(wb, rowhead, (short) i, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, headers, font, cellStyle, sheet);
				sheet.addMergedRegion(new CellRangeAddress(
                        0, //first row (0-based)
                        0, //last row  (0-based)
                        0, //first column (0-based)
//                        noOfFields-1  //last column  (0-based)
                        (noOfFields*2)-4  //last column  (0-based) //2 indicates multiply all columns by 2 and 3 is to remove unmerged columns
                ));
			}
			
//			int rowNum = 1;
			rowNum = rowNum + 1;//starts with 2nd row if +1
			XSSFRow row = sheet.createRow((short) rowNum);
			
			if(resultMap.size() <= 0){
				
				
				StringTokenizer st2 = new StringTokenizer(fields, "|");
				int n = 0;
				/*if(!fieldSentInList){
					while (st2.hasMoreTokens()) {
						rowHeadFields = st2.nextToken().toString();
						if(rowHeadFields.contains("*")){
							rowHeadFields = rowHeadFields.substring(0,rowHeadFields.lastIndexOf("*"));
						}
		//				rowhead.createCell((short) i).setCellValue(rowHeadFields);
						createCell(wb, row, (short) n, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rowHeadFields, rowNum, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet, noOfColumnsMerge);
						n++;
					}
					rowNum++;
				}*/
				
				while (rs.next()) {
					row = sheet.createRow((short) rowNum);
					for(int col=0; col<noOfFields; col++){
						if(col == 0){
							createCell(wb, row, (short) col, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rs.getString(col+1), rowNum, font, font2, font3, cellStyle, cellStyle2,cellStyle3, sheet, noOfColumnsMerge);
						}
						else{
							createCell(wb, row, (short) col, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rs.getString(col+1), rowNum, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet, noOfColumnsMerge);
						}
					}
					rowNum++;
				}
			}
			else{
//				rowNum = rowNum + 1;
				int m = 0;
				int k = 0;
				String[] dataArray = new String[resultMap.size()];
//		        dataArray = printList.toArray(dataArray);
		        logger.info("printList size === " + resultMap.size());
		        logger.info("dataArray === " + dataArray.length);
		        
		        if(headers.equalsIgnoreCase("")){
		        	m = 1;
		        }
		        
		        //set fields row
		        int tokenSize = 0;
	        	int columnNum = 0;
		        StringTokenizer st2 = new StringTokenizer(fields,"|");  
                tokenSize = st2.countTokens();
                String[] columnArray = new String[tokenSize];
                
            	Set set = resultMap.entrySet();
				Iterator j = set.iterator();
				String title;
				//set all fields values
				while(j.hasNext()) {
					Map.Entry me = (Map.Entry)j.next();
					if(fields.contains("*") && rowNum == 1){//for field name row
						while (st2.hasMoreTokens()) {
							rowHeadFields = st2.nextToken().toString();
							if(rowHeadFields.contains("*")){
								noOfColumnsMerge = Integer.parseInt(rowHeadFields.substring(rowHeadFields.lastIndexOf("*")+1));
								rowHeadFields = rowHeadFields.substring(0,rowHeadFields.lastIndexOf("*"));
							}
							createCell(wb, row, (short) columnNum, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, rowHeadFields, 
									rowNum, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet, noOfColumnsMerge);
							if(columnNum > 0 && rowNum == 1 && noOfColumnsMerge > 1){//need change if added column
								columnNum = columnNum + 1;
							}
							columnNum++;
						}
						columnNum = 0;
					}
					else{
						noOfColumnsMerge = 1;
			        	row = sheet.createRow((short) rowNum);
			        	resultSubjectMap = (LinkedHashMap) ((LinkedHashMap) me.getValue()).get("subjects");
			        	
			        	int tokenSize1 = 0;
			        	int columnNum1 = 0;
		                StringTokenizer st3 = new StringTokenizer(fields,"|");  
		                tokenSize1 = st3.countTokens();
		                String[] columnArray1 = new String[tokenSize1];
		                
	                	while (st3.hasMoreTokens()) {
	                		rowHeadFields = st3.nextToken().toString();
							if(rowHeadFields.contains("*")){
								rowHeadFields = rowHeadFields.substring(0,rowHeadFields.lastIndexOf("*"));
							}
							
							if(subjectTitleMap.containsKey(rowHeadFields)){
    							//marks
    							createCell(wb, row, (short) columnNum, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, resultSubjectMap.get(rowHeadFields+"_MARKS").toString(), 
    									rowNum, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet, noOfColumnsMerge);
    							columnNum++;
//	    							
    							//grade
    							createCell(wb, row, (short) columnNum, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, resultSubjectMap.get(rowHeadFields+"_GRADE").toString(), 
    									rowNum, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet, noOfColumnsMerge);
    							columnNum++;
							}
							else{
								if(rowHeadFields.equalsIgnoreCase("rollNo")){
									fieldValue = ((LinkedHashMap) me.getValue()).get("rollNo").toString();
									createCell(wb, row, (short) columnNum, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, fieldValue, 
											rowNum, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet, noOfColumnsMerge);
									columnNum++;
								}
								else if(rowHeadFields.equalsIgnoreCase("Total")){
									fieldValue = ((LinkedHashMap) me.getValue()).get(sem+"Marks").toString();
									createCell(wb, row, (short) columnNum, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, fieldValue, 
											rowNum, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet, noOfColumnsMerge);
									columnNum++;
								}
								else if(rowHeadFields.equalsIgnoreCase("Grade")){
									fieldValue = ((LinkedHashMap) me.getValue()).get(sem+"Grade").toString();
									createCell(wb, row, (short) columnNum, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER, fieldValue, 
											rowNum, font, font2, font3, cellStyle, cellStyle2, cellStyle3, sheet, noOfColumnsMerge);
									columnNum++;
								}
							}
	                	}
	                	columnNum = 0;
					}
					rowNum++;
		        }
			}
			
			//to set column width
//			fields = "RollNo*1|ENGLISH*2|HINDI*2|HINDI_SANSKRIT*2|MARATHI*2|MATHEMATICS*2|SCIENCE*2|SOCIAL_SCIENCE*2|DRAWING*2|COMPUTER*2|PHYSICAL_EDUCATION*2|ENGLISH*2|HINDI*2|HINDI_SANSKRIT*2|MARATHI*2|MATHEMATICS*2|SCIENCE*2|SOCIAL_SCIENCE*2|DRAWING*2|COMPUTER*2|PHYSICAL_EDUCATION*2|Total*1|Grade*1";
			StringTokenizer st3 = new StringTokenizer(fields, "|");
			String fieldToken = "";
			int cellWidth = 0;
			int colIndex = 0;
			while (st3.hasMoreTokens()) {
				fieldToken = st3.nextToken().toString();
				if(fieldToken.contains("*")){
					noOfColumnsMerge = Integer.parseInt(fieldToken.substring(fieldToken.lastIndexOf("*")+1));
				}
				sheet.autoSizeColumn(colIndex, true);
//				sheet.autoSizeColumn(colIndex);
				colIndex++;
				if(colIndex > 0 && noOfColumnsMerge > 1){//need change if added column
//				if(colIndex > 0){
					colIndex = colIndex + 1;
				}
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
    private static void createCell(Workbook workbook, Row row, short column, short halign, short valign, String cellValue, 
    		int rowNum, Font font, Font font2, Font font3, CellStyle cellStyle, CellStyle cellStyle2, CellStyle cellStyle3, 
    		XSSFSheet sheet, int noOfColumnsMerge) {
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
        /*else if(!headerClass.equalsIgnoreCase("") && rowNum == 3){
        	row.setHeight((short)600);
//        	sheet.addMergedRegion(CellRangeAddress.valueOf("B2:B3"));
//        	sheet.addMergedRegion(CellRangeAddress.valueOf("C2:C3"));
        	for(int fc = 0; fc < (noOfFieldsClass+3); fc++){////to merge 2nd and 3rd row
        		if(fc == 9){//need change if added column
        			fc = 13;
        		}
	        		sheet.addMergedRegion(new CellRangeAddress(//need change if added column
	                        3, //first row (0-based)
	                        4, //last row  (0-based)
	                        fc, //first column (0-based)
	                        fc  //last column  (0-based)
	                ));
        	}
        	sheet.addMergedRegion(new CellRangeAddress(//need change if added column
                    3, //first row (0-based)
                    3, //last row  (0-based)
                    9, //first column (0-based)
                    12  //last column  (0-based)
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
        }*/
        else if(rowNum == 1 && column >= 1){
        	sheet.addMergedRegion(new CellRangeAddress(
        			rowNum, //first row (0-based)
        			rowNum, //last row  (0-based)
        			column, //first column (0-based)
                    (column + (noOfColumnsMerge-1))  //last column  (0-based)
            ));
        	cellStyle3.setAlignment(halign);
        	cellStyle3.setVerticalAlignment(valign);
        	font3.setFontHeightInPoints((short)10);
        	font3.setFontName("Dotum");
        	font3.setItalic(false);
        	cellStyle3.setBorderBottom(CellStyle.BORDER_MEDIUM);
        	cellStyle3.setBorderTop(CellStyle.BORDER_MEDIUM);
        	cellStyle3.setBorderRight(CellStyle.BORDER_MEDIUM);
        	cellStyle3.setBorderLeft(CellStyle.BORDER_MEDIUM);
        	cellStyle3.setFont(font3);
            cell.setCellStyle(cellStyle3);
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

    private static void getHeaderStyle(Workbook workbook, Row row, short column, short halign, short valign, 
    		String cellValue, Font font, CellStyle cellStyle, XSSFSheet sheet)
    {
	    	Cell cell = row.createCell(column);
	        cell.setCellValue(cellValue);
//            CellStyle cellStyle = workbook.createCellStyle();
            
//            Font font= workbook.createFont();
            font.setFontHeightInPoints((short)10);
            font.setFontName("Dotum");
            font.setColor(IndexedColors.BLACK.getIndex());
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
            font.setItalic(false);
            cellStyle.setFont(font);
            
            cellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            
            cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
            cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
            cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
            
            cellStyle.setAlignment(halign);
            cellStyle.setVerticalAlignment(valign);
            cell.setCellStyle(cellStyle);

    }
}