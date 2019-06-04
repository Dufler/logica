package it.ltc.logica.common.controller.processi.specifici;

import java.util.List;

import it.ltc.logica.common.controller.dao.ProdottoLocaleDao;
import it.ltc.logica.database.model.prodotto.Modello;
import it.ltc.logica.gui.task.Processo;

public class ProcessoSelezioneModelli extends Processo {

	private static final String title = "Ricerca dei modelli";
	
	private List<Modello> lista;
	private final ProdottoLocaleDao dao;
	private final Modello filtro;

	public ProcessoSelezioneModelli(ProdottoLocaleDao dao, Modello filtro) {
		super(title, -1);
		this.dao = dao;
		this.filtro = filtro;
	}
	
	public List<Modello> getLista() {
		return lista;
	}

	@Override
	public void eseguiOperazioni() throws Exception {
		lista = dao.trovaModelli(filtro);
	}

}
