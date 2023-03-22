package org.rspeer.scripts.wintertodt.task.game;

import com.google.inject.Inject;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.game.component.Item;
import org.rspeer.game.effect.Health;
import org.rspeer.game.movement.Movement;
import org.rspeer.game.scene.Players;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.data.Constant;
import org.rspeer.scripts.wintertodt.domain.Domain;

@TaskDescriptor(
    name = "Waiting",
    blocking = true,
    priority = 100
)
public class WaitingAreaTask extends GameTask {

  @Inject
  public WaitingAreaTask(Domain domain) {
    super(domain);
  }

  @Override
  protected boolean play() {
    if (domain.getBoss().isRespawning() || Health.getPercent() > 30) {
      return false;
    }

    //TODO if enough time and points are low just reset and bank...

    Item food = Constant.FOOD.apply(Inventory.backpack().query()).first();
    if (food != null) {
      return false;
    }

    if (Constant.WAITING_AREA.contains(Players.self())) {
      return true;
    }

    //TODO Polarize this to select a tile from the area closest to the Gang defined in Config rather than random tile.
    Movement.walkTo(Constant.WAITING_AREA.getRandomTile());
    return true;
  }
}
