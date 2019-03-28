package it.ltc.logica.trasporti.gui.spedizioni.wizard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.common.composite.CompositeContrassegno;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.trasporti.gui.composite.CompositeDatiSpedizione;

public class InserimentoDatiSpedizioneWizardPage extends PaginaWizardRisultati {
	
	private static final String titolo = "Inserisci una nuova spedizione";
	private static final String descrizione = "Inserisci i dati sulla spedizione";
	
	private CompositeDatiSpedizione compositeDatiSpedizione;
	private CompositeContrassegno compositeContrassegno;
	private Button btnContrassegno;
	
	private final Spedizione spedizione;
	private final Contrassegno contrassegno;

	public InserimentoDatiSpedizioneWizardPage(Spedizione spedizione, Contrassegno contrassegno) {
		super(titolo, descrizione, false);
		
		this.spedizione = spedizione;
		this.contrassegno = contrassegno;
	}
	
	@Override
	protected boolean validazioneSpecifica(boolean valid) {
		//se il contrassegno non è selezionato non deve essere considerato.
		boolean datiSpedizioneValidi = compositeDatiSpedizione.isValid();
		boolean contrassegnoValido = btnContrassegno.getSelection() ? compositeContrassegno.isValid() : true;
		return datiSpedizioneValidi && contrassegnoValido;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeDatiSpedizione = new CompositeDatiSpedizione(this, container);
		compositeDatiSpedizione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite compositeDomanda = new Composite(container, SWT.NONE);
		compositeDomanda.setLayout(new GridLayout(1, false));
		compositeDomanda.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnContrassegno = new Button(compositeDomanda, SWT.CHECK);
		btnContrassegno.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setContrassegno();
			}
		});
		btnContrassegno.setText("Contrassegno");
		
		compositeContrassegno = new CompositeContrassegno(this, container);
		compositeContrassegno.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeContrassegno.enableElement(false);
		
		setContrassegno();
	}
	
	private void setContrassegno() {
		boolean contrassegno = btnContrassegno.getSelection();
		if (contrassegno) {
			addChild(compositeContrassegno);
		} else {
			removeChild(compositeContrassegno);
		}
		compositeContrassegno.enableElement(contrassegno);
		validate();
	}

	@Override
	public void copyDataToModel() {
		//Dati spedizione
		spedizione.setIdCorriere(compositeDatiSpedizione.getCorriere().getId());
		spedizione.setCodiceCliente(compositeDatiSpedizione.getCodice().getCodiceCliente());
		spedizione.setServizio(compositeDatiSpedizione.getServizio().getCodice());
		spedizione.setLetteraDiVettura(compositeDatiSpedizione.getLetteraDiVettura()); //TODO - Fare override e super della validazione e aggiungere controllo per vedere se la lettera di vettura esiste già.
		spedizione.setColli(compositeDatiSpedizione.getColli());
		spedizione.setPeso(compositeDatiSpedizione.getPeso());
		spedizione.setVolume(compositeDatiSpedizione.getVolume());
		spedizione.setDataPartenza(compositeDatiSpedizione.getDataSpedizione());
		spedizione.setFatturazione(compositeDatiSpedizione.getFatturazione());
		//Dati contrassegno, se necessario
		if (btnContrassegno.getSelection()) {
			spedizione.setContrassegno(true);
			contrassegno.setAnnullato(compositeContrassegno.getAnnullato());
			contrassegno.setTipo(compositeContrassegno.getTipoContrassegno().getCodice());
			contrassegno.setValore(compositeContrassegno.getValore());
			contrassegno.setValuta(compositeContrassegno.getValuta().getCodice());
		} else {
			spedizione.setContrassegno(false);
		}
	}

	@Override
	public void mostraRisultato() {
		//Imposto la commessa per mostrare solo i codici cliente veramente disponibili
		compositeDatiSpedizione.setCommessa(spedizione.getIdCommessa());
	}
}
