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

public class CreateFeesHead extends JFrame {

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

    static Logger logger = Logger.getLogger(CreateFeesHead.class.getName());
    
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
    static String payFrequency = "";
    static String feeCategory = "";
    static String headerStr = "";
    static String selectStd = "";
    static String stdClass = "";
    static double totalAmount = 0;
    static String tableOrderStatus = "";
    static LinkedHashMap<String, LinkedHashMap<String, String>> feesHeadMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    static LinkedHashMap<String, LinkedHashMap<String, String>> headerMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    static LinkedHashMap<String, String> shortCategoryMap = new LinkedHashMap<String, String>();
    static String referenceNoClass = "";
    static int scrollHeight = 0;
    static String todayDate = "";
    static String saveStatus = "";
    
    public CreateFeesHead(SessionData sessionData1, String academicYear, String retStd, String sec) {

    	System.gc();
    	feesHeadMap.clear();
    	headerMap.clear();
    	headerStr = "Select";
    	saveStatus = "";
    	section = sec;
    	selectStd = "Select";
    	stdClass = retStd;
    	academicYearClass = academicYear;
    	sessionData = sessionData1;
        user_name = sessionData1.getUserName();
        user_role = sessionData1.getUserRole();
        setVisible(false);
        dispose();
        frame = new JFrame("Welcome to "+sessionData1.getAppName());
        std = bundle.getString(section.toUpperCase() + "_STD");
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
				feeCategory = dbValidate.getFeesCategoryList(sessionData1, academicYearClass, stdClass, section, "CATEGORY", "FEES_HEAD");
				if(!stdClass.isEmpty()){
					feesHeadMap = dbValidate.getFeesHeadData(sessionData1, academicYearClass, stdClass, section, "");
					scrollHeight = (feesHeadMap.size() - 6) * 30; // to adjust the perfect scroll height
				}
				
				Set setsc = feesHeadMap.entrySet();
				Iterator isc = setsc.iterator();
				while(isc.hasNext()) {
					Map.Entry me = (Map.Entry)isc.next();
					shortCategoryMap.put(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("category").toString(), 
							((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("short_name").toString());
				}
				
				headerMap = dbValidate.fetchHeaderMap(sessionData);
				Set setHeader = headerMap.entrySet();
				Iterator hMap = setHeader.iterator();
				while(hMap.hasNext()) {
					Map.Entry me = (Map.Entry)hMap.next();
					headerStr = headerStr +","+ me.getKey().toString();
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
		createFeeButton.setBackground(Color.GREEN);
		createFeeButton.setBounds(width, 50, 140, 24);
		topbandPanel.add(createFeeButton);

		createFeeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

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
				new SearchFeeStudentNew(sessionData, "", "", "", "", "", "", retStudentMap, section, "", "", "", "", false, "", "", false);
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
		feeReportButton.setBounds(width, 50, 170, 24);
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
        
        ///founder//////////////
        /*final ImageIcon iconfounder = new ImageIcon(img_path + img_founder);
        JLabel labelfounder = new JLabel(iconfounder);
        labelfounder.setBounds(mainCentre - 160, -30, 315, 417);
        if(show_founder.equalsIgnoreCase("true")){
        	tabHeight = tabHeight + 180;
        	bottombandPanel.add(labelfounder);
        }*/
        
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
        academicYear_combo.setBounds(170, 42, 120, 25);
        academicYear_combo.setEnabled(true);
        bottombandPanel.add(academicYear_combo);
        // //////////////////////////////////
        // /////////////Std//////////////
        JLabel std_label = new JLabel("Std :");
        std_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        std_label.setBounds(330, 30, 70, 50);
        bottombandPanel.add(std_label);

        std = selectStd + "," + std;
        String[] stdList = std.split(",");
        final JComboBox std_combo = new JComboBox(stdList);
        std_combo.setSelectedItem(stdClass);
        std_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        std_combo.setBounds(380, 42, 90, 25);
        std_combo.setEditable(false);
        bottombandPanel.add(std_combo);
        
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        submitButton.setBounds(500, 42, 90, 25);
        bottombandPanel.add(submitButton);
        
        bottomBandItemHeight = bottomBandItemHeight + 30;
        JSeparator jSeparator1  = new JSeparator();
        jSeparator1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        jSeparator1.setBounds(40, bottomBandItemHeight, (screenWidth - 200), 50);
        bottombandPanel.add(jSeparator1);
        
        submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String stdSel = std_combo.getSelectedItem().toString();
				String academicSel = academicYear_combo.getSelectedItem().toString();
				if(stdSel.equalsIgnoreCase("Select")){
					JOptionPane.showMessageDialog(null, "Please select STD");
				}
				else{
					frame.setVisible(false);
					new CreateFeesHead(sessionData, academicSel, stdSel, section);
				}
			}
		});
        
        ///////////////start Details for adding order////////////
        int itemWidth = 40;
        final JTextField feesNameText = new JTextField();
        String[] feeCategoryList = feeCategory.split(",");
        String[] headerList = headerStr.split(",");
        final JComboBox feesCatCombo = new JComboBox(feeCategoryList);
        final JComboBox receiptHeadCombo = new JComboBox(headerList);
        final JTextField feeCatOtherText = new JTextField("Other");
        String optionList[] = { "No", "Yes" };
        final JComboBox feesOptCombo = new JComboBox(optionList);
        String[] payFrequencyList = payFrequency.split(",");
        final JComboBox feesFreqCombo = new JComboBox(payFrequencyList);
        final JTextField feesAmtText = new JTextField();
        final JCheckBox allStdCheck = new JCheckBox();
        JButton addButton = new JButton("Add");
        
        bottomBandItemHeight = bottomBandItemHeight + 10;
        if(!stdClass.equalsIgnoreCase("")){
        	JLabel feesNameLabel = new JLabel("Fees Name : ");
            feesNameLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feesNameLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(feesNameLabel);
            
            itemWidth = itemWidth + 105;
            feesNameText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feesNameText.setBounds(itemWidth, bottomBandItemHeight + 10, 200, 25);
            bottombandPanel.add(feesNameText);
            
            itemWidth = itemWidth + 220;
            JLabel feesCatLabel = new JLabel("Fees Category : ");
            feesCatLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feesCatLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(feesCatLabel);
            
            itemWidth = itemWidth + 130;
            feesCatCombo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feesCatCombo.setBounds(itemWidth, bottomBandItemHeight + 10, 220, 25);
            bottombandPanel.add(feesCatCombo);
            
            itemWidth = itemWidth + 240;
            feeCatOtherText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feeCatOtherText.setBounds(itemWidth, bottomBandItemHeight + 10, 220, 25);
            feeCatOtherText.setEditable(false);
            bottombandPanel.add(feeCatOtherText);
            
            itemWidth = itemWidth + 280;
            
            JRadioButton shortName_radio = new JRadioButton();
            shortName_radio.setBounds(itemWidth-30, bottomBandItemHeight + 13, 20, 20);
            shortName_radio.setSelected(true);
            shortName_radio.setEnabled(false);
            bottombandPanel.add(shortName_radio);
    		
            JLabel shotNameLabel = new JLabel("(Receipt Head Short Name)");
            shotNameLabel.setFont(new Font("Book Antiqua", Font.BOLD, 12));
            shotNameLabel.setBounds(itemWidth, bottomBandItemHeight-25, 200, 50);
            bottombandPanel.add(shotNameLabel);
            
            receiptHeadCombo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            receiptHeadCombo.setBounds(itemWidth, bottomBandItemHeight + 10, 150, 25);
            receiptHeadCombo.setSelectedItem(shortCategoryMap.get(feesCatCombo.getSelectedItem()));
            bottombandPanel.add(receiptHeadCombo);
            
            itemWidth = 40;
            bottomBandItemHeight = bottomBandItemHeight + 40;
            JLabel feesFreqLabel = new JLabel("Payment Frequency : ");
            feesFreqLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feesFreqLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(feesFreqLabel);
            
            itemWidth = itemWidth + 170;
            feesFreqCombo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feesFreqCombo.setBounds(itemWidth, bottomBandItemHeight + 10, 150, 25);
            bottombandPanel.add(feesFreqCombo);
            
            itemWidth = itemWidth + 170;
            JLabel feesOptionLabel = new JLabel("Optional : ");
            feesOptionLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feesOptionLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(feesOptionLabel);
            
            itemWidth = itemWidth + 90;
            feesOptCombo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feesOptCombo.setBounds(itemWidth, bottomBandItemHeight + 10, 100, 25);
            bottombandPanel.add(feesOptCombo);
            
            itemWidth = itemWidth + 170;
            JLabel feesAmtLabel = new JLabel("Yearly Amount : ");
            feesAmtLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feesAmtLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(feesAmtLabel);
            
            itemWidth = itemWidth + 130;
            feesAmtText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feesAmtText.setBounds(itemWidth, bottomBandItemHeight + 10, 70, 25);
            bottombandPanel.add(feesAmtText);
            
            itemWidth = itemWidth + 90;
            JLabel allStdLabel = new JLabel("All Std : ");
            allStdLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            allStdLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(allStdLabel);
            
            itemWidth = itemWidth + 70;
            allStdCheck.setBorder(null);
            allStdCheck.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            allStdCheck.setBounds(itemWidth, bottomBandItemHeight + 15, 13, 13);
            bottombandPanel.add(allStdCheck);
    		
            itemWidth = itemWidth + 70;
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
        
        itemWidth = itemWidth + 70;
        JLabel itemCode = new JLabel("Fee Name");
        itemCode.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        itemCode.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(itemCode);
        
        itemWidth = itemWidth + 220;
        JLabel itemName = new JLabel("Category");
        itemName.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        itemName.setBounds(itemWidth, bottomBandItemHeight, 600, 50);
        bottombandPanel.add(itemName);
        
        itemWidth = itemWidth + 160;
        JLabel qReady = new JLabel("Optional");
        qReady.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        qReady.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(qReady);
        
        itemWidth = itemWidth + 90;
        JLabel quantity = new JLabel("Frequency");
        quantity.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        quantity.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(quantity);
        
        itemWidth = itemWidth + 120;
        JLabel rate = new JLabel("Amount");
        rate.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        rate.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(rate);
        
        itemWidth = itemWidth + 80;
        JLabel shortNameLabel = new JLabel("Receipt Short Name");
        shortNameLabel.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        shortNameLabel.setBounds(itemWidth, bottomBandItemHeight, 150, 50);
        bottombandPanel.add(shortNameLabel);
        
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
        
        if (!feesHeadMap.isEmpty()) {
	        final JLabel[] srNo_labels = new JLabel[feesHeadMap.size()];
	        final JLabel[] orderNo_labels = new JLabel[feesHeadMap.size()];
	        final JTextField[] feeName_text = new JTextField[feesHeadMap.size()];
	        final JComboBox[] category_combo = new JComboBox[feesHeadMap.size()];
	        final JComboBox[] optional_combo = new JComboBox[feesHeadMap.size()];
	        final JComboBox[] frequency_combo = new JComboBox[feesHeadMap.size()];
	        final JComboBox[] shortName_combo = new JComboBox[feesHeadMap.size()];
	        final JTextField[] amount_text = new JTextField[feesHeadMap.size()];
	        final JButton[] edit_buttons = new JButton[feesHeadMap.size()];
	        final JButton[] save_buttons = new JButton[feesHeadMap.size()];
	        final JButton[] delete_buttons = new JButton[feesHeadMap.size()];
	        final JSeparator[] jSeparators  = new JSeparator[feesHeadMap.size()];
	        String shortName = "";
	        String[] feeCategoryList2 = feeCategory.substring(0,feeCategory.lastIndexOf(",")).split(",");
	        String feeName = "";
	        
	        int k = 0;
	        int j = 5;
	        Set set = feesHeadMap.entrySet();
			Iterator i = set.iterator();
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
				
				itemWidth = 20;
	        	srNo_labels[k] = new JLabel((k+1)+"");
	        	srNo_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	        	srNo_labels[k].setBounds(itemWidth, j, 120, 25);
	        	dataPanel.add(srNo_labels[k]);
	        	
	        	orderNo_labels[k] = new JLabel(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("order_no").toString());
	        	dataPanel.add(orderNo_labels[k]);
	    		
	        	itemWidth = itemWidth + 50;
	        	feeName = commonObj.revertCommaApostrophy(me.getKey().toString().replace("$$", "."));
	        	feeName_text[k] = new JTextField(feeName);
	        	feeName_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	        	feeName_text[k].setToolTipText(feeName);
	        	feeName_text[k].setBounds(itemWidth, j, 200, 25);
	        	feeName_text[k].setEditable(false);
	    		dataPanel.add(feeName_text[k]);
	    		
	    		itemWidth = itemWidth + 220;
	    		category_combo[k] = new JComboBox(feeCategoryList2);
	    		category_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("category").toString());
	    		category_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		category_combo[k].setBounds(itemWidth, j, 150, 25);
	    		dataPanel.add(category_combo[k]);
	    		
	    		itemWidth = itemWidth + 170;
	    		optional_combo[k] = new JComboBox(optionList);
	    		optional_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("optional").toString());
	    		optional_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		optional_combo[k].setBounds(itemWidth, j, 70, 25);
	    		dataPanel.add(optional_combo[k]);
	    		
	    		itemWidth = itemWidth + 80;
	    		frequency_combo[k] = new JComboBox(payFrequencyList);
	    		frequency_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("frequency").toString());
	    		frequency_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		frequency_combo[k].setBounds(itemWidth, j, 120, 25);
	    		dataPanel.add(frequency_combo[k]);
	    		
