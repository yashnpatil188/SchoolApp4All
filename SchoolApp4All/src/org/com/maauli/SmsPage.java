package org.com.maauli;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.lowagie.text.DocumentException;

public class SmsPage extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JComboBox studentcombo;

	static String std = "";
	
	static String smsTypeList = "";

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
	
    static String app_header = "";
    
    static String app_header_0 = "";

	static String img_menuband = "";

	static String img_mainband = "";

	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static SessionData sessionData = new SessionData();

	static Logger logger = Logger.getLogger(SmsPage.class.getName());

	static JFrame frame = null;

	static int retCount = 0;

	static int scrollHeight = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

	static List<String> allGrList = new ArrayList();

	static List<String> selectedAllGrList = new ArrayList();

	static List<String> duplicateLCList = new ArrayList();
	
	static List reportDataList = new ArrayList();

	static boolean setSelected = false;

	static boolean printDuplicate = false;

	static String dateLeaving = "";

	static String dateIssue = "";

	static String reason = "";
	
	static String reasonStr = "";
	
	static String remarkStr = "";
	
	static String progressStr = "";
	
	static String mediumStr = "";
	
	static String mediumStrEnable = "";

	static String remark = "";

//	static String remark2 = "";
	
	static String progress = "";
	
	static String conduct = "";

	static String grClass = "";

	static String stdClass = "";

	static String divClass = "";

	static String firstClass = "";

	static String lastClass = "";

	static String fatherClass = "";

	static String yearClass = "";

	static String smsTypeClass = "Select";

	static String user_name = "";

	static String user_role = "";
	
	static String academicYearClass = "";
	
	static String currentYear = "";
	
	static String feeStatusList = "";

	static LeavingCertAction LeavingCertActionObj = new LeavingCertAction();

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
    static String sms_required = "";
    static String fee_required = "";
    static String fee_visible_master_only = "";
    static String account_required = "";
    static String staff_required = "";
    static String dateTimeClass = "";
    static String fromDtClass = "";
    static String toDtClass = "";
    static int daysCheckStatus = 0;
    static boolean fee_visible_flag = true;

	private static LinkedHashMap foundStudentMap;
	private static LinkedHashMap<String, LinkedHashMap<String, String>> smsTemplateIdMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();

	public SmsPage(SessionData sessionData1, String retGr_no, String retStd, String retDiv, String retLastName,
			String retFirstName, String retFatherName, LinkedHashMap retStudentMap, boolean retSelected,
			String fromDate, String toDate, String reasonT, String remarkT, String conductT, String academicYear,
			String sec, String retLcType, String retUserName, String retUserRole, String progressT, String remark2T, String retDateTime) {

		logger.info("======SmsPage========");
		System.gc();
		fee_visible_flag = true;
		dateTimeClass = retDateTime;
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		academicYearClass = academicYear;
		grClass = retGr_no;
		stdClass = retStd;
		divClass = retDiv;
		firstClass = retFirstName;
		lastClass = retLastName;
		fatherClass = retFatherName;
		yearClass = academicYear;
		fromDtClass = fromDate;
		toDtClass = toDate;
		conduct = conductT;
		reason = reasonT;
		remark = remarkT;
//		remark2 = remark2T;
		progress = "";
		progress = progressT;
		entrytCnt = 0;
		reportDataList.clear();
		reportDataList.add("GR No.|Roll No.|Name|Phone|Sms Status|Message Sent|Message Id|Message Type|Sms Sent Date");

		section = sec;
		logger.info("section :: " + section);
		std = bundle.getString(section.toUpperCase() + "_STD");
		logger.info("std :: " + std);
		div = bundle.getString(section.toUpperCase() + "_DIV");
		logger.info("div :: " + div);
		secName = bundle.getString(section.toUpperCase() + "_SEC");
		logger.info("secName :: " + secName);
		smsTypeList = bundle.getString("SMS_TYPE_LIST");
		reasonStr = bundle.getString("REASON_LIST_"+sessionData1.getAppType());
		progressStr = bundle.getString("LC_PROGRESS_LIST");
		mediumStr = bundle.getString("LC_MEDIUM_LIST");
		mediumStrEnable = bundle.getString("LC_MEDIUM_ENABLE_"+sessionData1.getAppType()) == null ? "False" : bundle.getString("LC_MEDIUM_ENABLE_"+sessionData1.getAppType());
		remarkStr = bundle.getString("REMARK_LIST_"+sessionData1.getAppType());
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
    	sms_required = bundle.getString("SMS_REQUIRED");
    	fee_required = bundle.getString("FEE_REQUIRED");
    	fee_visible_master_only = bundle.getString("FEE_VISIBLE_MASTER_ONLY");
    	account_required = bundle.getString("ACCOUNT_REQUIRED");
    	staff_required = bundle.getString("STAFF_REQUIRED");
    	daysCheckStatus = Integer.parseInt(bundle.getString("DAYS_STATUS_CHECK"));
    	
        feeStatusList = bundle.getString("FEE_STATUS_LIST");

        
        currentYear = commonObj.getCurrentYear();
        remarkStr = remarkStr.replace("YYYY", currentYear);
        
		try {
			if(fee_visible_master_only.equalsIgnoreCase("true") && !sessionData.getConfigMap().get("SchoolApp_IP").contains("127.0.0.1")) {
        		fee_visible_flag = false;
        	}
        	else {
        		fee_visible_flag = true;
        	}
			if (dbValidate.connectDatabase(sessionData)) {
				String userActive = dbValidate.checkFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
				if (userActive.equalsIgnoreCase("") || userActive.equalsIgnoreCase(null)) {
					dbValidate.insertFormData(user_name, "LEAVING CERTIFICATE", user_role, section);
				}
			}
		} catch (Exception e1) {
			logger.info("Exception insertFormData ===>>>" + e1);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}

		if (!retLcType.equalsIgnoreCase("")) {
			smsTypeClass = retLcType;
		}
		
		allGrList.clear();
		duplicateLCList.clear();
		setSelected = retSelected;
		foundStudentMap = retStudentMap;
		retCount = foundStudentMap.size();
		logger.info("foundStudentMap size => " + retCount);
		if (setSelected) {
			selectAllCount = retCount;
		}
		scrollHeight = (retCount - 6) * 33; // to adjust the perfect scroll
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

						if (deleteFlag) {
							frame.setVisible(false);
							String[] arguments = new String[] {""};
			                LoginView.main(arguments);
						} else {
							logger.info("Exception while deleting form.");
						}
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
	//        leftPanel.add(staffButton);
	
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
	
	            	List subList = new ArrayList();
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
	        smsButton.setBackground(Color.GREEN);
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

        if(fee_required.equalsIgnoreCase("true") && fee_visible_flag){
	        p = p + 50;
	        JButton feeButton = new JButton("FEE");
	        feeButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
	        feeButton.setBounds(10, p + 50, 130, 35);
	        leftPanel.add(feeButton);
	
	        feeButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	
	            	frame.setVisible(false);
	            	LinkedHashMap findStudentMap = new LinkedHashMap();
					new CreateFeesHead(sessionData, "", "", section);
	            }
	        });
        }
        
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
		smsHomeButton.setBackground(Color.GREEN);
		smsHomeButton.setBounds(width, 50, 140, 24);
		topbandPanel.add(smsHomeButton);

		smsHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);
//            	LinkedHashMap findStudentMap = new LinkedHashMap();
//				new SmsPage(sessionData, "", "", "", "", "", "", findStudentMap, false, "", "", "", "", "",
//						"", section, "", user_name, user_role,"","","");
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
		smsTemplateButton.setBounds(width, 50, 170, 24);
		topbandPanel.add(smsTemplateButton);

		smsTemplateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new CreateSMSTemplate(sessionData, "", section);

			}
		});
		
//		width = width + 150;
//		JButton smsFeeReportButton = new JButton("SMS Fee Report");
//		smsFeeReportButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
//		smsFeeReportButton.setBackground(Color.GREEN);
//		smsFeeReportButton.setBounds(width, 50, 140, 24);
//		topbandPanel.add(smsFeeReportButton);
//
//		smsFeeReportButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//
//			}
//		});

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
//		if (!stdClass.equalsIgnoreCase("")) {
//			stdSel = stdClass;
//			std = stdSel + "," + std;
//		} else {
			std = "All," + std;
