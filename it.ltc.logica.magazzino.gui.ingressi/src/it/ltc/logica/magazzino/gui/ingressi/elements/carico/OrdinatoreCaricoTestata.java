package it.ltc.logica.magazzino.gui.ingressi.elements.carico;

import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCaricoTestata extends Ordinatore<CaricoTestata> {
	
	public OrdinatoreCaricoTestata() {}

	@Override
	protected int compare(CaricoTestata t1, CaricoTestata t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = ordinaPerData(t1, t2); break;
			case 1 : compare = ordinaPerRiferimentoCliente(t1, t2); break;
			case 2 : compare = ordinaPerRiferimentoInterno(t1, t2); break;
			case 3 : compare = ordinaPerPezzi(t1, t2); break;
			//case 4 : compare = ordinaPerColli(t1, t2); break;
			case 5 : compare = ordinePerStato(t1, t2); break;
			case 6 : compare = ordinaPerTipo(t1, t2); break;
			default : compare = 0;
		}
		return compare;
	}

	private int ordinaPerTipo(CaricoTestata t1, CaricoTestata t2) {
		int compare = compareString(t1.getTipo(), t2.getTipo());
		return compare;
	}

	private int ordinePerStato(CaricoTestata t1, CaricoTestata t2) {
		int compare = t1.getStato().compareTo(t2.getStato());
		return compare;
	}

//	private int ordinaPerColli(Ingresso t1, Ingresso t2) {
//		Integer p1 = t1.getColli() != null ? t1.getColli() : 0;
//		Integer p2 = t2.getColli() != null ? t2.getColli() : 0;
//		int compare = p1.compareTo(p2);
//		return compare;
//	}

	private int ordinaPerPezzi(CaricoTestata t1, CaricoTestata t2) {
		int compare = compareInteger(t1.getQuantitaDichiarataTotale(), t2.getQuantitaDichiarataTotale());
		return compare;
	}

	private int ordinaPerRiferimentoInterno(CaricoTestata t1, CaricoTestata t2) {
		int compare = compareString(t1.getDocumentoRiferimento(), t2.getDocumentoRiferimento());
		return compare;
	}

	private int ordinaPerRiferimentoCliente(CaricoTestata t1, CaricoTestata t2) {
		int compare = compareString(t1.getRiferimento(), t2.getRiferimento());
		return compare;
	}

	private int ordinaPerData(CaricoTestata t1, CaricoTestata t2) {
		int compare = compareDate(t1.getDataArrivo(), t2.getDataArrivo());
		return compare;
	}

}
