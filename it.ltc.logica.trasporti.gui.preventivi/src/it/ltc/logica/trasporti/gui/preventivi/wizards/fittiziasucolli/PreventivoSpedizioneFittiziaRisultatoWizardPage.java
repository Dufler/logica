package it.ltc.logica.trasporti.gui.preventivi.wizards.fittiziasucolli;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.dialog.DialogSelezioneCartella;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.trasporti.controller.preventivi.PreventivoFittiziaController;
import it.ltc.logica.utilities.chart.DatiController;
import it.ltc.logica.utilities.chart.DatiGraficoSemplice;
import it.ltc.logica.utilities.chart.GraficoSWT;

public class PreventivoSpedizioneFittiziaRisultatoWizardPage extends PaginaWizardRisultati {
	
	private static final String titolo = "Preventivo di costo e fatturazione per una spedizione";
	private static final String descrizione = "Il risultato del preventivo.";
	
	private PreventivoFittiziaController controllerPreventivo;
	
	private Composite compositeGrafico;
	private GraficoSWT grafico;
	private DatiGraficoSemplice dati;

	public PreventivoSpedizioneFittiziaRisultatoWizardPage() {
		super(titolo, descrizione, true);
		controllerPreventivo = PreventivoFittiziaController.getInstance();
	}
	
	public void mostraRisultato() {
		dati = controllerPreventivo.calcolaSpedizioniFittizieSuColli();
		grafico.costruisciGraficoABarreConEuro(dati);
		compositeGrafico.layout();
	}
	
	private void esportaDati() {
		DialogSelezioneCartella dialog = new DialogSelezioneCartella();
		String path = dialog.open();
		String nomeFile = "Costi e ricavi su piu' colli";
		boolean risultato = DatiController.getInstance().esportaDatiGraficoSuExcel(path, nomeFile, dati);
		if (risultato) {
			DialogMessaggio.openInformation("Esportazione completata", "Esportazione completata!\r\nE' stato generato il file:\r\n" + nomeFile);
		} else {
			DialogMessaggio.openError("Errore nella esportazione", "Non \u00E8 stato possibile esportare i risultati.");
		}
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
		
		Composite compositeControlli = new Composite(container, SWT.NONE);
		compositeControlli.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		compositeControlli.setBounds(0, 0, 84, 35);
		compositeControlli.setLayout(new GridLayout(1, false));
		
		Button btnEsportaDati = new Button(compositeControlli, SWT.NONE);
		btnEsportaDati.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				esportaDati();
			}
		});
		btnEsportaDati.setBounds(0, 0, 75, 25);
		btnEsportaDati.setText("Esporta dati");
	}
	
	@Override
	public void copyDataToModel() {
		//DO NOTHING!		
	}
	
}
