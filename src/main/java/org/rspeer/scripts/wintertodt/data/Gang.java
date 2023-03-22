package org.rspeer.scripts.wintertodt.data;

import org.rspeer.game.position.Position;

//What gang u reppin? WEST SIDE BEST SIDE FR
public enum Gang {

  WEST("West",
      new Position(1620, 3997),
      new Position(1620, 3988),
      new Position(1621, 3996)
  ),

  EAST("East",
      new Position(1638, 3997),
      new Position(1639, 3988),
      new Position(1639, 3996)
  ),

  NORTH_WEST("North-West",
      new Position(1620, 4015),
      new Position(1621, 4025),
      new Position(1621, 4018)
  ),

  NORTH_EAST("North-East",
      new Position(1638, 4015),
      new Position(1638, 4025),
      new Position(1639, 4018)
  );

  private final String name;
  private final Position brazier;
  private final Position roots;
  private final Position step;

  /**
   * @param name    The name as expected to display in the UI. I have to do this because the obfuscator strips constant names
   * @param brazier Position of the brazier object
   * @param roots   Position of the roots object
   * @param step    Position in front of the brazier
   */
  Gang(String name, Position brazier, Position roots, Position step) {
    this.name = name;
    this.brazier = brazier;
    this.roots = roots;
    this.step = step;
  }

  public Position getBrazier() {
    return brazier;
  }

  public Position getRoots() {
    return roots;
  }

  public Position getStep() {
    return step;
  }

  @Override
  public String toString() {
    return name;
  }
}
