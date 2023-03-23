package org.rspeer.scripts.wintertodt.task.game;

import org.rspeer.scripts.wintertodt.data.Action;
import org.rspeer.scripts.wintertodt.domain.Domain;

public abstract class ActionTask extends GameTask {

  protected final Action action;

  protected ActionTask(Domain domain, Action action) {
    super(domain);
    this.action = action;
  }
}
