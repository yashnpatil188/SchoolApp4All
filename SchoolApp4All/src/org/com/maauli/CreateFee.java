package org.com.maauli;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

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

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
// import com.lowagie.text.DocumentException;
// import com.lowagie.text.pdf.TextField;
import org.com.accesser.SessionData;

public class CreateFee
    extends JFrame {

    static int screenWidth;

    static int screenHeight;

    static int mainCentre;

    static JFrame frame = null;

    static JComboBox studentcombo;

    static List<String> foundStudentList = new ArrayList();

    static int retCount = 0;

    static int scrollHeight = 0;

    static int entrytCnt = 0;

    static int selectAllCount = 0;

    static List<String> subjectList = new ArrayList();

    static boolean setSelected = false;

    static boolean printDuplicate = false;

    static String grNoClass = "";

    static String stdClass = "";

    static String divClass = "";

    static String lastNameClass = "";

    static String firstNameClass = "";

    static String fatherNameClass = "";

    static String academicYearClass = "";

    static String rollFromClass = "";

    static String rollToClass = "";

    static String category = "";

    static String selectStd = "";

    static String user_name = "";

    static String user_role = "";

    static DBValidate dbValidate = new DBValidate();
    
    static SessionData sessionData 	= new SessionData();

    static Common commonObj = new Common();

    static String std = "";

    static String div = "";

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
    
    static List<String> fetchSubjectList = new ArrayList();
    static List<String> displaySubjectList = new ArrayList();
    
    static String lineLabel = "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
    static String lineLabel1 = "------------------------------------------------------------------------------------------------------------------------------------";

    ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

    static Logger logger = Logger.getLogger(CreateFee.class.getName());
	
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

    public CreateFee(SessionData sessionData1, String retCategory, String stdRet, String sec,
                         String retUserName, String retUserRole) {
    	System.gc();
    	sessionData = sessionData1;
        user_name = retUserName;
        user_role = retUserRole;
        selectStd = "Select";
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
        
        String todayDate = commonObj.getCurrentDate();
        academicYearClass = commonObj.getAcademicYear(todayDate);
        logger.info("academicYearClass ==" + academicYearClass);

        stdClass = stdRet;
        if (!stdRet.equalsIgnoreCase("")) {
            selectStd = stdRet;
        }
        // try {
        // retSubjectList = dbValidate.findSubTitleList();
        // } catch (Exception e) {
        // logger.info("Exception e = "+e);
        // }
        // ArrayList<String> arrlist = null;
        // arrlist = new ArrayList<String>(retSubjectList);
        // logger.info("arrlist => "+arrlist.size());

        // logger.info("retSubjectList size = "+retSubjectList.size());
        // subjectList = arrlist;
        // if(!retStd.equalsIgnoreCase("Select") && !retStd.equalsIgnoreCase("") &&
        // !academicYear.equalsIgnoreCase("Year") && !academicYear.equalsIgnoreCase(""))
        // {
        // logger.info("inside if...");
        // subjectList.add(stdClass+"|"+"New Subject"+"||"+"SELECT TITLE"+"|||"+"New Subject Title"+"||||"+"Marks"+"|||||"+"No");
        // }
        // logger.info("subjectList size = " +subjectList.size());
        //
        // stdClass = retStd;
        // academicYearClass = academicYear;
        // category = retCategory;
        // logger.info(stdClass+"::"+academicYearClass+"::"+category+"::"+retSelected);
        //
        // entrytCnt = 0;
        // logger.info("entrytCnt===>"+entrytCnt);

        try {
        	if(dbValidate.connectDatabase(sessionData)){
            	fetchSubjectList = dbValidate.fetchSubjectList(sessionData, stdClass, academicYearClass);
            	displaySubjectList.clear();
            	displaySubjectList.add("Subject|Title|Marks/Grade|Optional");
            	displaySubjectList.addAll(fetchSubjectList);
        	}

		} catch (Exception e1) {
            logger.info("Exception findSubTitleList ===>>>" + e1);
        } finally {
        	dbValidate.closeDatabase(sessionData);
        }
        
        scrollHeight = (displaySubjectList.size() - 6) * 30; // to adjust the perfect scroll height
        if (scrollHeight < 0)
            scrollHeight = 0;

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

    // public CreateSubject(String string, String string2, String string3,
    // String string4, String string5, String string6, Object object,
    // boolean b, String string7, String string8, String string9) {
    // // TODO Auto-generated constructor stub
    // }

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

        // /account//////////////
        JButton userAccountButton = new JButton("account") {

            /**
             * for background image
             */
            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {

                Image img = new ImageIcon(img_path + img_myaccount).getImage();
                Dimension size = new Dimension(69, 59);
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
                g.drawImage(img, 0, 0, null);
            }
        };
        userAccountButton.setFont(new Font("Book Antiqua", Font.BOLD, 15));
        userAccountButton.setBounds((screenWidth - 150), 10, 70, 60);
        titlePanel.add(userAccountButton);

        userAccountButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                // Call the main() method of MyClass
//                String[] arguments = new String[] {""};
//                LoginView.main(arguments);
//                frame.setVisible(false);
            }
        });

        // /logout//////////////
        JButton logoutButton = new JButton("Logout") {

            /**
             * for background image
             */
            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {

                Image img = new ImageIcon(img_path + img_logout).getImage();
                Dimension size = new Dimension(59, 64);
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
                g.drawImage(img, 0, 0, null);
            }
        };
        logoutButton.setFont(new Font("Book Antiqua", Font.BOLD, 15));
        logoutButton.setBounds((screenWidth - 80), 10, 60, 70);
        titlePanel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

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
        JButton homeButton = new JButton("home") {

            /**
             * for background image
             */
            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {

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
        leftPanel.add(homeButton);

        homeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                new Welcome(sessionData, user_name, user_role);
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
//        leftPanel.add(staffButton);

        staffButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            	new Welcome(sessionData, user_name, user_role);
                frame.setVisible(false);
            }
        });

        JButton academicButton = new JButton("Academic");
        academicButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        academicButton.setBounds(10, 200, 130, 35);
        academicButton.setBackground(Color.GREEN);
        leftPanel.add(academicButton);

        academicButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                new Academic(sessionData,section, user_name, user_role);
            }
        });

        JButton accountButton = new JButton("Account");
        accountButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        accountButton.setBounds(10, 250, 130, 35);
        accountButton.setBackground(Color.GREEN);
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
                new FindStudent(sessionData,"", "", "", "", "", "", studMap, section,
                    user_name, user_role, false);
                frame.setVisible(false);
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

        JLabel subMenuTitle = new JLabel("Academic");
        subMenuTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
        subMenuTitle.setForeground(Color.orange);
        subMenuTitle.setBounds(15, 45, 120, 30);
        topbandPanel.add(subMenuTitle);

        JButton classAllotButton = new JButton("Class Allotment");
        classAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        classAllotButton.setBounds(130, 50, 150, 24);
        topbandPanel.add(classAllotButton);

        classAllotButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                new ClassAllotment(sessionData, "", "", "", "", "", "", null, false, "", "",
                    "", section, user_name, user_role, "");
            }
        });

        JButton subAllotButton = new JButton("Subject Allotment");
        subAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        subAllotButton.setBounds(290, 50, 150, 24);// 620, 50, 150, 24
        subAllotButton.setBackground(Color.GREEN);
        topbandPanel.add(subAllotButton);

        subAllotButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                List subAllotList = new ArrayList();
                new MarksAllotment(sessionData, "", subAllotList, "", "", false, section,
                    user_name, user_role, "");
            }
        });

        JButton marksButton = new JButton("Marks Entry");
        marksButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        marksButton.setBounds(450, 50, 150, 24);
        topbandPanel.add(marksButton);

        marksButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                List markList = new ArrayList();
                new MarksEntry(sessionData, "", markList, "", false, "", "", "", "", "", "",
                    "", "", "", section, user_name, user_role, "", "");
            }
        });

        JButton resultPrintButton = new JButton("Result Print");
        resultPrintButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        resultPrintButton.setBounds(610, 50, 150, 24); // 300, 50, 150, 24
        topbandPanel.add(resultPrintButton);

        resultPrintButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                // new Student();
                // frame.setVisible(false);
            }
        });

        JButton attendanceButton = new JButton("Attendance");
		attendanceButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		attendanceButton.setBounds(770, 50, 150, 24); // 300, 50, 150, 24
		topbandPanel.add(attendanceButton);

		attendanceButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);
