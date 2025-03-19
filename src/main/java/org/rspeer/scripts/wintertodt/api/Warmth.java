package org.rspeer.scripts.wintertodt.api;

import org.rspeer.game.Vars;

public class Warmth {

  public static int getPercent() {
    return Vars.get(Vars.Type.VARBIT, 11434);
  }
}
