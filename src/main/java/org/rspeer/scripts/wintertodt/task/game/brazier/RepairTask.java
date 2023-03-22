package org.rspeer.scripts.wintertodt.task.game.brazier;

import com.google.inject.Inject;
import org.rspeer.game.adapter.scene.Player;
import org.rspeer.game.adapter.scene.SceneObject;
import org.rspeer.game.scene.Players;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Province;
import org.rspeer.scripts.wintertodt.data.action.Action;
import org.rspeer.scripts.wintertodt.domain.Domain;
import org.rspeer.scripts.wintertodt.task.game.ActionTask;

@TaskDescriptor(name = "Bob the builder can we fix it? Yes we can.")
public class RepairTask extends ActionTask {

  @Inject
  public RepairTask(Domain domain) {
    super(domain);
  }

  @Override
  public Action getAction() {
    return Action.REPAIR;
  }

  @Override
  protected boolean play() {
    if (domain.getBoss().isRespawning() || domain.getState().shouldChop()) {
      return false;
    }

    Player self = Players.self();
    if (self == null || self.isAnimating()) {
      return false;
    }

    SceneObject brazier = Province.findBrazier(domain.getConfig().getGang().getBrazier(), "Fix");
    if (brazier != null) {
      if (brazier.distance() <= 2 && getAction().isActive(domain)) {
        return true;
      }

      return brazier.interact("Fix");
    }

    return false;
  }
}
