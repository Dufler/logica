package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.DirittoFisso;

public class DirittoFissoCorriere extends DirittoFisso {
	
	public static final int ID = 100;
	
	private static DirittoFissoCorriere instance;

	private DirittoFissoCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static DirittoFissoCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new DirittoFissoCorriere(ambito);
		}
		return instance;
	}

}
