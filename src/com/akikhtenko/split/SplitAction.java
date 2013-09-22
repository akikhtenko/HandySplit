package com.akikhtenko.split;

import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainer;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainerElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class SplitAction {
	private static final String HALF_SIZE = "5000";
	private EPartService partService;
	
	public SplitAction(EPartService partService) {
		this.partService = partService;
	}
	
	public void split(SplitDirection direction) {
		MPart activeEditor = partService.getActivePart();
		
		MElementContainer<MUIElement> oldEditorStack = activeEditor.getParent();
		if (oldEditorStack.getChildren().size() < 2) {
			return;
		}
		
		MElementContainer<MUIElement> outerSash = oldEditorStack.getParent();
		
		MPartSashContainer splitSash = create_split_sash(direction);
		
		replace_in(outerSash, oldEditorStack, splitSash);
		
		MPartStack newEditorStack = create_new_stack(oldEditorStack);
		
		splitSash.getChildren().add((MPartSashContainerElement) oldEditorStack);
		splitSash.getChildren().add(newEditorStack);
		
		resize(splitSash, oldEditorStack, newEditorStack);
		
		move_editor_into_new_stack(activeEditor, oldEditorStack, newEditorStack);
		
        activate(activeEditor);
	}

	private void resize(MPartSashContainer splitSash, MElementContainer<MUIElement> oldEditorStack, MPartStack newEditorStack) {
		splitSash.setContainerData(oldEditorStack.getContainerData());
		newEditorStack.setContainerData(HALF_SIZE);
		oldEditorStack.setContainerData(HALF_SIZE);
	}
	
	private void move_editor_into_new_stack(MPart activeEditor, MElementContainer<MUIElement> oldEditorStack, MPartStack newStack) {
        oldEditorStack.getChildren().remove(activeEditor);
        activate((MPart) oldEditorStack.getSelectedElement());
        newStack.getChildren().add(activeEditor);
	}
	
	private void replace_in(MElementContainer<MUIElement> container, MUIElement old, MPartSashContainerElement nw) {
		int oldIndex = container.getChildren().indexOf(old);
		container.getChildren().remove(old);
		container.getChildren().add(oldIndex, nw);
	}

	private MPartSashContainer create_split_sash(SplitDirection direction) {
		MPartSashContainer splitSash = MBasicFactory.INSTANCE.createPartSashContainer();
		splitSash.setHorizontal(direction.isHorizontal());
		return splitSash;
	}

	private MPartStack create_new_stack(MElementContainer<MUIElement> oldEditorStack) {
		MPartStack newStack = MBasicFactory.INSTANCE.createPartStack();
        newStack.getTags().addAll(oldEditorStack.getTags());
        return newStack;
	}

	private void activate(MPart newPart) {
		partService.activate(newPart);
	}
}
