package org.com.maauli;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.formula.functions.Isref;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap; 

public class AttendanceUpdate extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JFrame frame = null;

	static List<String> foundStudentList = new ArrayList();

	static int retCount = 0;

	static int scrollHeight = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

	static boolean setSelected = false;

	static boolean printDuplicate = false;

	static String categoryList = "";
	
	static String monthlyList = "", monthClass = "";

	static String grNoClass = "";

	static String stdClass = "";

	static String divClass = "";
	
	static String catTypeClass = "";
	
	static String updateAllClass = "";
	
	static boolean isRecordNewClass = false;
	
	static String fromDateClass = "";
	
	static String toDateClass = "";

	static String lastNameClass = "";

	static String firstNameClass = "";

	static String fatherNameClass = "";

	static String academicYearClass = "";

	static String rollFromClass = "";

	static String rollToClass = "";

	static String category = "";

	static String selectStd = "";

	static String selectDiv = "";

	static String user_name = "";
	
	static String examClass = "";

	static String user_role = "";
	
    static String app_header = "";
    
    static String app_header_0 = "";

	static DBValidate dbValidate = new DBValidate();

	static SessionData sessionData = new SessionData();

	static Common commonObj = new Common();

	static String std = "";

	static String div = "";

	static String section = "";
	
	static String attendanceList = "";
	
	static String examCategory = "";

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

	static String yearList = "";
	
	static boolean isEditClass = false;
	
	static boolean isHalfDayClass = false;
	
	static List<String> recordsNotInAttendanceList = null;
	
	static LinkedHashMap<String, LinkedHashMap<String, String>> reasonMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
	
	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static Logger logger = Logger.getLogger(AttendanceUpdate.class.getName());
	
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
    static LinkedHashMap<String, String> studentMap;
    static TreeMap<String, String> studentLCMap = new TreeMap<String, String>();
    static CreateExcelInXLSX ce = new CreateExcelInXLSX();
    
	public AttendanceUpdate(SessionData sessionData1, String retStd, String retDiv, String academicYear, String sec, 
			String retUserName, String retUserRole, List<String> retStudentList, String retCatType, String retMonth, 
			String retFrom, String retTo, boolean isRecordNew, String retupdateAll, boolean isEdit, String exam, boolean isHalfDay) {
		System.gc();
		studentMap = new LinkedHashMap<String, String>();
		sessionData = sessionData1;
		isHalfDayClass = isHalfDay;
		isEditClass = isEdit;
		if(isHalfDayClass){
			isEditClass = isHalfDayClass;
		}
		user_name = retUserName;
		user_role = retUserRole;
		stdClass = retStd;
		divClass = retDiv;
		catTypeClass = retCatType;
		updateAllClass = retupdateAll;
		isRecordNewClass = isRecordNew;
		fromDateClass = retFrom;
		toDateClass = retTo;
		examClass = exam;

		category = retCatType;
		section = sec;
		std = bundle.getString(section.toUpperCase() + "_STD");
		div = bundle.getString(section.toUpperCase() + "_DIV");
		secName = bundle.getString(section.toUpperCase() + "_SEC");
		categoryList = bundle.getString("ATTENDANCE_CATEGORY");
		attendanceList = bundle.getString("ATTENDANCE_STATUS");
		examCategory = bundle.getString("EXAM_CATEGORY");
		monthlyList = bundle.getString("MONTH_LIST");
		monthClass = retMonth;
		if (!retMonth.equalsIgnoreCase("")) {
			monthlyList = "All," + monthlyList;
		} else {
			monthlyList = "Select,All," + monthlyList;
		}
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

		String todayDate = commonObj.getCurrentDate();
		academicYearClass = commonObj.getAcademicYear(todayDate);
		if (!academicYear.equalsIgnoreCase("")) {
			academicYearClass = academicYear;
		}
		try {
			if(dbValidate.connectDatabase(sessionData)){
				studentLCMap = dbValidate.findStudentLCList(sessionData1, "", stdClass, divClass, "", "", "", academicYear, "", "", section);
				yearList = dbValidate.findYearList(sessionData, "CLASS_ALLOTMENT");
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

		if (!retStd.equalsIgnoreCase("")) {
			std = retStd + "," + std;
		} else {
			std = "Select," + std;
		}
		if (!retDiv.equalsIgnoreCase("")) {
			div = retDiv + "," + div;
		} else {
			div = "Select," + div;
		}

		foundStudentList = retStudentList;
		String grNo = "";
		for(int i = 0; i < foundStudentList.size(); i++){
			grNo = foundStudentList.get(i);
			grNo = grNo.substring(grNo.indexOf("|")+1);
			grNo = grNo.substring(0, grNo.indexOf("|"));
			studentMap.put(grNo, foundStudentList.get(i));
		}
		retCount = foundStudentList.size();

		setVisible(false);
		dispose();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame = new JFrame("Welcome to "+sessionData1.getAppName());
		Common commonObj = new Common();
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

	private static void placeComponents(JPanel panelHome) {

		panelHome.setLayout(new BorderLayout());
		// //////////////////////////////////////////
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

				frame.setVisible(false);
				new Student(sessionData, section, user_name, user_role);
			}
		});

		JButton staffButton = new JButton("Staff");
		staffButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		staffButton.setBounds(10, 150, 130, 35);
//		leftPanel.add(staffButton);

		staffButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Welcome(sessionData, user_name, user_role);
				frame.setVisible(false);
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
				new FindStudent(sessionData, "", "", "", "", "", "", studMap, section, user_name, user_role, false);
				frame.setVisible(false);
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
		attendanceButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		attendanceButton.setBounds(750, 50, 130, 24); // 300, 50, 150, 24
		attendanceButton.setBackground(Color.GREEN);
		topbandPanel.add(attendanceButton);

		attendanceButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

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
				// change height to change scrolling area
				Dimension size = new Dimension(screenWidth - 156, 150);
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				g.drawImage(img, 0, 0, null);
			}
		};
		findPanel.setLayout(null);

		// /////////////Std//////////////
		JLabel admittedStd_label = new JLabel("Std :");
		admittedStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_label.setBounds(90, 00, 70, 50);
		findPanel.add(admittedStd_label);

		// String admittedStdList[] =
		// {stdSel,"IV","V","VI","VII","VIII","IX","X"};
		String[] stdList = std.split(",");
		final JComboBox admittedStd_combo = new JComboBox(stdList);
		admittedStd_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_combo.setBounds(140, 12, 90, 25);
		findPanel.add(admittedStd_combo);

		// //////////////////////////////////
		// /////////////Div//////////////
		JLabel admittedDiv_label = new JLabel("Div :");
		admittedDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedDiv_label.setBounds(290, 00, 70, 50);
		findPanel.add(admittedDiv_label);

		// String admittedDivList[] = {divClass,"A","B","C","D"};
		String[] divList = div.split(",");
		final JComboBox admittedDiv_combo = new JComboBox(divList);
		admittedDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedDiv_combo.setBounds(340, 12, 100, 25);
		findPanel.add(admittedDiv_combo);

		// ///////////////////////////////
		JLabel catType_label = new JLabel("Attendance :");
		catType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		catType_label.setBounds(470, 00, 150, 50);
		findPanel.add(catType_label);

		if (!category.equalsIgnoreCase("")) {
			categoryList = category + "," + categoryList;
		}
		String[] catList = categoryList.split(",");
		// String catList[] = {lcTypeClass,"Category Wise","Religion
		// Wise","General"};
		final JComboBox cat_combo = new JComboBox(catList);
		cat_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		cat_combo.setBounds(590, 12, 180, 25);
		findPanel.add(cat_combo);
		
		// /////////name radio///////////////30, 80, 120, 50
		final JRadioButton date_radio = new JRadioButton();
		date_radio.setBorder(null);
		date_radio.setBounds(65, 60, 12, 12);
		findPanel.add(date_radio);
		
		final JLabel from_label = new JLabel("Select :");
		from_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		from_label.setBounds(90, 40, 120, 50);
		findPanel.add(from_label);

		/****from date picker****/
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
	    datePanelFrom.setBounds(85, 52, 130, 26);
	    // Don't know about the formatter, but there it is...
	    final JDatePickerImpl datePickerFrom = new JDatePickerImpl(datePanelFrom, new DateLabelFormatter());
	    datePickerFrom.setBounds(160, 52, 130, 26);
        findPanel.add(datePickerFrom);

        //////////////////To/////////////////
		final JLabel to_label = new JLabel("To :");
		to_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		to_label.setBounds(330, 40, 70, 50);
