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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class MultipleFeesHead extends JFrame {
	
	private static MultipleFeesHead multipleFeesHead = new MultipleFeesHead();

	private static final long serialVersionUID = 1L;

	static int screenWidth;

    static int screenHeight;

    static int mainCentre;

    static JFrame frame = null;
    
    static String secName = "";

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
    
    static String img_databand = "";
    
    final static String createUser  = "Create New User";

    static DBValidate dbValidate = new DBValidate();
    
    static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

    static Logger logger = Logger.getLogger(MultipleFeesHead.class.getName());
    
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
    static String fee_required = "";
    static String account_required = "";
    static String staff_required = "";
    static String url = "";
    static int scrollHeight = 0;
    static String todayDate = "";
    static LinkedHashMap<String, LinkedHashMap<String, String>> feesHeadMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    
    /*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private MultipleFeesHead() {
	}

	/* Static 'instance' method */
	public static MultipleFeesHead getInstance() {
		return multipleFeesHead;
	}
	
	/* Other methods protected by singleton-ness */
	protected static void getMultipleFeesHead(SessionData sessionData1, String academicYear, String retStd) {

    	System.gc();
    	final String academicYearClass = academicYear;
    	final String stdClass = retStd;
    	sessionData = sessionData1;
        user_name = sessionData1.getUserName();
        user_role = sessionData1.getUserRole();
        String std = sessionData1.getConfigMap().get(sessionData1.getSectionName().toUpperCase() + "_STD");
//        frame.setVisible(false);
//        frame.dispose();
        frame = new JFrame("Welcome to "+sessionData1.getAppName());
        img_path = sessionData1.getConfigMap().get("IMAGE_PATH");
        logger.info("img_path :: " + img_path);
        img_home = sessionData1.getConfigMap().get("IMAGE_HOME");
        img_founder = sessionData1.getConfigMap().get("IMGAGE_FOUNDER");
        img_logo = sessionData1.getConfigMap().get("IMAGE_LOGO");
        img_myaccount = sessionData1.getConfigMap().get("IMAGE_MYACCOUNT");
        img_logout = sessionData1.getConfigMap().get("IMAGE_LOGOUT");
        img_titleband = sessionData1.getConfigMap().get("IMAGE_TITLEBAND");
        img_leftband = sessionData1.getConfigMap().get("IMAGE_LEFTBAND");
        img_menuband = sessionData1.getConfigMap().get("IMAGE_MENUBAND");
        img_mainband = sessionData1.getConfigMap().get("IMAGE_MAINBAND");
        img_databand = sessionData1.getConfigMap().get("IMAGE_DATABAND");
        sms_required = sessionData1.getConfigMap().get("SMS_REQUIRED");
        fee_required = sessionData1.getConfigMap().get("FEE_REQUIRED");
        account_required = sessionData1.getConfigMap().get("ACCOUNT_REQUIRED");
    	staff_required = sessionData1.getConfigMap().get("STAFF_REQUIRED");
        url = sessionData1.getConfigMap().get("DBURL_"+sessionData1.getDBName());
        secName = sessionData1.getConfigMap().get(sessionData.getSectionName().toUpperCase() + "_SEC");
        
        app_header = sessionData1.getConfigMap().get("APP_HEADER_"+sessionData.getAppType());
        app_header_0 = sessionData1.getConfigMap().get("APP_HEADER_0_"+sessionData.getAppType());
        app_header_0_fontName = sessionData1.getConfigMap().get("APP_HEADER_0_FONTNAME_"+sessionData.getAppType());
        app_header_0_fontSize = Integer.parseInt(sessionData1.getConfigMap().get("APP_HEADER_0_FONTSIZE_"+sessionData.getAppType()));
    	app_header_0_widthSpace = Integer.parseInt(sessionData1.getConfigMap().get("APP_HEADER_0_WIDTHSPACE_"+sessionData.getAppType()));
    	app_header_0_heightSpace = Integer.parseInt(sessionData1.getConfigMap().get("APP_HEADER_0_HEIGHTSPACE_"+sessionData.getAppType()));
    	app_header_fontName = sessionData1.getConfigMap().get("APP_HEADER_FONTNAME_"+sessionData.getAppType());
        app_header_fontSize = Integer.parseInt(sessionData1.getConfigMap().get("APP_HEADER_FONTSIZE_"+sessionData.getAppType()));
    	app_header_widthSpace = Integer.parseInt(sessionData1.getConfigMap().get("APP_HEADER_WIDTHSPACE_"+sessionData.getAppType()));
    	app_header_heightSpace = Integer.parseInt(sessionData1.getConfigMap().get("APP_HEADER_HEIGHTSPACE_"+sessionData.getAppType()));
    	app_header_2 = sessionData1.getConfigMap().get("APP_HEADER_2_"+sessionData.getAppType());
        app_header_2_fontName = sessionData1.getConfigMap().get("APP_HEADER_2_FONTNAME_"+sessionData.getAppType());
        app_header_2_fontSize = Integer.parseInt(sessionData1.getConfigMap().get("APP_HEADER_2_FONTSIZE_"+sessionData.getAppType()));
    	app_header_2_widthSpace = Integer.parseInt(sessionData1.getConfigMap().get("APP_HEADER_2_WIDTHSPACE_"+sessionData.getAppType()));
    	app_header_2_heightSpace = Integer.parseInt(sessionData1.getConfigMap().get("APP_HEADER_2_HEIGHTSPACE_"+sessionData.getAppType()));
    	
        jrc_required = sessionData1.getConfigMap().get("JRC_REQUIRED");
        sch_required = sessionData1.getConfigMap().get("SCH_REQUIRED");
        schigh_required = sessionData1.getConfigMap().get("SCHIGH_REQUIRED");
        scpri_required = sessionData1.getConfigMap().get("SCPRI_REQUIRED");
        scppr_required = sessionData1.getConfigMap().get("SCPPR_REQUIRED");
        show_founder = sessionData1.getConfigMap().get("SHOW_FOUNDER");
        show_donatedby = sessionData1.getConfigMap().get("SHOW_DONATEDBY");
        todayDate = commonObj.getCurrentDate();
        LinkedHashMap<String, LinkedHashMap<String, String>> headerMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
        LinkedHashMap<String, String> shortCategoryMap = new LinkedHashMap<String, String>();
        String feeHead = "", headerStr = "";
        
        try {
			if(dbValidate.connectDatabase(sessionData)){
				feeHead = dbValidate.getFeesCategoryList(sessionData1, academicYear, retStd, sessionData1.getSectionName(),
						"FEES_NAME", "FEES_HEAD");
				if(!retStd.isEmpty()){
					feesHeadMap = dbValidate.getFeesHeadData(sessionData1, academicYear, retStd, sessionData1.getSectionName(), "");
					scrollHeight = (feesHeadMap.size() - 6) * 30; // to adjust the perfect scroll height
				}
				
				Set setsc = feesHeadMap.entrySet();
				Iterator isc = setsc.iterator();
				while(isc.hasNext()) {
					Map.Entry me = (Map.Entry)isc.next();
					shortCategoryMap.put(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("category").toString(), 
							((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("short_name").toString());
				}
				
				headerMap = dbValidate.fetchMultipleHeadMap(sessionData, academicYearClass, stdClass, "");
				Set setHeader = headerMap.entrySet();
				Iterator hMap = setHeader.iterator();
				while(hMap.hasNext()) {
					Map.Entry me = (Map.Entry)hMap.next();
					headerStr = headerStr +","+ me.getKey().toString();
				}
			}
		} catch (Exception e1) {
            logger.error("Exception while getting table order ==>>>" + e1);
        }
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screenWidth = commonObj.screeWidth();
        screenHeight = commonObj.screeHeight();
        mainCentre = (screenWidth - 150) / 2;

        frame.setSize(screenWidth, screenHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
//        placeComponents(panel);
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
					commonObj.logException(e);
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
						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role,
								sessionData.getSectionName());
						dbValidate.closeDatabase(sessionData);

						frame.setVisible(false);
						String[] arguments = new String[] { "" };
						SchoolForAllLoginView.main(arguments);
					}
				} catch (Exception e1) {
					commonObj.logException(e1);
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
					commonObj.logException(e);
				}
			}
		});
        
        int p = 50;
        JButton studentButton = new JButton("Student");
        studentButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        studentButton.setBounds(10, p + 50, 130, 35);
        leftPanel.add(studentButton);
        
        studentButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                new Student(sessionData, sessionData.getSectionName(), user_name, user_role);
            }
        });

        if(staff_required.equalsIgnoreCase("true")){
	        p = p + 50;
	        JButton staffButton = new JButton("Staff");
	        staffButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
	        staffButton.setBounds(10, p + 50, 130, 35);
	        leftPanel.add(staffButton);
	
	        staffButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	
	                frame.setVisible(false);
	                new Staff(sessionData, sessionData.getSectionName(), user_name, user_role);
	            }
	        });
        }
        p = p + 50;
        JButton academicButton = new JButton("Academic");
        academicButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        academicButton.setBounds(10, p + 50, 130, 35);
        leftPanel.add(academicButton);

        academicButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                new Academic(sessionData, sessionData.getSectionName(), user_name, user_role);
            }
        });

        if(account_required.equalsIgnoreCase("true")){
	        p = p + 50;
	        JButton accountButton = new JButton("Account");
	        accountButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
	        accountButton.setBounds(10, p + 50, 130, 35);
	//        leftPanel.add(accountButton);
	
	        accountButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	                frame.setVisible(false);
	                new CreateSubject(sessionData, "", "", sessionData.getSectionName(), user_name, user_role, "");
	            }
	        });
        }
        p = p + 50;
        JButton searchButton = new JButton("Find");
        searchButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        searchButton.setBounds(10, p + 50, 130, 35);
        leftPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            	LinkedHashMap<String,LinkedHashMap<String, String>> studMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
                new FindStudent(sessionData, "", "", "", "", "", "", studMap, sessionData.getSectionName(),
                    user_name, user_role, false);
                frame.setVisible(false);
            }
        });
        
        if(sms_required.equalsIgnoreCase("true")){
	        p = p + 50;
	        JButton smsButton = new JButton("SMS");
	        smsButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
	        smsButton.setBounds(10, p + 50, 130, 35);
	        leftPanel.add(smsButton);
	
	        smsButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	
	            	frame.setVisible(false);
	            	LinkedHashMap findStudentMap = new LinkedHashMap();
					new SmsPage(sessionData, "", "", "", "", "", "", findStudentMap, false, "", "", "", "", "",
							"", sessionData.getSectionName(), "", user_name, user_role,"","","");
	            }
	        });
        }
        
        if(fee_required.equalsIgnoreCase("true")){
	        p = p + 50;
	        JButton feeButton = new JButton("FEE");
	        feeButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
	        feeButton.setBackground(Color.GREEN);
	        feeButton.setBounds(10, p + 50, 130, 35);
	        leftPanel.add(feeButton);
	
	        feeButton.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	
	            	frame.setVisible(false);
					new CreateFeesHead(sessionData, "", "", sessionData.getSectionName());
	            }
	        });
        }
        
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
		
		JLabel subMenuTitle = new JLabel("Fee");
		subMenuTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		subMenuTitle.setForeground(Color.orange);
		subMenuTitle.setBounds(20, 45, 100, 30);
		topbandPanel.add(subMenuTitle);
        
		int width = 80;
		JButton schoolHeadButton = new JButton("School Head");
		schoolHeadButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		schoolHeadButton.setBounds(width, 50, 140, 24);
		topbandPanel.add(schoolHeadButton);

		schoolHeadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				CreateSchoolHead.getCreateSchoolHead(sessionData, academicYearClass);
			}
		});

		width = width + 150;
		JButton createFeeButton = new JButton("Fee Head");
		createFeeButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		createFeeButton.setBounds(width, 50, 140, 24);
		topbandPanel.add(createFeeButton);

		createFeeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LinkedHashMap findStudentMap = new LinkedHashMap();
				new CreateFeesHead(sessionData, "", "", sessionData.getSectionName());
			}
		});
		
		width = width + 150;
		JButton MultipleFeeButton = new JButton("Multiple Fee Head");
		MultipleFeeButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		MultipleFeeButton.setBackground(Color.GREEN);
		MultipleFeeButton.setBounds(width, 50, 190, 24);
		topbandPanel.add(MultipleFeeButton);

		MultipleFeeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
            	MultipleFeesHead.getMultipleFeesHead(sessionData, "", "");
			}
		});
		
		width = width + 200;
		JButton feeAllotButton = new JButton("Fee Allotment");
		feeAllotButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		feeAllotButton.setBounds(width, 50, 150, 24);
		topbandPanel.add(feeAllotButton);

		feeAllotButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				StudentFeeAllot.getStudentFeeAllot(sessionData, "", "", "", "", "", "", "");
			}
		});
		
		width = width + 160;
		JButton feeSearchButton = new JButton("Search & Pay");
		feeSearchButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		feeSearchButton.setBounds(width, 50, 150, 24);
		topbandPanel.add(feeSearchButton);

		feeSearchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LinkedHashMap retStudentMap = new LinkedHashMap();
				new SearchFeeStudentNew(sessionData, "", "", "", "", "", "", retStudentMap, sessionData.getSectionName(), "", "", "", "", false, "", "", false);
			}
		});
		
		/*width = width + 190;
		JButton feePayButton = new JButton("Fees Payment");
		feePayButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		feePayButton.setBounds(width, 50, 170, 24);
		topbandPanel.add(feePayButton);

		feePayButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}
		});*/
		
		width = width + 160;
		JButton feeReportButton = new JButton("Fee Report");
		feeReportButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		feeReportButton.setBounds(width, 50, 150, 24);
		topbandPanel.add(feeReportButton);

		feeReportButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LinkedHashMap<String,LinkedHashMap<String, String>> studentMapEmpty =  new LinkedHashMap<>();
				FeesReport.getFeesReport(sessionData, "", "", "", "", "", "", studentMapEmpty, sessionData.getSectionName(), "", "", "", "", false);
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
        
        int tabHeight = 150;
        int bottomBandItemHeight = 50;
        int itemWidth = 40;
        
        // /////////////ACADEMIV YEAR //////////////
        JLabel academic_label = new JLabel("Academic Year :");
        academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        academic_label.setBounds(40, 30, 150, 50);
        bottombandPanel.add(academic_label);

        String yearList = commonObj.getAcademicYear(sessionData1,todayDate)+","+commonObj.getNextYear(sessionData, todayDate);
        String academicYearList[] = yearList.split(",");
