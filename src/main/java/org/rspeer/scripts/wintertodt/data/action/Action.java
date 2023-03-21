package org.rspeer.scripts.wintertodt.data.action;

import org.rspeer.scripts.wintertodt.domain.Domain;

public enum Action {

  FLETCH(3),
  CHOP(4);

  private final int duration;

  Action(int duration) {
    this.duration = duration;
  }

  public boolean isActive(Domain domain) {
    return !domain.isIdle(duration);
  }
}
