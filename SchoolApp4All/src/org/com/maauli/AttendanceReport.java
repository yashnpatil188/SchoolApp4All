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
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import java.util.LinkedHashMap; 

public class AttendanceReport extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JFrame frame = null;

	static JComboBox studentcombo;

	static List<String> foundStudentList = new ArrayList();

	static int retCount = 0;

	static int scrollHeight = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

//	static List<String> subjectList = new ArrayList();

	static boolean setSelected = false;

	static boolean printDuplicate = false;

	static String categoryList = "";
	
	static String monthlyList = "";
	
	static String monthClass = "";

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

	static String examCategory = "";
	
	static String fatherNameClass = "";

	static String academicYearClass = "";

	static String rollFromClass = "";

	static String rollToClass = "";

	static String category = "";

	static String selectStd = "";

	static String selectDiv = "";

	static String user_name = "";

	static String user_role = "";

	static DBValidate dbValidate = new DBValidate();

	static SessionData sessionData = new SessionData();

	static Common commonObj = new Common();

	static String std = "";

	static String div = "";

	static String section = "";
	
	static String attendanceList = "";

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

	static String yearList = "";
	
	static int addOnTokensClass = 0;
	
	static boolean isMonthSelectedClass = false;
	
//	List<String> dataList = null;
	
	static List<String> recordsNotInAttendanceList = null;
	
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
	
	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static Logger logger = Logger.getLogger(AttendanceReport.class.getName());

	public AttendanceReport(SessionData sessionData1, String retStd, String retDiv, String academicYear, String sec, String retUserName, String retUserRole, List<String> retStudentList, 
			String retCatType, String retMonth, String retFrom, String retTo, boolean isRecordNew, String retupdateAll, int addOnTokens, boolean isMonthSelected) {
		System.gc();
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		stdClass = retStd;
		divClass = retDiv;
		catTypeClass = retCatType;
		updateAllClass = retupdateAll;
		isRecordNewClass = isRecordNew;
		fromDateClass = retFrom;
		toDateClass = retTo;
		monthClass = retMonth;
		isMonthSelectedClass = isMonthSelected;
		logger.info("retStd :: " + retStd);
		logger.info("retDiv :: " + retDiv);
		logger.info("retCatType :: " + retCatType);
		logger.info("academicYear :: " + academicYear);
		addOnTokensClass = addOnTokens;

		category = retCatType;
		section = sec;
		logger.info("section :: " + section);
		std = bundle.getString(section.toUpperCase() + "_STD");
		logger.info("std :: " + std);
		div = bundle.getString(section.toUpperCase() + "_DIV");
		logger.info("div :: " + div);
		secName = bundle.getString(section.toUpperCase() + "_SEC");
		logger.info("secName :: " + secName);
		categoryList = bundle.getString("ATTENDANCE_CATEGORY");
		attendanceList = bundle.getString("ATTENDANCE_STATUS");
		examCategory = bundle.getString("EXAM_CATEGORY");
		monthlyList = bundle.getString("MONTH_LIST");
		if (!retMonth.equalsIgnoreCase("")) {
			monthlyList = retMonth + ",All," + monthlyList;
		} else {
			monthlyList = "Select,All," + monthlyList;
		}
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

		String todayDate = commonObj.getCurrentDate();
		academicYearClass = commonObj.getAcademicYear(todayDate);
		if (!academicYear.equalsIgnoreCase("")) {
			academicYearClass = academicYear;
		}
		try {
			if(dbValidate.connectDatabase(sessionData)){
				yearList = dbValidate.findYearList(sessionData, "CLASS_ALLOTMENT");
			}
		} catch (Exception e1) {
			logger.info("Exception yearList ===>>>" + e1);
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
		retCount = foundStudentList.size();
		logger.info("foundStudentList size ==> " + retCount);
//		if (setSelected) {
//			selectAllCount = retCount;
//		}
//		scrollHeight = (retCount - 6) * 30; // to adjust the perfect scroll
//											// height
//		if (scrollHeight < 0)
//			scrollHeight = 0;

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

		JButton classAllotButton = new JButton("Class Allotment");
        classAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        classAllotButton.setBounds(130, 50, 150, 24);
        topbandPanel.add(classAllotButton);

        classAllotButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
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

                List subList = new ArrayList();
                frame.setVisible(false);
                new CreateSubject(sessionData, "", "", section, user_name, user_role, "");
            }
        });

        JButton marksButton = new JButton("Marks Entry");
        marksButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        marksButton.setBounds(450, 50, 150, 24);
        topbandPanel.add(marksButton);

        marksButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                List markList = new ArrayList();
                new MarksEntry(sessionData, "", markList, "", false, "", "", "", "", "", "",
                    "", "", "", section, user_name, user_role, "", "");
            }
        });

        JButton resultPrintButton = new JButton("Result Print");
        resultPrintButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        resultPrintButton.setBounds(610, 50, 150, 24); // 300, 50, 150, 24
        topbandPanel.add(resultPrintButton);

        resultPrintButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            	frame.setVisible(false);
                List findList = new ArrayList();
				new Result(sessionData, "", "", "", "", "", "",	findList, false, false, "", section, user_name, user_role, "", "", "", 
						findList, findList, findList);
            }
        });

        JButton attendanceButton = new JButton("Attendance");
		attendanceButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		attendanceButton.setBounds(770, 50, 150, 24); // 300, 50, 150, 24
		attendanceButton.setBackground(Color.GREEN);
		topbandPanel.add(attendanceButton);

		attendanceButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				/*List studentList = new ArrayList();
				new AttendanceUpdate(sessionData, "", "", "", section, user_name, user_role, studentList, "","","","",false,"",true);
				frame.setVisible(false);*/
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
		// /////////////ACADEMIV YEAR //////////////
		/*JLabel academic_label = new JLabel("Year :");
		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academic_label.setBounds(90, 00, 70, 50);
		findPanel.add(academic_label);

		String academicYearList[] = yearList.split(",");
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academicYear_combo.setBounds(160, 12, 120, 25);
		findPanel.add(academicYear_combo);*/

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
		date_radio.setBounds(60, 55, 20, 20);
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
		
     // /////////month radio///////////////30, 80, 120, 50
		final JRadioButton month_radio = new JRadioButton();
		month_radio.setBounds(550, 55, 20, 20);
