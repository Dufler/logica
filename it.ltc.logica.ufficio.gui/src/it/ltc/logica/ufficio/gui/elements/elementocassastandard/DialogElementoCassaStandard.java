package it.ltc.logica.ufficio.gui.elements.elementocassastandard;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.prodotto.CassaStandard;
import it.ltc.logica.database.model.prodotto.ElementoCassaStandard;
import it.ltc.logica.gui.dialog.DialogModel;

public class DialogElementoCassaStandard extends DialogModel<ElementoCassaStandard> {

	public static final String title = "Elemento Cassa Standard";
	
	private final CassaStandard cassa;
	
	private CompositeElementoCassaStandard composite;
	
	public DialogElementoCassaStandard(ElementoCassaStandard value, CassaStandard cassa) {
		super(title, value);
		this.cassa = cassa;
		this.minimumHeight = 120;
	}

	@Override
	public void loadModel() {
		composite.setTaglia(valore.getTaglia());
		composite.setQuantita(valore.getQuantita());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

	@Override
	public void copyDataToModel() {
		valore.setTaglia(composite.getTaglia());
		valore.setQuantita(composite.getQuantita());		
	}

	@Override
	public List<String> validateModel() {
		List<String> errori = new LinkedList<>();
		if (valore.getQuantita() < 1) {
			errori.add("La quantit\u00E0 deve essere almeno 1.");
		}
		return null;
	}

	@Override
	public boolean updateModel() {
		if (cassa.getElementi().contains(valore))
			cassa.getElementi().remove(valore);
		boolean add = cassa.getElementi().add(valore);
		return add;
	}

	@Override
	public boolean insertModel() {
		boolean add = cassa.getElementi().add(valore);
		return add;
	}

	@Override
	public ElementoCassaStandard createNewModel() {
		ElementoCassaStandard elemento = new ElementoCassaStandard();
		return elemento;
	}

	@Override
	public boolean isDirty() {
		return composite.isDirty();
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeElementoCassaStandard(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));	
	}

}
