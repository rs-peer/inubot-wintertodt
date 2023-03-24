package org.rspeer.scripts.wintertodt.task.generic;

import com.google.inject.Inject;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.game.component.Item;
import org.rspeer.game.effect.Health;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Items;
import org.rspeer.scripts.wintertodt.api.Province;
import org.rspeer.scripts.wintertodt.data.Constant;
import org.rspeer.scripts.wintertodt.domain.Domain;

@TaskDescriptor(name = "Eating")
public class FoodTask extends Task {

  private final Domain domain;

  private int tolerance = Constant.EAT_FOOD_AT.random();

  @Inject
  public FoodTask(Domain domain) {
    this.domain = domain;
  }

  @Override
  public boolean execute() {
    Item food = Items.FOOD.apply(Inventory.backpack().query()).first();
    if (food == null || !shouldEat()) {
      return false;
    }

    food.interact(x -> true);
    tolerance = Constant.EAT_FOOD_AT.random();
    sleep(2);
    return true;
  }

  private boolean shouldEat() {
    if (!Province.isInGame() || domain.getBoss().isRespawning()) {
      return Health.getPercent() < 70;
    }

    return Health.getPercent() <= tolerance;
  }
}
