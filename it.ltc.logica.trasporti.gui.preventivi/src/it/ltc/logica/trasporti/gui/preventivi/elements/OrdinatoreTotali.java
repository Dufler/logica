package it.ltc.logica.trasporti.gui.preventivi.elements;

import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.trasporti.controller.preventivi.TotaleListino;

public class OrdinatoreTotali extends Ordinatore<TotaleListino> {

	@Override
	protected int compare(TotaleListino t1, TotaleListino t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = ordinaPerNome(t1, t2); break;
			case 1 : compare = ordinaPerTipo(t1, t2); break;
			case 2 : compare = ordinaPerValore(t1.getTotale(), t2.getTotale()); break;
			case 3 : compare = ordinaPerValore(t1.getNolo(), t2.getNolo()); break;
			case 4 : compare = ordinaPerValore(t1.getContrassegno(), t2.getContrassegno()); break;
			case 5 : compare = ordinaPerValore(t1.getExtra(), t2.getExtra()); break;
			default : compare = 0;
		}
		return compare;
	}
	
	private int ordinaPerNome(TotaleListino t1, TotaleListino t2) {
		String n1 = t1.getNome() != null ? t1.getNome() : "";
		String n2 = t2.getNome() != null ? t2.getNome() : "";
		int compare = n1.compareTo(n2);
		return compare;
	}
	
	private int ordinaPerTipo(TotaleListino t1, TotaleListino t2) {
		String n1 = t1.getTipoListino() != null ? t1.getTipoListino() : "";
		String n2 = t2.getTipoListino() != null ? t2.getTipoListino() : "";
		int compare = n1.compareTo(n2);
		return compare;
	}
	
	private int ordinaPerValore(Double d1, Double d2) {
		int compare = d1.compareTo(d2);
		return compare;
	}

}
