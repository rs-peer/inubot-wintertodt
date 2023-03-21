package org.rspeer.scripts.wintertodt.domain;

import com.google.inject.Singleton;

@Singleton
public class Timers {

  /** Current time in ticks **/
  private int now;

  /** Tick of the last animation **/
  private int animation;

  void tick() {
    now++;
  }

  void animate() {
    animation = now;
  }

  public boolean isIdle(int ticksThreshold) {
    return now - animation >= ticksThreshold;
  }
}
