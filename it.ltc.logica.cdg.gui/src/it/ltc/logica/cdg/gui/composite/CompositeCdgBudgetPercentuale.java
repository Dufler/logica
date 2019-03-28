package it.ltc.logica.cdg.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviGenerici;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForPercentage;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCdgBudgetPercentuale extends Gruppo {
	
	public static final String title = "Budget: percentuale di costo";
	
	private TextForPercentage textPercentuale;
	private ComboBox<CdgCostiRicaviGenerici> comboCosto;
	
	public CompositeCdgBudgetPercentuale(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblElemento = new Label(this, SWT.NONE);
		lblElemento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblElemento.setText("Elemento: ");
		
		comboCosto = new ComboBox<>(this);
		comboCosto.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCosto.setItems(ControllerCdgCostiRicaviGenerici.getInstance().getCostiRicavi());
		
		new SpacerLabel(this);
		
		Label lblPercentuale = new Label(this, SWT.NONE);
		lblPercentuale.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPercentuale.setText("Percentuale: ");
		
		textPercentuale = new TextForPercentage(this);
		textPercentuale.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
	}

	public Double getPercentuale() {
		return textPercentuale.getValue();
	}

	public void setPercentuale(Double percentuale) {
		textPercentuale.setValue(percentuale);
	}

	public CdgCostiRicaviGenerici getCosto() {
		return comboCosto.getSelectedValue();
	}

	public void setCosto(CdgCostiRicaviGenerici costo) {
		comboCosto.setSelectedValue(costo);
	}

}
