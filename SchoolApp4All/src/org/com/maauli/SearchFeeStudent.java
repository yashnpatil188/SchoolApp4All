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
import java.util.Collections;
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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class SearchFeeStudent extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static int screenWidth;

    static int screenHeight;

    static int mainCentre;

    static JFrame frame = null;
    
    static String secName = "";

    static String academicYearClass = "";
    
    static String yearList = "";
    
    private static String frequencyList = "";
    
    static String img_path = "";

    static String img_home = "";
    
    static String img_founder = "";

    static String img_logo = "";

    static String img_myaccount = "";

    static String img_logout = "";

    static String img_titleband = "";

    static String img_leftband = "";

    static String img_menuband = "";

    static String img_mainband = "";
    
    static String img_databand = "";
    
    static String section = "";
    
    final static String createUser  = "Create New User";

    static DBValidate dbValidate = new DBValidate();
    
    static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

    static Logger logger = Logger.getLogger(SearchFeeStudent.class.getName());
    
    static Common commonObj = new Common();

    static SessionData sessionData = new SessionData();
    
    static String jrc_required = "";
    
    static String sch_required = "";
    
    static String schigh_required = "";
    
    static String scpri_required = "";
    
    static String scppr_required = "";

    static String user_name = "";

    static String user_role = "";
    
    static String app_header = "";
    
    static String app_header_0 = "";
    
    static String show_founder = "";
    
    static String show_donatedby = "";
    static String divClass = "";
    
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
    static String sms_required = "";
    static String fee_required = "";
    static String account_required = "";
    static String staff_required = "";
    static String url = "";
    static String tableNoClass = "";
    static String itemNameList = "";
    static String itemCodeList = "";
    static String std = "";
    static String div = "";
    static String payFrequency = "";
    static String selectStd = "";
    static String stdClass = "";
    static String optionalClass = "";
    static double totalAmount = 0;
    private static String tableOrderStatus = "";
    private static String paymentModeList = "";
    private static String frequencyClass = "";
    private static String subFrequencyClass = "";
    private static LinkedHashMap<String, LinkedHashMap<String, String>> searchStudentMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    private static LinkedHashMap<String, LinkedHashMap<String, String>> selectedStudentMap;
    private static LinkedHashMap<String, LinkedHashMap<String, String>> feesPaymentMap; 
    private static LinkedHashMap<String, LinkedHashMap<String, String>> feesHeadMapClass = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    private static LinkedHashMap<String, LinkedHashMap<String, String>> studentFeeDetailsMap;
    private static LinkedHashMap<String,LinkedHashMap<String, Double>> studentPartialFeeMap;
    private static boolean isAllSelectedClass = false;
    private static String referenceNoClass = "";
    private static int scrollHeight = 0;
    private static int startMonth = Integer.parseInt(bundle.getString("ACADEMIC_START_MONTH"));
    private static String oldAcademicClass = "";
    private static String oldStdClass = "";
    private static boolean headerRadioClass = false;
    
    public SearchFeeStudent(SessionData sessionData1, String retGr_no, String retStd, String retDiv, String retLastName,
			String retFirstName, String retFatherName, LinkedHashMap<String,LinkedHashMap<String, String>> retStudentMap, String sec, 
			String academicYear, String optional, String frequency, String subFrequency, boolean isAllSelected, 
			String oldAcademic, String oldStd, boolean headerRadio) {

    	System.gc();
    	oldAcademicClass = oldAcademic;
    	headerRadioClass = headerRadio;
    	oldStdClass = oldStd;
    	isAllSelectedClass = isAllSelected;
    	studentFeeDetailsMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    	selectedStudentMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    	feesPaymentMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    	studentPartialFeeMap = new LinkedHashMap<String,LinkedHashMap<String, Double>>();
    	section = sec;
    	totalAmount = 0;
    	selectStd = "Select";
    	frequencyClass = frequency;
    	subFrequencyClass = subFrequency;
    	searchStudentMap = retStudentMap;
    	stdClass = retStd;
    	optionalClass = optional;
    	academicYearClass = academicYear;
    	sessionData = sessionData1;
		stdClass = retStd;
		divClass = retDiv;
        user_name = sessionData1.getUserName();
        user_role = sessionData1.getUserRole();
        setVisible(false);
        dispose();
        
        frame = new JFrame("Welcome to "+sessionData1.getAppName());
        std = bundle.getString(section.toUpperCase() + "_STD");
        div = bundle.getString(section.toUpperCase() + "_DIV");
        payFrequency = bundle.getString("PAY_FREQUENCY");
        img_path = bundle.getString("IMAGE_PATH");
        logger.info("img_path :: " + img_path);
        img_home = bundle.getString("IMAGE_HOME");
        img_founder = bundle.getString("IMGAGE_FOUNDER");
        img_logo = bundle.getString("IMAGE_LOGO");
        img_myaccount = bundle.getString("IMAGE_MYACCOUNT");
        img_logout = bundle.getString("IMAGE_LOGOUT");
        img_titleband = bundle.getString("IMAGE_TITLEBAND");
        img_leftband = bundle.getString("IMAGE_LEFTBAND");
        img_menuband = bundle.getString("IMAGE_MENUBAND");
        img_mainband = bundle.getString("IMAGE_MAINBAND");
        img_databand = bundle.getString("IMAGE_DATABAND");
        sms_required = bundle.getString("SMS_REQUIRED");
        fee_required = bundle.getString("FEE_REQUIRED");
        account_required = bundle.getString("ACCOUNT_REQUIRED");
    	staff_required = bundle.getString("STAFF_REQUIRED");
        url = bundle.getString("DBURL_"+sessionData1.getDBName());
        secName = bundle.getString(section.toUpperCase() + "_SEC");
        paymentModeList = bundle.getString("PAYMENT_MODE");
        
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
    	frequencyList =  bundle.getString("SEARCH_FREQUENCY");
    	
        jrc_required = bundle.getString("JRC_REQUIRED");
        sch_required = bundle.getString("SCH_REQUIRED");
        schigh_required = bundle.getString("SCHIGH_REQUIRED");
        scpri_required = bundle.getString("SCPRI_REQUIRED");
        scppr_required = bundle.getString("SCPPR_REQUIRED");
        show_founder = bundle.getString("SHOW_FOUNDER");
        show_donatedby = bundle.getString("SHOW_DONATEDBY");
        
        if(academicYearClass.trim().equalsIgnoreCase("")){
        	String todayDate = commonObj.getCurrentDate();
            academicYearClass = commonObj.getAcademicYear(todayDate);
        }
        
        try {
			if(dbValidate.connectDatabase(sessionData)){
				String std = stdClass;
				String academic = academicYearClass;
				feesHeadMapClass = dbValidate.getFeesHeadData(sessionData, academic, std, section, optionalClass);
				
				if(!frequencyClass.equalsIgnoreCase("Part Pay")){
					studentPartialFeeMap = 
							dbValidate.getPartialFeesData(sessionData1, academic, sessionData.getSectionName(), std, divClass, 
									optionalClass, frequencyClass, subFrequencyClass, feesHeadMapClass);
				}
				yearList = dbValidate.getAcademicYearList(sessionData, "HS_GENERAL_REGISTER", "ACADEMIC_YEAR").toString();
				if(!searchStudentMap.isEmpty()){
					scrollHeight = (searchStudentMap.size() - 6) * 30; // to adjust the perfect scroll height
				}
				
//				dbValidate.deleteDuplicateData(sessionData1, academicYear, stdClass, divClass, "fees_data_mandatory", "STD_1", "DIV_1");
			}
		} catch (Exception e1) {
            logger.error("Exception while getting table order ==>>>" + e1);
        }
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

    private static <E> void placeComponents(JPanel panel) {

        panel.setLayout(new BorderLayout());

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
        JLabel label = new JLabel(icon);
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
					logger.info(e);
				}
			}
		});
        
        int p = 50;
        JButton studentButton = new JButton("Student");
        studentButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        studentButton.setBounds(10, p + 50, 130, 35);
        leftPanel.add(studentButton);
        
        studentButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                new Student(sessionData, section, user_name, user_role);
            }
        });

        if(staff_required.equalsIgnoreCase("true")){
	        p = p + 50;
	        JButton staffButton = new JButton("Staff");
	        staffButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
	        staffButton.setBounds(10, p + 50, 130, 35);
	        leftPanel.add(staffButton);
	
	        staffButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	
	                frame.setVisible(false);
	                new Staff(sessionData, section, user_name, user_role);
	            }
	        });
        }
        p = p + 50;
        JButton academicButton = new JButton("Academic");
        academicButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        academicButton.setBounds(10, p + 50, 130, 35);
        leftPanel.add(academicButton);

        academicButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                new Academic(sessionData, section, user_name, user_role);
            }
        });

        if(account_required.equalsIgnoreCase("true")){
	        p = p + 50;
	        JButton accountButton = new JButton("Account");
	        accountButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
	        accountButton.setBounds(10, p + 50, 130, 35);
	//        leftPanel.add(accountButton);
	
	        accountButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	                frame.setVisible(false);
	                new CreateSubject(sessionData, "", "", section, user_name, user_role, "");
	            }
	        });
        }
        p = p + 50;
        JButton searchButton = new JButton("Find");
        searchButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        searchButton.setBounds(10, p + 50, 130, 35);
        leftPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            	LinkedHashMap<String,LinkedHashMap<String, String>> studMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
                new FindStudent(sessionData, "", "", "", "", "", "", studMap, section,
                    user_name, user_role, false);
                frame.setVisible(false);
            }
        });
        
        if(sms_required.equalsIgnoreCase("true")){
	        p = p + 50;
	        JButton smsButton = new JButton("SMS");
	        smsButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
	        smsButton.setBounds(10, p + 50, 130, 35);
	        leftPanel.add(smsButton);
	
	        smsButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	
	            	frame.setVisible(false);
	            	LinkedHashMap findStudentMap = new LinkedHashMap();
					new SmsPage(sessionData, "", "", "", "", "", "", findStudentMap, false, "", "", "", "", "",
							"", section, "", user_name, user_role,"","","");
	            }
	        });
        }
        
        if(fee_required.equalsIgnoreCase("true")){
	        p = p + 50;
	        JButton feeButton = new JButton("FEE");
	        feeButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
	        feeButton.setBackground(Color.GREEN);
	        feeButton.setBounds(10, p + 50, 130, 35);
	        leftPanel.add(feeButton);
	
	        feeButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	
	            	frame.setVisible(false);
					new CreateFeesHead(sessionData, "", "", section);
	            }
	        });
        }
        
        panel.add(leftPanel, BorderLayout.WEST);

        // ///////////main panel////////////////////////////////////////
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // ///////////menu band panel////////////////////////////////////////
        JPanel topbandPanel = new JPanel() {

            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {

                Image img = new ImageIcon(img_path + img_menuband).getImage();
                Dimension size = new Dimension(screenWidth - 150, 80);
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
                g.drawImage(img, 0, 0, null);
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
		JButton createFeeButton = new JButton("Fee Head");
		createFeeButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		createFeeButton.setBounds(width, 50, 140, 24);
		topbandPanel.add(createFeeButton);

		createFeeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
            	LinkedHashMap findStudentMap = new LinkedHashMap();
				new CreateFeesHead(sessionData, "", "", section);
			}
		});
		
		/*width = width + 190;
		JButton feeAllotButton = new JButton("Fee Allotment");
		feeAllotButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		feeAllotButton.setBounds(width, 50, 150, 24);
		topbandPanel.add(feeAllotButton);

		feeAllotButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}
		});*/
		
		width = width + 160;
		JButton feeSearchButton = new JButton("Search & Pay");
		feeSearchButton.setBackground(Color.GREEN);
		feeSearchButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		feeSearchButton.setBounds(width, 50, 150, 24);
		topbandPanel.add(feeSearchButton);

		feeSearchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

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
				FeesReport.getFeesReport(sessionData, "", "", "", "", "", "", studentMapEmpty, section, "", "", "", "", false);
			}
		});
		
        mainPanel.add(topbandPanel, BorderLayout.NORTH);

        // ///////////bottom band/////////////
        final JPanel bottombandPanel = new JPanel() {

            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {

                Image img = new ImageIcon(img_path + img_mainband).getImage();
                Dimension size =
                    new Dimension(screenWidth - 150, screenHeight - 150);
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
                g.drawImage(img, 0, 0, null);
            }
        };
        bottombandPanel.setLayout(null);
        
        int tabHeight = 150;
        
        ///founder//////////////
        /*final ImageIcon iconfounder = new ImageIcon(img_path + img_founder);
        JLabel labelfounder = new JLabel(iconfounder);
        labelfounder.setBounds(mainCentre - 160, -30, 315, 417);
        if(show_founder.equalsIgnoreCase("true")){
        	tabHeight = tabHeight + 180;
        	bottombandPanel.add(labelfounder);
        }*/
        
        int bottomBandItemHeight = 30;
        
     // /////////////GR No.//////////////
		final JRadioButton gr_no_radio = new JRadioButton();
		gr_no_radio.setBounds(30, bottomBandItemHeight + 15, 20, 20);
		bottombandPanel.add(gr_no_radio);
      		
 		JLabel gr_no_label = new JLabel("G.R. No.:");
 		gr_no_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		gr_no_label.setBounds(60, bottomBandItemHeight, 70, 50);
 		bottombandPanel.add(gr_no_label);

 		final JTextField gr_no_text = new JTextField();
 		gr_no_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		gr_no_text.setBounds(160, bottomBandItemHeight + 12, 130, 25);
 		bottombandPanel.add(gr_no_text);

 		/*SwingUtilities.invokeLater(new Runnable() {

 			public void run() {

 				gr_no_text.requestFocus();
 			}
 		});*/

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
 		final JRadioButton std_radio = new JRadioButton();
 		std_radio.setBounds(350, bottomBandItemHeight + 15, 20, 20);
 		bottombandPanel.add(std_radio);
 		
 		JLabel admittedStd_label = new JLabel("Std :");
 		admittedStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		admittedStd_label.setBounds(380, bottomBandItemHeight, 70, 50);
 		bottombandPanel.add(admittedStd_label);

 		String dispStd = stdClass + "," + std;
 		String[] stdList = dispStd.split(",");
 		final JComboBox std_combo = new JComboBox(stdList);
 		if(!oldAcademicClass.equalsIgnoreCase("")){
 			std_combo.setSelectedItem(oldStdClass);
 		}
 		std_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		std_combo.setBounds(430, bottomBandItemHeight + 12, 90, 25);
 		bottombandPanel.add(std_combo);

 		// //////////////////////////////////
 		// /////////////Div//////////////
 		JLabel admittedDiv_label = new JLabel("Div :");
 		admittedDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		admittedDiv_label.setBounds(530, bottomBandItemHeight, 70, 50);
 		bottombandPanel.add(admittedDiv_label);

 		String dispDiv = divClass + "," + div;
 		String[] divListView = dispDiv.split(",");
 		final JComboBox div_combo = new JComboBox(divListView);
 		div_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		div_combo.setBounds(580, bottomBandItemHeight + 12, 100, 25);
 		bottombandPanel.add(div_combo);
 		
 		JLabel feeType_label = new JLabel("Fee Optional :");
 		feeType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		feeType_label.setBounds(700, bottomBandItemHeight, 150, 50);
 		bottombandPanel.add(feeType_label);
 		
 		String[] optionalList = {"No","Yes"};
 		final JComboBox optional_combo = new JComboBox(optionalList);
 		optional_combo.setSelectedItem(optionalClass);
 		optional_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		optional_combo.setBounds(820, bottomBandItemHeight + 12, 100, 25);
 		bottombandPanel.add(optional_combo);
 		
 		final JRadioButton header_radio = new JRadioButton();
 		header_radio.setBounds(940, bottomBandItemHeight + 15, 20, 20);
 		header_radio.setSelected(headerRadioClass);
 		
 		JLabel header_label = new JLabel("Dnyanjyot Header");
 		header_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		header_label.setBounds(970, bottomBandItemHeight, 150, 50);
 		
 		if(sessionData.getDBName().equalsIgnoreCase("sanskaranjurschool")){
 			bottombandPanel.add(header_radio);
 			bottombandPanel.add(header_label);
        }
 		
 		//////////////////////////////////////
 		/*JLabel lcType_label = new JLabel("Allotment Type :");
 		lcType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		lcType_label.setBounds(710, 00, 140, 50);
 		bottombandPanel.add(lcType_label);
 		//////////////////////////////////////
 		if(!allomentTypeClass.equalsIgnoreCase("")){
 			allotTypeList = allomentTypeClass  +"," + allotTypeList;
 		}
 		String[] allotList = allotTypeList.split(",");
 		final JComboBox allotType_combo = new JComboBox(allotList);
 		allotType_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		allotType_combo.setBounds(860, 12, 150, 25);
 		bottombandPanel.add(allotType_combo);*/
 		
 		// /////////////Roll No.//////////////
 		JLabel rollNo_label = new JLabel("Roll No :");
 		rollNo_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		rollNo_label.setBounds(710, bottomBandItemHeight, 70, 50);
 		// bottombandPanel.add(rollNo_label);

 		JLabel from_label = new JLabel("From -");
 		from_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		from_label.setBounds(790, bottomBandItemHeight, 70, 50);
 		// bottombandPanel.add(from_label);

 		final JTextField from_text = new JTextField();
 		from_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		from_text.setBounds(850, bottomBandItemHeight+12, 40, 25);
 		// bottombandPanel.add(from_text);

 		JLabel to_label = new JLabel("To -");
 		to_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		to_label.setBounds(900, bottomBandItemHeight, 70, 50);
 		// bottombandPanel.add(to_label);

 		final JTextField to_text = new JTextField();
 		to_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		to_text.setBounds(940, bottomBandItemHeight+12, 40, 25);
 		// bottombandPanel.add(to_text);
 		// //////////////////////////////////
 		// /////////////Last Name//////////////
 		bottomBandItemHeight = bottomBandItemHeight + 40;
 		
 		final JRadioButton name_radio = new JRadioButton();
 		name_radio.setBounds(30, bottomBandItemHeight + 15, 20, 20);
 		bottombandPanel.add(name_radio);
 		
 		JLabel lastName_label = new JLabel("Last Name :");
 		lastName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		lastName_label.setBounds(60, bottomBandItemHeight, 120, 50);
 		bottombandPanel.add(lastName_label);

 		final JTextField lastName_text = new JTextField();
 		lastName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		lastName_text.setBounds(160, bottomBandItemHeight + 10, 130, 25);
 		bottombandPanel.add(lastName_text);
 		// //////////////////////////////////
 		// /////////////First Name//////////////
 		JLabel firstName_label = new JLabel("First Name :");
 		firstName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		firstName_label.setBounds(380, bottomBandItemHeight, 120, 50);
 		bottombandPanel.add(firstName_label);

 		final JTextField firstName_text = new JTextField();
 		firstName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		firstName_text.setBounds(480, bottomBandItemHeight + 10, 200, 25);
 		bottombandPanel.add(firstName_text);
 		// //////////////////////////////////
 		// /////////////Father Name//////////////
 		JLabel fatherName_label = new JLabel("Father Name :");
 		fatherName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		fatherName_label.setBounds(710, bottomBandItemHeight, 120, 50);
 		bottombandPanel.add(fatherName_label);

 		final JTextField fatherName_text = new JTextField();
 		fatherName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		fatherName_text.setBounds(830, bottomBandItemHeight+10, 200, 25);
 		bottombandPanel.add(fatherName_text);
 		// //////////////////////////////////
 		// /////////ACADEMIV YEAR radio///////////////30, 80, 120, 50
 		bottomBandItemHeight = bottomBandItemHeight + 30;
 		final JRadioButton academic_radio = new JRadioButton();
 		academic_radio.setBounds(30, bottomBandItemHeight+15, 20, 20);
 		academic_radio.setSelected(true);
 		academic_radio.setEnabled(false);
 		bottombandPanel.add(academic_radio);

 		// /////////////ACADEMIV YEAR //////////////
 		JLabel academic_label = new JLabel("Academic :");
 		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		academic_label.setBounds(60, bottomBandItemHeight, 100, 50);
 		bottombandPanel.add(academic_label);

 		String[] academicYearList = yearList.split(",");
 		final JComboBox academicYear_combo = new JComboBox(academicYearList);
 		academicYear_combo.setSelectedItem(academicYearClass);
 		if(!oldAcademicClass.equalsIgnoreCase("")){
 			academicYear_combo.setSelectedItem(oldAcademicClass);
 		}
 		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		academicYear_combo.setBounds(160, bottomBandItemHeight+15, 120, 25);
 		bottombandPanel.add(academicYear_combo);
 		
 		JLabel frequency_label = new JLabel("Frequency :");
 		frequency_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		frequency_label.setBounds(300, bottomBandItemHeight, 100, 50);
 		bottombandPanel.add(frequency_label);

 		String[] freqencyList = frequencyList.split(",");
 		final JComboBox frequency_combo = new JComboBox(freqencyList);
 		if(!stdClass.equalsIgnoreCase("")){
	 		frequency_combo.removeAllItems();
			frequency_combo.addItem("Part Pay");
			String frequencyAvailabe = dbValidate.getDistinctDiv(sessionData, stdClass, section, 
					"FREQUENCY", "STD_1", "fees_head",academicYearClass);
			if(frequencyAvailabe.contains("Monthly")){
				frequency_combo.addItem("Monthly");
				frequency_combo.addItem("Quarterly");
				frequency_combo.addItem("Half Yearly");
				frequency_combo.addItem("Yearly");
			}
			else if(frequencyAvailabe.contains("Quarterly")){
				frequency_combo.addItem("Quarterly");
				frequency_combo.addItem("Half Yearly");
				frequency_combo.addItem("Yearly");
			}
			else if(frequencyAvailabe.contains("Half Yearly")){
				frequency_combo.addItem("Half Yearly");
				frequency_combo.addItem("Yearly");
			}
			else if(frequencyAvailabe.contains("Yearly")){
				frequency_combo.addItem("Yearly");
			}
 		}
 		frequency_combo.setSelectedItem(frequencyClass);
 		frequency_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		frequency_combo.setBounds(400, bottomBandItemHeight+15, 120, 25);
 		bottombandPanel.add(frequency_combo);
 		
 		String[] freqencySubList = {};
 		final JComboBox frequencySub_combo = new JComboBox(freqencySubList);
 		frequencySub_combo.setSelectedItem(academicYearClass);
 		frequencySub_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		frequencySub_combo.setBounds(550, bottomBandItemHeight+15, 120, 25);
 		if(!frequencyClass.equalsIgnoreCase("Part Pay") && !frequencyClass.equalsIgnoreCase("Yearly") && !frequencyClass.equalsIgnoreCase("")){
 			bottombandPanel.add(frequencySub_combo);
 		}

 		if(frequencyClass.equalsIgnoreCase("Part Pay") || frequencyClass.equalsIgnoreCase("Yearly")){
			bottombandPanel.remove(frequencySub_combo);
		}
		else if(frequencyClass.equalsIgnoreCase("Monthly")){
			for(int i = 0; i < 12; i++){
				frequencySub_combo.addItem(commonObj.intgerToMonth((startMonth+i)+""));
			}
			frequencySub_combo.setSelectedItem(subFrequencyClass);
			bottombandPanel.add(frequencySub_combo);
		}
		else if(frequencyClass.equalsIgnoreCase("Quarterly")){
			for(int i = 1; i <= 4; i++){
				frequencySub_combo.addItem("Q "+i);
			}
			frequencySub_combo.setSelectedItem(subFrequencyClass);
			bottombandPanel.add(frequencySub_combo);
		}
		else if(frequencyClass.equalsIgnoreCase("Half Yearly")){
			for(int i = 1; i <= 2; i++){
				frequencySub_combo.addItem("Term "+i);
			}
			frequencySub_combo.setSelectedItem(subFrequencyClass);
			bottombandPanel.add(frequencySub_combo);
		}
 		
 		final JCheckBox admissionJCheckBox  = new JCheckBox();
 		admissionJCheckBox.setBorder(null);
 		if(!oldAcademicClass.equalsIgnoreCase("")){
 			admissionJCheckBox.setSelected(true);
		}
 		admissionJCheckBox.setBounds(690, bottomBandItemHeight+21, 13, 13);
		bottombandPanel.add(admissionJCheckBox);
		
		JLabel admission_label = new JLabel("Admission for next year");
		admission_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admission_label.setBounds(710, bottomBandItemHeight+2, 200, 50);
 		bottombandPanel.add(admission_label);
 		
 		if(!stdClass.equalsIgnoreCase("All") && !stdClass.equalsIgnoreCase("Select") && 
 	    		!stdClass.equalsIgnoreCase("") && dbValidate.connectDatabase(sessionData)){
 			div_combo.removeAllItems();
 			String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdClass, section, 
 					"PRESENT_DIV", "PRESENT_STD", "class_allotment",academicYearClass);
 			
 			for (String retval: divAvailabe.split(",")) {
 				div_combo.addItem(retval);
 			}
 			div_combo.setSelectedItem(divClass);
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
 					std_combo.setEnabled(false);
 					div_combo.setEnabled(false);
 					
 					if(!frequency_combo.getSelectedItem().toString().equalsIgnoreCase("Part Pay"))
 					{
 						JOptionPane.showMessageDialog(null, "Please select Part Pay in frequency for GR No. search.");
 					}
 				} else {
 					gr_no_radio.setSelected(false);
 					gr_no_text.setEnabled(false);
 					gr_no_text.setText("");
 				}
 			}
 		});

 		std_radio.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent e) {

// 				std_radio.setSelected(true);
 				if(std_radio.isSelected()){
 					std_combo.setEnabled(true);
 					div_combo.setEnabled(true);
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
 			}
 		});

 		std_combo.addActionListener(new ActionListener() {
 			
 			public void actionPerformed(ActionEvent e) {
 				try{
 					String stdSel = (String) std_combo.getSelectedItem();
 					String acadSel = (String) academicYear_combo.getSelectedItem();
 					String frequencySel = (String) frequency_combo.getSelectedItem();
 					
 					if(!stdSel.equalsIgnoreCase("All") && !stdSel.equalsIgnoreCase("Select") && dbValidate.connectDatabase(sessionData)){
 						div_combo.removeAllItems();
 						String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdSel, section, 
 								"PRESENT_DIV", "PRESENT_STD", "class_allotment",acadSel);
 						for (String retval: divAvailabe.split(",")) {
 							div_combo.addItem(retval);
 						}
 						
 						frequency_combo.removeAllItems();
 						frequency_combo.addItem("Part Pay");
 						String frequencyAvailabe = dbValidate.getDistinctDiv(sessionData, stdSel, section, 
 								"FREQUENCY", "STD_1", "fees_head",acadSel);
 						if(frequencyAvailabe.contains("Monthly")){
 							frequency_combo.addItem("Monthly");
 							frequency_combo.addItem("Quarterly");
 							frequency_combo.addItem("Half Yearly");
 							frequency_combo.addItem("Yearly");
 						}
 						else if(frequencyAvailabe.contains("Quarterly")){
 							frequency_combo.addItem("Quarterly");
 							frequency_combo.addItem("Half Yearly");
 							frequency_combo.addItem("Yearly");
 						}
 						else if(frequencyAvailabe.contains("Half Yearly")){
 							frequency_combo.addItem("Half Yearly");
 							frequency_combo.addItem("Yearly");
 						}
 						else if(frequencyAvailabe.contains("Yearly")){
 							frequency_combo.addItem("Yearly");
 						}
 					}
 				} catch (Exception e1) {
 					commonObj.logException(e1);
 				} finally {
 					dbValidate.closeDatabase(sessionData);
 				}
 			}
 		});

 		frequency_combo.addActionListener(new ActionListener() {
 			
 			public void actionPerformed(ActionEvent e) {
				String frequency = (String) frequency_combo.getSelectedItem();
				if(frequency != null){
					frequencySub_combo.removeAllItems();
					if(frequency.equalsIgnoreCase("Part Pay") || frequency.equalsIgnoreCase("Yearly")){
						bottombandPanel.remove(frequencySub_combo);
					}
					else if(frequency.equalsIgnoreCase("Monthly")){
						for(int i = 0; i < 12; i++){
							frequencySub_combo.addItem(commonObj.intgerToMonth((startMonth+i)+""));
						}
						bottombandPanel.add(frequencySub_combo);
					}
					else if(frequency.equalsIgnoreCase("Quarterly")){
						for(int i = 1; i <= 4; i++){
							frequencySub_combo.addItem("Q "+i);
						}
						bottombandPanel.add(frequencySub_combo);
					}
					else if(frequency.equalsIgnoreCase("Half Yearly")){
						for(int i = 1; i <= 2; i++){
							frequencySub_combo.addItem("Term "+i);
						}
						bottombandPanel.add(frequencySub_combo);
					}
					
					if(!frequency.equalsIgnoreCase("Part Pay")){
						name_radio.setSelected(false);
						gr_no_radio.setSelected(false);
						std_radio.setSelected(true);
						name_radio.setSelected(false);
	 					gr_no_text.setEnabled(false);
	 					lastName_text.setText("");
	 					lastName_text.setEnabled(false);
	 					firstName_text.setText("");
	 					firstName_text.setEnabled(false);
	 					fatherName_text.setText("");
	 					fatherName_text.setEnabled(false);
	 					std_combo.setEnabled(true);
	 					div_combo.setEnabled(true);
					}
					
					bottombandPanel.validate();
					bottombandPanel.repaint();
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
 					std_radio.setSelected(false);
 					std_combo.setEnabled(false);
 					div_combo.setEnabled(false);
 					if(!frequency_combo.getSelectedItem().toString().equalsIgnoreCase("Part Pay"))
 					{
 						JOptionPane.showMessageDialog(null, "Please select Part Pay in frequency for Name search.");
 					}
 				} else {
 					lastName_text.setEnabled(false);
 					firstName_text.setEnabled(false);
 					fatherName_text.setEnabled(false);
 				}
 			}
 		});

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
 		submitButton.setBounds(900, bottomBandItemHeight+15, 130, 25);
 		bottombandPanel.add(submitButton);
 		
 		JSeparator jSeparator1  = new JSeparator();
        jSeparator1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        jSeparator1.setBounds(40, bottomBandItemHeight+50, (screenWidth - 200), 50);
        bottombandPanel.add(jSeparator1);
        
        submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String gr_no = gr_no_text.getText();
				String lastName = lastName_text.getText();
				String firstName = firstName_text.getText();
				String fatherName = fatherName_text.getText();
				String std = (String) std_combo.getSelectedItem();
				String oldStd = "";
				if(std.equalsIgnoreCase("Select") || !std_radio.isSelected()){
					std = "";
				}
				String div = (String) div_combo.getSelectedItem();
				if(div.equalsIgnoreCase("Select") || !std_radio.isSelected()){
					div = "";
				}
				String academic = (String) academicYear_combo.getSelectedItem();
				String optional = (String) optional_combo.getSelectedItem();
				String frequency = (String) frequency_combo.getSelectedItem();
				String subFrequency = (String) frequencySub_combo.getSelectedItem();
				boolean grRadio = gr_no_radio.isSelected();
				boolean nameRadio = name_radio.isSelected();
				boolean stdRadio = std_radio.isSelected();
				boolean admissionSel = admissionJCheckBox.isSelected();
				boolean headerRadioSel = header_radio.isSelected();
				
				String oldAcademic = "";
				if(admissionSel){
					oldAcademic = academic;
					academic = commonObj.getNextYear(academic);
					oldStd = std;
					std = commonObj.IntegerToRoman("a"+(commonObj.RomanToInteger(oldStd)+1));
				}

				submitAction(sessionData, gr_no, lastName, firstName, 
			    		fatherName, std, div, academic, optional,
			    		grRadio, nameRadio, stdRadio, frequency, subFrequency, oldAcademic, oldStd, headerRadioSel);
			}

		});
        
        submitButton.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
            int keyCode=e.getKeyCode();
            if(keyCode == 10){
            	String gr_no = gr_no_text.getText();
				String lastName = lastName_text.getText();
				String firstName = firstName_text.getText();
				String fatherName = fatherName_text.getText();
				String std = (String) std_combo.getSelectedItem();
				String div = (String) div_combo.getSelectedItem();
				String academic = (String) academicYear_combo.getSelectedItem();
				String optional = (String) optional_combo.getSelectedItem();
				String frequency = (String) frequency_combo.getSelectedItem();
				String subFrequency = (String) frequencySub_combo.getSelectedItem();
				boolean grRadio = gr_no_radio.isSelected();
				boolean nameRadio = name_radio.isSelected();
				boolean stdRadio = std_radio.isSelected();
				boolean admissionSel = admissionJCheckBox.isSelected();
				boolean headerRadioSel = header_radio.isSelected();
				
				String oldAcademic = "";
				String oldStd = "";
				if(admissionSel){
					oldAcademic = academic;
					academic = commonObj.getNextYear(academic);
					oldStd = std;
					std = commonObj.IntegerToRoman("a"+(commonObj.RomanToInteger(oldStd)+1));
				}
				
				submitAction(sessionData, gr_no, lastName, firstName, 
			    		fatherName, std, div, academic, optional,
			    		grRadio, nameRadio, stdRadio, frequency, subFrequency, oldAcademic, oldStd, headerRadioSel);
              }
            }
        });
        
        // /////////////Receipt for selected students//////////////
		JButton payButton = new JButton("Pay & Receipt for selected Students");
		payButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		payButton.setBounds(40, screenHeight - 190, 300, 25);
		if(!frequencyClass.equalsIgnoreCase("Part Pay") && !frequencyClass.equalsIgnoreCase("") && !stdClass.equalsIgnoreCase("")){
			bottombandPanel.add(payButton);
		}
		
		payButton.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				boolean validate = true;
				String grNo = "";
				String paymentMode = "";
				String bankName = "";
				String chequeDDNo = "";
				String chequeDDDate = "";
				
				if(selectedStudentMap.size() == 0){
					JOptionPane.showMessageDialog(null, "Please select atleast one student");
					validate = false;
				}
				else {
					Set set = selectedStudentMap.entrySet();
	    			Iterator i = set.iterator();
	    			while(i.hasNext()) {
	    				Map.Entry me = (Map.Entry)i.next();
	    				grNo = me.getKey().toString();
	    				
	    				paymentMode = ((LinkedHashMap<?, ?>) selectedStudentMap.get(me.getKey())).get("paymentMode").toString();
	    				if(!paymentMode.equalsIgnoreCase("Cash")){
	    					if(((LinkedHashMap<?, ?>) selectedStudentMap.get(me.getKey())).get("bank") != null)
	    						bankName = ((LinkedHashMap<?, ?>) selectedStudentMap.get(me.getKey())).get("bank").toString();
	    					if(((LinkedHashMap<?, ?>) selectedStudentMap.get(me.getKey())).get("chequeDDNo") != null)
	    						chequeDDNo = ((LinkedHashMap<?, ?>) selectedStudentMap.get(me.getKey())).get("chequeDDNo").toString();
		    				if(((LinkedHashMap<?, ?>) selectedStudentMap.get(me.getKey())).get("chequeDDDate") != null)
		    					chequeDDDate = ((LinkedHashMap<?, ?>) selectedStudentMap.get(me.getKey())).get("chequeDDDate").toString();
		    				
		    				if(!validate(bankName, chequeDDNo, chequeDDDate, grNo)){
		    					validate = false;
		    					break;
		    				}
		    				bankName = "";
		    				chequeDDNo = "";
		    				chequeDDDate = "";
	    				}
	    			}
				}
				
				if(validate){
					try {
						getFeesPaymentMap(feesHeadMapClass, frequencyClass, subFrequencyClass);
						
						if(!oldAcademicClass.equalsIgnoreCase("")){
							int reply = JOptionPane.showConfirmDialog(null, "Do you really want to promote this student to next year & pay fees?", "Confirm validate", JOptionPane.YES_NO_OPTION);
							if(reply != JOptionPane.YES_OPTION){
								validate = false;
								JOptionPane.showMessageDialog(null, "Please unselect checkbox Admission for next year and submit again.");
							}
						}
						if(!feesHeadMapClass.isEmpty() && dbValidate.connectDatabase(sessionData) && validate){
							
							if(selectedStudentMap != null){
								///print receipt in PDF
								String feesForMonth = subFrequencyClass;
								if(frequencyClass.equalsIgnoreCase("Quarterly")){
									if(subFrequencyClass.equalsIgnoreCase("Q 1")){
										feesForMonth = commonObj.intgerToMonth(startMonth+"") + " - " + commonObj.intgerToMonth((startMonth+2)+"");
									}
									else if(subFrequencyClass.equalsIgnoreCase("Q 2")){
										feesForMonth = commonObj.intgerToMonth((startMonth+3)+"") + " - " + commonObj.intgerToMonth((startMonth+5)+"");
									}
									else if(subFrequencyClass.equalsIgnoreCase("Q 3")){
										feesForMonth = commonObj.intgerToMonth((startMonth+6)+"") + " - " + commonObj.intgerToMonth((startMonth+8)+"");
									}
									else if(subFrequencyClass.equalsIgnoreCase("Q 4")){
										feesForMonth = commonObj.intgerToMonth((startMonth+9)+"") + " - " + commonObj.intgerToMonth((startMonth+11)+"");
									}
								}
								else if(frequencyClass.equalsIgnoreCase("Half Yearly")){
									if(subFrequencyClass.equalsIgnoreCase("Term 1")){
										feesForMonth = commonObj.intgerToMonth(startMonth+"") + " - " + commonObj.intgerToMonth((startMonth+5)+"");
									}
									else if(subFrequencyClass.equalsIgnoreCase("Term 2")){
										feesForMonth = commonObj.intgerToMonth((startMonth+6)+"") + " - " + commonObj.intgerToMonth((startMonth+11)+"");
									}
								}
								else if(frequencyClass.equalsIgnoreCase("Yearly")){
									feesForMonth = commonObj.intgerToMonth(startMonth+"") + " - " + commonObj.intgerToMonth((startMonth+11)+"");
								}
								
								selectedStudentMap = dbValidate.updateSelectedFeeData(sessionData, selectedStudentMap, feesHeadMapClass, academicYearClass, optionalClass, 
										frequencyClass, subFrequencyClass, feesPaymentMap, totalAmount, oldAcademicClass, oldStdClass, feesHeadMapClass, feesForMonth, 0.0, 0.0);
								
								JOptionPane.showMessageDialog(null, "Payment updated successfully");
								LinkedHashMap emptyConcessionMap = new LinkedHashMap<>();
								FeeReceiptPDF.getFeeReceiptPDF(sessionData, feesPaymentMap, "0.00", 
										"", "", "", "", "", stdClass, divClass, academicYearClass, 
										"Pending", totalAmount, "", emptyConcessionMap, 0, optionalClass, 0, "", feesForMonth, 
										selectedStudentMap, headerRadioClass, null, "", null, frequencyClass, "", "", "", 0, 0);
								
								
								frame.setVisible(false);
								new SearchFeeStudentNew(sessionData, "", stdClass, divClass, "", "", "", searchStudentMap, section, academicYearClass, 
										optionalClass, frequencyClass, subFrequencyClass, isAllSelectedClass, oldAcademicClass, oldStdClass, headerRadioClass);
							}
							else{
								JOptionPane.showMessageDialog(null, "Payment updation failed.");
							}
							
							totalAmount = 0;
						}
						else if(validate){
							JOptionPane.showMessageDialog(null, "Fees Head with optional "+optionalClass+" for Std "+stdClass+" does not exist.");
						}
					} catch (Exception e1) {
						logger.info("Exception payButton from Search & Pay ===>>>" + e1);
					} finally {
						dbValidate.closeDatabase(sessionData);
					}
				}
				
			}
	
		});
      		
        /////start order headers////////////////
        int itemWidth = 40;
        bottomBandItemHeight = bottomBandItemHeight + 40;
        
        final JRadioButton radioAll = new JRadioButton();
        radioAll.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        radioAll.setBorder(null);
        radioAll.setBounds(itemWidth, bottomBandItemHeight+18, 12, 12);
        /*if(!frequencyClass.equalsIgnoreCase("Part Pay")  && !stdClass.equalsIgnoreCase("")){
        	bottombandPanel.add(radioAll);
		}*/
        
        if(isAllSelectedClass){
        	radioAll.setSelected(true);
        }
        
        itemWidth = itemWidth + 20;
        JLabel rollNo = new JLabel("Roll No.");
        rollNo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        rollNo.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(rollNo);
        
        itemWidth = itemWidth + 70;
        JLabel grNo = new JLabel("GR No.");
        grNo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        grNo.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(grNo);
        
        itemWidth = itemWidth + 90;
        /*JLabel stdDiv = new JLabel("Std-Div");
        stdDiv.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        stdDiv.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(stdDiv);
        
        itemWidth = itemWidth + 100;*/
        JLabel name = new JLabel("Name");
        name.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        name.setBounds(itemWidth, bottomBandItemHeight, 600, 50);
        bottombandPanel.add(name);
        
        if(!frequencyClass.equalsIgnoreCase("Part Pay") && !stdClass.equalsIgnoreCase("")){
	        itemWidth = itemWidth + 360;
	        JLabel paymentMode = new JLabel("Payment By");
	        paymentMode.setFont(new Font("Book Antiqua", Font.BOLD, 16));
	        paymentMode.setBounds(itemWidth, bottomBandItemHeight, 600, 50);
	        bottombandPanel.add(paymentMode);
        }
        
        radioAll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean radioAllSel = radioAll.isSelected();
				
				if(radioAllSel){
					frame.setVisible(false);
					new SearchFeeStudentNew(sessionData, "", stdClass, divClass, "", "", "", searchStudentMap, section, academicYearClass, 
							optionalClass, frequencyClass, subFrequencyClass, radioAllSel, oldAcademicClass, oldStdClass, headerRadioClass);
				}
				else{
					frame.setVisible(false);
					new SearchFeeStudentNew(sessionData, "", stdClass, divClass, "", "", "", searchStudentMap, section, academicYearClass, 
							optionalClass, frequencyClass, subFrequencyClass, radioAllSel, oldAcademicClass, oldStdClass, headerRadioClass);
				}
			}
		});
        
        final JPanel dataPanel = new JPanel() {

            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {

                Image img = new ImageIcon(img_path + img_databand).getImage();
                Dimension size =
                    new Dimension(screenWidth - 200, screenHeight + scrollHeight);//this height is responsible to increase scroll area
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
//                g.drawImage(img, 0, 0, null);
                g.drawImage(img, 0, 0, screenWidth - 158, (screenHeight) + scrollHeight, null);
            }
        };
