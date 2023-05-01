package org.com.maauli;

import java.awt.BorderLayout;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

import com.lowagie.text.DocumentException;

public class Result extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JComboBox studentcombo;

	static String std = "";

	static String div = "";

	static String selectStd = "Select";

	static String selectDiv = "Select";

	static String selectYear = "Year";

	static String examClass = "Select";
	
	static String examDisplay = "";
	
	static String examCategory = "";

	static String academicYearClass = "";

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

	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static SessionData sessionData = new SessionData();

	static Logger logger = Logger.getLogger(Result.class.getName());
	
	static TreeMap<String, String> studentLCMap = new TreeMap<String, String>();

	static JFrame frame = null;

	static int retCount = 0;

	static int scrollHeight = 0;
	
	static int scrollWidth = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

	static List<String> allGrList = new ArrayList<String>();

	static List<String> selectedAllGrList = new ArrayList<String>();

	static boolean setSelected = false;

	static String dateLeaving = "";

	static String dateIssue = "";

	static String reason = "";

	static String remark = "";

	static String conduct = "";

	static String grClass = "";

	static String stdClass = "";

	static String divClass = "";

	static String firstNameClass = "";

	static String lastNameClass = "";

	static String fatherNameClass = "";

	static String yearClass = "";

	static String lcTypeClass = "Select";

	static String user_name = "";

	static String user_role = "";

	static String yearList = "";

	static LeavingCertAction LeavingCertActionObj = new LeavingCertAction();

	static Common commonObj = new Common();

	static DBValidate dbValidate = new DBValidate();

	static List<String> subjectTitleList = new ArrayList<String>();

	static String horLineClass = "";
	
	static List<String> studentResultSem1Map = new ArrayList<String>();
	static List<String> studentResultSem2Map = new ArrayList<String>();
	static List<String> studentResultFinalMap = new ArrayList<String>();
	static List<String> studentResultMap = new ArrayList<String>();
	
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

	@SuppressWarnings("unchecked")
	public Result(SessionData sessionData1, String retGr_no, String retStd, String retDiv, String retLastName,
			String retFirstName, String retFatherName, List<String> retResultList, boolean displayResult,
			boolean retSelected, String academicYear, String sec, String retUserName, String retUserRole,
			String retFrom, String retTo, String retExam, List<String> retSem1ResultList, 
			List<String> retSem2ResultList, List<String> retGrList) {

		logger.info("======Result display========");
		System.gc();
		studentLCMap.clear();
		String horLine = "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
		horLineClass = horLine;
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		grClass = retGr_no;
		stdClass = retStd;
		divClass = retDiv;
		firstNameClass = retFirstName;
		lastNameClass = retLastName;
		fatherNameClass = retFatherName;
		yearClass = academicYear;
		entrytCnt = 0;

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
		studentResultMap = null;
		studentResultSem1Map = null;
		studentResultSem2Map = null;
		
		studentResultMap = retResultList;
		studentResultSem1Map = retSem1ResultList;
		studentResultSem2Map = retSem2ResultList;

		section = sec;
		logger.info("section :: " + section);
		std = bundle.getString(section.toUpperCase() + "_STD");
		logger.info("std :: " + std);
		div = bundle.getString(section.toUpperCase() + "_DIV");
		logger.info("div :: " + div);
		secName = bundle.getString(section.toUpperCase() + "_SEC");
		logger.info("secName :: " + secName);
		examCategory = bundle.getString("EXAM_CATEGORY_RESULT");
		logger.info("examCategory :: " + examCategory);
		img_path = bundle.getString("IMAGE_PATH");
		logger.info("img_path :: " + img_path);
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
		if (examClass.equalsIgnoreCase("Semester 1")) {
			examDisplay = "Sem 1";
		}
		if (examClass.equalsIgnoreCase("Semester 2")) {
			examDisplay = "Sem 2";
		}
		if (examClass.equalsIgnoreCase("Final")) {
			examDisplay = "Final";
		}
		logger.info("examClass :: " + examClass);

		String todayDate = commonObj.getCurrentDate();
		academicYearClass = commonObj.getAcademicYear(todayDate);
		if (!academicYear.equalsIgnoreCase("")) {
			academicYearClass = academicYear;
		}
		try {
			if (dbValidate.connectDatabase(sessionData)) {
				yearList = dbValidate.findYearList(sessionData, "MARKS_ENTRY");
				studentLCMap = dbValidate.findStudentLCList(sessionData1, "", stdClass, divClass, "", "", "", academicYearClass, "", "", section);

				// to get studentResultMap
				if (displayResult && studentResultMap.size() > 0) {
					// ////////to get subject title list
					subjectTitleList = dbValidate.findSubjectTitleList(sessionData, retStd, "", academicYearClass);
					subjectTitleList.remove(0);
					subjectTitleList.remove(0);
					logger.info("subjectTitleList size : " + subjectTitleList.size());
				}
				
				int addOnCount = subjectTitleList.size() - 6;
				if(addOnCount > 0){
					scrollWidth = addOnCount * 60;
					for(int i = 0; i < addOnCount; i++){
						horLineClass = horLineClass + "------------";
					}
				}
			}
		} catch (Exception e1) {
			commonObj.logException(e1);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
		if (!yearList.contains(academicYearClass)) {
			yearList = academicYearClass + "," + yearList;
		}
		if (!academicYear.equalsIgnoreCase("")) {
			yearList = academicYear + "," + yearList;
		}

		logger.info("academicYearClass ==" + academicYearClass);

		allGrList.clear();
//		selectedAllGrList.clear();
		selectedAllGrList = retGrList;
		// foundStudentList = retResultList;
		retCount = studentResultMap.size();
		logger.info("foundStudentList size ==> " + retCount);
		setSelected = retSelected;
		if (setSelected) {
			selectAllCount = retCount;
		}
		scrollHeight = (retCount - 6) * 30; // to adjust the perfect scroll height
		if (scrollHeight < 0)
			scrollHeight = 0;

		setVisible(false);
		dispose();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame = new JFrame("Welcome to "+sessionData1.getAppName());
		screenWidth = commonObj.screeWidth();
		screenHeight = commonObj.screeHeight();
		mainCentre = (screenWidth - 150) / 2;

		frame.setSize(screenWidth, screenHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
        
       	// Added for MAC ---> Function to set visible status of JFrame.
        if((System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
        	setVisible(true);
        }

		frame.add(panel);
		placeComponents(panel);

		frame.setVisible(true);
	}

	private static void placeComponents(final JPanel panelHome) {

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
					new MyAccount(sessionData);
				} catch (Exception e) {
					commonObj.logException(e);
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
						String[] arguments = new String[] {""};
		                LoginView.main(arguments);
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
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
					new Welcome(sessionData, user_name, user_role);
				} catch (Exception e) {
					commonObj.logException(e);
				}
			}
		});

		JButton studentButton = new JButton("Student");
		studentButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		studentButton.setBounds(10, 100, 130, 35);
		leftPanel.add(studentButton);

		studentButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Student(sessionData, section, user_name, user_role);
				frame.setVisible(false);
			}
		});

		JButton staffButton = new JButton("Staff");
		staffButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		staffButton.setBounds(10, 150, 130, 35);
