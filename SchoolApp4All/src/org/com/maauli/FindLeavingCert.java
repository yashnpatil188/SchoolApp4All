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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.lowagie.text.DocumentException;

public class FindLeavingCert extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JComboBox studentcombo;

	static String std = "";
	
	static String lcTypeList = "";

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

	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static SessionData sessionData = new SessionData();

	static Logger logger = Logger.getLogger(FindLeavingCert.class.getName());

	static JFrame frame = null;

	static int retCount = 0;

	static int scrollHeight = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

	static List<String> allGrList = new ArrayList();

	static List<String> selectedAllGrList = new ArrayList();

	static List<String> duplicateLCList = new ArrayList();

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

	static String lcTypeClass = "Select";

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
    static boolean lcDiscrepancyFlag = false;
	private static List<String> foundStudentList;
	static String lc_visible_master_only = "";
    static boolean lc_visible_flag = true;

	public FindLeavingCert(SessionData sessionData1, String retGr_no, String retStd, String retDiv, String retLastName,
			String retFirstName, String retFatherName, List<String> retStudentList, boolean retSelected,
			String dtLeaving, String dtIssue, String reasonT, String remarkT, String conductT, String academicYear,
			String sec, String retLcType, String retUserName, String retUserRole, String progressT, String remark2T) {

		logger.info("======FindLeavingCert========");
		System.gc();
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
		dateLeaving = dtLeaving;
		dateIssue = dtIssue;
		conduct = conductT;
		reason = reasonT;
		remark = remarkT;
//		remark2 = remark2T;
		progress = "";
		progress = progressT;
		entrytCnt = 0;

		section = sec;
		logger.info("section :: " + section);
		std = bundle.getString(section.toUpperCase() + "_STD");
		logger.info("std :: " + std);
		div = bundle.getString(section.toUpperCase() + "_DIV");
		logger.info("div :: " + div);
		secName = bundle.getString(section.toUpperCase() + "_SEC");
		logger.info("secName :: " + secName);
		lcTypeList = bundle.getString("LC_TYPE_LIST");
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
        feeStatusList = bundle.getString("FEE_STATUS_LIST");
        
        if(lc_visible_master_only.equalsIgnoreCase("true") && !sessionData.getConfigMap().get("SchoolApp_IP").contains("127.0.0.1")) {
    		lc_visible_flag = false;
    	}
    	else {
    		lc_visible_flag = true;
    	}
        
		////////////////try block added for chabildas ls issue showing 2016-17///////
		try{
			lcDiscrepancyFlag = Boolean.valueOf(bundle.getString("LC_DISCREPANCY_"+sessionData.getAppType()));
		}
		catch(MissingResourceException m){
			logger.error("LC_DISCREPANCY flag missing");
		}
        
        currentYear = commonObj.getCurrentYear();
        remarkStr = remarkStr.replace("YYYY", currentYear);
        /*if(!stdClass.equalsIgnoreCase("")){
        	int stdPromoted = commonObj.RomanToInteger(stdClass)+1;
    		String romanStd = commonObj.IntegerToRoman("a"+stdPromoted);
            remarkStr = remarkStr.replace("promoted to Std ****", "promoted to Std "+romanStd);
            remarkStr = remarkStr.replace("Detained in std ****", "Detained in std "+stdClass);
        }*/
        
		try {
			if (dbValidate.connectDatabase(sessionData)) {
				String userActive = dbValidate.checkFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
				if (userActive.equalsIgnoreCase("") || userActive.equalsIgnoreCase(null)) {
					dbValidate.insertFormData(user_name, "LEAVING CERTIFICATE", user_role, section);
				}
			}
		} catch (Exception e1) {
			commonObj.logException(e1);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}

		if (!retLcType.equalsIgnoreCase("")) {
			lcTypeClass = retLcType;
		}
		
		/*if(foundStudentList != null){
			foundStudentList.clear();
		}*/
		allGrList.clear();
		duplicateLCList.clear();
		setSelected = retSelected;
		foundStudentList = retStudentList;
		retCount = foundStudentList.size();
		logger.info("foundStudentList size => " + retCount);
		if (setSelected) {
			selectAllCount = retCount;
		}
		scrollHeight = (retCount - 0) * 120; // to adjust the perfect scroll
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
					commonObj.logException(e1);
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
					commonObj.logException(e);
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

				boolean deleteFlag = false;
				try {
					if (dbValidate.connectDatabase(sessionData)) {
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
						dbValidate.closeDatabase(sessionData);

						if (deleteFlag) {
							new Student(sessionData, section, user_name, user_role);
							frame.setVisible(false);
						} else {
							logger.info("Exception while deleting form.");
						}
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});

		JButton staffButton = new JButton("Staff");
		staffButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		staffButton.setBounds(10, 150, 130, 35);
//		leftPanel.add(staffButton);

		staffButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean deleteFlag = false;
				try {
					if (dbValidate.connectDatabase(sessionData)) {
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
						dbValidate.closeDatabase(sessionData);

						if (deleteFlag) {
							new Welcome(sessionData, user_name, user_role);
							frame.setVisible(false);
						} else {
							logger.info("Exception while deleting form.");
						}
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});

		JButton academicButton = new JButton("Academic");
		academicButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		academicButton.setBounds(10, 200, 130, 35);
		leftPanel.add(academicButton);

		academicButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean deleteFlag = false;
				try {
					if (dbValidate.connectDatabase(sessionData)) {
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
						dbValidate.closeDatabase(sessionData);

						if (deleteFlag) {
							new Academic(sessionData, section, user_name, user_role);
							frame.setVisible(false);
						} else {
							logger.info("Exception while deleting form.");
						}
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});

		JButton accountButton = new JButton("Account");
		accountButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		accountButton.setBounds(10, 250, 130, 35);
//		leftPanel.add(accountButton);

		accountButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean deleteFlag = false;
				try {
					if (dbValidate.connectDatabase(sessionData)) {
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
						dbValidate.closeDatabase(sessionData);

						if (deleteFlag) {
							// new Welcome(user_name,user_role);
							// frame.setVisible(false);
						} else {
							logger.info("Exception while deleting form.");
						}
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});

		JButton searchButton = new JButton("Find");
		searchButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		searchButton.setBounds(10, 300, 130, 35);
		leftPanel.add(searchButton);

		searchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean deleteFlag = false;
				try {
					if (dbValidate.connectDatabase(sessionData)) {
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
						dbValidate.closeDatabase(sessionData);

						if (deleteFlag) {
							LinkedHashMap<String,LinkedHashMap<String, String>> studMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
							new FindStudent(sessionData, "", "", "", "", "", "", studMap, section, user_name,
									user_role, false);
							frame.setVisible(false);
						} else {
							logger.info("Exception while deleting form.");
						}
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
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

				boolean deleteFlag = false;
				try {
					if (dbValidate.connectDatabase(sessionData)) {
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
						dbValidate.closeDatabase(sessionData);

						if (deleteFlag) {
							LinkedHashMap<String,LinkedHashMap<String, String>> studentList = new LinkedHashMap<String,LinkedHashMap<String, String>>();
							new AdmissionFormNew(sessionData, section, user_name, user_role,"ADMISSION","",studentList);
							frame.setVisible(false);
						} else {
							logger.info("Exception while deleting form.");
						}
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});

		buttonX = buttonX + 160;
		JButton strengthButton = new JButton("Strength");
		strengthButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		strengthButton.setBounds(buttonX, 50, 150, 24); // 300, 50, 150, 24
		topbandPanel.add(strengthButton);

		strengthButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean deleteFlag = false;
				try {
					if (dbValidate.connectDatabase(sessionData)) {
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
						dbValidate.closeDatabase(sessionData);

						if (deleteFlag) {
							List studList = new ArrayList();
							new Strength(sessionData, "", "", "", section, user_name, user_role, studList, "", "");
							frame.setVisible(false);
						} else {
							logger.info("Exception while deleting form.");
						}
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});

		if(lc_visible_flag) {
			buttonX = buttonX + 160;
			JButton lcButton = new JButton("Leaving certificate");
			lcButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
			lcButton.setBounds(buttonX, 50, 150, 24);
			lcButton.setBackground(Color.GREEN);
			topbandPanel.add(lcButton);
		}

		buttonX = buttonX + 160;
		JButton bonafideButton = new JButton("Certificate");
		bonafideButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		bonafideButton.setBounds(buttonX, 50, 150, 24);
		topbandPanel.add(bonafideButton);

		bonafideButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean deleteFlag = false;
				try {
					if (dbValidate.connectDatabase(sessionData)) {
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
						dbValidate.closeDatabase(sessionData);

						if (deleteFlag) {
							frame.setVisible(false);
							List findBonafideList = new ArrayList();
							new FindBonafide(sessionData, "", "", "", "", "", "", findBonafideList, false, 
									"", section, "", user_name, user_role, false);
						} else {
							logger.info("Exception while deleting form.");
						}
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});

		buttonX = buttonX + 160;
		JButton PrintListButton = new JButton("Print List");
		PrintListButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		PrintListButton.setBounds(buttonX, 50, 150, 24); // 300, 50, 150, 24
		topbandPanel.add(PrintListButton);

		PrintListButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean deleteFlag = false;
				try {
					if (dbValidate.connectDatabase(sessionData)) {
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
						dbValidate.closeDatabase(sessionData);

						if (deleteFlag) {
							frame.setVisible(false);
							List studList = new ArrayList();
							new PrintList(sessionData, "", "", "", section, user_name, user_role, studList, "", "");
						} else {
							logger.info("Exception while deleting form.");
						}
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});

		JButton feeButton = new JButton("Fee Status");
		feeButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		feeButton.setBounds(940, 50, 150, 24); // 300, 50, 150, 24
//		topbandPanel.add(feeButton);

		feeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean deleteFlag = false;
				try {
					if (dbValidate.connectDatabase(sessionData)) {
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
						dbValidate.closeDatabase(sessionData);

						if (deleteFlag) {
							List studentList = new ArrayList();
							frame.setVisible(false);
							new FeeStatus(sessionData, "", "", "", "", "", "", "", "", "", section, studentList, false,"",true);
						} else {
							logger.info("Exception while deleting form.");
						}
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
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
					gr_no_text.setText(commonObj.appendZero(grString));
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
		JLabel admittedStd_label = new JLabel("Std :");
		admittedStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_label.setBounds(380, 00, 70, 50);
		findPanel.add(admittedStd_label);

		String stdSel = "";
		if (!stdClass.equalsIgnoreCase("")) {
			stdSel = stdClass;
			std = stdSel + "," + std;
		} else {
			std = "Select," + std;
		}
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

		String divSel = "";
		if (!divClass.equalsIgnoreCase("")) {
			divSel = divClass;
			div = divSel + "," + div;
		} else {
			div = "Select," + div;
		}
		// String admittedDivList[] = {divClass,"A","B","C","D"};
		String[] divList = div.split(",");
		final JComboBox admittedDiv_combo = new JComboBox(divList);
		admittedDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedDiv_combo.setBounds(580, 12, 100, 25);
		findPanel.add(admittedDiv_combo);

		// ///////////////////////////////
		JLabel lcType_label = new JLabel("LC Type :");
		lcType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lcType_label.setBounds(710, 00, 110, 50);
		findPanel.add(lcType_label);

		String lcSel = "";
		if (!lcTypeClass.equalsIgnoreCase("")) {
			lcSel = lcTypeClass;
			lcTypeList = lcSel + "," + lcTypeList;
		} else {
			lcTypeList = "Select," + lcTypeList;
		}
		if(sessionData.getUserRole().equalsIgnoreCase("ADMINISTRATOR")){
			lcTypeList = lcTypeList + ",Update";
		}
//		String lcList[] = { lcTypeClass, "Original", "Duplicate" };
		String[] lcList = lcTypeList.split(",");
		final JComboBox lc_combo = new JComboBox(lcList);
		lc_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lc_combo.setBounds(830, 12, 100, 25);
		findPanel.add(lc_combo);

		// /////////////Roll No.//////////////
		JLabel rollNo_label = new JLabel("Roll No :");
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
		// //////////////////////////////////
		// /////////////Gr List to update//////////////
		final JLabel grList_label = new JLabel("Gr List :");
		grList_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		grList_label.setBounds(60, 40, 120, 50);
		if(lcTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(grList_label);
		}

		final JTextField grList_text = new JTextField();
		grList_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		grList_text.setBounds(160, 50, 600, 25);
		if(lcTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(grList_text);
		}
		// /////////////Last Name//////////////
		final JLabel lastName_label = new JLabel("Last Name :");
		lastName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_label.setBounds(60, 40, 120, 50);
		if(!lcTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(lastName_label);
		}

		final JTextField lastName_text = new JTextField(lastClass);
		lastName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_text.setBounds(160, 50, 130, 25);
		if(!lcTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(lastName_text);
		}
		// //////////////////////////////////
		// /////////////First Name//////////////
		final JLabel firstName_label = new JLabel("First Name :");
		firstName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_label.setBounds(380, 40, 120, 50);
		if(!lcTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(firstName_label);
		}

		final JTextField firstName_text = new JTextField(firstClass);
		firstName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_text.setBounds(480, 50, 200, 25);
		if(!lcTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(firstName_text);
		}
		// //////////////////////////////////
		// /////////////Father Name//////////////
		final JLabel fatherName_label = new JLabel("Father Name :");
		fatherName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_label.setBounds(710, 40, 120, 50);
		if(!lcTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(fatherName_label);
		}

		final JTextField fatherName_text = new JTextField(fatherClass);
		fatherName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_text.setBounds(830, 50, 200, 25);
		if(!lcTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(fatherName_text);
		}
		// //////////////////////////////////
		// /////////GR radio///////////////
		final JRadioButton gr_no_radio = new JRadioButton();
		gr_no_radio.setBounds(30, 15, 20, 20);
		if(lcTypeClass.equalsIgnoreCase("Update")){
			gr_no_radio.setSelected(false);
			gr_no_radio.setEnabled(false);
		}
		findPanel.add(gr_no_radio);
		// /////////name radio///////////////30, 80, 120, 50
		final JRadioButton name_radio = new JRadioButton();
		name_radio.setBounds(30, 55, 20, 20);
		if(!lcTypeClass.equalsIgnoreCase("Update")){
			findPanel.add(name_radio);
		}
		// /////////grList radio///////////////30, 80, 120, 50
		final JRadioButton grList_radio = new JRadioButton();
		grList_radio.setBounds(30, 55, 20, 20);
		if(lcTypeClass.equalsIgnoreCase("Update")){
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
		JLabel academic_label = new JLabel("Year :");
		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academic_label.setBounds(60, 80, 70, 50);
		findPanel.add(academic_label);

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
		
		if (!yearClass.equalsIgnoreCase("")){
			yearList = yearClass + "," + yearList;
		}
		
//		String academicYearList[] = { yearClass, "2010-11", "2011-12", "2012-13", "2013-14", "2014-15" };
		String[] academicYearList = yearList.split(",");
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academicYear_combo.setBounds(160, 95, 120, 25);
		findPanel.add(academicYear_combo);

		if(!stdSel.equalsIgnoreCase("All") && !stdSel.equalsIgnoreCase("Select") && 
	    		!stdSel.equalsIgnoreCase("") && dbValidate.connectDatabase(sessionData)){
			admittedDiv_combo.removeAllItems();
			String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdSel, section, 
					"PRESENT_DIV", "PRESENT_STD", "class_allotment",academicYearClass);
			
			for (String retval: divAvailabe.split(",")) {
				admittedDiv_combo.addItem(retval);
			}
			admittedDiv_combo.setSelectedItem(divClass);
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
				from_text.setText("");
				from_text.setEnabled(false);
				to_text.setText("");
				to_text.setEnabled(false);
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
				from_text.setText("");
				from_text.setEnabled(false);
				to_text.setText("");
				to_text.setEnabled(false);
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
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}

			}
		});

		// //////////////////////////////////
		admittedDiv_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if ((String) admittedDiv_combo.getSelectedItem() != "") {
					from_text.setEnabled(true);
					to_text.setEnabled(true);
				} else {
					from_text.setText("");
					from_text.setEnabled(false);
					to_text.setText("");
					to_text.setEnabled(false);
				}

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
				from_text.setText("");
				from_text.setEnabled(false);
				to_text.setText("");
				to_text.setEnabled(false);
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
		
		////////////////////////////////////
		lc_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String lcType = lc_combo.getSelectedItem().toString();
				if (lcType.equalsIgnoreCase("Update")) {
					gr_no_radio.setEnabled(false);
					gr_no_radio.setSelected(false);
					gr_no_text.setEditable(false);
					std_radio.setEnabled(false);
					admittedStd_combo.setEnabled(false);
					admittedDiv_combo.setEnabled(false);
					grList_radio.setSelected(true);
					grList_radio.setEnabled(false);
					findPanel.add(grList_radio);
					findPanel.add(grList_label);
					findPanel.add(grList_text);
					findPanel.remove(name_radio);
					findPanel.remove(lastName_label);
					findPanel.remove(lastName_text);
					findPanel.remove(firstName_label);
					findPanel.remove(firstName_text);
					findPanel.remove(fatherName_label);
					findPanel.remove(fatherName_text);
					findPanel.revalidate();
					findPanel.repaint();
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
					findPanel.revalidate();
					findPanel.repaint();
				}

			}
		});

		if(lcTypeClass.equalsIgnoreCase("Update")){
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
			from_text.setEnabled(false);
			to_text.setEnabled(false);
			lastName_text.setEnabled(false);
			firstName_text.setEnabled(false);
			fatherName_text.setEnabled(false);
		}
		// ///////////////////////
		// /////////////Submit//////////////
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(mainCentre - 150, 90, 130, 25);
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
				String lcType = "";
				String grList = "";
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
				lcType = (String) lc_combo.getSelectedItem();
				academicSel = (String) academicYear_combo.getSelectedItem();

				if (std.equalsIgnoreCase("Select")) {
					std = "";
				}
				if (div.equalsIgnoreCase("Select")) {
					div = "";
				}
				if (academicSel.equalsIgnoreCase("Select") || !academic_radio.isSelected()) {
					academicSel = "";
				}

				logger.info("gr No.:" + gr_no);
				logger.info("lastName.:" + lastName);
				logger.info("firstName:" + firstName);
				logger.info("fatherName:" + fatherName);
				logger.info("std:" + std);
				logger.info("div:" + div);
				logger.info("lcType:" + lcType);

				// boolean checkGrNoFlag = false;

				if (gr_radioSelected && !lcType.equalsIgnoreCase("Update")) {
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
				} else if (name_radioSelected && !lcType.equalsIgnoreCase("Update")) {
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
				} else if (std_radioSelected && !lcType.equalsIgnoreCase("Update")) {
					gr_no = "";
					lastName = "";
					firstName = "";
					fatherName = "";

					if (std.equalsIgnoreCase("") || std.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Std");
					} else if ((std.equalsIgnoreCase("") || std.equalsIgnoreCase("Select")) && !div.equalsIgnoreCase("")
							&& !div.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Std");
					}
				} else if (!year_radioSelected && !lcType.equalsIgnoreCase("Update")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select any one option");
				}
				if(lcType.equalsIgnoreCase("Update") && grList.equalsIgnoreCase("")){
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please enter Gr List to update LC.");
				}
				if (year_radioSelected && !lcType.equalsIgnoreCase("Update")) {
					if (academicSel.equalsIgnoreCase("") || academicSel.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Year");
					}
				}
				if (validateFields && (lcType.equalsIgnoreCase("Select") || lcType.equalsIgnoreCase(""))) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select LC Type");
				}
				logger.info("academicSel == " + academicSel);
				logger.info("validateFields == " + validateFields);
				List<String> studentList;
				if (validateFields) {
					try {
						if (dbValidate.connectDatabase(sessionData)) {
							studentList = dbValidate.findStudentLC(sessionData, gr_no, std, div, lastName, firstName, fatherName,
									academicSel, section, lcType, grList);
							int listSize = studentList.size();
							logger.info("No of students found :: " + listSize);

							if (listSize > 0) {
								frame.setVisible(false);
								new FindLeavingCert(sessionData, gr_no, std, div, lastName, firstName, fatherName,
										studentList, false, "", "", "", "", "", academicSel, section, lcType, user_name,
										user_role,"","");
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
								dbValidate.closeDatabase(sessionData);

								if (deleteFlag) {
									new Student(sessionData, section, user_name, user_role);
									frame.setVisible(false);
								} else {
									logger.info("Exception while deleting form.");
								}
							}
						} catch (Exception e12) {
							commonObj.logException(e12);
						} finally {
							dbValidate.closeDatabase(sessionData);
						}
						logger.info("Exception FindStudent form ===>>>" + e1);
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

				logger.info("Cliked Reset in findLeaving cert");
				List findLCList = new ArrayList();
				frame.setVisible(false);
				new FindLeavingCert(sessionData, "", "", "", "", "", "", findLCList, false, "", "", "", "", "", "",
						section, lcTypeClass, user_name, user_role,"","");
			}
		});

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
				Dimension size = new Dimension(screenWidth - 158, ((screenHeight - 330) + scrollHeight));// change height to change scrolling area
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				g.drawImage(img, 0, 0, screenWidth - 158, (screenHeight - 330) + scrollHeight, null);
			}
		};
		dataPanel.setLayout(null);
		if (foundStudentList.size() > 0) {
			int listSize = foundStudentList.size();
			logger.info("foundStudentList==>" + listSize);

			final JRadioButton all_radio = new JRadioButton();
			all_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			all_radio.setBounds(40, 13, 20, 20);
			all_radio.setSelected(setSelected);
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

			JLabel lc_label = new JLabel("L.C. No.");
			lc_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			lc_label.setBounds(650, 00, 120, 50);
			dataPanel.add(lc_label);

			JLabel pipe_label4 = new JLabel("|");
			pipe_label4.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label4.setBounds(750, 00, 120, 50);
			dataPanel.add(pipe_label4);

			JLabel dupLc_label = new JLabel("Dupl. L.C.");
			dupLc_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			dupLc_label.setBounds(770, 00, 120, 50);
			dataPanel.add(dupLc_label);
			
			JLabel pipe_label5 = new JLabel("|");
			pipe_label5.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label5.setBounds(870, 00, 120, 50);
			dataPanel.add(pipe_label5);

			JLabel tripLc_label = new JLabel("Trip. L.C.");
			tripLc_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			tripLc_label.setBounds(890, 00, 120, 50);
			dataPanel.add(tripLc_label);
			
			JLabel pipe_label6 = new JLabel("|");
			pipe_label6.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label6.setBounds(990, 00, 120, 50);
			dataPanel.add(pipe_label6);
			
			final JLabel delete_lc_label = new JLabel("Delete LC");
			delete_lc_label.setFont(new Font("Book Antiqua", Font.BOLD, 10));
			delete_lc_label.setBounds(1110, 05, 120, 50);
			dataPanel.add(delete_lc_label);
			
			final JRadioButton delete_lc_radio = new JRadioButton();
			delete_lc_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			delete_lc_radio.setBorder(null);
			delete_lc_radio.setBounds(1130, 10, 13, 13);
			delete_lc_radio.setSelected(setSelected);
			dataPanel.add(delete_lc_radio);

			JLabel line_label = new JLabel(
					"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			line_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			line_label.setBounds(30, 13, 1300, 50);
			dataPanel.add(line_label);

			String[] studentArray = new String[listSize];
			studentArray = foundStudentList.toArray(studentArray);
			logger.info("listSize === " + listSize);
			logger.info("studentArray === " + studentArray.length);

			if (listSize > 0) {
				int j = 0;
				int l = 0;
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
				final JLabel[] lc_labels = new JLabel[listSize];
				final JLabel[] dupLc_labels = new JLabel[listSize];
				final JLabel[] tripLc_labels = new JLabel[listSize];
				final JButton[] view_button = new JButton[listSize];
				final JButton[] delete_button = new JButton[listSize];
    	
				for (int i = 0; i < listSize; i++) {
					j = j + 30;
					l = j + 50;
					logger.info(j + "====" + studentArray[i]);
					final String gr = studentArray[i].substring(0, studentArray[i].indexOf("|"));
					String name = studentArray[i].substring(studentArray[i].indexOf("|") + 1,
							studentArray[i].indexOf("||"));
					String lcNo = studentArray[i].substring(studentArray[i].indexOf("||") + 2, studentArray[i].indexOf("|||"));
					String dupLcNo = studentArray[i].substring(studentArray[i].indexOf("|||") + 3, studentArray[i].indexOf("||||"));
					String origLcDate = studentArray[i].substring(studentArray[i].indexOf("||||") + 4, studentArray[i].indexOf("|||||"));
					String dupLcDate = studentArray[i].substring(studentArray[i].indexOf("|||||") + 5, studentArray[i].indexOf("||||||"));
					String tripLcNo = studentArray[i].substring(studentArray[i].indexOf("||||||") + 6, studentArray[i].indexOf("|||||||"));
					String tripLcDate = studentArray[i].substring(studentArray[i].indexOf("|||||||") + 7, studentArray[i].indexOf("||||||||"));
					String rollNo = studentArray[i].substring(studentArray[i].lastIndexOf("||||||||") + 8);

					logger.info("origLcDate::" + origLcDate + "::dupLcDate" + dupLcDate);

					sr_radio[i] = new JRadioButton();
					sr_radio[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sr_radio[i].setBounds(40, j + 13, 20, 20);
					sr_radio[i].setSelected(setSelected);
					if (tripLcNo.equalsIgnoreCase(" ") || tripLcNo.equalsIgnoreCase("") || tripLcNo.equalsIgnoreCase("NA")
							|| tripLcNo.equalsIgnoreCase(null) || tripLcNo.equalsIgnoreCase("0000")) {
						dataPanel.add(sr_radio[i]);
					}

					sr_labels[i] = new JLabel(rollNo);
					sr_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					sr_labels[i].setBounds(90, j, 120, 50);
					dataPanel.add(sr_labels[i]);

					pipe_labels1[i] = new JLabel("|");
					pipe_labels1[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels1[i].setBounds(130, j, 120, 50);
					dataPanel.add(pipe_labels1[i]);

					gr_labels[i] = new JLabel(gr);
					gr_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					gr_labels[i].setBounds(160, j, 120, 50);
					dataPanel.add(gr_labels[i]);
					logger.info(gr + "::allGrList in for loop 1==> " + allGrList.size());
					if (tripLcNo.equalsIgnoreCase(" ") || tripLcNo.equalsIgnoreCase("") || tripLcNo.equalsIgnoreCase("NA")
							|| tripLcNo.equalsIgnoreCase(null) || tripLcNo.equalsIgnoreCase("0000")) {
						allGrList.add(gr);
					}
					logger.info(gr + "::allGrList in for loop 2==> " + allGrList.size());

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

					String showOrigAcad = "";
					if (!lcNo.equalsIgnoreCase("") && !lcNo.equalsIgnoreCase(" ") && !lcNo.equalsIgnoreCase(null)
							&& !lcNo.equalsIgnoreCase("NA") && !lcNo.equalsIgnoreCase("0000")) {
						origLcDate = commonObj.formatyyyymmddtoddmmyyyy(origLcDate);
						showOrigAcad = "/" + commonObj.getAcademicYear(origLcDate);
						
						if(lcDiscrepancyFlag && commonObj.getAcademicYear(origLcDate).equalsIgnoreCase("2016-17")){
							///this condition is added for chabildas lc issue showing 2016-17
							showOrigAcad =  "/2017-18";
						}
					}
					lc_labels[i] = new JLabel(lcNo + showOrigAcad);
					lc_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					lc_labels[i].setBounds(650, j + 12, 100, 20);
					dataPanel.add(lc_labels[i]);

					pipe_labels4[i] = new JLabel("|");
					pipe_labels4[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels4[i].setBounds(750, j, 120, 50);
					dataPanel.add(pipe_labels4[i]);

					String showDupAcad = "";
					if (!dupLcNo.equalsIgnoreCase("") && !dupLcNo.equalsIgnoreCase(" ")
							&& !dupLcNo.equalsIgnoreCase(null) && !dupLcNo.equalsIgnoreCase("NA")
							&& !dupLcNo.equalsIgnoreCase("0000")) {
						showDupAcad = "/" + commonObj.getAcademicYear(dupLcDate);
					}
					dupLc_labels[i] = new JLabel(dupLcNo + showDupAcad);
					dupLc_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					dupLc_labels[i].setBounds(770, j + 12, 100, 20);
					dataPanel.add(dupLc_labels[i]);
					
					pipe_labels5[i] = new JLabel("|");
					pipe_labels5[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels5[i].setBounds(870, j, 120, 50);
					dataPanel.add(pipe_labels5[i]);

					String showTripAcad = "";
					if (!tripLcNo.equalsIgnoreCase("") && !tripLcNo.equalsIgnoreCase(" ")
							&& !tripLcNo.equalsIgnoreCase(null) && !tripLcNo.equalsIgnoreCase("NA")
							&& !tripLcNo.equalsIgnoreCase("0000")) {
						showTripAcad = "/" + commonObj.getAcademicYear(tripLcDate);
					}
					tripLc_labels[i] = new JLabel(tripLcNo + showTripAcad);
					tripLc_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					tripLc_labels[i].setBounds(890, j + 12, 100, 20);
					dataPanel.add(tripLc_labels[i]);
					
					pipe_labels6[i] = new JLabel("|");
					pipe_labels6[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels6[i].setBounds(990, j, 120, 50);
					dataPanel.add(pipe_labels6[i]);

					view_button[i] = new JButton("View");
					view_button[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					view_button[i].setBounds(1010, j + 12, 90, 20);
					if (!lcNo.equalsIgnoreCase("") && !lcNo.equalsIgnoreCase("NA") && !lcNo.equalsIgnoreCase(" ")
							&& !lcNo.equalsIgnoreCase("0000")) {
						dataPanel.add(view_button[i]);
					}
					
					delete_button[i] = new JButton("Del");
					delete_button[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					delete_button[i].setBounds(1110, j + 12, 60, 20);
					if(sessionData.getUserRole().equalsIgnoreCase("ADMINISTRATOR")){
						delete_button[i].setEnabled(true);
					}
					else {
						delete_button[i].setEnabled(false);
					}
					if (!lcNo.equalsIgnoreCase("") && !lcNo.equalsIgnoreCase("NA") && !lcNo.equalsIgnoreCase(" ")
							&& !lcNo.equalsIgnoreCase("0000")) {
						dataPanel.add(delete_button[i]);
					}

					// //////View Button action//////////////
					final int m = i;
					view_button[i].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {

							try {
								List<String> ViewLc = new ArrayList();
								ViewLc.add(gr_labels[m].getText());
								LeavingCertActionObj.LeavingCertAct(sessionData, "View", "", "", "", "", "", ViewLc, section,
										lcTypeClass, user_name, user_role, progress, "",academicYearClass,"", stdClass, divClass, "");
							} catch (DocumentException e1) {
								commonObj.logException(e1);
							}
						}
					});

					delete_button[i].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {

							try {
								boolean delete_radio = delete_lc_radio.isSelected();
								String grNo = gr_labels[m].getText();
								int reply = 0;
								reply = JOptionPane.showConfirmDialog(null, "Would You Like to delete LC for GR No. "+grNo+"? \n Once deleted you cannot revert back.", "Confirm Delete", JOptionPane.YES_NO_OPTION);
								
								if(dbValidate.connectDatabase(sessionData) && reply == JOptionPane.YES_OPTION && delete_radio) {
									dbValidate.deleteLC(sessionData, grNo);
									
									dataPanel.remove(delete_button[m]);
									dataPanel.remove(view_button[m]);
									dataPanel.remove(lc_labels[m]);
									dataPanel.revalidate();
									dataPanel.repaint();
//									List findLCList = new ArrayList();
//									frame.setVisible(false);
//									new FindLeavingCert(sessionData, "", "", "", "", "", "", findLCList, false, "", "", "", "", "", "",
//											section, lcTypeClass, user_name, user_role,"","");
								}
								else {
									JOptionPane.showMessageDialog(null, "Please select Delete LC radio on top to delete LC.");
								}
							} catch (Exception e1) {
								commonObj.logException(e1);
							}
						}
					});
					
					if (!lcNo.equalsIgnoreCase("") && !lcNo.equalsIgnoreCase("NA") && !lcNo.equalsIgnoreCase(" ")
							&& !lcNo.equalsIgnoreCase("0000") && setSelected) {
						duplicateLCList.add(gr);
					}

					line_labels[i] = new JLabel(
							"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					line_labels[i].setBounds(30, j + 10, 1300, 50);
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
								if (!lc_labels[k].getText().trim().equalsIgnoreCase("")) {
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
								if (!lc_labels[k].getText().trim().equalsIgnoreCase("")) {
									duplicateLCList.remove(gr_labels[k].getText());
									logger.info(gr_labels[k].getText() + " removed");
								}
								all_radio.setSelected(false);
								setSelected = false;
								selectAllCount--;
							}
							logger.info("selectAllCount ==>" + selectAllCount);
							if (selectAllCount == foundStudentList.size()) {
								all_radio.setSelected(true);
							}
							logger.info("allGrList end of for loop ==>" + allGrList.size());
						}
					});
				}
				// /////print data starts/////////////////////////////////
				
				final JRadioButton enableSave_radio = new JRadioButton();
				enableSave_radio.setBounds(40, l + 10, 20, 25);
				dataPanel.add(enableSave_radio);
				
				JLabel enableSave_label = new JLabel("Please click to enable Save & print option & generate "+lcTypeClass+ " LC.");
				enableSave_label.setForeground(Color.RED);
				enableSave_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				enableSave_label.setBounds(75, l + 10, 600, 25);
				dataPanel.add(enableSave_label);
				
				JButton viewAllButton = new JButton("View All Selected LC");
				viewAllButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				viewAllButton.setBounds(900, l + 10, 200, 25);
				if(!lcTypeClass.equalsIgnoreCase("Original")){
					dataPanel.add(viewAllButton);
				}
				viewAllButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						try {
							List<String> passGrList = new ArrayList();

							if (!setSelected && entrytCnt == 0) {
								allGrList.clear();
								passGrList = allGrList;
								logger.info("passGrList before 1 = " + passGrList.size());
							}
							passGrList = allGrList;
							
							if (passGrList.size() == 0) {
								JOptionPane.showMessageDialog(null, "No Student selected");
							} else {
								LeavingCertActionObj.LeavingCertAct(sessionData, "View", "", "", "", "", "", passGrList, section,
										lcTypeClass, user_name, user_role, progress, "",academicYearClass,"", stdClass, divClass, "");
							}
						} catch (DocumentException e1) {
							commonObj.logException(e1);
						}
					}
				});
				
				boolean typeFlag = false;
				if (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("Update")) {
					typeFlag = true;
				}
				JLabel dateLeaving_label = new JLabel("Date of Leaving");
				dateLeaving_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				dateLeaving_label.setBounds(60, l + 40, 150, 50);
				if(lcTypeClass.equalsIgnoreCase("Original")){
					dataPanel.add(dateLeaving_label);
				}
				/****Leaving date picker****/
				final UtilDateModel modelLeaving = new UtilDateModel();
				/*if(!fromDateClass.equalsIgnoreCase("")){
					int dayFromDate = Integer.parseInt(fromDateClass.substring(0,fromDateClass.indexOf("/")));
					int monthFromDate = Integer.parseInt(fromDateClass.substring(fromDateClass.indexOf("/")+1,fromDateClass.lastIndexOf("/")));
					int yearFromDate = Integer.parseInt(fromDateClass.substring(fromDateClass.lastIndexOf("/")+1));
					modelLeaving.setDate(yearFromDate,monthFromDate-1,dayFromDate);
//					modelLeaving.setDate(2014,04,24);
				}*/
				modelLeaving.setSelected(true);
			    Properties pLeaving = new Properties();
			    pLeaving.put("text.today", "Today");
			    pLeaving.put("text.month", "Month");
			    pLeaving.put("text.year", "Year");
			    final JDatePanelImpl datePanelLeaving = new JDatePanelImpl(modelLeaving, pLeaving);
			    datePanelLeaving.setBounds(120, l + 55, 130, 26);
			    datePanelLeaving.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
			    // Don't know about the formatter, but there it is...
			    final JDatePickerImpl datePickerLeaving = new JDatePickerImpl(datePanelLeaving, new DateLabelFormatter());
			    datePickerLeaving.setBounds(195, l + 55, 130, 26);
			    if(lcTypeClass.equalsIgnoreCase("Original")){
			    	dataPanel.add(datePickerLeaving);
			    }
				/*final JTextField dateLeaving_text = new JTextField();
				dateLeaving_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				dateLeaving_text.setBounds(190, l + 55, 100, 25);
				dateLeaving_text.setEditable(typeFlag);
				dataPanel.add(dateLeaving_text);
				dateLeaving_text.setText(dateLeaving);

				JLabel dolformat_label = new JLabel("(DD/MM/YYYY)");
				dolformat_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				dolformat_label.setBounds(300, l + 40, 130, 50);
				dataPanel.add(dolformat_label);*/

			    final JRadioButton dateIssue_radio = new JRadioButton();
			    dateIssue_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			    dateIssue_radio.setBorder(null);
			    dateIssue_radio.setBounds(350, l + 60, 12, 12);
			    dateIssue_radio.setSelected(true);
				dataPanel.add(dateIssue_radio);
				
				JLabel dateIssue_label = new JLabel("Date of Issue");
				dateIssue_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				dateIssue_label.setBounds(365, l + 40, 150, 50);
				dataPanel.add(dateIssue_label);

				/****Issue date picker****/
				final UtilDateModel modelIssue = new UtilDateModel();
				/*if(!fromDateClass.equalsIgnoreCase("")){
					int dayFromDate = Integer.parseInt(fromDateClass.substring(0,fromDateClass.indexOf("/")));
					int monthFromDate = Integer.parseInt(fromDateClass.substring(fromDateClass.indexOf("/")+1,fromDateClass.lastIndexOf("/")));
					int yearFromDate = Integer.parseInt(fromDateClass.substring(fromDateClass.lastIndexOf("/")+1));
					modelIssue.setDate(yearFromDate,monthFromDate-1,dayFromDate);
//					modelIssue.setDate(2014,04,24);
				}*/
				modelIssue.setSelected(true);
			    Properties pIssue = new Properties();
			    pIssue.put("text.today", "Today");
			    pIssue.put("text.month", "Month");
			    pIssue.put("text.year", "Year");
			    final JDatePanelImpl datePanelIssue = new JDatePanelImpl(modelIssue, pIssue);
			    datePanelIssue.setBounds(390, l + 55, 130, 26);
			    datePanelIssue.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
			    // Don't know about the formatter, but there it is...
			    final JDatePickerImpl datePickerIssue = new JDatePickerImpl(datePanelIssue, new DateLabelFormatter());
			    datePickerIssue.setBounds(465, l + 55, 130, 26);
			    dataPanel.add(datePickerIssue);
			    
				/*final JTextField dateIssue_text = new JTextField();
				dateIssue_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				dateIssue_text.setBounds(550, l + 55, 100, 25);
				dataPanel.add(dateIssue_text);
				dateIssue_text.setText(dateIssue);

				JLabel doiformat_label = new JLabel("(DD/MM/YYYY)");
				doiformat_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				doiformat_label.setBounds(660, l + 40, 130, 50);
				dataPanel.add(doiformat_label);*/

				JLabel conduct_label = new JLabel("Conduct");
				conduct_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				conduct_label.setBounds(630, l + 40, 150, 50);
				dataPanel.add(conduct_label);

				final JTextField conduct_text = new JTextField();
				conduct_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				conduct_text.setBounds(710, l + 55, 100, 25);
				conduct_text.setEditable(typeFlag);
				dataPanel.add(conduct_text);
				conduct_text.setText(conduct);

				JLabel medium_label = new JLabel("Medium");
				medium_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				medium_label.setBounds(830, l + 40, 150, 50);

				String[] mediumList = mediumStr.split(",");
				final JComboBox medium_combo = new JComboBox(mediumList);
				medium_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				medium_combo.setBounds(900, l + 55, 190, 25);
				medium_combo.setEnabled(typeFlag);
				if(mediumStrEnable.equalsIgnoreCase("True")){
					dataPanel.add(medium_label);
					dataPanel.add(medium_combo);
				}
				
				JLabel reason_label = new JLabel("Reason");
				reason_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				reason_label.setBounds(60, l + 80, 150, 50);
				dataPanel.add(reason_label);

				reasonStr = reasonStr.replace("*", "'");
				String[] reasonList = reasonStr.split(",");
				final JComboBox reason_combo = new JComboBox(reasonList);
				reason_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				reason_combo.setBounds(190, l + 95, 250, 25);
				reason_combo.setEnabled(typeFlag);
				dataPanel.add(reason_combo);
				
				final JTextField reason_text = new JTextField();
				reason_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				reason_text.setBounds(450, l + 95, 200, 25);
				reason_text.setEditable(typeFlag);
				dataPanel.add(reason_text);
				reason_text.setText(reason);
				
				JLabel progress_label = new JLabel("Progress");
				progress_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				progress_label.setBounds(720, l + 80, 150, 50);
				dataPanel.add(progress_label);

				String[] progressList = progressStr.split(",");
				final JComboBox progress_combo = new JComboBox(progressList);
				progress_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				progress_combo.setBounds(800, l + 95, 190, 25);
				progress_combo.setEnabled(typeFlag);
				dataPanel.add(progress_combo);

				JLabel remark_label = new JLabel("Remark");
				remark_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				remark_label.setBounds(60, l + 120, 150, 50);
				dataPanel.add(remark_label);

				final String[] remarkList = remarkStr.split(",");
				final JComboBox remark_combo = new JComboBox(remarkList);
				remark_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				remark_combo.setBounds(190, l + 135, 570, 25);
				remark_combo.setEnabled(typeFlag);
				dataPanel.add(remark_combo);
				
				///tooltip for comvbo box
				remark_combo.setRenderer(new BasicComboBoxRenderer() {
			         public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			            if (isSelected) {
			               setBackground(list.getSelectionBackground());
			               setForeground(list.getSelectionForeground());
			               if (index > -1) {
			                  list.setToolTipText(remarkList[index].toString());
			               }
			            }
			            else {
			               setBackground(list.getBackground());
			               setForeground(list.getForeground());
			            }
			            setFont(list.getFont());
			            setText((value == null) ? "" : value.toString());
			  
			            return this;
			         }
			      });
				
				final JTextField remark_text = new JTextField();
				remark_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				remark_text.setBounds(770, l + 135, 330, 25);
				remark_text.setEditable(typeFlag);
				dataPanel.add(remark_text);
				remark_text.setText(remark);

				/*JLabel remark2_label = new JLabel("Remark 2");
				remark2_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				remark2_label.setBounds(720, l + 120, 150, 50);
				dataPanel.add(remark2_label);

				final JTextField remark2_text = new JTextField();
				remark2_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				remark2_text.setBounds(800, l + 135, 190, 25);
				remark2_text.setEditable(typeFlag);
				dataPanel.add(remark2_text);
				progress_text.setText(reason);*/
				
				//////////////////////////////////////
				JLabel fee_label = new JLabel("Fee Status");
				fee_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				fee_label.setBounds(60, l + 170, 150, 50);
				dataPanel.add(fee_label);
				
				String[] feeList = feeStatusList.split(",");
				final JComboBox feeStatus_combo = new JComboBox(feeList);
				feeStatus_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				feeStatus_combo.setBounds(190, l + 180, 180, 25);
				feeStatus_combo.setEnabled(typeFlag);
				dataPanel.add(feeStatus_combo);
				
				final JButton validateButton = new JButton("Validate");
				validateButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				validateButton.setBounds(mainCentre - 150, l + 180, 130, 25);
				dataPanel.add(validateButton);
				
				final JButton savePrintButton = new JButton("Save & Print");
				savePrintButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				savePrintButton.setBounds(mainCentre + 50, l + 180, 130, 25);
				dataPanel.add(savePrintButton);
				
				validateButton.setEnabled(false);
				savePrintButton.setEnabled(false);
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
				
				reason_text.setText("Select Other to enter here");
				remark_text.setText("Select Other to enter here");
				reason_text.setEnabled(false);
				remark_text.setEnabled(false);
				
				dateIssue_radio.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if(dateIssue_radio.isSelected()) {
							dataPanel.add(datePickerIssue);
						}
						else {
							int reply = 0;
							reply = JOptionPane.showConfirmDialog(null, "Would you like Issue Date as blank", "Confirm", JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {
								dataPanel.remove(datePickerIssue);
							}
							else {
								dateIssue_radio.setSelected(true);
							}
						}
						dataPanel.revalidate();
						dataPanel.repaint();
					}
				});

				// //////All sr radio action//////////////
				all_radio.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						boolean pass_selected = all_radio.isSelected();
						logger.info("pass_selected : " + pass_selected);
						frame.setVisible(false);
						logger.info(" list size  : " + foundStudentList.size());
						Date today = new Date();
				        Date selectedLeavingDate = (Date) datePickerLeaving.getModel().getValue();
				        String leavingDate = commonObj.dateToDDMMYYYY(selectedLeavingDate);
				        Date selectedIssueDate = (Date) datePickerIssue.getModel().getValue();
				        String issueDate = commonObj.dateToDDMMYYYY(selectedIssueDate);
				        if(!dateIssue_radio.isSelected()) {
				        	issueDate = "";
				        }
				        String selProgress = progress_combo.getSelectedItem().toString();
				        
						new FindLeavingCert(sessionData, grClass, stdClass, divClass, lastClass, firstClass,
								fatherClass, foundStudentList, pass_selected, leavingDate,
								issueDate, reason_combo.getSelectedItem().toString(), remark_combo.getSelectedItem().toString(),
								conduct_text.getText(), academicYearClass, section, lcTypeClass, user_name, user_role,selProgress,"");
					}
				});

				/*dateLeaving_text.addFocusListener(new java.awt.event.FocusAdapter() {

					public void focusGained(java.awt.event.FocusEvent event) {

						dateLeaving_text.selectAll();
					}
				});

				dateLeaving_text.addKeyListener(new KeyAdapter() {

					public void keyReleased(KeyEvent e) {

						String dateLeavingStr = dateLeaving_text.getText();
						dateLeavingStr = dateLeavingStr.trim();
						if (dateLeavingStr.length() == 2) {
							dateLeavingStr = dateLeavingStr + "/";
							logger.info("dateLeavingStr===>" + dateLeavingStr);
							dateLeaving_text.setText(dateLeavingStr);
						} else if (dateLeavingStr.length() == 5) {
							dateLeavingStr = dateLeavingStr + "/";
							logger.info("dateLeavingStr===>" + dateLeavingStr);
							dateLeaving_text.setText(dateLeavingStr);
						} else if (dateLeavingStr.length() >= 10) {
							dateLeavingStr = dateLeavingStr.substring(0, 10);
							logger.info("dateLeavingStr===>" + dateLeavingStr);
							dateLeaving_text.setText(dateLeavingStr);
						}
					}
				});*/

				/*dateIssue_text.addFocusListener(new java.awt.event.FocusAdapter() {

					public void focusGained(java.awt.event.FocusEvent event) {

						dateIssue_text.selectAll();
					}
				});

				dateIssue_text.addKeyListener(new KeyAdapter() {

					public void keyReleased(KeyEvent e) {

						String dateIssueStr = dateIssue_text.getText();
						dateIssueStr = dateIssueStr.trim();
						if (dateIssueStr.length() == 2) {
							dateIssueStr = dateIssueStr + "/";
							logger.info("dateIssueStr===>" + dateIssueStr);
							dateIssue_text.setText(dateIssueStr);
						} else if (dateIssueStr.length() == 5) {
							dateIssueStr = dateIssueStr + "/";
							logger.info("dateIssueStr===>" + dateIssueStr);
							dateIssue_text.setText(dateIssueStr);
						} else if (dateIssueStr.length() >= 10) {
							dateIssueStr = dateIssueStr.substring(0, 10);
							logger.info("dateIssueStr===>" + dateIssueStr);
							dateIssue_text.setText(dateIssueStr);
						}
					}
				});*/

				conduct_text.addFocusListener(new java.awt.event.FocusAdapter() {

					public void focusGained(java.awt.event.FocusEvent event) {

						conduct_text.selectAll();
					}
				});

				// //////////////////////////////////
				reason_combo.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						if (reason_combo.getSelectedItem().toString().equalsIgnoreCase("Other")) {
							reason_text.setEnabled(true);
							reason_text.setText("");
						} else {
							reason_text.setText("Select Other to enter here");
							reason_text.setEnabled(false);
						}

					}
				});
				/*reason_text.addFocusListener(new java.awt.event.FocusAdapter() {

					public void focusGained(java.awt.event.FocusEvent event) {

						reason_text.selectAll();
					}
				});*/

				remark_combo.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						if (remark_combo.getSelectedItem().toString().equalsIgnoreCase("Other")) {
							remark_text.setEnabled(true);
							remark_text.setText("");
						} else {
							remark_text.setText("Select Other to enter here");
							remark_text.setEnabled(false);
						}

					}
				});
				/*remark_text.addFocusListener(new java.awt.event.FocusAdapter() {

					public void focusGained(java.awt.event.FocusEvent event) {

						remark_text.selectAll();
					}
				});*/

				enableSave_radio.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						String userActive = "";
						try {
							if(dbValidate.connectDatabase(sessionData)){
								userActive = dbValidate.checkFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
								if (userActive.equalsIgnoreCase(user_name) || userActive.equalsIgnoreCase("")
										|| userActive.equalsIgnoreCase(null)) {
									boolean radio_selected = enableSave_radio.isSelected();
									
									try {
										userActive = dbValidate.checkFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
										if (userActive.equalsIgnoreCase("") || userActive.equalsIgnoreCase(null)) {
											dbValidate.insertFormData(user_name, "LEAVING CERTIFICATE", user_role, section);
										}
									} catch (Exception e1) {
										commonObj.logException(e1);
									} finally {
										dbValidate.closeDatabase(sessionData);
									}
									
									if (radio_selected) {
										
										int reply = 0;
										reply = JOptionPane.showConfirmDialog(null, "Would You Like to generate "+lcTypeClass+" LC?", "Confirm validate", JOptionPane.YES_NO_OPTION);
										if (reply == JOptionPane.YES_OPTION) {
											validateButton.setEnabled(true);
											savePrintButton.setEnabled(true);
											enableSave_radio.setSelected(true);
										}
										else{
											validateButton.setEnabled(false);
											savePrintButton.setEnabled(false);
											enableSave_radio.setSelected(false);
										}
									} 
									else {
										validateButton.setEnabled(false);
										savePrintButton.setEnabled(false);
										enableSave_radio.setSelected(false);
									}
								} else if (!userActive.equalsIgnoreCase(user_name)) {
									enableSave_radio.setSelected(false);
									JOptionPane.showMessageDialog(null, "To generate LC User " + userActive + " should move away from LC tab.");
								}
							}
						} catch (Exception e1) {
							commonObj.logException(e1);
						} finally {
							dbValidate.closeDatabase(sessionData);
						}
					}
				});
			
				// /////Validate button action starts///////////////////
				validateButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						logger.info("validate button action starts");
						boolean flagLeaving = true;
				        Date selectedLeavingDate = (Date) datePickerLeaving.getModel().getValue();
				        String leavingDate = commonObj.dateToDDMMYYYY(selectedLeavingDate);
				        String leavingAcademic = commonObj.getAcademicYear(leavingDate);
				        Date selectedIssueDate = (Date) datePickerIssue.getModel().getValue();
				        String issueDate = commonObj.dateToDDMMYYYY(selectedIssueDate);
				        if(!dateIssue_radio.isSelected()) {
				        	issueDate = "";
				        }
				        String medium = medium_combo.getSelectedItem() == null ? "": medium_combo.getSelectedItem().toString();
				        if(medium.equalsIgnoreCase("Select")){
				        	medium = "";
				        }
				        String feeStatus = feeStatus_combo.getSelectedItem().toString();
						String reason = reason_combo.getSelectedItem().toString();
						reason = reason.replace("'", "*");
						if(reason.equalsIgnoreCase("Other")){
							reason = reason_text.getText().trim();
						}
						String remark = remark_combo.getSelectedItem().toString();
						if(remark.equalsIgnoreCase("Other")){
							remark = remark_text.getText().trim();
						}
