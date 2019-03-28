package it.ltc.logica.cdg.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForPercentage;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCdgCostiRicaviGenericiFase extends Gruppo {
	
	private final static String title = "Costi e Ricavi Generici: Percentuale per Fase";
	
	private ComboBox<CdgFase> comboFase;
	private TextForPercentage textPercentuale;

	public CompositeCdgCostiRicaviGenericiFase(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblFase = new Label(this, SWT.NONE);
		lblFase.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFase.setText("Fase: ");
		
		comboFase = new ComboBox<CdgFase>(this);
		comboFase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboFase.setItems(ControllerCdgFasi.getInstance().getFasi());
		
		new SpacerLabel(this);
		
		Label lblPercentuale = new Label(this, SWT.NONE);
		lblPercentuale.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPercentuale.setText("Percentuale: ");
		
		textPercentuale = new TextForPercentage(this);
		textPercentuale.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
	}

	public CdgFase getFase() {
		return comboFase.getSelectedValue();
	}

	public void setFase(CdgFase fase) {
		comboFase.setSelectedValue(fase);
	}

	public Double getPercentuale() {
		return textPercentuale.getValue();
	}

	public void setPercentuale(Double percentuale) {
		textPercentuale.setValue(percentuale);
	}

}
