package com.quicklyjava;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ProtectedExcelFile { 

    public static void main(final String[] args) throws Exception {     

        String fname = "D:/school_app/BACKUP/23_Mar_2016/subject_allotment_backup on 23_03_2016_1458687331308.xls";
        FileInputStream fileInput = null;       
        BufferedInputStream bufferInput = null;      
        POIFSFileSystem poiFileSystem = null;    
        FileOutputStream fileOut = null;

        try {           

            fileInput = new FileInputStream(fname);         
            bufferInput = new BufferedInputStream(fileInput);            
            poiFileSystem = new POIFSFileSystem(bufferInput);            

            Biff8EncryptionKey.setCurrentUserPassword("secret");            
            HSSFWorkbook workbook = new HSSFWorkbook(poiFileSystem, true);     
//            Sheet sheet1 = workbook.createSheet("Sheet1");
//            HSSFSheet sheet = workbook.getSheetAt(0);           

//            HSSFRow row = sheet.createRow(0);
//            Cell cell = row.createCell(0);
//
//            cell.setCellValue("THIS WORKS!"); 

            fileOut = new FileOutputStream(fname);
            workbook.writeProtectWorkbook(Biff8EncryptionKey.getCurrentUserPassword(), "");
            workbook.write(fileOut);



        } catch (Exception ex) {

            System.out.println(ex.getMessage());      

        } finally {         

              try {            

                  bufferInput.close();     

              } catch (IOException ex) {

                  System.out.println(ex.getMessage());     

              }    

              try {            

                  fileOut.close();     

              } catch (IOException ex) {

                  System.out.println(ex.getMessage());     

              } 
        }       

    }
}