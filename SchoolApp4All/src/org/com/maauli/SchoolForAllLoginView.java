package org.com.maauli;

import org.apache.log4j.Logger;
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

//start page
public class SchoolForAllLoginView {
	static int screenWidth;
	static int screenHeight;
	static int mainCentre;
	static String appName = "";
	static String img_login = "";
	static String jrc_required = "";
	static String sch_required = "";
	static JFrame frame = null;
	static String img_path = "";
	static String log_path = "";
	static Common commonObj = new Common();
	static Logger logger 			= Logger.getLogger(SchoolForAllLoginView.class.getName());
// static ResourceBundle bundle    = ResourceBundle.getBundle("org.com.accesser.school");
	static SessionData sessionData;
	private static LinkedHashMap<String, String> initialConfigMap;
	private static LinkedHashMap<String, String> configMap;
	private static DBValidate dbValidate = new DBValidate();
	private static LinkedHashMap<String, String> schoolMap;

	public static void main(String[] args) {
		try {
			logger.info("=====SchoolForAllLoginView main====");
			sessionData = new SessionData();
			schoolMap = new LinkedHashMap<String, String>();
			initialConfigMap = new LinkedHashMap<String, String>();
			configMap = new LinkedHashMap<String, String>();
	
			sessionData.setDBName("getschool");
			if((System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
				sessionData.setSchoolApp_ip("127.0.0.1");
			}
			else {
				sessionData.setSchoolApp_ip(System.getenv("SchoolApp_IP"));
			}
			
			sessionData.setDbURL("jdbc:mysql://" + sessionData.getSchoolApp_ip()
					+ "/getschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&serverTimezone=-05:30");
	
			configMap.put("DBUSER", "psUkfKpV6xHmdvuIMk05CQ==");
			configMap.put("DBPASSWD", "gHgrdxTlGCUy0nEW4A1QPg==");
			configMap.put("DBDRIVER", "com.mysql.jdbc.Driver");
			sessionData.setConfigMap(configMap);

			sessionData.setConnection(dbValidate.connectToDatabase(sessionData));
			if (sessionData.getConnection() != null) {
				initialConfigMap = dbValidate.getConfigMap(sessionData, "getschool", "app_connect", "");

				initialConfigMap.put("SchoolApp_IP", sessionData.getSchoolApp_ip());
			}
		} catch (Exception e1) {
			commonObj.logException(e1);
		}

		img_path = initialConfigMap.get("IMAGE_PATH");
		img_login = initialConfigMap.get("IMAGE_LOGIN");
		jrc_required = initialConfigMap.get("JRC_REQUIRED");
		sch_required = initialConfigMap.get("SCH_REQUIRED");
		log_path = commonObj.getDriveName() + initialConfigMap.get("LOG_PATH");

		commonObj.createTodayFolder(log_path, false);
		commonObj.createFile(log_path + "\\log_school.txt");
		logger = Logger.getLogger(SchoolForAllLoginView.class.getName());

		screenWidth = commonObj.screeWidth();
		screenHeight = commonObj.screeHeight();
		mainCentre = (screenWidth - 150) / 2;

		frame = new JFrame("Application Name");

		screenWidth = commonObj.screeWidth();
		screenHeight = commonObj.screeHeight();

		frame.setSize(screenWidth, screenHeight);
		frame.setResizable(false);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel() {
			/**
			 * for background image
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				Image img = new ImageIcon(img_path + "background.jpg").getImage();
				Dimension size = new Dimension(screenWidth, screenHeight);
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				g.drawImage(img, 0, 0, null);
			}
		};

		frame.add(panel);
		placeComponents(panel);
		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel) {

		panel.setLayout(null);
		String schoolList[];
		String schoolStr = "", schoolListData = "";
		
		try {
			JLabel headLabel = new JLabel("Welcome to MySchoolApp");
			logger.info("=====Welcome to MyApp====");
	
			headLabel.setFont(new Font("Book Antiqua", Font.BOLD, 30));
			headLabel.setBounds((screenWidth / 2 - 190), (screenHeight / 2 - 300), 900, 40);
			panel.add(headLabel);
	
			
			schoolList = initialConfigMap.get("SCHOOL_LIST").split(",");
	
				for (int i = 0; i < schoolList.length; i++) {
					schoolStr = schoolList[i].substring(0, schoolList[i].indexOf("|"));
					schoolListData = schoolListData + "," + schoolStr;
					schoolMap.put(schoolStr, schoolList[i].substring(schoolList[i].lastIndexOf("|") + 1));
				}
		}
		catch(Exception e) {
			logger.error("Exception placeComponents :: " + e);
		}

		schoolListData = schoolListData.substring(1);
		JLabel selectSchoolLabel = new JLabel("Please select school : ");
		selectSchoolLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		selectSchoolLabel.setBounds((screenWidth / 2 - 290), (screenHeight / 2 - 100), 900, 25);
		panel.add(selectSchoolLabel);

		String schoolListDisp[] = schoolListData.split(",");
		final JComboBox school_combo = new JComboBox(schoolListDisp);
		school_combo.setFont(new Font("Book Antiqua", Font.BOLD, 13));
		school_combo.setBounds((screenWidth / 2 - 80), (screenHeight / 2 - 100), 250, 25);
		panel.add(school_combo);

//		if (schoolMap.size() == 1) {
//			frame.setVisible(false);
//			callLoginSection(school_combo.getSelectedItem().toString());
//		} else {
//			frame.setVisible(true);
//		}

		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds((screenWidth / 2 + 190), (screenHeight / 2 - 100), 130, 25);
		panel.add(submitButton);

		submitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				try {
					String appType = "SCHOOL";
					String selSchool = school_combo.getSelectedItem().toString();
					if(selSchool.toLowerCase().contains("college")) {
						appType = "COLLEGE";
					}
					callLoginSection(selSchool, appType);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});

		submitButton.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == 10) {
					String appType = "SCHOOL";
					String selSchool = school_combo.getSelectedItem().toString();
					if(selSchool.toLowerCase().contains("college")) {
						appType = "COLLEGE";
					}
					callLoginSection(selSchool, appType);
				}
			}
		});

		/*
		 * if(sch_required.equalsIgnoreCase("true")){ JButton schoolButton = new
		 * JButton("school"); schoolButton.setFont(new Font("Book Antiqua",
		 * Font.BOLD, 25)); schoolButton.setBounds((screenWidth / 2 - 120),
		 * (screenHeight / 2 - 200), 200, 30); panel.add(schoolButton);
		 * 
		 * schoolButton.addActionListener(new ActionListener() {
		 * 
		 * public void actionPerformed(ActionEvent e) {
		 * sessionData.setDBName(sessionData1.getConfigMap().get("DB_NAME_SCHOOL"));
		 * sessionData.setAppName(sessionData1.getConfigMap().get("APP_NAME_SCHOOL")
		 * );
		 * sessionData.setSchoolName(sessionData1.getConfigMap().get("school_NAME"))
		 * ; sessionData.setAppType("SCHOOL"); frame.setVisible(false); new
		 * LoginDetails(sessionData); } }); }
		 */
	}

	public static void callLoginSection(String selSchool, String appType) {
		boolean validateFlag = false;
//		String school = "SCHOOL";
		try {
//		frame.setVisible(false);
//		new LoginSection(sessionData, selSchool, schoolMap.get(selSchool));
//		if(schoolMap.get(selSchool).toLowerCase().contains("college")) {
//			school_college = "COLLEGE";
//		}

			try {
//			if(dbValidate.connectDatabase(sessionData)){
				initialConfigMap = dbValidate.getConfigMap(sessionData, "getschool", "app_connect",
						selSchool + "|" + schoolMap.get(selSchool));
				initialConfigMap.put("SchoolApp_IP", sessionData.getSchoolApp_ip());
//			}
			} catch (Exception e1) {
				commonObj.logException(e1);
			}

			LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
			sessionData.setDbURL(initialConfigMap.get("DBURL_" + initialConfigMap.get("DB_NAME_" + appType)));
			sessionData.setDBName(initialConfigMap.get("DB_NAME_" + appType));
			try {
				configMap = dbValidate.getConfigMap(sessionData, sessionData.getDBName(), "config_data",
						initialConfigMap.get("SCHOOL_LIST"));
				configMap.put("SchoolApp_IP", sessionData.getSchoolApp_ip());
				// System.out.println();
			} catch (Exception e1) {
				commonObj.logException(e1);
			} finally {
			}

//			int refreshInterval = Integer.parseInt(configMap.get("REFRESH_INTERVAL"));
//			sessionData.setRefreshInterval(refreshInterval);
			sessionData.setAppName(configMap.get("APP_NAME_" + appType));
			sessionData.setSchoolName(configMap.get(appType + "_NAME"));
			sessionData.setAppType(appType);
			sessionData.setConfigMap(configMap);
			sessionData.setScreenHeight(screenHeight);
			sessionData.setScreenWidth(screenWidth);
			frame.setVisible(false);
			new LoginDetails(sessionData);

		} catch (Exception e1) {
			logger.error("Exception e1 callLoginSection :: " + e1);
		}
	}
}
