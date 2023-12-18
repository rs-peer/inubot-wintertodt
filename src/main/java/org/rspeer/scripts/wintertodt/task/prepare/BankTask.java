package org.rspeer.scripts.wintertodt.task.prepare;

import com.google.inject.Inject;
import org.rspeer.game.Definitions;
import org.rspeer.game.adapter.component.inventory.Bank;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.game.adapter.definition.ItemDefinition;
import org.rspeer.game.component.Item;
import org.rspeer.game.config.item.entry.builder.ItemEntryBuilder;
import org.rspeer.game.config.item.loadout.BackpackLoadout;
import org.rspeer.game.effect.Health;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Items;
import org.rspeer.scripts.wintertodt.api.Province;
import org.rspeer.scripts.wintertodt.data.WintertodtItem;
import org.rspeer.scripts.wintertodt.domain.config.Config;

@TaskDescriptor(
    name = "Banking!",
    blocking = true,
    blockIfSleeping = true,
    priority = 100
)
public class BankTask extends Task {

  private final Config config;

  @Inject
  public BankTask(Config config) {
    this.config = config;
  }

  @Override
  public boolean execute() {
    if (Province.isInGame() || config.isReady()) {
      return false;
    }

    if (!Bank.isOpen()) {
      if (config.isOpenCrates()) {
        Inventory.backpack().getItems("Supply crate").limit(9).forEach(x -> x.interact("Open"));
      }

      Bank.open();
      return true;
    }

    BackpackLoadout loadout = new BackpackLoadout("todt");
    for (WintertodtItem item : WintertodtItem.values()) {
      if (item.isRequired(config)) {
        loadout.add(new ItemEntryBuilder()
            .key(item.getName())
            .quantity(1)
            .build());
      }
    }

    //gross
    if (getAxe(Inventory.equipment()) == null) {
      Item bagged = getAxe(Inventory.backpack());
      if (bagged != null) {
        loadout.add(new ItemEntryBuilder()
            .key(bagged.getName())
            .quantity(1)
            .build());
      } else {
        //TODO get best axe if this happens
        Item banked = getAxe(Inventory.bank());
        if (banked != null) {
          loadout.add(new ItemEntryBuilder()
              .key(banked.getName())
              .quantity(1)
              .build());
        }
      }
    }

    //We count actions rather than getFoodId from config because we may have leftover cake slices.
    int remainingFoodAmount = Inventory.backpack().getCount(Items.FOOD);
    int foodRequired = config.getFoodAmount() - remainingFoodAmount;
    if (Health.getPercent() < 35) {
      foodRequired += 2;
    }

    if (foodRequired > 0) {
      ItemDefinition definition = Definitions.getItem(config.getFoodId());
      if (definition != null) {
        loadout.add(new ItemEntryBuilder()
            .key(definition.getName())
            .quantity(foodRequired)
            .build());
      }
    }

    if (!loadout.isBagged()) {
      loadout.withdraw(Inventory.bank());
    }

    return true;
  }

  private static Item getAxe(Inventory inv) {
    return inv.query().nameContains(" axe").results().first();
  }
}
