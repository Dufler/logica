package it.ltc.logica.common.controller.dao;

import java.util.List;

import it.ltc.database.dao.locali.SpedizioneLocaleDaoAstratto;
import it.ltc.logica.common.controller.processi.sincronizzazione.CRUDDaoConProcessi;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.task.Processo;

public class SpedizioneLocaleDao extends SpedizioneLocaleDaoAstratto implements CRUDDaoConProcessi<Spedizione> {
	
	protected Processo processo;

	public SpedizioneLocaleDao() {}
	
	public boolean sincronizza(List<Spedizione> spedizioni, Processo processo) {
		this.processo = processo;
		boolean insert = mergeEntities(spedizioni);
		this.processo = null; //tolgo il riferimento per il garbage collector.
		return insert;
	}

	@Override
	protected void aumentaProgresso() {
		if (processo != null)
			processo.aumentaProgresso(1);
	}

}
