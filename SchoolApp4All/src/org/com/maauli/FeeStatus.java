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
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.formula.functions.Isref;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.util.Date;
import java.util.LinkedHashMap; 

public class FeeStatus extends JFrame {

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

	static boolean setSelected = false;

	static boolean printDuplicate = false;

	static String categoryList = "";
	
	static String monthlyList = "";

	static String grNoClass = "";

	static String stdClass = "";

	static String divClass = "";
	
	static String catTypeClass = "";
	
	static String updateAllClass = "";
	
	static boolean isRecordNewClass = false;
	
	static String fromDateClass = "";
	
	static String toDateClass = "";
	
	static String currAcademicYear = "";

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
	
    static String app_header = "";
    
    static String app_header_0 = "";

	static DBValidate dbValidate = new DBValidate();

	static SessionData sessionData = new SessionData();

	static Common commonObj = new Common();

	static String std = "";

	static String div = "";

	static String section = "";
	
	static String feeStatusList = "";

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
	
	static List<String> recordsNotInFeeStatusList = null;
	
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
    static String lc_visible_master_only = "";
    static boolean lc_visible_flag = true;
    
	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static Logger logger = Logger.getLogger(FeeStatus.class.getName());
	
	public FeeStatus(SessionData sessionData1, String retGr,String retlastName,String retfirstName,String retfatherName,
			String retrollFrom,String retrollTo, String retStd, String retDiv, String academicYear, String sec, 
			List<String> retStudentList, boolean isRecordNew, String retupdateAll, boolean isEdit) {

		System.gc();
		sessionData = sessionData1;
		isEditClass = isEdit;
		user_name = sessionData1.getUserName();
		user_role = sessionData1.getUserRole();
		stdClass = retStd;
		divClass = retDiv;
		grNoClass = retGr;
		lastNameClass = retlastName;
		firstNameClass = retfirstName;
		fatherNameClass = retfatherName;
		rollFromClass = retrollFrom;
		rollToClass = retrollTo;
		updateAllClass = retupdateAll;
		isRecordNewClass = isRecordNew;
		logger.info("retStd :: " + retStd);
		logger.info("retDiv :: " + retDiv);
		currAcademicYear = commonObj.getAcademicYear(commonObj.getCurrentDate());
		logger.info("currAcademicYear :: " + currAcademicYear);
		logger.info("academicYear :: " + academicYear);

		section = sec;
		logger.info("section :: " + section);
		std = bundle.getString(section.toUpperCase() + "_STD");
		logger.info("std :: " + std);
		div = bundle.getString(section.toUpperCase() + "_DIV");
		logger.info("div :: " + div);
		secName = bundle.getString(section.toUpperCase() + "_SEC");
		logger.info("secName :: " + secName);
		categoryList = bundle.getString("ATTENDANCE_CATEGORY");
		feeStatusList = bundle.getString("FEE_STATUS");
		monthlyList = bundle.getString("MONTH_LIST");

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
		lc_visible_master_only = bundle.getString("LC_VISIBLE_MASTER_ONLY");
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
		
		if(lc_visible_master_only.equalsIgnoreCase("true") && !sessionData.getConfigMap().get("SchoolApp_IP").contains("127.0.0.1")) {
    		lc_visible_flag = false;
    	}
    	else {
    		lc_visible_flag = true;
    	}
		
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
		studentButton.setBackground(Color.GREEN);
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

		int buttonX = 20;
		
		JLabel menuBandTitle = new JLabel(secName);
		menuBandTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		menuBandTitle.setForeground(Color.orange);
		menuBandTitle.setBounds(buttonX, 0, 600, 30);
		topbandPanel.add(menuBandTitle);

		JLabel subMenuTitle = new JLabel("Student");
		subMenuTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		subMenuTitle.setForeground(Color.orange);
		subMenuTitle.setBounds(buttonX, 45, 100, 30);
		topbandPanel.add(subMenuTitle);

		buttonX = buttonX + 110;
		JButton admissionButton = new JButton("Admission");
		admissionButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		admissionButton.setBounds(buttonX, 50, 150, 24);
		topbandPanel.add(admissionButton);

		admissionButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LinkedHashMap<String,LinkedHashMap<String, String>> studentList = new LinkedHashMap<String,LinkedHashMap<String, String>>();
				new AdmissionFormNew(sessionData, section, user_name, user_role,"ADMISSION","",studentList);
			}
		});

		buttonX = buttonX + 160;
		JButton strengthButton = new JButton("Strength");
		strengthButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		strengthButton.setBounds(buttonX, 50, 150, 24); // 300, 50, 150, 24
		topbandPanel.add(strengthButton);

		strengthButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				List studList = new ArrayList();
				new Strength(sessionData, "", "", "", section, user_name, user_role, studList, "", "");
				frame.setVisible(false);
			}
		});

		if(lc_visible_flag) {
			buttonX = buttonX + 160;
			JButton lcButton = new JButton("Leaving certificate");
			lcButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
			lcButton.setBounds(buttonX, 50, 150, 24);
			topbandPanel.add(lcButton);
	
			lcButton.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent e) {
	
					frame.setVisible(false);
					List findLCList = new ArrayList();
					new FindLeavingCert(sessionData, "", "", "", "", "", "", findLCList, false, "", "", "", "",
							"", "", section, "", user_name, user_role,"","");
				}
			});
		}
		buttonX = buttonX + 160;
		JButton bonafideButton = new JButton("Certificate");
		bonafideButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		bonafideButton.setBounds(buttonX, 50, 150, 24);
		topbandPanel.add(bonafideButton);

		bonafideButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				List findBonafideList = new ArrayList();
				new FindBonafide(sessionData, "", "", "", "", "", "", findBonafideList, false, 
						"", section, "", user_name, user_role, false);
			}
		});

		buttonX = buttonX + 160;
		JButton PrintListButton = new JButton("Print List");
		PrintListButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		PrintListButton.setBounds(buttonX, 50, 150, 24); // 300, 50, 150, 24
		topbandPanel.add(PrintListButton);

		PrintListButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				List studList = new ArrayList();
				new PrintList(sessionData, "", "", "", section, user_name, user_role, studList, "", "");
				frame.setVisible(false);
			}
		});

		JButton feeButton = new JButton("Fee Status");
		feeButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		feeButton.setBounds(940, 50, 150, 24); // 300, 50, 150, 24
		feeButton.setBackground(Color.GREEN);
