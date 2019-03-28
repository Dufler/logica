package it.ltc.logica.admin.gui.elements.funzione;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.sistema.ControllerFeature;
import it.ltc.logica.common.controller.sistema.ControllerFunzioni;
import it.ltc.logica.common.controller.sistema.ControllerPermessi;
import it.ltc.logica.database.model.centrale.Funzione;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.dialog.DialogModel;

public class FunzioneDialog extends DialogModel<Funzione> {
	
	private static final String title = "Funzione";
	
	private FunzioneComposite composite;

	public FunzioneDialog(Funzione value) {
		super(title, value);
	}

	@Override
	public void loadModel() {
		composite.setNome(valore.getNome());
		composite.setPart(valore.getPartid());
		composite.setFeature(ControllerFeature.getInstance().getFeature(valore.getFeature()));
		composite.setIcona(Immagine.valueOf(valore.getIcona()));
		composite.setPermesso(ControllerPermessi.getInstance().getPermesso(valore.getPermessoid()));
	}

	@Override
	public void prefillModel() {
		//Qui potrei mettere la feature già impostata in base alla selezione fatta sulla tabella delle features.		
	}

	@Override
	public void copyDataToModel() {
		valore.setFeature(composite.getFeature().getFeatureid());
		valore.setIcona(composite.getIcona().name());
		valore.setNome(composite.getNome());
		valore.setPartid(composite.getPart());
		valore.setPermessoid(composite.getPermesso().getId());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = ControllerFunzioni.getInstance().aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = ControllerFunzioni.getInstance().inserisci(valore);
		return insert;
	}

	@Override
	public Funzione createNewModel() {
		Funzione funzione = new Funzione();
		return funzione;
	}

	@Override
	public boolean isDirty() {
		return composite.isDirty();
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		composite = new FunzioneComposite(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

}
