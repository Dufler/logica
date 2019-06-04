package it.ltc.logica.amministrazione.gui.commesse.dialogs;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.prodotti.ControllerBrand;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Brand;
import it.ltc.logica.gui.dialog.DialogModel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;

public class DialogBrand extends DialogModel<Brand> {

	public static final String title = "Brand";
	
	private CompositeBrand composite;
	
	private final ControllerBrand controller;
	
	public DialogBrand(Commessa commessa, Brand value) {
		super(title, value);
		controller = ControllerBrand.getInstance(commessa);		
		minimumHeight = 180;
	}

	@Override
	public void loadModel() {
		composite.setCodice(valore.getCodice());
		composite.setDescrizione(valore.getDescrizione());
	}

	@Override
	public void prefillModel() {}

	@Override
	public void copyDataToModel() {
		valore.setCodice(composite.getCodice());
		valore.setDescrizione(composite.getDescrizione());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = controller.aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = controller.inserisci(valore);
		return insert;
	}

	@Override
	public Brand createNewModel() {
		Brand brand = new Brand();
		return brand;
	}

	@Override
	public boolean isDirty() {
		return composite.isDirty();
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeBrand(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
	}

}
