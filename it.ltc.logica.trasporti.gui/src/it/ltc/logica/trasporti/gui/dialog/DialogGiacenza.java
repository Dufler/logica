package it.ltc.logica.trasporti.gui.dialog;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.trasporti.ControllerGiacenze;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;
import it.ltc.logica.database.model.centrale.trasporti.Giacenza;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.trasporti.gui.composite.CompositeGiacenza;

public class DialogGiacenza extends DialogModel<Giacenza> {

	private final static String title = "Giacenza";
	
	private final ControllerSpedizioni controllerSpedizioni;
	private final ControllerGiacenze controllerGiacenze;
	
	private final Spedizione spedizione;
	
	private CompositeGiacenza compositeGiacenza;
	
	public DialogGiacenza(Spedizione spedizione, Giacenza value) {
		super(title, value);
		
		this.spedizione = spedizione;
		
		controllerSpedizioni = ControllerSpedizioni.getInstance();
		controllerGiacenze = ControllerGiacenze.getInstance();
	}

	@Override
	public void loadModel() {
		compositeGiacenza.setDataApertura(valore.getDataApertura());
		compositeGiacenza.setDataChiusura(valore.getDataChiusura());
		compositeGiacenza.setLetteraDiVettura(valore.getLetteraDiVettura());
		compositeGiacenza.setLetteraDiVetturaOriginale(valore.getLetteraDiVetturaOriginale());
	}

	@Override
	public void prefillModel() {
		compositeGiacenza.setDataApertura(null);
		compositeGiacenza.setDataChiusura(null);
		compositeGiacenza.setLetteraDiVetturaOriginale(spedizione.getLetteraDiVettura());		
	}

	@Override
	public void copyDataToModel() {
		valore.setIdCommessa(spedizione.getIdCommessa());
		valore.setIdDocumento(spedizione.getIdDocumento());
		valore.setIdDestinatario(spedizione.getIndirizzoDestinazione());
		valore.setIdMittente(spedizione.getIndirizzoPartenza());
		valore.setIdSpedizione(spedizione.getId());
		valore.setFatturazione(Giacenza.Fatturazione.FATTURABILE);
		valore.setDataApertura(compositeGiacenza.getDataApertura());
		valore.setDataChiusura(compositeGiacenza.getDataChiusura());
		valore.setLetteraDiVettura(compositeGiacenza.getLetteraDiVettura());
		valore.setLetteraDiVetturaOriginale(compositeGiacenza.getLetteraDiVetturaOriginale());
		valore.setNote("Inserita manualmente.");
	}

	@Override
	public List<String> validateModel() {
		List<String> errors = new LinkedList<>();
		if (valore.getDataChiusura().before(valore.getDataApertura()))
			errors.add("Le data di chiusura precede quella di apertura.");
		return errors;
	}

	@Override
	public boolean updateModel() {
		boolean updateSpedizione = true;
		if (!spedizione.getGiacenza()) {
			spedizione.setGiacenza(true);
			updateSpedizione = controllerSpedizioni.aggiorna(spedizione);
		}
		boolean insertGiacenza = controllerGiacenze.inserisci(valore);
		return updateSpedizione && insertGiacenza;
	}

	@Override
	public boolean insertModel() {
		boolean updateSpedizione = true;
		if (!spedizione.getGiacenza()) {
			spedizione.setGiacenza(true);
			updateSpedizione = controllerSpedizioni.aggiorna(spedizione);
		}
		boolean updateGiacenza = controllerGiacenze.inserisci(valore);
		return updateSpedizione && updateGiacenza;
	}

	@Override
	public Giacenza createNewModel() {
		Giacenza giacenza = new Giacenza();
		return giacenza;
	}

	@Override
	public boolean isDirty() {
		return compositeGiacenza.isDirty();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		compositeGiacenza = new CompositeGiacenza(this, container);
		compositeGiacenza.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

}
