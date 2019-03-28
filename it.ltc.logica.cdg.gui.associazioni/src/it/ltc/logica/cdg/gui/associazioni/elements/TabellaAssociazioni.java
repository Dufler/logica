package it.ltc.logica.cdg.gui.associazioni.elements;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.associazioni.dialogs.DialogCdgAssociazioni;
import it.ltc.logica.common.controller.cdg.ControllerCdgAssociazioni;
import it.ltc.logica.database.model.centrale.cdg.CdgCostoAssociazione;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaAssociazioni extends TabellaCRUDConFiltro<CdgCostoAssociazione, CriteriFiltraggioSoloTesto> {
	
	protected FiltroAssociazioni filtro;

	public TabellaAssociazioni(Composite parent) {
		super(parent, STILE_SELEZIONE_SINGOLA, false, true);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Nome", 150, 0);
		aggiungiColonna("Sede", 100, 1);
		aggiungiColonna("Costo", 100, 2);
	}
	
	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerCdgAssociazioni.getInstance().getAssociazioni());
	}

	@Override
	protected Ordinatore<CdgCostoAssociazione> creaOrdinatore() {
		return new OrdinatoreAssociazioni();
	}

	@Override
	protected FiltroTabella<CdgCostoAssociazione, CriteriFiltraggioSoloTesto> creaFiltro() {
		filtro = new FiltroAssociazioni();
		return filtro;
	}

	@Override
	protected boolean eliminaElemento(CdgCostoAssociazione elemento) {
		return false;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG_COSTO_PERSONALE_CUD.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.CDG_COSTO_PERSONALE_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(CdgCostoAssociazione elemento) {
		DialogCdgAssociazioni dialog = new DialogCdgAssociazioni(elemento);
		return dialog;
	}

	@Override
	protected Etichettatore<CdgCostoAssociazione> creaEtichettatore() {
		return new EtichettatoreAssociazioni();
	}

	@Override
	protected ModificatoreValoriCelle<CdgCostoAssociazione> creaModificatore() {
		return null;
	}

}
