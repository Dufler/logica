 
package it.ltc.logica.cdg.gui.costiricavi.parts;

import javax.inject.Inject;
import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.costiricavi.elements.TabellaPezziCostiRicavi;
import it.ltc.logica.cdg.gui.costiricavi.elements.ToolbarPezziCostiRicavi;
import it.ltc.logica.cdg.gui.costiricavi.elements.commessa.TabellaCostiRicaviCommesse;
import it.ltc.logica.cdg.gui.costiricavi.elements.commessa.ToolbarCostiRicaviCommesse;
import it.ltc.logica.cdg.gui.costiricavi.elements.generici.TabellaCostiRicaviGenerici;
import it.ltc.logica.cdg.gui.costiricavi.elements.generici.ToolbarCostiRicaviGenericiRaggruppati;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.CTabItem;

public class CostiRicaviPart {
	
	private TabellaPezziCostiRicavi tabellaPezzi;
	private TabellaCostiRicaviCommesse tabellaCommesse;
	private TabellaCostiRicaviGenerici tabellaGenerici;
	
	@Inject
	public CostiRicaviPart() {}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		CTabFolder tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		//Pezzo
		CTabItem tbtmPezzo = new CTabItem(tabFolder, SWT.NONE);
		tbtmPezzo.setText("Singolo Pezzo");
		
		Composite compositePezzo = new Composite(tabFolder, SWT.NONE);
		tbtmPezzo.setControl(compositePezzo);
		compositePezzo.setLayout(new GridLayout(1, false));
		
		Label lblCostoERicavo = new Label(compositePezzo, SWT.NONE);
		lblCostoERicavo.setText("Costo e Ricavo per singolo pezzo: ");
		
		ToolbarPezziCostiRicavi toolbar = new ToolbarPezziCostiRicavi(compositePezzo);
		
		tabellaPezzi = new TabellaPezziCostiRicavi(compositePezzo);
		Table tablePezzi = tabellaPezzi.getTable();
		tablePezzi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbar.setTabella(tabellaPezzi);
		
		//Commessa
		CTabItem tbtmCommessa = new CTabItem(tabFolder, SWT.NONE);
		tbtmCommessa.setText("Commessa Specifica");
		
		Composite compositeCommessa = new Composite(tabFolder, SWT.NONE);
		tbtmCommessa.setControl(compositeCommessa);
		compositeCommessa.setLayout(new GridLayout(1, false));
		
		Label lblCostiERicavi = new Label(compositeCommessa, SWT.NONE);
		lblCostiERicavi.setText("Costi e Ricavi per Commessa: ");
		
		ToolbarCostiRicaviCommesse toolbarCommesse = new ToolbarCostiRicaviCommesse(compositeCommessa);
		
		tabellaCommesse = new TabellaCostiRicaviCommesse(compositeCommessa);
		Table tableCommesse = tabellaCommesse.getTable();
		tableCommesse.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbarCommesse.setTabella(tabellaCommesse);
		
		//Sede o Generico
		CTabItem tbtmSedeOAzienda = new CTabItem(tabFolder, SWT.NONE);
		tbtmSedeOAzienda.setText("Sede o Azienda");
		
		Composite compositeGenerici = new Composite(tabFolder, SWT.NONE);
		tbtmSedeOAzienda.setControl(compositeGenerici);
		compositeGenerici.setLayout(new GridLayout(1, false));
		
		ToolbarCostiRicaviGenericiRaggruppati toolbarGenerici = new ToolbarCostiRicaviGenericiRaggruppati(compositeGenerici);
		
		tabellaGenerici = new TabellaCostiRicaviGenerici(compositeGenerici);
		Table tableGenerici = tabellaGenerici.getTable();
		tableGenerici.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbarGenerici.setTabella(tabellaGenerici);
		
		tabFolder.setSelection(0);
	}
}