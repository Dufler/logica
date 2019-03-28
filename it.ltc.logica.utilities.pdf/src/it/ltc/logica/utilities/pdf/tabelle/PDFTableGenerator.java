package it.ltc.logica.utilities.pdf.tabelle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.util.Matrix;

import it.ltc.logica.utilities.pdf.DocumentoPDF;
import it.ltc.logica.utilities.pdf.elementi.RettangoloPDF;

public class PDFTableGenerator {

	// Generates document from Table object
	public void generatePDF(DocumentoPDF documento, List<String> columnNames, List<String[]> content) throws IOException {
		PDDocument document = documento.getDocument();
		TabellaPDF table = new TabellaPDF(columnNames, content);
		drawTable(document, table);
	}
	
	public void generatePDF(DocumentoPDF documento, List<ColonnaPDF> columnNames, List<String[]> content, PreferenzeTabellaPDF preferenze) throws IOException {
		PDDocument document = documento.getDocument();
		TabellaPDF table = new TabellaPDF(columnNames, content, preferenze);
		drawTable(document, table);
	}

	// Configures basic setup for the table and draws it page by page
	public void drawTable(PDDocument doc, TabellaPDF table) throws IOException {
		// Calculate pagination
		Integer rowsPerPage = new Double(Math.floor(table.getHeight() / table.getRowHeight())).intValue() - 1; // subtract
		Integer numberOfPages = new Double(Math.ceil(table.getNumberOfRows().floatValue() / rowsPerPage)).intValue();

		// Generate each page, get the content and draw it
		for (int pageCount = 0; pageCount < numberOfPages; pageCount++) {
			PDPage page = generatePage(doc, table);
			PDPageContentStream contentStream = generateContentStream(doc, page, table);
			List<String[]> currentPageContent = getContentForCurrentPage(table, rowsPerPage, pageCount);
			drawCurrentPage(table, currentPageContent, contentStream);
			contentStream.close();
		}
	}

	// Draws current page table grid and border lines and content
	private void drawCurrentPage(TabellaPDF table, List<String[]> currentPageContent, PDPageContentStream contentStream) throws IOException {
		float tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize().getHeight() - table.getMargin();

		// Draws grid and borders
		drawTableGrid(table, currentPageContent, contentStream, tableTopY);

		// Position cursor to start drawing content
		float nextTextX = table.getMargin() + table.getCellMargin();
		// Calculate center alignment for text in cell considering font height
		float nextTextY = tableTopY - (table.getRowHeight() / 2) - ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table.getFontSize()) / 4);

		// Write column headers
		writeContentLine(table.getColumnsNamesAsArray(), contentStream, nextTextX, nextTextY, table);
		nextTextY -= table.getRowHeight();
		nextTextX = table.getMargin() + table.getCellMargin();

		// Write content
		for (int i = 0; i < currentPageContent.size(); i++) {
			writeContentLine(currentPageContent.get(i), contentStream, nextTextX, nextTextY, table);
			nextTextY -= table.getRowHeight();
			nextTextX = table.getMargin() + table.getCellMargin();
		}
	}

	// Writes the content for one line
	private void writeContentLine(String[] lineContent, PDPageContentStream contentStream, float nextTextX, float nextTextY, TabellaPDF table) throws IOException {
		// for (int i = 0; i < table.getNumberOfColumns(); i++) {
		// String text = lineContent[i] != null ? lineContent[i] : "";
		// contentStream.beginText();
		// contentStream.newLineAtOffset(nextTextX, nextTextY);
		// contentStream.showText(text);
		// contentStream.endText();
		// nextTextX += table.getColumns().get(i).getWidth();
		// }
		for (int i = 0; i < table.getNumberOfColumns(); i++) {
			String text = lineContent[i] != null ? lineContent[i] : "";
			float width = table.getColumns().get(i).getWidth();
			// Controllo se la stringa è troppo lunga
			if (width < table.calculateTextWidth(text) + table.getCellMargin()) {
				while (width < table.calculateTextWidth(text + "...") + table.getCellMargin()) {
					text = text.substring(0, text.length() - 1);
				}
				text += "...";
			}
			contentStream.beginText();
			contentStream.newLineAtOffset(nextTextX, nextTextY);
			contentStream.showText(text);
			contentStream.endText();
			nextTextX += width;
		}
	}

	private void drawTableGrid(TabellaPDF table, List<String[]> currentPageContent, PDPageContentStream contentStream, float tableTopY) throws IOException {
		// Colora l'header della tabella
		RettangoloPDF header = new RettangoloPDF(table.getMargin(), tableTopY - table.getRowHeight(), table.getWidth(), table.getRowHeight(), table.getHeaderColor());
		header.aggiungi(contentStream);

		// TODO - disegnare l'header a parte, magari anche con font e dimensioni
		// diverse.

		// Draw row lines
		float nextY = tableTopY;
		for (int i = 0; i <= currentPageContent.size() + 1; i++) {
			contentStream.moveTo(table.getMargin(), nextY);
			contentStream.lineTo(table.getMargin() + table.getWidth(), nextY);
			contentStream.stroke();
			nextY -= table.getRowHeight();
		}

		// Draw column lines
		final float tableYLength = table.getRowHeight() + (table.getRowHeight() * currentPageContent.size());
		final float tableBottomY = tableTopY - tableYLength;
		float nextX = table.getMargin();
		for (int i = 0; i < table.getNumberOfColumns(); i++) {
			contentStream.moveTo(nextX, tableTopY);
			contentStream.lineTo(nextX, tableBottomY);
			contentStream.stroke();
			nextX += table.getColumns().get(i).getWidth();
		}
		contentStream.moveTo(nextX, tableTopY);
		contentStream.lineTo(nextX, tableBottomY);
		contentStream.stroke();
	}

	private List<String[]> getContentForCurrentPage(TabellaPDF table, Integer rowsPerPage, int pageCount) {
		int startRange = pageCount * rowsPerPage;
		int endRange = (pageCount * rowsPerPage) + rowsPerPage;
		if (endRange > table.getNumberOfRows()) {
			endRange = table.getNumberOfRows();
		}
		List<String[]> currentContent = new ArrayList<>();
		currentContent = table.getContent().subList(startRange, endRange);
		return currentContent;
		// return Arrays.copyOfRange(table.getContent(), startRange, endRange);
	}

	private PDPage generatePage(PDDocument doc, TabellaPDF table) {
		PDPage page = new PDPage();
		page.setMediaBox(table.getPageSize());
		page.setRotation(table.isLandscape() ? 90 : 0);
		doc.addPage(page);
		return page;
	}

	private PDPageContentStream generateContentStream(PDDocument doc, PDPage page, TabellaPDF table) throws IOException {
		PDPageContentStream contentStream = new PDPageContentStream(doc, page, AppendMode.APPEND, false, false);
		// User transformation matrix to change the reference when drawing.
		// This is necessary for the landscape position to draw correctly
		if (table.isLandscape()) {
			Matrix matrix = new Matrix(0, 1, -1, 0, table.getPageSize().getWidth(), 0);
			contentStream.transform(matrix);
		}
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		return contentStream;
	}
}
