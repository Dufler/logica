package it.ltc.logica.trasporti.gui.fatturazione.wizards.spedizioni;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.container.controller.ControllerUtente;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.expand.EspandiBarra;
import it.ltc.logica.gui.elements.expand.EspandiElemento;
import it.ltc.logica.gui.task.DialogProgresso;
import it.ltc.logica.gui.task.ProcessoUI;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.controller.FatturazioneSpedizioniController;
import it.ltc.logica.trasporti.gui.elements.fatturazione.CompositeFiltraPerFatturazione;
import it.ltc.logica.trasporti.gui.elements.fatturazione.TabellaFatturazione;
import it.ltc.logica.trasporti.gui.elements.fatturazione.TabellaFatturazioneSpedizioni;

public class EsitoFatturazioneWizardPage extends PaginaWizardRisultati {

	private static final String titolo = "Fatturazione Spedizioni";
	private static final String descrizione = "Controlla il risultato, conferma la fatturazione ed esporta i dati.";
	
	private final UISynchronize sync;
	
	private final ControllerUtente controllerUtente;
	private final FatturazioneSpedizioniController controllerFatturazione;
	
	private Composite compositeDettaglio;
	private CompositeFiltraPerFatturazione compositeFiltro;
	private Table tableDettaglio;
	private TabellaFatturazione tableViewerDettaglio;
	private Button btnEsportaDati;
	
	private Composite compositeTotali;
	private Label lblTotaleRicavi;
	private Label lblTotaleSpedizioni;
	private Label lblTotaleColli;
	private Label lblTotalePezzi;
	
	private EspandiBarra barra;
	private EspandiElemento filtro;
	
	private boolean salvataggio;
	
