package it.ltc.logica.trasporti.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.locali.CriteriSelezioneSpedizioni;
import it.ltc.logica.common.controller.trasporti.ControllerCap;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.common.controller.trasporti.ControllerRegioni;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.indirizzi.Regione;
import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.task.DialogProgresso;
import it.ltc.logica.utilities.chart.DatiGraficoSemplice;
import it.ltc.logica.utilities.chart.DatiGraficoTorta;
import it.ltc.logica.utilities.excel.FileExcel;

public class ReportController {

	private static ReportController instance;

	private Date dataDa;
	private Date dataA;
	private Commessa commessa;
	private Corriere corriere;
	private CodiceClienteCorriere codice;
	private Boolean contrassegno;
	private Integer minimoColli;
	private Integer massimoColli;
	private Integer minimoPezzi;
	private Integer massimoPezzi;
	private Double minimoPeso;
	private Double massimoPeso;
	private Double minimoVolume;
	private Double massimoVolume;

	private int rapportoPesoVolume;

	private final ControllerIndirizzi controllerIndirizzi;

	public Date getDataDa() {
		return dataDa;
	}

	public void setDataDa(Date dataDa) {
		this.dataDa = dataDa;
	}

	public Date getDataA() {
		return dataA;
	}

	public void setDataA(Date dataA) {
		this.dataA = dataA;
	}

