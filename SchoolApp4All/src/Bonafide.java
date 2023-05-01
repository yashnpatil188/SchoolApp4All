import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class Bonafide {
	
	public static void main(String[] args) {
		try {
			File file = new File("D:\\yash\\Result.pdf");
			FileOutputStream fileout = new FileOutputStream(file);
			Document document = new Document();
			PdfWriter.getInstance(document, fileout);
//			document.addAuthor("MAAULI EDUCATION SOCIETY");
			document.addTitle("Result");
			document.open();
			
			Chunk chunkHeader = new Chunk("MAAULI EDUCATION SOCIETY");
			Font font = new Font(Font.TIMES_ROMAN);
			font.setStyle(Font.BOLD);
			font.setSize(24);
			font.setColor(Color.RED);
			chunkHeader.setFont(font);
			Paragraph paragraph = new Paragraph();
			paragraph.add(chunkHeader);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);
			
			Chunk chunkSchoolName = new Chunk("ENGLISH MEDIUM HIGH SCHOOL, DUNGE");
			Font fontSchoolName = new Font(Font.TIMES_ROMAN);
			fontSchoolName.setStyle(Font.BOLD);
			fontSchoolName.setSize(18);
			fontSchoolName.setColor(Color.BLACK);
			chunkSchoolName.setFont(fontSchoolName);
			Paragraph paraSchoolName = new Paragraph();
			paraSchoolName.setSpacingBefore(20);
			paraSchoolName.add(chunkSchoolName);
			paraSchoolName.setAlignment(Element.ALIGN_CENTER);
			document.add(paraSchoolName);
			
			
			Chunk chunkAddress = new Chunk("Haridhara Complex, Anjurphata, Bhiwandi");
			Font fontAddress = new Font(Font.TIMES_ROMAN);
			//fontAddress.setStyle(Font.BOLD);
			fontAddress.setSize(12);
			fontAddress.setColor(Color.BLACK);
			chunkAddress.setFont(fontAddress);
			Paragraph paragraphAddress = new Paragraph();
			paragraphAddress.add(chunkAddress);
			paragraphAddress.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraphAddress);
				
			Chunk chunkDate = new Chunk("Date :-                           ");
			Font fontDate = new Font(Font.TIMES_ROMAN);
			fontDate.setStyle(Font.BOLD);
			fontDate.setSize(12);
			fontDate.setColor(Color.BLACK);
			chunkDate.setFont(fontDate);
			Paragraph paragraphDate = new Paragraph();
			paragraphDate.setSpacingBefore(15);
			paragraphDate.add(chunkDate);
			paragraphDate.setAlignment(Element.ALIGN_RIGHT);
			document.add(paragraphDate);
			
			Chunk chunkSubject = new Chunk("Bonafide Certificate");
			Font fontSubject = new Font(Font.TIMES_ROMAN);
			fontSubject.setStyle(Font.BOLD);
			fontSubject.setSize(18);
			fontSubject.setColor(Color.BLACK);
			chunkSubject.setFont(fontSubject);
			Paragraph paragraphSubject = new Paragraph();
			paragraphSubject.setSpacingBefore(15);
			paragraphSubject.add(chunkSubject);
			paragraphSubject.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraphSubject);
			
			Chunk chunkMatter = new Chunk("          This is to certified that Mst./Miss ________________________, studying in Std. ____ Div._____ in");
			Font fontMatter = new Font(Font.TIMES_ROMAN);
			fontMatter.setStyle(Font.NORMAL);
			fontMatter.setSize(12);
			fontMatter.setColor(Color.BLACK);
			chunkMatter.setFont(fontMatter);
			Paragraph paraMatter = new Paragraph();
			paraMatter.setSpacingBefore(43);
			paraMatter.add(chunkMatter);
			paraMatter.setAlignment(Element.ALIGN_LEFT);
			document.add(paraMatter);
			
			Chunk chunkMatter1 = new Chunk("the academic year _______. As per our school General Register his/her Date of Birth is recorded as _______ ");
			Font fontMatter1 = new Font(Font.TIMES_ROMAN);
			fontMatter1.setStyle(Font.NORMAL);
			fontMatter1.setSize(12);
			fontMatter1.setColor(Color.BLACK);
			chunkMatter1.setFont(fontMatter1);
			Paragraph paraMatter1 = new Paragraph();
			paraMatter1.setSpacingBefore(5);
			paraMatter1.add(chunkMatter1);
			paraMatter1.setAlignment(Element.ALIGN_LEFT);
			document.add(paraMatter1);
			
			Chunk chunkMatter11 = new Chunk("(In words). ");
			Font fontMatter11 = new Font(Font.TIMES_ROMAN);
			fontMatter11.setStyle(Font.NORMAL);
			fontMatter11.setSize(12);
			fontMatter11.setColor(Color.BLACK);
			chunkMatter11.setFont(fontMatter11);
			Paragraph paraMatter11 = new Paragraph();
			paraMatter11.setSpacingBefore(5);
			paraMatter11.add(chunkMatter11);
			paraMatter11.setAlignment(Element.ALIGN_LEFT);
			document.add(paraMatter11);
			
			Chunk chunkMatter2 = new Chunk("          To the best of my knowledge he/she belongs to the good moral character.");
			Font fontMatter2 = new Font(Font.TIMES_ROMAN);
			fontMatter2.setStyle(Font.NORMAL);
			fontMatter2.setSize(12);
			fontMatter2.setColor(Color.BLACK);
			chunkMatter2.setFont(fontMatter2);
			Paragraph paraMatter2 = new Paragraph();
			paraMatter2.setSpacingBefore(10);
			paraMatter2.add(chunkMatter2);
			paraMatter2.setAlignment(Element.ALIGN_LEFT);
			document.add(paraMatter2);
			
			Chunk chunkRC = new Chunk("Religion ________ Cast_______");
			Font fontRC = new Font(Font.TIMES_ROMAN);
			fontRC.setStyle(Font.NORMAL);
			fontRC.setSize(12);
			fontRC.setColor(Color.BLACK);
			chunkRC.setFont(fontRC);
			Paragraph paraRC = new Paragraph();			
			paraRC.setSpacingBefore(15);
			paraRC.add(chunkRC);
			paraRC.setAlignment(Element.ALIGN_LEFT);
			document.add(paraRC);
			
			Chunk chunkBirthplace = new Chunk("Place of Birth____________");
			Font fontBirthPlace = new Font(Font.TIMES_ROMAN);
			fontBirthPlace.setStyle(Font.NORMAL);
			fontBirthPlace.setSize(12);
			fontBirthPlace.setColor(Color.BLACK);
			chunkBirthplace.setFont(fontBirthPlace);
			Paragraph paraBirthPlace = new Paragraph();
			paraBirthPlace.setSpacingBefore(10);
			paraBirthPlace.add(chunkBirthplace);
			paraBirthPlace.setAlignment(Element.ALIGN_LEFT);
			document.add(paraBirthPlace);
			
			Chunk chunkGrNo = new Chunk("G. R. Number________");
			Font fontGrNo = new Font(Font.TIMES_ROMAN);
			fontGrNo.setStyle(Font.NORMAL);
			fontGrNo.setSize(12);
			fontGrNo.setColor(Color.BLACK);
			chunkGrNo.setFont(fontGrNo);
			Paragraph paraGrNo = new Paragraph();
			paraGrNo.setSpacingBefore(10);
			paraGrNo.add(chunkGrNo);
			paraGrNo.setAlignment(Element.ALIGN_LEFT);
			document.add(paraGrNo);
			
			Chunk chunkName = new Chunk("                  Principal                        ");
			Font fontName = new Font(Font.TIMES_ROMAN);
			fontName.setStyle(Font.NORMAL);
			fontName.setSize(12);
			fontName.setColor(Color.BLACK);
			chunkName.setFont(fontName);
			Paragraph paraName = new Paragraph();
			paraName.setSpacingBefore(40);
			paraName.add(chunkName);
			paraName.setAlignment(Element.ALIGN_RIGHT);
			document.add(paraName);
				
					
			document.close();
			
			// open pdf 

				String fileAddress= "D:\\yash\\Result.pdf";    //"D:\\java\\resume.pdf";
					try {
						if ((new File(fileAddress)).exists()) {
							if((System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)) {
								Runtime.getRuntime().exec(new String[]{"/usr/bin/open", fileAddress});
							}
							else {
								Process process = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+fileAddress);
								process.waitFor();
							}
						} else {
							System.out.println("File not found");
						}
						System.out.println("Done");
					} catch (Exception e) {
						System.out.println(":: -----Exception---- ::\n"+e);
					}
		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
