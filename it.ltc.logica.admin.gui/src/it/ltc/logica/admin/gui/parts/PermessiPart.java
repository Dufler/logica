
package it.ltc.logica.admin.gui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import it.ltc.logica.admin.gui.logic.PermessiContentProvider;
import it.ltc.logica.common.controller.sistema.ControllerPermessi;
import it.ltc.logica.database.model.centrale.utenti.Permesso;

public class PermessiPart {

	public static final String partID = "it.ltc.logica.admin.gui.part.permessi";

	private TreeViewer treeViewer;
	private Tree treePermessi;
	
	private final ControllerPermessi controllerPermessi;
	
	private final IInputValidator nameValidator;

	@Inject
	public PermessiPart() {
		controllerPermessi = ControllerPermessi.getInstance();
		nameValidator = new IInputValidator() {
			@Override
			public String isValid(String newText) {
				String error = null;
				if (newText == null || newText.isEmpty())
					error = "Inserisci un nome per il permesso";
				return error;
			}
		};
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		Label lblPermessi = new Label(parent, SWT.NONE);
		lblPermessi.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		lblPermessi.setText("Permessi");

		treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		treeViewer.setAutoExpandLevel(-1);
		treeViewer.setContentProvider(new PermessiContentProvider());
		
		treePermessi = treeViewer.getTree();
		treePermessi.setLinesVisible(true);
		treePermessi.setHeaderVisible(true);
		treePermessi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		// Menu
		Menu menu = new Menu(treePermessi);
		MenuItem add = new MenuItem(menu, SWT.PUSH);
		add.setText("Aggiungi");
		add.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				aggiungiPermesso();
			}
		});
		treePermessi.setMenu(menu);

		TreeViewerColumn colonnaNome = new TreeViewerColumn(treeViewer, SWT.NONE);
		colonnaNome.setEditingSupport(new EditingSupport(treeViewer) {

			private final CellEditor editor = new TextCellEditor(treePermessi);;
			
			@Override
			protected CellEditor getCellEditor(Object element) {
				return editor;
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}

			@Override
			protected Object getValue(Object element) {
				Permesso permesso = (Permesso) element;
				return permesso.getNome();
			}

			@Override
			protected void setValue(Object element, Object value) {
				if (element != null && value != null) {
					Permesso permesso = (Permesso) element;
					String nome = value.toString();
					permesso.setNome(nome);
					boolean update = controllerPermessi.aggiorna(permesso);
					if (update)
						treeViewer.update(element, null);
				}				
			}
			
		});
		colonnaNome.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Permesso permesso = (Permesso) element;
				return permesso.getNome();
			}
		});
		TreeColumn trclmnPermesso = colonnaNome.getColumn();
		trclmnPermesso.setWidth(200);
		trclmnPermesso.setText("Permesso");

		TreeViewerColumn colonnaID = new TreeViewerColumn(treeViewer, SWT.NONE);
		colonnaID.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Permesso permesso = (Permesso) element;
				return permesso.getId().toString();
			}
		});
		TreeColumn trclmnId = colonnaID.getColumn();
		trclmnId.setWidth(50);
		trclmnId.setText("ID");
		
		TreeViewerColumn colonnaCategoria = new TreeViewerColumn(treeViewer, SWT.NONE);
		colonnaCategoria.setEditingSupport(new EditingSupport(treeViewer) {

			private final CellEditor editor = new TextCellEditor(treePermessi);;
			
			@Override
			protected CellEditor getCellEditor(Object element) {
				return editor;
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}

			@Override
			protected Object getValue(Object element) {
				Permesso permesso = (Permesso) element;
				return permesso.getCategoria();
			}

			@Override
			protected void setValue(Object element, Object value) {
				if (element != null && value != null) {
					Permesso permesso = (Permesso) element;
					String categoria = value.toString();
					permesso.setCategoria(categoria);
					boolean update = controllerPermessi.aggiorna(permesso);
					if (update)
						treeViewer.update(element, null);
				}				
			}
			
		});
		colonnaCategoria.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Permesso permesso = (Permesso) element;
				return permesso.getCategoria();
			}
		});
		TreeColumn trclmnCategoria = colonnaCategoria.getColumn();
		trclmnCategoria.setWidth(50);
		trclmnCategoria.setText("Categoria");

		treeViewer.setInput(controllerPermessi.getPermessiAdAlbero());

	}

	private void aggiungiPermesso() {
		TreeItem[] selection = treePermessi.getSelection();
		if (selection.length == 1) {
			Permesso padre = (Permesso) selection[0].getData();
			String hint = "Padre: " + padre.getNome() + " (" + padre.getId() + ")" + "\r\n" + "Inserisci il nome del nuovo permesso.";
			InputDialog dialog = new InputDialog(null, "Nuovo Permesso", hint, "", nameValidator);
			if (dialog.open() == Window.OK) {
				String nomePermesso = dialog.getValue();
				boolean inserimento = controllerPermessi.aggiungiPermesso(padre, nomePermesso);
				if (inserimento) {
					treeViewer.refresh();
				}
			}
		}
	}

}