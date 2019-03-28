package it.ltc.logica.common.controller.dao;

import java.util.List;

import it.ltc.database.dao.locali.CapLocaleDaoAstratto;
import it.ltc.logica.common.controller.processi.sincronizzazione.CRUDDaoConProcessi;
import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.gui.task.Processo;

public class CapLocaleDao extends CapLocaleDaoAstratto implements CRUDDaoConProcessi<Cap> {
	
	protected Processo processo;

	public CapLocaleDao() {}
	
	public boolean sincronizza(List<Cap> cap, Processo processo) {
		this.processo = processo;
		boolean synchronize = mergeEntities(cap);
		this.processo = null; //tolgo il riferimento per il garbage collector.
		return synchronize;
	}

	@Override
	protected void aumentaProgresso() {
		if (processo != null)
			processo.aumentaProgresso(1);
	}
	
	@Override
	public String toString() {
		return "";
	}

}
