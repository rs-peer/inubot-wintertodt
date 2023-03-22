package org.rspeer.scripts.wintertodt.task.game;

import org.rspeer.scripts.wintertodt.data.Action;
import org.rspeer.scripts.wintertodt.domain.Domain;

public abstract class ActionTask extends GameTask {

  protected ActionTask(Domain domain) {
    super(domain);
  }

  public abstract Action getAction();
}
