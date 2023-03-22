package org.rspeer.scripts.wintertodt.task.prepare;

import com.google.inject.Inject;
import org.rspeer.commons.logging.Log;
import org.rspeer.game.adapter.component.inventory.Bank;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Province;
import org.rspeer.scripts.wintertodt.data.item.WintertodtItem;
import org.rspeer.scripts.wintertodt.domain.Config;

import java.util.ArrayList;
import java.util.List;

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

    List<WintertodtItem> missing = new ArrayList<>();
    for (WintertodtItem item : WintertodtItem.values()) {
      if (!item.isPresent(config)) {
        missing.add(item);
      }
    }

    if (!Bank.isOpen()) {
      Bank.open();
      sleepUntil(Bank::isOpen, 4);
      return true;
    }

    Bank bank = Inventory.bank();
    for (WintertodtItem item : missing) {
      Log.info("Withdrawing " + item.getName());
      bank.withdraw(item.getName(), 1);
    }

    //We count actions rather than getFoodId from config because we may have leftover cake slices.
    int remainingFoodAmount = Inventory.backpack().getCount(iq -> iq.actions("Eat", "Drink").results());
    int foodRequired = config.getFoodAmount() - remainingFoodAmount;
    if (foodRequired > 0) {
      Log.info("Withdrawing " + foodRequired + " food!");
      bank.withdraw(config.getFoodId(), foodRequired);
    }

    return true;
  }
}
