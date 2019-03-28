package it.ltc.logica.gui.elements;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

public abstract class TabellaCRU<T> extends Tabella<T> {
	
	protected MenuItem insert;
	protected MenuItem modify;
	
	public TabellaCRU(Composite parent) {
		this(parent, STILE_SEMPLICE, true);
	}
	
	public TabellaCRU(Composite parent, int style, boolean apriConDoppioClick) {
		super(parent, style);
		
		if (apriConDoppioClick)
			aggiungiListenerAperturaDoppioClick();
	}
	
	protected void aggiungiListenerAperturaDoppioClick() {		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				T elemento = getRigaSelezionata();
	    		if (elemento != null)
	    			apriDialog(elemento);
			}
		});
	}
	
	@Override
	protected void aggiungiMenuBase() {
		aggiungiVoceMenuCopia();
	    
	    //Aggiungo le voci di menù solo se l'utente ha i permessi richiesti
	    boolean permessiCRU = isPermesso();
	    if (permessiCRU) {
	    	aggiungiVoceMenuInserisci();
	    	aggiungiVoceMenuModifica();
	    }
	
	}
	
	protected void aggiungiVoceMenuInserisci() {
		insert = new MenuItem(menuPopup, SWT.PUSH);
	    insert.setText("Nuovo");
	    insert.setImage(Immagine.CROCEVERDE_16X16.getImage());
	    insert.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		apriDialog(null);
	    	}
	    });
	}
	
	protected void aggiungiVoceMenuModifica() {
		 modify = new MenuItem(menuPopup, SWT.PUSH);
		 modify.setText("Modifica");
		 modify.setImage(Immagine.MATITA_16X16.getImage());
		 modify.addListener(SWT.Selection, new Listener() {
		  	public void handleEvent(Event event) {
		   		T elemento = getRigaSelezionata();
		   		if (elemento != null)
		   			apriDialog(elemento);
		   	}
		 });
	}
	
	/**
	 * Abilita i comandi del menù a popup 
	 * @param abilita true se vanno abilitati, false se vanno disabilitati.
	 */
	public void abilitaComandiMenu(boolean abilita) {
		boolean permessiCRU = isPermesso();
		copy.setEnabled(abilita);
		insert.setEnabled(abilita && permessiCRU);
		modify.setEnabled(abilita && permessiCRU);
	}
	
	protected final boolean isPermesso() {
		boolean permesso = ControllerVariabiliGlobali.getInstance().haPermesso(getIDPermesso());
		return permesso;
	}
	
	protected abstract int getIDPermesso();

	protected void apriDialog(T elemento) {
		DialogApribile dialog = creaDialog(elemento);
		int result = dialog.open();
		if (result == Dialog.OK) {
			aggiornaContenuto();
		}
	}
	
	protected abstract DialogApribile creaDialog(T elemento);
	
	@Override
	protected void aggiungiListener() {
		// Di solito non è necessario.	
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		// Di solito non è necessario.		
	}

}
