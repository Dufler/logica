package it.ltc.logica.cdg.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici.DriverRipartizione;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCdgCostiRicaviGenerici extends Gruppo {
	
	private final static String title = "Costi e Ricavi Generici: Raggruppamento";
	
	private TextForString textNome;
	private ComboBox<DriverRipartizione> comboDriver;

	public CompositeCdgCostiRicaviGenerici(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblNome = new Label(this, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome: ");
		
		textNome = new TextForString(this, SWT.BORDER);
		textNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblDriver = new Label(this, SWT.NONE);
		lblDriver.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDriver.setText("Driver: ");
		
		comboDriver = new ComboBox<>(this);
		comboDriver.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboDriver.setItems(DriverRipartizione.values());
		
		new SpacerLabel(this);
	}

	public String getNome() {
		return textNome.getValue();
	}

	public void setNome(String nome) {
		textNome.setValue(nome);
	}

	public DriverRipartizione getDriver() {
		return comboDriver.getSelectedValue();
	}

	public void setDriver(DriverRipartizione driver) {
		comboDriver.setSelectedValue(driver);
	}

}
