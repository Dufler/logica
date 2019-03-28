package it.ltc.logica.trasporti.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeDatiOrdineDaSpedizione extends Gruppo {

	private static final String titolo = "Dati Ordine";
	private TextForString textRiferimentoCliente;
	private TextForString textRiferimentoInterno;
	private TextForInteger textPezzi;
	private TextForDescription textNote;
	private Label lblCommessa;
	private ComboBox<Commessa> comboCommessa;
	
	public CompositeDatiOrdineDaSpedizione(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, titolo);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		lblCommessa = new Label(this, SWT.NONE);
		lblCommessa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCommessa.setText("Commessa: ");
		
		comboCommessa = new ComboBox<Commessa>(this);
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		
		new SpacerLabel(this);
		
		Label lblRiferimentoCliente = new Label(this, SWT.NONE);
		lblRiferimentoCliente.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRiferimentoCliente.setText("Riferimento Cliente: ");
		
		textRiferimentoCliente = new TextForString(this);
		textRiferimentoCliente.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblRiferimentoInterno = new Label(this, SWT.NONE);
		lblRiferimentoInterno.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRiferimentoInterno.setText("Riferimento Interno: ");
		
		textRiferimentoInterno = new TextForString(this);
		textRiferimentoInterno.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblPezzi = new Label(this, SWT.NONE);
		lblPezzi.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPezzi.setText("Pezzi: ");
		
		textPezzi = new TextForInteger(this);
		textPezzi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblNote = new Label(this, SWT.NONE);
		lblNote.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNote.setText("Note: ");
		
		textNote = new TextForDescription(this);
		textNote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		new SpacerLabel(this);
	}
	
	/**
	 * Viene usata per farsi restituire la combo e applicarvi dei listener.
	 */
	public ComboBox<Commessa> getComboCommessa() {
		return comboCommessa;
	}
	
	public Commessa getCommessa() {
		return comboCommessa.getSelectedValue();
	}
	
	public void setCommessa(Commessa value) {
		comboCommessa.setSelectedValue(value);
	}
	
	public String getRiferimentoCliente() {
		return textRiferimentoCliente.getText();
	}
	
	public void setRiferimentoCliente(String value) {
		textRiferimentoCliente.setText(value);
	}
	
	public String getRiferimentoInterno() {
		return textRiferimentoInterno.getText();
	}
	
	public void setRiferimentoInterno(String value) {
		textRiferimentoInterno.setText(value);
	}
	
	public Integer getPezzi() {
		return textPezzi.getValue();
	}
	
	public void setPezzi(Integer value) {
		textPezzi.setValue(value);
	}
	
	public String getNote() {
		return textNote.getText();
	}
	
	public void setNote(String value) {
		textNote.setText(value);
	}

}
