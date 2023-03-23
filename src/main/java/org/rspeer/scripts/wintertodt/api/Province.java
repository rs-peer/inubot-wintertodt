package org.rspeer.scripts.wintertodt.api;

import org.rspeer.game.adapter.scene.Npc;
import org.rspeer.game.adapter.scene.SceneObject;
import org.rspeer.game.position.Position;
import org.rspeer.game.scene.*;
import org.rspeer.scripts.wintertodt.data.Constant;

public final class Province {

  private Province() {
    throw new IllegalAccessError();
  }

  public static boolean isInGame() {
    return Players.self().getPosition().getRegionId() == Constant.WINTERTODT_REGION_ID;
  }

  public static SceneObject findDoor() {
    return SceneObjects.query()
        .names("Doors of Dinh")
        .results()
        .nearest();
  }

  public static SceneObject findBrazier(Position position, String action) {
    return SceneObjects.query()
        .nameContains("brazier")
        .actions(action)
        .on(position)
        .results()
        .first();
  }

  public static SceneObject findRoots(Position position) {
    return SceneObjects.query()
        .nameContains(Constant.ROOT)
        .on(position)
        .results()
        .first();
  }

  /**
   * Note: Npcs are only loaded as far as the minimap distance
   * due to that, this function should only be used to verify that
   * the pyromancer for the defined side is down for the count.
   */
  public static Npc findIncapacitatedPyromancer(Position position) {
    return Npcs.query()
        .within(position, 4)
        .nameContains("Incapacitated")
        .results()
        .nearest();
  }
}
