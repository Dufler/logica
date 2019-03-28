package it.ltc.logica.ufficio.gui.uscite.elements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolItem;

import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.ToolbarSemplice;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarOrdiniSemplice extends ToolbarSemplice<TabellaOrdiniSemplice, OrdineTestata> {
	
	protected ToolItem aggiungi;
	protected ToolItem rimuovi;

	public ToolbarOrdiniSemplice(Composite parent) {
		super(parent);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_USCITE.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		//Aggiungi ordine
		aggiungi = new ToolItem(toolbar, SWT.NONE);
		aggiungi.setImage(Immagine.CROCEVERDE_16X16.getImage());
		aggiungi.setDisabledImage(Immagine.CROCEVERDE_ANNULLATA_16X16.getImage());
		aggiungi.setText("");
		aggiungi.setToolTipText("Aggiungi Ordine");
		aggiungi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				aggiungiOrdine();
			}
		});
		//Rimuovi ordine
		rimuovi = new ToolItem(toolbar, SWT.NONE);
		rimuovi.setImage(Immagine.CESTINO_16X16.getImage());
		rimuovi.setDisabledImage(Immagine.CESTINO_ANNULLATO_16X16.getImage());
		rimuovi.setText("");
		rimuovi.setToolTipText("Elimina");
		rimuovi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rimuoviOrdine();
			}
		});
	}

	protected void rimuoviOrdine() {
		tabella.rimuoviOrdine();		
	}

	protected void aggiungiOrdine() {
		tabella.aggiungiOrdine();
		
	}

	@Override
	protected void abilita(boolean abilita) {
		boolean permesso = isAbilitato() && abilita;
		aggiungi.setEnabled(permesso);
		rimuovi.setEnabled(permesso);
	}

}
