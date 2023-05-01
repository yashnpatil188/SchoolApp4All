package org.com.maauli;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
// import com.lowagie.text.DocumentException;
// import com.lowagie.text.pdf.TextField;
import org.com.accesser.SessionData;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class MarksEntryStudent extends JFrame {

	private static MarksEntryStudent marksEntryStudent = new MarksEntryStudent();
	
	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JFrame frame = null;

	static JComboBox studentcombo;

	static int retCount = 0;

	static int scrollHeight = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

	static List<String> studMarkList = new ArrayList();

	static List<String> findSubList = new ArrayList();

	static boolean setEditable = false;

	static String stdClass = "";

	static String academicYearClass = "";

	static String selectStd = "Select";

	static String selectDiv = "Select";

	static String selectYear = "Year";

	static String divClass = "";

	static String user_name = "";

	static String user_role = "";

	static String fromClass, toClass;

	static String lastClass, firstClass, fatherClass;

	static DBValidate dbValidate = new DBValidate();

	static SessionData sessionData 	= new SessionData();
	
	static Common commonObj = new Common();

	static String std = "";

	static String div = "";

	static String section = "";

	static String secName = "";

	static String img_path = "";

	static String img_home = "";

	static String img_logo = "";

	static String img_myaccount = "";

	static String img_logout = "";

	static String img_titleband = "";

	static String img_leftband = "";

	static String img_menuband = "";

	static String img_mainband = "";
	
    static String app_header = "";
    
    static String app_header_0 = "";

	static String examClass = "Select";
	
	static String examCategory = "";

	static String subjectClass = "";

	static String typeClass = "Select";
	
	static String typeClassDisp = "Select";

	static String examType = "";
	
	static LinkedHashMap<String,LinkedHashMap<String, String>> maxSubMarks;
	
	static boolean updateFlag = true;
	
	static String fromDateClass = "";

	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static Logger logger = Logger.getLogger(MarksEntryStudent.class.getName());
	
	static TreeMap<String, String> studentLCMap = new TreeMap<String, String>();
	
	static LinkedHashMap<String, String> grMarksDataMap;
	
	static LinkedHashMap<String,LinkedHashMap<String, String>> studentOptSubAllotMap;
	
	static String app_header_0_fontName = "";
    static int app_header_0_fontSize = 0;
    static int app_header_0_widthSpace = 0;
    static int app_header_0_heightSpace = 0;
    static String app_header_fontName = "";
    static int app_header_fontSize = 0;
    static int app_header_widthSpace = 0;
    static int app_header_heightSpace = 0;
    static String app_header_2 = "";
    static String app_header_2_fontName = "";
    static int app_header_2_fontSize = 0;
    static int app_header_2_widthSpace = 0;
    static int app_header_2_heightSpace = 0;

    /*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private MarksEntryStudent() {
	}

	/* Static 'instance' method */
	public static MarksEntryStudent getInstance() {
		return marksEntryStudent;
	}
	
	/* Other methods protected by singleton-ness */
	protected static void getMarksEntryStudent(final SessionData sessionData1, String retStd, List<String> retStudMarksList,
			String academicYear, boolean retEditable, String retDiv,
			String retFrom, String retTo, String retLast, String retFirst,
			String retFather, String retExam, String retSub, String retType,
			String sec, String retUserName, String retUserRole, String retDate, final String retGrNo) {

		grMarksDataMap  = new LinkedHashMap<String, String>();
		LinkedHashMap<String,LinkedHashMap<String, String>> marksSem1DataMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
		LinkedHashMap<String,LinkedHashMap<String, String>> marksSem2DataMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
		studentOptSubAllotMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
		
		String sem = "", max = "";
		int stdInt = 0;
		System.gc();
		maxSubMarks = new LinkedHashMap<String,LinkedHashMap<String, String>>();
		LinkedHashMap<String,String> maxMarksDetailsMap = new LinkedHashMap<String,String>();
		studentLCMap.clear();
		fromDateClass = retDate;
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		section = sec;
		studMarkList.clear();
		std = bundle.getString(section.toUpperCase() + "_STD");
		div = bundle.getString(section.toUpperCase() + "_DIV");
		secName = bundle.getString(section.toUpperCase() + "_SEC");
		examCategory = bundle.getString("EXAM_CATEGORY");
		examCategory = examCategory.substring(0, examCategory.lastIndexOf(","));
		
		String examTypeList = "EXAM_TYPE_5";
		if((retStd.equalsIgnoreCase("X") || retStd.equalsIgnoreCase("XI") || 
				retStd.equalsIgnoreCase("XII")) && retExam.equalsIgnoreCase("Semester 1")){
			examTypeList = "EXAM_TYPE_1";
		}
		else if((retStd.equalsIgnoreCase("X") || retStd.equalsIgnoreCase("XI") || 
				retStd.equalsIgnoreCase("XII")) && retExam.equalsIgnoreCase("Semester 2")){
			examTypeList = "EXAM_TYPE_2";
		}
		else if((retStd.equalsIgnoreCase("IX")) && retExam.equalsIgnoreCase("Semester 1")){
			examTypeList = "EXAM_TYPE_3";
		}
		else if((retStd.equalsIgnoreCase("IX")) && retExam.equalsIgnoreCase("Semester 2")){
			examTypeList = "EXAM_TYPE_4";
		}
		examType = bundle.getString(examTypeList);
		
		img_path = bundle.getString("IMAGE_PATH");
		img_home = bundle.getString("IMAGE_HOME");
		img_logo = bundle.getString("IMAGE_LOGO");
		img_myaccount = bundle.getString("IMAGE_MYACCOUNT");
		img_logout = bundle.getString("IMAGE_LOGOUT");
		img_titleband = bundle.getString("IMAGE_TITLEBAND");
		img_leftband = bundle.getString("IMAGE_LEFTBAND");
		img_menuband = bundle.getString("IMAGE_MENUBAND");
		img_mainband = bundle.getString("IMAGE_MAINBAND");
		app_header = bundle.getString("APP_HEADER_"+sessionData.getAppType());
        app_header_0 = bundle.getString("APP_HEADER_0_"+sessionData.getAppType());
        app_header_0_fontName = bundle.getString("APP_HEADER_0_FONTNAME_"+sessionData.getAppType());
        app_header_0_fontSize = Integer.parseInt(bundle.getString("APP_HEADER_0_FONTSIZE_"+sessionData.getAppType()));
    	app_header_0_widthSpace = Integer.parseInt(bundle.getString("APP_HEADER_0_WIDTHSPACE_"+sessionData.getAppType()));
    	app_header_0_heightSpace = Integer.parseInt(bundle.getString("APP_HEADER_0_HEIGHTSPACE_"+sessionData.getAppType()));
    	app_header_fontName = bundle.getString("APP_HEADER_FONTNAME_"+sessionData.getAppType());
        app_header_fontSize = Integer.parseInt(bundle.getString("APP_HEADER_FONTSIZE_"+sessionData.getAppType()));
    	app_header_widthSpace = Integer.parseInt(bundle.getString("APP_HEADER_WIDTHSPACE_"+sessionData.getAppType()));
    	app_header_heightSpace = Integer.parseInt(bundle.getString("APP_HEADER_HEIGHTSPACE_"+sessionData.getAppType()));
    	app_header_2 = bundle.getString("APP_HEADER_2_"+sessionData.getAppType());
        app_header_2_fontName = bundle.getString("APP_HEADER_2_FONTNAME_"+sessionData.getAppType());
        app_header_2_fontSize = Integer.parseInt(bundle.getString("APP_HEADER_2_FONTSIZE_"+sessionData.getAppType()));
    	app_header_2_widthSpace = Integer.parseInt(bundle.getString("APP_HEADER_2_WIDTHSPACE_"+sessionData.getAppType()));
    	app_header_2_heightSpace = Integer.parseInt(bundle.getString("APP_HEADER_2_HEIGHTSPACE_"+sessionData.getAppType()));

		if (!retExam.equalsIgnoreCase("")) {
			examClass = retExam;
		}
		/*else{
			examClass = "Select";
		}*/
		subjectClass = retSub;
		typeClassDisp = retType;
		if(!retType.equalsIgnoreCase("")){
			if(retType.contains("SUM_")){
				retType = retType.substring(retType.indexOf("_")+1)+"1";
			}
			typeClass = retType;
		}
		fromClass = retFrom;
		toClass = retTo;
		academicYearClass = academicYear;
		lastClass = retLast;
		firstClass = retFirst;
		fatherClass = retFather;
		stdClass = retStd;
		divClass = retDiv;
		setEditable = retEditable;

		if (!retStd.equalsIgnoreCase("")) {
			selectStd = stdClass;
		}
		else{
			selectStd = "Select";
		}
		if (!retDiv.equalsIgnoreCase("")) {
			selectDiv = divClass;
		}
		else{
			selectDiv = "Select";
		}

		try {
			if(dbValidate.connectDatabase(sessionData)){
				
				if(!retGrNo.equalsIgnoreCase("")){
					String studentInfo[] = dbValidate.viewStudentInfo(sessionData1, retGrNo, sec);
					stdClass = studentInfo[26];
					stdInt = commonObj.RomanToInteger(stdClass);
					marksSem1DataMap = dbValidate.obtainedMarksForAllExcel(sessionData, stdClass, divClass,academicYearClass,section,"","Semester 1",subjectClass,typeClass, retGrNo);
					grMarksDataMap = marksSem1DataMap.get(retGrNo);
					marksSem2DataMap = dbValidate.obtainedMarksForAllExcel(sessionData, stdClass, divClass,academicYearClass,section,"","Semester 2",subjectClass,typeClass, retGrNo);
					grMarksDataMap.putAll(marksSem2DataMap.get(retGrNo));
					
					stdClass = grMarksDataMap.get("std");
					divClass = grMarksDataMap.get("div");
					
					studentOptSubAllotMap = dbValidate.getStudentOptSubAllot(sessionData1, academicYearClass, stdClass, divClass, false);
					retStudMarksList.add(retGrNo);
				}
				
				studentLCMap = dbValidate.findStudentLCList(sessionData1, "", stdClass, divClass, "", "", "", academicYearClass, "", "", section);
				
				dbValidate.newStudentsInMarksEntry(sessionData1, academicYearClass, retStd, retDiv, section);
				////findMarksEntryList//////
				if(retType.equalsIgnoreCase("FUT") || retType.equalsIgnoreCase("SUT") || retType.equalsIgnoreCase("TEST")){
					retType = "OBT";
				}
				else if(retType.equalsIgnoreCase("ACTIVITY") || retType.equalsIgnoreCase("PROJ")){
					retType = "PRACT";
				}
				else if(retType.equalsIgnoreCase("ACT")){
					retType = "ACTIVITY";
				}
				
				if(examClass.equalsIgnoreCase("Semester 1")){
					sem = "sem1";
				} else if(examClass.equalsIgnoreCase("Semester 2")){
					sem = "sem2";
				}
				
				if (retStudMarksList.size() > 0) {
					studMarkList = retStudMarksList;
					
					////////find max subject marks//////
					maxSubMarks = dbValidate.findMaxMarks(sessionData, "",retType, stdClass, subjectClass, academicYear);
				} else if (!subjectClass.equalsIgnoreCase("")) {
					studMarkList = dbValidate.findMarksEntryList(sessionData, academicYearClass, stdClass, divClass, examClass, subjectClass, 
							retType, section, lastClass.toUpperCase(), firstClass.toUpperCase(), fatherClass.toUpperCase());
					
					////////find max subject marks//////
					maxSubMarks = dbValidate.findMaxMarks(sessionData, "",retType, stdClass, subjectClass, academicYear);
				}
				
				if (maxSubMarks.size() < 1 && !retStd.equalsIgnoreCase("")) {
					commonObj.showMessageDialog("No Subjects found for std " + retStd);
				}
			}
		} catch (Exception e1) {
            commonObj.logException(e1);
        } finally {
        	dbValidate.closeDatabase(sessionData);
        }

		String maxMarksFromMap = max;
		
//		academicYearClass = commonObj.getAcademicYear(commonObj.getCurrentDate());;
//		logger.info(stdClass + "::" + academicYearClass);

		entrytCnt = 0;
		logger.info("entrytCnt===>" + entrytCnt);

		scrollHeight = (maxSubMarks.size() - 6) * 30; // to adjust the perfect scroll height
		if (scrollHeight < 0)
			scrollHeight = 0;

//		setVisible(false);
//		dispose();
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame = new JFrame("Welcome to "+sessionData1.getAppName());
		final Common commonObj = new Common();
		screenWidth = commonObj.screeWidth();
		screenHeight = commonObj.screeHeight();
		mainCentre = (screenWidth - 150) / 2;

		frame.setSize(screenWidth, screenHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel panelHome = new JPanel();

		frame.add(panelHome);
//		placeComponents(panel);



		panelHome.removeAll();///to remve entire panel
		panelHome.setLayout(new BorderLayout());
		// ///////////top panel////////////////////////////////////////
		JPanel titlePanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				
				Image img = new ImageIcon(img_path + img_titleband).getImage();
				Dimension size = new Dimension(screenWidth, 75);
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				g.drawImage(img, 0, 0, null);
			}
		};
		titlePanel.setLayout(null);

		// //////////////logo//////////////////
		final ImageIcon icon = new ImageIcon(img_path + img_logo);
		final JLabel label = new JLabel(icon);
		// label.setFont(new Font("Book Antiqua", Font.BOLD, 30));
		label.setBounds(45, 15, 55, 55);
		titlePanel.add(label);

		// /////////////title//////////////
		JLabel title_0 = new JLabel(app_header_0);
        title_0.setFont(new Font(app_header_0_fontName, Font.BOLD, app_header_0_fontSize));
        title_0.setBounds((screenWidth / 2) - app_header_0_widthSpace, app_header_0_heightSpace, screenWidth - 400, 50);
        titlePanel.add(title_0);
        
        JLabel title = new JLabel(app_header);
        title.setFont(new Font(app_header_fontName, Font.BOLD, app_header_fontSize));//38 default
        title.setBounds((screenWidth / 2) - app_header_widthSpace, app_header_heightSpace, screenWidth - 100, 50);
        titlePanel.add(title);
        
        JLabel title_2 = new JLabel(app_header_2);
        title_2.setFont(new Font(app_header_2_fontName, Font.BOLD, app_header_2_fontSize));//38 default
        title_2.setBounds((screenWidth / 2) - app_header_2_widthSpace, app_header_2_heightSpace, screenWidth - 360, 50);
        titlePanel.add(title_2);

		// /account//////////////
        final ImageIcon iconaccount = new ImageIcon(img_path + img_myaccount);
        JLabel labelaccount = new JLabel(iconaccount);
        labelaccount.setBounds((screenWidth - 150), 10, 60, 60);
        titlePanel.add(labelaccount);
        
        labelaccount.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				try {
					frame.setVisible(false);
					panelHome.removeAll();///to remve entire panel
					new MyAccount(sessionData);
				} catch (Exception e) {
					logger.info(e);
				}
			}
		});
        
        // /logout//////////////
        final ImageIcon iconlogout = new ImageIcon(img_path + img_logout);
        JLabel labellogout = new JLabel(iconlogout);
        labellogout.setBounds((screenWidth - 80), 10, 60, 60);
        titlePanel.add(labellogout);
        
        labellogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				boolean deleteFlag = false;
				try {
					if (dbValidate.connectDatabase(sessionData)) {
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
						dbValidate.closeDatabase(sessionData);

						frame.setVisible(false);
						panelHome.removeAll();///to remve entire panel
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

        panelHome.add(titlePanel, BorderLayout.NORTH);

        // ///////////left Band panel////////////////////////////////////////
        JPanel leftPanel = new JPanel() {

            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {

                Image img = new ImageIcon(img_path + img_leftband).getImage();
                Dimension size = new Dimension(150, screenHeight - 100);
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
                g.drawImage(img, 0, 0, null);
            }
        };
        leftPanel.setLayout(null);

        // /home//////////////
        final ImageIcon iconhome = new ImageIcon(img_path + img_home);
        JLabel labelhome = new JLabel(iconhome);
        labelhome.setBounds(45, 10, 60, 60);
        leftPanel.add(labelhome);
        
        labelhome.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				try {
					frame.setVisible(false);
					panelHome.removeAll();///to remve entire panel
					new Welcome(sessionData, user_name, user_role);
				} catch (Exception e) {
					logger.info(e);
				}
			}
		});

		JButton studentButton = new JButton("Student");
		studentButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		studentButton.setBounds(10, 100, 130, 35);
		leftPanel.add(studentButton);

		studentButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				panelHome.removeAll();///to remve entire panel
				new Student(sessionData, section, user_name, user_role);
			}
		});

		JButton staffButton = new JButton("Staff");
		staffButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		staffButton.setBounds(10, 150, 130, 35);
