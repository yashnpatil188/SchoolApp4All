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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

public class EditSubject extends JFrame {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static JComboBox studentcombo;

	static String currentUser = "";

	static JFrame frame = null;

	static String std = "";

	static String div = "";
	
	static String religionList = "";
	
	static String categoryList = "";

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

	static int topGrNo = 0;

	static int nextGrNo = 0;

	static String nxtGrString = "";

	static String dateToday = "";

	static String academicYear = "";

	static String user_name = "";

	static String user_role = "";

    static String app_header = "";
    
    static String app_header_0 = "";
	
	static SessionData sessionData = new SessionData();

	static DBValidate dbValidate = new DBValidate();

	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static Logger logger = Logger.getLogger(EditSubject.class.getName());

	static Common commonObj = new Common();
	
	static String subjectName = "";
	
	static String subjectTitle = "";
	
	static String marks_grade = "";
	
	static String optionalValue = "";
	
	static String groupName = "";
	
	static String stdClass = "";
	
	static String divClass = "";
	
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

	public EditSubject(SessionData sessionData1, String sec, String retUserName, String retUserRole, 
			String subjectNameRet, String subjectTitleRet, String marks_gradeRet, String optionalValueRet, String groupNameRet,
			String std, String div, String academic) {
		System.gc();
		sessionData = sessionData1;
		user_name = retUserName;
		user_role = retUserRole;
		section = sec;
		subjectName = subjectNameRet;
		subjectTitle = subjectTitleRet;
		marks_grade = marks_gradeRet;
		optionalValue = optionalValueRet;
		groupName = groupNameRet;
		stdClass = std;
		divClass = div;
		dateToday = commonObj.getCurrentDate();
		academicYear = academic;
		logger.info("academicYear :: " + academicYear);
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
		screenWidth = commonObj.screeWidth();
		screenHeight = commonObj.screeHeight();
		mainCentre = (screenWidth - 150) / 2;

		frame.setSize(screenWidth, screenHeight);
		frame.setResizable(false);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		currentUser = commonObj.fileReader();
		logger.info("currentUser==>" + currentUser);

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

		mainPanel.add(topbandPanel, BorderLayout.NORTH);

		// ///////////bottom band/////////////
		JPanel bottombandPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				Image img = new ImageIcon(img_path + img_mainband).getImage();
				Dimension size = new Dimension(screenWidth - 150, screenHeight - 150);
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				g.drawImage(img, 0, 0, null);
			}
		};
		bottombandPanel.setLayout(null);

		JLabel page_label = new JLabel("Edit Subject for Std "+stdClass+" ("+academicYear+")");
		page_label.setFont(new Font("Book Antiqua", Font.BOLD, 20));
		page_label.setBounds(30, 40, 900, 50);
		bottombandPanel.add(page_label);
		
		// /////////////Subject Name//////////////
		JLabel subjectName_label = new JLabel("Subject Name :");
		subjectName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		subjectName_label.setBounds(30, 80, 120, 50);
		bottombandPanel.add(subjectName_label);

		final JTextField subjectName_text = new JTextField(subjectName);
		subjectName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		subjectName_text.setBounds(160, 90, 200, 25);
		bottombandPanel.add(subjectName_text);

		// /////////////Subject Title//////////////
		JLabel subjectTitle_label = new JLabel("Subject Title :");
		subjectTitle_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		subjectTitle_label.setBounds(30, 120, 120, 50);
		bottombandPanel.add(subjectTitle_label);

		boolean enableFlag = false;
        List<String> findSubTitleList = new ArrayList();
        try {
        	if(dbValidate.connectDatabase(sessionData)){
            	findSubTitleList = dbValidate.findSubjectTitleList(sessionData, stdClass, "", "");
            	findSubTitleList.remove(0);
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
        subTitle_combo.setSelectedItem(subjectTitle);
        subTitle_combo.setBounds(160, 130, 220, 25);
        subTitle_combo.setEnabled(enableFlag);
        bottombandPanel.add(subTitle_combo);
        
        final JTextField newSubTitle_text = new JTextField(subjectTitle);
        newSubTitle_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        newSubTitle_text.setBounds(400, 130, 200, 25);
        bottombandPanel.add(newSubTitle_text);
		// //////////////////////////////////
		// /////////////Marks Grade//////////////
		JLabel marksGrade_label = new JLabel("Marks/Grade : ");
		marksGrade_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		marksGrade_label.setBounds(30, 170, 120, 50);
		bottombandPanel.add(marksGrade_label);

		String mark_grade_list[] = {"Marks", "Grade"};
        final JComboBox markGrade_combo = new JComboBox(mark_grade_list);
        markGrade_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        markGrade_combo.setSelectedItem(marks_grade);
        markGrade_combo.setBounds(160, 180, 200, 25);
        bottombandPanel.add(markGrade_combo);
		// //////////////////////////////////
		// /////////////Optional//////////////
		JLabel optional_label = new JLabel("Optional :");
		optional_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		optional_label.setBounds(30, 220, 130, 50);
		bottombandPanel.add(optional_label);

		String optional[] = {"No", "Yes"};
        final JComboBox optional_combo = new JComboBox(optional);
        optional_combo.setFont(new Font("Book Antiqua", Font.BOLD, 16));
        optional_combo.setSelectedItem(optionalValue);
        optional_combo.setBounds(160, 230, 200, 25);
        bottombandPanel.add(optional_combo);
		// //////////////////////////////////
		// /////////////groupName//////////////
		JLabel groupName_label = new JLabel("Group Name :");
		groupName_label.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		groupName_label.setBounds(30, 270, 130, 50);
		bottombandPanel.add(groupName_label);

		final JTextField groupName_text = new JTextField(groupName);
		groupName_text.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		groupName_text.setBounds(160, 280, 200, 25);
		bottombandPanel.add(groupName_text);
		// //////////////////////////////////
		// /////////////Save//////////////
		final JButton submitButton = new JButton("Save");
		submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		submitButton.setBounds(mainCentre - 150, 350, 130, 25);
		bottombandPanel.add(submitButton);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					int reply = 0;
                    boolean updateSubject = false;
                    boolean validateSubList = true;
                    String subName = subjectName_text.getText();
                    subName = subName.replace("_", " ");
                    String subTitle = (String) subTitle_combo.getSelectedItem();
                    subTitle = subTitle.replace("_", " ");
                    String newSubTitle = newSubTitle_text.getText();
                    newSubTitle = newSubTitle.replace("_", " ");
                    String mark_grade = (String) markGrade_combo.getSelectedItem();
                    String option = (String) optional_combo.getSelectedItem();
                    String groupName = groupName_text.getText();
                    groupName = groupName.replace("_", " ");
                    if(groupName.equalsIgnoreCase("Group Name")){
                    	groupName = "";
                    }

                    if (subName.equalsIgnoreCase("") || subName.equalsIgnoreCase("New Subject")) {
                        JOptionPane.showMessageDialog(null, "Please enter New Subject Name");
                        validateSubList = false;
                    } else if (!commonObj.validateAlphaNum(subName)) {
                        JOptionPane.showMessageDialog(null, "Subject name should be alphanumeric");
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
                    } else if (!commonObj.validateAlphaNum(newSubTitle)) {
                        JOptionPane.showMessageDialog(null, "Subject title should be alphanumeric");
                        validateSubList = false;
                    }  else if (!commonObj.validateAlphaNum(groupName) && !groupName.equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "groupName should be alphanumeric");
                        validateSubList = false;
                    } else if (groupName.length() > 20) {
                        JOptionPane.showMessageDialog(null, "groupName cannot be greater than 20 char.");
                        validateSubList = false;
                    }
                    
                    logger.info("validateSubList = " + validateSubList);
                    if (validateSubList) {
                    	reply = JOptionPane.showConfirmDialog(null, "This update will reset subject marks entered in Marks Entry session. \n "
                				+ "Would You Like to update?", "Confirm update", JOptionPane.YES_NO_OPTION);
                		
                		if (reply == JOptionPane.YES_OPTION && dbValidate.connectDatabase(sessionData)) {
                			updateSubject = dbValidate.addSubject(subName, newSubTitle.replace(" ", "_"), mark_grade, option, 
                            		academicYear, stdClass, sessionData, groupName, true, section, subjectName,subjectTitle);
                            if (updateSubject) {
//                            	JOptionPane.showMessageDialog(null, subName+" updated successfully for "+stdClass);
                                frame.setVisible(false);
                                new CreateSubject(sessionData, "", stdClass, section, user_name, user_role, "");
                            }
                		}
                    }
                    logger.info("updateSubject = " + updateSubject);

                } catch (Exception e1) {
                    logger.info("Exception e1 = " + e1);
                }
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

		// /////////////////////////////
		mainPanel.add(bottombandPanel, BorderLayout.SOUTH);
		panel.add(mainPanel, BorderLayout.EAST);

	}
}
