package it.ltc.logica.cdg.gui.costiricavi.dialogs;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.composite.CompositeCdgBudgetPercentuale;
import it.ltc.logica.common.controller.cdg.ControllerCdgBudgetPercentuali;
import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviGenerici;
import it.ltc.logica.database.model.centrale.cdg.CdgBudget;
import it.ltc.logica.database.model.centrale.cdg.CdgBudgetPercentualiCosto;
import it.ltc.logica.gui.dialog.DialogModel;

public class DialogBudgetPercentuali extends DialogModel<CdgBudgetPercentualiCosto> {
	
	private final static String title = "Controllo di Gestione: Percentuale di Costo per Budget";

	private CompositeCdgBudgetPercentuale composite;
	
	private final CdgBudget budget;
	
	public DialogBudgetPercentuali(CdgBudgetPercentualiCosto value, CdgBudget budget) {
		super(title, value);
		this.budget = budget;
		
		minimumHeight = 100;
		minimumWidth = 150;
	}

	@Override
	public void loadModel() {
		composite.setCosto(ControllerCdgCostiRicaviGenerici.getInstance().getCostoRicavoGenericoDaID(valore.getCostoGenerico()));
		composite.setPercentuale(valore.getPercentuale());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

	@Override
	public void copyDataToModel() {
		valore.setBudget(budget.getId());
		valore.setCostoGenerico(composite.getCosto().getId());
		valore.setPercentuale(composite.getPercentuale());
	}

	@Override
	public List<String> validateModel() {
		List<String> errors = new LinkedList<>();
		if (!modify) {
			boolean esistente = false;
			List<CdgBudgetPercentualiCosto> percentualiEsistenti = ControllerCdgBudgetPercentuali.getInstance().getPercentualiPerBudget(budget.getId());
			for (CdgBudgetPercentualiCosto percentuale : percentualiEsistenti) {
				if (percentuale.getCostoGenerico() == composite.getCosto().getId()) {
					esistente = true;
					break;
				}
			}
			if (esistente) {
				errors.add("Il costo generico \u00E8 gi\u00E0 stato inserito.");
			}
		}
		return errors;
	}

	@Override
	public boolean updateModel() {
		boolean update = ControllerCdgBudgetPercentuali.getInstance().aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = ControllerCdgBudgetPercentuali.getInstance().inserisci(valore);
		return insert;
	}

	@Override
	public CdgBudgetPercentualiCosto createNewModel() {
		CdgBudgetPercentualiCosto model = new CdgBudgetPercentualiCosto();
		return model;
	}

	@Override
	public boolean isDirty() {
		boolean dirty = composite.isDirty();
		return dirty;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeCdgBudgetPercentuale(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

}