//		leftPanel.add(staffButton);

		staffButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// frame.setVisible(false);
				// new Welcome();
			}
		});

		JButton academicButton = new JButton("Academic");
		academicButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		academicButton.setBounds(10, 200, 130, 35);
		academicButton.setBackground(Color.GREEN);
		leftPanel.add(academicButton);

		academicButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				panelHome.removeAll();///to remve entire panel
				new Academic(sessionData, section, user_name, user_role);
			}
		});

		JButton accountButton = new JButton("Account");
		accountButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		accountButton.setBounds(10, 250, 130, 35);
//		leftPanel.add(accountButton);

		accountButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// frame.setVisible(false);
				// new Welcome();
			}
		});

		JButton searchButton = new JButton("Find");
		searchButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		searchButton.setBounds(10, 300, 130, 35);
		leftPanel.add(searchButton);

		searchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				LinkedHashMap<String,LinkedHashMap<String, String>> studMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
				new FindStudent(sessionData, "", "", "", "", "", "", studMap, section,
						user_name, user_role, false);
				frame.setVisible(false);
				panelHome.removeAll();///to remve entire panel
			}
		});

		panelHome.add(leftPanel, BorderLayout.WEST);

		// ///////////main panel////////////////////////////////////////
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		// ///////////menu band panel////////////////////////////////////////
		JPanel topbandPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				Image img = new ImageIcon(img_path + img_menuband).getImage();
				Dimension size = new Dimension(screenWidth - 150, 120);
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				// g.drawImage(img, 0, 0, null);
				g.drawImage(img, 0, 0, screenWidth - 150, 85, null);
			}
		};
		topbandPanel.setLayout(null);

		JLabel menuBandTitle = new JLabel(secName);
		menuBandTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		menuBandTitle.setForeground(Color.orange);
		menuBandTitle.setBounds(20, 0, 600, 30);
		topbandPanel.add(menuBandTitle);

		JLabel subMenuTitle = new JLabel("Academic");
		subMenuTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		subMenuTitle.setForeground(Color.orange);
		subMenuTitle.setBounds(15, 45, 120, 30);
		topbandPanel.add(subMenuTitle);

		JButton classAllotButton = new JButton("Class Allotment");
		classAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		classAllotButton.setBounds(130, 50, 150, 24);
		topbandPanel.add(classAllotButton);

		classAllotButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				panelHome.removeAll();///to remve entire panel
				new ClassAllotment(sessionData, "", "", "", "", "", "", null, false, "", "",
						"", section, user_name, user_role, "");
			}
		});

		JButton subAllotButton = new JButton("Subject Allotment");
		subAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		subAllotButton.setBounds(290, 50, 150, 24);// 620, 50, 150, 24
		topbandPanel.add(subAllotButton);

		subAllotButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				panelHome.removeAll();///to remve entire panel
				List subAllotList = new ArrayList();
				new MarksAllotment(sessionData, "", subAllotList, "", "", false, section,
						user_name, user_role, "");
			}
		});

		JButton marksButton = new JButton("Marks Entry");
		marksButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		marksButton.setBounds(450, 50, 150, 24);
		marksButton.setBackground(Color.GREEN);
		topbandPanel.add(marksButton);

		marksButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// frame.setVisible(false);
				// new Student();
			}
		});

		JButton resultPrintButton = new JButton("Result Print");
		resultPrintButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		resultPrintButton.setBounds(610, 50, 150, 24); // 300, 50, 150, 24
		topbandPanel.add(resultPrintButton);

		resultPrintButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            	frame.setVisible(false);
            	panelHome.removeAll();///to remve entire panel
                List findList = new ArrayList();
				new Result(sessionData, "", "", "", "", "", "",	findList, false, false, "", section, user_name, user_role, "", "", "", 
						findList, findList, findList);
            }
        });

		JButton attendanceButton = new JButton("Attendance");
		attendanceButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		attendanceButton.setBounds(770, 50, 150, 24); // 300, 50, 150, 24
		topbandPanel.add(attendanceButton);

		attendanceButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				List studentList = new ArrayList();
				new AttendanceUpdate(sessionData, "", "", "", section, user_name, user_role, studentList, 
						"","","","",false,"",true,"", false);
				frame.setVisible(false);
				panelHome.removeAll();///to remve entire panel
			}
		});

		JButton reportButton = new JButton("Reports");
		reportButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		reportButton.setBounds(930, 50, 150, 24);
		topbandPanel.add(reportButton);

		reportButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				LinkedHashMap studentHmap = new LinkedHashMap();
				new Reports(sessionData, "", "", "", section, user_name, user_role, studentHmap, "", "", "");
				frame.setVisible(false);
			}
		});

		mainPanel.add(topbandPanel, BorderLayout.EAST);
		// mainPanel.add(bottombandPanel, BorderLayout.SOUTH);

		// /////////////////bottombandPanel area//////////////////////////
		// JPanel panelWorkable = new JPanel(new BorderLayout());

		JPanel bottombandPanel = new JPanel(new BorderLayout());
		bottombandPanel.setPreferredSize(new Dimension(screenWidth - 150, screenHeight - 187));

		// ///////////find Panel/////////////
		final JPanel findPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				Image img = new ImageIcon(img_path + img_mainband).getImage();
				Dimension size = new Dimension(screenWidth - 156, 240);// change height to change scrolling area
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				g.drawImage(img, 0, 0, null);
			}
		};
		findPanel.setLayout(null);
		// /////////////ACADEMIV YEAR //////////////
		final JRadioButton academic_radio = new JRadioButton();
		academic_radio.setBounds(30, 14, 20, 20);
		academic_radio.setSelected(true);
		academic_radio.setEnabled(false);
		findPanel.add(academic_radio);

		JLabel academic_label = new JLabel("Academic :");
		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academic_label.setBounds(60, 00, 100, 50);
		findPanel.add(academic_label);

		String yearList = "";
		try {
			if (dbValidate.connectDatabase(sessionData)) {
				yearList = dbValidate.getAcademicYearList(sessionData, "HS_GENERAL_REGISTER", "ACADEMIC_YEAR").toString();
			}
		}  catch (Exception e12) {
			logger.info("Exception logoutButton ===>>>" + e12);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
		
		if(!yearList.contains(commonObj.getAcademicYear(commonObj.getCurrentDate()))){
			yearList = commonObj.getAcademicYear(commonObj.getCurrentDate()) + "," + yearList;
		}

		String academicYearList[] = yearList.split(",");
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		if(!academicYearClass.equalsIgnoreCase("")){
			academicYear_combo.setSelectedItem(academicYearClass);
		}
		academicYear_combo.setBounds(150, 12, 120, 25);
		findPanel.add(academicYear_combo);
		// //////////////////////////////////
		// /////////////Std//////////////
		final JRadioButton std_radio = new JRadioButton();
		std_radio.setBounds(310, 14, 20, 20);
		findPanel.add(std_radio);

		JLabel admittedStd_label = new JLabel("Std :");
		admittedStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_label.setBounds(340, 00, 70, 50);
		findPanel.add(admittedStd_label);

		std = selectStd + "," + std;
		String[] stdList = std.split(",");
		// String StdList[] = {selectStd,"IV","V","VI","VII","VIII","IX","X"};
		final JComboBox Std_combo = new JComboBox(stdList);
		Std_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		Std_combo.setBounds(400, 12, 90, 25);
		findPanel.add(Std_combo);

		// /////////////Div//////////////
		JLabel presentDiv_label = new JLabel("Div :");
		presentDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		presentDiv_label.setBounds(520, 00, 70, 50);
		findPanel.add(presentDiv_label);

		div = selectDiv + "," + div;
		String[] divList = div.split(",");
		// String presentDivList[] = {"Select","A","B","C","D","E"};
		final JComboBox presentDiv_combo = new JComboBox(divList);
		presentDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		presentDiv_combo.setBounds(580, 12, 90, 25);
		findPanel.add(presentDiv_combo);

		// //////Roll No//////////////////////
		/*JLabel rollNo_label = new JLabel("Roll No. :");
		rollNo_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		rollNo_label.setBounds(700, 00, 70, 50);
		findPanel.add(rollNo_label);

		JLabel from_label = new JLabel("From");
		from_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		from_label.setBounds(790, 00, 70, 50);
		findPanel.add(from_label);

		final JTextField from_text = new JTextField();
		from_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		from_text.setBounds(840, 13, 40, 25);
		findPanel.add(from_text);

		JLabel to_label = new JLabel("To");
		to_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		to_label.setBounds(900, 00, 70, 50);
		findPanel.add(to_label);

		final JTextField to_text = new JTextField();
		to_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		to_text.setBounds(930, 13, 40, 25);
		findPanel.add(to_text);*/

		// /////////////GR No.//////////////
		// /////////GR radio///////////////
		final JRadioButton gr_no_radio = new JRadioButton();
		gr_no_radio.setBounds(30, 55, 20, 20);
				
		JLabel gr_no_label = new JLabel("G.R. No.:");
		gr_no_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		gr_no_label.setBounds(60, 40, 70, 50);
		
		final JTextField gr_no_text = new JTextField();
		gr_no_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		if(!retGrNo.equalsIgnoreCase("")){
			gr_no_radio.setSelected(true);
			std_radio.setSelected(false);
			Std_combo.setSelectedIndex(0);
			presentDiv_combo.setSelectedIndex(0);
			Std_combo.setEnabled(false);
			presentDiv_combo.setEnabled(false);
			gr_no_text.setText(retGrNo);
		}
		else{
			std_radio.setSelected(true);
			gr_no_text.setEnabled(false);
		}
		gr_no_text.setBounds(140, 55, 130, 25);
		
		findPanel.add(gr_no_radio);
		findPanel.add(gr_no_label);
		findPanel.add(gr_no_text);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gr_no_text.requestFocus();
			}
		});

		gr_no_text.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent event) {
				String grString = gr_no_text.getText();
				if (grString.length() < 7 && grString.length() !=  0) {
					gr_no_text.setText(commonObj.appendZero(grString));
				} else if(grString.length() > 6) {
					grString = grString.substring(0, 7);
					gr_no_text.setText(grString);
				}
			}
		});
				
		// /////////name radio///////////////30, 80, 120, 50
		/*final JRadioButton name_radio = new JRadioButton();
		name_radio.setBounds(30, 55, 20, 20);
		findPanel.add(name_radio);

		// /////////////Last Name//////////////
		JLabel lastName_label = new JLabel("Last Name :");
		lastName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_label.setBounds(60, 40, 120, 50);
		findPanel.add(lastName_label);

		final JTextField lastName_text = new JTextField(lastClass);
		lastName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_text.setBounds(160, 50, 130, 25);
		findPanel.add(lastName_text);
		// //////////////////////////////////
		// /////////////First Name//////////////
		JLabel firstName_label = new JLabel("First Name :");
		firstName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_label.setBounds(310, 40, 120, 50);
		findPanel.add(firstName_label);

		final JTextField firstName_text = new JTextField(firstClass);
		firstName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_text.setBounds(410, 50, 130, 25);
		findPanel.add(firstName_text);
		// //////////////////////////////////
		// /////////////Father Name//////////////
		JLabel fatherName_label = new JLabel("Father Name :");
		fatherName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_label.setBounds(580, 40, 120, 50);
		findPanel.add(fatherName_label);

		final JTextField fatherName_text = new JTextField(fatherClass);
		fatherName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_text.setBounds(700, 50, 130, 25);
		findPanel.add(fatherName_text);*/
		// //////////////////////////////////

		// /////////////Exam//////////////
		final JRadioButton exam_radio = new JRadioButton();
		exam_radio.setBounds(30, 90, 20, 20);
		exam_radio.setSelected(true);
		exam_radio.setEnabled(false);
		findPanel.add(exam_radio);

		final JLabel exam_label = new JLabel("Exam");
		exam_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		exam_label.setBounds(60, 75, 150, 50);
		findPanel.add(exam_label);

		String[] examList = examCategory.split(",");
		final JComboBox exam_combo = new JComboBox(examList);
		if(examClass.equalsIgnoreCase("Final") && (stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XII"))){
			exam_combo.addItem("Final");
		}
		else{
			exam_combo.removeItem("Final");
		}
		exam_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		exam_combo.setBounds(120, 90, 120, 25);
		exam_combo.setSelectedItem(examClass);
		findPanel.add(exam_combo);
		
		////% in board exam/////////////////
		final JLabel percent_label = new JLabel("Note : Please enter % obtained in board exam on submit.");
		percent_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		percent_label.setForeground(Color.RED);
		percent_label.setBounds(310, 90, 500, 20);

		// /////////////Subject//////////////
		final JRadioButton subject_radio = new JRadioButton();
		subject_radio.setBounds(310, 90, 20, 20);
		subject_radio.setSelected(true);
		subject_radio.setEnabled(false);
		findPanel.add(subject_radio);

		final JLabel subject_label = new JLabel("Subject");
		subject_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		subject_label.setBounds(340, 75, 150, 50);
		findPanel.add(subject_label);

		final JComboBox subject_combo = new JComboBox();
		subject_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		subject_combo.setBounds(410, 90, 220, 25);
		findPanel.add(subject_combo);

		// /////////////type//////////////
		final JRadioButton type_radio = new JRadioButton();
		type_radio.setBounds(670, 90, 20, 20);
		type_radio.setSelected(true);
		type_radio.setEnabled(false);
		findPanel.add(type_radio);

		final JLabel type_label = new JLabel("Type");
		type_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		type_label.setBounds(700, 75, 150, 50);
		findPanel.add(type_label);

		examType = "All," + examType;
		String[] typeData = examType.split(",");
		final JComboBox type_combo = new JComboBox(typeData);
		type_combo.setSelectedItem(typeClassDisp);
		type_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		type_combo.setBounds(760, 90, 140, 25);
		findPanel.add(type_combo);

		// ////////update subject list//////////////////////////

		if (!stdClass.equalsIgnoreCase("")
				&& !stdClass.equalsIgnoreCase("Select")) {
			// presentDiv_combo.setEnabled(true);
			try {
				findSubList = dbValidate.findSubjectList(sessionData, stdClass, academicYearClass);
				String[] subjectArr = findSubList
						.toArray(new String[findSubList.size()]);
				// String subjectData[] = (subjectArr);
				subject_combo.removeAllItems();
				subject_combo.addItem("All");
//				if (!subjectClass.equalsIgnoreCase(""))
//					subject_combo.addItem(subjectClass);

				for (int z = 0; z < subjectArr.length; z++) {
					subject_combo.addItem(subjectArr[z]);
				}
				subject_combo.setSelectedItem(subjectClass);
			} catch (Exception e2) {
				logger.info("Exceptyion findSubList : " + e2);
			}
		} else {
			try {
				findSubList = dbValidate.findSubjectList(sessionData, (String) Std_combo.getSelectedItem(), academicYearClass);
				String[] subjectArr = findSubList
						.toArray(new String[findSubList.size()]);
				// String subjectData[] = (subjectArr);
				subject_combo.removeAllItems();
				subject_combo.addItem("All");
				if (!subjectClass.equalsIgnoreCase(""))
					subject_combo.addItem(subjectClass);

				for (int z = 0; z < subjectArr.length; z++) {
					subject_combo.addItem(subjectArr[z]);
				}
			} catch (Exception e2) {
				logger.info("Exceptyion findSubList : " + e2);
			}
		}

		if(!stdClass.equalsIgnoreCase("All") && !stdClass.equalsIgnoreCase("Select") && 
	    		!stdClass.equalsIgnoreCase("") && dbValidate.connectDatabase(sessionData)){
			presentDiv_combo.removeAllItems();
			String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdClass, section, 
					"PRESENT_DIV", "PRESENT_STD", "class_allotment",academicYearClass);
			
			for (String retval: divAvailabe.split(",")) {
				presentDiv_combo.addItem(retval);
			}
			presentDiv_combo.setSelectedItem(divClass);
		}
		
		// ////////update type list//////////////////////////////////
