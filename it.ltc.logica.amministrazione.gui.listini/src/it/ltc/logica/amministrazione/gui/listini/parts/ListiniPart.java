 
package it.ltc.logica.amministrazione.gui.listini.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.amministrazione.gui.listini.elements.TabellaListiniClienti;
import it.ltc.logica.amministrazione.gui.listini.elements.TabellaVociListinoClienti;
import it.ltc.logica.amministrazione.gui.listini.elements.ToolbarListiniClienti;
import it.ltc.logica.amministrazione.gui.listini.elements.ToolbarVociListinoClienti;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;

public class ListiniPart {
	
	public static final String partID = "it.ltc.logica.amministrazione.gui.listini.part.listini";
	
	private TabellaListiniClienti viewerListini;
	private Table tableListini;
	
	private TabellaVociListinoClienti viewerVociDiListino;
	private Table tableVociDiListino;
	private ToolbarVociListinoClienti toolbarVoci;

	private Composite compositeListini;
	private Composite compositeVoci;
	
	@Inject
	public ListiniPart() {}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite compositeClienti = new Composite(parent, SWT.NONE);
		compositeClienti.setLayout(new FillLayout(SWT.VERTICAL));
		compositeClienti.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		compositeListini = new Composite(compositeClienti, SWT.NONE);
		compositeListini.setLayout(new GridLayout(1, false));
		
		ToolbarListiniClienti toolbarListini = new ToolbarListiniClienti(compositeListini);
		
		viewerListini = new TabellaListiniClienti(compositeListini);
		
		tableListini = viewerListini.getTable();
		tableListini.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));	
		tableListini.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ListinoCommessa listinoSelezionato = viewerListini.getRigaSelezionata();
				viewerVociDiListino.setListino(listinoSelezionato);
				toolbarVoci.abilita(listinoSelezionato != null);
			}
		});
		
		toolbarListini.setTabella(viewerListini);
		
		compositeVoci = new Composite(compositeClienti, SWT.NONE);
		compositeVoci.setLayout(new GridLayout(1, false));
		
		toolbarVoci = new ToolbarVociListinoClienti(compositeVoci);
		toolbarVoci.abilita(false);
		
		viewerVociDiListino = new TabellaVociListinoClienti(compositeVoci);
		
		tableVociDiListino = viewerVociDiListino.getTable();
		tableVociDiListino.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbarVoci.setTabella(viewerVociDiListino);
		viewerListini.setTabellaVoci(viewerVociDiListino);
		
	}
	
}