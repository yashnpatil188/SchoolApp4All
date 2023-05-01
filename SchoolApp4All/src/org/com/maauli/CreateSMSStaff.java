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
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class CreateSMSStaff extends JFrame {

	private static final long serialVersionUID = 1L;

	static int screenWidth;

    static int screenHeight;

    static int mainCentre;

    static JFrame frame = null;
    
    static String secName = "";

    static String academicYearClass = "";
    
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

    static Logger logger = Logger.getLogger(CreateSMSStaff.class.getName());
    
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
    static String designationCategory = "";
    static String headerStr = "";
    static String selectStd = "";
    static String stdClass = "";
    static String divClass = "";
    static double totalAmount = 0;
    static String tableOrderStatus = "";
    static LinkedHashMap<String, LinkedHashMap<String, String>> staffDetailMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    static LinkedHashMap<String, LinkedHashMap<String, String>> headerMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    static LinkedHashMap<String, String> shortCategoryMap = new LinkedHashMap<String, String>();
    static String referenceNoClass = "";
    static int scrollHeight = 0;
    static String todayDate = "";
    static String saveStatus = "";
    
    public CreateSMSStaff(SessionData sessionData1, String academicYear, String sec) {

    	System.gc();
    	staffDetailMap.clear();
    	headerMap.clear();
    	headerStr = "Select";
    	saveStatus = "";
    	section = sec;
    	selectStd = "Select";
    	//stdClass = retStd;
    	academicYearClass = academicYear;
    	sessionData = sessionData1;
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
    	designationCategory = bundle.getString("SMS_DESIGNATION");
        jrc_required = bundle.getString("JRC_REQUIRED");
        sch_required = bundle.getString("SCH_REQUIRED");
        schigh_required = bundle.getString("SCHIGH_REQUIRED");
        scpri_required = bundle.getString("SCPRI_REQUIRED");
        scppr_required = bundle.getString("SCPPR_REQUIRED");
        show_founder = bundle.getString("SHOW_FOUNDER");
        show_donatedby = bundle.getString("SHOW_DONATEDBY");
        todayDate = commonObj.getCurrentDate();
        
        if(academicYearClass.trim().equalsIgnoreCase("")){
            academicYearClass = commonObj.getAcademicYear(todayDate);
        }
        
        try {
			if(dbValidate.connectDatabase(sessionData)){
				if(!academicYearClass.isEmpty()){
					staffDetailMap = dbValidate.getStaffSMSData(sessionData1, academicYearClass);
					scrollHeight = (staffDetailMap.size() - 6) * 30; // to adjust the perfect scroll height
				}
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

    private static void placeComponents(JPanel panel) {

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
		
		JLabel subMenuTitle = new JLabel("SMS");
		subMenuTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		subMenuTitle.setForeground(Color.orange);
		subMenuTitle.setBounds(20, 45, 100, 30);
		topbandPanel.add(subMenuTitle);
		
		int width = 80;
		JButton smsHomeButton = new JButton("SMS Home");
		smsHomeButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		smsHomeButton.setBounds(width, 50, 140, 24);
		topbandPanel.add(smsHomeButton);

		smsHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
            	LinkedHashMap findStudentMap = new LinkedHashMap();
				new SmsPage(sessionData, "", "", "", "", "", "", findStudentMap, false, "", "", "", "", "",
						"", section, "", user_name, user_role,"","","");
			}
		});
		
		width = width + 150;
		JButton staffEntryButton = new JButton("Staff Entry");
		staffEntryButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		staffEntryButton.setBackground(Color.GREEN);
		staffEntryButton.setBounds(width, 50, 140, 24);
		topbandPanel.add(staffEntryButton);

		staffEntryButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);
//				new CreateSMSStaff(sessionData, "", section);
			}
		});
		
		width = width + 150;
		JButton smsTemplateButton = new JButton("SMS Template");
		smsTemplateButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		smsTemplateButton.setBounds(width, 50, 170, 24);
		topbandPanel.add(smsTemplateButton);

		smsTemplateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new CreateSMSTemplate(sessionData, "", section);

			}
		});
		
        mainPanel.add(topbandPanel, BorderLayout.NORTH);

        // ///////////bottom band/////////////
        JPanel bottombandPanel = new JPanel() {

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
        
        int bottomBandItemHeight = 50;
        
        // /////////////ACADEMIV YEAR //////////////
        JLabel academic_label = new JLabel("Academic Year :");
        academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        academic_label.setBounds(40, 30, 150, 50);
        bottombandPanel.add(academic_label);

        String yearList = commonObj.getAcademicYear(todayDate)+","+commonObj.getNextYear(todayDate);
        String academicYearList[] = yearList.split(",");
//        String academicYearList[] = {academicYearClass};
        final JComboBox academicYear_combo = new JComboBox(academicYearList);
        academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        academicYear_combo.setSelectedItem(academicYearClass);
        academicYear_combo.setBounds(170, 42, 160, 25);
        academicYear_combo.setEnabled(true);
        bottombandPanel.add(academicYear_combo);
        
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        submitButton.setBounds(360, 42, 90, 25);
        bottombandPanel.add(submitButton);
        
        bottomBandItemHeight = bottomBandItemHeight + 30;
        JSeparator jSeparator1  = new JSeparator();
        jSeparator1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        jSeparator1.setBounds(40, bottomBandItemHeight, (screenWidth - 200), 50);
        bottombandPanel.add(jSeparator1);
        
        submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String academicSel = academicYear_combo.getSelectedItem().toString();
				frame.setVisible(false);
				new CreateSMSStaff(sessionData, academicSel, section);
			}
		});
        
        ///////////////start Details for adding order////////////
        int itemWidth = 40;
        std = std + ",All";
        div = div + ",All";
        String[] stdList = std.split(",");
        String[] divList = div.split(",");
        final JTextField staffNameText = new JTextField();
        final JTextField contactText = new JTextField();
        String[] designationCategoryList = designationCategory.split(",");
        final JComboBox designationCombo = new JComboBox(designationCategoryList);
        final JTextField feeCatOtherText = new JTextField("Other");
        String smsOptionList[] = { "Enable", "Disable" };
        final JComboBox divCombo = new JComboBox(divList);
        final JComboBox smsCombo = new JComboBox(smsOptionList);
        final JComboBox stdCombo = new JComboBox(stdList);
        JButton addButton = new JButton("Add");
        
        bottomBandItemHeight = bottomBandItemHeight + 10;
        if(!academicYearClass.equalsIgnoreCase("")){
        	JLabel staffNameLabel = new JLabel("Staff Name : ");
        	staffNameLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        	staffNameLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(staffNameLabel);
            
            itemWidth = itemWidth + 105;
            staffNameText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            staffNameText.setBounds(itemWidth, bottomBandItemHeight + 10, 200, 25);
            bottombandPanel.add(staffNameText);
            
            itemWidth = itemWidth + 220;
            JLabel designationLabel = new JLabel("Designation : ");
            designationLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            designationLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(designationLabel);
            
            itemWidth = itemWidth + 130;
            designationCombo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            designationCombo.setBounds(itemWidth, bottomBandItemHeight + 10, 220, 25);
            bottombandPanel.add(designationCombo);
            
            itemWidth = itemWidth + 240;
            JLabel contactLabel = new JLabel("Contact No. : ");
            contactLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            contactLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(contactLabel);
            
            itemWidth = itemWidth + 105;
            contactText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            contactText.setBounds(itemWidth, bottomBandItemHeight + 10, 200, 25);
            bottombandPanel.add(contactText);
    		
            itemWidth = 40;
            bottomBandItemHeight = bottomBandItemHeight + 40;
            JLabel stdLabel = new JLabel("Std : ");
            stdLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            stdLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(stdLabel);
            
            itemWidth = itemWidth + 60;
            stdCombo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            stdCombo.setBounds(itemWidth, bottomBandItemHeight + 10, 90, 25);
            bottombandPanel.add(stdCombo);
            
            itemWidth = itemWidth + 130;
            JLabel divLabel = new JLabel("Div : ");
            divLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            divLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(divLabel);
            
            itemWidth = itemWidth + 60;
            divCombo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            divCombo.setBounds(itemWidth, bottomBandItemHeight + 10, 100, 25);
            bottombandPanel.add(divCombo);
            
            itemWidth = itemWidth + 130;
            JLabel smsLabel = new JLabel("SMS : ");
            smsLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            smsLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(smsLabel);
            
            itemWidth = itemWidth + 60;
            smsCombo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            smsCombo.setBounds(itemWidth, bottomBandItemHeight + 10, 150, 25);
            bottombandPanel.add(smsCombo);
            
            itemWidth = itemWidth + 180;
    		addButton.setFont(new Font("Book Antiqua", Font.BOLD, 25));
    		addButton.setBounds(itemWidth, bottomBandItemHeight+10, 100, 25);
            bottombandPanel.add(addButton);
            
            bottomBandItemHeight = bottomBandItemHeight + 40;
            JSeparator jSeparator2  = new JSeparator();
            jSeparator2.setFont(new Font("Book Antiqua", Font.BOLD, 18));
            jSeparator2.setBounds(40, bottomBandItemHeight, (screenWidth - 200), 50);
            bottombandPanel.add(jSeparator2);
            
            
        }
        
        /////start order headers////////////////
        itemWidth = 40;
        JLabel srNo = new JLabel("Sr No.");
        srNo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        srNo.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(srNo);
        
        itemWidth = itemWidth + 90;
        JLabel staffNameLabel = new JLabel("Staff Name");
        staffNameLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        staffNameLabel.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(staffNameLabel);
        
        itemWidth = itemWidth + 220;
        JLabel designationLabel = new JLabel("Designation");
        designationLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        designationLabel.setBounds(itemWidth, bottomBandItemHeight, 600, 50);
        bottombandPanel.add(designationLabel);
        
        itemWidth = itemWidth + 180;
        JLabel contactLabel = new JLabel("Contact");
        contactLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        contactLabel.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(contactLabel);
        
        itemWidth = itemWidth + 130;
        JLabel stdLabel = new JLabel("Std");
        stdLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        stdLabel.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(stdLabel);
        
        itemWidth = itemWidth + 70;
        JLabel divLabel = new JLabel("Div");
        divLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        divLabel.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(divLabel);
        
        itemWidth = itemWidth + 80;
        JLabel smsLabel = new JLabel("SMS Enabled");
        smsLabel.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        smsLabel.setBounds(itemWidth, bottomBandItemHeight, 150, 50);
        bottombandPanel.add(smsLabel);
        
        /////end order headers///////////////
        /////////////order panel/////////////
        
        JPanel dataPanel = new JPanel() {

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
                g.drawImage(img, 0, 0, null);
            }
        };
