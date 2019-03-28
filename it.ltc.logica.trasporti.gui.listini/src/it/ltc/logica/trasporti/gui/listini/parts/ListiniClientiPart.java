 
package it.ltc.logica.trasporti.gui.listini.parts;

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

import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.trasporti.gui.listini.elements.cliente.TabellaListiniClienti;
import it.ltc.logica.trasporti.gui.listini.elements.cliente.ToolbarListiniClienti;
import it.ltc.logica.trasporti.gui.listini.elements.cliente.voci.TabellaVociListinoClienti;
import it.ltc.logica.trasporti.gui.listini.elements.cliente.voci.ToolbarVociListinoClienti;

public class ListiniClientiPart {
	
	public static final String partID = "it.ltc.logica.trasporti.gui.listini.part.listinicliente";
	
	private TabellaListiniClienti viewerListini;
	private Table tableListini;
	
	private TabellaVociListinoClienti viewerVociDiListino;
	private Table tableVociDiListino;

	private Composite compositeListini;
	private Composite compositeVoci;
	
	private ToolbarListiniClienti toolbarListini;
	private ToolbarVociListinoClienti toolbarVoci;

	@Inject
	public ListiniClientiPart() {}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite compositeClienti = new Composite(parent, SWT.NONE);
		compositeClienti.setLayout(new FillLayout(SWT.VERTICAL));
		compositeClienti.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		compositeListini = new Composite(compositeClienti, SWT.NONE);
		compositeListini.setLayout(new GridLayout(1, false));
		
		toolbarListini = new ToolbarListiniClienti(compositeListini);
			
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