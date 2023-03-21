package org.rspeer.scripts.wintertodt.data.position;

import org.rspeer.game.scene.Players;
import org.rspeer.scripts.wintertodt.data.Constant;

public class Province {

  public static boolean isInGame() {
    return Players.self().getPosition().getRegionId() == Constant.WINTERTODT_REGION_ID;
  }
}
