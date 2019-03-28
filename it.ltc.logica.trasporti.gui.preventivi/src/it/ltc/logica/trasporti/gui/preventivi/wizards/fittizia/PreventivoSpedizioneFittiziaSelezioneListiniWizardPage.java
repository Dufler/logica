package it.ltc.logica.trasporti.gui.preventivi.wizards.fittizia;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.common.controller.listini.ControllerListiniCorrieri;
import it.ltc.logica.common.controller.listini.ListiniSimulazioneController;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.controller.preventivi.PreventivoFittiziaController;
import it.ltc.logica.trasporti.gui.preventivi.elements.listini.TabellaCheckboxListini;

public class PreventivoSpedizioneFittiziaSelezioneListiniWizardPage extends PaginaWizard {
	
	private static final String title = "Preventivo di costo e fatturazione per una spedizione";
	private static final String description = "Seleziona i listini da utilizzare.";
	
	private final PreventivoFittiziaController controllerPreventivo;
	private final ControllerListiniCorrieri controllerListiniCorrieri;
	private final ControllerListiniClienti controllerListiniClienti;
	private final ListiniSimulazioneController controllerListiniSimulazione;
	
	private Composite container;
	
	private TabellaCheckboxListini checkboxTableViewerClienti;
	private Table tableListiniClienti;
	
	private TabellaCheckboxListini checkboxTableViewerCorrieri;
	private Table tableListiniCorrieri;
	
	private TabellaCheckboxListini checkboxTableViewerSimulazione;
	private Table tableListiniSimulazione;
	
	private boolean canFlip;

	public PreventivoSpedizioneFittiziaSelezioneListiniWizardPage() {
		super(title, description);
		controllerPreventivo = PreventivoFittiziaController.getInstance();
		controllerListiniCorrieri = ControllerListiniCorrieri.getInstance();
		controllerListiniClienti = ControllerListiniClienti.getInstance();
		controllerListiniSimulazione = ListiniSimulazioneController.getInstance();
		canFlip = false;
	}
	
	private void valida() {
		int tableClientiSelected = checkboxTableViewerClienti.getCheckedElements().length;
		int tableCorrieriSelected = checkboxTableViewerCorrieri.getCheckedElements().length;
		int tableSimulazioneSelected = checkboxTableViewerSimulazione.getCheckedElements().length;
		canFlip =  (tableClientiSelected + tableCorrieriSelected + tableSimulazioneSelected) > 0;
		setPageComplete(canFlip);
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return canFlip;
	}
	
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
		checkboxTableViewerClienti.setInput(controllerListiniClienti.getListiniPerTipo(AmbitoFattura.ID_SPEDIZIONI));
		for (TableColumn column :tableListiniClienti.getColumns()) {
			column.pack();
		}
		container.layout();
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
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite compositeClienti = new Composite(container, SWT.NONE);
		compositeClienti.setLayout(new GridLayout(1, false));
		
		Label lblSelezionaLaCommessa = new Label(compositeClienti, SWT.NONE);
		lblSelezionaLaCommessa.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblSelezionaLaCommessa.setText("Seleziona i listini cliente su cui effettuare la simulazione: ");
		
		checkboxTableViewerClienti = new TabellaCheckboxListini(compositeClienti);
		
		tableListiniClienti = checkboxTableViewerClienti.getTable();
		tableListiniClienti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				valida();
			}
		});
		tableListiniClienti.setToolTipText("Qui puoi selezionare su quali listini cliente realizzare il preventivo.");
		tableListiniClienti.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite compositeCorrieri = new Composite(container, SWT.NONE);
		compositeCorrieri.setLayout(new GridLayout(1, false));
		
		Label lblSelezionaIListiniCorrieri = new Label(compositeCorrieri, SWT.NONE);
		lblSelezionaIListiniCorrieri.setText("Seleziona i listini dei corrieri con cui realizzare la simulazione: ");
		
		checkboxTableViewerCorrieri = new TabellaCheckboxListini(compositeCorrieri);
		
		tableListiniCorrieri = checkboxTableViewerCorrieri.getTable();
		tableListiniCorrieri.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				valida();
			}
		});
		tableListiniCorrieri.setToolTipText("Qui puoi selezionare su quali listini corriere realizzare il preventivo.");
		tableListiniCorrieri.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite compositeSimulazione = new Composite(container, SWT.NONE);
		compositeSimulazione.setLayout(new GridLayout(1, false));
		
		Label lblSelezionaIListiniSimulazione = new Label(compositeSimulazione, SWT.NONE);
		lblSelezionaIListiniSimulazione.setText("Seleziona i listini di simulazione con cui realizzare la simulazione: ");
		
		
		checkboxTableViewerSimulazione = new TabellaCheckboxListini(compositeSimulazione);
		
		tableListiniSimulazione = checkboxTableViewerSimulazione.getTable();
		tableListiniSimulazione.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				valida();
			}
		});
		tableListiniSimulazione.setToolTipText("Qui puoi selezionare su quali listini di simulazione realizzare il preventivo.");
		tableListiniSimulazione.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
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
