package org.com.maauli;

import org.apache.log4j.Logger;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.com.accesser.SessionData;

//start page
public class LoginView {
	static int screenWidth;
	static int screenHeight;
	static int mainCentre;
	static String appName = "";
	static String img_login = "";
	static String jrc_required = "";
	static String sch_required = "";
	static JFrame frame 			= null;
	static String img_path			= "";
	static String log_path			= "";
	static Common commonObj 		= new Common();
	static Logger logger;
    static ResourceBundle bundle    = ResourceBundle.getBundle("org.com.accesser.school");
    static SessionData sessionData  = new SessionData();

	public static void main(String[] args) {
		
		img_login = bundle.getString("IMAGE_LOGIN");
		jrc_required = bundle.getString("JRC_REQUIRED");
		sch_required = bundle.getString("SCH_REQUIRED");
		log_path = commonObj.getDriveName() + bundle.getString("LOG_PATH");
		commonObj.createTodayFolder(log_path, false);
    	commonObj.createFile(log_path+"\\school.txt");
    	logger 			= Logger.getLogger(LoginView.class.getName());
    	
		TreeMap tm = new TreeMap();
		boolean validateAuthentication = true;
		
		screenWidth = commonObj.screeWidth();
        screenHeight = commonObj.screeHeight();
        mainCentre = (screenWidth - 150) / 2;
		
		frame = new JFrame("Application Name");

		if(validateAuthentication)
		{
			screenWidth = commonObj.screeWidth();
			screenHeight = commonObj.screeHeight();
			
			frame.setSize(screenWidth, screenHeight);
			frame.setResizable(false);
			
			img_path	= bundle.getString("IMAGE_PATH");
	    	logger.info("img_path :: "+img_path);
	
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JPanel panel = new JPanel() {
				/**
				 * for background image
				 */
				private static final long serialVersionUID = 1L;
	
				public void paintComponent(Graphics g) {
					Image img = new ImageIcon(img_path+"background.jpg").getImage();
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
	}

	private static void placeComponents(JPanel panel) {

		panel.setLayout(null);
		JLabel headLabel = new JLabel("Welcome to MySchoolApp");
		logger.info("=====Welcome to MyApp====");

		headLabel.setFont(new Font("Book Antiqua", Font.BOLD, 30));
		headLabel.setBounds((screenWidth / 2 - 190), (screenHeight / 2 - 300), 900, 40);
		panel.add(headLabel);

		if(sch_required.equalsIgnoreCase("true")){
			JButton schoolButton = new JButton("School");
			schoolButton.setFont(new Font("Book Antiqua", Font.BOLD, 25));
			schoolButton.setBounds((screenWidth / 2 - 120), (screenHeight / 2 - 200), 200, 30);
	        panel.add(schoolButton);
	
	        schoolButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	            	sessionData.setDBName(bundle.getString("DB_NAME_SCHOOL"));
	            	sessionData.setAppName(bundle.getString("APP_NAME_SCHOOL"));
	            	sessionData.setSchoolName(bundle.getString("SCHOOL_NAME"));
	            	sessionData.setAppType("SCHOOL");
	            	sessionData.setBundle(bundle);
	            	sessionData.setScreenHeight(screenHeight);
	            	sessionData.setScreenWidth(screenWidth);
	                frame.setVisible(false);
	                new LoginDetails(sessionData);
	            }
	        });
		}
		
        if(jrc_required.equalsIgnoreCase("true")){
	        JButton collegeButton = new JButton("College");
	        collegeButton.setFont(new Font("Book Antiqua", Font.BOLD, 25));
	        collegeButton.setBounds((screenWidth / 2 - 120), (screenHeight / 2 - 100), 200, 30);
	        panel.add(collegeButton);
	
	        collegeButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	
	            	sessionData.setDBName(bundle.getString("DB_NAME_COLLEGE"));
	            	sessionData.setAppName(bundle.getString("APP_NAME_COLLEGE"));
	            	sessionData.setSchoolName(bundle.getString("COLLEGE_NAME"));
	            	sessionData.setAppType("COLLEGE");
	            	sessionData.setBundle(bundle);
	            	sessionData.setScreenHeight(screenHeight);
	            	sessionData.setScreenWidth(screenWidth);
	                frame.setVisible(false);
	                new LoginDetails(sessionData);
	            }
	        });
        }
	}
}
