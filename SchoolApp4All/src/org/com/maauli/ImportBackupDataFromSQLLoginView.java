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
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;

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

public class ImportBackupDataFromSQLLoginView {
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
	static TreeMap schoolmap = new TreeMap();
	static String schoolStr = "";

	public static void main(String[] args) {

		schoolmap.put("PR High School", "jdbc:mysql://127.0.0.1/school?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 school");
		schoolmap.put("PR Primary School", "jdbc:mysql://127.0.0.1/school?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 school");
		schoolmap.put("Subhedar High School", "jdbc:mysql://127.0.0.1/subhedarschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 subhedarschool");
		schoolmap.put("Subhedar Primary School", "jdbc:mysql://127.0.0.1/subhedarschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 subhedarschool");
		schoolmap.put("Subhedar College", "jdbc:mysql://127.0.0.1/subhedarcollege?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 subhedarcollege");
		schoolmap.put("NES High School", "jdbc:mysql://127.0.0.1/nesschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 nesschool");
		schoolmap.put("NES Primary School", "jdbc:mysql://127.0.0.1/nesschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 nesschool");
		schoolmap.put("NES College", "jdbc:mysql://127.0.0.1/nescollege?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 nescollege");
		schoolmap.put("Vivekananda High School", "jdbc:mysql://127.0.0.1/vivekanandaschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 vivekanandaschool");
		schoolmap.put("Vivekananda Primary School", "jdbc:mysql://127.0.0.1/vivekanandaschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 vivekanandaschool");
		schoolmap.put("Vivekananda College", "jdbc:mysql://127.0.0.1/vivekanandacollege?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 vivekanandacollege");
		schoolmap.put("SV Joshi High School", "jdbc:mysql://127.0.0.1/svjoshischool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 svjoshischool");
		schoolmap.put("SV Joshi Primary School", "jdbc:mysql://127.0.0.1/svjoshischool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 svjoshischool");
		schoolmap.put("SV Joshi College", "jdbc:mysql://127.0.0.1/svjoshicollege?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 svjoshicollege");
		schoolmap.put("MH High School", "jdbc:mysql://127.0.0.1/mhschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 mhschool");
		schoolmap.put("MH Primary School", "jdbc:mysql://127.0.0.1/mhschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 mhschool");
		schoolmap.put("MH College", "jdbc:mysql://127.0.0.1/mhcollege?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 mhcollege");
		schoolmap.put("NEW High School", "jdbc:mysql://127.0.0.1/newschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 newschool");
		schoolmap.put("NEW Primary School", "jdbc:mysql://127.0.0.1/newschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 newschool");
		schoolmap.put("NEW College", "jdbc:mysql://127.0.0.1/newcollege?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 newcollege");
		schoolmap.put("English High School Uran", "jdbc:mysql://127.0.0.1/englishuranschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 englishuranschool");
		schoolmap.put("English Primary School Uran", "jdbc:mysql://127.0.0.1/englishpriuranschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 englishpriuranschool");
		schoolmap.put("Chabildas High School", "jdbc:mysql://127.0.0.1/chabildasschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 chabildasschool");
		schoolmap.put("Chabildas Primary School", "jdbc:mysql://127.0.0.1/chabildasprimary?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 chabildasprimary");
		schoolmap.put("Chabildas College", "jdbc:mysql://127.0.0.1/chabildascollege?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 chabildascollege");
		schoolmap.put("English Mahim High School", "jdbc:mysql://127.0.0.1/engmahimschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 engmahimschool");
		schoolmap.put("Jhbhagat High School", "jdbc:mysql://127.0.0.1/jhbhagatschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 jhbhagatschool");
		schoolmap.put("Jhbhagat College", "jdbc:mysql://127.0.0.1/jhbhagatcollege?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 jhbhagatcollege");
		schoolmap.put("NI High School Uran", "jdbc:mysql://127.0.0.1/niuranschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 niuranschool");
		schoolmap.put("NI College Uran", "jdbc:mysql://127.0.0.1/niurancollege?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 niurancollege");
		schoolmap.put("Primary School Uran", "jdbc:mysql://127.0.0.1/priuranschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 priuranschool");
		schoolmap.put("Primary School Bokadvira", "jdbc:mysql://127.0.0.1/pribokadviraschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 pribokadviraschool");
		schoolmap.put("P.E.M. High School", "jdbc:mysql://127.0.0.1/pemhighschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 pemhighschool");
		schoolmap.put("P.E.M. Primary School", "jdbc:mysql://127.0.0.1/pemprischool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 pemprischool");
		schoolmap.put("Vikas High School", "jdbc:mysql://127.0.0.1/vikashighschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 vikashighschool");
		schoolmap.put("Vikas Primary School", "jdbc:mysql://127.0.0.1/vikasprischool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 vikasprischool");
		schoolmap.put("P.D. Taware School Kalher", "jdbc:mysql://127.0.0.1/pdtavreschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 pdtavreschool");
		schoolmap.put("Madhavrao Patil School Kalher", "jdbc:mysql://127.0.0.1/mpbalmandirschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 mpbalmandirschool");
		schoolmap.put("Anant Patil College Kalher", "jdbc:mysql://127.0.0.1/alpkalhercollege?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 alpkalhercollege");
		schoolmap.put("Abhinav High School Kalher", "jdbc:mysql://127.0.0.1/abhinavmandirschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 abhinavmandirschool");
		schoolmap.put("Abhinav Pri School Kalher", "jdbc:mysql://127.0.0.1/abhinavmandirprischool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 abhinavmandirprischool");
		schoolmap.put("Sitaram Patil School Amne", "jdbc:mysql://127.0.0.1/newengamneschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 newengamneschool");
		schoolmap.put("Sanskar Eng School Anjur", "jdbc:mysql://127.0.0.1/sanskaranjurschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 sanskaranjurschool");
		schoolmap.put("R. K. Palvi VidyaMandir Padgha", "jdbc:mysql://127.0.0.1/rkpalvipadghaschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 rkpalvipadghaschool");
		schoolmap.put("Subhedar English High School", "jdbc:mysql://127.0.0.1/subhedarenghighschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 subhedarenghighschool");
		schoolmap.put("Subhedar English Pri School", "jdbc:mysql://127.0.0.1/subhedarengprischool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 subhedarengprischool");
		schoolmap.put("Blossom CBSE School", "jdbc:mysql://127.0.0.1/blossomcbseschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 blossomcbseschool");
		schoolmap.put("SSSangh English High School", "jdbc:mysql://127.0.0.1/sssanghenghighschool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 sssanghenghighschool");
		schoolmap.put("SSSangh English College", "jdbc:mysql://127.0.0.1/sssanghengcollege?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 sssanghengcollege");
		schoolmap.put("SSSangh English Pri School", "jdbc:mysql://127.0.0.1/sssanghengprischool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 sssanghengprischool");
		schoolmap.put("SSSangh English Pre Pri School", "jdbc:mysql://127.0.0.1/sssanghengpreprischool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 sssanghengpreprischool");
		schoolmap.put("SSSangh Hindi College", "jdbc:mysql://127.0.0.1/sssanghhincollege?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 sssanghhincollege");
		schoolmap.put("SSSangh Hindi Pri School", "jdbc:mysql://127.0.0.1/sssanghhinprischool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 sssanghhinprischool");
		schoolmap.put("SSSangh Hindi Pre Pri School", "jdbc:mysql://127.0.0.1/sssanghhinpreprischool?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&serverTimezone=-05:30 sssanghhinpreprischool");
		
		schoolStr = "Abhinav High School Kalher,Abhinav Pri School Kalher,Anant Patil College Kalher,Blossom CBSE School,Chabildas College,Chabildas High School,Chabildas Primary School,English High School Uran,English Mahim High School,"
				+ "English Primary School Uran,Jhbhagat College,Jhbhagat High School,Madhavrao Patil School Kalher,MH College,MH High School,"
				+ "MH Primary School,NES College,NES High School,NES Primary School,NEW College,Sanskar Eng School Anjur,Sitaram Patil School Amne,NEW High School,"
				+ "NEW Primary School,NI College Uran,NI High School Uran,P.D. Taware School Kalher,P.E.M. High School,P.E.M. Primary School,PR High School,PR Primary School,"
				+ "Primary School Bokadvira,Primary School Uran,Subhedar English High School,SSSangh English High School,SSSangh English College,SSSangh English Pri School,SSSangh English Pre Pri School," 
				+ "SSSangh Hindi High School,SSSangh Hindi College,SSSangh Hindi Pri School,SSSangh Hindi Pre Pri School,Subhedar English Pri School,Subhedar College,Subhedar High School,R. K. Palvi VidyaMandir Padgha,"
				+ "Subhedar Primary School,SV Joshi College,SV Joshi High School,SV Joshi Primary School,Vikas High School,Vikas Primary School,"
				+ "Vivekananda College,Vivekananda High School,Vivekananda Primary School";
		
//		schoolStr = "CountryData";
		
		commonObj.createTodayFolder(log_path, false);
    	logger 			= Logger.getLogger(ImportBackupDataFromSQLLoginView.class.getName());
		TreeMap tm = new TreeMap();
		
		screenWidth = commonObj.screeWidth();
        screenHeight = commonObj.screeHeight();
        mainCentre = (screenWidth - 150) / 2;
		
		frame = new JFrame("Backup Data Import");

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
		JLabel headLabel = new JLabel("Welcome to MyApp");
		logger.info("=====Welcome to MyApp====");

		headLabel.setFont(new Font("Book Antiqua", Font.BOLD, 30));
		headLabel.setBounds((screenWidth / 2 - 150), (screenHeight / 2 - 300), 300, 40);
//		panel.add(headLabel);
		
		JLabel school_label = new JLabel("Select School :");
		school_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		school_label.setBounds((screenWidth / 2 - 150), (screenHeight / 2 - 160), 150, 50);
		panel.add(school_label);

		String[] schoolList = schoolStr.split(",");
		final JComboBox school_combo = new JComboBox(schoolList);
		school_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		school_combo.setBounds((screenWidth / 2 + 00), (screenHeight / 2 - 150), 300, 25);
		panel.add(school_combo);
		
		// /////////////Enter Passcode//////////////
		JLabel passCode_label = new JLabel("Enter Passcode :");
		passCode_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		passCode_label.setBounds((screenWidth / 2 - 150), (screenHeight / 2 - 110), 120, 50);
		panel.add(passCode_label);

		final JPasswordField passCode_text = new JPasswordField();
		passCode_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		passCode_text.setBounds((screenWidth / 2 + 00), (screenHeight / 2 - 100), 200, 25);
		panel.add(passCode_text);
				

		JButton runButton = new JButton("Run");
		runButton.setFont(new Font("Book Antiqua", Font.BOLD, 25));
		runButton.setBounds((screenWidth / 2 - 100), (screenHeight / 2 - 50), 200, 25);
        panel.add(runButton);

        runButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	String school = (String) school_combo.getSelectedItem();
            	String passCode = new String(passCode_text.getPassword());
            	String date = commonObj.getCurrentDate();
            	date = date.replace("/", "");
            	if(passCode.equalsIgnoreCase("Smart@"+date)){
            		try {
            			String selectedSchool = schoolmap.get(school).toString();
            			String[] arguments = new String[] {selectedSchool};
    					ReadFileExample.main(arguments);
    					frame.setVisible(false);
    				} catch (Exception ex) {
    					logger.info(ex);
    				}
            	}
            	else{
            		commonObj.showMessageDialog("Please enter correct passcode.");
            	}
            }
        });
	}
}
