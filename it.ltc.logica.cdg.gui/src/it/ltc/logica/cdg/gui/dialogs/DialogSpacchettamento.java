package it.ltc.logica.cdg.gui.dialogs;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.composites.CompositeSpacchettamento;
import it.ltc.logica.common.controller.cdg.ControllerCdgEventi;
import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.database.model.centrale.cdg.CdgPezzo;
import it.ltc.logica.database.model.centrale.cdg.CdgPezzoEvento;
import it.ltc.logica.gui.dialog.DialogModel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;

public class DialogSpacchettamento extends DialogModel<CdgPezzoEvento> {
	
	private static final String title = "Costo e Ricavo percentuali";
	
	private CompositeSpacchettamento composite;
	
	private final CdgPezzo pezzo;
	
	public DialogSpacchettamento(CdgPezzo pezzo, CdgPezzoEvento value) {
		super(title, value);
		
		minimumWidth = 330;
		minimumHeight = 210;
		
		this.pezzo = pezzo;
	}

	@Override
	public void loadModel() {
		CdgEvento evento = ControllerCdgEventi.getInstance().getEvento(valore.getEvento());
		composite.setFase(ControllerCdgFasi.getInstance().getFase(evento.getFase()));
		composite.setEvento(evento);
		composite.setCosto(valore.getCosto());
		composite.setRicavo(valore.getRicavo());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!
	}

	@Override
	public void copyDataToModel() {
		int idPezzo = pezzo != null ? pezzo.getId() : -1;
		valore.setPezzo(idPezzo);
		valore.setEvento(composite.getEvento().getId());
		valore.setCosto(composite.getCosto());
		valore.setRicavo(composite.getRicavo());
	}

	@Override
	public List<String> validateModel() {
		List<String> errors = new LinkedList<>();
		//Disabilito gli extra
		CdgFase fase = composite.getFase();
		if (fase != null && fase.getId() == CdgFase.ID_EXTRA)
			errors.add("Non \u00E8 possibile inserire gli extra nelle percentuali standard.");
		double costo = composite.getCosto();
		if (costo < 0 || costo > 100)
			errors.add("La percentuale di costo deve essere compresa tra 0% e 100%.");
		double ricavo = composite.getRicavo();
		if (ricavo < 0 || ricavo > 100)
			errors.add("La percentuale di ricavo deve essere compresa tra 0% e 100%.");
		return errors;
	}

	@Override
	public boolean updateModel() {
		//DO NOTHING!
		return true;
	}

	@Override
	public boolean insertModel() {
		//DO NOTHING!
		return true;
	}

	@Override
	public CdgPezzoEvento createNewModel() {
		CdgPezzoEvento abbinamento = new CdgPezzoEvento();
		return abbinamento;
	}

	@Override
	public boolean isDirty() {
		boolean dirty = composite.isDirty();
		return dirty;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeSpacchettamento(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
	}

}
