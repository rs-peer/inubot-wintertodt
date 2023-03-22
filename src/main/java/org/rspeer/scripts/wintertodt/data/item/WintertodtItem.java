package org.rspeer.scripts.wintertodt.data.item;

import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.scripts.wintertodt.data.position.Crate;
import org.rspeer.scripts.wintertodt.domain.Config;

public enum WintertodtItem {

  KNIFE(Crate.KNIFE, "Knife"),
  TINDERBOX(Crate.TINDERBOX, "Tinderbox"),
  HAMMER(Crate.HAMMER, "Hammer"),
  AXE(Crate.AXE, "Axe", true);

  private final Crate crate;
  private final String name;
  private final boolean wearable;

  WintertodtItem(Crate crate, String name, boolean wearable) {
    this.crate = crate;
    this.name = name;
    this.wearable = wearable;
  }

  WintertodtItem(Crate crate, String name) {
    this(crate, name, false);
  }

  public boolean isPresent(Config config) {
    if (!crate.isRequired(config)) {
      return true;
    }

    if (wearable && Inventory.equipment().contains(iq -> iq.nameContains(name).results())) {
      return true;
    }

    return Inventory.backpack().contains(iq -> iq.names(name).results());
  }

  public String getName() {
    return name;
  }
}
