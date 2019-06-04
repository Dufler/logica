package it.ltc.logica.ufficio.gui.uscite.elements;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.uscite.ControllerOrdineTipi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.database.model.centrale.ordini.OrdineTipo;
import it.ltc.logica.database.model.centrale.ordini.StatiOrdine;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.gui.wizard.DialogWizard;
import it.ltc.logica.ufficio.gui.uscite.wizard.assegna.AssegnazioneOrdiniWizard;
import it.ltc.logica.ufficio.gui.uscite.wizard.finalizza.FinalizzaOrdiniWizard;
import it.ltc.logica.ufficio.gui.uscite.wizard.generamovimenti.GeneraMovimentiOrdiniWizard;
import it.ltc.logica.ufficio.gui.uscite.wizard.generaspedizioni.GeneraDatiSpedizioniWizard;
import it.ltc.logica.ufficio.gui.uscite.wizard.raggruppaspedizioni.RaggruppaSpedizioniWizard;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarOrdineTestata extends ToolbarCRUDConFiltro<TabellaOrdineTestata, OrdineTestata, CriteriFiltraggioOrdineTestata> {
	
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<StatiOrdine> comboStato;
	private ComboBox<OrdineTipo> comboTipo;
	
	private DateField dataDa;
	private DateField dataA;
	
	protected ToolItem finalizza;
	protected ToolItem assegna;
	protected ToolItem generaMovimenti;
	protected ToolItem generaDatiSpedizione;
	protected ToolItem raggruppaSpedizioni;

	public ToolbarOrdineTestata(Composite parent) {
		super(parent);
	}

	@Override
	protected void setupFiltri(Composite composite) {
		composite.setLayout(new GridLayout(2, false));
		
		Composite compositeCommessa = new Composite(composite, SWT.NONE);
		compositeCommessa.setLayout(new GridLayout(1, false));
		
		Label labelCommessa = new Label(compositeCommessa, SWT.NONE);
		labelCommessa.setText("Seleziona la commessa: ");
		
		comboCommessa = new ComboBox<>(compositeCommessa);
		comboCommessa.setItems(ControllerCommesse.getInstance().getCommesse());
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selezionaCommessa();
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
		comboStato.setItems(StatiOrdine.values());
		comboStato.setEnabled(false);
		comboStato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboStato.setRequired(false);
		
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
	protected CriteriFiltraggioOrdineTestata getCriteriDiFiltraggio() {
		CriteriFiltraggioOrdineTestata criteri = new CriteriFiltraggioOrdineTestata();
		criteri.setCommessa(comboCommessa.getSelectedValue());
		criteri.setRiferimento(filterText.getText());
		criteri.setStato(comboStato.getSelectedValue());
		criteri.setTipo(comboTipo.getSelectedValue());
		criteri.setDa(dataDa.getSimpleStartValue());
		criteri.setA(dataA.getSimpleEndValue());
		return criteri;
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
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_USCITE.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.UFFICIO_USCITE_ELIMINA.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		aggiungiSeparatore();
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();
		aggiungiSeparatore();
		
		//Aggiungo i tasti per la finalizzazione e l'assegnazione
		finalizza = new ToolItem(toolbar, SWT.NONE);
		finalizza.setImage(Immagine.FRECCIABLUSU_16X16.getImage());
		finalizza.setText("");
		finalizza.setToolTipText("Finalizza ordini");
		finalizza.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriFinalizza();
			}
		});
		
		assegna = new ToolItem(toolbar, SWT.NONE);
		assegna.setImage(Immagine.FRECCIAVERDESU_16X16.getImage());
		assegna.setText("");
		assegna.setToolTipText("Assegna ordini");
		assegna.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriAssegna();
			}
		});
		
		generaMovimenti = new ToolItem(toolbar, SWT.NONE);
		generaMovimenti.setImage(Immagine.FRECCIAGIALLAGIU_16X16.getImage());
		generaMovimenti.setText("");
		generaMovimenti.setToolTipText("Genera movimenti ordini");
		generaMovimenti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriGeneraMovimenti();
			}
		});
		
		generaDatiSpedizione = new ToolItem(toolbar, SWT.NONE);
		generaDatiSpedizione.setImage(Immagine.FRECCIAROSSAGIU_16X16.getImage());
		generaDatiSpedizione.setText("");
		generaDatiSpedizione.setToolTipText("Genera dati spedizioni");
		generaDatiSpedizione.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriGeneraDatiSpedizioni();
			}
		});
		
		raggruppaSpedizioni = new ToolItem(toolbar, SWT.NONE);
		raggruppaSpedizioni.setImage(Immagine.FRECCEROSSEGIU_16X16.getImage());
		raggruppaSpedizioni.setText("");
		raggruppaSpedizioni.setToolTipText("raggruppa spedizioni");
		raggruppaSpedizioni.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriRaggruppaSpedizioni();
			}
		});
	}
	
	private void selezionaCommessa() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		tabella.setCommessa(commessaSelezionata);
		boolean enable = commessaSelezionata != null;
		comboStato.setEnabled(enable);
		comboTipo.setEnabled(enable);
		filterText.setEnabled(enable);
		dataDa.setEnabled(enable);
		dataA.setEnabled(enable);
		if (enable) {			
			comboTipo.setItems(ControllerOrdineTipi.getInstance().getTipi(comboCommessa.getSelectedValue()));
		} else {
			comboStato.setSelectedValue(null);
			comboTipo.setSelectedValue(null);
			filterText.setText("");
		}
	}
	
	private void apriFinalizza() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		if (commessaSelezionata != null) {
			DialogWizard dialog = new DialogWizard(new FinalizzaOrdiniWizard(commessaSelezionata));
			int returnCode = dialog.open();
			if (returnCode == Window.OK) {
				tabella.aggiornaContenuto();
			}
		} else {
			DialogMessaggio.openWarning("Selezione commessa", "Va selezionata una commessa.");
		}	
	}
	
	private void apriAssegna() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		if (commessaSelezionata != null) {
			DialogWizard dialog = new DialogWizard(new AssegnazioneOrdiniWizard(commessaSelezionata));
			int returnCode = dialog.open();
			if (returnCode == Window.OK) {
				tabella.aggiornaContenuto();
			}
		} else {
			DialogMessaggio.openWarning("Selezione commessa", "Va selezionata una commessa.");
		}
	}
	
	private void apriGeneraMovimenti() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		if (commessaSelezionata != null) {
			DialogWizard dialog = new DialogWizard(new GeneraMovimentiOrdiniWizard(commessaSelezionata));
			int returnCode = dialog.open();
			if (returnCode == Window.OK) {
				tabella.aggiornaContenuto();
			}
		} else {
			DialogMessaggio.openWarning("Selezione commessa", "Va selezionata una commessa.");
		}
	}
	
	private void apriGeneraDatiSpedizioni() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		if (commessaSelezionata != null) {
			DialogWizard dialog = new DialogWizard(new GeneraDatiSpedizioniWizard(commessaSelezionata));
			int returnCode = dialog.open();
			if (returnCode == Window.OK) {
				tabella.aggiornaContenuto();
			}
		} else {
			DialogMessaggio.openWarning("Selezione commessa", "Va selezionata una commessa.");
		}
	}
	
	private void apriRaggruppaSpedizioni() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		if (commessaSelezionata != null) {
			DialogWizard dialog = new DialogWizard(new RaggruppaSpedizioniWizard(commessaSelezionata));
			int returnCode = dialog.open();
			if (returnCode == Window.OK) {
				tabella.aggiornaContenuto();
			}
		} else {
			DialogMessaggio.openWarning("Selezione commessa", "Va selezionata una commessa.");
		}
	}

}
