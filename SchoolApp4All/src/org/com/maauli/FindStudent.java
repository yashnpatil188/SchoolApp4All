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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class FindStudent extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JFrame frame = null;

	static JComboBox studentcombo;

	static int submitCount = 0;

	static List<String> studentInfoList = null;
	
	private static LinkedHashMap<String,LinkedHashMap<String, String>> foundStudentList;

	static int retCount = 0;

	static int scrollHeight = 0;

	static LinkedHashMap<String,LinkedHashMap<String, String>> passStudentList = null;

	static String std = "";

	static String div = "";

	static String stdClass = "Select";

	static String divClass = "Select";

	static String grClass = "";

	static String lastClass = "";

	static String firstClass = "";

	static String fatherClass = "";

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
	
    static String app_header = "";
    
    static String app_header_0 = "";

	static String user_name = "";

	static String user_role = "";
	
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
    static String yearList = "";
    
	static Common cm = new Common();

	static SessionData sessionData = new SessionData();
	
	static DBValidate findStudentDB = new DBValidate();

	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static Logger logger = Logger.getLogger(FindStudent.class.getName());
	
	static LinkedHashMap<String,String> selectedStudent = null;
	
	static boolean grAllClass = false;

	public FindStudent(SessionData sessionData1, String retGr_no, String retStd, String retDiv, String retLastName,
			String retFirstName, String retFatherName, LinkedHashMap<String,LinkedHashMap<String, String>> retStudentMap, 
			String sec, String retUserName,	String retUserRole, boolean grAll) {
		
		System.gc();
		grAllClass = grAll;
		selectedStudent = new LinkedHashMap<String,String>();
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		grClass = retGr_no;
		logger.info("retStd::" + retStd);
		logger.info("retDiv::" + retDiv);
		if (!retStd.equalsIgnoreCase("")) {
			stdClass = retStd;
		} else {
			stdClass = "Select";
		}
		if (!retDiv.equalsIgnoreCase("")) {
			divClass = retDiv;
		} else {
			divClass = "Select";
		}
		lastClass = retLastName;
		firstClass = retFirstName;
		fatherClass = retFatherName;
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

		passStudentList = retStudentMap;
		foundStudentList = retStudentMap;
		retCount = foundStudentList.size();
		scrollHeight = (retCount - 13) * 30; // to adjust the perfect scroll height
		if (scrollHeight < 0)
			scrollHeight = 0;

		try {
			if(findStudentDB.connectDatabase(sessionData)){
				yearList = findStudentDB.findYearList(sessionData, "CLASS_ALLOTMENT");
				yearList = "All,"+yearList;
			}
		} catch (Exception e1) {
			cm.logException(e1);
		}
		
		submitCount = 0;
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
					if (findStudentDB.connectDatabase(sessionData)) {
						deleteFlag = findStudentDB.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, section);
						findStudentDB.closeDatabase(sessionData);

						frame.setVisible(false);
						String[] arguments = new String[] {""};
		                LoginView.main(arguments);
					}
				} catch (Exception e1) {
					logger.info("Exception logoutButton ===>>>" + e1);
				} finally {
					findStudentDB.closeDatabase(sessionData);
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

				frame.setVisible(false);
				new Staff(sessionData, section, user_name, user_role);
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

				// frame.setVisible(false);
				// new Welcome();
			}
		});

		JButton searchButton = new JButton("Find");
		searchButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		searchButton.setBounds(10, 300, 130, 35);
		searchButton.setBackground(Color.GREEN);
		leftPanel.add(searchButton);

		searchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// List studList = new ArrayList();
				// new FindStudent("","", "", "", "", "", studList,section);
				// frame.setVisible(false);
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

		JLabel menuBandTitle = new JLabel(secName);
		menuBandTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		menuBandTitle.setForeground(Color.orange);
		menuBandTitle.setBounds(20, 0, 600, 30);
		topbandPanel.add(menuBandTitle);

		JLabel subMenuTitle = new JLabel("Find");
		subMenuTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		subMenuTitle.setForeground(Color.orange);
		subMenuTitle.setBounds(20, 45, 100, 30);
		topbandPanel.add(subMenuTitle);

		mainPanel.add(topbandPanel, BorderLayout.EAST);
		// mainPanel.add(bottombandPanel, BorderLayout.SOUTH);

		// /////////////////bottombandPanel area//////////////////////////
		// JPanel panelWorkable = new JPanel(new BorderLayout());

		JPanel bottombandPanel = new JPanel(new BorderLayout());
		// bottombandPanel.setBackground(Color.green);
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
				// g.drawImage(img, 6, 0, screenWidth-150, 150, null);
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
				if (grString.length() > 6) {
					grString = grString.substring(0, 7);
					logger.info("grString===>" + grString);
					gr_no_text.setText(grString);
				}
			}

			public void keyReleased(KeyEvent e) {
				String grString = gr_no_text.getText();
				if (grString.length() > 6) {
					grString = grString.substring(0, 7);
					logger.info("grString===>" + grString);
					gr_no_text.setText(grString);
				}
			}
		});
		 */
		gr_no_text.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent event) {
				String grString = gr_no_text.getText();
				if (grString.length() < 7 && grString.length() !=  0) {
					gr_no_text.setText(cm.appendZero(grString));
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
		admittedStd_label.setBounds(380, 00, 120, 50);
		findPanel.add(admittedStd_label);

		std = stdClass + "," + std;
		String[] stdList = std.split(",");
		// String admittedStdList[] = {"","V","VI","VII","VIII","IX","X"};
		final JComboBox admittedStd_combo = new JComboBox(stdList);
		admittedStd_combo.setSelectedItem(stdClass);
		admittedStd_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_combo.setBounds(480, 12, 200, 25);
		findPanel.add(admittedStd_combo);

		// //////////////////////////////////
		// /////////////Div//////////////
		JLabel admittedDiv_label = new JLabel("Div :");
		admittedDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedDiv_label.setBounds(710, 00, 120, 50);
		findPanel.add(admittedDiv_label);

		div = divClass + "," + div;
		String[] divList = div.split(",");
		// String admittedDivList[] = {"","A","B","C","D"};
		final JComboBox admittedDiv_combo = new JComboBox(divList);
		admittedDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedDiv_combo.setBounds(830, 12, 200, 25);
		findPanel.add(admittedDiv_combo);
		// //////////////////////////////////
		// /////////////Last Name//////////////
		JLabel lastName_label = new JLabel("Last Name :");
		lastName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_label.setBounds(60, 40, 120, 50);
		findPanel.add(lastName_label);

		final JTextField lastName_text = new JTextField(lastClass);
		lastName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lastName_text.setBounds(160, 50, 130, 25);
		findPanel.add(lastName_text);
		// //////////////////////////////////
		// /////////////First Name//////////////
		JLabel firstName_label = new JLabel("First Name :");
		firstName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_label.setBounds(380, 40, 120, 50);
		findPanel.add(firstName_label);

		final JTextField firstName_text = new JTextField(firstClass);
		firstName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		firstName_text.setBounds(480, 50, 200, 25);
		findPanel.add(firstName_text);
		// //////////////////////////////////
		// /////////////Father Name//////////////
		JLabel fatherName_label = new JLabel("Father Name :");
		fatherName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_label.setBounds(710, 40, 120, 50);
		findPanel.add(fatherName_label);

		final JTextField fatherName_text = new JTextField(fatherClass);
		fatherName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		fatherName_text.setBounds(830, 50, 200, 25);
		findPanel.add(fatherName_text);
		
		JLabel academic_label = new JLabel("Academic Year :");
		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academic_label.setBounds(60, 80, 120, 50);
		findPanel.add(academic_label);
		
		String academicYearList[] = yearList.split(",");
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academicYear_combo.setBounds(180, 92, 120, 25);
		findPanel.add(academicYear_combo);
		// //////////////////////////////////
		// /////////GR radio///////////////
		final JRadioButton gr_no_radio = new JRadioButton();
		gr_no_radio.setBounds(30, 15, 20, 20);
		findPanel.add(gr_no_radio);
		// /////////name radio///////////////30, 80, 120, 50
		final JRadioButton name_radio = new JRadioButton();
		name_radio.setBounds(30, 55, 20, 20);
		findPanel.add(name_radio);
		// /////////Std radio///////////////
		final JRadioButton std_radio = new JRadioButton();
		std_radio.setBounds(350, 15, 20, 20);
		findPanel.add(std_radio);

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
					String academicSel = (String) academicYear_combo.getSelectedItem();
					if(!stdSel.equalsIgnoreCase("All") && !stdSel.equalsIgnoreCase("Select") && findStudentDB.connectDatabase(sessionData)){
						admittedDiv_combo.removeAllItems();
						admittedDiv_combo.addItem("Select");
						String divAvailabe = findStudentDB.getDistinctDiv(sessionData, stdSel, section, 
								"PRESENT_DIV", "PRESENT_STD", "class_allotment",academicSel);
						
						for (String retval: divAvailabe.split(",")) {
							admittedDiv_combo.addItem(retval);
						}
					}
				} catch (Exception e1) {
					logger.info("Exception insertFormData ===>>>" + e1);
				} finally {
					findStudentDB.closeDatabase(sessionData);
				}

			}
		});
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
		std_radio.setSelected(false);
		name_radio.setSelected(false);
		gr_no_radio.setSelected(true);
		gr_no_text.setEnabled(true);
		admittedStd_combo.setEnabled(false);
		admittedDiv_combo.setEnabled(false);
		lastName_text.setEnabled(false);
		firstName_text.setEnabled(false);
		fatherName_text.setEnabled(false);
		// ///////////////////////

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
		// /////////////Submit//////////////
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(mainCentre - 150, 90, 130, 25);
		findPanel.add(submitButton);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				submitCount++;

				boolean validateFields = true;
				String gr_no = gr_no_text.getText();
				String lastName = lastName_text.getText();
				String firstName = firstName_text.getText();
				String fatherName = fatherName_text.getText();
				String std = (String) admittedStd_combo.getSelectedItem();
				String div = (String) admittedDiv_combo.getSelectedItem();
				String academic = academicYear_combo.getSelectedItem().toString();
				if(academic.equalsIgnoreCase("All")) {
					academic = "";
				}

				Common commonObj = new Common();

				if (gr_no_radio.isSelected() && (gr_no_text.getText().equalsIgnoreCase("") || gr_no.equalsIgnoreCase("0000000"))) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please enter valid GR No.");
				}
				else if (commonObj.checkComma(gr_no)) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "GR_NO cannot contain |-:';,");
				} 
				else if (name_radio.isSelected()){
					if (lastName.length() < 1 && firstName.length() < 1 && fatherName.length() < 1) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please enter Lastname or firstname or fathername");
					}
					else if (lastName.length() > 50) {
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
				}
				else if (std_radio.isSelected()){
					if(std.equalsIgnoreCase("Select") || std.equalsIgnoreCase("")){
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select std.");
					}
				}
				
				LinkedHashMap<String,LinkedHashMap<String, String>> studentMap;
				if (validateFields) {
					try {
						studentMap = findStudentDB.findStudent(sessionData, gr_no, std, div, lastName, firstName, fatherName, 
								section, academic, "", "");
						int listSize = studentMap.size();
						logger.info("No of students found :: " + listSize);

						if (listSize > 0) {
							frame.setVisible(false);
							new FindStudent(sessionData, gr_no, std, div, lastName, firstName, fatherName, studentMap, section, user_name, user_role, false);
						} else {
							// dataPanel.repaint(0, 0, 500, 5);
							JOptionPane.showMessageDialog(null, "No data found");
							frame.setVisible(false);
							new FindStudent(sessionData, gr_no, std, div, lastName, firstName, fatherName, studentMap, section, user_name, user_role, false);

						}

					} catch (Exception e1) {
						logger.info("Exception FindStudent form ===>>>" + e1);
						new Student(sessionData, section, user_name, user_role);
						frame.setVisible(false);
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

				frame.setVisible(false);
				LinkedHashMap<String,LinkedHashMap<String, String>> studMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
				new FindStudent(sessionData, "", "", "", "", "", "", studMap, section, user_name, user_role, false);
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

		bottombandPanel.add(findPanel, BorderLayout.NORTH);
		// ////////////////find panel ends/////////////////////////////////

		// //////////scrollArea panel/////////////////////////////////////
		JPanel scrollAreaPanel = new JPanel(new BorderLayout());
		// scrollAreaPanel.setBackground(Color.green);
		scrollAreaPanel.setPreferredSize(new Dimension(screenWidth - 150, screenHeight - 337));

		dataPanel.setLayout(null);
		if (foundStudentList.size() > 0) {
			int listSize = foundStudentList.size();
			int width = 40;
			String line = "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
			logger.info("foundStudentList==>" + listSize);

			final JRadioButton grAllRadio = new JRadioButton();
			grAllRadio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			grAllRadio.setBounds(width, 14, 18, 18);
			grAllRadio.setSelected(grAllClass);
			dataPanel.add(grAllRadio);
			
			width = width + 40;
			JLabel gr_label = new JLabel("Roll No.");
			gr_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			gr_label.setBounds(width, 00, 120, 50);
			dataPanel.add(gr_label);

			width = width + 70;
			JLabel pipe_label = new JLabel("|");
			pipe_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label.setBounds(width, 00, 120, 50);
			dataPanel.add(pipe_label);

			width = width + 20;
			JLabel stdDiv_label = new JLabel("Std-Div");
			stdDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			stdDiv_label.setBounds(width, 00, 120, 50);
			dataPanel.add(stdDiv_label);
			
//			width = width + 90;
			width = width + 170;
			JLabel pipe_label2 = new JLabel("|");
			pipe_label2.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label2.setBounds(width, 00, 120, 50);
			dataPanel.add(pipe_label2);
			
			width = width + 20;
			JLabel name_label = new JLabel("Name");
			name_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			name_label.setBounds(width, 00, 120, 50);
			dataPanel.add(name_label);
			
			width = width + 500;
			JButton printSelectedButton = new JButton("Print Selected");
			printSelectedButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			printSelectedButton.setBounds(width, 10, 160, 20);
			dataPanel.add(printSelectedButton);
			
			printSelectedButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if(!selectedStudent.isEmpty()) {
//						JOptionPane.showMessageDialog(null, selectedStudent.size() + " student selected");	
						AddmissionForm_PDF.getAddmissionForm_PDF(sessionData, selectedStudent, stdClass, divClass);
					}
					else {
						JOptionPane.showMessageDialog(null, "Please select students");	
					}
					
				}
			});
			
			grAllRadio.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					if(grAllRadio.isSelected()) {
						new FindStudent(sessionData, grClass, std, div, lastClass, firstClass, fatherClass, foundStudentList, section, user_name, user_role, true);
					}
					else {
						new FindStudent(sessionData, grClass, std, div, lastClass, firstClass, fatherClass, foundStudentList, section, user_name, user_role, false);
					}
					
				}
			});
			
			JLabel line_label = new JLabel(line);
			line_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			line_label.setBounds(30, 10, 1300, 50);
			dataPanel.add(line_label);

			if (listSize > 0) {
				int j = 0;
				final JRadioButton[] grRadio = new JRadioButton[listSize];
				JLabel[] rollNo_labels = new JLabel[listSize];
				final JLabel[] gr_labels = new JLabel[listSize];
				JLabel[] pipeRadio_labels = new JLabel[listSize];
				JLabel[] pipe_labels = new JLabel[listSize];
				JLabel[] name_labels = new JLabel[listSize];
				JLabel[] stdDiv_labels = new JLabel[listSize];
				JLabel[] line_labels = new JLabel[listSize];
				JButton[] view = new JButton[listSize];
				JButton[] print = new JButton[listSize];
				JButton[] performance = new JButton[listSize];
				try {
					if (findStudentDB.connectDatabase(sessionData)) {
						findStudentDB.alterStudyingSince(sessionData);
					}
				} catch (Exception e) {
					logger.info("fetch data Exception == " + e);
				} finally {
					findStudentDB.closeDatabase(sessionData);
				}
				
				int k = 0;
		        Set set = foundStudentList.entrySet();
				Iterator i = set.iterator();
				while(i.hasNext()) {
					width = 40;
					j = j + 30;
					Map.Entry me = (Map.Entry)i.next();
					final String gr = ((LinkedHashMap<?, ?>) foundStudentList.get(me.getKey())).get("grNo").toString();
					String name = ((LinkedHashMap<?, ?>) foundStudentList.get(me.getKey())).get("name").toString();
					String rollNo = ((LinkedHashMap<?, ?>) foundStudentList.get(me.getKey())).get("rollNo").toString();
					String std = ((LinkedHashMap<?, ?>) foundStudentList.get(me.getKey())).get("std").toString();
					String div = ((LinkedHashMap<?, ?>) foundStudentList.get(me.getKey())).get("div").toString();
					String originalLc = ((LinkedHashMap<?, ?>) foundStudentList.get(me.getKey())).get("originalLc").toString();

					grRadio[k] = new JRadioButton();
					grRadio[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					grRadio[k].setToolTipText(gr);
					grRadio[k].setSelected(grAllClass);
					if(grAllClass) {
						selectedStudent.put(gr, "");
					}
					grRadio[k].setBounds(width, j+14, 18, 18);
					dataPanel.add(grRadio[k]);
					
					pipeRadio_labels[k] = new JLabel("|");
					pipeRadio_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipeRadio_labels[k].setBounds(width+20, j, 120, 50);
					dataPanel.add(pipeRadio_labels[k]);
					
					width = width + 60;
					rollNo_labels[k] = new JLabel(rollNo);
					rollNo_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					rollNo_labels[k].setToolTipText(gr);
					rollNo_labels[k].setBounds(width, j, 120, 50);
					dataPanel.add(rollNo_labels[k]);
					
					gr_labels[k] = new JLabel(gr);
					gr_labels[k].setToolTipText(gr);
					gr_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					gr_labels[k].setBounds(width, j, 120, 40);
//					dataPanel.add(gr_labels[k]);

					width = width + 50;
					pipe_labels[k] = new JLabel("|");
					pipe_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels[k].setBounds(width, j, 120, 50);
					dataPanel.add(pipe_labels[k]);
					
					width = width + 20;
					stdDiv_labels[k] = new JLabel(std+" - "+div);
					stdDiv_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					stdDiv_labels[k].setBounds(width, j, 200, 50);
					dataPanel.add(stdDiv_labels[k]);
					
//					width = width + 90;
					width = width + 170;
					pipe_labels[k] = new JLabel("|");
					pipe_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					pipe_labels[k].setBounds(width, j, 120, 50);
					dataPanel.add(pipe_labels[k]);

					width = width + 20;
					name_labels[k] = new JLabel(name);
					name_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					if(!originalLc.equalsIgnoreCase("") && !originalLc.equalsIgnoreCase("-")){
						name_labels[k].setText(name + " (Left)");
						name_labels[k].setForeground(Color.RED);
					}
					name_labels[k].setBounds(width, j, 500, 50);
					dataPanel.add(name_labels[k]);

					width = width + 520;
					view[k] = new JButton("View");
					view[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					view[k].setBounds(width, j + 12, 100, 20);
					dataPanel.add(view[k]);
					
					width = width + 130;
					print[k] = new JButton("Print");
					print[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					print[k].setBounds(width, j + 12, 100, 20);
					dataPanel.add(print[k]);

					final int m = k;
					view[k].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {

							frame.setVisible(false);
//							if(sessionData.getSchoolName().equalsIgnoreCase("PR")){
//								new StudentViewForm(sessionData, gr, studentInfoList, section, user_name, user_role);
//							}
//							else{
								new AdmissionFormNew(sessionData, section, user_name, user_role,"VIEW",gr, foundStudentList);
//							}
						}
					});
					
					print[k].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							LinkedHashMap<String,String> selectedGr = new LinkedHashMap<String,String>();
							selectedGr.put(gr, "");
							AddmissionForm_PDF.getAddmissionForm_PDF(sessionData, selectedGr, stdClass, divClass);
						}
					});
					
					grRadio[k].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							boolean radioValue = grRadio[m].isSelected();
							if(radioValue) {
								selectedStudent.put(gr_labels[m].getText(), "");
							}
							else {
								grAllClass = false;
								grAllRadio.setSelected(false);
								selectedStudent.remove(gr_labels[m].getText());
							}
						}
					});

					performance[k] = new JButton("Performance");
					performance[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					performance[k].setBounds(860, j + 12, 130, 20);
//					dataPanel.add(performance[k]);

					performance[k].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {

							// frame.setVisible(false);
							// new StudentEditForm(gr, passStudentList,section);
						}
					});

					line_labels[k] = new JLabel(line);
					line_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					line_labels[k].setBounds(30, j + 10, 1300, 50);
					dataPanel.add(line_labels[k]);
					k++;
				}

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
