import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;

public class Poitest {

	public static void main(final String[] args) throws Exception {
		// http://poi.apache.org/encryption.html

		String fname = "d:\\yash\\123.xls";
		FileInputStream input = null;
		BufferedInputStream binput = null;
		POIFSFileSystem poifs = null;
		
		try {
			input = new FileInputStream(fname);
			binput = new BufferedInputStream(input);
			poifs = new POIFSFileSystem(binput);
			
			Biff8EncryptionKey.setCurrentUserPassword("123");
			
			HSSFWorkbook workbook = new HSSFWorkbook(poifs, true);
			workbook.writeProtectWorkbook("456", "yash");
			//
			HSSFSheet sheet = workbook.getSheetAt(0);
			sheet.protectSheet("abc");
			int rows = sheet.getLastRowNum();
			System.out.println("rows:"+rows);
			
			for (int i = 0; i <= rows; i++) {
				HSSFRow row = sheet.getRow(i);
				if (row == null)
					continue;
				int cols = row.getLastCellNum();
				System.out.println("cols:"+cols);

				for (int j = 0; j <= cols; j++) {
					HSSFCell cell = row.getCell((int) j);
					CellStyle cellStyle = workbook.createCellStyle();
					cellStyle.setLocked(true);
					cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
					cell.setCellStyle(cellStyle);
					if (cell == null)
						continue;
					if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						HSSFRichTextString val = cell.getRichStringCellValue();
						System.out.println(val);
					}
					if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						double val = cell.getNumericCellValue();
						System.out.println(val);
					}
				}
			}
			
			FileOutputStream fileOut = null;
			fileOut = new FileOutputStream(fname);
            workbook.writeProtectWorkbook(Biff8EncryptionKey.getCurrentUserPassword(), "");
            
            workbook.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				binput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("done");

	}
}