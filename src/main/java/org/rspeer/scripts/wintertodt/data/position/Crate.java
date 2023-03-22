package org.rspeer.scripts.wintertodt.data.position;

import org.rspeer.game.position.Position;
import org.rspeer.scripts.wintertodt.domain.Config;

import java.util.function.Predicate;

public enum Crate {

  HAMMER(new Position(1626, 3980)),
  KNIFE(new Position(1634, 3980), Config::isFletch),
  TINDERBOX(new Position(1634, 3984)),
  AXE(new Position(1626, 3984));

  private final Position position;
  private final Predicate<Config> required;

  Crate(Position position, Predicate<Config> required) {
    this.position = position;
    this.required = required;
  }

  Crate(Position position) {
    this(position, config -> true);
  }

  public Position getPosition() {
    return position;
  }

  public boolean isRequired(Config config) {
    return required.test(config);
  }
}