//						String remark2 = remark2_text.getText().trim();
						String progress = progress_combo.getSelectedItem().toString();;
						String conductTxt = conduct_text.getText().trim();
						logger.info("conductTxt :: " + conductTxt);

						if (lcTypeClass.equalsIgnoreCase("Original") && (!commonObj.validateDate(leavingDate)
								|| leavingDate.equalsIgnoreCase("") || !commonObj.validateYearFormat(leavingDate))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Date of Leaving");
						} else if (dateIssue_radio.isSelected() && (!commonObj.validateDate(issueDate) || issueDate.equalsIgnoreCase("")
								|| !commonObj.validateYearFormat(issueDate))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Date of Issue");
						} else if (commonObj.checkComma(reason)) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Reason cannot contain ;',-:|");
						} else if (reason.length() > 45) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Reason cannot exceed 45 characters");
						} /*else if (reason.length() <= 0 && (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("update"))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please enter reason.");
						}*/ else if (commonObj.checkCommaWithoutHiphen(remark)) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Remark cannot contain ;',:|");
						}
						else if ((stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XII")) && remark.contains("promoted to Std ****")) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please change Remark. This option is not valid for Std X & XII");
						}
						else if (remark.length() > 75) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Remark cannot exceed 75 characters");
						}
						/*else if (remark.length() <= 0 && (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("update"))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please enter remark.");
						} */
						/*else if (commonObj.checkComma(remark2)) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Remark2 cannot contain ;");
						} else if (remark2.length() > 50) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Remark2 cannot exceed 50 characters");
						} else if (remark2.length() <= 0 && (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("update"))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please enter remark2.");
						}*/ 
						else if (commonObj.checkComma(conductTxt)) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Conduct cannot contain ;',-:|");
						} else if (conductTxt.length() > 25) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Conduct cannot exceed 25 characters");
						} else if (conductTxt.length() <= 0 && (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("update"))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please enter conduct.");
						} else if (commonObj.checkComma(progress)) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Progress cannot contain ;',-:|");
						} else if (progress.length() > 25) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Progress cannot exceed 25 characters");
						} else if (progress.length() <= 0 && (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("update"))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please enter progress.");
						} else if (feeStatus.equalsIgnoreCase("Select") && (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("update"))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please select Fee Status");
						} else if ((medium.equalsIgnoreCase("") || medium.equalsIgnoreCase("Select")) && mediumStrEnable.equalsIgnoreCase("True") 
								&& (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("update"))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please select Medium");
						}
						
						List<String> passGrList = new ArrayList();
						logger.info("setSelected = " + setSelected);
						logger.info("passGrList 123 = " + passGrList.size());
						logger.info("allGrList 123 = " + allGrList.size());

						if (!setSelected && entrytCnt == 0) {
							allGrList.clear();
							passGrList = allGrList;
							logger.info("passGrList before 1 = " + passGrList.size());
						}
						passGrList = allGrList;
						logger.info("passGrList before 3 = " + passGrList.size());
						if (passGrList.size() == 0) {
							JOptionPane.showMessageDialog(null, "No Student selected");
						} else if (flagLeaving) {
							logger.info("duplicateLCList===>" + duplicateLCList.size());
							int reply = 0;
							if (duplicateLCList.size() > 0) {
								if(lcTypeClass.equalsIgnoreCase("Update")){
									reply = JOptionPane.showConfirmDialog(null, "Would You Like to update LC?", "Confirm validate", JOptionPane.YES_NO_OPTION);
								}
								else if(lcTypeClass.equalsIgnoreCase("Duplicate")){
									reply = JOptionPane.showConfirmDialog(null, "Would You Like to validate duplicate LC?", "Confirm validate", JOptionPane.YES_NO_OPTION);
								}
								else if(lcTypeClass.equalsIgnoreCase("Triplicate")){
									reply = JOptionPane.showConfirmDialog(null, "Would You Like to validate triplicate LC?", "Confirm validate", JOptionPane.YES_NO_OPTION);
								}
								
								if (reply == JOptionPane.YES_OPTION) {
									logger.info("conductTxt :: " + conductTxt);
									try {
										LeavingCertActionObj.LeavingCertAct(sessionData, "Validate", conductTxt, leavingDate,
												issueDate, reason, remark, passGrList, section, lcTypeClass, user_name,
												user_role, progress, "",academicYearClass, feeStatus, stdClass, divClass, medium);
									} catch (DocumentException e1) {
										commonObj.logException(e1);
									}
								} else if (reply == JOptionPane.NO_OPTION) {
									JOptionPane.showMessageDialog(null, "Please recheck again");
								}
							} else {
								try {
									reply = JOptionPane.showConfirmDialog(null, "Academic year("+leavingAcademic+") in LC No. is based on leaving date.\nWould you like to proceed.", "Confirm", JOptionPane.YES_NO_OPTION);
									if (reply == JOptionPane.YES_OPTION) {
										LeavingCertActionObj.LeavingCertAct(sessionData, "Validate", conductTxt, leavingDate, issueDate,
												reason, remark, passGrList, section, lcTypeClass, user_name, user_role, progress, "",academicYearClass,
												feeStatus, stdClass, divClass, medium);
									} else if (reply == JOptionPane.NO_OPTION) {
										JOptionPane.showMessageDialog(null, "Please recheck again");
									}
								} catch (DocumentException e1) {
									commonObj.logException(e1);
								}
							}
						}
					}
				});
				// /////validate button action ends//////////////////////
				// //////save & print action
				// starts//////////////////////////////////////
				savePrintButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						logger.info("save & print action starts");
						boolean flagLeaving = true;
						Date today = new Date();
				        Date selectedLeavingDate = (Date) datePickerLeaving.getModel().getValue();
				        String leavingDate = commonObj.dateToDDMMYYYY(selectedLeavingDate);
				        String leavingAcademic = commonObj.getAcademicYear(leavingDate);
				        Date selectedIssueDate = (Date) datePickerIssue.getModel().getValue();
				        String issueDate = commonObj.dateToDDMMYYYY(selectedIssueDate);
				        if(!dateIssue_radio.isSelected()) {
				        	issueDate = "";
				        }
				        String medium = medium_combo.getSelectedItem() == null ? "": medium_combo.getSelectedItem().toString();
				        if(medium.equalsIgnoreCase("Select")){
				        	medium = "";
				        }
				        String feeStatus = feeStatus_combo.getSelectedItem().toString();
				        String reason = reason_combo.getSelectedItem().toString();
				        reason = reason.replace("'", "*");
						if(reason.equalsIgnoreCase("Other")){
							reason = reason_text.getText().trim();
						}
						String remark = remark_combo.getSelectedItem().toString();
						if(remark.equalsIgnoreCase("Other")){
							remark = remark_text.getText().trim();
						}
