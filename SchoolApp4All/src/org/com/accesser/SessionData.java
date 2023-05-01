package org.com.accesser;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.ResourceBundle;

public class SessionData {

	public SessionData() {
//		// TODO Auto-generated constructor stub
	}
	
	private String userName 		= "";
	private String userRole 		= "";
	private String userStatus 		= "";
	private String firstName 		= "";
	private String lastName 		= "";
	private String userContact 		= "";
	private String userEmail 		= "";
	private String dbUser	 		= "";
	private String dbPass	 		= "";
	private String dbName	 		= "";
	private String appName	 		= "";
	private String schoolName 		= "";
	private String sectionName 		= "";
	private String sectionFullName 	= "";
	private String appType			= "";
	private String adminContact		= "";
	private String secretQuestion1	= "";
	private String secretQuestion2	= "";
	private String secretQuestion3	= "";
	private String secretAnswer1	= "";
	private String secretAnswer2	= "";
	private String secretAnswer3	= "";
	private String sectionLabel		= "";
	private int screenWidth			= 0;
	private int screenHeight		= 0;
	private Connection connection 	= null;
	private ResourceBundle bundle;
	private Properties properties;
	private LinkedHashMap<String, String> configMap;
	
	public LinkedHashMap<String, String> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(LinkedHashMap<String, String> configMap) {
		this.configMap = configMap;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getDBUser() {
		return dbUser;
	}

	public void setDBUser(String dbUser) {
		this.dbUser = dbUser;
	}
	
	public String getDBPass() {
		return dbPass;
	}

	public void setDBPass(String dbPass) {
		this.dbPass = dbPass;
	}
	
	public String getDBName() {
		return dbName;
	}

	public void setDBName(String dbName) {
		this.dbName = dbName;
	}
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	public String getSectionName() {
		return sectionName;
	}

	public void setSectionFullName(String sectionFullName) {
		this.sectionFullName = sectionFullName;
	}
	
	public String getSectionFullName() {
		return sectionFullName;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	public String getAdminContact() {
		return adminContact;
	}

	public void setAdminContact(String adminContact) {
		this.adminContact = adminContact;
	}
	
	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getSecretQuestion1() {
		return secretQuestion1;
	}

	public void setSecretQuestion1(String secretQuestion1) {
		this.secretQuestion1 = secretQuestion1;
	}

	public String getSecretQuestion2() {
		return secretQuestion2;
	}

	public void setSecretQuestion2(String secretQuestion2) {
		this.secretQuestion2 = secretQuestion2;
	}

	public String getSecretQuestion3() {
		return secretQuestion3;
	}

	public void setSecretQuestion3(String secretQuestion3) {
		this.secretQuestion3 = secretQuestion3;
	}

	public String getSecretAnswer1() {
		return secretAnswer1;
	}

	public void setSecretAnswer1(String secretAnswer1) {
		this.secretAnswer1 = secretAnswer1;
	}

	public String getSecretAnswer2() {
		return secretAnswer2;
	}

	public void setSecretAnswer2(String secretAnswer2) {
		this.secretAnswer2 = secretAnswer2;
	}

	public String getSecretAnswer3() {
		return secretAnswer3;
	}

	public void setSecretAnswer3(String secretAnswer3) {
		this.secretAnswer3 = secretAnswer3;
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public String getSectionLabel() {
		return sectionLabel;
	}

	public void setSectionLabel(String sectionLabel) {
		this.sectionLabel = sectionLabel;
	}
}
