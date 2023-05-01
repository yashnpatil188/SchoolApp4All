package org.com.maauli;

import org.apache.log4j.Logger;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;


public class CreateUser {
	static int screenWidth;
	static int screenHeight;
	static JFrame frame = null;
	final static String forgetPass  = "Forget Password";
	final static String createUser  = "Create New User";
	static String img_path			= "";
	static String img_submit 		= "";
	static String img_home 			= "";
	static String secret_quest_1	= "";
	static String secret_quest_2	= "";
	static String secret_quest_3	= "";
	static String create_user_role	= "";
	static Common commonObj 		= new Common();
	static String user_name 		= "";
    static String user_role 		= "";
	static Logger logger 			= Logger.getLogger(LoginView.class.getName());
    static ResourceBundle bundle    = ResourceBundle.getBundle("org.com.accesser.school");
    static SessionData sessionData 	= new SessionData();
    static DBValidate dbValidate 	= new DBValidate();
    

    public CreateUser(SessionData sessionData1, String retUserName, String retRole)
    {
    	System.gc();
    	user_name = retUserName;
        user_role = retRole;
        sessionData = sessionData1;

		frame = new JFrame("Application Name");
		screenWidth = commonObj.screeWidth();
		screenHeight = commonObj.screeHeight();

		frame.setSize(screenWidth, screenHeight);
		frame.setResizable(false);
		
		img_path	= bundle.getString("IMAGE_PATH");
    	logger.info("img_path :: "+img_path);
    	img_submit  = bundle.getString("IMAGE_SUBMIT");
    	img_home = bundle.getString("IMAGE_HOME");
    	
    	secret_quest_1     = bundle.getString("SECRET_QUEST_1");
    	secret_quest_2     = bundle.getString("SECRET_QUEST_2");
    	secret_quest_3     = bundle.getString("SECRET_QUEST_3");
    	create_user_role   = bundle.getString("CREATE_USER_ROLE");
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

	private static void placeComponents(JPanel panel) {

		panel.setLayout(null);
		JLabel headLabel = new JLabel("Create New User");
		logger.info("=====Create New User====");

		headLabel.setFont(new Font("Book Antiqua", Font.BOLD, 30));
		headLabel.setBounds((screenWidth / 2 - 150), 30, 300, 40);
		panel.add(headLabel);

		JLabel firstLabel = new JLabel("First Name");
		firstLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		firstLabel.setBounds(150, (screenHeight / 2 - 250), 150, 25);
		panel.add(firstLabel);
		
		final JTextField firstText = new JTextField(20);
		firstText.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		firstText.setBounds(280, (screenHeight / 2 - 250), 200, 25);
		panel.add(firstText);
		
		JLabel lastLabel = new JLabel("Last Name");
		lastLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		lastLabel.setBounds(590, (screenHeight / 2 - 250), 150, 25);
		panel.add(lastLabel);
		
		final JTextField lastText = new JTextField(20);
		lastText.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		lastText.setBounds(720, (screenHeight / 2 - 250), 200, 25);
		panel.add(lastText);
		
		JLabel contactLabel = new JLabel("Contact");
		contactLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		contactLabel.setBounds(150, (screenHeight / 2 - 200), 150, 25);
		panel.add(contactLabel);

		final JTextField contactText = new JTextField(20);
		contactText.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		contactText.setBounds(280, (screenHeight / 2 - 200), 200, 25);
		panel.add(contactText);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		emailLabel.setBounds(590, (screenHeight / 2 - 200), 80, 25);
		panel.add(emailLabel);

		final JTextField emailText = new JTextField(20);
		emailText.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		emailText.setBounds(720, (screenHeight / 2 - 200), 200, 25);
		panel.add(emailText);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		userLabel.setBounds(150, (screenHeight / 2 - 150), 160, 25);
		panel.add(userLabel);

		final JTextField userText = new JTextField(20);
		userText.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		userText.setBounds(280, (screenHeight / 2 - 150), 200, 25);
		panel.add(userText);
		
		JLabel userRole_label = new JLabel("User Role ");
		userRole_label.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		userRole_label.setBounds(590, (screenHeight / 2 -150), 90, 25);
		panel.add(userRole_label);
		
		String roleList[] = create_user_role.split(","); 
		final JComboBox role_combo = new JComboBox(roleList);
		role_combo.setFont(new Font("Book Antiqua", Font.BOLD, 13));
		role_combo.setBounds(720, (screenHeight / 2 - 150), 200, 25);
		panel.add(role_combo);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		passwordLabel.setBounds(150, (screenHeight / 2 - 100), 90, 25);
		panel.add(passwordLabel);

		final JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(280, (screenHeight / 2 - 100), 200, 25);
		panel.add(passwordText);
		
		JLabel confPasswordLabel = new JLabel("Confirm Password");
		confPasswordLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		confPasswordLabel.setBounds(530, (screenHeight / 2 - 100), 180, 25);
		panel.add(confPasswordLabel);

		final JPasswordField confPasswordText = new JPasswordField(20);
		confPasswordText.setBounds(720, (screenHeight / 2 - 100), 200, 25);
		panel.add(confPasswordText);
		
/*		JLabel secretQuest1Label = new JLabel("Secret Question 1");
		secretQuest1Label.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		secretQuest1Label.setBounds(100, (screenHeight / 2 - 50), 200, 25);
		panel.add(secretQuest1Label);

		String[] secret_QuestList_1 = secret_quest_1.split(","); 
	    final JComboBox secretQuest1combo = new JComboBox(secret_QuestList_1);
	    secretQuest1combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    secretQuest1combo.setBounds(280, (screenHeight / 2 - 50), 600, 25);
	    panel.add(secretQuest1combo);
		
		JLabel secretAns1Label = new JLabel("Secret Answer 1");
		secretAns1Label.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		secretAns1Label.setBounds(100, (screenHeight / 2 - 0), 200, 25);
		panel.add(secretAns1Label);

		final JPasswordField secretAns1Text = new JPasswordField(20);
		secretAns1Text.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		secretAns1Text.setBounds(280, (screenHeight / 2 - 0), 200, 25);
		panel.add(secretAns1Text);
		
		JLabel secretQuest2Label = new JLabel("Secret Question 2");
		secretQuest2Label.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		secretQuest2Label.setBounds(100, (screenHeight / 2 + 50), 200, 25);
		panel.add(secretQuest2Label);

		String[] secret_QuestList_2 = secret_quest_2.split(","); 
	    final JComboBox secretQuest2combo = new JComboBox(secret_QuestList_2);
	    secretQuest2combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    secretQuest2combo.setBounds(280, (screenHeight / 2 + 50), 600, 25);
	    panel.add(secretQuest2combo);
		
		JLabel secretAns2Label = new JLabel("Secret Answer 2");
		secretAns2Label.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		secretAns2Label.setBounds(100, (screenHeight / 2 + 100), 200, 25);
		panel.add(secretAns2Label);

		final JPasswordField secretAns2Text = new JPasswordField(20);
		secretAns2Text.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		secretAns2Text.setBounds(280, (screenHeight / 2 + 100), 200, 25);
		panel.add(secretAns2Text);
		
		JLabel secretQuest3Label = new JLabel("Secret Question 3");
		secretQuest3Label.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		secretQuest3Label.setBounds(100, (screenHeight / 2 + 150), 200, 25);
		panel.add(secretQuest3Label);

		String[] secret_QuestList_3 = secret_quest_3.split(","); 
	    final JComboBox secretQuest3combo = new JComboBox(secret_QuestList_3);
	    secretQuest3combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    secretQuest3combo.setBounds(280, (screenHeight / 2 + 150), 600, 25);
	    panel.add(secretQuest3combo);
		
		JLabel secretAns3Label = new JLabel("Secret Answer 3");
		secretAns3Label.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		secretAns3Label.setBounds(100, (screenHeight / 2 + 200), 200, 25);
		panel.add(secretAns3Label);

		final JPasswordField secretAns3Text = new JPasswordField(20);
		secretAns3Text.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		secretAns3Text.setBounds(280, (screenHeight / 2 + 200), 200, 25);
		panel.add(secretAns3Text);*/

        ///home//////////////
		final ImageIcon iconhome = new ImageIcon(img_path + img_home);
        JLabel labelhome = new JLabel(iconhome);
        labelhome.setBounds(45, 10, 60, 60);
        panel.add(labelhome);
        
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
        
        final ImageIcon iconsubmit = new ImageIcon(img_path + img_submit);
        JLabel labelsubmit = new JLabel(iconsubmit);
        labelsubmit.setBounds((screenWidth / 2 - 60), (screenHeight / 2 + 250), 104, 34);
        panel.add(labelsubmit);
        
		/*JButton submitButton = new JButton("Submit") {
			*//**
			 * for background image
			 *//*
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				Image img = new ImageIcon(img_path+"submit.jpg").getImage();
				Dimension size = new Dimension(100, 33);
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				g.drawImage(img, 0, 0, null);
			}
		};
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//		submitButton.setBounds((screenWidth / 2), (screenHeight / 2 + 250), 80, 25);
		submitButton.setBounds((screenWidth / 2), (screenHeight / 2 - 0), 80, 25);
		panel.add(submitButton);*/

        labelsubmit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				String firstName 	= "";
				String lastName 	= "";
				String userName 	= "";
				String password 	= "";
				String confPass 	= "";
				String userRole		= "";
				String contact		= "";
				String email		= "";
				String question1	= "";
				String question2	= "";
				String question3	= "";
				String answer1		= "";
				String answer2		= "";
				String answer3		= "";
				boolean validFlag	= true;
				
				firstName 			= firstText.getText();
				lastName 			= lastText.getText();
				userName 			= userText.getText();
				password			= new String(passwordText.getPassword());
				confPass			= new String(confPasswordText.getPassword());
				userRole 	 		= (String) role_combo.getSelectedItem();
				contact 			= contactText.getText();
				email 				= emailText.getText();
				/*question1 	 		= (String) secretQuest1combo.getSelectedItem();
				question2 	 		= (String) secretQuest2combo.getSelectedItem();
				question3 	 		= (String) secretQuest3combo.getSelectedItem();
				answer1 			= new String(secretAns1Text.getPassword());
				answer2 			= new String(secretAns2Text.getPassword());
				answer3 			= new String(secretAns3Text.getPassword());*/

				if(firstName.length() > 40)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("First Name",firstName, 40));
				}
				else if(commonObj.checkComma(firstName) || firstName.equalsIgnoreCase("") || commonObj.validateSpecial(firstName) || !commonObj.validateAlphabets(firstName))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid First Name");
				}
				else if(lastName.length() > 40)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("Last Name",lastName, 40));
				}
				else if(commonObj.checkComma(lastName) || lastName.equalsIgnoreCase("") || commonObj.validateSpecial(lastName) || !commonObj.validateAlphabets(lastName))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid Last Name");
				}
				else if(!commonObj.validateNumber(contact) || contact.equalsIgnoreCase(""))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid Contact");
				}
				else if(contact.length() < 6)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Contact number must be more than 5 digit");
				}
				else if(contact.length() > 20)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("Contact",contact, 20));
				}
				else if(!commonObj.validateEmail(email) || email.equalsIgnoreCase("") || commonObj.checkComma(email))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid Email Id");
				}
				else if(email.length() > 99)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("Email Id",email, 99));
				}
				else if(userName.length() > 40)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("User Name",userName, 40));
				}
				else if(commonObj.checkComma(userName) || userName.equalsIgnoreCase(""))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid User Name without comma");
				}
				else if(commonObj.checkComma(password) || password.equalsIgnoreCase(""))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid Password");
				}
				else if(!commonObj.validatePassword(password))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Password must contain one digit, one lower case char, length should be within 6 to 15 chars");
				}
				else if(!password.contentEquals(confPass))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Password & confirm password are different");
				}
				/*else if(answer1.equalsIgnoreCase("") || commonObj.checkComma(answer1))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid answer for question 1");
				}
				else if(answer1.length() > 30)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("Answer 1",answer1, 30));
				}
				else if(answer2.equalsIgnoreCase("") || commonObj.checkComma(answer2))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid answer for question 2");
				}
				else if(answer2.length() > 30)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("Answer 2",answer2, 30));
				}
				else if(answer3.equalsIgnoreCase("") || commonObj.checkComma(answer3))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid answer for question 3");
				}
				else if(answer3.length() > 30)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("Answer 3",answer3, 30));
				}*/
				if (validFlag) {
					boolean userCreated = false;
					boolean validateStatus = false;
					try {
			            if(dbValidate.connectDatabase(sessionData)){
				            validateStatus = dbValidate.validateUser(sessionData, userName, password, userRole);
				            if(!validateStatus){
					            userCreated = dbValidate.createUser(sessionData, firstName, lastName, userName, password, userRole, contact, email,
	        									question1, answer1, question2, answer2, question3, answer3, "NEW");
					            if(userCreated){
					            	commonObj.showMessageDialog("Username " +userName+ " created successfully");
					            	frame.setVisible(false);
					            	new CreateUser(sessionData, user_name, user_role);
					            } else {
					            	commonObj.showMessageDialog("Username " +userName+ " creation failed");
					            }
				            } else {
				            	commonObj.showMessageDialog("Username " +userName+ " already exists please try different");
				            }
			            }
					} catch (Exception e1) {
						logger.info("create user failed Exception e1 ===>>>" + e1);
						commonObj.showMessageDialog("Create user failed...");
					} finally {
			        	dbValidate.closeDatabase(sessionData);
			        }
				}
			}
        });
        
		/*submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName 	= "";
				String lastName 	= "";
				String userName 	= "";
				String password 	= "";
				String confPass 	= "";
				String userRole		= "";
				String contact		= "";
				String email		= "";
				String question1	= "";
				String question2	= "";
				String question3	= "";
				String answer1		= "";
				String answer2		= "";
				String answer3		= "";
				boolean validFlag	= true;
				
				firstName 			= firstText.getText();
				lastName 			= lastText.getText();
				userName 			= userText.getText();
				password			= new String(passwordText.getPassword());
				confPass			= new String(confPasswordText.getPassword());
				userRole 	 		= (String) role_combo.getSelectedItem();
				contact 			= contactText.getText();
				email 				= emailText.getText();
				question1 	 		= (String) secretQuest1combo.getSelectedItem();
				question2 	 		= (String) secretQuest2combo.getSelectedItem();
				question3 	 		= (String) secretQuest3combo.getSelectedItem();
				answer1 			= new String(secretAns1Text.getPassword());
				answer2 			= new String(secretAns2Text.getPassword());
				answer3 			= new String(secretAns3Text.getPassword());

				if(firstName.length() > 40)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("First Name",firstName, 40));
				}
				else if(commonObj.checkComma(firstName) || firstName.equalsIgnoreCase("") || commonObj.validateSpecial(firstName) || !commonObj.validateAlphabets(firstName))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid First Name");
				}
				else if(lastName.length() > 40)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("Last Name",lastName, 40));
				}
				else if(commonObj.checkComma(lastName) || lastName.equalsIgnoreCase("") || commonObj.validateSpecial(lastName) || !commonObj.validateAlphabets(lastName))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid Last Name");
				}
				else if(!commonObj.validateNumber(contact) || contact.equalsIgnoreCase(""))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid Contact");
				}
				else if(contact.length() < 6)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Contact number must be more than 5 digit");
				}
				else if(contact.length() > 20)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("Contact",contact, 20));
				}
				else if(!commonObj.validateEmail(email) || email.equalsIgnoreCase("") || commonObj.checkComma(email))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid Email Id");
				}
				else if(email.length() > 99)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("Email Id",email, 99));
				}
				else if(userName.length() > 40)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("User Name",userName, 40));
				}
				else if(commonObj.checkComma(userName) || userName.equalsIgnoreCase(""))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid User Name without comma");
				}
				else if(commonObj.checkComma(password) || password.equalsIgnoreCase(""))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid Password");
				}
				else if(!commonObj.validatePassword(password))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Password must contain one digit, one lower case char, length should be within 6 to 15 chars");
				}
				else if(!password.contentEquals(confPass))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Password & confirm password are different");
				}
				else if(answer1.equalsIgnoreCase("") || commonObj.checkComma(answer1))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid answer for question 1");
				}
				else if(answer1.length() > 30)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("Answer 1",answer1, 30));
				}
				else if(answer2.equalsIgnoreCase("") || commonObj.checkComma(answer2))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid answer for question 2");
				}
				else if(answer2.length() > 30)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("Answer 2",answer2, 30));
				}
				else if(answer3.equalsIgnoreCase("") || commonObj.checkComma(answer3))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "Please enter valid answer for question 3");
				}
				else if(answer3.length() > 30)
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, commonObj.charExceeded("Answer 3",answer3, 30));
				}
				if (validFlag) {
					boolean userCreated = false;
					boolean validateStatus = false;
					try {
			            if(dbValidate.connectDatabase(sessionData)){
				            validateStatus = dbValidate.validateUser(sessionData, userName, password, userRole);
				            if(!validateStatus){
					            userCreated = dbValidate.createUser(sessionData, firstName, lastName, userName, password, userRole, contact, email,
	        									question1, answer1, question2, answer2, question3, answer3, "NEW");
					            if(userCreated){
					            	commonObj.showMessageDialog("Username " +userName+ " created successfully");
					            	frame.setVisible(false);
					            	new CreateUser(user_name, user_role);
					            } else {
					            	commonObj.showMessageDialog("Username " +userName+ " creation failed");
					            }
				            } else {
				            	commonObj.showMessageDialog("Username " +userName+ " already exists please try different");
				            }
			            }
					} catch (Exception e1) {
						logger.info("create user failed Exception e1 ===>>>" + e1);
						commonObj.showMessageDialog("Create user failed...");
					} finally {
			        	dbValidate.closeDatabase(sessionData);
			        }
				}
			}

		});*/

//		final JLabel forgetLabel = new JLabel("<html><a href=\" " + forgetPass
//				+ "\"> Forget Password ? </a></html>");
//		forgetLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//		forgetLabel.setBounds((screenWidth / 2 - 15), (screenHeight / 2 + 200), 200, 30);
//		panel.add(forgetLabel);
//		forgetLabel.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent me) {
//				try {
//					frame.setVisible(false);
//					new Welcome(username,role);
//				} catch (Exception e) {
//					logger.info(e);
//				}
//			}
//		});
		
//		final JLabel CreateUserLabel = new JLabel("<html><a href=\" " + createUser
//				+ "\"> Create New User ? </a></html>");
//		CreateUserLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//		CreateUserLabel.setBounds((screenWidth / 2 - 180), (screenHeight / 2 +200), 200, 30);
//		panel.add(CreateUserLabel);
//		CreateUserLabel.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent me) {
//				try {
//					frame.setVisible(false);
//					new Welcome(username,role);
//				} catch (Exception e) {
//					logger.info(e);
//				}
//			}
//		});
	}

}
