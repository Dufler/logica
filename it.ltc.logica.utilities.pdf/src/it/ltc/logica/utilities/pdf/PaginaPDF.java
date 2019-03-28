package it.ltc.logica.utilities.pdf;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class PaginaPDF {
	
	private static final PDRectangle DEFAULT_PAGE_SIZE = PDRectangle.A4;
	
	public static final int DEFAULT_ROTATION = 0;
	public static final int LANDSCAPE_ROTATION = 90;
	
	private final PDDocument document;
	private final PDPage page;
	
	protected final List<ElementoPDF> elementi;
	
	public PaginaPDF(DocumentoPDF documento) {
		elementi = new LinkedList<>();
		document = documento.getDocument();
		page = new PDPage();
		page.setMediaBox(DEFAULT_PAGE_SIZE);
		page.setRotation(DEFAULT_ROTATION);
		document.addPage(page);
		documento.aggiungiPagina(this);
	}
	
	public PaginaPDF(DocumentoPDF documento, boolean landscape) {
		elementi = new LinkedList<>();
		document = documento.getDocument();
		if (landscape) {
			page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
		} else {
			page = new PDPage(DEFAULT_PAGE_SIZE);
		}
		document.addPage(page);
		documento.aggiungiPagina(this);
	}
	
	public void aggiungiElemento(ElementoPDF elemento) {
		elementi.add(elemento);
	}

	public PDPage getPage() {
		return page;
	}
	
	public float getHeight() {
		PDRectangle pageSize = page.getMediaBox();
		return pageSize.getHeight();
	}
	
	public float getWidth() {
		PDRectangle pageSize = page.getMediaBox();
		return pageSize.getWidth();
	}

	public void finalizza() throws IOException {
		PDPageContentStream stream = new PDPageContentStream(document, page, AppendMode.APPEND, true, true);
		for (ElementoPDF elemento : elementi)
			elemento.aggiungi(stream);
		stream.close();	
	}

}
