package it.ltc.logica.cdg.gui.associazioni.dialogs;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.composite.CompositeCdgAssociazioni;
import it.ltc.logica.common.controller.cdg.ControllerCdgAssociazioni;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.cdg.CdgCostoAssociazione;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;

public class DialogCdgAssociazioni extends DialogModel<CdgCostoAssociazione> {
	
	private static final String title = "Controllo di Gestione: Associazioni Operatori";

	private final ControllerCdgAssociazioni controllerAssociazioni;
	private final ControllerSedi controllerSedi;
	
	private CompositeCdgAssociazioni compositeAssociazioni;
	
	private final boolean permesso;
	
	public DialogCdgAssociazioni(CdgCostoAssociazione value) {
		super(title, value);
		
		minimumHeight = 150;
		minimumWidth = 350;
		
		permesso = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.CDG_COSTO_PERSONALE.getID());
		
		controllerAssociazioni = ControllerCdgAssociazioni.getInstance();
		controllerSedi = ControllerSedi.getInstance();
	}

	@Override
	public void loadModel() {
		compositeAssociazioni.setNome(valore.getNome());
		compositeAssociazioni.setSede(controllerSedi.getSede(valore.getSede()));
		compositeAssociazioni.setCosto(valore.getCosto());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

	@Override
	public void copyDataToModel() {
		valore.setNome(compositeAssociazioni.getNome());
		valore.setSede(compositeAssociazioni.getSede().getId());
		valore.setCosto(compositeAssociazioni.getCosto());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = controllerAssociazioni.aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = controllerAssociazioni.inserisci(valore);
		return insert;
	}

	@Override
	public CdgCostoAssociazione createNewModel() {
		CdgCostoAssociazione associazione = new CdgCostoAssociazione();
		return associazione;
	}

	@Override
	public boolean isDirty() {
		return compositeAssociazioni.isDirty();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		compositeAssociazioni = new CompositeCdgAssociazioni(this, container);
		compositeAssociazioni.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeAssociazioni.enableElement(permesso);
	}

}
