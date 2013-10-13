package com.akikhtenko.split;

import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class ToggleFocusAction {
	private EPartService partService;
	
	public ToggleFocusAction(EPartService partService) {
		this.partService = partService;
	}
	
	@SuppressWarnings("unchecked")
	public void toggle() {
		MPart activeEditor = partService.getActivePart();
		
		MElementContainer<MUIElement> activeEditorStack = activeEditor.getParent();
		MElementContainer<MUIElement> splitSash = activeEditorStack.getParent();
		if (splitSash.getChildren().size() != 2) {
			return;
		}
		
		int activeStackIndex = splitSash.getChildren().indexOf(activeEditorStack);
		MElementContainer<MUIElement> neighbourEditorStack = (MElementContainer<MUIElement>) splitSash.getChildren().get(activeStackIndex == 1 ? 0 : 1);

		activate((MPart) neighbourEditorStack.getSelectedElement());
	}
		
	private void activate(final MPart newPart) {
		partService.activate(newPart);
	}
}
