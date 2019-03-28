package it.ltc.logica.cdg.gui.costiricavi.elements.budget;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.costiricavi.dialogs.DialogBudget;
import it.ltc.logica.common.controller.cdg.ControllerCdgBudget;
import it.ltc.logica.database.model.centrale.cdg.CdgBudget;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaBudget extends TabellaCRUDConFiltro<CdgBudget, CriteriFiltraggioBudget> {

	public TabellaBudget(Composite parent) {
		super(parent);
	}

	@Override
	protected FiltroTabella<CdgBudget, CriteriFiltraggioBudget> creaFiltro() {
		return new FiltroBudget();
	}

	@Override
	protected boolean eliminaElemento(CdgBudget elemento) {
		boolean delete = ControllerCdgBudget.getInstance().elimina(elemento);
		return delete;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG_COSTI_RICAVI_CUD.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.CDG_COSTI_RICAVI_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(CdgBudget elemento) {
		DialogBudget dialog = new DialogBudget(elemento);
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Commessa", 100, 0);
		aggiungiColonna("Inizio", 100, 1);
		aggiungiColonna("Fine", 100, 2);
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerCdgBudget.getInstance().getBudgets());
	}

	@Override
	protected Ordinatore<CdgBudget> creaOrdinatore() {
		return new OrdinatoreBudget();
	}

	@Override
	protected Etichettatore<CdgBudget> creaEtichettatore() {
		return new EtichettatoreBudget();
	}

	@Override
	protected ModificatoreValoriCelle<CdgBudget> creaModificatore() {
		return null;
	}

}
