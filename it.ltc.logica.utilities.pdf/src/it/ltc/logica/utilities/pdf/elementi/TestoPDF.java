package it.ltc.logica.utilities.pdf.elementi;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

import it.ltc.logica.utilities.pdf.ElementoPDF;

public abstract class TestoPDF extends ElementoPDF {
	
	protected final String text;
	protected final float xOffset;
	protected final float yOffset;
	protected final float size;
	protected final PDFont font;
	protected final Color color;
	
	public TestoPDF(String text, float x, float y, float size, PDFont font, Color color) {
		this.text = text;
		this.xOffset = x;
		this.yOffset = y;
		this.size = size;
		this.font = font;
		this.color = color;
	}

	@Override
	public void aggiungi(PDPageContentStream stream) throws IOException {
		stream.setNonStrokingColor(color);
		stream.beginText();
		stream.newLineAtOffset(xOffset, yOffset);
		stream.setFont(font, size);
        stream.showText(text);
        stream.newLine();
        stream.endText();
        stream.setNonStrokingColor(DEFAULT_COLOR);
	}
	
	public float getWidth() {
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
