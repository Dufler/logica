package it.ltc.logica.ufficio.gui.elements.cassa;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.prodotti.ControllerCasse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Cassa;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.ufficio.gui.elements.elementocassa.TabellaElementiCassa;
import it.ltc.logica.ufficio.gui.elements.elementocassa.ToolbarElementiCassa;

public class DialogCassa extends DialogModel<Cassa> {

	public static final String title = "Cassa";
	
	private final Commessa commessa;
	private final ControllerCasse controller;
	
	private CompositeCassa composite;
	private TabellaElementiCassa tabellaComposizione;
	private ToolbarElementiCassa toolbarComposizione;
	
	public DialogCassa(Cassa value, Commessa commessa) {
		super(title, value);
		this.commessa = commessa;
		this.controller = new ControllerCasse(commessa);
		this.minimumHeight = 400;
	}

	@Override
	public void loadModel() {
		composite.setArticolo(valore.getCassa());
		composite.setCodiceCassa(valore.getCodiceCassa());
		composite.setModello(valore.getModello());
		composite.setTipoCassa(valore.getTipo());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!
	}

	@Override
	public void copyDataToModel() {
		valore.setIdCassa(composite.getArticolo().getId());
		valore.setCodiceCassa(composite.getCodiceCassa());
		valore.setModello(composite.getModello());
		valore.setTipo(composite.getTipoCassa());
	}

	@Override
	public List<String> validateModel() {
		List<String> errori = new LinkedList<>();
		if (valore.getProdotti().isEmpty()) {
			errori.add("E' necessario inserire almeno un prodotto nella cassa.");
		}
		return errori;
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
	public Cassa createNewModel() {
		Cassa cassa = new Cassa();
		cassa.setProdotti(new HashSet<>());
		return cassa;
	}

	@Override
	public boolean isDirty() {
		return composite.isDirty() || tabellaComposizione.isDirty();
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeCassa(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		composite.setCommessa(commessa);
		
		toolbarComposizione = new ToolbarElementiCassa(container);
		toolbarComposizione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		tabellaComposizione = new TabellaElementiCassa(container, valore, commessa);
		
		toolbarComposizione.setTabella(tabellaComposizione);
		
		tabellaComposizione.aggiornaContenuto();
	}

}
