package it.ltc.logica.cdg.gui.costiricavi.elements.budgetpercentuale;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.costiricavi.dialogs.DialogBudgetPercentuali;
import it.ltc.logica.common.controller.cdg.ControllerCdgBudgetPercentuali;
import it.ltc.logica.database.model.centrale.cdg.CdgBudget;
import it.ltc.logica.database.model.centrale.cdg.CdgBudgetPercentualiCosto;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaBudgetPercentuale extends TabellaCRUD<CdgBudgetPercentualiCosto> {

	private final CdgBudget budget;
	
	public TabellaBudgetPercentuale(Composite parent, CdgBudget budget) {
		super(parent);
		this.budget = budget;
		
		aggiornaContenuto();
	}

	@Override
	protected boolean eliminaElemento(CdgBudgetPercentualiCosto elemento) {
		boolean delete = ControllerCdgBudgetPercentuali.getInstance().elimina(elemento);
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
	protected DialogApribile creaDialog(CdgBudgetPercentualiCosto elemento) {
		DialogBudgetPercentuali dialog = new DialogBudgetPercentuali(elemento, budget);
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Costo", 100, 0);
		aggiungiColonna("Percentuale", 100, 1);
	}

	@Override
	public void aggiornaContenuto() {
		if (budget != null)
			setElementi(ControllerCdgBudgetPercentuali.getInstance().getPercentualiPerBudget(budget.getId()));
	}

	@Override
	protected Ordinatore<CdgBudgetPercentualiCosto> creaOrdinatore() {
		return new OrdinatoreBudgetPercentuale();
	}

	@Override
	protected Etichettatore<CdgBudgetPercentualiCosto> creaEtichettatore() {
		return new EtichettatoreBudgetPercentuale();
	}

	@Override
	protected ModificatoreValoriCelle<CdgBudgetPercentualiCosto> creaModificatore() {
		return null;
	}

}
