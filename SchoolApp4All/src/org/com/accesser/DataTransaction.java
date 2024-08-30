//package org.com.accesser;
//
//import java.util.ResourceBundle;
//
//import javax.sql.DataSource;
//import org.com.security.EncryptDecryptStr;
//import org.apache.commons.dbcp.BasicDataSource;
//
//public class DataTransaction {
//	static SessionData sessionData = new SessionData();
//	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
//	static String user = sessionData1.getConfigMap().get("DBUSER");
//	static String pwd = sessionData1.getConfigMap().get("DBPASSWD");
//	private final static BasicDataSource dataSource;
//
//	static {
//		EncryptDecryptStr encdec = new EncryptDecryptStr();
//		String url = sessionData1.getConfigMap().get("DBURL_" + sessionData.getDBName());
//		String dbUser = sessionData.getDBUser();
//		String dbPass = sessionData.getDBPass();
//		if (dbUser.equalsIgnoreCase(null) || dbUser.equalsIgnoreCase("")) {
//			sessionData.setDBUser(encdec.decryptString(user));
//			sessionData.setDBPass(encdec.decryptString(pwd));
//			dbUser = sessionData.getDBUser();
//			dbPass = sessionData.getDBPass();
//		}
//		dataSource = new BasicDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl(url);
//		dataSource.setUsername(dbUser);
//		dataSource.setPassword(dbPass);
//		dataSource.setMaxActive(100);
//		dataSource.setMaxWait(10000);
//		dataSource.setMaxIdle(10);
//	}
//
//	private DataTransaction() {
//	}
//
//	public static DataSource getDataSource(SessionData sessionData1) {
//		return dataSource;
//	}
//}
