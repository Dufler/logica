package it.ltc.logica.amministrazione.gui.commesse.elements;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.CommessaCentrale;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarCommesse extends ToolbarCRUDConFiltro<TabellaCommesse, CommessaCentrale, CriteriFiltraggioSoloTesto> {

	public ToolbarCommesse(Composite parent) {
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
		return Permessi.AMMINISTRAZIONE_CLIENTI_COMMESSE_CUD.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		aggiungiSeparatore();
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
	}

}
