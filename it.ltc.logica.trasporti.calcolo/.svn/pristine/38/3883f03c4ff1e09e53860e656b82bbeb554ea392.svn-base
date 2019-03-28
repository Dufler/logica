package it.ltc.logica.trasporti.calcolo.ambiti.simulazione;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.Istat;

public class IstatSimulazione extends Istat {
	
	public static final int ID = 80;
	
	private static IstatSimulazione instance;

	private IstatSimulazione(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static IstatSimulazione getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new IstatSimulazione(ambito);
		}
		return instance;
	}

}
