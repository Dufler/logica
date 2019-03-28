package it.ltc.logica.trasporti.gui.preventivi.wizards.importate;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.common.controller.listini.ControllerListiniCorrieri;
import it.ltc.logica.common.controller.listini.ListiniSimulazioneController;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.controller.preventivi.PreventivoSimulazioniImportateController;

public class PreventivoSpedizioneImportateSelezioneDatiWizardPage extends PaginaWizard {
	
	private static final String title = "Preventivo di costo e fatturazione per delle spedizioni importate.";
	private static final String description = "Seleziona i listini da utilizzare per la simulazione.";
	
	private static final int ALTEZZA_TABELLE = 200;
	
	private final PreventivoSimulazioniImportateController controllerPreventivo;
	private final ControllerListiniCorrieri controllerListiniCorrieri;
	private final ControllerListiniClienti controllerListiniClienti;
	private final ListiniSimulazioneController controllerListiniSimulazione;
	
	//private Composite container;
	
	private CheckboxTableViewer checkboxTableViewerClienti;
	private Table tableListiniClienti;
	
	private CheckboxTableViewer checkboxTableViewerCorrieri;
	private Table tableListiniCorrieri;
	
	private CheckboxTableViewer checkboxTableViewerSimulazione;
	private Table tableListiniSimulazione;
	
	//private boolean canFlip;

	public PreventivoSpedizioneImportateSelezioneDatiWizardPage() {
		super(title, description);
		controllerPreventivo = PreventivoSimulazioniImportateController.getInstance();
		controllerListiniCorrieri = ControllerListiniCorrieri.getInstance();
		controllerListiniClienti = ControllerListiniClienti.getInstance();
		controllerListiniSimulazione = ListiniSimulazioneController.getInstance();
		//canFlip = false;
	}
	
//	private void valida() {
//		int tableClientiSelected = checkboxTableViewerClienti.getCheckedElements().length;
//		int tableCorrieriSelected = checkboxTableViewerCorrieri.getCheckedElements().length;
//		int tableSimulazioneSelected = checkboxTableViewerSimulazione.getCheckedElements().length;
//		boolean listiniSelezionati = (tableClientiSelected + tableCorrieriSelected + tableSimulazioneSelected) > 0;
//		canFlip = listiniSelezionati;
//		setPageComplete(canFlip);
//	}
	
	protected boolean validazioneSpecifica(boolean valid) {
		int tableClientiSelected = checkboxTableViewerClienti.getCheckedElements().length;
		int tableCorrieriSelected = checkboxTableViewerCorrieri.getCheckedElements().length;
		int tableSimulazioneSelected = checkboxTableViewerSimulazione.getCheckedElements().length;
		boolean listiniSelezionati = (tableClientiSelected + tableCorrieriSelected + tableSimulazioneSelected) > 0;
		return valid && listiniSelezionati;
	}
	
//	@Override
//	public boolean canFlipToNextPage() {
//		return canFlip;
//	}
	
	private void setListiniCorriere() {
		Object[] listiniCorriereSelezionati = checkboxTableViewerCorrieri.getCheckedElements();
		List<ListinoCorriere> listini = new LinkedList<ListinoCorriere>();
		for (Object listino : listiniCorriereSelezionati) {
			ListinoCorriere l = (ListinoCorriere) listino;
			listini.add(l);
		}
		controllerPreventivo.setListiniCorriere(listini);
	}
	
	private void setListiniCliente() {
		Object[] listiniClienteSelezionati = checkboxTableViewerClienti.getCheckedElements();
		List<ListinoCommessa> listini = new LinkedList<ListinoCommessa>();
		for (Object listino : listiniClienteSelezionati) {
			ListinoCommessa l = (ListinoCommessa) listino;
			listini.add(l);
		}
		controllerPreventivo.setListiniCliente(listini);
	}
	
	private void setListiniSimulazione() {
		Object[] listiniSimulazioneSelezionati = checkboxTableViewerSimulazione.getCheckedElements();
		List<ListinoSimulazione> listini = new LinkedList<ListinoSimulazione>();
		for (Object listino : listiniSimulazioneSelezionati) {
			ListinoSimulazione l = (ListinoSimulazione) listino;
			listini.add(l);
		}
		controllerPreventivo.setListiniSimulazione(listini);
	}
	
