package it.ltc.logica.admin.gui.elements.funzione;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.sistema.ControllerPermessi;
import it.ltc.logica.database.model.centrale.Funzione;
import it.ltc.logica.database.model.centrale.utenti.Permesso;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreFunzioni extends Etichettatore<Funzione> {

	@Override
	public String getTesto(Funzione oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome(); break;
			case 1 : testo = oggetto.getPartid(); break;
			case 2 : testo = getPermesso(oggetto); break;
			case 3 : testo = oggetto.getFeature(); break;
			case 4 : testo = " "; break; //Ci sarà l'icona vicino, non serve il nome.
			default : testo = "";
		}
		return testo;
	}
	
	private String getPermesso(Funzione oggetto) {
		Permesso permesso = ControllerPermessi.getInstance().getPermesso(oggetto.getPermessoid());
		String stringa = permesso != null ? permesso.getNome() + " (" + permesso.getId() + ")" : "N/A";
		return stringa;
	}

	@Override
	public String getTestoTooltip(Funzione oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(Funzione oggetto, int colonna) {
		Image image = null;
		if (colonna == 4) {
			image = Immagine.valueOf(oggetto.getIcona()).getImage();
		}
		return image;
	}

}
