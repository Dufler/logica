package it.ltc.logica.amministrazione.gui.controllodigestione.elements;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCDGSpedizioni extends Etichettatore<Spedizione> {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public String getTesto(Spedizione oggetto, int colonna) {
		String testo;
		switch (colonna) {
		case 0 : testo = getRiferimento(oggetto); break;
		case 1 : testo = getData(oggetto); break;
		case 2 : testo = getCommessa(oggetto); break;
		case 3 : testo = getCorriere(oggetto); break;
		case 4 : testo = getCosto(oggetto); break;
		case 5 : testo = getRicavo(oggetto); break;
		default : testo = "";
		}
		return testo;
	}
	
	private String getCorriere(Spedizione oggetto) {
		Corriere corriere = ControllerCorrieri.getInstance().getCorriere(oggetto.getIdCorriere());
		String testo = corriere != null ? corriere.getNome() : "-";
		return testo;
	}

	private String getRicavo(Spedizione oggetto) {
		Double ricavo = oggetto.getRicavo();
		String testo = ricavo != null ? Decorator.getEuroValue(ricavo) : "-";
		return testo;
	}

	private String getCosto(Spedizione oggetto) {
		Double costo = oggetto.getCosto();
		String testo = costo != null ? Decorator.getEuroValue(costo) : "-";
		return testo;
	}

	private String getCommessa(Spedizione oggetto) {
		Commessa commessa = ControllerCommesse.getInstance().getCommessa(oggetto.getIdCommessa());
		String testo = commessa != null ? commessa.getNome() : "-";
		return testo;
	}

	private String getData(Spedizione oggetto) {
		Date data = oggetto.getDataPartenza();
		String testo = data != null ? sdf.format(data) : "-";
		return testo;
	}

	private String getRiferimento(Spedizione oggetto) {
		String testo = oggetto.getRiferimentoCliente() != null ? oggetto.getRiferimentoCliente() : "-";
		return testo;
	}

	@Override
	public String getTestoTooltip(Spedizione oggetto, int colonna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getIcona(Spedizione oggetto, int colonna) {
		// TODO Auto-generated method stub
		return null;
	}

}
