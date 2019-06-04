package it.ltc.logica.amministrazione.gui.fatturazione.elements.categorie;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCategorie extends Etichettatore<CategoriaMerceologica> {
	
	private final ControllerCommesse controller;
	
	public EtichettatoreCategorie() {
		controller = ControllerCommesse.getInstance();
	}

	@Override
	public String getTesto(CategoriaMerceologica oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome(); break;
			case 1 : testo = oggetto.getDescrizione(); break;
			case 2 : testo = getCommessa(oggetto); break;
			case 4 : testo = oggetto.getStato().name(); break;
			default : testo = "";
		}
		return testo;
	}
	
	private String getCommessa(CategoriaMerceologica oggetto) {
		Commessa commessa = controller.getCommessa(oggetto.getCommessa());
		String testo = commessa != null ? commessa.getNome() : "N/A";
		return testo;
	}

	@Override
	public String getTestoTooltip(CategoriaMerceologica oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CategoriaMerceologica oggetto, int colonna) {
		return null;
	}

}
