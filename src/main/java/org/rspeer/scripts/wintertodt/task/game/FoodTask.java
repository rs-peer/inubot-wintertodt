package org.rspeer.scripts.wintertodt.task.game;

import com.google.inject.Inject;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.game.component.Item;
import org.rspeer.game.effect.Health;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Items;
import org.rspeer.scripts.wintertodt.data.Constant;
import org.rspeer.scripts.wintertodt.domain.Domain;

@TaskDescriptor(name = "Eating")
public class FoodTask extends GameTask {

  private int tolerance = Constant.EAT_FOOD_AT.random();

  @Inject
  public FoodTask(Domain domain) {
    super(domain);
  }

  @Override
  protected boolean play() {
    if (domain.getBoss().isRespawning()) {
      return false;
    }

    Item food = Items.FOOD.apply(Inventory.backpack().query()).first();
    if (food == null || Health.getPercent() > tolerance) {
      return false;
    }

    food.interact(x -> true);
    tolerance = Constant.EAT_FOOD_AT.random();
    sleep(2);
    return true;
  }
}
