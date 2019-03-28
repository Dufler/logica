package it.ltc.logica.trasporti.gui.listini.wizards.corriere;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.calcolo.algoritmi.TipoAlgoritmo;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.gui.composite.CompositeListino;
import it.ltc.logica.trasporti.gui.elements.ETipoListino;

public class NuovaVoceListinoCorriereProprietaWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuova Voce di listino corriere - propriet\u00E0";
	private static final String descrizione = "Specifica nome, descrizione, ambito e tipologia di calcolo.";
	
	private CompositeListino compositeListino;
	
	private final VoceDiListinoCorriere voce;
	private final ListinoCorriere listino;
	private TipoAlgoritmo tipoAlgoritmo;

	public NuovaVoceListinoCorriereProprietaWizardPage(ListinoCorriere listino, VoceDiListinoCorriere voce) {
		super(titolo, descrizione);
		this.listino = listino;
		this.voce = voce;
	}
	
	public VoceDiListinoCorriere getVoceDiListino() {
		return voce;
	}
	
	public TipoAlgoritmo getTipoAlgoritmoSelezionato() {
		return tipoAlgoritmo;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeListino = new CompositeListino(this, container, ETipoListino.CORRIERE);
		compositeListino.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	public void copyDataToModel() {
		tipoAlgoritmo = compositeListino.getAlgoritmo();
		voce.setNome(compositeListino.getNome());
		voce.setDescrizione(compositeListino.getDescrizione());
		voce.setIdListino(listino.getId());
		voce.setTipoCalcolo(tipoAlgoritmo.getCodifica());
		voce.setIdSottoAmbito(compositeListino.getAmbito().getId());
		String valoreAmbito = compositeListino.getValoreAmbito();
		if (!valoreAmbito.isEmpty())
			voce.setValoreSottoAmbito(valoreAmbito);
	}

}
