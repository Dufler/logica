package it.ltc.logica.trasporti.gui.elements.spedizionemodel;

import java.util.Date;

import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

public class OrdinatoreSpedizioniModel extends Ordinatore<SpedizioneModel> {
	
	@Override
	protected int compare(SpedizioneModel t1, SpedizioneModel t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = ordinaPerData(t1.getSpedizione(), t2.getSpedizione()); break;
			case 1 : compare = ordinaPerDestinatario(t1.getDestinazione(), t2.getDestinazione()); break;
			case 2 : compare = ordinaPerLocalita(t1.getDestinazione(), t2.getDestinazione()); break;
			case 3 : compare = ordinaPerRiferimento(t1.getSpedizione(), t2.getSpedizione()); break;
			case 6 : compare = ordinaPerPezzi(t1.getSpedizione(), t2.getSpedizione()); break;
			case 7 : compare = ordinePerColli(t1.getSpedizione(), t2.getSpedizione()); break;
			case 8 : compare = ordinaPerPeso(t1.getSpedizione(), t2.getSpedizione()); break;
			case 9 : compare = compareDate(t1.getGiacenza().getDataApertura(), t2.getGiacenza().getDataApertura()); break; //Apertura giacenza
			case 10 : compare = compareDate(t1.getGiacenza().getDataChiusura(), t2.getGiacenza().getDataChiusura()); break; //Chiusura giacenza
			case 15 : compare = ordinaPerContrassegno(t1.getContrassegno(), t2.getContrassegno()); break;
			default : compare = 0;
		}
		return compare;
	}
	
	private int ordinaPerContrassegno(Contrassegno c1, Contrassegno c2) {
		Double v1 = c1 != null ? c1.getValore() : null;
		Double v2 = c2 != null ? c2.getValore() : null;
		int compare = compareDouble(v1, v2);
		return compare;
	}

	private int ordinaPerPeso(Spedizione t1, Spedizione t2) {
		int compare = compareDouble(t1.getPeso(), t2.getPeso());
		return compare;
	}

	private int ordinePerColli(Spedizione t1, Spedizione t2) {
		int compare = compareInteger(t1.getColli(), t2.getColli());
		return compare;
	}

	private int ordinaPerPezzi(Spedizione t1, Spedizione t2) {
		int compare = compareInteger(t1.getPezzi(), t2.getPezzi());
		return compare;
	}

	private int ordinaPerRiferimento(Spedizione t1, Spedizione t2) {
		String r1 = t1.getRiferimentoCliente();
		String r2 = t2.getRiferimentoCliente();
		int compare = compareString(r1, r2);
		return compare;
	}
	
	private int ordinaPerDestinatario(Indirizzo i1, Indirizzo i2) {
		String s1 = i1 != null ? i1.getRagioneSociale() : "";
		String s2 = i2 != null ? i2.getRagioneSociale() : "";
		int compare = compareString(s1, s2);
		return compare;
	}
	
	private int ordinaPerLocalita(Indirizzo i1, Indirizzo i2) {
		String s1 = i1 != null ? i1.getLocalita() : "";
		String s2 = i2 != null ? i2.getLocalita() : "";
		int compare = compareString(s1, s2);
		return compare;
	}

	private int ordinaPerData(Spedizione t1, Spedizione t2) {
		Date d1 = t1.getDataPartenza();
		Date d2 = t2.getDataPartenza();
		int order = compareDate(d1, d2);
		return order * -1;
	}

}
