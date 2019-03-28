package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public abstract class Istat extends IAmbitoTrasporto {
	
	public static final String CODICE = "ISTAT";
	
	protected Istat(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		return true;
	}

	@Override
	public String toString() {
		return CODICE;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.ISTAT;
	}
	
}
