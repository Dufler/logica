package it.ltc.logica.ufficio.gui.elements.prodotto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.common.controller.sistema.ControllerCategorieMerceologichePerCliente;
import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Prodotto;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.ufficio.gui.elements.prodotto.movimenti.TabellaMovimentiProdotto;
import it.ltc.logica.ufficio.gui.elements.prodotto.movimenti.ToolbarMovimentiProdotto;
import it.ltc.logica.ufficio.gui.elements.prodotto.saldo.TabellaSaldoProdotto;
import org.eclipse.swt.layout.FillLayout;

public class DialogProdotto extends DialogModel<Prodotto> {

	private static final String title = "Prodotto";
	
	private final Commessa commessa;
	private final ControllerProdotti controller;
	
	private CTabFolder tabFolder;
	private CompositeProdotto composite;
	private TabellaSaldoProdotto tabellaSaldi;
	private TabellaMovimentiProdotto tabellaMovimenti;
	
	public DialogProdotto(Commessa commessa, Prodotto value, ControllerProdotti controller) {
		super(title, value);
		this.commessa = commessa;
		this.controller = controller; //new ControllerProdotti(commessa);
		//Imposto la tabella
		//tabellaSaldi.setProdotto(valore);
		//tabellaSaldi.setController(controller);
	}

	@Override
	public void loadModel() {
		composite.setSKU(valore.getChiaveCliente());
		composite.setModello(valore.getCodiceModello());
		composite.setBarcode(valore.getBarcode());
		composite.setTaglia(valore.getTaglia());
		composite.setColore(valore.getColore());
		composite.setDescrizione(valore.getDescrizione());
		composite.setComposizione(valore.getComposizione());
		composite.setBrand(valore.getBrand());
		composite.setStagione(valore.getStagione());
		composite.setCategoria(ControllerCategorieMerceologichePerCliente.getInstance().getCategoria(commessa, valore.getCategoria()));
		composite.setMadeIn(ControllerNazioni.getInstance().getNazione(valore.getMadeIn()));
		composite.setCassa(valore.getCassa());
		composite.lockNonUpdatableElements();
	}

	@Override
	public void prefillModel() {
		composite.setMadeIn(ControllerNazioni.getInstance().getDefault());
		valore.setCommessa(commessa.getId());
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		String stagione = "CO" + sdf.format(now);
		composite.setStagione(stagione);
	}

	@Override
	public void copyDataToModel() {
		valore.setChiaveCliente(composite.getSKU());
		valore.setCodiceModello(composite.getModello());
		valore.setBarcode(composite.getBarcode());
		valore.setTaglia(composite.getTaglia());
		valore.setColore(composite.getColore());
		valore.setDescrizione(composite.getDescrizione());
		valore.setComposizione(composite.getComposizione());
		valore.setBrand(composite.getBrand());
		valore.setStagione(composite.getStagione());
		valore.setCategoria(composite.getCategoria().getNome());
		valore.setMadeIn(composite.getMadeIn() != null ? composite.getMadeIn().getCodiceIsoTre() : null);
		valore.setCassa(composite.getCassa());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = controller.aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = controller.inserisci(valore);
		return insert;
	}

	@Override
	public Prodotto createNewModel() {
		Prodotto prodotto = new Prodotto();
		return prodotto;
	}

	@Override
	public boolean isDirty() {
		boolean dirty = composite.isDirty();
		return dirty;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));

		tabFolder = new CTabFolder(container, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmDatiProdotto = new CTabItem(tabFolder, SWT.NONE);
		tbtmDatiProdotto.setText("Prodotto");

		Composite compositeDatiProdotto = new Composite(tabFolder, SWT.NONE);
		compositeDatiProdotto.setLayout(new GridLayout(1, false));
		
		composite = new CompositeProdotto(this, compositeDatiProdotto);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		ComboBox<CategoriaMerceologica> combo = composite.getComboCategoria();
		combo.setItems(ControllerCategorieMerceologichePerCliente.getInstance().getCategorie(commessa));
		
		tbtmDatiProdotto.setControl(compositeDatiProdotto);
		
		CTabItem tbtmMovimentiProdotto = new CTabItem(tabFolder, SWT.NONE);
		tbtmMovimentiProdotto.setText("Saldi e Movimenti");
		
		Composite compositeTab = new Composite(tabFolder, SWT.NONE);
		compositeTab.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite compositeMovimentiProdotto = new Composite(compositeTab, SWT.NONE);
		compositeMovimentiProdotto.setLayout(new GridLayout(1, false));
		
		Label lblSaldi = new Label(compositeMovimentiProdotto, SWT.NONE);
		lblSaldi.setText("Saldi");
		
		tabellaSaldi = new TabellaSaldoProdotto(compositeMovimentiProdotto);
		tabellaSaldi.setProdotto(valore);
		tabellaSaldi.setController(controller);			
		
		Table tableSaldi = tabellaSaldi.getTable();
		GridData layoutTabellaSaldi = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
		layoutTabellaSaldi.heightHint = 50;
		tableSaldi.setLayoutData(layoutTabellaSaldi);
		
		Composite compositeControlliMovimenti = new Composite(compositeMovimentiProdotto, SWT.NONE);
		compositeControlliMovimenti.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		compositeControlliMovimenti.setLayout(new GridLayout(1, false));
		
		Label lblMovimenti = new Label(compositeControlliMovimenti, SWT.NONE);
		lblMovimenti.setText("Movimenti");
		
		ToolbarMovimentiProdotto toolbarMovimenti = new ToolbarMovimentiProdotto(compositeControlliMovimenti);
		toolbarMovimenti.setCommessa(commessa);
		
		tabellaMovimenti = new TabellaMovimentiProdotto(compositeMovimentiProdotto, commessa, valore, controller);		
		tabellaMovimenti.setTabellaSaldoProdotto(tabellaSaldi);
		
		toolbarMovimenti.setTabella(tabellaMovimenti);
		
		Table tableMovimenti = tabellaMovimenti.getTable();
		GridData layoutTabellaMovimenti = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
		layoutTabellaMovimenti.heightHint = 160;
		tableMovimenti.setLayoutData(layoutTabellaMovimenti);
		
		//Se sto andando in modifica scarico saldi e movimenti altrimenti disabilito la toolbar e le tabelle.
		if (modify) {
			tabellaSaldi.aggiornaContenuto();
			tabellaMovimenti.aggiornaContenuto();
		} else {
			toolbarMovimenti.abilita(false);
			tabellaMovimenti.abilitaComandiMenu(false);
		}
		
		tbtmMovimentiProdotto.setControl(compositeTab);
		
		tabFolder.setSelection(0);
	}

}
