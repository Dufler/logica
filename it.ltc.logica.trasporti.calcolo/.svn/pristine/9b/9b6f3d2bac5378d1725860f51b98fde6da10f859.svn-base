package it.ltc.logica.trasporti.calcolo.ambiti.giacenza;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.Fuel;

public class FuelGiacenza extends Fuel {
	
	public static final int ID = 83;
	
	private static FuelGiacenza instance;

	private FuelGiacenza(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static FuelGiacenza getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new FuelGiacenza(ambito);
		}
		return instance;
	}

}
