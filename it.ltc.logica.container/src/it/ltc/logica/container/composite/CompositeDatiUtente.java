package it.ltc.logica.container.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeDatiUtente extends Gruppo {
	
	private static final String titolo = "Utente";
	
	private TextForString textUsername;
	private TextForString textNome;
	private TextForString textCognome;
	private TextForString textEmail;

	public CompositeDatiUtente(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, titolo);
	}
	
	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblUsername = new Label(this, SWT.NONE);
		lblUsername.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUsername.setText("Username: ");
		
		textUsername = new TextForString(this);
		textUsername.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblNome = new Label(this, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome: ");
		
		textNome = new TextForString(this);
		textNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblCognome = new Label(this, SWT.NONE);
		lblCognome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCognome.setText("Cognome: ");
		
		textCognome = new TextForString(this);
		textCognome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblEmail = new Label(this, SWT.NONE);
		lblEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEmail.setText("Email: ");
		
		textEmail = new TextForString(this);
		textEmail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
	}
	
	public String getUsername() {
		return textUsername.getText();
	}
	
	public void setUsername(String value) {
		textUsername.setText(value);
	}
	
	public String getNome() {
		return textNome.getText();
	}
	
	public void setNome(String value) {
		textNome.setText(value);
	}
	
	public String getCognome() {
		return textCognome.getText();
	}
	
	public void setCognome(String value) {
		textCognome.setText(value);
	}
	
	public String getEmail() {
		return textEmail.getText();
	}
	
	public void setEmail(String value) {
		textEmail.setText(value);
	}

}
