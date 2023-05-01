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
import java.io.File;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class Welcome
    extends JFrame {

    static int screenWidth;

    static int screenHeight;

    static int mainCentre;

    static JFrame frame = null;

    static JComboBox studentcombo;

    static String img_path = "";

    static String img_home = "";
    
    static String img_founder = "";

    static String img_logo = "";

    static String img_myaccount = "";

    static String img_logout = "";

    static String img_titleband = "";

    static String img_leftband = "";

    static String img_menuband = "";

    static String img_mainband = "";
    
    static String copyRightReserved = "� Rights reserved to Maauli Software Solutions";
    
    final static String createUser  = "Create New User";

    static DBValidate dbValidate = new DBValidate();
    
    static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

    static Logger logger = Logger.getLogger(Welcome.class.getName());
    
    static Common commonObj = new Common();

    static SessionData sessionData = new SessionData();
    
    static String jrc_required = "";
    
    static String sch_required = "";
    
    static String schigh_required = "";
    
    static String scpri_required = "";
    
    static String scppr_required = "";

    static String user_name = "";

    static String user_role = "";
    
    static String app_header = "";
    
    static String app_header_0 = "";
    
    static String show_founder = "";
    
    static String show_donatedby = "";
    
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
    static String url = "";
    static String jrc_label,high_label,pri_label,ppr_label = "";
    
    public Welcome(SessionData sessionData1, String retUserName, String retRole) {

    	System.gc();
    	
    	/////schedule to check undelivered message//////////
		String sms_required = bundle.getString("SMS_REQUIRED");
		if(sms_required.equalsIgnoreCase("true")){
			Timer time = new Timer(); // Instantiate Timer Object
			ScheduledTask st = new ScheduledTask(dbValidate, sessionData1, commonObj.getAcademicYear(commonObj.getCurrentDate()),"updateUndeliveredSms"); // Instantiate SheduledTask class
			time.schedule(st, 0, 1000 * 60 * 60);//every 60 minutes
		}
    		
		if(true){///schedule backup
			Timer time = new Timer(); // Instantiate Timer Object
			ScheduledTask st = new ScheduledTask(dbValidate, sessionData1, commonObj.getAcademicYear(commonObj.getCurrentDate()),"backupDatabase"); // Instantiate SheduledTask class
			time.schedule(st, 0, 1000 * 60 * 60);//every 60 minutes
		}
		
    	sessionData = sessionData1;
        user_name = retUserName;
        user_role = retRole;
        setVisible(false);
        dispose();
        frame = new JFrame("Welcome to "+sessionData1.getAppName());
        img_path = bundle.getString("IMAGE_PATH");
        logger.info("img_path :: " + img_path);
        img_home = bundle.getString("IMAGE_HOME");
        img_founder = bundle.getString("IMGAGE_FOUNDER");
        img_logo = bundle.getString("IMAGE_LOGO");
        img_myaccount = bundle.getString("IMAGE_MYACCOUNT");
        img_logout = bundle.getString("IMAGE_LOGOUT");
        img_titleband = bundle.getString("IMAGE_TITLEBAND");
        img_leftband = bundle.getString("IMAGE_LEFTBAND");
        img_menuband = bundle.getString("IMAGE_MENUBAND");
        img_mainband = bundle.getString("IMAGE_MAINBAND");
        sms_required = bundle.getString("SMS_REQUIRED");
        url = bundle.getString("DBURL_"+sessionData1.getDBName());
        
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
    	
        jrc_required = bundle.getString("JRC_REQUIRED");
        sch_required = bundle.getString("SCH_REQUIRED");
        schigh_required = bundle.getString("SCHIGH_REQUIRED");
        scpri_required = bundle.getString("SCPRI_REQUIRED");
        scppr_required = bundle.getString("SCPPR_REQUIRED");
        show_founder = bundle.getString("SHOW_FOUNDER");
        show_donatedby = bundle.getString("SHOW_DONATEDBY");
        jrc_label = bundle.getString("JRC_LABEL");
        high_label = bundle.getString("HIGH_LABEL");
        pri_label = bundle.getString("PRI_LABEL");
        ppr_label = bundle.getString("PPR_LABEL");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        JLabel label = new JLabel(icon);
        // label.setFont(new Font("Book Antiqua", Font.BOLD, 30));
        label.setBounds(45, 0, 75, 75);
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
        
        JButton backupButton = new JButton("Backup");
		backupButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		backupButton.setBounds(10, 200, 130, 35);
//		if(user_role.equalsIgnoreCase("ADMINISTRATOR") && url.contains("127.0.0.1")){
			leftPanel.add(backupButton);
//		}
		backupButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int reply = 0;
					reply = JOptionPane.showConfirmDialog(null, "Would You Like to take backup?", "Confirm validate", JOptionPane.YES_NO_OPTION);
					
					if (reply == JOptionPane.YES_OPTION) {
						String bckPath = commonObj.getDriveName() + bundle.getString("BACKUP_PATH_"+sessionData.getDBName())+commonObj.getCurrentDatein_dd_MMM_yyyy();
						if (dbValidate.connectDatabase(sessionData)) {
							File file = new File(bckPath+".zip");
							if (file.exists()) {
								bckPath = bckPath+"_latest";
							}
						    commonObj.deleteFolder(bckPath);
						    commonObj.deleteFile(bckPath+".zip");
						    dbValidate.backupToSQLFile(sessionData,bckPath, true);
//							dbValidate.BackupTables(sessionData,bckPath);
						    if(!(System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
								commonObj.CreatePasswordProtectedZip(bckPath,"");
								commonObj.deleteFolder(bckPath);
						    }
							commonObj.showMessageDialog("Backup completed");
						}
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
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

         /*JLabel menuBandTitle = new JLabel("Pre Primary");
         menuBandTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
         menuBandTitle.setForeground(Color.orange);
         menuBandTitle.setBounds(mainCentre-100, 0, 400, 30);
         topbandPanel.add(menuBandTitle);*/
        if(user_role.equalsIgnoreCase("ADMINISTRATOR")){
	        final JLabel CreateUserLabel = new JLabel("<html><a style=color:#FFFF66 href=\" " + createUser
					+ "\"> Create New User ? </a></html>");
			CreateUserLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
			CreateUserLabel.setBounds((screenWidth - 350), 45, 400, 30);
			topbandPanel.add(CreateUserLabel);
			CreateUserLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					try {
						new CreateUser(sessionData, user_name, user_role);
						frame.setVisible(false);
					} catch (Exception e) {
						logger.info(e);
					}
				}
			});
        }

        mainPanel.add(topbandPanel, BorderLayout.NORTH);

        // ///////////bottom band/////////////
        JPanel bottombandPanel = new JPanel() {

            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {

                Image img = new ImageIcon(img_path + img_mainband).getImage();
                Dimension size =
                    new Dimension(screenWidth - 150, screenHeight - 150);
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
                g.drawImage(img, 0, 0, null);
            }
        };
        bottombandPanel.setLayout(null);

        int tabHeight = 150;
        
        ///founder//////////////
        final ImageIcon iconfounder = new ImageIcon(img_path + img_founder);
        JLabel labelfounder = new JLabel(iconfounder);
        labelfounder.setBounds(mainCentre - 160, -30, 315, 417);
        if(show_founder.equalsIgnoreCase("true")){
        	tabHeight = tabHeight + 180;
        	bottombandPanel.add(labelfounder);
        }
        
        if(sch_required.equalsIgnoreCase("true") && scppr_required.equalsIgnoreCase("true") && !sessionData.getDBName().contains("COLLEGE")){
	        JButton prePrimaryButton = new JButton(ppr_label);
	        prePrimaryButton.setFont(new Font("Book Antiqua", Font.BOLD, 25));
	        prePrimaryButton.setBounds(mainCentre - 125, tabHeight, 250, 35);
	        tabHeight = tabHeight + 50;
	        bottombandPanel.add(prePrimaryButton);
	
	        prePrimaryButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	            	try {
						if (dbValidate.connectDatabase(sessionData)) {
			                sessionData.setSectionName("PPR");
			                sessionData.setSectionLabel(ppr_label);
			                dbValidate.updateOptionalSubject(sessionData);
			                
			                /*if(sms_required.equalsIgnoreCase("true")){
			                	Timer time = new Timer(); // Instantiate Timer Object
			        			ScheduledTask st = new ScheduledTask(dbValidate, sessionData, commonObj.getAcademicYear(commonObj.getCurrentDate())); // Instantiate SheduledTask class
			        			time.schedule(st, 0, 1000 * 60 * 60);//every 60 minutes
//				                Timer timer = new Timer();
//				        		timer.schedule(new MyTask(dbValidate, sessionData, commonObj.getAcademicYear(commonObj.getCurrentDate())), 0, 1000 * 60 * 60);
			                }*/
			                
			                frame.setVisible(false);
			                new Section(sessionData, "PPR", user_name, user_role);
						}
					} catch (Exception e1) {
						commonObj.logException(e1);
					} finally {
						dbValidate.closeDatabase(sessionData);
					}
	            }
	        });
        }
        
        if(sch_required.equalsIgnoreCase("true") && scpri_required.equalsIgnoreCase("true") && !sessionData.getDBName().contains("COLLEGE")){
	        JButton primaryButton = new JButton(pri_label);
	        primaryButton.setFont(new Font("Book Antiqua", Font.BOLD, 25));
	        primaryButton.setBounds(mainCentre - 125, tabHeight, 250, 35);
	        tabHeight = tabHeight + 50;
	        bottombandPanel.add(primaryButton);
	
	        primaryButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	            	try {
						if (dbValidate.connectDatabase(sessionData)) {
							sessionData.setSectionName("PRI");
							sessionData.setSectionLabel(pri_label);
							dbValidate.updateOptionalSubject(sessionData);
							
							/*if(sms_required.equalsIgnoreCase("true")){
								Timer time = new Timer(); // Instantiate Timer Object
			        			ScheduledTask st = new ScheduledTask(dbValidate, sessionData, commonObj.getAcademicYear(commonObj.getCurrentDate())); // Instantiate SheduledTask class
			        			time.schedule(st, 0, 1000 * 60 * 60);//every 60 minutes
//				                Timer timer = new Timer();
//				        		timer.schedule(new MyTask(dbValidate, sessionData, commonObj.getAcademicYear(commonObj.getCurrentDate())), 0, 1000 * 60 * 60);
			                }*/
							
			                frame.setVisible(false);
			                new Section(sessionData, "PRI", user_name, user_role);
						}
					} catch (Exception e1) {
						commonObj.logException(e1);
					} finally {
						dbValidate.closeDatabase(sessionData);
					}
	            }
	        });
        }
        if(sch_required.equalsIgnoreCase("true") && schigh_required.equalsIgnoreCase("true") && !sessionData.getDBName().contains("COLLEGE")){
	        JButton highSchoolButton = new JButton(high_label);
	        highSchoolButton.setFont(new Font("Book Antiqua", Font.BOLD, 25));
	        highSchoolButton.setBounds(mainCentre - 125, tabHeight, 250, 35);
	        tabHeight = tabHeight + 50;
	        bottombandPanel.add(highSchoolButton);
	
	        highSchoolButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	
	            	try {
						if (dbValidate.connectDatabase(sessionData)) {
							sessionData.setSectionName("HIGH");
							sessionData.setSectionLabel(high_label);
							dbValidate.updateOptionalSubject(sessionData);
							
							/*if(sms_required.equalsIgnoreCase("true")){
								Timer time = new Timer(); // Instantiate Timer Object
			        			ScheduledTask st = new ScheduledTask(dbValidate, sessionData, commonObj.getAcademicYear(commonObj.getCurrentDate())); // Instantiate SheduledTask class
			        			time.schedule(st, 0, 1000 * 60 * 60);//every 60 minutes
//				                Timer timer = new Timer();
//				        		timer.schedule(new MyTask(dbValidate, sessionData, commonObj.getAcademicYear(commonObj.getCurrentDate())), 0, 1000 * 60 * 60);
			                }*/
							
			                frame.setVisible(false);
			                new Section(sessionData, "HIGH", user_name, user_role);
						}
					} catch (Exception e1) {
						commonObj.logException(e1);
					} finally {
						dbValidate.closeDatabase(sessionData);
					}
	            }
	        });
        }

        if(sessionData.getDBName().contains("COLLEGE")){
	        JButton juniorCollegeButton = new JButton(jrc_label);
	        juniorCollegeButton.setFont(new Font("Book Antiqua", Font.BOLD, 25));
	        juniorCollegeButton.setBounds(mainCentre - 125, tabHeight, 250, 35);
	        bottombandPanel.add(juniorCollegeButton);
	
	        juniorCollegeButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
//	            	sessionData.setDBName("COLLEGE");
	            	sessionData.setSectionLabel(jrc_label);
	                frame.setVisible(false);
	                new WelcomeJRC(sessionData,user_name,user_role);
	            }
	        });
        }
        
        JButton libraryButton = new JButton("Library");
        libraryButton.setFont(new Font("Book Antiqua", Font.BOLD, 25));
        libraryButton.setBounds(mainCentre - 100, 400, 200, 35);
        // bottombandPanel.add(libraryButton);
        
        libraryButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                // frame.setVisible(false);
                // new Welcome();
            }
        });

        if(show_donatedby.equalsIgnoreCase("true")){
        	String donatedByMessage1 = bundle.getString("DONATEDBYMESSAGE1");
	        JLabel donatedByLabel1 = new JLabel(donatedByMessage1);
	        donatedByLabel1.setForeground(Color.RED);
	        donatedByLabel1.setFont(new Font("Book Antiqua", Font.BOLD, 24));
	        donatedByLabel1.setBounds(mainCentre - 400, 460, 1100, 35);
	        bottombandPanel.add(donatedByLabel1);
	        
	        String donatedByMessage2 = bundle.getString("DONATEDBYMESSAGE2");
	        JLabel donatedByLabel2 = new JLabel(donatedByMessage2);
	        donatedByLabel2.setForeground(Color.RED);
	        donatedByLabel2.setFont(new Font("Book Antiqua", Font.BOLD, 24));
	        donatedByLabel2.setBounds(mainCentre - 400, 480, 1100, 35);
	        bottombandPanel.add(donatedByLabel2);
        }
        JLabel copyRight = new JLabel(copyRightReserved);
        copyRight.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        copyRight.setBounds(mainCentre - 200, 540, 1000, 35);
        bottombandPanel.add(copyRight);
        
        mainPanel.add(bottombandPanel, BorderLayout.SOUTH);

        panel.add(mainPanel, BorderLayout.EAST);

        /*
         * JButton primaryButton = new JButton("Results"); primaryButton.setFont(new
         * Font("Book Antiqua", Font.BOLD, 25)); primaryButton.setBounds(600,20, 200, 35);
         * topPanel.add(primaryButton);
         */
        // panel.add(topPanel);
        // panel.add(leftPanel);
    }
}

class MyTask extends TimerTask {
	public MyTask(DBValidate dbValidate, SessionData sessionData, String academicYear){
		try {
			JFrame f = new JFrame("Updating SMS delivery status. Please wait. Don't Close");
			f.setBounds(sessionData.getScreenWidth()/2 - 150, sessionData.getScreenHeight()/2, 90, 25);
		    f.setSize(500, 0);
		    f.setResizable(false);
		    f.setVisible(true);
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
			dbValidate.getUndeliveredSms(sessionData, academicYear);
			
			f.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void run() {
//		System.out.println("hello " + new Date());
	}
}
