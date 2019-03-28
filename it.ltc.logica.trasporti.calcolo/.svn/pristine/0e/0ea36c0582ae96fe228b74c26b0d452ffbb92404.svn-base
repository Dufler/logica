package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import java.util.HashSet;
import java.util.Set;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public class SupplementoElba extends IAmbitoTrasporto {

	private static final Set<String> listaCap = new HashSet<String>();

	public static final int ID = 23;
	
	private static SupplementoElba instance;

	private SupplementoElba(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static SupplementoElba getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new SupplementoElba(ambito);
		}
		return instance;
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		Indirizzo destinazione = spedizione.getDestinazione();
		boolean applicabile = getListaCapIsoleMinori().contains(destinazione.getCap());
		return applicabile;
	}
	
	public static Set<String> getListaCapIsoleMinori() {
		if (listaCap.isEmpty()) {
			//Sonia infame per te solo lame!
			//non si è mai degnata di trovare questi CAP.
			listaCap.add("57036"); //Porto Azzurro
			listaCap.add("57037"); //Porto Ferraio
			listaCap.add("57038"); //Rio Marina
			listaCap.add("57039"); //Rio nell'Elba
		}
		return listaCap;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}

}
