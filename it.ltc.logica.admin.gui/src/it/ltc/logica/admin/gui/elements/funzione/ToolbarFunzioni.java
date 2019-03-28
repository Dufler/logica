package it.ltc.logica.admin.gui.elements.funzione;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerFeature;
import it.ltc.logica.database.model.centrale.Feature;
import it.ltc.logica.database.model.centrale.Funzione;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarFunzioni extends ToolbarCRUDConFiltro<TabellaFunzioni, Funzione, CriteriFiltraggioFunzione> {

	private ComboBox<Feature> comboFeature;
	
	public ToolbarFunzioni(Composite parent) {
		super(parent);
	}

	@Override
	protected void setupFiltri(Composite composite) {
		composite.setLayout(new GridLayout(2, false));
		
		Label lblFeature = new SpacerLabel(this);
		lblFeature.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFeature.setText("Feature: ");
		
		comboFeature = new ComboBox<>(this);
		comboFeature.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboFeature.setItems(ControllerFeature.getInstance().getFeatures());
	}

	@Override
	protected CriteriFiltraggioFunzione getCriteriDiFiltraggio() {
		CriteriFiltraggioFunzione criteri = new CriteriFiltraggioFunzione(comboFeature.getSelectedValue());
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		comboFeature.resetValue();		
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.ADMIN.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoRefresh();
	}

}
