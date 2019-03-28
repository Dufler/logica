package it.ltc.logica.cdg.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForMoney;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCdgAssociazioni extends Gruppo {
	
	private static final String title = "Associazione Operatori";
	
	private TextForString textNome;
	private TextForMoney textCosto;
	private ComboBox<Sede> comboSede;

	public CompositeCdgAssociazioni(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblNome = new Label(this, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome: ");
		
		textNome = new TextForString(this);
		textNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblSede = new Label(this, SWT.NONE);
		lblSede.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSede.setText("Sede: ");
		
		comboSede = new ComboBox<Sede>(this);
		comboSede.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboSede.setItems(ControllerSedi.getInstance().getSedi());

		new SpacerLabel(this);
		
		Label lblCosto = new Label(this, SWT.NONE);
		lblCosto.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCosto.setText("Costo: ");
		
		textCosto = new TextForMoney(this, true);
		textCosto.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
	}

	public String getNome() {
		return textNome.getText();
	}

	public void setNome(String value) {
		textNome.setText(value);
	}

	public Double getCosto() {
		return textCosto.getValue();
	}

	public void setCosto(Double value) {
		textCosto.setValue(value);
	}

	public Sede getSede() {
		return comboSede.getSelectedValue();
	}

	public void setSede(Sede value) {
		comboSede.setSelectedValue(value);
	}

}
