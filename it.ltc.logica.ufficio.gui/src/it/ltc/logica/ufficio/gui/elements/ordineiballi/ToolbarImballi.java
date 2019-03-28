package it.ltc.logica.ufficio.gui.elements.ordineiballi;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolItem;

import it.ltc.logica.database.model.centrale.ordini.ImballoCollo;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.ToolbarSemplice;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarImballi extends ToolbarSemplice<TabellaImballi, ImballoCollo> {
	
	protected ToolItem scarica;
	protected ToolItem reportImabllo;

	public ToolbarImballi(Composite parent) {
		super(parent);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_USCITE.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		scarica = new ToolItem(toolbar, SWT.NONE);
		scarica.setImage(Immagine.FRECCIAROSSAGIU_16X16.getImage());
		scarica.setDisabledImage(Immagine.FRECCIAROSSAGIU_16X16.getImage());
		scarica.setText("");
		scarica.setToolTipText("Scarica");
		scarica.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tabella.aggiornaContenuto();
			}
		});
		
		reportImabllo = new ToolItem(toolbar, SWT.NONE);
		reportImabllo.setImage(Immagine.CODICE_16X16.getImage());
		reportImabllo.setDisabledImage(Immagine.CODICE_16X16.getImage());
		reportImabllo.setText("");
		reportImabllo.setToolTipText("Report");
		reportImabllo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tabella.elaboraReportImballi();
			}
		});	
	}

	@Override
	protected void abilita(boolean abilita) {
		scarica.setEnabled(abilita);
		reportImabllo.setEnabled(abilita);
	}

}
