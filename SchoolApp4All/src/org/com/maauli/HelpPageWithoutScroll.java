package org.com.maauli;

import org.apache.log4j.Logger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;


public class HelpPageWithoutScroll {
	static int screenWidth;
    static int screenHeight;
    static int mainCentre;
    static int scrollHeight = 900;
    static JFrame frame = null;
	static String img_path			= "";
	static String img_home 			= "";
	static String img_logout 		= "";
	static Common commonObj 		= new Common();
	static String user_name 		= "";
    static String user_role 		= "";
    static String first_name 		= "";
    static String appType   		= "";
	static Logger logger 			= Logger.getLogger(LoginView.class.getName());
    static ResourceBundle bundle    = ResourceBundle.getBundle("org.com.accesser.school");
    static SessionData sessionData 	= new SessionData();
    static DBValidate dbValidate 	= new DBValidate();
    

    public HelpPageWithoutScroll(SessionData sessionData1)
    {
    	System.gc();
    	sessionData = sessionData1;
    	user_name = sessionData.getUserName();
        user_role = sessionData.getUserRole();
        first_name = sessionData.getFirstName();
        appType = sessionData.getAppType();

		frame = new JFrame("Application Name");
		screenWidth = commonObj.screeWidth();
		screenHeight = commonObj.screeHeight();
		mainCentre = (screenWidth - 150) / 2;
		
		frame.setSize(screenWidth, screenHeight);
		frame.setResizable(false);
		
		img_path	= bundle.getString("IMAGE_PATH");
    	logger.info("img_path :: "+img_path);
    	img_home = bundle.getString("IMAGE_HOME");
    	img_logout = bundle.getString("IMAGE_LOGOUT");
    	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel() {
			/**
			 * for background image
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				Image img = new ImageIcon(img_path+"background.jpg").getImage();
				Dimension size = new Dimension(screenWidth, screenHeight);
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				g.drawImage(img, 0, 0, null);
			}
		};

		frame.add(panel);
		placeComponents(panel);
		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel) {

		logger.info("=====Helpt====");
		int width = 40, height = 90;
		panel.setLayout(null);
		JLabel headLabel = new JLabel("Help");
		headLabel.setFont(new Font("Book Antiqua", Font.BOLD, 30));
		headLabel.setBounds((screenWidth / 2 - 150), 30, 200, 40);
		panel.add(headLabel);

		String dateToday = commonObj.getCurrentDate();
		String academicYear = commonObj.getAcademicYear(dateToday);
		String nextYear = commonObj.getNextYear(academicYear);
		
		JButton finalClassAllotButton = new JButton("Final Class Allotment");
		finalClassAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		finalClassAllotButton.setBounds(width, height, 200, 35);
        panel.add(finalClassAllotButton);

        JLabel finalClassAllotLabel = new JLabel("Students left till the date provided will not be shown in catalogue");
        finalClassAllotLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        finalClassAllotLabel.setBounds(260, height, 900, 40);
		panel.add(finalClassAllotLabel);
		
        finalClassAllotButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	int reply = JOptionPane.showConfirmDialog(null, "Would You Like to update Final Class Allotment date ?", "Confirm update", JOptionPane.YES_NO_OPTION);
            	String academicYear = commonObj.getAcademicYear(commonObj.getCurrentDate());
            	
				if (reply == JOptionPane.YES_OPTION && dbValidate.connectDatabase(sessionData)) {
					String fieldValue = JOptionPane.showInputDialog("Please Enter a Final Class Allotment date in dd/mm/yyyy format");
	            	boolean isSuccess = dbValidate.updateConfigField(sessionData, false, "FINAL_CLASS_ALLOTMENT_DATE_"+academicYear, fieldValue);
	            	if(isSuccess) {
	            		JOptionPane.showMessageDialog(null, "Final Class Allotment Date updation to "+fieldValue+" completed");
	            	}
	            	else {
	            		JOptionPane.showMessageDialog(null, "Final Class Allotment Date updation to "+fieldValue+" failed");
	            	}
	            	
				}
            }
        });
        
        height = height + 45;
		JButton updateFeesReportButton = new JButton("Update Fees Report");
		updateFeesReportButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		updateFeesReportButton.setBounds(width, height, 200, 35);
        panel.add(updateFeesReportButton);
        
		try {
			String feesReportYearList = "";
			if (dbValidate.connectDatabase(sessionData)) {
				feesReportYearList = dbValidate.getAcademicYearList(sessionData, "FEES_REPORT_MANDATORY", "ACADEMIC_YEAR").toString();
			}
			
			String[] academicYearList = feesReportYearList.split(",");
	 		final JComboBox academicYear_combo = new JComboBox(academicYearList);
	 		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
	 		academicYear_combo.setBounds(260, height, 150, 40);
	 		panel.add(academicYear_combo);
	 		
	 		String [] chooseDeleteTable = { "False", "True" };
	 		final JComboBox deleteTable_combo = new JComboBox(chooseDeleteTable);
	 		deleteTable_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
	 		deleteTable_combo.setBounds(425, height, 150, 40);
	 		panel.add(deleteTable_combo);
	 		
	 		JLabel updateFeesReportLabel = new JLabel("Select True only for first time and run as false for other academic year (One time activity)");
	        updateFeesReportLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
	        updateFeesReportLabel.setBounds(575, height, 900, 40);
			panel.add(updateFeesReportLabel);

			updateFeesReportButton.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
					
	            	try {
	            		if (dbValidate.connectDatabase(sessionData)) {
	            			boolean isTableDelete = Boolean.parseBoolean(deleteTable_combo.getSelectedItem().toString());
	            			String academicSelected = (String) academicYear_combo.getSelectedItem();
	            			String startDate = bundle.getString("ACADEMIC_YEAR_START_"+sessionData.getDBName());
	            			String startYear = academicSelected.substring(0, academicSelected.indexOf("-"));
	            			String endYear = (Integer.parseInt(startYear)+1) + "";
	    					startDate = commonObj.formatyyyymmddtoddmmyyyy(startYear + "-" + startDate);//yyyy-mm-dd
	    					
	    					String endDate = bundle.getString("ACADEMIC_YEAR_END_"+sessionData.getDBName());
	    					endDate = commonObj.formatyyyymmddtoddmmyyyy(endYear + "-" + endDate);//yyyy-mm-dd
	            			boolean isSuccess = dbValidate.exportFeesDataToReport(sessionData, academicSelected, "All", "All", "Academic", startDate, endDate, isTableDelete);
	            			deleteTable_combo.setSelectedItem("False");
	            			if(isSuccess) {
	    	            		JOptionPane.showMessageDialog(null, "Attendance Periodly table created successfully.");
	    	            	}
	    	            	else {
	    	            		JOptionPane.showMessageDialog(null, "Attendance Periodly table already created.");
	    	            	}
	    				}
	            	}
	            	catch(Exception e1) {
	            		commonObj.logException(e1);
	            	}
	            }
	        });
		} catch (Exception e2) {
			commonObj.logException(e2);
		}
        
        height = height + 45;
		JButton attendanceButton = new JButton("Periodly Attendance");
		attendanceButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		attendanceButton.setBounds(width, height, 200, 35);
        panel.add(attendanceButton);
        
        JLabel attendanceLabel = new JLabel("Before updating attendance monthly for first time please click Periodly Attendance.");
        attendanceLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        attendanceLabel.setBounds(260, height, 900, 40);
		panel.add(attendanceLabel);

        attendanceButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
				
            	try {
            		if (dbValidate.connectDatabase(sessionData)) {
    	            	boolean isSuccess = dbValidate.createAttendancePeriodly(sessionData);
    	            	if(isSuccess) {
    	            		JOptionPane.showMessageDialog(null, "Attendance Periodly table created successfully.");
    	            	}
    	            	else {
    	            		JOptionPane.showMessageDialog(null, "Attendance Periodly table already created.");
    	            	}
    				}
            	}
            	catch(Exception e1) {
            		commonObj.logException(e1);
            	}
            }
        });
        
        height = height + 45;
		JButton changeColumnLengthButton = new JButton("Marks Entry failure");
		changeColumnLengthButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		changeColumnLengthButton.setBounds(width, height, 200, 35);
        panel.add(changeColumnLengthButton);
        
        JLabel changeColumnLengthLabel = new JLabel("For failure while uploading Marks Entry. Then click edit and save for that subject under create subject list.");
        changeColumnLengthLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        changeColumnLengthLabel.setBounds(260, height, 900, 40);
		panel.add(changeColumnLengthLabel);

		changeColumnLengthButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
				
            	try {
            		if (dbValidate.connectDatabase(sessionData)) {
    	            	dbValidate.alterColumnLength(sessionData, "marks_entry", "", null);
	            		JOptionPane.showMessageDialog(null, "Success");
    				}
            	}
            	catch(Exception e1) {
            		commonObj.logException(e1);
            	}
            }
        });
		
		if(appType.equalsIgnoreCase("College")) {
			height = height + 45;
			JButton updateSubListButton = new JButton("Update Fee Report");
			updateSubListButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			updateSubListButton.setBounds(width, height, 200, 35);
	        panel.add(updateSubListButton);
	        
	        JLabel updateSubListLabel = new JLabel("To resolve fee report issue for GR No. 0002068 in Science Section");
	        updateSubListLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
	        updateSubListLabel.setBounds(260, height, 900, 40);
			panel.add(updateSubListLabel);

			updateSubListButton.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
					
	            	try {
	            		if (dbValidate.connectDatabase(sessionData)) {
	    	            	dbValidate.updateFeeReportIssue(sessionData);
		            		JOptionPane.showMessageDialog(null, "Success");
	    				}
	            	}
	            	catch(Exception e1) {
	            		commonObj.logException(e1);
	            	}
	            }
	        });
		}
        
		
		height = height + 45;
		JButton delDupSubAllotButton = new JButton("Duplicate Subject Allotment");
		delDupSubAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		delDupSubAllotButton.setBounds(width, height, 200, 35);
        panel.add(delDupSubAllotButton);
        
        JLabel delDupSubAllotLabel = new JLabel("Click when Marks/Max marks shown are more than provided");
        delDupSubAllotLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        delDupSubAllotLabel.setBounds(260, height, 900, 40);
		panel.add(delDupSubAllotLabel);

		delDupSubAllotButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
				
            	try {
            		if (dbValidate.connectDatabase(sessionData)) {
    	            	dbValidate.deleteDuplicateSubjectAllotment(sessionData);
	            		JOptionPane.showMessageDialog(null, "Success");
    				}
            	}
            	catch(Exception e1) {
            		commonObj.logException(e1);
            	}
            }
        });
		
		height = height + 45;
		JButton delDupOptAllotButton = new JButton("Duplicate Optional Allotment");
		delDupOptAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		delDupOptAllotButton.setBounds(width, height, 200, 35);
        panel.add(delDupOptAllotButton);
        
        JLabel delDupOptAllotLabel = new JLabel("Click when duplicate record appears in Optional Allotment");
        delDupOptAllotLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        delDupOptAllotLabel.setBounds(260, height, 900, 40);
		panel.add(delDupOptAllotLabel);

		delDupOptAllotButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
				
            	try {
            		if (dbValidate.connectDatabase(sessionData)) {
            			dbValidate.deleteDuplicateTableData(sessionData, "OPTIONAL_ALLOTMENT");
	            		JOptionPane.showMessageDialog(null, "Success");
    				}
            	}
            	catch(Exception e1) {
            		commonObj.logException(e1);
            	}
            }
        });
		
		height = height + 45;
		JButton delDupClassAllotButton = new JButton("Duplicate Class Allotment");
		delDupClassAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		delDupClassAllotButton.setBounds(width, height, 200, 35);
        panel.add(delDupClassAllotButton);
        
        JLabel delDupClassAllotLabel = new JLabel("Click when duplicate data in Class Allotment or Leaving Certificate");
        delDupClassAllotLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        delDupClassAllotLabel.setBounds(260, height, 900, 40);
		panel.add(delDupClassAllotLabel);

		delDupClassAllotButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
				
            	try {
            		if (dbValidate.connectDatabase(sessionData)) {
    	            	dbValidate.deleteDuplicateTableData(sessionData, "CLASS_ALLOTMENT");
	            		JOptionPane.showMessageDialog(null, "Success");
    				}
            	}
            	catch(Exception e1) {
            		commonObj.logException(e1);
            	}
            }
        });
		
		height = height + 45;
		JButton subInvalidButton = new JButton("Subject invalid characters");
		subInvalidButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		subInvalidButton.setBounds(width, height, 200, 35);
        panel.add(subInvalidButton);
        
        JLabel subInvalidLabel = new JLabel("Delete subjects with invalid characters");
        subInvalidLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        subInvalidLabel.setBounds(260, height, 900, 40);
		panel.add(subInvalidLabel);

		subInvalidButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
				
            	try {
            		if (dbValidate.connectDatabase(sessionData)) {
    	            	dbValidate.deleteSubjectInvalidChar(sessionData);
	            		JOptionPane.showMessageDialog(null, "Success");
    				}
            	}
            	catch(Exception e1) {
            		commonObj.logException(e1);
            	}
            }
        });
		
		height = height + 45;
		JButton timeZoneButton = new JButton("Change Time Zone to IST");
		timeZoneButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		timeZoneButton.setBounds(width, height, 200, 35);
        panel.add(timeZoneButton);
        
        JLabel timeZoneLabel = new JLabel("Change Time Zone to IST if date is not matching in Fee Report");
        timeZoneLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        timeZoneLabel.setBounds(260, height, 900, 40);
		panel.add(timeZoneLabel);

		timeZoneButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
				
            	try {
            		if (dbValidate.connectDatabase(sessionData)) {
    	            	dbValidate.changeTimeZone(sessionData);
	            		JOptionPane.showMessageDialog(null, "Success");
    				}
            	}
            	catch(Exception e1) {
            		commonObj.logException(e1);
            	}
            }
        });
        
		height = height + 45;
		JButton attendanceMonthButton = new JButton("Attendance Month");
		attendanceMonthButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		attendanceMonthButton.setBounds(width, height, 200, 35);
        panel.add(attendanceMonthButton);

        JLabel attendanceMonthLabel = new JLabel("Select Attandance month for Semesters");
        attendanceMonthLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        attendanceMonthLabel.setBounds(260, height, 900, 40);
		panel.add(attendanceMonthLabel);
		
		attendanceMonthButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	String semSelect = "", startMonth = "", endMonth = "";
            	boolean validate = true;
            	semSelect = JOptionPane.showInputDialog("Please enter semester for "+commonObj.getCurrentYear()+" which you want to update attendance months? \n " + 
            			"1. Semester 1 \n " + 
            			"2. Semester 2");
            	
            	if(semSelect != null && !semSelect.trim().equalsIgnoreCase("null") && semSelect.trim().equalsIgnoreCase("1")){
            		semSelect = "SEM1";
            		startMonth = JOptionPane.showInputDialog("Please Enter start month for "+semSelect+" as per below example. "
							+ "\n If January then enter 1 or June then enter 6");
            		endMonth = JOptionPane.showInputDialog("Please Enter end month for "+semSelect+" as per below example. "
							+ "\n If January then enter 1 or June then enter 6");
            		
            		if(Integer.parseInt(startMonth) > 12 || Integer.parseInt(endMonth) > 12) {
            			validate = false;
            		}
				}
            	else if(semSelect != null && !semSelect.trim().equalsIgnoreCase("null") && semSelect.trim().equalsIgnoreCase("2")){
            		semSelect = "SEM2";
            		startMonth = JOptionPane.showInputDialog("Please Enter start month for "+semSelect+" as per below example. "
							+ "\n If January then enter 1 or June then enter 6");
            		endMonth = JOptionPane.showInputDialog("Please Enter end month for "+semSelect+" as per below example. "
							+ "\n If January then enter 1 or June then enter 6");
            		
            		if(Integer.parseInt(startMonth) > 12 || Integer.parseInt(endMonth) > 12) {
            			validate = false;
            		}
				}
            	else {
            		JOptionPane.showMessageDialog(null, "Please enter 1 or 2 to select semester accordingly");
            		validate = false;
            	}
            	
            	if(validate && dbValidate.connectDatabase(sessionData)) {
            		boolean isSuccess = dbValidate.updateConfigField(sessionData, false, "ATT_"+semSelect+"_"+commonObj.getAcademicYear(commonObj.getCurrentDate()), startMonth+"_"+endMonth);
                	if(isSuccess) {
                		JOptionPane.showMessageDialog(null, "Attendance month for "+semSelect+" updated for "+commonObj.getAcademicYear(commonObj.getCurrentDate()));
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "Attendance month update failed");
                	}
            	}
            }
        });
		
		height = height + 45;
		JButton newStudentsToAttButton = new JButton("Add Students");
		newStudentsToAttButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		newStudentsToAttButton.setBounds(width, height, 200, 35);
        panel.add(newStudentsToAttButton);

        JLabel newStudentsToAttLabel = new JLabel("Add all  new students in latest attendance list");
        newStudentsToAttLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        newStudentsToAttLabel.setBounds(260, height, 900, 40);
		panel.add(newStudentsToAttLabel);
		
		newStudentsToAttButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	if(dbValidate.connectDatabase(sessionData)) {
            		dbValidate.newStudents(sessionData, commonObj.getAcademicYear(commonObj.getCurrentDate()), "", "", 
                			sessionData.getSectionName(), "ATTENDANCE_PERIOD");
            		JOptionPane.showMessageDialog(null, "Complete");
            	}
            }
        });
		
//		height = height + 45;
//		JButton updateAttButton = new JButton("Update Attendance");
//		updateAttButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
//		updateAttButton.setBounds(width, height, 200, 35);
//        panel.add(updateAttButton);
//
//        JLabel updateAttLabel = new JLabel("Update Attendance to new format");
//        updateAttLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//        updateAttLabel.setBounds(260, height, 900, 40);
//		panel.add(updateAttLabel);
//		
//		updateAttButton.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//            	if(dbValidate.connectDatabase(sessionData)) {
//            		dbValidate.updateAttendanceToNewFormat(sessionData);
//            		JOptionPane.showMessageDialog(null, "Complete");
//            	}
//            }
//        });
		
		height = height + 45;
		JButton updateFieldButton = new JButton("Update Field in Register");
		updateFieldButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		updateFieldButton.setBounds(width, height, 200, 35);
        panel.add(updateFieldButton);

        String fieldStr = "Select,Academic_Year,Adhaar_card,Admitted_Std,Admitted_Div,Bank_Name,Bank_Branch,Bank_Account,Bank_IFSC,Birth_Place,"
				+ "Contact_1,Contact_2,Country,Date_of_Admission,Date_of_Birth,Date_of_Leaving,Hobbies,"
				+ "Last_school,Mother_Tongue,Nationality,State,Student_Udise,Taluka,District,Replace_Gr_No,First_Name,Last_Name,Father_Name,Mother_Name";
//        if(sessionData.getUserName().equalsIgnoreCase("prp")) {
//        	fieldStr = fieldStr+",Import Backup Data";
//        }
        
        String[] FieldList = fieldStr.split(",");
		final JComboBox field_combo = new JComboBox(FieldList);
		field_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		field_combo.setBounds(260, height+5, 200, 25);
		panel.add(field_combo);
		
		JLabel note_label = new JLabel("Note : 1) Create .xls file with Gr No in column 1, Section name in column 2 and "
        		+ "selected field name in column 3");
        note_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        note_label.setBounds(475, height-10, 1200, 50);
		panel.add(note_label);
		
		height = height + 6;
		JLabel note_label2 = new JLabel("& all dates should be in DD/MM/YYYY format.");
		note_label2.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		note_label2.setBounds(530, height, 1200, 50);
		panel.add(note_label2);
		
		height = height + 15;
		JLabel note_label3 = new JLabel("2) For Academic_Year it will directly update to previous academic year.(Still need to follow file format)");
		note_label3.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		note_label3.setBounds(475, height, 1200, 50);
		panel.add(note_label3);
		
		updateFieldButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null, "Please confirm column 2 in excel has section name as "+sessionData.getSectionName());
            	if(dbValidate.connectDatabase(sessionData) && !field_combo.getSelectedItem().toString().equalsIgnoreCase("Select")) {
//            		new UpdateFieldToRegister(sessionData, field_combo.getSelectedItem().toString());
//            		if(field_combo.getSelectedItem().toString().equalsIgnoreCase("Import Backup Data")) {
////            			try {
////							dbValidate.truncateAllTables(sessionData);
////						} catch (SQLException e1) {
////						}
//            		}
            		String fieldName = field_combo.getSelectedItem().toString();
            		int reply = 0;
            		JFileChooser fileChooser = new JFileChooser();
					int returnValue = fileChooser.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
						String currentDirectory = fileChooser.getCurrentDirectory().toString();
						reply = JOptionPane.showConfirmDialog(null, "Would You Like to upload "+fieldName+" \n from "+absolutePath+"?", "Confirm", JOptionPane.YES_NO_OPTION);
						
						if (reply == JOptionPane.YES_OPTION) {
							try {
//								if(field_combo.getSelectedItem().toString().equalsIgnoreCase("Import Backup Data")) {
////			            			try {
////			            				dbValidate.truncateAllTables(sessionData);
//										commonObj.importBackupDataFromExcel(sessionData, absolutePath, currentDirectory, fieldName);
////									} catch (SQLException e1) {
////									}
//			            		}
//								else {
									commonObj.importExcel(sessionData, absolutePath, currentDirectory, fieldName);
//								}
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
            	else {
            		JOptionPane.showMessageDialog(null, "Please select appropriate field and then click again");
            	}
            }
        });
		
		height = height + 45;
		JButton footerChangeButton = new JButton("Change Footer");
		footerChangeButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		footerChangeButton.setBounds(width, height, 200, 35);
        panel.add(footerChangeButton);
        
		JLabel footerChangeLabel = new JLabel("Update footer data to be displayed on PDF's");
		footerChangeLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		footerChangeLabel.setBounds(260, height, 900, 40);
		panel.add(footerChangeLabel);
		
		footerChangeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	String footer = "", footerValue = "";
            	boolean validate = true;
            	footer = JOptionPane.showInputDialog("Please enter footer for "+commonObj.getCurrentYear()+" which you want to update? \n " + 
            			"1. Head Master \n " + 
            			"2. Parent \n " + 
            			"3. Class Teacher");
            	
            	if(footer != null && !footer.trim().equalsIgnoreCase("null") && footer.trim().equalsIgnoreCase("1")){
            		footer = "";
            		footerValue = JOptionPane.showInputDialog("");
				}
            	else if(footer != null && !footer.trim().equalsIgnoreCase("null") && footer.trim().equalsIgnoreCase("2")){
            		footer = "1";
            		footerValue = JOptionPane.showInputDialog("");
				}
            	else if(footer != null && !footer.trim().equalsIgnoreCase("null") && footer.trim().equalsIgnoreCase("3")){
            		footer = "2";
            		footerValue = JOptionPane.showInputDialog("");
				}
            	else {
            		JOptionPane.showMessageDialog(null, "Please enter footer number eg. 1 for changing Head Master value");
            		validate = false;
            	}
            	
            	if(validate && dbValidate.connectDatabase(sessionData)) {
            		boolean isSuccess = dbValidate.updateConfigField(sessionData, false, 
            				"FOOTER"+footer, footerValue);
                	if(isSuccess) {
                		JOptionPane.showMessageDialog(null, footerValue+" updated");
                	}
                	else {
                		JOptionPane.showMessageDialog(null, footerValue+" update failed");
                	}
            	}
            }
        });
		
		height = height + 45;
		JButton updateFeesButton = new JButton("Update Fees Data");
		updateFeesButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		updateFeesButton.setBounds(width, height, 200, 35);
        panel.add(updateFeesButton);

        JLabel updateFeesLabel = new JLabel("Update Div mismatch in fees data");
        updateFeesLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        updateFeesLabel.setBounds(260, height, 900, 40);
		panel.add(updateFeesLabel);
		
		updateFeesButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	if(dbValidate.connectDatabase(sessionData)) {
            		dbValidate.updateFeesDivMismatch(sessionData);
            		JOptionPane.showMessageDialog(null, "Complete");
            	}
            }
        });
		
		height = height + 45;
		JButton deletePriodicAttButton = new JButton("Delete Attendance");
		deletePriodicAttButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		deletePriodicAttButton.setBounds(width, height, 200, 35);
        panel.add(deletePriodicAttButton);
        
        JLabel deletePriodicAttLabel = new JLabel("Delete duplicate students in attendance only if entered attendance by exam and not by month");
        deletePriodicAttLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        deletePriodicAttLabel.setBounds(260, height, 900, 40);
		panel.add(deletePriodicAttLabel);

		deletePriodicAttButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
				
            	try {
            		if (dbValidate.connectDatabase(sessionData)) {
    	            	dbValidate.deletePeriodicAttendance(sessionData);
	            		JOptionPane.showMessageDialog(null, "Success");
    				}
            	}
            	catch(Exception e1) {
            		commonObj.logException(e1);
            	}
            }
        });
		
		height = height + 45;
		JButton marathiFontButton = new JButton("Marathi Font");
		marathiFontButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		marathiFontButton.setBounds(width, height, 200, 35);
        panel.add(marathiFontButton);

        JLabel marathiFontLabel = new JLabel("To accept remark in marathi font");
        marathiFontLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        marathiFontLabel.setBounds(260, height, 900, 40);
		panel.add(marathiFontLabel);
		
		marathiFontButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	if(dbValidate.connectDatabase(sessionData)) {
            		try {
						dbValidate.alterDBForMarathiFont(sessionData);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Failed");
					}
            		JOptionPane.showMessageDialog(null, "Complete");
            	}
            }
        });
		
		height = height + 45;
		JButton deleteSummativeButton = new JButton("Delete Summative");
		deleteSummativeButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		deleteSummativeButton.setBounds(width, height, 200, 35);
        panel.add(deleteSummativeButton);

        JLabel deleteSummativeLabel = new JLabel("Delete Summative marks entered on marks allotment for IX,X,XI,XII for "
        		+ "academic year "+commonObj.getAcademicYear(commonObj.getCurrentDate()));
        deleteSummativeLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        deleteSummativeLabel.setBounds(260, height, 900, 40);
		panel.add(deleteSummativeLabel);
		
		deleteSummativeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	if(dbValidate.connectDatabase(sessionData)) {
            		try {
						dbValidate.deleteSummativeMarksAlloted(sessionData, commonObj.getAcademicYear(commonObj.getCurrentDate()));
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Failed");
					}
            		JOptionPane.showMessageDialog(null, "Complete");
            	}
            }
        });
		
		height = height + 45;
		JButton resultPDFHeaderButton = new JButton("Result PDF Header");
		resultPDFHeaderButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		resultPDFHeaderButton.setBounds(width, height, 200, 35);
        panel.add(resultPDFHeaderButton);
        
        JLabel resultPDFHeaderLabel = new JLabel("Adjust Result PDF Header space as needed");
        resultPDFHeaderLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        resultPDFHeaderLabel.setBounds(260, height, 900, 40);
		panel.add(resultPDFHeaderLabel);
		
		resultPDFHeaderButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	int reply = JOptionPane.showConfirmDialog(null, "Would You Like to update result header spacing ?", "Confirm update", JOptionPane.YES_NO_OPTION);
            	
				if (reply == JOptionPane.YES_OPTION && dbValidate.connectDatabase(sessionData)) {
					String fieldValue = JOptionPane.showInputDialog("Please Enter a result pdf header space as needed");
	            	boolean isSuccess = dbValidate.updateConfigField(sessionData, false, "RESULT_PDF_HEADER_SPACE", fieldValue);
	            	if(isSuccess) {
	            		JOptionPane.showMessageDialog(null, "Result Header Space updation to "+fieldValue+" completed");
	            	}
	            	else {
	            		JOptionPane.showMessageDialog(null, "Result Header Space updation to "+fieldValue+" failed");
	            	}
	            	
				}
            }
        });
		
		height = height + 45;
		JButton resultPDFAnnualSpaceButton = new JButton("Result PDF Academic Header");
		resultPDFAnnualSpaceButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		resultPDFAnnualSpaceButton.setBounds(width, height, 200, 35);
        panel.add(resultPDFAnnualSpaceButton);
        
        JLabel resultPDFAnnualSpaceLabel = new JLabel("Adjust Result PDF Academic Year Header space as needed");
        resultPDFAnnualSpaceLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        resultPDFAnnualSpaceLabel.setBounds(260, height, 900, 40);
		panel.add(resultPDFAnnualSpaceLabel);
		
		resultPDFAnnualSpaceButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	int reply = JOptionPane.showConfirmDialog(null, "Would You Like to update result Academic year header spacing ?", "Confirm update", JOptionPane.YES_NO_OPTION);
            	
				if (reply == JOptionPane.YES_OPTION && dbValidate.connectDatabase(sessionData)) {
					String fieldValue = JOptionPane.showInputDialog("Please Enter a result pdf header space as needed");
	            	boolean isSuccess = dbValidate.updateConfigField(sessionData, false, "RESULT_PDF_ANNUAL_YEAR_HEADER_SPACE", fieldValue);
	            	if(isSuccess) {
	            		JOptionPane.showMessageDialog(null, "Result Academic Header Space updation to "+fieldValue+" completed");
	            	}
	            	else {
	            		JOptionPane.showMessageDialog(null, "Result Academic Header Space updation to "+fieldValue+" failed");
	            	}
	            	
				}
            }
        });
		
		height = height + 45;
		JButton resultStampSpaceButton = new JButton("Result Stamp Space");
		resultStampSpaceButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		resultStampSpaceButton.setBounds(width, height, 200, 35);
        panel.add(resultStampSpaceButton);
        
        JLabel resultStampSpaceLabel = new JLabel("Result Stamp space as needed (put negative valuse if required)");
        resultStampSpaceLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        resultStampSpaceLabel.setBounds(260, height, 900, 40);
		panel.add(resultStampSpaceLabel);
		
		resultStampSpaceButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	int reply = JOptionPane.showConfirmDialog(null, "Would You Like to update result stamp spacing ?", "Confirm update", JOptionPane.YES_NO_OPTION);
            	
				if (reply == JOptionPane.YES_OPTION && dbValidate.connectDatabase(sessionData)) {
					String fieldValue = JOptionPane.showInputDialog("Please Enter a result stamp spacing as needed");
	            	boolean isSuccess = dbValidate.updateConfigField(sessionData, false, "RESULT_PDF_STAMP_SPACE", fieldValue);
	            	if(isSuccess) {
	            		JOptionPane.showMessageDialog(null, "Result Stamp Space updation to "+fieldValue+" completed");
	            	}
	            	else {
	            		JOptionPane.showMessageDialog(null, "Result Stamp Space updation to "+fieldValue+" failed");
	            	}
	            	
				}
            }
        });
		
		
		height = height + 45;
		JButton feesReportIssueButton = new JButton("Fees Report Issue");
		feesReportIssueButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		feesReportIssueButton.setBounds(width, height, 200, 35);
        panel.add(feesReportIssueButton);
		
        String yearList[] = { academicYear, nextYear };
		final JComboBox year_combo = new JComboBox(yearList);
		year_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		year_combo.setBounds(260, height+10, 100, 25);
		panel.add(year_combo);
		
        JLabel feesReportIssueLabel = new JLabel("Fees Report not getting generated");
        feesReportIssueLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        feesReportIssueLabel.setBounds(400, height, 900, 40);
		panel.add(feesReportIssueLabel);
		
		feesReportIssueButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	String academicSel = year_combo.getSelectedItem().toString();
            	int reply = JOptionPane.showConfirmDialog(null, "Would You Like to update data for Fees Report ?", "Confirm update", JOptionPane.YES_NO_OPTION);
            	
				if (reply == JOptionPane.YES_OPTION && dbValidate.connectDatabase(sessionData)) {
	            	boolean isSuccess;
					try {
						isSuccess = dbValidate.updateFeesReportMissingData(sessionData, academicSel);
						if(isSuccess) {
		            		JOptionPane.showMessageDialog(null, "update data for Fees Report completed");
		            	}
		            	else {
		            		JOptionPane.showMessageDialog(null, "update data for Fees Report failed");
		            	}
					} catch (SQLException e1) {
					}
				}
            }
        });

		height = height + 45;
		JButton insertNewFeeDataButton = new JButton("Insert New Fee Data");
		insertNewFeeDataButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		insertNewFeeDataButton.setBounds(width, height, 200, 35);
        panel.add(insertNewFeeDataButton);
        
        JLabel insertNewFeeDataLabel = new JLabel("Insert New Fee Record for Fee");
        insertNewFeeDataLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        insertNewFeeDataLabel.setBounds(260, height, 900, 40);
		panel.add(insertNewFeeDataLabel);
		
		insertNewFeeDataButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	int reply = JOptionPane.showConfirmDialog(null, "Would You Like to insert record into Fee "
            			+ "\n for academic year "+commonObj.getAcademicYear(commonObj.getCurrentDate())+"?", 
            			"Confirm Insert", JOptionPane.YES_NO_OPTION);
            	
				if (reply == JOptionPane.YES_OPTION && dbValidate.connectDatabase(sessionData)) {
					try {
						JFrame f = new JFrame("Add new students to Fee in progress. Please Don't Close");
						commonObj.startProgressBar(f);
						dbValidate.insertNewFeeStudents(sessionData, "", "", academicYear);
						commonObj.closeProgressBar(f);
	            		JOptionPane.showMessageDialog(null, "Insert Fee record completed");
					} catch (Exception e1) {
						commonObj.logException(e1);
					}
				}
            }
        });
		
		height = height + 45;
		JButton deleteDuplicateFeeButton = new JButton("Delete Duplicate Fee Data");
		deleteDuplicateFeeButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		deleteDuplicateFeeButton.setBounds(width, height, 200, 35);
        panel.add(deleteDuplicateFeeButton);
        
        String[] stdList = bundle.getString(sessionData.getSectionName().toUpperCase() + "_STD").split(",");
		final JComboBox std_combo = new JComboBox(stdList);
		std_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		std_combo.setBounds(260, height+10, 100, 25);
		panel.add(std_combo);
		
        JLabel deleteDuplicateFeeLabel = new JLabel("Delete Duplicate Record from Fee");
        deleteDuplicateFeeLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        deleteDuplicateFeeLabel.setBounds(400, height, 900, 40);
		panel.add(deleteDuplicateFeeLabel);
		
		deleteDuplicateFeeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	String stdSel = std_combo.getSelectedItem().toString();
            	int reply = JOptionPane.showConfirmDialog(null, "Would You Like to delete duplicate record from Fee "
            			+ "std "+stdSel+" \n for academic year "+commonObj.getAcademicYear(commonObj.getCurrentDate())+"?", 
            			"Confirm delete", JOptionPane.YES_NO_OPTION);
            	
				if (reply == JOptionPane.YES_OPTION && dbValidate.connectDatabase(sessionData)) {
					try {
						JFrame f = new JFrame("Delete duplicate Fee data in progress. Please Don't Close");
						commonObj.startProgressBar(f);
						dbValidate.deleteDuplicateData(sessionData, academicYear, stdSel, "", "fees_data_mandatory", "STD_1", 
								"DIV_1", f);
						commonObj.closeProgressBar(f);
						JOptionPane.showMessageDialog(null, "delete duplicate record completed");
					} catch (Exception e1) {
						commonObj.logException(e1);
					}
				}
            }
        });
		
		height = height + 45;
		JButton updateNameInFeesButton = new JButton("Update Name in Fees Data");
		updateNameInFeesButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		updateNameInFeesButton.setBounds(width, height, 200, 35);
        panel.add(updateNameInFeesButton);

        JLabel updateNameInFeesLabel = new JLabel("Update Name, Roll No in fees data");
        updateNameInFeesLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        updateNameInFeesLabel.setBounds(260, height, 900, 40);
		panel.add(updateNameInFeesLabel);
		
		updateNameInFeesButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	if(dbValidate.connectDatabase(sessionData)) {
            		dbValidate.updateNameInFeesData(sessionData);
            		JOptionPane.showMessageDialog(null, "Complete");
            	}
            }
        });
		
		height = height + 45;
		JButton deleteDuplicateRecordButton = new JButton("Delete Duplicate Record");
		deleteDuplicateRecordButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		deleteDuplicateRecordButton.setBounds(width, height, 200, 35);
        panel.add(deleteDuplicateRecordButton);
        
        String tableList[] = { "class_allotment", "marks_entry", "result_data" };
        final JComboBox table_combo = new JComboBox(tableList);
        table_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        table_combo.setBounds(260, height+10, 160, 25);
		panel.add(table_combo);
		
		final JComboBox std1_combo = new JComboBox(stdList);
		std1_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		std1_combo.setBounds(440, height+10, 100, 25);
		panel.add(std1_combo);
		
        JLabel deleteDuplicateRecordLabel = new JLabel("Delete Duplicate Record from selected category");
        deleteDuplicateRecordLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        deleteDuplicateRecordLabel.setBounds(580, height, 900, 40);
		panel.add(deleteDuplicateRecordLabel);
		
		deleteDuplicateRecordButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	String stdSel = std1_combo.getSelectedItem().toString();
            	String tableName = table_combo.getSelectedItem().toString();
            	int reply = JOptionPane.showConfirmDialog(null, "Would You Like to delete duplicate record from "+tableName+" "
            			+ "std "+stdSel+" \n for academic year "+commonObj.getAcademicYear(commonObj.getCurrentDate())+"?", 
            			"Confirm delete", JOptionPane.YES_NO_OPTION);
            	
				if (reply == JOptionPane.YES_OPTION && dbValidate.connectDatabase(sessionData)) {
	            	boolean isSuccess;
					try {
						JFrame f = new JFrame("Delete duplicate "+tableName+" data in progress. Please Don't Close");
						commonObj.startProgressBar(f);
						isSuccess = dbValidate.deleteDuplicateRecordInTable(sessionData, 
								commonObj.getAcademicYear(commonObj.getCurrentDate()), tableName, stdSel, f);
						commonObj.closeProgressBar(f);
						if(isSuccess) {
		            		JOptionPane.showMessageDialog(null, "delete duplicate record completed");
		            	}
		            	else {
		            		JOptionPane.showMessageDialog(null, "delete duplicate record failed");
		            	}
					} catch (SQLException e1) {
					}
				}
            }
        });
		
		height = height + 45;
		JButton updatePresentStdFromNumberButton = new JButton("Update Present STD");
		updatePresentStdFromNumberButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		updatePresentStdFromNumberButton.setBounds(width, height, 200, 35);
        panel.add(updatePresentStdFromNumberButton);

        JLabel updatePresentStdFromNumberLabel = new JLabel("Update STD if Agewise Strength report fails");
        updatePresentStdFromNumberLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        updatePresentStdFromNumberLabel.setBounds(260, height, 900, 40);
		panel.add(updatePresentStdFromNumberLabel);
		
		updatePresentStdFromNumberButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	if(dbValidate.connectDatabase(sessionData)) {
            		try {
						dbValidate.updateStdFromNumber(sessionData);
					} catch (SQLException e1) {
					}
            		JOptionPane.showMessageDialog(null, "Complete");
            	}
            }
        });
		
		height = height + 45;
		JButton rollNoUpdateInFeeButton = new JButton("Roll No. Update in Fee");
		rollNoUpdateInFeeButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		rollNoUpdateInFeeButton.setBounds(width, height, 200, 35);
        panel.add(rollNoUpdateInFeeButton);

        JLabel rollNoUpdateInFeeLabel = new JLabel("Update Roll No. in fees data for "+academicYear);
        rollNoUpdateInFeeLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        rollNoUpdateInFeeLabel.setBounds(260, height, 900, 40);
		panel.add(rollNoUpdateInFeeLabel);
		
		rollNoUpdateInFeeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	if(dbValidate.connectDatabase(sessionData)) {
            		try {
            			JFrame f = new JFrame("Updating Roll No for Fee Data. Please Don't Close");
						commonObj.startProgressBar(f);
						dbValidate.updateRollNoInFee(sessionData, academicYear, f);
						commonObj.closeProgressBar(f);
					} catch (SQLException e1) {
					}
            		JOptionPane.showMessageDialog(null, "Complete");
            	}
            }
        });
		
		height = height + 45;
		JButton deleteUnwantedColumnButton = new JButton("Delete TUTITION_FEES");
		deleteUnwantedColumnButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		deleteUnwantedColumnButton.setBounds(width, height, 200, 35);
        panel.add(deleteUnwantedColumnButton);

        JLabel deleteUnwantedColumnLabel = new JLabel("Delete TUTITION_FEES unwanted column");
        deleteUnwantedColumnLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        deleteUnwantedColumnLabel.setBounds(260, height, 900, 40);
		panel.add(deleteUnwantedColumnLabel);
		
		deleteUnwantedColumnButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	if(dbValidate.connectDatabase(sessionData)) {
            		try {
						dbValidate.deleteColumn(sessionData, "");
					} catch (SQLException e1) {
					}
            		JOptionPane.showMessageDialog(null, "Complete");
            	}
            }
        });
		
		height = height + 45;
		JButton changeColumnSizeButton = new JButton("Column Size");
		changeColumnSizeButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		changeColumnSizeButton.setBounds(width, height, 200, 35);
        panel.add(changeColumnSizeButton);

        JLabel changeColumnSizeLabel = new JLabel("Change Column size in Config and Marks Entry");
        changeColumnSizeLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        changeColumnSizeLabel.setBounds(260, height, 900, 40);
		panel.add(changeColumnSizeLabel);
		
		changeColumnSizeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	if(dbValidate.connectDatabase(sessionData)) {
            		try {
						dbValidate.changeColumnSize(sessionData);
					} catch (SQLException e1) {
					}
            		JOptionPane.showMessageDialog(null, "Complete");
            	}
            }
        });
		
		if(sessionData.getUserName().equalsIgnoreCase("PRP")) {
			height = height + 45;
			JButton removeCasualMoneyButton = new JButton("Remove CAUTION MONEY");
			removeCasualMoneyButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			removeCasualMoneyButton.setBackground(Color.orange);
			removeCasualMoneyButton.setBounds(width, height, 200, 35);
	        panel.add(removeCasualMoneyButton);

	        JLabel removeCasualMoneyLabel = new JLabel("Remove CAUTION MONEY data for students not admitted this year");
	        removeCasualMoneyLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
	        removeCasualMoneyLabel.setBounds(260, height, 900, 40);
			panel.add(removeCasualMoneyLabel);
			
			removeCasualMoneyButton.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	            	if(dbValidate.connectDatabase(sessionData)) {
	            		try {
							dbValidate.removeCautionMoney(sessionData, academicYear);
						} catch (SQLException e1) {
						}
	            		JOptionPane.showMessageDialog(null, "Complete");
	            	}
	            }
	        });
			
			height = height + 45;
			JButton clrFeeRecordButton = new JButton("Clear Fee Record");
			clrFeeRecordButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			clrFeeRecordButton.setBackground(Color.orange);
			clrFeeRecordButton.setBounds(width, height, 200, 35);
	        panel.add(clrFeeRecordButton);

	        JLabel clrFeeRecordButtonLabel = new JLabel("Clear Fee Record for XII COM & Sci for 2019-20");
	        clrFeeRecordButtonLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
	        clrFeeRecordButtonLabel.setBounds(260, height, 900, 40);
			panel.add(clrFeeRecordButtonLabel);
			
			clrFeeRecordButton.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	            	if(dbValidate.connectDatabase(sessionData)) {
	            		try {
	            			int reply = JOptionPane.showConfirmDialog(null, "Would You Like to delete fee record "
	            					+ "for "+sessionData.getSectionName()+" 2019-20 ?", "Confirm update", JOptionPane.YES_NO_OPTION);
	                    	
	        				if (reply == JOptionPane.YES_OPTION && dbValidate.connectDatabase(sessionData)) {
	        					dbValidate.clrFeeRecord(sessionData);
	        				}
						} catch (SQLException e1) {
						}
	            		JOptionPane.showMessageDialog(null, "Complete");
	            	}
	            }
	        });
			
			height = height + 45;
			JButton importBackupDataButton = new JButton("Import Config BackUp");
			importBackupDataButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			importBackupDataButton.setBounds(width, height, 200, 35);
	        panel.add(importBackupDataButton);

	        JLabel importBackupDataLabel = new JLabel("Import Config Data From SQL");
	        importBackupDataLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
	        importBackupDataLabel.setBounds(260, height, 900, 40);
			panel.add(importBackupDataLabel);
			
			importBackupDataButton.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	            	if(dbValidate.connectDatabase(sessionData)) {
	            		try {
	            			String[] arguments = new String[] {};
	            			ImportBackupDataFromSQLLoginView.main(arguments);
						} catch (Exception e1) {
						}
	            		JOptionPane.showMessageDialog(null, "Complete");
	            	}
	            }
	        });
		}
		
		height = height + 45;
		JButton processLcStudentButton = new JButton("Process LC Student");
		processLcStudentButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		processLcStudentButton.setBounds(width, height, 200, 35);
        panel.add(processLcStudentButton);
        
        String processLcStudentList[] = { "TRUE","FALSE" };
        final JComboBox processLcStudent_combo = new JComboBox(processLcStudentList);
        processLcStudent_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        processLcStudent_combo.setBounds(260, height+5, 160, 25);
		panel.add(processLcStudent_combo);

        JLabel processLcStudentLabel = new JLabel("Allow result update for left student");
        processLcStudentLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        processLcStudentLabel.setBounds(460, height, 900, 40);
		panel.add(processLcStudentLabel);
		
		processLcStudentButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	if(dbValidate.connectDatabase(sessionData)) {
            		try {
            			String processLcStudentValue = processLcStudent_combo.getSelectedItem().toString();
            			
            			if(sessionData.getConfigMap().get("PROCESS_LC_STUDENT") != null) {
            				dbValidate.updateConfigField(sessionData, true, "PROCESS_LC_STUDENT", processLcStudentValue);
            			}
            			else {
            				dbValidate.updateConfigField(sessionData, false, "PROCESS_LC_STUDENT", processLcStudentValue);
            			}
					} catch (Exception e1) {
					}
            		JOptionPane.showMessageDialog(null, "Complete");
            	}
            }
        });
		
		height = height + 45;
		JButton deleteBlankGrButton = new JButton("Delete Blank GR Record");
		deleteBlankGrButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		deleteBlankGrButton.setBounds(width, height, 200, 35);
        panel.add(deleteBlankGrButton);
        
        String yearList1[] = { academicYear, Common.getPreviousYear(academicYear), nextYear };
		final JComboBox year_combo1 = new JComboBox(yearList1);
		year_combo1.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		year_combo1.setBounds(260, height+05, 100, 25);
		panel.add(year_combo1);
		
        JLabel deleteBlankGrLabel = new JLabel("Delete Blank GR Record from Fees");
        deleteBlankGrLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        deleteBlankGrLabel.setBounds(400, height, 900, 40);
		panel.add(deleteBlankGrLabel);
		
		deleteBlankGrButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	String academicSel = year_combo1.getSelectedItem().toString();
            	int reply = JOptionPane.showConfirmDialog(null, "Would You Like to delete blank GR from Fees ?", "Confirm update", JOptionPane.YES_NO_OPTION);
            	
				if (reply == JOptionPane.YES_OPTION && dbValidate.connectDatabase(sessionData)) {
	            	boolean isSuccess;
					try {
						isSuccess = dbValidate.deleteBlankGr(sessionData, academicSel, "FEES_DATA_MANDATORY");
						if(isSuccess) {
		            		JOptionPane.showMessageDialog(null, "Delete Blank GR Record from Fees completed");
		            	}
		            	else {
		            		JOptionPane.showMessageDialog(null, "Delete Blank GR Record from Fees failed");
		            	}
					} catch (SQLException e1) {
					}
				}
            }
        });
		
		height = height + 45;
		JButton resolveDoubleConcessionButton = new JButton("Double Concession");
		resolveDoubleConcessionButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		resolveDoubleConcessionButton.setBounds(width, height, 200, 35);
        panel.add(resolveDoubleConcessionButton);
        
        JLabel resolveDoubleConcessionLabel = new JLabel("Resolve double concession amount issue");
        resolveDoubleConcessionLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        resolveDoubleConcessionLabel.setBounds(260, height, 900, 40);
		panel.add(resolveDoubleConcessionLabel);
		
		resolveDoubleConcessionButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	int reply = JOptionPane.showConfirmDialog(null, "Would You Like to resolve double concession amount issue ?", "Confirm update", JOptionPane.YES_NO_OPTION);
            	
				if (reply == JOptionPane.YES_OPTION && dbValidate.connectDatabase(sessionData)) {
	            	boolean isSuccess;
					try {
						isSuccess = dbValidate.resolveDoubleConcession(sessionData);
						if(isSuccess) {
		            		JOptionPane.showMessageDialog(null, "Double concession amount issue completed");
		            	}
		            	else {
		            		JOptionPane.showMessageDialog(null, "Double concession amount issue failed");
		            	}
					} catch (Exception e1) {
					}
				}
            }
        });

      ///home//////////////
        final ImageIcon iconhome = new ImageIcon(img_path + img_home);
        JLabel labelhome = new JLabel(iconhome);
        labelhome.setBounds(45, 10, 60, 60);
        panel.add(labelhome);
        
        labelhome.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				try {
					frame.setVisible(false);
					new Welcome(sessionData, user_name, user_role);
				} catch (Exception e) {
					logger.info(e);
				}
			}
		});
		
        // /logout//////////////
        final ImageIcon iconlogout = new ImageIcon(img_path + img_logout);
        JLabel labellogout = new JLabel(iconlogout);
        labellogout.setBounds((screenWidth - 80), 10, 60, 60);
        panel.add(labellogout);
        
        labellogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				boolean deleteFlag = false;
				try {
					if (dbValidate.connectDatabase(sessionData)) {
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, "");
						dbValidate.closeDatabase(sessionData);

						frame.setVisible(false);
						String[] arguments = new String[] {""};
		                LoginView.main(arguments);
					}
				} catch (Exception e1) {
					logger.info("Exception logoutButton ===>>>" + e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});
        
		JButton submitButton = new JButton("Submit") {
			/**
			 * for background image
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				Image img = new ImageIcon(img_path+"submit.jpg").getImage();
				Dimension size = new Dimension(100, 33);
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				g.drawImage(img, 0, 0, null);
			}
		};
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		submitButton.setBounds((screenWidth / 2), (screenHeight / 2 + 250), 80, 25);
		submitButton.setBounds((screenWidth / 2), (screenHeight / 2 - 0), 80, 25);
//		panel.add(submitButton);

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName 	= "";
				String password 	= "";
				String confPass 	= "";
				boolean validFlag	= true;
				
				if(userName.length() > 40)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("User Name",userName, 40));
				}
				else if(commonObj.checkComma(userName) || userName.equalsIgnoreCase(""))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid User Name without comma");
				}
				else if(commonObj.checkComma(password) || password.equalsIgnoreCase(""))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid New Password");
				}
				else if(!commonObj.validatePassword(password))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "New Password must contain one digit, one lower case char, length should be within 6 to 15 chars");
				}
				else if(!password.contentEquals(confPass))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "New Password & confirm password are different");
				}
				if (validFlag) {
					boolean passwordUpdated = false;
					boolean validateUser = false;
					try {
						if(dbValidate.connectDatabase(sessionData)){
			            	passwordUpdated = dbValidate.updateForgotPassword(sessionData, userName, password);
				            if(passwordUpdated){
				            	commonObj.showMessageDialog("Password for " +userName+ " updated successfully");
				            } else {
				            	commonObj.showMessageDialog("Password update for " +userName+ " failed. Please try again");
				            }
				            frame.setVisible(false);
			            	String[] arguments = new String[] {""};
			                LoginView.main(arguments);
						}
					} catch (Exception e1) {
						logger.info("update password failed Exception e1 ===>>>" + e1);
						commonObj.showMessageDialog("Update Password failed...");
					} finally {
			        	dbValidate.closeDatabase(sessionData);
			        }
				}
			}
		});

	}

}
