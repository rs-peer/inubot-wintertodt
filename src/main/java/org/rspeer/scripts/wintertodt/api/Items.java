package org.rspeer.scripts.wintertodt.api;

import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.game.query.component.ItemQuery;
import org.rspeer.game.query.results.ItemQueryResults;
import org.rspeer.scripts.wintertodt.data.Constant;

import java.util.function.Function;

public final class Items {

  public static final Function<ItemQuery, ItemQueryResults> BURNABLE
      = iq -> iq.names(Constant.KINDLING, Constant.ROOT).results();

  public static final Function<ItemQuery, ItemQueryResults> FOOD
      = iq -> iq.actions("Eat", "Drink").results();

  public static final Function<ItemQuery, ItemQueryResults> JUNK
      = iq -> iq.names("Vial", "Jug").results();

  private Items() {
    throw new IllegalAccessError();
  }

  public static int getRootCount(Inventory inv) {
    return inv.getCount(iq -> iq.names(Constant.ROOT).results());
  }

  public static int getKindlingCount(Inventory inv) {
    return inv.getCount(iq -> iq.names(Constant.KINDLING).results());
  }

  public static int getStoredRootPoints(Inventory inv) {
    return getRootCount(inv) * 10;
  }

  public static int getStoredKindlingPoints(Inventory inv) {
    return getKindlingCount(inv) * 25;
  }

  public static int getStoredPoints(Inventory inv) {
    return getStoredRootPoints(inv) + getStoredKindlingPoints(inv);
  }
}
