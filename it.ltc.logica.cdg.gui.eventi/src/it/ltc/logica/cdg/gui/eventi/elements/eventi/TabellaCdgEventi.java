package it.ltc.logica.cdg.gui.eventi.elements.eventi;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.cdg.gui.eventi.dialogs.DialogCdgEventi;
import it.ltc.logica.common.controller.cdg.ControllerCdgEventi;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCdgEventi extends TabellaCRUDConFiltro<CdgEvento, CriteriFiltroEventi> {
	
	protected MenuItem insert;
	protected MenuItem modify;
	
	protected FiltroCdgEventi filtro;

	public TabellaCdgEventi(Composite parent) {
		super(parent, STILE_SELEZIONE_SINGOLA, false, true);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Nome", 150, 0);
		aggiungiColonna("Fase Logistica", 150, 1);
		aggiungiColonna("Categoria Merceologica", 150, 2);
		aggiungiColonna("Descrizione", 150, 3);
	}
	
	public void aggiornaContenuto() {
		setElementi(ControllerCdgEventi.getInstance().getEventi());
	}

	@Override
	protected Ordinatore<CdgEvento> creaOrdinatore() {
		return new OrdinatoreCdgEventi();
	}

	@Override
	protected FiltroTabella<CdgEvento, CriteriFiltroEventi> creaFiltro() {
		filtro = new FiltroCdgEventi();
		return filtro;
	}

	@Override
	protected boolean eliminaElemento(CdgEvento elemento) {
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
	protected DialogCdgEventi creaDialog(CdgEvento elemento) {
		DialogCdgEventi dialog = new DialogCdgEventi(elemento);
		return dialog;
	}

	@Override
	protected Etichettatore<CdgEvento> creaEtichettatore() {
		return new EtichettatoreCdgEventi();
	}

	@Override
	protected ModificatoreValoriCelle<CdgEvento> creaModificatore() {
		return null;
	}

}