//				List studentList = new ArrayList();
//				new AttendanceUpdate(sessionData, "", "", "", section, user_name, user_role, studentList, "","","","",false,"");
				
			}
		});

        JButton reportButton = new JButton("Reports");
        reportButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        reportButton.setBounds(930, 50, 150, 24);
        topbandPanel.add(reportButton);

        reportButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                // List studList = new ArrayList();
                // frame.setVisible(false);
                // new FindStudent("","", "", "", "", "", studList,section);
            }
        });

        mainPanel.add(topbandPanel, BorderLayout.EAST);
        // mainPanel.add(bottombandPanel, BorderLayout.SOUTH);

        // /////////////////bottombandPanel area//////////////////////////
        // JPanel panelWorkable = new JPanel(new BorderLayout());

        JPanel bottombandPanel = new JPanel(new BorderLayout());
        bottombandPanel.setPreferredSize(new Dimension(screenWidth - 150,
            screenHeight - 187));

        // ///////////find Panel/////////////
        final JPanel findPanel = new JPanel() {

            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {

                Image img = new ImageIcon(img_path + img_mainband).getImage();
                Dimension size = new Dimension(screenWidth - 156, 150);// change height to change
                                                                       // scrolling area
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
                g.drawImage(img, 0, 0, null);
            }
        };
        findPanel.setLayout(null);
        // /////////////ACADEMIV YEAR //////////////
        JLabel academic_label = new JLabel("Academic Year :");
        academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        academic_label.setBounds(mainCentre - 340, 00, 150, 50);
        findPanel.add(academic_label);

        // JLabel year_label = new JLabel(academicYearClass);
        // year_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        // year_label.setBounds(mainCentre-200, 12, 120, 25);
        // findPanel.add(year_label);

        String academicYearList[] = {academicYearClass};
        final JComboBox academicYear_combo = new JComboBox(academicYearList);
        academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        academicYear_combo.setBounds(mainCentre - 200, 12, 120, 25);
        academicYear_combo.setEnabled(true);
        findPanel.add(academicYear_combo);
        // //////////////////////////////////
        // /////////////Std//////////////
        JLabel admittedStd_label = new JLabel("Std :");
        admittedStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        admittedStd_label.setBounds(mainCentre - 50, 00, 70, 50);
        findPanel.add(admittedStd_label);

        std = selectStd + "," + std;
        String[] stdList = std.split(",");
        // String admittedStdList[] = {selectStd,"IV","V","VI","VII","VIII","IX","X"};
        final JComboBox admittedStd_combo = new JComboBox(stdList);
        admittedStd_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        admittedStd_combo.setBounds(mainCentre, 12, 90, 25);
        admittedStd_combo.setEditable(false);
        findPanel.add(admittedStd_combo);

        // /////////////Div//////////////
        JLabel presentDiv_label = new JLabel("Div :");
        presentDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        presentDiv_label.setBounds(mainCentre + 120, 00, 70, 50);
        findPanel.add(presentDiv_label);

        div = "Select," + div;
        String[] divList = div.split(",");
        // String presentDivList[] = {"Select","A","B","C","D","E"};
        final JComboBox presentDiv_combo = new JComboBox(divList);
        presentDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        presentDiv_combo.setBounds(mainCentre + 170, 12, 90, 25);
        findPanel.add(presentDiv_combo);

        // /////////////Create Subject//////////////
        final JRadioButton createSubject_radio = new JRadioButton();
        createSubject_radio.setBounds(mainCentre - 350, 55, 20, 20);
        findPanel.add(createSubject_radio);

        final JLabel createSubject_label = new JLabel("Create Subject");
        createSubject_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        createSubject_label.setBounds(mainCentre - 320, 40, 150, 50);
        findPanel.add(createSubject_label);

        // /////////////Subject Allotment//////////////
        final JRadioButton allotSubject_radio = new JRadioButton();
        allotSubject_radio.setBounds(mainCentre - 160, 55, 20, 20);
        findPanel.add(allotSubject_radio);

        final JLabel allotSubject_label = new JLabel("Marks Allotment");
        allotSubject_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        allotSubject_label.setBounds(mainCentre - 130, 40, 200, 50);
        findPanel.add(allotSubject_label);

        // /////////////Student Subject Allotment//////////////
        final JRadioButton studentSubject_radio = new JRadioButton();
        studentSubject_radio.setBounds(mainCentre + 70, 55, 20, 20);
        findPanel.add(studentSubject_radio);

        final JLabel studentSubject_label =
            new JLabel("Student Subject Allotment");
        studentSubject_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        studentSubject_label.setBounds(mainCentre + 100, 40, 200, 50);
        findPanel.add(studentSubject_label);

        // /////////////Default/Optional //////////////
        // String compOptionalList[] = {"Select","Compulsory","Optional"};
        // final JComboBox compOptional_combo = new JComboBox(compOptionalList);
        // compOptional_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        // compOptional_combo.setBounds(mainCentre+300, 52, 120, 25);
        // findPanel.add(compOptional_combo);

        createSubject_radio.setSelected(true);
        allotSubject_radio.setSelected(false);
        studentSubject_radio.setSelected(false);
        presentDiv_combo.setEnabled(false);
        // compOptional_combo.setEnabled(false);

        createSubject_radio.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (createSubject_radio.isSelected()) {
                    createSubject_radio.setSelected(true);
                    allotSubject_radio.setSelected(false);
                    studentSubject_radio.setSelected(false);
                    // academicYear_combo.setEnabled(false);
                    presentDiv_combo.setEnabled(false);
                    // compOptional_combo.setEnabled(false);
                    category = "Create Subject";
                } else {
                    category = "";
                }
            }
        });

        allotSubject_radio.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (allotSubject_radio.isSelected()) {
                    createSubject_radio.setSelected(false);
                    allotSubject_radio.setSelected(true);
                    studentSubject_radio.setSelected(false);
                    // academicYear_combo.setEnabled(true);
                    presentDiv_combo.setEnabled(false);
                    // compOptional_combo.setEnabled(false);
                    category = "Allot Subject";
                } else {
                    category = "";
                }
            }
        });

        studentSubject_radio.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (studentSubject_radio.isSelected()) {
                    createSubject_radio.setSelected(false);
                    allotSubject_radio.setSelected(false);
                    studentSubject_radio.setSelected(true);
                    // academicYear_combo.setEnabled(true);
                    presentDiv_combo.setEnabled(true);
                    // compOptional_combo.setEnabled(true);
                    category = "Student Subject Allotment";
                } else {
                    category = "";
                }
            }
        });
        // /////////////Submit//////////////
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        submitButton.setBounds(mainCentre - 250, 90, 130, 25);
        findPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                boolean validateFields = true;
                String std = "";
                String academicSel = "";
                // String compOptional = "";
                String div = "";

                std = (String) admittedStd_combo.getSelectedItem();
                academicSel = (String) academicYear_combo.getSelectedItem();
                div = (String) presentDiv_combo.getSelectedItem();
                // compOptional = (String) compOptional_combo.getSelectedItem();

                logger.info("std:" + std);
                logger.info("academicSel:" + academicSel);
                logger.info("div:" + div);
                // logger.info("compOptional:" +compOptional);

                if (createSubject_radio.isSelected()) {
                    category = "Create Subject";
                } else if (allotSubject_radio.isSelected()) {
                    category = "Allot Subject";
                } else if (studentSubject_radio.isSelected()) {
                    category = "Student Subject Allotment";
                }
                logger.info("category:" + category);

                if (academicSel.equalsIgnoreCase("Year")
                    && (category.equalsIgnoreCase("Allot Subject") || category.equalsIgnoreCase("Student Subject Allotment"))) {
                    validateFields = false;
                    JOptionPane.showMessageDialog(null,"Please select Academic year.");
                } else if (std.equalsIgnoreCase("Select")) {
                    validateFields = false;
                    JOptionPane.showMessageDialog(null, "Please select Std.");
                } else if (category.equalsIgnoreCase("Student Subject Allotment")
                    && div.equalsIgnoreCase("Select")) {
                    validateFields = false;
                    JOptionPane.showMessageDialog(null, "Please select Div");
                } else if (category.equalsIgnoreCase("")) {
                    validateFields = false;
                    JOptionPane.showMessageDialog(null,"Select a radio option.");
                }
                // else if((compOptional.equalsIgnoreCase("") ||
                // compOptional.equalsIgnoreCase("Select")) &&
                // category.equalsIgnoreCase("Student Subject Allotment"))
                // {
                // validateFields = false;
                // JOptionPane.showMessageDialog(null, "Please select Compulsory/Optional");
                // }

                if (!academicSel.equalsIgnoreCase("Year")) {
                    academicSel = academicSel;
                } else {
                    academicSel = "";
                }
                logger.info("std == " + std);
                logger.info("academicSel == " + academicSel);
                logger.info("validateFields flag..." + validateFields);
                if (validateFields
                    & category.equalsIgnoreCase("Create Subject")) {
                    try {
                        stdClass = std;
                        // academicYearClass = academicSel;
                        // category = category;
                        List subList = new ArrayList();
                        frame.setVisible(false);
                        new CreateFee(sessionData, category, stdClass, section, user_name, user_role);

                    } catch (Exception e1) {
                        logger.info("Exception e1 ===>>>" + e1);
                        new CreateFee(sessionData, category, stdClass, section, user_name, user_role);
                        frame.setVisible(false);
                    }
                } else if (validateFields & category.equalsIgnoreCase("Allot Subject")) {
                    List subAllotList = new ArrayList();
                    try {
                        stdClass = std;
                        // academicYearClass = academicSel;
                        // category = category;
                        List subList = new ArrayList();
                        frame.setVisible(false);
                        new MarksAllotment(sessionData, std, subAllotList,
                            academicYearClass, category, false, section, user_name, user_role, "");

                    } catch (Exception e1) {
                        logger.info("Exception e1 ===>>>" + e1);
                        new MarksAllotment(sessionData, std, subAllotList, academicSel, category, false, section, user_name, user_role, "");
                        frame.setVisible(false);
                    }
                } else if (validateFields
                    & category.equalsIgnoreCase("Student Subject Allotment")) {
                    List subList = new ArrayList();
                    try {
                        stdClass = std;
                        // academicYearClass = academicSel;
                        divClass = div;
                        // category = category;

                        frame.setVisible(false);
                        new StudentSubAllot(sessionData, stdClass, subList, academicYearClass, category, false, divClass,
                            section, "", "", "", user_name, user_role);

                    } catch (Exception e1) {
                        logger.info("Exception e1 ===>>>" + e1);
                        new StudentSubAllot(sessionData, stdClass, subList, academicYearClass, category, false, divClass,
                            section, "", "", "", user_name, user_role);
                        frame.setVisible(false);
                    }
                }
            }

        });
        // /////////////Reset//////////////
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        resetButton.setBounds(mainCentre - 75, 90, 130, 25);
        findPanel.add(resetButton);

        resetButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                logger.info("Cliked Reset in Create subject");
                frame.setVisible(false);
                new CreateFee(sessionData, "", "", section, user_name, user_role);
            }
        });

        bottombandPanel.add(findPanel, BorderLayout.EAST);
        // ////////////////find panel ends/////////////////////////////////

        // //////////scrollArea panel/////////////////////////////////////
        JPanel scrollAreaPanel = new JPanel(new BorderLayout());
        scrollAreaPanel.setPreferredSize(new Dimension(screenWidth - 150,
            screenHeight - 337));

        // ///////////Data Panel/////////////
        final JPanel dataPanel = new JPanel() {

            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {

                Image img = new ImageIcon(img_path + img_mainband).getImage();
                Dimension size =
                    new Dimension(screenWidth - 158,
                        ((screenHeight - 330) + scrollHeight));// change height to change scrolling
                                                               // area
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
                // g.drawImage(img, 0, 0, null);
                g.drawImage(img, 0, 0, screenWidth - 158, (screenHeight - 330)
                    + scrollHeight, null);
            }
        };
        dataPanel.setLayout(null);
        int listSize = subjectList.size();
        logger.info("subjectList size in if ==>" + listSize);
        // if(!stdClass.equalsIgnoreCase("") && !academicYearClass.equalsIgnoreCase("") &&
        // !category.equalsIgnoreCase("")){
        // logger.info("Add new subject line........");
        // subjectList.add(stdClass+"|"+"New Subject"+"||"+"SELECT TITLE"+"|||"+"New Subject Title"+"||||"+"Marks"+"|||||"+"No");
        // }

        if (!category.equalsIgnoreCase("") && !stdClass.equalsIgnoreCase("")
            && !stdClass.equalsIgnoreCase("Select")) {
            final JRadioButton all_radio = new JRadioButton();
            all_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            all_radio.setBounds(40, 13, 20, 20);
            all_radio.setSelected(true);
            all_radio.setEnabled(false);
            dataPanel.add(all_radio);

            JLabel newSub_label = new JLabel("New Subject");
            newSub_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            newSub_label.setBounds(70, 00, 120, 50);
            dataPanel.add(newSub_label);

            JLabel pipe_label2 = new JLabel("|");
            pipe_label2.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            pipe_label2.setBounds(280, 00, 120, 50);
            dataPanel.add(pipe_label2);

            JLabel subTitle_label = new JLabel("Subject Title");
            subTitle_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            subTitle_label.setBounds(290, 00, 120, 50);
            dataPanel.add(subTitle_label);

            JLabel pipe_label3 = new JLabel("|");
            pipe_label3.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            pipe_label3.setBounds(520, 00, 120, 50);
            dataPanel.add(pipe_label3);

            JLabel newSubTitle_label = new JLabel("New Subject Title");
            newSubTitle_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            newSubTitle_label.setBounds(530, 00, 160, 50);
            dataPanel.add(newSubTitle_label);

            JLabel pipe_label4 = new JLabel("|");
            pipe_label4.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            pipe_label4.setBounds(720, 00, 120, 50);
            dataPanel.add(pipe_label4);

            JLabel markGrade_label = new JLabel("Marks / Grade");
            markGrade_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            markGrade_label.setBounds(730, 00, 120, 50);
            dataPanel.add(markGrade_label);

            JLabel pipe_label5 = new JLabel("|");
            pipe_label5.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            pipe_label5.setBounds(850, 00, 120, 50);
            dataPanel.add(pipe_label5);

            JLabel optional_label = new JLabel("Optional");
            optional_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            optional_label.setBounds(860, 00, 120, 50);
            dataPanel.add(optional_label);

            JLabel line_label =
                new JLabel(lineLabel);
            line_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            line_label.setBounds(30, 13, 1100, 50);
            dataPanel.add(line_label);

            final JRadioButton sub_radio = new JRadioButton();
            sub_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            sub_radio.setBounds(40, 43, 20, 20);
            sub_radio.setSelected(true);
            sub_radio.setEnabled(false);
            dataPanel.add(sub_radio);

            final JTextField newSub_text = new JTextField("New Subject");
            newSub_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            newSub_text.setBounds(70, 42, 180, 20);
            dataPanel.add(newSub_text);

            JLabel subPipe_label2 = new JLabel("|");
            subPipe_label2.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            subPipe_label2.setBounds(280, 30, 120, 50);
            dataPanel.add(subPipe_label2);

            boolean enableFlag = false;
            List<String> findSubTitleList = new ArrayList();
//            List<String> fetchSubjectList = new ArrayList();
//            List<String> displaySubjectList = new ArrayList();
            // int findSubListSize = 0; //CREATE NEW TITLE
            try {
            	if(dbValidate.connectDatabase(sessionData)){
	            	findSubTitleList = dbValidate.findSubjectTitleList(sessionData, stdClass, "", academicYearClass);
	            	//fetchSubjectList = dbValidate.fetchSubjectList(stdClass);
	            	//displaySubjectList.add("Subject|Title|Marks/Grade|Optional");
	            	//displaySubjectList.addAll(fetchSubjectList);
	            	enableFlag = true;
            	}

			} catch (Exception e1) {
	            logger.info("Exception findSubTitleList ===>>>" + e1);
	        } finally {
	        	dbValidate.closeDatabase(sessionData);
	        }
            
            String[] subTitleArr = findSubTitleList.toArray(new String[findSubTitleList.size()]);

            final JComboBox subTitle_combo = new JComboBox(subTitleArr);
            subTitle_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            subTitle_combo.setBounds(290, 42, 220, 20);
            // if(i < (listSize - 1) )
            subTitle_combo.setEnabled(enableFlag);
            dataPanel.add(subTitle_combo);

            JLabel subPipe_label3 = new JLabel("|");
            subPipe_label3.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            subPipe_label3.setBounds(520, 30, 120, 50);
            dataPanel.add(subPipe_label3);

            final JTextField newSubTitle_text = new JTextField("New Subject Title");
            newSubTitle_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            newSubTitle_text.setBounds(530, 42, 180, 20);
            dataPanel.add(newSubTitle_text);

            JLabel subPipe_label4 = new JLabel("|");
            subPipe_label4.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            subPipe_label4.setBounds(720, 30, 120, 50);
            dataPanel.add(subPipe_label4);

            String mark_grade[] = {"Marks", "Grade"};
            final JComboBox markGrade_combo = new JComboBox(mark_grade);
            markGrade_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            markGrade_combo.setBounds(730, 42, 120, 20);
            dataPanel.add(markGrade_combo);

            JLabel subPipe_label5 = new JLabel("|");
            subPipe_label5.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            subPipe_label5.setBounds(850, 30, 120, 50);
            dataPanel.add(subPipe_label5);

            String optional[] = {"No", "Yes"};
            final JComboBox optional_combo = new JComboBox(optional);
            optional_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            optional_combo.setBounds(860, 42, 120, 20);
            dataPanel.add(optional_combo);

            newSub_text.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent arg0) {

                    newSub_text.selectAll();
                }

                @Override
                public void focusLost(FocusEvent arg0) {

                    // TODO Auto-generated method stub

                }
            });

            newSubTitle_text.setEnabled(false);
            subTitle_combo.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    String subTitle_sel =
                        (String) subTitle_combo.getSelectedItem();
                    newSubTitle_text.setText(subTitle_sel);
                    if (!subTitle_sel.equalsIgnoreCase("CREATE NEW TITLE")) {
                        newSubTitle_text.setEnabled(false);
                    } else {
                        newSubTitle_text.setEnabled(true);
                    }
                }
            });

            newSubTitle_text.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent arg0) {

                    newSubTitle_text.selectAll();
                }

                @Override
                public void focusLost(FocusEvent arg0) {

                    // TODO Auto-generated method stub

                }
            });

            logger.info("::subjectList in for loop 1==> " + subjectList.size());
            JButton addButton = new JButton("Save & Add");
            addButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            // addButton.setBounds(115, l+5, 150, 25);
            addButton.setBounds(mainCentre - 250, 90, 150, 25);
            dataPanel.add(addButton);

            addButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    try {
                        boolean insertSubject = false;
                        boolean validateSubList = true;
                        String subName = newSub_text.getText();
                        logger.info("subName = " + subName);
                        String subTitle = (String) subTitle_combo.getSelectedItem();
                        logger.info("subTitle = " + subTitle);
                        String newSubTitle = newSubTitle_text.getText();
                        logger.info("newSubTitle = " + newSubTitle);
                        String mark_grade = (String) markGrade_combo.getSelectedItem();
                        logger.info("mark_grade = " + mark_grade);
                        String option = (String) optional_combo.getSelectedItem();
                        logger.info("option = " + option);

                        if (subName.equalsIgnoreCase("") || subName.equalsIgnoreCase("New Subject")) {
                            JOptionPane.showMessageDialog(null, "Please enter New Subject Name");
                            validateSubList = false;
                        } else if (commonObj.checkComma(subName)) {
                            JOptionPane.showMessageDialog(null, "Subject name cannot contain |-:';,");
                            validateSubList = false;
                        } else if (subTitle.equalsIgnoreCase("SELECT TITLE") || subTitle
                                .equalsIgnoreCase("SELECT SUBJECT TITLE")) {
                            JOptionPane.showMessageDialog(null, "Please select Subject Title");
                            validateSubList = false;
                        } else if (subTitle.equalsIgnoreCase("CREATE NEW TITLE")
                            && (newSubTitle.equalsIgnoreCase("") || newSubTitle.equalsIgnoreCase("CREATE NEW TITLE") || 
                            		newSubTitle.equalsIgnoreCase("New Subject Title") || newSubTitle.equalsIgnoreCase("Select Title"))) {
                            JOptionPane.showMessageDialog(null, "Please enter New Subject Title");
                            validateSubList = false;
                        } else if (commonObj.checkComma(newSubTitle)) {
                            JOptionPane.showMessageDialog(null, "Subject title cannot contain |-:';,");
                            validateSubList = false;
                        }
                        logger.info("validateSubList = " + validateSubList);
                        if (validateSubList) {
//                            insertSubject = dbValidate.addSubject(subName, newSubTitle.replace(" ", "_"), mark_grade, option, academicYearClass, stdClass, sessionData, "");
                            if (insertSubject) {
                                frame.setVisible(false);
                                new CreateFee(sessionData, category, stdClass, section, user_name, user_role);
                            }
                        }
                        logger.info("insertSubject = " + insertSubject);

                    } catch (Exception e1) {
                        logger.info("Exception e1 = " + e1);
                    }
                }
            });

            // ///////save data box///////////////////////
            JLabel sep_label =
                new JLabel("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            sep_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            sep_label.setBounds(40, 50, 1600, 50);
            dataPanel.add(sep_label);

            JButton SaveButton = new JButton("Save & Exit");
            SaveButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            SaveButton.setBounds(mainCentre, 90, 150, 25);
            dataPanel.add(SaveButton);

            SaveButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    try {
                        boolean insertSubject = false;
                        boolean validateSubList = true;
                        String subName = newSub_text.getText();
                        logger.info("subName = " + subName);
                        String subTitle = (String) subTitle_combo.getSelectedItem();
                        logger.info("subTitle = " + subTitle);
                        String newSubTitle = newSubTitle_text.getText();
                        logger.info("newSubTitle = " + newSubTitle);
                        String mark_grade = (String) markGrade_combo.getSelectedItem();
                        logger.info("mark_grade = " + mark_grade);
                        String option = (String) optional_combo.getSelectedItem();
                        logger.info("option = " + option);

                        if (subName.equalsIgnoreCase("") || subName.equalsIgnoreCase("New Subject")) {
                            JOptionPane.showMessageDialog(null, "Please enter New Subject Name");
                            validateSubList = false;
                        } else if (commonObj.checkComma(subName)) {
                            JOptionPane.showMessageDialog(null, "Subject name cannot contain |-:';,");
                            validateSubList = false;
                        } else if (subTitle.equalsIgnoreCase("SELECT TITLE") || subTitle.equalsIgnoreCase("SELECT SUBJECT TITLE")) {
                            JOptionPane.showMessageDialog(null, "Please select Subject Title");
                            validateSubList = false;
                        } else if (subTitle.equalsIgnoreCase("CREATE NEW TITLE") && (newSubTitle.equalsIgnoreCase("")
                                || newSubTitle.equalsIgnoreCase("CREATE NEW TITLE") || newSubTitle.equalsIgnoreCase("New Subject Title")
                                || newSubTitle.equalsIgnoreCase("Select Title") || newSubTitle.equalsIgnoreCase("Select Subject Title"))) {
                            JOptionPane.showMessageDialog(null, "Please enter New Subject Title");
                            validateSubList = false;
                        } else if (commonObj.checkComma(newSubTitle)) {
                            JOptionPane.showMessageDialog(null, "Subject title cannot contain |-:';,");
                            validateSubList = false;
                        }
                        if (validateSubList == true && subTitle.equalsIgnoreCase("CREATE NEW TITLE")
                            && !newSubTitle.equalsIgnoreCase("New Subject Title")) {
                            subTitle = newSubTitle;
                        }
                        logger.info("validateSubList = " + validateSubList);
                        if (validateSubList) {
//                            insertSubject = dbValidate.addSubject(subName, subTitle.replace(" ", "_"), mark_grade, option, academicYearClass, stdClass, sessionData, "");
                            frame.setVisible(false);
                            new CreateFee(sessionData, "", "", section, user_name, user_role);
                        }
                        logger.info("insertSubject = " + insertSubject);

                    } catch (Exception e1) {
                        logger.info("Exception e1 = " + e1);
                    }
                }
            });

            JLabel note_label = new JLabel("Note : Please add the subjects in the order you want to display.");
            note_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            note_label.setBounds(40, 120, 1000, 50);
            dataPanel.add(note_label);
            
            JLabel sep1_label = new JLabel("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            sep1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            sep1_label.setBounds(40, 130, 1600, 50);
            dataPanel.add(sep1_label);

            ///////////////////////////////
            if (fetchSubjectList.size() > 0) {
            	int j = 150;
    			int l = 0;
    			final JLabel[] subjectName_labels = new JLabel[displaySubjectList.size()];
    			final JLabel[] subjectTitle_label = new JLabel[displaySubjectList.size()];
    			final JLabel[] marksGrade_label = new JLabel[displaySubjectList.size()];
    			final JLabel[] optionalSubjects_label = new JLabel[displaySubjectList.size()];
    			JLabel[] line_labels = new JLabel[displaySubjectList.size()];
    			JLabel[] pipe_labels1 = new JLabel[displaySubjectList.size()];
    			JLabel[] pipe_labels2 = new JLabel[displaySubjectList.size()];
    			JLabel[] pipe_labels3 = new JLabel[displaySubjectList.size()];
    			JLabel[] pipe_labels4 = new JLabel[displaySubjectList.size()];
    			
    			for (int i = 0; i < displaySubjectList.size(); i++) {
    				j = j + 30;
    				l = j + 50;
    				String[] subjectDetailsSplit;
    				List<String> fetchSubjectDetails = new ArrayList();
    				
    				StringTokenizer st = new StringTokenizer(displaySubjectList.get(i),"|");

    				while (st.hasMoreElements()) {
    					fetchSubjectDetails.add(st.nextElement()+"");
    				}

    				
    				subjectName_labels[i] = new JLabel(fetchSubjectDetails.get(0));
    				subjectName_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
    				subjectName_labels[i].setBounds(70, j, 200, 50);
    				dataPanel.add(subjectName_labels[i]);

    				pipe_labels1[i] = new JLabel("|");
    				pipe_labels1[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
    				pipe_labels1[i].setBounds(280, j, 120, 50);
    				dataPanel.add(pipe_labels1[i]);
    				
    				subjectTitle_label[i] = new JLabel(fetchSubjectDetails.get(1));
    				subjectTitle_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
    				subjectTitle_label[i].setBounds(290, j, 200, 50);
    				dataPanel.add(subjectTitle_label[i]);

    				pipe_labels2[i] = new JLabel("|");
    				pipe_labels2[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
    				pipe_labels2[i].setBounds(520, j, 120, 50);
    				dataPanel.add(pipe_labels2[i]);
    				
    				marksGrade_label[i] = new JLabel(fetchSubjectDetails.get(2));
    				marksGrade_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
    				marksGrade_label[i].setBounds(530, j, 120, 50);
    				dataPanel.add(marksGrade_label[i]);

    				pipe_labels3[i] = new JLabel("|");
    				pipe_labels3[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
    				pipe_labels3[i].setBounds(640, j, 120, 50);
    				dataPanel.add(pipe_labels3[i]);
    				
    				optionalSubjects_label[i] = new JLabel(fetchSubjectDetails.get(3));
    				optionalSubjects_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
    				optionalSubjects_label[i].setBounds(650, j, 120, 50);
    				dataPanel.add(optionalSubjects_label[i]);

    				pipe_labels4[i] = new JLabel("|");
    				pipe_labels4[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
    				pipe_labels4[i].setBounds(720, j, 120, 50);
    				dataPanel.add(pipe_labels4[i]);
    				
    				line_labels[i] = new JLabel(lineLabel1);
    				line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
    				line_labels[i].setBounds(70, j + 10, 1100, 50);
    				dataPanel.add(line_labels[i]);
    				
    				
    			}
            }
            ///////////////////////////////
        }

        // ////////////////data panel ends/////////////////////////////////

        JScrollPane jsp;
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
