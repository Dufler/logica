package it.ltc.logica.cdg.gui.costiricavi.dialogs;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.composite.CompositeCdgCostiRicaviGenericiDateValore;
import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviGenericiDateValore;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenericiDateValore;
import it.ltc.logica.gui.dialog.DialogModel;

public class DialogCostiRicaviGenericiDateValore extends DialogModel<CdgCostiRicaviGenericiDateValore> {
	
	private final static String title = "Controllo di Gestione: Costi e Ricavi Generici - Date e Valore";
	
	private CompositeCdgCostiRicaviGenericiDateValore composite;
	
	private final CdgCostiRicaviGenerici generico;

	public DialogCostiRicaviGenericiDateValore(CdgCostiRicaviGenericiDateValore value, CdgCostiRicaviGenerici generico) {
		super(title, value);
		
		this.generico = generico;
		
		minimumWidth = 200;
		minimumHeight = 150;
	}
	
	public void copiaValoriDaEsistente(CdgCostiRicaviGenericiDateValore copia) {
		composite.setDateEffettiva(copia.getDataEffettiva());
		composite.setDateFine(copia.getDataFine());
		composite.setDateInizio(copia.getDataInizio());
		composite.setValore(copia.getValore());
		composite.setSede(copia.getSede());
		composite.setDescrizione(copia.getDescrizione());
	}

	@Override
	public void loadModel() {
		composite.setDateEffettiva(valore.getDataEffettiva());
		composite.setDateFine(valore.getDataFine());
		composite.setDateInizio(valore.getDataInizio());
		composite.setValore(valore.getValore());
		composite.setSede(valore.getSede());
		composite.setDescrizione(valore.getDescrizione());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!
	}

	@Override
	public void copyDataToModel() {
		valore.setGenerico(generico.getId());
		valore.setDataEffettiva(composite.getDateEffettiva());
		valore.setDataFine(composite.getDateFine());
		valore.setDataInizio(composite.getDateInizio());
		valore.setValore(composite.getValore());
		valore.setSede(composite.getSede() != null ? composite.getSede().getId() : null);
		valore.setDescrizione(composite.getDescrizione());
	}

	@Override
	public List<String> validateModel() {
		List<String> errors = new LinkedList<>();
		if (composite.getDateInizio().after(composite.getDateFine()))
			errors.add("La data d'inizio deve precedere la data di fine.");
		return errors;
	}

	@Override
	public boolean updateModel() {
		boolean update = ControllerCdgCostiRicaviGenericiDateValore.getInstance().aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = ControllerCdgCostiRicaviGenericiDateValore.getInstance().inserisci(valore);
		return insert;
	}

	@Override
	public CdgCostiRicaviGenericiDateValore createNewModel() {
		CdgCostiRicaviGenericiDateValore model = new CdgCostiRicaviGenericiDateValore();
		return model;
	}

	@Override
	public boolean isDirty() {
		boolean dirty = composite.isDirty();
		return dirty;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeCdgCostiRicaviGenericiDateValore(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

}
