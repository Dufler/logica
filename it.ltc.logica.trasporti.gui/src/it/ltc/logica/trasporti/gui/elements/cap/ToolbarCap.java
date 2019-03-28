package it.ltc.logica.trasporti.gui.elements.cap;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.trasporti.ControllerCap;
import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarCap extends ToolbarCRUDConFiltro<TabellaCap, Cap, CriteriFiltraggioSoloTesto> {

	public ToolbarCap(Composite parent) {
		super(parent);
	}

	@Override
	protected void setupFiltri(Composite composite) {
		aggiungiCampoDiTestoGenericoPerFiltro(composite);		
	}

	@Override
	protected CriteriFiltraggioSoloTesto getCriteriDiFiltraggio() {
		CriteriFiltraggioSoloTesto criteri = new CriteriFiltraggioSoloTesto(filterText.getText());
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		filterText.setText("");
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_CAP_CUD.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		aggiungiTastoRefresh();
	}
	
	@Override
	protected void refreshTabella() {
		ControllerCap.getInstance().caricaDati();
		tabella.aggiornaContenuto();
	}

}
