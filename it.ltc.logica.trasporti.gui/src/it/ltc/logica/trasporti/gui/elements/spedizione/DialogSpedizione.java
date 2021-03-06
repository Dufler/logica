package it.ltc.logica.trasporti.gui.elements.spedizione;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.sistema.ControllerValute;
import it.ltc.logica.common.controller.trasporti.ControllerCodiciClienteCorriere;
import it.ltc.logica.common.controller.trasporti.ControllerContrassegni;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.common.controller.trasporti.ControllerProvince;
import it.ltc.logica.common.controller.trasporti.ControllerServizioSpedizione;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;
import it.ltc.logica.common.controller.trasporti.ControllerTipoContrassegno;
import it.ltc.logica.common.controller.trasporti.ControllerTracking;
import it.ltc.logica.database.model.centrale.Tracking;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.indirizzi.Nazione;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.Fatturazione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.TipoSpedizione;
import it.ltc.logica.gui.common.composite.CompositeContrassegno;
import it.ltc.logica.gui.common.composite.CompositeIndirizzo;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.trasporti.gui.composite.CompositeDatiOrdineDaSpedizione;
import it.ltc.logica.trasporti.gui.composite.CompositeDatiSpedizione;
import it.ltc.logica.trasporti.gui.elements.tracking.DialogTracking;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class DialogSpedizione extends DialogModel<Spedizione> {

	public static final String DEFAULT_TITLE = "Dettaglio Spedizione";
	
	private final ControllerSpedizioni controllerSpedizioni;
	private final ControllerIndirizzi controllerIndirizzi;
	private final ControllerContrassegni controllerContrassegni;
	private final ControllerTracking controllerTracking;
	
	private final boolean modifica;

	private final Spedizione spedizione;
	private final Contrassegno contrassegno;
	private final Indirizzo destinazione;
	private final Indirizzo mittente;

	private CTabFolder tabFolder;
	
	private CompositeDatiSpedizione compositeDatiSpedizione;
	private CompositeDatiOrdineDaSpedizione compositeOrdine;
	private CompositeContrassegno compositeContrassegno;
	private CompositeIndirizzo compositeDestinatario;
	private CompositeIndirizzo compositeMittente;

	public DialogSpedizione(Spedizione s) {
		super(DEFAULT_TITLE, s);
				
		controllerSpedizioni = ControllerSpedizioni.getInstance();
		controllerIndirizzi = ControllerIndirizzi.getInstance();
		controllerContrassegni = ControllerContrassegni.getInstance();
		controllerTracking = ControllerTracking.getInstance();
		
		spedizione = s;
		if (spedizione.getContrassegno())
			contrassegno = controllerContrassegni.getContrassegno(spedizione.getId());
		else
			contrassegno = null;
		destinazione = controllerIndirizzi.getIndirizzo(spedizione.getIndirizzoDestinazione());
		mittente = controllerIndirizzi.getIndirizzo(spedizione.getIndirizzoPartenza());
		
		modifica = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.TRASPORTI_SPEDIZIONI_CUD.getID());
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));

		tabFolder = new CTabFolder(container, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		CTabItem tbtmDatiSpedizione = new CTabItem(tabFolder, SWT.NONE);
		tbtmDatiSpedizione.setText("Dettagli Spedizione");

		Composite compositeSpedizione = new Composite(tabFolder, SWT.NONE);
		compositeSpedizione.setLayout(new GridLayout(1, false));		
		
		compositeDatiSpedizione = new CompositeDatiSpedizione(this, compositeSpedizione);
		compositeDatiSpedizione.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeDatiSpedizione.enableElement(modifica);
		
		tbtmDatiSpedizione.setControl(compositeSpedizione);
		
		Button btnMostraTracking = new Button(compositeSpedizione, SWT.NONE);
		btnMostraTracking.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mostraTracking();
			}
		});
		btnMostraTracking.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		btnMostraTracking.setBounds(0, 0, 75, 25);
		btnMostraTracking.setText("Mostra Tracking");
		
		if (contrassegno != null) {
			CTabItem tbtmDatiContrassegno = new CTabItem(tabFolder, SWT.NONE);
			tbtmDatiContrassegno.setText("Contrassegno");
			compositeContrassegno = new CompositeContrassegno(this, tabFolder);
			compositeContrassegno.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			compositeContrassegno.enableElement(modifica);
			tbtmDatiContrassegno.setControl(compositeContrassegno);
		}
		
		CTabItem tbtmDettagliOrdine = new CTabItem(tabFolder, SWT.NONE);
		tbtmDettagliOrdine.setText("Dettagli Ordine");
		
		compositeOrdine = new CompositeDatiOrdineDaSpedizione(this, tabFolder);
		compositeOrdine.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeOrdine.enableElement(modifica);
		addChild(compositeOrdine);
		tbtmDettagliOrdine.setControl(compositeOrdine);
		
		//Tengo traccia delle modifiche sulla commessa (Ho eliminato questo controllo per facilitare la vita a Sonia quando deve cambiare la commessa)
