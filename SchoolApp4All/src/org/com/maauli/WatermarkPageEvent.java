package org.com.maauli;

import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class WatermarkPageEvent extends PdfPageEventHelper {

//    Font FONT = new Font(Font.FontFamily.HELVETICA, 100, Font.BOLD, new GrayColor(0.85f));

//    @Override
//    public void onEndPage(PdfWriter writer, Document document) {
//        ColumnText.showTextAligned(writer.getDirectContentUnder(),
//                Element.ALIGN_CENTER, new Phrase("Cancel", FONT),
//                160.5f, 330, writer.getPageNumber() % 2 == 1 ? 45 : -45);
//    }
    
    public WatermarkPageEvent(PdfWriter writer, Document document, String text, float start, float height, int angle, int fontSize){
    	Font FONT = new Font(Font.FontFamily.HELVETICA, fontSize, Font.BOLD, new GrayColor(0.85f));
    	ColumnText.showTextAligned(writer.getDirectContentUnder(),
                Element.ALIGN_CENTER, new Phrase(text, FONT),
                start, height, writer.getPageNumber() % 2 == 1 ? angle : -angle);
		
	}
}
