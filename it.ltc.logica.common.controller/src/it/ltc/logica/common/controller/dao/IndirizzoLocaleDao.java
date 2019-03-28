package it.ltc.logica.common.controller.dao;

import java.util.List;

import it.ltc.database.dao.locali.IndirizzoLocaleDaoAstratto;
import it.ltc.logica.common.controller.processi.sincronizzazione.CRUDDaoConProcessi;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.task.Processo;

public class IndirizzoLocaleDao extends IndirizzoLocaleDaoAstratto implements CRUDDaoConProcessi<Indirizzo> {
	
	protected Processo processo;

	public IndirizzoLocaleDao() {}
	
	public boolean sincronizza(List<Indirizzo> indirizzi, Processo processo) {
		this.processo = processo;
		boolean synchronize = mergeEntities(indirizzi);
		this.processo = null; //tolgo il riferimento per fare pulizia.
		return synchronize;
	}

	@Override
	protected void aumentaProgresso() {
		if (processo != null)
			processo.aumentaProgresso(1);
	}

}
