package it.ltc.logica.cdg.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.common.controller.sistema.ControllerCategorieMerceologiche;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCdgEvento extends Gruppo {
	
	private static final String title = "Evento";
	
	private TextForString textNome;
	private TextForDescription textDescrizione;
	private ComboBox<CdgFase> comboFase;
	private ComboBox<CategoriaMerceologica> comboCategoria;

	public CompositeCdgEvento(ParentValidationHandler parentValidator, Composite parent) {
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
		
		Label lblFase = new Label(this, SWT.NONE);
		lblFase.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFase.setText("Fase: ");
		
		comboFase = new ComboBox<CdgFase>(this);
		comboFase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboFase.setItems(ControllerCdgFasi.getInstance().getFasi());

		new SpacerLabel(this);
		
		Label lblCategoria = new Label(this, SWT.NONE);
		lblCategoria.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCategoria.setText("Categoria: ");
		
		comboCategoria = new ComboBox<CategoriaMerceologica>(this);
		comboCategoria.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCategoria.setItems(ControllerCategorieMerceologiche.getInstance().getCategorie());

		new SpacerLabel(this);
		
		Label lblDescrizione = new Label(this, SWT.NONE);
		lblDescrizione.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrizione.setText("Descrizione: ");
		
		textDescrizione = new TextForDescription(this);
		textDescrizione.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		new SpacerLabel(this);		
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
	
	public CdgFase getFase() {
		return comboFase.getSelectedValue();
	}
	
	public void setFase(CdgFase value) {
		comboFase.setSelectedValue(value);
	}
	
	public CategoriaMerceologica getCategoria() {
		return comboCategoria.getSelectedValue();
	}
	
	public void setCategoria(CategoriaMerceologica value) {
		comboCategoria.setSelectedValue(value);
	}

}
