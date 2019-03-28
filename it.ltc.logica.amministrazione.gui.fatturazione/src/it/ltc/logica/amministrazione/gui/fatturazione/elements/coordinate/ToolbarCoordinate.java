package it.ltc.logica.amministrazione.gui.fatturazione.elements.coordinate;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.fatturazione.CoordinateBancarie;
import it.ltc.logica.gui.elements.ToolbarCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarCoordinate extends ToolbarCRUD<TabellaCoordinate, CoordinateBancarie> {

	public ToolbarCoordinate(Composite parent) {
		super(parent);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.AMMINSTRAZIONE_PREFERENZE_FATTURAZIONE_CUD.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();
	}

}
