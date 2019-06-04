package it.ltc.logica.common.controller.uscite;

import java.util.Date;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCommessa;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDato;
import it.ltc.logica.common.controller.processi.ProcessoRicercaDati;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.ordini.Delivery;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerDelivery extends ControllerCommessa<Delivery> {
	
	public static final String title = "Delivery";
	public static final String resource = "delivery";

	public ControllerDelivery(Commessa commessa) {
		super(title, resource, Delivery[].class, commessa);
	}
	
	public List<Delivery> trovaDelivery(String corriere, Date da, Date a) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		Delivery filtro = new Delivery();
		filtro.setDa(da);
		filtro.setA(a);
		filtro.setCorriere(corriere);
		ProcessoTrovaDelivery processo = new ProcessoTrovaDelivery(sede, commessa, filtro);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<Delivery> deliveries = processo.getLista();
		return deliveries;
	}
	
	protected class ProcessoTrovaDelivery extends ProcessoRicercaDati<Delivery> {

		public ProcessoTrovaDelivery(Sede sede, Commessa commessa, Delivery filtro) {
			super(title, "delivery/cerca", Delivery[].class, sede, commessa, filtro);
		}
		
	}
	
	public Delivery trovaDettagliDelivery(Delivery delivery) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoRecuperoDettagliDelivery processo = new ProcessoRecuperoDettagliDelivery(sede, commessa, delivery);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		Delivery dettagli = processo.getEsito() ? processo.getObject() : null;
		return dettagli;
	}
	
	protected class ProcessoRecuperoDettagliDelivery extends ProcessoCaricamentoDato<Delivery> {

		public ProcessoRecuperoDettagliDelivery(Sede sede, Commessa commessa, Delivery filtro) {
			super(title, "delivery/" + filtro.getId(), Delivery.class, sede, commessa);
		}
		
	}

}
