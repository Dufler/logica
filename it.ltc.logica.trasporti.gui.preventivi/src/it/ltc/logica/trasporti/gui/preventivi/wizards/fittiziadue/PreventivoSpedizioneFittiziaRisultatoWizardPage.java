package it.ltc.logica.trasporti.gui.preventivi.wizards.fittiziadue;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.controller.preventivi.PreventivoFittiziaController;
import it.ltc.logica.trasporti.gui.elements.spedizionemodel.TabellaSpedizioniModel;

public class PreventivoSpedizioneFittiziaRisultatoWizardPage extends PaginaWizardRisultati {
	
	private static final String titolo = "Preventivo di costo e fatturazione per una spedizione";
	private static final String descrizione = "Il risultato del preventivo.";
	
	private PreventivoFittiziaController controllerPreventivo;
	
	private Composite compositeGrafico;
	private Table tableDettaglio;
	private TabellaSpedizioniModel tableViewerDettaglio;

	public PreventivoSpedizioneFittiziaRisultatoWizardPage() {
		super(titolo, descrizione, true);
		controllerPreventivo = PreventivoFittiziaController.getInstance();
	}
	
	public void mostraRisultato() {
		List<SpedizioneModel> calcoli = controllerPreventivo.calcolaSpedizioniFittizieSuColliDue();
		impostaTabella();
		tableViewerDettaglio.setElementi(calcoli);
		compositeGrafico.layout();
	}
	
//	private void esportaDati() {
//		DialogSelezioneCartella dialog = new DialogSelezioneCartella();
//		String path = dialog.open();
//		String 
//		String nomeFile = "Simulazione Fittizia ";
//		boolean risultato = DatiController.getInstance().esportaDatiGraficoSuExcel(path, nomeFile, dati);
//		if (risultato) {
//			DialogMessaggio.openInformation("Esportazione completata", "Esportazione completata!\r\nE' stato generato il file:\r\n" + nomeFile);
//		} else {
//			DialogMessaggio.openError("Errore nella esportazione", "Non \u00E8 stato possibile esportare i risultati.");
//		}
//	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		Label lblRisultato = new Label(container, SWT.NONE);
		lblRisultato.setText("Risultato:");
		
		compositeGrafico = new Composite(container, SWT.NONE);
		compositeGrafico.setLayout(new GridLayout(1, false));
		compositeGrafico.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite compositeControlli = new Composite(container, SWT.NONE);
		compositeControlli.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		compositeControlli.setBounds(0, 0, 84, 35);
		compositeControlli.setLayout(new GridLayout(1, false));
		
//		Button btnEsportaDati = new Button(compositeControlli, SWT.NONE);
//		btnEsportaDati.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				esportaDati();
//			}
//		});
//		btnEsportaDati.setBounds(0, 0, 75, 25);
//		btnEsportaDati.setText("Esporta dati");
	}
	
	@Override
	public void copyDataToModel() {
		//DO NOTHING!		
	}
	
	private void impostaTabella() {
		for (Control control : compositeGrafico.getChildren()) {
	        control.dispose();
	    }
		tableViewerDettaglio = new TabellaSpedizioniModel(compositeGrafico, controllerPreventivo.getListiniCliente(), controllerPreventivo.getListiniCorriere(), controllerPreventivo.getListiniSimulazione(), false);
		tableDettaglio = tableViewerDettaglio.getTable();
		tableDettaglio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeGrafico.layout();
	}
	
}