	public Commessa getCommessa() {
		return commessa;
	}

	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}

	public Corriere getCorriere() {
		return corriere;
	}

	public void setCorriere(Corriere corriere) {
		this.corriere = corriere;
	}

	public Boolean getContrassegno() {
		return contrassegno;
	}

	public void setContrassegno(Boolean contrassegno) {
		this.contrassegno = contrassegno;
	}

	public Integer getMinimoColli() {
		return minimoColli;
	}

	public void setMinimoColli(Integer minimoColli) {
		this.minimoColli = minimoColli;
	}

	public Integer getMassimoColli() {
		return massimoColli;
	}

	public void setMassimoColli(Integer massimoColli) {
		this.massimoColli = massimoColli;
	}

	public Integer getMinimoPezzi() {
		return minimoPezzi;
	}

	public void setMinimoPezzi(Integer minimoPezzi) {
		this.minimoPezzi = minimoPezzi;
	}

	public Integer getMassimoPezzi() {
		return massimoPezzi;
	}

	public void setMassimoPezzi(Integer massimoPezzi) {
		this.massimoPezzi = massimoPezzi;
	}

	public Double getMinimoPeso() {
		return minimoPeso;
	}

	public void setMinimoPeso(Double minimoPeso) {
		this.minimoPeso = minimoPeso;
	}

	public Double getMassimoPeso() {
		return massimoPeso;
	}

	public void setMassimoPeso(Double massimoPeso) {
		this.massimoPeso = massimoPeso;
	}

	public Double getMinimoVolume() {
		return minimoVolume;
	}

	public void setMinimoVolume(Double minimoVolume) {
		this.minimoVolume = minimoVolume;
	}

	public Double getMassimoVolume() {
		return massimoVolume;
	}

	public void setMassimoVolume(Double massimoVolume) {
		this.massimoVolume = massimoVolume;
	}

	public int getRapportoPesoVolume() {
		return rapportoPesoVolume;
	}

	public void setRapportoPesoVolume(int rapportoPesoVolume) {
		this.rapportoPesoVolume = rapportoPesoVolume;
	}

	public CodiceClienteCorriere getCodice() {
		return codice;
	}

	public void setCodice(CodiceClienteCorriere codice) {
		this.codice = codice;
	}

	public void resetFiltri() {
		dataDa = null;
		dataA = null;
		commessa = null;
		corriere = null;
		codice = null;
		contrassegno = null;
		minimoColli = null;
		massimoColli = null;
		minimoPezzi = null;
		massimoPezzi = null;
		minimoPeso = null;
		massimoPeso = null;
		minimoVolume = null;
		massimoVolume = null;
	}

	private ReportController() {
		controllerIndirizzi = ControllerIndirizzi.getInstance();
	}

	public static ReportController getInstance() {
		if (instance == null) {
			instance = new ReportController();
		}
		return instance;
	}
	
	public CriteriSelezioneSpedizioni getCriteriSelezionati() {
		CriteriSelezioneSpedizioni criteri = new CriteriSelezioneSpedizioni();
		criteri.setCommessa(commessa != null ? commessa.getId() : null);
		criteri.setCorriere(corriere != null ? corriere.getId() : null);
		criteri.setCodiceCliente(codice != null ? codice.getCodiceCliente() : null);
		criteri.setDataA(dataA);
		criteri.setDataDa(dataDa);
		criteri.setContrassegno(contrassegno);
		criteri.setMinimoColli(minimoColli);
		criteri.setMassimoColli(massimoColli);
		criteri.setMinimoPezzi(minimoPezzi);
		criteri.setMassimoPezzi(massimoPezzi);
		criteri.setMinimoPeso(minimoPeso);
		criteri.setMassimoPeso(massimoPeso);
		criteri.setMinimoVolume(minimoVolume);
		criteri.setMassimoVolume(massimoVolume);
		return criteri;
	}

	protected List<Spedizione> getSpedizioni() {	
		CriteriSelezioneSpedizioni criteri = getCriteriSelezionati();
		List<Spedizione> listaSpedizioni = ControllerSpedizioni.getInstance().selezionaSpedizioni(criteri);
		return listaSpedizioni;
	}

	public List<ReportElement> calcolaReportSemplice() {
		// Preparo la lista di elementi di report da restituire
		List<ReportElement> report = new ArrayList<ReportElement>();
		// Recupero le spedizioni che corrispondono ai criteri selezionati
		List<Spedizione> spedizioni = getSpedizioni();
		// Preparo il calcolo di tali elementi
		ProcessoCalcoloReportSemplice runnable = new ProcessoCalcoloReportSemplice("Calcolo del report", spedizioni.size() + 3, report, spedizioni);
		// Avvio il calcolo
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(runnable);
		// Restituisco il report
		return report;
	}

	public void esportaDati(String path, List<ReportElement> report) {
		String nomeFile = path + "/report.xls";
		String titoloReport = "Report";
		FileExcel workbook = FileExcel.getFileExcel(nomeFile);
		boolean successo = false;
		if (workbook != null) {
			String nomeFoglio = "Report";
			workbook.creaFoglio(nomeFoglio);
			workbook.aggiungiTitolo(nomeFoglio, 0, 0, titoloReport);
			int riga = 2;
			for (ReportElement element : report) {
				workbook.aggiungiTesto(nomeFoglio, 0, riga, element.getKey());
				workbook.aggiungiTesto(nomeFoglio, 1, riga, element.getValue());
				riga += 1;
			}
			successo = workbook.salvaEChiudi();
		}
		if (successo) {
			DialogMessaggio.openInformation("Esportazione completata", "Esportazione completata!\r\nE' stato generato il file:\r\n" + nomeFile);
		} else {
			DialogMessaggio.openError("Errore nella esportazione", "Non \u00E8 stato possibile esportare i risultati.");
		}
	}

	public DatiGraficoSemplice calcolaDatiPesoVolume() {
		// Preparo le variabili di appoggio
		int fascia_0_5 = 0;
		int fascia_5_10 = 0;
		int fascia_10_20 = 0;
		int fascia_20_30 = 0;
		int fascia_30_40 = 0;
		int fascia_40_50 = 0;
		int fascia_50_60 = 0;
		int fascia_60_70 = 0;
		int fascia_70_80 = 0;
		int fascia_80_100 = 0;
		int fascia_oltre100 = 0;
		int spedizioniSenzaDati = 0;
		// Recupero le spedizioni e calcolo
		List<Spedizione> spedizioni = getSpedizioni();
		for (Spedizione spedizione : spedizioni) {
			Double volume = spedizione.getVolume();
			Double peso = spedizione.getPeso();
			if (volume != null && peso != null) {
				volume = volume * rapportoPesoVolume;
				double valore = (volume > peso) ? volume : peso;
				if (valore < 0) {
					spedizioniSenzaDati += 1;
				} else if (valore <= 5) {
					fascia_0_5 += 1;
				} else if (valore <= 10) {
					fascia_5_10 += 1;
				} else if (valore <= 20) {
					fascia_10_20 += 1;
				} else if (valore <= 30) {
					fascia_20_30 += 1;
				} else if (valore <= 40) {
					fascia_30_40 += 1;
				} else if (valore <= 50) {
					fascia_40_50 += 1;
				} else if (valore <= 60) {
					fascia_50_60 += 1;
				} else if (valore <= 70) {
					fascia_60_70 += 1;
				} else if (valore <= 80) {
					fascia_70_80 += 1;
				} else if (valore <= 100) {
					fascia_80_100 += 1;
				} else {
					fascia_oltre100 += 1;
				}
			} else {
				spedizioniSenzaDati += 1;
			}
		}
		// Pubblico i dati calcolati
		DatiGraficoSemplice dati = new DatiGraficoSemplice("Distribuzione del rapporto peso/volume per le spedizioni", "Percentuale", "");
		String riga = "Rapporto Peso/Volume 1:" + rapportoPesoVolume + "  ";
		double percentuale = 1.0;
		dati.aggiungiValore((fascia_0_5 * percentuale) / (spedizioni.size() - spedizioniSenzaDati), riga, "0-5 Kg");
		dati.aggiungiValore((fascia_5_10 * percentuale) / (spedizioni.size() - spedizioniSenzaDati), riga, "5-10 Kg");
		dati.aggiungiValore((fascia_10_20 * percentuale) / (spedizioni.size() - spedizioniSenzaDati), riga, "10-20 Kg");
		dati.aggiungiValore((fascia_20_30 * percentuale) / (spedizioni.size() - spedizioniSenzaDati), riga, "20-30 Kg");
		dati.aggiungiValore((fascia_30_40 * percentuale) / (spedizioni.size() - spedizioniSenzaDati), riga, "30-40 Kg");
		dati.aggiungiValore((fascia_40_50 * percentuale) / (spedizioni.size() - spedizioniSenzaDati), riga, "40-50 Kg");
		dati.aggiungiValore((fascia_50_60 * percentuale) / (spedizioni.size() - spedizioniSenzaDati), riga, "50-60 Kg");
		dati.aggiungiValore((fascia_60_70 * percentuale) / (spedizioni.size() - spedizioniSenzaDati), riga, "60-70 Kg");
		dati.aggiungiValore((fascia_70_80 * percentuale) / (spedizioni.size() - spedizioniSenzaDati), riga, "70-80 Kg");
		dati.aggiungiValore((fascia_80_100 * percentuale) / (spedizioni.size() - spedizioniSenzaDati), riga, "80-100 Kg");
		dati.aggiungiValore((fascia_oltre100 * percentuale) / (spedizioni.size() - spedizioniSenzaDati), riga, "Oltre 100 Kg");
		if (spedizioniSenzaDati > 0)
			dati.aggiungiValore((spedizioniSenzaDati * percentuale) / spedizioni.size(), riga, "Senza dati");
		return dati;
	}

	public DatiGraficoSemplice calcolaDatiDistribuzioneColli() {
		// Preparo le variabili di appoggio
		// nello 0 ci sono le spedizioni senza dati sul numero di colli
		// nelle altre ci sono rispettivamente le spedizioni con quello
		// specifico numero di colli
		int[] numeroColli = new int[11];
		// Recupero le spedizioni e calcolo
		List<Spedizione> spedizioni = getSpedizioni();
		for (Spedizione spedizione : spedizioni) {
			Integer colli = spedizione.getColli();
			if (colli > 10)
				colli = 10;
			numeroColli[colli] += 1;
		}
		// Pubblico i dati calcolati
		DatiGraficoSemplice dati = new DatiGraficoSemplice("Distribuzione del numero di colli per le spedizioni", "Percentuale", "");
		String riga = "Numero di colli";
		double percentuale = 1.0;
		dati.aggiungiValore((numeroColli[1] * percentuale) / (spedizioni.size() - numeroColli[0]), riga, "1 Collo");
		dati.aggiungiValore((numeroColli[2] * percentuale) / (spedizioni.size() - numeroColli[0]), riga, "2 Colli");
		dati.aggiungiValore((numeroColli[3] * percentuale) / (spedizioni.size() - numeroColli[0]), riga, "3 Colli");
		dati.aggiungiValore((numeroColli[4] * percentuale) / (spedizioni.size() - numeroColli[0]), riga, "4 Colli");
		dati.aggiungiValore((numeroColli[5] * percentuale) / (spedizioni.size() - numeroColli[0]), riga, "5 Colli");
		dati.aggiungiValore((numeroColli[6] * percentuale) / (spedizioni.size() - numeroColli[0]), riga, "6 Colli");
		dati.aggiungiValore((numeroColli[7] * percentuale) / (spedizioni.size() - numeroColli[0]), riga, "7 Colli");
		dati.aggiungiValore((numeroColli[8] * percentuale) / (spedizioni.size() - numeroColli[0]), riga, "8 Colli");
		dati.aggiungiValore((numeroColli[9] * percentuale) / (spedizioni.size() - numeroColli[0]), riga, "9 Colli");
		dati.aggiungiValore((numeroColli[10] * percentuale) / (spedizioni.size() - numeroColli[0]), riga, "10 o pi\u00F9 Colli");
		dati.aggiungiValore((numeroColli[0] * percentuale) / (spedizioni.size() - numeroColli[0]), riga, "Senza Dati");
		return dati;
	}

	public DatiGraficoTorta calcolaDatiDistribuzioneColliPerTorta() {
		// Preparo le variabili di appoggio
		// nello 0 ci sono le spedizioni senza dati sul numero di colli
		// nelle altre ci sono rispettivamente le spedizioni con quello
		// specifico numero di colli
		int[] numeroColli = new int[11];
		// Recupero le spedizioni e calcolo
		List<Spedizione> spedizioni = getSpedizioni();
		for (Spedizione spedizione : spedizioni) {
			Integer colli = spedizione.getColli();
			if (colli > 10)
				colli = 10;
			numeroColli[colli] += 1;
		}
		// Pubblico i dati calcolati
		DatiGraficoTorta dati = new DatiGraficoTorta("Distribuzione del numero di colli per le spedizioni");
		double percentuale = 1.0;
		// for (int index = 1; index <= 10; index++)
		// dati.aggiungiValore((numeroColli[index] * percentuale) /
		// (spedizioni.size() - numeroColli[0]), riga, Integer.toString(index));
		dati.aggiungiValore("Spedizioni con 1 Collo", (numeroColli[1] * percentuale));
		dati.aggiungiValore("Spedizioni con 2 Colli", (numeroColli[2] * percentuale));
		dati.aggiungiValore("Spedizioni con 3 Colli", (numeroColli[3] * percentuale));
		dati.aggiungiValore("Spedizioni con 4 Colli", (numeroColli[4] * percentuale));
		dati.aggiungiValore("Spedizioni con 5 Colli", (numeroColli[5] * percentuale));
		dati.aggiungiValore("Spedizioni con 6 Colli", (numeroColli[6] * percentuale));
		dati.aggiungiValore("Spedizioni con 7 Colli", (numeroColli[7] * percentuale));
		dati.aggiungiValore("Spedizioni con 8 Colli", (numeroColli[8] * percentuale));
		dati.aggiungiValore("Spedizioni con 9 Colli", (numeroColli[9] * percentuale));
		dati.aggiungiValore("Spedizioni con 10 o pi\u00F9 Colli", (numeroColli[10] * percentuale));
		dati.aggiungiValore("Spedizioni senza dati", (numeroColli[0] * percentuale));
		return dati;
	}

	public DatiGraficoSemplice calcolaDatiDistribuzioneDestinazioni() {
		HashMap<String, Integer> spedizioniPerRegione = new HashMap<String, Integer>();
		List<Spedizione> spedizioni = getSpedizioni();
		for (Spedizione spedizione : spedizioni) {
			Integer numeroSpedizioni = 1; // Conto la spedizione attuale
			Integer idDestinazione = spedizione.getIndirizzoDestinazione();
			Indirizzo destinazione = controllerIndirizzi.getIndirizzo(idDestinazione);
			String cap = destinazione.getCap();
			String regione = "EST";
			// Vado a verificare se trovo la destinazione tra i CAP presenti a
			// sistema
			// Se non è presente assumo che sia diretta all'estero.
			Cap c = ControllerCap.getInstance().getInfoGeneraliCap(cap);
			if (c != null) {
				regione = c.getRegione();
			} else {
				System.out.println(cap);
			}
			// Controllo se ne avevo gia' dirette in quella regione
			Integer i = spedizioniPerRegione.get(regione);
			if (i != null) {
				numeroSpedizioni += i;
			}
			// Aggiorno il totale
			spedizioniPerRegione.put(regione, numeroSpedizioni);

		}
		DatiGraficoSemplice dati = new DatiGraficoSemplice("Distribuzione del numero di spedizioni per destinazione", "Destinazione", "Spedizioni");
		for (String regione : spedizioniPerRegione.keySet()) {
			double value = spedizioniPerRegione.get(regione) * 1.0;
			Regione r = ControllerRegioni.getInstance().getRegione(regione);
			if (r != null)
				dati.aggiungiValore(value, r.getNome(), r.getZona());
			else
				System.out.println(regione);
		}
		return dati;
	}

}
