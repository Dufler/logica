package it.ltc.logica.trasporti.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.calcolo.algoritmi.AlgoritmoScaglioniRipetuti;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDouble;
import it.ltc.logica.gui.input.TextForMoney;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.trasporti.calcolo.algoritmi.StrategiaScaglioniPerTrasporti;

public class CompositeVoceScaglioniRipetuti extends Gruppo {
	
	private static final String titolo = "Valore degli scaglioni ripetuti";
	
	private int maxDecimali;
	
	private final boolean permessoGestione;
	
	private ComboBox<StrategiaScaglioniPerTrasporti> comboTipo;
	private TextForDouble textIntervallo;
	private TextForDouble textMinimo;
	private TextForDouble textMassimo;
	private TextForMoney textValore;

	public CompositeVoceScaglioniRipetuti(ParentValidationHandler parentValidator, Composite parent, boolean permesso) {
		super(parentValidator, parent, titolo);
		permessoGestione = permesso;
	}
	
	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblScaglioniRipetutiSu = new Label(this, SWT.NONE);
		lblScaglioniRipetutiSu.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblScaglioniRipetutiSu.setText("Scaglioni ripetuti su: ");
		
		comboTipo = new ComboBox<StrategiaScaglioniPerTrasporti>(this);
		comboTipo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setTipoScaglioni();
			}
		});
		comboTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTipo.setItems(StrategiaScaglioniPerTrasporti.values());
		
		new SpacerLabel(this);
		
		Label lblIntervallo = new Label(this, SWT.NONE);
		lblIntervallo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblIntervallo.setText("Intervallo: ");
		
		textIntervallo = new TextForDouble(this);
		textIntervallo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textIntervallo.setEnabled(false);
		
		new SpacerLabel(this);
		
		Label lblValoreMinimo = new Label(this, SWT.NONE);
		lblValoreMinimo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValoreMinimo.setText("Soglia Minima: ");
		
		textMinimo = new TextForDouble(this);
		textMinimo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textMinimo.setRequired(false);
		textMinimo.setEnabled(false);
		
		new SpacerLabel(this);
		
		Label lblValoreMassimo = new Label(this, SWT.NONE);
		lblValoreMassimo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValoreMassimo.setText("Soglia Massima: ");
		
		textMassimo = new TextForDouble(this);
		textMassimo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textMassimo.setRequired(false);
		textMassimo.setEnabled(false);
		
		new SpacerLabel(this);
		
		Label lblValore = new Label(this, SWT.NONE);
		lblValore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValore.setText("Valore: ");
		
		textValore = new TextForMoney(this);
		textValore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textValore.setEnabled(false);
		
		new SpacerLabel(this);
	}
	
	public String getTipo() {
		return comboTipo.getSelectedValue().name();
	}
	
	public void setTipo(String tipo) {
		StrategiaScaglioniPerTrasporti algoritmo = StrategiaScaglioniPerTrasporti.valueOf(tipo);
		comboTipo.setSelectedValue(algoritmo);
		setTipoScaglioni();
	}
	
	public Double getIntervallo() {
		return textIntervallo.getValue();
	}
	
	public void setIntervallo(Double intervallo) {
		textIntervallo.setValue(intervallo);
	}
	
	public Double getMinimo() {
		return textMinimo.getValue();
	}
	
	public void setMinimo(Double minimo) {
		textMinimo.setValue(minimo);
	}
	
	public Double getMassimo() {
		return textMassimo.getValue();
	}
	
	public void setMassimo(Double massimo) {
		textMassimo.setValue(massimo);
	}
	
	public Double getValore() {
		return textValore.getValue();
	}
	
	public void setValore(Double valore) {
		textValore.setValue(valore);
	}
	
	private void setTipoScaglioni() {
		int selectedIndex = comboTipo.getSelectionIndex();
		boolean abilita = (selectedIndex != -1);
		textIntervallo.setEnabled(abilita && permessoGestione);
		textMinimo.setEnabled(abilita && permessoGestione);
		textMassimo.setEnabled(abilita && permessoGestione);
		textValore.setEnabled(abilita && permessoGestione);
		if (abilita) {
			StrategiaScaglioniPerTrasporti asr = comboTipo.getSelectedValue();
			maxDecimali = AlgoritmoScaglioniRipetuti.getMaxDecimali(asr);
			textIntervallo.setMaxDecimal(maxDecimali);
			textMinimo.setMaxDecimal(maxDecimali);
			textMassimo.setMaxDecimal(maxDecimali);
		}
	}

}
