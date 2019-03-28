package it.ltc.logica.trasporti.gui.report.wizards.semplice;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.trasporti.controller.ReportController;
import it.ltc.logica.trasporti.controller.ReportElement;
import it.ltc.logica.trasporti.gui.report.elements.TabellaInfoSemplici;

public class StatisticheSempliciSpedizioniRisultatiWizardPage extends PaginaWizardRisultati {
	
	private static final String titolo = "Statistiche sulle spedizioni";
	private static final String descrizione = "Qui puoi visionare ed esportare i dati elaborati.";
	
	private ReportController controller;
	
	private List<ReportElement> report;
	
	private TabellaInfoSemplici tabella;	

	protected StatisticheSempliciSpedizioniRisultatiWizardPage() {
		super(titolo, descrizione, true);
		controller = ReportController.getInstance();
		setPageComplete(true);
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		Label lblRisultato = new Label(container, SWT.NONE);
		lblRisultato.setText("Risultato:");
		
		tabella = new TabellaInfoSemplici(container);
		
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
		tabella.copiaContenuto();
	}

	@Override
	public void mostraRisultato() {
		report = controller.calcolaReportSemplice();
		tabella.setCriteri(controller.getCriteriSelezionati());
		tabella.setElementi(report);
	}
	
	@Override
	public void copyDataToModel() {
		//DO NOTHING!		
	}

}
