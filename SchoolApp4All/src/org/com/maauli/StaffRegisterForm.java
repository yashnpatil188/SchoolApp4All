package org.com.maauli;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class StaffRegisterForm extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static String currentUser = "";

	static JFrame frame = null;

	static JComboBox studentcombo;

	static String std = "";

	static String div = "";

	static String section = "";

	static String secName = "";
	
    static String app_header = "";
    
    static String app_header_0 = "";

	static String img_path = "";

	static String img_home = "";

	static String img_logo = "";

	static String img_myaccount = "";

	static String img_logout = "";

	static String img_titleband = "";

	static String img_leftband = "";

	static String img_menuband = "";

	static String img_mainband = "";

	static String user_name = "";

	static String user_role = "";

	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static SessionData sessionData = new SessionData();

	static Logger logger = Logger.getLogger(StaffRegisterForm.class.getName());

	static Common commonObj = new Common();

	static int topGrNo = 0;

	static int nextGrNo = 0;
	
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

	static String nxtGrString = "";

	static DBValidate dbValidate = new DBValidate();

	static String line = "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

	static String lineSpaced = "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -";

	public StaffRegisterForm(SessionData sessionData1, String sec, String retUserName, String retUserRole) {

		System.gc();
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		section = sec;
		logger.info("section :: " + section);
		std = bundle.getString(section.toUpperCase() + "_STD");
		logger.info("std :: " + std);
		div = bundle.getString(section.toUpperCase() + "_DIV");
		logger.info("div :: " + div);
		secName = bundle.getString(section.toUpperCase() + "_SEC");
		logger.info("secName :: " + secName);
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

		setVisible(false);
		dispose();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame = new JFrame("Welcome to "+sessionData1.getAppName());
		Common commonObj = new Common();
		screenWidth = commonObj.screeWidth();
		screenHeight = commonObj.screeHeight();
		mainCentre = (screenWidth - 150) / 2;

		frame.setSize(screenWidth, screenHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		currentUser = commonObj.fileReader();
		logger.info("currentUser==>" + currentUser);

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

		JButton studentButton = new JButton("Student");
		studentButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		studentButton.setBounds(10, 100, 130, 35);
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
//		leftPanel.add(staffButton);

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
//		leftPanel.add(accountButton);

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

		// panel.add(leftPanel, BorderLayout.WEST);

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

		JLabel subMenuTitle = new JLabel("Staff");
		subMenuTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		subMenuTitle.setForeground(Color.orange);
		subMenuTitle.setBounds(20, 45, 100, 30);
		topbandPanel.add(subMenuTitle);

		JButton staffRegButton = new JButton("Registration");
		staffRegButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		staffRegButton.setBounds(130, 50, 150, 24);
		staffRegButton.setBackground(Color.GREEN);
		topbandPanel.add(staffRegButton);

		staffRegButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// frame.setVisible(false);
				// new StaffRegisterForm(section);
			}
		});

		JButton staffAttButton = new JButton("Attendance");
		staffAttButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		staffAttButton.setBounds(290, 50, 150, 24);// 620, 50, 150, 24
		topbandPanel.add(staffAttButton);

		staffAttButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

//				String userActive = "";
//				try {
//					if (dbValidate.connectDatabase(sessionData)) {
//						userActive = dbValidate.checkFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
//						dbValidate.closeDatabase(sessionData);
//
//						if (userActive.equalsIgnoreCase(user_name) || userActive.equalsIgnoreCase("")
//								|| userActive.equalsIgnoreCase(null)) {
							List findLCList = new ArrayList();
							new FindLeavingCert(sessionData, "", "", "", "", "", "", findLCList, false, "", "", "", "",
									"", "", section, "", user_name, user_role,"","");
							frame.setVisible(false);
