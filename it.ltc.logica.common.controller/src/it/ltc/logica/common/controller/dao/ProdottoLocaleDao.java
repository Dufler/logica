package it.ltc.logica.common.controller.dao;

import java.util.List;

import it.ltc.database.dao.locali.ProdottoLocaleDaoAstratto;
import it.ltc.logica.common.controller.processi.sincronizzazione.CRUDDaoConProcessi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Prodotto;
import it.ltc.logica.gui.task.Processo;

public class ProdottoLocaleDao extends ProdottoLocaleDaoAstratto implements CRUDDaoConProcessi<Prodotto> {

	protected Processo processo;
	
	public ProdottoLocaleDao(Commessa commessa) {
		super(commessa);
	}
	
	@Override
	protected void aumentaProgresso() {
		if (processo != null)
			processo.aumentaProgresso(1);
	}

	@Override
	public boolean sincronizza(List<Prodotto> lista, Processo processo) {
		this.processo = processo;
		boolean synchronize = mergeEntities(lista);
		this.processo = null; //tolgo il riferimento per il garbage collector.
		return synchronize;
	}

}
