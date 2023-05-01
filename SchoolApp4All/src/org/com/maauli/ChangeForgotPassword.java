package org.com.maauli;

import org.apache.log4j.Logger;

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


public class ChangeForgotPassword {
	static int screenWidth;
	static int screenHeight;
	static JFrame frame = null;
	final static String forgetPass  = "Forget Password";
	final static String createUser  = "Create New User";
	static String img_path			= "";
	static String img_home 			= "";
	static String img_logout 		= "";
	static String img_submit 		= "";
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
    

    public ChangeForgotPassword(SessionData sessionData1)
    {
    	System.gc();
    	sessionData = sessionData1;
    	user_name = sessionData1.getUserName();
        user_role = sessionData1.getUserRole();

		frame = new JFrame("Application Name");
		screenWidth = commonObj.screeWidth();
		screenHeight = commonObj.screeHeight();

		frame.setSize(screenWidth, screenHeight);
		frame.setResizable(false);
		
		img_path	= bundle.getString("IMAGE_PATH");
    	logger.info("img_path :: "+img_path);
//    	img_home = bundle.getString("IMAGE_HOME");
    	img_logout = bundle.getString("IMAGE_LOGOUT");
    	img_submit = bundle.getString("IMAGE_SUBMIT");
    	
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
		JLabel headLabel = new JLabel("Forgot Password");
		logger.info("=====Forgot Password====");

		headLabel.setFont(new Font("Book Antiqua", Font.BOLD, 30));
		headLabel.setBounds((screenWidth / 2 - 150), 30, 300, 40);
		panel.add(headLabel);

		/*JLabel firstLabel = new JLabel("First Name");
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
		panel.add(emailText);*/
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		userLabel.setBounds(100, (screenHeight / 2 - 250), 150, 25);
		panel.add(userLabel);

		final JTextField userText = new JTextField(user_name);
		userText.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		userText.setBounds(280, (screenHeight / 2 - 250), 200, 25);
		userText.setEditable(false);
		panel.add(userText);
		
		/*JLabel userRole_label = new JLabel("User Role ");
		userRole_label.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		userRole_label.setBounds(590, (screenHeight / 2 -150), 90, 25);
		panel.add(userRole_label);
		
		String roleList[] = create_user_role.split(","); 
		final JComboBox role_combo = new JComboBox(roleList);
		role_combo.setFont(new Font("Book Antiqua", Font.BOLD, 13));
		role_combo.setBounds(720, (screenHeight / 2 - 150), 200, 25);
		panel.add(role_combo);*/

/*		JLabel oldPasswordLabel = new JLabel("Old Password");
		oldPasswordLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		oldPasswordLabel.setBounds(100, (screenHeight / 2 - 200), 150, 25);
		panel.add(oldPasswordLabel);

		final JPasswordField oldPasswordText = new JPasswordField(20);
		oldPasswordText.setBounds(280, (screenHeight / 2 - 200), 200, 25);
		panel.add(oldPasswordText);
		*/
		JLabel passwordLabel = new JLabel("New Password");
		passwordLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		passwordLabel.setBounds(100, (screenHeight / 2 - 150), 160, 25);
		panel.add(passwordLabel);

		final JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(280, (screenHeight / 2 - 150), 200, 25);
		panel.add(passwordText);
		
		JLabel confPasswordLabel = new JLabel("Confirm Password");
		confPasswordLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		confPasswordLabel.setBounds(100, (screenHeight / 2 - 100), 160, 25);
		panel.add(confPasswordLabel);

		final JPasswordField confPasswordText = new JPasswordField(20);
		confPasswordText.setBounds(280, (screenHeight / 2 - 100), 200, 25);
		panel.add(confPasswordText);
		/*
		JLabel secretQuest1Label = new JLabel("Secret Question 1");
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
		panel.add(secretAns3Text);
*/
        // /home//////////////
/*        JButton homeButton = new JButton("home") {

            *//**
             * for background image
             *//*
            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {

                System.out.println("img_path + img_home" + img_path + img_home);
                Image img = new ImageIcon(img_path + img_home).getImage();
                Dimension size = new Dimension(60, 60);
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
                g.drawImage(img, 0, 0, null);
            }
        };
        homeButton.setFont(new Font("Book Antiqua", Font.BOLD, 15));
        homeButton.setBounds(45, 10, 30, 30);
        panel.add(homeButton);
        
        homeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
                new Welcome(user_name, user_role);
               
            }
        });*/
		
        // /logout//////////////
        final ImageIcon iconlogout = new ImageIcon(img_path + img_logout);
        JLabel labellogout = new JLabel(iconlogout);
        labellogout.setBounds((screenWidth - 80), 10, 60, 60);
        panel.add(labellogout);
        
        labellogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				boolean deleteFlag = false;
				try {
					if (dbValidate.connectDatabase(sessionData)) {
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role, "");
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
		submitButton.setBounds((screenWidth / 2), (screenHeight / 2 + 250), 80, 25);
//		submitButton.setBounds((screenWidth / 2), (screenHeight / 2 - 0), 80, 25);
		panel.add(submitButton);*/
        
        final ImageIcon iconsubmit = new ImageIcon(img_path + img_submit);
        JLabel labelsubmit = new JLabel(iconsubmit);
        labelsubmit.setBounds((screenWidth / 2 - 60), (screenHeight / 2 + 250), 104, 34);
        panel.add(labelsubmit);

        labelsubmit.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent me) {
				String userName 	= "";
				String password 	= "";
				String confPass 	= "";
				boolean validFlag	= true;
				
				userName 			= userText.getText();
				password			= new String(passwordText.getPassword());
				confPass			= new String(confPasswordText.getPassword());

				if(userName.length() > 40)
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
					JOptionPane.showMessageDialog(null, "Please enter valid New Password");
				}
				else if(!commonObj.validatePassword(password))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "New Password must contain one digit, one lower case char, length should be within 6 to 15 chars");
				}
				else if(!password.contentEquals(confPass))
				{
					validFlag = false;
					JOptionPane.showMessageDialog(null, "New Password & confirm password are different");
				}
				if (validFlag) {
					boolean passwordUpdated = false;
					boolean validateUser = false;
					try {
						if(dbValidate.connectDatabase(sessionData)){
			            	passwordUpdated = dbValidate.updateForgotPassword(sessionData, userName, password);
				            if(passwordUpdated){
				            	commonObj.showMessageDialog("Password for " +userName+ " updated successfully");
				            } else {
				            	commonObj.showMessageDialog("Password update for " +userName+ " failed. Please try again");
				            }
				            frame.setVisible(false);
			            	String[] arguments = new String[] {""};
			                LoginView.main(arguments);
						}
					} catch (Exception e1) {
						logger.info("update password failed Exception e1 ===>>>" + e1);
						commonObj.showMessageDialog("Update Password failed...");
					} finally {
			        	dbValidate.closeDatabase(sessionData);
			        }
				}
			}
		});

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
