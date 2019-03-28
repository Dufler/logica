package it.ltc.logica.amministrazione.calcolo.ambiti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;

public class IngressoAppeso extends IAmbitoLogistica {
	
	public static final int ID = 36;

	public IngressoAppeso(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	@Override
	public Tipo getTipo() {
		return IAmbitoLogistica.Tipo.LOGISTICA;
	}

}
