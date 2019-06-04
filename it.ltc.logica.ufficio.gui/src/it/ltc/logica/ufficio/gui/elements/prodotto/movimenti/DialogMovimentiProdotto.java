package it.ltc.logica.ufficio.gui.elements.prodotto.movimenti;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.ingressi.ControllerMagazzini;
import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.MovimentoProdotto;
import it.ltc.logica.database.model.prodotto.Prodotto;
import it.ltc.logica.gui.dialog.DialogModel;

public class DialogMovimentiProdotto extends DialogModel<MovimentoProdotto> {

	private static final String title = "Movimento";
	
	private final Commessa commessa;
	private final Prodotto prodotto;
	private final ControllerProdotti controller;
	
	private CompositeMovimentoProdotto composite;
	
	public DialogMovimentiProdotto(MovimentoProdotto value, Commessa commessa, Prodotto prodotto, ControllerProdotti controller) {
		super(title, value);
		this.commessa = commessa;
		this.prodotto = prodotto;
		this.controller = controller;
		this.minimumWidth = 250;
		this.minimumHeight = 180;
	}

	@Override
	public void loadModel() {
		composite.setArticolo(prodotto.getChiaveCliente());
		composite.setCommessa(commessa);
		composite.setQuantita(valore.getQuantita());
		composite.setMagazzino(ControllerMagazzini.getInstance().getMagazzinoDaCodiceLTC(commessa, valore.getMagazzino()));
		composite.setCausale(valore.getCausale());
		composite.setRiferimentoDocumento(valore.getDocumentoRiferimento());
		composite.setDataMovimento(valore.getDataMovimento());
		composite.enableElement(false);
	}

	@Override
	public void prefillModel() {
		composite.setArticolo(prodotto.getChiaveCliente());
		composite.setCommessa(commessa);
	}

	@Override
	public void copyDataToModel() {
		valore.setCausale(composite.getCausale());
		valore.setDataMovimento(composite.getDataMovimento());
		valore.setDocumentoID(-1);
		valore.setDocumentoRiferimento(composite.getRiferimentoDocumento());
		valore.setMagazzino(composite.getMagazzino().getCodiceLTC());
		valore.setQuantita(composite.getQuantita());
		valore.setSkuProdotto(prodotto.getChiaveCliente());
		valore.setIdProdotto(prodotto.getId());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		return false;
	}

	@Override
	public boolean insertModel() {
		boolean insert = controller.inserisciMovimento(valore);
		return insert;
	}

	@Override
	public MovimentoProdotto createNewModel() {
		MovimentoProdotto movimento = new MovimentoProdotto();
		return movimento;
	}

	@Override
	public boolean isDirty() {
		return composite.isDirty();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		composite = new CompositeMovimentoProdotto(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
	}

}
