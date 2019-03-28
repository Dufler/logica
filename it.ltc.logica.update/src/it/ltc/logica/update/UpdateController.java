package it.ltc.logica.update;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.LinkedHashSet;

import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.swt.widgets.Display;

import it.ltc.database.dao.locali.ResourceToDeleteDao;
import it.ltc.logica.database.model.locale.ResourceToDelete;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.task.DialogProgresso;

/**
 * Controller singleton che si occupa della ricerca, scaricamento ed installazione degli aggiornamenti.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class UpdateController {


	private final LinkedHashSet<String> featureNames;
	private final ResourceToDeleteDao manager;
	
	public UpdateController(LinkedHashSet<String> names) {
		manager = new ResourceToDeleteDao();
		featureNames = new LinkedHashSet<String>();
		featureNames.addAll(names);
	}
	
	protected void addResourceToDeleteList(String name) {
		ResourceToDelete resource = new ResourceToDelete();
		resource.setName(name);
		manager.inserisci(resource);
	}
	
	protected File getFeaturesFolder() {
		File folder = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "features" + System.getProperty("file.separator"));
		if (!folder.exists())
			folder.mkdirs();
		return folder;
	}
	
	protected File getPluginsFolder() {
		File folder = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "plugins" + System.getProperty("file.separator"));
		if (!folder.exists())
			folder.mkdirs();
		return folder;
	}
	
	protected File getUpdateFolder() {
		File folder = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "updates" + System.getProperty("file.separator"));
		if (!folder.exists())
			folder.mkdirs();
		return folder;
	}
	
	protected File getWorkbenchFile() {
		String workbenchPath = System.getProperty("user.dir") + System.getProperty("file.separator");
		workbenchPath += "workspace" + System.getProperty("file.separator");
		workbenchPath += ".metadata" + System.getProperty("file.separator");
		workbenchPath += ".plugins" + System.getProperty("file.separator");
		workbenchPath += "org.eclipse.e4.workbench" + System.getProperty("file.separator") + "workbench.xmi";
		File workbench = new File(workbenchPath);
		return workbench;
	}
	
	/**
	 * Esegue il download di un file.
	 * @param local il path del file che verrà salvato
	 * @param remote il path della risorsa
	 * @return l'esito dell'operazione
	 */
	protected boolean download(String local, String remote) {
		boolean download;
		try {
			URL resource = new URL(remote);
			ReadableByteChannel rbc = Channels.newChannel(resource.openStream());
			FileOutputStream fos = new FileOutputStream(local);
			long transferredBytes = fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.flush();
			fos.close();
			download = transferredBytes > 0;
		} catch (Exception e) {
			e.printStackTrace();
			download = false;
		}
		return download;
	}
	
	/**
	 * Controlla se esistono aggiornamenti per le feature con i nomi passati come argomento.
	 * @param featureNames i nomi delle feature da verificare
	 * @return La lista di feature che necessitano di aggiornare i plugin
	 */
	public void updateFeatures() {
		CheckForUpdatesProcess updater = new CheckForUpdatesProcess(featureNames);
		DialogProgresso dialog = new DialogProgresso("Controllo Aggiornamenti");
		dialog.esegui(updater);
	}
	
	/**
	 * Avvia un processo continuo per la ricerca di aggiornamenti
	 */
	public void scheduleUpdateCheck(UISynchronize sync) {
		UpdateScheduler scheduler = new UpdateScheduler(sync, featureNames);
		scheduler.schedule(UpdateScheduler.DELAY);
	}

	/**
	 * Riavvia l'applicazione indicando quanti aggiornamenti sono stati installati.
	 * @param featuresUpdated
	 */
	public void restart(int featuresUpdated) {
		Display.getDefault().asyncExec(new Runnable() {
		    public void run() {
		    	String title = "Aggiornamenti installati";
		    	String message;
		    	if (featuresUpdated == 1)
		    		message = "\u00C8 stato installato un nuovo aggiornamento. Il programma verr\u00E0 riavviato.";
		    	else
		    		message = "Sono stati installati " + featuresUpdated + " nuovi aggiornamenti. Il programma verr\u00E0 riavviato.";
		    	DialogMessaggio.openInformation(title, message);
		    	try {
		    		new ProcessBuilder(System.getProperty("user.dir") + System.getProperty("file.separator") + "LogicaLauncher.exe").start();
		    		System.exit(0);
		    	} catch (Exception e) {
		    		e.printStackTrace();
		    	}
		    }
		});
	}
	
}
