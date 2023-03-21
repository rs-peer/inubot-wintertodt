package org.rspeer.scripts.wintertodt.data;

import org.rspeer.commons.ToBooleanFunction;
import org.rspeer.game.position.Position;
import org.rspeer.scripts.wintertodt.domain.Domain;

public enum Crate {

  HAMMER(new Position(1626, 3980)),
  KNIFE(new Position(1634, 3980), Domain::isFletch),
  TINDERBOX(new Position(1634, 3984));

  private final Position position;
  private final ToBooleanFunction<Domain> required;

  Crate(Position position, ToBooleanFunction<Domain> required) {
    this.position = position;
    this.required = required;
  }

  Crate(Position position) {
    this(position, domain -> true);
  }

  public Position getPosition() {
    return position;
  }

  public boolean isRequired(Domain domain) {
    return required.applyAsBoolean(domain);
  }
}
