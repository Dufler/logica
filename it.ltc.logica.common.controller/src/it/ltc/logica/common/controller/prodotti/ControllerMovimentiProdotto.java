package it.ltc.logica.common.controller.prodotti;

import it.ltc.logica.common.controller.ControllerCommessa;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.MovimentoProdotto;

public class ControllerMovimentiProdotto extends ControllerCommessa<MovimentoProdotto> {
	
	private static final String title = "Movimenti";
	private static final String resource = "movimenti";

	public ControllerMovimentiProdotto(Commessa commessa) {
		super(title, resource, MovimentoProdotto[].class, commessa);
	}

}
