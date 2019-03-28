package it.ltc.logica.trasporti.calcolo.ambiti.simulazione;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloItalia;

public class NoloSimulazione extends NoloItalia {
	
	public static final int ID = 76;
	
	private static NoloSimulazione instance;

	private NoloSimulazione(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static NoloSimulazione getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new NoloSimulazione(ambito);
		}
		return instance;
	}

}
