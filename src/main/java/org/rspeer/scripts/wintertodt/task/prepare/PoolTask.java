package org.rspeer.scripts.wintertodt.task.prepare;

import com.google.inject.Inject;
import org.rspeer.game.House;
import org.rspeer.game.component.Inventories;
import org.rspeer.game.component.Item;
import org.rspeer.game.effect.Health;
import org.rspeer.game.scene.SceneObjects;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Province;
import org.rspeer.scripts.wintertodt.domain.Boss;

@TaskDescriptor(
    name = "Pooling!",
    blocking = true,
    blockIfSleeping = true,
    priority = 100
)
public class PoolTask extends Task {

  private final Boss boss;

  @Inject
  public PoolTask(Boss boss) {
    this.boss = boss;
  }

  @Override
  public boolean execute() {
    if ((Province.isInGame() && !boss.isRespawning())) {
      return false;
    }

    if (House.isInside() && Health.getPercent() >= 100) {
      SceneObjects.query()
          .nameContains("Jewellery box")
          .results()
          .limit(1)
          .forEach(x -> x.interact("Wintertodt Camp"));
      return true;
    }

    if (Health.getPercent() >= 100) {
      return false;
    }

    if (!House.isInside()) {
      Item cape = Inventories.equipment().query()
          .nameContains("Max cape", "Construction cape")
          .results()
          .first();
      if (cape == null) {
        return false;
      }

      cape.interact("Tele to POH");
      return true;
    }

    SceneObjects.query()
        .nameContains("pool of rejuv")
        .results()
        .limit(1)
        .forEach(x -> x.interact("Drink"));
    return true;
  }
}
