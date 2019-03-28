package it.ltc.logica.amministrazione.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerClienti;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Cliente;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCommessa extends Gruppo {
	
	public static final String title = "Commessa";
	
	private TextForString textNome;
	private TextForDescription textDescrizione;
	private ComboBox<Sede> comboSede;
	private ComboBox<Cliente> comboCliente;

	public CompositeCommessa(ParentValidationHandler parentValidator, Composite parent) {
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
		//textNome.setEditable(false);
		
		new SpacerLabel(this);
		
		Label lblCliente = new Label(this, SWT.NONE);
		lblCliente.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCliente.setText("Cliente: ");
		
		comboCliente = new ComboBox<Cliente>(this);
		comboCliente.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCliente.setItems(ControllerClienti.getInstance().getClienti());
		//comboCliente.setEnabled(false);
		
		new SpacerLabel(this);
		
		Label lblSede = new Label(this, SWT.NONE);
		lblSede.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSede.setText("Sede: ");
		
		comboSede = new ComboBox<Sede>(this);
		comboSede.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboSede.setItems(ControllerSedi.getInstance().getSedi());
		//comboSede.setEnabled(false);
		
		new SpacerLabel(this);
		
		Label lblDescrizione = new Label(this, SWT.NONE);
		lblDescrizione.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrizione.setText("Descrizione: ");
		
		textDescrizione = new TextForDescription(this);
		textDescrizione.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		//textDescrizione.setEditable(false);
		
		new SpacerLabel(this);
	}
	
	public String getNome() {
		return textNome.getText();
	}
	
	public void setNome(String text) {
		textNome.setText(text);
	}
	
	public String getDescrizione() {
		return textDescrizione.getText();
	}
	
	public void setDescrizione(String text) {
		textDescrizione.setText(text);
	}
	
	public Sede getSede() {
		return comboSede.getSelectedValue();
	}
	
	public void setSede(Sede value) {
		comboSede.setSelectedValue(value);
	}
	
	public Cliente getCliente() {
		return comboCliente.getSelectedValue();
	}
	
	public void setCliente(Cliente value) {
		comboCliente.setSelectedValue(value);
	}

}
