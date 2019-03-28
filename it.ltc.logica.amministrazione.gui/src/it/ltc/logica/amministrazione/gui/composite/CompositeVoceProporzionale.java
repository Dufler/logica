package it.ltc.logica.amministrazione.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.amministrazione.calcolo.algoritmi.StrategiaProporzionalePerAmministrazione;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForMoney;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeVoceProporzionale extends Gruppo {
	
	private static final String titolo = "Valore proporzionalit\u00E0";

	private ComboBox<StrategiaProporzionalePerAmministrazione> comboTipo;
	private TextForMoney textValore;
	private TextForMoney textMinimo;
	private TextForMoney textMassimo;

	public CompositeVoceProporzionale(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, titolo);
	}
	
	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblACosaDeve = new Label(this, SWT.NONE);
		lblACosaDeve.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblACosaDeve.setText("Tipo: ");
		
		comboTipo = new ComboBox<StrategiaProporzionalePerAmministrazione>(this);
		comboTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTipo.setItems(StrategiaProporzionalePerAmministrazione.values());
		
		new SpacerLabel(this);
		
		Label lblValore = new Label(this, SWT.NONE);
		lblValore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValore.setSize(38, 15);
		lblValore.setText("Valore: ");
		
		textValore = new TextForMoney(this);
		textValore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textValore.setSize(434, 21);
		
		new SpacerLabel(this);
		
		Label lblMinimo = new Label(this, SWT.NONE);
		lblMinimo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMinimo.setBounds(0, 0, 55, 15);
		lblMinimo.setText("Minimo: ");
		
		textMinimo = new TextForMoney(this);
		textMinimo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textMinimo.setRequired(false);
		
		new SpacerLabel(this);
		
		Label lblMassimo = new Label(this, SWT.NONE);
		lblMassimo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMassimo.setBounds(0, 0, 55, 15);
		lblMassimo.setText("Massimo: ");
		
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
	
	public String getTipo() {
		return comboTipo.getSelectedValue().name();
	}
	
	public void setTipo(String tipo) {
		comboTipo.setSelectedValue(StrategiaProporzionalePerAmministrazione.valueOf(tipo));
	}
	
}
