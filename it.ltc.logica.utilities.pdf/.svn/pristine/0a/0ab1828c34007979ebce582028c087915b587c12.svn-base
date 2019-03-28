package it.ltc.logica.utilities.pdf.elementi;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

import it.ltc.logica.utilities.pdf.ElementoPDF;

public class LineaPDF extends ElementoPDF {

	public static final Color DEFAULT_COLOR = Color.BLACK;
	
	protected final float x1;
	protected final float y1;
	protected final float x2;
	protected final float y2;
	protected final Color c;
	
	public LineaPDF(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.c = DEFAULT_COLOR;
	}
	
	public LineaPDF(float x1, float y1, float x2, float y2, Color c) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.c = c;
	}

	@Override
	public void aggiungi(PDPageContentStream stream) throws IOException {
		stream.setNonStrokingColor(c);
		stream.moveTo(x1, y1);
		stream.lineTo(x2, y2);
		stream.fill();
	}
	
}
