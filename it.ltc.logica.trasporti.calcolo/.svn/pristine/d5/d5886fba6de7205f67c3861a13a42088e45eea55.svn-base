package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.common.controller.trasporti.ControllerCap;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public abstract class SupplementoIsoleMinori extends IAmbitoTrasporto {
	
	public SupplementoIsoleMinori(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		Indirizzo destinazione = spedizione.getDestinazione();
		Cap cap = ControllerCap.getInstance().getInfoCap(destinazione.getCap(), destinazione.getLocalita());
		boolean applicabile = cap != null ? cap.getBrtIsole() : false;
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}

}
