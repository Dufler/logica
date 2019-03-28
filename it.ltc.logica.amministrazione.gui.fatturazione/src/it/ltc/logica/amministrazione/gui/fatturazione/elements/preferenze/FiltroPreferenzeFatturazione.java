package it.ltc.logica.amministrazione.gui.fatturazione.elements.preferenze;

import it.ltc.logica.database.model.centrale.fatturazione.PreferenzeFatturazione;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroPreferenzeFatturazione extends FiltroTabella<PreferenzeFatturazione, CriteriFiltraggioPreferenzeFatturazione> {

	@Override
	protected boolean checkElemento(PreferenzeFatturazione item) {
		boolean checkCommessa = checkIntValue(criteri.getCommessa(), item.getCommessa());
		boolean checkAmbito = checkIntValue(criteri.getAmbito(), item.getAmbito());
		return checkCommessa && checkAmbito;
	}

}
