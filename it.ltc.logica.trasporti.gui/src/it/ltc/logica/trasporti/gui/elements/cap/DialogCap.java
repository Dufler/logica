package it.ltc.logica.trasporti.gui.elements.cap;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.trasporti.ControllerCap;
import it.ltc.logica.common.controller.trasporti.ControllerProvince;
import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.trasporti.gui.composite.CompositeCap;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class DialogCap extends DialogModel<Cap> {
	
	private final static String title = "CAP";
	
	private CompositeCap compositeCap;
	private final Cap cap;
	
	private final boolean permessoGestione;
	private final boolean modify;
	
	private final ControllerCap controllerCap;
	
	public DialogCap(Cap c) {
		super(title, c);
		controllerCap = ControllerCap.getInstance();
		permessoGestione = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.TRASPORTI_CAP_CUD.getID());
		cap = getValore();
		modify = (c != null);
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		compositeCap = new CompositeCap(this, container);
		compositeCap.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeCap.enableElement(permessoGestione);
		if (modify)
			compositeCap.enableIDFields(false);
		addChild(compositeCap);
	}

	@Override
	public boolean isDirty() {
		return compositeCap.isDirty();
	}

	@Override
	public void loadModel() {
		compositeCap.setCAP(cap.getCap());
		compositeCap.setLocalita(cap.getLocalita());
		compositeCap.setProvincia(ControllerProvince.getInstance().getProvincia(cap.getProvincia()));
		compositeCap.setOre10(cap.getTntOreDieci());
		compositeCap.setOre12(cap.getTntOreDodici());
		compositeCap.setDisagiato(cap.getBrtDisagiate());
		compositeCap.setIsola(cap.getBrtIsole());
		compositeCap.setZTL(cap.getBrtZtl());
	}

	@Override
	public void copyDataToModel() {
		cap.setCap(compositeCap.getCAP());
		cap.setLocalita(compositeCap.getLocalita());
		cap.setProvincia(compositeCap.getProvincia().getSigla());
		cap.setRegione(compositeCap.getProvincia().getRegione());
		cap.setBrtDisagiate(compositeCap.getDisagiato());
		cap.setBrtIsole(compositeCap.getIsola());
		cap.setTntOreDieci(compositeCap.getOre10());
		cap.setTntOreDodici(compositeCap.getOre12());
		cap.setBrtZtl(compositeCap.getZTL());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = controllerCap.aggiorna(cap);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = controllerCap.inserisci(cap);
		return insert;
	}

	@Override
	public Cap createNewModel() {
		Cap cap = new Cap();
		return cap;
	}
	
	@Override
	public void prefillModel() {
		//DO NOTHING!
	}

}
