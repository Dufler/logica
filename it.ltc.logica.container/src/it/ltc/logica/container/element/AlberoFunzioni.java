package it.ltc.logica.container.element;

import java.util.List;

import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import it.ltc.logica.container.model.Modulo;

public class AlberoFunzioni extends TreeViewer {

	private Tree tree;
	private TreeColumnLayout layout;
	
	public AlberoFunzioni(Composite parent) {
		super(parent);
		
		setAutoExpandLevel(-1);
		setContentProvider(new ProviderFunzioni());
		
		tree = getTree();
		
		setStyle(parent);
		
		setColumns();
		
		addListener();
	}
	
	private void addListener() {
		addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				System.out.println(event.getSource());
			}
		});
	}
	
	private void setStyle(Composite parent) {
		tree.setLinesVisible(false);
		tree.setHeaderVisible(false);
		layout = new TreeColumnLayout();
		parent.setLayout(layout);
	}
	
	private void setColumns() {
		TreeViewerColumn colonnaFeature = new TreeViewerColumn(this, SWT.NONE);
		colonnaFeature.setLabelProvider(new DelegatingStyledCellLabelProvider(new EtichettatoreFeature()));
		TreeColumn trclmnFeature = colonnaFeature.getColumn();
		layout.setColumnData(trclmnFeature, new ColumnPixelData(88, true, true));
		trclmnFeature.setText("Feature");
		
		TreeViewerColumn colonnaFunzioni = new TreeViewerColumn(this, SWT.NONE);
		colonnaFunzioni.setLabelProvider(new DelegatingStyledCellLabelProvider(new EtichettatoreFunzioni()));
		TreeColumn trclmnFunzione = colonnaFunzioni.getColumn();
		layout.setColumnData(trclmnFunzione, new ColumnPixelData(150, true, true));
		trclmnFunzione.setText("Funzione");
	}
	
	public void setFunzioni(List<Modulo> funzioni) {
		setInput(funzioni);
	}

}
