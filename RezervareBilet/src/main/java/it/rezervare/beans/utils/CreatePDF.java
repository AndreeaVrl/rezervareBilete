package it.rezervare.beans.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import it.rezervare.beans.model.hibernateBeans.Bilet;

public class CreatePDF {

	private static Font TIME_ROMAN_SMALL = PDFHelper.getTimesNewRomanFont();
	private static Font FONT_FOR_LABEL= PDFHelper.getTimesNewRomanFont();
	
	static class MyFooter extends PdfPageEventHelper {

		private final String loogo;
        
		public MyFooter(final String logo){
        	this.loogo = logo;
        }
        public void onEndPage(final PdfWriter writer, final Document document) {
        	final PdfContentByte cb = writer.getDirectContent();
    		ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(String.format("Pagina %d ", 
    		        writer.getPageNumber()),TIME_ROMAN_SMALL), 
    		        document.right()  , document.bottom(), 0);
    		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("Raport generat de "+ loogo, TIME_ROMAN_SMALL), 
    		        document.left() , document.bottom(), 0);
        }
	 }
	
	public static Document createPDF(final String file, final Bilet bilet, final BigDecimal suma, final String titlePdf) throws MalformedURLException, IOException {

		Document document = new Document();

		try {
			document = new Document(PageSize.A4, 25, 25, 25, 60);
			final PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));
			final MyFooter event = new MyFooter("Euro Travel");
			pdfWriter.setPageEvent(event);
			document.open();

			addMetaData(document);

			addTitlePage(pdfWriter,document, titlePdf);

			createTable(document, bilet, suma);
			
			document.close();

		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final DocumentException e) {
			e.printStackTrace();
		}
		return document;

	}

	private static void addMetaData(final Document document) {
		document.addTitle("PDF Check-in");
		document.addSubject("Generate PDF check-in");
		document.addAuthor("EuroTravel");
		document.addCreator("EuroTravel");
	}

	private static void addTitlePage(final PdfWriter writer,final Document document, final String titlePdf) throws DocumentException, MalformedURLException, IOException {
		final Font font = PDFHelper.getTimesNewRomanFontForTitle();
		final PdfContentByte cb = writer.getDirectContent();
		final Paragraph header = new Paragraph();
		final PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[] { 100, 600 });
		/*final String ugalLogoSrc = ApplicationConstants.PATH_LOGO;
		final Image ugalLogo = Image.getInstance(ugalLogoSrc);
		ugalLogo.scalePercent(13f);*/
		/*final PdfPCell c1 = new PdfPCell(ugalLogo);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBorder(Rectangle.NO_BORDER);
		table.addCell(c1);*/
		final PdfPCell c2 = new PdfPCell(new Paragraph("Rezervare bilete online", font));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		c2.setBorder(Rectangle.NO_BORDER);
		table.addCell(c2);
		
		document.add(table);
		creteEmptyLine(header,3);
		document.add(header);
		final Font fontForReportType = PDFHelper.getTimesNewRomanFontFourteen();
		final Paragraph title = new Paragraph(titlePdf, fontForReportType);
		creteEmptyLine(title,2);
		ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                header,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.top() + 10, 0);
		document.add(title);
		

	}

	private static void creteEmptyLine(final Paragraph paragraph, final int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private static void createTable(final Document document, final Bilet bilet, final BigDecimal suma) {
		final Paragraph paragraph = new Paragraph(bilet.getClientBilet().getNume()+" "+bilet.getClientBilet().getPrenume());
		try {
			creteEmptyLine(paragraph, 1);
			final PdfPTable table = new PdfPTable(2);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.setWidths(new int[] { 350, 350});
			table.setWidthPercentage(100);
			PdfPCell c1 = new PdfPCell(new Phrase("Date Bilet",FONT_FOR_LABEL));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
	
			c1 = new PdfPCell(new Phrase("Date Pasager",FONT_FOR_LABEL));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			table.setHeaderRows(1);
			System.out.println(String.valueOf(bilet.getId()));
			System.out.println(bilet.getClientBilet().getNume()+" "+bilet.getClientBilet().getPrenume());
			System.out.println(String.valueOf(bilet.getClientBilet().getDataNasterii()));
			System.out.println(bilet.getClientRezervare().getNume()+" "+bilet.getClientRezervare().getPrenume());
			System.out.println(bilet.getPachet().getDenumire());
			System.out.println(bilet.getPachet().getCaracteristici());
			System.out.println(bilet.getLoc().getRand()+bilet.getLoc().getColoana());
			System.out.println(String.valueOf(bilet.getPachet().getTaxaPachet()));
			System.out.println(String.valueOf(bilet.getZbor().getPret()));
			System.out.println(String.valueOf(suma));
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(new Paragraph("Numar bilet", FONT_FOR_LABEL));
			table.addCell(new Paragraph(String.valueOf(bilet.getId()), TIME_ROMAN_SMALL));
			table.addCell(new Paragraph("Nume pasagert", FONT_FOR_LABEL));
			table.addCell(new Paragraph(bilet.getClientBilet().getNume()+" "+bilet.getClientBilet().getPrenume(), TIME_ROMAN_SMALL));
			table.addCell(new Paragraph("Data nasterii", FONT_FOR_LABEL));
			table.addCell(new Paragraph(String.valueOf(bilet.getClientBilet().getDataNasterii()), TIME_ROMAN_SMALL));
			table.addCell(new Paragraph("Rezervarea realizata de", FONT_FOR_LABEL));
			table.addCell(new Paragraph(bilet.getClientRezervare().getNume()+" "+bilet.getClientRezervare().getPrenume(), TIME_ROMAN_SMALL));
			table.addCell(new Paragraph("Pachet", FONT_FOR_LABEL));
			table.addCell(new Paragraph(bilet.getPachet().getDenumire(), TIME_ROMAN_SMALL));
			table.addCell(new Paragraph("Facilitati pachet", FONT_FOR_LABEL));
			table.addCell(new Paragraph(bilet.getPachet().getCaracteristici(), TIME_ROMAN_SMALL));
			table.addCell(new Paragraph("Loc", FONT_FOR_LABEL));
			table.addCell(new Paragraph(bilet.getLoc().getRand()+bilet.getLoc().getColoana(), TIME_ROMAN_SMALL));
			table.addCell(new Paragraph("Pret loc", FONT_FOR_LABEL));
			table.addCell(new Paragraph(String.valueOf(bilet.getPachet().getTaxaPachet()), TIME_ROMAN_SMALL));
			table.addCell(new Paragraph("Pret zbor", FONT_FOR_LABEL));
			table.addCell(new Paragraph(String.valueOf(bilet.getZbor().getPret()), TIME_ROMAN_SMALL));
			table.addCell(new Paragraph("Suma totala", FONT_FOR_LABEL));
			table.addCell(new Paragraph(String.valueOf(suma), TIME_ROMAN_SMALL));
			
			paragraph.add(table);
			creteEmptyLine(paragraph, 1);
			document.add(paragraph);
			document.newPage();
		} catch (final DocumentException e) {
			
			e.printStackTrace();
		}
	}

}
