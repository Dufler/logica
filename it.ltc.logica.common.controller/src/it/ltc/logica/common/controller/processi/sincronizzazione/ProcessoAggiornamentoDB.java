package it.ltc.logica.common.controller.processi.sincronizzazione;

import java.util.List;

import it.ltc.logica.gui.task.Processo;

public class ProcessoAggiornamentoDB<T> extends Processo {

	private final boolean reset;
	private final List<T> lista;
	private final CRUDDaoConProcessi<T> dao;
	
	private boolean esito;

	public ProcessoAggiornamentoDB(CRUDDaoConProcessi<T> dao, List<T> lista, boolean reset) {
		super("Aggiornamento Locale", lista.size());
		this.reset = reset;
		this.lista = lista;
		this.dao = dao;
	}
	
	public boolean getEsito() {
		return esito;
	}

	@Override
	public void eseguiOperazioni() throws Exception {
		boolean cancellazioneVecchiDati = reset ? dao.truncate() : true;
		System.out.println("Cancellazione vecchi dati: " + cancellazioneVecchiDati);
		boolean inserimento = dao.sincronizza(lista, this);
		System.out.println("Aggiornamento dati: " + inserimento);
		esito = cancellazioneVecchiDati && inserimento;
	}

}
