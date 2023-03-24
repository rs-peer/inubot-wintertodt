package org.rspeer.scripts.wintertodt.task.prepare;

import org.rspeer.game.adapter.scene.SceneObject;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Province;

@TaskDescriptor(name = "Starting a game!")
public class EnterTask extends Task {

  @Override
  public boolean execute() {
    if (Province.isInGame()) {
      return false;
    }

    SceneObject door = Province.findDoor();
    return door != null && door.interact("Enter");
  }
}
