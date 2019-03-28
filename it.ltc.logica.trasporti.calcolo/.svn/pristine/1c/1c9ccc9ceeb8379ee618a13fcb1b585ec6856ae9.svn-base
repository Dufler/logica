package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ConsegnaOre10;

public class Ore10Trasporti extends ConsegnaOre10 {
	
	public static final int ID = 5;
	
	private static Ore10Trasporti instance;

	private Ore10Trasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static Ore10Trasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new Ore10Trasporti(ambito);
		}
		return instance;
	}

}
