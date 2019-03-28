package it.ltc.logica.gui.common.tables.indirizzo;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;

public abstract class ToolbarIndirizziBase extends ToolbarCRUDConFiltro<TabellaIndirizziBase, Indirizzo, CriteriFiltraggioSoloTesto> {

	public ToolbarIndirizziBase(Composite parent) {
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
	protected void setupTastiToolbar() {
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
	}
	
}
