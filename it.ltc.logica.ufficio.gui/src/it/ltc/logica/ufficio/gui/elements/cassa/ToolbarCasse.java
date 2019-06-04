package it.ltc.logica.ufficio.gui.elements.cassa;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Cassa;
import it.ltc.logica.database.model.prodotto.TipoCassa;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarCasse extends ToolbarCRUDConFiltro<TabellaCasse, Cassa, CriteriFiltroCassa> {
	
	private static final String HINT_FILTRO = "Filtra per codice cassa o modello...";
	
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<TipoCassa> comboCassa;

	public ToolbarCasse(Composite parent) {
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
		
		comboCassa = new ComboBox<>(composite);
		comboCassa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCassa.setItems(TipoCassa.values());
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
	protected CriteriFiltroCassa getCriteriDiFiltraggio() {
		CriteriFiltroCassa criteri = new CriteriFiltroCassa(comboCommessa.getSelectedValue(), filterText.getText(), comboCassa.getSelectedValue());
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		comboCommessa.resetValue();
		comboCassa.resetValue();
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
