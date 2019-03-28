package it.ltc.logica.trasporti.gui.elements.indirizzosimulazione;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.trasporti.ControllerIndirizziSimulazione;
import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.common.controller.trasporti.ControllerProvince;
import it.ltc.logica.database.model.centrale.indirizzi.IndirizzoSimulazione;
import it.ltc.logica.gui.common.composite.CompositeIndirizzo;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class DialogIndirizzoSimulazione extends DialogModel<IndirizzoSimulazione> {
	
private final static String title = "Indirizzo";
	
	private CompositeIndirizzo compositeIndirizzo;
	
	private final ControllerIndirizziSimulazione controllerIndirizziSimulazione;
	private final boolean modifica;
	
	public DialogIndirizzoSimulazione(IndirizzoSimulazione i) {
		super(title, i);
		controllerIndirizziSimulazione = ControllerIndirizziSimulazione.getInstance();
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
		compositeIndirizzo.setRagioneSociale(valore.getRagioneSociale());
		String telefono = valore.getTelefono() != null ? valore.getTelefono() : "";
		compositeIndirizzo.setTelefono(telefono);
		String email = valore.getEmail() != null ? valore.getEmail() : "";
		compositeIndirizzo.setEmail(email);
		compositeIndirizzo.setIndirizzo(valore.getIndirizzo());
		compositeIndirizzo.setCap(valore.getCap());
		compositeIndirizzo.setLocalita(valore.getLocalita());
		compositeIndirizzo.setProvincia(ControllerProvince.getInstance().getProvincia(valore.getProvincia()));
		compositeIndirizzo.setNazione(ControllerNazioni.getInstance().getNazione(valore.getNazione()));
		compositeIndirizzo.setPiano(valore.getConsegnaAlPiano());
		compositeIndirizzo.setPrivato(valore.getConsegnaPrivato());
		compositeIndirizzo.setGDO(valore.getConsegnaGdo());
		compositeIndirizzo.setAppuntamento(valore.getConsegnaAppuntamento());
	}

	@Override
	public boolean isDirty() {
		boolean dirty =	compositeIndirizzo.isDirty();
		return dirty;
	}

	@Override
	public void copyDataToModel() {
		valore.setRagioneSociale(compositeIndirizzo.getRagioneSociale());
		valore.setIndirizzo(compositeIndirizzo.getIndirizzo());
		valore.setCap(compositeIndirizzo.getCap());
		valore.setLocalita(compositeIndirizzo.getLocalita());
		valore.setProvincia(compositeIndirizzo.getProvincia().getSigla());
		valore.setNazione(compositeIndirizzo.getNazione().getCodiceIsoTre());
		valore.setConsegnaAlPiano(compositeIndirizzo.getPiano());
		valore.setConsegnaPrivato(compositeIndirizzo.getPrivato());
		valore.setConsegnaGdo(compositeIndirizzo.getGDO());
		valore.setConsegnaAppuntamento(compositeIndirizzo.getAppuntamento());
		//Opzionali
		if (!compositeIndirizzo.getTelefono().isEmpty())
			valore.setTelefono(compositeIndirizzo.getTelefono());
		if (!compositeIndirizzo.getEmail().isEmpty())
			valore.setEmail(compositeIndirizzo.getEmail());
		valore.setInserimentoManuale(true);
	}

	@Override
	public boolean updateModel() {
		boolean update = controllerIndirizziSimulazione.salva(valore);
		return update;
	}
	
	@Override
	public boolean insertModel() {
		boolean inserimento = controllerIndirizziSimulazione.salva(valore);
		return inserimento;
	}

	@Override
	public IndirizzoSimulazione createNewModel() {
		IndirizzoSimulazione indirizzo = new IndirizzoSimulazione();
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
