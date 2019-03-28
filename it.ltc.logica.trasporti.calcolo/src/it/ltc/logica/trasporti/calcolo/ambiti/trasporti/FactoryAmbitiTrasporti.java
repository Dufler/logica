package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.FactoryAmbiti;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public class FactoryAmbitiTrasporti extends FactoryAmbiti {

	private static FactoryAmbitiTrasporti instance;

	private FactoryAmbitiTrasporti() {
	}

	public static FactoryAmbitiTrasporti getInstance() {
		if (instance == null) {
			instance = new FactoryAmbitiTrasporti();
		}
		return instance;
	}
	
	public boolean isAmbitoNoloBase(Integer id) {
		boolean noloBase = false;
		if (id != null) {
			if (id == NoloItaliaTrasporti.ID || id == NoloSUDTrasporti.ID || id == NoloEsteroTrasporti.ID)
				noloBase = true;
		}
		return noloBase;
	}
	
	@Override
	public IAmbitoTrasporto getAmbito(Integer id, String valore) {
		SottoAmbitoFattura ambitoTrasporto = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(id);
		IAmbitoTrasporto ambito;
		switch (id) {
			case ProvaDiConsegna.ID : ambito = ProvaDiConsegna.getInstance(ambitoTrasporto); break;
			case PriorityMonocollo.ID : ambito = PriorityMonocollo.getInstance(ambitoTrasporto); break;
			case NoloSUDTrasporti.ID : ambito = new NoloSUDTrasporti(ambitoTrasporto, valore); break;
			case SupplementoLivignoTrasporti.ID : ambito = SupplementoLivignoTrasporti.getInstance(ambitoTrasporto); break;
			case SupplementoIsoleVeneziaTrasporti.ID : ambito = SupplementoIsoleVeneziaTrasporti.getInstance(ambitoTrasporto); break;
			case SupplementoIsoleMinoriTrasporti.ID : ambito = SupplementoIsoleMinoriTrasporti.getInstance(ambitoTrasporto); break;
			case NoloEsteroTrasporti.ID : ambito = NoloEsteroTrasporti.getInstance(ambitoTrasporto); break;
			case SupplementoElba.ID : ambito = SupplementoElba.getInstance(ambitoTrasporto); break;
			case SupplementoZoneDisagiateTrasporti.ID : ambito = SupplementoZoneDisagiateTrasporti.getInstance(ambitoTrasporto); break;
			case SupplementoCampioneTrasporti.ID : ambito = SupplementoCampioneTrasporti.getInstance(ambitoTrasporto); break;
			case NoloItaliaTrasporti.ID : ambito = NoloItaliaTrasporti.getInstance(ambitoTrasporto); break;
			case SpedizioneConMoltiColliTrasporti.ID : ambito = new SpedizioneConMoltiColliTrasporti(ambitoTrasporto, valore); break;
			case IstatTrasporti.ID : ambito = IstatTrasporti.getInstance(ambitoTrasporto); break;
			case FuelTrasporti.ID : ambito = FuelTrasporti.getInstance(ambitoTrasporto); break;
			case ContrassegnoSuperioreTrasporti.ID : ambito = new ContrassegnoSuperioreTrasporti(ambitoTrasporto, valore); break;
			case ContrassegnoInferioreTrasporti.ID : ambito = new ContrassegnoInferioreTrasporti(ambitoTrasporto, valore); break;
			case ContrassegnoTrasporti.ID : ambito = ContrassegnoTrasporti.getInstance(ambitoTrasporto); break;
			case ConsegnaSuAppuntamentoTrasporti.ID : ambito = ConsegnaSuAppuntamentoTrasporti.getInstance(ambitoTrasporto); break;
			case Ore12Trasporti.ID : ambito = Ore12Trasporti.getInstance(ambitoTrasporto); break;
			case Ore10Trasporti.ID : ambito = Ore10Trasporti.getInstance(ambitoTrasporto); break;
			case ConsegnaGDOTrasporti.ID : ambito = ConsegnaGDOTrasporti.getInstance(ambitoTrasporto); break;
			case ConsegnaPrivatiTrasporti.ID : ambito = ConsegnaPrivatiTrasporti.getInstance(ambitoTrasporto); break;
			case ConsegnaPianoTrasporti.ID : ambito = ConsegnaPianoTrasporti.getInstance(ambitoTrasporto); break;
			case AnnullamentoContrassegno.ID : ambito = AnnullamentoContrassegno.getInstance(ambitoTrasporto); break;
			case NoloNazioniSpecificheTrasporti.ID : ambito = new NoloNazioniSpecificheTrasporti(ambitoTrasporto, valore); break;
			case VolumeSuperioreATrasporti.ID : ambito = new VolumeSuperioreATrasporti(ambitoTrasporto, valore); break;
			default : throw new IllegalArgumentException("L'ID specificato per l'ambito di trasporto non esiste.");
		}
		return ambito;
	}

}
