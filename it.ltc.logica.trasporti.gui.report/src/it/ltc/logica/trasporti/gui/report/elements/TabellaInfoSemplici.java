package it.ltc.logica.trasporti.gui.report.elements;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.database.dao.locali.CriteriSelezioneSpedizioni;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;
import it.ltc.logica.trasporti.controller.ReportElement;

public class TabellaInfoSemplici extends Tabella<ReportElement> {
	
	private CriteriSelezioneSpedizioni criteriSelezionati;

	public TabellaInfoSemplici(Composite parent) {
		super(parent);
	}
	
	public void setCriteri(CriteriSelezioneSpedizioni criteri) {
		criteriSelezionati = criteri;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Elemento", 100, 1);
		aggiungiColonna("Valore", 100, 2);
	}

	@Override
	public void aggiornaContenuto() {
		//DO NOTHING!		
	}

	@Override
	protected Ordinatore<ReportElement> creaOrdinatore() {
		return new OrdinatoreInfoSemplici();
	}

	@Override
	protected void aggiungiListener() {
		//DO NOTHING!		
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		//DO NOTHING!		
	}
	
	@Override
	protected String getTableText() {
		String testoCriteri = getTestoCriteri();
		String testoTabella = super.getTableText();
		return testoCriteri + testoTabella;
	}
	
	public void copiaContenuto() {
		copiaSelezione();
		DialogMessaggio.openInformation("Contenuto copiato", "Il contenuto della tabella \u00E8 stato salvato negli appunti ed \u00E8 pronto per essere copiato dentro excel.");
	}

	private String getTestoCriteri() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		StringBuilder sb = new StringBuilder();
		sb.append("Data generazione report: ");
		sb.append(TAB);
		sb.append(sdf.format(new Date()));
		sb.append(NEW_LINE);
		sb.append("Criteri selezionati");
		sb.append(NEW_LINE);
		if (criteriSelezionati != null) {			
			//Data
			sb.append("Data selezionata: ");
			sb.append(TAB);
			Date dataA = criteriSelezionati.getDataA();
			Date dataDa = criteriSelezionati.getDataDa();
			if (dataA == null && dataDa == null) {
				sb.append("indifferente");
			} else {				
				sb.append(dataDa != null ? " dal " + sdf.format(dataDa) : "");
				sb.append(dataA != null ? " al " + sdf.format(dataA) : "");
			}
			sb.append(NEW_LINE);
			//Commessa
			Commessa commessa = criteriSelezionati.getCommessa() != null ? ControllerCommesse.getInstance().getCommessa(criteriSelezionati.getCommessa()) : null;
			sb.append("Commessa selezionata: ");
			sb.append(TAB);
			sb.append(commessa != null ? commessa.getNome() : "Nessuna");
			sb.append(NEW_LINE);
			//Corriere
			Corriere corriere = criteriSelezionati.getCorriere() != null ? ControllerCorrieri.getInstance().getCorriere(criteriSelezionati.getCorriere()) : null;
			sb.append("Corriere selezionato: ");
			sb.append(TAB);
			sb.append(corriere != null ? corriere.getNome() : "Nessuno");
			sb.append(NEW_LINE);
			//Codice cliente
			sb.append("Codice cliente selezionato: ");
			sb.append(TAB);
			sb.append(criteriSelezionati.getCodiceCliente() != null ? criteriSelezionati.getCodiceCliente() : "Nessuno");
			sb.append(NEW_LINE);
			//Contrassegno
			sb.append("Contrassegno selezionato: ");
			sb.append(TAB);
			sb.append(criteriSelezionati.getContrassegno() != null ? criteriSelezionati.getContrassegno() ? "Si" : "No" : "Indifferente");
			sb.append(NEW_LINE);
			//Colli
			sb.append("Numero colli selezionati: ");
			sb.append(TAB);
			Integer colliDa = criteriSelezionati.getMinimoColli();
			Integer colliA = criteriSelezionati.getMassimoColli();
			if (colliDa == null && colliA == null) {
				sb.append("indifferente");
			} else {
				sb.append(colliDa != null ? " da " + colliDa : "");
				sb.append(colliA != null ? " fino a " + colliA : "");
			}
			sb.append(NEW_LINE);
			//Pezzi
			sb.append("Numero pezzi selezionati: ");
			sb.append(TAB);
			Integer pezziDa = criteriSelezionati.getMinimoPezzi();
			Integer pezziA = criteriSelezionati.getMassimoPezzi();
			if (pezziDa == null && pezziA == null) {
				sb.append("indifferente");
			} else {
				sb.append(pezziDa != null ? " da " + pezziDa : "");
				sb.append(pezziA != null ? " fino a " + pezziA : "");
			}
			sb.append(NEW_LINE);
			//Peso
			sb.append("Peso selezionato: ");
			sb.append(TAB);
			Double pesoDa = criteriSelezionati.getMinimoPeso();
			Double pesoA = criteriSelezionati.getMassimoPeso();
			if (pesoDa == null && pesoA == null) {
				sb.append("indifferente");
			} else {
				sb.append(pesoDa != null ? " da " + pesoDa + " Kg" : "");
				sb.append(pesoA != null ? " fino a " + pesoA + " Kg": "");
			}
			sb.append(NEW_LINE);
			//Volume
			sb.append("Volume selezionato: ");
			sb.append(TAB);
			Double volumeDa = criteriSelezionati.getMinimoVolume();
			Double volumeA = criteriSelezionati.getMassimoVolume();
			if (volumeDa == null && volumeA == null) {
				sb.append("indifferente");
			} else {
				sb.append(volumeDa != null ? " da " + volumeDa + " Mc" : "");
				sb.append(volumeA != null ? " fino a " + volumeA + " Mc": "");
			}
			sb.append(NEW_LINE);
		} else {
			sb.append("nessun criterio selezionato");
		}
		sb.append(NEW_LINE);
		return sb.toString();
	}

	@Override
	protected Etichettatore<ReportElement> creaEtichettatore() {
		return new EtichettatoreInfoSemplici();
	}

	@Override
	protected ModificatoreValoriCelle<ReportElement> creaModificatore() {
		return null;
	}

}
