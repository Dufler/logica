
package it.ltc.logica.cdg.gui.analisi.parts;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.cdg.gui.analisi.logic.ControllerAnalisiCDG;
import it.ltc.logica.cdg.gui.analisi.model.AnalisiCommessa;
import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.gui.composite.common.CompositeDate;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.utilities.chart.DatiGraficoSemplice;
import it.ltc.logica.utilities.chart.GraficoSWT;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class AnalisiPart {

	private final ControllerAnalisiCDG controllerAnalisi;
	private static final DecimalFormat formatEuro = new DecimalFormat("#,##0.000 \u20AC");

	private final boolean permesso;

	// nuova analisi
	private Button btnCalcolaCDG;
	private ComboBox<Sede> comboSede;
	private ComboBox<AnalisiCommessa> comboCommessa;

	private ScrolledComposite scrolledCompositeCDG;
	private Composite compositeCDG;
	private Composite compositeControlliCDG;
	private CompositeDate compositeDateCDG;
	private Composite compositeRisultatoCDG;
	private Composite compositeTotaliCDG;
	private Composite compositeFiltro;
	
	private Label lblTotaleCostiRealiCDG;
	private Label lblTotaleRicaviIpoteticiCDG;
	private Label lblTotaleScostamentoCDG;
	
	
	//Selezione e risultato
	private Sede sede;
	private Date inizio;
	private Date fine;
	private List<AnalisiCommessa> analisi;

	@Inject
	public AnalisiPart() {
		controllerAnalisi = ControllerAnalisiCDG.getInstance();
		permesso = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.CDG_ANALISI.getID());
	}

	@PostConstruct
	public void postConstruct(Composite parent) {

		scrolledCompositeCDG = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

		compositeCDG = new Composite(scrolledCompositeCDG, SWT.NONE);
		compositeCDG.setLayout(new GridLayout(1, false));

		scrolledCompositeCDG.setContent(compositeCDG);

		Label lblSelezionaUnaCommessaCDG = new Label(compositeCDG, SWT.NONE);
		lblSelezionaUnaCommessaCDG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblSelezionaUnaCommessaCDG.setText("Seleziona una sede e un periodo da esaminare: ");

		compositeControlliCDG = new Composite(compositeCDG, SWT.NONE);
		compositeControlliCDG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeControlliCDG.setLayout(new GridLayout(3, false));

		comboSede = new ComboBox<Sede>(compositeControlliCDG);
		comboSede.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboSede.setItems(ControllerSedi.getInstance().getSedi());

		compositeDateCDG = new CompositeDate(null, compositeControlliCDG, false);
		compositeDateCDG.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		compositeDateCDG.setA(new Date());
		compositeDateCDG.setDa(new Date());

		btnCalcolaCDG = new Button(compositeControlliCDG, SWT.NONE);
		btnCalcolaCDG.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Recupero la selezione.
				sede = comboSede.getSelectedValue();
				inizio = compositeDateCDG.getDaSoloGiorno();
				fine = compositeDateCDG.getASoloGiorno();
				if (sede == null || inizio == null || fine == null) {
					DialogMessaggio.openWarning("Selezione Incompleta",	"Per favore seleziona una sede e un intervallo di date prima di procedere con l'analisi.");
				} else {
					analisi = controllerAnalisi.elaboraRiepilogoControlloGestione(sede, inizio, fine);
					comboCommessa.setItems(analisi);
					elaboraAnalisiControlloCentriDiCostoRicavoPerSede(analisi);
				}
			}
		});
		btnCalcolaCDG.setText("Calcola");
		btnCalcolaCDG.setEnabled(permesso);

		compositeTotaliCDG = new Composite(compositeCDG, SWT.NONE);
		compositeTotaliCDG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeTotaliCDG.setSize(438, 85);
		compositeTotaliCDG.setLayout(new GridLayout(1, false));

		lblTotaleRicaviIpoteticiCDG = new Label(compositeTotaliCDG, SWT.NONE);
		lblTotaleRicaviIpoteticiCDG.setText("");

		lblTotaleCostiRealiCDG = new Label(compositeTotaliCDG, SWT.NONE);
		lblTotaleCostiRealiCDG.setText("");

		lblTotaleScostamentoCDG = new Label(compositeTotaliCDG, SWT.NONE);
		lblTotaleScostamentoCDG.setText("");
		
		compositeFiltro = new Composite(compositeCDG, SWT.NONE);
		compositeFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		compositeFiltro.setLayout(new GridLayout(4, false));
		
		Label lblFiltraPer = new Label(compositeFiltro, SWT.NONE);
		lblFiltraPer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFiltraPer.setBounds(0, 0, 55, 15);
		lblFiltraPer.setText("Filtra per: ");
		
		comboCommessa = new ComboBox<AnalisiCommessa>(compositeFiltro);
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		comboCommessa.setRequired(false);
		
		Button btnFiltra = new Button(compositeFiltro, SWT.NONE);
		btnFiltra.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AnalisiCommessa analisiCommessa = comboCommessa.getSelectedValue();
				if (analisiCommessa != null)
					elaboraAnalisiControlloCentriDiCostoRicavoPerCommessa(analisiCommessa);
				else
					DialogMessaggio.openWarning("Selezione Incompleta",	"Per favore seleziona una commessa prima di procedere con l'analisi.");
			}
		});
		btnFiltra.setText("Filtra");
		
		Button btnAnnullaFiltro = new Button(compositeFiltro, SWT.NONE);
		btnAnnullaFiltro.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comboCommessa.setSelectedValue(null);
				elaboraAnalisiControlloCentriDiCostoRicavoPerSede(analisi);
			}
		});
		btnAnnullaFiltro.setText("Annulla Filtro");
		
		compositeFiltro.setVisible(false);
		
		compositeRisultatoCDG = new Composite(compositeCDG, SWT.NONE);
		compositeRisultatoCDG.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeRisultatoCDG.setLayout(new GridLayout(1, false));

		// Calcolo la dimensione iniziale.
		scrolledCompositeCDG.setMinSize(compositeCDG.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledCompositeCDG.setExpandHorizontal(true);
		scrolledCompositeCDG.setExpandVertical(true);
	}
	
	private void ripulisciComposite() {
		for (Control control : compositeRisultatoCDG.getChildren())
			control.dispose();
	}
	
	private void aggiornaComposite() {
		compositeFiltro.setVisible(true);
		compositeRisultatoCDG.layout();

		Point nuovaDimensione = compositeCDG.computeSize(compositeControlliCDG.getSize().x, SWT.DEFAULT);
		scrolledCompositeCDG.setMinSize(nuovaDimensione);
		scrolledCompositeCDG.layout(true, true);
	}
	
	private void elaboraGrafico(String titolo, HashMap<String, Double> totaliRicavoPerNome, HashMap<String, Double> totaliCostoPerNome) {
		GraficoSWT grafico = new GraficoSWT(compositeRisultatoCDG, SWT.NONE);
		grafico.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		DatiGraficoSemplice dati = new DatiGraficoSemplice(titolo, "Aree", "Valore");
		//Aggiungo i totali al grafico
		for (String nome : totaliRicavoPerNome.keySet()) {
			double valore = totaliRicavoPerNome.get(nome);
			dati.aggiungiValore(valore, "Ricavo", nome);
		}
		for (String nome : totaliCostoPerNome.keySet()) {
			double valore = totaliCostoPerNome.get(nome);
			dati.aggiungiValore(valore, "Costo", nome);
		}
		grafico.costruisciGraficoABarreConEuro(dati);
	}
	
	private void elaboraTotali(HashMap<String, Double> totaliRicavoPerNome,	HashMap<String, Double> totaliCostoPerNome) {
		double totaleCosti = 0;
		double totaleRicavi = 0;
		// Aggiungo i totali al grafico
		for (String nome : totaliRicavoPerNome.keySet()) {
			double valore = totaliRicavoPerNome.get(nome);
			totaleRicavi += valore;
		}
		for (String nome : totaliCostoPerNome.keySet()) {
			double valore = totaliCostoPerNome.get(nome);
			totaleCosti += valore;
		}
		
		String risultato = totaleRicavi > totaleCosti ? "Utile : +" : "Perdita : -";
		lblTotaleCostiRealiCDG.setText("Totale Costi : " + formatEuro.format(totaleCosti));
		lblTotaleRicaviIpoteticiCDG.setText("Totale Ricavi : " +
		formatEuro.format(totaleRicavi));
		lblTotaleScostamentoCDG.setText(risultato + formatEuro.format(Math.abs(totaleRicavi - totaleCosti)));
	}
	
	private void elaboraMappaCosti(AnalisiCommessa analisiCommessa, HashMap<String, Double> totaliCostoPerNome) {
		HashMap<Integer, Double> costiPerFase = analisiCommessa.getRiepilogoCostiPeriodoPerFase();
		for (Integer idFase : costiPerFase.keySet()) {
			CdgFase fase = ControllerCdgFasi.getInstance().getFase(idFase);
			double totale = totaliCostoPerNome.containsKey(fase.getNome()) ? totaliCostoPerNome.get(fase.getNome()) : 0.0;
			totale += costiPerFase.get(idFase);
			totaliCostoPerNome.put(fase.getNome(), totale);
		}
	}
	
	private void eleboraMappaRicavi(AnalisiCommessa analisiCommessa, HashMap<String, Double> totaliRicavoPerNome) {
		HashMap<Integer, Double> ricaviPerFase = analisiCommessa.getRiepilogoRicaviPeriodoPerFase();
		for (Integer idFase : ricaviPerFase.keySet()) {
			CdgFase fase = ControllerCdgFasi.getInstance().getFase(idFase);
			double totale = totaliRicavoPerNome.containsKey(fase.getNome()) ? totaliRicavoPerNome.get(fase.getNome()) : 0.0;
			totale += ricaviPerFase.get(idFase);
			totaliRicavoPerNome.put(fase.getNome(), totale);
		}
	}
	
	private void elaboraAnalisiControlloCentriDiCostoRicavoPerSede(List<AnalisiCommessa> analisi) {
		// Ripulisco tutto
		ripulisciComposite();

		// Preparo i contenitori dei totali
		String titolo = "Andamento " + sede.getSede();
		HashMap<String, Double> totaliRicavoPerNome = new HashMap<>();
		HashMap<String, Double> totaliCostoPerNome = new HashMap<>();

		// Calcolo il riepilogo per giorno
		for (AnalisiCommessa analisiCommessa : analisi) {
			elaboraMappaCosti(analisiCommessa, totaliCostoPerNome);
			eleboraMappaRicavi(analisiCommessa, totaliRicavoPerNome);
		}

		// Elaboro il grafico
		elaboraGrafico(titolo, totaliRicavoPerNome, totaliCostoPerNome);

		// Elaboro i totali
		elaboraTotali(totaliRicavoPerNome, totaliCostoPerNome);

		// Aggiorno i composite
		aggiornaComposite();
	}
	
	private void elaboraAnalisiControlloCentriDiCostoRicavoPerCommessa(AnalisiCommessa analisiCommessa) {
		// Ripulisco tutto
		ripulisciComposite();
					
		//Preparo i contenitori dei totali
		String titolo = "Andamento " + analisiCommessa.getCommessa().getNome();
		HashMap<String, Double> totaliRicavoPerNome = new HashMap<>();
		HashMap<String, Double> totaliCostoPerNome = new HashMap<>();
		
		// Calcolo il riepilogo per giorno
		elaboraMappaCosti(analisiCommessa, totaliCostoPerNome);
		eleboraMappaRicavi(analisiCommessa, totaliRicavoPerNome);
		
		//Elaboro il grafico
		elaboraGrafico(titolo, totaliRicavoPerNome, totaliCostoPerNome);
		
		//Elaboro i totali
		elaboraTotali(totaliRicavoPerNome, totaliCostoPerNome);
		
		//Aggiorno i composite
		aggiornaComposite();
	}
}