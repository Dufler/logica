package it.ltc.logica.ufficio.gui.uscite.elements.finalizzazione;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.ordini.ProblemaFinalizzazioneOrdine;
import it.ltc.logica.database.model.centrale.ordini.RisultatoFinalizzazioneOrdine;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.tree.AlberoEtichettatore;

public class FinalizzazioneOrdineEtichettatore extends AlberoEtichettatore {

	public FinalizzazioneOrdineEtichettatore(int columnIndex) {
		super(columnIndex);
	}

	@Override
	protected String getTestoConStile(Object oggetto, int indiceColonna) {
		String testo;
		if (oggetto instanceof RisultatoFinalizzazioneOrdine) {
			RisultatoFinalizzazioneOrdine risultato = (RisultatoFinalizzazioneOrdine) oggetto;
			switch (indiceColonna) {
				case 1 : testo = risultato.getOrdine().getNumeroLista(); break;
				case 2 : testo = risultato.getOrdine().getRiferimento(); break;
				default : testo = "";
			}
		} else if (oggetto instanceof ProblemaFinalizzazioneOrdine) {
			ProblemaFinalizzazioneOrdine problema = (ProblemaFinalizzazioneOrdine) oggetto;
			switch (indiceColonna) {
				case 3 : testo = Integer.toString(problema.getNumeroRiga()); break;
				case 4 : testo = problema.getSku(); break;
				case 5 : testo = problema.getTaglia(); break;
				case 6 : testo = problema.getDescrizione(); break;
				case 7 : testo = Integer.toString(problema.getQuantitaDisponibile()); break;
				case 8 : testo = Integer.toString(problema.getQuantitaRichiesta()); break;
				default : testo = "";
			}
		} else {
			testo = "N/A";
		}		
		return testo;
	}

	@Override
	protected Image getImmagine(Object oggetto, int indiceColonna) {
		Image image = null;
		if (indiceColonna == 1 && oggetto instanceof RisultatoFinalizzazioneOrdine) {
			RisultatoFinalizzazioneOrdine risultato = (RisultatoFinalizzazioneOrdine) oggetto;
			boolean ok = risultato.getProblemi() == null || risultato.getProblemi().isEmpty(); 
			image = ok ? Immagine.SPUNTAVERDE_16X16.getImage() : Immagine.CROCEROSSA_16X16.getImage();
		}
		return image;
	}

}
