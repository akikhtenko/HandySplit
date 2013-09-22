package com.akikhtenko.split;

import static com.akikhtenko.split.SplitDirection.HORIZONTAL;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;


public class SplitProcessor extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow wnd = HandlerUtil.getActiveWorkbenchWindow(event);
//		EModelService modelService = (EModelService) wnd.getService(EModelService.class);
		EPartService partService = (EPartService) wnd.getService(EPartService.class);
		
		new SplitAction(partService).split(HORIZONTAL);
		
		return null;
	}
}
