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
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class FeesReport extends JFrame {

	private static FeesReport feesReport = new FeesReport();
	
	private static final long serialVersionUID = 1L;

	static int screenWidth;

    static int screenHeight;

    static int mainCentre;

    static JFrame frame = null;
    
    static String secName = "";

    static String academicYearClass = "";
    
    static String yearList = "";
    
    private static String reportType = "";
    
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
    
    static CreateExcel ce = new CreateExcel();
    
    static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

    static Logger logger = Logger.getLogger(FeesReport.class.getName());
    
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
    static String stdList = "";
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
    private static String fromDateClass = "";
    private static String toDateClass = "";
    private static String receiptShortName = "";
    private static String feeCategory = "", smsFooter = "";
    
    /*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private FeesReport() {
	}

	/* Static 'instance' method */
	public static FeesReport getInstance() {
		return feesReport;
	}
	
	/* Other methods protected by singleton-ness */
	protected static void getFeesReport(SessionData sessionData1, String retGr_no, String retStd, String retDiv, String retLastName,
			String retFirstName, String retFatherName, LinkedHashMap<String,LinkedHashMap<String, String>> retStudentMap, String sec, 
			String academicYear, String optional, String frequency, String subFrequency, boolean isAllSelected) {

    	System.gc();
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
//        setVisible(false);
//        dispose();
        
        frame = new JFrame("Welcome to "+sessionData1.getAppName());
        stdList = bundle.getString(section.toUpperCase() + "_STD");
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
        sessionData.setSectionFullName(secName);
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
    	smsFooter = bundle.getString("SMS_"+sessionData.getAppType()+"_FOOTER");
    	frequencyList =  bundle.getString("SEARCH_FREQUENCY");
    	reportType = bundle.getString("FEE_REPORT_TYPE");
    	
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
				feesHeadMapClass = dbValidate.getFeesHeadData(sessionData, academicYearClass, stdClass, section, optionalClass);
				
				if(!frequencyClass.equalsIgnoreCase("Part Pay")){
					studentPartialFeeMap = 
							dbValidate.getPartialFeesData(sessionData1, academicYearClass, sessionData.getSectionName(), stdClass, divClass, 
									optionalClass, frequencyClass, subFrequencyClass, feesHeadMapClass);
				}
				yearList = dbValidate.getAcademicYearList(sessionData, "HS_GENERAL_REGISTER", "ACADEMIC_YEAR").toString();
				if(!searchStudentMap.isEmpty()){
					scrollHeight = (searchStudentMap.size() - 6) * 30; // to adjust the perfect scroll height
				}
				
				feeCategory = dbValidate.getFeesCategoryList(sessionData1, academicYearClass, stdClass, section, "CATEGORY", "FEES_HEAD");
			}
		} catch (Exception e1) {
            logger.error("Exception while getting table order ==>>>" + e1);
        }
        
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
				new CreateFeesHead(sessionData, "", "", section);
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
		feeReportButton.setBackground(Color.GREEN);
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
        
 		// /////////////Std//////////////
 		final JRadioButton std_radio = new JRadioButton();
 		std_radio.setBounds(30, bottomBandItemHeight + 15, 20, 20);
 		std_radio.setSelected(true);
 		std_radio.setEnabled(false);
 		bottombandPanel.add(std_radio);
 		
 		JLabel admittedStd_label = new JLabel("Std :");
 		admittedStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		admittedStd_label.setBounds(60, bottomBandItemHeight, 70, 50);
 		bottombandPanel.add(admittedStd_label);

 		String dispStd = "All," + stdList;
 		String[] stdList = dispStd.split(",");
 		final JComboBox admittedStd_combo = new JComboBox(stdList);
 		admittedStd_combo.setSelectedItem(stdClass);
 		admittedStd_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		admittedStd_combo.setBounds(110, bottomBandItemHeight + 12, 90, 25);
 		bottombandPanel.add(admittedStd_combo);

 		// /////////////Div//////////////
 		JLabel admittedDiv_label = new JLabel("Div :");
 		admittedDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		admittedDiv_label.setBounds(210, bottomBandItemHeight, 70, 50);
 		bottombandPanel.add(admittedDiv_label);

 		String dispDiv = "All," + div;
 		String[] divListView = dispDiv.split(",");
 		final JComboBox admittedDiv_combo = new JComboBox(divListView);
 		admittedDiv_combo.setSelectedItem(divClass);
 		admittedDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		admittedDiv_combo.setBounds(260, bottomBandItemHeight + 12, 100, 25);
 		bottombandPanel.add(admittedDiv_combo);
 		
 		JLabel feeType_label = new JLabel("Fee Category :");
 		feeType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		feeType_label.setBounds(380, bottomBandItemHeight, 150, 50);
 		bottombandPanel.add(feeType_label);
 		
 		String[] feeCategoryList = feeCategory.split(",");
 		final JComboBox feesCatCombo = new JComboBox(feeCategoryList);
        feesCatCombo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        feesCatCombo.setBounds(490, bottomBandItemHeight + 15, 200, 25);
        bottombandPanel.add(feesCatCombo);
        