//		findPanel.add(to_label);

		/****to date picker****/
		final UtilDateModel modelTo = new UtilDateModel();
		if(!toDateClass.equalsIgnoreCase("")){
			int dayToDate = Integer.parseInt(toDateClass.substring(0,toDateClass.indexOf("/")));
			int monthToDate = Integer.parseInt(toDateClass.substring(toDateClass.indexOf("/")+1,toDateClass.lastIndexOf("/")));
			int yearToDate = Integer.parseInt(toDateClass.substring(toDateClass.lastIndexOf("/")+1));
			modelTo.setDate(yearToDate,monthToDate-1,dayToDate);
//			modelTo.setDate(2014,04,24);
		}
	    // Need this...
		modelTo.setSelected(true);
	    Properties pTo = new Properties();
	    pTo.put("text.today", "Today");
	    pTo.put("text.month", "Month");
	    pTo.put("text.year", "Year");
	    final JDatePanelImpl datePanelTo = new JDatePanelImpl(modelTo, pTo);
	    datePanelTo.setBounds(300, 52, 130, 26);
	    // Don't know about the formatter, but there it is...
	    final JDatePickerImpl datePickerTo = new JDatePickerImpl(datePanelTo, new DateLabelFormatter());
	    datePickerTo.setBounds(375, 52, 130, 26);
//        findPanel.add(datePickerTo);
		
	    ///////////month radio///////////////30, 80, 120, 50
		final JRadioButton month_radio = new JRadioButton();
		month_radio.setBorder(null);
		if(!monthClass.equalsIgnoreCase("")) {
			month_radio.setSelected(true);
		}
		month_radio.setBounds(550, 55, 12, 12);
//		findPanel.add(month_radio);
		
		final JLabel month_label = new JLabel("Month :");
		month_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		month_label.setBounds(580, 40, 120, 50);
//		findPanel.add(month_label);
		
		String monthList[] = monthlyList.split(",");
		final JComboBox month_combo = new JComboBox(monthList);
		month_combo.setSelectedItem(monthClass);
		month_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		month_combo.setBounds(650, 52, 120, 25);
//		findPanel.add(month_combo);
		
		 // /////////year radio///////////////30, 80, 120, 50
		/*final JRadioButton year_radio = new JRadioButton();
		year_radio.setBounds(790, 55, 20, 20);
		findPanel.add(year_radio);*/
		
		final JLabel year_label = new JLabel("Year :");
		year_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		year_label.setBounds(825, 00, 120, 50);
//		findPanel.add(year_label);
		
		String academicYearList[] = yearList.split(",");
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academicYear_combo.setBounds(875, 12, 120, 25);
//		findPanel.add(academicYear_combo);
		
		final JLabel exam_label = new JLabel("Exam :");
		exam_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		exam_label.setBounds(70, 40, 120, 50);
		
		String examList[] = examCategory.split(",");
		final JComboBox exam_combo = new JComboBox(examList);
		exam_combo.setSelectedItem(examClass);
		exam_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		exam_combo.setBounds(140, 52, 120, 25);
		
		final JRadioButton halfDayRadio = new JRadioButton();
		halfDayRadio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		halfDayRadio.setBorder(null);
		halfDayRadio.setBounds(65, 100, 12, 12);
		
		final JLabel halfDay_label = new JLabel("Half Day");
		halfDay_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		halfDay_label.setBounds(90, 80, 120, 50);
//		findPanel.add(halfDayRadio);
//		findPanel.add(halfDay_label);
		
		final JButton downloadButton = new JButton("Download");
		downloadButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		downloadButton.setBounds(858, 52, 130, 25);
//		findPanel.add(downloadButton);

		downloadButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int reply = 0;
					boolean validateFields = true;
					String academicSel = "", std = "", div = "", catType = "", exam = "", month = "";

					academicSel = (String) academicYear_combo.getSelectedItem() == null ? "" : (String) academicYear_combo.getSelectedItem();
					std = (String) admittedStd_combo.getSelectedItem() == null ? "" : (String) admittedStd_combo.getSelectedItem();
					div = (String) admittedDiv_combo.getSelectedItem() == null ? "" : (String) admittedDiv_combo.getSelectedItem();
					catType = (String) cat_combo.getSelectedItem();
					exam = exam_combo.getSelectedItem().toString();
					if(exam.equalsIgnoreCase("Select")) {
						exam = "";
					}
					if(month_radio.isSelected()){
						month = (String) month_combo.getSelectedItem();
					}

					if (std.equalsIgnoreCase("Select")){
						commonObj.showMessageDialog("Please select std");
						validateFields = false;
					}
					else if (validateFields && div.equalsIgnoreCase("Select")){
						commonObj.showMessageDialog("Please select div");
						validateFields = false;
					}
					else if(exam.equalsIgnoreCase("") && month.equalsIgnoreCase("")){
						commonObj.showMessageDialog("Please select Exam/Month");
						validateFields = false;
					}

					if (validateFields) {
						reply = JOptionPane.showConfirmDialog(null,
								"Would You Like to download "+exam+" Attendance for std  " + std + " div "+div+" ?",
								"Confirm download", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							String templatePath = commonObj.getDriveName()
									+ bundle.getString("TEMPLATE_PATH_" + sessionData.getDBName())
									+ commonObj.getCurrentDatein_dd_MMM_yyyy();
							if (dbValidate.connectDatabase(sessionData)) {
								JFrame f = new JFrame("Attendance downlaod in progress. Don't Close");
								f.setBounds(screenWidth / 2 - 150, screenHeight / 2, 90, 25);
								f.setSize(400, 0);
								f.setResizable(false);
								f.setVisible(true);
								f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

								foundStudentList = dbValidate.findAtendancePeriodicList(sessionData, "", std, div, 
					    				"", "", "", academicSel, "", "", section, true, exam, month, "download");
								if(month.equalsIgnoreCase("")) {
									month = exam;
								}
								ce.generateExcel(sessionData, "Attendance", 
										"Attendance_"+std+"_"+div+"_"+month+"_", "", foundStudentList, 
										true, secName + " Attendance "+exam+" "+academicSel+" STD:" + std + "  DIV:" + div, 1);
								
								f.setVisible(false);
								commonObj.showMessageDialog("Attendance downloaded to " + templatePath);
							}
						}
					}
				} catch (Exception e1) {
					logger.info("Exception backupButton : " + e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});

		// /////////////Upload Marks Allotment Template//////////////
		final JButton uploadButton = new JButton("Upload");
		uploadButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		uploadButton.setBounds(mainCentre + 250, 90, 130, 25);
