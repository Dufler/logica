package it.ltc.logica.cdg.gui.eventi.elements.abbinamentoeventi;

import it.ltc.logica.common.controller.cdg.ControllerCdgEventi;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.cdg.CdgCommessaEvento;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCommessaEventi extends Ordinatore<CdgCommessaEvento> {
	
	private final ControllerCommesse controllerCommesse;
	private final ControllerCdgEventi controllerEventi;
	
	protected OrdinatoreCommessaEventi() {
		controllerCommesse = ControllerCommesse.getInstance();
		controllerEventi = ControllerCdgEventi.getInstance();
	}

	@Override
	protected int compare(CdgCommessaEvento t1, CdgCommessaEvento t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = ordinaPerCommessa(t1, t2); break;
			case 1 : compare = ordinaPerEvento(t1, t2); break;
			case 2 : compare = compareInteger(t1.getDurata(), t2.getDurata()); break;
			default : compare = 0;
		}
		return compare;
	}

	private int ordinaPerCommessa(CdgCommessaEvento t1, CdgCommessaEvento t2) {
		Commessa c1 = controllerCommesse.getCommessa(t1.getCommessa());
		Commessa c2 = controllerCommesse.getCommessa(t2.getCommessa());
		String s1 = c1 != null ? c1.getNome() : "";
		String s2 = c2 != null ? c2.getNome() : "";
		int compare = compareString(s1, s2);
		return compare;
	}

	private int ordinaPerEvento(CdgCommessaEvento t1, CdgCommessaEvento t2) {
		CdgEvento e1 = controllerEventi.getEvento(t1.getEvento());
		CdgEvento e2 = controllerEventi.getEvento(t2.getEvento());
		String s1 = e1 != null ? e1.getNome() : "";
		String s2 = e2 != null ? e2.getNome() : "";
		int compare = compareString(s1, s2);
		return compare;
	}

}
