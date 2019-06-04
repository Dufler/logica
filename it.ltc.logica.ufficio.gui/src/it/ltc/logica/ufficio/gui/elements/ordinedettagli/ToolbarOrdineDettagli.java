package it.ltc.logica.ufficio.gui.elements.ordinedettagli;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.ordini.OrdineDettaglio;
import it.ltc.logica.database.model.centrale.ordini.StatiOrdine;
import it.ltc.logica.gui.elements.ToolbarCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarOrdineDettagli extends ToolbarCRUD<TabellaOrdineDettagli, OrdineDettaglio> {

	public ToolbarOrdineDettagli(Composite parent) {
		super(parent);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_INGRESSI.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();
	}
	
	public void abilitaTastiCRUDPerStato(StatiOrdine stato) {
		boolean permesso = isAbilitato();
		boolean statoNuovo = false;
		boolean statoModifica = false;
		boolean statoElimina = false;
		switch (stato) {
			case INSE : case ERRO : statoNuovo = true; statoModifica = true; statoElimina = true; break;
			default : statoNuovo = false; statoModifica = false; statoElimina = false; break;
		}
		nuovo.setEnabled(permesso && statoNuovo);
		modifica.setEnabled(permesso && statoModifica);
		elimina.setEnabled(permesso && statoElimina);
	}
	
}
