package org.com.maauli;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
// import com.lowagie.text.DocumentException;
// import com.lowagie.text.pdf.TextField;
import org.com.accesser.SessionData;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;

public class RemarksEntry extends JFrame {

	private static RemarksEntry marksEntry = new RemarksEntry();
	
	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JFrame frame = null;

	static JComboBox studentcombo;

	static int retCount = 0;

	static int scrollHeight = 0;

	static int entrytCnt = 0;

	static int selectAllCount = 0;

	static LinkedHashMap<String,LinkedHashMap<String, String>> studentRemarkMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();

	static List<String> findSubList = new ArrayList();

	static boolean setEditable = false;

	static String stdClass = "";

	static String academicYearClass = "";

	static String selectStd = "Select";

	static String selectDiv = "Select";

	static String selectYear = "Year";

	static String divClass = "";

	static String user_name = "";

	static String user_role = "";

	static String fromClass, toClass;

	static String lastClass, firstClass, fatherClass;

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

	static String examClass = "Select", examType = "";
	
	static String examCategory = "";

	static String subjectClass = "";

	static String typeClassDisp = "Select";

	static String type = "";
	
	static LinkedHashMap<String,LinkedHashMap<String, String>> maxSubMarks;
	
	static boolean updateFlag = true;
	
	static String fromDateClass = "";

	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static Logger logger = Logger.getLogger(RemarksEntry.class.getName());
	
	static TreeMap<String, String> studentLCMap = new TreeMap<String, String>();
	
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

    /*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private RemarksEntry() {
	}

	/* Static 'instance' method */
	public static RemarksEntry getInstance() {
		return marksEntry;
	}
	
	/* Other methods protected by singleton-ness */
	protected static void getRemarksEntry(SessionData sessionData1, String retStd, String academicYear, boolean retEditable, String retDiv, 
			String retFrom, String retTo, String retLast, String retFirst, String retFather, String retExam, String retSub, String retType, String sec, 
			String retUserName, String retUserRole, String retDate, final String retGrNo, String isCreate) {

		logger.info("===========Inside remarks Entry========");
		System.gc();
		String sem = "", max = "", retRemarkList = "";
		String[] remList = null;
		maxSubMarks = new LinkedHashMap<String,LinkedHashMap<String, String>>();
		LinkedHashMap<String,String> marksDetailsMap = new LinkedHashMap<String,String>();
		studentLCMap.clear();
		fromDateClass = retDate;
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		section = sec;
		studentRemarkMap.clear();
		std = bundle.getString(section.toUpperCase() + "_STD");
		div = bundle.getString(section.toUpperCase() + "_DIV");
		secName = bundle.getString(section.toUpperCase() + "_SEC");
		examCategory = bundle.getString("EXAM_CATEGORY");
		examCategory = examCategory.substring(0, examCategory.lastIndexOf(","));
		
		String examTypeList = "EXAM_TYPE_5";
		if((retStd.equalsIgnoreCase("XI") || 
				retStd.equalsIgnoreCase("XII")) && retExam.equalsIgnoreCase("Semester 1")){
			examTypeList = "EXAM_TYPE_1";
		}
		else if((retStd.equalsIgnoreCase("XI") || 
				retStd.equalsIgnoreCase("XII")) && retExam.equalsIgnoreCase("Semester 2")){
			examTypeList = "EXAM_TYPE_2";
		}
		else if((retStd.equalsIgnoreCase("X") || retStd.equalsIgnoreCase("IX")) && retExam.equalsIgnoreCase("Semester 1")){
			examTypeList = "EXAM_TYPE_3";
		}
		else if((retStd.equalsIgnoreCase("X") || retStd.equalsIgnoreCase("IX")) && retExam.equalsIgnoreCase("Semester 2")){
			examTypeList = "EXAM_TYPE_4";
		}
		
		img_path = bundle.getString("IMAGE_PATH");
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

    	examType = "";
		if (!retExam.equalsIgnoreCase("")) {
			examClass = retExam;
			if(retExam.equalsIgnoreCase("Semester 1")) {
				examType = "SEM1";
			} else if(retExam.equalsIgnoreCase("Semester 2")) {
				examType = "SEM2";
			} else if(retExam.equalsIgnoreCase("FINAL")) {
				examType = "FINAL";
			}
		}
		subjectClass = retSub;
		typeClassDisp = retType;
		
		fromClass = retFrom;
		toClass = retTo;
		academicYearClass = academicYear;
		lastClass = retLast;
		firstClass = retFirst;
		fatherClass = retFather;
		stdClass = retStd;
		divClass = retDiv;
		setEditable = retEditable;

		if (!retStd.equalsIgnoreCase("")) {
			selectStd = stdClass;
		}
		else{
			selectStd = "Select";
		}
		if (!retDiv.equalsIgnoreCase("")) {
			selectDiv = divClass;
		}
		else{
			selectDiv = "Select";
		}

		try {
			if(dbValidate.connectDatabase(sessionData)){
				if(isCreate.equalsIgnoreCase("")) {
					studentLCMap = dbValidate.findStudentLCList(sessionData1, "", stdClass, divClass, "", "", "", academicYearClass, "", "", section);
					
					if(examClass.equalsIgnoreCase("Semester 1")){
						sem = "sem1";
					} else if(examClass.equalsIgnoreCase("Semester 2")){
						sem = "sem2";
					}
					
					if (!subjectClass.equalsIgnoreCase("") && retType.equalsIgnoreCase("Remark")) {
						if(!dbValidate.findColumn(sessionData1, "result_data", subjectClass+"_REMSEM1")) {
							dbValidate.addRemarkColumn(sessionData, subjectClass);
						}
						LinkedHashMap subjectTitleMap = new LinkedHashMap<>();
						subjectTitleMap.put(subjectClass, subjectClass);
						studentRemarkMap = dbValidate.getRemarkResultMap(sessionData1, academicYearClass, stdClass, divClass, examClass, subjectTitleMap);
					}
					
					if(!retType.equalsIgnoreCase("Remark")) {
						marksDetailsMap = maxSubMarks.get(subjectClass);
						if(marksDetailsMap.get(sem+"_"+retType.toLowerCase()) != null){
							max = marksDetailsMap.get(sem+"_"+retType.toLowerCase());
						}
					}
				}
				else {
					retRemarkList = sessionData.getConfigMap().get("REMARK_LIST_"+stdClass+"_"+subjectClass);
				}
			}
		} catch (Exception e1) {
            logger.info("Exception findMarksEntryList ===>>>" + e1);
        } finally {
        	dbValidate.closeDatabase(sessionData);
        }

		final String maxMarksFromMap = max;
		
//		academicYearClass = commonObj.getAcademicYear(commonObj.getCurrentDate());;
//		logger.info(stdClass + "::" + academicYearClass);

		entrytCnt = 0;
		logger.info("entrytCnt===>" + entrytCnt);

		if(studentRemarkMap.size() > 10) {
			scrollHeight = (studentRemarkMap.size() - 6) * 30; // to adjust the perfect
			// scroll height
		}
		else if(retRemarkList != null && retRemarkList.length()>10){
			scrollHeight = (retRemarkList.length()); // to adjust the perfect
			// scroll height
		}
		if (scrollHeight < 0)
			scrollHeight = 0;

//		setVisible(false);
//		dispose();
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame = new JFrame("Welcome to "+sessionData1.getAppName());
		final Common commonObj = new Common();
		screenWidth = commonObj.screeWidth();
		screenHeight = commonObj.screeHeight();
		mainCentre = (screenWidth - 150) / 2;

		frame.setSize(screenWidth, screenHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel panelHome = new JPanel();

		frame.add(panelHome);
//		placeComponents(panel);



		panelHome.removeAll();///to remve entire panel
		panelHome.setLayout(new BorderLayout());
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
					panelHome.removeAll();///to remve entire panel
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
						panelHome.removeAll();///to remve entire panel
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
        final ImageIcon iconhome = new ImageIcon(img_path + img_home);
        JLabel labelhome = new JLabel(iconhome);
        labelhome.setBounds(45, 10, 60, 60);
        leftPanel.add(labelhome);
        
        labelhome.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				try {
					frame.setVisible(false);
					panelHome.removeAll();///to remve entire panel
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
				panelHome.removeAll();///to remve entire panel
				new Student(sessionData, section, user_name, user_role);
			}
		});