//		topbandPanel.add(feeButton);

		feeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				List studentList = new ArrayList();
				frame.setVisible(false);
				new FeeStatus(sessionData, "", "", "", "", "", "", "", "", "", section, studentList, false,"",true);
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
		
		// /////////////GR No.//////////////
		JLabel gr_no_label = new JLabel("G.R. No.:");
		gr_no_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		gr_no_label.setBounds(60, 00, 70, 50);
//		findPanel.add(gr_no_label);

		final JTextField gr_no_text = new JTextField();
		gr_no_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		gr_no_text.setBounds(160, 12, 130, 25);
//		findPanel.add(gr_no_text);

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

		String dispStd = std;
		String[] stdList = dispStd.split(",");
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

		String dispDiv = div;
		String[] divListView = dispDiv.split(",");
		final JComboBox admittedDiv_combo = new JComboBox(divListView);
		admittedDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedDiv_combo.setBounds(580, 12, 100, 25);
		findPanel.add(admittedDiv_combo);
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
//		findPanel.add(lastName_label);

		final JTextField lastName_text = new JTextField();
		lastName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_text.setBounds(160, 50, 130, 25);
//		findPanel.add(lastName_text);
		// //////////////////////////////////
		// /////////////First Name//////////////
		JLabel firstName_label = new JLabel("First Name :");
		firstName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_label.setBounds(380, 40, 120, 50);
//		findPanel.add(firstName_label);

		final JTextField firstName_text = new JTextField();
		firstName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_text.setBounds(480, 50, 200, 25);
//		findPanel.add(firstName_text);
		// //////////////////////////////////
		// /////////////Father Name//////////////
		JLabel fatherName_label = new JLabel("Father Name :");
		fatherName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_label.setBounds(710, 40, 120, 50);
//		findPanel.add(fatherName_label);

		final JTextField fatherName_text = new JTextField();
		fatherName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_text.setBounds(830, 50, 200, 25);
//		findPanel.add(fatherName_text);
		// //////////////////////////////////
		// /////////GR radio///////////////
		final JRadioButton gr_no_radio = new JRadioButton();
		gr_no_radio.setBounds(30, 15, 20, 20);
