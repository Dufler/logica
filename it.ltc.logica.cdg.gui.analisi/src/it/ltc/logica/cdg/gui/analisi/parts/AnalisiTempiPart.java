
package it.ltc.logica.cdg.gui.analisi.parts;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.cdg.gui.analisi.elements.RiepilogoTempiComposite;
import it.ltc.logica.cdg.gui.analisi.logic.ControllerAnalisiCDG;
import it.ltc.logica.cdg.gui.analisi.model.RiepilogoGiornalieroCDG;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.gui.composite.common.CompositeDate;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class AnalisiTempiPart {
	
	private static final DecimalFormat formatEuro = new DecimalFormat("#,##0.000 \u20AC");
	
	private final boolean permesso;
	private final ControllerAnalisiCDG controllerAnalisi;
	

	private Button btnCalcola;
	private ComboBox<Commessa> comboCommessa;

	private ScrolledComposite scrolledCompositeTempi;
	private Composite compositeTempi;
	private Composite compositeControlli;
	private CompositeDate compositeDate;
	private Composite compositeRisultato;
	private Composite compositeTotali;

	private Label lblTotaleCostiReali;
	private Label lblTotaleCostiIpotetici;
	private Label lblTotaleScostamento;
	
	@Inject
	public AnalisiTempiPart() {
		controllerAnalisi = ControllerAnalisiCDG.getInstance();
		permesso = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.CDG_ANALISI.getID());
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		scrolledCompositeTempi = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	
		compositeTempi = new Composite(scrolledCompositeTempi, SWT.NONE);
		compositeTempi.setLayout(new GridLayout(1, false));

		scrolledCompositeTempi.setContent(compositeTempi);

		Label lblSelezionaUnaCommessa = new Label(compositeTempi, SWT.NONE);
		lblSelezionaUnaCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblSelezionaUnaCommessa.setText("Seleziona una commessa e un periodo da esaminare: ");

		compositeControlli = new Composite(compositeTempi, SWT.NONE);
		compositeControlli.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeControlli.setLayout(new GridLayout(3, false));

		comboCommessa = new ComboBox<Commessa>(compositeControlli);
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());

		compositeDate = new CompositeDate(null, compositeControlli, false);
		compositeDate.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		compositeDate.setA(new Date());
		compositeDate.setDa(new Date());

		btnCalcola = new Button(compositeControlli, SWT.NONE);
		btnCalcola.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				elaboraAnalisiControlloTempi();
			}
		});
		btnCalcola.setText("Calcola");
		btnCalcola.setEnabled(permesso);

		compositeTotali = new Composite(compositeTempi, SWT.NONE);
		compositeTotali.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeTotali.setSize(438, 85);
		compositeTotali.setLayout(new GridLayout(1, false));

		// lblTotaleRicaviIpotetici = new Label(compositeTotali, SWT.NONE);
		// lblTotaleRicaviIpotetici.setText("");

		lblTotaleCostiIpotetici = new Label(compositeTotali, SWT.NONE);
		lblTotaleCostiIpotetici.setText("");

		lblTotaleCostiReali = new Label(compositeTotali, SWT.NONE);
		lblTotaleCostiReali.setText("");
		lblTotaleCostiReali.setToolTipText("I costi extra sono stati eliminati da questo totale.");

		lblTotaleScostamento = new Label(compositeTotali, SWT.NONE);
		lblTotaleScostamento.setText("");

		compositeRisultato = new Composite(compositeTempi, SWT.NONE);
		compositeRisultato.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		RowLayout rowLayout = new RowLayout();
		rowLayout.wrap = true;
		rowLayout.pack = true;
		rowLayout.justify = false;
		rowLayout.type = SWT.HORIZONTAL;
		compositeRisultato.setLayout(rowLayout);

		// Calcolo la dimensione iniziale.
		scrolledCompositeTempi.setMinSize(compositeTempi.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledCompositeTempi.setExpandHorizontal(true);
		scrolledCompositeTempi.setExpandVertical(true);
	}
	
	private void elaboraAnalisiControlloTempi() {
		// Recupero la selezione.
		Commessa commessa = comboCommessa.getSelectedValue();
		Date inizio = compositeDate.getDaSoloGiorno();
		Date fine = compositeDate.getASoloGiorno();
		if (commessa == null || inizio == null || fine == null) {
			DialogMessaggio.openWarning("Selezione Incompleta",
					"Per favore seleziona una commessa e un intervallo di date prima di procedere con l'analisi.");
		} else {
			// Ripulisco tutto
			for (Control control : compositeRisultato.getChildren())
				control.dispose();
			// Calcolo il riepilogo per giorno
			HashMap<Integer, RiepilogoGiornalieroCDG> mappaPerGiorno = controllerAnalisi.elaboraRiepilogoControlloTempi(commessa, inizio, fine);
			// Lo mostro a schermo
			double totaleCostiReali = 0;
			double totaleCostiExtra = 0;
			double totaleCostiIpotetici = 0;
			// double totaleRicaviIpotetici = 0;
			for (RiepilogoGiornalieroCDG riepilogo : mappaPerGiorno.values()) {
				RiepilogoTempiComposite composite = new RiepilogoTempiComposite(compositeRisultato, riepilogo);
				composite.layout();
				totaleCostiReali += riepilogo.getCostiReali();
				/*Fix sugli extra*/
				totaleCostiExtra += riepilogo.getCostiExtra();
				/*fine fix*/
				totaleCostiIpotetici += riepilogo.getCostiIpotetici();
				// totaleRicaviIpotetici += riepilogo.getRicaviIpotetici();
			}
			/*Fix sugli extra*/
			totaleCostiReali -= totaleCostiExtra;
			/*fine fix*/
			String risultato = totaleCostiIpotetici > totaleCostiReali ? "Utile : +" : "Perdita : -";
			String scostamentoExtra = ", un totale di " + formatEuro.format(totaleCostiExtra) + " \u00E8 stato identificato come extra ed \u00E8 stato rimosso dai costi reali subiti.";
			lblTotaleCostiReali.setText("Totale Costi Reali: " + formatEuro.format(totaleCostiReali));
			lblTotaleCostiIpotetici.setText("Totale Costi Ipotetici: " + formatEuro.format(totaleCostiIpotetici));
			// lblTotaleRicaviIpotetici.setText("Totale Ricavi Ipotetici: " +
			// formatEuro.format(totaleRicaviIpotetici));
			lblTotaleScostamento.setText(risultato + formatEuro.format(Math.abs(totaleCostiIpotetici - totaleCostiReali)) + scostamentoExtra);

			// Calcolo la nuova dimensione e la imposto come minima, in seguito ridisegno
			// tutto.

			Point nuovaDimensione = compositeTempi.computeSize(compositeControlli.getSize().x, SWT.DEFAULT);
			scrolledCompositeTempi.setMinSize(nuovaDimensione);
			scrolledCompositeTempi.layout(true, true);
		}
	}

}