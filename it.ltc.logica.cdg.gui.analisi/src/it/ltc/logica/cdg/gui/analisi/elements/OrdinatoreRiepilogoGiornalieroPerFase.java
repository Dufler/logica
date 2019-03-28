package it.ltc.logica.cdg.gui.analisi.elements;

import it.ltc.logica.cdg.gui.analisi.model.RiepilogoGiornalieroPerFaseCDG;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreRiepilogoGiornalieroPerFase extends Ordinatore<RiepilogoGiornalieroPerFaseCDG> {

	@Override
	protected int compare(RiepilogoGiornalieroPerFaseCDG t1, RiepilogoGiornalieroPerFaseCDG t2, int property) {
		int order;
		switch (property) {
			case 0 : order = compareInteger(t1.getFase(), t2.getFase()); break;
//			case 1 : order = compareDouble(t1.getRicaviIpotetici(), t2.getRicaviIpotetici()); break;
			case 2 : order = compareDouble(t1.getCostiReali(), t2.getCostiReali()); break;
			case 3 : order = compareDouble(t1.getCostiIpotetici(), t2.getCostiIpotetici()); break;
			case 4 : order = compareDouble(t1.getScostamentoTempi(), t2.getScostamentoTempi()); break;
			default : order = 0;
		}
		return order;
	}

}
