package org.com.maauli;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

import java.io.File;
import java.util.Date;
import java.util.ResourceBundle;
/**
 * 
 * @author Dhinakaran P.
 */
// Create a class extends with TimerTask
public class ScheduledTask extends TimerTask {

	Date now; // to display current time
	static DBValidate dbValidateClass 	= new DBValidate();
	static SessionData sessionDataClass  = new SessionData();
	static String academicYearClass = "", methodNameClass ="";
	static Common cm = new Common();
	static Logger logger = Logger.getLogger(ScheduledTask.class.getName());
	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	
	public ScheduledTask(DBValidate dbValidate, SessionData sessionData, String academicYear, String methodName){
		dbValidateClass = dbValidate;
		sessionDataClass = sessionData;
		academicYearClass = academicYear;
		methodNameClass = methodName;
		
	}
	// Add your task here
	public void run() {
		now = new Date(); // initialize date
		logger.info("Scheduler triggered at :: "+now);
		if(methodNameClass.equalsIgnoreCase("updateUndeliveredSms")){
			updateUndeliveredSms();
		}
		else if(methodNameClass.equalsIgnoreCase("backupDatabase")){
			backupDatabase();
		}
	}
	
	public void updateUndeliveredSms(){
		try {
			dbValidateClass.connectDatabase(sessionDataClass);
			dbValidateClass.getUndeliveredSms(sessionDataClass, academicYearClass);
		} catch (Exception e) {
			cm.logException(e);
		}
	}
	
	public void backupDatabase(){
		try {
			String bckPath = cm.getDriveName() + bundle.getString("BACKUP_PATH_"+sessionDataClass.getDBName())+cm.getCurrentDatein_dd_MMM_yyyy();
			if(!(System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
				if (dbValidateClass.connectDatabase(sessionDataClass)) {
					
					File file = new File(bckPath+".zip");
					if (file.exists()) {
						bckPath = bckPath+"_latest";
					}
				    cm.deleteFolder(bckPath);
				    cm.deleteFile(bckPath+".zip");
				    dbValidateClass.backupToSQLFile(sessionDataClass,bckPath, false);
					cm.CreatePasswordProtectedZip(bckPath,"");
					cm.deleteFolder(bckPath);
				}
			}
		} catch (Exception e) {
			logger.error("=====scheduled database backup error=====");
			cm.logException(e);
		}
	}
}