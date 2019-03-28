package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.SupplementoLivigno;

public class SupplementoLivignoCorriere extends SupplementoLivigno {
	
	public static final int ID = 52;
	
	private static SupplementoLivignoCorriere instance;

	private SupplementoLivignoCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static SupplementoLivignoCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new SupplementoLivignoCorriere(ambito);
		}
		return instance;
	}

}
