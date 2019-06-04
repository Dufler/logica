package it.ltc.logica.ufficio.gui.elements.caricodettagli;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolItem;

import it.ltc.logica.database.model.centrale.ingressi.CaricoDettaglio;
import it.ltc.logica.database.model.centrale.ingressi.StatiCarico;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.ToolbarCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarCaricoDettagli extends ToolbarCRUD<TabellaCaricoDettagli, CaricoDettaglio> {
	
	protected ToolItem inserimentoMultiplo;
	
	protected ToolItem reportSemplice;
	protected ToolItem reportSempliceXLS;
	protected ToolItem reportPerCollo;

	public ToolbarCaricoDettagli(Composite parent) {
		super(parent);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_INGRESSI.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		inserimentoMultiplo = new ToolItem(toolbar, SWT.NONE);
		inserimentoMultiplo.setImage(Immagine.CROCIVERDI_16X16.getImage());
		inserimentoMultiplo.setDisabledImage(Immagine.CROCEVERDE_ANNULLATA_16X16.getImage());
		inserimentoMultiplo.setText("");
		inserimentoMultiplo.setToolTipText("Inserimento multiplo");
		inserimentoMultiplo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inserimentoMultiplo();
			}
		});
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();
		//Tasti specifici
		reportSemplice = new ToolItem(toolbar, SWT.NONE);
		reportSemplice.setImage(Immagine.PDF_16X16.getImage());
		reportSemplice.setDisabledImage(Immagine.PDF_ANNULLATO_16X16.getImage());
		reportSemplice.setText("");
		reportSemplice.setToolTipText("Report Semplice PDF");
		reportSemplice.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				creaReportSemplicePDF();
			}
		});
		reportSempliceXLS = new ToolItem(toolbar, SWT.NONE);
		reportSempliceXLS.setImage(Immagine.EXCEL_16X16.getImage());
		reportSempliceXLS.setDisabledImage(Immagine.EXCEL_ANNULLATO_16X16.getImage());
		reportSempliceXLS.setText("");
		reportSempliceXLS.setToolTipText("Report Semplice Excel");
		reportSempliceXLS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				creaReportSempliceExcel();
			}
		});
		reportPerCollo = new ToolItem(toolbar, SWT.NONE);
		reportPerCollo.setImage(Immagine.SCATOLA_16X16.getImage());
		reportPerCollo.setDisabledImage(Immagine.SCATOLA_ANNULLATA_16X16.getImage());
		reportPerCollo.setText("");
		reportPerCollo.setToolTipText("Report per collo");
		reportPerCollo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				creaReportPerCollo();
			}
		});
	}
	
	protected void creaReportSemplicePDF() {
		tabella.creaReportSemplicePDF();
	}
	
	protected void creaReportSempliceExcel() {
		tabella.creaReportSempliceExcel();
	}
	
	protected void creaReportPerCollo() {
		tabella.creaReportPerCollo();
	}
	
	public void abilitaTastiCRUDPerStato(StatiCarico stato) {
		boolean permesso = isAbilitato();
		boolean statoNuovo = false;
		boolean statoModifica = false;
		boolean statoElimina = false;
		switch (stato) {
			case INSERITO : statoNuovo = true; statoModifica = true; statoElimina = true; break;
			case ARRIVATO : statoNuovo = true; statoModifica = true; statoElimina = true; break;
			case IN_LAVORAZIONE : statoNuovo = false; statoModifica = false; statoElimina = false; break;
			case LAVORATO : statoNuovo = false; statoModifica = true; statoElimina = false; break;
			case CHIUSO : statoNuovo = false; statoModifica = false; statoElimina = false; break;
			case ANNULLATO : statoNuovo = false; statoModifica = false; statoElimina = false; break;
		}
		nuovo.setEnabled(permesso && statoNuovo);
		inserimentoMultiplo.setEnabled(permesso && statoNuovo);
		modifica.setEnabled(permesso && statoModifica);
		elimina.setEnabled(permesso && statoElimina);
	}
	
	public void inserimentoMultiplo() {
		tabella.apriDialogInserimentoMultiplo();
	} 

}
