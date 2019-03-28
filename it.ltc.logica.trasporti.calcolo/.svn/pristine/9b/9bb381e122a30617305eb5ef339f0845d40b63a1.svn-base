package it.ltc.logica.trasporti.calcolo.ambiti.simulazione;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.Fuel;

public class FuelSimulazione extends Fuel {
	
	public static final int ID = 81;
	
	private static FuelSimulazione instance;

	private FuelSimulazione(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static FuelSimulazione getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new FuelSimulazione(ambito);
		}
		return instance;
	}

}
