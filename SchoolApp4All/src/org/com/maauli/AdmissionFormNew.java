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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class AdmissionFormNew extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JComboBox studentcombo;

	static String currentUser = "";

	static JFrame frame = null;

	static String std = "";
	
	static String payment_status = "";

	static String div = "";
	
	static String religionList = "";
	
	static String categoryList = "";

	static String section = "";

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

	static int topGrNo = 0;

	static int nextGrNo = 0;
	
	static int scrollHeight = 100;

	static String nxtGrString = "";

	static String dateToday = "";

	static String academicYear = "";
	
	static String studentInfo[] = null;
	
	static String academicYearDB = "";

	static String user_name = "";

	static String user_role = "";

    static String app_header = "";
    
    static String app_header_0 = "";
    
    static String classPageStatus = "";
    
    static String submitButtonName = "Submit";
    
    static String resetButtonName = "Reset";
    
    static String admittedStdBranch = "";
    
    static String classGr = "";
	
	static SessionData sessionData = new SessionData();

	static DBValidate dbValidate = new DBValidate();

	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static Logger logger = Logger.getLogger(AdmissionFormNew.class.getName());

	static Common commonObj = new Common();
	
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
    static String lc_visible_master_only = "";
    static boolean lc_visible_flag = true;
    static LinkedHashMap<String,LinkedHashMap<String, String>> foundStudentList;

	public AdmissionFormNew(SessionData sessionData1, String sec,
			String retUserName, String retUserRole, String pageStatus, String gr, LinkedHashMap<String,LinkedHashMap<String, String>> retFoundStudentList) {
		System.gc();
		foundStudentList = retFoundStudentList;
		academicYearDB = "";
		admittedStdBranch = "";
		classGr = gr;
		classPageStatus = pageStatus;
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		section = sec;
		logger.info("section :: " + section);
		dateToday = commonObj.getCurrentDate();
		academicYear = commonObj.getAcademicYear(dateToday);
		std = bundle.getString(section.toUpperCase() + "_STD");
		std = "Select,"+std;
		logger.info("std :: " + std);
		div = bundle.getString(section.toUpperCase() + "_DIV");
		div = "Select,"+div;
		logger.info("div :: " + div);
		secName = bundle.getString(section.toUpperCase() + "_SEC");
		logger.info("secName :: " + secName);
		religionList = bundle.getString("RELIGION_LIST");
		categoryList = bundle.getString("CATEGORY_NAMES");
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
        payment_status = bundle.getString("PAYMENT_STATUS");
        app_header_2_heightSpace = Integer.parseInt(bundle.getString("APP_HEADER_2_HEIGHTSPACE_"+sessionData.getAppType()));
        
        if(lc_visible_master_only.equalsIgnoreCase("true") && !sessionData.getConfigMap().get("SchoolApp_IP").contains("127.0.0.1")) {
    		lc_visible_flag = false;
    	}
    	else {
    		lc_visible_flag = true;
    	}
        
        if(pageStatus.equalsIgnoreCase("VIEW") || pageStatus.equalsIgnoreCase("EDIT")){
	        try {
				studentInfo = dbValidate.viewStudentInfo(sessionData, gr, section);
				logger.info("studentInfo::" + studentInfo.toString());
				academicYearDB = studentInfo[28];
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Error in fetching student info foe GR No. " + gr);
				e1.printStackTrace();
			}
        }
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

 						frame.setVisible(false);
 						String[] arguments = new String[] {""};
 		                LoginView.main(arguments);
 					}
 				} catch (Exception e1) {
 					logger.info("Exception logoutButton ===>>>" + e1);
 				}/* finally {
 					dbValidate.closeDatabase(sessionData);
 				}*/
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

 		JButton studentButton = new JButton("Student");
 		studentButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
 		studentButton.setBounds(10, 100, 130, 35);
 		studentButton.setBackground(Color.GREEN);
 		leftPanel.add(studentButton);

 		studentButton.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent e) {

 				frame.setVisible(false);
 				new Student(sessionData, section, user_name, user_role);
 			}
 		});

 		JButton staffButton = new JButton("Staff");
 		staffButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
 		staffButton.setBounds(10, 150, 130, 35);
// 		leftPanel.add(staffButton);

 		staffButton.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent e) {

 				// frame.setVisible(false);
 				// new Welcome();
 			}
 		});

 		JButton academicButton = new JButton("Academic");
 		academicButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
 		academicButton.setBounds(10, 200, 130, 35);
 		leftPanel.add(academicButton);

 		academicButton.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent e) {

 				frame.setVisible(false);
 				new Academic(sessionData, section, user_name, user_role);
 			}
 		});

 		JButton accountButton = new JButton("Account");
 		accountButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
 		accountButton.setBounds(10, 250, 130, 35);
// 		leftPanel.add(accountButton);

 		accountButton.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent e) {

 				new Welcome(sessionData, user_name, user_role);
 				frame.setVisible(false);
 			}
 		});

 		JButton searchButton = new JButton("Find");
 		searchButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
 		searchButton.setBounds(10, 300, 130, 35);
 		leftPanel.add(searchButton);

 		searchButton.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent e) {

 				LinkedHashMap<String,LinkedHashMap<String, String>> studMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
                 new FindStudent(sessionData, "", "", "", "", "", "", studMap, section, user_name, user_role, false);
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
 		admissionButton.setBackground(Color.GREEN);
 		topbandPanel.add(admissionButton);

 		admissionButton.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent e) {
 				//no code
 			}
 		});

 		buttonX = buttonX + 160;
 		JButton strengthButton = new JButton("Strength");
 		strengthButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
 		strengthButton.setBounds(buttonX, 50, 150, 24); // 300, 50, 150, 24
 		topbandPanel.add(strengthButton);

 		strengthButton.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent e) {

 				List studList = new ArrayList();
 				new Strength(sessionData, "", "", "", section, user_name, user_role, studList, "", "");
 				frame.setVisible(false);
 			}
 		});

 		buttonX = buttonX + 160;
 		JButton lcButton = new JButton("Leaving certificate");
 		lcButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
 		lcButton.setBounds(buttonX, 50, 150, 24);
 		topbandPanel.add(lcButton);

 		lcButton.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent e) {

 				frame.setVisible(false);
 				List findLCList = new ArrayList();
 				new FindLeavingCert(sessionData, "", "", "", "", "", "", findLCList, false, "", "", "", "",
 						"", "", section, "", user_name, user_role,"","");
 			}
 		});

 		buttonX = buttonX + 160;
 		JButton bonafideButton = new JButton("Certificate");
 		bonafideButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		bonafideButton.setBounds(buttonX, 50, 150, 24);
 		topbandPanel.add(bonafideButton);

 		bonafideButton.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent e) {

 				frame.setVisible(false);
 				List findBonafideList = new ArrayList();
 				new FindBonafide(sessionData, "", "", "", "", "", "", findBonafideList, false, 
 						"", section, "", user_name, user_role, false);
 			}
 		});

 		buttonX = buttonX + 160;
 		JButton PrintListButton = new JButton("Print List");
 		PrintListButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
 		PrintListButton.setBounds(buttonX, 50, 150, 24); // 300, 50, 150, 24
 		topbandPanel.add(PrintListButton);

 		PrintListButton.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent e) {

 				List studList = new ArrayList();
 				new PrintList(sessionData, "", "", "", section, user_name, user_role, studList, "", "");
 				frame.setVisible(false);
 			}
 		});

 		JButton feeButton = new JButton("Fee Status");
 		feeButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
 		feeButton.setBounds(940, 50, 150, 24); // 300, 50, 150, 24
//     		topbandPanel.add(feeButton);

 		feeButton.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent e) {

 				List studentList = new ArrayList();
 				frame.setVisible(false);
 				new FeeStatus(sessionData, "", "", "", "", "", "", "", "", "", section, studentList, false,"",true);
 			}
 		});

 		mainPanel.add(topbandPanel, BorderLayout.NORTH);

 		// //////////scrollArea panel/////////////////////////////////////
		JPanel scrollAreaPanel = new JPanel(new BorderLayout());
		scrollAreaPanel.setPreferredSize(new Dimension(screenWidth - 150, screenHeight - 183));
 			
        // ///////////bottom band/////////////
        JPanel bottombandPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				Image img = new ImageIcon(img_path + img_mainband).getImage();
//				Dimension size = new Dimension(screenWidth - 158, screenHeight - 150);
				// change height to change scrolling
				Dimension size = new Dimension(screenWidth - 150, ((screenHeight - 150) + scrollHeight));
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
//				g.drawImage(img, 0, 0, null);
				g.drawImage(img, 0, 0, screenWidth - 150, (screenHeight - 150) + scrollHeight, null);
			}
		};
        bottombandPanel.setLayout(null);

     // /////////////Admission form//////////////////////
 		// /////////////GR No.//////////////
 		JLabel gr_no_label = new JLabel("G.R. No. :");
 		gr_no_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		gr_no_label.setBounds(30, 0, 100, 50);
 		bottombandPanel.add(gr_no_label);

 		// try {
 		// topGrNo = Integer.parseInt(admissionDB.getTopData("GR_NO",
 		// "HS_GENERAL_REGISTER",
 		// section));
 		// nextGrNo = topGrNo+1;
 		// logger.info("nextGrNo :: "+nextGrNo);
 		// nxtGrString=""+nextGrNo;
 		// if(nxtGrString.length() < 6){
 		// Common cm = new Common();
 		// nxtGrString = cm.appendZero(nxtGrString);
 		// }
 		// logger.info("nxtGrString :: "+nxtGrString);
 		// } catch (NumberFormatException ne2) {
 		// logger.info("Exception ne2::"+ne2);
 		// } catch (Exception e2) {
 		// logger.info("Exception e2::"+e2);
 		// }
 		final JTextField gr_no_text = new JTextField(nxtGrString);
 		gr_no_text.setEditable(true);
 		if(classPageStatus.equalsIgnoreCase("VIEW") || classPageStatus.equalsIgnoreCase("EDIT")){
 			gr_no_text.setText(studentInfo[0]);
 			gr_no_text.setEditable(false);
 		}
 		gr_no_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		gr_no_text.setBounds(160, 12, 130, 25);
 		gr_no_text.setColumns(7);
 		bottombandPanel.add(gr_no_text);

 		SwingUtilities.invokeLater(new Runnable() {
 			public void run() {
 				gr_no_text.requestFocus();
 			}
 		});

 		/*gr_no_text.addKeyListener(new KeyAdapter() {
     			public void keyTyped(KeyEvent e) {
     				String grString = gr_no_text.getText();
     				if (grString.length() > 6) {
     					commonObj.showMessageDialog("G.R. No. should not exceed 7 digit.");
     				}
     			}
     			public void keyPressed(KeyEvent e) {
     				String grString = gr_no_text.getText();
     				if (grString.length() > 6) {
     					grString = grString.substring(0, 7);
     					logger.info("grString===>" + grString);
     					gr_no_text.setText(grString);
     				}
     			}
     			public void keyReleased(KeyEvent e) {
     				String grString = gr_no_text.getText();
     				if (grString.length() > 6) {
//     					grString = grString.substring(0, 7);
//     					gr_no_text.setText(grString);
     					commonObj.showMessageDialog("G.R. No. should not exceed 7 digit.");
     				}
     			}
     		});*/

 		gr_no_text.addFocusListener(new java.awt.event.FocusAdapter() {
 			public void focusLost(java.awt.event.FocusEvent event) {
 				String grString = gr_no_text.getText();
 				if(commonObj.validateAlphaNum(grString)){
 					grString = grString.replace(" ", "");
 					if (grString.length() < 7 && grString.length() !=  0) {
 						gr_no_text.setText(commonObj.appendZero(grString));
 					} else if(grString.length() > 6) {
 						grString = grString.substring(0, 7);
 						gr_no_text.setText(grString);
 					}
 				}
 				/*else if(!grString.equalsIgnoreCase("")){
 					commonObj.showMessageDialog("Please enter valid GR No.");
 				}*/
 			}
 		});

