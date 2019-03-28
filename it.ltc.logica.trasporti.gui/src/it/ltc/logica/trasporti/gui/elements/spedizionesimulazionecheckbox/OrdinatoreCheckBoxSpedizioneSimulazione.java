package it.ltc.logica.trasporti.gui.elements.spedizionesimulazionecheckbox;

import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.SpedizioneSimulazione;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCheckBoxSpedizioneSimulazione extends Ordinatore<SpedizioneSimulazione> {

	private final ControllerIndirizzi controllerIndirizzi;
	
	public OrdinatoreCheckBoxSpedizioneSimulazione() {
		controllerIndirizzi = ControllerIndirizzi.getInstance();
	}

	@Override
	protected int compare(SpedizioneSimulazione t1, SpedizioneSimulazione t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = ordinaPerData(t1, t2); break;
			case 1 : compare = ordinaPerDestinatario(t1, t2); break;
			case 2 : compare = ordinaPerColli(t1, t2); break;
			case 3 : compare = ordinaPerPezzi(t1, t2); break;
			case 4 : compare = ordinaPerContrassegno(t1, t2); break;
			case 5 : compare = ordinaPerGiacenza(t1, t2); break;
			default : compare = 0;
		}
		return compare;
	}
	
	private int ordinaPerGiacenza(SpedizioneSimulazione t1, SpedizioneSimulazione t2) {
		int compare = compareBoolean(t1.getGiacenza(), t2.getGiacenza());
		return compare;
	}
	
	private int ordinaPerContrassegno(SpedizioneSimulazione t1, SpedizioneSimulazione t2) {
		int compare = compareBoolean(t1.getContrassegno(), t2.getContrassegno());
		return compare;
	}
	
	private int ordinaPerPezzi(SpedizioneSimulazione t1, SpedizioneSimulazione t2) {
		int compare = compareInteger(t1.getPezzi(), t2.getPezzi());
		return compare;
	}
	
	private int ordinaPerColli(SpedizioneSimulazione t1, SpedizioneSimulazione t2) {
		int compare = compareInteger(t1.getColli(), t2.getColli());
		return compare;
	}
	
	private int ordinaPerDestinatario(SpedizioneSimulazione t1, SpedizioneSimulazione t2) {
		int compare = compareString(getDestinatario(t1), getDestinatario(t2));
		return compare;
	}

	private int ordinaPerData(SpedizioneSimulazione t1, SpedizioneSimulazione t2) {
		int order = compareDate(t1.getDataPartenza(), t2.getDataPartenza());
		return order * -1;
	}
	
	public String getDestinatario(SpedizioneSimulazione spedizione) {
		Integer idDestinatario = spedizione.getIndirizzoDestinazione();
		Indirizzo destinatario = controllerIndirizzi.getIndirizzo(idDestinatario);
		String ragioneSociale = destinatario.getRagioneSociale();
		return ragioneSociale;
	}
	
}