//		findPanel.add(uploadButton);

		uploadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int reply = 0;
					boolean validateFields = true;
					String academicSel = "", std = "", div = "", catType = "", exam = "", month = "";

					academicSel = (String) academicYear_combo.getSelectedItem() == null ? "" : (String) academicYear_combo.getSelectedItem();
					std = (String) admittedStd_combo.getSelectedItem() == null ? "" : (String) admittedStd_combo.getSelectedItem();
					div = (String) admittedDiv_combo.getSelectedItem() == null ? "" : (String) admittedDiv_combo.getSelectedItem();
					catType = (String) cat_combo.getSelectedItem();
					exam = exam_combo.getSelectedItem().toString();
					if(exam.equalsIgnoreCase("Select")) {
						exam = "";
					}
					if(month_radio.isSelected()){
						month = (String) month_combo.getSelectedItem();
					}

					if (std.equalsIgnoreCase("Select")){
						commonObj.showMessageDialog("Please select std");
						validateFields = false;
					}
					else if (validateFields && div.equalsIgnoreCase("Select")){
						commonObj.showMessageDialog("Please select div");
						validateFields = false;
					}
					else if(exam.equalsIgnoreCase("") && month.equalsIgnoreCase("")){
						commonObj.showMessageDialog("Please select Exam/Month");
						validateFields = false;
					}
					
					if(validateFields){
						String default_path = commonObj.getDriveName() + bundle.getString("TEMPLATE_PATH_"+sessionData.getDBName());
						JFileChooser fileChooser = new JFileChooser(default_path);
						int returnValue = fileChooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
							
							reply = JOptionPane.showConfirmDialog(null, "Would You Like to upload "+absolutePath+" \n "
									+ "for Std "+std+" Div "+div+"?", "Confirm", JOptionPane.YES_NO_OPTION);
							
							if (reply == JOptionPane.YES_OPTION) {
								try {
								    new UploadAttendanceFromExcel(sessionData, absolutePath, academicSel, std, div, exam, month);
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
		
		// ////////date_radio.addActionListener///////////
		date_radio.setSelected(true);
		if(monthClass.equalsIgnoreCase("")) {
			month_combo.setSelectedItem("All");
			month_combo.setEnabled(false);
			month_radio.setSelected(false);
		}
		academicYear_combo.setSelectedItem("All");
//		if(!sessionData.getUserName().equalsIgnoreCase("PRP")) {
//			academicYear_combo.setEnabled(false);
//		}
		
		if(!stdClass.equalsIgnoreCase("All") && !stdClass.equalsIgnoreCase("Select") && 
	    		!stdClass.equalsIgnoreCase("") && dbValidate.connectDatabase(sessionData)){
			admittedDiv_combo.removeAllItems();
			String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdClass, section, 
					"PRESENT_DIV", "PRESENT_STD", "class_allotment",academicYearClass);
			
			for (String retval: divAvailabe.split(",")) {
				admittedDiv_combo.addItem(retval);
			}
			admittedDiv_combo.setSelectedItem(divClass);
		}
		
		if (catTypeClass.equalsIgnoreCase("Mark Vacation")) {
			from_label.setText("From :");
			findPanel.add(date_radio);
			findPanel.add(from_label);
			findPanel.add(datePickerFrom);
			findPanel.add(to_label);
			findPanel.add(datePickerTo);
			findPanel.remove(exam_label);
			findPanel.remove(exam_combo);
			findPanel.remove(month_radio);
			findPanel.remove(month_label);
			findPanel.remove(month_combo);
			findPanel.remove(year_label);
			findPanel.remove(academicYear_combo);
			findPanel.remove(downloadButton);
			findPanel.remove(uploadButton);
			findPanel.revalidate();
			findPanel.repaint();
		} else if(catTypeClass.equalsIgnoreCase("Attendance Report")){
				from_label.setText("From :");
				findPanel.add(date_radio);
				findPanel.add(from_label);
				findPanel.add(datePickerFrom);
				findPanel.add(to_label);
				findPanel.add(datePickerTo);
				findPanel.add(month_radio);
				findPanel.add(month_label);
				findPanel.add(month_combo);
				findPanel.add(year_label);
				findPanel.add(academicYear_combo);
				findPanel.remove(exam_label);
				findPanel.remove(exam_combo);
				findPanel.remove(downloadButton);
				findPanel.remove(uploadButton);
				findPanel.revalidate();
				findPanel.repaint();
		} else if(catTypeClass.equalsIgnoreCase("Manual Attendance")){
			findPanel.remove(date_radio);
			findPanel.remove(from_label);
			findPanel.remove(datePickerFrom);
			findPanel.remove(to_label);
			findPanel.remove(datePickerTo);
			findPanel.add(month_radio);
			findPanel.add(month_label);
			findPanel.add(month_combo);
			findPanel.add(year_label);
			findPanel.add(academicYear_combo);
			findPanel.add(exam_label);
			findPanel.add(exam_combo);
			findPanel.add(downloadButton);
			findPanel.add(uploadButton);
			findPanel.revalidate();
			findPanel.repaint();
		}
		
		admittedStd_combo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
					String stdSel = (String) admittedStd_combo.getSelectedItem();
					String acadSel = (String) academicYear_combo.getSelectedItem();
					if(!stdSel.equalsIgnoreCase("All") && !stdSel.equalsIgnoreCase("Select") && dbValidate.connectDatabase(sessionData)){
						admittedDiv_combo.removeAllItems();
						admittedDiv_combo.addItem("Select");
						String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdSel, section, 
								"PRESENT_DIV", "PRESENT_STD", "class_allotment",acadSel);
						
						for (String retval: divAvailabe.split(",")) {
							admittedDiv_combo.addItem(retval);
						}
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});
		
		exam_combo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
					String examSel = exam_combo.getSelectedItem().toString();
					if(!examSel.equalsIgnoreCase("Select") && !examSel.equalsIgnoreCase("")) {
						month_combo.setSelectedItem("All");
						month_combo.setEnabled(false);
						month_radio.setSelected(false);
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				}
			}
		});
		
		month_combo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
					String monthSel = month_combo.getSelectedItem().toString();
					if(!monthSel.equalsIgnoreCase("All") && !monthSel.equalsIgnoreCase("")) {
						exam_combo.setSelectedItem("Select");
						exam_combo.setEnabled(false);
					}