//		leftPanel.add(staffButton);

		staffButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Welcome(sessionData, user_name, user_role);
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
				// new Welcome(user_name,user_role);
			}
		});

		JButton searchButton = new JButton("Find");
		searchButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		searchButton.setBounds(10, 300, 130, 35);
		leftPanel.add(searchButton);

		searchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LinkedHashMap<String,LinkedHashMap<String, String>> studMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
				new FindStudent(sessionData, "", "", "", "", "", "", studMap, section, user_name,
						user_role, false);
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

		JButton classAllotButton = new JButton("Class Allot");
		classAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		classAllotButton.setBounds(130, 50, 120, 24);
		topbandPanel.add(classAllotButton);

		classAllotButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				panelHome.removeAll();///to remve entire panel
				new ClassAllotment(sessionData, "", "", "", "", "", "", null, false, "", "",
						"", section, user_name, user_role, "");
			}
		});

		JButton subAllotButton = new JButton("Subject Allot");
		subAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		subAllotButton.setBounds(260, 50, 130, 24);// 620, 50, 150, 24
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
		marksButton.setBounds(400, 50, 130, 24);
		topbandPanel.add(marksButton);

		marksButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
                List markList = new ArrayList();
                new MarksEntry(sessionData, "", markList, "", false, "", "", "", "", "", "",
                    "", "", "", section, user_name, user_role, "", "");
			}
		});

		JButton remarkButton = new JButton("Remarks");
		remarkButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		remarkButton.setBounds(540, 50, 100, 24); // 300, 50, 150, 24
		remarkButton.setEnabled(true);topbandPanel.add(remarkButton);

		remarkButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
                List markList = new ArrayList();
                RemarksEntry remarksEntryObject = RemarksEntry.getInstance();
                remarksEntryObject.getRemarksEntry(sessionData, "", "", false, "", "", "", "", "", "", "",
                    "", "", section, user_name, user_role, "", "", "");
            }
        });
		
		JButton resultPrintButton = new JButton("Result");
		resultPrintButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		resultPrintButton.setBounds(650, 50, 90, 24); // 300, 50, 150, 24
		resultPrintButton.setBackground(Color.GREEN);
		topbandPanel.add(resultPrintButton);

		resultPrintButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });

		JButton attendanceButton = new JButton("Attendance");
		attendanceButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		attendanceButton.setBounds(750, 50, 130, 24); // 300, 50, 150, 24
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
		reportButton.setBounds(890, 50, 100, 24);
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
				Dimension size = new Dimension(screenWidth - 150, 150);// change
																		// height
																		// to
																		// change
																		// scrolling
																		// area
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
			commonObj.logException(e12);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
		
		if (!academicYearClass.equalsIgnoreCase("")){
			yearList = academicYearClass + "," + yearList;
		}

		String[] academicYearList = yearList.split(",");
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
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
		if(!stdClass.equalsIgnoreCase("All") && !stdClass.equalsIgnoreCase("Select") && dbValidate.connectDatabase(sessionData)){
			presentDiv_combo.removeAllItems();
			presentDiv_combo.addItem("Select");
			String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdClass, section, 
					"PRESENT_DIV", "PRESENT_STD", "class_allotment",academicYearClass);
			
			for (String retval: divAvailabe.split(",")) {
				presentDiv_combo.addItem(retval);
			}
		}
		presentDiv_combo.setSelectedItem(divClass);
		findPanel.add(presentDiv_combo);

		// //////Roll No//////////////////////
		JLabel rollNo_label = new JLabel("Roll No. :");
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
		findPanel.add(to_text);

		// /////////name radio///////////////30, 80, 120, 50
		final JRadioButton name_radio = new JRadioButton();
		name_radio.setBounds(30, 55, 20, 20);
		if(!lastNameClass.equalsIgnoreCase("") || !firstNameClass.equalsIgnoreCase("") || !fatherNameClass.equalsIgnoreCase("")){
			name_radio.setSelected(true);
		}
		findPanel.add(name_radio);

		// /////////////Last Name//////////////
		JLabel lastName_label = new JLabel("Last Name :");
		lastName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_label.setBounds(60, 40, 120, 50);
		findPanel.add(lastName_label);

		final JTextField lastName_text = new JTextField(lastNameClass);
		lastName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_text.setBounds(160, 50, 130, 25);
		findPanel.add(lastName_text);
		// //////////////////////////////////
		// /////////////First Name//////////////
		JLabel firstName_label = new JLabel("First Name :");
		firstName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_label.setBounds(310, 40, 120, 50);
		findPanel.add(firstName_label);

		final JTextField firstName_text = new JTextField(firstNameClass);
		firstName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_text.setBounds(410, 50, 130, 25);
		findPanel.add(firstName_text);
		// //////////////////////////////////
		// /////////////Father Name//////////////
		JLabel fatherName_label = new JLabel("Father Name :");
		fatherName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_label.setBounds(580, 40, 120, 50);
		findPanel.add(fatherName_label);

		final JTextField fatherName_text = new JTextField(fatherNameClass);
		fatherName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_text.setBounds(700, 50, 130, 25);
		findPanel.add(fatherName_text);
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

//		examCategory = examClass + "," + examCategory;
		String[] examList = examCategory.split(",");
		DefaultComboBoxModel model = new DefaultComboBoxModel(examList);
		final JComboBox exam_combo = new JComboBox(model);
//		if(!stdClass.equalsIgnoreCase("IX") && !stdClass.equalsIgnoreCase("X") && 
//				!stdClass.equalsIgnoreCase("XI") && !stdClass.equalsIgnoreCase("XII")){
//				exam_combo.removeItem("Final");
//		}
//		else if(model.getIndexOf("Final") == -1 ){
//			exam_combo.addItem("Final");;
//		}
		exam_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		exam_combo.setSelectedItem(examClass);
		exam_combo.setBounds(120, 90, 120, 25);
		findPanel.add(exam_combo);

		final JButton updateResultButton = new JButton("Update Result");
		updateResultButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		updateResultButton.setBounds(310, 90, 150, 25);// 620, 50, 150, 24
		
		int initialYearFromAcadSel = Integer.parseInt(academicYearClass.substring(0, academicYearClass.indexOf("-")));
		String todayDate = commonObj.getCurrentDate();
		String academic = commonObj.getAcademicYear(todayDate);
		int initialYearFromAcademic = Integer.parseInt(academic.substring(0, academic.indexOf("-")));
//		if(initialYearFromAcadSel < initialYearFromAcademic){
//			updateResultButton.setEnabled(false);
//		}
//		else{
//			updateResultButton.setEnabled(true);
//		}
		if(examClass.equalsIgnoreCase("FUT") || examClass.equalsIgnoreCase("SUT") || examClass.equalsIgnoreCase("SELECT")) {
			updateResultButton.setEnabled(false);
		}
		else{
			updateResultButton.setEnabled(true);
		}
		
		int stdClassInt = 0;
		if(!stdClass.equalsIgnoreCase("")){
			stdClassInt = commonObj.RomanToInteger(stdClass);
			if (stdClassInt < 9 && examClass.equalsIgnoreCase("Final")) {
				try {
//					updateResultButton.setEnabled(false);
					findPanel.revalidate();
					findPanel.repaint();
				} catch (Exception e2) {
					commonObj.logException(e2);
				}
			}
		}
//		updateResultButton.setEnabled(false);
		findPanel.add(updateResultButton);

		final JButton printUnitTestResultButton = new JButton();
		printUnitTestResultButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		printUnitTestResultButton.setBounds(490, 90, 150, 25);// 620, 50, 150, 24
