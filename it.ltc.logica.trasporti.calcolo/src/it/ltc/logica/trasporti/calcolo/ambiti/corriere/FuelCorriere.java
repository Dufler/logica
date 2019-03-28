package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.Fuel;

public class FuelCorriere extends Fuel {
	
	public static final int ID = 59;
	
	private static FuelCorriere instance;

	private FuelCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static FuelCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new FuelCorriere(ambito);
		}
		return instance;
	}

}
