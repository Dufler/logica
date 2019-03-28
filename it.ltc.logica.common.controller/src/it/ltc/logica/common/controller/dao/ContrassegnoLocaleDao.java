package it.ltc.logica.common.controller.dao;

import java.util.List;

import it.ltc.database.dao.locali.ContrassegnoLocaleDaoAstratto;
import it.ltc.logica.common.controller.processi.sincronizzazione.CRUDDaoConProcessi;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.gui.task.Processo;

public class ContrassegnoLocaleDao extends ContrassegnoLocaleDaoAstratto implements CRUDDaoConProcessi<Contrassegno> {
	
	protected Processo processo;

	public ContrassegnoLocaleDao() {}
	
	public boolean sincronizza(List<Contrassegno> contrassegni, Processo processo) {
		this.processo = processo;
		boolean synchronize = mergeEntities(contrassegni);
		this.processo = null; //tolgo il riferimento per il garbage collector.
		return synchronize;
	}

	@Override
	protected void aumentaProgresso() {
		if (processo != null)
			processo.aumentaProgresso(1);
	}

}
