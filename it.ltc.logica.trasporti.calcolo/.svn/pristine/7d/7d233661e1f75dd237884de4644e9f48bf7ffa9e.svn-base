package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.SupplementoIsoleMinori;

public class SupplementoIsoleCorriere extends SupplementoIsoleMinori {

	public static final int ID = 51;
	
	private static SupplementoIsoleCorriere instance;

	private SupplementoIsoleCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static SupplementoIsoleCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new SupplementoIsoleCorriere(ambito);
		}
		return instance;
	}
}
