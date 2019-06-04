package it.ltc.logica.ufficio.gui.elements.elementocassa;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Cassa;
import it.ltc.logica.database.model.prodotto.ElementoCassa;
import it.ltc.logica.gui.dialog.DialogModel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;

public class DialogElementoCassa extends DialogModel<ElementoCassa> {

	public static final String title = "Prodotto Cassa";
	
	private final Cassa cassa;
	private final Commessa commessa;
	
	private CompositeElementoCassa composite;
	
	public DialogElementoCassa(ElementoCassa value, Cassa cassa, Commessa commessa) {
		super(title, value);
		this.cassa = cassa;
		this.commessa = commessa;
		
		this.minimumHeight = 100;
	}

	@Override
	public void loadModel() {
		composite.setArticolo(valore.getProdotto());
		composite.setQuantita(valore.getQuantita());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

	@Override
	public void copyDataToModel() {
		valore.setProdotto(composite.getArticolo());
		valore.setIdProdotto(composite.getArticolo().getId());
		valore.setQuantita(composite.getQuantita());
	}

	@Override
	public List<String> validateModel() {
//		List<String> errori = new LinkedList<>();
//		if (cassa.getProdotti().contains(valore)) {
//			errori.add("Il prodotto " + valore.getProdotto().getChiaveCliente() + " \u00E8 gi\u00E0 presente nella cassa.");
//		}
//		return errori;
		return null;
	}

	@Override
	public boolean updateModel() {
		if (cassa.getProdotti().contains(valore))
			cassa.getProdotti().remove(valore);
		boolean add = cassa.getProdotti().add(valore);
		return add;
	}

	@Override
	public boolean insertModel() {
		boolean add = cassa.getProdotti().add(valore);
		return add;
	}

	@Override
	public ElementoCassa createNewModel() {
		ElementoCassa elemento = new ElementoCassa();
		return elemento;
	}

	@Override
	public boolean isDirty() {
		return composite.isDirty();
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeElementoCassa(this, container, commessa);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
	}

}
