package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public abstract class Forfettario extends IAmbitoTrasporto {

	public Forfettario(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	@Override
	public Tipo getTipo() {
		return Tipo.FORFETTARIO;
	}

	@Override
	public boolean isApplicabile(SpedizioneModel model) {
		//Non è mai applicabile, va sempre inserito a mano in fase di fatturazione.
		return false;
	}
	
	@Override
	public double getValore(SpedizioneModel spedizione, MVoceDiListino<SpedizioneModel> voce) {
		//Non esegue mai un calcolo, il valore va inserito a mano.
		return 0;
	}

}
