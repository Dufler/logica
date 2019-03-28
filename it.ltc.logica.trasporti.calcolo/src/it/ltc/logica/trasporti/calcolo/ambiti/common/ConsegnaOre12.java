package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public abstract class ConsegnaOre12 extends IAmbitoTrasporto {

	public static final String CODIFICA_SERVIZIO_ORE12 = "O12";
	
	public ConsegnaOre12(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		String servizio = spedizione.getSpedizione().getServizio();
		boolean applicabile = CODIFICA_SERVIZIO_ORE12.equals(servizio);
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}
	
}
