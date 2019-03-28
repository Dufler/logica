package it.ltc.logica.trasporti.gui.report.wizards.pesovolume;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.dialog.DialogSelezioneCartella;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.trasporti.controller.ReportController;
import it.ltc.logica.trasporti.controller.ReportElement;
import it.ltc.logica.utilities.chart.DatiGraficoSemplice;
import it.ltc.logica.utilities.chart.GraficoSWT;

public class RisultatiWizardPage extends PaginaWizardRisultati {
	
	private static final String titolo = "Statistiche sulle spedizioni";
	private static final String descrizione = "Qui puoi visionare ed esportare i dati elaborati.";
	
	private ReportController controller;
	
	private List<ReportElement> report;

	private Composite compositeGrafico;
	private GraficoSWT grafico;
	
	protected RisultatiWizardPage() {
		super(titolo, descrizione, true);
		controller = ReportController.getInstance();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		Label lblRisultato = new Label(container, SWT.NONE);
		lblRisultato.setText("Risultato:");
		
		compositeGrafico = new Composite(container, SWT.NONE);
		compositeGrafico.setLayout(new GridLayout(1, false));
		compositeGrafico.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		grafico = new GraficoSWT(compositeGrafico, SWT.NONE);
		grafico.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Button btnEsportaDati = new Button(container, SWT.NONE);
		btnEsportaDati.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				esportaDati();
			}
		});
		btnEsportaDati.setText("Esporta Dati");

	}
	
	private void esportaDati() {
		DialogSelezioneCartella dialog = new DialogSelezioneCartella();
		String path = dialog.open();
		if (path != null && !path.isEmpty())
			controller.esportaDati(path, report);
	}

	@Override
	public void mostraRisultato() {
		DatiGraficoSemplice dati = controller.calcolaDatiPesoVolume();
		grafico.costruisciGraficoABarreConPercentuali(dati);
		compositeGrafico.layout();
	}
	
	@Override
	public void copyDataToModel() {
		//DO NOTHING!		
	}

}
