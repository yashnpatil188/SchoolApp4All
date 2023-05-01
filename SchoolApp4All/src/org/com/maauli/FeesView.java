package org.com.maauli;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.sun.org.apache.xerces.internal.impl.dtd.models.CMAny;

public class FeesView extends JFrame {

	private static final long serialVersionUID = 1L;

	static int screenWidth;

    static int screenHeight;

    static int mainCentre;

    static JFrame frame = null;
    
    static String secName = "";

    static String academicYearClass = "";
    
    static String yearList = "";
    
    static String img_path = "";

    static String img_home = "";
    
//    static String img_founder = "";

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

    static Logger logger = Logger.getLogger(FeesView.class.getName());
    
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
    static String grNoClass = "";
    static String nameClass = "";
    static String contact1Class = "";
    static String contact2Class = "";
    static String rollNoClass = "";
    static String selectStd = "";
    static String stdClass = "";
//    static String optionalClass = "";
    static String categoryClass = "";
    static String paymentModeList = "";
    private static double totalAmount = 0;
    private static double concessionAmount = 0, totalConcessionPaid = 0;
    private static int maxFrequencyClass = 0;
    private static String lastMonth = "";
    private static String frequencyClass = "";
    private static String subFrequencyClass = "";
    private static ArrayList<Integer> feesForMonthsList;
    private static TreeMap<Integer, String> receiptMap;
    static LinkedHashMap<String, LinkedHashMap<String, String>> searchStudentMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    static LinkedHashMap<String, LinkedHashMap<String, String>> feesHeadMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    static LinkedHashMap<String, String> studentFeesMap = new LinkedHashMap<String, String>();
    static LinkedHashMap<String,LinkedHashMap<String,String>> studentOptMap = new LinkedHashMap<String,LinkedHashMap<String,String>>();
    static LinkedHashMap<String,LinkedHashMap<String,String>> multiFeeHeadMap = new LinkedHashMap<String,LinkedHashMap<String,String>>();
    static LinkedHashMap<String, LinkedHashMap<String, String>> feesPaymentMap;
    static LinkedHashMap concessionMap;
    static int scrollHeight = 0;
    static double totalBalanceFromDB = 0;
    private static int startMonth = Integer.parseInt(bundle.getString("ACADEMIC_START_MONTH"));
    private static String oldAcademicClass = "";
    private static String oldStdClass = "";
    private static boolean headerRadioClass = false;
    private static String optionalFee = "";
    private static String receiptShortName = "";
    
