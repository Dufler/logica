package it.ltc.logica.trasporti.gui.elements.spedizione;

import java.util.Date;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.trasporti.ControllerContrassegni;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreSpedizioni extends Etichettatore<Spedizione> {
	
	private final ControllerIndirizzi controllerIndirizzi;
	private final ControllerContrassegni controllerContrassegni;
	
	public EtichettatoreSpedizioni() {
		controllerIndirizzi = ControllerIndirizzi.getInstance();
		controllerContrassegni = ControllerContrassegni.getInstance();
	}

	@Override
	public String getTesto(Spedizione oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getData(oggetto); break;
			case 1 : testo = getDestinatario(oggetto); break;
			case 2 : testo = oggetto.getRiferimentoCliente(); break;
			case 3 : testo = getColli(oggetto); break;
			case 4 : testo = getPezzi(oggetto); break;
			case 5 : testo = getContrassegno(oggetto); break;
			case 6 : testo = oggetto.getGiacenza() ? "Giacenza" : ""; break;
			case 7 : testo = oggetto.getInRitardo() ? "Ritardo" : ""; break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(Spedizione oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getData(oggetto); break;
			case 1 : testo = getToolTipDestinatario(oggetto); break;
			case 2 : testo = oggetto.getRiferimentoCliente(); break;
			case 3 : testo = getColli(oggetto); break;
			case 4 : testo = getPezzi(oggetto); break;
			case 5 : testo = getToolTipContrassegno(oggetto); break;
			case 6 : testo = oggetto.getGiacenza() ? "In Giacenza" : ""; break;
			case 7 : testo = oggetto.getInRitardo() ? "In Ritardo!" : ""; break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public Image getIcona(Spedizione oggetto, int colonna) {
		Image image = null;
		if (colonna == 0 && !areDatiCompleti(oggetto)) {
			image = Immagine.RED_MARK_12X20.getImage();
		}
		return image;
	}
	
	private boolean areDatiCompleti(Spedizione spedizione) {
		boolean pezzi = spedizione.getPezzi() > 0;
		boolean colli = spedizione.getColli() > 0;
		boolean peso = spedizione.getPeso() != null && spedizione.getPeso() > 0;
		boolean volume = spedizione.getVolume() != null && spedizione.getVolume() > 0;
		return pezzi && colli && peso && volume;
	}
	
//	@Override
//	public Image getIcona(Spedizione oggetto, int colonna) {
//		Image image = null;
//		if (colonna == 0) {
//			image = Decorator.combineImages(areDatiCompleti(oggetto));
//		}
//		return image;
//	}
//	
//	private List<Immagine> areDatiCompleti(Spedizione spedizione) {
//		List<Immagine> immagini = new ArrayList<Immagine>();
//		if (spedizione.getPezzi() == null || spedizione.getPezzi() < 1)
//			immagini.add(Immagine.FRECCIABLUSU_16X16);
//		if (spedizione.getColli() == null || spedizione.getColli() < 1)
//			immagini.add(Immagine.FRECCIAGIALLAGIU_16X16);
//		if (spedizione.getPeso() == null || spedizione.getPeso() <= 0)
//			immagini.add(Immagine.FRECCIAROSSAGIU_16X16);
//		if (spedizione.getVolume() == null || spedizione.getVolume() <= 0)
//			immagini.add(Immagine.FRECCIAVERDESU_16X16);
//		return immagini;
//	}
	
	private String getData(Spedizione spedizione) {
		String testo;
		Date data = spedizione.getDataPartenza();
		if (data != null)
			testo = sdf.format(data);
		else
			testo = "Non ancora partita.";
		return testo;
	}
	
	public String getDestinatario(Spedizione spedizione) {
		//Integer idDestinatario = spedizione.getIndirizzoDestinazione();
		//Indirizzo destinatario = controllerIndirizzi.getIndirizzo(idDestinatario);
		String ragioneSociale = spedizione.getRagioneSocialeDestinatario();//destinatario.getRagioneSociale();
		return ragioneSociale;
	}
	
	public String getToolTipDestinatario(Spedizione spedizione) {
		Integer idDestinatario = spedizione.getIndirizzoDestinazione();
		Indirizzo destinatario = controllerIndirizzi.getIndirizzo(idDestinatario);
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
	
	public String getColli(Spedizione spedizione) {
		String testo = Integer.toString(spedizione.getColli());
		return testo;
	}
	
	public String getPezzi(Spedizione spedizione) {
		String testo = Integer.toString(spedizione.getPezzi());
		return testo;
	}
	
	public String getContrassegno(Spedizione spedizione) {
		String contrassegno;
		if (spedizione.getContrassegno()) {
			Contrassegno c = controllerContrassegni.getContrassegno(spedizione.getId());
			if (c != null)
				contrassegno = Decorator.getEuroValue(c.getValore());
			else
				contrassegno = "???";
		} else {
			contrassegno = "";
		}
		return contrassegno;
	}
	
	public String getToolTipContrassegno(Spedizione spedizione) {
		String contrassegno;
		if (spedizione.getContrassegno()) {
			Contrassegno c = controllerContrassegni.getContrassegno(spedizione.getId());
			if (c != null)
				contrassegno = Decorator.getEuroValue(c.getValore());
			else
				contrassegno = "La spedizione \u00E8 in contrassegno ma non \u00E8 stato definito un valore.";
		} else {
			contrassegno = "-";
		}
		return contrassegno;
	}

}
