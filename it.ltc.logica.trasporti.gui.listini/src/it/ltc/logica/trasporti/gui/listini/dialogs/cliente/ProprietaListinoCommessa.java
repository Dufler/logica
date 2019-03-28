package it.ltc.logica.trasporti.gui.listini.dialogs.cliente;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.gui.dialog.DialogSelezioneCartella;
import it.ltc.logica.trasporti.gui.composite.CompositeProprietaListinoCliente;

public class ProprietaListinoCommessa extends DialogModel<ListinoCommessa> {
	
	private static final String titolo = "Propriet\u00E0 - Listino cliente";
	public static final String STAMPA_LABEL = "Esporta Listino";
	
	private final ControllerListiniClienti controller;
	
	private ListinoCommessa listino;
	
	private CompositeProprietaListinoCliente compositeProprieta;
	
	private final boolean permessoGestione;
	
	public ProprietaListinoCommessa(ListinoCommessa listinoCommessa, boolean permesso) {
		super(titolo, listinoCommessa);
		listino = listinoCommessa;
		controller = ControllerListiniClienti.getInstance();
		permessoGestione = permesso;
	}
	
	public void copyDataToModel() {
		String nome = compositeProprieta.getNome();
		listino.setNome(nome);
		Integer tipo = compositeProprieta.getTipo().getId();
		listino.setTipo(tipo);
		String descrizione = compositeProprieta.getDescrizione();
		listino.setDescrizione(descrizione);
		Integer idCommessa = compositeProprieta.getCommessa().getId();
		listino.setIdCommessa(idCommessa);
	}

	@Override
	public void loadModel() {
		String nome = listino.getNome();
		compositeProprieta.setNome(nome);
		String descrizione = listino.getDescrizione();
		compositeProprieta.setDescrizione(descrizione);
		Integer idCommessa = listino.getIdCommessa();
		Commessa commessa = ControllerCommesse.getInstance().getCommessa(idCommessa);
		compositeProprieta.setCommessa(commessa);
		Integer id = listino.getTipo();
		AmbitoFattura tipo = ControllerAmbitiFatturazione.getInstance().getAmbito(id);
		compositeProprieta.setTipo(tipo);
	}

	@Override
	public boolean updateModel() {
		boolean update = controller.aggiornaListino(listino);
		return update;
	}
	
	@Override
	public boolean insertModel() {
		boolean inserimento = controller.inserisciListino(listino);
		return inserimento;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		compositeProprieta = new CompositeProprietaListinoCliente(this, container, CompositeProprietaListinoCliente.Tipo.CLIENTE);
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
	public ListinoCommessa createNewModel() {
		ListinoCommessa listino = new ListinoCommessa();
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
