package it.ltc.logica.utilities.pdf.tabelle;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

public class TabellaPDF { 
	
	private final PreferenzeTabellaPDF preferences;

    // Table attributes
    private final float margin;
    private final float height;
    private final PDRectangle pageSize;
    private final boolean isLandscape;
    private final float rowHeight;
    private final float headerRowHeight;
    private final float cellMargin;
    private final float headerCellMargin;
    private final Color headerColor;
    
    // font attributes
    private final PDFont textFont;
    private final float fontSize;

    // Content attributes
    private final int numberOfRows;
    private final List<ColonnaPDF> columns;
    private final List<String[]> content;
    

    public TabellaPDF(List<ColonnaPDF> tableColumns, List<String[]> tableContent, PreferenzeTabellaPDF tablePreferences) {
    	preferences = tablePreferences;
    	pageSize = preferences.getPageSize();
    	isLandscape = preferences.isLandscape();
    	margin = preferences.getMargin();
    	cellMargin = preferences.getCellMargin();
    	headerCellMargin = preferences.getHeaderCellMargin();
    	rowHeight = preferences.getRowHeight();
    	headerRowHeight = preferences.getHeaderRowHeight();
    	headerColor = preferences.getHeaderColor();
    	textFont = preferences.getTextFont();
    	fontSize = preferences.getFontSize();
    	content = tableContent;
    	numberOfRows = tableContent.size();
    	height = calculateTableHeight();
    	columns = tableColumns;
    }
    
    public TabellaPDF(List<String> columnNames, List<String[]> tableContent) {
    	preferences = new PreferenzeTabellaPDF();
    	pageSize = preferences.getPageSize();
    	isLandscape = preferences.isLandscape();
    	margin = preferences.getMargin();
    	cellMargin = preferences.getCellMargin();
    	headerCellMargin = preferences.getHeaderCellMargin();
    	rowHeight = preferences.getRowHeight();
    	headerRowHeight = preferences.getHeaderRowHeight();
    	headerColor = preferences.getHeaderColor();
    	textFont = preferences.getTextFont();
    	fontSize = preferences.getFontSize();
    	content = tableContent;
    	numberOfRows = tableContent.size();
    	height = calculateTableHeight();
    	columns = generateColumns(columnNames);
    }
    
    private float calculateTableHeight() {
    	float tableHeight = isLandscape ? pageSize.getWidth() - (2 * margin) : pageSize.getHeight() - (2 * margin);
    	return tableHeight;
    }
    
    private List<ColonnaPDF> generateColumns(List<String> columnNames) {
    	List<ColonnaPDF> columns = new ArrayList<>();
    	for (String name : columnNames) {
    		float width = calculateTextWidth(name) + headerCellMargin;
    		if (width > preferences.getMaxColumnWidth())
    			width = preferences.getMaxColumnWidth();
			ColonnaPDF colonna = new ColonnaPDF(name, width);
	    	columns.add(colonna);
    	}
    	return columns;
    }
    
    public float calculateTextWidth(String text) {
    	float width;
    	try {
			width = textFont.getStringWidth(text) * fontSize / 1000f;
		} catch (IOException e) { width = 0; }
    	return width;
    }

    public Integer getNumberOfColumns() {
        return this.getColumns().size();
    }

    public float getWidth() {
        float tableWidth = 0f;
        for (ColonnaPDF column : columns) {
            tableWidth += column.getWidth();
        }
        return tableWidth;
    }

    public float getMargin() {
        return margin;
    }

    public PDRectangle getPageSize() {
        return pageSize;
    }

    public PDFont getTextFont() {
        return textFont;
    }

    public float getFontSize() {
        return fontSize;
    }

    public String[] getColumnsNamesAsArray() {
        String[] columnNames = new String[getNumberOfColumns()];
        for (int i = 0; i < getNumberOfColumns(); i++) {
            columnNames[i] = columns.get(i).getName();
        }
        return columnNames;
    }

    public List<ColonnaPDF> getColumns() {
        return columns;
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public float getHeight() {
        return height;
    }

    public float getRowHeight() {
        return rowHeight;
    }

    public float getHeaderRowHeight() {
		return headerRowHeight;
	}

	public List<String[]> getContent() {
        return content;
    }

    public float getCellMargin() {
        return cellMargin;
    }

    public float getHeaderCellMargin() {
		return headerCellMargin;
	}

	public boolean isLandscape() {
        return isLandscape;
    }

	public Color getHeaderColor() {
		return headerColor;
	}
}
