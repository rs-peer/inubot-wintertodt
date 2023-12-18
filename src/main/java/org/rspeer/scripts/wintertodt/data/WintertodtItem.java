package org.rspeer.scripts.wintertodt.data;

import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.game.query.component.ItemQuery;
import org.rspeer.game.query.results.ItemQueryResults;
import org.rspeer.scripts.wintertodt.domain.config.Config;

import java.util.function.Function;

public enum WintertodtItem implements Function<ItemQuery, ItemQueryResults> {

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

  public boolean isRequired(Config config) {
    return crate.isRequired(config);
  }

  public boolean isPresent(Config config) {
    if (!crate.isRequired(config)) {
      return true;
    }

    if (wearable && Inventory.equipment().contains(this)) {
      return true;
    }

    return Inventory.backpack().contains(this);
  }

  public String getName() {
    return name;
  }

  @Override
  public ItemQueryResults apply(ItemQuery iq) {
    return iq.nameContains(name).results();
  }
}
