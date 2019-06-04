package it.ltc.logica.ufficio.gui.uscite.elements.spedizione.risultato;

import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreRisultatoDatiSpedizione extends Ordinatore<RisultatoSalvataggioDatiSpedizione> {

	@Override
	protected int compare(RisultatoSalvataggioDatiSpedizione t1, RisultatoSalvataggioDatiSpedizione t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getRiferimento(), t2.getRiferimento()); break;
			case 1 : compare = compareString(t1.getOrdine(), t2.getOrdine()); break;
			default : compare = 0;
		}
		return compare;
	}

}
