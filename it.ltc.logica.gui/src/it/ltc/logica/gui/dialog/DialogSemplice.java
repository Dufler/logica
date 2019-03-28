package it.ltc.logica.gui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import it.ltc.logica.gui.composite.GruppoDiElementi;
import it.ltc.logica.gui.decoration.Immagine;

/**
 * Classe che ridefinisce le funzionalità di una dialog semplice.
 * Vanno necessariamente implementati i metodi <code>aggiungiElementiGrafici</code> e <code>checkElementiGrafici</code>.
 * E' possibile andare ad aggiungere o modificare lo stato dei bottoni estendendo il metodo <code>aggiungiAltriBottoni</code>.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public abstract class DialogSemplice extends Dialog implements DialogApribile {
	
	protected static final String OK_LABEL = "Ok";
	protected static final String CANCEL_LABEL = "Annulla";
	
	protected int minimumHeight = 300;
	protected int minimumWidth = 400;
	
	protected Button okButton;
	protected Button cancelButton;
	
	protected String title;
	protected Composite container;
	
	protected final boolean abilitaComponenti;

	/**
	 * Istanzia una semplice dialog con il titolo e l'immagine specificati.
	 * @param title Il titolo della dialog.
	 * @param image L'icona da mostrare accanto al titolo.
	 * @param abilitaComponenti indica se abilitare o disabilitare i componenti.
	 */
	protected DialogSemplice(String title, Image image, boolean abilitaComponenti) {
		super(Display.getDefault().getActiveShell());
		this.title = title;
		this.abilitaComponenti = abilitaComponenti;
		setDefaultImage(image != null ? image : Immagine.IMPOSTAZIONI_16X16.getImage());
	}
	
	@Override
    protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(title);
		newShell.setMinimumSize(minimumWidth, minimumHeight);
    }
	
	protected void setDimensioniMinime(int width, int height) {
		this.minimumWidth = width;
		this.minimumHeight = height;
		getShell().setMinimumSize(minimumWidth, minimumHeight);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));
		
		aggiungiElementiGrafici(container);
		
		abilitaComponenti(abilitaComponenti);
		
		checkElementiGrafici();
		
		return container;
	}
	
	/**
	 * Abilita o disabilita i gruppi di elementi.
	 * @param abilitaComponenti
	 */
	protected void abilitaComponenti(boolean abilitaComponenti) {
		for (Control control : container.getChildren()) {
			if (control instanceof GruppoDiElementi) {
				GruppoDiElementi gruppo = (GruppoDiElementi) control;
				gruppo.enableElement(abilitaComponenti);
			}
		}
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		okButton = createButton(parent, IDialogConstants.OK_ID, OK_LABEL, false);
		cancelButton = createButton(parent, IDialogConstants.CANCEL_ID, CANCEL_LABEL, true);
		aggiungiAltriBottoni(parent);
	}
	
	/**
	 * Mostra il messaggio di notifica specificato in una finestra con il titolo specificato.
	 * @param title il titolo da mostrare.
	 * @param message il messaggio da mostrare.
	 */
	protected boolean mostraMessaggio(String title, String message) {
		return DialogMessaggio.openConfirm(title, message);
	}
	
	/**
	 * Mostra il messaggio d'errore specificato in una finestra con il titolo specificato.
	 * @param title il titolo da mostrare.
	 * @param message il messaggio da mostrare.
	 */
	protected void mostraErrore(String title, String message) {
		DialogMessaggio.openError(title, message);
	}
	
	/**
	 * Abilita o disabilita il bottone "Ok"
	 * @param enable
	 */
	public void enableOkButton(boolean enable) {
		if (okButton != null)
			okButton.setEnabled(enable);
	}
	
	/**
	 * Abilita o disabilita il bottone "Annulla"
	 * @param enable
	 */
	public void enableCancelButton(boolean enable) {
		if (cancelButton != null)
			cancelButton.setEnabled(enable);
	}
	
	/**
	 * Questo metodo va sovrascritto dalle classi che intendono aggiungere più bottoni.
	 */
	protected void aggiungiAltriBottoni(Composite parent) {
		//DO NOTHING!
	}
	
	/**
	 * Aggiungere qui gli elementi grafici che compongono la dialog.
	 * @param container
	 */
	protected abstract void aggiungiElementiGrafici(Composite container);
	
	/**
	 * Aggiungere controlli e/o integrazioni sugli elementi grafici che compongono la dialog.
	 * @param container
	 */
	protected abstract void checkElementiGrafici();

}
