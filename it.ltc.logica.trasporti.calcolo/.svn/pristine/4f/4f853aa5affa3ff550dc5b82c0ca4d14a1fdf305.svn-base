package it.ltc.logica.trasporti.calcolo.ambiti.giacenza;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.Istat;

public class IstatGiacenza extends Istat {
	
	public static final int ID = 90;
	
	private static IstatGiacenza instance;

	private IstatGiacenza(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static IstatGiacenza getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new IstatGiacenza(ambito);
		}
		return instance;
	}

}
