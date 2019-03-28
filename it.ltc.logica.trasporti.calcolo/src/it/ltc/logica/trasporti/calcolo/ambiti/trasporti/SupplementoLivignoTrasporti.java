package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.SupplementoLivigno;

public class SupplementoLivignoTrasporti extends SupplementoLivigno {
	
	public static final int ID = 27;
	
	private static SupplementoLivignoTrasporti instance;

	private SupplementoLivignoTrasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static SupplementoLivignoTrasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new SupplementoLivignoTrasporti(ambito);
		}
		return instance;
	}

}
