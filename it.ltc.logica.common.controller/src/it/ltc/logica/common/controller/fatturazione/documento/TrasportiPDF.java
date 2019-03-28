package it.ltc.logica.common.controller.fatturazione.documento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.database.model.centrale.Cliente;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.PreferenzeFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.VoceFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.utilities.pdf.DocumentoPDF;
import it.ltc.logica.utilities.pdf.PaginaPDF;
import it.ltc.logica.utilities.pdf.tabelle.ColonnaPDF;
import it.ltc.logica.utilities.pdf.tabelle.PDFTableGenerator;
import it.ltc.logica.utilities.pdf.tabelle.PreferenzeTabellaPDF;

public class TrasportiPDF extends RealizzatoreDocumentoDiFatturazioneTrasporti {
	
	private final static String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private final HashMap<String, Integer> mappaColonneBase;
	
	private int indiceColonnaTotale;
	private int indiceColonnaNolo;
	
	public TrasportiPDF() {		
		mappaColonneBase = new LinkedHashMap<>();
	}
	
	@Override
	public void setDocumento(DocumentoFattura documento) {
		this.documento = documento;
		setupPreferenzeLayoutAllegato();
	}
	
	@Override
	public void elaboraDocumento() {
		PreferenzeTabellaPDF preferenze = new PreferenzeTabellaPDF();
		List<ColonnaPDF> columnNames = getBaseColumns();
		//Creo una mappa per i totali di colonna
		HashMap<Integer, Double> totaliColonna = new HashMap<Integer, Double>();
		indiceColonnaTotale = columnNames.size() - 1;
		indiceColonnaNolo = columnNames.size();
		totaliColonna.put(indiceColonnaNolo, 0.0);
		totaliColonna.put(indiceColonnaTotale, 0.0);
		//Creo una mappa di voci raggruppate per spedizione
		List<Spedizione> spedizioni = new LinkedList<Spedizione>();
		List<String[]> listContent = new ArrayList<>();
		HashMap<Integer, List<VoceFattura>> mappaVoci = new HashMap<Integer, List<VoceFattura>>();
		HashMap<Integer, Integer> colonneVoci = new HashMap<Integer, Integer>();
		HashMap<String, String> legendaVoci = new HashMap<String, String>();
		int colonnaSottoTitoli = columnNames.size();
		for (VoceFattura voce : voci) {
			//Raggruppamento per spedizione
			Integer idSpedizione = voce.getIdRiferimento();
			if (!mappaVoci.containsKey(idSpedizione)) {
				Spedizione spedizione = controllerSpedizioni.getSpedizione(idSpedizione);
				spedizioni.add(spedizione);
				LinkedList<VoceFattura> nuovaLista = new LinkedList<VoceFattura>();
				mappaVoci.put(idSpedizione, nuovaLista);
			}
			List<VoceFattura> listaVoci = mappaVoci.get(idSpedizione);
			listaVoci.add(voce);
			//Raggruppamento per voce di listino
			if (!colonneVoci.containsKey(voce.getIdVoce()) && !isNoloBase(voce)) {
				colonnaSottoTitoli += 1;
				VoceDiListino voceDiListino = controllerListiniClienti.getVoceDiListino(voce.getIdVoce()); 
				String nomeColonna = voceDiListino != null ? voceDiListino.getNome() : "N/A";
				String legendaColonna = "" + alfabeto.charAt(legendaVoci.size());
				legendaVoci.put(nomeColonna, legendaColonna);
				float larghezzaColonna = preferenze.calculateTextWidth(nomeColonna);
				if (larghezzaColonna > preferenze.getMaxColumnWidth())
					larghezzaColonna = preferenze.getMaxColumnWidth();
				columnNames.add(new ColonnaPDF(legendaColonna, larghezzaColonna));
				colonneVoci.put(voce.getIdVoce(), colonnaSottoTitoli);
				totaliColonna.put(colonnaSottoTitoli, 0.0);
				//colonnaSottoTitoli += 1;
			}
		}
		//Ordino le spedizioni per data
		spedizioni.sort(null);
		
		for (Spedizione spedizione : spedizioni) {
			Integer idSpedizione = spedizione.getId();
			Indirizzo destinazione = controllerIndirizzi.getIndirizzo(spedizione.getIndirizzoDestinazione());
			
			//Preparo un array di stringhe della stessa dimensione delle colonne
			String[] row = new String[columnNames.size()];
			//Parto dalla prima e vado via via riempendo i valori ma solo per le colonne visibili
			int index = 0;
			//Data
			int larghezzaData = mappaColonneBase.get("Data");
			if (larghezzaData > 0) {
				row[index] = semplice.format(spedizione.getDataPartenza());
				index += 1;
			}
			//Riferimento
			int larghezzaRiferimento = mappaColonneBase.get("Riferimento");
			if (larghezzaRiferimento > 0) {
				row[index] = spedizione.getRiferimentoCliente();
				index += 1;
			}
			//Destinatario
			int larghezzaDestinatario = mappaColonneBase.get("Destinatario");
			if (larghezzaDestinatario > 0) {
				row[index] = destinazione.getRagioneSociale();
				index += 1;
			}
			//Località
			int larghezzaLocalita = mappaColonneBase.get("Località");
			if (larghezzaLocalita > 0) {
				row[index] = destinazione.getLocalita();
				index += 1;
			}
			//Provincia
			int larghezzaProvincia = mappaColonneBase.get("Provincia");
			if (larghezzaProvincia > 0) {
				row[index] = destinazione.getProvincia();
				index += 1;
			}
			//Colli
			int larghezzaColli = mappaColonneBase.get("Colli");
			if (larghezzaColli > 0) {
				row[index] = Integer.toString(spedizione.getColli());
				index += 1;
			}
			//Pezzi
			int larghezzaPezzi = mappaColonneBase.get("Pezzi");
			if (larghezzaPezzi > 0) {
				row[index] = Integer.toString(spedizione.getPezzi());
				index += 1;
			}
			//Peso
			int larghezzaPeso = mappaColonneBase.get("Peso");
			if (larghezzaPeso > 0) {
				row[index] = formatNumeri.format(spedizione.getPeso());
				index += 1;
			}
			//Volume
			int larghezzaVolume = mappaColonneBase.get("Volume");
			if (larghezzaVolume > 0) {
				row[index] = formatNumeri.format(spedizione.getVolume());
				index += 1;
			}
			//Contrassegno
			int larghezzaContrassegno = mappaColonneBase.get("Contrassegno");
			if (larghezzaContrassegno > 0) {
				if (spedizione.getContrassegno()) {
					Contrassegno contrassegno = controllerContrassegni.getContrassegno(idSpedizione);
					row[index] = contrassegno != null ? formatEuro.format(contrassegno.getValore()) : "";
				}
				index += 1;
			}
			List<VoceFattura> voci = mappaVoci.get(idSpedizione);
			//Aggiusto il totale di colonna per i totali
			Double totaleSpedizione = getTotale(voci);
			Double totaleTotali = totaliColonna.get(indiceColonnaTotale);
			totaleTotali += totaleSpedizione;
			totaliColonna.put(indiceColonnaTotale, totaleTotali);
			//Totale
			int larghezzaTotale = mappaColonneBase.get("Totale");
			if (larghezzaTotale > 0) {
				row[index] = formatEuro.format(totaleSpedizione);
				index += 1;
			}
			//Aggiusto il totale di colonna per il nolo
			Double totaleNoloSpedizione = getCostoNoloBase(voci);
			Double totaleNolo = totaliColonna.get(indiceColonnaNolo);
			totaleNolo += totaleNoloSpedizione;
			totaliColonna.put(indiceColonnaNolo, totaleNolo);
			//Nolo
			int larghezzaNolo = mappaColonneBase.get("Nolo");
			if (larghezzaNolo > 0) {
				row[index] = formatEuro.format(totaleNoloSpedizione);
				index += 1;
			}			
			
			//Aggiusto i totali per tutte le voci
			for (VoceFattura voce : voci) {
				Integer colonna = colonneVoci.get(voce.getIdVoce());
				if (colonna != null) {
					row[colonna - 1] = formatEuro.format(voce.getImporto());
					Double totale = totaliColonna.get(colonna);
					totale += voce.getImporto();
					totaliColonna.put(colonna, totale);
				}
			}
			listContent.add(row);
		}
		
		//Aggiungo i totali di colonna
		String[] row = new String[columnNames.size()];
		for (Integer colonna : totaliColonna.keySet()) {
			Double totale = totaliColonna.get(colonna);
			if (totale > 0) {
				row[colonna - 1] = formatEuro.format(totale);
			}
		}
		listContent.add(row);
		
		//Genero il documento
		try {
			String nomeFile = getNomeFile(filePath, ".pdf");
			DocumentoPDF documento = new DocumentoPDF();
			if (!bozza)	//Se non è una bozza aggiungo la prima pagina.
				aggiungiPrimaPagina(documento, colonneVoci, totaliColonna);
			PDFTableGenerator pdfTable = new PDFTableGenerator();
			pdfTable.generatePDF(documento, columnNames, listContent, preferenze);
			aggiungiPaginaLegenda(documento, legendaVoci);
			documento.stampa(nomeFile);
		} catch (IOException e) {
			//TODO - notifica l'utente
			e.printStackTrace();
		}
		
	}
	