//        String academicYearList[] = {academicYear};
        final JComboBox academicYear_combo = new JComboBox(academicYearList);
        academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        academicYear_combo.setSelectedItem(academicYear);
        academicYear_combo.setBounds(170, 42, 120, 25);
        academicYear_combo.setEnabled(true);
        bottombandPanel.add(academicYear_combo);
        // //////////////////////////////////
        // /////////////Std//////////////
        JLabel std_label = new JLabel("Std :");
        std_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        std_label.setBounds(330, 30, 70, 50);
        bottombandPanel.add(std_label);

        std = "Select," + std;
        String[] stdList = std.split(",");
        final JComboBox std_combo = new JComboBox(stdList);
        std_combo.setSelectedItem(retStd);
        std_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        std_combo.setBounds(380, 42, 90, 25);
        std_combo.setEditable(false);
        bottombandPanel.add(std_combo);
        
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        submitButton.setBounds(500, 42, 90, 25);
        bottombandPanel.add(submitButton);
        
        bottomBandItemHeight = bottomBandItemHeight + 30;
        JSeparator jSeparator1  = new JSeparator();
        jSeparator1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        jSeparator1.setBounds(40, bottomBandItemHeight, (screenWidth - 200), 50);
        bottombandPanel.add(jSeparator1);
        
        submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String stdSel = std_combo.getSelectedItem().toString();
				String academicSel = academicYear_combo.getSelectedItem().toString();
				if(stdSel.equalsIgnoreCase("Select")){
					JOptionPane.showMessageDialog(null, "Please select STD");
				}
				else{
					frame.setVisible(false);
					MultipleFeesHead.getMultipleFeesHead(sessionData, academicSel, stdSel);
				}
			}
		});
        
        /////end order headers///////////////
        /////////////order panel/////////////
        
        itemWidth = 40;
        final JTextField feesNameText = new JTextField();
        String[] feeHeadList = commonObj.revertCommaApostrophy(feeHead).split(",");
        String[] headerList = headerStr.split(",");
        final JComboBox feesCombo = new JComboBox(feeHeadList);
        final JTextField feeMultipleText = new JTextField("");
        final JTextField feesAmtText = new JTextField();
        final JCheckBox allStdCheck = new JCheckBox();
        JButton addButton = new JButton("Add");
        
        bottomBandItemHeight = bottomBandItemHeight + 10;
        if(!retStd.equalsIgnoreCase("")){
        	JLabel feesNameLabel = new JLabel("Fees Name : ");
            feesNameLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feesNameLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(feesNameLabel);
            
            itemWidth = itemWidth + 90;
            feesCombo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feesCombo.setBounds(itemWidth, bottomBandItemHeight + 10, 220, 25);
            bottombandPanel.add(feesCombo);
            
            itemWidth = itemWidth + 230;
            JLabel subFeesNameLabel = new JLabel("Sub Fees Name : ");
            subFeesNameLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            subFeesNameLabel.setBounds(itemWidth, bottomBandItemHeight, 180, 50);
            bottombandPanel.add(subFeesNameLabel);
            
            itemWidth = itemWidth + 130;
            feeMultipleText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feeMultipleText.setBounds(itemWidth, bottomBandItemHeight + 10, 280, 25);
            bottombandPanel.add(feeMultipleText);
            
            itemWidth = itemWidth + 290;
            JLabel feesAmtLabel = new JLabel("Amount : ");
            feesAmtLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feesAmtLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(feesAmtLabel);
            
            itemWidth = itemWidth + 70;
            feesAmtText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            feesAmtText.setBounds(itemWidth, bottomBandItemHeight + 10, 70, 25);
            bottombandPanel.add(feesAmtText);
            
            itemWidth = itemWidth + 80;
            JLabel allStdLabel = new JLabel("All Std : ");
            allStdLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            allStdLabel.setBounds(itemWidth, bottomBandItemHeight, 200, 50);
            bottombandPanel.add(allStdLabel);
            
            itemWidth = itemWidth + 70;
            allStdCheck.setBorder(null);
            allStdCheck.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            allStdCheck.setBounds(itemWidth, bottomBandItemHeight + 17, 13, 13);
            bottombandPanel.add(allStdCheck);
            
            itemWidth = itemWidth + 30;
    		addButton.setFont(new Font("Book Antiqua", Font.BOLD, 25));
    		addButton.setBounds(itemWidth, bottomBandItemHeight+10, 100, 25);
            bottombandPanel.add(addButton);
            
            bottomBandItemHeight = bottomBandItemHeight + 40;
            JSeparator jSeparator2  = new JSeparator();
            jSeparator2.setFont(new Font("Book Antiqua", Font.BOLD, 18));
            jSeparator2.setBounds(40, bottomBandItemHeight, (screenWidth - 200), 50);
            bottombandPanel.add(jSeparator2);
            
          ////addButton action///////////////////
          addButton.addKeyListener(new KeyAdapter(){
              public void keyReleased(KeyEvent e){
              int keyCode=e.getKeyCode();
              String fee_name = feesCombo.getSelectedItem().toString();
              String subFee_name = feeMultipleText.getText();
              String amount = feesAmtText.getText();
              boolean allStd = allStdCheck.isSelected();
              if(keyCode == 10){
  					addToOrder(sessionData,fee_name,subFee_name,amount, academicYearClass, stdClass, allStd);
                }
              }
          });
          
          addButton.addActionListener(new ActionListener() {
  
  			public void actionPerformed(ActionEvent e) {
  				String fee_name = feesCombo.getSelectedItem().toString();
                String subFee_name = feeMultipleText.getText();
                String amount = feesAmtText.getText();
                boolean allStd = allStdCheck.isSelected();
				addToOrder(sessionData,fee_name,subFee_name,amount, academicYearClass, stdClass, allStd);
  			}
          });
          
          feesCombo.addActionListener(new ActionListener() {
        	  
    			public void actionPerformed(ActionEvent e) {
    				String fee_name = feesCombo.getSelectedItem().toString();
    				if(feesHeadMap.get(fee_name) != null) {
    					feesAmtText.setText(((LinkedHashMap<?, ?>) feesHeadMap.get(fee_name)).get("amount").toString());
    				}
    			}
            });
        }
        
        /////start order headers////////////////
        itemWidth = 40;
        JLabel srNo = new JLabel("Sr No.");
        srNo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        srNo.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(srNo);
        
        itemWidth = itemWidth + 70;
        JLabel stdLabel = new JLabel("Std");
        stdLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        stdLabel.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(stdLabel);
        
        itemWidth = itemWidth + 60;
        JLabel feeName = new JLabel("Fee Name");
        feeName.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        feeName.setBounds(itemWidth, bottomBandItemHeight, 600, 50);
        bottombandPanel.add(feeName);
        
        itemWidth = itemWidth + 300;
        JLabel subFeeLabel = new JLabel("Sub Fee Name");
        subFeeLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        subFeeLabel.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(subFeeLabel);
        
        itemWidth = itemWidth + 300;
        JLabel amountLabel = new JLabel("Amount");
        amountLabel.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        amountLabel.setBounds(itemWidth, bottomBandItemHeight, 120, 50);
        bottombandPanel.add(amountLabel);
        
        /////end order headers///////////////
        /////////////order panel/////////////
        
        JPanel dataPanel = new JPanel() {

            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {

                Image img = new ImageIcon(img_path + img_databand).getImage();
                Dimension size =
                    new Dimension(screenWidth - 200, screenHeight + scrollHeight);//this height is responsible to increase scroll area
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
                g.drawImage(img, 0, 0, null);
            }
        };
