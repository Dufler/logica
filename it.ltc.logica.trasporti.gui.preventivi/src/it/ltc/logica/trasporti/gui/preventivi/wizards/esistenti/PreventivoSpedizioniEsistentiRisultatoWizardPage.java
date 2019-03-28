package it.ltc.logica.trasporti.gui.preventivi.wizards.esistenti;

import java.text.DecimalFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.trasporti.controller.preventivi.EsitoSimulazione;
import it.ltc.logica.trasporti.controller.preventivi.PreventivoEsistentiController;
import it.ltc.logica.trasporti.gui.elements.spedizionemodel.TabellaSpedizioniModel;
import it.ltc.logica.trasporti.gui.preventivi.elements.TabellaTotali;

public class PreventivoSpedizioniEsistentiRisultatoWizardPage extends PaginaWizardRisultati {
	
	private static final String titolo = "Preventivo di costo e fatturazione per una spedizione gi\u00E0 esistente";
	private static final String descrizione = "Risultato del preventivo";
	
	private final PreventivoEsistentiController controllerPreventivo;
	
	protected final DecimalFormat formatEuro;
	
	private Composite container;
	
	private TabellaSpedizioniModel tableViewerDettaglio;
	private TabellaTotali tableViewerTotali;
	private Table tableDettaglio;
	private Table tableTotali;
	
	private Composite compositeDati;
	private Label lblTotaleSpedizioni;
	private Label lblTotaleColli;
	private Label lblTotalePezzi;
	private Label lblTotaleCosti;
	
	private Composite compositeDettaglio;
	private Composite compositeTotali;

	public PreventivoSpedizioniEsistentiRisultatoWizardPage() {
		super(titolo, descrizione, true);
		controllerPreventivo = PreventivoEsistentiController.getInstance();
		formatEuro = new DecimalFormat("#,##0.000 \u20AC");
	}

	@Override
	public void aggiungiElementiGrafici(Composite c) {
		container = c;
		container.setLayout(new GridLayout(1, false));
		
		compositeDettaglio = new Composite(container, SWT.NONE);
		compositeDettaglio.setLayout(new GridLayout(1, false));
		compositeDettaglio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			
		compositeDati = new Composite(container, SWT.NONE);
		compositeDati.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeDati.setLayout(new GridLayout(4, false));
		
		lblTotaleSpedizioni = new Label(compositeDati, SWT.NONE);
		lblTotaleSpedizioni.setText("Totale Spedizioni: ");
		
		lblTotaleColli = new Label(compositeDati, SWT.NONE);
		lblTotaleColli.setText("Totale Colli: ");
		
		lblTotalePezzi = new Label(compositeDati, SWT.NONE);
		lblTotalePezzi.setText("Totale Pezzi: ");
		
		lblTotaleCosti = new Label(compositeDati, SWT.NONE);
		lblTotaleCosti.setText("Totale Costi: ");
		
		compositeTotali = new Composite(container, SWT.NONE);
		compositeTotali.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		compositeTotali.setLayout(new GridLayout(1, false));
		
		Label lblTotale = new Label(compositeTotali, SWT.NONE);
		lblTotale.setText("Totale Costi e Ricavi: ");
		
		tableViewerTotali = new TabellaTotali(compositeTotali, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tableTotali = tableViewerTotali.getTable();
		tableTotali.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tableTotali.setBounds(0, 0, 564, 113);
		
	}
	
	@Override
	public void mostraRisultato() {
		EsitoSimulazione simulazione = controllerPreventivo.calcola();
		aggiornaTotaliStatistiche(simulazione.getSpedizioni(), simulazione.getColli(), simulazione.getPezzi(), simulazione.getTotaleCosti(), simulazione.isCostiPresenti());
		impostaTabella();
		tableViewerDettaglio.setElementi(simulazione.getCalcoli());
		tableViewerTotali.setElementi(simulazione.getTotali());
		container.layout();
	}
	
	protected void aggiornaTotaliStatistiche(int totaleSpedizioni, int totaleColli, int totalePezzi, double totaleCosti, boolean costiPresenti) {
		lblTotaleSpedizioni.setText("Totale Spedizioni: " + totaleSpedizioni);
		lblTotaleColli.setText("Totale Colli: " + totaleColli);
		lblTotalePezzi.setText("Totale Pezzi: " + totalePezzi);
		String costi = "Totale Costi: " + formatEuro.format(totaleCosti);
		if (!costiPresenti)
			costi += " (alcune spedizioni non hanno il costo)";
		lblTotaleCosti.setText(costi);
		compositeDati.layout();
	}

	private void impostaTabella() {
		tableTotali.removeAll();
		for (Control control : compositeDettaglio.getChildren()) {
	        control.dispose();
	    }
		tableViewerDettaglio = new TabellaSpedizioniModel(compositeDettaglio, controllerPreventivo.getListiniCliente(), controllerPreventivo.getListiniCorriere(), controllerPreventivo.getListiniSimulazione(), false);
		tableDettaglio = tableViewerDettaglio.getTable();
		tableDettaglio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeDettaglio.layout();
	}
	
	@Override
	public void copyDataToModel() {
		//DO NOTHING!		
	}
	
}
