package org.com.maauli;

import org.apache.log4j.Logger;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;
import org.com.security.EncryptDecryptStr;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class BulkUploadSms {

	/**
	 * @param args
	 */
	static Common commonObj = new Common();
	
	static String gr_no_class = "";

	static DBValidate dbValidate = new DBValidate();

	static Logger logger = Logger.getLogger(BulkUploadSms.class.getName());

	static JFrame frame = new JFrame("Select File");

	static String fileName = "";

	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	public static void uploadSms(SessionData sessionData, String filePath, String currentDirectory, String moduleName) {
		try {
			String sms_url = bundle.getString("SMS_URL");
			String sms_user = bundle.getString("SMS_" + sessionData.getAppType() + "_USER");
			String sms_pass = bundle.getString("SMS_" + sessionData.getAppType() + "_PASS");
			String sms_sender = bundle.getString("SMS_" + sessionData.getAppType() + "_SENDER");
			String apiKey = bundle.getString(sessionData.getAppType() + "_APIKEY");
			String sms_pe_id = bundle.getString("SMS_PE_ID");
			String phone = "";
			String name = "";
			String message = "", smsTemplateId = "";
			String type = "TRANS";
			String academic = commonObj.getAcademicYear(commonObj.getCurrentDate());
			int noOfResultCount = 0;
			String sms_maauli_flag = bundle.getString("SMS_MAAULI_FLAG");
			
			if(sessionData.getUserName().equalsIgnoreCase("prp") && sms_maauli_flag.equalsIgnoreCase("true")){
				sms_user = bundle.getString("SMS_USER");
				sms_pass = bundle.getString("SMS_PASS");
				sms_sender = bundle.getString("SMS_SENDER");
				apiKey = bundle.getString("SMS_APIKEY");
			}

			fileName = "errors_in_bulkUpload_" + commonObj.timeInMillis() + ".txt";
			// Class.forName("com.mysql.jdbc.Driver");
			// Connection con = (Connection)
			// DriverManager.getConnection(DB_URL+DB_NAME+"?user="+USER+"&password="+PASS);
			// con.setAutoCommit(false);

			FileInputStream input = new FileInputStream(filePath);
			POIFSFileSystem fs = new POIFSFileSystem(input);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			DataFormatter formatter = new DataFormatter();
			Row row;
			if (dbValidate.connectDatabase(sessionData)) {
				for (int i = 2; i <= sheet.getLastRowNum(); i++) {
					try {
						row = sheet.getRow(i);
						if(row.getCell(0) == null || row.getCell(1) == null || row.getCell(2) == null) {
							break;
						}
						name = row.getCell(0).getStringCellValue().toString().trim().toUpperCase();
						phone = formatter.formatCellValue(row.getCell(1));
						message = formatter.formatCellValue(row.getCell(2));
						smsTemplateId = formatter.formatCellValue(row.getCell(3));
						
						if(phone.length() != 10){
							commonObj.writeToText(currentDirectory, fileName, name + " :: " + phone + " => Mobile No. is not 10 digit.");
							continue;
						}

						String data = "username=" + sms_user;
						data += "&" + "apikey=" + apiKey;
						data += "&" + "smstype=" + type;
						data += "&" + "sendername=" + sms_sender;
						data += "&" + "numbers=" + phone;
						data += "&" + "peid=" + sms_pe_id;
						data += "&" + "templateid=" + smsTemplateId;
						data += "&" + "message=" + URLEncoder.encode(message, "UTF-8");

						URL url = new URL(sms_url + data);
						BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

						String line;
						String sResult1 = "";
						message = message.replace("\n", "newLine");
						try {
							while ((line = br.readLine()) != null) {
								sResult1 += line;
							}

							String response = "";
							String status = "";
							String msgId = "";

							response = sResult1.substring(sResult1.indexOf("responseCode") + 15,
									sResult1.indexOf(",") - 2);
							if (response.contains("Submitted") || response.contains("SUBMITTED")) {
								response = "SUBMITTED";
							}
							status = response;
							msgId = sResult1.substring(sResult1.indexOf("msgid") + 8, sResult1.lastIndexOf("}") - 1);

							dbValidate.insertSmsData(sessionData, "", "", "", academic, phone, message, sms_sender, "",
									type, status, response, msgId, "", "", apiKey, "Bulk SMS", name, "", moduleName, "");
							noOfResultCount++;
						} catch (Exception e1) {
							logger.error("Exception e1 ::" + e1);
						}
						// con.commit();
					} catch (Exception e) {
						logger.error("Exception " + e);
						String errorMessage = e.getMessage();
						if (errorMessage.contains("UnknownHostException") || errorMessage.contains("hspsms")) {
							errorMessage = "SMS server connectivity issue.";
						}
						commonObj.writeToText(currentDirectory, fileName,
								name + " :: " + phone + " => " + errorMessage);
					}
				}
				input.close();
				logger.info("Success import excel to mysql table");
				JOptionPane.showMessageDialog(null, "Bulk SMS sent successfully for " + noOfResultCount + " numbers.");
			}
			// con.close();
			try {
				String fileAddress = currentDirectory + "\\" + fileName;
				if ((new File(fileAddress)).exists()) {
					if((System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
						Runtime.getRuntime().exec(new String[]{"/usr/bin/open", fileAddress});
					}
					else {
						Process process = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+fileAddress);
						process.waitFor();
					}
				} else {
					logger.info("File not found");
				}
				logger.info("Done");
			} catch (Exception e) {
				logger.error(":: -----Exception---- ::\n" + e);
			}
		} catch (Exception e) {
			logger.error("Exception " + e);
			commonObj.writeToText(currentDirectory, fileName, gr_no_class + " => " + e);
			JOptionPane.showMessageDialog(null, "Error in import :: " + e);
		} finally {
			dbValidate.closeDatabase(sessionData);
		}
	}
}