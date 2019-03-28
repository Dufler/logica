package it.ltc.logica.common.controller.trasporti;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUDLocale;
import it.ltc.logica.common.controller.dao.GiacenzaLocaleDao;
import it.ltc.logica.common.controller.processi.sincronizzazione.CRUDDaoConProcessi;
import it.ltc.logica.common.controller.processi.sincronizzazione.ProcessoAggiornamentoDB;
import it.ltc.logica.database.model.centrale.trasporti.Giacenza;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerGiacenze extends ControllerCRUDLocale<Giacenza> {
	
	private static final String title = "Giacenze";
	private static final String resource = "giacenza";
	
	private static ControllerGiacenze instance;
	
	private final GiacenzaLocaleDao dao;

	private ControllerGiacenze() {
		super(title, resource, Giacenza[].class);
		dao = new GiacenzaLocaleDao();
		caricaDati();
	}

	public static ControllerGiacenze getInstance() {
		if (instance == null) {
			instance = new ControllerGiacenze();
		}
		return instance;
	}
	
	@Override
	protected String getNomeTabella() {
		return "spedizione_giacenza";
	}
	
	public Collection<Giacenza> getGiacenze() {
		return dao.trovaTutte();
	}
	
	public Giacenza getPrimaGiacenza(Spedizione spedizione) {
		Giacenza primaGiacenza = null;
		if (spedizione.getGiacenza()) {
			for (Giacenza giacenza : getGiacenze()) {
				if (giacenza.getIdSpedizione() == spedizione.getId()) {
					primaGiacenza = giacenza;
					break;
				}
			}
		}
		return primaGiacenza;
	}
	
	public List<Giacenza> getGiacenzePerSpedizione(Spedizione spedizione) {
		List<Giacenza> giacenze = new LinkedList<>();
		if (spedizione.getGiacenza()) {
			for (Giacenza giacenza : getGiacenze()) {
				if (giacenza.getIdSpedizione() == spedizione.getId()) {
					giacenze.add(giacenza);
				}
			}
		}
		return giacenze;
	}
	
	public Giacenza getGiacenza(int id) {
		return dao.trovaDaID(id);
	}
	

	@Override
	protected void aggiornaInfoInserimento(Giacenza object, Giacenza nuovo) {
		object.setId(nuovo.getId());
		dao.inserisci(nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(Giacenza object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void aggiornaInfoEliminazione(Giacenza object) {
		dao.elimina(object);
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Giacenza> lista) {
		ProcessoAggiornamentoDB<Giacenza> p = new ProcessoAggiornamentoDB<Giacenza>(dao, lista, false);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(p);
		return p.getEsito();
	}

	@Override
	protected CRUDDaoConProcessi<Giacenza> getDao() {
		return dao;
	}

	@Override
	protected boolean aggiornaInfoLocale(Giacenza object) {
		Giacenza entity = dao.aggiorna(object);
		return entity != null;
	}

}
