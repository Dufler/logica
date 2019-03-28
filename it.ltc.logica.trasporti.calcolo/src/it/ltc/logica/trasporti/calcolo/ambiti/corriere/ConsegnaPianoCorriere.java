package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ConsegnaAlPiano;

public class ConsegnaPianoCorriere extends ConsegnaAlPiano {
	
	public static final int ID = 35;
	
	private static ConsegnaPianoCorriere instance;

	private ConsegnaPianoCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static ConsegnaPianoCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new ConsegnaPianoCorriere(ambito);
		}
		return instance;
	}

}
