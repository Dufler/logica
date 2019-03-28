package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloItalia;

public class NoloPriorityOre12 extends NoloItalia {

	public static final String CODIFICA_SERVIZIO_ORE12 = "O12";
	public static final int ID = 29;
	
	private static NoloPriorityOre12 instance;

	private NoloPriorityOre12(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static NoloPriorityOre12 getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new NoloPriorityOre12(ambito);
		}
		return instance;
	}
	
	@Override
	public boolean isApplicabile(SpedizioneModel model) {
		String servizio = model.getSpedizione().getServizio();
		boolean applicabile = CODIFICA_SERVIZIO_ORE12.equals(servizio);
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}
	
}
