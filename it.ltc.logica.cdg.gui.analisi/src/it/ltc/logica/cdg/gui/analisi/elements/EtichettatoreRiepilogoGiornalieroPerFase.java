package it.ltc.logica.cdg.gui.analisi.elements;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.cdg.gui.analisi.model.RiepilogoGiornalieroPerFaseCDG;
import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreRiepilogoGiornalieroPerFase extends Etichettatore<RiepilogoGiornalieroPerFaseCDG> {

	@Override
	public String getTesto(RiepilogoGiornalieroPerFaseCDG oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getFase(oggetto); break;
			case 1 : testo = formatEuro.format(oggetto.getRicaviIpotetici()); break;
			case 2 : testo = formatEuro.format(oggetto.getCostiReali()); break;
			case 3 : testo = formatEuro.format(oggetto.getCostiIpotetici()); if (oggetto.getFase() == CdgFase.ID_EXTRA) testo = "N/A"; break;
			case 4 : testo = formatEuro.format(oggetto.getScostamentoTempi()); if (oggetto.getFase() == CdgFase.ID_EXTRA) testo = "N/A"; break;
			case 5 : testo = formatEuro.format(oggetto.getScostamentoCDG()); break;
			case 6 : testo = Integer.toString(oggetto.getTotalePezzi()); break;
			case 7 : testo = Integer.toString(oggetto.getTotaleSecondi()); break;
			default : testo = "";
		}
		return testo;
	}

	private String getFase(RiepilogoGiornalieroPerFaseCDG oggetto) {
		CdgFase fase = ControllerCdgFasi.getInstance().getFase(oggetto.getFase());
		String testo = fase != null ? fase.getNome() : "N/A";
		return testo;
	}

	@Override
	public String getTestoTooltip(RiepilogoGiornalieroPerFaseCDG oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(RiepilogoGiornalieroPerFaseCDG oggetto, int colonna) {
		Image icona;
		switch (colonna) {
			case 4 : icona = oggetto.isPerditaTempi() ? Immagine.SOLDIGIU_16X32.getImage() : Immagine.SOLDISU_16X32.getImage(); /*Fix sulla fase, tolgo l'icona*/ if (oggetto.getFase() == CdgFase.ID_EXTRA) icona = null; break;
			case 5 : icona = oggetto.isPerditaCDG() ? Immagine.SOLDIGIU_16X32.getImage() : Immagine.SOLDISU_16X32.getImage(); break;
			default : icona = null;
		}
		return icona;
	}

}
