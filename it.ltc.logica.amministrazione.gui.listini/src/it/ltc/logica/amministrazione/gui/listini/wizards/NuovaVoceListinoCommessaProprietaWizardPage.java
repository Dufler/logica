package it.ltc.logica.amministrazione.gui.listini.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.composite.CompositeListino;
import it.ltc.logica.common.calcolo.algoritmi.TipoAlgoritmo;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.gui.wizard.PaginaWizard;

public class NuovaVoceListinoCommessaProprietaWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuova Voce di listino cliente - propriet\u00E0";
	private static final String descrizione = "Specifica nome, descrizione, ambito e tipologia di calcolo.";
	
	private final ListinoCommessa listino;
	private final VoceDiListino voce;
	
	private TipoAlgoritmo tipoAlgoritmo;
	
	private CompositeListino compositeListino;

	public NuovaVoceListinoCommessaProprietaWizardPage(ListinoCommessa listino, VoceDiListino voce) {
		super(titolo, descrizione);
		this.listino = listino;
		this.voce = voce;
	}
	
	public VoceDiListino getVoceDiListino() {
		return voce;
	}
	
	public TipoAlgoritmo getTipoAlgoritmoSelezionato() {
		return tipoAlgoritmo;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeListino = new CompositeListino(this, container, CompositeListino.Tipo.LOGISTICA);
		compositeListino.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		addChild(compositeListino);
	}

	@Override
	public void copyDataToModel() {
		tipoAlgoritmo = compositeListino.getAlgoritmo();
		voce.setNome(compositeListino.getNome());
		voce.setDescrizione(compositeListino.getDescrizione());
		voce.setIdListino(listino.getId());
		voce.setTipoCalcolo(tipoAlgoritmo.getCodifica());
		voce.setIdSottoAmbito(compositeListino.getAmbito().getId());
		voce.setStrategiaCalcolo(compositeListino.getAlgoritmo().getCodifica());
		String valoreSottoAmbito = compositeListino.getValoreAmbito();
		if (!valoreSottoAmbito.isEmpty())
			voce.setValoreSottoAmbito(valoreSottoAmbito);
	}

}
