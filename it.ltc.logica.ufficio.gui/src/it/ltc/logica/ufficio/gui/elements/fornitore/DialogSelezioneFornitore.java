package it.ltc.logica.ufficio.gui.elements.fornitore;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.Fornitore;
import it.ltc.logica.gui.dialog.DialogSelezione;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;

public class DialogSelezioneFornitore extends DialogSelezione<Fornitore> {

	private static final String title = "Seleziona un fornitore";
	
	private Fornitore fornitoreSelezionato;
	
	private final Commessa commessa;
	private Text text;
	
	private TabellaFornitori viewer;
	private Table table;
	
	public DialogSelezioneFornitore(Commessa commessa) {
		super(title);
		this.commessa = commessa;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		Label lblFiltro = new Label(container, SWT.NONE);
		lblFiltro.setText("Filtro di ricerca:");
		
		Composite compositeFiltro = new Composite(container, SWT.NONE);
		compositeFiltro.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		compositeFiltro.setLayout(new GridLayout(3, false));
		
		Label lblRagioneSociale = new Label(compositeFiltro, SWT.NONE);
		lblRagioneSociale.setSize(85, 15);
		lblRagioneSociale.setText("Ragione sociale:");
		
		text = new Text(compositeFiltro, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(compositeFiltro, SWT.NONE);
		text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				filtra();
			}
		});
		
		Composite compositeNuovo = new Composite(container, SWT.NONE);
		compositeNuovo.setLayout(new GridLayout(2, false));
		compositeNuovo.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		
		
		String testoLabelFornitori = "Fornitori";
		if (commessa != null) {
			testoLabelFornitori += " per " + commessa.getNome();
		}
		Label lblFornitori = new Label(compositeNuovo, SWT.NONE);
		lblFornitori.setText(testoLabelFornitori);
		
		Button btnNewButton = new Button(compositeNuovo, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				nuovoFornitore();
			}
		});
		btnNewButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnNewButton.setText("Nuovo Fornitore");
		
		viewer = new TabellaFornitori(container, commessa, false);
		table = viewer.getTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				int selectionIndex = table.getSelectionIndex();
				if (selectionIndex != -1) {
					TableItem selectedItem = table.getItem(selectionIndex);
					fornitoreSelezionato = (Fornitore) selectedItem.getData();
					okPressed();
				}
			}
		});
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = table.getSelectionIndex();
				if (selectionIndex != -1) {
					TableItem selectedItem = table.getItem(selectionIndex);
					fornitoreSelezionato = (Fornitore) selectedItem.getData();
				}
			}
		});
		GridData layoutTabella = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
		layoutTabella.heightHint = 400;
		table.setLayoutData(layoutTabella);
	}

	@Override
	public void caricaModel() {
		viewer.aggiornaContenuto();
	}

	@Override
	public Fornitore getSelezione() {
		return fornitoreSelezionato;
	}
	
	private void nuovoFornitore() {
		DialogFornitore dialog = new DialogFornitore(commessa);
		fornitoreSelezionato = dialog.apri();
		if (fornitoreSelezionato != null)
			okPressed();
	}
	
	private void filtra() {
		CriteriFiltraggioSoloTesto criteri = new CriteriFiltraggioSoloTesto(text.getText());
		viewer.filtra(criteri);
	}

	@Override
	public void checkElementiGrafici() {}
}
