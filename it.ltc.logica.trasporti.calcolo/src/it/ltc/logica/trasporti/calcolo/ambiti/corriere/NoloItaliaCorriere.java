package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloItalia;

public class NoloItaliaCorriere extends NoloItalia {
	
	public static final int ID = 33;
	
	private static NoloItaliaCorriere instance;

	private NoloItaliaCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static NoloItaliaCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new NoloItaliaCorriere(ambito);
		}
		return instance;
	}

}
