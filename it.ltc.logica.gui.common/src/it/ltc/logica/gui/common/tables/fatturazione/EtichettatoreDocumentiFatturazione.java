package it.ltc.logica.gui.common.tables.fatturazione;

import java.text.SimpleDateFormat;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura.Stato;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreDocumentiFatturazione extends Etichettatore<DocumentoFattura> {
	
	private final SimpleDateFormat sdf;
	
	public EtichettatoreDocumentiFatturazione() {
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}

	@Override
	public String getTesto(DocumentoFattura oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getNomeCommessa(oggetto); break;
			case 1 : testo = getTipoDocumento(oggetto); break;
			case 2 : testo = getStato(oggetto); break;
			case 3 : testo = oggetto.getMeseAnno(); break;
			case 4 : testo = getData(oggetto); break;
			case 5 : testo = oggetto.getUtenteCreatore(); break;
			default : testo = "";
		}
		return testo;
	}
	
	@Override
	public String getTestoTooltip(DocumentoFattura oggetto, int colonna) {
		String testo = getTesto(oggetto, colonna);
		return testo;
	}

	@Override
	public Image getIcona(DocumentoFattura oggetto, int colonna) {
		return null;
	}

	private String getStato(DocumentoFattura oggetto) {
		Stato stato = oggetto.getStato();
		String nome = stato != null ? stato.toString() : "???";
		return nome;
	}

	private String getData(DocumentoFattura oggetto) {
		String data = sdf.format(oggetto.getDataGenerazione());
		return data;
	}

	private String getTipoDocumento(DocumentoFattura oggetto) {
		AmbitoFattura ambito = ControllerAmbitiFatturazione.getInstance().getAmbito(oggetto.getIdAmbito());
		String tipo = ambito != null ? ambito.getNome() : "";
		return tipo;
	}

	private String getNomeCommessa(DocumentoFattura oggetto) {
		Commessa commessa = ControllerCommesse.getInstance().getCommessa(oggetto.getIdCommessa());
		String nome = commessa != null ? commessa.getNome() : "";
		return nome;
	}

}