//		findPanel.add(gr_no_radio);
		// /////////name radio///////////////30, 80, 120, 50
		final JRadioButton name_radio = new JRadioButton();
		name_radio.setBounds(30, 55, 20, 20);
//		findPanel.add(name_radio);
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

		String academicYearList[] = { currAcademicYear };
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academicYear_combo.setBounds(160, 95, 120, 25);
		academicYear_combo.setEnabled(false);
		findPanel.add(academicYear_combo);

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
		
		admittedStd_combo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
					String stdSel = (String) admittedStd_combo.getSelectedItem();
					String acadSel = (String) academicYear_combo.getSelectedItem();
					if(!stdSel.equalsIgnoreCase("All") && !stdSel.equalsIgnoreCase("Select") && dbValidate.connectDatabase(sessionData)){
						admittedDiv_combo.removeAllItems();
//						admittedDiv_combo.addItem("All");
						String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdSel, section, 
								"PRESENT_DIV", "PRESENT_STD", "class_allotment",acadSel);
						
						for (String retval: divAvailabe.split(",")) {
							admittedDiv_combo.addItem(retval);
						}
					}
				} catch (Exception e1) {
					logger.info("Exception insertFormData ===>>>" + e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});
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

		// //////////////////////////////////
		// admittedStd_combo.addActionListener(new ActionListener(){
		// public void actionPerformed(ActionEvent e) {
		// if((String) admittedStd_combo.getSelectedItem() != ""){
		// admittedDiv_combo.setEnabled(true);
		// }
		// else{
		// admittedDiv_combo.setSelectedItem("");
		// admittedDiv_combo.setEnabled(false);
		// }
		//
		// }
		// });
		// //////////////////////////////////
		// admittedDiv_combo.addActionListener(new ActionListener(){
		// public void actionPerformed(ActionEvent e) {
		// if((String) admittedDiv_combo.getSelectedItem() != ""){
		// from_text.setEnabled(true);
		// to_text.setEnabled(true);
		// }
		// else{
		// from_text.setText("");
		// from_text.setEnabled(false);
		// to_text.setText("");
		// to_text.setEnabled(false);
		// }
		//
		// }
		// });

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

				/*if (academic_radio.isSelected() && !academicSel.equalsIgnoreCase("Year")) {
					academicSel = academicSel;
				} else {
					academicSel = "";
				}*/
				logger.info("academicSel == " + academicSel);
				List<String> studentList;
				logger.info("validateFields flag..." + validateFields);
				
				if (validateFields) {//validateFields
					try {
						if(dbValidate.connectDatabase(sessionData)){
							foundStudentList.clear();
							////First check for records not present in fee_status table
							recordsNotInFeeStatusList = dbValidate.recordsNotInFeeStatus(sessionData, gr_no, lastName, firstName, fatherName, std, div, academicSel, section, rollFromSubmit, rollToSubmit, "UNPAID");
							if (recordsNotInFeeStatusList.size() > 0) {
								foundStudentList = recordsNotInFeeStatusList;
								logger.info("Inserting new data to FeeStatus..");
								dbValidate.insertStudentFeeStatus(sessionData, foundStudentList, academicYearClass, 
										stdClass, divClass, false, section, updateAllClass);
								logger.info("Inserting new data to FeeStatus completed..");
//								frame.setVisible(false);
//								new FeeStatus(sessionData, gr_no, lastName, firstName, fatherName, rollFromSubmit, rollToSubmit, 
//										std, div, academicSel, section, foundStudentList, true, "", true);
							} 
//							else {
							////check for records present in fee status table
								recordsNotInFeeStatusList = dbValidate.recordsFoundInFeeStatusForYear(sessionData, std, div, academicSel, section, "");
								if(recordsNotInFeeStatusList.size() <= 0){
									commonObj.showMessageDialog("No data found.");
									frame.setVisible(false);
									new FeeStatus(sessionData, gr_no, lastName, firstName, fatherName, rollFromSubmit, rollToSubmit, 
											std, div, academicSel, section, foundStudentList, false, "", true);
								}
								else{
									foundStudentList = recordsNotInFeeStatusList;
									frame.setVisible(false);
									new FeeStatus(sessionData, gr_no, lastName, firstName, fatherName, rollFromSubmit, rollToSubmit, 
											std, div, academicSel, section, foundStudentList, false, "", false);
								}
//							}
						}
					} catch (Exception e1) {
						logger.info("Exception Strength ===>>>" + e1);
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
				new FeeStatus(sessionData, "", "", "", "", "", "", "", "", "", section, studentList, false,"",true);
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

			int j = 20;
			int l = 0;
			String line1 = "----------------------------------------------------------------------------------------------------------------------------------------------------------------";
			String line2 = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
			
			///////////////////////header////////////////////
			JLabel pipe_label1 = new JLabel("|");
			pipe_label1.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label1.setBounds(60, j, 120, 50);
			dataPanel.add(pipe_label1);
			
			JLabel std_label = new JLabel("Roll No.");
			std_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			std_label.setBounds(80, j, 80, 50);
			dataPanel.add(std_label);

			JLabel pipe_label2 = new JLabel("|");
			pipe_label2.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label2.setBounds(140, j, 120, 50);
			dataPanel.add(pipe_label2);
			
			JLabel gr_label = new JLabel("Gr No.");
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
			
			String[] attendanceDropDown = feeStatusList.split(",");
			// /////////////Default/Optional //////////////
			final JComboBox attendanceHeader_combo = new JComboBox(attendanceDropDown);
			if(!updateAllClass.equalsIgnoreCase("")){
				attendanceHeader_combo.setSelectedItem(updateAllClass);
			}
			attendanceHeader_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			attendanceHeader_combo.setBounds(650, j + 12, 130, 20);
			attendanceHeader_combo.setEnabled(isEditClass);
			dataPanel.add(attendanceHeader_combo);
			
			JLabel lineheader_labels = new JLabel(line1);
			lineheader_labels.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			lineheader_labels.setBounds(60, j + 10, 1100, 50);
			dataPanel.add(lineheader_labels);

			attendanceHeader_combo.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {
						String selAllAttendance = (String) attendanceHeader_combo.getSelectedItem();
						logger.info("selAllAttendance ::" + selAllAttendance);
						
						frame.setVisible(false);
						
						new FeeStatus(sessionData, grNoClass, lastNameClass, firstNameClass, fatherNameClass, rollFromClass, rollToClass, 
								std, div, academicYearClass, section, foundStudentList, false, selAllAttendance, true);
					} catch (Exception e1) {
						logger.info("attendanceHeader_combo Exception e1 = " + e1);
					}
				}
			});
			
			// /////////////Edit//////////////
			JButton editButton = new JButton("Edit");
			editButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			editButton.setBounds(900, j + 12, 130, 25);
			editButton.setEnabled(!isEditClass);
			dataPanel.add(editButton);

			editButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					List studentList = new ArrayList();
					frame.setVisible(false);
					new FeeStatus(sessionData, grNoClass, lastNameClass, firstNameClass, fatherNameClass, rollFromClass, rollToClass, 
							stdClass, divClass, academicYearClass, section, foundStudentList, isRecordNewClass, updateAllClass, true);
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
//			String feeStatusList[] = { "PRESENT", "ABSENT", "HOLIDAY" };
//			JLabel lineabove_l1abels = new JLabel(line1);
			
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

				if(tokenSize == 5){
					line1 = line2;
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
				attendance_combo[i].setEnabled(isEditClass);
				if(catTypeClass.equalsIgnoreCase("Mark Vacation")){
					attendance_combo[i].setSelectedItem("HOLIDAY");
					attendance_combo[i].setEnabled(false);
				} else if(!updateAllClass.equalsIgnoreCase("")){
					attendance_combo[i].setSelectedItem(updateAllClass);
					foundStudentList.remove(dataArray[i]);
					foundStudentList.add(columnArray[0] +"|"+ columnArray[1] +"|"+ columnArray[2] +"|"+ updateAllClass);
				}
				else if(!columnArray[3].equalsIgnoreCase("")){
					attendance_combo[i].setSelectedItem(columnArray[3]);
				}
				attendance_combo[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				attendance_combo[i].setBounds(650, j + 12, 130, 20);
				dataPanel.add(attendance_combo[i]);
				
				final int n = i;
				attendance_combo[i].addFocusListener(new FocusListener()  {
					
					String studentDetail = "";

					@Override
					public void focusGained(FocusEvent arg0) {

						studentDetail = temp1_labels[n].getText() + "|" + temp2_labels[n].getText() + "|"
								+ temp3_labels[n].getText() + "|" + attendance_combo[n].getSelectedItem();
						logger.info("attendance_combo gain :::" + studentDetail);
						logger.info("attendance_combo gain before :::" + foundStudentList.size());
						foundStudentList.remove(studentDetail);
						logger.info("attendance_combo gain after :::" + foundStudentList.size());
					}

					@Override
					public void focusLost(FocusEvent arg0) {

						studentDetail = temp1_labels[n].getText() + "|" + temp2_labels[n].getText() + "|"
								+ temp3_labels[n].getText() + "|" + attendance_combo[n].getSelectedItem();
						logger.info("attendance_combo lost :::" + studentDetail);
						logger.info("attendance_combo lost before :::" + foundStudentList.size());
						foundStudentList.add(studentDetail);
						logger.info("attendance_combo lost after :::" + foundStudentList.size());
					}
				});
				
				//column 4
				/*if(tokenSize >= 4){
					temp4_labels[i] = new JLabel(columnArray[3]);
					temp4_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					temp4_labels[i].setBounds(650, j + 12, 190, 20);
					temp4_labels[i].setToolTipText(columnArray[3]);
					dataPanel.add(temp4_labels[i]);
					
					pipe_labels5[i] = new JLabel("|");
					pipe_labels5[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels5[i].setBounds(850, j, 120, 50);
					dataPanel.add(pipe_labels5[i]);
				}*/
				
/*				//column 5
				if(tokenSize >= 5){
					temp5_labels[i] = new JLabel(columnArray[4]);
					temp5_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					temp5_labels[i].setBounds(870, j + 12, 130, 20);
					temp5_labels[i].setToolTipText(columnArray[4]);
					dataPanel.add(temp5_labels[i]);
	
					pipe_labels6[i] = new JLabel("|");
					pipe_labels6[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels6[i].setBounds(1000, j, 120, 50);
					dataPanel.add(pipe_labels6[i]);
				}*/
				
				line_labels[i] = new JLabel(line1);
				line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				line_labels[i].setBounds(60, j + 10, 1100, 50);
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

					try {
						if(dbValidate.connectDatabase(sessionData)){
							if(isRecordNewClass){
								insertAttendance = dbValidate.insertStudentFeeStatus(sessionData, foundStudentList, academicYearClass, 
										stdClass, divClass, false, section, updateAllClass);
							}
							else{
								insertAttendance = dbValidate.insertStudentFeeStatus(sessionData, foundStudentList, academicYearClass, 
										stdClass, divClass, true, section, updateAllClass);
							}
							if(insertAttendance){
								commonObj.showMessageDialog("Fee Status updated successfully.");
								List studentList = new ArrayList();
								frame.setVisible(false);
								new FeeStatus(sessionData, grNoClass, lastNameClass, firstNameClass, fatherNameClass, rollFromClass, rollToClass, 
										std, div, academicYearClass, section, studentList, isRecordNewClass, updateAllClass, true);
							}
							else{
								commonObj.showMessageDialog("Attendance updation failed.");
							}
						}
					} catch (Exception e1) {
						logger.info("Exception Fee Status Update ===>>>" + e1);
					} finally {
						dbValidate.closeDatabase(sessionData);
					}
				}
			});
			
			// /////////////Print in excel//////////////
			JButton printButton = new JButton("Open in Excel");
			printButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			printButton.setBounds(mainCentre + 150, k + 60, 160, 25);
			printButton.setEnabled(!isEditClass);
			dataPanel.add(printButton);

			printButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					boolean insertAttendance = true;

					try {
						CreateExcel ce = new CreateExcel();
						List<String> feeStatusPrintList = new ArrayList();
						feeStatusPrintList.add("Roll No*3|Gr No*3|Name*9|Fee Status");
						feeStatusPrintList.addAll(1, foundStudentList);
						ce.generateExcel(sessionData, "PRINTLIST", "FEE_STATUS", "", feeStatusPrintList, true, secName+" FEE_STATUS   STD:"+stdClass+"   DIV:"+divClass+ "   Academic Year:"+academicYearClass, 1);
						
					} catch (Exception e1) {
						logger.info("Exception Fee Status Update ===>>>" + e1);
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