// 		JLabel feeType_label = new JLabel("Fee Optional :");
// 		feeType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
// 		feeType_label.setBounds(380, bottomBandItemHeight, 150, 50);
// 		bottombandPanel.add(feeType_label);
// 		
// 		String[] optionalList = {"No","Yes"};
// 		final JComboBox optional_combo = new JComboBox(optionalList);
// 		optional_combo.setSelectedItem(optionalClass);
// 		optional_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
// 		optional_combo.setBounds(500, bottomBandItemHeight + 12, 100, 25);
// 		bottombandPanel.add(optional_combo);
 		
 		// /////////ACADEMIV YEAR radio///////////////30, 80, 120, 50
 		JLabel academic_label = new JLabel("Academic :");
 		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		academic_label.setBounds(700, bottomBandItemHeight, 100, 50);
 		bottombandPanel.add(academic_label);

 		String[] academicYearList = yearList.split(",");
 		final JComboBox academicYear_combo = new JComboBox(academicYearList);
 		academicYear_combo.setSelectedItem(academicYearClass);
 		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		academicYear_combo.setBounds(790, bottomBandItemHeight+15, 90, 25);
 		bottombandPanel.add(academicYear_combo);
 		
 		// /////////Report category///////////////30, 80, 120, 50
 		JLabel report_label = new JLabel("Report Type :");
 		report_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		report_label.setBounds(890, bottomBandItemHeight, 120, 50);
 		bottombandPanel.add(report_label);

 		String[] reportTypeList = reportType.split(",");
 		final JComboBox reportType_combo = new JComboBox(reportTypeList);
