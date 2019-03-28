package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ConsegnaSuAppuntamento;

public class ConsegnaAppuntamentoCorriere extends ConsegnaSuAppuntamento {
	
	public static final int ID = 60;
	
	private static ConsegnaAppuntamentoCorriere instance;

	private ConsegnaAppuntamentoCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static ConsegnaAppuntamentoCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new ConsegnaAppuntamentoCorriere(ambito);
		}
		return instance;
	}

}
