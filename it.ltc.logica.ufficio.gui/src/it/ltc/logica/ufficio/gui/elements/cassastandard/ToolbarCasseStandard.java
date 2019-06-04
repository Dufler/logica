package it.ltc.logica.ufficio.gui.elements.cassastandard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.CassaStandard;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarCasseStandard extends ToolbarCRUDConFiltro<TabellaCasseStandard, CassaStandard, CriteriFiltraggioSoloTesto> {
	
	private static final String HINT_FILTRO = "Filtra per codice cassa...";
	
	private ComboBox<Commessa> comboCommessa;

	public ToolbarCasseStandard(Composite parent) {
		super(parent);
	}

	@Override
	protected void setupFiltri(Composite composite) {
		composite.setLayout(new GridLayout(7, false));
		
		Label labelCommessa = new Label(composite, SWT.NONE);
		labelCommessa.setText("Seleziona la commessa: ");
		
		comboCommessa = new ComboBox<>(composite);
		comboCommessa.setItems(ControllerCommesse.getInstance().getCommesse());
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selezioneCommessa();
			}
		});
		
		aggiungiCampoDiTestoGenericoPerFiltro(composite);
		filterText.setEnabled(false);
		filterText.setMessage(HINT_FILTRO);
		filterText.setToolTipText(HINT_FILTRO);
		
	}
	
	private void selezioneCommessa() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		tabella.setCommessa(commessaSelezionata);
		boolean enable = commessaSelezionata != null;
		filterText.setEnabled(enable);
		if (enable) {
			tabella.aggiornaContenuto();
		} else {
			filterText.setText("");
		}
	}

	@Override
	protected CriteriFiltraggioSoloTesto getCriteriDiFiltraggio() {
		CriteriFiltraggioSoloTesto criteri = new CriteriFiltraggioSoloTesto(filterText.getText());
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		comboCommessa.resetValue();
		filterText.setText("");		
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_PRODOTTI.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.UFFICIO_PRODOTTI_ELIMINA.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();
	}

}
