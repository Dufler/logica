package it.ltc.logica.amministrazione.gui.commesse.elements;

import it.ltc.logica.database.model.centrale.CommessaCentrale;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCommesse extends Ordinatore<CommessaCentrale> {

	@Override
	protected int compare(CommessaCentrale t1, CommessaCentrale t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = ordinaPerNomeCommessa(t1, t2); break;
			case 1 : compare = ordinaPerSede(t1, t2); break;
			default : compare = 0;
		}
		return compare;
	}

	private int ordinaPerNomeCommessa(CommessaCentrale t1, CommessaCentrale t2) {
		String c1 = t1.getNome() != null ? t1.getNome() : "";
		String c2 = t2.getNome() != null ? t2.getNome() : "";
		int compare = c1.compareTo(c2);
		return compare;
	}

	private int ordinaPerSede(CommessaCentrale t1, CommessaCentrale t2) {
		Integer s1 = t1.getIdSede();
		Integer s2 = t2.getIdSede();
		int compare = s1.compareTo(s2);
		return compare;
	}

}
