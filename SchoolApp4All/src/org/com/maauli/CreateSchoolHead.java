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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class CreateSchoolHead extends JFrame {
	
	private static CreateSchoolHead createSchoolHead = new CreateSchoolHead();

	private static final long serialVersionUID = 1L;

	static int screenWidth;

    static int screenHeight;

    static int mainCentre;

    static JFrame frame = null;
    
    static String secName = "";

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
    
    final static String createUser  = "Create New User";

    static DBValidate dbValidate = new DBValidate();
    
    static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

    static Logger logger = Logger.getLogger(CreateSchoolHead.class.getName());
    
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
    static int scrollHeight = 0;
    static String todayDate = "";
    
    /*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private CreateSchoolHead() {
	}

	/* Static 'instance' method */
	public static CreateSchoolHead getInstance() {
		return createSchoolHead;
	}
	
	/* Other methods protected by singleton-ness */
	protected static void getCreateSchoolHead(SessionData sessionData1, final String academic) {

    	System.gc();
    	sessionData = sessionData1;
        user_name = sessionData1.getUserName();
        user_role = sessionData1.getUserRole();
//        frame.setVisible(false);
//        frame.dispose();
        frame = new JFrame("Welcome to "+sessionData1.getAppName());
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
        secName = bundle.getString(sessionData.getSectionName().toUpperCase() + "_SEC");
        
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
        LinkedHashMap<String, LinkedHashMap<String, String>> headerMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
        
        try {
			if(dbValidate.connectDatabase(sessionData)){
//				schoolHeadMap = dbValidate.getSchoolHeadData(sessionData1);
				headerMap = dbValidate.fetchHeaderMap(sessionData);
				scrollHeight = (headerMap.size() - 6) * 30; // to adjust the perfect scroll height
			}
		} catch (Exception e1) {
			commonObj.logException(e1);
        }
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screenWidth = commonObj.screeWidth();
        screenHeight = commonObj.screeHeight();
        mainCentre = (screenWidth - 150) / 2;

        frame.setSize(screenWidth, screenHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
		
		// Added for MAC ---> Function to set visible status of JFrame.
        if((System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
        	panel.setVisible(true);
        }
        
        frame.add(panel);
//        placeComponents(panel);
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
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role,
								sessionData.getSectionName());
						dbValidate.closeDatabase(sessionData);

						frame.setVisible(false);
						String[] arguments = new String[] { "" };
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
        
        int p = 50;
        JButton studentButton = new JButton("Student");
        studentButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        studentButton.setBounds(10, p + 50, 130, 35);
        leftPanel.add(studentButton);
        
        studentButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                new Student(sessionData, sessionData.getSectionName(), user_name, user_role);
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
	                new Staff(sessionData, sessionData.getSectionName(), user_name, user_role);
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
                new Academic(sessionData, sessionData.getSectionName(), user_name, user_role);
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
	                new CreateSubject(sessionData, "", "", sessionData.getSectionName(), user_name, user_role, "");
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
                new FindStudent(sessionData, "", "", "", "", "", "", studMap, sessionData.getSectionName(),
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
							"", sessionData.getSectionName(), "", user_name, user_role,"","","");
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
					new CreateFeesHead(sessionData, "", "", sessionData.getSectionName());
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
		schoolHeadButton.setBackground(Color.GREEN);
		schoolHeadButton.setBounds(width, 50, 140, 24);
		topbandPanel.add(schoolHeadButton);

		schoolHeadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				CreateSchoolHead.getCreateSchoolHead(sessionData, academic);
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
        
        int bottomBandItemHeight = 30;
        int itemWidth = 40;
        
        // /////////////ACADEMIV YEAR //////////////
        JLabel institute_label = new JLabel("Institute Name   :");
        institute_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        institute_label.setBounds(itemWidth, bottomBandItemHeight, 150, 50);
        bottombandPanel.add(institute_label);

        itemWidth = itemWidth + 130;
        final JTextField instituteText = new JTextField();
        instituteText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        instituteText.setBounds(itemWidth, bottomBandItemHeight+12, 800, 25);
        bottombandPanel.add(instituteText);
        
        itemWidth = 40;
        bottomBandItemHeight = bottomBandItemHeight + 40;
        JLabel school_label = new JLabel("Main Head         :");
        school_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        school_label.setBounds(itemWidth, bottomBandItemHeight, 150, 50);
        bottombandPanel.add(school_label);

        itemWidth = itemWidth + 130;
        final JTextField schoolText = new JTextField();
        schoolText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        schoolText.setBounds(itemWidth, bottomBandItemHeight+12, 800, 25);
        bottombandPanel.add(schoolText);
        
        itemWidth = 40;
        bottomBandItemHeight = bottomBandItemHeight + 40;
        JLabel schoolAdd_label = new JLabel("Address Head    :");
        schoolAdd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        schoolAdd_label.setBounds(itemWidth, bottomBandItemHeight, 150, 50);
        bottombandPanel.add(schoolAdd_label);

        itemWidth = itemWidth + 130;
        final JTextField schoolAddText = new JTextField();
        schoolAddText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        schoolAddText.setBounds(itemWidth, bottomBandItemHeight+12, 800, 25);
        bottombandPanel.add(schoolAddText);
        
        itemWidth = 40;
        bottomBandItemHeight = bottomBandItemHeight + 40;
        JLabel headerShort_label = new JLabel("Header Short Name :");
        headerShort_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        headerShort_label.setBounds(itemWidth, bottomBandItemHeight, 300, 50);
        bottombandPanel.add(headerShort_label);

        itemWidth = itemWidth + 160;
        final JTextField headerShortText = new JTextField();
        headerShortText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        headerShortText.setBounds(itemWidth, bottomBandItemHeight+12, 300, 25);
        bottombandPanel.add(headerShortText);
        
        itemWidth = itemWidth + 460;
        bottomBandItemHeight = bottomBandItemHeight;
        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        addButton.setBounds(itemWidth, bottomBandItemHeight+12, 90, 25);
        bottombandPanel.add(addButton);
        
        bottomBandItemHeight = bottomBandItemHeight + 60;
        JSeparator jSeparator1  = new JSeparator();
        jSeparator1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        jSeparator1.setBounds(40, bottomBandItemHeight, (screenWidth - 200), 50);
        bottombandPanel.add(jSeparator1);
        
        ////addButton action///////////////////
        addButton.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
            int keyCode=e.getKeyCode();
            String inst_name = instituteText.getText();
            String school_name = schoolText.getText();
            String school_add = schoolAddText.getText();
            String short_name = headerShortText.getText();
            if(keyCode == 10){
				addToOrder(sessionData,inst_name,school_name,school_add,short_name, academic);
              }
            }
        });
        
        addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String inst_name = instituteText.getText();
	            String school_name = schoolText.getText();
	            String school_add = schoolAddText.getText();
	            String short_name = headerShortText.getText();
				addToOrder(sessionData,inst_name,school_name,school_add,short_name, academic);
			}
        });
        
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
        
        if (!headerMap.isEmpty()) {
	        final JLabel[] srNo_labels = new JLabel[headerMap.size()];
	        final JLabel[] short_labels = new JLabel[headerMap.size()];
	        final JLabel[] short_name = new JLabel[headerMap.size()];
	        final JLabel[] institute_labels = new JLabel[headerMap.size()];
	        final JTextField[] institute_text = new JTextField[headerMap.size()];
	        final JLabel[] mainHead_labels = new JLabel[headerMap.size()];
	        final JTextField[] mainHead_text = new JTextField[headerMap.size()];
	        final JLabel[] addHead_labels = new JLabel[headerMap.size()];
	        final JTextField[] addHead_text = new JTextField[headerMap.size()];
	        final JButton[] edit_buttons = new JButton[headerMap.size()];
	        final JButton[] save_buttons = new JButton[headerMap.size()];
	        final JButton[] delete_buttons = new JButton[headerMap.size()];
	        final JSeparator[] jSeparators  = new JSeparator[headerMap.size()];
	        
	        int k = 0;
	        int j = 5;
	        Set set = headerMap.entrySet();
			Iterator i = set.iterator();
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
				LinkedHashMap<String, String> headerDetails = new LinkedHashMap<String, String>();
				headerDetails = (LinkedHashMap<String, String>) me.getValue();
				
				itemWidth = 20;
	        	srNo_labels[k] = new JLabel((k+1)+".");
	        	srNo_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	        	srNo_labels[k].setBounds(itemWidth, j, 120, 25);
	        	dataPanel.add(srNo_labels[k]);
	        	
	        	itemWidth = itemWidth + 50;
	        	short_labels[k] = new JLabel("Header Short Name :    ");
	        	short_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	        	short_labels[k].setBounds(itemWidth, j, 500, 25);
	    		dataPanel.add(short_labels[k]);
	    		
	    		itemWidth = itemWidth + 170;
	    		short_name[k] = new JLabel(commonObj.revertCommaApostrophy(headerDetails.get("shortName")));
	    		short_name[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		short_name[k].setBounds(itemWidth, j, 500, 25);
	    		dataPanel.add(short_name[k]);
	    		
	    		itemWidth = itemWidth + 570;
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
	    		
	    		itemWidth = 70;
	    		institute_labels[k] = new JLabel("Institute Name          : ");
	    		institute_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		institute_labels[k].setBounds(itemWidth, j+30, 200, 25);
	    		dataPanel.add(institute_labels[k]);
	    		
	    		itemWidth = itemWidth + 170;
	    		institute_text[k] = new JTextField(commonObj.revertCommaApostrophy(headerDetails.get("institute")));
	    		institute_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		institute_text[k].setBounds(itemWidth, j+30, 800, 25);
	    		institute_text[k].setEditable(false);
	    		dataPanel.add(institute_text[k]);
	    		
	    		itemWidth = 70;
	    		mainHead_labels[k] = new JLabel("Main Head                : ");
	    		mainHead_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		mainHead_labels[k].setBounds(itemWidth, j+60, 200, 25);
	    		dataPanel.add(mainHead_labels[k]);
	    		
	    		itemWidth = itemWidth + 170;
	    		mainHead_text[k] = new JTextField(commonObj.revertCommaApostrophy(headerDetails.get("mainHead")));
	    		mainHead_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		mainHead_text[k].setBounds(itemWidth, j+60, 800, 25);
	    		mainHead_text[k].setEditable(false);
	    		dataPanel.add(mainHead_text[k]);
	    		
	    		itemWidth = 70;
	    		addHead_labels[k] = new JLabel("Address Head           : ");
	    		addHead_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		addHead_labels[k].setBounds(itemWidth, j+90, 200, 25);
	    		dataPanel.add(addHead_labels[k]);
	    		
	    		itemWidth = itemWidth + 170;
	    		addHead_text[k] = new JTextField(commonObj.revertCommaApostrophy(headerDetails.get("addHead")));
	    		addHead_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		addHead_text[k].setBounds(itemWidth, j+90, 800, 25);
	    		addHead_text[k].setEditable(false);
	    		dataPanel.add(addHead_text[k]);
//	    		itemWidth = itemWidth + 220;
//	    		category_combo[k] = new JComboBox(feeCategoryList2);
//	    		category_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("category").toString());
//	    		category_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
//	    		category_combo[k].setBounds(itemWidth, j, 150, 25);
//	    		dataPanel.add(category_combo[k]);
//	    		
//	    		itemWidth = itemWidth + 200;
//	    		optional_combo[k] = new JComboBox(optionList);
//	    		optional_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("optional").toString());
//	    		optional_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
//	    		optional_combo[k].setBounds(itemWidth, j, 70, 25);
//	    		dataPanel.add(optional_combo[k]);
//	    		
//	    		itemWidth = itemWidth + 100;
//	    		frequency_combo[k] = new JComboBox(payFrequencyList);
//	    		frequency_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("frequency").toString());
//	    		frequency_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
//	    		frequency_combo[k].setBounds(itemWidth, j, 120, 25);
//	    		dataPanel.add(frequency_combo[k]);
//	    		
//	    		itemWidth = itemWidth + 150;
//	    		amount_text[k] = new JTextField(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("amount").toString());
//	    		amount_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
//	    		amount_text[k].setBounds(itemWidth, j, 70, 25);
//	    		amount_text[k].setEditable(false);
//	    		dataPanel.add(amount_text[k]);
	    		
	    		jSeparators[k] = new JSeparator();
	    		jSeparators[k].setFont(new Font("Book Antiqua", Font.BOLD, 18));
	    		jSeparators[k].setBounds(10, j + 120, (screenWidth - 220), 50);
	    		dataPanel.add(jSeparators[k]);
	    		
	            final int m = k;
	    		edit_buttons[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							boolean isEditable = true;
							String shortName = short_name[m].getText();
							
//							if(dbValidate.connectDatabase(sessionData)){
//								isEditable = dbValidate.isFeesEditable(sessionData, academicYearClass, stdClass, section, optional, feesHead);
//							}
							
							if(isEditable){
								institute_text[m].setEditable(true);
								mainHead_text[m].setEditable(true);
								addHead_text[m].setEditable(true);
								edit_buttons[m].setEnabled(false);
								save_buttons[m].setEnabled(true);
							}
							else{
								JOptionPane.showMessageDialog(null, "Cannot delete as school head data for short name "+shortName+" is utilized");
							}
						} catch (Exception e1) {
							commonObj.logException(e1);
						}
					}
				});
	    		
	    		save_buttons[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							boolean updateFlag = false;
							String shortName = short_name[m].getText();
							String inst_name = institute_text[m].getText();
							String main_head = mainHead_text[m].getText();
							String add_head = addHead_text[m].getText();
							
							if(dbValidate.connectDatabase(sessionData)){
								updateFlag = dbValidate.addReceiptHead(sessionData, inst_name, main_head, add_head, shortName, "update");
							}
							
							institute_text[m].setEditable(false);
							mainHead_text[m].setEditable(false);
							addHead_text[m].setEditable(false);
							edit_buttons[m].setEnabled(true);
							save_buttons[m].setEnabled(false);
						} catch (Exception e1) {
							commonObj.logException(e1);
						}
					}
				});
	    		
	    		delete_buttons[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						///delete school head/////
						try {
							boolean isEditable = true;
							
//							if(dbValidate.connectDatabase(sessionData)){
//								isEditable = dbValidate.isFeesEditable(sessionData, academicYearClass, stdClass, section, optional, feesHead);
//							}
							
							if(isEditable){
								int reply = 0;
								reply = JOptionPane.showConfirmDialog(null, "Would You Like to delete "+short_name[m].getText()+"?", "Confirm validate", JOptionPane.YES_NO_OPTION);
								
								if(dbValidate.connectDatabase(sessionData) && reply == JOptionPane.YES_OPTION){
									dbValidate.addReceiptHead(sessionData, "", "", "", short_name[m].getText(), "delete");
									
									frame.setVisible(false);
									CreateSchoolHead.getCreateSchoolHead(sessionData, academic);
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Cannot delete as school head for "+short_name[m].getText()+" is utilized.");
							}
						} catch (Exception e1) {
							commonObj.logException(e1);
						}
					}
				});
	    		
	    		j = j + 130;
	    		k++;
	        }
			
			bottombandPanel.revalidate();
			bottombandPanel.repaint();
        }
        
        bottomBandItemHeight = bottomBandItemHeight + 30;
        JScrollPane jsp;
        int height = 380;
		jsp = new JScrollPane(dataPanel, 
				   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  
				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(30, bottomBandItemHeight + 10, screenWidth - 180, screenHeight - height);
		
        bottombandPanel.add(jsp, BorderLayout.SOUTH);
        ///////////////end order panel////////////
        
        mainPanel.add(bottombandPanel, BorderLayout.SOUTH);
        panel.add(mainPanel, BorderLayout.EAST);

        frame.setVisible(true);
        
    }

