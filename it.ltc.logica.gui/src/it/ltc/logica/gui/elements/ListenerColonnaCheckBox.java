package it.ltc.logica.gui.elements;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class ListenerColonnaCheckBox<T> extends SelectionAdapter {
	
	private final int index;
	private final TableColumn column;
	private final Ordinatore<T> sorter;
	private final Table table;
	private final TabellaCheckBox<T> viewer;
	
	public ListenerColonnaCheckBox(int indiceColonna, TableColumn colonna, Table tabella, Ordinatore<T> ordinatore, TabellaCheckBox<T> visionatore) {
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
//		for (TableColumn column : table.getColumns()) {
//			column.pack();
//		}
	}

}
