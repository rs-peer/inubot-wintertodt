package org.rspeer.scripts.wintertodt.data.locale;

import org.rspeer.game.adapter.component.World;

public enum GameWorld {

  US(307, World.Location.US),
  UK(309, World.Location.UK),
  DE(311, World.Location.DE);

  private final int id;
  private final World.Location location;

  GameWorld(int id, World.Location location) {
    this.id = id;
    this.location = location;
  }

  public static GameWorld valueOf(World.Location location) {
    for (GameWorld world : GameWorld.values()) {
      if (world.location == location) {
        return world;
      }
    }

    throw new IllegalAccessError();
  }

  public int getId() {
    return id;
  }
}
