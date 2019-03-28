package it.ltc.logica.common.controller;

import it.ltc.logica.common.controller.processi.ProcessoAggiornamentoDati;
import it.ltc.logica.common.controller.processi.ProcessoCancellazioneDati;
import it.ltc.logica.common.controller.processi.ProcessoInserimentoDati;
import it.ltc.logica.gui.task.DialogProgresso;

public abstract class ControllerCRUD<T> extends ControllerReadOnly<T> {
	
	protected final boolean mapReturnValueOnInsert;
	protected final boolean mapReturnValueOnUpdate;
	protected final boolean mapReturnValueOnDelete;
	
	/**
	 * Costruttore di default.<br>
	 * Permette di specificare il titolo, la risorsa e la classe.
	 * @param title Il titolo che avrà la finestra durante l'attesa della risposta dal web service.
	 * @param resource L'URL della risorsa del web service.
	 * @param c La classe da mappare.
	 */
	public ControllerCRUD(String title, String resource, Class<T[]> c) {
		this(title, resource, c, true, true, true);
	}
	
	/**
	 * Costruttore specifico.<br>
	 * Permette di specificare il titolo, la risorsa, la classe e se andare a mappare le risposte.
	 * @param title Il titolo che avrà la finestra durante l'attesa della risposta dal web service.
	 * @param resource L'URL della risorsa del web service.
	 * @param c La classe da mappare.
	 * @param insert indica se bisogna mappare o meno la risposta di una chiamata di inserimento.
	 * @param update indica se bisogna mappare o meno la risposta di una chiamata di aggiornamento.
	 * @param delete indica se bisogna mappare o meno la risposta di una chiamata di cancellazione.
	 */
	public ControllerCRUD(String title, String resource, Class<T[]> c, boolean insert, boolean update, boolean delete) {
		super(title, resource, c);
		this.mapReturnValueOnInsert = insert;
		this.mapReturnValueOnUpdate = update;
		this.mapReturnValueOnDelete = delete;
	}
	
	public boolean inserisciSenzaProgressDialog(T object) {
		ProcessoInserimento processo = new ProcessoInserimento(object);
		processo.eseguiOperazioniSenzaProgresso();
		boolean esito = processo.getEsito();
		if (esito) {
			aggiornaInfoInserimento(object, processo.getObject());
		}
		return esito;
	}
	
	public boolean inserisci(T object) {
		ProcessoInserimento processo = new ProcessoInserimento(object);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (esito) {
			aggiornaInfoInserimento(object, processo.getObject());
		}
		return esito;
	}

	/**
	 * Metodo da implementare.<br>
	 * Aggiunge le info dell'inserimento nei dati gestiti dal controller.
	 * @param object l'oggetto che viene passato per l'inserimento.
	 * @param nuovo l'oggetto restituito dal web service.
	 */
	protected abstract void aggiornaInfoInserimento(T object, T nuovo);
	
	public boolean aggiornaSenzaProgressDialog(T object) {
		ProcessoAggiornamento processo = new ProcessoAggiornamento(object);
		processo.eseguiOperazioniSenzaProgresso();
		boolean esito = processo.getEsito();
		if (!esito) {
			aggiornaInfoElemento(object);
		}
		return esito;
	}
	
	public boolean aggiorna(T object) {
		ProcessoAggiornamento processo = new ProcessoAggiornamento(object);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (!esito) {
			aggiornaInfoElemento(object);
		}
		return esito;
	}
	
	/**
	 * Metodo da implementare.<br>
	 * Aggiorna le info del solo oggetto passato come argomento a partire dal web service.
	 * @param object
	 */
	protected abstract void aggiornaInfoElemento(T object);
	
	public boolean eliminaSenzaProgressDialog(T object) {
		ProcessoEliminazione processo = new ProcessoEliminazione(object);
		processo.eseguiOperazioniSenzaProgresso();
		boolean esito = processo.getEsito();
		if (esito) {
			aggiornaInfoEliminazione(object);
		}
		return esito;
	}
	
	public boolean elimina(T object) {
		ProcessoEliminazione processo = new ProcessoEliminazione(object);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (esito) {
			aggiornaInfoEliminazione(object);
		}
		return esito;
	}
	
	/**
	 * Metodo da implementare.<br>
	 * Rimuove le info dell'oggetto eliminato dai dati gestiti dal controller.
	 * @param object l'oggetto che viene passato per l'eliminazione.
	 */
	protected abstract void aggiornaInfoEliminazione(T object);
	
	protected class ProcessoInserimento extends ProcessoInserimentoDati<T> {

		public ProcessoInserimento(T object) {
			super(title, resource, object, mapReturnValueOnInsert, getDomain(), getContextPath(), getRisorsaCommessa());
		}
		
	}
	
	protected class ProcessoAggiornamento extends ProcessoAggiornamentoDati<T> {

		public ProcessoAggiornamento(T object) {
			super(title, resource, object, mapReturnValueOnUpdate, getDomain(), getContextPath(), getRisorsaCommessa());
		}
		
	}
	
	protected class ProcessoEliminazione extends ProcessoCancellazioneDati<T> {

		public ProcessoEliminazione(T object) {
			super(title, resource, object, mapReturnValueOnDelete, getDomain(), getContextPath(), getRisorsaCommessa());
		}
		
	}
	
	/**
	 * Restituisce il nome della tabella su cui si opera.<br>
	 * Viene ricavata dal nome della classe, da estendere qualora non andasse bene.
	 * @return la stringa che rappresenta il nome della tabella. 
	 */
	protected String getNomeTabella() {
		String nomeTabella = c.getSimpleName().toLowerCase();
		nomeTabella = nomeTabella.replaceAll("\\[\\]", "");
		return nomeTabella;
	}

	public boolean isMapReturnValueOnInsert() {
		return mapReturnValueOnInsert;
	}

	public boolean isMapReturnValueOnUpdate() {
		return mapReturnValueOnUpdate;
	}

	public boolean isMapReturnValueOnDelete() {
		return mapReturnValueOnDelete;
	}

}
