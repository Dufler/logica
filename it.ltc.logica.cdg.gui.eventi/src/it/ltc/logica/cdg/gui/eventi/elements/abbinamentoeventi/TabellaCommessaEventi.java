package it.ltc.logica.cdg.gui.eventi.elements.abbinamentoeventi;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.cdg.gui.eventi.dialogs.DialogCdgCommessaEvento;
import it.ltc.logica.common.controller.cdg.ControllerCdgCommessaEventi;
import it.ltc.logica.database.model.centrale.cdg.CdgCommessaEvento;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCommessaEventi extends TabellaCRUDConFiltro<CdgCommessaEvento, CriteriFiltraggioCommessaEventi> {
	
	protected MenuItem insert;
	protected MenuItem modify;
	
	protected FiltroCommessaEventi filtro;

	public TabellaCommessaEventi(Composite parent) {
		super(parent, STILE_SELEZIONE_SINGOLA, false, true);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Commessa", 150, 0);
		aggiungiColonna("Evento", 200, 1);
		aggiungiColonna("Durata", 100, 2);
	}
	
	public void aggiornaContenuto() {
		setElementi(ControllerCdgCommessaEventi.getInstance().getEventi());
	}

	@Override
	protected Ordinatore<CdgCommessaEvento> creaOrdinatore() {
		return new OrdinatoreCommessaEventi();
	}

	@Override
	protected FiltroTabella<CdgCommessaEvento, CriteriFiltraggioCommessaEventi> creaFiltro() {
		filtro = new FiltroCommessaEventi();
		return filtro;
	}

	@Override
	protected boolean eliminaElemento(CdgCommessaEvento elemento) {
		return false;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG_EVENTI_CUD.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.CDG_EVENTI_CUD.getID();
	}

	@Override
	protected DialogCdgCommessaEvento creaDialog(CdgCommessaEvento elemento) {
		DialogCdgCommessaEvento dialog = new DialogCdgCommessaEvento(elemento);
		return dialog;
	}

	@Override
	protected Etichettatore<CdgCommessaEvento> creaEtichettatore() {
		return new EtichettatoreCommessaEventi();
	}

	@Override
	protected ModificatoreValoriCelle<CdgCommessaEvento> creaModificatore() {
		return null;
	}

}
