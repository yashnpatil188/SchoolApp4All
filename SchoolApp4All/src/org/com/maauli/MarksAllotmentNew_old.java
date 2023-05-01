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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
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
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class MarksAllotmentNew_old extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JFrame frame = null;

	static JComboBox studentcombo;

	static int retCount = 0;

	static int scrollHeight = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

	static TreeMap subjectListMap = new TreeMap();

	static List<String> subjectList = new ArrayList();

	static List<String> backupSubjectList = new ArrayList();

	static List<String> findSubList = new ArrayList();

	static boolean setSelected = false;

	static String stdClass = "";

	static String academicYearClass = "";

	static String category = "";

	static String selectStd = "";

	static String selectYear = "Year";

	static String divClass = "";

	static String user_name = "";

	static String user_role = "";

	static DBValidate dbValidate = new DBValidate();

	static SessionData sessionData = new SessionData();
	
	static UploadMarksAllotmentFromExcel uploadMarksAllotmentFromExcel = new UploadMarksAllotmentFromExcel();

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
	
	static List<String> updateMarksEntryList;

	static LinkedHashMap subjectMap = new LinkedHashMap();

	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static Logger logger = Logger.getLogger(MarksAllotmentNew_old.class.getName());

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
	static String semesterClass = "";

	public MarksAllotmentNew_old(SessionData sessionData1, String retStd, List<String> retSubjectList, String academicYear,
			String retCategory, boolean retSelected, String sec, String retUserName, String retUserRole, String semester) {

		System.gc();
		updateMarksEntryList = new ArrayList();
		sessionData = sessionData1;
		user_name = retUserName;
		semesterClass = semester;
		user_role = retUserRole;
		subjectListMap.clear();
		selectStd = "Select";
		section = sec;
		logger.info("section :: " + section);
		std = bundle.getString(section.toUpperCase() + "_STD");
		logger.info("std :: " + std);
		div = bundle.getString(section.toUpperCase() + "_DIV");
		logger.info("div :: " + div);
		secName = bundle.getString(section.toUpperCase() + "_SEC");
		logger.info("secName :: " + secName);
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
		app_header = bundle.getString("APP_HEADER_" + sessionData.getAppType());
		app_header_0 = bundle.getString("APP_HEADER_0_" + sessionData.getAppType());
		app_header_0_fontName = bundle.getString("APP_HEADER_0_FONTNAME_" + sessionData.getAppType());
		app_header_0_fontSize = Integer.parseInt(bundle.getString("APP_HEADER_0_FONTSIZE_" + sessionData.getAppType()));
		app_header_0_widthSpace = Integer.parseInt(bundle.getString("APP_HEADER_0_WIDTHSPACE_" + sessionData.getAppType()));
		app_header_0_heightSpace = Integer.parseInt(bundle.getString("APP_HEADER_0_HEIGHTSPACE_" + sessionData.getAppType()));
		app_header_fontName = bundle.getString("APP_HEADER_FONTNAME_" + sessionData.getAppType());
		app_header_fontSize = Integer.parseInt(bundle.getString("APP_HEADER_FONTSIZE_" + sessionData.getAppType()));
		app_header_widthSpace = Integer.parseInt(bundle.getString("APP_HEADER_WIDTHSPACE_" + sessionData.getAppType()));
		app_header_heightSpace = Integer.parseInt(bundle.getString("APP_HEADER_HEIGHTSPACE_" + sessionData.getAppType()));
		app_header_2 = bundle.getString("APP_HEADER_2_" + sessionData.getAppType());
		app_header_2_fontName = bundle.getString("APP_HEADER_2_FONTNAME_" + sessionData.getAppType());
		app_header_2_fontSize = Integer.parseInt(bundle.getString("APP_HEADER_2_FONTSIZE_" + sessionData.getAppType()));
		app_header_2_widthSpace = Integer.parseInt(bundle.getString("APP_HEADER_2_WIDTHSPACE_" + sessionData.getAppType()));
		app_header_2_heightSpace = Integer.parseInt(bundle.getString("APP_HEADER_2_HEIGHTSPACE_" + sessionData.getAppType()));

		String todayDate = commonObj.getCurrentDate();
		academicYearClass = commonObj.getAcademicYear(todayDate);
		logger.info("academicYearClass ==" + academicYearClass);

		if (!retStd.equalsIgnoreCase(""))
			selectStd = retStd;
		if (!academicYear.equalsIgnoreCase(""))
			selectYear = academicYear;

		try {
			subjectMap.clear();
			dbValidate.connectDatabase(sessionData1);
			subjectMap.putAll(dbValidate.findSubMaxMinList(sessionData, academicYear, retStd));

			subjectMap = dbValidate.findNewSubList(sessionData, academicYear, retStd, subjectMap);

			if (subjectMap.size() < 1 && !retStd.equalsIgnoreCase("")) {
				commonObj.showMessageDialog("No Subjects found for std " + retStd);
			}
		} catch (Exception e) {
			logger.info("Exception e = " + e);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}

		ArrayList<String> arrlist = null;
		arrlist = new ArrayList<String>(retSubjectList);
		logger.info("arrlist => " + arrlist.size());

		logger.info("retSubjectList size = " + retSubjectList.size());
		subjectList = arrlist;
		backupSubjectList = retSubjectList;

		logger.info("subjectList size = " + subjectList.size());

		stdClass = retStd;
		category = retCategory;
		logger.info(stdClass + "::" + academicYearClass + "::" + category + "::" + retSelected);

		entrytCnt = 0;
		logger.info("entrytCnt===>" + entrytCnt);

		int subjectSize = subjectMap.size();
		if (stdClass.equalsIgnoreCase("IX") || stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XI")
				|| stdClass.equalsIgnoreCase("XII")) {
			subjectSize = subjectSize * 2;
		}
		scrollHeight = (subjectMap.size() + 10) * 30; // to adjust the perfect scroll height
		if (scrollHeight < 0)
			scrollHeight = 0;

		setVisible(false);
		dispose();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame = new JFrame("Welcome to " + sessionData1.getAppName());
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
		title.setFont(new Font(app_header_fontName, Font.BOLD, app_header_fontSize));// 38 default
		title.setBounds((screenWidth / 2) - app_header_widthSpace, app_header_heightSpace, screenWidth - 100, 50);
		titlePanel.add(title);

		JLabel title_2 = new JLabel(app_header_2);
		title_2.setFont(new Font(app_header_2_fontName, Font.BOLD, app_header_2_fontSize));// 38 default
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
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role,
								section);
						dbValidate.closeDatabase(sessionData);

						frame.setVisible(false);
						String[] arguments = new String[] { "" };
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
		// leftPanel.add(staffButton);

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
				new Academic(sessionData, section, user_name, user_role);
			}
		});

		JButton accountButton = new JButton("Account");
		accountButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		accountButton.setBounds(10, 250, 130, 35);
		// leftPanel.add(accountButton);

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
		subAllotButton.setBackground(Color.GREEN);
		topbandPanel.add(subAllotButton);

		subAllotButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
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

		// /////////////////bottombandPanel area//////////////////////////
		JPanel bottombandPanel = new JPanel(new BorderLayout());
		bottombandPanel.setPreferredSize(new Dimension(screenWidth - 150, screenHeight - 187));

		// ///////////find Panel/////////////
		final JPanel findPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				Image img = new ImageIcon(img_path + img_mainband).getImage();
				Dimension size = new Dimension(screenWidth - 156, 150);// change height to change scrolling area
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
		JLabel academic_label = new JLabel("Academic Year :");
		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academic_label.setBounds(mainCentre - 340, 00, 150, 50);
		findPanel.add(academic_label);

		String academicYearList[] = { academicYearClass };
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academicYear_combo.setBounds(mainCentre - 200, 12, 120, 25);
		findPanel.add(academicYear_combo);
		// //////////////////////////////////
		// /////////////Std//////////////
		JLabel admittedStd_label = new JLabel("Std :");
		admittedStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_label.setBounds(mainCentre - 50, 00, 70, 50);
		findPanel.add(admittedStd_label);

		std = selectStd + "," + std;
		String[] stdList = std.split(",");
		final JComboBox admittedStd_combo = new JComboBox(stdList);
		admittedStd_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_combo.setBounds(mainCentre, 12, 90, 25);
		findPanel.add(admittedStd_combo);

		// /////////////Div//////////////
		JLabel presentDiv_label = new JLabel("Div :");
		presentDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		presentDiv_label.setBounds(mainCentre + 120, 00, 70, 50);
		findPanel.add(presentDiv_label);

		div = "Select," + div;
		String[] divList = div.split(",");
		// String presentDivList[] = {"Select","A","B","C","D","E"};
		final JComboBox presentDiv_combo = new JComboBox(divList);
		presentDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		presentDiv_combo.setBounds(mainCentre + 170, 12, 90, 25);
		findPanel.add(presentDiv_combo);
		
		// /////////////Semester//////////////
		final JLabel semester_label = new JLabel("Semester :");
		semester_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		semester_label.setBounds(mainCentre + 290, 00, 200, 50);

		String semesterList[] = {"Semester 1","Semester 2"};
		final JComboBox semester_combo = new JComboBox(semesterList);
		semester_combo.setSelectedItem(semesterClass);
		semester_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		semester_combo.setBounds(mainCentre + 370, 12, 120, 25);
		if(!semesterClass.equalsIgnoreCase("")){
			int stdInt = commonObj.RomanToInteger(stdClass);
			if(stdInt < 9){
	        	findPanel.add(semester_label);
	        	findPanel.add(semester_combo);
	        }
		}

		// /////////////Create Subject//////////////
		final JRadioButton createSubject_radio = new JRadioButton();
		createSubject_radio.setBounds(mainCentre - 350, 55, 20, 20);
		findPanel.add(createSubject_radio);

		final JLabel createSubject_label = new JLabel("Create Subject");
		createSubject_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		createSubject_label.setBounds(mainCentre - 320, 40, 150, 50);
		findPanel.add(createSubject_label);

		// /////////////Subject Allotment//////////////
		final JRadioButton allotSubject_radio = new JRadioButton();
		allotSubject_radio.setBounds(mainCentre - 160, 55, 20, 20);
		findPanel.add(allotSubject_radio);

		final JLabel allotSubject_label = new JLabel("Marks Allotment");
		allotSubject_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		allotSubject_label.setBounds(mainCentre - 130, 40, 200, 50);
		findPanel.add(allotSubject_label);

		// /////////////Student Subject Allotment//////////////
		final JRadioButton studentSubject_radio = new JRadioButton();
		studentSubject_radio.setBounds(mainCentre + 70, 55, 20, 20);
		findPanel.add(studentSubject_radio);

		final JLabel studentSubject_label = new JLabel("Student Subject Allotment");
		studentSubject_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		studentSubject_label.setBounds(mainCentre + 100, 40, 200, 50);
		findPanel.add(studentSubject_label);

		admittedStd_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					String stdSel = (String) admittedStd_combo.getSelectedItem();
					String acadSel = (String) academicYear_combo.getSelectedItem();
					boolean marksAllotRadio = allotSubject_radio.isSelected();
					if (!stdSel.equalsIgnoreCase("All") && !stdSel.equalsIgnoreCase("Select")
							&& dbValidate.connectDatabase(sessionData)) {
						presentDiv_combo.removeAllItems();
						presentDiv_combo.addItem("Select");
						String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdSel, section, "PRESENT_DIV",
								"PRESENT_STD", "class_allotment", acadSel);

						for (String retval : divAvailabe.split(",")) {
							presentDiv_combo.addItem(retval);
						}
					}
					int stdInt = commonObj.RomanToInteger(stdSel);
					if(stdInt < 9 && marksAllotRadio){
                    	findPanel.add(semester_label);
                    	findPanel.add(semester_combo);
                    }
					else{
						findPanel.remove(semester_label);
                    	findPanel.remove(semester_combo);
					}
					findPanel.revalidate();
					findPanel.repaint();
				} catch (Exception e1) {
					logger.info("Exception insertFormData ===>>>" + e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});

		createSubject_radio.setSelected(false);
		allotSubject_radio.setSelected(true);
		studentSubject_radio.setSelected(false);
		presentDiv_combo.setEnabled(false);

		createSubject_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (createSubject_radio.isSelected()) {
					createSubject_radio.setSelected(true);
					allotSubject_radio.setSelected(false);
					studentSubject_radio.setSelected(false);
					presentDiv_combo.setEnabled(false);
					findPanel.remove(semester_label);
                    findPanel.remove(semester_combo);
					category = "Create Subject";
				} else {
					category = "";
				}
				findPanel.revalidate();
				findPanel.repaint();
			}
		});

		allotSubject_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int stdInt = commonObj.RomanToInteger(admittedStd_combo.getSelectedItem().toString());
				
				if (allotSubject_radio.isSelected()) {
					createSubject_radio.setSelected(false);
					allotSubject_radio.setSelected(true);
					studentSubject_radio.setSelected(false);
					presentDiv_combo.setEnabled(false);
					if(stdInt < 9){
                    	findPanel.add(semester_label);
                    	findPanel.add(semester_combo);
                    }
					category = "Allot Subject";
				} else {
					category = "";
				}
				findPanel.revalidate();
    			findPanel.repaint();
			}
		});

		studentSubject_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (studentSubject_radio.isSelected()) {
					createSubject_radio.setSelected(false);
					allotSubject_radio.setSelected(false);
					studentSubject_radio.setSelected(true);
					presentDiv_combo.setEnabled(true);
					findPanel.remove(semester_label);
                    findPanel.remove(semester_combo);
					category = "Student Subject Allotment";
				} else {
					category = "";
				}
				findPanel.revalidate();
    			findPanel.repaint();
			}
		});
		// /////////////Submit//////////////
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(mainCentre - 250, 90, 130, 25);
		findPanel.add(submitButton);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean validateFields = true;
				String std = "";
				String academicSel = "";
				String div = "";
				String semester = "";

				std = (String) admittedStd_combo.getSelectedItem();
				int stdInt = commonObj.RomanToInteger(std);
				academicSel = (String) academicYear_combo.getSelectedItem();
				div = (String) presentDiv_combo.getSelectedItem();
				if(stdInt < 9){
					semester = (String) semester_combo.getSelectedItem();
				}

				logger.info("std:" + std);
				logger.info("academicSel:" + academicSel);
				logger.info("div:" + div);

				if (createSubject_radio.isSelected()) {
					category = "Create Subject";
				} else if (allotSubject_radio.isSelected()) {
					category = "Allot Subject";
				} else if (studentSubject_radio.isSelected()) {
					category = "Student Subject Allotment";
				}
				logger.info("category:" + category);

				if (academicSel.equalsIgnoreCase("Year")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Academic year.");
				} else if (std.equalsIgnoreCase("Select")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Std.");
				} else if (category.equalsIgnoreCase("Student Subject Allotment") && div.equalsIgnoreCase("Select")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Div");
				} else if (category.equalsIgnoreCase("")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Select a radio option.");
				}

				if (!academicSel.equalsIgnoreCase("Year")) {
					academicSel = academicSel;
				} else {
					academicSel = "";
				}
				logger.info("std == " + std);
				logger.info("academicSel == " + academicSel);
				logger.info("validateFields flag..." + validateFields);
				if (validateFields & category.equalsIgnoreCase("Create Subject")) {
					try {
						List subList = new ArrayList();

						frame.setVisible(false);
						new CreateSubject(sessionData, category, std, section, user_name, user_role, "");

					} catch (Exception e1) {
						logger.info("Exception e1 ===>>>" + e1);
						new Student(sessionData, section, user_name, user_role);
						frame.setVisible(false);
					}
				} else if (validateFields & category.equalsIgnoreCase("Allot Subject")) {
					try {
						category = category;
						List subList = new ArrayList();

						frame.setVisible(false);
						if(stdInt >= 9){
							new MarksAllotment(sessionData, std, subList, academicYearClass, category, false, section,
									user_name, user_role, semester);
						} else{
							new MarksAllotmentNew(sessionData, std, subList, academicYearClass, category, false, section,
									user_name, user_role, semester);
						}

					} catch (Exception e1) {
						logger.info("Exception e1 ===>>>" + e1);
						new Student(sessionData, section, user_name, user_role);
						frame.setVisible(false);
					}
				} else if (validateFields & category.equalsIgnoreCase("Student Subject Allotment")) {
					try {
						divClass = div;
						List subList = new ArrayList();

						frame.setVisible(false);
						new StudentSubAllot(sessionData, std, subList, academicYearClass, category, false, divClass,
								section, "", "", "", user_name, user_role);

					} catch (Exception e1) {
						logger.info("Exception e1 ===>>>" + e1);
						new Student(sessionData, section, user_name, user_role);
						frame.setVisible(false);
					}
				}
			}

		});
		// /////////////Reset//////////////
		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		resetButton.setBounds(mainCentre - 75, 90, 130, 25);
		findPanel.add(resetButton);

		resetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				logger.info("Cliked Reset in findLeaving cert");
				List subAllotList = new ArrayList();
				frame.setVisible(false);
				new MarksAllotmentNew(sessionData, "", subAllotList, "", "", false, section, user_name, user_role, "");
			}
		});

		// /////////////Download Template//////////////
		JButton templateButton = new JButton("Download");
		templateButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		templateButton.setBounds(mainCentre + 100, 90, 130, 25);
		findPanel.add(templateButton);

		templateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int reply = 0;
					boolean validateFields = true;
					String academicSel = "";
					String std = "";
					String messageDisplay = "";

					academicSel = (String) academicYear_combo.getSelectedItem() == null ? "" : (String) academicYear_combo.getSelectedItem();
					std = (String) admittedStd_combo.getSelectedItem() == null ? "" : (String) admittedStd_combo.getSelectedItem();

					logger.info("std:" + std);
					logger.info("academicSel:" + academicSel);

					if (academicSel.equalsIgnoreCase("Year") || academicSel.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std to download template.");
					} else if (std.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std to download template.");
					}

					if (validateFields) {
						reply = JOptionPane.showConfirmDialog(null,
								"Would You Like to download template for Marks Allotment std  " + std + " ?",
								"Confirm download", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							String templatePath = commonObj.getDriveName()
									+ bundle.getString("TEMPLATE_PATH_" + sessionData.getDBName())
									+ commonObj.getCurrentDatein_dd_MMM_yyyy();
							if (dbValidate.connectDatabase(sessionData)) {
								JFrame f = new JFrame("Marks Allotment Template downlaod in progress. Don't Close");
								f.setBounds(screenWidth / 2 - 150, screenHeight / 2, 90, 25);
								f.setSize(400, 0);
								f.setResizable(false);
								f.setVisible(true);
								f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

								List<String> subjectList = dbValidate.fetchSubjectList(sessionData, std, academicSel);
								List<String> marksAllotmentList = new ArrayList<String>();
								String subjectName = "";
								String examType = "";
								String convertTo = "";
								int stdInt = commonObj.RomanToInteger(std);
								if (stdInt == 9) {
									marksAllotmentList.add("SEMESTER| |I|I|I|I|I|I|II|II|II|II|II|II");
									examType = "SUBJECT| |FUT|PRES|MCAP|WRITE|PRACT|ACT|SUT|PRES|MCAP|WRITE|PRACT|ACT";
									marksAllotmentList.add(examType);
									examType = "|Max| | | | | | | | | | | | ";
									convertTo = " |Convert To| | | | | | | | | | | | ";
								}
								else if (stdInt < 9) {
									marksAllotmentList.add("SEMESTER| |F|F|F|F|F|F|F|F|S|S|S");
									examType = "SUBJECT| |DOBS|TEST|ORAL|ASSIGN|PROJECT|PRACT|ACT|OTHER|ORAL|PRACT|WRITE";
									marksAllotmentList.add(examType);
									examType = "|Max| | | | | | | | | | | ";
								}
								else{
									marksAllotmentList.add("SEMESTER| |I|I|I|I|I|I|I|II|II|II|II|II|II|II");
									examType = "SUBJECT| |DOBS|TEST|ORAL|ASSIGN|WRITE|PROJ|ACT|DOBS|TEST|ORAL|ASSIGN|WRITE|PROJ|ACT";
									marksAllotmentList.add(examType);
									examType = "|Max| | | | | | | | | | | | ";
								}
								for (int i = 0; i < subjectList.size(); i++) {
									subjectName = subjectList.get(i).substring(0, subjectList.get(i).indexOf("|")) + examType;
									marksAllotmentList.add(subjectName);
									if (stdInt > 8) {
										marksAllotmentList.add(convertTo);
									}
								}

								CreateExcelInXLSX ceXlsx = new CreateExcelInXLSX();
								ceXlsx.generateExcel(sessionData, "SUBJECT", "MARKS_ALLOTMENT_"+std+"_", "", marksAllotmentList,
										true, secName + " Marks Allotment  STD:" + std + "  " + academicSel, 1);
								f.setVisible(false);
								commonObj.showMessageDialog("Template downloaded to " + templatePath);
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
		JButton uploadButton = new JButton("Upload");
		uploadButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		uploadButton.setBounds(mainCentre + 250, 90, 130, 25);
		findPanel.add(uploadButton);

		uploadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean validate = true;
					String stdSel = admittedStd_combo.getSelectedItem().toString();
					String academicSel = academicYear_combo.getSelectedItem().toString();
					String semester = semester_combo.getSelectedItem().toString();
					
					if(academicSel.equalsIgnoreCase("Select") || academicSel.equalsIgnoreCase("")){
						commonObj.showMessageDialog("Please select Academic Year, Std to upload excel");
						validate = false;
					}
					else if(stdSel.equalsIgnoreCase("Select") || stdSel.equalsIgnoreCase("")){
						commonObj.showMessageDialog("Please select Academic Year, Std to upload excel");
						validate = false;
					}
					
					if(validate){
						String default_path = commonObj.getDriveName() + bundle.getString("TEMPLATE_PATH_"+sessionData.getDBName());
						JFileChooser fileChooser = new JFileChooser(default_path);
						int returnValue = fileChooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
							
							int reply = 0;
							reply = JOptionPane.showConfirmDialog(null, semester +" : Would You Like to upload "+absolutePath+"?", "Confirm", JOptionPane.YES_NO_OPTION);
							
							if (reply == JOptionPane.YES_OPTION) {
								try {
									/*JFrame f = new JFrame("Marks upload in progress. Please wait & Don't Close");
									f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
								    f.setSize(500, 0);
								    f.setResizable(false);
								    f.setVisible(true);
								    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
								    String messageDisplay = "";
								    uploadMarksAllotmentFromExcel.uploadMaxMarksFromExcel(sessionData, absolutePath, academicSel, stdSel, semester);
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
		scrollAreaPanel.setPreferredSize(new Dimension(screenWidth - 150, screenHeight - 337));

		// ///////////Data Panel/////////////
		final JPanel dataPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				Image img = new ImageIcon(img_path + img_mainband).getImage();
				Dimension size = new Dimension(screenWidth - 158, ((screenHeight - 330) + scrollHeight));// change
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
				g.drawImage(img, 0, 0, screenWidth - 158, (screenHeight - 330) + scrollHeight, null);
			}
		};
		dataPanel.setLayout(null);
		int listSize = subjectMap.size();
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
			final JRadioButton all_radio = new JRadioButton();
			all_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			all_radio.setBounds(40, 13, 20, 20);
			all_radio.setSelected(true);
			all_radio.setEnabled(false);
			dataPanel.add(all_radio);

			JLabel std_label = new JLabel("STD.: " + stdClass);
			std_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			std_label.setBounds(70, 00, 120, 50);
			dataPanel.add(std_label);

			JLabel pipe_label2 = new JLabel("|");
			pipe_label2.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label2.setBounds(250, 00, 120, 50);
			dataPanel.add(pipe_label2);

			JLabel sem1_label = new JLabel("FORMATIVE EVALUATION (A)");
			sem1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sem1_label.setBounds(startWidth + 100, 00, 500, 50);
			dataPanel.add(sem1_label);

			JLabel pipe_label5 = new JLabel("|");
			pipe_label5.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label5.setBounds(startWidth + 470, 00, 120, 50);
			dataPanel.add(pipe_label5);

			JLabel summative_label = new JLabel("SUMMATIVE EVALUATION (B)");
			summative_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			summative_label.setBounds(startWidth + 490, 00, 500, 50);
			dataPanel.add(summative_label);

			JLabel pipe_label12 = new JLabel("|");
			pipe_label12.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label12.setBounds(1010, 00, 120, 50);
			dataPanel.add(pipe_label12);

			JLabel line_label = new JLabel(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			line_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			line_label.setBounds(30, 13, 1100, 50);
			dataPanel.add(line_label);

			JLabel sub_label = new JLabel("SUBJECT");
			sub_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sub_label.setBounds(60, 30, 150, 50);
			dataPanel.add(sub_label);

			JLabel pipe_label7 = new JLabel("|");
			pipe_label7.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label7.setBounds(250, 30, 120, 50);
			dataPanel.add(pipe_label7);

			JLabel sem1DailyObs_label = new JLabel("DOBS");
			sem1DailyObs_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			sem1DailyObs_label.setBounds(startWidth, 30, 160, 50);
			dataPanel.add(sem1DailyObs_label);
			
			JLabel sem1Oral_label = new JLabel("ORAL");
			sem1Oral_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			sem1Oral_label.setBounds(startWidth + 60, 30, 160, 50);
			dataPanel.add(sem1Oral_label);
			
			JLabel sem1Pract_label = new JLabel("PRACT.");
			sem1Pract_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			sem1Pract_label.setBounds(startWidth + 120, 30, 160, 50);
			dataPanel.add(sem1Pract_label);
			
			JLabel sem1Act_label = new JLabel("ACT");
			sem1Act_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			sem1Act_label.setBounds(startWidth + 185, 30, 160, 50);
			dataPanel.add(sem1Act_label);
			
			JLabel sem1Proj_label = new JLabel("PROJ");
			sem1Proj_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			sem1Proj_label.setBounds(startWidth + 240, 30, 160, 50);
			dataPanel.add(sem1Proj_label);
			
			JLabel sem1Obt_label = new JLabel("TEST");
			sem1Obt_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			sem1Obt_label.setBounds(startWidth + 300, 30, 160, 50);
			dataPanel.add(sem1Obt_label);

			JLabel sem1Assign_label = new JLabel("ASSIGN");
			sem1Assign_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			sem1Assign_label.setBounds(startWidth + 350, 30, 160, 50);
			dataPanel.add(sem1Assign_label);

			JLabel sem1Other_label = new JLabel("OTHER");
			sem1Other_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			sem1Other_label.setBounds(startWidth + 420, 30, 160, 50);
			dataPanel.add(sem1Other_label);

			JLabel pipe_label9 = new JLabel("|");
			pipe_label9.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label9.setBounds(startWidth + 470, 30, 120, 50);
			dataPanel.add(pipe_label9);

			JLabel sem1Oral1_label = new JLabel("ORAL");
			sem1Oral1_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			sem1Oral1_label.setBounds(startWidth + 500, 30, 160, 50);
			dataPanel.add(sem1Oral1_label);
			
			JLabel sem1Pract1_label = new JLabel("PRACT.");
			sem1Pract1_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			sem1Pract1_label.setBounds(startWidth + 560, 30, 160, 50);
			dataPanel.add(sem1Pract1_label);
			
			JLabel sem1Write_label = new JLabel("WRITE");
			sem1Write_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			sem1Write_label.setBounds(startWidth + 620, 30, 160, 50);
			dataPanel.add(sem1Write_label);

			JLabel pipe_label11 = new JLabel("|");
			pipe_label11.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label11.setBounds(1010, 30, 120, 50);
			dataPanel.add(pipe_label11);

			JLabel maxLine_label = new JLabel(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			maxLine_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			maxLine_label.setBounds(30, 43, 1100, 50);
			dataPanel.add(maxLine_label);

			String[] subjectArray = new String[listSize];
			subjectArray = subjectList.toArray(subjectArray);
			logger.info("listSize === " + listSize);
			logger.info("subjectArray === " + subjectArray.length);
			listSize = subjectArray.length;
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
				final JTextField[] sem1DailyObs_text = new JTextField[listSize];
				final JTextField[] sem1Obt_text = new JTextField[listSize];
				final JTextField[] sem1Oral_text = new JTextField[listSize];
				final JTextField[] sem1Assign_text = new JTextField[listSize];
				final JTextField[] sem1Pres_text = new JTextField[listSize];
				final JTextField[] sem1Mcap_text = new JTextField[listSize];
				final JTextField[] sem1Write_text = new JTextField[listSize];
				final JTextField[] sem1Pract_text = new JTextField[listSize];
				final JTextField[] sem1Act_text = new JTextField[listSize];
				final JTextField[] sem1Project_text = new JTextField[listSize];
				final JTextField[] sem1Other_text = new JTextField[listSize];
				final JTextField[] sem1Oral1_text = new JTextField[listSize];
				final JTextField[] sem1Write1_text = new JTextField[listSize];
				final JTextField[] sem1Pract1_text = new JTextField[listSize];
				
				final JTextField[] sem2DailyObs_text = new JTextField[listSize];
				final JTextField[] sem2Obt_text = new JTextField[listSize];
				final JTextField[] sem2Oral_text = new JTextField[listSize];
				final JTextField[] sem2Assign_text = new JTextField[listSize];
				final JTextField[] sem2Pres_text = new JTextField[listSize];
				final JTextField[] sem2Mcap_text = new JTextField[listSize];
				final JTextField[] sem2Write_text = new JTextField[listSize];
				final JTextField[] sem2Pract_text = new JTextField[listSize];
				final JTextField[] sem2Act_text = new JTextField[listSize];
				final JTextField[] sem2Project_text = new JTextField[listSize];
				final JTextField[] sem2Other_text = new JTextField[listSize];
				final JTextField[] sem2Oral1_text = new JTextField[listSize];
				final JTextField[] sem2Write1_text = new JTextField[listSize];
				final JTextField[] sem2Pract1_text = new JTextField[listSize];
				
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

				Set set = subjectMap.entrySet();
				// Get an iterator
				Iterator m = set.iterator();

				j = 30;
				int i = 0;
				while (m.hasNext()) {
					j = j + 30;
					l = j + 50;

					Map.Entry me = (Map.Entry) m.next();
					LinkedHashMap subjectMaxMap = new LinkedHashMap();
					subjectMaxMap = (LinkedHashMap) me.getValue();

					String std = stdClass;
					String subject = subjectMaxMap.get("subject_name") == null ? "0" : subjectMaxMap.get("subject_name").toString();
					String subTitle = subjectMaxMap.get("subject_title") == null ? "0" : subjectMaxMap.get("subject_title").toString();
					String marks_grade = subjectMaxMap.get("marks_grade") == null ? "0": subjectMaxMap.get("marks_grade").toString();
					String optional = subjectMaxMap.get("optional") == null ? "0" : subjectMaxMap.get("optional").toString();
					
					String sem1Dobs = subjectMaxMap.get("sem1_dobs") == null ? "0" : subjectMaxMap.get("sem1_dobs").toString();
					String sem1Obt = subjectMaxMap.get("sem1_obt") == null ? "0" : subjectMaxMap.get("sem1_obt").toString();
					String sem1Oral = subjectMaxMap.get("sem1_oral") == null ? "0" : subjectMaxMap.get("sem1_oral").toString();
					String sem1Pres = subjectMaxMap.get("sem1_pres") == null ? "0" : subjectMaxMap.get("sem1_pres").toString();
					String sem1Assign = subjectMaxMap.get("sem1_assign") == null ? "0" : subjectMaxMap.get("sem1_assign").toString();
					String sem1Mcap = subjectMaxMap.get("sem1_mcap") == null ? "0" : subjectMaxMap.get("sem1_mcap").toString();
					String sem1Write = subjectMaxMap.get("sem1_write") == null ? "0" : subjectMaxMap.get("sem1_write").toString();
					String sem1Pract = subjectMaxMap.get("sem1_pract") == null ? "0" : subjectMaxMap.get("sem1_pract").toString();
					String sem1Act = subjectMaxMap.get("sem1_act") == null ? "0" : subjectMaxMap.get("sem1_act").toString();
					String sem1Project = subjectMaxMap.get("sem1_project") == null ? "0" : subjectMaxMap.get("sem1_project").toString();
					String sem1Other = subjectMaxMap.get("sem1_other") == null ? "0" : subjectMaxMap.get("sem1_other").toString();
					String sem1Oral1 = subjectMaxMap.get("sem1_oral1") == null ? "0" : subjectMaxMap.get("sem1_oral1").toString();
					String sem1Write1 = subjectMaxMap.get("sem1_write1") == null ? "0" : subjectMaxMap.get("sem1_write1").toString();
					String sem1Pract1 = subjectMaxMap.get("sem1_pract1") == null ? "0" : subjectMaxMap.get("sem1_pract1").toString();
					
					String sem2Dobs = subjectMaxMap.get("sem2_dobs") == null ? "0" : subjectMaxMap.get("sem2_dobs").toString();
					String sem2Obt = subjectMaxMap.get("sem2_obt") == null ? "0" : subjectMaxMap.get("sem2_obt").toString();
					String sem2Oral = subjectMaxMap.get("sem2_oral") == null ? "0" : subjectMaxMap.get("sem2_oral").toString();
					String sem2Pres = subjectMaxMap.get("sem2_pres") == null ? "0" : subjectMaxMap.get("sem2_pres").toString();
					String sem2Assign = subjectMaxMap.get("sem2_assign") == null ? "0" : subjectMaxMap.get("sem2_assign").toString();
					String sem2Mcap = subjectMaxMap.get("sem2_mcap") == null ? "0" : subjectMaxMap.get("sem2_mcap").toString();
					String sem2Write = subjectMaxMap.get("sem2_write") == null ? "0" : subjectMaxMap.get("sem2_write").toString();
					String sem2Pract = subjectMaxMap.get("sem2_pract") == null ? "0" : subjectMaxMap.get("sem2_pract").toString();
					String sem2Act = subjectMaxMap.get("sem2_act") == null ? "0" : subjectMaxMap.get("sem2_act").toString();
					String sem2Project = subjectMaxMap.get("sem2_project") == null ? "0" : subjectMaxMap.get("sem2_project").toString();
					String sem2Other = subjectMaxMap.get("sem2_other") == null ? "0" : subjectMaxMap.get("sem2_other").toString();
					String sem2Oral1 = subjectMaxMap.get("sem2_oral1") == null ? "0" : subjectMaxMap.get("sem2_oral1").toString();
					String sem2Write1 = subjectMaxMap.get("sem2_write1") == null ? "0" : subjectMaxMap.get("sem2_write1").toString();
					String sem2Pract1 = subjectMaxMap.get("sem2_pract1") == null ? "0" : subjectMaxMap.get("sem2_pract1").toString();
					
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

					max_label[i] = new JLabel("Max.");
					max_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 10));
					max_label[i].setBounds(220, j + 12, 200, 20);
					dataPanel.add(max_label[i]);

					pipe_labels2[i] = new JLabel("|");
					pipe_labels2[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels2[i].setBounds(250, j, 120, 50);
					dataPanel.add(pipe_labels2[i]);

					sem1DailyObs_text[i] = new JTextField(sem1Dobs);
					sem1DailyObs_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem1DailyObs_text[i].setBounds(startWidth, j + 12, 40, 20);

					sem1Oral_text[i] = new JTextField(sem1Oral);
					sem1Oral_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem1Oral_text[i].setBounds(startWidth + 60, j + 12, 40, 20);
					
					sem1Pract_text[i] = new JTextField(sem1Pract);
					sem1Pract_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem1Pract_text[i].setBounds(startWidth + 120, j + 12, 40, 20);
					
					sem1Act_text[i] = new JTextField(sem1Act);
					sem1Act_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem1Act_text[i].setBounds(startWidth + 180, j + 12, 40, 20);
					
					sem1Project_text[i] = new JTextField(sem1Project);
					sem1Project_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem1Project_text[i].setBounds(startWidth + 240, j + 12, 40, 20);
					
					sem1Obt_text[i] = new JTextField(sem1Obt);
					sem1Obt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem1Obt_text[i].setBounds(startWidth + 300, j + 12, 40, 20);

					sem1Assign_text[i] = new JTextField(sem1Assign);
					sem1Assign_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem1Assign_text[i].setBounds(startWidth + 360, j + 12, 40, 20);
					
					sem1Write_text[i] = new JTextField(sem1Write);
					sem1Write_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem1Write_text[i].setBounds(startWidth + 360, j + 12, 40, 20);
					
					sem1Pres_text[i] = new JTextField(sem1Pres);
					sem1Pres_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem1Pres_text[i].setBounds(startWidth + 360, j + 12, 40, 20);
					
					sem1Mcap_text[i] = new JTextField(sem1Mcap);
					sem1Mcap_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem1Mcap_text[i].setBounds(startWidth + 360, j + 12, 40, 20);
					
					sem1Other_text[i] = new JTextField(sem1Other);
					sem1Other_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem1Other_text[i].setBounds(startWidth + 420, j + 12, 40, 20);
					
					pipe_labels4[i] = new JLabel("|");
					pipe_labels4[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels4[i].setBounds(startWidth + 470, j, 40, 50);

					sem1Oral1_text[i] = new JTextField(sem1Oral1);
					sem1Oral1_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem1Oral1_text[i].setBounds(startWidth + 500, j + 12, 40, 20);
					
					sem1Pract1_text[i] = new JTextField(sem1Pract1);
					sem1Pract1_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem1Pract1_text[i].setBounds(startWidth + 560, j + 12, 40, 20);
					
					sem1Write1_text[i] = new JTextField(sem1Write1);
					sem1Write1_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem1Write1_text[i].setBounds(startWidth + 620, j + 12, 40, 20);
					
					//////semester 2 data////////////////
					sem2DailyObs_text[i] = new JTextField(sem2Dobs);
					sem2DailyObs_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem2DailyObs_text[i].setBounds(startWidth, j + 12, 40, 20);

					sem2Oral_text[i] = new JTextField(sem2Oral);
					sem2Oral_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem2Oral_text[i].setBounds(startWidth + 60, j + 12, 40, 20);
					
					sem2Pract_text[i] = new JTextField(sem2Pract);
					sem2Pract_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem2Pract_text[i].setBounds(startWidth + 120, j + 12, 40, 20);
					
					sem2Act_text[i] = new JTextField(sem2Act);
					sem2Act_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem2Act_text[i].setBounds(startWidth + 180, j + 12, 40, 20);
					
					sem2Project_text[i] = new JTextField(sem2Project);
					sem2Project_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem2Project_text[i].setBounds(startWidth + 240, j + 12, 40, 20);
					
					sem2Obt_text[i] = new JTextField(sem2Obt);
					sem2Obt_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem2Obt_text[i].setBounds(startWidth + 300, j + 12, 40, 20);

					sem2Assign_text[i] = new JTextField(sem2Assign);
					sem2Assign_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem2Assign_text[i].setBounds(startWidth + 360, j + 12, 40, 20);
					
					sem2Write_text[i] = new JTextField(sem2Write);
					sem2Write_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem2Write_text[i].setBounds(startWidth + 360, j + 12, 40, 20);
					
					sem2Pres_text[i] = new JTextField(sem2Pres);
					sem2Pres_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem2Pres_text[i].setBounds(startWidth + 360, j + 12, 40, 20);
					
					sem2Mcap_text[i] = new JTextField(sem2Mcap);
					sem2Mcap_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem2Mcap_text[i].setBounds(startWidth + 360, j + 12, 40, 20);
					
					sem2Other_text[i] = new JTextField(sem2Other);
					sem2Other_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem2Other_text[i].setBounds(startWidth + 420, j + 12, 40, 20);
					
					pipe_labels4[i] = new JLabel("|");
					pipe_labels4[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels4[i].setBounds(startWidth + 470, j, 40, 50);

					sem2Oral1_text[i] = new JTextField(sem2Oral1);
					sem2Oral1_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem2Oral1_text[i].setBounds(startWidth + 500, j + 12, 40, 20);
					
					sem2Pract1_text[i] = new JTextField(sem2Pract1);
					sem2Pract1_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem2Pract1_text[i].setBounds(startWidth + 560, j + 12, 40, 20);
					
					sem2Write1_text[i] = new JTextField(sem2Write1);
					sem2Write1_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sem2Write1_text[i].setBounds(startWidth + 620, j + 12, 40, 20);
					
					if(semesterClass.equalsIgnoreCase("Semester 1")){
						dataPanel.add(sem1DailyObs_text[i]);
						dataPanel.add(sem1Obt_text[i]);
						dataPanel.add(sem1Oral_text[i]);
						dataPanel.add(sem1Assign_text[i]);
//						dataPanel.add(sem1Write_text[i]);
//						dataPanel.add(sem1Pres_text[i]);
//						dataPanel.add(sem1Mcap_text[i]);
						dataPanel.add(sem1Project_text[i]);
						dataPanel.add(sem1Pract_text[i]);
						dataPanel.add(sem1Act_text[i]);
						dataPanel.add(sem1Other_text[i]);
						dataPanel.add(pipe_labels4[i]);
						dataPanel.add(sem1Oral1_text[i]);
						dataPanel.add(sem1Pract1_text[i]);
						dataPanel.add(sem1Write1_text[i]);
					}
					else if(semesterClass.equalsIgnoreCase("Semester 2")){
						dataPanel.add(sem2DailyObs_text[i]);
						dataPanel.add(sem2Obt_text[i]);
						dataPanel.add(sem2Oral_text[i]);
						dataPanel.add(sem2Assign_text[i]);
//						dataPanel.add(sem2Write_text[i]);
//						dataPanel.add(sem2Pres_text[i]);
//						dataPanel.add(sem2Mcap_text[i]);
						dataPanel.add(sem2Project_text[i]);
						dataPanel.add(sem2Pract_text[i]);
						dataPanel.add(sem2Act_text[i]);
						dataPanel.add(sem2Other_text[i]);
						dataPanel.add(pipe_labels4[i]);
						dataPanel.add(sem2Oral1_text[i]);
						dataPanel.add(sem2Pract1_text[i]);
						dataPanel.add(sem2Write1_text[i]);
					}

					pipe_labels6[i] = new JLabel("|");
					pipe_labels6[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels6[i].setBounds(1010, j, 40, 50);
					dataPanel.add(pipe_labels6[i]);

					///////////////////////////
					if (stdClass.equalsIgnoreCase("IX") || stdClass.equalsIgnoreCase("X")
							|| stdClass.equalsIgnoreCase("XI") || stdClass.equalsIgnoreCase("XII")) {
						line_labels[i] = new JLabel(
								"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						line_labels[i].setBounds(30, j + 10, 1100, 50);
						dataPanel.add(line_labels[i]);

						j = j + 30;

						convert_label[i] = new JLabel("Convert To");
						convert_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 10));
						convert_label[i].setBounds(190, j + 12, 200, 20);
						dataPanel.add(convert_label[i]);

						pipe_labels7[i] = new JLabel("|");
						pipe_labels7[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						pipe_labels7[i].setBounds(250, j, 120, 50);
						dataPanel.add(pipe_labels7[i]);

						pipe_labels6[i] = new JLabel("|");
						pipe_labels6[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						pipe_labels6[i].setBounds(1010, j, 40, 50);
						dataPanel.add(pipe_labels6[i]);
						//////////////////////////////////

						line2_labels[i] = new JLabel(
								"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						line2_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						line2_labels[i].setBounds(30, j + 12, 1100, 50);
						dataPanel.add(line2_labels[i]);
					}

					orderNo_label[i] = new JLabel(orderNo);
					orderNo_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));

					line1_labels[i] = new JLabel(
							"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					line1_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					line1_labels[i].setBounds(30, j + 10, 1100, 50);
					dataPanel.add(line1_labels[i]);

					final int k = i;
					sem1DailyObs_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {
							sem1DailyObs_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem1DailyObs_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem1_dobs"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_FDOB");
								updateMarksEntryList.add(subName_label[k].getText()+"_FTOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});

					sem1Obt_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem1Obt_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem1Obt_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem1_obt"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_FOBT");
								updateMarksEntryList.add(subName_label[k].getText()+"_FTOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});

					sem1Oral_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem1Oral_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem1Oral_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem1_oral"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_FORA");
								updateMarksEntryList.add(subName_label[k].getText()+"_FTOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});
					
					sem1Assign_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem1Assign_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem1Assign_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem1_assign"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_FASS");
								updateMarksEntryList.add(subName_label[k].getText()+"_FTOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});

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
//							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
//							if(!sem1Write_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem1_write"))){
//								updateMarksEntryList.add(subName_label[k].getText()+"_FWRI");
//								updateMarksEntryList.add(subName_label[k].getText()+"_FTOT");
//							}
//							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem1DailyObs_text[k].getText().trim();
//							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
//									: sem1Obt_text[k].getText().trim();
//							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem1Oral_text[k].getText().trim();
//							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem1Assign_text[k].getText().trim();
//							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem1Write_text[k].getText().trim();
//							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem1Pract_text[k].getText().trim();
//							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem1Act_text[k].getText().trim();
//							String sem1Pres_text_focus = sem1Pres_text[k].getText().trim().equalsIgnoreCase("") ? "0"
//									: sem1Pres_text[k].getText().trim();
//							String sem1Mcap_text_focus = sem1Mcap_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem1Mcap_text[k].getText().trim();
//							String sem2Pres_text_focus = sem2Pres_text[k].getText().trim().equalsIgnoreCase("") ? "0"
//									: sem2Pres_text[k].getText().trim();
//							String sem2Mcap_text_focus = sem2Mcap_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem2Mcap_text[k].getText().trim();
//							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
//									: sem2Obt_text[k].getText().trim();
//							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem2Oral_text[k].getText().trim();
//							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem2Assign_text[k].getText().trim();
//							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem2Write_text[k].getText().trim();
//							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem2Pract_text[k].getText().trim();
//							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem2Act_text[k].getText().trim();
//							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
//									? "0" : sem2DailyObs_text[k].getText().trim();
//
//							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
//							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
//							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
//							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
//							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
//							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
//							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
//							subMaxMinMap.put("sem1_pres", sem1Pres_text_focus);
//							subMaxMinMap.put("sem1_mcap", sem1Mcap_text_focus);
//							subMaxMinMap.put("sem2_pres", sem2Pres_text_focus);
//							subMaxMinMap.put("sem2_mcap", sem2Mcap_text_focus);
//							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
//							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
//							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
//							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
//							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
//							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
//							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
//						}
//					});

					sem1Pract_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem1Pract_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem1Pract_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem1_pract"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_FPRA");
								updateMarksEntryList.add(subName_label[k].getText()+"_FTOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});
					
					sem1Act_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem1Act_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem1Act_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem1_act"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_FACT");
								updateMarksEntryList.add(subName_label[k].getText()+"_FTOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});
					
					sem1Project_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem1Project_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem1Project_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem1_project"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_FPRO");
								updateMarksEntryList.add(subName_label[k].getText()+"_FTOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});
					
					sem1Other_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem1Other_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem1Other_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem1_other"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_FOTH");
								updateMarksEntryList.add(subName_label[k].getText()+"_FTOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});
					
					sem1Oral1_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem1Oral1_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem1Oral1_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem1_oral1"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_FORA1");
								updateMarksEntryList.add(subName_label[k].getText()+"_FTOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});

					sem1Pract1_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem1Pract1_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem1Pract1_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem1_pract1"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_FPRA1");
								updateMarksEntryList.add(subName_label[k].getText()+"_FTOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});
					
					sem1Write1_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem1Write1_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem1Write1_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem1_write1"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_FWRI1");
								updateMarksEntryList.add(subName_label[k].getText()+"_FTOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});
					
					sem2DailyObs_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem2DailyObs_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem2DailyObs_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem2_dobs"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_SDOB");
								updateMarksEntryList.add(subName_label[k].getText()+"_STOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});

					sem2Obt_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem2Obt_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem2Obt_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem2_obt"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_SOBT");
								updateMarksEntryList.add(subName_label[k].getText()+"_STOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});

					sem2Oral_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem2Oral_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem2Oral_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem2_oral"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_SORA");
								updateMarksEntryList.add(subName_label[k].getText()+"_STOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});

					sem2Assign_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem2Assign_text[k].selectAll();

						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem2Assign_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem2_assign"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_SASS");
								updateMarksEntryList.add(subName_label[k].getText()+"_STOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});

					sem2Write_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem2Write_text[k].selectAll();

						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem2Write_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem2_write"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_SWRI");
								updateMarksEntryList.add(subName_label[k].getText()+"_STOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});

					sem2Pract_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem2Pract_text[k].selectAll();

						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem2Pract_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem2_pract"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_SPRA");
								updateMarksEntryList.add(subName_label[k].getText()+"_STOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});
					
					sem2Act_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem2Act_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem2Act_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem2_act"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_SACT");
								updateMarksEntryList.add(subName_label[k].getText()+"_STOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});
					
					sem2Project_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem2Project_text[k].selectAll();

						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem2Project_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem2_project"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_SPRO");
								updateMarksEntryList.add(subName_label[k].getText()+"_STOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});
					
					sem2Other_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem2Other_text[k].selectAll();

						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem2Other_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem2_other"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_SOTH");
								updateMarksEntryList.add(subName_label[k].getText()+"_STOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});
					
					sem2Oral1_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem2Oral1_text[k].selectAll();

						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem2Oral1_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem2_oral1"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_SORA1");
								updateMarksEntryList.add(subName_label[k].getText()+"_STOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});
					
					sem2Pract1_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem2Pract1_text[k].selectAll();

						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem2Pract1_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem2_pract1"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_SPRA1");
								updateMarksEntryList.add(subName_label[k].getText()+"_STOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});
					
					sem2Write1_text[i].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {

							sem2Write1_text[k].selectAll();

						}

						@Override
						public void focusLost(FocusEvent arg0) {
							LinkedHashMap<String, String> subMaxMinMap = new LinkedHashMap<String, String>();
							subMaxMinMap = (LinkedHashMap) subjectMap.get(subName_label[k].getText().toString());
							if(!sem2Write1_text[k].getText().equalsIgnoreCase(subMaxMinMap.get("sem2_write1"))){
								updateMarksEntryList.add(subName_label[k].getText()+"_SWRI1");
								updateMarksEntryList.add(subName_label[k].getText()+"_STOT");
							}
							String sem1Dobs_text_focus = sem1DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1DailyObs_text[k].getText().trim();
							String sem1Obt_text_focus = sem1Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem1Obt_text[k].getText().trim();
							String sem1Oral_text_focus = sem1Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral_text[k].getText().trim();
							String sem1Assign_text_focus = sem1Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Assign_text[k].getText().trim();
							String sem1Write_text_focus = sem1Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write_text[k].getText().trim();
							String sem1Pract_text_focus = sem1Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract_text[k].getText().trim();
							String sem1Act_text_focus = sem1Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Act_text[k].getText().trim();
							String sem1Project_text_focus = sem1Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Project_text[k].getText().trim();
							String sem1Other_text_focus = sem1Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Other_text[k].getText().trim();
							String sem1Oral1_text_focus = sem1Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Oral1_text[k].getText().trim();
							String sem1Pract1_text_focus = sem1Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Pract1_text[k].getText().trim();
							String sem1Write1_text_focus = sem1Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem1Write1_text[k].getText().trim();
							String sem2Obt_text_focus = sem2Obt_text[k].getText().trim().equalsIgnoreCase("") ? "0"
									: sem2Obt_text[k].getText().trim();
							String sem2Oral_text_focus = sem2Oral_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral_text[k].getText().trim();
							String sem2Assign_text_focus = sem2Assign_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Assign_text[k].getText().trim();
							String sem2Write_text_focus = sem2Write_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write_text[k].getText().trim();
							String sem2Pract_text_focus = sem2Pract_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract_text[k].getText().trim();
							String sem2Act_text_focus = sem2Act_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Act_text[k].getText().trim();
							String sem2Dobs_text_focus = sem2DailyObs_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2DailyObs_text[k].getText().trim();
							String sem2Project_text_focus = sem2Project_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Project_text[k].getText().trim();
							String sem2Other_text_focus = sem2Other_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Other_text[k].getText().trim();
							String sem2Oral1_text_focus = sem2Oral1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Oral1_text[k].getText().trim();
							String sem2Pract1_text_focus = sem2Pract1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Pract1_text[k].getText().trim();
							String sem2Write1_text_focus = sem2Write1_text[k].getText().trim().equalsIgnoreCase("")
									? "0" : sem2Write1_text[k].getText().trim();

							subMaxMinMap.put("sem1_dobs", sem1Dobs_text_focus);
							subMaxMinMap.put("sem1_obt", sem1Obt_text_focus);
							subMaxMinMap.put("sem1_oral", sem1Oral_text_focus);
							subMaxMinMap.put("sem1_assign", sem1Assign_text_focus);
							subMaxMinMap.put("sem1_write", sem1Write_text_focus);
							subMaxMinMap.put("sem1_pract", sem1Pract_text_focus);
							subMaxMinMap.put("sem1_act", sem1Act_text_focus);
							subMaxMinMap.put("sem1_project", sem1Project_text_focus);
							subMaxMinMap.put("sem1_other", sem1Other_text_focus);
							subMaxMinMap.put("sem1_oral1", sem1Oral1_text_focus);
							subMaxMinMap.put("sem1_pract1", sem1Pract1_text_focus);
							subMaxMinMap.put("sem1_write1", sem1Write1_text_focus);
							subMaxMinMap.put("sem2_obt", sem2Obt_text_focus);
							subMaxMinMap.put("sem2_oral", sem2Oral_text_focus);
							subMaxMinMap.put("sem2_assign", sem2Assign_text_focus);
							subMaxMinMap.put("sem2_write", sem2Write_text_focus);
							subMaxMinMap.put("sem2_pract", sem2Pract_text_focus);
							subMaxMinMap.put("sem2_act", sem2Act_text_focus);
							subMaxMinMap.put("sem2_dobs", sem2Dobs_text_focus);
							subMaxMinMap.put("sem2_project", sem2Project_text_focus);
							subMaxMinMap.put("sem2_other", sem2Other_text_focus);
							subMaxMinMap.put("sem2_oral1", sem2Oral1_text_focus);
							subMaxMinMap.put("sem2_pract1", sem2Pract1_text_focus);
							subMaxMinMap.put("sem2_write1", sem2Write1_text_focus);
						}
					});
					//////////////////////
					i++;
				}
				logger.info("subjectList==> " + subjectList.size());

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
							"               " + (++n) + ") DOBS - Daily Observation, OBT - Open Book Test, ACT - Activity");
					abbrevations_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
					abbrevations_label.setBounds(40, k, 1100, 50);
					abbrevations_label.setForeground(Color.RED);
					dataPanel.add(abbrevations_label);
					k = k + 15;
				}
				else {
					JLabel abbrevations_label = new JLabel(
							"               " + (++n) + ") FUT - First Unit Test, SUT - Second Unit Test, PRES - Presentation, "
									+ "MCAP - Measuring Capacity, ACT - Activity");
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
							subjectList.clear();
							Set set = subjectListMap.entrySet();
							Iterator i = set.iterator();
							while (i.hasNext()) {
								Map.Entry me = (Map.Entry) i.next();
								subjectList.add(me.getValue().toString());
							}

							String[] subjectArray = new String[subjectList.size()];
							String[] backupSubjectArray = new String[backupSubjectList.size()];

							Collections.sort(backupSubjectList);
							Collections.sort(subjectList);

							reply = JOptionPane.showConfirmDialog(null,
									"This update will reset the marks entered in Marks Entry session. \n "
											+ "Would You Like to update Marks Allotment?",
									"Confirm update", JOptionPane.YES_NO_OPTION);

							if (reply == JOptionPane.YES_OPTION) {

								Set set1 = subjectMap.entrySet();
								// Get an iterator
								Iterator m = set1.iterator();

								while (m.hasNext()) {
									Map.Entry me = (Map.Entry) m.next();
									LinkedHashMap subjectMaxMap = new LinkedHashMap();
									subjectMaxMap = (LinkedHashMap) me.getValue();

									String std = stdClass;
									String subject = subjectMaxMap.get("subject_name") == null ? "0" : subjectMaxMap.get("subject_name").toString();
									String subTitle = subjectMaxMap.get("subject_title") == null ? "0" : subjectMaxMap.get("subject_title").toString();
									String marks_grade = subjectMaxMap.get("marks_grade") == null ? "0" : subjectMaxMap.get("marks_grade").toString();
									String optional = subjectMaxMap.get("optional") == null ? "0" : subjectMaxMap.get("optional").toString();
									
									String sem1Dobs = (subjectMaxMap.get("sem1_dobs") == null || subjectMaxMap.get("sem1_dobs").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_dobs").toString();
									String sem1Obt = (subjectMaxMap.get("sem1_obt") == null || subjectMaxMap.get("sem1_obt").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_obt").toString();
									String sem1Oral = (subjectMaxMap.get("sem1_oral") == null || subjectMaxMap.get("sem1_oral").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_oral").toString();
									String sem1Assign = (subjectMaxMap.get("sem1_assign") == null || subjectMaxMap.get("sem1_assign").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_assign").toString();
									String sem1Write = (subjectMaxMap.get("sem1_write") == null || subjectMaxMap.get("sem1_write").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_write").toString();
									String sem1Pract = (subjectMaxMap.get("sem1_pract") == null || subjectMaxMap.get("sem1_pract").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_pract").toString();
									String sem1Act = (subjectMaxMap.get("sem1_act") == null || subjectMaxMap.get("sem1_act").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_act").toString();
									String sem1Project = (subjectMaxMap.get("sem1_project") == null || subjectMaxMap.get("sem1_project").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_project").toString();
									String sem1Other = (subjectMaxMap.get("sem1_other") == null || subjectMaxMap.get("sem1_other").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_other").toString();
									String sem1Oral1 = (subjectMaxMap.get("sem1_oral1") == null || subjectMaxMap.get("sem1_oral1").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_oral1").toString();
									String sem1Pract1 = (subjectMaxMap.get("sem1_pract1") == null || subjectMaxMap.get("sem1_pract1").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_pract1").toString();
									String sem1Write1 = (subjectMaxMap.get("sem1_write1") == null || subjectMaxMap.get("sem1_write1").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_write1").toString();
									String sem1Pres = (subjectMaxMap.get("sem1_pres") == null || subjectMaxMap.get("sem1_pres").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_pres").toString();
									String sem1Mcap = (subjectMaxMap.get("sem1_mcap") == null || subjectMaxMap.get("sem1_mcap").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_mcap").toString();
									
									String sem2Pres = (subjectMaxMap.get("sem2_pres") == null || subjectMaxMap.get("sem2_pres").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_pres").toString();
									String sem2Mcap = (subjectMaxMap.get("sem2_mcap") == null || subjectMaxMap.get("sem2_mcap").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_mcap").toString();
									String sem2Dobs = (subjectMaxMap.get("sem2_dobs") == null || subjectMaxMap.get("sem2_dobs").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_dobs").toString();
									String sem2Obt = (subjectMaxMap.get("sem2_obt") == null || subjectMaxMap.get("sem2_obt").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_obt").toString();
									String sem2Oral = (subjectMaxMap.get("sem2_oral") == null || subjectMaxMap.get("sem2_oral").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_oral").toString();
									String sem2Assign = (subjectMaxMap.get("sem2_assign") == null || subjectMaxMap.get("sem2_assign").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_assign").toString();
									String sem2Write = (subjectMaxMap.get("sem2_write") == null || subjectMaxMap.get("sem2_write").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_write").toString();
									String sem2Pract = (subjectMaxMap.get("sem2_pract") == null || subjectMaxMap.get("sem2_pract").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_pract").toString();
									String sem2Act = (subjectMaxMap.get("sem2_act") == null || subjectMaxMap.get("sem2_act").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_act").toString();
									String sem2Project = (subjectMaxMap.get("sem2_project") == null || subjectMaxMap.get("sem2_project").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_project").toString();
									String sem2Other = (subjectMaxMap.get("sem2_other") == null || subjectMaxMap.get("sem2_other").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_other").toString();
									String sem2Oral1 = (subjectMaxMap.get("sem2_oral1") == null || subjectMaxMap.get("sem2_oral1").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_oral1").toString();
									String sem2Pract1 = (subjectMaxMap.get("sem2_pract1") == null || subjectMaxMap.get("sem2_pract1").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_pract1").toString();
									String sem2Write1 = (subjectMaxMap.get("sem2_write1") == null || subjectMaxMap.get("sem2_write1").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_write1").toString();
									String orderNo = (subjectMaxMap.get("order_no") == null || subjectMaxMap.get("order_no").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("order_no").toString();

									String sem1DobsCt = (subjectMaxMap.get("sem1_dobs_ct") == null || subjectMaxMap.get("sem1_dobs_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_dobs_ct").toString();
									String sem1ObtCt = (subjectMaxMap.get("sem1_obt_ct") == null || subjectMaxMap.get("sem1_obt_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_obt_ct").toString();
									String sem1OralCt = (subjectMaxMap.get("sem1_oral_ct") == null || subjectMaxMap.get("sem1_oral_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_oral_ct").toString();
									String sem1AssignCt = (subjectMaxMap.get("sem1_assign_ct") == null || subjectMaxMap.get("sem1_assign_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_assign_ct").toString();
									String sem1WriteCt = (subjectMaxMap.get("sem1_write_ct") == null || subjectMaxMap.get("sem1_write_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_write_ct").toString();
									String sem1PractCt = (subjectMaxMap.get("sem1_pract_ct") == null || subjectMaxMap.get("sem1_pract_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_pract_ct").toString();
									String sem1ActCt = (subjectMaxMap.get("sem1_act_ct") == null || subjectMaxMap.get("sem1_act_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_act_ct").toString();
									String sem1PresCt = (subjectMaxMap.get("sem1_pres_ct") == null || subjectMaxMap.get("sem1_pres_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_pres_ct").toString();
									String sem1McapCt = (subjectMaxMap.get("sem1_mcap_ct") == null || subjectMaxMap.get("sem1_mcap_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_mcap_ct").toString();
									String sem1ProjectCt = (subjectMaxMap.get("sem1_project_ct") == null || subjectMaxMap.get("sem1_project_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_project_ct").toString();
									String sem1OtherCt = (subjectMaxMap.get("sem1_other_ct") == null || subjectMaxMap.get("sem1_other_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_other_ct").toString();
									String sem1Oral1Ct = (subjectMaxMap.get("sem1_oral1_ct") == null || subjectMaxMap.get("sem1_oral1_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_oral1_ct").toString();
									String sem1Pract1Ct = (subjectMaxMap.get("sem1_pract1_ct") == null || subjectMaxMap.get("sem1_pract1_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_pract1_ct").toString();
									String sem1Write1Ct = (subjectMaxMap.get("sem1_write1_ct") == null || subjectMaxMap.get("sem1_write1_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem1_write1_ct").toString();
									String sem2PresCt = (subjectMaxMap.get("sem2_pres_ct") == null || subjectMaxMap.get("sem2_pres_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_pres_ct").toString();
									String sem2McapCt = (subjectMaxMap.get("sem2_mcap_ct") == null || subjectMaxMap.get("sem2_mcap_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_mcap_ct").toString();
									String sem2DobsCt = (subjectMaxMap.get("sem2_dobs_ct") == null || subjectMaxMap.get("sem2_dobs_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_dobs_ct").toString();
									String sem2ObtCt = (subjectMaxMap.get("sem2_obt_ct") == null || subjectMaxMap.get("sem2_obt_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_obt_ct").toString();
									String sem2OralCt = (subjectMaxMap.get("sem2_oral_ct") == null || subjectMaxMap.get("sem2_oral_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_oral_ct").toString();
									String sem2AssignCt = (subjectMaxMap.get("sem2_assign_ct") == null || subjectMaxMap.get("sem2_assign_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_assign_ct").toString();
									String sem2WriteCt = (subjectMaxMap.get("sem2_write_ct") == null || subjectMaxMap.get("sem2_write_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_write_ct").toString();
									String sem2PractCt = (subjectMaxMap.get("sem2_pract_ct") == null || subjectMaxMap.get("sem2_pract_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_pract_ct").toString();
									String sem2ActCt = (subjectMaxMap.get("sem2_act_ct") == null || subjectMaxMap.get("sem2_act_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_act_ct").toString();
									String sem2ProjectCt = (subjectMaxMap.get("sem2_project_ct") == null || subjectMaxMap.get("sem2_project_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_project_ct").toString();
									String sem2OtherCt = (subjectMaxMap.get("sem2_other_ct") == null || subjectMaxMap.get("sem2_other_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_other_ct").toString();
									String sem2Oral1Ct = (subjectMaxMap.get("sem2_oral1_ct") == null || subjectMaxMap.get("sem2_oral1_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_oral1_ct").toString();
									String sem2Pract1Ct = (subjectMaxMap.get("sem2_pract1_ct") == null || subjectMaxMap.get("sem2_pract1_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_pract1_ct").toString();
									String sem2Write1Ct = (subjectMaxMap.get("sem2_write1_ct") == null || subjectMaxMap.get("sem2_write1_ct").toString().equalsIgnoreCase("null")) ? "0" : subjectMaxMap.get("sem2_write1_ct").toString();

									String updateMarksEntryQuery = "update marks_entry set ";
									String sem1_dobs = "";
									String sem1_obt = "";
									String sem1_oral = "";
									String sem1_assign = "";
									String sem1_write = "";
									String sem1_pract = "";
									String sem1_act = "";
									String sem1_pres = "";
									String sem1_mcap = "";
									String sem1_project = "";
									String sem1_other = "";
									String sem1_oral1 = "";
									String sem1_pract1 = "";
									String sem1_write1 = "";
									String sem2_pres = "";
									String sem2_mcap = "";
									String sem2_dobs = "";
									String sem2_obt = "";
									String sem2_oral = "";
									String sem2_assign = "";
									String sem2_write = "";
									String sem2_pract = "";
									String sem2_act = "";
									String sem2_project = "";
									String sem2_other = "";
									String sem2_oral1 = "";
									String sem2_pract1 = "";
									String sem2_write1 = "";

									sem1_dobs = "," + subject + "_FDOB=" + "'0'";
									sem1_obt = "," + subject + "_FOBT=" + "'0'";
									sem1_oral = "," + subject + "_FORA=" + "'0'";
									sem1_assign = "," + subject + "_FASS=" + "'0'";
									sem1_write = "," + subject + "_FWRI=" + "'0'";
									sem1_pract = "," + subject + "_FPRA=" + "'0'";
									sem1_act = "," + subject + "_FACT=" + "'0'";
									sem1_pres = "," + subject + "_FPRE=" + "'0'";
									sem1_mcap = "," + subject + "_FMCA=" + "'0'";
									sem1_project = "," + subject + "_FPRO=" + "'0'";
									sem1_other = "," + subject + "_FOTH=" + "'0'";
									sem1_oral1 = "," + subject + "_FORA1=" + "'0'";
									sem1_write1 = "," + subject + "_FWRI1=" + "'0'";
									sem1_pract1 = "," + subject + "_FPRA1=" + "'0'";
									sem2_pres = "," + subject + "_SPRE=" + "'0'";
									sem2_mcap = "," + subject + "_SMCA=" + "'0'";
									sem2_dobs = "," + subject + "_SDOB=" + "'0'";
									sem2_obt = "," + subject + "_SOBT=" + "'0'";
									sem2_oral = "," + subject + "_SORA=" + "'0'";
									sem2_assign = "," + subject + "_SASS=" + "'0'";
									sem2_write = "," + subject + "_SWRI=" + "'0'";
									sem2_pract = "," + subject + "_SPRA=" + "'0'";
									sem2_act = "," + subject + "_SACT=" + "'0'";
									sem2_project = "," + subject + "_SPRO=" + "'0'";
									sem2_other = "," + subject + "_SOTH=" + "'0'";
									sem2_oral1 = "," + subject + "_SORA1=" + "'0'";
									sem2_write1 = "," + subject + "_SWRI1=" + "'0'";
									sem2_pract1 = "," + subject + "_SPRA1=" + "'0'";

									String setValues = sem1_dobs + sem1_obt + sem1_oral + sem1_assign + sem1_write
											+ sem1_pract + sem1_act + sem2_dobs + sem2_obt + sem2_oral + sem2_assign + sem2_write
											+ sem2_pract + sem2_act + sem1_pres + sem1_mcap + sem2_pres + sem2_mcap + sem1_project 
											+ sem1_other + sem1_oral1 + sem1_pract1 + sem1_write1 + sem2_project + sem2_other + sem2_oral1 
											+ sem2_pract1 + sem2_write1;

									if (!setValues.equalsIgnoreCase("")) {
										setValues = setValues.substring(1);
										updateMarksEntryQuery = updateMarksEntryQuery + setValues + " where std_1 = '"
												+ std + "' and academic_year = '" + academicYearClass + "'";
									}

									std = std.trim();
									if (!std.equalsIgnoreCase("IX") && !std.equalsIgnoreCase("X")
											&& !std.equalsIgnoreCase("XI") && !std.equalsIgnoreCase("XII")) {
										if (sem1Dobs.equalsIgnoreCase("") || !commonObj.validateNumber(sem1Dobs)) {
											JOptionPane.showMessageDialog(null,
													"Please enter First Semester Daily Observation for all subjects in numeric.");
											validateSubList = false;
											break;
										}
									} else if (sem1Obt.equalsIgnoreCase("") || !commonObj.validateNumber(sem1Obt)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Fut for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem1Oral.equalsIgnoreCase("") || !commonObj.validateNumber(sem1Oral)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Oral for all subjects in numeric.");
										validateSubList = false;
										break;
									} else
										if (sem1Assign.equalsIgnoreCase("") || !commonObj.validateNumber(sem1Assign)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Assign for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem1Write.equalsIgnoreCase("") || !commonObj.validateNumber(sem1Write)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Write for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem1Pract.equalsIgnoreCase("") || !commonObj.validateNumber(sem1Pract)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Practical for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem1Act.equalsIgnoreCase("") || !commonObj.validateNumber(sem1Act)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Activity for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem1Pres.equalsIgnoreCase("") || !commonObj.validateNumber(sem1Pres)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Presentation for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem1Mcap.equalsIgnoreCase("") || !commonObj.validateNumber(sem1Mcap)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Measuring Capacity for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem1Project.equalsIgnoreCase("") || !commonObj.validateNumber(sem1Project)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Project for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem1Other.equalsIgnoreCase("") || !commonObj.validateNumber(sem1Other)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Other for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem1Oral1.equalsIgnoreCase("") || !commonObj.validateNumber(sem1Oral1)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Oral for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem1Pract1.equalsIgnoreCase("") || !commonObj.validateNumber(sem1Pract1)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Practical for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem1Write1.equalsIgnoreCase("") || !commonObj.validateNumber(sem1Write1)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Write for all subjects in numeric.");
										validateSubList = false;
										break;
									}
									
									if (!std.equalsIgnoreCase("IX") && !std.equalsIgnoreCase("X")
											&& !std.equalsIgnoreCase("XI") && !std.equalsIgnoreCase("XII")) {
										if (sem2Dobs.equalsIgnoreCase("") || !commonObj.validateNumber(sem2Dobs)) {
											JOptionPane.showMessageDialog(null,
													"Please enter Second Semester Daily Observation for all subjects in numeric.");
											validateSubList = false;
											break;
										}
									} else if (sem2Obt.equalsIgnoreCase("") || !commonObj.validateNumber(sem2Obt)) {
										JOptionPane.showMessageDialog(null,
												"Please enter Second Semester Sut for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem2Oral.equalsIgnoreCase("") || !commonObj.validateNumber(sem2Oral)) {
										JOptionPane.showMessageDialog(null,
												"Please enter Second Semester Oral for all subjects in numeric.");
										validateSubList = false;
										break;
									} else
										if (sem2Assign.equalsIgnoreCase("") || !commonObj.validateNumber(sem2Assign)) {
										JOptionPane.showMessageDialog(null,
												"Please enter Second Semester Assign for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem2Write.equalsIgnoreCase("") || !commonObj.validateNumber(sem2Write)) {
										JOptionPane.showMessageDialog(null,
												"Please enter Second Semester Write for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem2Pract.equalsIgnoreCase("") || !commonObj.validateNumber(sem2Pract)) {
										JOptionPane.showMessageDialog(null,
												"Please enter Second Semester Practical for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem2Act.equalsIgnoreCase("") || !commonObj.validateNumber(sem2Act)) {
										JOptionPane.showMessageDialog(null,
												"Please enter Second Semester Activity for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem2Pres.equalsIgnoreCase("") || !commonObj.validateNumber(sem2Pres)) {
										JOptionPane.showMessageDialog(null,
												"Please enter Second Semester Presentation for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem2Mcap.equalsIgnoreCase("") || !commonObj.validateNumber(sem2Mcap)) {
										JOptionPane.showMessageDialog(null,
												"Please enter Second Semester Measuring Capacity for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem2Project.equalsIgnoreCase("") || !commonObj.validateNumber(sem2Project)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Project for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem2Other.equalsIgnoreCase("") || !commonObj.validateNumber(sem2Other)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Other for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem2Oral1.equalsIgnoreCase("") || !commonObj.validateNumber(sem2Oral1)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Oral for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem2Pract1.equalsIgnoreCase("") || !commonObj.validateNumber(sem2Pract1)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Practical for all subjects in numeric.");
										validateSubList = false;
										break;
									} else if (sem2Write1.equalsIgnoreCase("") || !commonObj.validateNumber(sem2Write1)) {
										JOptionPane.showMessageDialog(null,
												"Please enter First Semester Write for all subjects in numeric.");
										validateSubList = false;
										break;
									}
								}
								logger.info("validateSubList = " + validateSubList);
								if (validateSubList) {
									try {
										if (dbValidate.connectDatabase(sessionData)) {
											dbValidate.updateMarksEntryToReset(updateMarksEntryList, academicYearClass, stdClass);
											insertSubject = dbValidate.updateSubMaxMin(sessionData, subjectMap,
													academicYearClass, stdClass, semesterClass);
										}
									} catch (Exception e12) {
										logger.info("Exception logoutButton ===>>>" + e12);
									} finally {
										dbValidate.closeDatabase(sessionData);
									}

									if (!insertSubject) {
										JOptionPane.showMessageDialog(null, "Subject data updatation failed.");
									} else {
										JOptionPane.showMessageDialog(null, "Subject data updated successfully.");
									}
									frame.setVisible(false);
									List subList = new ArrayList();
									new MarksAllotmentNew(sessionData, stdClass, subList, academicYearClass, category,
											false, section, user_name, user_role, semesterClass);
								}
								logger.info("insertSubject = " + insertSubject);
							}
							/*
							 * } else{ JOptionPane.showMessageDialog(null,
							 * "No change found to save.."); }
							 */
						} catch (Exception e1) {
							logger.info("Exception e1 = " + e1);
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
	}
}
