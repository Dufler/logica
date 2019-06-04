package it.ltc.logica.ufficio.gui.elements.ordinedettagli;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.ingressi.ControllerMagazzini;
import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.common.controller.uscite.ControllerOrdiniDettagli;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineDettaglio;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.dialog.DialogModel;

public class DialogOrdineDettaglio extends DialogModel<OrdineDettaglio> {

	public static final String title = "Riga del carico";
	
	private final Commessa commessa;
	private final OrdineTestata ordine;
	private final List<OrdineDettaglio> dettagli;
	private final ControllerProdotti controllerProdotti;
	private final ControllerOrdiniDettagli controller;
	
	private CompositeOrdineDettaglio composite;
	
	public DialogOrdineDettaglio(Commessa commessa, OrdineTestata ordine, List<OrdineDettaglio> dettagli, OrdineDettaglio value, ControllerProdotti controller) {
		super(title, value);
		this.commessa = commessa;
		this.ordine = ordine;
		this.dettagli = dettagli;
		this.controller = new ControllerOrdiniDettagli(commessa);
		this.controllerProdotti = controller;
	}

	@Override
	public void loadModel() {
		composite.setArticolo(controllerProdotti.trovaDaID(valore.getArticolo()));
		composite.setMagazzino(ControllerMagazzini.getInstance().getMagazzinoDaCodiceLTC(commessa, valore.getMagazzino()));
		composite.setNote(valore.getNote());
		composite.setOrdinati(valore.getQuantitaOrdinata());
		composite.setAssegnati(valore.getQuantitaAssegnata());
		composite.setImballati(valore.getQuantitaImballata());
		composite.setRiga(valore.getRiga());
	}

	@Override
	public void prefillModel() {
		composite.setAssegnati(0);
		composite.setImballati(0);
		composite.setRiga(trovaProgressivoRiga());
		composite.setMagazzino(ControllerMagazzini.getInstance().getMagazzinoDefault(commessa));
	}
	
	private int trovaProgressivoRiga() {
		int max = 0;
		if (dettagli != null) for (OrdineDettaglio dettaglio : dettagli) {
			if (dettaglio.getRiga() > max)
				max = dettaglio.getRiga();
		}
		return max + 1;
	}

	@Override
	public void copyDataToModel() {
		valore.setArticolo(composite.getArticolo() != null ? composite.getArticolo().getId() : 0);
		valore.setOrdine(ordine.getId());
		valore.setMagazzino(composite.getMagazzino().getCodiceLTC());
		valore.setNote(composite.getNote());
		valore.setQuantitaOrdinata(composite.getOrdinati());
		valore.setQuantitaAssegnata(composite.getAssegnati());
		valore.setQuantitaImballata(composite.getImballati());
		valore.setRiga(composite.getRiga());
	}

	@Override
	public List<String> validateModel() {
		List<String> errors = new LinkedList<>();
		if (composite.getArticolo() == null) {
			errors.add("\u00C8 necessario selezionare un articolo.");
		}
		return errors;
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
	public OrdineDettaglio createNewModel() {
		OrdineDettaglio dettaglio = new OrdineDettaglio();
		return dettaglio;
	}

	@Override
	public boolean isDirty() {
		boolean dirty = composite.isDirty();
		return dirty;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeOrdineDettaglio(this, commessa, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}
	
}
