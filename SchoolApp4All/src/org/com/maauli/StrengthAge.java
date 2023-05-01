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
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
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

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
// import com.lowagie.text.DocumentException;
// import com.lowagie.text.pdf.TextField;
import org.com.accesser.SessionData;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class StrengthAge extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JFrame frame = null;

	static JComboBox studentcombo;

	static TreeMap<String,String> ageStrengthMapClass = new TreeMap();

	static int retCount = 0;

	static int scrollHeight = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

	static List<String> subjectList = new ArrayList();

	static boolean setSelected = false;

	static boolean printDuplicate = false;

	static String categoryList = "";

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

	static String standard = "";
	
	static String[] ageArray;
	
	static int ageSize = 0;
	
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
	
	static String excelData = "";
	
	static String ageExcel = "";
	
	static String tillDateClass = "";
	
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
    
	static List<String> excelDataForAge = new ArrayList();
	
	static CreateExcel ce = new CreateExcel();

	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static Logger logger = Logger.getLogger(StrengthAge.class.getName());

	public StrengthAge(SessionData sessionData1, String retStd, String retDiv, String academicYear, String sec,
			String retUserName, String retUserRole, TreeMap<String,String> retAgeStrengthMap, String retCatType, String retTillDate) {

		System.gc();
		ageStrengthMapClass = retAgeStrengthMap;
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		stdClass = retStd;
		divClass = retDiv;
		tillDateClass = retTillDate;
		logger.info("retStd :: " + retStd);
		logger.info("retDiv :: " + retDiv);
		logger.info("retCatType :: " + retCatType);
		logger.info("academicYear :: " + academicYear);

		category = retCatType;
		section = sec;
		logger.info("section :: " + section);
		std = bundle.getString(section.toUpperCase() + "_STD");
		logger.info("std :: " + std);
		String secName = bundle.getString(section.toUpperCase() + "_SEC");
		if(secName.contains("Section")){
			secName = secName.substring(0, secName.indexOf("Section"));
		}
		
		standard = std;
		int m = 0;
		String ageSortedList = ageStrengthMapClass.get("ageSorted");
		if(ageSortedList != null){
			StringTokenizer st = new StringTokenizer(ageSortedList, "|");
			ageSize = st.countTokens();
			ageArray = new String[ageSize+2];
			ageArray[m] = "Class";
			ageArray[m+1] = "Age";
			m=2;
			while (st.hasMoreTokens()) {
				ageArray[m] = st.nextToken();
				ageExcel = ageExcel + "|" + ageArray[m];
				m++;
			}
		}
		
		div = bundle.getString(section.toUpperCase() + "_DIV");
		logger.info("div :: " + div);
		secName = bundle.getString(section.toUpperCase() + "_SEC");
		logger.info("secName :: " + secName);
		categoryList = bundle.getString("CATEGORY_LIST");
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
		else{
			academicYearClass = commonObj.getAcademicYear(todayDate);
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
			std = retStd + ",All," + std;
		} else {
			std = "Select,All," + std;
		}
		if (!retDiv.equalsIgnoreCase("")) {
			div = retDiv + ",All," + div;
		} else {
			div = "Select,All," + div;
		}

		retCount = ageStrengthMapClass.size();
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

				LinkedHashMap<String,LinkedHashMap<String, String>> studentList = new LinkedHashMap<String,LinkedHashMap<String, String>>();
				new AdmissionFormNew(sessionData, section, user_name, user_role,"ADMISSION","",studentList);
				frame.setVisible(false);
			}
		});

		buttonX = buttonX + 160;
		JButton strengthButton = new JButton("Strength");
		strengthButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		strengthButton.setBounds(buttonX, 50, 150, 24); // 300, 50, 150, 24
		strengthButton.setBackground(Color.GREEN);
		topbandPanel.add(strengthButton);

		strengthButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

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
		JLabel academic_label = new JLabel("Year :");
		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academic_label.setBounds(90, 00, 70, 50);
		findPanel.add(academic_label);

		String academicYearList[] = yearList.split(",");
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setSelectedItem(academicYearClass);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academicYear_combo.setBounds(160, 12, 120, 25);
		findPanel.add(academicYear_combo);

		// /////////////Std//////////////
		JLabel admittedStd_label = new JLabel("Std :");
		admittedStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_label.setBounds(380, 00, 70, 50);
		findPanel.add(admittedStd_label);

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

		// String admittedDivList[] = {divClass,"A","B","C","D"};
		String[] divList = div.split(",");
		final JComboBox admittedDiv_combo = new JComboBox(divList);
		admittedDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedDiv_combo.setBounds(580, 12, 100, 25);
		findPanel.add(admittedDiv_combo);

		// ///////////////////////////////
		JLabel catType_label = new JLabel("Category Type :");
		catType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		catType_label.setBounds(710, 00, 150, 50);
		findPanel.add(catType_label);

		if (!category.equalsIgnoreCase("")) {
			categoryList = category + "," + categoryList;
		}
		String[] catList = categoryList.split(",");
		// String catList[] = {lcTypeClass,"Category Wise","Religion
		// Wise","General"};
		final JComboBox cat_combo = new JComboBox(catList);
		cat_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		cat_combo.setBounds(830, 12, 160, 25);
		findPanel.add(cat_combo);

		// //////////////////////////////////
		if (((String) cat_combo.getSelectedItem()).equalsIgnoreCase("General")) {
			// admittedDiv_combo.setSelectedIndex(1);
			admittedDiv_combo.setSelectedItem("All");
			admittedDiv_combo.setEnabled(false);
		} else {
			admittedDiv_combo.setEnabled(true);
		}
		
		final JRadioButton date_radio = new JRadioButton();
		date_radio.setBounds(30, 52, 20, 20);
		if(!tillDateClass.equalsIgnoreCase("")){
			date_radio.setSelected(true);
		}
		findPanel.add(date_radio);
		
		final JLabel date_label = new JLabel("Till Date :");
		date_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		date_label.setBounds(60, 40, 210, 50);
		findPanel.add(date_label);
		
		final JLabel lc_label = new JLabel("(Includes Student with LC)");
		lc_label.setFont(new Font("Book Antiqua", Font.BOLD, 10));
		lc_label.setBounds(60, 55, 210, 50);
		findPanel.add(lc_label);

		/****from date picker****/
		final UtilDateModel modelFrom = new UtilDateModel();
		if(!tillDateClass.equalsIgnoreCase("")){
			int yearFromDate = Integer.parseInt(tillDateClass.substring(0,tillDateClass.indexOf("/")));
			int monthFromDate = Integer.parseInt(tillDateClass.substring(tillDateClass.indexOf("/")+1,tillDateClass.lastIndexOf("/")));
			int dayFromDate = Integer.parseInt(tillDateClass.substring(tillDateClass.lastIndexOf("/")+1));
			modelFrom.setDate(yearFromDate,monthFromDate-1,dayFromDate);
//			modelFrom.setDate(2014,04,24);
		}
		modelFrom.setSelected(true);
	    Properties pFrom = new Properties();
	    pFrom.put("text.today", "Today");
	    pFrom.put("text.month", "Month");
	    pFrom.put("text.year", "Year");
	    final JDatePanelImpl datePanelFrom = new JDatePanelImpl(modelFrom, pFrom);
	    datePanelFrom.setBounds(120, 48, 130, 26);
	    // Don't know about the formatter, but there it is...
	    final JDatePickerImpl datePickerFrom = new JDatePickerImpl(datePanelFrom, new DateLabelFormatter());
	    datePickerFrom.setBounds(155, 48, 130, 26);
        findPanel.add(datePickerFrom);

        if(!stdClass.equalsIgnoreCase("All") && !stdClass.equalsIgnoreCase("Select") && 
	    		!stdClass.equalsIgnoreCase("") && dbValidate.connectDatabase(sessionData)){
			admittedDiv_combo.removeAllItems();
			admittedDiv_combo.addItem("All");
			String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdClass, section, 
					"PRESENT_DIV", "PRESENT_STD", "class_allotment",academicYearClass);
			
			for (String retval: divAvailabe.split(",")) {
				admittedDiv_combo.addItem(retval);
			}
			admittedDiv_combo.setSelectedItem(divClass);
		}
        
		cat_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (((String) cat_combo.getSelectedItem()).equalsIgnoreCase("General")) {
					// admittedDiv_combo.setSelectedIndex(1);
					admittedDiv_combo.setSelectedItem("All");
//					admittedDiv_combo.setEnabled(false);
				} else {
					admittedDiv_combo.setEnabled(true);
				}

			}
		});
		
		admittedStd_combo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
					String stdSel = (String) admittedStd_combo.getSelectedItem();
					String acadSel = (String) academicYear_combo.getSelectedItem();
					if(!stdSel.equalsIgnoreCase("All") && !stdSel.equalsIgnoreCase("Select") && dbValidate.connectDatabase(sessionData)){
						admittedDiv_combo.removeAllItems();
						admittedDiv_combo.addItem("All");
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
				String dateTillSelected = "";
				String tillDateAcademic = "";

				std = (String) admittedStd_combo.getSelectedItem();
				div = (String) admittedDiv_combo.getSelectedItem();
				catType = (String) cat_combo.getSelectedItem();
				academicSel = (String) academicYear_combo.getSelectedItem();
				
				if(date_radio.isSelected()){
					Date selectedFromDate = (Date) datePickerFrom.getModel().getValue();
					dateTillSelected = commonObj.dateToYYYYMMDD(selectedFromDate);
					tillDateAcademic = commonObj.formatyyyymmddtoddmmyyyy(dateTillSelected);
					tillDateAcademic = commonObj.getAcademicYear(tillDateAcademic);
		        }

				if (!tillDateAcademic.equalsIgnoreCase(academicSel) && date_radio.isSelected()) {
					validateFields = false;
					commonObj.showMessageDialog("Please Select till date from academic year "+academicSel);
				}
				if (std.equalsIgnoreCase("Select")) {
					validateFields = false;
					commonObj.showMessageDialog("Please Select Std");
				}
				else if (std.equalsIgnoreCase("All")) {
					std = "";
				}
				if (div.equalsIgnoreCase("Select") || div.equalsIgnoreCase("All")) {
					div = "";
				}
				if (academicSel.equalsIgnoreCase("Select")) {
					academicSel = "";
				}

				logger.info("std:" + std);
				logger.info("div:" + div);
				logger.info("catType:" + catType);

				// if (academicSel.equalsIgnoreCase("")
				// || academicSel.equalsIgnoreCase("Select")) {
				// validateFields = false;
				// JOptionPane.showMessageDialog(null, "Please select Year");
				// }
				logger.info("academicSel == " + academicSel);
				logger.info("validateFields == " + validateFields);
				List<String> dataList = null;
				TreeMap ageStrengthMap = new TreeMap();

				if (validateFields) {
					int listSize = 0;
					try {
						if(dbValidate.connectDatabase(sessionData)){
							if (catType.equalsIgnoreCase("Category Wise")) {
								dataList = dbValidate.findCategoryWise(sessionData, std, div, academicSel, section, catType, print, dateTillSelected);
								listSize = dataList.size();
							} else if (catType.equalsIgnoreCase("Religion Wise")) {
								dataList = dbValidate.findReligionWise(sessionData, std, div, academicSel, section, catType, print, dateTillSelected);
								listSize = dataList.size();
							} else if (catType.equalsIgnoreCase("General")) {
								dataList = dbValidate.findGeneralWise(sessionData, std, div, academicSel, section, catType, print, dateTillSelected);
								listSize = dataList.size();
							} else if (catType.equalsIgnoreCase("Age Wise")) {
								ageStrengthMap = dbValidate.findAgeWise(sessionData, std, div, academicSel, section, catType, print, dateTillSelected);
								listSize = ageStrengthMap.size();
							}
	
							logger.info("No of students found :: " + listSize);
	
							String passStd = (String) admittedStd_combo.getSelectedItem();
							String passDiv = (String) admittedDiv_combo.getSelectedItem();
							String passYear = (String) academicYear_combo.getSelectedItem();
	
							if (listSize > 1 && !catType.equalsIgnoreCase("Age Wise")) {
								frame.setVisible(false);
								new Strength(sessionData, passStd, passDiv, passYear, section, user_name, user_role, dataList, catType, dateTillSelected);
							} else if(listSize > 3 && catType.equalsIgnoreCase("Age Wise")){
								frame.setVisible(false);
								new StrengthAge(sessionData, passStd, passDiv, passYear, section, user_name, user_role, ageStrengthMap, catType, dateTillSelected);
							} else {
								JOptionPane.showMessageDialog(null, "No data found");
							}
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

				TreeMap<String,String> ageMap = new TreeMap();
				frame.setVisible(false);
				if(category.equalsIgnoreCase("Age Wise")){
					new StrengthAge(sessionData, "", "", "", section, user_name, user_role, ageMap, category, "");
				}
				else{
					List studList = new ArrayList();
					new Strength(sessionData, "", "", "", section, user_name, user_role, studList, "", "");
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
		int listSize = ageStrengthMapClass.size() - 1;
		logger.info("foundStudentList size in if ==>" + listSize);

		/*String[] dataArray = new String[listSize];
		dataArray = ageStrengthMapClass.toArray(dataArray);
		logger.info("listSize === " + listSize);
		logger.info("dataArray === " + dataArray.length);*/

		if (listSize > 0) {

			int j = 0;
			int l = 0;
			JLabel[] line_labels = new JLabel[listSize];
			JLabel[] pipe_labels1 = new JLabel[listSize];
			final JLabel[] temp1_labels = new JLabel[listSize];
			JLabel[] pipe_labels2 = new JLabel[listSize];
			JLabel[] temp2_labels = new JLabel[listSize];
			JLabel[] pipe_labels3 = new JLabel[listSize];
			JLabel[] temp3_labels = new JLabel[listSize];
			JLabel[] pipe_labels4 = new JLabel[listSize];
			JLabel[] temp4_labels = new JLabel[listSize];
			JLabel[] pipe_labels5 = new JLabel[listSize];
			JLabel[] temp5_labels = new JLabel[listSize];
			JLabel[] pipe_labels6 = new JLabel[listSize];
			JLabel[] temp6_labels = new JLabel[listSize];
			JLabel[] pipe_labels7 = new JLabel[listSize];
			JLabel[] temp7_labels = new JLabel[listSize];
			JLabel[] pipe_labels8 = new JLabel[listSize];
			JLabel[] temp8_labels = new JLabel[listSize];
			JLabel[] pipe_labels9 = new JLabel[listSize];
			JLabel[] temp9_labels = new JLabel[listSize];
			JLabel[] pipe_labels10 = new JLabel[listSize];
			JLabel[] temp10_labels = new JLabel[listSize];
			JLabel[] pipe_labels11 = new JLabel[listSize];
			JLabel[] temp11_labels = new JLabel[listSize];
			JLabel[] pipe_labels12 = new JLabel[listSize];
			JLabel[] temp12_labels = new JLabel[listSize];
			JLabel[] pipe_labels13 = new JLabel[listSize];
			JLabel[] temp13_labels = new JLabel[listSize];
			JLabel[] pipe_labels14 = new JLabel[listSize];
			JLabel[] temp14_labels = new JLabel[listSize];
			JLabel[] pipe_labels15= new JLabel[listSize];
			JLabel[] temp15_labels = new JLabel[listSize];
			JLabel[] pipe_labels16 = new JLabel[listSize];
			
			String line1 = " ------------";
			String line2 = "------------------------";
			
			int standardSize = 0;
			int n = 0;
			if(!stdClass.equalsIgnoreCase("All")){
				standard = stdClass;
			}
			String stdFromMap = ageStrengthMapClass.get("stdList");
			/*String standardNew = "";
			StringTokenizer st1 = new StringTokenizer(standard, ",");
			standardSize = st1.countTokens();
			while (st1.hasMoreTokens()) {
				standardNew = standardNew + "," + st1.nextToken();
			}*/
			
			StringTokenizer st2 = new StringTokenizer(stdFromMap, "|");
			standardSize = st2.countTokens();
			String[] standardArray = new String[standardSize];
			while (st2.hasMoreTokens()) {
				standardArray[n] = st2.nextToken();
				excelData = excelData + "|" + standardArray[n];
				line1 = line1 + line2;
				n++;
			}
			excelDataForAge.clear();
//			excelDataForAge.add(excelData);
//			excelDataForAge.add(ageExcel);
			
			JLabel lineabove_l1abels = new JLabel(line1);
			String displayTotal="";
			int k = 0;
			int p = 60;
			for (int i = 0; i < ageArray.length; i++) {
				excelData = "";
				p = 60;
				j = j + 30;
				k = j;
				l = j + 50;

				pipe_labels1[i] = new JLabel("|");
				pipe_labels1[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels1[i].setBounds(p, j, 120, 50);
				dataPanel.add(pipe_labels1[i]);

				displayTotal = ageArray[i];
				if(displayTotal.equalsIgnoreCase("1000")){
					displayTotal = "Total";
				}
				
				p = p + 20;
				excelData = excelData + "|" + displayTotal;
				temp1_labels[i] = new JLabel(displayTotal);
				temp1_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				temp1_labels[i].setBounds(p, j, 350, 50);
				dataPanel.add(temp1_labels[i]);

				p = p + 40;
				pipe_labels2[i] = new JLabel("|");
				pipe_labels2[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels2[i].setBounds(p, j, 120, 50);
				dataPanel.add(pipe_labels2[i]);

				for(int m = 0; m < standardSize; m++){
					p = p + 10;
					String male0 = ageStrengthMapClass.get(ageArray[i]+"|"+standardArray[m]+"|"+"MALE") == null ? "0" : ageStrengthMapClass.get(ageArray[i]+"|"+standardArray[m]+"|"+"MALE");
					excelData = excelData + "|" + male0;
					if(male0.length()>3 && !male0.equalsIgnoreCase("Boys")) {
						if(!male0.contains(" ")) {
							male0 = male0.substring(0,3);
						}
						else {
							male0 = commonObj.getFirstLetterFromString(male0);
						}
					}
					temp2_labels[i] = new JLabel(male0);
					temp2_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					temp2_labels[i].setBounds(p, j, 500, 50);
					dataPanel.add(temp2_labels[i]);

					p = p + 40;
					pipe_labels3[i] = new JLabel("|");
					pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels3[i].setBounds(p, j, 120, 50);
					dataPanel.add(pipe_labels3[i]);

					p = p + 20;
					String female0 = ageStrengthMapClass.get(ageArray[i]+"|"+standardArray[m]+"|"+"FEMALE") == null ? "0" : ageStrengthMapClass.get(ageArray[i]+"|"+standardArray[m]+"|"+"FEMALE");
					excelData = excelData + "|" + female0;
					if(female0.length()>3 && !female0.equalsIgnoreCase("Girls")) {
						if(!female0.contains(" ")) {
							female0 = female0.substring(0,3);
						}
						else {
							female0 = commonObj.getFirstLetterFromString(female0);
						}
					}
					temp3_labels[i] = new JLabel(female0);
					temp3_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					temp3_labels[i].setBounds(p, j + 15, 100, 20);
					dataPanel.add(temp3_labels[i]);

					p = p + 40;
					pipe_labels4[i] = new JLabel("|");
					pipe_labels4[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels4[i].setBounds(p, j, 120, 50);
					dataPanel.add(pipe_labels4[i]);
				}

				excelDataForAge.add(excelData);
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
					String std = "";
					String div = "";
					String academicSel = "";
					String catType = "";
					String print = "PRINT";
					List<String> printList = null;

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
						if(dbValidate.connectDatabase(sessionData)){
							if (catType.equalsIgnoreCase("Category Wise")) {
								printList = dbValidate.findCategoryWise(sessionData, std, div, academicSel, section, catType, print, tillDateClass);
							} else if (catType.equalsIgnoreCase("Religion Wise")) {
								printList = dbValidate.findReligionWise(sessionData, std, div, academicSel, section, catType, print, tillDateClass);
							} else if (catType.equalsIgnoreCase("General")) {
								printList = dbValidate.findGeneralWise(sessionData, std, div, academicSel, section, catType, print, tillDateClass);
							} else if (catType.equalsIgnoreCase("Age Wise")) {
								if(div.equalsIgnoreCase("")){
									div = "All";
								}
								if(std.equalsIgnoreCase("")){
									std = "All";
								}
								secName = bundle.getString(section.toUpperCase() + "_SEC");
								if(secName.contains("Section")){
									secName = secName.substring(0, secName.indexOf("Section"));
								}
								ce.generateExcel(sessionData, "STRENGTH", "Age Wise", "", excelDataForAge, true, secName+" Age Wise  STD:"+std+"   DIV:"+div+ "  " +academicYearClass, 1);
							}
	
							int listSize = printList.size();
							logger.info("No of students found :: " + listSize);
	
						}
					} catch (Exception e1) {
						logger.info("Exception Strength ===>>>" + e1);
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