// 		////////////////////////insert image from Database////////////////
// 		byte b[] = null;
// 		
// 		try {
// 			if (dbValidate.connectDatabase(sessionData)) {
// 				b = dbValidate.getImage(sessionData, classGr);
// 			}
// 		} catch (Exception e1) {
// 			commonObj.logException(e1);
// 		}
// 		
// 		ImageIcon studentImage;
// 		if(b == null) {
// 			studentImage = new ImageIcon(img_path + "student.jpg");
// 		}
// 		else {
// 			studentImage = new ImageIcon(b);
// 		}
// 		final JLabel labelStudentImg = new JLabel(studentImage);
//        labelStudentImg.setBounds(960, -130, 120, 417);
// 		bottombandPanel.add(labelStudentImg);
 		
 		byte b[] = null;
 		ImageIcon studentImage;
 		
 		String studentImagePath = commonObj.createFolder(commonObj.getDriveName() + "/" + sessionData.getSchoolName()
			+ "_app/STUDENTIMAGE/" + sessionData.getAppType() + "/");
		String fileName = gr_no_text.getText().trim()+"_"+sessionData.getSectionName()+".jpg";
		try {
			b = commonObj.imageToByteArray(studentImagePath + fileName);
		} catch (IOException e2) {
			commonObj.logException(e2);
		}
		
		if(b == null) {
 			studentImage = new ImageIcon(img_path + "student.jpg");
 		}
 		else {
 			studentImage = new ImageIcon(b);
 		}
//		studentImage = new ImageIcon(b);
 		
 		final JLabel labelStudentImg = new JLabel();
 		labelStudentImg.setIcon(new ImageIcon(studentImage.getImage().getScaledInstance(120, 140, Image.SCALE_DEFAULT)));
        labelStudentImg.setBounds(960, -130, 120, 417);
 		bottombandPanel.add(labelStudentImg);
     	
 		// /////////////Student udise ID//////////////
 		JLabel suid_label = new JLabel("Student Udise ID :");
 		suid_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		suid_label.setBounds(330, 0, 150, 50);
 		bottombandPanel.add(suid_label);
 		
 		final JTextField suid_text = new JTextField("Optional");
 		suid_text.setEditable(true);
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			suid_text.setText(studentInfo[29]);
 			suid_text.setEditable(false);
 		}
 		else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			suid_text.setText(studentInfo[29]);
//     			suid_text.setEditable(false);
 		}
 		suid_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		suid_text.setForeground(Color.GRAY);
 		suid_text.setBounds(480, 12, 200, 25);
 		suid_text.setColumns(7);
 		bottombandPanel.add(suid_text);
 		
 		if(academicYearDB.equalsIgnoreCase("")){
 			academicYearDB = academicYear;
 		}
 		String nextYear = commonObj.getNextYear(academicYearDB);
 		if(academicYearDB.equalsIgnoreCase(nextYear)) {
 			nextYear = academicYear;
 		}
 		
 		JLabel academicYear_label = new JLabel("Academic Year : ");
 		academicYear_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		academicYear_label.setBounds(700, 0, 300, 50);
 		bottombandPanel.add(academicYear_label);
 		
 		String yearList[] = { academicYearDB, nextYear };
 		final JComboBox year_combo = new JComboBox(yearList);
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			year_combo.setSelectedItem(studentInfo[28]);
 			year_combo.setEnabled(false);
 		}
 		year_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		year_combo.setBounds(850, 12, 100, 25);
 		bottombandPanel.add(year_combo);
 		
 		// /////////////Last Name//////////////
 		JLabel lastName_label = new JLabel("Last Name :");
 		lastName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		lastName_label.setBounds(30, 40, 110, 50);
 		bottombandPanel.add(lastName_label);

 		final JTextField lastName_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			lastName_text.setText(studentInfo[1]);
 			lastName_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			lastName_text.setText(studentInfo[1]);
 		}
 		lastName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		lastName_text.setBounds(160, 50, 180, 25);
 		bottombandPanel.add(lastName_text);
 		// //////////////////////////////////
 		// /////////////First Name//////////////
 		JLabel firstName_label = new JLabel("First Name :");
 		firstName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		firstName_label.setBounds(350, 40, 120, 50);
 		bottombandPanel.add(firstName_label);

 		final JTextField firstName_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			firstName_text.setText(studentInfo[2]);
 			firstName_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			firstName_text.setText(studentInfo[2]);
 		}
 		firstName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		firstName_text.setBounds(450, 50, 180, 25);
 		bottombandPanel.add(firstName_text);
 		// //////////////////////////////////
 		// /////////////Father Name//////////////
 		JLabel fatherName_label = new JLabel("Father Name :");
 		fatherName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		fatherName_label.setBounds(660, 40, 110, 50);
 		bottombandPanel.add(fatherName_label);

 		final JTextField fatherName_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			fatherName_text.setText(studentInfo[3]);
 			fatherName_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			fatherName_text.setText(studentInfo[3]);
 		}
 		fatherName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		fatherName_text.setBounds(770, 50, 180, 25);
 		bottombandPanel.add(fatherName_text);
 		// //////////////////////////////////
 		// /////////////Mother Name//////////////
 		JLabel motherName_label = new JLabel("Mother Name :");
 		motherName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		motherName_label.setBounds(30, 90, 120, 50);
 		bottombandPanel.add(motherName_label);

 		final JTextField motherName_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			motherName_text.setText(studentInfo[4]);
 			motherName_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			motherName_text.setText(studentInfo[4]);
 		}
 		motherName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		motherName_text.setBounds(160, 100, 200, 25);
 		bottombandPanel.add(motherName_text);
 		// //////////////////////////////////
 		// /////////////Gender//////////////
 		JLabel gender_label = new JLabel("Gender :");
 		gender_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		gender_label.setBounds(380, 90, 120, 50);
 		bottombandPanel.add(gender_label);

 		String selectGender = "Select";
 		if(!classPageStatus.equalsIgnoreCase("ADMISSION")){
 			selectGender = studentInfo[5];
 		}
 		String genderList[] = { selectGender, "MALE", "FEMALE" };
 		final JComboBox gender_combo = new JComboBox(genderList);
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			gender_combo.setEnabled(false);
 		}
 		gender_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		gender_combo.setBounds(460, 100, 160, 25);
 		bottombandPanel.add(gender_combo);
 		// //////////////////////////////////
 		// /////////////Email//////////////
 		JLabel email_label = new JLabel("Email Id :");
 		email_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		email_label.setBounds(650, 90, 130, 50);
 		bottombandPanel.add(email_label);

 		final JTextField email_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			email_text.setText(studentInfo[6]);
 			email_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			email_text.setText(studentInfo[6]);
 		}
 		email_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		email_text.setBounds(730, 100, 220, 25);
 		bottombandPanel.add(email_text);
 		// //////////////////////////////////
 		// /////////////Permanent Address//////////////
 		JLabel permanentAdd_label = new JLabel("Permanent Add :");
 		permanentAdd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		permanentAdd_label.setBounds(30, 140, 130, 50);
 		bottombandPanel.add(permanentAdd_label);

 		final JTextField permanentAdd_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			String permanentAdd = commonObj.revertCommaApostrophy(studentInfo[7]);
 			permanentAdd_text.setText(permanentAdd);
 			permanentAdd_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			String permanentAdd = commonObj.revertCommaApostrophy(studentInfo[7]);
 			permanentAdd_text.setText(permanentAdd);
 			permanentAdd_text.setText(permanentAdd);
 		}
 		permanentAdd_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		permanentAdd_text.setBounds(160, 150, 920, 25);
 		bottombandPanel.add(permanentAdd_text);
 		// //////////////////////////////////
 		// /////////////Residential Address//////////////
 		JLabel residentAdd_label = new JLabel("Resident Add :");
 		residentAdd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		residentAdd_label.setBounds(30, 190, 130, 50);
 		bottombandPanel.add(residentAdd_label);

 		final JTextField residentAdd_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			String residentAdd = commonObj.revertCommaApostrophy(studentInfo[8]);
 			residentAdd_text.setText(residentAdd);
 			residentAdd_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			String residentAdd = commonObj.revertCommaApostrophy(studentInfo[8]);
 			residentAdd_text.setText(residentAdd);
 		}
 		residentAdd_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		residentAdd_text.setBounds(160, 200, 920, 25);
 		bottombandPanel.add(residentAdd_text);
 		
 		// /////////////Contact 1//////////////
 		JLabel contact1_label = new JLabel("Mobile No. :");
 		contact1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		contact1_label.setBounds(30, 230, 120, 50);
 		bottombandPanel.add(contact1_label);

 		final JTextField contact1_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			contact1_text.setText(studentInfo[9]);
 			contact1_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			contact1_text.setText(studentInfo[9]);
 		}
 		contact1_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		contact1_text.setBounds(160, 240, 120, 25);
 		bottombandPanel.add(contact1_text);
 		// //////////////////////////////////
 		// /////////////Contact 2//////////////
 		JLabel contact2_label = new JLabel("Contact No. 2 :");
 		contact2_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		contact2_label.setBounds(300, 230, 120, 50);
 		bottombandPanel.add(contact2_label);

 		final JTextField contact2_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			contact2_text.setText(studentInfo[10]);
 			contact2_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			contact2_text.setText(studentInfo[10]);
 		}
 		contact2_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		contact2_text.setBounds(420, 240, 120, 25);
 		bottombandPanel.add(contact2_text);
 		// //////////////////////////////////
 		// /////////////Birth Place//////////////
 		JLabel birthPlace_label = new JLabel("Birth Place :");
 		birthPlace_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		birthPlace_label.setBounds(560, 230, 120, 50); // 570
 		bottombandPanel.add(birthPlace_label);

 		final JTextField birthPlace_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			String birthPlace = commonObj.revertCommaApostrophy(studentInfo[13]);
 			birthPlace_text.setText(birthPlace);
 			birthPlace_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			String birthPlace = commonObj.revertCommaApostrophy(studentInfo[13]);
 			birthPlace_text.setText(birthPlace);
 		}
 		birthPlace_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		birthPlace_text.setBounds(660, 240, 140, 25);
 		bottombandPanel.add(birthPlace_text);
 		// //////////////////////////////////
 		// /////////////Taluka//////////////
 		JLabel taluka_label = new JLabel("Taluka :");
 		taluka_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		taluka_label.setBounds(820, 230, 130, 50);
 		bottombandPanel.add(taluka_label);

 		final JTextField taluka_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			taluka_text.setText(studentInfo[31]);
 			taluka_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			taluka_text.setText(studentInfo[31]);
 		}
 		taluka_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		taluka_text.setBounds(890, 240, 190, 25);
 		bottombandPanel.add(taluka_text);
 		// //////////////////////////////////
 		// /////////////District//////////////
 		JLabel district_label = new JLabel("District :");
 		district_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		district_label.setBounds(30, 280, 130, 50);
 		bottombandPanel.add(district_label);

 		final JTextField district_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			district_text.setText(studentInfo[32]);
 			district_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			district_text.setText(studentInfo[32]);
 		}
 		district_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		district_text.setBounds(160, 290, 120, 25);
 		bottombandPanel.add(district_text);
 		// //////////////////////////////////
 		// //////////////State//////////////
 		JLabel state_label = new JLabel("State :");
 		state_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		state_label.setBounds(300, 280, 130, 50);
 		bottombandPanel.add(state_label);

 		final JTextField state_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			state_text.setText(studentInfo[33]);
 			state_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			state_text.setText(studentInfo[33]);
 		}
 		state_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		state_text.setBounds(350, 290, 180, 25);
 		bottombandPanel.add(state_text);
 		// //////////////////////////////////
 		// /////////////Country//////////////
 		JLabel country_label = new JLabel("Country :");
 		country_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		country_label.setBounds(550, 280, 130, 50);
 		bottombandPanel.add(country_label);

 		final JTextField country_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			country_text.setText(studentInfo[34]);
 			country_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			country_text.setText(studentInfo[34]);
 		}
 		country_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		country_text.setBounds(630, 290, 150, 25);
 		bottombandPanel.add(country_text);
 		// //////////////////////////////////
 		// /////////////Date of birth//////////////
 		JLabel dob_label = new JLabel("Date of Birth :");
 		dob_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		dob_label.setBounds(820, 280, 120, 50);
 		bottombandPanel.add(dob_label);

 		final JTextField dobDD_text = new JTextField("DD");
 		dobDD_text.setForeground(Color.GRAY);
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			dobDD_text.setForeground(Color.BLACK);
 			if(studentInfo[11] != null && !studentInfo[11].trim().equalsIgnoreCase("")){
 				dobDD_text.setText(studentInfo[11].substring(studentInfo[11].lastIndexOf("-") + 1, studentInfo[11].lastIndexOf("-") + 3));
 			}
 			dobDD_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			dobDD_text.setForeground(Color.BLACK);
 			if(studentInfo[11] != null && !studentInfo[11].trim().equalsIgnoreCase("")){
 				dobDD_text.setText(studentInfo[11].substring(studentInfo[11].lastIndexOf("-") + 1, studentInfo[11].lastIndexOf("-") + 3));
 			}
 		}
 		dobDD_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		dobDD_text.setBounds(930, 290, 30, 25);
 		bottombandPanel.add(dobDD_text);

 		JLabel slashDD_label = new JLabel("/");
 		slashDD_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		slashDD_label.setBounds(962, 280, 10, 50);
 		bottombandPanel.add(slashDD_label);

 		final JTextField dobMM_text = new JTextField("MM");
 		dobMM_text.setForeground(Color.GRAY);
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			dobMM_text.setForeground(Color.BLACK);
 			if(studentInfo[11] != null && !studentInfo[11].trim().equalsIgnoreCase("")){
 				dobMM_text.setText(studentInfo[11].substring(studentInfo[11].indexOf("-") + 1, studentInfo[11].indexOf("-") + 3));
 			}
 			dobMM_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			dobMM_text.setForeground(Color.BLACK);
 			if(studentInfo[11] != null && !studentInfo[11].trim().equalsIgnoreCase("")){
 				dobMM_text.setText(studentInfo[11].substring(studentInfo[11].indexOf("-") + 1, studentInfo[11].indexOf("-") + 3));
 			}
 		}
 		dobMM_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		dobMM_text.setBounds(970, 290, 38, 25);
 		bottombandPanel.add(dobMM_text);

 		JLabel slashMM_label = new JLabel("/");
 		slashMM_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		slashMM_label.setBounds(1010, 280, 10, 50);
 		bottombandPanel.add(slashMM_label);

 		final JTextField dobYYYY_text = new JTextField("YYYY");
 		dobYYYY_text.setForeground(Color.GRAY);
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			dobYYYY_text.setForeground(Color.BLACK);
 			if(studentInfo[11] != null && !studentInfo[11].trim().equalsIgnoreCase("")){
 				dobYYYY_text.setText(studentInfo[11].substring(0, 4));
 			}
 			dobYYYY_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			dobYYYY_text.setForeground(Color.BLACK);
 			if(studentInfo[11] != null && !studentInfo[11].trim().equalsIgnoreCase("")){
 				dobYYYY_text.setText(studentInfo[11].substring(0, 4));
 			}
 		}
 		dobYYYY_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		dobYYYY_text.setBounds(1018, 290, 60, 25);
 		bottombandPanel.add(dobYYYY_text);

 		// JLabel dobFormat_label = new JLabel("(DD/MM/YYYY)");
 		// dobFormat_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		// dobFormat_label.setBounds(830, 230, 150, 50);
 		// bottombandPanel.add(dobFormat_label);
 		// //////////////////////////////////
 		// /////////////DOB in words//////////////
 		JLabel dobWords_label = new JLabel("DOB in Words :");
 		dobWords_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		dobWords_label.setBounds(30, 330, 130, 50);
 		bottombandPanel.add(dobWords_label);

 		final JTextField dobWords_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			if(studentInfo[12] != null && !studentInfo[12].trim().equalsIgnoreCase("")){
 				dobWords_text.setText(studentInfo[12].toString());
 			}
 			dobWords_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			if(studentInfo[12] != null && !studentInfo[12].trim().equalsIgnoreCase("")){
 				dobWords_text.setText(studentInfo[12]);
 			}
 		}
 		dobWords_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		dobWords_text.setBounds(160, 340, 920, 25);
 		dobWords_text.setEditable(false);
 		bottombandPanel.add(dobWords_text);
 		// //////////////////////////////////
 		// /////////////Nationality//////////////
 		JLabel nationality_label = new JLabel("Nationality :");
 		nationality_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		nationality_label.setBounds(30, 380, 120, 50);
 		bottombandPanel.add(nationality_label);

 		final JTextField nationality_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			nationality_text.setText(studentInfo[14]);
 			nationality_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			nationality_text.setText(studentInfo[14]);
 		}
 		nationality_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		nationality_text.setBounds(160, 390, 200, 25);
 		bottombandPanel.add(nationality_text);
 		// //////////////////////////////////
 		// /////////////Religion//////////////
 		JLabel religion_label = new JLabel("Religion :");
 		religion_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		religion_label.setBounds(380, 380, 120, 50);
 		bottombandPanel.add(religion_label);

 		String selectReligion = "Select";
 		if(!classPageStatus.equalsIgnoreCase("ADMISSION")){
 			selectReligion = studentInfo[15];
 		}
 		religionList = selectReligion+","+religionList;
 		String[] religion = religionList.split(",");
