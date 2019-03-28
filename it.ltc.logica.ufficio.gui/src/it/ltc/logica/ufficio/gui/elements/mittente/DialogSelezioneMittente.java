package it.ltc.logica.ufficio.gui.elements.mittente;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.dialog.DialogSelezione;

public class DialogSelezioneMittente extends DialogSelezione<Indirizzo> {

	public static final String title = "Seleziona un mittente";
	
	private final Commessa commessa;
	
	public DialogSelezioneMittente(Commessa commessa) {
		super(title);
		
		this.commessa = commessa;
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		
		Label lblDestinatari = new Label(container, SWT.NONE);
		lblDestinatari.setText("Mittenti per " + commessa.getNome());
		
		ToolbarMittenti toolbar = new ToolbarMittenti(container);
		toolbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		TabellaMittenti tabella = new TabellaMittenti(container, false, commessa);
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
				DialogMittente dialog = new DialogMittente(null, commessa);
				selectedValue = dialog.apri();
				if (selectedValue != null)
					okPressed();
			}
		});
		
		//Li carico tutti subito, di solito è soltanto uno.
		tabella.aggiornaContenuto();
	}
	
}
