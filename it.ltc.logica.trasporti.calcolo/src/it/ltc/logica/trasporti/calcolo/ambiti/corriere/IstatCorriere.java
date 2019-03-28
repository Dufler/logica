package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.Istat;

public class IstatCorriere extends Istat {
	
	public static final int ID = 62;
	
	private static IstatCorriere instance;

	private IstatCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static IstatCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new IstatCorriere(ambito);
		}
		return instance;
	}

}