//      dataPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
        dataPanel.setAutoscrolls(true);
        dataPanel.setLayout(null);
        
        if (!headerMap.isEmpty()) {
	        final JLabel[] srNo_labels = new JLabel[headerMap.size()];
	        final JLabel[] std_labels = new JLabel[headerMap.size()];
	        final JLabel[] feeName_labels = new JLabel[headerMap.size()];
	        final JLabel[] subFeeName_labels = new JLabel[headerMap.size()];
	        final JTextField[] subFeeName_text = new JTextField[headerMap.size()];
	        final JTextField[] amount_text = new JTextField[headerMap.size()];
	        final JButton[] edit_buttons = new JButton[headerMap.size()];
	        final JButton[] save_buttons = new JButton[headerMap.size()];
	        final JButton[] delete_buttons = new JButton[headerMap.size()];
	        final JSeparator[] jSeparators  = new JSeparator[headerMap.size()];
	        
	        int k = 0;
	        int j = 5;
	        String subFeeName = "", feeNameDisp  = "";
	        Set set = headerMap.entrySet();
			Iterator i = set.iterator();
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
				LinkedHashMap<String, String> headerDetails = new LinkedHashMap<String, String>();
				headerDetails = (LinkedHashMap<String, String>) me.getValue();
				
				itemWidth = 20;
	        	srNo_labels[k] = new JLabel((k+1)+".");
	        	srNo_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	        	srNo_labels[k].setBounds(itemWidth, j, 120, 25);
	        	dataPanel.add(srNo_labels[k]);
	        	
	        	itemWidth = itemWidth + 60;
	        	std_labels[k] = new JLabel(stdClass);
	        	std_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	        	std_labels[k].setBounds(itemWidth, j, 500, 25);
	    		dataPanel.add(std_labels[k]);
	    		
	    		itemWidth = itemWidth + 60;
	    		feeNameDisp = commonObj.revertCommaApostrophy(headerDetails.get("fees_name"));
	    		feeName_labels[k] = new JLabel(feeNameDisp);
	    		feeName_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		feeName_labels[k].setBounds(itemWidth, j, 500, 25);
	    		dataPanel.add(feeName_labels[k]);
	    		
	    		itemWidth = itemWidth + 300;
	    		subFeeName = commonObj.revertCommaApostrophy(headerDetails.get("sub_fees_name"));
	    		subFeeName_labels[k] = new JLabel(subFeeName);
	    		subFeeName_text[k] = new JTextField(subFeeName);
	    		subFeeName_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		subFeeName_text[k].setBounds(itemWidth, j, 280, 25);
	    		subFeeName_text[k].setEditable(false);
	    		dataPanel.add(subFeeName_text[k]);
	    		
	    		itemWidth = itemWidth + 300;
	    		amount_text[k] = new JTextField(headerDetails.get("amount"));
	    		amount_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		amount_text[k].setBounds(itemWidth, j, 70, 25);
	    		amount_text[k].setEditable(false);
	    		dataPanel.add(amount_text[k]);
	    		
	    		itemWidth = itemWidth + 150;
	    		edit_buttons[k] = new JButton("Edit");
	    		edit_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		edit_buttons[k].setBounds(itemWidth, j, 70, 25);
	    		dataPanel.add(edit_buttons[k]);
	    		
	    		itemWidth = itemWidth + 80;
	    		save_buttons[k] = new JButton("Save");
	    		save_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		save_buttons[k].setEnabled(false);
	    		save_buttons[k].setBounds(itemWidth, j, 70, 25);
	    		dataPanel.add(save_buttons[k]);
	    		
	    		itemWidth = itemWidth + 80;
	    		delete_buttons[k] = new JButton("Delete");
	    		delete_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
	    		delete_buttons[k].setBounds(itemWidth, j, 90, 25);
	    		dataPanel.add(delete_buttons[k]);
	    		
	    		jSeparators[k] = new JSeparator();
	    		jSeparators[k].setFont(new Font("Book Antiqua", Font.BOLD, 18));
	    		jSeparators[k].setBounds(10, j + 30, (screenWidth - 220), 50);
	    		dataPanel.add(jSeparators[k]);
	    		
	            final int m = k;
	    		edit_buttons[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							boolean isEditable = true;
							String subFeeName = subFeeName_text[m].getText();
							
//							if(dbValidate.connectDatabase(sessionData)){
//								isEditable = dbValidate.isFeesEditable(sessionData, academicYear, retStd, sessionData1.getSectionName(), optional, feesHead);
//							}
							
							if(isEditable){
								subFeeName_text[m].setEditable(true);
								amount_text[m].setEditable(true);
								edit_buttons[m].setEnabled(false);
								save_buttons[m].setEnabled(true);
							}
							else{
								JOptionPane.showMessageDialog(null, "Cannot delete as multiple head data for sub fee name "+subFeeName+" is utilized");
							}
						} catch (Exception e1) {
							commonObj.logException(e1);
						}
					}
				});
	    		
	    		save_buttons[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							boolean updateFlag = false;
							String fee_name = feeName_labels[m].getText();
							String subFee_name = subFeeName_text[m].getText();
							String amount = amount_text[m].getText();
							
							if(dbValidate.connectDatabase(sessionData)){
								updateFlag = dbValidate.addMultipleHead(sessionData, fee_name, subFee_name, 
										amount, academicYearClass, stdClass, "update", subFeeName_labels[m].getText(), false);
							}
							
							subFeeName_text[m].setEditable(false);
							amount_text[m].setEditable(false);
							edit_buttons[m].setEnabled(true);
							save_buttons[m].setEnabled(false);
						} catch (Exception e1) {
							commonObj.logException(e1);
						}
					}
				});
	    		
	    		delete_buttons[k].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						///delete multiple head/////
						try {
							boolean isEditable = true;
							
//							if(dbValidate.connectDatabase(sessionData)){
//								isEditable = dbValidate.isFeesEditable(sessionData, academicYear, retStd, sessionData1.getSectionName(), optional, feesHead);
//							}
							
							if(isEditable){
								int reply = 0;
								reply = JOptionPane.showConfirmDialog(null, "Would You Like to delete "+subFeeName_text[m].getText()+"?", "Confirm validate", JOptionPane.YES_NO_OPTION);
								
								if(dbValidate.connectDatabase(sessionData) && reply == JOptionPane.YES_OPTION){
									dbValidate.addMultipleHead(sessionData, feeName_labels[m].getText(), "", 
											"", academicYearClass, stdClass, "delete", subFeeName_labels[m].getText(), false);
									
									frame.setVisible(false);
									MultipleFeesHead.getMultipleFeesHead(sessionData, "", "");
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Cannot delete as multiple fee head for "+subFeeName_labels[m].getText()+" is utilized.");
							}
						} catch (Exception e1) {
							commonObj.logException(e1);
						}
					}
				});
	    		
	    		j = j + 40;
	    		k++;
	        }
			
			bottombandPanel.revalidate();
			bottombandPanel.repaint();
        }
        
        bottomBandItemHeight = bottomBandItemHeight + 30;
        JScrollPane jsp;
        int height = 380;
		jsp = new JScrollPane(dataPanel, 
				   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  
				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(30, bottomBandItemHeight + 10, screenWidth - 180, screenHeight - height);
		
        bottombandPanel.add(jsp, BorderLayout.SOUTH);
        ///////////////end order panel////////////
        
        mainPanel.add(bottombandPanel, BorderLayout.SOUTH);
        panel.add(mainPanel, BorderLayout.EAST);

        frame.setVisible(true);
        
    }

