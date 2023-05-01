
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;


public class DisablePrint1 {

public static void main(String args[]) throws DocumentException{
	
	
	try {
		PdfReader reader;
		PdfStamper stamper;
		reader = new PdfReader("D:\\yash\\LeavingCertificate.pdf");
		stamper = new PdfStamper(reader, new FileOutputStream("D:\\yash\\LeavingCertificateNew.pdf"));
//		stamper.setEncryption("my-owner-password".getBytes(), "my-user-password".getBytes(),
//			    PdfWriter.AllowCopy, PdfWriter.STRENGTH40BITS);
		stamper.setEncryption(null, null,
			    PdfWriter.AllowCopy, PdfWriter.STRENGTH40BITS);
		stamper.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//    PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("my-new-file.pdf"));
	

    

	
}
}
