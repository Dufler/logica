package it.ltc.logica.amministrazione.gui.fatturazione.elements.preferenze;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.fatturazione.ControllerPreferenzeFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.PreferenzeFatturazione;
import it.ltc.logica.gui.common.dialogs.fatturazione.DialogPreferenzeFatturazione;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaPreferenzeFatturazione extends TabellaCRUDConFiltro<PreferenzeFatturazione, CriteriFiltraggioPreferenzeFatturazione> {

	public TabellaPreferenzeFatturazione(Composite parent) {
		super(parent, STILE_SELEZIONE_SINGOLA);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Commessa", 120, 0);
		aggiungiColonna("Ambito", 120, 1);
	}

	@Override
	protected Ordinatore<PreferenzeFatturazione> creaOrdinatore() {
		return new OrdinatorePreferenzeFatturazione();
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.AMMINSTRAZIONE_PREFERENZE_FATTURAZIONE_CUD.getID();
	}

	@Override
	protected DialogPreferenzeFatturazione creaDialog(PreferenzeFatturazione elemento) {
		DialogPreferenzeFatturazione dialog = new DialogPreferenzeFatturazione(elemento);
		return dialog;
	}
	
	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerPreferenzeFatturazione.getInstance().getPreferenze());
		
	}

	@Override
	protected FiltroTabella<PreferenzeFatturazione, CriteriFiltraggioPreferenzeFatturazione> creaFiltro() {
		return new FiltroPreferenzeFatturazione();
	}

	@Override
	protected boolean eliminaElemento(PreferenzeFatturazione elemento) {
		boolean elimina = ControllerPreferenzeFatturazione.getInstance().elimina(elemento);
		return elimina;
	}

	@Override
	protected Etichettatore<PreferenzeFatturazione> creaEtichettatore() {
		return new EtichettatorePreferenzeFatturazione();
	}

	@Override
	protected ModificatoreValoriCelle<PreferenzeFatturazione> creaModificatore() {
		return null;
	}

}
