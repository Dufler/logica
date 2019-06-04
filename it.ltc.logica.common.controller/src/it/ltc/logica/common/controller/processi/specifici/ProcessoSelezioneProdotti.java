package it.ltc.logica.common.controller.processi.specifici;

import java.util.List;

import it.ltc.logica.common.controller.dao.ProdottoLocaleDao;
import it.ltc.logica.database.model.prodotto.Prodotto;
import it.ltc.logica.gui.task.Processo;

public class ProcessoSelezioneProdotti extends Processo {

	private static final String title = "Filtraggio dei prodotti";
	
	private List<Prodotto> lista;
	private final ProdottoLocaleDao dao;
	private final Prodotto filtro;

	public ProcessoSelezioneProdotti(ProdottoLocaleDao dao, Prodotto filtro) {
		super(title, -1);
		this.dao = dao;
		this.filtro = filtro;
	}
	
	public List<Prodotto> getLista() {
		return lista;
	}

	@Override
	public void eseguiOperazioni() throws Exception {
		lista = dao.trovaCorrispondenti(filtro);
	}

}
