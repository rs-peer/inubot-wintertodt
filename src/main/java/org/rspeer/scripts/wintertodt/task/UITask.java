package org.rspeer.scripts.wintertodt.task;

import com.google.inject.Inject;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.domain.config.Config;

@TaskDescriptor(
    name = "Waiting on user config",
    blocking = true,
    priority = Integer.MAX_VALUE
)
public class UITask extends Task {

  private final Config config;

  @Inject
  public UITask(Config config) {
    this.config = config;
  }

  @Override
  public boolean execute() {
    return !config.isBound();
  }
}
