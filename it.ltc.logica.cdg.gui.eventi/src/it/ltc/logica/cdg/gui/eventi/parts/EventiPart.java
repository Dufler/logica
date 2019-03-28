 
package it.ltc.logica.cdg.gui.eventi.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.cdg.gui.eventi.elements.abbinamentoeventi.TabellaCommessaEventi;
import it.ltc.logica.cdg.gui.eventi.elements.abbinamentoeventi.ToolbarCommessaEventi;
import it.ltc.logica.cdg.gui.eventi.elements.eventi.TabellaCdgEventi;
import it.ltc.logica.cdg.gui.eventi.elements.eventi.ToolbarEventi;
import it.ltc.logica.cdg.gui.eventi.elements.fasi.TabellaFasi;

public class EventiPart {
	
	private TabellaCdgEventi tabellaEventi;
	private TabellaCommessaEventi tabellaAbbinamenti;
	private TabellaFasi tabellaFasi;
	
	@Inject
	public EventiPart() {}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		//Tabs
		
		CTabFolder tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmEventi = new CTabItem(tabFolder, SWT.NONE);
		tbtmEventi.setText("Eventi");
		
		Composite compositeEventi = new Composite(tabFolder, SWT.NONE);
		tbtmEventi.setControl(compositeEventi);
		compositeEventi.setLayout(new GridLayout(1, false));
		
		CTabItem tbtmAbbinamentoEventocommessa = new CTabItem(tabFolder, SWT.NONE);
		tbtmAbbinamentoEventocommessa.setText("Abbinamento Evento-Commessa");
		
		Composite compositeAbbinamenti = new Composite(tabFolder, SWT.NONE);
		tbtmAbbinamentoEventocommessa.setControl(compositeAbbinamenti);
		compositeAbbinamenti.setLayout(new GridLayout(1, false));
		
		CTabItem tbtmFasiLogistiche = new CTabItem(tabFolder, SWT.NONE);
		tbtmFasiLogistiche.setText("Fasi Logistiche");
		
		Composite compositeFasi = new Composite(tabFolder, SWT.NONE);
		tbtmFasiLogistiche.setControl(compositeFasi);
		compositeFasi.setLayout(new GridLayout(1, false));
		
		//Tabelle
		
		ToolbarEventi toolbarEventi = new ToolbarEventi(compositeEventi);
		
		tabellaEventi = new TabellaCdgEventi(compositeEventi);
		Table tableEventi = tabellaEventi.getTable();
		tableEventi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabellaEventi.aggiornaContenuto();
		
		toolbarEventi.setTabella(tabellaEventi);
		
		ToolbarCommessaEventi toolbarAbbinamenti = new ToolbarCommessaEventi(compositeAbbinamenti);
		
		tabellaAbbinamenti = new TabellaCommessaEventi(compositeAbbinamenti);
		Table tableAbbinamenti = tabellaAbbinamenti.getTable();
		tableAbbinamenti.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabellaAbbinamenti.aggiornaContenuto();
		
		toolbarAbbinamenti.setTabella(tabellaAbbinamenti);
		
		tabellaFasi = new TabellaFasi(compositeFasi);
		Table tableFasi = tabellaFasi.getTable();
		tableFasi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabellaFasi.aggiornaContenuto();
		
		tabFolder.setSelection(0);
	}	
	
}