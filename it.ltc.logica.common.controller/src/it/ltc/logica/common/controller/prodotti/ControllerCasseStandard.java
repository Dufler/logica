package it.ltc.logica.common.controller.prodotti;

import java.util.List;

import it.ltc.logica.common.controller.ControllerCommessa;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDato;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.CassaStandard;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerCasseStandard  extends ControllerCommessa<CassaStandard> {
	
	private static final String title = "Casse Standard";
	private static final String resource = "cassastandard";

	public ControllerCasseStandard(Commessa commessa) {
		super(title, resource, CassaStandard[].class, commessa);
	}
	
	public List<CassaStandard> getCasse() {
		ProcessoCaricamentoDati<CassaStandard> processo = new ProcessoCaricamentoDati<>(title, resource, CassaStandard[].class, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<CassaStandard> casse = processo.getLista();
		return casse;
	}

	public CassaStandard getCassa(int id) {
		ProcessoCaricamentoDato<CassaStandard> processo = new ProcessoCaricamentoDato<>(title, resource + "/" + id, CassaStandard.class, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		CassaStandard cassa = processo.getObject();
		return cassa;
	}
	
	@Override
	protected void aggiornaInfoInserimento(CassaStandard object, CassaStandard nuovo) {
		//DO NOTHING!
	}
}