//		if (!examClass.equalsIgnoreCase("")
//				&& !examClass.equalsIgnoreCase("Select")) {
//			try {
//				type_combo.removeAllItems();
//				if (!typeClass.equalsIgnoreCase(""))
//					type_combo.addItem(typeClass);
//				
//				if (examClass.equalsIgnoreCase("Semester 1")) {
//					type_combo.addItem("FUT");
//				} else if (examClass.equalsIgnoreCase("Semester 2")) {
//					type_combo.addItem("SUT");
//				}
//				type_combo.addItem("ORAL");
//				type_combo.addItem("WRITTEN");
//				type_combo.addItem("ASSIGNMENT");
//				type_combo.addItem("PRACTICAL");
//			} catch (Exception e2) {
//				logger.info("Exception exam_combo : " + e2);
//			}
//		} else {
//			presentDiv_combo.setSelectedIndex(0);
//			// presentDiv_combo.setEnabled(false);
//		}
		
		if(examClass.equalsIgnoreCase("Final") && (stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XII"))){
			findPanel.remove(subject_radio);
			findPanel.remove(subject_label);
			findPanel.remove(subject_combo);
			findPanel.remove(type_radio);
			findPanel.remove(type_label);
			findPanel.remove(type_combo);
			findPanel.add(percent_label);
			findPanel.revalidate();
			findPanel.repaint();
		}
		else{
			findPanel.add(subject_radio);
			findPanel.add(subject_label);
			findPanel.add(subject_combo);
			findPanel.add(type_radio);
			findPanel.add(type_label);
			findPanel.add(type_combo);
			findPanel.remove(percent_label);
			findPanel.revalidate();
			findPanel.repaint();
		}
		// ////////Std_combo action//////////////////////////
		Std_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				stdClass = (String) Std_combo.getSelectedItem();
				academicYearClass = (String) academicYear_combo.getSelectedItem();
				presentDiv_combo.removeAllItems();
				if (!stdClass.equalsIgnoreCase("") && !stdClass.equalsIgnoreCase("Select")) {
					try {
						findSubList = dbValidate.findSubjectList(sessionData, stdClass, academicYearClass);
						String[] subjectArr = findSubList.toArray(new String[findSubList.size()]);
						subject_combo.removeAllItems();
						subject_combo.addItem("All");
						for (int z = 0; z < subjectArr.length; z++) {
							subject_combo.addItem(subjectArr[z]);
						}
						if(stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XII")){
							exam_combo.addItem("Final");
						}
						else{
							exam_combo.removeItem("Final");
							findPanel.add(subject_radio);
							findPanel.add(subject_label);
							findPanel.add(subject_combo);
							findPanel.add(type_radio);
							findPanel.add(type_label);
							findPanel.add(type_combo);
							findPanel.remove(percent_label);
							findPanel.revalidate();
							findPanel.repaint();
						}
						exam_combo.setSelectedIndex(0);
						
					} catch (Exception e2) {
						logger.info("Exceptyion findSubList : " + e2);
					}
				}
				
				try{
					String stdSel = stdClass;
					String acadSel = (String) academicYear_combo.getSelectedItem();
					if(!stdSel.equalsIgnoreCase("All") && !stdSel.equalsIgnoreCase("Select") && dbValidate.connectDatabase(sessionData)){
						presentDiv_combo.removeAllItems();
						presentDiv_combo.addItem("Select");
						String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdSel, section, 
								"PRESENT_DIV", "PRESENT_STD", "class_allotment",acadSel);
						
						for (String retval: divAvailabe.split(",")) {
							presentDiv_combo.addItem(retval);
						}
					}
				} catch (Exception e1) {
					logger.info("Exception insertFormData ===>>>" + e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});

		// ////////div_combo action//////////////////////////
		presentDiv_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				logger.info("inside presentDiv_combo.addActionListener");
				/*divClass = (String) presentDiv_combo.getSelectedItem();
				if (!divClass.equalsIgnoreCase("") && !divClass.equalsIgnoreCase("Select")) {
					from_text.setEnabled(true);
					to_text.setEnabled(true);
				} else {
					from_text.setText("");
					from_text.setEnabled(false);
					to_text.setText("");
					to_text.setEnabled(false);
				}*/

			}
		});

		// ////////std_radio.addActionListener///////////
		std_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (std_radio.isSelected()) {
					gr_no_radio.setSelected(false);
					gr_no_text.setEnabled(false);
					gr_no_text.setText("");
					Std_combo.setEnabled(true);
					presentDiv_combo.setEnabled(true);
				} else {
					gr_no_text.setEnabled(true);
					Std_combo.setSelectedIndex(0);
					presentDiv_combo.setSelectedIndex(0);
					Std_combo.setEnabled(false);
					presentDiv_combo.setEnabled(false);
					/*name_radio.setEnabled(true);
					lastName_text.setEnabled(true);
					firstName_text.setEnabled(true);
					fatherName_text.setEnabled(true);*/
				}
			}
		});

		// //////gr radio action//////////////
		gr_no_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (gr_no_radio.isSelected()) {
					gr_no_text.setEnabled(true);
					Std_combo.setSelectedIndex(0);
//					presentDiv_combo.setSelectedIndex(0);
					std_radio.setSelected(false);
					Std_combo.setEnabled(false);
					presentDiv_combo.setEnabled(false);

				} else {
					gr_no_radio.setSelected(false);
					gr_no_text.setEnabled(false);
					gr_no_text.setText("");
				}
			}
		});
		// ////////name_radio.addActionListener///////////
		/*name_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (name_radio.isSelected()) {
					lastName_text.setEnabled(true);
					firstName_text.setEnabled(true);
					fatherName_text.setEnabled(true);
				} else {
					lastName_text.setText("");
					lastName_text.setEnabled(false);
					firstName_text.setText("");
					firstName_text.setEnabled(false);
					fatherName_text.setText("");
					fatherName_text.setEnabled(false);
				}
			}
		});*/

		// ////////exam_radio.addActionListener///////////
		exam_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (exam_radio.isSelected()) {
					exam_radio.setSelected(true);
					academicYear_combo.setEnabled(false);
					// presentDiv_combo.setEnabled(false);
				}
			}
		});

		// ////////exam_combo action//////////////////////////
		exam_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				logger.info("inside exam_combo.addActionListener");
				String examSel = (String) exam_combo.getSelectedItem();
				String stdSel = (String) Std_combo.getSelectedItem();
				
				if (!examSel.equalsIgnoreCase("") && !examSel.equalsIgnoreCase("Select") &&  
						(stdSel.equalsIgnoreCase("IX") || stdSel.equalsIgnoreCase("X") || 
								stdSel.equalsIgnoreCase("XI") || stdSel.equalsIgnoreCase("XII"))) {
					try {
						type_combo.removeAllItems();
						type_combo.addItem("Select");
						type_combo.addItem("All");
						if(!stdSel.equalsIgnoreCase("IX")){
							type_combo.addItem("ORAL");
							type_combo.addItem("ASSIGN");
							type_combo.addItem("WRITE");
							if (examSel.equalsIgnoreCase("Semester 1")) {
								type_combo.addItem("FUT");
								type_combo.addItem("PRACT");
							} else if (examSel.equalsIgnoreCase("Semester 2")) {
								type_combo.addItem("SUT");
								type_combo.addItem("ACTIVITY");
							}
						}
						else{
							if (examSel.equalsIgnoreCase("Semester 1")) {
								type_combo.addItem("FUT");
							} else if (examSel.equalsIgnoreCase("Semester 2")) {
								type_combo.addItem("SUT");
							}
							type_combo.addItem("PRES");
							type_combo.addItem("MCAP");
							type_combo.addItem("WRITE");
							type_combo.addItem("PRACT");
							type_combo.addItem("ACT");
						}
						
						if(examSel.equalsIgnoreCase("Final") && (stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XII"))){
							findPanel.remove(subject_radio);
							findPanel.remove(subject_label);
							findPanel.remove(subject_combo);
							findPanel.remove(type_radio);
							findPanel.remove(type_label);
							findPanel.remove(type_combo);
							findPanel.add(percent_label);
							findPanel.revalidate();
							findPanel.repaint();
						}
						else{
							findPanel.add(subject_radio);
							findPanel.add(subject_label);
							findPanel.add(subject_combo);
							findPanel.add(type_radio);
							findPanel.add(type_label);
							findPanel.add(type_combo);
							findPanel.remove(percent_label);
							findPanel.revalidate();
							findPanel.repaint();
						}
					} catch (Exception e2) {
						logger.info("Exception exam_combo : " + e2);
					}
				} 
				else if (!examSel.equalsIgnoreCase("") && !examSel.equalsIgnoreCase("Select") &&  
						(!stdSel.equalsIgnoreCase("IX") && !stdSel.equalsIgnoreCase("X") && 
								!stdSel.equalsIgnoreCase("XI") && !stdSel.equalsIgnoreCase("XII"))) {
					try {
						type_combo.removeAllItems();
						type_combo.addItem("Select");
						type_combo.addItem("All");
						type_combo.addItem("DOBS");
						type_combo.addItem("TEST");
						type_combo.addItem("ORAL");
						type_combo.addItem("ASSIGN");
						type_combo.addItem("PRACT");
						type_combo.addItem("ACT");
						type_combo.addItem("PROJECT");
						type_combo.addItem("OTHER");
						type_combo.addItem("SUM_ORAL");
						type_combo.addItem("SUM_PRACT");
						type_combo.addItem("SUM_WRITE");
					} catch (Exception e2) {
						logger.info("Exception exam_combo : " + e2);
					}
				}

			}
		});

		/*final JRadioButton date_radio = new JRadioButton();
		date_radio.setBounds(30, 122, 20, 20);
		findPanel.add(date_radio);
		
		final JLabel date_label = new JLabel("Date :");
		date_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		date_label.setBounds(60, 110, 210, 50);
		findPanel.add(date_label);

		*//****from date picker****//*
		final UtilDateModel modelFrom = new UtilDateModel();
		if(!fromDateClass.equalsIgnoreCase("")){
			int dayFromDate = Integer.parseInt(fromDateClass.substring(0,fromDateClass.indexOf("/")));
			int monthFromDate = Integer.parseInt(fromDateClass.substring(fromDateClass.indexOf("/")+1,fromDateClass.lastIndexOf("/")));
			int yearFromDate = Integer.parseInt(fromDateClass.substring(fromDateClass.lastIndexOf("/")+1));
			modelFrom.setDate(yearFromDate,monthFromDate-1,dayFromDate);
//			modelFrom.setDate(2014,04,24);
		}
		modelFrom.setSelected(true);
	    Properties pFrom = new Properties();
	    pFrom.put("text.today", "Today");
	    pFrom.put("text.month", "Month");
	    pFrom.put("text.year", "Year");
	    final JDatePanelImpl datePanelFrom = new JDatePanelImpl(modelFrom, pFrom);
	    datePanelFrom.setBounds(85, 122, 130, 26);
	    // Don't know about the formatter, but there it is...
	    final JDatePickerImpl datePickerFrom = new JDatePickerImpl(datePanelFrom, new DateLabelFormatter());
	    datePickerFrom.setBounds(120, 122, 130, 26);
        findPanel.add(datePickerFrom);*/
		
