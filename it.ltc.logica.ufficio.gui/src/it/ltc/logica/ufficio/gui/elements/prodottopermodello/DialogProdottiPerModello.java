package it.ltc.logica.ufficio.gui.elements.prodottopermodello;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import it.ltc.logica.common.controller.prodotti.ProdottiPerModello;
import it.ltc.logica.common.controller.sistema.ControllerCategorieMerceologiche;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.gui.dialog.DialogModel;

public class DialogProdottiPerModello extends DialogModel<ProdottiPerModello> {

	private static final String title = "Prodotti per modello";
	
	private final ControllerCategorieMerceologiche controllerCategorie;
	
	private Text textModello;
	private Text textCategoria;
	private Text textBrand;
	private Text textColore;
	private Text textDescrizione;
	
	private TabellaProdottiConQuantita viewer;
	private Table table;
	
	public DialogProdottiPerModello(ProdottiPerModello value) {
		super(title, value);
		controllerCategorie = ControllerCategorieMerceologiche.getInstance();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		Composite compositeDati = new Composite(container, SWT.NONE);
		compositeDati.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		compositeDati.setLayout(new GridLayout(2, false));
		
		Label lblModello = new Label(compositeDati, SWT.NONE);
		lblModello.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModello.setText("Modello: ");
		
		textModello = new Text(compositeDati, SWT.BORDER);
		textModello.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textModello.setEditable(false);
		
		Label lblCategoria = new Label(compositeDati, SWT.NONE);
		lblCategoria.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCategoria.setText("Categoria: ");
		
		textCategoria = new Text(compositeDati, SWT.BORDER);
		textCategoria.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textCategoria.setEditable(false);
		
		Label lblBrand = new Label(compositeDati, SWT.NONE);
		lblBrand.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBrand.setText("Brand: ");
		
		textBrand = new Text(compositeDati, SWT.BORDER);
		textBrand.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textBrand.setEditable(false);
		
		Label lblColore = new Label(compositeDati, SWT.NONE);
		lblColore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblColore.setText("Colore: ");
		
		textColore = new Text(compositeDati, SWT.BORDER);
		textColore.setEditable(false);
		textColore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDescrizione = new Label(compositeDati, SWT.NONE);
		lblDescrizione.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescrizione.setText("Descrizione: ");
		
		textDescrizione = new Text(compositeDati, SWT.BORDER | SWT.MULTI);
		textDescrizione.setEditable(false);
		textDescrizione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite compositeArticoli = new Composite(container, SWT.NONE);
		compositeArticoli.setLayout(new GridLayout(1, false));
		compositeArticoli.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		
		Label lblArticoli = new Label(compositeArticoli, SWT.NONE);
		lblArticoli.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblArticoli.setText("Articoli:");
		
		viewer = new TabellaProdottiConQuantita(compositeArticoli);
		table = viewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		viewer.setElementi(valore.getProdotti());
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void loadModel() {
		String modello = valore.getCodiceModello() != null ? valore.getCodiceModello() : "-";
		textModello.setText(modello);
		CategoriaMerceologica categoria = controllerCategorie.getCategoria(valore.getCategoriaMerceologica());
		String testoCategoria = categoria != null ? categoria.getNome() : "-";
		textCategoria.setText(testoCategoria);
		String testoBrand = valore.getBrand() != null ? valore.getBrand() : "-";
		textBrand.setText(testoBrand);
		String colore = valore.getColore() != null ? valore.getColore() : "-";
		textColore.setText(colore);
		String descrizione = valore.getDescrizione() != null ? valore.getDescrizione() : "-";
		textDescrizione.setText(descrizione);
	}

	@Override
	public void copyDataToModel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> validateModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateModel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertModel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProdottiPerModello createNewModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

}
