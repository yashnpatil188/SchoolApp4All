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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
// import com.lowagie.text.DocumentException;
// import com.lowagie.text.pdf.TextField;
import org.com.accesser.SessionData;

public class StudentSubAllot extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JFrame frame = null;

	static JComboBox studentcombo;

	static int retCount = 0;

	static int scrollHeight = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

	static List<String> subjectList = new ArrayList<String>();

	static List<String> findSubList = new ArrayList<String>();

	static List<String> findSubTitleList = new ArrayList<String>();

	static boolean setSelected = false;

	static String stdClass = "";

	static String academicYearClass = "";

	static String category = "";

	static String divClass = "";

	static String selectStd = "";

	static String selectDiv = "Select";

	static String selectAcademic = "Year";

	static String compOptClass = "";

	static String subTitleClass = "";

	static String selAllClass = "Select";

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

	static Logger logger = Logger.getLogger(StudentSubAllot.class.getName());

	public StudentSubAllot(SessionData sessionData1, String retStd, List<String> retSubjectList, String academicYear,
			String retCategory, boolean retSelected, String retDiv, String sec, String retCompOpt, String retSubTitle,
			String retSelAll, String retUserName, String retUserRole) {

		logger.info("=============StudentSubAllot=================");
		System.gc();
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		selectStd = "Select";
		selectDiv = "Select";
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

		subTitleClass = retSubTitle;
		logger.info("subTitleClass :: " + subTitleClass);
		logger.info("retSelAll :: " + retSelAll);
		String todayDate = commonObj.getCurrentDate();
		academicYearClass = academicYear;

		if (!retSelAll.equalsIgnoreCase("")) {
			selAllClass = retSelAll;
		} else {
			selAllClass = "Select";
		}
		logger.info("selAllClass :: " + selAllClass);

		compOptClass = retCompOpt;
		stdClass = retStd;
		if (!retStd.equalsIgnoreCase("")) {
			selectStd = retStd;
		}
		divClass = retDiv;
		if (!retDiv.equalsIgnoreCase("")) {
			selectDiv = retDiv;
		}
		// academicYearClass = academicYear;
		selectAcademic = academicYear;
		if (!academicYear.equalsIgnoreCase("") && !academicYear.equalsIgnoreCase("Year")) {
			selectAcademic = academicYear;
		}
		logger.info("stdClass == " + stdClass);
		logger.info("divClass == " + divClass);
		logger.info("academicYearClass == " + academicYearClass);

		// if(compOptClass.equalsIgnoreCase("Optional")){
		try {
			if(dbValidate.connectDatabase(sessionData)){
				findSubTitleList.clear();
				if (!subTitleClass.equalsIgnoreCase("") && !subTitleClass.equalsIgnoreCase("Select Subject Title")) {
					findSubTitleList.add(subTitleClass);
				}
	
				findSubTitleList.addAll(dbValidate.findSubjectTitleList(sessionData, stdClass, "YES", academicYearClass));
				findSubTitleList.remove(findSubTitleList.indexOf("CREATE NEW TITLE"));
				logger.info("findSubTitleList size 111== " + findSubTitleList.size());
				if (!retSubTitle.equalsIgnoreCase("SELECT SUBJECT TITLE") && findSubTitleList.size() > 1) {
					retSubjectList = dbValidate.findStudentSubjectAllot(sessionData, academicYear, retStd, retDiv, subTitleClass,	retSelAll);
					logger.info("retSubjectList size 222 == " + retSubjectList.size());
				}
				else {
					JOptionPane.showMessageDialog(null, "No optional subject found");
				}
			}
		} catch (Exception e) {
			logger.info("Exception findSubTitleList = " + e);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
		// }
		// else if(compOptClass.equalsIgnoreCase("Compulsory")){
		// try {
		// dbValidate.allotCompulsorySub(academicYearClass, stdClass,
		// divClass, section, "");
		// } catch (Exception e) {
		// logger.info("allotCompulsorySub Exception == "+e);
		// }
		// }

		ArrayList<String> arrlist = null;
		arrlist = new ArrayList<String>(retSubjectList);
		logger.info("arrlist => " + arrlist.size());

		logger.info("retSubjectList size = " + retSubjectList.size());
		subjectList = arrlist;

		logger.info("subjectList size = " + subjectList.size());

		// stdClass = retStd;
		// academicYearClass = academicYear;
		category = retCategory;
		// divClass = retDiv;
		logger.info(stdClass + "::" + academicYearClass + "::" + category + "::" + retSelected + "::" + divClass);

		entrytCnt = 0;
		logger.info("entrytCnt===>" + entrytCnt);

		scrollHeight = (subjectList.size() - 6) * 30; // to adjust the perfect
														// scroll height
		if (scrollHeight < 0)
			scrollHeight = 0;

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

	// public SubjectAllotment(String string, String string2, String string3,
	// String string4, String string5, String string6, Object object,
	// boolean b, String string7, String string8, String string9) {
	// // TODO Auto-generated constructor stub
	// }

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
				Dimension size = new Dimension(screenWidth - 156, 150);// change
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
		JLabel academic_label = new JLabel("Academic Year :");
		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academic_label.setBounds(mainCentre - 340, 00, 150, 50);
		findPanel.add(academic_label);
		
		// JLabel year_label = new JLabel(academicYearClass);
		// year_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// year_label.setBounds(mainCentre-200, 12, 120, 25);
		// findPanel.add(year_label);

		String todayDate = commonObj.getCurrentDate();
		String currentAcademic = commonObj.getAcademicYear(todayDate);
		String yearList = currentAcademic + "," + Common.getPreviousYear(currentAcademic);
		String academicYearList[] = yearList.split(",");
		
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academicYear_combo.setBounds(mainCentre - 200, 12, 120, 25);
		if(!academicYearClass.equalsIgnoreCase("")){
			academicYear_combo.setSelectedItem(academicYearClass);
		}
		findPanel.add(academicYear_combo);
		// //////////////////////////////////
		// /////////////Std//////////////
		JLabel admittedStd_label = new JLabel("Std :");
		admittedStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_label.setBounds(mainCentre - 50, 00, 70, 50);
		findPanel.add(admittedStd_label);

		std = selectStd + "," + std;
		String[] stdList = std.split(",");
		// String admittedStdList[] =
		// {selectStd,"IV","V","VI","VII","VIII","IX","X"};
		final JComboBox admittedStd_combo = new JComboBox(stdList);
		admittedStd_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_combo.setBounds(mainCentre, 12, 90, 25);
		findPanel.add(admittedStd_combo);

		// /////////////Div//////////////
		JLabel presentDiv_label = new JLabel("Div :");
		presentDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		presentDiv_label.setBounds(mainCentre + 120, 00, 70, 50);
		findPanel.add(presentDiv_label);

		div = selectDiv + "," + div;
		String[] divList = div.split(",");
		// String presentDivList[] = {selectDiv,"A","B","C","D","E"};
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
		semester_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		semester_combo.setBounds(mainCentre + 370, 12, 120, 25);

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

		// /////////////Default/Optional //////////////
		// String compOptionalList[] = {"Select","Compulsory","Optional"};
		// final JComboBox compOptional_combo = new JComboBox(compOptionalList);
		// compOptional_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// compOptional_combo.setBounds(mainCentre+300, 52, 120, 25);
		// findPanel.add(compOptional_combo);

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
		
		admittedStd_combo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
					String stdSel = (String) admittedStd_combo.getSelectedItem();
					String acadSel = (String) academicYear_combo.getSelectedItem();
					boolean marksAllotRadio = allotSubject_radio.isSelected();
					if(!stdSel.equalsIgnoreCase("All") && !stdSel.equalsIgnoreCase("Select") && dbValidate.connectDatabase(sessionData)){
						presentDiv_combo.removeAllItems();
						presentDiv_combo.addItem("Select");
						String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdSel, section, 
								"PRESENT_DIV", "PRESENT_STD", "class_allotment",acadSel);
						
						for (String retval: divAvailabe.split(",")) {
							presentDiv_combo.addItem(retval);
						}
					}
					int stdInt = commonObj.RomanToInteger(stdSel);
					if(stdInt <= 10 && marksAllotRadio){
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
		allotSubject_radio.setSelected(false);
		studentSubject_radio.setSelected(true);
		presentDiv_combo.setEnabled(true);

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
					if(stdInt <= 10){
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
				academicSel = (String) academicYear_combo.getSelectedItem();
				div = (String) presentDiv_combo.getSelectedItem();
				semester = (String) semester_combo.getSelectedItem();

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

				if (academicSel.equalsIgnoreCase("Year")) {
					academicSel = "";
				}
				logger.info("academicSel == " + academicSel);
				logger.info("validateFields flag..." + validateFields);

				if (validateFields & category.equalsIgnoreCase("Create Subject")) {
					try {
						stdClass = std;

						frame.setVisible(false);
						new CreateSubject(sessionData, category, stdClass, section, user_name, user_role, academicSel);

					} catch (Exception e1) {
						logger.info("Exception e1 ===>>>" + e1);
						new Student(sessionData, section, user_name, user_role);
						frame.setVisible(false);
					}
				} else if (validateFields & category.equalsIgnoreCase("Allot Subject")) {
					try {
						stdClass = std;
						List<String> subList = new ArrayList<String>();

						frame.setVisible(false);
						int stdInt = commonObj.RomanToInteger(std);
						if(stdInt >= 11){
							new MarksAllotment(sessionData, std, subList, academicSel, category, false, section,
									user_name, user_role, "");
						} else{
							new MarksAllotmentNew(sessionData, std, subList, academicSel, category, false, section,
									user_name, user_role, semester);
						}

					} catch (Exception e1) {
						logger.info("Exception e1 ===>>>" + e1);
						new Student(sessionData, section, user_name, user_role);
						frame.setVisible(false);
					}
				} else if (validateFields & category.equalsIgnoreCase("Student Subject Allotment")) {
					try {
						stdClass = std;
						// academicYearClass = academicSel;
						divClass = div;
						// category = category;
						List<String> subList = new ArrayList<String>();

						frame.setVisible(false);
						new StudentSubAllot(sessionData, stdClass, subList, academicSel, category, false,
								divClass, section, "", "", "", user_name, user_role);

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
				List<String> findLCList = new ArrayList<String>();
				frame.setVisible(false);
				new StudentSubAllot(sessionData, "", findLCList, "", category, false, "", section, "", "", "",
						user_name, user_role);
				// new ClassAllotment("","", "", "", "", "", findLCList,
				// false,"","","",section);
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
		int listSize = subjectList.size();
		logger.info("subjectList size in if ==>" + listSize);
		// if(listSize == 0 && !stdClass.equalsIgnoreCase("") &&
		// !academicYearClass.equalsIgnoreCase("") &&
		// !category.equalsIgnoreCase("")){
		// logger.info("Add new subject line........");
		// subjectList.add(stdClass+"|"+"New Subject"+"||"+"SELECT
		// TITLE"+"|||"+"New Subject Title"+"||||"+"Marks"+"|||||"+"No");
		// }

		if (subjectList.size() > 0) {
			final JRadioButton all_radio = new JRadioButton();
			all_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			all_radio.setBounds(40, 13, 20, 20);
			all_radio.setSelected(true);
			all_radio.setEnabled(false);
			dataPanel.add(all_radio);
	
			JLabel std_label = new JLabel("Roll No.");
			std_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			std_label.setBounds(70, 00, 120, 50);
			dataPanel.add(std_label);
	
			JLabel pipe_label1 = new JLabel("|");
			pipe_label1.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label1.setBounds(150, 00, 120, 50);
			dataPanel.add(pipe_label1);
	
			JLabel sub_label = new JLabel("Student Name");
			sub_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sub_label.setBounds(170, 00, 150, 50);
			dataPanel.add(sub_label);
	
			// JLabel pipe_label2 = new JLabel("|");
			// pipe_label2.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			// pipe_label2.setBounds(500, 00, 120, 50);
			// dataPanel.add(pipe_label2);
	
			// JLabel fut_label = new JLabel("Subject Title");
			// fut_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			// fut_label.setBounds(500, 00, 200, 50);
			// dataPanel.add(fut_label);
	
			boolean enableFlag = false;
			List<String> SubTitleList = new ArrayList<String>();
	
			try {
				// SubTitleList.add("Select Subject Title");
				SubTitleList.addAll(findSubTitleList);
				enableFlag = true;
			} catch (Exception e1) {
				logger.info("Exception e1 = " + e1);
			}
			String[] subTitleArr = SubTitleList.toArray(new String[SubTitleList.size()]);
			// /////////////Default/Optional //////////////
			final JComboBox subTitle_combo1 = new JComboBox(subTitleArr);
			subTitle_combo1.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			subTitle_combo1.setBounds(570, 12, 250, 25);
			dataPanel.add(subTitle_combo1);
	
			subTitle_combo1.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent e) {
	
					try {
						String selSubTitle = (String) subTitle_combo1.getSelectedItem();
						logger.info("selSubTitle ::" + selSubTitle);
						if (!selSubTitle.equalsIgnoreCase("SELECT SUBJECT TITLE") && !selSubTitle.equalsIgnoreCase("")) {
							frame.setVisible(false);
							List<String> subList = new ArrayList<String>();
							new StudentSubAllot(sessionData, stdClass, subList, academicYearClass, category, false,
									divClass, section, compOptClass, selSubTitle, "", user_name, user_role);
						} else {
							JOptionPane.showMessageDialog(null, "Please Select Subject Title");
						}
					} catch (Exception e1) {
						logger.info("subTitle_combo1 Exception e1 = " + e1);
					}
				}
			});
			// JLabel pipe_label4 = new JLabel("|");
			// pipe_label4.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			// pipe_label4.setBounds(830, 00, 120, 50);
			// dataPanel.add(pipe_label4);
	
			// final JRadioButton updateAll_radio = new JRadioButton();
			// updateAll_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			// updateAll_radio.setBounds(810, 12, 20, 20);
			// dataPanel.add(updateAll_radio);
	
			// JButton updateAll_button = new JButton("YES for All");
			// updateAll_button.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			// updateAll_button.setBounds(850, 8, 120, 25);
			// dataPanel.add(updateAll_button);
	
			String selectAllList[] = { "SELECT ALL", "YES", "NO" };
			final JComboBox selectAll_combo = new JComboBox(selectAllList);
			selectAll_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			selectAll_combo.setBounds(850, 8, 140, 25);
			dataPanel.add(selectAll_combo);
	
			JLabel line_label = new JLabel(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			line_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			line_label.setBounds(30, 13, 1100, 50);
			dataPanel.add(line_label);
	
			logger.info("subjectList.size() before if == " + subjectList.size());
		
			int j = 0;
			int l = 0;
			final JRadioButton[] sr_radio = new JRadioButton[listSize];
			final JLabel[] rollNo_labels = new JLabel[listSize];
			final JLabel[] studentName_label = new JLabel[listSize];
			final JLabel[] grNo_label = new JLabel[listSize];
			final JComboBox[] selSubTitle_combo = new JComboBox[listSize];
			final JLabel[] optionalSubjects_label = new JLabel[listSize];
			JLabel[] line_labels = new JLabel[listSize];
			JLabel[] pipe_labels1 = new JLabel[listSize];
			// JLabel[] pipe_labels2 = new JLabel[listSize];
			// JLabel[] pipe_labels3 = new JLabel[listSize];

			String[] subjectArray = new String[listSize];
			subjectArray = subjectList.toArray(subjectArray);
			logger.info("listSize === " + listSize);
			logger.info("subjectArray === " + subjectArray.length);
			listSize = subjectArray.length;

			for (int i = 0; i < listSize; i++) {
				j = j + 30;
				l = j + 50;
				// subjectList.remove(subjectArray[i]);
				// subjectArray[i] = subjectArray[i].replace("null", "NA");
				// subjectList.add(subjectArray[i]);
				// studentList.add(nameDB+"|"+grDB+"||"+rollNoDB+"|||"+presentStdDb+"||||"+presentDivDb);
				// logger.info(j+"===="+subjectArray[i]);
				// String rollNo = subjectArray[i].substring(0,
				// subjectArray[i].indexOf("|"));
				String studentName = subjectArray[i].substring(0, subjectArray[i].indexOf("|"));
				studentName = studentName.replace("*", " ");
				String grNo = subjectArray[i].substring(subjectArray[i].indexOf("|") + 1, subjectArray[i].indexOf("||"));
				String rollNo = subjectArray[i].substring(subjectArray[i].indexOf("||") + 2, subjectArray[i].indexOf("|||"));
				String subTitle = subjectArray[i].substring(subjectArray[i].indexOf("|||") + 3, subjectArray[i].indexOf("||||"));
				String optSubjects = subjectArray[i].substring(subjectArray[i].lastIndexOf("||||") + 4);
				if (selAllClass.equalsIgnoreCase("YES") || selAllClass.equalsIgnoreCase("NO")) {
					subTitle = selAllClass;
				}
				logger.info("subTitle..." + subTitle);

				sr_radio[i] = new JRadioButton();
				sr_radio[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				sr_radio[i].setBounds(40, j + 13, 20, 20);
				sr_radio[i].setSelected(true);
				sr_radio[i].setEnabled(false);
				dataPanel.add(sr_radio[i]);

				grNo_label[i] = new JLabel(grNo);
				grNo_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));

				rollNo_labels[i] = new JLabel(rollNo);
				rollNo_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				rollNo_labels[i].setBounds(90, j, 120, 50);
				dataPanel.add(rollNo_labels[i]);

				pipe_labels1[i] = new JLabel("|");
				pipe_labels1[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels1[i].setBounds(150, j, 120, 50);
				dataPanel.add(pipe_labels1[i]);

				studentName_label[i] = new JLabel(studentName);
				studentName_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				studentName_label[i].setBounds(170, j + 12, 470, 20);
				dataPanel.add(studentName_label[i]);

				String selSubTitleList[] = { subTitle, "YES", "NO" };
				selSubTitle_combo[i] = new JComboBox(selSubTitleList);
				selSubTitle_combo[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				selSubTitle_combo[i].setBounds(660, j + 12, 100, 20);
				dataPanel.add(selSubTitle_combo[i]);
				
				optionalSubjects_label[i] = new JLabel(optSubjects);
//				optionalSubjects_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				optionalSubjects_label[i].setBounds(800, j + 12, 470, 20);
//				dataPanel.add(optionalSubjects_label[i]);

				line_labels[i] = new JLabel(
						"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				line_labels[i].setBounds(30, j + 10, 1100, 50);
				dataPanel.add(line_labels[i]);

				final int k = i;
				selSubTitle_combo[i].addFocusListener(new FocusListener() {

					String subjectDetail = "";

					@Override
					public void focusGained(FocusEvent arg0) {

						subjectDetail = studentName_label[k].getText() + "|" + grNo_label[k].getText() + "||"
								+ rollNo_labels[k].getText() + "|||" + selSubTitle_combo[k].getSelectedItem() + "||||" + optionalSubjects_label[k].getText();
						logger.info("selSubTitle_combo gain :::" + subjectDetail);
						logger.info("selSubTitle_combo gain before :::" + subjectList.size());
						subjectList.remove(subjectDetail);
						logger.info("selSubTitle_combo gain after :::" + subjectList.size());
					}

					@Override
					public void focusLost(FocusEvent arg0) {

						subjectDetail = studentName_label[k].getText() + "|" + grNo_label[k].getText() + "||"
								+ rollNo_labels[k].getText() + "|||" + selSubTitle_combo[k].getSelectedItem() + "||||" + optionalSubjects_label[k].getText();
						logger.info("selSubTitle_combo lost :::" + subjectDetail);
						logger.info("selSubTitle_combo lost before :::" + subjectList.size());
						subjectList.add(subjectDetail);
						logger.info("selSubTitle_combo lost after :::" + subjectList.size());
					}
				});
			}

			logger.info("subjectList==> " + subjectList.size());

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

			JLabel sep1_label = new JLabel(
					"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
			sep1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sep1_label.setBounds(40, l + 70, 1600, 50);
			dataPanel.add(sep1_label);
			// ///////end of save data box//////////////////////

			SaveButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {
						boolean insertSubject = false;
						boolean validateSubList = true;
						String updateAllSubTitle = "";
						String selSubTitle = (String) subTitle_combo1.getSelectedItem();
						logger.info("selSubTitle ::" + selSubTitle);

						if (!selSubTitle.equalsIgnoreCase("SELECT SUBJECT TITLE") && !selSubTitle.equalsIgnoreCase("")) {
							String[] subjectArray = new String[subjectList.size()];
							subjectArray = subjectList.toArray(subjectArray);
							for (int k = 0; k < subjectArray.length; k++) {
								String subTitle = subjectArray[k].substring(subjectArray[k].indexOf("|||") + 3, subjectArray[k].indexOf("||||"));
//								String subTitle = subjectArray[k].substring(subjectArray[k].lastIndexOf("|||") + 3);
								if (subTitle.equalsIgnoreCase("") || subTitle.equalsIgnoreCase("Select")) {
									JOptionPane.showMessageDialog(null, "Please select Yes/No for all students");
									validateSubList = false;
									break;
								}
							}
							logger.info("validateSubList = " + validateSubList);
							if (validateSubList) {
								logger.info("academicYearClass == " + academicYearClass);
								insertSubject = dbValidate.updateStudentSubAllot(sessionData, subjectList, academicYearClass,
										stdClass, divClass, updateAllSubTitle, section, subTitleClass);

								if (!insertSubject) {
									JOptionPane.showMessageDialog(null, "Subject data not updated successfully");
								} else {
									JOptionPane.showMessageDialog(null, "Subject data updated successfully");
								}

								frame.setVisible(false);
								List<String> subList = new ArrayList<String>();
								new StudentSubAllot(sessionData, stdClass, subList, academicYearClass, category, false,
										divClass, section, compOptClass, subTitleClass, "", user_name, user_role);
							}
							logger.info("insertSubject = " + insertSubject);
						} else {
							JOptionPane.showMessageDialog(null, "Please Select Subject Title");
						}

					} catch (Exception e1) {
						logger.info("Exception e1 = " + e1);
					}
				}
			});

			selectAll_combo.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {
						boolean insertSubject = false;
						// boolean validateSubList = true;
						String selectAll = (String) selectAll_combo.getSelectedItem();
						logger.info("selectAll ::" + selectAll);
						String selSubTitle = (String) subTitle_combo1.getSelectedItem();
						logger.info("selSubTitle ::" + selSubTitle);

						if (!selSubTitle.equalsIgnoreCase("SELECT SUBJECT TITLE")
								&& !selSubTitle.equalsIgnoreCase("")) {
							String[] subjectArray = new String[subjectList.size()];
							subjectArray = subjectList.toArray(subjectArray);
							if (!selectAll.equalsIgnoreCase("Select All")) {
								logger.info("academicYearClass == " + academicYearClass);
								frame.setVisible(false);
								List<String> subList = new ArrayList<String>();
								new StudentSubAllot(sessionData, stdClass, subList, academicYearClass, category, false,
										divClass, section, compOptClass, subTitleClass, selectAll, user_name,
										user_role);
							}
							logger.info("insertSubject = " + insertSubject);
						} else {
							JOptionPane.showMessageDialog(null, "Please Select Subject Title");
						}

					} catch (Exception e1) {
						logger.info("Exception e1 = " + e1);
					}
				}
			});

		}
		// else {
		// JOptionPane.showMessageDialog(null, "No data found ...");
		// }

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
