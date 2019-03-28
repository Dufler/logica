package it.ltc.logica.trasporti.gui.elements.indirizzosimulazione;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.indirizzi.IndirizzoSimulazione;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarIndirizziSimulazione extends ToolbarCRUDConFiltro<TabellaIndirizziSimulazione, IndirizzoSimulazione, CriteriFiltraggioSoloTesto> {

	public ToolbarIndirizziSimulazione(Composite parent) {
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
		return Permessi.TRASPORTI_PREVENTIVI.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();		
	}
	
}
