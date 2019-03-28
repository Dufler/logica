package it.ltc.logica.common.controller.cdg;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.cdg.CdgBudgetPercentualiCosto;

public class ControllerCdgBudgetPercentuali extends ControllerCRUD<CdgBudgetPercentualiCosto> {
	
	private static final String title = "Controllo di Gestione: Percentuale Costo per Budget";
	private static final String resource = "cdg/budgetpercentuali";
	
	private static ControllerCdgBudgetPercentuali instance;
	
	private final Set<CdgBudgetPercentualiCosto> percentuali;

	private ControllerCdgBudgetPercentuali() {
		super(title, resource, CdgBudgetPercentualiCosto[].class);
		percentuali = new HashSet<>();
		caricaDati();
	}

	public static synchronized ControllerCdgBudgetPercentuali getInstance() {
		if (null == instance) {
			instance = new ControllerCdgBudgetPercentuali();
		}
		return instance;
	}
	
	public Collection<CdgBudgetPercentualiCosto> getPercentuali() {
		return percentuali;
	}
	
	public List<CdgBudgetPercentualiCosto> getPercentualiPerBudget(int idBudget) {
		List<CdgBudgetPercentualiCosto> lista = new LinkedList<>();
		for (CdgBudgetPercentualiCosto elemento : percentuali) {
			if (elemento.getBudget() == idBudget)
				lista.add(elemento);
		}
		return lista;
	}

	@Override
	protected void aggiornaInfoInserimento(CdgBudgetPercentualiCosto object, CdgBudgetPercentualiCosto nuovo) {
		percentuali.add(nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(CdgBudgetPercentualiCosto object) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void aggiornaInfoEliminazione(CdgBudgetPercentualiCosto object) {
		percentuali.remove(object);
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CdgBudgetPercentualiCosto> lista) {
		percentuali.clear();
		percentuali.addAll(lista);
		return true;
	}

}
