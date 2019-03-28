package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloEstero;

public class NoloEsteroTrasporti extends NoloEstero {
	
	public static final int ID = 24;
	
	private static NoloEsteroTrasporti instance;

	private NoloEsteroTrasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static NoloEsteroTrasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new NoloEsteroTrasporti(ambito);
		}
		return instance;
	}

}
