package it.ltc.logica.admin.gui.elements.funzione;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerFeature;
import it.ltc.logica.common.controller.sistema.ControllerPermessi;
import it.ltc.logica.database.model.centrale.Feature;
import it.ltc.logica.database.model.centrale.utenti.Permesso;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class FunzioneComposite extends Gruppo {
	
	private static final String title = "Funzione";
	
	private TextForString textNome;
	private TextForString textPart;
	private ComboBox<Feature> comboFeature;
	private ComboBox<Permesso> comboPermesso;
	private ComboBox<Immagine> comboIcona;

	public FunzioneComposite(ParentValidationHandler parentValidator, Composite parent) {
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
		
		new SpacerLabel(this);
		
		Label lblPartId = new Label(this, SWT.NONE);
		lblPartId.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPartId.setText("Part ID: ");
		
		textPart = new TextForString(this);
		textPart.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblFeature = new Label(this, SWT.NONE);
		lblFeature.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFeature.setText("Feature: ");
		
		comboFeature = new ComboBox<>(this);
		comboFeature.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboFeature.setItems(ControllerFeature.getInstance().getFeatures());
		
		new SpacerLabel(this);
		
		Label lblPermesso = new Label(this, SWT.NONE);
		lblPermesso.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPermesso.setText("Permesso: ");
		
		comboPermesso = new ComboBox<>(this);
		comboPermesso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboPermesso.setItems(ControllerPermessi.getInstance().getPermessi());
		
		new SpacerLabel(this);
		
		Label lblIcona = new Label(this, SWT.NONE);
		lblIcona.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblIcona.setText("Icona: ");
		
		comboIcona = new ComboBox<>(this);
		comboIcona.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboIcona.setItems(Immagine.values());
		
		new SpacerLabel(this);
	}
	
	public String getNome() {
		return textNome.getValue();
	}

	public void setNome(String nome) {
		textNome.setValue(nome);
	}

	public String getPart() {
		return textPart.getValue();
	}

	public void setPart(String part) {
		textPart.setValue(part);
	}

	public Feature getFeature() {
		return comboFeature.getSelectedValue();
	}

	public void setFeature(Feature feature) {
		comboFeature.setSelectedValue(feature);
	}
	
	public Permesso getPermesso() {
		return comboPermesso.getSelectedValue();
	}

	public void setPermesso(Permesso permesso) {
		comboPermesso.setSelectedValue(permesso);
	}

	public Immagine getIcona() {
		return comboIcona.getSelectedValue();
	}

	public void setIcona(Immagine icona) {
		comboIcona.setSelectedValue(icona);
	}

}