	private PaginaPDF aggiungiPaginaLegenda(DocumentoPDF documento, HashMap<String, String> legendaVoci) {
		PaginaLegendaFatturazionePDF legenda = new PaginaLegendaFatturazionePDF(documento, legendaVoci);
		return legenda;
	}

	private PaginaPDF aggiungiPrimaPagina(DocumentoPDF pdf, HashMap<Integer, Integer> colonneVoci, HashMap<Integer, Double> totaliColonna) {
		//Recupero i dati generali
		Commessa commessa = controllerCommesse.getCommessa(documento.getIdCommessa());
		Cliente cliente = controllerClienti.getCliente(commessa.getIdCliente());
		Indirizzo indirizzo = controllerIndirizzi.getIndirizzo(cliente.getIndirizzo());
		double totaleFattura = totaliColonna.get(indiceColonnaTotale);
		//Recupero le voci di fatturazione
		List<TotaleVoceFatturazionePDF> voci = new LinkedList<>();
		for (Integer colonna : totaliColonna.keySet()) {
			//Salto la voce relativa al totale.
			if (colonna == indiceColonnaTotale)
				continue;
			Double totaleColonna = totaliColonna.get(colonna);
			if (totaleColonna > 0) {
				//Se è la colonna del nolo inserisco la descrizione in maniera fissa.
				String testoDescrizioneColonna = colonna == indiceColonnaNolo ? "Nolo" : getDescrizioneVoce(colonna, colonneVoci);
				String codiceVoce = ""; //TODO - Per il momento è fissa.
				int quantita = 1; //TODO - Per il momento è fissa.
				TotaleVoceFatturazionePDF voce = new TotaleVoceFatturazionePDF(codiceVoce, testoDescrizioneColonna, quantita, totaleColonna, documento.getIva());
				voci.add(voce);
			}
		}
		//Creo la pagina
		PaginaIntestazioneFatturazionePDF pagina = new PaginaIntestazioneFatturazionePDF(pdf, documento, cliente, indirizzo, totaleFattura, voci);
		return pagina;
	}
	
