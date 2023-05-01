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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
// import com.lowagie.text.DocumentException;
// import com.lowagie.text.pdf.TextField;
import org.com.accesser.SessionData;

public class StudentFeeAllot extends JFrame {
	
	private static StudentFeeAllot studentFeeAllot = new StudentFeeAllot();

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JFrame frame = null;

	static JComboBox studentcombo;

	static int retCount = 0;

	static int scrollHeight = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

	static List<String> findSubList = new ArrayList<String>();

	static boolean setSelected = false;

	static String stdClass = "";

	static String academicYearClass = "";

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
	static Logger logger = Logger.getLogger(StudentFeeAllot.class.getName());
	static LinkedHashMap<String,LinkedHashMap<String,String>> studentMap = new LinkedHashMap<String,LinkedHashMap<String,String>>();
	static boolean multipleFlag  = false;
	
	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private StudentFeeAllot() {
	}

	/* Static 'instance' method */
	public static StudentFeeAllot getInstance() {
		return studentFeeAllot;
	}
	
	/* Other methods protected by singleton-ness */
	protected static void getStudentFeeAllot(SessionData sessionData1, String retStd, String academicYear,
			final String retCategory, String retDiv, final String retFeeHead, String selectAllFee, final String retGender) {

		logger.info("=============StudentFeeAllot=================");
		
		ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
		LinkedHashMap<String,LinkedHashMap<String,String>> feeHeadMap = new LinkedHashMap<String,LinkedHashMap<String,String>>();
		LinkedHashMap<String,LinkedHashMap<String,String>> multiFeeHeadMap = new LinkedHashMap<String,LinkedHashMap<String,String>>();
		
		System.gc();
		sessionData = sessionData1;
		selectStd = "Select";
		selectDiv = "Select";
		multipleFlag = false;
		std = bundle.getString(sessionData1.getSectionName()+ "_STD");
		div = bundle.getString(sessionData1.getSectionName() + "_DIV");
		secName = bundle.getString(sessionData1.getSectionName() + "_SEC");
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

		stdClass = retStd;
		if (!retStd.equalsIgnoreCase("")) {
			selectStd = retStd;
		}
		divClass = retDiv;
		if (!retDiv.equalsIgnoreCase("")) {
			selectDiv = retDiv;
		}
		selectAcademic = academicYear;
		if (!academicYear.equalsIgnoreCase("") && !academicYear.equalsIgnoreCase("Year")) {
			selectAcademic = academicYear;
		}

		try {
			if(dbValidate.connectDatabase(sessionData)){
				feeHeadMap.clear();
				feeHeadMap.put("Select",null);
				feeHeadMap.putAll(dbValidate.getFeesHeadData(sessionData1, academicYearClass, stdClass, sessionData1.getSectionName(), ""));
//				feeHeadMap = dbValidate.getFeesHeadData(sessionData1, academicYearClass, stdClass, sessionData1.getSectionName(), "");
				multiFeeHeadMap = dbValidate.fetchMultipleHeadMap(sessionData, academicYearClass, stdClass, "");
			}
			if(!retStd.equalsIgnoreCase("")) {
				studentMap = dbValidate.insertNewFeeAllotStudents(sessionData1, retStd, retDiv, academicYear, retCategory, retGender);
			}
			else {
				studentMap.clear();
			}
			
			///code to remove fees head with optional no and not in multiple head
			String optional = "", feeFromMultiMap = "";
			boolean isKeep = false;
			if(!feeHeadMap.isEmpty()) {
				LinkedHashMap<String,LinkedHashMap<String,String>> feeHeadMapTmp = new LinkedHashMap<String,LinkedHashMap<String,String>>();
				feeHeadMapTmp.putAll(feeHeadMap);
				Set setmfh = multiFeeHeadMap.entrySet();
				Set setfh = feeHeadMapTmp.entrySet();
				Iterator ifh = setfh.iterator();
				while(ifh.hasNext()) {
					Map.Entry me = (Map.Entry)ifh.next();
					if(feeHeadMapTmp.get(me.getKey()) != null){
					optional = ((LinkedHashMap<?, ?>) feeHeadMapTmp.get(me.getKey())).get("optional").toString();
						if(optional.equalsIgnoreCase("No")) {
							Iterator imfh = setmfh.iterator();
							while(imfh.hasNext()) {
								Map.Entry memfh = (Map.Entry)imfh.next();
								feeFromMultiMap = ((LinkedHashMap<?, ?>) multiFeeHeadMap.get(memfh.getKey())).get("fees_name").toString();
								feeFromMultiMap = commonObj.replaceCommaApostrophy(feeFromMultiMap);
								if(feeFromMultiMap.equalsIgnoreCase(me.getKey().toString())) {
									isKeep = true;
									break;
								}
							}
							if(!isKeep) {
								feeHeadMap.remove(me.getKey());
							}
						}
					}
					isKeep = false;
				}
			}
		} catch (Exception e) {
			commonObj.logException(e);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}

		entrytCnt = 0;
		scrollHeight = (studentMap.size() - 6) * 30; // to adjust the perfect
		if (scrollHeight < 0)
			scrollHeight = 0;

		frame = new JFrame("Welcome to "+sessionData1.getAppName());
		screenWidth = commonObj.screeWidth();
		screenHeight = commonObj.screeHeight();
		mainCentre = (screenWidth - 150) / 2;

		frame.setSize(screenWidth, screenHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		frame.add(panel);

		panel.setLayout(new BorderLayout());
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
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, sessionData.getSectionName());
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

        panel.add(titlePanel, BorderLayout.NORTH);

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
				new Student(sessionData, sessionData.getSectionName(), user_name, user_role);
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
				new Academic(sessionData, sessionData.getSectionName(), user_name, user_role);
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
				new FindStudent(sessionData, "", "", "", "", "", "", studMap, sessionData.getSectionName(), user_name, user_role, false);
				frame.setVisible(false);
			}
		});

		panel.add(leftPanel, BorderLayout.WEST);

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

		JLabel subMenuTitle = new JLabel("Fee");
		subMenuTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		subMenuTitle.setForeground(Color.orange);
		subMenuTitle.setBounds(20, 45, 100, 30);
		topbandPanel.add(subMenuTitle);
        
		int width = 80;
		JButton schoolHeadButton = new JButton("School Head");
		schoolHeadButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		schoolHeadButton.setBounds(width, 50, 140, 24);
		topbandPanel.add(schoolHeadButton);

		schoolHeadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				CreateSchoolHead.getCreateSchoolHead(sessionData, academicYearClass);
			}
		});

		width = width + 150;
		JButton createFeeButton = new JButton("Fee Head");
		createFeeButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		createFeeButton.setBounds(width, 50, 140, 24);
		topbandPanel.add(createFeeButton);

		createFeeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LinkedHashMap findStudentMap = new LinkedHashMap();
				new CreateFeesHead(sessionData, "", "", sessionData.getSectionName());
			}
		});
		
		width = width + 150;
		JButton MultipleFeeButton = new JButton("Multiple Fee Head");
		MultipleFeeButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		MultipleFeeButton.setBounds(width, 50, 190, 24);
		topbandPanel.add(MultipleFeeButton);

		MultipleFeeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
            	MultipleFeesHead.getMultipleFeesHead(sessionData, "", "");
			}
		});
		
		width = width + 200;
		JButton feeAllotButton = new JButton("Fee Allotment");
		feeAllotButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		feeAllotButton.setBackground(Color.GREEN);
		feeAllotButton.setBounds(width, 50, 150, 24);
		topbandPanel.add(feeAllotButton);

		feeAllotButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				StudentFeeAllot.getStudentFeeAllot(sessionData, "", "", "", "", "", "", "");
			}
		});
		
		width = width + 160;
		JButton feeSearchButton = new JButton("Search & Pay");
		feeSearchButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		feeSearchButton.setBounds(width, 50, 150, 24);
		topbandPanel.add(feeSearchButton);

		feeSearchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LinkedHashMap retStudentMap = new LinkedHashMap();
				new SearchFeeStudentNew(sessionData, "", "", "", "", "", "", retStudentMap, sessionData.getSectionName(), "", "", "", "", false, "", "", false);
			}
		});
		
		/*width = width + 190;
		JButton feePayButton = new JButton("Fees Payment");
		feePayButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		feePayButton.setBounds(width, 50, 170, 24);
		topbandPanel.add(feePayButton);

		feePayButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}
		});*/
		
		width = width + 160;
		JButton feeReportButton = new JButton("Fee Report");
		feeReportButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		feeReportButton.setBounds(width, 50, 150, 24);
		topbandPanel.add(feeReportButton);

		feeReportButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LinkedHashMap<String,LinkedHashMap<String, String>> studentMapEmpty =  new LinkedHashMap<>();
				FeesReport.getFeesReport(sessionData, "", "", "", "", "", "", studentMapEmpty, sessionData.getSectionName(), "", "", "", "", false);
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
		width = 40;
		JLabel academic_label = new JLabel("Academic Year :");
		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academic_label.setBounds(width, 00, 150, 50);
		findPanel.add(academic_label);
		
		width = width + 130;
		String yearList = "";
		try {
			if (dbValidate.connectDatabase(sessionData)) {
				yearList = dbValidate.getAcademicYearList(sessionData, "HS_GENERAL_REGISTER", "ACADEMIC_YEAR").toString();
			}
		}  catch (Exception e12) {
			commonObj.logException(e12);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
        String academicYearList[] = yearList.split(",");
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academicYear_combo.setSelectedItem(academicYear);
		academicYear_combo.setBounds(width, 12, 120, 25);
		findPanel.add(academicYear_combo);
		// //////////////////////////////////
		// /////////////Std//////////////
		width = width + 160;
		JLabel admittedStd_label = new JLabel("Std :");
		admittedStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_label.setBounds(width, 00, 70, 50);
		findPanel.add(admittedStd_label);

		width = width + 40;
		std = selectStd + "," + std;
		String[] stdList = std.split(",");
		final JComboBox admittedStd_combo = new JComboBox(stdList);
		admittedStd_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_combo.setBounds(width, 12, 90, 25);
		findPanel.add(admittedStd_combo);

		// /////////////Div//////////////
		width = width + 120;
		JLabel presentDiv_label = new JLabel("Div :");
		presentDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		presentDiv_label.setBounds(width, 00, 70, 50);
		findPanel.add(presentDiv_label);

		width = width + 40;
		div = selectDiv + "," + div;
		String[] divList = div.split(",");
		final JComboBox presentDiv_combo = new JComboBox(divList);
		presentDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		presentDiv_combo.setBounds(width, 12, 90, 25);
		findPanel.add(presentDiv_combo);
		
		if(!stdClass.equalsIgnoreCase("All") && !stdClass.equalsIgnoreCase("Select") && 
	    		!stdClass.equalsIgnoreCase("") && dbValidate.connectDatabase(sessionData)){
			presentDiv_combo.removeAllItems();
			String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdClass, sessionData.getSectionName(), 
					"PRESENT_DIV", "PRESENT_STD", "class_allotment",academicYearClass);
			
			for (String retval: divAvailabe.split(",")) {
				presentDiv_combo.addItem(retval);
			}
			presentDiv_combo.setSelectedItem(divClass);
		}
		
		width = width + 140;
		
		// /////////////Gender//////////////
		final JCheckBox genderCheckBox  = new JCheckBox();
		genderCheckBox.setBorder(null);
		if(!retGender.equalsIgnoreCase("")) {
			genderCheckBox.setSelected(true);
		}
        genderCheckBox.setBounds(width-20, 18, 13, 13);
        findPanel.add(genderCheckBox);
        
		JLabel gender_label = new JLabel("Gender :");
		gender_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		gender_label.setBounds(width, 00, 120, 50);
		findPanel.add(gender_label);

		String genderList[] = { "Select", "MALE", "FEMALE" };
		final JComboBox gender_combo = new JComboBox(genderList);
		gender_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		gender_combo.setSelectedItem(retGender);
		gender_combo.setBounds(width+70, 12, 110, 25);
		findPanel.add(gender_combo);
				
		width = width + 210;
		// /////////////Category//////////////
		final JCheckBox categoryCheckBox  = new JCheckBox();
		categoryCheckBox.setBorder(null);
		if(!retCategory.equalsIgnoreCase("")) {
			categoryCheckBox.setSelected(true);
		}
		categoryCheckBox.setBounds(width-20, 18, 13, 13);
        findPanel.add(categoryCheckBox);
        
		JLabel category_label = new JLabel("Category :");
		category_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		category_label.setBounds(width, 00, 120, 50);
		findPanel.add(category_label);

		String categoryList = "Select,"+bundle.getString("CATEGORY_NAMES");
		final JComboBox category_combo = new JComboBox(categoryList.split(","));
		category_combo.setSelectedItem(retCategory);
		category_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		category_combo.setBounds(width+90, 12, 100, 25);
		findPanel.add(category_combo);
				
		admittedStd_combo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			}
		});

		presentDiv_combo.setEnabled(true);

		// /////////////Submit//////////////
		width = 340;
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(width, 52, 130, 25);
		findPanel.add(submitButton);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean validateFields = true, isGenderSelected = genderCheckBox.isSelected(), 
						isCategorySelected = categoryCheckBox.isSelected();
				String std = "", genderSel ="", categorySel = "";
				String academicSel = "";
				String div = "";
				String semester = "";

				std = (String) admittedStd_combo.getSelectedItem();
				academicSel = (String) academicYear_combo.getSelectedItem();
				div = (String) presentDiv_combo.getSelectedItem();

				if (academicSel.equalsIgnoreCase("Year")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Academic year.");
				} else if (std.equalsIgnoreCase("Select")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Std.");
				} else if (div.equalsIgnoreCase("Select")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Div");
				}
				
				if(isGenderSelected && validateFields) {
					genderSel = gender_combo.getSelectedItem().toString();
					if(genderSel.isEmpty() || genderSel.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Gender");
					}
				}
				if(isCategorySelected && validateFields) {
					categorySel = category_combo.getSelectedItem().toString();
					if(categorySel.isEmpty() || categorySel.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Category");
					}
				}
				
				if(validateFields) {
					frame.setVisible(false);
					StudentFeeAllot.getStudentFeeAllot(sessionData, std, academicSel, categorySel, div, "", "", genderSel);
				}
			}

		});
		// /////////////Reset//////////////
		width = width + 150;
		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		resetButton.setBounds(width, 52, 130, 25);
		findPanel.add(resetButton);

		resetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				StudentFeeAllot.getStudentFeeAllot(sessionData, "", "", "", "", "", "", "");
			}
		});

        JSeparator jSeparator1  = new JSeparator();
        jSeparator1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        jSeparator1.setBounds(40, 100, (screenWidth - 200), 50);
        findPanel.add(jSeparator1);
        
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
		int listSize = studentMap.size();

		if (feeHeadMap.size() > 0) {
			final JRadioButton all_radio = new JRadioButton();
			all_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			all_radio.setBounds(40, 122, 20, 20);
			all_radio.setSelected(true);
			all_radio.setEnabled(false);
			findPanel.add(all_radio);
	
			JLabel std_label = new JLabel("Roll No.");
			std_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			std_label.setBounds(70, 108, 120, 50);
			findPanel.add(std_label);
	
			JLabel pipe_label1 = new JLabel("|");
			pipe_label1.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label1.setBounds(150, 108, 120, 50);
			findPanel.add(pipe_label1);
	
			JLabel sub_label = new JLabel("Student Name");
			sub_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sub_label.setBounds(170, 108, 150, 50);
			findPanel.add(sub_label);
	
			boolean enableFlag = false;
			List<String> feeHeadList = new ArrayList<String>();
	
			try {
				Set setsc = feeHeadMap.entrySet();
				Iterator isc = setsc.iterator();
				while(isc.hasNext()) {
					Map.Entry me = (Map.Entry)isc.next();
					feeHeadList.add(commonObj.revertCommaApostrophy(me.getKey().toString()));
				}
				enableFlag = true;
			} catch (Exception e1) {
				commonObj.logException(e1);
			}
			String[] FeeHeadArr = feeHeadList.toArray(new String[feeHeadList.size()]);
			// /////////////Default/Optional //////////////
			final JComboBox feeHead_combo = new JComboBox(FeeHeadArr);
			feeHead_combo.setSelectedItem(retFeeHead);
			feeHead_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			feeHead_combo.setBounds(450, 114, 250, 25);
			findPanel.add(feeHead_combo);
	
			feeHead_combo.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent e) {
	
					try {
						String selFeeHead = (String) feeHead_combo.getSelectedItem();
						if (!selFeeHead.equalsIgnoreCase("SELECT SUBJECT TITLE") && !selFeeHead.equalsIgnoreCase("")) {
							frame.setVisible(false);
							StudentFeeAllot.getStudentFeeAllot(sessionData, stdClass, academicYearClass, retCategory, divClass, selFeeHead, "", retGender);
						} else {
							JOptionPane.showMessageDialog(null, "Please Select Subject Title");
						}
					} catch (Exception e1) {
						commonObj.logException(e1);
					}
				}
			});
	
			String feeForAll = "Select for All";
			String showFeeHead = "";
			if(!multiFeeHeadMap.isEmpty()) {
				String feeFromMultiMap = "";
				Set setmfh = multiFeeHeadMap.entrySet();
				Iterator imfh = setmfh.iterator();
				while(imfh.hasNext()) {
					Map.Entry me = (Map.Entry)imfh.next();
					feeFromMultiMap = ((LinkedHashMap<?, ?>) multiFeeHeadMap.get(me.getKey())).get("fees_name").toString();
					if(feeFromMultiMap.equalsIgnoreCase(retFeeHead)) {
						showFeeHead = showFeeHead + "," + me.getKey();
						multipleFlag = true;
					}
				}
				if(!showFeeHead.equalsIgnoreCase("")) {
					showFeeHead = showFeeHead.substring(1);
				}
			}
			
			if(!showFeeHead.equalsIgnoreCase("")) {
				feeForAll = feeForAll + "," + showFeeHead;
			}
			else if(showFeeHead.equalsIgnoreCase("")) {
				feeForAll = feeForAll + "," + feeHead_combo.getSelectedItem();
			}
			
			String[] selectAllList = commonObj.revertCommaApostrophy(feeForAll).split(",");
			final JComboBox selectAll_combo = new JComboBox(selectAllList);
			selectAll_combo.setSelectedItem(selectAllFee);
			selectAll_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			selectAll_combo.setBounds(720, 114, 300, 25);
			findPanel.add(selectAll_combo);
	
