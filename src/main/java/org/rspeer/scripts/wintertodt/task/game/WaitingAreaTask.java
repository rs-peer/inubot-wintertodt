package org.rspeer.scripts.wintertodt.task.game;

import com.google.inject.Inject;
import org.rspeer.game.component.Inventories;
import org.rspeer.game.component.Item;
import org.rspeer.game.movement.Movement;
import org.rspeer.game.scene.Players;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Items;
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
    if (domain.getBoss().isRespawning()) {
      return false;
    }

    //TODO if enough time and points are low just reset and bank...

    Item food = Items.FOOD.apply(Inventories.backpack().query()).first();
    if (food != null) {
      return false;
    }

    if (Constant.WAITING_AREA.contains(Players.self())) {
      return true;
    }

    //TODO Polarize this to select a tile from the area closest to the Gang defined in Config rather than random tile.
    Movement.walkTowards(Constant.WAITING_AREA.getRandomTile());
    return true;
  }
}
