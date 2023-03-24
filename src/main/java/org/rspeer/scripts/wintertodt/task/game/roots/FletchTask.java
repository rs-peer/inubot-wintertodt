package org.rspeer.scripts.wintertodt.task.game.roots;

import com.google.inject.Inject;
import org.rspeer.commons.logging.Log;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.game.adapter.scene.Player;
import org.rspeer.game.component.Item;
import org.rspeer.game.scene.Players;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Items;
import org.rspeer.scripts.wintertodt.data.*;
import org.rspeer.scripts.wintertodt.domain.Domain;
import org.rspeer.scripts.wintertodt.task.game.ActionTask;

@TaskDescriptor(
    name = "Y Fletch",
    priority = 10,
    blocking = true,
    blockIfSleeping = true
)
public class FletchTask extends ActionTask {

  @Inject
  public FletchTask(Domain domain) {
    super(domain, Action.FLETCH);
  }

  @Override
  protected boolean play() {
    //TODO fletch 1 log while moving between the logs and brazier, even if fletching is disabled because it's lossless?
    if (!domain.getConfig().isFletch()
        || domain.getBoss().isRespawning()
        || domain.getState().shouldChop()) {
      return false;
    }

    Player self = Players.self();
    if (self == null || isInsufficientStoredPoints()) {
      return false;
    }

    //Only start fletching while moving
    if (!self.isMoving() && domain.getState().getGang().getBrazier().distance() > 2) {
      return false;
    }

    Item root = Inventory.backpack().query()
        .nameContains(Constant.ROOT)
        .results()
        .first();
    if (root == null) {
      return false;
    }

    if (action.isActive(domain)) {
      return true;
    }

    Inventory.backpack().use(
        iq -> iq.names(WintertodtItem.KNIFE.getName()).results().first(),
        root
    );
    sleepUntil(() -> action.isActive(domain), action.getDuration());
    return true;
  }

  private boolean isInsufficientStoredPoints() {
    int points = domain.getBoss().getPoints();
    int forNextThreshold = Constant.POINTS_THRESHOLD - (points % Constant.POINTS_THRESHOLD);
    return points >= forNextThreshold - Items.getStoredRootPoints(Inventory.backpack());
  }
}
