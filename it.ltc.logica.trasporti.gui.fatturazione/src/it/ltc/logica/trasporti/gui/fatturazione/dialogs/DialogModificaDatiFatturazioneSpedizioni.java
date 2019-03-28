package it.ltc.logica.trasporti.gui.fatturazione.dialogs;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino.Scopo;
import it.ltc.logica.common.calcolo.algoritmi.VoceCalcolata;
import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.fatturazione.ControllerDocumentiDiFatturazione;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;
import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.VoceFattura;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.dialog.DialogSemplice;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.gui.elements.fatturazione.CompositeFiltraPerFatturazione;
import it.ltc.logica.trasporti.gui.elements.fatturazione.TabellaCorreggiFatturazioneSpedizioni;
import it.ltc.logica.trasporti.gui.elements.fatturazione.TabellaFatturazione;

public class DialogModificaDatiFatturazioneSpedizioni extends DialogSemplice {

	private static final String title = "Modifica Dati di Fatturazione: Spedizioni Italia";
	
	private Composite compositeTotali;
	private Label lblTotaleRicavi;
	private Label lblTotaleSpedizioni;
	private Label lblTotaleColli;
	private Label lblTotalePezzi;
	
	private Composite compositeDettaglio;
	private CompositeFiltraPerFatturazione compositeFiltro;
	private Table tableDettaglio;
	private TabellaFatturazione tableViewerDettaglio;
	
	private final DecimalFormat df;
	
	private final ControllerListiniClienti controllerListini;
	private final ControllerAmbitiFatturazione controllerAmbiti;
	private final ControllerSpedizioni controllerSpedizioni;
	private final ControllerDocumentiDiFatturazione controllerDocumenti;
	
	private final DocumentoFattura documento;
	private final ListinoCommessa listino;
	private final HashMap<Integer, SpedizioneModel> lista;
	private final HashMap<Integer, List<VoceFattura>> mappaListeVoci;
	private final List<VoceFattura> voci;
	
	public DialogModificaDatiFatturazioneSpedizioni(DocumentoFattura documento) {
		super(title, null, true);
		
		setShellStyle(SWT.SHELL_TRIM  | getDefaultOrientation());
		
		df = new DecimalFormat("#,##0.000 \u20AC");
		
		controllerListini = ControllerListiniClienti.getInstance();
		controllerAmbiti = ControllerAmbitiFatturazione.getInstance();
		controllerSpedizioni = ControllerSpedizioni.getInstance();
		controllerDocumenti = ControllerDocumentiDiFatturazione.getInstance();
		
		this.documento = documento;
		voci = controllerDocumenti.caricaVociPerDocumento(documento);
		listino = controllerListini.getListinoPerAmbitoECliente(documento.getIdAmbito(), documento.getIdCommessa());
		lista = new HashMap<>();
		mappaListeVoci = new HashMap<>();
		for (VoceFattura voce : voci) {
			//Carico il model se non c'è l'ho ancora
			int id = voce.getIdRiferimento();
			if (!lista.containsKey(id)) {
				Spedizione spedizione = controllerSpedizioni.getSpedizione(id);
				SpedizioneModel model = SpedizioneModel.caricaSpedizione(spedizione);
				Calcolo calcolo = new Calcolo(listino.getNome(), listino.getId(), Calcolo.Tipo.RICAVO);
				model.addCalcolo(calcolo);
				lista.put(id, model);
			}
			//Aggiungo la voce alla lista
			if (!mappaListeVoci.containsKey(id)) {
				mappaListeVoci.put(id, new LinkedList<VoceFattura>());
			}
			List<VoceFattura> listaVociFattura = mappaListeVoci.get(id);
			listaVociFattura.add(voce);
			//Aggiungo la voce calcolata
			SpedizioneModel model = lista.get(id);
			Calcolo calcolo = model.getCalcoloRichiesto(Scopo.RICAVO);
			VoceDiListino voceListino = controllerListini.getVoceDiListino(voce.getIdVoce());
			SottoAmbitoFattura ambito = controllerAmbiti.getSottoAmbito(voce.getIdSottoAmbito());
			VoceCalcolata voceCalcolata = new VoceCalcolata(voceListino.getId(), voceListino.getNome(), ambito.getId(), ambito.getNome(), ambito.getCategoriaAmbito(), ambito.getDescrizione());
			voceCalcolata.setIdVoceFatturazione(voce.getId());
			voceCalcolata.setCosto(voce.getImporto());
			calcolo.aggiungiVoce(voceCalcolata);
		}
	}
	
	@Override
    protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setMaximized(true);
		newShell.setText(title);
    }

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		
		container.setLayout(new GridLayout(1, false));
		
		compositeDettaglio = new Composite(container, SWT.NONE);
		compositeDettaglio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeDettaglio.setLayout(new GridLayout(1, false));
		
		compositeFiltro = new CompositeFiltraPerFatturazione(null, compositeDettaglio);
		
		tableViewerDettaglio = new TabellaCorreggiFatturazioneSpedizioni(compositeDettaglio, listino, true, documento, mappaListeVoci);
		tableDettaglio = tableViewerDettaglio.getTable();
		tableDettaglio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tableViewerDettaglio.setInput(lista.values());
		
		compositeFiltro.setTabellaFatturazione(tableViewerDettaglio);
		compositeDettaglio.layout();
		
		compositeTotali = new Composite(container, SWT.NONE);
		compositeTotali.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));
		compositeTotali.setLayout(new GridLayout(1, false));
		
		lblTotaleRicavi = new Label(compositeTotali, SWT.NONE);
		lblTotaleRicavi.setText("Totale ricavi: ");
		new Label(compositeTotali, SWT.NONE);
		
		lblTotaleSpedizioni = new Label(compositeTotali, SWT.NONE);
		lblTotaleSpedizioni.setText("Totale spedizioni: ");
		
		lblTotaleColli = new Label(compositeTotali, SWT.NONE);
		lblTotaleColli.setText("Totale colli:");
		
		lblTotalePezzi = new Label(compositeTotali, SWT.NONE);
		lblTotalePezzi.setText("Totale pezzi: ");

		aggiornaTotali();
	}
	
	private void aggiornaTotali() {
		double totaleRicavi = 0;
		int totaleSpedizioni = 0;
		int totaleColli = 0;
		int totalePezzi = 0;
		//Calcolo i totali
		for (SpedizioneModel model : lista.values()) {
			totaleRicavi += model.getCostoTotale(Scopo.RICAVO);
			totaleColli += model.getSpedizione().getColli();
			totalePezzi += model.getSpedizione().getPezzi();
			totaleSpedizioni += 1;
		}
		//Aggiorno la UI
		lblTotaleRicavi.setText("Totale ricavi: " + df.format(totaleRicavi));
		lblTotaleSpedizioni.setText("Totale spedizioni: " + totaleSpedizioni);
		lblTotaleColli.setText("Totale colli:" + totaleColli);
		lblTotalePezzi.setText("Totale pezzi: " + totalePezzi);
		compositeTotali.layout();
	}

	@Override
	public void checkElementiGrafici() {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		okButton = createButton(parent, IDialogConstants.OK_ID, OK_LABEL, false);
		cancelButton = createButton(parent, IDialogConstants.CANCEL_ID, CANCEL_LABEL, true);
	}

}
