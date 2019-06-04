package it.ltc.logica.ufficio.gui.uscite.elements.spedizione;

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
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.database.model.centrale.ordini.StatiSpedizione;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarDatiSpedizione extends ToolbarCRUDConFiltro<TabellaDatiSpedizioni, DatiSpedizione, CriteriFiltroDatiSpedizione> {
	
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<StatiSpedizione> comboStato;
	private DateField dataDa;
	private DateField dataA;
	
	protected ToolItem abilita;
	protected ToolItem delivery;

	public ToolbarDatiSpedizione(Composite parent) {
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
		compositeFiltri.setLayout(new GridLayout(5, false));
		
		Label labelFiltri = new Label(compositeFiltri, SWT.NONE);
		labelFiltri.setText("Filtra per: 		");
		
		new SpacerLabel(compositeFiltri, SpacerLabel.LARGE);
		
		Label labelDataDa = new Label(compositeFiltri, SWT.NONE);
		labelDataDa.setText("Da:");
		
		new SpacerLabel(compositeFiltri);
		
		Label labelDataA = new Label(compositeFiltri, SWT.NONE);
		labelDataA.setText("A:");
		
		aggiungiCampoDiTestoGenericoPerFiltro(compositeFiltri);
		filterText.setEnabled(false);
		
		comboStato = new ComboBox<>(compositeFiltri);
		comboStato.setItems(StatiSpedizione.values());
		comboStato.setEnabled(false);
		comboStato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboStato.setRequired(false);
		
		dataDa = new DateField(compositeFiltri, true);
		dataDa.setEnabled(false);
		dataDa.setRequired(false);
		
		new SpacerLabel(compositeFiltri);
		
		dataA = new DateField(compositeFiltri, true);
		dataA.setEnabled(false);
		dataA.setRequired(false);
	}

	@Override
	protected CriteriFiltroDatiSpedizione getCriteriDiFiltraggio() {
		CriteriFiltroDatiSpedizione criteri = new CriteriFiltroDatiSpedizione(filterText.getText(), comboStato.getSelectedValue(), dataDa.getSimpleStartValue(), dataA.getSimpleEndValue());
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		//Tolgo la selezione della commessa
		comboCommessa.setSelectedValue(null);
		//Ripulisco i filtri specifici
		comboStato.setSelectedValue(null);
		dataDa.resetValue();
		dataA.resetValue();
		filterText.setText("");
		//Disabilito
		comboStato.setEnabled(false);
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
	
	protected void creaCompositeDx() {
		compositeDx = new Composite(this, SWT.NONE);
		compositeDx.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		compositeDx.setLayout(new GridLayout(1, false));
		
		new SpacerLabel(compositeDx);
		
		toolbar = new ToolBar(compositeDx, SWT.FLAT | SWT.RIGHT);
		toolbar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		//Aggiungo i tasti per l'abilitazione e la stampa delivery
		abilita = new ToolItem(toolbar, SWT.NONE);
		abilita.setImage(Immagine.SPUNTAVERDE_16X16.getImage());
		abilita.setText("");
		abilita.setToolTipText("Abilita");
		abilita.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriAbilita();
			}
		});
		
		delivery = new ToolItem(toolbar, SWT.NONE);
		delivery.setImage(Immagine.REPORT_16X16.getImage());
		delivery.setText("");
		delivery.setToolTipText("Stampa Delivery");
		delivery.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriStampaDelivery();
			}
		});
	}
	
	private void selezionaCommessa() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		tabella.setCommessa(commessaSelezionata);
		boolean enable = commessaSelezionata != null;
		comboStato.setEnabled(enable);
		filterText.setEnabled(enable);
		dataDa.setEnabled(enable);
		dataA.setEnabled(enable);
		if (!enable) {			
			comboStato.setSelectedValue(null);
			filterText.setText("");
		}
	}
	
	private void apriAbilita() {
		DialogAbilitaSpedizioni dialog = new DialogAbilitaSpedizioni();
		dialog.open();
	}
	
	private void apriStampaDelivery() {
		DialogStampaDelivery dialog = new DialogStampaDelivery();
		dialog.open();
//		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
//		if (commessaSelezionata != null) {
//			DialogWizard dialog = new DialogWizard(new FinalizzaOrdiniWizard(commessaSelezionata));
//			int returnCode = dialog.open();
//			if (returnCode == Window.OK) {
//				tabella.aggiornaContenuto();
//			}
//		} else {
//			DialogMessaggio.openWarning("Selezione commessa", "Va selezionata una commessa.");
//		}	
	}

}
