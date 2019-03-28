package it.ltc.logica.common.controller.dao;

import java.util.List;

import it.ltc.database.dao.locali.GiacenzaLocaleDaoAstratto;
import it.ltc.logica.common.controller.processi.sincronizzazione.CRUDDaoConProcessi;
import it.ltc.logica.database.model.centrale.trasporti.Giacenza;
import it.ltc.logica.gui.task.Processo;

public class GiacenzaLocaleDao extends GiacenzaLocaleDaoAstratto implements CRUDDaoConProcessi<Giacenza> {
	
	protected Processo processo;

	public GiacenzaLocaleDao() {}
	
	public boolean sincronizza(List<Giacenza> giacenze, Processo processo) {
		this.processo = processo;
		boolean synchronize = mergeEntities(giacenze);
		this.processo = null; //tolgo il riferimento per il garbage collector.
		return synchronize;
	}

	@Override
	protected void aumentaProgresso() {
		if (processo != null)
			processo.aumentaProgresso(1);
	}

}
