package it.ltc.logica.trasporti.gui.fatturazione.elements.nonfatturabili;

import java.util.Date;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCheckBoxSpedizione extends Etichettatore<Spedizione> {
	
	private final ControllerIndirizzi controllerIndirizzi;
	
	public EtichettatoreCheckBoxSpedizione() {
		controllerIndirizzi = ControllerIndirizzi.getInstance();
	}

	@Override
	public String getTesto(Spedizione oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getData(oggetto); break;
			case 1 : testo = getDestinatario(oggetto); break;
			case 2 : testo = oggetto.getRiferimentoCliente(); break;
			case 3 : testo = oggetto.getFatturazione().toString(); break;
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
			case 3 : testo = oggetto.getFatturazione().toString(); break;
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

}
