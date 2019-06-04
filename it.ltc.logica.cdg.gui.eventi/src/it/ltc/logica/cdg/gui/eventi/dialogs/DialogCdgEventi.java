package it.ltc.logica.cdg.gui.eventi.dialogs;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.composite.CompositeCdgEvento;
import it.ltc.logica.common.controller.cdg.ControllerCdgEventi;
import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.common.controller.sistema.ControllerCategorieMerceologiche;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.gui.dialog.DialogModel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;

public class DialogCdgEventi extends DialogModel<CdgEvento> {

	private static final String title = "Controllo di Gestione: Eventi";
	
	private CompositeCdgEvento compositeEvento;
	
	private final ControllerCdgEventi controllerEventi;
	private final ControllerCdgFasi controllerFasi;
	private final ControllerCategorieMerceologiche controllerCategorie;
	
	public DialogCdgEventi(CdgEvento value) {
		super(title, value);
		
		minimumHeight = 300;
		
		controllerEventi = ControllerCdgEventi.getInstance();
		controllerFasi = ControllerCdgFasi.getInstance();
		controllerCategorie = ControllerCategorieMerceologiche.getInstance();
	}

	@Override
	public void loadModel() {
		compositeEvento.setNome(valore.getNome());
		compositeEvento.setDescrizione(valore.getDescrizione());
		compositeEvento.setFase(controllerFasi.getFase(valore.getFase()));
		compositeEvento.setCategoria(controllerCategorie.getCategoria(valore.getCategoriaMerceologica(), 0)); //FIXME
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

	@Override
	public void copyDataToModel() {
		valore.setNome(compositeEvento.getNome());
		valore.setDescrizione(compositeEvento.getDescrizione());
		valore.setFase(compositeEvento.getFase().getId());
		valore.setCategoriaMerceologica(compositeEvento.getCategoria().getNome());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = controllerEventi.aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = controllerEventi.inserisci(valore);
		return insert;
	}

	@Override
	public CdgEvento createNewModel() {
		CdgEvento evento = new CdgEvento();
		return evento;
	}

	@Override
	public boolean isDirty() {
		return compositeEvento.isDirty();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		compositeEvento = new CompositeCdgEvento(this, container);
		compositeEvento.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

}
