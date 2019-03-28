package it.ltc.logica.ufficio.gui.elements.fornitore;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.ingressi.ControllerFornitori;
import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.common.controller.trasporti.ControllerProvince;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.ingressi.Fornitore;
import it.ltc.logica.gui.dialog.DialogModel;

public class DialogFornitore extends DialogModel<Fornitore> {
	
	private static final String title = "Fornitore";
	
	private CompositeFornitore compositeFornitore;
	
	private final ControllerFornitori controllerFornitori;
	
	private final Indirizzo indirizzoFornitore;

	public DialogFornitore(Commessa commessa, Fornitore value) {
		super(title, value);
		controllerFornitori = new ControllerFornitori(commessa);
		if (value != null)
			indirizzoFornitore = value.getIndirizzo();
		else
			indirizzoFornitore = new Indirizzo();
	}
	
	public DialogFornitore(Commessa commessa) {
		this(commessa, null);
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeFornitore = new CompositeFornitore(this, container);
		compositeFornitore.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	public boolean isDirty() {
		return compositeFornitore.isDirty();
	}

	@Override
	public void loadModel() {
		compositeFornitore.setRagioneSociale(valore.getNome());
		compositeFornitore.setNote(valore.getNote());
		compositeFornitore.setIndirizzo(indirizzoFornitore.getIndirizzo());
		compositeFornitore.setLocalita(indirizzoFornitore.getLocalita());
		compositeFornitore.setCap(indirizzoFornitore.getCap());
		compositeFornitore.setProvincia(ControllerProvince.getInstance().getProvincia(indirizzoFornitore.getProvincia()));
		compositeFornitore.setNazione(ControllerNazioni.getInstance().getNazione(indirizzoFornitore.getNazione()));
		compositeFornitore.setTelefono(indirizzoFornitore.getTelefono());
		compositeFornitore.setEmail(indirizzoFornitore.getEmail());
		compositeFornitore.setCodice(valore.getRiferimentoCliente());
	}

	@Override
	public void copyDataToModel() {
		valore.setNome(compositeFornitore.getRagioneSociale());
		valore.setNote(compositeFornitore.getNote());
		valore.setRiferimentoCliente(compositeFornitore.getCodice());
		indirizzoFornitore.setCap(compositeFornitore.getCap());
		indirizzoFornitore.setConsegnaAppuntamento(false);
		indirizzoFornitore.setConsegnaGdo(false);
		indirizzoFornitore.setConsegnaAlPiano(false);
		indirizzoFornitore.setConsegnaPrivato(false);
		indirizzoFornitore.setEmail(compositeFornitore.getEmail());
		indirizzoFornitore.setIndirizzo(compositeFornitore.getIndirizzo());
		indirizzoFornitore.setLocalita(compositeFornitore.getLocalita());
		indirizzoFornitore.setNazione(compositeFornitore.getNazione().getCodiceIsoTre());
		indirizzoFornitore.setProvincia(compositeFornitore.getProvincia().getSigla());
		indirizzoFornitore.setRagioneSociale(compositeFornitore.getRagioneSociale());
		indirizzoFornitore.setTelefono(compositeFornitore.getTelefono());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		return controllerFornitori.aggiorna(valore);
	}

	@Override
	public boolean insertModel() {
		valore.setIndirizzo(indirizzoFornitore);
		boolean insert = controllerFornitori.inserisci(valore);
		return insert;
	}

	@Override
	public Fornitore createNewModel() {
		Fornitore nuovoFornitore = new Fornitore();
		return nuovoFornitore;
	}
	
	@Override
	public void prefillModel() {}

}
