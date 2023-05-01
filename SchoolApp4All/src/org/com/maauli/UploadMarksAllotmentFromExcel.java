package org.com.maauli;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class UploadMarksAllotmentFromExcel {

	static Common commonObj = new Common();
	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	static SessionData sessionData = new SessionData();
	static DBValidate dbValidate = new DBValidate();
	static Logger logger = Logger.getLogger(UploadMarksAllotmentFromExcel.class.getName());

	public void uploadMaxMarksFromExcel(SessionData sessiosnData1, String filepath, String academic, String std, String semester) {

		try {
			logger.info("===UploadMarksAllotmentFromExcel====");
			String fileName = filepath;
			String semType = "sem1";
			if(semester.equalsIgnoreCase("Semester 2")){
				semType = "sem2";
			}
			LinkedHashMap subjectListMap = new LinkedHashMap();
			int stdInt = commonObj.RomanToInteger(std);
			
			XSSFWorkbook wb = new XSSFWorkbook(fileName);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			int noOfColumns = sheet.getRow(2).getLastCellNum();
			String examType = "";

			// Get exam type headers
			row = sheet.getRow(2);
			int sem1Count = ((noOfColumns - 2) / 2) + 2;
			List examTypeHeadersList = new ArrayList();
			for (int i = 2; i < noOfColumns; i++) {
				examType = row.getCell(i).getStringCellValue().toString().trim().toLowerCase();
				if (examType.equalsIgnoreCase("pract/act") || examType.equalsIgnoreCase("proj/act") || examType.equalsIgnoreCase("MCQ/PRA")) {
					examType = "pract";
				}
				if (examType.equalsIgnoreCase("fut") || examType.equalsIgnoreCase("sut") || examType.equalsIgnoreCase("test")) {
					examType = "obt";
				}

				if(stdInt < 9){
					if(i < 10){
						examTypeHeadersList.add(semType+"_" + examType);
					}
					else{
						examTypeHeadersList.add(semType+"_" + examType + "1");
					}
				}
				else if (i < sem1Count) {
					examTypeHeadersList.add("sem1_" + examType);
				} else {
					examTypeHeadersList.add("sem2_" + examType);
				}
			}

			String subject_name = "";
			String marks = "";
			LinkedHashMap subjectDetailsMap = new LinkedHashMap();
			LinkedHashMap newSubjectMaxMap = new LinkedHashMap();
			LinkedHashMap subjectOrderMap = new LinkedHashMap();

			// Get max marks for each subject
			try {
				if (dbValidate.connectDatabase(sessiosnData1)) {
					String subject_status = "old";
					newSubjectMaxMap = dbValidate.findNewSubList(sessiosnData1, academic, std, newSubjectMaxMap);
					subjectOrderMap = dbValidate.findSubjectOrderMap(sessiosnData1, academic, std);
					if(newSubjectMaxMap.size() > 0){
						subject_status = "new";
					}
					
					for (int i = 3; i <= sheet.getLastRowNum(); i++) {
						row = sheet.getRow(i);
						if(stdInt > 0){
							if (i % 2 != 0) {
								subject_name = row.getCell(0).getStringCellValue().toString().trim().toUpperCase();
								subjectDetailsMap.put("subject_name", subject_name);
								subjectDetailsMap.put("subject_status", subject_status);
	
								for (int j = 0; j < examTypeHeadersList.size(); j++) {
									marks = row.getCell(j + 2).toString();
									marks = marks.substring(0, marks.indexOf("."));
									subjectDetailsMap.put(examTypeHeadersList.get(j), marks);
								}
							} else {
								for (int j = 0; j < examTypeHeadersList.size(); j++) {
									marks = row.getCell(j + 2).toString();
									marks = marks.substring(0, marks.indexOf("."));
									subjectDetailsMap.put(examTypeHeadersList.get(j) + "_ct",
											marks);
								}
								subjectDetailsMap.put("order_no", subjectOrderMap.get(subject_name));
								subjectListMap.put(subject_name, subjectDetailsMap);
	
								dbValidate.updateSubMaxMin(sessiosnData1, subjectListMap, academic, std, semester);
							}
						}
						else{
							subject_name = row.getCell(0).getStringCellValue().toString().trim().toUpperCase();
							subjectDetailsMap.put("subject_name", subject_name);
							subjectDetailsMap.put("subject_status", subject_status);

							for (int j = 0; j < examTypeHeadersList.size(); j++) {
								marks = row.getCell(j + 2).toString();
								marks = marks.substring(0, marks.indexOf("."));
								subjectDetailsMap.put(examTypeHeadersList.get(j), marks);
							}
							subjectDetailsMap.put("order_no", subjectOrderMap.get(subject_name));
							subjectListMap.put(subject_name, subjectDetailsMap);
							
							dbValidate.updateSubMaxMin(sessiosnData1, subjectListMap, academic, std, semester);
						}
					}
				}
			} catch (Exception e12) {
				logger.error("Exception logoutButton ==>>" + e12);
			} finally {
				dbValidate.closeDatabase(sessionData);
			}
			logger.info("Marks Allotment upload completed..");
			JOptionPane.showMessageDialog(null, "Marks Allotment upload completed..");

		} catch (Exception e) {
			logger.error("Marks Allotment Exception.." + e);
			JOptionPane.showMessageDialog(null, "Marks Allotment Exception.." + e);
		}
	}
}
