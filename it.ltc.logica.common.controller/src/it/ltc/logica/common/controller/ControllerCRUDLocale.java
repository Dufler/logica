package it.ltc.logica.common.controller;

import java.util.Date;

import it.ltc.database.dao.locali.VersioneTabellaDao;
import it.ltc.logica.common.controller.processi.sincronizzazione.CRUDDaoConProcessi;
import it.ltc.logica.common.controller.processi.sincronizzazione.CriteriUltimaModifica;
import it.ltc.logica.common.controller.processi.sincronizzazione.ProcessoChiamataSincronizzazione;
import it.ltc.logica.common.controller.processi.sincronizzazione.ProcessoSalvataggioSincronizzazione;
import it.ltc.logica.database.model.locale.VersioneTabella;
import it.ltc.logica.gui.task.DialogProgresso;

/**
 * Questo tipo di controller salva i dati in locale e li recupera da li.<br>
 * Al caricamento dei dati, in base alla versione di cui si disponeva in locale e quella lato server, si decide se:
 * <ul>
 * <li>eseguire uno wipe dei dati locali e reinserirli tutti.</li>
 * <li>sincronizzare i dati locali inserendo quelli mancanti e aggiornare quelli presenti.</li>
 * <li>non fare nulla.</li>
 * </ul>
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 * @param <T>
 */
public abstract class ControllerCRUDLocale<T> extends ControllerCRUD<T> {
	
	protected static final String updatedResources = "/ultimamodifica";
	
	protected final VersioneTabellaDao daoVersione;

	public ControllerCRUDLocale(String title, String resource, Class<T[]> c) {
		super(title, resource, c);
		this.daoVersione = VersioneTabellaDao.getInstance();
	}
	
	public ControllerCRUDLocale(String title, String resource, Class<T[]> c, boolean insert, boolean update, boolean delete) {
		super(title, resource, c, insert, update, delete);
		this.daoVersione = VersioneTabellaDao.getInstance();
	}
	
	protected Date getDataUltimaVersione() {
		VersioneTabella versione = daoVersione.getVersione(getNomeTabella());
		Date dataUltimaVersione = versione != null ? versione.getDataVersione() : new Date();
		return dataUltimaVersione;
	}
	
	protected boolean setDataUltimaVersione(Date ultimaVersione) {
		boolean update = daoVersione.setVersione(getNomeTabella(), ultimaVersione);
		return update;
	}
	
	@Override
	public synchronized void caricaDatiSenzaProgressDialog() {
		CriteriUltimaModifica criteri = new CriteriUltimaModifica();
		criteri.setDataUltimaModifica(getDataUltimaVersione());
		ProcessoChiamataSincronizzazione<T> processoChiamata = new ProcessoChiamataSincronizzazione<T>(title, resource + updatedResources, c, criteri);
		processoChiamata.eseguiOperazioniSenzaProgresso();
		if (processoChiamata.getEsito()) {
			ProcessoSalvataggioSincronizzazione<T> processoSalvataggio = new ProcessoSalvataggioSincronizzazione<>(title, processoChiamata.getStatus(), processoChiamata.getEntities(), getDao());
			DialogProgresso dialogSalvataggio = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
			dialogSalvataggio.esegui(processoSalvataggio);
			if (processoSalvataggio.getEsito())
				setDataUltimaVersione(processoSalvataggio.getDataUltimoAggiornamento());
		}
	}
	
	@Override
	public synchronized void caricaDati() {
		CriteriUltimaModifica criteri = new CriteriUltimaModifica();
		criteri.setDataUltimaModifica(getDataUltimaVersione());
		ProcessoChiamataSincronizzazione<T> processoChiamata = new ProcessoChiamataSincronizzazione<T>(title, resource + updatedResources, c, criteri);
		DialogProgresso dialogChiamata = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialogChiamata.esegui(processoChiamata);
		if (processoChiamata.getEsito()) {
			ProcessoSalvataggioSincronizzazione<T> processoSalvataggio = new ProcessoSalvataggioSincronizzazione<>(title, processoChiamata.getStatus(), processoChiamata.getEntities(), getDao());
			DialogProgresso dialogSalvataggio = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
			dialogSalvataggio.esegui(processoSalvataggio);
			//Se la sincronizzazione è andata a buon fine ed ho effettivamente salvato qualcosa allora aggiorno la data della versione della tabella.
			if (processoSalvataggio.getEsito() && processoSalvataggio.getStatus() != 204) 
				setDataUltimaVersione(processoSalvataggio.getDataUltimoAggiornamento());
		}
	}
	
	/**
	 * Metodo da implementare per ottenere l'oggetto per l'accesso ai dati.
	 * @return il Dao per poter accedere ai dati locali.
	 */
	protected abstract CRUDDaoConProcessi<T> getDao();
	
	protected abstract boolean aggiornaInfoLocale(T object);
	
	@Override
	public boolean aggiorna(T object) {
		ProcessoAggiornamento processo = new ProcessoAggiornamento(object);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (!esito) {
			aggiornaInfoElemento(object);
		} else {
			aggiornaInfoLocale(processo.isMapReturnValue() ? processo.getObject() : object);
		}
		return esito;
	}
	
	@Override
	public boolean aggiornaSenzaProgressDialog(T object) {
		ProcessoAggiornamento processo = new ProcessoAggiornamento(object);
		processo.eseguiOperazioniSenzaProgresso();
		boolean esito = processo.getEsito();
		if (!esito) {
			aggiornaInfoElemento(object);
		} else {
			aggiornaInfoLocale(processo.isMapReturnValue() ? processo.getObject() : object);
		}
		return esito;
	}
	
}
