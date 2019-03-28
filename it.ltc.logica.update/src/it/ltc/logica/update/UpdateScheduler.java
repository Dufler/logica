package it.ltc.logica.update;

import java.util.LinkedHashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.ui.di.UISynchronize;

public class UpdateScheduler extends Job {

	/**
	 * Il nome del processo
	 */
	public static final String NAME = "Controllo Aggiornamenti";

	/**
	 * Il tempo ogni quanto viene ripetuto (ogni ora)
	 */
	public static final long DELAY = 3600000;

	private boolean running = true;
	private UISynchronize sync;
	private final LinkedHashSet<String> featureNames;

	public UpdateScheduler(UISynchronize sync, LinkedHashSet<String> featureNames) {
		super(NAME);
		this.sync = sync;
		this.featureNames = featureNames;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		schedule(DELAY);
		sync.asyncExec(new Runnable() {
			@Override
            public void run() {
				//UpdateController.getInstance().updateFeatures();
				CheckForUpdatesProcess updater = new CheckForUpdatesProcess(featureNames);
				updater.eseguiOperazioni();
				System.out.println("Schedulazione per il controllo degli aggiornamenti eseguita!");
			}
		});
		return Status.OK_STATUS;
	}

	@Override
	public boolean shouldSchedule() {
		return running;
	}

	public void stop() {
		running = false;
	}

}
