package it.ltc.logica.common.controller.fatturazione.documento;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.trasporti.ControllerGiacenze;
import it.ltc.logica.database.model.centrale.fatturazione.VoceFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.database.model.centrale.trasporti.Giacenza;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.utilities.excel.FileExcel;

public class GiacenzeExcel extends RealizzatoreDocumentoDiFatturazioneTrasporti {
	
	private final ControllerGiacenze controllerGiacenze;
	
	public GiacenzeExcel() {		
		controllerGiacenze = ControllerGiacenze.getInstance();
	}

	@Override
	public void elaboraDocumento() {
		String nomeFile = getNomeFile(filePath, ".xls");
		FileExcel workbook = FileExcel.getFileExcel(nomeFile);
		String codice = controllerCommesse.getCommessa(documento.getIdCommessa()).getNome();
		boolean successo = false;
		if (workbook != null && !voci.isEmpty()) {
			workbook.creaFoglio(codice);
			//Titolo - riga 1
			workbook.aggiungiTitolo(codice, 0, 0, codice);
			//Creo una mappa per i totali di colonna
			HashMap<Integer, Double> totaliColonna = new HashMap<Integer, Double>();
			//Sotto titoli - riga 2
			int colonnaSottoTitoli = 0;
			workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Data Apertura");
			colonnaSottoTitoli += 1;
			workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Data Chiusura");
			colonnaSottoTitoli += 1;
			workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Durata");
			colonnaSottoTitoli += 1;
			workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Riferimento");
			colonnaSottoTitoli += 1;
			workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Destinatario");
			colonnaSottoTitoli += 1;
			workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Localita'");
			colonnaSottoTitoli += 1;
			workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Provincia");
			colonnaSottoTitoli += 1;
			workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Colli");
			colonnaSottoTitoli += 1;
			workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Pezzi");
			colonnaSottoTitoli += 1;
			workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Peso");
			colonnaSottoTitoli += 1;
			workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Volume");
			colonnaSottoTitoli += 1;
			workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Totale");
			totaliColonna.put(colonnaSottoTitoli, 0.0);
			int indiceTotale = colonnaSottoTitoli;
//			colonnaSottoTitoli += 1;
//			workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Nolo");
//			totaliColonna.put(colonnaSottoTitoli, 0.0);
//			int indiceNolo = colonnaSottoTitoli;
			//Recupero le Spedizioni - righe successive
			//Creo una mappa di voci raggruppate per spedizione
			List<Giacenza> giacenze = new LinkedList<>();
			HashMap<Integer, List<VoceFattura>> mappaVoci = new HashMap<Integer, List<VoceFattura>>();
			HashMap<Integer, Spedizione> spedizioni = new HashMap<>();
			HashMap<Integer, Integer> colonneVoci = new HashMap<Integer, Integer>();
			for (VoceFattura voce : voci) {
				//Raggruppamento per giacenza
				Integer idGiacenza = voce.getIdRiferimento();
				if (!mappaVoci.containsKey(idGiacenza)) {
					Giacenza giacenza = controllerGiacenze.getGiacenza(idGiacenza);
					giacenze.add(giacenza);
					LinkedList<VoceFattura> nuovaLista = new LinkedList<VoceFattura>();
					mappaVoci.put(idGiacenza, nuovaLista);
					//Infine recupero la spedizione collegata, se necessario
					if (!spedizioni.containsKey(giacenza.getIdSpedizione())) {
						Spedizione spedizione = controllerSpedizioni.getSpedizione(giacenza.getIdSpedizione());
						spedizioni.put(spedizione.getId(), spedizione);
					}
				}
				List<VoceFattura> listaVoci = mappaVoci.get(idGiacenza);
				listaVoci.add(voce);
				//Raggruppamento per voce di listino
				if (!colonneVoci.containsKey(voce.getIdVoce()) && !isNoloBase(voce)) {
					colonnaSottoTitoli += 1;
					VoceDiListino voceDiListino = controllerListiniClienti.getVoceDiListino(voce.getIdVoce());
					String nomeVoce = voceDiListino != null ? voceDiListino.getNome() : "N/A";
					workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, nomeVoce);
					colonneVoci.put(voce.getIdVoce(), colonnaSottoTitoli);
					totaliColonna.put(colonnaSottoTitoli, 0.0);
				}
			}
			//Ordino le spedizioni per data
			giacenze.sort(null);
			int riga = 1;
			for (Giacenza giacenza : giacenze) {
				Spedizione spedizione = spedizioni.get(giacenza.getIdSpedizione());
				Indirizzo destinazione = controllerIndirizzi.getIndirizzo(spedizione.getIndirizzoDestinazione());
				riga += 1;
				workbook.aggiungiTesto(codice, 0, riga, semplice.format(giacenza.getDataApertura()));
				workbook.aggiungiTesto(codice, 1, riga, semplice.format(giacenza.getDataChiusura()));
				workbook.aggiungiInteger(codice, 2, riga, giacenza.calcolaDurata());
				workbook.aggiungiTesto(codice, 3, riga, spedizione.getRiferimentoCliente());
				workbook.aggiungiTesto(codice, 4, riga, destinazione.getRagioneSociale());
				workbook.aggiungiTesto(codice, 5, riga, destinazione.getLocalita());
				workbook.aggiungiTesto(codice, 6, riga, destinazione.getProvincia());
				workbook.aggiungiInteger(codice, 7, riga, spedizione.getColli());
				workbook.aggiungiInteger(codice, 8, riga, spedizione.getPezzi());
				workbook.aggiungiDouble(codice, 9, riga, spedizione.getPeso());
				workbook.aggiungiDouble(codice, 10, riga, spedizione.getVolume());
				List<VoceFattura> voci = mappaVoci.get(giacenza.getId());
				//Aggiusto il totale di colonna per i totali
				Double totaleSpedizione = getTotale(voci);
				Double totaleTotali = totaliColonna.get(indiceTotale);
				totaleTotali += totaleSpedizione;
				totaliColonna.put(indiceTotale, totaleTotali);
				workbook.aggiungiValutaTreCifre(codice, indiceTotale, riga, totaleSpedizione);
				//Aggiusto il totale di colonna per il nolo
//				Double totaleNoloSpedizione = getCostoNoloBase(voci);
//				Double totaleNolo = totaliColonna.get(indiceNolo);
//				totaleNolo += totaleNoloSpedizione;
//				totaliColonna.put(indiceNolo, totaleNolo);
//				workbook.aggiungiValutaTreCifre(codice, indiceNolo, riga, totaleNoloSpedizione);
				//Aggiusto i totali per tutte le voci
				for (VoceFattura voce : voci) {
					Integer colonna = colonneVoci.get(voce.getIdVoce());
					if (colonna != null) {
						workbook.aggiungiValutaTreCifre(codice, colonna, riga, voce.getImporto());
						Double totale = totaliColonna.get(colonna);
						totale += voce.getImporto();
						totaliColonna.put(colonna, totale);
					}
				}
			}
			//Per ogni elemento in totaliColonna controllo il valore: se è 0 elimino la colonna, altrimenti scrivo il totale in fondo.
			riga += 1;
			for (Integer colonna : totaliColonna.keySet()) {
				double totale = totaliColonna.get(colonna);
				if (totale > 0) {
					workbook.aggiungiValutaTreCifre(codice, colonna, riga, totale);
				} else {
					//In teoria questa feature non serve perchè se una voce è a 0 euro non viene salvata.
					//workbook.eliminaColonna(codice, colonna);
				}
			}
			successo = workbook.salvaEChiudi();
		}
		if (successo) {
			System.out.println("Esportazione completata: è stato generato il file: " + nomeFile);
		} else {
			System.out.println("Errore nella esportazione non \u00E8 stato possibile esportare i risultati.");
		}
	}

}
