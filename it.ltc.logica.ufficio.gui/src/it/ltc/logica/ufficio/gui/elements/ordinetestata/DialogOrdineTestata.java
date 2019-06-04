package it.ltc.logica.ufficio.gui.elements.ordinetestata;

import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.common.controller.uscite.ControllerDestinatari;
import it.ltc.logica.common.controller.uscite.ControllerMittenti;
import it.ltc.logica.common.controller.uscite.ControllerOrdineTipi;
import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.database.model.centrale.ordini.StatiOrdine;
import it.ltc.logica.gui.common.composite.CompositeDocumento;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.ufficio.gui.elements.ordineiballi.TabellaImballi;
import it.ltc.logica.ufficio.gui.elements.ordineiballi.ToolbarImballi;
import it.ltc.logica.ufficio.gui.elements.ordineoperatori.TabellaOrdineOperatori;
import it.ltc.logica.ufficio.gui.elements.ordinestati.TabellaOrdineStati;

public class DialogOrdineTestata extends DialogModel<OrdineTestata> {
	
	private static final String title = "Ordine";
	
	private final boolean abilita;
	private final Commessa commessa;
	private final ControllerOrdini controllerOrdini;
	private final ControllerOrdineTipi controllerTipi;
	private final ControllerDestinatari controllerDestinatari;
	private final ControllerMittenti conotrollerMittenti;	
	
	private CTabFolder tabFolder;
	private CompositeOrdineTestata compositeOrdine;
	private CompositeDocumento compositeDocumento;
	private CompositeInfoImballo compositeImballo;
	private ToolbarImballi toolbarImballi;
	private TabellaImballi tabellaImballi;

	public DialogOrdineTestata(Commessa commessa, OrdineTestata value, boolean abilita) {
		super(title, value);
		
		this.abilita = abilita;
		this.commessa = commessa;
		this.controllerOrdini = new ControllerOrdini(commessa);
		this.controllerTipi = ControllerOrdineTipi.getInstance();
		this.controllerDestinatari = new ControllerDestinatari(commessa);
		this.conotrollerMittenti = new ControllerMittenti(commessa);
		
		this.minimumHeight = 550;
	}

	@Override
	public void loadModel() {
		//Documento
		compositeDocumento.setDataDocumento(valore.getDocumentoData());
		compositeDocumento.setRiferimento(valore.getDocumentoRiferimento());
		compositeDocumento.setTipo(valore.getDocumentoTipo());
		//Ordine
		compositeOrdine.setRiferimento(valore.getRiferimento());
		compositeOrdine.setLista(valore.getNumeroLista());
		compositeOrdine.setDestinatario(controllerDestinatari.getIndirizzo(valore.getDestinatario()));
		compositeOrdine.setMittente(conotrollerMittenti.getIndirizzo(valore.getMittente()));
		compositeOrdine.setPezzi(valore.getQuantitaOrdinataTotale(), valore.getQuantitaImballataTotale());
//		compositeOrdine.setColli(valore.getColli());
		compositeOrdine.setTipo(controllerTipi.getTipo(commessa, valore.getTipo()));
		compositeOrdine.setStato(valore.getStato());
		compositeOrdine.settNote(valore.getNote());
		compositeOrdine.setDataOrdine(valore.getDataOrdine());
		compositeOrdine.setDataConsegna(valore.getDataConsegna());
		compositeOrdine.setPriorita(valore.getPriorita());
		compositeOrdine.lockNonUpdatableElements();
		//Imballi
		compositeImballo.setColli(valore.getColli());
	}

	@Override
	public void prefillModel() {
		compositeOrdine.setPriorita(1);
		compositeOrdine.setDataOrdine(new Date());
		compositeOrdine.setStato(StatiOrdine.INSE);
	}

