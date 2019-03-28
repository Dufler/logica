package it.ltc.logica.cdg.gui.costiricavi.elements.commessa;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.costiricavi.dialogs.DialogCostiRicaviCommessa;
import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviCommesse;
import it.ltc.logica.database.model.centrale.cdg.CdgCostoRicavoCommessa;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCostiRicaviCommesse extends TabellaCRUDConFiltro<CdgCostoRicavoCommessa, CriteriFiltraggioCostiRicaviCommesse> {

	public TabellaCostiRicaviCommesse(Composite parent) {
		super(parent);
	}

	@Override
	protected FiltroTabella<CdgCostoRicavoCommessa, CriteriFiltraggioCostiRicaviCommesse> creaFiltro() {
		return new FiltroCostiRicaviCommesse();
	}

	@Override
	protected boolean eliminaElemento(CdgCostoRicavoCommessa elemento) {
		boolean elimina = ControllerCdgCostiRicaviCommesse.getInstance().elimina(elemento);
		return elimina;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG_COSTI_RICAVI_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(CdgCostoRicavoCommessa elemento) {
		DialogCostiRicaviCommessa dialog = new DialogCostiRicaviCommessa(elemento);
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Commessa", 100, 0);
		aggiungiColonna("Tipo", 100, 1);
		aggiungiColonna("Valore", 100, 2);
		aggiungiColonna("Data", 100, 3);
		aggiungiColonna("Descrizione", 100, 4);
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerCdgCostiRicaviCommesse.getInstance().getCostiRicavi());
	}

	@Override
	protected Ordinatore<CdgCostoRicavoCommessa> creaOrdinatore() {
		return new OrdinatoreCostiRicaviCommesse();
	}

	@Override
	protected Etichettatore<CdgCostoRicavoCommessa> creaEtichettatore() {
		return new EtichettatoreCostiRicaviCommesse();
	}

	@Override
	protected ModificatoreValoriCelle<CdgCostoRicavoCommessa> creaModificatore() {
		return null;
	}

}
