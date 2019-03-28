package it.ltc.logica.amministrazione.gui.fatturazione.dialogs;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.composite.CompositeCoordinateBancarie;
import it.ltc.logica.common.controller.fatturazione.ControllerCoordinateBancarie;
import it.ltc.logica.database.model.centrale.fatturazione.CoordinateBancarie;
import it.ltc.logica.gui.dialog.DialogModel;
import org.eclipse.swt.layout.GridData;

public class DialogCoordinateBancarie extends DialogModel<CoordinateBancarie> {
	
	private final static String title = "Coordinate Bancarie";
	
	private final ControllerCoordinateBancarie controller;
	
	private CompositeCoordinateBancarie composite;
	
	public DialogCoordinateBancarie(CoordinateBancarie value) {
		super(title, value);
		controller = ControllerCoordinateBancarie.getInstance();
	}

	@Override
	public void loadModel() {
		composite.setNome(valore.getNome());
		composite.setEnte(valore.getEnte());
		composite.setCoordinate(valore.getCoordinate());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!
	}

	@Override
	public void copyDataToModel() {
		valore.setNome(composite.getNome());
		valore.setEnte(composite.getEnte());
		valore.setCoordinate(composite.getCoordinate());
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
	public CoordinateBancarie createNewModel() {
		CoordinateBancarie entity = new CoordinateBancarie();
		return entity;
	}

	@Override
	public boolean isDirty() {
		return composite.isDirty();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeCoordinateBancarie(this, container);		
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

}
