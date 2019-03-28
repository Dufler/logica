package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public class PriorityMonocollo extends IAmbitoTrasporto {

	public static final String CODIFICA_SERVIZIO_ORE10 = "O10";
	public static final String CODIFICA_SERVIZIO_ORE12 = "O12";
	
	public static final int ID = 31;
	
	private static PriorityMonocollo instance;

	private PriorityMonocollo(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static PriorityMonocollo getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new PriorityMonocollo(ambito);
		}
		return instance;
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		String servizio = spedizione.getSpedizione().getServizio();
		boolean checkServizio = CODIFICA_SERVIZIO_ORE10.equals(servizio) || CODIFICA_SERVIZIO_ORE12.equals(servizio);
		boolean checkColli = spedizione.getSpedizione().getColli() == 1;
		boolean applicabile = checkServizio && checkColli;
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}

}