//		printUnitTestResultButton.setEnabled(false);
//		findPanel.add(printUnitTestResultButton);
		
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
		
		final JCheckBox updateCheck = new JCheckBox();
		updateCheck.setBorder(null);
		updateCheck.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		updateCheck.setBounds(700, 98, 13, 13);
		
		
		JLabel updateCheckLabel = new JLabel("Enable Update");
		updateCheckLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		updateCheckLabel.setBounds(720, 98, 150, 13);
		
		if(sessionData.getUserRole().equalsIgnoreCase("ADMINISTRATOR")) {
			findPanel.add(updateCheck);
			findPanel.add(updateCheckLabel);
		}
		
		updateCheck.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String acadSel = (String) academicYear_combo.getSelectedItem();
				int initialYearFromAcadSel = Integer.parseInt(acadSel.substring(0, acadSel.indexOf("-")));
				String todayDate = commonObj.getCurrentDate();
				String academic = commonObj.getAcademicYear(todayDate);
				int initialYearFromAcademic = Integer.parseInt(academic.substring(0, academic.indexOf("-")));
				int reply = 0;
				if(updateCheck.isSelected()) {
					reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to update result for previous year?", "Confirm validate", JOptionPane.YES_NO_OPTION);
				}
				
				if(initialYearFromAcadSel <= initialYearFromAcademic && updateCheck.isSelected() && reply == JOptionPane.YES_OPTION){
					updateResultButton.setEnabled(true);
				}
				else if(initialYearFromAcadSel < initialYearFromAcademic || reply == JOptionPane.NO_OPTION){
//					updateResultButton.setEnabled(false);
					updateCheck.setSelected(false);
				}
			}
		});
		
		
		updateResultButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					boolean resultUpdateFlag = false;
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

					academicSel = (String) academicYear_combo.getSelectedItem() == null ? "" : (String) academicYear_combo.getSelectedItem();
					std = (String) Std_combo.getSelectedItem() == null ? "" : (String) Std_combo.getSelectedItem();
					div = (String) presentDiv_combo.getSelectedItem() == null ? "" : (String) presentDiv_combo.getSelectedItem();
					from = from_text.getText() == null ? "" : from_text.getText();
					to = to_text.getText() == null ? "" : to_text.getText();
					firstName = firstName_text.getText() == null ? "" : firstName_text.getText();
					lastName = lastName_text.getText() == null ? "" : lastName_text.getText();
					fatherName = fatherName_text.getText() == null ? "" : fatherName_text.getText();
					exam = (String) exam_combo.getSelectedItem() == null ? "" : (String) exam_combo.getSelectedItem();

					if (academicSel.equalsIgnoreCase("Year")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic year.");
					} else if (std.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Std.");
					} else if (div.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Div.");
					} else if (!lastName.equalsIgnoreCase("") && !commonObj.validateAlphaNum(lastName)
							&& name_radio.isSelected()) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid LastName");
					} else if (!firstName.equalsIgnoreCase("") && !commonObj.validateAlphaNum(firstName)
							&& name_radio.isSelected()) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid FirstName");
					} else if (!fatherName.equalsIgnoreCase("") && !commonObj.validateAlphaNum(fatherName)
							&& name_radio.isSelected()) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid FatherName");
					} else if (exam.equalsIgnoreCase("Select") || exam.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Exam");
					}

					if (academicSel.equalsIgnoreCase("Year")) {
						academicSel = "";
					}
					
					if(validateFields){
						if (dbValidate.connectDatabase(sessionData)) {
							//// Update result//////
							resultUpdateFlag = dbValidate.updateResult(sessionData, academicSel, std, div,
									(String) exam_combo.getSelectedItem(), section, lastName.toUpperCase(),
									firstName.toUpperCase(), fatherName.toUpperCase());
	
							logger.info("resultUpdateFlag == " + resultUpdateFlag);
						}
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});
		
		printUnitTestResultButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					boolean resultUpdateFlag = false;
					boolean validateFields = true;
					String grNo = "";
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

					academicSel = (String) academicYear_combo.getSelectedItem() == null ? "" : (String) academicYear_combo.getSelectedItem();
					std = (String) Std_combo.getSelectedItem() == null ? "" : (String) Std_combo.getSelectedItem();
					div = (String) presentDiv_combo.getSelectedItem() == null ? "" : (String) presentDiv_combo.getSelectedItem();
					from = from_text.getText() == null ? "" : from_text.getText();
					to = to_text.getText() == null ? "" : to_text.getText();
					firstName = firstName_text.getText() == null ? "" : firstName_text.getText();
					lastName = lastName_text.getText() == null ? "" : lastName_text.getText();
					fatherName = fatherName_text.getText() == null ? "" : fatherName_text.getText();
					exam = (String) exam_combo.getSelectedItem() == null ? "" : (String) exam_combo.getSelectedItem();

					if (academicSel.equalsIgnoreCase("Year")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic year.");
					} else if (std.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Std.");
					} else if (div.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Div.");
					} else if (!lastName.equalsIgnoreCase("") && !commonObj.validateAlphaNum(lastName)
							&& name_radio.isSelected()) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid LastName");
					} else if (!firstName.equalsIgnoreCase("") && !commonObj.validateAlphaNum(firstName)
							&& name_radio.isSelected()) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid FirstName");
					} else if (!fatherName.equalsIgnoreCase("") && !commonObj.validateAlphaNum(fatherName)
							&& name_radio.isSelected()) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid FatherName");
					} else if (exam.equalsIgnoreCase("Select") || exam.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Exam");
					}

					if (academicSel.equalsIgnoreCase("Year")) {
						academicSel = "";
					}
					
					if(exam.equalsIgnoreCase("FUT") || exam.equalsIgnoreCase("SUT") || exam.equalsIgnoreCase("TEST")){
						exam = "OBT";
					}
					logger.info("academicSel == " + academicSel);
					logger.info("validateFields flag..." + validateFields);
					
					if(validateFields){
						if (dbValidate.connectDatabase(sessionData)) {
							//// print unit test result//////
							resultUpdateFlag = dbValidate.printUnitTestResult(sessionData, academicSel, std, div,
									(String) exam_combo.getSelectedItem(), section, lastName.toUpperCase(),
									firstName.toUpperCase(), fatherName.toUpperCase());
	
							logger.info("resultUpdateFlag == " + resultUpdateFlag);
						}
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});

		academicYear_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String acadSel = (String) academicYear_combo.getSelectedItem();
				int initialYearFromAcadSel = Integer.parseInt(acadSel.substring(0, acadSel.indexOf("-")));
				String todayDate = commonObj.getCurrentDate();
				String academic = commonObj.getAcademicYear(todayDate);
				int initialYearFromAcademic = Integer.parseInt(academic.substring(0, academic.indexOf("-")));
				if(initialYearFromAcadSel < initialYearFromAcademic){
//					updateResultButton.setEnabled(false);
				}
				else{
					updateResultButton.setEnabled(true);
				}
			}
		});
		
		Std_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				logger.info("inside Std_combo.addActionListener");
