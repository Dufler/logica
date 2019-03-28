package it.ltc.logica.cdg.gui.costiricavi.elements;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.cdg.gui.costiricavi.dialogs.DialogPezziCostiRicavi;
import it.ltc.logica.common.controller.cdg.ControllerCdgPezzi;
import it.ltc.logica.database.model.centrale.cdg.CdgPezzo;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaPezziCostiRicavi extends TabellaCRUDConFiltro<CdgPezzo, CriteriFiltroPezziCostiRicavi> {
	
	protected MenuItem insert;
	protected MenuItem modify;
	
	private FiltroPezziCostiRicavi filtro;

	public TabellaPezziCostiRicavi(Composite parent) {
		super(parent, STILE_SELEZIONE_SINGOLA, false, true);		
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Commessa", 150, 0);
		aggiungiColonna("Categoria", 150, 1);
		aggiungiColonna("Costo", 100, 2);
		aggiungiColonna("Ricavo", 100, 3);
	}
	
	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerCdgPezzi.getInstance().getPezzi());
	}

	@Override
	protected Ordinatore<CdgPezzo> creaOrdinatore() {
		return new OrdinatorePezziCostiRicavi();
	}

	@Override
	protected FiltroTabella<CdgPezzo, CriteriFiltroPezziCostiRicavi> creaFiltro() {
		filtro = new FiltroPezziCostiRicavi();
		return filtro;
	}

	@Override
	protected boolean eliminaElemento(CdgPezzo elemento) {
		return false;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG_COSTI_RICAVI_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(CdgPezzo elemento) {
		DialogPezziCostiRicavi dialog = new DialogPezziCostiRicavi(elemento);
		return dialog;
	}

	@Override
	protected Etichettatore<CdgPezzo> creaEtichettatore() {
		return new EtichettatorePezziCostiRicavi();
	}

	@Override
	protected ModificatoreValoriCelle<CdgPezzo> creaModificatore() {
		return null;
	}

}
