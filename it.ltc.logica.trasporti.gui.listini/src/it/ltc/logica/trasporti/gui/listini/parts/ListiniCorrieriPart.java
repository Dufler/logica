 
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

import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.trasporti.gui.listini.elements.corriere.TabellaListiniCorriere;
import it.ltc.logica.trasporti.gui.listini.elements.corriere.ToolbarListiniCorriere;
import it.ltc.logica.trasporti.gui.listini.elements.corriere.voci.TabellaVociListinoCorrieri;
import it.ltc.logica.trasporti.gui.listini.elements.corriere.voci.ToolbarVociListinoCorriere;

public class ListiniCorrieriPart {
	
	public static final String partID = "it.ltc.logica.trasporti.gui.listini.part.listinicorrieri";
	
	private TabellaListiniCorriere viewerListini;
	private Table tableListini;
	
	private TabellaVociListinoCorrieri viewerVociDiListino;
	private Table tableVociDiListino;

	private Composite compositeListini;
	private Composite compositeVoci;
	
	private ToolbarListiniCorriere toolbarListini;
	private ToolbarVociListinoCorriere toolbarVoci;
	
	@Inject
	public ListiniCorrieriPart() {}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite compositeCorrieri = new Composite(parent, SWT.NONE);
		compositeCorrieri.setLayout(new FillLayout(SWT.VERTICAL));
		compositeCorrieri.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		compositeListini = new Composite(compositeCorrieri, SWT.NONE);
		compositeListini.setLayout(new GridLayout(1, false));
		
		toolbarListini = new ToolbarListiniCorriere(compositeListini);
		
		viewerListini = new TabellaListiniCorriere(compositeListini);
		tableListini = viewerListini.getTable();
		tableListini.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tableListini.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ListinoCorriere listinoSelezionato = viewerListini.getRigaSelezionata();
				viewerVociDiListino.setListino(listinoSelezionato);
				toolbarVoci.abilita(listinoSelezionato != null);
			}
		});
		
		toolbarListini.setTabella(viewerListini);
		
		compositeVoci = new Composite(compositeCorrieri, SWT.NONE);
		compositeVoci.setLayout(new GridLayout(1, false));
		
		toolbarVoci = new ToolbarVociListinoCorriere(compositeVoci);
		toolbarVoci.abilita(false);
		
		viewerVociDiListino = new TabellaVociListinoCorrieri(compositeVoci);
		tableVociDiListino = viewerVociDiListino.getTable();
		tableVociDiListino.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbarVoci.setTabella(viewerVociDiListino);
		viewerListini.setTabellaVoci(viewerVociDiListino);
	}
	
}