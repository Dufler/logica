package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.common.controller.trasporti.ControllerCap;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

public class NoloLight extends NoloItalia {
	
	private static final double MAX_VOLUME = 0.022;
	private static final double MAX_PESO = 3.01;
	
	private static final String CAP_LIVIGNO = "23030";
	private static final String LOCALITA_LIVIGNO = "LIVIGNO";

	public NoloLight(SottoAmbitoFattura ambito) {
		super(ambito);
	}
	
	@Override
	public boolean isApplicabile(SpedizioneModel model) {
		Spedizione spedizione = model.getSpedizione();
		boolean condizioneVolume = checkVolume(spedizione);
		boolean condizionePeso = checkPeso(spedizione);
		boolean condizioneDestinazione = checkDestinazione(model);
		boolean applicabile = condizioneVolume && condizionePeso && condizioneDestinazione;
		return applicabile;
	}
	
	private boolean checkDestinazione(SpedizioneModel spedizione) {
		boolean check;
		Indirizzo destinazione = spedizione.getDestinazione();
		String cap = destinazione.getCap();
		String localita = destinazione.getLocalita();
		boolean checkLivigno = !CAP_LIVIGNO.equals(cap) && !LOCALITA_LIVIGNO.equals(localita);
		Cap c = ControllerCap.getInstance().getInfoCap(cap, localita);
		if (c != null) {
			boolean checkDisagiata = c.getBrtDisagiate();
			boolean checkIsole = c.getBrtIsole();
			check = !(checkLivigno || checkDisagiata || checkIsole);
		} else {
			check = false;
		}
		return check;
	}

	private boolean checkPeso(Spedizione spedizione) {
		double peso = spedizione.getPeso() != null ? spedizione.getPeso() : 0;
		boolean check = peso < MAX_PESO;
		return check;
	}

	private boolean checkVolume(Spedizione spedizione) {
		double volume = spedizione.getVolume() != null ? spedizione.getVolume() : 0;
		boolean check = volume < MAX_VOLUME;
		return check;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_BASE;
	}

}
