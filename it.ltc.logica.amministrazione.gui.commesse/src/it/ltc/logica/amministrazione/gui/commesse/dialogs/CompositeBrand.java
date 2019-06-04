package it.ltc.logica.amministrazione.gui.commesse.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeBrand extends Gruppo {
	
	public static final String title = "Brand";
	
	private TextForInteger textCodice;
	private TextForDescription textDescrizione;

	public CompositeBrand(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblCodice = new Label(this, SWT.NONE);
		lblCodice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCodice.setText("Codice: ");
		
		textCodice = new TextForInteger(this);
		textCodice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblDescrizione = new Label(this, SWT.NONE);
		lblDescrizione.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrizione.setText("Descrizione: ");
		
		textDescrizione = new TextForDescription(this, 50);
		textDescrizione.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		new SpacerLabel(this);
	}

	public Integer getCodice() {
		return textCodice.getValue();
	}

	public void setCodice(Integer codice) {
		textCodice.setValue(codice);
	}

	public String getDescrizione() {
		return textDescrizione.getValue();
	}

	public void setDescrizione(String descrizione) {
		textDescrizione.setValue(descrizione);
	}

}
