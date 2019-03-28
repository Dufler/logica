package it.ltc.logica.gui.elements;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class ListenerColonna<T> extends SelectionAdapter {
	
	private final int index;
	private final TableColumn column;
	private final Ordinatore<T> sorter;
	private final Table table;
	private final Tabella<T> viewer;
	
	public ListenerColonna(int indiceColonna, TableColumn colonna, Table tabella, Ordinatore<T> ordinatore, Tabella<T> visionatore) {
		index = indiceColonna;
		column = colonna;
		table = tabella;
		sorter = ordinatore;
		viewer = visionatore;
	}
	
	@Override
    public void widgetSelected(SelectionEvent e) {
		sorter.setColumn(index);
		int direction = sorter.getDirection();
		table.setSortDirection(direction);
		table.setSortColumn(column);
		viewer.refresh();
		viewer.aggiustaLarghezzaColonne();
	}

}