		JButton staffButton = new JButton("Staff");
		staffButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		staffButton.setBounds(10, 150, 130, 35);
//		leftPanel.add(staffButton);

		staffButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// frame.setVisible(false);
				// new Welcome();
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
				panelHome.removeAll();///to remve entire panel
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
		leftPanel.add(searchButton);

		searchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				LinkedHashMap<String,LinkedHashMap<String, String>> studMap = new LinkedHashMap<String,LinkedHashMap<String, String>>();
				new FindStudent(sessionData, "", "", "", "", "", "", studMap, section,
						user_name, user_role, false);
				frame.setVisible(false);
				panelHome.removeAll();///to remve entire panel
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

		JButton classAllotButton = new JButton("Class Allot");
		classAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		classAllotButton.setBounds(130, 50, 120, 24);
		topbandPanel.add(classAllotButton);

		classAllotButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				panelHome.removeAll();///to remve entire panel
				new ClassAllotment(sessionData, "", "", "", "", "", "", null, false, "", "",
						"", section, user_name, user_role, "");
			}
		});

		JButton subAllotButton = new JButton("Subject Allot");
		subAllotButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		subAllotButton.setBounds(260, 50, 130, 24);// 620, 50, 150, 24
		topbandPanel.add(subAllotButton);

		subAllotButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				panelHome.removeAll();///to remve entire panel
				List subAllotList = new ArrayList();
				new MarksAllotment(sessionData, "", subAllotList, "", "", false, section,
						user_name, user_role, "");
			}
		});

		JButton marksButton = new JButton("Marks Entry");
		marksButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		marksButton.setBounds(400, 50, 130, 24);
		topbandPanel.add(marksButton);

		marksButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
                List markList = new ArrayList();
                new MarksEntry(sessionData, "", markList, "", false, "", "", "", "", "", "",
                    "", "", "", section, user_name, user_role, "", "");
			}
		});

		JButton remarkButton = new JButton("Remarks");
		remarkButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		remarkButton.setBounds(540, 50, 100, 24); // 300, 50, 150, 24
		remarkButton.setBackground(Color.GREEN);
		remarkButton.setEnabled(true);
		topbandPanel.add(remarkButton);

		remarkButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
                List markList = new ArrayList();
                RemarksEntry remarksEntryObject = RemarksEntry.getInstance();
                remarksEntryObject.getRemarksEntry(sessionData, "", "", false, "", "", "", "", "", "", "",
                    "", "", section, user_name, user_role, "", "", "");
            }
        });
		
		JButton resultPrintButton = new JButton("Result");
		resultPrintButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		resultPrintButton.setBounds(650, 50, 90, 24); // 300, 50, 150, 24
		topbandPanel.add(resultPrintButton);

		resultPrintButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            	frame.setVisible(false);
            	panelHome.removeAll();///to remve entire panel
                List findList = new ArrayList();
				new Result(sessionData, "", "", "", "", "", "",	findList, false, false, "", section, user_name, user_role, "", "", "", 
						findList, findList, findList);
            }
        });

		JButton attendanceButton = new JButton("Attendance");
		attendanceButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		attendanceButton.setBounds(750, 50, 130, 24); // 300, 50, 150, 24
		topbandPanel.add(attendanceButton);

		attendanceButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				List studentList = new ArrayList();
				new AttendanceUpdate(sessionData, "", "", "", section, user_name, user_role, studentList, 
						"","","","",false,"",true,"", false);
				frame.setVisible(false);
				panelHome.removeAll();///to remve entire panel
			}
		});

		JButton reportButton = new JButton("Reports");
		reportButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		reportButton.setBounds(890, 50, 100, 24);
		topbandPanel.add(reportButton);

		reportButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				LinkedHashMap studentHmap = new LinkedHashMap();
				new Reports(sessionData, "", "", "", section, user_name, user_role, studentHmap, "", "", "");
				frame.setVisible(false);
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
				Dimension size = new Dimension(screenWidth - 156, 150);// change height to change scrolling area
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
		final JRadioButton academic_radio = new JRadioButton();
		academic_radio.setBounds(30, 14, 20, 20);
		academic_radio.setSelected(true);
		academic_radio.setEnabled(false);
		findPanel.add(academic_radio);

		JLabel academic_label = new JLabel("Academic :");
		academic_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		academic_label.setBounds(60, 00, 100, 50);
		findPanel.add(academic_label);

		String yearList = "";
		try {
			if (dbValidate.connectDatabase(sessionData)) {
				yearList = dbValidate.getAcademicYearList(sessionData, "HS_GENERAL_REGISTER", "ACADEMIC_YEAR").toString();
			}
		}  catch (Exception e12) {
			logger.info("Exception logoutButton ===>>>" + e12);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
		
		yearList = commonObj.getAcademicYear(commonObj.getCurrentDate()) + "," + yearList;

		String academicYearList[] = yearList.split(",");
		final JComboBox academicYear_combo = new JComboBox(academicYearList);
		academicYear_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		if(!academicYearClass.equalsIgnoreCase("")){
			academicYear_combo.setSelectedItem(academicYearClass);
		}
		academicYear_combo.setBounds(150, 12, 120, 25);
		findPanel.add(academicYear_combo);
		// //////////////////////////////////
		// /////////////Std//////////////
		final JRadioButton std_radio = new JRadioButton();
		std_radio.setBounds(310, 14, 20, 20);
		findPanel.add(std_radio);

		JLabel admittedStd_label = new JLabel("Std :");
		admittedStd_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		admittedStd_label.setBounds(340, 00, 70, 50);
		findPanel.add(admittedStd_label);

		std = selectStd + "," + std;
		String[] stdList = std.split(",");
		// String StdList[] = {selectStd,"IV","V","VI","VII","VIII","IX","X"};
		final JComboBox Std_combo = new JComboBox(stdList);
		Std_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		Std_combo.setBounds(400, 12, 90, 25);
		findPanel.add(Std_combo);

		// /////////////Div//////////////
		JLabel presentDiv_label = new JLabel("Div :");
		presentDiv_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		presentDiv_label.setBounds(520, 00, 70, 50);
		findPanel.add(presentDiv_label);

		div = selectDiv + "," + div;
		String[] divList = div.split(",");
		// String presentDivList[] = {"Select","A","B","C","D","E"};
		final JComboBox presentDiv_combo = new JComboBox(divList);
		presentDiv_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		presentDiv_combo.setBounds(580, 12, 90, 25);
		findPanel.add(presentDiv_combo);

		final JRadioButton create_radio = new JRadioButton();
		create_radio.setBounds(700, 14, 20, 20);
		if(!isCreate.equalsIgnoreCase("")) {
			create_radio.setSelected(true);
		}
		findPanel.add(create_radio);
		
		JLabel createType_label = new JLabel("Create Remark");
		createType_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		createType_label.setBounds(730, 00, 150, 50);
		findPanel.add(createType_label);
		
		/*// /////////////GR No.//////////////
		// /////////GR radio///////////////
		final JRadioButton gr_no_radio = new JRadioButton();
		gr_no_radio.setBounds(30, 55, 20, 20);
		findPanel.add(gr_no_radio);
				
		JLabel gr_no_label = new JLabel("G.R. No.:");
		gr_no_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		gr_no_label.setBounds(60, 40, 70, 50);
		findPanel.add(gr_no_label);

		final JTextField gr_no_text = new JTextField();
		gr_no_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		gr_no_text.setBounds(140, 55, 130, 25);
		findPanel.add(gr_no_text);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gr_no_text.requestFocus();
			}
		});

		gr_no_text.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent event) {
				String grString = gr_no_text.getText();
				if (grString.length() < 7 && grString.length() !=  0) {
					gr_no_text.setText(commonObj.appendZero(grString));
				} else if(grString.length() > 6) {
					grString = grString.substring(0, 7);
					gr_no_text.setText(grString);
				}
			}
		});*/

		// /////////////Exam//////////////
		final JRadioButton exam_radio = new JRadioButton();
		exam_radio.setBounds(30, 55, 20, 20);
		exam_radio.setSelected(true);
		exam_radio.setEnabled(false);
		findPanel.add(exam_radio);

		final JLabel exam_label = new JLabel("Exam");
		exam_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		exam_label.setBounds(60, 40, 150, 50);
		findPanel.add(exam_label);

		String[] examList = examCategory.split(",");
		final JComboBox exam_combo = new JComboBox(examList);
		if(examClass.equalsIgnoreCase("Final") && (stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XII"))){
			exam_combo.addItem("Final");
		}
		else{
			exam_combo.removeItem("Final");
		}
		exam_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		exam_combo.setBounds(120, 55, 120, 25);
		exam_combo.setSelectedItem(examClass);
		findPanel.add(exam_combo);
		
		////% in board exam/////////////////
		final JLabel percent_label = new JLabel("Note : Please enter % obtained in board exam on submit.");
		percent_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		percent_label.setForeground(Color.RED);
		percent_label.setBounds(310, 40, 500, 20);

		// /////////////type//////////////
		final JRadioButton type_radio = new JRadioButton();
		type_radio.setBounds(310, 55, 20, 20);
		type_radio.setSelected(true);
		type_radio.setEnabled(false);
		findPanel.add(type_radio);

		final JLabel type_label = new JLabel("Type");
		type_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		type_label.setBounds(340, 40, 150, 50);
		findPanel.add(type_label);

//		type = "Remark,Improvement,Progress";
		type = "Remark";
		String[] typeData = type.split(",");
		final JComboBox type_combo = new JComboBox(typeData);
		type_combo.setSelectedItem(typeClassDisp);
		type_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		type_combo.setBounds(390, 55, 140, 25);
		findPanel.add(type_combo);

		// /////////////Subject//////////////
		final JRadioButton subject_radio = new JRadioButton();
		subject_radio.setBounds(610, 55, 20, 20);
		subject_radio.setSelected(true);
		subject_radio.setEnabled(false);
		findPanel.add(subject_radio);

		final JLabel subject_label = new JLabel("Subject");
		subject_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		subject_label.setBounds(640, 40, 150, 50);
		findPanel.add(subject_label);

		final JComboBox subject_combo = new JComboBox();
		subject_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		subject_combo.setBounds(700, 55, 220, 25);
		findPanel.add(subject_combo);
				
		// ////////update subject list//////////////////////////

		if (!stdClass.equalsIgnoreCase("")
				&& !stdClass.equalsIgnoreCase("Select") && dbValidate.connectDatabase(sessionData1)) {
			// presentDiv_combo.setEnabled(true);
			try {
				findSubList = dbValidate.findSubjectTitleList(sessionData1, retStd, "", academicYear);
				findSubList.remove("SELECT SUBJECT TITLE");
				findSubList.remove("CREATE NEW TITLE");
				String[] subjectArr = findSubList.toArray(new String[findSubList.size()]);
				subject_combo.removeAllItems();
				subject_combo.addItem("All");

				for (int z = 0; z < subjectArr.length; z++) {
					subject_combo.addItem(subjectArr[z]);
				}
				subject_combo.setSelectedItem(subjectClass);
			} catch (Exception e2) {
				logger.info("Exceptyion findSubList : " + e2);
			}
		} else {
			try {
				findSubList = dbValidate.findSubjectList(sessionData, (String) Std_combo.getSelectedItem(), academicYearClass);
				String[] subjectArr = findSubList
						.toArray(new String[findSubList.size()]);
				// String subjectData[] = (subjectArr);
				subject_combo.removeAllItems();
				subject_combo.addItem("All");
				if (!subjectClass.equalsIgnoreCase(""))
					subject_combo.addItem(subjectClass);

				for (int z = 0; z < subjectArr.length; z++) {
					subject_combo.addItem(subjectArr[z]);
				}
			} catch (Exception e2) {
				logger.info("Exceptyion findSubList : " + e2);
			}
		}

		if(!stdClass.equalsIgnoreCase("All") && !stdClass.equalsIgnoreCase("Select") && 
	    		!stdClass.equalsIgnoreCase("") && dbValidate.connectDatabase(sessionData)){
			presentDiv_combo.removeAllItems();
			String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdClass, section, 
					"PRESENT_DIV", "PRESENT_STD", "class_allotment",academicYearClass);
			
			for (String retval: divAvailabe.split(",")) {
				presentDiv_combo.addItem(retval);
			}
			presentDiv_combo.setSelectedItem(divClass);
		}
		
		if(examClass.equalsIgnoreCase("Final") && (stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XII"))){
			findPanel.remove(subject_radio);
			findPanel.remove(subject_label);
			findPanel.remove(subject_combo);
			findPanel.remove(type_radio);
			findPanel.remove(type_label);
			findPanel.remove(type_combo);
			findPanel.add(percent_label);
			findPanel.revalidate();
			findPanel.repaint();
		}
		else{
			findPanel.add(subject_radio);
			findPanel.add(subject_label);
			findPanel.add(subject_combo);
			findPanel.add(type_radio);
			findPanel.add(type_label);
			findPanel.add(type_combo);
			findPanel.remove(percent_label);
			findPanel.revalidate();
			findPanel.repaint();
		}
		// ////////Std_combo action//////////////////////////
		Std_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String stdSel = (String) Std_combo.getSelectedItem();
				String academicYearSel = (String) academicYear_combo.getSelectedItem();
				presentDiv_combo.removeAllItems();
				if (!stdSel.equalsIgnoreCase("") && !stdSel.equalsIgnoreCase("Select") && dbValidate.connectDatabase(sessionData)) {
					try {
						findSubList = dbValidate.findSubjectTitleList(sessionData1, stdSel, "", academicYearSel);
						findSubList.remove("SELECT SUBJECT TITLE");
						findSubList.remove("CREATE NEW TITLE");
						String[] subjectArr = findSubList.toArray(new String[findSubList.size()]);
						subject_combo.removeAllItems();
						subject_combo.addItem("All");
						for (int z = 0; z < subjectArr.length; z++) {
							subject_combo.addItem(subjectArr[z]);
						}
						/*if(stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XII")){
							exam_combo.addItem("Final");
						}
						else{
							exam_combo.removeItem("Final");
							findPanel.add(subject_radio);
							findPanel.add(subject_label);
							findPanel.add(subject_combo);
							findPanel.add(type_radio);
							findPanel.add(type_label);
							findPanel.add(type_combo);
							findPanel.remove(percent_label);
							findPanel.revalidate();
							findPanel.repaint();
						}*/
//						exam_combo.setSelectedIndex(0);
						
					} catch (Exception e2) {
						logger.error("Exceptyion findSubList : " + e2);
					}
				}
				
				try{
					if(!stdSel.equalsIgnoreCase("All") && !stdSel.equalsIgnoreCase("Select") && dbValidate.connectDatabase(sessionData)){
						presentDiv_combo.removeAllItems();
						presentDiv_combo.addItem("Select");
						String divAvailabe = dbValidate.getDistinctDiv(sessionData, stdSel, section, 
								"PRESENT_DIV", "PRESENT_STD", "class_allotment",academicYearSel);
						
						for (String retval: divAvailabe.split(",")) {
							presentDiv_combo.addItem(retval);
						}
					}
				} catch (Exception e1) {
					logger.info("Exception insertFormData ===>>>" + e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});

		// ////////std_radio.addActionListener///////////
		std_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (std_radio.isSelected()) {
//					gr_no_radio.setSelected(false);
//					gr_no_text.setEnabled(false);
//					gr_no_text.setText("");
					Std_combo.setEnabled(true);
					presentDiv_combo.setEnabled(true);
				} else {
//					gr_no_text.setEnabled(true);
					Std_combo.setSelectedIndex(0);
					presentDiv_combo.setSelectedIndex(0);
					Std_combo.setEnabled(false);
					presentDiv_combo.setEnabled(false);
					/*name_radio.setEnabled(true);
					lastName_text.setEnabled(true);
					firstName_text.setEnabled(true);
					fatherName_text.setEnabled(true);*/
				}
			}
		});

		/*// //////gr radio action//////////////
		gr_no_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (gr_no_radio.isSelected()) {
					gr_no_text.setEnabled(true);
					Std_combo.setSelectedIndex(0);
//					presentDiv_combo.setSelectedIndex(0);
					std_radio.setSelected(false);
					Std_combo.setEnabled(false);
					presentDiv_combo.setEnabled(false);

				} else {
					gr_no_radio.setSelected(false);
					gr_no_text.setEnabled(false);
					gr_no_text.setText("");
				}
			}
		});*/

		// ////////exam_radio.addActionListener///////////
		exam_radio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (exam_radio.isSelected()) {
					exam_radio.setSelected(true);
					academicYear_combo.setEnabled(false);
					// presentDiv_combo.setEnabled(false);
				}
			}
		});

		// ////////exam_combo action//////////////////////////
		exam_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

