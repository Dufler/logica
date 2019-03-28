package it.ltc.logica.ufficio.gui.elements.fornitore;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.common.controller.trasporti.ControllerProvince;
import it.ltc.logica.database.model.centrale.indirizzi.Nazione;
import it.ltc.logica.database.model.centrale.indirizzi.Provincia;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeFornitore extends Gruppo {
	
	private static final String title = "Fornitore";
	private static final String HINT_CODICE = "Inserire il codice del fornitore, se disponibile.";
	
	
	private TextForString textRagioneSociale;
	private TextForDescription textNote;
	private TextForString textIndirizzo;
	private TextForString textLocalita;
	private TextForString textCap;
	private ComboBox<Provincia> comboProvincia;
	private ComboBox<Nazione> comboNazione;
	private TextForString textTelefono;
	private TextForString textEmail;
	private Label lblCodice;
	private TextForString textCodice;

	public CompositeFornitore(ParentValidationHandler parentValidator, Composite parent) {
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
		
		lblCodice = new Label(this, SWT.NONE);
		lblCodice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCodice.setText("Codice: ");
		
		textCodice = new TextForString(this);
		textCodice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textCodice.setRequired(false);
		textCodice.setMessage(HINT_CODICE);
		
		new SpacerLabel(this);
		
		Label lblIndirizzo = new Label(this, SWT.NONE);
		lblIndirizzo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblIndirizzo.setText("Indirizzo: ");
		
		textIndirizzo = new TextForString(this);
		textIndirizzo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblCap = new Label(this, SWT.NONE);
		lblCap.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCap.setText("CAP: ");
		
		textCap = new TextForString(this);
		textCap.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblLocalit = new Label(this, SWT.NONE);
		lblLocalit.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLocalit.setText("Localit\u00E0: ");
		
		textLocalita = new TextForString(this);
		textLocalita.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblProvincia = new Label(this, SWT.NONE);
		lblProvincia.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProvincia.setText("Provincia: ");
		
		comboProvincia = new ComboBox<Provincia>(this);
		comboProvincia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboProvincia.setItems(ControllerProvince.getInstance().getProvince());
		
		new SpacerLabel(this);
		
		Label lblNazione = new Label(this, SWT.NONE);
		lblNazione.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNazione.setText("Nazione: ");
		
		comboNazione = new ComboBox<Nazione>(this);
		comboNazione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboNazione.setItems(ControllerNazioni.getInstance().getNazioni());
		comboNazione.setSelectedValue(ControllerNazioni.getInstance().getDefault());
		
		new SpacerLabel(this);
		
		Label lblTelefono = new Label(this, SWT.NONE);
		lblTelefono.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTelefono.setText("Telefono: ");
		
		textTelefono = new TextForString(this);
		textTelefono.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textTelefono.setRequired(false);
		new Label(this, SWT.NONE);
		
		Label lblEmail = new Label(this, SWT.NONE);
		lblEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEmail.setText("Email: ");
		
		textEmail = new TextForString(this, TextForString.REGEX_EMAIL);
		textEmail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textEmail.setRequired(false);
		new Label(this, SWT.NONE);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNewLabel.setText("Note: ");
		
		textNote = new TextForDescription(this);
		textNote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		new SpacerLabel(this);
	}
	
	public String getRagioneSociale() {
		return textRagioneSociale.getText();
	}
	
	public void setRagioneSociale(String value) {
		textRagioneSociale.setText(value);
	}
	
	public Provincia getProvincia() {
		return comboProvincia.getSelectedValue();
	}
	
	public void setProvincia(Provincia value) {
		comboProvincia.setSelectedValue(value);
	}
	
	public Nazione getNazione() {
		return comboNazione.getSelectedValue();
	}
	
	public void setNazione(Nazione value) {
		comboNazione.setSelectedValue(value);
	}
	
	public String getNote() {
		return textNote.getText();
	}
	
	public void setNote(String value) {
		textNote.setText(value);
	}
	
	public String getIndirizzo() {
		return textIndirizzo.getText();
	}
	
	public void setIndirizzo(String value) {
		textIndirizzo.setText(value);
	}
	
	public String getLocalita() {
		return textLocalita.getText();
	}
	
	public void setLocalita(String value) {
		textLocalita.setText(value);
	}
	
	public String getCap() {
		return textCap.getText();
	}
	
	public void setCap(String value) {
		textCap.setText(value);
	}
	
	public String getTelefono() {
		return textTelefono.getText();
	}
	
	public void setTelefono(String value) {
		textTelefono.setText(value);
	}
	
	public String getEmail() {
		return textEmail.getText();
	}
	
	public void setEmail(String value) {
		textEmail.setText(value);
	}
	
	public String getCodice() {
		return textCodice.getText();
	}
	
	public void setCodice(String value) {
		textCodice.setText(value);
	}

}
