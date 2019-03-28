package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.common.controller.trasporti.ControllerCap;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

public abstract class SupplementoZoneDisagiate extends NoloItalia {
	
	public SupplementoZoneDisagiate(SottoAmbitoFattura ambito) {
		super(ambito);
	}
	
	@Override
	public boolean isApplicabile(SpedizioneModel model) {
		Indirizzo destinazione = model.getDestinazione();
		String capDestinazione = destinazione.getCap();
		String localitaDestinazione = destinazione.getLocalita();
		Cap cap = ControllerCap.getInstance().getInfoCap(capDestinazione, localitaDestinazione);
		boolean applicabile = (cap != null) ? cap.getBrtDisagiate() : false;
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}

}
