package it.ltc.logica.utilities.pdf.tabelle;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PreferenzeTabellaPDF {
	
	private static final Color DEFAULT_HEADER_COLOR = Color.CYAN;
	
	private static final PDRectangle DEFAULT_PAGE_SIZE = PDRectangle.A4;
	private static final boolean DEFAULT_LANDSCAPE = true;
	private static final float DEFAULT_MARGIN = 20;
	private static final float DEFAULT_MAX_COLUMN_WIDTH = 200;
	
	private static final float DEFAULT_CELL_MARGIN = 2;
	private static final float DEFAULT_ROW_HEIGHT = 15;
	
	private static final float DEFAULT_HEADER_CELL_MARGIN = 5;
	private static final float DEFAULT_HEADER_ROW_HEIGHT = 15;

    private static final PDFont DEFAULT_TEXT_FONT = PDType1Font.HELVETICA;
    private static final float DEFAULT_FONT_SIZE = 10;   
	
	private final PDRectangle pageSize;
	private final boolean isLandscape;
	private final float margin;
	private final float cellMargin;
    private final float headerCellMargin;
    private final Color headerColor;
    private final float rowHeight;
    private final float headerRowHeight;
    private final PDFont textFont;
    private final float fontSize;
    private final float maxColumnWidth;
	
	public PreferenzeTabellaPDF() {
		pageSize = DEFAULT_PAGE_SIZE;
    	isLandscape = DEFAULT_LANDSCAPE;
    	margin = DEFAULT_MARGIN;
    	cellMargin = DEFAULT_CELL_MARGIN;
    	headerCellMargin = DEFAULT_HEADER_CELL_MARGIN;
    	rowHeight = DEFAULT_ROW_HEIGHT;
    	headerRowHeight = DEFAULT_HEADER_ROW_HEIGHT;
    	headerColor = DEFAULT_HEADER_COLOR;
    	textFont = DEFAULT_TEXT_FONT;
    	fontSize = DEFAULT_FONT_SIZE;
    	maxColumnWidth = DEFAULT_MAX_COLUMN_WIDTH;
	}
	
	public PreferenzeTabellaPDF(boolean isLandscape, float maxColumnWidth, float margin, float cellMargin, float headerCellMargin, float rowHeight, float headerRowHeight, float fontSize, Color headerColor) {
		this.isLandscape = isLandscape;
		this.maxColumnWidth = maxColumnWidth;
		this.margin = margin;
		this.cellMargin = cellMargin;
		this.headerCellMargin = headerCellMargin;
		this.rowHeight = rowHeight;
		this.headerRowHeight = headerRowHeight;
		this.headerColor = headerColor;
		this.fontSize = fontSize;
		this.textFont = DEFAULT_TEXT_FONT;
		this.pageSize = DEFAULT_PAGE_SIZE;
	}
	
	public float calculateTableHeight() {
    	float tableHeight = isLandscape ? pageSize.getWidth() - (2 * margin) : pageSize.getHeight() - (2 * margin);
    	return tableHeight;
    }
	
	public float calculateTextWidth(String text) {
    	float width;
    	try {
			width = textFont.getStringWidth(text) * fontSize / 1000f;
		} catch (IOException e) { width = 0; }
    	return width;
    }

	public PDRectangle getPageSize() {
		return pageSize;
	}

	public boolean isLandscape() {
		return isLandscape;
	}

	public float getMargin() {
		return margin;
	}

	public float getCellMargin() {
		return cellMargin;
	}

	public float getHeaderCellMargin() {
		return headerCellMargin;
	}

	public Color getHeaderColor() {
		return headerColor;
	}

	public float getRowHeight() {
		return rowHeight;
	}

	public float getHeaderRowHeight() {
		return headerRowHeight;
	}

	public PDFont getTextFont() {
		return textFont;
	}

	public float getFontSize() {
		return fontSize;
	}

	public float getMaxColumnWidth() {
		return maxColumnWidth;
	}

}
