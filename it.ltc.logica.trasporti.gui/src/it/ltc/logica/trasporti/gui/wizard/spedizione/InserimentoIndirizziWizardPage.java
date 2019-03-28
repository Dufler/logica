package it.ltc.logica.trasporti.gui.wizard.spedizione;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.common.controller.trasporti.ControllerProvince;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.common.composite.CompositeIndirizzo;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.gui.elements.indirizzo.DialogSelezioneIndirizzo;

public class InserimentoIndirizziWizardPage extends PaginaWizard {
	
	private static final String titolo = "Inserisci una nuova spedizione";
	private static final String descrizione = "Inserisci l'indirizzo del mittente e del destinatario.";
	
	private final Spedizione spedizione;
	private final Indirizzo mittente;
	private final Indirizzo destinatario;
	
	private CompositeIndirizzo compositeMittente;
	private CompositeIndirizzo compositeDestinatario;
	
	private Button btnInserisciIlMittente;
	private Button btnInserisciIlDestinatario;
	
	private final ControllerIndirizzi controller;

	public InserimentoIndirizziWizardPage(Spedizione spedizione, Indirizzo mittente, Indirizzo destinatario) {
		super(titolo, descrizione);
		controller = ControllerIndirizzi.getInstance();
		
		this.spedizione = spedizione;
		this.mittente = mittente;
		this.destinatario = destinatario;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		btnInserisciIlMittente = new Button(container, SWT.NONE);
		btnInserisciIlMittente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inserisciMittente();
			}
		});
		btnInserisciIlMittente.setText("Inserisci il mittente dalla rubrica");
		
		compositeMittente = new CompositeIndirizzo(this, container, CompositeIndirizzo.TipoIndirizzo.MITTENTE);
		compositeMittente.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeMittente.enableElement(false);
		
		btnInserisciIlDestinatario = new Button(container, SWT.NONE);
		btnInserisciIlDestinatario.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inserisciDestinatario();
			}
		});
		btnInserisciIlDestinatario.setText("Inserisci il destinatario dalla rubrica");
		
		compositeDestinatario = new CompositeIndirizzo(this, container, CompositeIndirizzo.TipoIndirizzo.DESTINATARIO);
		compositeDestinatario.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeDestinatario.enableElement(false);
	}
	
	private Indirizzo selezionaIndirizzo() {
		DialogSelezioneIndirizzo dialog = new DialogSelezioneIndirizzo();
		Indirizzo indirizzo = dialog.apri();
		return indirizzo;
	}

	private void inserisciMittente() {
		Indirizzo mittente = selezionaIndirizzo();
		if (mittente != null) {
			//Imposto l'ID e tutto il resto
			this.mittente.setId(mittente.getId());
			this.mittente.setRagioneSociale(mittente.getRagioneSociale());
			this.mittente.setIndirizzo(mittente.getIndirizzo());
			this.mittente.setLocalita(mittente.getLocalita());
			this.mittente.setCap(mittente.getCap());
			this.mittente.setProvincia(mittente.getProvincia());
			this.mittente.setNazione(mittente.getNazione());
			this.mittente.setConsegnaAlPiano(mittente.getConsegnaAlPiano());
			this.mittente.setConsegnaGdo(mittente.getConsegnaGdo());
			this.mittente.setConsegnaPrivato(mittente.getConsegnaPrivato());
			this.mittente.setConsegnaAppuntamento(mittente.getConsegnaAppuntamento());
			//Aggiorno la UI
			compositeMittente.setRagioneSociale(mittente.getRagioneSociale());
			compositeMittente.setIndirizzo(mittente.getIndirizzo());
			compositeMittente.setLocalita(mittente.getLocalita());
			compositeMittente.setCap(mittente.getCap());
			compositeMittente.setNazione(ControllerNazioni.getInstance().getNazione(mittente.getNazione()));
			compositeMittente.setProvincia(ControllerProvince.getInstance().getProvincia(mittente.getProvincia()));
			compositeMittente.setPiano(mittente.getConsegnaAlPiano());
			compositeMittente.setGDO(mittente.getConsegnaGdo());
			compositeMittente.setPrivato(mittente.getConsegnaPrivato());
			compositeMittente.setAppuntamento(mittente.getConsegnaAppuntamento());
		}
	}
	
	private void inserisciDestinatario() {
		Indirizzo destinatario = selezionaIndirizzo();
		if (destinatario != null) {
			//Imposto l'ID e tutto il resto
			this.destinatario.setId(destinatario.getId());
			this.destinatario.setRagioneSociale(destinatario.getRagioneSociale());
			this.destinatario.setIndirizzo(destinatario.getIndirizzo());
			this.destinatario.setLocalita(destinatario.getLocalita());
			this.destinatario.setCap(destinatario.getCap());
			this.destinatario.setNazione(destinatario.getNazione());
			this.destinatario.setProvincia(destinatario.getProvincia());
			this.destinatario.setConsegnaAlPiano(destinatario.getConsegnaAlPiano());
			this.destinatario.setConsegnaGdo(destinatario.getConsegnaGdo());
			this.destinatario.setConsegnaPrivato(destinatario.getConsegnaPrivato());
			this.destinatario.setConsegnaAppuntamento(destinatario.getConsegnaAppuntamento());
			//Aggiorno la UI
			compositeDestinatario.setRagioneSociale(destinatario.getRagioneSociale());
			compositeDestinatario.setIndirizzo(destinatario.getIndirizzo());
			compositeDestinatario.setLocalita(destinatario.getLocalita());
			compositeDestinatario.setCap(destinatario.getCap());
			compositeDestinatario.setNazione(ControllerNazioni.getInstance().getNazione(destinatario.getNazione()));
			compositeDestinatario.setProvincia(ControllerProvince.getInstance().getProvincia(destinatario.getProvincia()));
			compositeDestinatario.setPiano(destinatario.getConsegnaAlPiano());
			compositeDestinatario.setGDO(destinatario.getConsegnaGdo());
			compositeDestinatario.setPrivato(destinatario.getConsegnaPrivato());
			compositeDestinatario.setAppuntamento(destinatario.getConsegnaAppuntamento());
		}
	}

	@Override
	public void copyDataToModel() {
		//Spedizione, è necessario solo questa. Il resto è stato fatto prima.
		spedizione.setTipo(controller.getTipoSpedizioneDaDestinazione(destinatario.getNazione()));
		spedizione.setRagioneSocialeDestinatario(destinatario.getRagioneSociale());
		spedizione.setIndirizzoDestinazione(destinatario.getId());
		spedizione.setIndirizzoPartenza(mittente.getId());
	}

}
