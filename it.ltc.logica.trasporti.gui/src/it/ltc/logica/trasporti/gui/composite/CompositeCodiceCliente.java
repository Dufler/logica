package it.ltc.logica.trasporti.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCodiceCliente extends Gruppo {
	
	private static final String titolo = "Codice Cliente";
	
	private TextForString textCodice;
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<Corriere> comboCorriere;
	private Label lblDescrizione;
	private TextForDescription textDescrizione;
	private Label lblStato;
	private ComboBox<CodiceClienteCorriere.Stato> comboStato;
	

	public CompositeCodiceCliente(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, titolo);
	}
	
	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblCodice = new Label(this, SWT.NONE);
		lblCodice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCodice.setText("Codice: ");
		
		textCodice = new TextForString(this);
		textCodice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addNonUpdatableElement(textCodice);
		
		new SpacerLabel(this);
		
		lblStato = new Label(this, SWT.NONE);
		lblStato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStato.setText("Stato: ");
		
		comboStato = new ComboBox<CodiceClienteCorriere.Stato>(this);
		comboStato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboStato.setItems(CodiceClienteCorriere.Stato.values());
		
		new SpacerLabel(this);
		
		Label lblCliente = new Label(this, SWT.NONE);
		lblCliente.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCliente.setText("Cliente: ");
		
		comboCommessa = new ComboBox<Commessa>(this);
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		
		new SpacerLabel(this);
		
		Label lblCorriere = new Label(this, SWT.NONE);
		lblCorriere.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCorriere.setText("Corriere: ");
		
		comboCorriere = new ComboBox<Corriere>(this);
		comboCorriere.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCorriere.setItems(ControllerCorrieri.getInstance().getCorrieri());
		
		new SpacerLabel(this);
		
		lblDescrizione = new Label(this, SWT.NONE);
		lblDescrizione.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrizione.setText("Descrizione: ");
		
		textDescrizione = new TextForDescription(this);
		textDescrizione.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		new SpacerLabel(this);
	}
	
	public String getCodiceCliente() {
		return textCodice.getText();
	}
	
	public void setCodiceCliente(String value) {
		textCodice.setText(value);
	}
	
	public Commessa getCommessa() {
		return comboCommessa.getSelectedValue();
	}
	
	public void setCommessa(Commessa value) {
		comboCommessa.setSelectedValue(value);
	}
	
	public Corriere getCorriere() {
		return comboCorriere.getSelectedValue();
	}
	
	public void setCorriere(Corriere value) {
		comboCorriere.setSelectedValue(value);
	}
	
	public String getDescrizione() {
		return textDescrizione.getText();
	}
	
	public void setDescrizione(String value) {
		textDescrizione.setText(value);
	}
	
	public String getStato() {
		return comboStato.getSelectedValue().getCodice();
	}
	
	public void setStato(String stato) {
		CodiceClienteCorriere.Stato value = CodiceClienteCorriere.Stato.valueOf(stato);
		comboStato.setSelectedValue(value);
	}

}
