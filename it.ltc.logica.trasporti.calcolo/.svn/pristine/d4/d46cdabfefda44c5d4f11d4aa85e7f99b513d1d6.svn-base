package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.Forfettario;

public class ForfettarioCorriere extends Forfettario {
	
	public static final int ID = 86;
	
	private static ForfettarioCorriere instance;

	private ForfettarioCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static ForfettarioCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new ForfettarioCorriere(ambito);
		}
		return instance;
	}

}
