package org.rspeer.scripts.wintertodt.domain;

import com.google.inject.Singleton;
import jag.script.RSScriptEvent;
import org.rspeer.game.Vars;
import org.rspeer.scripts.wintertodt.data.Constant;

@Singleton
public class Boss {

  private int energy;

  public int getEnergy() {
    return energy;
  }

  /**
   * @return The number of server ticks until the boss respawns
   */
  public int getTimer() {
    return Vars.get(Vars.Type.VARBIT, Constant.WINTERTODT_TIMER_VARBIT_ID);
  }

  public boolean isRespawning() {
    return getEnergy() == 0 && getTimer() > 0;
  }

  public void update(RSScriptEvent src) {
    if (src.getScriptId() != Constant.WINT_UPDATE_SCRIPT_ID) {
      return;
    }

    Object[] args = src.getArgs();
    if (args == null || args.length < 2) {
      return;
    }

    //[clientscript,wint_update](int $int0, int $int1, int $int2, int $int3, int $int4, int $int5, int $int6, int $int7, int $int8, int $int9)
    //if_settext("Wintertodt's Energy: <tostring(interpolate(1, 100, 0, 3500, $int1))>%", interface_396:21);
    //As illustrated above, the wintertodt energy % is internally a value between 0 and 3500.
    //We interpolate this to get a 1-100 value
    int value = (int) args[2];
    energy = (int) ((value / 3500.0 * 100.0) + 0.5);
  }
}
