package it.ltc.logica.admin.gui.elements.feature;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.sistema.ControllerPermessi;
import it.ltc.logica.database.model.centrale.Feature;
import it.ltc.logica.database.model.centrale.utenti.Permesso;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreFeature extends Etichettatore<Feature> {

	@Override
	public String getTesto(Feature oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome(); break;
			case 1 : testo = oggetto.getVersione(); break;
			case 2 : testo = oggetto.getFeatureid(); break;
			case 3 : testo = oggetto.getPerspectiveid(); break;
			case 4 : testo = getPermesso(oggetto); break;
			case 5 : testo = " "; break; //Ci sarà l'icona vicino, non serve il nome.
			case 6 : testo = oggetto.getColore(); break;
			case 7 : testo = Integer.toString(oggetto.getPosizione()); break;
			default : testo = "";
		}
		return testo;
	}
	
	private String getPermesso(Feature oggetto) {
		Permesso permesso = ControllerPermessi.getInstance().getPermesso(oggetto.getPermessoid());
		String stringa = permesso != null ? permesso.getNome() + " (" + permesso.getId() + ")" : "N/A";
		return stringa;
	}

	@Override
	public String getTestoTooltip(Feature oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(Feature oggetto, int colonna) {
		Image image = null;
		if (colonna == 5) {
			image = Immagine.valueOf(oggetto.getIcona()).getImage();
		}
		return image;
	}

}
