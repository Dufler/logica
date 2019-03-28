package it.ltc.logica.trasporti.gui.elements.spedizionecheckbox;

import java.util.Date;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.trasporti.ControllerContrassegni;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCheckBoxSpedizione extends Etichettatore<Spedizione> {
	
	private final ControllerIndirizzi controllerIndirizzi;
	private final ControllerContrassegni controllerContrassegni;
	
	public EtichettatoreCheckBoxSpedizione() {
		controllerIndirizzi = ControllerIndirizzi.getInstance();
		controllerContrassegni = ControllerContrassegni.getInstance();
	}

	@Override
	public String getTesto(Spedizione oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getData(oggetto); break;
			case 1 : testo = getDestinatario(oggetto); break;
			case 2 : testo = getColli(oggetto); break;
			case 3 : testo = getPezzi(oggetto); break;
			case 4 : testo = getContrassegno(oggetto); break;
			case 5 : testo = oggetto.getGiacenza() ? "In Giacenza" : ""; break;
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
			case 2 : testo = getColli(oggetto); break;
			case 3 : testo = getPezzi(oggetto); break;
			case 4 : testo = getContrassegno(oggetto); break;
			case 5 : testo = oggetto.getGiacenza() ? "In Giacenza" : ""; break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public Image getIcona(Spedizione oggetto, int colonna) {
		return null;
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
	
	public String getDestinatario(Spedizione spedizione) {
		String ragioneSociale = spedizione.getRagioneSocialeDestinatario();
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
			Contrassegno cs = controllerContrassegni.getContrassegno(spedizione.getId());
			contrassegno = Decorator.getEuroValue(cs.getValore());
		} else {
			contrassegno = "";
		}
		return contrassegno;
	}

}
