package it.ltc.logica.ufficio.gui.elements.prodottopermodello;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import it.ltc.logica.common.controller.prodotti.ProdottoConQuantita;

public class ProdottiConQuantitaEditingSupport extends EditingSupport {

	private final TableViewer viewer;
	private final CellEditor editor;
	
	public ProdottiConQuantitaEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
		this.editor = new TextCellEditor(viewer.getTable());
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		ProdottoConQuantita pcq = (ProdottoConQuantita) element;
		String value = Integer.toString(pcq.getQuantita());
		return value;
	}

	@Override
	protected void setValue(Object element, Object value) {
		if (element != null && value != null) {
			try {
				Integer nuovaQuantita = Integer.parseInt(value.toString());
				//La quantita' inserita deve essere almeno 0.
				if (nuovaQuantita < 0)
					nuovaQuantita = 0;
				ProdottoConQuantita pcq = (ProdottoConQuantita) element;
				pcq.setQuantita(nuovaQuantita);
				viewer.update(element, null);
			} catch (NumberFormatException e) {}
		}
	}

}
