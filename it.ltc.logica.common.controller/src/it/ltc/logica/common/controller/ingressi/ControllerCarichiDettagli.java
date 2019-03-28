package it.ltc.logica.common.controller.ingressi;

import java.util.List;

import it.ltc.logica.common.controller.ControllerCommessa;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.common.controller.processi.ProcessoRicercaDati;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.ingressi.CaricoDettaglio;
import it.ltc.logica.database.model.centrale.ingressi.ColloCaricoJSON;
import it.ltc.logica.database.model.centrale.ingressi.ProdottoCaricoJSON;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerCarichiDettagli extends ControllerCommessa<CaricoDettaglio> {

	public static final String title = "Dettagli carico";
	public static final String resource = "carico/dettaglio";

	public ControllerCarichiDettagli(Commessa commessa) {
		super(title, resource, CaricoDettaglio[].class, commessa);
	}
	
	public List<CaricoDettaglio> trovaDettagli(int carico, Commessa commessa) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoTrovaDettagli processo = new ProcessoTrovaDettagli(carico, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<CaricoDettaglio> dettagli = processo.getLista();
		return dettagli;
	}
	
	private class ProcessoTrovaDettagli extends ProcessoCaricamentoDati<CaricoDettaglio> {

		public ProcessoTrovaDettagli(int carico, Sede sede, Commessa commessa) {
			super(title, resource + "/" + carico, CaricoDettaglio[].class, sede, commessa);
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
			super(title, "carico/collo/cerca", ColloCaricoJSON[].class, sede, commessa, filtro);
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
			super(title, "carico/prodotto/cerca", ProdottoCaricoJSON[].class, sede, commessa, filtro);
		}
		
	}
	
	@Override
	protected void aggiornaInfoInserimento(CaricoDettaglio object, CaricoDettaglio nuovo) {
		object.setId(nuovo.getId());
	}

}
