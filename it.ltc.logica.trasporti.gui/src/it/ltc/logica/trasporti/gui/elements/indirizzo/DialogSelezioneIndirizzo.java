package it.ltc.logica.trasporti.gui.elements.indirizzo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.dialog.DialogSelezione;

public class DialogSelezioneIndirizzo extends DialogSelezione<Indirizzo> {
	
	private static final String titolo = "Rubrica Indirizzi";
	
	private Indirizzo indirizzoSelezionato;
	
	private TabellaIndirizzi tableViewer;
	private Table table;

	public DialogSelezioneIndirizzo() {
		super(titolo);
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblSelezionaUnindirizzoE = new Label(container, SWT.NONE);
		lblSelezionaUnindirizzoE.setText("Seleziona un'indirizzo e premi \"Ok\"");
		
		ToolbarIndirizzi toolbar = new ToolbarIndirizzi(container);
		
		tableViewer = new TabellaIndirizzi(container, false);
		table = tableViewer.getTable();
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = table.getSelectionIndex();
				if (selectionIndex != -1) {
					TableItem selectedItem = table.getItem(selectionIndex);
					indirizzoSelezionato = (Indirizzo) selectedItem.getData();
				}
			}
		});
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbar.setTabella(tableViewer);
	}

	@Override
	public void caricaModel() {}

	@Override
	public Indirizzo getSelezione() {
		return indirizzoSelezionato;
	}

	@Override
	public void checkElementiGrafici() {}

}
