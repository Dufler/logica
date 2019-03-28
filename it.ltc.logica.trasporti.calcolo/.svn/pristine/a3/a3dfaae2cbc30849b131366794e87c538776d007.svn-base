package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ConsegnaOre10;

public class Ore10Corriere extends ConsegnaOre10 {
	
	public static final int ID = 57;
	
	private static Ore10Corriere instance;

	private Ore10Corriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static Ore10Corriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new Ore10Corriere(ambito);
		}
		return instance;
	}

}
