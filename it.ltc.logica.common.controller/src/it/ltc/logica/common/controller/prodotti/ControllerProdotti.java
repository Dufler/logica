package it.ltc.logica.common.controller.prodotti;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUDLocale;
import it.ltc.logica.common.controller.dao.ProdottoLocaleDao;
import it.ltc.logica.common.controller.processi.ProcessoCancellazioneDati;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.common.controller.processi.ProcessoInserimentoDati;
import it.ltc.logica.common.controller.processi.sincronizzazione.CriteriUltimaModifica;
import it.ltc.logica.common.controller.processi.sincronizzazione.ProcessoAggiornamentoDB;
import it.ltc.logica.common.controller.processi.sincronizzazione.ProcessoChiamataSincronizzazione;
import it.ltc.logica.common.controller.processi.sincronizzazione.ProcessoSalvataggioSincronizzazione;
import it.ltc.logica.common.controller.processi.specifici.ProcessoSelezioneModelli;
import it.ltc.logica.common.controller.processi.specifici.ProcessoSelezioneProdotti;
import it.ltc.logica.common.controller.processi.specifici.ProcessoSelezioneProdottiPerModello;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.common.ws.RestClient;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.prodotto.Modello;
import it.ltc.logica.database.model.prodotto.MovimentoProdotto;
import it.ltc.logica.database.model.prodotto.Prodotto;
import it.ltc.logica.database.model.prodotto.SaldoProdotto;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerProdotti extends ControllerCRUDLocale<Prodotto> {
	
	public static final String title = "Prodotti";
	public static final String resource = "prodotto";
	
	protected final Sede sede;
	protected final Commessa commessa;
	
	private final HashMap<Integer, Prodotto> prodotti;
	
	public ControllerProdotti(Commessa commessa) {
		super(title, resource, Prodotto[].class);
		this.commessa = commessa;
		this.sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		this.prodotti = new HashMap<>();
		caricaDati();
	}
	
	@Override
	public synchronized void caricaDati() {
		CriteriUltimaModifica criteri = new CriteriUltimaModifica();
		criteri.setDataUltimaModifica(getDataUltimaVersione());
		ProcessoChiamataSincronizzazione<Prodotto> processoChiamata = new ProcessoChiamataSincronizzazione<>(title, resource + updatedResources, c, criteri, sede, commessa);
		DialogProgresso dialogChiamata = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialogChiamata.esegui(processoChiamata);
		if (processoChiamata.getEsito()) {
			ProcessoSalvataggioSincronizzazione<Prodotto> processoSalvataggio = new ProcessoSalvataggioSincronizzazione<>(title, processoChiamata.getStatus(), processoChiamata.getEntities(), getDao());
			DialogProgresso dialogSalvataggio = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
			dialogSalvataggio.esegui(processoSalvataggio);
			if (processoSalvataggio.getEsito())
				setDataUltimaVersione(processoSalvataggio.getDataUltimoAggiornamento());
		}
	}
	
	/**
	 * Non vado a cercare la info dell'ultima versione sulla tabella versioni ma faccio una MAX sul campo date filtrando per commessa.
	 */
	protected Date getDataUltimaVersione() {
		ProdottoLocaleDao dao = getDao();
		Date dataUltimaVersione = dao.trovaDataUltimoAggiornamento();
		if (dataUltimaVersione == null) {
			dataUltimaVersione = new Date();
			dataUltimaVersione.setTime(0);
		}
		return dataUltimaVersione;
	}
	
	/**
	 * Non è necessario salvare la info dell'ultima versione sulla tabella versioni.
	 */
	protected boolean setDataUltimaVersione(Date ultimaVersione) {
		return true;
	}
	
	public Prodotto trovaDaID(int id) {
		if (!prodotti.containsKey(id)) {
			ProdottoLocaleDao dao = getDao();
			Prodotto prodotto = dao.trovaDaID(id, commessa.getId());
			prodotti.put(id, prodotto);
		}
		return prodotti.get(id);
	}
	
	public List<Prodotto> cerca(Prodotto filtro) {
		List<Prodotto> lista;
		if (filtro != null) {
			ProdottoLocaleDao dao = getDao();
			ProcessoSelezioneProdotti processo = new ProcessoSelezioneProdotti(dao, filtro);
			DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
			dialog.esegui(processo);
			lista = processo.getLista();
		} else {
			lista = new LinkedList<>();
		}		
		return lista;
	}
	
	public List<Prodotto> cercaDaModello(Prodotto filtro) {
		List<Prodotto> lista;
		if (filtro != null) {
			ProdottoLocaleDao dao = getDao();
			ProcessoSelezioneProdottiPerModello processo = new ProcessoSelezioneProdottiPerModello(dao, filtro);
			DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
			dialog.esegui(processo);
			lista = processo.getLista();
		} else {
			lista = new LinkedList<>();
		}		
		return lista;
	}
	
	public List<Modello> trovaModelli(Modello filtro) {
		List<Modello> lista;
		if (filtro != null) {
			ProdottoLocaleDao dao = getDao();
			ProcessoSelezioneModelli processo = new ProcessoSelezioneModelli(dao, filtro);
			DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
			dialog.esegui(processo);
			lista = processo.getLista();
		} else {
			lista = new LinkedList<>();
		}		
		return lista;
	}

	public Collection<ProdottiPerModello> raggruppaPerModello(List<Prodotto> prodotti) {
		HashMap<String, ProdottiPerModello> prodottiPerModello = new HashMap<>();
		for (Prodotto prodotto : prodotti) {
			if (!prodottiPerModello.containsKey(prodotto.getCodiceModello())) {
				ProdottiPerModello ppm = new ProdottiPerModello(prodotto);
				prodottiPerModello.put(prodotto.getCodiceModello(), ppm);
			}
			ProdottiPerModello ppm = prodottiPerModello.get(prodotto.getCodiceModello());
			ppm.addProdotto(prodotto, 0);
		}
		return prodottiPerModello.values();
	}

	@Override
	protected ProdottoLocaleDao getDao() {
		ProdottoLocaleDao dao = new ProdottoLocaleDao(commessa);
		return dao;
	}

	@Override
	protected boolean aggiornaInfoLocale(Prodotto object) {
		ProdottoLocaleDao dao = getDao();
		Prodotto entity = dao.aggiorna(object);
		return entity != null;
	}

	@Override
	protected void aggiornaInfoInserimento(Prodotto object, Prodotto nuovo) {
		object.setId(nuovo.getId());
		object.setCommessa(nuovo.getCommessa());
		ProdottoLocaleDao dao = getDao();
		dao.inserisci(nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(Prodotto object) {
		// DO NOTHING?
	}

	@Override
	protected void aggiornaInfoEliminazione(Prodotto object) {
		ProdottoLocaleDao dao = getDao();
		dao.elimina(object);
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Prodotto> lista) {
		ProdottoLocaleDao dao = getDao();
		ProcessoAggiornamentoDB<Prodotto> p = new ProcessoAggiornamentoDB<>(dao, lista, false);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(p);
		return p.getEsito();
	}
	
	@Override
	public String getRisorsaCommessa() {
		return commessa.getNomeRisorsa();
	}
	
	@Override
	public String getContextPath() {
		return RestClient.CONTEXT_PATH_SEDE;
	}
	
	public List<SaldoProdotto> trovaSaldi(Prodotto prodotto) {
		ProcessoRecuperoSaldo processo = new ProcessoRecuperoSaldo(prodotto.getId());
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		return processo.getLista();
	}
	
	public List<MovimentoProdotto> trovaMovimenti(Prodotto prodotto) {
		ProcessoRecuperoMovimenti processo = new ProcessoRecuperoMovimenti(prodotto.getId());
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		return processo.getLista();
	}
	
	public boolean inserisciMovimento(MovimentoProdotto movimento) {
		ProcessoInserimentoMovimento processo = new ProcessoInserimentoMovimento(movimento);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		return processo.getEsito();
	}
	
	public boolean eliminaMovimento(MovimentoProdotto movimento) {
		ProcessoEliminazioneMovimento processo = new ProcessoEliminazioneMovimento(movimento);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		return processo.getEsito();
	}
	
	protected class ProcessoRecuperoSaldo extends ProcessoCaricamentoDati<SaldoProdotto> {

		public ProcessoRecuperoSaldo(int idProdotto) {
			super("Saldi", "movimenti/saldo/" + idProdotto, SaldoProdotto[].class, sede, commessa);
		}
		
	}
	
	protected class ProcessoRecuperoMovimenti extends ProcessoCaricamentoDati<MovimentoProdotto> {

		public ProcessoRecuperoMovimenti(int idProdotto) {
			super("Movimenti", "movimenti/" + idProdotto, MovimentoProdotto[].class, sede, commessa);
		}
		
	}
	
	protected class ProcessoInserimentoMovimento extends ProcessoInserimentoDati<MovimentoProdotto> {

		public ProcessoInserimentoMovimento(MovimentoProdotto movimento) {
			super("Movimento", "movimenti", movimento, true, getDomain(), getContextPath(), getRisorsaCommessa());
		}
		
	}
	
	protected class ProcessoEliminazioneMovimento extends ProcessoCancellazioneDati<MovimentoProdotto> {

		public ProcessoEliminazioneMovimento(MovimentoProdotto movimento) {
			super("Movimento", "movimenti", movimento, true, getDomain(), getContextPath(), getRisorsaCommessa());
		}
		
	}

}
