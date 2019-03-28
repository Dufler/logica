package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.SupplementoCampioneItalia;

public class SupplementoCampioneCorriere extends SupplementoCampioneItalia {
	
	public static final int ID = 53;
	
	private static SupplementoCampioneCorriere instance;

	private SupplementoCampioneCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static SupplementoCampioneCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new SupplementoCampioneCorriere(ambito);
		}
		return instance;
	}

}
