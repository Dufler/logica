package it.ltc.logica.trasporti.calcolo.ambiti.simulazione;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloEstero;

public class NoloEsteroSimulazione extends NoloEstero {
	
	public static final int ID = 78;
	
	private static NoloEsteroSimulazione instance;

	private NoloEsteroSimulazione(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static NoloEsteroSimulazione getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new NoloEsteroSimulazione(ambito);
		}
		return instance;
	}

}
