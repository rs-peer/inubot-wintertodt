package org.rspeer.scripts.wintertodt.api;

import org.rspeer.game.Vars;

public class Warmth {

  public static int getPercent() {
    int warmth = Vars.get(Vars.Type.VARBIT, 11434);
    if (warmth == 0) {
      return 0;
    }

    return warmth / 10;
  }
}
