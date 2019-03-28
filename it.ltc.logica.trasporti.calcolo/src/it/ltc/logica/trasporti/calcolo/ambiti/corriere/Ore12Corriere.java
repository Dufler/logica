package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ConsegnaOre12;

public class Ore12Corriere extends ConsegnaOre12 {
	
	public static final int ID = 58;
	
	private static Ore12Corriere instance;

	private Ore12Corriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static Ore12Corriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new Ore12Corriere(ambito);
		}
		return instance;
	}

}
