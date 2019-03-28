package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ConsegnaOre10;

public class Ore9Corriere extends ConsegnaOre10 {
	
	public static final int ID = 87;
	
	private static Ore9Corriere instance;

	private Ore9Corriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static Ore9Corriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new Ore9Corriere(ambito);
		}
		return instance;
	}

}
