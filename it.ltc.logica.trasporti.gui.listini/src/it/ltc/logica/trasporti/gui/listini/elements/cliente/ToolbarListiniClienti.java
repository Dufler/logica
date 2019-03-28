package it.ltc.logica.trasporti.gui.listini.elements.cliente;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarListiniClienti extends ToolbarCRUDConFiltro<TabellaListiniClienti, ListinoCommessa, CriteriFiltraggioSoloTesto> {

	public ToolbarListiniClienti(Composite parent) {
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
		return Permessi.TRASPORTI_LISTINI_CLIENTI_CUD.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
	}

}
