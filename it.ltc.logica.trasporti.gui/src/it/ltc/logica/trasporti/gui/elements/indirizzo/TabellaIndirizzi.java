package it.ltc.logica.trasporti.gui.elements.indirizzo;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.common.tables.indirizzo.TabellaIndirizziBase;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaIndirizzi extends TabellaIndirizziBase {

	public TabellaIndirizzi(Composite parent, boolean aperturaDoppioClick) {
		super(parent, aperturaDoppioClick);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_INDIRIZZI_GESTIONE.getID();
	}

	@Override
	protected DialogIndirizzo creaDialog(Indirizzo elemento) {
		DialogIndirizzo dialog = new DialogIndirizzo(elemento);
		return dialog;
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerIndirizzi.getInstance().selezionaIndirizzi(criteriSelezione));
	}

}