//    private static void placeComponents(JPanel panel) {
//
//        panel.setLayout(new BorderLayout());
//
//        // ///////////top panel////////////////////////////////////////
//        JPanel titlePanel = new JPanel() {
//
//            private static final long serialVersionUID = 1L;
//
//            public void paintComponent(Graphics g) {
//
//                Image img = new ImageIcon(img_path + img_titleband).getImage();
//                Dimension size = new Dimension(screenWidth, 75);
//                setPreferredSize(size);
//                setMinimumSize(size);
//                setMaximumSize(size);
//                setSize(size);
//                setLayout(null);
//                g.drawImage(img, 0, 0, null);
//            }
//        };
//        titlePanel.setLayout(null);
//
//        // //////////////logo//////////////////
//        final ImageIcon icon = new ImageIcon(img_path + img_logo);
//        JLabel label = new JLabel(icon);
//        // label.setFont(new Font("Book Antiqua", Font.BOLD, 30));
//        label.setBounds(45, 15, 55, 55);
//    	titlePanel.add(label);
//        // /////////////title//////////////
//    	JLabel title_0 = new JLabel(app_header_0);
//        title_0.setFont(new Font(app_header_0_fontName, Font.BOLD, app_header_0_fontSize));
//        title_0.setBounds((screenWidth / 2) - app_header_0_widthSpace, app_header_0_heightSpace, screenWidth - 400, 50);
//        titlePanel.add(title_0);
//        
//        JLabel title = new JLabel(app_header);
//        title.setFont(new Font(app_header_fontName, Font.BOLD, app_header_fontSize));//38 default
//        title.setBounds((screenWidth / 2) - app_header_widthSpace, app_header_heightSpace, screenWidth - 100, 50);
//        titlePanel.add(title);
//        
//        JLabel title_2 = new JLabel(app_header_2);
//        title_2.setFont(new Font(app_header_2_fontName, Font.BOLD, app_header_2_fontSize));//38 default
//        title_2.setBounds((screenWidth / 2) - app_header_2_widthSpace, app_header_2_heightSpace, screenWidth - 360, 50);
//        titlePanel.add(title_2);
//
//        // /account//////////////
//        final ImageIcon iconaccount = new ImageIcon(img_path + img_myaccount);
//        JLabel labelaccount = new JLabel(iconaccount);
//        labelaccount.setBounds((screenWidth - 150), 10, 60, 60);
//        titlePanel.add(labelaccount);
//        
//        labelaccount.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent me) {
//				try {
//					frame.setVisible(false);
//					new MyAccount(sessionData);
//				} catch (Exception e) {
//					commonObj.logException(e);
//				}
//			}
//		});
//        
//        // /logout//////////////
//        final ImageIcon iconlogout = new ImageIcon(img_path + img_logout);
//        JLabel labellogout = new JLabel(iconlogout);
//        labellogout.setBounds((screenWidth - 80), 10, 60, 60);
//        titlePanel.add(labellogout);
//        
//        labellogout.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent me) {
//				boolean deleteFlag = false;
//				try {
//					if (dbValidate.connectDatabase(sessionData)) {
//						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role,
//								sessionData.getSectionName());
//						dbValidate.closeDatabase(sessionData);
//
//						frame.setVisible(false);
//						String[] arguments = new String[] { "" };
//						LoginView.main(arguments);
//					}
//				} catch (Exception e1) {
//					commonObj.logException(e1);
//				} finally {
//					dbValidate.closeDatabase(sessionData);
//				}
//			}
//		});
//
//        panel.add(titlePanel, BorderLayout.NORTH);
//
//        // ///////////left Band panel////////////////////////////////////////
//        JPanel leftPanel = new JPanel() {
//
//            private static final long serialVersionUID = 1L;
//
//            public void paintComponent(Graphics g) {
//
//                Image img = new ImageIcon(img_path + img_leftband).getImage();
//                Dimension size = new Dimension(150, screenHeight - 100);
//                setPreferredSize(size);
//                setMinimumSize(size);
//                setMaximumSize(size);
//                setSize(size);
//                setLayout(null);
//                g.drawImage(img, 0, 0, null);
//            }
//        };
//        leftPanel.setLayout(null);
//
//        // /home//////////////
//        final ImageIcon iconhome = new ImageIcon(img_path + img_home);
//        JLabel labelhome = new JLabel(iconhome);
//        labelhome.setBounds(45, 10, 60, 60);
//        leftPanel.add(labelhome);
//        
//        labelhome.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent me) {
//				try {
//					frame.setVisible(false);
//					new Welcome(sessionData, user_name, user_role);
//				} catch (Exception e) {
//					commonObj.logException(e);
//				}
//			}
//		});
//        
//        int p = 50;
//        JButton studentButton = new JButton("Student");
//        studentButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//        studentButton.setBounds(10, p + 50, 130, 35);
//        leftPanel.add(studentButton);
//        
//        studentButton.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//
//                frame.setVisible(false);
//                new Student(sessionData, sessionData.getSectionName(), user_name, user_role);
//            }
//        });
//
//        if(staff_required.equalsIgnoreCase("true")){
//	        p = p + 50;
//	        JButton staffButton = new JButton("Staff");
//	        staffButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//	        staffButton.setBounds(10, p + 50, 130, 35);
//	        leftPanel.add(staffButton);
//	
//	        staffButton.addActionListener(new ActionListener() {
//	
//	            public void actionPerformed(ActionEvent e) {
//	
//	                frame.setVisible(false);
//	                new Staff(sessionData, sessionData.getSectionName(), user_name, user_role);
//	            }
//	        });
//        }
//        p = p + 50;
//        JButton academicButton = new JButton("Academic");
//        academicButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//        academicButton.setBounds(10, p + 50, 130, 35);
//        leftPanel.add(academicButton);
//
//        academicButton.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//
//                frame.setVisible(false);
//                new Academic(sessionData, sessionData.getSectionName(), user_name, user_role);
//            }
//        });
//
//        if(account_required.equalsIgnoreCase("true")){
//	        p = p + 50;
//	        JButton accountButton = new JButton("Account");
//	        accountButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//	        accountButton.setBounds(10, p + 50, 130, 35);
//	//        leftPanel.add(accountButton);
//	
//	        accountButton.addActionListener(new ActionListener() {
//	
//	            public void actionPerformed(ActionEvent e) {
//	                frame.setVisible(false);
//	                new CreateSubject(sessionData, "", "", sessionData.getSectionName(), user_name, user_role);
//	            }
//	        });
//        }
//        p = p + 50;
//        JButton searchButton = new JButton("Find");
//        searchButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//        searchButton.setBounds(10, p + 50, 130, 35);
//        leftPanel.add(searchButton);
//
//        searchButton.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//
//            	LinkedHashMap<String,LinkedHashMap<String, String>> studMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
//                new FindStudent(sessionData, "", "", "", "", "", "", studMap, sessionData.getSectionName(),
//                    user_name, user_role);
//                frame.setVisible(false);
//            }
//        });
//        
//        if(sms_required.equalsIgnoreCase("true")){
//	        p = p + 50;
//	        JButton smsButton = new JButton("SMS");
//	        smsButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//	        smsButton.setBounds(10, p + 50, 130, 35);
//	        leftPanel.add(smsButton);
//	
//	        smsButton.addActionListener(new ActionListener() {
//	
//	            public void actionPerformed(ActionEvent e) {
//	
//	            	frame.setVisible(false);
//	            	LinkedHashMap findStudentMap = new LinkedHashMap();
//					new SmsPage(sessionData, "", "", "", "", "", "", findStudentMap, false, "", "", "", "", "",
//							"", sessionData.getSectionName(), "", user_name, user_role,"","","");
//	            }
//	        });
//        }
//        
//        if(fee_required.equalsIgnoreCase("true")){
//	        p = p + 50;
//	        JButton feeButton = new JButton("FEE");
//	        feeButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//	        feeButton.setBackground(Color.GREEN);
//	        feeButton.setBounds(10, p + 50, 130, 35);
//	        leftPanel.add(feeButton);
//	
//	        feeButton.addActionListener(new ActionListener() {
//	
//	            public void actionPerformed(ActionEvent e) {
//	
//	            	frame.setVisible(false);
//					new CreateFeesHead(sessionData, "", "", sessionData.getSectionName());
//	            }
//	        });
//        }
//        
//        panel.add(leftPanel, BorderLayout.WEST);
//
//        // ///////////main panel////////////////////////////////////////
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BorderLayout());
//
//        // ///////////menu band panel////////////////////////////////////////
//        JPanel topbandPanel = new JPanel() {
//
//            private static final long serialVersionUID = 1L;
//
//            public void paintComponent(Graphics g) {
//
//                Image img = new ImageIcon(img_path + img_menuband).getImage();
//                Dimension size = new Dimension(screenWidth - 150, 80);
//                setPreferredSize(size);
//                setMinimumSize(size);
//                setMaximumSize(size);
//                setSize(size);
//                setLayout(null);
//                g.drawImage(img, 0, 0, null);
//            }
//        };
//        topbandPanel.setLayout(null);
//
//        JLabel menuBandTitle = new JLabel(secName);
//		menuBandTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
//		menuBandTitle.setForeground(Color.orange);
//		menuBandTitle.setBounds(20, 0, 600, 30);
//		topbandPanel.add(menuBandTitle);
//		
//		JLabel subMenuTitle = new JLabel("Fee");
//		subMenuTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
//		subMenuTitle.setForeground(Color.orange);
//		subMenuTitle.setBounds(20, 45, 100, 30);
//		topbandPanel.add(subMenuTitle);
//        
//		int width = 80;
//		JButton schoolHeadButton = new JButton("School Head");
//		schoolHeadButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
//		schoolHeadButton.setBackground(Color.GREEN);
//		schoolHeadButton.setBounds(width, 50, 140, 24);
//		topbandPanel.add(schoolHeadButton);
//
//		schoolHeadButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);
//				new CreateSchoolHead(sessionData);
//			}
//		});
//
//		width = width + 150;
//		JButton createFeeButton = new JButton("Fee Head");
//		createFeeButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
//		createFeeButton.setBounds(width, 50, 140, 24);
//		topbandPanel.add(createFeeButton);
//
//		createFeeButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);
//				new CreateFeesHead(sessionData, "", "", sessionData.getSectionName());
//			}
//		});
//		
//		/*width = width + 190;
//		JButton feeAllotButton = new JButton("Fee Allotment");
//		feeAllotButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
//		feeAllotButton.setBounds(width, 50, 150, 24);
//		topbandPanel.add(feeAllotButton);
//
//		feeAllotButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//
//			}
//		});*/
//		
//		width = width + 190;
//		JButton feeSearchButton = new JButton("Search & Pay");
//		feeSearchButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
//		feeSearchButton.setBounds(width, 50, 170, 24);
//		topbandPanel.add(feeSearchButton);
//
//		feeSearchButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);
//				LinkedHashMap retStudentMap = new LinkedHashMap();
//				new SearchFeeStudent(sessionData, "", "", "", "", "", "", retStudentMap, sessionData.getSectionName(), "", "", "", "", false, "", "", false);
//			}
//		});
//		
//		/*width = width + 190;
//		JButton feePayButton = new JButton("Fees Payment");
//		feePayButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
//		feePayButton.setBounds(width, 50, 170, 24);
//		topbandPanel.add(feePayButton);
//
//		feePayButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//
//			}
//		});*/
//		
//		width = width + 190;
//		JButton feeReportButton = new JButton("Fee Report");
//		feeReportButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
//		feeReportButton.setBounds(width, 50, 170, 24);
//		topbandPanel.add(feeReportButton);
//
//		feeReportButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);
//				LinkedHashMap<String,LinkedHashMap<String, String>> studentMapEmpty =  new LinkedHashMap<>();
//				FeesReport.getFeesReport(sessionData, "", "", "", "", "", "", studentMapEmpty, sessionData.getSectionName(), "", "", "", "", false);
//			}
//		});
//		
//        mainPanel.add(topbandPanel, BorderLayout.NORTH);
//
//        // ///////////bottom band/////////////
//        JPanel bottombandPanel = new JPanel() {
//
//            private static final long serialVersionUID = 1L;
//
//            public void paintComponent(Graphics g) {
//
//                Image img = new ImageIcon(img_path + img_mainband).getImage();
//                Dimension size =
//                    new Dimension(screenWidth - 150, screenHeight - 150);
//                setPreferredSize(size);
//                setMinimumSize(size);
//                setMaximumSize(size);
//                setSize(size);
//                setLayout(null);
//                g.drawImage(img, 0, 0, null);
//            }
//        };
//        bottombandPanel.setLayout(null);
//        
//        int tabHeight = 150;
//        
//        ///founder//////////////
//        /*final ImageIcon iconfounder = new ImageIcon(img_path + img_founder);
//        JLabel labelfounder = new JLabel(iconfounder);
//        labelfounder.setBounds(mainCentre - 160, -30, 315, 417);
//        if(show_founder.equalsIgnoreCase("true")){
//        	tabHeight = tabHeight + 180;
//        	bottombandPanel.add(labelfounder);
//        }*/
//        
//        int bottomBandItemHeight = 30;
//        int itemWidth = 40;
//        
//        // /////////////ACADEMIV YEAR //////////////
//        JLabel institute_label = new JLabel("Institute Name   :");
//        institute_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        institute_label.setBounds(itemWidth, bottomBandItemHeight, 150, 50);
//        bottombandPanel.add(institute_label);
//
//        itemWidth = itemWidth + 130;
//        final JTextField instituteText = new JTextField();
//        instituteText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        instituteText.setBounds(itemWidth, bottomBandItemHeight+12, 800, 25);
//        bottombandPanel.add(instituteText);
//        
//        itemWidth = 40;
//        bottomBandItemHeight = bottomBandItemHeight + 40;
//        JLabel school_label = new JLabel("School Name     :");
//        school_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        school_label.setBounds(itemWidth, bottomBandItemHeight, 150, 50);
//        bottombandPanel.add(school_label);
//
//        itemWidth = itemWidth + 130;
//        final JTextField schoolText = new JTextField();
//        schoolText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        schoolText.setBounds(itemWidth, bottomBandItemHeight+12, 800, 25);
//        bottombandPanel.add(schoolText);
//        
//        itemWidth = 40;
//        bottomBandItemHeight = bottomBandItemHeight + 40;
//        JLabel schoolAdd_label = new JLabel("School Address :");
//        schoolAdd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        schoolAdd_label.setBounds(itemWidth, bottomBandItemHeight, 150, 50);
//        bottombandPanel.add(schoolAdd_label);
//
//        itemWidth = itemWidth + 130;
//        final JTextField schoolAddText = new JTextField();
//        schoolAddText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        schoolAddText.setBounds(itemWidth, bottomBandItemHeight+12, 800, 25);
//        bottombandPanel.add(schoolAddText);
//        
//        itemWidth = 40;
//        bottomBandItemHeight = bottomBandItemHeight + 40;
//        JLabel headerShort_label = new JLabel("Header Short Name :");
//        headerShort_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        headerShort_label.setBounds(itemWidth, bottomBandItemHeight, 300, 50);
//        bottombandPanel.add(headerShort_label);
//
//        itemWidth = itemWidth + 160;
//        final JTextField headerShortText = new JTextField();
//        headerShortText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        headerShortText.setBounds(itemWidth, bottomBandItemHeight+12, 300, 25);
//        bottombandPanel.add(headerShortText);
//        
//        itemWidth = itemWidth + 460;
//        bottomBandItemHeight = bottomBandItemHeight;
//        JButton addButton = new JButton("Add");
//        addButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        addButton.setBounds(itemWidth, bottomBandItemHeight+12, 90, 25);
//        bottombandPanel.add(addButton);
//        
//        bottomBandItemHeight = bottomBandItemHeight + 60;
//        JSeparator jSeparator1  = new JSeparator();
//        jSeparator1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//        jSeparator1.setBounds(40, bottomBandItemHeight, (screenWidth - 200), 50);
//        bottombandPanel.add(jSeparator1);
//        
//        ////addButton action///////////////////
//        addButton.addKeyListener(new KeyAdapter(){
//            public void keyReleased(KeyEvent e){
//            int keyCode=e.getKeyCode();
//            String inst_name = instituteText.getText();
//            String school_name = schoolText.getText();
//            String school_add = schoolAddText.getText();
//            String short_name = headerShortText.getText();
//            if(keyCode == 10){
//				addToOrder(sessionData,inst_name,school_name,school_add,short_name);
//              }
//            }
//        });
//        
//        addButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				String inst_name = instituteText.getText();
//	            String school_name = schoolText.getText();
//	            String school_add = schoolAddText.getText();
//	            String short_name = headerShortText.getText();
//				addToOrder(sessionData,inst_name,school_name,school_add,short_name);
//			}
//        });
//        
//        /////end order headers///////////////
//        /////////////order panel/////////////
//        
//        JPanel dataPanel = new JPanel() {
//
//            private static final long serialVersionUID = 1L;
//
//            public void paintComponent(Graphics g) {
//
//                Image img = new ImageIcon(img_path + img_databand).getImage();
//                Dimension size =
//                    new Dimension(screenWidth - 200, screenHeight + scrollHeight);//this height is responsible to increase scroll area
//                setPreferredSize(size);
//                setMinimumSize(size);
//                setMaximumSize(size);
//                setSize(size);
//                setLayout(null);
//                g.drawImage(img, 0, 0, null);
//            }
//        };
////      dataPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
//        dataPanel.setAutoscrolls(true);
//        dataPanel.setLayout(null);
//        
////        if (!schoolHeadMap.isEmpty()) {
////	        final JLabel[] srNo_labels = new JLabel[schoolHeadMap.size()];
////	        final JLabel[] orderNo_labels = new JLabel[feesHeadMap.size()];
////	        final JTextField[] feeName_text = new JTextField[feesHeadMap.size()];
////	        final JComboBox[] category_combo = new JComboBox[feesHeadMap.size()];
////	        final JComboBox[] optional_combo = new JComboBox[feesHeadMap.size()];
////	        final JComboBox[] frequency_combo = new JComboBox[feesHeadMap.size()];
////	        final JTextField[] amount_text = new JTextField[feesHeadMap.size()];
////	        final JButton[] edit_buttons = new JButton[feesHeadMap.size()];
////	        final JButton[] save_buttons = new JButton[feesHeadMap.size()];
////	        final JButton[] delete_buttons = new JButton[feesHeadMap.size()];
////	        final JSeparator[] jSeparators  = new JSeparator[feesHeadMap.size()];
////	        
////	        String[] feeCategoryList2 = feeCategory.substring(0,feeCategory.lastIndexOf(",")).split(",");
////	        
////	        int k = 0;
////	        int j = 5;
////	        Set set = feesHeadMap.entrySet();
////			Iterator i = set.iterator();
////			while(i.hasNext()) {
////				Map.Entry me = (Map.Entry)i.next();
////				
////				itemWidth = 20;
////	        	srNo_labels[k] = new JLabel((k+1)+"");
////	        	srNo_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	        	srNo_labels[k].setBounds(itemWidth, j, 120, 25);
////	        	dataPanel.add(srNo_labels[k]);
////	        	
////	        	orderNo_labels[k] = new JLabel(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("order_no").toString());
////	        	dataPanel.add(orderNo_labels[k]);
////	    		
////	        	itemWidth = itemWidth + 50;
////	        	feeName_text[k] = new JTextField(me.getKey().toString().replace("$$", "."));
////	        	feeName_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	        	feeName_text[k].setBounds(itemWidth, j, 200, 25);
////	        	feeName_text[k].setEditable(false);
////	    		dataPanel.add(feeName_text[k]);
////	    		
////	    		itemWidth = itemWidth + 220;
////	    		category_combo[k] = new JComboBox(feeCategoryList2);
////	    		category_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("category").toString());
////	    		category_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	    		category_combo[k].setBounds(itemWidth, j, 150, 25);
////	    		dataPanel.add(category_combo[k]);
////	    		
////	    		itemWidth = itemWidth + 200;
////	    		optional_combo[k] = new JComboBox(optionList);
////	    		optional_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("optional").toString());
////	    		optional_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	    		optional_combo[k].setBounds(itemWidth, j, 70, 25);
////	    		dataPanel.add(optional_combo[k]);
////	    		
////	    		itemWidth = itemWidth + 100;
////	    		frequency_combo[k] = new JComboBox(payFrequencyList);
////	    		frequency_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("frequency").toString());
////	    		frequency_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	    		frequency_combo[k].setBounds(itemWidth, j, 120, 25);
////	    		dataPanel.add(frequency_combo[k]);
////	    		
////	    		itemWidth = itemWidth + 150;
////	    		amount_text[k] = new JTextField(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("amount").toString());
////	    		amount_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	    		amount_text[k].setBounds(itemWidth, j, 70, 25);
////	    		amount_text[k].setEditable(false);
////	    		dataPanel.add(amount_text[k]);
////	    		
////	    		itemWidth = itemWidth + 80;
////	    		edit_buttons[k] = new JButton("Edit");
////	    		edit_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	    		edit_buttons[k].setBounds(itemWidth, j, 70, 25);
////	    		dataPanel.add(edit_buttons[k]);
////	    		
////	    		itemWidth = itemWidth + 80;
////	    		save_buttons[k] = new JButton("Save");
////	    		save_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	    		save_buttons[k].setEnabled(false);
////	    		save_buttons[k].setBounds(itemWidth, j, 70, 25);
////	    		dataPanel.add(save_buttons[k]);
////	    		
////	    		itemWidth = itemWidth + 80;
////	    		delete_buttons[k] = new JButton("Delete");
////	    		delete_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	    		delete_buttons[k].setBounds(itemWidth, j, 90, 25);
////	    		dataPanel.add(delete_buttons[k]);
////	    		
////	    		jSeparators[k] = new JSeparator();
////	    		jSeparators[k].setFont(new Font("Book Antiqua", Font.BOLD, 18));
////	    		jSeparators[k].setBounds(10, j + 28, (screenWidth - 220), 50);
////	    		dataPanel.add(jSeparators[k]);
////	    		
////	            final int m = k;
////	    		edit_buttons[k].addActionListener(new ActionListener() {
////
////					public void actionPerformed(ActionEvent e) {
////						try {
////							boolean isEditable = true;
////							String optional = optional_combo[m].getSelectedItem().toString();
////							String feesHead = feeName_text[m].getText();
////							
////							if(dbValidate.connectDatabase(sessionData)){
////								isEditable = dbValidate.isFeesEditable(sessionData, academicYearClass, stdClass, section, optional, feesHead);
////							}
////							
////							if(isEditable){
////								feeName_text[m].setEditable(true);
////								amount_text[m].setEditable(true);
////								edit_buttons[m].setEnabled(false);
////								save_buttons[m].setEnabled(true);
////							}
////							else{
////								JOptionPane.showMessageDialog(null, "Cannot delete as fee data for "+feesHead+" exist");
////							}
////						} catch (Exception e1) {
////							commonObj.logException(e1);
////						} finally {
////							dbValidate.closeDatabase(sessionData);
////						}
////					}
////				});
////	    		
////	    		optional_combo[k].addActionListener(new ActionListener() {
////
////	    			public void actionPerformed(ActionEvent e) {
////	                	String optionalSel = optional_combo[m].getSelectedItem().toString();
////	                	String payFrequency = frequency_combo[m].getSelectedItem().toString();
////	                	
////	                	if(optionalSel.equalsIgnoreCase("Yes")){
////	                		frequency_combo[m].setSelectedItem("Occasionally");
////	                	}
////	                	else if(payFrequency.equalsIgnoreCase("Occasionally")){
////	                		frequency_combo[m].setSelectedItem("Monthly");
////	                	}
////	    			}
////	            });
////	    		
////	    		frequency_combo[k].addActionListener(new ActionListener() {
////
////	    			public void actionPerformed(ActionEvent e) {
////	    				String payFrequency = frequency_combo[m].getSelectedItem().toString();
////	                	if(payFrequency.equalsIgnoreCase("Occasionally")){
////	                		optional_combo[m].setSelectedItem("Yes");
////	                	}
////	                	else{
////	                		optional_combo[m].setSelectedItem("No");
////	                	}
////	    			}
////	            });
////	    		
////	    		save_buttons[k].addActionListener(new ActionListener() {
////
////					public void actionPerformed(ActionEvent e) {
////						try {
////							
////							String orderNoSel = orderNo_labels[m].getText();
////							String feeNameSel = feeName_text[m].getText().replace(".", "$$");
////							String categorySel = category_combo[m].getSelectedItem().toString();
////							String optionalSel = optional_combo[m].getSelectedItem().toString();
////							String frequencySel = frequency_combo[m].getSelectedItem().toString();
////							String amountSel = amount_text[m].getText();
////							
////							if(dbValidate.connectDatabase(sessionData)){
////								dbValidate.addFeeName(sessionData, feeNameSel, categorySel, "", optionalSel, frequencySel, amountSel, 
////										true, stdClass, academicYearClass, section, orderNoSel, false, "");
////							}
////							
////							feeName_text[m].setEditable(false);
////							amount_text[m].setEditable(false);
////							edit_buttons[m].setEnabled(true);
////							save_buttons[m].setEnabled(false);
////						} catch (Exception e1) {
////							commonObj.logException(e1);
////						} finally {
////							dbValidate.closeDatabase(sessionData);
////						}
////					}
////				});
////	    		
////	    		delete_buttons[k].addActionListener(new ActionListener() {
////
////					public void actionPerformed(ActionEvent e) {
////						///delete fee name/////
////						try {
////							boolean isEditable = true;
////							String optional = optional_combo[m].getSelectedItem().toString();
////							String feesHead = feeName_text[m].getText();
////							
////							if(dbValidate.connectDatabase(sessionData)){
////								isEditable = dbValidate.isFeesEditable(sessionData, academicYearClass, stdClass, section, optional, feesHead);
////							}
////							
////							if(isEditable){
////								int reply = 0;
////								reply = JOptionPane.showConfirmDialog(null, "Would You Like to delete "+feeName_text[m].getText()+"?", "Confirm validate", JOptionPane.YES_NO_OPTION);
////								
////								if(dbValidate.connectDatabase(sessionData) && reply == JOptionPane.YES_OPTION){
////									dbValidate.deleteFeeName(sessionData, orderNo_labels[m].getText());
////									
////									frame.setVisible(false);
////									new CreateSchoolHead(sessionData);
////								}
////							}
////							else{
////								JOptionPane.showMessageDialog(null, "Cannot delete as fee data for "+feesHead+" exist");
////							}
////						} catch (Exception e1) {
////							commonObj.logException(e1);
////						} finally {
////							dbValidate.closeDatabase(sessionData);
////						}
////					}
////				});
////	    		
////	    		j = j + 32;
////	    		k++;
////	        }
////			
////			bottombandPanel.revalidate();
////			bottombandPanel.repaint();
////        }
//        
//        bottomBandItemHeight = bottomBandItemHeight + 30;
//        JScrollPane jsp;
//        int height = 380;
//		jsp = new JScrollPane(dataPanel, 
//				   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  
//				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		jsp.setBounds(30, bottomBandItemHeight + 10, screenWidth - 180, screenHeight - height);
//		
//        bottombandPanel.add(jsp, BorderLayout.SOUTH);
//        ///////////////end order panel////////////
//        
//        mainPanel.add(bottombandPanel, BorderLayout.SOUTH);
//        panel.add(mainPanel, BorderLayout.EAST);
//    }
    
    public static void addToOrder(SessionData sessionData,String inst_name,String main_head,
    		String add_head,String short_name, String academic){
    	
    	boolean validateFields = true;
		try {
			inst_name = commonObj.replaceCommaApostrophy(inst_name);
			main_head = commonObj.replaceCommaApostrophy(main_head);
			add_head = commonObj.replaceCommaApostrophy(add_head);
			
			if (inst_name.isEmpty() || main_head.isEmpty() || add_head.isEmpty() || short_name.isEmpty()) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Please enter all fields.");
			}
			else if (commonObj.checkComma(inst_name)) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Institute Name cannot contain |:;");
			} else if (inst_name.length() > 80) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, commonObj.charExceeded("Institute Name", inst_name, 80));
			} else if (commonObj.checkComma(main_head)) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Main Header cannot contain |:;");
			} else if (main_head.length() > 80) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, commonObj.charExceeded("Main Header", main_head, 80));
			} else if (commonObj.checkComma(add_head)) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Address Header cannot contain |:;");
			} else if (add_head.length() > 80) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, commonObj.charExceeded("Address Header", add_head, 80));
			} else if (commonObj.checkComma(short_name)) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Short Name cannot contain |:;");
			} else if (short_name.length() > 20) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, commonObj.charExceeded("Short Name", short_name, 80));
			}
			
			if(validateFields){
				if(dbValidate.connectDatabase(sessionData)){
					boolean addFlag = dbValidate.addReceiptHead(sessionData, inst_name, main_head, add_head, short_name, "insert");
					
					if(addFlag == true){
						frame.setVisible(false);
						CreateSchoolHead.getCreateSchoolHead(sessionData, academic);
					}
				}
			}
		} catch (Exception e1) {
			commonObj.logException(e1);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
	}
}
