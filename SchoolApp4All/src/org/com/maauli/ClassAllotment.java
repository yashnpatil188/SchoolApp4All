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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
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

// import com.lowagie.text.DocumentException;

public class ClassAllotment extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static String std = "";

	static String div = "";
	
	static String allotTypeList = "";

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

	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static Logger logger = Logger.getLogger(ClassAllotment.class.getName());

	static JFrame frame = null;

	static JComboBox studentcombo;

	static  LinkedHashMap foundStudentMap = new LinkedHashMap();

	static int retCount = 0;

	static int scrollHeight = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

	static List<String> allGrList = new ArrayList();

	static List<String> selectedGrList = new ArrayList();

	// static List<String> duplicateLCList = new ArrayList();
	static boolean setSelected = false;

	static boolean printDuplicate = false;

	static String grNoClass = "";

	static String stdClass = "";

	static String divClass = "";

	static String lastNameClass = "";

	static String firstNameClass = "";

	static String fatherNameClass = "";

	static String academicYearClass = "";

	static String rollFromClass = "";

	static String rollToClass = "";

	static DBValidate findStudentDB = new DBValidate();

	static SessionData sessionData = new SessionData();

	static Common commonObj = new Common();

	static String currAcademicYear = "";
	
	static String promoteYear = "";

	static String user_name = "";

	static String user_role = "";
	
    static String app_header = "";
    
    static String app_header_0 = "";
    
    static String allomentTypeClass = "";

	static ClassPDF classPDFObj = new ClassPDF();
	
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

	public ClassAllotment(SessionData sessionData1, String retGr_no, String retStd, String retDiv, String retLastName,
			String retFirstName, String retFatherName, LinkedHashMap retStudentList, boolean retSelected,
			String academicYear, String rollFrom, String rollTo, String sec, String retUserName, String retUserRole, String allomentType) {

		logger.info("=============ClassAllotment=======");
		System.gc();
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		section = sec;
		allomentTypeClass = allomentType;
		logger.info("section :: " + section);
		std = bundle.getString(section.toUpperCase() + "_STD");
		logger.info("std :: " + std);
		div = bundle.getString(section.toUpperCase() + "_DIV");
		logger.info("div :: " + div);
		allotTypeList = bundle.getString("ALLOT_TYPE_LIST");
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
    	currAcademicYear = commonObj.getAcademicYear(commonObj.getCurrentDate());
    	academicYearClass = academicYear;
    	
        if(academicYearClass == null || academicYearClass.equalsIgnoreCase("")) {
        	promoteYear = commonObj.getPromoteYear(currAcademicYear);
        }
        else {
        	promoteYear = commonObj.getPromoteYear(academicYearClass);
        }
		
		// String todayDate = commonObj.getCurrentDate();
		// academicYearClass = commonObj.getAcademicYear(todayDate);
		// logger.info("academicYearClass =="+academicYearClass);

		grNoClass = retGr_no;
		lastNameClass = retLastName;
		firstNameClass = retFirstName;
		fatherNameClass = retFatherName;
		if (retStd.equalsIgnoreCase(""))
			stdClass = "Select";
		else
			stdClass = retStd;

		if (retDiv.equalsIgnoreCase(""))
			divClass = "Select";
		else
			divClass = retDiv;
		
		rollFromClass = rollFrom;
		rollToClass = rollTo;
		ArrayList<String> arrlist = null;
		logger.info(grNoClass + "::" + stdClass + "::" + divClass + "::" + lastNameClass + "::" + firstNameClass + "::"
				+ fatherNameClass + "::" + academicYearClass + "::" + rollFromClass + "::" + rollToClass);

		// int studentSize = retStudentList.size();
		// logger.info("studentSize => "+studentSize);
		entrytCnt = 0;
		logger.info("entrytCnt===>" + entrytCnt);

		try {
			// if(!stdClass.equalsIgnoreCase("Select") &&
			// !stdClass.equalsIgnoreCase("")){
			retStudentList = findStudentDB.findClassAllotList(sessionData, retGr_no, retStd, retDiv, retLastName, retFirstName,
					retFatherName, academicYearClass, rollFrom, rollTo, section, false,"");
			logger.info("retStudentList size => " + retStudentList.size());
			/*if(retStudentList.size() < 1 && !academicYear.equalsIgnoreCase("")){
				commonObj.showMessageDialog("No Data Found.");
			}*/
			foundStudentMap.clear();
			foundStudentMap.putAll(retStudentList);
			allGrList.clear();
			setSelected = retSelected;
			int foundSize = foundStudentMap.size();
			logger.info("foundSize => " + foundSize);
			retCount = foundStudentMap.size();
		} catch (Exception e) {
			logger.info("Exception = " + e);
		}

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
					if (findStudentDB.connectDatabase(sessionData)) {
						deleteFlag = findStudentDB.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
						findStudentDB.closeDatabase(sessionData);

						frame.setVisible(false);
						String[] arguments = new String[] {""};
		                LoginView.main(arguments);
					}
				} catch (Exception e1) {
					logger.info("Exception logoutButton ===>>>" + e1);
				} finally {
					findStudentDB.closeDatabase(sessionData);
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
		classAllotButton.setBackground(Color.GREEN);
		topbandPanel.add(classAllotButton);

		classAllotButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

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

		// /////////////Find Student form//////////////////////
		// /////////////GR No.//////////////
		JLabel gr_no_label = new JLabel("G.R. No.:");
		gr_no_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		gr_no_label.setBounds(60, 00, 70, 50);
		findPanel.add(gr_no_label);

		final JTextField gr_no_text = new JTextField();
		gr_no_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		gr_no_text.setBounds(160, 12, 130, 25);
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
		// //////////////////////////////////
		// /////////////Std//////////////
		JLabel admittedStd_label = new JLabel("Std :");
		admittedStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_label.setBounds(380, 00, 70, 50);
		findPanel.add(admittedStd_label);

		String dispStd = stdClass + "," + std;
		String[] stdList = dispStd.split(",");
		// String admittedStdList[] =
		// {"Select","IV","V","VI","VII","VIII","IX","X"};
		final JComboBox admittedStd_combo = new JComboBox(stdList);
		admittedStd_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_combo.setBounds(430, 12, 90, 25);
		findPanel.add(admittedStd_combo);

		// //////////////////////////////////
		// /////////////Div//////////////
		JLabel admittedDiv_label = new JLabel("Div :");
		admittedDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedDiv_label.setBounds(530, 00, 70, 50);
		findPanel.add(admittedDiv_label);

		String dispDiv = divClass + "," + div;
		String[] divListView = dispDiv.split(",");
		// String admittedDivList[] = {"Select","A","B","C","D"};
		final JComboBox admittedDiv_combo = new JComboBox(divListView);
		admittedDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedDiv_combo.setBounds(580, 12, 100, 25);
		findPanel.add(admittedDiv_combo);
		
		//////////////////////////////////////
		JLabel lcType_label = new JLabel("Allotment Type :");
		lcType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lcType_label.setBounds(710, 00, 140, 50);
		findPanel.add(lcType_label);
		//////////////////////////////////////
		if(!allomentTypeClass.equalsIgnoreCase("")){
			allotTypeList = allomentTypeClass  +"," + allotTypeList;
		}
		String[] allotList = allotTypeList.split(",");
		final JComboBox allotType_combo = new JComboBox(allotList);
		allotType_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		allotType_combo.setBounds(860, 12, 150, 25);
		findPanel.add(allotType_combo);
		
		// /////////////Roll No.//////////////
		JLabel rollNo_label = new JLabel("Roll No :");
		rollNo_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		rollNo_label.setBounds(710, 00, 70, 50);
		// findPanel.add(rollNo_label);

		JLabel from_label = new JLabel("From -");
		from_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		from_label.setBounds(790, 00, 70, 50);
		// findPanel.add(from_label);

		final JTextField from_text = new JTextField();
		from_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		from_text.setBounds(850, 12, 40, 25);
		// findPanel.add(from_text);

		JLabel to_label = new JLabel("To -");
		to_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		to_label.setBounds(900, 00, 70, 50);
		// findPanel.add(to_label);

		final JTextField to_text = new JTextField();
		to_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		to_text.setBounds(940, 12, 40, 25);
		// findPanel.add(to_text);
		// //////////////////////////////////
		// /////////////Last Name//////////////
		JLabel lastName_label = new JLabel("Last Name :");
		lastName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_label.setBounds(60, 40, 120, 50);
		findPanel.add(lastName_label);

		final JTextField lastName_text = new JTextField();
		lastName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_text.setBounds(160, 50, 130, 25);
		findPanel.add(lastName_text);
		// //////////////////////////////////
		// /////////////First Name//////////////
		JLabel firstName_label = new JLabel("First Name :");
		firstName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_label.setBounds(380, 40, 120, 50);
		findPanel.add(firstName_label);

		final JTextField firstName_text = new JTextField();
		firstName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_text.setBounds(480, 50, 200, 25);
		findPanel.add(firstName_text);
		// //////////////////////////////////
		// /////////////Father Name//////////////
		JLabel fatherName_label = new JLabel("Father Name :");
		fatherName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_label.setBounds(710, 40, 120, 50);
		findPanel.add(fatherName_label);

		final JTextField fatherName_text = new JTextField();
		fatherName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_text.setBounds(830, 50, 200, 25);
		findPanel.add(fatherName_text);
		// //////////////////////////////////
		// /////////GR radio///////////////
		final JRadioButton gr_no_radio = new JRadioButton();
		gr_no_radio.setBounds(30, 15, 20, 20);
		findPanel.add(gr_no_radio);
		// /////////name radio///////////////30, 80, 120, 50
		final JRadioButton name_radio = new JRadioButton();
		name_radio.setBounds(30, 55, 20, 20);
		findPanel.add(name_radio);
		// /////////Std radio///////////////
		final JRadioButton std_radio = new JRadioButton();
		std_radio.setBounds(350, 15, 20, 20);
		findPanel.add(std_radio);
		// /////////ACADEMIV YEAR radio///////////////30, 80, 120, 50
		final JRadioButton academic_radio = new JRadioButton();
		academic_radio.setBounds(30, 95, 20, 20);
		academic_radio.setSelected(true);
		academic_radio.setEnabled(false);
		findPanel.add(academic_radio);

		// /////////////ACADEMIV YEAR //////////////
		JLabel academic_label = new JLabel("Academic :");
		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academic_label.setBounds(60, 80, 100, 50);
		findPanel.add(academic_label);

		String yearList = currAcademicYear + "," + Common.getPreviousYear(currAcademicYear);
		String academicYearList[] = yearList.split(",");
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		if(!academicYearClass.equalsIgnoreCase("")){
			academicYear_combo.setSelectedItem(academicYearClass);
		}
		academicYear_combo.setBounds(160, 95, 120, 25);
		findPanel.add(academicYear_combo);

		if(!stdClass.equalsIgnoreCase("All") && !stdClass.equalsIgnoreCase("Select") && 
	    		!stdClass.equalsIgnoreCase("") && findStudentDB.connectDatabase(sessionData)){
			admittedDiv_combo.removeAllItems();
			String divAvailabe = findStudentDB.getDistinctDiv(sessionData, stdClass, section, 
					"PRESENT_DIV", "PRESENT_STD", "class_allotment",academicYearClass);
			
			for (String retval: divAvailabe.split(",")) {
				admittedDiv_combo.addItem(retval);
			}
			admittedDiv_combo.setSelectedItem(divClass);
		}
		// //////radio action//////////////
		gr_no_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (gr_no_radio.isSelected()) {
					name_radio.setSelected(false);
					gr_no_text.setEnabled(true);
					lastName_text.setText("");
					lastName_text.setEnabled(false);
					firstName_text.setText("");
					firstName_text.setEnabled(false);
					fatherName_text.setText("");
					fatherName_text.setEnabled(false);
					std_radio.setSelected(false);
					admittedStd_combo.setEnabled(false);
					admittedDiv_combo.setEnabled(false);

				} else {
					gr_no_radio.setSelected(false);
					gr_no_text.setEnabled(false);
					gr_no_text.setText("");
				}
			}
		});

		std_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				std_radio.setSelected(true);
				admittedStd_combo.setEnabled(true);
				admittedDiv_combo.setEnabled(true);
				gr_no_radio.setSelected(false);
				name_radio.setSelected(false);
				gr_no_text.setText("");
				gr_no_text.setEnabled(false);
				lastName_text.setText("");
				lastName_text.setEnabled(false);
				firstName_text.setText("");
				firstName_text.setEnabled(false);
				fatherName_text.setText("");
				fatherName_text.setEnabled(false);
			}
		});

		admittedStd_combo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
					String stdSel = (String) admittedStd_combo.getSelectedItem();
					String acadSel = (String) academicYear_combo.getSelectedItem();
					if(!stdSel.equalsIgnoreCase("All") && !stdSel.equalsIgnoreCase("Select") && findStudentDB.connectDatabase(sessionData)){
						admittedDiv_combo.removeAllItems();
//						admittedDiv_combo.addItem("All");
						String divAvailabe = findStudentDB.getDistinctDiv(sessionData, stdSel, section, 
								"PRESENT_DIV", "PRESENT_STD", "class_allotment",acadSel);
						
						for (String retval: divAvailabe.split(",")) {
							admittedDiv_combo.addItem(retval);
						}
					}
				} catch (Exception e1) {
					logger.info("Exception insertFormData ===>>>" + e1);
				} finally {
					findStudentDB.closeDatabase(sessionData);
				}
			}
		});

		////////////////////////////////////
		allotType_combo.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
		
			String allotType = allotType_combo.getSelectedItem().toString();
			frame.setVisible(false);
				if (allotType.equalsIgnoreCase("Promote Student")) {
					new PromoteClass(sessionData, grNoClass, (String) admittedStd_combo.getSelectedItem(), (String) admittedDiv_combo.getSelectedItem(), lastNameClass, firstNameClass,
							fatherNameClass, null, false, promoteYear, rollFromClass, rollToClass, section,
							user_name, user_role,"Promote Student");
				} 
				else if (allotType.equalsIgnoreCase("Update AB Form")) {
					new StatementUpdate(sessionData, grNoClass, (String) admittedStd_combo.getSelectedItem(), (String) admittedDiv_combo.getSelectedItem(), lastNameClass, firstNameClass,
							fatherNameClass, null, false, academicYearClass, section,
							user_name, user_role,"","","","","Update AB Form");
				}
				else if (allotType.equalsIgnoreCase("Manual Roll No.")) {
					new ClassAllotment(sessionData, grNoClass, (String) admittedStd_combo.getSelectedItem(), (String) admittedDiv_combo.getSelectedItem(), lastNameClass, firstNameClass,
							fatherNameClass, null, false, academicYearClass, rollFromClass, rollToClass, section,
							user_name, user_role, "Manual Roll No.");
				}
				else {
					new ClassAllotment(sessionData, grNoClass, (String) admittedStd_combo.getSelectedItem(), (String) admittedDiv_combo.getSelectedItem(), lastNameClass, firstNameClass,
							fatherNameClass, null, false, academicYearClass, rollFromClass, rollToClass, section,
							user_name, user_role, "Allot Student");
				}
			}
		});

		name_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (name_radio.isSelected()) {
					gr_no_radio.setSelected(false);
					gr_no_text.setText("");
					gr_no_text.setEnabled(false);
					lastName_text.setEnabled(true);
					firstName_text.setEnabled(true);
					fatherName_text.setEnabled(true);
					std_radio.setSelected(true);
					admittedStd_combo.setEnabled(true);
					admittedDiv_combo.setEnabled(true);
				} else {
					// gr_no_radio.setSelected(false);
					// gr_no_text.setText("");
					// gr_no_text.setEnabled(false);
					lastName_text.setEnabled(false);
					firstName_text.setEnabled(false);
					fatherName_text.setEnabled(false);
				}
			}
		});

		// //////////////////////////////////
		// academic_radio.addActionListener(new ActionListener(){
		// public void actionPerformed(ActionEvent e) {
		// if(academic_radio.isSelected()){
		// academicYear_combo.setEnabled(true);
		// }
		// else{
		// academicYear_combo.setEnabled(false);
		// }
		//
		// }
		// });

		std_radio.setSelected(true);
		name_radio.setSelected(false);
		gr_no_radio.setSelected(false);
		gr_no_text.setEnabled(false);
		lastName_text.setEnabled(false);
		firstName_text.setEnabled(false);
		fatherName_text.setEnabled(false);
		// academicYear_combo.setEnabled(true);
		// ///////////////////////
		// /////////////Submit//////////////
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(mainCentre - 150, 90, 130, 25);
		findPanel.add(submitButton);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean validateFields = true;
				String gr_no = "";
				String lastName = "";
				String firstName = "";
				String fatherName = "";
				String userName = "";
				String std = "";
				String div = "";
				String academicSel = "";
				String rollFromSubmit = "";
				String rollToSubmit = "";
				String allotTypeSel = "";

				gr_no = gr_no_text.getText();
				lastName = lastName_text.getText();
				firstName = firstName_text.getText();
				fatherName = fatherName_text.getText();
				std = (String) admittedStd_combo.getSelectedItem();
				div = (String) admittedDiv_combo.getSelectedItem();
				academicSel = (String) academicYear_combo.getSelectedItem();
				rollFromSubmit = from_text.getText();
				rollToSubmit = to_text.getText();
				allotTypeSel = (String) allotType_combo.getSelectedItem();

				if (commonObj.checkComma(gr_no)) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "GR_NO cannot contain |-:';,");
				} else if (std.equalsIgnoreCase("Select") && gr_no.equalsIgnoreCase("")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Std.");
				}
				else if (div.equalsIgnoreCase("Select") && gr_no.equalsIgnoreCase("")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Div.");
				} else if (lastName.length() > 50) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("LastName", lastName, 50));
				} else if (commonObj.checkComma(lastName)) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "LastName cannot contain |-:';,");
				} else if (firstName.length() > 50) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("FirstName", firstName, 50));
				} else if (commonObj.checkComma(firstName)) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "FirstName cannot contain |-:';,");
				} else if (fatherName.length() > 50) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("FatherName", fatherName, 50));
				} else if (commonObj.checkComma(fatherName)) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "FatherName cannot contain |-:';,");
				}

				if (academic_radio.isSelected() && !academicSel.equalsIgnoreCase("Year")) {
					academicSel = academicSel;
				} else {
					academicSel = "";
				}
				logger.info("academicSel == " + academicSel);
				List<String> studentList;
				logger.info("validateFields flag..." + validateFields);
				if (validateFields) {
					try {
						grNoClass = gr_no;
						lastNameClass = lastName;
						firstNameClass = firstName;
						fatherNameClass = fatherName;
						stdClass = std;
						divClass = div;
						// academicYearClass = academicSel;
						rollFromClass = rollFromSubmit;
						rollToClass = rollToSubmit;

						frame.setVisible(false);
						if(allotTypeSel.equalsIgnoreCase("Allot Student")){
							new ClassAllotment(sessionData, grNoClass, stdClass, divClass, lastNameClass, firstNameClass,
									fatherNameClass, null, false, academicSel, rollFromClass, rollToClass, section,
									user_name, user_role, allotTypeSel);
						}
						else if (allotTypeSel.equalsIgnoreCase("Update AB Form")) {
							new StatementUpdate(sessionData, grNoClass, (String) admittedStd_combo.getSelectedItem(), (String) admittedDiv_combo.getSelectedItem(), lastNameClass, firstNameClass,
									fatherNameClass, null, false, academicSel, section,
									user_name, user_role,"","","","","Update AB Form");
						}
						else if (allotTypeSel.equalsIgnoreCase("Manual Roll No.")) {
							new ClassAllotment(sessionData, grNoClass, (String) admittedStd_combo.getSelectedItem(), (String) admittedDiv_combo.getSelectedItem(), lastNameClass, firstNameClass,
									fatherNameClass, null, false, academicSel, rollFromClass, rollToClass, section,
									user_name, user_role, "Manual Roll No.");
						}
						else{
							new PromoteClass(sessionData, grNoClass, stdClass, divClass, lastNameClass, firstNameClass,
								fatherNameClass, null, false, promoteYear, rollFromClass, rollToClass, section,
								user_name, user_role,allotTypeSel);
						}
					} catch (Exception e1) {
						logger.info("Exception FindStudent form ===>> >" + e1);
						new ClassAllotment(sessionData, grNoClass, stdClass, divClass, lastNameClass, firstNameClass,
								fatherNameClass, null, false, academicSel, rollFromClass, rollToClass, section,
								user_name, user_role,"");
						frame.setVisible(false);
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

//				logger.info("Cliked Reset in findLeaving cert");
				LinkedHashMap studentMap = new LinkedHashMap();
				frame.setVisible(false);
				new ClassAllotment(sessionData, "", "", "", "", "", "", studentMap, false, "", "", "", section,
						user_name, user_role,"");
			}
		});

		lastName_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				lastName_text.selectAll();
			}
		});
		firstName_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				firstName_text.selectAll();
			}
		});
		fatherName_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				fatherName_text.selectAll();
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
		if (foundStudentMap.size() > 0) {
			int listSize = foundStudentMap.size();

			final JRadioButton all_radio = new JRadioButton();
			all_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			all_radio.setBounds(40, 13, 20, 20);
			all_radio.setSelected(true);
			all_radio.setEnabled(false);
			dataPanel.add(all_radio);

			JLabel sr_label = new JLabel("Roll No.");
			sr_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sr_label.setBounds(70, 00, 120, 50);
			dataPanel.add(sr_label);

			JLabel pipe_label1 = new JLabel("|");
			pipe_label1.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label1.setBounds(130, 00, 120, 50);
			dataPanel.add(pipe_label1);

			JLabel gr_label = new JLabel("Gr No.");
			gr_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			gr_label.setBounds(160, 00, 120, 50);
			dataPanel.add(gr_label);

			JLabel pipe_label2 = new JLabel("|");
			pipe_label2.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label2.setBounds(230, 00, 120, 50);
			dataPanel.add(pipe_label2);

			JLabel name_label = new JLabel("Name");
			name_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			name_label.setBounds(280, 00, 120, 50);
			dataPanel.add(name_label);

			JLabel pipe_label3 = new JLabel("|");
			pipe_label3.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label3.setBounds(640, 00, 120, 50);
			dataPanel.add(pipe_label3);

			JLabel std_label = new JLabel("Std.");
			std_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			std_label.setBounds(660, 00, 120, 50);
			dataPanel.add(std_label);

			JLabel pipe_label4 = new JLabel("|");
			pipe_label4.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label4.setBounds(710, 00, 120, 50);
			dataPanel.add(pipe_label4);

			JLabel presentDiv_label = new JLabel("Div");
			presentDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			presentDiv_label.setBounds(730, 00, 120, 50);
			dataPanel.add(presentDiv_label);

			JLabel pipe_label5 = new JLabel("|");
			pipe_label5.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label5.setBounds(850, 00, 120, 50);
			dataPanel.add(pipe_label5);

			String labelTodisplay = "New Div";
			if (allomentTypeClass.equalsIgnoreCase("Manual Roll No.")) {
				labelTodisplay = "New Roll No.";
			}
			JLabel newDiv_label = new JLabel(labelTodisplay);
			newDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			newDiv_label.setBounds(870, 00, 120, 50);
			dataPanel.add(newDiv_label);

			JLabel line_label = new JLabel(
					"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			line_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			line_label.setBounds(30, 13, 1000, 50);
			dataPanel.add(line_label);

			logger.info("listSize === " + listSize);

			if (listSize > 0) {
				int j = 0;
				int l = 0;
				final JRadioButton[] roll_radio = new JRadioButton[listSize];
				final JLabel[] roll_labels = new JLabel[listSize];
				JLabel[] pipe_labels1 = new JLabel[listSize];
				final JLabel[] gr_labels = new JLabel[listSize];
				JLabel[] pipe_labels2 = new JLabel[listSize];
				final JLabel[] name_labels = new JLabel[listSize];
				final JLabel[] nameToolTip_labels = new JLabel[listSize];
				JLabel[] line_labels = new JLabel[listSize];
				JLabel[] pipe_labels3 = new JLabel[listSize];
				JLabel[] pipe_labels4 = new JLabel[listSize];
				JLabel[] pipe_labels5 = new JLabel[listSize];
				final JLabel[] presentStd_labels = new JLabel[listSize];
				final JLabel[] presentDiv_labels = new JLabel[listSize];
				// String divList[] = {"Select","A","B","C","D"};
				final JComboBox[] newDiv_combo = new JComboBox[listSize];
				final JTextField[] newRollText = new JTextField[listSize];

				LinkedHashMap insideMap = new LinkedHashMap();
				Set set = foundStudentMap.entrySet();
				Iterator m = set.iterator();
				int i = 0;
				while(m.hasNext()) {
					j = j + 30;
					l = j + 50;
					Map.Entry me = (Map.Entry)m.next();
					insideMap = (LinkedHashMap) me.getValue();
					String name = insideMap.get("name").toString();
					final String gr = insideMap.get("gr").toString();
					String rollNo = insideMap.get("rollNo").toString();
					String presentStd = insideMap.get("presentStd").toString();
					String presentDiv = insideMap.get("presentDiv").toString();
					String remarkDiv = insideMap.get("remarkDiv").toString();
					String gender = insideMap.get("gender").toString();
					// logger.info("remarkDiv = "+remarkDiv);
					roll_radio[i] = new JRadioButton();
					roll_radio[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					roll_radio[i].setBounds(40, j + 13, 20, 20);
					roll_radio[i].setSelected(true);
					roll_radio[i].setEnabled(false);
					dataPanel.add(roll_radio[i]);

					roll_labels[i] = new JLabel(rollNo);
					roll_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					roll_labels[i].setBounds(90, j, 120, 50);
					dataPanel.add(roll_labels[i]);

					pipe_labels1[i] = new JLabel("|");
					pipe_labels1[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels1[i].setBounds(130, j, 120, 50);
					dataPanel.add(pipe_labels1[i]);

					gr_labels[i] = new JLabel(gr);
					gr_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					gr_labels[i].setBounds(160, j, 120, 50);
					dataPanel.add(gr_labels[i]);

					pipe_labels2[i] = new JLabel("|");
					pipe_labels2[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels2[i].setBounds(230, j, 120, 50);
					dataPanel.add(pipe_labels2[i]);

					nameToolTip_labels[i] = new JLabel("");
					nameToolTip_labels[i].setToolTipText(gender);
					nameToolTip_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					nameToolTip_labels[i].setBounds(280, j-15, 350, 50);
					dataPanel.add(nameToolTip_labels[i]);
					
					name_labels[i] = new JLabel(name);
//					name_labels[i].setToolTipText(gender);
					name_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					name_labels[i].setBounds(280, j, 350, 50);
					dataPanel.add(name_labels[i]);

					pipe_labels3[i] = new JLabel("|");
					pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels3[i].setBounds(640, j, 120, 50);
					dataPanel.add(pipe_labels3[i]);

					stdClass = presentStd;
					presentStd_labels[i] = new JLabel(presentStd);
					presentStd_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					presentStd_labels[i].setBounds(660, j + 12, 100, 20);
					dataPanel.add(presentStd_labels[i]);

					pipe_labels4[i] = new JLabel("|");
					pipe_labels4[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels4[i].setBounds(710, j, 120, 50);
					dataPanel.add(pipe_labels4[i]);

					divClass = presentDiv;
					presentDiv_labels[i] = new JLabel(presentDiv);
					presentDiv_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					presentDiv_labels[i].setBounds(740, j + 12, 100, 20);
					dataPanel.add(presentDiv_labels[i]);

					pipe_labels5[i] = new JLabel("|");
					pipe_labels5[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels5[i].setBounds(850, j, 120, 50);
					dataPanel.add(pipe_labels5[i]);

					String newDiv = presentDiv + "," + div;
					String[] divList = newDiv.split(",");
					newDiv_combo[i] = new JComboBox(divList);
					newDiv_combo[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					newDiv_combo[i].setBounds(870, j + 12, 120, 20);
					
					newRollText[i] = new JTextField(rollNo);
					newRollText[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					newRollText[i].setBounds(870, j + 12, 100, 20);
					
					if(!allomentTypeClass.equalsIgnoreCase("Manual Roll No.")){
						dataPanel.add(newDiv_combo[i]);
					}
					else{
						dataPanel.add(newRollText[i]);
					}

					if (insideMap.get("remarkDiv").toString().equalsIgnoreCase("EXISTING")) {
						insideMap.put("status", "EXISTING");
					} else {
						insideMap.put("status", "NEW");
					}

					line_labels[i] = new JLabel(
							"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					line_labels[i].setBounds(30, j + 10, 1000, 50);
					dataPanel.add(line_labels[i]);

					// //////sr radio action//////////////
					final int k = i;

					newDiv_combo[i].addFocusListener(new FocusListener() {

						String grDetail = "";

						@Override
						public void focusGained(FocusEvent arg0) {
//							foundStudentMap.remove(gr_labels[k].getText());
						}

						@Override
						public void focusLost(FocusEvent arg0) {

							String gr = gr_labels[k].getText();
							String presentDiv = presentDiv_labels[k].getText();
							String newDiv = (String) newDiv_combo[k].getSelectedItem();
							((LinkedHashMap) foundStudentMap.get(gr)).put("presentDiv",presentDiv);
							((LinkedHashMap) foundStudentMap.get(gr)).put("newDiv",newDiv);
							if(!presentDiv.equalsIgnoreCase(newDiv)){
								((LinkedHashMap) foundStudentMap.get(gr)).put("status","NEW");
							}
						}
					});
					
					newRollText[i].addFocusListener(new FocusListener() {

						String grDetail = "";

						@Override
						public void focusGained(FocusEvent arg0) {
							newRollText[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {

							String newRollChanged = newRollText[k].getText();
							String gr = gr_labels[k].getText();
							String presentDiv = presentDiv_labels[k].getText();
							String newDiv = (String) newDiv_combo[k].getSelectedItem();
							((LinkedHashMap) foundStudentMap.get(gr)).put("rollNo",newRollChanged);
						}
					});
					i++;
				}
				
				// /////Arrange Roll No. starts/////////////////////////////////
				final JRadioButton arrangeRoll_radio = new JRadioButton();
				arrangeRoll_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				arrangeRoll_radio.setBounds(50, l + 10, 20, 20);
				dataPanel.add(arrangeRoll_radio);

				JLabel arrangeRoll_label = new JLabel("Arrange Roll No. by alphabet");
				arrangeRoll_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				arrangeRoll_label.setBounds(75, l - 5, 250, 50);
				dataPanel.add(arrangeRoll_label);
				
				// /////Arrange Roll No. by girls first/////////////////////////////////
				final JRadioButton girlsRoll_radio = new JRadioButton();
				girlsRoll_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				girlsRoll_radio.setBounds(320, l + 10, 20, 20);
				dataPanel.add(girlsRoll_radio);

				JLabel girslRoll_label = new JLabel("Arrange Roll No. by Girls First");
				girslRoll_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				girslRoll_label.setBounds(350, l - 5, 250, 50);
				dataPanel.add(girslRoll_label);
				
				// /////Arrange Roll No. by boys first/////////////////////////////////
				final JRadioButton boysRoll_radio = new JRadioButton();
				boysRoll_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				boysRoll_radio.setBounds(600, l + 10, 20, 20);
				dataPanel.add(boysRoll_radio);

				JLabel boysRoll_label = new JLabel("Arrange Roll No. by Boys first");
				boysRoll_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				boysRoll_label.setBounds(630, l - 5, 250, 50);
				dataPanel.add(boysRoll_label);

				JButton updateButton = new JButton("Update Class");
				updateButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				updateButton.setBounds(mainCentre - 180, l + 35, 130, 25);
				dataPanel.add(updateButton);

				JButton printButton = new JButton("Print");
				printButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				printButton.setBounds(mainCentre + 20, l + 35, 130, 25);
				dataPanel.add(printButton);

				// ///////print data ends///////////////////////
				// ///////print data box///////////////////////
				JLabel sep_label = new JLabel(
						"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				sep_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				sep_label.setBounds(40, l - 30, 1600, 50);
				dataPanel.add(sep_label);

				JLabel sep1_label = new JLabel(
						"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				sep1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				sep1_label.setBounds(40, l + 45, 1600, 50);
				dataPanel.add(sep1_label);
				// /////////////////////////////

				// //////All sr radio action//////////////
				all_radio.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						boolean pass_selected = all_radio.isSelected();
						// logger.info("pass_selected : "+pass_selected);
						frame.setVisible(false);
						// logger.info(" list size : "+foundStudentList.size());
						new ClassAllotment(sessionData, "", "", "", "", "", "", foundStudentMap, pass_selected, "", "",
								"", section, user_name, user_role,allomentTypeClass);
					}
				});

				// /////updateButton action starts///////////////////
				updateButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						logger.info("======Inside Update button action=====");
						Common commonObj1 = new Common();
						DBValidate classAllottObj = new DBValidate();
						boolean flagLeaving = true;
						List<String> passStudentList = new ArrayList();

						if (foundStudentMap.size() == 0) {
							JOptionPane.showMessageDialog(null, "No Student selected");
						} else if (flagLeaving) {
							// logger.info("duplicateLCList===>"+duplicateLCList.size());
							if (flagLeaving) {
								JFrame f = new JFrame("Student Class Allotment Update in progress. Please Don't Close");
								int reply = JOptionPane.showConfirmDialog(null,"Would You Like to Update Class Allotment?", "Confirm Update",JOptionPane.YES_NO_OPTION);
								if (reply == JOptionPane.YES_OPTION) {
									try {
										// /////////////////////////////
										f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
									    f.setSize(500, 0);
									    f.setResizable(false);
									    f.setVisible(true);
									    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

									    if(allomentTypeClass.equalsIgnoreCase("Manual Roll No.")){
									    	classAllottObj.updateClass(sessionData, foundStudentMap, section, academicYearClass,allomentTypeClass);
									    }
									    else{
										    String remark = "";
											int listSize = allGrList.size();
											if (arrangeRoll_radio.isSelected() || girlsRoll_radio.isSelected() || boysRoll_radio.isSelected()) {
												logger.info("if arrangeRoll_radio selected===");
												remark = "EXISTING";
												int j = 1;
												
												String dispDiv = div;
												String[] divListView = dispDiv.split(",");
												HashMap<String, Integer> hmapDivCount = new HashMap<String, Integer>();
												List<List<String>> l = new ArrayList<List<String>>(foundStudentMap.keySet());
												
												LinkedHashMap foundStudentMapTemp = new LinkedHashMap();
												foundStudentMapTemp.putAll(foundStudentMap);
												TreeMap alphabeticalList = new TreeMap();
												if(arrangeRoll_radio.isSelected()){
													for (int i = 0; i < foundStudentMap.size(); i++) {
														alphabeticalList.put(((LinkedHashMap) foundStudentMap.get(l.get(i))).get("name").toString()+"|"+((LinkedHashMap) foundStudentMap.get(l.get(i))).get("gr").toString(),((LinkedHashMap) foundStudentMap.get(l.get(i))));
													}
												}
												else if(girlsRoll_radio.isSelected()){
													for (int i = 0; i < foundStudentMap.size(); i++) {
														alphabeticalList.put(((LinkedHashMap) foundStudentMap.get(l.get(i))).get("gender").toString()+((LinkedHashMap) foundStudentMap.get(l.get(i))).get("name").toString(),((LinkedHashMap) foundStudentMap.get(l.get(i))));
													}
												}
												else if(boysRoll_radio.isSelected()){
													for (int i = 0; i < foundStudentMap.size(); i++) {
														String gender = ((LinkedHashMap) foundStudentMap.get(l.get(i))).get("gender").toString();
														if(gender.equalsIgnoreCase("MALE")){
															gender = "A"+gender;
														}
														alphabeticalList.put(gender+((LinkedHashMap) foundStudentMap.get(l.get(i))).get("name").toString(),((LinkedHashMap) foundStudentMap.get(l.get(i))));
													}
												}
												foundStudentMap.clear();
												
												for(int i = 0; i < divListView.length; i++) {
													hmapDivCount.put(divListView[i], 0);
												}
												
												List<List<String>> m = new ArrayList<List<String>>(alphabeticalList.keySet());
												for (int i = 0; i < alphabeticalList.size(); i++) {
													String rollNo1 = ((LinkedHashMap) alphabeticalList.get(m.get(i))).get("rollNo").toString();
													String gr1 = ((LinkedHashMap) alphabeticalList.get(m.get(i))).get("gr").toString();
													String name1 = ((LinkedHashMap) alphabeticalList.get(m.get(i))).get("name").toString();
													String presentStd1 = ((LinkedHashMap) alphabeticalList.get(m.get(i))).get("presentStd").toString();
													String presentDiv1 = ((LinkedHashMap) alphabeticalList.get(m.get(i))).get("presentDiv").toString();
													String NewDiv1 = ((LinkedHashMap) alphabeticalList.get(m.get(i))).get("newDiv").toString();
													String exist_new = ((LinkedHashMap) alphabeticalList.get(m.get(i))).get("status").toString();
													
													if (presentDiv1.equalsIgnoreCase(NewDiv1)) {
														passStudentList.add(gr1 + "|" + j + "||" + NewDiv1 + "|||" + presentDiv1 + "||||" + remark);
														foundStudentMap.put(gr1,alphabeticalList.get(m.get(i)));
														((LinkedHashMap)foundStudentMap.get(gr1)).put("status","EXISTING");
														((LinkedHashMap)foundStudentMap.get(gr1)).put("rollNo",j);
														j++;
													} else {
														int distinctCount = 0;
														if (hmapDivCount.get(NewDiv1) == 0) {
															int divCount = findStudentDB.getDistinctCount(sessionData, "CLASS_ALLOTMENT", "ROLL_NO",presentStd1, NewDiv1, academicYearClass);
															divCount = divCount + 1;
															hmapDivCount.put(NewDiv1, divCount);
															distinctCount = divCount;
														} else {
															int divCountInMap = hmapDivCount.get(NewDiv1);
															divCountInMap = divCountInMap + 1;
															hmapDivCount.put(NewDiv1, divCountInMap);
															distinctCount = divCountInMap;
														}
														foundStudentMap.put(gr1,alphabeticalList.get(m.get(i)));
														((LinkedHashMap)foundStudentMap.get(gr1)).put("status","NEW");
														((LinkedHashMap) foundStudentMap.get(gr1)).put("rollNo",distinctCount);
														passStudentList.add(gr1 + "|" + distinctCount + "||" + NewDiv1+ "|||" + presentDiv1 + "||||" + remark);
														String grDetail = name1 + "|" + gr1 + "||" + distinctCount + "|||"+ presentStd1 + "||||" + presentDiv1 + "|||||" + NewDiv1;
													}
												}
											} else {
												int j = 0;
												
												String dispDiv = div;
												String[] divListView = dispDiv.split(",");
												HashMap<String, Integer> hmapDivCount = new HashMap<String, Integer>();
												for(int i = 0; i < divListView.length; i++) {
													hmapDivCount.put(divListView[i], 0);
												}
	
												List<List<String>> l = new ArrayList<List<String>>(foundStudentMap.keySet());
												for (int i = 0; i < foundStudentMap.size(); i++) {
													String rollNo1 = ((LinkedHashMap) foundStudentMap.get(l.get(i))).get("rollNo").toString();
													String gr1 = ((LinkedHashMap) foundStudentMap.get(l.get(i))).get("gr").toString();
													if(gr1.equalsIgnoreCase("0009037")) {
														System.out.println("");
													}
													String name1 = ((LinkedHashMap) foundStudentMap.get(l.get(i))).get("name").toString();
													String presentStd1 = ((LinkedHashMap) foundStudentMap.get(l.get(i))).get("presentStd").toString();
													String presentDiv1 = ((LinkedHashMap) foundStudentMap.get(l.get(i))).get("presentDiv").toString();
													String NewDiv1 = ((LinkedHashMap) foundStudentMap.get(l.get(i))).get("newDiv").toString();
													String exist_new = ((LinkedHashMap) foundStudentMap.get(l.get(i))).get("status").toString();
													if (exist_new.equalsIgnoreCase("NEW") && presentDiv1.equalsIgnoreCase(NewDiv1)) {
														logger.info("=========inside if ======== ");
														int distinctCount = 0;
														j = j + 1;
														passStudentList.add(gr1 + "|" + j + "||" + presentDiv1 + "||| ||||" + remark);
														((LinkedHashMap)foundStudentMap.get(gr1)).put("presentDiv",NewDiv1);
														((LinkedHashMap)foundStudentMap.get(gr1)).put("status","EXISTING");
														((LinkedHashMap)foundStudentMap.get(gr1)).put("rollNo",j);
													} else if (exist_new.equalsIgnoreCase("NEW")) {
														remark = "NEW";
														int distinctCount = 0;
														if (hmapDivCount.get(NewDiv1) == 0) {
															int divCount = findStudentDB.getDistinctCount(sessionData, "CLASS_ALLOTMENT", "ROLL_NO",presentStd1, NewDiv1, academicYearClass);
															divCount = divCount + 1;
															hmapDivCount.put(NewDiv1, divCount);
															distinctCount = divCount;
														} else {
															int divCountInMap = hmapDivCount.get(NewDiv1);
															divCountInMap = divCountInMap + 1;
															hmapDivCount.put(NewDiv1, divCountInMap);
															distinctCount = divCountInMap;
														} 
														((LinkedHashMap) foundStudentMap.get(gr1)).put("rollNo",distinctCount);
														passStudentList.add(gr1 + "|" + distinctCount + "||" + NewDiv1+ "|||" + presentDiv1 + "||||" + remark);
													} else {
														j = j + 1;
														remark = "EXISTING";
														hmapDivCount.put(presentDiv1, j);
														((LinkedHashMap) foundStudentMap.get(gr1)).put("rollNo",j);
														passStudentList.add(gr1 + "|" + j + "||" + presentDiv1 + "||| ||||" + remark);
													}
												}
											}
									    }
									} catch (Exception e1) {
										logger.info("Exception e ==>> " + e1);
										f.setVisible(false);
									}
								} else if (reply == JOptionPane.NO_OPTION) {
									JOptionPane.showMessageDialog(null, "Please recheck again");
								}
								try {
									if(!allomentTypeClass.equalsIgnoreCase("Manual Roll No.")){
										classAllottObj.updateClass(sessionData, foundStudentMap, section, academicYearClass,allomentTypeClass);
									}
									frame.setVisible(false);
									new ClassAllotment(sessionData, grNoClass, stdClass, divClass, lastNameClass,
											firstNameClass, fatherNameClass, null, false, academicYearClass,
											rollFromClass, rollToClass, section, user_name, user_role,"");
									// frame.setVisible(false);
									// ///////////////////
								} catch (Exception e2) {
									f.setVisible(false);
									logger.info("Exception e2 == " + e2);
								}
								f.setVisible(false);
							}
						}
					}
				});

				// /////printButton action starts///////////////////
				printButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						// foundStudentList
						logger.info("======Inside Print button action=====");
						boolean flagLeaving = true;

						classPDFObj = new ClassPDF();
						try {
							classPDFObj.ClassPDFGen(sessionData, foundStudentMap, stdClass, divClass, academicYearClass, user_name, user_role);
						} catch (Exception e1) {
							logger.info("printButton Exception e == " + e1);
						}
					}

				});
				// ends/////////////////////////////////////
			}
		}else if(!stdClass.equalsIgnoreCase(null) && !stdClass.equalsIgnoreCase("Select")){
			commonObj.showMessageDialog("No Data Found.");
		}
		// ////////////////data panel ends/////////////////////////////////

		JScrollPane jsp;
		// dataPanel.setBackground(Color.green);

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
