package org.rspeer.scripts.wintertodt.task.prepare;

import com.google.inject.Inject;
import org.rspeer.game.component.Worlds;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.data.locale.GameWorld;
import org.rspeer.scripts.wintertodt.domain.Config;

@TaskDescriptor(name = "Hopping to Wintertodt world!")
public class HopTask extends Task {

  private final Config config;

  @Inject
  public HopTask(Config config) {
    this.config = config;
  }

  @Override
  public boolean execute() {
    GameWorld world = config.getWorld();
    return Worlds.getLocal() != world.getId() && Worlds.hopTo(world.getId());
  }
}