//      dataPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
        dataPanel.setAutoscrolls(true);
        dataPanel.setLayout(null);
        
        if (!staffDetailMap.isEmpty()) {
	        final JLabel[] srNo_labels = new JLabel[staffDetailMap.size()];
	        final JLabel[] orderNo_labels = new JLabel[staffDetailMap.size()];
	        final JTextField[] staffName_text = new JTextField[staffDetailMap.size()];
	        final JComboBox[] designation_combo = new JComboBox[staffDetailMap.size()];
	        final JTextField[] contact_text = new JTextField[staffDetailMap.size()];
	        final JComboBox[] std_combo = new JComboBox[staffDetailMap.size()];
	        final JComboBox[] div_combo = new JComboBox[staffDetailMap.size()];
	        final JComboBox[] sms_combo = new JComboBox[staffDetailMap.size()];
	        final JButton[] edit_buttons = new JButton[staffDetailMap.size()];
	        final JButton[] save_buttons = new JButton[staffDetailMap.size()];
	        final JButton[] delete_buttons = new JButton[staffDetailMap.size()];
	        final JSeparator[] jSeparators  = new JSeparator[staffDetailMap.size()];
	        String shortName = "";
//	        String[] designationCategoryList2 = designationCategory.substring(0,designationCategory.lastIndexOf(",")).split(",");
	        String staffName = "", contact = "";
	        
	        int k = 0;
	        int j = 5;
	        Set set = staffDetailMap.entrySet();
			Iterator i = set.iterator();
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
				
				itemWidth = 20;
	        	srNo_labels[k] = new JLabel((k+1)+"");
	        	srNo_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	        	srNo_labels[k].setBounds(itemWidth, j, 120, 25);
	        	dataPanel.add(srNo_labels[k]);
	        	
	        	orderNo_labels[k] = new JLabel(((LinkedHashMap<?, ?>) staffDetailMap.get(me.getKey())).get("staffName").toString());
	        	dataPanel.add(orderNo_labels[k]);
	    		
	        	itemWidth = itemWidth + 50;
	        	staffName = commonObj.revertCommaApostrophy(me.getKey().toString().replace("$$", "."));
	        	staffName_text[k] = new JTextField(staffName);
	        	staffName_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	        	staffName_text[k].setToolTipText(staffName);
	        	staffName_text[k].setBounds(itemWidth, j, 200, 25);
	        	staffName_text[k].enable(false);
	    		dataPanel.add(staffName_text[k]);
	    		
	    		itemWidth = itemWidth + 220;
	    		designation_combo[k] = new JComboBox(designationCategoryList);
	    		designation_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) staffDetailMap.get(me.getKey())).get("designation").toString());
	    		designation_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		designation_combo[k].setBounds(itemWidth, j, 170, 25);
	    		designation_combo[k].setEnabled(false);
	    		dataPanel.add(designation_combo[k]);
	    		
	    		itemWidth = itemWidth + 180;
	    		contact = ((LinkedHashMap<?, ?>) staffDetailMap.get(me.getKey())).get("contact").toString();
	    		contact_text[k] = new JTextField(contact);
	    		contact_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		contact_text[k].setToolTipText(staffName);
	    		contact_text[k].setBounds(itemWidth, j, 120, 25);
	    		contact_text[k].enable(false);
	    		dataPanel.add(contact_text[k]);
	    		
	    		itemWidth = itemWidth + 140;
	    		std_combo[k] = new JComboBox(stdList);
	    		std_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) staffDetailMap.get(me.getKey())).get("std").toString());
	    		std_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		std_combo[k].setBounds(itemWidth, j, 80, 25);
	    		std_combo[k].setEnabled(false);
	    		dataPanel.add(std_combo[k]);
	    		
	    		itemWidth = itemWidth + 80;
	    		div_combo[k] = new JComboBox(divList);
	    		div_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) staffDetailMap.get(me.getKey())).get("div").toString());
	    		div_combo[k].setToolTipText(shortName);
	    		div_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		div_combo[k].setBounds(itemWidth, j, 80, 25);
	    		div_combo[k].setEnabled(false);
	    		dataPanel.add(div_combo[k]);
	    		
	    		itemWidth = itemWidth + 90;
	    		sms_combo[k] = new JComboBox(smsOptionList);
	    		sms_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) staffDetailMap.get(me.getKey())).get("smsEnabled").toString());
	    		sms_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		sms_combo[k].setBounds(itemWidth, j, 120, 25);
	    		sms_combo[k].setEnabled(false);
	    		dataPanel.add(sms_combo[k]);
	    		
	    		itemWidth = itemWidth + 120;
	    		edit_buttons[k] = new JButton("Edit");
	    		edit_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		edit_buttons[k].setBounds(itemWidth, j, 70, 25);
	    		dataPanel.add(edit_buttons[k]);
	    		
	    		itemWidth = itemWidth + 80;
	    		save_buttons[k] = new JButton("Save");
	    		save_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		save_buttons[k].setEnabled(false);
	    		save_buttons[k].setBounds(itemWidth, j, 70, 25);
	    		dataPanel.add(save_buttons[k]);
	    		
	    		itemWidth = itemWidth + 80;
	    		delete_buttons[k] = new JButton("Delete");
	    		delete_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		delete_buttons[k].setBounds(itemWidth, j, 90, 25);
	    		dataPanel.add(delete_buttons[k]);
	    		
	    		jSeparators[k] = new JSeparator();
	    		jSeparators[k].setFont(new Font("Book Antiqua", Font.BOLD, 18));
	    		jSeparators[k].setBounds(10, j + 28, (screenWidth - 220), 50);
	    		dataPanel.add(jSeparators[k]);
	    		
	            final int m = k;
	            
	            designation_combo[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							String receiptHead = div_combo[m].getSelectedItem().toString();
			            	String designationCategory = designation_combo[m].getSelectedItem().toString();
			            	
							if(saveStatus.equalsIgnoreCase("edit")) {
				            	if(shortCategoryMap.get(designationCategory) != null && !shortCategoryMap.get(designationCategory).equalsIgnoreCase("")) {
				            		div_combo[m].setSelectedItem(shortCategoryMap.get(designationCategory));
				            	}
				            	else {
				            		div_combo[m].setSelectedItem("Select");
				            	}
							}
							else {
								JOptionPane.showMessageDialog(null, "Please click edit button to change");
							}
						} catch (Exception e1) {
							logger.info("Exception insert fee name ===>>>" + e1);
						} finally {
							dbValidate.closeDatabase(sessionData);
						}
					}
				});
	            
	            div_combo[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							String receiptHead = div_combo[m].getSelectedItem().toString();
			            	String designationCategory = designation_combo[m].getSelectedItem().toString();
			            	
							if(saveStatus.equalsIgnoreCase("edit")) {
				            	if(!receiptHead.equalsIgnoreCase("Select")) {
				            		if(!shortCategoryMap.get(designationCategory).equalsIgnoreCase(receiptHead)) {
				            			int reply = 0;
				        				reply = JOptionPane.showConfirmDialog(null, "Would You Like to change \n Receipt head "+receiptHead+" for all "+designationCategory+" Fess Category for Std "+stdClass+"?", "Confirm validate", JOptionPane.YES_NO_OPTION);
				        				
				        				if(reply != JOptionPane.YES_OPTION){
				        					div_combo[m].setSelectedItem(shortCategoryMap.get(designationCategory));
				        				}
				            		}
				            	}
							}
							else {
								div_combo[m].setSelectedItem(shortCategoryMap.get(designationCategory));
								JOptionPane.showMessageDialog(null, "Please click edit button to change");
							}
						} catch (Exception e1) {
							logger.info("Exception insert fee name ===>>>" + e1);
						} finally {
							dbValidate.closeDatabase(sessionData);
						}
					}
				});
	            
	    		edit_buttons[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							boolean isEditable = true;
							String optional = sms_combo[m].getSelectedItem().toString();
							String feesHead = staffName_text[m].getText();
							
							if(dbValidate.connectDatabase(sessionData)){
								isEditable = dbValidate.isFeesEditable(sessionData, academicYearClass, stdClass, section, optional, feesHead);
							}
							
							if(isEditable){
								contact_text[m].enable(true);
								contact_text[m].setEditable(true);
								sms_combo[m].setEnabled(true);
								edit_buttons[m].setEnabled(false);
								save_buttons[m].setEnabled(true);
								saveStatus = "edit";
							}
							else{
								JOptionPane.showMessageDialog(null, "Cannot delete as fee data for "+feesHead+" exist");
							}
						} catch (Exception e1) {
							commonObj.logException(e1);
						} finally {
							dbValidate.closeDatabase(sessionData);
						}
					}
				});
	    		
	    		save_buttons[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							
							String orderNoSel = orderNo_labels[m].getText();
							String staffNameSel = staffName_text[m].getText().trim();
							String designationSel = designation_combo[m].getSelectedItem().toString();
							String smsSel = sms_combo[m].getSelectedItem().toString();
							String contactSel = contact_text[m].getText();
							String divSel = div_combo[m].getSelectedItem().toString();
							String stdSel = std_combo[m].getSelectedItem().toString();
							
							if(dbValidate.connectDatabase(sessionData)){
								dbValidate.addSMSStaff(sessionData, staffNameSel, designationSel, contactSel, 
										stdSel, divSel, smsSel, academicYearClass, true);
							}
							
							staffName_text[m].setEditable(false);
//							std_combo[m].setEditable(false);
							edit_buttons[m].setEnabled(true);
							save_buttons[m].setEnabled(false);
							saveStatus = "save";
							
							frame.setVisible(false);
							new CreateSMSStaff(sessionData, academicYearClass, section);
						} catch (Exception e1) {
							logger.info("Exception insert fee name ===>>>" + e1);
						} finally {
							dbValidate.closeDatabase(sessionData);
						}
					}
				});
	    		
	    		delete_buttons[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						///delete fee name/////
						try {
							boolean isEditable = true;
							String orderNoSel = orderNo_labels[m].getText();
							String staffNameSel = staffName_text[m].getText().replace(".", "$$");
							staffNameSel = commonObj.replaceCommaApostrophy(staffNameSel);
							String designationSel = designation_combo[m].getSelectedItem().toString();
							String smsSel = sms_combo[m].getSelectedItem().toString();
							String contactSel = contact_text[m].getText();
							String divSel = div_combo[m].getSelectedItem().toString();
							String stdSel = std_combo[m].getSelectedItem().toString();
							
							
							int reply = 0;
							reply = JOptionPane.showConfirmDialog(null, "Would You Like to delete "+staffName_text[m].getText()+"?", "Confirm delete", JOptionPane.YES_NO_OPTION);
							
							if(dbValidate.connectDatabase(sessionData) && reply == JOptionPane.YES_OPTION){
								dbValidate.deleteStaffName(sessionData, staffNameSel, designationSel, contactSel, 
										stdSel, divSel, smsSel, academicYearClass);
								
								frame.setVisible(false);
								new CreateSMSStaff(sessionData, academicYearClass, section);
							}
						} catch (Exception e1) {
							commonObj.logException(e1);
						} finally {
							dbValidate.closeDatabase(sessionData);
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
        if(stdClass.equalsIgnoreCase("")){
        	height = 300;
        }
		jsp = new JScrollPane(dataPanel, 
				   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  
				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(30, bottomBandItemHeight + 10, screenWidth - 180, screenHeight - height);
		
        bottombandPanel.add(jsp, BorderLayout.SOUTH);
        ///////////////end order panel////////////
        
        designationCombo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
            	String designation = designationCombo.getSelectedItem().toString();
            	if(designationCategory.equalsIgnoreCase("Other")){
            		feeCatOtherText.setEditable(true);
            		feeCatOtherText.requestFocus();
            		feeCatOtherText.selectAll();
            	}
            	else{
            		feeCatOtherText.setEditable(false);
            	}
			}
        });
        
        ////addButton action///////////////////
        addButton.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
            int keyCode=e.getKeyCode();
            if(keyCode == 10){
            	String academic = academicYear_combo.getSelectedItem().toString();
            	String staffName = staffNameText.getText();
            	String designation = designationCombo.getSelectedItem().toString();
            	String contact = contactText.getText();
            	String std = stdCombo.getSelectedItem().toString();
            	String div = divCombo.getSelectedItem().toString();
            	String sms = smsCombo.getSelectedItem().toString();
            	addToStaff(sessionData, staffName, designation, contact, std, div, sms, academic);
              }
            }
        });
        
        addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String academic = academicYear_combo.getSelectedItem().toString();
				String staffName = staffNameText.getText();
            	String designation = designationCombo.getSelectedItem().toString();
            	String contact = contactText.getText();
            	String std = stdCombo.getSelectedItem().toString();
            	String div = divCombo.getSelectedItem().toString();
            	String sms = smsCombo.getSelectedItem().toString();	
            	addToStaff(sessionData, staffName, designation, contact, std, div, sms, academic);
			}
        });
        
        stdCombo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String std = stdCombo.getSelectedItem().toString();
				if(std.equalsIgnoreCase("All")){
					divCombo.setSelectedItem("All");
				}
			}
        });
        
        mainPanel.add(bottombandPanel, BorderLayout.SOUTH);
        panel.add(mainPanel, BorderLayout.EAST);
    }
    
    public static void addToStaff(SessionData sessionData, String staffName, String designation, String contact, 
    		String std, String div, String sms, String academic){
    	
    	boolean validateFields = true;
		try {
			if(staffName.trim().length() == 0) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Staff Name cannot be empty");
			}
			else if (commonObj.checkComma(staffName) || commonObj.validateSpecial(staffName)) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Staff Name can only contain alphabets or number");
			}
			else if(staffName.trim().length() > 100) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Staff Name cannot be greater than 100 characters");
			}
			else if(designation.trim().length() == 0) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Designation cannot be empty");
			}
			else if(designation.trim().length() > 100) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Designation cannot be greater than 100 characters");
			}
			else if(contact.trim().length() == 0) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Contact cannot be empty");
			}
			else if(contact.trim().length() > 12) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Contact be greater than 12 characters");
			}
			else if (!commonObj.validateOnlyNumber(contact)) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Please enter valid contact details");
			}
			
			if(validateFields){
				if(dbValidate.connectDatabase(sessionData)){
					boolean addFlag = dbValidate.addSMSStaff(sessionData, staffName.toUpperCase(), designation, contact, std, div, sms, academic, false);
					
					if(addFlag == true){
						frame.setVisible(false);
						new CreateSMSStaff(sessionData, academicYearClass, section);
					}
				}
				
			}
		} catch (Exception e1) {
			logger.info("Exception insert fee name ===>>>" + e1);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
	}
}
