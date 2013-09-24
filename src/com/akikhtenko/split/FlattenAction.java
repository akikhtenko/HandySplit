package com.akikhtenko.split;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.Display;

public class FlattenAction {
	private EPartService partService;
	
	public FlattenAction(EPartService partService) {
		this.partService = partService;
	}
	
	@SuppressWarnings("unchecked")
	public void flatten() {
		MPart activeEditor = partService.getActivePart();
		
		MElementContainer<MUIElement> activeEditorStack = activeEditor.getParent();
		MElementContainer<MUIElement> splitSash = activeEditorStack.getParent();
		if (splitSash.getChildren().size() != 2) {
			return;
		}
		
		Iterator<MUIElement> stacks = splitSash.getChildren().iterator();
		MElementContainer<MUIElement> leftEditorStack = (MElementContainer<MUIElement>) stacks.next();
		MElementContainer<MUIElement> rightEditorStack = (MElementContainer<MUIElement>) stacks.next();
		
		move_all_editors_to_new_stack(leftEditorStack, rightEditorStack, activeEditor);

		asynchActivate(activeEditor);
	}
	
	private void move_all_editors_to_new_stack(MElementContainer<MUIElement> leftEditorStack, MElementContainer<MUIElement> rigthEditorStack, MPart activeEditor) {
		for (MUIElement muiElement: new ArrayList<MUIElement>(rigthEditorStack.getChildren())) {
			rigthEditorStack.getChildren().remove(muiElement);
			leftEditorStack.getChildren().add(muiElement);

			// A HACK here! Can't explain but this allows the cursor to appear in the active editor when finished
			if (muiElement != activeEditor) {
				activate((MPart) muiElement);
			}
		}
	}
	
	private void activate(MPart newPart) {
		partService.activate(newPart);
	}

	private void asynchActivate(final MPart newPart) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				activate(newPart);
			}
		});
	}
}