/*				logger.info("inside exam_combo.addActionListener");
				String examSel = (String) exam_combo.getSelectedItem();
				String stdSel = (String) Std_combo.getSelectedItem();
				
				if (!examSel.equalsIgnoreCase("") && !examSel.equalsIgnoreCase("Select") &&  
						(stdSel.equalsIgnoreCase("IX") || stdSel.equalsIgnoreCase("X") || 
								stdSel.equalsIgnoreCase("XI") || stdSel.equalsIgnoreCase("XII"))) {
					try {
						type_combo.removeAllItems();
						type_combo.addItem("Select");
						type_combo.addItem("All");
						if(!stdSel.equalsIgnoreCase("IX") && !stdSel.equalsIgnoreCase("X")){
							type_combo.addItem("ORAL");
							type_combo.addItem("ASSIGN");
							type_combo.addItem("WRITE");
							if (examSel.equalsIgnoreCase("Semester 1")) {
								type_combo.addItem("FUT");
								type_combo.addItem("PRACT");
							} else if (examSel.equalsIgnoreCase("Semester 2")) {
								type_combo.addItem("SUT");
								type_combo.addItem("PRACT");
							}
						}
						else{
							if (examSel.equalsIgnoreCase("Semester 1")) {
								type_combo.addItem("FUT");
							} else if (examSel.equalsIgnoreCase("Semester 2")) {
								type_combo.addItem("SUT");
							}
							type_combo.addItem("PRES");
							type_combo.addItem("MCAP");
							type_combo.addItem("WRITE");
							type_combo.addItem("PRACT");
							type_combo.addItem("ACT");
						}
						
						if(examSel.equalsIgnoreCase("Final") && (stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XII"))){
							findPanel.remove(subject_radio);
							findPanel.remove(subject_label);
							findPanel.remove(subject_combo);
							findPanel.remove(type_radio);
							findPanel.remove(type_label);
							findPanel.remove(type_combo);
							findPanel.add(percent_label);
							findPanel.revalidate();
							findPanel.repaint();
						}
						else{
							findPanel.add(subject_radio);
							findPanel.add(subject_label);
							findPanel.add(subject_combo);
							findPanel.add(type_radio);
							findPanel.add(type_label);
							findPanel.add(type_combo);
							findPanel.remove(percent_label);
							findPanel.revalidate();
							findPanel.repaint();
						}
					} catch (Exception e2) {
						logger.info("Exception exam_combo : " + e2);
					}
				} 
				else if (!examSel.equalsIgnoreCase("") && !examSel.equalsIgnoreCase("Select") &&  
						(!stdSel.equalsIgnoreCase("IX") && !stdSel.equalsIgnoreCase("X") && 
								!stdSel.equalsIgnoreCase("XI") && !stdSel.equalsIgnoreCase("XII"))) {
					try {
						if(type_combo != null) {
							type_combo.removeAllItems();
						}
						type_combo.addItem("Select");
						type_combo.addItem("All");
						type_combo.addItem("DOBS");
						type_combo.addItem("TEST");
						type_combo.addItem("ORAL");
						type_combo.addItem("ASSIGN");
						type_combo.addItem("PRACT");
						type_combo.addItem("ACT");
						type_combo.addItem("PROJECT");
						type_combo.addItem("OTHER");
						type_combo.addItem("SUM_ORAL");
						type_combo.addItem("SUM_PRACT");
						type_combo.addItem("SUM_WRITE");
						type_combo.addItem("REMARK");
					} catch (Exception e2) {
						logger.info("Exception exam_combo : " + e2);
					}
				}*/

			}
		});

		
		std_radio.setSelected(true);
