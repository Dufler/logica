package it.ltc.logica.ufficio.gui.elements.ordinedettagli;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.ordini.OrdineDettaglio;
import it.ltc.logica.database.model.centrale.ordini.StatiOrdine;
import it.ltc.logica.gui.elements.ToolbarCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarOrdineDettagli extends ToolbarCRUD<TabellaOrdineDettagli, OrdineDettaglio> {
	
//	protected ToolItem reportSemplice;
//	protected ToolItem reportSempliceXLS;
//	protected ToolItem reportPerCollo;

	public ToolbarOrdineDettagli(Composite parent) {
		super(parent);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_INGRESSI.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();
		//Tasti specifici
//		reportSemplice = new ToolItem(toolbar, SWT.NONE);
//		reportSemplice.setImage(Immagine.PDF_16X16.getImage());
//		reportSemplice.setDisabledImage(Immagine.PDF_ANNULLATO_16X16.getImage());
//		reportSemplice.setText("");
//		reportSemplice.setToolTipText("Report Semplice PDF");
//		reportSemplice.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				creaReportSemplicePDF();
//			}
//		});
//		reportSempliceXLS = new ToolItem(toolbar, SWT.NONE);
//		reportSempliceXLS.setImage(Immagine.EXCEL_16X16.getImage());
//		reportSempliceXLS.setDisabledImage(Immagine.EXCEL_ANNULLATO_16X16.getImage());
//		reportSempliceXLS.setText("");
//		reportSempliceXLS.setToolTipText("Report Semplice Excel");
//		reportSempliceXLS.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				creaReportSempliceExcel();
//			}
//		});
//		reportPerCollo = new ToolItem(toolbar, SWT.NONE);
//		reportPerCollo.setImage(Immagine.LENTE_16X16.getImage());
//		reportPerCollo.setDisabledImage(Immagine.LENTE_16X16.getImage());
//		reportPerCollo.setText("");
//		reportPerCollo.setToolTipText("Report per collo");
//		reportPerCollo.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				creaReportPerCollo();
//			}
//		});
	}
	
//	protected void creaReportSemplicePDF() {
//		tabella.creaReportSemplicePDF();
//	}
//	
//	protected void creaReportSempliceExcel() {
//		tabella.creaReportSempliceExcel();
//	}
//	
//	protected void creaReportPerCollo() {
//		tabella.creaReportPerCollo();
//	}
	
	public void abilitaTastiCRUDPerStato(StatiOrdine stato) {
		boolean permesso = isAbilitato();
		boolean statoNuovo = false;
		boolean statoModifica = false;
		boolean statoElimina = false;
		switch (stato) {
			case INSE : statoNuovo = true; statoModifica = true; statoElimina = true; break;
			default : statoNuovo = false; statoModifica = false; statoElimina = false; break;
		}
		nuovo.setEnabled(permesso && statoNuovo);
		modifica.setEnabled(permesso && statoModifica);
		elimina.setEnabled(permesso && statoElimina);
	}
}
