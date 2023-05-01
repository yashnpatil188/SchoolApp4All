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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class CreateSMSTemplate extends JFrame {

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

    static Logger logger = Logger.getLogger(CreateSMSTemplate.class.getName());
    
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
    static LinkedHashMap<String, LinkedHashMap<String, String>> smsTemplateDetailMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    static LinkedHashMap<String, LinkedHashMap<String, String>> headerMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    static LinkedHashMap<String, String> shortCategoryMap = new LinkedHashMap<String, String>();
    static String referenceNoClass = "";
    static int scrollHeight = 0;
    static String todayDate = "";
    static String saveStatus = "", senderName = "";
    
    public CreateSMSTemplate(SessionData sessionData1, String academicYear, String sec) {

    	System.gc();
    	smsTemplateDetailMap.clear();
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
        senderName = bundle.getString("SMS_"+sessionData.getAppType()+"_SENDER");
        todayDate = commonObj.getCurrentDate();
        
        if(academicYearClass.trim().equalsIgnoreCase("")){
            academicYearClass = commonObj.getAcademicYear(todayDate);
        }
        
        try {
			if(dbValidate.connectDatabase(sessionData)){
				smsTemplateDetailMap = dbValidate.getSMSTemplateData(sessionData1);
				scrollHeight = (smsTemplateDetailMap.size() - 6) * 30; // to adjust the perfect scroll height
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
		staffEntryButton.setBounds(width, 50, 140, 24);
		topbandPanel.add(staffEntryButton);

		staffEntryButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new CreateSMSStaff(sessionData, "", section);

			}
		});
		
		width = width + 150;
		JButton smsTemplateButton = new JButton("SMS Template");
		smsTemplateButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		smsTemplateButton.setBackground(Color.GREEN);
		smsTemplateButton.setBounds(width, 50, 170, 24);
		topbandPanel.add(smsTemplateButton);

		smsTemplateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new CreateSMSTemplate(sessionData, academicYearClass, section);

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
        
        int bottomBandItemHeight = 40;
        
        ///////////////start Details for adding order////////////
        int itemWidth = 40;
        std = std + ",All";
        div = div + ",All";
        String[] stdList = std.split(",");
        String[] divList = div.split(",");
        
        String[] designationCategoryList = designationCategory.split(",");
        final JTextField feeCatOtherText = new JTextField("Other");
        String smsOptionList[] = { "Enable", "Disable" };
        JButton addButton = new JButton("Add");
        
    	JLabel senderNameLabel = new JLabel("Sender Name : ");
    	senderNameLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
    	senderNameLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
        bottombandPanel.add(senderNameLabel);
        
        itemWidth = itemWidth + 120;
        JLabel senderNameText = new JLabel(senderName);
        senderNameText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        senderNameText.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
        bottombandPanel.add(senderNameText);
        
        itemWidth = itemWidth + 150;
        JLabel templateIdLabel = new JLabel("Template Id : ");
        templateIdLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        templateIdLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
        bottombandPanel.add(templateIdLabel);
        
        itemWidth = itemWidth + 120;
        final JTextField templateIdText = new JTextField();
        templateIdText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        templateIdText.setBounds(itemWidth, bottomBandItemHeight + 10, 240, 25);
        bottombandPanel.add(templateIdText);
        
        itemWidth = itemWidth + 260;
        JLabel templateNameLabel = new JLabel("Template Name : ");
        templateNameLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        templateNameLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
        bottombandPanel.add(templateNameLabel);
        
        itemWidth = itemWidth + 150;
        final JTextField templateNameText = new JTextField();
        templateNameText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        templateNameText.setBounds(itemWidth, bottomBandItemHeight + 10, 240, 25);
        bottombandPanel.add(templateNameText);
		
        itemWidth = 40;
        bottomBandItemHeight = bottomBandItemHeight + 40;
        JLabel messageBodyLabel = new JLabel("Message Body : ");
        messageBodyLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        messageBodyLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
        bottombandPanel.add(messageBodyLabel);
        
        itemWidth = itemWidth + 140;
        final JTextArea messageBodyText = new JTextArea();
        messageBodyText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        messageBodyText.setLineWrap(true);
        messageBodyText.setWrapStyleWord(true);
        
        //messageBodyText scroll area
        JScrollPane scroll = new JScrollPane(messageBodyText);
        scroll.setBounds(itemWidth, bottomBandItemHeight + 10, 800, 80);  
        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        bottombandPanel.add(scroll);
        
        itemWidth = itemWidth + 800;
		addButton.setFont(new Font("Book Antiqua", Font.BOLD, 25));
		addButton.setBounds(itemWidth, bottomBandItemHeight+10, 100, 25);
        bottombandPanel.add(addButton);
        
        bottomBandItemHeight = bottomBandItemHeight + 100;
        JSeparator jSeparator2  = new JSeparator();
        jSeparator2.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        jSeparator2.setBounds(40, bottomBandItemHeight, (screenWidth - 200), 50);
        bottombandPanel.add(jSeparator2);

        
        /////start order headers////////////////
        itemWidth = 50;
        JLabel senderLabel = new JLabel("Sender Name");
        senderLabel.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        senderLabel.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(senderLabel);
        
        itemWidth = itemWidth + 160;
        JLabel tempIdLabel = new JLabel("Template ID");
        tempIdLabel.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        tempIdLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
        bottombandPanel.add(tempIdLabel);
        
        itemWidth = itemWidth + 220;
        JLabel tempName = new JLabel("Template Name");
        tempName.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        tempName.setBounds(itemWidth, bottomBandItemHeight, 600, 50);
        bottombandPanel.add(tempName);
        
        itemWidth = itemWidth + 220;
        JLabel messageLabel = new JLabel("Message Body");
        messageLabel.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        messageLabel.setBounds(itemWidth, bottomBandItemHeight, 150, 50);
        bottombandPanel.add(messageLabel);
        
//        itemWidth = itemWidth + 500;
//        JLabel statusLabel = new JLabel("Template Enabled");
//        statusLabel.setFont(new Font("Book Antiqua", Font.BOLD, 14));
//        statusLabel.setBounds(itemWidth, bottomBandItemHeight, 150, 50);
//        bottombandPanel.add(statusLabel);
        
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
        
        if (!smsTemplateDetailMap.isEmpty()) {
	        final JLabel[] sender_labels = new JLabel[smsTemplateDetailMap.size()];
	        final JLabel[] tempId_labels = new JLabel[smsTemplateDetailMap.size()];
	        final JLabel[] tempName_labels = new JLabel[smsTemplateDetailMap.size()];
	        final JTextArea[] messageBodyArea = new JTextArea[smsTemplateDetailMap.size()];
	        final JScrollPane[] scrollText = new JScrollPane[smsTemplateDetailMap.size()];
	        final JTextField[] contact_text = new JTextField[smsTemplateDetailMap.size()];
	        final JButton[] edit_buttons = new JButton[smsTemplateDetailMap.size()];
	        final JButton[] save_buttons = new JButton[smsTemplateDetailMap.size()];
	        final JButton[] delete_buttons = new JButton[smsTemplateDetailMap.size()];
	        final JSeparator[] jSeparators  = new JSeparator[smsTemplateDetailMap.size()];
	        String shortName = "";
//	        String[] designationCategoryList2 = designationCategory.substring(0,designationCategory.lastIndexOf(",")).split(",");
	        String staffName = "", contact = "";
	        
	        int k = 0;
	        int j = 5;
	        Set set = smsTemplateDetailMap.entrySet();
			Iterator i = set.iterator();
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
				
				itemWidth = 20;
				sender_labels[k] = new JLabel(((LinkedHashMap<?, ?>) smsTemplateDetailMap.get(me.getKey())).get("senderName").toString());
				sender_labels[k].setFont(new Font("Book Antiqua", Font.PLAIN, 16));
				sender_labels[k].setBounds(itemWidth, j, 200, 25);
	        	dataPanel.add(sender_labels[k]);
	        	
	        	itemWidth = itemWidth + 160;
	        	tempId_labels[k] = new JLabel(((LinkedHashMap<?, ?>) smsTemplateDetailMap.get(me.getKey())).get("templateId").toString());
	        	tempId_labels[k].setFont(new Font("Book Antiqua", Font.PLAIN, 16));
	        	tempId_labels[k].setBounds(itemWidth, j, 200, 25);
	        	dataPanel.add(tempId_labels[k]);
	        	
	        	itemWidth = itemWidth + 220;
	        	tempName_labels[k] = new JLabel(((LinkedHashMap<?, ?>) smsTemplateDetailMap.get(me.getKey())).get("templateName").toString());
	        	tempName_labels[k].setFont(new Font("Book Antiqua", Font.PLAIN, 16));
	        	tempName_labels[k].setBounds(itemWidth, j, 200, 25);
	        	dataPanel.add(tempName_labels[k]);
	        	
	        	itemWidth = itemWidth + 220;
	        	messageBodyArea[k] = new JTextArea(((LinkedHashMap<?, ?>) smsTemplateDetailMap.get(me.getKey())).get("messageBody").toString());
	        	messageBodyArea[k].setFont(new Font("Book Antiqua", Font.PLAIN, 16));
	        	messageBodyArea[k].setLineWrap(true);
	        	messageBodyArea[k].setWrapStyleWord(true);
	        	messageBodyArea[k].enable(false);
	        	
	        	//messageBodyText scroll area
	            scrollText[k] = new JScrollPane(messageBodyArea[k]);
	            scrollText[k].setBounds(itemWidth, j, 300, 50);  
	            scrollText[k].setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	            dataPanel.add(scrollText[k]);
	            
	    		itemWidth = itemWidth + 330;
	    		delete_buttons[k] = new JButton("Delete");
	    		delete_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		delete_buttons[k].setBounds(itemWidth, j, 90, 25);
	    		dataPanel.add(delete_buttons[k]);
	    		
	    		jSeparators[k] = new JSeparator();
	    		jSeparators[k].setFont(new Font("Book Antiqua", Font.BOLD, 18));
	    		jSeparators[k].setBounds(10, j + 50, (screenWidth - 220), 50);
	    		dataPanel.add(jSeparators[k]);
	    		
	            final int m = k;
	            
//	    		save_buttons[k].addActionListener(new ActionListener() {
//
//					public void actionPerformed(ActionEvent e) {
//						try {
//							
//							String orderNoSel = tempId_labels[m].getText();
//							String staffNameSel = staffName_text[m].getText().trim();
//							String designationSel = designation_combo[m].getSelectedItem().toString();
//							String smsSel = sms_combo[m].getSelectedItem().toString();
//							String contactSel = contact_text[m].getText();
//							String divSel = div_combo[m].getSelectedItem().toString();
//							String stdSel = std_combo[m].getSelectedItem().toString();
//							
//							if(dbValidate.connectDatabase(sessionData)){
//								dbValidate.addSMSStaff(sessionData, staffNameSel, designationSel, contactSel, 
//										stdSel, divSel, smsSel, academicYearClass, true);
//							}
//							
//							staffName_text[m].setEditable(false);
////							std_combo[m].setEditable(false);
//							edit_buttons[m].setEnabled(true);
//							save_buttons[m].setEnabled(false);
//							saveStatus = "save";
//							
//							frame.setVisible(false);
//							new CreateSMSTemplate(sessionData, academicYearClass, section);
//						} catch (Exception e1) {
//							logger.info("Exception insert fee name ===>>>" + e1);
//						} finally {
//							dbValidate.closeDatabase(sessionData);
//						}
//					}
//				});
//	    		
	    		delete_buttons[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						///delete fee name/////
						try {
							boolean isEditable = true;
							String sender = sender_labels[m].getText();
							String tempId = tempId_labels[m].getText();
							String tempName = tempName_labels[m].getText();
							String messageBody = messageBodyArea[m].getText();
							
							int reply = 0;
							reply = JOptionPane.showConfirmDialog(null, "Would You Like to delete "+messageBody+"?", "Confirm delete", JOptionPane.YES_NO_OPTION);
							
							sender = commonObj.replaceCommaApostrophy(sender);
							tempId = commonObj.replaceCommaApostrophy(tempId);
							tempName = commonObj.replaceCommaApostrophy(tempName);
							messageBody = commonObj.replaceCommaApostrophy(messageBody);
							
							
							if(dbValidate.connectDatabase(sessionData) && reply == JOptionPane.YES_OPTION){
								dbValidate.deleteSmsTemplate(sessionData, sender, tempId, tempName, messageBody);
								
								frame.setVisible(false);
								new CreateSMSTemplate(sessionData, academicYearClass, section);
							}
						} catch (Exception e1) {
							commonObj.logException(e1);
						} finally {
							dbValidate.closeDatabase(sessionData);
						}
					}
				});
	    		
	    		j = j + 60;
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
        
        ////addButton action///////////////////
        addButton.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
            int keyCode=e.getKeyCode();
            if(keyCode == 10){
            	String senderName = senderNameText.getText();
            	String templateId = templateIdText.getText();
            	String templateName = templateNameText.getText();
            	String messageBody = messageBodyText.getText();
            	addToSmsTemplate(sessionData, senderName, templateId, templateName, messageBody);
              }
            }
        });
        
        addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String senderName = senderNameText.getText();
            	String templateId = templateIdText.getText();
            	String templateName = templateNameText.getText();
            	String messageBody = messageBodyText.getText();
            	addToSmsTemplate(sessionData, senderName, templateId, templateName, messageBody);
			}
        });
        
        mainPanel.add(bottombandPanel, BorderLayout.SOUTH);
        panel.add(mainPanel, BorderLayout.EAST);
    }
    
    public static void addToSmsTemplate(SessionData sessionData, String senderName, String templateId, String templateName, 
    		String messageBody){
    	
    	boolean validateFields = true;
		try {
			if(senderName.trim().length() <= 0 || templateId.trim().length() <= 0 || 
					templateName.trim().length() <= 0 || messageBody.trim().length() <= 0) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "All fields are mandatory");
			}
			else if(senderName.trim().length() > 20) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Sender Name should not be more than 20 characters");
			}
			else if(templateId.trim().length() > 50) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Template Id should not be more than 50 characters");
			}
			else if(templateName.trim().length() > 50) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Template name should not be more than 50 characters");
			}
			else if(messageBody.trim().length() > 500) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Message body should not be more than 500 characters");
			}
			
			if(validateFields){
				if(dbValidate.connectDatabase(sessionData)){
					boolean addFlag = dbValidate.addSMSTemplate(sessionData, senderName, templateId, templateName, messageBody, false);
					
					if(addFlag == true){
						frame.setVisible(false);
						new CreateSMSTemplate(sessionData, academicYearClass, section);
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
