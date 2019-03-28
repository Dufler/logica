package it.ltc.logica.cdg.gui.costiricavi.dialogs;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.composite.CompositeCdgCostiRicaviCommessa;
import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviCommesse;
import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.cdg.CdgCostoRicavoCommessa;
import it.ltc.logica.gui.dialog.DialogModel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;

public class DialogCostiRicaviCommessa extends DialogModel<CdgCostoRicavoCommessa> {
	
	private final static String title = "Controllo di Gestione: Costi e Ricavi per Commessa";
	
	private CompositeCdgCostiRicaviCommessa composite;

	public DialogCostiRicaviCommessa(CdgCostoRicavoCommessa value) {
		super(title, value);
		
		minimumHeight = 400;
	}

	@Override
	public void loadModel() {
		composite.setCommessa(ControllerCommesse.getInstance().getCommessa(valore.getCommessa()));
		composite.setTipo(valore.getTipo());
		composite.setFase(ControllerCdgFasi.getInstance().getFase(valore.getFase()));
		composite.setDataEmissione(valore.getDataEmissione());
		composite.setValore(valore.getValore());
		composite.setDescrizione(valore.getDescrizione());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!
	}

	@Override
	public void copyDataToModel() {
		valore.setCommessa(composite.getCommessa().getId());
		valore.setTipo(composite.getTipo());
		valore.setFase(composite.getFase().getId());
		valore.setDataEmissione(composite.getDataEmissione());
		valore.setValore(composite.getValore());
		valore.setDescrizione(composite.getDescrizione());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = ControllerCdgCostiRicaviCommesse.getInstance().aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = ControllerCdgCostiRicaviCommesse.getInstance().inserisci(valore);
		return insert;
	}

	@Override
	public CdgCostoRicavoCommessa createNewModel() {
		CdgCostoRicavoCommessa model = new CdgCostoRicavoCommessa();
		return model;
	}

	@Override
	public boolean isDirty() {
		boolean dirty = composite.isDirty();
		return dirty;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeCdgCostiRicaviCommessa(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
	}

}
