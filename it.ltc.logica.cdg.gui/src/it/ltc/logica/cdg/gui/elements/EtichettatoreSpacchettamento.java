package it.ltc.logica.cdg.gui.elements;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.cdg.ControllerCdgEventi;
import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.database.model.centrale.cdg.CdgPezzoEvento;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreSpacchettamento extends Etichettatore<CdgPezzoEvento> {

	private final ControllerCdgEventi controllerEventi;
	private final ControllerCdgFasi controllerFasi;
	
	protected EtichettatoreSpacchettamento() {
		super(DEFAULT_DATE_FORMAT, DEFAULT_EURO_FORMAT, DEFAULT_DECIMAL_FORMAT, HIGH_PRECISION_PERCENTAGE_FORMAT);
		controllerEventi = ControllerCdgEventi.getInstance();
		controllerFasi = ControllerCdgFasi.getInstance();
	}
	
	@Override
	public String getTesto(CdgPezzoEvento oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getEvento(oggetto); break;
			case 1 : testo = getFase(oggetto); break;
			case 2 : testo = formatPercentuali.format(oggetto.getCosto() / 100.0); break;
			case 3 : testo = formatPercentuali.format(oggetto.getRicavo() / 100.0); break;
			default : testo = "";
		}
		return testo;
	}

	private String getFase(CdgPezzoEvento oggetto) {
		CdgEvento evento = controllerEventi.getEvento(oggetto.getEvento());
		CdgFase fase = evento != null ? controllerFasi.getFase(evento.getFase()) : null;
		String testo = fase != null ? fase.getNome() : "N/A";
		return testo;
	}

	private String getEvento(CdgPezzoEvento oggetto) {
		CdgEvento evento = controllerEventi.getEvento(oggetto.getEvento());
		String testo = evento != null ? evento.getNome() : "N/A";
		return testo;
	}

	@Override
	public String getTestoTooltip(CdgPezzoEvento oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CdgPezzoEvento oggetto, int colonna) {
		return null;
	}

}
