package it.ltc.logica.amministrazione.gui.fatturazione.wizards.uscite;

import java.util.List;

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

import it.ltc.logica.amministrazione.calcolo.algoritmi.LogisticaModel;
import it.ltc.logica.amministrazione.gui.fatturazione.controller.FatturazioneUsciteController;
import it.ltc.logica.amministrazione.gui.fatturazione.elements.TabellaFatturazione;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino.Scopo;
import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.task.DialogProgresso;
import it.ltc.logica.gui.task.Processo;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;


public class EsitoFatturazioneWizardPage extends PaginaWizardRisultati {

	private static final String titolo = "Fatturazione Uscite";
	private static final String descrizione = "Controlla il risultato, conferma la fatturazione ed esporta i dati.";
	
	//private final UISynchronize sync;
	
	//private final ControllerUtente controllerUtente;
	private final FatturazioneUsciteController controllerFatturazione;
	
	private Composite compositeDettaglio;
	private Table tableDettaglio;
	private TabellaFatturazione tableViewerDettaglio;
	private Button btnEsportaDati;
	
	private Composite compositeTotali;
	private Label lblTotaleRicavi;
	private Label lblTotaleSpedizioni;
	
	private boolean esportazioneTerminata;
	
	public EsitoFatturazioneWizardPage() {
		super(titolo, descrizione, true);
		setPageComplete(false);
		controllerFatturazione = FatturazioneUsciteController.getInstance();
		//controllerUtente = ControllerUtente.getInstance();
		//sync = controllerUtente.getSync();
		esportazioneTerminata = false;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeDettaglio = new Composite(container, SWT.NONE);
		compositeDettaglio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeDettaglio.setLayout(new GridLayout(1, false));
		
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
		btnEsportaDati.setText("Esporta Dati");
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
		
	}
	
	@Override
	public IWizardPage getPreviousPage() {
		if (tableViewerDettaglio != null)
			tableDettaglio.dispose();
		lblTotaleRicavi.setText("Totale ricavi: ");
		lblTotaleSpedizioni.setText("Totale spedizioni: ");
		return super.getPreviousPage();
	}
	
	private class ProcessoCaricamentoDati extends Processo {
		
		private static final String title = "Caricamento dati";

		public ProcessoCaricamentoDati() {
			super(title, -1);
		}

		@Override
		public void eseguiOperazioni() throws Exception {
			List<LogisticaModel> lista = controllerFatturazione.getUsciteFatturate();
			//Calcolo dei totali
			double totRicavi = 0;
			int totIngressi = 0;
			for (LogisticaModel model : lista) {
				totRicavi += model.getCostoTotale(Scopo.RICAVO);
				totIngressi += 1;
			}
			final String totaleRicavi = "Totale ricavi: " + Decorator.getEuroValue(totRicavi);
			final String totaleSpedizioni = "Totale ingressi: " + totIngressi;
			//Aggiorno la UI
			lblTotaleRicavi.setText(totaleRicavi);
			lblTotaleSpedizioni.setText(totaleSpedizioni);
			compositeTotali.layout();
			tableViewerDettaglio = new TabellaFatturazione(compositeDettaglio);
			tableDettaglio = tableViewerDettaglio.getTable();
			tableDettaglio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			compositeDettaglio.layout();
			tableViewerDettaglio.setInput(lista);
		}
		
	}
	
	private void caricaDati() {
		ProcessoCaricamentoDati processo = new ProcessoCaricamentoDati();
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
	}

//	private void caricaDati() {
//		IRunnableWithProgress runnable = new IRunnableWithProgress() {
//			@Override
//			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
//				monitor.beginTask("Caricamento dati", IProgressMonitor.UNKNOWN);
//				List<LogisticaModel> lista = controllerFatturazione.getUsciteFatturate();
//				//Calcolo dei totali
//				double totRicavi = 0;
//				int totIngressi = 0;
//				for (LogisticaModel model : lista) {
//					totRicavi += model.getCostoTotale(Scopo.RICAVO);
//					totIngressi += 1;
//				}
//				final String totaleRicavi = "Totale ricavi: " + Decorator.getEuroValue(totRicavi);
//				final String totaleSpedizioni = "Totale ingressi: " + totIngressi;
//				//Aggiorno la UI
//				sync.asyncExec(new Runnable() {
//					@Override
//					public void run() {
//						lblTotaleRicavi.setText(totaleRicavi);
//						lblTotaleSpedizioni.setText(totaleSpedizioni);
//						compositeTotali.layout();
//						tableViewerDettaglio = new TabellaFatturazione(compositeDettaglio);
//						tableDettaglio = tableViewerDettaglio.getTable();
//						tableDettaglio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
//						compositeDettaglio.layout();
//						tableViewerDettaglio.setInput(lista);
//					}
//				});
//				monitor.done();
//			}
//		};
//
//		try {
//			DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
//			dialog.run(true, true, runnable);
//		} catch (InvocationTargetException | InterruptedException e) {
//			e.printStackTrace();
//		}	
//		
//	}

	private void esportaDati() {
		boolean scelta = DialogMessaggio.openQuestion("Conferma Dati di Fatturazione", "Vuoi confermare i dati di fatturazione?");
		if (scelta) {
			boolean salvataggio = controllerFatturazione.salvaDatiLogistica();
			if (salvataggio) {
				btnEsportaDati.setEnabled(false);
				esportazioneTerminata = true;
				setPageComplete(true);
			} else {
				DialogMessaggio.openError("Errore", "Errore nel salvataggio dei dati.");
			}
		}
	}

	@Override
	public void mostraRisultato() {
		controllerFatturazione.calcolaFatturazioneLogistica();
		caricaDati();
		btnEsportaDati.setEnabled(true);
	}

	public boolean isEsportazioneTerminata() {
		return esportazioneTerminata;
	}
	
	@Override
	public void copyDataToModel() {
		//DO NOTHING!		
	}
	
}
