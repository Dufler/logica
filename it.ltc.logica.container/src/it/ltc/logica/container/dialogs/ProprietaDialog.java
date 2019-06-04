package it.ltc.logica.container.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.container.element.proprieta.TabellaProprieta;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.dialog.DialogSemplice;

public class ProprietaDialog extends DialogSemplice {

	private static final String titolo = "Logica - Propriet\u00E0";
	
//	private static final String OK_LABEL = "Ok";
//	private static final String CANCEL_LABEL = "Chiudi";
	
//	private Button okButton;
	private TabellaProprieta tabella;
//	private Composite container;
	
	protected ProprietaDialog() {
		super(titolo, Immagine.IMPOSTAZIONI_16X16.getImage(), true);
	}
	
//	@Override
//	protected Control createDialogArea(Composite parent) {
//		container = (Composite) super.createDialogArea(parent);
//		container.setLayout(new GridLayout(1, false));
//		
//		Label lblProprieta = new Label(container, SWT.NONE);
//		lblProprieta.setText("Propriet\u00E0 base di Logica: fai doppio-click per modificarle, usare con cautela!!");
//		
//		tabella = new TabellaProprieta(container);
//		tabella.aggiornaContenuto();
//		
//		return container;
//	}
	
//	@Override
//	protected void createButtonsForButtonBar(Composite parent) {
//		okButton = createButton(parent, IDialogConstants.OK_ID, OK_LABEL, true);
//		okButton.setBackground(SWTResourceManager.getColor(255, 204, 0));
//		createButton(parent, IDialogConstants.CANCEL_ID, CANCEL_LABEL, false);
//	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		Label lblProprieta = new Label(container, SWT.NONE);
		lblProprieta.setText("Propriet\u00E0 base di Logica: fai doppio-click per modificarle, usare con cautela!!");
		
		tabella = new TabellaProprieta(container);
		tabella.aggiornaContenuto();
	}

	@Override
	protected void checkElementiGrafici() {
		//DO NOTHING!		
	}

}
