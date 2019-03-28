package it.ltc.logica.ufficio.gui.ingressi.elements;

import java.util.Date;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.database.model.centrale.ingressi.StatiCarico;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCaricoTestata extends Etichettatore<CaricoTestata> {

	@Override
	public String getTesto(CaricoTestata oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getData(oggetto); break;
			case 1 : testo = getRiferimentoCliente(oggetto); break;
			case 2 : testo = getRiferimentoInterno(oggetto); break;
			case 3 : testo = getPezziDichiarati(oggetto); break;
			case 4 : testo = getPezziRiscontrati(oggetto); break;
			case 5 : testo = getStato(oggetto); break;
			case 6 : testo = getTipo(oggetto); break;
			default : testo = "";
		}
		return testo;
	}

	private String getTipo(CaricoTestata oggetto) {
		String testo = oggetto.getTipo() != null ? oggetto.getTipo() : "-";
		return testo;
	}
	
	private String getDescrizioneTipo(CaricoTestata oggetto) {
		String testo = oggetto.getTipo() != null ? oggetto.getTipo() : "-";
		return testo;
	}

	private String getStato(CaricoTestata oggetto) {
		StatiCarico stato = oggetto.getStato();
		String testo = stato != null ? stato.toString() : "-";
		return testo;
	}
	
	private String getDescrizioneStato(CaricoTestata oggetto) {
		StatiCarico stato = oggetto.getStato();
		String testo = stato != null ? stato.toString() : "-";
		return testo;
	}

	private String getPezziDichiarati(CaricoTestata oggetto) {
		String testo = Integer.toString(oggetto.getQuantitaDichiarataTotale());
		return testo;
	}
	
	private String getPezziRiscontrati(CaricoTestata oggetto) {
		String testo = Integer.toString(oggetto.getQuantitaRiscontrataTotale());
		return testo;
	}

	private String getRiferimentoInterno(CaricoTestata oggetto) {
		String testo = oggetto.getDocumentoRiferimento() != null ? oggetto.getDocumentoRiferimento() : "-";
		return testo;
	}

	private String getRiferimentoCliente(CaricoTestata oggetto) {
		String testo = oggetto.getRiferimento() != null ? oggetto.getRiferimento() : "-";
		return testo;
	}

	private String getData(CaricoTestata oggetto) {
		Date data = oggetto.getDataArrivo();
		String testo = data != null ? sdf.format(data) : "Non Disponibile";
		return testo;
	}

	@Override
	public String getTestoTooltip(CaricoTestata oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getData(oggetto); break;
			case 1 : testo = getRiferimentoCliente(oggetto); break;
			case 2 : testo = getRiferimentoInterno(oggetto); break;
			case 3 : testo = getPezziDichiarati(oggetto); break;
			case 4 : testo = getPezziRiscontrati(oggetto); break;
			case 5 : testo = getDescrizioneStato(oggetto); break;
			case 6 : testo = getDescrizioneTipo(oggetto); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public Image getIcona(CaricoTestata oggetto, int colonna) {
		return null;
	}

}
