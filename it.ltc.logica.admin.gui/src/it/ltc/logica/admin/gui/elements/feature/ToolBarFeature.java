package it.ltc.logica.admin.gui.elements.feature;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.Feature;
import it.ltc.logica.gui.elements.ToolbarCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolBarFeature extends ToolbarCRUD<TabellaFeature, Feature> {

	public ToolBarFeature(Composite parent) {
		super(parent);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.ADMIN.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoRefresh();
	}

}
