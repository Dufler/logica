package it.ltc.logica.ufficio.gui.elements.prodotto;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Modello;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarModelli extends ToolbarCRUDConFiltro<TabellaModelli, Modello, CriteriFiltraggioModello> {
	
private static final String HINT_FILTRO = "Filtra per modello...";
	
	private ComboBox<Commessa> comboCommessa;
	
	public ToolbarModelli(Composite parent) {
		super(parent);
	}

	@Override
	protected void setupFiltri(Composite composite) {
		composite.setLayout(new GridLayout(3, false));
		
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
			//DO NOTHING!
		} else {
			filterText.setText("");
		}
	}

	@Override
	protected CriteriFiltraggioModello getCriteriDiFiltraggio() {
		CriteriFiltraggioModello filtro = new CriteriFiltraggioModello(comboCommessa.getSelectedValue(), filterText.getText());
		return filtro;
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
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();
		aggiungiTastoRefresh();
	}

	public void fissaCommessa(Commessa commessa) {
		comboCommessa.setSelectedValue(commessa);
		comboCommessa.setEnabled(false);
		filterText.setEnabled(true);
	}
	
	protected void refreshTabella() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		if (commessaSelezionata != null)
			tabella.setCommessa(commessaSelezionata);
		tabella.aggiornaContenuto();
	}

}
