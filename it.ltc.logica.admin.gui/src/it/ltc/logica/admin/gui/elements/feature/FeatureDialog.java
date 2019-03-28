package it.ltc.logica.admin.gui.elements.feature;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.sistema.ControllerFeature;
import it.ltc.logica.common.controller.sistema.ControllerPermessi;
import it.ltc.logica.database.model.centrale.Feature;
import it.ltc.logica.gui.decoration.Colore;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.dialog.DialogModel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;

public class FeatureDialog extends DialogModel<Feature> {

	private static final String title = "Feature";
	
	private FeatureComposite composite;
	
	public FeatureDialog(Feature value) {
		super(title, value);
	}

	@Override
	public void loadModel() {
		composite.setNome(valore.getNome());
		composite.setVersione(valore.getVersione());
		composite.setPerspective(valore.getPerspectiveid());
		composite.setFeature(valore.getFeatureid());
		composite.setPermesso(ControllerPermessi.getInstance().getPermesso(valore.getPermessoid()));
		composite.setIcona(Immagine.valueOf(valore.getIcona()));
		composite.setColore(Colore.valueOf(valore.getColore()));
		composite.setPosizione(valore.getPosizione());
	}

	@Override
	public void prefillModel() {
		composite.setVersione("1.0");		
	}

	@Override
	public void copyDataToModel() {
		valore.setNome(composite.getNome());
		valore.setVersione(composite.getVersione());
		valore.setPerspectiveid(composite.getPerspective());
		valore.setFeatureid(composite.getFeature());
		valore.setPermessoid(composite.getPermesso().getId());
		valore.setIcona(composite.getIcona().name());
		valore.setColore(composite.getColore().name());
		valore.setPosizione(composite.getPosizione());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = ControllerFeature.getInstance().aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = ControllerFeature.getInstance().inserisci(valore);
		return insert;
	}

	@Override
	public Feature createNewModel() {
		Feature feature = new Feature();
		return feature;
	}

	@Override
	public boolean isDirty() {
		return composite.isDirty();
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		composite = new FeatureComposite(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));		
	}

}
