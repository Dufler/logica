package it.ltc.logica.cdg.gui.eventi.elements.abbinamentoeventi;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.cdg.ControllerCdgEventi;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.cdg.CdgCommessaEvento;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCommessaEventi extends Etichettatore<CdgCommessaEvento> {
	
	private final ControllerCommesse controllerCommesse;
	private final ControllerCdgEventi controllerEventi;
	
	protected EtichettatoreCommessaEventi() {
		controllerCommesse = ControllerCommesse.getInstance();
		controllerEventi = ControllerCdgEventi.getInstance();
	}

	@Override
	public String getTesto(CdgCommessaEvento oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getCommessa(oggetto); break;
			case 1 : testo = getEvento(oggetto); break;
			case 2 : testo = getDurata(oggetto); break;
			default : testo = "";
		} 
		return testo;
	}

	private String getDurata(CdgCommessaEvento oggetto) {
		int durata = oggetto.getDurata();
		String testo;
		if (durata < 1) {
			testo = "Valore non accettabile! ( " + durata + " )";
		} else if (durata == 1) {
			testo = "1 secondo";
		} else {
			testo = durata  + " secondi";
		}
		return testo;
	}

	private String getEvento(CdgCommessaEvento oggetto) {
		CdgEvento evento = controllerEventi.getEvento(oggetto.getEvento());
		String testo = evento != null ? evento.getNome() : "N/A";
		return testo;
	}

	private String getCommessa(CdgCommessaEvento oggetto) {
		Commessa commessa = controllerCommesse.getCommessa(oggetto.getCommessa());
		String testo = commessa != null ? commessa.getNome() : "N/A";
		return testo;
	}

	@Override
	public String getTestoTooltip(CdgCommessaEvento oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CdgCommessaEvento oggetto, int colonna) {
		Image image = null;
		if (colonna == 2 && oggetto.getDurata() < 1) {
			image = Immagine.RED_MARK_12X20.getImage();
		}
		return image;
	}

}
