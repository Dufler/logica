package it.ltc.logica.common.controller;

import java.util.List;

import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.gui.task.DialogProgresso;

public abstract class ControllerReadOnly<T> {
	
	protected final String title;
	protected final String resource;
	protected final Class<T[]> c;
	
	protected String domain;
	protected String contextPath;
	protected String risorsaCommessa;
	
	/**
	 * Costruttore di default.<br>
	 * Permette di specificare il titolo, la risorsa e la classe.
	 * @param title Il titolo che avrà la finestra durante l'attesa della risposta dal web service.
	 * @param resource L'URL della risorsa del web service.
	 * @param c La classe da mappare.
	 */
	public ControllerReadOnly(String title, String resource, Class<T[]> c) {
		this.title = title;
		this.resource = resource;
		this.c = c;
	}
	
	public synchronized void caricaDatiSenzaProgressDialog() {
		ProcessoCaricamentoDati<T> processo = new ProcessoCaricamentoDati<T>(title, resource, c, getDomain(), getContextPath(), getRisorsaCommessa());
		processo.eseguiOperazioniSenzaProgresso();
		if (processo.getEsito()) {
			aggiornaInfoTuttiDati(processo.getLista());
		}
	}
	
	public synchronized void caricaDati() {
		ProcessoCaricamentoDati<T> processo = new ProcessoCaricamentoDati<T>(title, resource, c, getDomain(), getContextPath(), getRisorsaCommessa());
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		if (processo.getEsito()) {
			aggiornaInfoTuttiDati(processo.getLista());
		}
	}
	
	/**
	 * Metodo da implementare.<br>
	 * Aggiunge tutte le info recuperate dal web service nei dati gestiti dal controller.
	 * @param lista la lista degli oggetti recuperati.
	 */
	protected abstract boolean aggiornaInfoTuttiDati(List<T> lista);

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getRisorsaCommessa() {
		return risorsaCommessa;
	}

	public void setRisorsaCommessa(String risorsaCommessa) {
		this.risorsaCommessa = risorsaCommessa;
	}

}