//		findPanel.add(month_radio);
		
		final JLabel month_label = new JLabel("Month :");
		month_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		month_label.setBounds(580, 40, 120, 50);
//		findPanel.add(month_label);
		
		String monthList[] = monthlyList.split(",");
		final JComboBox month_combo = new JComboBox(monthList);
		month_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		month_combo.setBounds(650, 52, 120, 25);
//		findPanel.add(month_combo);
		
		 // /////////year radio///////////////30, 80, 120, 50
		/*final JRadioButton year_radio = new JRadioButton();
		year_radio.setBounds(790, 55, 20, 20);
		findPanel.add(year_radio);*/
		
		final JLabel year_label = new JLabel("Year :");
		year_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		year_label.setBounds(825, 40, 120, 50);
//		findPanel.add(year_label);
		
		String academicYearList[] = yearList.split(",");
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academicYear_combo.setBounds(875, 52, 120, 25);
//		findPanel.add(academicYear_combo);
		
		final JLabel exam_label = new JLabel("Exam :");
		exam_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		exam_label.setBounds(70, 40, 120, 50);
		
		String examList[] = examCategory.split(",");
		final JComboBox exam_combo = new JComboBox(examList);
//		exam_combo.setSelectedItem(examClass);
		exam_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		exam_combo.setBounds(140, 52, 120, 25);
		
		// ////////date_radio.addActionListener///////////
		date_radio.setSelected(true);
		month_combo.setSelectedItem("All");
		month_combo.setEnabled(false);
		academicYear_combo.setSelectedItem("All");
		academicYear_combo.setEnabled(false);
		month_radio.setSelected(false);
		
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
			findPanel.add(to_label);
			findPanel.add(datePickerTo);
			findPanel.remove(month_radio);
			findPanel.remove(month_label);
			findPanel.remove(month_combo);
			findPanel.remove(year_label);
			findPanel.remove(academicYear_combo);
			findPanel.revalidate();
			findPanel.repaint();
		} else if(catTypeClass.equalsIgnoreCase("Attendance Report")){
				from_label.setText("From :");
				findPanel.add(to_label);
				findPanel.add(datePickerTo);
				findPanel.add(month_radio);
				findPanel.add(month_label);
				findPanel.add(month_combo);
				findPanel.add(year_label);
				findPanel.add(academicYear_combo);
				findPanel.revalidate();
				findPanel.repaint();
		}
		
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

				if (month_radio.isSelected()) {
					date_radio.setSelected(false);
					month_combo.setEnabled(true);
					academicYear_combo.setEnabled(true);
					
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
					findPanel.revalidate();
					findPanel.repaint();
				} else if(catType.equalsIgnoreCase("Manual Attendance")){
					findPanel.remove(date_radio);
					findPanel.remove(from_label);
					findPanel.remove(datePickerFrom);
					findPanel.remove(to_label);
					findPanel.remove(datePickerTo);
					findPanel.remove(month_radio);
					findPanel.remove(month_label);
					findPanel.remove(month_combo);
					findPanel.add(year_label);
					findPanel.add(academicYear_combo);
					findPanel.add(exam_label);
					findPanel.add(exam_combo);
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

				std = (String) admittedStd_combo.getSelectedItem();
				div = (String) admittedDiv_combo.getSelectedItem();
				catType = (String) cat_combo.getSelectedItem();
				academicSel = (String) academicYear_combo.getSelectedItem();
				
				Date today = new Date();
				
		        Date selectedFromDate = (Date) datePickerFrom.getModel().getValue();
		        fromDate = commonObj.dateToDDMMYYYY(selectedFromDate);
		        
		        Date selectedToDate = (Date) datePickerTo.getModel().getValue();
		        toDate = commonObj.dateToDDMMYYYY(selectedToDate);
		        
		        if(date_radio.isSelected() && !catType.equalsIgnoreCase("Update Attendance")){
		        	today = selectedToDate;
		        }
		        else if(month_radio.isSelected()){
					month = (String) month_combo.getSelectedItem();
					year = (String) academicYear_combo.getSelectedItem();
				}
				/*else{
					fromDate = "";
					toDate = "";
					selectedFromDate = null;
					selectedToDate = null;
				}*/
		        
		        /*if(catType.equalsIgnoreCase("Mark Vacation")){
					today = selectedToDate;
				}*/
		        
				if (std.equalsIgnoreCase("Select")){
					commonObj.showMessageDialog("Please select std");
					validateFields = false;
				}
				else if (validateFields && div.equalsIgnoreCase("Select")){
					commonObj.showMessageDialog("Please select div");
					validateFields = false;
				}
				else if(commonObj.daysBetween(selectedFromDate, today) < 0 && !catType.equalsIgnoreCase("Update Attendance")){
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
				
				logger.info("std:" + std);
				logger.info("div:" + div);
				logger.info("catType:" + catType);
				logger.info("academicSel == " + academicSel);
				logger.info("validateFields == " + validateFields);
				
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
												fromDate,toDate,true,"",true,"", false);
									} else {
									////check for records present in attendance table
										recordsNotInAttendanceList = dbValidate.recordsFoundInAttendanceForDate(sessionData, std, div, academicSel, section, fromDate, "");
										if(recordsNotInAttendanceList.size() <= 0){
											commonObj.showMessageDialog("No data found.");
											frame.setVisible(false);
											new AttendanceUpdate(sessionData, std, div, academicSel, section, user_name, user_role, recordsNotInAttendanceList, catType,"",
													fromDate,toDate,false,"",true,"", false);
										}
										else{
											foundStudentList = recordsNotInAttendanceList;
											frame.setVisible(false);
											new AttendanceUpdate(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType,"",
													fromDate,toDate,false,"",false, "", false);
										}
									}
								}
								else{
									String exam = (String) exam_combo.getSelectedItem();
							    	if(exam.equalsIgnoreCase("Select")){
							    		JOptionPane.showMessageDialog(null, "Please select Exam.");
							    	}
							    	else{
							    		foundStudentList = dbValidate.findClassAteendanceList(sessionData, "", std, div, 
							    				"", "", "", academicSel, "", "", section, true, exam);
							    		frame.setVisible(false);
							    		new AttendanceUpdate(sessionData, std, div, academicSel, section, user_name, user_role, 
							    				foundStudentList, catType,"", fromDate,toDate,false,"",true, exam, false);
							    	}
								}
							} else{
								isMonthSelectedClass = month_radio.isSelected();
								if(isMonthSelectedClass){
									addOnTokensClass = 5;
									foundStudentList = dbValidate.attendanceReport(sessionData, academicSel, std, div, section, fromDate, toDate, month, year);
									
									if(foundStudentList.size() <= 0){
										commonObj.showMessageDialog("No data found.");
										frame.setVisible(false);
										new AttendanceReport(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType, month,fromDate,toDate,false,"", addOnTokensClass, isMonthSelectedClass);
									} else{
										frame.setVisible(false);
										new AttendanceReport(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType, month,fromDate,toDate,false,"", addOnTokensClass, isMonthSelectedClass);
									}
									
								} else{
									
									addOnTokensClass = commonObj.daysBetween(selectedFromDate, selectedToDate)+1;
									foundStudentList = dbValidate.attendanceStatus(sessionData, academicSel, std, div, section, fromDate, toDate, month, year, addOnTokensClass);
									
									if(foundStudentList.size() <= 0){
										commonObj.showMessageDialog("No data found.");
										frame.setVisible(false);
										new AttendanceReport(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType, month,fromDate,toDate,false,"", addOnTokensClass, isMonthSelectedClass);
									} else{
										frame.setVisible(false);
										new AttendanceReport(sessionData, std, div, academicSel, section, user_name, user_role, foundStudentList, catType, month,fromDate,toDate,false,"", addOnTokensClass, isMonthSelectedClass);
									}
								}
							}
						}
					} catch (Exception e1) {
						logger.info("Exception Strength ==>>>" + e1);
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
				new AttendanceReport(sessionData, "", "", "", section, user_name, user_role, studentList, "","","","",false,"", 0, isMonthSelectedClass);
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
				logger.info("foundStudentList size ==> " + retCount);
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
		logger.info("foundStudentList size in if ==>" + listSize);

		String[] dataArray = new String[listSize];
		dataArray = foundStudentList.toArray(dataArray);
		logger.info("listSize === " + listSize);
		logger.info("dataArray === " + dataArray.length);
		
//		String selectAllList[] = { "SELECT ALL", "PRESENT", "ABSENT", "HOLIDAY" };
//		final JComboBox selectAll_combo = new JComboBox(selectAllList);
//		selectAll_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//		selectAll_combo.setBounds(850, 8, 140, 25);
//		dataPanel.add(selectAll_combo);

		if (listSize > 0) {

			int j = 0;
			int l = 0;
			JLabel[] pipe_labels1 = new JLabel[listSize];
			JLabel[] line_labels = new JLabel[listSize];
			
			JLabel[] pipe_labels2 = new JLabel[listSize];
			JLabel[] pipe_labels3 = new JLabel[listSize];
			JLabel[] pipe_labels4 = new JLabel[listSize];
			JLabel[] pipe_labels5 = new JLabel[listSize];
			JLabel[] pipe_labels6 = new JLabel[listSize];
			JLabel[] pipe_labels7 = new JLabel[listSize];
			JLabel[] pipe_labels8 = new JLabel[listSize];
			JLabel[] pipe_labels9 = new JLabel[listSize];
			
			final JLabel[] temp1_labels = new JLabel[listSize];
			JLabel[] temp2_labels = new JLabel[listSize];
			JLabel[] temp3_labels = new JLabel[listSize];
			JLabel[] temp4_labels = new JLabel[listSize];
			JLabel[] temp5_labels = new JLabel[listSize];
			JLabel[] temp6_labels = new JLabel[listSize];
			JLabel[] temp7_labels = new JLabel[listSize];
			JLabel[] temp8_labels = new JLabel[listSize];
			
			String line1 = "---------------------------------------------------------------------------------------------------------";
			String line2 = "--------------";
			String line3 = "----------------";
//			JLabel lineabove_l1abels = new JLabel(line1);
			
			if(addOnTokensClass > 0){
				for(int n = 0; n < addOnTokensClass; n++){
					if(n == 2){
						line1 = line1 + line3;
					} else{
						line1 = line1 + line2;
					}
					
				}
			}
			
			int k = 0;
			for (int i = 0; i < listSize; i++) {
				j = j + 30;
				k = j;
				l = j + 50;
				int tokenSize = 0;
				int m = 0;

				logger.info(j + "====" + dataArray[i]);
				StringTokenizer st = new StringTokenizer(dataArray[i], "|");
				tokenSize = st.countTokens();
				String[] columnArray = new String[tokenSize];
				while (st.hasMoreTokens()) {
					columnArray[m] = st.nextToken();
					m++;
				}

				pipe_labels1[i] = new JLabel("|");
				pipe_labels1[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels1[i].setBounds(60, j, 120, 50);
				dataPanel.add(pipe_labels1[i]);

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
				String nameStr = columnArray[2];
				if(nameStr.contains("*auto")){
					nameStr = nameStr.substring(0,nameStr.indexOf("*"));
				}
				temp3_labels[i] = new JLabel(nameStr);
				temp3_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				temp3_labels[i].setBounds(240, j + 12, 390, 20);
				temp3_labels[i].setToolTipText(columnArray[2]);
				dataPanel.add(temp3_labels[i]);
				
				pipe_labels4[i] = new JLabel("|");
				pipe_labels4[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels4[i].setBounds(580, j, 120, 50);
				dataPanel.add(pipe_labels4[i]);

				//column 4
				if(tokenSize > 3){
					temp4_labels[i] = new JLabel(columnArray[3]);
					temp4_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					if(i == 0){
						temp4_labels[i].setBounds(590, j + 12, 190, 20);
					}else{
						temp4_labels[i].setBounds(610, j + 12, 190, 20);
					}
					
					temp4_labels[i].setToolTipText(columnArray[3]);
					dataPanel.add(temp4_labels[i]);
					
					pipe_labels5[i] = new JLabel("|");
					pipe_labels5[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels5[i].setBounds(650, j, 120, 50);
					dataPanel.add(pipe_labels5[i]);
				}
				
				//column 5
				if(tokenSize > 4){
					temp5_labels[i] = new JLabel(columnArray[4]);
					temp5_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					if(i == 0){
						temp5_labels[i].setBounds(660, j + 12, 130, 20);
					}else{
						temp5_labels[i].setBounds(680, j + 12, 130, 20);
					}
					temp5_labels[i].setToolTipText(columnArray[4]);
					dataPanel.add(temp5_labels[i]);
	
					pipe_labels6[i] = new JLabel("|");
					pipe_labels6[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels6[i].setBounds(720, j, 120, 50);
					dataPanel.add(pipe_labels6[i]);
				}
				
				//column 6
				if(tokenSize > 5){
					temp5_labels[i] = new JLabel(columnArray[5]);
					temp5_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					if(i == 0){
						temp5_labels[i].setBounds(730, j + 12, 130, 20);
					}else{
						temp5_labels[i].setBounds(750, j + 12, 130, 20);
					}
					temp5_labels[i].setToolTipText(columnArray[4]);
					dataPanel.add(temp5_labels[i]);
	
					pipe_labels6[i] = new JLabel("|");
					pipe_labels6[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels6[i].setBounds(800, j, 120, 50);
					dataPanel.add(pipe_labels6[i]);
				}
				
				//column 7
				if(tokenSize > 6){
					temp5_labels[i] = new JLabel(columnArray[6]);
					temp5_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					if(i == 0){
						temp5_labels[i].setBounds(810, j + 12, 130, 20);
					}else{
						temp5_labels[i].setBounds(830, j + 12, 130, 20);
					}
					temp5_labels[i].setToolTipText(columnArray[4]);
					dataPanel.add(temp5_labels[i]);
	
					pipe_labels6[i] = new JLabel("|");
					pipe_labels6[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels6[i].setBounds(870, j, 120, 50);
					dataPanel.add(pipe_labels6[i]);
				}
				
				//column 8
				if(tokenSize > 7){
					temp5_labels[i] = new JLabel(columnArray[7]);
					temp5_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					if(i == 0){
						temp5_labels[i].setBounds(890, j + 12, 130, 20);
					}else{
						temp5_labels[i].setBounds(900, j + 12, 130, 20);
					}
					temp5_labels[i].setToolTipText(columnArray[4]);
					dataPanel.add(temp5_labels[i]);
	
					pipe_labels6[i] = new JLabel("|");
					pipe_labels6[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels6[i].setBounds(940, j, 120, 50);
					dataPanel.add(pipe_labels6[i]);
				}
				
				//column 9
				if(tokenSize > 8){
					temp5_labels[i] = new JLabel(columnArray[8]);
					temp5_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					if(i == 0){
						temp5_labels[i].setBounds(960, j + 12, 130, 20);
					}else{
						temp5_labels[i].setBounds(980, j + 12, 130, 20);
					}
					temp5_labels[i].setToolTipText(columnArray[4]);
					dataPanel.add(temp5_labels[i]);
	
					pipe_labels6[i] = new JLabel("|");
					pipe_labels6[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels6[i].setBounds(1010, j, 120, 50);
					dataPanel.add(pipe_labels6[i]);
				}
				
				//column 10
				if(tokenSize > 9){
					temp5_labels[i] = new JLabel(columnArray[9]);
					temp5_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					if(i == 0){
						temp5_labels[i].setBounds(1030, j + 12, 130, 20);
					}else{
						temp5_labels[i].setBounds(1050, j + 12, 130, 20);
					}
					temp5_labels[i].setToolTipText(columnArray[4]);
					dataPanel.add(temp5_labels[i]);
	
					pipe_labels6[i] = new JLabel("|");
					pipe_labels6[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels6[i].setBounds(1080, j, 120, 50);
					dataPanel.add(pipe_labels6[i]);
				}
				line_labels[i] = new JLabel(line1);
				line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				line_labels[i].setBounds(60, j + 10, 1100, 50);
				dataPanel.add(line_labels[i]);
			}
			// /////////////Print//////////////
			JButton printButton = new JButton("Open in Excel");
			printButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			printButton.setBounds(mainCentre - 50, k + 60, 160, 25);
			dataPanel.add(printButton);

			printButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					boolean validateFields = true;
					String std = "";
					String div = "";
					String print = "PRINT";
					List<String> printAttendance = null;

					std = stdClass;
					div = divClass;

					if (std.equalsIgnoreCase("Select") || std.equalsIgnoreCase("All")) {
						std = "";
					}
					if (div.equalsIgnoreCase("Select") || div.equalsIgnoreCase("All")) {
						div = "";
					}

					try {
						if(dbValidate.connectDatabase(sessionData)){
							CreateExcel ce = new CreateExcel();
							if (category.equalsIgnoreCase("Attendance Report") && !isMonthSelectedClass) {
									ce.generateExcel(sessionData, "ATTENDANCE", "DAY_STATUS", "", foundStudentList, true, "DAY_STATUS   STD:"+stdClass+"   DIV:"+divClass+  "    Year:"+academicYearClass, 1);
							} else if (category.equalsIgnoreCase("Attendance Report") && isMonthSelectedClass && !monthClass.equalsIgnoreCase("ALL")) {
								List passFoundStudentList = new ArrayList();
								List studentListToPrint = new ArrayList();
								passFoundStudentList = foundStudentList;
								studentListToPrint = dbValidate.attendanceMonth(sessionData, academicYearClass, std, div, section, fromDateClass, toDateClass, monthClass, passFoundStudentList, addOnTokensClass);
								ce.generateExcel(sessionData, "ATTENDANCE", "DAY_STATUS", "", studentListToPrint, true, "Report for Month:"+monthClass+"    STD:"+stdClass+"   DIV:"+divClass+  "    Year:"+academicYearClass, 1);
							}else if (category.equalsIgnoreCase("Attendance Report") && isMonthSelectedClass && monthClass.equalsIgnoreCase("ALL")) {
								ce.generateExcel(sessionData, "ATTENDANCE", "DAY_STATUS", "", foundStudentList, true, "Report for Academic Year:"+academicYearClass+"    STD:"+stdClass+"   DIV:"+divClass+  "    Year:"+academicYearClass, 1);
							}
	
							int listSize = printAttendance.size();
							logger.info("No of students found :: " + listSize);
	
							String passStd = (String) admittedStd_combo.getSelectedItem();
							String passDiv = (String) admittedDiv_combo.getSelectedItem();
							String passYear = (String) academicYear_combo.getSelectedItem();
	
							if (listSize > 1 && !print.equalsIgnoreCase("PRINT")) {
								frame.setVisible(false);
//								new Strength(sessionData, passStd, passDiv, passYear, section, user_name, user_role, printList, catType);
								new PrintList(sessionData, passStd, passDiv, passYear, section, user_name, user_role, printAttendance, category, "");
							} else if (!print.equalsIgnoreCase("PRINT")) {
								JOptionPane.showMessageDialog(null, "No data found");
							}
						}
					} catch (Exception e1) {
						logger.info("Exception Attendance Report ===>>>" + e1);
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
