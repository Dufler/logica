package it.ltc.logica.common.controller.processi.sincronizzazione;

import java.util.Date;
import java.util.List;

import it.ltc.logica.gui.task.Processo;

public class ProcessoSalvataggioSincronizzazione<T> extends Processo {
	
	private final CRUDDaoConProcessi<T> dao;
	private final List<T> entities;
	private final int status;
	
	private boolean esito;
	private Date dataUltimoAggiornamento;

	public ProcessoSalvataggioSincronizzazione(String title, int status, List<T> entities, CRUDDaoConProcessi<T> dao) {
		super("Aggiornamento locale: " + title, entities.size());
		this.status = status;
		this.entities = entities;
		this.dao = dao;
	}
	
	public boolean getEsito() {
		return esito;
	}
	
	public int getStatus() {
		return status;
	}
	
	public Date getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	@Override
	public void eseguiOperazioni() throws Exception {
		switch (status) {
			case 200 : esito = aggiornamentoDati(); break;
			case 204 : esito = true; break;
			case 203 : esito = resetEAggiornamentoDati(); break;
		}
		if (!esito) {
			throw new RuntimeException("Errore durante la sincronizzazione dei dati!");
		}
	}
	
	private boolean aggiornamentoDati() {
		boolean esito = dao.sincronizza(entities, this);
		System.out.println("Aggiornamento dati: " + esito);
		dataUltimoAggiornamento = dao.trovaDataUltimoAggiornamento();
		return esito;
	}
	
	private boolean resetEAggiornamentoDati() {
		boolean esito = dao.truncate();
		System.out.println("Cancellazione vecchi dati: " + esito);
		if (esito) {
			esito = dao.sincronizza(entities, this);
			System.out.println("Aggiornamento dati: " + esito);
			dataUltimoAggiornamento = dao.trovaDataUltimoAggiornamento();
		}
		return esito;
	}

}
