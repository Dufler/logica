package it.ltc.logica.trasporti.gui.fatturazione.elements.nonfatturabili;

import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCheckBoxSpedizione extends Ordinatore<Spedizione> {

	private final ControllerIndirizzi controllerIndirizzi;
	
	public OrdinatoreCheckBoxSpedizione() {
		controllerIndirizzi = ControllerIndirizzi.getInstance();
	}

	@Override
	protected int compare(Spedizione t1, Spedizione t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareDate(t1.getDataPartenza(), t2.getDataPartenza()); break;
			case 1 : compare = ordinaPerDestinatario(t1, t2); break;
			case 2 : compare = compareString(t1.getRiferimentoCliente(), t2.getRiferimentoCliente()); break;
			case 3 : compare = t1.getFatturazione().compareTo(t2.getFatturazione()); break;
			default : compare = 0;
		}
		return compare;
	}
	
	private int ordinaPerDestinatario(Spedizione t1, Spedizione t2) {
		int compare = compareString(getDestinatario(t1), getDestinatario(t2));
		return compare;
	}
	
	public String getDestinatario(Spedizione spedizione) {
		Integer idDestinatario = spedizione.getIndirizzoDestinazione();
		Indirizzo destinatario = controllerIndirizzi.getIndirizzo(idDestinatario);
		String ragioneSociale = destinatario.getRagioneSociale();
		return ragioneSociale;
	}
	
}
