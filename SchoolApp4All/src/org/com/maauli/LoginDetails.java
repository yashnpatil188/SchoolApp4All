package org.com.maauli;

import org.apache.log4j.Logger;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class LoginDetails {
	static int screenWidth;
	static int screenHeight;
	static int mainCentre;
	static String img_login = "";
	static JFrame frame 			= null;
	final static String forgotPass  = "Forgot Password";
	final static String back  = "Back";
	static String img_path			= "";
	static Common commonObj 		= new Common();
	static Logger logger 			= Logger.getLogger(LoginDetails.class.getName());
//	static final Logger logger = Logger.getLogger("debugLogger");
//	static final Logger logger = Logger.getLogger("reportsLogger");
	
    static ResourceBundle bundle    = ResourceBundle.getBundle("org.com.accesser.school");
    static SessionData sessionData  = new SessionData();
    static DBValidate dbValidate 	= new DBValidate();

	public LoginDetails(SessionData sessionData1) {
		
		boolean validateAuthentication = true;
		bundle = sessionData1.getBundle();
		TreeMap tm = new TreeMap();
		sessionData = sessionData1;
		screenWidth = commonObj.screeWidth();
		screenHeight = commonObj.screeHeight();
		mainCentre = (screenWidth - 150) / 2;
		
		frame = new JFrame("Welcome to "+sessionData1.getAppName());
		img_login = bundle.getString("IMAGE_LOGIN");
		JFrame f = new JFrame("Connectivity in progress. Don't Close");
		
		try {
			f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
		    f.setSize(400, 0);
		    f.setResizable(false);
		    f.setVisible(true);
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
			if(dbValidate.connectDatabase(sessionData1)){
				dbValidate.addRenewCodeColumn(sessionData1);
				dbValidate.addBalanceFeeColumn(sessionData1);
				dbValidate.update_max_allowed_packet(sessionData1);
				tm = dbValidate.getAuthenticationDetails(sessionData, sessionData.getSchoolName());
				if(!tm.get("valid_sn").toString().contains(commonObj.getDriveSerialNumber())){
					f.setVisible(false);
					validateAuthentication = false;
					commonObj.showMessageDialog("This machine is not registered with Maauli Software Solutions.");
					frame.setVisible(false);
				}
				if(!tm.get("expiry_status").toString().equalsIgnoreCase("ACTIVE") && validateAuthentication){
					f.setVisible(false);
					validateAuthentication = false;
					commonObj.showMessageDialog("Licence copy has expired. Please renew.");
					commonObj.renewLogic(sessionData1, tm);
					frame.setVisible(false);
					String[] arguments = new String[] {""};
	                LoginView.main(arguments);
				}
				else {
					try{
						if(validateAuthentication){
							int daysLeft = 0;
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
							Date today = new Date();
					        Date todayDate = formatter.parse(commonObj.getCurrentDate().toString());
					        Date expiryDate = formatter.parse(commonObj.MM_ddlmmlyyyy(tm.get("expiry_date").toString()));
					        daysLeft = commonObj.daysBetween(todayDate, expiryDate);
					        
					        if(daysLeft >= 0 && daysLeft < 10){
					        	f.setVisible(false);
					        	commonObj.showMessageDialog("License copy is about to expire. Renew it soon.");
					        	commonObj.renewLogic(sessionData1, tm);
					        	frame.setVisible(false);
					        	String[] arguments = new String[] {""};
				                LoginView.main(arguments);
					        } else if(daysLeft < 0){
					        	dbValidate.updateAuthenticationDetails();
					        	f.setVisible(false);
					        	commonObj.showMessageDialog("License copy has expired. Please renew.");
					        	commonObj.renewLogic(sessionData1, tm);
								validateAuthentication = false;
								frame.setVisible(false);
								String[] arguments = new String[] {""};
				                LoginView.main(arguments);
					        }
						}
						/*else{
							f.setVisible(false);
							commonObj.showMessageDialog("Please get the licensed copy from Maauli Software solutions.");
						}*/
					} catch(Exception e){
						commonObj.logException(e);
						commonObj.showMessageDialog("Error occured in validating expiry."
								+ " \n Please contact Maauli Software solutions");
						f.setVisible(false);
					}
				}
			}
			else{
				f.setVisible(false);
				logger.error("Failed to connect database before validating machine");
				validateAuthentication = false;
			}
		} catch (Exception e1) {
			f.setVisible(false);
			validateAuthentication = false;
			commonObj.logException(e1);
			commonObj.showMessageDialog("Please contact Maauli Software solutions Administrator.");
		}

		if(validateAuthentication)
		{
			f.setVisible(false);
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
//			placeComponents(panel);
			/////////////place comonents start/////////////
			panel.setLayout(null);
			JLabel headLabel = new JLabel("Welcome to MySchoolApp");
			logger.info("=====Welcome to MyApp====");

			headLabel.setFont(new Font("Book Antiqua", Font.BOLD, 30));
			headLabel.setBounds((screenWidth / 2 - 190), (screenHeight / 2 - 300), 900, 40);
			panel.add(headLabel);

			JLabel userLabel = new JLabel("Username");
			userLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
			userLabel.setBounds((screenWidth / 2 - 200), (screenHeight / 2 - 200), 90, 25);
			panel.add(userLabel);

			final JTextField userText = new JTextField(20);
			userText.setBounds((screenWidth / 2 - 100), (screenHeight / 2 - 200),
					160, 25);
			panel.add(userText);

			JLabel passwordLabel = new JLabel("Password");
			passwordLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
			passwordLabel.setBounds((screenWidth / 2 - 200), (screenHeight / 2 - 150), 80, 25);
			panel.add(passwordLabel);

			final JPasswordField passwordText = new JPasswordField(20);
			passwordText.setBounds((screenWidth / 2 - 100), (screenHeight / 2 - 150), 160, 25);
			panel.add(passwordText);
			
			/////////////////////////////////
			JLabel userRole_label = new JLabel("User Role ");
			userRole_label.setFont(new Font("Book Antiqua", Font.BOLD, 18));
			userRole_label.setBounds((screenWidth / 2 - 200), (screenHeight / 2 - 100), 160, 25);
			panel.add(userRole_label);
			
			String roleList[] = {"Administrator","Teaching Staff","Non-Teaching staff"};
			final JComboBox role_combo = new JComboBox(roleList);
			role_combo.setFont(new Font("Book Antiqua", Font.BOLD, 13));
			role_combo.setBounds((screenWidth / 2 - 100), (screenHeight / 2 - 100), 160, 25);
			panel.add(role_combo);

			final ImageIcon iconLogin = new ImageIcon(img_path + img_login);
	        JLabel labelLogin = new JLabel(iconLogin);
	        labelLogin.setBounds((screenWidth / 2 - 60), (screenHeight / 2 - 50), 60, 60);
	        panel.add(labelLogin);
	        
	        labelLogin.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					try {
						String username = "";
						String password = "";
						String role		= "";
						
						username = userText.getText();
						password = new String(passwordText.getPassword());
						role 	 = (String) role_combo.getSelectedItem();

						callValidateLogin(username, password, role);
					} catch (Exception e) {
						logger.info(e);
					}
				}
			});

			final JLabel backLabel = new JLabel("<html><a href=\" " + back
					+ "\"> Back </a></html>");
			backLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
			backLabel.setBounds((screenWidth / 2 - 100), (screenHeight / 2), 50, 30);
			panel.add(backLabel);
			backLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					try {
						frame.setVisible(false);
						String[] arguments = new String[] {""};
		                LoginView.main(arguments);
					} catch (Exception e) {
						logger.info(e);
					}
				}
			});
			
			final JLabel forgetLabel = new JLabel("<html><a href=\" " + forgotPass
					+ "\"> Forget Password ?</a></html>");
			forgetLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
			forgetLabel.setBounds((screenWidth / 2), (screenHeight / 2), 200, 30);
			panel.add(forgetLabel);
			forgetLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					try {
						String username = userText.getText();
						if(!username.equalsIgnoreCase("")){
							frame.setVisible(false);
							new ForgotPasswordSecret(sessionData, userText.getText());
						} else {
							commonObj.showMessageDialog("Please enter User Name");
						}
					} catch (Exception e) {
						logger.info(e);
					}
				}
			});
			
			passwordText.addKeyListener(new KeyAdapter(){
	            public void keyReleased(KeyEvent e){
	            int keyCode=e.getKeyCode();
	            if(keyCode == 10){//for enter key
	            	String username = "";
	            	String password = "";
	            	String role		= "";
	            	
					username = userText.getText();
					password = new String(passwordText.getPassword());
					role 	 = (String) role_combo.getSelectedItem();

					callValidateLogin(username, password, role);
	              }
	            }
	       });
			
			userText.addKeyListener(new KeyAdapter(){
	            public void keyReleased(KeyEvent e){
	            int keyCode=e.getKeyCode();
	            if(keyCode == 10){
	            	String username = "";
	            	String role		= "";
	            	String password = "";
	            	
					username = userText.getText();
					password = new String(passwordText.getPassword());
					role 	 = (String) role_combo.getSelectedItem();

					callValidateLogin(username, password, role);

	              }
	            }
	       });
			
			role_combo.addKeyListener(new KeyAdapter(){
	            public void keyReleased(KeyEvent e){
	            int keyCode=e.getKeyCode();
	            if(keyCode == 10){
	            	String username = "";
	            	String password = "";
	            	String role		= "";
	            	
					username = userText.getText();
					password = new String(passwordText.getPassword());
					role 	 = (String) role_combo.getSelectedItem();

					callValidateLogin(username, password, role);

	              }
	            }
	       });
			
			labelLogin.addKeyListener(new KeyAdapter(){
	            public void keyReleased(KeyEvent e){
	            int keyCode=e.getKeyCode();
	            if(keyCode == 10){
	            	String username = "";
	            	String password = "";
	            	String role		= "";
	            	
					username = userText.getText();
					password = new String(passwordText.getPassword());
					role 	 = (String) role_combo.getSelectedItem();

					callValidateLogin(username, password, role);
	              }
	            }
	       });
			////////////place component ends///////////////
			
			frame.setVisible(true);
		}
	}
	
	public static void callValidateLogin(String username, String password, String role){
		boolean validateFlag = false;
		try {
			validateFlag = commonObj.validateLogin(sessionData, username, password, role.toUpperCase());
			String userStatusSession = sessionData.getUserStatus();
			if(validateFlag){
				if (!userStatusSession.equalsIgnoreCase("NEW") && dbValidate.connectDatabase(sessionData)) {
					LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
					configMap = dbValidate.getConfigMap(sessionData, "config_data", sessionData.getDBName()+"|"+sessionData.getDBName());
					configMap.put("SchoolApp_IP", bundle.getString("DBURL_"+sessionData.getDBName()));
					
					String startDate = bundle.getString("ACADEMIC_YEAR_START_"+sessionData.getDBName());
					startDate = commonObj.formatyyyymmddtoddmmyyyy(commonObj.getCurrentYear().substring(0,4) + "-" + startDate);//yyyy-mm-dd
					configMap.put("ACADEMIC_START_DATE", startDate);
					
					String endDate = bundle.getString("ACADEMIC_YEAR_END_"+sessionData.getDBName());
					endDate = commonObj.formatyyyymmddtoddmmyyyy(commonObj.getCurrentYear().substring(0,4) + "-" + endDate);//yyyy-mm-dd
					configMap.put("ACADEMIC_END_DATE", endDate);
					
					dbValidate.createTable(sessionData);
					dbValidate.changeTimeZone(sessionData);
					dbValidate.createAttendancePeriodly(sessionData);
					sessionData.setConfigMap(configMap);
					frame.setVisible(false);
					new Welcome(sessionData,username,role);
				} else {
					frame.setVisible(false);
					new ChangeOnFirstLogin(sessionData);
				}
			}
		} catch (Exception e1) {
			commonObj.logException(e1);
		}
	}
}