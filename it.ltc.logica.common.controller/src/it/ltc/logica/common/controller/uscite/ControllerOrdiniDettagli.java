package it.ltc.logica.common.controller.uscite;

import java.util.List;

import it.ltc.logica.common.controller.ControllerCommessa;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.common.controller.processi.ProcessoRicercaDati;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.ingressi.ColloCaricoJSON;
import it.ltc.logica.database.model.centrale.ingressi.ProdottoCaricoJSON;
import it.ltc.logica.database.model.centrale.ordini.OrdineDettaglio;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerOrdiniDettagli extends ControllerCommessa<OrdineDettaglio> {
	
	public static final String title = "Dettagli ordine";
	public static final String resource = "ordine/dettaglio";

	public ControllerOrdiniDettagli(Commessa commessa) {
		super(title, resource, OrdineDettaglio[].class, commessa);
	}
	
	public List<OrdineDettaglio> trovaDettagli(int carico, Commessa commessa) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoTrovaDettagli processo = new ProcessoTrovaDettagli(carico, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<OrdineDettaglio> dettagli = processo.getLista();
		return dettagli;
	}
	
	private class ProcessoTrovaDettagli extends ProcessoCaricamentoDati<OrdineDettaglio> {

		public ProcessoTrovaDettagli(int ordine, Sede sede, Commessa commessa) {
			super(title, resource + "/" + ordine, OrdineDettaglio[].class, sede, commessa);
		}
		
	}
	
	public List<ColloCaricoJSON> trovaColli(int carico, Commessa commessa) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ColloCaricoJSON filtro = new ColloCaricoJSON();
		filtro.setCarico(carico);
		ProcessoTrovaColli processo = new ProcessoTrovaColli(filtro, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<ColloCaricoJSON> dettagli = processo.getLista();
		return dettagli;
	}
	
	private class ProcessoTrovaColli extends ProcessoRicercaDati<ColloCaricoJSON> {

		public ProcessoTrovaColli(ColloCaricoJSON filtro, Sede sede, Commessa commessa) {
			super(title, "ordine/collo/cerca", ColloCaricoJSON[].class, sede, commessa, filtro);
		}
		
	}
	
	public List<ProdottoCaricoJSON> trovaProdotti(int carico, Commessa commessa) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProdottoCaricoJSON filtro = new ProdottoCaricoJSON();
		filtro.setCarico(carico);
		ProcessoTrovaProdotti processo = new ProcessoTrovaProdotti(filtro, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<ProdottoCaricoJSON> dettagli = processo.getLista();
		return dettagli;
	}
	
	private class ProcessoTrovaProdotti extends ProcessoRicercaDati<ProdottoCaricoJSON> {

		public ProcessoTrovaProdotti(ProdottoCaricoJSON filtro, Sede sede, Commessa commessa) {
			super(title, "ordine/prodotto/cerca", ProdottoCaricoJSON[].class, sede, commessa, filtro);
		}
		
	}
	
	@Override
	protected void aggiornaInfoInserimento(OrdineDettaglio object, OrdineDettaglio nuovo) {
		object.setId(nuovo.getId());
	}

}
