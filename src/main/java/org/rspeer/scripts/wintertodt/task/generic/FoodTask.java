package org.rspeer.scripts.wintertodt.task.generic;

import com.google.inject.Inject;
import org.rspeer.game.component.Inventories;
import org.rspeer.game.component.Item;
import org.rspeer.game.component.tdi.Tab;
import org.rspeer.game.component.tdi.Tabs;
import org.rspeer.game.effect.Health;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.*;
import org.rspeer.scripts.wintertodt.data.Constant;
import org.rspeer.scripts.wintertodt.domain.Domain;

@TaskDescriptor(name = "Eating", priority = 300)
public class FoodTask extends Task {

  private final Domain domain;

  private int tolerance = Constant.EAT_FOOD_AT.random();

  @Inject
  public FoodTask(Domain domain) {
    this.domain = domain;
  }

  @Override
  public boolean execute() {
    Item food = Items.FOOD.apply(Inventories.backpack().query()).first();
    if (food == null || !shouldEat()) {
      return false;
    }

    if (!Tabs.isOpen(Tab.INVENTORY)) {
      Tabs.open(Tab.INVENTORY);
    }

    food.interact(x -> true);
    tolerance = Constant.EAT_FOOD_AT.random();
    sleep(2);
    return true;
  }

  private boolean shouldEat() {
    if (!Province.isInGame() || domain.getBoss().isRespawning()) {
      return Warmth.getPercent() < 70;
    }

    return Warmth.getPercent() <= tolerance;
  }
}