//      dataPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
        dataPanel.setAutoscrolls(true);
        dataPanel.setLayout(null);
        
        if (!searchStudentMap.isEmpty()) {
        	final JRadioButton[] student_radio = new JRadioButton[searchStudentMap.size()];
	        final JLabel[] rollNo_labels = new JLabel[searchStudentMap.size()];
	        final JLabel[] grNo_labels = new JLabel[searchStudentMap.size()];
	        final JLabel[] std_labels = new JLabel[searchStudentMap.size()];
	        final JLabel[] div_labels = new JLabel[searchStudentMap.size()];
//	        final JLabel[] stdDiv_labels = new JLabel[searchStudentMap.size()];
	        final JLabel[] name_labels = new JLabel[searchStudentMap.size()];
	        final JLabel[] contactNo1_labels = new JLabel[searchStudentMap.size()];
	        final JLabel[] contactNo2_labels = new JLabel[searchStudentMap.size()];
	        final JButton[] pay_buttons = new JButton[searchStudentMap.size()];
	        final JComboBox[] mode_combo = new JComboBox[searchStudentMap.size()];
	        final JLabel[] concession_labels = new JLabel[searchStudentMap.size()];
	        final JTextField[] bank_text = new JTextField[searchStudentMap.size()];
	        final JTextField[] checkDDNo_text = new JTextField[searchStudentMap.size()];
	        final JTextField[] dateDD_text = new JTextField[searchStudentMap.size()];
	        final JLabel[] slashDD_label = new JLabel[searchStudentMap.size()];
	        final JTextField[] dateMM_text = new JTextField[searchStudentMap.size()];
	        final JLabel[] slashMM_label = new JLabel[searchStudentMap.size()];
	        final JTextField[] dateYYYY_text = new JTextField[searchStudentMap.size()];
	        final JSeparator[] jSeparators  = new JSeparator[searchStudentMap.size()];
	        String stdDivFromMap = "";
	        String[] modeList = paymentModeList.split(",");
	        String nameFromMap = "", contactNo1FromMap = "",contactNo2FromMap = "";
	        String grNoMap = "";
	        String rollNoMap = "";
	        String stdMap = "";
	        String divMap = "";
	        String paymentModeMap = "";
	        
	        int k = 0;
	        int j = 5;
	        Set set = searchStudentMap.entrySet();
			Iterator i = set.iterator();
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
				stdMap = ((LinkedHashMap<?, ?>) searchStudentMap.get(me.getKey())).get("std").toString();
				divMap = ((LinkedHashMap<?, ?>) searchStudentMap.get(me.getKey())).get("div").toString();
				stdDivFromMap = stdMap + "-" + divMap;
				rollNoMap = ((LinkedHashMap<?, ?>) searchStudentMap.get(me.getKey())).get("rollNo").toString();
				grNoMap = me.getKey().toString();
				nameFromMap = ((LinkedHashMap<?, ?>) searchStudentMap.get(me.getKey())).get("name").toString();
				contactNo1FromMap = ((LinkedHashMap<?, ?>) searchStudentMap.get(me.getKey())).get("contactNo1").toString();
				contactNo2FromMap = ((LinkedHashMap<?, ?>) searchStudentMap.get(me.getKey())).get("contactNo2").toString();

				itemWidth = 20;
				student_radio[k] = new JRadioButton();
				student_radio[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				student_radio[k].setBorder(null);
				student_radio[k].setToolTipText(stdDivFromMap);
				student_radio[k].setBounds(itemWidth-10, j+5, 12, 12);
				if(!frequencyClass.equalsIgnoreCase("Part Pay") && !stdClass.equalsIgnoreCase("") 
						&& !studentPartialFeeMap.containsKey(grNoMap)){
					dataPanel.add(student_radio[k]);
					bottombandPanel.add(radioAll);
				}
				
				if(isAllSelectedClass && !studentPartialFeeMap.containsKey(grNoMap)){
					LinkedHashMap studentDetailMap = new LinkedHashMap();
					studentDetailMap.put("grNo", grNoMap);
					studentDetailMap.put("rollNo", rollNoMap);
					studentDetailMap.put("name", nameFromMap);
					studentDetailMap.put("std", stdMap);
					studentDetailMap.put("div", divMap);
					studentDetailMap.put("paymentMode", "Cash");
					studentDetailMap.put("contactNo1", contactNo1FromMap);
					studentDetailMap.put("contactNo2", contactNo2FromMap);
					selectedStudentMap.put(grNoMap, studentDetailMap);
					student_radio[k].setSelected(true);
				}
	        	
	        	itemWidth = itemWidth + 20;
				rollNo_labels[k] = new JLabel(rollNoMap);
				rollNo_labels[k].setToolTipText(stdDivFromMap);
				rollNo_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				rollNo_labels[k].setBounds(itemWidth, j, 120, 25);
	        	dataPanel.add(rollNo_labels[k]);
	        	
	        	itemWidth = itemWidth + 60;
	        	grNo_labels[k] = new JLabel(grNoMap);
	        	grNo_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	        	grNo_labels[k].setBounds(itemWidth, j, 200, 25);
	    		dataPanel.add(grNo_labels[k]);
	    		
	    		itemWidth = itemWidth + 90;
	    		std_labels[k] = new JLabel(stdMap);
	    		dataPanel.add(std_labels[k]);
	    		
	    		div_labels[k] = new JLabel(divMap);
	    		dataPanel.add(div_labels[k]);
	    		
	    		/*stdDiv_labels[k] = new JLabel(stdDivFromMap);
	    		stdDiv_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		stdDiv_labels[k].setBounds(itemWidth, j, 150, 25);
	    		dataPanel.add(stdDiv_labels[k]);
	    		
	    		itemWidth = itemWidth + 80;*/
	    		name_labels[k] = new JLabel(nameFromMap);
	    		name_labels[k].setToolTipText(nameFromMap);
	    		name_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		name_labels[k].setBounds(itemWidth, j, 350, 25);
	    		dataPanel.add(name_labels[k]);
	    		
	    		contactNo1_labels[k] = new JLabel(contactNo1FromMap);
	    		contactNo2_labels[k] = new JLabel(contactNo2FromMap);
	    		
	    		itemWidth = itemWidth + 360;
	    		pay_buttons[k] = new JButton("Pay");
	    		pay_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		pay_buttons[k].setBounds(itemWidth, j, 90, 25);
	    		
	    		mode_combo[k] = new JComboBox(modeList);
	            mode_combo[k].setBorder(null);
	            mode_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	            mode_combo[k].setBounds(itemWidth, j, 90, 25);
	            
	            itemWidth = itemWidth + 100;
	            
	            concession_labels[k] = new JLabel("Concession Applicable");
	            concession_labels[k].setBorder(null);
	            concession_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	            concession_labels[k].setBounds(itemWidth, j, 200, 25);
	            
	            bank_text[k] = new JTextField("Bank Name");
	            bank_text[k].setForeground(Color.GRAY);
	            bank_text[k].setBorder(null);
	            bank_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	            bank_text[k].setBounds(itemWidth, j, 190, 25);
	            
	            itemWidth = itemWidth + 195;
	            checkDDNo_text[k] = new JTextField("Cheque No");
	            checkDDNo_text[k].setForeground(Color.GRAY);
	            checkDDNo_text[k].setBorder(null);
	            checkDDNo_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 14));
	            checkDDNo_text[k].setBounds(itemWidth, j, 78, 25);
	            
	            itemWidth = itemWidth + 80;
	            dateDD_text[k] = new JTextField("DD");
	            dateDD_text[k].setForeground(Color.GRAY);
	            dateDD_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 14));
	            dateDD_text[k].setBounds(itemWidth, j, 30, 25);

	    		itemWidth = itemWidth + 32;
	    		slashDD_label[k] = new JLabel("/");
	    		slashDD_label[k].setFont(new Font("Book Antiqua", Font.BOLD, 14));
	    		slashDD_label[k].setBounds(itemWidth, j-12, 10, 50);

	    		itemWidth = itemWidth + 8;
	    		dateMM_text[k] = new JTextField("MM");
	    		dateMM_text[k].setForeground(Color.GRAY);
	    		dateMM_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 14));
	    		dateMM_text[k].setBounds(itemWidth, j, 38, 25);

	    		itemWidth = itemWidth + 40;
	    		slashMM_label[k] = new JLabel("/");
	    		slashMM_label[k].setFont(new Font("Book Antiqua", Font.BOLD, 14));
	    		slashMM_label[k].setBounds(itemWidth, j-12, 10, 50);

	    		itemWidth = itemWidth + 10;
	    		dateYYYY_text[k] = new JTextField("YYYY");
	    		dateYYYY_text[k].setForeground(Color.GRAY);
	    		dateYYYY_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 14));
	    		dateYYYY_text[k].setBounds(itemWidth, j, 45, 25);
	            
	    		if(!frequencyClass.equalsIgnoreCase("Part Pay") && studentPartialFeeMap.get(grNoMap) != null && 
	    				((LinkedHashMap<?, ?>) studentPartialFeeMap.get(grNoMap)).get("conecession") != null){
	    			dataPanel.add(pay_buttons[k]);
	    			dataPanel.add(concession_labels[k]);
	    		}
	    		else if(frequencyClass.equalsIgnoreCase("Part Pay") || stdClass.equalsIgnoreCase("") || 
	    				(studentPartialFeeMap.get(grNoMap) != null && 
	    				((LinkedHashMap<?, ?>) studentPartialFeeMap.get(grNoMap)).get("feesPaid") != null)){
		    		dataPanel.add(pay_buttons[k]);
	    		}
	    		else{
		            dataPanel.add(mode_combo[k]);
	    		}
	            
	    		jSeparators[k] = new JSeparator();
	    		jSeparators[k].setFont(new Font("Book Antiqua", Font.BOLD, 18));
	    		jSeparators[k].setBounds(10, j + 28, (screenWidth - 220), 50);
	    		dataPanel.add(jSeparators[k]);
	    		
	            final int m = k;
	            pay_buttons[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						String rollNoSel = rollNo_labels[m].getText();
						String grNoSel = grNo_labels[m].getText();
						String nameSel = name_labels[m].getText();
						String stdSel = std_labels[m].getText();
						String divSel = div_labels[m].getText();
						String contact1Sel = contactNo1_labels[m].getText();
						String contact2Sel = contactNo2_labels[m].getText();

						payAction(rollNoSel, grNoSel, nameSel, stdSel, divSel, contact1Sel, contact2Sel, oldAcademicClass, oldStdClass);
					}
				});
	            
	            student_radio[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						boolean radioSel = student_radio[m].isSelected();
						String grNo = grNo_labels[m].getText();
						String rollNo = rollNo_labels[m].getText();
						String name = name_labels[m].getText();
						String std = std_labels[m].getText();
						String div = div_labels[m].getText();
						String contact1Sel = contactNo1_labels[m].getText();
						String contact2Sel = contactNo2_labels[m].getText();

						if(radioSel){
							
							LinkedHashMap studentDetailMap = new LinkedHashMap();
							String paymentMode = mode_combo[m].getSelectedItem().toString();
							
							studentDetailMap.put("grNo", grNo);
							studentDetailMap.put("rollNo", rollNo);
							studentDetailMap.put("name", name);
							studentDetailMap.put("std", std);
							studentDetailMap.put("div", div);
							studentDetailMap.put("paymentMode", paymentMode);
							studentDetailMap.put("contact1", contact1Sel);
							studentDetailMap.put("contact2", contact2Sel);
							
							if(!paymentMode.equalsIgnoreCase("Cash")){
								String bank = bank_text[m].getText();
								String chequeDDNo = checkDDNo_text[m].getText();
								String chequeDD_date = dateDD_text[m].getText().trim() + "/" + dateMM_text[m].getText().trim() + "/"
										+ dateYYYY_text[m].getText().trim();
								
								if(commonObj.validateAlphaNum(bank) && !bank.equalsIgnoreCase("Bank Name")){
									studentDetailMap.put("bank", bank);
								}
								
								if(commonObj.validateNumber(chequeDDNo) && !chequeDDNo.equalsIgnoreCase("Cheque No") && !chequeDDNo.equalsIgnoreCase("DD No")){
									studentDetailMap.put("chequeDDNo", chequeDDNo);
								}
								
								if(commonObj.validateDate(chequeDD_date) && !chequeDD_date.equalsIgnoreCase("")){
									studentDetailMap.put("chequeDDDate", chequeDDNo);
								}
							}
							selectedStudentMap.put(grNo, studentDetailMap);
						}
						else{
							selectedStudentMap.remove(grNo);
						}
					}
				});
	            
	            bank_text[k].addFocusListener(new java.awt.event.FocusAdapter() {

	    			String bank = "";
	    			String grNoSel = "";

	    			public void focusGained(java.awt.event.FocusEvent event) {

	    				bank = bank_text[m].getText();
	    				bank_text[m].setForeground(Color.BLACK);
	    				if(!commonObj.validateAlphaNum(bank) || bank.equalsIgnoreCase("Bank Name")){
	    					bank_text[m].setText("");
	    				} else {
	    					bank_text[m].selectAll();
	    				}
	    			}

	    			public void focusLost(java.awt.event.FocusEvent event) {

	    				bank = bank_text[m].getText();
	    				grNoSel = grNo_labels[m].getText();
	    				LinkedHashMap studentDetailMap = new LinkedHashMap();
	    				boolean validate = true;
	    				
	    				if(selectedStudentMap.get(grNoSel) != null){
    						studentDetailMap = selectedStudentMap.get(grNoSel);
	    				}
	    				
	    				if (!commonObj.validateAlphaNum(bank)) {
	    					bank_text[m].setForeground(Color.GRAY);
	    					bank_text[m].setText("Bank Name");
	    					validate = false;
	    					studentDetailMap.remove("bank");
	    				} else if(!commonObj.charExceeded("Bank Name", bank, 100).equalsIgnoreCase("")){
	    					JOptionPane.showMessageDialog(null, commonObj.charExceeded("Bank Name", bank, 100));
	    					bank_text[m].selectAll();
	    					validate = false;
	    					studentDetailMap.remove("bank");
	    				} else if(commonObj.checkComma(bank)){
	    					JOptionPane.showMessageDialog(null, "Bank Name cannot contain characters ;',-:|");
	    					validate = false;
	    					studentDetailMap.remove("bank");
	    				}
	    				
	    				if(validate){
	    					if(selectedStudentMap.get(grNoSel) != null){
	    						studentDetailMap = selectedStudentMap.get(grNoSel);
								
								if(!bank.equalsIgnoreCase("Bank Name")){
									studentDetailMap.put("bank", bank);
								}
								selectedStudentMap.put(grNoSel, studentDetailMap);
	    					}
	    					else{
	    						studentDetailMap.put("grNo", grNoSel);
	    						studentDetailMap.put("bank", bank);
	    						selectedStudentMap.put(grNoSel, studentDetailMap);
	    					}
	    				}
	    			}
	    		});
	            
	            checkDDNo_text[k].addFocusListener(new java.awt.event.FocusAdapter() {

	    			String chequeDDNo = "";
	    			String paymentMode = "";
	    			
	    			public void focusGained(java.awt.event.FocusEvent event) {

	    				chequeDDNo = checkDDNo_text[m].getText();
	    				checkDDNo_text[m].setForeground(Color.BLACK);
	    				if(!commonObj.validateNumber(chequeDDNo) || chequeDDNo.equalsIgnoreCase("Cheque No") || chequeDDNo.equalsIgnoreCase("DD No")){
	    					checkDDNo_text[m].setText("");
	    				} else {
	    					checkDDNo_text[m].selectAll();
	    				}
	    			}

	    			public void focusLost(java.awt.event.FocusEvent event) {

	    				boolean validate = true;
	    				String grNoSel = grNo_labels[m].getText();
	    				chequeDDNo = checkDDNo_text[m].getText();
	    				paymentMode = mode_combo[m].getSelectedItem().toString();
	    				LinkedHashMap studentDetailMap = new LinkedHashMap();
	    				
	    				if(selectedStudentMap.get(grNoSel) != null){
    						studentDetailMap = selectedStudentMap.get(grNoSel);
	    				}
	    				
	    				if (!commonObj.validateNumber(chequeDDNo)) {
	    					checkDDNo_text[m].setForeground(Color.GRAY);
	    					if(paymentMode.equalsIgnoreCase("Cheque")){
	    						checkDDNo_text[m].setText("Cheque No");
	    					}
	    					else if(paymentMode.equalsIgnoreCase("DD")){
	    						checkDDNo_text[m].setText("DD No");
	    					}
	    					studentDetailMap.remove("chequeDDNo");
	    					validate = false;
	    				} else if(!commonObj.charExceeded(paymentMode+" No", chequeDDNo, 10).equalsIgnoreCase("")){
	    					JOptionPane.showMessageDialog(null, commonObj.charExceeded(paymentMode+" No", chequeDDNo, 10));
	    					checkDDNo_text[m].selectAll();
	    					validate = false;
	    					studentDetailMap.remove("chequeDDNo");
	    				}
	    				
	    				if(validate){
	    					if(selectedStudentMap.get(grNoSel) != null){
	    						studentDetailMap = selectedStudentMap.get(grNoSel);
								
								studentDetailMap.put("chequeDDNo", chequeDDNo);
								selectedStudentMap.put(grNoSel, studentDetailMap);
	    					}
	    					else{
	    						studentDetailMap.put("grNo", grNoSel);
	    						studentDetailMap.put("chequeDDNo", chequeDDNo);
	    						selectedStudentMap.put(grNoSel, studentDetailMap);
	    					}
	    				}
	    			}
	    		});
	            
	            mode_combo[k].addActionListener(new ActionListener() {

	            	String grNoSel = grNo_labels[m].getText();
	            	LinkedHashMap studentDetailMap = new LinkedHashMap();
	            	
					public void actionPerformed(ActionEvent e) {
						String modeSel = (String) mode_combo[m].getSelectedItem();
						if(modeSel.equalsIgnoreCase("Cash")){
							dataPanel.remove(bank_text[m]);
				            dataPanel.remove(checkDDNo_text[m]);
				            dataPanel.remove(dateDD_text[m]);
				            dataPanel.remove(slashDD_label[m]);
				            dataPanel.remove(dateMM_text[m]);
				            dataPanel.remove(slashMM_label[m]);
				            dataPanel.remove(dateYYYY_text[m]);
				            
				            if(selectedStudentMap.get(grNoSel) != null){
	    						studentDetailMap = selectedStudentMap.get(grNoSel);
	    						studentDetailMap.remove("bank");
	    						studentDetailMap.remove("chequeDDNo");
	    						studentDetailMap.remove("chequeDDDate");
	    						
								studentDetailMap.put("paymentMode", modeSel);
								selectedStudentMap.put(grNoSel, studentDetailMap);
	    					}
	    					else{
	    						studentDetailMap.put("grNo", grNoSel);
	    						studentDetailMap.put("paymentMode", modeSel);
	    						selectedStudentMap.put(grNoSel, studentDetailMap);
	    					}
						}
						else{
							dataPanel.add(bank_text[m]);
							checkDDNo_text[m].setText(modeSel + " No");
				            dataPanel.add(checkDDNo_text[m]);
				            dataPanel.add(dateDD_text[m]);
				            dataPanel.add(slashDD_label[m]);
				            dataPanel.add(dateMM_text[m]);
				            dataPanel.add(slashMM_label[m]);
				            dataPanel.add(dateYYYY_text[m]);
				            
				            if(selectedStudentMap.get(grNoSel) != null){
	    						studentDetailMap = selectedStudentMap.get(grNoSel);
								
								studentDetailMap.put("paymentMode", modeSel);
								selectedStudentMap.put(grNoSel, studentDetailMap);
	    					}
	    					else{
	    						studentDetailMap.put("grNo", grNoSel);
	    						studentDetailMap.put("paymentMode", modeSel);
	    						selectedStudentMap.put(grNoSel, studentDetailMap);
	    					}
						}
					}
				});
	            
	            pay_buttons[k].addKeyListener(new KeyAdapter(){
	                public void keyReleased(KeyEvent e){
	                int keyCode=e.getKeyCode();
	                if(keyCode == 10){
	                	String rollNoSel = rollNo_labels[m].getText();
						String grNoSel = grNo_labels[m].getText();
						String nameSel = name_labels[m].getText();
						String stdSel = std_labels[m].getText();
						String divSel = div_labels[m].getText();
						String contact1Sel = contactNo1_labels[m].getText();
						String contact2Sel = contactNo2_labels[m].getText();

						payAction(rollNoSel, grNoSel, nameSel, stdSel, divSel, contact1Sel, contact2Sel, oldAcademicClass, oldStdClass);
	                  }
	                }
	            });
	            
	            dateDD_text[k].addFocusListener(new java.awt.event.FocusAdapter() {

	    			String strDD = "";

	    			public void focusGained(java.awt.event.FocusEvent event) {

	    				strDD = dateDD_text[m].getText();
	    				dateDD_text[m].setForeground(Color.BLACK);
	    				if (commonObj.validateOnlyNumber(strDD)) {
	    					dateDD_text[m].selectAll();
	    				} else {
	    					dateDD_text[m].setText("");
	    				}

	    			}

	    			public void focusLost(java.awt.event.FocusEvent event) {
	    				boolean validate =  true;
	    				String grNoSel = grNo_labels[m].getText();
	    				strDD = dateDD_text[m].getText();
	    				LinkedHashMap studentDetailMap = new LinkedHashMap();
	    				
	    				if(selectedStudentMap.get(grNoSel) != null){
    						studentDetailMap = selectedStudentMap.get(grNoSel);
	    				}
	    				
	    				if (!commonObj.validateOnlyNumber(strDD) || Integer.parseInt(strDD) < 1) {
	    					dateDD_text[m].setForeground(Color.GRAY);
	    					dateDD_text[m].setText("DD");
	    					validate =  false;
	    					studentDetailMap.remove("chequeDDDate");
	    				} else if (strDD.length() == 0 || strDD.length() > 2) {
	    					JOptionPane.showMessageDialog(null, "Please enter Date in two digit");
	    					dateDD_text[m].setForeground(Color.GRAY);
	    					dateDD_text[m].setText("DD");
	    					dateDD_text[m].requestFocus();
	    					validate =  false;
	    					studentDetailMap.remove("chequeDDDate");
	    				} else if(Integer.parseInt(strDD) > 31){
	    					JOptionPane.showMessageDialog(null, "Please enter valid Date in two digit");
	    					dateDD_text[m].setForeground(Color.GRAY);
	    					dateDD_text[m].setText("DD");
	    					dateDD_text[m].requestFocus();
	    					validate =  false;
	    					studentDetailMap.remove("chequeDDDate");
	    				}
	    				
	    				if(strDD.length() < 2 && !strDD.equalsIgnoreCase("0") && !strDD.equalsIgnoreCase("")){
	    					strDD = commonObj.appendZeroByLength(strDD, 2);
	    					dateDD_text[m].setText(strDD);
	    				}
	    				
	    				String chequeDD_date = dateDD_text[m].getText().trim() + "/" + dateMM_text[m].getText().trim() + "/"
								+ dateYYYY_text[m].getText().trim();
	    				if (!commonObj.validateDate(chequeDD_date) || chequeDD_date.equalsIgnoreCase("")) {
	    					validate =  false;
	    				}
	    				
	    				if(validate){
	    					if(selectedStudentMap.get(grNoSel) != null){
	    						studentDetailMap = selectedStudentMap.get(grNoSel);
								
								studentDetailMap.put("chequeDDDate", chequeDD_date);
								selectedStudentMap.put(grNoSel, studentDetailMap);
	    					}
	    					else{
	    						studentDetailMap.put("grNo", grNoSel);
	    						studentDetailMap.put("chequeDDDate", chequeDD_date);
	    						selectedStudentMap.put(grNoSel, studentDetailMap);
	    					}
	    				}

	    			}
	    		});
	            dateMM_text[k].addFocusListener(new java.awt.event.FocusAdapter() {

	    			String strMM = "";

	    			public void focusGained(java.awt.event.FocusEvent event) {

	    				strMM = dateMM_text[m].getText();
	    				dateMM_text[m].setForeground(Color.BLACK);
	    				if (commonObj.validateOnlyNumber(strMM)) {
	    					dateMM_text[m].selectAll();
	    				} else {
	    					dateMM_text[m].setText("");
	    				}

	    			}

	    			public void focusLost(java.awt.event.FocusEvent event) {
	    				boolean validate = true;
	    				String grNoSel = grNo_labels[m].getText();
	    				strMM = dateMM_text[m].getText();
	    				LinkedHashMap studentDetailMap = new LinkedHashMap();
	    				
	    				if(selectedStudentMap.get(grNoSel) != null){
    						studentDetailMap = selectedStudentMap.get(grNoSel);
	    				}
	    				
	    				if (!commonObj.validateOnlyNumber(strMM) || Integer.parseInt(strMM) < 1) {
	    					dateMM_text[m].setForeground(Color.GRAY);
	    					dateMM_text[m].setText("MM");
	    					validate =  false;
	    					studentDetailMap.remove("chequeDDDate");
	    				} else if (strMM.length() == 0 || strMM.length() > 2) {
	    					JOptionPane.showMessageDialog(null, "Please enter Month in two digit");
	    					dateMM_text[m].setForeground(Color.GRAY);
	    					dateMM_text[m].setText("MM");
	    					dateMM_text[m].requestFocus();
	    					validate =  false;
	    					studentDetailMap.remove("chequeDDDate");
	    				} else if(Integer.parseInt(strMM) > 12){
	    					JOptionPane.showMessageDialog(null, "Please enter valid Month in two digit");
	    					dateMM_text[m].setForeground(Color.GRAY);
	    					dateMM_text[m].setText("MM");
	    					dateMM_text[m].requestFocus();
	    					validate =  false;
	    					studentDetailMap.remove("chequeDDDate");
	    				}
	    				
	    				if(strMM.length() < 2 && !strMM.equalsIgnoreCase("0") && !strMM.equalsIgnoreCase("")){
	    					strMM = commonObj.appendZeroByLength(strMM, 2);
	    					dateMM_text[m].setText(strMM);
	    				}
	    				
	    				String chequeDD_date = dateDD_text[m].getText().trim() + "/" + dateMM_text[m].getText().trim() + "/"
								+ dateYYYY_text[m].getText().trim();
	    				if (!commonObj.validateDate(chequeDD_date) || chequeDD_date.equalsIgnoreCase("")) {
	    					validate =  false;
	    				}
	    				
	    				if(validate){
	    					if(selectedStudentMap.get(grNoSel) != null){
	    						studentDetailMap = selectedStudentMap.get(grNoSel);
								
								studentDetailMap.put("chequeDDDate", chequeDD_date);
								selectedStudentMap.put(grNoSel, studentDetailMap);
	    					}
	    					else{
	    						studentDetailMap.put("grNo", grNoSel);
	    						studentDetailMap.put("chequeDDDate", chequeDD_date);
	    						selectedStudentMap.put(grNoSel, studentDetailMap);
	    					}
	    				}

	    			}
	    		});
	            dateYYYY_text[k].addFocusListener(new java.awt.event.FocusAdapter() {

	    			String strYYYY = "";

	    			public void focusGained(java.awt.event.FocusEvent event) {

	    				strYYYY = dateYYYY_text[m].getText();
	    				dateYYYY_text[m].setForeground(Color.BLACK);
	    				if (commonObj.validateOnlyNumber(strYYYY)) {
	    					dateYYYY_text[m].selectAll();
	    				} else {
	    					dateYYYY_text[m].setText("");
	    				}

	    			}

	    			public void focusLost(java.awt.event.FocusEvent event) {
	    				boolean validate = true;
	    				LinkedHashMap studentDetailMap = new LinkedHashMap();
	    				String grNoSel = grNo_labels[m].getText();
	    				strYYYY = dateYYYY_text[m].getText();
	    				String chequeDD_date = dateDD_text[m].getText().trim() + "/" + dateMM_text[m].getText().trim() + "/"
								+ dateYYYY_text[m].getText().trim();
	    				
	    				if(selectedStudentMap.get(grNoSel) != null){
    						studentDetailMap = selectedStudentMap.get(grNoSel);
	    				}
	    				
	    				if (!commonObj.validateOnlyNumber(strYYYY) || Integer.parseInt(strYYYY) < 1) {
	    					dateYYYY_text[m].setForeground(Color.GRAY);
	    					dateYYYY_text[m].setText("YYYY");
	    					studentDetailMap.remove("chequeDDDate");
	    					validate = false;
	    				} else if (strYYYY.length() != 4) {
	    					JOptionPane.showMessageDialog(null, "Please enter Year in four digit");
	    					dateYYYY_text[m].setForeground(Color.GRAY);
	    					dateYYYY_text[m].setText("YYYY");
	    					dateYYYY_text[m].requestFocus();
	    					validate = false;
	    					studentDetailMap.remove("chequeDDDate");
	    				}
	    				else if (!commonObj.validateDate(chequeDD_date) || chequeDD_date.equalsIgnoreCase("")) {
	    					validate =  false;
	    					studentDetailMap.remove("chequeDDDate");
	    				}
	    				
	    				if(validate){
	    					if(selectedStudentMap.get(grNoSel) != null){
	    						studentDetailMap = selectedStudentMap.get(grNoSel);
								studentDetailMap.put("chequeDDDate", chequeDD_date);
								selectedStudentMap.put(grNoSel, studentDetailMap);
	    					}
	    					else{
	    						studentDetailMap.put("grNo", grNoSel);
	    						studentDetailMap.put("chequeDDDate", chequeDD_date);
	    						selectedStudentMap.put(grNoSel, studentDetailMap);
	    					}
	    				}
	    			}
	    		});
	    		
	    		j = j + 32;
	    		k++;
	        }
			
			bottombandPanel.revalidate();
			bottombandPanel.repaint();
        }
        
        bottomBandItemHeight = bottomBandItemHeight + 30;
        JScrollPane jsp;
        int height = 380;
        /*if(stdClass.equalsIgnoreCase("")){
        	height = 380;
        }*/
		jsp = new JScrollPane(dataPanel, 
				   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  
				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(30, bottomBandItemHeight + 10, screenWidth - 180, screenHeight - height);
		
        bottombandPanel.add(jsp, BorderLayout.SOUTH);
        ///////////////end order panel////////////
        
        mainPanel.add(bottombandPanel, BorderLayout.SOUTH);
        panel.add(mainPanel, BorderLayout.EAST);
    }
    
    private static void submitAction(SessionData sessionData, String gr_no, String lastName, String firstName, 
    		String fatherName, String std, String div, String academic, String optional,
    		boolean grRadio, boolean name_radio, boolean std_radio, String frequency, 
    		String subFrequency, String oldAcademic, String oldStd, boolean headerRadioSel){
    	
    	boolean validateFields = true;
    	String mandatory = "mandatory";
    	if(optional.equalsIgnoreCase("Yes")){
    		mandatory = "optional";
    	}
    	
    	Common commonObj = new Common();
		
		if (grRadio && (gr_no.equalsIgnoreCase("") || gr_no.equalsIgnoreCase("0000000"))) {
			validateFields = false;
			JOptionPane.showMessageDialog(null, "Please enter valid GR No.");
		}
		else if(!frequency.equalsIgnoreCase("Part Pay") && grRadio)
		{
			validateFields = false;
			JOptionPane.showMessageDialog(null, "Please select Part Pay in frequency for GR No search.");
		}
		else if (commonObj.checkComma(gr_no)) {
			validateFields = false;
			JOptionPane.showMessageDialog(null, "GR_NO cannot contain |-:';,");
		} 
		else if (name_radio){
			if(!frequency.equalsIgnoreCase("Part Pay"))
			{
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Please select Part Pay in frequency for Name search");
			}
			else if (lastName.length() < 1 && firstName.length() < 1 && fatherName.length() < 1) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Please enter Lastname or firstname or fathername");
			}
			else if (lastName.length() > 50) {
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
		}
		else if (std_radio){
			if(std.equalsIgnoreCase("Select") || std.equalsIgnoreCase("")){
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Please select std.");
			}
		}
		
		LinkedHashMap<String,LinkedHashMap<String, String>> studentMap;
		if (validateFields && dbValidate.connectDatabase(sessionData)) {
			try {
				
				/////insert new students//////////
//				dbValidate.insertNewFeeStudents(sessionData, std, div, academic);
//				dbValidate.updateNameInFeesData(sessionData);
				/////////////////////////
				
				studentMap = dbValidate.findFeeStudent(sessionData, gr_no, std, div, lastName, firstName, fatherName, section, academic, oldAcademic, oldStd);
				int listSize = studentMap.size();
				logger.info("No of students found :: " + listSize);

				if (listSize > 0) {
					frame.setVisible(false);
					new SearchFeeStudentNew(sessionData, gr_no, std, div, lastName, firstName, fatherName, studentMap, section, academic, optional, 
							frequency, subFrequency, false, oldAcademic, oldStd, headerRadioSel);
				} else {
					JOptionPane.showMessageDialog(null, "No data found");
						frame.setVisible(false);
						new SearchFeeStudentNew(sessionData, gr_no, std, div, lastName, firstName, fatherName, studentMap, section, academic, optional, 
								frequency, subFrequency, false, oldAcademic, oldStd, headerRadioSel);

				}

			} catch (Exception e1) {
				commonObj.logException(e1);
				JOptionPane.showMessageDialog(null, "Try Update Name in Fees Data on Help page");
				frame.setVisible(false);
				LinkedHashMap<String,LinkedHashMap<String, String>> studentMapEmpty =  new LinkedHashMap<>();
				new SearchFeeStudentNew(sessionData, "", "", "", "", "", "", studentMapEmpty, section, "", "", "", "", false, oldAcademic, oldStd, headerRadioClass);
			}
		}
	}
    
    private static void payAction(String rollNoSel, String grNoSel, String nameSel, String stdSel, String divSel, 
    		String contact1, String contact2, String oldAcademic, String oldStd){
    	try {
    		int reply = 0;
			LinkedHashMap<String, LinkedHashMap<String, String>> feesHeadMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
			
			if(dbValidate.connectDatabase(sessionData)){
				feesHeadMap = dbValidate.getFeesHeadData(sessionData, academicYearClass, stdSel, section, optionalClass);
			}
			
			if(!feesHeadMap.isEmpty()){
				Set set = feesHeadMap.entrySet();
				Iterator i = set.iterator();
				int frequencyInt = 0;
				ArrayList<Integer> frequencyList = new ArrayList<>();
				while(i.hasNext()) {
					Map.Entry me = (Map.Entry)i.next();
					
					frequencyInt = commonObj.frequencyToInteger(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("frequency").toString());
					if(!frequencyList.contains(frequencyInt)){
						frequencyList.add(frequencyInt);
					}
				}
				
				Collections.sort(frequencyList);
				String maxFrequency = frequencyList.get(frequencyList.size() - 1).toString();
				LinkedHashMap<String,LinkedHashMap<String, String>> studentMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
				if(!oldAcademicClass.equalsIgnoreCase("")){
					studentMap = dbValidate.findFeeStudent(sessionData, grNoSel, stdSel, divSel, "", "", "", 
							section, academicYearClass, "", "");
				}
				if(!oldAcademicClass.equalsIgnoreCase("") && studentMap.get(grNoSel) == null){
					reply = JOptionPane.showConfirmDialog(null, "Do you really want to promote this student to next year & pay fees?", "Confirm validate", JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_OPTION){
						frame.setVisible(false);
						new FeesView(sessionData, grNoSel, stdSel, divSel, nameSel, rollNoSel, searchStudentMap, section, academicYearClass, 
								optionalClass, feesHeadMap, maxFrequency, frequencyClass, subFrequencyClass, contact1, contact2, 
								oldAcademicClass, oldStdClass, headerRadioClass);
					}
					else{
						JOptionPane.showMessageDialog(null, "Please unselect checkbox Admission for next year and submit again.");
					}
				}
				else{
					frame.setVisible(false);
					new FeesView(sessionData, grNoSel, stdSel, divSel, nameSel, rollNoSel, searchStudentMap, section, academicYearClass, 
							optionalClass, feesHeadMap, maxFrequency, frequencyClass, subFrequencyClass, contact1, contact2, "", "", headerRadioClass);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Fees Head with optional "+optionalClass+" for Std "+stdSel+" does not exist for "+academicYearClass);
			}
		} catch (Exception e1) {
			logger.info("Exception insert fee name ==>>>" + e1);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
    }
    
    private static boolean validate(String bank, String chequeDDNo, String chequeDD_date, String grNo){
    	if (bank.length() == 0) {
			JOptionPane.showMessageDialog(null, "Please enter Bank details for Gr "+grNo);
			return false;
		}
		else if (bank.length() > 100) {
			JOptionPane.showMessageDialog(null, commonObj.charExceeded("Bank", bank, 100));
			return false;
		} else if (commonObj.checkComma(bank)) {
			JOptionPane.showMessageDialog(null, "Bank cannot contain |-:';,  for Gr "+grNo);
			return false;
		} else if (chequeDDNo.length() == 0 || chequeDDNo.length() > 10 || !commonObj.validateNumber(chequeDDNo)) {
			JOptionPane.showMessageDialog(null, "Please enter Cheque/DD No. details for Gr "+grNo);
			return false;
		} else if (!commonObj.validateDate(chequeDD_date) || chequeDD_date.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter valid Cheque/DD Date for Gr "+grNo);
			return false;
		}
    	return true;
    }
    
    private static void getFeesPaymentMap(LinkedHashMap<String, LinkedHashMap<String, String>> feesHeadMap, String frequency, String subFrequency){
		int startMonth = Integer.parseInt(bundle.getString("ACADEMIC_START_MONTH"));
		String feeType = "";
		String freqFromMap = "";
		int freqDivisor = 0;
		
		/////while loop to get fee month list according to selected frequency
		Set set = feesHeadMap.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()) {
	    	LinkedHashMap feesTypeDetailMap = new LinkedHashMap();
			LinkedHashMap feesTypeMonthMap = new LinkedHashMap();

			Map.Entry me = (Map.Entry)i.next();
			feeType = me.getKey().toString();
			
			freqFromMap = ((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("frequency").toString();
        	freqDivisor = commonObj.frequencyToInteger(freqFromMap);
        	double amount = (Double.parseDouble(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("amount").toString())/freqDivisor);
        	
        	if(frequency.equalsIgnoreCase("Monthly")){
	        	if(freqDivisor == 1 && !commonObj.intgerToMonth(startMonth+"").equalsIgnoreCase(subFrequency)){
	        		continue;
	        	}
	        	else if(freqDivisor == 1){
	        		updateFeesPayment(amount, subFrequency, feeType, feesTypeDetailMap, feesTypeMonthMap);
	        		/*totalAmount = totalAmount + amount;
		        	feesTypeDetailMap.put("amount", amount);
					feesTypeMonthMap.put(subFrequency, feesTypeDetailMap);
					feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        	}
	        	if(freqDivisor == 2){
	        		for(int j = 0; j < 12; j+=6){
	        			if(!commonObj.intgerToMonth((startMonth + j)+"").equalsIgnoreCase(subFrequency)){
	        				continue;
		        		}
	        			else{
	        				updateFeesPayment(amount, subFrequency, feeType, feesTypeDetailMap, feesTypeMonthMap);
	        				/*totalAmount = totalAmount + amount;
	        	        	feesTypeDetailMap.put("amount", amount);
	        				feesTypeMonthMap.put(subFrequency, feesTypeDetailMap);
	        				feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        			}
	        		}
	        	}
	        	if(freqDivisor == 4){
	        		for(int j = 0; j < 12; j+=3){
	        			if(!commonObj.intgerToMonth((startMonth + j)+"").equalsIgnoreCase(subFrequency)){
	        				continue;
		        		}
	        			else{
	        				updateFeesPayment(amount, subFrequency, feeType, feesTypeDetailMap, feesTypeMonthMap);
	        				/*totalAmount = totalAmount + amount;
	        	        	feesTypeDetailMap.put("amount", amount);
	        				feesTypeMonthMap.put(subFrequency, feesTypeDetailMap);
	        				feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        			}
	        		}
	        	}
	        	
	        	if(freqDivisor == 12){
	        		updateFeesPayment(amount, subFrequency, feeType, feesTypeDetailMap, feesTypeMonthMap);
	        		/*totalAmount = totalAmount + amount;
	        		feesTypeDetailMap.put("amount", amount);
    				feesTypeMonthMap.put(subFrequency, feesTypeDetailMap);
    				feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        	}
        	}
        	else if(frequency.equalsIgnoreCase("Quarterly")){
        		int q = Integer.parseInt(subFrequency.substring(subFrequency.lastIndexOf(" ")+1));
        		if(freqDivisor == 1 && !subFrequency.equalsIgnoreCase("Q 1")){
	        		continue;
	        	}
	        	else if(freqDivisor == 1){
	        		updateFeesPayment(amount, commonObj.intgerToMonth(startMonth+""), feeType, feesTypeDetailMap, feesTypeMonthMap);
	        		/*totalAmount = totalAmount + amount;
		        	feesTypeDetailMap.put("amount", amount);
					feesTypeMonthMap.put(commonObj.intgerToMonth(startMonth+""), feesTypeDetailMap);
					feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        	}
	        	else if(freqDivisor == 2 && (subFrequency.equalsIgnoreCase("Q 2") || subFrequency.equalsIgnoreCase("Q 4"))){
	        		continue;
	        	}
	        	else if(freqDivisor == 2){
	        		if(subFrequency.equalsIgnoreCase("Q 1")){
	        			updateFeesPayment(amount, commonObj.intgerToMonth(startMonth+""), feeType, feesTypeDetailMap, feesTypeMonthMap);
	        			/*totalAmount = totalAmount + amount;
			        	feesTypeDetailMap.put("amount", amount);
						feesTypeMonthMap.put(commonObj.intgerToMonth(startMonth+""), feesTypeDetailMap);
						feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        		}
	        		else if(subFrequency.equalsIgnoreCase("Q 3")){
	        			updateFeesPayment(amount, commonObj.intgerToMonth((startMonth+6)+""), feeType, feesTypeDetailMap, feesTypeMonthMap);
	        			/*totalAmount = totalAmount + amount;
			        	feesTypeDetailMap.put("amount", amount);
						feesTypeMonthMap.put(commonObj.intgerToMonth((startMonth+6)+""), feesTypeDetailMap);
						feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        		}
	        	}
	        	else if(freqDivisor == 4){
	        		updateFeesPayment(amount, commonObj.intgerToMonth((startMonth-3+(3*q)+"")), feeType, feesTypeDetailMap, feesTypeMonthMap);
    				/*totalAmount = totalAmount + amount;
    	        	feesTypeDetailMap.put("amount", amount);
    				feesTypeMonthMap.put(commonObj.intgerToMonth((startMonth-3+(3*q)+"")), feesTypeDetailMap);
    				feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        	}
	        	else if(freqDivisor == 12){
	        		int k = 1;
	        		if(subFrequency.equalsIgnoreCase("Q 2")){
	        			k = 4;
	        		}
	        		else if(subFrequency.equalsIgnoreCase("Q 3")){
	        			k = 7;
	        		}
	        		else if(subFrequency.equalsIgnoreCase("Q 4")){
	        			k = 10;
	        		}
	        		for(int j = k-1; j < k+2; j++){
	        			updateFeesPayment(amount, commonObj.intgerToMonth((startMonth+(j)+"")), feeType, feesTypeDetailMap, feesTypeMonthMap);
	        			/*totalAmount = totalAmount + amount;
	    	        	feesTypeDetailMap.put("amount", amount);
	    				feesTypeMonthMap.put(commonObj.intgerToMonth((startMonth+(j)+"")), feesTypeDetailMap);
	    				feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        		}
	        	}
        	}
        	else if(frequency.equalsIgnoreCase("Half Yearly")){
        		int q = Integer.parseInt(subFrequency.substring(subFrequency.lastIndexOf(" ")+1));
        		if(freqDivisor == 1 && !subFrequency.equalsIgnoreCase("Term 1")){
	        		continue;
	        	}
	        	else if(freqDivisor == 1){
	        		updateFeesPayment(amount, commonObj.intgerToMonth(startMonth+""), feeType, feesTypeDetailMap, feesTypeMonthMap);
	        		/*totalAmount = totalAmount + amount;
		        	feesTypeDetailMap.put("amount", amount);
					feesTypeMonthMap.put(commonObj.intgerToMonth(startMonth+""), feesTypeDetailMap);
					feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        	}
	        	else if(freqDivisor == 2){
	        		if(subFrequency.equalsIgnoreCase("Term 1")){
	        			updateFeesPayment(amount, commonObj.intgerToMonth(startMonth+""), feeType, feesTypeDetailMap, feesTypeMonthMap);
	        			/*totalAmount = totalAmount + amount;
			        	feesTypeDetailMap.put("amount", amount);
						feesTypeMonthMap.put(commonObj.intgerToMonth(startMonth+""), feesTypeDetailMap);
						feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        		}
	        		else if(subFrequency.equalsIgnoreCase("Term 2")){
	        			updateFeesPayment(amount, commonObj.intgerToMonth((startMonth+6)+""), feeType, feesTypeDetailMap, feesTypeMonthMap);
	        			/*totalAmount = totalAmount + amount;
			        	feesTypeDetailMap.put("amount", amount);
						feesTypeMonthMap.put(commonObj.intgerToMonth((startMonth+6)+""), feesTypeDetailMap);*/
						feesPaymentMap.put(feeType, feesTypeMonthMap);
	        		}
	        	}
	        	else if(freqDivisor == 4){
	        		int k = 1;
	        		if(subFrequency.equalsIgnoreCase("Term 2")){
	        			k = 7;
	        		}
	        		for(int j = k-1; j < k+5; j+=3){
	        			updateFeesPayment(amount, commonObj.intgerToMonth((startMonth+(j)+"")), feeType, feesTypeDetailMap, feesTypeMonthMap);
	        			/*totalAmount = totalAmount + amount;
	    	        	feesTypeDetailMap.put("amount", amount);
	    				feesTypeMonthMap.put(commonObj.intgerToMonth((startMonth+(j)+"")), feesTypeDetailMap);
	    				feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        		}
	        	}
	        	else if(freqDivisor == 12){
	        		int k = 1;
	        		if(subFrequency.equalsIgnoreCase("Term 2")){
	        			k = 7;
	        		}
	        		for(int j = k-1; j < k+5; j++){
	        			updateFeesPayment(amount, commonObj.intgerToMonth((startMonth+(j)+"")), feeType, feesTypeDetailMap, feesTypeMonthMap);
	        			/*totalAmount = totalAmount + amount;
	    	        	feesTypeDetailMap.put("amount", amount);
	    				feesTypeMonthMap.put(commonObj.intgerToMonth((startMonth+(j)+"")), feesTypeDetailMap);
	    				feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        		}
	        	}
        	}
        	else if(frequency.equalsIgnoreCase("Yearly")){
        		if(freqDivisor == 1){
        			updateFeesPayment(amount, commonObj.intgerToMonth(startMonth+""), feeType, feesTypeDetailMap, feesTypeMonthMap);
	        		/*totalAmount = totalAmount + amount;
		        	feesTypeDetailMap.put("amount", amount);
					feesTypeMonthMap.put(commonObj.intgerToMonth(startMonth+""), feesTypeDetailMap);
					feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        	}
	        	else if(freqDivisor == 2){
	        		updateFeesPayment(amount, commonObj.intgerToMonth(startMonth+""), feeType, feesTypeDetailMap, feesTypeMonthMap);
        			/*totalAmount = totalAmount + amount;
		        	feesTypeDetailMap.put("amount", amount);
					feesTypeMonthMap.put(commonObj.intgerToMonth(startMonth+""), feesTypeDetailMap);
					feesPaymentMap.put(feeType, feesTypeMonthMap);*/
        			
	        		updateFeesPayment(amount, commonObj.intgerToMonth((startMonth+6)+""), feeType, feesTypeDetailMap, feesTypeMonthMap);
					/*totalAmount = totalAmount + amount;
		        	feesTypeDetailMap.put("amount", amount);
					feesTypeMonthMap.put(commonObj.intgerToMonth((startMonth+6)+""), feesTypeDetailMap);
					feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        	}
	        	else if(freqDivisor == 4){
	        		for(int j = 0; j < 12; j+=3){
	        			updateFeesPayment(amount, commonObj.intgerToMonth((startMonth+(j)+"")), feeType, feesTypeDetailMap, feesTypeMonthMap);
	        			/*totalAmount = totalAmount + amount;
	    	        	feesTypeDetailMap.put("amount", amount);
	    				feesTypeMonthMap.put(commonObj.intgerToMonth((startMonth+(j)+"")), feesTypeDetailMap);
	    				feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        		}
	        	}
	        	else if(freqDivisor == 12){
	        		for(int j = 0; j < 12; j++){
	        			updateFeesPayment(amount, commonObj.intgerToMonth((startMonth+(j)+"")), feeType, feesTypeDetailMap, feesTypeMonthMap);
	        			/*totalAmount = totalAmount + amount;
	    	        	feesTypeDetailMap.put("amount", amount);
	    				feesTypeMonthMap.put(commonObj.intgerToMonth((startMonth+(j)+"")), feesTypeDetailMap);
	    				feesPaymentMap.put(feeType, feesTypeMonthMap);*/
	        		}
	        	}
        	}
		}
    }
    
    public static void updateFeesPayment(double amount, String month, String feeType, LinkedHashMap feesTypeDetailMap, LinkedHashMap feesTypeMonthMap){
		totalAmount = totalAmount + amount;
    	feesTypeDetailMap.put("amount", amount);
		feesTypeMonthMap.put(month, feesTypeDetailMap);
		feesPaymentMap.put(feeType, feesTypeMonthMap);
    }
}
