package it.ltc.logica.utilities.pdf.stili;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public enum StileTestoPDF {
	
	HELVETICA_10(PDType1Font.HELVETICA, 10, Color.BLACK),
	HELVETICA_10_BOLD(PDType1Font.HELVETICA_BOLD, 10, Color.BLACK),
	HELVETICA_12(PDType1Font.HELVETICA, 12, Color.BLACK),
	HELVETICA_12_BOLD(PDType1Font.HELVETICA_BOLD, 12, Color.BLACK),
	TITOLO(PDType1Font.HELVETICA_BOLD, 20, Color.BLACK);
	
	private final PDFont font;
	private final int size;
	private final Color color;
	
	private StileTestoPDF(PDFont font, int size, Color color) {
		this.font = font;
		this.size = size;
		this.color = color;
	}

	public PDFont getFont() {
		return font;
	}

	public int getSize() {
		return size;
	}

	public Color getColor() {
		return color;
	}
	
	public float getWidth(String text) {
		float width;
		try {
			width = font.getStringWidth(text) * size / 1000f;
		} catch (IOException e) {
			width = 0;
			e.printStackTrace();
		}
		return width;
	}

}