// 		reportType_combo.setSelectedItem(academicYearClass);
 		reportType_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		reportType_combo.setBounds(1000, bottomBandItemHeight+15, 130, 25);
 		bottombandPanel.add(reportType_combo);
 		
 		bottomBandItemHeight = bottomBandItemHeight + 40;
 		
 		final JLabel from_label = new JLabel("From :");
		from_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		from_label.setBounds(60, bottomBandItemHeight, 120, 50);
		bottombandPanel.add(from_label);

		/****from date picker****/
		final UtilDateModel modelFrom = new UtilDateModel();
		if(!fromDateClass.equalsIgnoreCase("")){
			int dayFromDate = Integer.parseInt(fromDateClass.substring(0,fromDateClass.indexOf("/")));
			int monthFromDate = Integer.parseInt(fromDateClass.substring(fromDateClass.indexOf("/")+1,fromDateClass.lastIndexOf("/")));
			int yearFromDate = Integer.parseInt(fromDateClass.substring(fromDateClass.lastIndexOf("/")+1));
			modelFrom.setDate(yearFromDate,monthFromDate-1,dayFromDate);
//			modelFrom.setDate(2014,04,24);
		}
		modelFrom.setSelected(true);
	    Properties pFrom = new Properties();
	    pFrom.put("text.today", "Today");
	    pFrom.put("text.month", "Month");
	    pFrom.put("text.year", "Year");
	    final JDatePanelImpl datePanelFrom = new JDatePanelImpl(modelFrom, pFrom);
	    datePanelFrom.setBounds(5, bottomBandItemHeight+12, 130, 26);
	    final JDatePickerImpl datePickerFrom = new JDatePickerImpl(datePanelFrom, new DateLabelFormatter());
	    datePickerFrom.setBounds(110, bottomBandItemHeight+12, 130, 26);
	    bottombandPanel.add(datePickerFrom);

        //////////////////To/////////////////
		final JLabel to_label = new JLabel("To :");
		to_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		to_label.setBounds(250, bottomBandItemHeight, 70, 50);
		bottombandPanel.add(to_label);

		/****to date picker****/
		final UtilDateModel modelTo = new UtilDateModel();
		if(!toDateClass.equalsIgnoreCase("")){
			int dayToDate = Integer.parseInt(toDateClass.substring(0,toDateClass.indexOf("/")));
			int monthToDate = Integer.parseInt(toDateClass.substring(toDateClass.indexOf("/")+1,toDateClass.lastIndexOf("/")));
			int yearToDate = Integer.parseInt(toDateClass.substring(toDateClass.lastIndexOf("/")+1));
			modelTo.setDate(yearToDate,monthToDate-1,dayToDate);
//			modelTo.setDate(2014,04,24);
		}
	    // Need this...
		modelTo.setSelected(true);
	    Properties pTo = new Properties();
	    pTo.put("text.today", "Today");
	    pTo.put("text.month", "Month");
	    pTo.put("text.year", "Year");
	    final JDatePanelImpl datePanelTo = new JDatePanelImpl(modelTo, pTo);
	    datePanelTo.setBounds(210, bottomBandItemHeight+12, 130, 26);
	    // Don't know about the formatter, but there it is...
	    final JDatePickerImpl datePickerTo = new JDatePickerImpl(datePanelTo, new DateLabelFormatter());
	    datePickerTo.setBounds(285, bottomBandItemHeight+12, 130, 26);
	    bottombandPanel.add(datePickerTo);
 		
 		// /////////////Submit//////////////
 		JButton submitButton = new JButton("Submit");
 		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		submitButton.setBounds(700, bottomBandItemHeight+15, 130, 25);
 		bottombandPanel.add(submitButton);
 		
 		String smsbuttonName = "";
 		if(bundle.getString("DAILY_ADMIN_SMS").equalsIgnoreCase("true") && 
 				bundle.getString("STAFF_FEE_REPORT_SMS").equalsIgnoreCase("true")) {
 			smsbuttonName = "Sms to Admin/Staff";
 		} else if(bundle.getString("DAILY_ADMIN_SMS").equalsIgnoreCase("true")) {
 			smsbuttonName = "Sms to Admin";
 		} else if(bundle.getString("STAFF_FEE_REPORT_SMS").equalsIgnoreCase("true")) {
 			smsbuttonName = "Sms to Staff";
 		}
 		JButton smsAdminButton = new JButton(smsbuttonName);
 		smsAdminButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		smsAdminButton.setBounds(850, bottomBandItemHeight+15, 200, 25);
 		if((bundle.getString("DAILY_ADMIN_SMS").equalsIgnoreCase("true") || 
 				bundle.getString("STAFF_FEE_REPORT_SMS").equalsIgnoreCase("true")) && 
 				bundle.getString("SMS_FEE_FLAG").equalsIgnoreCase("true")){
 			bottombandPanel.add(smsAdminButton);
 		}
 		
 		JSeparator jSeparator1  = new JSeparator();
        jSeparator1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        jSeparator1.setBounds(40, bottomBandItemHeight+50, (screenWidth - 200), 50);
        bottombandPanel.add(jSeparator1);
        
        submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String std = (String) admittedStd_combo.getSelectedItem();
				String div = (String) admittedDiv_combo.getSelectedItem();
				String academic = (String) academicYear_combo.getSelectedItem();
				String category = (String) feesCatCombo.getSelectedItem();
				boolean stdRadio = std_radio.isSelected();
				Date selectedFromDate = (Date) datePickerFrom.getModel().getValue();
				Date selectedToDate = (Date) datePickerTo.getModel().getValue();
		        if(selectedFromDate.after(selectedToDate)) {
					JOptionPane.showMessageDialog(null, "From date cannot be greater than To date.");
				}
				else {
					String fromDate = commonObj.dateToDDMMYYYY(selectedFromDate);
			        String toDate = commonObj.dateToDDMMYYYY(selectedToDate);
			        String reportTypeSel = reportType_combo.getSelectedItem().toString();
			        
					submitAction(sessionData, std, div, academic, category, stdRadio, fromDate, toDate, reportTypeSel);
				}
			}

		});
        
        smsAdminButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String todayDate = commonObj.getCurrentDate();
				String period = "", smsText = "", smsTemplateId = "", fromDate = "", toDate = "", optionSelect = "", 
						smsType = "", stdStr = "";
				String std = (String) admittedStd_combo.getSelectedItem();
				String div = (String) admittedDiv_combo.getSelectedItem();
				String academic = (String) academicYear_combo.getSelectedItem();
				List<String> passGrList = new ArrayList();
				LinkedHashMap foundStudentMap = new LinkedHashMap<>();
				double totalAmount = 0;
				boolean isValid = true;
				String optionAdminStaff = "";
				
				if(bundle.getString("DAILY_ADMIN_SMS").equalsIgnoreCase("true") && 
		 				bundle.getString("STAFF_FEE_REPORT_SMS").equalsIgnoreCase("true")) {
					optionAdminStaff = JOptionPane.showInputDialog("=== Send SMS === \n Please Enter option \n 1 : To Admin "
							+ " \n 2 : To Staff");
		 		} else if(bundle.getString("DAILY_ADMIN_SMS").equalsIgnoreCase("true")) {
		 			optionAdminStaff = "1";
		 		} else if(bundle.getString("STAFF_FEE_REPORT_SMS").equalsIgnoreCase("true")) {
		 			optionAdminStaff = "2";
		 		}
				
				if(optionAdminStaff != null && !optionAdminStaff.trim().equalsIgnoreCase("null")) {
					if(optionAdminStaff.equalsIgnoreCase("1")){
						optionSelect = JOptionPane.showInputDialog("===Send SMS to Admin=== \n Please Enter option \n 1 : Todays Collection "
								+ " \n 2 : This Week Collection \n 3 : This Month Collection \n 4 : Selected Date Range");
						
						if(optionSelect != null && !optionSelect.trim().equalsIgnoreCase("null")){
							if(optionSelect.equalsIgnoreCase("1")){
								period = "Today's";
							} else if(optionSelect.equalsIgnoreCase("2")){
								period = "This week's";
							} else if(optionSelect.equalsIgnoreCase("3")){
								period = "This month's";
							} else if(optionSelect.equalsIgnoreCase("4")){
								Date selectedFromDate = (Date) datePickerFrom.getModel().getValue();
								Date selectedToDate = (Date) datePickerTo.getModel().getValue();
						        if(selectedFromDate.after(selectedToDate)) {
						        	isValid = false;
									JOptionPane.showMessageDialog(null, "From date cannot be greater than To date.");
								}
								else {
								    fromDate = commonObj.dateToYYYYMMDD(selectedFromDate);
							        toDate = commonObj.dateToYYYYMMDD(selectedToDate);
							        period = fromDate + " to " + toDate;
								}
							}
							
							smsType = "SMS_FEE_ADMIN";
							dbValidate.connectDatabase(sessionData);
							totalAmount = dbValidate.getFeesCollection(sessionData, optionSelect, fromDate, toDate, std, div, academic);
							totalAmount = Math.round(totalAmount*100.0)/100.0;
							smsText = "Fees Report\n"+period + " fees collection amount is Rs."+totalAmount+"\nBy "+smsFooter;
							smsTemplateId = bundle.getString("SMS_FEE_COLLECTION_REPORT_TEMP_ID");
							if(isValid) {
								String smsResponse = commonObj.sendHspSms(sessionData, passGrList, foundStudentMap, smsText, smsTemplateId, section, "", 
										academicYearClass, "", "", "", smsType);
							}
						}
						else {
							isValid = false;
							JOptionPane.showMessageDialog(null, "Please enter valid option.");
						}
						
					} else if(optionAdminStaff.equalsIgnoreCase("2")){
						if(bundle.getString("STAFF_FEE_REPORT_SMS").equalsIgnoreCase("true") && 
				 				bundle.getString("SMS_FEE_FLAG").equalsIgnoreCase("true")){
							
							optionSelect = JOptionPane.showInputDialog("===Send SMS to Staff=== \n Please Enter option \n 1 : Todays Collection "
									+ " \n 2 : This Week Collection \n 3 : This Month Collection \n 4 : Selected Date Range");
								
							if(optionSelect != null && !optionSelect.trim().equalsIgnoreCase("null")){
								LinkedHashMap<String,String> dateMap = new LinkedHashMap<String,String>();
								dateMap = commonObj.getDateForADayInWeek(sessionData);
								
								if(optionSelect.equalsIgnoreCase("1")){
									fromDate = toDate = commonObj.dateToYYYYMMDD(new Date());
									period = "Today's";
								} else if(optionSelect.equalsIgnoreCase("2")){
									fromDate = dateMap.get("firstDay");
									toDate = dateMap.get("lastDay");
									period = "This week's";
								} else if(optionSelect.equalsIgnoreCase("3")){
									fromDate = dateMap.get("firstDayOfMonth");
									toDate = dateMap.get("lastDayOfMonth");
									period = "This month's";
								} else if(optionSelect.equalsIgnoreCase("4")){
									Date selectedFromDate = (Date) datePickerFrom.getModel().getValue();
									Date selectedToDate = (Date) datePickerTo.getModel().getValue();
							        if(selectedFromDate.after(selectedToDate)) {
							        	isValid = false;
										JOptionPane.showMessageDialog(null, "From date cannot be greater than To date.");
									}
									else {
									    fromDate = commonObj.dateToYYYYMMDD(selectedFromDate);
								        toDate = commonObj.dateToYYYYMMDD(selectedToDate);
								        period = fromDate + " to " + toDate;
									}
								}
								
								smsType = "SMS_FEE_STAFF";
								dbValidate.connectDatabase(sessionData);
								totalAmount = dbValidate.getFeesCollection(sessionData, optionSelect, fromDate, toDate, std, div, academic);
								totalAmount = Math.round(totalAmount*100.0)/100.0;
								String smsSchoolStr = bundle.getString("SMS_"+sessionData.getAppType());
								String smsCondition = "", totalStr = " total";
								if(!std.equalsIgnoreCase("All") && !std.equalsIgnoreCase("")) {
									smsCondition = " STD="+std+"";
								}
								if(!div.equalsIgnoreCase("All") && !div.equalsIgnoreCase("")) {
									smsCondition = smsCondition + " DIV="+div+"";
								}
								
								if(smsSchoolStr.equalsIgnoreCase("") && smsCondition.equalsIgnoreCase("")) {
									totalStr = "Total";
								}
								
								smsText = "Fees Report\n" + smsSchoolStr + smsCondition + totalStr + " fees collection is Rs. "+totalAmount+ " from date "+fromDate+ " to "+toDate+"\nBy "+smsFooter;
								smsTemplateId = bundle.getString("SMS_FEE_TOTAL_COLLECTION_TEMP_ID");
								if(isValid) {
									String smsResponse = commonObj.sendHspStaffFeeSms(sessionData, passGrList, foundStudentMap, smsText, smsTemplateId, section, "", 
											academicYearClass, std, div, "", smsType);
									if(smsResponse.contains("Success")) {
										JOptionPane.showMessageDialog(null, smsResponse);
									}
									else {
										JOptionPane.showMessageDialog(null, smsResponse);
									}
								}
							}
							else {
								isValid = false;
								JOptionPane.showMessageDialog(null, "Please enter valid option.");
							}
						}
						else {
							isValid = false;
							JOptionPane.showMessageDialog(null, "Fee SMS to Staff is not enabled");
						}
					}
				}
			}
        });
        
        submitButton.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
            int keyCode=e.getKeyCode();
            if(keyCode == 10){
				String std = (String) admittedStd_combo.getSelectedItem();
				String div = (String) admittedDiv_combo.getSelectedItem();
				/*if(std.equalsIgnoreCase("All") || !std_radio.isSelected()){
					std = "";
				}*/
				/*if(div.equalsIgnoreCase("All") || !std_radio.isSelected()){
					div = "";
				}*/
				String academic = (String) academicYear_combo.getSelectedItem();
				String category = (String) feesCatCombo.getSelectedItem();
				boolean stdRadio = std_radio.isSelected();
				Date selectedFromDate = (Date) datePickerFrom.getModel().getValue();
				Date selectedToDate = (Date) datePickerTo.getModel().getValue();
				
				if(selectedFromDate.after(selectedToDate)) {
					JOptionPane.showMessageDialog(null, "From date cannot be greater than To date.");
				}
				else {
					String fromDate = commonObj.dateToDDMMYYYY(selectedFromDate);
			        String toDate = commonObj.dateToDDMMYYYY(selectedToDate);
			        String reportTypeSel = reportType_combo.getSelectedItem().toString();
			        
					submitAction(sessionData, std, div, academic, category, stdRadio, fromDate, toDate, reportTypeSel);
				}
              }
            }
        });
        
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

        reportType_combo.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent e) {

 				String reportTypeSel = reportType_combo.getSelectedItem().toString();
 				String stdSel = admittedStd_combo.getSelectedItem().toString();
 				String divSel = admittedDiv_combo.getSelectedItem().toString();
 				
 				if(reportTypeSel.equalsIgnoreCase("Daily")){
 					bottombandPanel.add(from_label);
 					bottombandPanel.add(datePickerFrom);
 					bottombandPanel.add(to_label);
 					bottombandPanel.add(datePickerTo);
 				}
 				else if(reportTypeSel.equalsIgnoreCase("Classwise") || reportTypeSel.equalsIgnoreCase("Fee Abstract")){
 					bottombandPanel.add(from_label);
 					bottombandPanel.add(datePickerFrom);
 					bottombandPanel.add(to_label);
 					bottombandPanel.add(datePickerTo);
 				}
 				else if(reportTypeSel.equalsIgnoreCase("Consolidate")){
 					bottombandPanel.remove(from_label);
 					bottombandPanel.remove(datePickerFrom);
 					bottombandPanel.remove(to_label);
 					bottombandPanel.remove(datePickerTo);
 					if(!stdSel.equalsIgnoreCase("All") && !divSel.equalsIgnoreCase("All")){
 						JOptionPane.showMessageDialog(null, "For Consolidate report select All in std & div");
 					}
 				}
 			}
 		});
        
 		std_radio.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent e) {

 				if(std_radio.isSelected()){
 					admittedStd_combo.setEnabled(true);
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
 					
 					if(!stdSel.equalsIgnoreCase("") && !stdSel.equalsIgnoreCase("Select") && dbValidate.connectDatabase(sessionData)) {
	 					try {
						feeCategory = dbValidate.getFeesCategoryList(sessionData, acadSel, stdSel, section, "CATEGORY", "FEES_HEAD");
	 						if(!feeCategory.isEmpty()) {
	 							feesCatCombo.removeAllItems();
	 							String[] categoryList = feeCategory.split(",");
	 							for (int z = 0; z < categoryList.length; z++) {
	 								feesCatCombo.addItem(categoryList[z]);
	 							}
	 						}
	 					} catch (Exception e1) {
	 			            commonObj.logException(e1);
	 			        }
	 				}
 				} catch (Exception e1) {
 					logger.info("Exception insertFormData ===>>>" + e1);
 				} finally {
 					dbValidate.closeDatabase(sessionData);
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
						
						if(!feesHeadMapClass.isEmpty()){
							
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
									frequencyClass, subFrequencyClass, feesPaymentMap, totalAmount, "", "", feesHeadMapClass, feesForMonth, 0.0, 0.0);
							
							JOptionPane.showMessageDialog(null, "Payment updated successfully");
							LinkedHashMap emptyConcessionMap = new LinkedHashMap<>();
							FeeReceiptPDF.getFeeReceiptPDF(sessionData, feesPaymentMap, "0.00", "", "", "", "", "", stdClass, 
									divClass, academicYearClass, "Pending", totalAmount, "", emptyConcessionMap, 0, 
									optionalClass, 0, "", feesForMonth, selectedStudentMap, false, null, receiptShortName, null, 
									frequencyClass, "", "", "", 0, 0);
							
							/*frame.setVisible(false);
							new FeesView(sessionData, grNoClass, stdClass, divClass, nameClass, rollNoClass, searchStudentMap, section, academicYearClass, 
									optionalClass, feesHeadMap, maxFrequencyClass+"", frequencyClass, subFrequencyClass);*/
							
							totalAmount = 0;
						}
						else{
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
        if(!frequencyClass.equalsIgnoreCase("Part Pay")  && !stdClass.equalsIgnoreCase("")){
        	bottombandPanel.add(radioAll);
		}
        
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
					FeesReport.getFeesReport(sessionData, "", stdClass, divClass, "", "", "", searchStudentMap, section, academicYearClass, 
							optionalClass, frequencyClass, subFrequencyClass, radioAllSel);
				}
				else{
					frame.setVisible(false);
					FeesReport.getFeesReport(sessionData, "", stdClass, divClass, "", "", "", searchStudentMap, section, academicYearClass, 
							optionalClass, frequencyClass, subFrequencyClass, radioAllSel);
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
	        final JButton[] pay_buttons = new JButton[searchStudentMap.size()];
	        final JComboBox[] mode_combo = new JComboBox[searchStudentMap.size()];
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
	        String nameFromMap = "";
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

				itemWidth = 20;
				student_radio[k] = new JRadioButton();
				student_radio[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				student_radio[k].setBorder(null);
				student_radio[k].setToolTipText(stdDivFromMap);
				student_radio[k].setBounds(itemWidth-10, j+5, 12, 12);
				if(!frequencyClass.equalsIgnoreCase("Part Pay") && !stdClass.equalsIgnoreCase("") && !studentPartialFeeMap.containsKey(grNoMap)){
					dataPanel.add(student_radio[k]);
				}
				
				if(isAllSelectedClass && !studentPartialFeeMap.containsKey(grNoMap)){
					LinkedHashMap studentDetailMap = new LinkedHashMap();
					studentDetailMap.put("grNo", grNoMap);
					studentDetailMap.put("rollNo", rollNoMap);
					studentDetailMap.put("name", nameFromMap);
					studentDetailMap.put("std", stdMap);
					studentDetailMap.put("div", divMap);
					studentDetailMap.put("paymentMode", "Cash");
					selectedStudentMap.put(grNoMap, studentDetailMap);
					student_radio[k].setSelected(true);
				}
	        	
	        	itemWidth = itemWidth + 20;
				rollNo_labels[k] = new JLabel(rollNoMap);
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
	    		
	    		itemWidth = itemWidth + 360;
	    		pay_buttons[k] = new JButton("Pay");
	    		pay_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		pay_buttons[k].setBounds(itemWidth, j, 90, 25);
	    		
	    		mode_combo[k] = new JComboBox(modeList);
	            mode_combo[k].setBorder(null);
	            mode_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	            mode_combo[k].setBounds(itemWidth, j, 90, 25);
	            
	            itemWidth = itemWidth + 100;
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
	            
	    		if(frequencyClass.equalsIgnoreCase("Part Pay") || stdClass.equalsIgnoreCase("") || studentPartialFeeMap.containsKey(grNoMap)){
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

						payAction(rollNoSel, grNoSel, nameSel, stdSel, divSel);
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
						
						if(radioSel){
							
							LinkedHashMap studentDetailMap = new LinkedHashMap();
							String paymentMode = mode_combo[m].getSelectedItem().toString();
							
							studentDetailMap.put("grNo", grNo);
							studentDetailMap.put("rollNo", rollNo);
							studentDetailMap.put("name", name);
							studentDetailMap.put("std", std);
							studentDetailMap.put("div", div);
							studentDetailMap.put("paymentMode", paymentMode);
							
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

						payAction(rollNoSel, grNoSel, nameSel, stdSel, divSel);
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
    
    private static void submitAction(SessionData sessionData, String std, String div, String academic, String category,
    		boolean std_radio, String fromDate, String toDate, String reportTypeSel){
    	
    	boolean validateFields = true;
    	Common commonObj = new Common();
		
    	if(reportTypeSel.equalsIgnoreCase("Classwise") && (std.equalsIgnoreCase("All") || div.equalsIgnoreCase("All"))){
			JOptionPane.showMessageDialog(null, "For Classwise report don't select All in std/div");
			validateFields = false;
		}
    	else if(reportTypeSel.equalsIgnoreCase("Consolidate") && (!std.equalsIgnoreCase("All") || !div.equalsIgnoreCase("All"))){
			JOptionPane.showMessageDialog(null, "For Consolidate report select All in std & div");
			validateFields = false;
		}
		
		LinkedHashMap<String,LinkedHashMap<String, String>> studentMap;
		if (validateFields) {
			try {
				if(dbValidate.connectDatabase(sessionData)){
					if(reportTypeSel.equalsIgnoreCase("Daily")){
						dbValidate.getDailyFeesReport(sessionData, academic, std, div, category, fromDate, toDate);
//						dbValidate.exportFeesDataToReport(sessionData, academic, std, div, category, fromDate, toDate);
					}
					else if(reportTypeSel.equalsIgnoreCase("Classwise")){
						LinkedHashMap<String, LinkedHashMap<String, String>> feesHeadMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
						
						feesHeadMap = dbValidate.getFeesHeadData(sessionData, academic, std, section, category);
						
						if(!feesHeadMap.isEmpty()){
							dbValidate.getClasswiseFeeReport(sessionData, academic, std, div, category, feesHeadMap, "Classwise");
						}
						else{
							JOptionPane.showMessageDialog(null, "Fees Head with category "+category+" for Std "+stdClass+" does not exist.");
						}
					}
					else if(reportTypeSel.equalsIgnoreCase("Fee Abstract")){
						dbValidate.getFeesAbstractReport(sessionData, academic, std, div, category, fromDate, toDate);
					}
					else if(reportTypeSel.equalsIgnoreCase("Consolidate")){
						dbValidate.getConsolidateFeeReport(sessionData, academic, std, div, category);
					}
					else if(reportTypeSel.equalsIgnoreCase("Defaulter")){
						LinkedHashMap<String, LinkedHashMap<String, String>> feesHeadMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
						List<String> studentReportList = new ArrayList<String>();
						
						if(!std.equalsIgnoreCase("All")) {
							stdList = std;
						}
						for (String retStd: stdList.split(",")) {
							feesHeadMap = dbValidate.getFeesHeadData(sessionData, academic, retStd, section, category);
							if(!feesHeadMap.isEmpty()){
								studentReportList.addAll(dbValidate.getDefaulterFeeReport(sessionData, academic, std, div, category, feesHeadMap, "Defaulter", ""));
							}
							feesHeadMap.clear();
			 			}
						
						if(studentReportList.size() > 3){
							ce.generateExcel(sessionData, "FEE REPORT", "FEE_REPORT_"+reportTypeSel.replace(" ", "_")+"_"+academic+"_", "", 
									studentReportList, true, reportTypeSel+" Report for  STD: "+std+"   DIV: "+div+"    Year:"+academic+ "   Category:"+category+ "   "+sessionData.getSectionFullName(), 1);	
						}
						else{
							JOptionPane.showMessageDialog(null, "No Data found..");
						}
						studentReportList.clear();
					}
				}
			} catch (Exception e1) {
				commonObj.logException(e1);
				frame.setVisible(false);
				LinkedHashMap<String,LinkedHashMap<String, String>> studentMapEmpty =  new LinkedHashMap<>();
				FeesReport.getFeesReport(sessionData, "", "", "", "", "", "", studentMapEmpty, section, "", "", "", "", false);
			}
		}
	}
    
    private static void payAction(String rollNoSel, String grNoSel, String nameSel, String stdSel, String divSel){
    	try {
			
			LinkedHashMap<String, LinkedHashMap<String, String>> feesHeadMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
			
			if(dbValidate.connectDatabase(sessionData)){
				feesHeadMap = dbValidate.getFeesHeadData(sessionData, academicYearClass, stdClass, section, optionalClass);
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
				
				frame.setVisible(false);
				new FeesView(sessionData, grNoSel, stdSel, divSel, nameSel, rollNoSel, searchStudentMap, section, academicYearClass, 
						optionalClass, feesHeadMap, maxFrequency, frequencyClass, subFrequencyClass, "", "", "", "", false);
			}
			else{
				JOptionPane.showMessageDialog(null, "Fees Head with optional "+optionalClass+" for Std "+stdClass+" does not exist.");
			}
		} catch (Exception e1) {
			logger.info("Exception insert fee name ===>>>" + e1);
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
			
			receiptShortName = ((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("short_name").toString();
			
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
