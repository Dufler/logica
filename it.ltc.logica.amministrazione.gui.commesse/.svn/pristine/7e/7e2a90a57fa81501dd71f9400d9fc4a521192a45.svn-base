package it.ltc.logica.amministrazione.gui.commesse.elements;

import it.ltc.logica.database.model.centrale.Cliente;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreClienti extends Ordinatore<Cliente> {

	@Override
	protected int compare(Cliente t1, Cliente t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareRagioneSociale(t1, t2); break;
			case 1 : compare = comparePIvaCF(t1, t2); break;
			default : compare = 0;
		}
		return compare;
	}

	private int comparePIvaCF(Cliente t1, Cliente t2) {
		String p1 = t1.getPartitaIva() != null ? t1.getPartitaIva() : "";
		String p2 = t2.getPartitaIva() != null ? t2.getPartitaIva() : "";
		int compare = p1.compareTo(p2);
		return compare;
	}

	private int compareRagioneSociale(Cliente t1, Cliente t2) {
		String r1 = t1.getRagioneSociale() != null ? t1.getRagioneSociale() : "";
		String r2 = t2.getRagioneSociale() != null ? t2.getRagioneSociale() : "";
		int compare = r1.compareTo(r2);
		return compare;
	}

}
