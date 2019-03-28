package it.ltc.logica.common.controller.ingressi;

import java.util.Date;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCommessa;
import it.ltc.logica.common.controller.processi.ProcessoAggiornamentoDati;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.common.controller.processi.ProcessoRicercaDati;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.ingressi.StatiCarico;
import it.ltc.logica.database.model.centrale.ingressi.CaricoStato;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerCarichi extends ControllerCommessa<CaricoTestata> {
	
	public static final String title = "Carichi";
	public static final String resource = "carico";
	
	public ControllerCarichi(Commessa commessa) {
		super(title, resource, CaricoTestata[].class, commessa);
	}
	
	public List<CaricoTestata> trovaCarichi(String riferimento, StatiCarico stato, int fornitore, String tipo, Date da, Date a) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		CaricoTestata filtro = new CaricoTestata();
		filtro.setRiferimento(riferimento);
		filtro.setStato(stato);
		filtro.setTipo(tipo);
		filtro.setFornitore(fornitore);
		filtro.setDa(da);
		filtro.setA(a);
		ProcessoTrovaCarichi processo = new ProcessoTrovaCarichi(sede, commessa, filtro);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<CaricoTestata> carichi = processo.getLista();
		return carichi;
	}
	
	protected class ProcessoTrovaCarichi extends ProcessoRicercaDati<CaricoTestata> {

		public ProcessoTrovaCarichi(Sede sede,	Commessa commessa, CaricoTestata filtro) {
			super(title, resource + "/cerca", c, sede, commessa, filtro);
		}
		
	}

	public boolean modificaStato(CaricoTestata carico, StatiCarico stato) {
		//Tengo in memoria lo stato attuale nel caso in cui l'aggiornamento fallisca.
		StatiCarico statoAttuale = carico.getStato();
		carico.setStato(stato);
		ProcessoAggiornamentoDati<CaricoTestata> processo = new ProcessoAggiornamentoDati<CaricoTestata>(title, resource + "/modificastato", carico, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		//Se la richiesta non è andata a buon reimposto lo stato
		if (!esito) {
			carico.setStato(statoAttuale);
		}
		return esito;
	}
	
	public List<CaricoStato> trovaStati(int idCarico) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		CaricoTestata filtro = new CaricoTestata();
		filtro.setId(idCarico);
		ProcessoTrovaStati processo = new ProcessoTrovaStati(sede, commessa, filtro);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<CaricoStato> stati = processo.getLista();
		return stati;
	}
	
	protected class ProcessoTrovaStati extends ProcessoCaricamentoDati<CaricoStato> {

		public ProcessoTrovaStati(Sede sede, Commessa commessa, CaricoTestata filtro) {
			super(title, resource + "/stati/" + filtro.getId(), CaricoStato[].class, sede, commessa);
		}
		
	}

}
