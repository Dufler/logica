package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ConsegnaOre10;

public class Ore1030Corriere extends ConsegnaOre10 {
	
	public static final int ID = 88;
	
	private static Ore1030Corriere instance;

	private Ore1030Corriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static Ore1030Corriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new Ore1030Corriere(ambito);
		}
		return instance;
	}

}
