package it.ltc.logica.common.controller.cdg;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.common.ws.RestClient;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.cdg.CdgEventoRiepilogo;
import it.ltc.logica.gui.task.DialogProgresso;
import it.ltc.logica.gui.task.Processo;

public class ControllerCdgRiepilogoEventi {
	
	private static final String title = "Controllo di Gestione: recupero dati dalla sede.";
	private static final String resource = "cdg/eventoriepilogo";
	
	private static ControllerCdgRiepilogoEventi instance;

	private ControllerCdgRiepilogoEventi() {}

	public static ControllerCdgRiepilogoEventi getInstance() {
		if (instance == null) {
			instance = new ControllerCdgRiepilogoEventi();
		}
		return instance;
	}
	
	public List<CdgEventoRiepilogo> getRiepilogo(FiltroRiepilogo filtro) {
		//Recupero Commessa e Sede da cui prendere le info
		Commessa commessa = ControllerCommesse.getInstance().getCommessa(filtro.getCommessa());
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		//Istanzio la chiamata per recuperare i dati
		ProcessoOttieniRiepilogo processo = new ProcessoOttieniRiepilogo(sede.getIndirizzoWeb(), filtro);
		//Ottengo i dati richiesti
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<CdgEventoRiepilogo> lista = processo.getLista();
		return lista;
	}
	
	private class ProcessoOttieniRiepilogo extends Processo {
		
		private final FiltroRiepilogo filtro;
		private final List<CdgEventoRiepilogo> lista;

		public ProcessoOttieniRiepilogo(String address, FiltroRiepilogo filtro) {
			super(title, -1);
			this.filtro = filtro;
			this.lista = new LinkedList<>();
		}
		
		public List<CdgEventoRiepilogo> getLista() {
			return lista;
		}
		
		@Override
		public void eseguiOperazioni() throws Exception {
			RestClient client = new RestClient(RestClient.CONTEXT_PATH_SEDE, null);
			CdgEventoRiepilogo[] array = client.post(resource + "/cerca", filtro, CdgEventoRiepilogo[].class);
			if (client.getStatus() == 200) {
				lista.addAll(Arrays.asList(array));
			} else {
				throw new RuntimeException("Errore durante lo scaricamento: '" + client.getError() + "'");
			}
		}
		
	}

}