//		}
		// String admittedStdList[] =
		// {stdSel,"IV","V","VI","VII","VIII","IX","X"};
		String[] stdList = std.split(",");
		final JComboBox admittedStd_combo = new JComboBox(stdList);
		if(!stdClass.equalsIgnoreCase("")) {
			admittedStd_combo.setSelectedItem(stdClass);
		}
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
//		if (!divClass.equalsIgnoreCase("")) {
//			divSel = divClass;
//			div = divSel + "," + div;
//		} else {
			div = "All," + div;
//		}
		// String admittedDivList[] = {divClass,"A","B","C","D"};
		String[] divList = div.split(",");
		final JComboBox admittedDiv_combo = new JComboBox(divList);
		if(!divClass.equalsIgnoreCase("")) {
			admittedDiv_combo.setSelectedItem(divClass);
		}
		admittedDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedDiv_combo.setBounds(580, 12, 100, 25);
		findPanel.add(admittedDiv_combo);

		// ///////////////////////////////
		JLabel lcType_label = new JLabel("Type :");
		lcType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lcType_label.setBounds(710, 00, 110, 50);
		findPanel.add(lcType_label);

//		String lcSel = "";
//		if (!smsTypeClass.equalsIgnoreCase("")) {
//			lcSel = smsTypeClass;
//			smsTypeList = lcSel + "," + smsTypeList;
//		} else {
//			smsTypeList = "Select," + smsTypeList;
//		}

		String[] smsList = smsTypeList.split(",");
		final JComboBox sms_combo = new JComboBox(smsList);
		sms_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sms_combo.setBounds(770, 12, 180, 25);
		sms_combo.setSelectedItem(smsTypeClass);
		findPanel.add(sms_combo);

		// /////////////Roll No.//////////////
		/*JLabel rollNo_label = new JLabel("Roll No :");
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
*/		// //////////////////////////////////
		// /////////////Gr List to update//////////////
		final JLabel grList_label = new JLabel("Gr List :");
		grList_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		grList_label.setBounds(60, 40, 120, 50);
		if(smsTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(grList_label);
		}

		final JTextField grList_text = new JTextField();
		grList_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		grList_text.setBounds(160, 50, 600, 25);
		if(smsTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(grList_text);
		}
		// /////////////Last Name//////////////
		final JLabel lastName_label = new JLabel("Last Name :");
		lastName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_label.setBounds(60, 40, 120, 50);
		if(!smsTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(lastName_label);
		}

		final JTextField lastName_text = new JTextField(lastClass);
		lastName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_text.setBounds(160, 50, 130, 25);
		if(!smsTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(lastName_text);
		}
		// //////////////////////////////////
		// /////////////First Name//////////////
		final JLabel firstName_label = new JLabel("First Name :");
		firstName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_label.setBounds(380, 40, 120, 50);
		if(!smsTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(firstName_label);
		}

		final JTextField firstName_text = new JTextField(firstClass);
		firstName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_text.setBounds(480, 50, 200, 25);
		if(!smsTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(firstName_text);
		}
		// //////////////////////////////////
		// /////////////Father Name//////////////
		final JLabel fatherName_label = new JLabel("Father Name :");
		fatherName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_label.setBounds(710, 40, 120, 50);
		if(!smsTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(fatherName_label);
		}

		final JTextField fatherName_text = new JTextField(fatherClass);
		fatherName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_text.setBounds(830, 50, 200, 25);
		if(!smsTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(fatherName_text);
		}
		// //////////////////////////////////
		// /////////GR radio///////////////
		final JRadioButton gr_no_radio = new JRadioButton();
		gr_no_radio.setBounds(30, 15, 20, 20);
		if(smsTypeClass.equalsIgnoreCase("Update")){
			gr_no_radio.setSelected(false);
			gr_no_radio.setEnabled(false);
		}
		findPanel.add(gr_no_radio);
		// /////////name radio///////////////30, 80, 120, 50
		final JRadioButton name_radio = new JRadioButton();
		name_radio.setBounds(30, 55, 20, 20);
		if(!smsTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(name_radio);
		}
		// /////////grList radio///////////////30, 80, 120, 50
		final JRadioButton grList_radio = new JRadioButton();
		grList_radio.setBounds(30, 55, 20, 20);
		if(smsTypeClass.equalsIgnoreCase("Update")){
			grList_radio.setSelected(true);
			grList_radio.setEnabled(false);
			findPanel.add(grList_radio);
		}
		// /////////Std radio///////////////
		final JRadioButton std_radio = new JRadioButton();
		std_radio.setBounds(350, 15, 20, 20);
		findPanel.add(std_radio);
		// /////////ACADEMIV YEAR radio///////////////30, 80, 120, 50
		final JRadioButton academic_radio = new JRadioButton();
		academic_radio.setBounds(30, 95, 20, 20);
		academic_radio.setSelected(true);
		findPanel.add(academic_radio);

		// /////////////ACADEMIV YEAR //////////////
		final JLabel academic_label = new JLabel("Year :");
		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academic_label.setBounds(60, 80, 70, 50);
		findPanel.add(academic_label);

		String yearList = "";
		try {
			if (dbValidate.connectDatabase(sessionData)) {
				yearList = dbValidate.getAcademicYearList(sessionData, "HS_GENERAL_REGISTER", "ACADEMIC_YEAR").toString();
			}
		}  catch (Exception e12) {
			logger.info("Exception logoutButton ===>>>" + e12);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
		
		if (!yearClass.equalsIgnoreCase("")){
			yearList = yearClass + "," + yearList;
		}
		
//		String academicYearList[] = { yearClass, "2010-11", "2011-12", "2012-13", "2013-14", "2014-15" };
		String[] academicYearList = yearList.split(",");
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academicYear_combo.setBounds(160, 95, 120, 25);
		findPanel.add(academicYear_combo);

		final JLabel dateTime_label = new JLabel("Date & Time :");
		dateTime_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dateTime_label.setBounds(350, 80, 200, 50);
		findPanel.add(dateTime_label);
		
		final JLabel format_label = new JLabel("(24 Hours format)");
		format_label.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		format_label.setBounds(350, 95, 200, 50);
		findPanel.add(format_label);
		
		final JTextField DD_text = new JTextField("DD");
		DD_text.setForeground(Color.GRAY);
		DD_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		DD_text.setBounds(460, 90, 30, 25);
		findPanel.add(DD_text);
		
		DD_text.addFocusListener(new java.awt.event.FocusAdapter() {
			String strDD = "";
			public void focusGained(java.awt.event.FocusEvent event) {
				strDD = DD_text.getText();
				DD_text.setForeground(Color.BLACK);
				if (commonObj.validateOnlyNumber(strDD)) {
					DD_text.selectAll();
				} else {
					DD_text.setText("");
				}
			}
			public void focusLost(java.awt.event.FocusEvent event) {
				strDD = DD_text.getText();
				if (!commonObj.validateOnlyNumber(strDD)) {
					DD_text.setForeground(Color.GRAY);
					DD_text.setText("DD");
				} else if (strDD.length() != 2 || (Integer.parseInt(strDD) > 31)) {
					JOptionPane.showMessageDialog(null, "Please enter valid Day in two digit");
					DD_text.setForeground(Color.GRAY);
					DD_text.setText("DD");
					DD_text.requestFocus();
				}
			}
		});

		final JLabel slashDD_label = new JLabel("/");
		slashDD_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		slashDD_label.setBounds(492, 80, 10, 50);
		findPanel.add(slashDD_label);

		final JTextField MM_text = new JTextField("MM");
		MM_text.setForeground(Color.GRAY);
		MM_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		MM_text.setBounds(500, 90, 38, 25);
		findPanel.add(MM_text);
		
		MM_text.addFocusListener(new java.awt.event.FocusAdapter() {
			String strMM = "";
			public void focusGained(java.awt.event.FocusEvent event) {
				strMM = MM_text.getText();
				MM_text.setForeground(Color.BLACK);
				if (commonObj.validateOnlyNumber(strMM)) {
					MM_text.selectAll();
				} else {
					MM_text.setText("");
				}
			}
			public void focusLost(java.awt.event.FocusEvent event) {
				strMM = MM_text.getText();
				if (!commonObj.validateOnlyNumber(strMM)) {
					MM_text.setForeground(Color.GRAY);
					MM_text.setText("MM");
				} else if (strMM.length() != 2 || (Integer.parseInt(strMM) > 12)) {
					JOptionPane.showMessageDialog(null, "Please enter valid Month in two digit");
					MM_text.setForeground(Color.GRAY);
					MM_text.setText("MM");
					MM_text.requestFocus();
				}
			}
		});

		final JLabel slashMM_label = new JLabel("/");
		slashMM_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		slashMM_label.setBounds(540, 80, 10, 50);
		findPanel.add(slashMM_label);

		final JTextField YYYY_text = new JTextField("YYYY");
		YYYY_text.setForeground(Color.GRAY);
		YYYY_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		YYYY_text.setBounds(550, 90, 50, 25);
		findPanel.add(YYYY_text);
		
		YYYY_text.addFocusListener(new java.awt.event.FocusAdapter() {
			String strYYYY = "";
			public void focusGained(java.awt.event.FocusEvent event) {
				strYYYY = YYYY_text.getText();
				YYYY_text.setForeground(Color.BLACK);
				if (commonObj.validateOnlyNumber(strYYYY)) {
					YYYY_text.selectAll();
				} else {
					YYYY_text.setText("");
				}
			}
			public void focusLost(java.awt.event.FocusEvent event) {
				strYYYY = YYYY_text.getText();
				if (!commonObj.validateOnlyNumber(strYYYY)) {
					YYYY_text.setForeground(Color.GRAY);
					YYYY_text.setText("YYYY");
				} else if (strYYYY.length() != 4) {
					JOptionPane.showMessageDialog(null, "Please enter valid Year in four digit");
					YYYY_text.setForeground(Color.GRAY);
					YYYY_text.setText("YYYY");
					YYYY_text.requestFocus();
				}
			}
		});
		
		final JTextField hh_text = new JTextField("HH");
		hh_text.setForeground(Color.GRAY);
		hh_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		hh_text.setBounds(620, 90, 38, 25);
		findPanel.add(hh_text);
		
		hh_text.addFocusListener(new java.awt.event.FocusAdapter() {
			String strHH = "";
			public void focusGained(java.awt.event.FocusEvent event) {
				strHH = hh_text.getText();
				hh_text.setForeground(Color.BLACK);
				if (commonObj.validateOnlyNumber(strHH)) {
					hh_text.selectAll();
				} else {
					hh_text.setText("");
				}
			}
			public void focusLost(java.awt.event.FocusEvent event) {
				strHH = hh_text.getText();
				if (!commonObj.validateOnlyNumber(strHH)) {
					hh_text.setForeground(Color.GRAY);
					hh_text.setText("HH");
				} else if (strHH.length() != 2 || (Integer.parseInt(strHH) > 24)) {
					JOptionPane.showMessageDialog(null, "Please enter valid hours in two digit");
					hh_text.setForeground(Color.GRAY);
					hh_text.setText("HH");
					hh_text.requestFocus();
				}
			}
		});
		
		final JLabel colon_label = new JLabel(":");
		colon_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		colon_label.setBounds(660, 90, 38, 25);
		findPanel.add(colon_label);
		
		final JTextField min_text = new JTextField("MM");
		min_text.setForeground(Color.GRAY);
		min_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		min_text.setBounds(670, 90, 38, 25);
		findPanel.add(min_text);
		
		min_text.addFocusListener(new java.awt.event.FocusAdapter() {
			String strMin = "";
			public void focusGained(java.awt.event.FocusEvent event) {
				strMin = min_text.getText();
				min_text.setForeground(Color.BLACK);
				if (commonObj.validateOnlyNumber(strMin)) {
					min_text.selectAll();
				} else {
					min_text.setText("");
				}
			}
			public void focusLost(java.awt.event.FocusEvent event) {
				strMin = min_text.getText();
				if (!commonObj.validateOnlyNumber(strMin)) {
					min_text.setForeground(Color.GRAY);
					min_text.setText("MM");
				} else if (strMin.length() != 2 || (Integer.parseInt(strMin) > 59)) {
					JOptionPane.showMessageDialog(null, "Please enter valid minutes in two digit");
					min_text.setForeground(Color.GRAY);
					min_text.setText("MM");
					min_text.requestFocus();
				}
			}
		});
		
		final JLabel from_label = new JLabel();
		from_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		from_label.setBounds(60, 80, 120, 50);
		findPanel.add(from_label);

		/****from date picker****/
		final UtilDateModel modelFrom = new UtilDateModel();
		if(!fromDtClass.equalsIgnoreCase("")){
			int dayFromDate = Integer.parseInt(fromDtClass.substring(0,fromDtClass.indexOf("/")));
			int monthFromDate = Integer.parseInt(fromDtClass.substring(fromDtClass.indexOf("/")+1,fromDtClass.lastIndexOf("/")));
			int yearFromDate = Integer.parseInt(fromDtClass.substring(fromDtClass.lastIndexOf("/")+1));
			modelFrom.setDate(yearFromDate,monthFromDate-1,dayFromDate);
//			modelFrom.setDate(2014,04,24);
		}
		modelFrom.setSelected(true);
	    Properties pFrom = new Properties();
	    pFrom.put("text.today", "Today");
	    pFrom.put("text.month", "Month");
	    pFrom.put("text.year", "Year");
	    final JDatePanelImpl datePanelFrom = new JDatePanelImpl(modelFrom, pFrom);
	    datePanelFrom.setBounds(55, 92, 130, 26);
	    // Don't know about the formatter, but there it is...
	    final JDatePickerImpl datePickerFrom = new JDatePickerImpl(datePanelFrom, new DateLabelFormatter());
	    datePickerFrom.setBounds(125, 92, 130, 26);
        findPanel.add(datePickerFrom);

        //////////////////To/////////////////
		final JLabel to_label = new JLabel("To :");
		to_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		to_label.setBounds(280, 80, 70, 50);
//		findPanel.add(to_label);

		/****to date picker****/
		final UtilDateModel modelTo = new UtilDateModel();
		if(!toDtClass.equalsIgnoreCase("")){
			int dayToDate = Integer.parseInt(toDtClass.substring(0,toDtClass.indexOf("/")));
			int monthToDate = Integer.parseInt(toDtClass.substring(toDtClass.indexOf("/")+1,toDtClass.lastIndexOf("/")));
			int yearToDate = Integer.parseInt(toDtClass.substring(toDtClass.lastIndexOf("/")+1));
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
	    datePanelTo.setBounds(300, 92, 130, 26);
	    // Don't know about the formatter, but there it is...
	    final JDatePickerImpl datePickerTo = new JDatePickerImpl(datePanelTo, new DateLabelFormatter());
	    datePickerTo.setBounds(325, 92, 130, 26);
//        findPanel.add(datePickerTo);
	    
	    /*if (smsTypeClass.equalsIgnoreCase("SMS Report")) {
	    	findPanel.add(from_label);
			from_label.setText("From :");
			findPanel.add(from_label);
			findPanel.add(datePickerFrom);
			to_label.setText("To :");
			findPanel.add(to_label);
			findPanel.add(datePickerTo);
			findPanel.remove(academicYear_combo);
			findPanel.remove(academic_label);
			findPanel.revalidate();
			findPanel.repaint();
		} else {
			findPanel.remove(from_label);
			findPanel.remove(datePickerFrom);
			findPanel.remove(to_label);
			findPanel.remove(datePickerTo);
			findPanel.add(academicYear_combo);
			findPanel.add(academic_label);
			findPanel.revalidate();
			findPanel.repaint();
		}*/
		
	    if(!stdSel.equalsIgnoreCase("All") && !stdSel.equalsIgnoreCase("Select") && 
	    		!stdSel.equalsIgnoreCase("") && dbValidate.connectDatabase(sessionData)){
			admittedDiv_combo.removeAllItems();
			String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdSel, section, 
					"PRESENT_DIV", "PRESENT_STD", "class_allotment",academicYearClass);
			
			for (String retval: divAvailabe.split(",")) {
				admittedDiv_combo.addItem(retval);
				admittedDiv_combo.setSelectedItem(divClass);
			}
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
		
		if (smsTypeClass.equalsIgnoreCase("SMS Report")) {
	    	findPanel.add(from_label);
			from_label.setText("From :");
			findPanel.add(from_label);
			findPanel.add(datePickerFrom);
			to_label.setText("To :");
			findPanel.add(to_label);
			findPanel.add(datePickerTo);
			findPanel.remove(academicYear_combo);
			findPanel.remove(academic_label);
			findPanel.remove(dateTime_label);
			findPanel.remove(format_label);
			findPanel.remove(DD_text);
			findPanel.remove(slashDD_label);
			findPanel.remove(MM_text);
			findPanel.remove(slashMM_label);
			findPanel.remove(YYYY_text);
			findPanel.remove(hh_text);
			findPanel.remove(colon_label);
			findPanel.remove(min_text);
			findPanel.revalidate();
			findPanel.repaint();
		}
		else if(!smsTypeClass.equalsIgnoreCase("Schedule SMS")){
			findPanel.remove(from_label);
			findPanel.remove(datePickerFrom);
			findPanel.remove(to_label);
			findPanel.remove(datePickerTo);
			findPanel.add(academicYear_combo);
			findPanel.add(academic_label);
			findPanel.remove(dateTime_label);
			findPanel.remove(format_label);
			findPanel.remove(DD_text);
			findPanel.remove(slashDD_label);
			findPanel.remove(MM_text);
			findPanel.remove(slashMM_label);
			findPanel.remove(YYYY_text);
			findPanel.remove(hh_text);
			findPanel.remove(colon_label);
			findPanel.remove(min_text);
		}
		else{
			findPanel.remove(from_label);
			findPanel.remove(datePickerFrom);
			findPanel.remove(to_label);
			findPanel.remove(datePickerTo);
			findPanel.add(academicYear_combo);
			findPanel.add(academic_label);
			findPanel.add(dateTime_label);
			findPanel.add(format_label);
			findPanel.add(DD_text);
			findPanel.add(slashDD_label);
			findPanel.add(MM_text);
			findPanel.add(slashMM_label);
			findPanel.add(YYYY_text);
			findPanel.add(hh_text);
			findPanel.add(colon_label);
			findPanel.add(min_text);
		}
		////////////////////////////////////
		sms_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String smsType = sms_combo.getSelectedItem().toString();
				
				if (smsType.equalsIgnoreCase("Check Balance")) {
					findPanel.remove(dateTime_label);
					findPanel.remove(format_label);
					findPanel.remove(DD_text);
					findPanel.remove(slashDD_label);
					findPanel.remove(MM_text);
					findPanel.remove(slashMM_label);
					findPanel.remove(YYYY_text);
					findPanel.remove(hh_text);
					findPanel.remove(colon_label);
					findPanel.remove(min_text);
					findPanel.remove(from_label);
					findPanel.remove(datePickerFrom);
					findPanel.remove(to_label);
					findPanel.remove(datePickerTo);
					findPanel.add(academicYear_combo);
					findPanel.add(academic_label);
					findPanel.revalidate();
					findPanel.repaint();
					
					JFrame f = new JFrame("Check Balance in progress. Don't Close");
					f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
				    f.setSize(400, 0);
				    f.setResizable(false);
				    f.setVisible(true);
				    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					String retBalance = commonObj.checkHspSmsBalance(sessionData);
					f.setVisible(false);
					JOptionPane.showMessageDialog(null, "SMS Balance :: " + retBalance);
					
					
				} else if (smsType.equalsIgnoreCase("Schedule SMS")) {
					gr_no_radio.setEnabled(true);
					grList_radio.setSelected(false);
					gr_no_text.setEditable(true);
					std_radio.setEnabled(true);
					admittedStd_combo.setEditable(true);
					admittedDiv_combo.setEnabled(true);
					academic_radio.setEnabled(true);
					findPanel.remove(grList_radio);
					findPanel.remove(grList_label);
					findPanel.remove(grList_text);
					findPanel.add(name_radio);
					findPanel.add(lastName_label);
					findPanel.add(lastName_text);
					findPanel.add(firstName_label);
					findPanel.add(firstName_text);
					findPanel.add(fatherName_label);
					findPanel.add(fatherName_text);
					findPanel.add(dateTime_label);
					findPanel.add(format_label);
					findPanel.add(DD_text);
					findPanel.add(slashDD_label);
					findPanel.add(MM_text);
					findPanel.add(slashMM_label);
					findPanel.add(YYYY_text);
					findPanel.add(hh_text);
					findPanel.add(colon_label);
					findPanel.add(min_text);
					findPanel.remove(from_label);
					findPanel.remove(datePickerFrom);
					findPanel.remove(to_label);
					findPanel.remove(datePickerTo);
					findPanel.add(academicYear_combo);
					findPanel.add(academic_label);
					findPanel.revalidate();
					findPanel.repaint();
				} else if (smsType.equalsIgnoreCase("SMS Report")) {
			    	findPanel.add(from_label);
					from_label.setText("From :");
					findPanel.add(from_label);
					findPanel.add(datePickerFrom);
					to_label.setText("To :");
					findPanel.add(to_label);
					findPanel.add(datePickerTo);
					findPanel.remove(academicYear_combo);
					findPanel.remove(academic_label);
					findPanel.remove(dateTime_label);
					findPanel.remove(format_label);
					findPanel.remove(DD_text);
					findPanel.remove(slashDD_label);
					findPanel.remove(MM_text);
					findPanel.remove(slashMM_label);
					findPanel.remove(YYYY_text);
					findPanel.remove(hh_text);
					findPanel.remove(colon_label);
					findPanel.remove(min_text);
					findPanel.revalidate();
					findPanel.repaint();
				} else if (smsType.equalsIgnoreCase("Send Bulk SMS")) {
					String default_path = commonObj.getDriveName() + bundle.getString("TEMPLATE_PATH_"+sessionData.getDBName());
					JButton button = new JButton("Select File");
					JFileChooser fileChooser = new JFileChooser(default_path);
					BulkUploadSms bulkUploadSms = new BulkUploadSms();
					
					int returnValue = fileChooser.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
						String currentDirectory = fileChooser.getCurrentDirectory().toString();
						
						int reply = 0;
						reply = JOptionPane.showConfirmDialog(null, "Would You Like to upload "+absolutePath+"?", "Confirm", JOptionPane.YES_NO_OPTION);
						
						if (reply == JOptionPane.YES_OPTION) {
							try {
								bulkUploadSms.uploadSms(sessionData, absolutePath, currentDirectory, "");
//								LinkedHashMap findstudentMap = new LinkedHashMap();
//								frame.setVisible(false);
//								new SmsPage(sessionData, "", "", "", "", "", "", findstudentMap, false, "", "", "", "", "", "",
//										section, smsTypeClass, user_name, user_role,"","","");
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Error : "+ e1);
							}
						} else if (reply == JOptionPane.NO_OPTION) {
							JOptionPane.showMessageDialog(null, "You have cancelled to upload.");
						}
					}
				} else if (smsType.equalsIgnoreCase("Bulk SMS Report")) {
					findPanel.add(from_label);
					from_label.setText("From :");
					findPanel.add(from_label);
					findPanel.add(datePickerFrom);
					to_label.setText("To :");
					findPanel.add(to_label);
					findPanel.add(datePickerTo);
					findPanel.remove(academicYear_combo);
					findPanel.remove(academic_label);
					findPanel.remove(dateTime_label);
					findPanel.remove(format_label);
					findPanel.remove(DD_text);
					findPanel.remove(slashDD_label);
					findPanel.remove(MM_text);
					findPanel.remove(slashMM_label);
					findPanel.remove(YYYY_text);
					findPanel.remove(hh_text);
					findPanel.remove(colon_label);
					findPanel.remove(min_text);
					findPanel.revalidate();
					findPanel.repaint();
				} else if (smsType.equalsIgnoreCase("Bulk SMS Template")) {
					try {
						List bulkSmsTemplate = new ArrayList();
						bulkSmsTemplate.add("Name|Phone|Message|TemplateId");
						CreateExcel ce = new CreateExcel();
						ce.generateExcel(sessionData, "PRINTLIST", "Bulk SMS Template_", "", bulkSmsTemplate, true, "Bulk SMS Template", 1);
					} catch (Exception e1) {
						logger.info("Bulk SMS Template Exception = "+e1);
					}
				} else {
					gr_no_radio.setEnabled(true);
					grList_radio.setSelected(false);
					gr_no_text.setEditable(true);
					std_radio.setEnabled(true);
					admittedStd_combo.setEditable(true);
					admittedDiv_combo.setEnabled(true);
					academic_radio.setEnabled(true);
					findPanel.remove(grList_radio);
					findPanel.remove(grList_label);
					findPanel.remove(grList_text);
					findPanel.add(name_radio);
					findPanel.add(lastName_label);
					findPanel.add(lastName_text);
					findPanel.add(firstName_label);
					findPanel.add(firstName_text);
					findPanel.add(fatherName_label);
					findPanel.add(fatherName_text);
					findPanel.remove(dateTime_label);
					findPanel.remove(format_label);
					findPanel.remove(DD_text);
					findPanel.remove(slashDD_label);
					findPanel.remove(MM_text);
					findPanel.remove(slashMM_label);
					findPanel.remove(YYYY_text);
					findPanel.remove(hh_text);
					findPanel.remove(colon_label);
					findPanel.remove(min_text);
					findPanel.remove(from_label);
					findPanel.remove(datePickerFrom);
					findPanel.remove(to_label);
					findPanel.remove(datePickerTo);
					findPanel.add(academicYear_combo);
					findPanel.add(academic_label);
					findPanel.revalidate();
					findPanel.repaint();
				}

			}
		});

		if(smsTypeClass.equalsIgnoreCase("Update")){
			gr_no_text.setEditable(false);
			std_radio.setEnabled(false);
			admittedStd_combo.setEnabled(false);
			admittedDiv_combo.setEnabled(false);
			academic_radio.setEnabled(false);
			academicYear_combo.setEnabled(false);
		} else{
			std_radio.setSelected(true);
			name_radio.setSelected(false);
			academic_radio.setEnabled(true);
			academicYear_combo.setEnabled(true);
			gr_no_text.setEnabled(true);
			admittedStd_combo.setEnabled(true);
			admittedDiv_combo.setEnabled(true);
			lastName_text.setEnabled(false);
			firstName_text.setEnabled(false);
			fatherName_text.setEnabled(false);
		}
		// ///////////////////////
		// /////////////Submit//////////////
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(mainCentre + 200, 90, 130, 25);
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
				String smsType = "";
				String grList = "";
				String dd = "";
				String mm = "";
				String yyyy = "";
				String hh = "";
				String min = "";
				String dateTime = "";
				String fromDate = "";
				String toDate = "";
				boolean gr_radioSelected = false;
				boolean std_radioSelected = false;
				boolean name_radioSelected = false;
				boolean year_radioSelected = false;

				gr_radioSelected = gr_no_radio.isSelected();
				std_radioSelected = std_radio.isSelected();
				name_radioSelected = name_radio.isSelected();
				year_radioSelected = academic_radio.isSelected();
				grList = grList_text.getText();

				gr_no = gr_no_text.getText();
				lastName = lastName_text.getText();
				firstName = firstName_text.getText();
				fatherName = fatherName_text.getText();
				std = (String) admittedStd_combo.getSelectedItem();
				div = (String) admittedDiv_combo.getSelectedItem();
				smsType = (String) sms_combo.getSelectedItem();
				academicSel = (String) academicYear_combo.getSelectedItem();
				
				Date selectedFromDate = (Date) datePickerFrom.getModel().getValue();
		        Date selectedToDate = (Date) datePickerTo.getModel().getValue();
		        if(smsType.equalsIgnoreCase("SMS Report")){
		        	fromDate = commonObj.dateToYYYYMMDD(selectedFromDate);
			        toDate = commonObj.dateToYYYYMMDD(selectedToDate);
			        String nextDay = (Integer.parseInt(toDate.substring(toDate.lastIndexOf("/")+1))+1)+"";
			        toDate = toDate.substring(0, toDate.lastIndexOf("/")+1)+nextDay;
		        }

				if (std.equalsIgnoreCase("All")) {
					std = "";
				}
				if (div.equalsIgnoreCase("All")) {
					div = "";
				}
				if (academicSel.equalsIgnoreCase("Select") || !academic_radio.isSelected()) {
					academicSel = "";
				}

				logger.info("smsType:" + smsType);

				if(smsType.equalsIgnoreCase("Check Balance")){
					JFrame f = new JFrame("Check Balance in progress. Don't Close");
					f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
				    f.setSize(400, 0);
				    f.setResizable(false);
				    f.setVisible(true);
				    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					String retBalance = commonObj.checkHspSmsBalance(sessionData);
					f.setVisible(false);
					JOptionPane.showMessageDialog(null, "SMS Balance :: " + retBalance);
					validateFields = false;
				}
				else if(smsType.equalsIgnoreCase("Bulk SMS Report")){
					try {
						List bulkReportDataList = new ArrayList();
				        fromDate = commonObj.dateToYYYYMMDD(selectedFromDate);
				        toDate = commonObj.dateToYYYYMMDD(selectedToDate);
				        String nextDay = (Integer.parseInt(toDate.substring(toDate.lastIndexOf("/")+1))+1)+"";
				        toDate = toDate.substring(0, toDate.lastIndexOf("/")+1)+nextDay;
				        
			        	JFrame f = new JFrame("Bulk SMS Report downlaod in progress. Don't Close");
						f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
					    f.setSize(500, 0);
					    f.setResizable(false);
					    f.setVisible(true);
					    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					    if (dbValidate.connectDatabase(sessionData)) {
					    	bulkReportDataList = dbValidate.bulkSmsReport(sessionData, academicYearClass, "", smsType, "", fromDate, toDate);
					    }
			        	f.setVisible(false);
						
					} catch (Exception e1) {
						logger.info("Bulk SMS Report Exception = "+e1);
					} finally{
						dbValidate.closeDatabase(sessionData);
					}
					validateFields = false;
				} 
				else{
					if (gr_radioSelected && !smsType.equalsIgnoreCase("Update")) {
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
					} else if (name_radioSelected && !smsType.equalsIgnoreCase("Update")) {
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
					} else if (std_radioSelected && !smsType.equalsIgnoreCase("Update")) {
						gr_no = "";
						lastName = "";
						firstName = "";
						fatherName = "";
					} else if (!year_radioSelected && !smsType.equalsIgnoreCase("Update")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select any one option");
					}
					if (year_radioSelected && !smsType.equalsIgnoreCase("Update")) {
						if (academicSel.equalsIgnoreCase("") || academicSel.equalsIgnoreCase("Select")) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please select Year");
						}
					}
					if (validateFields && (smsType.equalsIgnoreCase("Select") || smsType.equalsIgnoreCase(""))) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select SMS Type");
					}
				}
				if(smsType.equalsIgnoreCase("Schedule SMS")){
					dd = DD_text.getText();
					mm = MM_text.getText();
					yyyy =YYYY_text.getText();
					hh = hh_text.getText();
					min = min_text.getText();
					dateTime = dd+"/"+mm+"/"+yyyy;
					
					if(dd.equalsIgnoreCase("") || mm.equalsIgnoreCase("") || yyyy.equalsIgnoreCase("") || hh.equalsIgnoreCase("") || min.equalsIgnoreCase("")){
						JOptionPane.showMessageDialog(null, "Please enter all fields for date & time");
						validateFields = false;
					}
					else if(!commonObj.validateDate(dateTime)){
						JOptionPane.showMessageDialog(null, "Please enter valid date & time");
						validateFields = false;
					}
					else{
						dateTime = yyyy+"-"+mm+"-"+dd+" "+hh+":"+min;
					}
				}
				logger.info("academicSel == " + academicSel);
				logger.info("validateFields == " + validateFields);
				List<String> studentList = null;
				LinkedHashMap retStudentMap = new LinkedHashMap();
				if (validateFields) {
					try {
						if (dbValidate.connectDatabase(sessionData)) {
							if(smsType.equalsIgnoreCase("SMS Report")){
							retStudentMap = dbValidate.findSmsReport(sessionData, gr_no, std, div, lastName, firstName, fatherName,
									academicSel, section, smsType, grList, fromDate, toDate);
							} 
							else 
							{
								retStudentMap = dbValidate.findStudentData(sessionData, gr_no, std, div, lastName, firstName, fatherName,
									academicSel, section, smsType, grList, fromDate, toDate);
							}
							int listSize = retStudentMap.size();
							logger.info("No of students found :: " + listSize);

							if (listSize > 0) {
								frame.setVisible(false);
								new SmsPage(sessionData, gr_no, std, div, lastName, firstName, fatherName,
										retStudentMap, false, "", "", "", "", "", academicSel, section, smsType, user_name,
										user_role,"","",dateTime);
							} else {
								JOptionPane.showMessageDialog(null, "No data found");
							}
						}
					} catch (Exception e1) {
						boolean deleteFlag = false;
						try {
							if (dbValidate.connectDatabase(sessionData)) {
								deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role,
										section);

								if (deleteFlag) {
									new Student(sessionData, section, user_name, user_role);
									frame.setVisible(false);
								} else {
									logger.info("Exception while deleting form.");
								}
							}
						} catch (Exception e12) {
							logger.info("Exception logoutButton =>>" + e12);
						} finally {
							dbValidate.closeDatabase(sessionData);
						}
						logger.info("Exception FindStudent form ===>>" + e1);
					} finally {
						dbValidate.closeDatabase(sessionData);
					}
				}
			}

		});
		// /////////////Reset//////////////
		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		resetButton.setBounds(mainCentre + 375, 90, 130, 25);
		findPanel.add(resetButton);

		resetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				logger.info("Cliked Reset in findLeaving cert");
				LinkedHashMap findstudentMap = new LinkedHashMap();
				frame.setVisible(false);
				new SmsPage(sessionData, "", "", "", "", "", "", findstudentMap, false, "", "", "", "", "", "",
						section, smsTypeClass, user_name, user_role,"","","");
			}
		});
		
