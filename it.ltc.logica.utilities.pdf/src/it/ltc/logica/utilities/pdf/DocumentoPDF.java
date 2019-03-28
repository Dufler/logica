package it.ltc.logica.utilities.pdf;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

public class DocumentoPDF {
	
	private final PDDocument document;
	protected final List<PaginaPDF> pagine;
	
	public DocumentoPDF() {
		document = new PDDocument();
		pagine = new LinkedList<>();
	}
	
	public void stampa(String completePath) throws IOException {
		for (PaginaPDF pagina : pagine) {
			pagina.finalizza();
		}
		document.save(completePath);
	    document.close();
	}
	
	protected void aggiungiPagina(PaginaPDF pagina) {
		pagine.add(pagina);
	}

	public PDDocument getDocument() {
		return document;
	}

}