//				stdClass = (String) Std_combo.getSelectedItem();
				
				try{
					String stdSel = (String) Std_combo.getSelectedItem();
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
//					if(!stdSel.equalsIgnoreCase("IX") && !stdSel.equalsIgnoreCase("X") && 
//						!stdSel.equalsIgnoreCase("XI") && !stdSel.equalsIgnoreCase("XII")){
//						exam_combo.removeItem("Final");
//					}
//					else if(model.getIndexOf("Final") == -1 ){
//						exam_combo.addItem("Final");;
//					}
				} catch (Exception e1) {
					commonObj.logException(e1);
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
					name_radio.setSelected(false);
					name_radio.setEnabled(false);
					lastName_text.setText("");
					lastName_text.setEnabled(false);
					firstName_text.setText("");
					firstName_text.setEnabled(false);
					fatherName_text.setText("");
					fatherName_text.setEnabled(false);
				} else {
					name_radio.setEnabled(true);
					lastName_text.setEnabled(true);
					firstName_text.setEnabled(true);
					fatherName_text.setEnabled(true);
				}
			}
		});

		// ////////name_radio.addActionListener///////////
		name_radio.addActionListener(new ActionListener() {

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
		});

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

		//////// exam_combo action//////////////////////////
		exam_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				logger.info("inside exam_combo.addActionListener");
				String examSel = (String) exam_combo.getSelectedItem();
				String std = (String) Std_combo.getSelectedItem();
				int stdInt = 0;
				if(!std.equalsIgnoreCase("SELECT") && !std.equalsIgnoreCase("")) {
					stdInt = commonObj.RomanToInteger(std);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please select Std and Div");
				}
				updateCheck.setSelected(false);
				if (examSel.equalsIgnoreCase("FUT") || examSel.equalsIgnoreCase("SUT")) {
					try {
						printUnitTestResultButton.setText("Print " +examSel);
						findPanel.add(printUnitTestResultButton);
						updateResultButton.setEnabled(false);
						findPanel.revalidate();
						findPanel.repaint();
					} catch (Exception e2) {
						commonObj.logException(e2);
					}
				} 
				else if (stdInt < 9 && examSel.equalsIgnoreCase("Final")) {
					try {
//						updateResultButton.setEnabled(false);
						findPanel.revalidate();
						findPanel.repaint();
					} catch (Exception e2) {
						commonObj.logException(e2);
					}
				} 
				else {
					findPanel.remove(printUnitTestResultButton);
					updateResultButton.setEnabled(true);
					findPanel.revalidate();
					findPanel.repaint();
				}
				
				String acadSel = (String) academicYear_combo.getSelectedItem();
				int initialYearFromAcadSel = Integer.parseInt(acadSel.substring(0, acadSel.indexOf("-")));
				String todayDate = commonObj.getCurrentDate();
				String academic = commonObj.getAcademicYear(todayDate);
				int initialYearFromAcademic = Integer.parseInt(academic.substring(0, academic.indexOf("-")));
				if(initialYearFromAcadSel < initialYearFromAcademic){
//					updateResultButton.setEnabled(false);
				}
				else{
//					updateResultButton.setEnabled(true);
				}

			}
		});

		std_radio.setSelected(true);
		std_radio.setEnabled(false);
		if(!examClass.equalsIgnoreCase("FUT") && !examClass.equalsIgnoreCase("SUT")){
			findPanel.remove(printUnitTestResultButton);
		}
		else if(examClass.equalsIgnoreCase("FUT") || examClass.equalsIgnoreCase("SUT")){
			printUnitTestResultButton.setText("Print "+examClass);
			findPanel.add(printUnitTestResultButton);
		}
		// presentDiv_combo.setEnabled(false);
		from_text.setEnabled(false);
		to_text.setEnabled(false);
		if(lastNameClass.equalsIgnoreCase("")){
			lastName_text.setEnabled(false);
		}
		if(firstNameClass.equalsIgnoreCase("")){
			firstName_text.setEnabled(false);
		}
		if(fatherNameClass.equalsIgnoreCase("")){
			fatherName_text.setEnabled(false);
		}

		// /////////////Submit//////////////
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(930, 60, 130, 25);
//		submitButton.setEnabled(false);
		findPanel.add(submitButton);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean validateFields = true;
				String grNo = "";
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

				academicSel = (String) academicYear_combo.getSelectedItem() == null ? "" : (String) academicYear_combo.getSelectedItem();
				std = (String) Std_combo.getSelectedItem() == null ? "" : (String) Std_combo.getSelectedItem();
				div = (String) presentDiv_combo.getSelectedItem() == null ? "" : (String) presentDiv_combo.getSelectedItem();
				from = from_text.getText() == null ? "" : from_text.getText();
				to = to_text.getText() == null ? "" : to_text.getText();
				firstName = firstName_text.getText() == null ? "" : firstName_text.getText();
				lastName = lastName_text.getText() == null ? "" : lastName_text.getText();
				fatherName = fatherName_text.getText() == null ? "" : fatherName_text.getText();
				exam = (String) exam_combo.getSelectedItem() == null ? "" : (String) exam_combo.getSelectedItem();

				if (academicSel.equalsIgnoreCase("Year")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Academic year.");
				} else if (std.equalsIgnoreCase("Select")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Std.");
				} else if (div.equalsIgnoreCase("Select")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Div.");
				} else if (!lastName.equalsIgnoreCase("") && !commonObj.validateAlphaNum(lastName)
						&& name_radio.isSelected()) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please enter valid LastName");
				} else if (!firstName.equalsIgnoreCase("") && !commonObj.validateAlphaNum(firstName)
						&& name_radio.isSelected()) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please enter valid FirstName");
				} else if (!fatherName.equalsIgnoreCase("") && !commonObj.validateAlphaNum(fatherName)
						&& name_radio.isSelected()) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please enter valid FatherName");
				} else if (exam.equalsIgnoreCase("Select") || exam.equalsIgnoreCase("")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Exam");
				}

				if (academicSel.equalsIgnoreCase("Year")) {
					academicSel = "";
				}
				logger.info("academicSel == " + academicSel);
				logger.info("validateFields flag..." + validateFields);
				if (validateFields) {
					try {
						if (dbValidate.connectDatabase(sessionData)) {
							boolean changedFlag = dbValidate.marksEntryChanged(sessionData, std, div, academicSel);
							if(!changedFlag){
								if (exam.equalsIgnoreCase("Semester 1")) {
									studentResultSem1Map = dbValidate.findResultList(sessionData, academicSel, std, div, exam, "", "", section, lastName, firstName, fatherName);
									studentResultMap.clear();
									studentResultMap = studentResultSem1Map;
									logger.info("studentResultSem1Map size : " + studentResultSem1Map.size());
								} else if (exam.equalsIgnoreCase("Semester 2")) {
									studentResultSem2Map = dbValidate.findResultList(sessionData, academicSel, std, div, exam, "", "", section, lastName, firstName, fatherName);
									studentResultMap.clear();
									studentResultMap = studentResultSem2Map;
									logger.info("studentResultSem2Map size : " + studentResultSem2Map.size());
								} else if (exam.equalsIgnoreCase("FINAL") && (std.equalsIgnoreCase("IX") || std.equalsIgnoreCase("X") || std.equalsIgnoreCase("XI") || std.equalsIgnoreCase("XII"))) {
									studentResultFinalMap = dbValidate.findResultList(sessionData, academicSel, std, div, exam, "", "", section, lastName, firstName, fatherName);
									studentResultMap.clear();
									studentResultMap = studentResultFinalMap;
									logger.info("studentResultFinalMap size : " + studentResultFinalMap.size());
								} else if (exam.equalsIgnoreCase("FINAL")) {
									studentResultSem1Map = dbValidate.findResultList(sessionData, academicSel, std, div, "Semester 1", "", "", section, lastName, firstName, fatherName);
									studentResultSem2Map = dbValidate.findResultList(sessionData, academicSel, std, div, "Semester 2", "", "", section, lastName, firstName, fatherName);
//									studentResultFinalMap = dbValidate.findResultList(academicSel, std, div, exam, "", "", section, lastName, firstName, fatherName);
									studentResultMap.clear();
//									studentResultMap = studentResultFinalMap;
									studentResultMap.addAll(studentResultSem1Map);
									studentResultMap.addAll(studentResultSem2Map);
									logger.info("studentResultFinalMap size : " + studentResultFinalMap.size());
								}
								if (studentResultMap.size() == 0) {
									JOptionPane.showMessageDialog(null, "No data found");
								}
								frame.setVisible(false);
								findPanel.invalidate();
								panelHome.invalidate();
								System.gc();
								new Result(sessionData, grNo, std, div, lastName, firstName, fatherName, studentResultMap,
										true, false, academicSel, section, user_name, user_role, from, to, exam, 
										studentResultSem1Map, studentResultSem2Map, allGrList);
							}
							else{
								frame.setVisible(false);
								studentResultMap.clear();
								studentResultSem1Map.clear();
								studentResultSem2Map.clear();
								findPanel.invalidate();
								panelHome.invalidate();
								System.gc();
								new Result(sessionData, grNo, std, div, lastName, firstName, fatherName, studentResultMap,
										true, false, academicSel, section, user_name, user_role, from, to, exam, 
										studentResultSem1Map, studentResultSem2Map, allGrList);
							}
						}
					} catch (Exception e1) {
						commonObj.logException(e1);
						frame.setVisible(false);
		            	panelHome.removeAll();///to remve entire panel
		                List findList = new ArrayList();
						new Result(sessionData, "", "", "", "", "", "",	findList, false, false, "", section, user_name, 
								user_role, "", "", "", findList, findList, findList);
					} finally {
						dbValidate.closeDatabase(sessionData);
					}
				}
			}

		});
		// /////////////Reset//////////////
		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		resetButton.setBounds(930, 90, 130, 25);
		findPanel.add(resetButton);

		/*resetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
                List findList = new ArrayList();
				new Result(sessionData, "", "", "", "", "", "",	findList, false, false, "", section, user_name, user_role, "", "", "", findList, findList);
			}
		});*/
		
		resetButton.addActionListener(new ActionListener() {
	        public void actionPerformed( ActionEvent e )
	        {
	          new Thread() {
	            public void run() {
	              // Put the whole body of the existing actionPerformed method here
	            	frame.setVisible(false);
	                List findList = new ArrayList();
					new Result(sessionData, "", "", "", "", "", "",	findList, false, false, "", section, user_name, user_role, 
							"", "", "", findList, findList, findList);
	            }
	          }.start();
	         }
		});

		bottombandPanel.add(findPanel, BorderLayout.EAST);
		// ////////////////find panel ends/////////////////////////////////

		// //////////scrollArea panel/////////////////////////////////////
