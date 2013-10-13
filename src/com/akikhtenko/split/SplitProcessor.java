package com.akikhtenko.split;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class SplitProcessor extends AbstractSplitHandler {

	public static final String DIRECTION_PARAM = "com.akikhtenko.split.direction";

	@Override
	protected void handleAction(ExecutionEvent event, EPartService partService) {
		String direction = event.getParameter(DIRECTION_PARAM);
		new SplitAction(partService).split(SplitDirection.fromString(direction));		
	}
}
