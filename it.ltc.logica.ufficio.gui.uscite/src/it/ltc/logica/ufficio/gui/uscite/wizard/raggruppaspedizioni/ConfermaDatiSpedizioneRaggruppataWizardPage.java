package it.ltc.logica.ufficio.gui.uscite.wizard.raggruppaspedizioni;

import java.util.List;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.ufficio.gui.uscite.elements.spedizione.TabellaDatiSpedizioneSemplice;

public class ConfermaDatiSpedizioneRaggruppataWizardPage extends PaginaWizardRisultati {
	
	private final static String title = "Generazione Dati Raggruppamento Spedizioni";
	private final static String description = "Verifica i dati sul raggruppamento spedizioni.";
	
	private TabellaDatiSpedizioneSemplice tabella;
	
	private final Commessa commessa;
	private final List<OrdineTestata> ordiniDaSpedire;
	private final DatiSpedizione dati;
	private final ControllerOrdini controller;

	protected ConfermaDatiSpedizioneRaggruppataWizardPage(Commessa commessa, List<OrdineTestata> ordiniDaSpedire, DatiSpedizione datiRaggruppamento) {
		super(title, description, false);
		this.commessa = commessa;
		this.ordiniDaSpedire = ordiniDaSpedire;
		this.dati = datiRaggruppamento;
		this.controller = new ControllerOrdini(commessa);
	}

	@Override
	public void mostraRisultato() {
		//Cerco gli attuali dati spedizione per gli ordini da raggruppare.
		for (OrdineTestata ordine : ordiniDaSpedire) {
			DatiSpedizione datiSpedizione = controller.trovaDatiSpedizione(ordine);
			//Imposto i valori base (che dovrebbero essere comuni)
			dati.setCorriere(datiSpedizione.getCorriere());
			dati.setCodiceCorriere(datiSpedizione.getCodiceCorriere());
			//vado a sommare tutto il possibile
			dati.setDataConsegna(datiSpedizione.getDataConsegna());
			dati.setDataDocumento(datiSpedizione.getDataDocumento());
			dati.setNote(datiSpedizione.getNote());//*
			dati.getOrdini().add(ordine);
			dati.setRiferimento(datiSpedizione.getRiferimento());
			dati.setRiferimentoDocumento(datiSpedizione.getRiferimentoDocumento());
			dati.setServizioCorriere(datiSpedizione.getServizioCorriere());
			dati.setTipoDocumento(datiSpedizione.getTipoDocumento());
			if (datiSpedizione.getValoreContrassegno() != null) {
				Double giàPresente = dati.getValoreContrassegno() != null ? dati.getValoreContrassegno() : 0;
				dati.setValoreContrassegno(giàPresente + datiSpedizione.getValoreContrassegno());
				dati.setTipoContrassegno(datiSpedizione.getTipoContrassegno());
				dati.setValutaContrassegno(datiSpedizione.getValutaContrassegno());
			}
			if (datiSpedizione.getValoreDoganale() != null) {
				Double giàPresente = dati.getValoreDoganale() != null ? dati.getValoreDoganale() : 0;
				dati.setValoreDoganale(giàPresente + datiSpedizione.getValoreDoganale());
			}			
			dati.setVolume(dati.getVolume() + datiSpedizione.getVolume());
			dati.setPezzi(dati.getPezzi() + datiSpedizione.getPezzi());
			dati.setPeso(dati.getPeso() + datiSpedizione.getPeso());
			dati.setColli(dati.getColli() + datiSpedizione.getColli());
			
		}
		DatiSpedizione[] array = new DatiSpedizione[1];
		array[0] = dati;
		tabella.setElementi(array);
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		tabella = new TabellaDatiSpedizioneSemplice(container, commessa);
	}

}
