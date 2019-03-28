package it.ltc.logica.trasporti.gui.elements.indirizzo;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.gui.common.tables.indirizzo.ToolbarIndirizziBase;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarIndirizzi extends ToolbarIndirizziBase {

	public ToolbarIndirizzi(Composite parent) {
		super(parent);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_INDIRIZZI_GESTIONE.getID();
	}
}
