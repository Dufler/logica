package it.ltc.logica.amministrazione.gui.fatturazione.elements.preferenze;

import it.ltc.logica.database.model.centrale.fatturazione.PreferenzeFatturazione;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatorePreferenzeFatturazione extends Ordinatore<PreferenzeFatturazione> {

	@Override
	protected int compare(PreferenzeFatturazione t1, PreferenzeFatturazione t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = ordinaPerCommessa(t1, t2); break;
			case 1 : compare = ordinaPerAmbito(t1, t2); break;
			default : compare = 0;
		}
		return compare;
	}
	
	private int ordinaPerCommessa(PreferenzeFatturazione t1, PreferenzeFatturazione t2) {
		Integer c1 = t1.getCommessa();
		Integer c2 = t2.getCommessa();
		int compare = c1.compareTo(c2);
		return compare;
	}

	private int ordinaPerAmbito(PreferenzeFatturazione t1, PreferenzeFatturazione t2) {
		Integer a1 = t1.getAmbito();
		Integer a2 = t2.getAmbito();
		int compare = a1.compareTo(a2);
		return compare;
	}

}
