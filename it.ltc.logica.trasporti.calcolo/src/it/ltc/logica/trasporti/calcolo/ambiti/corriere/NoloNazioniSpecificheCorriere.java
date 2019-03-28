package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloNazioniSpecifiche;

public class NoloNazioniSpecificheCorriere extends NoloNazioniSpecifiche {
	
	public static final int ID = 111;
	
	public NoloNazioniSpecificheCorriere(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
	}

}