//    private static void placeComponents(JPanel panel) {
//
//        panel.setLayout(new BorderLayout());
//
//        // ///////////top panel////////////////////////////////////////
//        JPanel titlePanel = new JPanel() {
//
//            private static final long serialVersionUID = 1L;
//
//            public void paintComponent(Graphics g) {
//
//                Image img = new ImageIcon(img_path + img_titleband).getImage();
//                Dimension size = new Dimension(screenWidth, 75);
//                setPreferredSize(size);
//                setMinimumSize(size);
//                setMaximumSize(size);
//                setSize(size);
//                setLayout(null);
//                g.drawImage(img, 0, 0, null);
//            }
//        };
//        titlePanel.setLayout(null);
//
//        // //////////////logo//////////////////
//        final ImageIcon icon = new ImageIcon(img_path + img_logo);
//        JLabel label = new JLabel(icon);
//        // label.setFont(new Font("Book Antiqua", Font.BOLD, 30));
//        label.setBounds(45, 15, 55, 55);
//    	titlePanel.add(label);
//        // /////////////title//////////////
//    	JLabel title_0 = new JLabel(app_header_0);
//        title_0.setFont(new Font(app_header_0_fontName, Font.BOLD, app_header_0_fontSize));
//        title_0.setBounds((screenWidth / 2) - app_header_0_widthSpace, app_header_0_heightSpace, screenWidth - 400, 50);
//        titlePanel.add(title_0);
//        
//        JLabel title = new JLabel(app_header);
//        title.setFont(new Font(app_header_fontName, Font.BOLD, app_header_fontSize));//38 default
//        title.setBounds((screenWidth / 2) - app_header_widthSpace, app_header_heightSpace, screenWidth - 100, 50);
//        titlePanel.add(title);
//        
//        JLabel title_2 = new JLabel(app_header_2);
//        title_2.setFont(new Font(app_header_2_fontName, Font.BOLD, app_header_2_fontSize));//38 default
//        title_2.setBounds((screenWidth / 2) - app_header_2_widthSpace, app_header_2_heightSpace, screenWidth - 360, 50);
//        titlePanel.add(title_2);
//
//        // /account//////////////
//        final ImageIcon iconaccount = new ImageIcon(img_path + img_myaccount);
//        JLabel labelaccount = new JLabel(iconaccount);
//        labelaccount.setBounds((screenWidth - 150), 10, 60, 60);
//        titlePanel.add(labelaccount);
//        
//        labelaccount.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent me) {
//				try {
//					frame.setVisible(false);
//					new MyAccount(sessionData);
//				} catch (Exception e) {
//					commonObj.logException(e);
//				}
//			}
//		});
//        
//        // /logout//////////////
//        final ImageIcon iconlogout = new ImageIcon(img_path + img_logout);
//        JLabel labellogout = new JLabel(iconlogout);
//        labellogout.setBounds((screenWidth - 80), 10, 60, 60);
//        titlePanel.add(labellogout);
//        
//        labellogout.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent me) {
//				boolean deleteFlag = false;
//				try {
//					if (dbValidate.connectDatabase(sessionData)) {
//						deleteFlag = dbValidate.deleteFormData(sessionData, user_name, "LEAVING CERTIFICATE", user_role,
//								sessionData.getSectionName());
//						dbValidate.closeDatabase(sessionData);
//
//						frame.setVisible(false);
//						String[] arguments = new String[] { "" };
//						SchoolForAllLoginView.main(arguments);
//					}
//				} catch (Exception e1) {
//					commonObj.logException(e1);
//				} finally {
//					dbValidate.closeDatabase(sessionData);
//				}
//			}
//		});
//
//        panel.add(titlePanel, BorderLayout.NORTH);
//
//        // ///////////left Band panel////////////////////////////////////////
//        JPanel leftPanel = new JPanel() {
//
//            private static final long serialVersionUID = 1L;
//
//            public void paintComponent(Graphics g) {
//
//                Image img = new ImageIcon(img_path + img_leftband).getImage();
//                Dimension size = new Dimension(150, screenHeight - 100);
//                setPreferredSize(size);
//                setMinimumSize(size);
//                setMaximumSize(size);
//                setSize(size);
//                setLayout(null);
//                g.drawImage(img, 0, 0, null);
//            }
//        };
//        leftPanel.setLayout(null);
//
//        // /home//////////////
//        final ImageIcon iconhome = new ImageIcon(img_path + img_home);
//        JLabel labelhome = new JLabel(iconhome);
//        labelhome.setBounds(45, 10, 60, 60);
//        leftPanel.add(labelhome);
//        
//        labelhome.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent me) {
//				try {
//					frame.setVisible(false);
//					new Welcome(sessionData, user_name, user_role);
//				} catch (Exception e) {
//					commonObj.logException(e);
//				}
//			}
//		});
//        
//        int p = 50;
//        JButton studentButton = new JButton("Student");
//        studentButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//        studentButton.setBounds(10, p + 50, 130, 35);
//        leftPanel.add(studentButton);
//        
//        studentButton.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//
//                frame.setVisible(false);
//                new Student(sessionData, sessionData.getSectionName(), user_name, user_role);
//            }
//        });
//
//        if(staff_required.equalsIgnoreCase("true")){
//	        p = p + 50;
//	        JButton staffButton = new JButton("Staff");
//	        staffButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//	        staffButton.setBounds(10, p + 50, 130, 35);
//	        leftPanel.add(staffButton);
//	
//	        staffButton.addActionListener(new ActionListener() {
//	
//	            public void actionPerformed(ActionEvent e) {
//	
//	                frame.setVisible(false);
//	                new Staff(sessionData, sessionData.getSectionName(), user_name, user_role);
//	            }
//	        });
//        }
//        p = p + 50;
//        JButton academicButton = new JButton("Academic");
//        academicButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//        academicButton.setBounds(10, p + 50, 130, 35);
//        leftPanel.add(academicButton);
//
//        academicButton.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//
//                frame.setVisible(false);
//                new Academic(sessionData, sessionData.getSectionName(), user_name, user_role);
//            }
//        });
//
//        if(account_required.equalsIgnoreCase("true")){
//	        p = p + 50;
//	        JButton accountButton = new JButton("Account");
//	        accountButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//	        accountButton.setBounds(10, p + 50, 130, 35);
//	//        leftPanel.add(accountButton);
//	
//	        accountButton.addActionListener(new ActionListener() {
//	
//	            public void actionPerformed(ActionEvent e) {
//	                frame.setVisible(false);
//	                new CreateSubject(sessionData, "", "", sessionData.getSectionName(), user_name, user_role);
//	            }
//	        });
//        }
//        p = p + 50;
//        JButton searchButton = new JButton("Find");
//        searchButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//        searchButton.setBounds(10, p + 50, 130, 35);
//        leftPanel.add(searchButton);
//
//        searchButton.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//
//            	LinkedHashMap<String,LinkedHashMap<String, String>> studMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
//                new FindStudent(sessionData, "", "", "", "", "", "", studMap, sessionData.getSectionName(),
//                    user_name, user_role);
//                frame.setVisible(false);
//            }
//        });
//        
//        if(sms_required.equalsIgnoreCase("true")){
//	        p = p + 50;
//	        JButton smsButton = new JButton("SMS");
//	        smsButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//	        smsButton.setBounds(10, p + 50, 130, 35);
//	        leftPanel.add(smsButton);
//	
//	        smsButton.addActionListener(new ActionListener() {
//	
//	            public void actionPerformed(ActionEvent e) {
//	
//	            	frame.setVisible(false);
//	            	LinkedHashMap findStudentMap = new LinkedHashMap();
//					new SmsPage(sessionData, "", "", "", "", "", "", findStudentMap, false, "", "", "", "", "",
//							"", sessionData.getSectionName(), "", user_name, user_role,"","","");
//	            }
//	        });
//        }
//        
//        if(fee_required.equalsIgnoreCase("true")){
//	        p = p + 50;
//	        JButton feeButton = new JButton("FEE");
//	        feeButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//	        feeButton.setBackground(Color.GREEN);
//	        feeButton.setBounds(10, p + 50, 130, 35);
//	        leftPanel.add(feeButton);
//	
//	        feeButton.addActionListener(new ActionListener() {
//	
//	            public void actionPerformed(ActionEvent e) {
//	
//	            	frame.setVisible(false);
//					new CreateFeesHead(sessionData, "", "", sessionData.getSectionName());
//	            }
//	        });
//        }
//        
//        panel.add(leftPanel, BorderLayout.WEST);
//
//        // ///////////main panel////////////////////////////////////////
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BorderLayout());
//
//        // ///////////menu band panel////////////////////////////////////////
//        JPanel topbandPanel = new JPanel() {
//
//            private static final long serialVersionUID = 1L;
//
//            public void paintComponent(Graphics g) {
//
//                Image img = new ImageIcon(img_path + img_menuband).getImage();
//                Dimension size = new Dimension(screenWidth - 150, 80);
//                setPreferredSize(size);
//                setMinimumSize(size);
//                setMaximumSize(size);
//                setSize(size);
//                setLayout(null);
//                g.drawImage(img, 0, 0, null);
//            }
//        };
//        topbandPanel.setLayout(null);
//
//        JLabel menuBandTitle = new JLabel(secName);
//		menuBandTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
//		menuBandTitle.setForeground(Color.orange);
//		menuBandTitle.setBounds(20, 0, 600, 30);
//		topbandPanel.add(menuBandTitle);
//		
//		JLabel subMenuTitle = new JLabel("Fee");
//		subMenuTitle.setFont(new Font("Book Antiqua", Font.BOLD, 24));
//		subMenuTitle.setForeground(Color.orange);
//		subMenuTitle.setBounds(20, 45, 100, 30);
//		topbandPanel.add(subMenuTitle);
//        
//		int width = 80;
//		JButton schoolHeadButton = new JButton("School Head");
//		schoolHeadButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
//		schoolHeadButton.setBackground(Color.GREEN);
//		schoolHeadButton.setBounds(width, 50, 140, 24);
//		topbandPanel.add(schoolHeadButton);
//
//		schoolHeadButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);
//				new MultipleFeesHead(sessionData);
//			}
//		});
//
//		width = width + 150;
//		JButton createFeeButton = new JButton("Fee Head");
//		createFeeButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
//		createFeeButton.setBounds(width, 50, 140, 24);
//		topbandPanel.add(createFeeButton);
//
//		createFeeButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);
//				new CreateFeesHead(sessionData, "", "", sessionData.getSectionName());
//			}
//		});
//		
//		/*width = width + 190;
//		JButton feeAllotButton = new JButton("Fee Allotment");
//		feeAllotButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
//		feeAllotButton.setBounds(width, 50, 150, 24);
//		topbandPanel.add(feeAllotButton);
//
//		feeAllotButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//
//			}
//		});*/
//		
//		width = width + 190;
//		JButton feeSearchButton = new JButton("Search & Pay");
//		feeSearchButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
//		feeSearchButton.setBounds(width, 50, 170, 24);
//		topbandPanel.add(feeSearchButton);
//
//		feeSearchButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);
//				LinkedHashMap retStudentMap = new LinkedHashMap();
//				new SearchFeeStudent(sessionData, "", "", "", "", "", "", retStudentMap, sessionData.getSectionName(), "", "", "", "", false, "", "", false);
//			}
//		});
//		
//		/*width = width + 190;
//		JButton feePayButton = new JButton("Fees Payment");
//		feePayButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
//		feePayButton.setBounds(width, 50, 170, 24);
//		topbandPanel.add(feePayButton);
//
//		feePayButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//
//			}
//		});*/
//		
//		width = width + 190;
//		JButton feeReportButton = new JButton("Fee Report");
//		feeReportButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
//		feeReportButton.setBounds(width, 50, 170, 24);
//		topbandPanel.add(feeReportButton);
//
//		feeReportButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);
//				LinkedHashMap<String,LinkedHashMap<String, String>> studentMapEmpty =  new LinkedHashMap<>();
//				FeesReport.getFeesReport(sessionData, "", "", "", "", "", "", studentMapEmpty, sessionData.getSectionName(), "", "", "", "", false);
//			}
//		});
//		
//        mainPanel.add(topbandPanel, BorderLayout.NORTH);
//
//        // ///////////bottom band/////////////
//        JPanel bottombandPanel = new JPanel() {
//
//            private static final long serialVersionUID = 1L;
//
//            public void paintComponent(Graphics g) {
//
//                Image img = new ImageIcon(img_path + img_mainband).getImage();
//                Dimension size =
//                    new Dimension(screenWidth - 150, screenHeight - 150);
//                setPreferredSize(size);
//                setMinimumSize(size);
//                setMaximumSize(size);
//                setSize(size);
//                setLayout(null);
//                g.drawImage(img, 0, 0, null);
//            }
//        };
//        bottombandPanel.setLayout(null);
//        
//        int tabHeight = 150;
//        
//        ///founder//////////////
//        /*final ImageIcon iconfounder = new ImageIcon(img_path + img_founder);
//        JLabel labelfounder = new JLabel(iconfounder);
//        labelfounder.setBounds(mainCentre - 160, -30, 315, 417);
//        if(show_founder.equalsIgnoreCase("true")){
//        	tabHeight = tabHeight + 180;
//        	bottombandPanel.add(labelfounder);
//        }*/
//        
//        int bottomBandItemHeight = 30;
//        int itemWidth = 40;
//        
//        // /////////////ACADEMIV YEAR //////////////
//        JLabel institute_label = new JLabel("Institute Name   :");
//        institute_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        institute_label.setBounds(itemWidth, bottomBandItemHeight, 150, 50);
//        bottombandPanel.add(institute_label);
//
//        itemWidth = itemWidth + 130;
//        final JTextField instituteText = new JTextField();
//        instituteText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        instituteText.setBounds(itemWidth, bottomBandItemHeight+12, 800, 25);
//        bottombandPanel.add(instituteText);
//        
//        itemWidth = 40;
//        bottomBandItemHeight = bottomBandItemHeight + 40;
//        JLabel school_label = new JLabel("School Name     :");
//        school_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        school_label.setBounds(itemWidth, bottomBandItemHeight, 150, 50);
//        bottombandPanel.add(school_label);
//
//        itemWidth = itemWidth + 130;
//        final JTextField schoolText = new JTextField();
//        schoolText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        schoolText.setBounds(itemWidth, bottomBandItemHeight+12, 800, 25);
//        bottombandPanel.add(schoolText);
//        
//        itemWidth = 40;
//        bottomBandItemHeight = bottomBandItemHeight + 40;
//        JLabel schoolAdd_label = new JLabel("School Address :");
//        schoolAdd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        schoolAdd_label.setBounds(itemWidth, bottomBandItemHeight, 150, 50);
//        bottombandPanel.add(schoolAdd_label);
//
//        itemWidth = itemWidth + 130;
//        final JTextField schoolAddText = new JTextField();
//        schoolAddText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        schoolAddText.setBounds(itemWidth, bottomBandItemHeight+12, 800, 25);
//        bottombandPanel.add(schoolAddText);
//        
//        itemWidth = 40;
//        bottomBandItemHeight = bottomBandItemHeight + 40;
//        JLabel headerShort_label = new JLabel("Header Short Name :");
//        headerShort_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        headerShort_label.setBounds(itemWidth, bottomBandItemHeight, 300, 50);
//        bottombandPanel.add(headerShort_label);
//
//        itemWidth = itemWidth + 160;
//        final JTextField headerShortText = new JTextField();
//        headerShortText.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        headerShortText.setBounds(itemWidth, bottomBandItemHeight+12, 300, 25);
//        bottombandPanel.add(headerShortText);
//        
//        itemWidth = itemWidth + 460;
//        bottomBandItemHeight = bottomBandItemHeight;
//        JButton addButton = new JButton("Add");
//        addButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
//        addButton.setBounds(itemWidth, bottomBandItemHeight+12, 90, 25);
//        bottombandPanel.add(addButton);
//        
//        bottomBandItemHeight = bottomBandItemHeight + 60;
//        JSeparator jSeparator1  = new JSeparator();
//        jSeparator1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
//        jSeparator1.setBounds(40, bottomBandItemHeight, (screenWidth - 200), 50);
//        bottombandPanel.add(jSeparator1);
//        
//        ////addButton action///////////////////
//        addButton.addKeyListener(new KeyAdapter(){
//            public void keyReleased(KeyEvent e){
//            int keyCode=e.getKeyCode();
//            String inst_name = instituteText.getText();
//            String school_name = schoolText.getText();
//            String school_add = schoolAddText.getText();
//            String short_name = headerShortText.getText();
//            if(keyCode == 10){
//				addToOrder(sessionData,inst_name,school_name,school_add,short_name);
//              }
//            }
//        });
//        
//        addButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				String inst_name = instituteText.getText();
//	            String school_name = schoolText.getText();
//	            String school_add = schoolAddText.getText();
//	            String short_name = headerShortText.getText();
//				addToOrder(sessionData,inst_name,school_name,school_add,short_name);
//			}
//        });
//        
//        /////end order headers///////////////
//        /////////////order panel/////////////
//        
//        JPanel dataPanel = new JPanel() {
//
//            private static final long serialVersionUID = 1L;
//
//            public void paintComponent(Graphics g) {
//
//                Image img = new ImageIcon(img_path + img_databand).getImage();
//                Dimension size =
//                    new Dimension(screenWidth - 200, screenHeight + scrollHeight);//this height is responsible to increase scroll area
//                setPreferredSize(size);
//                setMinimumSize(size);
//                setMaximumSize(size);
//                setSize(size);
//                setLayout(null);
//                g.drawImage(img, 0, 0, null);
//            }
//        };
////      dataPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
//        dataPanel.setAutoscrolls(true);
//        dataPanel.setLayout(null);
//        
////        if (!schoolHeadMap.isEmpty()) {
////	        final JLabel[] srNo_labels = new JLabel[schoolHeadMap.size()];
////	        final JLabel[] orderNo_labels = new JLabel[feesHeadMap.size()];
////	        final JTextField[] feeName_text = new JTextField[feesHeadMap.size()];
////	        final JComboBox[] category_combo = new JComboBox[feesHeadMap.size()];
////	        final JComboBox[] optional_combo = new JComboBox[feesHeadMap.size()];
////	        final JComboBox[] frequency_combo = new JComboBox[feesHeadMap.size()];
////	        final JTextField[] amount_text = new JTextField[feesHeadMap.size()];
////	        final JButton[] edit_buttons = new JButton[feesHeadMap.size()];
////	        final JButton[] save_buttons = new JButton[feesHeadMap.size()];
////	        final JButton[] delete_buttons = new JButton[feesHeadMap.size()];
////	        final JSeparator[] jSeparators  = new JSeparator[feesHeadMap.size()];
////	        
////	        String[] feeCategoryList2 = feeCategory.substring(0,feeCategory.lastIndexOf(",")).split(",");
////	        
////	        int k = 0;
////	        int j = 5;
////	        Set set = feesHeadMap.entrySet();
////			Iterator i = set.iterator();
////			while(i.hasNext()) {
////				Map.Entry me = (Map.Entry)i.next();
////				
////				itemWidth = 20;
////	        	srNo_labels[k] = new JLabel((k+1)+"");
////	        	srNo_labels[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	        	srNo_labels[k].setBounds(itemWidth, j, 120, 25);
////	        	dataPanel.add(srNo_labels[k]);
////	        	
////	        	orderNo_labels[k] = new JLabel(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("order_no").toString());
////	        	dataPanel.add(orderNo_labels[k]);
////	    		
////	        	itemWidth = itemWidth + 50;
////	        	feeName_text[k] = new JTextField(me.getKey().toString().replace("$$", "."));
////	        	feeName_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	        	feeName_text[k].setBounds(itemWidth, j, 200, 25);
////	        	feeName_text[k].setEditable(false);
////	    		dataPanel.add(feeName_text[k]);
////	    		
////	    		itemWidth = itemWidth + 220;
////	    		category_combo[k] = new JComboBox(feeCategoryList2);
////	    		category_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("category").toString());
////	    		category_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	    		category_combo[k].setBounds(itemWidth, j, 150, 25);
////	    		dataPanel.add(category_combo[k]);
////	    		
////	    		itemWidth = itemWidth + 200;
////	    		optional_combo[k] = new JComboBox(optionList);
////	    		optional_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("optional").toString());
////	    		optional_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	    		optional_combo[k].setBounds(itemWidth, j, 70, 25);
////	    		dataPanel.add(optional_combo[k]);
////	    		
////	    		itemWidth = itemWidth + 100;
////	    		frequency_combo[k] = new JComboBox(payFrequencyList);
////	    		frequency_combo[k].setSelectedItem(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("frequency").toString());
////	    		frequency_combo[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	    		frequency_combo[k].setBounds(itemWidth, j, 120, 25);
////	    		dataPanel.add(frequency_combo[k]);
////	    		
////	    		itemWidth = itemWidth + 150;
////	    		amount_text[k] = new JTextField(((LinkedHashMap<?, ?>) feesHeadMap.get(me.getKey())).get("amount").toString());
////	    		amount_text[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	    		amount_text[k].setBounds(itemWidth, j, 70, 25);
////	    		amount_text[k].setEditable(false);
////	    		dataPanel.add(amount_text[k]);
////	    		
////	    		itemWidth = itemWidth + 80;
////	    		edit_buttons[k] = new JButton("Edit");
////	    		edit_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	    		edit_buttons[k].setBounds(itemWidth, j, 70, 25);
////	    		dataPanel.add(edit_buttons[k]);
////	    		
////	    		itemWidth = itemWidth + 80;
////	    		save_buttons[k] = new JButton("Save");
////	    		save_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	    		save_buttons[k].setEnabled(false);
////	    		save_buttons[k].setBounds(itemWidth, j, 70, 25);
////	    		dataPanel.add(save_buttons[k]);
////	    		
////	    		itemWidth = itemWidth + 80;
////	    		delete_buttons[k] = new JButton("Delete");
////	    		delete_buttons[k].setFont(new Font("Book Antiqua", Font.BOLD, 16));
////	    		delete_buttons[k].setBounds(itemWidth, j, 90, 25);
////	    		dataPanel.add(delete_buttons[k]);
////	    		
////	    		jSeparators[k] = new JSeparator();
////	    		jSeparators[k].setFont(new Font("Book Antiqua", Font.BOLD, 18));
////	    		jSeparators[k].setBounds(10, j + 28, (screenWidth - 220), 50);
////	    		dataPanel.add(jSeparators[k]);
////	    		
////	            final int m = k;
////	    		edit_buttons[k].addActionListener(new ActionListener() {
////
////					public void actionPerformed(ActionEvent e) {
////						try {
////							boolean isEditable = true;
////							String optional = optional_combo[m].getSelectedItem().toString();
////							String feesHead = feeName_text[m].getText();
////							
////							if(dbValidate.connectDatabase(sessionData)){
////								isEditable = dbValidate.isFeesEditable(sessionData, academicYear, retStd, sessionData1.getSectionName(), optional, feesHead);
////							}
////							
////							if(isEditable){
////								feeName_text[m].setEditable(true);
////								amount_text[m].setEditable(true);
////								edit_buttons[m].setEnabled(false);
////								save_buttons[m].setEnabled(true);
////							}
////							else{
////								JOptionPane.showMessageDialog(null, "Cannot delete as fee data for "+feesHead+" exist");
////							}
////						} catch (Exception e1) {
////							commonObj.logException(e1);
////						} finally {
////							dbValidate.closeDatabase(sessionData);
////						}
////					}
////				});
////	    		
////	    		optional_combo[k].addActionListener(new ActionListener() {
////
////	    			public void actionPerformed(ActionEvent e) {
////	                	String optionalSel = optional_combo[m].getSelectedItem().toString();
////	                	String payFrequency = frequency_combo[m].getSelectedItem().toString();
////	                	
////	                	if(optionalSel.equalsIgnoreCase("Yes")){
////	                		frequency_combo[m].setSelectedItem("Occasionally");
////	                	}
////	                	else if(payFrequency.equalsIgnoreCase("Occasionally")){
////	                		frequency_combo[m].setSelectedItem("Monthly");
////	                	}
////	    			}
////	            });
////	    		
////	    		frequency_combo[k].addActionListener(new ActionListener() {
////
////	    			public void actionPerformed(ActionEvent e) {
////	    				String payFrequency = frequency_combo[m].getSelectedItem().toString();
////	                	if(payFrequency.equalsIgnoreCase("Occasionally")){
////	                		optional_combo[m].setSelectedItem("Yes");
////	                	}
////	                	else{
////	                		optional_combo[m].setSelectedItem("No");
////	                	}
////	    			}
////	            });
////	    		
////	    		save_buttons[k].addActionListener(new ActionListener() {
////
////					public void actionPerformed(ActionEvent e) {
////						try {
////							
////							String orderNoSel = orderNo_labels[m].getText();
////							String feeNameSel = feeName_text[m].getText().replace(".", "$$");
////							String categorySel = category_combo[m].getSelectedItem().toString();
////							String optionalSel = optional_combo[m].getSelectedItem().toString();
////							String frequencySel = frequency_combo[m].getSelectedItem().toString();
////							String amountSel = amount_text[m].getText();
////							
////							if(dbValidate.connectDatabase(sessionData)){
////								dbValidate.addFeeName(sessionData, feeNameSel, categorySel, "", optionalSel, frequencySel, amountSel, 
////										true, retStd, academicYear, sessionData1.getSectionName(), orderNoSel, false, "");
////							}
////							
////							feeName_text[m].setEditable(false);
////							amount_text[m].setEditable(false);
////							edit_buttons[m].setEnabled(true);
////							save_buttons[m].setEnabled(false);
////						} catch (Exception e1) {
////							commonObj.logException(e1);
////						} finally {
////							dbValidate.closeDatabase(sessionData);
////						}
////					}
////				});
////	    		
////	    		delete_buttons[k].addActionListener(new ActionListener() {
////
////					public void actionPerformed(ActionEvent e) {
////						///delete fee name/////
////						try {
////							boolean isEditable = true;
////							String optional = optional_combo[m].getSelectedItem().toString();
////							String feesHead = feeName_text[m].getText();
////							
////							if(dbValidate.connectDatabase(sessionData)){
////								isEditable = dbValidate.isFeesEditable(sessionData, academicYear, retStd, sessionData1.getSectionName(), optional, feesHead);
////							}
////							
////							if(isEditable){
////								int reply = 0;
////								reply = JOptionPane.showConfirmDialog(null, "Would You Like to delete "+feeName_text[m].getText()+"?", "Confirm validate", JOptionPane.YES_NO_OPTION);
////								
////								if(dbValidate.connectDatabase(sessionData) && reply == JOptionPane.YES_OPTION){
////									dbValidate.deleteFeeName(sessionData, orderNo_labels[m].getText());
////									
////									frame.setVisible(false);
////									new MultipleFeesHead(sessionData);
////								}
////							}
////							else{
////								JOptionPane.showMessageDialog(null, "Cannot delete as fee data for "+feesHead+" exist");
////							}
////						} catch (Exception e1) {
////							commonObj.logException(e1);
////						} finally {
////							dbValidate.closeDatabase(sessionData);
////						}
////					}
////				});
////	    		
////	    		j = j + 32;
////	    		k++;
////	        }
////			
////			bottombandPanel.revalidate();
////			bottombandPanel.repaint();
////        }
//        
//        bottomBandItemHeight = bottomBandItemHeight + 30;
//        JScrollPane jsp;
//        int height = 380;
//		jsp = new JScrollPane(dataPanel, 
//				   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  
//				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		jsp.setBounds(30, bottomBandItemHeight + 10, screenWidth - 180, screenHeight - height);
//		
//        bottombandPanel.add(jsp, BorderLayout.SOUTH);
//        ///////////////end order panel////////////
//        
//        mainPanel.add(bottombandPanel, BorderLayout.SOUTH);
//        panel.add(mainPanel, BorderLayout.EAST);
//    }
    
    public static void addToOrder(SessionData sessionData,String fee_name,String subFee_name,String amount, 
    		String academicYear, String std, boolean allStd){
    	
    	boolean validateFields = true;
		try {
			subFee_name = commonObj.replaceCommaApostrophy(subFee_name);
			
			if (subFee_name.isEmpty() || amount.isEmpty() || fee_name.equalsIgnoreCase("Select")) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Please enter all fields.");
			}
			else if (commonObj.checkComma(subFee_name)) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Institute Name cannot contain |:;");
			} else if (subFee_name.length() > 150) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, commonObj.charExceeded("Sub Fee Name", subFee_name, 150));
			} else if (!commonObj.validateNumber(amount)) {
				validateFields = false;
				JOptionPane.showMessageDialog(null, "Please enter valid amount");
			}
			
			if(validateFields){
				if(dbValidate.connectDatabase(sessionData)){
					boolean addFlag = dbValidate.addMultipleHead(sessionData, fee_name, subFee_name, 
							amount, academicYear,std, "insert", "", allStd);
					
					if(addFlag == true){
						frame.setVisible(false);
						MultipleFeesHead.getMultipleFeesHead(sessionData, academicYear, std);
					}
				}
			}
		} catch (Exception e1) {
			commonObj.logException(e1);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
	}
}