//		std_radio.setEnabled(false);
		// presentDiv_combo.setEnabled(false);
		/*from_text.setEnabled(false);
		to_text.setEnabled(false);*/
		/*lastName_text.setEnabled(false);
		firstName_text.setEnabled(false);
		fatherName_text.setEnabled(false);*/

		// /////////////Submit//////////////
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(930, 60, 130, 25);
//		submitButton.setEnabled(false);
		findPanel.add(submitButton);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				logger.info("======Inside submit action=====");
				boolean validateFields = true;
				String academicSel = "";
				String std = "";
				String div = "";
				String from = "";
				String to = "";
				String firstName = "";
				String lastName = "";
				String fatherName = "";
				String exam = "";
				String subject = "";
				String type = "";
				String dateSelected = "";
				String grNo = "";
				boolean isGrSelected = gr_no_radio.isSelected();
				if(isGrSelected){
					grNo = gr_no_text.getText();
				}
				/*boolean isNameSelected = name_radio.isSelected();
				if(isNameSelected){
					firstName = firstName_text.getText() == null ? "" : firstName_text.getText();
					lastName = lastName_text.getText() == null ? "" : lastName_text.getText();
					fatherName = fatherName_text.getText() == null ? "" : fatherName_text.getText();
				}*/
				
				academicSel = (String) academicYear_combo.getSelectedItem() == null ? "" : (String) academicYear_combo.getSelectedItem();
				std = (String) Std_combo.getSelectedItem() == null ? "" : (String) Std_combo.getSelectedItem();
				div = (String) presentDiv_combo.getSelectedItem() == null ? "" : (String) presentDiv_combo.getSelectedItem();
				/*from = from_text.getText() == null ? "" : from_text.getText();
				to = to_text.getText() == null ? "" : to_text.getText();*/
				exam = (String) exam_combo.getSelectedItem() == null ? "" : (String) exam_combo.getSelectedItem();
				subject = (String) subject_combo.getSelectedItem() == null ? "" : (String) subject_combo.getSelectedItem();
				type = (String) type_combo.getSelectedItem() == null ? "" : (String) type_combo.getSelectedItem();

				if (academicSel.equalsIgnoreCase("Year")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Academic year.");
				} else if (std.equalsIgnoreCase("Select") && !isGrSelected) { 
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Std.");
				} else if (div.equalsIgnoreCase("Select") && !isGrSelected) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Div.");
				} 
				/*else if (isNameSelected && !lastName.equalsIgnoreCase("") && !commonObj.validateAlphaNum(lastName) && name_radio.isSelected()) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please enter valid LastName");
				} else if (isNameSelected && !firstName.equalsIgnoreCase("") && !commonObj.validateAlphaNum(firstName) && name_radio.isSelected()) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please enter valid FirstName");
				} else if (isNameSelected && !fatherName.equalsIgnoreCase("") && !commonObj.validateAlphaNum(fatherName) && name_radio.isSelected()) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please enter valid FatherName");
				}*/ 
				else if (exam.equalsIgnoreCase("Select") || exam.equalsIgnoreCase("")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Exam");
				} else if (!isGrSelected && (!std.equalsIgnoreCase("X") && !std.equalsIgnoreCase("XII") && !exam.equalsIgnoreCase("Final")) && 
						(subject.equalsIgnoreCase("Select") || subject.equalsIgnoreCase("All") || subject.equalsIgnoreCase(""))) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select one Subject");
				} else if (!std.equalsIgnoreCase("") && !std.equalsIgnoreCase("Select") && !std.equalsIgnoreCase("X") && !std.equalsIgnoreCase("XII")) {
					if(!isGrSelected && (type.equalsIgnoreCase("Select") || type.equalsIgnoreCase("") || type.equalsIgnoreCase("All"))) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Type");
					}
				} else if ((std.equalsIgnoreCase("") || std.equalsIgnoreCase("Select")) && gr_no_radio.isSelected()) {
					if((type.equalsIgnoreCase("Select") || type.equalsIgnoreCase(""))) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Type");
					}
				}

				if (academicSel.equalsIgnoreCase("Year")) {
					academicSel = "";
				}
				if(std.equalsIgnoreCase("Select")){
					std = "";
					div = "";
				}
				
				if (validateFields) {
					try {
						stdClass = std;
						// academicYearClass = academicSel;
						List markList = new ArrayList();

						frame.setVisible(false);
						panelHome.removeAll();///to remve entire panel
						if(!isGrSelected){
							new MarksEntry(sessionData, std, markList, academicSel, false, div, from, to, lastName, firstName, fatherName, exam, 
									subject, type, section, user_name, user_role, "", grNo);
						}
						else{
							MarksEntryStudent.getMarksEntryStudent(sessionData, std, markList, academicSel, false, div, from, to, lastName, firstName, fatherName, exam, 
									subject, type, section, user_name, user_role, "", grNo);
						}

					} catch (Exception e1) {
						commonObj.logException(e1);
						new Student(sessionData, section, user_name, user_role);
						frame.setVisible(false);
						panelHome.removeAll();///to remve entire panel
					}
				}
			}

		});
		// /////////////Reset//////////////
		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		resetButton.setBounds(930, 90, 130, 25);
		findPanel.add(resetButton);

		resetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				logger.info("CliCked Reset in Marks Entry");
				List findLCList = new ArrayList();
				frame.setVisible(false);
				panelHome.removeAll();///to remve entire panel
				new MarksEntry(sessionData, "", findLCList, "", false, "", "", "", "", "", "",
	                    "", "", "", section, user_name, user_role, "", retGrNo);
			}
		});
		
		// /////////////Marks Obtained//////////////
		JButton marksObtButton = new JButton("Marks Obtained Excel");
		marksObtButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		marksObtButton.setBounds(350, 120, 210, 25);
		findPanel.add(marksObtButton);

		marksObtButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int reply = 0;
					boolean validateFields = true;
					String academicSel = "";
					String std = "";
					String div = "";
					String from = "";
					String to = "";
					String firstName = "";
					String lastName = "";
					String fatherName = "";
					String exam = "";
					String subject = "";
					String type = "";

					academicSel = (String) academicYear_combo.getSelectedItem() == null ? "" : (String) academicYear_combo.getSelectedItem();
					std = (String) Std_combo.getSelectedItem() == null ? "" : (String) Std_combo.getSelectedItem();
					div = (String) presentDiv_combo.getSelectedItem() == null ? "" : (String) presentDiv_combo.getSelectedItem();
					exam = (String) exam_combo.getSelectedItem() == null ? "" : (String) exam_combo.getSelectedItem();
					subject = (String) subject_combo.getSelectedItem() == null ? "" : (String) subject_combo.getSelectedItem();
					type = (String) type_combo.getSelectedItem() == null ? "" : (String) type_combo.getSelectedItem();

					if (academicSel.equalsIgnoreCase("Year") || academicSel.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std, Div and Exam to download template.");
					} else if (std.equalsIgnoreCase("Select")) { 
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std, Div and Exam to download template.");
					} else if (div.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std, Div and Exam to download template.");
					} else if (exam.equalsIgnoreCase("Select") || exam.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std, Div and Exam to download template");
					} else if (subject.equalsIgnoreCase("All") && type.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select type as you are downloading for All subjects");
					}

					if(validateFields){
						reply = JOptionPane.showConfirmDialog(null, "Would You Like to download Marks Obtained "+exam+" ?", "Confirm validate", JOptionPane.YES_NO_OPTION);
						
						if (reply == JOptionPane.YES_OPTION) {
							if (dbValidate.connectDatabase(sessionData)) {
								JFrame f = new JFrame("Marks Obtained downlaod in progress. Don't Close");
								f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
							    f.setSize(400, 0);
							    f.setResizable(false);
							    f.setVisible(true);
							    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							    
							    dbValidate.newStudentsInMarksEntry(sessionData, academicSel, std, div, section);
							    if(!subject.equalsIgnoreCase("All")){
							    	dbValidate.obtainedMarksExcel(sessionData, std, div,academicSel,section,"print",exam,subject,type);
							    }
							    else{
							    	dbValidate.obtainedMarksForAllExcel(sessionData, std, div,academicSel,section,"print",exam,subject,type,retGrNo);
							    }
								f.setVisible(false);
							}
						}
					}
				} catch (Exception e1) {
					logger.info("Exception backupButton : "+e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});
				
		JLabel templateLabel = new JLabel("Template for Marks Entry >> ");
		templateLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		templateLabel.setBounds(570, 120, 300, 25);
		findPanel.add(templateLabel);
        
		// /////////////Download Template//////////////
		JButton templateButton = new JButton("Download");
		templateButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		templateButton.setBounds(790, 120, 130, 25);
		findPanel.add(templateButton);

		templateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int reply = 0;
					boolean validateFields = true;
					String academicSel = "";
					String std = "";
					String div = "";
					String from = "";
					String to = "";
					String firstName = "";
					String lastName = "";
					String fatherName = "";
					String exam = "";
					String subject = "";
					String type = "";
					String messageDisplay = "";

					academicSel = (String) academicYear_combo.getSelectedItem() == null ? "" : (String) academicYear_combo.getSelectedItem();
					std = (String) Std_combo.getSelectedItem() == null ? "" : (String) Std_combo.getSelectedItem();
					div = (String) presentDiv_combo.getSelectedItem() == null ? "" : (String) presentDiv_combo.getSelectedItem();
					exam = (String) exam_combo.getSelectedItem() == null ? "" : (String) exam_combo.getSelectedItem();
					subject = (String) subject_combo.getSelectedItem() == null ? "" : (String) subject_combo.getSelectedItem();
					type = (String) type_combo.getSelectedItem() == null ? "" : (String) type_combo.getSelectedItem();

					if (academicSel.equalsIgnoreCase("Year") || academicSel.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std, Div and Exam to download template.");
					} else if (std.equalsIgnoreCase("Select")) { 
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std, Div and Exam to download template.");
					} else if (div.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std, Div and Exam to download template.");
					} else if (exam.equalsIgnoreCase("Select") || exam.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std, Div and Exam to download template");
					} else if (type.equalsIgnoreCase("Select") || type.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std, Div, Exam & Type to download template");
					} else if (type.equalsIgnoreCase("All") && subject.equalsIgnoreCase("All")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select either Subject or Type as All. Don't select both All.");
					} 
					
					if(!subject.equalsIgnoreCase("")){
						messageDisplay = " \n Subject : "+subject;
					}
					if(!type.equalsIgnoreCase("")){
						messageDisplay = messageDisplay + " \n Type : "+type;
					}

					if(validateFields){
						reply = JOptionPane.showConfirmDialog(null, "Would You Like to download template for Marks Entry "+exam+messageDisplay+" ?", "Confirm validate", JOptionPane.YES_NO_OPTION);
						
						if (reply == JOptionPane.YES_OPTION) {
							String templatePath = commonObj.getDriveName() + bundle.getString("TEMPLATE_PATH_"+sessionData.getDBName())+commonObj.getCurrentDatein_dd_MMM_yyyy();
							if (dbValidate.connectDatabase(sessionData)) {
								JFrame f = new JFrame("Marks Entry Template downlaod in progress. Don't Close");
								f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
							    f.setSize(400, 0);
							    f.setResizable(false);
							    f.setVisible(true);
							    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							    dbValidate.newStudentsInMarksEntry(sessionData, academicSel, std, div, section);
								dbValidate.findMarksEntryTemplate(sessionData, academicSel, std, div, exam, subject, 
										type, section, lastClass.toUpperCase(), firstClass.toUpperCase(), fatherClass.toUpperCase());
								f.setVisible(false);
								commonObj.showMessageDialog("Template downloaded to "+templatePath);
							}
						}
					}
				} catch (Exception e1) {
					logger.info("Exception backupButton : "+e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});
		
		// /////////////Upload Marks Template//////////////
		JButton uploadButton = new JButton("Upload");
		uploadButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		uploadButton.setBounds(930, 120, 130, 25);
		findPanel.add(uploadButton);
		
		JSeparator jseparator = new JSeparator();
 		jseparator.setFont(new Font("Book Antiqua", Font.BOLD, 18));
 		jseparator.setBounds(30, 150, (screenWidth - 200), 50);
 		findPanel.add(jseparator);

		uploadButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					boolean validate = true;
					String stdSel = Std_combo.getSelectedItem().toString();
					String divSel = presentDiv_combo.getSelectedItem().toString();
					String academicSel = academicYear_combo.getSelectedItem().toString();
					String examSel = exam_combo.getSelectedItem().toString();
					String subject = (String) subject_combo.getSelectedItem() == null ? "" : (String) subject_combo.getSelectedItem();
					String type = (String) type_combo.getSelectedItem() == null ? "" : (String) type_combo.getSelectedItem();
					
					if(academicSel.equalsIgnoreCase("Select") || academicSel.equalsIgnoreCase("")){
						commonObj.showMessageDialog("Please select Academic Year, Std, Div and Exam to upload excel");
						validate = false;
					}
					else if(stdSel.equalsIgnoreCase("Select") || stdSel.equalsIgnoreCase("")){
						commonObj.showMessageDialog("Please select Academic Year, Std, Div and Exam to upload excel");
						validate = false;
					}
					else if(divSel.equalsIgnoreCase("Select") || divSel.equalsIgnoreCase("")){
						commonObj.showMessageDialog("Please select Academic Year, Std, Div and Exam to upload excel");
						validate = false;
					}
					else if(examSel.equalsIgnoreCase("Select") || examSel.equalsIgnoreCase("")){
						commonObj.showMessageDialog("Please select Academic Year, Std, Div and Exam to upload excel");
						validate = false;
					} else if (type.equalsIgnoreCase("All") && subject.equalsIgnoreCase("All")) {
						validate = false;
						JOptionPane.showMessageDialog(null, "Please select either Subject or Type as All. Don't select both All.");
					} 
					
					if(validate){
						String default_path = commonObj.getDriveName() + bundle.getString("TEMPLATE_PATH_"+sessionData.getDBName());
						JFileChooser fileChooser = new JFileChooser(default_path);
						int returnValue = fileChooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
							
							int reply = 0;
							reply = JOptionPane.showConfirmDialog(null, "Would You Like to upload "+absolutePath+"?", "Confirm", JOptionPane.YES_NO_OPTION);
							
							if (reply == JOptionPane.YES_OPTION) {
								try {
									/*JFrame f = new JFrame("Marks upload in progress. Please wait & Don't Close");
									f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
								    f.setSize(500, 0);
								    f.setResizable(false);
								    f.setVisible(true);
								    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
								    String messageDisplay = "";
								    messageDisplay = commonObj.uploadMarksTemplate(sessionData, absolutePath, stdSel, divSel, academicSel, examSel);
//									f.setVisible(false);
									commonObj.showMessageDialog(messageDisplay);
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(null, "Error : "+ e1);
								}
							} else if (reply == JOptionPane.NO_OPTION) {
								JOptionPane.showMessageDialog(null, "You have cancelled to upload.");
							}
						}
					}
				} catch (Exception e1) {
					logger.info("Exception backupButton : "+e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});
		
		bottombandPanel.add(findPanel, BorderLayout.EAST);
		// ////////////////find panel ends/////////////////////////////////

		// //////////scrollArea panel/////////////////////////////////////
		JPanel scrollAreaPanel = new JPanel(new BorderLayout());
		scrollAreaPanel.setPreferredSize(new Dimension(screenWidth - 150, screenHeight - 425));//change dataPanel setting here

		// ///////////Data Panel/////////////
		final JPanel dataPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				Image img = new ImageIcon(img_path + img_mainband).getImage();
				Dimension size = new Dimension(screenWidth - 158, ((screenHeight - 330) + scrollHeight));// change height to change scrolling area
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				// g.drawImage(img, 0, 0, null);
				g.drawImage(img, 0, 0, screenWidth - 158, (screenHeight - 330)
						+ scrollHeight, null);
			}
		};
		dataPanel.setLayout(null);

		int listSize = marksSem1DataMap.size();
		logger.info("subjectList size in if ==>" + listSize);

		if (listSize > 0) {
			int startWidth = 270;
			if (stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XI")
					|| stdClass.equalsIgnoreCase("XII")) {
				startWidth = 240;
			}
			else if(stdClass.equalsIgnoreCase("IX")){
				startWidth = 220;
			}
			
			JLabel name_label = new JLabel("Name : " + grMarksDataMap.get("name"));
			name_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			name_label.setBounds(40, 140, 600, 50);
			findPanel.add(name_label);
			
			JSeparator nameJseparator = new JSeparator();
			nameJseparator.setFont(new Font("Book Antiqua", Font.BOLD, 18));
			nameJseparator.setBounds(30, 175, (screenWidth - 200), 50);
	 		findPanel.add(nameJseparator);
	 		
			final JRadioButton all_radio = new JRadioButton();
			all_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			all_radio.setBounds(40, 180, 20, 20);
			all_radio.setSelected(true);
			all_radio.setEnabled(false);
			findPanel.add(all_radio);

			JLabel std_label = new JLabel("STD.: " + stdClass);
			std_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			std_label.setBounds(70, 167, 120, 50);
			findPanel.add(std_label);

			// JLabel pipe_label1 = new JLabel(": "+stdClass);
			// pipe_label1.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			// pipe_label1.setBounds(105, 00, 120, 50);
			// dataPanel.add(pipe_label1);

			JLabel pipe_label2 = new JLabel("|");
			pipe_label2.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label2.setBounds(250, 167, 120, 50);
			findPanel.add(pipe_label2);

			startWidth = 220;
			int semHeaderWidth = 370, incrementalType = 0, incrementalTypeSem2 = 0;
			List<String> typeListSem1 = Arrays.asList("FUT","PRES","MCAP","WRITE","PRACT","ACT");
			if(stdInt < 9) {
				startWidth = 210;
				typeListSem1 = Arrays.asList("DOBS","TEST","ORAL","ASSIGN","WRITE","PROJ/ACT");
			}
			else if(commonObj.is9thEvaluation(stdClass, academicYear)) {
				typeListSem1 = Arrays.asList("FUT","MCQ/PRA","LISTEN","SPEAK","ASSIGN","PROJ","WRITE");
			}
			
			if(typeListSem1.size() > 6) {
				incrementalType = typeListSem1.size() - 5;
				incrementalTypeSem2 = incrementalType+2;
			}
			
			JLabel sem1_label = new JLabel("FIRST SEMESTER");
			sem1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sem1_label.setBounds(370+(incrementalType*30), 167, 160, 50);
			findPanel.add(sem1_label);

			JLabel pipe_label5 = new JLabel("|");
			pipe_label5.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label5.setBounds(630+(incrementalType*30), 167, 120, 50);
			findPanel.add(pipe_label5);

			JLabel sem2_label = new JLabel("SECOND SEMESTER");
			sem2_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sem2_label.setBounds(740+(incrementalTypeSem2*30), 167, 180, 50);
			findPanel.add(sem2_label);

			JLabel pipe_label12 = new JLabel("|");
			pipe_label12.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label12.setBounds(1010+(incrementalTypeSem2*30), 167, 120, 50);
			findPanel.add(pipe_label12);

			String lineStr = "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
			JLabel line_label = new JLabel(lineStr);
			line_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			line_label.setBounds(30, 180, 1200, 50);
			findPanel.add(line_label);

			JLabel sub_label = new JLabel("SUBJECT");
			sub_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sub_label.setBounds(60, 200, 150, 50);
			findPanel.add(sub_label);

			JLabel pipe_label7 = new JLabel("|");
			pipe_label7.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label7.setBounds(250, 200, 120, 50);
			findPanel.add(pipe_label7);

			if(typeListSem1.size() > 6) {
				startWidth = startWidth - 10;
			}
			JLabel[] typeSem1_labels = new JLabel[typeListSem1.size()];
			
			for(int i = 0; i < typeListSem1.size(); i++) {
				startWidth = startWidth + 60;
				if(typeListSem1.get(i).length() > 6 && i < (typeListSem1.size()-1)) {
					startWidth = startWidth - 10;
				}
				typeSem1_labels[i] = new JLabel(typeListSem1.get(i));
				typeSem1_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 12));
				typeSem1_labels[i].setBounds(startWidth, 200, 160, 50);
				findPanel.add(typeSem1_labels[i]);
				if(typeListSem1.get(i).length() > 6 && i < (typeListSem1.size()-1)) {
					startWidth = startWidth + 20;
				}
			}
			
			if(stdInt < 9) {
				startWidth = startWidth + 60;
			}
			else {
				startWidth = startWidth + 50;
			}
			
			JLabel pipe_label9 = new JLabel("|");
			pipe_label9.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label9.setBounds(startWidth, 200, 120, 50);
			findPanel.add(pipe_label9);

			if(stdInt < 9) {
				startWidth = startWidth - 40;
			}
			else {
				startWidth = startWidth - 30;
			}
			
			List<String> typeListSem2 = Arrays.asList("SUT","PRES","MCAP","WRITE","PRACT","ACT");
			if(stdInt < 9) {
				typeListSem2 = Arrays.asList("DOBS","TEST","ORAL","ASSIGN","WRITE","PROJ/ACT");
			}
			else if(commonObj.is9thEvaluation(stdClass, academicYear)) {
				typeListSem2 = Arrays.asList("SUT","MCQ/PRA","LISTEN","SPEAK","ASSIGN","PROJ","WRITE");
			}
			
			JLabel[] typeSem2_labels = new JLabel[typeListSem2.size()];
			
			for(int i = 0; i < typeListSem2.size(); i++) {
				startWidth = startWidth + 60;
				if(typeListSem1.get(i).length() > 6) {
					startWidth = startWidth - 10;
				}
				typeSem2_labels[i] = new JLabel(typeListSem2.get(i));
				typeSem2_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 12));
				typeSem2_labels[i].setBounds(startWidth, 200, 160, 50);
				findPanel.add(typeSem2_labels[i]);
				if(typeListSem1.get(i).length() > 6) {
					startWidth = startWidth + 10;
				}
			}
			
			if(stdInt < 9) {
				startWidth = startWidth + 60;
			}
			else {
				startWidth = startWidth + 50;
			}
			JLabel pipe_label11 = new JLabel("|");
			pipe_label11.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label11.setBounds(startWidth, 200, 120, 50);
			findPanel.add(pipe_label11);

			JLabel maxLine_label = new JLabel(lineStr);
			maxLine_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			maxLine_label.setBounds(30, 210, 1200, 50);
			findPanel.add(maxLine_label);

			listSize = maxSubMarks.size();
			String[] subjectArray = new String[listSize];
			if (listSize > 0) {
				int j = 0;
				int l = 0;
				final JRadioButton[] sr_radio = new JRadioButton[listSize];
				final JLabel[] std_labels = new JLabel[listSize];
				final JLabel[] subName_label = new JLabel[listSize];
				final JLabel[] max_label = new JLabel[listSize];
				final JLabel[] convert_label = new JLabel[listSize];
				final JLabel[] subTitle_label = new JLabel[listSize];
				final JLabel[] marksGrade_label = new JLabel[listSize];
				final JLabel[] orderNo_label = new JLabel[listSize];
				final JLabel[] optional_label = new JLabel[listSize];
				boolean disableFlagSem1 = false;
				boolean disableFlagSem2 = false;
				
				if(!sem.equalsIgnoreCase("sem1")) {
					disableFlagSem1 = true;
				}
				else if(!sem.equalsIgnoreCase("sem2")) {
					disableFlagSem2 = true;
				}
				List<String> typeListSem1Marks = Arrays.asList("FDOB","FOBT","FORA","FASS","FWRI","FPRA");
				List<String> typeListSem2Marks = Arrays.asList("SDOB","SOBT","SORA","SASS","SWRI","SPRA");
				List<String> typeListSem1Max = Arrays.asList("dobs","obt","oral","assign","write","pract");
				List<String> typeListSem2Max = Arrays.asList("dobs","obt","oral","assign","write","pract");
				
				if(commonObj.is9thEvaluation(stdClass, academicYear)) {
					typeListSem1Marks = Arrays.asList("FOBT","FPRA","FLIS","FSPE","FASS","FPRO","FWRI");
					typeListSem2Marks = Arrays.asList("SOBT","SPRA","SLIS","SSPE","SASS","SPRO","SWRI");
					typeListSem1Max = Arrays.asList("obt","pract","listen","speak","assign","project","write");
					typeListSem2Max = Arrays.asList("obt","pract","listen","speak","assign","project","write");
				}
				else if(stdInt > 8) {
					typeListSem1Marks = Arrays.asList("FOBT","FPRES","FMCA","FWRI","FPRA","FACT");
					typeListSem2Marks = Arrays.asList("SOBT","SPRES","SMCA","SWRI","SPRA","SACT");
					typeListSem1Max = Arrays.asList("obt","pres","mcap","write","pract","activity");
					typeListSem2Max = Arrays.asList("obt","pres","mcap","write","pract","activity");
				}
				
				final JTextField[] sem1_text = new JTextField[listSize*typeListSem1Marks.size()];
				final JTextField[] sem2_text = new JTextField[listSize*typeListSem1Marks.size()];
				final List<String> typeListSem1MarksFinal = typeListSem1Marks;
				final List<String> typeListSem2MarksFinal = typeListSem2Marks;
				final List<String> typeListSem1MaxFinal = typeListSem1Max;
				final List<String> typeListSem2MaxFinal = typeListSem2Max;
				
//				final JTextField[] sem1DailyObs_text = new JTextField[listSize];
//				final JTextField[] sem1Obt_text = new JTextField[listSize];
//				final JTextField[] sem1Oral_text = new JTextField[listSize];
//				final JTextField[] sem1Assign_text = new JTextField[listSize];
//				final JTextField[] sem1Pres_text = new JTextField[listSize];
//				final JTextField[] sem1Mcap_text = new JTextField[listSize];
//				final JTextField[] sem1Write_text = new JTextField[listSize];
//				final JTextField[] sem1Pract_text = new JTextField[listSize];
//				final JTextField[] sem1Act_text = new JTextField[listSize];
//				final JTextField[] sem1Project_text = new JTextField[listSize];
//				final JTextField[] sem1Other_text = new JTextField[listSize];
//				final JTextField[] sem1Oral1_text = new JTextField[listSize];
//				final JTextField[] sem1Pract1_text = new JTextField[listSize];
//				final JTextField[] sem1Write1_text = new JTextField[listSize];
//				final JTextField[] sem1Listen_text = new JTextField[listSize];
//				final JTextField[] sem1Speak_text = new JTextField[listSize];
//				final JTextField[] sem1Assign1_text = new JTextField[listSize];
//				final JTextField[] sem1Itot_text = new JTextField[listSize];
//				
//				final JTextField[] sem2DailyObs_text = new JTextField[listSize];
//				final JTextField[] sem2Obt_text = new JTextField[listSize];
//				final JTextField[] sem2Oral_text = new JTextField[listSize];
//				final JTextField[] sem2Assign_text = new JTextField[listSize];
//				final JTextField[] sem2Pres_text = new JTextField[listSize];
//				final JTextField[] sem2Mcap_text = new JTextField[listSize];
//				final JTextField[] sem2Write_text = new JTextField[listSize];
//				final JTextField[] sem2Pract_text = new JTextField[listSize];
//				final JTextField[] sem2Act_text = new JTextField[listSize];
//				final JTextField[] sem2Project_text = new JTextField[listSize];
//				final JTextField[] sem2Other_text = new JTextField[listSize];
//				final JTextField[] sem2Oral1_text = new JTextField[listSize];
//				final JTextField[] sem2Pract1_text = new JTextField[listSize];
//				final JTextField[] sem2Write1_text = new JTextField[listSize];
//				final JTextField[] sem2Listen_text = new JTextField[listSize];
//				final JTextField[] sem2Speak_text = new JTextField[listSize];
//				final JTextField[] sem2Assign1_text = new JTextField[listSize];
//				final JTextField[] sem2Itot_text = new JTextField[listSize];
				
				JLabel[] line_labels = new JLabel[listSize];
				JLabel[] line1_labels = new JLabel[listSize];
				JLabel[] line2_labels = new JLabel[listSize];
				JLabel[] pipe_labels1 = new JLabel[listSize];
				JLabel[] pipe_labels2 = new JLabel[listSize];
				JLabel[] pipe_labels3 = new JLabel[listSize];
				JLabel[] pipe_labels4 = new JLabel[listSize];
				JLabel[] pipe_labels5 = new JLabel[listSize];
				JLabel[] pipe_labels6 = new JLabel[listSize];
				JLabel[] pipe_labels7 = new JLabel[listSize];
				JLabel[] pipe_labels8 = new JLabel[listSize];

				Set set = maxSubMarks.entrySet();
				// Get an iterator
				Iterator m = set.iterator();

				j = -35;
				int i = 0, p = 0, q = 0, sem1TypeCount = 0, sem2TypeCount = 0, sem1SubCount = 0, sem2SubCount = 0;
				while (m.hasNext()) {
					j = j + 30;
					l = j + 50;

					Map.Entry me = (Map.Entry) m.next();
					LinkedHashMap subjectMaxMap = new LinkedHashMap();
					subjectMaxMap = (LinkedHashMap) me.getValue();

					// subjectList.remove(subjectArray[i]);
					// subjectArray[i] = subjectArray[i].replace("null", "NA");
					// subjectList.add(subjectArray[i]);
					String std = stdClass;
					String subject = subjectMaxMap.get("subject_name") == null ? "0" : subjectMaxMap.get("subject_name").toString();
					String subTitle = subjectMaxMap.get("subject_title") == null ? "0" : subjectMaxMap.get("subject_title").toString();
					String marks_grade = subjectMaxMap.get("marks_grade") == null ? "0" : subjectMaxMap.get("marks_grade").toString();
					String optional = subjectMaxMap.get("optional") == null ? "0" : subjectMaxMap.get("optional").toString();
					String optionalSubject = studentOptSubAllotMap.get(retGrNo).get("optionalSubject");
					String[] optionList = optionalSubject.split("\\|");
					boolean disableFlag = false;
					for(int k = 0; k < optionList.length; k++){
						if(optionList[k].equalsIgnoreCase(subTitle+"_NO")){
							disableFlag = true;
						}
					}
					
					String sem1Dobs = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FDOB");
					String sem1Obt = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FOBT");
					String sem1Oral = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FORA");
					String sem1Pres = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FPRE");
					String sem1Assign = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FASS");
					String sem1Mcap = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FMCA");
					String sem1Write = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FWRI");
					String sem1Pract = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FPRA");
					String sem1Act = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FACT");
					String sem1Project = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FPRO");
					String sem1Other = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FOTH");
					String sem1Oral1 = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FORA1");
					String sem1Pract1 = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FPRA1");
					String sem1Write1 = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FWRI1");
					String sem1Listen = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FLIS");
					String sem1Speak = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FSPE");
					String sem1Assign1 = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FASS1");
					String sem1Itot = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_FITOT");
					
					String sem2Dobs = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SDOB");
					String sem2Obt = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SOBT");
					String sem2Oral = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SORA");
					String sem2Pres = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SPRE");
					String sem2Assign = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SASS");
					String sem2Mcap = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SMCA");
					String sem2Write = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SWRI");
					String sem2Pract = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SPRA");
					String sem2Act = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SACT");
					String sem2Project = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SPRO");
					String sem2Other = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SOTH");
					String sem2Oral1 = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SORA1");
					String sem2Pract1 = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SPRA1");
					String sem2Write1 = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SWRI1");
					String sem2Listen = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SLIS");
					String sem2Speak = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SSPE");
					String sem2Assign1 = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SASS1");
					String sem2Itot = grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_SITOT");
					
					String orderNo = subjectMaxMap.get("order_no") == null ? "0" : subjectMaxMap.get("order_no").toString();

					std_labels[i] = new JLabel(std);
					std_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));

					subTitle_label[i] = new JLabel(subTitle);
					subTitle_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));

					marksGrade_label[i] = new JLabel(marks_grade);
					marksGrade_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));

					optional_label[i] = new JLabel(optional);
					optional_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));

					subName_label[i] = new JLabel(subject);
					subName_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					subName_label[i].setBounds(40, j + 12, 200, 20);
					subName_label[i].setToolTipText(subject);
					dataPanel.add(subName_label[i]);

					/*max_label[i] = new JLabel("Max.");
					max_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 10));
					max_label[i].setBounds(220, j + 12, 200, 20);
					dataPanel.add(max_label[i]);*/

					pipe_labels2[i] = new JLabel("|");
					pipe_labels2[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels2[i].setBounds(250, j, 120, 50);
					dataPanel.add(pipe_labels2[i]);

					startWidth = 270;
//					if (stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XI")
//							|| stdClass.equalsIgnoreCase("XII")) {
//						startWidth = 240;
//					}
//					else if(stdClass.equalsIgnoreCase("IX")){
//						startWidth = 220;
//					}
					
					for(int k = 0; k < typeListSem1MarksFinal.size(); k++) {
						sem1_text[p] = new JTextField(grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_"+typeListSem1MarksFinal.get(k)));
						sem1_text[p].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem1_text[p].setBounds(startWidth, j + 12, 40, 20);
						if(disableFlag || disableFlagSem1){
							sem1_text[p].setEditable(false);
						}
						if(maxSubMarks.get(subject).get("sem1_"+typeListSem1MaxFinal.get(sem1TypeCount)) != null)
							sem1_text[p].setToolTipText((maxSubMarks.get(subject).get("sem1_"+typeListSem1MaxFinal.get(sem1TypeCount))).toString());
							dataPanel.add(sem1_text[p]);
						startWidth = startWidth + 60;
						
						final int n = p, sem1TypeCountFinal = sem1TypeCount, sem1SubTypeCount = typeListSem1MarksFinal.size(), 
								sem1SubCountFinal = sem1SubCount;
						
						sem1_text[p].addFocusListener(new FocusListener() {

							@Override
							public void focusGained(FocusEvent arg0) {
								sem1_text[n].selectAll();
							}

							@Override
							public void focusLost(FocusEvent arg0) {
								LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
								subMaxMinMap = maxSubMarks.get(subName_label[sem1SubCountFinal].getText().toString());
								String sem1_text_focus = sem1_text[n].getText().trim().equalsIgnoreCase("")
										? "0" : sem1_text[n].getText().trim();

								if(commonObj.validateNumber(sem1_text_focus) && Double.parseDouble(sem1_text_focus) > Double.parseDouble(subMaxMinMap.get("sem1_"+typeListSem1MaxFinal.get(sem1TypeCountFinal)))){
									commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem1_"+typeListSem1MaxFinal.get(sem1TypeCountFinal)));
									sem1_text[n].setText("0");
								}
								else if(!commonObj.validateNumber(sem1_text_focus) && !sem1_text_focus.equalsIgnoreCase("AB") && !sem1_text_focus.equalsIgnoreCase("MG")){
									commonObj.showMessageDialog("Please enter AB or MG");
									sem1_text[n].setText("0");
								}
								else{
									grMarksDataMap.put(subName_label[sem1SubCountFinal].getText()+"_"+typeListSem1MarksFinal.get(sem1TypeCountFinal), sem1_text_focus);
								}
							}
						});
						
						if((n%sem1SubTypeCount) == (typeListSem1MarksFinal.size()-1)) {
							sem1SubCount++;
						}
						p = p + 1;
						sem1TypeCount = sem1TypeCount + 1;
						if(sem1TypeCount >= typeListSem1MaxFinal.size()) {
							sem1TypeCount = 0;
						}
					}

//					if(stdInt > 8) {
//						startWidth = startWidth + 50;
//					}
					pipe_labels4[i] = new JLabel("|");
					pipe_labels4[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels4[i].setBounds(startWidth, j, 40, 50);
					dataPanel.add(pipe_labels4[i]);

//					if(stdInt > 8) {
//						startWidth = startWidth - 30;
//					}
//					else {
//						startWidth = startWidth + 20;
//					}
					
					startWidth = startWidth + 20;
					for(int k = 0; k < typeListSem2MarksFinal.size(); k++) {
						sem2_text[q] = new JTextField(grMarksDataMap.get(subjectMaxMap.get("subject_name")+"_"+typeListSem2MarksFinal.get(k)));
						sem2_text[q].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem2_text[q].setBounds(startWidth, j + 12, 40, 20);
						if(disableFlag || disableFlagSem2){
							sem2_text[q].setEditable(false);
						}
						if(maxSubMarks.get(subject).get("sem2_"+typeListSem2MaxFinal.get(sem2TypeCount)) != null)
							sem2_text[q].setToolTipText((maxSubMarks.get(subject).get("sem2_"+typeListSem2MaxFinal.get(sem2TypeCount))).toString());
							dataPanel.add(sem2_text[q]);
						startWidth = startWidth + 60;
						
						final int n = q, sem2TypeCountFinal = sem2TypeCount, sem2SubTypeCount = typeListSem2MarksFinal.size(), 
								sem2SubCountFinal = sem2SubCount;
						sem2_text[q].addFocusListener(new FocusListener() {

							@Override
							public void focusGained(FocusEvent arg0) {
								sem2_text[n].selectAll();
							}

							@Override
							public void focusLost(FocusEvent arg0) {
								LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
								subMaxMinMap = maxSubMarks.get(subName_label[sem2SubCountFinal].getText().toString());
								String sem2_text_focus = sem2_text[n].getText().trim().equalsIgnoreCase("")
										? "0" : sem2_text[n].getText().trim();

								if(commonObj.validateNumber(sem2_text_focus) && Double.parseDouble(sem2_text_focus) > Double.parseDouble(subMaxMinMap.get("sem2_"+typeListSem2MaxFinal.get(sem2TypeCountFinal)))){
									commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem2_"+typeListSem2MaxFinal.get(sem2TypeCountFinal)));
									sem2_text[n].setText("0");
								}
								else if(!commonObj.validateNumber(sem2_text_focus) && !sem2_text_focus.equalsIgnoreCase("AB") && !sem2_text_focus.equalsIgnoreCase("MG")){
									commonObj.showMessageDialog("Please enter AB or MG");
									sem2_text[n].setText("0");
								}
								else{
									grMarksDataMap.put(subName_label[sem2SubCountFinal].getText()+"_"+typeListSem2MarksFinal.get(sem2TypeCountFinal), sem2_text_focus);
								}
							}
						});
						
						if((n%sem2SubTypeCount) == (typeListSem2MarksFinal.size()-1)) {
							sem2SubCount++;
						}
						q = q + 1;
						sem2TypeCount = sem2TypeCount + 1;
						if(sem2TypeCount >= typeListSem2MaxFinal.size()) {
							sem2TypeCount = 0;
						}
					}
					
					if(!examClass.equalsIgnoreCase("Semester 1")){
//						sem1_text[i].setEditable(false);
//						sem1Obt_text[i].setEditable(false);
//						sem1Oral_text[i].setEditable(false);
//						sem1Assign_text[i].setEditable(false);
//						sem1Pres_text[i].setEditable(false);
//						sem1Mcap_text[i].setEditable(false);
//						sem1Write_text[i].setEditable(false);
//						sem1Pract_text[i].setEditable(false);
//						sem1Act_text[i].setEditable(false);
					}
					if(!examClass.equalsIgnoreCase("Semester 2")){
//						sem2_text[i].setEditable(false);
//						sem2Obt_text[i].setEditable(false);
//						sem2Oral_text[i].setEditable(false);
//						sem2Assign_text[i].setEditable(false);
//						sem2Pres_text[i].setEditable(false);
//						sem2Mcap_text[i].setEditable(false);
//						sem2Write_text[i].setEditable(false);
//						sem2Pract_text[i].setEditable(false);
//						sem2Act_text[i].setEditable(false);
					}
					
					if(stdInt > 8) {
						startWidth = startWidth + 270;
					}
					pipe_labels6[i] = new JLabel("|");
					pipe_labels6[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels6[i].setBounds(startWidth, j, 40, 50);
					dataPanel.add(pipe_labels6[i]);

					///////////////////////////
					if (stdClass.equalsIgnoreCase("IX") || stdClass.equalsIgnoreCase("X")
							|| stdClass.equalsIgnoreCase("XI") || stdClass.equalsIgnoreCase("XII")) {
						/*line_labels[i] = new JLabel(
								"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						line_labels[i].setBounds(30, j + 10, 1100, 50);
						dataPanel.add(line_labels[i]);

						j = j + 30;*/

						/*convert_label[i] = new JLabel("Convert To");
						convert_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 10));
						convert_label[i].setBounds(190, j + 12, 200, 20);
						dataPanel.add(convert_label[i]);

						pipe_labels7[i] = new JLabel("|");
						pipe_labels7[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						pipe_labels7[i].setBounds(250, j, 120, 50);
						dataPanel.add(pipe_labels7[i]);*/

						/*sem1DailyObsCt_text[i] = new JTextField(sem1DobsCt);
						sem1DailyObsCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem1DailyObsCt_text[i].setBounds(startWidth, j + 12, 40, 20);
						if (!stdClass.equalsIgnoreCase("IX") && !stdClass.equalsIgnoreCase("X")
								&& !stdClass.equalsIgnoreCase("XI") && !stdClass.equalsIgnoreCase("XII")) {
							dataPanel.add(sem1DailyObsCt_text[i]);
						}

						sem1ObtCt_text[i] = new JTextField(sem1ObtCt);
						sem1ObtCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem1ObtCt_text[i].setBounds(startWidth + 60, j + 12, 40, 20);
						dataPanel.add(sem1ObtCt_text[i]);

						sem1OralCt_text[i] = new JTextField(sem1OralCt);
						sem1OralCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem1OralCt_text[i].setBounds(startWidth + 120, j + 12, 40, 20);
						
						sem1PresCt_text[i] = new JTextField(sem1PresCt);
						sem1PresCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem1PresCt_text[i].setBounds(startWidth + 120, j + 12, 40, 20);
						if (stdClass.equalsIgnoreCase("IX")) {
							dataPanel.add(sem1PresCt_text[i]);
						} else{
							dataPanel.add(sem1OralCt_text[i]);
						}

						sem1AssignCt_text[i] = new JTextField(sem1AssignCt);
						sem1AssignCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem1AssignCt_text[i].setBounds(startWidth + 180, j + 12, 40, 20);
						
						sem1McapCt_text[i] = new JTextField(sem1McapCt);
						sem1McapCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem1McapCt_text[i].setBounds(startWidth + 180, j + 12, 40, 20);
						if (stdClass.equalsIgnoreCase("IX")) {
							dataPanel.add(sem1McapCt_text[i]);
						} else{
							dataPanel.add(sem1AssignCt_text[i]);
						}

						sem1WriteCt_text[i] = new JTextField(sem1WriteCt);
						sem1WriteCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem1WriteCt_text[i].setBounds(startWidth + 240, j + 12, 40, 20);
						dataPanel.add(sem1WriteCt_text[i]);

						sem1PractCt_text[i] = new JTextField(sem1PractCt);
						sem1PractCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem1PractCt_text[i].setBounds(startWidth + 300, j + 12, 40, 20);
						dataPanel.add(sem1PractCt_text[i]);
						
						sem1ActCt_text[i] = new JTextField(sem1ActCt);
						sem1ActCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem1ActCt_text[i].setBounds(startWidth + 360, j + 12, 40, 20);
						if (stdClass.equalsIgnoreCase("IX")){
							dataPanel.add(sem1ActCt_text[i]);
						}

						pipe_labels8[i] = new JLabel("|");
						pipe_labels8[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						pipe_labels8[i].setBounds(630, j, 40, 50);
						dataPanel.add(pipe_labels8[i]);

						sem2DailyObsCt_text[i] = new JTextField(sem2DobsCt);
						sem2DailyObsCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem2DailyObsCt_text[i].setBounds(startWidth + 380, j + 12, 40, 20);
						if (!stdClass.equalsIgnoreCase("IX") && !stdClass.equalsIgnoreCase("X")
								&& !stdClass.equalsIgnoreCase("XI") && !stdClass.equalsIgnoreCase("XII")) {
							dataPanel.add(sem2DailyObsCt_text[i]);
						}

						sem2ObtCt_text[i] = new JTextField(sem2ObtCt);
						sem2ObtCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem2ObtCt_text[i].setBounds(startWidth + 440, j + 12, 40, 20);
						dataPanel.add(sem2ObtCt_text[i]);

						sem2OralCt_text[i] = new JTextField(sem2OralCt);
						sem2OralCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem2OralCt_text[i].setBounds(startWidth + 500, j + 12, 40, 20);
						
						sem2PresCt_text[i] = new JTextField(sem2PresCt);
						sem2PresCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem2PresCt_text[i].setBounds(startWidth + 500, j + 12, 40, 20);
						if (stdClass.equalsIgnoreCase("IX")) {
							dataPanel.add(sem2PresCt_text[i]);
						} else{
							dataPanel.add(sem2OralCt_text[i]);
						}

						sem2AssignCt_text[i] = new JTextField(sem2AssignCt);
						sem2AssignCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem2AssignCt_text[i].setBounds(startWidth + 555, j + 12, 40, 20);
						
						sem2McapCt_text[i] = new JTextField(sem2McapCt);
						sem2McapCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem2McapCt_text[i].setBounds(startWidth + 555, j + 12, 40, 20);
						if (stdClass.equalsIgnoreCase("IX")) {
							dataPanel.add(sem2McapCt_text[i]);
						} else{
							dataPanel.add(sem2AssignCt_text[i]);
						}

						sem2WriteCt_text[i] = new JTextField(sem2WriteCt);
						sem2WriteCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem2WriteCt_text[i].setBounds(startWidth + 620, j + 12, 40, 20);
						dataPanel.add(sem2WriteCt_text[i]);

						sem2PractCt_text[i] = new JTextField(sem2PractCt);
						sem2PractCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem2PractCt_text[i].setBounds(startWidth + 680, j + 12, 40, 20);
						dataPanel.add(sem2PractCt_text[i]);
						
						sem2ActCt_text[i] = new JTextField(sem2ActCt);
						sem2ActCt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						sem2ActCt_text[i].setBounds(startWidth + 740, j + 12, 40, 20);
						if (stdClass.equalsIgnoreCase("IX")){
							dataPanel.add(sem2ActCt_text[i]);
						}*/

						pipe_labels6[i] = new JLabel("|");
						pipe_labels6[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						pipe_labels6[i].setBounds(1010+(incrementalTypeSem2*30), j, 40, 50);
						dataPanel.add(pipe_labels6[i]);
						//////////////////////////////////

						/*line2_labels[i] = new JLabel(
								"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						line2_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						line2_labels[i].setBounds(30, j + 12, 1100, 50);
						dataPanel.add(line2_labels[i]);*/
					}

					orderNo_label[i] = new JLabel(orderNo);
					orderNo_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));

					line1_labels[i] = new JLabel(lineStr);
					line1_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					line1_labels[i].setBounds(30, j + 10, 1200, 50);
					dataPanel.add(line1_labels[i]);

//					final int k = i;
//					sem1_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//							sem1_text[k].selectAll();
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							String sem1_text_focus = sem1_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem1_text[k].getText().trim();
//
//							if(commonObj.validateNumber(sem1_text_focus) && Double.parseDouble(sem1_text_focus) > Double.parseDouble(subMaxMinMap.get("sem1_dobs"))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem1_dobs"));
//								sem1_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem1_text_focus) && !sem1_text_focus.equalsIgnoreCase("AB") && !sem1_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem1_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_FDOB", sem1_text_focus);
//							}
//						}
//					});

//					sem1Obt_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//
//							sem1Obt_text[k].selectAll();
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							String subjectName = subName_label[k].getText().toString();
//							
//							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
//									: sem1Obt_text[k].getText().trim();
//
//							if(commonObj.validateNumber(sem1Obt_text_focus) && Double.parseDouble(sem1Obt_text_focus) > Double.parseDouble(subMaxMinMap.get("sem1_obt"))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem1_obt"));
//								sem1Obt_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem1Obt_text_focus) && !sem1Obt_text_focus.equalsIgnoreCase("AB") && !sem1Obt_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem1Obt_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_FOBT", sem1Obt_text_focus);
//							}
//						}
//					});
//
//					sem1Oral_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//
//							sem1Oral_text[k].selectAll();
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							
//							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem1Oral_text[k].getText().trim();
//
//							if(commonObj.validateNumber(sem1Oral_text_focus) && (Double.parseDouble(sem1Oral_text_focus) > Double.parseDouble(subMaxMinMap.get("sem1_oral")))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem1_oral"));
//								sem1Oral_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem1Oral_text_focus) && !sem1Oral_text_focus.equalsIgnoreCase("AB") && !sem1Oral_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem1Oral_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_FORA", sem1Oral_text_focus);
//							}
//						}
//					});
//					
//					sem1Pres_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//
//							sem1Pres_text[k].selectAll();
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							String sem1Pres_text_focus = sem1Pres_text[k].getText().trim().equalsIgnoreCase("") ? "0"
//									: sem1Pres_text[k].getText().trim();
//
//							if(commonObj.validateNumber(sem1Pres_text_focus) && Double.parseDouble(sem1Pres_text_focus) > Double.parseDouble(subMaxMinMap.get("sem1_pres"))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem1_pres"));
//								sem1Pres_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem1Pres_text_focus) && !commonObj.validateNumber(sem1Pres_text_focus) && !sem1Pres_text_focus.equalsIgnoreCase("AB") && !sem1Pres_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem1Pres_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_FPRE", sem1Pres_text_focus);
//							}
//						}
//					});
//					
//					sem1Mcap_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//
//							sem1Mcap_text[k].selectAll();
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							String sem1Mcap_text_focus = sem1Mcap_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem1Mcap_text[k].getText().trim();
//
//							if(commonObj.validateNumber(sem1Mcap_text_focus) && Double.parseDouble(sem1Mcap_text_focus) > Double.parseDouble(subMaxMinMap.get("sem1_mcap"))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem1_mcap"));
//								sem1Mcap_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem1Mcap_text_focus) && !sem1Mcap_text_focus.equalsIgnoreCase("AB") && !sem1Mcap_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem1Mcap_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_FMCA", sem1Mcap_text_focus);
//							}
//						}
//					});
//
//					sem1Assign_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//
//							sem1Assign_text[k].selectAll();
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem1Assign_text[k].getText().trim();
//
//							if(commonObj.validateNumber(sem1Assign_text_focus) && Double.parseDouble(sem1Assign_text_focus) > Double.parseDouble(subMaxMinMap.get("sem1_assign"))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem1_assign"));
//								sem1Assign_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem1Assign_text_focus) && !sem1Assign_text_focus.equalsIgnoreCase("AB") && !sem1Assign_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem1Assign_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_FASS", sem1Assign_text_focus);
//							}
//						}
//					});
//
//					sem1Write_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//
//							sem1Write_text[k].selectAll();
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem1Write_text[k].getText().trim();
//
//							if(commonObj.validateNumber(sem1Write_text_focus) && Double.parseDouble(sem1Write_text_focus) > Double.parseDouble(subMaxMinMap.get("sem1_write"))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem1_write"));
//								sem1Write_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem1Write_text_focus) && !sem1Write_text_focus.equalsIgnoreCase("AB") && !sem1Write_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem1Write_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_FWRI", sem1Write_text_focus);
//							}
//						}
//					});
//
//					sem1Pract_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//
//							sem1Pract_text[k].selectAll();
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem1Pract_text[k].getText().trim();
//							if(commonObj.validateNumber(sem1Pract_text_focus) && Double.parseDouble(sem1Pract_text_focus) > Double.parseDouble(subMaxMinMap.get("sem1_pract"))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem1_pract"));
//								sem1Pract_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem1Pract_text_focus) && !sem1Pract_text_focus.equalsIgnoreCase("AB") && !sem1Pract_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem1Pract_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_FPRA", sem1Pract_text_focus);
//							}
//						}
//					});
//					
//					if (stdClass.equalsIgnoreCase("IX")) {
//						sem1Act_text[i].addFocusListener(new FocusListener() {
//	
//							@Override
//							public void focusGained(FocusEvent arg0) {
//	
//								sem1Act_text[k].selectAll();
//							}
//	
//							@Override
//							public void focusLost(FocusEvent arg0) {
//								LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//								subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//								String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
//										? "0" : sem1Act_text[k].getText().trim();
//	
//								if(commonObj.validateNumber(sem1Act_text_focus) && Double.parseDouble(sem1Act_text_focus) > Double.parseDouble(subMaxMinMap.get("sem1_activity"))){
//									commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem1_activity"));
//									sem1Act_text[k].setText("0");
//								}
//								else if(!commonObj.validateNumber(sem1Act_text_focus) && !sem1Act_text_focus.equalsIgnoreCase("AB") && !sem1Act_text_focus.equalsIgnoreCase("MG")){
//									commonObj.showMessageDialog("Please enter AB or MG");
//									sem1Act_text[k].setText("0");
//								}
//								else{
//									grMarksDataMap.put(subName_label[k].getText()+"_FACT", sem1Act_text_focus);
//								}
//							}
//						});
//					}

//					if (!stdClass.equalsIgnoreCase("IX") && !stdClass.equalsIgnoreCase("X")
//							&& !stdClass.equalsIgnoreCase("XI") && !stdClass.equalsIgnoreCase("XII")) {
//						sem2DailyObs_text[i].addFocusListener(new FocusListener() {
//
//							@Override
//							public void focusGained(FocusEvent arg0) {
//
//								sem2DailyObs_text[k].selectAll();
//							}
//
//							@Override
//							public void focusLost(FocusEvent arg0) {
//								LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//								subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//								String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
//										? "0" : sem2DailyObs_text[k].getText().trim();
//
//								if(commonObj.validateNumber(sem2Dobs_text_focus) && Double.parseDouble(sem2Dobs_text_focus) > Double.parseDouble(subMaxMinMap.get("sem2_dobs"))){
//									commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem2_dobs"));
//									sem2DailyObs_text[k].setText("0");
//								}
//								else if(!commonObj.validateNumber(sem2Dobs_text_focus) && !sem2Dobs_text_focus.equalsIgnoreCase("AB") && !sem2Dobs_text_focus.equalsIgnoreCase("MG")){
//									commonObj.showMessageDialog("Please enter AB or MG");
//									sem2DailyObs_text[k].setText("0");
//								}
//								else{
//									grMarksDataMap.put(subName_label[k].getText()+"_SDOB", sem2Dobs_text_focus);
//								}
//							}
//						});
//
//					}
//
//					sem2Obt_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//
//							sem2Obt_text[k].selectAll();
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
//									: sem2Obt_text[k].getText().trim();
//							
//							if(commonObj.validateNumber(sem2Obt_text_focus) && Double.parseDouble(sem2Obt_text_focus) > Double.parseDouble(subMaxMinMap.get("sem2_obt"))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem2_obt"));
//								sem2Obt_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem2Obt_text_focus) && !sem2Obt_text_focus.equalsIgnoreCase("AB") && !sem2Obt_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem2Obt_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_SOBT", sem2Obt_text_focus);
//							}
//						}
//					});
//
//					sem2Oral_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//
//							sem2Oral_text[k].selectAll();
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem2Oral_text[k].getText().trim();
//							
//							if(commonObj.validateNumber(sem2Oral_text_focus) && Double.parseDouble(sem2Oral_text_focus) > Double.parseDouble(subMaxMinMap.get("sem2_oral"))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem2_oral"));
//								sem2Oral_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem2Oral_text_focus) && !sem2Oral_text_focus.equalsIgnoreCase("AB") && !sem2Oral_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem2Oral_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_SORA", sem2Oral_text_focus);
//							}
//						}
//					});
//
//					sem2Assign_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//
//							sem2Assign_text[k].selectAll();
//
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem2Assign_text[k].getText().trim();
//							
//							if(commonObj.validateNumber(sem2Assign_text_focus) && Double.parseDouble(sem2Assign_text_focus) > Double.parseDouble(subMaxMinMap.get("sem2_assign"))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem2_assign"));
//								sem2Assign_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem2Assign_text_focus) && !sem2Assign_text_focus.equalsIgnoreCase("AB") && !sem2Assign_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem2Assign_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_SASS", sem2Assign_text_focus);
//							}
//						}
//					});
//
//					sem2Pres_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//
//							sem2Pres_text[k].selectAll();
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							String sem2Pres_text_focus = sem2Pres_text[k].getText().trim().equalsIgnoreCase("") ? "0"
//									: sem2Pres_text[k].getText().trim();
//							
//							if(commonObj.validateNumber(sem2Pres_text_focus) && Double.parseDouble(sem2Pres_text_focus) > Double.parseDouble(subMaxMinMap.get("sem2_pres"))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem2_pres"));
//								sem2Pres_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem2Pres_text_focus) && !sem2Pres_text_focus.equalsIgnoreCase("AB") && !sem2Pres_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem2Pres_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_SPRE", sem2Pres_text_focus);
//							}
//						}
//					});
//					
//					sem2Mcap_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//
//							sem2Mcap_text[k].selectAll();
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							String sem2Mcap_text_focus = sem2Mcap_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem2Mcap_text[k].getText().trim();
//							
//							if(commonObj.validateNumber(sem2Mcap_text_focus) && Double.parseDouble(sem2Mcap_text_focus) > Double.parseDouble(subMaxMinMap.get("sem2_mcap"))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem2_mcap"));
//								sem2Mcap_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem2Mcap_text_focus) && !sem2Mcap_text_focus.equalsIgnoreCase("AB") && !sem2Mcap_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem2Mcap_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_SMCA", sem2Mcap_text_focus);
//							}
//						}
//					});
//					
//					sem2Write_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//
//							sem2Write_text[k].selectAll();
//
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem2Write_text[k].getText().trim();
//							
//							if(commonObj.validateNumber(sem2Write_text_focus) && Double.parseDouble(sem2Write_text_focus) > Double.parseDouble(subMaxMinMap.get("sem2_write"))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem2_write"));
//								sem2Write_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem2Write_text_focus) && !sem2Write_text_focus.equalsIgnoreCase("AB") && !sem2Write_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem2Write_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_SWRI", sem2Write_text_focus);
//							}
//						}
//					});
//
//					sem2Pract_text[i].addFocusListener(new FocusListener() {
//
//						@Override
//						public void focusGained(FocusEvent arg0) {
//
//							sem2Pract_text[k].selectAll();
//
//						}
//
//						@Override
//						public void focusLost(FocusEvent arg0) {
//							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//							subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem2Pract_text[k].getText().trim();
//							
//							if(commonObj.validateNumber(sem2Pract_text_focus) && Double.parseDouble(sem2Pract_text_focus) > Double.parseDouble(subMaxMinMap.get("sem2_pract"))){
//								commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem2_pract"));
//								sem2Pract_text[k].setText("0");
//							}
//							else if(!commonObj.validateNumber(sem2Pract_text_focus) && !sem2Pract_text_focus.equalsIgnoreCase("AB") && !sem2Pract_text_focus.equalsIgnoreCase("MG")){
//								commonObj.showMessageDialog("Please enter AB or MG");
//								sem2Pract_text[k].setText("0");
//							}
//							else{
//								grMarksDataMap.put(subName_label[k].getText()+"_SPRA", sem2Pract_text_focus);
//							}
//						}
//					});
//					
//					if (stdClass.equalsIgnoreCase("IX")) {
//						sem2Act_text[i].addFocusListener(new FocusListener() {
//	
//							@Override
//							public void focusGained(FocusEvent arg0) {
//	
//								sem2Act_text[k].selectAll();
//	
//							}
//	
//							@Override
//							public void focusLost(FocusEvent arg0) {
//								LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
//								subMaxMinMap = maxSubMarks.get(subName_label[k].getText().toString());
//								String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
//										? "0" : sem2Act_text[k].getText().trim();
//								
//								if(commonObj.validateNumber(sem2Act_text_focus) && Double.parseDouble(sem2Act_text_focus) > Double.parseDouble(subMaxMinMap.get("sem2_activity"))){
//									commonObj.showMessageDialog("Marks cannot be greater than "+subMaxMinMap.get("sem2_activity"));
//									sem2Act_text[k].setText("0");
//								}
//								else if(!commonObj.validateNumber(sem2Act_text_focus) && !sem2Act_text_focus.equalsIgnoreCase("AB") && !sem2Act_text_focus.equalsIgnoreCase("MG")){
//									commonObj.showMessageDialog("Please enter AB or MG");
//									sem2Act_text[k].setText("0");
//								}
//								else{
//									grMarksDataMap.put(subName_label[k].getText()+"_SACT", sem2Act_text_focus);
//								}
//							}
//						});
//					}

					//////////////////////
					i++;
				}

				int n = 0;
				int k = l + 20;
				l = l + 20;

				JLabel notes_label = new JLabel("NOTE : ");
				notes_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
				notes_label.setBounds(40, k, 1100, 50);
				notes_label.setForeground(Color.RED);
				dataPanel.add(notes_label);

				if (!stdClass.equalsIgnoreCase("IX") && !stdClass.equalsIgnoreCase("X")
						&& !stdClass.equalsIgnoreCase("XI") && !stdClass.equalsIgnoreCase("XII")) {
					JLabel abbrevations_label = new JLabel(
							"               " + (++n) + ") DOBS - Daily Observation, OBT - Open Book Test");
					abbrevations_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
					abbrevations_label.setBounds(40, k, 1100, 50);
					abbrevations_label.setForeground(Color.RED);
					dataPanel.add(abbrevations_label);
					k = k + 15;
				}

				JLabel note_label = new JLabel(
						"" + (++n) + ") If any category is not applicable put 0 i.e. zero instead of NA/Blank.");
				note_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
				note_label.setBounds(85, k, 1100, 50);
				note_label.setForeground(Color.RED);
				dataPanel.add(note_label);

				// ///////save data box///////////////////////
				JLabel sep_label = new JLabel(
						"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				sep_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				sep_label.setBounds(40, l + 30, 1600, 50);
				dataPanel.add(sep_label);

				JButton SaveButton = new JButton("Save");
				SaveButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				SaveButton.setBounds(mainCentre - 150, l + 65, 150, 25);
				dataPanel.add(SaveButton);

				SaveButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						try {
							int reply = 0;
							boolean insertSubject = false;
							boolean validateSubList = true;
							reply = JOptionPane.showConfirmDialog(null,
									"This update will reset the marks entered in Marks Entry session. \n "
											+ "Would You Like to update Marks for GR No. "+retGrNo+"?",
									"Confirm update", JOptionPane.YES_NO_OPTION);

							if (reply == JOptionPane.YES_OPTION && dbValidate.connectDatabase(sessionData1)) {
								dbValidate.updateSubMarksFromMap(sessionData1, grMarksDataMap, academicYearClass, stdClass, divClass, maxSubMarks, examClass, studentOptSubAllotMap);
							}
						} catch (Exception e1) {
							commonObj.logException(e1);
						} finally {
							dbValidate.closeDatabase(sessionData);
						}
					}
				});

				JLabel sep1_label = new JLabel(
						"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				sep1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				sep1_label.setBounds(40, l + 70, 1600, 50);
				dataPanel.add(sep1_label);
				// /////////////////////////////
			}
		}
		// ////////////////data panel ends/////////////////////////////////

		JScrollPane jsp;
		jsp = new JScrollPane(dataPanel);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollAreaPanel.add(jsp, BorderLayout.EAST);
		bottombandPanel.add(scrollAreaPanel, BorderLayout.SOUTH);
		mainPanel.add(bottombandPanel, BorderLayout.SOUTH);
		// ////////////////////////////////
		panelHome.add(mainPanel, BorderLayout.EAST);
		panelHome.setSize(screenWidth, screenHeight);
	
		frame.setVisible(true);
	}
}
