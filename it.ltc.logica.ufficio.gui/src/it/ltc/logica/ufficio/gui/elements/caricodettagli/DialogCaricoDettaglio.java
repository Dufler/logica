package it.ltc.logica.ufficio.gui.elements.caricodettagli;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.ingressi.ControllerCarichiDettagli;
import it.ltc.logica.common.controller.ingressi.ControllerMagazzini;
import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.CaricoDettaglio;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.database.model.centrale.ingressi.StatiCarico;
import it.ltc.logica.gui.dialog.DialogModel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;

public class DialogCaricoDettaglio extends DialogModel<CaricoDettaglio> {

	public static final String title = "Riga del carico";
	
	private final Commessa commessa;
	private final CaricoTestata carico;
	private final List<CaricoDettaglio> dettagli;
	private final ControllerProdotti controllerProdotti;
	private final ControllerCarichiDettagli controller;
	
	private CompositeDettaglioCarico composite;
	
	public DialogCaricoDettaglio(Commessa commessa, CaricoTestata carico, List<CaricoDettaglio> dettagli, CaricoDettaglio value, ControllerProdotti controller) {
		super(title, value);
		this.commessa = commessa;
		this.carico = carico;
		this.dettagli = dettagli;
		this.controller = new ControllerCarichiDettagli(commessa);
		this.controllerProdotti = controller;
	}

	@Override
	public void loadModel() {
		composite.setArticolo(controllerProdotti.trovaDaID(valore.getArticolo()));
		composite.setMadeIn(ControllerNazioni.getInstance().getNazione(valore.getMadeIn()));
		composite.setMagazzino(ControllerMagazzini.getInstance().getMagazzinoDaCodiceLTC(commessa, valore.getMagazzino()));
		composite.setNote(valore.getNote());
		composite.setDichiarati(valore.getQuantitaDichiarata());
		composite.setRiscontrati(valore.getQuantitaRiscontrata());
		composite.setRiga(valore.getRiga());
	}

	@Override
	public void prefillModel() {
		composite.setMadeIn(ControllerNazioni.getInstance().getDefault());
		composite.setRiscontrati(0);
		composite.setRiga(trovaProgressivoRiga());
	}
	
	private int trovaProgressivoRiga() {
		int max = 0;
		if (dettagli != null) for (CaricoDettaglio dettaglio : dettagli) {
			if (dettaglio.getRiga() > max)
				max = dettaglio.getRiga();
		}
		return max + 1;
	}

	@Override
	public void copyDataToModel() {
		valore.setArticolo(composite.getArticolo() != null ? composite.getArticolo().getId() : 0);
		valore.setCarico(carico.getId());
		valore.setMadeIn(composite.getMadeIn().getCodiceIsoTre());
		valore.setMagazzino(composite.getMagazzino().getCodiceLTC());
		valore.setNote(composite.getNote());
		valore.setQuantitaDichiarata(composite.getDichiarati());
		valore.setQuantitaRiscontrata(composite.getRiscontrati());
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
	public CaricoDettaglio createNewModel() {
		CaricoDettaglio dettaglio = new CaricoDettaglio();
		return dettaglio;
	}

	@Override
	public boolean isDirty() {
		boolean dirty = composite.isDirty();
		return dirty;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeDettaglioCarico(this, commessa, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		boolean enable = (carico.getStato() == StatiCarico.INSERITO || carico.getStato() == StatiCarico.ARRIVATO);
		composite.enableElement(enable);
	}

}
