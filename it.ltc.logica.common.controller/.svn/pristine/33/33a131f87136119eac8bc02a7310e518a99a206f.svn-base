package it.ltc.logica.common.controller.fatturazione.documento;

import java.util.List;

import it.ltc.logica.database.model.centrale.fatturazione.VoceFattura;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;

public abstract class RealizzatoreDocumentoDiFatturazioneTrasporti extends RealizzatoreDocumentoDiFatturazione {

	protected boolean isNoloBase(VoceFattura v) {
		VoceDiListino voce = controllerListiniClienti.getVoceDiListino(v.getIdVoce());
		int id = voce.getIdSottoAmbito();
		// Se l'ID è quello di:
		// 20 - Nolo Italia
		// 24 - Nolo Estero
		// 28 - Nolo priority ore 10
		// 29 - Nolo priority ore 12
		// 30 - Nolo SUD
		boolean base = (id == 20 || id == 24 || id == 28 || id == 29 || id == 30);
		return base;
	}

	protected Double getCostoNoloBase(List<VoceFattura> voci) {
		double totale = 0;
		for (VoceFattura voce : voci) {
			if (isNoloBase(voce)) {
				totale += voce.getImporto();
			}
		}
		return totale;
	}

	protected Double getTotale(List<VoceFattura> voci) {
		double totale = 0;
		for (VoceFattura voce : voci) {
			totale += voce.getImporto();
		}
		return totale;
	}

}
