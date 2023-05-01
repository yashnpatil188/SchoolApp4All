package org.com.maauli;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;
import com.mysql.jdbc.PreparedStatement;

public class UpdateFieldToRegister {

	/**
	 * @param args
	 */
	static Common commonObj = new Common();
	static String gr_no_class = "", errors = "";
	static JFrame frame;;
	static String fileName = "", fieldName = "";
	static SessionData sessionData;
	static DBValidate dbValidate = new DBValidate();
	
	public UpdateFieldToRegister(SessionData sessionData1, String fieldStr)
    {
		sessionData = sessionData1;
		fieldName = fieldStr;
		frame = new JFrame("Select File");
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		frame.setLayout(new FlowLayout());
		frame.setLocation(500, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button = new JButton("Select File");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
					String currentDirectory = fileChooser.getCurrentDirectory().toString();
					frame.setVisible(false);
					
					int reply = 0;
					reply = JOptionPane.showConfirmDialog(null, "Would You Like to upload "+fieldName+" \n from "+absolutePath+"?", "Confirm", JOptionPane.YES_NO_OPTION);
					
					if (reply == JOptionPane.YES_OPTION) {
						try {
							importExcel(absolutePath, currentDirectory);
							JOptionPane.showMessageDialog(null, "Field "+fieldName+" updated successfully");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Error : "+ e1);
							JOptionPane.showMessageDialog(null, "Field "+fieldName+" update failed");
						}
					} else if (reply == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null, "You have cancelled to upload.");
					}
				}
			}
		});
		frame.add(button);
		frame.pack();
		frame.setSize(300, 75);
		frame.setVisible(true);
	}
	
	public static void importExcel(String filePath, String currentDirectory) throws IOException, InterruptedException, ClassNotFoundException{
		int screenWidth = commonObj.screeWidth();
		int screenHeight = commonObj.screeHeight();
		String dateToday = commonObj.getCurrentDate();
    	String academicYear = commonObj.getAcademicYear(dateToday);
    	String previousYear = commonObj.getPreviousYear(academicYear);
		
		JFrame f = new JFrame("Data update in progress. Don't Close");
		f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
	    f.setSize(400, 0);
	    f.setResizable(false);
	    f.setVisible(true);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		try {
			dbValidate.connectDatabase(sessionData);
			fileName  = "errors_in_import_"+commonObj.timeInMillis()+".txt";
			
			PreparedStatement pstm = null;
			FileInputStream input = new FileInputStream(filePath);
			POIFSFileSystem fs = new POIFSFileSystem(input);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			String field_name_From_Excel = fieldName, field_name = "", field_value = "", GR_NO = "", section_nm = "";
			boolean validateField = true;
			Row row;
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				try{
					row = sheet.getRow(i);
					validateField = true;
					try{
						GR_NO = row.getCell(0).getStringCellValue().toString().trim().toUpperCase();
					} catch (Exception e) {
						GR_NO = ((int) row.getCell(0).getNumericCellValue())+"";
					}
					
					if(GR_NO.length() < 7){
						GR_NO = commonObj.appendZero(GR_NO);
					}
					if(GR_NO.trim().equalsIgnoreCase("0000000")){
						return;
					}
					gr_no_class = GR_NO;
					
					section_nm = row.getCell(1).getStringCellValue().toString().trim().toUpperCase();
					field_value = row.getCell(2).toString().trim();
					
					if(section_nm.equalsIgnoreCase("")){
						errors = " with some errors in file";
						commonObj.writeToText(currentDirectory, fileName, "validate section name for GR_NO "+GR_NO);
						validateField = false;
						continue;
					}
					
					switch(field_name_From_Excel) {
			         case "Hobbies" :
		        	 	field_name = "EXTRA_1";
						field_value = commonObj.replaceCommaApostrophy(field_value);
						if (commonObj.checkComma(field_value)) {
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, "hobbies cannot contain |:; for GR_NO "+GR_NO);
							validateField = false;
							continue;
						} 
						field_value = "'"+field_value+"'";
			            break;
			         case "Student_Udise" :
			        	field_name = "SUID";
			        	field_value = commonObj.replaceCommaApostrophy(field_value);
						
						if (commonObj.checkComma(field_value)) {
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, "Student Udise id cannot contain |-:';, for GR_NO "+GR_NO);
							validateField = false;
							continue;
						}
						else if (field_value.length() > 50) {
							validateField = false;
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("Student Udise id", field_value, 50) + " for GR_NO "+GR_NO);
							continue;
						}
						field_value = "'"+field_value+"'";
			            break;
			         case "Adhaar_card" :
			        	field_name = "ADHAAR_CARD";
			        	field_value = commonObj.replaceCommaApostrophy(field_value);
						
						if(!commonObj.validateAadharNumber(field_value) && !(field_value).equalsIgnoreCase("") && (field_value).length() != 12){
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid Adhaar Card for GR_NO "+GR_NO);
							validateField = false;
							continue;
						}
						field_value = "'"+field_value+"'";
						break;
			         case "Date_of_Admission" :
			        	field_name = "DATE_ADMITTED";
						if ((!commonObj.validateDate(field_value) || field_value.equalsIgnoreCase(""))) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid Date of Admission for Gr No. "+GR_NO);
							continue;
						} else if (!commonObj.validateDate(field_value)) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid Year of Admission for Gr No. "+GR_NO);
							continue;
						} else {
							field_value = "STR_TO_DATE('" + field_value.trim().toUpperCase() + "', '%d/%m/%Y')";
						}
			            break;
			         case "Date_of_Birth" :
			        	field_name = "DOB";
						if ((!commonObj.validateDate(field_value) || field_value.equalsIgnoreCase(""))) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid Date of Birth for Gr No. "+GR_NO);
							continue;
						} else if (!commonObj.validateDate(field_value)) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid Year of Birth for Gr No. "+GR_NO);
							continue;
						} else {
							field_value = "STR_TO_DATE('" + field_value.trim().toUpperCase() + "', '%d/%m/%Y')";
						}
			            break;
			         case "Date_of_Leaving" :
			        	field_name = "DATE_LEAVING";
						if ((!commonObj.validateDate(field_value) || field_value.equalsIgnoreCase(""))) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid Date of Leaving for Gr No. "+GR_NO);
							continue;
						} else if (!commonObj.validateDate(field_value)) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid Year of Leaving for Gr No. "+GR_NO);
							continue;
						} else {
							field_value = "STR_TO_DATE('" + field_value.trim().toUpperCase() + "', '%d/%m/%Y')";
						}
			            break;
			         case "Last_school" :
			        	field_name = "LAST_SCHOOL";
			        	field_value = commonObj.replaceCommaApostrophy(field_value);
						if ((field_value.length() > 200)) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Last school cannot be grater than 200 characters for Gr No. "+GR_NO);
							continue;
						}
						field_value = "'"+field_value+"'";
			            break;
			         case "Birth_Place" :
			        	field_name = "BIRTH_PLACE";
			        	field_value = commonObj.replaceCommaApostrophy(field_value);
			        	if (field_value.length() > 50) {
			        		validateField = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Birth Place", field_value, 50));
							commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("Birth Place", field_value, 50)+" for Gr No. "+GR_NO);
							continue;
						} else if (commonObj.checkComma(field_value)) {
							validateField = false;
							JOptionPane.showMessageDialog(null, "Please enter valid characters in Birth Place without |:;");
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid characters in Birth Place without |:; for Gr No. "+GR_NO);
							continue;
						}
						field_value = "'"+field_value+"'";
			            break;
			         case "Taluka" :
			        	field_name = "TALUKA";
			        	if (commonObj.checkComma(field_value)) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid Taluka without |-:';, for Gr No. "+GR_NO);
							continue;
						} else if (field_value.length() > 30) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("Taluka", field_value, 30)+" for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "District" :
			        	field_name = "DISTRICT";
			        	if (commonObj.checkComma(field_value)) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid District without |-:';, for Gr No. "+GR_NO);
							continue;
						} else if (field_value.length() > 30) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("District", field_value, 30)+" for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "State" :
			        	field_name = "STATE";
			        	if (commonObj.checkComma(field_value)) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid State without |-:';, for Gr No. "+GR_NO);
							continue;
						} else if (field_value.length() > 30) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("State", field_value, 30)+" for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "Country" :
			        	field_name = "COUNTRY";
			        	if (commonObj.checkComma(field_value)) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid Country without |-:';, for Gr No. "+GR_NO);
							continue;
						} else if (field_value.length() > 30) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("Country", field_value, 30)+" for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "Nationality" :
			        	field_name = "NATIONALITY";
			        	if (field_value.length() > 20) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("Nationality", field_value, 20)+" for Gr No. "+GR_NO);
							continue;
						} else if (commonObj.checkComma(field_value) || commonObj.validateSpecial(field_value)) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid Nationality without |-:';, for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "Mother_Tongue" :
			        	field_name = "MOTHER_TONGUE";
			        	if (field_value.length() > 20) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("Mother Tongue", field_value, 20)+" for Gr No. "+GR_NO);
							continue;
						} else if (commonObj.checkComma(field_value) || commonObj.validateSpecial(field_value)) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid MotherTongue without |-:';, for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "Contact_1" :
			        	field_name = "CONTACT_1";
			        	if(field_value.contains(".")) {
			        		field_value = field_value.substring(0,field_value.indexOf("."));
			        	}
			        	if (!field_value.equalsIgnoreCase("") && (!commonObj.validateNumber(field_value) || field_value.length() != 10)) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid Mobile No. for Gr No. "+GR_NO);
							continue;
						} else if (field_value.length() > 20) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("Contact", field_value, 20)+" for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "Contact_2" :
			        	field_name = "CONTACT_2";
			        	if(field_value.contains(".")) {
			        		field_value = field_value.substring(0,field_value.indexOf("."));
			        	}
			        	if (!field_value.equalsIgnoreCase("") && (!commonObj.validateNumber(field_value) || field_value.length() != 10)) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid Mobile No. for Gr No. "+GR_NO);
							continue;
						} else if (field_value.length() > 20) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("Contact", field_value, 20)+" for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "Academic_Year" :
			        	field_name = "ACADEMIC_YEAR";
			        	field_value = "'"+previousYear+"'";
			            break;
			         case "Admitted_Std" :
				        	field_name = "ADMITTED_STD";
				        	field_value = "'"+field_value+"'";
				            break;
			         case "Admitted_Div" :
				        	field_name = "ADMITTED_DIV";
				        	field_value = "'"+field_value+"'";
				            break;
			         case "Bank_Name" :
			        	field_name = "BANK";
			        	if (field_value.length() > 40) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("Bank Name", field_value, 40)+" for Gr No. "+GR_NO);
							continue;
						} else if (commonObj.checkComma(field_value) || commonObj.validateSpecial(field_value)) {
							validateField = false;
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid Bank Name without |-:';, for Gr No. "+GR_NO);
							continue;
						}
			        	field_value = "'"+field_value+"'";
			            break;
			         case "Bank_Branch" :
				        	field_name = "BANK_BRANCH";
				        	if (field_value.length() > 90) {
								validateField = false;
								commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("Bank Branch", field_value, 90)+" for Gr No. "+GR_NO);
								continue;
							} else if (commonObj.checkComma(field_value) || commonObj.validateSpecial(field_value)) {
								validateField = false;
								commonObj.writeToText(currentDirectory, fileName, "Please enter valid Bank Branch without |-:';, for Gr No. "+GR_NO);
								continue;
							}
				        	field_value = "'"+field_value+"'";
				            break;
			         case "Bank_Account" :
				        	field_name = "BANK_ACCOUNT";
				        	if (field_value.length() > 20) {
								validateField = false;
								commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("Bank Account", field_value, 20)+" for Gr No. "+GR_NO);
								continue;
							} else if (commonObj.checkComma(field_value) || commonObj.validateSpecial(field_value)) {
								validateField = false;
								commonObj.writeToText(currentDirectory, fileName, "Please enter valid Bank Account without |-:';, for Gr No. "+GR_NO);
								continue;
							}
				        	field_value = "'"+field_value+"'";
				            break;
			         case "Bank_IFSC" :
				        	field_name = "BANK_IFSC";
				        	if (field_value.length() > 20) {
								validateField = false;
								commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("Bank IFSC", field_value, 20)+" for Gr No. "+GR_NO);
								continue;
							} else if (commonObj.checkComma(field_value) || commonObj.validateSpecial(field_value)) {
								validateField = false;
								commonObj.writeToText(currentDirectory, fileName, "Please enter valid Bank IFSC without |-:';, for Gr No. "+GR_NO);
								continue;
							}
				        	field_value = "'"+field_value+"'";
				            break;
			         default :
			            System.out.println("Invalid value");
			      }
					
					/*if(field_name_From_Excel.equalsIgnoreCase("Hobbies")){
						field_name = "EXTRA_1";
						field_value = "'"+commonObj.replaceCommaApostrophy(field_value)+"'";
						
						if (commonObj.checkComma(field_value)) {
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, "hobbies cannot contain |:; for GR_NO "+GR_NO);
							validateField = false;
							continue;
						}
					}
					else if(field_name_From_Excel.equalsIgnoreCase("Student_Udise")){
						field_name = "SUID";
						field_value = "'"+commonObj.replaceCommaApostrophy(field_value)+"'";
						
						if (commonObj.checkComma(field_value)) {
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, "Student Udise id cannot contain |-:';, for GR_NO "+GR_NO);
							validateField = false;
							continue;
						}
						else if (field_value.length() > 50) {
							validateField = false;
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, commonObj.charExceeded("Student Udise id", field_value, 50) + " for GR_NO "+GR_NO);
							continue;
						}
					}
					else if(field_name_From_Excel.equalsIgnoreCase("Adhaar_card")){
						field_name = "ADHAAR_CARD";
						field_value = "'"+commonObj.replaceCommaApostrophy(field_value)+"'";
						
						if(!commonObj.validateAadharNumber(field_value) && !(field_value).equalsIgnoreCase("") && (field_value).length() != 12){
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, "Please enter valid Adhaar Card for GR_NO "+GR_NO);
							validateField = false;
							continue;
						} 
					}
					else if(field_name_From_Excel.equalsIgnoreCase("Date_of_Admission")){
						field_name = "DATE_ADMITTED";
						if ((!commonObj.validateDate(field_value) || field_value.equalsIgnoreCase(""))) {
							validateField = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Date of Admission for Gr No. "+GR_NO);
						} else if (!commonObj.validateDate(field_value)) {
							validateField = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Year of Admission for Gr No. "+GR_NO);
						} else {
							field_value = "STR_TO_DATE('" + field_value.trim().toUpperCase() + "', '%d/%m/%Y')";
						}
					}
					else if(field_name_From_Excel.equalsIgnoreCase("Date_of_Birth")){
						field_name = "DOB";
						if ((!commonObj.validateDate(field_value) || field_value.equalsIgnoreCase(""))) {
							validateField = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Date of Birth for Gr No. "+GR_NO);
						} else if (!commonObj.validateDate(field_value)) {
							validateField = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Year of Birth for Gr No. "+GR_NO);
						} else {
							field_value = "STR_TO_DATE('" + field_value.trim().toUpperCase() + "', '%d/%m/%Y')";
						}
					}
					else if(field_name_From_Excel.equalsIgnoreCase("Last_school")){
						field_name = "LAST_SCHOOL";
						field_value = "'"+commonObj.replaceCommaApostrophy(field_value)+"'";
						if ((field_value.length() > 200)) {
							validateField = false;
							JOptionPane.showMessageDialog(null, "Last school cannot be grater than 200 characters for Gr No. "+GR_NO);
						}
					}*/
						
					if(validateField){
						//====hs_general_register=========//
						String sql = "UPDATE hs_general_register SET "+field_name+" = "+field_value+" "
								+ "where GR_NO='"+GR_NO+"' and SECTION_NM='"+section_nm+"'";
						System.out.println("sql::" + sql);
		
						pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(sql);
						pstm.execute();
						System.out.println("Import rows " + i+" for hs_general_register");
					}
					if(field_name.equalsIgnoreCase("Academic_Year")){
						String updateAcademicToPrevious = "";
						try{
							updateAcademicToPrevious = "update attendance set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							System.out.println("Exception "+e);
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update class_allotment set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							System.out.println("Exception "+e);
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update fee_status set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							System.out.println("Exception "+e);
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update fees_data_mandatory set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							System.out.println("Exception "+e);
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update fees_data_optional set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							System.out.println("Exception "+e);
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update marks_entry set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							System.out.println("Exception "+e);
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update optional_allotment set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							System.out.println("Exception "+e);
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update optional_fee_allotment set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							System.out.println("Exception "+e);
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update result_data set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							System.out.println("Exception "+e);
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update sms_data set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							System.out.println("Exception "+e);
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update statement_data set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							System.out.println("Exception "+e);
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
						
						try{
							updateAcademicToPrevious = "update student_subject set ACADEMIC_YEAR='"+previousYear+"' where "
									+ "ACADEMIC_YEAR='"+academicYear+"' and GR_NO='"+GR_NO+"'";
							pstm = (PreparedStatement) sessionData.getConnection().prepareStatement(updateAcademicToPrevious);
							pstm.execute();
						}
						catch (Exception e) {
							System.out.println("Exception "+e);
							errors = " with some errors in file";
							commonObj.writeToText(currentDirectory, fileName, GR_NO + " => academic year : " +e);
						}
					}
					
//					sessionData.getConnection().commit();
				} catch (Exception e) {
					System.out.println("Exception "+e);
					errors = " with some errors in file";
					commonObj.writeToText(currentDirectory, fileName, gr_no_class + " => " +e);
				}
			}
			pstm.close();
			sessionData.getConnection().close();
			input.close();
			System.out.println("Success import excel to mysql table");
			JOptionPane.showMessageDialog(null, "Excel inserted successfully.."+errors);
			try {
				String fileAddress = currentDirectory+"\\"+fileName;
				if ((new File(fileAddress)).exists()) {
					if((System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
						Runtime.getRuntime().exec(new String[]{"/usr/bin/open", fileAddress});
					}
					else {
						Process process = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+fileAddress);
						process.waitFor();
					}
				} else {
					System.out.println("File not found");
				}
				System.out.println("Done");
			} catch (Exception e) {
				System.out.println(":: -----Exception---- ::\n"+e);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Exception "+e);
			commonObj.writeToText(currentDirectory, fileName, gr_no_class + " => " +e);
			JOptionPane.showMessageDialog(null, "D drive DATA.xls (The system cannot find the file specified)");
		} catch (SQLException e) {
			System.out.println("Exception "+e);
			commonObj.writeToText(currentDirectory, fileName, gr_no_class + " => " +e);
			JOptionPane.showMessageDialog(null, "Error in import :: "+e);
		} catch (Exception e) {
			System.out.println("Exception "+e);
			commonObj.writeToText(currentDirectory, fileName, gr_no_class + " => " +e);
			JOptionPane.showMessageDialog(null, "Error in import :: "+e);
		}
		finally {
			f.setVisible(false);
		}
	}
}