//						} else if (!userActive.equalsIgnoreCase(user_name)) {
//							JOptionPane.showMessageDialog(null,
//									"User " + userActive + " already using form LEAVING CERTIFICATE");
//						}
//					}
//				} catch (Exception e1) {
//					logger.info("Exception insertFormData ===>>>" + e1);
//				} finally {
//					dbValidate.closeDatabase(sessionData);
//				}
			}
		});

		JButton leaveButton = new JButton("Leave Update");
		leaveButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		leaveButton.setBounds(450, 50, 150, 24);
		topbandPanel.add(leaveButton);

		leaveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// frame.setVisible(false);
				// new Student();
			}
		});

		JButton serviceButton = new JButton("Update Service");
		serviceButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		serviceButton.setBounds(610, 50, 150, 24); // 300, 50, 150, 24
		topbandPanel.add(serviceButton);

		serviceButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// new Student();
				// frame.setVisible(false);
			}
		});

		JButton letterButton = new JButton("Certificate");
		letterButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		letterButton.setBounds(770, 50, 150, 24);
		topbandPanel.add(letterButton);

		letterButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				LinkedHashMap<String,LinkedHashMap<String, String>> studMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
				new FindStudent(sessionData, "", "", "", "", "", "", studMap, section, user_name, user_role, false);
				frame.setVisible(false);
			}
		});

		mainPanel.add(topbandPanel, BorderLayout.NORTH);

		// //////////scrollArea panel/////////////////////////////////////
		JPanel scrollAreaPanel = new JPanel(new BorderLayout());
		scrollAreaPanel.setPreferredSize(new Dimension(screenWidth - 150, screenHeight - 187));
		// ///////////bottom band/////////////
		JPanel bottombandPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				Image img = new ImageIcon(img_path + img_mainband).getImage();
				Dimension size = new Dimension(screenWidth - 150, screenHeight - 150);
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				// g.drawImage(img, 0, 0, null);
				g.drawImage(img, 0, 0, screenWidth - 150, screenHeight - 150, null);
			}
		};
		bottombandPanel.setLayout(null);

		// /////////////Admission form//////////////////////
		// /////////////GR No.//////////////

		JLabel staff_id_label = new JLabel("Staff ID :");
		staff_id_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		staff_id_label.setBounds(30, 10, 70, 50);
		bottombandPanel.add(staff_id_label);

		try {
			topGrNo = Integer.parseInt(dbValidate.getTopData(sessionData, "GR_NO", "HS_GENERAL_REGISTER", section));
			nextGrNo = topGrNo + 1;
			logger.info("nextGrNo :: " + nextGrNo);
			nxtGrString = "" + nextGrNo;
			if (nxtGrString.length() < 6) {
				Common cm = new Common();
				nxtGrString = cm.appendZero(nxtGrString);
			}
			logger.info("nxtGrString :: " + nxtGrString);
		} catch (NumberFormatException ne2) {
			logger.info("Exception ne2::" + ne2);
		} catch (Exception e2) {
			logger.info("Exception e2::" + e2);
		}
		final JTextField staff_id_text = new JTextField(nxtGrString);
		staff_id_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		staff_id_text.setBounds(100, 22, 100, 25);
		staff_id_text.setEditable(false);
		bottombandPanel.add(staff_id_text);

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				staff_id_text.requestFocus();
			}
		});

		// staff_id_text.addKeyListener(new KeyAdapter(){
		// public void keyPressed(KeyEvent e){
		// String grString=staff_id_text.getText();
		// if(grString.length()>5){
		// grString = grString.substring(0,5);
		// logger.info("grString===>"+grString);
		// staff_id_text.setText(grString);
		// }
		// }
		// });
		//
		// staff_id_text.addFocusListener(new java.awt.event.FocusAdapter() {
		// public void focusLost(java.awt.event.FocusEvent event) {
		// String grString=staff_id_text.getText();
		// if(grString.length() < 6){
		// Common cm = new Common();
		// staff_id_text.setText(cm.appendZero(grString));
		// }
		// }
		// });
		// //////////////////////////////////
		// /////////////Type//////////////
		JLabel type_label = new JLabel("Type :");
		type_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		type_label.setBounds(250, 10, 70, 50);
		bottombandPanel.add(type_label);

		String typeList[] = { "Teaching", "Non-teaching" };
		final JComboBox type_combo = new JComboBox(typeList);
		type_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		type_combo.setBounds(300, 20, 150, 25);
		bottombandPanel.add(type_combo);
		// //////////////////////////////////
		// /////////////Staff Title//////////////
		JLabel title_label = new JLabel("Title : ");
		title_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		title_label.setBounds(480, 10, 70, 50);
		bottombandPanel.add(title_label);

		String titleList[] = { "Mr.", "Mrs.", "Ms." };
		final JComboBox title_combo = new JComboBox(titleList);
		title_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		title_combo.setBounds(530, 20, 80, 25);
		bottombandPanel.add(title_combo);
		// //////////////////////////////////
		// /////////////Gender//////////////
		JLabel gender_label = new JLabel("Gender :");
		gender_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		gender_label.setBounds(640, 10, 120, 50);
		bottombandPanel.add(gender_label);

		String genderList[] = { "Male", "Female" };
		final JComboBox gender_combo = new JComboBox(genderList);
		gender_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		gender_combo.setBounds(720, 20, 80, 25);
		bottombandPanel.add(gender_combo);
		// //////////////////////////////////
		// /////////////Last Name//////////////
		JLabel lastName_label = new JLabel("Last Name :");
		lastName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_label.setBounds(30, 50, 120, 50);
		bottombandPanel.add(lastName_label);

		final JTextField lastName_text = new JTextField();
		lastName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_text.setBounds(160, 60, 200, 25);
		bottombandPanel.add(lastName_text);
		// //////////////////////////////////
		// /////////////First Name//////////////
		JLabel firstName_label = new JLabel("First Name :");
		firstName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_label.setBounds(380, 50, 120, 50);
		bottombandPanel.add(firstName_label);

		final JTextField firstName_text = new JTextField();
		firstName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_text.setBounds(480, 60, 200, 25);
		bottombandPanel.add(firstName_text);
		// //////////////////////////////////
		// /////////////Father Name//////////////
		JLabel fatherName_label = new JLabel("Middle Name :");
		fatherName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_label.setBounds(710, 50, 120, 50);
		bottombandPanel.add(fatherName_label);

		final JTextField fatherName_text = new JTextField();
		fatherName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_text.setBounds(830, 60, 200, 25);
		bottombandPanel.add(fatherName_text);
		// //////////////////////////////////
		// /////////////Mother Name//////////////
		// JLabel motherName_label = new JLabel("Mother Name :");
		// motherName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// motherName_label.setBounds(30, 130, 120, 50);
		// bottombandPanel.add(motherName_label);
		//
		// final JTextField motherName_text = new JTextField();
		// motherName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// motherName_text.setBounds(160, 140, 200, 25);
		// bottombandPanel.add(motherName_text);
		// //////////////////////////////////
		// /////////////Permanent Address//////////////
		JLabel permanentAdd_label = new JLabel("Permanent Add :");
		permanentAdd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		permanentAdd_label.setBounds(30, 100, 130, 50);
		bottombandPanel.add(permanentAdd_label);

		final JTextField permanentAdd_text = new JTextField();
		permanentAdd_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		permanentAdd_text.setBounds(160, 110, 370, 25);
		bottombandPanel.add(permanentAdd_text);
		// //////////////////////////////////
		// /////////////Residential Address//////////////
		JLabel residentAdd_label = new JLabel("Resident Add :");
		residentAdd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		residentAdd_label.setBounds(550, 100, 130, 50);
		bottombandPanel.add(residentAdd_label);

		final JTextField residentAdd_text = new JTextField();
		residentAdd_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		residentAdd_text.setBounds(665, 110, 370, 25);
		bottombandPanel.add(residentAdd_text);
		// //////////////////////////////////
		// /////////////Date of birth//////////////
		JLabel dob_label = new JLabel("Date of Birth :");
		dob_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dob_label.setBounds(30, 150, 120, 50);
		bottombandPanel.add(dob_label);

		final JTextField dobDD_text = new JTextField();
		dobDD_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dobDD_text.setBounds(160, 160, 30, 25);
		bottombandPanel.add(dobDD_text);

		JLabel slashDD_label = new JLabel("/");
		slashDD_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		slashDD_label.setBounds(192, 150, 10, 50);
		bottombandPanel.add(slashDD_label);

		final JTextField dobMM_text = new JTextField();
		dobMM_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dobMM_text.setBounds(200, 160, 30, 25);
		bottombandPanel.add(dobMM_text);

		JLabel slashMM_label = new JLabel("/");
		slashMM_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		slashMM_label.setBounds(232, 150, 10, 50);
		bottombandPanel.add(slashMM_label);

		final JTextField dobYYYY_text = new JTextField();
		dobYYYY_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dobYYYY_text.setBounds(240, 160, 60, 25);
		bottombandPanel.add(dobYYYY_text);

		JLabel dobFormat_label = new JLabel("(DD/MM/YYYY)");
		dobFormat_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dobFormat_label.setBounds(310, 150, 150, 50);
		bottombandPanel.add(dobFormat_label);
		// //////////////////////////////////
		// /////////////Contact 1//////////////
		JLabel contact1_label = new JLabel("Contact 1 :");
		contact1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		contact1_label.setBounds(450, 150, 120, 50);
		bottombandPanel.add(contact1_label);

		final JTextField contact1_text = new JTextField();
		contact1_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		contact1_text.setBounds(540, 160, 90, 25);
		bottombandPanel.add(contact1_text);
		// //////////////////////////////////
		// /////////////Contact 2//////////////
		JLabel contact2_label = new JLabel("Contact 2 :");
		contact2_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		contact2_label.setBounds(640, 150, 120, 50);
		bottombandPanel.add(contact2_label);

		final JTextField contact2_text = new JTextField();
		contact2_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		contact2_text.setBounds(730, 160, 90, 25);
		bottombandPanel.add(contact2_text);
		// //////////////////////////////////
		// /////////////Email//////////////
		JLabel email_label = new JLabel("Email Id :");
		email_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		email_label.setBounds(830, 150, 130, 50);
		bottombandPanel.add(email_label);

		final JTextField email_text = new JTextField();
		email_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		email_text.setBounds(910, 160, 160, 25);
		bottombandPanel.add(email_text);
		// /////////////Category//////////////
		JLabel category_label = new JLabel("Category :");
		category_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		category_label.setBounds(30, 200, 120, 50);
		bottombandPanel.add(category_label);

		String categoryList[] = { "SC", "ST", "NT A", "NT B", "NT C", "NT D", "SBC", "OBC", "OPEN" };
		final JComboBox category_combo = new JComboBox(categoryList);
		category_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		category_combo.setBounds(160, 210, 130, 25);
		bottombandPanel.add(category_combo);
		// //////////////////////////////////
		// /////////////Cast//////////////
		JLabel cast_label = new JLabel("Cast :");
		cast_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		cast_label.setBounds(310, 200, 120, 50);
		bottombandPanel.add(cast_label);

		final JTextField cast_text = new JTextField();
		cast_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		cast_text.setBounds(380, 210, 130, 25);
		bottombandPanel.add(cast_text);
		// //////////////////////////////////
		// /////////////Adhaar//////////////
		JLabel adhaar_label = new JLabel("Aadhaar No. :");
		adhaar_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		adhaar_label.setBounds(550, 200, 130, 50);
		bottombandPanel.add(adhaar_label);

		final JTextField aadhaar_text = new JTextField();
		aadhaar_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		aadhaar_text.setBounds(660, 210, 120, 25);
		bottombandPanel.add(aadhaar_text);
		// //////////////////////////////////
		// /////////////PAN//////////////
		JLabel pan_label = new JLabel("PAN No. :");
		pan_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		pan_label.setBounds(810, 200, 130, 50);
		bottombandPanel.add(pan_label);

		final JTextField pan_text = new JTextField();
		pan_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		pan_text.setBounds(900, 210, 120, 25);
		bottombandPanel.add(pan_text);

		// /////////////line0_label//////////////
		JLabel line0_label = new JLabel(line);
		line0_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		line0_label.setBounds(30, 225, 1100, 50);
		bottombandPanel.add(line0_label);
		// /////////////Date of Joining//////////////
		JLabel doj_label = new JLabel("Date of Joining :");
		doj_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		doj_label.setBounds(30, 250, 150, 50);
		bottombandPanel.add(doj_label);

		final JTextField dojDD_text = new JTextField();
		dojDD_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dojDD_text.setBounds(160, 260, 30, 25);
		bottombandPanel.add(dojDD_text);

		JLabel slashDdoj_label = new JLabel("/");
		slashDdoj_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		slashDdoj_label.setBounds(192, 250, 10, 50);
		bottombandPanel.add(slashDdoj_label);

		final JTextField dojMM_text = new JTextField();
		dojMM_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dojMM_text.setBounds(200, 260, 30, 25);
		bottombandPanel.add(dojMM_text);

		JLabel slashMdoj_label = new JLabel("/");
		slashMdoj_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		slashMdoj_label.setBounds(232, 250, 10, 50);
		bottombandPanel.add(slashMdoj_label);

		final JTextField dojYYYY_text = new JTextField();
		dojYYYY_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dojYYYY_text.setBounds(240, 260, 60, 25);
		bottombandPanel.add(dojYYYY_text);

		JLabel dojformat_label = new JLabel("(DD/MM/YYYY)");
		dojformat_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		dojformat_label.setBounds(310, 250, 130, 50);
		bottombandPanel.add(dojformat_label);

		// /////////////Date of Admission//////////////
		JLabel doa_label = new JLabel("Appointment Date :");
		doa_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		doa_label.setBounds(470, 250, 150, 50);
		bottombandPanel.add(doa_label);

		final JTextField doaDD_text = new JTextField();
		doaDD_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		doaDD_text.setBounds(620, 260, 30, 25);
		bottombandPanel.add(doaDD_text);

		JLabel slashDDoa_label = new JLabel("/");
		slashDDoa_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		slashDDoa_label.setBounds(652, 250, 10, 50);
		bottombandPanel.add(slashDDoa_label);

		final JTextField doaMM_text = new JTextField();
		doaMM_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		doaMM_text.setBounds(660, 260, 30, 25);
		bottombandPanel.add(doaMM_text);

		JLabel slashMDoa_label = new JLabel("/");
		slashMDoa_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		slashMDoa_label.setBounds(692, 250, 10, 50);
		bottombandPanel.add(slashMDoa_label);

		final JTextField doaYYYY_text = new JTextField();
		doaYYYY_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		doaYYYY_text.setBounds(700, 260, 60, 25);
		bottombandPanel.add(doaYYYY_text);

		JLabel doaformat_label = new JLabel("(DD/MM/YYYY)");
		doaformat_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		doaformat_label.setBounds(770, 250, 130, 50);
		bottombandPanel.add(doaformat_label);
		// /////////////Designation//////////////
		JLabel desig_label = new JLabel("Designation :");
		desig_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		desig_label.setBounds(30, 300, 130, 50);
		bottombandPanel.add(desig_label);

		final JTextField desig_text = new JTextField();
		desig_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		desig_text.setBounds(160, 310, 170, 25);
		bottombandPanel.add(desig_text);
		// //////////////////////////////////
		// /////////////Staff category//////////////
		JLabel staffCat_label = new JLabel("Staff Category :");
		staffCat_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		staffCat_label.setBounds(350, 300, 150, 50);
		bottombandPanel.add(staffCat_label);

		String staffCatList[] = { "Regular", "Contract", "Part time" };
		final JComboBox staffCat_combo = new JComboBox(staffCatList);
		staffCat_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		staffCat_combo.setBounds(480, 310, 130, 25);
		bottombandPanel.add(staffCat_combo);
		// //////////////////////////////////
		// /////////////Appt. type//////////////
		JLabel apptType_label = new JLabel("Appointment Type :");
		apptType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		apptType_label.setBounds(650, 300, 150, 50);
		bottombandPanel.add(apptType_label);

		String apptTypeList[] = { "Temporary", "Permanent" };
		final JComboBox apptType_combo = new JComboBox(apptTypeList);
		apptType_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		apptType_combo.setBounds(810, 310, 150, 25);
		bottombandPanel.add(apptType_combo);
		// //////////////////////////////////

		// ///////////////sepacad1_label//////////////
		// JLabel sepAcadBox_label = new JLabel("|");
		// sepAcadBox_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// sepAcadBox_label.setBounds(30, 370, 130, 50);
		// bottombandPanel.add(sepAcadBox_label);
		//
		// ///////////////sepAcadBox1_label//////////////
		// JLabel sepAcadBox1_label = new JLabel("|");
		// sepAcadBox1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// sepAcadBox1_label.setBounds(200, 370, 130, 50);
		// bottombandPanel.add(sepAcadBox1_label);
		//
		// ///////////////line4_label//////////////
		// JLabel line4_label = new JLabel("---------------------------------");
		// line4_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// line4_label.setBounds(35, 355, 400, 50);
		// bottombandPanel.add(line4_label);

		JLabel line2_label = new JLabel("ACADEMIC");
		line2_label.setFont(new Font("Book Antiqua", Font.PLAIN, 21));
		line2_label.setBounds(45, 365, 400, 50);
		line2_label.setForeground(Color.BLUE);
		bottombandPanel.add(line2_label);
		// ///////////////Academic//////////////
		// JLabel acad_label = new JLabel("Academic :");
		// acad_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// acad_label.setBounds(30, 350, 130, 50);
		// bottombandPanel.add(acad_label);

		// /////////////line3_label//////////////
		JLabel line3_label = new JLabel(line);
		line3_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		line3_label.setBounds(30, 385, 1100, 50);
		bottombandPanel.add(line3_label);
		// /////////////Qualification//////////////
		JLabel qual_label = new JLabel("Qualification");
		qual_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		qual_label.setBounds(45, 400, 200, 50);
		bottombandPanel.add(qual_label);

		// /////////////sep1_label//////////////
		JLabel sep1_label = new JLabel("|");
		sep1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sep1_label.setBounds(180, 400, 130, 50);
		bottombandPanel.add(sep1_label);

		// /////////////Subject//////////////
		JLabel sub_label = new JLabel("Subject");
		sub_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sub_label.setBounds(240, 400, 130, 50);
		bottombandPanel.add(sub_label);

		// /////////////sep2_label//////////////
		JLabel sep2_label = new JLabel("|");
		sep2_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sep2_label.setBounds(380, 400, 130, 50);
		bottombandPanel.add(sep2_label);

		// /////////////Board / University//////////////
		JLabel board_label = new JLabel("Board / University");
		board_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		board_label.setBounds(510, 400, 200, 50);
		bottombandPanel.add(board_label);

		// /////////////sep3_label//////////////
		JLabel sep3_label = new JLabel("|");
		sep3_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sep3_label.setBounds(750, 400, 130, 50);
		bottombandPanel.add(sep3_label);

		// /////////////Year//////////////
		JLabel year_label = new JLabel("Year");
		year_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		year_label.setBounds(790, 400, 70, 50);
		bottombandPanel.add(year_label);

		// /////////////sep4_label//////////////
		JLabel sep4_label = new JLabel("|");
		sep4_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sep4_label.setBounds(860, 400, 130, 50);
		bottombandPanel.add(sep4_label);

		// /////////////Class//////////////
		JLabel class_label = new JLabel("Class");
		class_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		class_label.setBounds(900, 400, 70, 50);
		bottombandPanel.add(class_label);

		// /////////////sep5_label//////////////
		JLabel sep5_label = new JLabel("|");
		sep5_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sep5_label.setBounds(970, 400, 130, 50);
		bottombandPanel.add(sep5_label);

		// /////////////Distinction//////////////
		JLabel distinct_label = new JLabel("Distinction");
		distinct_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		distinct_label.setBounds(990, 400, 100, 50);
		bottombandPanel.add(distinct_label);

		// /////////////sep6_label//////////////
		JLabel sep6_label = new JLabel("|");
		sep6_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sep6_label.setBounds(1080, 400, 130, 50);
		bottombandPanel.add(sep6_label);

		// /////////////line1_label//////////////
		// JLabel line1_label = new JLabel(line);
		// line1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// line1_label.setBounds(30, 413, 1100, 50);
		// bottombandPanel.add(line1_label);

		// //////acad
		// 1//////////////////////////////////////////////////////////////////
		String acadQual1List[] = { "Regular", "Contract", "Part time" };
		final JComboBox acadQual1_combo = new JComboBox(acadQual1List);
		acadQual1_combo.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		acadQual1_combo.setBounds(40, 443, 130, 25);
		bottombandPanel.add(acadQual1_combo);

		String acadQual2List[] = { "Regular", "Contract", "Part time" };
		final JComboBox acadQual2_combo = new JComboBox(acadQual2List);
		acadQual2_combo.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		acadQual2_combo.setBounds(40, 473, 130, 25);
		bottombandPanel.add(acadQual2_combo);

		String acadQual3List[] = { "Regular", "Contract", "Part time" };
		final JComboBox acadQual3_combo = new JComboBox(acadQual3List);
		acadQual3_combo.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		acadQual3_combo.setBounds(40, 503, 130, 25);
		bottombandPanel.add(acadQual3_combo);

		String acadQual4List[] = { "Regular", "Contract", "Part time" };
		final JComboBox acadQual4_combo = new JComboBox(acadQual4List);
		acadQual4_combo.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		acadQual4_combo.setBounds(40, 533, 130, 25);
		bottombandPanel.add(acadQual4_combo);

		String acadQual5List[] = { "Regular", "Contract", "Part time" };
		final JComboBox acadQual5_combo = new JComboBox(acadQual5List);
		acadQual5_combo.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		acadQual5_combo.setBounds(40, 563, 130, 25);
		bottombandPanel.add(acadQual5_combo);

		String acadQual6List[] = { "Regular", "Contract", "Part time" };
		final JComboBox acadQual6_combo = new JComboBox(acadQual6List);
		acadQual6_combo.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		acadQual6_combo.setBounds(40, 593, 130, 25);
		bottombandPanel.add(acadQual6_combo);

		// ///////////////////////////
		JLabel sepacad1_label = new JLabel("|");
		sepacad1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sepacad1_label.setBounds(180, 430, 130, 50);
		bottombandPanel.add(sepacad1_label);

		JLabel sepacad2_label = new JLabel("|");
		sepacad2_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sepacad2_label.setBounds(180, 460, 130, 50);
		bottombandPanel.add(sepacad2_label);

		JLabel sepacad3_label = new JLabel("|");
		sepacad3_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sepacad3_label.setBounds(180, 490, 130, 50);
		bottombandPanel.add(sepacad3_label);

		JLabel sepacad4_label = new JLabel("|");
		sepacad4_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sepacad4_label.setBounds(180, 520, 130, 50);
		bottombandPanel.add(sepacad4_label);

		JLabel sepacad5_label = new JLabel("|");
		sepacad5_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sepacad5_label.setBounds(180, 550, 130, 50);
		bottombandPanel.add(sepacad5_label);

		JLabel sepacad6_label = new JLabel("|");
		sepacad6_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sepacad6_label.setBounds(180, 580, 130, 50);
		bottombandPanel.add(sepacad6_label);

		// /////////////Subject//////////////
		JTextField acadSub1_text = new JTextField();
		acadSub1_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		acadSub1_text.setBounds(195, 443, 180, 25);
		bottombandPanel.add(acadSub1_text);

		JTextField acadSub2_text = new JTextField();
		acadSub2_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		acadSub2_text.setBounds(195, 473, 180, 25);
		bottombandPanel.add(acadSub2_text);

		JTextField acadSub3_text = new JTextField();
		acadSub3_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		acadSub3_text.setBounds(195, 503, 180, 25);
		bottombandPanel.add(acadSub3_text);

		JTextField acadSub4_text = new JTextField();
		acadSub4_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		acadSub4_text.setBounds(195, 533, 180, 25);
		bottombandPanel.add(acadSub4_text);

		JTextField acadSub5_text = new JTextField();
		acadSub5_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		acadSub5_text.setBounds(195, 563, 180, 25);
		bottombandPanel.add(acadSub5_text);

		JTextField acadSub6_text = new JTextField();
		acadSub6_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		acadSub6_text.setBounds(195, 593, 180, 25);
		bottombandPanel.add(acadSub6_text);

		// /////////////sepacad2_label//////////////
		JLabel sepacad7_label = new JLabel("|");
		sepacad7_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sepacad7_label.setBounds(380, 430, 130, 50);
		bottombandPanel.add(sepacad7_label);

		// /////////////Board / University//////////////
		JTextField acadBoard1_text = new JTextField();
		acadBoard1_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		acadBoard1_text.setBounds(395, 443, 350, 25);
		bottombandPanel.add(acadBoard1_text);

		// /////////////sepacad3_label//////////////
		JLabel sepacad8_label = new JLabel("|");
		sepacad8_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sepacad8_label.setBounds(750, 430, 130, 50);
		bottombandPanel.add(sepacad8_label);

		// /////////////Year//////////////
		JTextField acadYear1_text = new JTextField();
		acadYear1_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		acadYear1_text.setBounds(770, 443, 80, 25);
		bottombandPanel.add(acadYear1_text);

		// /////////////sepacad4_label//////////////
		JLabel sepaca9_label = new JLabel("|");
		sepaca9_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sepaca9_label.setBounds(860, 430, 130, 50);
		bottombandPanel.add(sepaca9_label);

		// /////////////Class//////////////
		JTextField acadClass1_text = new JTextField();
		acadClass1_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		acadClass1_text.setBounds(880, 443, 80, 25);
		bottombandPanel.add(acadClass1_text);

		// /////////////sepacad5_label//////////////
		JLabel sepacad10_label = new JLabel("|");
		sepacad10_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sepacad10_label.setBounds(970, 430, 130, 50);
		bottombandPanel.add(sepacad10_label);

		// /////////////Distinction//////////////
		JTextField acadDist1_text = new JTextField();
		acadDist1_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		acadDist1_text.setBounds(985, 443, 90, 25);
		bottombandPanel.add(acadDist1_text);

		// /////////////sepacad6_label//////////////
		JLabel sepacad11_label = new JLabel("|");
		sepacad11_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		sepacad11_label.setBounds(1080, 430, 130, 50);
		bottombandPanel.add(sepacad11_label);

		// ///////////Birth Place//////////////
		// JLabel birthPlace_label = new JLabel("Birth Place :");
		// birthPlace_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// birthPlace_label.setBounds(730, 280, 120, 50);
		// bottombandPanel.add(birthPlace_label);
		//
		// final JTextField birthPlace_text = new JTextField();
		// birthPlace_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// birthPlace_text.setBounds(830, 290, 200, 25);
		// bottombandPanel.add(birthPlace_text);
		// //////////////////////////////////
		// /////////////Nationality//////////////
		// JLabel nationality_label = new JLabel("Nationality :");
		// nationality_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// nationality_label.setBounds(30, 330, 120, 50);
		// bottombandPanel.add(nationality_label);
		//
		// final JTextField nationality_text = new JTextField();
		// nationality_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// nationality_text.setBounds(160, 340, 200, 25);
		// bottombandPanel.add(nationality_text);
		// //////////////////////////////////
		// /////////////Religion//////////////
		// JLabel religion_label = new JLabel("Religion :");
		// religion_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// religion_label.setBounds(380, 330, 120, 50);
		// bottombandPanel.add(religion_label);
		//
		// String religionList[] =
		// {"Hindu","Muslim","Christian","Sikh","Jain","Parsi","Buddha","NavBuddha"};
		// final JComboBox religion_combo = new JComboBox(religionList);
		// religion_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// religion_combo.setBounds(480, 340, 200, 25);
		// bottombandPanel.add(religion_combo);
		// //////////////////////////////////
		// /////////////Mother Tongue//////////////
		// JLabel motherTongue_label = new JLabel("Mother Tongue :");
		// motherTongue_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// motherTongue_label.setBounds(380, 380, 140, 50);
		// bottombandPanel.add(motherTongue_label);
		//
		// final JTextField motherTongue_text = new JTextField();
		// motherTongue_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// motherTongue_text.setBounds(520, 390, 150, 25);
		// bottombandPanel.add(motherTongue_text);
		// //////////////////////////////////

		/*
		 * final JTextField doa_text = new JTextField(); doa_text.setFont(new
		 * Font("Book Antiqua", Font.BOLD, 16)); doa_text.setBounds(830, 390,
		 * 120, 25); bottombandPanel.add(doa_text);
		 */
		// //////////////////////////////////
		// /////////////Last School//////////////
		// JLabel lastSchool_label = new JLabel("Last School :");
		// lastSchool_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// lastSchool_label.setBounds(30, 430, 120, 50);
		// bottombandPanel.add(lastSchool_label);
		//
		// final JTextField lastSchool_text = new JTextField();
		// lastSchool_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// lastSchool_text.setBounds(160, 440, 200, 25);
		// bottombandPanel.add(lastSchool_text);
		// //////////////////////////////////
		// /////////////Admitted Std//////////////
		// JLabel admittedStd_label = new JLabel("Admitted Std :");
		// admittedStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// admittedStd_label.setBounds(380, 430, 120, 50);
		// bottombandPanel.add(admittedStd_label);
		//
		// String[] stdList = std.split(",");
		// // String admittedStdList[] = {"V","VI","VII","VIII","IX","X"};
		// final JComboBox admittedStd_combo = new JComboBox(stdList);
		// admittedStd_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// admittedStd_combo.setBounds(520, 440, 100, 25);
		// bottombandPanel.add(admittedStd_combo);
		// //////////////////////////////////
		// /////////////Admitted Div//////////////
		// JLabel admittedDiv_label = new JLabel("Admitted Div :");
		// admittedDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// admittedDiv_label.setBounds(30, 480, 120, 50);
		// bottombandPanel.add(admittedDiv_label);
		//
		// String[] divList = div.split(",");
		// // String admittedDivList[] = {"A","B","C","D"};
		// final JComboBox admittedDiv_combo = new JComboBox(divList);
		// admittedDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// admittedDiv_combo.setBounds(160, 490, 100, 25);
		// bottombandPanel.add(admittedDiv_combo);
		// //////////////////////////////////

		// /////////////Payment Status//////////////
		// JLabel paymentStatus_label = new JLabel("Paying / Free :");
		// paymentStatus_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// paymentStatus_label.setBounds(380, 480, 130, 50);
		// bottombandPanel.add(paymentStatus_label);
		//
		// String paymentStatusList[] = {"Paying","Free"};
		// final JComboBox paymentStatus_combo = new
		// JComboBox(paymentStatusList);
		// paymentStatus_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		// paymentStatus_combo.setBounds(520, 490, 100, 25);
		// bottombandPanel.add(paymentStatus_combo);
		// //////////////////////////////////
		// /////////////Submit//////////////
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(mainCentre - 150, 580, 130, 25);
		bottombandPanel.add(submitButton);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean validateFields = true;
				String staff_id = "";
				String lastName = "";
				String firstName = "";
				String fatherName = "";
				String motherName = "";
				String gender = "";
				String emailId = "";
				String permanentAdd = "";
				String residentAdd = "";
				String contact1 = "";
				String contact2 = "";
				String birthDate = "";
				String dobWords = "";
				String birthPlace = "";
				String nationality = "";
				String religion = "";
				String category = "";
				String cast = "";
				String motherTongue = "";
				String lastSchool = "";
				String admittedStd = "";
				String admittedDiv = "";
				String dateAdmitted = "";
				String paymentStatus = "";
				String userName = currentUser.trim();
				String adhaarCard = "";

				staff_id = staff_id_text.getText().trim();
				lastName = lastName_text.getText().trim();
				firstName = firstName_text.getText().trim();
				fatherName = fatherName_text.getText().trim();
				// motherName =motherName_text.getText().trim();
				gender = (String) gender_combo.getSelectedItem();
				emailId = email_text.getText().trim();
				permanentAdd = permanentAdd_text.getText().trim();
				residentAdd = residentAdd_text.getText().trim();
				contact1 = contact1_text.getText().trim();
				contact2 = contact2_text.getText().trim();
				birthDate = dobDD_text.getText().trim() + "/" + dobMM_text.getText().trim() + "/"
						+ dobYYYY_text.getText().trim();
				// dobWords =dobWords_text.getText().trim();
				// birthPlace =birthPlace_text.getText().trim();
				// nationality =nationality_text.getText().trim();
				// religion =(String) religion_combo.getSelectedItem();
				category = (String) category_combo.getSelectedItem();
				cast = cast_text.getText().trim();
				// motherTongue =motherTongue_text.getText().trim();
				// lastSchool =lastSchool_text.getText().trim();
				// admittedStd =(String) admittedStd_combo.getSelectedItem();
				// admittedDiv =(String) admittedDiv_combo.getSelectedItem();
				dateAdmitted = doaDD_text.getText().trim() + "/" + doaMM_text.getText().trim() + "/"
						+ doaYYYY_text.getText().trim();
				// paymentStatus =(String)
				// paymentStatus_combo.getSelectedItem();

				logger.info("staff_id.:" + staff_id);
				logger.info("lastName.:" + lastName);
				logger.info("firstName:" + firstName);
				logger.info("fatherName:" + fatherName);
				logger.info("motherName:" + motherName);
				logger.info("gender:" + gender);
				logger.info("emailId:" + emailId);
				logger.info("permanentAdd:" + permanentAdd);
				logger.info("residentAdd:" + residentAdd);
				logger.info("contact1:" + contact1);
				logger.info("contact2:" + contact2);
				logger.info("birthDate:" + birthDate);
				logger.info("dobWords:" + dobWords);
				logger.info("birthPlace:" + birthPlace);
				logger.info("nationality:" + nationality);
				logger.info("religion:" + religion);
				logger.info("category:" + category);
				logger.info("cast:" + cast);
				logger.info("motherTongue:" + motherTongue);
				logger.info("lastSchool:" + lastSchool);
				logger.info("admittedStd:" + admittedStd);
				logger.info("admittedDiv:" + admittedDiv);
				logger.info("dateAdmitted:" + dateAdmitted);
				logger.info("paymentStatus:" + paymentStatus);
				logger.info("userName:" + userName);

				boolean checkGrNoFlag = false;
				try {
					try {
						if (dbValidate.connectDatabase(sessionData)) {
							checkGrNoFlag = dbValidate.validateGrNo(sessionData, staff_id, section, "HS_GENERAL_REGISTER");
						}
					} catch (Exception e1) {
						logger.info("Exception ===>>>" + e1);
					} finally {
						dbValidate.closeDatabase(sessionData);
					}
					logger.info("checkGrNoFlag==>" + checkGrNoFlag);
					if (checkGrNoFlag)
						JOptionPane.showMessageDialog(null, "staff_id " + staff_id + " already exists");
				} catch (Exception e1) {
					logger.info("Exception GR_NO ===>>>" + e1);
				}

				if (!checkGrNoFlag) {
					if (staff_id.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter staff_id");
					} else if (commonObj.checkComma(staff_id)) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "staff_id cannot contain |-:';,");
					} else if (lastName.length() > 50) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, commonObj.charExceeded("LastName", lastName, 50));
					} else if (commonObj.checkComma(lastName) || lastName.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid LastName");
					} else if (firstName.length() > 50) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, commonObj.charExceeded("FirstName", firstName, 50));
					} else if (commonObj.checkComma(firstName) || firstName.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid FirstName");
					} else if (fatherName.length() > 50) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, commonObj.charExceeded("FatherName", fatherName, 50));
					} else if (commonObj.checkComma(fatherName) || fatherName.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid FatherName");
					} else if (motherName.length() > 50) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, commonObj.charExceeded("MotherName", motherName, 50));
					} else if (commonObj.checkComma(motherName) || motherName.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid MotherName");
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
					} else if (commonObj.checkComma(permanentAdd) || permanentAdd.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid Permanent Add");
					} else if (residentAdd.length() > 200) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, commonObj.charExceeded("Resident Add", residentAdd, 200));
					} else if (commonObj.checkComma(residentAdd) || residentAdd.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid Resident Add");
					} else if (!contact1.equalsIgnoreCase("") && (!commonObj.validateNumber(contact1) || contact1.length() != 10)) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid Mobile No.");
					} else if (contact1.length() > 20) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, commonObj.charExceeded("Contact 1", contact1, 20));
					} else if (!commonObj.validateNumber(contact2) && !contact2.equalsIgnoreCase("")) {
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
						JOptionPane.showMessageDialog(null, "birthPlace cannot contain |-:';,");
					} else if (nationality.length() > 20) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, commonObj.charExceeded("Nationality", nationality, 20));
					} else if (commonObj.checkComma(nationality) || nationality.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid Nationality");
					} else if (cast.length() > 20) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, commonObj.charExceeded("Cast", cast, 20));
					} else if (commonObj.checkComma(cast) || cast.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid Cast");
					} else if (motherTongue.length() > 20) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, commonObj.charExceeded("Mother Tongue", motherTongue, 20));
					} else if (commonObj.checkComma(motherTongue) || motherTongue.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid MotherTongue");
					} else if (lastSchool.length() > 100) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, commonObj.charExceeded("LastSchool", lastSchool, 100));
					} else if (commonObj.checkComma(lastSchool)) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "lastSchool cannot contain |-:';,");
					} else if (!commonObj.validateDate(dateAdmitted) || dateAdmitted.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter valid Date of Admission");
					}

					Boolean flagValue = false;
					if (validateFields) {
						try {
							/*flagValue = dbValidate.admitForm(sessionData, staff_id, lastName, firstName, fatherName, motherName,
									gender, emailId, permanentAdd, residentAdd, contact1, contact2, birthDate, dobWords,
									birthPlace, nationality, religion, category, cast, motherTongue, lastSchool,
									admittedStd, admittedDiv, dateAdmitted, paymentStatus, userName, section, adhaarCard,"","","","","","","");*/
							logger.info("flagValue==>" + flagValue);
							if (flagValue) {
								frame.setVisible(false);
								if(sessionData.getSchoolName().equalsIgnoreCase("PR")){
//									new AdmissionForm(sessionData, section, user_name, user_role);
								}
								else{
									LinkedHashMap<String,LinkedHashMap<String, String>> studentList = new LinkedHashMap<String,LinkedHashMap<String, String>>();
									new AdmissionFormNew(sessionData, section, user_name, user_role,"ADMISSION","",studentList);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Data not inserted Sucessfully");
							}
						} catch (Exception e1) {
							logger.info("Exception Admission form ===>>>" + e1);
							new Student(sessionData, section, user_name, user_role);
							frame.setVisible(false);
						}
					}
				}
			}

		});

		// /////////////////////////////
		JScrollPane jsp;
		// dataPanel.setBackground(Color.green);

		jsp = new JScrollPane(bottombandPanel);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollAreaPanel.add(jsp, BorderLayout.WEST);
		// bottombandPanel.add(scrollAreaPanel, BorderLayout.SOUTH);
		// mainPanel.add(bottombandPanel, BorderLayout.SOUTH);
		// ////////////////////////////////
		// panelHome.add(mainPanel, BorderLayout.EAST);
		// panelHome.setSize(screenWidth, screenHeight);

		mainPanel.add(scrollAreaPanel, BorderLayout.SOUTH);

		panel.add(mainPanel, BorderLayout.EAST);
		panel.add(leftPanel, BorderLayout.WEST);

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
		// motherName_text.addFocusListener(new java.awt.event.FocusAdapter() {
		// public void focusGained(java.awt.event.FocusEvent event) {
		// motherName_text.selectAll();
		// }
		// });
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
		contact1_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				contact1_text.selectAll();
			}
		});
		contact2_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				contact2_text.selectAll();
			}
		});
		dobDD_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				dobDD_text.selectAll();
			}
		});
		dobMM_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				dobMM_text.selectAll();
			}
		});
		dobYYYY_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				dobYYYY_text.selectAll();
			}
		});
		// birthPlace_text.addFocusListener(new java.awt.event.FocusAdapter() {
		// public void focusGained(java.awt.event.FocusEvent event) {
		// birthPlace_text.selectAll();
		// }
		// });
		// nationality_text.addFocusListener(new java.awt.event.FocusAdapter() {
		// public void focusGained(java.awt.event.FocusEvent event) {
		// nationality_text.selectAll();
		// }
		// });
		cast_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				cast_text.selectAll();
			}
		});
		// motherTongue_text.addFocusListener(new java.awt.event.FocusAdapter()
		// {
		// public void focusGained(java.awt.event.FocusEvent event) {
		// motherTongue_text.selectAll();
		// }
		// });
		doaDD_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				doaDD_text.selectAll();
			}
		});
		doaMM_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				doaMM_text.selectAll();
			}
		});
		doaYYYY_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent event) {

				doaYYYY_text.selectAll();
			}
		});
		// lastSchool_text.addFocusListener(new java.awt.event.FocusAdapter() {
		// public void focusGained(java.awt.event.FocusEvent event) {
		// lastSchool_text.selectAll();
		// }
		// });

		/*
		 * dobDD_text.addKeyListener(new KeyAdapter(){ public void
		 * keyPressed(KeyEvent e){ String value=dobDD_text.getText();
		 * if(value.length()==1){ dobMM_text.requestFocus(); } } });
		 * dobMM_text.addKeyListener(new KeyAdapter(){ public void
		 * keyPressed(KeyEvent e){ String value=dobMM_text.getText();
		 * if(value.length()==1){ dobYYYY_text.requestFocus(); } } });
		 * dobYYYY_text.addKeyListener(new KeyAdapter(){ public void
		 * keyPressed(KeyEvent e){ String value=dobYYYY_text.getText();
		 * if(value.length()==3){ dobWords_text.requestFocus(); } } });
		 */
		dobDD_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusLost(java.awt.event.FocusEvent event) {

				Common date2word = new Common();
				int day = 0;
				int month = 0;
				int year = 0;
				if (!dobDD_text.getText().equalsIgnoreCase(""))
					day = Integer.parseInt(dobDD_text.getText());
				if (!dobMM_text.getText().equalsIgnoreCase(""))
					month = Integer.parseInt(dobMM_text.getText());
				if (!dobYYYY_text.getText().equalsIgnoreCase(""))
					year = Integer.parseInt(dobYYYY_text.getText());
				// if(day != 0 && month != 0 && year != 0){
				// dobWords_text.setText(date2word.dateToWords(day, month,
				// year));
				// //dobMM_text.requestFocus();
				// }
			}
		});

		dobMM_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusLost(java.awt.event.FocusEvent event) {

				Common date2word = new Common();
				int day = 0;
				int month = 0;
				int year = 0;
				if (!dobDD_text.getText().equalsIgnoreCase(""))
					day = Integer.parseInt(dobDD_text.getText());
				if (!dobMM_text.getText().equalsIgnoreCase(""))
					month = Integer.parseInt(dobMM_text.getText());
				if (!dobYYYY_text.getText().equalsIgnoreCase(""))
					year = Integer.parseInt(dobYYYY_text.getText());
				// if(day != 0 && month != 0 && year != 0){
				// dobWords_text.setText(date2word.dateToWords(day, month,
				// year));
				// //dobYYYY_text.requestFocus();
				// }
			}
		});

		dobYYYY_text.addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusLost(java.awt.event.FocusEvent event) {

				Common date2word = new Common();
				int day = 0;
				int month = 0;
				int year = 0;
				if (!dobDD_text.getText().equalsIgnoreCase(""))
					day = Integer.parseInt(dobDD_text.getText());
				if (!dobMM_text.getText().equalsIgnoreCase(""))
					month = Integer.parseInt(dobMM_text.getText());
				if (!dobYYYY_text.getText().equalsIgnoreCase(""))
					year = Integer.parseInt(dobYYYY_text.getText());
				// if(day != 0 && month != 0 && year != 0){
				// dobWords_text.setText(date2word.dateToWords(day, month,
				// year));
				// //birthPlace_text.requestFocus();
				// }
			}
		});
		/*
		 * dobWords_text.addFocusListener(new java.awt.event.FocusAdapter() {
		 * public void focusGained(java.awt.event.FocusEvent event) { Common
		 * date2word = new Common(); int day =
		 * Integer.parseInt(dobDD_text.getText()); int month =
		 * Integer.parseInt(dobMM_text.getText()); int year =
		 * Integer.parseInt(dobYYYY_text.getText());
		 * dobWords_text.setText(date2word.dateToWords(day, month, year));
		 * birthPlace_text.requestFocus(); } });
		 */
		// doaDD_text.addKeyListener(new KeyAdapter(){
		// public void keyPressed(KeyEvent e){
		// String value=doaDD_text.getText();
		// if(value.length()==1){
		// doaMM_text.requestFocus();
		// }
		// }
		// });
		// doaMM_text.addKeyListener(new KeyAdapter(){
		// public void keyPressed(KeyEvent e){
		// String value=doaMM_text.getText();
		// if(value.length()==1){
		// doaYYYY_text.requestFocus();
		// }
		// }
		// });
		// doaYYYY_text.addKeyListener(new KeyAdapter(){
		// public void keyPressed(KeyEvent e){
		// String value=doaYYYY_text.getText();
		// if(value.length()==3){
		// lastSchool_text.requestFocus();
		// }
		// }
		// });

		// /////////////Reset//////////////
		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		resetButton.setBounds(mainCentre + 75, 580, 130, 25);
		bottombandPanel.add(resetButton);

		resetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				if(sessionData.getSchoolName().equalsIgnoreCase("PR")){
//					new AdmissionForm(sessionData, section, user_name, user_role);
				}
				else{
					LinkedHashMap<String,LinkedHashMap<String, String>> studentList = new LinkedHashMap<String,LinkedHashMap<String, String>>();
					new AdmissionFormNew(sessionData, section, user_name, user_role,"ADMISSION","",studentList);
				}
			}
		});
	}
}
