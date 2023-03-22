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

  void message(String text) {
    //This message will interrupt the player animation
    if (text.contains("congratulations")
        || text.contains("the cold of the wintertodt")
        || text.contains("you eat")
        || text.contains("heals some health")) {
      animation = 0;
    }
  }

  public boolean isIdle(int ticksThreshold) {
    return now - animation >= ticksThreshold;
  }
}
