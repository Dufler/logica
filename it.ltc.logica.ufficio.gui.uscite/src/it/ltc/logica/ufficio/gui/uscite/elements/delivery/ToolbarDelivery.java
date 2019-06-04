package it.ltc.logica.ufficio.gui.uscite.elements.delivery;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.Delivery;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarDelivery extends ToolbarCRUDConFiltro<TabellaDelivery, Delivery, FiltroDelivery> {
	
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<Corriere> comboCorriere;
	private DateField dataDa;
	private DateField dataA;

	public ToolbarDelivery(Composite parent) {
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
		
		comboCorriere = new ComboBox<>(compositeFiltri);
		comboCorriere.setItems(ControllerCorrieri.getInstance().getCorrieri());
		comboCorriere.setEnabled(false);
		comboCorriere.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCorriere.setRequired(false);
		
		dataDa = new DateField(compositeFiltri, true);
		dataDa.setEnabled(false);
		dataDa.setRequired(false);
		
		new SpacerLabel(compositeFiltri);
		
		dataA = new DateField(compositeFiltri, true);
		dataA.setEnabled(false);
		dataA.setRequired(false);
	}

	@Override
	protected FiltroDelivery getCriteriDiFiltraggio() {
		FiltroDelivery filtro = new FiltroDelivery(comboCorriere.getSelectedValue() != null ? comboCorriere.getSelectedValue().getCodifica() : null, dataDa.getSimpleStartValue(), dataA.getSimpleEndValue());
		return filtro;
	}

	@Override
	protected void resetCampiFiltri() {
		//Tolgo la selezione della commessa
		comboCommessa.setSelectedValue(null);
		//Ripulisco i filtri specifici
		comboCorriere.setSelectedValue(null);
		dataDa.resetValue();
		dataA.resetValue();
		filterText.setText("");
		//Disabilito
		comboCorriere.setEnabled(false);
		filterText.setEnabled(false);
		dataDa.setEnabled(false);
		dataA.setEnabled(false);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_USCITE.getID();
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
	}
	
	private void selezionaCommessa() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		tabella.setCommessa(commessaSelezionata);
		boolean enable = commessaSelezionata != null;
		comboCorriere.setEnabled(enable);
		filterText.setEnabled(enable);
		dataDa.setEnabled(enable);
		dataA.setEnabled(enable);
		if (!enable) {			
			comboCorriere.setSelectedValue(null);
			filterText.setText("");
		}
	}

}
