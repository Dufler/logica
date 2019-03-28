package it.ltc.logica.ufficio.gui.uscite.elements.assegnazione;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneOrdine;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneOrdine.StatoAssegnazione;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneRigaOrdine;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.tree.AlberoEtichettatore;

public class RisultatoEtichettatore extends AlberoEtichettatore {

	public RisultatoEtichettatore(int columnIndex) {
		super(columnIndex);
	}

	@Override
	protected String getTestoConStile(Object oggetto, int indiceColonna) {
		String testo;
		if (oggetto instanceof RisultatoAssegnazioneOrdine) {
			RisultatoAssegnazioneOrdine risultato = (RisultatoAssegnazioneOrdine) oggetto;
			switch (indiceColonna) {
				case 1 : testo = risultato.getOrdine().getNumeroLista(); break;
				case 2 : testo = risultato.getOrdine().getRiferimento(); break;
				case 3 : testo = risultato.getStato().name(); break;
				default : testo = "";
			}
		} else if (oggetto instanceof RisultatoAssegnazioneRigaOrdine) {
			RisultatoAssegnazioneRigaOrdine riga = (RisultatoAssegnazioneRigaOrdine) oggetto;
			switch (indiceColonna) {
				case 4 : testo = Integer.toString(riga.getNumeroRiga()); break;
				case 5 : testo = riga.getSku(); break;
				case 6 : testo = riga.getTaglia(); break;
				case 7 : testo = riga.getDescrizione(); break;
				case 8 : testo = Integer.toString(riga.getQuantitaRichiesta()); break;
				case 9 : testo = Integer.toString(riga.getQuantitaAssegnata()); break;
				case 10 : testo = riga.getStato().toString(); break;
				case 11 : testo = riga.getCollo(); break;
				case 12 : testo = riga.getUbicazione(); break;
				case 13 : testo = Integer.toString(riga.getQuantita()); break;
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
		if (indiceColonna == 1 && oggetto instanceof RisultatoAssegnazioneOrdine) {
			RisultatoAssegnazioneOrdine risultato = (RisultatoAssegnazioneOrdine) oggetto;
			image = risultato.getStato() == StatoAssegnazione.OK ? Immagine.SPUNTAVERDE_16X16.getImage() : Immagine.CROCEROSSA_16X16.getImage();
		} else if (indiceColonna == 3 && oggetto instanceof RisultatoAssegnazioneRigaOrdine) {
			RisultatoAssegnazioneRigaOrdine riga = (RisultatoAssegnazioneRigaOrdine) oggetto;
			switch (riga.getStato()) {
				case PRELIEVO : image = Immagine.SPUNTAVERDE_16X16.getImage(); break;
				case SCORTA : image = Immagine.SCATOLA_16X16.getImage(); break;
				case NONUBICATO : image = Immagine.SCATOLA_ANNULLATA_16X16.getImage(); break;
				case NONPRESENTE : image = Immagine.RED_QUESTION_MARK_12X20.getImage(); break;
				case LEGACY : image = Immagine.RED_MARK_12X20.getImage(); break; 
			}
		}
		return image;
	}

}
