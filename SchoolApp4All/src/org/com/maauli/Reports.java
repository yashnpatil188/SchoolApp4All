package org.com.maauli;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
// import com.lowagie.text.DocumentException;
// import com.lowagie.text.pdf.TextField;
import org.com.accesser.SessionData;

import com.lowagie.text.DocumentException;

public class Reports extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JFrame frame = null;

	static JComboBox studentcombo;

	static LinkedHashMap foundStudentList = new LinkedHashMap();

	static LinkedHashMap<String, LinkedHashMap<String, String>> findSubMap;

	static String subjectClass = "";

	static int retCount = 0;

	static int scrollHeight = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

	static List<String> subjectList = new ArrayList();

	static boolean setSelected = false;

	static boolean printDuplicate = false;

	static String reportList = "";

	static String grNoClass = "";

	static String stdClass = "";

	static String divClass = "";

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

	static String user_role = "";

	static DBValidate dbValidate = new DBValidate();

	static SessionData sessionData = new SessionData();

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

	static String yearList = "";

	static String examCategory = "";

	static String examClass = "Select";

	static String examDisplay = "";

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

	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static Logger logger = Logger.getLogger(Reports.class.getName());

	public Reports(SessionData sessionData1, String retStd, String retDiv, String academicYear, String sec,
			String retUserName, String retUserRole, LinkedHashMap retStudentHmap, String retCatType, String retExam,
			String subjectName) {

		System.gc();
		findSubMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		stdClass = retStd;
		divClass = retDiv;
		subjectClass = subjectName;
		category = retCatType;
		section = sec;
		logger.info("section :: " + section);
		std = bundle.getString(section.toUpperCase() + "_STD");
		logger.info("std :: " + std);
		div = bundle.getString(section.toUpperCase() + "_DIV");
		logger.info("div :: " + div);
		secName = bundle.getString(section.toUpperCase() + "_SEC");
		logger.info("secName :: " + secName);
		reportList = bundle.getString("REPORT_LIST");
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
		app_header_0_widthSpace = Integer
				.parseInt(bundle.getString("APP_HEADER_0_WIDTHSPACE_" + sessionData.getAppType()));
		app_header_0_heightSpace = Integer
				.parseInt(bundle.getString("APP_HEADER_0_HEIGHTSPACE_" + sessionData.getAppType()));
		app_header_fontName = bundle.getString("APP_HEADER_FONTNAME_" + sessionData.getAppType());
		app_header_fontSize = Integer.parseInt(bundle.getString("APP_HEADER_FONTSIZE_" + sessionData.getAppType()));
		app_header_widthSpace = Integer.parseInt(bundle.getString("APP_HEADER_WIDTHSPACE_" + sessionData.getAppType()));
		app_header_heightSpace = Integer
				.parseInt(bundle.getString("APP_HEADER_HEIGHTSPACE_" + sessionData.getAppType()));
		app_header_2 = bundle.getString("APP_HEADER_2_" + sessionData.getAppType());
		app_header_2_fontName = bundle.getString("APP_HEADER_2_FONTNAME_" + sessionData.getAppType());
		app_header_2_fontSize = Integer.parseInt(bundle.getString("APP_HEADER_2_FONTSIZE_" + sessionData.getAppType()));
		app_header_2_widthSpace = Integer
				.parseInt(bundle.getString("APP_HEADER_2_WIDTHSPACE_" + sessionData.getAppType()));
		app_header_2_heightSpace = Integer
				.parseInt(bundle.getString("APP_HEADER_2_HEIGHTSPACE_" + sessionData.getAppType()));

		examCategory = bundle.getString("EXAM_CATEGORY");

		if (!retExam.equalsIgnoreCase("")) {
			examClass = retExam;
		} else {
			examClass = "Select";
		}
		if (examClass.equalsIgnoreCase("Semester 1")) {
			examDisplay = "Sem 1";
		}
		if (examClass.equalsIgnoreCase("Semester 2")) {
			examDisplay = "Sem 2";
		}
		if (examClass.equalsIgnoreCase("Final")) {
			examDisplay = "Final";
		}

		String todayDate = commonObj.getCurrentDate();
		// academicYearClass = commonObj.getAcademicYear(todayDate);
		if (!academicYear.equalsIgnoreCase("")) {
			academicYearClass = academicYear;
		}
		try {
			if (dbValidate.connectDatabase(sessionData)) {
				yearList = dbValidate.findYearList(sessionData, "HS_GENERAL_REGISTER");
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
			std = retStd + ",All," + std;
		} else {
			std = "Select,All," + std;
		}
		if (!retDiv.equalsIgnoreCase("")) {
			div = retDiv + ",All," + div;
		} else {
			div = "Select,All," + div;
		}

		foundStudentList = retStudentHmap;
		retCount = foundStudentList.size();
		logger.info("foundStudentList size ==> " + retCount);
		if (setSelected) {
			selectAllCount = retCount;
		}
		scrollHeight = (retCount - 6) * 30; // to adjust the perfect scroll
											// height
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

				LinkedHashMap<String, LinkedHashMap<String, String>> studMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
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
				panelHome.removeAll();/// to remve entire panel
				new ClassAllotment(sessionData, "", "", "", "", "", "", null, false, "", "", "", section, user_name,
						user_role, "");
			}
		});

		JButton subAllotButton = new JButton("Subject Allot");
		subAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		subAllotButton.setBounds(260, 50, 130, 24);// 620, 50, 150, 24
		topbandPanel.add(subAllotButton);

		subAllotButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				panelHome.removeAll();/// to remve entire panel
				List subAllotList = new ArrayList();
				new MarksAllotment(sessionData, "", subAllotList, "", "", false, section, user_name, user_role, "");
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
				new MarksEntry(sessionData, "", markList, "", false, "", "", "", "", "", "", "", "", "",
						section, user_name, user_role, "", "");
			}
		});

		JButton remarkButton = new JButton("Remarks");
		remarkButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		remarkButton.setBounds(540, 50, 100, 24); // 300, 50, 150, 24
		remarkButton.setEnabled(true);
		topbandPanel.add(remarkButton);

		remarkButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				List markList = new ArrayList();
				RemarksEntry remarksEntryObject = RemarksEntry.getInstance();
				remarksEntryObject.getRemarksEntry(sessionData, "", "", false, "", "", "", "", "", "", "", "", "",
						section, user_name, user_role, "", "", "");
			}
		});

		JButton resultPrintButton = new JButton("Result");
		resultPrintButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		resultPrintButton.setBounds(650, 50, 90, 24); // 300, 50, 150, 24
		topbandPanel.add(resultPrintButton);

		resultPrintButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				panelHome.removeAll();/// to remve entire panel
				List findList = new ArrayList();
				new Result(sessionData, "", "", "", "", "", "", findList, false, false, "", section, user_name,
						user_role, "", "", "", findList, findList, findList);
			}
		});

		JButton attendanceButton = new JButton("Attendance");
		attendanceButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		attendanceButton.setBounds(750, 50, 130, 24); // 300, 50, 150, 24
		topbandPanel.add(attendanceButton);

		attendanceButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				List studentList = new ArrayList();
				new AttendanceUpdate(sessionData, "", "", "", section, user_name, user_role, studentList, "", "", "",
						"", false, "", true, "", false);
				frame.setVisible(false);
				panelHome.removeAll();/// to remve entire panel
			}
		});

		JButton reportButton = new JButton("Reports");
		reportButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		reportButton.setBounds(890, 50, 100, 24);
		reportButton.setBackground(Color.GREEN);
		topbandPanel.add(reportButton);

		reportButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

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
		JLabel academic_label = new JLabel("Year :");
		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academic_label.setBounds(90, 00, 70, 50);
		findPanel.add(academic_label);

		String academicYearList[] = yearList.split(",");
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academicYear_combo.setBounds(160, 12, 120, 25);
		findPanel.add(academicYear_combo);

		// /////////////Std//////////////
		JLabel std_label = new JLabel("Std :");
		std_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		std_label.setBounds(380, 00, 70, 50);
		findPanel.add(std_label);

		// String stdList[] =
		// {stdSel,"IV","V","VI","VII","VIII","IX","X"};
		String[] stdList = std.split(",");
		final JComboBox std_combo = new JComboBox(stdList);
		std_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		std_combo.setBounds(430, 12, 90, 25);
		findPanel.add(std_combo);

		// //////////////////////////////////
		// /////////////Div//////////////
		JLabel div_label = new JLabel("Div :");
		div_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		div_label.setBounds(530, 00, 70, 50);
		findPanel.add(div_label);

		// String admittedDivList[] = {divClass,"A","B","C","D"};
		String[] divList = div.split(",");
		final JComboBox div_combo = new JComboBox(divList);
		div_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		div_combo.setBounds(580, 12, 100, 25);
		if (!stdClass.equalsIgnoreCase("All") && !stdClass.equalsIgnoreCase("Select")
				&& dbValidate.connectDatabase(sessionData)) {
			div_combo.removeAllItems();
			if (divClass.equalsIgnoreCase("")) {
				div_combo.addItem("Select");
				div_combo.addItem("All");
			} else {
				div_combo.addItem(divClass);
			}
			String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdClass, section, "PRESENT_DIV", "PRESENT_STD",
					"class_allotment", academicYearClass);

			for (String retval : divAvailabe.split(",")) {
				div_combo.addItem(retval);
			}
		}
		div_combo.setSelectedItem(divClass);
		findPanel.add(div_combo);

		// ///////////////////////////////
		JLabel catType_label = new JLabel("Category Type :");
		catType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		catType_label.setBounds(710, 00, 150, 50);
		findPanel.add(catType_label);

		if (!category.equalsIgnoreCase("")) {
			reportList = category + "," + reportList;
		}
		String[] catList = reportList.split(",");
		// String catList[] = {lcTypeClass,"Category Wise","Religion
		// Wise","General"};
		final JComboBox cat_combo = new JComboBox(catList);
		cat_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		cat_combo.setBounds(830, 12, 220, 25);
		findPanel.add(cat_combo);

		final JLabel exam_label = new JLabel("Exam : ");
		exam_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		exam_label.setBounds(90, 40, 70, 50);
		findPanel.add(exam_label);

		String[] examList = examCategory.split(",");
		final DefaultComboBoxModel model = new DefaultComboBoxModel(examList);
		final JComboBox exam_combo = new JComboBox(model);
		if (!stdClass.equalsIgnoreCase("IX") && !stdClass.equalsIgnoreCase("X") && !stdClass.equalsIgnoreCase("XI")
				&& !stdClass.equalsIgnoreCase("XII")) {
			exam_combo.removeItem("Final");
			;
		} else if (model.getIndexOf("Final") == -1) {
			exam_combo.addItem("Final");
		}
		exam_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		exam_combo.setSelectedItem(examClass);
		exam_combo.setBounds(160, 52, 120, 25);
		findPanel.add(exam_combo);

		final JLabel subject_label = new JLabel("Subject");
		subject_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		subject_label.setBounds(340, 40, 150, 50);
		findPanel.add(subject_label);

		final JComboBox subject_combo = new JComboBox();
		subject_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		subject_combo.setBounds(410, 52, 220, 25);
		findPanel.add(subject_combo);

		// ////////update subject list//////////////////////////
		if (!stdClass.equalsIgnoreCase("") && !stdClass.equalsIgnoreCase("Select")) {
			// presentDiv_combo.setEnabled(true);
			try {
				findSubMap = dbValidate.getSujectDetails(sessionData, stdClass, academicYearClass);
				subject_combo.removeAllItems();
				subject_combo.addItem("Select");
				subject_combo.addItem("All");

				String titleList = "";
				Set set = findSubMap.entrySet();
				Iterator n = set.iterator();
				while (n.hasNext()) {
					Map.Entry me = (Map.Entry) n.next();
					LinkedHashMap subjectmap = new LinkedHashMap();
					subjectmap = (LinkedHashMap) me.getValue();
					if (!titleList.contains("," + subjectmap.get("subject_title").toString())) {
						titleList = titleList + "," + subjectmap.get("subject_title");
						subject_combo.addItem(subjectmap.get("subject_title"));
					}
				}
				subject_combo.setSelectedItem(subjectClass);
			} catch (Exception e2) {
				logger.info("Exceptyion findSubList : " + e2);
			}
		} else {
			try {
				findSubMap = dbValidate.getSujectDetails(sessionData, stdClass, academicYearClass);
				subject_combo.removeAllItems();
				subject_combo.addItem("Select");
				subject_combo.addItem("All");
				if (!subjectClass.equalsIgnoreCase(""))
					subject_combo.addItem(subjectClass);

				String titleList = "";
				Set set = findSubMap.entrySet();
				Iterator n = set.iterator();
				while (n.hasNext()) {
					Map.Entry me = (Map.Entry) n.next();
					LinkedHashMap subjectmap = new LinkedHashMap();
					subjectmap = (LinkedHashMap) me.getValue();
					if (!titleList.contains("," + subjectmap.get("subject_title").toString())) {
						titleList = titleList + "," + subjectmap.get("subject_title");
						subject_combo.addItem(subjectmap.get("subject_title"));
					}
				}
			} catch (Exception e2) {
				commonObj.logException(e2);
			}
		}

		// ///////////////////////////////
		final JLabel formType_label = new JLabel("Form Type :");
		formType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		formType_label.setBounds(90, 40, 150, 50);
		findPanel.add(formType_label);

		String formList[] = { "Statement A", "Statement B" };
		final JComboBox form_combo = new JComboBox(formList);
		form_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		form_combo.setBounds(190, 52, 130, 25);
		findPanel.add(form_combo);

		// ///////////////////////////////
		final JLabel gender_label = new JLabel("Gender :");
		gender_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		gender_label.setBounds(360, 40, 150, 50);
		findPanel.add(gender_label);

		String genderList[] = { "Boys", "Girls" };
		final JComboBox gender_combo = new JComboBox(genderList);
		gender_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		gender_combo.setBounds(430, 52, 100, 25);
		findPanel.add(gender_combo);

		// //////////////////////////////////
		if (((String) cat_combo.getSelectedItem()).equalsIgnoreCase("General")) {
			// div_combo.setSelectedIndex(1);
			// div_combo.setSelectedItem("All");
			// div_combo.setEnabled(false);
		} else {
			div_combo.setSelectedIndex(0);
			div_combo.setEnabled(true);
		}

		std_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					String stdSel = (String) std_combo.getSelectedItem();
					String acadSel = (String) academicYear_combo.getSelectedItem();
					if (!stdSel.equalsIgnoreCase("All") && !stdSel.equalsIgnoreCase("Select")
							&& dbValidate.connectDatabase(sessionData)) {
						div_combo.removeAllItems();
						div_combo.addItem("Select");
						div_combo.addItem("All");
						String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdSel, section, "PRESENT_DIV",
								"PRESENT_STD", "class_allotment", acadSel);

						for (String retval : divAvailabe.split(",")) {
							div_combo.addItem(retval);
						}
					}
					//// update subject list
					if (!stdSel.equalsIgnoreCase("") && !stdSel.equalsIgnoreCase("Select")) {
						try {
							findSubMap = dbValidate.getSujectDetails(sessionData, stdSel, acadSel);
							subject_combo.removeAllItems();
							subject_combo.addItem("Select");
							subject_combo.addItem("All");

							String titleList = "";
							Set set = findSubMap.entrySet();
							Iterator n = set.iterator();
							while (n.hasNext()) {
								Map.Entry me = (Map.Entry) n.next();
								LinkedHashMap subjectmap = new LinkedHashMap();
								subjectmap = (LinkedHashMap) me.getValue();
								if (!titleList.contains("," + subjectmap.get("subject_title").toString())) {
									titleList = titleList + "," + subjectmap.get("subject_title");
									subject_combo.addItem(subjectmap.get("subject_title"));
								}
							}
							if (stdSel.equalsIgnoreCase("X") || stdSel.equalsIgnoreCase("XII")) {
								exam_combo.addItem("Final");
							} else {
								exam_combo.removeItem("Final");
								findPanel.add(subject_label);
								findPanel.add(subject_combo);
								findPanel.revalidate();
								findPanel.repaint();
							}
							exam_combo.setSelectedIndex(0);

						} catch (Exception e2) {
							commonObj.logException(e2);
						}
					}
					if (!stdSel.equalsIgnoreCase("IX") && !stdSel.equalsIgnoreCase("X")
							&& !stdSel.equalsIgnoreCase("XI") && !stdSel.equalsIgnoreCase("XII")) {
						exam_combo.removeItem("Final");
						;
					} else if (model.getIndexOf("Final") == -1) {
						exam_combo.addItem("Final");
						;
					}
				} catch (Exception e1) {
					logger.info("Exception insertFormData ===>>>" + e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});

		final JButton submitButton = new JButton("Submit");
		cat_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				subject_combo.setEnabled(false);

				if (((String) cat_combo.getSelectedItem()).equalsIgnoreCase("Topper")) {
					// from_label.setText("From :");
					findPanel.remove(formType_label);
					findPanel.remove(form_combo);
					findPanel.remove(gender_label);
					findPanel.remove(gender_combo);
					subject_combo.setEnabled(true);
					submitButton.setText("Submit");
					findPanel.revalidate();
					findPanel.repaint();
				} else if (((String) cat_combo.getSelectedItem()).equalsIgnoreCase("Marksheet Subjectwise")) {
					// from_label.setText("From :");
					findPanel.remove(formType_label);
					findPanel.remove(form_combo);
					findPanel.remove(gender_label);
					findPanel.remove(gender_combo);
					subject_combo.setEnabled(true);
					submitButton.setText("Open in PDF");
					findPanel.revalidate();
					findPanel.repaint();
				} else if (((String) cat_combo.getSelectedItem()).equalsIgnoreCase("Marks Grade Sheet")
						|| ((String) cat_combo.getSelectedItem()).equalsIgnoreCase("CC Book")) {
					// from_label.setText("From :");
					findPanel.remove(formType_label);
					findPanel.remove(form_combo);
					findPanel.remove(gender_label);
					findPanel.remove(gender_combo);
					subject_combo.setSelectedIndex(0);
					subject_combo.setEnabled(false);
					submitButton.setText("Open in PDF");
					findPanel.revalidate();
					findPanel.repaint();
				} else {
					// div_combo.setSelectedIndex(0);
					div_combo.setEnabled(true);
					findPanel.remove(formType_label);
					findPanel.remove(form_combo);
					findPanel.remove(gender_label);
					findPanel.remove(gender_combo);
					// submitButton.setText("Submit");
					submitButton.setText("Open in PDF");
					findPanel.revalidate();
					findPanel.repaint();
				}

			}
		});

		// /////////////Submit//////////////
		// JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(mainCentre - 150, 90, 150, 25);
		findPanel.add(submitButton);

		/////// remove on load
		findPanel.remove(formType_label);
		findPanel.remove(form_combo);
		findPanel.remove(gender_label);
		findPanel.remove(gender_combo);
		findPanel.revalidate();
		findPanel.repaint();

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean validateFields = true;
				String std = "";
				String div = "";
				String academicSel = "";
				String catType = "";
				String print = "";
				String gender = "";
				String formType = "";
				String exam = "";
				String subject = "";
				String subjectTitle = "";
				int stdInt = 0;
				int listSize = 0;

				std = (String) std_combo.getSelectedItem();
				div = (String) div_combo.getSelectedItem();
				catType = (String) cat_combo.getSelectedItem();
				gender = (String) gender_combo.getSelectedItem();
				academicSel = (String) academicYear_combo.getSelectedItem();
				formType = (String) form_combo.getSelectedItem();
				exam = (String) exam_combo.getSelectedItem() == null ? "" : (String) exam_combo.getSelectedItem();
				subjectTitle = (String) subject_combo.getSelectedItem() == null ? ""
						: (String) subject_combo.getSelectedItem();

				if (std.equalsIgnoreCase("Select")) {
					commonObj.showMessageDialog("Please select std");
					validateFields = false;
				} else if (std.equalsIgnoreCase("All")) {
					std = "";
				} else {
					stdInt = commonObj.RomanToInteger(std);
				}

				if (validateFields && div.equalsIgnoreCase("Select") && !std.equalsIgnoreCase("")) {
					commonObj.showMessageDialog("Please select div");
					validateFields = false;
				} else if (exam.equalsIgnoreCase("Select") || exam.equalsIgnoreCase("")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Exam");
				} else if (validateFields && div.equalsIgnoreCase("All")) {
					div = "";
				}

				if (catType.equalsIgnoreCase("Marksheet Subjectwise") && subjectTitle.equalsIgnoreCase("Select")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Subject");
				} else if (catType.equalsIgnoreCase("Exam Register") && stdInt < 9) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Exam Register report is not valid for less than Std 9th");
				} else if (catType.equalsIgnoreCase("Gradewise Classification") && stdInt > 8) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Gradewise Classification report is not valid for Std " + std);
				} else if (catType.equalsIgnoreCase("Exam Register") && stdInt >= 9
						&& !exam.equalsIgnoreCase("Final")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Exam Register report is valid only for Final exam");
				}

				if (academicSel.equalsIgnoreCase("Select")) {
					academicSel = "";
				}

				LinkedHashMap<String, LinkedHashMap<String, String>> studentHmap = new LinkedHashMap<String, LinkedHashMap<String, String>>();

				if (validateFields) {
					try {
						if (dbValidate.connectDatabase(sessionData)) {
							LinkedHashMap<String, String> leftDataMap = new LinkedHashMap<String, String>();
							leftDataMap = dbValidate.getLeftStudentMap(sessionData, academicSel, std, div);

							LinkedHashMap<String, LinkedHashMap<String, String>> marksSemDataMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
							LinkedHashMap<String, LinkedHashMap<String, String>> studentOptSubAllotMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
							LinkedHashMap<String, LinkedHashMap<String, String>> maxSubMarks = new LinkedHashMap<String, LinkedHashMap<String, String>>();
							LinkedHashMap<String, LinkedHashMap<String, String>> grStudentMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();

							// if(exam.equalsIgnoreCase("Final")){
							// maxSubMarks = dbValidate.getMaxMarksReportForAllSubjects(sessionData, std,
							// academicSel, exam);
							// grStudentMap = dbValidate.printResultWithMarksList(sessionData, academicSel,
							// std, div, exam,
							// section, lastNameClass, firstNameClass, fatherNameClass, leftDataMap);
							// } else{
							// maxSubMarks = dbValidate.findMaxMarksSubTitle(sessionData, "","", std,
							// subjectTitle, academicSel);
							// grStudentMap = dbValidate.printResultList(sessionData, academicSel, std, div,
							// exam, sessionData.getSectionName(), "", "", "", leftDataMap);
							// }
							// studentOptSubAllotMap = dbValidate.getStudentOptSubAllot(sessionData,
							// academicSel, std, div, false);
							// marksSemDataMap = dbValidate.getMarksheetGradewise(sessionData, std,
							// div,academicSel,section,exam,
							// findSubMap, maxSubMarks, studentOptSubAllotMap);

							if (catType.equalsIgnoreCase("Topper")) {
								if (exam.equalsIgnoreCase("Final")) {
									maxSubMarks = dbValidate.getMaxMarksReportForAllSubjects(sessionData, std,
											academicSel, exam);
									grStudentMap = dbValidate.printResultWithMarksList(sessionData, academicSel, std,
											div, exam, section, lastNameClass, firstNameClass, fatherNameClass,
											leftDataMap);
								} else {
									maxSubMarks = dbValidate.findMaxMarksSubTitle(sessionData, "", "", std,
											subjectTitle, academicSel);
									grStudentMap = dbValidate.printResultList(sessionData, academicSel, std, div, exam,
											sessionData.getSectionName(), "", "", "", leftDataMap);
								}
								studentOptSubAllotMap = dbValidate.getStudentOptSubAllot(sessionData, academicSel, std,
										div, false);
								if (subjectTitle.equalsIgnoreCase("All")) {
									marksSemDataMap = grStudentMap;
								} else if(!subjectTitle.equalsIgnoreCase("All") && !exam.equalsIgnoreCase("Final")) {
									marksSemDataMap = dbValidate.getMarksheetGradewise(sessionData, std, div,
											academicSel, section, exam, findSubMap, maxSubMarks, 
											studentOptSubAllotMap, grStudentMap);
								} else {
									marksSemDataMap = grStudentMap;
								}

								studentHmap = dbValidate.topperReportList(sessionData, std, div, academicSel, section,
										catType, print, exam, "DESC", subjectTitle, marksSemDataMap);
								listSize = studentHmap.size();

								if (listSize > 1) {
									frame.setVisible(false);
									new Reports(sessionData, std, div, academicSel, section, user_name, user_role,
											studentHmap, catType, exam, subjectTitle);
								} else {
									JOptionPane.showMessageDialog(null, "No data found");
								}
							} else if (catType.equalsIgnoreCase("Bottom 10")) {
								studentHmap = dbValidate.topperReportList(sessionData, std, div, academicSel, section,
										catType, print, exam, "ASC", subjectTitle, marksSemDataMap);
								listSize = studentHmap.size();

								if (listSize > 1) {
									frame.setVisible(false);
									new Reports(sessionData, std, div, academicSel, section, user_name, user_role,
											studentHmap, catType, exam, subjectTitle);
								} else {
									JOptionPane.showMessageDialog(null, "No data found");
								}
							} else if (catType.equalsIgnoreCase("Marksheet Subjectwise")) {
								maxSubMarks = dbValidate.findMaxMarksSubTitle(sessionData, "", "", std, subjectTitle,
										academicSel);
								marksSemDataMap = dbValidate.getMarksheetSubjectwise(sessionData, std, div, academicSel,
										section, "", exam, subjectTitle, findSubMap, maxSubMarks, null,
										"Marksheet Subjectwise");//leftDataMap set as null as IV all students created LC
								studentOptSubAllotMap = dbValidate.getStudentOptSubAllot(sessionData, academicSel, std,
										div, false);
								listSize = marksSemDataMap.size();

								if (stdInt < 9 && !subjectTitle.equalsIgnoreCase("All")) {
									new MarksheetSubjectwise_PDF(sessionData, exam, subjectTitle, std, div, academicSel,
											marksSemDataMap, studentOptSubAllotMap, maxSubMarks, null);//leftDataMap set as null as IV all students created LC
								} else if(!subjectTitle.equalsIgnoreCase("All")) {
									new MarksheetSubjectwise_IX_PDF(sessionData, exam, subjectTitle, std, div,
											academicSel, marksSemDataMap, studentOptSubAllotMap, maxSubMarks,
											null);//leftDataMap set as null as IV all students created LC
								} else if(subjectTitle.equalsIgnoreCase("All")){
									JOptionPane.showMessageDialog(null, "Please select subject to get Marksheet Subjectwise");
								}

							} else if (catType.equalsIgnoreCase("Marks Sheet")) {
								if (exam.equalsIgnoreCase("Final")) {
									maxSubMarks = dbValidate.getMaxMarksReportForAllSubjects(sessionData, std,
											academicSel, exam);
									grStudentMap = dbValidate.printResultWithMarksList(sessionData, academicSel, std,
											div, exam, section, lastNameClass, firstNameClass, fatherNameClass,
											null);//leftDataMap set as null as IV all students created LC
								} else {
									maxSubMarks = dbValidate.findMaxMarksSubTitle(sessionData, "", "", std,
											subjectTitle, academicSel);
									grStudentMap = dbValidate.printResultList(sessionData, academicSel, std, div, exam,
											sessionData.getSectionName(), "", "", "", null);//leftDataMap set as null as IV all students created LC
								}
								studentOptSubAllotMap = dbValidate.getStudentOptSubAllot(sessionData, academicSel, std,
										div, false);
								marksSemDataMap = dbValidate.getMarksheetGradewise(sessionData, std, div, academicSel,
										section, exam, findSubMap, maxSubMarks, studentOptSubAllotMap, grStudentMap);
								listSize = marksSemDataMap.size();
								if (stdInt > 0 && stdInt < 9) {
									new MarksGradeSheet_PDF(sessionData, exam, subjectTitle, std, div, academicSel,
											marksSemDataMap, studentOptSubAllotMap, maxSubMarks, grStudentMap,
											null);//leftDataMap set as null as IV all students created LC
								} else if(stdInt >= 9){
									new MarksSheet_IX_PDF(sessionData, exam, subjectTitle, std, div, academicSel,
											marksSemDataMap, studentOptSubAllotMap, maxSubMarks, grStudentMap,
											null);//leftDataMap set as null as IV all students created LC
								} else {
									new MarksSheet_PPR_PDF(sessionData, exam, subjectTitle, std, div, academicSel,
											marksSemDataMap, studentOptSubAllotMap, maxSubMarks, grStudentMap,
											null);//leftDataMap set as null as IV all students created LC
								}
							} else if (catType.equalsIgnoreCase("CC Book")) {
								maxSubMarks = dbValidate.findMaxMarksSubTitle(sessionData, "", "", std, "",
										academicSel);
								studentOptSubAllotMap = dbValidate.getStudentOptSubAllot(sessionData, academicSel, std,
										div, false);
								marksSemDataMap = dbValidate.getMarksAllSubject(sessionData, std, div, academicSel,
										section, exam, findSubMap, maxSubMarks);
								listSize = marksSemDataMap.size();
								new CC_Book_PDF(sessionData, exam, subjectTitle, std, div, academicSel, marksSemDataMap,
										studentOptSubAllotMap, maxSubMarks);
							} else if (catType.equalsIgnoreCase("Categorywise")) {
								LinkedHashMap<String, Integer> categorywiseReportMap = new LinkedHashMap<String, Integer>();
								categorywiseReportMap = dbValidate.getCategorywiseReport(sessionData, academicSel, std,
										div, exam, "CATEGORY");
								categorywiseReportMap = dbValidate.getLeftStudentReport(sessionData, academicSel, std,
										div, exam, "CATEGORY", categorywiseReportMap);
								listSize = categorywiseReportMap.size();
								ArrayList<String> categoryList = new ArrayList<String>();
								// categoryList = dbValidate.getCategoryList(sessionData, academicSel, std, div,
								// exam);
								String[] category_names = bundle.getString("CATEGORY_NAMES").split(",");
								for (int i = 0; i < category_names.length; i++) {
									categoryList.add(category_names[i]);
								}
								new CategorywiseReport_PDF(sessionData, exam, subjectTitle, std, div, academicSel,
										categorywiseReportMap, categoryList, "CATEGORYWISE");
							} else if (catType.equalsIgnoreCase("Religionwise")) {
								LinkedHashMap<String, Integer> categorywiseReportMap = new LinkedHashMap<String, Integer>();
								categorywiseReportMap = dbValidate.getCategorywiseReport(sessionData, academicSel, std,
										div, exam, "RELIGION");
								categorywiseReportMap = dbValidate.getLeftStudentReport(sessionData, academicSel, std,
										div, exam, "RELIGION", categorywiseReportMap);
								listSize = categorywiseReportMap.size();
								ArrayList<String> categoryList = new ArrayList<String>();
								String[] religion_list = bundle.getString("RELIGION_LIST").split(",");
								for (int i = 0; i < religion_list.length; i++) {
									categoryList.add(religion_list[i]);
								}

								new CategorywiseReport_PDF(sessionData, exam, subjectTitle, std, div, academicSel,
										categorywiseReportMap, categoryList, "RELIGIONWISE");
							} else if (catType.equalsIgnoreCase("Subjectwise")) {
								LinkedHashMap<String, Integer> categorywiseReportMap = new LinkedHashMap<String, Integer>();
								categorywiseReportMap = dbValidate.getCategorywiseReport(sessionData, academicSel, std,
										div, exam, "Subject");
								categorywiseReportMap = dbValidate.getLeftStudentReport(sessionData, academicSel, std,
										div, exam, "Subject", categorywiseReportMap);
								listSize = categorywiseReportMap.size();
								ArrayList<String> categoryList = new ArrayList<String>();
								categoryList = (ArrayList<String>) dbValidate.findSubjectTitleList(sessionData, std, "",
										academicSel);
								categoryList.remove(0);
								categoryList.remove(0);

								new CategorywiseReport_PDF(sessionData, exam, subjectTitle, std, div, academicSel,
										categorywiseReportMap, categoryList, "SUBJECTWISE");
							} else if (catType.equalsIgnoreCase("Progress Report")) {
								if (stdInt < 9) {
									LinkedHashMap<String, LinkedHashMap<String, String>> attendanceMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
									JFrame f = new JFrame("Progress Report downlaod in progress. Please Don't Close");
									f.setBounds(screenWidth / 2 - 150, screenHeight / 2, 90, 25);
									f.setSize(600, 0);
									f.setResizable(false);
									f.setVisible(true);
									f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

									String sem = "";
									if (exam.equalsIgnoreCase("Semester 2")) {
										sem = "SEM2";
									} else {
										sem = "SEM1";
									}

									if (exam.equalsIgnoreCase("Final")) {
										maxSubMarks = dbValidate.getMaxMarksReportForAllSubjects(sessionData, std,
												academicSel, exam);
										grStudentMap = dbValidate.printResultWithMarksList(sessionData, academicSel,
												std, div, exam, section, lastNameClass, firstNameClass, fatherNameClass,
												null);//leftDataMap set as null as IV all students created LC
									} else {
										maxSubMarks = dbValidate.findMaxMarksSubTitle(sessionData, "", "", std,
												subjectTitle, academicSel);
										grStudentMap = dbValidate.printResultList(sessionData, academicSel, std, div,
												exam, sessionData.getSectionName(), "", "", "", null);//leftDataMap set as null as IV all students created LC
									}
									if (maxSubMarks.size() <= 10) {
										String space = " ";
										int addCount = 10 - maxSubMarks.size();
										for (int i = 0; i < addCount; i++) {
											maxSubMarks.put(space, null);
											space = space + " ";
										}
									}
									studentOptSubAllotMap = dbValidate.getStudentOptSubAllot(sessionData, academicSel,
											std, div, false);
									marksSemDataMap = dbValidate.getMarksheetGradewise(sessionData, std, div,
											academicSel, section, exam, findSubMap, maxSubMarks, studentOptSubAllotMap, grStudentMap);
									attendanceMap = dbValidate.getAttendanceMap(sessionData, std, div, academicSel,
											sem);
									listSize = marksSemDataMap.size();
									f.setVisible(false);
									new ProgressReport_PDF(sessionData, exam, subjectTitle, std, div, academicSel,
											marksSemDataMap, studentOptSubAllotMap, maxSubMarks, grStudentMap,
											leftDataMap, attendanceMap);
								} else {
									JOptionPane.showMessageDialog(null,
											"Progress report is available only till std 8th");
								}
							} else if (catType.equalsIgnoreCase("Exam Register")) {
								LinkedHashMap<String, LinkedHashMap<String, String>> marksSubjectcDataMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
								subjectTitle = "";

								if (exam.equalsIgnoreCase("Final")) {
									maxSubMarks = dbValidate.findMaxMarksSubTitle(sessionData, "", "", std,
											subjectTitle, academicSel);
									marksSubjectcDataMap = dbValidate.getMarksheetSubjectwise(sessionData, std, div,
											academicSel, section, "", exam, subjectTitle, findSubMap, maxSubMarks,
											leftDataMap, "Exam Register");
									grStudentMap = dbValidate.printResultWithMarksList(sessionData, academicSel, std,
											div, exam, section, lastNameClass, firstNameClass, fatherNameClass,
											leftDataMap);
									studentOptSubAllotMap = dbValidate.getStudentOptSubAllot(sessionData, academicSel,
											std, div, false);
									marksSemDataMap = dbValidate.getMarksheetGradewise(sessionData, std, div,
											academicSel, section, exam, findSubMap, maxSubMarks, studentOptSubAllotMap, grStudentMap);
									listSize = marksSemDataMap.size();

									new ExamRegister_IX_PDF(sessionData, exam, subjectTitle, std, div, academicSel,
											marksSemDataMap, studentOptSubAllotMap, maxSubMarks, grStudentMap,
											leftDataMap, marksSubjectcDataMap, findSubMap, "Exam Register");
								} else {
									JOptionPane.showMessageDialog(null,
											"Exam Register report is only valid for Final Exam");
								}
							} else if (catType.equalsIgnoreCase("Exam Register-Fail")) {
								LinkedHashMap<String, LinkedHashMap<String, String>> marksSubjectcDataMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
								subjectTitle = "";

								if (exam.equalsIgnoreCase("Final")) {
									maxSubMarks = dbValidate.findMaxMarksSubTitle(sessionData, "", "", std,
											subjectTitle, academicSel);
									marksSubjectcDataMap = dbValidate.getMarksheetSubjectwise(sessionData, std, div,
											academicSel, section, "", exam, subjectTitle, findSubMap, maxSubMarks,
											leftDataMap, "Exam Register");
									grStudentMap = dbValidate.printResultWithMarksList(sessionData, academicSel, std,
											div, exam, section, lastNameClass, firstNameClass, fatherNameClass,
											leftDataMap);
									studentOptSubAllotMap = dbValidate.getStudentOptSubAllot(sessionData, academicSel,
											std, div, false);
									marksSemDataMap = dbValidate.getMarksheetGradewise(sessionData, std, div,
											academicSel, section, exam, findSubMap, maxSubMarks, studentOptSubAllotMap, grStudentMap);
									listSize = marksSemDataMap.size();

									new ExamRegister_IX_PDF(sessionData, exam, subjectTitle, std, div, academicSel,
											marksSemDataMap, studentOptSubAllotMap, maxSubMarks, grStudentMap,
											leftDataMap, marksSubjectcDataMap, findSubMap, "Exam Register-Fail");
								} else {
									JOptionPane.showMessageDialog(null,
											"Exam Register report is only valid for Final Exam");
								}
							} else if (catType.equalsIgnoreCase("Gradewise Classification")) {
								LinkedHashMap<String, LinkedHashMap<String, Integer>> grStudentMapGrade = new LinkedHashMap<String, LinkedHashMap<String, Integer>>();
								String stdStr = bundle.getString(section.toUpperCase() + "_STD");
								if (std.equalsIgnoreCase("")) {
									stdStr = stdStr.replace(",IX", "");
									stdStr = stdStr.replace(",X", "");
									stdStr = "'" + stdStr.replace(",", "','") + "'";
								} else {
									stdStr = "'" + std + "'";
								}
								grStudentMapGrade = dbValidate.gradewiseClassification(sessionData, academicSel, std,
										div, exam, section, stdStr);
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
				LinkedHashMap studentHmap = new LinkedHashMap();
				frame.setVisible(false);
				// new Strength(sessionData, "", "", "", section, user_name, user_role,
				// studList1, "");
				new Reports(sessionData, "", "", "", section, user_name, user_role, studentHmap, "", "", "");
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
				// change height to change scrolling area
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

		// String[] dataArray = new String[listSize];
		// dataArray = foundStudentList.toArray(dataArray);
		// logger.info("listSize === " + listSize);
		// logger.info("dataArray === " + dataArray.length);

		if (listSize > 0) {

			int j = 0;
			int l = 0;
			JLabel[] pipe_labels0 = new JLabel[listSize];
			JLabel[] stdpipe_labels = new JLabel[listSize];
			JLabel[] pipe_labels1 = new JLabel[listSize];
			JLabel[] line_labels = new JLabel[listSize];

			JLabel[] pipe_labels2 = new JLabel[listSize];
			JLabel[] pipe_labels3 = new JLabel[listSize];
			JLabel[] pipe_labels4 = new JLabel[listSize];
			JLabel[] pipe_labels5 = new JLabel[listSize];
			JLabel[] pipe_labels6 = new JLabel[listSize];

			JLabel[] temp0_labels = new JLabel[listSize];
			JLabel[] tempstd_labels = new JLabel[listSize];
			JLabel[] temp1_labels = new JLabel[listSize];
			JLabel[] temp2_labels = new JLabel[listSize];
			JLabel[] temp3_labels = new JLabel[listSize];
			JLabel[] temp4_labels = new JLabel[listSize];
			JLabel[] temp5_labels = new JLabel[listSize];

			String line1 = "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

			int k = 0;
			int i = 0;
			Set set = foundStudentList.entrySet();
			Iterator n = set.iterator();

			int width = 40;
			String srNo = "", std = "", div = "";
			String rNo = "";
			String grNo = "";
			String name = "";
			String percent = "";
			String marks = "";
			LinkedHashMap studenthmap = new LinkedHashMap();
			while (n.hasNext()) {
				j = j + 30;
				k = j;
				l = j + 50;
				int tokenSize = 0;
				int m = 0;
				width = 40;

				Map.Entry me = (Map.Entry) n.next();
				studenthmap = (LinkedHashMap) me.getValue();
				tokenSize = studenthmap.size();
				// srNo = (i+1)+""; //studenthmap.get("srNo") + "";
				srNo = studenthmap.get("srNo") + "";
				std = (String) studenthmap.get("std");
				div = studenthmap.get("div") == null ? "" : studenthmap.get("div").toString();
				rNo = (String) studenthmap.get("rollNo");
				grNo = (String) studenthmap.get("grNo");
				name = (String) studenthmap.get("name");
				percent = (String) studenthmap.get("percent");
				marks = (String) studenthmap.get("marks");
				if (category.equalsIgnoreCase("topper") && !subjectClass.equalsIgnoreCase("")) {
					percent = (String) studenthmap.get("marks");
				}

				pipe_labels0[i] = new JLabel("|");
				pipe_labels0[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels0[i].setBounds(width, j, 120, 50);
				dataPanel.add(pipe_labels0[i]);

				// column 1
				width = width + 10;
				temp0_labels[i] = new JLabel(srNo);
				temp0_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				temp0_labels[i].setBounds(width, j, 80, 50);
				dataPanel.add(temp0_labels[i]);

				width = width + 40;
				stdpipe_labels[i] = new JLabel("|");
				stdpipe_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				stdpipe_labels[i].setBounds(width, j, 120, 50);
				dataPanel.add(stdpipe_labels[i]);

				// column 2
				width = width + 10;
				tempstd_labels[i] = new JLabel(std + " " + div);
				tempstd_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				tempstd_labels[i].setBounds(width, j, 80, 50);
				dataPanel.add(tempstd_labels[i]);

				width = width + 60;
				pipe_labels1[i] = new JLabel("|");
				pipe_labels1[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels1[i].setBounds(width, j, 120, 50);
				dataPanel.add(pipe_labels1[i]);

				// column 4
				width = width + 20;
				temp1_labels[i] = new JLabel(rNo);
				temp1_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				temp1_labels[i].setBounds(width, j, 80, 50);
				dataPanel.add(temp1_labels[i]);

				width = width + 60;
				pipe_labels2[i] = new JLabel("|");
				pipe_labels2[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels2[i].setBounds(width, j, 120, 50);
				dataPanel.add(pipe_labels2[i]);

				// column 5
				width = width + 20;
				temp2_labels[i] = new JLabel(grNo);
				temp2_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				temp2_labels[i].setBounds(width, j, 60, 50);
				temp2_labels[i].setToolTipText(grNo);
				dataPanel.add(temp2_labels[i]);

				width = width + 60;
				pipe_labels3[i] = new JLabel("|");
				pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels3[i].setBounds(width, j, 120, 50);
				dataPanel.add(pipe_labels3[i]);

				// column 6
				width = width + 20;
				temp3_labels[i] = new JLabel(name);
				temp3_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				temp3_labels[i].setBounds(width, j + 12, 380, 20);
				temp3_labels[i].setToolTipText(name);
				dataPanel.add(temp3_labels[i]);

				width = width + 370;
				pipe_labels4[i] = new JLabel("|");
				pipe_labels4[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels4[i].setBounds(width, j, 120, 50);
				dataPanel.add(pipe_labels4[i]);

				// column 7
				if (tokenSize >= 4) {
					width = width + 20;
					temp4_labels[i] = new JLabel(percent);
					temp4_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					temp4_labels[i].setBounds(width, j + 12, 270, 20);
					temp4_labels[i].setToolTipText(marks);
					dataPanel.add(temp4_labels[i]);

					width = width + 270;
					pipe_labels5[i] = new JLabel("|");
					pipe_labels5[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels5[i].setBounds(width, j, 120, 50);
					dataPanel.add(pipe_labels5[i]);
				}

				// column 5
				/*
				 * if(tokenSize >= 5){ width = width +20; temp5_labels[i] = new
				 * JLabel(columnArray[4]); temp5_labels[i].setFont(new Font("Book Antiqua",
				 * Font.BOLD, 16)); temp5_labels[i].setBounds(width, j + 12, 130, 20);
				 * temp5_labels[i].setToolTipText(columnArray[4]);
				 * dataPanel.add(temp5_labels[i]);
				 * 
				 * width = width +200; pipe_labels6[i] = new JLabel("|");
				 * pipe_labels6[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				 * pipe_labels6[i].setBounds(width, j, 120, 50); dataPanel.add(pipe_labels6[i]);
				 * }
				 */

				line_labels[i] = new JLabel(line1);
				line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				line_labels[i].setBounds(40, j + 10, 1100, 50);
				dataPanel.add(line_labels[i]);

				i++;
			}
			// /////////////Print//////////////
			JButton printButton = new JButton("Open in Excel");
			printButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			printButton.setBounds(mainCentre - 50, k + 60, 160, 25);
			// dataPanel.add(printButton);

			printButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					boolean validateFields = true;
					String std = "";
					String div = "";
					String academicSel = "";
					String catType = "";
					String gender = (String) gender_combo.getSelectedItem();
					String print = "PRINT";
					List<String> printList = null;
					String formType = (String) form_combo.getSelectedItem();

					std = stdClass;
					div = divClass;
					catType = category;
					academicSel = academicYearClass;

					if (std.equalsIgnoreCase("Select") || std.equalsIgnoreCase("All")) {
						std = "";
					}
					if (div.equalsIgnoreCase("Select") || div.equalsIgnoreCase("All")) {
						div = "";
					}
					if (academicSel.equalsIgnoreCase("Select")) {
						academicSel = "";
					}

					try {
						if (dbValidate.connectDatabase(sessionData)) {
							if (catType.equalsIgnoreCase("Category Wise")) {
								printList = dbValidate.categoryPrintList(sessionData, std, div, academicSel, section,
										catType, print, "");
							} else if (catType.equalsIgnoreCase("Religion Wise")) {
								printList = dbValidate.religionPrintList(sessionData, std, div, academicSel, section,
										catType, print, "");
							} else if (catType.equalsIgnoreCase("General")) {
								printList = dbValidate.generalPrintList(sessionData, std, div, academicSel, section,
										catType, print, "");
							} else if (catType.equalsIgnoreCase("AB Form")) {
								printList = dbValidate.abFormPrintList(sessionData, std, div, academicSel, section,
										catType, print, gender, formType, "", "");
							} else if (catType.equalsIgnoreCase("New Admissions")) {
								printList = dbValidate.newAdmissionList(sessionData, std, div, academicSel, section,
										catType, print, "");
							}

							int listSize = printList.size();
							logger.info("No of students found :: " + listSize);

							String passStd = (String) std_combo.getSelectedItem();
							String passDiv = (String) div_combo.getSelectedItem();
							String passYear = (String) academicYear_combo.getSelectedItem();

							/*
							 * if (listSize > 1 && !print.equalsIgnoreCase("PRINT")) {
							 * frame.setVisible(false); // new Strength(sessionData, passStd, passDiv,
							 * passYear, section, user_name, user_role, printList, catType); new
							 * Reports(sessionData, passStd, passDiv, passYear, section, user_name,
							 * user_role, printList, catType, examClass); } else if
							 * (!print.equalsIgnoreCase("PRINT")) { JOptionPane.showMessageDialog(null,
							 * "No data found"); }
							 */
						}
					} catch (Exception e1) {
						logger.info("Exception PrintList ===>>>" + e1);
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
