package it.ltc.logica.amministrazione.gui.fatturazione.dialogs;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.prodotti.ControllerBrand;
import it.ltc.logica.common.controller.sistema.ControllerCategorieMerceologiche;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica.StatoCategoria;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Brand;
import it.ltc.logica.gui.dialog.DialogModel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;

public class DialogCategorie extends DialogModel<CategoriaMerceologica> {

	public static final String title = "categorie di Fatturazione";
	
	private CompositeCategorie composite;
	
	private final ControllerCategorieMerceologiche controller;
	
	public DialogCategorie(CategoriaMerceologica value) {
		super(title, value);
		
		controller = ControllerCategorieMerceologiche.getInstance();
	}

	@Override
	public void loadModel() {
		//Trovo la commessa e il brand corretti
		Commessa commessa = ControllerCommesse.getInstance().getCommessa(valore.getCommessa());
		ControllerBrand controller = ControllerBrand.getInstance(commessa);
		Brand brand = controller.getBrandDaCodice(valore.getBrand());
		//carico i valori
		composite.setCommessa(commessa);
		composite.setDescrizione(valore.getDescrizione());
		composite.setCodice(valore.getNome());
		composite.setBrand(brand);
		composite.setStato(valore.getStato());		
	}

	@Override
	public void prefillModel() {
		composite.setStato(StatoCategoria.ATTIVO);		
	}

	@Override
	public void copyDataToModel() {
		valore.setCommessa(composite.getCommessa().getId());
		valore.setDescrizione(composite.getDescrizione());
		valore.setNome(composite.getCodice());
		valore.setBrand(composite.getBrand() != null ? composite.getBrand().getCodice() : 0);
		valore.setStato(composite.getStato());
	}

	@Override
	public List<String> validateModel() {
		List<String> errori = new LinkedList<>();
		//Se sono in inserimento controllo che non esista un'altra categoria con lo stesso codice.
		if (!modify) {
			CategoriaMerceologica esistente = controller.getCategoria(valore.getNome(), valore.getCommessa());
			if (esistente != null) {
				errori.add("La categoria merceologica " + valore.getNome() + " \u00E8 gi\u00E0 stata definita per la commessa indicata.");
			}
		}		
		return errori;
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
	public CategoriaMerceologica createNewModel() {
		CategoriaMerceologica categoria = new CategoriaMerceologica();
		return categoria;
	}

	@Override
	public boolean isDirty() {
		return composite.isDirty();
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeCategorie(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		//Se vado in modifica non posso fargli aggiornare la commessa o il codice.
		if (modify)
			composite.lockNonUpdatableElements();
	}

}
