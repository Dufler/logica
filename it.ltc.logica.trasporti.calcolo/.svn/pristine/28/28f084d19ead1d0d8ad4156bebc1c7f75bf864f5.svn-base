package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.AmbitoContrassegno;

public class ContrassegnoCorriere extends AmbitoContrassegno {
	
	public static final int ID = 34;
	
	private static ContrassegnoCorriere instance;

	private ContrassegnoCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static ContrassegnoCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new ContrassegnoCorriere(ambito);
		}
		return instance;
	}

}
