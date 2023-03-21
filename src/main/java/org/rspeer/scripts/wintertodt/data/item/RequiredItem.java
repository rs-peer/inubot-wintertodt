package org.rspeer.scripts.wintertodt.data.item;

import org.rspeer.commons.ToBooleanFunction;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.scripts.wintertodt.data.position.Crate;
import org.rspeer.scripts.wintertodt.domain.Domain;

public enum RequiredItem {

  KNIFE(Crate.KNIFE, "Knife"),
  TINDERBOX(Crate.TINDERBOX, "Tinderbox"),
  HAMMER(Crate.HAMMER, "Hammer");

  private final Crate crate;
  private final ToBooleanFunction<Domain> condition;

  RequiredItem(Crate crate, ToBooleanFunction<Domain> condition) {
    this.crate = crate;
    this.condition = condition;
  }

  RequiredItem(Crate crate, String name) {
    this(crate, domain -> Inventory.backpack().contains(iq -> iq.names(name).results()));
  }

  public boolean isPresent(Domain domain) {
    return !crate.isRequired(domain) || condition.applyAsBoolean(domain);
  }
}
