package it.ltc.logica.trasporti.calcolo.ambiti.simulazione;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloNazioniSpecifiche;

public class NoloNazioniSpecificheSimulazione extends NoloNazioniSpecifiche {
	
	public static final int ID = 132;

	public NoloNazioniSpecificheSimulazione(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
	}

}