//			JLabel line_label = new JLabel(
//					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//			line_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//			line_label.setBounds(30, 13, 1100, 50);
//			dataPanel.add(line_label);
	
			int j = 0;
			int l = 0;
			final JRadioButton[] sr_radio = new JRadioButton[listSize];
			final JLabel[] rollNo_labels = new JLabel[listSize];
			final JLabel[] studentName_label = new JLabel[listSize];
			final JLabel[] grNo_label = new JLabel[listSize];
			final JComboBox[] selFeeHead_combo = new JComboBox[listSize];
//			final JLabel[] optionalSubjects_label = new JLabel[listSize];
			JLabel[] line_labels = new JLabel[listSize];
			JLabel[] pipe_labels1 = new JLabel[listSize];
			// JLabel[] pipe_labels2 = new JLabel[listSize];
			// JLabel[] pipe_labels3 = new JLabel[listSize];
			String selectedStr = "";
			
			Set setsc = studentMap.entrySet();
			Iterator isc = setsc.iterator();
			int i = 0;
			while(isc.hasNext()) {
				Map.Entry me = (Map.Entry)isc.next();
				l = j + 50;
				
				String studentName = ((LinkedHashMap<?, ?>) studentMap.get(me.getKey())).get("name").toString();
				studentName = studentName.replace("*", " ");
				String grNo = me.getKey().toString();
				String rollNo = ((LinkedHashMap<?, ?>) studentMap.get(me.getKey())).get("roll").toString();
				String optFee = ((LinkedHashMap<?, ?>) studentMap.get(me.getKey())).get("optional_fee").toString();
//				if (selAllClass.equalsIgnoreCase("YES") || selAllClass.equalsIgnoreCase("NO")) {
//					subTitle = selAllClass;
//				}

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

//				String selSubTitleList[] = {"Select", "Yes", "No" };
				String[] selectList = commonObj.revertCommaApostrophy(feeForAll).replace("Select for All", "Select").split(",");
				selFeeHead_combo[i] = new JComboBox(selectList);
				selectedStr = keepSelectedFee(feeHead_combo.getSelectedItem().toString(), "", optFee);
				selFeeHead_combo[i].setSelectedItem(selectedStr);
				if(!selectAllFee.equalsIgnoreCase("")) {
					selFeeHead_combo[i].setSelectedItem(selectAllFee);
					String optFeeSel = "", optSubFeeSel = "";
					if(multipleFlag) {
						optFeeSel = feeHead_combo.getSelectedItem().toString();
						optSubFeeSel = selectAllFee;
					}
					else {
						optFeeSel = selectAllFee;
					}
					
					if(optFeeSel.equalsIgnoreCase("Select")) {
						optFeeSel = "";
					}
					if(optSubFeeSel.equalsIgnoreCase("Select")) {
						optSubFeeSel = "";
					}
					
					updateStudentMap(grNo, optFeeSel, optSubFeeSel, optFeeSel);
				}
				selFeeHead_combo[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				selFeeHead_combo[i].setBounds(660, j + 12, 300, 20);
				dataPanel.add(selFeeHead_combo[i]);
				
//				optionalSubjects_label[i] = new JLabel(optFee);
//				optionalSubjects_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
//				optionalSubjects_label[i].setBounds(800, j + 12, 470, 20);
//				dataPanel.add(optionalSubjects_label[i]);

				line_labels[i] = new JLabel(
						"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				line_labels[i].setBounds(30, j + 10, 1100, 50);
				dataPanel.add(line_labels[i]);

				final int k = i;
				
				selFeeHead_combo[i].addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						String optFeeSel = "";
						String optSubFeeSel = "";
						if(multipleFlag) {
							optFeeSel = feeHead_combo.getSelectedItem().toString();
							optSubFeeSel = selFeeHead_combo[k].getSelectedItem().toString();
						}
						else {
							optFeeSel = selFeeHead_combo[k].getSelectedItem().toString();
						}
						
						if(optFeeSel.equalsIgnoreCase("Select")) {
							optFeeSel = "";
						}
						if(optSubFeeSel.equalsIgnoreCase("Select")) {
							optSubFeeSel = "";
						}
						updateStudentMap(grNo_label[k].getText(), optFeeSel, optSubFeeSel, feeHead_combo.getSelectedItem().toString());
					}
				});
				
				/*selFeeHead_combo[i].addFocusListener(new FocusListener() {

					String subjectDetail = "";

					@Override
					public void focusGained(FocusEvent arg0) {
//						subjectDetail = studentName_label[k].getText() + "|" + grNo_label[k].getText() + "||"
//								+ rollNo_labels[k].getText() + "|||" + selFeeHead_combo[k].getSelectedItem() + "||||" + optionalSubjects_label[k].getText();
//						subjectList.remove(subjectDetail);
					}

					@Override
					public void focusLost(FocusEvent arg0) {
						updateStudentMap(grNo_label[k].getText(), selFeeHead_combo[k].getSelectedItem().toString());
//						subjectDetail = studentName_label[k].getText() + "|" + grNo_label[k].getText() + "||"
//								+ rollNo_labels[k].getText() + "|||" + selFeeHead_combo[k].getSelectedItem() + "||||" + optionalSubjects_label[k].getText();
//						subjectList.add(subjectDetail);
					}
				});*/
				
				i++;
				j = j + 30;
			}

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
						int reply = 0;
						boolean flag = false;
        				reply = JOptionPane.showConfirmDialog(null, "Would You Like to optional fees for Std "+stdClass+" and Div "+divClass+"?", "Confirm validate", JOptionPane.YES_NO_OPTION);
        				
        				if(dbValidate.connectDatabase(sessionData) && reply == JOptionPane.YES_OPTION){
        					flag = dbValidate.updateFeeAllotStudents(sessionData, stdClass, divClass, academicYearClass, studentMap);
        				}
        				if(flag) {
        					JOptionPane.showMessageDialog(null, "Optional fee updated successfully");
        				}
        				else {
        					JOptionPane.showMessageDialog(null, "Optional fee failed to update");
        				}
        				
