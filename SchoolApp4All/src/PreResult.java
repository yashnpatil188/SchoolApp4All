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

public class PreResult {
	
	public static void main(String[] args) {
		try {
			File file = new File("D:\\yash\\PreResult.pdf");
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
			
			Chunk chunkName = new Chunk("                                  Yogesh Jagannnath Patil");
			Font fontName = new Font(Font.TIMES_ROMAN);
			fontName.setStyle(Font.NORMAL);
			fontName.setSize(12);
			fontName.setColor(Color.BLACK);
			chunkName.setFont(fontName);
			Paragraph paraName = new Paragraph();
			paraName.setSpacingBefore(10);
			paraName.add(chunkName);
			paraName.setAlignment(Element.ALIGN_LEFT);
			document.add(paraName);
			
			Chunk chunkStd = new Chunk("             VIII");
			Font fontStd = new Font(Font.TIMES_ROMAN);
			fontStd.setStyle(Font.NORMAL);
			fontStd.setSize(12);
			fontStd.setColor(Color.BLACK);
			chunkStd.setFont(fontStd);
			Paragraph paraStd = new Paragraph();
			paraStd.setSpacingBefore(36);
			paraStd.add(chunkStd);
			paraStd.setAlignment(Element.ALIGN_LEFT);
			document.add(paraStd);
			
			Chunk chunkDiv = new Chunk("                                   B");
			Font fontDiv = new Font(Font.TIMES_ROMAN);
			fontDiv.setStyle(Font.NORMAL);
			fontDiv.setSize(12);
			fontDiv.setColor(Color.BLACK);
			chunkDiv.setFont(fontDiv);
			Paragraph paraDiv = new Paragraph();			
			paraDiv.setSpacingBefore(-16);
			paraDiv.add(chunkDiv);
			paraDiv.setAlignment(Element.ALIGN_LEFT);
			document.add(paraDiv);
			
			Chunk chunkRNo = new Chunk("                                                      35");
			Font fontRNo = new Font(Font.TIMES_ROMAN);
			fontRNo.setStyle(Font.NORMAL);
			fontRNo.setSize(12);
			fontRNo.setColor(Color.BLACK);
			chunkRNo.setFont(fontRNo);
			Paragraph paraRNo = new Paragraph();			
			paraRNo.setSpacingBefore(-16);
			paraRNo.add(chunkRNo);
			paraRNo.setAlignment(Element.ALIGN_LEFT);
			document.add(paraRNo);
			
			Chunk chunkGrNo = new Chunk("                                                                             12345");
			Font fontGrNo = new Font(Font.TIMES_ROMAN);
			fontGrNo.setStyle(Font.NORMAL);
			fontGrNo.setSize(12);
			fontGrNo.setColor(Color.BLACK);
			chunkGrNo.setFont(fontGrNo);
			Paragraph paraGrNo = new Paragraph();
			paraGrNo.setSpacingBefore(-16);
			paraGrNo.add(chunkGrNo);
			paraGrNo.setAlignment(Element.ALIGN_LEFT);
			document.add(paraGrNo);
			
			Chunk chunkBirthDate = new Chunk("                                                                                       " + "    " +
					"                    18/09/1985");
			Font fontBirthDate = new Font(Font.TIMES_ROMAN);
			fontBirthDate.setStyle(Font.NORMAL);
			fontBirthDate.setSize(12);
			fontBirthDate.setColor(Color.BLACK);
			chunkBirthDate.setFont(fontBirthDate);
			Paragraph paraBirthDate = new Paragraph();
			paraBirthDate.setSpacingBefore(-16);
			paraBirthDate.add(chunkBirthDate);
			paraBirthDate.setAlignment(Element.ALIGN_LEFT);
			document.add(paraBirthDate);
			
			Chunk chunkAttendance = new Chunk("                                                                                       " + "    " +
					"                                                       95/100");
			Font fontAttendance = new Font(Font.TIMES_ROMAN);
			fontAttendance.setStyle(Font.NORMAL);
			fontAttendance.setSize(12);
			fontAttendance.setColor(Color.BLACK);
			chunkAttendance.setFont(fontRNo);
			Paragraph paraTitleAttendance = new Paragraph();	
			paraTitleAttendance.setSpacingBefore(-16);
			paraTitleAttendance.add(chunkAttendance);
			paraTitleAttendance.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleAttendance);
						
			Chunk chunkEnglishFTG = new Chunk("                                                           55 ");
			Font fontEnglishFTG = new Font(Font.TIMES_ROMAN);
			fontEnglishFTG.setStyle(Font.NORMAL);
			fontEnglishFTG.setSize(12);
			fontEnglishFTG.setColor(Color.BLACK);
			chunkEnglishFTG.setFont(fontRNo);
			Paragraph paraTitleEnglishFTG = new Paragraph();	
			paraTitleEnglishFTG.setSpacingBefore(25);
			paraTitleEnglishFTG.add(chunkEnglishFTG);
			paraTitleEnglishFTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleEnglishFTG);
			
