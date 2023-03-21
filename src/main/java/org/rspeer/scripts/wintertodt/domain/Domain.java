package org.rspeer.scripts.wintertodt.domain;

import com.google.inject.Singleton;
import jag.script.RSScriptEvent;
import org.rspeer.event.Subscribe;
import org.rspeer.game.Vars;
import org.rspeer.game.adapter.scene.EffectObject;
import org.rspeer.game.event.*;
import org.rspeer.game.scene.Players;
import org.rspeer.game.script.event.ScriptConfigEvent;
import org.rspeer.game.script.meta.ScriptConfig;
import org.rspeer.scripts.wintertodt.data.Constant;
import org.rspeer.scripts.wintertodt.data.position.Gang;

@Singleton
public class Domain {

  private boolean fletch;
  private int wintertodtEnergy;
  private Gang gang = Gang.WEST;
  private int tick = 0;
  private int activeTick = 0;

  @Subscribe
  public void notify(ScriptConfigEvent event) {
    ScriptConfig config = event.getSource();
    fletch = config.getBoolean("Fletch");
    gang = config.get("Side");
  }

  @Subscribe
  public void notify(ClientScriptEvent event) {
    RSScriptEvent src = event.getSource();
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
    wintertodtEnergy = (int) ((value / 3500.0 * 100.0) + 0.5);
  }

  @Subscribe
  public void notify(TickEvent event) {
    tick++;
  }

  @Subscribe
  public void notify(AnimationEvent event) {
    if (!event.getSource().equals(Players.self())) {
      return;
    }

    this.activeTick = tick;
  }

  @Subscribe
  public void notify(EffectObjectSpawnEvent event) {
    EffectObject fx = event.getSource();
    //We will be using this to detect snowfall.
  }

  public boolean isFletch() {
    return fletch;
  }

  public int getWintertodtEnergy() {
    return wintertodtEnergy;
  }

  /**
   * @return The number of server ticks until wintertodt respawns
   */
  public int getWintertodtTimer() {
    return Vars.get(Vars.Type.VARBIT, Constant.WINTERTODT_TIMER_VARBIT_ID);
  }

  public Gang getGang() {
    return gang;
  }

  public boolean isIdle(int ticksThreshold) {
    return tick - activeTick >= ticksThreshold;
  }

  public boolean isInGame() {
    return Players.self().getPosition().getRegionId() == Constant.WINTERTODT_REGION_ID;
  }

  public boolean isWaiting() {
    return getWintertodtEnergy() == 0 && getWintertodtTimer() > 0;
  }
}
