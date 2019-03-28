package it.ltc.logica.cdg.gui.costiricavi.dialogs;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.composite.CompositeCdgCostiRicaviGenerici;
import it.ltc.logica.cdg.gui.costiricavi.elements.generici.fasi.TabellaCostiRicaviGenericiPerFase;
import it.ltc.logica.cdg.gui.costiricavi.elements.generici.fasi.ToolbarCostiRicaviGenericiPerFase;
import it.ltc.logica.cdg.gui.costiricavi.elements.generici.singoli.TabellaCostiRicaviGenericiSingoli;
import it.ltc.logica.cdg.gui.costiricavi.elements.generici.singoli.ToolbarCostiRicaviGenericiSingoli;
import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviGenerici;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici;
import it.ltc.logica.gui.dialog.DialogModel;

public class DialogCostiRicaviGenerici extends DialogModel<CdgCostiRicaviGenerici> {
	
	private final static String title = "Controllo di Gestione: Costi e Ricavi Generici";
	
	private CompositeCdgCostiRicaviGenerici composite;

	public DialogCostiRicaviGenerici(CdgCostiRicaviGenerici value) {
		super(title, value);
		
		minimumHeight = modify ? 550 : 180;
		minimumWidth = 300;
	}

	@Override
	public void loadModel() {
		composite.setDriver(valore.getDriver());
		composite.setNome(valore.getNome());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

	@Override
	public void copyDataToModel() {
		valore.setDriver(composite.getDriver());
		valore.setNome(composite.getNome());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = ControllerCdgCostiRicaviGenerici.getInstance().aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = ControllerCdgCostiRicaviGenerici.getInstance().inserisci(valore);
		return insert;
	}

	@Override
	public CdgCostiRicaviGenerici createNewModel() {
		CdgCostiRicaviGenerici model = new CdgCostiRicaviGenerici();
		return model;
	}

	@Override
	public boolean isDirty() {
		boolean dirty = composite.isDirty();
		return dirty;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeCdgCostiRicaviGenerici(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		// Mostro le tabelle solo se sto andando in modifica.
		if (modify) {
			ToolbarCostiRicaviGenericiPerFase toolbarFasi = new ToolbarCostiRicaviGenericiPerFase(container);
			
			TabellaCostiRicaviGenericiPerFase tabellaFasi = new TabellaCostiRicaviGenericiPerFase(container, valore);
			
			toolbarFasi.setTabella(tabellaFasi);
			
			ToolbarCostiRicaviGenericiSingoli toolbar = new ToolbarCostiRicaviGenericiSingoli(container);
			
			TabellaCostiRicaviGenericiSingoli tabella = new TabellaCostiRicaviGenericiSingoli(container, valore, !modify);
			
			tabella.aggiornaContenuto();
			
			toolbar.setTabella(tabella);
		}
		
	}

}
