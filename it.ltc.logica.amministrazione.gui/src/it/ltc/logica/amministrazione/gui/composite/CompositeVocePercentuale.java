package it.ltc.logica.amministrazione.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.amministrazione.calcolo.algoritmi.StrategiaPercentualePerAmministrazione;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForMoney;
import it.ltc.logica.gui.input.TextForPercentage;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeVocePercentuale extends Gruppo {
	
	private static final String titolo = "Valore percentuale";
	
	private TextForPercentage textValore;
	private ComboBox<StrategiaPercentualePerAmministrazione> comboTipo;
	private Label lblValoreMinimo;
	private TextForMoney textMinimo;
	private Label lblValoreMassimo;
	private TextForMoney textMassimo;

	public CompositeVocePercentuale(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, titolo);
	}
	
	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblValore = new Label(this, SWT.NONE);
		lblValore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValore.setText("Valore Percentuale: ");
		
		textValore = new TextForPercentage(this);
		textValore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblTipo = new Label(this, SWT.NONE);
		lblTipo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipo.setText("Tipo: ");
		
		comboTipo = new ComboBox<StrategiaPercentualePerAmministrazione>(this);
		comboTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTipo.setItems(StrategiaPercentualePerAmministrazione.values());
		
		new SpacerLabel(this);
		
		lblValoreMinimo = new Label(this, SWT.NONE);
		lblValoreMinimo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValoreMinimo.setText("Valore Minimo: ");
		
		textMinimo = new TextForMoney(this);
		textMinimo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textMinimo.setRequired(false);
		
		new SpacerLabel(this);
		
		lblValoreMassimo = new Label(this, SWT.NONE);
		lblValoreMassimo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValoreMassimo.setText("Valore Massimo: ");
		
		textMassimo = new TextForMoney(this);
		textMassimo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textMassimo.setRequired(false);
		
		new SpacerLabel(this);
	}
	
	public Double getValore() {
		return textValore.getValue();
	}
	
	public void setValore(Double valore) {
		textValore.setValue(valore);
	}
	
	public String getTipo() {
		return comboTipo.getSelectedValue().name();
	}
	
	public void setTipo(String tipo) {
		comboTipo.setSelectedValue(StrategiaPercentualePerAmministrazione.valueOf(tipo));
	}
	
	public Double getMinimo() {
		return textMinimo.getValue();
	}
	
	public void setMinimo(Double valore) {
		textMinimo.setValue(valore);
	}
	
	public Double getMassimo() {
		return textMassimo.getValue();
	}
	
	public void setMassimo(Double valore) {
		textMassimo.setValue(valore);
	}

}
