package it.ltc.logica.trasporti.gui.listini.dialogs.corriere;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.listini.ControllerListiniCorrieri;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.gui.dialog.DialogSelezioneCartella;
import it.ltc.logica.trasporti.gui.composite.CompositeProprietaListinoCorriere;

public class ProprietaListinoCorriere extends DialogModel<ListinoCorriere> {
	
	private static final String titolo = "Propriet\u00E0 - Listino corriere";
	private static final String STAMPA_LABEL = "Esporta Listino";
	
	private final ControllerListiniCorrieri controller;
	
	private ListinoCorriere listino;
	
	private CompositeProprietaListinoCorriere compositeListino;
	
	private final boolean permessoGestione;
	
	public ProprietaListinoCorriere(ListinoCorriere listinoCorriere, boolean permesso) {
		super(titolo, listinoCorriere);
		controller = ControllerListiniCorrieri.getInstance();
		listino = listinoCorriere;
		permessoGestione = permesso;
	}
	
	@Override
	public void aggiungiAltriBottoni(Composite parent) {
		Button stampa = createButton(parent, IDialogConstants.DETAILS_ID, STAMPA_LABEL, false);
		stampa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DialogSelezioneCartella dialog = new DialogSelezioneCartella();
				String path = dialog.open();
				if (path != null && !path.isEmpty())
					controller.esportaListino(listino, path);
			}
		});
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeListino = new CompositeProprietaListinoCorriere(this, container);
		compositeListino.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeListino.enableElement(permessoGestione);
		addChild(compositeListino);
	}

	@Override
	public boolean isDirty() {
		return compositeListino.isDirty();
	}

	@Override
	public void loadModel() {
		compositeListino.setCorriere(ControllerCorrieri.getInstance().getCorriere(listino.getIdCorriere()));
		compositeListino.setNome(listino.getNome());
		compositeListino.setDescrizione(listino.getDescrizione());
		compositeListino.setTipo(ControllerAmbitiFatturazione.getInstance().getAmbito(listino.getTipo()));
	}

	@Override
	public void copyDataToModel() {
		listino.setIdCorriere(compositeListino.getCorriere().getId());
		listino.setNome(compositeListino.getNome());
		listino.setDescrizione(compositeListino.getDescrizione());
		listino.setTipo(compositeListino.getTipo().getId());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = controller.aggiornaListino(listino);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = controller.inserisciListino(listino);
		return insert;
	}

	@Override
	public ListinoCorriere createNewModel() {
		ListinoCorriere listino = new ListinoCorriere();
		return listino;
	}
	
	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

}