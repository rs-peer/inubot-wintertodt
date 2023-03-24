package org.rspeer.scripts.wintertodt.task.game.brazier;

import com.google.inject.Inject;
import org.rspeer.game.adapter.scene.Player;
import org.rspeer.game.adapter.scene.SceneObject;
import org.rspeer.game.scene.Players;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Province;
import org.rspeer.scripts.wintertodt.data.Action;
import org.rspeer.scripts.wintertodt.domain.Domain;
import org.rspeer.scripts.wintertodt.task.game.ActionTask;

@TaskDescriptor(name = "We gonna let it burn burn burn burn")
public class BurnTask extends ActionTask {

  @Inject
  public BurnTask(Domain domain) {
    super(domain, Action.BURN);
  }

  @Override
  protected boolean play() {
    if (domain.getBoss().isRespawning() || domain.getState().shouldChop()) {
      return false;
    }

    Player self = Players.self();
    if (self == null) {
      return false;
    }

    SceneObject brazier = Province.findBrazier(domain.getState().getGang().getBrazier(), "Feed");
    if (brazier == null) {
      return false;
    }

    if (brazier.distance() <= 2 && action.isActive(domain)) {
      return true;
    }

    return brazier.interact("Feed");
  }
}
