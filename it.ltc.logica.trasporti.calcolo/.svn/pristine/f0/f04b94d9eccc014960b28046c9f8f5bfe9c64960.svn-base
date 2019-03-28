package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.SupplementoCampioneItalia;

public class SupplementoCampioneTrasporti extends SupplementoCampioneItalia {
	
	public static final int ID = 21;
	
	private static SupplementoCampioneTrasporti instance;

	private SupplementoCampioneTrasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static SupplementoCampioneTrasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new SupplementoCampioneTrasporti(ambito);
		}
		return instance;
	}

}
