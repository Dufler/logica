package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloEstero;

public class NoloEsteroGenerico extends NoloEstero {
	
	public static final int ID = 50;
	
	private static NoloEsteroGenerico instance;

	private NoloEsteroGenerico(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static NoloEsteroGenerico getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new NoloEsteroGenerico(ambito);
		}
		return instance;
	}

}
