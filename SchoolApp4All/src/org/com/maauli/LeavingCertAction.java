package org.com.maauli;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

import com.lowagie.text.DocumentException;

// import com.lowagie.text.pdf.draw.LineSeparator;

public class LeavingCertAction {

	DBValidate findStudentDB = new DBValidate();

	Common commonLc = new Common();

	static String leavingDate = "";

	static String issueDate = "";

	static String reason = "";

	static String remark = "";
	
	static String remark2 = "";
	
	static String progress = "";

	static String conduct = "";

	static String fileName = "";

	static boolean fileOpenFlag = false;

	List<String> selectedLcList = new ArrayList();

	static String fileAddress = "";

	static String path = "";

	static String std = "";

	static String div = "";

	static String section = "";

	static String secName = "";

	static String user_name = "";

	static String user_role = "";

	ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");

	static Logger logger = Logger.getLogger(LeavingCertAction.class.getName());

	static List<String> recLCList = new ArrayList();

	static CreateLcPDF createLcPdf = new CreateLcPDF();
	static CreateLcPDFNew createLcPdfNew = new CreateLcPDFNew();

	public void LeavingCertAct(SessionData sessionData, String view_save, String conductTxt, String leavingDt, String issueDt, String reasonT,
			String remarkT, List<String> passGrList, String sec, String retLcType, String retUserName, String retUserRole, String progressT, 
			String remark2T, String academic, String feeStatus, String retStd, String retDiv, String medium) throws DocumentException {

		logger.info("Inside LeavingCertAction class.............");
		try {
			// int lcCountYear = 0;
			// int dupLcCountYear = 0;
			// int triLcCountYear = 0;
			user_name = retUserName;
			user_role = retUserRole;
			section = sec;
			std = bundle.getString(section.toUpperCase() + "_STD");
			div = bundle.getString(section.toUpperCase() + "_DIV");
			secName = bundle.getString(section.toUpperCase() + "_SEC");
			leavingDate = leavingDt;
			issueDate = issueDt;
			reason = reasonT;
			remark = remarkT;
			remark2 = remark2T;
			conduct = conductTxt;
			progress = progressT;

			if (view_save.equalsIgnoreCase("SAVE")) {
				try {
					if (findStudentDB.connectDatabase(sessionData)) {
						List<String> updateLcList = findStudentDB.updateLC(sessionData, conductTxt, leavingDate, issueDate, reason,
								remark, passGrList, section, progress, remark2T,academic,retLcType, feeStatus, retStd, retDiv, medium);
						/*if(sessionData.getSchoolName().equalsIgnoreCase("PR")){
							createLcPdf.LeavingCertificate(sessionData, view_save, conductTxt, leavingDate, issueDate, reason, remark,
									updateLcList, section, retLcType, user_name, user_role, progress, remark2,academic, feeStatus);
						}
						else{*/
							createLcPdfNew.LeavingCertificate(sessionData, view_save, conductTxt, leavingDate, issueDate, reason, remark,
									updateLcList, section, retLcType, user_name, user_role, progress, remark2,academic, feeStatus, retStd, retDiv, medium);
//						}
					}
				} catch (Exception e) {
					logger.info("Update LC Exception == " + e);
				} finally {
					findStudentDB.closeDatabase(sessionData);
				}
			} else {
				/*if(sessionData.getSchoolName().equalsIgnoreCase("PR")){
					createLcPdf.LeavingCertificate(sessionData, view_save, conductTxt, leavingDate, issueDate, reason, remark,
							passGrList, section, retLcType, user_name, user_role, progress, remark2,academic, feeStatus);
				}
				else{*/
					createLcPdfNew.LeavingCertificate(sessionData, view_save, conductTxt, leavingDate, issueDate, reason, remark, passGrList, 
					section, retLcType, user_name, user_role, progress, remark2,academic, feeStatus, retStd, retDiv, medium);
//				}
			}
		} catch (Exception e) {
			logger.info("Outer try Exception == " + e);
		}

	}
}
