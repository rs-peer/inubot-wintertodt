package org.rspeer.scripts.wintertodt.data.action;

import org.rspeer.scripts.wintertodt.domain.Domain;

/**
 * Enum containing possible wintertodt actions and their durations in ticks
 */
public enum Action {

  //TODO Add repair brazier action

  REPAIR(3676, 3),
  FLETCH(1248, 3),
  CHOP(2846, 4),
  BURN(832, 4),
  LIGHT(733, 4); //might be 4 need to verify

  private final int animation;
  private final int duration;

  Action(int animation, int duration) {
    this.animation = animation;
    this.duration = duration;
  }

  public int getAnimation() {
    return animation;
  }

  public int getDuration() {
    return duration;
  }

  public boolean isActive(Domain domain) {
    return domain.getState().getLastAnimation() == animation && !domain.getTimers().isIdle(duration);
  }
}
