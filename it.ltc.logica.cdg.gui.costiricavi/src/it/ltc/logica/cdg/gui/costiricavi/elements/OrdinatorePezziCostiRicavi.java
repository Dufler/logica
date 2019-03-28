package it.ltc.logica.cdg.gui.costiricavi.elements;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.cdg.CdgPezzo;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatorePezziCostiRicavi extends Ordinatore<CdgPezzo> {
	
	private final ControllerCommesse controller;
	
	protected OrdinatorePezziCostiRicavi() {
		controller = ControllerCommesse.getInstance();
	}

	@Override
	protected int compare(CdgPezzo t1, CdgPezzo t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = ordinaPerCommessa(t1, t2); break;
			case 1 : compare = compareString(t1.getCategoriaMerceologica(), t2.getCategoriaMerceologica()); break;
			case 2 : compare = compareDouble(t1.getCosto(), t2.getCosto()); break;
			case 3 : compare = compareDouble(t1.getRicavo(), t2.getRicavo()); break;
			default : compare = 0;
		}
		return compare;
	}

	private int ordinaPerCommessa(CdgPezzo t1, CdgPezzo t2) {
		Commessa c1 = controller.getCommessa(t1.getCommessa());
		Commessa c2 = controller.getCommessa(t2.getCommessa());
		String s1 = c1 != null ? c1.getNome() : "";
		String s2 = c2 != null ? c2.getNome() : "";
		int compare = compareString(s1, s2);
		return compare;
	}

}
