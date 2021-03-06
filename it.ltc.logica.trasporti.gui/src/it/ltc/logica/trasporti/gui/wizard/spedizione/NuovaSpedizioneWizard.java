package it.ltc.logica.trasporti.gui.wizard.spedizione;

import java.util.Date;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.common.controller.sistema.ControllerDocumenti;
import it.ltc.logica.common.controller.trasporti.ControllerContrassegni;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;
import it.ltc.logica.database.model.centrale.documenti.Documento;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class NuovaSpedizioneWizard extends WizardConRisultati {
	
	private static final String title = "Inserisci una nuova spedizione";
	
	private final ControllerSpedizioni controllerSpedizioni;
	private final ControllerContrassegni controllerContrassegni;
	private final ControllerDocumenti controllerDocumenti;
	
	private final Documento documento;
	private final Spedizione spedizione;
	private final Contrassegno contrassegno;
	private final Indirizzo mittente;
	private final Indirizzo destinatario;
	
	private final SelezioneClienteCorriereWizardPage selezionePage;
	private final InserimentoDatiSpedizioneWizardPage datiPage;
	private final InserimentoIndirizziWizardPage indirizziPage;
	private final RiepilogoNuovaSpedizioneWizardPage riepilogoPage;

	public NuovaSpedizioneWizard() {
		super(title, false);
		
		controllerSpedizioni = ControllerSpedizioni.getInstance();
		controllerContrassegni = ControllerContrassegni.getInstance();
		controllerDocumenti = ControllerDocumenti.getInstance();
		
		documento = creaBaseDocumento();
		spedizione = creaBaseSpedizione();
		contrassegno = new Contrassegno();
		mittente = new Indirizzo();
		destinatario = new Indirizzo();
		
		selezionePage = new SelezioneClienteCorriereWizardPage(documento, spedizione);
		datiPage = new InserimentoDatiSpedizioneWizardPage(spedizione, contrassegno);
		indirizziPage = new InserimentoIndirizziWizardPage(spedizione, mittente, destinatario);
		riepilogoPage = new RiepilogoNuovaSpedizioneWizardPage(spedizione, contrassegno, mittente, destinatario);
	}
	
	/**
	 * Crea un documento con i valori di default già impostati.
	 * @return un oggetto <code>Documento</code>.
	 */
	private Documento creaBaseDocumento() {
		Documento documento = new Documento();
		documento.setDataCreazione(new Date());
		documento.setTipo(Documento.TipoDocumento.ORDINE);
		return documento;
	}
	
	/**
	 * Crea una spedizione con i valori di default già impostati.
	 * @return un oggetto <code>Spedizione</code>.
	 */
	private Spedizione creaBaseSpedizione() {
		Spedizione spedizione = new Spedizione();
		spedizione.setDatiCompleti(true);
		spedizione.setInRitardo(false);
		spedizione.setParticolarita(false);
		spedizione.setGiacenza(false);
		spedizione.setAssicurazione(false);
		spedizione.setStato("IMN");
		return spedizione;
	}

	@Override
	public void addPages() {
		addPage(selezionePage);
		addPage(datiPage);
		addPage(indirizziPage);
		addPage(riepilogoPage);
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextPage = null;
		if (selezionePage.equals(page)) {
			nextPage = datiPage;
		} else if (datiPage.equals(page)) {
			nextPage = indirizziPage;
		} else if (indirizziPage.equals(page)) {
			nextPage = riepilogoPage;
		}
		return nextPage;
	}

	@Override
	public boolean finisci() {
		//Condizioni iniziali da verificare
		boolean inserimentoOrdine = false;
		boolean inserimentoSpedizione = false;
		boolean inserimentoContrassegno = true;
		//Inserisco l'ordine
		inserimentoOrdine = controllerDocumenti.inserisci(documento);
		if (inserimentoOrdine) {
			spedizione.setIdDocumento(documento.getId());
			inserimentoSpedizione = controllerSpedizioni.inserisci(spedizione);
			if (inserimentoSpedizione && spedizione.getContrassegno() && contrassegno != null) {
				contrassegno.setIdSpedizione(spedizione.getId());
				inserimentoContrassegno = controllerContrassegni.inserisci(contrassegno);
			}
		}
		return inserimentoOrdine && inserimentoSpedizione && inserimentoContrassegno;
	}

}
