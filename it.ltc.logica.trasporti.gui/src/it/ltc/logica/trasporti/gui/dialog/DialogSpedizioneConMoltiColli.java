package it.ltc.logica.trasporti.gui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.dialog.DialogSempliceConValidazione;
import it.ltc.logica.gui.input.TextForInteger;

public class DialogSpedizioneConMoltiColli extends DialogSempliceConValidazione {

private static final String title = "Inserisci Numero Colli";
	
	private static final String TITOLO_ERRORE_VALORE_PRECEDENTE = "Errore nel valore";
	private static final String MESSAGGIO_ERRORE_VALORE_PRECEDENTE = "Il valore precedentemente inserito non \u00E8 valido.\r\n \u00C8 stato impostato il valore di default 0.";
	
	private final String valorePrecedente;
	
	private String nuovoValore;
	
	private TextForInteger textFranchigia;
	
	public DialogSpedizioneConMoltiColli(String valorePrecedente) {
		super(title, null, true);
		this.valorePrecedente = valorePrecedente;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(2, false));
		
		Label lblInserisciIlNumero = new Label(container, SWT.NONE);
		lblInserisciIlNumero.setText("Inserisci il numero minimo di colli per cui si deve applicare:");

		new SpacerLabel(container);
		
		textFranchigia = new TextForInteger(container);
		textFranchigia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(textFranchigia);
		
		new SpacerLabel(container);
	}

	@Override
	public void checkElementiGrafici() {
		Integer value;
		try {
			value = Integer.parseInt(valorePrecedente);
		} catch (NumberFormatException e) {
			value = 0;
			mostraMessaggio(TITOLO_ERRORE_VALORE_PRECEDENTE, MESSAGGIO_ERRORE_VALORE_PRECEDENTE);
		}
		textFranchigia.setValue(value);
	}

	@Override
	public boolean isDirty() {
		return textFranchigia.isDirty();
	}

	@Override
	protected boolean validation() {
		boolean valido = textFranchigia.isValid();
		if (valido)
			nuovoValore = textFranchigia.getText();
		return valido;
	}
	
	public String apri() {
		String giorni;
		if (open() == Dialog.OK && isDirty()) {
			giorni = nuovoValore;
		} else {
			giorni = valorePrecedente;
		}
		return giorni;
	}

}
