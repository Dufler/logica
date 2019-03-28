package it.ltc.logica.ufficio.gui.uscite.elements.spedizione;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.trasporti.ControllerCodiciClienteCorriere;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.common.controller.trasporti.ControllerServizioSpedizione;
import it.ltc.logica.common.controller.trasporti.ControllerTipoContrassegno;
import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.documenti.Documento.TipoDocumento;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.gui.common.composite.CompositeContrassegno;
import it.ltc.logica.gui.common.composite.CompositeCorriere;
import it.ltc.logica.gui.common.composite.CompositeDocumento;
import it.ltc.logica.gui.dialog.DialogModel;

public class DialogDatiSpedizione extends DialogModel<DatiSpedizione> {

	public static final String title = "Dati spedizione";
	
	private final Commessa commessa;
	private final ControllerOrdini controller;
	
	private CompositeCorriere compositeCorriere;
	private CompositeContrassegno compositeContrassegno;
	private CompositeDocumento compositeDocumento;
	private CompositeDatiSpedizione compositeDatiSpedizione;
	
	public DialogDatiSpedizione(Commessa commessa, DatiSpedizione value) {
		super(title, value);
		this.commessa = commessa;
		this.controller = new ControllerOrdini(commessa);
		this.minimumHeight = 500;
	}

	@Override
	public void loadModel() {
		//Dati spedizione
		compositeDatiSpedizione.setPezzi(valore.getPezzi());
		compositeDatiSpedizione.setColli(valore.getColli());
		compositeDatiSpedizione.setPeso(valore.getPeso());
		compositeDatiSpedizione.setVolume(valore.getVolume());
		compositeDatiSpedizione.setDataConsegna(valore.getDataConsegna());
		compositeDatiSpedizione.setOrdini(valore.getOrdini());
		//corriere
		compositeCorriere.setCorriere(ControllerCorrieri.getInstance().getCorriere(valore.getCorriere()));
		compositeCorriere.setCodice(ControllerCodiciClienteCorriere.getInstance().getCodiceCliente(valore.getCodiceCorriere()));
		compositeCorriere.setServizio(ControllerServizioSpedizione.getInstance().getServizio(valore.getServizioCorriere()));
		//contrassegno
		compositeContrassegno.setValore(valore.getValoreContrassegno());
		compositeContrassegno.setTipoContrassegno(ControllerTipoContrassegno.getInstance().getTipoContrassegno(valore.getTipoContrassegno()));
		//documento
		compositeDocumento.setRiferimento(valore.getRiferimentoDocumento());
		compositeDocumento.setDataDocumento(valore.getDataDocumento());
		String tipoDocumento = valore.getTipoDocumento();
		if (tipoDocumento != null && !tipoDocumento.isEmpty())
			compositeDocumento.setTipo(TipoDocumento.valueOf(tipoDocumento));
	}

	@Override
	public void prefillModel() {
		compositeDatiSpedizione.setDataConsegna(new Date());
		compositeDocumento.setDataDocumento(new Date());
	}

	@Override
	public void copyDataToModel() {
		//Dati spedizione
		valore.setPezzi(compositeDatiSpedizione.getPezzi());
		valore.setColli(compositeDatiSpedizione.getColli());
		valore.setPeso(compositeDatiSpedizione.getPeso());
		valore.setVolume(compositeDatiSpedizione.getVolume());
		valore.setDataConsegna(compositeDatiSpedizione.getDataConsegna());
		valore.setOrdini(compositeDatiSpedizione.getOrdini());
		//corriere
		valore.setCorriere(compositeCorriere.getCorriere().getCodifica());
		valore.setCodiceCorriere(compositeCorriere.getCodice().getCodiceCliente());
		valore.setServizioCorriere(compositeCorriere.getServizio().getCodice());
		//contrassegno
		valore.setValoreContrassegno(compositeContrassegno.getValore());
		valore.setTipoContrassegno(compositeContrassegno.getTipoContrassegno().getCodice());
		//documento
		valore.setRiferimentoDocumento(compositeDocumento.getRiferimento());
		valore.setDataDocumento(compositeDocumento.getDataDocumento());
		valore.setTipoDocumento(compositeDocumento.getTipo().name());
	}

	@Override
	public List<String> validateModel() {
		List<String> errors = new LinkedList<>();
		if (valore.getOrdini().isEmpty()) {
			errors.add("E' necessario selezionare almeno un ordine da spedire.");
		}
		return errors;
	}

	@Override
	public boolean updateModel() {
		boolean salva = controller.salvaDatiSpedizione(valore) != null;
		return salva;
	}

	@Override
	public boolean insertModel() {
		boolean salva = controller.salvaDatiSpedizione(valore) != null;
		return salva;
	}

	@Override
	public DatiSpedizione createNewModel() {
		DatiSpedizione dati = new DatiSpedizione();
		return dati;
	}

	@Override
	public boolean isDirty() {
		boolean dati = compositeDatiSpedizione.isDirty();
		boolean corriere = compositeCorriere.isDirty();
		boolean contrassegno = compositeContrassegno.isDirty();
		boolean documento = compositeDocumento.isDirty();
		return dati || corriere || contrassegno || documento;
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		CTabFolder tabFolder = new CTabFolder(container, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		CTabItem tabDatiSpedizione = new CTabItem(tabFolder, SWT.NONE);
		tabDatiSpedizione.setText("Dati Spedizione");
		
		compositeDatiSpedizione = new CompositeDatiSpedizione(this, tabFolder); 
		compositeDatiSpedizione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		tabDatiSpedizione.setControl(compositeDatiSpedizione);
		
		CTabItem tbtmCorriere = new CTabItem(tabFolder, SWT.NONE);
		tbtmCorriere.setText("Corriere");
		
		compositeCorriere = new CompositeCorriere(this, tabFolder, commessa);
		compositeCorriere.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		tbtmCorriere.setControl(compositeCorriere);
		
		CTabItem tbtmContrassegno = new CTabItem(tabFolder, SWT.NONE);
		tbtmContrassegno.setText("Contrassegno");
		
		compositeContrassegno = new CompositeContrassegno(this, tabFolder);
		compositeContrassegno.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		tbtmContrassegno.setControl(compositeContrassegno);
		
		CTabItem tbtmDocumento = new CTabItem(tabFolder, SWT.NONE);
		tbtmDocumento.setText("Documento");
		
		compositeDocumento = new CompositeDocumento(this, tabFolder);
		compositeDocumento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		tbtmDocumento.setControl(compositeDocumento);		
		
	}

}
