package it.ltc.logica.trasporti.gui.listini.elements.simulazione.voci;

import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreVociListiniSimulazione extends Ordinatore<ListinoSimulazioneVoce> {

	@Override
	protected int compare(ListinoSimulazioneVoce t1, ListinoSimulazioneVoce t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = t1.getNome().compareTo(t2.getNome()); break;
			case 1 : compare = compareInteger(t1.getIdsottoAmbito(), t2.getIdsottoAmbito()); break;
			case 2 : compare = t1.getDescrizione().compareTo(t2.getDescrizione()); break;
			default : compare = 0;
		}
		return compare;
	}

}
