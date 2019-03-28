package it.ltc.logica.trasporti.gui.elements.spedizionesimulazionecheckbox;

import java.util.Date;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.trasporti.ControllerContrassegniSimulazione;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizziSimulazione;
import it.ltc.logica.database.model.centrale.indirizzi.IndirizzoSimulazione;
import it.ltc.logica.database.model.centrale.trasporti.ContrassegnoSimulazione;
import it.ltc.logica.database.model.centrale.trasporti.SpedizioneSimulazione;
import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCheckBoxSpedizioneSimulazione extends Etichettatore<SpedizioneSimulazione> {
	
	private final ControllerIndirizziSimulazione controllerIndirizzi;
	private final ControllerContrassegniSimulazione controllerContrassegni;
	
	public EtichettatoreCheckBoxSpedizioneSimulazione() {
		controllerIndirizzi = ControllerIndirizziSimulazione.getInstance();
		controllerContrassegni = ControllerContrassegniSimulazione.getInstance();
	}

	@Override
	public String getTesto(SpedizioneSimulazione oggetto, int colonna) {
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
	public String getTestoTooltip(SpedizioneSimulazione oggetto, int colonna) {
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
	public Image getIcona(SpedizioneSimulazione oggetto, int colonna) {
		return null;
	}
	
	private String getData(SpedizioneSimulazione spedizione) {
		String testo;
		Date data = spedizione.getDataPartenza();
		if (data != null)
			testo = sdf.format(data);
		else
			testo = "Non ancora partita.";
		return testo;
	}
	
	public String getDestinatario(SpedizioneSimulazione spedizione) {
		String ragioneSociale = spedizione.getRagioneSocialeDestinatario();
		return ragioneSociale;
	}
	
	public String getToolTipDestinatario(SpedizioneSimulazione spedizione) {
		int idDestinatario = spedizione.getIndirizzoDestinazione();
		IndirizzoSimulazione destinatario = controllerIndirizzi.getIndirizzo(idDestinatario);
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
	
	public String getColli(SpedizioneSimulazione spedizione) {
		String testo = Integer.toString(spedizione.getColli());
		return testo;
	}
	
	public String getPezzi(SpedizioneSimulazione spedizione) {
		String testo = Integer.toString(spedizione.getPezzi());
		return testo;
	}
	
	public String getContrassegno(SpedizioneSimulazione spedizione) {
		String contrassegno;
		if (spedizione.getContrassegno()) {
			ContrassegnoSimulazione cs = controllerContrassegni.getContrassegno(spedizione.getId());
			contrassegno = Decorator.getEuroValue(cs.getValore());
		} else {
			contrassegno = "";
		}
		return contrassegno;
	}

}
