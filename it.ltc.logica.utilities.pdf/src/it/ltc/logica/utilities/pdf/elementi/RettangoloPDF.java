package it.ltc.logica.utilities.pdf.elementi;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

import it.ltc.logica.utilities.pdf.ElementoPDF;

public class RettangoloPDF extends ElementoPDF {
	
	protected final float x;
	protected final float y;
	protected final float w;
	protected final float h;
	protected final Color c;
	
	public RettangoloPDF(float x, float y, float w, float h, Color c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
	}

	@Override
	public void aggiungi(PDPageContentStream stream) throws IOException {
		stream.setNonStrokingColor(c);
		stream.addRect(x, y, w, h);
		stream.fill();
		stream.setNonStrokingColor(DEFAULT_COLOR);
	}

}