//						boolean insertSubject = false;
//						boolean validateSubList = true;
//						String updateAllSubTitle = "";
//						String selSubTitle = (String) feeHead_combo.getSelectedItem();

//						if (!selSubTitle.equalsIgnoreCase("SELECT SUBJECT TITLE") && !selSubTitle.equalsIgnoreCase("")) {
//							String[] subjectArray = new String[subjectList.size()];
//							subjectArray = subjectList.toArray(subjectArray);
//							for (int k = 0; k < subjectArray.length; k++) {
//								String subTitle = subjectArray[k].substring(subjectArray[k].indexOf("|||") + 3, subjectArray[k].indexOf("||||"));
////								String subTitle = subjectArray[k].substring(subjectArray[k].lastIndexOf("|||") + 3);
//								if (subTitle.equalsIgnoreCase("") || subTitle.equalsIgnoreCase("Select")) {
//									JOptionPane.showMessageDialog(null, "Please select Yes/No for all students");
//									validateSubList = false;
//									break;
//								}
//							}
//							if (validateSubList) {
//								insertSubject = dbValidate.updateStudentSubAllot(sessionData, subjectList, academicYearClass,
//										stdClass, divClass, updateAllSubTitle, sessionData.getSectionName(), subTitleClass);
//
//								if (!insertSubject) {
//									JOptionPane.showMessageDialog(null, "Subject data not updated successfully");
//								} else {
//									JOptionPane.showMessageDialog(null, "Subject data updated successfully");
//								}
//
//								List<String> subList = new ArrayList<String>();
//								frame.setVisible(false);
//								StudentFeeAllot.getStudentFeeAllot(sessionData, stdClass, academicYearClass, category, divClass);
//							}
//						} else {
//							JOptionPane.showMessageDialog(null, "Please Select Subject Title");
//						}

					} catch (Exception e1) {
						commonObj.logException(e1);
					}
				}
			});

			selectAll_combo.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {
						String feeAll = (String) selectAll_combo.getSelectedItem();
						frame.setVisible(false);
						StudentFeeAllot.getStudentFeeAllot(sessionData, stdClass, academicYearClass, retCategory, divClass, retFeeHead, feeAll, retGender);
						/*for(int i = 0; i < studentMap.size(); i++) {
							selFeeHead_combo[i].setSelectedItem(feeAll);
						}*/
					} catch (Exception e1) {
						commonObj.logException(e1);
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
		panel.add(mainPanel, BorderLayout.EAST);
		panel.setSize(screenWidth, screenHeight);
		frame.setVisible(true);
	}
	
	public static void updateStudentMap(String grNo, String optFeeSel, String optSubFeeSel, String feeName) {
		LinkedHashMap<String, String> headerDetails = new LinkedHashMap<String, String>();
		headerDetails = studentMap.get(grNo);
		String[] optFeeFromMap = null;
		if(!headerDetails.get("optional_fee").equalsIgnoreCase("")) {
			optFeeFromMap = headerDetails.get("optional_fee").substring(1).split("\\|");
		}
		String newOpt = "";
		if(optFeeFromMap != null && optFeeFromMap.length > 0) {
			for(int i = 0; i < optFeeFromMap.length; i++) {
				
				if(!multipleFlag && !optFeeSel.equalsIgnoreCase("")) {
					newOpt = optFeeSel +"^|";
				}
				else if(!optSubFeeSel.equalsIgnoreCase("")){
					newOpt = optFeeSel +"^"+ optSubFeeSel+"|";
				}
				
				
				if(!newOpt.equalsIgnoreCase(optFeeFromMap[i]) && !newOpt.equalsIgnoreCase("") 
						&& !headerDetails.get("optional_fee").contains(newOpt) && !optFeeFromMap[i].contains(feeName+"^")) {
					studentMap.get(grNo).put("optional_fee", (headerDetails.get("optional_fee") +"|"+ newOpt).replace("||", "|"));
				}
				else if(optFeeFromMap[i].contains(feeName+"^")){
//					studentMap.get(grNo).put("optional_fee", headerDetails.get("optional_fee").replace(optFeeFromMap[i]+"|", ""));
					studentMap.get(grNo).put("optional_fee", (headerDetails.get("optional_fee") +"|"+ newOpt).replace(optFeeFromMap[i]+"|", "").replace("||", "|"));
				}
				newOpt = "";
			}
		}else {
			if(!multipleFlag && !optFeeSel.equalsIgnoreCase("")) {
				newOpt = optFeeSel +"^|";
			}
			else if(!optSubFeeSel.equalsIgnoreCase("")){
				newOpt = optFeeSel +"^"+ optSubFeeSel+"|";
			}
			
			
			if(!newOpt.equalsIgnoreCase("")) {
				studentMap.get(grNo).put("optional_fee", (headerDetails.get("optional_fee") +"|"+ newOpt).replace("||", "|"));
			}
//			else if(optFeeFromMap[i].contains(feeName+"^")){
//				studentMap.get(grNo).put("optional_fee", (headerDetails.get("optional_fee") +"|"+ newOpt).replace(optFeeFromMap[i]+"|", "").replace("||", "|"));
//			}
			newOpt = "";
		}
		/*if(!optFeeSel.equalsIgnoreCase("Select")) {
			studentMap.get(grNo).put("optional_fee", optFeeSel+"^"+optSubFeeSel+"^|");
		} else {
			studentMap.get(grNo).put("optional_fee", "");
		}*/
	}
	
	public static String keepSelectedFee(String optFeeSel, String optSubFeeSel, String studentOptFee) {
		String keepSelected = "", optFee = "", optSubFee = "";
		String[] resultString = null;
		boolean breakLoop = false;
		resultString = studentOptFee.split("\\|");
		if(!studentOptFee.isEmpty()) {
			for(int i = 0; i < resultString.length; i++) {
				if(resultString[i].isEmpty()) {
					continue;
				}
				optFee = resultString[i].substring(0, resultString[i].indexOf("^"));
				optSubFee = resultString[i].substring(resultString[i].indexOf("^")+1);
				if(optFeeSel.equalsIgnoreCase(optFee) && !multipleFlag) {
					keepSelected = optFee;
					breakLoop = true;
				}else if(studentOptFee.contains(optFeeSel +"^"+ optSubFee) && multipleFlag && !resultString[i].endsWith("^")) {
					keepSelected = optSubFee;
					breakLoop = true;
				}
				if(breakLoop) {
					break;
				}
			}
		}
		return keepSelected;
	}
}
