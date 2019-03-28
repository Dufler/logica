package it.ltc.logica.cdg.gui.costiricavi.elements.generici;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.costiricavi.dialogs.DialogCostiRicaviGenerici;
import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviGenerici;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCostiRicaviGenerici extends TabellaCRUDConFiltro<CdgCostiRicaviGenerici, CriteriFiltraggioCostiRicaviGenerici> {

	public TabellaCostiRicaviGenerici(Composite parent) {
		super(parent);
	}

	@Override
	protected FiltroTabella<CdgCostiRicaviGenerici, CriteriFiltraggioCostiRicaviGenerici> creaFiltro() {
		return new FiltroCostiRicaviGenerici();
	}

	@Override
	protected boolean eliminaElemento(CdgCostiRicaviGenerici elemento) {
		boolean delete = ControllerCdgCostiRicaviGenerici.getInstance().elimina(elemento);
		return delete;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG_COSTI_RICAVI_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(CdgCostiRicaviGenerici elemento) {
		DialogCostiRicaviGenerici dialog = new DialogCostiRicaviGenerici(elemento);
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Nome", 100, 0);
		//aggiungiColonna("Sede", 100, new EtichettatoreCostiRicaviGenerici(), 1);
		aggiungiColonna("Driver", 100, 2);
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerCdgCostiRicaviGenerici.getInstance().getCostiRicavi());		
	}

	@Override
	protected Ordinatore<CdgCostiRicaviGenerici> creaOrdinatore() {
		return new OrdinatoreCostiRicaviGenerici();
	}

	@Override
	protected Etichettatore<CdgCostiRicaviGenerici> creaEtichettatore() {
		return new EtichettatoreCostiRicaviGenerici();
	}

	@Override
	protected ModificatoreValoriCelle<CdgCostiRicaviGenerici> creaModificatore() {
		return null;
	}

}