			Chunk chunkEnglishSTG = new Chunk("                                                                                        50 ");
			Font fontEnglishSTG = new Font(Font.TIMES_ROMAN);
			fontEnglishSTG.setStyle(Font.NORMAL);
			fontEnglishSTG.setSize(12);
			fontEnglishSTG.setColor(Color.BLACK);
			chunkEnglishSTG.setFont(fontRNo);
			Paragraph paraTitleEnglishSTG = new Paragraph();	
			paraTitleEnglishSTG.setSpacingBefore(-16);
			paraTitleEnglishSTG.add(chunkEnglishSTG);
			paraTitleEnglishSTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleEnglishSTG);
			
			Chunk chunkHindiFTG = new Chunk("                                                           55 ");
			Font fontHindiFTG = new Font(Font.TIMES_ROMAN);
			fontHindiFTG.setStyle(Font.NORMAL);
			fontHindiFTG.setSize(12);
			fontHindiFTG.setColor(Color.BLACK);
			chunkHindiFTG.setFont(fontRNo);
			Paragraph paraTitleHindiFTG = new Paragraph();	
			paraTitleHindiFTG.setSpacingBefore(9);
			paraTitleHindiFTG.add(chunkHindiFTG);
			paraTitleHindiFTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleHindiFTG);
			
			Chunk chunkHindiSTG = new Chunk("                                                                                        50 ");
			Font fontHindiSTG = new Font(Font.TIMES_ROMAN);
			fontHindiSTG.setStyle(Font.NORMAL);
			fontHindiSTG.setSize(12);
			fontHindiSTG.setColor(Color.BLACK);
			chunkHindiSTG.setFont(fontRNo);
			Paragraph paraTitleHindiSTG = new Paragraph();	
			paraTitleHindiSTG.setSpacingBefore(-16);
			paraTitleHindiSTG.add(chunkHindiSTG);
			paraTitleHindiSTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleHindiSTG);
			
			Chunk chunkMarathiFTG = new Chunk("                                                           55 ");
			Font fontMarathiFTG = new Font(Font.TIMES_ROMAN);
			fontMarathiFTG.setStyle(Font.NORMAL);
			fontMarathiFTG.setSize(12);
			fontMarathiFTG.setColor(Color.BLACK);
			chunkMarathiFTG.setFont(fontRNo);
			Paragraph paraTitleMarathiFTG = new Paragraph();	
			paraTitleMarathiFTG.setSpacingBefore(9);
			paraTitleMarathiFTG.add(chunkMarathiFTG);
			paraTitleMarathiFTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleMarathiFTG);
			
			Chunk chunkMarathiSTG = new Chunk("                                                                                        50 ");
			Font fontMarathiSTG = new Font(Font.TIMES_ROMAN);
			fontMarathiSTG.setStyle(Font.NORMAL);
			fontMarathiSTG.setSize(12);
			fontMarathiSTG.setColor(Color.BLACK);
			chunkMarathiSTG.setFont(fontRNo);
			Paragraph paraTitleMarathiSTG = new Paragraph();	
			paraTitleMarathiSTG.setSpacingBefore(-16);
			paraTitleMarathiSTG.add(chunkMarathiSTG);
			paraTitleMarathiSTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleMarathiSTG);
			
			Chunk chunkMathematicsFTG = new Chunk("                                                           55 ");
			Font fontMathematicsFTG = new Font(Font.TIMES_ROMAN);
			fontMathematicsFTG.setStyle(Font.NORMAL);
			fontMathematicsFTG.setSize(12);
			fontMathematicsFTG.setColor(Color.BLACK);
			chunkMathematicsFTG.setFont(fontRNo);
			Paragraph paraTitleMathematicsFTG = new Paragraph();	
			paraTitleMathematicsFTG.setSpacingBefore(9);
			paraTitleMathematicsFTG.add(chunkMathematicsFTG);
			paraTitleMathematicsFTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleMathematicsFTG);
			
			Chunk chunkMathematicsSTG = new Chunk("                                                                                        50 ");
			Font fontMathematicsSTG = new Font(Font.TIMES_ROMAN);
			fontMathematicsSTG.setStyle(Font.NORMAL);
			fontMathematicsSTG.setSize(12);
			fontMathematicsSTG.setColor(Color.BLACK);
			chunkMathematicsSTG.setFont(fontRNo);
			Paragraph paraTitleMathematicsSTG = new Paragraph();	
			paraTitleMathematicsSTG.setSpacingBefore(-16);
			paraTitleMathematicsSTG.add(chunkMathematicsSTG);
			paraTitleMathematicsSTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleMathematicsSTG);
			
			Chunk chunkScienceFTG = new Chunk("                                                           55 ");
			Font fontScienceFTG = new Font(Font.TIMES_ROMAN);
			fontScienceFTG.setStyle(Font.NORMAL);
			fontScienceFTG.setSize(12);
			fontScienceFTG.setColor(Color.BLACK);
			chunkScienceFTG.setFont(fontRNo);
			Paragraph paraTitleScienceFTG = new Paragraph();	
			paraTitleScienceFTG.setSpacingBefore(5);
			paraTitleScienceFTG.add(chunkScienceFTG);
			paraTitleScienceFTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleScienceFTG);
			
			Chunk chunkScienceSTG = new Chunk("                                                                                        50 ");
			Font fontScienceSTG = new Font(Font.TIMES_ROMAN);
			fontScienceSTG.setStyle(Font.NORMAL);
			fontScienceSTG.setSize(12);
			fontScienceSTG.setColor(Color.BLACK);
			chunkScienceSTG.setFont(fontRNo);
			Paragraph paraTitleScienceSTG = new Paragraph();	
			paraTitleScienceSTG.setSpacingBefore(-16);
			paraTitleScienceSTG.add(chunkScienceSTG);
			paraTitleScienceSTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleScienceSTG);
			
			Chunk chunkSocialStudiesFTG = new Chunk("                                                           55 ");
			Font fontSocialStudiesFTG = new Font(Font.TIMES_ROMAN);
			fontSocialStudiesFTG.setStyle(Font.NORMAL);
			fontSocialStudiesFTG.setSize(12);
			fontSocialStudiesFTG.setColor(Color.BLACK);
			chunkSocialStudiesFTG.setFont(fontRNo);
			Paragraph paraTitleSocialStudiesFTG = new Paragraph();	
			paraTitleSocialStudiesFTG.setSpacingBefore(5);
			paraTitleSocialStudiesFTG.add(chunkSocialStudiesFTG);
			paraTitleSocialStudiesFTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleSocialStudiesFTG);
			
			Chunk chunkSocialStudiesSTG = new Chunk("                                                                                        50 ");
			Font fontSocialStudiesSTG = new Font(Font.TIMES_ROMAN);
			fontSocialStudiesSTG.setStyle(Font.NORMAL);
			fontSocialStudiesSTG.setSize(12);
			fontSocialStudiesSTG.setColor(Color.BLACK);
			chunkSocialStudiesSTG.setFont(fontRNo);
			Paragraph paraTitleSocialStudieSTG = new Paragraph();	
			paraTitleSocialStudieSTG.setSpacingBefore(-16);
			paraTitleSocialStudieSTG.add(chunkSocialStudiesSTG);
			paraTitleSocialStudieSTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleSocialStudieSTG);
			
			Chunk chunkDrawingFTG = new Chunk("                                                           55 ");
			Font fontDrawingFTG = new Font(Font.TIMES_ROMAN);
			fontDrawingFTG.setStyle(Font.NORMAL);
			fontDrawingFTG.setSize(12);
			fontDrawingFTG.setColor(Color.BLACK);
			chunkDrawingFTG.setFont(fontRNo);
			Paragraph paraTitleDrawingFTG = new Paragraph();
			paraTitleDrawingFTG.setSpacingBefore(5);
			paraTitleDrawingFTG.add(chunkDrawingFTG);
			paraTitleDrawingFTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleDrawingFTG);
			
			Chunk chunkDrawingSTG = new Chunk("                                                                                        50 ");
			Font fontDrawingSTG = new Font(Font.TIMES_ROMAN);
			fontDrawingSTG.setStyle(Font.NORMAL);
			fontDrawingSTG.setSize(12);
			fontDrawingSTG.setColor(Color.BLACK);
			chunkDrawingSTG.setFont(fontRNo);
			Paragraph paraTitleDrawingSTG = new Paragraph();
			paraTitleDrawingSTG.setSpacingBefore(-16);
			paraTitleDrawingSTG.add(chunkDrawingSTG);
			paraTitleDrawingSTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleDrawingSTG);
			
			Chunk chunkComputerFTG = new Chunk("                                                           55 ");
			Font fontComputerFTG = new Font(Font.TIMES_ROMAN);
			fontComputerFTG.setStyle(Font.NORMAL);
			fontComputerFTG.setSize(12);
			fontComputerFTG.setColor(Color.BLACK);
			chunkComputerFTG.setFont(fontRNo);
			Paragraph paraTitleComputerFTG = new Paragraph();	
			paraTitleComputerFTG.setSpacingBefore(5);
			paraTitleComputerFTG.add(chunkComputerFTG);
			paraTitleComputerFTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleComputerFTG);
		
			Chunk chunkComputerSTG = new Chunk("                                                                                        50 ");
			Font fontComputerSTG = new Font(Font.TIMES_ROMAN);
			fontComputerSTG.setStyle(Font.NORMAL);
			fontComputerSTG.setSize(12);
			fontComputerSTG.setColor(Color.BLACK);
			chunkComputerSTG.setFont(fontRNo);
			Paragraph paraTitleComputerSTG = new Paragraph();	
			paraTitleComputerSTG.setSpacingBefore(-16);
			paraTitleComputerSTG.add(chunkComputerSTG);
			paraTitleComputerSTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleComputerSTG);
			
			Chunk chunkPhysicalFTG = new Chunk("                                                           55 ");
			Font fontPhysicalFTG = new Font(Font.TIMES_ROMAN);
			fontPhysicalFTG.setStyle(Font.NORMAL);
			fontPhysicalFTG.setSize(12);
			fontPhysicalFTG.setColor(Color.BLACK);
			chunkPhysicalFTG.setFont(fontRNo);
			Paragraph paraTitlePhysicalFTG = new Paragraph();	
			paraTitlePhysicalFTG.setSpacingBefore(5);
			paraTitlePhysicalFTG.add(chunkPhysicalFTG);
			paraTitlePhysicalFTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitlePhysicalFTG);
			
			Chunk chunkPhysicalSTG = new Chunk("                                                                                        50 ");
			Font fontPhysicalSTG = new Font(Font.TIMES_ROMAN);
			fontPhysicalSTG.setStyle(Font.NORMAL);
			fontPhysicalSTG.setSize(12);
			fontPhysicalSTG.setColor(Color.BLACK);
			chunkPhysicalSTG.setFont(fontRNo);
			Paragraph paraTitlePhysicalSTG = new Paragraph();	
			paraTitlePhysicalSTG.setSpacingBefore(-16);
			paraTitlePhysicalSTG.add(chunkPhysicalSTG);
			paraTitlePhysicalSTG.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitlePhysicalSTG);			
			
			Chunk chunkResult = new Chunk("                                                                            VIII");
			Font fontResult = new Font(Font.TIMES_ROMAN);
			fontResult.setStyle(Font.NORMAL);
			fontResult.setSize(12);
			fontResult.setColor(Color.BLACK);
			chunkResult.setFont(fontRNo);
			Paragraph paraTitleResult = new Paragraph();	
			paraTitleResult.setSpacingBefore(30);
			paraTitleResult.add(chunkResult);
			paraTitleResult.setAlignment(Element.ALIGN_LEFT);
			document.add(paraTitleResult);

			
			
			document.close();
			
			// open pdf 

				String fileAddress= "D:\\yash\\PreResult.pdf";    //"D:\\java\\resume.pdf";
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
