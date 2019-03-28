package it.ltc.logica.trasporti.gui.listini.elements.simulazione.voci;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarVociListiniSimulazione extends ToolbarCRUDConFiltro<TabellaVociListinoSimulazione, ListinoSimulazioneVoce, CriteriFiltraggioSoloTesto> {

	public ToolbarVociListiniSimulazione(Composite parent) {
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
		return Permessi.TRASPORTI_LISTINI_SIMULAZIONE.getID();
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
