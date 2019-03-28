package it.ltc.logica.common.controller.processi.specifici;

import java.util.List;

import it.ltc.database.dao.locali.CriteriSelezioneIndirizzi;
import it.ltc.logica.common.controller.dao.IndirizzoLocaleDao;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.task.Processo;

public class ProcessoSelezioneIndirizzi extends Processo {
	
	private static final String title = "Filtraggio degli Indirizzi";
	
	private List<Indirizzo> indirizzi;
	private final IndirizzoLocaleDao dao;
	private final CriteriSelezioneIndirizzi criteri;

	public ProcessoSelezioneIndirizzi(IndirizzoLocaleDao dao, CriteriSelezioneIndirizzi criteri) {
		super(title, -1);
		this.dao = dao;
		this.criteri = criteri;
	}
	
	public List<Indirizzo> getIndirizzi() {
		return indirizzi;
	}

	@Override
	public void eseguiOperazioni() throws Exception {
		indirizzi = dao.trovaCorrispondenti(criteri);
	}

}
