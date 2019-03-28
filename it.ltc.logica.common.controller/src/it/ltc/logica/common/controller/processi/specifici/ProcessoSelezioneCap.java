package it.ltc.logica.common.controller.processi.specifici;

import java.util.List;

import it.ltc.database.dao.locali.CriteriSelezioneCap;
import it.ltc.logica.common.controller.dao.CapLocaleDao;
import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.gui.task.Processo;

public class ProcessoSelezioneCap extends Processo {

	private static final String title = "Filtraggio degli CAP";
	
	private List<Cap> lista;
	private final CapLocaleDao dao;
	private final CriteriSelezioneCap criteri;

	public ProcessoSelezioneCap(CapLocaleDao dao, CriteriSelezioneCap criteri) {
		super(title, -1);
		this.dao = dao;
		this.criteri = criteri;
	}
	
	public List<Cap> getLista() {
		return lista;
	}

	@Override
	public void eseguiOperazioni() throws Exception {
		lista = dao.trovaCorrispondenti(criteri);
	}

}
