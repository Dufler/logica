package it.ltc.logica.trasporti.gui.listini.wizards.cliente;

import it.ltc.logica.common.controller.listini.ListiniSimulazioneController;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.wizard.WizardConFinale;

public class RincaraListinoWizard extends WizardConFinale {
	
	private static final String title = "Nuovo Listino Cliente";
	
	private ListiniSimulazioneController controller;
	
	private final RincaraProprietaWizardPage paginaProprieta;
	private final RincaraValoriWizardPage paginaValori;

	public RincaraListinoWizard() {
		super(title, true);
		controller = ListiniSimulazioneController.getInstance();
		paginaProprieta = new RincaraProprietaWizardPage();
		paginaValori = new RincaraValoriWizardPage();
	}

	@Override
	public void addPages() {
		addPage(paginaProprieta);
		addPage(paginaValori);
	}

	@Override
	public boolean finisci() {
		ListinoSimulazione listino = paginaProprieta.getListino();
		ListinoCorriere listinoCorriere = paginaValori.getListinoCorriere();
		Double[] rincari = paginaValori.getRincari();
		String inserimento = controller.creaNuovoListinoDaCorriere(listino, listinoCorriere, rincari);
		boolean insert = inserimento.isEmpty() ? true : false;
		if (!insert) {
			DialogMessaggio.openInformation("Attenzione", "Nella generazione del listino si sono verificati i seguenti problemi:\r\n" + inserimento);
			//Il seguente è un errore fatale, il resto sono solo warning.
			if (!inserimento.equals(ListiniSimulazioneController.MESSAGGIO_ERRORE_CREAZIONE_LISTINO))
				insert = true;
		}
		return insert;
	}

}