//					else if(monthSel.equalsIgnoreCase("All")) {
//						exam_combo.setEnabled(true);
//						if(exam_combo.getSelectedItem().toString().equalsIgnoreCase("Select")) {
//							JOptionPane.showMessageDialog(null, "Please select Exam");
//						}
//					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				}
			}
		});

		date_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (date_radio.isSelected()) {
					month_combo.setSelectedItem("All");
					month_combo.setEnabled(false);
					academicYear_combo.setSelectedItem("All");
					academicYear_combo.setEnabled(false);
					month_radio.setSelected(false);
				} else {
					date_radio.setSelected(false);
					month_combo.setEnabled(true);
					academicYear_combo.setEnabled(true);
					month_radio.setSelected(true);
				}
			}
		});
		
		// ////////month_radio.addActionListener///////////
		month_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String catType = (String) cat_combo.getSelectedItem();
				if (month_radio.isSelected()) {
					date_radio.setSelected(false);
					month_combo.setEnabled(true);
					if(!catType.equalsIgnoreCase("Manual Attendance")) {
						academicYear_combo.setEnabled(true);
					}
					else {
						exam_combo.setSelectedItem("Select");
					}
					
				} else {
					date_radio.setSelected(true);
					month_combo.setSelectedItem("All");
					academicYear_combo.setSelectedItem("All");
					month_combo.setEnabled(false);
					academicYear_combo.setEnabled(false);
					month_radio.setSelected(false);
				}
			}
		});
		
		cat_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String catType = (String) cat_combo.getSelectedItem();
				if (catType.equalsIgnoreCase("Update Attendance")) {
					from_label.setText("Select :");
					findPanel.add(date_radio);
					findPanel.add(from_label);
					findPanel.add(datePickerFrom);
					findPanel.remove(exam_label);
					findPanel.remove(exam_combo);
					findPanel.remove(to_label);
					findPanel.remove(datePickerTo);
					findPanel.remove(month_radio);
					findPanel.remove(month_label);
					findPanel.remove(month_combo);
					findPanel.remove(year_label);
					findPanel.remove(academicYear_combo);
					findPanel.remove(downloadButton);
					findPanel.remove(uploadButton);
					findPanel.revalidate();
					findPanel.repaint();
				} 
				else if (catType.equalsIgnoreCase("Mark Vacation")) {
					from_label.setText("From :");
					findPanel.add(date_radio);
					findPanel.add(from_label);
					findPanel.add(datePickerFrom);
					findPanel.add(to_label);
					findPanel.add(datePickerTo);
					findPanel.remove(exam_label);
					findPanel.remove(exam_combo);
					findPanel.remove(month_radio);
					findPanel.remove(month_label);
					findPanel.remove(month_combo);
					findPanel.remove(year_label);
					findPanel.remove(academicYear_combo);
					findPanel.remove(downloadButton);
					findPanel.remove(uploadButton);
					findPanel.revalidate();
					findPanel.repaint();
				} else if(catType.equalsIgnoreCase("Manual Attendance")){
					findPanel.remove(date_radio);
					findPanel.remove(from_label);
					findPanel.remove(datePickerFrom);
					findPanel.remove(to_label);
					findPanel.remove(datePickerTo);
					findPanel.add(month_radio);
					findPanel.add(month_label);
					findPanel.add(month_combo);
					findPanel.add(year_label);
					findPanel.add(academicYear_combo);
					findPanel.add(exam_label);
					findPanel.add(exam_combo);
					findPanel.add(downloadButton);
					findPanel.add(uploadButton);
					findPanel.revalidate();
					findPanel.repaint();
				} else if(catType.equalsIgnoreCase("Attendance Report")){
					from_label.setText("From :");
					findPanel.add(date_radio);
					findPanel.add(from_label);
					findPanel.add(datePickerFrom);
					findPanel.add(to_label);
					findPanel.add(datePickerTo);
					findPanel.remove(exam_label);
					findPanel.remove(exam_combo);
					findPanel.add(month_radio);
					findPanel.add(month_label);
					findPanel.add(month_combo);
					findPanel.add(year_label);
					findPanel.add(academicYear_combo);
					findPanel.remove(downloadButton);
					findPanel.remove(uploadButton);
					findPanel.revalidate();
					findPanel.repaint();
				}
				else {
					from_label.setText("From :");
					findPanel.add(date_radio);
					findPanel.add(from_label);
					findPanel.add(datePickerFrom);
					findPanel.add(to_label);
					findPanel.add(datePickerTo);
					findPanel.add(month_radio);
					findPanel.add(month_label);
					findPanel.add(month_combo);
					findPanel.add(year_label);
					findPanel.add(academicYear_combo);
					findPanel.remove(downloadButton);
					findPanel.remove(uploadButton);
					findPanel.revalidate();
					findPanel.repaint();
				}

			}
		});

		// /////////////Submit//////////////
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(mainCentre - 150, 90, 130, 25);
		findPanel.add(submitButton);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean validateFields = true;
				boolean isHalfDaySel = false;
				String std = "";
				String div = "";
				String academicSel = "";
				String catType = "";
				String print = "";
				String fromDate = "";
				String toDate = "";
				String attendStatus = "";
				String month = "";
				String year = "";
				int addOnTokens = 0;

				std = (String) admittedStd_combo.getSelectedItem();
				div = (String) admittedDiv_combo.getSelectedItem();
				catType = (String) cat_combo.getSelectedItem();
				academicSel = (String) academicYear_combo.getSelectedItem();
				isHalfDaySel = halfDayRadio.isSelected();
				if(month_radio.isSelected()){
					month = (String) month_combo.getSelectedItem();
					year = (String) academicYear_combo.getSelectedItem();
				}
				
				Date today = new Date();
				
		        Date selectedFromDate = (Date) datePickerFrom.getModel().getValue();
		        fromDate = commonObj.dateToDDMMYYYY(selectedFromDate);
		        
		        Date selectedToDate = (Date) datePickerTo.getModel().getValue();
		        toDate = commonObj.dateToDDMMYYYY(selectedToDate);
		        
		        if(!catType.equalsIgnoreCase("Update Attendance")){
					today = selectedToDate;
				}
		        
				if (std.equalsIgnoreCase("Select")){
					commonObj.showMessageDialog("Please select std");
					validateFields = false;
				}
				else if (validateFields && div.equalsIgnoreCase("Select")){
					commonObj.showMessageDialog("Please select div");
					validateFields = false;
				}
				else if(commonObj.daysBetween(selectedFromDate, today) < 0){
					commonObj.showMessageDialog("Please select valid date");
					validateFields = false;
				}
				/*else if(commonObj.daysBetween(selectedToDate, today) < 0){
					commonObj.showMessageDialog("Please select valid date");
					validateFields = false;
				}*/
				else if(commonObj.daysBetween(selectedFromDate, selectedToDate) < 0 && !catType.equalsIgnoreCase("Update Attendance")){
					commonObj.showMessageDialog("From date cannot be less than To date");
					validateFields = false;
				}
				else if(commonObj.daysBetween(selectedFromDate, selectedToDate) > 6 && catType.equalsIgnoreCase("Attendance Report") && month.equalsIgnoreCase("")){
					commonObj.showMessageDialog("Please select a period of 7 days or less.");
					validateFields = false;
				}
				/*else if(catType.equalsIgnoreCase("Attendance Report") && commonObj.daysBetween(selectedFromDate, selectedToDate) > 7){
					commonObj.showMessageDialog("Please select a period of 7 days for daywise status.");
					validateFields = false;
				}*/
				else if(catType.equalsIgnoreCase("Manual Attendance") && 
						exam_combo.getSelectedItem().toString().equalsIgnoreCase("Select") && 
						(month.equalsIgnoreCase("All") || month.equalsIgnoreCase(""))){
					commonObj.showMessageDialog("Please select Exam");
					validateFields = false;
				}
				
				if (validateFields) {//validateFields
					try {
						if(dbValidate.connectDatabase(sessionData)){
						
							if(catType.equalsIgnoreCase("Mark Vacation")){
								attendStatus = "HOLIDAY";
							} else if(catType.equalsIgnoreCase("Update Attendance")){
								attendStatus = "PRESENT";
							}
							
							if(!catType.equalsIgnoreCase("Attendance Report")){
								
								if(!catType.equalsIgnoreCase("Manual Attendance")){
									////First check for records not present in attendance table
									recordsNotInAttendanceList = dbValidate.recordsNotInAttendanceForDate(sessionData, std, div, academicSel, section, fromDate, attendStatus);
									if (recordsNotInAttendanceList.size() > 0) {
										foundStudentList = recordsNotInAttendanceList;
										frame.setVisible(false);
										new AttendanceUpdate(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType,"",
												fromDate,toDate,true,"",true, "", isHalfDaySel);
									} else {
									////check for records present in attendance table
										recordsNotInAttendanceList = dbValidate.recordsFoundInAttendanceForDate(sessionData, std, div, academicSel, section, fromDate, "");
										if(recordsNotInAttendanceList.size() <= 0){
											commonObj.showMessageDialog("No data found.");
											frame.setVisible(false);
											new AttendanceUpdate(sessionData, std, div, academicSel, section, user_name, user_role, recordsNotInAttendanceList, catType,"",
													fromDate,toDate,false,"",true, "", isHalfDaySel);
										}
										else{
											foundStudentList = recordsNotInAttendanceList;
											frame.setVisible(false);
											new AttendanceUpdate(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType,"",
													fromDate,toDate,false,"",false, "", isHalfDaySel);
										}
									}
								}
								else{
									String exam = (String) exam_combo.getSelectedItem();
							    	if(exam.equalsIgnoreCase("Select") && month.equalsIgnoreCase("")){
							    		JOptionPane.showMessageDialog(null, "Please select Exam or Month");
							    	}
							    	else{
							    		dbValidate.newStudents(sessionData, academicSel, std, div, section, "ATTENDANCE_PERIOD");
							    		foundStudentList = dbValidate.findAtendancePeriodicList(sessionData, "", std, div, 
							    				"", "", "", academicSel, "", "", section, true, exam, month, "");
							    		frame.setVisible(false);
							    		new AttendanceUpdate(sessionData, std, div, academicSel, section, user_name, user_role, 
							    				foundStudentList, catType, month, fromDate,toDate,false,"",false, exam, isHalfDaySel);
							    	}
								}
							} else{
								boolean isMonthSelected = false;
								isMonthSelected = month_radio.isSelected();
								if(isMonthSelected){
									addOnTokens = 5;
									foundStudentList = dbValidate.attendanceReport(sessionData, academicSel, std, div, section, fromDate, toDate, month, year);
									if(foundStudentList.size() <= 0){
										commonObj.showMessageDialog("No data found.");
										frame.setVisible(false);
										new AttendanceReport(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType, month,fromDate,toDate,false,"", addOnTokens, isMonthSelected);
									} else{
										frame.setVisible(false);
										new AttendanceReport(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType, month,fromDate,toDate,false,"", addOnTokens, isMonthSelected);
									}
								} else{
									addOnTokens = commonObj.daysBetween(selectedFromDate, selectedToDate)+1;
									foundStudentList = dbValidate.attendanceStatus(sessionData, academicSel, std, div, section, fromDate, toDate, month, year, addOnTokens);
									if(foundStudentList.size() <= 0){
										commonObj.showMessageDialog("No data found.");
										frame.setVisible(false);
										new AttendanceReport(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType, month,fromDate,toDate,false,"", addOnTokens, isMonthSelected);
									} else{
										frame.setVisible(false);
										new AttendanceReport(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType, month,fromDate,toDate,false,"", addOnTokens, isMonthSelected);
									}
								}
							}
						}
					} catch (Exception e1) {
						commonObj.logException(e1);
					} finally {
						dbValidate.closeDatabase(sessionData);
					}
				}
			}

		});
		// /////////////Reset//////////////
		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		resetButton.setBounds(mainCentre + 75, 90, 130, 25);
		findPanel.add(resetButton);

		resetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				List studentList = new ArrayList();
				frame.setVisible(false);
				new AttendanceUpdate(sessionData, "", "", "", section, user_name, user_role, studentList, "","","","",false,"",true, "", false);
			}
		});

		bottombandPanel.add(findPanel, BorderLayout.EAST);
		// ////////////////find panel ends/////////////////////////////////

		// //////////scrollArea panel/////////////////////////////////////
		JPanel scrollAreaPanel = new JPanel(new BorderLayout());
		scrollAreaPanel.setPreferredSize(new Dimension(screenWidth - 150, screenHeight - 337));

		// ///////////Data Panel/////////////
		final JPanel dataPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				Image img = new ImageIcon(img_path + img_mainband).getImage();
				
				// change height to change scrolling area /////////////
				retCount = foundStudentList.size();
				scrollHeight = (retCount - 6) * 30; // to adjust the perfect scroll height
				if (scrollHeight < 0)
				{
					scrollHeight = 0;
				}
				////////////////////////
				
				Dimension size = new Dimension(screenWidth - 158, ((screenHeight - 330) + scrollHeight));
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				// g.drawImage(img, 0, 0, null);
				g.drawImage(img, 0, 0, screenWidth - 158, (screenHeight - 330) + scrollHeight, null);
			}
		};
		dataPanel.setLayout(null);
		
		int listSize = foundStudentList.size();

		String[] dataArray = new String[listSize];
		dataArray = foundStudentList.toArray(dataArray);

		if (listSize > 0) {

			int j = 20;
			int l = 0;
			String line1 = "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
			String line2 = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
			
			///////////////////////header////////////////////
			JLabel pipe_label1 = new JLabel("|");
			pipe_label1.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label1.setBounds(60, j, 120, 50);
			dataPanel.add(pipe_label1);
			
			JLabel roll_label = new JLabel("Roll No.");
			roll_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			roll_label.setBounds(80, j, 80, 50);
			dataPanel.add(roll_label);

			JLabel pipe_label2 = new JLabel("|");
			pipe_label2.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label2.setBounds(140, j, 120, 50);
			dataPanel.add(pipe_label2);
			
			final JLabel gr_label = new JLabel("Gr No.");
			gr_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			gr_label.setBounds(160, j, 60, 50);
			dataPanel.add(gr_label);

			JLabel pipe_label3 = new JLabel("|");
			pipe_label3.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label3.setBounds(220, j, 120, 50);
			dataPanel.add(pipe_label3);
			
			JLabel name_label = new JLabel("Name");
			name_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			name_label.setBounds(240, j + 12, 390, 20);
			dataPanel.add(name_label);

			JLabel pipe_label4 = new JLabel("|");
			pipe_label4.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label4.setBounds(630, j, 120, 50);
			dataPanel.add(pipe_label4);
			
			String[] attendanceDropDown = attendanceList.split(",");
			// /////////////Default/Optional //////////////
			final JComboBox attendanceHeader_combo = new JComboBox(attendanceDropDown);
			if(!updateAllClass.equalsIgnoreCase("")){
				attendanceHeader_combo.setSelectedItem(updateAllClass);
			}
			attendanceHeader_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			attendanceHeader_combo.setBounds(650, j + 12, 130, 20);
			attendanceHeader_combo.setEnabled(isEditClass);
			if(catTypeClass.equalsIgnoreCase("Mark Vacation")){
				attendanceHeader_combo.setSelectedItem("HOLIDAY");
				attendanceHeader_combo.setEnabled(false);
			}
			if(!catTypeClass.equalsIgnoreCase("Manual Attendance")){
				dataPanel.add(attendanceHeader_combo);
			}
			
			JLabel totalDays_label = new JLabel("Total Days : ");
			totalDays_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			totalDays_label.setBounds(650, j - 10, 390, 20);
			
			final JTextField totalDays_text = new JTextField();
			totalDays_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			if(examClass.equalsIgnoreCase("Final")){
				totalDays_text.setEditable(false);
			}
			else {
				totalDays_text.setEditable(isEditClass);
			}
			totalDays_text.setBounds(750, j - 10, 50, 20);
			
			JLabel attendedDays_label = new JLabel("Days Attended");
			attendedDays_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			attendedDays_label.setBounds(660, j + 12, 390, 20);
			
			if(catTypeClass.equalsIgnoreCase("Manual Attendance")){
				dataPanel.add(totalDays_label);
				dataPanel.add(totalDays_text);
				dataPanel.add(attendedDays_label);
			}
			
			JLabel lineheader_labels = new JLabel(line1);
			lineheader_labels.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			lineheader_labels.setBounds(40, j + 10, 1100, 50);
			dataPanel.add(lineheader_labels);

			attendanceHeader_combo.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {
						String selAllAttendance = (String) attendanceHeader_combo.getSelectedItem();
						
						frame.setVisible(false);
						new AttendanceUpdate(sessionData, std, div, academicYearClass, section, user_name, user_role, foundStudentList, catTypeClass,"",
								fromDateClass,toDateClass,isRecordNewClass,selAllAttendance,true, "", false);
					} catch (Exception e1) {
						commonObj.logException(e1);
					}
				}
			});
			
			totalDays_text.addFocusListener(new FocusListener()  {
				
				String studentDetail = "";

				@Override
				public void focusGained(FocusEvent arg0) {
					///////
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					List<String> tempStudentList = new ArrayList();
					String totalDays = totalDays_text.getText();
					for(int i=0; i<foundStudentList.size(); i++){
						String studentData = foundStudentList.get(i);
						studentData = studentData.substring(0, studentData.indexOf("/")+1)+totalDays;
						tempStudentList.add(studentData);
					}
					foundStudentList.clear();
					foundStudentList = tempStudentList;
				}
			});
			
			// /////////////Edit//////////////
			JButton editButton = new JButton("Edit");
			editButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			editButton.setBounds(900, j+6, 130, 25);
			editButton.setEnabled(!isEditClass);
			dataPanel.add(editButton);

			editButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					boolean validateFields = true;
					boolean isHalfDaySel = false;
					String std = "";
					String div = "";
					String academicSel = "";
					String catType = "";
					String print = "";
					String fromDate = "";
					String toDate = "";
					String attendStatus = "";
					String month = "";
					String year = "";
					int addOnTokens = 0;

					std = (String) admittedStd_combo.getSelectedItem();
					div = (String) admittedDiv_combo.getSelectedItem();
					catType = (String) cat_combo.getSelectedItem();
					academicSel = (String) academicYear_combo.getSelectedItem();
					isHalfDaySel = halfDayRadio.isSelected();
					if(month_radio.isSelected()){
						month = (String) month_combo.getSelectedItem();
						year = (String) academicYear_combo.getSelectedItem();
					}
					
					Date today = new Date();
					
			        Date selectedFromDate = (Date) datePickerFrom.getModel().getValue();
			        fromDate = commonObj.dateToDDMMYYYY(selectedFromDate);
			        
			        Date selectedToDate = (Date) datePickerTo.getModel().getValue();
			        toDate = commonObj.dateToDDMMYYYY(selectedToDate);
			        
			        if(!catType.equalsIgnoreCase("Update Attendance")){
						today = selectedToDate;
					}
			        
					if (std.equalsIgnoreCase("Select")){
						commonObj.showMessageDialog("Please select std");
						validateFields = false;
					}
					else if (validateFields && div.equalsIgnoreCase("Select")){
						commonObj.showMessageDialog("Please select div");
						validateFields = false;
					}
					else if(commonObj.daysBetween(selectedFromDate, today) < 0){
						commonObj.showMessageDialog("Please select valid date");
						validateFields = false;
					}
					/*else if(commonObj.daysBetween(selectedToDate, today) < 0){
						commonObj.showMessageDialog("Please select valid date");
						validateFields = false;
					}*/
					else if(commonObj.daysBetween(selectedFromDate, selectedToDate) < 0 && !catType.equalsIgnoreCase("Update Attendance")){
						commonObj.showMessageDialog("From date cannot be less than To date");
						validateFields = false;
					}
					else if(commonObj.daysBetween(selectedFromDate, selectedToDate) > 6 && catType.equalsIgnoreCase("Attendance Report") && month.equalsIgnoreCase("")){
						commonObj.showMessageDialog("Please select a period of 7 days or less.");
						validateFields = false;
					}
					/*else if(catType.equalsIgnoreCase("Attendance Report") && commonObj.daysBetween(selectedFromDate, selectedToDate) > 7){
						commonObj.showMessageDialog("Please select a period of 7 days for daywise status.");
						validateFields = false;
					}*/
					else if(catType.equalsIgnoreCase("Manual Attendance") && 
							exam_combo.getSelectedItem().toString().equalsIgnoreCase("Select") && 
							(month.equalsIgnoreCase("All") || month.equalsIgnoreCase(""))){
						commonObj.showMessageDialog("Please select Exam");
						validateFields = false;
					}
					
					if (validateFields) {//validateFields
						try {
							if(dbValidate.connectDatabase(sessionData)){
							
								if(catType.equalsIgnoreCase("Mark Vacation")){
									attendStatus = "HOLIDAY";
								} else if(catType.equalsIgnoreCase("Update Attendance")){
									attendStatus = "PRESENT";
								}
								
								if(!catType.equalsIgnoreCase("Attendance Report")){
									
									if(!catType.equalsIgnoreCase("Manual Attendance")){
										////First check for records not present in attendance table
										recordsNotInAttendanceList = dbValidate.recordsNotInAttendanceForDate(sessionData, std, div, academicSel, section, fromDate, attendStatus);
										if (recordsNotInAttendanceList.size() > 0) {
											foundStudentList = recordsNotInAttendanceList;
											frame.setVisible(false);
											new AttendanceUpdate(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType,"",
													fromDate,toDate,true,"",true, "", isHalfDaySel);
										} else {
										////check for records present in attendance table
											recordsNotInAttendanceList = dbValidate.recordsFoundInAttendanceForDate(sessionData, std, div, academicSel, section, fromDate, "");
											if(recordsNotInAttendanceList.size() <= 0){
												commonObj.showMessageDialog("No data found.");
												frame.setVisible(false);
												new AttendanceUpdate(sessionData, std, div, academicSel, section, user_name, user_role, recordsNotInAttendanceList, catType,"",
														fromDate,toDate,false,"",true, "", isHalfDaySel);
											}
											else{
												foundStudentList = recordsNotInAttendanceList;
												frame.setVisible(false);
												new AttendanceUpdate(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType,"",
														fromDate,toDate,false,"",true, "", isHalfDaySel);
											}
										}
									}
									else{
										String exam = (String) exam_combo.getSelectedItem();
								    	if(exam.equalsIgnoreCase("Select") && month.equalsIgnoreCase("")){
								    		JOptionPane.showMessageDialog(null, "Please select Exam or Month");
								    	}
								    	else{
								    		dbValidate.newStudents(sessionData, academicSel, std, div, section, "ATTENDANCE_PERIOD");
								    		foundStudentList = dbValidate.findAtendancePeriodicList(sessionData, "", std, div, 
								    				"", "", "", academicSel, "", "", section, true, exam, month, "");
								    		frame.setVisible(false);
								    		new AttendanceUpdate(sessionData, std, div, academicSel, section, user_name, user_role, 
								    				foundStudentList, catType, month, fromDate,toDate,false,"",true, exam, isHalfDaySel);
								    	}
									}
								} else{
									boolean isMonthSelected = false;
									isMonthSelected = month_radio.isSelected();
									if(isMonthSelected){
										addOnTokens = 5;
										foundStudentList = dbValidate.attendanceReport(sessionData, academicSel, std, div, section, fromDate, toDate, month, year);
										if(foundStudentList.size() <= 0){
											commonObj.showMessageDialog("No data found.");
											frame.setVisible(false);
											new AttendanceReport(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType, month,fromDate,toDate,false,"", addOnTokens, isMonthSelected);
										} else{
											frame.setVisible(false);
											new AttendanceReport(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType, month,fromDate,toDate,false,"", addOnTokens, isMonthSelected);
										}
									} else{
										addOnTokens = commonObj.daysBetween(selectedFromDate, selectedToDate)+1;
										foundStudentList = dbValidate.attendanceStatus(sessionData, academicSel, std, div, section, fromDate, toDate, month, year, addOnTokens);
										if(foundStudentList.size() <= 0){
											commonObj.showMessageDialog("No data found.");
											frame.setVisible(false);
											new AttendanceReport(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType, month,fromDate,toDate,false,"", addOnTokens, isMonthSelected);
										} else{
											frame.setVisible(false);
											new AttendanceReport(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType, month,fromDate,toDate,false,"", addOnTokens, isMonthSelected);
										}
									}
								}
							}
						} catch (Exception e1) {
							commonObj.logException(e1);
						} finally {
							dbValidate.closeDatabase(sessionData);
						}
					}
