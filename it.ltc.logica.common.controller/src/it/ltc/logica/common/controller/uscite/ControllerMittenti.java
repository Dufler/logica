package it.ltc.logica.common.controller.uscite;

import java.util.List;

import it.ltc.logica.common.controller.ControllerCommessa;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDato;
import it.ltc.logica.common.controller.processi.ProcessoRicercaDati;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerMittenti extends ControllerCommessa<Indirizzo> {
	
	public static final String title = "Mittenti";
	public static final String resource = "ordine/mittente";

	public ControllerMittenti(Commessa commessa) {
		super(title, resource, Indirizzo[].class, commessa);
	}
	
	public List<Indirizzo> getIndirizzi(Indirizzo filtro) {
		ProcessoRicercaDati<Indirizzo> processo = new ProcessoRicercaDati<>(title, resource + "/cerca", Indirizzo[].class, sede, commessa, filtro);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<Indirizzo> indirizzi = processo.getLista();
		return indirizzi;
	}

	public Indirizzo getIndirizzo(int id) {
		ProcessoCaricamentoDato<Indirizzo> processo = new ProcessoCaricamentoDato<>(title, resource + "/" + id, Indirizzo.class, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		Indirizzo fornitore = processo.getObject();
		return fornitore;
	}
	
	@Override
	protected void aggiornaInfoInserimento(Indirizzo object, Indirizzo nuovo) {
		object.setId(nuovo.getId());
	}

}
