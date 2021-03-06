package it.ltc.logica.trasporti.gui.listini.dialogs.simulazione;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.listini.ListiniSimulazioneController;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.gui.dialog.DialogSelezioneCartella;
import it.ltc.logica.trasporti.gui.composite.CompositeProprietaListinoCliente;

public class ProprietaListinoSimulazione extends DialogModel<ListinoSimulazione> {
	
	private static final String titolo = "Propriet\u00E0 - Listino di simulazione";
	public static final String STAMPA_LABEL = "Esporta Listino";
	
	private final ListiniSimulazioneController controller;
	
	private ListinoSimulazione listino;
	
	private CompositeProprietaListinoCliente compositeProprieta;
	
	private final boolean permessoGestione;
	
	public ProprietaListinoSimulazione(ListinoSimulazione listinoCommessa, boolean permesso) {
		super(titolo, listinoCommessa);
		listino = listinoCommessa;
		controller = ListiniSimulazioneController.getInstance();
		permessoGestione = permesso;
	}
	
	public void copyDataToModel() {
		String nome = compositeProprieta.getNome();
		listino.setNome(nome);
		Integer tipo = compositeProprieta.getTipo().getId();
		listino.setTipo(tipo);
		String descrizione = compositeProprieta.getDescrizione();
		listino.setDescrizione(descrizione);
	}

	@Override
	public void loadModel() {
		String nome = listino.getNome();
		compositeProprieta.setNome(nome);
		String descrizione = listino.getDescrizione();
		compositeProprieta.setDescrizione(descrizione);
		//Integer idCommessa = listino.getIdCommessa();
		//Commessa commessa = Commessa.getCommessa(idCommessa);
		//compositeProprieta.setCommessa(commessa);
		Integer id = listino.getTipo();
		AmbitoFattura tipo = ControllerAmbitiFatturazione.getInstance().getAmbito(id);
		compositeProprieta.setTipo(tipo);
	}

	@Override
	public boolean updateModel() {
		boolean update = controller.aggiornaListinoCliente(listino);
		return update;
	}
	
	@Override
	public boolean insertModel() {
		boolean inserimento = controller.inserisciListino(listino);
		return inserimento;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		compositeProprieta = new CompositeProprietaListinoCliente(this, container, CompositeProprietaListinoCliente.Tipo.SIMULAZIONE);
		compositeProprieta.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeProprieta.enableElement(permessoGestione);
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
	public boolean isDirty() {
		boolean modificato = compositeProprieta.isDirty();
		return modificato;
	}

	@Override
	public ListinoSimulazione createNewModel() {
		ListinoSimulazione listino = new ListinoSimulazione();
		return listino;
	}

	@Override
	public List<String> validateModel() {
		return null;
	}
	
	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

}
