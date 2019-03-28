package it.ltc.logica.ufficio.gui.uscite.elements.generamovimenti;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.ordini.RisultatoGenerazioneMovimenti;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.tree.AlberoEtichettatore;

public class GenerazioneMovimentiOrdineEtichettatore extends AlberoEtichettatore {

	public GenerazioneMovimentiOrdineEtichettatore(int columnIndex) {
		super(columnIndex);
	}

	@Override
	protected String getTestoConStile(Object oggetto, int indiceColonna) {
		String testo;
		if (oggetto instanceof RisultatoGenerazioneMovimenti) {
			RisultatoGenerazioneMovimenti risultato = (RisultatoGenerazioneMovimenti) oggetto;
			switch (indiceColonna) {
				case 1 : testo = risultato.getOrdine().getNumeroLista(); break;
				case 2 : testo = risultato.getOrdine().getRiferimento(); break;
				default : testo = "";
			}
		} else if (oggetto instanceof String && indiceColonna == 3) {
			testo = oggetto.toString();
		} else {
			testo = "";
		}		
		return testo;
	}

	@Override
	protected Image getImmagine(Object oggetto, int indiceColonna) {
		Image image = null;
		if (indiceColonna == 1 && oggetto instanceof RisultatoGenerazioneMovimenti) {
			RisultatoGenerazioneMovimenti risultato = (RisultatoGenerazioneMovimenti) oggetto;
			image = risultato.isEsito() ? Immagine.SPUNTAVERDE_16X16.getImage() : Immagine.CROCEROSSA_16X16.getImage();
		}
		return image;
	}

}
