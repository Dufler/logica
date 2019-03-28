package it.ltc.logica.common.controller.processi.sincronizzazione;

import java.util.Date;
import java.util.List;

import it.ltc.logica.gui.task.Processo;

public interface CRUDDaoConProcessi<T> {
	
	public boolean sincronizza(List<T> lista, Processo processo);
	
	public boolean truncate();
	
	public Date trovaDataUltimoAggiornamento();
	
}
