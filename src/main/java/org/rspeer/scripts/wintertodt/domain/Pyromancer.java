package org.rspeer.scripts.wintertodt.domain;

import org.rspeer.scripts.wintertodt.data.Gang;

public class Pyromancer {

  private final Timers timers;
  private final Gang gang;

  private boolean fallen;
  private int fallenTick;

  public Pyromancer(Timers timers, Gang gang) {
    this.timers = timers;
    this.gang = gang;
  }

  public boolean isDead() {
    return timers.now() - fallenTick >= 3;
  }

  void fall() {
    if (!fallen) {
      fallen = true;
      fallenTick = timers.now();
    }
  }

  void reset() {
    fallen = false;
    fallenTick = 0;
  }

  void update(int state) {
    if (state == 0) {
      fall();
      return;
    }

    reset();
  }

  Gang getGang() {
    return gang;
  }
}
