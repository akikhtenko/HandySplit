package com.akikhtenko.split;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public abstract class AbstractSplitHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow wnd = HandlerUtil.getActiveWorkbenchWindow(event);
		EPartService partService = (EPartService) wnd.getService(EPartService.class);
		
		MPart activeEditor = partService.getActivePart();
		if (isEditor(activeEditor)) {			
			handleAction(event, partService);
		}
		
		return null;
	}

	private boolean isEditor(MPart activeEditor) {
		for (String tag : activeEditor.getTags()) {
			if (tag.equalsIgnoreCase("editor")) {
				return true;
			}
		}
		return false;
	}

	protected abstract void handleAction(ExecutionEvent event, EPartService partService);
}
