package it.ltc.logica.trasporti.calcolo.ambiti.common;

import java.util.HashSet;
import java.util.Set;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public abstract class SupplementoIsoleVenezia extends IAmbitoTrasporto {

	private static final Set<String> listaCap = new HashSet<String>();
	
	public SupplementoIsoleVenezia(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		Indirizzo destinazione = spedizione.getDestinazione();
		boolean applicabile = getListaCapIsoleVenezia().contains(destinazione.getCap());
		return applicabile;
	}
	
	public static Set<String> getListaCapIsoleVenezia() {
		if (listaCap.isEmpty()) {
			//Sonia infame per te solo lame!
			//non si è mai degnata di trovare questi CAP.
			listaCap.add("30123");
			listaCap.add("30133");
			listaCap.add("30141");
			listaCap.add("30142");
		}
		return listaCap;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}

}
