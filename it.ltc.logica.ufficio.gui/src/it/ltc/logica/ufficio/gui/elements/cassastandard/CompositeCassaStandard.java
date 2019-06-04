package it.ltc.logica.ufficio.gui.elements.cassastandard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCassaStandard extends Gruppo {
	
	public static final String title = "Cassa Standard";
	
	private TextForString textCodice;

	public CompositeCassaStandard(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblCodice = new Label(this, SWT.NONE);
		lblCodice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCodice.setText("Codice: ");
		
		textCodice = new TextForString(this);
		textCodice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
	}
	
	public String getCodiceCassa() {
		return textCodice.getText();
	}

	public void setCodiceCassa(String value) {
		textCodice.setText(value);
	}

}
