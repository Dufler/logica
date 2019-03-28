package it.ltc.logica.common.controller.trasporti;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.locali.CriteriSelezioneIndirizzi;
import it.ltc.logica.common.controller.ControllerCRUDLocale;
import it.ltc.logica.common.controller.dao.IndirizzoLocaleDao;
import it.ltc.logica.common.controller.processi.sincronizzazione.CRUDDaoConProcessi;
import it.ltc.logica.common.controller.processi.sincronizzazione.ProcessoAggiornamentoDB;
import it.ltc.logica.common.controller.processi.specifici.ProcessoSelezioneIndirizzi;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.indirizzi.Nazione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.TipoSpedizione;
import it.ltc.logica.gui.task.DialogProgresso;

/**
 * Controller adibito alla gestione degli indirizzi.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class ControllerIndirizzi extends ControllerCRUDLocale<Indirizzo> {
	
	private final static String title = "Indirizzi";
	private final static String resource = "indirizzo";
	
	private static ControllerIndirizzi instance;
	
	private final HashMap<Integer, Indirizzo> indirizzi;
//	private final HashMap<String, Provincia> province;
//	private final HashMap<String, Nazione> nazioni;
//	private final HashMap<String, Regione> regioni;

	private ControllerIndirizzi() {
		super(title, resource, Indirizzo[].class, true, false, false);
		indirizzi = new HashMap<Integer, Indirizzo>();
//		province = new HashMap<String, Provincia>();
//		nazioni = new HashMap<String, Nazione>();
//		regioni = new HashMap<String, Regione>();
		//caricaDatiGenerali();
		caricaDati();
	}

	public static ControllerIndirizzi getInstance() {
		if (instance == null) {
			instance = new ControllerIndirizzi();
		}
		return instance;
	}
	
//	public Provincia getProvincia(String sigla) {
//		return province.get(sigla);
//	}
//	
//	public Collection<Provincia> getProvince() {
//		return province.values();
//	}
	
//	public Regione getRegione(String sigla) {
//		return regioni.get(sigla);
//	}
//	
//	public Collection<Regione> getRegioni() {
//		return regioni.values();
//	}
	
//	public Nazione getNazione(String codice) {
//		return nazioni.get(codice);
//	}
//	
//	public Collection<Nazione> getNazioni() {
//		return nazioni.values();
//	}
	
	public Indirizzo getIndirizzo(int id) {
		if (!indirizzi.containsKey(id)) {
			IndirizzoLocaleDao dao = new IndirizzoLocaleDao();
			Indirizzo indirizzo = dao.trovaDaID(id);
			indirizzi.put(id, indirizzo);
		}
		return indirizzi.get(id);
	}
	
	public Collection<Indirizzo> getAlcuniIndirizzi() {
		IndirizzoLocaleDao dao = new IndirizzoLocaleDao();
		return dao.trovaTopN(1000);
	}
	
	public List<Indirizzo> selezionaIndirizzi(CriteriSelezioneIndirizzi criteri) {
		List<Indirizzo> indirizziSelezionati;
		if (criteri != null) {
			IndirizzoLocaleDao dao = new IndirizzoLocaleDao();
			ProcessoSelezioneIndirizzi processo = new ProcessoSelezioneIndirizzi(dao, criteri);
			DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
			dialog.esegui(processo);
			indirizziSelezionati = processo.getIndirizzi();
		} else {
			indirizziSelezionati = new LinkedList<>();
		}		
		return indirizziSelezionati;
	}
	
	/**
	 * Restituisce il tipo di spedizione in base al codice della nazione di destinazione.
	 * @param codiceNazione
	 * @return
	 */
	public TipoSpedizione getTipoSpedizioneDaDestinazione(String codiceNazione) {
		TipoSpedizione tipo;
		Nazione nazione = ControllerNazioni.getInstance().getNazione(codiceNazione); //nazioni.get(codiceNazione);
		if (nazione != null) {
			if ("ITA".equals(nazione.getCodiceIsoTre())) {
				tipo = TipoSpedizione.ITALIA;
			} else if (nazione.getUe()) {
				tipo = TipoSpedizione.UE;
			} else {
				tipo = TipoSpedizione.EXTRA_UE;
			}
		} else {
			tipo = TipoSpedizione.ITALIA;
		}
		return tipo;
	}
	
//	/**
//	 * Forza il sistema a ricaricare i dati sugli indirizzi.
//	 */
//	public synchronized void caricaDatiGenerali() {
//		ProcessoCaricamentoDatiGenerali pci = new ProcessoCaricamentoDatiGenerali();
//		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
//		dialog.esegui(pci);
//	}
//	
//	private class ProcessoCaricamentoDatiGenerali extends Processo {
//
//		private static final String title = "Caricamento Province, Regioni e Nazioni";
//		
//		public ProcessoCaricamentoDatiGenerali() {
//			super(title, -1);
//		}
//		
//		@Override
//		public void eseguiOperazioni() throws Exception {
//			RestClient client = new RestClient();
//			//Province
//			Provincia[] arrayProvince = client.get("provincia", Provincia[].class);
//			if (client.getStatus() == 200) {
//				province.clear();
//				for (Provincia provincia : arrayProvince) {
//					province.put(provincia.getSigla(), provincia);
//				}
//			}
//			//Regioni
//			Regione[] arrayRegioni = client.get("regione", Regione[].class);
//			if (client.getStatus() == 200) {
//				regioni.clear();
//				for (Regione regione : arrayRegioni) {
//					regioni.put(regione.getCodice(), regione);
//				}
//			}
//			//Nazioni
//			Nazione[] arrayNazioni = client.get("nazione", Nazione[].class);
//			if (client.getStatus() == 200) {
//				nazioni.clear();
//				for (Nazione nazione : arrayNazioni) {
//					nazioni.put(nazione.getCodiceIsoTre(), nazione);
//				}
//			}
//		}
//		
//	}

	@Override
	protected void aggiornaInfoInserimento(Indirizzo object, Indirizzo nuovo) {
		object.setId(nuovo.getId());
		IndirizzoLocaleDao dao = new IndirizzoLocaleDao();
		dao.inserisci(nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(Indirizzo object) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void aggiornaInfoEliminazione(Indirizzo object) {
		IndirizzoLocaleDao dao = new IndirizzoLocaleDao();
		dao.elimina(object);
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Indirizzo> lista) {
		IndirizzoLocaleDao dao = new IndirizzoLocaleDao();
		ProcessoAggiornamentoDB<Indirizzo> p = new ProcessoAggiornamentoDB<Indirizzo>(dao, lista, false);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(p);
		return p.getEsito();
	}

	@Override
	protected CRUDDaoConProcessi<Indirizzo> getDao() {
		IndirizzoLocaleDao dao = new IndirizzoLocaleDao();
		return dao;
	}

	@Override
	protected boolean aggiornaInfoLocale(Indirizzo object) {
		IndirizzoLocaleDao dao = new IndirizzoLocaleDao();
		Indirizzo entity = dao.aggiorna(object);
		return entity != null;
	}

}
