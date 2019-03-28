package it.ltc.logica.common.controller.cdg;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.cdg.CdgBudget;

public class ControllerCdgBudget extends ControllerCRUD<CdgBudget> {
	
	private static final String title = "Controllo di Gestione: Budget";
	private static final String resource = "cdg/budget";
	
	private static ControllerCdgBudget instance;
	
	private final HashMap<Integer, CdgBudget> budgets;

	private ControllerCdgBudget() {
		super(title, resource, CdgBudget[].class);
		budgets = new HashMap<>();
		caricaDati();
	}

	public static synchronized ControllerCdgBudget getInstance() {
		if (null == instance) {
			instance = new ControllerCdgBudget();
		}
		return instance;
	}
	
	public Collection<CdgBudget> getBudgets() {
		return budgets.values();
	}
	
	public CdgBudget getBudgetPerCommessa(int commessa, Date giorno) {
		CdgBudget budgetTrovato = null;
		for (CdgBudget budget : budgets.values()) {
			if (budget.getCommessa() == commessa && budget.getDataInizio().getTime() <= giorno.getTime() && budget.getDataFine().getTime() >= giorno.getTime()) {
				budgetTrovato = budget;
				break;
			}
		}
		return budgetTrovato;
	}

	@Override
	protected void aggiornaInfoInserimento(CdgBudget object, CdgBudget nuovo) {
		budgets.put(nuovo.getId(), nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(CdgBudget object) {
		// TODO - Refresh	
	}

	@Override
	protected void aggiornaInfoEliminazione(CdgBudget budget) {
		budgets.remove(budget.getId());	
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CdgBudget> lista) {
		for (CdgBudget budget : lista) {
			budgets.put(budget.getId(), budget);
		}
		return true;
	}

}
