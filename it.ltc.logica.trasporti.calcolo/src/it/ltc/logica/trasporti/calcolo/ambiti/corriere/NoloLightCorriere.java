package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloLight;

public class NoloLightCorriere extends NoloLight {
	
	public static final int ID = 97;
	
	private static NoloLightCorriere instance;

	private NoloLightCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static NoloLightCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new NoloLightCorriere(ambito);
		}
		return instance;
	}

}
