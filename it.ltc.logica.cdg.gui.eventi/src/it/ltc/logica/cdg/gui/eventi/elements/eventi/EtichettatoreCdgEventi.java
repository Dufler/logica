package it.ltc.logica.cdg.gui.eventi.elements.eventi;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.common.controller.sistema.ControllerCategorieMerceologiche;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCdgEventi extends Etichettatore<CdgEvento> {

	@Override
	public String getTesto(CdgEvento oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome(); break;
			case 1 : testo = getFase(oggetto); break;
			case 2 : testo = getCategoria(oggetto); break;
			case 3 : testo = oggetto.getDescrizione(); break;
			default : testo = "";
		}
		return testo;
	}

	private String getCategoria(CdgEvento oggetto) {
		CategoriaMerceologica categoria = ControllerCategorieMerceologiche.getInstance().getCategoria(oggetto.getCategoriaMerceologica(), 0); //FIXME
		String testo = categoria != null ? categoria.getNome() : "N/A";
		return testo;
	}

	private String getFase(CdgEvento oggetto) {
		CdgFase fase = ControllerCdgFasi.getInstance().getFase(oggetto.getFase());
		String testo = fase != null ? fase.getNome() : "N/A";
		return testo;
	}

	@Override
	public String getTestoTooltip(CdgEvento oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CdgEvento oggetto, int colonna) {
		return null;
	}

}
