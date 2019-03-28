package it.ltc.logica.cdg.gui.costiricavi.dialogs;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.composite.CompositeCdgCostiRicaviGenericiFase;
import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviGenericiFasi;
import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenericiFase;
import it.ltc.logica.gui.dialog.DialogModel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;

public class DialogCostiRicaviGenericiFase extends DialogModel<CdgCostiRicaviGenericiFase> {
	
	private final static String title = "Controllo di Gestione: Costi e Ricavi Generici - Percentuale per Fase";
	
	private CompositeCdgCostiRicaviGenericiFase composite;
	
	private final CdgCostiRicaviGenerici generico;

	public DialogCostiRicaviGenericiFase(CdgCostiRicaviGenericiFase value, CdgCostiRicaviGenerici generico) {
		super(title, value);
		this.generico = generico;
		
		minimumHeight = 100;
		minimumWidth = 150;
	}

	@Override
	public void loadModel() {
		composite.setFase(ControllerCdgFasi.getInstance().getFase(valore.getFase()));
		composite.setPercentuale(valore.getPercentuale());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

	@Override
	public void copyDataToModel() {
		valore.setGenerico(generico.getId());
		valore.setFase(composite.getFase().getId());
		valore.setPercentuale(composite.getPercentuale());
	}

	@Override
	public List<String> validateModel() {
		List<String> errors = new LinkedList<>();
		boolean esistente = false;
		List<CdgCostiRicaviGenericiFase> fasiEsistenti = ControllerCdgCostiRicaviGenericiFasi.getInstance().getPercentualiPerGenerico(generico.getId());
		for (CdgCostiRicaviGenericiFase fase : fasiEsistenti) {
			if (fase.getFase() == composite.getFase().getId()) {
				esistente = true;
				break;
			}
		}
		if (esistente) {
			errors.add("La fase \u00E8 gi\u00E0 stata inserita.");
		}
		return errors;
	}

	@Override
	public boolean updateModel() {
		boolean update = ControllerCdgCostiRicaviGenericiFasi.getInstance().aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = ControllerCdgCostiRicaviGenericiFasi.getInstance().inserisci(valore);
		return insert;
	}

	@Override
	public CdgCostiRicaviGenericiFase createNewModel() {
		CdgCostiRicaviGenericiFase model = new CdgCostiRicaviGenericiFase();
		return model;
	}

	@Override
	public boolean isDirty() {
		boolean dirty = composite.isDirty();
		return dirty;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeCdgCostiRicaviGenericiFase(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));		
	}

}
