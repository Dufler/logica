package it.ltc.logica.ufficio.gui.uscite.elements.assegnazione;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneOrdine;
import it.ltc.logica.gui.composite.GruppoSemplice;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.ufficio.gui.uscite.reports.ReportListaNonDisponibili;
import it.ltc.logica.ufficio.gui.uscite.reports.ReportListaNonUbicati;
import it.ltc.logica.ufficio.gui.uscite.reports.ReportListaPrelievo;
import it.ltc.logica.ufficio.gui.uscite.reports.ReportListaScorte;

public class CompositeControlliAssegnazione extends GruppoSemplice {
	
	private final Commessa commessa;
	//private List<RisultatoAssegnazioneOrdine> risultati;
	
	private List<RisultatoAssegnazioneOrdine> listePrelievo;
	private List<RisultatoAssegnazioneOrdine> listeScorta;
	private List<RisultatoAssegnazioneOrdine> listeNonUbicate;
	private List<RisultatoAssegnazioneOrdine> listeNonDisponibili;
	
	private Button btnListaPrelievo;
	private Button btnListaScorte;
	private Button btnListaNonUbicati;
	private Button btnProdottiMancanti;
	private Button btnStampaAutomatica;
	
	private boolean risultatoVisualizzato;

	public CompositeControlliAssegnazione(Commessa commessa, ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent);
		this.commessa = commessa;
	}
	
	public void setRisultati(List<RisultatoAssegnazioneOrdine> risultati) {
		//this.risultati = risultati;
		//Setup delle variabili
		boolean prelievo = false;
		boolean scorte = false;
		boolean nonUbicati = false;
		boolean nonPresenti = false;
		listePrelievo = new LinkedList<>();
		listeScorta = new LinkedList<>();
		listeNonUbicate = new LinkedList<>();
		listeNonDisponibili = new LinkedList<>();
		for (RisultatoAssegnazioneOrdine risultato : risultati) {
			//liste specifiche
			if (risultato.isPrelevabile()) {
				prelievo = true;
				listePrelievo.add(risultato);
			}
			if (risultato.isAScorta()) {
				scorte = true;
				listeScorta.add(risultato);
			}
			if (risultato.isNonUbicato()) {
				nonUbicati = true;
				listeNonUbicate.add(risultato);
			}
			if (risultato.isNonPresente()) {
				nonPresenti = true;
				listeNonDisponibili.add(risultato);
			}			
		}
		//Impostazione dei controlli
		btnListaPrelievo.setEnabled(prelievo);
		btnListaScorte.setEnabled(scorte);
		btnListaNonUbicati.setEnabled(nonUbicati);
		btnProdottiMancanti.setEnabled(nonPresenti);
	}
	
	@Override
	protected boolean validazioneSpecifica(boolean valid) {
		return risultatoVisualizzato;
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(4, false));
		
		btnListaPrelievo = new Button(this, SWT.NONE);
		btnListaPrelievo.setEnabled(false);
		btnListaPrelievo.setText("Lista Prelievo");
		btnListaPrelievo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stampaListePrelievo();
			}
		});
		
		btnListaScorte = new Button(this, SWT.NONE);
		btnListaScorte.setEnabled(false);
		btnListaScorte.setText("Lista Scorte");
		btnListaScorte.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stampaListaScorte();
			}
		});
		
		btnListaNonUbicati = new Button(this, SWT.NONE);
		btnListaNonUbicati.setEnabled(false);
		btnListaNonUbicati.setText("Lista Non Ubicati");
		btnListaNonUbicati.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stampaListaNonUbicati();
			}
		});
		
		btnProdottiMancanti = new Button(this, SWT.NONE);
		btnProdottiMancanti.setEnabled(false);
		btnProdottiMancanti.setText("Prodotti non disponibili");
		btnProdottiMancanti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stampaListaNonDisponibili();
			}
		});

		btnStampaAutomatica = new Button(this, SWT.CHECK);
		btnStampaAutomatica.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		btnStampaAutomatica.setText("Stampa automatica");
	}
	
	protected void visualizzaReport(String exportPath) {
		boolean stampa = btnStampaAutomatica.getSelection();
		if (exportPath != null) {
			if (stampa) {
				//PRINT
			} else {
				Program.launch(exportPath);
			}
		} else {
			DialogMessaggio.openError("Errore durante la generazione del report", "Impossibile visualizzare/stampare il report perch\u00E8 non \u00E8 stato generato.");
		}
	}
	
	protected void stampaListePrelievo() {
		//genero un file pdf dal nome univoco e lo apro/stampo 
		ReportListaPrelievo report = new ReportListaPrelievo();
		for (RisultatoAssegnazioneOrdine lista : listePrelievo) {
			String exportPath = report.creaReport(commessa, lista);
			visualizzaReport(exportPath);
		}
		//Notifico che è possibile chiudere il wizard ora.
		risultatoVisualizzato = true;
		validate();
	}
	
	protected void stampaListaScorte() {
		//genero un file pdf dal nome univoco e lo apro/stampo 
		ReportListaScorte report = new ReportListaScorte();
		String exportPath = report.creaReport(commessa, listeScorta);
		visualizzaReport(exportPath);
	}
	
	protected void stampaListaNonUbicati() {
		//genero un file pdf dal nome univoco e lo apro/stampo 
		ReportListaNonUbicati report = new ReportListaNonUbicati();
		String exportPath = report.creaReport(commessa, listeNonUbicate);
		visualizzaReport(exportPath);
	}
	
	protected void stampaListaNonDisponibili() {
		//genero un file pdf dal nome univoco e lo apro/stampo 
		ReportListaNonDisponibili report = new ReportListaNonDisponibili();
		String exportPath = report.creaReport(commessa, listeNonDisponibili);
		visualizzaReport(exportPath);
	}

}
