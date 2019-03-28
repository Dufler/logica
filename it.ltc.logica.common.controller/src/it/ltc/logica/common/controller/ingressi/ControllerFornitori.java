package it.ltc.logica.common.controller.ingressi;

import java.util.List;

import it.ltc.logica.common.controller.ControllerCommessa;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDato;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.Fornitore;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerFornitori extends ControllerCommessa<Fornitore> {

	public static final String title = "Fornitori";
	public static final String resource = "fornitore";
	
	public ControllerFornitori(Commessa commessa) {
		super(title, resource, Fornitore[].class, commessa);
	}

	public List<Fornitore> getFornitori() {
		ProcessoCaricamentoDati<Fornitore> processo = new ProcessoCaricamentoDati<>(title, resource, Fornitore[].class, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<Fornitore> fornitori = processo.getLista();
		return fornitori;
	}

	public Fornitore getFornitore(int id) {
		ProcessoCaricamentoDato<Fornitore> processo = new ProcessoCaricamentoDato<>(title, resource + "/" + id, Fornitore.class, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		Fornitore fornitore = processo.getObject();
		return fornitore;
	}
	
	@Override
	protected void aggiornaInfoInserimento(Fornitore object, Fornitore nuovo) {
		object.setId(nuovo.getId());
	}

}
