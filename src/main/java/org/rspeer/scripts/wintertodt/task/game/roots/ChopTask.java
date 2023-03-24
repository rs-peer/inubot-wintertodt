package org.rspeer.scripts.wintertodt.task.game.roots;

import com.google.inject.Inject;
import org.rspeer.game.adapter.scene.Player;
import org.rspeer.game.adapter.scene.SceneObject;
import org.rspeer.game.scene.Players;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Province;
import org.rspeer.scripts.wintertodt.data.Action;
import org.rspeer.scripts.wintertodt.domain.Domain;
import org.rspeer.scripts.wintertodt.task.game.ActionTask;

@TaskDescriptor(name = "Chop chop")
public class ChopTask extends ActionTask {

  @Inject
  public ChopTask(Domain domain) {
    super(domain, Action.CHOP);
  }

  @Override
  protected boolean play() {
    if (domain.getBoss().isRespawning() || !domain.getState().shouldChop()) {
      return false;
    }

    Player self = Players.self();
    if (self == null || self.isAnimating()) {
      return false;
    }

    SceneObject root = Province.findRoots(domain.getState().getGang().getRoots());
    return root != null && root.interact("Chop");
  }
}
