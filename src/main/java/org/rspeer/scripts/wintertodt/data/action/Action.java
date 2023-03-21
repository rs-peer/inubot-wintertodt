package org.rspeer.scripts.wintertodt.data.action;

import org.rspeer.scripts.wintertodt.domain.Domain;

/**
 * Enum containing possible wintertodt actions and their durations in ticks
 */
public enum Action {

  FLETCH(1248, 3),
  CHOP(2846, 4),
  BURN(832, 3),
  LIGHT(733, 3); //might be 4 need to verify

  private final int animation;
  private final int duration;

  Action(int animation, int duration) {
    this.animation = animation;
    this.duration = duration;
  }

  public int getAnimation() {
    return animation;
  }

  public boolean isActive(Domain domain) {
    return !domain.timers().isIdle(duration);
  }
}