    public FeesView(SessionData sessionData1, String retGr_no, String retStd, String retDiv, String retName, String retRollNo,
    		LinkedHashMap<String,LinkedHashMap<String, String>> retStudentMap, String sec, String academicYear, String category,
    		LinkedHashMap<String,LinkedHashMap<String, String>> retFeesHeadMap, String maxFrequency, String frequency, 
    		String subFrequency, String contact1, String contact2, String oldAcademic, String oldStd, boolean headerRadio) {

    	System.gc();
    	totalBalanceFromDB = 0;
    	receiptShortName = "";
    	section = sec;
    	lastMonth = "";
    	oldAcademicClass = oldAcademic;
    	headerRadioClass = headerRadio;
    	oldStdClass = oldStd;
    	frequencyClass = frequency;
    	subFrequencyClass = subFrequency;
    	maxFrequencyClass = Integer.parseInt(maxFrequency);
    	feesForMonthsList = new ArrayList<Integer>();
    	receiptMap = new TreeMap<Integer, String>();
    	feesPaymentMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    	concessionMap = new LinkedHashMap();
    	feesHeadMap = retFeesHeadMap;
    	totalAmount = 0;
    	concessionAmount = 0;
    	selectStd = "Select";
    	grNoClass = retGr_no;
    	nameClass = retName;
    	contact1Class = contact1;
    	contact2Class = contact2;
    	rollNoClass = retRollNo;
    	categoryClass = category;
    	searchStudentMap = retStudentMap;
    	stdClass = retStd;
    	academicYearClass = academicYear;
    	sessionData = sessionData1;
    	if (retStd.equalsIgnoreCase(""))
			stdClass = "Select";
		else
			stdClass = retStd;

		if (retDiv.equalsIgnoreCase(""))
			divClass = "Select";
		else
			divClass = retDiv;
        user_name = sessionData1.getUserName();
        user_role = sessionData1.getUserRole();
        setVisible(false);
        dispose();
        frame = new JFrame("Welcome to "+sessionData1.getAppName());
        img_path = bundle.getString("IMAGE_PATH");
        logger.info("img_path :: " + img_path);
        img_home = bundle.getString("IMAGE_HOME");
//        img_founder = bundle.getString("IMGAGE_FOUNDER");
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
        paymentModeList = bundle.getString("PAYMENT_MODE");
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
        
        if(academicYearClass.trim().equalsIgnoreCase("")){
        	String todayDate = commonObj.getCurrentDate();
            academicYearClass = commonObj.getAcademicYear(todayDate);
        }
        
        try {
			if(dbValidate.connectDatabase(sessionData)){
				yearList = dbValidate.getAcademicYearList(sessionData, "HS_GENERAL_REGISTER", "ACADEMIC_YEAR");
				studentFeesMap = dbValidate.getStudentFeesData(sessionData, academicYearClass, stdClass, divClass, grNoClass, 
						categoryClass, feesHeadMap);
				String concessionStr = studentFeesMap.get("CONCESSION_PERCENT") == null ? "0" : studentFeesMap.get("CONCESSION_PERCENT").trim();
				
//				dbValidate.deleteDuplicateData(sessionData1, academicYear, stdClass, divClass, "fees_data_mandatory", "STD_1", "DIV_1");
				/*if(!concessionStr.equalsIgnoreCase("0"))
				{
					concessionStr = concessionStr.substring(1, concessionStr.length()-1);
					String[] concessionArr = concessionStr.split(",");
					String concessionValue = "";
					for(int i = 0; i < concessionArr.length; i++){
						concessionValue = concessionArr[i].trim();
						concessionMap.put(concessionValue.substring(0, concessionValue.indexOf("=")), concessionValue.substring(concessionValue.lastIndexOf("=")+1));
					}
				}*/
				scrollHeight = (feesHeadMap.size() - 6) * 30; // to adjust the perfect scroll height
				studentOptMap = dbValidate.insertNewFeeAllotStudents(sessionData1, retStd, retDiv, academicYearClass, "", "");
				multiFeeHeadMap = dbValidate.fetchMultipleHeadMap(sessionData, academicYearClass, stdClass, "");
				
				///logic to get list of receipt numbers///
				receiptMap = dbValidate.getFeesReceiptList(sessionData1, academicYearClass, stdClass, divClass, grNoClass, feesHeadMap);
			}
		} catch (Exception e1) {
            commonObj.logException(e1);
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
            	frame.setVisible(false);
                new SearchFeeStudentNew(sessionData, "", "", "", "", "", "", studMap, section, "", categoryClass, frequencyClass, "", false, "", "", headerRadioClass);
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
		
		JLabel viewFeeTitle = new JLabel("View Student Fee Details");
		viewFeeTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		viewFeeTitle.setForeground(Color.orange);
		viewFeeTitle.setBounds(screenWidth/2-250, 0, 400, 30);
		topbandPanel.add(viewFeeTitle);
		
		JLabel subMenuTitle = new JLabel("Fee");
		subMenuTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		subMenuTitle.setForeground(Color.orange);
		subMenuTitle.setBounds(20, 45, 100, 30);
		topbandPanel.add(subMenuTitle);
        
		int width = 80;
		JButton backToSearchButton = new JButton("Back to Search");
		backToSearchButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		backToSearchButton.setBounds(width, 50, 170, 24);
		topbandPanel.add(backToSearchButton);

		backToSearchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new SearchFeeStudentNew(sessionData, "", stdClass, divClass, "", "", "", searchStudentMap, section, academicYearClass, categoryClass, 
						frequencyClass, subFrequencyClass, false, oldAcademicClass, oldStdClass, headerRadioClass);
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
        
        ////////////////////////////////////////// 
        
        int monthColumns = 12;
//		if(optionalClass.equalsIgnoreCase("Yes")){
//			monthColumns = 1;
//		}
		
        width = 900;
        JLabel totalPaid_label = new JLabel("Total Paid : "+(studentFeesMap.get("TOTAL_AMOUNT") == null ? "0" : studentFeesMap.get("TOTAL_AMOUNT").trim()));
 		totalPaid_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		totalPaid_label.setBounds(width+50, bottomBandItemHeight, 400, 50);
 		bottombandPanel.add(totalPaid_label);
 		
 		totalConcessionPaid = Double.parseDouble(studentFeesMap.get("CONCESSION_AMOUNT") == null ? "0" : studentFeesMap.get("CONCESSION_AMOUNT").trim());
 		JLabel concessionPaid_label = new JLabel("Total Concession : "+totalConcessionPaid);
 		concessionPaid_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		concessionPaid_label.setBounds(width, bottomBandItemHeight+20, 400, 50);
 		bottombandPanel.add(concessionPaid_label);
 		
 		JLabel totalPenalty_label = new JLabel("Total Penalty : "+(studentFeesMap.get("PENALTY_AMOUNT") == null ? "0.0" : studentFeesMap.get("PENALTY_AMOUNT").trim()));
 		totalPenalty_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		totalPenalty_label.setBounds(width+30, bottomBandItemHeight+40, 400, 50);
 		bottombandPanel.add(totalPenalty_label);
 		
 		String balanceMessage = "";
 		totalBalanceFromDB = Double.parseDouble((studentFeesMap.get("BALANCE_AMOUNT") == null ? "0.0" : studentFeesMap.get("BALANCE_AMOUNT").trim()));
 		if(totalBalanceFromDB >= 0) {
 			balanceMessage = "   Balance Due  : ";
		}
		else if(totalBalanceFromDB < 0) {
			balanceMessage = "Balance Owed  : ";
		}
 		JLabel totalBalance_label = new JLabel(balanceMessage + Math.abs(totalBalanceFromDB));
 		totalBalance_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		totalBalance_label.setBounds(width+20, bottomBandItemHeight+60, 400, 50);
 		bottombandPanel.add(totalBalance_label);
 		
        width = 40;
        JLabel name_label = new JLabel("Name : ");
 		name_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		name_label.setBounds(width, bottomBandItemHeight, 400, 50);
 		bottombandPanel.add(name_label);
 		
 		width = width + 60;
 		JLabel nameDisplay_label = new JLabel(nameClass);
 		nameDisplay_label.setFont(new Font("Book Antiqua", Font.BOLD, 21));
 		nameDisplay_label.setBounds(width, bottomBandItemHeight, 800, 50);
 		bottombandPanel.add(nameDisplay_label);
 		
 		width = 40;
 		bottomBandItemHeight = bottomBandItemHeight + 30;
 		JLabel rollNo_label = new JLabel("Roll No. : "+rollNoClass);
 		rollNo_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		rollNo_label.setBounds(width, bottomBandItemHeight, 200, 50);
 		bottombandPanel.add(rollNo_label);
 		
 		width = width + 120;
 		JLabel grNo_label = new JLabel("Gr No. : "+grNoClass);
 		grNo_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		grNo_label.setBounds(width, bottomBandItemHeight, 200, 50);
 		bottombandPanel.add(grNo_label);

 		width = width + 150;
 		JLabel std_label = new JLabel("Std : "+stdClass);
 		std_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		std_label.setBounds(width, bottomBandItemHeight, 100, 50);
 		bottombandPanel.add(std_label);
 		
 		width = width + 100;
 		JLabel div_label = new JLabel("Div : "+divClass);
 		div_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		div_label.setBounds(width, bottomBandItemHeight, 100, 50);
 		bottombandPanel.add(div_label);
 		
 		width = 40;
 		bottomBandItemHeight = bottomBandItemHeight + 30;
 		JLabel academicYear_label = new JLabel("Academic Year : "+academicYearClass);
 		academicYear_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		academicYear_label.setBounds(width, bottomBandItemHeight, 200, 50);
 		bottombandPanel.add(academicYear_label);
 		
 		width =  width + 200;
 		JLabel feeType_label = new JLabel("Fee Category : "+categoryClass);
 		feeType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		feeType_label.setBounds(width, bottomBandItemHeight, 200, 50);
 		bottombandPanel.add(feeType_label);
 		
 		JSeparator jseparator = new JSeparator();
 		jseparator.setFont(new Font("Book Antiqua", Font.BOLD, 18));
 		jseparator.setBounds(30, bottomBandItemHeight + 40, (screenWidth - 200), 50);
 		bottombandPanel.add(jseparator);
        
        /////start Data headers////////////////
        int itemWidth = 40;
        bottomBandItemHeight = bottomBandItemHeight + 30;
        JLabel feeType = new JLabel("Fee Type");
        feeType.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        feeType.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(feeType);
        
        itemWidth = itemWidth + 200;
        JSeparator feeTypeSeparator = new JSeparator(JSeparator.VERTICAL);
        feeTypeSeparator.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        feeTypeSeparator.setBounds(itemWidth - 20, bottomBandItemHeight+10, (screenWidth - 200), 100);
 		bottombandPanel.add(feeTypeSeparator);
 		
        JLabel feeLabel = new JLabel("Fee");
        feeLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        feeLabel.setBounds(itemWidth+20, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(feeLabel);
        
        JLabel amountLabel = new JLabel("Amount");
        amountLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        amountLabel.setBounds(itemWidth, bottomBandItemHeight+15, 120, 50);
        bottombandPanel.add(amountLabel);
        
        itemWidth = itemWidth + 120;
        JSeparator feeAmountSeparator = new JSeparator(JSeparator.VERTICAL);
        feeAmountSeparator.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        feeAmountSeparator.setBounds(itemWidth - 40, bottomBandItemHeight+10, 50, 100);
 		bottombandPanel.add(feeAmountSeparator);
 		
 		JLabel concessionLabel = new JLabel("Conce-");
 		concessionLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		concessionLabel.setBounds(itemWidth-30, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(concessionLabel);
        
        JLabel ssionLabel = new JLabel("ssion");
 		ssionLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		ssionLabel.setBounds(itemWidth-30, bottomBandItemHeight+15, 120, 50);
        bottombandPanel.add(ssionLabel);
        
//        itemWidth = itemWidth + 120;
        JSeparator concessionSeparator = new JSeparator(JSeparator.VERTICAL);
        concessionSeparator.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        concessionSeparator.setBounds(itemWidth + 30, bottomBandItemHeight+10, 50, 100);
 		bottombandPanel.add(concessionSeparator);
 		
 		if(studentOptMap.get(grNoClass) != null) {
 			optionalFee = ((LinkedHashMap<?, ?>) studentOptMap.get(grNoClass)).get("optional_fee").toString();
 		}
        
 		final JLabel[] month_labels = new JLabel[13];
 		final JSeparator[] monthSeparator = new JSeparator[13];
 		
 		itemWidth = itemWidth + 40;
 		String month = "All";
 		for(int m=1; m <=monthColumns; m++){
 			
// 			if(optionalClass.equalsIgnoreCase("No")){
 				month = commonObj.intgerToMonth((startMonth+(m-1))+"");
// 			}
 			
 			if(maxFrequencyClass == 1 && m == 1){
 				month_labels[m] = new JLabel("Yearly");
 	 			month_labels[m].setFont(new Font("Book Antiqua", Font.BOLD, 16));
 	 			month_labels[m].setBounds(itemWidth, bottomBandItemHeight, 120, 50);
 	 	        bottombandPanel.add(month_labels[m]);
 	 	        
 	 	        monthSeparator[m] = new JSeparator(JSeparator.VERTICAL);
 	 	        monthSeparator[m].setFont(new Font("Book Antiqua", Font.BOLD, 18));
 	 	        monthSeparator[m].setBounds(itemWidth - 15, bottomBandItemHeight+10, 50, 100);
 	 	        if(m != 1)
 	 	 		bottombandPanel.add(monthSeparator[m]);
 	 	        
 	 	        itemWidth = itemWidth + 60;
 			}
 			else if(maxFrequencyClass == 2 && (m == 1 || m == 7)){
 				if(m == 1){
 					month_labels[m] = new JLabel("Term 1");	
 				}
 				else if(m == 7){
 					month_labels[m] = new JLabel("Term 2");
 				}
 	 			month_labels[m].setFont(new Font("Book Antiqua", Font.BOLD, 16));
 	 			month_labels[m].setBounds(itemWidth-8, bottomBandItemHeight, 120, 50);
 	 	        bottombandPanel.add(month_labels[m]);
 	 	        
 	 	        monthSeparator[m] = new JSeparator(JSeparator.VERTICAL);
 	 	        monthSeparator[m].setFont(new Font("Book Antiqua", Font.BOLD, 18));
 	 	        monthSeparator[m].setBounds(itemWidth - 15, bottomBandItemHeight+10, 50, 100);
 	 	        if(m != 1)
 	 	 		bottombandPanel.add(monthSeparator[m]);
 	 	        
 	 	        itemWidth = itemWidth + 60;
 			}
 			else if(maxFrequencyClass == 4 && (m == 1 || m == 4 || m == 7 || m == 10)){
 				
 				if(m == 1){
 					month_labels[m] = new JLabel("Q1");	
 				}
 				else if(m == 4){
 					month_labels[m] = new JLabel("Q2");
 				}
 				else if(m == 7){
 					month_labels[m] = new JLabel("Q3");	
 				}
 				else if(m == 10){
 					month_labels[m] = new JLabel("Q4");
 				}
 				
 	 			month_labels[m].setFont(new Font("Book Antiqua", Font.BOLD, 16));
 	 			month_labels[m].setBounds(itemWidth+5, bottomBandItemHeight, 120, 50);
 	 	        bottombandPanel.add(month_labels[m]);
 	 	        
 	 	        monthSeparator[m] = new JSeparator(JSeparator.VERTICAL);
 	 	        monthSeparator[m].setFont(new Font("Book Antiqua", Font.BOLD, 18));
 	 	        monthSeparator[m].setBounds(itemWidth - 15, bottomBandItemHeight+10, 50, 100);
 	 	        if(m != 1)
 	 	 		bottombandPanel.add(monthSeparator[m]);
 	 	        
 	 	        itemWidth = itemWidth + 60;
 			}
 			else if(maxFrequencyClass == 12){
 				month_labels[m] = new JLabel(month);
 	 			month_labels[m].setFont(new Font("Book Antiqua", Font.BOLD, 16));
 	 			month_labels[m].setBounds(itemWidth, bottomBandItemHeight, 120, 50);
 	 	        bottombandPanel.add(month_labels[m]);
 	 	        
 	 	        monthSeparator[m] = new JSeparator(JSeparator.VERTICAL);
 	 	        monthSeparator[m].setFont(new Font("Book Antiqua", Font.BOLD, 18));
 	 	        monthSeparator[m].setBounds(itemWidth - 15, bottomBandItemHeight+10, 50, 100);
 	 	        if(m != 1)
 	 	 		bottombandPanel.add(monthSeparator[m]);
 	 	        
 	 	        itemWidth = itemWidth + 60;
 			}
 		}
 		
 		itemWidth = 40;
 		int bottombandHeight = 270;
 		// Added for MAC
        if((System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
        	bottombandHeight = 320;
        }
        
        JLabel penaltyAmount_label = new JLabel("Penalty Amount : ");
        penaltyAmount_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        penaltyAmount_label.setBounds(itemWidth, screenHeight - bottombandHeight, 150, 50);
        bottombandPanel.add(penaltyAmount_label);
        
        final JTextField penaltyAmount_text = new JTextField();
        penaltyAmount_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        penaltyAmount_text.setBounds(itemWidth + 140, screenHeight - (bottombandHeight - 10), 80, 25);
        bottombandPanel.add(penaltyAmount_text);
        
        itemWidth = itemWidth + 240;
        String[] modeList = paymentModeList.split(",");
        final JComboBox mode_combo = new JComboBox(modeList);
        mode_combo.setBorder(null);
        mode_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        mode_combo.setBounds(itemWidth, screenHeight - (bottombandHeight - 10), 100, 25);
        bottombandPanel.add(mode_combo);
        
        itemWidth = itemWidth + 110;
        final JLabel bank_label = new JLabel("Bank : ");
        bank_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        bank_label.setBounds(itemWidth, screenHeight - (bottombandHeight + 5), 120, 50);
        
        itemWidth = itemWidth + 50;
        final JTextField bank_text = new JTextField();
        bank_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        bank_text.setBounds(itemWidth, screenHeight - (bottombandHeight - 10), 250, 25);
        
        itemWidth = itemWidth + 260;
        final JLabel cheque_dd_no_label = new JLabel("Cheque No. : ");
        cheque_dd_no_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        cheque_dd_no_label.setBounds(itemWidth, screenHeight - (bottombandHeight + 5), 200, 50);
        
        itemWidth = itemWidth + 110;
        final JTextField cheque_dd_no_text = new JTextField();
        cheque_dd_no_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        cheque_dd_no_text.setBounds(itemWidth, screenHeight - (bottombandHeight - 10), 90, 25);
        
        itemWidth = itemWidth + 95;
        final JLabel date_label = new JLabel("Date :");
        date_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        date_label.setBounds(itemWidth, screenHeight - (bottombandHeight + 5), 120, 50);
		
		itemWidth = itemWidth + 50;
        final JTextField dateDD_text = new JTextField("DD");
        dateDD_text.setForeground(Color.GRAY);
        dateDD_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        dateDD_text.setBounds(itemWidth, screenHeight - (bottombandHeight - 10), 30, 25);

		itemWidth = itemWidth + 32;
		final JLabel slashDD_label = new JLabel("/");
		slashDD_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		slashDD_label.setBounds(itemWidth, screenHeight - bottombandHeight, 10, 50);

		itemWidth = itemWidth + 8;
		final JTextField dateMM_text = new JTextField("MM");
		dateMM_text.setForeground(Color.GRAY);
		dateMM_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dateMM_text.setBounds(itemWidth, screenHeight - (bottombandHeight - 10), 38, 25);

		itemWidth = itemWidth + 40;
		final JLabel slashMM_label = new JLabel("/");
		slashMM_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		slashMM_label.setBounds(itemWidth, screenHeight - bottombandHeight, 10, 50);

		itemWidth = itemWidth + 10;
		final JTextField dateYYYY_text = new JTextField("YYYY");
		dateYYYY_text.setForeground(Color.GRAY);
		dateYYYY_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dateYYYY_text.setBounds(itemWidth, screenHeight - (bottombandHeight - 10), 60, 25);
        
		itemWidth = 40;
        JLabel totalConcession_label = new JLabel("Total Concession : ");
        totalConcession_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        totalConcession_label.setBounds(itemWidth, screenHeight - (bottombandHeight - 40), 150, 50);
        bottombandPanel.add(totalConcession_label);
        
        final JTextField totalConcession_text = new JTextField("0.00");
        totalConcession_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        if(concessionAmount > 0) {
        	totalConcession_text.setEditable(false);
        }
		else {
			totalConcession_text.setEditable(true);
		}
        totalConcession_text.setBounds(itemWidth + 140, screenHeight - (bottombandHeight - 50), 120, 25);
        bottombandPanel.add(totalConcession_text);
        
        itemWidth = 350;
        JLabel totalAmount_label = new JLabel("Total Amount : ");
        totalAmount_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        totalAmount_label.setBounds(itemWidth, screenHeight - (bottombandHeight - 40), 120, 50);
        bottombandPanel.add(totalAmount_label);
        
        final JTextField totalAmount_text = new JTextField();
        totalAmount_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        totalAmount_text.setEditable(false);
        totalAmount_text.setBounds(itemWidth + 120, screenHeight - (bottombandHeight - 50), 120, 25);
        bottombandPanel.add(totalAmount_text);
        
        final JCheckBox backDateCheckBox  = new JCheckBox();
        backDateCheckBox.setBorder(null);
        backDateCheckBox.setBounds(itemWidth + 275, screenHeight - (bottombandHeight - 55), 13, 13);
        bottombandPanel.add(backDateCheckBox);
        
        final JTextField dobDD_text = new JTextField("DD");
		dobDD_text.setForeground(Color.GRAY);
		dobDD_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dobDD_text.setBounds(itemWidth + 300, screenHeight - (bottombandHeight - 50), 30, 25);
		bottombandPanel.add(dobDD_text);

		JLabel slashDD_backDated_label = new JLabel("/");
		slashDD_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		slashDD_label.setBounds(itemWidth + 335, screenHeight - (bottombandHeight - 40), 10, 50);
		bottombandPanel.add(slashDD_label);

		final JTextField dobMM_text = new JTextField("MM");
		dobMM_text.setForeground(Color.GRAY);
		dobMM_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dobMM_text.setBounds(itemWidth + 345, screenHeight - (bottombandHeight - 50), 38, 25);
		bottombandPanel.add(dobMM_text);

		JLabel slashMM_backDated_label = new JLabel("/");
		slashMM_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		slashMM_label.setBounds(itemWidth + 390, screenHeight - (bottombandHeight - 40), 10, 50);
		bottombandPanel.add(slashMM_label);

		final JTextField dobYYYY_text = new JTextField("YYYY");
		dobYYYY_text.setForeground(Color.GRAY);
		dobYYYY_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dobYYYY_text.setBounds(itemWidth + 400, screenHeight - (bottombandHeight - 50), 60, 25);
		bottombandPanel.add(dobYYYY_text);
		
        JLabel totalAmountPaid_label = new JLabel("Total Amount Paid: ");
        totalAmountPaid_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        totalAmountPaid_label.setBounds(itemWidth + 480, screenHeight - (bottombandHeight - 40), 200, 50);
        bottombandPanel.add(totalAmountPaid_label);
        
        final JTextField totalAmountPaid_text = new JTextField();
        totalAmountPaid_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        totalAmountPaid_text.setBounds(itemWidth + 630, screenHeight - (bottombandHeight - 50), 120, 25);
        bottombandPanel.add(totalAmountPaid_text);
        if(totalBalanceFromDB != 0) {
        	totalAmountPaid_text.setText(totalBalanceFromDB+"");
		}

		itemWidth = 40;
		JLabel remark_label = new JLabel("Remark : ");
		remark_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		remark_label.setBounds(itemWidth, screenHeight - (bottombandHeight - 65), 120, 50);
        bottombandPanel.add(remark_label);
        
        final JTextField remark_text = new JTextField();
        remark_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        remark_text.setBounds(itemWidth + 120, screenHeight - (bottombandHeight - 80), 500, 25);
        bottombandPanel.add(remark_text);
		
        JButton pay_button = new JButton("Pay & Print Receipt");
        pay_button.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        pay_button.setBounds(itemWidth + 800, screenHeight - (bottombandHeight - 80), 190, 25);
        bottombandPanel.add(pay_button);
        
        totalConcession_text.addFocusListener(new java.awt.event.FocusAdapter() {

			String totalConcession = "";

			@Override
			public void focusGained(FocusEvent arg0) {

				totalConcession = totalConcession_text.getText();
				if(commonObj.validateNumber(totalConcession)){
					totalAmount = totalAmount + Double.parseDouble(totalConcession);
					totalAmount = Double.parseDouble(String.format("%.2f", totalAmount));
				}
				totalConcession_text.selectAll();
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				totalConcession = totalConcession_text.getText().equalsIgnoreCase("") ? "0.00" : (totalConcession_text.getText().trim());
				if(commonObj.validateNumber(totalConcession)){
					totalAmount = totalAmount - Double.parseDouble(totalConcession);
					totalAmount = Double.parseDouble(String.format("%.2f", totalAmount));
					totalAmount_text.setText(String.format("%.2f", totalAmount));
					totalAmountPaid_text.setText(String.format("%.2f", totalAmount + totalBalanceFromDB));
					totalConcession_text.setText(String.format("%.2f", Double.parseDouble(totalConcession)));
				}
				else{
					JOptionPane.showMessageDialog(null, "Please enter valid Concession amount");
				}
				if(totalConcession.equalsIgnoreCase("") || Double.parseDouble(totalConcession) == 0) {
					totalConcession_text.setText("0.00");
				}
				concessionAmount = Double.parseDouble(totalConcession);
			}
		});
        
        dobDD_text.addFocusListener(new java.awt.event.FocusAdapter() {

			String strDD = "";

			public void focusGained(java.awt.event.FocusEvent event) {

				strDD = dobDD_text.getText();
				dobDD_text.setForeground(Color.BLACK);
				if (commonObj.validateOnlyNumber(strDD)) {
					dobDD_text.selectAll();
				} else {
					dobDD_text.setText("");
				}

			}

			public void focusLost(java.awt.event.FocusEvent event) {

				strDD = dobDD_text.getText();
				if (!commonObj.validateOnlyNumber(strDD)) {
					dobDD_text.setForeground(Color.GRAY);
					dobDD_text.setText("DD");
				} else if (strDD.length() != 2) {
					JOptionPane.showMessageDialog(null, "Please enter Date in two digit");
					dobDD_text.setForeground(Color.GRAY);
					dobDD_text.setText("DD");
					dobDD_text.requestFocus();
				} else if(Integer.parseInt(strDD) > 31){
					JOptionPane.showMessageDialog(null, "Please enter valid Date in two digit");
					dobDD_text.setForeground(Color.GRAY);
					dobDD_text.setText("DD");
					dobDD_text.requestFocus();
				}

			}
		});
		dobMM_text.addFocusListener(new java.awt.event.FocusAdapter() {

			String strMM = "";

			public void focusGained(java.awt.event.FocusEvent event) {

				strMM = dobMM_text.getText();
				dobMM_text.setForeground(Color.BLACK);
				if (commonObj.validateOnlyNumber(strMM)) {
					dobMM_text.selectAll();
				} else {
					dobMM_text.setText("");
				}

			}

			public void focusLost(java.awt.event.FocusEvent event) {

				strMM = dobMM_text.getText();
				if (!commonObj.validateOnlyNumber(strMM)) {
					dobMM_text.setForeground(Color.GRAY);
					dobMM_text.setText("MM");
				} else if (strMM.length() != 2) {
					JOptionPane.showMessageDialog(null, "Please enter Month in two digit");
					dobMM_text.setForeground(Color.GRAY);
					dobMM_text.setText("MM");
					dobMM_text.requestFocus();
				} else if(Integer.parseInt(strMM) > 12){
					JOptionPane.showMessageDialog(null, "Please enter valid Month in two digit");
					dobMM_text.setForeground(Color.GRAY);
					dobMM_text.setText("MM");
					dobMM_text.requestFocus();
				}

			}
		});
		dobYYYY_text.addFocusListener(new java.awt.event.FocusAdapter() {

			String strYYYY = "";

			public void focusGained(java.awt.event.FocusEvent event) {

				strYYYY = dobYYYY_text.getText();
				dobYYYY_text.setForeground(Color.BLACK);
				if (commonObj.validateOnlyNumber(strYYYY)) {
					dobYYYY_text.selectAll();
				} else {
					dobYYYY_text.setText("");
				}

			}

			public void focusLost(java.awt.event.FocusEvent event) {

				strYYYY = dobYYYY_text.getText();
				if (!commonObj.validateOnlyNumber(strYYYY)) {
					dobYYYY_text.setForeground(Color.GRAY);
					dobYYYY_text.setText("YYYY");
				} else if (strYYYY.length() != 4) {
					JOptionPane.showMessageDialog(null, "Please enter Year in four digit");
					dobYYYY_text.setForeground(Color.GRAY);
					dobYYYY_text.setText("YYYY");
					dobYYYY_text.requestFocus();
				}

			}
		});
		
        mode_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String modeSel = mode_combo.getSelectedItem().toString();
				if(modeSel.equalsIgnoreCase("Cash")){
					bottombandPanel.remove(bank_label);
					bottombandPanel.remove(bank_text);
					bottombandPanel.remove(cheque_dd_no_label);
					bottombandPanel.remove(cheque_dd_no_text);
					bottombandPanel.remove(date_label);
					bottombandPanel.remove(dateDD_text);
					bottombandPanel.remove(slashDD_label);
					bottombandPanel.remove(dateMM_text);
					bottombandPanel.remove(slashMM_label);
					bottombandPanel.remove(dateYYYY_text);
				}
				else if(modeSel.equalsIgnoreCase("Cheque") || modeSel.equalsIgnoreCase("DD") || modeSel.equalsIgnoreCase("UPI")){
					bottombandPanel.add(bank_label);
					bottombandPanel.add(bank_text);
					if(modeSel.equalsIgnoreCase("Cheque")) {
//						bank_text.setEnabled(true);
						cheque_dd_no_label.setText("Cheque No. : ");
					} else if(modeSel.equalsIgnoreCase("DD")) {
//						bank_text.setEnabled(true);
						cheque_dd_no_label.setText("DD No. : ");
					} else if(modeSel.equalsIgnoreCase("UPI")) {
//						bank_text.setEnabled(false);
//						bank_text.setText("");
						cheque_dd_no_label.setText("UPI No. : ");
					}
					
					bottombandPanel.add(cheque_dd_no_label);
					bottombandPanel.add(cheque_dd_no_text);
					bottombandPanel.add(date_label);
					bottombandPanel.add(dateDD_text);
					bottombandPanel.add(slashDD_label);
					bottombandPanel.add(dateMM_text);
					bottombandPanel.add(slashMM_label);
					bottombandPanel.add(dateYYYY_text);
				}
				bottombandPanel.revalidate();
				bottombandPanel.repaint();
			}
        });
        
        dateDD_text.addFocusListener(new java.awt.event.FocusAdapter() {

			String strDD = "";

			public void focusGained(java.awt.event.FocusEvent event) {

				strDD = dateDD_text.getText();
				dateDD_text.setForeground(Color.BLACK);
				if (commonObj.validateOnlyNumber(strDD)) {
					dateDD_text.selectAll();
				} else {
					dateDD_text.setText("");
				}

			}

			public void focusLost(java.awt.event.FocusEvent event) {

				strDD = dateDD_text.getText();
				if (!commonObj.validateOnlyNumber(strDD) || Integer.parseInt(strDD) < 1) {
					dateDD_text.setForeground(Color.GRAY);
					dateDD_text.setText("DD");
				} else if (strDD.length() == 0 || strDD.length() > 2) {
					JOptionPane.showMessageDialog(null, "Please enter Date in two digit");
					dateDD_text.setForeground(Color.GRAY);
					dateDD_text.setText("DD");
					dateDD_text.requestFocus();
				} else if(Integer.parseInt(strDD) > 31){
					JOptionPane.showMessageDialog(null, "Please enter valid Date in two digit");
					dateDD_text.setForeground(Color.GRAY);
					dateDD_text.setText("DD");
					dateDD_text.requestFocus();
				}
				
				if(strDD.length() < 2 && !strDD.equalsIgnoreCase("0") && !strDD.equalsIgnoreCase("")){
					strDD = commonObj.appendZeroByLength(strDD, 2);
					dateDD_text.setText(strDD);
				}

			}
		});
        dateMM_text.addFocusListener(new java.awt.event.FocusAdapter() {

			String strMM = "";

			public void focusGained(java.awt.event.FocusEvent event) {

				strMM = dateMM_text.getText();
				dateMM_text.setForeground(Color.BLACK);
				if (commonObj.validateOnlyNumber(strMM)) {
					dateMM_text.selectAll();
				} else {
					dateMM_text.setText("");
				}

			}

			public void focusLost(java.awt.event.FocusEvent event) {

				strMM = dateMM_text.getText();
				if (!commonObj.validateOnlyNumber(strMM) || Integer.parseInt(strMM) < 1) {
					dateMM_text.setForeground(Color.GRAY);
					dateMM_text.setText("MM");
				} else if (strMM.length() == 0 || strMM.length() > 2) {
					JOptionPane.showMessageDialog(null, "Please enter Month in two digit");
					dateMM_text.setForeground(Color.GRAY);
					dateMM_text.setText("MM");
					dateMM_text.requestFocus();
				} else if(Integer.parseInt(strMM) > 12){
					JOptionPane.showMessageDialog(null, "Please enter valid Month in two digit");
					dateMM_text.setForeground(Color.GRAY);
					dateMM_text.setText("MM");
					dateMM_text.requestFocus();
				}
				
				if(strMM.length() < 2 && !strMM.equalsIgnoreCase("0") && !strMM.equalsIgnoreCase("")){
					strMM = commonObj.appendZeroByLength(strMM, 2);
					dateMM_text.setText(strMM);
				}

			}
		});
        
        dateYYYY_text.addFocusListener(new java.awt.event.FocusAdapter() {

			String strYYYY = "";

			public void focusGained(java.awt.event.FocusEvent event) {

				strYYYY = dateYYYY_text.getText();
				dateYYYY_text.setForeground(Color.BLACK);
				if (commonObj.validateOnlyNumber(strYYYY)) {
					dateYYYY_text.selectAll();
				} else {
					dateYYYY_text.setText("");
				}

			}

			public void focusLost(java.awt.event.FocusEvent event) {

				strYYYY = dateYYYY_text.getText();
				if (!commonObj.validateOnlyNumber(strYYYY) || Integer.parseInt(strYYYY) < 1) {
					dateYYYY_text.setForeground(Color.GRAY);
					dateYYYY_text.setText("YYYY");
				} else if (strYYYY.length() != 4) {
					JOptionPane.showMessageDialog(null, "Please enter Year in four digit");
					dateYYYY_text.setForeground(Color.GRAY);
					dateYYYY_text.setText("YYYY");
					dateYYYY_text.requestFocus();
				}

			}
		});
        
        totalAmountPaid_text.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent event) {
				totalAmountPaid_text.selectAll();
			}

			public void focusLost(java.awt.event.FocusEvent event) {
			}
		});
        
        pay_button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int reply = 0;
				double balanceAmount = 0.0;
				String penaltyAmount = penaltyAmount_text.getText().equalsIgnoreCase("") ? "0" : penaltyAmount_text.getText();
				String totalAmountPaid = totalAmountPaid_text.getText().equalsIgnoreCase("") ? "0" : totalAmountPaid_text.getText();
				String paymentMode = "NA", balanceMessage = "";
				String bank = "NA";
				String cheque_dd_no = "NA";
				String modeSel = mode_combo.getSelectedItem().toString();
				String chequeDD_date = "NA";
				String backDate = dobDD_text.getText().trim() + "/" + dobMM_text.getText().trim() + "/"
						+ dobYYYY_text.getText().trim();
				boolean isBackDate = backDateCheckBox.isSelected();
				String remark = remark_text.getText().equalsIgnoreCase("") ? " " : remark_text.getText();
				
				boolean validateField = true;
				if(modeSel.equalsIgnoreCase("Cash")){
					paymentMode = "Cash";
				}
				else if(modeSel.equalsIgnoreCase("Cheque")){
					paymentMode = "Cheque";
					bank = bank_text.getText();
					cheque_dd_no = cheque_dd_no_text.getText();
					chequeDD_date = dateDD_text.getText().trim() + "/" + dateMM_text.getText().trim() + "/"
							+ dateYYYY_text.getText().trim();
					validateField = validate(penaltyAmount, bank, cheque_dd_no, chequeDD_date, modeSel);
				}
				else if(modeSel.equalsIgnoreCase("DD")){
					paymentMode = "DD";
					bank = bank_text.getText();
					cheque_dd_no = cheque_dd_no_text.getText();
					chequeDD_date = dateDD_text.getText().trim() + "/" + dateMM_text.getText().trim() + "/"
							+ dateYYYY_text.getText().trim();
					validateField = validate(penaltyAmount, bank, cheque_dd_no, chequeDD_date, modeSel);
				}
				else if(modeSel.equalsIgnoreCase("UPI")){
					paymentMode = "UPI";
					bank = bank_text.getText();
					cheque_dd_no = cheque_dd_no_text.getText();
					chequeDD_date = dateDD_text.getText().trim() + "/" + dateMM_text.getText().trim() + "/"
							+ dateYYYY_text.getText().trim();
					validateField = validate(penaltyAmount, bank, cheque_dd_no, chequeDD_date, modeSel);
				}
				
				String optionSms = "";
				if(bundle.getString("SMS_FEE_FLAG").equalsIgnoreCase("true") && bundle.getString("STAFF_FEE_SMS").equalsIgnoreCase("true")) {
					optionSms = JOptionPane.showInputDialog("=== SMS will be sent to Parents and Staff === \n Please Enter option \n 1 : Proceed "
							+ " \n 2 : Cancel Fee Payment");
				} else if(bundle.getString("SMS_FEE_FLAG").equalsIgnoreCase("true")) {
					optionSms = JOptionPane.showInputDialog("=== SMS will be sent to Parents === \n Please Enter option \n 1 : Proceed "
							+ " \n 2 : Cancel Fee Payment");
				} else if(bundle.getString("STAFF_FEE_SMS").equalsIgnoreCase("true")) {
					optionSms = JOptionPane.showInputDialog("=== SMS will be sent to Staff === \n Please Enter option \n 1 : Proceed "
							+ " \n 2 : Cancel Fee Payment");
				}
				
				if(optionSms == null || optionSms.trim().equalsIgnoreCase("null") || optionSms.equalsIgnoreCase("2")){
					validateField = false;
					JOptionPane.showMessageDialog(null, "Fee Payment cancelled");
				}
				
				if(validateField) {
					if(!commonObj.charExceeded("Bank Name", bank, 100).equalsIgnoreCase("")){
						JOptionPane.showMessageDialog(null, commonObj.charExceeded("Bank Name", bank, 100));
						validateField = false;
					} else if(commonObj.checkComma(bank)){
						validateField = false;
						JOptionPane.showMessageDialog(null, "Bank Name cannot contain characters ;',-:|");
					}
					
					if (isBackDate && (!commonObj.validateDate(backDate))) {
						validateField = false;
						JOptionPane.showMessageDialog(null, "Please enter valid fee receipt date");
					}
					else if(!isBackDate){
						backDate = commonObj.getCurrentDate();
					}
					
					if(!commonObj.charExceeded("Remark", remark, 90).equalsIgnoreCase("")){
						JOptionPane.showMessageDialog(null, commonObj.charExceeded("Remark", remark, 90));
						validateField = false;
					} else if(commonObj.checkComma(remark) || remark.contains("!")){
						validateField = false;
						JOptionPane.showMessageDialog(null, "Remark cannot contain characters ;',-:|!");
					}
					
					if(feesPaymentMap.isEmpty() && validateField){
						JOptionPane.showMessageDialog(null, "Please select atleast one checkbox.");
						validateField = false;
					}
					
					totalAmount = Double.parseDouble(String.format("%.2f", totalAmount));
					balanceAmount = commonObj.checkBalanceAmount(totalAmount, totalAmountPaid, totalBalanceFromDB);
					balanceAmount = Double.parseDouble(String.format("%.2f", balanceAmount));
					reply = JOptionPane.showConfirmDialog(null, commonObj.balanceAmountMessage(balanceAmount)+"\n Do you want to proceed with generating payment receipt ?", "Confirm Payment", JOptionPane.YES_NO_OPTION);
				}
				
				if(validateField && reply == JOptionPane.YES_OPTION){
					int count = 0;
					String feesForMonths = "";
					String lastMonthSel = "";
					String promoteText = "";
					if(!oldAcademicClass.equalsIgnoreCase("")){
						promoteText = "\n Also this student will be promoted to next year";
					}
					reply = JOptionPane.showConfirmDialog(null, "Please confirm to update Fee Payment by "+paymentMode+" "+promoteText+" dated : "+backDate+"?", "Confirm validate", JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_OPTION){
						try
						{
							if(!isBackDate){
								backDate = "";
							}
							
							Collections.sort(feesForMonthsList);
							feesForMonths = commonObj.intgerToMonth(feesForMonthsList.get(0).toString());
							lastMonthSel = commonObj.intgerToMonth(feesForMonthsList.get(feesForMonthsList.size() - 1).toString());
							if(lastMonth.equalsIgnoreCase("")){
								if(!feesForMonths.equalsIgnoreCase(lastMonthSel)){
									feesForMonths = feesForMonths + " - " + lastMonthSel;
								}
							}
							else{
								feesForMonths = feesForMonths + " - " + lastMonth;
							}
							
							count = dbValidate.updateCountData(sessionData, academicYearClass, sessionData.getSectionName(), "FEE_RECEIPT", "");
							
							String[] nameSplit = nameClass.split(" ");
							String last = nameSplit[0];
							String first = nameSplit[1];
							String father = nameSplit[2];
							
							boolean updateFlag = dbValidate.updateFeeData(sessionData, feesPaymentMap, penaltyAmount, 
									bank, cheque_dd_no, paymentMode, grNoClass, stdClass, divClass, academicYearClass, 
									"Pending", totalAmount, !studentFeesMap.isEmpty(), chequeDD_date, concessionMap, 
									concessionAmount, categoryClass, count, feesForMonths, contact1Class, contact2Class, 
									nameClass, rollNoClass, backDate, remark, studentFeesMap, balanceAmount, totalBalanceFromDB, first, last, father);
							
							if(updateFlag){
								if(!oldAcademicClass.equalsIgnoreCase("")){
									LinkedHashMap<String, String> studentMap = new LinkedHashMap<String, String>();
									studentMap.put(grNoClass, nameClass + "|" + grNoClass + "|" +rollNoClass+ "|" + stdClass + "|" + divClass + "|" + last + "|" + first + "|" + father);
									dbValidate.promoteClass(sessionData, studentMap, section, academicYearClass, oldStdClass, divClass);
								}
								///print receipt in PDF
								JOptionPane.showMessageDialog(null, "Payment updated successfully");
								LinkedHashMap<String, LinkedHashMap<String, String>> selectedStudentMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
								LinkedHashMap<String, String> grDetailMap = new LinkedHashMap<String, String>();
								grDetailMap.put("grNo", grNoClass);
								grDetailMap.put("rollNo", rollNoClass);
								grDetailMap.put("name", nameClass);
								grDetailMap.put("std", stdClass);
								grDetailMap.put("div", divClass);
								grDetailMap.put("feeStatus", "Pending");
								grDetailMap.put("paymentMode", paymentMode);
								if(!paymentMode.equalsIgnoreCase("Cash")){
									grDetailMap.put("bank", bank);
									grDetailMap.put("chequeDDNo", cheque_dd_no);
									grDetailMap.put("chequeDDDate", chequeDD_date);
								}
								grDetailMap.put("receiptNo", count+"");
								selectedStudentMap.put(grNoClass, grDetailMap);
								
								FeeReceiptPDF.getFeeReceiptPDF(sessionData, feesPaymentMap, penaltyAmount, bank, cheque_dd_no, paymentMode, grNoClass, 
										nameClass, stdClass, divClass, academicYearClass, "Pending", totalAmount, chequeDD_date, concessionMap, 
										concessionAmount, categoryClass, count, rollNoClass, feesForMonths, selectedStudentMap, headerRadioClass, 
										studentOptMap, receiptShortName, null, frequencyClass, backDate, remark, "", balanceAmount, totalBalanceFromDB);
//								searchStudentMap.remove(grNoClass);
								frame.setVisible(false);
								new FeesView(sessionData, grNoClass, stdClass, divClass, nameClass, rollNoClass, searchStudentMap, section, academicYearClass, 
										categoryClass, feesHeadMap, maxFrequencyClass+"", frequencyClass, subFrequencyClass, contact1Class, contact2Class, 
										oldAcademicClass, oldStdClass, headerRadioClass);
							}
						} catch (Exception ex) {
							logger.error("updateCountData Exception= " + ex);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Make the required changes before payment.");
					}
				}
			}
        });
        
        penaltyAmount_text.addFocusListener(new FocusListener() {

        	String penaltyAmount = "";
        	
			@Override
			public void focusGained(FocusEvent arg0) {

				penaltyAmount = penaltyAmount_text.getText();
				if(commonObj.validateNumber(penaltyAmount)){
					totalAmount = totalAmount - Double.parseDouble(penaltyAmount);
//					totalAmount_text.setText(String.format("%.2f", totalAmount));
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				penaltyAmount = penaltyAmount_text.getText().equalsIgnoreCase("") ? "0" : (penaltyAmount_text.getText().trim());
				if(commonObj.validateNumber(penaltyAmount)){
					totalAmount = totalAmount + Double.parseDouble(penaltyAmount);
					totalAmount_text.setText(String.format("%.2f", totalAmount));
					totalAmountPaid_text.setText(String.format("%.2f", totalAmount + totalBalanceFromDB));
				}
				else{
//					penaltyAmount_text.setText("");
					JOptionPane.showMessageDialog(null, "Please enter valid penalty amount");
				}
			}
		});
        
        JPanel dataPanel = new JPanel() {

            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {

            	try {
//	            	BufferedImage origImage = ImageIO.read(new File(img_path + img_databand));
//	                Image img = origImage.getScaledInstance(700,500, BufferedImage.SCALE_SMOOTH);
	                
	                Image img = new ImageIcon(img_path + img_databand).getImage();
	                Dimension size =
	                    new Dimension(screenWidth - 200, screenHeight + scrollHeight);//this height is responsible to increase scroll area
	                setPreferredSize(size);
	                setMinimumSize(size);
	                setMaximumSize(size);
	                setSize(size);
	                setLayout(null);
	//                g.drawImage(img, 0, 0, null);
	                g.drawImage(img, 0, 0, screenWidth - 158, (screenHeight)
							+ scrollHeight, null);
            	} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
//      dataPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
        dataPanel.setAutoscrolls(true);
        dataPanel.setLayout(null);
        
        if (!feesHeadMap.isEmpty()) {
        	try{
        		String[] optionalList = commonObj.replaceCommaApostrophy(optionalFee).split("\\|");
        		String subFee = "";
        		String concessionStr = "0";
        		Color  green   = new Color(32, 124, 46);
        		final LinkedHashMap<String, LinkedHashMap<String, String>> feesHeadMapForCheck = new LinkedHashMap<String, LinkedHashMap<String, String>>();
        		feesHeadMapForCheck.put(" ", null);
        		feesHeadMapForCheck.putAll(feesHeadMap);
        		
        		final JLabel[] feeType_labels = new JLabel[feesHeadMapForCheck.size()];
        		final JLabel[] subFee_labels = new JLabel[feesHeadMapForCheck.size()];
    	        final JLabel[] feeAmount_labels = new JLabel[feesHeadMapForCheck.size()];
    	        final JTextField[] concession_text = new JTextField[feesHeadMapForCheck.size()];
    	        final JSeparator[] jSeparators  = new JSeparator[feesHeadMapForCheck.size()];

    	        String feeTypeStr = "";
    	        int feesSize = feesHeadMapForCheck.size()+2;
    	        final JCheckBox[][] monthJCheckBox  = new JCheckBox[feesSize][13];
    	        final JLabel[][] paidLabel  = new JLabel[feesSize][13];
    	        
    	        int k = 0;
    	        int j = 5;
    	        int q = 0;
    	        boolean isOptional = false;
    	        double freqDivisor = 0;
    	        String freqFromMap = "", optional = "";
    	        Set set = feesHeadMapForCheck.entrySet();
    			Iterator i = set.iterator();
    			while(i.hasNext()) {
    				Map.Entry me = (Map.Entry)i.next();
    				
    				final int m = k;
        	        final int s = k+1;
        	        final int r = q;
    				itemWidth = 10;
    				subFee = "";
    				isOptional = false;
    				feeTypeStr = me.getKey().toString();
    				if(feesHeadMapForCheck.get(me.getKey()) != null) {
    					optional = ((LinkedHashMap<?, ?>) feesHeadMapForCheck.get(me.getKey())).get("optional").toString();
    					receiptShortName = ((LinkedHashMap<?, ?>) feesHeadMapForCheck.get(me.getKey())).get("short_name").toString();
    				}
    				
    				for(int n = 0; n < optionalList.length; n++) {
    	        		if(optionalList[n].contains(feeTypeStr+"^") && subFee.equalsIgnoreCase("")) {
    	        			subFee = optionalList[n].substring(optionalList[n].indexOf("^")+1);
    	        			isOptional = true;
    	        			if(!subFee.equalsIgnoreCase("")) {
    	        				feesHeadMapForCheck.get(me.getKey()).put("amount", ((LinkedHashMap<?, ?>) multiFeeHeadMap.get(subFee)).get("amount").toString());
    	        				subFee = " ("+ subFee +")";
    	        			}
    	        			break;
    	        		}
    	        	}
    				
    				feeType_labels[k] = new JLabel(commonObj.revertCommaApostrophy(feeTypeStr.replace("$$", ".")));
    				feeType_labels[k].setToolTipText(commonObj.revertCommaApostrophy(feeTypeStr.replace("$$", ".")));
    				feeType_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 12));
    				if(subFee.equalsIgnoreCase("")) {
    					feeType_labels[k].setBounds(itemWidth, j, 175, 25);
    				}
    				else {
    					feeType_labels[k].setBounds(itemWidth, j-4, 175, 25);
    				}
    				if(optional.equalsIgnoreCase("No") || (optional.equalsIgnoreCase("Yes") && isOptional)) {
    					dataPanel.add(feeType_labels[k]);
    				}
    				else if(!optional.equalsIgnoreCase("")){
    					feesHeadMap.remove(me.getKey());
    					continue;
    				}
    	        	
    	        	if(!subFee.equalsIgnoreCase("")) {
    	        		subFee_labels[k] = new JLabel(commonObj.revertCommaApostrophy(subFee));
        	        	subFee_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 10));
        	        	subFee_labels[k].setBounds(itemWidth-5, j+6, 175, 25);
        	        	dataPanel.add(subFee_labels[k]);
    	        	}
    	        	
    	        	itemWidth = itemWidth + 200;
    	        	if(feesHeadMapForCheck.get(me.getKey()) != null){
	    	        	freqFromMap = ((LinkedHashMap<?, ?>) feesHeadMapForCheck.get(me.getKey())).get("frequency").toString();
	    	        	freqDivisor = commonObj.frequencyToInteger(freqFromMap);
	    	        	double amount = (Double.parseDouble(((LinkedHashMap<?, ?>) feesHeadMapForCheck.get(me.getKey())).get("amount").toString())/freqDivisor);
	    	        	feeAmount_labels[k] = new JLabel(String.format("%.2f", amount));
	    	        	feeAmount_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    	        	feeAmount_labels[k].setBounds(itemWidth, j, 200, 25);
	    	    		dataPanel.add(feeAmount_labels[k]);
	    	    		
	    	    		if(concessionMap.get(feeTypeStr.trim()) != null){
	    	    			concessionStr = concessionMap.get(me.getKey()).toString();
	    	    		}
	    	    		else{
	    	    			concessionMap.put(me.getKey(), "0");
	    	    		}
	    	    		concession_text[k] = new JTextField(concessionStr);
	    	    		if(!concessionStr.equalsIgnoreCase("0")){
	    	    			concession_text[k].setEditable(false);
	    	    		}
	    	    		concession_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    	    		concession_text[k].setBounds(itemWidth + 95, j, 50, 25);
	    	    		dataPanel.add(concession_text[k]);
	    	    		
	    	    		concession_text[k].addFocusListener(new FocusListener() {

	    	    			String feeType = commonObj.replaceCommaApostrophy(feeType_labels[m].getText().replace(".", "$$"));
	    	    			LinkedHashMap feesTypeMonthMap = new LinkedHashMap();
	        	        	String concessionPercent = "";
	        	        	
	        				@Override
	        				public void focusGained(FocusEvent arg0) {
	        					double revertConcessAmt = 0;
	        					double monthAmt = 0;
	        					concession_text[m].selectAll();
	        					concessionPercent = concession_text[m].getText().equalsIgnoreCase("") ? "0" : (concession_text[m].getText().trim());
	        					
	        					if(commonObj.validateNumber(concessionPercent) && feesPaymentMap.get(feeType) != null){
	        						feesTypeMonthMap = feesPaymentMap.get(feeType);
	        						
        							Set setMonth = feesTypeMonthMap.entrySet();
        							Iterator j = setMonth.iterator();
        							while(j.hasNext()) {
        								Map.Entry monthEntry = (Map.Entry)j.next();
        								monthAmt = Double.parseDouble(((LinkedHashMap<?, ?>) feesTypeMonthMap.get(monthEntry.getKey())).get("amount").toString());
        								
//        								revertConcessAmt = monthAmt * (Double.parseDouble(concessionPercent)/100);
        								revertConcessAmt = Double.parseDouble(concessionPercent);
		        	    				totalAmount = totalAmount + revertConcessAmt;
		        	    				concessionAmount = concessionAmount - revertConcessAmt;
        							}
	        					}
	        				}

	        				@Override
	        				public void focusLost(FocusEvent arg0) {
	        					double applyConcessAmt = 0;
	        					double monthAmt = 0;
	        					double totalConcessionText = Double.parseDouble(totalConcession_text.getText());
	        					String isConcession = "false|0";
	        					int concessionCountBeforeAdd = 0, concessionCountAfterAdd = 0;
	        					
	        					isConcession = commonObj.isConcession(concessionMap);
	        					concessionCountBeforeAdd = Integer.parseInt(isConcession.substring(isConcession.indexOf("|")+1));
	        					
	        					concessionPercent = concession_text[m].getText().equalsIgnoreCase("") ? "0" : (concession_text[m].getText().trim());
	        					concessionMap.put(feeType, concessionPercent);
	        					isConcession = commonObj.isConcession(concessionMap);
	        					concessionCountAfterAdd = Integer.parseInt(isConcession.substring(isConcession.indexOf("|")+1));
	        					if(isConcession.contains("true")) {
	        						if(concessionCountAfterAdd == 1 && concessionCountBeforeAdd == 0) {
	        							totalAmount = totalAmount + concessionAmount;
	        							concessionAmount = 0;
	        						}
	        					}
	        					
	        					if(commonObj.validateNumber(concessionPercent) && feesPaymentMap.get(feeType) != null){
	        						feesTypeMonthMap = feesPaymentMap.get(feeType);
	        						
	        						Set set = feesTypeMonthMap.entrySet();
		        	    			Iterator j = set.iterator();
		        	    			while(j.hasNext()) {
        								Map.Entry monthEntry = (Map.Entry)j.next();
        								monthAmt = Double.parseDouble(((LinkedHashMap<?, ?>) feesTypeMonthMap.get(monthEntry.getKey())).get("amount").toString());
        								
//        								applyConcessAmt = monthAmt * (Double.parseDouble(concessionPercent)/100);
        								applyConcessAmt = Double.parseDouble(concessionPercent);
		        	    				totalAmount = totalAmount - applyConcessAmt;
		        	    				concessionAmount = concessionAmount + applyConcessAmt;
        							}
		        	    			totalAmount_text.setText(String.format("%.2f", totalAmount));
		        	    			totalAmountPaid_text.setText(String.format("%.2f", totalAmount + totalBalanceFromDB));
		        	    			totalConcession_text.setText(String.format("%.2f", concessionAmount));
		        	    			if(concessionAmount > 0 && commonObj.isConcession(concessionMap).contains("true")) {
		        	    	        	totalConcession_text.setEditable(false);
		        	    	        }
		        	    			else {
		        	    				totalConcession_text.setEditable(true);
		        	    			}
	        					}
	        					else if(!commonObj.validateNumber(concessionPercent)){
	        						JOptionPane.showMessageDialog(null, "Please enter valid concession Amount");
	        					}
	        				}
	        			});
    	        	}
    	        	
    	    		itemWidth = itemWidth + 165;
    	    		String statusCheck = "";
        	        for(int l = 1; l <= monthColumns ; l++){
        	        	
        	        	final int n = l;
        	        	statusCheck = "";
        	        	
    	        		if(freqFromMap.equalsIgnoreCase("Monthly") || freqFromMap.equalsIgnoreCase("Occasionally") || q == 0){
    	        			if(q == 0){
	    	        			if(maxFrequencyClass == 1 && l == 1){
	    	        				monthJCheckBox[q][l] = new JCheckBox();
	    	        	        	monthJCheckBox[q][l].setBorder(null);
		        	        		monthJCheckBox[q][l].setBounds(itemWidth+35, j+150, 13, 13);
	    	        				bottombandPanel.add(monthJCheckBox[q][l]);
	    	        				itemWidth = itemWidth + 60;
	    	         			}
	    	         			else if(maxFrequencyClass == 2 && (l == 1 || l == 7)){
	    	         				monthJCheckBox[q][l] = new JCheckBox();
	    	        	        	monthJCheckBox[q][l].setBorder(null);
		        	        		monthJCheckBox[q][l].setBounds(itemWidth+35, j+150, 13, 13);
	    	         				bottombandPanel.add(monthJCheckBox[q][l]);
	    	         				itemWidth = itemWidth + 60;
	    	         			}
	    	         			else if(maxFrequencyClass == 4 && (l == 1 || l == 4 || l == 7 || l == 10)){
	    	         				monthJCheckBox[q][l] = new JCheckBox();
	    	        	        	monthJCheckBox[q][l].setBorder(null);
		        	        		monthJCheckBox[q][l].setBounds(itemWidth+35, j+150, 13, 13);
	    	         				bottombandPanel.add(monthJCheckBox[q][l]);
	    	         				itemWidth = itemWidth + 60;
	    	         			}
	    	         			else if(maxFrequencyClass == 12){
	    	         				monthJCheckBox[q][l] = new JCheckBox();
	    	        	        	monthJCheckBox[q][l].setBorder(null);
		        	        		monthJCheckBox[q][l].setBounds(itemWidth+35, j+150, 13, 13);
	    	         				bottombandPanel.add(monthJCheckBox[q][l]);
	    	         				itemWidth = itemWidth + 60;
	    	         			}
        	        		}
    	        			else{
    	        				if(studentFeesMap.get(feeTypeStr+"_"+commonObj.intgerToMonth(startMonth+(l-1)+"")) != null) {
            	        			statusCheck = studentFeesMap.get(feeTypeStr+"_"+commonObj.intgerToMonth(startMonth+(l-1)+"")+"_BANK");
            	        			statusCheck = commonObj.getStringArrayElement(statusCheck, "^", 9, "");
            	        		}
    	        				if(studentFeesMap.get(feeTypeStr+"_"+commonObj.intgerToMonth(startMonth+(l-1)+"")) == null || 
            	        				statusCheck.equalsIgnoreCase("C")){
    	        					monthJCheckBox[q][l] = new JCheckBox();
        	        	        	monthJCheckBox[q][l].setBorder(null);
        	        	        	monthJCheckBox[q][l].setBounds(itemWidth+5, j+5, 13, 13);
            	        			dataPanel.add(monthJCheckBox[q][l]);
    	        				}
    	        				else{
    	        					paidLabel[q][l] = new JLabel("Paid");
    	        					paidLabel[q][l].setForeground(green);
    	        					paidLabel[q][l].setFont(new Font("Book Antiqua", Font.BOLD, 16));
    	        					paidLabel[q][l].setBounds(itemWidth-5, j-5, 50, 30);
            	        			dataPanel.add(paidLabel[q][l]);
    	        				}
    	        			}
        	        	}
        	        	else if(freqFromMap.equalsIgnoreCase("Quarterly") && (l == 1 || l == 4 || l == 7 || l == 10)){
        	        		if(studentFeesMap.get(feeTypeStr+"_"+commonObj.intgerToMonth(startMonth+(l-1)+"")) != null) {
        	        			statusCheck = studentFeesMap.get(feeTypeStr+"_"+commonObj.intgerToMonth(startMonth+(l-1)+"")+"_BANK");
        	        			statusCheck = commonObj.getStringArrayElement(statusCheck, "^", 9, "");
        	        		}
        	        		if(studentFeesMap.get(feeTypeStr+"_"+commonObj.intgerToMonth(startMonth+(l-1)+"")) == null || 
        	        				statusCheck.equalsIgnoreCase("C")){
	        	        		monthJCheckBox[q][l] = new JCheckBox();
	            	        	monthJCheckBox[q][l].setBorder(null);
	            	        	monthJCheckBox[q][l].setBounds(itemWidth+5, j+5, 13, 13);
	        	        		dataPanel.add(monthJCheckBox[q][l]);
        	        		}
	        				else{
	        					paidLabel[q][l] = new JLabel("Paid");
	        					paidLabel[q][l].setForeground(green);
	        					paidLabel[q][l].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	        					paidLabel[q][l].setBounds(itemWidth-5, j-5, 50, 30);
        	        			dataPanel.add(paidLabel[q][l]);
	        				}
        	        	}
        	        	else if(freqFromMap.equalsIgnoreCase("Half Yearly") && (l == 1 || l == 7)){
        	        		if(studentFeesMap.get(feeTypeStr+"_"+commonObj.intgerToMonth(startMonth+(l-1)+"")) != null) {
        	        			statusCheck = studentFeesMap.get(feeTypeStr+"_"+commonObj.intgerToMonth(startMonth+(l-1)+"")+"_BANK");
        	        			statusCheck = commonObj.getStringArrayElement(statusCheck, "^", 9, "");
        	        		}
        	        		if(studentFeesMap.get(feeTypeStr+"_"+commonObj.intgerToMonth(startMonth+(l-1)+"")) == null || 
        	        				statusCheck.equalsIgnoreCase("C")){	
        	        			monthJCheckBox[q][l] = new JCheckBox();
	            	        	monthJCheckBox[q][l].setBorder(null);
	            	        	monthJCheckBox[q][l].setBounds(itemWidth+5, j+5, 13, 13);
	        	        		dataPanel.add(monthJCheckBox[q][l]);
	        	        	}
	        				else{
	        					paidLabel[q][l] = new JLabel("Paid");
	        					paidLabel[q][l].setForeground(green);
	        					paidLabel[q][l].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	        					paidLabel[q][l].setBounds(itemWidth-5, j-5, 50, 30);
	    	        			dataPanel.add(paidLabel[q][l]);
	        				}
        	        	}
        	        	else if(freqFromMap.equalsIgnoreCase("Yearly") && l == 1){
        	        		if(studentFeesMap.get(feeTypeStr+"_"+commonObj.intgerToMonth(startMonth+(l-1)+"")) != null) {
        	        			statusCheck = studentFeesMap.get(feeTypeStr+"_"+commonObj.intgerToMonth(startMonth+(l-1)+"")+"_BANK");
        	        			statusCheck = commonObj.getStringArrayElement(statusCheck, "^", 9, "");
//            	        		statusCheck = statusCheck.substring(statusCheck.lastIndexOf("^")+1);
        	        		}
        	        		if(studentFeesMap.get(feeTypeStr+"_"+commonObj.intgerToMonth(startMonth+(l-1)+"")) == null || 
        	        				statusCheck.equalsIgnoreCase("C")){
        	        			monthJCheckBox[q][l] = new JCheckBox();
	            	        	monthJCheckBox[q][l].setBorder(null);
	            	        	monthJCheckBox[q][l].setBounds(itemWidth+5, j+5, 13, 13);
	        	        		dataPanel.add(monthJCheckBox[q][l]);
	        	        	}
	        				else{
	        					paidLabel[q][l] = new JLabel("Paid");
	        					paidLabel[q][l].setForeground(green);
	        					paidLabel[q][l].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	        					paidLabel[q][l].setBounds(itemWidth-5, j-5, 50, 30);
	    	        			dataPanel.add(paidLabel[q][l]);
	        				}
        	        	}
    	        		
    	        		if(monthJCheckBox[q][l] != null){
	    	        		monthJCheckBox[q][l].addActionListener(new ActionListener() {
	        					public void actionPerformed(ActionEvent e) {
	        						String feeType = "";
	        						String month = "";
	        						String amount = "";
	        						String freqFromMap = "";
	        						int freqDivisor = 0;
	        						if(commonObj.isConcession(concessionMap).contains("true")) {
	        							totalConcession_text.setEditable(true);
	        						}
	        						if(r == 0){
	        							boolean entireMonthSelected = monthJCheckBox[r][n].isSelected();
	        							if(maxFrequencyClass == 1 && n == 1){
	        								lastMonth = "MAY";
	        							}
	        							else if(maxFrequencyClass == 2 && (n == 1 || n == 7)){
	        								lastMonth = commonObj.intgerToMonth((n+startMonth+4)+"");
	        							}
	        							else if(maxFrequencyClass == 4 && (n == 1 || n == 4 || n == 7 || n == 10)){
	        								lastMonth = commonObj.intgerToMonth((n+startMonth+1)+"");
	        							}
	        							
	        							for(int b = 1; b <= n; b++){
	        								if(monthJCheckBox[r][b] == null){
	        									continue;
	        								}
	        								monthJCheckBox[r][b].setSelected(entireMonthSelected);
	        								if(entireMonthSelected && n > 1 && b != n){
	        									//monthJCheckBox[r][b].setEnabled(false);
	        								}
	        								else if(n > 1 && b != n){
	        									monthJCheckBox[r][b].setEnabled(true);
	        								}
	        								
	        								for(int a = 1; a <= feesHeadMapForCheck.size(); a++){
		        								if(monthJCheckBox[a][b] != null){
		            								monthJCheckBox[a][b].setSelected(entireMonthSelected);
		            								if(entireMonthSelected){
			        									//monthJCheckBox[a][b].setEnabled(false);
			        								}
			        								else{
			        									monthJCheckBox[a][b].setEnabled(true);
			        								}
		            								
		            								if(feeType_labels[a] != null){
		            									feeType = commonObj.replaceCommaApostrophy(feeType_labels[a].getText());
		                        						month = commonObj.intgerToMonth((b+(startMonth-1))+"");
		                        						
		                        						freqFromMap = ((LinkedHashMap<?, ?>) feesHeadMapForCheck.get(feeType)).get("frequency").toString();
		                        	    	        	freqDivisor = commonObj.frequencyToInteger(freqFromMap);
		                        						amount = (Double.parseDouble(((LinkedHashMap<?, ?>) feesHeadMapForCheck.get(feeType)).get("amount").toString())/freqDivisor)+"";
		                        						
		                        						feesCalculation(feeType, month, amount, monthJCheckBox[a][b].isSelected());
		            								}
		            							}
		        							}
	        							}
	        						}
	        						else{
	            						feeType = commonObj.replaceCommaApostrophy(feeType_labels[m].getText().replace(".", "$$"));
	            						month = commonObj.intgerToMonth((n+(startMonth-1))+"");
	            						freqFromMap = ((LinkedHashMap<?, ?>) feesHeadMapForCheck.get(feeType)).get("frequency").toString();
                	    	        	freqDivisor = commonObj.frequencyToInteger(freqFromMap);
                						amount = (Double.parseDouble(((LinkedHashMap<?, ?>) feesHeadMapForCheck.get(feeType)).get("amount").toString())/freqDivisor)+"";
    		        	    			
	            						feesCalculation(feeType, month, amount, monthJCheckBox[r][n].isSelected());
//	            						totalAmount_text.setText(String.format("%.2f", totalAmount));
	        						}
	        						
            						totalAmount_text.setText(String.format("%.2f", totalAmount));
            						totalAmountPaid_text.setText(String.format("%.2f", totalAmount + totalBalanceFromDB));
            						totalConcession_text.setText(String.format("%.2f", concessionAmount));
            						if(concessionAmount > 0 && commonObj.isConcession(concessionMap).contains("true")) {
		        	    	        	totalConcession_text.setEditable(false);
		        	    	        }
		        	    			else {
		        	    				totalConcession_text.setEditable(true);
		        	    			}
	        					}
	        				});
    	        		}
        	        		
    	        		/*if(q != 0){
    	        			itemWidth = itemWidth + 60;
    	        		}
    	        		*/
    	        		if(maxFrequencyClass == 1 && l == 1 && q != 0){
    	        			itemWidth = itemWidth + 60;
	         			}
	         			else if(maxFrequencyClass == 2 && (l == 1 || l == 7) && q != 0){
	         				itemWidth = itemWidth + 60;
	         			}
	         			else if(maxFrequencyClass == 4 && (l == 1 || l == 4 || l == 7 || l == 10) && q != 0){
	         				itemWidth = itemWidth + 60;
	         			}
	         			else if(maxFrequencyClass == 12 && q != 0){
	         				itemWidth = itemWidth + 60;
	         			}
        	        }
    	    		
    	    		jSeparators[k] = new JSeparator();
    	    		jSeparators[k].setFont(new Font("Book Antiqua", Font.BOLD, 18));
    	    		jSeparators[k].setBounds(10, j + 28, (screenWidth - 220), 50);
    	    		dataPanel.add(jSeparators[k]);
    	    		
    	    		if(q > 0){
    	    			j = j + 32;
    	    		}
    	    		k++;
    	    		q++;
    	        }
    			
    			////////////////jseparators start/////////////////
    			itemWidth = 189;
    			int vertSep = (feesHeadMap.size()*32)+2;
    	        JSeparator feeTypeSepInScroll = new JSeparator(JSeparator.VERTICAL);
    	        feeTypeSepInScroll.setFont(new Font("Book Antiqua", Font.BOLD, 18));
    	        feeTypeSepInScroll.setBounds(itemWidth, 0, 20, vertSep);
    	        dataPanel.add(feeTypeSepInScroll);
    	        
    	        itemWidth = itemWidth + 120;
    	        JSeparator feeAmountSepInScroll = new JSeparator(JSeparator.VERTICAL);
    	        feeAmountSepInScroll.setFont(new Font("Book Antiqua", Font.BOLD, 18));
    	        feeAmountSepInScroll.setBounds(itemWidth-20, 0, 20, vertSep);
    	        dataPanel.add(feeAmountSepInScroll);
    	        
    	        JSeparator concessionSepInScroll = new JSeparator(JSeparator.VERTICAL);
    	        concessionSepInScroll.setFont(new Font("Book Antiqua", Font.BOLD, 18));
    	        concessionSepInScroll.setBounds(itemWidth + 50, 0, 20, vertSep);
    	        dataPanel.add(concessionSepInScroll);
    	        
    	        itemWidth = itemWidth + 100;
    	        final JSeparator[] monthSeparators  = new JSeparator[50];
    	        int startSep = 1;
    	        if(maxFrequencyClass != 12){
    	        	startSep = 2;
    	        }
    	        
    	        for(int l = startSep; l < monthColumns ; l++){
    	    		if(maxFrequencyClass == 1 && l == 1){
    	    			monthSeparators[l] = new JSeparator(JSeparator.VERTICAL);
        	        	monthSeparators[l].setBounds(itemWidth+5, 0, 20, vertSep);
        	    		dataPanel.add(monthSeparators[l]);
        	    		itemWidth = itemWidth + 60;
         			}
         			else if(maxFrequencyClass == 2 && (l == 1 || l == 7)){
         				monthSeparators[l] = new JSeparator(JSeparator.VERTICAL);
        	        	monthSeparators[l].setBounds(itemWidth+5, 0, 20, vertSep);
        	    		dataPanel.add(monthSeparators[l]);
        	    		itemWidth = itemWidth + 60;
         			}
         			else if(maxFrequencyClass == 4 && (l == 1 || l == 4 || l == 7 || l == 10)){
         				monthSeparators[l] = new JSeparator(JSeparator.VERTICAL);
        	        	monthSeparators[l].setBounds(itemWidth+5, 0, 20, vertSep);
        	    		dataPanel.add(monthSeparators[l]);
        	    		itemWidth = itemWidth + 60;
         			}
         			else if(maxFrequencyClass == 12){
         				monthSeparators[l] = new JSeparator(JSeparator.VERTICAL);
        	        	monthSeparators[l].setBounds(itemWidth+5, 0, 20, vertSep);
        	    		dataPanel.add(monthSeparators[l]);
        	    		itemWidth = itemWidth + 60;
         			}
    	        }
    	        ////////////////jseparators end/////////////////
    	        if(!receiptMap.isEmpty()) {
    	        	String receiptStr = "";
    	        	itemWidth = 40;
    	        	
    	        	final JButton[] receipt_buttons = new JButton[receiptMap.size()];
    	        	JLabel receipt_label = new JLabel("Receipt Number List below for re-print : ");
    	        	receipt_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
    	        	receipt_label.setBounds(itemWidth, j - 10, 400, 50);
    	            dataPanel.add(receipt_label);
    	            
    	        	int m = 0, n = 1;
    		        Set setReceipt = receiptMap.entrySet();
    				Iterator ir = setReceipt.iterator();
    				while(ir.hasNext()) {
    					Map.Entry meReceipt = (Map.Entry)ir.next();
    					receipt_buttons[m] = new JButton(meReceipt.getKey()+"");
    					if(meReceipt.getValue().toString().equalsIgnoreCase("C")) {
    						receipt_buttons[m].setBackground(Color.RED);
    					}
    					else {
    						receiptStr = receiptStr + meReceipt.getKey()+"," ;
    					}
    		        	receipt_buttons[m].setFont(new Font("Book Antiqua", Font.BOLD, 16));
    		        	receipt_buttons[m].setBounds(itemWidth, j+30, 90, 25);
    		        	dataPanel.add(receipt_buttons[m]);
    		        	itemWidth = itemWidth + 110;
    		        	
    		        	final int r = m;
    		        	receipt_buttons[m].addActionListener(new ActionListener() {

    						public void actionPerformed(ActionEvent e) {
    							String receiptSel = receipt_buttons[r].getText();
    							rePrintReceipt(receiptSel);
    						}
    					});
    		            
    		        	if(n % 9 == 0) {
    		        		j = j + 30;
    		        		itemWidth = 40;
    		        	}
    		        	m++;
    		        	n++;
    				}
    				
    				itemWidth = 40;
    				final JCheckBox cancelCheckBox  = new JCheckBox();
    				cancelCheckBox.setBorder(null);
    				cancelCheckBox.setBounds(itemWidth, j + 80, 13, 13);
    				dataPanel.add(cancelCheckBox);
    		        
    		        final JLabel cancel_label = new JLabel("Select Receipt : ");
    		        cancel_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
    		        cancel_label.setBounds(itemWidth + 20, j + 75, 300, 25);
    		        dataPanel.add(cancel_label);
    				
    		        String[] receiptList = receiptStr.split(",");
    		        final JComboBox receipt_combo = new JComboBox(receiptList);
    		        receipt_combo.setBorder(null);
    		        receipt_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
    		        receipt_combo.setBounds(itemWidth + 150, j + 75, 100, 25);
    		        dataPanel.add(receipt_combo);
    		        
    		        final JButton cancel_buttons = new JButton("Cancel receipt");
					cancel_buttons.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					cancel_buttons.setBounds(itemWidth + 260, j + 75, 150, 25);
		        	dataPanel.add(cancel_buttons);
		        	
		        	final JButton rectifyConcession_buttons = new JButton("Rectify Concession");
		        	rectifyConcession_buttons.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		        	rectifyConcession_buttons.setBounds(itemWidth + 420, j + 75, 180, 25);
		        	dataPanel.add(rectifyConcession_buttons);
		        	
		        	cancel_buttons.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							int reply = 0;
							boolean cancelSel = cancelCheckBox.isSelected();
							String receiptToCancel = receipt_combo.getSelectedItem().toString();
							if(cancelSel) {
								reply = JOptionPane.showConfirmDialog(null, "Please confirm to cancel receipt number "+receiptToCancel+"?", "Confirm to cancel", JOptionPane.YES_NO_OPTION);
								if(reply == JOptionPane.YES_OPTION){
	    							cancelReceipt(receiptToCancel);
//									JOptionPane.showMessageDialog(null, "Logic to cancel receipt is not yet built");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Please select checkbox to proceed with cancellation of receipt number "+receiptToCancel);
							}
							
						}
					});
		        	
		        	rectifyConcession_buttons.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							int reply = 0;
							boolean cancelSel = cancelCheckBox.isSelected();
							String receiptToRectify = receipt_combo.getSelectedItem().toString();
							if(cancelSel) {
								reply = JOptionPane.showConfirmDialog(null, "Please confirm to rectify concession for receipt number "+receiptToRectify+"?", "Confirm to cancel", JOptionPane.YES_NO_OPTION);
								if(reply == JOptionPane.YES_OPTION){
									rectifyConcessionInReceipt(receiptToRectify);
//									JOptionPane.showMessageDialog(null, "Logic to cancel receipt is not yet built");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Please select checkbox to rectify concession of receipt number "+receiptToRectify);
							}
							
						}
					});
    	        }
    	        ///////////////////////////////
    	        
    			bottombandPanel.revalidate();
    			bottombandPanel.repaint();
        	} catch (Exception e1) {
    			commonObj.logException(e1);
            }
        }
        else{
        	frame.setVisible(false);
        	JOptionPane.showMessageDialog(null, "Fees Head with category "+categoryClass+" for Std "+stdClass+" does not exist.");
			new SearchFeeStudentNew(sessionData, "", stdClass, divClass, "", "", "", searchStudentMap, section, academicYearClass, categoryClass, 
					frequencyClass, subFrequencyClass, false, "", "", headerRadioClass);
        }
        
        bottomBandItemHeight = bottomBandItemHeight + 30;
        JScrollPane jsp;
        int height = 450;

        jsp = new JScrollPane(dataPanel, 
				   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  
				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(30, bottomBandItemHeight + 25, screenWidth - 180, screenHeight - height);
		
		// Added for MAC
        if((System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
        	jsp.setBounds(30, bottomBandItemHeight + 25, screenWidth - 180, screenHeight - height - 50);
        }
		
        bottombandPanel.add(jsp, BorderLayout.SOUTH);
        ///////////////end order panel////////////
        
        mainPanel.add(bottombandPanel, BorderLayout.SOUTH);
        panel.add(mainPanel, BorderLayout.EAST);
    }
    
    private static void feesCalculation(String feeType, String month, String amount, boolean isSelected){
    	LinkedHashMap feesTypeDetailMap = new LinkedHashMap();
		LinkedHashMap feesTypeMonthMap = new LinkedHashMap();
		double applyConcessAmt = 0;
		int feeTypeMonthInt = 0;
		int monthInt = commonObj.MonthToIntger(month);
		boolean removeMonth = true;
		
		if(isSelected){
			if(feesPaymentMap.get(feeType) != null){
				if(!feesPaymentMap.get(feeType).containsKey(month)){
					applyConcessAmt = Double.parseDouble(concessionMap.get(feeType).toString());
					concessionAmount = concessionAmount + applyConcessAmt;
					
					feesTypeMonthMap = feesPaymentMap.get(feeType);
					feesTypeDetailMap.put("amount", (Double.parseDouble(amount)));
					feesTypeMonthMap.put(month, feesTypeDetailMap);
					totalAmount = totalAmount + Double.parseDouble(amount) - applyConcessAmt;
				}
			}
			else{
				applyConcessAmt = Double.parseDouble(concessionMap.get(feeType).toString());
				concessionAmount = concessionAmount + applyConcessAmt;
				
				feesTypeDetailMap.put("amount", (Double.parseDouble(amount)));
				feesTypeMonthMap.put(month, feesTypeDetailMap);
				feesPaymentMap.put(feeType, feesTypeMonthMap);
				totalAmount = totalAmount + Double.parseDouble(amount) - applyConcessAmt;
			}
			
			if(!feesForMonthsList.contains(monthInt)){
				feesForMonthsList.add(monthInt);
			}
		}
		else{
			if(feesPaymentMap.get(feeType).get(month) != null){
				feesTypeDetailMap = feesPaymentMap.get(feeType);
				feesPaymentMap.get(feeType).remove(month);
				if(feesPaymentMap.get(feeType).isEmpty()) {
					feesPaymentMap.remove(feeType);
				}
				applyConcessAmt = Double.parseDouble(concessionMap.get(feeType).toString());
				totalAmount = totalAmount - Double.parseDouble(amount) + applyConcessAmt;
				concessionAmount = concessionAmount - applyConcessAmt;
			}
			
			LinkedHashMap feesTypeMonthRemoveMap = new LinkedHashMap();
			Set<?> set = feesPaymentMap.entrySet();
			Iterator<?> i = set.iterator();
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
				feeType = commonObj.revertCommaApostrophy(me.getKey().toString().replace("$$", "."));
				
				feesTypeMonthRemoveMap = feesPaymentMap.get(me.getKey());
				Set<?> setMonth = feesTypeMonthRemoveMap.entrySet();
				Iterator<?> m = setMonth.iterator();
				double feeTypeTotal = 0;
				while(m.hasNext()) {
					Map.Entry monthData = (Map.Entry)m.next();
					feeTypeMonthInt = commonObj.MonthToIntger(monthData.getKey().toString());
					if(feeTypeMonthInt == monthInt) {
						removeMonth = false;
						break;
					}
				}
			}
			
			
			if(feesForMonthsList.contains(monthInt) && removeMonth){
				feesForMonthsList.remove(feesForMonthsList.indexOf(monthInt));
			}
		}
    }
    
    private static boolean validate(String penaltyAmount, String bank, String chequeDDNo, String chequeDD_date, String modeSel){
    	if (penaltyAmount.length() > 10 || (penaltyAmount.length() > 0 && !commonObj.validateNumber(penaltyAmount))) {
			JOptionPane.showMessageDialog(null, "Please enter valid Penalty Amount.");
			return false;
		} else if (bank.length() == 0) {
			JOptionPane.showMessageDialog(null, "Please enter Bank details.");
			return false;
		}
		else if (bank.length() > 100) {
			JOptionPane.showMessageDialog(null, commonObj.charExceeded("Bank", bank, 100));
			return false;
		} else if (commonObj.checkComma(bank)) {
			JOptionPane.showMessageDialog(null, "Bank cannot contain |-:';,");
			return false;
		} else if (modeSel.equalsIgnoreCase("UPI") && (chequeDDNo.length() == 0 || chequeDDNo.length() > 20 || !commonObj.validateNumber(chequeDDNo))) {
			JOptionPane.showMessageDialog(null, "UPI number should be less than 20 character");
			return false;
		} 
		else if (!modeSel.equalsIgnoreCase("UPI") && (chequeDDNo.length() == 0 || chequeDDNo.length() > 10 || !commonObj.validateNumber(chequeDDNo))) {
			JOptionPane.showMessageDialog(null, "Please enter Cheque/DD No. details.");
			return false;
		} else if (!modeSel.equalsIgnoreCase("UPI") && (!commonObj.validateDate(chequeDD_date) || chequeDD_date.equalsIgnoreCase(""))) {
			JOptionPane.showMessageDialog(null, "Please enter valid Cheque/DD Date.");
			return false;
		}
    	return true;
    }
    
    private static void rePrintReceipt(String receiptSel) {
    	String feeType = "", feeTypeWithMonth = "", feeMonth = "", feeAmount = "", paidDate = "", freqFromMap = "", paymentMode = "", 
    			bank = "", cheque_dd_no = "", chequeDD_date = "", feesForMonths = "", remark = "", status = "";
    	Double totalAmount = 0.0, concession = 0.0, penalty = 0.0, balanceAmount = 0.0, prevBalanceAmount = 0.0;
    	int freqDivisor = 0;
    	LinkedHashMap<String, LinkedHashMap<String, String>> feesPaymentReceiptMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    	String[] bankDetails = null, dataSplit = null;
    	
    	Set set = studentFeesMap.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();
			feeType = me.getKey().toString();
			if(me.getValue() != null && !me.getValue().toString().equalsIgnoreCase("null") && feeType.contains("_BANK")) {
				dataSplit = me.getValue().toString().split("\\!");
				for(int j = 0; j < dataSplit.length; j++) {
					bankDetails = dataSplit[j].split("\\^");
					if(bankDetails.length > 1 && bankDetails[6].trim().equalsIgnoreCase(receiptSel)) {
						break;
					}
				}
				
				if(bankDetails.length <= 1 || !bankDetails[6].trim().equalsIgnoreCase(receiptSel)) {
					continue;
				}
				
				feeTypeWithMonth = feeType.substring(0, feeType.length()-5);
				feeMonth = feeTypeWithMonth.substring(feeTypeWithMonth.lastIndexOf("_")+1);
				feeType = feeType.substring(0, feeType.length()-9);
//				feeAmount = studentFeesMap.get(feeTypeWithMonth);
				if(feesHeadMap.get(feeType) != null) {
					freqFromMap = ((LinkedHashMap<?, ?>) feesHeadMap.get(feeType)).get("frequency").toString();
				}
				else {
					JOptionPane.showMessageDialog(null, "Fee Type "+feeType+ " might have been deleted from Fee Head \n Or \n If fee type "+feeType+" is optional then please allot to respective student in Fee Allotment tab");
					continue;
				}
	        	freqDivisor = commonObj.frequencyToInteger(freqFromMap);
	        	feeAmount = (Double.parseDouble(((LinkedHashMap<?, ?>) feesHeadMap.get(feeType)).get("amount").toString())/freqDivisor)+"";
//				feeAmount = (((LinkedHashMap<?, ?>) feesHeadMap.get(feeType)).get("amount").toString());
				if(paidDate.equalsIgnoreCase("")) {
					paidDate = studentFeesMap.get(feeTypeWithMonth+"_DATE");
					if(paidDate.contains(" ")) {
						paidDate = commonObj.dateFormatFromyyyymmddToddmmyyyy(paidDate.substring(0,  paidDate.indexOf(" ")));
					}

					/***
					 * paymentMode +"^"+ bank +"^"+ chequeDDNo +"^"+ chequeDDDate +"^"+ penaltyAmount +"^"+ concessionAmount +"^"+ 
					 * receiptNo +"^"+ feesForMonths +"^"+ remark +"^A" +"^"+ balanceAmount +"^"+ prevBalanceAmount;
					 ***/
					paymentMode = bankDetails[0].toString();
					bank = bankDetails[1].toString();
					cheque_dd_no = bankDetails[2].toString();
					chequeDD_date = bankDetails[3].toString();
					feesForMonths = bankDetails[7].toString();
					if(bankDetails.length > 8) {
						remark = bankDetails[8].toString().trim();
						status = bankDetails[9].toString().trim();
					}
					if(bankDetails.length > 10) {
						balanceAmount = Double.parseDouble(bankDetails[10].toString().trim());
					}
					if(bankDetails.length > 11) {
						prevBalanceAmount = Double.parseDouble(bankDetails[11].toString().trim());
					}
					
				}
				totalAmount = totalAmount + Double.parseDouble(feeAmount);
				concession = concession + Double.parseDouble(bankDetails[5].toString());
				if(penalty == 0) {
					penalty = penalty + Double.parseDouble(bankDetails[4].toString());
				}
				
				LinkedHashMap feesTypeDetailReceiptMap = new LinkedHashMap();
				LinkedHashMap feesTypeMonthReceiptMap = new LinkedHashMap();
				feesTypeDetailReceiptMap.put("amount", (Double.parseDouble(feeAmount)));
				if(feesPaymentReceiptMap.get(feeType) != null) {
					feesTypeMonthReceiptMap = feesPaymentReceiptMap.get(feeType);
				}
				feesTypeMonthReceiptMap.put(feeMonth, feesTypeDetailReceiptMap);
				feesPaymentReceiptMap.put(feeType, feesTypeMonthReceiptMap);
			}
		}
		if(!feesPaymentReceiptMap.isEmpty()) {
			totalAmount = totalAmount - concession + penalty;
			
			LinkedHashMap<String, LinkedHashMap<String, String>> selectedStudentMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
			LinkedHashMap<String, String> grDetailMap = new LinkedHashMap<String, String>();
			grDetailMap.put("grNo", grNoClass);
			grDetailMap.put("rollNo", rollNoClass);
			grDetailMap.put("name", nameClass);
			grDetailMap.put("std", stdClass);
			grDetailMap.put("div", divClass);
			grDetailMap.put("feeStatus", "Pending");
			grDetailMap.put("paymentMode", paymentMode);
			if(!paymentMode.equalsIgnoreCase("Cash")){
				grDetailMap.put("bank", bank);
				grDetailMap.put("chequeDDNo", cheque_dd_no);
				grDetailMap.put("chequeDDDate", chequeDD_date);
			}
			grDetailMap.put("receiptNo", receiptSel+"");
			selectedStudentMap.put(grNoClass, grDetailMap);
			
			FeeReceiptPDF.getFeeReceiptPDF(sessionData, feesPaymentReceiptMap, penalty.toString(), bank, cheque_dd_no, paymentMode, grNoClass, 
					nameClass, stdClass, divClass, academicYearClass, "Pending", totalAmount, chequeDD_date, concessionMap, 
					concession, categoryClass, 0, rollNoClass, feesForMonths, selectedStudentMap, headerRadioClass, 
					studentOptMap, receiptShortName, null, frequencyClass, paidDate, remark, status, balanceAmount, prevBalanceAmount);
		}
    }
    
    private static void cancelReceipt(String receiptSel) {
    	String feeType = "", feeTypeWithMonth = "", feeMonth = "", feeAmount = "", paidDate = "",  paymentMode = "", bank = "", 
    			cheque_dd_no = "", chequeDD_date = "", feesForMonths = "", remark = "", status = "", updateQuery = "", data = "",
				paymentDetails = "",receiptNo = "", paymentData = "", feeHeadZero = "";
    	Double totalAmount = 0.0, concession = 0.0, penalty = 0.0, feeConcession = 0.0, concessionTotal = 0.0, 
    			penaltyTotal = 0.0, grandTotal = 0.0, balanceAmount = 0.0, prevBalanceAmount = 0.0;
    	LinkedHashMap<String, LinkedHashMap<String, String>> feesPaymentReceiptMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    	String[] bankDetails = null, dataSplit = null, columnSplit = null;
    	boolean isUpdate = false, isFeeHeadActive = false;
    	
    	updateQuery = "update "+sessionData.getDBName()+".fees_data_mandatory set ";
    	String updateReportQuery = "update "+sessionData.getDBName()+".fees_report_mandatory set ";
    	
    	Set set = studentFeesMap.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()) {
			isFeeHeadActive = false;
			Map.Entry me = (Map.Entry)i.next();
			feeType = me.getKey().toString();
			if(me.getValue() != null && !me.getValue().toString().equalsIgnoreCase("null") && feeType.contains("_BANK")) {
				dataSplit = me.getValue().toString().split("\\!");
				for(int j = 0; j < dataSplit.length; j++) {
					bankDetails = dataSplit[j].split("\\^");
					if(bankDetails[6].equalsIgnoreCase(receiptSel)) {
						break;
					}
				}
				
				if(!bankDetails[6].equalsIgnoreCase(receiptSel)) {
					continue;
				}
				
				if(bankDetails.length > 8) {
					remark = bankDetails[8].toString().trim();
					status = bankDetails[9].toString().trim();
				}
				if(bankDetails.length > 10) {
					balanceAmount = Double.parseDouble(bankDetails[10].toString().trim());
				}
				if(bankDetails.length > 11) {
					prevBalanceAmount = Double.parseDouble(bankDetails[11].toString().trim());
				}
				
				if(!status.equalsIgnoreCase("C")) {
					feeTypeWithMonth = feeType.substring(0, feeType.length()-5);
					feeMonth = feeTypeWithMonth.substring(feeTypeWithMonth.lastIndexOf("_")+1);
					feeAmount = studentFeesMap.get(feeTypeWithMonth);
					if(paidDate.equalsIgnoreCase("")) {
						paidDate = studentFeesMap.get(feeTypeWithMonth+"_DATE");
						if(paidDate.contains(" ")) {
							paidDate = commonObj.dateFormatFromyyyymmddToddmmyyyy(paidDate.substring(0,  paidDate.indexOf(" ")));
						}

						////paymentMode +"^"+ bank +"^"+ chequeDDNo +"^"+ chequeDDDate +"^"+ penaltyAmount +"^"+ concessionAmount +"^"+ receiptNo +"^"+ feesForMonths +"^"+ remark +"^A;
						paymentMode = bankDetails[0].toString();
						bank = bankDetails[1].toString();
						cheque_dd_no = bankDetails[2].toString();
						chequeDD_date = bankDetails[3].toString();
						feesForMonths = bankDetails[7].toString();
					}
					totalAmount = totalAmount + Double.parseDouble(feeAmount);
					feeConcession = Double.parseDouble(bankDetails[5].toString());
					concession = concession + feeConcession;
					if(penalty == 0) {
						penalty = penalty + Double.parseDouble(bankDetails[4].toString());
					}
					
					data = paymentMode +" ^"+ bank +" ^"+ cheque_dd_no +" ^"+ chequeDD_date +" ^"+ penalty +" ^"+ feeConcession +" ^"+ 
					receiptSel +" ^"+ feesForMonths +" ^"+ remark+"|"+paidDate+" ^C^"+balanceAmount+"^"+prevBalanceAmount;
					
					if(studentFeesMap.get(feeTypeWithMonth+"_BANK") != null) {
						paymentDetails = studentFeesMap.get(feeTypeWithMonth+"_BANK");
						dataSplit = paymentDetails.toString().split("\\!");
						
						for(int j = 0; j < dataSplit.length; j++) {
							if(dataSplit[j] != null) {
								columnSplit = dataSplit[j].toString().split("\\^");
								receiptNo = columnSplit[6];
								if(receiptNo.equalsIgnoreCase(receiptSel)) {
									paymentData = paymentData + "!" + data;
									isFeeHeadActive = true;
								}
								else {
									paymentData = paymentData + "!" + dataSplit[j];
									isFeeHeadActive = true;
								}
							}
						}
						
						if(isFeeHeadActive) {
							feeHeadZero = feeTypeWithMonth +" = 0,"+feeHeadZero; 
						}
						if(!paymentData.equalsIgnoreCase("")) {
							paymentData = paymentData.substring(1);
						}
					}
					updateQuery = updateQuery + feeType+"='"+paymentData+"',"+feeHeadZero;
					paymentData = "";
					isUpdate = true;
					
				}
			}
		}
		if(!status.equalsIgnoreCase("C") && isUpdate) {
			if(totalConcessionPaid == 0) {
				concessionTotal = 0.0;
			}
			else {
				concessionTotal = totalConcessionPaid - concession;
			}
			penaltyTotal = Double.parseDouble((studentFeesMap.get("PENALTY_AMOUNT") == null ? "0" : studentFeesMap.get("PENALTY_AMOUNT").trim()));
			penaltyTotal = penaltyTotal - penalty;
			grandTotal = Double.parseDouble((studentFeesMap.get("TOTAL_AMOUNT") == null ? "0" : studentFeesMap.get("TOTAL_AMOUNT").trim()));
			grandTotal = grandTotal - totalAmount + concession - penalty;
			
			updateQuery = updateQuery + "CONCESSION_AMOUNT="+concessionTotal+",PENALTY_AMOUNT="+penaltyTotal+","
					+ "TOTAL_AMOUNT="+grandTotal+",MODIFIED_DATE=SYSDATE(),MODIFIED_BY='"+sessionData.getUserName()+"',BALANCE_AMOUNT=BALANCE_AMOUNT-"+balanceAmount+"";
			updateQuery = updateQuery + " where GR_NO='"+grNoClass+ "' and STD_1='"+stdClass+"' and DIV_1='"+divClass+"' "
					+ "and ACADEMIC_YEAR='"+academicYearClass+"'";
			
			try {
				if(dbValidate.connectDatabase(sessionData) && isUpdate){
					boolean updateFlag = dbValidate.updateReceipt(sessionData, updateQuery);
					
					if(updateFlag) {
						JOptionPane.showMessageDialog(null, "Fee receipt "+receiptSel+ " is cancelled.");
						frame.setVisible(false);
						new FeesView(sessionData, grNoClass, stdClass, divClass, nameClass, rollNoClass, searchStudentMap, section, academicYearClass, 
								categoryClass, feesHeadMap, maxFrequencyClass+"", frequencyClass, subFrequencyClass, contact1Class, contact2Class, 
								oldAcademicClass, oldStdClass, headerRadioClass);
					}
				}
			} catch (Exception e) {
	            commonObj.logException(e);
	        }
//			totalAmount = totalAmount - concession + penalty;
//			
//			LinkedHashMap<String, LinkedHashMap<String, String>> selectedStudentMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
//			LinkedHashMap<String, String> grDetailMap = new LinkedHashMap<String, String>();
//			grDetailMap.put("grNo", grNoClass);
//			grDetailMap.put("rollNo", rollNoClass);
//			grDetailMap.put("name", nameClass);
//			grDetailMap.put("std", stdClass);
//			grDetailMap.put("div", divClass);
//			grDetailMap.put("feeStatus", "Pending");
//			grDetailMap.put("paymentMode", paymentMode);
//			if(!paymentMode.equalsIgnoreCase("Cash")){
//				grDetailMap.put("bank", bank);
//				grDetailMap.put("chequeDDNo", cheque_dd_no);
//				grDetailMap.put("chequeDDDate", chequeDD_date);
//			}
//			grDetailMap.put("receiptNo", receiptSel+"");
//			selectedStudentMap.put(grNoClass, grDetailMap);
//			
//			FeeReceiptPDF.getFeeReceiptPDF(sessionData, feesPaymentReceiptMap, penalty.toString(), bank, cheque_dd_no, paymentMode, grNoClass, 
//					nameClass, stdClass, divClass, academicYearClass, "Pending", totalAmount, chequeDD_date, concessionMap, 
//					concession, categoryClass, 0, rollNoClass, feesForMonths, selectedStudentMap, headerRadioClass, 
//					studentOptMap, receiptShortName, null, frequencyClass, paidDate, remark, "");
		}
		else {
			JOptionPane.showMessageDialog(null, "Fee receipt "+receiptSel+" is already in cancelled state.");
		}
    }
    
    private static void rectifyConcessionInReceipt(String receiptSel) {
    	String feeType = "", feeTypeWithMonth = "", feeMonth = "", feeAmount = "", paidDate = "",  paymentMode = "", bank = "", 
    			cheque_dd_no = "", chequeDD_date = "", feesForMonths = "", remark = "", status = "", updateQuery = "", data = "",
				paymentDetails = "",receiptNo = "", paymentData = "", feeHeadZero = "", concessionStr = "";
    	Double totalAmount = 0.0, concession = 0.0, penalty = 0.0, feeConcession = 0.0, concessionTotal = 0.0, 
    			penaltyTotal = 0.0, grandTotal = 0.0, balanceAmount = 0.0, prevBalanceAmount = 0.0;
    	LinkedHashMap<String, LinkedHashMap<String, String>> feesPaymentReceiptMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    	String[] bankDetails = null, bankDetailsToUpdate = null, dataSplit = null, columnSplit = null;
    	LinkedHashMap<Double, Double> concessionMap = new LinkedHashMap<Double, Double>();
    	int countConcession = 1;
    	boolean concessionUpdated = false;
    	updateQuery = "update "+sessionData.getDBName()+".fees_data_mandatory set ";
    	
    	Set set = studentFeesMap.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();
			feeType = me.getKey().toString();
			if(me.getValue() != null && !me.getValue().toString().equalsIgnoreCase("null") && feeType.contains("_BANK")) {
				dataSplit = me.getValue().toString().split("\\!");
				for(int j = 0; j < dataSplit.length; j++) {
					bankDetails = dataSplit[j].split("\\^");
					if(bankDetails[6].equalsIgnoreCase(receiptSel)) {
						break;
					}
				}
				
				if(!bankDetails[6].equalsIgnoreCase(receiptSel)) {
					continue;
				}
				
				if(!status.equalsIgnoreCase("C")) {
					feeConcession = Double.parseDouble(bankDetails[5].toString());
					if(!concessionMap.containsKey(feeConcession)) {
						concessionMap.put(feeConcession, feeConcession);
						concessionStr = concessionStr +" "+countConcession+". "+ feeConcession + "\n";
						countConcession++;
					}
				}
			}
		}
		
		concessionStr = concessionStr + "\n Please enter correct concession amount allocated";
		Double concessionSelect = Double.parseDouble(JOptionPane.showInputDialog(concessionStr));
		String feesData = "", receiptData = "";
		
		if(concessionMap.containsKey(concessionSelect)) {
		////update the concession selected
			Iterator k = set.iterator();
			while(k.hasNext()) {
				feesData = "";
				Map.Entry me = (Map.Entry)k.next();
				feeType = me.getKey().toString();
				if(me.getValue() != null && !me.getValue().toString().equalsIgnoreCase("null") && feeType.contains("_BANK")) {
					dataSplit = me.getValue().toString().split("\\!");
					for(int j = 0; j < dataSplit.length; j++) {
						bankDetails = dataSplit[j].split("\\^");
						if(dataSplit.length == 1 && !bankDetails[6].equalsIgnoreCase(receiptSel)) {
							bankDetailsToUpdate = null;
							break;
						}
						else if(bankDetails[6].equalsIgnoreCase(receiptSel)) {
							bankDetailsToUpdate = bankDetails;
//							break;
						}
						else {
							feesData = feesData + dataSplit[j]+"!";
						}
					}
					
					if(bankDetailsToUpdate == null || !bankDetailsToUpdate[6].equalsIgnoreCase(receiptSel)) {
						continue;
					}
					
					if(!status.equalsIgnoreCase("C")) {
						/***
						 * paymentMode +"^"+ bank +"^"+ chequeDDNo +"^"+ chequeDDDate +"^"+ penaltyAmount +"^"+ concessionAmount +"^"+ 
						 * receiptNo +"^"+ feesForMonths +"^"+ remark +"^A" +"^"+ balanceAmount +"^"+ prevBalanceAmount;
						 ***/
						for(int j = 0; j < bankDetailsToUpdate.length; j++) {
							
							if(j == 5 && !concessionUpdated) {
								receiptData = receiptData + concessionSelect+"^";
								concessionUpdated = true;
							}
							else if(j == 5) {
								receiptData = receiptData + "0.0^";
							}
							else {
								receiptData = receiptData + bankDetailsToUpdate[j]+"^";
							}
						}
						
						receiptData = receiptData.substring(0, receiptData.length() - 1);
						feesData = feesData + receiptData;
						receiptData = "";
					}
					updateQuery = updateQuery + feeType+"='"+feesData+"',";
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Please enter correct amount from list");
		}
		
		if(!status.equalsIgnoreCase("C")) {
			updateQuery = updateQuery.substring(0, updateQuery.length() - 1);
			updateQuery = updateQuery + " where GR_NO='"+grNoClass+ "' and STD_1='"+stdClass+"' and "
					+ "DIV_1='"+divClass+"' and ACADEMIC_YEAR='"+academicYearClass+"'";
			
			try {
				if(dbValidate.connectDatabase(sessionData)){
					boolean updateFlag = dbValidate.updateReceipt(sessionData, updateQuery);
					
					if(updateFlag) {//updateFlag
						JOptionPane.showMessageDialog(null, "Concession "+concessionSelect+" updated for Fee receipt "+receiptSel);
						frame.setVisible(false);
						new FeesView(sessionData, grNoClass, stdClass, divClass, nameClass, rollNoClass, searchStudentMap, section, academicYearClass, 
								categoryClass, feesHeadMap, maxFrequencyClass+"", frequencyClass, subFrequencyClass, contact1Class, contact2Class, 
								oldAcademicClass, oldStdClass, headerRadioClass);
					}
					else {
						JOptionPane.showMessageDialog(null, "Concession "+concessionSelect+" not updated for Fee receipt "+receiptSel);
					}
				}
			} catch (Exception e) {
	            commonObj.logException(e);
	        }
		}
		else {
			JOptionPane.showMessageDialog(null, "Fee receipt "+receiptSel+" is already in cancelled state.");
		}
    }
}
