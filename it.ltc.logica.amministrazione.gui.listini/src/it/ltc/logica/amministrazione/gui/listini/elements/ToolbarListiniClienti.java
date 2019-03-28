package it.ltc.logica.amministrazione.gui.listini.elements;

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
	protected void setupTastiToolbar() {
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		aggiungiSeparatore();
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();
	}

	@Override
	protected void resetCampiFiltri() {
		filterText.setText("");		
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.AMMINISTRAZIONE_LISTINI_CUD.getID();
	}

}
