package it.ltc.logica.trasporti.gui.preventivi.wizards.esistenti;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import it.ltc.database.dao.locali.CriteriSelezioneSpedizioni;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.TipoSpedizione;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.trasporti.controller.preventivi.PreventivoEsistentiController;
import it.ltc.logica.trasporti.gui.elements.spedizionecheckbox.TabellaCheckBoxSpedizione;

public class PreventivoSpedizioniEsistentiSelezioneSpedizioniWizardPage extends PaginaWizardRisultati {
	
	private static final String titolo = "Preventivo di costo e fatturazione per spedizioni gi\u00E0 esistenti";
	private static final String descrizione = "Seleziona una o pi\u00F9 spedizioni gi\u00E0 effettuate per vedere quanto sarebbero costate.";
	
	private PreventivoEsistentiController controllerPreventivo;
	private final ControllerSpedizioni controllerSpedizioni;
	
	private TabellaCheckBoxSpedizione checkboxTableViewer;
	private Table table;
	
	private boolean canFlip;

	public PreventivoSpedizioniEsistentiSelezioneSpedizioniWizardPage() {
		super(titolo, descrizione, false);
		controllerSpedizioni = ControllerSpedizioni.getInstance();
		controllerPreventivo = PreventivoEsistentiController.getInstance();
		canFlip = false;
	}
	
	private void valida() {
		canFlip = checkboxTableViewer.getCheckedElements().length > 0;
		setPageComplete(canFlip);
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return canFlip;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		checkboxTableViewer = new TabellaCheckBoxSpedizione(container, TabellaCheckBoxSpedizione.TIPO_COMPLETO);
		
		table = checkboxTableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				valida();
			}
		});
		
		Composite compositeSelezione = new Composite(container, SWT.NONE);
		compositeSelezione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeSelezione.setLayout(new GridLayout(2, false));
		
		Button btnSelezionaTutte = new Button(compositeSelezione, SWT.NONE);
		btnSelezionaTutte.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkboxTableViewer.setAllChecked(true);
				valida();
			}
		});
		btnSelezionaTutte.setText("Seleziona tutte");
		
		Button btnDeselezionaTutte = new Button(compositeSelezione, SWT.NONE);
		btnDeselezionaTutte.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkboxTableViewer.setAllChecked(false);
				valida();
			}
		});
		btnDeselezionaTutte.setText("Deseleziona tutte");
	}

	@Override
	public void mostraRisultato() {
		Integer commessa = controllerPreventivo.getCommessa() != null ? controllerPreventivo.getCommessa().getId() : null;
		Integer corriere = controllerPreventivo.getCorriere() != null ? controllerPreventivo.getCorriere().getId() : null;
		String codiceCliente = controllerPreventivo.getCodice() != null ? controllerPreventivo.getCodice().getCodiceCliente() : null;
		String servizio = controllerPreventivo.getServizio() != null ? controllerPreventivo.getServizio().getCodice() : null;
		TipoSpedizione tipo = controllerPreventivo.getTipoSpedizione();
		Boolean contrassegno = controllerPreventivo.getContrassegno();
		Date da = controllerPreventivo.getDataDa();
		Date a = controllerPreventivo.getDataA();
		boolean pezziNecessari = controllerPreventivo.isPezziNecessari();
		boolean colliNecessari = controllerPreventivo.isColliNecessari();
		boolean pesoNecessario = controllerPreventivo.isPesoNecessario();
		boolean volumeNecessario = controllerPreventivo.isVolumeNecassario();
		CriteriSelezioneSpedizioni criteri = new CriteriSelezioneSpedizioni();
		criteri.setCommessa(commessa);
		criteri.setDataDa(da);
		criteri.setDataA(a);
		criteri.setContrassegno(contrassegno);
		criteri.setCorriere(corriere);
		criteri.setCodiceCliente(codiceCliente);
		criteri.setServizio(servizio);
		criteri.setTipo(tipo);
		criteri.setPezziNecessari(pezziNecessari);
		criteri.setColliNecessari(colliNecessari);
		criteri.setPesoNecessario(pesoNecessario);
		criteri.setVolumeNecessario(volumeNecessario);
		checkboxTableViewer.setInput(controllerSpedizioni.selezionaSpedizioni(criteri));
		//checkboxTableViewer.setInput(controllerSpedizioni.filtraSpedizioni(commessaSelezionata, da, a, contrassegno, corriere, codiceCliente, servizio, tipo, pezziNecessari, colliNecessari, pesoNecessario, volumeNecessario));
		for (TableColumn column : table.getColumns()) {
			column.pack();
		}
	}
	
	@Override
	public void copyDataToModel() {
		Object[] spedizioniSelezionate = checkboxTableViewer.getCheckedElements();
		controllerPreventivo.setSpedizioniSelezionate(spedizioniSelezionate);		
	}
	
}
