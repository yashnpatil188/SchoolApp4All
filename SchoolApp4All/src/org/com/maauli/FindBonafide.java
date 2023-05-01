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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

import com.lowagie.text.DocumentException;

public class FindBonafide extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JComboBox studentcombo;

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
	
    static String app_header = "";
    
    static String app_header_0 = "";

	static String img_mainband = "";

	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static SessionData sessionData = new SessionData();

	static Logger logger = Logger.getLogger(FindBonafide.class.getName());

	static JFrame frame = null;

	static int retCount = 0;

	static int scrollHeight = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

	static List<String> allGrList = new ArrayList();

	static List<String> selectedAllGrList = new ArrayList();

	static List<String> duplicateLCList = new ArrayList();

	static boolean setSelected = false;

	static String grClass = "";

	static String stdClass = "";

	static String divClass = "";

	static String firstClass = "";

	static String lastClass = "";

	static String fatherClass = "";

	static String yearClass = "";

	static String yearList = "";
	
	static String CertTypeClass = "Select";

	static String user_name = "";

	static String user_role = "";
	
	static boolean isSingleCopyClass = false;

	static BonafidePDF bonafidePDF = new BonafidePDF();
	
	static StudyCertificatePDF studyCertificatePDF = new StudyCertificatePDF();
	
	static NOCPDF NOCPDF = new NOCPDF();

	static Common commonObj = new Common();

	static DBValidate dbValidate = new DBValidate();
	
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
    static String currentAcademicYearClass = "";
    static String lc_visible_master_only = "";
    static boolean lc_visible_flag = true;
    
	private static List<String> foundStudentList;

	public FindBonafide(SessionData sessionData1, String retGr_no, String retStd, String retDiv, String retLastName,
			String retFirstName, String retFatherName, List<String> retStudentList, boolean retSelected,
			String academicYear, String sec, String retCertType, String retUserName, String retUserRole, boolean isSingleCopy) {

		logger.info("======FindLeavingCert========");
		System.gc();
		currentAcademicYearClass = "";
		isSingleCopyClass = isSingleCopy;
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		grClass = retGr_no;
		stdClass = retStd;
		divClass = retDiv;
		firstClass = retFirstName;
		lastClass = retLastName;
		fatherClass = retFatherName;
		yearClass = academicYear;
		entrytCnt = 0;

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
        currentAcademicYearClass = commonObj.getAcademicYear(todayDate);
        
        if(lc_visible_master_only.equalsIgnoreCase("true") && !sessionData.getConfigMap().get("SchoolApp_IP").contains("127.0.0.1")) {
    		lc_visible_flag = false;
    	}
    	else {
    		lc_visible_flag = true;
    	}
        
		if (!academicYear.equalsIgnoreCase("")) {
			yearClass = academicYear;
		}
		try {
			if(dbValidate.connectDatabase(sessionData)){
				yearList = dbValidate.findYearList(sessionData, "HS_GENERAL_REGISTER");
			}
		} catch (Exception e1) {
			logger.info("Exception yearList ===>>>" + e1);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
		if (!yearList.contains(yearClass)) {
			yearList = yearClass + "," + yearList;
		}
		if (!academicYear.equalsIgnoreCase("")) {
			yearList = academicYear + "," + yearList;
		}

		if (!retCertType.equalsIgnoreCase("")) {
			CertTypeClass = retCertType;
		}

		allGrList.clear();
		duplicateLCList.clear();
		setSelected = retSelected;
		foundStudentList = retStudentList;
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

//				frame.setVisible(false);
//				new Welcome(sessionData, user_name, user_role);
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

				// new Welcome(user_name,user_role);
				// frame.setVisible(false);
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
				new FindStudent(sessionData, "", "", "", "", "", "", studMap, section, user_name, user_role, false);
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

				LinkedHashMap<String,LinkedHashMap<String, String>> studentList = new LinkedHashMap<String,LinkedHashMap<String, String>>();
				new AdmissionFormNew(sessionData, section, user_name, user_role,"ADMISSION","", studentList);
				frame.setVisible(false);
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
					new FindLeavingCert(sessionData, "", "", "", "", "", "", findLCList, false, "", "", "", "", "",
							"", section, "", user_name, user_role,"","");
				}
			});
		}

		buttonX = buttonX + 160;
		JButton bonafideButton = new JButton("Certificate");
		bonafideButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		bonafideButton.setBounds(buttonX, 50, 150, 24);
		bonafideButton.setBackground(Color.GREEN);
		topbandPanel.add(bonafideButton);

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

		// /////////////Find Student form//////////////////////
		// /////////////GR No.//////////////
		JLabel gr_no_label = new JLabel("G.R. No.:");
		gr_no_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		gr_no_label.setBounds(60, 00, 70, 50);
		findPanel.add(gr_no_label);

		final JTextField gr_no_text = new JTextField(grClass);
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
					gr_no_text.setText(commonObj.appendZero(grString));
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

		String stdSel = "";
		if (!stdClass.equalsIgnoreCase("")) {
			stdSel = stdClass;
			std = stdSel + "," + std;
		} else {
			std = "Select," + std;
		}
		// String admittedStdList[] =
		// {stdSel,"IV","V","VI","VII","VIII","IX","X"};
		String[] stdList = std.split(",");
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

		String divSel = "";
		if (!divClass.equalsIgnoreCase("")) {
			divSel = divClass;
			div = divSel + "," + div;
		} else {
			div = "Select," + div;
		}
		// String admittedDivList[] = {divClass,"A","B","C","D"};
		String[] divList = div.split(",");
		final JComboBox admittedDiv_combo = new JComboBox(divList);
		admittedDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedDiv_combo.setBounds(580, 12, 100, 25);
		findPanel.add(admittedDiv_combo);

		// ///////////////////////////////
		JLabel lcType_label = new JLabel("Certificate :");
		lcType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lcType_label.setBounds(710, 00, 110, 50);
		findPanel.add(lcType_label);

		String lcList[] = { "Bonafide", "NOC", "Nirgam Utara (GR Extract)", "Study Certificate" };
		final JComboBox lc_combo = new JComboBox(lcList);
		lc_combo.setSelectedItem(CertTypeClass);
		lc_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lc_combo.setBounds(810, 12, 250, 25);
		findPanel.add(lc_combo);

		// /////////////Roll No.//////////////
		JLabel rollNo_label = new JLabel("Roll No :");
		rollNo_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		rollNo_label.setBounds(680, 00, 70, 50);
		// findPanel.add(rollNo_label);

		JLabel from_label = new JLabel("From -");
		from_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		from_label.setBounds(760, 00, 70, 50);
		// findPanel.add(from_label);

		final JTextField from_text = new JTextField();
		from_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		from_text.setBounds(820, 12, 40, 25);
		// findPanel.add(from_text);

		JLabel to_label = new JLabel("To -");
		to_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		to_label.setBounds(870, 00, 70, 50);
		// findPanel.add(to_label);

		final JTextField to_text = new JTextField();
		to_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		to_text.setBounds(910, 12, 40, 25);
		// findPanel.add(to_text);
		// //////////////////////////////////
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
		firstName_label.setBounds(380, 40, 120, 50);
		findPanel.add(firstName_label);

		final JTextField firstName_text = new JTextField(firstClass);
		firstName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_text.setBounds(480, 50, 200, 25);
		findPanel.add(firstName_text);
		// //////////////////////////////////
		// /////////////Father Name//////////////
		JLabel fatherName_label = new JLabel("Father Name :");
		fatherName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_label.setBounds(710, 40, 120, 50);
		findPanel.add(fatherName_label);

		final JTextField fatherName_text = new JTextField(fatherClass);
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
		findPanel.add(academic_radio);

		// /////////////ACADEMIV YEAR //////////////
		JLabel academic_label = new JLabel("Year :");
		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academic_label.setBounds(60, 80, 70, 50);
		findPanel.add(academic_label);
		
		///single copy radio
		final JRadioButton singleCopy_radio = new JRadioButton();
		if(isSingleCopyClass){
			singleCopy_radio.setSelected(true);
		}
		singleCopy_radio.setBounds(310, 100, 12, 12);
		singleCopy_radio.setBorder(null);
		findPanel.add(singleCopy_radio);

		// /////////////ACADEMIV YEAR //////////////
		final JLabel singleCopy_label = new JLabel("Single Copy");
		singleCopy_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		singleCopy_label.setBounds(330, 80, 120, 50);
		findPanel.add(singleCopy_label);

		String yearSel = "";
		if (!yearClass.equalsIgnoreCase(""))
		{	
			yearSel = yearClass;
		}
		String academicYearList[] = yearList.split(",");
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setSelectedItem(currentAcademicYearClass);
//		String academicYearList[] = { yearClass, "2010-11", "2011-12", "2012-13", "2013-14", "2014-15" };
//		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academicYear_combo.setBounds(160, 95, 120, 25);
		findPanel.add(academicYear_combo);

		if(!stdSel.equalsIgnoreCase("All") && !stdSel.equalsIgnoreCase("Select") && 
	    		!stdSel.equalsIgnoreCase("") && dbValidate.connectDatabase(sessionData)){
			admittedDiv_combo.removeAllItems();
			String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdSel, section, 
					"PRESENT_DIV", "PRESENT_STD", "class_allotment",yearClass);
			
			for (String retval: divAvailabe.split(",")) {
				admittedDiv_combo.addItem(retval);
			}
			admittedDiv_combo.setSelectedItem(divClass);
		}
		// //////radio action//////////////
		gr_no_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				std_radio.setSelected(false);
				name_radio.setSelected(false);
				gr_no_text.setEnabled(true);
				admittedStd_combo.setSelectedItem("");
				admittedStd_combo.setEnabled(false);
				admittedDiv_combo.setSelectedItem("");
				admittedDiv_combo.setEnabled(false);
				from_text.setText("");
				from_text.setEnabled(false);
				to_text.setText("");
				to_text.setEnabled(false);
				lastName_text.setText("");
				lastName_text.setEnabled(false);
				firstName_text.setText("");
				firstName_text.setEnabled(false);
				fatherName_text.setText("");
				fatherName_text.setEnabled(false);
			}
		});

		std_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				gr_no_radio.setSelected(false);
				name_radio.setSelected(false);
				gr_no_text.setText("");
				gr_no_text.setEnabled(false);
				admittedStd_combo.setEnabled(true);
				admittedDiv_combo.setEnabled(true);
				from_text.setText("");
				from_text.setEnabled(false);
				to_text.setText("");
				to_text.setEnabled(false);
				lastName_text.setText("");
				lastName_text.setEnabled(false);
				firstName_text.setText("");
				firstName_text.setEnabled(false);
				fatherName_text.setText("");
				fatherName_text.setEnabled(false);
			}
		});

		// //////////////////////////////////
		admittedStd_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if ((String) admittedStd_combo.getSelectedItem() != "") {
					admittedDiv_combo.setEnabled(true);
				} else {
					admittedDiv_combo.setSelectedItem("");
					admittedDiv_combo.setEnabled(false);
				}
				
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

		// //////////////////////////////////
		admittedDiv_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if ((String) admittedDiv_combo.getSelectedItem() != "") {
					from_text.setEnabled(true);
					to_text.setEnabled(true);
				} else {
					from_text.setText("");
					from_text.setEnabled(false);
					to_text.setText("");
					to_text.setEnabled(false);
				}

			}
		});

		// //////////////////////////////////
		lc_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String certificate = (String) lc_combo.getSelectedItem();
				if (certificate.equalsIgnoreCase("Bonafide")) {
					findPanel.add(singleCopy_radio);
					findPanel.add(singleCopy_label);
				} else {
					findPanel.remove(singleCopy_radio);
					findPanel.remove(singleCopy_label);
				}
				findPanel.revalidate();
				findPanel.repaint();
			}
		});
				
		name_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				std_radio.setSelected(false);
				gr_no_radio.setSelected(false);
				gr_no_text.setText("");
				gr_no_text.setEnabled(false);
				admittedStd_combo.setSelectedItem("");
				admittedStd_combo.setEnabled(false);
				admittedDiv_combo.setSelectedItem("");
				admittedDiv_combo.setEnabled(false);
				from_text.setText("");
				from_text.setEnabled(false);
				to_text.setText("");
				to_text.setEnabled(false);
				lastName_text.setEnabled(true);
				firstName_text.setEnabled(true);
				fatherName_text.setEnabled(true);
			}
		});

		// //////////////////////////////////
		academic_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (academic_radio.isSelected()) {
					academicYear_combo.setEnabled(true);
				} else {
					academicYear_combo.setEnabled(false);
				}

			}
		});

		std_radio.setSelected(false);
		name_radio.setSelected(false);
		gr_no_radio.setSelected(true);
		gr_no_text.setEnabled(true);
		admittedStd_combo.setEnabled(false);
		admittedDiv_combo.setEnabled(false);
		from_text.setEnabled(false);
		to_text.setEnabled(false);
		lastName_text.setEnabled(false);
		firstName_text.setEnabled(false);
		fatherName_text.setEnabled(false);
		academicYear_combo.setEnabled(false);
		// ///////////////////////
		// /////////////Submit//////////////
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(mainCentre - 50, 90, 130, 25);
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
				String lcType = "";
				boolean gr_radioSelected = false;
				boolean std_radioSelected = false;
				boolean name_radioSelected = false;
				boolean year_radioSelected = false;
				boolean isSingleCopy = singleCopy_radio.isSelected();

				gr_radioSelected = gr_no_radio.isSelected();
				std_radioSelected = std_radio.isSelected();
				name_radioSelected = name_radio.isSelected();
				year_radioSelected = academic_radio.isSelected();

				gr_no = gr_no_text.getText();
				lastName = lastName_text.getText();
				firstName = firstName_text.getText();
				fatherName = fatherName_text.getText();
				std = (String) admittedStd_combo.getSelectedItem();
				div = (String) admittedDiv_combo.getSelectedItem();
				lcType = (String) lc_combo.getSelectedItem();
				academicSel = (String) academicYear_combo.getSelectedItem();

				if (std.equalsIgnoreCase("Select")) {
					std = "";
				}
				if (div.equalsIgnoreCase("Select")) {
					div = "";
				}
				if (academicSel.equalsIgnoreCase("Select")) {
					academicSel = "";
				}

				logger.info("gr No.:" + gr_no);
				logger.info("lastName.:" + lastName);
				logger.info("firstName:" + firstName);
				logger.info("fatherName:" + fatherName);
				logger.info("std:" + std);
				logger.info("div:" + div);
				logger.info("lcType:" + lcType);

				// boolean checkGrNoFlag = false;

				if (gr_radioSelected) {
					lastName = "";
					firstName = "";
					fatherName = "";
					std = "";
					div = "";

					if (gr_no.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter GR No.");
					} else if (commonObj.checkComma(gr_no)) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "GR No. cannot contain |-:';,");
					}
				} else if (name_radioSelected) {
					gr_no = "";
					std = "";
					div = "";

					if (lastName.length() == 0 && firstName.length() == 0 && fatherName.length() == 0) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter atleast one name field");
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
				} else if (std_radioSelected) {
					gr_no = "";
					lastName = "";
					firstName = "";
					fatherName = "";

					if (std.equalsIgnoreCase("") || std.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Std");
					} else if ((std.equalsIgnoreCase("") || std.equalsIgnoreCase("Select")) && !div.equalsIgnoreCase("")
							&& !div.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Std");
					}
				} else if (!year_radioSelected) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select any one option");
				}
				if (year_radioSelected) {
					if (academicSel.equalsIgnoreCase("") || academicSel.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Year");
					}
				}
				if (validateFields && (lcType.equalsIgnoreCase("Select") || lcType.equalsIgnoreCase(""))) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select certificate");
				}
				logger.info("academicSel == " + academicSel);
				logger.info("validateFields == " + validateFields);
				List<String> studentList;
				if (validateFields) {
					try {
						if (dbValidate.connectDatabase(sessionData)) {
							studentList = dbValidate.findStudentLC(sessionData, gr_no, std, div, lastName, firstName, fatherName,
									academicSel, section, lcType, "");
							int listSize = studentList.size();
							logger.info("No of students found :: " + listSize);

							if (listSize > 0) {
								frame.setVisible(false);
								new FindBonafide(sessionData, gr_no, std, div, lastName, firstName, fatherName,
										studentList, false, academicSel, section, lcType, user_name, user_role,isSingleCopy);
								
							} else {
								JOptionPane.showMessageDialog(null, "No data found");
							}
						}
					} catch (Exception e1) {
						frame.setVisible(false);
						new Student(sessionData, section, user_name, user_role);
						logger.info("Exception FindStudent form ===>>>" + e1);
					} finally {
						dbValidate.closeDatabase(sessionData);
					}
				}
			}

		});
		// /////////////Reset//////////////
		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		resetButton.setBounds(mainCentre + 125, 90, 130, 25);
		findPanel.add(resetButton);

		resetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				logger.info("Cliked Reset in findLeaving cert");
				List findLCList = new ArrayList();
				frame.setVisible(false);
				new FindBonafide(sessionData, "", "", "", "", "", "", findLCList, false, "",
						section, CertTypeClass, user_name, user_role, isSingleCopyClass);
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
			logger.info("foundStudentList==>" + listSize);

			final JRadioButton all_radio = new JRadioButton();
			all_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			all_radio.setBounds(40, 13, 20, 20);
			all_radio.setSelected(setSelected);
			dataPanel.add(all_radio);

			JLabel sr_label = new JLabel("Roll. No.");
			sr_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sr_label.setBounds(67, 00, 120, 50);
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
			name_label.setBounds(250, 00, 120, 50);
			dataPanel.add(name_label);

			JLabel pipe_label3 = new JLabel("|");
			pipe_label3.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label3.setBounds(750, 00, 120, 50);
