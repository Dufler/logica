 
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

import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.trasporti.gui.listini.elements.simulazione.TabellaListiniSimulazione;
import it.ltc.logica.trasporti.gui.listini.elements.simulazione.ToolbarListiniSimulazione;
import it.ltc.logica.trasporti.gui.listini.elements.simulazione.voci.TabellaVociListinoSimulazione;
import it.ltc.logica.trasporti.gui.listini.elements.simulazione.voci.ToolbarVociListiniSimulazione;

public class ListiniSimulazionePart {
	
	public static final String partID = "it.ltc.logica.trasporti.gui.listini.part.listinisimulazione";
	
	private TabellaListiniSimulazione viewerListini;
	private Table tableListini;
	
	private TabellaVociListinoSimulazione viewerVociDiListino;
	private Table tableVociDiListino;
	
	private Composite compositeNuoviListini;
	private Composite compositeListini;
	private Composite compositeVoci;
	
	private ToolbarListiniSimulazione toolbarListini;
	private ToolbarVociListiniSimulazione toolbarVoci;
	
	@Inject
	public ListiniSimulazionePart() {}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite compositeClienti = new Composite(parent, SWT.NONE);
		compositeClienti.setLayout(new FillLayout(SWT.VERTICAL));
		compositeClienti.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		compositeListini = new Composite(compositeClienti, SWT.NONE);
		compositeListini.setLayout(new GridLayout(1, false));
		
		compositeNuoviListini = new Composite(compositeListini, SWT.NONE);
		compositeNuoviListini.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeNuoviListini.setLayout(new GridLayout(2, false));
		
		toolbarListini = new ToolbarListiniSimulazione(compositeListini);
		
		viewerListini = new TabellaListiniSimulazione(compositeListini);
		
		tableListini = viewerListini.getTable();
		tableListini.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));	
		tableListini.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ListinoSimulazione listinoSelezionato  = viewerListini.getRigaSelezionata();
				viewerVociDiListino.setListino(listinoSelezionato);
				toolbarVoci.abilita(listinoSelezionato != null);
			}
		});
		
		toolbarListini.setTabella(viewerListini);
		
		compositeVoci = new Composite(compositeClienti, SWT.NONE);
		compositeVoci.setLayout(new GridLayout(1, false));
		
		toolbarVoci = new ToolbarVociListiniSimulazione(compositeVoci);
		toolbarVoci.abilita(false);
		
		viewerVociDiListino = new TabellaVociListinoSimulazione(compositeVoci);
		
		tableVociDiListino = viewerVociDiListino.getTable();
		tableVociDiListino.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbarVoci.setTabella(viewerVociDiListino);
		viewerListini.setTabella(viewerVociDiListino);
		
	}
	
}