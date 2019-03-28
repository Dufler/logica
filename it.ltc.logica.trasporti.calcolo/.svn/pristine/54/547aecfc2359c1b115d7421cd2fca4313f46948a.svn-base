package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.Fuel;

public class FuelTrasporti extends Fuel {
	
	public static final int ID = 12;
	
	private static FuelTrasporti instance;

	private FuelTrasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static FuelTrasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new FuelTrasporti(ambito);
		}
		return instance;
	}

}
