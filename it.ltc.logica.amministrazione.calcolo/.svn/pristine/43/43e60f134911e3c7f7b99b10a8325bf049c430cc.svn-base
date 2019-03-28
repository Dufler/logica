package it.ltc.logica.amministrazione.calcolo.ambiti;

import java.util.HashMap;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;

public class FactoryAmbiti {

	private static final HashMap<Integer, IAmbitoLogistica> mappaAmbiti = new HashMap<Integer, IAmbitoLogistica>();
	
	public static IAmbitoLogistica getAmbito(Integer id) {
		if (!mappaAmbiti.containsKey(id)) {
			IAmbitoLogistica ambito = creaAmbito(id);
			mappaAmbiti.put(id, ambito);
		}
		return mappaAmbiti.get(id);
	}
	
	private static IAmbitoLogistica creaAmbito(Integer id) {
		SottoAmbitoFattura ambitoAmministrazione = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(id);
		IAmbitoLogistica ambito;
		switch (id) {
			case IngressoAppeso.ID : ambito = new IngressoAppeso(ambitoAmministrazione); break;
			case IngressoSteso.ID : ambito = new IngressoSteso(ambitoAmministrazione); break;
			case IngressoCalzatura.ID : ambito = new IngressoCalzatura(ambitoAmministrazione); break;
			case OrdineWeb.ID : ambito = new OrdineWeb(ambitoAmministrazione); break;
			case UscitaAppeso.ID : ambito = new UscitaAppeso(ambitoAmministrazione); break;
			case UscitaSteso.ID : ambito = new UscitaSteso(ambitoAmministrazione); break;
			case UscitaCalzatura.ID : ambito = new UscitaCalzatura(ambitoAmministrazione); break;
			default : ambito = null;
		}
		return ambito;
	}

}
