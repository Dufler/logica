package it.ltc.logica.gui.elements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

/**
 * Tabella che adempie alle funzioni di creazione, modifica ed eliminazione sugli elementi.
 * @author Damiano
 *
 * @param <T>
 */
public abstract class TabellaCRUD<T> extends TabellaCRU<T> {
	
	protected final boolean deleteAvaible;
	protected MenuItem delete;
	
	public TabellaCRUD(Composite parent) {
		this(parent, STILE_SEMPLICE, true, true);
	}

	public TabellaCRUD(Composite parent, int style) {
		this(parent, style, true, true);
	}
	
	public TabellaCRUD(Composite parent, int style, boolean deleteAvaible, boolean apriConDoppioClick) {
		super(parent, style, apriConDoppioClick);
		
		this.deleteAvaible = deleteAvaible && isPermessoDelete();
		
		if (!deleteAvaible && delete != null) {
			delete.dispose();
		}
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
	    
	    boolean permessoDelete = isPermessoDelete();
	    if (permessoDelete) {
	    	aggiungiVoceMenuElimina();
	    }
	    
	}
	
	protected void aggiungiVoceMenuElimina() {
		delete = new MenuItem(menuPopup, SWT.PUSH);
	    delete.setText("Elimina");
	    delete.setImage(Immagine.CESTINO_16X16.getImage());
	    delete.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		apriDialogElimina();
	    	}
	    });
	}
	
	/**
	 * Abilita i comandi del menù a popup 
	 * @param abilita true se vanno abilitati, false se vanno disabilitati.
	 */
	@Override
	public void abilitaComandiMenu(boolean abilita) {
		boolean permessiCRU = isPermesso();
		copy.setEnabled(abilita);
		if (insert != null) insert.setEnabled(abilita && permessiCRU);
		if (modify != null) modify.setEnabled(abilita && permessiCRU);
		if (delete != null) delete.setEnabled(abilita && permessiCRU);
	}
	
	public void apriDialogElimina() {
		if (deleteAvaible) {
			T elemento = getRigaSelezionata();
			if (elemento != null) {
				boolean scelta = DialogMessaggio.openConfirm("Eliminazione", "Sei sicuro di volerlo eliminare?");
				if (scelta) {
					boolean esito = eliminaElemento(elemento);
					if (!esito) {
						DialogMessaggio.openWarning("Eliminazione non riuscita", "Impossibile eliminare l'elemento selezionato. Se pensi che questo sia un bug contattare il CED.");
					} else {
						aggiornaContenuto();
						dirty = true;
					}
				}
			} else {
				DialogMessaggio.openInformation("Selezione Necessaria", "\u00C8 necessario selezionare un elemento!");
			}
		} else {
			DialogMessaggio.openInformation("Eliminazione non disponibile", "L'eliminazione degli elementi non \u00E8 disponibile.");
		}
	}
	
	protected abstract boolean eliminaElemento(T elemento);
	
	protected boolean isPermessoDelete() {
		boolean permesso = ControllerVariabiliGlobali.getInstance().haPermesso(getIDPermessoDelete());
		return permesso;
	}
	
	/**
	 * Come default restituisce lo stesso ID permesso della versione create, read, update.<br>
	 * E' possibile specificare un altro permesso però, se necessario.
	 * @return
	 */
	protected int getIDPermessoDelete() {
		return getIDPermesso();
	}

}