//		JPanel scrollAreaPanel = new JPanel(new BorderLayout());
//		scrollAreaPanel.setPreferredSize(new Dimension(screenWidth - 150, screenHeight - 337));

		// ///////////Data Panel/////////////
		final JPanel dataPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				Image img = new ImageIcon(img_path + img_mainband).getImage();
				Dimension size = new Dimension(screenWidth + scrollWidth, ((screenHeight - 330) + scrollHeight));// change
																											// height
																											// to
																											// change
																											// scrolling
																											// area
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				// g.drawImage(img, 0, 0, null);
				g.drawImage(img, 0, 0, screenWidth + scrollWidth, (screenHeight - 330) + scrollHeight, null);
			}
		};
		dataPanel.setLayout(null);
		if (studentResultMap.size() > 0) {
			int listSize = studentResultMap.size();
			JLabel[] sub_labels = new JLabel[subjectTitleList.size()];
			JLabel[] toolTip_sub_labels = new JLabel[subjectTitleList.size()];
			JLabel[] pipe_labels = new JLabel[subjectTitleList.size()];

			/////////header///////////////
			final JRadioButton all_radio = new JRadioButton();
			all_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			all_radio.setBounds(30, 13, 20, 20);
			all_radio.setSelected(setSelected);
			dataPanel.add(all_radio);

			JLabel sr_label = new JLabel("R.No.");
			sr_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sr_label.setBounds(60, 00, 120, 50);
			dataPanel.add(sr_label);

			JLabel pipe_label1 = new JLabel("|");
			pipe_label1.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label1.setBounds(100, 00, 120, 50);
			dataPanel.add(pipe_label1);

			JLabel name_label = new JLabel("Name");
			name_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			name_label.setBounds(150, 00, 120, 50);
			dataPanel.add(name_label);

			JLabel pipe_label4 = new JLabel("|");
			pipe_label4.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label4.setBounds(450, 00, 120, 50);
			dataPanel.add(pipe_label4);

			JLabel sem_label = new JLabel("Exam");
			sem_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sem_label.setBounds(470, 00, 120, 50);
			dataPanel.add(sem_label);

			JLabel pipe_label2 = new JLabel("|");
			pipe_label2.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label2.setBounds(520, 00, 120, 50);
			dataPanel.add(pipe_label2);
			
			JLabel result_label = new JLabel("Result");
			result_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			result_label.setBounds(540, 00, 120, 50);
			dataPanel.add(result_label);

			JLabel pipe_label3 = new JLabel("|");
			pipe_label3.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label3.setBounds(590, 00, 120, 50);
			dataPanel.add(pipe_label3);
			
			JLabel percent_label = new JLabel("%");
			percent_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			percent_label.setBounds(625, 00, 120, 50);
			dataPanel.add(percent_label);

			JLabel pipe_label5 = new JLabel("|");
			pipe_label5.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label5.setBounds(660, 00, 120, 50);
			dataPanel.add(pipe_label5);
			
			JLabel marks_label = new JLabel("Marks");
			marks_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			marks_label.setBounds(680, 00, 120, 50);
			dataPanel.add(marks_label);

			JLabel pipe_label6 = new JLabel("|");
			pipe_label6.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label6.setBounds(730, 00, 120, 50);
			dataPanel.add(pipe_label6);

			JLabel total_label = new JLabel("Total");
			total_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			total_label.setBounds(750, 00, 120, 50);
			dataPanel.add(total_label);

			JLabel pipe_label13 = new JLabel("|");
			pipe_label13.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label13.setBounds(800, 00, 120, 50);
			dataPanel.add(pipe_label13);
			
			int k = 820;
			int t = 0;
			for (int i = 0; i < subjectTitleList.size(); i++) {
				String subTitleDisp = subjectTitleList.get(i);
				if(subTitleDisp.contains("_")){
					subTitleDisp = subTitleDisp.substring(0, 1) + "/" + subTitleDisp.substring(subTitleDisp.indexOf("_")+1, subTitleDisp.indexOf("_")+2);
				}
				else if(subTitleDisp.length() > 3){
					subTitleDisp = subTitleDisp.substring(0, 3);
				}
				/*else{
					subTitleDisp = subTitleDisp.substring(0, 3);
				}*/
				sub_labels[i] = new JLabel(subTitleDisp);
				sub_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				sub_labels[i].setBounds(k + t, 00, 45, 50);
				sub_labels[i].setToolTipText(subjectTitleList.get(i));
				dataPanel.add(sub_labels[i]);

				pipe_labels[i] = new JLabel("|");
				pipe_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels[i].setBounds(k + 50, 00, 120, 50);
				dataPanel.add(pipe_labels[i]);

				k = k + 60;
				t = 10;

			}

			JLabel line_label = new JLabel(horLineClass);
			line_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			line_label.setBounds(30, 13, screenWidth + scrollWidth, 50);
			dataPanel.add(line_label);

			String[] studentArray = new String[listSize];
			studentArray = studentResultMap.toArray(studentArray);
			logger.info("listSize === " + listSize);
			logger.info("studentArray === " + studentArray.length);
			
			String[] studentSem1Array = new String[studentResultSem1Map.size()];
			studentSem1Array = studentResultSem1Map.toArray(studentSem1Array);
			
			String[] studentSem2Array = new String[studentResultSem2Map.size()];
			studentSem2Array = studentResultSem2Map.toArray(studentSem2Array);
			

			if (listSize > 0) {
				int j = 0;
				int l = 0;
				int sem1Index = 0;
				int sem2Index = 0;
				
				final JRadioButton[] sr_radio = new JRadioButton[listSize];
				JLabel[] roll_labels = new JLabel[listSize];
				final JLabel[] gr_labels = new JLabel[listSize];
				final JLabel[] dupLc_labels = new JLabel[listSize];
				JLabel[] name_labels = new JLabel[listSize];
				JLabel[] toolTip_name_labels = new JLabel[listSize];
				JLabel[] exam_labels = new JLabel[listSize];
				JLabel[] toolTip_exam_labels = new JLabel[listSize];
				JLabel[] result_labels = new JLabel[listSize];
				JLabel[] toolTip_result_labels = new JLabel[listSize];
				JLabel[] percent_labels = new JLabel[listSize];
				JLabel[] toolTip_percent_labels = new JLabel[listSize];
				JLabel[] marks_labels = new JLabel[listSize];
				JLabel[] toolTip_marks_labels = new JLabel[listSize];
				JLabel[] total_labels = new JLabel[listSize];
				JLabel[] toolTip_total_labels = new JLabel[listSize];
				JLabel[] line_labels = new JLabel[listSize];
				JButton[] view_button = new JButton[listSize];
				JLabel[] pipe_labels1 = new JLabel[listSize];
				JLabel[] pipe_labels2 = new JLabel[listSize];
				/*
				 * JLabel[] pipe_labels3 = new JLabel[listSize]; JLabel[]
				 * pipe_labels4 = new JLabel[listSize]; JLabel[] pipe_labels5 =
				 * new JLabel[listSize];
				 */

				String[] temp;
				String delimiter = ",";
				String str;

				for (int i = 0; i < listSize; i++) {
					j = j + 30;
					l = j + 50;

//					if(examClass.equalsIgnoreCase("Final") && !stdClass.equalsIgnoreCase("IX") && !stdClass.equalsIgnoreCase("X") && 
//							!stdClass.equalsIgnoreCase("XI") && !stdClass.equalsIgnoreCase("XII") && !stdClass.equalsIgnoreCase("JR KG") && !stdClass.equalsIgnoreCase("SR KG")){
					if(examClass.equalsIgnoreCase("Final") && !stdClass.equalsIgnoreCase("IX") && !stdClass.equalsIgnoreCase("X") && 
							!stdClass.equalsIgnoreCase("XI") && !stdClass.equalsIgnoreCase("XII")){
						//As numbering starts from Zero
						if(i % 2 == 0){
							str = studentSem1Array[sem1Index].toString();
						}else{
							str = studentSem2Array[sem2Index].toString();
						}
					}
					else{
						str = studentArray[i].toString();
					}
					
					temp = str.split(delimiter);
					String grNo = temp[0];
					String rollNo = temp[1];
					String name = temp[2] + " " + temp[3] + " " + temp[4];
					String percent = temp[5];
					String progress = temp[7];
					String result = temp[8];
					String marks = temp[9];
					String total = temp[10];
					if(progress.equalsIgnoreCase("NA")){
						progress = result;
					}

					sr_radio[i] = new JRadioButton();
					sr_radio[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sr_radio[i].setBounds(30, j + 15, 20, 20);
					sr_radio[i].setSelected(setSelected);
//					dataPanel.add(sr_radio[i]);

					gr_labels[i] = new JLabel(grNo);
					gr_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
//					allGrList.add(grNo);

					roll_labels[i] = new JLabel(rollNo);
					roll_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					roll_labels[i].setBounds(70, j + 2, 120, 50);
					roll_labels[i].setToolTipText(grNo);
//					dataPanel.add(roll_labels[i]);

					pipe_labels1[i] = new JLabel("|");
					pipe_labels1[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels1[i].setBounds(100, j, 120, 50);
//					dataPanel.add(pipe_labels1[i]);

					name_labels[i] = new JLabel(name);
					name_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					name_labels[i].setBounds(120, j + 2, 330, 50);
					if(studentLCMap.get(grNo) != null){
						name_labels[i].setForeground(Color.RED);
					}
//					name_labels[i].setToolTipText(name);
					toolTip_name_labels[i] = new JLabel();
					toolTip_name_labels[i].setToolTipText(name);
					toolTip_name_labels[i].setBounds(120, j + 2, 330, 40);
//					dataPanel.add(name_labels[i]);

					JLabel pipe_label11 = new JLabel("|");
					pipe_label11.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_label11.setBounds(450, j, 120, 50);
					dataPanel.add(pipe_label11);
					
//					if(examClass.equalsIgnoreCase("Final") && !stdClass.equalsIgnoreCase("IX") && !stdClass.equalsIgnoreCase("X") && 
//							!stdClass.equalsIgnoreCase("XI") && !stdClass.equalsIgnoreCase("XII") && !stdClass.equalsIgnoreCase("JR KG") && !stdClass.equalsIgnoreCase("SR KG")){
					if(examClass.equalsIgnoreCase("Final") && !stdClass.equalsIgnoreCase("IX") && !stdClass.equalsIgnoreCase("X") && 
							!stdClass.equalsIgnoreCase("XI") && !stdClass.equalsIgnoreCase("XII")){
						//As numbering starts from Zero
						if(i % 2 == 0){
							dataPanel.add(sr_radio[i]);
							if(setSelected){
								allGrList.add(grNo);
							}
							dataPanel.add(roll_labels[i]);
							dataPanel.add(pipe_labels1[i]);
							dataPanel.add(name_labels[i]);
							dataPanel.add(toolTip_name_labels[i]);
							
							examDisplay = "Sem 1";
						}else{
							examDisplay = "Sem 2";
						}
					}
					else{
						str = studentArray[i].toString();
						dataPanel.add(sr_radio[i]);
						if(setSelected){
							allGrList.add(grNo);
						}
						dataPanel.add(roll_labels[i]);
						dataPanel.add(pipe_labels1[i]);
						dataPanel.add(name_labels[i]);
						dataPanel.add(toolTip_name_labels[i]);
					}

					exam_labels[i] = new JLabel(examDisplay);
					exam_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					exam_labels[i].setBounds(470, j + 2, 50, 50);
//					exam_labels[i].setToolTipText(examClass);
					dataPanel.add(exam_labels[i]);
					toolTip_exam_labels[i] = new JLabel();
					toolTip_exam_labels[i].setToolTipText("Exam");
					toolTip_exam_labels[i].setBounds(470, j + 2, 50, 40);
					dataPanel.add(toolTip_exam_labels[i]);

					JLabel pipe_label7 = new JLabel("|");
					pipe_label7.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_label7.setBounds(520, j, 120, 50);
					dataPanel.add(pipe_label7);
					
					if(result.contains("|")){
						result = result.substring(0,result.indexOf("|"));
					}
					result_labels[i] = new JLabel(result);
					result_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					result_labels[i].setBounds(540, j + 2, 50, 50);
//					result_labels[i].setToolTipText(progress);
					dataPanel.add(result_labels[i]);
					toolTip_result_labels[i] = new JLabel();
					toolTip_result_labels[i].setToolTipText(progress);
					toolTip_result_labels[i].setBounds(540, j + 2, 50, 40);
					dataPanel.add(toolTip_result_labels[i]);
					
					JLabel pipe_label8 = new JLabel("|");
					pipe_label8.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_label8.setBounds(590, j, 120, 50);
					dataPanel.add(pipe_label8);
					
					percent_labels[i] = new JLabel(percent);
					percent_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					percent_labels[i].setBounds(610, j + 2, 50, 50);
//					percent_labels[i].setToolTipText(percent);
					dataPanel.add(percent_labels[i]);
					toolTip_percent_labels[i] = new JLabel();
					toolTip_percent_labels[i].setToolTipText("%");
					toolTip_percent_labels[i].setBounds(610, j + 2, 50, 40);
					dataPanel.add(toolTip_percent_labels[i]);

					JLabel pipe_label9 = new JLabel("|");
					pipe_label9.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_label9.setBounds(660, j, 120, 50);
					dataPanel.add(pipe_label9);
					
					marks_labels[i] = new JLabel(marks);
					marks_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					marks_labels[i].setBounds(680, j + 2, 50, 50);
//					marks_labels[i].setToolTipText(marks);
					dataPanel.add(marks_labels[i]);
					toolTip_marks_labels[i] = new JLabel();
					toolTip_marks_labels[i].setToolTipText("Marks");
					toolTip_marks_labels[i].setBounds(680, j + 2, 50, 40);
					dataPanel.add(toolTip_marks_labels[i]);

					JLabel pipe_label10 = new JLabel("|");
					pipe_label10.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_label10.setBounds(730, j, 120, 50);
					dataPanel.add(pipe_label10);
					
					total_labels[i] = new JLabel(total);
					if(!total.contains("+")){
						total_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					}
					else{
						total_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					}
					total_labels[i].setBounds(750, j + 2, 50, 50);
//					total_labels[i].setToolTipText(total);
					dataPanel.add(total_labels[i]);
					toolTip_total_labels[i] = new JLabel();
					toolTip_total_labels[i].setToolTipText("Total");
					toolTip_total_labels[i].setBounds(750, j + 2, 50, 40);
					dataPanel.add(toolTip_total_labels[i]);

					JLabel pipe_label12 = new JLabel("|");
					pipe_label12.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_label12.setBounds(800, j, 120, 50);
					dataPanel.add(pipe_label12);

					//logic to display subject wise marks
					int m = 820;
					int p = 11;
					String dispResult = "";
					String dispMarks = "";
					String dispPassStatus = "";
					String dispReason = "";
					String dispAbsentMarks = "";
					for (int n = 0; n < subjectTitleList.size(); n++) {
						dispResult = temp[p];
						if(!stdClass.equalsIgnoreCase("IX") && !stdClass.equalsIgnoreCase("X") && !stdClass.equalsIgnoreCase("XI")
								&& !stdClass.equalsIgnoreCase("XII") && !stdClass.equalsIgnoreCase("JR KG") && !stdClass.equalsIgnoreCase("SR KG")){
							if(dispResult.contains("~")){
								dispMarks = dispResult.substring(0, dispResult.indexOf("~"));
							}
							if(dispResult.contains("AB") || dispResult.contains("MG")){
								if(!examClass.equalsIgnoreCase("FINAL")){
									dispMarks = dispResult.substring(0, dispResult.lastIndexOf("+"));
								}
								else if(examClass.equalsIgnoreCase("FINAL")){
									dispMarks = dispResult.substring(0, dispResult.lastIndexOf("+"));
								}
								if(dispResult.contains("(")){
									dispMarks = dispResult + ")";
								}
							}
							else if(dispResult.contains("(") && dispResult.contains("+")){
								dispMarks = dispResult.substring(0, dispResult.indexOf("+")) + " " +dispResult.substring(dispResult.indexOf("("));
							}
							else if(dispResult.contains("(F)")){
								dispMarks = dispResult.substring(0, dispResult.indexOf("+")) + " " +dispResult.substring(dispResult.indexOf("("));
							}
							else{
								dispMarks = dispResult;
							}
						}
						else
						{
							if(!dispResult.equalsIgnoreCase("NA") && !commonObj.validateNumber(dispResult) && 
									dispResult.contains("(")){
								dispMarks = dispResult.substring(0, dispResult.indexOf("("));
								if(dispMarks.contains("+") && !examClass.equalsIgnoreCase("Final")){
									dispMarks = dispMarks.substring(0, dispMarks.indexOf("+"));
								}
								dispPassStatus = dispResult.substring(dispResult.indexOf("(")+1, dispResult.indexOf("#"));
								dispReason = dispResult.substring(dispResult.indexOf("#")+1, dispResult.indexOf("@"));
								dispAbsentMarks = dispResult.substring(dispResult.indexOf("@")+1, dispResult.indexOf(")"));
								if(dispReason.equalsIgnoreCase("MG")){
									dispMarks = dispMarks + "(MG)";
								}
								else if(dispPassStatus.equalsIgnoreCase("F")){
									dispMarks = dispMarks + "(F)";
								}
							}
							else{
								dispMarks = dispResult;
							}
						}
						//don't display AB
						/*if(dispMarks.contains("AB")){
							dispMarks = dispMarks.replace("+AB", "");
						}*/
						sub_labels[n] = new JLabel(dispMarks);
						sub_labels[n].setFont(new Font("Book Antiqua", Font.BOLD, 12));
						int q = 0;
						if((temp[p].contains("+") && temp[p].contains("(F)")) || temp[p].contains("MG") || 
								temp[p].contains("AB")){
							q = 0;
						}
						else{
							q = -10;
						}
						sub_labels[n].setBounds(m - q, j, 75, 50);
//						sub_labels[n].setToolTipText(subjectTitleList.get(n));
						dataPanel.add(sub_labels[n]);
						toolTip_sub_labels[n] = new JLabel();
						toolTip_sub_labels[n].setToolTipText(subjectTitleList.get(n));
						toolTip_sub_labels[n].setBounds(m - 15, j, 70, 40);
						dataPanel.add(toolTip_sub_labels[n]);

						pipe_labels[n] = new JLabel("|");
						pipe_labels[n].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						pipe_labels[n].setBounds(m + 50, j, 120, 50);
						dataPanel.add(pipe_labels[n]);

						p = p + 1;
						m = m + 60;

					}

					line_labels[i] = new JLabel(horLineClass);
					line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					line_labels[i].setBounds(30, j + 15, screenWidth + scrollWidth, 50);
					dataPanel.add(line_labels[i]);

					// //////sr radio action//////////////
					int r = i;
					if(examClass.equalsIgnoreCase("Final") && !stdClass.equalsIgnoreCase("IX") && 
							!stdClass.equalsIgnoreCase("X") && !stdClass.equalsIgnoreCase("XI") && 
							!stdClass.equalsIgnoreCase("XII")){
						r = sem1Index;
					}
					
					final int s = r;
					
					// allGrList.clear();
					sr_radio[i].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							new Thread() {
								public void run() {
									int q = s;
									boolean radio_selected = false;
									
									if(examClass.equalsIgnoreCase("Final") && !stdClass.equalsIgnoreCase("IX") && !stdClass.equalsIgnoreCase("X")
											&& !stdClass.equalsIgnoreCase("XI") && !stdClass.equalsIgnoreCase("XII")
											&& !stdClass.equalsIgnoreCase("JR KG") && !stdClass.equalsIgnoreCase("SR KG")){
										q = q + q;
									}
		
									radio_selected = sr_radio[q].isSelected();
									logger.info("q value " + q + ":  " + radio_selected + "::radio_selected for : "
											+ gr_labels[q].getText());
		
									/*if (!setSelected) {
										allGrList.clear();
									}*/
		
									if (radio_selected) {
										logger.info(" allgr list before : " + allGrList.size());
										allGrList.add(gr_labels[q].getText());
										logger.info(" allgr list after : " + allGrList.size());
										setSelected = true;
										selectAllCount++;
									} else {
										entrytCnt++;
										logger.info(" all grp list before remove 1 :" + allGrList.size());
										allGrList.remove(gr_labels[q].getText());
										logger.info(" all grp list before remove after 2 :" + allGrList.size());
										all_radio.setSelected(false);
										setSelected = false;
										selectAllCount--;
									}
									logger.info("selectAllCount ==>" + selectAllCount);
									// if (selectAllCount == foundStudentList.size()) {
									// all_radio.setSelected(true);
									// }
									logger.info("allGrList end of for loop ==>" + allGrList.size());
					            }
					          }.start();
						}
					});
					
					if(i % 2 == 0){
						sem1Index++;
					}else{
						sem2Index++;
					}
				}

				// /////////////Note//////////////
				JLabel note_label = new JLabel("Note :");
				note_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				note_label.setBounds(60, l-10, 120, 50);
				dataPanel.add(note_label);

				final JTextField note_text = new JTextField("");
				note_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				note_text.setBounds(120, l, 800, 25);
				dataPanel.add(note_text);
				
				JLabel sep_label = new JLabel(
						"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				sep_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				sep_label.setBounds(40, l + 10, 1600, 50);
				dataPanel.add(sep_label);
				
				JButton printExcelButton = new JButton("Print in Excel");
				printExcelButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				printExcelButton.setBounds(mainCentre - 200, l + 50, 150, 25);
				dataPanel.add(printExcelButton);
				
				JButton printButton = new JButton("Print");
				printButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				printButton.setBounds(mainCentre, l + 50, 130, 25);
				dataPanel.add(printButton);
				// ///////print data ends///////////////////////

				JLabel sep1_label = new JLabel(
						"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				sep1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				sep1_label.setBounds(40, l + 60, 1600, 50);
				dataPanel.add(sep1_label);
				// /////////////////////////////

				// //////All sr radio action//////////////
				all_radio.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						boolean pass_selected = all_radio.isSelected();
						logger.info("pass_selected : " + pass_selected);
						selectedAllGrList.clear();
						if(pass_selected){
							selectedAllGrList.addAll(allGrList);
						}
						frame.setVisible(false);
						new Result(sessionData, grClass, stdClass, divClass, lastNameClass, firstNameClass, fatherNameClass,
								studentResultMap, false, pass_selected, yearClass, section, user_name, user_role, "",
								"", (String) exam_combo.getSelectedItem(), studentResultSem1Map, studentResultSem2Map, selectedAllGrList);
					}
				});

				// //////save & print action starts//////////////////////////////////////
				printExcelButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						logger.info("print action starts");
						LinkedHashMap grStudentMap = new LinkedHashMap();
						LinkedHashMap grStudentSelectedMap = new LinkedHashMap();
						LinkedHashMap<String,String> leftDataMap = new LinkedHashMap<String,String>();
						List<String> subjectTitleList = new ArrayList<String>();
						boolean isAllSelected = all_radio.isSelected();
						String note = note_text.getText().toString();
						int stdInt = commonObj.RomanToInteger(stdClass);
						
						try {
							if(dbValidate.connectDatabase(sessionData)){
								JFrame f = new JFrame("Result downlaod in progress. Don't Close");
								f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
							    f.setSize(400, 0);
							    f.setResizable(false);
							    f.setVisible(true);
							    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							    
								if(stdInt <= 0 || stdInt >= 9){
									grStudentMap = dbValidate.printResultWithMarksList(sessionData, academicYearClass, stdClass, divClass, examClass, 
											section, lastNameClass, firstNameClass, fatherNameClass, leftDataMap);
									subjectTitleList = dbValidate.findSubjectTitleList(sessionData, stdClass, "", academicYearClass);
									subjectTitleList.remove(0);
									subjectTitleList.remove(0);
									
									if(selectedAllGrList.size() > 0){
										for(int i = 0; i < selectedAllGrList.size(); i++){
											grStudentSelectedMap.put(selectedAllGrList.get(i), grStudentMap.get(selectedAllGrList.get(i)));
										}
									}
									else {
										commonObj.showMessageDialog("No student selected.");
										f.setVisible(false);
										return;
									}
										
									Map<String,String> maxMarksMapOrder = new LinkedHashMap<String, String>();
									Map<String,String> gradeMarksMapOrder = new LinkedHashMap<String, String>();
							    	try {
										maxMarksMapOrder = dbValidate.getMaxMarksForAllSubjects(sessionData, stdClass, academicYearClass, examClass);
										gradeMarksMapOrder = dbValidate.getGradeMarksForAllSubjects(sessionData, stdClass, academicYearClass, examClass);
									} catch (Exception e1) {
										f.setVisible(false);
										e1.printStackTrace();
										commonObj.logException(e1);
										commonObj.showMessageDialog("failed to get max marks for subjects.");
									}
								    	
									if(examClass.equalsIgnoreCase("FINAL")){
										String findQuery = "";
										commonObj.resultAverageSheet(sessionData, section, academicYearClass, grStudentSelectedMap, 
												subjectTitleList,examClass, stdClass, divClass, note, maxMarksMapOrder, gradeMarksMapOrder);
									}
									else if(!examClass.equalsIgnoreCase("FINAL")){
											String findQuery = "";
											commonObj.resultSemesterSheet(sessionData, section, academicYearClass, grStudentSelectedMap, 
													subjectTitleList,examClass, stdClass, divClass, note, maxMarksMapOrder, gradeMarksMapOrder);
									}
								}
								else{
									if(!examClass.equalsIgnoreCase("Final")){
										dbValidate.printExcelResultMap(sessionData, academicYearClass, stdClass, divClass, examClass, 
												section, lastNameClass, firstNameClass, fatherNameClass);
									}
									else{
										dbValidate.printExcelFinalResultMap(sessionData, academicYearClass, stdClass, divClass, examClass, 
												section, lastNameClass, firstNameClass, fatherNameClass);
									}
								}
								f.setVisible(false);
							}
						} catch (Exception e1) {
							commonObj.logException(e1);
						} finally {
							dbValidate.closeDatabase(sessionData);
						}
					}
				});
				
				printButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						logger.info("print action starts");
						LinkedHashMap grStudentMap = new LinkedHashMap();
						LinkedHashMap grStudentSelectedMap = new LinkedHashMap();
						LinkedHashMap<String,String> leftDataMap = new LinkedHashMap<String,String>();
						List<String> subjectTitleList = new ArrayList<String>();
						boolean isAllSelected = all_radio.isSelected();
						String note = note_text.getText().toString();
						int stdInt = commonObj.RomanToInteger(stdClass);
						
						try {
							if(dbValidate.connectDatabase(sessionData)){
								
								if(stdInt >= 9){
									grStudentMap = dbValidate.printResultWithMarksList(sessionData, academicYearClass, stdClass, divClass, examClass, 
											section, lastNameClass, firstNameClass, fatherNameClass, leftDataMap);
								}
								else if(stdInt <= 0){
									grStudentMap = dbValidate.printResultWithMarksList(sessionData, academicYearClass, stdClass, divClass, examClass, 
											section, lastNameClass, firstNameClass, fatherNameClass, leftDataMap);
								}
								else{
									grStudentMap = dbValidate.printResultList(sessionData, academicYearClass, stdClass, divClass, examClass, 
											section, lastNameClass, firstNameClass, fatherNameClass, leftDataMap);
								}
								
								subjectTitleList = dbValidate.findSubjectTitleList(sessionData, stdClass, "", academicYearClass);
								subjectTitleList.remove(0);
								subjectTitleList.remove(0);
								
								if(selectedAllGrList.size() > 0){
									for(int i = 0; i < selectedAllGrList.size(); i++){
										grStudentSelectedMap.put(selectedAllGrList.get(i), grStudentMap.get(selectedAllGrList.get(i)));
									}
								}
								else {
									commonObj.showMessageDialog("No student selected.");
									return;
								}
									
								Map<String,String> maxMarksMapOrder = new LinkedHashMap<String, String>();
								Map<String,String> gradeMarksMapOrder = new LinkedHashMap<String, String>();
						    	try {
									maxMarksMapOrder = dbValidate.getMaxMarksForAllSubjects(sessionData, stdClass, academicYearClass, examClass);
									gradeMarksMapOrder = dbValidate.getGradeMarksForAllSubjects(sessionData, stdClass, academicYearClass, examClass);
								} catch (Exception e1) {
									commonObj.logException(e1);
									commonObj.showMessageDialog("failed to get max marks for subjects.");
								}
							    	
								if(stdClass.equalsIgnoreCase("IX") || stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XI")
										|| stdClass.equalsIgnoreCase("XII")){
									ResultMarksPDF resultMarksPDF = new ResultMarksPDF(sessionData, section, academicYearClass, grStudentSelectedMap, 
											subjectTitleList,examClass, stdClass, divClass, note, maxMarksMapOrder, gradeMarksMapOrder);
								}
								else if(stdClass.equalsIgnoreCase("JR KG") || stdClass.equalsIgnoreCase("SR KG")){
									ResultPPRMarksPDF resultPPRMarksPDF = new ResultPPRMarksPDF(sessionData, section, academicYearClass, grStudentSelectedMap, 
											subjectTitleList, examClass, stdClass, divClass, note, maxMarksMapOrder, gradeMarksMapOrder);
								}
								else{
									ResultGradePDF resultGradePDF = new ResultGradePDF(sessionData, section, academicYearClass, grStudentSelectedMap, 
										subjectTitleList,examClass, stdClass, divClass, note);
								}
							}
						} catch (Exception e1) {
							commonObj.logException(e1);
						} finally {
							dbValidate.closeDatabase(sessionData);
						}
					}
				});
				// //////save & print action
				// ends/////////////////////////////////////
			}
		}
		// ////////////////data panel ends/////////////////////////////////

		// dataPanel.setBackground(Color.green);

		JScrollPane scrollPane = new JScrollPane(dataPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(5, 0, screenWidth - 154, screenHeight - 337);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, screenHeight - 337));
        contentPane.add(scrollPane);
		bottombandPanel.add(contentPane, BorderLayout.SOUTH);
		mainPanel.add(bottombandPanel, BorderLayout.SOUTH);
		// ////////////////////////////////
		panelHome.add(mainPanel, BorderLayout.EAST);
		panelHome.setSize(screenWidth, screenHeight);
	}
}
