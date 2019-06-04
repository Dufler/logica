package it.ltc.logica.ufficio.gui.elements.cassastandard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.prodotti.ControllerCasseStandard;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.CassaStandard;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.ufficio.gui.elements.elementocassastandard.TabellaElementiCassaStandard;
import it.ltc.logica.ufficio.gui.elements.elementocassastandard.ToolbarElementiCassaStandard;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;

public class DialogCassaStandard extends DialogModel<CassaStandard> {

	public static final String title = "Cassa Standard";
	
	private final ControllerCasseStandard controller;
	
	private CompositeCassaStandard composite;
	private TabellaElementiCassaStandard tabellaComposizione;
	private ToolbarElementiCassaStandard toolbarComposizione;
	
	public DialogCassaStandard(CassaStandard value, Commessa commessa) {
		super(title, value);
		this.controller = new ControllerCasseStandard(commessa);
		this.minimumHeight = 300;
	}

	@Override
	public void loadModel() {
		composite.setCodiceCassa(valore.getCodiceCassa());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

	@Override
	public void copyDataToModel() {
		valore.setCodiceCassa(composite.getCodiceCassa());		
	}

	@Override
	public List<String> validateModel() {
		List<String> errori = new LinkedList<>();
		if (valore.getElementi().isEmpty()) {
			errori.add("\u00C8 necessario inserire almeno un elemento nella cassa standard.");
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
	public CassaStandard createNewModel() {
		CassaStandard cassa = new CassaStandard();
		cassa.setElementi(new HashSet<>());
		return cassa;
	}

	@Override
	public boolean isDirty() {
		return composite.isDirty() || tabellaComposizione.isDirty();
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeCassaStandard(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		toolbarComposizione = new ToolbarElementiCassaStandard(container);
		
		tabellaComposizione = new TabellaElementiCassaStandard(container, valore);
		
		toolbarComposizione.setTabella(tabellaComposizione);
		tabellaComposizione.aggiornaContenuto();
	}

}
