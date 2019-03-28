package it.ltc.logica.trasporti.controller.preventivi;

import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.common.calcolo.algoritmi.Calcolo.Tipo;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.trasporti.calcolo.algoritmi.CalcolatoreSimulazioni;
import it.ltc.logica.trasporti.calcolo.algoritmi.CalcolatoreTrasporti;
import it.ltc.logica.trasporti.calcolo.algoritmi.CalcolatoreTrasportiCorrieri;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

public abstract class PreventivoController {

	// Listini selezionati
	protected final List<ListinoCorriere> listiniCorriere;
	protected final List<ListinoCommessa> listiniCliente;
	protected final List<ListinoSimulazione> listiniSimulazione;
	
	// Calcolatori
	protected final CalcolatoreTrasporti calcolatoreTrasporti;
	protected final CalcolatoreTrasportiCorrieri calcolatoreCorriere;
	protected final CalcolatoreSimulazioni calcolatoreSimulazione;

	protected PreventivoController() {
		listiniCorriere = new LinkedList<ListinoCorriere>();
		listiniCliente = new LinkedList<ListinoCommessa>();
		listiniSimulazione = new LinkedList<ListinoSimulazione>();
		calcolatoreTrasporti = CalcolatoreTrasporti.getInstance();
		calcolatoreCorriere = CalcolatoreTrasportiCorrieri.getInstance();
		calcolatoreSimulazione = CalcolatoreSimulazioni.getInstance();
	}

	public void setListiniCorriere(List<ListinoCorriere> listiniSelezionati) {
		listiniCorriere.clear();
		listiniCorriere.addAll(listiniSelezionati);
	}

	public List<ListinoCorriere> getListiniCorriere() {
		return listiniCorriere;
	}

	public void setListiniCliente(List<ListinoCommessa> listiniSelezionati) {
		listiniCliente.clear();
		listiniCliente.addAll(listiniSelezionati);
	}

	public List<ListinoCommessa> getListiniCliente() {
		return listiniCliente;
	}

	public void setListiniSimulazione(List<ListinoSimulazione> listiniSelezionati) {
		listiniSimulazione.clear();
		listiniSimulazione.addAll(listiniSelezionati);
	}

	public List<ListinoSimulazione> getListiniSimulazione() {
		return listiniSimulazione;
	}
	
	protected void calcola(SpedizioneModel spedizione) {
		//Calcolo dei costi
		for (ListinoCorriere listino : listiniCorriere) {
			calcolatoreCorriere.calcolaCosto(spedizione, listino);
		}
		//Calcolo dei ricavi
		for (ListinoCommessa listino : listiniCliente) {
			calcolatoreTrasporti.calcolaRicavo(spedizione, listino);
		}
		//Calcolo i listini di simulazione
		for (ListinoSimulazione listino : listiniSimulazione) {
			calcolatoreSimulazione.calcolaSimulazione(spedizione, listino);
		}
		trovaCalcoliMigliori(spedizione);
	}
	
	protected void calcola(List<SpedizioneModel> spedizioni) {
		for (SpedizioneModel spedizione : spedizioni) {
			//Calcolo dei costi
			for (ListinoCorriere listino : listiniCorriere) {
				calcolatoreCorriere.calcolaCosto(spedizione, listino);
			}
			//Calcolo dei ricavi
			for (ListinoCommessa listino : listiniCliente) {
				calcolatoreTrasporti.calcolaRicavo(spedizione, listino);
			}
			//Calcolo i listini di simulazione
			for (ListinoSimulazione listino : listiniSimulazione) {
				calcolatoreSimulazione.calcolaSimulazione(spedizione, listino);
			}
			trovaCalcoliMigliori(spedizione);
		}
	}
	
	protected void trovaCalcoliMigliori(SpedizioneModel model) {
		Calcolo costoMinore = null;
		Calcolo ricavoMaggiore = null;
		//Vado a eseguire una ricerca solo se ho almeno 2 calcoli
		if (model.getCalcoli().size() > 1)
		for (Calcolo calcolo : model.getCalcoli()) {
			if (calcolo.getTipo() == Tipo.COSTO) {
				if (costoMinore == null || costoMinore.getTotale() > calcolo.getTotale()) {
					costoMinore = calcolo;
				}
			} else {
				if (ricavoMaggiore == null || ricavoMaggiore.getTotale() < calcolo.getTotale()) {
					ricavoMaggiore = calcolo;
				}
			}
		}
		//Segno i migliori trovati, se trovati
		if (costoMinore != null)
			costoMinore.setCostoMinore(true);
		if (ricavoMaggiore != null)
			ricavoMaggiore.setRicavoMaggiore(true);
	}

}
