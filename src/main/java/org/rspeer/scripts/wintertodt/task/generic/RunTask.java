package org.rspeer.scripts.wintertodt.task.generic;

import org.rspeer.game.movement.Movement;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.data.Constant;

@TaskDescriptor(name = "Toggling run")
public class RunTask extends Task {

  private int tolerance = Constant.TOGGLE_RUN_AT.random();

  @Override
  public boolean execute() {
    if (Movement.isRunEnabled() || Movement.getRunEnergy() < tolerance) {
      return false;
    }

    Movement.toggleRun(true);
    tolerance = Constant.TOGGLE_RUN_AT.random();
    return true;
  }
}
