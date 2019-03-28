package it.ltc.logica.utilities.pdf;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

public abstract class ElementoPDF {
	
	public static final Color DEFAULT_COLOR = Color.BLACK;

	public abstract void aggiungi(PDPageContentStream stream) throws IOException;
	
}
