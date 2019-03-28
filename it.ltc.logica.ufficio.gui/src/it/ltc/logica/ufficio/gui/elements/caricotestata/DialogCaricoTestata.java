package it.ltc.logica.ufficio.gui.elements.caricotestata;

import java.util.LinkedList;
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

import it.ltc.logica.common.controller.ingressi.ControllerCarichi;
import it.ltc.logica.common.controller.ingressi.ControllerCarichiTipi;
import it.ltc.logica.common.controller.ingressi.ControllerFornitori;
import it.ltc.logica.common.controller.ingressi.ControllerStagioni;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.documenti.Documento.TipoDocumento;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.database.model.centrale.ingressi.StatiCarico;
import it.ltc.logica.gui.common.composite.CompositeDocumento;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.ufficio.gui.elements.caricostati.TabellaCaricoStati;

public class DialogCaricoTestata extends DialogModel<CaricoTestata> {

	private static final String title = "Carico";
	
	private final Commessa commessa;
	private final ControllerCarichi controllerCarichi;
	private final ControllerCarichiTipi controllerTipi;
	private final ControllerFornitori controllerFornitori;
	
	private CompositeCaricoTestata compositeTestata;
	private CompositeDocumento compositeDocumento;
	
	private CTabFolder tabFolder;
	
	public DialogCaricoTestata(Commessa commessa, CaricoTestata value, boolean abilita) {
		super(title, null, value, abilita);
		
		this.commessa = commessa;
		this.controllerCarichi = new ControllerCarichi(commessa);
		this.controllerTipi = ControllerCarichiTipi.getInstance();
		this.controllerFornitori = new ControllerFornitori(commessa);
		
		this.minimumHeight = 550;
	}

	@Override
	public void loadModel() {
		//Testata
		compositeTestata.setRiferimento(valore.getRiferimento());
		compositeTestata.setStato(valore.getStato());
		compositeTestata.setTipo(controllerTipi.getTipo(commessa, valore.getTipo()));
//		compositeTestata.setStagione(valore.getStagione());
		compositeTestata.setStagione(ControllerStagioni.getInstance().getStagioneDaCodice(commessa, valore.getStagione()));
		compositeTestata.setPezzi(valore.getQuantitaRiscontrataTotale() + "/" + valore.getQuantitaDichiarataTotale());
		compositeTestata.setNote(valore.getNote());
		compositeTestata.setDataEffettiva(valore.getDataArrivo());
		compositeTestata.setDataPresunta(valore.getDataArrivoPresunto());
		compositeTestata.setEccedenze(valore.isProdottiEccedenti());
		compositeTestata.setNonDichiarati(valore.isProdottiNonDichiarati());
		compositeTestata.setFornitore(controllerFornitori.getFornitore(valore.getFornitore()));
		compositeTestata.lockNonUpdatableElements();
		//Documento
		compositeDocumento.setRiferimento(valore.getDocumentoRiferimento());
		compositeDocumento.setDataDocumento(valore.getDocumentoData());
		compositeDocumento.setTipo(TipoDocumento.valueOf(valore.getDocumentoTipo()));
	}

	@Override
	public void prefillModel() {
//		Date now = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yy");
//		String stagione = "CO" + sdf.format(now);
//		compositeTestata.setStagione(stagione);
		compositeTestata.setStato(StatiCarico.INSERITO);
	}

	@Override
	public void copyDataToModel() {
		//Testata
		valore.setRiferimento(compositeTestata.getRiferimento());
		//valore.setStato(StatiCarico.INSERITO);
		valore.setTipo(compositeTestata.getTipo().getCodice());
//		valore.setStagione(compositeTestata.getStagione());
		valore.setStagione(compositeTestata.getStagione().getCodice());
		valore.setNote(compositeTestata.getNote());
		valore.setDataArrivo(compositeTestata.getDataEffettiva());
		valore.setDataArrivoPresunto(compositeTestata.getDataPresunta());
		valore.setProdottiEccedenti(compositeTestata.getEccedenze());
		valore.setProdottiNonDichiarati(compositeTestata.getNonDichiarati());
		valore.setFornitore(compositeTestata.getFornitore() != null ? compositeTestata.getFornitore().getId() : 0);
		//Documento
		valore.setDocumentoRiferimento(compositeDocumento.getRiferimento());
		valore.setDocumentoData(compositeDocumento.getDataDocumento());
		valore.setDocumentoTipo(compositeDocumento.getTipo().name());
	}

	@Override
	public List<String> validateModel() {
		List<String> errors = new LinkedList<>();
		if (valore.getFornitore() < 1)
			errors.add("Va selezionato un fornitore valido.");
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = controllerCarichi.aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = controllerCarichi.inserisci(valore);
		return insert;
	}

	@Override
	public CaricoTestata createNewModel() {
		CaricoTestata carico = new CaricoTestata();
		return carico;
	}

	@Override
	public boolean isDirty() {
		boolean testataDirty = compositeTestata.isDirty();
		boolean documentoDirty = compositeDocumento.isDirty();
		return testataDirty || documentoDirty;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		if (modify) {
			
			tabFolder = new CTabFolder(container, SWT.BORDER);
			tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
			
			CTabItem tbtmDatiCarico = new CTabItem(tabFolder, SWT.NONE);
			tbtmDatiCarico.setText("Info Carico");
			
			Composite compositeDatiCarico = new Composite(tabFolder, SWT.NONE);
			compositeDatiCarico.setLayout(new GridLayout(1, false));
			
			compositeTestata = new CompositeCaricoTestata(this, compositeDatiCarico, commessa);
			compositeTestata.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			
			compositeDocumento = new CompositeDocumento(this, compositeDatiCarico);
			compositeDocumento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			
			tbtmDatiCarico.setControl(compositeDatiCarico);
			
			CTabItem tbtmStatiCarico = new CTabItem(tabFolder, SWT.NONE);
			tbtmStatiCarico.setText("Avanzamento Stati");
			
			Composite compositeStatiCarico = new Composite(tabFolder, SWT.NONE);
			compositeStatiCarico.setLayout(new GridLayout(1, false));
			
			Label lblAvanzamentoStati = new Label(compositeStatiCarico, SWT.NONE);
			lblAvanzamentoStati.setText("Avanzamento stati:");
			
			TabellaCaricoStati tabellaStati = new TabellaCaricoStati(compositeStatiCarico, controllerCarichi, valore);
			
			Table table = tabellaStati.getTable();
			table.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
			
			tbtmStatiCarico.setControl(compositeStatiCarico);
			
			tabellaStati.aggiornaContenuto();	
		} else {
			compositeTestata = new CompositeCaricoTestata(this, container, commessa);
			compositeTestata.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			
			compositeDocumento = new CompositeDocumento(this, container);
			compositeDocumento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		}
	}

}
