package it.ltc.logica.trasporti.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeProprietaListinoCorriere extends Gruppo {
	
	private static final String titolo = "Propriet\u00E0 - Listino Corriere";
	
	private ComboBox<Corriere> comboCorriere;
	private ComboBox<AmbitoFattura> comboTipo;
	private TextForString textNome;
	private TextForDescription textDescrizione;
	private Label lblTipo;

	public CompositeProprietaListinoCorriere(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, titolo);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		Label lblCorriere = new Label(this, SWT.NONE);
		lblCorriere.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCorriere.setText("Corriere: ");
		
		comboCorriere = new ComboBox<Corriere>(this);
		comboCorriere.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCorriere.setItems(ControllerCorrieri.getInstance().getCorrieri());
		
		new SpacerLabel(this);
		
		lblTipo = new Label(this, SWT.NONE);
		lblTipo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipo.setText("Tipo: ");
		
		comboTipo = new ComboBox<AmbitoFattura>(this);
		comboTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTipo.setItems(ControllerAmbitiFatturazione.getInstance().getAmbitiPerCategoria(AmbitoFattura.Categoria.CORRIERI));
		
		new SpacerLabel(this);
		
		Label lblNome = new Label(this, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome: ");
		
		textNome = new TextForString(this);
		textNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblDescrizione = new Label(this, SWT.NONE);
		lblDescrizione.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrizione.setText("Descrizione: ");
		
		textDescrizione = new TextForDescription(this);
		textDescrizione.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		new SpacerLabel(this);
	}
	
	public Corriere getCorriere() {
		return comboCorriere.getSelectedValue();
	}
	
	public void setCorriere(Corriere value) {
		comboCorriere.setSelectedValue(value);
	}
	
	public String getNome() {
		return textNome.getText();
	}
	
	public void setNome(String value) {
		textNome.setText(value);
	}
	
	public String getDescrizione() {
		return textDescrizione.getText();
	}
	
	public void setDescrizione(String value) {
		textDescrizione.setText(value);
	}
	
	public AmbitoFattura getTipo() {
		return comboTipo.getSelectedValue();
	}
	
	public void setTipo(AmbitoFattura tipo) {
		comboTipo.setSelectedValue(tipo);
	}

}
