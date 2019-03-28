package it.ltc.logica.trasporti.gui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.dialog.DialogSempliceConValidazione;
import it.ltc.logica.gui.input.TextForMoney;

public class DialogSogliaVolume extends DialogSempliceConValidazione {
	
	private static final String title = "Inserisci Soglia Volume";
	
	private static final String TITOLO_ERRORE_VALORE_PRECEDENTE = "Errore nel valore";
	private static final String MESSAGGIO_ERRORE_VALORE_PRECEDENTE = "Il valore precedentemente inserito non \u00E8 valido.\r\n \u00C8 stato impostato il valore di default 0.";
	
	private final String valorePrecedente;
	
	private String nuovoValore;
	
	private TextForMoney textSoglia;
	
	public DialogSogliaVolume(String valorePrecedente) {
		super(title, null, true);
		this.valorePrecedente = valorePrecedente;
		minimumHeight = 100;
		minimumWidth = 250;
	}

	@Override
	public boolean isDirty() {
		return textSoglia.isDirty();
	}

	@Override
	protected boolean validation() {
		boolean valido = textSoglia.isValid();
		if (valido)
			nuovoValore = textSoglia.getText();
		return valido;
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(2, false));
		
		Label lblInserisciIlNumero = new Label(container, SWT.NONE);
		lblInserisciIlNumero.setText("Inserisci il valore di soglia del volume: ");

		new SpacerLabel(container);
		
		textSoglia = new TextForMoney(container);
		textSoglia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(textSoglia);
		
		new SpacerLabel(container);
	}

	@Override
	protected void checkElementiGrafici() {
		Double value;
		try {
			value = Double.parseDouble(valorePrecedente);
		} catch (NumberFormatException e) {
			value = 0.0;
			mostraMessaggio(TITOLO_ERRORE_VALORE_PRECEDENTE, MESSAGGIO_ERRORE_VALORE_PRECEDENTE);
		}
		textSoglia.setValue(value);
	}
	
	public String apri() {
		String soglia;
		if (open() == Dialog.OK && isDirty()) {
			soglia = nuovoValore;
		} else {
			soglia = valorePrecedente;
		}
		return soglia;
	}

}