	private String getDescrizioneVoce(int indiceColonna, HashMap<Integer, Integer> colonneVoci) {
		String descrizione = "";
		for (int idVoce : colonneVoci.keySet()) {
			int colonna = colonneVoci.get(idVoce);
			if (colonna == indiceColonna) {
				VoceDiListino voceDiListino = controllerListiniClienti.getVoceDiListino(idVoce);
				descrizione = voceDiListino.getNome();
				break;
			}
		}
		return descrizione;
	}
	
	private void setupPreferenzeLayoutAllegato() {
		//Carico le preferenze
		PreferenzeFatturazione preferenze = controllerPreferenze.getPreferenzePerCommessaSuAmbito(documento.getIdCommessa(), documento.getIdAmbito());
		String layout = preferenze.getLayout();
		String[] values = layout.split("\\|");
		for (String value : values) {
			try {
				String[] keyValue = value.split("=");
				String name = keyValue[0];
				int width = Integer.parseInt(keyValue[1]);
				mappaColonneBase.put(name, width);
			} catch (Exception e) {}
		}
	}
	
	private List<ColonnaPDF> getBaseColumns() {
		List<ColonnaPDF> columnNames = new ArrayList<>();
		for (String value : mappaColonneBase.keySet()) {
			int width = mappaColonneBase.get(value);
			//Inserisco la colonna solo se la larghezza indicata è maggiore di 0.
			if (width > 0)
				columnNames.add(new ColonnaPDF(value, width));
		}
		return columnNames;
	}

}