//						String remark2 = remark2_text.getText().trim();
						String conductTxt = conduct_text.getText().trim();
						String progress = progress_combo.getSelectedItem().toString();

						if (lcTypeClass.equalsIgnoreCase("Original") && (!commonObj.validateDate(leavingDate)
								|| leavingDate.equalsIgnoreCase("") || !commonObj.validateYearFormat(leavingDate))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Date of Leaving");
						} else if (dateIssue_radio.isSelected() && (!commonObj.validateDate(issueDate) || issueDate.equalsIgnoreCase("")
								|| !commonObj.validateYearFormat(issueDate))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Date of Issue");
						} else if (commonObj.checkComma(reason)) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Reason cannot contain ;',-:|");
						} else if (reason.length() > 45) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Reason cannot exceed 45 characters");
						} /*else if (reason.length() <= 0 && (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("update"))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please enter reason.");
						}*/ else if (commonObj.checkCommaWithoutHiphen(remark)) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Remark cannot contain ;',:|");
						}
						else if ((stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XII")) && remark.contains("promoted to Std ****")) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please change Remark. This option is not valid for Std X & XII");
						}
						else if (remark.length() > 75) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Remark cannot exceed 75 characters");
						}
						/*else if (remark.length() <= 0 && (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("update"))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please enter remark.");
						} */
						/*else if (commonObj.checkComma(remark2)) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Remark2 cannot contain ;");
						} else if (remark2.length() > 50) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Remark2 cannot exceed 50 characters");
						} else if (remark2.length() <= 0 && (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("update"))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please enter remark2.");
						}*/ 
						else if (commonObj.checkComma(conductTxt)) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Conduct cannot contain ;',-:|");
						} else if (conductTxt.length() > 25) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Conduct cannot exceed 25 characters");
						} else if (conductTxt.length() <= 0 && (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("update"))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please enter conduct.");
						} else if (commonObj.checkComma(progress)) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Progress cannot contain ;',-:|");
						} else if (progress.length() > 25) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Progress cannot exceed 25 characters");
						} else if (progress.length() <= 0 && (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("update"))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please enter progress.");
						}  else if (feeStatus.equalsIgnoreCase("Select") && (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("update"))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please select Fee Status");
						} else if (medium.equalsIgnoreCase("Select") && (lcTypeClass.equalsIgnoreCase("Original") || lcTypeClass.equalsIgnoreCase("update"))) {
							flagLeaving = false;
							JOptionPane.showMessageDialog(null, "Please select Medium");
						}
						
						if(lcTypeClass.equalsIgnoreCase("update")){
							leavingDate = "";
						}
						List<String> passGrList = new ArrayList();
						logger.info("setSelected = " + setSelected);
						logger.info("passGrList 123 = " + passGrList.size());
						logger.info("allGrList 123 = " + allGrList.size());

						if (!setSelected && entrytCnt == 0) {
							allGrList.clear();
							passGrList = allGrList;
							logger.info("passGrList before 1 = " + passGrList.size());
						}
						// else{
						// passGrList = allGrList;
						// logger.info("passGrList before 2 =
						// "+passGrList.size());
						// }
						passGrList = allGrList;
						logger.info("passGrList before 3 = " + passGrList.size());
						if (passGrList.size() == 0) {
							JOptionPane.showMessageDialog(null, "No Student selected");
						} else if (flagLeaving) {
							logger.info("duplicateLCList===>" + duplicateLCList.size());
							int reply = 0;
							if (duplicateLCList.size() > 0) {
								if(lcTypeClass.equalsIgnoreCase("Duplicate")){
									reply = JOptionPane.showConfirmDialog(null, "Would You Like to Save/Print duplicate LC?", "Confirm Save/Print",
										JOptionPane.YES_NO_OPTION);
								} else if(lcTypeClass.equalsIgnoreCase("Triplicate")){
									reply = JOptionPane.showConfirmDialog(null, "Would You Like to Save/Print triplicate LC?", "Confirm Save/Print",
											JOptionPane.YES_NO_OPTION);
								}
								if (reply == JOptionPane.YES_OPTION) {
									logger.info("conductTxt :: " + conductTxt);
									try {
										LeavingCertActionObj.LeavingCertAct(sessionData, "save", conductTxt, leavingDate, issueDate,
												reason, remark, passGrList, section, lcTypeClass, user_name, user_role, progress, "",academicYearClass,
												feeStatus, stdClass, divClass, medium);
										frame.setVisible(false);
										List findLCList = new ArrayList();
										new FindLeavingCert(sessionData, grClass, stdClass, divClass, lastClass,
												firstClass, fatherClass, findLCList, false, "", "", "", "", "",
												yearClass, section, lcTypeClass, user_name, user_role,"","");
									} catch (DocumentException e1) {
										commonObj.logException(e1);
									}
								} else if (reply == JOptionPane.NO_OPTION) {
									JOptionPane.showMessageDialog(null, "Please recheck again");
								}
							} else {
								try {
									reply = JOptionPane.showConfirmDialog(null, "Academic year("+leavingAcademic+") in LC No. is based on leaving date.\nWould you like to proceed.", "Confirm", JOptionPane.YES_NO_OPTION);
									if (reply == JOptionPane.YES_OPTION) {
										LeavingCertActionObj.LeavingCertAct(sessionData, "save", conductTxt, leavingDate, issueDate,
												reason, remark, passGrList, section, lcTypeClass, user_name, user_role, progress, "", academicYearClass,
												feeStatus, stdClass, divClass, medium);
										JOptionPane.showMessageDialog(null, "LC created successfully...");
										frame.setVisible(false);
										List findLCList = new ArrayList();
										new FindLeavingCert(sessionData, grClass, stdClass, divClass, lastClass, firstClass,
												fatherClass, findLCList, false, "", "", "", "", "", yearClass, section,
												lcTypeClass, user_name, user_role,"","");
									}
									else if (reply == JOptionPane.NO_OPTION) {
											JOptionPane.showMessageDialog(null, "Please recheck again");
									}
								} catch (DocumentException e1) {
									commonObj.logException(e1);
								}
							}
						}
					}
				});
				// //////save & print action
				// ends/////////////////////////////////////
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
