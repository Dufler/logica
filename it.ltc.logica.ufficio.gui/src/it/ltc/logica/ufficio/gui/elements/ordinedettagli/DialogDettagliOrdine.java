package it.ltc.logica.ufficio.gui.elements.ordinedettagli;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.dialog.DialogSemplice;

public class DialogDettagliOrdine  extends DialogSemplice {

	public static final String title = "Dettagli dell'ordine";
	
	private final Commessa commessa;
	private final OrdineTestata ordine;
	
	public DialogDettagliOrdine(Commessa commessa, OrdineTestata ordine) {
		super(title, null, true);
		this.commessa = commessa;
		this.ordine = ordine;
		
		setShellStyle(SWT.SHELL_TRIM  | getDefaultOrientation());
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		ToolbarOrdineDettagli toolbar = new ToolbarOrdineDettagli(container);
		toolbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		TabellaOrdineDettagli tabella = new TabellaOrdineDettagli(container, commessa, ordine);
		Table table = tabella.getTable();
		GridData layoutTabella = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		layoutTabella.heightHint = 400;
		table.setLayoutData(layoutTabella);

		toolbar.setTabella(tabella);
		
		tabella.aggiornaContenuto();
		
		//Abilito o disabilito le voci specifiche
		tabella.abilitaTastiCRUDPerStato(ordine.getStato());
		toolbar.abilitaTastiCRUDPerStato(ordine.getStato());
	}

	@Override
	public void checkElementiGrafici() {
		//DO NOTHING!
	}

}
