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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
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
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

// import com.lowagie.text.DocumentException;

public class StatementUpdate extends JFrame {

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

	static Logger logger = Logger.getLogger(StatementUpdate.class.getName());

	static JFrame frame = null;

	static List<String> foundStudentList = new ArrayList();

	static int retCount = 0;

	static int scrollHeight = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

	static List<String> allGrList = new ArrayList();

	static List<String> selectedGrList = new ArrayList();

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
    
    static String allomentTypeClass = "";
    
    static HashMap<String, String> hmapStudents = new HashMap<String, String>();

	static ClassPDF classPDFObj = new ClassPDF();
	
	static String lineLabel = "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

	static JFrame f1 = new JFrame();
	
	public StatementUpdate(SessionData sessionData1, String retGr_no, String retStd, String retDiv, String retLastName,
			String retFirstName, String retFatherName, List<String> retStudentList, boolean retSelected,
			String academicYear, String sec, String retUserName, String retUserRole, String retWorkingDays, 
			String retTuitionFee,String retAdmFee, String retTermFee, String allomentType) {

		logger.info("=============StatementUpdate=======");
		System.gc();
		/////////////////////
		Common commonObj = new Common();
		screenWidth = commonObj.screeWidth();
		screenHeight = commonObj.screeHeight();
		mainCentre = (screenWidth - 150) / 2;
		f1 = new JFrame("Please wait Statement Page is loading");
		f1.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
	    f1.setSize(500, 0);
	    f1.setResizable(false);
	    f1.setVisible(true);
	    f1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		/////////////////////
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		academicYearClass = academicYear;
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
        if(academicYearClass == null || academicYearClass.equalsIgnoreCase("")) {
        	promoteYear = commonObj.getPromoteYear(currAcademicYear);
        }
        else {
        	promoteYear = commonObj.getPromoteYear(academicYearClass);
        }
		foundStudentList.clear();
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

		academicYearClass = academicYear;
		ArrayList<String> arrlist = null;
		logger.info(grNoClass + "::" + stdClass + "::" + divClass + "::" + lastNameClass + "::" + firstNameClass + "::"
				+ fatherNameClass + "::" + academicYearClass + "::" + rollFromClass + "::" + rollToClass);

		entrytCnt = 0;
		logger.info("entrytCnt===>" + entrytCnt);

		
		try {
			try {
				if (findStudentDB.connectDatabase(sessionData)) {
					retStudentList = findStudentDB.findStatementList(sessionData, retGr_no, retStd, retDiv, retLastName, retFirstName,
							retFatherName, academicYearClass, section);
				}
			} catch (Exception e1) {
				logger.info("Exception findStatementList ===>>>" + e1);
			} finally {
				findStudentDB.closeDatabase(sessionData);
			}
			
			logger.info("retStudentList size => " + retStudentList.size());
			if(retStudentList.size() < 1 && !academicYear.equalsIgnoreCase("")){
				commonObj.showMessageDialog("No Data Found.");
			}
			arrlist = new ArrayList<String>(retStudentList);
			logger.info("arrlist => " + arrlist.size());
			foundStudentList = arrlist;
			logger.info("foundStudentList" + foundStudentList.size());
			allGrList.clear();
			setSelected = retSelected;
			int foundSize = foundStudentList.size();
			logger.info("foundSize 123=> " + foundSize);
			retCount = foundStudentList.size();
			logger.info("foundStudentList size ==> " + retCount);

			// }
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

	// public ClassAllotment(String string, String string2, String string3,
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

		JButton classAllotButton = new JButton("Class Allotment");
		classAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		classAllotButton.setBounds(130, 50, 150, 24);
		classAllotButton.setBackground(Color.GREEN);
		topbandPanel.add(classAllotButton);

		classAllotButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				new ClassAllotment(sessionData, "", "", "", "", "", "", null, false, "", "", "", section, user_name,
						user_role,"");
			}
		});

		JButton subAllotButton = new JButton("Subject Allotment");
		subAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		subAllotButton.setBounds(290, 50, 150, 24);// 620, 50, 150, 24
		topbandPanel.add(subAllotButton);

		subAllotButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				List subAllotList = new ArrayList();
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
				new MarksEntry(sessionData, "", markList, "", false, "", "", "", "", "", "", "", "", "", section,
						user_name, user_role, "", "");
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
		topbandPanel.add(attendanceButton);

		attendanceButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				List studentList = new ArrayList();
				new AttendanceUpdate(sessionData, "", "", "", section, user_name, user_role, studentList, "","","","",false,"",true,"", false);
				frame.setVisible(false);
			}
		});

		JButton reportButton = new JButton("Reports");
		reportButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		reportButton.setBounds(930, 50, 150, 24);
		topbandPanel.add(reportButton);

		reportButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// frame.setVisible(false);
				// List studList = new ArrayList();
				// new FindStudent("","", "", "", "", "", studList,section);
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

		/*gr_no_text.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				String grString = gr_no_text.getText();
				grString = grString.trim();
				if (grString.length() > 6) {
					grString = grString.substring(0, 6);
					logger.info("grString===>" + grString);
					gr_no_text.setText(grString);
				}
			}
		});

		gr_no_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusLost(java.awt.event.FocusEvent event) {

				String grString = gr_no_text.getText();
				grString = grString.trim();
				if (grString.length() < 7 && grString.length() > 0) {
					Common cm = new Common();
					gr_no_text.setText(cm.appendZero(grString));
				}
			}
		});*/
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
		// /////////////Submit///////////////
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(mainCentre - 150, 90, 130, 25);
		findPanel.add(submitButton);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean validateFields = true;
				logger.info("Clicked submit button...");
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
				logger.info("gr No.:" + gr_no);
				lastName = lastName_text.getText();
				logger.info("lastName.:" + lastName);
				firstName = firstName_text.getText();
				logger.info("firstName:" + firstName);
				fatherName = fatherName_text.getText();
				logger.info("fatherName:" + fatherName);
				std = (String) admittedStd_combo.getSelectedItem();
				logger.info("std:" + std);
				div = (String) admittedDiv_combo.getSelectedItem();
				logger.info("div:" + div);
				academicSel = (String) academicYear_combo.getSelectedItem();
				rollFromSubmit = from_text.getText();
				logger.info("rollFromSubmit:" + rollFromSubmit);
				rollToSubmit = to_text.getText();
				logger.info("rollToSubmit:" + rollToSubmit);
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
						else{
							new PromoteClass(sessionData, grNoClass, stdClass, divClass, lastNameClass, firstNameClass,
								fatherNameClass, null, false, promoteYear, rollFromClass, rollToClass, section,
								user_name, user_role,allotTypeSel);
						}
					} catch (Exception e1) {
						logger.info("Exception FindStudent form ===>> >" + e1);
//						new StatementUpdate(sessionData, grNoClass, stdClass, divClass, lastNameClass, firstNameClass,
//								fatherNameClass, null, false, academicSel, rollFromClass, rollToClass, section,
//								user_name, user_role,"");
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
				List findLCList = new ArrayList();
				frame.setVisible(false);
				new StatementUpdate(sessionData, "", "", "", "", "", "", findLCList, false, "", section,
						user_name, user_role,"","","","","");
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
		if (foundStudentList.size() > 0) {
			int listSize = foundStudentList.size();
			int height = 70;
			logger.info("foundStudentList==>" + listSize);
			
			String firdtGr = foundStudentList.get(0);
			firdtGr = firdtGr.replace(" ", "~");
			StringTokenizer st2 = new StringTokenizer(firdtGr, "|");
	    	List<String> firstStatementDetail = new ArrayList();
	    	while(st2.hasMoreTokens()){
	    		firstStatementDetail.add(st2.nextToken());
	    	}
	    	
			JLabel working_label = new JLabel("No. of Working days:");
			working_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			working_label.setBounds(40, height-70, 180, 50);
			dataPanel.add(working_label);
			
			final JTextField working_text = new JTextField();
			working_text.setText(firstStatementDetail.get(14));
			working_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			working_text.setBounds(200, height-58, 30, 25);
			dataPanel.add(working_text);
			
			working_text.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent event) {
					working_text.selectAll();
				}
			});
			
			JLabel tuition_label = new JLabel("Tuition Fee:");
			tuition_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			tuition_label.setBounds(240, height-70, 180, 50);
			dataPanel.add(tuition_label);
			
			final JTextField tuition_text = new JTextField();
			tuition_text.setText(firstStatementDetail.get(7));
			tuition_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			tuition_text.setBounds(330, height-58, 40, 25);
			dataPanel.add(tuition_text);
			
			tuition_text.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent event) {
					tuition_text.selectAll();
				}
			});
			
			JLabel adm_label = new JLabel("Adm Fee:");
			adm_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			adm_label.setBounds(380, height-70, 180, 50);
			dataPanel.add(adm_label);
			
			final JTextField adm_text = new JTextField();
			adm_text.setText(firstStatementDetail.get(8));
			adm_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			adm_text.setBounds(455, height-58, 40, 25);
			dataPanel.add(adm_text);
			
			adm_text.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent event) {
					adm_text.selectAll();
				}
			});
			
			JLabel term_label = new JLabel("Term Fee:");
			term_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			term_label.setBounds(505, height-70, 180, 50);
			dataPanel.add(term_label);
			
			final JTextField term_text = new JTextField();
			term_text.setText(firstStatementDetail.get(9));
			term_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			term_text.setBounds(580, height-58, 40, 25);
			dataPanel.add(term_text);
			
			term_text.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent event) {
					term_text.selectAll();
				}
			});
			
			JLabel failed_label = new JLabel("Failed last year:");
			failed_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			failed_label.setBounds(630, height-70, 180, 50);
			dataPanel.add(failed_label);
			
			String failed_List[] = {"Select","Yes","No"};
			final JComboBox failed_combo = new JComboBox(failed_List);
			failed_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			failed_combo.setBounds(750, height-58, 80, 25);
			dataPanel.add(failed_combo);
			
			JLabel ruralUrban_label = new JLabel("Rural/Urban:");
			ruralUrban_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			ruralUrban_label.setBounds(850, height-70, 180, 50);
			dataPanel.add(ruralUrban_label);
			
			String ruralUrban_List[] = {"Select","Rural","Urban"};
			final JComboBox ruralUrban_combo = new JComboBox(ruralUrban_List);
			ruralUrban_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			ruralUrban_combo.setBounds(960, height-58, 80, 25);
			dataPanel.add(ruralUrban_combo);
			
			final JRadioButton freeship_radio = new JRadioButton();
			freeship_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			freeship_radio.setBounds(30, height-18, 20, 20);
			freeship_radio.setSelected(false);
			dataPanel.add(freeship_radio);
			
			JLabel freeship_label = new JLabel("Freeship Date:");
			freeship_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			freeship_label.setBounds(60, height-30, 180, 50);
			dataPanel.add(freeship_label);
			
			/****freeship date picker****/
			final UtilDateModel modelFreeship = new UtilDateModel();
			/*int dayFromDate = 00;
			int monthFromDate = 00;
			int yearFromDate = 0000;
			modelFreeship.setDate(yearFromDate,monthFromDate-1,dayFromDate);*/
			modelFreeship.setSelected(true);
		    Properties pFreeship = new Properties();
		    final JDatePanelImpl datePanelFreeship = new JDatePanelImpl(modelFreeship, pFreeship);
		    datePanelFreeship.setBounds(100, height-18, 130, 26);
		    // Don't know about the formatter, but there it is...
		    final JDatePickerImpl datePickerFreeship = new JDatePickerImpl(datePanelFreeship, new DateLabelFormatter());
		    datePickerFreeship.setBounds(175, height-18, 130, 26);
		    dataPanel.add(datePickerFreeship);
		    
		    JLabel incCert_label = new JLabel("Income Certificate:");
		    incCert_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		    incCert_label.setBounds(320, height-30, 180, 50);
			dataPanel.add(incCert_label);
			
			String incCert_List[] = {"Select","Yes","No"};
			final JComboBox incCert_combo = new JComboBox(incCert_List);
			incCert_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			incCert_combo.setBounds(470, height-18, 80, 25);
			dataPanel.add(incCert_combo);
			
			JLabel note_label = new JLabel("Note: If entered value in above fields will be applicable for all students.");
			note_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			note_label.setBounds(40, height-00, 600, 50);
			note_label.setForeground(Color.RED);
			dataPanel.add(note_label);
			
			JLabel line_label = new JLabel(lineLabel);
			line_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			line_label.setBounds(30, height+18, 1100, 50);
			dataPanel.add(line_label);
			
			height = height + 40;
			final JRadioButton all_radio = new JRadioButton();
			all_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			all_radio.setBounds(40, height+13, 20, 20);
			all_radio.setSelected(true);
			all_radio.setEnabled(false);
			dataPanel.add(all_radio);

			/*JLabel sr_label = new JLabel("Roll No.");
			sr_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sr_label.setBounds(70, height+00, 120, 50);
			dataPanel.add(sr_label);

			JLabel pipe_label1 = new JLabel("|");
			pipe_label1.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label1.setBounds(130, height+00, 120, 50);
			dataPanel.add(pipe_label1);

			JLabel gr_label = new JLabel("Gr No.");
			gr_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			gr_label.setBounds(160, height+00, 120, 50);
			dataPanel.add(gr_label);

			JLabel pipe_label2 = new JLabel("|");
			pipe_label2.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label2.setBounds(230, height+00, 120, 50);
			dataPanel.add(pipe_label2);*/

			JLabel name_label = new JLabel("Name");
			name_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			name_label.setBounds(70, height+00, 120, 50);
			dataPanel.add(name_label);

			JLabel pipe_label3 = new JLabel("|");
			pipe_label3.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label3.setBounds(430, height+00, 120, 50);
			dataPanel.add(pipe_label3);

			JLabel annual_label = new JLabel("Annual");
			annual_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			annual_label.setBounds(450, height-10, 120, 50);
			dataPanel.add(annual_label);
			
			JLabel income_label = new JLabel("Income");
			income_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			income_label.setBounds(450, height+04, 120, 50);
			dataPanel.add(income_label);

			JLabel pipe_label4 = new JLabel("|");
			pipe_label4.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label4.setBounds(510, height+00, 120, 50);
			dataPanel.add(pipe_label4);
			
			JLabel noOf_label = new JLabel("No. of");
			noOf_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			noOf_label.setBounds(530, height-10, 120, 50);
			dataPanel.add(noOf_label);
			
			JLabel child_label = new JLabel("Child");
			child_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			child_label.setBounds(530, height+04, 120, 50);
			dataPanel.add(child_label);

			JLabel pipe_label5 = new JLabel("|");
			pipe_label5.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label5.setBounds(580, height+00, 120, 50);
			dataPanel.add(pipe_label5);
			
			JLabel daysAttended_label = new JLabel("Days");
			daysAttended_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			daysAttended_label.setBounds(605, height-10, 120, 50);
			dataPanel.add(daysAttended_label);
			
			JLabel byStudent_label = new JLabel("Attended");
			byStudent_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			byStudent_label.setBounds(595, height+04, 120, 50);
			dataPanel.add(byStudent_label);

			JLabel pipe_label6 = new JLabel("|");
			pipe_label6.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label6.setBounds(670, height+00, 120, 50);
			dataPanel.add(pipe_label6);
			
			JLabel rural_label = new JLabel("Rural / ");
			rural_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			rural_label.setBounds(690, height-10, 120, 50);
			dataPanel.add(rural_label);
			
			JLabel urban_label = new JLabel("Urban");
			urban_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			urban_label.setBounds(690, height+04, 120, 50);
			dataPanel.add(urban_label);

			JLabel pipe_label7 = new JLabel("|");
			pipe_label7.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label7.setBounds(765, height+00, 120, 50);
			dataPanel.add(pipe_label7);
			
			JLabel incomeC_label = new JLabel("Income");
			incomeC_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			incomeC_label.setBounds(790, height-10, 120, 50);
			dataPanel.add(incomeC_label);
			
			JLabel certificate_label = new JLabel("Certificate");
			certificate_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			certificate_label.setBounds(780, height+04, 120, 50);
			dataPanel.add(certificate_label);

			JLabel pipe_label8 = new JLabel("|");
			pipe_label8.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label8.setBounds(860, height+00, 120, 50);
			dataPanel.add(pipe_label8);
			
			JLabel freeshipDt_label = new JLabel("Freeship Date");
			freeshipDt_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			freeshipDt_label.setBounds(880, height-10, 120, 50);
			dataPanel.add(freeshipDt_label);
			
			JLabel date_label = new JLabel("(DD/MM/YYYY)");
			date_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
			date_label.setBounds(880, height+04, 120, 50);
			dataPanel.add(date_label);
			
			JLabel pipe_label9 = new JLabel("|");
			pipe_label9.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label9.setBounds(985, height+00, 120, 50);
			dataPanel.add(pipe_label9);
			
			JLabel failedYr_label = new JLabel("Failed");
			failedYr_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			failedYr_label.setBounds(1005, height-10, 120, 50);
			dataPanel.add(failedYr_label);
			
			JLabel lastYear_label = new JLabel("Last Year");
			lastYear_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			lastYear_label.setBounds(1000, height+04, 120, 50);
			dataPanel.add(lastYear_label);
			
			JLabel pipe_label10 = new JLabel("|");
			pipe_label10.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label10.setBounds(1080, height+00, 120, 50);
			dataPanel.add(pipe_label10);

			JLabel line1_label = new JLabel(lineLabel);
			line1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			line1_label.setBounds(30, height+13, 1100, 50);
			dataPanel.add(line1_label);

			String[] studentArray = new String[listSize];
			studentArray = foundStudentList.toArray(studentArray);
			logger.info("listSize === " + listSize);
			logger.info("studentArray === " + studentArray.length);

			if (listSize > 0) {
				int j = 110;
				int l = 110;
				final JRadioButton[] roll_radio = new JRadioButton[listSize];
				final JLabel[] roll_labels = new JLabel[listSize];
				final JLabel[] gr_labels = new JLabel[listSize];
				final JLabel[] name_labels = new JLabel[listSize];
				final JTextField[] annualIncome_text = new JTextField[listSize];
				final JTextField[] noOfChild_text = new JTextField[listSize];
				final JTextField[] daysAttended_text = new JTextField[listSize];
				final JComboBox[] rural_urban_combo = new JComboBox[listSize];
				final JComboBox[] incomeC_combo = new JComboBox[listSize];
				final JComboBox[] failedLast_combo = new JComboBox[listSize];
				final JTextField[] freeshipDate_text = new JTextField[listSize];
				/*final JDatePickerImpl[] datePickerFreeshipDt = new JDatePickerImpl[listSize];
				final JDatePanelImpl[] datePanelFreeshipDt = new JDatePanelImpl[listSize];*/
				
				JLabel[] line_labels = new JLabel[listSize];
				JLabel[] pipe_labels1 = new JLabel[listSize];
				JLabel[] pipe_labels2 = new JLabel[listSize];
				JLabel[] pipe_labels3 = new JLabel[listSize];
				JLabel[] pipe_labels4 = new JLabel[listSize];
				JLabel[] pipe_labels5 = new JLabel[listSize];

				for (int i = 0; i < listSize; i++) {
					j = j + 30;
					l = j + 50;
					String grDetail = foundStudentList.get(i);
					grDetail = grDetail.replace(" ", "~");
					StringTokenizer st1 = new StringTokenizer(grDetail, "|");
			    	List<String> statementDetaillist = new ArrayList();
			    	while(st1.hasMoreTokens()){
			    		statementDetaillist.add(st1.nextToken());
			    	}
			    	
					String classList = "";
					String name = statementDetaillist.get(0);
					name = name.replace("*", " ");
					final String gr = statementDetaillist.get(1);
					
					hmapStudents.put(gr, foundStudentList.get(i));

					roll_radio[i] = new JRadioButton();
					roll_radio[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					roll_radio[i].setBounds(40, j + 13, 20, 20);
					roll_radio[i].setToolTipText(gr);
					roll_radio[i].setSelected(true);
					roll_radio[i].setEnabled(false);
					dataPanel.add(roll_radio[i]);

					/*roll_labels[i] = new JLabel((i+1)+"");
					roll_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					roll_labels[i].setBounds(90, j, 120, 50);
					dataPanel.add(roll_labels[i]);

					pipe_labels1[i] = new JLabel("|");
					pipe_labels1[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels1[i].setBounds(130, j, 120, 50);
					dataPanel.add(pipe_labels1[i]);

					gr_labels[i] = new JLabel(gr);
					gr_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
//					gr_labels[i].setBounds(160, j, 120, 50);
//					dataPanel.add(gr_labels[i]);

					pipe_labels2[i] = new JLabel("|");
					pipe_labels2[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels2[i].setBounds(230, j, 120, 50);
					dataPanel.add(pipe_labels2[i]);*/
					name_labels[i] = new JLabel(name);
					name_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					name_labels[i].setBounds(70, j+10, 350, 30);
					name_labels[i].setToolTipText(name);
					dataPanel.add(name_labels[i]);

					pipe_labels3[i] = new JLabel("|");
					pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels3[i].setBounds(430, j, 120, 50);
					dataPanel.add(pipe_labels3[i]);
					
					annualIncome_text[i] = new JTextField();
					annualIncome_text[i].setText(statementDetaillist.get(10));
					annualIncome_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					annualIncome_text[i].setBounds(445, j+12, 60, 20);
					dataPanel.add(annualIncome_text[i]);

					pipe_labels3[i] = new JLabel("|");
					pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels3[i].setBounds(510, j, 120, 50);
					dataPanel.add(pipe_labels3[i]);
					
					noOfChild_text[i] = new JTextField();
					noOfChild_text[i].setText(statementDetaillist.get(13));
					noOfChild_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					noOfChild_text[i].setBounds(525, j+12, 50, 20);
					dataPanel.add(noOfChild_text[i]);

					pipe_labels3[i] = new JLabel("|");
					pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels3[i].setBounds(580, j, 120, 50);
					dataPanel.add(pipe_labels3[i]);
					
					daysAttended_text[i] = new JTextField();
					daysAttended_text[i].setText(statementDetaillist.get(15));
					daysAttended_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					daysAttended_text[i].setBounds(600, j+12, 50, 20);
					dataPanel.add(daysAttended_text[i]);

					pipe_labels3[i] = new JLabel("|");
					pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels3[i].setBounds(670, j, 120, 50);
					dataPanel.add(pipe_labels3[i]);
					
					rural_urban_combo[i] = new JComboBox(ruralUrban_List);
					rural_urban_combo[i].setSelectedItem(statementDetaillist.get(11).trim());
					rural_urban_combo[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					rural_urban_combo[i].setBounds(685, j+12, 75, 20);
					dataPanel.add(rural_urban_combo[i]);
					
					pipe_labels3[i] = new JLabel("|");
					pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels3[i].setBounds(765, j, 120, 50);
					dataPanel.add(pipe_labels3[i]);
					
					incomeC_combo[i] = new JComboBox(incCert_List);
					incomeC_combo[i].setSelectedItem(statementDetaillist.get(12));
					incomeC_combo[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					incomeC_combo[i].setBounds(780, j+12, 75, 20);
					dataPanel.add(incomeC_combo[i]);
					
					pipe_labels3[i] = new JLabel("|");
					pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels3[i].setBounds(860, j, 120, 50);
					dataPanel.add(pipe_labels3[i]);
					
					
					freeshipDate_text[i] = new JTextField();
					freeshipDate_text[i].setText(statementDetaillist.get(6));
					freeshipDate_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					freeshipDate_text[i].setBounds(880, j+12, 100, 20);
					dataPanel.add(freeshipDate_text[i]);
					/****freeship date picker****/
					/*final UtilDateModel modelFreeshipDt = new UtilDateModel();
					String selDate = statementDetaillist.get(6);
					if(!selDate.equalsIgnoreCase("")){
						int dayFromDate = Integer.parseInt(selDate.substring(selDate.lastIndexOf("-")+1));;
						int monthFromDate = Integer.parseInt(selDate.substring(selDate.indexOf("-")+1,selDate.lastIndexOf("-")));;
						int yearFromDate = Integer.parseInt(selDate.substring(0,4));
						modelFreeshipDt.setDate(yearFromDate,monthFromDate-1,dayFromDate);
					}
					modelFreeshipDt.setSelected(true);
				    Properties pFreeshipDt = new Properties();
				    datePanelFreeshipDt[i] = new JDatePanelImpl(modelFreeshipDt, pFreeshipDt);
				    datePanelFreeshipDt[i].setBounds(870, j+12, 110, 26);
				    // Don't know about the formatter, but there it is...
				    datePickerFreeshipDt[i] = new JDatePickerImpl(datePanelFreeshipDt[i], new DateLabelFormatter());
				    datePickerFreeshipDt[i].setBounds(870, j+12, 110, 20);
				    dataPanel.add(datePickerFreeshipDt[i]);*/
				    
					pipe_labels3[i] = new JLabel("|");
					pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels3[i].setBounds(985, j, 120, 50);
					dataPanel.add(pipe_labels3[i]);
					
					failedLast_combo[i] = new JComboBox(failed_List);
					failedLast_combo[i].setSelectedItem(statementDetaillist.get(3));
					failedLast_combo[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					failedLast_combo[i].setBounds(1000, j+12, 75, 20);
					dataPanel.add(failedLast_combo[i]);
					
				    pipe_labels3[i] = new JLabel("|");
					pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels3[i].setBounds(1080, j, 120, 50);
					dataPanel.add(pipe_labels3[i]);

					line_labels[i] = new JLabel(lineLabel);
					line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					line_labels[i].setBounds(30, j + 10, 1100, 50);
					dataPanel.add(line_labels[i]);

					final int k = i;
					annualIncome_text[i].addFocusListener(new FocusListener() {

						String grDetail = "";
						String valueChanged = "";
						String annualIncome = "";

						@Override
						public void focusGained(FocusEvent arg0) {
							annualIncome = annualIncome_text[k].getText();
							annualIncome_text[k].selectAll();
						}
						@Override
						public void focusLost(FocusEvent arg0) {
							
							int m = 0;
							int tokenSize = 0;
							valueChanged = annualIncome_text[k].getText();
							if(!valueChanged.equalsIgnoreCase(annualIncome)){
								grDetail =  hmapStudents.get(gr).replace(" ", "~");
								StringTokenizer st = new StringTokenizer(hmapStudents.get(gr), "|");
								tokenSize = st.countTokens();
								String[] columnArray = new String[tokenSize];
								while (st.hasMoreTokens()) {
									columnArray[m] = st.nextToken().replace("~", "");
									m++;
								}
								grDetail = columnArray[0] + "|" + columnArray[1] + "|" + columnArray[2] + "|" + columnArray[3] + "|" 
										   + columnArray[4] + "|" + columnArray[5] + "|" + columnArray[6] + "|" 
										   + columnArray[7] + "|" + columnArray[8] + "|" + columnArray[9] + "|" + valueChanged + "|" 
										   + columnArray[11] + "|" + columnArray[12] + "|" 
										   + columnArray[13] + "|" + columnArray[14] + "|" + columnArray[15] + "|" + columnArray[16];
								hmapStudents.remove(gr);
								hmapStudents.put(gr,grDetail);
							}
						}
					});
					
					noOfChild_text[i].addFocusListener(new FocusListener() {

						String grDetail = "";
						String valueChanged = "";
						String noOfChild = "";

						@Override
						public void focusGained(FocusEvent arg0) {
							noOfChild = noOfChild_text[k].getText();
							noOfChild_text[k].selectAll();
						}
						@Override
						public void focusLost(FocusEvent arg0) {
							
							int m = 0;
							int tokenSize = 0;
							valueChanged = noOfChild_text[k].getText();
							if(!valueChanged.equalsIgnoreCase(noOfChild)){
								grDetail =  hmapStudents.get(gr).replace(" ", "~");
								StringTokenizer st = new StringTokenizer(hmapStudents.get(gr), "|");
								tokenSize = st.countTokens();
								String[] columnArray = new String[tokenSize];
								while (st.hasMoreTokens()) {
									columnArray[m] = st.nextToken().replace("~", "");
									m++;
								}
								grDetail = columnArray[0] + "|" + columnArray[1] + "|" + columnArray[2] + "|" + columnArray[3] + "|" 
										   + columnArray[4] + "|" + columnArray[5] + "|" + columnArray[6] + "|" 
										   + columnArray[7] + "|" + columnArray[8] + "|" + columnArray[9] + "|" + columnArray[10] + "|" 
										   + columnArray[11] + "|" + columnArray[12] + "|" 
										   + valueChanged + "|" + columnArray[14] + "|" + columnArray[15] + "|" + columnArray[16];
								hmapStudents.remove(gr);
								hmapStudents.put(gr,grDetail);
							}
						}
					});
					
					daysAttended_text[i].addFocusListener(new FocusListener() {

						String grDetail = "";
						String valueChanged = "";
						String daysAttended = "";

						@Override
						public void focusGained(FocusEvent arg0) {
							daysAttended = daysAttended_text[k].getText();
							daysAttended_text[k].selectAll();
						}
						@Override
						public void focusLost(FocusEvent arg0) {
							
							int m = 0;
							int tokenSize = 0;
							valueChanged = daysAttended_text[k].getText();
							if(!valueChanged.equalsIgnoreCase(daysAttended)){
								grDetail =  hmapStudents.get(gr).replace(" ", "~");
								StringTokenizer st = new StringTokenizer(hmapStudents.get(gr), "|");
								tokenSize = st.countTokens();
								String[] columnArray = new String[tokenSize];
								while (st.hasMoreTokens()) {
									columnArray[m] = st.nextToken().replace("~", "");
									m++;
								}
								grDetail = columnArray[0] + "|" + columnArray[1] + "|" + columnArray[2] + "|" + columnArray[3] + "|" 
										   + columnArray[4] + "|" + columnArray[5] + "|" + columnArray[6] + "|" 
										   + columnArray[7] + "|" + columnArray[8] + "|" + columnArray[9] + "|" + columnArray[10] + "|" 
										   + columnArray[11] + "|" + columnArray[12] + "|" 
										   + columnArray[13] + "|" + columnArray[14] + "|" + valueChanged + "|" + columnArray[16];
								hmapStudents.remove(gr);
								hmapStudents.put(gr,grDetail);
							}
						}
					});
					
					rural_urban_combo[i].addFocusListener(new FocusListener() {

						String grDetail = "";
						String valueChanged = "";
						String rural_urban = "";

						@Override
						public void focusGained(FocusEvent arg0) {
							rural_urban = rural_urban_combo[k].getSelectedItem().toString();
						}
						@Override
						public void focusLost(FocusEvent arg0) {
							
							int m = 0;
							int tokenSize = 0;
							valueChanged = rural_urban_combo[k].getSelectedItem().toString();
							if(!valueChanged.equalsIgnoreCase(rural_urban)){
								grDetail =  hmapStudents.get(gr).replace(" ", "~");
								StringTokenizer st = new StringTokenizer(hmapStudents.get(gr), "|");
								tokenSize = st.countTokens();
								String[] columnArray = new String[tokenSize];
								while (st.hasMoreTokens()) {
									columnArray[m] = st.nextToken().replace("~", "");
									m++;
								}
								grDetail = columnArray[0] + "|" + columnArray[1] + "|" + columnArray[2] + "|" + columnArray[3] + "|" 
										   + columnArray[4] + "|" + columnArray[5] + "|" + columnArray[6] + "|" 
										   + columnArray[7] + "|" + columnArray[8] + "|" + columnArray[9] + "|" + columnArray[10] + "|" 
										   + valueChanged + "|" + columnArray[12] + "|" 
										   + columnArray[13] + "|" + columnArray[14] + "|" + columnArray[15] + "|" + columnArray[16];
								hmapStudents.remove(gr);
								hmapStudents.put(gr,grDetail);
							}
						}
					});
					
					incomeC_combo[i].addFocusListener(new FocusListener() {

						String grDetail = "";
						String valueChanged = "";
						String incomeC = "";

						@Override
						public void focusGained(FocusEvent arg0) {
							incomeC = incomeC_combo[k].getSelectedItem().toString();
						}
						@Override
						public void focusLost(FocusEvent arg0) {
							
							int m = 0;
							int tokenSize = 0;
							valueChanged = incomeC_combo[k].getSelectedItem().toString();
							if(!valueChanged.equalsIgnoreCase(incomeC)){
								grDetail =  hmapStudents.get(gr).replace(" ", "~");
								StringTokenizer st = new StringTokenizer(hmapStudents.get(gr), "|");
								tokenSize = st.countTokens();
								String[] columnArray = new String[tokenSize];
								while (st.hasMoreTokens()) {
									columnArray[m] = st.nextToken().replace("~", "");
									m++;
								}
								grDetail = columnArray[0] + "|" + columnArray[1] + "|" + columnArray[2] + "|" + columnArray[3] + "|" 
										   + columnArray[4] + "|" + columnArray[5] + "|" + columnArray[6] + "|" 
										   + columnArray[7] + "|" + columnArray[8] + "|" + columnArray[9] + "|" + columnArray[10] + "|" 
										   + columnArray[11] + "|" + valueChanged + "|" 
										   + columnArray[13] + "|" + columnArray[14] + "|" + columnArray[15] + "|" + columnArray[16];
								hmapStudents.remove(gr);
								hmapStudents.put(gr,grDetail);
							}
						}
					});
					
					freeshipDate_text[i].addFocusListener(new FocusListener() {

						String grDetail = "";
						String valueChanged = "";
						String freeshipDate = "";

						@Override
						public void focusGained(FocusEvent arg0) {
							freeshipDate = freeshipDate_text[k].getText();
							freeshipDate_text[k].selectAll();
						}
						@Override
						public void focusLost(FocusEvent arg0) {
							
							int m = 0;
							int tokenSize = 0;
							valueChanged = freeshipDate_text[k].getText();
							if(!valueChanged.equalsIgnoreCase(freeshipDate) && commonObj.validateDate(valueChanged)){
								grDetail =  hmapStudents.get(gr).replace(" ", "~");
								StringTokenizer st = new StringTokenizer(hmapStudents.get(gr), "|");
								tokenSize = st.countTokens();
								String[] columnArray = new String[tokenSize];
								while (st.hasMoreTokens()) {
									columnArray[m] = st.nextToken().replace("~", "");
									m++;
								}
								grDetail = columnArray[0] + "|" + columnArray[1] + "|" + columnArray[2] + "|" + columnArray[3] + "|" 
										   + columnArray[4] + "|" + columnArray[5] + "|" + valueChanged + "|" 
										   + columnArray[7] + "|" + columnArray[8] + "|" + columnArray[9] + "|" + columnArray[10] + "|" 
										   + columnArray[11] + "|" + columnArray[12] + "|" 
										   + columnArray[13] + "|" + columnArray[14] + "|" + columnArray[15] + "|" + columnArray[16];
								hmapStudents.remove(gr);
								hmapStudents.put(gr,grDetail);
							}
							else if(!commonObj.validateDate(valueChanged) && !valueChanged.equalsIgnoreCase("")){
								commonObj.showMessageDialog("Please enter valid date");
								freeshipDate_text[k].requestFocus();
							}
						}
					});
					/*datePanelFreeshipDt[i].addFocusListener(new FocusListener() {

						String grDetail = "";
						String valueChanged = "";
						String datePickerFree = "";

						@Override
						public void focusGained(FocusEvent arg0) {
							Date freeshipDateArr = (Date) datePickerFreeshipDt[k].getModel().getValue();
					        datePickerFree = commonObj.dateToDDMMYYYY(freeshipDateArr);
						}
						@Override
						public void focusLost(FocusEvent arg0) {
							
							int m = 0;
							int tokenSize = 0;
							Date freeshipDateArr = (Date) datePickerFreeshipDt[k].getModel().getValue();
					        valueChanged = commonObj.dateToDDMMYYYY(freeshipDateArr);
					        
							if(!valueChanged.equalsIgnoreCase(datePickerFree)){
								grDetail =  hmapStudents.get(gr).replace(" ", "~");
								StringTokenizer st = new StringTokenizer(hmapStudents.get(gr), "|");
								tokenSize = st.countTokens();
								String[] columnArray = new String[tokenSize];
								while (st.hasMoreTokens()) {
									columnArray[m] = st.nextToken().replace("~", "");
									m++;
								}
								grDetail = columnArray[0] + "|" + columnArray[1] + "|" + columnArray[2] + "|" + columnArray[3] + "|" 
										   + columnArray[4] + "|" + columnArray[5] + "|" + valueChanged + "|" 
										   + columnArray[7] + "|" + columnArray[8] + "|" + columnArray[9] + "|" + columnArray[10] + "|" 
										   + columnArray[11] + "|" + columnArray[12] + "|" 
										   + columnArray[13] + "|" + columnArray[14] + "|" + columnArray[15] + "|" + columnArray[16];
								hmapStudents.remove(gr);
								hmapStudents.put(gr,grDetail);
							}
						}
					});*/
					
					failedLast_combo[i].addFocusListener(new FocusListener() {

						String grDetail = "";
						String valueChanged = "";
						String failedLast = "";

						@Override
						public void focusGained(FocusEvent arg0) {
							failedLast = failedLast_combo[k].getSelectedItem().toString();
						}
						@Override
						public void focusLost(FocusEvent arg0) {
							
							int m = 0;
							int tokenSize = 0;
							valueChanged = failedLast_combo[k].getSelectedItem().toString();
							if(!valueChanged.equalsIgnoreCase(failedLast)){
								grDetail =  hmapStudents.get(gr).replace(" ", "~");
								StringTokenizer st = new StringTokenizer(hmapStudents.get(gr), "|");
								tokenSize = st.countTokens();
								String[] columnArray = new String[tokenSize];
								while (st.hasMoreTokens()) {
									columnArray[m] = st.nextToken().replace("~", "");
									m++;
								}
								grDetail = columnArray[0] + "|" + columnArray[1] + "|" + columnArray[2] + "|" + valueChanged + "|" 
										   + columnArray[4] + "|" + columnArray[5] + "|" + columnArray[6] + "|" 
										   + columnArray[7] + "|" + columnArray[8] + "|" + columnArray[9] + "|" + columnArray[10] + "|" 
										   + columnArray[11] + "|" + columnArray[12] + "|" 
										   + columnArray[13] + "|" + columnArray[14] + "|" + columnArray[15] + "|" + columnArray[16];
								hmapStudents.remove(gr);
								hmapStudents.put(gr,grDetail);
							}
						}
					});
				}
				// /////Arrange Roll No. starts/////////////////////////////////

				JButton updateButton = new JButton("Update Statement");
				updateButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				updateButton.setBounds(mainCentre - 120, l + 5, 170, 25);
				dataPanel.add(updateButton);

				/*JButton printButton = new JButton("Print");
				printButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				printButton.setBounds(mainCentre + 50, l + 5, 130, 25);
				dataPanel.add(printButton);*/

				JLabel sep_label = new JLabel(
						"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				sep_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				sep_label.setBounds(40, l - 30, 1600, 50);
				dataPanel.add(sep_label);

				JLabel sep1_label = new JLabel(
						"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				sep1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				sep1_label.setBounds(40, l + 10, 1600, 50);
				dataPanel.add(sep1_label);
				// /////////////////////////////

				// //////All sr radio action//////////////
				all_radio.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						boolean pass_selected = all_radio.isSelected();
						// logger.info("pass_selected : "+pass_selected);
						frame.setVisible(false);
						// logger.info(" list size : "+foundStudentList.size());
						new StatementUpdate(sessionData, "", "", "", "", "", "", foundStudentList, pass_selected, "", 
								section, user_name, user_role,"","","","",allomentTypeClass);
					}
				});

				// /////updateButton action starts///////////////////
				updateButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						logger.info("======Inside Update button action=====");
						Common commonObj1 = new Common();
						boolean flagLeaving = true;
						List<String> passStudentList = new ArrayList();

						String workingDays = working_text.getText().trim();
						String tuitionFee = tuition_text.getText().trim();
						String admFee = adm_text.getText().trim();
						String termFee = term_text.getText().trim();
						String failLastYear = failed_combo.getSelectedItem().toString();
						String ruralUrban = ruralUrban_combo.getSelectedItem().toString();
						Date freeshipDate = (Date) datePickerFreeship.getModel().getValue();
				        String freeshipDateStr = commonObj.dateToDDMMYYYY(freeshipDate);
						String incomeCert = incCert_combo.getSelectedItem().toString();
						if(!freeship_radio.isSelected()){
							freeshipDateStr = "";
						}
						logger.info("setSelected = " + setSelected);
						logger.info("selectedGrList = " + hmapStudents.size());
						logger.info("allGrList before if = " + allGrList.size());
						if (hmapStudents.size() == 0) {
							JOptionPane.showMessageDialog(null, "No Student selected");
						} else if (flagLeaving) {
							if (flagLeaving) {
								int reply = JOptionPane.showConfirmDialog(null,
										"Would You Like to Update AB Statement?", "Confirm Update",
										JOptionPane.YES_NO_OPTION);
								if (reply == JOptionPane.YES_OPTION) {
									try {
										// /////////////////////////////
										String remark = "";
										int listSize = hmapStudents.size();
										if(findStudentDB.connectDatabase(sessionData)){
											JFrame f = new JFrame("Statement Data updated in progress. Please wait & Don't Close");
											f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
										    f.setSize(500, 0);
										    f.setResizable(false);
										    f.setVisible(true);
										    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
											findStudentDB.updateStatement(sessionData, workingDays, tuitionFee, admFee, 
													termFee, failLastYear, ruralUrban, freeshipDateStr, incomeCert, 
													academicYearClass, section, hmapStudents);
											f.setVisible(false);
											commonObj.showMessageDialog("Statement Data updated Sucessfully");
										}
										frame.setVisible(false);
										new StatementUpdate(sessionData, grNoClass, (String) admittedStd_combo.getSelectedItem(), 
												(String) admittedDiv_combo.getSelectedItem(), lastNameClass, firstNameClass,
												fatherNameClass, null, false, academicYearClass, section,
												user_name, user_role,workingDays,tuitionFee,admFee,termFee,"Update AB Form");
									} catch (Exception e1) {
										logger.info("Exception e == " + e1);
										e1.printStackTrace();
									} finally {
										findStudentDB.closeDatabase(sessionData);
									}
								} else if (reply == JOptionPane.NO_OPTION) {
									JOptionPane.showMessageDialog(null, "Please recheck again");
								}
							}
						}
					}
				});

				// /////printButton action starts///////////////////
				/*printButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						// foundStudentList
						logger.info("======Inside Print button action=====");
						boolean flagLeaving = true;

						classPDFObj = new ClassPDF();
						try {
							classPDFObj.ClassPDFGen(sessionData, foundStudentList, stdClass, divClass, academicYearClass, user_name,
									user_role);
						} catch (Exception e1) {
							logger.info("printButton Exception e == " + e1);
						}
					}

				});*/
			}
		}else if(!stdClass.equalsIgnoreCase(null) && !stdClass.equalsIgnoreCase("Select")){
			commonObj.showMessageDialog("No Data Found.");
		}
		// ////////////////data panel ends/////////////////////////////////
		f1.setVisible(false);
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
