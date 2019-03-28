package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.FactoryAmbiti;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public class FactoryAmbitiCorriere extends FactoryAmbiti {
	
	private static FactoryAmbitiCorriere instance;

	private FactoryAmbitiCorriere() {
	}

	public static FactoryAmbitiCorriere getInstance() {
		if (instance == null) {
			instance = new FactoryAmbitiCorriere();
		}
		return instance;
	}

	@Override
	public IAmbitoTrasporto getAmbito(Integer id, String valore) {
		SottoAmbitoFattura ambitoTrasporto = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(id);
		IAmbitoTrasporto ambito;
		switch (id) {
			case ContrassegnoSuperioreCorriere.ID : ambito = new ContrassegnoSuperioreCorriere(ambitoTrasporto, valore); break;
			case ContrassegnoInferioreCorriere.ID : ambito = new ContrassegnoInferioreCorriere(ambitoTrasporto, valore); break;
			case IstatCorriere.ID : ambito = IstatCorriere.getInstance(ambitoTrasporto); break;
			case ConsegnaGDOCorriere.ID : ambito = ConsegnaGDOCorriere.getInstance(ambitoTrasporto); break;
			case ConsegnaAppuntamentoCorriere.ID : ambito = ConsegnaAppuntamentoCorriere.getInstance(ambitoTrasporto); break;
			case FuelCorriere.ID : ambito = FuelCorriere.getInstance(ambitoTrasporto); break;
			case Ore12Corriere.ID : ambito = Ore12Corriere.getInstance(ambitoTrasporto); break;
			case Ore10Corriere.ID : ambito = Ore10Corriere.getInstance(ambitoTrasporto); break;
			case SupplementoZoneDisagiateCorriere.ID : ambito = SupplementoZoneDisagiateCorriere.getInstance(ambitoTrasporto); break;
			case ProvaDiConsegna.ID : ambito = ProvaDiConsegna.getInstance(ambitoTrasporto); break;
			case SpedizioneConMoltiColliCorriere.ID : ambito = new SpedizioneConMoltiColliCorriere(ambitoTrasporto, valore); break;
			case SupplementoCampioneCorriere.ID : ambito = SupplementoCampioneCorriere.getInstance(ambitoTrasporto); break;
			case SupplementoLivignoCorriere.ID : ambito = SupplementoLivignoCorriere.getInstance(ambitoTrasporto); break;
			case SupplementoIsoleCorriere.ID : ambito = SupplementoIsoleCorriere.getInstance(ambitoTrasporto); break;
			case NoloEsteroGenerico.ID : ambito = NoloEsteroGenerico.getInstance(ambitoTrasporto); break;
			case ConsegnaPianoCorriere.ID : ambito = ConsegnaPianoCorriere.getInstance(ambitoTrasporto); break;
			case ContrassegnoCorriere.ID : ambito = ContrassegnoCorriere.getInstance(ambitoTrasporto); break;
			case NoloItaliaCorriere.ID : ambito = NoloItaliaCorriere.getInstance(ambitoTrasporto); break;
			case NoloPriorityOre10.ID : ambito = NoloPriorityOre10.getInstance(ambitoTrasporto); break;
			case NoloPriorityOre12.ID : ambito = NoloPriorityOre12.getInstance(ambitoTrasporto); break;
			case ConsegnaPrivatiCorriere.ID : ambito = ConsegnaPrivatiCorriere.getInstance(ambitoTrasporto); break;
			case ForfettarioCorriere.ID : ambito = ForfettarioCorriere.getInstance(ambitoTrasporto); break;
			case Ore9Corriere.ID : ambito = Ore9Corriere.getInstance(ambitoTrasporto); break;
			case Ore1030Corriere.ID : ambito = Ore1030Corriere.getInstance(ambitoTrasporto); break;
			case NoloRegioniSpecificheCorriere.ID : ambito = new NoloRegioniSpecificheCorriere(ambitoTrasporto, valore); break;
			case NoloLightCorriere.ID : ambito = NoloLightCorriere.getInstance(ambitoTrasporto); break;
			case DirittoFissoCorriere.ID : ambito = DirittoFissoCorriere.getInstance(ambitoTrasporto); break;
			case SupplementoAltaUrbanizzazione.ID : ambito = SupplementoAltaUrbanizzazione.getInstance(ambitoTrasporto); break;
			case NoloNazioniSpecificheCorriere.ID : ambito = new NoloNazioniSpecificheCorriere(ambitoTrasporto, valore); break;
			case VolumeSuperioreACorriere.ID : ambito = new VolumeSuperioreACorriere(ambitoTrasporto, valore); break;
			default : throw new IllegalArgumentException("L'ID specificato per l'ambito di corriere non esiste. (ID :" + id + ")");
		}
		return ambito;
	}

}