//     		String religionList[] = { "Hindu", "Muslim", "Christian", "Sikh", "Jain", "Parsi", "Buddha", "NavBuddha" };
 		final JComboBox religion_combo = new JComboBox(religion);
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			religion_combo.setEnabled(false);
 		}
 		religion_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		religion_combo.setBounds(480, 390, 150, 25);
 		bottombandPanel.add(religion_combo);
 		
 		JLabel otherReligion_label = new JLabel("change if other (Shown on LC)");
 		otherReligion_label.setFont(new Font("Book Antiqua", Font.BOLD, 10));
 		otherReligion_label.setBounds(650, 355, 160, 50);
 		bottombandPanel.add(otherReligion_label);
 		
 		final JTextField religion_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("ADMISSION")){
 			religion_text.setText("Select");
 		}
 		else if(classPageStatus.equalsIgnoreCase("VIEW")){
 			religion_text.setText(studentInfo[25]);
 			religion_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			religion_text.setText(studentInfo[25]);
 		}
 		religion_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		religion_text.setBounds(650, 390, 200, 25);
 		bottombandPanel.add(religion_text);
 		// //////////////////////////////////
 		// /////////////Category//////////////
 		JLabel category_label = new JLabel("Category :");
 		category_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		category_label.setBounds(890, 380, 120, 50);
 		bottombandPanel.add(category_label);

 		String selectCategory = "Select";
 		if(!classPageStatus.equalsIgnoreCase("ADMISSION")){
 			selectCategory = studentInfo[16];
 		}
 		categoryList = selectCategory+","+categoryList;
 		String[] category = categoryList.split(",");
