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
import it.ltc.logica.database.model.prodotto.Modello;
import it.ltc.logica.gui.dialog.DialogSelezione;

public class DialogSelezioneModello extends DialogSelezione<Modello> {
	
private static final String title = "Seleziona il modello";
	
	private TabellaModelli tabella;
	
	private final Commessa commessa;
	
	public DialogSelezioneModello(Commessa commessa) {
		super(title);
		this.commessa = commessa;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		ToolbarModelli toolbar = new ToolbarModelli(container);
		toolbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		toolbar.fissaCommessa(commessa);
		
		tabella = new TabellaModelli(container);
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