//		ComboBox<Commessa> comboCommessa = compositeOrdine.getComboCommessa();
//		comboCommessa.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				Commessa commessaSelezionata = comboCommessa.getSelectedValue();
//				if (commessaSelezionata != null) {
//					compositeDatiSpedizione.setCommessa(commessaSelezionata.getId());
//				}
//			}
//		});

		CTabItem tbtmDestinatario = new CTabItem(tabFolder, SWT.NONE);
		tbtmDestinatario.setText("Destinatario");

		Composite compositeDatiDestinatario = new Composite(tabFolder, SWT.NONE);
		tbtmDestinatario.setControl(compositeDatiDestinatario);
		compositeDatiDestinatario.setLayout(new GridLayout(1, false));

		compositeDestinatario = new CompositeIndirizzo(this, compositeDatiDestinatario, CompositeIndirizzo.TipoIndirizzo.DESTINATARIO);
		compositeDestinatario.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeDestinatario.enableElement(modifica);

		CTabItem tbtmMittente = new CTabItem(tabFolder, SWT.NONE);
		tbtmMittente.setText("Mittente");

		Composite compositeDatiMittente = new Composite(tabFolder, SWT.NONE);
		tbtmMittente.setControl(compositeDatiMittente);
		compositeDatiMittente.setLayout(new GridLayout(1, false));

		compositeMittente = new CompositeIndirizzo(this, compositeDatiMittente, CompositeIndirizzo.TipoIndirizzo.MITTENTE);
		compositeMittente.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeMittente.enableElement(modifica);		
	}

	@Override
	public void loadModel() {
		// Dati spedizione
		compositeDatiSpedizione.setCorriere(ControllerCorrieri.getInstance().getCorriere(spedizione.getIdCorriere()));
		compositeDatiSpedizione.setServizio(ControllerServizioSpedizione.getInstance().getServizio(spedizione.getServizio()));
		//compositeDatiSpedizione.setCommessa(spedizione.getIdCommessa());
		compositeDatiSpedizione.setCommessa(-1); //Fix per far comparire tutti i codici cliente che appartengono al corriere senza prendere in considerazione la commessa.
		compositeDatiSpedizione.setCodice(ControllerCodiciClienteCorriere.getInstance().getCodiceCliente(spedizione.getCodiceCliente()));
		String letteraDiVettura = spedizione.getLetteraDiVettura() != null ? spedizione.getLetteraDiVettura() : "";
		compositeDatiSpedizione.setLetteraDiVettura(letteraDiVettura);
		compositeDatiSpedizione.setColli(spedizione.getColli());
		compositeDatiSpedizione.setVolume(spedizione.getVolume());
		compositeDatiSpedizione.setPeso(spedizione.getPeso());
		compositeDatiSpedizione.setDataSpedizione(spedizione.getDataPartenza());
		Fatturazione fatturazione = spedizione.getFatturazione();
		compositeDatiSpedizione.setFatturazione(fatturazione);
		//Dati Ordine
		compositeOrdine.setCommessa(ControllerCommesse.getInstance().getCommessa(spedizione.getIdCommessa()));
		String riferimentoCliente = spedizione.getRiferimentoCliente() != null ? spedizione.getRiferimentoCliente() : "";
		compositeOrdine.setRiferimentoCliente(riferimentoCliente);
		String riferimentoInterno = spedizione.getRiferimentoMittente() != null ? spedizione.getRiferimentoMittente() : "";
		compositeOrdine.setRiferimentoInterno(riferimentoInterno);
		compositeOrdine.setPezzi(spedizione.getPezzi());
		String note = spedizione.getNote() != null ? spedizione.getNote() : "";
		compositeOrdine.setNote(note);
		//Contrassegno
		if (contrassegno != null) {
			compositeContrassegno.setValore(contrassegno.getValore());
			compositeContrassegno.setValuta(ControllerValute.getInstance().getValuta(contrassegno.getValuta()));
			compositeContrassegno.setTipoContrassegno(ControllerTipoContrassegno.getInstance().getTipoContrassegno(contrassegno.getTipo()));
			compositeContrassegno.setAnnullato(contrassegno.getAnnullato());
		}
		// Destinatario
		compositeDestinatario.setIndirizzo(destinazione.getIndirizzo());
		compositeDestinatario.setCap(destinazione.getCap());
		compositeDestinatario.setRagioneSociale(destinazione.getRagioneSociale());
		compositeDestinatario.setLocalita(destinazione.getLocalita());
		compositeDestinatario.setNazione(ControllerNazioni.getInstance().getNazione(destinazione.getNazione()));
		compositeDestinatario.setProvincia(ControllerProvince.getInstance().getProvincia(destinazione.getProvincia()));
		compositeDestinatario.setPiano(destinazione.getConsegnaAlPiano());
		compositeDestinatario.setPrivato(destinazione.getConsegnaPrivato());
		compositeDestinatario.setGDO(destinazione.getConsegnaGdo());
		compositeDestinatario.setAppuntamento(destinazione.getConsegnaAppuntamento());
		// Mittente
		compositeMittente.setIndirizzo(mittente.getIndirizzo());
		compositeMittente.setCap(mittente.getCap());
		compositeMittente.setRagioneSociale(mittente.getRagioneSociale());
		compositeMittente.setLocalita(mittente.getLocalita());
		compositeMittente.setNazione(ControllerNazioni.getInstance().getNazione(mittente.getNazione()));
		compositeMittente.setProvincia(ControllerProvince.getInstance().getProvincia(mittente.getProvincia()));
		compositeMittente.setPiano(mittente.getConsegnaAlPiano());
		compositeMittente.setPrivato(mittente.getConsegnaPrivato());
		compositeMittente.setGDO(mittente.getConsegnaGdo());
		compositeMittente.setAppuntamento(mittente.getConsegnaAppuntamento());
	}

	@Override
	public void copyDataToModel() {
		// Dati spedizione
		spedizione.setIdCorriere(compositeDatiSpedizione.getCorriere().getId());
		spedizione.setServizio(compositeDatiSpedizione.getServizio().getCodice());
		spedizione.setCodiceCliente(compositeDatiSpedizione.getCodice().getCodiceCliente());
		spedizione.setLetteraDiVettura(compositeDatiSpedizione.getLetteraDiVettura());
		spedizione.setColli(compositeDatiSpedizione.getColli());
		spedizione.setVolume(compositeDatiSpedizione.getVolume());
		spedizione.setPeso(compositeDatiSpedizione.getPeso());
		spedizione.setDataPartenza(compositeDatiSpedizione.getDataSpedizione());
		spedizione.setFatturazione(compositeDatiSpedizione.getFatturazione());
		//Tipo spedizione in base alla nazione di destinazione
		TipoSpedizione tipo;
		Nazione n = compositeDestinatario.getNazione();
		if (n.getCodiceIsoTre().equals("ITA")) {
			tipo = TipoSpedizione.ITALIA;
		} else {
			tipo = n.getUe() ? TipoSpedizione.UE : TipoSpedizione.EXTRA_UE;
		}
		spedizione.setTipo(tipo);
		//Dati ordine
		int idCommessa = compositeOrdine.getCommessa().getId();
		String riferimentoCliente = compositeOrdine.getRiferimentoCliente();
		String riferimentoMittente = compositeOrdine.getRiferimentoInterno();
		String note = compositeOrdine.getNote().isEmpty() ? null : compositeOrdine.getNote();
		spedizione.setIdCommessa(idCommessa);
		spedizione.setRiferimentoCliente(riferimentoCliente);
		spedizione.setRiferimentoMittente(riferimentoMittente);
		spedizione.setPezzi(compositeOrdine.getPezzi());
		spedizione.setNote(note);
		//Controllo la completezza dei dati
		Integer colli = spedizione.getColli();
		Integer pezzi = spedizione.getPezzi();
		Double volume = spedizione.getVolume();
		Double peso = spedizione.getPeso();
		if (colli != null && colli > 0 && pezzi != null && pezzi > 0 && volume != null && volume > 0 && peso != null && peso > 0) {
			spedizione.setDatiCompleti(true);
		}
		//Contrassegno
		if (contrassegno != null) {
			contrassegno.setValore(compositeContrassegno.getValore());
			contrassegno.setValuta(compositeContrassegno.getValuta().getCodice());
			contrassegno.setTipo(compositeContrassegno.getTipoContrassegno().getCodice());
			contrassegno.setAnnullato(compositeContrassegno.getAnnullato());
		}
		// Destinatario
		destinazione.setIndirizzo(compositeDestinatario.getIndirizzo());
		destinazione.setCap(compositeDestinatario.getCap());
		destinazione.setRagioneSociale(compositeDestinatario.getRagioneSociale());
		destinazione.setLocalita(compositeDestinatario.getLocalita());
		destinazione.setNazione(compositeDestinatario.getNazione().getCodiceIsoTre());
		destinazione.setProvincia(compositeDestinatario.getProvincia().getSigla());
		destinazione.setConsegnaAlPiano(compositeDestinatario.getPiano());
		destinazione.setConsegnaPrivato(compositeDestinatario.getPrivato());
		destinazione.setConsegnaGdo(compositeDestinatario.getGDO());
		destinazione.setConsegnaAppuntamento(compositeDestinatario.getAppuntamento());
		// Mittente
		mittente.setIndirizzo(compositeMittente.getIndirizzo());
		mittente.setCap(compositeMittente.getCap());
		mittente.setRagioneSociale(compositeMittente.getRagioneSociale());
		mittente.setLocalita(compositeMittente.getLocalita());
		mittente.setNazione(compositeMittente.getNazione().getCodiceIsoTre());
		mittente.setProvincia(compositeMittente.getProvincia().getSigla());
		mittente.setConsegnaAlPiano(compositeMittente.getPiano());
		mittente.setConsegnaPrivato(compositeMittente.getPrivato());
		mittente.setConsegnaGdo(compositeMittente.getGDO());
		mittente.setConsegnaAppuntamento(compositeMittente.getAppuntamento());
	}

	@Override
	public boolean updateModel() {
		boolean updateDestinatario = controllerIndirizzi.aggiorna(destinazione);
		boolean updateMittente = controllerIndirizzi.aggiorna(mittente);
		boolean updateSpedizione = controllerSpedizioni.aggiorna(spedizione);
		boolean updateContrassegno = true;
		if (contrassegno != null)
			updateContrassegno = controllerContrassegni.aggiorna(contrassegno);
		boolean update = updateSpedizione && updateContrassegno && updateDestinatario && updateMittente;
		return update;
	}

	@Override
	public boolean isDirty() {
		boolean modificaSpedizione = compositeDatiSpedizione.isDirty();
		boolean modificaOrdine = compositeOrdine.isDirty();
		boolean modificaContrassegno = false;
		if (compositeContrassegno != null)
			modificaContrassegno = compositeContrassegno.isDirty();
		boolean modificaDestinatario = compositeDestinatario.isDirty();
		boolean modificaMittente = compositeMittente.isDirty();
		return modificaSpedizione || modificaOrdine || modificaContrassegno || modificaDestinatario || modificaMittente;
	}

	@Override
	public Spedizione getValore() {
		return spedizione;
	}

	@Override
	public Spedizione createNewModel() {
		Spedizione spedizione = new Spedizione();
		return spedizione;
	}

	@Override
	public boolean insertModel() {
		//DO NOTHING!	
		return false;
	}
	
	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

	@Override
	public List<String> validateModel() {
		return null;
	}
	
	private void mostraTracking() {
		List<Tracking> lista = controllerTracking.getTracking(spedizione.getId());
		DialogTracking dialog = new DialogTracking(spedizione.getRiferimentoCliente(), lista);
		dialog.open();
	}
}
