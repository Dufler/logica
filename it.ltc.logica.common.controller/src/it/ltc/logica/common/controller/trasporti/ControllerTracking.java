package it.ltc.logica.common.controller.trasporti;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.ws.RestClient;
import it.ltc.logica.database.model.centrale.Tracking;
import it.ltc.logica.gui.task.DialogProgresso;
import it.ltc.logica.gui.task.Processo;

public class ControllerTracking {
	
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm";
	
	private static ControllerTracking instance;
	
	private final HashMap<Integer, List<Tracking>> tracciamenti;

	private ControllerTracking() {
		tracciamenti = new HashMap<Integer, List<Tracking>>();
	}

	public static ControllerTracking getInstance() {
		if (instance == null) {
			instance = new ControllerTracking();
		}
		return instance;
	}
	
	public List<Tracking> getTracking(int idSpedizione) {
		return getTracking(idSpedizione, false);
	}
	
	public List<Tracking> getTracking(int idSpedizione, boolean forceUpdate) {
		if (forceUpdate || !tracciamenti.containsKey(idSpedizione)) {
			ProcessoCaricamentoDati pcd = new ProcessoCaricamentoDati(idSpedizione);
			DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
			dialog.esegui(pcd);
		}
		return tracciamenti.get(idSpedizione);
	}
	
	private class ProcessoCaricamentoDati extends Processo {

		private static final String title = "Caricamento del tracking";
		
		private final int id;

		public ProcessoCaricamentoDati(int id) {
			super(title, -1);
			this.id = id;
		}
		
		@Override
		public void eseguiOperazioni() throws Exception {
			RestClient client = new RestClient(null, null, DEFAULT_DATE_FORMAT, null);
			Tracking[] array = client.get("tracking/eventi/" + id, Tracking[].class);
			if (client.getStatus() == 200 || client.getStatus() == 204) {
				List<Tracking> lista = array != null ? Arrays.asList(array) : new LinkedList<Tracking>();
				tracciamenti.put(id, lista);
			} else {
				throw new RuntimeException("Errore durante il recupero del tracking: '" + client.getError() + "'");
			}			
		}
	}

}