//     		String categoryList[] = { "SC", "ST", "NT A", "NT B", "NT C", "NT D", "SBC", "OBC", "OPEN" };
 		final JComboBox category_combo = new JComboBox(category);
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			category_combo.setEnabled(false);
 		}
 		category_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		category_combo.setBounds(980, 390, 100, 25);
 		bottombandPanel.add(category_combo);
 		// //////////////////////////////////
 		// /////////////Cast//////////////
 		JLabel cast_label = new JLabel("Cast :");
 		cast_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		cast_label.setBounds(30, 430, 120, 50);
 		bottombandPanel.add(cast_label);

 		final JTextField cast_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			cast_text.setText(studentInfo[17]);
 			cast_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			cast_text.setText(studentInfo[17]);
 		}
 		cast_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		cast_text.setBounds(160, 440, 150, 25);
 		bottombandPanel.add(cast_text);
 		// /////////////Sub Cast//////////////
 		JLabel subcast_label = new JLabel("Sub Cast :");
 		subcast_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		subcast_label.setBounds(320, 430, 120, 50);
 		bottombandPanel.add(subcast_label);

 		final JTextField subcast_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			subcast_text.setText(studentInfo[30]);
 			subcast_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			subcast_text.setText(studentInfo[30]);
 		}
 		subcast_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		subcast_text.setBounds(410, 440, 150, 25);
 		bottombandPanel.add(subcast_text);
 		// //////////////////////////////////
 		// /////////////Mother Tongue//////////////
 		JLabel motherTongue_label = new JLabel("Mother Tongue :");
 		motherTongue_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		motherTongue_label.setBounds(580, 430, 140, 50);
 		bottombandPanel.add(motherTongue_label);

 		final JTextField motherTongue_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			motherTongue_text.setText(studentInfo[18]);
 			motherTongue_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			motherTongue_text.setText(studentInfo[18]);
 		}
 		motherTongue_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		motherTongue_text.setBounds(710, 440, 100, 25);
 		bottombandPanel.add(motherTongue_text);
 		// //////////////////////////////////
 		// /////////////Date of Admission//////////////
 		JLabel doa_label = new JLabel("Admission Date :");
 		doa_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		doa_label.setBounds(820, 430, 150, 50);
 		bottombandPanel.add(doa_label);

 		final JTextField doaDD_text = new JTextField("DD");
 		if((classPageStatus.equalsIgnoreCase("EDIT")) && studentInfo[19] != null && !studentInfo[19].equalsIgnoreCase(" ") && !studentInfo[19].equalsIgnoreCase("-")){
 			doaDD_text.setText(studentInfo[19].substring(studentInfo[19].lastIndexOf("-") + 1, studentInfo[19].lastIndexOf("-") + 3));
 		}
 		else if(classPageStatus.equalsIgnoreCase("VIEW")){
 			if(studentInfo[19] != null && !studentInfo[19].equalsIgnoreCase(" ") && !studentInfo[19].equalsIgnoreCase("-")){
 				doaDD_text.setText(studentInfo[19].substring(studentInfo[19].lastIndexOf("-") + 1, studentInfo[19].lastIndexOf("-") + 3));
 			}
 			doaDD_text.setEditable(false);
 		}
 		doaDD_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		doaDD_text.setBounds(950, 440, 30, 25);
 		bottombandPanel.add(doaDD_text);

 		JLabel slashDDoa_label = new JLabel("/");
 		slashDDoa_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		slashDDoa_label.setBounds(982, 430, 10, 50);
 		bottombandPanel.add(slashDDoa_label);

 		final JTextField doaMM_text = new JTextField("MM");
 		if((classPageStatus.equalsIgnoreCase("EDIT")) && studentInfo[19] != null && !studentInfo[19].equalsIgnoreCase(" ") && !studentInfo[19].equalsIgnoreCase("-")){
 			doaMM_text.setText(studentInfo[19].substring(studentInfo[19].indexOf("-") + 1, studentInfo[19].indexOf("-") + 3));
 		}
 		else if(classPageStatus.equalsIgnoreCase("VIEW")){
 			if(studentInfo[19] != null && !studentInfo[19].equalsIgnoreCase(" ") && !studentInfo[19].equalsIgnoreCase("-")){
 				doaMM_text.setText(studentInfo[19].substring(studentInfo[19].indexOf("-") + 1, studentInfo[19].indexOf("-") + 3));
 			}
 			doaMM_text.setEditable(false);
 		}
 		doaMM_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		doaMM_text.setBounds(990, 440, 38, 25);
 		bottombandPanel.add(doaMM_text);

 		JLabel slashMDoa_label = new JLabel("/");
 		slashMDoa_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		slashMDoa_label.setBounds(1032, 430, 10, 50);
 		bottombandPanel.add(slashMDoa_label);

 		final JTextField doaYYYY_text = new JTextField("YYYY");
 		if((classPageStatus.equalsIgnoreCase("EDIT")) && studentInfo[19] != null && !studentInfo[19].equalsIgnoreCase(" ") && !studentInfo[19].equalsIgnoreCase("-")){
 			doaYYYY_text.setText(studentInfo[19].substring(0, 4));
 		}
 		else if(classPageStatus.equalsIgnoreCase("VIEW")){
 			if(studentInfo[19] != null && !studentInfo[19].equalsIgnoreCase(" ") && !studentInfo[19].equalsIgnoreCase("-")){
 				doaYYYY_text.setText(studentInfo[19].substring(0, 4));
 			}
 			doaYYYY_text.setEditable(false);
 		}
 		doaYYYY_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		doaYYYY_text.setBounds(1040, 440, 60, 25);
 		bottombandPanel.add(doaYYYY_text);
 		//////////////////////////
 		// /////////////Admitted Std//////////////
 		JLabel admittedStd_label = new JLabel("Admitted Std :");
 		admittedStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		admittedStd_label.setBounds(30, 480, 120, 50);
 		bottombandPanel.add(admittedStd_label);

 		String selectAdStd = std.substring(0,std.indexOf(","));
 		if(!classPageStatus.equalsIgnoreCase("ADMISSION")){
 			selectAdStd = studentInfo[21];
 		}
//     		std = selectAdStd+","+std;
 		String[] stdList = std.split(",");
 		// String admittedStdList[] = {"V","VI","VII","VIII","IX","X"};
 		final JComboBox admittedStd_combo = new JComboBox(stdList);
 		admittedStd_combo.setSelectedItem(selectAdStd);
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			admittedStd_combo.setSelectedItem(studentInfo[21]);
 			admittedStd_combo.setEnabled(false);
 		}
 		
 		admittedStd_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		admittedStd_combo.setBounds(160, 490, 100, 25);
 		bottombandPanel.add(admittedStd_combo);
 		// //////////////////////////////////
 		// /////////////Admitted Div//////////////
 		JLabel admittedDiv_label = new JLabel("Admitted Div :");
 		admittedDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		admittedDiv_label.setBounds(270, 480, 120, 50);
 		bottombandPanel.add(admittedDiv_label);

 		String selectAdDiv = "";
 		if(div.contains(",")){
 			selectAdDiv = div.substring(0,div.indexOf(","));
 		}
 		if(!classPageStatus.equalsIgnoreCase("ADMISSION")){
 			selectAdDiv = studentInfo[22];
 		}
