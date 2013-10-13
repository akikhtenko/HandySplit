package com.akikhtenko.split;

import static com.akikhtenko.split.SplitProcessor.DIRECTION_PARAM;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class CloneProcessor extends AbstractSplitHandler {

	@Override
	protected void handleAction(ExecutionEvent event, EPartService partService) {
		String direction = event.getParameter(DIRECTION_PARAM);
		new CloneAction(partService).cloneSplit(SplitDirection.fromString(direction));
	}
}
