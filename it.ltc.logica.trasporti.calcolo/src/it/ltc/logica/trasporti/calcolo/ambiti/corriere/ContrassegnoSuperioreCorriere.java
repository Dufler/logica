package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ContrassegnoSuperioreA;

public class ContrassegnoSuperioreCorriere extends ContrassegnoSuperioreA {

	public static final int ID = 63;

	public ContrassegnoSuperioreCorriere(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
	}
	
}
