package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ContrassegnoInferioreA;

public class ContrassegnoInferioreCorriere extends ContrassegnoInferioreA {
	
	public static final int ID = 64;
	
	public ContrassegnoInferioreCorriere(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
	}

}
