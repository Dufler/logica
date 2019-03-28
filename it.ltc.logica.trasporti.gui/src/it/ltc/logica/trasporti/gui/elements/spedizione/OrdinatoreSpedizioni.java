package it.ltc.logica.trasporti.gui.elements.spedizione;

import it.ltc.logica.common.controller.trasporti.ControllerContrassegni;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreSpedizioni extends Ordinatore<Spedizione> {
	
	private final ControllerIndirizzi controllerIndirizzi;
	private final ControllerContrassegni controllerContrassegni;
	
	public OrdinatoreSpedizioni() {
		controllerIndirizzi = ControllerIndirizzi.getInstance();
		controllerContrassegni = ControllerContrassegni.getInstance();
	}

	@Override
	protected int compare(Spedizione t1, Spedizione t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = ordinaPerData(t1, t2); break;
			case 1 : compare = ordinaPerDestinatario(t1, t2); break;
			case 2 : compare = ordinaPerRiferimento(t1, t2); break;
			case 3 : compare = ordinaPerColli(t1, t2); break;
			case 4 : compare = ordinaPerPezzi(t1, t2); break;
			case 5 : compare = ordinaPerContrassegno(t1, t2); break;
			case 6 : compare = ordinaPerGiacenza(t1, t2); break;
			case 7 : compare = ordinaPerRitardo(t1, t2); break;
			default : compare = 0;
		}
		return compare;
	}

	private int ordinaPerRitardo(Spedizione t1, Spedizione t2) {
		int compare;
		Boolean r1 = t1.getInRitardo();
		Boolean r2 = t2.getInRitardo();
		compare = r1.compareTo(r2);
		//Tie breaker
		if (compare == 0) {
			compare = ordinaPerData(t1, t2);
		}
		return compare;
	}
	
	private int ordinaPerGiacenza(Spedizione t1, Spedizione t2) {
		int compare;
		Boolean r1 = t1.getGiacenza();
		Boolean r2 = t2.getGiacenza();
		compare = r1.compareTo(r2);
		//Tie breaker
		if (compare == 0) {
			compare = ordinaPerData(t1, t2);
		}
		return compare;
	}
	
	private int ordinaPerContrassegno(Spedizione t1, Spedizione t2) {
		int compare;
		Boolean r1 = t1.getContrassegno();
		Boolean r2 = t2.getContrassegno();
		if (r1 && r2) {
			Contrassegno c1 = controllerContrassegni.getContrassegno(t1.getId());
			Contrassegno c2 = controllerContrassegni.getContrassegno(t2.getId());
			compare = c1.getValore().compareTo(c2.getValore());
			//Tie breaker
			if (compare == 0) {
				compare = ordinaPerData(t1, t2);
			}
		} else {
			compare = r1.compareTo(r2);
		}
		return compare;
	}
	
	private int ordinaPerPezzi(Spedizione t1, Spedizione t2) {
		int compare = compareInteger(t1.getPezzi(), t2.getPezzi());
		//Tie breaker
		if (compare == 0) {
			compare = ordinaPerData(t1, t2);
		}
		return compare;
	}
	
	private int ordinaPerColli(Spedizione t1, Spedizione t2) {
		int compare = compareInteger(t1.getColli(), t2.getColli());
		//Tie breaker
		if (compare == 0) {
			compare = ordinaPerData(t1, t2);
		}
		return compare;
	}
	
	private int ordinaPerRiferimento(Spedizione t1, Spedizione t2) {
		int compare = compareString(t1.getRiferimentoCliente(), t2.getRiferimentoCliente());
		//Tie breaker
		if (compare == 0) {
			compare = ordinaPerData(t1, t2);
		}
		return compare;
	}
	
	private int ordinaPerDestinatario(Spedizione t1, Spedizione t2) {
		int compare = compareString(getDestinatario(t1), getDestinatario(t2));
		//Tie breaker
		if (compare == 0) {
			compare = ordinaPerData(t1, t2);
		}
		return compare;
	}

	private int ordinaPerData(Spedizione t1, Spedizione t2) {
		int order = compareDate(t1.getDataPartenza(), t2.getDataPartenza());
		return order * -1;
	}
	
	public String getDestinatario(Spedizione spedizione) {
		Integer idDestinatario = spedizione.getIndirizzoDestinazione();
		Indirizzo destinatario = controllerIndirizzi.getIndirizzo(idDestinatario);
		String ragioneSociale = destinatario.getRagioneSociale();
		//null check
		if (ragioneSociale == null)
			ragioneSociale = "";
		return ragioneSociale;
	}

}
