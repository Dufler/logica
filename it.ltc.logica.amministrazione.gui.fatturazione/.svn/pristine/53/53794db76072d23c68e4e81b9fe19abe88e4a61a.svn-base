package it.ltc.logica.amministrazione.gui.fatturazione.elements.coordinate;

import it.ltc.logica.database.model.centrale.fatturazione.CoordinateBancarie;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCoordinate extends Ordinatore<CoordinateBancarie> {

	@Override
	protected int compare(CoordinateBancarie t1, CoordinateBancarie t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = ordinePerNome(t1, t2); break;
			case 1 : compare = ordinePerEnte(t1, t2); break;
			case 2 : compare = ordinePerCoordinate(t1, t2); break;
			default : compare = 0;
		}
		return compare;
	}

	private int ordinePerNome(CoordinateBancarie t1, CoordinateBancarie t2) {
		String n1 = t1.getNome() != null ? t1.getNome() : "";
		String n2 = t2.getNome() != null ? t2.getNome() : "";
		int compare = n1.compareTo(n2);
		return compare;
	}

	private int ordinePerCoordinate(CoordinateBancarie t1, CoordinateBancarie t2) {
		String c1 = t1.getCoordinate() != null ? t1.getCoordinate() : "";
		String c2 = t2.getCoordinate() != null ? t2.getCoordinate() : "";
		int compare = c1.compareTo(c2);
		return compare;
	}

	private int ordinePerEnte(CoordinateBancarie t1, CoordinateBancarie t2) {
		String e1 = t1.getEnte() != null ? t1.getEnte() : "";
		String e2 = t2.getEnte() != null ? t2.getEnte() : "";
		int compare = e1.compareTo(e2);
		return compare;
	}

}
