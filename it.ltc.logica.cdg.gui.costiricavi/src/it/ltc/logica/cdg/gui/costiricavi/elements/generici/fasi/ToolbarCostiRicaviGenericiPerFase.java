package it.ltc.logica.cdg.gui.costiricavi.elements.generici.fasi;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenericiFase;
import it.ltc.logica.gui.elements.ToolbarCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarCostiRicaviGenericiPerFase extends ToolbarCRUD<TabellaCostiRicaviGenericiPerFase, CdgCostiRicaviGenericiFase> {

	public ToolbarCostiRicaviGenericiPerFase(Composite parent) {
		super(parent);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG_COSTI_RICAVI_CUD.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();
	}

}
