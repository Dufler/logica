package it.ltc.logica.common.controller.prodotti;

import java.util.List;

import it.ltc.logica.common.controller.ControllerCommessa;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDato;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Cassa;
import it.ltc.logica.database.model.prodotto.ElementoCassa;
import it.ltc.logica.database.model.prodotto.Prodotto;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerCasse extends ControllerCommessa<Cassa> {
	
	private static final String title = "Casse";
	private static final String resource = "cassa";
	
	private final ControllerProdotti controllerProdotti;

	public ControllerCasse(Commessa commessa) {
		super(title, resource, Cassa[].class, commessa);
		this.controllerProdotti = new ControllerProdotti(commessa);
	}
	
	public List<Cassa> getCasse() {
		ProcessoCaricamentoDati<Cassa> processo = new ProcessoCaricamentoDati<>(title, resource, Cassa[].class, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<Cassa> casse = processo.getLista();
		for (Cassa cassa : casse)
			recuperaInfoProdotti(cassa);
		return casse;
	}

	public Cassa getCassa(int id) {
		ProcessoCaricamentoDato<Cassa> processo = new ProcessoCaricamentoDato<>(title, resource + "/" + id, Cassa.class, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		Cassa cassa = processo.getObject();
		recuperaInfoProdotti(cassa);
		return cassa;
	}
	
	@Override
	protected void aggiornaInfoInserimento(Cassa object, Cassa nuovo) {
		object.setIdCassa(nuovo.getIdCassa());
	}
	
	protected void recuperaInfoProdotti(Cassa cassa) {
		//Recupero il prodotto a cassa
		Prodotto prodottoACassa = controllerProdotti.trovaDaID(cassa.getIdCassa());
		cassa.setCassa(prodottoACassa);
		//Recupero i singoli elementi
		for (ElementoCassa elemento : cassa.getProdotti()) {
			Prodotto prodotto = controllerProdotti.trovaDaID(elemento.getIdProdotto());
			elemento.setProdotto(prodotto);
		}
	}

}
