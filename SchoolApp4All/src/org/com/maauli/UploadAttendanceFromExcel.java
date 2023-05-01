package org.com.maauli;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class UploadAttendanceFromExcel {

	static Common commonObj = new Common();
	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	static DBValidate dbValidate = new DBValidate();
	static Logger logger = Logger.getLogger(UploadAttendanceFromExcel.class.getName());

	public UploadAttendanceFromExcel(SessionData sessiosnData1, String filepath, String academic, String std, 
			String div, String semester, String month) {
		JFrame f = new JFrame("Attendance upload in progress. Please wait & Don't Close");
		try {
			logger.info("===UploadAttendanceFromExcel====");
			int screenWidth = commonObj.screeWidth();
			int screenHeight = commonObj.screeHeight();
			int academicStartMonth = Integer.parseInt(bundle.getString("ACADEMIC_START_MONTH"));
			
			String fileName = filepath;
			String monthCol = "";
			String[] monthList = null;
			
			if(!month.equalsIgnoreCase("All") && !month.equalsIgnoreCase("")) {
				monthCol = month.substring(0, 3)+"_";
				monthList = monthCol.split(",");
			}
			else if(month.equalsIgnoreCase("All")) {
				for(int i = academicStartMonth; i < (academicStartMonth+12); i++) {
					monthCol = monthCol + commonObj.intgerToMonth(i+"")+"_,";
				}
				monthCol = monthCol.substring(0, monthCol.length()-1);
				monthList = monthCol.split(",");
			}
			else if(semester.equalsIgnoreCase("Semester 1")){
				monthCol = "SEM1";
				monthList = monthCol.split(",");
			}
			else if(semester.equalsIgnoreCase("Semester 2")){
				monthCol = "SEM2";
				monthList = monthCol.split(",");
			}
			else if(semester.equalsIgnoreCase("Final")){
				monthCol = "SEM1,SEM2,YEARLY";
				monthList = monthCol.split(",");
			}
			
//			LinkedHashMap subjectListMap = new LinkedHashMap();
//			int stdInt = commonObj.RomanToInteger(std);
			
			XSSFWorkbook wb = new XSSFWorkbook(fileName);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			int noOfColumns = sheet.getRow(2).getLastCellNum();
			String monthData = "", updateQuery = "", grNo = "", totalStr = "";
			boolean updateStatus = false;

			// Get exam type headers
			row = sheet.getRow(2);
//			int sem1Count = ((noOfColumns - 2) / 2) + 2;
//			List examTypeHeadersList = new ArrayList();
			
			if(dbValidate.connectDatabase(sessiosnData1)) {
				f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
			    f.setSize(500, 0);
			    f.setResizable(false);
			    f.setVisible(true);
			    f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
			    
			    for (int j = 5; j < noOfColumns; j++) {
					monthData = row.getCell(j).toString();
					if(semester.equalsIgnoreCase("")) {
						totalStr = totalStr + monthList[j-5]+ "TOT=" +monthData+",";
					}
					else {
						totalStr = totalStr + monthList[j-5]+ "_TOT=" +monthData+",";
					}
				}
			    totalStr = totalStr.substring(0, totalStr.length() - 1);
			    
				for (int i = 3; i <= sheet.getLastRowNum(); i++) {
					f.setTitle("Attendance upload in progress "+(i-1)+"/"+sheet.getLastRowNum()+". Please wait & Don't Close");
					row = sheet.getRow(i);
					grNo = row.getCell(1).getStringCellValue().trim();
					
					updateQuery = "UPDATE ATTENDANCE_PERIOD SET "; 
					for (int j = 5; j < noOfColumns; j++) {
						monthData = row.getCell(j).toString();
						updateQuery = updateQuery + monthList[j-5]+ "=" +monthData+",";
					}
					updateQuery = updateQuery + totalStr;
					updateQuery = updateQuery + " WHERE GR_NO='"+grNo+"' AND ACADEMIC_YEAR='"+academic+"' AND "
							+ "SECTION_NM='"+sessiosnData1.getSectionName()+"'";
					
					updateStatus = dbValidate.updateQueryToDB(sessiosnData1, updateQuery);
					if(!updateStatus) {
						JOptionPane.showMessageDialog(null, "Attendance upload failed from Gr No "+grNo);
						break;
					}
				}

				f.setVisible(false);
				logger.info("Attendance upload completed..");
				JOptionPane.showMessageDialog(null, "Attendance upload completed..");
			}
		} catch (Exception e) {
			commonObj.logException(e);
			JOptionPane.showMessageDialog(null, "Attendance Exception.." + e);
			f.setVisible(false);
		}
	}
}
