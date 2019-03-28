package it.ltc.logica.common.controller.trasporti;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.locali.CriteriSelezioneSpedizioni;
import it.ltc.logica.common.controller.ControllerCRUDLocale;
import it.ltc.logica.common.controller.dao.SpedizioneLocaleDao;
import it.ltc.logica.common.controller.processi.sincronizzazione.CRUDDaoConProcessi;
import it.ltc.logica.common.controller.processi.sincronizzazione.ProcessoAggiornamentoDB;
import it.ltc.logica.common.controller.processi.specifici.ProcessoSelezioneSpedizioni;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerSpedizioni extends ControllerCRUDLocale<Spedizione> {

	private final static String title = "Spedizioni";
	private final static String resource = "spedizione";

	private static ControllerSpedizioni instance;

//	private final HashMap<String, ServizioSpedizione> servizi;

	private ControllerSpedizioni() {
		super(title, resource, Spedizione[].class);
//		servizi = new HashMap<>();
//		caricaDatiServizi();
		caricaDati();
	}

	public static ControllerSpedizioni getInstance() {
		if (instance == null) {
			instance = new ControllerSpedizioni();
		}
		return instance;
	}

	public Collection<Spedizione> getSpedizioniRecenti() {
		SpedizioneLocaleDao dao = new SpedizioneLocaleDao();
		return dao.trovaTutteTopN(2000);
	}

	public Spedizione getSpedizione(Integer id) {
		SpedizioneLocaleDao dao = new SpedizioneLocaleDao();
		return dao.trovaDaID(id);
	}

	@Override
	protected void aggiornaInfoInserimento(Spedizione object, Spedizione nuovo) {
		object.setId(nuovo.getId());
		SpedizioneLocaleDao dao = new SpedizioneLocaleDao();
		dao.inserisci(nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(Spedizione object) {
		// TODO - Refresh
	}

	@Override
	protected void aggiornaInfoEliminazione(Spedizione object) {
		SpedizioneLocaleDao dao = new SpedizioneLocaleDao();
		dao.elimina(object);
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Spedizione> lista) {
		SpedizioneLocaleDao dao = new SpedizioneLocaleDao();
		ProcessoAggiornamentoDB<Spedizione> p = new ProcessoAggiornamentoDB<Spedizione>(dao, lista, false);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(p);
		return p.getEsito();
	}
	
	public List<Spedizione> selezionaSpedizioniSenzaProgressDialog(CriteriSelezioneSpedizioni criteri) {
		List<Spedizione> spedizioni;
		if (criteri != null) {
			SpedizioneLocaleDao dao = new SpedizioneLocaleDao();
			ProcessoSelezioneSpedizioni processo = new ProcessoSelezioneSpedizioni(dao, criteri);
			processo.eseguiOperazioniSenzaProgresso();
			spedizioni = processo.getLista();
		} else {
			spedizioni = new LinkedList<>();
		}		
		return spedizioni;
	}

	public List<Spedizione> selezionaSpedizioni(CriteriSelezioneSpedizioni criteri) {
		List<Spedizione> spedizioni;
		if (criteri != null) {
			SpedizioneLocaleDao dao = new SpedizioneLocaleDao();
			ProcessoSelezioneSpedizioni processo = new ProcessoSelezioneSpedizioni(dao, criteri);
			DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
			dialog.esegui(processo);
			spedizioni = processo.getLista();
		} else {
			spedizioni = new LinkedList<>();
		}		
		return spedizioni;
	}

	@Override
	protected CRUDDaoConProcessi<Spedizione> getDao() {
		SpedizioneLocaleDao dao = new SpedizioneLocaleDao();
		return dao;
	}

	@Override
	protected boolean aggiornaInfoLocale(Spedizione object) {
		SpedizioneLocaleDao dao = new SpedizioneLocaleDao();
		Spedizione entity = dao.aggiorna(object);
		return entity != null;
	}

}
