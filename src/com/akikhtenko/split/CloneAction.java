package com.akikhtenko.split;

import static com.akikhtenko.split.SplitDirection.HORIZONTAL;

import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class CloneAction {
	private EPartService partService;
	
	public CloneAction(EPartService partService) {
		this.partService = partService;
	}
	
	public void cloneSplit() {
		MPart activeEditor = partService.getActivePart();
		MPart clonedEditor = cloneEditor();		
		
		MElementContainer<MUIElement> oldEditorStack = activeEditor.getParent();
		MElementContainer<MUIElement> splitSash = oldEditorStack.getParent();
		
		oldEditorStack.getChildren().add(clonedEditor);
		activate(clonedEditor);
		
		if (splitSash.getChildren().size() != 2) {
			new SplitAction(partService).split(HORIZONTAL);
		} else {
			new MoveAction(partService).move();
		}
	}
	
	private void activate(final MPart newPart) {
		partService.activate(newPart);
	}
	
    private MPart cloneEditor() {
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IEditorPart editor = page.getActiveEditor();
		try {
			IEditorPart newEditor = page.openEditor(editor.getEditorInput(),
			        editor.getSite().getId(), false, IWorkbenchPage.MATCH_NONE);

			return (MPart) newEditor.getSite().getService(MPart.class);
		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}
    }
}
