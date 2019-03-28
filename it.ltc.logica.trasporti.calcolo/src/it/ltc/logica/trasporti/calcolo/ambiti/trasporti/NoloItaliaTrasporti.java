package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloItalia;

public class NoloItaliaTrasporti extends NoloItalia {
	
	public static final int ID = 20;
	
	private static NoloItaliaTrasporti instance;

	private NoloItaliaTrasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static NoloItaliaTrasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new NoloItaliaTrasporti(ambito);
		}
		return instance;
	}

}
