package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloNazioniSpecifiche;

public class NoloNazioniSpecificheTrasporti extends NoloNazioniSpecifiche {
	
	public static final int ID = 133;
	
	public NoloNazioniSpecificheTrasporti(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
	}
}
