package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ConsegnaSuAppuntamento;

public class ConsegnaSuAppuntamentoTrasporti extends ConsegnaSuAppuntamento {
	
	public static final int ID = 7;
	
	private static ConsegnaSuAppuntamentoTrasporti instance;

	private ConsegnaSuAppuntamentoTrasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static ConsegnaSuAppuntamentoTrasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new ConsegnaSuAppuntamentoTrasporti(ambito);
		}
		return instance;
	}

}