	@Override
	public void copyDataToModel() {
		// Documento
		valore.setDocumentoData(compositeDocumento.getDataDocumento());
		valore.setDocumentoRiferimento(compositeDocumento.getRiferimento());
		valore.setDocumentoTipo(compositeDocumento.getTipo());
		// Ordine
		valore.setRiferimento(compositeOrdine.getRiferimento());
		valore.setNumeroLista(compositeOrdine.getLista());
		valore.setRagioneSocialeDestinatario(compositeOrdine.getDestinatario() != null ? compositeOrdine.getDestinatario().getRagioneSociale() : "");
		valore.setDestinatario(compositeOrdine.getDestinatario() != null ? compositeOrdine.getDestinatario().getId() : -1);
		valore.setMittente(compositeOrdine.getMittente() != null ? compositeOrdine.getMittente().getId() : -1);
		valore.setTipo(compositeOrdine.getTipo().getCodice());
		valore.setNote(compositeOrdine.getNote());
		valore.setDataOrdine(compositeOrdine.getDataOrdine());
		valore.setDataConsegna(compositeOrdine.getDataConsegna());
		valore.setPriorita(compositeOrdine.getPriorita());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = controllerOrdini.aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = controllerOrdini.inserisci(valore);
		return insert;
	}

	@Override
	public OrdineTestata createNewModel() {
		OrdineTestata testata = new OrdineTestata();
		return testata;
	}

	@Override
	public boolean isDirty() {
		boolean ordineDirty = compositeOrdine.isDirty();
		boolean documentoDirty = compositeDocumento.isDirty();
		return ordineDirty || documentoDirty;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		if (modify) {
			
			tabFolder = new CTabFolder(container, SWT.BORDER);
			tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
			
			CTabItem tbtmDatiCarico = new CTabItem(tabFolder, SWT.NONE);
			tbtmDatiCarico.setText("Info Carico");
			
			Composite compositeDati = new Composite(tabFolder, SWT.NONE);
			compositeDati.setLayout(new GridLayout(1, false));
			
			compositeOrdine = new CompositeOrdineTestata(this, compositeDati, commessa);
			compositeOrdine.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			
			compositeDocumento = new CompositeDocumento(this, compositeDati);
			compositeDocumento.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
			
			tbtmDatiCarico.setControl(compositeDati);
			
			CTabItem tbtmStatiCarico = new CTabItem(tabFolder, SWT.NONE);
			tbtmStatiCarico.setText("Avanzamento Stati");
			
			Composite compositeStati = new Composite(tabFolder, SWT.NONE);
			compositeStati.setLayout(new GridLayout(1, false));
			
			Label lblAvanzamentoStati = new Label(compositeStati, SWT.NONE);
			lblAvanzamentoStati.setText("Avanzamento stati:");
			
			TabellaOrdineStati tabellaStati = new TabellaOrdineStati(compositeStati, controllerOrdini, valore);
			
			Table tableStati = tabellaStati.getTable();
			tableStati.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
			
			Label lblOperatori = new Label(compositeStati, SWT.NONE);
			lblOperatori.setText("Operatori:");
			
			TabellaOrdineOperatori tabellaOperatori = new TabellaOrdineOperatori(compositeStati, controllerOrdini, valore);
			
			Table tableOperatori = tabellaOperatori.getTable();
			tableOperatori.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
			
			tbtmStatiCarico.setControl(compositeStati);
			
			tabellaStati.aggiornaContenuto();
			tabellaOperatori.aggiornaContenuto();
			
			CTabItem tbtmImballiOrdine = new CTabItem(tabFolder, SWT.NONE);
			tbtmImballiOrdine.setText("Info Imballo");
			
			Composite compositeImballi = new Composite(tabFolder, SWT.NONE);
			compositeImballi.setLayout(new GridLayout(1, false));
			
			compositeImballo = new CompositeInfoImballo(compositeImballi);
			compositeImballo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
			compositeImballo.enableElement(false);
			
			toolbarImballi = new ToolbarImballi(compositeImballi);
			
			tabellaImballi = new TabellaImballi(compositeImballi, commessa, valore);
			//tabellaImballi.mostraMessaggio("Scarica Info", "Clicca sul tasto scarica per recuperare le info sugli imballi.");
			//tabellaImballi.getImballi();
			
			toolbarImballi.setTabella(tabellaImballi);
			
			tbtmImballiOrdine.setControl(compositeImballi);
			
		} else {
			compositeOrdine = new CompositeOrdineTestata(this, container, commessa);
			compositeOrdine.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			
			compositeDocumento = new CompositeDocumento(this, container);
			compositeDocumento.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		}
		
		//Abilito o disabilito i componenti
		compositeOrdine.enableElement(abilita);
		compositeDocumento.enableElement(abilita);
	}

}
