package it.ltc.logica.cdg.gui.eventi.dialogs;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.composite.CompositeCdgCommessaEvento;
import it.ltc.logica.common.controller.cdg.ControllerCdgCommessaEventi;
import it.ltc.logica.common.controller.cdg.ControllerCdgEventi;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.cdg.CdgCommessaEvento;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.gui.dialog.DialogModel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;

public class DialogCdgCommessaEvento extends DialogModel<CdgCommessaEvento> {
	
	private final static String title = "Controllo di Gestione: Durata";
	
	private CompositeCdgCommessaEvento compositeCommessaEvento;
	
	private final ControllerCdgCommessaEventi controllerAbbinamenti;
	private final ControllerCdgEventi controllerEventi;
	private final ControllerCommesse controllerCommesse;

	public DialogCdgCommessaEvento(CdgCommessaEvento value) {
		super(title, value);
		
		minimumHeight = 150;
		minimumWidth = 200;
		
		controllerAbbinamenti = ControllerCdgCommessaEventi.getInstance();
		controllerEventi = ControllerCdgEventi.getInstance();
		controllerCommesse = ControllerCommesse.getInstance();
	}

	@Override
	public void loadModel() {
		compositeCommessaEvento.setCommessa(controllerCommesse.getCommessa(valore.getCommessa()));
		compositeCommessaEvento.setEvento(controllerEventi.getEvento(valore.getEvento()));
		compositeCommessaEvento.setDurata(valore.getDurata());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

	@Override
	public void copyDataToModel() {
		valore.setCommessa(compositeCommessaEvento.getCommessa().getId());
		valore.setEvento(compositeCommessaEvento.getEvento().getId());
		valore.setDurata(compositeCommessaEvento.getDurata());
	}

	@Override
	public List<String> validateModel() {
		List<String> errors = new LinkedList<>();
		CdgCommessaEvento abbinamento = controllerAbbinamenti.getEvento(valore.getCommessa(), valore.getEvento());
		if (!modify && abbinamento != null) {
			Commessa commessa = controllerCommesse.getCommessa(valore.getCommessa());
			String nomeCommessa = commessa != null ? commessa.getNome() : "???";
			CdgEvento evento = controllerEventi.getEvento(valore.getEvento());
			String nomeEvento = evento != null ? evento.getNome() : "???";
			String errorMessage = "Attenzione! L'abbinamento " + nomeCommessa + "-" + nomeEvento + " esiste gi\u00E0.\r\nNon verr\u00E0 effettuato alcun inserimento.";
			errors.add(errorMessage);
		}
		return errors;
	}

	@Override
	public boolean updateModel() {
		boolean update = controllerAbbinamenti.aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = controllerAbbinamenti.inserisci(valore);
		return insert;
	}

	@Override
	public CdgCommessaEvento createNewModel() {
		CdgCommessaEvento abbinamento = new CdgCommessaEvento();
		return abbinamento;
	}

	@Override
	public boolean isDirty() {
		return compositeCommessaEvento.isDirty();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		compositeCommessaEvento = new CompositeCdgCommessaEvento(this, container);
		compositeCommessaEvento.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

}
