package it.ltc.logica.ufficio.gui.elements.caricodettagli;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.gui.dialog.DialogSemplice;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

public class DialogDettagli extends DialogSemplice {

	public static final String title = "Dettagli del carico";
	
	private final Commessa commessa;
	private final CaricoTestata carico;
	
	public DialogDettagli(Commessa commessa, CaricoTestata carico) {
		super(title, null, true);
		this.commessa = commessa;
		this.carico = carico;
		
		setShellStyle(SWT.SHELL_TRIM  | getDefaultOrientation());
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		ToolbarCaricoDettagli toolbar = new ToolbarCaricoDettagli(container);
		toolbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		TabellaCaricoDettagli tabella = new TabellaCaricoDettagli(container, commessa, carico);
		Table table = tabella.getTable();
		GridData layoutTabella = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		layoutTabella.heightHint = 400;
		table.setLayoutData(layoutTabella);

		toolbar.setTabella(tabella);
		
		tabella.aggiornaContenuto();
		
		//Abilito o disabilito le voci specifiche
		tabella.abilitaTastiCRUDPerStato(carico.getStato());
		toolbar.abilitaTastiCRUDPerStato(carico.getStato());
	}

	@Override
	public void checkElementiGrafici() {
		//DO NOTHING!
	}

}
