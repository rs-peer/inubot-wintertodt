package org.rspeer.scripts.wintertodt.task.generic;

import org.rspeer.game.component.Dialog;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;

@TaskDescriptor(name = "Dialog")
public class DialogTask extends Task {

  @Override
  public boolean execute() {
    return Dialog.canContinue() && Dialog.processContinue();
  }
}
