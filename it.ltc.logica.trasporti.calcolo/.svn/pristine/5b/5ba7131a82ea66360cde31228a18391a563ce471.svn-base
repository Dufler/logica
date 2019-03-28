package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.Istat;

public class IstatTrasporti extends Istat {
	
	public static final int ID = 18;
	
	private static IstatTrasporti instance;

	private IstatTrasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static IstatTrasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new IstatTrasporti(ambito);
		}
		return instance;
	}

}
