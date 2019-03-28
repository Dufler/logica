package it.ltc.logica.ufficio.gui.uscite.elements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.dialog.DialogSelezione;
import it.ltc.logica.ufficio.gui.uscite.elements.TabellaOrdiniDaLavorare.TipoOrdiniLavorabili;

public class DialogSelezioneOrdine extends DialogSelezione<OrdineTestata> {

	public static final String title = "Seleziona un ordine";
	
	public static final String testoLabelSelezione = "Ordine selezionato: ";
	
	private final Commessa commessa;
	
	private Label labelSelezione;
	private TabellaOrdiniDaLavorare tabella;
	
	public DialogSelezioneOrdine(Commessa commessa) {
		super(title);
		this.commessa = commessa;
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		labelSelezione = new Label(container, SWT.NONE);
		labelSelezione.setText(testoLabelSelezione);
		
		tabella = new TabellaOrdiniDaLavorare(container, commessa, TipoOrdiniLavorabili.SPEDIRE_SELEZIONE_SINGOLA);
		Table table = tabella.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectedValue = tabella.getRigaSelezionata();
				if (selectedValue != null) {
					labelSelezione.setText(testoLabelSelezione + selectedValue.getRiferimento());
				}
			}
		});
		
		tabella.aggiornaContenuto();		
	}

}
