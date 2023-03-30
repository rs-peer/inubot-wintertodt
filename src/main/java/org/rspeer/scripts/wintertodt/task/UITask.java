package org.rspeer.scripts.wintertodt.task;

import com.google.inject.Inject;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.domain.Domain;

@TaskDescriptor(
    name = "UI",
    blocking = true,
    priority = Integer.MAX_VALUE
)
public class UITask extends Task {

  private final Domain domain;

  @Inject
  public UITask(Domain domain) {
    this.domain = domain;
  }

  @Override
  public boolean execute() {
    return domain.getConfig().getGang() == null;
  }
}
