 
package it.ltc.logica.admin.gui.parts;

import javax.inject.Inject;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import it.ltc.logica.admin.gui.logic.ControllerUtenti;
import it.ltc.logica.admin.gui.logic.PermessiContentProvider;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.sistema.ControllerFeature;
import it.ltc.logica.common.controller.sistema.ControllerPermessi;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Feature;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.utenti.Permesso;
import it.ltc.logica.database.model.centrale.utenti.Utente;
import it.ltc.logica.gui.input.ComboBox;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class GestioneUtentiPart {
	
	public static final String partID = "it.ltc.logica.admin.gui.part.gestioneutenti";
	
	private final ControllerUtenti controller;
	private final ControllerPermessi controllerPermessi;
	private final ControllerCommesse controllerCommesse;
	
	private ComboBox<Utente> comboUtenti;
	
	private CheckboxTableViewer checkboxSedi;
	private Table tableSedi;
	
	private CheckboxTableViewer checkboxCommesse;
	private Table tableCommesse;
	
	private CheckboxTableViewer checkboxFeatures;
	private Table tableFeatures;
	
	private CheckboxTreeViewer checkboxPermessi;
	private Tree treePermessi;
	
	private Button btnAggiornaDettagliUtente;
	
	@Inject
	public GestioneUtentiPart() {
		controller = ControllerUtenti.getInstance();
		controllerPermessi = ControllerPermessi.getInstance();
		controllerCommesse = ControllerCommesse.getInstance();
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		
		Label lblGestioneUtenti = new Label(parent, SWT.NONE);
		lblGestioneUtenti.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		lblGestioneUtenti.setText("Gestione Utenti");
		new Label(parent, SWT.NONE);
		
		Label lblSelezionaUnUtente = new Label(parent, SWT.NONE);
		lblSelezionaUnUtente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				controller.caricaDati();
				comboUtenti.setItems(controller.getUtenti());
			}
		});
		lblSelezionaUnUtente.setText("seleziona un utente:");
		lblSelezionaUnUtente.setToolTipText("Fai doppio click qui per aggiornare la lista degli utenti.");
		new Label(parent, SWT.NONE);
		
		comboUtenti = new ComboBox<Utente>(parent);
		comboUtenti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selezionaUtente();
			}
		});
		comboUtenti.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		comboUtenti.setItems(controller.getUtenti());
		new Label(parent, SWT.NONE);
		
		Label lblSedi = new Label(parent, SWT.NONE);
		lblSedi.setText("Sedi");
		
		Label lblCommesse = new Label(parent, SWT.NONE);
		lblCommesse.setText("Commesse");
		
		checkboxSedi = CheckboxTableViewer.newCheckList(parent, SWT.BORDER | SWT.FULL_SELECTION);
		checkboxSedi.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				btnAggiornaDettagliUtente.setEnabled(true);
			}
		});
		checkboxSedi.setContentProvider(ArrayContentProvider.getInstance());
		tableSedi = checkboxSedi.getTable();
		tableSedi.setHeaderVisible(true);
		tableSedi.setLinesVisible(true);
		tableSedi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		TableViewerColumn colonnaSedi = new TableViewerColumn(checkboxSedi, SWT.NONE);
		colonnaSedi.setLabelProvider(new ColumnLabelProvider());
		TableColumn tblclmnSede = colonnaSedi.getColumn();
		tblclmnSede.setWidth(350);
		tblclmnSede.setText("Sede");
		
		checkboxSedi.setInput(ControllerSedi.getInstance().getSedi());
		
		checkboxCommesse = CheckboxTableViewer.newCheckList(parent, SWT.BORDER | SWT.FULL_SELECTION);
		checkboxCommesse.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				btnAggiornaDettagliUtente.setEnabled(true);
			}
		});
		checkboxCommesse.setContentProvider(ArrayContentProvider.getInstance());
		tableCommesse = checkboxCommesse.getTable();
		tableCommesse.setLinesVisible(true);
		tableCommesse.setHeaderVisible(true);
		tableCommesse.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableViewerColumn colonnaCommesse = new TableViewerColumn(checkboxCommesse, SWT.NONE);
		colonnaCommesse.setLabelProvider(new ColumnLabelProvider());
		TableColumn tblclmnNome = colonnaCommesse.getColumn();
		tblclmnNome.setWidth(350);
		tblclmnNome.setText("Nome");
		
		checkboxCommesse.setInput(ControllerCommesse.getInstance().getTutteCommesse());
		
		Label lblFeatures = new Label(parent, SWT.NONE);
		lblFeatures.setText("Features");
		
		Label lblPermessi = new Label(parent, SWT.NONE);
		lblPermessi.setText("Permessi");
		
		checkboxFeatures = CheckboxTableViewer.newCheckList(parent, SWT.BORDER | SWT.FULL_SELECTION);
		checkboxFeatures.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				btnAggiornaDettagliUtente.setEnabled(true);
			}
		});
		checkboxFeatures.setContentProvider(ArrayContentProvider.getInstance());
		tableFeatures = checkboxFeatures.getTable();
		tableFeatures.setLinesVisible(true);
		tableFeatures.setHeaderVisible(true);
		tableFeatures.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		TableViewerColumn colonnaFeature = new TableViewerColumn(checkboxFeatures, SWT.NONE);
		colonnaFeature.setLabelProvider(new ColumnLabelProvider());
		TableColumn tblclmnFeature = colonnaFeature.getColumn();
		tblclmnFeature.setWidth(350);
		tblclmnFeature.setText("Nome");
		
		checkboxFeatures.setInput(ControllerFeature.getInstance().getFeatures());
		
		checkboxPermessi = new CheckboxTreeViewer(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		checkboxPermessi.setAutoExpandLevel(CheckboxTreeViewer.ALL_LEVELS);
		checkboxPermessi.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				btnAggiornaDettagliUtente.setEnabled(true);
			}
		});
		checkboxPermessi.setContentProvider(new PermessiContentProvider());
		treePermessi = checkboxPermessi.getTree();
		treePermessi.setLinesVisible(true);
		treePermessi.setHeaderVisible(true);
		treePermessi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TreeViewerColumn treeViewerColumn = new TreeViewerColumn(checkboxPermessi, SWT.NONE);
		treeViewerColumn.setLabelProvider(new ColumnLabelProvider());
		TreeColumn trclmnPermesso = treeViewerColumn.getColumn();
		trclmnPermesso.setWidth(350);
		trclmnPermesso.setText("Permesso");
		
		btnAggiornaDettagliUtente = new Button(parent, SWT.NONE);
		btnAggiornaDettagliUtente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				aggiornaDettagliUtente();
			}
		});
		btnAggiornaDettagliUtente.setText("Aggiorna Dettagli Utente");
		btnAggiornaDettagliUtente.setEnabled(false);
		
		new Label(parent, SWT.NONE);
		
		checkboxPermessi.setInput(controllerPermessi.getPermessiAdAlbero());
		
	}
	
	private void selezionaUtente() {
		Utente user = comboUtenti.getSelectedValue();
		if (user != null) {
			//Sedi
			checkboxSedi.setAllChecked(false);
			if (user.getSedi() != null)
			for (Integer idSede : user.getSedi()) {
				Sede sede = ControllerSedi.getInstance().getSede(idSede);
				checkboxSedi.setChecked(sede, true);
			}
			//Commesse
			checkboxCommesse.setAllChecked(false);
			if (user.getCommesse() != null)
			for (Integer idCommessa : user.getCommesse()) {
				Commessa commessa = controllerCommesse.getCommessa(idCommessa);
				checkboxCommesse.setChecked(commessa, true);
			}
			//Feature
			checkboxFeatures.setAllChecked(false);
			if (user.getFeatures() != null)
			for (String nome : user.getFeatures()) {
				Feature feature = ControllerFeature.getInstance().getFeatureDaID(nome);
				checkboxFeatures.setChecked(feature, true);
			}
			//Permessi
			//Reset dell'albero
			for (Permesso permesso : ControllerPermessi.getInstance().getPermessi()) {
				checkboxPermessi.setChecked(permesso, false);
			}
			//Impostazione degli attuali permessi
			if (user.getPermessi() != null)
			for (Integer idPermesso : user.getPermessi()) {
				Permesso permesso = ControllerPermessi.getInstance().getPermesso(idPermesso);
				checkboxPermessi.setChecked(permesso, true);
			}
		}
	}
	
	private void aggiornaDettagliUtente() {
		int selectionIndex = comboUtenti.getSelectionIndex();
		if (selectionIndex != -1) {
			Utente utente = comboUtenti.getSelectedValue();
			//Sedi
			Set<Integer> sediSelezionate = new HashSet<Integer>();
			for (Object o : checkboxSedi.getCheckedElements()) {
				Sede sede = (Sede) o;
				sediSelezionate.add(sede.getId());
			}
			//Commesse
			Set<Integer> commesseSelezionate = new HashSet<Integer>();
			for (Object o : checkboxCommesse.getCheckedElements()) {
				Commessa commessa = (Commessa) o;
				commesseSelezionate.add(commessa.getId());
			}
			//Permessi
			Set<Integer> permessiSelezionati = new HashSet<Integer>();
			for (Object o : checkboxPermessi.getCheckedElements()) {
				Permesso permesso = (Permesso) o;
				permessiSelezionati.add(permesso.getId());
			}
			//Features
			Set<String> featureSelezionate = new HashSet<String>();
			for (Object o : checkboxFeatures.getCheckedElements()) {
				Feature feature = (Feature) o;
				featureSelezionate.add(feature.getFeatureid());
			}
			controller.aggiornaDettagliUtente(utente, sediSelezionate, featureSelezionate, commesseSelezionate, permessiSelezionati);
			btnAggiornaDettagliUtente.setEnabled(false);
		}
	}
	
}