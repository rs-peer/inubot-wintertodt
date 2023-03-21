package org.rspeer.scripts.wintertodt.api;

import org.rspeer.game.scene.Players;
import org.rspeer.scripts.wintertodt.data.Constant;

public final class Province {

  private Province() {
    throw new IllegalAccessError();
  }

  public static boolean isInGame() {
    return Players.self().getPosition().getRegionId() == Constant.WINTERTODT_REGION_ID;
  }
}
