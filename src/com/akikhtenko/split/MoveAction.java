package com.akikhtenko.split;

import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.Display;

public class MoveAction {
	private EPartService partService;
	
	public MoveAction(EPartService partService) {
		this.partService = partService;
	}
	
	@SuppressWarnings("unchecked")
	public void move() {
		MPart activeEditor = partService.getActivePart();
		
		MElementContainer<MUIElement> oldEditorStack = activeEditor.getParent();
		MElementContainer<MUIElement> splitSash = oldEditorStack.getParent();
		if (splitSash.getChildren().size() != 2) {
			return;
		}
		
		int oldStackIndex = splitSash.getChildren().indexOf(oldEditorStack);
		MElementContainer<MUIElement> newEditorStack = (MElementContainer<MUIElement>) splitSash.getChildren().get(oldStackIndex == 1 ? 0 : 1);
		
		move_editor_into_new_stack(activeEditor, oldEditorStack, newEditorStack);

		activate(activeEditor);
	}
	
	private void move_editor_into_new_stack(MPart activeEditor, MElementContainer<MUIElement> oldEditorStack, MElementContainer<MUIElement> newStack) {
        oldEditorStack.getChildren().remove(activeEditor);
        newStack.getChildren().add(activeEditor);
	}
	
	private void activate(final MPart newPart) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				partService.activate(newPart);
			}
		});
	}
}
