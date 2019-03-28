package it.ltc.logica.ufficio.gui.elements.destinatario;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.dialog.DialogSelezione;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;

public class DialogSelezioneDestinatario extends DialogSelezione<Indirizzo> {

	public static final String title = "Seleziona un destinatario";
	
	private final Commessa commessa;
	
	public DialogSelezioneDestinatario(Commessa commessa) {
		super(title);
		
		this.commessa = commessa;
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		
		Label lblDestinatari = new Label(container, SWT.NONE);
		lblDestinatari.setText("Destinatari per " + commessa.getNome());
		
		ToolbarDestinatari toolbar = new ToolbarDestinatari(container);
		toolbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		TabellaDestinatari tabella = new TabellaDestinatari(container, false, commessa);
		Table table = tabella.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				Indirizzo selezionato = tabella.getRigaSelezionata();
				if (selezionato != null) {
					selectedValue = selezionato;
					okPressed();
				}
			}
		});
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Indirizzo selezionato = tabella.getRigaSelezionata();
				if (selezionato != null) {
					selectedValue = selezionato;
				}
			}
		});
		
		toolbar.setTabella(tabella);
		toolbar.getToolitemNuovo().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DialogDestinatario dialog = new DialogDestinatario(null, commessa);
				selectedValue = dialog.apri();
				if (selectedValue != null)
					okPressed();
			}
		});
	}
}
