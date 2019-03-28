package it.ltc.logica.trasporti.gui.listini.elements.simulazione.voci;

import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroVociListinoSimulazione extends FiltroTabella<ListinoSimulazioneVoce, CriteriFiltraggioSoloTesto> {

	@Override
	protected boolean checkElemento(ListinoSimulazioneVoce item) {
		boolean checkNome = checkStringValue(criteri.getTesto(), item.getNome());
		boolean checkDescrizione = checkStringValue(criteri.getTesto(), item.getDescrizione());
		return checkNome || checkDescrizione;
	}

}
