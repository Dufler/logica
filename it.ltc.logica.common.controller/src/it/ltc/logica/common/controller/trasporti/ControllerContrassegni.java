package it.ltc.logica.common.controller.trasporti;

import java.util.Collection;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUDLocale;
import it.ltc.logica.common.controller.dao.ContrassegnoLocaleDao;
import it.ltc.logica.common.controller.processi.sincronizzazione.CRUDDaoConProcessi;
import it.ltc.logica.common.controller.processi.sincronizzazione.ProcessoAggiornamentoDB;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerContrassegni extends ControllerCRUDLocale<Contrassegno> {

	private static final String title = "Contrassegni";
	private static final String resource = "contrassegno";

	private static ControllerContrassegni instance;

	private final ContrassegnoLocaleDao dao;

	private ControllerContrassegni() {
		super(title, resource, Contrassegno[].class);
		dao = new ContrassegnoLocaleDao();
		caricaDati();
	}
	
	@Override
	protected String getNomeTabella() {
		return "spedizione_contrassegno";
	}

	public static ControllerContrassegni getInstance() {
		if (instance == null) {
			instance = new ControllerContrassegni();
		}
		return instance;
	}

	public Collection<Contrassegno> getContrassegni() {
		return dao.trovaTutte();
	}

	public Contrassegno getContrassegno(int id) {
		return dao.trovaDaID(id);
	}

	@Override
	protected void aggiornaInfoInserimento(Contrassegno object, Contrassegno nuovo) {
		dao.inserisci(nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(Contrassegno object) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void aggiornaInfoEliminazione(Contrassegno object) {
		dao.elimina(object);
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Contrassegno> lista) {
		ProcessoAggiornamentoDB<Contrassegno> p = new ProcessoAggiornamentoDB<Contrassegno>(dao, lista, false);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(p);
		return p.getEsito();
	}

	@Override
	protected CRUDDaoConProcessi<Contrassegno> getDao() {
		return dao;
	}

	@Override
	protected boolean aggiornaInfoLocale(Contrassegno object) {
		Contrassegno entity = dao.aggiorna(object);
		return entity != null;
	}

}
