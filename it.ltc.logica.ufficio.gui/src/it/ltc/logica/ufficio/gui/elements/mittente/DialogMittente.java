package it.ltc.logica.ufficio.gui.elements.mittente;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.common.controller.trasporti.ControllerProvince;
import it.ltc.logica.common.controller.uscite.ControllerMittenti;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.common.composite.CompositeIndirizzo;
import it.ltc.logica.gui.common.composite.CompositeIndirizzo.TipoIndirizzo;
import it.ltc.logica.gui.dialog.DialogModel;

public class DialogMittente extends DialogModel<Indirizzo> {
	
	public static final String title = "Mittente";
	
	private CompositeIndirizzo compositeIndirizzo;
	
	private final ControllerMittenti controller;

	public DialogMittente(Indirizzo value, Commessa commessa) {
		super(title, value);
		
		controller = new ControllerMittenti(commessa);
	}

	@Override
	public void loadModel() {
		compositeIndirizzo.setRagioneSociale(valore.getRagioneSociale());
		compositeIndirizzo.setIndirizzo(valore.getIndirizzo());
		compositeIndirizzo.setCap(valore.getCap());
		compositeIndirizzo.setLocalita(valore.getLocalita());
		compositeIndirizzo.setProvincia(ControllerProvince.getInstance().getProvincia(valore.getProvincia()));
		compositeIndirizzo.setNazione(ControllerNazioni.getInstance().getNazione(valore.getNazione()));
		compositeIndirizzo.setTelefono(valore.getTelefono());
		compositeIndirizzo.setEmail(valore.getEmail());
	}

	@Override
	public void prefillModel() {}

	@Override
	public void copyDataToModel() {
		valore.setRagioneSociale(compositeIndirizzo.getRagioneSociale());
		valore.setIndirizzo(compositeIndirizzo.getIndirizzo());
		valore.setCap(compositeIndirizzo.getCap());
		valore.setLocalita(compositeIndirizzo.getLocalita());
		valore.setProvincia(compositeIndirizzo.getProvincia().getSigla());
		valore.setNazione(compositeIndirizzo.getNazione().getCodiceIsoTre());
		valore.setTelefono(compositeIndirizzo.getTelefono());
		valore.setEmail(compositeIndirizzo.getEmail());
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
	public Indirizzo createNewModel() {
		Indirizzo indirizzo = new Indirizzo();
		return indirizzo;
	}

	@Override
	public boolean isDirty() {
		return compositeIndirizzo.isDirty();
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		compositeIndirizzo = new CompositeIndirizzo(this, container, TipoIndirizzo.SEMPLICE);
	}
}
