package it.ltc.logica.trasporti.gui.elements.indirizzo;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.common.controller.trasporti.ControllerProvince;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.common.composite.CompositeIndirizzo;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class DialogIndirizzo extends DialogModel<Indirizzo> {

	private final static String title = "Indirizzo";
	
	private CompositeIndirizzo compositeIndirizzo;
	
	private final ControllerIndirizzi controllerIndirizzi;
	private final Indirizzo indirizzo;
	private final boolean modifica;
	
	public DialogIndirizzo(Indirizzo i) {
		super(title, i);
		controllerIndirizzi = ControllerIndirizzi.getInstance();
		indirizzo = getValore();
		modifica = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.TRASPORTI_INDIRIZZI_GESTIONE.getID());
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		compositeIndirizzo = new CompositeIndirizzo(this, container, CompositeIndirizzo.TipoIndirizzo.DEFAULT);
		compositeIndirizzo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeIndirizzo.enableElement(modifica);
	}

	@Override
	public void loadModel() {
		compositeIndirizzo.setRagioneSociale(indirizzo.getRagioneSociale());
		String telefono = indirizzo.getTelefono() != null ? indirizzo.getTelefono() : "";
		compositeIndirizzo.setTelefono(telefono);
		String email = indirizzo.getEmail() != null ? indirizzo.getEmail() : "";
		compositeIndirizzo.setEmail(email);
		compositeIndirizzo.setIndirizzo(indirizzo.getIndirizzo());
		compositeIndirizzo.setCap(indirizzo.getCap());
		compositeIndirizzo.setLocalita(indirizzo.getLocalita());
		compositeIndirizzo.setProvincia(ControllerProvince.getInstance().getProvincia(indirizzo.getProvincia()));
		compositeIndirizzo.setNazione(ControllerNazioni.getInstance().getNazione(indirizzo.getNazione()));
		compositeIndirizzo.setPiano(indirizzo.getConsegnaAlPiano());
		compositeIndirizzo.setPrivato(indirizzo.getConsegnaPrivato());
		compositeIndirizzo.setGDO(indirizzo.getConsegnaGdo());
		compositeIndirizzo.setAppuntamento(indirizzo.getConsegnaAppuntamento());
	}

	@Override
	public boolean isDirty() {
		boolean dirty =	compositeIndirizzo.isDirty();
		return dirty;
	}

	@Override
	public void copyDataToModel() {
		indirizzo.setRagioneSociale(compositeIndirizzo.getRagioneSociale());
		indirizzo.setIndirizzo(compositeIndirizzo.getIndirizzo());
		indirizzo.setCap(compositeIndirizzo.getCap());
		indirizzo.setLocalita(compositeIndirizzo.getLocalita());
		indirizzo.setProvincia(compositeIndirizzo.getProvincia().getSigla());
		indirizzo.setNazione(compositeIndirizzo.getNazione().getCodiceIsoTre());
		indirizzo.setConsegnaAlPiano(compositeIndirizzo.getPiano());
		indirizzo.setConsegnaPrivato(compositeIndirizzo.getPrivato());
		indirizzo.setConsegnaGdo(compositeIndirizzo.getGDO());
		indirizzo.setConsegnaAppuntamento(compositeIndirizzo.getAppuntamento());
		//Opzionali
		if (!compositeIndirizzo.getTelefono().isEmpty())
			indirizzo.setTelefono(compositeIndirizzo.getTelefono());
		if (!compositeIndirizzo.getEmail().isEmpty())
			indirizzo.setEmail(compositeIndirizzo.getEmail());
	}

	@Override
	public boolean updateModel() {
		boolean update = controllerIndirizzi.aggiorna(indirizzo);
		return update;
	}
	
	@Override
	public boolean insertModel() {
		boolean inserimento = controllerIndirizzi.inserisci(indirizzo);
		return inserimento;
	}

	@Override
	public Indirizzo createNewModel() {
		Indirizzo indirizzo = new Indirizzo();
		return indirizzo;
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public void prefillModel() {
		compositeIndirizzo.setNazione(ControllerNazioni.getInstance().getDefault());	
	}

}
