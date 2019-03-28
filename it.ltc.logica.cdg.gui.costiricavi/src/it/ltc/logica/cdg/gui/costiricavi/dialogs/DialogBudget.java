package it.ltc.logica.cdg.gui.costiricavi.dialogs;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.cdg.gui.composite.CompositeCdgBudget;
import it.ltc.logica.cdg.gui.costiricavi.elements.budgetpercentuale.TabellaBudgetPercentuale;
import it.ltc.logica.cdg.gui.costiricavi.elements.budgetpercentuale.ToolbarBudgetPercentuale;
import it.ltc.logica.common.controller.cdg.ControllerCdgBudget;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.cdg.CdgBudget;
import it.ltc.logica.gui.dialog.DialogModel;

public class DialogBudget extends DialogModel<CdgBudget> {
	
	private final static String title = "Controllo di Gestione: Budget";
	
	private CompositeCdgBudget composite;

	public DialogBudget(CdgBudget value) {
		super(title, value);
		
		minimumHeight = modify ? 550 : 180;
		minimumWidth = 300;
	}

	@Override
	public void loadModel() {
		composite.setCommessa(ControllerCommesse.getInstance().getCommessa(valore.getCommessa()));
		composite.setCosto(valore.getCosto());
		composite.setRicavo(valore.getRicavo());
		composite.setFine(valore.getDataFine());
		composite.setDateInizio(valore.getDataInizio());
		composite.setDescrizione(valore.getDescrizione());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!
	}

	@Override
	public void copyDataToModel() {
		valore.setCommessa(composite.getCommessa().getId());
		valore.setCosto(composite.getCosto());
		valore.setRicavo(composite.getRicavo());
		valore.setDataFine(composite.getFine());
		valore.setDataInizio(composite.getInizio());
		valore.setDescrizione(composite.getDescrizione());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = ControllerCdgBudget.getInstance().aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = ControllerCdgBudget.getInstance().inserisci(valore);
		return insert;
	}

	@Override
	public CdgBudget createNewModel() {
		CdgBudget model = new CdgBudget();
		return model;
	}

	@Override
	public boolean isDirty() {
		boolean dirty = composite.isDirty();
		return dirty;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeCdgBudget(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		// Mostro le tabelle solo se sto andando in modifica.
		if (modify) {
			ToolbarBudgetPercentuale toolbar = new ToolbarBudgetPercentuale(container);
			
			TabellaBudgetPercentuale tabella = new TabellaBudgetPercentuale(container, valore);
			Table table = tabella.getTable();
			table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			
			toolbar.setTabella(tabella);
		}
		
	}

}
