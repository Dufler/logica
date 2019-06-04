package it.ltc.logica.ufficio.gui.elements.elementocassa;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.database.model.prodotto.ElementoCassa;
import it.ltc.logica.gui.elements.ToolbarCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarElementiCassa extends ToolbarCRUD<TabellaElementiCassa, ElementoCassa> {

	public ToolbarElementiCassa(Composite parent) {
		super(parent);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_PRODOTTI.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();
	}
	
	protected void aggiungiElementi(Composite composite) {
		Label lblComposizione = new Label(composite, SWT.NONE);
		lblComposizione.setText("Composizione: ");
	}

}