//					
//					List studentList = new ArrayList();
//					frame.setVisible(false);
//					new AttendanceUpdate(sessionData, stdClass, divClass, academicYearClass, section, user_name, user_role, foundStudentList, catTypeClass,"",
//							fromDateClass,toDateClass,isRecordNewClass,updateAllClass,true,"", false);
				}
			});
			
			///////header ends/////////////////////
			
			JLabel[] pipe_labels1 = new JLabel[listSize];
			JLabel[] line_labels = new JLabel[listSize];
			
			JLabel[] pipe_labels2 = new JLabel[listSize];
			JLabel[] pipe_labels3 = new JLabel[listSize];
			JLabel[] pipe_labels4 = new JLabel[listSize];
			JLabel[] pipe_labels5 = new JLabel[listSize];
			JLabel[] pipe_labels6 = new JLabel[listSize];
			
			final JLabel[] temp1_labels = new JLabel[listSize];
			final JLabel[] temp2_labels = new JLabel[listSize];
			final JLabel[] temp3_labels = new JLabel[listSize];
			final JLabel[] temp4_labels = new JLabel[listSize];
			final JLabel[] temp5_labels = new JLabel[listSize];
			
			final JComboBox[] attendance_combo = new JComboBox[listSize];
			final JLabel[] attendedDays_labels = new JLabel[listSize];
			final JTextField[] attendedDaysText = new JTextField[listSize];
			final JTextField[] contact1Text = new JTextField[listSize];
			final JTextField[] contact2Text = new JTextField[listSize];
			final JRadioButton[] grRadio = new JRadioButton[listSize];
			final JTextField[] reasonText = new JTextField[listSize];
			
			int k = 0;
			for (int i = 0; i < listSize; i++) {
				j = j + 30;
				k = j;
				l = j + 50;
				int tokenSize = 0;
				int m = 0;

				StringTokenizer st = new StringTokenizer(dataArray[i], "|");
				tokenSize = st.countTokens();
				String[] columnArray = new String[tokenSize];
				while (st.hasMoreTokens()) {
					columnArray[m] = st.nextToken();
					m++;
				}

				if(tokenSize == 5){
					line1 = line2;
				}
				
				pipe_labels1[i] = new JLabel("|");
				pipe_labels1[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels1[i].setBounds(60, j, 120, 50);
				dataPanel.add(pipe_labels1[i]);

				grRadio[i] = new JRadioButton();
				grRadio[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				grRadio[i].setBorder(null);
				grRadio[i].setBounds(40, j+16, 12, 12);
				if(isHalfDayClass){
					dataPanel.add(grRadio[i]);
				}
				
				//column 1
				temp1_labels[i] = new JLabel(columnArray[0]);
				temp1_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				temp1_labels[i].setBounds(80, j, 80, 50);
				dataPanel.add(temp1_labels[i]);

				pipe_labels2[i] = new JLabel("|");
				pipe_labels2[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels2[i].setBounds(140, j, 120, 50);
				dataPanel.add(pipe_labels2[i]);

				//column 2
				temp2_labels[i] = new JLabel(columnArray[1]);
				temp2_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				temp2_labels[i].setBounds(160, j, 60, 50);
				temp2_labels[i].setToolTipText(columnArray[1]);
				dataPanel.add(temp2_labels[i]);

				pipe_labels3[i] = new JLabel("|");
				pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels3[i].setBounds(220, j, 120, 50);
				dataPanel.add(pipe_labels3[i]);

				//column 3
				temp3_labels[i] = new JLabel(columnArray[2]);
				temp3_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				temp3_labels[i].setBounds(240, j + 12, 390, 20);
				temp3_labels[i].setToolTipText(columnArray[2]);
				dataPanel.add(temp3_labels[i]);
				
				pipe_labels4[i] = new JLabel("|");
				pipe_labels4[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels4[i].setBounds(630, j, 120, 50);
				dataPanel.add(pipe_labels4[i]);

				attendance_combo[i] = new JComboBox(attendanceDropDown);
				attendedDays_labels[i] = new JLabel("Attended Days : ");
				attendedDaysText[i] = new JTextField("");
				reasonText[i] = new JTextField("Select to add reason");
				
				if(!catTypeClass.equalsIgnoreCase("Manual Attendance") && !isHalfDayClass){
					attendance_combo[i].setEnabled(isEditClass);
					if(catTypeClass.equalsIgnoreCase("Mark Vacation")){
						attendance_combo[i].setSelectedItem("HOLIDAY");
						attendance_combo[i].setEnabled(false);
					} else if(!updateAllClass.equalsIgnoreCase("")){
						attendance_combo[i].setSelectedItem(updateAllClass);
						foundStudentList.remove(dataArray[i]);
						foundStudentList.add(columnArray[0] +"|"+ columnArray[1] +"|"+ columnArray[2] +"|"+ updateAllClass);
					}
					else if(!columnArray[3].trim().equalsIgnoreCase("")){
						attendance_combo[i].setSelectedItem(columnArray[3].trim());
					}
					attendance_combo[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					attendance_combo[i].setBounds(650, j + 12, 130, 20);
					dataPanel.add(attendance_combo[i]);
				}else if(!isHalfDayClass) {
					attendedDays_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					attendedDays_labels[i].setBounds(650, j + 12, 150, 20);
					
					String attendedDays = columnArray[3].substring(0, columnArray[3].indexOf("/"));
					String totalDays = columnArray[3].substring(columnArray[3].indexOf("/")+1);
					if(totalDays_text.getText() != null && !totalDays_text.getText().equalsIgnoreCase("") && (Integer.parseInt(totalDays) > Integer.parseInt(totalDays_text.getText()))){
						totalDays_text.setText(totalDays);
					}
					else{
						totalDays_text.setText(totalDays);
					}
					attendedDaysText[i].setText(attendedDays);
					if(examClass.equalsIgnoreCase("Final")){
						attendedDaysText[i].setEditable(false);
					}
					else {
						attendedDaysText[i].setEditable(isEditClass);
					}
					attendedDaysText[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					attendedDaysText[i].setBounds(690, j + 12, 50, 20);
					dataPanel.add(attendedDaysText[i]);
				} else{
					reasonText[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					reasonText[i].setEnabled(false);
					reasonText[i].setBounds(650, j + 12, 400, 20);
					dataPanel.add(reasonText[i]);
				}
				
				if(studentLCMap.containsKey(columnArray[1])) {
					temp1_labels[i].setForeground(Color.RED);
					temp2_labels[i].setForeground(Color.RED);
					temp3_labels[i].setForeground(Color.RED);
//					attendance_combo[i].setEnabled(false);
//					attendedDaysText[i].setEnabled(false);
//					reasonText[i].setEnabled(false);
				}
				
				if(columnArray.length > 4){
					contact1Text[i] = new JTextField(columnArray[4]);
					contact1Text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					
					contact2Text[i] = new JTextField(columnArray[5]);
					contact2Text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				}
				else{
					contact1Text[i] = new JTextField("0");
					contact1Text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					
					contact2Text[i] = new JTextField("0");
					contact2Text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				}
				
				
				final int n = i;
				attendance_combo[i].addFocusListener(new FocusListener()  {
					
					String studentDetail = "";

					@Override
					public void focusGained(FocusEvent arg0) {

						studentDetail = temp1_labels[n].getText() + "|" + temp2_labels[n].getText() + "|"
								+ temp3_labels[n].getText() + "|" + attendance_combo[n].getSelectedItem() + "|" 
								+ contact1Text[n].getText() + "|" + contact2Text[n].getText();
						foundStudentList.remove(studentDetail);
						studentMap.remove(temp2_labels[n].getText());
						
					}

					@Override
					public void focusLost(FocusEvent arg0) {

						studentDetail = temp1_labels[n].getText() + "|" + temp2_labels[n].getText() + "|"
								+ temp3_labels[n].getText() + "|" + attendance_combo[n].getSelectedItem() + "|" 
								+ contact1Text[n].getText() + "|" + contact2Text[n].getText();
						foundStudentList.add(studentDetail);
						studentMap.put(temp2_labels[n].getText(), studentDetail);
					}
				});
				
				attendedDaysText[i].addFocusListener(new FocusListener()  {
					
					String studentDetail = "";

					@Override
					public void focusGained(FocusEvent arg0) {
						String attendedDays = attendedDaysText[n].getText().trim().equalsIgnoreCase("") ? " " : attendedDaysText[n].getText();
						String totalDays = totalDays_text.getText().trim().equalsIgnoreCase("") ? " " : totalDays_text.getText();
						studentDetail = temp1_labels[n].getText() + "|" + temp2_labels[n].getText() + "|"
								+ temp3_labels[n].getText() + "|" + attendedDays+"/"+totalDays;
						foundStudentList.remove(studentDetail);
						studentMap.remove(temp2_labels[n].getText());
						attendedDaysText[n].selectAll();
					}

					@Override
					public void focusLost(FocusEvent arg0) {
						String attendedDays = attendedDaysText[n].getText().trim().equalsIgnoreCase("") ? " " : attendedDaysText[n].getText();
						String totalDays = totalDays_text.getText().trim().equalsIgnoreCase("") ? " " : totalDays_text.getText();
						studentDetail = temp1_labels[n].getText() + "|" + temp2_labels[n].getText() + "|"
								+ temp3_labels[n].getText() + "|" + attendedDays+"/"+totalDays;
						studentMap.put(temp2_labels[n].getText(), studentDetail);
						foundStudentList.add(studentDetail);
					}
				});
				
				grRadio[i].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						boolean isradioSelected = grRadio[n].isSelected();
						String grSelected = gr_label.getText();
						
						if(isradioSelected){
							reasonText[n].setText("");
							reasonText[n].setEnabled(true);
						}
						else{
							reasonText[n].setText("Select to add reason");
							reasonText[n].setEnabled(false);
							reasonMap.remove(grSelected);
						}
					}
				});
				
				reasonText[i].addFocusListener(new FocusListener()  {
					
					String studentDetail = "";

					@Override
					public void focusGained(FocusEvent arg0) {
						String reasonTextSel = reasonText[n].getText().trim().equalsIgnoreCase("") ? " " : reasonText[n].getText();
						reasonText[n].selectAll();
					}

					@Override
					public void focusLost(FocusEvent arg0) {
						LinkedHashMap<String, String> grMap = new LinkedHashMap<String, String>();
						String reasonTextSel = reasonText[n].getText().trim();
						String grSelected = gr_label.getText();
						String contact1Sel = contact1Text[n].getText();
						String contact2Sel = contact2Text[n].getText();
						String rollSel = temp1_labels[n].getText();
						String nameSel = temp2_labels[n].getText();
						
						grMap.put("reason", reasonTextSel);
						grMap.put("gr_no", grSelected);
						grMap.put("std", stdClass);
						grMap.put("div", divClass);
						grMap.put("roll_no", rollSel);
						grMap.put("name", nameSel);
						grMap.put("contact1", contact1Sel);
						grMap.put("contact2", contact2Sel);
						
						reasonMap.put(grSelected, grMap);
					}
				});
				
				line_labels[i] = new JLabel(line1);
				line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				line_labels[i].setBounds(40, j + 10, 1100, 50);
				dataPanel.add(line_labels[i]);
			}
			// /////////////Print//////////////
			JButton updateButton = new JButton("Update");
			updateButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			updateButton.setBounds(mainCentre - 50, k + 60, 160, 25);
			updateButton.setEnabled(isEditClass);
			dataPanel.add(updateButton);

			updateButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					boolean insertAttendance = true;
					JFrame f = new JFrame("Attendance Update in progress. Please Don't Close");
					try {
						f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
					    f.setSize(500, 0);
					    f.setResizable(false);
					    f.setVisible(true);
					    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					    
					    if(catTypeClass.equalsIgnoreCase("Mark Vacation")){
							insertAttendance = dbValidate.markVacationtAttendance(sessionData, foundStudentList, academicYearClass, stdClass, divClass, 
									false, section, fromDateClass, toDateClass, updateAllClass);
						}
					    else if(catTypeClass.equalsIgnoreCase("Manual Attendance")){
					    	String totalDays = totalDays_text.getText();
					    	
					    	if(totalDays.trim().equalsIgnoreCase("")){
					    		JOptionPane.showMessageDialog(null, "Please insert Total Days");
					    	}
					    	else{
					    		boolean validFlag = true;
					    		String studentDetail = "";
				    			String attendance = "";
				    			String rollNo = "";
				    			int present = 0;
				    			int maxDays = 0;
				    			foundStudentList.clear();
				    			Set set = studentMap.entrySet();
				    			Iterator n = set.iterator();
				    			while(n.hasNext()) {
				    				Map.Entry me = (Map.Entry)n.next();
				    				studentDetail = me.getValue().toString();
				    				rollNo = studentDetail.substring(0, studentDetail.indexOf("|"));
					    			attendance = studentDetail.substring(studentDetail.lastIndexOf("|")+1);
					    			present = Integer.parseInt(attendance.substring(0, attendance.indexOf("/")));
					    			maxDays = Integer.parseInt(attendance.substring(attendance.indexOf("/")+1));
					    			if(present > Integer.parseInt(totalDays)){
					    				validFlag = false;
					    				insertAttendance = false;
					    				commonObj.showMessageDialog("Roll No. "+rollNo+" has present days more than total days");
					    				break;
					    			}
					    			studentDetail = studentDetail.replace("/"+maxDays, "/"+totalDays);
					    			foundStudentList.add(studentDetail);
				    			}
				    			
					    		/*for(int i = 0; i < foundStudentList.size(); i++){
					    			studentDetail = foundStudentList.get(i);
					    			rollNo = studentDetail.substring(0, studentDetail.indexOf("|"));
					    			attendance = studentDetail.substring(studentDetail.lastIndexOf("|")+1);
					    			present = Integer.parseInt(attendance.substring(0, attendance.indexOf("/")));
					    			maxDays = Integer.parseInt(attendance.substring(attendance.indexOf("/")+1));
					    			if(present > maxDays){
					    				validFlag = false;
					    				insertAttendance = false;
					    				commonObj.showMessageDialog("Roll No. "+rollNo+" has present days more than total days");
					    				break;
					    			}
					    		}*/
					    		if(validFlag){
					    			insertAttendance = dbValidate.updateManualAttendance(sessionData, foundStudentList, academicYearClass, 
											stdClass, divClass, true, section, examClass, monthClass);
					    		}
					    	}
							
						}else if(isRecordNewClass){
							insertAttendance = dbValidate.insertStudentAttendance(sessionData, foundStudentList, academicYearClass, stdClass, divClass, 
									false, section, fromDateClass, toDateClass, updateAllClass);
						}
						else{
							insertAttendance = dbValidate.insertStudentAttendance(sessionData, foundStudentList, academicYearClass, stdClass, divClass, 
									true, section, fromDateClass, toDateClass, updateAllClass);
						}
					    
					    if(isHalfDayClass && reasonMap.size() > 0 
					    		&& bundle.getString("SMS_ATTENDANCE_FLAG").equalsIgnoreCase("true")){
							
					    	List<String> passGrList = new ArrayList();
							LinkedHashMap foundStudentMap = new LinkedHashMap<>();
							String grNoSms = "", smsText = "", smsTemplateId = "";
							//iterate students
							Set setForSms = reasonMap.entrySet();
							Iterator j = setForSms.iterator();
							while(j.hasNext()) {
								passGrList.clear();
								foundStudentMap.clear();
								LinkedHashMap<String, String> grMap = new LinkedHashMap<String, String>();
								
								Map.Entry me = (Map.Entry)j.next();
								grNoSms = me.getKey().toString();
								grMap = (LinkedHashMap<String, String>) me.getValue();
								passGrList.add(grNoSms);
								foundStudentMap.put(grNoSms, grMap);
								
								smsText = grMap.get("reason");
								smsTemplateId = bundle.getString("SMS_ATTENDANCE_REASON_TEMP_ID");
								
								String smsResponse = commonObj.sendHspSms(sessionData, passGrList, foundStudentMap, smsText, smsTemplateId, sessionData.getSectionName(), "", 
										academicYearClass, stdClass, divClass, "", "HALFATT");
							}
					    }
					    
						if(insertAttendance){
							commonObj.showMessageDialog("Attendance updated successfully.");
							List studentList = new ArrayList();
							frame.setVisible(false);
							new AttendanceUpdate(sessionData, stdClass, divClass, academicYearClass, section, user_name, user_role, studentList, catTypeClass,"",
									fromDateClass,toDateClass,isRecordNewClass,"",false, examClass, false);
						}
						else{
							commonObj.showMessageDialog("Attendance updation failed.");
						}
						f.setVisible(false);
					} catch (Exception e1) {
						f.setVisible(false);
						commonObj.logException(e1);
					} finally {
						dbValidate.closeDatabase(sessionData);
					}
				}
			});
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
	}
}
