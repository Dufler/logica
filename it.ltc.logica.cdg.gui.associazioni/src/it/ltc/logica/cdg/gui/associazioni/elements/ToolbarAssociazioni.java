package it.ltc.logica.cdg.gui.associazioni.elements;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.cdg.CdgCostoAssociazione;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarAssociazioni extends ToolbarCRUDConFiltro<TabellaAssociazioni, CdgCostoAssociazione, CriteriFiltraggioSoloTesto> {

	public ToolbarAssociazioni(Composite parent) {
		super(parent);
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		aggiungiSeparatore();
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
	}

	@Override
	protected void setupFiltri(Composite composite) {
		composite.setLayout(new GridLayout(1, false));
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
		return Permessi.CDG_COSTO_PERSONALE_CUD.getID();
	}

}
