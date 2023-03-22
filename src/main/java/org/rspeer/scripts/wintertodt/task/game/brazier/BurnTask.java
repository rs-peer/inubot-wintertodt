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

@TaskDescriptor(name = "We gonna let it burn burn burn burn")
public class BurnTask extends ActionTask {

  @Inject
  public BurnTask(Domain domain) {
    super(domain);
  }

  @Override
  public Action getAction() {
    return Action.BURN;
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

    //TODO The way I check for idle here is bad
    //because it is efficient to fletch 1 log while moving between the roots and the brazier.
    //isIdle checks for animation which wouldn't work if I were to add that
    SceneObject brazier = Province.findBrazier(domain.getConfig().getGang().getBrazier(), "Feed");
    if (brazier != null) {
      if (brazier.distance() <= 2 && !domain.getTimers().isIdle(3)) {
        return true;
      }

      return brazier.interact("Feed");
    }

    return false;
  }
}