	public EsitoFatturazioneWizardPage() {
		super(titolo, descrizione, true);
		setPageComplete(false);
		controllerFatturazione = FatturazioneSpedizioniController.getInstance();
		controllerUtente = ControllerUtente.getInstance();
		sync = controllerUtente.getSync();
		salvataggio = false;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeDettaglio = new Composite(container, SWT.NONE);
		compositeDettaglio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeDettaglio.setLayout(new GridLayout(1, false));
		
		//compositeFiltro = new CompositeFiltraPerFatturazione(compositeDettaglio);
		
		barra = new EspandiBarra(compositeDettaglio);
		barra.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		compositeFiltro = new CompositeFiltraPerFatturazione(null, barra);
		
		filtro = new EspandiElemento(barra, compositeFiltro, "Filtra");
		filtro.setExpanded(false);
//		filtro.setText("Filtra");
//		filtro.setContenuto(compositeFiltro);
		
		Composite compositeControlli = new Composite(container, SWT.NONE);
		compositeControlli.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 1));
		compositeControlli.setLayout(new GridLayout(2, false));
		
		btnEsportaDati = new Button(compositeControlli, SWT.NONE);
		btnEsportaDati.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				esportaDati();
			}
		});
		btnEsportaDati.setText("Conferma Dati Fatturazione");
		btnEsportaDati.setEnabled(false);
		new Label(compositeControlli, SWT.NONE);
		
		compositeTotali = new Composite(container, SWT.NONE);
		compositeTotali.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));
		compositeTotali.setLayout(new GridLayout(3, false));
		
		lblTotaleRicavi = new Label(compositeTotali, SWT.NONE);
		lblTotaleRicavi.setText("Totale ricavi: ");
		new Label(compositeTotali, SWT.NONE);
		new Label(compositeTotali, SWT.NONE);
		
		lblTotaleSpedizioni = new Label(compositeTotali, SWT.NONE);
		lblTotaleSpedizioni.setText("Totale spedizioni: ");
		
		lblTotaleColli = new Label(compositeTotali, SWT.NONE);
		lblTotaleColli.setText("Totale colli:");
		
		lblTotalePezzi = new Label(compositeTotali, SWT.NONE);
		lblTotalePezzi.setText("Totale pezzi: ");
	}
	
	@Override
	public IWizardPage getPreviousPage() {
		if (tableViewerDettaglio != null)
			tableDettaglio.dispose();
		lblTotaleRicavi.setText("Totale ricavi: ");
		lblTotaleSpedizioni.setText("Totale spedizioni: ");
		lblTotaleColli.setText("Totale colli:");
		lblTotalePezzi.setText("Totale pezzi: ");
		return super.getPreviousPage();
	}
	
	private class ProcessoCaricamentoDati extends ProcessoUI {
		
		private static final String title = "Caricamento dati";

		public ProcessoCaricamentoDati() {
			super(title, -1, false, sync);
		}

		@Override
		public void eseguiOperazioniSincronizzate() throws RuntimeException {
//			List<ListinoCommessa> listini = controllerFatturazione.getListiniTrasportiUtilizzati();
//			if (listini.isEmpty())
//				throw new RuntimeException("Non sono stati eseguiti calcoli. Controllare che:\r\n - Ci sia almeno una spedizione.\r\n - Ci sia almeno un listino.");
			ListinoCommessa listino = controllerFatturazione.getListinoSelezionato();
			if (listino == null)
				throw new RuntimeException("Non sono stati eseguiti calcoli. Controllare che:\r\n - Ci sia almeno una spedizione.\r\n - Ci sia almeno un listino.");
			HashMap<String, List<SpedizioneModel>> listePerCodice = controllerFatturazione.getSpedizioniFatturate();
			List<SpedizioneModel> lista = new ArrayList<SpedizioneModel>();
			for (String codice : listePerCodice.keySet()) {
				lista.addAll(listePerCodice.get(codice));
			}
			//Calcolo dei totali
			double totRicavi = 0;
			int totSpedizioni = 0;
			int totColli = 0;
			int totPezzi = 0;
			for (SpedizioneModel model : lista) {
				totRicavi += model.getPreventivoRicavo().getTotale();
				totSpedizioni += 1;
				totColli += model.getSpedizione().getColli();
				totPezzi += model.getSpedizione().getPezzi();
			}
			final String totaleRicavi = "Totale ricavi: " + Decorator.getEuroValue(totRicavi);
			final String totaleSpedizioni = "Totale spedizioni: " + totSpedizioni;
			final String totaleColli = "Totale colli:" + totColli;
			final String totalePezzi = "Totale pezzi: " + totPezzi;
			//Aggiorno la UI
			lblTotaleRicavi.setText(totaleRicavi);
			lblTotaleSpedizioni.setText(totaleSpedizioni);
			lblTotaleColli.setText(totaleColli);
			lblTotalePezzi.setText(totalePezzi);
			compositeTotali.layout();
			//In teoria questo controllo non dovrebbe più servire per la runtime exception di cui sopra
//			ListinoCommessa listino = listini.isEmpty() ? null : listini.get(0);
			tableViewerDettaglio = new TabellaFatturazioneSpedizioni(compositeDettaglio, listino, true);
			tableDettaglio = tableViewerDettaglio.getTable();
			tableDettaglio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			compositeDettaglio.layout();
			tableViewerDettaglio.setElementiDaFatturare(lista);
			
			compositeFiltro.setTabellaFatturazione(tableViewerDettaglio);
		}		
	}
	
	private void caricaDati() {
		ProcessoCaricamentoDati pcc = new ProcessoCaricamentoDati();
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(pcc);
	}

	private void esportaDati() {
		boolean scelta = DialogMessaggio.openQuestion("Conferma Dati di Fatturazione", "Vuoi confermare i dati di fatturazione?");
		if (scelta) {
			salvataggio = controllerFatturazione.salvaDatiTrasporti();
			if (salvataggio) {
				btnEsportaDati.setEnabled(false);
				setPageComplete(true);
			} else {
				DialogMessaggio.openError("Errore", "Errore nel salvataggio dei dati.");
			}
		}
	}

	@Override
	public void mostraRisultato() {
		controllerFatturazione.calcolaFatturazioneTrasporti();
		caricaDati();
		btnEsportaDati.setEnabled(true);
	}
	
	@Override
	protected boolean validazioneSpecifica(boolean valid) {
		return salvataggio;
	}
	
	@Override
	public void copyDataToModel() {
		//DO NOTHING!		
	}
	
}