//			dataPanel.add(pipe_label3);

			JLabel lc_label = new JLabel("L.C. No.");
			lc_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			lc_label.setBounds(770, 00, 120, 50);
//			dataPanel.add(lc_label);

			JLabel pipe_label4 = new JLabel("|");
			pipe_label4.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label4.setBounds(870, 00, 120, 50);
//			dataPanel.add(pipe_label4);

			JLabel dupLc_label = new JLabel("Dupl. L.C.");
			dupLc_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			dupLc_label.setBounds(890, 00, 120, 50);
//			dataPanel.add(dupLc_label);

			JLabel line_label = new JLabel(
					"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			line_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			line_label.setBounds(30, 13, 1100, 50);
			dataPanel.add(line_label);

			String[] studentArray = new String[listSize];
			studentArray = foundStudentList.toArray(studentArray);
			logger.info("listSize === " + listSize);
			logger.info("studentArray === " + studentArray.length);

			if (listSize > 0) {
				int j = 0;
				int l = 0;
				final JRadioButton[] sr_radio = new JRadioButton[listSize];
				JLabel[] sr_labels = new JLabel[listSize];
				JLabel[] pipe_labels1 = new JLabel[listSize];
				final JLabel[] gr_labels = new JLabel[listSize];
				JLabel[] pipe_labels2 = new JLabel[listSize];
				JLabel[] name_labels = new JLabel[listSize];
				JLabel[] line_labels = new JLabel[listSize];
				JLabel[] pipe_labels3 = new JLabel[listSize];
				JLabel[] pipe_labels4 = new JLabel[listSize];
				final JLabel[] lc_labels = new JLabel[listSize];
				final JLabel[] dupLc_labels = new JLabel[listSize];
				JButton[] view_button = new JButton[listSize];

				for (int i = 0; i < listSize; i++) {
					j = j + 30;
					l = j + 50;
					logger.info(j + "====" + studentArray[i]);
					final String gr = studentArray[i].substring(0, studentArray[i].indexOf("|"));
					String name = studentArray[i].substring(studentArray[i].indexOf("|") + 1,
							studentArray[i].indexOf("||"));
					String lc = studentArray[i].substring(studentArray[i].indexOf("||||") + 4,
							studentArray[i].indexOf("|||||"));
					String rollNo = studentArray[i].substring(studentArray[i].indexOf("||||||||")+8);
				
					lc_labels[i] = new JLabel(lc);
					dataPanel.add(lc_labels[i]);
					
					sr_radio[i] = new JRadioButton();
					sr_radio[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sr_radio[i].setBounds(40, j + 13, 20, 20);
					sr_radio[i].setSelected(setSelected);
					dataPanel.add(sr_radio[i]);

					sr_labels[i] = new JLabel(rollNo);
					sr_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sr_labels[i].setBounds(90, j, 120, 50);

					pipe_labels1[i] = new JLabel("|");
					pipe_labels1[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels1[i].setBounds(130, j, 120, 50);
					dataPanel.add(pipe_labels1[i]);

					gr_labels[i] = new JLabel(gr);
					gr_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					gr_labels[i].setBounds(160, j, 120, 50);
					
//					logger.info(gr + "::allGrList in for loop 1==> " + allGrList.size());
//					if (dupLcNo.equalsIgnoreCase(" ") || dupLcNo.equalsIgnoreCase("") || dupLcNo.equalsIgnoreCase("NA")
//							|| dupLcNo.equalsIgnoreCase(null) || dupLcNo.equalsIgnoreCase("0000")) {
						allGrList.add(gr);
//					}
					// allGrList.add(gr);
//					logger.info(gr + "::allGrList in for loop 2==> " + allGrList.size());

					pipe_labels2[i] = new JLabel("|");
					pipe_labels2[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels2[i].setBounds(230, j, 120, 50);
					dataPanel.add(pipe_labels2[i]);

					name_labels[i] = new JLabel(name);
					name_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					name_labels[i].setBounds(250, j, 500, 50);
					if(!lc.trim().equalsIgnoreCase("")){
//						sr_labels[i].setForeground(Color.RED);
//						gr_labels[i].setForeground(Color.RED);
						name_labels[i].setForeground(Color.RED);
					}
					
					dataPanel.add(sr_labels[i]);
					dataPanel.add(gr_labels[i]);
					dataPanel.add(name_labels[i]);

					pipe_labels3[i] = new JLabel("|");
					pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels3[i].setBounds(750, j, 120, 50);
					dataPanel.add(pipe_labels3[i]);

					view_button[i] = new JButton("View "+gr);
					view_button[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					view_button[i].setBounds(770, j + 12, 150, 20);
//					if (!lcNo.equalsIgnoreCase("") && !lcNo.equalsIgnoreCase("NA") && !lcNo.equalsIgnoreCase(" ")
//							&& !lcNo.equalsIgnoreCase("0000")) {
						dataPanel.add(view_button[i]);
//					}

					// //////View Button action//////////////
					final int m = i;
					view_button[i].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {

							try {
								List<String> viewBonafide = new ArrayList();
								String lc = lc_labels[m].getText().trim();
								viewBonafide.add(gr_labels[m].getText());
								if(CertTypeClass.equalsIgnoreCase("Bonafide")){
									bonafidePDF.bonafideCertificate(sessionData, "View", viewBonafide, section, CertTypeClass, user_name, user_role, isSingleCopyClass);
								}
								else if(CertTypeClass.equalsIgnoreCase("NOC")){
									NOCPDF.NOCCertificate(sessionData, "View", viewBonafide, section, CertTypeClass, user_name, user_role);
								} else if(CertTypeClass.equalsIgnoreCase("Nirgam Utara (GR Extract)")){
									if(!lc.equalsIgnoreCase("")){
										AdmissionRegisterPDF.getAdmissionRegisterPDF(sessionData, viewBonafide);
									}else{
										JOptionPane.showMessageDialog(null, "Nirgam Utara (GR Extract) not generated for GR No. "+gr_labels[m].getText()+" \n as LC is not created.");
									}
								} else if(CertTypeClass.equalsIgnoreCase("Study Certificate")){
									studyCertificatePDF.studyCertificate(sessionData, "View", viewBonafide, section, CertTypeClass, user_name, user_role, isSingleCopyClass);
								}
								
								
							} catch (Exception e1) {
								logger.info("Exception e == " + e1);
								e1.printStackTrace();
							}
						}
					});

					/*if (!lcNo.equalsIgnoreCase("") && !lcNo.equalsIgnoreCase("NA") && !lcNo.equalsIgnoreCase(" ")
							&& !lcNo.equalsIgnoreCase("0000") && setSelected) {
						duplicateLCList.add(gr);
					}*/

					line_labels[i] = new JLabel(
							"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					line_labels[i].setBounds(30, j + 10, 1100, 50);
					dataPanel.add(line_labels[i]);

					// //////sr radio action//////////////
					final int k = i;
					// allGrList.clear();
					sr_radio[i].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {

							boolean radio_selected = sr_radio[k].isSelected();
							logger.info("k value " + k + ":  " + radio_selected + "::radio_selected for : "
									+ gr_labels[k].getText());

							if (!setSelected && entrytCnt == 0) {
								allGrList.clear();
							}

							if (radio_selected) {
								/*if (!lc_labels[k].getText().trim().equalsIgnoreCase("")) {
									duplicateLCList.add(gr_labels[k].getText());
									logger.info(gr_labels[k].getText() + " added");
								}*/
								logger.info(" allgr list before : " + allGrList.size());
								allGrList.add(gr_labels[k].getText());
								logger.info(" allgr list after : " + allGrList.size());
								setSelected = true;
								selectAllCount++;
							} else {
								entrytCnt++;
								logger.info(" all grp list before remove 1 :" + allGrList.size());
								allGrList.remove(gr_labels[k].getText());
								logger.info(" all grp list before remove after 2 :" + allGrList.size());
								/*if (!lc_labels[k].getText().trim().equalsIgnoreCase("")) {
									duplicateLCList.remove(gr_labels[k].getText());
									logger.info(gr_labels[k].getText() + " removed");
								}*/
								all_radio.setSelected(false);
								setSelected = false;
								selectAllCount--;
							}
							logger.info("selectAllCount ==>" + selectAllCount);
							if (selectAllCount == foundStudentList.size()) {
								all_radio.setSelected(true);
							}
							logger.info("allGrList end of for loop ==>" + allGrList.size());
						}
					});
				}
				// /////print data starts/////////////////////////////////
				JButton validateButton = new JButton("View Selected");
				validateButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				validateButton.setBounds(mainCentre - 150, l + 30, 160, 25);
				dataPanel.add(validateButton);

				JButton savePrintButton = new JButton("Save & Print");
				savePrintButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				savePrintButton.setBounds(mainCentre + 50, l + 30, 130, 25);
//				dataPanel.add(savePrintButton);
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
				sep1_label.setBounds(40, l + 190, 1600, 50);
				dataPanel.add(sep1_label);
				// /////////////////////////////

				// //////All sr radio action//////////////
				all_radio.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						boolean pass_selected = all_radio.isSelected();
						logger.info("pass_selected : " + pass_selected);
						frame.setVisible(false);
						logger.info(" list size  : " + foundStudentList.size());
						new FindBonafide(sessionData, grClass, stdClass, divClass, lastClass, firstClass,
								fatherClass, foundStudentList, pass_selected, "", section, CertTypeClass, user_name, user_role, isSingleCopyClass);
					}
				});
				
				// /////Validate button action starts///////////////////
				validateButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						logger.info("validate button action starts");
						boolean flagLeaving = true;

						List<String> passGrList = new ArrayList();
						logger.info("setSelected = " + setSelected);
						logger.info("passGrList 123 = " + passGrList.size());
						logger.info("allGrList 123 = " + allGrList.size());

						if (!setSelected && entrytCnt == 0) {
							allGrList.clear();
							passGrList = allGrList;
							logger.info("passGrList before 1 = " + passGrList.size());
						}
						passGrList = allGrList;
						logger.info("passGrList before 3 = " + passGrList.size());
						if (passGrList.size() == 0) {
							JOptionPane.showMessageDialog(null, "No Student selected");
						} else if (flagLeaving) {
							logger.info("duplicateLCList===>" + duplicateLCList.size());
							if (duplicateLCList.size() > 0) {
								int reply = JOptionPane.showConfirmDialog(null,
										"Would You Like to validate Certificate?", "Confirm validate",
										JOptionPane.YES_NO_OPTION);
								if (reply == JOptionPane.YES_OPTION) {
									try {
										if(CertTypeClass.equalsIgnoreCase("Bonafide")){
											bonafidePDF.bonafideCertificate(sessionData, "Validate", passGrList, section, CertTypeClass, user_name, user_role, isSingleCopyClass);
										}
										else if(CertTypeClass.equalsIgnoreCase("NOC")){
											NOCPDF.NOCCertificate(sessionData, "Validate", passGrList, section, CertTypeClass, user_name, user_role);
										}
										else if(CertTypeClass.equalsIgnoreCase("Nirgam Utara (GR Extract)")){
											AdmissionRegisterPDF.getAdmissionRegisterPDF(sessionData, passGrList);
										} else if(CertTypeClass.equalsIgnoreCase("Study Certificate")){
											studyCertificatePDF.studyCertificate(sessionData, "Validate", passGrList, section, CertTypeClass, user_name, user_role, isSingleCopyClass);
										}
									} catch (Exception e1) {
										logger.info("Exception e == " + e1);
										e1.printStackTrace();
									}
								} else if (reply == JOptionPane.NO_OPTION) {
									JOptionPane.showMessageDialog(null, "Please recheck again");
								}
							} else {
								try {
									if(CertTypeClass.equalsIgnoreCase("Bonafide")){
										bonafidePDF.bonafideCertificate(sessionData, "Validate", passGrList, section, CertTypeClass, user_name, user_role, isSingleCopyClass);
									}
									else if(CertTypeClass.equalsIgnoreCase("NOC")){
										NOCPDF.NOCCertificate(sessionData, "Validate", passGrList, section, CertTypeClass, user_name, user_role);
									}
									else if(CertTypeClass.equalsIgnoreCase("Nirgam Utara (GR Extract)")){
										AdmissionRegisterPDF.getAdmissionRegisterPDF(sessionData, passGrList);
									} else if(CertTypeClass.equalsIgnoreCase("Study Certificate")){
										studyCertificatePDF.studyCertificate(sessionData, "Validate", passGrList, section, CertTypeClass, user_name, user_role, isSingleCopyClass);
									}
								} catch (Exception e1) {
									logger.info("Exception e == " + e1);
									e1.printStackTrace();
								}
							}
						}
					}
				});
				// /////validate button action ends//////////////////////
				// //////save & print action
				// starts//////////////////////////////////////
				savePrintButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						logger.info("save & print action starts");
						boolean flagLeaving = true;

						List<String> passGrList = new ArrayList();
						logger.info("setSelected = " + setSelected);
						logger.info("passGrList 123 = " + passGrList.size());
						logger.info("allGrList 123 = " + allGrList.size());

						if (!setSelected && entrytCnt == 0) {
							allGrList.clear();
							passGrList = allGrList;
							logger.info("passGrList before 1 = " + passGrList.size());
						}

						passGrList = allGrList;
						logger.info("passGrList before 3 = " + passGrList.size());
						if (passGrList.size() == 0) {
							JOptionPane.showMessageDialog(null, "No Student selected");
						} else if (flagLeaving) {
							logger.info("duplicateLCList===>" + duplicateLCList.size());
							if (duplicateLCList.size() > 0) {
								int reply = JOptionPane.showConfirmDialog(null,
										"Would You Like to Save/Print duplicate LC?", "Confirm Save/Print",
										JOptionPane.YES_NO_OPTION);
								if (reply == JOptionPane.YES_OPTION) {
									try {
										bonafidePDF.bonafideCertificate(sessionData, "save", passGrList, section, CertTypeClass, user_name, user_role, isSingleCopyClass);
										frame.setVisible(false);
										List findLCList = new ArrayList();
										new FindBonafide(sessionData, grClass, stdClass, divClass, lastClass,
												firstClass, fatherClass, findLCList, false, 
												yearClass, section, CertTypeClass, user_name, user_role, isSingleCopyClass);
										
									} catch (Exception e1) {
										commonObj.logException(e1);
									}
								} else if (reply == JOptionPane.NO_OPTION) {
									JOptionPane.showMessageDialog(null, "Please recheck again");
								}
							} else {
								try {
									bonafidePDF.bonafideCertificate(sessionData, "save", passGrList, section, CertTypeClass, user_name, user_role, isSingleCopyClass);
									JOptionPane.showMessageDialog(null, "Bonafide created successfully...");
									frame.setVisible(false);
									List findLCList = new ArrayList();
									new FindBonafide(sessionData, grClass, stdClass, divClass, lastClass,
											firstClass, fatherClass, findLCList, false, 
											yearClass, section, CertTypeClass, user_name, user_role, isSingleCopyClass);
								} catch (Exception e1) {
									commonObj.logException(e1);
								}
							}
						}
					}
				});
				// //////save & print action
				// ends/////////////////////////////////////
			}
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
