
package it.ltc.logica.container.sample.handlers;

import org.eclipse.e4.core.di.annotations.Execute;

import it.ltc.logica.container.controller.ControllerUtente;
import it.ltc.logica.update.UpdateController;

public class UpdateHandler {
	
	@Execute
	public void execute() {
		UpdateController controller = new UpdateController(ControllerUtente.getInstance().getFeaturesNames());
		controller.updateFeatures();
	}
}
//public class UpdateHandler {
//
//	boolean cancelled = false;
//
//	@Execute
//	public void execute(IProvisioningAgent agent, UISynchronize sync, IWorkbench workbench) {
//
//		// update using a progress monitor
//		IRunnableWithProgress runnable = new IRunnableWithProgress() {
//
//			@Override
//			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
//				update(agent, monitor, sync, workbench);
//			}
//		};
//
//		try {
//			new ProgressMonitorDialog(null).run(true, true, runnable);
//		} catch (InvocationTargetException | InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private IStatus update(IProvisioningAgent agent, IProgressMonitor monitor, UISynchronize sync, IWorkbench workbench) {
//		try {
//		ProvisioningSession session = new ProvisioningSession(agent);
//		// update the whole running profile, otherwise specify IUs
//		UpdateOperation operation = new UpdateOperation(session);
//		//aggiunta
//		File repositoryFolder = new File("C:/Users/Damiano/workspace-rcp/Update/");
//		System.out.println("Repository URI: '" + repositoryFolder.toURI() + "'");
//		URI uri = repositoryFolder.toURI(); //null;
////		try {
////			uri = new URI("http://localhost:8080/logica-update");
////		} catch (URISyntaxException e) {
////			e.printStackTrace();
////		}
//		URI[] repository = new URI[] {uri};
//		operation.getProvisioningContext().setArtifactRepositories(repository);
//		operation.getProvisioningContext().setMetadataRepositories(repository);
//		Map<String, String> repositoryProperties = operation.getProvisioningContext().getProperties();
//		String textProperties = "";
//		for (String key : repositoryProperties.keySet()) {
//			textProperties += key + ":" + repositoryProperties.get(key);
//		}
//		showMessage(sync, textProperties);
//		//fine
//		SubMonitor sub = SubMonitor.convert(monitor, "Checking for application updates...", 200);
//		// check if updates are available
//		IStatus status = operation.resolveModal(sub.newChild(100));
//		showMessage(sync, "Status aggiornamento: " + status.getCode() + status.getMessage());
//		if (status.getCode() == UpdateOperation.STATUS_NOTHING_TO_UPDATE) {
//			showMessage(sync, "Nothing to update");
//			return Status.CANCEL_STATUS;
//		} else {
//			ProvisioningJob provisioningJob = operation.getProvisioningJob(sub.newChild(100));
//			if (provisioningJob != null) {
//				sync.syncExec(new Runnable() {
//
//					@Override
//					public void run() {
//						boolean performUpdate = MessageDialog.openQuestion(null, "Updates available",
//								"There are updates available. Do you want to install them now?");
//						if (performUpdate) {
//							provisioningJob.addJobChangeListener(new JobChangeAdapter() {
//								@Override
//								public void done(IJobChangeEvent event) {
//									if (event.getResult().isOK()) {
//										sync.syncExec(new Runnable() {
//
//											@Override
//											public void run() {
//												boolean restart = MessageDialog.openQuestion(null,
//														"Updates installed, restart?",
//														"Updates have been installed successfully, do you want to restart?");
//												if (restart) {
//													workbench.restart();
//												}
//											}
//										});
//									} else {
//										String message = event.getResult().getMessage() + "\r\n";
//										for (StackTraceElement ste : event.getResult().getException().getStackTrace()) {
//											message += ste.toString() + "\r\n";
//										}
//										showError(sync, message);
//										cancelled = true;
//									}
//								}
//							});
//
//							// since we switched to the UI thread for
//							// interacting with the user
//							// we need to schedule the provisioning thread,
//							// otherwise it would
//							// be executed also in the UI thread and not in a
//							// background thread
//							provisioningJob.schedule();
//						} else {
//							cancelled = true;
//						}
//					}
//				});
//			} else {
//				if (operation.hasResolved()) {
//					showError(sync, "Couldn't get provisioning job: " + operation.getResolutionResult());
//				} else {
//					showError(sync, "Couldn't resolve provisioning job");
//				}
//				cancelled = true;
//			}
//		}
//		} catch(Exception e) {
//			String message = e.getMessage() + "\r\n";
//			for (StackTraceElement ste : e.getStackTrace()) {
//				message += ste.toString() + "\r\n";
//			}
//			showError(sync, message);
//		}
//
//		if (cancelled) {
//			// reset cancelled flag
//			cancelled = false;
//			return Status.CANCEL_STATUS;
//		}
//		return Status.OK_STATUS;
//	}
//
//	private void showMessage(UISynchronize sync, final String message) {
//		// as the provision needs to be executed in a background thread
//		// we need to ensure that the message dialog is executed in
//		// the UI thread
//		sync.syncExec(new Runnable() {
//
//			@Override
//			public void run() {
//				MessageDialog.openInformation(null, "Information", message);
//			}
//		});
//	}
//
//	private void showError(UISynchronize sync, final String message) {
//		// as the provision needs to be executed in a background thread
//		// we need to ensure that the message dialog is executed in
//		// the UI thread
//		sync.syncExec(new Runnable() {
//
//			@Override
//			public void run() {
//				MessageDialog.openError(null, "Error", message);
//			}
//		});
//	}
//}