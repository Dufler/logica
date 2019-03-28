package it.ltc.logica.amministrazione.calcolo.ambiti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;

public class IngressoSteso extends IAmbitoLogistica {
	
	public static final int ID = 37;

	public IngressoSteso(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	@Override
	public Tipo getTipo() {
		return Tipo.LOGISTICA;
	}

}