	private void aggiornaTabellaClienti() {
		checkboxTableViewerClienti.setInput(controllerListiniClienti.getListiniClientiPerTrasporti());
		for (TableColumn column : tableListiniClienti.getColumns()) {
			column.pack();
		}
	}
	
	private void aggiornaTabellaCorrieri() {
		checkboxTableViewerCorrieri.setInput(controllerListiniCorrieri.getListini());
		for (TableColumn column :tableListiniCorrieri.getColumns()) {
			column.pack();
		}
	}
	
	private void aggiornaTabellaSimulazione() {
		checkboxTableViewerSimulazione.setInput(controllerListiniSimulazione.getListiniDiSimulazione());
		for (TableColumn column :tableListiniSimulazione.getColumns()) {
			column.pack();
		}
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		this.container = container;
		container.setLayout(new GridLayout(1, false));
		
		Label lblSelezionaLaCommessa = new Label(container, SWT.NONE);
		lblSelezionaLaCommessa.setText("Seleziona i listini con cui effettuare le simulazioni: ");
//		lblSelezionaLaCommessa.setText("Seleziona la commessa del cliente da cui selezionare i listini e le spedizioni: ");
		
//		comboCommessa = new ComboBox<Commessa>(container);
//		comboCommessa.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				valida();
//				Commessa commessa = comboCommessa.getSelectedValue();
//				if (commessa != null)
//					aggiornaTabellaClienti(commessa);
//			}
//		});
//		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
//		
//		comboViewer = comboCommessa.getViewer();
//		comboViewer.setInput(ControllerCommesse.getInstance().getCommesse());
		
		Label lblSelezionaIListiniClienti = new Label(container, SWT.NONE);
		lblSelezionaIListiniClienti.setText("Seleziona i listini legati alla commessa selezionata con cui realizzare il preventivo:");
		
		checkboxTableViewerClienti = CheckboxTableViewer.newCheckList(container, SWT.BORDER | SWT.FULL_SELECTION);
		checkboxTableViewerClienti.setContentProvider(ArrayContentProvider.getInstance());
		
		tableListiniClienti = checkboxTableViewerClienti.getTable();
		tableListiniClienti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				validate();
			}
		});
		tableListiniClienti.setToolTipText("Qui puoi selezionare su quali listini cliente realizzare il preventivo.");
		tableListiniClienti.setLinesVisible(true);
		tableListiniClienti.setHeaderVisible(true);
		GridData layoutTableListiniClienti = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		layoutTableListiniClienti.heightHint = ALTEZZA_TABELLE;
		tableListiniClienti.setLayoutData(layoutTableListiniClienti);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(checkboxTableViewerClienti, SWT.NONE);
		tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				ListinoCommessa listino = (ListinoCommessa) element;
				String nome = listino.getNome();
				return nome;
			}
		});
		
		TableColumn tblclmnListinoClienti = tableViewerColumn.getColumn();
		tblclmnListinoClienti.setWidth(100);
		tblclmnListinoClienti.setText("Listino");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(checkboxTableViewerClienti, SWT.NONE);
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				ListinoCommessa listino = (ListinoCommessa) element;
				String descrizione = listino.getDescrizione();
				return descrizione;
			}
		});
		
		TableColumn tblclmnDescrizioneClienti = tableViewerColumn_1.getColumn();
		tblclmnDescrizioneClienti.setWidth(100);
		tblclmnDescrizioneClienti.setText("Descrizione");
		
		Label lblSelezionaIListiniCorrieri = new Label(container, SWT.NONE);
		lblSelezionaIListiniCorrieri.setText("Seleziona i listini dei corrieri con cui realizzare il preventivo: ");
		
		checkboxTableViewerCorrieri = CheckboxTableViewer.newCheckList(container, SWT.BORDER | SWT.FULL_SELECTION);
		checkboxTableViewerCorrieri.setContentProvider(ArrayContentProvider.getInstance());
		
		tableListiniCorrieri = checkboxTableViewerCorrieri.getTable();
		tableListiniCorrieri.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				validate();
			}
		});
		tableListiniCorrieri.setToolTipText("Qui puoi selezionare su quali listini corriere realizzare il preventivo.");
		tableListiniCorrieri.setLinesVisible(true);
		tableListiniCorrieri.setHeaderVisible(true);
		GridData layoutTabellaListiniCorrieri = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		layoutTabellaListiniCorrieri.heightHint = ALTEZZA_TABELLE;
		tableListiniCorrieri.setLayoutData(layoutTabellaListiniCorrieri);
		
		TableViewerColumn tableViewerColumnListino = new TableViewerColumn(checkboxTableViewerCorrieri, SWT.NONE);
		tableViewerColumnListino.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				ListinoCorriere listino = (ListinoCorriere) element;
				String nome = listino.getNome();
				return nome;
			}
		});
		
		TableColumn tblclmnListino = tableViewerColumnListino.getColumn();
		tblclmnListino.setWidth(100);
		tblclmnListino.setText("Listino");
		
		TableViewerColumn tableViewerColumnDescrizione = new TableViewerColumn(checkboxTableViewerCorrieri, SWT.NONE);
		tableViewerColumnDescrizione.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				ListinoCorriere listino = (ListinoCorriere) element;
				String descrizione = listino.getDescrizione();
				return descrizione;
			}
		});
		
		TableColumn tblclmnDescrizione = tableViewerColumnDescrizione.getColumn();
		tblclmnDescrizione.setWidth(100);
		tblclmnDescrizione.setText("Descrizione");
		
		Label lblSelezionaIListiniSimulazione = new Label(container, SWT.NONE);
		lblSelezionaIListiniSimulazione.setText("Seleziona i listini di simulazione con cui realizzare il preventivo: ");
		
		checkboxTableViewerSimulazione = CheckboxTableViewer.newCheckList(container, SWT.BORDER | SWT.FULL_SELECTION);
		checkboxTableViewerSimulazione.setContentProvider(ArrayContentProvider.getInstance());
		
		tableListiniSimulazione = checkboxTableViewerSimulazione.getTable();
		tableListiniSimulazione.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				validate();
			}
		});
		tableListiniSimulazione.setToolTipText("Qui puoi selezionare su quali listini di simulazione realizzare il preventivo.");
		tableListiniSimulazione.setLinesVisible(true);
		tableListiniSimulazione.setHeaderVisible(true);
		GridData layoutTabellaListiniSimulazione = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		layoutTabellaListiniSimulazione.heightHint = ALTEZZA_TABELLE;
		tableListiniSimulazione.setLayoutData(layoutTabellaListiniSimulazione);
		
		TableViewerColumn tableViewerColumnListinoS = new TableViewerColumn(checkboxTableViewerSimulazione, SWT.NONE);
		tableViewerColumnListinoS.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				ListinoSimulazione listino = (ListinoSimulazione) element;
				String nome = listino.getNome();
				return nome;
			}
		});
		
		TableColumn tblclmnListinoS = tableViewerColumnListinoS.getColumn();
		tblclmnListinoS.setWidth(100);
		tblclmnListinoS.setText("Listino");
		
		TableViewerColumn tableViewerColumnDescrizioneS = new TableViewerColumn(checkboxTableViewerSimulazione, SWT.NONE);
		tableViewerColumnDescrizioneS.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				ListinoSimulazione listino = (ListinoSimulazione) element;
				String descrizione = listino.getDescrizione();
				return descrizione;
			}
		});
		
		TableColumn tblclmnDescrizioneS = tableViewerColumnDescrizioneS.getColumn();
		tblclmnDescrizioneS.setWidth(100);
		tblclmnDescrizioneS.setText("Descrizione");
		
		aggiornaTabellaClienti();
		aggiornaTabellaCorrieri();
		aggiornaTabellaSimulazione();
	}

	@Override
	public void copyDataToModel() {
		setListiniCorriere();
		setListiniCliente();
		setListiniSimulazione();
	}
}
