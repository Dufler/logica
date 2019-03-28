package it.ltc.logica.cdg.gui.costiricavi.dialogs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.composite.CompositeCdgPezziCostiRicavi;
import it.ltc.logica.cdg.gui.elements.TabellaSpacchettamento;
import it.ltc.logica.common.controller.cdg.ControllerCdgEventi;
import it.ltc.logica.common.controller.cdg.ControllerCdgPezzi;
import it.ltc.logica.common.controller.sistema.ControllerCategorieMerceologiche;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.database.model.centrale.cdg.CdgPezzo;
import it.ltc.logica.database.model.centrale.cdg.CdgPezzoEvento;
import it.ltc.logica.gui.dialog.DialogModel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

public class DialogPezziCostiRicavi extends DialogModel<CdgPezzo> {

	private final static String title = "Controllo di Gestione: Costi e Ricavi per Pezzo";
	
	private CompositeCdgPezziCostiRicavi composite;
	private TabellaSpacchettamento tabella;
	
	private final ControllerCdgPezzi controller;
	
	public DialogPezziCostiRicavi(CdgPezzo value) {
		super(title, value);
		
		minimumHeight = 600;
		minimumWidth = 500;
		
		controller = ControllerCdgPezzi.getInstance();
	}

	@Override
	public void loadModel() {
		composite.setCategoria(ControllerCategorieMerceologiche.getInstance().getCategoria(valore.getCategoriaMerceologica()));
		composite.setCommessa(ControllerCommesse.getInstance().getCommessa(valore.getCommessa()));
		composite.setCosto(valore.getCosto());
		composite.setRicavo(valore.getRicavo());
		tabella.setPezzo(valore);
		tabella.setSpacchettamenti(valore.getSpacchettamenti());
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

	@Override
	public void copyDataToModel() {
		valore.setCategoriaMerceologica(composite.getCategoria().getNome());
		valore.setCommessa(composite.getCommessa().getId());
		valore.setCosto(composite.getCosto());
		valore.setRicavo(composite.getRicavo());
		valore.setSpacchettamenti(tabella.getSpacchettamenti());
	}

	@Override
	public List<String> validateModel() {
		List<String> errors = new LinkedList<>();
		//Controllo costo e ricavo.
		double costo = composite.getCosto();
		if (costo <= 0)
			errors.add("Il costo deve essere maggiore di 0.");
		double ricavo = composite.getRicavo();
		if (ricavo <= 0)
			errors.add("Il ricavo deve essere maggiore di 0.");
		//Controllo che la somma delle percentuali di costo e ricavo sia 100.
		//Controllo che lo stesso evento non sia stato selezionato più di una volta.
		Set<Integer> eventi = new HashSet<>();
		double costoPercentuale = 0;
		double ricavoPercentuale = 0;
		for (CdgPezzoEvento spacchettamento : tabella.getElementi()) {
			costoPercentuale += spacchettamento.getCosto();
			ricavoPercentuale += spacchettamento.getRicavo();
			if (eventi.contains(spacchettamento.getEvento())) {
				CdgEvento evento = ControllerCdgEventi.getInstance().getEvento(spacchettamento.getEvento());
				String nomeEvento = evento != null ? evento.getNome() : "";
				errors.add("E' stato selezionato lo stesso evento più di una volta. " + nomeEvento);
			} else {
				eventi.add(spacchettamento.getEvento());
			}
		}
		if (costoPercentuale != 100.0)
			errors.add("La somma delle percentuali di costo deve essere uguale a 100.");
		if (ricavoPercentuale != 100.0)
			errors.add("La somma delle percentuali di ricavo deve essere uguale a 100.");
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
	public CdgPezzo createNewModel() {
		CdgPezzo pezzo = new CdgPezzo();
		return pezzo;
	}

	@Override
	public boolean isDirty() {
		boolean compositeDirty = composite.isDirty();
		boolean tableDirty = tabella.isDirty();
		return compositeDirty || tableDirty;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		
		composite = new CompositeCdgPezziCostiRicavi(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		tabella = new TabellaSpacchettamento(container);
		Table table = tabella.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}
	
	
}
