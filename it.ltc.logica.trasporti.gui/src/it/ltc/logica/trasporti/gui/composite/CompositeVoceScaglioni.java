package it.ltc.logica.trasporti.gui.composite;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import it.ltc.logica.common.calcolo.algoritmi.AlgoritmoScaglioni;
import it.ltc.logica.common.calcolo.algoritmi.Scaglione;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.trasporti.calcolo.algoritmi.StrategiaScaglioniPerTrasporti;

public class CompositeVoceScaglioni extends Gruppo {
	
	private static final String titolo = "Valori dei scaglioni";
	
	private ComboBox<StrategiaScaglioniPerTrasporti> comboTipo;
	
	private Composite composite;
	private CompositeNuovoScaglione compositeNuovoScaglione;
	private Button btnInserisci;
	
	private int maxDecimali;
	
	private TableViewer viewer;
	private Table table;
	private TableViewerColumn tableViewerColumnDa;
	private TableViewerColumn tableViewerColumnA;
	private TableViewerColumn tableViewerColumnValore;
	private ArrayList<Scaglione> listaScaglioni;

	public CompositeVoceScaglioni(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, titolo);
	}
	
	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(1, false));
		
		listaScaglioni = new ArrayList<Scaglione>();
		
		Label lblCosaMisuranoGli = new Label(this, SWT.NONE);
		lblCosaMisuranoGli.setText("Cosa misurano gli scaglioni? ");
		
		comboTipo = new ComboBox<StrategiaScaglioniPerTrasporti>(this);
		comboTipo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dirty = true;
				setTipoScaglioni();
				viewer.refresh();
			}
		});
		comboTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTipo.setItems(StrategiaScaglioniPerTrasporti.values());
		
		composite = new Composite(this, SWT.NONE);
		
		composite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		composite.setLayout(new GridLayout(2, false));
		
		compositeNuovoScaglione = new CompositeNuovoScaglione(this, composite, this);
		compositeNuovoScaglione.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		btnInserisci = new Button(composite, SWT.NONE);
		btnInserisci.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dirty = true;
				//Recupero i valori inseriti
				StrategiaScaglioniPerTrasporti tipo = comboTipo.getSelectedValue();
				Double inizio = compositeNuovoScaglione.getDa();
				Double fine = compositeNuovoScaglione.getA();
				Double valore = compositeNuovoScaglione.getValore();
				//Aggiungo il nuovo scaglione alla lista
				Scaglione nuovoScaglione = new Scaglione();
				nuovoScaglione.setTipo(tipo.getCodifica());
				nuovoScaglione.setInizio(inizio);
				nuovoScaglione.setFine(fine);
				nuovoScaglione.setValore(valore);
				listaScaglioni.add(nuovoScaglione);
				//Aggiungo il nuovo scaglione alla tabella
				viewer.setInput(listaScaglioni);
				//Resetto la gui e valido
				compositeNuovoScaglione.resetValues();
				//Reinoltro la validazione
				validate();
			}
		});
		btnInserisci.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, true, false, 1, 1));
		btnInserisci.setText("Inserisci");
		btnInserisci.setEnabled(false);
		
		viewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setComparator(new ViewerComparator() {
			public int compare(Viewer viewer, Object e1, Object e2) {
				Scaglione s1 = (Scaglione) e1;
				Scaglione s2 = (Scaglione) e2;
				return s1.compareTo(s2);
			}
		});
		
		table = viewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Menu menuPopupTabella = new Menu(table);
	    table.setMenu(menuPopupTabella);
	    MenuItem delete = new MenuItem(menuPopupTabella, SWT.PUSH);
	    delete.setText("Elimina");
	    delete.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		int selectionIndex = table.getSelectionIndex();
	    		if (selectionIndex != -1) {
	    			listaScaglioni.remove(selectionIndex);
		            viewer.refresh();
		            validate();
		            dirty = true;
	    		}
	    	}
	    });
		
	    tableViewerColumnDa = new TableViewerColumn(viewer, SWT.NONE);
	    tableViewerColumnDa.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				Scaglione scaglione = (Scaglione) element;
				String inizio = scaglione.getInizioStringa();
				return inizio;
			}
		});
		TableColumn tblclmnDa = tableViewerColumnDa.getColumn();
		tblclmnDa.setWidth(100);
		tblclmnDa.setText("Da: ");
		
		tableViewerColumnA = new TableViewerColumn(viewer, SWT.NONE);
	    tableViewerColumnA.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				Scaglione scaglione = (Scaglione) element;
				String fine = scaglione.getFineStringa();
				return fine;
			}
		});
		TableColumn tblclmnA = tableViewerColumnA.getColumn();
		tblclmnA.setWidth(100);
		tblclmnA.setText("A: ");
		
		tableViewerColumnValore = new TableViewerColumn(viewer, SWT.NONE);
		tableViewerColumnValore.setEditingSupport(new ScaglioneEditingSupport(viewer, 2) {
			@Override
			protected void checkAndEdit(Scaglione scaglione, double valore) {
				scaglione.setValore(valore);
				dirty = true;
			}

			@Override
			protected Object getValue(Object element) {
				Scaglione scaglione = (Scaglione) element;
				return scaglione.getValoreStringa();
			}
		});
	    tableViewerColumnValore.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				Scaglione scaglione = (Scaglione) element;
				String valore = scaglione.getValoreStringa();
				return valore;
			}
		});
		TableColumn tblclmnValore = tableViewerColumnValore.getColumn();
		tblclmnValore.setWidth(100);
		tblclmnValore.setText("Valore: ");
	}
	
	private void setTipoScaglioni() {
		int selectedIndex = comboTipo.getSelectionIndex();
		boolean abilita = (selectedIndex != -1);
		compositeNuovoScaglione.enableElement(abilita);
		if (abilita) {
			StrategiaScaglioniPerTrasporti as = comboTipo.getSelectedValue();
			for (Scaglione scaglione : listaScaglioni) {
				scaglione.setTipo(as.getCodifica());
			}
			maxDecimali = AlgoritmoScaglioni.getMaxDecimali(as);
			compositeNuovoScaglione.setMaxDecimali(maxDecimali);
			tableViewerColumnDa.setEditingSupport(new ScaglioneEditingSupport(viewer, maxDecimali) {
				@Override
				protected void checkAndEdit(Scaglione scaglione, double valore) {
					if (valore < scaglione.getFine()) {
						scaglione.setInizio(valore);
						dirty = true;
					}					
				}

				@Override
				protected Object getValue(Object element) {
					Scaglione scaglione = (Scaglione) element;
					return scaglione.getInizioStringa();
				}
			});
			tableViewerColumnA.setEditingSupport(new ScaglioneEditingSupport(viewer, maxDecimali) {
				@Override
				protected void checkAndEdit(Scaglione scaglione, double valore) {
					if (valore > scaglione.getInizio()) {
						scaglione.setFine(valore);
						dirty = true;
					}
				}

				@Override
				protected Object getValue(Object element) {
					Scaglione scaglione = (Scaglione) element;
					return scaglione.getFineStringa();
				}
			});
		}
	}
	
	public void setInseribile(boolean inseribile) {
		boolean algoritmoSelezionato = (comboTipo.getSelectionIndex() != -1);
		btnInserisci.setEnabled(inseribile && algoritmoSelezionato);
	}
	
	public String getTipo() {
		return comboTipo.getSelectedValue().getCodifica();
	}
	
	public void setTipo(String tipo) {
		StrategiaScaglioniPerTrasporti algoritmo = StrategiaScaglioniPerTrasporti.valueOf(tipo);
		comboTipo.setSelectedValue(algoritmo);
		setTipoScaglioni();
	}
	
	public ArrayList<Scaglione> getScaglioni() {
		return listaScaglioni;
	}
	
	public void setScaglioni(ArrayList<Scaglione> lista) {
		listaScaglioni = lista;
		viewer.setInput(listaScaglioni);
		validate();
	}
	
	public void abilitaInserimento(boolean abilita) {
		compositeNuovoScaglione.enableElement(abilita);
	}

	public void aggiungiScaglione(Scaglione scaglione) {
		listaScaglioni.add(scaglione);
		//Aggiungo il nuovo scaglione alla tabella
		viewer.setInput(listaScaglioni);
	}
	
	@Override
	public boolean validate() {
		valid = listaScaglioni.size() > 0;
		forwardValidation();
		return valid;
	}
	
	@Override
	public boolean isDirty() {
		return dirty;
	}
	
	@Override
	public void enableElement(boolean enable) {
		super.enableElement(enable);
		compositeNuovoScaglione.enableElement(enable);
		table.setEnabled(enable);
	}

}