//     		div = selectAdDiv+","+div;
 		String[] divList = div.split(",");
 		// String admittedDivList[] = {"A","B","C","D"};
 		final JComboBox admittedDiv_combo = new JComboBox(divList);
 		admittedDiv_combo.setSelectedItem(selectAdDiv);
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			admittedDiv_combo.setEnabled(false);
 		}
 		admittedDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		admittedDiv_combo.setBounds(380, 490, 100, 25);
 		bottombandPanel.add(admittedDiv_combo);
 		// //////////////////////////////////
 		// /////////////present Std//////////////
 		JLabel presentStd_label = new JLabel("Present Std :");
 		presentStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		presentStd_label.setBounds(490, 480, 120, 50);
 		bottombandPanel.add(presentStd_label);

 		String selectPrStd = std.substring(0,std.indexOf(","));
 		if(!classPageStatus.equalsIgnoreCase("ADMISSION")){
 			selectPrStd = studentInfo[26];
 		}
 		/*if(!selectPrStd.equalsIgnoreCase("")){
 			std = selectPrStd+","+std;
 		}*/
 		String[] presentstdList = std.split(",");
 		// String presentStdList[] = {"V","VI","VII","VIII","IX","X"};
 		final JComboBox presentStd_combo = new JComboBox(presentstdList);
 		presentStd_combo.setSelectedItem(selectPrStd);
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			presentStd_combo.setEnabled(false);
 		}
 		presentStd_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		presentStd_combo.setBounds(590, 490, 100, 25);
 		bottombandPanel.add(presentStd_combo);
 		// //////////////////////////////////
 		// /////////////present Div//////////////
 		JLabel presentDiv_label = new JLabel("Present Div :");
 		presentDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		presentDiv_label.setBounds(700, 480, 120, 50);
 		bottombandPanel.add(presentDiv_label);

 		String selectPrDiv = "";
 		if(div.contains(",")){
 			selectPrDiv = div.substring(0,div.indexOf(","));
 		}
 		if(!classPageStatus.equalsIgnoreCase("ADMISSION")){
 			selectPrDiv = studentInfo[27];
 		}
 		/*if(!selectPrDiv.equalsIgnoreCase("")){
 			div = selectPrDiv+","+div;
 		}*/
 		String[] presentdivList = div.split(",");
 		// String presentDivList[] = {"A","B","C","D"};
 		final JComboBox presentDiv_combo = new JComboBox(presentdivList);
 		presentDiv_combo.setSelectedItem(selectPrDiv);
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			presentDiv_combo.setEnabled(false);
 		}
 		presentDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		presentDiv_combo.setBounds(800, 490, 100, 25);
 		bottombandPanel.add(presentDiv_combo);
 		////////////////////////////////////
 		///////////////Payment Status//////////////
 		JLabel paymentStatus_label = new JLabel("Paying / Free :");
 		paymentStatus_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		paymentStatus_label.setBounds(910, 480, 130, 50);
 		bottombandPanel.add(paymentStatus_label);
 	
 		String selectPayStatus = "Select";
 		if(!classPageStatus.equalsIgnoreCase("ADMISSION")){
 			selectPayStatus = studentInfo[23];
 		}
 		payment_status = selectPayStatus+","+payment_status;
 		String[] paymentList = payment_status.split(",");
 	//	String paymentStatusList[] = { "Paying", "Free" };
 		final JComboBox paymentStatus_combo = new JComboBox(paymentList);
 		paymentStatus_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		paymentStatus_combo.setBounds(1020, 490, 90, 25);
 		bottombandPanel.add(paymentStatus_combo);
 		// /////////////Last School//////////////
 		JLabel lastSchool_label = new JLabel("Last School :");
 		lastSchool_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		lastSchool_label.setBounds(340, 520, 120, 50);
 		bottombandPanel.add(lastSchool_label);

 		final JTextField lastSchool_text = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			String lastSchool = commonObj.revertCommaApostrophy(studentInfo[20]);
 			lastSchool_text.setText(lastSchool);
 			lastSchool_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			String lastSchool = commonObj.revertCommaApostrophy(studentInfo[20]);
 			lastSchool_text.setText(lastSchool);
 		}
 		lastSchool_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		lastSchool_text.setBounds(440, 530, 450, 25);
 		bottombandPanel.add(lastSchool_text);
 		// //////////////////////////////////
 		final JTextField lastSchoolUdise_text = new JTextField("Last School Udise");
 		String lastSchoolUdise = "";
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			lastSchoolUdise = commonObj.revertCommaApostrophy(studentInfo[37]);
 			lastSchoolUdise_text.setText(lastSchoolUdise);
 			lastSchoolUdise_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			lastSchoolUdise = commonObj.revertCommaApostrophy(studentInfo[37]);
 			lastSchoolUdise_text.setText(lastSchoolUdise);
 		}
 		if(lastSchoolUdise.trim().equalsIgnoreCase("")) {
 			lastSchoolUdise_text.setText("Last School Udise");
 			lastSchoolUdise_text.setForeground(Color.GRAY);
 		}
 		else {
 			lastSchoolUdise_text.setForeground(Color.BLACK);
 		}
 		lastSchoolUdise_text.setToolTipText("Last School Udise");
 		lastSchoolUdise_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		lastSchoolUdise_text.setBounds(900, 530, 180, 25);
 		bottombandPanel.add(lastSchoolUdise_text);
 		// //////////////////////////////////

 		// /////////////Adhaar Card//////////////
 		JLabel adhaarCard_label = new JLabel("Adhaar Card :");
 		adhaarCard_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		adhaarCard_label.setBounds(30, 520, 130, 50);
 		bottombandPanel.add(adhaarCard_label);

 		String adhaar1 = "";
 		String adhaar2 = "";
 		String adhaar3 = "";
 		final JTextField adhaarCard_text1 = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			String adhaarCard = studentInfo[24];
 			if(adhaarCard != null && !adhaarCard.equalsIgnoreCase("") & adhaarCard.length() >= 12){
 				adhaar1 = adhaarCard.substring(0, 4);
 				adhaar2 = adhaarCard.substring(4, 8);
 				adhaar3 = adhaarCard.substring(8, 12);
 			}
 			adhaarCard_text1.setText(adhaar1);
 			adhaarCard_text1.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			String adhaarCard = studentInfo[24];
 			if(adhaarCard != null && !adhaarCard.equalsIgnoreCase("") & adhaarCard.length() >= 12){
 				adhaar1 = adhaarCard.substring(0, 4);
 				adhaar2 = adhaarCard.substring(4, 8);
 				adhaar3 = adhaarCard.substring(8, 12);
 			}
 			adhaarCard_text1.setText(adhaar1);
 		}
 		adhaarCard_text1.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		adhaarCard_text1.setBounds(160, 530, 50, 25);
 		bottombandPanel.add(adhaarCard_text1);

 		final JTextField adhaarCard_text2 = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			adhaarCard_text2.setText(adhaar2);
 			adhaarCard_text2.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			adhaarCard_text2.setText(adhaar2);
 		}
 		adhaarCard_text2.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		adhaarCard_text2.setBounds(220, 530, 50, 25);
 		bottombandPanel.add(adhaarCard_text2);

 		final JTextField adhaarCard_text3 = new JTextField();
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			adhaarCard_text3.setText(adhaar3);
 			adhaarCard_text3.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			adhaarCard_text3.setText(adhaar3);
 		}
 		adhaarCard_text3.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		adhaarCard_text3.setBounds(280, 530, 50, 25);
 		bottombandPanel.add(adhaarCard_text3);
 		
 		// /////////////Hobbies insert into EXTRA_1//////////////
 		JLabel hobbies_label = new JLabel("Hobbies :");
 		hobbies_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		hobbies_label.setBounds(30, 560, 130, 50);
 		bottombandPanel.add(hobbies_label);
 		
 		final JTextField hobbies_text = new JTextField();
 		hobbies_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
 		hobbies_text.setBounds(160, 570, 440, 25);
 		String hobbies = "";
 		if(classPageStatus.equalsIgnoreCase("VIEW")){
 			hobbies = commonObj.revertCommaApostrophy(studentInfo[35]);
 			hobbies_text.setText(hobbies);
 			hobbies_text.setEditable(false);
 		} else if(classPageStatus.equalsIgnoreCase("EDIT")){
 			hobbies = commonObj.revertCommaApostrophy(studentInfo[35]);
 			hobbies_text.setText(hobbies);
 		}
 		bottombandPanel.add(hobbies_text);
 		
 		String stdAdmittedWords = "";
 		if(classPageStatus.equalsIgnoreCase("EDIT")){
 			if(!studentInfo[36].trim().equalsIgnoreCase("") && studentInfo[36] != null){
 				stdAdmittedWords = " ("+commonObj.FirstWordCap(commonObj.RomanToWord(studentInfo[21]))+")";
 				admittedStdBranch = studentInfo[21] + stdAdmittedWords + studentInfo[36].substring(studentInfo[36].indexOf(")")+1);
 			}
 		}
 		
 		// //////////////////////////////////
 		// /////////////Submit//////////////
     	
 		final JButton printButton = new JButton("Print");
		printButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		printButton.setBounds(mainCentre - 350, 620, 130, 25);
		if(classPageStatus.equalsIgnoreCase("VIEW")) {
			bottombandPanel.add(printButton);
		}
		
		printButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean validateFields = true;
				String gr_no = "";
				String suid = "";
				String lastName = "";
				String firstName = "";
				String fatherName = "";
				String motherName = "";
				String gender = "";
				String emailId = "";
				String permanentAdd = "";
				String residentAdd = "";
				String taluka = "";
				String district = "";
				String state = "";
				String country = "";
				String contact1 = "";
				String contact2 = "";
				String birthDate = "";
				String dobWords = "";
				String birthPlace = "";
				String nationality = "";
				String religion = "";
				String category = "";
				String cast = "";
				String subcast = "";
				String motherTongue = "";
				String lastSchool = "";
				String admittedStd = "";
				String admittedDiv = "";
				String presentStd = "";
				String presentDiv = "";
				String dateAdmitted = "";
				String paymentStatus = "";
				String userName = currentUser.trim();
				String adhaarCard1 = "";
				String adhaarCard2 = "";
				String adhaarCard3 = "";
				String adhaarCard = "";
				String otherReligion = "";
				String hobbies = "";
				String academicSel = year_combo.getSelectedItem().toString();

				gr_no = gr_no_text.getText().trim();
				suid = suid_text.getText().trim();
				if(suid.equalsIgnoreCase("Optional")){
					suid = "";
				}
				lastName = lastName_text.getText().trim();
				firstName = firstName_text.getText().trim();
				fatherName = fatherName_text.getText().trim();
				motherName = motherName_text.getText().trim();
				gender = (String) gender_combo.getSelectedItem();
				emailId = email_text.getText().trim();
				permanentAdd = permanentAdd_text.getText().trim();
				permanentAdd = commonObj.replaceCommaApostrophy(permanentAdd);
				residentAdd = residentAdd_text.getText().trim();
				residentAdd = commonObj.replaceCommaApostrophy(residentAdd);
				taluka = taluka_text.getText().trim();
				district = district_text.getText().trim();
				state = state_text.getText().trim();
				country = country_text.getText().trim();
				contact1 = contact1_text.getText().trim();
				contact2 = contact2_text.getText().trim();
				birthDate = dobDD_text.getText().trim() + "/" + dobMM_text.getText().trim() + "/"
						+ dobYYYY_text.getText().trim();
				dobWords = dobWords_text.getText().trim();
				birthPlace = birthPlace_text.getText().trim();
				birthPlace = commonObj.replaceCommaApostrophy(birthPlace);
				nationality = nationality_text.getText().trim();
				religion = (String) religion_combo.getSelectedItem();
				otherReligion = (String) religion_text.getText().trim();
				if(otherReligion.equalsIgnoreCase("Select")){
					otherReligion = "";
				}
				category = (String) category_combo.getSelectedItem();
				cast = cast_text.getText().trim();
				subcast = subcast_text.getText().trim();
				motherTongue = motherTongue_text.getText().trim();
				lastSchool = lastSchool_text.getText().trim();
				lastSchool = commonObj.replaceCommaApostrophy(lastSchool);
				admittedStd = (String) admittedStd_combo.getSelectedItem();
				admittedDiv = (String) admittedDiv_combo.getSelectedItem();
				presentStd = (String) presentStd_combo.getSelectedItem();
				presentDiv = (String) presentDiv_combo.getSelectedItem();
				dateAdmitted = doaDD_text.getText().trim() + "/" + doaMM_text.getText().trim() + "/"
						+ doaYYYY_text.getText().trim();
				paymentStatus = (String) paymentStatus_combo.getSelectedItem();
				adhaarCard1 = adhaarCard_text1.getText().trim();
				adhaarCard2 = adhaarCard_text2.getText().trim();
				adhaarCard3 = adhaarCard_text3.getText().trim();
				adhaarCard  = adhaarCard1+adhaarCard2+adhaarCard3;
				hobbies = hobbies_text.getText();
				hobbies = commonObj.replaceCommaApostrophy(hobbies);

				int reply = 0;
				try {
					reply = JOptionPane.showConfirmDialog(null, "Would You Like to open & print admission form for Gr. No. "+gr_no+"?", "Confirm", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
//						AddmissionForm_PDF.getAddmissionForm_PDF(sessionData, gr_no, lastName, firstName, fatherName, motherName, gender,
//								emailId, permanentAdd, residentAdd, contact1, contact2, birthDate, dobWords, birthPlace,
//								nationality, religion, category, cast, motherTongue, lastSchool, admittedStd, admittedDiv,
//								presentStd, presentDiv, dateAdmitted, paymentStatus, userName, section, adhaarCard, otherReligion, academicSel,suid,
//								taluka, district,state,country,subcast, hobbies, admittedStdBranch);
						LinkedHashMap<String,String> selectedGr = new LinkedHashMap<String,String>();
						selectedGr.put(gr_no, "");
						AddmissionForm_PDF.getAddmissionForm_PDF(sessionData, selectedGr, "", "");
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				}
			}

		});
		
		if(classPageStatus.equalsIgnoreCase("Admission")){
			submitButtonName = "Submit";
		}
		else if(classPageStatus.equalsIgnoreCase("VIEW")){
			submitButtonName = "Edit";
		}
		else if(classPageStatus.equalsIgnoreCase("EDIT")){
			submitButtonName = "Save";
		}
		final JButton submitButton = new JButton(submitButtonName);
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(mainCentre - 150, 620, 130, 25);
		bottombandPanel.add(submitButton);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean validateFields = true;
				String gr_no = "";
				String suid = "";
				String lastName = "";
				String firstName = "";
				String fatherName = "";
				String motherName = "";
				String gender = "";
				String emailId = "";
				String permanentAdd = "";
				String residentAdd = "";
				String taluka = "";
				String district = "";
				String state = "";
				String country = "";
				String contact1 = "";
				String contact2 = "";
				String birthDate = "";
				String dobWords = "";
				String birthPlace = "";
				String nationality = "";
				String religion = "";
				String category = "";
				String cast = "";
				String subcast = "";
				String motherTongue = "";
				String lastSchool = "";
				String admittedStd = "";
				String admittedDiv = "";
				String presentStd = "";
				String presentDiv = "";
				String dateAdmitted = "";
				String paymentStatus = "";
				String userName = currentUser.trim();
				String adhaarCard1 = "";
				String adhaarCard2 = "";
				String adhaarCard3 = "";
				String adhaarCard = "";
				String otherReligion = "";
				String hobbies = "";
				String lastSchoolUdise = "";
				String academicSel = year_combo.getSelectedItem().toString();

				gr_no = gr_no_text.getText().trim();
				suid = suid_text.getText().trim();
				if(suid.equalsIgnoreCase("Optional")){
					suid = "";
				}
				lastName = lastName_text.getText().trim();
				firstName = firstName_text.getText().trim();
				fatherName = fatherName_text.getText().trim();
				motherName = motherName_text.getText().trim();
				gender = (String) gender_combo.getSelectedItem();
				emailId = email_text.getText().trim();
				permanentAdd = permanentAdd_text.getText().trim();
				permanentAdd = commonObj.replaceCommaApostrophy(permanentAdd);
				residentAdd = residentAdd_text.getText().trim();
				residentAdd = commonObj.replaceCommaApostrophy(residentAdd);
				taluka = taluka_text.getText().trim();
				district = district_text.getText().trim();
				state = state_text.getText().trim();
				country = country_text.getText().trim();
				contact1 = contact1_text.getText().trim();
				contact2 = contact2_text.getText().trim();
				birthDate = dobDD_text.getText().trim() + "/" + dobMM_text.getText().trim() + "/"
						+ dobYYYY_text.getText().trim();
				dobWords = dobWords_text.getText().trim();
				birthPlace = birthPlace_text.getText().trim();
				birthPlace = commonObj.replaceCommaApostrophy(birthPlace);
				nationality = nationality_text.getText().trim();
				religion = (String) religion_combo.getSelectedItem();
				otherReligion = (String) religion_text.getText().trim();
				if(otherReligion.equalsIgnoreCase("Select")){
					otherReligion = "";
				}
				category = (String) category_combo.getSelectedItem();
				cast = cast_text.getText().trim();
				subcast = subcast_text.getText().trim();
				motherTongue = motherTongue_text.getText().trim();
				lastSchool = lastSchool_text.getText().trim();
				lastSchool = commonObj.replaceCommaApostrophy(lastSchool);
				lastSchoolUdise = lastSchoolUdise_text.getText().trim();
				if(lastSchoolUdise.equalsIgnoreCase("Last School Udise") || lastSchoolUdise.equalsIgnoreCase("")){
					lastSchoolUdise = " ";
				}
				admittedStd = (String) admittedStd_combo.getSelectedItem();
				admittedDiv = (String) admittedDiv_combo.getSelectedItem();
				presentStd = (String) presentStd_combo.getSelectedItem();
				presentDiv = (String) presentDiv_combo.getSelectedItem();
				dateAdmitted = doaDD_text.getText().trim() + "/" + doaMM_text.getText().trim() + "/"
						+ doaYYYY_text.getText().trim();
				paymentStatus = (String) paymentStatus_combo.getSelectedItem();
				adhaarCard1 = adhaarCard_text1.getText().trim();
				adhaarCard2 = adhaarCard_text2.getText().trim();
				adhaarCard3 = adhaarCard_text3.getText().trim();
				adhaarCard  = adhaarCard1+adhaarCard2+adhaarCard3;
				hobbies = hobbies_text.getText();
				hobbies = commonObj.replaceCommaApostrophy(hobbies);

				boolean checkGrNoFlag = false;
				String lastGrNo = "";
				try {
					try {
						if (dbValidate.connectDatabase(sessionData) && submitButtonName.equalsIgnoreCase("Submit")) {
							checkGrNoFlag = dbValidate.validateGrNo(sessionData, gr_no, section, "HS_GENERAL_REGISTER");
							lastGrNo = dbValidate.getLastCount(sessionData, "HS_GENERAL_REGISTER", "GR_NO") + "";
							lastGrNo = commonObj.appendZero(lastGrNo);
						}
					} catch (Exception e1) {
						commonObj.logException(e1);
					} finally {
						dbValidate.closeDatabase(sessionData);
					}
					
					
					logger.info("checkGrNoFlag==>" + checkGrNoFlag);
					if (checkGrNoFlag && submitButtonName.equalsIgnoreCase("Submit")) {
						JOptionPane.showMessageDialog(null, "GR_NO " + gr_no + " already exists. Last Gr No. was "+lastGrNo);
					}
				} catch (Exception e1) {
					logger.info("Exception GR_NO ===>>>" + e1);
				}

				if (!checkGrNoFlag) {
					////without validation
					if(submitButtonName.equalsIgnoreCase("SAVE") || submitButtonName.equalsIgnoreCase("Submit")){
						if (gr_no.equalsIgnoreCase("") || gr_no.equalsIgnoreCase("0000000")) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid GR No.");
						} else if (commonObj.checkComma(gr_no)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "GR_NO cannot contain |-:';,");
						} else if (commonObj.checkComma(suid)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Student Udise id cannot contain |-:';,");
						} else if (suid.length() > 50) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Student Udise id", suid, 50));
						} else if (lastName.length() > 50) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("LastName", lastName, 50));
						} else if (commonObj.checkComma(lastName) || commonObj.validateSpecial(lastName)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid LastName");
						} else if (firstName.length() > 50) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("FirstName", firstName, 50));
						} else if (commonObj.checkComma(firstName) || firstName.equalsIgnoreCase("")
								|| commonObj.validateSpecial(firstName)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid FirstName");
						} else if (fatherName.length() > 50) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("FatherName", fatherName, 50));
						} else if (commonObj.checkComma(fatherName) || commonObj.validateSpecial(fatherName)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid FatherName");
						} else if (motherName.length() > 50) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("MotherName", motherName, 50));
						} else if (commonObj.checkComma(motherName) || commonObj.validateSpecial(motherName)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid MotherName");
						} else if (gender.equalsIgnoreCase("Select")) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please select Gender");
						} else if (!commonObj.validateEmail(emailId) && !emailId.equalsIgnoreCase("")) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Email Id");
						} else if (emailId.length() > 99) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Email Id", emailId, 99));
						} else if (commonObj.checkComma(emailId)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "EmailId cannot contain |-:';,");
						} else if (permanentAdd.length() > 200) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Permanent Add", permanentAdd, 200));
						} else if (commonObj.checkComma(permanentAdd)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Permanent Add without |:;");
						} else if (residentAdd.length() > 200) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Resident Add", residentAdd, 200));
						} else if (commonObj.checkComma(residentAdd)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Resident Add without |:;");
						} else if (commonObj.checkComma(taluka)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Taluka without |-:';,");
						} else if (taluka.length() > 30) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Taluka", taluka, 30));
						} else if (commonObj.checkComma(district)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid District without |-:';,");
						} else if (district.length() > 30) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("District", district, 30));
						} else if (commonObj.checkComma(state)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid State without |-:';,");
						} else if (state.length() > 30) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("State", state, 30));
						} else if (commonObj.checkComma(country)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Country without |-:';,");
						} else if (country.length() > 30) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Country", country, 30));
						} else if (!contact1.equalsIgnoreCase("") && (!commonObj.validateNumber(contact1) || contact1.length() != 10)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Mobile No.");
						} else if (contact1.length() > 20) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Contact 1", contact1, 20));
						} else if (!commonObj.validateOnlyNumber(contact2) && !contact2.equalsIgnoreCase("")) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Contact No. 2");
						} else if (contact2.length() > 20) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Contact 2", contact2, 20));
						} else if (!commonObj.validateDate(birthDate) || birthDate.equalsIgnoreCase("")) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Date of Birth");
						} else if (dobWords.length() > 100) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("DOB in words", dobWords, 100));
						} else if (commonObj.checkComma(dobWords)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Dob in Words cannot contain |-:';,");
						} else if (birthPlace.length() > 50) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Birth Place", birthPlace, 50));
						} else if (commonObj.checkComma(birthPlace)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid characters in Birth Place without |:;");
						} else if (nationality.length() > 20) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Nationality", nationality, 20));
						} else if (commonObj.checkComma(nationality) || commonObj.validateSpecial(nationality)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Nationality without |-:';,");
						} else if (religion.equalsIgnoreCase("Select")) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please select Religion");
						} else if (otherReligion.length() > 30) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Religion changed", otherReligion, 30));
						} else if (commonObj.checkComma(otherReligion) || commonObj.validateSpecial(otherReligion)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Religion without |-:';,");
						} else if(category.equalsIgnoreCase("Select")){
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please select Category");
						} else if (cast.length() > 20) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Cast", cast, 20));
						} else if (commonObj.checkComma(cast) || commonObj.validateSpecial(cast)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Cast without |-:';,");
						} else if (commonObj.checkComma(subcast)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Sub Cast without |-:';,");
						} else if (subcast.length() > 30) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Sub Cast", subcast, 30));
						} else if (motherTongue.length() > 20) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Mother Tongue", motherTongue, 20));
						} else if (commonObj.checkComma(motherTongue) || commonObj.validateSpecial(motherTongue)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid MotherTongue without |-:';,");
						} else if (lastSchool.length() > 80) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("LastSchool", lastSchool, 80));
						} else if (commonObj.checkComma(lastSchool)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "lastSchool cannot contain |:;");
						} else if (lastSchoolUdise.length() > 50) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, commonObj.charExceeded("Last School Udise No.", lastSchoolUdise, 50));
						} else if(admittedStd.equalsIgnoreCase("Select")){
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please select Admitted Std.");
						} else if(admittedDiv.equalsIgnoreCase("Select")){
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please select Admitted Div.");
						} else if(paymentStatus.equalsIgnoreCase("Select")){
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please select Paying / Free");
						} else if(presentStd.equalsIgnoreCase("Select")){
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please select Present Std.");
						} else if(presentDiv.equalsIgnoreCase("Select")){
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please select Present Div.");
						} else if (!classPageStatus.equalsIgnoreCase("Admission") && (!commonObj.validateDate(dateAdmitted) || dateAdmitted.equalsIgnoreCase(""))) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Date of Admission");
						} else if (!commonObj.validateDate(dateAdmitted)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Year of Admission");
						} else if(!commonObj.validateAadharNumber(adhaarCard1+adhaarCard2+adhaarCard3) && !(adhaarCard1+adhaarCard2+adhaarCard3).equalsIgnoreCase("")){
							validateFields = false;
							JOptionPane.showMessageDialog(null, "Please enter valid Adhaar Card");
						} else if (commonObj.checkComma(hobbies)) {
							validateFields = false;
							JOptionPane.showMessageDialog(null, "hobbies cannot contain |:;");
						}
					}
					Boolean flagValue = false;
					if (validateFields && dbValidate.connectDatabase(sessionData)) {
						int reply = 0;
						try {
							dbValidate.addPersonalDetailsColumns(sessionData);
							
							if(submitButtonName.equalsIgnoreCase("Submit")){
								reply = JOptionPane.showConfirmDialog(null, "Would You Like to add "+gr_no+" to academic year "+academicSel+"?", "Confirm", JOptionPane.YES_NO_OPTION);
								
								if (reply == JOptionPane.YES_OPTION) {
									try {
										flagValue = dbValidate.admitForm(sessionData, gr_no, lastName, firstName, fatherName, motherName, gender,
												emailId, permanentAdd, residentAdd, contact1, contact2, birthDate, dobWords,
												birthPlace, nationality, religion, category, cast, motherTongue, lastSchool, admittedStd, admittedDiv,
												presentStd, presentDiv, dateAdmitted, paymentStatus, userName, section,adhaarCard,otherReligion,suid,
												taluka, district,state,country,subcast, academicSel, hobbies, lastSchoolUdise);
									} catch (Exception e1) {
										JOptionPane.showMessageDialog(null, "Error : "+ e1);
									}
								} else if (reply == JOptionPane.NO_OPTION) {
									JOptionPane.showMessageDialog(null, "You have cancelled to admit student.");
								}
							}
							else if(submitButtonName.equalsIgnoreCase("Save")){
								String oldAcademicYear = studentInfo[28];
								flagValue = dbValidate.editForm(sessionData, gr_no, lastName, firstName, fatherName, motherName, gender,
										emailId, permanentAdd, residentAdd, contact1, contact2, birthDate, dobWords, birthPlace,
										nationality, religion, category, cast, motherTongue, lastSchool, admittedStd, admittedDiv,
										presentStd, presentDiv, dateAdmitted, paymentStatus, userName, section, adhaarCard, otherReligion, academicSel,suid,
										taluka, district,state,country,subcast, hobbies, admittedStdBranch, oldAcademicYear, lastSchoolUdise);
							}
							
							if (flagValue) {//generate PDF
								reply = JOptionPane.showConfirmDialog(null, "Would You Like to open & print admission form for Gr. No. "+gr_no+"?", "Confirm", JOptionPane.YES_NO_OPTION);
								if (reply == JOptionPane.YES_OPTION) {
//									AddmissionForm_PDF.getAddmissionForm_PDF(sessionData, gr_no, lastName, firstName, fatherName, motherName, gender,
//											emailId, permanentAdd, residentAdd, contact1, contact2, birthDate, dobWords, birthPlace,
//											nationality, religion, category, cast, motherTongue, lastSchool, admittedStd, admittedDiv,
//											presentStd, presentDiv, dateAdmitted, paymentStatus, userName, section, adhaarCard, otherReligion, academicSel,suid,
//											taluka, district,state,country,subcast, hobbies, admittedStdBranch);
									
									LinkedHashMap<String,String> selectedGr = new LinkedHashMap<String,String>();
									selectedGr.put(gr_no, "");
									AddmissionForm_PDF.getAddmissionForm_PDF(sessionData, selectedGr, "", "");
								}
							}
							if (flagValue && submitButtonName.equalsIgnoreCase("Submit")) {
								reply = JOptionPane.showConfirmDialog(null, "Would You Like to pay admission fee for "+gr_no+" to academic year "+academicSel+"?", "Confirm", JOptionPane.YES_NO_OPTION);
								frame.setVisible(false);
								if (reply == JOptionPane.YES_OPTION) {
									LinkedHashMap<String, LinkedHashMap<String, String>> feesHeadMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
									LinkedHashMap<String,String> studentDetailsMap = new LinkedHashMap<String,String>();
									LinkedHashMap<String,LinkedHashMap<String, String>> studentMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
									studentDetailsMap.put("grNo",gr_no);
									studentDetailsMap.put("std",presentStd);
									studentDetailsMap.put("div",presentDiv);
									studentDetailsMap.put("name",lastName+" "+firstName+" "+fatherName);
									studentDetailsMap.put("rollNo","1");
									studentDetailsMap.put("originalLc","");
									studentDetailsMap.put("contactNo1",contact1);
									studentDetailsMap.put("contactNo2",contact2);
									studentMap.put(gr_no, studentDetailsMap);
									
									if(dbValidate.connectDatabase(sessionData)){
										feesHeadMap = dbValidate.getFeesHeadData(sessionData, academicSel, presentStd, section, "");
									}
									
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
//									String maxFrequency = frequencyList.get(frequencyList.size() - 1).toString();
									new FeesView(sessionData, gr_no, presentStd, presentDiv, lastName+" "+firstName+" "+fatherName, 
											"1", studentMap, section, academicSel, 
											"", feesHeadMap, frequencyList.get(frequencyList.size() - 1)+"", "Part Pay", "", contact1, contact2, "", "", false);
								}else{
									frame.setVisible(false);
									new AdmissionFormNew(sessionData, section, user_name, user_role,"ADMISSION","", foundStudentList);
								}
							}
							else if (flagValue && submitButtonName.equalsIgnoreCase("Save")) {
								frame.setVisible(false);
								new FindStudent(sessionData, "", presentStd_combo.getSelectedItem().toString(), presentDiv_combo.getSelectedItem().toString(), "", "", 
										"", foundStudentList, section, user_name, user_role, false);
							}
							else if (submitButtonName.equalsIgnoreCase("Edit")){
								frame.setVisible(false);
								new AdmissionFormNew(sessionData, section, user_name, user_role,"Edit",gr_no, foundStudentList);
							}
							else if (reply != JOptionPane.NO_OPTION){
								JOptionPane.showMessageDialog(null, "Data insertion failed..");
							}
						} catch (Exception e1) {
							logger.info("Exception Admission form ===>>>" + e1);
							dbValidate.closeDatabase(sessionData);
							new Student(sessionData, section, user_name, user_role);
							frame.setVisible(false);
						}
					}
					dbValidate.closeDatabase(sessionData);
				}
			}

		});
		
		JScrollPane jsp;
		jsp = new JScrollPane(bottombandPanel);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollAreaPanel.add(jsp, BorderLayout.EAST);
        mainPanel.add(scrollAreaPanel, BorderLayout.SOUTH);
        panel.add(mainPanel, BorderLayout.EAST);

        suid_text.addFocusListener(new java.awt.event.FocusAdapter() {
			String suid = "";
			public void focusGained(java.awt.event.FocusEvent event) {

				suid = suid_text.getText();
				suid_text.setForeground(Color.BLACK);
				if (!suid.equalsIgnoreCase("Optional")) {
					suid_text.selectAll();
				} else {
					suid_text.setText("");
				}
			}

			public void focusLost(java.awt.event.FocusEvent event) {
				suid = suid_text.getText();
				if (suid.equalsIgnoreCase("Optional") || suid.equalsIgnoreCase("")) {
					suid_text.setForeground(Color.GRAY);
					suid_text.setText("Optional");
				}
			}
		});
		
		lastSchoolUdise_text.addFocusListener(new java.awt.event.FocusAdapter() {
			String lastSchoolUdise = "";
			public void focusGained(java.awt.event.FocusEvent event) {

				lastSchoolUdise = lastSchoolUdise_text.getText();
				lastSchoolUdise_text.setForeground(Color.BLACK);
				if (!lastSchoolUdise.equalsIgnoreCase("Last School Udise")) {
					lastSchoolUdise_text.selectAll();
				} else {
					lastSchoolUdise_text.setText("");
				}
			}

			public void focusLost(java.awt.event.FocusEvent event) {
				lastSchoolUdise = lastSchoolUdise_text.getText();
				if (lastSchoolUdise.equalsIgnoreCase("Last School Udise") || lastSchoolUdise.equalsIgnoreCase("")) {
					lastSchoolUdise_text.setForeground(Color.GRAY);
					lastSchoolUdise_text.setText("Last School Udise");
				}
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
		motherName_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				motherName_text.selectAll();
			}
		});
		email_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				email_text.selectAll();
			}
		});
		permanentAdd_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				permanentAdd_text.selectAll();
			}
		});
		residentAdd_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				residentAdd_text.selectAll();
			}
		});
		
		religion_combo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String religion_sel = (String) religion_combo.getSelectedItem();
                religion_text.setText(religion_sel);
                /*if(!religion_sel.equalsIgnoreCase("OTHER")){
                	religion_text.setText(religion_sel);
                	religion_text.setEnabled(false);
                }
                else if(religion_sel.equalsIgnoreCase("OTHER")){
                	religion_text.setText(religion_sel);
                	religion_text.setEnabled(true);
                }*/
            }
        });
		
		religion_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				religion_text.selectAll();
			}
		});
		
		contact1_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				contact1_text.selectAll();
			}

			public void focusLost(java.awt.event.FocusEvent event) {

				if (contact1_text.getText().length() > 12) {
					contact1_text.setText(contact1_text.getText().substring(0, 12));
				}
			}
		});
		contact2_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				contact2_text.selectAll();
			}

			public void focusLost(java.awt.event.FocusEvent event) {

				if (contact2_text.getText().length() > 12) {
					contact2_text.setText(contact2_text.getText().substring(0, 12));
				}
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
		
		birthPlace_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				birthPlace_text.selectAll();
			}
		});
		nationality_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				nationality_text.selectAll();
			}
		});
		cast_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				cast_text.selectAll();
			}
		});
		motherTongue_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				motherTongue_text.selectAll();
			}
		});
		doaDD_text.addFocusListener(new java.awt.event.FocusAdapter() {

			String strDD = "";

			public void focusGained(java.awt.event.FocusEvent event) {

				strDD = doaDD_text.getText();
				if (commonObj.validateOnlyNumber(strDD)) {
					doaDD_text.selectAll();
				} else {
					doaDD_text.setText("");
				}

			}

			public void focusLost(java.awt.event.FocusEvent event) {

				strDD = doaDD_text.getText();
				if (!commonObj.validateOnlyNumber(strDD)) {
					doaDD_text.setText("DD");
				} else if (strDD.length() != 2) {
					JOptionPane.showMessageDialog(null, "Please enter Date in two digit");
					doaDD_text.setText("DD");
					doaDD_text.requestFocus();
				}
			}
		});
		doaMM_text.addFocusListener(new java.awt.event.FocusAdapter() {

			String strMM = "";

			public void focusGained(java.awt.event.FocusEvent event) {

				strMM = doaMM_text.getText();
				if (commonObj.validateOnlyNumber(strMM)) {
					doaMM_text.selectAll();
				} else {
					doaMM_text.setText("");
				}

			}

			public void focusLost(java.awt.event.FocusEvent event) {

				strMM = doaMM_text.getText();
				if (!commonObj.validateOnlyNumber(strMM)) {
					doaMM_text.setText("MM");
				} else if (strMM.length() != 2) {
					JOptionPane.showMessageDialog(null, "Please enter Month in two digit");
					doaMM_text.setText("MM");
					doaMM_text.requestFocus();
				}
			}
		});
		doaYYYY_text.addFocusListener(new java.awt.event.FocusAdapter() {

			String strYYYY = "";

			public void focusGained(java.awt.event.FocusEvent event) {

				strYYYY = doaYYYY_text.getText();
				if (commonObj.validateOnlyNumber(strYYYY)) {
					doaYYYY_text.selectAll();
				} else {
					doaYYYY_text.setText("");
				}

			}

			public void focusLost(java.awt.event.FocusEvent event) {

				strYYYY = doaYYYY_text.getText();
				if (!commonObj.validateOnlyNumber(strYYYY)) {
					doaYYYY_text.setText("YYYY");
				} else if (strYYYY.length() != 4) {
					JOptionPane.showMessageDialog(null, "Please enter Year in four digit");
					doaYYYY_text.setText("YYYY");
					doaYYYY_text.requestFocus();
				}
			}
		});
		lastSchool_text.addFocusListener(new java.awt.event.FocusAdapter() {

			String strLastSchool = "";

			public void focusGained(java.awt.event.FocusEvent event) {

				strLastSchool = lastSchool_text.getText();
				if (!strLastSchool.equalsIgnoreCase("NA")) {
					lastSchool_text.selectAll();
				} else {
					lastSchool_text.setText("");
				}

			}

			public void focusLost(java.awt.event.FocusEvent event) {

				strLastSchool = lastSchool_text.getText();
				if (strLastSchool.equalsIgnoreCase("")) {
					lastSchool_text.setText("NA");
				}
			}
		});

		dobDD_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusLost(java.awt.event.FocusEvent event) {

				int day = 0;
				int month = 0;
				int year = 0;
				if (!dobDD_text.getText().equalsIgnoreCase("") && commonObj.validateOnlyNumber(dobDD_text.getText()))
					day = Integer.parseInt(dobDD_text.getText());
				if (!dobMM_text.getText().equalsIgnoreCase("") && commonObj.validateOnlyNumber(dobMM_text.getText()))
					month = Integer.parseInt(dobMM_text.getText());
				if (!dobYYYY_text.getText().equalsIgnoreCase("") && commonObj.validateOnlyNumber(dobYYYY_text.getText()))
					year = Integer.parseInt(dobYYYY_text.getText());
				if (day != 0 && month != 0 && year != 0) {
					dobWords_text.setText(commonObj.dateToWords(day, month, year).toUpperCase());
					// dobMM_text.requestFocus();
				}
			}
		});

		dobMM_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusLost(java.awt.event.FocusEvent event) {

				Common date2word = new Common();
				int day = 0;
				int month = 0;
				int year = 0;
				if (!dobDD_text.getText().equalsIgnoreCase("") && commonObj.validateOnlyNumber(dobDD_text.getText()))
					day = Integer.parseInt(dobDD_text.getText());
				if (!dobMM_text.getText().equalsIgnoreCase("") && commonObj.validateOnlyNumber(dobMM_text.getText()))
					month = Integer.parseInt(dobMM_text.getText());
				if (!dobYYYY_text.getText().equalsIgnoreCase("") && commonObj.validateOnlyNumber(dobYYYY_text.getText()))
					year = Integer.parseInt(dobYYYY_text.getText());
				if (day != 0 && month != 0 && year != 0) {
					dobWords_text.setText(date2word.dateToWords(day, month, year).toUpperCase());
					// dobYYYY_text.requestFocus();
				}
			}
		});

		dobYYYY_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusLost(java.awt.event.FocusEvent event) {

				Common date2word = new Common();
				int day = 0;
				int month = 0;
				int year = 0;
				if (!dobDD_text.getText().equalsIgnoreCase("") && commonObj.validateOnlyNumber(dobDD_text.getText()))
					day = Integer.parseInt(dobDD_text.getText());
				if (!dobMM_text.getText().equalsIgnoreCase("") && commonObj.validateOnlyNumber(dobMM_text.getText()))
					month = Integer.parseInt(dobMM_text.getText());
				if (!dobYYYY_text.getText().equalsIgnoreCase("") && commonObj.validateOnlyNumber(dobYYYY_text.getText()))
					year = Integer.parseInt(dobYYYY_text.getText());
				if (day != 0 && month != 0 && year != 0) {
					dobWords_text.setText(date2word.dateToWords(day, month, year).toUpperCase());
					// birthPlace_text.requestFocus();
				}
			}
		});
		
		/*adhaarCard_text1.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusLost(java.awt.event.FocusEvent event) {

				String adhaar1 = adhaarCard_text1.getText().trim();
				if (adhaar1.length() < 7) {
					Common cm = new Common();
					gr_no_text.setText(cm.appendZero(adhaar1));
				} else 
				if(adhaar1.length() > 3) {
					adhaar1 = adhaar1.substring(0, 4);
					adhaarCard_text1.setText(adhaar1);
				}
			}
		});*/
		/*adhaarCard_text1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				String adhaarString = adhaarCard_text1.getText();
				if (adhaarString.length() > 3) {
					adhaarString = adhaarString.substring(0, 4);
					adhaarCard_text1.setText(adhaarString);
				}
			}
			public void keyReleased(KeyEvent e) {
				String adhaarString = adhaarCard_text1.getText();
				if (adhaarString.length() > 3) {
					adhaarString = adhaarString.substring(0, 4);
					adhaarCard_text1.setText(adhaarString);
				}
			}
		});*/
		
		/*adhaarCard_text2.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusLost(java.awt.event.FocusEvent event) {

				String adhaar2 = adhaarCard_text2.getText().trim();
				if (adhaar2.length() < 7) {
					Common cm = new Common();
					gr_no_text.setText(cm.appendZero(adhaar2));
				} else 
				if(adhaar2.length() > 3) {
					adhaar2 = adhaar2.substring(0, 4);
					adhaarCard_text2.setText(adhaar2);
				}
			}
		});*/
		/*adhaarCard_text2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				String adhaarString = adhaarCard_text2.getText();
				if (adhaarString.length() > 3) {
					adhaarString = adhaarString.substring(0, 4);
					adhaarCard_text2.setText(adhaarString);
				}
			}
			public void keyReleased(KeyEvent e) {
				String adhaarString = adhaarCard_text2.getText();
				if (adhaarString.length() > 3) {
					adhaarString = adhaarString.substring(0, 4);
					adhaarCard_text2.setText(adhaarString);
				}
			}
		});*/
		
		/*adhaarCard_text3.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusLost(java.awt.event.FocusEvent event) {

				String adhaar3 = adhaarCard_text3.getText().trim();
				if (adhaar3.length() < 7) {
					Common cm = new Common();
					gr_no_text.setText(cm.appendZero(adhaar3));
				} else 
				if(adhaar3.length() > 3) {
					adhaar3 = adhaar3.substring(0, 4);
					adhaarCard_text3.setText(adhaar3);
				}
			}
		});*/
		/*adhaarCard_text1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String value = adhaarCard_text1.getText().trim();
				if (value.length() == 4) {
					adhaarCard_text2.requestFocus();
				}
			}
		});
		adhaarCard_text2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String value = adhaarCard_text2.getText().trim();
				if (value.length() == 4) {
					adhaarCard_text3.requestFocus();
				}
			}
		});
		adhaarCard_text3.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String value = adhaarCard_text3.getText().trim();
				if (value.length() == 4) {
					submitButton.requestFocus();
				}
			}
		});*/
		
		// /////////////Reset//////////////
		if(classPageStatus.equalsIgnoreCase("View")){
			resetButtonName = "Cancel";
		}
		JButton resetButton = new JButton(resetButtonName);
		resetButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		resetButton.setBounds(mainCentre + 25, 620, 130, 25);
		bottombandPanel.add(resetButton);

		resetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				if(resetButtonName.equalsIgnoreCase("Reset")){
					/*if(sessionData.getSchoolName().equalsIgnoreCase("PR")){
						new AdmissionForm(sessionData, section, user_name, user_role);
					}
					else{*/
						new AdmissionFormNew(sessionData, section, user_name, user_role,"ADMISSION","", foundStudentList);
//					}
				}
				if(classPageStatus.equalsIgnoreCase("ADMISSION")){
					new Student(sessionData, section, user_name, user_role);
				}
				else if(resetButtonName.equalsIgnoreCase("Cancel")){
					new FindStudent(sessionData, "", presentStd_combo.getSelectedItem().toString(), presentDiv_combo.getSelectedItem().toString(), "", "", "", foundStudentList, section, user_name, user_role, false);
				}
			}
		});
		
		// /////////////Delete//////////////
		JButton deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		deleteButton.setBounds(mainCentre + 190, 620, 130, 25);
		if(sessionData.getUserRole().equalsIgnoreCase("ADMINISTRATOR") && classPageStatus.equalsIgnoreCase("View")){
			bottombandPanel.add(deleteButton);
		}
		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int reply = 0;
					reply = JOptionPane.showConfirmDialog(null, classGr+" will be deleted permanently?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
					if (dbValidate.connectDatabase(sessionData) && reply == JOptionPane.YES_OPTION) {
						dbValidate.deleteForm(sessionData, classGr, section, sessionData);
						commonObj.showMessageDialog("Data deleted for GR No.: "+classGr);
						foundStudentList.remove(classGr);
						frame.setVisible(false);
						new FindStudent(sessionData, "", "", "", "", "", "", foundStudentList, section, user_name, user_role, false);
					}
				} catch (Exception e1) {
					logger.info("Exception insertFormData ===>>>" + e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
				
			}
		});
		
		year_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int reply = 0;
					boolean flag = false;
					String presentStd = studentInfo[26];
					String demotedStd = commonObj.IntegerToRoman("a"+(commonObj.RomanToInteger(presentStd)-1));
					String selectedAcademicYear = year_combo.getSelectedItem().toString();
					int oldYear = Integer.parseInt(academicYearDB.substring(0,academicYearDB.indexOf("-")));
					int selectedYear = Integer.parseInt(selectedAcademicYear.substring(0,selectedAcademicYear.indexOf("-")));
					if(selectedYear < oldYear) {
						reply = JOptionPane.showConfirmDialog(null, "GR No.: "+classGr+" will be demoted to \n"
								+ "Std: "+demotedStd+" \nAcademic Year: "+selectedAcademicYear+"?", "Confirm Demote", JOptionPane.YES_NO_OPTION);
						if (dbValidate.connectDatabase(sessionData) && reply == JOptionPane.YES_OPTION) {
							String[] tableList = {"attendance", "attendance_period", "class_allotment", "marks_entry", "optional_allotment"
									, "optional_fee_allotment", "result_data", "statement_data", "student_subject"};
							for (String tableName: tableList) {           
								dbValidate.deleteRecordFromTable(sessionData, academicYearDB, classGr, tableName);
							}
							
							String updateStr = "ACADEMIC_YEAR='"+selectedAcademicYear+"',PRESENT_STD='"+demotedStd+"'";
							flag = dbValidate.updateRecordInTable(sessionData, academicYearDB, classGr, "hs_general_register", updateStr);
						}
						if(flag) {
							JOptionPane.showMessageDialog(null, "GR No. " + classGr + " demoted successfully to \n"
									+ "Std: "+demotedStd+" \nAcademic Year: "+selectedAcademicYear);
						}
						else {
							JOptionPane.showMessageDialog(null, "GR No. " + classGr + " demoted failed to \n"
									+ "Std: "+demotedStd+" \nAcademic Year: "+selectedAcademicYear);
						}
						frame.setVisible(false);
						new AdmissionFormNew(sessionData, section, user_name, user_role,"VIEW",classGr, foundStudentList);
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});
		
		labelStudentImg.addMouseListener(new MouseAdapter() {
 			public void mouseClicked(MouseEvent me) {
 				boolean retFlag = false, isUpdate = false, validate = true;
 				try {
 					String gr = gr_no_text.getText();
 					String name = lastName_text.getText().trim() +" "+ firstName_text.getText().trim() +" "+ fatherName_text.getText().trim() +" "+ motherName_text.getText().trim();
 					boolean checkGrNoFlag = dbValidate.validateGrNo(sessionData, gr, section, "student_imgs");
 					
 					if(classPageStatus.equalsIgnoreCase("Submit") && !checkGrNoFlag) {
 						isUpdate = false;
 						validate = false;
 						JOptionPane.showMessageDialog(null, "GR_NO " + gr + " already exists.");
 					}
 					else if(classPageStatus.equalsIgnoreCase("View")) {
 						validate = false;
 					}
 					else if(classPageStatus.equalsIgnoreCase("Edit") && !checkGrNoFlag) {
 						isUpdate = false;
 					}
 					else if(classPageStatus.equalsIgnoreCase("Edit") && checkGrNoFlag) {
 						isUpdate = true;
 					}
 					
 					if(!gr.equalsIgnoreCase("") && !gr.equalsIgnoreCase("0000000") && validate) {
 						JFileChooser fileChooser = new JFileChooser();
 						int returnValue = fileChooser.showOpenDialog(null);
 						if (returnValue == JFileChooser.APPROVE_OPTION) {
 							String imgPath = fileChooser.getSelectedFile().getAbsolutePath();
 							
 							int reply = 0;
 							reply = JOptionPane.showConfirmDialog(null, "Would You Like to upload "+imgPath+"?", "Confirm", JOptionPane.YES_NO_OPTION);
 							
 							if (reply == JOptionPane.YES_OPTION) {
 								try {
 									String sourcePath = imgPath;
 									String destinationPath = "";
 									String studentImagePath = commonObj.createFolder(commonObj.getDriveName() + "/" + sessionData.getSchoolName()
 									+ "_app/STUDENTIMAGE/" + sessionData.getAppType() + "/");
 									String fileName = gr+"_"+sessionData.getSectionName()+".jpg";
 									destinationPath = studentImagePath + fileName;
 									commonObj.copyFileUsingStream(sourcePath, destinationPath);
 									
// 									////code to store in database
// 									byte[] imgByte = commonObj.convertImage(imgPath, fileName);
// 									if (dbValidate.connectDatabase(sessionData)){
// 										retFlag = dbValidate.insertStudentImage(sessionData, name, gr, fileName, isUpdate);
// 									}
 									
 									/////to display in screen on selection
 									byte[] imgByte = commonObj.convertImage(destinationPath, fileName);
									labelStudentImg.setIcon(new ImageIcon(imgByte));
									
 								} catch (Exception e1) {
 									JOptionPane.showMessageDialog(null, "Error : "+ e1);
 								}
 							} else if (reply == JOptionPane.NO_OPTION) {
 								JOptionPane.showMessageDialog(null, "You have cancelled to upload.");
 							}
 						}
 					}
 					else if(gr.equalsIgnoreCase("") || gr.equalsIgnoreCase("0000000")) {
 						JOptionPane.showMessageDialog(null, "Please enter Gr No.");
 					}
 				} catch (Exception e1) {
 					commonObj.logException(e1);
 				}
 			}
 		});
	}
}
