package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ConsegnaAiPrivati;

public class ConsegnaPrivatiCorriere extends ConsegnaAiPrivati {
	
	public static final int ID = 85;
	
	private static ConsegnaPrivatiCorriere instance;

	private ConsegnaPrivatiCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static ConsegnaPrivatiCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new ConsegnaPrivatiCorriere(ambito);
		}
		return instance;
	}

}
