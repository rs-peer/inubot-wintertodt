package org.rspeer.scripts.wintertodt.task.prepare;

import com.google.inject.Inject;
import org.rspeer.game.adapter.scene.SceneObject;
import org.rspeer.game.component.Dialog;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Province;
import org.rspeer.scripts.wintertodt.domain.Domain;

@TaskDescriptor(
    name = "Exiting the wintertodt area!",
    blocking = true,
    blockIfSleeping = true,
    priority = 100
)
public class ExitTask extends Task {

  private final Domain domain;

  @Inject
  public ExitTask(Domain domain) {
    this.domain = domain;
  }

  @Override
  public boolean execute() {
    if (!Province.isInGame() || !domain.getBoss().isRespawning() || domain.getConfig().isReady()) {
      return false;
    }

    if (Dialog.isViewingChatOptions()) {
      return Dialog.process(0);
    }

    SceneObject door = Province.findDoor();
    return door != null && door.interact("Enter");
  }
}
