package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

public abstract class NoloEstero extends NoloItalia {
	
	private static final String ITALIA = "ITA";
	public static final String CODICE = "NOLO_ESTERO";
	
	public NoloEstero(SottoAmbitoFattura ambito) {
		super(ambito);
	}
	
	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		Indirizzo destinazione = spedizione.getDestinazione();
		boolean applicabile = !ITALIA.equals(destinazione.getNazione());
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_BASE;
	}

}