//		JLabel note_label = new JLabel("Note : Delivery status can be check only for last 2 days. * indicates sms delivery can't be checked.");
//		note_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//		note_label.setForeground(Color.RED);
//		note_label.setBounds(60, 110, 900, 50);
//		findPanel.add(note_label);
		
		// /////////////CheckBalance//////////////
		/*JButton checkBalanceButton = new JButton("Check Balance");
		checkBalanceButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		checkBalanceButton.setBounds(mainCentre + 240, 90, 160, 25);
		findPanel.add(checkBalanceButton);

		checkBalanceButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String retBalance = commonObj.checkSmsBalance();
				JOptionPane.showMessageDialog(null, "SMS Balance :: " + retBalance);
			}
		});*/

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
		if (foundStudentMap.size() > 0) {
			int listSize = foundStudentMap.size();
			logger.info("foundStudentMap==>" + listSize);

			final JRadioButton all_radio = new JRadioButton();
			all_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			all_radio.setBounds(40, 13, 20, 20);
			all_radio.setSelected(setSelected);
			if(smsTypeClass.equalsIgnoreCase("SMS Report")){
				all_radio.setEnabled(false);
				all_radio.setSelected(true);
			}
			dataPanel.add(all_radio);

			JLabel sr_label = new JLabel("Roll. No.");
			sr_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sr_label.setBounds(65, 00, 120, 50);
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
			pipe_label3.setBounds(630, 00, 120, 50);
			dataPanel.add(pipe_label3);

			JLabel lc_label = new JLabel("Contact No.");
			lc_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			lc_label.setBounds(650, 00, 120, 50);
			dataPanel.add(lc_label);

			JLabel pipe_label4 = new JLabel("|");
			pipe_label4.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label4.setBounds(750, 00, 120, 50);
			dataPanel.add(pipe_label4);
			
			JLabel status_label = new JLabel("Last SMS Status");
			status_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			status_label.setBounds(770, 00, 120, 50);
			dataPanel.add(status_label);
			
			JLabel pipe_label5 = new JLabel("|");
			pipe_label5.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label5.setBounds(930, 00, 120, 50);
			dataPanel.add(pipe_label5);
			
			JLabel date_label = new JLabel("Date Sent/Schedule");
			date_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			date_label.setBounds(940, 00, 300, 50);
			dataPanel.add(date_label);

			JLabel line_label = new JLabel(
					"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			line_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			line_label.setBounds(30, 13, 1200, 50);
			dataPanel.add(line_label);

			if (listSize > 0) {
				int j = 0;
				int l = 0;
				int i = 0;
				
				final JRadioButton[] sr_radio = new JRadioButton[listSize];
				JLabel[] sr_labels = new JLabel[listSize];
				JLabel[] pipe_labels1 = new JLabel[listSize];
				final JLabel[] gr_labels = new JLabel[listSize];
				JLabel[] pipe_labels2 = new JLabel[listSize];
				JLabel[] name_labels = new JLabel[listSize];
				JLabel[] line_labels = new JLabel[listSize];
				JLabel[] pipe_labels3 = new JLabel[listSize];
				JLabel[] pipe_labels4 = new JLabel[listSize];
				JLabel[] pipe_labels5 = new JLabel[listSize];
				JLabel[] pipe_labels6 = new JLabel[listSize];
				final JLabel[] contact1_labels = new JLabel[listSize];
				JButton[] status = new JButton[listSize];
				JLabel[] date_labels = new JLabel[listSize];

				String rollNo = "", stdDisp = "", divDisp = "";
				String name = "";
				String contact1 = "";
				String contact2 = "";
				String excelData = "";
				
				Set set = foundStudentMap.entrySet();
				Iterator n = set.iterator();
				LinkedHashMap studenthmap = new LinkedHashMap();
				
				while(n.hasNext()) {
					j = j + 30;
					l = j + 50;
					int tokenSize = 0;
					int m = 0;
					
					Map.Entry me = (Map.Entry)n.next();
					studenthmap = (LinkedHashMap) me.getValue();
					tokenSize = studenthmap.size();
					rollNo = (String) studenthmap.get("roll_no") == null ? " ": (String) studenthmap.get("roll_no");
					if(rollNo.equalsIgnoreCase("")){
						rollNo = "-";
					}
					stdDisp = (String) studenthmap.get("std") == null ? " ": (String) studenthmap.get("std");
					divDisp = (String) studenthmap.get("div") == null ? " ": (String) studenthmap.get("div");
					final String grNo = (String) studenthmap.get("gr_no");
					name = (String) studenthmap.get("name") == null ? " ": (String) studenthmap.get("name");
					if(name.equalsIgnoreCase("")){
						name = "-";
					}
					contact1 = (String) studenthmap.get("contact1");
					if(contact1.trim().equalsIgnoreCase("")){
						contact1 = (String) studenthmap.get("contact2");
					}
					
					final String sender = studenthmap.get("sender") == null ? " ": (String) studenthmap.get("sender");
					String delivery = studenthmap.get("status") == null ? " ": (String) studenthmap.get("status");
					if(delivery != null && delivery.equalsIgnoreCase("S")){
						delivery = "DELIVERED";
					}
					final String smsStatus = delivery;
					final String smsId = studenthmap.get("messageId") == null ? " ": (String) studenthmap.get("messageId");
					final String phone = studenthmap.get("contact1") == null ? " ": (String) studenthmap.get("contact1");
					final String message = studenthmap.get("message") == null ? " ": (String) studenthmap.get("message");
					final String messageType = studenthmap.get("messageType") == null ? " ": (String) studenthmap.get("messageType");
					final String smsDate = studenthmap.get("smsDate") == null ? " ": (String) studenthmap.get("smsDate");
					excelData = grNo+"|"+rollNo+"|"+name+"|"+contact1+"|"+smsStatus.toUpperCase()+"|"+message+"|"+smsId+"|"+messageType+"|"+smsDate;
					reportDataList.add(excelData);
					sr_radio[i] = new JRadioButton();
					sr_radio[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sr_radio[i].setBounds(40, j + 13, 20, 20);
					sr_radio[i].setSelected(setSelected);
					if(smsTypeClass.equalsIgnoreCase("SMS Report")){
						sr_radio[i].setEnabled(false);
						sr_radio[i].setSelected(true);
					}
					dataPanel.add(sr_radio[i]);

					sr_labels[i] = new JLabel(rollNo);
					sr_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sr_labels[i].setToolTipText(stdDisp+"-"+divDisp);
					sr_labels[i].setBounds(90, j, 120, 50);
					dataPanel.add(sr_labels[i]);

					pipe_labels1[i] = new JLabel("|");
					pipe_labels1[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels1[i].setBounds(130, j, 120, 50);
					dataPanel.add(pipe_labels1[i]);

					gr_labels[i] = new JLabel(grNo);
					gr_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					gr_labels[i].setBounds(160, j, 120, 50);
					dataPanel.add(gr_labels[i]);
					allGrList.add(grNo);

					pipe_labels2[i] = new JLabel("|");
					pipe_labels2[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels2[i].setBounds(230, j, 120, 50);
					dataPanel.add(pipe_labels2[i]);

					name_labels[i] = new JLabel(name);
					name_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					name_labels[i].setBounds(250, j, 350, 50);
					name_labels[i].setToolTipText(name);
					dataPanel.add(name_labels[i]);

					pipe_labels3[i] = new JLabel("|");
					pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels3[i].setBounds(630, j, 120, 50);
					dataPanel.add(pipe_labels3[i]);

					contact1_labels[i] = new JLabel(contact1);
					contact1_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					contact1_labels[i].setBounds(650, j + 12, 100, 20);
					contact1_labels[i].setToolTipText(contact1);
					dataPanel.add(contact1_labels[i]);

					pipe_labels4[i] = new JLabel("|");
					pipe_labels4[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels4[i].setBounds(750, j, 120, 50);
					dataPanel.add(pipe_labels4[i]);
					
					status[i] = new JButton(smsStatus.toUpperCase());
					if(smsStatus.equalsIgnoreCase("DELIVERED")){
						status[i].setEnabled(false);
						status[i].setBackground(Color.GREEN);
					}
					else if(smsStatus.contains("*")){
						status[i].setEnabled(false);
					}
					String sms = message.replace("newLine", "<br>");
					sms = commonObj.revertCommaApostrophy(sms);
					status[i].setToolTipText("<html><p width=\"200\">" +sms+"</p></html>");
					status[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					status[i].setBounds(770, j + 12, 150, 20);
					if(studenthmap.get("status") != null){
						dataPanel.add(status[i]);
					}
					
					final int k1=i;
					status[i].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							try{
								int daysLeft = 0;
								String status = "";
								SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
						        Date todayDate = formatter.parse(commonObj.getCurrentDateInHHmm().toString());
						        Date expiryDate = formatter.parse(commonObj.dateFormatFromyyyymmddhhmmToddmmyyyyhhmm(smsDate));
						        daysLeft = commonObj.daysBetween(expiryDate, todayDate);
						        boolean checkStatus = commonObj.dateDifferenceInMinutes(todayDate, expiryDate);
						        if(checkStatus && daysLeft <= daysCheckStatus){
						        	status = commonObj.checkHSPDeliveryStatus(sessionData, smsStatus, smsId, contact1_labels[k1].getText().toString(), messageType, grNo, section);
						        }
						        else if(!checkStatus){
						        	status = "SUBMITTED";
						        }
							/*int daysLeft = 0;
							String status = "";
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					        Date todayDate = formatter.parse(commonObj.getCurrentDateInHHmm().toString());
					        Date expiryDate = formatter.parse(commonObj.dateFormatFromyyyymmddhhmmToddmmyyyyhhmm(smsDate));
					        daysLeft = commonObj.daysBetween(expiryDate, todayDate);
					        if(daysLeft > 0 && daysLeft <= daysCheckStatus){
					        	status = commonObj.checkHSPDeliveryStatus(sessionData, smsStatus, smsId, contact1_labels[k1].getText().toString(), messageType, grNo, section);
					        }
					        else if(daysLeft < 0){
					        	status = "SUBMITTED";
					        }*/
							if(!status.contains("internet") && !status.contains("invalid")){
								JButton btn = (JButton)e.getSource();
					            btn.setText(status);
							}
				            JOptionPane.showMessageDialog(null, status);
							}catch(Exception ex){
								logger.error("Exception while checking the status = " + ex);
							}
						}
					});

					pipe_labels5[i] = new JLabel("|");
					pipe_labels5[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels5[i].setBounds(930, j, 120, 50);
					dataPanel.add(pipe_labels5[i]);
					
					date_labels[i] = new JLabel(smsDate);
					date_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					date_labels[i].setBounds(950, j + 12, 400, 20);
					dataPanel.add(date_labels[i]);
					
					line_labels[i] = new JLabel(
							"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					line_labels[i].setBounds(30, j + 10, 1200, 50);
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
								if (!contact1_labels[k].getText().trim().equalsIgnoreCase("")) {
									duplicateLCList.add(gr_labels[k].getText());
									logger.info(gr_labels[k].getText() + " added");
								}
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
								if (!contact1_labels[k].getText().trim().equalsIgnoreCase("")) {
									duplicateLCList.remove(gr_labels[k].getText());
									logger.info(gr_labels[k].getText() + " removed");
								}
								all_radio.setSelected(false);
								setSelected = false;
								selectAllCount--;
							}
							logger.info("selectAllCount ==>" + selectAllCount);
							if (selectAllCount == foundStudentMap.size()) {
								all_radio.setSelected(true);
							}
							logger.info("allGrList end of for loop ==>" + allGrList.size());
						}
					});
					i++;
				}
				// /////print data starts/////////////////////////////////
				
				if(!smsTypeClass.equalsIgnoreCase("SMS Report")){
					if(smsTypeClass.equalsIgnoreCase("Schedule SMS")){
						JLabel smsSchedule_label = new JLabel("SMS will be scheduled at "+dateTimeClass+" (YYYY-MM-DD HH:MIN)");
						smsSchedule_label.setFont(new Font("Book Antiqua", Font.BOLD, 14));
						smsSchedule_label.setBounds(170, l - 15, 600, 50);
						smsSchedule_label.setForeground(Color.RED);
						
						dataPanel.add(smsSchedule_label);
					}
					
					JLabel sms_label = new JLabel("SMS Text ");
					sms_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sms_label.setBounds(40, l + 10, 150, 50);
					dataPanel.add(sms_label);
	
					String smsTemplateStr = "Please Select Template";
					try {
						if(dbValidate.connectDatabase(sessionData)){
							smsTemplateStr = smsTemplateStr +"|"+ dbValidate.getSMSTemplateStr(sessionData);
							smsTemplateIdMap = dbValidate.getSMSTemplateIdMap(sessionData);
						}
					} catch (Exception e1) {
			            logger.error("Exception while getting sms template details ==>>>" + e1);
			        }
					
					String[] smsTemplateList = smsTemplateStr.split("\\|");
					final JComboBox smsTemplate_combo = new JComboBox(smsTemplateList);
					smsTemplate_combo.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
					smsTemplate_combo.setBounds(140, l + 25, 900, 25);
					smsTemplate_combo.setSelectedItem(smsTypeClass);
					dataPanel.add(smsTemplate_combo);
					
					final JTextArea sms_textArea = new JTextArea();
					if(true) {
						sms_textArea.setFont(new Font("FreeSans", Font.PLAIN, 16));//Shivaji02
					}
					else {
						sms_textArea.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
					}
					sms_textArea.setLineWrap(true);
					sms_textArea.setVisible(true);
					JScrollPane scroll = new JScrollPane(sms_textArea);
					scroll.setBounds(140, l + 60, 900, 60);
					scroll.setSize( 900, 60 );
					dataPanel.add(scroll);
										
					JLabel char_label = new JLabel("Characters: ");
					char_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					char_label.setBounds(40, l + 110, 150, 50);
					dataPanel.add(char_label);
					
					final JLabel charLength_label = new JLabel("0");
					charLength_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					charLength_label.setBounds(135, l + 110, 150, 50);
					dataPanel.add(charLength_label);
					
					JLabel noOfSms_label = new JLabel("No of SMS: ");
					noOfSms_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					noOfSms_label.setBounds(40, l + 130, 150, 50);
					dataPanel.add(noOfSms_label);
					
					final JLabel smsCount_label = new JLabel("0");
					smsCount_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					smsCount_label.setBounds(130, l + 130, 150, 50);
					dataPanel.add(smsCount_label);
					
					smsTemplate_combo.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {

							try{
								String smsTemplateSel = (String) smsTemplate_combo.getSelectedItem();
								sms_textArea.removeAll();
								sms_textArea.setText(((LinkedHashMap<?, ?>) smsTemplateIdMap.get(smsTemplateSel)).get("messageBody").toString());
								
								if(!smsTemplateSel.equalsIgnoreCase("Please Select Template")) {
									String noOfCharacters = "";
									double smsDouble = 0.0;
									int smsLength = 0;
									
									int noOfSms = 0;
									noOfCharacters = sms_textArea.getText();
									smsLength = noOfCharacters.length();
									//160 147 152
									charLength_label.setText(""+noOfCharacters.length());
									if(smsLength > 0 && smsLength <= 160)
									{
										noOfSms = 1;
										smsCount_label.setText(""+noOfSms);
										smsLength = smsLength - 160;
									}
									else if(smsLength > 160){
										noOfSms = 1;
									}
									smsLength = smsLength - 160;
									
									if(smsLength > 0 && smsLength <= 147)
									{
										noOfSms = 2;
										smsCount_label.setText(""+noOfSms);
										smsLength = smsLength - 147;
									}
									else if(smsLength > 147){
										noOfSms = 2;
									}
									smsLength = smsLength - 147;
									if(smsLength > 0){
										smsDouble = smsLength / 152.0;
										noOfSms = noOfSms + (int) Math.ceil(smsDouble);
										smsCount_label.setText(""+noOfSms);
									}
								}
							} catch (Exception e1) {
								logger.info("Exception insertFormData ===>>>" + e1);
							} finally {
								dbValidate.closeDatabase(sessionData);
							}

						}
					});
					
//					sms_textArea.addFocusListener(new java.awt.event.FocusAdapter() {
//						String noOfCharacters = "";
//						double smsDouble = 0.0;
//						int smsLength = 0;
//						public void keyReleased(KeyEvent e) {
//							int noOfSms = 0;
//							noOfCharacters = sms_textArea.getText();
//							smsLength = noOfCharacters.length();
//							//160 147 152
//							charLength_label.setText(""+noOfCharacters.length());
//							if(smsLength > 0 && smsLength <= 160)
//							{
//								noOfSms = 1;
//								smsCount_label.setText(""+noOfSms);
//								smsLength = smsLength - 160;
//							}
//							else if(smsLength > 160){
//								noOfSms = 1;
//							}
//							smsLength = smsLength - 160;
//							
//							if(smsLength > 0 && smsLength <= 147)
//							{
//								noOfSms = 2;
//								smsCount_label.setText(""+noOfSms);
//								smsLength = smsLength - 147;
//							}
//							else if(smsLength > 147){
//								noOfSms = 2;
//							}
//							smsLength = smsLength - 147;
//							if(smsLength > 0){
//								smsDouble = smsLength / 152.0;
//								noOfSms = noOfSms + (int) Math.ceil(smsDouble);
//								smsCount_label.setText(""+noOfSms);
//							}
//						}
//					});
					
					final JRadioButton enableSave_radio = new JRadioButton();
					enableSave_radio.setBounds(40, l + 180, 20, 25);
					dataPanel.add(enableSave_radio);
					
					JLabel enableSave_label = new JLabel("Please click to enable "+smsTypeClass+" option");
					enableSave_label.setForeground(Color.RED);
					enableSave_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					enableSave_label.setBounds(75, l + 180, 600, 25);
					dataPanel.add(enableSave_label);
					
					final JButton sendSmsButton = new JButton("Send");
					sendSmsButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sendSmsButton.setBounds(mainCentre + 50, l + 180, 130, 25);
					dataPanel.add(sendSmsButton);
					sendSmsButton.setEnabled(false);
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
							logger.info(" list size  : " + foundStudentMap.size());
							Date today = new Date();
					        
							new SmsPage(sessionData, grClass, stdClass, divClass, lastClass, firstClass,
									fatherClass, foundStudentMap, pass_selected, "",
									"", "", "", "", academicYearClass, section, smsTypeClass, user_name, user_role,"","",dateTimeClass);
						}
					});
	
					enableSave_radio.addActionListener(new ActionListener() {
	
						public void actionPerformed(ActionEvent e) {
	
							try {
								if(dbValidate.connectDatabase(sessionData)){
									boolean radio_selected = enableSave_radio.isSelected();
									
									if (radio_selected) {
										
										int reply = 0;
										reply = JOptionPane.showConfirmDialog(null, "Would You Like to "+smsTypeClass+"?", "Confirm Send", JOptionPane.YES_NO_OPTION);
										if (reply == JOptionPane.YES_OPTION) {
											sendSmsButton.setEnabled(true);
											enableSave_radio.setSelected(true);
										}
										else{
											sendSmsButton.setEnabled(false);
											enableSave_radio.setSelected(false);
										}
									} 
									else {
										sendSmsButton.setEnabled(false);
										enableSave_radio.setSelected(false);
									}
								}
							} catch (Exception e1) {
								logger.info("Exception insertFormData ===>>>" + e1);
							} finally {
								dbValidate.closeDatabase(sessionData);
							}
						}
					});
				
					// //////save & print action
					// starts//////////////////////////////////////
					sendSmsButton.addActionListener(new ActionListener() {
	
						public void actionPerformed(ActionEvent e) {
	
							logger.info("save & print action starts");
							boolean flagLeaving = true;
							String smsStr = "sent";
							Date today = new Date();
							String smsTemplate = (String) smsTemplate_combo.getSelectedItem();
							String smsText = sms_textArea.getText();
							String smsTempId = ((LinkedHashMap<?, ?>) smsTemplateIdMap.get(smsTemplate)).get("templateId").toString();
							List<String> passGrList = new ArrayList();
	
							if (!setSelected && entrytCnt == 0) {
								allGrList.clear();
								passGrList = allGrList;
							}
							passGrList = allGrList;
							
							if(smsText.length() > 159){
								double smsDouble = smsText.length() / 159.0;
								int noOfSms = (int) Math.ceil(smsDouble);
								int reply = JOptionPane.showConfirmDialog(null, "Would You Like to send "+noOfSms+" message for each number?", "Confirm", JOptionPane.YES_NO_OPTION);
								if (reply == JOptionPane.NO_OPTION) {
									flagLeaving = false;
								}
							}
							if(smsTypeClass.equalsIgnoreCase("Schedule SMS")){
								smsStr = "scheduled";
								int replySchedule = JOptionPane.showConfirmDialog(null, "Would You Like to schedule sms at \n"+dateTimeClass+" (yyyy-mm-dd hh:min)", "Confirm", JOptionPane.YES_NO_OPTION);
								if (replySchedule == JOptionPane.NO_OPTION) {
									flagLeaving = false;
								}
							}
							
							if (passGrList.size() == 0) {
								JOptionPane.showMessageDialog(null, "No Student selected");
							} else if (flagLeaving) {
								try {
									//use sendBhashSms method for bhashsms & sendHspSms for hsp sms
									String smsResponse = commonObj.sendHspSms(sessionData, passGrList, foundStudentMap, smsText, smsTempId, section, smsTypeClass, 
											academicYearClass, stdClass, divClass, dateTimeClass, "");
									if(!smsResponse.contains("connecting")){
										smsResponse = "SMS "+smsStr+" successfully...";
									}
									JOptionPane.showMessageDialog(null, smsResponse);
									frame.setVisible(false);
									LinkedHashMap findstudentMap = new LinkedHashMap();
									new SmsPage(sessionData, grClass, stdClass, divClass, lastClass, firstClass,
											fatherClass, findstudentMap, false, "", "", "", "", "", yearClass, section,
											smsTypeClass, user_name, user_role,"","", "");
								} catch (Exception e1) {
									logger.info("Exception e == " + e1);
									e1.printStackTrace();
								}
							}
						}
					});
				}
				else {
					JButton excelButton = new JButton("Open in Excel");
					excelButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
					excelButton.setBounds(mainCentre - 50, l + 50, 180, 25);
					dataPanel.add(excelButton);
					excelButton.setEnabled(true);
					final CreateExcel ce = new CreateExcel();
					
					excelButton.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							
							ce.generateExcel(sessionData, "PRINTLIST", "CATEGORY_WISE", "", reportDataList, true, secName+" SMS Report  STD:"+stdClass+"  DIV:"+divClass, 1);
						}
					});
				}
				// //////save & print action ends//////////
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
