package it.ltc.logica.trasporti.calcolo.ambiti.simulazione;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public class PrioritySimulazione extends IAmbitoTrasporto {
	
	public static final int ID = 91;
	
	public static final String CODIFICA_SERVIZIO_ORE10 = "O10";
	public static final String CODIFICA_SERVIZIO_ORE12 = "O12";
	
	private static PrioritySimulazione instance;

	private PrioritySimulazione(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static PrioritySimulazione getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new PrioritySimulazione(ambito);
		}
		return instance;
	}

	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}

	@Override
	public boolean isApplicabile(SpedizioneModel model) {
		String servizio = model.getSpedizione().getServizio();
		boolean o10 = CODIFICA_SERVIZIO_ORE10.equals(servizio);
		boolean o12 = CODIFICA_SERVIZIO_ORE12.equals(servizio);
		boolean applicabile = o10 || o12;
		return applicabile;
	}

}
