package org.rspeer.scripts.wintertodt.task.generic;

import com.google.inject.Inject;
import org.rspeer.game.component.Inventories;
import org.rspeer.game.component.tdi.Tab;
import org.rspeer.game.component.tdi.Tabs;
import org.rspeer.game.query.results.ItemQueryResults;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Items;
import org.rspeer.scripts.wintertodt.api.Province;
import org.rspeer.scripts.wintertodt.domain.Domain;

@TaskDescriptor(name = "Dropping")
public class DropJunkTask extends Task {

  private final Domain domain;

  @Inject
  public DropJunkTask(Domain domain) {
    this.domain = domain;
  }

  @Override
  public boolean execute() {
    if (Province.isInGame() && !domain.getBoss().isRespawning()) {
      return false;
    }

    ItemQueryResults junk = Items.JUNK.apply(Inventories.backpack().query());
    if (junk.isEmpty()) {
      return false;
    }

    if (!Tabs.isOpen(Tab.INVENTORY)) {
      Tabs.open(Tab.INVENTORY);
    }

    junk.forEach(item -> item.interact("Drop"));
    return true;
  }
}
