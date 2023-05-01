package org.com.maauli;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

public class CSVToExcelConverter {

	public CSVToExcelConverter(String filePath, String fileName) {
		try {
			ArrayList arList = null;
			ArrayList al = null;
			String fName = filePath+"/"+fileName+".csv";
			String thisLine;
			int count = 0;
			FileInputStream fis = new FileInputStream(fName);
			DataInputStream myInput = new DataInputStream(fis);
			int i = 0;
			arList = new ArrayList();
			while ((thisLine = myInput.readLine()) != null) {
				al = new ArrayList();
				String strar[] = thisLine.split(",");
				for (int j = 0; j < strar.length; j++) {
					al.add(strar[j]);
				}
				arList.add(al);
				System.out.println();
				i++;
			}

			try {
				XSSFWorkbook hwb = new XSSFWorkbook();
				XSSFSheet sheet = hwb.createSheet("new sheet");
				for (int k = 0; k < arList.size(); k++) {
					ArrayList ardata = (ArrayList) arList.get(k);
					XSSFRow row = sheet.createRow((short) 0 + k);
					for (int p = 0; p < ardata.size(); p++) {
						Cell cell = row.createCell(p);
						String data = ardata.get(p).toString();
						if (data.startsWith("=")) {
							// cell.setCellType(Cell.CELL_TYPE_STRING);
							data = data.replaceAll("\"", "");
							data = data.replaceAll("=", "");
							cell.setCellValue(data);
						} else if (data.startsWith("\"")) {
							data = data.replaceAll("\"", "");
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(data);
						} else {
							data = data.replaceAll("\"", "");
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(data);
						}
						// */
						// cell.setCellValue(ardata.get(p).toString());
					}
					System.out.println();
				}
				FileOutputStream fileOut = new FileOutputStream(filePath+"/"+fileName+".xlsx");
				hwb.write(fileOut);
				fileOut.close();
				System.out.println("Your excel file has been generated");
			} catch (Exception ex) {
				ex.printStackTrace();
			} // main method ends
		} catch (Exception e) {
			System.out.println("Your excel file generation failed.");
		}
	}
}