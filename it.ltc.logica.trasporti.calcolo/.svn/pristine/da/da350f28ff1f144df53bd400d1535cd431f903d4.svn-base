package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public abstract class ConsegnaSuAppuntamento extends IAmbitoTrasporto {
	
	public ConsegnaSuAppuntamento(SottoAmbitoFattura ambito) {
		super(ambito);
	}
	
	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		boolean applicabile = false;
		Indirizzo destinazione = spedizione.getDestinazione();
		if (destinazione != null) {
			applicabile = destinazione.getConsegnaAppuntamento();
		}
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}
	
}
