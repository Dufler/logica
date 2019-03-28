package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ConsegnaOre12;

public class Ore12Trasporti extends ConsegnaOre12 {
	
	public static final int ID = 6;
	
	private static Ore12Trasporti instance;

	private Ore12Trasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static Ore12Trasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new Ore12Trasporti(ambito);
		}
		return instance;
	}

}
