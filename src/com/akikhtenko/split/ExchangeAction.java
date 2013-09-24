package com.akikhtenko.split;

import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class ExchangeAction {
	private EPartService partService;
	
	public ExchangeAction(EPartService partService) {
		this.partService = partService;
	}
	
	@SuppressWarnings("unchecked")
	public void exchange() {
		MPart activeEditor = partService.getActivePart();
		
		MElementContainer<MUIElement> oldEditorStack = activeEditor.getParent();
		MElementContainer<MUIElement> splitSash = oldEditorStack.getParent();
		if (splitSash.getChildren().size() != 2) {
			return;
		}
		
		int oldStackIndex = splitSash.getChildren().indexOf(oldEditorStack);
		MElementContainer<MUIElement> newEditorStack = (MElementContainer<MUIElement>) splitSash.getChildren().get(oldStackIndex == 1 ? 0 : 1);
		
		exchange_editor_with_selected_in_new_stack(activeEditor, oldEditorStack, newEditorStack);

		activate(activeEditor);
	}
	
	private void exchange_editor_with_selected_in_new_stack(MPart activeEditor, MElementContainer<MUIElement> oldEditorStack, MElementContainer<MUIElement> newStack) {
        int activeEditorIndex = oldEditorStack.getChildren().indexOf(activeEditor);
        MUIElement exchangedEditor = newStack.getSelectedElement();
		int exchangedEditorIndex = newStack.getChildren().indexOf(exchangedEditor);
        
		oldEditorStack.getChildren().add(activeEditorIndex, exchangedEditor);
        newStack.getChildren().add(exchangedEditorIndex, activeEditor);
	}
	
	private void activate(final MPart newPart) {
		partService.activate(newPart);
	}
}
