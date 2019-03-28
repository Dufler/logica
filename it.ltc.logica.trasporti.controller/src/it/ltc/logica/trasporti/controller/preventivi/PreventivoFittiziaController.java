package it.ltc.logica.trasporti.controller.preventivi;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Assicurazione;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.database.model.centrale.trasporti.ParticolaritaSpedizione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.utilities.chart.DatiGraficoSemplice;

public class PreventivoFittiziaController extends PreventivoController {

	private static PreventivoFittiziaController instance;

	private int pezzi;
	private int colli;
	private double peso;
	private double volume;
	private boolean inContrassegno;
	private double valoreContrassegno;
	private int colliDa;
	private int colliA;
	private List<Indirizzo> indirizzi;

	private PreventivoFittiziaController() {}

	public static PreventivoFittiziaController getInstance() {
		if (instance == null) {
			instance = new PreventivoFittiziaController();
		}
		return instance;
	}

	public int getPezzi() {
		return pezzi;
	}

	public void setPezzi(int pezzi) {
		this.pezzi = pezzi;
	}

	public int getColli() {
		return colli;
	}

	public void setColli(int colli) {
		this.colli = colli;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public int getColliDa() {
		return colliDa;
	}

	public void setColliDa(int colliDa) {
		this.colliDa = colliDa;
	}

	public int getColliA() {
		return colliA;
	}

	public void setColliA(int colliA) {
		this.colliA = colliA;
	}

	public boolean isContrassegno() {
		return inContrassegno;
	}

	public void setContrassegno(boolean contrassegno) {
		this.inContrassegno = contrassegno;
	}

	public double getValoreContrassegno() {
		return valoreContrassegno;
	}

	public void setValoreContrassegno(double valoreContrassegno) {
		this.valoreContrassegno = valoreContrassegno;
	}
	
	private boolean checkListiniSelezionati() {
		int corriere = listiniCorriere.size();
		int cliente = listiniCliente.size();
		int simulazione = listiniSimulazione.size();
		boolean check = (corriere + cliente + simulazione) > 0;
		return check;
	}

	public List<SpedizioneModel> calcolaSpedizioniFittizie() {
		List<SpedizioneModel> preventivi = new LinkedList<SpedizioneModel>();
		//Controllo se ho almeno un listino su cui eseguire i calcoli.
		if (checkListiniSelezionati()) {
			//Aggiungo la base su cui eseguire i calcoli
			aggiungiBaseModel(preventivi);
			// Calcolo i preventivi per ogni spedizione
			calcola(preventivi);
		}
		return preventivi;
	}
	
	public DatiGraficoSemplice calcolaSpedizioniFittizieSuColli() {
		DatiGraficoSemplice dati = new DatiGraficoSemplice("Costi e Ricavi per le spedizioni", "Numero di Colli", "Costo");
		if (checkListiniSelezionati()) {
			//Aggiungo i modelli base su cui eseguire la simulazione
			Indirizzo destinazione = indirizzi.get(0);
			List<SpedizioneModel> preventivi = aggiungiSpedizioniSuColliModel(destinazione);
			//Eseguo il calcolo dei preventivi
			calcola(preventivi);
			//Aggiungo i risultati trovati ai dati per il grafico
			for (SpedizioneModel spedizione : preventivi) {
				int colli = spedizione.getSpedizione().getColli();
				//Dati sui costi
				for (Calcolo c : spedizione.getPreventiviCosto()) {
					dati.aggiungiValore(c.getTotale(), "Costo: " + c.getNome(), colli + " Collo/i");
				}
				//Dati sui ricavi
				for (Calcolo c : spedizione.getPreventiviRicavo()) {
					dati.aggiungiValore(c.getTotale(), "Ricavo: " + c.getNome(), colli + " Collo/i");
				}
			}
		}
		return dati;
	}
	
	public List<SpedizioneModel> calcolaSpedizioniFittizieSuColliDue() {
		List<SpedizioneModel> preventivi = new LinkedList<SpedizioneModel>();
		if (checkListiniSelezionati()) {
			//Aggiungo i modelli base su cui eseguire la simulazione
			for (Indirizzo destinazione : indirizzi)
				preventivi.addAll(aggiungiSpedizioniSuColliModel(destinazione));
			//Eseguo il calcolo dei preventivi
			calcola(preventivi);
		}
		return preventivi;
	}
	
	private List<SpedizioneModel> aggiungiSpedizioniSuColliModel(Indirizzo destinazione) {
		List<SpedizioneModel> preventivi = new LinkedList<SpedizioneModel>();
		// Indirizzo di partenza, fisso per la sede di Perugia
		Indirizzo partenza = getIndirizzoDiPartenza();
//		//Indirizzo di destinazione, selezionato dall'utente
//		Indirizzo destinazione = indirizzi.get(0);
		for (int index = colliDa; index <= colliA; index++) {
			Spedizione spedizione = new Spedizione();
			spedizione.setDataPartenza(new Date());
			spedizione.setId(-1);
			spedizione.setIdCommessa(-1);
			spedizione.setIdCorriere(-1);
			spedizione.setIdDocumento(-1);
			spedizione.setPezzi(pezzi * index);
			spedizione.setColli(index);
			spedizione.setPeso(peso * index);
			spedizione.setVolume(volume * index);
			spedizione.setServizio("DEF");
			spedizione.setValoreMerceDichiarato(0.0);
			//Preparo le condizioni di contorno: assicurazione, contrassegno e particolarita'
			spedizione.setGiacenza(false);
			spedizione.setAssicurazione(false);
			Assicurazione assicurazione = null;
			spedizione.setContrassegno(false);
			Contrassegno contrassegno = null;
			if (inContrassegno) {
				contrassegno = new Contrassegno();
				contrassegno.setAnnullato(false);
				contrassegno.setIdSpedizione(-1);
				contrassegno.setTipo("BM");
				contrassegno.setValore(valoreContrassegno);
				contrassegno.setValuta("EUR");
			}
			spedizione.setParticolarita(false);
			ParticolaritaSpedizione particolarita = null;
			SpedizioneModel model = new SpedizioneModel(spedizione, contrassegno, assicurazione, destinazione, partenza, null, particolarita, null);
			model.setLabel(destinazione.getRagioneSociale() + " " + index + " collo/i");
			preventivi.add(model);
		}
		return preventivi;
	}

	private void aggiungiBaseModel(List<SpedizioneModel> preventivi) {
		// Preparo le informazioni base per la spedizione in base ai criteri inseriti dall'utente
		Spedizione spedizione = new Spedizione();
		spedizione.setDataPartenza(new Date());
		spedizione.setId(-1);
		spedizione.setIdCommessa(-1);
		spedizione.setIdCorriere(-1);
		spedizione.setIdDocumento(-1);
		spedizione.setPezzi(pezzi);
		spedizione.setColli(colli);
		spedizione.setPeso(peso * colli);
		spedizione.setVolume(volume * colli);
		spedizione.setServizio("DEF");
		spedizione.setValoreMerceDichiarato(0.0);
		//Preparo le condizioni di contorno: assicurazione, contrassegno e particolarita'
		spedizione.setGiacenza(false);
		spedizione.setAssicurazione(false);
		Assicurazione assicurazione = null;
		spedizione.setContrassegno(false);
		Contrassegno contrassegno = null;
		if (inContrassegno) {
			contrassegno = new Contrassegno();
			contrassegno.setAnnullato(false);
			contrassegno.setIdSpedizione(-1);
			contrassegno.setTipo("BM");
			contrassegno.setValore(valoreContrassegno);
			contrassegno.setValuta("EUR");
		}
		spedizione.setParticolarita(false);
		ParticolaritaSpedizione particolarita = null;
		// Indirizzo di partenza, fisso per la sede di Perugia
		Indirizzo partenza = getIndirizzoDiPartenza();
		for (Indirizzo indirizzo : indirizzi) {
			SpedizioneModel model = new SpedizioneModel(spedizione, contrassegno, assicurazione, indirizzo, partenza, null, particolarita, null);
			model.setLabel(indirizzo.getRagioneSociale());
			preventivi.add(model);
		}
	}
	
	private Indirizzo getIndirizzoDiPartenza() {
		Indirizzo perugia = new Indirizzo();
		perugia.setCap("06073");
		perugia.setConsegnaAppuntamento(false);
		perugia.setConsegnaGdo(false);
		perugia.setConsegnaAlPiano(false);
		perugia.setConsegnaPrivato(false);
		perugia.setId(-1);
		perugia.setIndirizzo("Via Firenze, 41");
		perugia.setLocalita("CORCIANO");
		perugia.setNazione("ITA");
		perugia.setProvincia("PG");
		perugia.setRagioneSociale("LTC");
		return perugia;
	}

	public List<Indirizzo> getIndirizzi() {
		return indirizzi;
	}

	public void setIndirizzi(List<Indirizzo> indirizzi) {
		this.indirizzi = indirizzi;
	}

}
