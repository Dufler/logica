package it.ltc.logica.admin.gui.elements.feature;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerPermessi;
import it.ltc.logica.database.model.centrale.utenti.Permesso;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.Colore;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class FeatureComposite extends Gruppo {
	
	private static final String title = "Feature";
	
	private TextForString textNome;
	private TextForString textPerspective;
	private TextForString textVersione;
	private ComboBox<Permesso> comboPermesso;
	private ComboBox<Immagine> comboIcona;
	private ComboBox<Colore> comboColore;
	private TextForString textFeature;
	private TextForInteger textPosizione;

	public FeatureComposite(ParentValidationHandler parentValidator, Composite parent) {
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
		
		Label lblPerspectiveId = new Label(this, SWT.NONE);
		lblPerspectiveId.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPerspectiveId.setText("Perspective ID: ");
		
		textPerspective = new TextForString(this);
		textPerspective.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addNonUpdatableElement(textPerspective);

		new SpacerLabel(this);
		
		Label lblFeatureId = new Label(this, SWT.NONE);
		lblFeatureId.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFeatureId.setText("Feature ID: ");
		
		textFeature = new TextForString(this);
		textFeature.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblVersione = new Label(this, SWT.NONE);
		lblVersione.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVersione.setText("Versione: ");
		
		textVersione = new TextForString(this);
		textVersione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblPosizione = new Label(this, SWT.NONE);
		lblPosizione.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPosizione.setText("Posizione: ");
		
		textPosizione = new TextForInteger(this);
		textPosizione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

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
		
		Label lblColore = new Label(this, SWT.NONE);
		lblColore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblColore.setText("Colore: ");
		
		comboColore = new ComboBox<>(this);
		comboColore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboColore.setItems(Colore.values());

		new SpacerLabel(this);
	}

	public String getNome() {
		return textNome.getValue();
	}

	public void setNome(String nome) {
		textNome.setValue(nome);
	}

	public String getPerspective() {
		return textPerspective.getValue();
	}

	public void setPerspective(String perspective) {
		textPerspective.setValue(perspective);
	}
	
	public String getFeature() {
		return textFeature.getValue();
	}
	
	public void setFeature(String feature) {
		textFeature.setValue(feature);
	}

	public String getVersione() {
		return textVersione.getValue();
	}

	public void setVersione(String versione) {
		textVersione.setValue(versione);
	}
	
	public Integer getPosizione() {
		return textPosizione.getValue();
	}
	
	public void setPosizione(Integer posizione) {
		textPosizione.setValue(posizione);
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
	
	public Colore getColore() {
		return comboColore.getSelectedValue();
	}
	
	public void setColore(Colore colore) {
		comboColore.setSelectedValue(colore);
	}

}
