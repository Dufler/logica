package it.ltc.logica.trasporti.calcolo.ambiti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

public class Fuel extends IAmbitoTrasporto {
	
	public Fuel(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static final String CODICE = "FUEL";
	public static final String CODICE_FISSO = "FUEL_FISSO";
	public static final String CODICE_VARIABILE = "FUEL_VARIABILE";
	public static final int ID = 12;

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
		return Tipo.FUEL;
	}

}
