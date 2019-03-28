package it.ltc.logica.trasporti.gui.elements.spedizionemodel;

import java.util.Date;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.common.calcolo.algoritmi.VoceCalcolata;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.database.model.centrale.trasporti.Giacenza;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

public class EtichettatoreSpedizioniModel extends Etichettatore<SpedizioneModel> {

	private final Integer idListino;
	
	public EtichettatoreSpedizioniModel() {
		this(null);
	}
	
	public EtichettatoreSpedizioniModel(Integer id) {
		super(DEFAULT_DATE_FORMAT, DEFAULT_EURO_FORMAT, HIGH_PRECISION_DECIMAL_FORMAT, DEFAULT_PERCENTAGE_FORMAT);
		idListino = id;
	}
	
	@Override
	public String getTesto(SpedizioneModel oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getData(oggetto.getSpedizione()); break;
			case 1 : testo = getDestinatario(oggetto.getDestinazione()); break;
			case 2 : testo = getLocalita(oggetto.getDestinazione()); break;
			case 3 : testo = oggetto.getSpedizione().getRiferimentoCliente(); break;
			case 4 : case  5 : testo = getTotale(oggetto); break;
			case 6 : testo = getPezzi(oggetto.getSpedizione()); break;
			case 7 : testo = getColli(oggetto.getSpedizione()); break;
			case 8 : testo = getPeso(oggetto.getSpedizione()); break;
			case 17 : testo = getVolume(oggetto.getSpedizione()); break;
			case 9 : testo = getDataAperturaGiacenza(oggetto.getGiacenza()); break;
			case 10 : testo = getDataChiusuraGiacenza(oggetto.getGiacenza()); break;
			case 11 : testo = getDurataGiacenza(oggetto.getGiacenza()); break;
			case 12 : testo = getLetteraDiVettura(oggetto.getSpedizione()); break;
			case 13 : testo = getLetteraDiVetturaGiacenza(oggetto.getGiacenza()); break;
			case 14 : testo = getLetteraDiVetturaOriginaleGiacenza(oggetto.getGiacenza()); break;
			case 15 : testo = getContrassegno(oggetto.getContrassegno()); break;
			case 16 : testo = getCostoReale(oggetto.getSpedizione()); break;
			default : testo = "";
		}
		return testo;
	}
	
	private String getCostoReale(Spedizione spedizione) {
		Double costo = spedizione.getCosto();
		String valore = costo != null ? formatEuro.format(costo) : "-";
		return valore;
	}

	private String getContrassegno(Contrassegno contrassegno) {
		String valore = contrassegno != null && contrassegno.getValore() != null ? formatEuro.format(contrassegno.getValore()) : "";
		return valore;
	}

	private String getLetteraDiVetturaOriginaleGiacenza(Giacenza giacenza) {
		String ldv = giacenza.getLetteraDiVetturaOriginale();
		if (ldv == null || ldv.isEmpty())
			ldv = "???";
		return ldv;
	}

	private String getLetteraDiVetturaGiacenza(Giacenza giacenza) {
		String ldv = giacenza.getLetteraDiVettura();
		if (ldv == null || ldv.isEmpty())
			ldv = "???";
		return ldv;
	}

	private String getLetteraDiVettura(Spedizione spedizione) {
		String ldv = spedizione.getLetteraDiVettura();
		if (ldv == null || ldv.isEmpty())
			ldv = "???";
		return ldv;
	}

	private String getDurataGiacenza(Giacenza giacenza) {
		String testo = "";
		if (giacenza != null && giacenza.getDataChiusura() != null && giacenza.getDataApertura() != null) {
			Integer durataInGiorni = giacenza.calcolaDurata();
			testo = durataInGiorni.toString();
		}
		return testo;
	}

	private String getDataChiusuraGiacenza(Giacenza giacenza) {
		String testo = "";
		if (giacenza != null) {
			Date data = giacenza.getDataChiusura();
			testo = data != null ? sdf.format(data) : "???";
		}
		return testo;
	}

	private String getDataAperturaGiacenza(Giacenza giacenza) {
		String testo = "";
		if (giacenza != null) {
			Date data = giacenza.getDataApertura();
			testo = data != null ? sdf.format(data) : "???";
		}
		return testo;
	}

	private String getPeso(Spedizione spedizione) {
		Double peso = spedizione.getPeso();
		String testoPeso = peso != null ? formatDecimali.format(peso) : "-";
		String testo = testoPeso + " Kg";
		return testo;
	}
	
	private String getVolume(Spedizione spedizione) {
		Double volume = spedizione.getVolume();
		String testoVolume = volume != null ? formatDecimali.format(volume) : "-";
		String testo = testoVolume + " mc";
		return testo;
	}

	private String getColli(Spedizione spedizione) {
		Integer colli = spedizione.getColli();
		String testo = colli != null ? colli.toString() : "";
		return testo;
	}

	private String getPezzi(Spedizione spedizione) {
		Integer pezzi = spedizione.getPezzi();
		String testo = pezzi != null ? pezzi.toString() : "";
		return testo;
	}

	@Override
	public String getTestoTooltip(SpedizioneModel oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getData(oggetto.getSpedizione()); break;
			case 1 : case 2: testo = getToolTipDestinatario(oggetto.getDestinazione()); break;
			case 3 : testo = oggetto.getSpedizione().getRiferimentoCliente(); break;
			case 4 : case  5 : testo = getTooltipTotale(oggetto); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public Image getIcona(SpedizioneModel oggetto, int colonna) {
		Image image = null;
		if (colonna == 0 && !oggetto.isDatiPerCalcoloCompleti()) {
			image = Immagine.RED_MARK_12X20.getImage();
		} else if (colonna == 4) {
			Calcolo calcolo = oggetto.getCalcolo(idListino);
			if (calcolo != null && calcolo.isRicavoMaggiore()) {
				image = Immagine.MONETE_16X16.getImage();
			}
		} else if (colonna == 5) {
			Calcolo calcolo = oggetto.getCalcolo(idListino);
			if (calcolo != null && calcolo.isCostoMinore()) {
				image = Immagine.SALVADANAIO_16X16.getImage();
			}
		} else if (colonna == 6 && !oggetto.isDatiPezzi()) {
			image = Immagine.RED_MARK_12X20.getImage();
		} else if (colonna == 7 && !oggetto.isDatiColli()) {
			image = Immagine.RED_MARK_12X20.getImage();
		} else if (colonna == 8 && !(oggetto.isDatiPeso() && oggetto.isDatiVolume())) {
			image = Immagine.RED_MARK_12X20.getImage();
		}
		return image;
	}
	
	private String getData(Spedizione spedizione) {
		String testo;
		Date data = spedizione.getDataPartenza();
		if (data != null)
			testo = sdf.format(data);
		else
			testo = "Non ancora partita.";
		return testo;
	}
	
	public String getDestinatario(Indirizzo destinatario) {
		String ragioneSociale = destinatario.getRagioneSociale();
		return ragioneSociale;
	}
	
	public String getLocalita(Indirizzo destinatario) {
		String localita = destinatario.getLocalita();
		String provincia = destinatario.getProvincia();
		if (provincia != null && !provincia.isEmpty() && !"XX".equalsIgnoreCase(provincia))
			localita += " (" + provincia + ")";
		return localita;
	}
	
	public String getToolTipDestinatario(Indirizzo destinatario) {
		String ragioneSociale = destinatario.getRagioneSociale();
		String indirizzo = destinatario.getIndirizzo();
		String localita = destinatario.getLocalita();
		String provincia = destinatario.getProvincia();
		if (provincia.equals("XX"))
			provincia = " " + destinatario.getNazione();
		else
			provincia = " (" + provincia +")";
		String testo = ragioneSociale + "\r\n" + indirizzo + "\r\n" + localita + provincia;
		return testo;
	}
	
	private String getTotale(SpedizioneModel oggetto) {
		Calcolo calcolo = oggetto.getCalcolo(idListino);
		double totale = calcolo != null ? calcolo.getTotale() : 0;
		String testo = Decorator.getEuroValue(totale);
		return testo;
	}
	
	private String getTooltipTotale(SpedizioneModel oggetto) {
		String testo = "";
		Calcolo calcolo = oggetto.getCalcolo(idListino);
		if (calcolo != null) {
			for (VoceCalcolata voce : calcolo.getVoci()) {
				String nome = voce.getNomeVoce();
				String costo = Decorator.getEuroValue(voce.getCosto());
				testo += nome + ": " + costo + "\r\n";
			}
		}
		return testo;
	}
	
	public int getCodiceColoreScritte(SpedizioneModel oggetto) {
		return oggetto.getColoreScrittaTabella();
	}

}
