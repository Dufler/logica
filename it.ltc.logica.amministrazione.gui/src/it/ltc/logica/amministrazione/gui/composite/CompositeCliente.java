package it.ltc.logica.amministrazione.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCliente extends Gruppo {
	
	public static final String title = "Cliente";
	private TextForString textRagioneSociale;
	private TextForString textPIvaCF;
	private TextForString textCodice;

	public CompositeCliente(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblRagioneSociale = new Label(this, SWT.NONE);
		lblRagioneSociale.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRagioneSociale.setText("Ragione sociale: ");
		
		textRagioneSociale = new TextForString(this);
		textRagioneSociale.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblPivaCf = new Label(this, SWT.NONE);
		lblPivaCf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPivaCf.setText("P.IVA / C.F.");
		
		textPIvaCF = new TextForString(this, TextForString.REGEX_PIVA);
		textPIvaCF.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblCodiceInterno = new Label(this, SWT.NONE);
		lblCodiceInterno.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCodiceInterno.setText("Codice interno: ");
		
		textCodice = new TextForString(this);
		textCodice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
	}
	
	public void setRagioneSociale(String value) {
		textRagioneSociale.setText(value);
	}
	
	public String getRagioneSociale() {
		return textRagioneSociale.getText();
	}
	
	public void setPIvaCF(String value) {
		textPIvaCF.setText(value);
	}
	
	public String getPIvaCF() {
		return textPIvaCF.getText();
	}
	
	public void setCodice(String value) {
		textCodice.setText(value);
	}
	
	public String getCodice() {
		return textCodice.getText();
	}

}
