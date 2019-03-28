package it.ltc.logica.ufficio.gui.elements.mittente;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.uscite.ControllerMittenti;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.common.tables.indirizzo.TabellaIndirizziBase;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.ufficio.gui.elements.destinatario.DialogDestinatario;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaMittenti extends TabellaIndirizziBase {
	
	private final Commessa commessa;
	private final ControllerMittenti controller;

	public TabellaMittenti(Composite parent, boolean aperturaDoppioClick, Commessa commessa) {
		super(parent, aperturaDoppioClick);
		
		this.commessa = commessa;
		this.controller = new ControllerMittenti(commessa);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_USCITE.getID();
	}

	@Override
	protected DialogApribile creaDialog(Indirizzo elemento) {
		DialogDestinatario dialog = new DialogDestinatario(elemento, commessa);
		return dialog;
	}

	@Override
	public void aggiornaContenuto() {
		if (controller != null) {
			Indirizzo filtro = new Indirizzo();
			filtro.setRagioneSociale(criteriSelezione != null ? criteriSelezione.getTesto() : "");
			setElementi(controller.getIndirizzi(filtro));
		}		
	}
}
