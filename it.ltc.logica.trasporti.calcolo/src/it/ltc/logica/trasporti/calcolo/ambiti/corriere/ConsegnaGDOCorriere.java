package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ConsegnaAGDO;

public class ConsegnaGDOCorriere extends ConsegnaAGDO {
	
	public static final int ID = 61;
	
	private static ConsegnaGDOCorriere instance;

	private ConsegnaGDOCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static ConsegnaGDOCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new ConsegnaGDOCorriere(ambito);
		}
		return instance;
	}

}
