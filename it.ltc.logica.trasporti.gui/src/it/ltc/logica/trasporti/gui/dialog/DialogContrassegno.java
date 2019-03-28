package it.ltc.logica.trasporti.gui.dialog;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.sistema.ControllerValute;
import it.ltc.logica.common.controller.trasporti.ControllerContrassegni;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;
import it.ltc.logica.common.controller.trasporti.ControllerTipoContrassegno;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.common.composite.CompositeContrassegno;
import it.ltc.logica.gui.dialog.DialogModel;

public class DialogContrassegno extends DialogModel<Contrassegno> {

	private static final String title = "Contrassegno";
	
	private CompositeContrassegno compositeContrassegno;
	
	private final Spedizione spedizione;
	
	private final ControllerSpedizioni controllerSpedizioni;
	private final ControllerContrassegni controllerContrassegni;
	
	public DialogContrassegno(Spedizione spedizione, Contrassegno value) {
		super(title, value);
		
		this.spedizione = spedizione;	
		this.controllerSpedizioni = ControllerSpedizioni.getInstance();
		this.controllerContrassegni = ControllerContrassegni.getInstance();
	}

	@Override
	public void loadModel() {
		compositeContrassegno.setValore(valore.getValore());
		compositeContrassegno.setValuta(ControllerValute.getInstance().getValuta(valore.getValuta()));
		compositeContrassegno.setTipoContrassegno(ControllerTipoContrassegno.getInstance().getTipoContrassegno(valore.getTipo()));
		compositeContrassegno.setAnnullato(valore.getAnnullato());
	}

	@Override
	public void prefillModel() {
		compositeContrassegno.setValuta(ControllerValute.getInstance().getValuta("EUR"));
		compositeContrassegno.setAnnullato(false);
	}

	@Override
	public void copyDataToModel() {
		//Copio i valori del contrassegno.
		valore.setIdSpedizione(spedizione.getId());
		valore.setValore(compositeContrassegno.getValore());
		valore.setValuta(compositeContrassegno.getValuta().getCodice());
		valore.setTipo(compositeContrassegno.getTipoContrassegno().getCodice());
		valore.setAnnullato(compositeContrassegno.getAnnullato());
		//Segno la spedizione come in contrassegno.
		spedizione.setContrassegno(true);
	}

	@Override
	public List<String> validateModel() {
		List<String> errors = new LinkedList<>();
		Double valoreContrassegno = compositeContrassegno.getValore();
		if (valoreContrassegno < 0)
			errors.add("Il valore del contrassegno non pu\u00F2 essere inferiore a 0.");
		return errors;
	}

	@Override
	public boolean updateModel() {
		return controllerContrassegni.aggiorna(valore);
	}

	@Override
	public boolean insertModel() {
		boolean inserimento = controllerContrassegni.inserisci(valore);
		boolean aggiornamento = controllerSpedizioni.aggiorna(spedizione);
		return inserimento && aggiornamento;
	}

	@Override
	public Contrassegno createNewModel() {
		Contrassegno contrassegno = new Contrassegno();
		return contrassegno;
	}

	@Override
	public boolean isDirty() {
		return compositeContrassegno.isDirty();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		compositeContrassegno = new CompositeContrassegno(this, container);
		compositeContrassegno.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

}