//		gr_no_text.setEnabled(false);

		// /////////////Submit//////////////
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(930, 60, 130, 25);
		findPanel.add(submitButton);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean validateFields = true;
				boolean isCreateSel = create_radio.isSelected();
				String academicSel = "", createType = "";
				String std = "";
				String div = "";
				String from = "";
				String to = "";
				String firstName = "";
				String lastName = "";
				String fatherName = "";
				String exam = "";
				String subject = "";
				String type = "";
				String grNo = "";
				if(isCreateSel){
					createType = "create";
				}
				
				academicSel = (String) academicYear_combo.getSelectedItem() == null ? "" : (String) academicYear_combo.getSelectedItem();
				std = (String) Std_combo.getSelectedItem() == null ? "" : (String) Std_combo.getSelectedItem();
				div = (String) presentDiv_combo.getSelectedItem() == null ? "" : (String) presentDiv_combo.getSelectedItem();
				exam = (String) exam_combo.getSelectedItem() == null ? "" : (String) exam_combo.getSelectedItem();
				subject = (String) subject_combo.getSelectedItem() == null ? "" : (String) subject_combo.getSelectedItem();
				type = (String) type_combo.getSelectedItem() == null ? "" : (String) type_combo.getSelectedItem();

				if (academicSel.equalsIgnoreCase("Year")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Academic year.");
				} else if (std.equalsIgnoreCase("Select")) { 
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Std.");
				} else if (div.equalsIgnoreCase("Select")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Div.");
				} 
				else if (exam.equalsIgnoreCase("Select") || exam.equalsIgnoreCase("")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select Exam");
				} 
				else if (isCreateSel && subject.equalsIgnoreCase("All")) {
					validateFields = false;
					JOptionPane.showMessageDialog(null, "Please select one Subject");
				} 
				else if (!std.equalsIgnoreCase("X") && !std.equalsIgnoreCase("XII")) {
					if((type.equalsIgnoreCase("Select") || type.equalsIgnoreCase("") || type.equalsIgnoreCase("All"))) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Type");
					}
				}

				if (academicSel.equalsIgnoreCase("Year")) {
					academicSel = "";
				}
				if(std.equalsIgnoreCase("Select")){
					std = "";
					div = "";
				}
				
				logger.info("academicSel == " + academicSel);
				logger.info("validateFields flag..." + validateFields);
				if (validateFields) {
					try {
						stdClass = std;
						// academicYearClass = academicSel;
						List markList = new ArrayList();

						if(type.equalsIgnoreCase("Remark")){
							dbValidate.addRemarkColumn(sessionData, subject);
							frame.setVisible(false);
							panelHome.removeAll();///to remve entire panel
							
							RemarksEntry remarksEntryObject = RemarksEntry.getInstance();
							remarksEntryObject.getRemarksEntry(sessionData, std, academicSel, false, div, from, to, lastName, firstName, 
			                		fatherName, exam, subject, type, section, user_name, user_role, "", grNo, createType);
						}
					} catch (Exception e1) {
						commonObj.logException(e1);
						new Student(sessionData, section, user_name, user_role);
						frame.setVisible(false);
						panelHome.removeAll();///to remve entire panel
					}
				}
			}

		});
		// /////////////Reset//////////////
		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		resetButton.setBounds(930, 90, 130, 25);
		findPanel.add(resetButton);

		resetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				logger.info("CliCked Reset in Marks Entry");
				List findLCList = new ArrayList();
				frame.setVisible(false);
				panelHome.removeAll();///to remve entire panel
				RemarksEntry marksEntryObject = RemarksEntry.getInstance();
                marksEntryObject.getRemarksEntry(sessionData, "", "", false, "", "", "", "", "", "", "",
	                    "", "", section, user_name, user_role, "", retGrNo, "");
			}
		});
		
		// /////////////Marks Obtained//////////////
		JButton marksObtButton = new JButton("Remarks Obtained Excel");
		marksObtButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		marksObtButton.setBounds(500, 90, 250, 25);
		findPanel.add(marksObtButton);

		marksObtButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int reply = 0;
					boolean validateFields = true;
					String academicSel = "";
					String std = "";
					String div = "";
					String from = "";
					String to = "";
					String firstName = "";
					String lastName = "";
					String fatherName = "";
					String exam = "";
					String subject = "";
					String type = "";

					academicSel = (String) academicYear_combo.getSelectedItem() == null ? "" : (String) academicYear_combo.getSelectedItem();
					std = (String) Std_combo.getSelectedItem() == null ? "" : (String) Std_combo.getSelectedItem();
					div = (String) presentDiv_combo.getSelectedItem() == null ? "" : (String) presentDiv_combo.getSelectedItem();
					exam = (String) exam_combo.getSelectedItem() == null ? "" : (String) exam_combo.getSelectedItem();
					subject = (String) subject_combo.getSelectedItem() == null ? "" : (String) subject_combo.getSelectedItem();
					type = (String) type_combo.getSelectedItem() == null ? "" : (String) type_combo.getSelectedItem();

					if (academicSel.equalsIgnoreCase("Year") || academicSel.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std, Div and Exam to download template.");
					} else if (std.equalsIgnoreCase("Select")) { 
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std, Div and Exam to download template.");
					} else if (div.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std, Div and Exam to download template.");
					} else if (exam.equalsIgnoreCase("Select") || exam.equalsIgnoreCase("")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select Academic Year, Std, Div and Exam to download template");
					} else if (subject.equalsIgnoreCase("All") && type.equalsIgnoreCase("Select")) {
						validateFields = false;
						JOptionPane.showMessageDialog(null, "Please select type as you are downloading for All subjects");
					}

					if(validateFields){
						reply = JOptionPane.showConfirmDialog(null, "Would You Like to download Marks Obtained "+exam+" ?", "Confirm validate", JOptionPane.YES_NO_OPTION);
						
						if (reply == JOptionPane.YES_OPTION) {
							if (dbValidate.connectDatabase(sessionData)) {
								JFrame f = new JFrame("Marks Obtained downlaod in progress. Don't Close");
								f.setBounds(screenWidth/2 - 150, screenHeight/2, 90, 25);
							    f.setSize(400, 0);
							    f.setResizable(false);
							    f.setVisible(true);
							    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							    
						    	dbValidate.obtainedRemarksForAllExcel(sessionData, std, div,academicSel,section,"print",exam,subject,type, retGrNo);
								f.setVisible(false);
							}
						}
					}
				} catch (Exception e1) {
					logger.info("Exception backupButton : "+e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
			}
		});
				
		// /////////////Upload Marks Template//////////////
		JButton uploadButton = new JButton("Upload");
		uploadButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		uploadButton.setBounds(780, 90, 130, 25);
		findPanel.add(uploadButton);

		uploadButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					boolean validate = true;
					String stdSel = Std_combo.getSelectedItem().toString();
					String divSel = presentDiv_combo.getSelectedItem().toString();
					String academicSel = academicYear_combo.getSelectedItem().toString();
					String examSel = exam_combo.getSelectedItem().toString();
					String subject = (String) subject_combo.getSelectedItem() == null ? "" : (String) subject_combo.getSelectedItem();
					String type = (String) type_combo.getSelectedItem() == null ? "" : (String) type_combo.getSelectedItem();
					
					if(academicSel.equalsIgnoreCase("Select") || academicSel.equalsIgnoreCase("")){
						commonObj.showMessageDialog("Please select Academic Year, Std, Div and Exam to upload excel");
						validate = false;
					}
					else if(stdSel.equalsIgnoreCase("Select") || stdSel.equalsIgnoreCase("")){
						commonObj.showMessageDialog("Please select Academic Year, Std, Div and Exam to upload excel");
						validate = false;
					}
					else if(divSel.equalsIgnoreCase("Select") || divSel.equalsIgnoreCase("")){
						commonObj.showMessageDialog("Please select Academic Year, Std, Div and Exam to upload excel");
						validate = false;
					}
					else if(examSel.equalsIgnoreCase("Select") || examSel.equalsIgnoreCase("")){
						commonObj.showMessageDialog("Please select Academic Year, Std, Div and Exam to upload excel");
						validate = false;
					} else if (type.equalsIgnoreCase("All") && subject.equalsIgnoreCase("All")) {
						validate = false;
						JOptionPane.showMessageDialog(null, "Please select either Subject or Type as All. Don't select both All.");
					} 
					
					if(validate){
						String default_path = commonObj.getDriveName() + bundle.getString("TEMPLATE_PATH_"+sessionData.getDBName());
						JFileChooser fileChooser = new JFileChooser(default_path);
						int returnValue = fileChooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
							
							int reply = 0;
							reply = JOptionPane.showConfirmDialog(null, "Would You Like to upload "+absolutePath+"?", "Confirm", JOptionPane.YES_NO_OPTION);
							
							if (reply == JOptionPane.YES_OPTION) {
								try {
								    String messageDisplay = "";
//								    if(subject.equalsIgnoreCase("All") && absolutePath.contains("OBTAINED MARKS")) {
								    	messageDisplay = commonObj.uploadAllRemarksTemplate(sessionData, absolutePath, stdSel, divSel, 
								    			academicSel, examSel, type);
//								    }
//								    else {
//								    	messageDisplay = commonObj.uploadMarksTemplate(sessionData, absolutePath, stdSel, divSel, academicSel, examSel);
//								    }
									commonObj.showMessageDialog(messageDisplay);
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(null, "Error : "+ e1);
								}
							} else if (reply == JOptionPane.NO_OPTION) {
								JOptionPane.showMessageDialog(null, "You have cancelled to upload.");
							}
						}
					}
				} catch (Exception e1) {
					logger.info("Exception backupButton : "+e1);
				} finally {
					dbValidate.closeDatabase(sessionData);
				}
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
				Dimension size = new Dimension(screenWidth - 158,
						((screenHeight - 330) + scrollHeight));// change height
																// to change
																// scrolling
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

		if (studentRemarkMap.size() > 0) {
			String fontType = "English";
			final JRadioButton all_radio = new JRadioButton();
			final JComboBox[] remark_combo = new JComboBox[studentRemarkMap.size()];
			String remark_list = sessionData.getConfigMap().get("REMARK_LIST"+"_"+stdClass+"_"+subjectClass);
			if(remark_list == null || remark_list.equalsIgnoreCase("null")) {
				remark_list = "";
				JOptionPane.showMessageDialog(null,"Remarks not found for subject "+subjectClass);
			}
			
			String[] remarkList = ("Select|"+remark_list).split("\\|");
			//Validate Marathi font
			for(int i=0; i < remarkList.length; i++) {
				if(!commonObj.validateMarathi(remarkList[i])) {
					fontType = "Marathi";
				}
			}
			
			all_radio.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			all_radio.setBounds(40, 13, 20, 20);
			all_radio.setSelected(true);
			all_radio.setEnabled(false);
			dataPanel.add(all_radio);

			JLabel std_label = new JLabel("Std: " + stdClass);
			std_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			std_label.setBounds(70, 00, 120, 50);
			dataPanel.add(std_label);

			JLabel div_label = new JLabel("Div: " + divClass);
			div_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			div_label.setBounds(150, 00, 120, 50);
			dataPanel.add(div_label);
			
			JLabel sem_label = new JLabel(examClass);
			sem_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sem_label.setBounds(240, 00, 120, 50);
			dataPanel.add(sem_label);

			JLabel line_label = new JLabel(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			line_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			line_label.setBounds(30, 13, 1100, 50);
			dataPanel.add(line_label);

			JLabel roll_label = new JLabel("Roll No.");
			roll_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			roll_label.setBounds(40, 30, 120, 50);
			dataPanel.add(roll_label);

			JLabel pipe_label7 = new JLabel("|");
			pipe_label7.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label7.setBounds(110, 30, 120, 50);
			dataPanel.add(pipe_label7);

			JLabel name_label = new JLabel("Name");
			name_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			name_label.setBounds(130, 30, 120, 50);
			dataPanel.add(name_label);

			JLabel pipe_label1 = new JLabel("|");
			pipe_label1.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			pipe_label1.setBounds(500, 30, 120, 50);
			dataPanel.add(pipe_label1);

			String subjectHeader = "Subject : " + subjectClass + " ("+typeClassDisp+")";
			if((stdClass.equalsIgnoreCase("X") || stdClass.equalsIgnoreCase("XII")) && examClass.equalsIgnoreCase("Final")){
				subjectHeader = "% in board exam";
			}
			JLabel sem1Fut_label = new JLabel(subjectHeader);
			sem1Fut_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sem1Fut_label.setBounds(530, 30, 450, 50);
			dataPanel.add(sem1Fut_label);
			
			JLabel line1_label = new JLabel(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			line1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			line1_label.setBounds(30, 43, 1100, 50);
			dataPanel.add(line1_label);

			// ////////data//////////////////////////////////////////////////////
			int listSize = 0;
			listSize = studentRemarkMap.size();

			int j = 30;
			int l = 30;
			int rowNumber = 0;
			final JLabel[] rowNo_label = new JLabel[listSize];
			final JLabel[] rollNo_labels = new JLabel[listSize];
			final JLabel[] studentName_label = new JLabel[listSize];
			final JLabel[] grNo_label = new JLabel[listSize];
			final JTextField[] subMarks_text = new JTextField[listSize];
			final JLabel[] maxMarks_label = new JLabel[listSize];
			JLabel[] line_labels = new JLabel[listSize];
			JLabel[] pipe_labels1 = new JLabel[listSize];
			JLabel[] pipe_labels2 = new JLabel[listSize];
			
			int i = 0;
			Set set1 = studentRemarkMap.entrySet();
			Iterator m = set1.iterator();
			while(m.hasNext()) {
				Map.Entry me = (Map.Entry)m.next();
				j = j + 30;
				l = j + 50;
				rowNumber = rowNumber + 1;
				String grNo = me.getKey().toString();
				String rollNo = studentRemarkMap.get(grNo).get("rollNo");
				String studentName = studentRemarkMap.get(grNo).get("name");
				String subremarks = studentRemarkMap.get(grNo).get(subjectClass+"_REM"+examType).trim();

				rowNo_label[i] = new JLabel(rowNumber+"");
				
				grNo_label[i] = new JLabel(grNo);
				grNo_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));

				rollNo_labels[i] = new JLabel(rollNo);
				rollNo_labels[i].setToolTipText(grNo);
				rollNo_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				rollNo_labels[i].setBounds(50, j, 120, 50);
				dataPanel.add(rollNo_labels[i]);

				pipe_labels1[i] = new JLabel("|");
				pipe_labels1[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels1[i].setBounds(110, j, 120, 50);
				dataPanel.add(pipe_labels1[i]);

				studentName_label[i] = new JLabel(studentName);
				studentName_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				studentName_label[i].setToolTipText(studentName);
				if(studentLCMap.get(grNo) != null){
					studentName_label[i].setForeground(Color.RED);
				}
				studentName_label[i].setBounds(130, j + 12, 370, 20);
				dataPanel.add(studentName_label[i]);

				pipe_labels2[i] = new JLabel("|");
				pipe_labels2[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_labels2[i].setBounds(500, j, 120, 50);
				dataPanel.add(pipe_labels2[i]);

				if(!typeClassDisp.equalsIgnoreCase("Remark")) {
					if(subremarks.contains(".") && (Integer.parseInt(subremarks.substring(subremarks.indexOf(".")+1))) == 0)
					{
						subremarks = subremarks.substring(0, subremarks.indexOf("."));
					}
					subMarks_text[i] = new JTextField(subremarks);
					subMarks_text[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					subMarks_text[i].setBounds(550, j + 12, 40, 20);
					subMarks_text[i].setEditable(setEditable);
					subMarks_text[i].setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
					dataPanel.add(subMarks_text[i]);
					
					maxMarks_label[i] = new JLabel(" / " + maxMarksFromMap);
					maxMarks_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					maxMarks_label[i].setToolTipText(studentName);
					maxMarks_label[i].setBounds(600, j + 12, 60, 20);
					if(!stdClass.equalsIgnoreCase("X") && !stdClass.equalsIgnoreCase("XII") && !examClass.equalsIgnoreCase("Final")){
						dataPanel.add(maxMarks_label[i]);
					}
				}
				else {
					remark_combo[i] = new JComboBox(remarkList);
					remark_combo[i].setEnabled(setEditable);
					remark_combo[i].setSelectedItem(subremarks);
					remark_combo[i].setToolTipText(subremarks);
					if(fontType.equalsIgnoreCase("Marathi")) {
						remark_combo[i].setFont(new Font("FreeSans", Font.BOLD, 14));//Shivaji02
						remark_combo[i].setBounds(550, j + 12, 500, 22);
					}
					else {
						remark_combo[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
						remark_combo[i].setBounds(550, j + 12, 500, 20);
					}
					dataPanel.add(remark_combo[i]);
				}
				
				line_labels[i] = new JLabel(
						"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				line_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				line_labels[i].setBounds(30, j + 10, 1100, 50);
				dataPanel.add(line_labels[i]);

				final int k = i;
				if(!typeClassDisp.equalsIgnoreCase("Remark")) {
					subMarks_text[i].addFocusListener(new FocusListener() {

						String studMarksDetail = "";

						@Override
						public void focusGained(FocusEvent arg0) {

							subMarks_text[k].selectAll();
						}

						@Override
						public void focusLost(FocusEvent arg0) {
						}
					});
					
					
					subMarks_text[i].addKeyListener(new KeyAdapter(){
			            public void keyReleased(final KeyEvent e){
			            	new Thread() {
			    	            public void run() {
					            	try{
							            /*int keyCode=e.getKeyChar();
							            int m = Integer.parseInt(rowNo_label[k].getText());
					            		double marksEntered = 0;
					            		String marksTyped = subMarks_text[k].getText();
					            		
					            		if(!marksTyped.equalsIgnoreCase("") && commonObj.validateNumber(marksTyped)){
					            			marksEntered = Double.parseDouble(marksTyped);
					            		} 
					            			
						            	if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB){ //for enter key maxSubMarks
											
						            		if(!marksTyped.equalsIgnoreCase("AB") && !marksTyped.equalsIgnoreCase("MG")){
						            			if(!std.equalsIgnoreCase("X") && !std.equalsIgnoreCase("XII") && !examClass.equalsIgnoreCase("Final")){
								            		if(marksEntered > (Double.parseDouble(maxMarksFromMap))){
								            			JOptionPane.showMessageDialog(null,"Please enter marks less than Max marks "+maxMarksFromMap);
								            			updateFlag = false;
								            		} else{
								            			updateFlag = true;
								            			if((m) <= studMarkList.size()){
								            				subMarks_text[m].requestFocus();
								            			}
								            		}
						            			} else{
							            			updateFlag = true;
							            			if((m) <= studMarkList.size()){
							            				subMarks_text[m].requestFocus();
							            			}
							            		}
						            		}
						            		else if(marksTyped.equalsIgnoreCase("AB") || marksTyped.equalsIgnoreCase("MG")){
					            				subMarks_text[m].requestFocus();
					            			}
						              	} // end of enter key
						            	else if(!marksTyped.equalsIgnoreCase("AB") && !marksTyped.equalsIgnoreCase("MG")){
						            		if(!std.equalsIgnoreCase("X") && !std.equalsIgnoreCase("XII") && !examClass.equalsIgnoreCase("Final")){
								            	if(marksEntered > Double.parseDouble(maxMarksFromMap)){
		//					            			subMarks_text[m-1].requestFocus();
							            			JOptionPane.showMessageDialog(null,"Please enter marks less than Max marks "+maxMarksFromMap);
							            			updateFlag = false;
							            		} else{
							            			updateFlag = true;
							            		}
						            		}
						            		else if (Double.parseDouble(marksTyped) > 100) {
												JOptionPane.showMessageDialog(null,"% obtained cannot be greater than 100 for all students");
												updateFlag = false;
											}
						            		else{
						            			updateFlag = true;
						            		}
						            	}
						            	
						            	String studMarksDetail = "";
										studMarksDetail = grNo_label[k].getText() + "|" + rollNo_labels[k].getText() + "||"
												+ studentName_label[k].getText() + "|||";
				
										for(int n = 0; m <= studMarkList.size(); n++){
											if(studMarkList.get(n).contains(studMarksDetail)){
												studMarkList.remove(studMarkList.get(n));
												break;
											}
										}
										
										if(marksTyped.equalsIgnoreCase("")) 
										{
											marksTyped = " ";
										}
										else if(!marksTyped.equalsIgnoreCase("AB") && !marksTyped.equalsIgnoreCase("MG") && commonObj.validateNumber(marksTyped)){
											marksTyped = (double) marksEntered + "";
										}
						            	studMarksDetail = grNo_label[k].getText() + "|" + rollNo_labels[k].getText() + "||"
												+ studentName_label[k].getText() + "|||" + marksTyped;
						            	studMarkList.add(studMarksDetail);*/
					            	}
						            catch (RuntimeException e1) {
						            	commonObj.logException(e1);
						            }
			    	            }
			  	          }.start();
			            }
			       });
				}
				else {
					remark_combo[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							studentRemarkMap.get(grNo_label[k].getText()).put(subjectClass+"_REM"+examType, remark_combo[k].getSelectedItem().toString());
						}
					});
				}
				i++;
			}
			
            JLabel note_label = new JLabel("NOTE : ABSENT = AB & MEDICAL = MG");
            note_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            note_label.setBounds(40, l + 15, 1600, 50);
            dataPanel.add(note_label);
            
            JLabel note2_label = new JLabel("       Student name mark in RED have left the school");
            note2_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
            note2_label.setBounds(70, l + 35, 1600, 50);
            dataPanel.add(note2_label);
            
			JLabel sep_label = new JLabel("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
			sep_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sep_label.setBounds(40, l + 55, 1600, 50);
			dataPanel.add(sep_label);

			JButton SaveButton = new JButton("Save");
			SaveButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			SaveButton.setBounds(mainCentre - 180, l + 90, 150, 25);
			if(setEditable){
				dataPanel.add(SaveButton);
			}

			JButton EditButton = new JButton("Edit");
			EditButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			EditButton.setBounds(580, 12, 90, 25);
			if((!std.equalsIgnoreCase("X") && !std.equalsIgnoreCase("XII") && !examClass.equalsIgnoreCase("Final")) && 
					(maxMarksFromMap.equalsIgnoreCase("0") || maxMarksFromMap.equalsIgnoreCase("") || 
					maxMarksFromMap.equalsIgnoreCase("NA") || maxMarksFromMap.equalsIgnoreCase("null")) && 
					!typeClassDisp.equalsIgnoreCase("Remark")){
				EditButton.setEnabled(false);
			}
			if(!setEditable){
				dataPanel.add(EditButton);
			}

			SaveButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					try {
						if(dbValidate.connectDatabase(sessionData)) {
							LinkedHashMap subjectTitleMap = new LinkedHashMap<>();
							subjectTitleMap.put(subjectClass, subjectClass);
							String gr = grNo_label[0].getText();
							
							int i = 0;
							Set set1 = studentRemarkMap.entrySet();
							Iterator m = set1.iterator();
							while(m.hasNext()) {
								Map.Entry me = (Map.Entry)m.next();
								String grNo = me.getKey().toString();
								String rollNo = studentRemarkMap.get(grNo).get("rollNo");
								String subremarks = studentRemarkMap.get(grNo).get(subjectClass+"_REM"+examType).trim();
								if(!remark_combo[i].getSelectedItem().toString().equalsIgnoreCase(subremarks)) {
									if(remark_combo[i].getSelectedItem().toString().equalsIgnoreCase("Select")) {
										studentRemarkMap.get(grNo).put(subjectClass+"_REM"+examType, "");
									}
									else {
										studentRemarkMap.get(grNo).put(subjectClass+"_REM"+examType, remark_combo[i].getSelectedItem().toString());
									}
								}
								i++;
							}
							dbValidate.updateRemarkResultMap(sessionData1, academicYearClass, stdClass, divClass, examType, subjectTitleMap, studentRemarkMap);
							JOptionPane.showMessageDialog(null, "Remarks updated successfully");
						}
					} catch (Exception e1) {
						commonObj.logException(e1);
					}  finally {
						dbValidate.closeDatabase(sessionData);
					}
				}
			});
			
			EditButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					try {
						boolean updateMarks = false;
						boolean validateMarksList = true;
						if (validateMarksList) {

							frame.setVisible(false);
							panelHome.removeAll();///to remve entire panel
							List subMarksList = new ArrayList();
							RemarksEntry marksEntryObject = RemarksEntry.getInstance();
			                marksEntryObject.getRemarksEntry(sessionData, stdClass, academicYearClass,
									true, divClass, fromClass,
									toClass, lastClass, firstClass, fatherClass,
									examClass, subjectClass, typeClassDisp,
									section, user_name, user_role, "", retGrNo, "");
						}
						logger.info("updateMarks = " + updateMarks);

					} catch (Exception e1) {
						logger.info("Exception e1 = " + e1);
					}
					
				}
			});
			
			JLabel sep1_label = new JLabel(
					"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
			sep1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sep1_label.setBounds(40, l + 95, 1600, 50);
			dataPanel.add(sep1_label);
		} 
		else if(!stdClass.equalsIgnoreCase("") && !stdClass.equalsIgnoreCase(null) && !isCreate.equalsIgnoreCase("")) {
			
			String fontType = "English";
			boolean validateField = false;
			String remark = "", remarkUpdate = "";
			if(isCreate.equalsIgnoreCase("create")) {
				remark = JOptionPane.showInputDialog("Please Enter Remark");
			}
			
			if(retRemarkList == null) {
				retRemarkList = "";
			}
			String[] remNewList = retRemarkList.split("\\|");
			remList = retRemarkList.split("\\|");
			
			if(remark != null && remark.length()<=100) {
//				String[] remAdded = remark.split("\\|");
				for(int i = 0; i < remList.length; i++) {
					
//					for(int j = 0; j < remAdded.length; j++) {
						if(remList[i].equalsIgnoreCase(remark.trim())) {
							JOptionPane.showMessageDialog(null, "Remark "+remark+" already exists");
						}
						else {
							List<String> list = new ArrayList<String>(Arrays.asList(remNewList));
							if(!list.contains(remark) && !remark.toString().equalsIgnoreCase("")) {
								list.add(remark);
								remNewList = list.toArray(new String[0]);
							}
							validateField = true;
						}
//					}
				}
			}
			else if(remark != null && remark.length()>100){
				JOptionPane.showMessageDialog(null, "Remark should not be greater than 100 characters");
				validateField = false;
			}
			remList = remNewList;
			remarkUpdate = "";
			if(remNewList.length > 1) {
				for(int i = 0; i < remNewList.length; i++) {
					if(!remNewList[i].toString().trim().equalsIgnoreCase(""))
					{
						remarkUpdate = remarkUpdate +"|"+remNewList[i];
					}
				}
				if(remarkUpdate.startsWith("|")) {
					remarkUpdate = remarkUpdate.substring(1);
				}
			}
			
			Arrays.sort(remList);
			if(!stdClass.equalsIgnoreCase("All") && validateField && !remarkUpdate.equalsIgnoreCase("")) {
				boolean updateFlag = dbValidate.updateConfigField(sessionData, false, "REMARK_LIST_"+stdClass+"_"+subjectClass, commonObj.replaceCommaApostrophy(remarkUpdate.trim()));
				if(updateFlag) {
					JOptionPane.showMessageDialog(null, "Remark "+remark+" "+isCreate+" successfully");
				}
				else {
					JOptionPane.showMessageDialog(null, "Remark "+remark+" "+isCreate+" Failed");
				}
			}
			
			final JLabel[] rowNo_label = new JLabel[remList.length];
			final JLabel[] remark_labels = new JLabel[remList.length];
			JButton[] delete_button = new JButton[remList.length];
			int j = 30;
			
			JLabel subj_label = new JLabel("Remarks for Subject : "+subjectClass+"                Std: "+stdClass);
			subj_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			subj_label.setBounds(50, j-30, 900, 50);
			dataPanel.add(subj_label);
			
			JLabel sep0_labe0 = new JLabel(
					"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
			sep0_labe0.setFont(new Font("Book Antiqua", Font.BOLD, 16));
			sep0_labe0.setBounds(40, j-20, 1600, 50);
			dataPanel.add(sep0_labe0);
			
			int k = 0;
			for(int i = 0; i < remList.length; i++) {
				if(remList[i].toString().equalsIgnoreCase("")) {
					continue;
				}
				rowNo_label[i] = new JLabel(++k+"");
				rowNo_label[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				rowNo_label[i].setBounds(50, j, 120, 50);
				dataPanel.add(rowNo_label[i]);
				
				JLabel pipe_label7 = new JLabel("|");
				pipe_label7.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				pipe_label7.setBounds(80, j, 120, 50);
				dataPanel.add(pipe_label7);
				
				if(!commonObj.validateAlphaNum(remList[i])) {
					fontType = "Marathi";
				}
				remark_labels[i] = new JLabel(remList[i]);
				if(fontType.equalsIgnoreCase("Marathi")) {
					remark_labels[i].setFont(new Font("FreeSans", Font.BOLD, 16));
					remark_labels[i].setBounds(100, j-4, 900, 48);
				}
				else {
					remark_labels[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
					remark_labels[i].setBounds(100, j, 900, 50);
				}
				
				dataPanel.add(remark_labels[i]);
				
				delete_button[i] = new JButton("Delete");
				delete_button[i].setFont(new Font("Book Antiqua", Font.BOLD, 16));
				delete_button[i].setBounds(900, j + 12, 90, 20);
				dataPanel.add(delete_button[i]);
				
				JLabel sep1_label = new JLabel(
						"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				sep1_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
				sep1_label.setBounds(40, j+10, 1600, 50);
				dataPanel.add(sep1_label);
				
				final int m = i;
				delete_button[i].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							int reply = 0;
							boolean validateField = false;
							String remarkName = remark_labels[m].getText();
							reply = JOptionPane.showConfirmDialog(null, "Deleting remark "+remarkName+" \n "
	                				+ "Would You Like to delete?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
	                		
	                		if (reply == JOptionPane.YES_OPTION) {
	                			String[] remRemoved = remarkName.split("\\|");
	                			String[] remList = sessionData.getConfigMap().get("REMARK_LIST_"+stdClass+"_"+subjectClass).split("\\|");
	        					
	        					for(int i = 0; i < remRemoved.length; i++) {
	        						
	        						for(int j = 0; j < remList.length; j++) {
	        							if(remRemoved[i].equalsIgnoreCase(remList[j].trim())) {
	        								List<String> list = new ArrayList<String>(Arrays.asList(remList));
	        								list.remove(remRemoved[i]);
	        								remList = list.toArray(new String[0]);
	            						}
	        						}
	        					}
	        					String remark = "";
        						for(int i = 0; i < remList.length; i++) {
        							if(!remList[i].equalsIgnoreCase("")) {
        								remark = remark +"|"+remList[i];
        							}
            					}
	        					remark.replace("||", "|");
	        					if(remark.startsWith("|")) {
	        						remark = remark.substring(1);
	        					}
	        					validateField = true;
	        					
	        					if(validateField) {
	        						Arrays.sort(remList);
	        						if(!stdClass.equalsIgnoreCase("All") && validateField) {
	        							dbValidate.updateConfigField(sessionData, false, "REMARK_LIST_"+stdClass+"_"+subjectClass, remark.trim());
	        							
	        						}
        							frame.setVisible(false);
        							panelHome.removeAll();///to remve entire panel
        							
        							RemarksEntry remarksEntryObject = RemarksEntry.getInstance();
        							remarksEntryObject.getRemarksEntry(sessionData, stdClass, academicYearClass, false, divClass, "", "", "", "", 
        			                		"", examClass, subjectClass, type, section, user_name, user_role, "", retGrNo, "delete");
	                    		}
	                		}
						} catch (Exception e1) {
						}
					}
				});
				
				j = j +30;
			}
		}
		else if(!stdClass.equalsIgnoreCase("") && !stdClass.equalsIgnoreCase(null)){
			commonObj.showMessageDialog("No data found.");
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
	
		frame.setVisible(true);
	}
}
