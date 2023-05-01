import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class StudentList {

    public static void main(String[] args) {

        try {

              OutputStream file = new FileOutputStream(new File("D:\\StudentList.pdf"));
	          Document document = new Document();
	          PdfWriter.getInstance(document, file);

			//Inserting Image in PDF
//			     Image image = Image.getInstance ("src/pdf/java4s.png");
//			     image.scaleAbsolute(120f, 60f);//image width,height	

			//Inserting Table in PDF
			     PdfPTable table=new PdfPTable(5);

	                     PdfPCell cell = new PdfPCell (new Paragraph ("Java4s.com"));

				      cell.setColspan (5);
				      
				      cell.setHorizontalAlignment (Element.ALIGN_CENTER);
				      cell.setPadding (10.0f);
				      cell.setBackgroundColor (new BaseColor (140, 221, 8));					               

				      table.addCell(cell);						               
				       PdfPCell Rollcell = new PdfPCell (new Paragraph ("Roll No"));
				       Rollcell.setHorizontalAlignment (Element.ALIGN_CENTER);
				      table.addCell(Rollcell);
				      
				      PdfPCell GRcell = new PdfPCell (new Paragraph ("G.R.No"));
				       GRcell.setHorizontalAlignment (Element.ALIGN_CENTER);
				      table.addCell(GRcell);
				      
				      PdfPCell NMcell = new PdfPCell (new Paragraph ("Name of the Students"));
				      NMcell.setHorizontalAlignment (Element.ALIGN_CENTER);
				      table.addCell(NMcell);
				      
				      PdfPCell Gendercell = new PdfPCell (new Paragraph ("Gender"));
				      Gendercell.setHorizontalAlignment (Element.ALIGN_CENTER);
                      table.addCell(Gendercell);
                      
				      PdfPCell BDcell = new PdfPCell (new Paragraph ("Birth Date"));
				      BDcell.setHorizontalAlignment (Element.ALIGN_CENTER);
                      table.addCell(BDcell);
                      
                      PdfPCell RN1cell = new PdfPCell (new Paragraph ("1"));
                      RN1cell.setHorizontalAlignment (Element.ALIGN_CENTER);
				      table.addCell(RN1cell);
				      
                      PdfPCell GR1cell = new PdfPCell (new Paragraph ("0001"));
                      GR1cell.setHorizontalAlignment (Element.ALIGN_CENTER);
				      table.addCell(GR1cell);
				      
				      PdfPCell NM1cell = new PdfPCell (new Paragraph ("AAA BBB CCC"));
                      NM1cell.setHorizontalAlignment (Element.ALIGN_CENTER);				      
				      table.addCell(NM1cell);
				      
			
	      
				      PdfPCell G1cell = new PdfPCell (new Paragraph ("Male"));
				      G1cell.setHorizontalAlignment (Element.ALIGN_CENTER);	      
				      table.addCell(G1cell);
				      
				      
				      PdfPCell BD1cell = new PdfPCell (new Paragraph ("20/06/2010"));
			
	      BD1cell.setHorizontalAlignment (Element.ALIGN_CENTER);	      
				      table.addCell(BD1cell);
				      
                      PdfPCell RN2cell = new PdfPCell (new Paragraph ("2"));
                      RN2cell.setHorizontalAlignment (Element.ALIGN_CENTER);
				      table.addCell(RN2cell);
				      
                      PdfPCell GR2cell = new PdfPCell (new Paragraph ("0002"));
                      GR2cell.setHorizontalAlignment (Element.ALIGN_CENTER);
				      table.addCell(GR2cell);
				      
				      PdfPCell NM2cell = new PdfPCell (new Paragraph ("BBB CCC DDD"));
				      NM2cell.setHorizontalAlignment (Element.ALIGN_CENTER);				      
				      table.addCell(NM2cell);
				      
				      
				      PdfPCell G2cell = new PdfPCell (new Paragraph ("Female"));
				      G2cell.setHorizontalAlignment (Element.ALIGN_CENTER);	      
				      table.addCell(G2cell);
				      
				      
				      PdfPCell BD2cell = new PdfPCell (new Paragraph ("12/06/2009"));
				      BD2cell.setHorizontalAlignment (Element.ALIGN_CENTER);	      
				      table.addCell(BD2cell);

				      PdfPCell RN3cell = new PdfPCell (new Paragraph ("3"));
                      RN3cell.setHorizontalAlignment (Element.ALIGN_CENTER);
				      table.addCell(RN3cell);
				      
                      PdfPCell GR3cell = new PdfPCell (new Paragraph ("0003"));
                      GR3cell.setHorizontalAlignment (Element.ALIGN_CENTER);
				      table.addCell(GR3cell);
				      
				      PdfPCell NM3cell = new PdfPCell (new Paragraph ("CCC DDD EEE"));
				      NM3cell.setHorizontalAlignment (Element.ALIGN_CENTER);				      
				      table.addCell(NM3cell);
				      
				      
				      PdfPCell G3cell = new PdfPCell (new Paragraph ("Female"));
				      G3cell.setHorizontalAlignment (Element.ALIGN_CENTER);	      
				      table.addCell(G3cell);
				      
				      
				      PdfPCell BD3cell = new PdfPCell (new Paragraph ("02/09/2009"));
				      BD3cell.setHorizontalAlignment (Element.ALIGN_CENTER);	      
				      table.addCell(BD3cell);
			
				      table.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
				      table.setSpacingAfter(30.0f);        // Space After table starts, like margin-Bottom in CSS								          

			 //Inserting List in PDF
//				      List list=new List(true,30);
//			          list.add(new ListItem("Java4s"));
//				      list.add(new ListItem("Php4s"));
//				      list.add(new ListItem("Some Thing..."));		

			 //Text formating in PDF
//	                Chunk chunk=new Chunk("Welecome To Java4s Programming Blog...");
//					chunk.setUnderline(+1f,-2f);//1st co-ordinate is for line width,2nd is space between
//					Chunk chunk1=new Chunk("Php4s.com");
//					chunk1.setUnderline(+4f,-8f);
//					chunk1.setBackground(new BaseColor (17, 46, 193));      

			 //Now Insert Every Thing Into PDF Document
		         document.open();//PDF document opened........			       

//					document.add(image);

					document.add(Chunk.NEWLINE);   //Something like in HTML :-)

//                   document.add(new Paragraph("Dear Java4s.com"));
//	                document.add(new Paragraph("Document Generated On - "+new Date().toString()));	

					float[] columnWidths = new float[] {10f, 10f, 30f, 15f, 15f};
		            table.setWidths(columnWidths);
					document.add(table);

//					document.add(chunk);
//				
//	document.add(chunk1);

//					document.add(Chunk.NEWLINE);   //Something like in HTML :-)							    

//       				document.newPage();            //Opened new page
//					document.add(list);            //In the new page we are going to add list

		         document.close();

			             file.close();

            System.out.println("Pdf created successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}