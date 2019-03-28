package it.ltc.logica.trasporti.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.TextForMoney;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeVoceFissa extends Gruppo {
	
	private static final String titolo = "Valore fisso";
	
	private TextForMoney textValore;

	public CompositeVoceFissa(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, titolo);
	}
	
	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblValore = new Label(this, SWT.NONE);
		lblValore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValore.setText("Valore: ");
		
		textValore = new TextForMoney(this);
		textValore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
	}

	
	public Double getValore() {
		return textValore.getValue();
	}
	
	public void setValore(Double valore) {
		textValore.setValue(valore);
	}
}
