package it.ltc.logica.common.controller.trasporti;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.locali.CriteriSelezioneCap;
import it.ltc.logica.common.controller.ControllerCRUDLocale;
import it.ltc.logica.common.controller.dao.CapLocaleDao;
import it.ltc.logica.common.controller.processi.sincronizzazione.CRUDDaoConProcessi;
import it.ltc.logica.common.controller.processi.sincronizzazione.ProcessoAggiornamentoDB;
import it.ltc.logica.common.controller.processi.specifici.ProcessoSelezioneCap;
import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerCap extends ControllerCRUDLocale<Cap> {

	private static final String title = "Cap";
	private static final String resource = "cap";

	private static ControllerCap instance;

	private ControllerCap() {
		super(title, resource, Cap[].class);
		caricaDati();
	}

	public static ControllerCap getInstance() {
		if (instance == null) {
			instance = new ControllerCap();
		}
		return instance;
	}

	public Collection<Cap> getListaCap() {
		CapLocaleDao dao = new CapLocaleDao();
		return dao.trovaPrimiN(1000);
	}
	
	public List<Cap> selezionaCap(CriteriSelezioneCap criteri) {
		List<Cap> lista;
		if (criteri != null) {
			CapLocaleDao dao = new CapLocaleDao();
			ProcessoSelezioneCap processo = new ProcessoSelezioneCap(dao, criteri);
			DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
			dialog.esegui(processo);
			lista = processo.getLista();
		} else {
			lista = new LinkedList<>();
		}		
		return lista;
	}
	
	public Collection<Cap> getTuttiCap() {
		CapLocaleDao dao = new CapLocaleDao();
		return dao.trovaTutte();
	}

	public Cap getInfoGeneraliCap(String cap) {
		CapLocaleDao dao = new CapLocaleDao();
		return dao.trovaDaSoloCap(cap);
	}
	
	public Cap getCapDaLocalita(String localita) {
		CapLocaleDao dao = new CapLocaleDao();
		return dao.trovaDaLocalita(localita);
	}

	public Cap getInfoCap(String cap, String localita) {
		CapLocaleDao dao = new CapLocaleDao();
		return dao.trovaDaID(cap, localita);
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Cap> lista) {
		CapLocaleDao dao = new CapLocaleDao();
		ProcessoAggiornamentoDB<Cap> p = new ProcessoAggiornamentoDB<Cap>(dao, lista, false);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(p);
		return p.getEsito();
	}

	@Override
	protected void aggiornaInfoInserimento(Cap object, Cap cap) {
		CapLocaleDao dao = new CapLocaleDao();
		dao.inserisci(cap);
	}

	@Override
	protected void aggiornaInfoElemento(Cap object) {
		// TODO - ricarica lo specifico cap dal ws?
	}

	@Override
	protected void aggiornaInfoEliminazione(Cap cap) {
		CapLocaleDao dao = new CapLocaleDao();
		dao.elimina(cap);
	}

	@Override
	protected CRUDDaoConProcessi<Cap> getDao() {
		CapLocaleDao dao = new CapLocaleDao();
		return dao;
	}

	@Override
	protected boolean aggiornaInfoLocale(Cap object) {
		CapLocaleDao dao = new CapLocaleDao();
		Cap entity = dao.aggiorna(object);
		return entity != null;
	}

}
