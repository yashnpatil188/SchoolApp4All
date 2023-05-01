import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class FooterPDFDemo {
 
	//To set header & footer on all pages/////////////
    /*class MyFooter extends PdfPageEventHelper {
        Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);
 
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            Phrase header = new Phrase("this is a header", ffont);
            Phrase footer = new Phrase("this is a footer", ffont);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header,(document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,(document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() - 10, 0);
        }
    }*/
 
    public static final String DEST = "D:/yash/page_footer2.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FooterPDFDemo().createPdf(DEST);
    }
    
    Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);
    
    public void onEndPage(PdfWriter writer, Document document, String headerData, String footerData) {
        PdfContentByte cb = writer.getDirectContent();
        Phrase header = new Phrase(headerData, ffont);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header,(document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, 0);
        Phrase footer = new Phrase(footerData, ffont);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,(document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() + 30, 0);
    }
 
    public void createPdf(String filename) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        //Call to set header & footer on all pages////
        /*MyFooter event = new MyFooter();
        writer.setPageEvent(event);*/
        // step 3
        document.open();
        
        // step 4
        for (int i = 0; i < 3; ) {
            i++;
            document.add(new Paragraph("Test " + i));
            String separator ="----------------------------------------------------------------------------------------------------------------------------------";
            onEndPage(writer, document, "Test " + i, separator);
            document.newPage();
        }
        // step 5
        document.close();
    }
}