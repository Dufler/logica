package it.ltc.logica.ufficio.gui.elements.prodotto;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Prodotto;
import it.ltc.logica.gui.dialog.DialogSelezione;

public class DialogSelezioneProdotto extends DialogSelezione<Prodotto> {

	private static final String title = "Seleziona il prodotto";
	
	private TabellaProdotti tabella;
	
	private final Commessa commessa;
	
	public DialogSelezioneProdotto(Commessa commessa) {
		super(title);
		this.commessa = commessa;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		ToolbarProdotti toolbar = new ToolbarProdotti(container);
		toolbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		toolbar.fissaCommessa(commessa);
		
		tabella = new TabellaProdotti(container, false);
		tabella.setCommessa(commessa);
		Table table = tabella.getTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				int selectionIndex = table.getSelectionIndex();
				if (selectionIndex != -1) {
					selectedValue = tabella.getRigaSelezionata();
					okPressed();
				}
			}
		});
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectedValue = tabella.getRigaSelezionata();
			}
		});
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbar.setTabella(tabella);	
	}

}
