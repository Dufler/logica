package it.ltc.logica.trasporti.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.TextForDouble;
import it.ltc.logica.gui.input.TextForMoney;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeNuovoScaglione extends Gruppo {
	
	private static final String titolo = "Nuovo Scaglione";
	private static final String messaggioErroreValoreScaglione = "Il valore inserito in 'A' deve essere maggiore del valore inserito in 'DA'.";
	
	private final CompositeVoceScaglioni parent;
	
	private TextForDouble textDa;
	private TextForDouble textA;
	private TextForMoney textValore;
	
	private int maxDecimali;

	public CompositeNuovoScaglione(ParentValidationHandler parentValidator, Composite composite, CompositeVoceScaglioni parent) {
		super(parentValidator, composite, titolo);
		this.parent = parent;
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(9, false));
		
		Label lblDa = new Label(this, SWT.NONE);
		lblDa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDa.setText("Da: ");
		
		textDa = new TextForDouble(this);
		textDa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblA = new Label(this, SWT.NONE);
		lblA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblA.setText("A: ");
		
		textA = new TextForDouble(this);
		textA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblValore = new Label(this, SWT.NONE);
		lblValore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValore.setText("Valore: ");
		
		textValore = new TextForMoney(this);
		textValore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
	}
	
	@Override
	public boolean validate() {
		valid = super.validate();
		if (valid) {
			Double da = textDa.getValue();
			Double a = textA.getValue();
			valid = (da != null && a != null && da < a);
			if (!valid)
				textA.showErrorDecoration(messaggioErroreValoreScaglione);
		}
		//All'inizio il parent non è ancora presente.
		if (parent != null)
			parent.setInseribile(valid);
		return valid;
	}

	public void setMaxDecimali(int maxDecimali) {
		this.maxDecimali = maxDecimali;
		textDa.setMaxDecimal(maxDecimali);
		textA.setMaxDecimal(maxDecimali);
	}
	
	public Double getDa() {
		return textDa.getValue();
	}
	
	public Double getA() {
		return textA.getValue();
	}
	
	public Double getValore() {
		return textValore.getValue();
	}
	
	public void resetValues() {
		Double fine = textA.getValue();
		//Resetto la gui e valido
		if (maxDecimali == 0) {
			fine += 1;
		} else {
			fine += 0.001;
		}
		textDa.setValue(fine);
		textA.setValue(null);
		textValore.setValue(null);
		textA.setFocus();
	}
	
}