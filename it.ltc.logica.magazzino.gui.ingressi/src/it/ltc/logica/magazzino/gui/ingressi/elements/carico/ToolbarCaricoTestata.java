package it.ltc.logica.magazzino.gui.ingressi.elements.carico;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;

import it.ltc.logica.common.controller.ingressi.ControllerCarichiTipi;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.StatiCarico;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTipo;
import it.ltc.logica.gui.common.combobox.ComboCommesse;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarCaricoTestata extends ToolbarCRUDConFiltro<TabellaCaricoTestata, CaricoTestata, CriteriFiltraggioCaricoTestata> {

	private ComboBox<Commessa> comboCommessa;
	private ComboBox<StatiCarico> comboStato;
	private ComboBox<CaricoTipo> comboTipo;
	
	private DateField dataDa;
	private DateField dataA;
	
	public ToolbarCaricoTestata(Composite parent) {
		super(parent);
	}

	@Override
	protected void setupFiltri(Composite composite) {
		composite.setLayout(new GridLayout(2, false));
		
		Composite compositeCommessa = new Composite(composite, SWT.NONE);
		compositeCommessa.setLayout(new GridLayout(1, false));
		
		Label labelCommessa = new Label(compositeCommessa, SWT.NONE);
		labelCommessa.setText("Seleziona la commessa: ");
		
		comboCommessa = new ComboCommesse(compositeCommessa, false);
		comboCommessa.setItems(ControllerCommesse.getInstance().getCommesse());
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selezioneCommessa();
			}
		});
		
		Composite compositeFiltri = new Composite(composite, SWT.NONE);
		compositeFiltri.setLayout(new GridLayout(6, false));
		
		Label labelFiltri = new Label(compositeFiltri, SWT.NONE);
		labelFiltri.setText("Filtra per: 		");
		
		new SpacerLabel(compositeFiltri, SpacerLabel.LARGE);
		
		new SpacerLabel(compositeFiltri, SpacerLabel.LARGE);
		
		Label labelDataDa = new Label(compositeFiltri, SWT.NONE);
		labelDataDa.setText("Da:");
		
		new SpacerLabel(compositeFiltri);
		
		Label labelDataA = new Label(compositeFiltri, SWT.NONE);
		labelDataA.setText("A:");
		
		aggiungiCampoDiTestoGenericoPerFiltro(compositeFiltri);
		filterText.setEnabled(false);
		
		comboStato = new ComboBox<>(compositeFiltri);
		List<StatiCarico> statiPossibili = new LinkedList<>();
		statiPossibili.add(StatiCarico.ARRIVATO);
		statiPossibili.add(StatiCarico.IN_LAVORAZIONE);
		comboStato.setItems(statiPossibili);
		comboStato.setEnabled(false);
		comboStato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboStato.setRequired(true);
		comboStato.setSelectedValue(StatiCarico.ARRIVATO);
		
		comboTipo = new ComboBox<>(compositeFiltri);
		comboTipo.setEnabled(false);
		comboTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTipo.setRequired(false);
		
		dataDa = new DateField(compositeFiltri, true);
		dataDa.setEnabled(false);
		dataDa.setRequired(false);
		
		new SpacerLabel(compositeFiltri);
		
		dataA = new DateField(compositeFiltri, true);
		dataA.setEnabled(false);
		dataA.setRequired(false);
	}
	
	@Override
	protected void creaCompositeDx() {
		compositeDx = new Composite(this, SWT.NONE);
		compositeDx.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		compositeDx.setLayout(new GridLayout(1, false));
		
		new SpacerLabel(compositeDx);
		
		toolbar = new ToolBar(compositeDx, SWT.FLAT | SWT.RIGHT);
		toolbar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
	}

	@Override
	protected CriteriFiltraggioCaricoTestata getCriteriDiFiltraggio() {
		CriteriFiltraggioCaricoTestata criteri = new CriteriFiltraggioCaricoTestata();
		criteri.setCommessa(comboCommessa.getSelectedValue());
		criteri.setRiferimento(filterText.getText());
		criteri.setStato(comboStato.getSelectedValue());
		criteri.setTipo(comboTipo.getSelectedValue());
		criteri.setDa(dataDa.getSimpleStartValue());
		criteri.setA(dataA.getSimpleEndValue());
		return criteri;
	}
	
	protected void selezioneCommessa() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		tabella.setCommessa(commessaSelezionata);
		boolean enable = commessaSelezionata != null;
		comboStato.setEnabled(enable);
		comboTipo.setEnabled(enable);
		filterText.setEnabled(enable);
		dataDa.setEnabled(enable);
		dataA.setEnabled(enable);
		if (enable) {
			comboTipo.setItems(ControllerCarichiTipi.getInstance().getTipi(comboCommessa.getSelectedValue()));
		} else {
			comboStato.setSelectedValue(null);
			comboTipo.setSelectedValue(null);
			filterText.setText("");
		}
	}

	@Override
	protected void resetCampiFiltri() {
		//Tolgo la selezione della commessa
		comboCommessa.setSelectedValue(null);
		//Ripulisco i filtri specifici
		comboStato.setSelectedValue(null);
		comboTipo.setSelectedValue(null);
		dataDa.resetValue();
		dataA.resetValue();
		filterText.setText("");
		//Disabilito
		comboStato.setEnabled(false);
		comboTipo.setEnabled(false);
		filterText.setEnabled(false);
		dataDa.setEnabled(false);
		dataA.setEnabled(false);
		//esprimo la deselezione
		selezioneCommessa();
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_INGRESSI.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.UFFICIO_INGRESSI_ELIMINA.getID();
	}

	@Override
	protected void setupTastiToolbar() {		
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		//aggiungiSeparatore();
		//aggiungiTastoNuovo();
		//aggiungiTastoModifica();
		//aggiungiTastoElimina(); //Non rendo disponibile questa funzione qui ma solo da menù per certi utenti.
	}

}
