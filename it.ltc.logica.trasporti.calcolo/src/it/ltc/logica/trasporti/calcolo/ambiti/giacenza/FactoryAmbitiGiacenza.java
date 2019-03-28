package it.ltc.logica.trasporti.calcolo.ambiti.giacenza;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.FactoryAmbiti;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public class FactoryAmbitiGiacenza extends FactoryAmbiti {
	
	private static FactoryAmbitiGiacenza instance;

	private FactoryAmbitiGiacenza() {
	}

	public static FactoryAmbitiGiacenza getInstance() {
		if (instance == null) {
			instance = new FactoryAmbitiGiacenza();
		}
		return instance;
	}
	
	public static boolean isAmbitoNoloBase(Integer id) {
		boolean noloBase = false;
		if (id != null) {
			if (id == GiacenzaReso.ID || id == GiacenzaRiconsegna.ID || id == GiacenzaRiconsegnaRegioneSpecifica.ID)
				noloBase = true;
		}
		return noloBase;
	}

	@Override
	public IAmbitoTrasporto getAmbito(Integer id, String valore) {
		SottoAmbitoFattura ambitoTrasporto = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(id);
		IAmbitoTrasporto ambito;
		switch (id) {
			case GiacenzaApertura.ID : ambito = GiacenzaApertura.getInstance(ambitoTrasporto); break;
			case GiacenzaReso.ID : ambito = GiacenzaReso.getInstance(ambitoTrasporto); break;
			case GiacenzaRiconsegna.ID : ambito = GiacenzaRiconsegna.getInstance(ambitoTrasporto); break;
			case GiacenzaRiconsegnaRegioneSpecifica.ID : ambito = new GiacenzaRiconsegnaRegioneSpecifica(ambitoTrasporto, valore); break;
			case GiacenzaSosta.ID : ambito = new GiacenzaSosta(ambitoTrasporto, valore); break;
			case FuelGiacenza.ID : ambito = FuelGiacenza.getInstance(ambitoTrasporto); break;
			case SpedizioneConMoltiColliGiacenza.ID : ambito = new SpedizioneConMoltiColliGiacenza(ambitoTrasporto, valore); break;
			case IstatGiacenza.ID : ambito = IstatGiacenza.getInstance(ambitoTrasporto); break;
			default : throw new IllegalArgumentException("L'ID specificato per l'ambito di giacenza non esiste.");
		}
		return ambito;
	}

}
