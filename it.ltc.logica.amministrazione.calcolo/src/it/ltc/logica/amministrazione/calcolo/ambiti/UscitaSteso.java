package it.ltc.logica.amministrazione.calcolo.ambiti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;

public class UscitaSteso extends IAmbitoLogistica {
	
	public static final int ID = 47;

	public UscitaSteso(SottoAmbitoFattura ambito) {
		super(ambito);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Tipo getTipo() {
		return Tipo.LOGISTICA;
	}

}
