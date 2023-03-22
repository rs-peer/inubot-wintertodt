package org.rspeer.scripts.wintertodt.api;

import org.rspeer.game.adapter.scene.SceneObject;
import org.rspeer.game.position.Position;
import org.rspeer.game.scene.Players;
import org.rspeer.game.scene.SceneObjects;
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
}
