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

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class Staff
    extends JFrame {

    static int screenWidth;

    static int screenHeight;

    static int mainCentre;

    static String section = "";

    static JFrame frame = null;

    static String secName = "";

    static JComboBox studentcombo;

    static String img_path = "";

    static String img_home = "";

    static String app_header = "";
    
    static String app_header_0 = "";
    
    static String img_logo = "";

    static String img_myaccount = "";

    static String img_logout = "";

    static String img_titleband = "";

    static String img_leftband = "";

    static String img_menuband = "";

    static String img_mainband = "";

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

    ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
    
    static SessionData sessionData = new SessionData();

    static Logger logger = Logger.getLogger(Student.class.getName());

    static DBValidate dbValidate = new DBValidate();

    public Staff(SessionData sessionData1, String sec, String retUserName, String retUserRole) {

    	System.gc();
    	sessionData = sessionData1;
        user_name = retUserName;
        user_role = retUserRole;
        section = sec;
        logger.info("section :: " + section);
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
        staffButton.setBackground(Color.GREEN);
//        leftPanel.add(staffButton);

        staffButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                // frame.setVisible(false);
                // new Staff(section);
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
//        leftPanel.add(accountButton);

        accountButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                // frame.setVisible(false);
                // new Welcome();
            }
        });

        JButton searchButton = new JButton("Find");
        searchButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        searchButton.setBounds(10, 300, 130, 35);
        leftPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            	LinkedHashMap<String,LinkedHashMap<String, String>> studMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
                new FindStudent(sessionData, "", "", "", "", "", "", studMap, section,
                    user_name, user_role, false);
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
        topbandPanel.add(staffRegButton);

        staffRegButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                new StaffRegisterForm(sessionData, section, user_name, user_role);
            }
        });

        JButton staffAttButton = new JButton("Attendance");
        staffAttButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        staffAttButton.setBounds(290, 50, 150, 24);// 620, 50, 150, 24
        topbandPanel.add(staffAttButton);

        staffAttButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

//                String userActive = "";
//                try {
//                	if(dbValidate.connectDatabase(sessionData)){
//	                    userActive =
//	                        dbValidate.checkFormData(sessionData, user_name,
//	                            "LEAVING CERTIFICATE", user_role, section);
//	                    dbValidate.closeDatabase(sessionData);
//	
//	                    if (userActive.equalsIgnoreCase(user_name)
//	                        || userActive.equalsIgnoreCase("")
//	                        || userActive.equalsIgnoreCase(null)) {
	                        List findLCList = new ArrayList();
	                        new FindLeavingCert(sessionData, "", "", "", "", "", "", findLCList,
	                            false, "", "", "", "", "", "", section, "", user_name, user_role, "", "");
	                        frame.setVisible(false);
//	                    } else if (!userActive.equalsIgnoreCase(user_name)) {
//	                        JOptionPane.showMessageDialog(null, "User "
//	                            + userActive
//	                            + " already using form LEAVING CERTIFICATE");
//	                    }
//                	}
//                } catch (Exception e1) {
//                    logger.info("Exception insertFormData ===>>>" + e1);
//                } finally {
//                    dbValidate.closeDatabase(sessionData);
//                }
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

        JButton serviceButton = new JButton("Service Updation");
        serviceButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        serviceButton.setBounds(610, 50, 150, 24); // 300, 50, 150, 24
        topbandPanel.add(serviceButton);

        serviceButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                // new Student();
                // frame.setVisible(false);
            }
        });

        JButton letterButton = new JButton("Letter Certificate");
        letterButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        letterButton.setBounds(770, 50, 150, 24);
        topbandPanel.add(letterButton);

        letterButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            	LinkedHashMap<String,LinkedHashMap<String, String>> studMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
                new FindStudent(sessionData, "", "", "", "", "", "", studMap, section,
                    user_name, user_role, false);
                frame.setVisible(false);
            }
        });

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

        mainPanel.add(bottombandPanel, BorderLayout.SOUTH);

        panel.add(mainPanel, BorderLayout.EAST);

    }
}