	    		itemWidth = itemWidth + 130;
	    		amount_text[k] = new JTextField(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("amount").toString());
	    		amount_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		amount_text[k].setBounds(itemWidth, j, 70, 25);
	    		amount_text[k].setEditable(false);
	    		dataPanel.add(amount_text[k]);
	    		
	    		itemWidth = itemWidth + 80;
	    		shortName = ((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("short_name").toString();
	    		shortName_combo[k] = new JComboBox(headerList);
	    		shortName_combo[k].setSelectedItem(shortName);
	    		shortName_combo[k].setToolTipText(shortName);
	    		shortName_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		shortName_combo[k].setBounds(itemWidth, j, 130, 25);
	    		dataPanel.add(shortName_combo[k]);
	    		
	    		itemWidth = itemWidth + 150;
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
	            
	            category_combo[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							String receiptHead = shortName_combo[m].getSelectedItem().toString();
			            	String feeCategory = category_combo[m].getSelectedItem().toString();
			            	
							if(saveStatus.equalsIgnoreCase("edit")) {
				            	if(shortCategoryMap.get(feeCategory) != null && !shortCategoryMap.get(feeCategory).equalsIgnoreCase("")) {
				            		shortName_combo[m].setSelectedItem(shortCategoryMap.get(feeCategory));
				            	}
				            	else {
				            		shortName_combo[m].setSelectedItem("Select");
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
	            
	            shortName_combo[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							String receiptHead = shortName_combo[m].getSelectedItem().toString();
			            	String feeCategory = category_combo[m].getSelectedItem().toString();
			            	
							if(saveStatus.equalsIgnoreCase("edit")) {
				            	if(!receiptHead.equalsIgnoreCase("Select")) {
				            		if(!shortCategoryMap.get(feeCategory).equalsIgnoreCase(receiptHead)) {
				            			int reply = 0;
				        				reply = JOptionPane.showConfirmDialog(null, "Would You Like to change \n Receipt head "+receiptHead+" for all "+feeCategory+" Fess Category for Std "+stdClass+"?", "Confirm validate", JOptionPane.YES_NO_OPTION);
				        				
				        				if(reply != JOptionPane.YES_OPTION){
				        					shortName_combo[m].setSelectedItem(shortCategoryMap.get(feeCategory));
				        				}
				            		}
				            	}
							}
							else {
								shortName_combo[m].setSelectedItem(shortCategoryMap.get(feeCategory));
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
							String optional = optional_combo[m].getSelectedItem().toString();
							String feesHead = feeName_text[m].getText();
							
							if(dbValidate.connectDatabase(sessionData)){
								isEditable = dbValidate.isFeesEditable(sessionData, academicYearClass, stdClass, section, optional, feesHead);
							}
							
							if(isEditable){
								feeName_text[m].setEditable(true);
								amount_text[m].setEditable(true);
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
	    		
	    		/*optional_combo[k].addActionListener(new ActionListener() {

	    			public void actionPerformed(ActionEvent e) {
	                	String optionalSel = optional_combo[m].getSelectedItem().toString();
	                	String payFrequency = frequency_combo[m].getSelectedItem().toString();
	                	
	                	if(optionalSel.equalsIgnoreCase("Yes")){
	                		frequency_combo[m].setSelectedItem("Occasionally");
	                	}
	                	else if(payFrequency.equalsIgnoreCase("Occasionally")){
	                		frequency_combo[m].setSelectedItem("Monthly");
	                	}
	    			}
	            });*/
	    		
	    		/*frequency_combo[k].addActionListener(new ActionListener() {

	    			public void actionPerformed(ActionEvent e) {
	    				String payFrequency = frequency_combo[m].getSelectedItem().toString();
	                	if(payFrequency.equalsIgnoreCase("Occasionally")){
	                		optional_combo[m].setSelectedItem("Yes");
	                	}
	                	else{
	                		optional_combo[m].setSelectedItem("No");
	                	}
	    			}
	            });*/
	    		
	    		save_buttons[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							
							String orderNoSel = orderNo_labels[m].getText();
							String feeNameSel = feeName_text[m].getText().replace(".", "$$");
							feeNameSel = commonObj.replaceCommaApostrophy(feeNameSel);
							String categorySel = category_combo[m].getSelectedItem().toString();
							String optionalSel = optional_combo[m].getSelectedItem().toString();
							String frequencySel = frequency_combo[m].getSelectedItem().toString();
							String shortNameSel = shortName_combo[m].getSelectedItem().toString();
							String amountSel = amount_text[m].getText();
							
							if(dbValidate.connectDatabase(sessionData)){
								dbValidate.addFeeName(sessionData, feeNameSel, categorySel, "", optionalSel, frequencySel, amountSel, 
										true, stdClass, academicYearClass, section, orderNoSel, false, "", shortNameSel);
							}
							
							feeName_text[m].setEditable(false);
							amount_text[m].setEditable(false);
							edit_buttons[m].setEnabled(true);
							save_buttons[m].setEnabled(false);
							saveStatus = "save";
							
							frame.setVisible(false);
							new CreateFeesHead(sessionData, academicYearClass, stdClass, section);
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
							String optional = optional_combo[m].getSelectedItem().toString();
							String feesHead = feeName_text[m].getText();
							
							if(dbValidate.connectDatabase(sessionData)){
								isEditable = dbValidate.isFeesEditable(sessionData, academicYearClass, stdClass, section, optional, feesHead);
							}
							
							if(isEditable){
								int reply = 0;
								reply = JOptionPane.showConfirmDialog(null, "Would You Like to delete "+feeName_text[m].getText()+"?", "Confirm validate", JOptionPane.YES_NO_OPTION);
								
								if(dbValidate.connectDatabase(sessionData) && reply == JOptionPane.YES_OPTION){
									dbValidate.deleteFeeName(sessionData, orderNo_labels[m].getText());
									
									frame.setVisible(false);
									new CreateFeesHead(sessionData, academicYearClass, stdClass, section);
								}
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
        
        feesCatCombo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
            	String feeCategory = feesCatCombo.getSelectedItem().toString();
            	if(feeCategory.equalsIgnoreCase("Other")){
            		feeCatOtherText.setEditable(true);
            		feeCatOtherText.requestFocus();
            		feeCatOtherText.selectAll();
            	}
            	else{
            		feeCatOtherText.setEditable(false);
            	}
            	
            	if(shortCategoryMap.get(feeCategory) != null && !shortCategoryMap.get(feeCategory).equalsIgnoreCase("")) {
            		receiptHeadCombo.setSelectedItem(shortCategoryMap.get(feeCategory));
            	}
            	else {
            		receiptHeadCombo.setSelectedItem("Select");
            	}
			}
        });
        
        receiptHeadCombo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
            	String receiptHead = receiptHeadCombo.getSelectedItem().toString();
            	String feeCategory = feesCatCombo.getSelectedItem().toString();
            	boolean allStd = allStdCheck.isSelected();
            	String stdSel = stdClass;
            	if(allStd) {
            		stdSel = "All";
            	}
            	
            	if(!receiptHead.equalsIgnoreCase("Select")) {
            		if(shortCategoryMap.get(feeCategory) == null || !shortCategoryMap.get(feeCategory).equalsIgnoreCase(receiptHead)) {
            			int reply = 0;
        				reply = JOptionPane.showConfirmDialog(null, "Would You Like to change \n Receipt head "+receiptHead+" for all "+feeCategory+" Fess Category for Std "+stdSel+"?", "Confirm validate", JOptionPane.YES_NO_OPTION);
        				
        				if(reply != JOptionPane.YES_OPTION){
        					receiptHeadCombo.setSelectedItem(shortCategoryMap.get(feeCategory));
        				}
            		}
            	}
			}
        });
        
        ////addButton action///////////////////
        addButton.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
            int keyCode=e.getKeyCode();
            if(keyCode == 10){
            	String feeName = feesNameText.getText();
            	String feeCategory = feesCatCombo.getSelectedItem().toString();
            	String feeCatOther = feeCatOtherText.getText();
            	String optional = feesOptCombo.getSelectedItem().toString();
            	String payFrequency = feesFreqCombo.getSelectedItem().toString();
            	String shortName = receiptHeadCombo.getSelectedItem().toString();
            	String amount = feesAmtText.getText();
            	boolean isAllSelected = allStdCheck.isSelected();
				addToOrder(sessionData, feeName, feeCategory, feeCatOther, optional, payFrequency, amount, isAllSelected, shortName);
              }
            }
        });
        
        /*feesOptCombo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
            	String optionalSel = feesOptCombo.getSelectedItem().toString();
            	String frequencySel = feesFreqCombo.getSelectedItem().toString();
            	if(optionalSel.equalsIgnoreCase("Yes")){
            		feesFreqCombo.setSelectedItem("Occasionally");
            	}
            	else if(frequencySel.equalsIgnoreCase("Occasionally")){
            		feesFreqCombo.setSelectedItem("Monthly");
            	}
			}
        });*/
        
        /*feesFreqCombo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
            	String payFrequency = feesFreqCombo.getSelectedItem().toString();
            	if(payFrequency.equalsIgnoreCase("Occasionally")){
            		feesOptCombo.setSelectedItem("Yes");
            	}
            	else{
            		feesOptCombo.setSelectedItem("No");
            	}
			}
        });*/
        
        addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String feeName = feesNameText.getText();
            	String feeCategory = feesCatCombo.getSelectedItem().toString();
            	String feeCatOther = feeCatOtherText.getText();
            	String optional = feesOptCombo.getSelectedItem().toString();
            	String payFrequency = feesFreqCombo.getSelectedItem().toString();
            	String amount = feesAmtText.getText();
            	String shortName = receiptHeadCombo.getSelectedItem() == null ? "" : receiptHeadCombo.getSelectedItem().toString();
            	boolean isAllSelected = allStdCheck.isSelected();
				addToOrder(sessionData, feeName, feeCategory, feeCatOther, optional, payFrequency, amount, isAllSelected, shortName);
			}
        });
        
        mainPanel.add(bottombandPanel, BorderLayout.SOUTH);
        panel.add(mainPanel, BorderLayout.EAST);
    }
    
    public static void addToOrder(SessionData sessionData, String feeName, String feeCategory, String feeCatOther, 
    		String optional, String payFrequency, String amount, boolean isAllSelected, String shortName){
    	
    	boolean validateFields = true;
    	feeName = feeName.replace(".", "$$");
		try {
			if(feeName.trim().length() == 0) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Fee Name cannot be empty");
			}
			else if(commonObj.checkComma(feeName)) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Fee Name cannot contain |-:';,");
			}
			else if(feeName.trim().length() > 100) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Fee Name cannot be greater than 100 characters");
			}
			else if(feeCategory.equalsIgnoreCase("Other") && feeCatOther.trim().length() == 0) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Fee Category text cannot be empty");
			}
			else if(commonObj.checkComma(feeCatOther)) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Fee Category cannot contain |-:';,");
			}
			else if(feeCatOther.trim().length() > 100) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Fee Category cannot be greater than 100 characters");
			}
			else if(amount.trim().length() == 0) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Amount cannot be empty");
			}
			else if(!commonObj.validateNumber((amount))) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Please enter valid Amount");
			}
			
			if(validateFields){
				if(dbValidate.connectDatabase(sessionData)){
					boolean addFlag = dbValidate.addFeeName(sessionData, feeName, feeCategory, feeCatOther, optional, payFrequency, amount, 
							false, stdClass, academicYearClass, section, "", isAllSelected, std, shortName);
					
					if(addFlag == true){
						frame.setVisible(false);
						new CreateFeesHead(sessionData, academicYearClass, stdClass, section);
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
