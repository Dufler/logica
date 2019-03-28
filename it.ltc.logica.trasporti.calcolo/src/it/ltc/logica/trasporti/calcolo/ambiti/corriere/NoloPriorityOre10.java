package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloItalia;

public class NoloPriorityOre10 extends NoloItalia {

	public static final String CODIFICA_SERVIZIO_ORE10 = "O10";
	public static final int ID = 28;
	
	private static NoloPriorityOre10 instance;

	private NoloPriorityOre10(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static NoloPriorityOre10 getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new NoloPriorityOre10(ambito);
		}
		return instance;
	}
	
	@Override
	public boolean isApplicabile(SpedizioneModel model) {
		String servizio = model.getSpedizione().getServizio();
		boolean applicabile = CODIFICA_SERVIZIO_ORE10.equals(servizio);
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}

}
